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
filename	WEB3BondOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券注文更新インタセプタ(WEB3BondOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/08 唐性峰 (中訊) 新規作成
                      : 2006/10/08 張騰宇 ＤＢ更新仕様No.013
*/

package webbroker3.bd;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3HostReflectDivDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.common.define.WEB3LockStatusDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (債券注文更新インタセプタ)
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondOrderUpdateInterceptor implements BondOrderManagerPersistenceEventInterceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondOrderUpdateInterceptor.class);

    /**
     * (債券約定日情報)<BR>
     * 債券約定日情報
     */
    private WEB3BondExecuteDateInfo bondExecuteDateInfo;

    /**
     * (債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果
     */
    private WEB3BondEstimatedPriceCalcResult bondEstimatedPriceCalcResult;

    /**
     * (拡張債券新規注文内容)<BR>
     * 拡張債券新規注文内容
     */
    private WEB3BondNewOrderSpec bondNewOrderSpec;

    /**
     * (債券注文更新インタセプタ)<BR>
     * コンストラクタ
     * @@roseuid 44DFD2880148
     */
    public WEB3BondOrderUpdateInterceptor()
    {

    }

    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * <BR>
     * 注文単位テーブル更新 <BR>
     * 　@注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * 項目設定内容は、DB更新仕様 <BR>
     * 「応募・買付_債券注文単位テーブルDB更新仕様.xls」 <BR>
     * 「売却_債券注文単位テーブルDB更新仕様.xls」<BR>
     * <BR>
     * 参照。 <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44DFD27500FA
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_params == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        //取引 deal_type   (応募・買付)
        if (OrderTypeEnum.BOND_BUY.equals(l_params.getOrderType()))
        {
            if (this.bondNewOrderSpec.getBondOrderTypeJudge().isBuyOrder())
            {
                //債券新規注文内容.get債券注文種別判定.is買付注文 == true の場合、92:国内仕切取引
                l_params.setDealType(WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING);
            }
            else if (this.bondNewOrderSpec.getBondOrderTypeJudge().isRecruitOrder())
            {
                //債券新規注文内容.get債券注文種別判定.is応募注文 == true の場合、応募の場合35:募集取引
                l_params.setDealType(WEB3DealTypeDef.RECRUIT_TRADING);
            }
        }

        //取引   deal_type   (売却)
        //92:国内仕切取引
        else if (OrderTypeEnum.BOND_SELL.equals(l_params.getOrderType()))
        {
            l_params.setDealType(WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING);
        }

        //受渡日  delivery_date
        //債券約定日情報.get受渡日（）
        l_params.setDeliveryDate(this.bondExecuteDateInfo.getDeliveryDate());

        //現地受渡日  foreign_delivery_date
        //債券約定日情報.get現地受渡日（）
        l_params.setForeignDeliveryDate(this.bondExecuteDateInfo.getForeignDeliveryDate());

        //注文ロック区分  lock_status
        //1：解除中
        l_params.setLockStatus(WEB3LockStatusDef.RELEASING);

        //注文約定区分  order_exec_status
        //0：未約定
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.UNEXECUTED);

        //発注日       biz_date
        //債券約定日情報.get発注日（）
        l_params.setBizDate(
            WEB3DateUtility.formatDate(this.bondExecuteDateInfo.getBizDate(), "yyyyMMdd"));

        //現地発注日   foreign_biz_date
        //債券約定日情報.get現地発注日（）
        l_params.setForeignBizDate(
            WEB3DateUtility.formatDate(this.bondExecuteDateInfo.getForeignBizDate(), "yyyyMMdd"));

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
        final String STR_ATTRIBUTE_NAME = "xblocks.gtl.attributes.systemtimestamp";

        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(STR_ATTRIBUTE_NAME);
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
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        l_params.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());

        //注文単価   price
        //債券受渡代金計算結果.get単価（）
        if (this.bondEstimatedPriceCalcResult.getPrice() != null)
        {
            l_params.setPrice(this.bondEstimatedPriceCalcResult.getPrice().doubleValue());
        }

        //識別コード    order_request_number
        //注文識別コード採番サービス.get新規識別コード( )
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
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
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
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
            log.error("__error  in  getProduct()__ ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
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
        l_params.setSettlementDiv(this.bondNewOrderSpec.getSettlementDiv());

        //自動約定区分        auto_exec_div
        //債券新規注文内容.getProductCode()に該当する債券銘柄.get自動約定区分（）
        l_params.setAutoExecDiv(l_bondProductRow.getAutoExecDiv());

        //約定単価      executed_price
        //null
        l_params.setExecutedPrice(null);

        //基準為替レート       base_fx_rate
        //債券受渡代金計算結果.get為替レート（）
        if (this.bondEstimatedPriceCalcResult.getFxRate() != null)
        {
            l_params.setBaseFxRate(this.bondEstimatedPriceCalcResult.getFxRate().doubleValue());
        }

        //約定為替レート    exec_fx_rate
        //null
        l_params.setExecFxRate(null);

        //売買代金（円貨）    trading_price
        //債券受渡代金計算結果.get売買代金（円貨）（）
        if (this.bondEstimatedPriceCalcResult.getTradingPrice() != null)
        {
            l_params.setTradingPrice(
                this.bondEstimatedPriceCalcResult.getTradingPrice().doubleValue());
        }

        //売買代金（外貨）    foreign_trading_price
        //債券受渡代金計算結果.get売買代金（外貨）（）
        if (this.bondEstimatedPriceCalcResult.getForeignTradePrice() != null)
        {
            l_params.setForeignTradingPrice(
                this.bondEstimatedPriceCalcResult.getForeignTradePrice().doubleValue());
        }

        //経過利子（円貨）    accrued_interest
        //債券受渡代金計算結果.get経過利子（円貨）（）
        if (this.bondEstimatedPriceCalcResult.getAccruedInterest() != null)
        {
            l_params.setAccruedInterest(
                this.bondEstimatedPriceCalcResult.getAccruedInterest().doubleValue());
        }

        //経過利子（外貨）    foreign_accrued_interest
        //債券受渡代金計算結果.get経過利子（外貨）（）
        if (this.bondEstimatedPriceCalcResult.getForeignAccruedInterest() != null)
        {
            l_params.setForeignAccruedInterest(
                this.bondEstimatedPriceCalcResult.getForeignAccruedInterest().doubleValue());
        }

        //受渡代金（円貨）    estimated_price
        //債券受渡代金計算結果.get受渡代金（円貨）（）
        if (this.bondEstimatedPriceCalcResult.getEstimatedPrice() != null)
        {
            l_params.setEstimatedPrice(
                this.bondEstimatedPriceCalcResult.getEstimatedPrice().doubleValue());
        }

        //受渡代金（外貨）    foreign_estimated_price
        //債券受渡代金計算結果.get受渡代金（外貨）（）
        if (this.bondEstimatedPriceCalcResult.getForeignEstimatedPrice() != null)
        {
            l_params.setForeignEstimatedPrice(
                this.bondEstimatedPriceCalcResult.getForeignEstimatedPrice().doubleValue());
        }

        //中途換金調整額 adjustment_before_maturity
        //null
        l_params.setAdjustmentBeforeMaturity(null);

        //経過日数      elapsed_days
        //債券受渡代金計算結果.get経過日数（）
        l_params.setElapsedDays(
            this.bondEstimatedPriceCalcResult.getElapsedDays());


        //基準日数      calc_base_days
        //債券受渡代金計算結果.get基準日数（）
        l_params.setCalcBaseDays(
            this.bondEstimatedPriceCalcResult.getCalcBaseDays());


        //約定日        exec_date
        //債券約定日情報.get約定日（）
        l_params.setExecDate(this.bondExecuteDateInfo.getExecuteDate());

        //現地約定日     foreign_exec_date
        //債券約定日情報.get現地約定日（）
        l_params.setForeignExecDate(this.bondExecuteDateInfo.getForeignExecuteDate());

        //入金日 payment_date
        //債券約定日情報.get入金日（）
        l_params.setPaymentDate(this.bondExecuteDateInfo.getPaymentDate());

        //カストディアンコード    custodian_code
        //債券新規注文内容.getProductCode()に該当する債券銘柄.getカストディアンコード（）
        l_params.setCustodianCode(l_bondProductRow.getCustodianCode());

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
     * （mutateメソッドの実装） <BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * １）　@注文単位オブジェクト取得 <BR>
     * <BR>
     * 引数の注文履歴Params.注文ID、<BR>
     * 注文単位ＩＤに該当する注文単位オブジェクトを取得する。 <BR>
     * <BR>
     * ２）　@拡張項目セット <BR>
     * 　@パラメータ.注文履歴Paramsの拡張項目に値をセットし、返却する。 <BR>
     * 　@項目設定内容は、 <BR>
     * 　@「応募・買付_債券注文履歴テーブルDB更新仕様.xls」<BR>
     * 　@「売却_債券注文履歴テーブルDB更新仕様.xls」<BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderActionParams
     * @@roseuid 44DFD2A70251
     */
    public BondOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderActionParams l_params)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderActionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_params == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        OrderManager l_orderMgr = l_tradingMod.getOrderManager();
        BondOrderUnitRow l_orderUnitRow = null;

        try
        {
            OrderUnit l_orderUnit = l_orderMgr.getOrderUnit(l_params.getOrderUnitId());
            l_orderUnitRow = (BondOrderUnitRow) l_orderUnit.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        //取引者ＩＤ    trader_id
        // 債券注文単位テーブル.取引者ID
        if (l_orderUnitRow.getTraderIdIsNull())
        {
            l_params.setTraderId(null);
        }
        else
        {
            l_params.setTraderId(l_orderUnitRow.getTraderId());
        }

        //取引    deal_type
        // 債券注文単位テーブル.取引
        l_params.setDealType(l_orderUnitRow.getDealType());

        //注文単価    price
        //債券注文単位テーブル.注文単価
        if (l_orderUnitRow.getPriceIsNull())
        {
            l_params.setPrice(null);
        }
        else
        {
            l_params.setPrice(l_orderUnitRow.getPrice());
        }

        //指値     limit_price
        //債券注文単位テーブル.指値
        if (l_orderUnitRow.getLimitPriceIsNull())
        {
            l_params.setLimitPrice(null);
        }
        else
        {
            l_params.setLimitPrice(l_orderUnitRow.getLimitPrice());
        }

        //注文約定区分 order_exec_status
        //債券注文単位テーブル.注文約定区分
        l_params.setOrderExecStatus(l_orderUnitRow.getOrderExecStatus());

        //約定日     exec_date
        //債券注文単位テーブル.約定日
        l_params.setExecDate(l_orderUnitRow.getExecDate());

        //現地約定日     foreign_exec_date
        //債券注文単位テーブル.現地約定日
        l_params.setForeignExecDate(l_orderUnitRow.getForeignExecDate());

        //受渡日     delivery_date
        // 債券注文単位テーブル.受渡日
        l_params.setDeliveryDate(l_orderUnitRow.getDeliveryDate());

        //現地受渡日     foreign_delivery_date
        //債券注文単位テーブル.現地受渡日
        l_params.setForeignDeliveryDate(l_orderUnitRow.getForeignDeliveryDate());

        //入金日 payment_date
        //債券注文単位テーブル.入金日
        l_params.setPaymentDate(l_orderUnitRow.getPaymentDate());

        //基準為替レート      base_fx_rate
        //債券注文単位テーブル.基準為替レート
        if (l_orderUnitRow.getBaseFxRateIsNull())
        {
            l_params.setBaseFxRate(null);
        }
        else
        {
            l_params.setBaseFxRate(l_orderUnitRow.getBaseFxRate());
        }

        //約定為替レート     exec_fx_rate
        //債券注文単位テーブル.約定為替レート
        if (l_orderUnitRow.getExecFxRateIsNull())
        {
            l_params.setExecFxRate(null);
        }
        else
        {
            l_params.setExecFxRate(l_orderUnitRow.getExecFxRate());
        }

        //売買代金（円貨）      trading_price
        //債券注文単位テーブル.売買代金（円貨）
        if (l_orderUnitRow.getTradingPriceIsNull())
        {
            l_params.setTradingPrice(null);
        }
        else
        {
            l_params.setTradingPrice(l_orderUnitRow.getTradingPrice());
        }

        //売買代金（外貨）      foreign_trading_price
        //債券注文単位テーブル.売買代金（外貨）
        if (l_orderUnitRow.getForeignTradingPriceIsNull())
        {
            l_params.setForeignTradingPrice(null);
        }
        else
        {
            l_params.setForeignTradingPrice(l_orderUnitRow.getForeignTradingPrice());
        }

        //経過利子（円貨）      accrued_interest
        //債券注文単位テーブル.経過利子（円貨）
        if (l_orderUnitRow.getAccruedInterestIsNull())
        {
            l_params.setAccruedInterest(null);
        }
        else
        {
            l_params.setAccruedInterest(l_orderUnitRow.getAccruedInterest());
        }

        //経過利子（外貨）      foreign_accrued_interest
        //債券注文単位テーブル.経過利子（外貨）
        if (l_orderUnitRow.getForeignAccruedInterestIsNull())
        {
            l_params.setForeignAccruedInterest(null);
        }
        else
        {
            l_params.setForeignAccruedInterest(l_orderUnitRow.getForeignAccruedInterest());
        }

        //受渡代金（円貨）      estimated_price
        //債券注文単位テーブル.受渡代金（円貨）
        if (l_orderUnitRow.getEstimatedPriceIsNull())
        {
            l_params.setEstimatedPrice(null);
        }
        else
        {
            l_params.setEstimatedPrice(l_orderUnitRow.getEstimatedPrice());
        }

        //受渡代金（外貨）      foreign_estimated_price
        // 債券注文単位テーブル.受渡代金（外貨）
        if (l_orderUnitRow.getForeignEstimatedPriceIsNull())
        {
            l_params.setForeignEstimatedPrice(null);
        }
        else
        {
            l_params.setForeignEstimatedPrice(l_orderUnitRow.getForeignEstimatedPrice());
        }

        //中途換金調整額 adjustment_before_maturity
        //債券注文単位テーブル.中途換金調整額
        if (l_orderUnitRow.getAdjustmentBeforeMaturityIsNull())
        {
            l_params.setAdjustmentBeforeMaturity(null);
        }
        else
        {
            l_params.setAdjustmentBeforeMaturity(l_orderUnitRow.getAdjustmentBeforeMaturity());
        }

        //経過日数        elapsed_days
        //債券注文単位テーブル.経過日数
        if (l_orderUnitRow.getElapsedDaysIsNull())
        {
            l_params.setElapsedDays(null);
        }
        else
        {
            l_params.setElapsedDays(l_orderUnitRow.getElapsedDays());
        }

        //基準日数        calc_base_days
        //債券注文単位テーブル.基準日数
        if (l_orderUnitRow.getCalcBaseDaysIsNull())
        {
            l_params.setCalcBaseDays(null);
        }
        else
        {
            l_params.setCalcBaseDays(l_orderUnitRow.getCalcBaseDays());
        }

        //カストディアンコード       custodian_code
        //債券注文単位テーブル.カストディアンコード
        l_params.setCustodianCode(l_orderUnitRow.getCustodianCode());

        //注文経路区分       order_root_div
        //債券注文単位テーブル.注文経路区分
        l_params.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());

        //管理者コード        administrator_code
        //債券注文単位テーブル.管理者コード
        l_params.setAdministratorCode(l_orderUnitRow.getAdministratorCode());

        //注文エラー理由コード      error_reason_code
        //債券注文単位テーブル.注文エラー理由コード
        l_params.setErrorReasonCode(l_orderUnitRow.getErrorReasonCode());

        log.exiting(STR_METHOD_NAME);

        return l_params;
    }

    /**
     * (get債券約定日情報)<BR>
     * this.債券約定日情報を返却する。
     * @@return WEB3BondExecuteDateInfo
     * @@roseuid 44DFD2CA01C5
     */
    public WEB3BondExecuteDateInfo getBondExecuteDateInfo()
    {
        return this.bondExecuteDateInfo;
    }

    /**
     * (set債券約定日情報)<BR>
     * 債券約定日情報をセットする。
     * @@param l_bondExecuteDateInfo - (債券約定日情報)<BR>
     * 債券約定日情報
     * @@roseuid 44DFD2D9000F
     */
    public void setBondExecuteDateInfo(WEB3BondExecuteDateInfo l_bondExecuteDateInfo)
    {
        this.bondExecuteDateInfo = l_bondExecuteDateInfo;
    }

    /**
     * (get債券受渡代金計算結果)<BR>
     * this.債券受渡代金計算結果を返却する。
     * @@return WEB3BondEstimatedPriceCalcResult
     * @@roseuid 44DFD2E60128
     */
    public WEB3BondEstimatedPriceCalcResult getBondEstimatedPriceCalcResult()
    {
        return this.bondEstimatedPriceCalcResult;
    }

    /**
     * (set債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果をセットする。
     * @@param l_bondEstimatedPriceCalcResult - (債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果
     * @@roseuid 44DFD2FE0242
     */
    public void setBondEstimatedPriceCalcResult(WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult)
    {
        this.bondEstimatedPriceCalcResult = l_bondEstimatedPriceCalcResult;
    }

    /**
     * (get拡張債券新規注文内容)<BR>
     * this.拡張債券新規注文内容を返却する。
     * @@return WEB3BondNewOrderSpec
     * @@roseuid 44DFD31603A9
     */
    public WEB3BondNewOrderSpec getBondNewOrderSpec()
    {
        return this.bondNewOrderSpec;
    }

    /**
     * (set拡張債券新規注文内容)<BR>
     * 拡張債券新規注文内容をセットする。
     * @@param l_bondNewOrderSpec - (拡張債券新規注文内容)<BR>
     * 拡張債券新規注文内容
     * @@roseuid 44DFD32A0196
     */
    public void setBondNewOrderSpec(WEB3BondNewOrderSpec l_bondNewOrderSpec)
    {
        this.bondNewOrderSpec = l_bondNewOrderSpec;
    }

    public BondOrderExecutionParams mutate(
        OrderManagerPersistenceType arg0,
        OrderManagerPersistenceContext arg1,
        BondOrderExecutionParams arg2)
    {
        return null;
    }

    public BatchedQuery getQueryToExecute(
        OrderManagerPersistenceType arg0,
        Class arg1)
    {
        return null;
    }
}
@
