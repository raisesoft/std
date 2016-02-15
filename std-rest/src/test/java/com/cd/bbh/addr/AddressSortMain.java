package com.cd.bbh.addr;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AddressSortMain {

	private static Logger logger = LoggerFactory.getLogger(AddressSortMain.class);
	private static Map<String, Set<JsonNode>> value = new TreeMap<String, Set<JsonNode>>();

	public static void main(String[] args) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(new File("C:/Users/aowin/works/24技术实现/源码管理/java/bbh-wsapi/src/test/resources/areas.json"));
		for (JsonNode jsonNode : node) {
			if (jsonNode.has("sub")) {
				getLastParent(jsonNode, jsonNode.get("sub"));
			}
		}
		for (Map.Entry<String, Set<JsonNode>> entry : value.entrySet()) {
			logger.debug(entry.toString());
//			System.out.println(entry);
		}
	}

	public static void getLastParent(JsonNode parent, JsonNode childs) {

		if (childs.isArray()) {
			for (JsonNode child : childs) {
				if (child.has("sub")) {
					getLastParent(child, child.get("sub"));
				} else {
					setValue(parent);
				}
			}
		} else {
			if (childs.has("sub")) {
				getLastParent(childs, childs.get("sub"));
			} else {
				setValue(parent);
			}
		}
	}

	public static void setValue(JsonNode node) {
		String key = "\"" + node.get("letter").textValue().substring(0,1) + "\"";
		if (value.containsKey(key)) {
			value.get(key).add(node);
		} else {
			Set<JsonNode> nodes = new HashSet<JsonNode>();
			nodes.add(node);
			value.put(key, nodes);
		}
	}
}
