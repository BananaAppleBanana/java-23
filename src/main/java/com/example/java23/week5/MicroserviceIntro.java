package com.example.java23.week5;

/**
 *
 *  master branch   --------
 *  release branch      ----------
 *  development branch  -------------------------------
 *                                    \             /
 *                                    feature branch
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 * Monolithic
 *                               Load Balancer
 *                      /          /           \         \
 *               node1         node2         node3       node4
 *                      \       \           /           /
 *                               Redis
 *
 *  scale up
 *      1. horizontal scaling (add more nodes)
 *      2. vertical scaling (add hardware)
 *
 *  pros
 *      1.
 *  cons
 *      1. deployment
 *      2. development
 *      3. cannot use message queue
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 * Microservice
 *
 *                  serviceA(node1, 2, 3)
 *                  /                   \
 *             serviceB(node1, 2)       serviceC(node1, node2, node3)
 *                                       \
 *                                       serviceD(node1, 2) --- message queue  -----  serviceE(node1,2)
 *
 * pros
 *      1. services with diff implementations (coding languages)
 *      2. development
 *      3. deployment
 *      4. scalability
 * need to consider
 *      1. bad api performance
 *      2. service boundary
 *      3. communication between services
 *              webservice(soap / rest)
 *              message queue
 *      4. service A -> rest request private network -> service C
 *                    http://application-C/..
 *                 \                                /
 *                      discovery service
 *                         key    :  value pair
 *                application A   :  ip4 , ip5, ip6
 *                application C   :  ip1 , ip2, ip3
 *      5. centralize configuration service
 *      6. centralize security service
 *      7. api gateway
 *              rate limiter
 *              count              12
 *                      t1  t2  t3 t4 t5 t6  t7
 *              generate global unique id (co-relation id)
 *                  1. uuid
 *                  2. database sequence / primary key
 *                  3. snowflake
 *              log api performance
 *      8. database sharding / scalability + cache
 *      9. message queue + global transaction
 *      10. CI/CD
 *      11. monitors
 *      12. log server
 *
 */