head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSettleContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用返済注文内容(WEB3MarginCloseMarginOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 凌建平 (中訊) 新規作成
Revesion History : 2004/12/09 中尾寿彦(SRA) 残案件対応による修正
Revesion History : 2004/12/09 岡村和明(SRA) メソッドの変数名を変更
                 : 2006/11/01 趙林鵬 (中訊) モデル No.1010. 1051
*/

package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用返済注文内容）。<BR>
 * <BR>
 * 信用取引・返済注文内容の入力を表現する。<BR>
 * 注文マネージャに渡すパラメタになる。<BR>
 * <BR>
 * xTradeのEqTypeSettleContractOrderSpecを拡張したクラス。
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginSettleContractOrderSpec extends EqTypeSettleContractOrderSpec 
{
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityNewCashBasedOrderSpec.class);
    
    /**
     * コンストラクタ。<BR>
     * @@param l_trader - (扱者)<BR>
     * @@param l_closeMarginContractEntry - (決済建株エントリ)<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * @@param l_executionCondition - (執行条件)<BR>
     * @@param l_datExpirationDate - (注文失効日)<BR>
     * @@param l_taxType - (税区分)<BR>
     */
    protected WEB3MarginSettleContractOrderSpec(
        Trader l_trader,
        EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_executionCondition,
        Date l_datExpirationDate,
        TaxTypeEnum l_taxType)
    {
        super(
            l_trader,
            l_closeMarginContractEntry,
            l_dblLimitPrice,
            l_executionCondition,
            l_datExpirationDate,
            l_taxType);
    }

    /**
     * (発注日。)<BR>
     * 市場に発注する処理日付。<BR>
     * （取引カレンダより取得する）<BR>
     */
    private Date bizDate;
    
    /**
     * (値段条件)。<BR>
     * （0：　@DEFAULT(条件指定なし)<BR>
     * 　@1：　@現在値指値注文<BR>
     * 　@3：　@優先指値注文<BR>
     * 　@5：　@成行残数指値注文<BR>
     * 　@7：　@成行残数取消注文）<BR>
     */
    private String priceConditionType;
    
    /**
     * (発注条件。)<BR>
     * （0：DEFAULT（条件指定なし）　@1：逆指値　@2：W指値）<BR>
     */
    private String orderConditionType;
    
    /**
     * (発注条件演算子。)<BR>
     * （0：Equal（基準値と同じ値になったら）　@<BR>
     * 1：基準値以上　@<BR>
     * 2：基準値以下）<BR>
     */
    private String orderCondOperator;
    
    /**
     * (逆指値基準値。)<BR>
     * （逆指値、W指値の場合のみセット）<BR>
     */
    private double stopOrderPrice;
    
    /**
     * (（W指値）訂正指値。)<BR>
     * （W指値の場合のみセット）<BR>
     */
    private double wLimitPrice;
    
    /**
     * (決済順序区分。)<BR>
     * (0：ランダム　@1：単価益順　@2：単価損順　@3：建日順)<BR>
     * 一括返済の場合セット。<BR>
     */
    private String closingOrderType;
    
    /**
     * (初回注文の注文単位ID)<BR>
     */
    private Long firstOrderUnitId;

    /**
     * (（Ｗ指値）執行条件)<BR>
     * （Ｗ指値）執行条件<BR>
     */
    private EqTypeExecutionConditionType wlimitExecCondType;

    /**
     * (create返済注文内容)<BR>
     * （staticメソッド）<BR>
     * 信用返済注文内容オブジェクトを生成し返却する。<BR>
     * <BR>
     * 手続きはシーケンス図「（信用注文）create返済注文内容」を参照。<BR>
     * @@param l_trader - (扱者)<BR>
     * 扱者オブジェクト。<BR>
     * @@param l_closeMarginContractEntry - (決済建株エントリ)<BR>
     * 決済建株エントリの配列。<BR>
     * @@param l_dblLimitPrice - 指値。<BR>
     * @@param l_executionCondition - 執行条件<BR>
     * (0：その他　@1：条件なし　@2：寄り　@3：引け　@6：不出来引け成行）<BR>
     * @@param l_datExpirationDate - 注文失効日。<BR>
     * @@param l_taxType - (税区分)<BR>
     * 0:その他,　@1:一般,　@2:特定,　@3:特定口座かつ源泉徴収<BR>
     * （TaxTypeEnumにて定義）<BR>
     * @@param l_strPriceConditionType - (値段条件)<BR>
     * 値段条件。<BR>
     * @@param l_strOrderConditionType - 発注条件。<BR>
     * @@param l_strOrderCondOperator - 発注条件演算子。<BR>
     * @@param l_dblStopOrderPrice - 逆指値基準値。<BR>
     * @@param l_dblWLimitPrice - (W指値)訂正指値。<BR>
     * @@param l_strClosingOrderType - 決済順序区分。<BR>
     * (0：ランダム　@1：単価益順　@2：単価損順　@3：建日順)
     * @@param l_lngFirstOrderUnitId - (初回注文の注文単位ID)<BR>
     * 注文繰越での注文作成時には、繰越対象の注文単位.注文単位IDをセット。<BR>
     * 上記以外の新規注文登録時には、nullをセット。<BR>
     * @@return WEB3MarginCloseMarginOrderSpec
     * @@throws WEB3SystemLayerException
     * @@roseuid 40B2FDB30326
     */
    public static WEB3MarginSettleContractOrderSpec createCloseMarginOrderSpec(
        Trader l_trader, 
        EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry, 
        double l_dblLimitPrice, 
        EqTypeExecutionConditionType l_executionCondition, 
        Date l_datExpirationDate, 
        TaxTypeEnum l_taxType,
        String l_strPriceConditionType,
        String l_strOrderConditionType, 
        String l_strOrderCondOperator, 
        double l_dblStopOrderPrice, 
        double l_dblWLimitPrice, 
        String l_strClosingOrderType, 
        Long l_lngFirstOrderUnitId)
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME="createCloseMarginOrderSpec()";
        log.entering(STR_METHOD_NAME);
        
        //1 注文失効日がnullの場合、発注日を指定する
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        if(l_datExpirationDate == null)
        {
            l_datExpirationDate = l_datOrderBizDate;
            
        }
        
        //2 返済注文内容インスタンスを生成する。
        WEB3MarginSettleContractOrderSpec l_orderSpec = 
            new WEB3MarginSettleContractOrderSpec(
                l_trader,
                l_closeMarginContractEntry,
                l_dblLimitPrice,
                l_executionCondition,
                l_datExpirationDate,
                l_taxType);
       
        //3 発注日をセット
        l_orderSpec.setBizDate(l_datExpirationDate);
        
        //4 値段条件をセット
        l_orderSpec.setPriceConditionType(l_strPriceConditionType);
        
        //5 発注条件をセット
        l_orderSpec.setOrderConditionType(l_strOrderConditionType);
        
        //6 発注条件演算子をセット
        l_orderSpec.setOrderCondOperator(l_strOrderCondOperator);
        
        //7 逆指値基準値をセット
        l_orderSpec.setStopOrderPrice(l_dblStopOrderPrice);
        
        //8（W指値）訂正指値をセット
        l_orderSpec.setWLimitPrice(l_dblWLimitPrice);
        
        //9 弁済区分をセット
        l_orderSpec.setClosingOrderType(l_strClosingOrderType);
        
        //10 初回注文の注文単位IDをセット
        l_orderSpec.setFirstOrderUnitId(l_lngFirstOrderUnitId);
        
        log.exiting(STR_METHOD_NAME);
        
        return  l_orderSpec;
    }

    /**
     * (create返済注文内容)<BR>
     * （staticメソッド）<BR>
     * 信用返済注文内容オブジェクトを生成し返却する。<BR>
     * <BR>
     * １）　@信用返済注文内容.create返済注文内容( )をdelegateする。<BR>
     * <BR>
     * 　@　@　@引数設定は以下のように行う。<BR>
     * <BR>
     * 　@扱者 :　@パラメータ.同項目 <BR>
     * 　@決済建株エントリ : パラメータ.同項目 <BR>
     * 　@指値 : パラメータ.同項目 <BR>
     * 　@執行条件 :　@パラメータ.同項目 <BR>
     * 　@注文失効日 : パラメータ.同項目 <BR>
     * 　@税区分 : パラメータ.同項目 <BR>
     * 　@値段条件 : パラメータ.同項目 <BR>
     * 　@発注条件 : パラメータ.同項目 <BR>
     * 　@発注条件演算子 : パラメータ.同項目 <BR>
     * 　@逆指値基準値 : パラメータ.同項目 <BR>
     * 　@(W指値)訂正指値 : パラメータ.同項目 <BR>
     * 　@決済順序区分 : パラメータ.同項目 <BR>
     * 　@初回注文の注文単位ID : パラメータ.同項目 <BR>
     * <BR>
     * <BR>
     * ２）　@set（Ｗ指値）執行条件( )をcallする。<BR>
     * <BR>
     * 　@　@　@引数設定は以下のように行う。<BR>
     * <BR>
     * 　@　@　@（Ｗ指値）執行条件：パラメータ.同項目<BR>
     * @@param l_trader - (扱者)<BR>
     * 扱者オブジェクト。<BR>
     * @@param l_closeMarginContractEntrys - (決済建株エントリ)<BR>
     * 決済建株エントリの配列。<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値。<BR>
     * @@param l_execCondType - (執行条件)<BR>
     * 執行条件<BR>
     * (0：その他　@1：条件なし　@2：寄り　@3：引け　@6：不出来引け成行）<BR>
     * @@param l_datExpirationDate - (注文失効日)<BR>
     * 注文失効日。<BR>
     * @@param l_taxType - (税区分)<BR>
     * 0:その他,　@1:一般,　@2:特定,　@3:特定口座かつ源泉徴収<BR>
     * （TaxTypeEnumにて定義）<BR>
     * @@param l_strPriceConditionType - (値段条件)<BR>
     * 値段条件。<BR>
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件。<BR>
     * @@param l_strOrderCondOperator - (発注条件演算子)<BR>
     * 発注条件演算子。<BR>
     * @@param l_dblStopOrderPrice - (逆指値基準値)<BR>
     * 逆指値基準値。<BR>
     * @@param l_dblWLimitPrice - ((W指値)訂正指値)<BR>
     * (W指値)訂正指値。<BR>
     * @@param l_strClosingOrderType - (決済順序区分)<BR>
     * 決済順序区分。<BR>
     * (0：ランダム　@1：単価益順　@2：単価損順　@3：建日順)<BR>
     * @@param l_lngFirstOrderUnitId - (初回注文の注文単位ID)<BR>
     * 注文繰越での注文作成時には、繰越対象の注文単位.注文単位IDをセット。<BR>
     * 上記以外の新規注文登録時には、nullをセット。<BR>
     * @@param l_wlimitExecCondType - (（Ｗ指値）執行条件)<BR>
     * 執行条件<BR>
     * (0：その他　@1：条件なし　@2：寄り　@3：引け　@6：不出来引け成行）<BR>
     * @@return WEB3MarginCloseMarginOrderSpec
     * @@throws WEB3SystemLayerException
     * @@roseuid 40B2FDB30326
     */
    public static WEB3MarginSettleContractOrderSpec createCloseMarginOrderSpec(
        Trader l_trader,
        EqTypeSettleContractOrderEntry[] l_closeMarginContractEntrys,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_execCondType,
        Date l_datExpirationDate,
        TaxTypeEnum l_taxType,
        String l_strPriceConditionType,
        String l_strOrderConditionType,
        String l_strOrderCondOperator,
        double l_dblStopOrderPrice,
        double l_dblWLimitPrice,
        String l_strClosingOrderType,
        Long l_lngFirstOrderUnitId,
        EqTypeExecutionConditionType l_wlimitExecCondType)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "createCloseMarginOrderSpec(Trader, "
            + "EqTypeSettleContractOrderEntry[], double, EqTypeExecutionConditionType, "
            + "Date, TaxTypeEnum, String, String, String, double, double, String, Long, "
            + "EqTypeExecutionConditionType";
        log.entering(STR_METHOD_NAME);

        //信用新規建注文内容オブジェクトを生成
        //信用返済注文内容.create返済注文内容( )をdelegateする。
        WEB3MarginSettleContractOrderSpec l_orderSpec =
            WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                l_trader,
                l_closeMarginContractEntrys,
                l_dblLimitPrice,
                l_execCondType,
                l_datExpirationDate,
                l_taxType,
                l_strPriceConditionType,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_dblStopOrderPrice,
                l_dblWLimitPrice,
                l_strClosingOrderType,
                l_lngFirstOrderUnitId);

        //２）set（Ｗ指値）執行条件( )をcallする。
        //引数設定は以下のように行う。
        //（Ｗ指値）執行条件：　@同項目
        l_orderSpec.setWlimitExecCondType(l_wlimitExecCondType);

        log.exiting(STR_METHOD_NAME);

        return l_orderSpec;
    }

    /**
     * (set発注日)<BR>
     * 発注日をセットする。
     * @@param l_datBizDate - 発注日
     * @@roseuid 40B30A970182
     */
    public void setBizDate(Date l_datBizDate) 
    {
        this.bizDate = l_datBizDate;
    }
    
    /**
     * (get発注日)<BR>
     * 発注日を取得する。
     * @@return Date
     * @@roseuid 40B30A970190
     */
    public Date getBizDate() 
    {
        return this.bizDate;
    }
    
    /**
     * (set値段条件)<BR>
     * 値段条件をセットする。
     * @@param l_strPriceConditionType - 値段条件
     */
    public void setPriceConditionType(String l_strPriceConditionType) 
    {
        this.priceConditionType = l_strPriceConditionType;
    }
    
    /**
     * (get値段条件)<BR>
     * 値段条件を取得する。
     * @@return String
     */
    public String getPriceConditionType() 
    {
        return this.priceConditionType;
    }
    
    /**
     * (set発注条件)<BR>
     * 発注条件をセットする。
     * @@param l_strOrderConditionType - 発注条件
     * @@roseuid 40B30A9701A1
     */
    public void setOrderConditionType(String l_strOrderConditionType) 
    {
        this.orderConditionType = l_strOrderConditionType;
    }
    
    /**
     * (get発注条件)<BR>
     * 発注条件を取得する。
     * @@return String
     * @@roseuid 40B30A9701A0
     */
    public String getOrderConditionType() 
    {
        return this.orderConditionType;
    }
    
    /**
     * (set発注条件演算子)<BR>
     * 発注条件演算子をセットする。
     * @@param l_strOrderCondOperator - 発注条件演算子
     * @@roseuid 40B30A9701A3
     */
    public void setOrderCondOperator(String l_strOrderCondOperator) 
    {
        this.orderCondOperator = l_strOrderCondOperator;
    }
    
    /**
     * (get発注条件演算子)<BR>
     * 発注条件演算子を取得する。
     * @@return String
     * @@roseuid 40B30A9701AF
     */
    public String getOrderCondOperator() 
    {
        return this.orderCondOperator;
    }
    
    /**
     * (set逆指値基準値)<BR>
     * 逆指値基準値をセットする。
     * @@param l_dblStopOrderPrice - 逆指値基準値
     * @@roseuid 40B30A9701B0
     */
    public void setStopOrderPrice(double l_dblStopOrderPrice) 
    {
        this.stopOrderPrice = l_dblStopOrderPrice;
    }
    
    /**
     * (get逆指値基準値)<BR>
     * 逆指値基準値を取得する。
     * @@return double
     * @@roseuid 40B30A9701B2
     */
    public double getStopOrderPrice() 
    {
        return this.stopOrderPrice;
    }
    
    /**
     * (set（W指値）訂正指値)<BR>
     * (W指値)訂正指値をセットする。
     * @@param l_dblWLimitPrice - (W指値)訂正指値
     * @@roseuid 40B30A9701BF
     */
    public void setWLimitPrice(double l_dblWLimitPrice) 
    {
        this.wLimitPrice = l_dblWLimitPrice;
    }
    
    /**
     * (get（W指値）訂正指値)<BR>
     * (W指値)訂正指値を取得する。
     * @@return double
     * @@roseuid 40B30A9701C1
     */
    public double getWLimitPrice() 
    {
        return this.wLimitPrice;
    }
    
    /**
     * (set初回注文の注文単位ID)<BR>
     * 初回注文の注文単位IDをセットする。
     * @@param l_lngFirstOrderUnitId - 初回注文の注文単位ID。<BR>
     * （注文単位テーブル.初回注文の注文単位ID）
     * @@roseuid 40B30A97020D
     */
    public void setFirstOrderUnitId(Long l_lngFirstOrderUnitId) 
    {
        this.firstOrderUnitId = l_lngFirstOrderUnitId;
    }
    
    /**
     * (get初回注文の注文単位ID)<BR>
     * 初回注文の注文単位IDを取得する。<BR>
     * <BR>
     * （注文単位テーブル.初回注文の注文単位ID）<BR>
     * @@return Long
     * @@roseuid 40B30A97020F
     */
    public Long getFirstOrderUnitId() 
    {
        return this.firstOrderUnitId;
    }
    
    /**
     * (set決済順序区分)<BR>
     * 決済順序区分をセットする。
     * @@param l_strClosingOrderType - 決済順序区分。<BR>
     * (0：ランダム　@1：単価益順　@2：単価損順　@3：建日順)<BR>
     * 一括返済の場合セット。<BR>
     * @@roseuid 40B30A970210
     */
    public void setClosingOrderType(String l_strClosingOrderType) 
    {
        this.closingOrderType = l_strClosingOrderType;
    }
    
    /**
     * (get決済順序区分)<BR>
     * 決済順序区分を取得する。
     * @@return String
     * @@roseuid 40B30A97021D
     */
    public String getClosingOrderType() 
    {
        return this.closingOrderType;
    }
    
    /**
     * (is出来るまで注文)<BR>
     * 該当注文が出来るまで注文の場合true<BR>
     * 当日のみ注文の場合falseを返す。<BR>
     * <BR>
     * １）　@this.初回注文の注文単位ID != nullの場合は、trueを返す。<BR>
     * 　@　@　@this.初回注文の注文単位ID == nullの場合は、falseを返す。<BR>
     * @@return boolean
     * @@roseuid 40B30A97021E
     */
    public boolean isCarriedOrder() 
    {
        if(this.firstOrderUnitId != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * (is（W指値）訂正指値)<BR>
     * ・W指値注文の場合は「（W指値）訂正指値」が指値であるかどうかを返す。<BR>
     * 　@指値の場合はtrueを、成行の場合はfalseを返す。<BR>
     * ・W指値注文でない場合は、例外をthrowする。<BR>
     * <BR>
     * １）　@this.get発注条件( ) != ”W指値”の場合は、例外をthrowする。<BR>
     * <BR>
     * ２）　@this.get（W指値）訂正指値( ) == 0 の場合は、falseを返す。以外、trueを返す。<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40B30A97022C
     */
    public boolean isWLimitPrice()
        throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME = "isWLimitPrice()";
        log.entering(STR_METHOD_NAME);
         
        //１）　@this.get発注条件( ) != ”W指値”の場合は、falseを返す。 
        if( !(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.getOrderConditionType())))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00726, STR_METHOD_NAME);
        }
        
        boolean l_result = true;
        //２）　@this.get（W指値）訂正指値( ) == 0 の場合は、falseを返す。以外、trueを返す
        if(String.valueOf(this.getWLimitPrice()) != null)
        {
            if(this.getWLimitPrice() == 0)
            {
                l_result = false;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
        
    }

    /**
     * (set（Ｗ指値）執行条件)<BR>
     * （Ｗ指値）執行条件をセットする。<BR>
     * @@param l_wlimitExecCondType - (（Ｗ指値）執行条件)<BR>
     * （Ｗ指値）執行条件<BR>
     * @@roseuid 40B30A97020D
     */
    public void setWlimitExecCondType(EqTypeExecutionConditionType l_wlimitExecCondType)
    {
        this.wlimitExecCondType = l_wlimitExecCondType;
    }

    /**
     * (get（Ｗ指値）執行条件)<BR>
     * （Ｗ指値）執行条件を取得する。<BR>
     * @@return EqTypeExecutionConditionType
     * @@roseuid 40B30A97021E
     */
    public EqTypeExecutionConditionType getWlimitExecCondType()
    {
        return this.wlimitExecCondType;
    }

}
@
