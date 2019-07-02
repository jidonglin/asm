package com.asm.adapter;

import jdk.internal.org.objectweb.asm.TypePath;
import org.objectweb.asm.*;

/**
 * @Author: jidonglin
 * @Date: 2019/6/20 14:47
 */
public class FieldChangeAdapter extends FieldVisitor {
    public FieldChangeAdapter(int api) {
        super(api);
    }

    public FieldChangeAdapter(int api, FieldVisitor fv) {
        super(api, fv);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return super.visitAnnotation(desc, visible);
    }

    @Override
    public void visitAttribute(Attribute attr) {
        super.visitAttribute(attr);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
