package com.cd.bbh.parent.mine.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cd.bbh.parent.mine.vo.MediaVO;

@Repository
public interface MediaDao {

	int addMedia(List<MediaVO> medias);

}
