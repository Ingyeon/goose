package com.ssafy.api.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;


@Service("codeService")
public class CodeServiceImpl implements CodeService {
	
	public void codeCompile() {
		  String clientId = "683c1c7ad02b383e183ce75fb4258278"; //Replace with your client ID
	      String clientSecret = "48d14c2f3257a101345589019219ae6a4b94a59502add15eb4bef43c0544ed83"; //Replace with your client Secret
	        String script = "";
	        String language = "python3";
	        String versionIndex = "0";
	      
        try {
            URL url = new URL("https://api.jdoodle.com/v1/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("charset", "UTF-8");

            String input = "{\"clientId\": \"" + clientId +
                    "\",\"clientSecret\":\"" + clientSecret +
                    "\",\"script\":\"" + script +
//                    "\",\"stdin\":\"" + stdin +
                    "\",\"language\":\"" + language +
                    "\",\"versionIndex\":\"" + versionIndex +
                    "\"} ";

            System.out.println(input+"\n"+input.getBytes());
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IllegalArgumentException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;
            System.out.println("Output from JDoodle .... \n");
            StringBuilder result = new StringBuilder();
            while ((output = bufferedReader.readLine()) != null) {
                result.append(output);
            }
            System.out.println(result);
            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void codeCompileTemp() {
		  String clientId = "683c1c7ad02b383e183ce75fb4258278"; //Replace with your client ID
	      String clientSecret = "48d14c2f3257a101345589019219ae6a4b94a59502add15eb4bef43c0544ed83"; //Replace with your client Secret
	      String script = "";
	      String language = "python3";
	      String versionIndex = "0";
	      
        try {
        	
        	

        	URL url = new URL("https://api.jdoodle.com/v1/execute");
        	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        	connection.setDoOutput(true);
        	connection.setRequestMethod("POST");
        	connection.setRequestProperty("Content-Type", "application/json");
        	connection.setRequestProperty("Accept-Charset", "UTF-8"); 
        	connection.setConnectTimeout(10000);
        	connection.setReadTimeout(10000);
              
        	JSONObject input = new JSONObject();
        	 input.put("clientId", clientId).put("clientSecret", clientSecret).put("script",script).put("language", language).put("versionIndex",versionIndex);
        	OutputStream os = connection.getOutputStream();
        	os.write(input.toString().getBytes("UTF-8"));
        	os.flush();

//            URL url = new URL("https://api.jdoodle.com/v1/execute");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.setRequestMethod("POST");
//            
////            JSONObject input = new JSONObject();
////            input.put("clientId", clientId).put("clientSecret", clientSecret).put("script",script).put("language", language).put("versionIndex",versionIndex);
//            
//            
//            
//            String input = "{\"clientId\": \"" + clientId +
//                    "\",\"clientSecret\":\"" + clientSecret +
//                    "\",\"script\":\"" + script +
//                    "\",\"language\":\"" + language +
//                    "\",\"versionIndex\":\"" + versionIndex +
//                    "\"} ";
//
//            System.out.println("1 : "+input);
//            System.out.println("2 : "+input.toString());
//            byte[] outputBytes = input.getBytes("UTF-8");
//            
//            OutputStream os = connection.getOutputStream();
//            os.write(outputBytes);
//            os.close();
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
//            bw.write(input.toString());
//            bw.flush();
//            bw.close();
//            OutputStream outputStream = connection.getOutputStream();
//            outputStream.write(input);
//            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IllegalArgumentException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;
            System.out.println("Output from JDoodle .... \n");
            StringBuilder result = new StringBuilder();
            while ((output = bufferedReader.readLine()) != null) {
                result.append(output);
            }
            System.out.println(result);
            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void codeCompileAlpaca() {
//		String clientId = "30bab460a38fc3db6e63aefa34335ae1"; //Replace with your client ID
//        String clientSecret = "5390f6559f24a6e97eed02b2b69694e6272337c7c244ad636d9632ee9ca03d48"; //Replace with your client Secret
		  String clientId = "683c1c7ad02b383e183ce75fb4258278"; //Replace with your client ID
	      String clientSecret = "48d14c2f3257a101345589019219ae6a4b94a59502add15eb4bef43c0544ed83"; //Replace with your client Secret
	      String script = "print(10+20)";
	      String language = "python3";
	      String versionIndex = "0";
        try {
//            URL url = new URL("	https://webhook.site/eb6779d7-cb61-4bcd-8317-73d9d50bffc2");
        	URL url = new URL("https://api.jdoodle.com/v1/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"clientId\": \"" + clientId +
                    "\",\"clientSecret\":\"" + clientSecret +
                    "\",\"script\":\"" + script +
                    "\",\"versionIndex\":\"" + versionIndex +
                    "\",\"language\":\"" + language +
                    "\"} ";

            System.out.println("1 : " + input);
            System.out.println("2 : "+ input.getBytes("UTF-8"));
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes("UTF-8"));
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IllegalArgumentException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;
            StringBuilder result = new StringBuilder();
            while ((output = bufferedReader.readLine()) != null) {
                result.append(output);
            }

            connection.disconnect();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());

            System.out.println(jsonObject);

        } catch (IOException | ParseException e) {
//            throw new ServerErrorException(e);
        }
    }

}
