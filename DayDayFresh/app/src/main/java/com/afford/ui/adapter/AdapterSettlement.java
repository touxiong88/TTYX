package com.afford.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afford.R;
import com.afford.thirdapi.greedsqlite.ShoppingCart;
import com.afford.ui.activity.server.AtyServerSettlement;
import com.afford.util.list.UtilList;
import com.afford.util.number.UtilNumber;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;
import java.util.Map;

/**
 * Created by NiuFC on 2015/11/11.
 */
public class AdapterSettlement extends AdapterBase<ShoppingCart> {


    private List<ShoppingCart> listObject;
    public AdapterSettlement(Activity context, List<ShoppingCart> _mList) {
        super(context,_mList);
        listObject = _mList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater = mContext.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.aty_settlement_item, null);
            viewHolder = new ViewHolder(mContext,mList);
            //依赖注入初始化
            ViewUtils.inject(viewHolder, convertView);
            convertView.setTag(viewHolder);
            viewHolder.refresh(position);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.update(position,mList);
        }

        return convertView;
    }

    /**
     * @author p
     */
    static class ViewHolder {
        @ViewInject(R.id.goodsname)
        private TextView mGoodsName;

        @ViewInject(R.id.goodsNum)
        private TextView mNum;
        @ViewInject(R.id.goodsPrice)
        private TextView mPrice;

        private Activity mContent;
        private List<ShoppingCart> mListMap;
        public ViewHolder(Activity context,List<ShoppingCart> _listMap) {
            mContent = context;
            mListMap = _listMap;
        }

        /**
         *  更新最新数据
         * @param _listMap
         */
        public void refreshList(List<ShoppingCart> _listMap){
            mListMap = _listMap;
        }
        /**
         * 第一次刷新
         */
        public void refresh(final int position) {
            if (!UtilList.isEmpty(mListMap)){
                mGoodsName.setText(mListMap.get(position).getName()+"");
                mNum.setText("x  "+ UtilNumber.IntegerValueOf(mListMap.get(position).getBuynum()));
                mPrice.setText("￥"+mListMap.get(position).getSellprice());
            }else{
                LogUtils.e("refresh===Error=====null=========>" + mListMap + "");
            }

        }

        /**
         * 更新
         */
        public void update(final int position,List<ShoppingCart> _listMap) {
            refreshList(_listMap);
            refresh(position);
        }
    }
}
