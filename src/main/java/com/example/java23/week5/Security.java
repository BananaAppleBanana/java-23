package com.example.java23.week5;


/**
 *  *  ASCII
 *
 *  encode vs encryption
 *
 *  encode:
 *  encryption :
 *      Symmetric key  : encryption + decryption
 *      Asymmetric key : private key encryption + public key decryption
 *                       public key encryption + private key decryption
 *
 *   *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  how to secure rest api
 *  1. authentication (identity)
 *      hash(password)
 *  2. authorization (role)
 *      a. session
 *      b. JWT token
 *         encode(header.payload.encrypt(signature))
 *         header: algorithm
 *         payload: data
 *         signature = header + payload
 *  3. https
 *      http + ssl / tls
 *      ssl / tls = key exchange = from asymmetric key to symmetric key
 *      client                                  server
 *               ->       hi          ->
 *             <- certificate  + public key <-
 *             ->   public key[random string] ->
 *             <-   hash[random string] <-
 *                generate symmetric key
 *             ->  symmetric key[data] ->
 *             <-  symmetric key[data] <-
 *  4. CSRF
 *  5. SQL / Log.. injection
 *  6. DDOS
 *      a. firewall (allow rule / deny rule)
 *      b. rate limiter
 *      c. deploy more nodes
 *      d. dynamic page -> static page
 *      e. CDN (aws cloud front(edge location))
 *      ...
 *  7. database / storage security (encrypt / secure at rest)
 *      example s3
 *      a. client encrypts data -> upload to s3
 *      b. s3 server side encryption(s3 default key)
 *      c. client provides key -> s3
 *         s3 server side encryption
 *      d. kms
 *
 *
 *     *     *     *     *     *     *     *     *     *
 *     OAuth2.0
 *     user -> login -> server
 *     user <- jwt   <- server
 *     user -> /student with jwt -> server
 *
 *     *     *     *     *     *     *     *     *     *
 *                                  user
 *                                   |
 *                              edge  cache
 *                                  |
 *                                application
 *                                  |
 *                          load balancer (sticky session)
 *                        /         \           \       \
 *                     node1        node2     node3     node4
 *
 *       *       *       *       *       *       *       *       *       *       *
 *
 *       filter1 -> filter2 -> filter3.. ->  controller
 *       filter1 <- filter2 <- filter3.. <-  controller
 *
 *       authentication filter -> jwt filter -> ... -> controller
 *       authentication filter -> jwt filter -> verify security context(thread local) -> controller -> @PreAuthorize("hasRole['Admin']")
 *                                                  clean up security context         <- controller
 *
 *       1. authentication filter : username password
 *              DaoAuthenticationProvider
 *                  UserDetailsService
 *                      loadUserByUsername()
 *       2. JWT filter: jwt
 *              get jwt from header
 *                  jwt utility class verify jwt token
 *                      a. trust jwt
 *                      b. verify roles / user id with database one more time
 *                      c. load roles from database
 *                  save to security context
 *
 *     *     *     *     *     *     *     *     *     *     *     *     *     *     *
 *     microservice intro
 *
 */