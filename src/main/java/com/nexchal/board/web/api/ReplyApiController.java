package com.nexchal.board.web.api;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexchal.board.domain.ReplyVO;
import com.nexchal.board.domain.paging.Criteria;
import com.nexchal.board.domain.paging.Pagination;
import com.nexchal.board.service.reply.ReplyService;
import com.nexchal.board.web.dto.ResponseDto;
import com.nexchal.security.service.CustomUserDetails;
import com.nexchal.user.domain.UserVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

// @PreAuthorize(value = "isAuthenticated()")  // 2026-01-17 : 로그인한 이용자는 접근 불가(옛날 방식)
@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
@Log4j2
public class ReplyApiController {

	private final ReplyService replyService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerReply(@RequestBody ReplyVO replyVO,
										   @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		
		if(customUserDetails == null) {
			return new ResponseEntity<>(new ResponseDto<>(-1, "로그인한 회원만 댓글을 작성할 수 있습니다.", null), HttpStatus.UNAUTHORIZED);
		}
		
		Map<String, Object> result = new HashMap<>();
		
		Long rno = replyService.registerReply(replyVO);

		int replyCount = replyService.getTotalReplyWithBno(null, replyVO.getBno());
		
		log.info("replyVO : " + replyVO);
		
		result.put("rno", rno);
		result.put("replyCount", replyCount);
		
		return new ResponseEntity<>(new ResponseDto<>(1, "댓글 작성하기 성공", result), HttpStatus.OK);
		
	}
	
	@GetMapping("/{rno}") 
	public Map<String, Object> getReplyOne(@PathVariable("rno") Long rno) {
		
		Map<String, Object> result = new HashMap<>();
		
		ReplyVO replyVO = replyService.getReply(rno);
		
		log.info("replyVO : " + replyVO);
		
		result.put("replyVO", replyVO);
		
		return result;
		
	}
	
//	@DeleteMapping("/{rno}")
	public Map<String, Object> deleteReplyOneV1(@PathVariable("rno") Long rno) {
		
		Map<String, Object> result = new HashMap<>();
		
		ReplyVO replyVO = replyService.getReply(rno);
		replyVO.setDeleteYn(true);
		
		log.info("replyVO : " + replyVO);
		
		replyService.modifyReply(replyVO);
		
		ReplyVO deletedReplyVO = replyService.getReply(replyVO.getRno());
		
		result.put("replyVO", deletedReplyVO);
		
		return result;
		
	}
	
	@DeleteMapping("/{rno}")
	public ResponseEntity<?> deleteReplyOneV2(@PathVariable("rno") Long rno, 
			  								  @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		
		try {
			checkAuthor(rno, customUserDetails.getUserVO());
		} catch (Exception e) {
			Map<String, Object> errorMap = new HashMap<>();
			
			errorMap.put("status", 403);
			
			return new ResponseEntity<>(new ResponseDto<>(-1, "작성자만 삭제가 가능합니다.", errorMap), HttpStatus.FORBIDDEN);  
		}
		
		Map<String, Object> result = new HashMap<>();
		
		ReplyVO replyVO = replyService.getReply(rno);
		replyVO.setDeleteYn(true);
		
		log.info("replyVO : " + replyVO);
		
		replyService.modifyReply(replyVO);
		
		ReplyVO deletedReplyVO = replyService.getReply(replyVO.getRno());
		
		result.put("replyVO", deletedReplyVO);
		
		return new ResponseEntity<>(new ResponseDto<>(1, "댓글 삭제하기 성공", null), HttpStatus.OK); 
	}
	
//	@PutMapping("/{rno}")
	public Map<String, Object> modifyReplyOneV1(@PathVariable("rno") Long rno, 
											    @RequestBody ReplyVO replyVO) {
		
		Map<String, Object> result = new HashMap<>();
		
		replyVO.setRno(rno);
		replyVO.setUpdateDate(LocalDateTime.now());
		
		replyService.modifyReply(replyVO);
		
		ReplyVO updatedReplyVO = replyService.getReply(replyVO.getRno());
		
		result.put("replyVO", updatedReplyVO);
		
		return result;
	}
	
	@PutMapping("/{rno}")
	public ResponseEntity<?> modifyReplyOneV2(@PathVariable("rno") Long rno, 
			  								  @RequestBody ReplyVO replyVO,
			  								  @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		
		try {
			checkAuthor(rno, customUserDetails.getUserVO());
		} catch (Exception e) {
			Map<String, Object> errorMap = new HashMap<>();
			
			errorMap.put("status", 403);
			
			return new ResponseEntity<>(new ResponseDto<>(-1, "작성자만 수정이 가능합니다.", errorMap), HttpStatus.FORBIDDEN);  
		}
		
		return new ResponseEntity<>(new ResponseDto<>(1, "댓글 작성하기 성공", null), HttpStatus.OK); 
	}
		
	
//	@GetMapping("/list/{bno}")
	public Map<String, Object> getReplyListOfBoardV1(Criteria criteria, @PathVariable("bno") Long bno) {
		
		log.info("bno : " + bno);
		log.info("criteria : " + criteria);
		
		Map<String, Object> result = new HashMap<>();
		
		List<ReplyVO> replies = replyService.getRelpyListWithBno(criteria, bno);
	
		int totalCount = replyService.getTotalReplyWithBno(criteria, bno);
		
		Pagination pagination = new Pagination(criteria, totalCount);
		
		result.put("replies", replies);
		result.put("pagination", pagination);
		
		return result;
	}
	
	@GetMapping("/list/{bno}")
	public ResponseEntity<?> getReplyListOfBoardV2(Criteria criteria, 
												   @PathVariable("bno") Long bno,
												   @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		
		if(customUserDetails.getUserVO() == null) {
			Map<String, Object> errorMap = new HashMap<>();
			
			errorMap.put("status", 403);
			
			return new ResponseEntity<>(new ResponseDto<>(-1, "비회원은 이용할 수 없습니다.", errorMap), HttpStatus.FORBIDDEN);  
		}
		
		log.info("bno : " + bno);
		log.info("criteria : " + criteria);
		
		Map<String, Object> result = new HashMap<>();
		
		List<ReplyVO> replies = replyService.getRelpyListWithBno(criteria, bno);
	
		int totalCount = replyService.getTotalReplyWithBno(criteria, bno);
		
		Pagination pagination = new Pagination(criteria, totalCount);
		
		result.put("replies", replies);
		result.put("pagination", pagination);
		
		return new ResponseEntity<>(new ResponseDto<>(1, "댓글 조회하기 성공", result), HttpStatus.OK); 
	}
	
	private void checkAuthor(Long rno, UserVO userVO) {
		
		log.info("=============== CHECK AUTHOR ===============");
		log.info("userVO : " + userVO.toString());
		
		if(rno == null || userVO == null) {
			throw new RuntimeException("작성자가 존재하지 않습니다.");
		}
		
		ReplyVO findReplyVO = replyService.getReply(rno);
		
		log.info("=============== CHECK AUTHOR ===============");
		log.info("findReplyVO : " + findReplyVO.toString());
		
		if(!findReplyVO.getWriter().equals(userVO.getUsername())) {
			throw new RuntimeException("작성자 본인만 가능합니다.");
		}
		
	}
	
}
/*
  - api sample test -
  @GetMapping("/sample01")
	public Map<String, String> sample01() {
		Map<String, String> result = new HashMap<>();
		
		result.put("v1", "AAA");
		result.put("v2", "BBB");
		
		return result;
	}
	
	@GetMapping("/sample02")
	public String sample02() {
		return "Hello World";
	}
	
	// consumes : 해당 맵핑이 어떤 데이터를 소비하는지 명시, produces : return시 어떤 데이터로 반환할 것인지
	@GetMapping(value = "/sample03", consumes= {}, produces = MediaType.APPLICATION_JSON_VALUE)
	public String sample03() {
		return "Hello World";
	} 
 */
