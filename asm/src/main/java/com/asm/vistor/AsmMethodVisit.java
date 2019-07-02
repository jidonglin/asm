package com.asm.vistor;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @Author: jidonglin
 * @Date: 2019/6/20 9:56
 */
public class AsmMethodVisit extends MethodVisitor {



    public AsmMethodVisit(MethodVisitor mv) {
        super(Opcodes.ASM4, mv);
    }
    @Override
    public void visitCode() {
        //此方法在访问方法的头部时被访问到，仅被访问一次，此处可插入新的指令
//        mv.visitCode();
//        mv.visitFieldInsn(Opcodes.GETSTATIC, "owner", "timer", "J");
//        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
//        mv.visitInsn(Opcodes.LSUB);
//        mv.visitFieldInsn(Opcodes.PUTSTATIC, "owner", "timer", "J");
    }

    @Override
    public void visitInsn(int i) {
        //此方法可以获取方法中每一个条指令的操作类型，被访问多次
        //如应在方法结尾处添加新的指令，则应判断
        //在方法结尾新增打印
        if (i == Opcodes.RETURN){
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Hello world");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        }
        super.visitInsn(i);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc) {
        super.visitMethodInsn(opcode, owner, name, desc);
    }
}
