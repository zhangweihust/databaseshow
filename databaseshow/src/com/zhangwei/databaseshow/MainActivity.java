package com.zhangwei.databaseshow;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tv_info = (TextView) findViewById(R.id.tv_info);
		
		/*String[] cmd = {"ps"};*/
		String package_path = this.getFilesDir().getAbsolutePath();
		//String package_path = "/data/data/com.guosen.android";
		String bin =  package_path + "/sqlite3";
		
		CmdHelper.CopyAssets((Context)this, package_path + "/");
		
		String[] cmds = new String[2];
		cmds[0] = "chmod 777 " + bin ;
		cmds[1] = "echo \"select * from guosenparam;\" | " + bin + " /data/data/com.guosen.android/databases/guosen";
		Log.i(TAG, cmds[0] + "\n" + cmds[1] + "\n");
		
		try {
			String result = CmdHelper.run(cmds, /*"/system/bin"*/ package_path);
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
