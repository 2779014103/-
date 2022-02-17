package com.wzh.getData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetQuestionStr {
    int num=20;


    private ArrayList<Integer> qno;
    private ArrayList<String> qlist;
    private ArrayList<String> alist;
    private ArrayList<String> blist;
    private ArrayList<String> clist;
    private ArrayList<String> dlist;
    private ArrayList<String> ralist;


    public GetQuestionStr() {
        qlist = new ArrayList<>();
        alist = new ArrayList<>();
        blist = new ArrayList<>();
        clist = new ArrayList<>();
        dlist = new ArrayList<>();
        ralist = new ArrayList<>();
        qno = new ArrayList<>();
        getData();
    }

    private void getQno() {
        Connection connection = null;
        Statement statement = null;
        try {
            ArrayList<Integer> allQno = new ArrayList();
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/考试系统?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select qno from test");
            while (rs.next()) {
                allQno.add(rs.getInt(1));
            }

            int i = 0;
            while (true) {
                int p = (int) (Math.random() * allQno.size());
                if (!qno.contains(p)) {
                    i++;
                    qno.add(p);
                }
                if (i == num) {
                    break;
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

    private void getData() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/考试系统?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
            getQno();
            for (int i = 0; i < num; i++) {
                PreparedStatement ps = connection.prepareStatement("select question,A,B,C,D,rightAnswer from test where qno=?");
                ps.setInt(1, qno.get(i));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    qlist.add(rs.getString(1));
                    alist.add(rs.getString(2));
                    blist.add(rs.getString(3));
                    clist.add(rs.getString(4));
                    dlist.add(rs.getString(5));
                    ralist.add(rs.getString(6));
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public List getQ(){
        return qlist;
    }

    public List getA(){
        return alist;
    }
    public List getB(){
        return blist;
    }
    public List getC(){
        return clist;
    }
    public List getD(){
        return dlist;
    }
    public List getRA(){
        return ralist;
    }
}
