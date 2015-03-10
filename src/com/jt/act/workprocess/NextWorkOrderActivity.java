package com.jt.act.workprocess;

import project.config.Config;
import android.content.Intent;
import android.os.Bundle;

import com.jt.pojo.ListInfo;
import com.jt.pojo.User;

public class NextWorkOrderActivity extends CheckHistoryActivity {

	/**
	 * 
	 */
	private static final long serialVersionUID = NextWorkOrderActivity.class
			.hashCode();

	/**
	 */
	public void onCreate(Bundle savedInstanceState) {

		ListInfo lf = new ListInfo();
		String url = Config.getUrls()[5];

		lf.setUrl(String.format("%s?sid=%s&userid=%s", url,
				User.cUser.getSid(), User.cUser.getUserid()));
		Intent _intent = getIntent();
		_intent.putExtra("" + ListInfo.serialVersionUID, lf);

		super.onCreate(savedInstanceState);// thy-learn must call first to init
											// user, so you can getAu()

	}

}
