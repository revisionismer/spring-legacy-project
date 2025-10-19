package com.nexchal.board.web.api;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
@Log4j2
public class ReplyApiController {

	private final ReplyService replyService;
	
	@PostMapping("/register")
	public Map<String, Object> registerReply(@RequestBody ReplyVO replyVO) {
		
		Map<String, Object> result = new HashMap<>();
		
		Long rno = replyService.registerReply(replyVO);

		int replyCount = replyService.getTotalReplyWithBno(null, replyVO.getBno());
		
		log.info("replyVO : " + replyVO);
		
		result.put("rno", rno);
		result.put("replyCount", replyCount);
		
		return result;
		
	}
	
	@GetMapping("/{rno}") 
	public Map<String, Object> getReplyOne(@PathVariable("rno") Long rno) {
		
		Map<String, Object> result = new HashMap<>();
		
		ReplyVO replyVO = replyService.getReply(rno);
		
		log.info("replyVO : " + replyVO);
		
		result.put("replyVO", replyVO);
		
		return result;
		
	}
	
	@DeleteMapping("/{rno}")
	public Map<String, Object> deleteReplyOneByRno(@PathVariable("rno") Long rno) {
		
		Map<String, Object> result = new HashMap<>();
		
		ReplyVO replyVO = replyService.getReply(rno);
		replyVO.setDeleteYn(true);
		
		log.info("replyVO : " + replyVO);
		
		replyService.modifyReply(replyVO);
		
		ReplyVO deletedReplyVO = replyService.getReply(replyVO.getRno());
		
		result.put("replyVO", deletedReplyVO);
		
		return result;
		
	}
	
	@PutMapping("/{rno}")
	public Map<String, Object> modifyReplyOne(@PathVariable("rno") Long rno, 
											  @RequestBody ReplyVO replyVO) {
		
		Map<String, Object> result = new HashMap<>();
		
		replyVO.setRno(rno);
		replyVO.setUpdateDate(LocalDateTime.now());
		
		replyService.modifyReply(replyVO);
		
		ReplyVO updatedReplyVO = replyService.getReply(replyVO.getRno());
		
		result.put("replyVO", updatedReplyVO);
		
		return result;
	}
	
	@GetMapping("/list/{bno}")
	public Map<String, Object> getReplyListOfBoard(Criteria criteria, @PathVariable("bno") Long bno) {
		
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
