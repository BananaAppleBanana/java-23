package com.example.java23.week4;

import java.sql.*;


/**
 *  TODO JDBC
 *  1. connection pool
 *  2. builder => dynamic query
 *  3. sql injection
 *  4. orm / object mapping
 *  5. migrate database
 *      a. database connection(username, password, db name, url, driver)
 *      b. sql query
 *                  /===>  oracle query
 *    my query
 *                  \===>  postgre query
 *  6. cache
 *
 *  orm
 *  1. lazy loading (N + 1 problem) , eager loading
 *  2. why orm
 *  3. criteria query
 *  4. mapping / annotations
 *
 *  1. create many to many tables
 *  2. configure foreign key
 *  3. auto increment primary key (sequence)
 */


public class JdbcExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:8080/database_name";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //STEP 2: Register JDBC driver -> DriverManager
            Class.forName(JDBC_DRIVER); //static block

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(false);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            String username = "";
            String password = "";
            String sql1 = "SELECT .... first, last, age FROM Employees WHERE username = "
                    + username + " AND password = xx + " + password;
            String sql2 = "SELECT ... FROM ... WHERE val1 = not null AND val2 = col2 OR val5 = col5.....";
                /*
                 */
            stmt = conn.prepareStatement(sql1);
            ResultSet rs = stmt.executeQuery();

            //STEP 5: Extract data from result set
            //30 columns -> new instance , Student / List<Student> / Set<Student>
            //List<Student> studentList = executeQuery(query);
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }

            conn.commit();
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //log => file
        } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }//end main
}