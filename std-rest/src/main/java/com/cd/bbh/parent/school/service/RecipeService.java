package com.cd.bbh.parent.school.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.utils.DateUtils;
import com.cd.bbh.parent.school.dao.RecipesDao;
import com.cd.bbh.parent.school.vo.Recipe;
import com.cd.bbh.parent.school.vo.RecipesVO;

@Service
public class RecipeService {

	@Autowired
	private RecipesDao recipesDao;

	public List<RecipesVO> findRecipes(Date searchDate, Long childid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginDate", DateUtils.getMonday(searchDate));
		params.put("endDate", DateUtils.getSunday(searchDate));
		params.put("child", childid);

		// 接收到一周的食谱数据
		List<RecipesVO> rvoList = recipesDao.searchRecipes(params);
		List<RecipesVO> result = buildRecipeVO();
		for (RecipesVO recipesVO : rvoList) {
			// 每天的食谱列表
			List<Recipe> rList = recipesVO.getRecipes();
			Map<String, Recipe> rMap = new HashMap<String, Recipe>();
			for (Recipe recipe : rList) {
				// 食谱类型
				String duration = recipe.getDuration();
				// 判定食谱类型是否存在
				if (rMap.containsKey(duration)) {
					Recipe r = rMap.get(duration);
					StringBuffer comment = new StringBuffer(r.getComments());
					comment.append(",");
					comment.append(recipe.getComments());
					r.setComments(comment.toString());
				} else {
					rMap.put(duration, recipe);
				}
			}
			// map转成list
			rList = new ArrayList<Recipe>(rMap.values());
			// 更新数据
			recipesVO.setRecipes(rList);
			
			RecipesVO  resultVO = result.get(result.indexOf(recipesVO));
			resultVO.setRecipes(recipesVO.getRecipes());
			Collections.sort(resultVO.getRecipes());
			recipesVO.setRecipes(null);//释放引用
		}
		return result;

	}

	private List<RecipesVO> buildRecipeVO() {
//		String[] week = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };
		String[] week = { "星期一", "星期二", "星期三", "星期四", "星期五"};
		List<RecipesVO> coursevos = new ArrayList<RecipesVO>();
		for (int i = 0; i < week.length; i++) {
			RecipesVO recipeVO = new RecipesVO();
			recipeVO.setDayOfWeek(week[i]);
			coursevos.add(recipeVO);
		}
		return coursevos;
	}

}
