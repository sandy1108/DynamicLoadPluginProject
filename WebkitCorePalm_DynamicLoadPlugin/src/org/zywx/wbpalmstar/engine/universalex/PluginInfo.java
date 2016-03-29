package org.zywx.wbpalmstar.engine.universalex;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

public class PluginInfo {
	/**
	 * 正常编译加载
	 */
	public final static int TYPE_STATIC = 0;
	/**
	 * dex动态加载方式
	 */
	public final static int TYPE_DEX = 1;
	/**
	 * apk动态加载方式
	 */
	public final static int TYPE_DYNAMIC = 2;

	public String uexName;
	public int loadType;
	public String version;
	public int buildVersion;

	public String packageName;
	public ClassLoader classLoader;
	public AssetManager assetManager;
	public Resources resources;
	public PackageInfo packageInfo;
}
