package com.ecommerceapp.exception;

public class ResourceNotFoundException extends RuntimeException {

    private final String className;
    private final String methodName;

    public ResourceNotFoundException(String message, String className, String methodName) {
        super("[" + className + "." + methodName + "] " + message);
        this.className = className;
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }
}