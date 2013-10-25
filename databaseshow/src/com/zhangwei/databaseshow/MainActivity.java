package com.zhangwei.databaseshow;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tv_info = (TextView) findViewById(R.id.tv_info);
		
		/*String[] cmd = {"ps"};*/
		String cmd = "echo \"select * from guosenparam;\" | sqlite3 /data/data/com.guosen.android/databases/guosen";
		try {
			String result = CmdHelper.run(cmd, "/system/bin");
			tv_info.setText(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
