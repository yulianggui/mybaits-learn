package com.zhegui.myorm.v2.plugin;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.PluginException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * create by zhegui on 2018/11/18
 */
public class ZGPlugin implements InvocationHandler{

    private Object target;

    private ZGInterceptor interceptor;

    private Map<Class<?>, Set<Method>> signatureMap;

    public ZGPlugin(Object target, ZGInterceptor interceptor, Map<Class<?>, Set<Method>> signatureMap) {
        this.target = target;
        this.interceptor = interceptor;
        this.signatureMap = signatureMap;
    }

    /**
     *
     * @param target
     * @param interceptor
     * @return
     */
    public static Object wrap(Object target, ZGInterceptor interceptor){
        System.out.println("new interceptor:" + interceptor.toString());
        Map<Class<?>, Set<Method>> signatureMapA = getSignatureMap(interceptor);  //需要拦截的方法
        Class<?> type = target.getClass(); //被代理类的类型
        Class<?>[] interfaces = getAllInterfaces(type, signatureMapA);  //被代理类所有的接口，包括父类
        if(interfaces.length > 0 ){
            System.out.println("插件增强了 target");
            return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                    interfaces, new ZGPlugin(target, interceptor, signatureMapA));
        }
        return target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Set<Method> methods = signatureMap.get(method.getDeclaringClass());
        //如果是需要拦截的方法，则使用用户实现的 intercept 方法处理
        //当然，在intercept中，记得返回调用 method.invoke(target, args);
        if(methods != null && methods.contains(method)){
            return this.interceptor.intercept(new ZGInvocation(target, method, args));
        }else{
            return method.invoke(target, args);
        }
    }

    /**
     * 解析interceptor 上的注解，获取到ZGIntercepts外层注解的信息
     *    并拿到内层注解ZGSignature
     *      遍历ZGSignature注解的各个值，将Method拿到
     * @param interceptor
     * @return
     */
    private static Map<Class<?>, Set<Method>> getSignatureMap(ZGInterceptor interceptor){
        ZGIntercepts ZGInterceptsAnnotation = (ZGIntercepts) interceptor.getClass().getAnnotation(ZGIntercepts.class);
        if(ZGInterceptsAnnotation == null){
            System.out.println("ZGInterceptsAnnotation == null");
            throw new PluginException("No @Intercepts annotation was found in interceptor " + interceptor.getClass().getName());
        }else{
            Map<Class<?>, Set<Method>> signatureMap = new HashMap<>();
            ZGSignature[] value = ZGInterceptsAnnotation.value();
            for (ZGSignature signature : value) {
                Set<Method> methods = signatureMap.get(signature.type());
                if(methods == null){
                    methods = new HashSet<>();
                    signatureMap.put(signature.type(), methods);
                }
                String methodName = signature.method();
                try {
                    //获取到需要代理拦截的方法
                    //将方法添加到clazz对于的signatureMap中
                    Method method = signature.type().getMethod(methodName, signature.args());
                    signatureMap.get(signature.type()).add(method);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    throw  new RuntimeException("Could not find method on " + signature.type() + " named "
                            + signature.method() + ". Cause: " + e, e);
                }

            }
            return signatureMap;
        }
    }

    /**
     * 遍历type的超类
     *    for(interfacesSet = new HashSet(); type != null; type = type.getSuperclass())
     *        type = type.getSuperclass()的父类
     *           继续一种往下走
     *    找到target所有的接口
     * @param type
     * @param signatureMap
     * @return
     */
    private static Class<?>[] getAllInterfaces(Class<?> type, Map<Class<?>, Set<Method>> signatureMap){
        HashSet<Class<?>> interfacesSet = new HashSet();
        for(interfacesSet = new HashSet(); type != null; type = type.getSuperclass()){
            Class<?>[] interfaces = type.getInterfaces();
            for (int i=0; i< interfaces.length; i++) {
                Class<?> c = interfaces[i];
                if (signatureMap.containsKey(c)) {
                    interfacesSet.add(c);
                }
            }
        }
        return interfacesSet.toArray(new Class[interfacesSet.size()]);
    }
}
