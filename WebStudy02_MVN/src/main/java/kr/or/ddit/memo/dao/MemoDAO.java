package kr.or.ddit.memo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.MemoVO;

public interface MemoDAO {
	public List<MemoVO> selectMemoList(); //리턴타입 결과의 집합인 리스트, 레코드 하나에 대한 타입 MemoVO
	public int insertMemo(MemoVO memo); //  이 파라미터가 xml에 적용되어야한다.
	public int updateMemo(MemoVO memo); // 이건 있는 거에서 수정하는 거라서 코드값이 무조건 필요하다
//	public int deleteMemo(int code); //이름이 없었다가 어노테이션을 붙여서 이름이 있는 값이 되었다
	public int deleteMemo(@Param("code") int code); 
}
