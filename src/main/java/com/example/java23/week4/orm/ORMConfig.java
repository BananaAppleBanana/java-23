package com.example.java23.week4.orm;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


/**
 *  Hibernate vs jpa
 *
 *  1 database -> 1 datasource -> 1 session factory / entity manager factory -> m sessions / entity managers
 *
 *  Hibernate : session
 *  JPA       : entity manager
 *                  persist() : insert
 *                  merge(stu) : if find id => update
 *                               if not     => persist(stu)
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   Book  1 - m BARelation m - 1 Author
 *
 *   database :
 *   Book (id(pk), name)
 *   BARelation (id, b_id(fk), a_id(fk))
 *   Author (id(pk), name)
 *
 *   Java :
 *   @Table(..)
 *   class Book {
 *      @Column(..)
 *      private String name;
 *      @OneToMany(mappedBy = "bookField123456")
 *      private List<BARelation> relations;
 *   }
 *
 *   @Table(..)
 *   class BARelation {
 *      @ManyToOne
 *      @JoinColumn(foreign key column)
 *      private Author author;
 *
 *      @ManyToOne
 *      @JoinColumn(foreign key column)
 *      private Book bookField123456;
 *   }
 *
 *   @Table(..)
 *   class Author {
 *       @Column(..)
 *       private String name;
 *       private List<BARelation> relations;
 *   }
 *
 *
 *
 *   EntityManager em = ..;
 *   Book book = em.find(Book.class, id);
 *   book.getRelations();
 *      1. eager loading: em.find(Book.class); => load List<book> + relations (join)
 *      2. lazy loading : em.find(Book.class, id); => only load List<book>
 *          for(BARelation relation: List<book>) {
 *              List<BARelation> reList = book.getRelations()
 *          }
 *
 *
 *   Tomorrow
 *      Spring IOC/AOP
 *      SpringBoot
 */
public class ORMConfig {

    private DataSource getDataSource() {
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setDatabaseName("OrmDemo");
        dataSource.setUser("postgres");
        dataSource.setPassword("password");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/university");
        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.show_sql", "true");
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com/example/java23/week4/orm");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(hibernateProperties);
        em.setPersistenceUnitName("demo-unit");
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }

    public static void main(String[] args) {
        ORMConfig ormConfig = new ORMConfig();
        DataSource dataSource = ormConfig.getDataSource();
        Properties properties = ormConfig.getProperties();
        EntityManagerFactory entityManagerFactory = ormConfig.entityManagerFactory(dataSource, properties);

        EntityManager em = entityManagerFactory.createEntityManager();
        PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();
        //..

//        em.getTransaction().begin();
//        Student s = new Student();
//        s.setName("32th");
//        em.persist(s); //insert
//        em.flush();
//        List<Student> sList = em.createQuery("select s from Student s").getResultList();
//        System.out.println(sList);
//        for(Student s: sList) {
//            List<Teacher_Student> ts = s.getTeacher_students();
//            System.out.println("************************************************");
//            System.out.println(s.getId() + " 's size is : " + ts.size());
//        }
        addToJunctionTable4(em);
    }



    private static void insertToStudent(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Student s = new Student();
        s.setName("Jerry");
        s.setId("17");
        em.merge(s);
        tx.commit();
    }

    private static void getStudentById(EntityManager em) {
        Query query = em.createQuery("select s from Student s left join fetch s.teacher_students ts where s.id = ?1");
        query.setParameter(1, "17");
        Student s = (Student)query.getSingleResult();
        System.out.println(s);
    }

    private static void addToJunctionTable1(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Student s = new Student();
        s.setName("30th stu");
        Teacher t = new Teacher();
        //persist t first to get new id
        em.persist(t);
        t.setName("30th teacher");
        //build connection between t and s
        Teacher_Student ts = new Teacher_Student();
        ts.setStu(s);
        ts.setTeacher(t);
        t.addTeacher_students(ts);
        em.persist(s);
        tx.commit();
    }

    private static void addToJunctionTable2(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createNativeQuery("INSERT INTO TEACHER_STUDENT (S_ID, T_ID) VALUES (?, ?)");
        query.setParameter(1, 4);
        query.setParameter(2, 4);
        query.executeUpdate();
        tx.commit();
    }

    private static void addToJunctionTable3(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Student s = em.find(Student.class, "14");
        Teacher t = em.find(Teacher.class, "9");
        Teacher_Student ts = new Teacher_Student();
        ts.setStu(s);
        ts.setTeacher(t);
        em.persist(ts);
        tx.commit();
    }

    private static void addToJunctionTable4(EntityManager em) {
        em.getTransaction().begin();
        Student s = new Student();
        s.setId("14");
        Teacher t = new Teacher();
        t.setId("12");
        Teacher_Student ts = new Teacher_Student();
        ts.setStu(s);
        ts.setTeacher(t);
        em.persist(ts);
        em.getTransaction().commit();
    }

    private static void notOrphanRemoveBiRelation(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select s from Student s join fetch s.teacher_students ts where s.id = ?1");
        query.setParameter(1, "5");
        Student s = (Student) query.getSingleResult();
        Teacher t = em.find(Teacher.class, "3");
        List<Teacher_Student> teacher_students = new ArrayList<>();
        for(Teacher_Student ts: s.getTeacher_students()) {
            if(ts.getTeacher().getId().equals(t.getId())) {
                teacher_students.add(ts);
                em.remove(ts);
            }
        }
        s.getTeacher_students().removeAll(teacher_students);
        t.getTeacher_students().removeAll(teacher_students);
        tx.commit();
    }

    private static void notOrphanRemoveSingleRelation(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select s from Student s join fetch s.teacher_students ts where s.id = ?1");
        query.setParameter(1, "5");
        Student s = (Student) query.getSingleResult();
        for(Teacher_Student ts: s.getTeacher_students()) {
            em.remove(ts);
        }
        s.setTeacher_students(new ArrayList<>());
        tx.commit();
    }

    private static void orphanRemove(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select s from Student s join s.teacher_students ts where s.id = ?1");
        query.setParameter(1, "14");
        Student s = (Student) query.getSingleResult();
        Iterator<Teacher_Student> itr = s.getTeacher_students().iterator();
        while(itr.hasNext()) {
            Teacher_Student ts = itr.next();
            if(ts.getTeacher().getId().equals("9")) {
                itr.remove();
            }
        }
        tx.commit();
    }


    private static void withoutOrphanRemove(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select s from Student s join fetch s.teacher_students ts where s.id = ?1");
        query.setParameter(1, "14");
        Student s = (Student) query.getSingleResult();
        Iterator<Teacher_Student> itr = s.getTeacher_students().iterator();
        while(itr.hasNext()) {
            Teacher_Student ts = itr.next();
            if(ts.getTeacher().getId().equals("9")) {
                itr.remove();
                em.remove(ts);
            }
        }
        tx.commit();
    }
}
