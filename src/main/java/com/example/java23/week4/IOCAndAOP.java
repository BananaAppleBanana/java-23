package com.example.java23.week4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Why Spring / Advantages of Spring / Features from Spring
 * 1. IOC (inversion of control)
 *    Dependency Injection (implementation of IOC)
 *       a. ApplicationContext (factory / Map)
 *       b. class level: @Component(utility..), @Controller(SpringMVC), @Service(business), @Repository(dao),
 *          method level: @Bean
 *       c. @Autowired => inject beans
 *          field injection  X
 *          setter injection
 *          constructor injection
 *
 *          by type
 *          by name: @Qualifier, reference name,
 *          @Primary
 *       d. bean scope: Singleton(default), Prototype, Request, Session, Global Session
 * 2. AOP
 *      @Aspect
 *      class EmpAOP {
 *
 *          @Before
 *          @PointCutting(location)
 *          public void print() {
 *              ..
 *          }

            @After
 *          @PointCutting(location)
 *          public void print() {
 *              ..
 *          }
 *
 *          //define point cutting = location
 *      }
 * RMI class {
 *      List<Interceptors> list {before1, after1, before2}
 *      int idx = 0;
 *      proceed() {
 *          if(idx >= list.size()) {
 *              return execute real logic();
 *          }
 *          Interceptor itc = list.get(idx++);
 *          itc.execute(this);
 *
 *      }
 *}
 * BeforeInterceptor {
 *     execute(RMI rmi) {
 *         run before logic
 *         return rmi.proceed();
 *     }
 *}
 *
 * AfterInterceptor {
 *     execute(RMI rmi) {
 *         Object res = rmi.proceed();
 *         run after logic
 *         return res;
 *     }
 *}
 *  Before1
 *  execute before1,
 *  return proceed()
 *          After1
 *          res = rmi.proceed()
 *                  Before2
 *                  execute before2
 *                  return proceed()
 *                      execute real logic
 *          execute after logic
 *          return res
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   Spring
 *       package -> war -> upload to server container
 *   Spring Boot
 *      1. based on Spring
 *      2. application properties/yml
 *      3. enable auto configuration  => spring.factories
 *      4. main method + embedded tomcat
 *      5. actuator
 *      6. microservice
 *
 *
 *   Spring Boot + hibernate
 *
 *    *    *    *    *    *    *    *    *    *    *    *
 *    network
 *    http
 *    tcp
 *    spring mvc
 *    rest api
 *    example: spring boot + spring MVC + rest api
 *
 */

@SpringBootApplication
class IOCAndAOP {
    private static EmpService empService1;
    private static EmpService empService2;

    @Autowired
    public IOCAndAOP(
            @Qualifier("empServiceImpl1") EmpService s1,
            @Qualifier("empServiceImpl1") EmpService s2)
    {
        this.empService1 = s1;
        this.empService2 = s2;
    }

    public static void main(String[] args) {
        SpringApplication.run(IOCAndAOP.class, args);
        System.out.println(empService1 == empService2);
    }
}
@Service
interface EmpService {}
@Service
@Scope("prototype")
class EmpServiceImpl1 implements EmpService {
    @Override
    public String toString() {
        return "EmpServiceImpl1{}";
    }
}
@Service
class EmpServiceImpl2 implements EmpService {
    @Override
    public String toString() {
        return "EmpServiceImpl2{}";
    }
}

@Configuration
class MyConfig {

    @Bean
    public Map<Integer, Integer> getMap() {
        return new HashMap<>();
    }
}