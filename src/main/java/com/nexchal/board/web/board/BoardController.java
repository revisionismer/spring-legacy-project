package com.nexchal.board.web.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	// 1-1. list
	@GetMapping("/list")
	public void getAllBoardlist(Model model) {
		log.info("------------list----------");
		
		List<BoardVO> result = boardService.readBoardlist();
		
		model.addAttribute("list", result);
		
		log.info(result);
	}
	
}
