package com.bianlaoshi.new1;

import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by frank on 2017/11/30.
 */

public class l_xiugaixiangmuThread extends Thread {
    private String url;
    private int pid;
    EditText pname;
    EditText pnum;
    EditText prequire;
    EditText pintroduction;
    EditText pelse;

    public l_xiugaixiangmuThread(String url,int pid,EditText pname,EditText pnum,EditText prequire,EditText pintroduction,EditText pelse)
    {
        this.url=url;
        this.pid=pid;
        this.pname=pname;
        this.pelse=pelse;
        this.pintroduction=pintroduction;
        this.pnum=pnum;
        this.prequire=prequire;
    }
    private void doGet() throws IOException {
        url=url+"?pname="+ URLEncoder.encode(pname.getText().toString(),"utf-8")+"&pelse="+URLEncoder.encode(pelse.getText().toString(),"utf-8")+"&pintroduction="+URLEncoder.encode(pintroduction.getText().toString(),"utf-8")+
                "&pnum="+pnum.getText().toString()+"&prequire="+URLEncoder.encode(prequire.getText().toString(),"utf-8")+"&pid="+this.pid;
        URL httpUrl=new URL(this.url);
        HttpURLConnection conn= (HttpURLConnection) httpUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setReadTimeout(5000);
        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        String str;
        final StringBuffer sb=new StringBuffer();
        while((str=br.readLine())!=null)
        {
            sb.append(str);
        }
    }

    public void run()
    {
        try {
            doGet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
