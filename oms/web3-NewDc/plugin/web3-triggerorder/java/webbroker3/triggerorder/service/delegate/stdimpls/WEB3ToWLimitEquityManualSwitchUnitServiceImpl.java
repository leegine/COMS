head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEquityManualSwitchUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式W指値注文手動切替UnitServiceImpl(WEB3ToWLimitEquityManualSwitchUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/18 徐宏偉 (中訊) 新規作成 （モデル）No.184 ,196 , 202
Revesion History : 2007/01/13 徐宏偉 (中訊) モデル219
Revesion History : 2007/01/30 唐性峰 (中訊) モデル228
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.define.WEB3EquityWlimitOrderPriceDivDef;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.service.WEB3RlsRequestSenderService;
import webbroker3.triggerorder.WEB3ToRlsCoopDataManager;
import webbroker3.triggerorder.message.WEB3EquityManualUnit;
import webbroker3.triggerorder.message.WEB3ManualCommissionInfoUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquityManualSwitchUnitService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株式W指値注文手動切替UnitServiceImpl)
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3ToWLimitEquityManualSwitchUnitServiceImpl
    extends WEB3ToEquityManualOrderUnitServiceImpl
    implements WEB3ToWLimitEquityManualSwitchUnitService
{
    /**
     * ログオブジェクト
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3ToWLimitEquityManualSwitchUnitServiceImpl.class);

    /**
     * (set取引カレンダコンテキスト)<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * <BR>
     * １）　@部店オブジェクトを取得。<BR>
     * 　@　@　@拡張アカウントマネージャ.getBranch()をコールする。<BR>
     * <BR>
     * 　@　@　@[getBranch()に設定する引数]<BR>
     * 　@　@　@arg0：　@注文データ.getBranchId()の戻り値<BR>
     * <BR>
     * ２）　@証券会社オブジェクトを取得。<BR>
     * 　@　@　@getBranch()の戻り値.getInstitution()をコールする。<BR>
     * <BR>
     * ３）　@市場オブジェクトを取得する。<BR>
     * 　@　@３－１）　@注文単位Rowを生成する。<BR>
     * 　@　@　@　@　@　@　@　@注文単位.getDataSourceObject()をコールする。<BR>
     * <BR>
     * 　@　@３－２）　@拡張金融オブジェクトマネージャ.getMarket()をコールする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@[getMarket()に設定する引数]<BR>
     * 　@　@　@　@　@　@　@　@　@注文単位Row.getMarketId()<BR>
     * <BR>
     * ４）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－パラメータ.注文データの内容より取引時間コンテキストの<BR>
     * 　@　@　@プロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = getInstitution()の戻り値.getInstitutionCode()<BR>
     * 　@取引カレンダコンテキスト.部店コード = getBranch()の戻り値.getBranchCode()<BR>
     * 　@取引カレンダコンテキスト.市場コード = getMarket()の戻り値.getMarketCode()<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”株式・信用”<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = (*1)<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”訂正”<BR>
     * <BR>
     * 　@(*1)注文受付商品<BR>
     * 　@　@　@・注文カテゴリ（注文データ.getOrderCateg()）が”現物注文”の場合、<BR>
     * 　@　@　@　@　@　@”株式”をセットする。<BR>
     * 　@　@　@・注文カテゴリが上記以外の場合、<BR>
     * 　@　@　@　@　@　@”信用”をセットする。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@取引時間コンテキストをセットする。<BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ５）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * @@param l_orderUnit - (注文データ)<BR>
     * @@throws WEB3BaseException<BR>
     */
    protected void setTradingClendarContext(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setTradingClendarContext(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //（継承元クラスの同名メソッドのオーバーライド）
        //取引カレンダが利用するコンテキストを生成する。
        //１）　@部店オブジェクトを取得。
        //　@　@　@拡張アカウントマネージャ.getBranch()をコールする。
        //　@　@　@[getBranch()に設定する引数]
        //　@　@　@arg0：　@注文データ.getBranchId()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        Branch l_branch = null;

        try
        {
            l_branch = l_accountManager.getBranch(l_orderUnit.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //２）　@証券会社オブジェクトを取得
        //　@　@　@getBranch()の戻り値.getInstitution()をコールする
        Institution l_institution = l_branch.getInstitution();

        //３）　@市場オブジェクトを取得する
        //　@　@３－１）　@注文単位Rowを生成する
        //　@　@　@　@　@　@　@　@注文単位.getDataSourceObject()をコールする
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //　@　@３－２）　@拡張金融オブジェクトマネージャ.getMarket()をコールする
        //　@　@　@　@　@　@　@　@　@[getMarket()に設定する引数]
        //　@　@　@　@　@　@　@　@　@注文単位Row.getMarketId()
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        long l_lngMarketId = l_eqtypeOrderUnitRow.getMarketId();
        WEB3GentradeMarket l_market = null;

        try
        {
            l_market =
                (WEB3GentradeMarket)l_finObjManager.getMarket(l_lngMarketId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //４）　@取引カレンダコンテキストに内容をセットする。
        //　@－パラメータ.注文データの内容より取引時間コンテキストの
        //　@　@　@プロパティをセットする。
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        //　@取引カレンダコンテキスト.証券会社コード = getInstitution()の戻り値.getInstitutionCode()
        l_context.setInstitutionCode(l_institution.getInstitutionCode());

        //　@取引カレンダコンテキスト.部店コード = getBranch()の戻り値.getBranchCode()
        l_context.setBranchCode(l_branch.getBranchCode());

        //　@取引カレンダコンテキスト.市場コード = getMarket()の戻り値.getMarketCode()
        l_context.setMarketCode(l_market.getMarketCode());

        //　@取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

        //　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

        //　@取引カレンダコンテキスト.注文受付商品 = (*1)
        //　@(*1)注文受付商品
        //　@　@　@・注文カテゴリ（注文データ.getOrderCateg()）が”現物注文”の場合
        //　@　@　@　@　@　@”株式”をセットする。
        //　@　@　@・注文カテゴリが上記以外の場合、
        //　@　@　@　@　@　@”信用”をセットする。
        if (OrderCategEnum.ASSET.equals(l_orderUnit.getOrderCateg()))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        }
        //　@  ・注文カテゴリが上記以外の場合
        //　@　@　@　@”信用”をセットする。
        else
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        }
        //　@取引カレンダコンテキスト.注文受付トランザクション = ”訂正”
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);

        //　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて
        //　@　@　@取引時間コンテキストをセットする
        //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //５）　@受付日時、日付ロールをセットする。
        //　@－取引時間管理.setTimestamp()をコールする。
        WEB3GentradeTradingTimeManagement.setTimestamp();
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getUnitレスポンス)<BR>
     * 株式手動発注UnitにW指値注文固有のプロパティをセットする。<BR>
     * <BR>
     * <BR>
     * １）　@注文データを"株式注文単位型"にキャストする。<BR>
     * 　@　@（※以下、キャストしたオブジェクトを"注文単位"と表記する。）<BR>
     * <BR>
     * ２）　@共通のプロパティをセットする。<BR>
     * 　@　@　@super.getUnitレスポンス()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[getUnitレスポンス()に設定する引数]<BR>
     * 　@　@　@　@注文データ：　@１）で生成した注文単位<BR>
     * <BR>
     * ３）　@注文単位.発注条件 == "W指値"の場合のみ<BR>
     * 　@　@　@執行条件を取得する。<BR>
     * <BR>
     * 　@　@　@拡張株式注文マネージャ.get執行条件（SONAR）()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[get執行条件(SONAR)()に設定する引数]<BR>
     * 　@　@　@　@執行条件：　@注文単位.(W指値)執行条件<BR>
     * <BR>
     * ４）　@発注状況区分を取得する。<BR>
     * 　@　@　@株式データアダプタ.getW指値発注状況区分()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[getW指値発注状況区分()に設定する引数]<BR>
     * 　@　@　@　@注文単位：　@注文単位<BR>
     * <BR>
     * ５）　@W指値注文手動発注エラーコードを取得する。<BR>
     * 　@　@　@ルールエンジン連携データマネージャ.getW指値手動発注エラーコード()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[getW指値手動発注エラーコード()に設定する引数]<BR>
     * 　@　@　@　@注文単位：　@注文単位<BR>
     * <BR>
     * ６）　@ルールエンジンからの通知データを取得する。<BR>
     * 　@　@　@ルールエンジン連携データマネージャ.getルールエンジンからの通知データ()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[getルールエンジンからの通知データ()に設定する引数]<BR>
     * 　@　@　@　@注文単位：　@注文単位<BR>
     * 　@　@　@　@条件注文種別：　@”W指値注文”<BR>
     * 　@　@　@　@銘柄タイプ：　@ProductTypeEnum.株式<BR>
     * <BR>
     * ７）　@拡張アカウントマネージャ.get補助口座()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[get補助口座()の引数]<BR>
     * 　@　@　@　@arg0：　@注文単位.getAccountId()<BR>
     * 　@　@　@　@arg1：　@注文単位.getSubAccountId()<BR>
     * <BR>
     * ８） 手動発注手数料情報を取得する。<BR>
     * <BR>
     * 　@８－１）　@株式計算サービス.create手数料()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[create手数料()の引数]<BR>
     * 　@　@　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * 　@８－２）　@this.get概算代金計算結果()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[get概算代金計算結果()に設定する引数]<BR>
     * 　@　@　@　@注文単位：　@注文単位<BR>
     * 　@　@　@　@補助口座：　@get補助口座()の戻り値<BR>
     * 　@　@　@　@手数料：　@手数料オブジェクト<BR>
     * <BR>
     * 　@８－３）　@this.create手動発注手数料情報()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[create手動発注手数料情報()に設定する引数]<BR>
     * 　@　@　@　@補助口座：　@get補助口座()の戻り値<BR>
     * 　@　@　@　@手数料：　@手数料オブジェクト<BR>
     * <BR>
     * ９）　@注文単位.注文カテゴリ == "返済注文"の場合のみ<BR>
     * 　@　@　@信用取引建株明細を取得する。<BR>
     * <BR>
     * 　@　@　@this.create建株明細byOrder()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[create建株明細byOrder()に設定する引数]<BR>
     * 　@　@　@　@注文単位：　@注文単位<BR>
     * <BR>
     * １０）　@getUnitレスポンス()の戻り値（株式手動発注Unit）に<BR>
     * 　@　@　@W指値注文固有のプロパティをセットする。<BR>
     * <BR>
     * 　@条件注文種別：　@"W指値注文"をセット<BR>
     * 　@発注条件演算子：　@(*1)注文単位.発注条件演算子<BR>
     * 　@発注条件単価：　@(*1)注文単位.逆指値基準値<BR>
     * 　@W指値用注文単価区分：　@(*1)注文単位.（W指値）訂正指値が"0"の場合"成行"をセット<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@以外、"指値"をセット<BR>
     * 　@W指値用注文単価：　@(*1)注文単位.（W指値）訂正指値が"0"以外の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@注文単位.（W指値）訂正指値をセット<BR>
     * 　@W指値用執行条件：　@(*1)get執行条件（SONAR）()の戻り値<BR>
     * 　@発注状況区分：　@getW指値発注状況区分()の戻り値<BR>
     * 　@概算受渡代金：　@概算代金計算結果.get概算受渡代金()の戻り値<BR>
     * 　@手動発注エラーコード：　@getW指値手動発注エラーコード()の戻り値<BR>
     * 　@時価情報受信時間：　@(*2)ルールエンジンからの通知Params.tickヒットタイムスタンプ<BR>
     * 　@トリガー起動時間：　@(*2)ルールエンジンからの通知Params.ルールエンジンファ@イアタイムスタンプ<BR>
     * 　@発注完了時間：　@(*2)ルールエンジンからの通知Params.発注完了タイムスタンプ<BR>
     * 　@手動発注手数料情報：　@create手動発注手数料情報()の戻り値<BR>
     * 　@信用取引建株明細：　@create建株明細byOrder()の戻り値<BR>
     * <BR>
     * 　@(*1)注文単位.発注条件が"W指値"の場合のみセット<BR>
     * 　@(*2)getルールエンジンからの通知()の戻り値≠nullの場合セット<BR>
     * <BR>
     * １１）　@プロパティをセットした株式手動発注Unitを返す。<BR>
     * <BR>
     * @@param l_orderUnit - ( 注文データ)
     * @@return WEB3EquityManualUnit
     * @@throws WEB3BaseException
     */
    protected WEB3EquityManualUnit getUnitResponse(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUnitResponse(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）　@注文データを"株式注文単位型"にキャストする
        //  　@　@（※以下、キャストしたオブジェクトを"注文単位"と表記する。）
        EqTypeOrderUnit l_eqTypeOrderUnit = l_orderUnit;
        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        //  ２）　@共通のプロパティをセットする
        //  　@　@　@super.getUnitレスポンス()をコールする
        //  　@　@　@　@[getUnitレスポンス()に設定する引数]
        //  　@　@　@　@注文データ：　@１）で生成した注文単位
        WEB3EquityManualUnit l_equityManualUnit =
            super.getUnitResponse(l_eqTypeOrderUnit);

        //３）注文単位.発注条件 == "W指値"の場合
        //  　@執行条件を取得する
        //  　@　@　@拡張株式注文マネージャ.get執行条件（SONAR）()をコールする
        //  　@　@　@　@[get執行条件(SONAR)()に設定する引数]
        //  　@　@　@　@執行条件：　@注文単位.(W指値)執行条件
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        String l_strExecutionConditionTypeSonar = null;
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
            l_eqTypeOrderUnitRow.getOrderConditionType()))
        {
            l_strExecutionConditionTypeSonar = l_orderManager.getExecutionConditionTypeSonar(
                    l_eqTypeOrderUnitRow.getWLimitExecCondType());
        }

        //  ４）　@発注状況区分を取得する
        //  　@　@　@株式データアダプタ.getW指値発注状況区分()をコールする
        //  　@　@　@　@[getW指値発注状況区分()に設定する引数]
        //  　@　@　@　@注文単位：　@注文単位
        String l_strWLimitOrderStatusType =
            WEB3EquityDataAdapter.getWLimitOrderStatusType(l_eqTypeOrderUnit);

        //  ５）　@W指値注文手動発注エラーコードを取得する
        //  　@　@　@ルールエンジン連携データマネージャ.getW指値手動発注エラーコード()をコールする
        //  　@　@　@　@[getW指値手動発注エラーコード()に設定する引数]
        //  　@　@　@　@注文単位：　@注文単位
        String l_strWLimitManualOrderErrorCode =
            WEB3ToRlsCoopDataManager.getWLimitManualOrderErrorCode(l_eqTypeOrderUnit);

        //  ６）　@ルールエンジンからの通知データを取得する
        //  　@　@　@ルールエンジン連携データマネージャ.getルールエンジンからの通知データ()をコールする
        //  　@　@　@　@[getルールエンジンからの通知データ()に設定する引数]
        //  　@　@　@　@注文単位：　@注文単位
        //  　@　@　@　@条件注文種別：　@”W指値注文”
        //  　@　@　@　@銘柄タイプ：　@ProductTypeEnum.株式
        RlsConOrderHitNotifyParams l_hitNotifyParams =
            WEB3ToRlsCoopDataManager.getRLSConOrderHitNotifyData(
                l_eqTypeOrderUnit,
                WEB3TriggerOrderTypeDef.W_LlIMIT,
                ProductTypeEnum.EQUITY);

        //  ７）　@拡張アカウントマネージャ.get補助口座()をコールする
        //  　@　@　@　@[get補助口座()の引数]
        //  　@　@　@　@arg0：　@注文単位.getAccountId()
        //  　@　@　@　@arg1：　@注文単位.getSubAccountId()
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accountManager.getSubAccount(
                l_eqTypeOrderUnit.getAccountId(),
                l_eqTypeOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //  ８）手動発注手数料情報を取得する
        //  　@８－１）　@株式計算サービス.create手数料()をコールする
        //  　@　@　@　@[create手数料()の引数]
        //  　@　@　@　@注文単位：　@パラメータ.注文単位
        WEB3EquityBizLogicProvider l_eqBizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();

        EqTypeOrderUnitImpl l_orderUnitImpl =
            (EqTypeOrderUnitImpl)l_eqTypeOrderUnit;

        WEB3GentradeCommission l_commission =
            l_eqBizLogicProvider.createCommission(l_orderUnitImpl);

        //  　@８－２）　@this.get概算代金計算結果()をコールする
        //  　@　@　@　@[get概算代金計算結果()に設定する引数]
        //  　@　@　@　@注文単位：　@注文単位
        //  　@　@　@　@補助口座：　@get補助口座()の戻り値
        //  　@　@　@　@手数料：　@手数料オブジェクト
        WEB3EquityEstimatedPrice l_equityEstimatedPrice =
            this.getEstimatedPrice(l_eqTypeOrderUnit, l_subAccount, l_commission);

        //  　@８－３）　@this.create手動発注手数料情報()をコールする
        //  　@　@　@　@[create手動発注手数料情報()に設定する引数]
        //  　@　@　@　@補助口座：　@get補助口座()の戻り値
        //  　@　@　@　@手数料：　@手数料オブジェクト
        WEB3ManualCommissionInfoUnit l_commissionInfoUnit =
            this.createManualCommissionInfoUnit(l_subAccount, l_commission);

        //  ９）　@注文単位.注文カテゴリ=="返済注文"の場合のみ
        //  　@　@　@信用取引建株明細を取得する
        //  　@　@　@this.create建株明細byOrder()をコールする
        //  　@　@　@　@[create建株明細byOrder()に設定する引数]
        //  　@　@　@　@注文単位：　@注文単位
        WEB3MarginContractUnit[] l_marginContractUnits = null;
        if (OrderCategEnum.CLOSE_MARGIN.equals(l_eqTypeOrderUnit.getOrderCateg()))
        {
            l_marginContractUnits =
                this.createContractUnitByOrder(l_eqTypeOrderUnit);
        }

        //  １０）　@getUnitレスポンス()の戻り値（株式手動発注Unit）に
        //  　@　@　@W指値注文固有のプロパティをセットする
        //  　@条件注文種別：　@"W指値注文"をセット
        l_equityManualUnit.triggerOrderType = WEB3TriggerOrderTypeDef.W_LlIMIT;

        //(*1)注文単位.発注条件が"W指値"の場合のみセット
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
            l_eqTypeOrderUnitRow.getOrderConditionType()))
        {
            //  　@発注条件演算子：　@(*1)注文単位.発注条件演算子
            l_equityManualUnit.condOperator = l_eqTypeOrderUnitRow.getOrderCondOperator();

            //  　@発注条件単価：　@(*1)注文単位.逆指値基準値
            if (!l_eqTypeOrderUnitRow.getStopOrderPriceIsNull())
            {
                l_equityManualUnit.orderCondPrice = WEB3StringTypeUtility.formatNumber(
                    l_eqTypeOrderUnitRow.getStopOrderPrice());
            }

            //  　@W指値用注文単価区分：　@(*1)注文単位.（W指値）訂正指値が"0"の場合"成行"をセット 　@以外、"指値"をセット
            if (l_eqTypeOrderUnitRow.getWLimitPrice() == 0)
            {
                l_equityManualUnit.wLimitOrderPriceDiv =
                    WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
            }
            else
            {
                l_equityManualUnit.wLimitOrderPriceDiv =
                    WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;

                //  　@W指値用注文単価：　@(*1)注文単位.（W指値）訂正指値が"0"以外の場合、
                //注文単位.（W指値）訂正指値をセット
                l_equityManualUnit.wLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_eqTypeOrderUnitRow.getWLimitPrice());
            }

            //  　@W指値用執行条件：　@(*1)get執行条件（SONAR）()の戻り値
            l_equityManualUnit.wlimitExecCondType = l_strExecutionConditionTypeSonar;
        }

        //  　@発注状況区分：　@getW指値発注状況区分()の戻り値
        l_equityManualUnit.triggerOrderState = l_strWLimitOrderStatusType;

        //  　@概算受渡代金：　@概算代金計算結果.get概算受渡代金()の戻り値
        if (l_equityEstimatedPrice != null)
        {
            l_equityManualUnit.estimatedPrice = WEB3StringTypeUtility.formatNumber(
                l_equityEstimatedPrice.getEstimateDeliveryAmount());
        }

        //  　@手動発注エラーコード：　@getW指値手動発注エラーコード()の戻り値
        l_equityManualUnit.manualOrderErrorCode = l_strWLimitManualOrderErrorCode;

        //  　@(*2)getルールエンジンからの通知()の戻り値≠nullの場合セット
        if (l_hitNotifyParams != null)
        {
            //時価情報受信時間：　@(*2)ルールエンジンからの通知Params.tickヒットタイムスタンプ
            l_equityManualUnit.currentPriceInfoAcceptTime = l_hitNotifyParams.getHitTickTimestamp();

            //  　@トリガー起動時間：　@(*2)ルールエンジンからの通知Params.ルールエンジンファ@イアタイムスタンプ
            l_equityManualUnit.triggerStartTime = l_hitNotifyParams.getRlsHitTimestamp();

            //  　@発注完了時間：　@(*2)ルールエンジンからの通知Params.発注完了タイムスタンプ
            l_equityManualUnit.orderCompleteTime = l_hitNotifyParams.getOrderSubmitTimestamp();
        }

        //  　@手動発注手数料情報：　@create手動発注手数料情報()の戻り値
        l_equityManualUnit.commissionInfo = l_commissionInfoUnit;
        //  　@信用取引建株明細：　@create建株明細byOrder()の戻り値
        l_equityManualUnit.contractUnits = l_marginContractUnits;

        //  １１）　@プロパティをセットした株式手動発注Unitを返す
        log.exiting(STR_METHOD_NAME);
        return l_equityManualUnit;
    }

    /**
     * (get概算代金計算結果)<BR>
     * ストップ注文の概算代金を取得する。<BR>
     * <BR>
     * １）　@パラメータ.注文単位.getDataSourceObject()をコールする。 <BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.is売注文()をコールする。 <BR>
     * <BR>
     * 　@　@　@[is売り注文()の引数] <BR>
     * 　@　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ３）　@概算代金を取得する。 <BR>
     * <BR>
     * 　@３－１）　@注文単位.注文カテゴリが"現物注文"の場合 <BR>
     * 　@　@　@拡張株式注文マネージャ.calc概算受渡代金()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[calc概算受渡代金()の引数] <BR>
     * 　@　@　@　@手数料：　@パラメータ.手数料 <BR>
     * 　@　@　@　@指値：　@注文単位.（W指値）訂正指値 <BR>
     * 　@　@　@　@（W指値)訂正指値： 注文単位.（W指値）訂正指値 <BR>
     * 　@　@　@　@逆指値基準値：　@注文単位.逆指値基準値 <BR>
     * 　@　@　@　@執行条件：　@注文単位.（W指値）執行条件 <BR>
     * 　@　@　@　@（W指値）執行条件：　@注文単位.（W指値）執行条件 <BR>
     * 　@　@　@　@値段条件：　@注文単位.値段条件 <BR>
     * 　@　@　@　@発注条件：　@注文単位.発注条件 <BR>
     * 　@　@　@　@確認時取得時価：　@0（固定） <BR>
     * 　@　@　@　@isストップ注文有効：　@true（固定）（*） <BR>
     * 　@　@　@　@補助口座：　@get補助口座()の戻り値 <BR>
     * 　@　@　@　@取引銘柄：　@注文単位.getTradedProduct()の戻り値 <BR>
     * 　@　@　@　@株数：　@注文単位.注文数量 <BR>
     * 　@　@　@　@is売注文：　@is売注文()の戻り値 <BR>
     * 　@　@　@　@約定数量：　@注文単位.約定数量 <BR>
     * 　@　@　@　@合計約定金額：　@注文単位.合計約定金額 <BR>
     * 　@　@　@　@isSkip金額チェック：　@false（固定） <BR>
     * <BR>
     * 　@３－２）　@注文単位.注文カテゴリが"新規建注文"の場合 <BR>
     * 　@　@　@拡張株式注文マネージャ.calc注文時建代金()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[calc注文時建代金()の引数]  <BR>
     * 　@　@　@　@手数料：　@パラメータ.手数料 <BR>
     * 　@　@　@　@指値：　@注文単位.（W指値）訂正指値 <BR>
     * 　@　@　@　@（W指値）訂正指値：　@注文単位.（W指値）訂正指値  <BR>
     * 　@　@　@　@逆指値基準値：　@注文単位.逆指値基準値  <BR>
     * 　@　@　@　@執行条件：　@注文単位.（W指値）執行条件 <BR>
     * 　@　@　@　@（W指値）執行条件：　@注文単位.（W指値）執行条件 <BR>
     * 　@　@　@　@値段条件：　@注文単位.値段条件 <BR>
     * 　@　@　@　@発注条件：　@注文単位.発注条件  <BR>
     * 　@　@　@　@確認時取得時価：　@0（固定） <BR>
     * 　@　@　@　@isストップ注文有効：　@true（固定） （*） <BR>
     * 　@　@　@　@is売建：　@is売注文()の戻り値 <BR>
     * 　@　@　@　@補助口座：　@get補助口座()の戻り値 <BR>
     * 　@　@　@　@取引銘柄：　@注文単位.getTradedProduct()の戻り値 <BR>
     * 　@　@　@　@株数：　@注文単位.注文数量 <BR>
     * 　@　@　@　@約定数量：　@注文単位.約定数量 <BR>
     * 　@　@　@　@合計約定金額：　@注文単位.合計約定金額  <BR>
     * 　@　@　@　@isSkip金額チェック：　@false（固定）  <BR>
     * <BR>
     * 　@３－３）　@注文単位.注文カテゴリが"返済注文"の場合 <BR>
     * <BR>
     * 　@　@３－３－１）　@株式ポジションマネージャ.create決済建株エントリ()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@　@　@[create決済建株エントリ()の引数]  <BR>
     * 　@　@　@　@　@　@注文単位ID：　@注文単位.注文単位ID <BR>
     * <BR>
     * 　@　@３－３－２）　@パラメータ.手数料.setIs指値()をコールする。<BR>
     * <BR>
     * 　@　@　@　@　@　@[setIs指値()の引数]<BR>
     * 　@　@　@　@　@　@is指値：　@注文単位.（W指値）訂正指値≠"0"の場合のみ、true（指値）をセット<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@以外、false（成行）をセット<BR>
     * <BR>
     * 　@　@３－３－３）　@拡張株式注文マネージャ.calc概算決済損益代金()をコールする。  <BR>
     * <BR>
     * 　@　@　@　@　@　@[calc概算決済損益代金()の引数]  <BR>
     * 　@　@　@　@　@　@手数料：　@パラメータ.手数料 <BR>
     * 　@　@　@　@　@　@指値：　@注文単位.（W指値）訂正指値 <BR>
     * 　@　@　@　@　@　@補助口座：　@get補助口座()の戻り値 <BR>
     * 　@　@　@　@　@　@取引銘柄：　@注文単位..getTradedProduct()の戻り値 <BR>
     * 　@　@　@　@　@　@決済建株エントリ：　@create決済建株エントリ()の戻り値 <BR>
     * 　@　@　@　@　@　@数量：　@パラメータ.注文単位.注文数量 <BR>
     * 　@　@　@　@　@　@注文単位：　@パラメータ.注文単位 <BR>
     * 　@　@　@　@　@　@今回約定数量：　@0（固定）  <BR>
     * 　@　@　@　@　@　@今回約定単価：　@0（固定）  <BR>
     * 　@　@　@　@　@　@isSkip金額チェック：　@false（固定） <BR>
     * <BR>
     * ４）　@概算代金計算結果オブジェクトを返却する。 <BR>
     * <BR>
     * <BR>
     * （*）ストップ注文有効時の概算代金を計算する。 <BR>
     * <BR>
     * @@param l_orderUnit - (注文データ)
     * @@param l_subAccount - (補助口座)
     * @@param l_commission - (手数料)
     * @@return WEB3EquityEstimatedPrice
     * @@throws WEB3BaseException
     */
    protected WEB3EquityEstimatedPrice getEstimatedPrice(
        EqTypeOrderUnit l_orderUnit,
        SubAccount l_subAccount,
        WEB3GentradeCommission l_commission) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getEstimatedPrice(EqTypeOrderUnit, SubAccount, WEB3GentradeCommission)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）　@パラメータ.注文単位.getDataSourceObject()をコールする
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //２）　@拡張株式注文マネージャ.is売注文()をコールする
        //　@　@　@[is売り注文()の引数]
        //　@　@　@注文単位：　@パラメータ.注文単位
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnSellOrder = l_orderManager.isSellOrder(l_orderUnit);

        //３）　@概算代金を取得する
        //　@３－１）　@注文単位.注文カテゴリが"現物注文"の場合
        if (OrderCategEnum.ASSET.equals(l_orderUnitRow.getOrderCateg()))
        {
            //　@　@　@拡張株式注文マネージャ.calc概算受渡代金()をコールする
            //　@　@　@　@[calc概算受渡代金()の引数]
            //　@　@　@　@手数料：　@パラメータ.手数料
            //　@　@　@　@指値：　@注文単位.（W指値）訂正指値
            //　@　@　@　@（W指値)訂正指値：注文単位.（W指値）訂正指値
            //　@　@　@　@逆指値基準値：　@注文単位.逆指値基準値
            //　@　@　@　@執行条件：　@注文単位.（W指値）執行条件
            //　@　@　@　@（W指値）執行条件：　@注文単位.（W指値）執行条件
            //　@　@　@　@値段条件：　@注文単位.値段条件
            //　@　@　@　@発注条件：　@注文単位.発注条件
            //　@　@　@　@確認時取得時価：　@0（固定）
            //　@　@　@　@isストップ注文有効：　@true（固定）（*）
            //　@　@　@　@補助口座：　@get補助口座()の戻り値
            //　@　@　@　@取引銘柄：　@注文単位.getTradedProduct()の戻り値
            //　@　@　@　@株数：　@注文単位.注文数量
            //　@　@　@　@is売注文：　@is売注文()の戻り値
            //　@　@　@　@約定数量：　@注文単位.約定数量
            //　@　@　@　@合計約定金額：　@注文単位.合計約定金額
            //　@　@　@　@isSkip金額チェック：　@false（固定）
            WEB3EquityTradedProduct l_tradedProduct = null;
            if (l_orderUnit.getTradedProduct() != null)
            {
                l_tradedProduct = (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
            }
            WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
                l_orderManager.calcEstimateDeliveryAmount(
                    l_commission,
                    l_orderUnitRow.getWLimitPrice(),
                    l_orderUnitRow.getWLimitPrice(),
                    l_orderUnitRow.getStopOrderPrice(),
                    l_orderUnitRow.getWLimitExecCondType(),
                    l_orderUnitRow.getWLimitExecCondType(),
                    l_orderUnitRow.getPriceConditionType(),
                    l_orderUnitRow.getOrderConditionType(),
                    "0",
                    true,
                    l_subAccount,
                    l_tradedProduct,
                    l_orderUnit.getQuantity(),
                    l_blnSellOrder,
                    l_orderUnit.getExecutedQuantity(),
                    l_orderUnit.getExecutedAmount(),
                    false);

            //概算代金計算結果オブジェクトを返却する
            log.exiting(STR_METHOD_NAME);
            return l_estimatedDeliveryPrice;
        }
        //　@３－２）　@注文単位.注文カテゴリが"新規建注文"の場合
        else if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
        {
            //　@　@　@拡張株式注文マネージャ.calc注文時建代金()をコールする
            //　@　@　@　@[calc注文時建代金()の引数]
            //　@　@　@　@手数料：　@パラメータ.手数料
            //　@　@　@　@指値：　@注文単位.（W指値）訂正指値
            //　@　@　@　@（W指値）訂正指値：　@注文単位.（W指値）訂正指値
            //　@　@　@　@逆指値基準値：　@注文単位.逆指値基準値
            //　@　@　@　@執行条件：　@注文単位.（W指値）執行条件
            //　@　@　@　@（W指値）執行条件：　@注文単位.（W指値）執行条件
            //　@　@　@　@値段条件：　@注文単位.値段条件
            //　@　@　@　@発注条件：　@注文単位.発注条件
            //　@　@　@　@確認時取得時価：　@0（固定）
            //　@　@　@　@isストップ注文有効：　@true（固定）（*）
            //　@　@　@　@is売建：　@is売注文()の戻り値
            //　@　@　@　@補助口座：　@get補助口座()の戻り値
            //　@　@　@　@取引銘柄：　@注文単位.getTradedProduct()の戻り値
            //　@　@　@　@株数：　@注文単位.注文数量
            //　@　@　@　@約定数量：　@注文単位.約定数量
            //　@　@　@　@合計約定金額：　@注文単位.合計約定金額
            //　@　@　@　@isSkip金額チェック：　@false（固定）
            EqTypeTradedProduct l_tradedProduct = null;
            if (l_orderUnit.getTradedProduct() != null)
            {
                l_tradedProduct = (EqTypeTradedProduct)l_orderUnit.getTradedProduct();
            }
            WEB3EquityEstimatedContractPrice l_equityEstimatedContractPricer =
                l_orderManager.calcContractAmountAtOrder(
                    l_commission,
                    l_orderUnitRow.getWLimitPrice(),
                    l_orderUnitRow.getWLimitPrice(),
                    l_orderUnitRow.getStopOrderPrice(),
                    l_orderUnitRow.getWLimitExecCondType(),
                    l_orderUnitRow.getWLimitExecCondType(),
                    l_orderUnitRow.getPriceConditionType(),
                    l_orderUnitRow.getOrderConditionType(),
                    "0",
                    true,
                    l_blnSellOrder,
                    l_subAccount,
                    l_tradedProduct,
                    l_orderUnit.getQuantity(),
                    l_orderUnit.getExecutedQuantity(),
                    l_orderUnit.getExecutedAmount(),
                    false);

            //概算代金計算結果オブジェクトを返却する
            log.exiting(STR_METHOD_NAME);
            return l_equityEstimatedContractPricer;
        }
        //　@３－３）　@注文単位.注文カテゴリが"返済注文"の場合
        else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
        {
            //３－３－１）　@株式ポジションマネージャ.create決済建株エントリ()をコールする
            //　@　@　@　@　@　@[create決済建株エントリ()の引数]
            //　@　@　@　@　@　@注文単位ID：　@注文単位.注文単位ID
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            EqTypeSettleContractOrderEntry[] l_settleContractOrderEntry =
                l_positionManager.createCloseMarginContractEntry(l_orderUnit.getOrderUnitId());

            //３－３－２）　@パラメータ.手数料.setIs指値()をコールする。
            //　@　@　@[setIs指値()の引数]
            //　@　@　@is指値：　@注文単位.（W指値）訂正指値≠"0"の場合のみ、true（指値）をセット
            //　@　@　@　@　@　@　@　@　@以外、false（成行）をセット
            boolean l_blnIsLimitPrice = false;
            if (l_orderUnitRow.getWLimitPrice() != 0)
            {
                l_blnIsLimitPrice = true;
            }
            l_commission.setIsLimitPrice(l_blnIsLimitPrice);

            //　@　@３－３－２）　@拡張株式注文マネージャ.calc概算決済損益代金()をコールする
            //　@　@　@　@　@　@[calc概算決済損益代金()の引数]
            //　@　@　@　@　@　@手数料：　@パラメータ.手数料
            //　@　@　@　@　@　@指値：　@注文単位.（W指値）訂正指値
            //　@　@　@　@　@　@補助口座：　@get補助口座()の戻り値
            //　@　@　@　@　@　@取引銘柄：　@注文単位..getTradedProduct()の戻り値
            //　@　@　@　@　@　@決済建株エントリ：　@create決済建株エントリ()の戻り値
            //　@　@　@　@　@　@数量：　@パラメータ.注文単位.注文数量
            //　@　@　@　@　@　@注文単位：　@パラメータ.注文単位
            //　@　@　@　@　@　@今回約定数量：　@0（固定）
            //　@　@　@　@　@　@今回約定単価：　@0（固定）
            //　@　@　@　@　@　@isSkip金額チェック：　@false（固定）
            WEB3GentradeSubAccount l_genSubAccount = null;
            if (l_subAccount != null)
            {
                l_genSubAccount = (WEB3GentradeSubAccount)l_subAccount;
            }

            WEB3EquityTradedProduct l_equityTradedProduct = null;
            if (l_orderUnit.getTradedProduct() != null)
            {
                l_equityTradedProduct = (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
            }
            WEB3EquityRealizedProfitAndLossPrice l_realizedProfitAndLossPrice =
                l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    l_orderUnitRow.getWLimitPrice(),
                    l_genSubAccount,
                    l_equityTradedProduct,
                    l_settleContractOrderEntry,
                    l_orderUnit.getQuantity(),
                    l_orderUnit,
                    0,
                    0,
                    false);

            //概算代金計算結果オブジェクトを返却する
            log.exiting(STR_METHOD_NAME);
            return l_realizedProfitAndLossPrice;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (create手動発注手数料情報)<BR>
     * 手動発注手数料情報にプロパティを設定する。<BR>
     * <BR>
     * １）　@委託手数料を取得する。<BR>
     * 　@　@株式計算サービス.calc委託手数料()をコールする。<BR>
     * 　@　@（※以下、取得した手数料オブジェクトを"手数料"と表記する）<BR>
     * <BR>
     * 　@　@[calc委託手数料()の引数]<BR>
     * 　@　@手数料：　@パラメータ.手数料 <BR>
     * 　@　@補助口座：　@パラメータ.補助口座 <BR>
     * <BR>
     * ２）　@消費税を取得する。 <BR>
     * 　@　@株式計算サービス.calc消費税()をコールする。<BR>
     * <BR>
     * 　@　@[calc消費税()の引数]<BR>
     * 　@　@金額：　@手数料.手数料金額 <BR>
     * 　@　@基準日：　@手数料.発注日 <BR>
     * 　@　@補助口座：　@パラメータ.補助口座 <BR>
     * <BR>
     * ３）　@手動発注手数料情報に以下プロパティにセットする。<BR>
     * <BR>
     * 　@　@手数料コース：　@手数料.get手数料コース()の戻り値<BR>
     * 　@　@手数料：　@手数料.get手数料金額()の戻り値 <BR>
     * 　@　@手数料消費税：　@calc消費税()の戻り値 <BR>
     * <BR>
     * ４）　@手動発注手数料情報を返却する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_commission - (手数料)
     * @@return WEB3ManualCommissionInfoUnit
     * @@throws WEB3BaseException
     */
    protected WEB3ManualCommissionInfoUnit createManualCommissionInfoUnit(
        SubAccount l_subAccount,
        WEB3GentradeCommission l_commission
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createManualCommissionInfoUnit(SubAccount, WEB3GentradeCommission)";
        log.entering(STR_METHOD_NAME);

        if (l_commission == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //    １）　@委託手数料を取得する
        //  　@　@株式計算サービス.calc委託手数料()をコールする
        //  　@　@（※以下、取得した手数料オブジェクトを"手数料"と表記する）
        //  　@　@[calc委託手数料()の引数]
        //  　@　@手数料：　@パラメータ.手数料
        //  　@　@補助口座：　@パラメータ.補助口座
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_eqBizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        l_eqBizLogicProvider.calcCommission(l_commission, l_subAccount);

        //  ２）　@消費税を取得する
        //  　@　@株式計算サービス.calc消費税()をコールする
        //  　@　@[calc消費税()の引数]
        //  　@　@金額：　@手数料.手数料金額
        //  　@　@基準日：　@手数料.発注日
        //  　@　@補助口座：　@パラメータ.補助口座
        double l_dblSalesTax = l_eqBizLogicProvider.calcSalesTax(
            l_commission.getCommission(),
            l_commission.getOrderBizDate(),
            l_subAccount);

        //  ３）　@手動発注手数料情報に以下プロパティにセットする
        //  　@　@手数料コース：　@手数料.get手数料コース()の戻り値
        //  　@　@手数料：　@手数料.get手数料金額()の戻り値
        //  　@　@手数料消費税：　@calc消費税()の戻り値
        WEB3ManualCommissionInfoUnit l_manualCommissionInfoUnit =
            new WEB3ManualCommissionInfoUnit();
        l_manualCommissionInfoUnit.commissionCourse =
            l_commission.getCommissionCourseDiv();

        l_manualCommissionInfoUnit.commission =
            WEB3StringTypeUtility.formatNumber(l_commission.getCommission());

        l_manualCommissionInfoUnit.commissionConsumptionTax =
            WEB3StringTypeUtility.formatNumber(l_dblSalesTax);

        //  ４）　@手動発注手数料情報を返却する
        log.exiting(STR_METHOD_NAME);
        return l_manualCommissionInfoUnit;
    }

    /**
     * ルールエンジンからの通知テーブルへのINSERTメソッドをコールする。<BR>
     * <BR>
     * １）　@補助口座を取得する。<BR>
     * 　@　@　@拡張アカウントマネージャー.get補助口座()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[getSubAccount()に指定する引数]<BR>
     * 　@　@　@　@顧客ID：　@注文データ.getAccountId()の戻り値<BR>
     * 　@　@　@　@補助口座ID：　@注文データ.getSubAccountId()の戻り値 <BR>
     * <BR>
     * ２）　@ルールエンジンからの通知テーブルへINSERTする。<BR>
     * 　@　@　@WEB3RlsRequestSenderServiceImpl.sendManualSubmitConOrder()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[sendManualSubmitConOrder()に指定する引数]  <BR>
     * 　@　@　@　@発注者ログインID：　@パラメータ.発注者ログインID  <BR>
     * 　@　@　@　@通知経路：　@パラメータ.通知経路  <BR>
     * 　@　@　@　@補助口座：　@getSubAccount()の戻り値  <BR>
     * 　@　@　@　@条件付注文タイプ：　@条件注文種別."W指値注文"  <BR>
     * 　@　@　@　@注文の銘柄タイプ：　@銘柄タイプ."株式"  <BR>
     * 　@　@　@　@注文ID：　@注文データ.注文ID  <BR>
     * 　@　@　@　@親注文の銘柄タイプ：　@null  <BR>
     * 　@　@　@　@親注文の注文ID：　@null  <BR>
     * 　@　@　@　@発注順番：　@0<BR>
     * <BR>
     * @@param l_orderUnit - (注文データ)
     * @@param l_submitterLoginId - (発注者ログインID)
     * @@param l_strNotifyType - (通知経路)
     * @@throws WEB3BaseException
     */
    protected void sendRLSRequest(
        EqTypeOrderUnit l_orderUnit,
        Long l_submitterLoginId,
        String l_strNotifyType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "sendRLSRequest(EqTypeOrderUnit, Long, String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１）　@補助口座を取得する
        //　@　@　@拡張アカウントマネージャー.get補助口座()をコールする
        //　@　@　@　@[getSubAccount()に指定する引数]
        //        顧客ID：　@注文データ.getAccountId()の戻り値
        //　@　@　@　@補助口座ID：　@注文データ.getSubAccountId()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        SubAccount l_subAccount = null;

        try
        {
            l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("補助口座テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）　@ルールエンジンからの通知テーブルへINSERTする
        //　@　@　@WEB3RlsRequestSenderServiceImpl.sendManualSubmitConOrder()をコールする
        //　@　@　@　@[sendManualSubmitConOrder()に指定する引数]
        //　@　@　@　@発注者ログインID：　@パラメータ.発注者ログインID
        //　@　@　@　@通知経路：　@パラメータ.通知経路
        //　@　@　@　@補助口座：　@getSubAccount()の戻り値
        //　@　@　@　@条件付注文タイプ：　@条件注文種別."W指値注文"
        //　@　@　@　@注文の銘柄タイプ：　@銘柄タイプ."株式"
        //　@　@　@　@注文ID：　@注文データ.注文ID
        //　@　@　@　@親注文の銘柄タイプ：　@null
        //　@　@　@　@親注文の注文ID：　@null
        //　@　@　@　@発注順番：　@0
        WEB3RlsRequestSenderService l_requestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(WEB3RlsRequestSenderService.class);
        l_requestSenderService.sendManualSubmitConOrder(
            l_submitterLoginId,
            l_strNotifyType,
            l_subAccount,
            Integer.parseInt(WEB3TriggerOrderTypeDef.W_LlIMIT),
            ProductTypeEnum.EQUITY,
            new Long(l_orderUnit.getOrderId()),
            null,
            null,
            0);

        log.exiting(STR_METHOD_NAME);
    }
}
@
