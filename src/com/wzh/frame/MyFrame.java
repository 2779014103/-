package com.wzh.frame;

import javax.swing.*;

import com.wzh.getData.GetQuestionStr;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyFrame extends JFrame implements ActionListener,Runnable{
    int num=20;

    Font font=new Font("微软雅黑",Font.BOLD,20);

    boolean flag=true;

    String[] answer=new String[20];
    
    int index;
    GetQuestionStr getQuestionStr;
    ArrayList<String> Question;
    ArrayList<String> a;
    ArrayList<String> b;
    ArrayList<String> c;
    ArrayList<String> d;
    ArrayList<String> ra;
    
    JTextArea question;
    JRadioButton a1;
    JRadioButton b1;
    JRadioButton c1;
    JRadioButton d1;

    ButtonGroup bg;

    JLabel label;
    int m=10;
    int s=0;
    
    String error=new String();


    public MyFrame(){
        index=0;
        getQuestionStr=new GetQuestionStr();
        Question= (ArrayList<String>) getQuestionStr.getQ();
        a= (ArrayList<String>) getQuestionStr.getA();
        b= (ArrayList<String>) getQuestionStr.getB();
        c= (ArrayList<String>) getQuestionStr.getC();
        d= (ArrayList<String>) getQuestionStr.getD();
        ra= (ArrayList<String>) getQuestionStr.getRA();



        this.setTitle("JAVA考试系统");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setLayout(null);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocation(0,0);
        this.setResizable(false);
       // this.getContentPane().setBackground(Color.pink);

        this.setFrame();
        this.setVisible(true);


    }

    private void setFrame(){

        question=new JTextArea();
        question.append(index+1+this.Question.get(index));

        //question.setBackground(Color.pink);
        question.setEditable(false);
        question.setFont(font);
        question.setBounds(100,50,1400,600);

        label=new JLabel("剩余时间: "+m+":"+s);
        label.setFont(font);
        label.setBounds(1600,50,300,20);


        JPanel radioPanel=new JPanel();
        radioPanel.setLayout(new GridLayout(4,1));
        a1=new JRadioButton(a.get(index));
        b1=new JRadioButton(b.get(index));
        c1=new JRadioButton(c.get(index));
        d1=new JRadioButton(d.get(index));
        bg=new ButtonGroup();

        a1.setFont(font);
        b1.setFont(font);
        c1.setFont(font);
        d1.setFont(font);

        bg.add(a1);
        bg.add(b1);
        bg.add(c1);
        bg.add(d1);

        bg.clearSelection();

        a1.addActionListener(this);
        b1.addActionListener(this);
        c1.addActionListener(this);
        d1.addActionListener(this);

        radioPanel.add(a1);
        radioPanel.add(b1);
        radioPanel.add(c1);
        radioPanel.add(d1);
        radioPanel.setBounds(100,650,1000,300);



        JPanel jPanel=new JPanel();
        jPanel.setLayout(new GridLayout(1,3));
        JButton before=new JButton("上一题");
        JButton tj=new JButton("提交");
        JButton after=new JButton("下一题");

        before.addActionListener(this);
        tj.addActionListener(this);
        after.addActionListener(this);

        jPanel.add(before);
        jPanel.add(tj);
        jPanel.add(after);
        jPanel.setBounds(725,950,300,50);
        this.add(jPanel);
        this.add(question);
        this.add(radioPanel);
        this.add(label);

    }

    private int getScore(){
        int score=0;
        for(int i=0;i<num;i++){
            if(ra.get(i).equals(answer[i])){
                score+=5;
            }
        }
        return score;
    }
    
    private String getErrorStr(){
    	int index=0;
    	for(int i=0;i<ra.size();i++){
    		if(!ra.get(i).equals(answer[i])){
    			index++;
    			error=error+index+Question.get(i)+"\n"+a.get(i)+"\n"+b.get(i)+"\n"+c.get(i)+"\n"+d.get(i)+"\n"+"你的答案为"+answer[i]+"\n"+"正确答案"+ra.get(i)+"\n\n\n";
    		}
    	}
    	return error;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.flag){
            if(e.getActionCommand().equals("下一题")){
                if(this.index<num-1)
                    this.index++;
                question.setText(index+1+this.Question.get(index));
                a1.setText(a.get(index));
                b1.setText(b.get(index));
                c1.setText(c.get(index));
                d1.setText(d.get(index));

                bg.clearSelection();
            }

            if(e.getActionCommand().equals("上一题")){

                if(this.index>0)
                    this.index--;
                question.setText(index+1+this.Question.get(index));

                a1.setText(a.get(index));
                b1.setText(b.get(index));
                c1.setText(c.get(index));
                d1.setText(d.get(index));

                bg.clearSelection();
            }

            if(e.getActionCommand().charAt(0)=='A'){
                answer[index]="A";
            }

            if(e.getActionCommand().charAt(0)=='B'){
                answer[index]="B";
            }

            if(e.getActionCommand().charAt(0)=='C'){
                answer[index]="C";
            }

            if(e.getActionCommand().charAt(0)=='D'){
                answer[index]="D";
            }

            if(e.getActionCommand().equals("提交")){
            	JDialog jDialog=new JDialog();
            	jDialog.setTitle("成绩单");
            	jDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
            	jDialog.setBounds(200,100,1500,800);
            	jDialog.setResizable(false);
            	
            	jDialog.setLayout(null);
            	JTextArea jArea=new JTextArea("你的成绩"+this.getScore()+"分");
            	jArea.append("\n\n\n==============\n\n\n错题解析\n\n");
            	jArea.append(getErrorStr());
            	jArea.setFont(font);
            	
            	JScrollPane scrollPane=new JScrollPane(jArea);
            	scrollPane.setBounds(0,0,1450,750);
            	
            	scrollPane.setVisible(true);
            	jArea.setEditable(false);
            	
            	jDialog.add(scrollPane);
            	
            	jDialog.setVisible(true);
                //question.setText("你的成绩"+this.getScore()+"分");
                this.flag=false;
            }
        }
    }

    long start=System.currentTimeMillis();
    @Override
    public void run() {
        while (true && flag){
            long end=System.currentTimeMillis();
            if(end-start>=1000){

                if(s==0){
                    m=m-1;
                    s=59;
                }

                this.s=this.s-1;
                label.setText("剩余时间: "+m+":"+s);
                start=end;

            }

            if(m==0 && s==0){
                //question.setText("你的成绩"+this.getScore()+"分");
                this.flag=false;
            }
        }
        label.setText("");
    }

    public static void main(String[] args) {
    	MyFrame frame=new MyFrame();
        new Thread(frame).start();
        
       
    }
}
