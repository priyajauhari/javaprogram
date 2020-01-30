package com.oracle.jaxws.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.xml.txw2.Document;

public class SOAPClient {
	
	String auth, soapUser, soapPass, WSUrl, SoapHeader, SoapFooter;
	
	public SOAPClient() {
		auth = "false";
		soapUser = "user";
		soapPass = "pass";
		
		WSUrl = "http://localhost:8080/JAXWS-Tomcat/PersonService";

		SoapHeader = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.jaxws.oracle.com/\" xmlns:mod=\"http://jaxws.oracle.com/model\">";
	    SoapHeader += "<soapenv:Header>";
		if (Boolean.valueOf(auth)) {
		    SoapHeader += "      <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" soapenv:mustUnderstand=\"0\">";
		    SoapHeader += "         <wsse:UsernameToken xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" wsu:Id=\"UsernameToken-4799946\">";
		    SoapHeader += "            <wsse:Username>"+soapUser+"</wsse:Username>";
		    SoapHeader += "            <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">"+soapPass+"</wsse:Password>";
		    SoapHeader += "         </wsse:UsernameToken>";
		    SoapHeader += "      </wsse:Security>";
		}
		SoapHeader += "</soapenv:Header>";
		SoapHeader += "<soapenv:Body>";

		SoapFooter = "</soapenv:Body>"; 
		SoapFooter +="</soapenv:Envelope>";

	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		HashMap<String, String> personDetails = new SOAPClient().getPerson("40");
		Iterator<Entry<String, String>> iterator= personDetails.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry entry = iterator.next();
			System.out.println(entry.getKey()+" = "+entry.getValue());
		}
	}
	
	public HashMap<String, String> getPerson(String id) throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
	    URL url = new URL(WSUrl);
	    System.out.println("SOAP Service Url :-\n"+url);
	    
	    String str = SoapHeader;
	    str += "<ser:getPerson>";
	    str += "	<getPersonRequest>";
	    str += "	    <mod:id>10</mod:id>"; 
	    str += "	</getPersonRequest>"; 
	    str += "</ser:getPerson>";
	    str += SoapFooter;

	    System.out.println("SOAP request getPerson :-\n"+str);
	    String xml = getOpCodeReturn(url,str);
	    System.out.println("SOAP response getPerson :-\n"+xml);
	    
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    factory.setIgnoringComments(true);
	    factory.setNamespaceAware(false);
	    DocumentBuilder newDocumentBuilder = factory.newDocumentBuilder();
	    org.w3c.dom.Document doc1 = newDocumentBuilder.parse((new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8")))));
	    doc1.getDocumentElement().normalize();

	    NodeList result = doc1.getElementsByTagName("return");
	    HashMap<String, String> personMap = new LinkedHashMap<String,String>();
	    if(result.getLength() > 0) {
	    	Node resultNode = result.item(0);
		    Element ServiceElement = (Element)resultNode;

		    String idP = getValue("id",ServiceElement);
		    String name = getValue("name",ServiceElement);
		    String age = getValue("age",ServiceElement);
		    
		    personMap.put("idP", idP);
		    personMap.put("name", name);
		    personMap.put("age", age);
	    }
	    return personMap;
	}
	
	private String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);

		String value="";
		try {
			value=node.getNodeValue();
			//print ("value = "+value);
		}
		catch (Exception e){
			value="";
		}
		return value;
	}

	public String getOpCodeReturn(URL url, String request) throws IOException {
		java.net.HttpURLConnection uc = (java.net.HttpURLConnection)url.openConnection();
		uc.setRequestMethod("POST");
		uc.setDoOutput(true);
		uc.setRequestProperty("Accept-Encoding","gzip,deflate");
		uc.setRequestProperty("Content-Type","text/xml;charset=UTF-8");
		uc.setRequestProperty("SOAPAction","");
		uc.setRequestProperty("Connection","Keep-Alive");
		uc.setRequestProperty("User-Agent","iSimulator");

		if (Boolean.valueOf(auth))
		{
			String name = "authname";
			String password = "authpass";
			String authString = name + ":" + password;
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			
			uc.setRequestProperty("Authorization","Basic " + authStringEnc);
		}

		PrintWriter pw = new PrintWriter(uc.getOutputStream());
		pw.println(request);
		pw.close();

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		String read = br.readLine();
	        
		while (read != null) {
			sb.append(read);
			read = br.readLine();
		}
		uc.disconnect();
		return sb.toString();
	}
}