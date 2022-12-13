package com.example.java23.week6;

/**
 * Waterfall
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 * Agile
 * Scrum (1 sprint = 2 ~ 3 weeks) (tool: Jira)
 *      1. Daily stand up meeting (15 ~ 30min)
 *      2. Sprint planning (TODO for this Sprint)
 *          a. get highest priority stories / tickets from production backlog(Application TODO List)
 *          b. evaluate points of stories / tickets
 *                1 point = 0.5h / 1h / 2h
 *                points = difficulty level (fibonacci, 1 2 3 5 8)
 *          c. assign tasks / stories
 *      3. Retrospective meeting  / Sprint review meeting
 *      4. demo review meeting every few sprints
 * team size (3 - 8)
 *      1 x manager
 *      0 / 1 scrum master
 *      0 / 1 BA
 *      1 x team leader
 *      n x backend developers
 *      m x frontend developers
 *      0 / 1 / 2 x QA
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Daily Work
 *      1. daily stand up / sprint planning meeting
 *      2. meetings with BA / Manager / QA => clarify requirement (QA / BA / Manager)
 *      3.   stories => TDD
 *          a. design rest api + write doc
 *          b. check out new feature branch
 *          c. OOD / abstract class + interface
 *          d. write test cases
 *          e. impl TODO
 *          f. run test cases + fix issues
 *          g. pull request code review
 *          h. merge back to development branch
 *          i. trigger jenkins -> deploy to development env
 *      4.  tickets => bug report
 *          a. locate the bug => reproduce
 *              code level bug
 *              network issues
 *              configuration issues
 *              database dirty data
 *              ..
 *          b. search log in log server (Splunk)
 *              co-relation id / request id
 *          c. checkout hotfix branch from production branch / release branch
 *          d. fix issue + rewrite test cases
 *          e. pull request code review / pr + merge to prod
 *          f. pull request code review / pr + merge to release
 *     *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *     CI/CD => jenkins pipeline
 *      1. git branch changed / merged
 *      2. trigger jenkins pipeline
 *      build   ->   test  ->    package    ->   deploy env
 *                     |
 *              generate report  -> sonarqube(server)
 *              1. code coverage 80% / 90%
 *              2. security check
 *
 *
 *      devops -> jenkins pipeline / infrastructure
 *      customer support team ->
 *    *      *      *      *      *      *      *      *      *      *
 *     Test
 *     1. unit test + mockito
 *          service - repository
 *          class EmpService {
 *              public xx get() {
 *                  xx
 *                  xx = repository.get();
 *                  xx
 *              }
 *          }
 *     2. integration test (postman / mockmvc library)
 *     3. function test
 *     4. automation test (selenium)
 *     5. performance(pressure) test (jmeter)
 *     6. smoke / regression test
 *
 */