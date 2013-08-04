package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.json.JSONArray;
import org.json.JSONObject;

import play.mvc.Controller;
import play.mvc.Result;

import com.db4o.ObjectSet;

public class Application extends Controller {

	public static Result index() {
		JsonNode jsonStr = request().body().asJson();
		System.out.println("jsonStr"+ jsonStr);
		JsonNode requestId = jsonStr.get("request_id");
		System.out.println("requestId"+ requestId);
		String responseStr = "";
		switch (requestId.asInt()) {
		case RequestIdDefine.REQUESTID_LOGIN:
			responseStr = handleLogin(jsonStr);
			break;
		case RequestIdDefine.REQUESTID_COMMIT_BAG:
			responseStr = handleCommit(jsonStr);
			break;
		case RequestIdDefine.REQUESTID_UPDTAE_STATE:
			responseStr = updateState(jsonStr);
		case RequestIdDefine.REQUESTID_GET_DESPATCH_BAGS:
			responseStr = getDespatchBags(jsonStr);
			break;
		case RequestIdDefine.REQUESTID_GET_ACCPET_BAGS:
			responseStr = getAcceptBags(jsonStr);
			break;
		case RequestIdDefine.REQUESTID_GET_LEFT_BAGS:
			responseStr = getLeftBags(jsonStr);
			break;
		default:
			break;
		}
		return ok(responseStr);
	}

	private static String handleLogin(JsonNode requset) {
		String account = requset.get("account").getTextValue();
		String password = requset.get("password").getTextValue();
		DBHelp dataBaseHelper = new DBHelp();
		dataBaseHelper.open();
		//dataBaseHelper.addEmployee("Employee", "123456");
		boolean isLogin = dataBaseHelper.employeeCheck(account, password);
		dataBaseHelper.close();
		return isLogin + "";
	}

	private static String handleCommit(JsonNode requset) {
		String weight = requset.get("weight").getTextValue();
		String toPerson = requset.get("toPerson").getTextValue();
		String toLocation = requset.get("toLocation").getTextValue();
		String toMobile = requset.get("toMobile").getTextValue();
		String fromPerson = requset.get("fromPerson").getTextValue();
		String fromLocation = requset.get("fromLocation").getTextValue();
		String fromMobile = requset.get("fromMobile").getTextValue();
		String price = requset.get("price").getTextValue();

		DBHelp dataBaseHelper = new DBHelp();
		dataBaseHelper.open();
		dataBaseHelper.addCommitBag(weight, toPerson, toLocation, toMobile, fromPerson, fromLocation, fromMobile, price);
		dataBaseHelper.close();
		return true + "";
	}
	
	private static String updateState(JsonNode requset) {
		String id = requset.get("id").getTextValue();
		int state = requset.get("state").getIntValue();
		DBHelp dataBaseHelper = new DBHelp();
		dataBaseHelper.open();
		boolean isSucc = dataBaseHelper.updateState(id, state);
		dataBaseHelper.close();
		return isSucc + "";
	}

	/**
	 * 检查区域id和小站id，根据id来获取数据
	 * @param requset
	 * @return
	 */
	private static String getDespatchBags(JsonNode requset) {
		String locales_id = requset.get("locales_id").getTextValue();
		String station_id = requset.get("station_id").getTextValue();
		DBHelp dataBaseHelper = new DBHelp();
		dataBaseHelper.open();
		ObjectSet<ExpresstationInfo> expresstationInfos = dataBaseHelper.getDespatchBags(locales_id, station_id);
		List<JSONObject> infoList = new ArrayList<JSONObject>();
		for (ExpresstationInfo expresstationInfo : expresstationInfos) {
			Map<String, String> info = new HashMap<String, String>();
			info.put("weight", expresstationInfo.getWeight());
			info.put("toPerson", expresstationInfo.getToPerson());
			info.put("toLocation", expresstationInfo.getToLocation());
			info.put("toMobile", expresstationInfo.getToMobile());
			info.put("fromPerson", expresstationInfo.getFromPerson());
			info.put("fromLocation", expresstationInfo.getFromLocation());
			info.put("fromMobile", expresstationInfo.getFromMobile());
			info.put("price", expresstationInfo.getPrice());
			infoList.add(new JSONObject(info));
		}
		JSONArray infos = new JSONArray(infoList);
		dataBaseHelper.close();
		return infos.toString();
	}
	
	/**
	 * 检查区域id和小站id，根据id来获取数据
	 * @param requset
	 * @return
	 */
	private static String getAcceptBags(JsonNode requset) {
		String locales_id = requset.get("locales_id").getTextValue();
		String station_id = requset.get("station_id").getTextValue();
		DBHelp dataBaseHelper = new DBHelp();
		dataBaseHelper.open();
		ObjectSet<ExpresstationInfo> expresstationInfos = dataBaseHelper.getAcceptBags(locales_id, station_id);
		List<JSONObject> infoList = new ArrayList<JSONObject>();
		for (ExpresstationInfo expresstationInfo : expresstationInfos) {
			Map<String, String> info = new HashMap<String, String>();
			info.put("weight", expresstationInfo.getWeight());
			info.put("toPerson", expresstationInfo.getToPerson());
			info.put("toLocation", expresstationInfo.getToLocation());
			info.put("toMobile", expresstationInfo.getToMobile());
			info.put("fromPerson", expresstationInfo.getFromPerson());
			info.put("fromLocation", expresstationInfo.getFromLocation());
			info.put("fromMobile", expresstationInfo.getFromMobile());
			info.put("price", expresstationInfo.getPrice());
			infoList.add(new JSONObject(info));
		}
		JSONArray infos = new JSONArray(infoList);
		dataBaseHelper.close();
		return infos.toString();
	}
	
	/**
	 * 检查区域id和小站id，根据id来获取数据
	 * @param requset
	 * @return
	 */
	private static String getLeftBags(JsonNode requset) {
		String locales_id = requset.get("locales_id").getTextValue();
		String station_id = requset.get("station_id").getTextValue();
		DBHelp dataBaseHelper = new DBHelp();
		dataBaseHelper.open();
		ObjectSet<ExpresstationInfo> expresstationInfos = dataBaseHelper.getLeftBags(locales_id, station_id);
		List<JSONObject> infoList = new ArrayList<JSONObject>();
		for (ExpresstationInfo expresstationInfo : expresstationInfos) {
			Map<String, String> info = new HashMap<String, String>();
			info.put("weight", expresstationInfo.getWeight());
			info.put("toPerson", expresstationInfo.getToPerson());
			info.put("toLocation", expresstationInfo.getToLocation());
			info.put("toMobile", expresstationInfo.getToMobile());
			info.put("fromPerson", expresstationInfo.getFromPerson());
			info.put("fromLocation", expresstationInfo.getFromLocation());
			info.put("fromMobile", expresstationInfo.getFromMobile());
			info.put("price", expresstationInfo.getPrice());
			infoList.add(new JSONObject(info));
		}
		JSONArray infos = new JSONArray(infoList);
		dataBaseHelper.close();
		return infos.toString();
	}
}
