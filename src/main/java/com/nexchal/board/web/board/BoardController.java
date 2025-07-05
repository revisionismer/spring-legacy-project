package com.nexchal.board.web.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nexchal.board.domain.BoardVO;
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
	@GetMapping("/list")
	public void getAllBoardlist(Model model) {
		log.info("------------list----------");
		
		List<BoardVO> result = boardService.readBoardlist();
		
		model.addAttribute("list", result);
		
		log.info(result);
	}
	
	// 1-2. readBoardOne
	@GetMapping("/read/{bno}")
	public String getBoardOne(@PathVariable(name = "bno") Long bno, Model model) {
		log.info("------------boardOne----------");
		
		BoardVO boardVO = boardService.readBoardOne(bno);
		
		log.info("boardVO : " + boardVO);
		
		model.addAttribute("board", boardVO);
		
		return "/board/read";
	}
	
	// 1-3. modify
	@GetMapping("/modify/{bno}")
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
}
