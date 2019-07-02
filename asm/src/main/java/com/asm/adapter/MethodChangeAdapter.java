package com.asm.adapter;

import com.asm.vistor.AsmMethodVisit;
import org.objectweb.asm.*;

/**
 * @Author: jidonglin
 * @Date: 2019/6/20 9:48
 */
public class MethodChangeAdapter extends ClassVisitor implements Opcodes {
    public MethodChangeAdapter(ClassVisitor cv) {
        super(Opcodes.ASM4, cv);
    }

    public MethodChangeAdapter(int i) {
        super(i);
    }

    public MethodChangeAdapter(int i, ClassVisitor classVisitor) {
        super(i, classVisitor);
    }

    @Override
    public void visit(int i, int i1, String s, String s1, String s2, String[] strings) {
        super.visit(i, i1, s, s1, s2, strings);
    }

    @Override
    public void visitSource(String s, String s1) {
        super.visitSource(s, s1);
    }

    @Override
    public void visitOuterClass(String s, String s1, String s2) {
        super.visitOuterClass(s, s1, s2);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, boolean b) {
        return super.visitAnnotation(s, b);
    }


    @Override
    public void visitAttribute(Attribute attribute) {
        super.visitAttribute(attribute);
    }

    @Override
    public void visitInnerClass(String s, String s1, String s2, int i) {
        super.visitInnerClass(s, s1, s2, i);
    }

    /**
     * 属性的修改
     * @param i
     * @param s
     * @param s1
     * @param s2
     * @param o
     * @return
     */
    @Override
    public FieldVisitor visitField(int i, String s, String s1, String s2, Object o) {
        System.out.println(s1+"---"+s);
        return super.visitField(i, s, s1, s2, o);
    }

    /**
     * 方法名的修改
     * @param i
     * @param s
     * @param s1
     * @param s2
     * @param strings
     * @return
     */
    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        System.out.println(s1+"---"+s);
        //修改方法名
        if (cv != null && "exec".equals(s)){
            return cv.visitMethod(ACC_PUBLIC+ACC_STATIC,"exec"+"Test", s1, s2, strings);
        }
        if (cv != null && "changeMethodContext".equals(s)){
            MethodVisitor methodVisitor = cv.visitMethod(i, s, s1, s2, strings);
            MethodVisitor newMethod = new AsmMethodVisit(methodVisitor);
            return  newMethod;
        }
        return super.visitMethod(i, s, s1, s2, strings);
    }

    @Override
    public void visitEnd() {
        //新增属性
        FieldVisitor fieldVisitor = cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "name", Type.getDescriptor(String.class), "signature","newValue");
        fieldVisitor.visitEnd();

        //新增方法
        MethodVisitor methodVisitor = cv.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "add",
                "([Ljava/lang/String;)V", null, null);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitLdcInsn("new method");
        //引用对象调用类的方法
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();



        FieldVisitor fieldVisitor1 = cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "name1", Type.getDescriptor(String.class), "signature","newValue");
        fieldVisitor1.visitEnd();
    }
}
