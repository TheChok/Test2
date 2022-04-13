package com.edu.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.edu.member.service.MemberServiceImpl;
import com.edu.member.vo.MemberVO;

//-----------------------------------------------------------------------------------------------------------
// public class MemberDAOImpl implements MemberDAO
//-----------------------------------------------------------------------------------------------------------
@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 회원 전체 목록 조회
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result = sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 조회
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO selectMember(String id) throws DataAccessException {
		MemberVO memberVO = sqlSession.selectOne("mapper.member.selectMember", id);
		return memberVO;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 수정하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int updateMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.update("mapper.member.updateMember", memberVO);
		return result;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO loginByID(MemberVO memberVO) throws DataAccessException {
		MemberVO memVO = sqlSession.selectOne("mapper.member.loginByID", memberVO);
		return memVO;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사(Ajax)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int idCheck(MemberVO memberVO) throws DataAccessException {
		logger.info("MemberDAOImpl idCheck.....");
		System.out.println("id : " + memberVO.getId());
		return sqlSession.selectOne("mapper.member.idCheck", memberVO);
	}






} // End - public class MemberDAOImpl implements MemberDAO
