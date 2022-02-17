package com.wzh.getData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadDate {
    private BufferedReader br=null;
    private ArrayList<String> questionList;
    private ArrayList<String> AList;
    private ArrayList<String> BList;
    private ArrayList<String> CList;
    private ArrayList<String> DList;
    private String filePath;

    public ReadDate(String file){
        this.filePath=file;
        AList=new ArrayList<>();
        BList=new ArrayList<>();
        CList=new ArrayList<>();
        DList=new ArrayList<>();
        questionList=new ArrayList<>();
        this.getData();
    }

    private void getData(){
        try {
            br=new BufferedReader(new FileReader(this.filePath));
            String str=null;
            String qstr="";
            while(null!=(str=br.readLine())){
                if(str.length()>0){
                    //System.out.println(str);

                    if(str.charAt(0)!='A' &&str.charAt(0)!='B' &&str.charAt(0)!='C' && str.charAt(0)!='D'){
                        qstr=qstr+str+"\n";
                    }

                    if(str.startsWith("A")){
                        char[] c=new char[10240];
                        for(int i=4;i<qstr.length();i++){
                            c[i]=qstr.charAt(i);
                        }
                        questionList.add(new String(c,4,qstr.length()));
                        qstr="";
                        AList.add(str);
                    }

                    if(str.startsWith("B")){
                        BList.add(str);
                    }

                    if(str.startsWith("C")){
                        CList.add(str);
                    }

                    if(str.startsWith("D")){
                        DList.add(str);
                    }
                }
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

    }

    public ArrayList getAList(){
        return AList;
    }

    public ArrayList getBList(){
        return BList;
    }

    public ArrayList getCList(){
        return CList;
    }

    public ArrayList getDList(){
        return DList;
    }

    public ArrayList<String> getQList(){
        return questionList;
    }
}
