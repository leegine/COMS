head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEqtypeChangeOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式予約注文訂正内容(WEB3ToSuccEqtypeChangeOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7 郭英 (中訊) 新規作成 
*/

package webbroker3.triggerorder;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;

import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;


/**
 * (株式予約注文訂正内容)<BR>
 * 株式予約注文訂正内容の入力を表現する。 <BR>
 * <BR>
 * xTradeのEqTypeChangeOrderSpecを拡張したクラス。<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3ToSuccEqtypeChangeOrderSpec extends EqTypeChangeOrderSpec 
{
    /**
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     */
    public WEB3ToSuccEqtypeChangeOrderSpec(long arg0, long arg1, double arg2, double arg3)
    {
        super(arg0, arg1, arg2, arg3);
        // TODO Auto-generated constructor stub
    }

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3ToSuccEqtypeChangeOrderSpec.class);

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
     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。 <BR>
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
     * (create株式予約注文訂正内容)<BR>
     * （staticメソッド）<BR>
     * 株式予約注文訂正内容オブジェクトを生成し返却する。<BR>
     * <BR>
     * 手続きはシーケンス図「（連続注文）create株式予約注文訂正内容」を参照。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
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
     * 
     * @@param l_blnModifiedIsCarriedOrder - (訂正後is出来るまで注文)<BR>
     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。<BR>
     * 出来るまで注文であればtrue、以外はfalse。<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 扱者オブジェクト<BR>
     * @@param l_modifiedPriceAdjustValue - (訂正後単価調整値)<BR>
     * 訂正後単価調整値。<BR>
     * （単価調整値指定なし時はnullをセット）<BR>
     * @@return webbroker3.triggerorder.WEB3ToSuccEqtypeChangeOrderSpec
     * @@roseuid 433B92D70269
     */
    public static WEB3ToSuccEqtypeChangeOrderSpec createEqtypeChangeOrderSpec(
        long l_lngOrderId, 
        double l_dblModifiedQuantity, 
        double l_dblModifiedLimitPrice, 
        double l_dblModifiedEstimatedPrice, 
        double l_dblModifiedCalcUnitPrice, 
        Date l_datModifiedExpirationDate, 
        boolean l_blnModifiedIsCarriedOrder, 
        WEB3GentradeTrader l_trader, 
        Double l_modifiedPriceAdjustValue) 
    {
        final String STR_METHOD_NAME = " createEqtypeChangeOrderSpec(long, double, " + 
            "double, double, double, Date, boolean, WEB3GentradeTrader, Double) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:EqTypeChangeOrderSpec(注文ID : long, 注文単位ID : long, 訂正後株数 : double, 訂正後指値 : double)
        WEB3ToSuccEqtypeChangeOrderSpec l_eqtypeChangeOrderSpec = new WEB3ToSuccEqtypeChangeOrderSpec(
            l_lngOrderId,
            -1,
            l_dblModifiedQuantity,
            l_dblModifiedLimitPrice);
        
        //1.2:set訂正後概算受渡代金(double)
        l_eqtypeChangeOrderSpec.setModifiedEstimatedPrice(l_dblModifiedEstimatedPrice);
        
        //1.3:set訂正後計算単価(double)
        l_eqtypeChangeOrderSpec.setModifiedCalcUnitPrice(l_dblModifiedCalcUnitPrice);
        
        //1.4:set訂正後注文失効日(Date)
        l_eqtypeChangeOrderSpec.setModifiedExpirationDate(l_datModifiedExpirationDate);
        
        //1.5:set訂正後is出来るまで注文(boolean)
        l_eqtypeChangeOrderSpec.setModifiedIsCarriedOrder(l_blnModifiedIsCarriedOrder);
        
        //1.6:set扱者(扱者)
        l_eqtypeChangeOrderSpec.setTrader(l_trader);
        
        //1.7:set訂正後単価調整値(Double)
        l_eqtypeChangeOrderSpec.setModifiedPriceAdjustValue(l_modifiedPriceAdjustValue);
        
        log.exiting(STR_METHOD_NAME);        
        return l_eqtypeChangeOrderSpec;
    }
    
    /**
     * (get株式予約注文訂正内容詳細)<BR>
     * （getChangeOrderUnitEntry）<BR>
     * <BR>
     * 株式予約注文訂正内容詳細を取得する。<BR>
     * <BR>
     * getChangeOrderUnitEntries( )にて株式予約注文訂正内容詳細一覧を取得、<BR>
     * 戻り値Listの0番目の要素を返却する。<BR>
     * @@return EqTypeChangeOrderUnitEntry
     * @@roseuid 433B92D70298
     */
    public EqTypeChangeOrderUnitEntry getChangeOrderUnitEntry() 
    {
        final String STR_METHOD_NAME = " getChangeOrderUnitEntry() ";
        log.entering(STR_METHOD_NAME);
        
        EqTypeChangeOrderUnitEntry[] l_eqTypeChangeOrderUnitEntry = super.getChangeOrderUnitEntries();
        
        log.exiting(STR_METHOD_NAME);
        return l_eqTypeChangeOrderUnitEntry[0];
    }
    
    /**
     * (get訂正後計算単価)<BR>
     * 訂正後計算単価を取得する。<BR>
     * @@return double
     * @@roseuid 433B92D702B7
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
     * @@roseuid 433B92D702D7
     */
    public void setModifiedCalcUnitPrice(double l_dblModifiedCalcUnitPrice) 
    {
        this.modifiedCalcUnitPrice = l_dblModifiedCalcUnitPrice;
    }
    
    /**
     * (get訂正後注文失効日)<BR>
     * 訂正後注文失効日を取得する。<BR>
     * @@return Date
     * @@roseuid 433B92D702E6
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
     * @@roseuid 433B92D70306
     */
    public void setModifiedExpirationDate(Date l_datModifiedExpirationDate) 
    {
        this.modifiedExpirationDate = l_datModifiedExpirationDate;
    }
    
    /**
     * (get訂正後概算受渡代金)<BR>
     * 訂正後概算受渡代金を取得する。<BR>
     * @@return double
     * @@roseuid 433B92D70354
     */
    public double getModifiedEstimatedPrice() 
    {
        return modifiedEstimatedPrice;
    }
    
    /**
     * (set訂正後概算受渡代金)<BR>
     * 訂正後概算受渡代金をセットする。<BR>
     * @@param l_dblEstimatedPrice - (概算受渡代金)<BR>
     * 概算受渡代金。<BR>
     * @@roseuid 433B92D70373
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
     * @@roseuid 433B92D70392
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
     * @@roseuid 433B92D703B1
     */
    public boolean isModifiedCarriedOrder() 
    {
        return this.modifiedIsCarriedOrder;
    }
    
    /**
     * (get扱者)<BR>
     * 扱者を取得する。<BR>
     * @@return WEB3GentradeTrader
     * @@roseuid 433B92D703D1
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
     * @@roseuid 433B92D80008
     */
    public void setTrader(WEB3GentradeTrader l_trader) 
    {
        this.trader = l_trader;
    }
    
    /**
     * (get訂正後単価調整値)<BR>
     * 訂正後単価調整値を取得する。<BR>
     * @@return Double
     * @@roseuid 433BAA650298
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
     * @@roseuid 433BAA6502A8
     */
    public void setModifiedPriceAdjustValue(Double l_modifiedPriceAdjustValue) 
    {
        this.modifiedPriceAdjustValue = l_modifiedPriceAdjustValue;
    }
}
@
