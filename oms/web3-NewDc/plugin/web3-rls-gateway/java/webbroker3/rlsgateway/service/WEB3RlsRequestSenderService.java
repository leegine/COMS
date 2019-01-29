head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsRequestSenderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : ルールエンジンへ送信処理サービス(WEB3RlsRequestSenderService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/14 劉(FLJ) 新規作成
 */
package webbroker3.rlsgateway.service;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.common.*;

/**
 *
 * ルールエンジンへ送信処理サービス
 * @@author 劉 (FLJ)
 * @@version 1.0
 */
public interface WEB3RlsRequestSenderService
    extends Service
{

    /**
     * （連続注文約定通知）<br />
     * <br />
     * ルールエンジンに対して、連続注文約定通知メッセージを送信。<br />
     * <br />
     * @@param l_subaccount - 補助口座<br />
     * @@param l_lngConOrderId - 親注文の注文ID<br />
     * @@param l_productType - 商品タイプ<br />
     * @@param l_strRequestNumber - 識別コード<br />
     * <br />
     */
    public void sendConOrderExecuteMessage(SubAccount l_subaccount,
                                           Long l_lngConOrderId,
                                           ProductTypeEnum l_productType,
                                           String l_strRequestNumber) throws
        WEB3SystemLayerException, WEB3BaseException;

    /**
     * （条件付注文注文登録）<br /> <br />
     * ルールエンジンに対して、条件付注文登録メッセージを送信。<br /> <br />
     *
     * @@param l_subaccount - 補助口座
     * @@param l_intOrderType - 条件付注文タイプ
     * @@see WEB3RlsNotifyOrderTypeDef
     * @@param l_conOrderProductType - 親注文商品タイプ
     * @@param l_lngConOrderId - 親注文の注文ID
     * @@param l_subOrderProductTypes - 子注文商品タイプリスト
     * @@param l_lngSubOrderIds - 子注文の注文IDリスト <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendRegisterConOrdersMessage(SubAccount l_subaccount,
                                             int l_intOrderType,
                                             ProductTypeEnum l_conOrderProductType,
                                             Long l_lngConOrderId,
                                             ProductTypeEnum[] l_subOrderProductTypes,
                                             Long[] l_lngSubOrderIds) throws
        WEB3SystemLayerException, WEB3BaseException;

    /**
     * （条件付注文注文訂正）<br /> <br />
     * ルールエンジンに対して、条件付注文訂正メッセージを送信。<br /> <br />
     *
     * @@param l_subaccount - 補助口座
     * @@param l_intOrderType - 条件付注文タイプ
     * @@see WEB3RlsNotifyOrderTypeDef
     * @@param l_conOrderProductType - 親注文商品タイプ
     * @@param l_lngConOrderId - 親注文の注文ID
     * @@param l_subOrderProductTypes - 子注文商品タイプリスト
     * @@param l_lngSubOrderIds - 子注文の注文IDリスト <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendModifyConOrdersMessage(SubAccount l_subaccount,
                                           int l_intOrderType,
                                           ProductTypeEnum l_conOrderProductType,
                                           Long l_lngConOrderId,
                                           ProductTypeEnum[] l_subOrderProductTypes,
                                           Long[] l_lngSubOrderIds) throws
        WEB3SystemLayerException, WEB3BaseException;

    /**
     * （条件付注文注文取消）<br />
     * <br />
     * ルールエンジンに対して、条件付注文取消メッセージを送信。<br />
     * <br />
     * @@param l_subaccount - 補助口座<br />
     * @@param l_intOrderType - 条件付注文タイプ
     * @@see WEB3RlsNotifyOrderTypeDef
     * @@param l_productType - 商品タイプ
     * @@param l_lngSubOrderIds - 注文ID<br />
     * <br />
     */
    public void sendCancelConOrderMessage(SubAccount l_subaccount,
                                          int l_intOrderType,
                                          ProductTypeEnum l_productType,
                                          Long l_lngOrderId) throws
        WEB3SystemLayerException, WEB3BaseException;

    /**
     * （手動条件付注文発注）<br /> <br />
     * 手動条件付注文発注を行う。<br /> <br />
     *
     * @@param l_lngSubmitterLoginId - 発注者ログインID
     * @@param l_strSubmitnotifyType - 通知経路
     * @@param l_subaccount - 補助口座
     * @@param l_intOrderType - 条件付注文タイプ
     * @@see WEB3RlsNotifyOrderTypeDef
     * @@param l_conOrderProductType - 注文商品タイプ
     * @@param l_lngConOrderId - 注文の注文ID
     * @@param l_lngParentOrderId - 親注文の注文ID
     * @@param l_intSerialNoInParent - 発注順番
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendManualSubmitConOrder(
        Long l_lngSubmitterLoginId,
        String l_strSubmitnotifyType,
        SubAccount l_subaccount,
        int l_intOrderType,
        ProductTypeEnum l_conOrderProductType,
        Long l_lngConOrderId,
        ProductTypeEnum
        l_parentOrderProductType,
        Long l_lngParentOrderId,
        int l_intSerialNoInParent) throws
        WEB3SystemLayerException, WEB3BaseException;

}
@
