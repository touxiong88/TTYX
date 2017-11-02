package com.afford.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.afford.AffordApp;
import com.afford.R;
import com.afford.common.constants.AffConstans;
import com.afford.common.constants.CommConstans;
import com.afford.sqlite.SqliteShoppingCart;
import com.afford.thirdapi.greedsqlite.ShoppingCart;
import com.afford.ui.activity.shop.AtyAffordShop;
import com.afford.ui.activity.shop.AtyAffordShopDetail;
import com.afford.util.list.UtilList;
import com.afford.util.number.UtilNumber;
import com.afford.util.string.UtilString;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.List;
import java.util.Map;

/**
 * @author NiuFC
 * @version 1.0
 * @date 2015-10-30
 */
public class AdapterAffordShop extends AdapterBase<Map<String, Object>> {


    public AdapterAffordShop(Activity context, List<Map<String, Object>> _List) {
        super(context, _List);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater = mContext.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.aty_affordshop_item, null);
            viewHolder = new ViewHolder(mContext, mList);
            //依赖注入初始化
            ViewUtils.inject(viewHolder, convertView);
            convertView.setTag(viewHolder);
            viewHolder.refresh(position);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            //每次更新最新数据
            viewHolder.update(position, mList);
        }
        return convertView;
    }

    /**
     * @author p
     */
    static class ViewHolder {

        @ViewInject(R.id.gridLayout)
        private RelativeLayout gridLayout;
        @ViewInject(R.id.act_affordshop_item_image)
        private ImageView imgView;
        @ViewInject(R.id.aty_affordshop_item_goodsname)
        private TextView txtViewName;
        @ViewInject(R.id.aty_affordshop_item_new_goodsprice)
        private TextView txtViewPrice;
        @ViewInject(R.id.aty_affordshop_item_old_goodsprice)
        private TextView txtViewOldPrice;
        @ViewInject(R.id.shopcart_image)
        private ImageView mAddShopCart;
        @ViewInject(R.id.shopcart_image)
        private ImageButton imgBtn;

        private Activity mContent;
        private List<Map<String, Object>> mListMap;

        private Intent mIntent;
        private int mPosition;
        private Bitmap map = null;

        public ViewHolder(Activity context, List<Map<String, Object>> _listMap) {
            mContent = context;
            mListMap = _listMap;
        }

        @OnClick(R.id.shopcart_image)//购物车图片
        public void onImgBtnClick(View view) {
            if (!UtilList.isEmpty(mListMap)) {
                ShoppingCart _ShopCart = SqliteShoppingCart.getInstance(mContent).getShoppingCartByUid(mListMap.get(mPosition).get("ID") + "");

                if (_ShopCart != null) {
                    int _ShopNums = UtilNumber.IntegerValueOf(_ShopCart.getBuynum());
                    LogUtils.e("购物车已有数据===============>" + _ShopNums);
                    addShopCart(mListMap.get(mPosition), ++_ShopNums);
                } else {
//                    LogUtils.e("购物车第一次添加数据===============>" + _ShopCart);
                    addShopCart(mListMap.get(mPosition), 1);
                }

                int[] startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
                view.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
                ImageView ball = new ImageView(mContent);// buyImg是动画的图片
                //设置动画图片大小
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40, 40);
                ball.setLayoutParams(layoutParams);

//                LogUtils.e("uri===图片地址===========>" + mListMap.get(mPosition).get("THUMB") + "");
                if (!UtilString.isEmpty(mListMap.get(mPosition).get("THUMB") + "")) {
                    String uri = AffConstans.PUBLIC.ADDRESS_IMAGE + mListMap.get(mPosition).get("THUMB") + "";
//                    LogUtils.e("uri===图片地址===========>" + uri);
                    Glide.with(mContent)
                            .load(uri)
                            .placeholder(R.drawable.cvs_btn_addshopcar)
                            .error(R.drawable.cvs_btn_addshopcar)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(ball);
                } else {
//                    LogUtils.e("uri===图片地址====error=======>" + mListMap.get(mPosition).get("THUMB")+"");
                }
                ((AtyAffordShop) mContent).setAnim(ball, startLocation);// 开始执行动画

                //发送广播更新底部购物车显示
//                sendShopChartBroadcast();
            } else {
                Toast.makeText(mContent, "服务器繁忙，请稍后再试...", Toast.LENGTH_SHORT).show();
            }
        }

        @OnClick(R.id.gridLayout)
        public void onGridClick(View view) {
            if (!UtilList.isEmpty(mListMap)) {
                if (mIntent == null) {
                    mIntent = new Intent(mContent, AtyAffordShopDetail.class);
                }
                //便利店列表
                mIntent.putExtra(AtyAffordShopDetail.RETURN_ACTIVITY, CommConstans.SHOPDETAIL.RETURN_TAG0 + "");
                mIntent.putExtra(AtyAffordShop.INTENT_SHOP_ID, mListMap.get(mPosition).get("ID") + "");
                mIntent.putExtra(AtyAffordShop.INTENT_SHOP_IMG, mListMap.get(mPosition).get("THUMB") + "");
                mContent.startActivity(mIntent);
            } else {
                Toast.makeText(mContent, "服务器繁忙，请稍后再试...", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * 更新最新数据
         *
         * @param _listMap
         */
        public void refreshList(List<Map<String, Object>> _listMap) {
            mListMap = _listMap;
        }

        /**
         * 第一次刷新
         */
        public void refresh(final int position) {
            mPosition = position;
            /***********  赋值 ***********/
            if(!UtilList.isEmpty(mListMap)){
                Glide.with(mContent)
                        .load(AffConstans.PUBLIC.ADDRESS_IMAGE + mListMap.get(position).get("THUMB") + "")
                        .placeholder(R.drawable.default_list)
                        .error(R.drawable.default_list)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgView);
                txtViewPrice.setText("￥" + mListMap.get(position).get("SELLPRICE") + "");
                txtViewOldPrice.setText("￥" + mListMap.get(position).get("MARKETPRICE") + "");
               String isself = mListMap.get(position).get("ISSELF") + "";
                if(UtilString.isEquals(isself,"1")){
                    Bitmap b = BitmapFactory.decodeResource(mContent.getResources(), R.drawable.title_myself);
                    ImageSpan imageSpan = new ImageSpan(mContent, b);
                    SpannableString spanString = new SpannableString("icon");
                    spanString.setSpan(imageSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    txtViewName.setText(spanString);
                    txtViewName.append(mListMap.get(position).get("NAME") + "");
                }else {
                    txtViewName.setText(mListMap.get(position).get("NAME") + "");
                }
                txtViewOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); //中划线
            }else {
                LogUtils.e("refresh=========null=====>" + mListMap);
            }

        }

        /**
         * 更新
         */
        public void update(final int position, List<Map<String, Object>> _listMap) {
            refreshList(_listMap);
            refresh(position);
        }

        private void addShopCart(Map<String, Object> objectMap, int num) {
            if (objectMap == null) {
//                LogUtils.e("null===============>" + objectMap);
                return;
            }
            ShoppingCart _shoppingCart = new ShoppingCart();
            //点击加入购物车将数据保存至本地数据库
            _shoppingCart.setID(objectMap.get("ID") + "");
            String isSelf = objectMap.get("ISSELF") + "";
            LogUtils.e("addShopCart===addShopCart===333333===ID======>" + objectMap.get("ID"));
            //如果是自营
            if ("1".equals(isSelf)){
                _shoppingCart.setShopID(CommConstans.SELF_TYPE);
            }else {
                if (AffordApp.getInstance().getEntityLocation()!=null){
                    _shoppingCart.setShopID(AffordApp.getInstance().getEntityLocation().getSTORE().getID() + "");
                }else {
                    _shoppingCart.setShopID("便利店");
                }

            }

            _shoppingCart.setName(objectMap.get("NAME") + "");
            _shoppingCart.setSpec(objectMap.get("SPEC") + "");
            _shoppingCart.setShortinfo(objectMap.get("SHORTINFO") + "");
            _shoppingCart.setMarketprice(objectMap.get("MARKETPRICE") + "");
            String img = objectMap.get("THUMB") + "";
            if (!UtilString.isEmpty(img)) {
                _shoppingCart.setImgsrc(img + "");
            } else {
                _shoppingCart.setImgsrc("");
            }
            //是否是自营商品：0：不是，1：是。
            _shoppingCart.setShoptype(objectMap.get("ISSELF") + "");
            _shoppingCart.setUrv(objectMap.get("URV") + "");
            _shoppingCart.setType(objectMap.get("TYPE") + "");
            _shoppingCart.setBuynum(num + "");
            _shoppingCart.setSellprice(objectMap.get("SELLPRICE") + "");
            _shoppingCart.setIschoose(true);
            //保存到本地数据库
            SqliteShoppingCart.getInstance(mContent).update(objectMap.get("ID") + "", _shoppingCart);
        }
    }
}
