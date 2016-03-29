package org.zywx.wbpalmstar.engine.universalex;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;

/**
 * 用于承载动态加载的插件相关资源管理
 * 
 * @author yipeng.zhang
 * @createdAt 2016年2月25日
 */
public class PluginContext extends ContextWrapper {
	public ClassLoader classLoader;
	public AssetManager assetManager;
	public Resources resources;
	public String packageName;

	public PluginContext(Context base) {
		super(base);
	}

	public PluginContext(Context base, ClassLoader classLoader,
			AssetManager assetManager, Resources resources, String packageName) {
		this(base);
		initPluginContext(classLoader, assetManager, resources, packageName);
	}

	private void initPluginContext(ClassLoader classLoader,
			AssetManager assetManager, Resources resources, String packageName) {
		this.classLoader = classLoader;
		this.assetManager = assetManager;
		this.resources = resources;
		this.packageName = packageName;
	}

	@Override
	public AssetManager getAssets() {
		return assetManager == null ? super.getAssets() : assetManager;
	}

	@Override
	public Resources getResources() {
		return resources == null ? super.getResources() : resources;
	}

	@Override
	public ClassLoader getClassLoader() {
		return classLoader == null ? super.getClassLoader() : classLoader;
	}

	@Override
	public String getPackageName() {
		return packageName == null ? super.getPackageName() : packageName;
	}

}
