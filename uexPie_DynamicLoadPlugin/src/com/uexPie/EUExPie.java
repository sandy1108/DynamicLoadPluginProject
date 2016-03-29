package com.uexPie;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.zywx.wbpalmstar.engine.EBrowserView;
import org.zywx.wbpalmstar.engine.universalex.EUExBase;
import org.zywx.wbpalmstar.engine.universalex.PluginInfo;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.uexPie.bean.PieBean;

public class EUExPie extends EUExBase {
    static String opID = "0";
	static final String functionName = "uexPie.loadData";
	static final String cbOpenFunName = "uexPie.cbOpen";
	private PieView pieView;
	public static final String TAG = "uexPie";

	private int startX = 0;
	private int startY = 0;
	public static int screenWidth = 0;
	public static int screenHeight = 0;
	
	private Map<String, View> map_activity;

    private static final String BUNDLE_DATA = "data";
    private static final int MSG_OPEN = 1;
    private static final int MSG_CLOSE = 2;
    private static final int MSG_SET_JSON_DATA = 3;

	public EUExPie(Context context, EBrowserView arg1, PluginInfo info) {
		super(context, arg1, info);
		map_activity = new HashMap<String, View>();
		EUExUtil.init(mPluginContext);
	}

	@Override
	protected boolean clean() {
		close(null);
		return false;
	}

    public void open(String[] params) {
        if (params == null || params.length < 1) {
            errorCallback(0, 0, "error params!");
            return;
        }
        Message msg = new Message();
        msg.obj = this;
        msg.what = MSG_OPEN;
        Bundle bd = new Bundle();
        bd.putStringArray(BUNDLE_DATA, params);
        msg.setData(bd);
        mHandler.sendMessage(msg);
    }

    private void openMsg(String[] params) {
		try {
			opID = params[0];
			if(map_activity.containsKey(opID)) {
			    return;
			}
			if (params[1].length() != 0) {
			    startX = (int) Double.parseDouble(params[1]);
			}
			if (params[2].length() != 0) {
			    startY = (int) Double.parseDouble(params[2]);
			}
			if (params[3].length() != 0) {
			    screenWidth = (int) Double.parseDouble(params[3]);
			}
			if (params[4].length() != 0) {
			    screenHeight = (int) Double.parseDouble(params[4]);
			}

			// pieView = new PieView(mContext);
			pieView = new PieView(mPluginContext);

//        if (0 == screenWidth || 0 == screenHeight) {
//            Display display = ((Activity) mContext).getWindowManager()
//                    .getDefaultDisplay();
//            screenWidth = display.getWidth();
//            screenHeight = display.getHeight();
//        }
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
			        screenWidth, screenHeight);
			lp.leftMargin = startX;
			lp.topMargin = startY;
			//addView2CurrentWindow(pieView, lp);
			map_activity.put(opID, pieView);

			loadData(opID);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void close(String[] params) {
        Message msg = new Message();
        msg.obj = this;
        msg.what = MSG_CLOSE;
        Bundle bd = new Bundle();
        bd.putStringArray(BUNDLE_DATA, params);
        msg.setData(bd);
        mHandler.sendMessage(msg);
    }

    private void closeMsg(String[] params) {
        if (!map_activity.isEmpty()) {
            Set<Entry<String, View>> entrySet = map_activity.entrySet();
            Iterator<Entry<String, View>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, View> entry = iterator.next();
                View view = entry.getValue();
                removeViewFromCurrentWindow(view);
            }
            map_activity.clear();
        }
    }


	private void addView2CurrentWindow(View child, RelativeLayout.LayoutParams parms) {
		int l = (int) (parms.leftMargin);
		int t = (int) (parms.topMargin);
		int w = parms.width;
		int h = parms.height;
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(w, h);
		lp.gravity = Gravity.NO_GRAVITY;
		lp.leftMargin = l;
		lp.topMargin = t;
		adptLayoutParams(parms, lp);
		mBrwView.addViewToCurrentWindow(child, lp);
	}
	
	public void loadData(String opID) {
		jsCallback(functionName, Integer.parseInt(opID), 0, 0);
		jsCallback(cbOpenFunName, Integer.parseInt(opID), 0, 0);
	}

    public void setJsonData(String[] params) {
        if (params == null || params.length < 1) {
            errorCallback(0, 0, "error params!");
            return;
        }
        Message msg = new Message();
        msg.obj = this;
        msg.what = MSG_SET_JSON_DATA;
        Bundle bd = new Bundle();
        bd.putStringArray(BUNDLE_DATA, params);
        msg.setData(bd);
        mHandler.sendMessage(msg);
    }

	public void setJsonDataMsg(String[] params) {
		try {
			JSONObject json = new JSONObject(params[0]);
			String jsonResult = json.getString("data");
			final List<PieBean> pieList = PieUtility.parseData(jsonResult);
            pieView.setData(pieList, screenWidth, screenHeight);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void onHandleMessage(Message message) {
        if(message == null){
            return;
        }
        Bundle bundle=message.getData();
        switch (message.what) {

            case MSG_OPEN:
                openMsg(bundle.getStringArray(BUNDLE_DATA));
                break;
            case MSG_CLOSE:
                closeMsg(bundle.getStringArray(BUNDLE_DATA));
                break;
            case MSG_SET_JSON_DATA:
                setJsonDataMsg(bundle.getStringArray(BUNDLE_DATA));
                break;
            default:
                super.onHandleMessage(message);
        }
    }
}
