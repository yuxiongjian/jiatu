package test;

import org.alexd.jsonrpc.JSONRPCException;

import project.config.DebugSetting;
import project.util.ProjectLocationListener;
import project.util.zxing.act.CaptureActivity;
import project.util.zxing.camera.CameraManager;





import junit.framework.TestResult;
import junit.framework.TestSuite;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.test.AndroidTestCase;
import android.test.AndroidTestRunner;
import android.test.InstrumentationTestCase;
import android.test.InstrumentationTestRunner;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.jt.AppLoginActivity;
import com.jt.R;
import com.jt.act.SaveMachineLocActivity;
import com.jt.appservice.JtService;

public class jitaoTest extends InstrumentationTestCase  {
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#createResult()
	 */
	

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#runTest()
	 */
	

	private TextView text;
	private Button button;

	public jitaoTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	
	@Override
    protected void setUp()  {
        try {
            super.setUp();
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent();
        ProjectLocationListener.mCtx= this.getInstrumentation().getContext();
        intent.setClassName("com.jt", SaveMachineLocActivity.class.getName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        SaveMachineLocActivity sample = (SaveMachineLocActivity) getInstrumentation().startActivitySync(intent);
       // CameraManager.isLandscape = false;
        DebugSetting.debug=DebugSetting.DEBUG.OUTERDEBUG;
    }
	
	protected void setUp1()  {
        try {
            super.setUp();
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent();
        intent.setClassName("com.jt", AppLoginActivity.class.getName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppLoginActivity sample = (AppLoginActivity) getInstrumentation().startActivitySync(intent);
        text = (TextView) sample.findViewById(R.id.username);
        button = (Button) sample.findViewById(R.id.sign_in_button);
        DebugSetting.debug=DebugSetting.DEBUG.OUTERDEBUG;
    }
  
    /*
     * 垃圾清理与资源回收
     * @see android.test.InstrumentationTestCase#tearDown()
     */
    @Override
    protected void tearDown()  {
    	//AppLoginActivity.;
        try {
            super.tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    /*
     * 活动功能测试
     */
    public void testActivity() throws Exception {
        Log.v("testActivity", "test the Activity");
        //SystemClock.sleep(1500);
        Test.testCheckValid(getInstrumentation().getContext());
       // Test.testSaveMobile();
       // getInstrumentation().runOnMainSync(new PerformClick(button));
        SystemClock.sleep(3000);
        //assertEquals("Hello Android", text.getText().toString());
    }
  
    /*
     * 模拟按钮点击的接口
     */
    private class PerformClick implements Runnable {
        Button btn;
        public PerformClick(Button button) {
            btn = button;
        }
  
        public void run() {
            btn.performClick();
        }
    }
  
    /*
     * 测试类中的方法
     */
    public void testAdd() throws Exception{
        String tag = "testAdd";
        Log.v(tag, "test the method");
       
    }

	public TestResult run(){
	
	//JtService.setServiceUrl("http://app.jitoa.com.cn/api/jsonrpc/mobile");
		JtService.setServiceUrl("http://app.jitoa.com.cn/api/jsonrpc/mobile");
	try {
		JtService.Login("99000458450043", "123456");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JSONRPCException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return new TestResult();
	}
	

}
