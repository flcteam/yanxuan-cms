package com.flc.util;

/**
 * 获取唯一号
 * @author thinkpad
 *
 */
public class SNO {
	private static Object m_syncObj = new Object();
	private static int m_iSerail = 0;
	//private static long m_lStart = System.currentTimeMillis()/1000;

	public static String getSNO() {
		String sRet = null;
		synchronized (m_syncObj) {
			long lTime = System.currentTimeMillis()/1000;
			
			if(m_iSerail > 4000)
				m_iSerail = 0;
			else
				m_iSerail++;
				
			sRet =  Long.toHexString(lTime).toLowerCase() + Integer.toHexString(m_iSerail);
		}
		return sRet;
	}
	
	public static String getSNO(String servicename) {
		String sno = getSNO();
		
		return servicename + "_" + sno;
	}
}
