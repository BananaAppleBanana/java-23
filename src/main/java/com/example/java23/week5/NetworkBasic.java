package com.example.java23.week5;

/**
 *
 *     device(name = ?)
 *     | network card mac address exchange private ip
 *     computer -> private ip + port -> NAT(public ip + port) -> ip packet[101010101..] -> public ip + port ->  private ip + port
 *            connection = [source ip + port,  destination ip + port]
 *
 *     what happened when you click url / website
 *     1. browser open random port
 *     2. DNS   www.amazon.com -> ip
 *        build connection = [source ip + port,  destination ip + port]
 *     3. tcp
 *         client                               server
 *         seq 0
 *                  -> build connection
 *                  <- ack = 1
 *                  ->
 *
 *
 *                  -> data len = 50, seq
 *                  <- ack
 *
 *     *     *     *     *     *     *     *     *     *     *     *     *     *
 *     OSI
 *     Application  : http
 *     Persistence  : ssl/tls
 *     Session      : socket
 *     Transport    : tcp / udp
 *     Network      : ip
 *     Data link    : ethernet / mac
 *     Physical     : cable
 *
 *      [ip header][tcp header][http header][data]
 *      *      *      *      *      *      *      *      *      *      *
 *     internet  ->  destination ip + port
 *          1. socket build connection
 *          2. assign connection to Thread1 / 2/ 3/ 4..
 *              Thread1 -> get, /student -> StudentServlet -> doGet() -> return data
 *
 *
 *    internet -> tomcat
 *                  -> connection
 *                  -> thread pool get one thread
 *                  -> dispatcher servlet(/*) -> handler mapping -> controller
 *                          |
 *                  http message converter(@ResponseBody)
 *                         |
 *                      jackson
 *                        |
 *                      json
 *
 *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *    Rest api (architecture)
 *      1. http
 *      2. http method
 *         get -> retrieve
 *         post -> create
 *         put -> update  (entire resource)  idempotent
 *         patch -> update (partial update)  idempotent
 *         delete -> remove
 *      3. /noun
 *         get all -> /students?x=v
 *         get student by id -> /students/{id}
 *         post -> /students
 *         put -> /students/{id}
 *         delete -> /students/{id}
 *
 *          path variable  vs  request param
 *         /students/{id}      /students?nameStartWith=T
 *
 *      4. stateless
 *      5. status code
 *          200: OK,  201: Created,  204: OK no content
 *          300: redirection
 *          400: bad request, 401 / 403, 404: resource not found
 *          500: internal server error
 *
 *      6. response body
 *          post: return id
 *    *     *     *     *     *     *     *     *     *     *     *
 *
 *
 */