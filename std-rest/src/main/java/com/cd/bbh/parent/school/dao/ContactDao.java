package com.cd.bbh.parent.school.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cd.bbh.parent.school.vo.ContactVO;

@Repository
public interface ContactDao {

	List<ContactVO> searchContactsByTeacher(Long teacherid);

	List<ContactVO> searchContactsByParent(Long parentid);

}
