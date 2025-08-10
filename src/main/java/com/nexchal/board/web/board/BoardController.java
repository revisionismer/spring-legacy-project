package com.nexchal.board.web.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nexchal.board.domain.BoardVO;
import com.nexchal.board.domain.paging.Criteria;
import com.nexchal.board.domain.paging.Pagination;
import com.nexchal.board.service.board.BoardServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	private final BoardServiceImpl boardService;
	
	// 1-1. getAllBoardlist 
//	@GetMapping("/list")
	public void getAllBoardlist(Model model) {
		log.info("------------list----------");
		
		List<BoardVO> result = boardService.readBoardlist();
		
		model.addAttribute("list", result);
		
		log.info(result);
	}
	
	@GetMapping("/list")
	public void getAllBoardlist(@ModelAttribute("cri") Criteria criteria, Model model) {
		log.info("------------list----------");
		
		List<BoardVO> result = boardService.readBoardlist(criteria);
		
		model.addAttribute("list", result);
		
		Pagination pagination = new Pagination(criteria, boardService.getTotalCount(criteria));
		
		model.addAttribute("pagination", pagination);
		
		log.info(result);
	}
	
	// 1-2. readBoardOne
	// @GetMapping("/read/{bno}")
	public String getBoardOne(@PathVariable(name = "bno") Long bno, Model model) {
		log.info("------------boardOne----------");
		
		BoardVO boardVO = boardService.readBoardOne(bno);
		
		log.info("boardVO : " + boardVO);
		
		model.addAttribute("board", boardVO);
		
		return "/board/read";
	}
	
	// 1-3. modify
	// @GetMapping("/modify/{bno}")
	public String modifyBoardOne(@PathVariable(name = "bno") Long bno, Model model) {
		log.info("------------boardOne----------");
		
		BoardVO boardVO = boardService.readBoardOne(bno);
		
		log.info("boardVO : " + boardVO);
		
		model.addAttribute("board", boardVO);
		
		return "/board/modify";
	}
	

	// 1-4. register(화면)
	@GetMapping("/register")
	public String registerBoardGET(Model model) {
		log.info("------------registerBoard 화면 이동----------");
		
		return "/board/register";
	}

	// 1-5. register(등록)
	@PostMapping("/register")
	public String registerBoardPOST(BoardVO boardVO, RedirectAttributes rttr) {
		log.info("------------board write ----------");
		
		Long bno = boardService.createBoard(boardVO);
	
		rttr.addFlashAttribute("result", bno);
		
		return "redirect:/board/list";
	}
	
	// 1-6. read와 modify 페이지 컨트롤러 합치기
	@GetMapping("/{job}/{bno}")
	public String readAndUpdateView(@PathVariable(name = "job") String job, 
									@PathVariable(name = "bno") Long bno,
									Model model) {
		
		log.info("job : " + job);
		log.info("bno : " + bno);
		
		if(!( job.equals("read") || job.equals("modify")) ) {
			throw new RuntimeException("Bad Request job");
		}
		
		BoardVO boardVO = boardService.readBoardOne(bno);
		
		log.info("boardVO : " + boardVO);
		
		model.addAttribute("board", boardVO);
		
		return "/board/" + job;
	}
	
	@PostMapping("/delete/{bno}")
	public String deleteBookByBno(@PathVariable(name = "bno") Long bno, RedirectAttributes rttr) {
		
		BoardVO boardVO = boardService.readBoardOne(bno);
		boardVO.setDeleteYn(true);
		
		log.info("boardVO : " + boardVO);
		
		boardService.updateBoard(boardVO);
		
		rttr.addFlashAttribute("result", boardVO.getBno());
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify/{bno}")
	public String modifyBookByBno(@PathVariable(name = "bno") Long bno, 
								  BoardVO boardVO,
								  RedirectAttributes rttr) {
		
		boardVO.setBno(bno);
	
		log.info("boardVO : " + boardVO);
		
		boardService.updateBoard(boardVO);
		
		return "redirect:/board/read/" + bno;
	}
}