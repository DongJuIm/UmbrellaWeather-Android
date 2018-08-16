package com.umbrellaweather;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by 임동주 on 2017-12-02.
 */
public  class ToDayRainTask extends AsyncTask<String, Void, Document> {
    private Activity context;

    NodeList nodeList;
    Document doc = null;
    static String weaher,pop;
    public static String todayMsg;

    public ToDayRainTask(Activity context) {
        this.context = context;
    }



    @Override
    protected Document doInBackground(String... urls) {
        Log.v("doInBackground","Start Check");
        URL url;
        try {
            url = new URL(urls[0]);
            DocumentBuilderFactory dbf = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder db;

            db = dbf.newDocumentBuilder();
            doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();


        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.v("doInBackground","End Check");
        return doc;

    }


    protected  void onPostExecute(Document doc) {

        if (doc.getElementsByTagName("data") != null){

            nodeList = doc.getElementsByTagName("data");

        }

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);
            Element fstElmnt = (Element) node;

            NodeList dayList = fstElmnt.getElementsByTagName("day").item(0).getChildNodes();

            if (((Node) dayList.item(0)).getNodeValue().equals("0")) {

                NodeList weatherList = fstElmnt.getElementsByTagName("wfKor").item(0).getChildNodes();
                weaher = ((Node) weatherList.item(0)).getNodeValue();

                Log.v("DAY", weaher);

                if (weaher.contains("비")) {

                    NodeList popList = fstElmnt.getElementsByTagName("pop").item(0).getChildNodes();
                    pop = ((Node) popList.item(0)).getNodeValue();

                    todayMsg = "오늘은"+" "+weaher+" "+"가(이) 올 예정 입니다."  + "\n" + "강수확률" +" "+ pop + "%";


                    break;
                } else{
todayMsg = "오늘은 비가 올 예정이 아닙니다.";
                     }

            }
        }



    }



}