package com.tvd12.master_design_patterns;

public final class LazyInitSingletonSingleCheckedLocking {

    private static LazyInitSingletonSingleCheckedLocking instance;

    private LazyInitSingletonSingleCheckedLocking() {}

    public static LazyInitSingletonSingleCheckedLocking getInstance() {
        synchronized (LazyInitSingletonSingleCheckedLocking.class) {
            if (instance == null) {
                instance = new LazyInitSingletonSingleCheckedLocking();
                instance.loadDataFromDatabase();
            }
        }
        return instance;
    }

    private void loadDataFromDatabase() {}

    public void doSomeThing() {
        System.out.println("Do something");
    }
}
