package com.ssafy.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.article.ArticleRegisterPostReq;
import com.ssafy.api.service.CodeService;
import com.ssafy.api.service.CodeServiceImpl;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 컴파일러 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "컴파일러 API", tags = {"Compiler"})
@RestController
@RequestMapping("/api/v1/compiler")
public class CompilerController {
	
	@Autowired
	UserService userService;
	@Autowired
	CodeService codeService;
	
	@PostMapping()
	@ApiOperation(value = "코드 컴파일", notes = "<strong>코드를</strong>를 통해 실행한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류") 
    })

	public ResponseEntity<? extends BaseResponseBody> registerArticles() {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
//		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();

		// 컴파일 start
		
//		  String clientId = "683c1c7ad02b383e183ce75fb4258278"; //Replace with your client ID
//	      String clientSecret = "97d8fd4d984d31f322f95d330d5de1256a8b27f7155d09ef7bcae0bf5b20e293"; //Replace with your client Secret
//	        String script = "public class MyClass {\r\n" + 
//	        		"    public static void main(String args[]) {\r\n" + 
//	        		"      int x=10;\r\n" + 
//	        		"      int y=25;\r\n" + 
//	        		"      int z=x+y;\r\n" + 
//	        		"\r\n" + 
//	        		"      System.out.println(\"Sum of x+y = \" + z);\r\n" + 
//	        		"    }\r\n" + 
//	        		"}";
//	        String language = "java";
//	        String versionIndex = "0";
//
//	        try {
//	            URL url = new URL("https://api.jdoodle.com/v1/execute");
//	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//	            connection.setDoOutput(true);
//	            connection.setRequestMethod("POST");
//	            connection.setRequestProperty("Content-Type", "application/json");
//
//	            String input = "{\"clientId\": \"" + clientId + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + script +
//	            "\",\"language\":\"" + language + "\",\"versionIndex\":\"" + versionIndex + "\"} ";
//
//	            System.out.println(input);
//
//	            OutputStream outputStream = connection.getOutputStream();
//	            outputStream.write(input.getBytes());
//	            outputStream.flush();
//
//	            System.out.println(connection.getResponseCode());
//	            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
//	                throw new RuntimeException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
//	            }
//
//	            BufferedReader bufferedReader;
//	            bufferedReader = new BufferedReader(new InputStreamReader(
//	            (connection.getInputStream())));
//
//	            String output;
//	            System.out.println("Output from JDoodle .... \n");
//	            while ((output = bufferedReader.readLine()) != null) {
//	                System.out.println(output);
//	            }
//
//	            connection.disconnect();
//
//	        } catch (MalformedURLException e) {
//	            e.printStackTrace();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
		// 컴파일 end

		

	      codeService.codeCompileAlpaca();
	      
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	


      
  
}