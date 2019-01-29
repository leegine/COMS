head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsConnectorService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ルールエンジンへ注文送信処理をするサービス(WEB3RlsConnectorService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/12 齋藤 栄三(FLJ) 新規作成
*/
package webbroker3.rlsgateway.connector;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.rlsgateway.data.OmsConOrderRequestRow;

/**
 *
 * ルールエンジンへ注文送信処理をするサービス
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public interface WEB3RlsConnectorService extends Service
{

    /**
     * ルールエンジンへ注文登録処理をする。
     * 
     * @@param OmsConOrderRequestRow - リクエスト
     * @@param OrderUnit - 注文内容
     * @@throws WEB3BaseException
     */
    public void register(OmsConOrderRequestRow l_request, OrderUnit l_unit) throws WEB3BaseException;

    /**
     * ルールエンジンへ注文訂正処理をする。
     * 
     * @@param OmsConOrderRequestRow - リクエスト
     * @@param OrderUnit - 注文内容
     * @@throws WEB3BaseException
     */
    public void modify(OmsConOrderRequestRow l_request, OrderUnit l_unit) throws WEB3BaseException;

    /**
     * ルールエンジンへ注文取消処理をする。
     * 
     * @@param OmsConOrderRequestRow - リクエスト
     * @@param OrderUnit - 注文内容
     * @@throws WEB3BaseException
     */
    public void cancel(OmsConOrderRequestRow l_request, OrderUnit l_unit) throws WEB3BaseException;
    
    /**
     * xTier起動済みフラグを設定する。
     * 
     * @@param boolean - xTier起動済みフラグ
     */
    public void setIsXtierStarted(boolean l_isXtierStarted);
    
    /**
     * コネクタを準備する。
     * 
     */
    public void prepareConnection2Rls() throws WEB3SystemLayerException;
}
@
