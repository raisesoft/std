package com.cd.bbh.parent.mine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.parent.mine.dao.MineHomeDao;
import com.cd.bbh.parent.mine.model.MineHome;

@Service
public class MineHomeService {

	@Autowired
	private MineHomeDao mineHomeDao;

	/**
	 * 获取我的栏目内的基本内容
	 * 
	 * @param parentid
	 * @return
	 */
	public List<MineHome> findMineHome(Long parentid) {
		List<MineHome> mineHomes =  mineHomeDao.searchByParentId(parentid);
		for (MineHome mineHome : mineHomes) {
			String nickName = mineHome.getNickname();
			if(nickName == null || nickName.length() == 0) {
				mineHome.setNickname(mineHome.getName());
			}
		}
		return mineHomes;
	}
}
