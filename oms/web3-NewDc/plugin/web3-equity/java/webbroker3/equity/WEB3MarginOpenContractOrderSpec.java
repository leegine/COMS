head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用新規建注文内容(WEB3MarginOpenMarginOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 凌建平 (中訊) 新規作成
                   2004/12/09 中尾寿彦(SRA) 残案件対応による修正
                   2004/12/29 岡村和明(SRA) パラメータの変数名を修正
                   2005/01/06 岡村和明 (SRA) JavaDoc修正
                   2006/11/02 趙林鵬 (中訊) モデル No.1001
*/

package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用新規建注文内容）。<BR>
 * <BR>
 * 信用取引・新規建注文内容の入力を表現する。<BR>
 * 注文マネージャに渡すパラメタになる。<BR>
 * <BR>
 * xTradeのEqTypeOpenContractOrderSpecを拡張したクラス。
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginOpenContractOrderSpec extends EqTypeOpenContractOrderSpec 
{
    /**
     * （ログユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityNewCashBasedOrderSpec.class);

    /**
     * （信用新規建注文内容）。<BR>
     * コンストラクタ。
     * @@param l_trader (扱者)
     * @@param l_blnIsLong (is買建)
     * @@param l_strProductCpde 銘柄コード。
     * @@param l_strMarketCode 市場コード
     * @@param l_dblQuantity 数量（枚数）
     * @@param l_dblLimitPrice 指値
     * @@param l_executionType 執行条件
     * @@param l_datExpirationDate 注文失効日
     * @@param l_taxType (税区分)
     */
    protected WEB3MarginOpenContractOrderSpec(
        Trader l_trader, 
        boolean l_blnIsLong, 
        String l_strProductCpde, 
        String l_strMarketCode, 
        double l_dblQuantity, 
        double l_dblLimitPrice, 
        EqTypeExecutionConditionType l_executionType, 
        Date l_datExpirationDate, 
        TaxTypeEnum l_taxType)
    {
        super(l_trader,
            l_blnIsLong,
            l_strProductCpde,
            l_strMarketCode,
            l_dblQuantity,
            l_dblLimitPrice,
            l_executionType,
            l_datExpirationDate,
            l_taxType);
    }

    /**
     * （発注日）。<BR>
     * <BR>
     * 市場に発注する処理日付。<BR>
     * （取引カレンダより取得する）<BR>
     */
    private Date bizDate;
    
    /**
     * （計算単価）。<BR>
     * <BR>
     * （注文単位.注文単価にセット）
     */
    private double calcUnitPrice;
    
    /**
     * （値段条件)。<BR>
     * <BR>
     * （0：　@DEFAULT(条件指定なし)<BR>
     * 　@1：　@現在値指値注文<BR>
     * 　@3：　@優先指値注文<BR>
     * 　@5：　@成行残数指値注文<BR>
     * 　@7：　@成行残数取消注文）<BR>
     */
    private String priceConditionType;
    
    /**
     * （発注条件）。<BR>
     * <BR>
     * （0：DEFAULT（条件指定なし）　@1：逆指値　@2：W指値）
     */
    private String orderConditionType;
    
    /**
     * （発注条件演算子）。<BR>
     * <BR>
     * （0：Equal（基準値と同じ値になったら）<BR>
     * 1：基準値以上　@<BR>
     * 2：基準値以下）<BR>
     */
    private String orderCondOperator;
    
    /**
     * （逆指値基準値）。<BR>
     * <BR>
     * （逆指値、W指値の場合のみセット）
     */
    private double stopOrderPrice;
    
    /**
     * （（W指値）訂正指値。）。<BR>
     * <BR>
     * （W指値の場合のみセット）
     */
    private double wLimitPrice;
    
    /**
     * （弁済区分）。<BR>
     * <BR>
     * （0：DEFAULT　@1：制度信用　@2：一般信用）
     */
    private String repaymentType;
    
    /**
     * （弁済期限値）。<BR>
     * <BR>
     * （月単位で設定。無期限の場合は、ALL9を設定）
     */
    private double repaymentNum;
    
    /**
     * （初回注文の注文単位ID）。
     */
    private Long firstOrderUnitId;
    
    /**
     * （建代金）。
     */
    private double contractAmount;

    /**
     * (（Ｗ指値）執行条件)<BR>
     * （Ｗ指値）執行条件<BR>
     */
    private EqTypeExecutionConditionType wlimitExecCondType;

    /**
     * （create新規建注文内容）。<BR>
     * <BR>
     * （staticメソッド）<BR>
     * 信用新規建注文内容オブジェクトを生成し返却する。<BR>
     * <BR>
     * 手続きはシーケンス図「（信用注文）create新規建注文内容」を参照。<BR>
     * @@param l_trader - (扱者)<BR>
     * 扱者オブジェクト<BR>
     * @@param l_blnIsLong - (is買建)<BR>
     * （isBuyToOpenOrder）<BR>
     * 買建かどうかの判定。<BR>
     * 買建の場合はtrue、売建の場合はfalse。
     * @@param l_strProductCpde 銘柄コード。
     * @@param l_strMarketCode 市場コード
     * @@param l_dblQuantity 数量（枚数）
     * @@param l_dblLimitPrice 指値
     * @@param l_executionType 執行条件
     * @@param l_datExpirationDate 注文失効日
     * @@param l_taxType (税区分)<BR>
     * 　@　@　@0:その他,　@1:一般,　@2:特定,　@3:特定口座かつ源泉徴収<BR>
     * 　@　@　@（TaxTypeEnumにて定義）
     * @@param l_strPriceConditionType (値段条件)
     * @@param l_strOrderConditionType 発注条件
     * @@param l_strOrderCondOperator 発注条件演算子
     * @@param l_dblStopOrderPrice 逆指値基準値
     * @@param l_dblWLimitPrice (W指値)訂正指値
     * @@param l_strRepaymentType (弁済区分)<BR>
     * 　@　@　@0：DEFAULT<BR>
     * 　@　@　@1：制度信用<BR>
     * 　@　@　@2：一般信用
     * @@param l_dblRepaymentNum 弁済期限値
     * @@param l_lngFirstOrderUnitId (初回注文の注文単位ID)<BR>
     * 　@　@　@注文繰越での注文作成時には、繰越対象の注文単位.初回注文の<BR>
     * 　@　@　@注文単位ID をセット。<BR>
     * 　@　@　@上記以外の新規注文登録時には、nullをセット。
     * @@return WEB3MarginOpenMarginOrderSpec
     * @@throws WEB3SystemLayerException
     * @@roseuid 40A8B9580038
     */
    public static WEB3MarginOpenContractOrderSpec createOpenMarginOrderSpec(
        Trader l_trader,
        boolean l_blnIsLong, 
        String l_strProductCpde, 
        String l_strMarketCode, 
        double l_dblQuantity, 
        double l_dblLimitPrice, 
        EqTypeExecutionConditionType l_executionType,
        Date l_datExpirationDate,
        TaxTypeEnum l_taxType,
        String l_strPriceConditionType,
        String l_strOrderConditionType, 
        String l_strOrderCondOperator, 
        double l_dblStopOrderPrice, 
        double l_dblWLimitPrice, 
        String l_strRepaymentType, 
        double l_dblRepaymentNum, 
        Long l_lngFirstOrderUnitId) 
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME="createOpenMarginOrderSpec()";
        log.entering(STR_METHOD_NAME);
        
        //1  注文失効日がnullの場合、発注日を指定する
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        if(l_datExpirationDate == null)
        {
            l_datExpirationDate = l_datOrderBizDate;
        }
        
        //2 新規建注文内容インスタンスを生成する。
        WEB3MarginOpenContractOrderSpec l_orderSpec = 
            new WEB3MarginOpenContractOrderSpec(
                l_trader,
                l_blnIsLong,
                l_strProductCpde,
                l_strMarketCode,
                l_dblQuantity,
                l_dblLimitPrice,
                l_executionType,
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
        l_orderSpec.setRepaymentType(l_strRepaymentType);
        
        //10 弁済期限値をセット
        l_orderSpec.setRepaymentNum(l_dblRepaymentNum);
        
        //11 初回注文の注文単位IDをセット
        l_orderSpec.setFirstOrderUnitId(l_lngFirstOrderUnitId);
        
        log.exiting(STR_METHOD_NAME);
        
        return  l_orderSpec;        
    }

    /**
     * （create新規建注文内容）。<BR>
     * <BR>
     * （staticメソッド）<BR>
     * 信用新規建注文内容オブジェクトを生成し返却する。<BR>
     * <BR>
     * 手続きはシーケンス図「（信用注文）create新規建注文内容」を参照。<BR>
     * <BR>
     * １）信用新規建注文内容.create新規建注文内容( ) をdelegateする。<BR>
     * <BR>
     * 　@　@引数設定は以下のように行う。<BR>
     * <BR>
     * 扱者：　@パラメータ.同項目 <BR>
     * is買建：　@パラメータ.同項目 <BR>
     * 銘柄コード：　@パラメータ.同項目 <BR>
     * 市場コード：　@パラメータ.同項目 <BR>
     * 数量：　@パラメータ.同項目 <BR>
     * 指値：　@パラメータ.同項目 <BR>
     * 執行条件：　@パラメータ.同項目 <BR>
     * 注文失効日：　@パラメータ.同項目 <BR>
     * 税区分：　@パラメータ.同項目 <BR>
     * 値段条件：　@パラメータ.同項目 <BR>
     * 発注条件：　@パラメータ.同項目 <BR>
     * 発注条件演算子：　@パラメータ.同項目 <BR>
     * 逆指値基準値：　@パラメータ.同項目 <BR>
     * （W指値）訂正指値：　@パラメータ.同項目 <BR>
     * 弁済区分：　@パラメータ.同項目 <BR>
     * 弁済期限値：　@パラメータ.同項目 <BR>
     * 初回注文の注文単位ID：　@パラメータ.同項目 <BR>
     * <BR>
     * <BR>
     * ２）set（Ｗ指値）執行条件( )をcallする。<BR>
     * <BR>
     * 　@　@引数設定は以下のように行う。<BR>
     * <BR>
     * 　@　@　@（Ｗ指値）執行条件：　@同項目<BR>
     * @@param l_trader - (扱者)<BR>
     * 扱者オブジェクト<BR>
     * @@param l_blnIsLong - (is買建)<BR>
     * （isBuyToOpenOrder）<BR>
     * 買建かどうかの判定。<BR>
     * 買建の場合はtrue、売建の場合はfalse。<BR>
     * @@param l_strProductCpde - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@param l_dblQuantity - 数量<BR>
     * 数量（枚数）<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値<BR>
     * @@param l_execCondType - (執行条件)<BR>
     * 執行条件<BR>
     * @@param l_datExpirationDate - (注文失効日)<BR>
     * 注文失効日<BR>
     * @@param l_taxType - (税区分)<BR>
     * 0:その他,　@1:一般,　@2:特定,　@3:特定口座かつ源泉徴収<BR>
     * （TaxTypeEnumにて定義）<BR>
     * @@param l_strPriceConditionType - (値段条件)<BR>
     * 値段条件<BR>
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_strOrderCondOperator - (発注条件演算子)<BR>
     * 発注条件演算子<BR>
     * @@param l_dblStopOrderPrice - (逆指値基準値)<BR>
     * 逆指値基準値<BR>
     * @@param l_dblWLimitPrice - ((W指値)訂正指値)<BR>
     * (W指値)訂正指値<BR>
     * @@param l_strRepaymentType - (弁済区分)<BR>
     * 0：DEFAULT<BR>
     * 1：制度信用<BR>
     * 2：一般信用<BR>
     * @@param l_dblRepaymentNum - (弁済期限値)<BR>
     * 弁済期限値<BR>
     * @@param l_lngFirstOrderUnitId - (初回注文の注文単位ID)<BR>
     * 注文繰越での注文作成時には、繰越対象の注文単位.初回注文の<BR>
     * 注文単位ID をセット。<BR>
     * 上記以外の新規注文登録時には、nullをセット。<BR>
     * @@param l_wlimitExecCondType - (（Ｗ指値）執行条件)<BR>
     * （Ｗ指値）執行条件<BR>
     * @@return WEB3MarginOpenMarginOrderSpec
     * @@throws WEB3SystemLayerException
     * @@roseuid 40A8B9580038
     */
    public static WEB3MarginOpenContractOrderSpec createOpenMarginOrderSpec(
        Trader l_trader,
        boolean l_blnIsLong,
        String l_strProductCpde,
        String l_strMarketCode,
        double l_dblQuantity,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_execCondType,
        Date l_datExpirationDate,
        TaxTypeEnum l_taxType,
        String l_strPriceConditionType,
        String l_strOrderConditionType,
        String l_strOrderCondOperator,
        double l_dblStopOrderPrice,
        double l_dblWLimitPrice,
        String l_strRepaymentType,
        double l_dblRepaymentNum,
        Long l_lngFirstOrderUnitId,
        EqTypeExecutionConditionType l_wlimitExecCondType)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "createOpenMarginOrderSpec(Trader, boolean, "
            + "String, String, double, double, EqTypeExecutionConditionType, Date, "
            + "TaxTypeEnum, String, String, String, double, double, String, double, Long, "
            + "EqTypeExecutionConditionType";
        log.entering(STR_METHOD_NAME);

        //信用新規建注文内容オブジェクトを生成
        //１）信用新規建注文内容.create新規建注文内容( ) をdelegateする。
        WEB3MarginOpenContractOrderSpec l_orderSpec =
            WEB3MarginOpenContractOrderSpec.createOpenMarginOrderSpec(
                l_trader,
                l_blnIsLong,
                l_strProductCpde,
                l_strMarketCode,
                l_dblQuantity,
                l_dblLimitPrice,
                l_execCondType,
                l_datExpirationDate,
                l_taxType,
                l_strPriceConditionType,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_dblStopOrderPrice,
                l_dblWLimitPrice,
                l_strRepaymentType,
                l_dblRepaymentNum,
                l_lngFirstOrderUnitId);

        //２）set（Ｗ指値）執行条件( )をcallする。
        //引数設定は以下のように行う。
        //（Ｗ指値）執行条件：　@同項目
        l_orderSpec.setWlimitExecCondType(l_wlimitExecCondType);

        log.exiting(STR_METHOD_NAME);

        return l_orderSpec;
    }

    /**
     * （set発注日）。<BR>
     * <BR>
     * 発注日をセットする。
     * @@param l_datBizDate 発注日
     * @@roseuid 40A8B9580058
     */
    public void setBizDate(Date l_datBizDate) 
    {
        this.bizDate = l_datBizDate;
    }
    
    /**
     * (get発注日）。<BR>
     * <BR>
     * 発注日を取得する。
     * @@return Date
     * @@roseuid 40A8B958004A
     */
    public Date getBizDate() 
    {
        return this.bizDate;
    }
    
    /**
     * （set計算単価）。<BR>
     * <BR>
     * 計算単価をセットする。
     * @@param l_dblCalcUnitPrice 計算単価。
     * @@roseuid 40AC517502DA
     */
    public void setCalcUnitPrice(double l_dblCalcUnitPrice) 
    {
        this.calcUnitPrice = l_dblCalcUnitPrice;
    }
    
    /**
     * （get計算単価）。<BR>
     * <BR>
     * 計算単価を取得する。
     * @@return double
     * @@roseuid 40AC517502E9
     */
    public double getCalcUnitPrice() 
    {
        return this.calcUnitPrice;
    }
    
    /**
     * （set値段条件）。<BR>
     * <BR>
     * 値段条件をセットする。
     * @@param l_strPriceConditionType 値段条件
     */
    public void setPriceConditionType(String l_strPriceConditionType) 
    {
        this.priceConditionType = l_strPriceConditionType;
    }
    
    /**
     * （get値段条件）。<BR>
     * <BR>
     * 値段条件を取得する。
     * @@return String
     */
    public String getPriceConditionType() 
    {
        return this.priceConditionType;
    }
    
    /**
     * （get発注条件）。<BR>
     * <BR>
     * 発注条件を取得する。
     * @@return String
     * @@roseuid 40A8B958005A
     */
    public String getOrderConditionType() 
    {
        return this.orderConditionType;
    }
    
    /**
     * （set発注条件）。<BR>
     * <BR>
     * 発注条件をセットする。
     * @@param l_strOrderConditionType 発注条件
     * @@roseuid 40A8B9580067
     */
    public void setOrderConditionType(String l_strOrderConditionType) 
    {
        this.orderConditionType = l_strOrderConditionType;
    }
    
    /**
     * （set発注条件演算子）。<BR>
     * <BR>
     * 発注条件演算子をセットする。
     * @@param l_strOrderCondOperator 発注条件演算子
     * @@roseuid 40AC522E021E
     */
    public void setOrderCondOperator(String l_strOrderCondOperator) 
    {
        this.orderCondOperator = l_strOrderCondOperator;
    }
    
    /**
     * （get発注条件演算子）。<BR>
     * <BR>
     * 発注条件演算子を取得する。
     * @@return String
     * @@roseuid 40AC522501EF
     */
    public String getOrderCondOperator() 
    {
        return this.orderCondOperator;
    }
    
    /**
     * （set逆指値基準値）。<BR>
     * <BR>
     * 逆指値基準値をセットする。
     * @@param l_dblStopOrderPrice 逆指値基準値
     * @@roseuid 40AC524301B1
     */
    public void setStopOrderPrice(double l_dblStopOrderPrice) 
    {
        this.stopOrderPrice = l_dblStopOrderPrice;
    }
    
    /**
     * （get逆指値基準値）。<BR>
     * <BR>
     * 逆指値基準値を取得する。
     * @@return double
     * @@roseuid 40AC524C0124
     */
    public double getStopOrderPrice() 
    {
        return this.stopOrderPrice;
    }
    
    /**
     * （set（W指値）訂正指値）。<BR>
     * <BR>
     * (W指値)訂正指値をセットする。
     * @@param l_dblWLimitPrice (W指値)訂正指値
     * @@roseuid 40A8B958006A
     */
    public void setWLimitPrice(double l_dblWLimitPrice) 
    {
        this.wLimitPrice = l_dblWLimitPrice;
    }
    
    /**
     * （get（W指値）訂正指値）。<BR>
     * <BR>
     * (W指値)訂正指値を取得する。
     * @@return double
     * @@roseuid 40A8B9580069
     */
    public double getWLimitPrice() 
    {
        return this.wLimitPrice;
    }
    
    /**
     * （set弁済区分）。<BR>
     * <BR>
     * 弁済区分をセットする。
     * @@param l_strRepaymentType 弁済区分。
     * @@roseuid 40AACDDC027F
     */
    public void setRepaymentType(String l_strRepaymentType) 
    {
        this.repaymentType = l_strRepaymentType;
    }
    
    /**
     * （get弁済区分）。<BR>
     * <BR>
     * 弁済区分を取得する。
     * @@return String
     * @@roseuid 40AACDDC026F
     */
    public String getRepaymentType() 
    {
        return this.repaymentType;
    }
    
    /**
     * （set弁済期限値）。<BR>
     * <BR>
     * 弁済期限値をセットする。
     * @@param l_dblRepaymentNum 弁済期限値。
     * @@roseuid 40ADAEA301E2
     */
    public void setRepaymentNum(double l_dblRepaymentNum) 
    {
        this.repaymentNum = l_dblRepaymentNum;
    }
    
    /**
     * （get弁済期限値）。<BR>
     * <BR>
     * 弁済期限値を取得する。
     * @@return double
     * @@roseuid 40ADAEA301F1
     */
    public double getRepaymentNum() 
    {
        return this.repaymentNum;
    }
    
    /**
     * （set初回注文の注文単位ID）。<BR>
     * <BR>
     * 初回注文の注文単位IDをセットする。
     * @@param l_lngFirstOrderUnitId 初回注文の注文単位ID。<BR>
     * 　@　@　@（注文単位テーブル.初回注文の注文単位ID）
     * @@roseuid 40AB1B4600C9
     */
    public void setFirstOrderUnitId(Long l_lngFirstOrderUnitId) 
    {
        this.firstOrderUnitId = l_lngFirstOrderUnitId;
    }
    
    /**
     * （get初回注文の注文単位ID）。<BR>
     * <BR>
     * 初回注文の注文単位IDを取得する。<BR>
     * <BR>
     * （注文単位テーブル.初回注文の注文単位ID）
     * @@return Long
     * @@roseuid 40AB1B4600D9
     */
    public Long getFirstOrderUnitId() 
    {
        return this.firstOrderUnitId;
    }
    
    /**
     * （set建代金）。<BR>
     * <BR>
     * 建代金をセットする。
     * @@param l_dblPrice 建代金。
     * @@roseuid 40B1AFFF0128
     */
    public void setContractAmount(double l_dblPrice) 
    {
        this.contractAmount = l_dblPrice;
    }
    
    /**
     * （get建代金）。<BR>
     * <BR>
     * 建代金を取得する。
     * @@return double
     * @@roseuid 40B1AFFF0137
     */
    public double getContractAmount() 
    {
        return this.contractAmount;
    }
    
    /**
     * （is出来るまで注文）。<BR>
     * <BR>
     * 該当注文が出来るまで注文の場合true<BR>
     * 当日のみ注文の場合falseを返す。<BR>
     * <BR>
     * １）　@this.初回注文の注文単位ID != nullの場合は、trueを返す。<BR>
     * 　@　@　@this.初回注文の注文単位ID == nullの場合は、falseを返す。
     * @@return boolean
     * @@roseuid 40AB1B710211
     */
    public boolean isCarriedOrder() 
    {
        if (this.firstOrderUnitId != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * （is（W指値）訂正指値）。<BR>
     * <BR>
     * ・W指値注文の場合は「（W指値）訂正指値」が指値であるかどうかを返す。<BR>
     * 　@指値の場合はtrueを、成行の場合はfalseを返す。<BR>
     * ・W指値注文でない場合は、例外をthrowする。<BR>
     * <BR>
     * １）　@this.get発注条件( ) != ”W指値”の場合は、例外をthrowする。<BR>
     * <BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00726<BR>
     * <BR>
     * ２）　@this.get（W指値）訂正指値( ) == 0 の場合は、falseを返す。以外、trueを返す。<BR>
     * @@return boolean
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40AB1B710221
     */
    public boolean isWLimitPrice()
        throws WEB3BusinessLayerException  
    {
        final String STR_METHOD_NAME = "isWLimitPrice()";
        log.entering(STR_METHOD_NAME);
         
        //１）　@this.get発注条件( ) != ”W指値”の場合は、falseを返す。 
        if (!(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(getOrderConditionType())))
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
