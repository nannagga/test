package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.naming.directory.InvalidSearchFilterException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.service.BoardService;
import com.java.vo.BoardVo;
import com.java.vo.BoardVo2;

@Controller
public class BoardController {

	@Autowired
	BoardService boardservice;

	// --=-=-=-=-=-=-=[ 글쓰기 페이지 ]-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	@GetMapping("board/fboardWrite") // 글쓰기
	public String fboardWrite(Model model) {

		return "board/fboardWrite";
	}

	@GetMapping("board/fboardWrite2") // 다중파일 올려서 글쓰기 페이지
	public String fboardWrite2(Model model) {

		return "board/fboardWrite2";
	}

	@GetMapping("board/fboardWrite3") // ajax 글쓰기 페이지 이동
	public String fboardWrite3(Model model) {

		return "board/fboardWrite3";
	}
	// --=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

	// --=-=-=-=-=-=-=[ 글쓰기 저장 ]-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	@PostMapping("board/fboardWrite3") // 게시글 저장
	@ResponseBody // 페이지가 열리면 안됨
	public String fboardWrite3(BoardVo boardVo, @RequestPart MultipartFile file, Model model) {
		boardVo.setBfile("");

		if (!file.isEmpty()) {
			String originFileName = file.getOriginalFilename(); // 파일명 하나 받아옴.
			long time = System.currentTimeMillis(); // 현재 시간을 밀리 세컨드로 바꿔줌 122111222345567
			String uploadFileName = String.format("%d_%s", time, originFileName); // 파일명 중복 방지를 위해서 이렇게 함.
																					// 1212113354355_a.jpg
			String fileSaveUrl = "C:\\Users\\KV007\\git\\repository\\sts0221\\src\\main\\resources\\static\\upload\\";
//			String fileSaveUrl = "/upload";
			File f = new File(fileSaveUrl + uploadFileName); // /upload/1212113354355_a.jpg
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("fboardWrite3 uploadFileName" + uploadFileName);
			// 변경된 파일이름을 boardVo객체에 저장.
			boardVo.setBfile(uploadFileName);

		} // if
		boardservice.insertBoard(boardVo);

		return "redirect:/board/fboardList";
	}// 파일저장하기

	@PostMapping("board/fboardWrite") // 게시글 저장
	public String fboardWrite(BoardVo boardVo, @RequestPart MultipartFile file, Model model) {
		boardVo.setBfile("");

		if (!file.isEmpty()) {
			String originFileName = file.getOriginalFilename(); // 파일명 하나 받아옴.
			long time = System.currentTimeMillis(); // 현재 시간을 밀리 세컨드로 바꿔줌 122111222345567
			String uploadFileName = String.format("%d_%s", time, originFileName); // 파일명 중복 방지를 위해서 이렇게 함.
																					// 1212113354355_a.jpg
			String fileSaveUrl = "C:\\Users\\KV007\\git\\repository\\sts0221\\src\\main\\resources\\static\\upload\\";
//			String fileSaveUrl = "/upload";
			File f = new File(fileSaveUrl + uploadFileName); // /upload/1212113354355_a.jpg
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 변경된 파일이름을 boardVo객체에 저장.
			boardVo.setBfile(uploadFileName);

		} // if
		boardservice.insertBoard(boardVo);

		return "redirect:/board/fboardList";
	}// 파일저장하기

	@PostMapping("board/fboardWrite2") // 첨부파일 2개 있는 게시글 저장
	public String fboardWrite2(BoardVo2 boardVo2, @RequestPart List<MultipartFile> files, Model model) {
		// 업로드 파일이 없을경우
		boardVo2.setBfile("");
		boardVo2.setBfile2("");

		String[] uploadFileName = new String[2];

		if (!files.isEmpty()) {
			for (int i = 0; i < files.size(); i++) {
				String originFileName = files.get(i).getOriginalFilename(); // 파일명 하나 받아옴.
				long time = System.currentTimeMillis(); // 현재 시간을 밀리 세컨드로 바꿔줌 122111222345567
				uploadFileName[i] = String.format("%d_%s", time, originFileName); // 파일명 중복 방지를 위해서 이렇게 함.
																					// 1212113354355_a.jpg
				String fileSaveUrl = "C:\\Users\\KV007\\git\\repository\\sts0221\\src\\main\\resources\\static\\upload\\";
//				String fileSaveUrl = "/upload";
				File f = new File(fileSaveUrl + uploadFileName[i]); // /upload/1212113354355_a.jpg
				try {
					files.get(i).transferTo(f);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (i == 0)
					boardVo2.setBfile(uploadFileName[i]);
				else
					boardVo2.setBfile2(uploadFileName[i]);

				System.out.println("uploadFileName[i]" + uploadFileName[i]);
			} // for

		} // if
//		boardservice.insertBoard(boardVo2);

		return "redirect:/board/fboardList";
	}// 파일 2개저장하기

	// --=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

	@GetMapping("board/fboardList") // 전체게시글 가져오기
	public String fboardList(@RequestParam(defaultValue = "1") int page, Model model) {
		// 모든 게시글 정보 model에 추가해서 보냄.

//		List<BoardVo> list = boardservice.selectBoardList();
		Map<String, Object> map = boardservice.selectBoardList(page);
		model.addAttribute("map", map);

		return "board/fboardList";
	}

	@RequestMapping("board/fboardView") // url주소만 체크
	public String fboardView(@RequestParam("bno") int bno, @RequestParam(defaultValue = "1") int page, Model model) {
		BoardVo boardVo = boardservice.selectOne(bno);
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("page", page);
//		model.addAttribute("boardVo", boardVo);
//		model.addAttribute("num", 1);
//		return "board/fboardView";
//		return "redirect:/?num=10&page=2"; //redirect:/board/fboardView 이 주소를 찾아서 열어라 url주소도 바꿔줌(데이터 리셋해서 넘어감.) 데이터를 넘기고 싶으면 파라메터 값으로 보내줘야함.
//		return "index"; //""여기 들어오는 이름.jsp파일을 열어달라는 것.
		return "board/fboardView";
	}

	@GetMapping("board/fboardUpdate") // 게시글 수정페이지
	public String fboardUpdate(@RequestParam int bno, @RequestParam(defaultValue = "1") int page, Model model) {
		BoardVo boardVo = boardservice.selectOne(bno);
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("page", page);
		return "board/fboardUpdate";
	}// 게시글 수정페이지

	@PostMapping("board/fboardUpdate") // 게시글 수정
	public String fboardUpdate(BoardVo boardVo, @RequestPart MultipartFile file, Model model) {

		// 업로드 파일 있을경우
		if (!file.isEmpty()) {
			String originFileName = file.getOriginalFilename(); // 파일명 하나 받아옴.
			long time = System.currentTimeMillis(); // 현재 시간을 밀리 세컨드로 바꿔줌 122111222345567
			String uploadFileName = String.format("%d_%s", time, originFileName); // 파일명 중복 방지를 위해서 이렇게 함.
																					// 1212113354355_a.jpg
			String fileSaveUrl = "C:\\Users\\KV007\\git\\repository\\sts0221\\src\\main\\resources\\static\\upload\\";
//			String fileSaveUrl = "/upload";
			File f = new File(fileSaveUrl + uploadFileName); // /upload/1212113354355_a.jpg
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 변경된 파일이름을 boardVo객체에 저장.
			boardVo.setBfile(uploadFileName);

		} // if
		boardservice.updateBoard(boardVo);

		return "redirect:/board/fboardView?bno="+boardVo.getBno();
	}// 게시글 수정

	@GetMapping("board/fboardDelete") // 게시글 삭제
	public String fboardDelete(@RequestParam int bno, @RequestParam(defaultValue = "1") int page, Model model) {
		boardservice.deleteBoard(bno);
		return "redirect:/board/fboardList";
	}// 게시글 삭제

	@GetMapping("board/fboardReply") // 게시글 답변달기 페이지
	public String fboardReply(@RequestParam int bno, @RequestParam(defaultValue = "1") int page, Model model) {
		BoardVo boardVo = boardservice.selectOne(bno);
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("page", page);
		return "board/fboardReply";
	}// 게시글 답변달기 페이지

	@PostMapping("board/fboardReply") // 게시글 답변달기 페이지
	public String fboardReply(BoardVo boardVo, @RequestPart MultipartFile file, Model model) {
		boardVo.setBfile("");

		if (!file.isEmpty()) {
			String originFileName = file.getOriginalFilename(); // 파일명 하나 받아옴.
			long time = System.currentTimeMillis(); // 현재 시간을 밀리 세컨드로 바꿔줌 122111222345567
			String uploadFileName = String.format("%d_%s", time, originFileName); // 파일명 중복 방지를 위해서 이렇게 함.
																					// 1212113354355_a.jpg
			String fileSaveUrl = "C:\\Users\\KV007\\git\\repository\\sts0221\\src\\main\\resources\\static\\upload\\";
//			String fileSaveUrl = "/upload";
			File f = new File(fileSaveUrl + uploadFileName); // /upload/1212113354355_a.jpg
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 변경된 파일이름을 boardVo객체에 저장.
			boardVo.setBfile(uploadFileName);

		} // if
		boardservice.insertReply(boardVo);

		return "redirect:/board/fboardList";
	}// 게시글 답변달기 페이지

	// requstParam
//	@PostMapping("board/fboardView")
//	public String fboardView(@RequestParam(required=false,defaultValue="1") int id,@RequestParam String btitle, Model model) { //형변환 없이 바로 받을 수 있다. 단 id가 숫자일 경우만 가능. 
//		model.addAttribute("id",id);
//		model.addAttribute("btitle",btitle);
//		return "board/fboardView";
//	}

	// HttpServletRequest
//	@PostMapping("board/fboardView")
//	public String fboardView(HttpServletRequest request, Model model) {
//		String id = request.getParameter("id");
//		model.addAttribute("id",id);
//		return "board/fboardView";
//	}
}
