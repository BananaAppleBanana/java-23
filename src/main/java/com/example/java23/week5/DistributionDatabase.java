package com.example.java23.week5;

/**
 *  single leader cluster
 *
 *      client
 *        |
 *      backend
 *       |
 *      primary DB - read db1 / read db2 / read db3..
 *      |
 *     stand by DB
 *
 *
 *  availability: stand by db
 *  scalability : vertical scaling vs horizontal scaling
 *
 *  write ack: primary db + 0 ~ n read database
 *  read     : from primary / replica
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   multi leader cluster
 *
 *   primary DB1      <->     primary DB2       <->      primary DB3
 *    /     \                /         \                 /       \
 *  read1   read2
 *
 *      Last Write Win :
 *      clock vectoring:
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   leaderless (all leaders)
 *           node1
 *   node2              node3
 *
 *   node4              node5
 *          node6
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   CAP
 *      consistency
 *      availability
 *      partition tolerance
 *   BASE
 *      basic availability
 *      soft stage
 *      eventually consistency
 *    *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   MongoDB (CP)
 *                      mongos(gateway) -->  mongos(config server / metadata)
 *                    /       \             \
 *           sharding1      sharding2       sharding3...
 *         primary node         ..
 *         secondary node1      ..
 *         secondary node2      ..
 *
 *        id 1 ~ 1000     1001 ~ 2000      2001 ~ 3000
 *        hash(id)
 *
 *
 *        global secondary index
 *        name start with 'Abc'
 *        salary > 1000
 *        age > 50
 *     *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *   Cassandra(AP + eventually consistency)
 *   LSM tree
 *   Node
 *  write        ->   ->     mem table(sorted)    ->  SST(sorted string table)
 *               |
 *           commit log
 *
 *  read    -> mem table -> bloom(ing) filter
 *
 *
 *                    N1(0)
 *
 *           N2                 N3(10000)
 *
 *           N4                 N5(50k)
 *
 *                   N6(100k)
 *
 *     Replica = 3
 *     Read Consistency level  = 1
 *     Write Consistency level = 1
 *
 *     R + W > Replica
 *     2 + 2 > 3
 *     R : N1 + N3
 *     W : waiting(N1) ok(N3 N5)
 *     overlapped node: N3
 *
 *      Read repair
 *     *     *     *     *     *     *     *     *     *     *     *
 *     Redis(fixed hash slot range)
 *
 *     Leader1(0 ~ 5000)          Leader2(5001 ~ .. )            Leader3()
 *     /        \
 *   Follower1  2               ...
 *
 *    1. AOF append of file / command log -> query appended to file
 *    2. DB snapshot
 *    3. AOF + DB
 *     *     *     *     *     *     *     *     *     *     *     *
 *   1. cache aside pattern
 *
 *              client   -   db
 *                |
 *              cache
 *
 *     read
 *     1. read from cache
 *          find -> return
 *          cannot find -> read from db, save to cache(TTL / timeout)
 *     write
 *     1. remove cache
 *     2. save to db
 *
 *   2. read through
 *      client <- cache <- db
 *   3. write through
 *      client -> cache -> db
 *   4. write back (?)
 *      client -> cache
 *      client -> cache
 *      client -> cache
 *                cache -> db
 */