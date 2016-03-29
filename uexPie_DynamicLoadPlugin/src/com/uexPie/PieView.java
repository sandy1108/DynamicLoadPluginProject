package com.uexPie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.zywx.wbpalmstar.widgetone.uexpie.R;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.uexPie.bean.PieBean;

public class PieView extends FrameLayout {
	ChartView mChartView;
    private Context mContext;

	int fontSize[] = new int[] { 20, 20, 20, 20, 20 };

    public PieView(Context context) {
        super(context);
        this.mContext = context;
    }

	public void setData(List<PieBean> pieList,int screenWidth, int screenHeight){
		int centerX = screenWidth/2;
		int centerY = screenHeight/2;
		// int viewId = EUExUtil.getResLayoutID("plugin_uexpie_view");
		int viewId = R.layout.plugin_uexpie_view;
		Log.i("PieView", viewId + " plugin_uexpie_view");
		// LayoutInflater.from(mContext).inflate(viewId,
		// this, true);
		View pieView = LayoutInflater.from(mContext).inflate(viewId, null,
				false);
//		pieView.setTag("123");
		Log.i("PieView", pieView.getId() + " plugin_uexpie_view getID");
		addView(pieView);
		viewId = EUExUtil.getResIdID("plugin_uexpie_view_chartView");
		Log.i("PieView", viewId + " plugin_uexpie_view_chartView(dynamic)");
		viewId = R.id.plugin_uexpie_view_chartView;
		Log.i("PieView", viewId + " plugin_uexpie_view_chartView(R.id.plugin_uexpie_view_chartView)");
//		pieView = this.findViewWithTag("123");
		mChartView = (ChartView) pieView.findViewById(viewId);
		mChartView.setAntiAlias(true);
		mChartView.setCenter(new Point(centerX, centerY));
		mChartView.setStartAngle(270);
		
		int size = pieList.size();
		float percentSum = 0;
		for(int i=0;i<size;i++){
			PieBean pieBean = pieList.get(i);
			percentSum += Float.parseFloat(pieBean.getValue());
		}
		List<PieBean> resultList = dataReset(pieList);
		ArrayList<ChartProp> acps = mChartView.createCharts(size,screenWidth,screenHeight);
		for (int i = 0; i < size; i++) {
			ChartProp chartProp = acps.get(i);
			PieBean pieBean = resultList.get(i);
			chartProp.setColor(PieUtility.parseColor(pieBean.getColor()));
			chartProp.setPercent(Float.parseFloat(pieBean.getValue())/percentSum);
			chartProp.setValue(pieBean.getSubTitle());
			chartProp.setName(pieBean.getTitle());
		}
	}
	public List<PieBean> dataReset(List<PieBean> list){
		if(null==list || list.size()==0){
			return null;
		}
		List<PieBean> tempList = list;
		Collections.sort(tempList, new SortByAge());
		List<PieBean> pieList = new ArrayList<PieBean>();
		List<PieBean> evenList = new ArrayList<PieBean>();
		for(int i=0;i<tempList.size();i++){
			if(i%2==0){
				pieList.add(tempList.get(i));
			}else{
				evenList.add(tempList.get(i));
			}
		}
		for(int m=evenList.size()-1;m>=0;m--){
			pieList.add(evenList.get(m));
		}
		return pieList;
	}
	class SortByAge implements Comparator {
		 @Override
		public int compare(Object o1, Object o2) {
		  PieBean s1 = (PieBean) o1;
		  PieBean s2 = (PieBean) o2;
		  if (Float.parseFloat(s1.getValue()) > Float.parseFloat(s2.getValue())){
			  return 1;
		  }else if(Float.parseFloat(s1.getValue()) == Float.parseFloat(s2.getValue())){
			  return 0;
		  }else{
			  return -1;
		  }
		 }
		}
}
