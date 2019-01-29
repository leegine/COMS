head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeSettleContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用返済注文訂正内容(WEB3MarginChangeSettleContractOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 凌建平 (中訊) 新規作成
                   2004/12/09 中尾寿彦(SRA) 残案件対応による修正
                   2004/12/29 岡村和明(SRA) パラメータ名を修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2006/11/01 趙林鵬 (中訊) モデル No.1020, 1048, 1050
*/

package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用返済注文訂正内容）。<BR>
 * <BR>
 * 信用取引の返済注文訂正内容の入力を表現する。<BR>
 * 注文マネージャに渡すパラメタになる。<BR>
 * <BR>
 * xTradeのEqTypeChangeSettleContractOrderSpecを拡張したクラス。
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginChangeSettleContractOrderSpec extends EqTypeChangeSettleContractOrderSpec 
{
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityNewCashBasedOrderSpec.class);
    
    /**
     * コンストラクタ。<BR>
     * @@param l_lngOrderId - (注文ID)
     * @@param l_changeOrderUnitEntry - (注文訂正詳細)
     */
    public WEB3MarginChangeSettleContractOrderSpec(
        long l_lngOrderId,
        EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry)
    {
        super(l_lngOrderId, l_changeOrderUnitEntry);
    }

    /**
     * (訂正後発注条件)<BR>
     * 訂正入力の発注条件。<BR>
     * （0：DEFAULT（条件指定なし）　@1：逆指値　@2：W指値）
     */
    private String modifiedOrderConditionType;
    
    /**
     * (訂正後発注条件演算子)<BR>
     * 訂正入力の発注条件演算子。<><BR>
     * （0：Equal（基準値と同じ値になったら）　@<BR>
     * 1：基準値以上　@<BR>
     * 2：基準値以下）<BR>
     */
    private String modifiedOrderCondOperator;
    
    /**
     * (訂正後逆指値基準値)<BR>
     * 訂正入力の逆指値基準値。<BR>
     * （逆指値、W指値の場合のみセット）<BR>
     */
    private double modifiedStopOrderPrice;
    
    /**
     * (訂正後（W指値）訂正指値)<BR>
     * 訂正入力の（W指値）訂正指値。<BR>
     * （W指値の場合のみセット）<BR>
     */
    private double modifiedWLimitPrice;
    
    /**
     * (訂正後執行条件)<BR>
     * 訂正入力の執行条件。<BR>
     * （1：条件なし　@2：寄り　@3：引け　@6：不出来引け成行）<BR>
     */
    private EqTypeExecutionConditionType modifiedExecutionCondition;
    
    /**
     * (訂正後注文失効日)<BR>
     * 訂正入力の注文失効日。
     */
    private Date modifiedExpirationDate;

    /**
     * (訂正後値段条件)<BR>
     * 訂正入力の値段条件。<BR>
     */
    private String modifiedPriceConditionType;

    /**
     * (訂正後is出来るまで注文)<BR>
     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。<BR>
     * 出来るまで注文であればtrue、以外はfalse。<BR>
     */
    private boolean modifiedIsCarriedOrder;

    /**
     * (訂正後（W指値）執行条件)<BR>
     * 訂正後（W指値）執行条件<BR>
     * （W指値の場合のみセット）<BR>
     */
    private EqTypeExecutionConditionType modifiedWlimitExecCondType;

    /**
     * (（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分<BR>
     */
    private String wlimitEnableStatusDiv;

    /**
     * (create返済注文訂正内容)<BR>
     * （staticメソッド）<BR>
     * 信用返済注文訂正内容オブジェクトを生成し返却する。<BR>
     * <BR>
     * 手続きはシーケンス図「（信用注文）create返済注文訂正内容」を参照。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 原注文の注文ID<BR>
     * @@param l_changeOrderUnitEntry - 注文訂正詳細<BR>
     * @@param l_strModifiedPriceConditionType - (訂正後値段条件)<BR>
     * 訂正入力の値段条件<BR>
     * @@param l_strModifiedOrderConditionType - (訂正後発注条件)<BR>
     * 訂正入力の発注条件。<BR>
     * （0：DEFAULT（条件指定なし）　@1：逆指値　@2：W指値）<BR>
     * @@param l_strModifiedOrderCondOperator - (訂正後発注条件演算子)<BR>
     * 訂正入力の発注条件演算子。<BR>
     * （0：Equal（基準値と同じ値になったら）　@<BR>
     * 1：基準値以上　@<BR>
     * 2：基準値以下）<BR>
     * @@param l_dblModifiedStopOrderPrice - (訂正後逆指値基準値)<BR>
     * 訂正入力の逆指値基準値。<BR>
     * （逆指値、W指値の場合のみセット）<BR>
     * @@param l_dblModifiedWLimitPrice - (訂正後（W指値）訂正指値)<BR>
     * 訂正入力の（W指値）訂正指値。<BR>
     * （W指値の場合のみセット）<BR>
     * @@param l_modifiedExecutionCondition - (訂正後執行条件)<BR>
     * 訂正入力の執行条件。<BR>
     * （1：条件なし　@2：寄り　@3：引け　@6：不出来引け成行）<BR>
     * @@param l_datModifiedExpirationDate - (訂正後注文失効日)<BR>
     * 訂正入力の注文失効日。
     * @@param l_blnModifiedIsCarriedOrder - (訂正後is出来るまで注文)<BR>
     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。<BR>
     * 出来るまで注文であればtrue、以外はfalse。<BR>
     * @@return WEB3MarginCloseMarginChangeOrderSpec
     * @@roseuid 40C8504C0130
     */
    public static WEB3MarginChangeSettleContractOrderSpec createCloseMarginChangeOrderSpec(
        long l_lngOrderId, 
        EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry,
        String l_strModifiedPriceConditionType,
        String l_strModifiedOrderConditionType, 
        String l_strModifiedOrderCondOperator, 
        double l_dblModifiedStopOrderPrice, 
        double l_dblModifiedWLimitPrice, 
        EqTypeExecutionConditionType l_modifiedExecutionCondition, 
        Date l_datModifiedExpirationDate, 
        boolean l_blnModifiedIsCarriedOrder) 
    {
        String STR_METHOD_NAME="createCloseMarginChangeOrderSpec()";
        log.entering(STR_METHOD_NAME);
        
        //1 返済注文訂正内容インスタンスを生成する。
        WEB3MarginChangeSettleContractOrderSpec l_orderSpec = 
            new WEB3MarginChangeSettleContractOrderSpec(l_lngOrderId, l_changeOrderUnitEntry);
        
        //2 訂正後値段条件をセット
        l_orderSpec.setModifiedPriceConditionType(l_strModifiedPriceConditionType);
        
        //3 訂正後発注条件をセット
        l_orderSpec.setModifiedOrderConditionType(l_strModifiedOrderConditionType);
        
        //4 訂正後発注条件演算子をセット
        l_orderSpec.setModifiedOrderCondOperator(l_strModifiedOrderCondOperator);
        
        //5 訂正後逆指値基準値をセット
        l_orderSpec.setModifiedStopOrderPrice(l_dblModifiedStopOrderPrice);
        
        //6 訂正後（W指値）訂正指値をセット
        l_orderSpec.setModifiedWLimitPrice(l_dblModifiedWLimitPrice);
        
        //7 訂正後執行条件（W指値）訂正指値をセット
        l_orderSpec.setModifiedExecutionCondition(l_modifiedExecutionCondition);
        
        //8 訂正後注文失効日をセット
        l_orderSpec.setModifiedExpirationDate(l_datModifiedExpirationDate);
        
        //9 訂正後is出来るまで注文をセット
        l_orderSpec.setModifiedIsCarriedOrder(l_blnModifiedIsCarriedOrder);
        
        log.exiting(STR_METHOD_NAME);
        
        return  l_orderSpec; 
    }

    /**
     * (create返済注文訂正内容)<BR>
     * （staticメソッド）<BR>
     * 信用返済注文訂正内容オブジェクトを生成し返却する。<BR>
     * <BR>
     * １）　@this.create信用返済注文訂正内容()をcallする。<BR>
     * 　@[引数] <BR>
     * 　@　@注文ID：　@（引数より編集） <BR>
     * 　@　@注文訂正詳細：　@（引数より編集） <BR>
     * 　@　@訂正後値段条件：　@（引数より編集） <BR>
     * 　@　@訂正後発注条件：　@（引数より編集） <BR>
     * 　@　@訂正後発注条件演算子：　@（引数より編集） <BR>
     * 　@　@訂正後逆指値基準値：　@（引数より編集） <BR>
     * 　@　@訂正後(W指値)訂正指値：　@（引数より編集） <BR>
     * 　@　@訂正後執行条件：　@（引数より編集） <BR>
     * 　@　@訂正後注文失効日：　@（引数より編集） <BR>
     * 　@　@訂正後is出来るまで注文：　@（引数より編集） <BR>
     * <BR>
     * ２）　@拡張プロパティをセットする <BR>
     * <BR>
     * 　@－訂正後（W指値）執行条件：　@（引数より編集） <BR>
     * 　@－（W指値）注文有効状態：　@（引数より編集）<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 原注文の注文ID<BR>
     * @@param l_changeOrderUnitEntry - (注文訂正詳細)<BR>
     * 注文訂正詳細<BR>
     * @@param l_strModifiedPriceConditionType - (訂正後値段条件)<BR>
     * 訂正入力の値段条件<BR>
     * @@param l_strModifiedOrderConditionType - (訂正後発注条件)<BR>
     * 訂正入力の発注条件。<BR>
     * （0：DEFAULT（条件指定なし）　@1：逆指値　@2：W指値）<BR>
     * @@param l_strModifiedOrderCondOperator - (訂正後発注条件演算子)<BR>
     * 訂正入力の発注条件演算子。<BR>
     * （0：Equal（基準値と同じ値になったら）　@<BR>
     * 1：基準値以上　@<BR>
     * 2：基準値以下）<BR>
     * @@param l_dblModifiedStopOrderPrice - (訂正後逆指値基準値)<BR>
     * 訂正入力の逆指値基準値。<BR>
     * （逆指値、W指値の場合のみセット）<BR>
     * @@param l_dblModifiedWLimitPrice - (訂正後（W指値）訂正指値)<BR>
     * 訂正入力の（W指値）訂正指値。<BR>
     * （W指値の場合のみセット）<BR>
     * @@param l_modifiedExecutionCondition - (訂正後執行条件)<BR>
     * 訂正入力の執行条件。<BR>
     * （1：条件なし　@2：寄り　@3：引け　@6：不出来引け成行）<BR>
     * @@param l_datModifiedExpirationDate - (訂正後注文失効日)<BR>
     * 訂正入力の注文失効日。<BR>
     * @@param l_blnModifiedIsCarriedOrder - (訂正後is出来るまで注文)<BR>
     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。<BR>
     * 出来るまで注文であればtrue、以外はfalse。<BR>
     * @@param l_modifiedWlimitExecCondType - (訂正後（W指値）執行条件)<BR>
     * 訂正後（W指値）執行条件<BR>
     * @@param l_strWlimitEnableStatusDiv - (（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分<BR>
     * @@return WEB3MarginCloseMarginChangeOrderSpec
     * @@roseuid 40C8504C0130
     */
    public static WEB3MarginChangeSettleContractOrderSpec createCloseMarginChangeOrderSpec(
        long l_lngOrderId,
        EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry,
        String l_strModifiedPriceConditionType,
        String l_strModifiedOrderConditionType,
        String l_strModifiedOrderCondOperator,
        double l_dblModifiedStopOrderPrice,
        double l_dblModifiedWLimitPrice,
        EqTypeExecutionConditionType l_modifiedExecutionCondition,
        Date l_datModifiedExpirationDate,
        boolean l_blnModifiedIsCarriedOrder,
        EqTypeExecutionConditionType l_modifiedWlimitExecCondType,
        String l_strWlimitEnableStatusDiv)
    {
        final String STR_METHOD_NAME = "createCloseMarginChangeOrderSpec(long, "
            + "EqTypeContractSettleChangeOrderUnitEntry, String, String, String, "
            + "double, double, EqTypeExecutionConditionType, Date, boolean, "
            + "EqTypeExecutionConditionType, String";
        log.entering(STR_METHOD_NAME);

        //１）　@this.create信用返済注文訂正内容()をcallする。
        WEB3MarginChangeSettleContractOrderSpec l_orderSpec =
            WEB3MarginChangeSettleContractOrderSpec.createCloseMarginChangeOrderSpec(
                l_lngOrderId,
                l_changeOrderUnitEntry,
                l_strModifiedPriceConditionType,
                l_strModifiedOrderConditionType,
                l_strModifiedOrderCondOperator,
                l_dblModifiedStopOrderPrice,
                l_dblModifiedWLimitPrice,
                l_modifiedExecutionCondition,
                l_datModifiedExpirationDate,
                l_blnModifiedIsCarriedOrder);

        //２）　@拡張プロパティをセットする
        //－訂正後（W指値）執行条件：　@（引数より編集）
        l_orderSpec.setModifiedWlimitExecCondType(l_modifiedWlimitExecCondType);

        //－（W指値）注文有効状態：　@（引数より編集）
        l_orderSpec.setWlimitEnableStatusDiv(l_strWlimitEnableStatusDiv);

        log.exiting(STR_METHOD_NAME);

        return l_orderSpec;
    }

    /**
     * (get注文訂正詳細)<BR>
     * （getChangeOrderUnitEntry）<BR>
     * <BR>
     * 注文訂正詳細を取得する。<BR>
     * <BR>
     * getChangeOrderUnitEntries( )にて注文訂正詳細一覧を取得、<BR>
     * 戻り値Listの0番目の要素を返却する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry
     * @@roseuid 40C9203F02ED
     */
    public EqTypeContractSettleChangeOrderUnitEntry getChangeOrderUnitEntry() 
    {
        return this.getChangeOrderUnitEntries()[0];
    }
    
    /**
     * (set訂正後発注条件)<BR>
     * 訂正後発注条件をセットする。
     * @@param l_strModifiedOrderConditionType - 訂正後発注条件
     * @@roseuid 40C56E56034C
     */
    public void setModifiedOrderConditionType(String l_strModifiedOrderConditionType) 
    {
        this.modifiedOrderConditionType = l_strModifiedOrderConditionType;
    }
    
    /**
     * (get訂正後発注条件)<BR>
     * 訂正後発注条件を取得する。
     * @@return String
     * @@roseuid 40C56E56034B
     */
    public String getModifiedOrderConditionType() 
    {
        return this.modifiedOrderConditionType;
    }
    
    /**
     * (set訂正後発注条件演算子)<BR>
     * 訂正後発注条件演算子をセットする。
     * @@param l_strModifiedOrderCondOperator - 訂正後発注条件演算子
     * @@roseuid 40C56E56034E
     */
    public void setModifiedOrderCondOperator(String l_strModifiedOrderCondOperator) 
    {
        this.modifiedOrderCondOperator = l_strModifiedOrderCondOperator;
    }
    
    /**
     * (get訂正後発注条件演算子)<BR>
     * 訂正後発注条件演算子を取得する。
     * @@return String
     * @@roseuid 40C56E560350
     */
    public String getModifiedOrderCondOperator() 
    {
        return this.modifiedOrderCondOperator;
    }
    
    /**
     * (set訂正後逆指値基準値)<BR>
     * 訂正後逆指値基準値をセットする。
     * @@param l_dblModifiedStopOrderPrice - 訂正後逆指値基準値
     * @@roseuid 40C56E560351
     */
    public void setModifiedStopOrderPrice(double l_dblModifiedStopOrderPrice) 
    {
        this.modifiedStopOrderPrice = l_dblModifiedStopOrderPrice;
    }
    
    /**
     * (get訂正後逆指値基準値)<BR>
     * 訂正後逆指値基準値を取得する。
     * @@return double
     * @@roseuid 40C56E560353
     */
    public double getModifiedStopOrderPrice() 
    {
        return this.modifiedStopOrderPrice;
    }
    
    /**
     * (set訂正後（W指値）訂正指値)<BR>
     * 訂正後(W指値)訂正指値をセットする。
     * @@param l_dblModifiedWLimitPrice - 訂正後(W指値)訂正指値
     * @@roseuid 40C56E560354
     */
    public void setModifiedWLimitPrice(double l_dblModifiedWLimitPrice) 
    {
        this.modifiedWLimitPrice = l_dblModifiedWLimitPrice;
    }
    
    /**
     * (get訂正後（W指値）訂正指値)<BR>
     * 訂正後(W指値)訂正指値を取得する。
     * @@return double
     * @@roseuid 40C56E56035A
     */
    public double getModifiedWLimitPrice() 
    {
        return this.modifiedWLimitPrice;
    }
    
    /**
     * (set訂正後執行条件)<BR>
     * 訂正後執行条件をセットする。<BR>
     * @@param l_modifiedExecutionCondition - (訂正後執行条件)<BR>
     * 訂正後の執行条件
     * @@roseuid 40C5706E007C
     */
    public void setModifiedExecutionCondition(EqTypeExecutionConditionType l_modifiedExecutionCondition) 
    {
        this.modifiedExecutionCondition = l_modifiedExecutionCondition;
    }
    
    /**
     * (get訂正後執行条件)<BR>
     * 訂正後執行条件を取得する。<BR>
     * <BR>
     * （注文単位テーブル.執行条件）<BR>
     * @@return EqTypeExecutionConditionType
     * @@roseuid 40C57082004D
     */
    public EqTypeExecutionConditionType getModifiedExecutionCondition() 
    {
        return this.modifiedExecutionCondition;
    }
    
    /**
     * (set訂正後注文失効日)<BR>
     * 訂正後注文失効日をセットする。
     * @@param l_datModifiedExpirationDate - 訂正後注文失効日
     * @@roseuid 40C5709801A5
     */
    public void setModifiedExpirationDate(Date l_datModifiedExpirationDate) 
    {
        this.modifiedExpirationDate = l_datModifiedExpirationDate;
    }
    
    /**
     * (get訂正後注文失効日)<BR>
     * 訂正後注文失効日を取得する。<BR>
     * <BR>
     * （注文単位テーブル.注文失効日付）<BR>
     * @@return Date
     * @@roseuid 40C570AA01D4
     */
    public Date getModifiedExpirationDate() 
    {
        return this.modifiedExpirationDate;
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
     * (set訂正後is出来るまで注文)<BR>
     * 訂正後is出来るまで注文をセットする。
     * @@param l_blnModifiedIsCarriedOrder - (訂正後is出来るまで注文)<BR>
     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。<BR>
     * 出来るまで注文であればtrue、以外はfalse。<BR>
     * @@roseuid 40C714480054
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
     * @@roseuid 40C56E560361
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
     * ２）　@this.get訂正後（W指値）訂正指値( ) == 0 の場合は、falseを返す。<BR>
     * 以外、trueを返す。<BR>
     * @@return boolean
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40C56E560389
     */
    public boolean isModifiedWLimitPrice()
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "isWLimitPrice()";
        log.entering(STR_METHOD_NAME);
         
        //１）　@this.get訂正後発注条件( ) != ”W指値”の場合は、falseを返す。 
        if( !(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.getModifiedOrderConditionType())))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00726, STR_METHOD_NAME);
        }
        
        boolean l_result = true;
        //２）　@this.get訂正後（W指値）訂正指値( ) == 0 の場合は、falseを返す。以外、trueを返す
        if(String.valueOf(this.getModifiedWLimitPrice()) != null)
        {
            if(this.getModifiedWLimitPrice() == 0)
            {
                l_result = false;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
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

}
@
