package com.swp.utils;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC 通用方法
 */
public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        //注册驱动程序
        try {
            //方法1--通过文件阅读器获取文件
//            {
//                Properties pro = new Properties();
//                ClassLoader classLoader = com.swp.utils.JDBCUtils.class.getClassLoader();
//                URL resource = classLoader.getResource("/jdbc.properties");
////                assert resource != null;
//                String path = resource.getPath();
//                pro.load(new FileReader(path));
//                url = pro.getProperty("url");
//                user = pro.getProperty("user");
//                password = pro.getProperty("password");
//                driver = pro.getProperty("driver");
//                Class.forName("driver");
//            }

            /*方法2--将文件作为流直接获取*/
            /**
             * 相对路径：  .  这个点代表当前目录。当前目录本质上是java命令运行的目录
             * java项目：  在ecplise中，当前目录指向项目的根目录。
             * web项目： 当前目录指向%tomcat%/bin目录
             *   1)结论： 在web项目不能使用相对路径
             *      web项目中加载配置文件： ServletContext.getRealPath()  /  getResourceAsStream()
             *      这种方式对于jdbcUtil这个工具而言，放到java项目中找不到ServletContext对象，不通用的！
             *   2)不能使用ServletContext读取文件
             *
             *   3）使用类路径方式读取配置文件
             *
             *   4)cj版本的mysql驱动会有时区问题，需要在url后面添加serverTimezone=GMT%2B8或者serverTimezone=Asia/shanghai
             */

                Properties pro = new Properties();
                pro.load(JDBCUtils.class.getResourceAsStream("/jdbc.properties"));
                url = pro.getProperty("url");
                user = pro.getProperty("user");
                password = pro.getProperty("password");
                driver = pro.getProperty("driver");
                Class.forName(driver);

                System.out.println(url);
                System.out.println(user);
                System.out.println(password);
                System.out.println(driver);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("没发现class文件");
        }
    }

    /**
     * 获取连接
     *
     * @return 连接
     */
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 释放资源
     */
    public static void close(Statement st, Connection conn) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(ResultSet rs, Statement st, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if( st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(JDBCUtils.getConnection());
    }
}
