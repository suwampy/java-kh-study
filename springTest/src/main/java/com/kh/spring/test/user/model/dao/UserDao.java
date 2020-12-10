package com.kh.spring.test.user.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.test.user.model.vo.User;

// 3_5. UserDao 만들기 (mybatis-config.xml, user-mapper.xml)
// 3_6. UserDaoTest 만들기
@Repository
public class UserDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public User selectOneUser(String id) {
		return sqlSession.selectOne("user.selectOneUser", id);
	}
	
	public int insertUser(User u) {
		return sqlSession.insert("user.insertUser", u);
	}
	
	public int deleteUser(String id) {
		return sqlSession.delete("user.deleteUser", id);
	}
	
	
	
	
	
	
}
