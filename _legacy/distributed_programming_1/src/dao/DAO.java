package dao;

import java.sql.*;

public class DAO {

    private static final String URL = "jdbc:mysql://localhost:3306/insurance_system";
    private static final String ID = "root";
    private static final String PW = "jin951753";
    protected Connection connection;
    protected String databaseName;
    protected String tableName;
    protected ResultSet rs;

    public DAO(String daoName) {
        connection = getConnection();
        if (connection != null) System.out.println(daoName + "가 데이터베이스와 연결되었습니다.");
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, ID, PW);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("데이트베이스 연결에 실패했습니다.");
            return null;
        }
    }

    protected boolean executeQuery(String query) {
        try {
            System.out.println("쿼리를 요청합니다 : " + query);
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs == null) return false;
            return true;
        } catch (SQLException e) {
            System.out.println("쿼리 수행에 실패했습니다 : " + query);
            return false;
        }
    }

    protected boolean executeUpdate(String query) {
        try {
            System.out.println("쿼리를 요청합니다 : " + query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.out.println("쿼리 수행에 실패했습니다 : " + query);
            return false;
        }
    }

    protected String getString(String columnLabel) {
        try {
            System.out.println("다음 칼럼으로부터 값을 가져옵니다 : " + columnLabel);
            return rs.getString(columnLabel);
        } catch (SQLException e) {
            System.out.println("다음 칼럼으로부터 값을 가져오지 못했습니다 : " + columnLabel);
            e.printStackTrace();
            return null;
        }
    }

    protected int getInt(String columnLabel) {
        try {
            System.out.println("다음 칼럼으로부터 값을 가져옵니다 : " + columnLabel);
            return rs.getInt(columnLabel);
        } catch (SQLException e) {
            System.out.println("다음 칼럼으로부터 값을 가져오지 못했습니다 : " + columnLabel);
            e.printStackTrace();
            return -1;
        }
    }

    protected float getFloat(String columnLabel) {
        try {
            System.out.println("다음 칼럼으로부터 값을 가져옵니다 : " + columnLabel);
            return rs.getFloat(columnLabel);
        } catch (SQLException e) {
            System.out.println("다음 칼럼으로부터 값을 가져오지 못했습니다 : " + columnLabel);
            e.printStackTrace();
            return -1;
        }
    }

    protected boolean next() {
        try {
            rs.next();
            return !rs.isAfterLast();
        } catch (SQLException e) {
            System.out.println("엔트리 조회에 실패했습니다.");
            return false;
        }
    }

}
