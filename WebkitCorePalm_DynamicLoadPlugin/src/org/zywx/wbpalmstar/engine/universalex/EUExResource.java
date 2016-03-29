/*
 *  Copyright (C) 2014 The AppCan Open Source Project.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.

 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.zywx.wbpalmstar.engine.universalex;

import org.zywx.wbpalmstar.engine.EUtil;

import android.content.Context;
import android.content.res.Resources;

public class EUExResource {

	public String packageName;
	public Resources resources;
	public Context mContext;

    public static final String anim = "anim";
    public static final String animator = "animator";
    public static final String interpolator = "interpolator";
    public static final String menu = "menu";
    public static final String mipmap = "mipmap";
    public static final String array = "array";
    public static final String bool = "bool";
    public static final String stringArray = "string-array";
    public static final String attr = "attr";
    public static final String color = "color";
    public static final String dimen = "dimen";
    public static final String drawable = "drawable";
    public static final String id = "id";
    public static final String layout = "layout";
    public static final String raw = "raw";
    public static final String string = "string";
    public static final String style = "style";
    public static final String xml = "xml";
    public static final String styleable = "styleable";

	public void init(Context ctx) {
        packageName = ctx.getPackageName();
        resources = ctx.getResources();
        mContext = ctx.getApplicationContext();
    }

	public int getResDrawableID(String resName) {

        return resources.getIdentifier(resName, drawable, packageName);
    }

	public int getResLayoutID(String resName) {

        return resources.getIdentifier(resName, layout, packageName);
    }

	public int getResAnimID(String resName) {

        return resources.getIdentifier(resName, anim, packageName);
    }

	public int getResAnimatorID(String resName) {

        return resources.getIdentifier(resName, animator, packageName);
    }

	public int getResAttrID(String resName) {

        return resources.getIdentifier(resName, attr, packageName);
    }

	public int getResColorID(String resName) {

        return resources.getIdentifier(resName, color, packageName);
    }

	public int getResDimenID(String resName) {

        return resources.getIdentifier(resName, dimen, packageName);
    }

	public int getResIdID(String resName) {

        return resources.getIdentifier(resName, id, packageName);
    }

	public int getResRawID(String resName) {

        return resources.getIdentifier(resName, raw, packageName);
    }

	public int getResStringID(String resName) {

        return resources.getIdentifier(resName, string, packageName);
    }

	public int getResStyleID(String resName) {

        return resources.getIdentifier(resName, style, packageName);
    }

	public int getResStyleableID(String name) {
        return resources.getIdentifier(name, styleable, packageName);
    }

	public int getResXmlID(String resName) {

        return resources.getIdentifier(resName, xml, packageName);
    }

	public int getResInterpolatorID(String resName) {

        return resources.getIdentifier(resName, interpolator, packageName);
    }

	public int getResMenuID(String resName) {

        return resources.getIdentifier(resName, menu, packageName);
    }

	public int getResMipmapID(String resName) {

        return resources.getIdentifier(resName, mipmap, packageName);
    }

	public int getResArrayID(String resName) {

        return resources.getIdentifier(resName, array, packageName);
    }

	public int getResBoolID(String resName) {

        return resources.getIdentifier(resName, bool, packageName);
    }

	public int getResStringArrayID(String resName) {

        return resources.getIdentifier(resName, stringArray, packageName);
    }

	public String getString(String resName) {
        int id = getResStringID(resName);
        return resources.getString(id);
    }

	public String[] getStringArray(String resArrayName) {
        try {
            int id = getResArrayID(resArrayName);
            String[] arry = resources.getStringArray(id);
            return arry;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	public String getCertificatePsw(Context context, String appid) {

        return EUtil.getCertificatePsw(context, appid);
    }

	public int dipToPixels(int dip) {
        float density = resources.getDisplayMetrics().density;
        int valuePixels = (int) (dip * density + 0.5f);
        return valuePixels;
    }


	public int px2dip(float pxValue) {

        final float scale = resources.getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
