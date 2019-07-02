package com.asm.loader;

import com.asm.adapter.MethodChangeAdapter;
import com.asm.test.AsmTest;
import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: jidonglin
 * @Date: 2019/6/20 10:29
 */
public class AsmExample extends ClassLoader implements Opcodes {
    public static void main(String[] args) throws IOException, IllegalArgumentException, SecurityException,
            IllegalAccessException, InvocationTargetException, InstantiationException{
        //在原有类新增方法，属性等
//        ClassReader classReader = new ClassReader(AsmTest.class.getName());
//        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
//        ClassVisitor classVisitor = new MethodChangeAdapter(classWriter);
//        classReader.accept(classVisitor, Opcodes.ASM4);
//
//        byte[] code = classWriter.toByteArray();
//
//        File file=new File("D:\\asmTest\\classes\\com\\asm\\test");
//        if(!file.exists()){//如果文件夹不存在
//            file.mkdirs();//创建文件夹
//        }
//        FileOutputStream fileOutputStream = new FileOutputStream("D:\\asmTest\\classes\\com\\asm\\test\\AsmTest.class");
//        fileOutputStream.write(code);
//        fileOutputStream.close();

        //新增一个类
        ClassWriter classWriter1 = new ClassWriter(0);
        //类名
        classWriter1.visit(V1_7, ACC_PUBLIC, "com/asm/test/newClass", null, null, null);
        //注释
        AnnotationVisitor annotationVisitor = classWriter1.visitAnnotation("Lorg/springframework/data/mongodb/core/mapping/Document;", true);
        //注释参数
        annotationVisitor.visit("collection", "uc_members");
        annotationVisitor.visitEnd();

        MethodVisitor methodVisitor = classWriter1.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "", "<init>", "()V");
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();

        byte[] code1 = classWriter1.toByteArray();
        FileOutputStream fileOutputStream1 = new FileOutputStream("D:\\asmTest\\classes\\com\\asm\\test\\newClass.class");
        fileOutputStream1.write(code1);
        fileOutputStream1.close();


    }
}
