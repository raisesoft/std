package com.cd.bbh.school.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.school.app.dao.SchoolFeedbackDao;
import com.cd.bbh.school.app.dao.SchoolTeacherClassDao;
import com.cd.bbh.school.app.vo.Feedback;
import com.cd.bbh.school.app.vo.FeedbackItemRecord;
import com.cd.bbh.school.app.vo.FeedbackItemVO;
import com.cd.bbh.school.app.vo.FeedbackRecord;
import com.cd.bbh.school.app.vo.FeedbackTemplate;
import com.cd.bbh.school.app.vo.FeedbackVO;

@Service
public class SchoolFeedbackService {

	@Autowired
	private SchoolTeacherClassDao stcd;
	@Autowired
	private SchoolFeedbackDao sfbd;
	
	/**
	 * 获取模板列表
	 * @param teacher
	 * @return
	 */
	public List<Feedback> findFeedbackTemplates(long teacherId) {
		//获取学校ID
		Long schoolId = stcd.selectSchoolIdByTeacher(teacherId);		
		return sfbd.selectFeedbackBySchool(schoolId);
	}
	
	/**
	 * 获取制定反馈模板
	 * @param templateId
	 * @return
	 */
	public FeedbackTemplate findFeedbackTemplate(long templateId) {
		
		FeedbackTemplate ft = new FeedbackTemplate();
		ft.setFeedBack(sfbd.selectFeedbackById(templateId));
		ft.setFeedBackItems(sfbd.selectFeedbackItemByFeedBackId(templateId));
		
		return ft;
	}
	
	/**
	 * 提交反馈分数
	 * @param feedbackId
	 * @param data
	 */
	public void createFeedbackItemRecord(String data, FeedbackRecord fr) {
		//=============================添加评论======================================
		
		if (fr.getTeacherComments() == null) {
			fr.setTeacherStat("N");
		} else {
			fr.setTeacherStat("Y");
		}
		if(fr.getParentComments() == null) {
			fr.setParentStat("N");
		} else {
			fr.setParentStat("Y");
		}
		sfbd.insertFeedbackRecord(fr);//添数据，注入Id
		//============================存入分数===================================
		List<FeedbackItemRecord> list = new ArrayList<FeedbackItemRecord>();
		String[] datas = data.split(",");
		for (String s : datas) {
			String[] dt = s.split(":");
			FeedbackItemRecord fir = new FeedbackItemRecord();
			fir.setFeedBackItemId(Long.parseLong(dt[0]));
			fir.setScore(Float.parseFloat(dt[1]));
			fir.setFeedbackId(fr.getId());
			if (fir.getScore() == 0) {
				fir.setStat("N");
			} else {
				fir.setStat("Y");
			}
			list.add(fir);
		}
		sfbd.insertFeedbackItemRecord(list);
	}
	
	/**
	 * 教师获取自己的反馈表单
	 * @param teacher
	 * @return
	 */
	public List<FeedbackVO> findFeedbackList(long teacher, Map<String, Object> search) {
		long start = 0L, num = (long) search.get("dataNum"), page = (long) search.get("page");
		//頁面處理
		if(num > 0) {
			start = num * (page - 1); 
			search.put("start", start);
		}
		//獲取反饋信息
		List<FeedbackVO> feedbacks = sfbd.selectFeedbackByTeacher(teacher, search);
		List<Long> ids = new ArrayList<Long>();
		for (FeedbackVO f : feedbacks) {
			ids.add(f.getId());
		}
		if(ids.size() == 0) {
			return null;
		}
		//獲取反饋選項信息
		List<FeedbackItemVO> feedbackItems = sfbd.selectFeedbackItemByTeacher(ids);
		//表單處理
		Map<Long, List<FeedbackItemVO>> itemMap = new HashMap<Long, List<FeedbackItemVO>>();
		for (FeedbackItemVO f : feedbackItems) {
			long feedbackId = f.getFeedbackId();
			if(itemMap.containsKey(feedbackId)) {
				itemMap.get(feedbackId).add(f);
			} else {
				List<FeedbackItemVO> fiList = new ArrayList<FeedbackItemVO>();
				fiList.add(f);
				itemMap.put(feedbackId, fiList);
			}
		}
		for (FeedbackVO feedbackVO : feedbacks) {
			long id = feedbackVO.getId();
			if(itemMap.containsKey(id)) {
				feedbackVO.setFeedBackItems(itemMap.get(id));
			}
		}
	
		return feedbacks;
	}
}

