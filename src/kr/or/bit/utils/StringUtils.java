package kr.or.bit.utils;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Set;

public class StringUtils {
	public static boolean isNullOrEmpty(String str) {
		return (str == null || str.equals("")) ? true : false;
	}

	public static String titleSubString(String title) {
		return title.substring(0, 10);
	}

	/**
	 * 자바 객체 리스트형식을 JSON문자열로 파싱
	 * 
	 * @param <T>
	 * @param list
	 * @param object
	 * @return
	 */
	public <T extends Object> String listParseToJavascriptArray(List<T> list, T object) {
		// 객체 변수명 문자열로 담기
		List<String> fieldNames = new ArrayList<String>();

		for (Field field : object.getClass().getDeclaredFields()) {
			String str = field.toString();
			fieldNames.add(str.substring(str.lastIndexOf('.') + 1));
		}

		// 파싱?
		StringBuilder parsedString = new StringBuilder();
		parsedString.append("[");

		try {

			for (int i = 0; i < list.size(); i++) {
				parsedString.append("{");
				for (int j = 0; j < fieldNames.size(); j++) {
					Field obj = list.get(i).getClass().getDeclaredField(fieldNames.get(j));
					Object key = obj.getName();
					// Object value = obj.get(list.get(i));
					obj.setAccessible(true);
					Object value = obj.get(list.get(i));

					parsedString.append("\"").append(key).append("\"").append(":").append("\"").append(value)
							.append("\"");

					if (j == fieldNames.size() - 1) {
						// nothing

					} else {
						parsedString.append(",");
					}

				} // j for end

				if (i == list.size() - 1) {
					parsedString.append("}");
				} else {
					parsedString.append("},");
				}

			} // i for end

		} catch (Exception e) {
			e.printStackTrace();
		}

		parsedString.append("]");

		return parsedString.toString();
	}

	public <T extends Object> JSONArray listParseToJsonArray(List<T> list, T object) {
		// 객체 변수명 문자열로 담기
		List<String> fieldNames = new ArrayList<String>();

		for (Field field : object.getClass().getDeclaredFields()) {
			String str = field.toString();
			fieldNames.add(str.substring(str.lastIndexOf('.') + 1));
		}

		// 파싱?

		JSONArray jsonArr = new JSONArray();

		try {

			for (int i = 0; i < list.size(); i++) {
				JSONObject jsonObj = new JSONObject();
				for (int j = 0; j < fieldNames.size(); j++) {
					Field obj = list.get(i).getClass().getDeclaredField(fieldNames.get(j));
					obj.setAccessible(true);
					String key = obj.getName();
					Object value = obj.get(list.get(i));

					if (value instanceof Date) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						sdf.format(value);
						value = value.toString();
					}

					jsonObj.put(key, value);

				} // j for end
				jsonArr.add(jsonObj);

			} // i for end

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonArr;
	}

	public StringBuilder setSearchCondition(Map<String, String> searchCondition) {
		StringBuilder searchBuilder = new StringBuilder();

		if (!StringUtils.isNullOrEmpty(searchCondition.get("제목"))) {
			searchBuilder.append(" and QNA_TITLE like ? ");
		}

		if (!StringUtils.isNullOrEmpty(searchCondition.get("내용"))) {
			searchBuilder.append(" and QNA_CONTENT like ? ");
		}

		if (!StringUtils.isNullOrEmpty(searchCondition.get("작성자"))) {
			searchBuilder.append(" and NICKNAME like ? ");
		}

		if (!StringUtils.isNullOrEmpty(searchCondition.get("회원아이디"))) {
			searchBuilder.append(" and EMAIL like ? ");
		}

		return searchBuilder;
	}

	public void setPstmt(int count, Map<String, String> searchCondition, PreparedStatement pstmt) throws Exception {

		if (!StringUtils.isNullOrEmpty(searchCondition.get("제목"))) {
			pstmt.setString(++count, "%" + searchCondition.get("제목") + "%");
		}

		if (!StringUtils.isNullOrEmpty(searchCondition.get("내용"))) {
			pstmt.setString(++count, "%" + searchCondition.get("내용") + "%");
		}

		if (!StringUtils.isNullOrEmpty(searchCondition.get("작성자"))) {
			pstmt.setString(++count, "%" + searchCondition.get("작성자") + "%");
		}

		if (!StringUtils.isNullOrEmpty(searchCondition.get("회원아이디"))) {
			pstmt.setString(++count, "%" + searchCondition.get("회원아이디") + "%");
		}
	}

}
