package controllers;

import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.reflect.jdk.JdkReflector;

public class DBHelp {
	private static final String DB_NAME = "ExpressDB.data";
	private EmbeddedObjectContainer mDB;

	public void open() {
		EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
		configuration.common().reflectWith(new JdkReflector(this.getClass().getClassLoader()));
		mDB = Db4oEmbedded.openFile(configuration, DB_NAME);
	}

	public void addEmployee(String account, String password) {
		mDB.store(new Employee(account, password));
		mDB.commit();
	}

	public void addCommitBag(String weight, String toPerson, String toLocation, String toMobile, String fromPerson,
			String fromLocation, String fromMobile, String price) {
		ExpresstationInfo info = new ExpresstationInfo();
		info.setWeight(weight);
		info.setToPerson(toPerson);
		info.setToLocation(toLocation);
		info.setToMobile(toMobile);
		info.setFromPerson(fromPerson);
		info.setFromLocation(fromLocation);
		info.setFromMobile(fromMobile);
		info.setPrice(price);
		mDB.store(info);
		mDB.commit();
	}

	public boolean employeeCheck(String account, String password) {
		ObjectSet<Employee> objectSet = mDB.queryByExample(new Employee(account, password));
		if (objectSet.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateState(String id, int state) {
		ExpresstationInfo info = new ExpresstationInfo();
		info.setId(id);
		ObjectSet<ExpresstationInfo> objectSet = mDB.queryByExample(info);
		if (objectSet.hasNext()) {
			ExpresstationInfo expresstationInfo = objectSet.next();
			expresstationInfo.setState(state);
			mDB.store(expresstationInfo);
			mDB.commit();
			return true;
		} else {
			return false;
		}
	}

	/**获取已经派送往该小站的快件*/
	public ObjectSet<ExpresstationInfo> getDespatchBags(String locales_id, String station_id) {
		ExpresstationInfo info = new ExpresstationInfo();
		info.setState(ExpresstationInfo.state_despatch);
		ObjectSet<ExpresstationInfo> objectSet = mDB.queryByExample(info);
		return objectSet;
	}
	
	/**获取小站已揽收的快件*/
	public ObjectSet<ExpresstationInfo> getAcceptBags(String locales_id, String station_id) {
		ExpresstationInfo info = new ExpresstationInfo();
		info.setState(ExpresstationInfo.state_accept);
		ObjectSet<ExpresstationInfo> objectSet = mDB.queryByExample(info);
		return objectSet;
	}
	
	/**获取小站剩余的快件*/
	public ObjectSet<ExpresstationInfo> getLeftBags(String locales_id, String station_id) {
		ExpresstationInfo info = new ExpresstationInfo();
		info.setState(ExpresstationInfo.state_in_station);
		ObjectSet<ExpresstationInfo> objectSet = mDB.queryByExample(info);
		return objectSet;
	}

	public void close() {
		mDB.close();
	}
}
