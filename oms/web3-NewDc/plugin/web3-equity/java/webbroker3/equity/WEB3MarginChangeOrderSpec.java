head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  * (信用新規建注文訂正内容)<BR>
                 :  * 信用取引の新規建注文訂正内容の入力を表現する。<BR>
                 :  * 注文マネージャに渡すパラメタになる。<BR>
                 :  * xTradeのEqTypeChangeOrderSpecを拡張したクラス。(WEB3MarginChangeOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 李松峰 (中訊) 新規作成
                   2004/12/15 中尾寿彦(SRA) 残案件対応による修正
                   2004/12/29 岡村和明(SRA) パラメータ名を修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2006/11/01 趙林鵬 (中訊) モデル No.1008
*/
package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用新規建注文訂正内容）。<BR>
 * <BR>
 * 信用取引の新規建注文訂正内容の入力を表現する。<BR>
 * 注文マネージャに渡すパラメタになる。<BR>
 * <BR>
 * xTradeのEqTypeChangeOrderSpecを拡張したクラス。
 * @@version 1.0
 */
public class WEB3MarginChangeOrderSpec extends EqTypeChangeOrderSpec 
{ 
   /**
    * log<BR>
    */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3MarginChangeOrderSpec.class);

    
    /**
     * （信用新規建注文訂正内容）。<BR>
     * <BR>
     * コンストラクタ。
     * @@param l_lngOrderId (注文ID)
     * @@param l_lngOrderUnitId (注文単位ID)
     * @@param l_dblModifiedOrderQuantity (訂正後注文株数)
     * @@param l_dblModifiedCalcUnitPrice (訂正後指値)
     */
    public WEB3MarginChangeOrderSpec(
        long l_lngOrderId,
        long l_lngOrderUnitId,
        double l_dblModifiedOrderQuantity,
        double l_dblModifiedCalcUnitPrice)
    {
        super(
            l_lngOrderId,
            l_lngOrderUnitId,
            l_dblModifiedOrderQuantity,
            l_dblModifiedCalcUnitPrice);
    }

    /**
     * (訂正後注文株数)<BR>
     * 訂正入力の注文株数
     */
    private double modifiedOrderQuantity;
    
    /**
     * (訂正後計算単価)<BR>
     * 訂正入力の注文単価より算出した計算単価
     */
    private double modifiedCalcUnitPrice;
    
    /**
     * (訂正後執行条件)<BR>
     * 訂正入力の執行条件。<BR>
     * （1：条件なし　@2：寄り　@3：引け　@6：不出来引け成行）<BR>
     */
    private EqTypeExecutionConditionType modifiedExecutionType;
    
    /**
     * (訂正後注文失効日)<BR>
     * 訂正入力の注文失効日
     * (出来るまで注文の場合のみ)
     */
    private Date modifiedExpirationDate;
    
    /**
     * (訂正後値段条件)<BR>
     * 訂正入力の値段条件。<BR>
     */
    private String modifiedPriceConditionType;
    
    /**
     * (訂正後発注条件)<BR>
     * 訂正入力の発注条件<BR>
     * 0：DEFAULT（条件指定なし）<BR>
     * 1：逆指値<BR>
     * 2：W指値<BR>
     */
    private String modifiedOrderConditionType;
    
    /**
     * (訂正後発注条件演算子)<BR>
     * 訂正入力の発注条件演算子<BR>
     * （0：Equal（基準値と同じ値になったら）　@<BR>
     * 1：基準値以上　@<BR>
     * 2：基準値以下）<BR>
     */
    private String modifiedOrderCondOperator;
    
    /**
     * (訂正後逆指値基準値)<BR>
     * 訂正入力の逆指値基準値<BR>
     * （逆指値、W指値の場合のみセット）<BR>
     */
    private double modifiedStopOrderPrice;
    
    /**
     * (訂正後(W指値)訂正指値)<BR>
     * 訂正入力の（W指値）訂正指値。
     * （W指値の場合のみセット）
     */
    private double modifiedWLimitPrice;

    /**
     * (訂正後（W指値）執行条件)<BR>
     * 訂正後（W指値）執行条件<BR>
     * （W指値の場合のみセット）<BR>
     */
    private EqTypeExecutionConditionType modifiedWlimitExecCondType;

    /**
     * (訂正後建代金)<BR>
     */
    private double modifiedContractAmount;
    
    /**
     * (訂正後is出来るまで注文)<BR>
     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。<BR>
     * 出来るまで注文であればtrue、以外はfalse。<BR>
     */
    private boolean modifiedIsCarriedOrder;
    
    /**
     * (扱者)<BR>
     * 代理入力扱者。
     */
    private Trader trader;

    /**
     * (（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分<BR>
     */
    private String wlimitEnableStatusDiv;

    /**
     * (create新規建注文訂正内容)<BR>
     * （staticメソッド）<BR>
     * 信用新規建注文訂正内容オブジェクトを生成し返却する。<BR>
     * <BR>
     * 手続きはシーケンス図「（信用注文）create新規建注文訂正内容」を参照。<BR>
     * @@param l_lngOrderId - 注文ID
     * @@param l_lngOrderUnitId - 注文単位ID
     * @@param l_dblModifiedOrderQuantity - (訂正後注文株数)<BR>
     * 訂正入力の注文株数
     * @@param l_dblModifiedCalcUnitPrice - (訂正後指値)<BR>
     * 訂正入力の注文単価
     * @@param l_modifiedExecutionType - (訂正後執行条件)<BR>
     * 訂正入力の執行条件
     * @@param l_datModifiedExpirationDate - (訂正後注文失効日)<BR>
     * 訂正入力の注文失効日
     * @@param l_strModifiedPriceConditionType - (訂正後値段条件)<BR>
     * 訂正入力の値段条件
     * @@param l_strOrderConditionType - 発注条件
     * @@param l_strModifiedOrderCondOperat - (訂正後発注条件演算子)<BR>
     * 訂正入力の発注条件演算子
     * @@param l_dblModifiedStopOrderPrice - (訂正後逆指値基準値)<BR>
     * 訂正入力の逆指値基準値
     * @@param l_dblModifiedWLimitPrice - (訂正後（W指値）訂正指値)<BR>
     * 訂正入力の(W指値)訂正指値
     * @@param l_blnModifiedIsCarriedOrder - (訂正後is出来るまで注文)<BR>
     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。<BR>
     * 出来るまで注文であればtrue、以外はfalse。<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 扱者オブジェクト
     * @@return WEB3MarginChangeOrderSpec
     * @@roseuid 40E11F070120
     */
    public static WEB3MarginChangeOrderSpec createOpenMarginChangeOrderSpec(
        long l_lngOrderId,
        long l_lngOrderUnitId,
        double l_dblModifiedOrderQuantity,
        double l_dblModifiedCalcUnitPrice,
        EqTypeExecutionConditionType l_modifiedExecutionType,
        Date l_datModifiedExpirationDate,
        String l_strModifiedPriceConditionType,
        String l_strOrderConditionType,
        String l_strModifiedOrderCondOperat,
        double l_dblModifiedStopOrderPrice,
        double l_dblModifiedWLimitPrice,
        boolean l_blnModifiedIsCarriedOrder,
        Trader l_trader) 
    {
        String STR_METHOD_NAME="createOpenMarginChangeOrderSpec(long,long,double,double,EqTypeExecutionConditionType,Date,String,String,double,double,boolean,Trader)";
        log.entering(STR_METHOD_NAME);
        //手続きはシーケンス図「（信用注文）create新規建注文訂正内容」を参照。<BR>
        //    * @@param l_lngOrderId - 注文ID
        //    * @@param l_lngOrderUnitId - 注文単位ID
        //    * @@param l_dblModifiedOrderQuantity - (訂正後注文株数)<BR>
        //    * @@param l_dblModifiedCalcUnitPrice - (訂正後指値)<BR>
        WEB3MarginChangeOrderSpec l_marginChangeOrderSpec = new WEB3MarginChangeOrderSpec(l_lngOrderId,l_lngOrderUnitId,l_dblModifiedOrderQuantity,l_dblModifiedCalcUnitPrice);
        // * @@param l_modifiedExecutionType - (訂正後執行条件)<BR>
        //*(訂正後注文株数)
        l_marginChangeOrderSpec.setModifiedOrderQuantity(l_dblModifiedOrderQuantity);
        //* 訂正入力の執行条件
        l_marginChangeOrderSpec.setModifiedExecutionType(l_modifiedExecutionType);
        //    * @@param l_datModifiedExpirationDate - (訂正後注文失効日)<BR>
        //    * 訂正入力の注文失効日
        l_marginChangeOrderSpec.setModifiedExpirationDate(l_datModifiedExpirationDate);
        //    * 訂正入力の値段条件
        l_marginChangeOrderSpec.setModifiedPriceConditionType(l_strModifiedPriceConditionType);
        //    * @@param l_strOrderConditionType - 発注条件
        l_marginChangeOrderSpec.setModifiedOrderConditionType(l_strOrderConditionType);
        //    * @@param l_strModifiedOrderCondOperat - (訂正後発注条件演算子)<BR>
        //    * 訂正入力の発注条件演算子
        l_marginChangeOrderSpec.setModifiedOrderCondOperator(l_strModifiedOrderCondOperat);
        //* @@param l_dblModifiedStopOrderPrice - (訂正後逆指値基準値)<BR>
        //     * 訂正入力の逆指値基準値
        l_marginChangeOrderSpec.setModifiedStopOrderPrice(l_dblModifiedStopOrderPrice);
        //     * @@param l_dblModifiedWLimitPrice - (訂正後（W指値）訂正指値)<BR>
        //     * 訂正入力の(W指値)訂正指値
        l_marginChangeOrderSpec.setModifiedWLimitPrice(l_dblModifiedWLimitPrice);
        //     * @@param l_blnModifiedIsCarriedOrder - (訂正後is出来るまで注文)<BR>
        //     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。<BR>
        l_marginChangeOrderSpec.setModifiedIsCarriedOrder(l_blnModifiedIsCarriedOrder);
        //     * @@param l_trader - (代理入力者)<BR>
        //     * 扱者オブジェクト
        l_marginChangeOrderSpec.setTrader(l_trader);
        log.exiting(STR_METHOD_NAME);
        return l_marginChangeOrderSpec;
    }

    /**
     * (create新規建注文訂正内容)<BR>
     * （staticメソッド）<BR>
     * 信用新規建注文訂正内容オブジェクトを生成し返却する。<BR>
     * <BR>
     * １）　@this.create信用新規建注文訂正内容()をcallする。<BR>
     * 　@[引数] <BR>
     * 　@　@注文ID：　@（引数より編集）<BR>
     * 　@　@注文単位ID：　@（引数より編集） <BR>
     * 　@　@訂正後注文株数：　@（引数より編集） <BR>
     * 　@　@訂正後指値：　@（引数より編集） <BR>
     * 　@　@訂正後執行条件：　@（引数より編集） <BR>
     * 　@　@訂正後注文失効日：　@（引数より編集） <BR>
     * 　@　@訂正後値段条件：　@（引数より編集） <BR>
     * 　@　@発注条件：　@（引数より編集） <BR>
     * 　@　@訂正後発注条件演算子：　@（引数より編集） <BR>
     * 　@　@訂正後逆指値基準値：　@（引数より編集） <BR>
     * 　@　@訂正後(W指値)訂正指値：　@（引数より編集） <BR>
     * 　@　@訂正後is出来るまで注文：　@（引数より編集） <BR>
     * 　@　@代理入力者：　@（引数より編集） <BR>
     * <BR>
     * ２）　@拡張プロパティをセットする <BR>
     * <BR>
     * 　@－訂正後（W指値）執行条件：　@（引数より編集） <BR>
     * 　@－（W指値）注文有効状態：　@（引数より編集）<BR>
     * @@param l_lngOrderId - (注文ID)
     * 注文ID
     * @@param l_lngOrderUnitId - (注文単位ID)
     * 注文単位ID
     * @@param l_dblModifiedOrderQuantity - (訂正後注文株数)<BR>
     * 訂正入力の注文株数
     * @@param l_dblModifiedCalcUnitPrice - (訂正後指値)<BR>
     * 訂正入力の注文単価
     * @@param l_modifiedExecutionType - (訂正後執行条件)<BR>
     * 訂正入力の執行条件
     * @@param l_datModifiedExpirationDate - (訂正後注文失効日)<BR>
     * 訂正入力の注文失効日
     * @@param l_strModifiedPriceConditionType - (訂正後値段条件)<BR>
     * 訂正入力の値段条件
     * @@param l_strOrderConditionType - (発注条件)
     * 発注条件
     * @@param l_strModifiedOrderCondOperat - (訂正後発注条件演算子)<BR>
     * 訂正入力の発注条件演算子
     * @@param l_dblModifiedStopOrderPrice - (訂正後逆指値基準値)<BR>
     * 訂正入力の逆指値基準値
     * @@param l_dblModifiedWLimitPrice - (訂正後（W指値）訂正指値)<BR>
     * 訂正入力の(W指値)訂正指値
     * @@param l_modifiedWlimitExecCondType - (訂正後（W指値）執行条件)<BR>
     * 訂正入力の（W指値）執行条件<BR>
     * @@param l_blnModifiedIsCarriedOrder - (訂正後is出来るまで注文)<BR>
     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。<BR>
     * 出来るまで注文であればtrue、以外はfalse。<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 扱者オブジェクト
     * @@param l_strWlimitEnableStatusDiv - (（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分<BR>
     * @@return WEB3MarginChangeOrderSpec
     * @@roseuid 40E11F070120
     */
    public static WEB3MarginChangeOrderSpec createOpenMarginChangeOrderSpec(
        long l_lngOrderId,
        long l_lngOrderUnitId,
        double l_dblModifiedOrderQuantity,
        double l_dblModifiedCalcUnitPrice,
        EqTypeExecutionConditionType l_modifiedExecutionType,
        Date l_datModifiedExpirationDate,
        String l_strModifiedPriceConditionType,
        String l_strOrderConditionType,
        String l_strModifiedOrderCondOperat,
        double l_dblModifiedStopOrderPrice,
        double l_dblModifiedWLimitPrice,
        EqTypeExecutionConditionType l_modifiedWlimitExecCondType,
        boolean l_blnModifiedIsCarriedOrder,
        Trader l_trader,
        String l_strWlimitEnableStatusDiv)
    {
        final String STR_METHOD_NAME = "createOpenMarginChangeOrderSpec(long, long, "
            + "double, double, EqTypeExecutionConditionType, Date, "
            + "String, String, String, double, double, EqTypeExecutionConditionType, boolean, "
            + "Trader, String";
        log.entering(STR_METHOD_NAME);

        //１）　@this.create信用新規建注文訂正内容()をcallする。
        WEB3MarginChangeOrderSpec l_orderSpec =
            WEB3MarginChangeOrderSpec.createOpenMarginChangeOrderSpec(
                l_lngOrderId,
                l_lngOrderUnitId,
                l_dblModifiedOrderQuantity,
                l_dblModifiedCalcUnitPrice,
                l_modifiedExecutionType,
                l_datModifiedExpirationDate,
                l_strModifiedPriceConditionType,
                l_strOrderConditionType,
                l_strModifiedOrderCondOperat,
                l_dblModifiedStopOrderPrice,
                l_dblModifiedWLimitPrice,
                l_blnModifiedIsCarriedOrder,
                l_trader);

        //２）　@拡張プロパティをセットする
        //－訂正後（W指値）執行条件：　@（引数より編集）
        l_orderSpec.setModifiedWlimitExecCondType(l_modifiedWlimitExecCondType);

        //　@－（W指値）注文有効状態：　@（引数より編集）
        l_orderSpec.setWlimitEnableStatusDiv(l_strWlimitEnableStatusDiv);

        log.exiting(STR_METHOD_NAME);

        return l_orderSpec;
    }

    /**
     * (get新規建注文訂正内容詳細)<BR>
     * （getChangeOrderUnitEntryのオーバーライド）<BR>
     * <BR>
     * 新規建注文訂正内容詳細を取得する。<BR>
     * <BR>
     * getChangeOrderUnitEntries( )にて新規建注文訂正内容詳細一覧を取得、<BR>
     * 戻り値Listの0番目の要素を返却する。<BR>
     * @@return EqTypeChangeOrderUnitEntry
     * @@roseuid 40E3D15C03E6
     */
    public EqTypeChangeOrderUnitEntry getChangeOrderUnitEntry() 
    {
        return super.getChangeOrderUnitEntries()[0];
    }
    
    /**
     * (get訂正後注文株数)<BR>
     * 訂正後注文株数を取得する。
     * @@return double
     * @@roseuid 40E11A9B0362
     */
    public double getModifiedOrderQuantity() 
    {
        return modifiedOrderQuantity;
    }
    
    /**
     * (set訂正後注文株数)<BR>
     * 訂正後注文株数をセットする。
     * @@param l_dblModifiedOrderQuantity - 訂正入力の注文株数
     * @@roseuid 40E11ADB0017
     */
    public void setModifiedOrderQuantity(double l_dblModifiedOrderQuantity) 
    {
        this.modifiedOrderQuantity = l_dblModifiedOrderQuantity;
    }
    
    /**
     * (get訂正後計算単価)<BR>
     * 訂正後計算単価を取得する。
     * @@return double
     * @@roseuid 40E11B3E019D
     */
    public double getModifiedCalcUnitPrice() 
    {
        return modifiedCalcUnitPrice;
    }
    
    /**
     * (set訂正後計算単価)<BR>
     * 訂正後計算単価をセットする。
     * @@param l_dblModifiedCalcUnitPrice - (訂正後計算単価)<BR>
     * 訂正入力の注文単価より算出した計算単価
     * @@roseuid 40E11B3E01AD
     */
    public void setModifiedCalcUnitPrice(double l_dblModifiedCalcUnitPrice) 
    {
        this.modifiedCalcUnitPrice = l_dblModifiedCalcUnitPrice;
    }
    
    /**
     * (get訂正後執行条件)<BR>
     * 訂正後執行条件を取得する。
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType
     * @@roseuid 40E11B7E0036
     */
    public EqTypeExecutionConditionType getModifiedExecutionType() 
    {
        return this.modifiedExecutionType ;
    }
    
    /**
     * (set訂正後執行条件)<BR>
     * 訂正後執行条件をセットする。
     * @@param l_modifiedExecutionType - (訂正後執行条件)<BR>
     * 訂正入力の執行条件
     * @@roseuid 40E11B7E0055
     */
    public void setModifiedExecutionType(EqTypeExecutionConditionType l_modifiedExecutionType) 
    {
        this.modifiedExecutionType = l_modifiedExecutionType;
    }
    
    /**
     * (get訂正後注文失効日)<BR>
     * 訂正後注文失効日を取得する。
     * @@return Date
     * @@roseuid 40E11C170074
     */
    public Date getModifiedExpirationDate() 
    {
        return this.modifiedExpirationDate;
    }
    
    /**
     * (set訂正後注文失効日)<BR>
     * 訂正後注文失効日をセットする。
     * @@param l_datModifiedExpirationDate - (訂正後注文失効日)<BR>
     * 訂正入力の注文失効日
     * @@roseuid 40E11C170084
     */
    public void setModifiedExpirationDate(Date l_datModifiedExpirationDate) 
    {
        this.modifiedExpirationDate = l_datModifiedExpirationDate;
    }
    
    /**
     * (set訂正後値段条件)<BR>
     * 値段条件をセットする。<BR>
     * @@param l_strChangeAfterPriceConditionType - 訂正後値段条件<BR>
     * 訂正後値段条件<BR>
     * 0：　@DEFAULT(条件指定なし)<BR>
     * 1：　@現在値指値注文<BR>
     * 3：　@優先指値注文<BR>
     * 5：　@成行残数指値注文<BR>
     * 7：　@成行残数取消注文<BR>
     */
    public void setModifiedPriceConditionType(String l_strChangeAfterPriceConditionType) 
    {
        this.modifiedPriceConditionType = l_strChangeAfterPriceConditionType;
    }
    
    /**
     * (get訂正後値段条件)<BR>
     * 訂正後の値段条件を取得する。<BR>
     * @@return String
     */
    public String getModifiedPriceConditionType() 
    {
        return this.modifiedPriceConditionType;
    }
    
    /**
     * (get訂正後発注条件)<BR>
     * 発注条件区分を取得する。
     * @@return String
     * @@roseuid 40E11C790391
     */
    public String getModifiedOrderConditionType() 
    {
     return this.modifiedOrderConditionType;
    }
    
    /**
     * (set訂正後発注条件)<BR>
     * 発注条件区分をセットする。
     * @@param l_strModifiedOrderConditionType - 訂正後発注条件<BR>
     * 0：DEFAULT（条件指定なし）<BR>
     * 1：逆指値<BR>
     * 2：W指値<BR>
     * @@roseuid 40E11C7903B1
     */
    public void setModifiedOrderConditionType(String l_strModifiedOrderConditionType) 
    {
        this.modifiedOrderConditionType = l_strModifiedOrderConditionType;
    }
    
    /**
     * (get訂正後発注条件演算子)<BR>
     * 訂正後発注条件演算子を取得する。
     * @@return String
     * @@roseuid 40E11D940065
     */
    public String getModifiedOrderCondOperator() 
    {
        return this.modifiedOrderCondOperator;
    }
    
    /**
     * (set訂正後発注条件演算子)<BR>
     * 訂正後発注条件演算子をセットする。
     * @@param l_strModifiedOrderCondOperator - (訂正後発注条件演算子)<BR>
     * 訂正入力の発注条件演算子<BR>
     * （0：Equal（基準値と同じ値になったら）　@<BR>
     * 1：基準値以上　@<BR>
     * 2：基準値以下）<BR>
     * @@roseuid 40E11D940074
     */
    public void setModifiedOrderCondOperator(String l_strModifiedOrderCondOperator) 
    {
        this.modifiedOrderCondOperator = l_strModifiedOrderCondOperator;
    }
    
    /**
     * (get訂正後逆指値基準値)<BR>
     * 訂正後逆指値基準値を取得する。
     * @@return double
     * @@roseuid 40E11D4B02A7
     */
    public double getModifiedStopOrderPrice() 
    {
        return this.modifiedStopOrderPrice;
    }
    
    /**
     * (set訂正後逆指値基準値)<BR>
     * 訂正後逆指値基準値をセットする。
     * @@param l_dblModifiedStopOrderPrice - (訂正後逆指値基準値)<BR>
     * 訂正入力の逆指値基準値
     * @@roseuid 40E11D4B02C6
     */
    public void setModifiedStopOrderPrice(double l_dblModifiedStopOrderPrice) 
    {
        this.modifiedStopOrderPrice = l_dblModifiedStopOrderPrice;
    }
    
    /**
     * (get訂正後（W指値）訂正指値)<BR>
     * 訂正後(W指値)訂正指値を取得する。
     * @@return double
     * @@roseuid 40E11DE0014F
     */
    public double getModifiedWLimitPrice() 
    {
     return this.modifiedWLimitPrice;
    }
    
    /**
     * (set訂正後（W指値）訂正指値)<BR>
     * 訂正後(W指値)訂正指値をセットする。
     * @@param l_dblModifiedWLimitPrice - (訂正後（W指値）訂正指値)<BR>
     * 訂正入力の(W指値)訂正指値
     * @@roseuid 40E11DE0015F
     */
    public void setModifiedWLimitPrice(double l_dblModifiedWLimitPrice) 
    {
        this.modifiedWLimitPrice = l_dblModifiedWLimitPrice;
    }
    
    /**
     * (get訂正後建代金)<BR>
     * 訂正後建代金を取得する。
     * @@return double
     * @@roseuid 40E8D99E0139
     */
    public double getModifiedContractAmount() 
    {
        return this.modifiedContractAmount;
    }
    
    /**
     * (set訂正後建代金)<BR>
     * 訂正後建代金をセットする。
     * @@param l_dblPrice - 建代金。
     * @@roseuid 40E8D99E0129
     */
    public void setModifiedContractAmount(double l_dblContractAmount) 
    {
        this.modifiedContractAmount = l_dblContractAmount;
    }
    
    /**
     * (set訂正後is出来るまで注文)<BR>
     * 訂正後is出来るまで注文をセットする。
     * @@param l_blnModifiedIsCarriedOrder - (訂正後is出来るまで注文)<BR>
     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。
     * 出来るまで注文であればtrue、以外はfalse。
     * @@roseuid 40E4CFF30370
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
     * @@roseuid 40E4CFBA0276
     */
    public boolean isModifiedIsCarriedOrder() 
    {
        return this.modifiedIsCarriedOrder;
    }
    
    /**
     * (is訂正後（W指値）訂正指値)<BR>
     * ・W指値注文の場合は「（W指値）訂正指値」が指値であるかどうかを返す。<BR>
     * 　@指値の場合はtrueを、成行の場合はfalseを返す。<BR>
     * ・W指値注文でない場合は、例外をthrowする。<BR>
     * <BR>
     * １）　@this.get訂正後発注条件( ) != ”W指値”の場合は、例外をthrowする。<BR>
     * <BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00726<BR>   
     * <BR>
     * ２）　@this.get訂正後（W指値）訂正指値( ) == 0 の場合は、falseを返す。以外、<BR>
     * trueを返す。<BR>
     * @@return boolean
     * @@roseuid 40E4C1A8039E
     */
    public boolean isModifiedWLimitPrice() 
        throws WEB3BusinessLayerException
    {
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.getModifiedOrderConditionType()))
        {
            //throw new  
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00726, "isModifiedWLimitPrice()");
        } 
        if(this.getModifiedWLimitPrice() == 0)
        {
            return false;
        }
        return true;
    }
    
    /**
     * (get扱者)<BR>
     * 扱者を取得する。
     * @@return Trader
     * @@roseuid 40ED1C9F0216
     */
    public Trader getTrader() 
    {
        return this.trader;
    }
    
    /**
     * (set扱者)<BR>
     * 扱者をセットする。
     * @@param l_trader - (代理入力者)<BR>
     * 扱者オブジェクト
     * @@roseuid 40ED1C9F0225
     */
    public void setTrader(Trader l_trader) 
    {
        this.trader = l_trader;
    }

    /**
     * (set訂正後（W指値）執行条件 )<BR>
     * 訂正後（W指値）執行条件をセットする。<BR>
     * @@param l_modifiedWlimitExecCondType - (訂正後（W指値）執行条件)<BR>
     * 訂正後（W指値）執行条件<BR>
     * @@roseuid 40B30A97020D
     */
    public void setModifiedWlimitExecCondType(
        EqTypeExecutionConditionType l_modifiedWlimitExecCondType)
    {
        this.modifiedWlimitExecCondType = l_modifiedWlimitExecCondType;
    }

    /**
     * (get訂正後（W指値）執行条件)<BR>
     * 訂正後（W指値）執行条件を取得する。<BR>
     * @@return EqTypeExecutionConditionType
     * @@roseuid 40B30A97021E
     */
    public EqTypeExecutionConditionType getModifiedWlimitExecCondType()
    {
        return this.modifiedWlimitExecCondType;
    }

    /**
     * (set（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分をセットする。<BR>
     * @@param l_wlimitEnableStatusDiv - (（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分<BR>
     * @@roseuid 40B30A97020D
     */
    public void setWlimitEnableStatusDiv(String  l_strWlimitEnableStatusDiv)
    {
        this.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;
    }

    /**
     * (get（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分を取得する。<BR>
     * @@return String
     * @@roseuid 40B30A97021E
     */
    public String  getWlimitEnableStatusDiv()
    {
        return this.wlimitEnableStatusDiv;
    }

    /**
     * (isストップ注文有効)<BR>
     * this.（W指値）有効状態区分 == "ストップ注文有効"の場合、true、<BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     */
    public boolean isStopOrderEnable()
    {
        if (WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE.equals(
            this.wlimitEnableStatusDiv))
        {
            return true;
        }
        return false;
    }

}
@
