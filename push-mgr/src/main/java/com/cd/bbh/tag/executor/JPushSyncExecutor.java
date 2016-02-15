package com.cd.bbh.tag.executor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.jpush.api.JPushClient;

import com.cd.bbh.tag.service.MessageTagService;
import com.cd.bbh.tag.vo.MessageUserTag;
import com.cd.bbh.tag.vo.PushData;

@Component("sync")
public class JPushSyncExecutor implements Executor {

	private static final Logger logger = LoggerFactory.getLogger(JPushSyncExecutor.class);

	@Autowired
	private JPushClient pushClient;

	@Autowired
	private MessageTagService messageTagService;

	public void execute() {
		List<MessageUserTag> userTags = messageTagService.searchMessageUserTag();
		if (userTags.size() <= 0) {
			logger.debug("No user tag updated. Exit executor...");
			return;
		}

		List<Long> deleteMessageUserTags = new ArrayList<Long>();
		List<MessageUserTag> updateMessageUserTags = new ArrayList<MessageUserTag>();

		updateJPushServer(userTags, deleteMessageUserTags, updateMessageUserTags);
		updateDatabase(deleteMessageUserTags, updateMessageUserTags);
	}

	private void updateJPushServer(List<MessageUserTag> users, List<Long> delete, List<MessageUserTag> update) {
		logger.debug("Begin sync user tag to JPush server...");
		Map<String, PushData> pushDataMap = new HashMap<String, PushData>();
		for (MessageUserTag user : users) {
			if (!pushDataMap.containsKey(user.getGroup())) {
				pushDataMap.put(user.getGroup(), new PushData());
			}
			PushData data = pushDataMap.get(user.getGroup());

			if (user.getSyncOperation().equals("D")) {
				delete.add(user.getId());
				data.addToRemove(user.getRegid());
			} else if (user.getSyncOperation().equals("A")) {
				update.add(user);
				data.addToAdd(user.getRegid());
			}
		}
		try {
			List<String> existTags = pushClient.getTagList().tags;
			for (Map.Entry<String, PushData> push : pushDataMap.entrySet()) {
				if (!existTags.contains(push.getKey())) {
					Set<String> addSet = push.getValue().getToAdd();
					Set<String> tagSet = new HashSet<String>();
					tagSet.add(push.getKey());
					for (String string : addSet) {
						pushClient.updateDeviceTagAlias(string, null, tagSet, null);
						break;
					}
				}
				if (push.getValue() != null && push.getValue().isValid()) {
					pushClient.addRemoveDevicesFromTag(push.getKey(), push.getValue().getToAdd(), push.getValue().getToRemove());
				}
			}
		} catch (Exception e) {
			delete.clear();
			update.clear();
			logger.error("Sync user tag failed!", e);
		}
	}

	private void updateDatabase(List<Long> delete, List<MessageUserTag> update) {
		logger.debug("Update local database...");
		if (delete.size() > 0) {
			messageTagService.deleteMessageUserTag(delete);
		}
		if (update.size() > 0) {
			messageTagService.updateMessageUserTag(update);
		}
	}
}
