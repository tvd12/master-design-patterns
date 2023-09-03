using System;
using System.Threading;

namespace master_design_pattern
{
    public sealed class EagerSingleton
    {
        private static readonly EagerSingleton INSTANCE =
            new EagerSingleton();

        private EagerSingleton() { }

        public static EagerSingleton GetInstance()
        {
            return INSTANCE;
        }
    }

    public sealed class LazyInitSingletonSingleCheckedLocking
    {

        private static LazyInitSingletonSingleCheckedLocking instance;
        private static readonly object LOCK = new object();

        private LazyInitSingletonSingleCheckedLocking() { }

        public static LazyInitSingletonSingleCheckedLocking GetInstance()
        {
            lock (LOCK)
            {
                if (instance == null)
                {
                    instance = new LazyInitSingletonSingleCheckedLocking();
                    instance.LoadDataFromDatabase();
                }
            }
            return instance;
        }

        private void LoadDataFromDatabase() { }
    }

    public sealed class LazyInitSingletonDoubleCheckedLocking
    {

        private static LazyInitSingletonDoubleCheckedLocking instance;
        private static readonly object LOCK = new object();

        private LazyInitSingletonDoubleCheckedLocking() { }

        public static LazyInitSingletonDoubleCheckedLocking GetInstance()
        {
            if (instance == null)
            {
                lock (LOCK)
                {
                    if (instance == null)
                    {
                        instance = new LazyInitSingletonDoubleCheckedLocking();
                        instance.LoadDataFromDatabase();
                    }
                }
            }
            return instance;
        }

        private void LoadDataFromDatabase() { }
    }

    public sealed class LazyInitSingletonWithoutLocking
    {

        private static LazyInitSingletonWithoutLocking instance;

        private LazyInitSingletonWithoutLocking() {
            Console.WriteLine(
              "Initializing by thread: " + Thread.CurrentThread.Name
           );
            Thread.Sleep(1000);
            Console.WriteLine(
              "Initialized by thread: " + Thread.CurrentThread.Name
           );

        }

        public static LazyInitSingletonWithoutLocking GetInstance()
        {
            if (instance == null)
            {
                instance = new LazyInitSingletonWithoutLocking();
                instance.LoadDataFromDatabase();
            }
            return instance;
        }

        private void LoadDataFromDatabase() { }

        public static void Test()
        {
            Thread[] threads = new Thread[5];
            for (int i = 0; i < threads.Length; ++i)
            {
                threads[i] = new Thread(() =>
                {
                    GetInstance();
                });
                threads[i].Name = "Thread-" + i;
            }
            for (int i = 0; i < threads.Length; ++i)
            {
                threads[i].Start();
            }
        }
    }
}
