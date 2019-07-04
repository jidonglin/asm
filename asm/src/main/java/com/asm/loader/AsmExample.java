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
        //参数分别为：1、版本号，2、类的访问标识，3、类名（必须带有包名），4、与泛型相关（对应class的signature属性），5、父类，6、要实现的接口
        classWriter1.visit(V1_1, ACC_PUBLIC , "com/asm/test/NewClass", null, "java/lang/Object", null);
//        //注释
//        AnnotationVisitor annotationVisitor = classWriter1.visitAnnotation("Lorg/springframework/data/mongodb/core/mapping/Document;", true);
//        //注释参数
//        annotationVisitor.visit("collection", "uc_members");
//        annotationVisitor.visitEnd();

//        FieldVisitor fieldVisitor = classWriter1.visitField(ACC_PUBLIC, "newField", "Ljava/lang/String;", null, null);
//        fieldVisitor.visitEnd();

        //添加构造函数
        MethodVisitor methodVisitor = classWriter1.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);

        //生成构造方法的字节码指令
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();



        //新增方法 ([Ljava/lang/String;)V String数组   (Ljava/lang/String;)V String对象  ()V无参数方法
        methodVisitor = classWriter1.visitMethod(ACC_PUBLIC + ACC_STATIC, "newMethod", "()V", null, new String[]{"java/lang/Exception"});
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitLdcInsn("Hello world!");
        methodVisitor .visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");

        methodVisitor.visitLdcInsn("arg1");
        //方法多个参数使用
//        methodVisitor.visitLdcInsn("arg2");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "com/asm/test/call/CallMethod", "methodName", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "com/asm/test/call/CallMethod", "methodName", "(Ljava/lang/String;)Ljava/lang/String;");

        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(2, 2);
        methodVisitor.visitEnd();
        byte[] code1 = classWriter1.toByteArray();
        FileOutputStream fileOutputStream1 = new FileOutputStream("D:\\asmTest\\classes\\com\\asm\\test\\NewClass.class");
        fileOutputStream1.write(code1);
        fileOutputStream1.close();


    }
}
