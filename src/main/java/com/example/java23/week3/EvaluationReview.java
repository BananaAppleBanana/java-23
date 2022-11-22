package com.example.java23.week3;

import java.sql.Array;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *           Throwable
 *          /        \
 *        Error   Exception(checked)
 *                    \
 *                 Runtime Exception(unchecked)
 *
 *
 *      exception  ->  log file
 *
 *
 *      throw new Exception
 */

class MyRuntimeException extends RuntimeException {

}

/**
 *
 *    HashMap<Employee, Integer>
 *          hashcode equals
 *
 *    int hashcode() {
 *        return 5;
 *    }
 *
 *    boolean equals(Object obj) {
 *        ..
 *    }
 *
 *     *     *     *     *     *     *     *     *     *
 *    why is hashMap O(1) time complexity
 *     *     *     *     *     *     *     *     *     *
 *    concurrent hashmaps
 *      put -> synchronized first node in bucket
 *
 *
 *   T1 ->  put(1, 1),... put(1, 10).... put(1, 20).... put(x, x)
 *          1000 x put
 *            get(1)
 *
 *
 *
 *     public volatile int a = 5;
 *
 *
 *     public void get() {
 *         return a;
 *     }
 *
 *     public synchronized void set(int a) {
 *         int x = a;
 *         xxxxx
 *         this.a = res;
 *     }
 *
 */
class VolatileTest {
    private static volatile boolean flag = true;
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            while(flag) {

            }
            System.out.println("break infinite for loop");
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = false;
            System.out.println("current flag = " + flag);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }


}

/**
 *  wait vs sleep
 *  wait(5000)
 */

/**
 *  s1 = new SynchronizedTest();
 *  s2 = new SynchronizedTest();
 *  s1.get3() vs s2.get3()
 *
 *  synchronized => object / instance
 */
class SynchronizedTest {

    //SynchronizedTest.class object
    public synchronized static void get1() {
    }

    //SynchronizedTest.class object
    public static void get2() {
        synchronized (SynchronizedTest.class) {}
    }

    //this
    public synchronized void get3() {}

    //this
    public void get4() {
        synchronized (this) {}
    }
}

/**
 *  synchronized vs reentrant lock (Condition)
 */

/**
 *  ThreadPoolExecutor(min pool size, max pool size, time unit, alive time, blockingqueue, thread factory)
 *      single thread pool = fixed thread pool
 *      cached thread pool
 *      schedule thread pool (blocking queue => delayed queue (priority queue))
 *
 *                                              netty => hashed time wheel?
 *  ForkJoinPool
 *      queue  -> t1 queue [task1][task2]
 *             -> t2 queue [task3]
 *
 */

/**
 *  java 8
 *
 *  what is lambda expression and why
 *
 *  functional interface
 *      1. Comparator
 *      2. Runnable vs Callable
 *      3. Consumer : one input, no output
 *      4. Predicate : one input, boolean output
 *      5. Function : one input, one output
 *      6. Supplier :  no input, one output
 */
class Java8Example {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(10);
        list.add(20);
        list.add(20);
        list.add(20);

        List<Integer> res = list.stream().map(x -> new Integer(x)).collect(Collectors.toList());
        //Metadata LinkedList: ReferencePipeline <-> ReferencePipeline <-> ReferencePipeline <-> ReferencePipeline <-> terminal operation
        //Iterator -> Sink(accept, next, end)  -> Sink() ->  Sink  ->  terminal operation
        //{{"1", 1}, {"10", 1}, {"20", 3}}
        Map<String, Integer> map = list.stream().map(x -> String.valueOf(x)).collect(
                HashMap::new,
                (mapRes, element) -> mapRes.put(element, mapRes.getOrDefault(element, 0) + 1),
                (m1, m2) -> m1.putAll(m2)
        );
        System.out.println(map);
    }
}

/**
 *  CompletableFuture vs Future
 */
class ThreadPoolTest {
    private static final ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception{
        impl2();
    }
    public static void impl2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<CompletableFuture<Integer>> futuresList = new ArrayList<>();
        for(int element: list) {
            futuresList.add(
                    CompletableFuture
                            .supplyAsync(() -> element, pool)
                            .thenApplyAsync(e -> e * 2)
            );
        }
        CompletableFuture<Void> cf = CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[0]));
        int res = cf.thenApply(Void -> futuresList.stream().map(CompletableFuture::join).reduce(0, (v1, v2) -> v1 + v2)).join();
        System.out.println(res);
    }

    public static void impl1() throws Exception{
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Future<Integer>> futuresList = new ArrayList<>();
        // sum(element * 2)
        for(int element: list) {
            Future<Integer> future = pool.submit(() -> element * 2);
            futuresList.add(future);
        }
        int res = 0;
        for(Future<Integer> future: futuresList) {
            res += future.get();
        }
        System.out.println(res);
    }
}

/**
 *  SOLID
 *  Single responsibility
 *  Open - close
 *  Liskov substitution
 *  Interface segregation
 *  Dependency inversion
 *      class A {
 *          private B b;
 *      }
 *      interface B {}
 *      class BImpl1 implements B {}
 *      class BImpl2 implements B {}
 */

/**
 *  stack over flow error
 *  out of memory error
 *
 *
 *     [eden][s0][s1] young gen   minor gc
 *      old gen     major gc
 *
 *      G1 : young gen + old
 *      [][][][][][][][][]
 *      [][][][][][][][][]
 *      [][][][][][][][][]
 *      [][][][][][][][][]
 *
 *      CMS
 *          1. initial mark root (STW)
 *          2. concurrent mark
 *          3. final mark(STW)
 *          4. concurrent remove
 *      Mark and sweep + compress (STW)
 *
 * 1. heap dump => Jprofiler / java mission control / memory analyzer
 *    check memory leak
 * 2. increase young gen size
 * 3. reference
 *      SoftReference => heap full -> remove object
 *      WeakReference =>
 *      PhantomReference + Reference Queue =>
 *
 */


/**
 *  sql + db
 *
 *  emp m - 1 dept
 *  emp(id(pk), name, salary, dept_id(fk))
 *  dept(id(pk), name)
 *
 *  2nd highest salary in each department
 *  display
 *  id, dept_name, salary
 *  ..  ..
 *  select ... t.salary
 *  from (select e.*, dense_rank() over (partition by d.dept_id order by salary desc) as rank
 *      from emp e (inner) join dept d e.dept_id = d.id) t
 *  where t.rank = 2
 *
 *  count number of employees in each dept
 *
 *  select d.id, count(e.id)
 *  from dept d left join emp e on d.id = e.dept_id
 *  group by d.id
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   ACID ->
 *
 *   id,  name      row_id,  tx_id, rollback pointer
 *    1    Tom         xx11      1        null
 *
 *    begin tx
 *    update table set name = 'Alex' where id = 1
 *
 *
 *   id,  name      row_id,  tx_id, rollback pointer
 *    1    Alex        xx22      2        |
 *                                        |
 *                                       1    Tom         xx11      1        null
 *
 *    rc
 *    select * => read_view(committed tx id list [1])
 *    select * => read_view
 *
 *    rr
 *    select * => read_view(committed tx id list [1])
 *    select * => use previous read_view
 *
 *    serializable
 *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *
 *     index
 *
 *          [1, 10, 20]
 *            add(15)
 *                  [15]
 *                /     \
 *          [1, 10]     [15, 20]
 *
 *                    [11, 15,  30]
 *               /     /      \       \
 *        [1, 10] <->[11, 12] <->[15, 20] <->[30, 40]
 *        rowid         rowid       rowid       rowid
 *         name
 *         ..
 *
 *    index organized table (IOT)
 *     *   *   *   *   *   *   *   *   *   *   *   *   *
 *     1 ~ Long.MAX_VALUE   => design system => query(val) : return true(exist),  false(not found)
 *
 *
 *     1. hashmap / set => redis cache cluster
 *     2. database table => primary key + b+ index
 *     3. bitmap
 *        101010000100..00000000000000    Long.MAX_VALUE
 *
 *    bitmap index
 *     id  state    NJ      NY      VA
 *     1     NJ     1       0       0
 *     2     NY     0       1       0
 *     3     NJ     1       0       0
 *     4     VA     0       0       1
 *
 *
 *      NY || VA
 *     *     *     *     *     *     *     *     *     *
 *     where id = 5 or id = 6
 *     where id >= 5 and id <= 6
 *
 *     index(table column) / full(table) / leading(t1..) / parallel()
 *     use_nl / use_hash / use_merge
 *
 *     execution plan (statistics)
 *          index access path:
 *              index unique scan
 *              index range scan
 *              index full scan
 *              index fast full scan
 *          full table scan
 *
 */

/**
 *  design pattern (singleton, factory...)
 *  design pattern + reflection
 */



/**
 *  Next Week
 *      Monday : ORM(hibernate + jpa + spring data jpa) + JDBC
 *      Tuesday : Spring + Spring Boot
 *      Wednesday : Network
 *      Thursday : Restful api
 *      Friday : Restful api
 */

