package org.zywx.wbpalmstar.plugin.uexdevice;

import org.zywx.wbpalmstar.widgetone.uexdevice.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView view = new TextView(this);
		String text = getString(R.string.plugin_uexdevice_teststring);
		// view.setText("TestActivity start success!!!!!");
		view.setText(text);
		view.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));
		setContentView(view);
	}

}
