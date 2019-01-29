head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券注文更新インタセプタクラス(WEB3BondDomesticOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.231,モデルNo.243
Revision History : 2007/08/28 柴双紅 (中訊) ＤＢ更新仕様No.039
*/
package webbroker3.bd;

import java.sql.Timestamp;

import webbroker3.bd.define.WEB3BondAutoExecDivListDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3HostReflectDivDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.common.define.WEB3LockStatusDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

/**
 * (国内債券注文更新インタセプタクラス)<BR>
 * 国内債券注文更新インタセプタクラス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticOrderUpdateInterceptor extends WEB3BondOrderUpdateInterceptor
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticOrderUpdateInterceptor.class);

    /**
     * (国内債券注文更新インタセプタクラス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44D84FAD015E
     */
    public WEB3BondDomesticOrderUpdateInterceptor()
    {

    }

    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * <BR>
     * 注文単位テーブル更新<BR>
     * 　@注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * 項目設定内容は、DB更新仕様<BR>
     * 「国内債券応募_債券注文単位テーブルDB更新仕様.xls」<BR>
     * <BR>
     * 参照。<BR>
     * @@param l_persistenceType - (OrderManagerPersistenceType)<BR>
     * OrderManagerPersistenceType<BR>
     * @@param l_context - (OrderManagerPersistenceContext)<BR>
     * OrderManagerPersistenceContext<BR>
     * @@param l_params - (BondOrderUnitParams)<BR>
     * BondOrderUnitParams<BR>
     * @@return BondOrderUnitParams
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params)
    {
        final String STR_METHOD_NAME = " mutate(OrderManagerPersistenceType,"
            + " OrderManagerPersistenceContext"
            + " BondOrderUnitParams";
        log.entering(STR_METHOD_NAME);

        if (l_params == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータNull出来ない。");
        }

        //注文種別: 403:国内債券応募注文
        l_params.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);

        //取引,35:募集取引
        l_params.setDealType(WEB3DealTypeDef.RECRUIT_TRADING);

        //受渡日,債券約定日情報.get受渡日（）
        l_params.setDeliveryDate(this.getBondExecuteDateInfo().getDeliveryDate());

        //現地受渡日,null
        l_params.setForeignDeliveryDate(null);

        //注文ロック区分  lock_status
        //1：解除中
        l_params.setLockStatus(WEB3LockStatusDef.RELEASING);

        //注文約定区分  order_exec_status
        //0：未約定
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.UNEXECUTED);

        //発注日       biz_date
        //債券約定日情報.get発注日（）
        l_params.setBizDate(
            WEB3DateUtility.formatDate(
                this.getBondExecuteDateInfo().getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //現地発注日   foreign_biz_date
        //債券約定日情報.get現地発注日（）
        l_params.setForeignBizDate(null);

        //初回注文の注文チャネル   order_chanel
        //セッションより取得した注文チャネル
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

        l_params.setOrderChanel(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));

        //受注日時   received_date_time
        //サーバ側でサービスが起動された時間（計算式書（共通）(*2) 処理日付　@を参照）
        //(*2) 処理日付
        //プロセス開始時点の日付・時間をスレッドに保存し、各処理にて利用する。
        //（ThreadLocalSystemAttributesRegistry#attributes("xblocks.gtl.attributes.system_timestamp")）

        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_params.setReceivedDateTime(l_tsOrderAcceptDate);

        //扱者コード（SONAR）   sonar_trader_code
        //顧客.扱者コード（SONAR）
        // * SONARで管理している顧客の扱者）
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        MainAccount l_mainAccount = null;

        try
        {
            l_mainAccount =
                l_accountManager.getMainAccount(l_params.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        l_params.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());

        //注文単価   price
        //債券受渡代金計算結果.get単価（）
        if (this.getBondEstimatedPriceCalcResult().getPrice() != null)
        {
            l_params.setPrice(this.getBondEstimatedPriceCalcResult().getPrice().doubleValue());
        }
        else
        {
            l_params.setPrice(null);
        }

        //識別コード    order_request_number
        //注文識別コード採番サービス.get新規識別コード( )
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);

        try
        {
            String l_strNewNumber =
                l_hostReqOrderNumberManageService.getNewNumber(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    ProductTypeEnum.BOND);
            l_params.setOrderRequestNumber(l_strNewNumber);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("識別コードを取得する: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //債券タイプ   bond_type
        //債券新規注文内容.getProductCode()に該当する債券銘柄.get債券タイプ（）
        ProductManager l_productManager =
            l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();

        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct = (WEB3BondProduct)l_productManager.getProduct(l_params.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        BondProductRow l_bondProductRow =
            (BondProductRow)l_bondProduct.getDataSourceObject();

        l_params.setBondType(l_bondProductRow.getBondType());

        //通貨コード     currency_code
        //債券新規注文内容.getProductCode()に該当する債券銘柄.get通貨コード（）
        l_params.setCurrencyCode(l_bondProductRow.getCurrencyCode());

        //決済区分      settlement_div
        //債券新規注文内容.決済区分
        l_params.setSettlementDiv(this.getBondNewOrderSpec().getSettlementDiv());

        //自動約定区分        auto_exec_div
        //0：非自動約定
        l_params.setAutoExecDiv(WEB3BondAutoExecDivListDef.ZERO);

        //約定単価      executed_price
        //null
        l_params.setExecutedPrice(null);

        //基準為替レート       base_fx_rate
        l_params.setBaseFxRate(null);

        //約定為替レート    exec_fx_rate
        //null
        l_params.setExecFxRate(null);

        //売買代金（円貨）    trading_price
        //債券受渡代金計算結果.get売買代金（円貨）（）
        if (this.getBondEstimatedPriceCalcResult().getTradingPrice() != null)
        {
            l_params.setTradingPrice(
                this.getBondEstimatedPriceCalcResult().getTradingPrice().doubleValue());
        }
        else
        {
            l_params.setTradingPrice(null);
        }

        //売買代金（外貨）    foreign_trading_price null
        l_params.setForeignTradingPrice(null);

        //経過利子（円貨）    accrued_interest
        //債券受渡代金計算結果.get経過利子（円貨）（）
        if (this.getBondEstimatedPriceCalcResult().getAccruedInterest() != null)
        {
            l_params.setAccruedInterest(
                this.getBondEstimatedPriceCalcResult().getAccruedInterest().doubleValue());
        }
        else
        {
            l_params.setAccruedInterest(null);
        }

        //経過利子（外貨）    foreign_accrued_interest null
        l_params.setForeignAccruedInterest(null);

        //受渡代金（円貨）    estimated_price
        //債券受渡代金計算結果.get受渡代金（円貨）（）
        if (this.getBondEstimatedPriceCalcResult().getEstimatedPrice() != null)
        {
            l_params.setEstimatedPrice(
                this.getBondEstimatedPriceCalcResult().getEstimatedPrice().doubleValue());
        }
        else
        {
            l_params.setEstimatedPrice(null);
        }

        //受渡代金（外貨）    foreign_estimated_price null
        l_params.setForeignEstimatedPrice(null);

        //中途換金調整額 adjustment_before_maturity
        //null
        l_params.setAdjustmentBeforeMaturity(null);

        //経過日数      elapsed_days null
        l_params.setElapsedDays(null);


        //基準日数      calc_base_days null
        l_params.setCalcBaseDays(null);

        //約定日        exec_date
        //債券約定日情報.get約定日（）
        l_params.setExecDate(this.getBondExecuteDateInfo().getExecuteDate());

        //現地約定日     foreign_exec_date null
        l_params.setForeignExecDate(null);

        //入金日 payment_date
        //債券約定日情報.get入金日（）
        l_params.setPaymentDate(this.getBondExecuteDateInfo().getPaymentDate());

        //カストディアンコード    custodian_code null
        l_params.setCustodianCode(null);

        //注文経路区分        order_root_div
        //セッションより取得した注文経路区分
        l_params.setOrderRootDiv(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //host送信区分      host_send_div
        //0：未送信
        l_params.setHostSendDiv(WEB3HostSendDivDef.UNSEND);

        //管理者コード        administrator_code
        //null
        l_params.setAdministratorCode(null);

        //注文エラー理由コード    error_reason_code
        //null
        l_params.setErrorReasonCode(null);

        //約定分host反映区分 exec_host_reflect_div
        //0：未反映
        l_params.setExecHostReflectDiv(WEB3HostReflectDivDef.NOT_REFLECT);

        //取消分host反映区分 cancel_host_reflect_div
        //0：未反映
        l_params.setCancelHostReflectDiv(WEB3HostReflectDivDef.NOT_REFLECT);

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }

    /**
     * (（注文履歴）更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * １）　@注文単位オブジェクト取得<BR>
     * <BR>
     * 引数の注文履歴Params.注文ID、注文単位ＩＤに該当する注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@拡張項目セット<BR>
     * 　@パラメータ.注文履歴Paramsの拡張項目に値をセットし、返却する。<BR>
     * 　@項目設定内容は、<BR>
     * 　@「国内債券応募_債券注文履歴テーブルDB更新仕様.xls」<BR>
     * @@param l_persistenceType - (OrderManagerPersistenceType)<BR>
     * OrderManagerPersistenceType<BR>
     * @@param l_context - (OrderManagerPersistenceContext)<BR>
     * OrderManagerPersistenceContext<BR>
     * @@param l_params - (BondOrderActionParams)<BR>
     * BondOrderActionParams<BR>
     * @@return BondOrderActionParams
     */
    public BondOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderActionParams l_params)
    {
        final String STR_METHOD_NAME = " mutate(OrderManagerPersistenceType,"
            + " OrderManagerPersistenceContext"
            + " BondOrderActionParams";
        log.entering(STR_METHOD_NAME);

        if (l_params == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータNull出来ない。");
        }

        //拡張項目セット
        long l_lngOrderUnitId = l_params.getOrderUnitId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondOrderManager l_orderManager = (WEB3BondOrderManager)l_tradingMod.getOrderManager();
        BondOrderUnit l_bondOrderUnit = null;

        //注文単位オブジェクト取得
        l_bondOrderUnit = (BondOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);

        BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();

        //債券注文単位テーブル.取引者ID
        if (!l_bondOrderUnitRow.getTraderIdIsNull())
        {
            l_params.setTraderId(l_bondOrderUnitRow.getTraderId());
        }
        else
        {
            l_params.setTraderId(null);
        }

        //債券注文単位テーブル.取引
        l_params.setDealType(l_bondOrderUnitRow.getDealType());

        //債券注文単位テーブル.注文単価
        if (!l_bondOrderUnitRow.getPriceIsNull())
        {
            l_params.setPrice(l_bondOrderUnitRow.getPrice());
        }
        else
        {
            l_params.setPrice(null);
        }

        //債券注文単位テーブル.指値
        if (!l_bondOrderUnitRow.getLimitPriceIsNull())
        {
            l_params.setLimitPrice(l_bondOrderUnitRow.getLimitPrice());
        }
        else
        {
            l_params.setLimitPrice(null);
        }

        //債券注文単位テーブル.注文約定区分
        l_params.setOrderExecStatus(l_bondOrderUnitRow.getOrderExecStatus());

        //債券注文単位テーブル.約定日
        l_params.setExecDate(l_bondOrderUnitRow.getExecDate());

        //債券注文単位テーブル.現地約定日
        l_params.setForeignExecDate(l_bondOrderUnitRow.getForeignExecDate());

        //債券注文単位テーブル.受渡日
        l_params.setDeliveryDate(l_bondOrderUnitRow.getDeliveryDate());

        //債券注文単位テーブル.現地受渡日
        l_params.setForeignDeliveryDate(l_bondOrderUnitRow.getForeignDeliveryDate());

        //債券注文単位テーブル.入金日
        l_params.setPaymentDate(l_bondOrderUnitRow.getPaymentDate());

        //債券注文単位テーブル.基準為替レート
        if (!l_bondOrderUnitRow.getBaseFxRateIsNull())
        {
            l_params.setBaseFxRate(l_bondOrderUnitRow.getBaseFxRate());
        }
        else
        {
            l_params.setBaseFxRate(null);
        }

        //債券注文単位テーブル.約定為替レート
        if (!l_bondOrderUnitRow.getExecFxRateIsNull())
        {
            l_params.setExecFxRate(l_bondOrderUnitRow.getExecFxRate());
        }
        else
        {
            l_params.setExecFxRate(null);
        }

        //債券注文単位テーブル.売買代金（円貨）
        if (!l_bondOrderUnitRow.getTradingPriceIsNull())
        {
            l_params.setTradingPrice(l_bondOrderUnitRow.getTradingPrice());
        }
        else
        {
            l_params.setTradingPrice(null);
        }

        //債券注文単位テーブル.売買代金（外貨）
        if (!l_bondOrderUnitRow.getForeignTradingPriceIsNull())
        {
            l_params.setForeignTradingPrice(l_bondOrderUnitRow.getForeignTradingPrice());
        }
        else
        {
            l_params.setForeignTradingPrice(null);
        }

        //債券注文単位テーブル.経過利子（円貨）
        if (!l_bondOrderUnitRow.getAccruedInterestIsNull())
        {
            l_params.setAccruedInterest(l_bondOrderUnitRow.getAccruedInterest());
        }
        else
        {
            l_params.setAccruedInterest(null);
        }

        //債券注文単位テーブル.経過利子（外貨）
        if (!l_bondOrderUnitRow.getForeignAccruedInterestIsNull())
        {
            l_params.setForeignAccruedInterest(l_bondOrderUnitRow.getForeignAccruedInterest());
        }
        else
        {
            l_params.setForeignAccruedInterest(null);
        }

        //債券注文単位テーブル.受渡代金（円貨）
        if (!l_bondOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_params.setEstimatedPrice(l_bondOrderUnitRow.getEstimatedPrice());
        }
        else
        {
            l_params.setEstimatedPrice(null);
        }
        //債券注文単位テーブル.受渡代金（外貨）
        if (!l_bondOrderUnitRow.getForeignEstimatedPriceIsNull())
        {
            l_params.setForeignEstimatedPrice(l_bondOrderUnitRow.getForeignEstimatedPrice());
        }
        else
        {
            l_params.setForeignEstimatedPrice(null);
        }

        //債券注文単位テーブル.中途換金調整額
        if (!l_bondOrderUnitRow.getAdjustmentBeforeMaturityIsNull())
        {
            l_params.setAdjustmentBeforeMaturity(l_bondOrderUnitRow.getAdjustmentBeforeMaturity());
        }
        else
        {
            l_params.setAdjustmentBeforeMaturity(null);
        }

        //債券注文単位テーブル.経過日数
        if (!l_bondOrderUnitRow.getElapsedDaysIsNull())
        {
            l_params.setElapsedDays(l_bondOrderUnitRow.getElapsedDays());
        }
        else
        {
            l_params.setElapsedDays(null);
        }

        //債券注文単位テーブル.基準日数
        if (!l_bondOrderUnitRow.getCalcBaseDaysIsNull())
        {
            l_params.setCalcBaseDays(l_bondOrderUnitRow.getCalcBaseDays());
        }
        else
        {
            l_params.setCalcBaseDays(null);
        }

        //債券注文単位テーブル.カストディアンコード
        l_params.setCustodianCode(l_bondOrderUnitRow.getCustodianCode());

        //債券注文単位テーブル.注文経路区分
        l_params.setOrderRootDiv(l_bondOrderUnitRow.getOrderRootDiv());

        //債券注文単位テーブル.管理者コード
        l_params.setAdministratorCode(l_bondOrderUnitRow.getAdministratorCode());

        //債券注文単位テーブル.注文エラー理由コード
        l_params.setErrorReasonCode(l_bondOrderUnitRow.getErrorReasonCode());

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
