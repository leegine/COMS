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
filename	WEB3EquityNewCashBasedOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文内容(WEB3EquityNewCashBasedOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 李綱 (中訊) 新規作成
                 : 2004/12/28 岡村 (SRA) パラメータの変数名を修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2006/11/02 趙林鵬 (中訊) モデル No.988
Revesion History : 2007/12/17 趙林鵬 (中訊) モデル No.1216,1251
Revesion History : 2008/01/08 趙林鵬 (中訊) モデル No.1280
*/
package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

/**
 * （株式注文内容）。<BR>
 * <BR>
 * 注文内容の入力を表現する。 <BR>
 * 注文マネージャに渡すパラメタになる。<BR>
 * <BR>
 * xTradeのEqTypeNewCashBasedOrderSpecを拡張したクラス。
 * @@version 1.0
 */
public class WEB3EquityNewCashBasedOrderSpec
    extends EqTypeNewCashBasedOrderSpec
{

    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityNewCashBasedOrderSpec.class);

    /**
     * (証券会社コード)
     */
    private String institutionCode;

    /**
     * (注文チャネル)
     */
    private String orderChanel;

    /**
     * (発注日)。 <BR>
     * 市場に発注する処理日付。 <BR>
     * （取引カレンダより取得する） <BR>
     */
    private Timestamp orderBizDate;

    /**
     * (手数料商品コード) <BR>
     * <BR>
     * （会社部店商品テーブル.手数料商品コード） <BR>
     */
    private String commissionProductCode = null;

    /**
     * (注文単価。)
     * （注文単位.注文単価にセット）
     */
    private double orderUnitPrice = 0.0;

    /**
     * (概算受渡代金) <BR>
     * （発注審査にて計算する） <BR>
     */
    private double estimate_delivery_amount = 0.0;

    /**
     * (手数料) <BR>
     * 手数料オブジェクト <BR>
     * （create手数料( )にて生成する） <BR>
     */
    private WEB3GentradeCommission equityCommission;

    /**
     * (初回注文の注文単位ID) <BR>
     * 初回注文の注文単位ID。 <BR>
     */
    private Long firstOrderUnitId;

    /**
     * (値段条件)<BR>
     * （0：　@DEFAULT(条件指定なし)<BR>
     * 　@1：　@現在値指値注文<BR>
     * 　@3：　@優先指値注文<BR>
     * 　@5：　@成行残数指値注文<BR>
     * 　@7：　@成行残数取消注文）<BR>
     */
    private String priceConditionType;

    /**
     * (発注条件)。 <BR>
     * （0：DEFAULT（条件指定なし）　@1：逆指値　@2：W指値） <BR>
     */
    private String orderCond;

    /**
     * (発注条件演算子)。 <BR>
     * （0：Equal（基準値と同じ値になったら）　@ <BR>
     * 1：基準値以上 <BR>
     * 2：基準値以下） <BR>
     */
    private String orderCondOperator;

    /**
     * (逆指値基準値)。 <BR>
     * （逆指値、W指値の場合のみセット） <BR>
     */
    private double stopLimitPriceBasePrice;

    /**
     * (（W指値）訂正指値。) <BR>
     * （W指値の場合のみセット） <BR>
     */
    private double wLimitPriceChange;

    /**
     * (（Ｗ指値）執行条件)<BR>
     * （Ｗ指値）執行条件<BR>
     */
    private EqTypeExecutionConditionType wlimitExecCondType;

    /**
     * (コンストラクタ。)<BR>
     *<BR> 
     * @@param l_trader
     * @@param l_isBuyOrder
     * @@param l_strProductCode
     * @@param l_strMarketCode
     * @@param l_dbQuantity
     * @@param l_dbPrice
     * @@param l_execType
     * @@param l_datOrderExpDate
     * @@param l_taxType
     * @@roseuid 40236D290127
     */
    private WEB3EquityNewCashBasedOrderSpec(
        Trader l_trader,
        boolean l_isBuyOrder,
        String l_strProductCode,
        String l_strMarketCode,
        double l_dblQuantity,
        double l_dblPrice,
        EqTypeExecutionConditionType l_execType,
        Date l_datOrderExpDate,
        TaxTypeEnum l_taxType)
    {
        super(
            l_trader,
            l_isBuyOrder,
            l_strProductCode,
            l_strMarketCode,
            l_dblQuantity,
            l_dblPrice,
            l_execType,
            l_datOrderExpDate,
            l_taxType);
    }

    /**
     * (get証券会社コード) <BR>
     * 証券会社コードを取得する。 <BR>
     * @@return String
     * @@roseuid 3FFB7A53032B
     */
    public String getInstitutionCode()
    {
        return this.institutionCode;
    }

    /**
     * (set証券会社コード) <BR>
     * 証券会社コードを設定する。 <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@roseuid 3FFB7A59037A
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        this.institutionCode = l_strInstitutionCode;
    }

    /**
     * (set発注日) <BR>
     * 発注日をセットする。 <BR>
     * <BR>
     * @@param l_tsOrderBizDate - 発注日
     * @@roseuid 400665430102
     */
    public void setOrderBizDate(Timestamp l_tsOrderBizDate)
    {
        this.orderBizDate = l_tsOrderBizDate;
    }

    /**
     * (get発注日) <BR>
     * 発注日を取得する。 <BR>
     * @@return Timestamp
     * @@roseuid 4018CE4C0010
     */
    public Timestamp getOrderBizDate()
    {
        return this.orderBizDate;
    }

    /**
     * (set概算受渡代金) <BR>
     * 概算受渡代金をセットする。 <BR>
     * @@param l_dblEstimatedPrice - 概算金額 <BR>
     * @@roseuid 400E729203DE
     */
    public void setEstimateDeliveryAmount(double l_dblEstimatedPrice)
    {
        this.estimate_delivery_amount = l_dblEstimatedPrice;
    }

    /**
     * (get概算受渡代金) <BR>
     * 概算受渡代金を取得する。 <BR>
     * @@return double
     * @@roseuid 400E729901DA
     */
    public double getEstimateDeliveryAmount()
    {
        return estimate_delivery_amount;
    }

    /**
     * (set注文単価) <BR>
     * 注文単価をセットする。 <BR>
     * @@param l_dblOrderUnitPrice - 注文単価
     * @@roseuid 400E72C902B5
     */
    public void setOrderUnitPrice(double l_dblOrderUnitPrice)
    {
        this.orderUnitPrice = l_dblOrderUnitPrice;
    }

    /**
     * (get注文単価) <BR>
     * 注文単価を取得する。 <BR>
     * @@return double
     * @@roseuid 400E72CF00D0
     */
    public double getOrderUnitPrice()
    {
        return orderUnitPrice;
    }

    /**
     * (set注文チャネル) <BR>
     * 注文チャネルをセットする。 <BR>
     * <BR>
     * @@param l_strOrderChannel - (注文チャネル) <BR>
     * 注文チャネルをセットする。 <BR>
     * @@roseuid 400FBB770167
     */
    public void setOrderChannel(String l_strOrderChannel)
    {
        this.orderChanel = l_strOrderChannel;
    }

    /**
     * (get注文チャネル) <BR>
     * 注文チャネルを取得する。 <BR>
     * <BR>
     * @@return String
     * @@roseuid 400FBB9702CE
     */
    public String getOrderChannel()
    {
        return orderChanel;
    }

    /**
     * (create注文内容) <BR>
     * 注文内容を生成する。<BR>
     * （createOrderSpec）<BR>
     * <BR>
     * 注文内容インスタンスを生成し、プロパティに値をセットする。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * <BR>
     * ２）　@拡張項目セット<BR>
     * 　@－取引時間管理.get発注日( )で取得した日付を発注日にセットする。<BR>
     * 　@－証券会社コードをインスタンスにセットする。<BR>
     * 　@－ログインチャネルをインスタンスにセットする。<BR>
     * 　@－値段条件をインスタンスにセットする。<BR>
     * 　@－発注条件をインスタンスにセットする。<BR>
     * 　@－発注条件演算子をインスタンスにセットする。<BR>
     * 　@－逆指値基準値をインスタンスにセットする。<BR>
     * 　@－（W指値）訂正指値をインスタンスにセットする。<BR>
     * 　@－初回注文の注文単位IDをインスタンスにセットする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@param l_trader - (扱者) <BR>
     * 代理入力実施トレーダ。 <BR>
     * 代理入力でない場合、nullを指定。 <BR>
     * @@param l_strMarketCode - (市場コード) <BR>
     * 入力市場コード <BR>
     * @@param l_strProductCode - (銘柄コード) <BR>
     * 入力銘柄コード <BR>
     * @@param l_dblOrderQuantity - (株数) <BR>
     * 入力株数 <BR>
     * @@param l_dblLimitPrice - (指値) <BR>
     * 指値の場合のみ指定。 <BR>
     * 指値以外の場合はnullを指定。 <BR>
     * @@param l_eqTypeExecutionconditionType - (執行条件) <BR>
     * 1:条件なし,　@2:寄り,　@3:引け,　@6:不出来引け成行 <BR>
     * （EqTypeExecutionConditionTypeにて定義） <BR>
     * @@param l_taxTypeEnum - (税区分) <BR>
     * 0:その他,　@1:一般,　@2:特定,　@3:特定口座かつ源泉徴収 <BR>
     * （TaxTypeEnumにて定義） <BR>
     * @@param l_tsOrderLaspeDate - (注文失効日)<BR>
     * 注文失効日。<BR>
     * @@param l_isSellOrder - (is売注文) <BR>
     * 売注文の場合はtrue、 <BR>
     * 買注文の場合はfalseを指定する。 <BR>
     * @@param l_strOrderChannel - (注文チャネル)<BR>
     * @@param l_strPriceConditionType - (値段条件)<BR>
     * @@param l_strOrderCond - (発注条件)<BR>
     * @@param l_strOrderCondOperator - (発注条件演算子)<BR>
     * @@param l_dblStopOrderBasePrice - (逆指値基準値)<BR>
     * @@param l_dblWLimitOrderChange - (（W指値）訂正指値)<BR>
     * @@param l_lngFirstOrderUnitId - (初回注文の注文単位ID) <BR>
     * 注文繰越での注文作成時には、繰越対象の注文単位.初回注文の注文単位ID をセット。<BR>
     * 上記以外の新規注文登録時には、nullをセット。<BR>
     * @@return WEB3EquityNewCashBasedOrderSpec
     * @@roseuid 4143DDE10363
     */
    public static WEB3EquityNewCashBasedOrderSpec createOrderSpec(
        String l_strInstitutionCode,
        WEB3GentradeTrader l_trader,
        String l_strMarketCode,
        String l_strProductCode,
        double l_dblOrderQuantity,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_eqTypeExecutionconditionType,
        TaxTypeEnum l_taxTypeEnum,
        Timestamp l_tsOrderLaspeDate,
        boolean l_isSellOrder,
        String l_strOrderChannel,
        String l_strPriceConditionType,
        String l_strOrderCond,
        String l_strOrderCondOperator,
        double l_stopOrderBasePrice,
        double l_wLimitOrderChange,
        Long l_firstOrderUnitId)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
             "createOrderSpec(" 
             + "String, WEB3GentradeTrader, String, "
             + "String, double, double, EqTypeExecutionConditionType, "
             + "TaxTypeEnum, Timestamp, boolean, String, String," 
             + "String, double, double, long)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.指値≠null（指値注文）の場合、
        //パラメータ.指値を指定し、株式注文内容インスタンスを生成する。
        //以外の場合、指値として0を指定し、
        //株式注文内容インスタンスを生成する。
        WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec =
            new WEB3EquityNewCashBasedOrderSpec(
                (Trader) l_trader,
                !l_isSellOrder,
                l_strProductCode,
                l_strMarketCode,
                l_dblOrderQuantity,
                l_dblLimitPrice,
                l_eqTypeExecutionconditionType,
                (Date)l_tsOrderLaspeDate,
                l_taxTypeEnum);

        // ２）　@拡張項目セット
        //取引時間管理.get発注日( )で取得した日付を発注日にセットする
        Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        Timestamp l_tsBizDate = new Timestamp(l_bizDate.getTime());
        l_equityNewCashBasedOrderSpec.setOrderBizDate(l_tsBizDate); 
        //証券会社コードをインスタンスにセットする。   
        l_equityNewCashBasedOrderSpec.setInstitutionCode(
            l_strInstitutionCode);
        //ログインチャネルをインスタンスにセットする
        l_equityNewCashBasedOrderSpec.setOrderChannel(
            l_strOrderChannel);
        //値段条件をインスタンスにセットする
        l_equityNewCashBasedOrderSpec.setPriceConditionType(
            l_strPriceConditionType);
        //発注条件をインスタンスにセットする
        l_equityNewCashBasedOrderSpec.setOrderCond(
            l_strOrderCond);
        //発注条件演算子をインスタンスにセットする
        l_equityNewCashBasedOrderSpec.setOrderCondOperator(
            l_strOrderCondOperator);
        //逆指値基準値をインスタンスにセットする
        l_equityNewCashBasedOrderSpec.setStopOrderBasePrice(
            l_stopOrderBasePrice);
        //（W指値）訂正指値をインスタンスにセットする
        l_equityNewCashBasedOrderSpec.setWLimitPriceChange(
            l_wLimitOrderChange);
        //初回注文の注文単位IDをインスタンスにセットする
        l_equityNewCashBasedOrderSpec.setFirstOrderUnitId(
            l_firstOrderUnitId);

        log.exiting(STR_METHOD_NAME);

        return l_equityNewCashBasedOrderSpec;
    }

    /**
     * (create注文内容) <BR>
     * 注文内容を生成する。<BR>
     * （createOrderSpec）<BR>
     * <BR>
     * １）　@株式注文内容.create注文内容( )をdelegateする。<BR>
     * <BR>
     * 　@　@　@引数設定は以下のように行う。<BR>
     * <BR>
     * 　@証券会社コード : パラメータ.同項目<BR>
     * 　@扱者 :　@扱者, 市場コード : パラメータ.同項目<BR>
     * 　@銘柄コード : パラメータ.同項目<BR>
     * 　@株数 : パラメータ.同項目<BR>
     * 　@指値 : パラメータ.同項目<BR>
     * 　@執行条件 : パラメータ.同項目<BR>
     * 　@税区分 : パラメータ.同項目<BR>
     * 　@注文失効日 : パラメータ.同項目<BR>
     * 　@is売注文 : パラメータ.同項目<BR>
     * 　@注文チャネル : パラメータ.同項目<BR>
     * 　@値段条件 : パラメータ.同項目<BR>
     * 　@発注条件 : パラメータ.同項目<BR>
     * 　@発注条件演算子 : パラメータ.同項目<BR>
     * 　@逆指値基準値 : パラメータ.同項目<BR>
     * 　@（W指値）訂正指値 : パラメータ.同項目<BR>
     * 　@初回注文の注文単位ID : パラメータ.同項目<BR>
     * <BR>
     * <BR>
     * ２）　@set（Ｗ指値）執行条件( )をcallする。<BR>
     * <BR>
     * 　@　@　@引数設定は以下のように行う。<BR>
     * <BR>
     * 　@　@　@（Ｗ指値）執行条件：パラメータ.同項目<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@param l_trader - (扱者) <BR>
     * 代理入力実施トレーダ。 <BR>
     * 代理入力でない場合、nullを指定。 <BR>
     * @@param l_strMarketCode - (市場コード) <BR>
     * 入力市場コード <BR>
     * @@param l_strProductCode - (銘柄コード) <BR>
     * 入力銘柄コード <BR>
     * @@param l_dblOrderQuantity - (株数) <BR>
     * 入力株数 <BR>
     * @@param l_dblLimitPrice - (指値) <BR>
     * 指値の場合のみ指定。指値以外の場合は0を指定。 <BR>
     * @@param l_execCondType - (執行条件) <BR>
     * 1:条件なし,　@2:寄り,　@3:引け,　@6:不出来引け成行 <BR>
     * （EqTypeExecutionConditionTypeにて定義） <BR>
     * @@param l_taxTypeEnum - (税区分) <BR>
     * 0:その他,　@1:一般,　@2:特定,　@3:特定口座かつ源泉徴収 <BR>
     * （TaxTypeEnumにて定義） <BR>
     * @@param l_tsOrderLaspeDate - (注文失効日)<BR>
     * 注文失効日。<BR>
     * @@param l_blnIsSellOrder - (is売注文) <BR>
     * 売注文の場合はtrue、買注文の場合はfalseを指定する。 <BR>
     * @@param l_strOrderChannel - (注文チャネル)<BR>
     * 注文チャネル<BR>
     * @@param l_strPriceConditionType - (値段条件)<BR>
     * 値段条件<BR>
     * @@param l_strOrderCond - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_strOrderCondOperator - (発注条件演算子)<BR>
     * 発注条件演算子<BR>
     * @@param l_dblStopOrderBasePrice - (逆指値基準値)<BR>
     * 逆指値基準値<BR>
     * @@param l_dblWLimitOrderChange - (（W指値）訂正指値)<BR>
     * （W指値）訂正指値<BR>
     * @@param l_lngFirstOrderUnitId - (初回注文の注文単位ID) <BR>
     * 注文繰越での注文作成時には、繰越対象の注文単位.初回注文の注文単位ID をセット。<BR>
     * 上記以外の新規注文登録時には、nullをセット。<BR>
     * @@param l_wlimitExecCondType - (（Ｗ指値）執行条件)<BR>
     * 1:条件なし,　@2:寄り,　@3:引け,　@6:不出来引け成行 <BR>
     * （EqTypeExecutionConditionTypeにて定義）<BR>
     * @@return WEB3EquityNewCashBasedOrderSpec
     * @@throws WEB3SystemLayerException
     * @@roseuid 4143DDE10363
     */
    public static WEB3EquityNewCashBasedOrderSpec createOrderSpec(
        String l_strInstitutionCode,
        WEB3GentradeTrader l_trader,
        String l_strMarketCode,
        String l_strProductCode,
        double l_dblOrderQuantity,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_execCondType,
        TaxTypeEnum l_taxTypeEnum,
        Timestamp l_tsOrderLaspeDate,
        boolean l_blnIsSellOrder,
        String l_strOrderChannel,
        String l_strPriceConditionType,
        String l_strOrderCond,
        String l_strOrderCondOperator,
        double l_dblStopOrderBasePrice,
        double l_dblWLimitOrderChange,
        Long l_lngFirstOrderUnitId,
        EqTypeExecutionConditionType l_wlimitExecCondType)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "createOrderSpec(String, WEB3GentradeTrader, "
            + "String, String, double, double, EqTypeExecutionConditionType, TaxTypeEnum, "
            + "Timestamp,boolean, String, String, String, String, double, double, Long, "
            + "EqTypeExecutionConditionType";
        log.entering(STR_METHOD_NAME);

        //１）　@株式注文内容.create注文内容( )をdelegateする。
        WEB3EquityNewCashBasedOrderSpec l_orderSpec =
            WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                l_strInstitutionCode,
                l_trader,
                l_strMarketCode,
                l_strProductCode,
                l_dblOrderQuantity,
                l_dblLimitPrice,
                l_execCondType,
                l_taxTypeEnum,
                l_tsOrderLaspeDate,
                l_blnIsSellOrder,
                l_strOrderChannel,
                l_strPriceConditionType,
                l_strOrderCond,
                l_strOrderCondOperator,
                l_dblStopOrderBasePrice,
                l_dblWLimitOrderChange,
                l_lngFirstOrderUnitId);

        //２）　@set（Ｗ指値）執行条件( )をcallする。
        //引数設定は以下のように行う。
        //（Ｗ指値）執行条件：パラメータ.同項目
        l_orderSpec.setWlimitExecCondType(l_wlimitExecCondType);

        log.exiting(STR_METHOD_NAME);

        return l_orderSpec;
    }

    /**
     * (set手数料商品コード) <BR>
     * 手数料商品コードをセットする。 <BR>
     * <BR>
     * @@param l_strCommissionProductCode - 手数料商品コード。 <BR>
     * （会社部店商品テーブル.手数料商品コード） <BR>
     * @@roseuid 4018E64401A7
     */
    public void setCommissionProductCode(String l_strCommissionProductCode)
    {
        this.commissionProductCode = l_strCommissionProductCode;
    }

    /**
     * (get手数料商品コード) <BR>
     * 手数料商品コードを取得する。 <BR>
     * <BR>
     * （会社部店商品テーブル.手数料商品コード） <BR>
     * @@return String
     * @@roseuid 4018E6860253
     */
    public String getCommissionProductCode()
    {
        return this.commissionProductCode;
    }

    /**
     * (create手数料) <BR>
     * 概算売買代金計算に使用する手数料オブジェクトを生成する。 <BR>
     *  <BR>
     * １）　@手数料インスタンスを生成し、プロパティに以下の通りセットを行う。 <BR>
     *  <BR>
     * 証券会社コード： 部店.証券会社コード <BR>
     * 部店ID： 部店.部店ID <BR>
     * 手数料商品コード： this.get手数料商品コード( ) <BR>
     * 取引コード（SONAR）：　@引数の取引コード（SONAR） <BR>
     * 発注日：　@this.get発注日( ) <BR>
     * 弁済区分：　@弁済区分.その他 <BR>
     * 注文チャネル： this.get注文チャネル( ) <BR>
     * is指値： this.isLimitOrder( ) <BR>
     *  <BR>
     * (*) 以外の項目は、計算サービスにてセットする。 <BR>
     *  <BR>
     * ２）　@自身のプロパティに設定する。 <BR>
     *  <BR>
     * this.set手数料( ) <BR>
     * <BR>
     * @@param l_branch
     * @@param l_sonarTradedCode
     * @@return WEB3BusinessLayerException
     * @@roseuid 4143DDE201D4
     */
    public WEB3GentradeCommission createCommission(
        Branch l_branch,
        String l_sonarTradedCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCommission(Branch,String)";

        log.entering(STR_METHOD_NAME);

        // １）　@手数料インスタンスを生成し、プロパティに以下の通りセットを行う。
        WEB3GentradeCommission l_gentradeCommission =
            new WEB3GentradeCommission();
        // 証券会社コード
        l_gentradeCommission.setInstitutionCode(
            l_branch.getInstitution().getInstitutionCode());
        // 部店ID
        l_gentradeCommission.setBranchId(l_branch.getBranchId());
        // 手数料商品コード
        l_gentradeCommission.setCommissionProductCode(
            this.getCommissionProductCode());
        // 取引コード（SONAR）
        l_gentradeCommission.setSonarTradedCode(l_sonarTradedCode);
        // 発注日
        l_gentradeCommission.setOrderBizDate(this.getOrderBizDate());
        // 弁済区分
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
        // 注文チャネル
        l_gentradeCommission.setOrderChannel(this.getOrderChannel());
        // is指値： this.isLimitOrder( ) 
        l_gentradeCommission.setIsLimitPrice(this.isLimitOrder( ));
		//市場コード（SONAR）
		if (this.getMarketCode() != null)
		{
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
			WEB3GentradeFinObjectManager l_finObjectManager =
				(WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
			try
			{
				Market l_market =
					l_finObjectManager.getMarket(this.getInstitutionCode(), this.getMarketCode());
				MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
				l_gentradeCommission.setSonarMarketCode(l_marketRow.getSonarMarketCode());
			}
			catch (NotFoundException l_nfe)
			{
				throw new WEB3SystemLayerException(
				   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				   this.getClass().getName() + "." + STR_METHOD_NAME,
				   l_nfe.getMessage(),
				   l_nfe);
			}
		} 
        
        // ２）　@自身のプロパティに設定する。
        this.setCommission(l_gentradeCommission);

        log.exiting(STR_METHOD_NAME);

        return l_gentradeCommission;
    }

    /**
     * (get手数料) <BR>
     * 手数料オブジェクトを取得する。 <BR>
     * <BR>
     * @@return WEB3EquityCommission
     * @@roseuid 4019EDAB006E
     */
    public WEB3GentradeCommission getCommission()
    {
        return this.equityCommission;
    }

    /**
     * (set手数料) <BR>
     * 手数料オブジェクトをセットする。 <BR>
     * <BR>
     * @@param l_commission - (手数料) <BR>
     * 手数料オブジェクト <BR>
     * @@roseuid 403D5196039D
     */
    public void setCommission(WEB3GentradeCommission l_commission)
    {
        this.equityCommission = l_commission;
    }

    /**
     * (set初回注文の注文単位ID) <BR>
     * 初回注文の注文単位IDをセットする。 <BR>
     * <BR>
     * @@param l_lngfirstOrderUnitId - 初回注文の注文単位ID。 <BR>
     * （注文単位テーブル.初回注文の注文単位ID） <BR>
     * @@roseuid 405ED11502FE
     */
    public void setFirstOrderUnitId(Long l_lngfirstOrderUnitId)
    {
        this.firstOrderUnitId = l_lngfirstOrderUnitId;
    }

    /**
     * (get初回注文の注文単位ID) <BR>
     * 初回注文の注文単位IDを取得する。 <BR>
     * <BR>
     * （注文単位テーブル.初回注文の注文単位ID） <BR>
     * @@return Long
     * @@roseuid 405ED115030E
     */
    public Long getFirstOrderUnitId()
    {
        return firstOrderUnitId;
    }

    /**
     * (set発注条件) <BR>
     * 発注条件をセットする。 <BR>
     * @@param l_strOrderCond - 発注条件 <BR>
     * @@roseuid 4076363E0166
     */
    public void setOrderCond(String l_strOrderCond)
    {
        this.orderCond = l_strOrderCond;
    }

    /**
     * (set発注条件演算子) <BR>
     * 発注条件演算子をセットする。
     * @@param l_strOrderCondOperator 発注条件演算子 <BR>
     * @@roseuid 4076287C032B
     */
    public void setOrderCondOperator(String l_strOrderCondOperator)
    {
        this.orderCondOperator = l_strOrderCondOperator;
    }

    /**
     * (set逆指値基準値) <BR>
     * 逆指値基準値をセットする。
     * @@param l_dblStopOrderBasePrice 逆指値基準値
     * @@roseuid 4076287C0389
     */
    public void setStopOrderBasePrice(double l_stopOrderBasePrice)
    {
        this.stopLimitPriceBasePrice = l_stopOrderBasePrice;
    }

    /**
     * (set（W指値）訂正指値) <BR>
     * （W指値）訂正指値をセットする。
     * @@param l_dblWLimitPriceChange （W指値）訂正指値
     * @@roseuid 4076287C03D7
     */
    public void setWLimitPriceChange(double l_dblWLimitPriceChange)
    {
        this.wLimitPriceChange = l_dblWLimitPriceChange;
    }

    /**
     * (get発注条件) <BR>
     * 発注条件を取得する。
     * @@return String
     * @@roseuid 407636660231
     */
    public String getOrderCond()
    {
        return this.orderCond;
    }

    /**
     * (get発注条件演算子) <BR>
     * 発注条件演算子を取得する。
     * @@return String
     * @@roseuid 407628D80389
     */
    public String getOrderCondOperator()
    {
        return this.orderCondOperator;
    }

    /**
     * (get逆指値基準値) <BR>
     * 逆指値基準値を取得する。
     * @@return Double
     * @@roseuid 407628D803D7
     */
    public double getStopLimitPriceBasePrice()
    {
        return this.stopLimitPriceBasePrice;
    }

    /**
     * (get（W指値）訂正指値) <BR>
     * （W指値）訂正指値を取得する。 
     * @@return Double
     * @@roseuid 407628D9003D
     */
    public double getWLimitPriceChange()
    {
        return this.wLimitPriceChange;
    }

    /**
     * (is出来るまで注文) <BR>
     * 該当注文が出来るまで注文の場合true <BR>
     * 当日のみ注文の場合falseを返す。 <BR>
     * <BR>
     * １）　@this.初回注文の注文単位ID != nullの場合は、trueを返す。 <BR>
     * 　@　@　@this.初回注文の注文単位ID == nullの場合は、falseを返す。 
     * @@return boolean
     * @@roseuid 4063B93202B8
     */
    public boolean isOrderUntilDeadLine()
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
     * (is（W指値）訂正指値) <BR>
     * ・W指値注文の場合は「（W指値）訂正指値」が <BR>
     * 指値であるかどうかを返す。 <BR>
     * 　@指値の場合はtrueを、成行の場合はfalseを返す。 <BR>
     * ・W指値注文でない場合は、例外をthrowする。 <BR>
     * <BR>
     * １）　@this.get発注条件( ) != ”W指値”の場合は、 <BR>
     *  例外をthrowする。 <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag:   BUSINESS_ERROR_00157 <BR>
     * <BR>
     * ２）　@this.get（W指値）訂正指値( ) == 0 の場合は、 <BR>
     *  falseを返す。以外、trueを返す。
     * @@return boolean
     * @@throw WEB3BusinessLayerException
     * @@roseuid 40762A62029E
     */
    public boolean isWLimitPriceChange()
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "isWLimitPriceChange()";
        log.entering(STR_METHOD_NAME);
         
        //this.get発注条件( ) != ”W指値”の場合は、例外をthrowする。 
        if( !(this.getOrderCond().equals(WEB3OrderingConditionDef.W_LIMIT_PRICE)))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00157,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        boolean l_result = true;
        //this.get（W指値）訂正指値( ) == 0 の場合は、falseを返す。以外、trueを返す
        if(this.getWLimitPriceChange() == 0.0D)
        {
            l_result = false;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_result;

    }

    /**
     * (set値段条件)<BR>
     * 値段条件をセットする。
     * @@param l_strPriceConditionType 値段条件
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

    /**
     * (createPTS株式注文内容)<BR>
     * 注文内容を生成する。<BR>
     * <BR>
     * 注文内容インスタンスを生成し、プロパティに値をセットする。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * 　@－株式注文内容インスタンスを生成する。<BR>
     * <BR>
     * ２）　@拡張項目セット<BR>
     * 　@－PTS取引時間管理.get発注日()で取得した日付を発注日にセットする。<BR>
     * 　@－証券会社コードをインスタンスにセットする。<BR>
     * 　@－ログインチャネルをインスタンスにセットする。<BR>
     * 　@－値段条件をインスタンスにセットする。<BR>
     * 　@－発注条件をインスタンスにセットする。<BR>
     * 　@－発注条件演算子をインスタンスにセットする。<BR>
     * 　@－逆指値基準値をインスタンスにセットする。<BR>
     * 　@－（W指値）訂正指値をインスタンスにセットする。<BR>
     * 　@－初回注文の注文単位IDをインスタンスにセットする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_trader - (扱者) <BR>
     * @@param l_strMarketCode - (市場コード) <BR>
     * @@param l_strProductCode - (銘柄コード) <BR>
     * @@param l_dblOrderQuantity - (株数) <BR>
     * @@param l_dblLimitPrice - (指値) <BR>
     * @@param l_execCondType - (執行条件) <BR>
     * @@param l_taxTypeEnum - (税区分) <BR>
     * @@param l_tsOrderLaspeDate - (注文失効日)<BR>
     * @@param l_blnIsSellOrder - (is売注文) <BR>
     * @@param l_strOrderChannel - (注文チャネル)<BR>
     * @@param l_strPriceConditionType - (値段条件)<BR>
     * @@param l_strOrderCond - (発注条件)<BR>
     * @@param l_strOrderCondOperator - (発注条件演算子)<BR>
     * @@param l_dblStopOrderBasePrice - (逆指値基準値)<BR>
     * @@param l_dblWLimitOrderChange - (（W指値）訂正指値)<BR>
     * @@param l_firstOrderUnitId - (初回注文の注文単位ID) <BR>
     * @@return WEB3EquityNewCashBasedOrderSpec
     * @@throws WEB3BaseException
     */
    public static WEB3EquityNewCashBasedOrderSpec createPTSEquityOrderSpec(
        String l_strInstitutionCode,
        WEB3GentradeTrader l_trader,
        String l_strMarketCode,
        String l_strProductCode,
        double l_dblOrderQuantity,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_execCondType,
        TaxTypeEnum l_taxTypeEnum,
        Timestamp l_tsOrderLaspeDate,
        boolean l_blnIsSellOrder,
        String l_strOrderChannel,
        String l_strPriceConditionType,
        String l_strOrderCond,
        String l_strOrderCondOperator,
        double l_dblStopOrderBasePrice,
        double l_dblWLimitOrderChange,
        Long l_firstOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOrderSpec(String, WEB3GentradeTrader, "
            + "String, String, double, double, EqTypeExecutionConditionType, TaxTypeEnum, "
            + "Timestamp,boolean, String, String, String, String, double, double, Long)";
        log.entering(STR_METHOD_NAME);

        //株式注文内容インスタンスを生成する。
        WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec =
            new WEB3EquityNewCashBasedOrderSpec(
                l_trader,
                !l_blnIsSellOrder,
                l_strProductCode,
                l_strMarketCode,
                l_dblOrderQuantity,
                l_dblLimitPrice,
                l_execCondType,
                l_tsOrderLaspeDate,
                l_taxTypeEnum);

        // ２）　@拡張項目セット
        //PTS取引時間管理.get発注日()で取得した日付を発注日にセットする。 
        l_equityNewCashBasedOrderSpec.setOrderBizDate(
            new Timestamp(WEB3EquityPTSTradingTimeManagement.getOrderBizDate().getTime()));

        //証券会社コードをインスタンスにセットする。   
        l_equityNewCashBasedOrderSpec.setInstitutionCode(
            l_strInstitutionCode);

        //ログインチャネルをインスタンスにセットする
        l_equityNewCashBasedOrderSpec.setOrderChannel(
            l_strOrderChannel);

        //値段条件をインスタンスにセットする
        l_equityNewCashBasedOrderSpec.setPriceConditionType(
            l_strPriceConditionType);

        //発注条件をインスタンスにセットする
        l_equityNewCashBasedOrderSpec.setOrderCond(
            l_strOrderCond);

        //発注条件演算子をインスタンスにセットする
        l_equityNewCashBasedOrderSpec.setOrderCondOperator(
            l_strOrderCondOperator);

        //逆指値基準値をインスタンスにセットする
        l_equityNewCashBasedOrderSpec.setStopOrderBasePrice(
            l_dblStopOrderBasePrice);

        //（W指値）訂正指値をインスタンスにセットする
        l_equityNewCashBasedOrderSpec.setWLimitPriceChange(
            l_dblWLimitOrderChange);

        //初回注文の注文単位IDをインスタンスにセットする
        l_equityNewCashBasedOrderSpec.setFirstOrderUnitId(
            l_firstOrderUnitId);

        log.exiting(STR_METHOD_NAME);
        return l_equityNewCashBasedOrderSpec;
    }
}
@
