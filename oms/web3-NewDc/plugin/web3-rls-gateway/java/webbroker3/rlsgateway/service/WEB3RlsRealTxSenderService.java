head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsRealTxSenderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 実際ルールエンジンへ送信トランザクションの処理サービス(WEB3RlsRealTxSenderService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/14 劉(FLJ) 新規作成
 */
package webbroker3.rlsgateway.service;

import com.fitechlabs.xtrade.kernel.boot.*;
import webbroker3.common.*;
import webbroker3.rlsgateway.*;

/**
 * 実際ルールエンジンへ送信トランザクションの処理サービス
 * @@author 劉 (FLJ)
 * @@version 1.0
 */
public interface WEB3RlsRealTxSenderService
    extends Service
{

    /**
     * （連続注文約定通知）<br />
     * <br />
     * ルールエンジンに対して、連続注文約定通知メッセージを送信。<br />
     * <br />
     * @@param l_context - 送信内容コンテクスト<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendConOrderExecuteMessage(WEB3RlsConOrderExecuteMessageContext
                                           l_context) throws
        WEB3SystemLayerException, WEB3BaseException;

    /**
     * （条件付注文注文登録）<br /> <br />
     * ルールエンジンに対して、条件付注文登録メッセージを送信。<br />
     * <br />
     * @@param l_context - 送信内容コンテクスト<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendRegisterConOrdersMessage(WEB3RlsRegisterConOrdersMessageContext
                                             l_context) throws
        WEB3SystemLayerException, WEB3BaseException;

    /**
     * （条件付注文注文訂正）<br /> <br />
     * ルールエンジンに対して、条件付注文訂正メッセージを送信<br />
     * <br />
     * @@param l_context - 送信内容コンテクスト<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendModifyConOrdersMessage(WEB3RlsModifyConOrdersMessageContext
                                           l_context) throws
        WEB3SystemLayerException, WEB3BaseException;

    /**
     * （条件付注文注文取消）<br />
     * <br />
     * ルールエンジンに対して、条件付注文取消メッセージを送信。<br />
     * <br />
     * @@param l_context - 送信内容コンテクスト<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendCancelConOrderMessage(WEB3RlsCancelConOrdersMessageContext l_context) throws
        WEB3SystemLayerException, WEB3BaseException;

    /**
     * （手動条件付注文注文発注）<br />
     * <br />
     * 手動条件付注文注文発注。<br />
     * <br />
     * @@param l_context - 強制発注内容コンテクスト<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendManualSubmitConOrder(WEB3RlsManualSubmitConOrderMessageContext
                                         l_context) throws
        WEB3SystemLayerException, WEB3BaseException;

}
@
