head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsConnectorHelpler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ルールエンジンへの注文送信ヘルパー(WEB3RlsConnectorHelpler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/15 齋藤 栄三(FLJ) 新規作成
*/
package webbroker3.rlsgateway.connector;

import com.fitechlabs.fin.intellioms.price.PriceCondOrder;
import com.fitechlabs.fin.intellioms.rulesys.CondOrder;
import com.fitechlabs.fin.intellioms.simple.SimpleCondOrder;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.rlsgateway.data.OmsConOrderRequestRow;
import webbroker3.rlsgateway.data.RlsCondOrderRow;
import webbroker3.rlsgateway.data.RlsOmsOrderRow;
import webbroker3.rlsgateway.data.RlsPriceCondRow;

/**
 *
 * ルールエンジンへの注文送信ヘルパー
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public interface WEB3RlsConnectorHelpler
{
    
    /**
     * create条件付き注文 <BR>
     * 引数に応じて条件付き注文を生成する。 <BR>
     * <BR>
     * １）新規か以前のCondOrderを作成するかで分岐する。 <BR>
     * <BR>
     * ２）条件付き注文タイプで処理を分岐する。 <BR>
     * <BR>
     * ３）条件に応じた注文を生成する。 <BR>
     * <BR>
     * @@param OmsConOrderRequestRow - (ルールエンジンへのリクエスト通知)
     * @@param WEB3RlsOrders - (注文一覧)
     * @@param boolean - (新規=true 以前=false)
     * @@return CondOrder
     */
    public CondOrder createCondOrder(OmsConOrderRequestRow l_request, WEB3RlsOrders l_rlsOrders, boolean l_isNew);

    /**
     * create逆指値注文 <BR>
     * 逆指値注文を生成する。 <BR>
     * <BR>
     * １）逆指値注文を生成する。 <BR>
     * <BR>
     * @@param RlsCondOrderRow - (条件付き注文)
     * @@param RlsPriceCondRow - (逆指値条件)
     * @@return PriceCondOrder
     */
    public PriceCondOrder createPriceCondOrder(RlsCondOrderRow l_rlsCondOrderRow, RlsPriceCondRow l_rlsPriceCondRow);
    
    /**
     * createシンプル <BR>
     * シンプル注文を生成する。 <BR>
     * <BR>
     * １）シンプル注文を生成する。 <BR>
     * <BR>
     * @@param RlsCondOrderRow - (条件付き注文)
     * @@param RlsOmsOrderRow - (注文内容)
     * @@return SimpleCondOrder
     */
    public SimpleCondOrder createSimpleCondOrder(RlsCondOrderRow l_rlsCondOrderRow, RlsOmsOrderRow l_rlsOmsOrderRow);
    
    /**
     * addDb注文 <BR>
     * DB内注文を注文一覧に付与する。 <BR>
     * <BR>
     * １）DB内注文を注文一覧に付与する。 <BR>
     * <BR>
     * @@param OmsConOrderRequestRow - (ルールエンジンへのリクエスト通知)
     * @@param WEB3RlsOrders - (注文一覧)
     * @@return WEB3RlsOrders
     */
    public WEB3RlsOrders addDbOrders(OmsConOrderRequestRow l_request, WEB3RlsOrders l_rlsOrders) throws WEB3SystemLayerException;
    
    
    /**
     * create注文一覧 <BR>
     * 注文単位オブジェクトから注文一覧を作成する。 <BR>
     * <BR>
     * １）DB内注文を注文一覧に付与する。 <BR>
     * <BR>
     * ２）条件付き注文タイプで処理を分岐する。 <BR>
     * <BR>
     * １）DB内注文を注文一覧に付与する。 <BR>
     * <BR>
     * @@param OmsConOrderRequestRow - (ルールエンジンへのリクエスト通知)
     * @@param OrderUnit - (注文単位オブジェクト)
     * @@return WEB3RlsOrders
     */
    public WEB3RlsOrders createRlsOrders(OmsConOrderRequestRow l_request, OrderUnit l_unit);
}
@
