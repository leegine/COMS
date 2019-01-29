head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.16.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsBalRefTotalParIndexUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物オプション指数別残高合計(WEB3FuturesOptionsBalRefTotalParIndexUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/24 周捷(中訊) 新規作成
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (株価指数先物オプション指数別残高合計)<BR>
 * 株価指数先物オプション指数別残高合計クラス<BR>
 * 
 * @@author 周捷(中訊)
 * @@version 1.0
 */
public class WEB3FuturesOptionsBalRefTotalParIndexUnit extends Message
{
    /**
     * (指数種別)<BR>
     * 0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225<BR>
     */
    public String targetProductCode;
    
    /**
     * (プット買建玉時価総額)<BR>
     * 指数別プット買建玉時価総額<BR>
     */
    public String putBuyCurrentPrice = "0";
    
    /**
     * (コール買建玉時価総額)<BR>
     * 指数別コール買建玉時価総額<BR>
     */
    public String callBuyCurrentPrice = "0";
    
    /**
     * (買建玉時価総額)<BR>
     * 指数別買建玉時価総額<BR>
     */
    public String buyCurrentPrice = "0";
    
    /**
     * (プット売建玉時価総額)<BR>
     * 指数別プット売建玉時価総額<BR>
     */
    public String putSellCurrentPrice = "0";
    
    /**
     * (コール売建玉時価総額)<BR>
     * 指数別コール売建玉時価総額<BR>
     */
    public String callSellCurrentPrice = "0";
    
    /**
     * (売建玉時価総額)<BR>
     * 指数別売建玉時価総額<BR>
     */
    public String sellCurrentPrice = "0";
    
    /**
     * (プット買建玉総数量)<BR>
     * 指数別プット買建玉総数量<BR>
     */
    public String putBuyTotalQuantity = "0";
    
    /**
     * (コール買建玉総数量)<BR>
     * 指数別コール買建玉総数量<BR>
     */
    public String callBuyTotalQuantity = "0";
    
    /**
     * (買建玉総数量)<BR>
     * 指数別買建玉総数量 <BR>
     */
    public String buyTotalQuantity = "0";
    
    /**
     * (プット売建玉総数量)<BR>
     * 指数別プット売建玉総数量<BR>
     */
    public String putSellTotalQuantity = "0";
    
    /**
     * (コール売建玉総数量)<BR>
     * 指数別コール売建玉総数量<BR>
     */
    public String callSellTotalQuantity = "0";
    
    /**
     * (売建玉総数量)<BR>
     * 指数別売建玉総数量<BR>
     */
    public String sellTotalQuantity = "0";
    
    /**
     * (建玉総数量)<BR>
     * 指数別建玉総数量<BR>
     */
    public String totalQuantity = "0";
    
    /**
     * (プット買建玉評価損益合計)<BR>
     * 指数別プット買建玉評価損益合計<BR>
     */
    public String putBuyAssetProfitLoss = "0";
    
    /**
     * (コール買建玉評価損益合計)<BR>
     * 指数別コール買建玉評価損益合計<BR>
     */
    public String callBuyAssetProfitLoss = "0";
    
    /**
     * (買建玉評価損益合計)<BR>
     * 指数別買建玉評価損益合計<BR>
     */
    public String buyAssetProfitLoss = "0";
    
    /**
     * (プット売建玉評価損益合計)<BR>
     * 指数別プット売建玉評価損益合計<BR>
     */
    public String putSellAssetProfitLoss = "0";
    
    /**
     * (コール売建玉評価損益合計)<BR>
     * 指数別コール売建玉評価損益合計<BR>
     */
    public String callSellAssetProfitLoss = "0";
    
    /**
     * (売建玉評価損益合計)<BR>
     * 指数別売建玉評価損益合計<BR>
     */
    public String sellAssetProfitLoss = "0";
    
    /**
     * (評価損益合計)<BR>
     * 指数別評価損益合計<BR>
     */
    public String appraisalProfitLoss = "0";
    
    /**
     * (プット買建玉評価損益合計(諸経費込))<BR>
     * 指数別プット買建玉評価損益合計(諸経費込)<BR>
     */
    public String putBuyAssetProfitLossCost = "0";
    
    /**
     * (コール買建玉評価損益合計(諸経費込))<BR>
     * 指数別コール買建玉評価損益合計(諸経費込)<BR>
     */
    public String callBuyAssetProfitLossCost = "0";
    
    /**
     * (買建玉評価損益合計(諸経費込))<BR>
     * 指数別買建玉評価損益合計(諸経費込)<BR>
     */
    public String buyAssetProfitLossCost = "0";
    
    /**
     * (プット売建玉評価損益合計(諸経費込))<BR>
     * 指数別プット売建玉評価損益合計(諸経費込)<BR>
     */
    public String putSellAssetProfitLossCost = "0";
    
    /**
     * (コール売建玉評価損益合計(諸経費込))<BR>
     * 指数別コール売建玉評価損益合計(諸経費込)<BR>
     */
    public String callSellAssetProfitLossCost = "0";
    
    /**
     * (売建玉評価損益合計(諸経費込))<BR>
     * 指数別売建玉評価損益合計(諸経費込)<BR>
     */
    public String sellAssetProfitLossCost = "0";
    
    /**
     * (評価損益合計(諸経費込))<BR>
     * 指数別評価損益合計(諸経費込)<BR>
     */
    public String appraisalProfitLossCost = "0";
}
@
