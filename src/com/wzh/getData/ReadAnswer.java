package com.wzh.getData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadAnswer {
    private ArrayList<Character> rightAnswerList;
    private BufferedReader br=null;
    private String path;


    public ReadAnswer(String path){
        rightAnswerList=new ArrayList<>();
        this.path=path;
    }

    public List getRAList(){
        try {
            br=new BufferedReader(new FileReader(this.path));
            String str;
            while(null!=(str=br.readLine())){
                if(str.length()>0)
                    rightAnswerList.add(str.charAt(str.length()-1));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return rightAnswerList;
    }
}
