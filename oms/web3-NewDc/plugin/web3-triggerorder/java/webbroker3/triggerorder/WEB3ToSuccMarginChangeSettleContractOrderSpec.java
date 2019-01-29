head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeSettleContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式予約返済注文訂正内容(WEB3ToSuccMarginChangeSettleContractOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7 郭英 (中訊) 新規作成 
*/


package webbroker3.triggerorder;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;

import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;


/**
 * (株式予約返済注文訂正内容)<BR>
 * 株式予約返済注文訂正内容の入力を表現する。 <BR>
 * <BR>
 * xTradeのEqTypeChangeSettleContractOrderSpecを拡張したクラス。<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeSettleContractOrderSpec extends EqTypeChangeSettleContractOrderSpec 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeSettleContractOrderSpec.class);
        
    /**
     * @@param arg0
     * @@param arg1
     */
    public WEB3ToSuccMarginChangeSettleContractOrderSpec(long arg0, EqTypeContractSettleChangeOrderUnitEntry[] arg1)
    {
        super(arg0, arg1);
    }

    /**
     * (訂正後概算受渡代金)<BR>
     * 訂正後概算受渡代金。<BR>
     */
    private double modifiedEstimatedPrice;
    
    /**
     * (訂正後計算単価)<BR>
     * 訂正後計算単価。<BR>
     */
    private double modifiedCalcUnitPrice;
    
    /**
     * (訂正後注文失効日)<BR>
     * 訂正入力の注文失効日。<BR>
     * （出来るまで注文の場合のみ）<BR>
     */
    private Date modifiedExpirationDate;
    
    /**
     * (訂正後is出来るまで注文)<BR>
     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。<BR>
     * 出来るまで注文であればtrue、以外はfalse。<BR>
     */
    private boolean modifiedIsCarriedOrder;
    
    /**
     * (扱者)<BR>
     * 代理入力扱者。<BR>
     */
    private WEB3GentradeTrader trader;
    
    /**
     * (訂正後単価調整値)<BR>
     * 訂正後単価調整値。<BR>
     * （単価調整値指定時のみセット。以外、null）<BR>
     */
    private Double modifiedPriceAdjustValue;    
    
    /**
     * (create株式予約返済注文訂正内容)<BR>
     * （staticメソッド）<BR>
     * 株式予約返済注文訂正内容オブジェクトを生成し返却する。<BR>
     * <BR>
     * 手続きはシーケンス図「（連続注文）create株式予約返済注文訂正内容」を参照。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_eqTypeSettleContractOrderEntries - (決済建株エントリ)<BR>
     * 決済建株エントリ。<BR>
     * @@param l_dblModifiedQuantity - (訂正後株数)<BR>
     * 訂正入力の株数。<BR>
     * @@param l_dblModifiedLimitPrice - (訂正後指値)<BR>
     * 訂正入力の単価。<BR>
     * @@param l_dblModifiedEstimatedPrice - (訂正後概算受渡代金)<BR>
     * 訂正後概算受渡代金。<BR>
     * @@param l_dblModifiedCalcUnitPrice - (訂正後計算単価)<BR>
     * 訂正後計算単価。<BR>
     * @@param l_datModifiedExpirationDate - (訂正後注文失効日)<BR>
     * 訂正入力の注文失効日<BR>
     * @@param l_blnModifiedIsCarriedOrder - (訂正後is出来るまで注文)<BR>
     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。<BR>
     * 出来るまで注文であればtrue、以外はfalse。<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 扱者オブジェクト<BR>
     * @@param l_modifiedPriceAdjustValue - (訂正後単価調整値)<BR>
     * 訂正後単価調整値。<BR>
     * （単価調整値指定なし時はnullをセット）<BR>
     * @@return webbroker3.triggerorder.WEB3ToSuccMarginChangeSettleContractOrderSpec
     * @@roseuid 43424CF90107
     */
    public static WEB3ToSuccMarginChangeSettleContractOrderSpec createMarginChangeSettleContractOrderSpec(
        long l_lngOrderId, 
        EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntries, 
        double l_dblModifiedQuantity, 
        double l_dblModifiedLimitPrice, 
        double l_dblModifiedEstimatedPrice, 
        double l_dblModifiedCalcUnitPrice, 
        Date l_datModifiedExpirationDate, 
        boolean l_blnModifiedIsCarriedOrder, 
        WEB3GentradeTrader l_trader, 
        Double l_modifiedPriceAdjustValue) 
    {
        final String STR_METHOD_NAME = " createMarginChangeSettleContractOrderSpec(long, " + 
            "EqTypeSettleContractOrderEntry[], double, double, double, double, Date, boolean," +
            " WEB3GentradeTrader, Double) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1: EqTypeContractSettleChangeOrderUnitEntry(arg0 : long, arg1 : double, arg2 : EqTypeSettleContractOrderEntry[])
        EqTypeContractSettleChangeOrderUnitEntry l_eqTypeContractSettleChangeOrderUnitEntry = 
            new EqTypeContractSettleChangeOrderUnitEntry(
                -1,
                l_dblModifiedLimitPrice,
                l_eqTypeSettleContractOrderEntries);
        
        //1.2:EqTypeChangeSettleContractOrderSpec(arg0 : long, arg1 : EqTypeChangeSettleContractOrderSpec[])
        EqTypeContractSettleChangeOrderUnitEntry[] l_eqTypeContractSettleChangeOrderUnitEntres = 
            {l_eqTypeContractSettleChangeOrderUnitEntry};
            
        WEB3ToSuccMarginChangeSettleContractOrderSpec l_marginChangeSettleContractOrderSpec = 
            new WEB3ToSuccMarginChangeSettleContractOrderSpec(
                l_lngOrderId, 
                l_eqTypeContractSettleChangeOrderUnitEntres);
        
        //1.3:set訂正後概算受渡代金(double)
        l_marginChangeSettleContractOrderSpec.setModifiedEstimatedPrice(l_dblModifiedEstimatedPrice);
        
        //1.4:set訂正後計算単価(double)
        l_marginChangeSettleContractOrderSpec.setModifiedCalcUnitPrice(l_dblModifiedCalcUnitPrice);
        
        //1.5:set訂正後注文失効日(Date)
        l_marginChangeSettleContractOrderSpec.setModifiedExpirationDate(l_datModifiedExpirationDate);
        
        //1.6:set訂正後is出来るまで注文(boolean)
        l_marginChangeSettleContractOrderSpec.setModifiedIsCarriedOrder(l_blnModifiedIsCarriedOrder);
        
        //1.7:set扱者(扱者)
        l_marginChangeSettleContractOrderSpec.setTrader(l_trader);
        
        //1.8:set訂正後単価調整値
        l_marginChangeSettleContractOrderSpec.setModifiedPriceAdjustValue(l_modifiedPriceAdjustValue);
        
        log.exiting(STR_METHOD_NAME);
        return l_marginChangeSettleContractOrderSpec;
    }
    
    /**
     * (get株式予約返済注文訂正内容詳細)<BR>
     * （getChangeOrderUnitEntry）<BR>
     * <BR>
     * 株式予約返済注文訂正内容詳細を取得する。<BR>
     * <BR>
     * getChangeOrderUnitEntries( )にて株式予約返済注文訂正内容詳細一覧を取得、<BR>
     * 戻り値Listの0番目の要素を返却する。<BR>
     * @@return EqTypeContractSettleChangeOrderUnitEntry
     * @@roseuid 43424CF90136
     */
    public EqTypeContractSettleChangeOrderUnitEntry getChangeOrderUnitEntry() 
    {
        final String STR_METHOD_NAME = " getChangeOrderUnitEntry() ";
        log.entering(STR_METHOD_NAME);
        
        EqTypeContractSettleChangeOrderUnitEntry[] l_eqTypeContractSettleChangeOrderUnitEntry = 
            super.getChangeOrderUnitEntries();
        
        log.exiting(STR_METHOD_NAME);
        return l_eqTypeContractSettleChangeOrderUnitEntry[0];
    }
    
    /**
     * (get訂正後計算単価)<BR>
     * 訂正後計算単価を取得する。<BR>
     * @@return double
     * @@roseuid 43424CF90165
     */
    public double getModifiedCalcUnitPrice() 
    {
        return this.modifiedCalcUnitPrice;
    }
    
    /**
     * (set訂正後計算単価)<BR>
     * 訂正後計算単価をセットする。<BR>
     * @@param l_dblModifiedCalcUnitPrice - (訂正後計算単価)<BR>
     * 訂正入力の注文単価より算出した計算単価<BR>
     * @@roseuid 43424CF90193
     */
    public void setModifiedCalcUnitPrice(double l_dblModifiedCalcUnitPrice) 
    {
        this.modifiedCalcUnitPrice = l_dblModifiedCalcUnitPrice;
    }
    
    /**
     * (get訂正後注文失効日)<BR>
     * 訂正後注文失効日を取得する。<BR>
     * @@return Date
     * @@roseuid 43424CF901B3
     */
    public Date getModifiedExpirationDate() 
    {
        return this.modifiedExpirationDate;
    }
    
    /**
     * (set訂正後注文失効日)<BR>
     * 訂正後注文失効日をセットする。<BR>
     * @@param l_datModifiedExpirationDate - (訂正後注文失効日)<BR>
     * 訂正入力の注文失効日<BR>
     * @@roseuid 43424CF90210
     */
    public void setModifiedExpirationDate(Date l_datModifiedExpirationDate) 
    {
        this.modifiedExpirationDate = l_datModifiedExpirationDate;
    }
    
    /**
     * (get訂正後概算受渡代金)<BR>
     * 訂正後概算受渡代金を取得する。<BR>
     * @@return double
     * @@roseuid 43424CF9023F
     */
    public double getModifiedEstimatedPrice() 
    {
        return this.modifiedEstimatedPrice;
    }
    
    /**
     * (set訂正後概算受渡代金)<BR>
     * 訂正後概算受渡代金をセットする。<BR>
     * @@param l_dblEstimatedPrice - (概算受渡代金)<BR>
     * 概算受渡代金。<BR>
     * @@roseuid 43424CF9026E
     */
    public void setModifiedEstimatedPrice(double l_dblEstimatedPrice) 
    {
        this.modifiedEstimatedPrice = l_dblEstimatedPrice;
    }
    
    /**
     * (set訂正後is出来るまで注文)<BR>
     * 訂正後is出来るまで注文をセットする。<BR>
     * @@param l_blnModifiedIsCarriedOrder - (訂正後is出来るまで注文)<BR>
     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。<BR>
     * 出来るまで注文であればtrue、以外はfalse。<BR>
     * @@roseuid 43424CF9028D
     */
    public void setModifiedIsCarriedOrder(boolean l_blnModifiedIsCarriedOrder) 
    {
        this.modifiedIsCarriedOrder = l_blnModifiedIsCarriedOrder;
    }
    
    /**
     * (is訂正後出来るまで注文)<BR>
     * 訂正後が「出来るまで注文」であるかどうかを返す。<BR>
     * this.訂正後is出来るまで注文の値を返す。<BR>
     * @@return boolean
     * @@roseuid 43424CF902BC
     */
    public boolean isModifiedCarriedOrder() 
    {
        return this.modifiedIsCarriedOrder;
    }
    
    /**
     * (get扱者)<BR>
     * 扱者を取得する。<BR>
     * @@return WEB3GentradeTrader
     * @@roseuid 43424CF902EB
     */
    public WEB3GentradeTrader getTrader() 
    {
        return this.trader;
    }
    
    /**
     * (set扱者)<BR>
     * 扱者をセットする。<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 扱者オブジェクト<BR>
     * @@roseuid 43424CF9031A
     */
    public void setTrader(WEB3GentradeTrader l_trader) 
    {
        this.trader = l_trader;
    }
    
    /**
     * (get訂正後単価調整値)<BR>
     * 訂正後単価調整値を取得する。<BR>
     * @@return Double
     * @@roseuid 43424CF90349
     */
    public Double getModifiedPriceAdjustValue() 
    {
        return this.modifiedPriceAdjustValue;
    }
    
    /**
     * (set訂正後単価調整値)<BR>
     * 訂正後単価調整値をセットする。<BR>
     * @@param l_modifiedPriceAdjustValue - (訂正後単価調整値)<BR>
     * 訂正後単価調整値。<BR>
     * @@roseuid 43424CF90378
     */
    public void setModifiedPriceAdjustValue(Double l_modifiedPriceAdjustValue) 
    {
        this.modifiedPriceAdjustValue = l_modifiedPriceAdjustValue;
    }
}
@
