package com.afford.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.afford.R;
import com.afford.common.constants.AffConstans;
import com.afford.util.list.UtilList;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;
import java.util.Map;

/**
 * Created by NiuFC on 2015/11/11.
 */
public class AdapterRefundGoods extends AdapterBase<Map<String,Object>> {


    private List<Map<String,Object>> listObject;
    public AdapterRefundGoods(Activity context, List<Map<String, Object>> _mList) {
        super(context,_mList);
        listObject = _mList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater = mContext.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.aty_orderdetail_goods_item, null);
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

        @ViewInject(R.id.affordshop_image)
        private ImageView mImage;


        private Activity mContent;
        private List<Map<String, Object>> mListMap;
        public ViewHolder(Activity context,List<Map<String, Object>> _listMap) {
            mContent = context;
            mListMap = _listMap;
        }

        /**
         *  更新最新数据
         * @param _listMap
         */
        public void refreshList(List<Map<String, Object>> _listMap){
            mListMap = _listMap;
        }
        /**
         * 第一次刷新
         */
        public void refresh(final int position) {
            if (!UtilList.isEmpty(mListMap)){
                mGoodsName.setText(mListMap.get(position).get("NAME")+"");
                mNum.setText("x  "+mListMap.get(position).get("COUNT"));
                mPrice.setText("￥" + mListMap.get(position).get("AMOUNT"));

                String uri = AffConstans.PUBLIC.ADDRESS_IMAGE + mListMap.get(position).get("THUMB") + "";
                Glide.with(mContent)
                        .load(uri)
                        .placeholder(R.drawable.default_list)
                        .error(R.drawable.default_list)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(mImage);

            }else{
                LogUtils.e("refresh===Error=====null=========>" + mListMap + "");
            }

        }

        /**
         * 更新
         */
        public void update(final int position,List<Map<String, Object>> _listMap) {
            refreshList(_listMap);
            refresh(position);
        }
    }
}
