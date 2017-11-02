package com.afford.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.afford.R;
import com.afford.business.BusinessOrder;
import com.afford.business.entity.EtySendToUI;
import com.afford.common.base.BaseActivity;
import com.afford.common.constants.AffConstans;
import com.afford.ui.adapter.AdapterOrderState;
import com.afford.util.list.UtilList;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.List;
import java.util.Map;

public class AtyOrderState extends BaseActivity {

    @ViewInject(R.id.title)
    private TextView mTitle;//名称
    @ViewInject(R.id.pay_way)
    private TextView mpayWay;//支付方式
    @ViewInject(R.id.del_way)
    private TextView mDelWay;//配送方式
    @ViewInject(R.id.ord_num)
    private TextView mOrdNum;//订单编号

    @ViewInject(R.id.listview)
    private ListView mListview;

    private  String mOrderCode="";
    private  String mOrderPrice="";
    private  String paytype="";
    private  String taketype="";

    private BusinessOrder mBllOrder;
    private AdapterOrderState mAdapterState;//列表适配器
    //支付方式 1：在线支付，2：货到付款
    public final static String PAYTYPE = "PAYTYPE";
    //收货方式  1：送货，2：自取
    public final static String TAKETYPE = "TAKETYPE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_orderstate);
        ViewUtils.inject(this);
        //标题命名
        mTitle.setText(R.string.order_trace);
        //请求列表数据
        mBllOrder = new BusinessOrder(this);

        mOrderCode = getIntent().getStringExtra(AtySettlementOrder.ORDER_CODE);
        mOrderPrice = getIntent().getStringExtra(AtySettlementOrder.ORDER_PRICE);

        paytype = getIntent().getStringExtra(PAYTYPE);
        taketype = getIntent().getStringExtra(TAKETYPE);
        //支付方式
        if ("1".equals(paytype)){
            mpayWay.setText("在线支付");
        }else {
            mpayWay.setText("货到付款");//货到付款
        }
        if ("1".equals(taketype)){
            mDelWay.setText("门店配送");//配送方式//支付方式
        }else {
            mDelWay.setText("上门自取");
        }



        mOrdNum.setText(mOrderCode+"");//订单编号
        if (!"".equals(mOrderCode)) {
            showProDialog(this);
            mBllOrder.getOrderTrack(mOrderCode);
        }

    }

    @Override
    public void onSuccess(EtySendToUI beanSendUI) {
        if (beanSendUI != null) {
            switch (beanSendUI.getTag()) {
                case AffConstans.BUSINESS.TAG_ORDER_TRACK://订单状态跟踪
                    dissProDialog();
                    List<Map<String,Object>> listmap = (List<Map<String,Object>>) beanSendUI.getInfo();
                    if (!UtilList.isEmpty(listmap)) {
                        LogUtils.e("onSuccess====成功=======>" + listmap);
                        mAdapterState = new AdapterOrderState(this, listmap);
                        mListview.setAdapter(mAdapterState);
                        mAdapterState.notifyDataSetChanged();
                    }else {
                        LogUtils.e("onSuccess=======null====>" + listmap);
                    }
                    break;

                default:
                    break;
            }
        } else {
            LogUtils.e("onSuccess=======error====>" + beanSendUI);
        }

    }

    @Override
    public void onFailure(EtySendToUI beanSendUI) {
        if (beanSendUI != null) {
            switch (beanSendUI.getTag()) {
                case AffConstans.BUSINESS.TAG_ORDER_TRACK://订单状态跟踪
                    dissProDialog();
                    break;

                default:
                    break;
            }
        } else {
            LogUtils.e("onSuccess=======error====>" + beanSendUI);
        }
    }

    @OnClick(R.id.ibtnBack)
    public void baceOnclick(View view){
        onBackPressed();
    }


}
