package com.kapild.bart;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class URLHelper {

		public static Element getRequest(String urlString) throws IOException, ParserConfigurationException, SAXException{

			URL url;
			url = new URL(urlString);
	
			URLConnection urlCon = url.openConnection();
			HttpURLConnection httpCon = (HttpURLConnection)urlCon;
			
			int responseCode = httpCon.getResponseCode();
	
			if(responseCode== HttpURLConnection.HTTP_OK){
				InputStream in = httpCon.getInputStream();
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document dom = db.parse(in);
				Element docEle = dom.getDocumentElement();
				return docEle;
			}else{
				return null;
			}
	}
	
}
