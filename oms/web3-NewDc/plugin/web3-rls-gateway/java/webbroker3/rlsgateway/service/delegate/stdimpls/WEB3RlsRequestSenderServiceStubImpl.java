head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.19.04.48.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5844dad14234ce1;
filename	WEB3RlsRequestSenderServiceStubImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : ルールエンジンへ送信処理サービスStub実装クラス(WEB3RlsRequestSenderServiceStubImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/07 劉(FLJ) 新規作成
 */
package webbroker3.rlsgateway.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.common.*;
import webbroker3.rlsgateway.*;
import webbroker3.rlsgateway.define.*;
import webbroker3.rlsgateway.service.*;
import webbroker3.util.*;

/**
 *
 * ルールエンジンへ送信処理サービス実装
 * @@author 劉 (FLJ)
 * @@version 1.0
 */
public class WEB3RlsRequestSenderServiceStubImpl
    implements WEB3RlsRequestSenderService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsRequestSenderServiceImpl.class);

    /**
     * （条件付注文約定通知）<br />
     * <br />
     * ルールエンジンに対して、条件付注文約定通知メッセージを送信。<br />
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
        WEB3SystemLayerException, WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " sendExecuteMessage(SubAccount,Long ,ProductTypeEnum,String)";
        log.entering(STR_METHOD_NAME);

        WEB3RlsConOrderExecuteMessageContext l_context = new
            WEB3RlsConOrderExecuteMessageContext
            (l_subaccount, WEB3RlsNotifyOrderTypeDef.EXECUTE,
             l_productType,
             l_lngConOrderId,
             l_strRequestNumber
             );

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME,
            l_context);

        doRealTxSend();
        log.exiting(STR_METHOD_NAME);

    }

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
        WEB3SystemLayerException, WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " sendRegisterConOrdersMessage(SubAccount,int,ProductTypeEnum,Long ,ProductTypeEnum[],Long[])";
        log.entering(STR_METHOD_NAME);

        WEB3RlsRegisterConOrdersMessageContext l_context = new
            WEB3RlsRegisterConOrdersMessageContext
            (l_subaccount,
             l_intOrderType,
             l_conOrderProductType,
             l_lngConOrderId,
             l_subOrderProductTypes,
             l_lngSubOrderIds
             );

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME,
            l_context);

        doRealTxSend();
        log.exiting(STR_METHOD_NAME);

    }

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
        WEB3SystemLayerException, WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " sendModifyConOrdersMessage(SubAccount,int,ProductTypeEnum,Long ,ProductTypeEnum[],Long[])";
        log.entering(STR_METHOD_NAME);

        WEB3RlsModifyConOrdersMessageContext l_context = new
            WEB3RlsModifyConOrdersMessageContext
            (l_subaccount,
             l_intOrderType,
             l_conOrderProductType,
             l_lngConOrderId,
             l_subOrderProductTypes,
             l_lngSubOrderIds
             );

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME,
            l_context);

        doRealTxSend();
        log.exiting(STR_METHOD_NAME);

    }

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
        WEB3SystemLayerException, WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " sendCancelConOrderMessage(SubAccount,int,ProductTypeEnum,Long)";

        log.entering(STR_METHOD_NAME);

        WEB3RlsCancelConOrdersMessageContext l_context = new
            WEB3RlsCancelConOrdersMessageContext
            (l_subaccount,
             l_intOrderType,
             l_productType,
             l_lngOrderId
             );

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME,
            l_context);

        doRealTxSend();

        log.exiting(STR_METHOD_NAME);

    }

    private void doRealTxSend() throws
        WEB3SystemLayerException, WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " doRealTxSend()";
        log.entering(STR_METHOD_NAME);

        Object l_context = ThreadLocalSystemAttributesRegistry
            .getAttribute(WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME);

        // 送信内容が設定されていない場合
        if (l_context == null)
        {
            log.debug("WEB3RlsMessageContextが設定されていないためルールエンジンへ送信しませんでした。");
            log.exiting(STR_METHOD_NAME);
            return;
        }
        log.debug("ルールエンジンへ送信内容：" + l_context.toString());

        // コンテキスト情報をクリア
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME, null);

        log.exiting(STR_METHOD_NAME);
        return;
    }

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
        WEB3SystemLayerException, WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " sendManualSubmitConOrder(Long,String,SubAccount,int,ProductTypeEnum,Long ,ProductTypeEnum[],Long[])";
        log.entering(STR_METHOD_NAME);

        WEB3RlsManualSubmitConOrderMessageContext l_context = new
            WEB3RlsManualSubmitConOrderMessageContext
            (l_lngSubmitterLoginId,
             l_strSubmitnotifyType,
             l_subaccount,
             l_intOrderType,
             l_conOrderProductType,
             l_lngConOrderId,
             l_parentOrderProductType,
             l_lngParentOrderId,
             l_intSerialNoInParent
             );

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME,
            l_context);

        doRealTxSend();
        log.exiting(STR_METHOD_NAME);

    }

}
@
