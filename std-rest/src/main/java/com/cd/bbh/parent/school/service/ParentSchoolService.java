package com.cd.bbh.parent.school.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.Pageable;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.exception.ApplicationException;
import com.cd.bbh.parent.mine.vo.ChildStarVO;
import com.cd.bbh.parent.school.dao.ClassStarDao;
import com.cd.bbh.parent.school.dao.SchoolDao;
import com.cd.bbh.parent.school.vo.SchoolVO;

@Service
public class ParentSchoolService {

	@Autowired
	private SchoolDao schoolDao;

	@Autowired
	private ClassStarDao classStarDao;

	/**
	 * 获取好评最高的学校
	 * 
	 * @param pageable
	 * @return
	 */
	public List<SchoolVO> findStarSchool(Pageable pageable, String cityname, Float longtitude, Float latitude) {
		return schoolDao.selectStarSchool(cityname, longtitude, latitude, pageable.getPage(), pageable.getPagesize());
	}

	/**
	 * 根据学校ID查询学校
	 * 
	 * @param primeKey
	 * @return
	 */
	public SchoolVO findSchoolDetail(Long primeKey) {
		return schoolDao.selectSchool(primeKey);
	}

	public List<SchoolVO> findSchoolByName(Pageable pageable, String condition, String cityname) {
		return schoolDao.selectSchoolByName(cityname, null, null, condition, pageable.getPage(), pageable.getPagesize());
	}

	public List<ChildStarVO> findClassWeekStar(Long childid, Pageable pageable) {
		ChildStarVO star = classStarDao.searchMyChildWeekStar(childid);
		if (star == null) {
			throw new ApplicationException(ResultEnum.NO_CHILD_IN_SCHOOL_ERROR);
		}
		List<ChildStarVO> stars = new ArrayList<ChildStarVO>();
		List<ChildStarVO> temps = classStarDao.searchClassWeekStars(childid, pageable.getPage(), pageable.getPagesize() - 1);
		stars.add(star);
		stars.addAll(temps);
		return stars;
	}

	public List<ChildStarVO> findClassMonthStar(Long childid, Pageable pageable) {
		ChildStarVO star = classStarDao.searchMyChildMonthStar(childid);
		if (star == null) {
			throw new ApplicationException(ResultEnum.NO_CHILD_IN_SCHOOL_ERROR);
		}
		List<ChildStarVO> stars = new ArrayList<ChildStarVO>();
		List<ChildStarVO> temps = classStarDao.searchClassMonthStars(childid, pageable.getPage(), pageable.getPagesize() - 1);
		stars.add(star);
		stars.addAll(temps);
		return stars;
	}
}
