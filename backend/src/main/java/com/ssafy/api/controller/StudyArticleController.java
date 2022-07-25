package com.ssafy.api.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.api.request.article.StudyArticleRegistPostReq;
import com.ssafy.api.request.article.StudyArticleUpdatePatchReq;
import com.ssafy.api.response.article.FileDto;
import com.ssafy.api.response.article.StudyArticlesInfoRes;
import com.ssafy.api.service.FileService;
import com.ssafy.api.service.StudyArticleService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.MD5Generator;
import com.ssafy.db.entity.StudyArticle;
import com.ssafy.db.entity.User;
import com.ssafy.db.specification.StudyArticleSpecification;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 스터디 게시판 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "스터디 게시판 API", tags = {"StudyArticle"})
@CrossOrigin
@RestController
@RequestMapping("/api/v1/studyArticles")
public class StudyArticleController {
	
//	@PostMapping(value = "/test", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE )
//	public ResponseEntity<? extends BaseResponseBody> saveDocu2ment(@RequestPart("files") MultipartFile[] filesUpload) {
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
//	}
	
	@Autowired
	StudyArticleService studyArticleService;
	@Autowired
	UserService userService;
	@Autowired
	FileService fileService;
	
	@PostMapping()
	@ApiOperation(value = "게시글 작성", notes = "<strong>게시글 정보</strong>를 통해 게시글을 작성한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })

	public ResponseEntity<? extends BaseResponseBody> registerArticles(
			@RequestBody @ApiParam(value="게시글 작성 정보", required = true) StudyArticleRegistPostReq registerInfo, @ApiIgnore Authentication authentication) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		
//		System.out.println(1);
//
//		try {
//            String origFilename = files.getOriginalFilename();
//            String filename = new MD5Generator(origFilename).toString();
//            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
//            String savePath = System.getProperty("user.dir") + "\\files";
//            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
//            if (!new File(savePath).exists()) {
//                try{
//                    new File(savePath).mkdir();
//                }
//                catch(Exception e){
//                    e.getStackTrace();
//                }
//            }
//            String filePath = savePath + "\\" + filename;
//            files.transferTo(new File(filePath));
//
//            FileDto fileDto = new FileDto();
//            fileDto.setOrigFilename(origFilename);
//            fileDto.setFilename(filename);
//            fileDto.setFilePath(filePath);
//
//            Long fileId = fileService.saveFile(fileDto);
//    		String userId = userDetails.getUsername();
//    		User user = userService.getUserByUserId(userId);
//    		studyArticleService.createArticle(user, fileId, registerInfo);
//    		
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
		
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		studyArticleService.createArticle(user, registerInfo);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	@GetMapping("/list")
	@ApiOperation(value = "게시글 전체 or 조건 조회", notes = "게시글의 전체 or 상세 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<Page<StudyArticle>> getArticles(
			@RequestParam(required = true) int page,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String category) {
		
	

		PageRequest pageRequest = PageRequest.of(page-1, 10);
		
		Specification<StudyArticle> spec = (root, query, criteriaBuilder) -> null;
		
		if (title != null) {
			spec = spec.and(StudyArticleSpecification.equalTitle(title));
		}
		if (category != null) {
			spec = spec.and(StudyArticleSpecification.equalCategory(category));
		}
		
		Page<StudyArticle> articles = studyArticleService.getArticles(spec, pageRequest);

		return ResponseEntity.status(200).body(articles);
	}
	
	@GetMapping("{id}")
	@ApiOperation(value = "게시글 상세 조회", notes = "게시글의 상세 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<StudyArticlesInfoRes> getArticle(@RequestParam(value = "게시글 id")Long id) {
		
		studyArticleService.updateHit(id);
		StudyArticle articles = studyArticleService.getArticlesById(id);

		StudyArticlesInfoRes articleInfoRes = StudyArticlesInfoRes.of(articles);

		return ResponseEntity.status(200).body(articleInfoRes);
	}
	
	@PatchMapping("{id}")
	@ApiOperation(value = "게시글 정보 수정", notes = "<strong>게시글 정보</strong>를 수정 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> updateArticle(
			@RequestBody @ApiParam(value="게시글 수정 정보", required = true) StudyArticleUpdatePatchReq updateInfo, Long id, @ApiIgnore Authentication authentication) {
		
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		
		studyArticleService.updateArticle(id, user, updateInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@DeleteMapping("{id}")
	@ApiOperation(value = "게시글 삭제", notes = "<strong>게시글 번호</strong>를 통해 게시글 삭제 한다.") 
    @ApiResponses({
        @ApiResponse(code = 204, message = "성공"),
    })
	public ResponseEntity<? extends BaseResponseBody> deleteArticle(
			@RequestBody @ApiParam(value="게시글 id", required = true) Long id, @ApiIgnore Authentication authentication) {
		
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		studyArticleService.deleteArticlesById(id);
		
		return ResponseEntity.status(204).body(BaseResponseBody.of(204, "Success"));
	}
	
}
