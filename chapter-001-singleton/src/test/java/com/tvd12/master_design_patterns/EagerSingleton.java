package com.tvd12.master_design_patterns;

public final class EagerSingleton {

    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public void doSomeThing() {
        System.out.println("Do something");
    }

    public static void someStaticMethod() {
        System.out.println("Some static method");
    }
}

