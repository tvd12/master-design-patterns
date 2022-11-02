package com.tvd12.master_design_patterns;

public final class LazyInitSingleton {

    private static LazyInitSingleton instance;

    private LazyInitSingleton() {}

    public static LazyInitSingleton getInstance() {
        if (instance == null) {
            synchronized (LazyInitSingleton.class) {
                if (instance == null) {
                    instance = new LazyInitSingleton();
                }
            }
        }
        return instance;
    }

    public void doSomeThing() {
        System.out.println("Do something");
    }
}
