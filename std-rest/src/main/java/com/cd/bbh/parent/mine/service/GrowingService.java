package com.cd.bbh.parent.mine.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cd.bbh.common.Pageable;
import com.cd.bbh.common.utils.FileUploadUtils;
import com.cd.bbh.parent.mine.dao.ChildrenDao;
import com.cd.bbh.parent.mine.dao.GrowingDao;
import com.cd.bbh.parent.mine.vo.GrowingVO;
import com.cd.bbh.parent.mine.vo.MediaItemVO;

@Service
public class GrowingService {

	@Autowired
	private GrowingDao growingDao;

	@Autowired
	private ChildrenDao childrenDao;

	public List<GrowingVO> searchGrowingRecordByChildid(Long childid, Date searchDate, Pageable pageable) {
		return growingDao.searchGrowingRecordByChildid(childid, searchDate, pageable.getPage(), pageable.getPagesize());
	}

	public GrowingVO createGrowingRecord(GrowingVO grow, Long creatorid, MultipartFile[] files) {
		String relation = childrenDao.selectRelation(grow.getChildid(), creatorid);
		if (StringUtils.isNoneBlank(relation)) {
			grow.setCreator(relation);
		}
		growingDao.insertGrowingRecord(grow);
		if (files != null && files.length > 0) {
			String dataStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
			List<MediaItemVO> items = new ArrayList<MediaItemVO>();
			for (int i = 0; i < files.length; i++) {
				String mediaType = FileUploadUtils.checkFileType(files[i]);
				String filePath = FileUploadUtils.uploadFile(files[i], dataStr, creatorid.toString());
				MediaItemVO mediaItemVO = new MediaItemVO();
				mediaItemVO.setMediaPath(filePath);
				mediaItemVO.setMediaType(mediaType);
				if (mediaType.equals("A")) {
					mediaItemVO.setThumbnailPath(filePath.substring(0, filePath.lastIndexOf(".")) + ".png");
				}
				mediaItemVO.setGrowingid(grow.getGrowid());
				mediaItemVO.setItemCreatorid(creatorid);
				items.add(mediaItemVO);
			}
			growingDao.insertGrowMediaItems(items);
			grow.setMedias(items);
		}
		return grow;
	}

	public void createGrowingComment(Long growingid, String comments) {
		growingDao.updateGrowComments(growingid, comments);
	}
}
