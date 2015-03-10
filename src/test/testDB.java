package test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jt.appservice.JtService;
import com.jt.pojo.WorkOrder;
import com.jt.pojo.WorkOrder.WorkOrderImpl;
import com.jt.pojo.WorkRequest;
import com.jt.pojo.WorkRequest.WorkRequestImpl;
import com.tgb.lk.ahibernate.util.MyDBHelper;

import project.config.Config;
import project.util.MyLog;
import project.util.sqldb.DBHelper;
import project.util.sqldb.dao.Student;
import project.util.sqldb.dao.Teacher;
import project.util.sqldb.dao.impl.StudentDaoImpl;
import project.util.sqldb.dao.impl.TeacherDaoImpl;
import android.app.Activity;

public class testDB {//只有int 才能自增长
	public static void testworkorder(Activity act){
		
		//int version = new DBHelper(act).getWritableDatabase().getVersion();
		//Config.DBVERSION=version+1;
		Object retworkworder = null;
		try {
			retworkworder = JtService.findAssignedWorkOrder(Test.sid,	Test.username, WorkOrder.已到达);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		WorkOrder[] wos = (WorkOrder[]) retworkworder;
		
		WorkOrder wd = wos[0];
		wd.setFetchTime(new Date());
		wd.setLocalDBTime(new Date());
		int id = wd.getSysid();
		wd.setWorkRequestID(wd.getWorkRequest().getSysid());
		WorkOrderImpl wdDao = new WorkOrderImpl(act);
		long ret = wdDao.insert(wd,false);
		WorkRequestImpl wrDao = new WorkRequestImpl(act);
		long wrid = wrDao.insert(wd.getWorkRequest(),false);
		wd = wdDao.get(id);
		WorkRequest wr = wrDao.get((int) wrid);
		wd.setWorkRequest(wr);
		MyLog.Log(wd);
		
	}
	
	public static void testdb(Activity act) {
		TeacherDaoImpl teacherDao = new TeacherDaoImpl(act);
		StudentDaoImpl studentDao = new StudentDaoImpl(act);
		teacherDao.getDbHelper().getReadableDatabase().getVersion();
		// 添加
		Teacher teacher = new Teacher();
		teacher.ismachineok=true;//无法保存
		//teacher.setObjectValue();
		//teacher.setName("米老师");
		//teacher.setAge(null);//
		teacher.setFetchTime(new Date());//两层派生，无法保存
		teacher.reservetime = new Date();//
		teacher.reserve = new Date();//
		teacher.setTitle("教授");
		//teacher.setId((int)Math.round(10000*Math.random()));

		Long teacherId = teacherDao.insert(teacher);
		MyLog.Assert(teacherId>0, "Insert 出错");
		MyLog.Log(teacher);
		teacher = teacherDao.get( teacherId.intValue());
		MyLog.Log(teacher);
		//List<Teacher> ts = teacherDao.find();
		Student student1 = new Student();
		student1.setName("lk");
		student1.setAge(26);
		student1.setClasses("五");
		student1.setTeacherId(teacherId.intValue());
		Long studentId1 = studentDao.insert(student1);

		Student student2 = new Student();
		student2.setName("cls");
		student2.setAge(26);
		student2.setClasses("五");
		student2.setTeacherId(teacherId.intValue());
		Long studentId2 = studentDao.insert(student2);

		Student student3 = new Student();
		student3.setName("lb");
		student3.setAge(27);
		student3.setClasses("五期");
		student3.setTeacherId(teacherId.intValue());
		Long studentId3 = studentDao.insert(student3);

		Student student4 = new Student();
		student4.setId(1111);
		student4.setName("李坤");
		student4.setAge(26);
		student4.setClasses("五期提高班");
		student4.setTeacherId(teacherId.intValue());
		// 上面的示例中insert(entity)方法Id会自增.
		// 使用insert(entity,false)这样方式可以插入有固定Id的数据.
		Long studentId4 = studentDao.insert(student4, false);
		System.out.println("插入数据时可以不让主键自增,插入指定Id的数据值为=====" + studentId4);
		// 查询
		// 方式1:根据Id查询单个对象
		// 结果:student1Student [id=1, name=lk,age=26,teacherId=1, classes=五]
		Student student5 = studentDao.get(studentId1.intValue());
		System.out.println("student4" + student5);

		// 方式2:查询出表中的所有记录
		// 执行结果如下:
		// list1:Student [id=1, name=lk,age=26,teacherId=1, classes=五]
		// list1:Student [id=2, name=cls,age=26,teacherId=1, classes=五]
		// list1:Student [id=3, name=lb,age=27,teacherId=1, classes=五期]
		List<Student> list1 = studentDao.find();
		for (Student student : list1) {
			System.out.println("list1:" + student);
		}

		// 方式3:限制条件查询和查询结果
		// 执行结果:list2:Student [id=2, name=cls,age=0,teacherId=0, classes=null]
		List<Student> list2 = studentDao.find(new String[] { "id", "name" },
				" id = ? ", new String[] { studentId2.toString() }, null, null,
				null, null);
		for (Student student : list2) {
			System.out.println("list2:" + student);
		}

		// 方式4:使用sql查询出结果,此种方式是2,3,4中最灵活的.
		// 执行结果:
		// list3:Student [id=2, name=cls,age=26,teacherId=1, classes=五]
		// list3:Student [id=3, name=lb,age=27,teacherId=1, classes=五期]
		List<Student> list3 = studentDao.rawQuery(
				"select * from t_student where id in (?,?) ", new String[] {
						studentId2.toString(), studentId3.toString() });
		for (Student student : list3) {
			System.out.println("list3:" + student);
		}
		// 方式4:使用模糊查询.
		List<Student> list = studentDao.rawQuery(
				"select * from t_student t where t.classes like ?",
				new String[] { "%五%" });
		System.out.println(list.size());

		// 方式4进阶:如果想查询出米老师的学生,可以这样实现:
		// 执行结果:
		// list4:Student [id=1, name=lk,age=26,teacherId=1, classes=五]
		// list4:Student [id=2, name=cls,age=26,teacherId=1, classes=五]
		// list4:Student [id=3, name=lb,age=27,teacherId=1, classes=五期]
		List<Student> list4 = studentDao
				.rawQuery(
						"select s.* from t_student s join t_teacher t on s.teacher_id = t.id where t.name= ? ",
						new String[] { "米老师" });
		for (Student student : list4) {
			System.out.println("list4:" + student);
		}

		// 方式5:我只想知道姓名和年龄,查询得到List<Map<String,String>>形式.只查2个字会比查询所有字段并封装为对象效率高吧,尤其字段值很多时我们的手机更喜欢这种方式哦.
		// 结果:
		// listMap1: name:lk;age:26
		// listMap1: name:cls;age:26
		// listMap1: name:lb;age:27
		List<Map<String, String>> listMap1 = studentDao.query2MapList(
				"select name,Age from t_student ", null);
		for (Map<String, String> map : listMap1) {
			// 查询的List中的map以查询sql中的属性值的小写形式为key,注意是小写形式哦.
			System.out.println("listMap1: name:" + map.get("name") + ";age:"
					+ map.get("age"));
		}

		// 方式5进阶:我想知道前2名学生的姓名和班主任姓名,这种方式是不是超灵活啊,用其他的方式查询都没这种方式好用吧,哈哈.
		// 结果:
		// listMap2: student_name:lk;teacher_name:米老师
		// listMap2: student_name:cls;teacher_name:米老师
		List<Map<String, String>> listMap2 = studentDao
				.query2MapList(
						"select s.name sname,t.name tname from t_student s join t_teacher t on s.teacher_id = t.id limit ? ",
						new String[] { "2" });
		for (Map<String, String> map : listMap2) {
			System.out.println("listMap2: student_name:" + map.get("sname")
					+ ";teacher_name:" + map.get("tname"));
		}

		// 更新
		// 结果: Student [id=1, name=李坤,age=26,teacherId=1, classes=五期]
		student1 = studentDao.get(studentId1.intValue());
		student1.setName("李坤");
		student1.setClasses("五期");
		studentDao.update(student1);
		System.out.println(student1);

		// 删除:支持单个id删除,也支持多个id同时删除哦.
		studentDao.delete(studentId1.intValue());
		studentDao.delete(new Integer[] { studentId2.intValue(),
				studentId3.intValue() });
		studentDao.delete(1111);

		// 支持执行sql语句哦.
		teacherDao.execSql("insert into t_teacher(name,age) values('米教授',50)",
				null);

	}

}
