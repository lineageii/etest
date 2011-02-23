package net.jptest.jlpt.config;

import org.apache.commons.configuration.Configuration;

@SuppressWarnings("unchecked")
public class Config {
	private static final Configuration config = new ConfigUtil("config.xml").geConfig();

	/** 日语报名地址 */
	public static final String URL = config.getString("url");
	/** 数据库名称 */
	public static final String DB = config.getString("db");
	/** 测试/实际 */
	public static final Object TEST_OR_REAL = config.getString("testOrReal");
	/** 数据库URL 默认:localhost */
	public static final String DB_ADDRESS = config.getString("dbAddress");
	/** 数据库端口 */
	public static final String DB_PORT = config.getString("dbPort");
	/** 数据库用户名 */
	public static final String DB_USER = config.getString("dbUser");
	/** 数据库密码 */
	public static final String DB_PASSWORD = config.getString("dbPassword");
	/** 线程数  */
	public static final int THREAD_POOL_NUM = config.getInt("threadPoolNum");
	/** 报名批处理结束时间 */
	public static final String END_TIME = config.getString("endTime");
	/** 用户新密码 */
	public static final String NEW_PASSWORD = config.getString("newPassword");
	/** 报名开始等级 */
	public static final int START_LEVEL = config.getInt("startLevel");
	/** 是否更新用户状态到riyubaomin，实际报名的时候设为false，报名结束线程调低后，设为true。 */
	public static final boolean IS_UPDATE_USER = config.getBoolean("isUpdateUser");
	
	public static final String REG_UID_PREX = config.getString("regUidPrex");
	
	public static final String REG_PROVINCE = config.getString("regProvince");
	
	public static final String REG_LEVEL = config.getString("regLevel");
	
	public static final int REG_START = config.getInt("regStart");
	
	public static final int REG_END = config.getInt("regEnd");
	
	/** 用户注册常驻批处理运行间隔时间,配置文件未设定默认值为100秒 */
	public static final long REG_INTERVAL = config.getLong("regInterval", 100);
	
	private Config() {

	}
}
