package com.JavaDay5.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class SqlDatabase {
        static boolean isSqlDriver = true;
        // for MS-SQL Server
        // JDBC driver name and database URL
        private static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        private static final String DRIVER_URL = "jdbc:sqlserver://localhost:1433" + ";databaseName=Exam1;";
        private static final String userName = "demo1";
        private static final String passWord = "1";;

        public static void main(String[] args) {
                Connection conn = null;
                Statement stmt = null;
                int opt;

                try {
                        if (isSqlDriver) {
                                // STEP 2: Register JDBC driver
                                Class.forName(DRIVER_CLASS);
                                // STEP 3: Open a connection
                                System.out.println("Connecting to database...");
                                conn = DriverManager.getConnection(DRIVER_URL, userName, passWord);

                                System.out.println("Database is connected");
                        }

                        Scanner scanIn = new Scanner(System.in);
                        System.out.println("Enter one option, from 1 to 5:");
                        opt = scanIn.nextInt();
                        System.out.println("Tao cac lenh truy van SQL ...");
                        stmt = conn.createStatement();
                        String sql;
                        ResultSet rs = null;

                        switch (opt) {
                        case 1:
                                sql = "SELECT CarID, Maker, Model,Color,CarNote FROM Car";
                                rs = stmt.executeQuery(sql);
                                while (rs.next()) {
                                        // Lay du lieu boi su dung ten cot
                                        String R1 = rs.getString("CarID");
                                        String R2 = rs.getString("Maker");
                                        String R3 = rs.getString("Model");
                                        String R4 = rs.getString("Color");
                                        String R5 = rs.getString("CarNote");
                                        // Hien thi cac gia tri
                                        System.out.print("\nCarID :" + R1);
                                        System.out.print("\nMaker :" + R2);
                                        System.out.print("\nModel: " + R3);
                                        System.out.print("\nColor :" + R4);
                                        System.out.print("\nCarNote " + R5);
                                        System.out.print("\n=================");
                                }
                                break;
                        case 2:
                                sql = "select c.Name, co.Amout  " + "from Customer as c " + "join CarOrder as co "
                                                + "on c.CustomerID = co.CustomerID " + "where co.OrderStatus = 1 "
                                                + "order by co.Amout asc";
                                rs = stmt.executeQuery(sql);
                                // Lay du lieu boi su dung ten cot
                                String R1 = rs.getString("Name");
                                String R2 = rs.getString("Amout");
                                while (rs.next()) {

                                        // Hien thi cac gia tri
                                        System.out.print("\nName" + R1);
                                        System.out.print("\nAmout :" + R2);
                                        System.out.print("\n=================");
                                }
                                break;
                        case 3:
                                sql = "SELECT * CountHigestMaker";
                                rs = stmt.executeQuery(sql);
                                String R3 = rs.getString("Name");
                                String R4 = rs.getString("Amout");
                                try {

                                        while (rs.next()) {
                                                System.out.print("\nName" + R3);
                                                System.out.print("\nAmout :" + R4);
                                                System.out.print("\n=================");
                                        }
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }

                                break;
                        case 4:

                                sql = "exec RemoveCancelOrder";
                                rs = stmt.executeQuery(sql);
                                while (rs.next()) {
                                        System.out.println("So luong order bi huy la " + rs.getInt(1));
                                }

                                break;
                        case 5:
                                sql = "exec PreventInvalidOrder";
                                rs = stmt.executeQuery(sql);
                                break;
                        default:
                                System.out.println("Please enter correct option from 1 to 5");

                        }

                        // STEP 6: Clean-up environment
                        rs.close();
                        stmt.close();
                        conn.close();
                } catch (SQLException se) {
                        // Handle errors for JDBC
                        se.printStackTrace();
                } catch (Exception e) {
                        // Handle errors for Class.forName
                        e.printStackTrace();
                } finally {
                        // finally block used to close resources
                        try {
                                if (stmt != null)
                                        stmt.close();
                        } catch (SQLException se2) {
                        } // nothing we can do
                        try {
                                if (conn != null)
                                        conn.close();
                        } catch (SQLException se) {
                                se.printStackTrace();
                        } // end finally try
                } // end try

        }// end main
}
