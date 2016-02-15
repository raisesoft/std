package com.cd.bbh.parent.mine.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cd.bbh.parent.mine.vo.GrowingVO;
import com.cd.bbh.parent.mine.vo.MediaItemVO;

@Repository
public interface GrowingDao {

	/**
	 * 查询成长记录数据
	 * 
	 * @param childid
	 * @param searchDate
	 * @param begin
	 * @param end
	 * @return
	 */
	List<GrowingVO> searchGrowingRecordByChildid(@Param("childid") Long childid, @Param("searchDate") Date searchDate, @Param("page") Integer page, @Param("pagesize") Integer pagesize);

	int insertGrowingRecord(GrowingVO growingVO);

	int insertGrowMediaItems(List<MediaItemVO> items);

	int updateGrowComments(@Param("growingid") Long growingid, @Param("comments") String comments);
}
