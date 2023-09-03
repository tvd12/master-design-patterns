package com.tvd12.master_design_patterns;

public final class LazyInitSingletonDoubleCheckedLocking {

    private static LazyInitSingletonDoubleCheckedLocking instance;

    private LazyInitSingletonDoubleCheckedLocking() {}

    public static LazyInitSingletonDoubleCheckedLocking getInstance() {
        if (instance == null) {
            synchronized (LazyInitSingletonDoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new LazyInitSingletonDoubleCheckedLocking();
                    instance.loadDataFromDatabase();
                }
            }
        }
        return instance;
    }

    private void loadDataFromDatabase() {}

    public void doSomeThing() {
        System.out.println("Do something");
    }
}
