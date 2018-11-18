package com.zhegui.myorm.v2.config;

import com.zhegui.myorm.v2.annotation.ZGDelete;
import com.zhegui.myorm.v2.annotation.ZGInsert;
import com.zhegui.myorm.v2.annotation.ZGSelect;
import com.zhegui.myorm.v2.annotation.ZGUpdate;
import com.zhegui.myorm.v2.bind.MapperRegister;
import com.zhegui.myorm.v2.enums.SqlActionType;
import com.zhegui.myorm.v2.plugin.ZGInterceptorChain;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;

/**
 * create by zhegui on 2018/11/17
 */
public class ZGConfiguration {

    private String scanPath;

    private volatile boolean isScan = false;

    private MapperRegister mapperRegister = new MapperRegister();

    private ZGInterceptorChain interceptorChain = new ZGInterceptorChain();

    public ZGConfiguration(String scanPath) {
        this.scanPath = scanPath;
    }

    public ZGInterceptorChain getInterceptorChain() {
        return interceptorChain;
    }

    public ZGConfiguration build() throws UnsupportedEncodingException, ClassNotFoundException {
        if(scanPath == null || "".equals(scanPath.toString())){
            throw new IllegalArgumentException("");
        }
        doScanPath();
        return this;
    }

    private void doScanPath() throws UnsupportedEncodingException, ClassNotFoundException {
        if(isScan){
            System.out.println("is already scan !");
            return ;
        }
        String filePath = scanPath.replace(".", "/");
        URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
        //https://blog.csdn.net/u013871439/article/details/70231288/
        //在这里不处理jar包里面的情况
        if(url !=null && url.toString().startsWith("file")){
            String dirFilePath = URLDecoder.decode(url.getFile(),"utf-8");
            File dirFile = new File(dirFilePath);
            if(dirFile != null && dirFile.isDirectory()){
                File[] classFiles = dirFile.listFiles();
                for (File fileItem: classFiles) {
                    if(fileItem.isFile() && fileItem.getName().endsWith(".class")){
                        String absFilePath = fileItem.getAbsolutePath();
                        int index = absFilePath.lastIndexOf("classes");
                        String clazzFilePath = absFilePath.substring(index + 7 + 1);
                        //去掉 .class
                        String clazzName = clazzFilePath.substring(0, clazzFilePath.length() - 6);
                        clazzName = clazzName.replaceAll("\\\\",".");
                        Class<?> clazz = Class.forName(clazzName);
                        if(!clazz.isInterface()){
                            return;
                        }
                        System.out.println(clazz);
                        Method[] methods = clazz.getDeclaredMethods();
                        for (Method method : methods) {
                            Annotation[] methodAnnotations = method.getAnnotations();
                            for (Annotation annotation: methodAnnotations) {
                                Class<?> annotationClazz = annotation.annotationType();
                                if(annotationClazz.equals(ZGSelect.class)){
                                    mapperRegister.addMapperSql(((ZGSelect)annotation).sql(), method.getName(),SqlActionType.SELECT, clazz,method.getReturnType());
                                }else if(annotationClazz.equals(ZGDelete.class)){
                                    mapperRegister.addMapperSql(((ZGDelete)annotation).sql(), method.getName(),SqlActionType.DELETE, clazz, method.getReturnType());
                                }else if (annotationClazz.equals(ZGUpdate.class)){
                                    mapperRegister.addMapperSql(((ZGUpdate)annotation).sql(), method.getName(),SqlActionType.UPDATE, clazz, method.getReturnType());
                                }else if(annotationClazz.equals(ZGInsert.class)){
                                    mapperRegister.addMapperSql(((ZGInsert)annotation).sql(), method.getName(),SqlActionType.INSERT, clazz, method.getReturnType());
                                }
                            }
                        }
                    }
                }
            }
        }
        isScan = true;
    }

    public MapperRegister getMapperRegister() {
        return mapperRegister;
    }

    public static void main(String[] args) {
        ZGConfiguration configuration = new ZGConfiguration("com.zhegui.myorm.v2.mapper");
        try {
            configuration.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
