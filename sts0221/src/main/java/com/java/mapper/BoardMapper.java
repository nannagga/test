package com.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.java.vo.BoardVo;

@Mapper //sqlsessionTemplate 여기에 들어감 자동으로 실행시켜줌 (SqlSessionTemplate sqlSessionTemplate)주입시켜줌
public interface BoardMapper {
	
	//*.xml 문서 selectBoardList를 찾아서 실행함. BoardVo에 넣어서 다시 보내달라는 뜻.
	public List<BoardVo> selectBoardList(int startrow, int endrow);

	public int selectCount();

	//게시글 1개 가져오기
	public BoardVo selectOne(int bno);
	
	//조회수 1 증가
	public void updateBhitUp(int bno);

	//파일첨부 게시글 저장
	public void insertBoard(BoardVo boardVo);

	//수정한 게시글 저장
	public void updateBoard(BoardVo boardVo);

	//게시글 삭제
	public void deleteBoard(int bno);

	//게시글에 답변달기
	public void insertReply(BoardVo boardVo);

	//게시글 답변달기 step 1증가시키기
	public void updateReplyStepUp(BoardVo boardVo);

}
