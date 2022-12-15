package com.example.java23.week6;

/**
 *  region
 *      availability zone
 *          data center
 *
 *  1. EC2
 *          on-demand
 *          reserved
 *          spot (target price)
 *          dedicate
 *      network interface (ENI)
 *          primary public ip
 *          primary private ip
 *          secondary private ip
 *          ..
 *      auto scaling group (ASG)
 *
 *  2. Route 53 (DNS, region load balancer)
 *  3. API Gateway (throttle limit / rate limiter)
 *  4. Application Load balancer (http / endpoint)
 *       EC2 register on ALB
 *       ALB health check (3 fails among 5 requests)
 *  5. VPC
 *      route table
 *      subnet(CIDR block : ip range)(belongs to only one AZ)
 *          "public subnet" connected to "internet gateway"
 *          private subnet
 *      network access control list (NACL) (stateless)
 *          outbound deny all + inbound allow 140.0.0.0 (outbound cannot send response 140.0.0.0)
 *  6. security group (stateful)
 *          inbound request 140.0.0.0 ok + (outbound response 140.0.0.0 ok)
 *          outbound request 140.0.0.0 ok + (inbound response 140.0.0.0 ok)
 *
 *  7. S3
 *          S3 - standard
 *          S3 - IA (infrequently access)
 *          S3 - IA single zone
 *          features
 *              upload
 *              multipart / parallel uploading
 *              versioning
 *              MFA
 *              server side encryption
 *                  a. S3 key
 *                  b. configure key in KMS(key management service)
 *                  c. client pass key through request
 *              client side encryption
 *                  a. client encrypt data before uploading
 *          /url/bucket/..
 *          folder
 *              folder
 *
 *  8. S3 -> lifecycle -> S3 glacier(for archived data)
 *          expediate(extra money) => few minutes
 *          standard retrieve => 3 ~ 5h
 *          bulk retrieve => 12h
 *  9. RDS
 *          a. oracle
 *          b. mysql
 *          c. postgre
 *          feature
 *          a. multi-AZ (stand by instance in another AZ)
 *          b. read replica
 *
 * 10. Aurora
 *          a. auto scaling
 *          b. multi-AZ / multi region backup
 *          c. larger spaces
 *          b. read replica
 * 11. EFS(file system)
 * 12. DynamoDB
 *          a. DynamoDB stream (CDC)
 *          b. DynamoDB DAX (cache)
 * 13. Elastic Cache
 * 14. SQS / SNS / MQ
 * 15. cloudwatch
 * 16. cloudtrail
 * 17. ECR (docker image repository)
 * 18. ECS
 *          a. service
 *          b. task definition -> task
 *          c. EC2 + agent / Fargate
 *
 * 19. IAM (role)
 *          do not use root account
 *
 *
 *                      client
 *                        | http://domain-name/.
 *                      route 53
 *                  /           \
 *              region1         region2
 *             api gateway      api gateway
 *                  |                   \
 *                ALB                   ALB (security group)      ---> cloudwatch + trigger
 *             /      \                  \
 *           EC2     EC2                ASG[EC2, EC2, EC2, EC2](security group from alb sg)
 *                                          AMI
 *
 *
 *            ----VPC ----internet gateway-----
 *                        |                \
 *                      ALB(subnet)         \----------------------
 *                    /          \                                  \
 *                /user        /employee                             \
 *                EC2             EC2(private ip)   -> request -> NAT instance
 *        private subnet1     private subnet1                      public subnet
 *                                  |                                  \
 *                                 RDS     -----------------------------
 *                            private subnet2
 *
 *
 *
 *
 *      VM  VM  VM
 *      OS  OS  OS
 *       Hardware
 *
 *     docker container1 / 2 / 3
 *      OS/HV ?
 *      Hardware
 *
 *    tomorrow 2:30pm
 *      interview questions
 *
 *
 *      1. React / Angular + JAVA
 *      2. AWS + Java
 *      3. Python + Java
 */