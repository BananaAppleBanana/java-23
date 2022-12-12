package com.example.java23.week6;

/**
 *
 *    client
 *      |
 *    service1 -> message queue(memory + disk) -> service2  ->  service3
 *    producer                                    consumer
 *
 *     *    *    *    *    *    *    *    *    *    *
 *     queue model
 *     producer1                                    consumer1[x3]
 *     producer2        queue1[x1][x2][x3]          consumer2[x1]
 *     producer3                                    consumer3[x2]
 *
 *     topic model
 *     producer1                                    consumer1[x3]
 *     producer2        queue1[x1][x2][x3]          consumer2[x3]
 *     producer3                                    consumer3[x3]
 *
 *
 *     active / rabbit mq
 *     *    *    *    *    *    *    *    *    *    *
 *     AWS
 *     SQS (queue model) + visibility timeout + dead letter queue
 *     producer1                                    consumer1[x3]
 *     producer2        queue1[x1][x2][x3]          consumer2[x1]
 *     producer3                                    consumer3[x2]
 *
 *
 *     SNS (topic model)
 *                      -> SQS
 *     producer ->  SNS -> SQS
 *                      -> text
 *                      -> email
 *
 *     *    *    *    *    *    *    *    *    *    *
 *    kafka
 *
 *                      Topic1                      consumer group1
 *     producer1        partition1[x1][x2]          consumer1(t1p2)
 *     producer2        partition2[x3]              consumer2(t1p1)
 *     producer3                                    consumer3
 *
 *                                                  consumer group2
 *                                                  consumer1(t1p2 + t1p1)
 *  topic partition m to 1 consumer in same consumer group
 *     *    *    *    *    *    *    *    *    *    *
 *    problems
 *      1. duplicate messages
 *          a. cache processed message id
 *          b. idempotent service
 *      2. database + message transaction
 *          service -> message queue ->
 *           |
 *          database
 *
 *          1. insert to db(db tx)
 *          service shutdown
 *          2. push to message queue(message queue tx)
 *
 *          1. push to message queue(message queue tx)
 *          service shutdown
 *          2. insert to db(db tx)
 *
 *          CDC(change data capture) + outbox
 *
 *          service
 *           |
 *          database  ->  monitor / cdc service -> message queue ->
 *          step1:
 *              begin
 *              insert data into table
 *              insert message into outbox table
 *              commit
 *          step2:
 *              monitor / cdc
 *       *       *       *       *       *       *
 *      2PC commit
 *              service
 *           /             \
 *       db1                db2
 *
 *      1. prepare
 *      2. commit
 *       *       *       *       *       *       *
 *      SAGA pattern
 *
 *      client -> service -> queue -> service -> queue -> service
 *                  |                   |                   |
 *                 db                   db                  db
 *
 *                                          <-  queue
 *                                              compensation tx
 *
 *       *       *       *       *       *       *
 *       git
 *       master branch  (production)    -----------------v1.0.1
 *                                                      /
 *       release branch   ----   v1.0.0     ----    v1.0.1  ------  v2.0.0
 *
 *       development branch ------------------------------------------------------
 *                                      \       /
 *       feature branch                 ---------
 *
 *      question:
 *      what will you do after you get a story / task
 *      1. clarify requirement (QA / BA / Manager)
 *      2. design rest api + write doc
 *         check out new feature branch
 *      3. OOD / abstract class + interface
 *      4. write test cases
 *      5. impl TODO
 *      6. run test cases + fix issues
 *      7. pull request code review
 *      8. merge back to development branch
 *
 *
 *      TDD
 *
 *      tomorrow
 *      2pm cdt - 4pm cdt
 *      daily work + test + jenkins deployment + production support
 *
 *
 */
