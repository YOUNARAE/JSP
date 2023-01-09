package kr.or.ddit.memo.dao;

import java.util.List;
import kr.or.ddit.vo.MemoVO;

public interface MemoDAO {
	public List<MemoVO> selectMemoList();
	public int insertMemo(MemoVO memo);
	public int updateMemo(MemoVO memo); // 이건 있는 거에서 수정하는 거라서 코드값이 무조건 필요하다
	public int deleteMemo(int code);
}
