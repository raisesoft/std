package com.cd.bbh.school.app.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.Pageable;
import com.cd.bbh.common.utils.DateUtils;
import com.cd.bbh.parent.mine.vo.ChildStarVO;
import com.cd.bbh.parent.school.dao.ClassStarDao;
import com.cd.bbh.parent.school.dao.RecipesDao;
import com.cd.bbh.parent.school.vo.Recipe;
import com.cd.bbh.parent.school.vo.RecipesVO;
import com.cd.bbh.school.app.dao.SchoolCourseRecordDao;
import com.cd.bbh.school.app.dao.SchoolTeacherClassDao;
import com.cd.bbh.school.app.vo.ClassSimple;
import com.cd.bbh.school.app.vo.CourseRecord;
import com.cd.bbh.school.app.vo.CourseRecordVO;

@Service
public class TeacherSchoolService {

	@Autowired
	private ClassStarDao classStarDao;
	@Autowired
	private RecipesDao recipesDao;
	@Autowired
	private SchoolCourseRecordDao teacherSchoolDao;
	@Autowired
	private SchoolTeacherClassDao schoolTeacherClassDao;

	/**
	 * 根据教师ID，查询周班级之星。
	 * 
	 */
	public List<ChildStarVO> findClassWeekStar(Long teacherid, Pageable pageable) {
		return classStarDao.searchTeacherClassWeekStars(teacherid, pageable.getPage(), pageable.getPagesize());
	}

	/**
	 * 根据教师ID，查询月班级之星。
	 * 
	 */
	public List<ChildStarVO> findClassMonthStar(Long teacherid, Pageable pageable) {
		return classStarDao.searchTeacherClassMonthStars(teacherid, pageable.getPage(), pageable.getPagesize());
	}

	/**
	 * 老师查看食谱
	 * 
	 * @param searchDate
	 * @param teacherid
	 * @return
	 */
	public List<RecipesVO> findRecipes(Date searchDate, Long teacherid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginDate", DateUtils.getMonday(searchDate));
		params.put("endDate", DateUtils.getSunday(searchDate));
		params.put("teacherid", teacherid);
		List<RecipesVO> result = recipesDao.searchTeacherRecipes(params);
		List<RecipesVO> recipeVOs = buildRecipeVO();

		for (RecipesVO tempRecipeVO : result) {
			List<Recipe> recipes = tempRecipeVO.getRecipes();
			Map<String, Recipe> recipeMap = new HashMap<String, Recipe>();
			for (Recipe recipe : recipes) {
				if (!recipeMap.containsKey(recipe.getDuration())) {
					recipeMap.put(recipe.getDuration(), recipe);
				} else {
					Recipe temp = recipeMap.get(recipe.getDuration());
					temp.setComments(temp.getComments() + "," + recipe.getComments());
				}
			}
			tempRecipeVO.getRecipes().clear();
			tempRecipeVO.getRecipes().addAll(recipeMap.values());
			
			RecipesVO  recipeVO = recipeVOs.get(recipeVOs.indexOf(tempRecipeVO));
			recipeVO.setRecipes(tempRecipeVO.getRecipes());
			Collections.sort(recipeVO.getRecipes());
			tempRecipeVO.setRecipes(null);//释放引用
		}
		return recipeVOs;
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

	/**
	 * 老师查看班级剪影
	 * 
	 * @param searchDate
	 * @param teacherid
	 * @return
	 */
	public List<RecipesVO> findAlbums(Date searchDate, Long teacherid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginDate", DateUtils.getMonday(searchDate));
		params.put("endDate", DateUtils.getSunday(searchDate));
		params.put("teacherid", teacherid);
		return recipesDao.searchRecipes(params);
	}

	/**
	 * 老师查看摄像头信息
	 * 
	 * @param searchDate
	 * @param teacherid
	 * @return
	 */
	public List<RecipesVO> findMonitors(Date searchDate, Long teacherid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginDate", DateUtils.getMonday(searchDate));
		params.put("endDate", DateUtils.getSunday(searchDate));
		params.put("teacherid", teacherid);
		return recipesDao.searchRecipes(params);
	}

	/**
	 * 老师查看积分信息
	 * 
	 * @param searchDate
	 * @param teacherid
	 * @return
	 */
	public List<RecipesVO> findIntegrals(Date searchDate, Long teacherid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginDate", DateUtils.getMonday(searchDate));
		params.put("endDate", DateUtils.getSunday(searchDate));
		params.put("teacherid", teacherid);
		return recipesDao.searchRecipes(params);
	}

	/**
	 * 老师查看通讯录
	 * 
	 * @param searchDate
	 * @param teacherid
	 * @return
	 */
	public List<RecipesVO> findContacts(Date searchDate, Long teacherid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginDate", DateUtils.getMonday(searchDate));
		params.put("endDate", DateUtils.getSunday(searchDate));
		params.put("teacherid", teacherid);
		return recipesDao.searchRecipes(params);
	}

	/**
	 * 老师根据时间获取课表
	 * 
	 * @param syllabus
	 * @return
	 */
	public List<CourseRecordVO> findSyllabus(Date syllabus, long teacherId) {
		Date weekOfMonday = DateUtils.parse(DateUtils.getMonday(syllabus));
		Date weekOfSunday = DateUtils.getDayOfEndTime(DateUtils.parse(DateUtils.getSunday(syllabus))).getTime();

		// 接收周一至周五课程数据（sql按照时间排序）
		List<CourseRecord> courseRecords = teacherSchoolDao.selectCourseRecord(teacherId, weekOfMonday, weekOfSunday);
		// 接收每一天的课程
		Map<String, List<CourseRecord>> courseOfDays = new HashMap<String, List<CourseRecord>>();
		// 时间序列化
		SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
		for (CourseRecord courseRecord : courseRecords) {
			// 获取星期几
			String courseTime = dateFm.format(courseRecord.getBeginDate());
			if (courseOfDays.containsKey(courseTime)) {
				courseOfDays.get(courseTime).add(courseRecord);
			} else {
				List<CourseRecord> courseTemps = new ArrayList<CourseRecord>();
				courseTemps.add(courseRecord);
				courseOfDays.put(courseTime, courseTemps);
			}
		}
		List<CourseRecordVO> courseRecordVOs = new ArrayList<CourseRecordVO>();
		String[] week = { "星期一", "星期二", "星期三", "星期四", "星期五"};
		for (int i = 0; i < week.length; i++) {
			CourseRecordVO courseRecordVO = new CourseRecordVO();
			courseRecordVO.setDayOfWeek(week[i]);
			if (courseOfDays.containsKey(week[i])) {
				courseRecordVO.setCourseRecords(courseOfDays.get(week[i]));
			}
			courseRecordVOs.add(courseRecordVO);

		}
		return courseRecordVOs;
	}
	
	/**
	 * 获取班级列表
	 * @param teacherId
	 * @return
	 */
	public List<ClassSimple> findClassListByTeacher(long teacherId) {
		return schoolTeacherClassDao.selectClassByTeacher(teacherId);
	}
	/**
	 * 获取班级列表
	 * @param teacherId
	 * @return
	 */
	public List<ClassSimple> findClassListByPrincipal(long principalId) {
		//校长ID==学校Id;
		return schoolTeacherClassDao.selectClassBySchool(principalId);
	}
}
