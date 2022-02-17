package com.wzh.getData;
//82 96 96 230 308 308
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WriteToSQL {
    public static void main(String[] args) {
        List<String> question=new ArrayList<>();
        List<String> a=new ArrayList<>();
        List<String> b=new ArrayList<>();
        List<String> c=new ArrayList<>();
        List<String> d=new ArrayList<>();
        List<Character> answer=new ArrayList<>();

        ReadDate rd=new ReadDate("C:/Users/86133/Desktop/考试系统/src/test.txt");
        ReadAnswer ra=new ReadAnswer("C:/Users/86133/Desktop/考试系统/src/answer.txt");

        question=rd.getQList();
        a=rd.getAList();
        b=rd.getBList();
        c=rd.getCList();
        d= rd.getDList();

        answer=ra.getRAList();

        /*System.out.println(question.size());

        for (int i = 0; i < question.size(); i++) {
            System.out.println(question.get(i));
        }*/



        Connection connection=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/考试系统?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
            Statement statement=connection.createStatement();
            statement.execute("CREATE TABLE test(qno int(11) NOT NULL,question varchar(1024) DEFAULT NULL,A varchar(1024) DEFAULT NULL,B varchar(1024) DEFAULT NULL,C varchar(1024) DEFAULT NULL,D varchar(1024) DEFAULT NULL,rightAnswer char(1) DEFAULT NULL,PRIMARY KEY (qno)) ENGINE=InnoDB DEFAULT CHARSET=utf8");
            for(int i=0;i<a.size();i++){
                String sql="insert into test values(?,?,?,?,?,?,?)";
                PreparedStatement ps=connection.prepareStatement(sql);
                ps.setInt(1,i);
                ps.setString(2, question.get(i));
                ps.setString(3,a.get(i));
                ps.setString(4,b.get(i));
                ps.setString(5,c.get(i));
                ps.setString(6,d.get(i));
                ps.setString(7, String.valueOf(answer.get(i)));

                ps.executeUpdate();
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
}