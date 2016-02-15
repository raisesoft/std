package com.cd.bbh.parent.school.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cd.bbh.parent.school.vo.RecipesVO;

@Repository
public interface RecipesDao {

	List<RecipesVO> searchRecipes(Map<String, Object> params);

	List<RecipesVO> searchTeacherRecipes(Map<String, Object> params);

}
