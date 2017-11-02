/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.afford.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afford.AffordApp;
import com.afford.R;
import com.afford.business.entity.EtySendToUI;
import com.afford.common.base.BaseActivity;
import com.afford.common.base.BaseAtyFragment;
import com.afford.common.constants.CommConstans;
import com.afford.ui.activity.fragment.FragmentServerDetail;
import com.afford.ui.activity.fragment.FragmentServerState;
import com.afford.ui.widget.WdtPagerSlidingTabStrip;
import com.lidroid.xutils.util.LogUtils;


public class AtyFragmentServerDetail extends BaseAtyFragment implements OnClickListener {

	private WdtPagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;

	private int currentColor = 0xFF666666;
	private TextView mTopTitle_txt;
	private ImageButton mIbtnBack;
	private ImageButton mRightPhone;//右侧电话

	private int mSearchClassifyType;// 此变量标志着是否是从搜索页面过来的，按地区或行业搜索的

	public final static String TABCODE = "tabCode";//
//	public final static String SER_HOUSE_TAG = "ser_house_tag";//服务家政标志
//	public final static String DAT_DEEP_TAG = "dat_deep_tag";//日常/深度保洁标志

	public static String mOrderCode="";
	public static boolean ORDSTATETAG = false;
	public static String mAtyHouseTpye = "";//记录从家政或服务传过来的标志
//	public static String mDayOrDeepType="";//日常保洁/深度保洁标志

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_frag_server_detail);
		//区分是日常保洁等家政服务还是其他服务
		mAtyHouseTpye = getIntent().getStringExtra(AtySettlementOrder.ORDER_PAY_TYPE);
		//订单号
		mOrderCode = getIntent().getStringExtra(AtySettlementOrder.ORDER_CODE);


		mTopTitle_txt = (TextView) findViewById(R.id.title);
		mTopTitle_txt.setText("大实惠云超市");
		mRightPhone = (ImageButton) findViewById(R.id.top_btn_right);
		mRightPhone.setVisibility(View.VISIBLE);
		mRightPhone.setImageResource(R.drawable.icon_phone);
		mRightPhone.setOnClickListener(this);
		mIbtnBack = (ImageButton) findViewById(R.id.ibtnBack);
		mIbtnBack.setOnClickListener(this);


		tabs = (WdtPagerSlidingTabStrip) findViewById(R.id.apps_fragment_tabs);
		pager = (ViewPager) findViewById(R.id.apps_fragment_viewpager);
		Intent intent = getIntent();
		//此页面会从两个页面进入，1 应用首页分类按钮 2 搜索页面分类按钮 如果从搜索页面分类按钮点击进入，须取出searchClassifyType
		mSearchClassifyType = intent.getIntExtra("searchClassifyType", -1);
		adapter = new MyPagerAdapter(getSupportFragmentManager());

		// 设置缓存页面，当前页面的相邻N各页面都会被缓存
		pager.setOffscreenPageLimit(adapter.getCount());
		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		pager.setCurrentItem(intent.getIntExtra(TABCODE, 0));
		tabs.setViewPager(pager);
		changeColor(currentColor);
	}

	@Override
	protected void onResume() {
		adapter.notifyDataSetChanged();
		super.onResume();
	}

	private void changeColor(int newColor) {
		tabs.setIndicatorColor(newColor);
	}

	public void onColorClicked(View v) {
		int color = Color.parseColor(v.getTag().toString());
		changeColor(color);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("currentColor", currentColor);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		currentColor = savedInstanceState.getInt("currentColor");
		changeColor(currentColor);
	}

	@Override
	public void onSuccess(EtySendToUI beanSendUI) {

	}

	@Override
	public void onFailure(EtySendToUI beanSendUI) {

	}

	public class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager supportFragmentManager) {
			super(supportFragmentManager);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return CommConstans.COMMONARRAY.TABS_ORDERDETAIL[position];
		}

		@Override
		public int getCount() {
			return CommConstans.COMMONARRAY.TABS_ORDERDETAIL.length;
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = FragmentServerState.newInstance(AtyFragmentServerDetail.this, position);
				break;
			case 1:
				fragment = FragmentServerDetail.newInstance(AtyFragmentServerDetail.this, position);
				break;
			default:
				break;
			}
			return fragment;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ibtnBack:
			onBackPressed();
			break;
		case R.id.top_btn_right:
			String phone = AffordApp.getInstance().getEntityLocation().getSTORE().getTEL();
			LogUtils.e("onClick===========phone==========>"+phone);
			Intent intent = new Intent(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone)));
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}