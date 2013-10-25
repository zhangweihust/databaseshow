package com.zhangwei.databaseshow;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.util.Log;

public class CmdHelper {
	private static final String TAG = "CmdHelper";

	/**
	 * 执行一个shell命令，并返回字符串值
	 * 
	 * @param cmd 命令名称&参数组成的数组（例如：{"/system/bin/cat", "/proc/version"}）
	 * @param workdirectory 命令执行路径（例如："system/bin/"）
	 * @return 执行结果组成的字符串
	 * @throws IOException
	 */
	public static synchronized String run(String cmd, String workdirectory) throws IOException {
		StringBuffer result = new StringBuffer();
		try {
			// 创建操作系统进程（也可以由Runtime.exec()启动）
			// Runtime runtime = Runtime.getRuntime();
			// Process proc = runtime.exec(cmd);
			// InputStream inputstream = proc.getInputStream();
			/*Process  process = Runtime.getRuntime().exec("su");*/

			ProcessBuilder builder = new ProcessBuilder("su"/*cmd*/);
			InputStream in = null;


			// 设置一个路径（绝对路径了就不一定需要）
			if (workdirectory != null) {
				// 设置工作目录（同上）
				builder.directory(new File(workdirectory));
				// 合并标准错误和标准输出
				builder.redirectErrorStream(true);

				Process process = builder.start();
				
			    //process.waitFor();
				
				DataOutputStream os = new DataOutputStream(process.getOutputStream());
				
			    os.writeBytes(cmd + "\n");
			    os.writeBytes("exit\n");
			    os.flush();

			    process.waitFor();

				in = process.getInputStream();
				byte[] re = new byte[1024];
				while (in.read(re) != -1) {
					result = result.append(new String(re));
				}
				
				Log.e(TAG, "result:" + result.toString());
			}

			if (in != null) {
				in.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result.toString();
	}

}
