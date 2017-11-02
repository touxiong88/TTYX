package com.afford.common.base;

import com.afford.business.entity.EtySendToUI;

/**
 * Created by hhz on 2015/5/26.
 */
public interface AffRequestCallBack<T> {
    public void onSuccess(EtySendToUI successEty);
    public void onFailure(EtySendToUI failureEty);

}
