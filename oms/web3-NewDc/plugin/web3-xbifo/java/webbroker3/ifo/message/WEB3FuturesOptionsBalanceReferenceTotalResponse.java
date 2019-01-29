head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsBalanceReferenceTotalResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物オプション残高照会残高合計レスポンスクラス(WEB3FuturesOptionsBalanceReferenceTotalResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 呉艶飛 新規作成   
                 : 2006/07/24 黄建 仕様変更モデルNo.526、531 
*/
package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (株価指数先物オプション残高照会残高合計レスポンス)<BR>
 * 株価指数先物オプション残高照会残高合計レスポンスクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0  
 */
public class WEB3FuturesOptionsBalanceReferenceTotalResponse extends WEB3GenResponse 
{
   
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 2004012291504L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futuresOptions_balanceReferenceTotal";
    
    /**
     * プット買建玉時価総額
     */
    public String putBuyCurrentPrice = "0";
    
    /**
     * コール買建玉時価総額
     */
    public String callBuyCurrentPrice = "0";
    
    /**
     * 買建玉時価総額
     */
    public String buyCurrentPrice = "0";
    
    /**
     * プット売建玉時価総額
     */
    public String putSellCurrentPrice = "0";
    
    /**
     * コール売建玉時価総額
     */
    public String callSellCurrentPrice = "0";
    
    /**
     * 売建玉時価総額
     */
    public String sellCurrentPrice = "0";
    
    /**
     * プット買建玉総数量
     */
    public String putBuyTotalQuantity = "0";
    
    /**
     * コール買建玉総数量
     */
    public String callBuyTotalQuantity = "0";
    
    /**
     * 買建玉総数量
     */
    public String buyTotalQuantity = "0";
    
    /**
     * プット売建玉総数量
     */
    public String putSellTotalQuantity = "0";
    
    /**
     * コール売建玉総数量
     */
    public String callSellTotalQuantity = "0";
    
    /**
     * 売建玉総数量
     */
    public String sellTotalQuantity = "0";
    
    /**
     * 建玉総数量
     */
    public String totalQuantity = "0";
    
    /**
     * プット買建玉評価損益合計
     */
    public String putBuyAssetProfitLoss = "0";
    
    /**
     * コール買建玉評価損益合計
     */
    public String callBuyAssetProfitLoss = "0";
    
    /**
     * 買建玉評価損益合計
     */
    public String buyAssetProfitLoss = "0";
    
    /**
     * プット売建玉評価損益合計
     */
    public String putSellAssetProfitLoss = "0";
    
    /**
     * コール売建玉評価損益合計
     */
    public String callSellAssetProfitLoss = "0";
    
    /**
     * 売建玉評価損益合計
     */
    public String sellAssetProfitLoss = "0";
    
    /**
     * 評価損益合計
     */
    public String appraisalProfitLoss = "0";
    
    /**
     * プット買建玉評価損益合計(諸経費込)
     */
    public String putBuyAssetProfitLossCost = "0";
    
    /**
     * コール買建玉評価損益合計(諸経費込)
     */
    public String callBuyAssetProfitLossCost = "0";
    
    /**
     * 買建玉評価損益合計(諸経費込)
     */
    public String buyAssetProfitLossCost = "0";
    
    /**
     * プット売建玉評価損益合計(諸経費込)
     */
    public String putSellAssetProfitLossCost = "0";
    
    /**
     * コール売建玉評価損益合計(諸経費込)
     */
    public String callSellAssetProfitLossCost = "0";
    
    /**
     * 売建玉評価損益合計(諸経費込)
     */
    public String sellAssetProfitLossCost = "0";
    
    /**
     * 評価損益合計(諸経費込)
     */
    public String appraisalProfitLossCost = "0";
    
    /**
     * (指数別残高合計)<BR>
     * 株価指数先物オプション指数別残高合計の配列<BR>
     */
    public WEB3FuturesOptionsBalRefTotalParIndexUnit[] futuresOptionsBalRefTotalParIndexUnits;
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FuturesOptionsBalanceReferenceTotalResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
