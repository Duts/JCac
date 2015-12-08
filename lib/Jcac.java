/*
The MIT License (MIT)

Copyright (c) 2015 Robert Halandut

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/


package lib;
import java.io.*;
import java.net.*;
import java.util.*;

public class Jcac  
{
	private String APIKEY 		= "";
	private String LOGIN_EMAIL      = "";
	private String version		= "v1";
	static public final String PATH = "https://panel.cloudatcost.com/api/";

	/**
		Configure the API
		@param apikey : APIKEY, loginEmail: loginEmail
	*/
	public void Configure ( String apikey, String loginEmail)  {
		this.APIKEY = apikey;
		this.LOGIN_EMAIL = loginEmail;
		return;
	}

	private String GETrequest( String type )  {
		String response = "";

		try {
	    	URL catc = new URL(PATH + version + "/" + type + ".php?key=" + this.APIKEY + "&login=" + this.LOGIN_EMAIL);
	        URLConnection open_catc = catc.openConnection();
	        BufferedReader process = new BufferedReader(new InputStreamReader(open_catc.getInputStream()));

	        String req_response;

	        while ((req_response = process.readLine()) != null) 
	            response = req_response;
	        process.close();

	        return response;
        }
		catch (Exception e) {
			response = "[Error]: Connection Error ( " + e + " )";
			return response;
		}
	}

	private String POSTrequest( String type, int sid ) throws Exception {
		String response = "";
		try {
	        URL url = new URL(PATH + version + "/" + type + ".php");
	        Map<String,Object> parm = new LinkedHashMap<>();
	        parm.put("key", this.APIKEY);
	        parm.put("login", this.LOGIN_EMAIL);
	        parm.put("sid", sid);

	        StringBuilder Dpost = new StringBuilder();
	        for (Map.Entry<String,Object> param : parm.entrySet()) {
	            if (Dpost.length() != 0) Dpost.append('&');
	            Dpost.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            Dpost.append('=');
	            Dpost.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
	        byte[] DpostBytes = Dpost.toString().getBytes("UTF-8");

	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf(DpostBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(DpostBytes);
	        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        for ( int c = in.read(); c != -1; c = in.read() ){
	      		response += (char)c;
	       	}
	       	return response;
       }
       catch (Exception e) {
			response = "[Error]: Connection Error ( " + e + " )";
			return response;
       }
	}

	private String POSTrequest( String type, int sid, String operation) throws Exception {
		String response = "";
		try {
	        URL url = new URL(PATH + version + "/" + type + ".php");
	        Map<String,Object> parm = new LinkedHashMap<>();
	        parm.put("key", this.APIKEY);
	        parm.put("login", this.LOGIN_EMAIL);
	        parm.put("sid", sid);
	        switch (operation) {
	        	case "poweron":
	        	case "poweroff":
	        	case "reset":
	        		parm.put("action", operation);
	        		break;
	        	case "normal":
	        	case "safe":
	        		parm.put("mode", operation);
	        		break;
	        }

	        StringBuilder Dpost = new StringBuilder();
	        for (Map.Entry<String,Object> param : parm.entrySet()) {
	            if (Dpost.length() != 0) Dpost.append('&');
	            Dpost.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            Dpost.append('=');
	            Dpost.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
	        byte[] DpostBytes = Dpost.toString().getBytes("UTF-8");

	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf(DpostBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(DpostBytes);
	        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        for ( int c = in.read(); c != -1; c = in.read() ){
	      		response += (char)c;
	       	}
	       	return response;
       }
       catch (Exception e) {
       		response = "[Error]: Connection Error ( " + e + " )";
			return response;
       }
	}

	/**
		Request URL for console access
		@param sid : ServerID
	*/
	public String console(int sid) throws Exception{
		return POSTrequest("console", sid);
	}

	/**
		Activate server power operations
		@param {sid : ServerID , operation: [poweron - poweroff - reset]
	*/
	public String powerop(int sid, String operation) throws Exception{
		return POSTrequest("powerop", sid, operation);
	}

	/**
		Set the run mode of the server to either 'normal' or 'safe'. Safe automatically turns off the server after 7 days of idle usage. Normal keeps it on indefinitely.
		@param {sid : ServerID, operation: [normal - safe]
	*/
	public String runmode(int sid, String operation) throws Exception{
		return POSTrequest("runmode", sid, operation);
	}

	/**
		List all servers on the account
	*/
	public String listsServers()  {
		return GETrequest("listservers");
	}

	/**
		List all templates available
	*/
	public String listtemplates()  {
		return GETrequest("listtemplates");
	}

	/**
		List all tasks in operation
	*/
	public String listtasks()  {
		return GETrequest("listtasks");
	}

}
