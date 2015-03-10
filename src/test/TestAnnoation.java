package test;

import java.util.TreeMap;

import project.annotation.map.PojoMap;
import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;
import project.pojo.Bpojo.KEYVALUE;
import project.pojo.Bpojo.ValuePosition;
import project.util.MyLog;

import com.jt.pojo.CustomerMachine;
import com.jt.pojo.MachLocate;

public class TestAnnoation {
	public static void test() {
		String[] items = {};

		MachLocate bj = new MachLocate();
		TreeMap<String, PojoMap> pm = bj.getFieldsAnnotation(PojoMap.class,
				null);
		MyLog.Log(pm);
		items = bj.getListItemValues();
		MyLog.Assert(items.length > 0, "");
		ValuePosition ret = bj.findListItemPositon(items[0], KEYVALUE.FIND_BY_VALUE);
		MyLog.Assert(ret.findOut!=null,"LIstItem value not found:"+items[0]);
		Bpojo ma = new CustomerMachine();
		TreeMap<String, PojoUI> tm = ma.getFieldsAnnotation(PojoUI.class, null);
		MyLog.Log(tm);

		ma.setObjectValue();
		PojoUI ret1 = ma.getFieldAnnotation("address");
		MyLog.Log(ret1);
		return;
	}
}
