package kr.or.bit.ainboard.utils;

import java.lang.reflect.Field;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StringUtils {
	//제목 글자수 자르기
   public static String titleSubString(String title) {
	   return title.substring(0, 10);
   }
   
   //댓글 비동기할 때 list를 JSON형식으로 파싱 (String이니까 ..... (몬소리지))
   //이거를 DB에서 List로 받아오잖아 응 받아와가지고 그걸 이제 비동기로 데이터를 가져오려는데
   //자바스크립트에서 쓸 때 나는 JSON형식으로 받고싶었써
   //String JSON형식으로 하려면 "" 이거 붙여야대니까
   //구래서 객체를 받아 (제네릭으로다가 받아따) -> DTO형식 ㄷㅏ 돼
   //이제 이거를 거기있는 필드값있자나 String int 이론고
   //그런거(변수명)를 이제 스트링으로 바꿔서 변수명 설정해노코
   public <T extends Object> String listParseToJavascriptArray(List<T> list, T object) {
       
       //객체 변수명 문자열로 담기
       List<String> fieldNames = new ArrayList<String>();
       
       
       //변수명설정
       for(Field field : object.getClass().getDeclaredFields()) {
           String str = field.toString();
           fieldNames.add(str.substring(str.lastIndexOf('.') + 1));
       }
       
       // 파싱
       //대괄호로 시작
       StringBuilder parsedString = new StringBuilder();
       parsedString.append("[");
       
       try {
           //for문 원래 있던 리스트 수만큼 돌리기
           for(int i = 0; i < list.size(); i++) {
        	 //객체 (중괄호로 시작)
               parsedString.append("{");
               
               for(int j = 0; j < fieldNames.size(); j++) {
            	   //이게 원래 그 객체에 담겨져있던 변수 값 (데이터)
                   Field obj = list.get(i).getClass().getDeclaredField(fieldNames.get(j));
                   //변수명(String)
                   Object key = obj.getName();
                   // Object value = obj.get(list.get(i));
                   // 원래 못들고오눈뎅 .. ㅎ.. 이제 거기에 프라이빗 접속할 수 있도록 바꿔주고
                   obj.setAccessible(true);
                   //진챠 값 들고온고지
                   Object value = obj.get(list.get(i));
                   
                   //더블코테이션
                   parsedString.append("\"")
                   .append(key)
                   .append("\"")
                   .append(":")
                   .append("\"")
                   .append(value)
                   .append("\"");
                   
                   
                   if(j == fieldNames.size() - 1) {
                       // nothing
                       
                   } else {
                       parsedString.append(",");
                   }
                   
               } // j for end
               
               if(i == list.size() - 1) {
                   parsedString.append("}");
               } else {
                   parsedString.append("},");
               }
               
           }// i for end
       
       } catch(Exception e) {
           e.printStackTrace();
       }
       
       parsedString.append("]");
       
       return parsedString.toString();
   }
   
   public <T extends Object> JSONArray listParseToJsonArray(List<T> list, T object) {
		// 객체 변수명 문자열로 담기
	       List<String> fieldNames = new ArrayList<String>();
	       
	       for(Field field : object.getClass().getDeclaredFields()) {
	           String str = field.toString();
	           fieldNames.add(str.substring(str.lastIndexOf('.') + 1));
	       }
	       
	       // 파싱?
	       
	       JSONArray jsonArr = new JSONArray();
	       
	       try {
	           
	           for(int i = 0; i < list.size(); i++) {
	        	   JSONObject jsonObj = new JSONObject();
	               for(int j = 0; j < fieldNames.size(); j++) {
	                   Field obj = list.get(i).getClass().getDeclaredField(fieldNames.get(j));
	                   obj.setAccessible(true);
	                   String key = obj.getName();
	                   Object value = obj.get(list.get(i));
	                   
	                   if(value instanceof Date) {
	                	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                	   sdf.format(value);
	                	   value = value.toString();
	                   }
	                   
	                   jsonObj.put(key, value);
	                   
	               } // j for end
	               jsonArr.add(jsonObj);
	               
	           }// i for end
	       
	       } catch(Exception e) {
	           e.printStackTrace();
	       }
	       
	       
	       return jsonArr;
	}
   
   
}
