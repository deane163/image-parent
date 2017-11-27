package com.xiaoshu.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DirMaker {

	private static final TimeZone tz2 = TimeZone.getTimeZone("ETC/GMT-8");
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/");
	
	static {
		sdf.setTimeZone(tz2);
	}

	/**
	 * Enhancement of java.io.File#createNewFile() Create the given file. If the
	 * parent directory don't exists, we will create them all.
	 * 
	 * @param file
	 *            the file to be created
	 * @return true if the named file does not exist and was successfully
	 *         created; false if the named file already exists
	 * @see java.io.File#createNewFile
	 * @throws IOException
	 */
	public static boolean createFile(File file) throws IOException {
		if (!file.exists()) {
			makeDir(file.getParentFile());
		}
		return file.createNewFile();
	}

	/**
	 * Enhancement of java.io.File#mkdir() Create the given directory . If the
	 * parent folders don't exists, we will create them all.
	 * 
	 * @see java.io.File#mkdir()
	 * @param dir
	 *            the directory to be created
	 */
	public static void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}

	/**
	 * get the image upload current path as 2017/09/21/19
	 * 
	 * @return
	 */
	public static String getCurrentPath() {

		return sdf.format(new Date());
	}
}
