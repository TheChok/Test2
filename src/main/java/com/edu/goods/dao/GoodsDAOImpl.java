package com.edu.goods.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.edu.goods.vo.BookTypeVO;


//-----------------------------------------------------------------------------------------------------------
// public class GoodsDAOImpl implements GoodsDAO 
//-----------------------------------------------------------------------------------------------------------
@Repository
public class GoodsDAOImpl implements GoodsDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsDAOImpl.class);

	@Autowired
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
	// public interface GoodsDAO
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<BookTypeVO> listBookTypes() throws DataAccessException {
		
		logger.info("GoodsDAOImpl listbookTypes() 시작");
		
		List<BookTypeVO> booksTypeList = null;
		booksTypeList = sqlSession.selectList("mapper.goods.selectAllBookType");

		return booksTypeList;
	}

} // End - public class GoodsDAOImpl implements GoodsDAO 













