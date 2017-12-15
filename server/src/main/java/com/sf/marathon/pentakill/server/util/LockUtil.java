package com.sf.marathon.pentakill.server.util;

import java.util.HashSet;
import java.util.Set;

public class LockUtil {

	private static final Set<String> set = new HashSet<>();

	private LockUtil() {

	}

	public static synchronized boolean lock(String key) {
		if (set.contains(key)) {
			return false;
		} else {
			set.add(key);
			return true;
		}
	}

	public static synchronized void unLock(String key) {
		set.remove(key);
	}
}
