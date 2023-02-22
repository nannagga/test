package com.java.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.mapper.BoardMapper;
import com.java.vo.BoardVo;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardMapper boardMapper;
	
	
	@Override //전체 게시글 가져오기
	public Map<String, Object> selectBoardList(int page) {
		//page:현재 페이지, listCount(게시글 총 개수), maxpage: 최대 페이지, startpage:, endpage, startrow, endrow/게시판 하단 넘버링에 필요한 것들.
		//페이지 정보 메소드
		HashMap<String, Object> map = pageMethod(page);
		int startrow = (int)map.get("startrow");
		int endrow = (int)map.get("endrow");
		
		//전체 게시글 가져오기
		List<BoardVo> list = boardMapper.selectBoardList(startrow, endrow);
		//페이지 정보(현재 페이지), 전체 게시글 개수....
//		System.out.println("BoardServiceImpl listCount : "+listCount);
		map.put("list", list);
		map.put("page", page);
		map.put("listCount", map.get("listCount"));
		map.put("maxpage", map.get("maxpage"));
		map.put("startpage", map.get("startpage"));
		map.put("endpage", map.get("endpage"));
		return map;
	}//selectBoardList
	
	
	
	public HashMap<String, Object> pageMethod(int page) {
		HashMap<String , Object> map = new HashMap<>();
		
		int rowPerPage=10; //한 패이지 당 게시글 개수(하단 넘버링 당)
		int pageList=5; //하단 넘버링 개수 
		//page:현재 페이지, listCount(게시글 총 개수), maxpage: 최대 페이지, startpage:, endpage, startrow, endrow/게시판 하단 넘버링에 필요한 것들.
		
		int listCount = boardMapper.selectCount();
		int maxpage =(int)(Math.ceil((double)(listCount/rowPerPage)));
		int startpage = ((page-1)/pageList)*pageList+1;
		int endpage = maxpage;
		if(endpage>startpage+pageList-1) endpage=startpage+pageList-1;
		
		int startrow = (page-1)*rowPerPage+1;
		int endrow = startrow+rowPerPage-1;
		map.put("listCount", listCount);
		map.put("maxpage", maxpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		return map; //리턴타입 개수 무제한 여러개를 보낼때 많이 사용함
	}
	
	//1개 게시글 가져오기
	public BoardVo selectOne(int bno) {
		//count 1증가
		boardMapper.updateBhitUp(bno);
		BoardVo boardVo = boardMapper.selectOne(bno);
		
		return boardVo;
	}//selectOne


	@Override //파일첨부 게시글 저장
	public void insertBoard(BoardVo boardVo) {
		boardMapper.insertBoard(boardVo);
	}//insertBoard



	@Override //게시글 수정 저장
	public void updateBoard(BoardVo boardVo) {
		boardMapper.updateBoard(boardVo);
	}

	@Override //게시글 삭제하기
	public void deleteBoard(int bno) {
		boardMapper.deleteBoard(bno);
	}

	@Override //게시글에 답변달기
	public void insertReply(BoardVo boardVo) {
		//bgroup 부모보다 큰 bstep을 1 증가 
		boardMapper.updateReplyStepUp(boardVo);
		boardMapper.insertReply(boardVo);
	}
	
}
