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
filename	WEB3EquityChangeOrderUnitEntry.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文訂正値詳細(WEB3EquityChangeOrderUnitEntry.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/19 周玲玲 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2006/11/01 趙林鵬 (中訊) モデル No.995
                   2006/11/21 柴双紅 (中訊) モデル No.1068
*/
package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderRequestAdapter;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文訂正値詳細）。<BR>
 * <BR>
 * 訂正入力内容の詳細（EqTypeChangeOrderUnitEntry）を表現する。
 * @@version 1.0
 */
public class WEB3EquityChangeOrderUnitEntry extends EqTypeChangeOrderUnitEntry
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityChangeOrderUnitEntry.class);

    /**
     * (訂正後執行条件)<BR>
     * （afterChangeExecutionConditionType）<BR>
     * 訂正後の執行条件。<BR>
     */
    private EqTypeExecutionConditionType afterChangeExecutionConditionType;

    /**
     * (注文単位)<BR>
     * 訂正対象の注文単位オブジェクト<BR>
     */
    private OrderUnit orderUnit;

    /**
     * (訂正後is出来るまで注文)<BR>
     * 訂正入力内容が、出来るまで注文であるかどうかのフラグ。<BR>
     * 出来るまで注文であればtrue、以外はfalse。<BR>
     */
    private boolean changeAfterIsOrderUntilDeadLine;

    /**
     * (訂正後値段条件)<BR>
     * 訂正入力の値段条件。<BR>
     */
    private String changeAfterPriceConditionType;

    /**
     * (訂正後発注条件)<BR>
     * 訂正入力の発注条件。<BR>
     */
    private String changeAfterOrderCondType;

    /**
     * (訂正後発注条件演算子)<BR>
     * 訂正入力の発注条件演算子。<BR>
     */
    private String changeAfterOrderCondOperator;

    /**
     * (訂正後逆指値基準値)<BR>
     * 訂正入力の逆指値基準値。<BR>
     */
    private double changeAfterStopOrderCondPriceBasePrice;

    /**
     * (訂正後（W指値）訂正指値)<BR>
     * 訂正入力の（W指値）訂正指値。<BR>
     */
    private double changeAfterWlimitOrderCondPrice;

    /**
     * (訂正後注文失効日)<BR>
     * 訂正後の注文失効日（注文有効期限）。<BR>
     */
    private Date modifiedExpirationDate;

    /**
     * (訂正後（W指値）執行条件)<BR>
     * 訂正後（W指値）執行条件<BR>
     */
    private EqTypeExecutionConditionType modifiedWlimitExecCondType;

    /**
     * (（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分<BR>
     */
    private String wlimitEnableStatusDiv;

    /**
     * @@roseuid 40A9D29D0053<BR>
     */
    public WEB3EquityChangeOrderUnitEntry(
        long l_lngOrderUnitId, 
        double l_dblAfterChangeOrigQuantity, 
        double l_dblAfterChangePrice)
    {
        super(l_lngOrderUnitId, l_dblAfterChangeOrigQuantity, l_dblAfterChangePrice);
    }

    /**
     * (get訂正後執行条件)<BR>
     * （getAfterChangeExecutionConditionType）<BR>
     * 訂正後の執行条件を取得する。<BR>
     * @@return EqTypeExecutionConditionType<BR>
     * @@roseuid 4018ED550233<BR>
     */
    public EqTypeExecutionConditionType getAfterChangeExecutionConditionType()
    {
        return this.afterChangeExecutionConditionType;
    }

    /**
     * (create株式注文訂正値詳細)<BR>
     * 注文訂正値詳細オブジェクトを生成し、<BR>
     * リクエストアダプタの内容よりプロパティをセットする。<BR>
     * <BR>
     * １）　@訂正対象注文単位取得<BR>
     * 　@リクエストアダプタ.get注文ID()にて、訂正元注文の注文IDを取得する。<BR>
     * 　@取得した注文IDに該当する注文オブジェクトを取得する。<BR>
     * 　@取得した注文オブジェクト.getOrderUnits()にて取得した注文単位オブジェクトのうち、<BR>
     * 　@0番目の要素を取得する。<BR>
     * <BR>
     * ２）　@インスタンス生成<BR>
     * 　@スーパークラスのコンストラクタ（EqTypeChangeOrderUnitEntry）をコールする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@orderUnitId：　@１）で取得した注文オブジェクト.注文単位ID。<BR>
     * 　@afterChangeOrigQuantity：　@リクエストアダプタ.get訂正後株数<BR>
     * 　@afterChangePrice：　@リクエストアダプタ.get訂正後単価<BR>
     * <BR>
     * ３）　@拡張項目セット<BR>
     * 　@自身のインスタンスに以下の通りセットする。<BR>
     * <BR>
     * 　@－訂正後執行条件：　@リクエストアダプタ.get執行条件( )<BR>
     * 　@－注文単位：　@１）で取得した注文単位オブジェクト<BR>
     * 　@－訂正後is出来るまで注文：　@リクエストアダプタ.is出来るまで注文( )<BR>
     * 　@－訂正後値段条件：　@リクエストアダプタ.リクエストデータ.値段条件<BR>
     * 　@－訂正後発注条件：　@リクエストアダプタ.リクエストデータ.発注条件区分<BR>
     * 　@－訂正後注文失効日：　@リクエストアダプタ.get訂正後注文失効日( )<BR>
     * <BR>
     * 　@※コード読み替え等が必要な項目は、リクエストアダプタのgetterを使用してセット。<BR>
     * 　@※上記が必要ない項目は、リクエストアダプタ.リクエストデータの同項目をそのままセット。<BR>
     * <BR>
     * 以下は、リクエストアダプタ.リクエストデータ.発注条件区分 により設定仕様が異なる。<BR>
     * (1)発注条件区分＝”指定なし”の場合<BR>
     * 　@－訂正後発注条件演算子：　@null<BR>
     * 　@－訂正後逆指値基準値：　@0<BR>
     * 　@－訂正後（W指値）訂正指値：　@0<BR>
     * 　@－訂正後（W指値）執行条件：　@null <BR>
     * 　@－（W指値）有効状態区分：　@null<BR>
     * <BR>
     * (2)発注条件区分＝”逆指値”の場合<BR>
     * 　@－訂正後発注条件演算子：　@リクエストアダプタ.リクエストデータ.逆指値用発注条件演算子<BR>
     * 　@－訂正後逆指値基準値：　@リクエストアダプタ.リクエストデータ.逆指値用発注条件単価 をdoubleに変換した値<BR>
     * 　@－訂正後（W指値）訂正指値：　@0<BR>
     * 　@－訂正後（W指値）執行条件：　@null<BR>
     * 　@－（W指値）有効状態区分：　@null <BR>
     * <BR>
     * (3)発注条件区分＝”W指値”の場合<BR>
     * 　@－訂正後発注条件演算子：　@リクエストアダプタ.リクエストデータ.W指値用発注条件演算子<BR>
     * 　@－訂正後逆指値基準値：　@リクエストアダプタ.リクエストデータ.W指値用発注条件単価 をdoubleに変換した値<BR>
     * 　@－訂正後（W指値）訂正指値：　@リクエストアダプタ.リクエストデータ.W指値用注文単価 をdoubleに変換した値。<BR>
     * 　@　@　@※リクエストアダプタ.リクエストデータ.W指値用注文単価区分=="成行"の場合は、0をセット。<BR>
     * 　@－訂正後（W指値）執行条件：　@リクエストアダプタ.get（W指値）執行条件() <BR>
     * 　@－（W指値）有効状態区分：　@リクエストアダプタ.リクエストデータ.Ｗ指値用有効状態区分<BR>
     * <BR>　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@
     * @@param l_requestAdaptor - (リクエストアダプタ)<BR>
     * リクエストアダプタ<BR>
     * @@return WEB3EquityChangeOrderUnitEntry<BR>
     * @@roseuid 4021DBBD01C1<BR>
     */
    public static WEB3EquityChangeOrderUnitEntry createChangeOrderUnitEntry(
        WEB3EquityChangeOrderRequestAdapter l_requestAdaptor) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createChangeOrderUnitEntry(WEB3EquityChangeOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        //訂正対象注文ID取得
        long l_lngOrderId = l_requestAdaptor.getRequestOrderId();

        //取得した注文IDに該当する注文オブジェクトを取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        Order l_order = null;
        try
        {
            l_order = l_tradingMod.getOrderManager().getOrder(l_lngOrderId);
        }
        catch (NotFoundException nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3EquityChangeOrderUnitEntry.class + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }

        //get注文単位
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        OrderUnit l_orderUnit = l_orderUnits[0];

        //インスタンス生成
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            new WEB3EquityChangeOrderUnitEntry(
                l_orderUnit.getOrderUnitId(), 
                l_requestAdaptor.getRequestOrderQuantity(), 
                l_requestAdaptor.getRequestLimitPrice());

        //set訂正後執行条件
        l_changeOrderUnitEntry.afterChangeExecutionConditionType = l_requestAdaptor.getExecCondType();

        //set注文単位
        l_changeOrderUnitEntry.orderUnit = l_orderUnit;

        //set訂正後is出来るまで注文
        l_changeOrderUnitEntry.changeAfterIsOrderUntilDeadLine = 
            l_requestAdaptor.isOrderUntilDeadLine();    

        //set訂正後値段条件
        //set訂正後発注条件
        String l_strPriceConditionType = null;
        String l_orderCondType = null;
        if(l_requestAdaptor.requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            WEB3EquityChangeCompleteRequest l_equityChangeCompleteRequest = 
                (WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData;
            l_strPriceConditionType = l_equityChangeCompleteRequest.priceCondType;
            l_orderCondType = l_equityChangeCompleteRequest.orderCondType;
        }
        else if(l_requestAdaptor.requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            WEB3EquityChangeConfirmRequest l_equityChangeComfirmRequest = 
                (WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData;
            l_strPriceConditionType = l_equityChangeComfirmRequest.priceCondType;
            l_orderCondType = l_equityChangeComfirmRequest.orderCondType;
        }
        else
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                WEB3EquityChangeOrderUnitEntry.class + "." + STR_METHOD_NAME);
        }
        l_changeOrderUnitEntry.changeAfterPriceConditionType = l_strPriceConditionType;
        l_changeOrderUnitEntry.changeAfterOrderCondType = l_orderCondType;
        
        // set訂正後注文失効日
        l_changeOrderUnitEntry.modifiedExpirationDate = l_requestAdaptor.getExpirationDate();

        //発注条件区分＝”指定なし”の場合
        //   　@－訂正後発注条件演算子：　@null
        //   　@－訂正後逆指値基準値：　@0
        //   　@－訂正後（W指値）訂正指値：　@0
        //   　@－訂正後（W指値）執行条件：　@null
        //   　@－（W指値）有効状態区分：　@null
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_orderCondType))
        {
            l_changeOrderUnitEntry.changeAfterOrderCondOperator = null;
            l_changeOrderUnitEntry.changeAfterStopOrderCondPriceBasePrice = 0.0D;
            l_changeOrderUnitEntry.changeAfterWlimitOrderCondPrice = 0.0D;
            l_changeOrderUnitEntry.modifiedWlimitExecCondType = null;
            l_changeOrderUnitEntry.wlimitEnableStatusDiv = null;
        }

        //発注条件区分＝”逆指値”の場合
        //－訂正後発注条件演算子：　@リクエストアダプタ.リクエストデータ.逆指値用発注条件演算子
        //－訂正後逆指値基準値：　@リクエストアダプタ.リクエストデータ.逆指値用発注条件単価 をdoubleに変換した値
        //－訂正後（W指値）訂正指値：　@0
        //－訂正後（W指値）執行条件：　@null
        //－（W指値）有効状態区分：　@null
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderCondType))
        {
            if (l_requestAdaptor.requestData instanceof WEB3EquityChangeConfirmRequest)
            {
                l_changeOrderUnitEntry.changeAfterOrderCondOperator =
                    ((WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData).stopOrderCondOperator;

                l_changeOrderUnitEntry.changeAfterStopOrderCondPriceBasePrice =
                    (Double.parseDouble(
                        ((WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData).stopOrderCondPrice));
            }
            else
            {
                l_changeOrderUnitEntry.changeAfterOrderCondOperator =
                    ((WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData).stopOrderCondOperator;

                l_changeOrderUnitEntry.changeAfterStopOrderCondPriceBasePrice =
                    (Double.parseDouble(
                        ((WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData).stopOrderCondPrice));
            }

            l_changeOrderUnitEntry.changeAfterWlimitOrderCondPrice = 0.0D;

            l_changeOrderUnitEntry.modifiedWlimitExecCondType = null;

            l_changeOrderUnitEntry.wlimitEnableStatusDiv = null;
        }

        //発注条件区分＝”W指値”の場合
        //－訂正後発注条件演算子：　@リクエストアダプタ.リクエストデータ.W指値用発注条件演算子
        //－訂正後逆指値基準値：　@リクエストアダプタ.リクエストデータ.W指値用発注条件単価 をdoubleに変換した値
        //－訂正後（W指値）訂正指値：　@リクエストアダプタ.リクエストデータ.W指値用注文単価 をdoubleに変換した値。
        //※リクエストアダプタ.リクエストデータ.W指値用注文単価区分=="成行"の場合は、0をセット。
        //－訂正後（W指値）執行条件：　@リクエストアダプタ.get（W指値）執行条件()
        //－（W指値）有効状態区分：　@リクエストアダプタ.リクエストデータ.Ｗ指値用有効状態区分
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderCondType))
        {
            if (l_requestAdaptor.requestData instanceof WEB3EquityChangeConfirmRequest)
            {
                l_changeOrderUnitEntry.changeAfterOrderCondOperator =
                    ((WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData).wlimitOrderCondOperator;

                l_changeOrderUnitEntry.changeAfterStopOrderCondPriceBasePrice =
                    Double.parseDouble(
                        ((WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData).wlimitOrderCondPrice);

                if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(
                    ((WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData).wLimitOrderPriceDiv))
                {
                    l_changeOrderUnitEntry.changeAfterWlimitOrderCondPrice = 0.0D;
                }
                else
                {
                    l_changeOrderUnitEntry.changeAfterWlimitOrderCondPrice =
                        Double.parseDouble(
                            ((WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData).wLimitPrice);
                }

                l_changeOrderUnitEntry.modifiedWlimitExecCondType =
                    l_requestAdaptor.getWLimitExecCondType();

                l_changeOrderUnitEntry.wlimitEnableStatusDiv =
                    ((WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData).wlimitEnableStatusDiv;

            }
            else
            {
                l_changeOrderUnitEntry.changeAfterOrderCondOperator =
                    ((WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData).wlimitOrderCondOperator;

                l_changeOrderUnitEntry.changeAfterStopOrderCondPriceBasePrice =
                    Double.parseDouble(
                        ((WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData).wlimitOrderCondPrice);

                if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(
                    ((WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData).wLimitOrderPriceDiv))
                {
                    l_changeOrderUnitEntry.changeAfterWlimitOrderCondPrice = 0.0D;
                }
                else
                {
                    l_changeOrderUnitEntry.changeAfterWlimitOrderCondPrice =
                        Double.parseDouble(
                            ((WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData).wLimitPrice);
                }

                l_changeOrderUnitEntry.modifiedWlimitExecCondType =
                    l_requestAdaptor.getWLimitExecCondType();

                l_changeOrderUnitEntry.wlimitEnableStatusDiv =
                    ((WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData).wlimitEnableStatusDiv;

            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_changeOrderUnitEntry;
    }

    /**
     * (get注文単位)<BR>
     * 訂正対象注文単位オブジェクトを取得する。<BR>
     * @@return OrderUnit<BR>
     * @@roseuid 4021E0A60173<BR>
     */
    public OrderUnit getOrderUnit()
    {
        return this.orderUnit;
    }

    /**
     * (get訂正後is出来るまで注文)<BR>
     * 訂正後が「出来るまで注文」であるかどうかを返す。<BR>
     * @@return boolean<BR>
     * @@roseuid 4074D20800BB<BR>
     */
    public boolean getChangeAfterIsOrderUntilDeadLine()
    {
        return this.changeAfterIsOrderUntilDeadLine;
    }

    /**
     * (get訂正後値段条件)<BR>
     * 訂正後の値段条件を取得する。<BR>
     * @@return String
     */
    public String getChangeAfterPriceConditionType() 
    {
        return this.changeAfterPriceConditionType;
    }

    /**
     * (get訂正後発注条件)<BR>
     * 訂正後の発注条件を取得する。<BR>
     * @@return String<BR>
     * @@roseuid 4074D188033B<BR>
     */
    public String getChangeAfterOrderCondType()
    {
        return this.changeAfterOrderCondType;
    }

    /**
     * (get訂正後発注条件演算子)<BR>
     * 訂正後の発注条件演算子を取得する。<BR>
     * @@return String<BR>
     * @@roseuid 4074DDA3001E<BR>
     */
    public String getChangeAfterOrderCondOperator()
    {
        return this.changeAfterOrderCondOperator;
    }

    /**
     * (get訂正後逆指値基準値)<BR>
     * 訂正後の逆指値基準値を取得する。<BR>
     * @@return double<BR>
     * @@roseuid 4074DDE1000F<BR>
     */
    public double getChangeAfterStopOrderCondPriceBasePrice()
    {
        return this.changeAfterStopOrderCondPriceBasePrice;
    }

    /**
     * (get訂正後（W指値）訂正指値)<BR>
     * 訂正後の（W指値）訂正指値を取得する。<BR>
     * @@return double<BR>
     * @@roseuid 4074DE0202ED<BR>
     */
    public double getChangeAfterWlimitOrderCondPrice()
    {
        return this.changeAfterWlimitOrderCondPrice;
    }

    /**
     * (get訂正後注文失効日)<BR>
     * 訂正後の注文失効日を取得する。<BR>
     * @@return Date
     */
    public Date getModifiedExpirationDate() 
    {
        return this.modifiedExpirationDate;
    }

    /**
     * (get訂正後（W指値）執行条件)<BR>
     * 訂正後の（W指値）執行条件を取得する。<BR>
     * @@return EqTypeExecutionConditionType
     */
    public EqTypeExecutionConditionType getModifiedWlimitExecCondType()
    {
        return this.modifiedWlimitExecCondType;
    }

    /**
     * (get（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分を取得する。<BR>
     * @@return String
     */
    public String getWlimitEnableStatusDiv()
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

    /**
     * (株式注文訂正値詳細)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * 　@スーパークラスのコンストラクタ（EqTypeChangeOrderUnitEntry）をコールする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@orderUnitId：　@パラメータ.注文単位.注文単位ID<BR>
     * 　@　@afterChangeOrigQuantity：　@パラメータ.注文単位.注文数量<BR>
     * 　@　@afterChangePrice：　@パラメータ.注文単位.（W指値）訂正指値<BR>
     * <BR>
     * ２）　@拡張プロパティをセットする<BR>
     * <BR>
     * 　@－訂正後執行条件：　@パラメータ.訂正後執行条件<BR>
     * 　@－注文単位：　@パラメータ.注文単位<BR>
     * 　@－訂正後is出来るまで注文：　@パラメータ.訂正後is出来るまで注文<BR>
     * 　@－訂正後値段条件：　@パラメータ.訂正後値段条件<BR>
     * 　@－訂正後発注条件：　@パラメータ.訂正後発注条件<BR>
     * 　@－訂正後発注条件演算子：　@パラメータ.訂正後発注条件演算子<BR>
     * 　@－訂正後逆指値基準値：　@パラメータ.訂正後逆指値基準値<BR>
     * 　@－訂正後（W指値）訂正指値：　@パラメータ.訂正後（W指値）訂正指値<BR>
     * 　@－訂正後注文失効日：　@パラメータ.訂正後注文失効日<BR>
     * 　@－訂正後（W指値）執行条件：　@パラメータ.訂正後（W指値）執行条件<BR>
     * 　@－（W指値）有効状態区分：　@パラメータ.（W指値）有効状態区分<BR>
     * @@param l_afterChangeExecutionConditionType - (訂正後執行条件)<BR>
     * 訂正後執行条件<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_blnAfterChangeIsOrderUntilDeadLine - (訂正後is出来るまで注文)<BR>
     * 訂正後is出来るまで注文<BR>
     * @@param l_strAfterChangePriceConditionType - (訂正後値段条件)<BR>
     * 訂正後値段条件<BR>
     * @@param l_strAfterChangeOrderCondType - (訂正後発注条件)<BR>
     * 訂正後発注条件<BR>
     * @@param l_strAfterChangeOrderCondOperator - (訂正後発注条件演算子)<BR>
     * 訂正後発注条件演算子<BR>
     * @@param l_dblAfterChangeStopOrderCondPriceBasePrice - (訂正後逆指値基準値)<BR>
     * 訂正後逆指値基準値<BR>
     * @@param l_dblAfterChangeWlimitOrderCondPrice - (訂正後（W指値）訂正指値)<BR>
     * 訂正後（W指値）訂正指値<BR>
     * @@param l_afterChangeExpirationDate - (訂正後注文失効日)<BR>
     * 訂正後注文失効日<BR>
     * @@param l_afterChangeWlimitExecCondType - (訂正後（W指値）執行条件)<BR>
     * 訂正後（W指値）執行条件<BR>
     * @@param l_strWlimitEnableStatusDiv - (（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分
     */
    public WEB3EquityChangeOrderUnitEntry(
        EqTypeExecutionConditionType l_afterChangeExecutionConditionType,
        OrderUnit l_orderUnit,
        boolean l_blnAfterChangeIsOrderUntilDeadLine,
        String l_strAfterChangePriceConditionType,
        String l_strAfterChangeOrderCondType,
        String l_strAfterChangeOrderCondOperator,
        double l_dblAfterChangeStopOrderCondPriceBasePrice,
        double l_dblAfterChangeWlimitOrderCondPrice,
        Date l_afterChangeExpirationDate,
        EqTypeExecutionConditionType l_afterChangeWlimitExecCondType,
        String l_strWlimitEnableStatusDiv)
    {
        //１）　@インスタンス生成
        super(l_orderUnit.getOrderUnitId(),
            l_orderUnit.getQuantity(),
            ((EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject()).getWLimitPrice());
        //訂正後執行条件
        this.afterChangeExecutionConditionType = l_afterChangeExecutionConditionType;
        //注文単位
        this.orderUnit = l_orderUnit;
        //訂正後is出来るまで注文
        this.changeAfterIsOrderUntilDeadLine = l_blnAfterChangeIsOrderUntilDeadLine;
        //訂正後値段条件
        this.changeAfterPriceConditionType = l_strAfterChangePriceConditionType;
        //訂正後発注条件
        this.changeAfterOrderCondType = l_strAfterChangeOrderCondType;
        //訂正後発注条件演算子
        this.changeAfterOrderCondOperator = l_strAfterChangeOrderCondOperator;
        //訂正後逆指値基準値
        this.changeAfterStopOrderCondPriceBasePrice = l_dblAfterChangeStopOrderCondPriceBasePrice;
        //訂正後（W指値）訂正指値
        this.changeAfterWlimitOrderCondPrice = l_dblAfterChangeWlimitOrderCondPrice;
        //訂正後注文失効日
        this.modifiedExpirationDate = l_afterChangeExpirationDate;
        //訂正後（W指値）執行条件
        this.modifiedWlimitExecCondType = l_afterChangeWlimitExecCondType;
        //（W指値）有効状態区分
        this.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;
    }
}
@
