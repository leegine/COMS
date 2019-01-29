head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文通知一件サービスImpl(WEB3EquityOrderNotifyPartServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/26 欒学峰(中訊) 作成
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderNotifyInterceptor;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.service.delegate.WEB3EquityOrderInputNotifyAdapter;
import webbroker3.equity.service.delegate.WEB3EquityOrderNotifyUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.common.define.WEB3StatusDef;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

/**
 * （株式注文通知一件サービスImpl）。<br>
 * <br>
 * 引数で渡された【株式注文入力通知キューテーブル】の<br>
 * 現物株式の一件を処理する。<br>
 * [株式注文通知サービス]からコールされるサブサービス。
 * @@version 1.0
 */
public class WEB3EquityOrderNotifyUnitServiceImpl implements WEB3EquityOrderNotifyUnitService
{

    /**
     * （株式注文入力通知キューParams）。<br>
     * <br>
     * 【株式注文入力通知キューテーブル】の１レコード。 <br>
     */
    private HostEqtypeOrderReceiptParams hostEqtypeOrderReceiptParams;

    /**
     * （ログ出力ユーティリティ）。<br>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityOrderNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 40B30B200251
     */
    public WEB3EquityOrderNotifyUnitServiceImpl()
    {

    }

    /**
     * （calc概算受渡代金コール）。<br>
     * <br>
     * 概算受渡代金計算メソッドのコール、<br>
     * 及び株式注文内容.概算受渡代金 への値のセットを行う。<br>
     * ※シーケンス図 （注文通知）calc概算受渡代金コール を参照。<br>
     * @@param l_newCashBasedOrderSpec 株式注文内容
     * @@throws WEB3BaseException
     * @@roseuid 402AE0D40037
     */
    public void calcEstimateDeliveryAmount(
        WEB3EquityNewCashBasedOrderSpec l_newCashBasedOrderSpec)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcEstimateDeliveryAmount(WEB3EquityNewCashBasedOrderSpec)";

        log.entering(STR_METHOD_NAME);

        // 1.1 get執行条件( )
        //WEB3EquityOrderInputNotifyAdapter l_adapter =
        //  WEB3EquityOrderInputNotifyAdapter.create(this.hostEqtypeOrderReceiptParams);
        //EqTypeExecutionConditionType l_executionConditionType = l_adapter.getExecutionCondition();

        // 1.2 is信用口座開設(弁済区分 : String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accMgr.getMainAccount(
                    l_accMgr.getInstitution(l_newCashBasedOrderSpec.getInstitutionCode()).getInstitutionId(),
                    this.hostEqtypeOrderReceiptParams.getBranchCode(),
                    this.hostEqtypeOrderReceiptParams.getAccountCode());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            // 1.3 is信用口座開設()==true（＝信用客）
            if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                l_subAccount =
                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            // 1.4 is信用口座開設()≠true（＝非信用客）の場合
            else
            {
                l_subAccount =
                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        // 1.5 get取引銘柄( )
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            WEB3EquityProductManager l_equityProductManager =
                (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_equityProductManager.getTradedProduct(
                    l_subAccount.getInstitution(),
                    l_newCashBasedOrderSpec.getProductCode(),
                    l_newCashBasedOrderSpec.getMarketCode());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        // 1.10 getBranch( )
        Branch l_branch = l_mainAccount.getBranch();

        // 1.11 create手数料( )
        l_newCashBasedOrderSpec.createCommission(
            l_branch,
            hostEqtypeOrderReceiptParams.getSonarTradedCode());

        // 1.12 get手数料( )
        WEB3GentradeCommission l_commission = l_newCashBasedOrderSpec.getCommission();

        OrderTypeEnum l_orderType = null;
        if (l_newCashBasedOrderSpec.isBuyOrder())
        {
            l_orderType = OrderTypeEnum.EQUITY_BUY;
        }
        else
        {
            l_orderType = OrderTypeEnum.EQUITY_SELL;
        }
        
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY)
                .getOrderManager();
        // calc拘束金額計算単価
        double l_dblCalcPrice = l_orderManager.calcPriceForRestraintAmount(
            l_commission,
            l_orderType,
            l_newCashBasedOrderSpec.getLimitPrice(),
            l_newCashBasedOrderSpec.getWLimitPriceChange(),
            l_newCashBasedOrderSpec.getOrderCond(),
            l_newCashBasedOrderSpec.getExecConditionType(),
            l_newCashBasedOrderSpec.getPriceConditionType(),
            l_tradedProduct,
            l_subAccount,
            null);

        // set注文単価( )
        l_newCashBasedOrderSpec.setOrderUnitPrice(l_dblCalcPrice);

        // 1.13 get注文単価( )
        double l_dblOrderUnitPrice = l_newCashBasedOrderSpec.getOrderUnitPrice();

        // 1.14 isSellOrder( )
        boolean l_isSellOrder = l_newCashBasedOrderSpec.isSellOrder();

        // 1.15 getQuantity( )
        double l_dblQuantity = l_newCashBasedOrderSpec.getQuantity();

        // 1.16 calc概算受渡代金( )
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr = (WEB3EquityOrderManager) l_tm.getOrderManager();
        WEB3EquityEstimatedDeliveryPrice l_EstimatedDeliveryPrice =
            l_orderMgr.calcEstimateDeliveryAmount(
                l_commission,
                l_dblOrderUnitPrice,
                l_subAccount,
                l_tradedProduct,
                l_dblQuantity,
                l_isSellOrder,
                0.0D,
                0.0D,
                true,
                true);

        // 1.17 set概算受渡代金( )
        l_newCashBasedOrderSpec.setEstimateDeliveryAmount(
            l_EstimatedDeliveryPrice.getEstimateDeliveryAmount());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （通知一件処理）。<br>
     * <br>
     * 株式注文通知サービスより【株式注文入力通知キューテーブル】<br>
     * のデータを一件受け取り、新規注文登録を行う。<br>
     * @@param WEB3EquityOrderInputNotifyAdapter 株式注文入力通知データアダプタ
     * @@throws webbroker3.common.WEB3SystemLayerException
     * @@roseuid 402AE9500065
     */
    public void notifyPartProcess(
        WEB3EquityOrderInputNotifyAdapter l_orderInputNotifyAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyPartProcess(HostEqtypeOrderReceiptParams)";
        log.entering(STR_METHOD_NAME);

        this.hostEqtypeOrderReceiptParams = l_orderInputNotifyAdapter.getHostEqtypeOrderReceipt();
        try
        {
            WEB3EquityOrderInputNotifyAdapter l_adapter =
                WEB3EquityOrderInputNotifyAdapter.create(this.hostEqtypeOrderReceiptParams);

            // 1.2.2.1 get市場コード( )
            String l_strMarketCode = l_adapter.getMarketCode();

            // 1.2.2.2 get執行条件( )
            EqTypeExecutionConditionType l_execCondition = l_adapter.getExecutionCondition();

            // 1.2.2.3 get税区分( )
            TaxTypeEnum l_taxTypeEnum = l_adapter.getTaxDivision();

            // 1.2.2.4 is売注文( )
            boolean l_isSellOrder = l_adapter.isSellOrder();

            // 1.2.2.5 get値段条件( )
            String l_strPriceConditionType = l_adapter.getPriceConditionType();

            // 1.2.2.6 create注文内容( )
            WEB3EquityNewCashBasedOrderSpec l_eqNewCashBasedOrderSpec =
                WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                    this.hostEqtypeOrderReceiptParams.getInstitutionCode(),
                    null,
                    l_strMarketCode,
                    this.hostEqtypeOrderReceiptParams.getProductCode(),
                    this.hostEqtypeOrderReceiptParams.getQuantity(),
                    this.hostEqtypeOrderReceiptParams.getLimitPrice(),
                    l_execCondition,
                    l_taxTypeEnum,
                    new Timestamp(WEB3GentradeTradingTimeManagement.getOrderBizDate().getTime()),
                    l_isSellOrder,
                    this.hostEqtypeOrderReceiptParams.getChannel(),
                    l_strPriceConditionType,
                    WEB3OrderingConditionDef.DEFAULT,
                    null,
                    0.0D,
                    0.0D,
                    null);

            // 1.2.2.7 set手数料商品コード( )
            l_eqNewCashBasedOrderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);

            // 1.2.2.8 概算受渡代金計算の実行
            this.calcEstimateDeliveryAmount(l_eqNewCashBasedOrderSpec);

            // 1.2.2.9 株式注文通知インタセプタ( )
            WEB3EquityOrderNotifyInterceptor l_orderNotifyIntercepter =
                new WEB3EquityOrderNotifyInterceptor(this.hostEqtypeOrderReceiptParams);

            // 1.2.2.10 set株式注文内容( )
            l_orderNotifyIntercepter.setEquityOrderSpec(l_eqNewCashBasedOrderSpec);

            // 1.2.2.11 setThreadLocalPersistenceEventInterceptor( )
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_eqOrderMgr = (WEB3EquityOrderManager)l_tm.getOrderManager();
            l_eqOrderMgr.setThreadLocalPersistenceEventInterceptor(l_orderNotifyIntercepter);

            // 1.2.2.12 createNewOrderId( )
            long l_lngOrderId = l_eqOrderMgr.createNewOrderId();

            // 1.2.2.13 getTradingPassword( )
            WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_accMgr.getMainAccount(
                    l_accMgr.getInstitution(this.hostEqtypeOrderReceiptParams.getInstitutionCode()).getInstitutionId(),
                    this.hostEqtypeOrderReceiptParams.getBranchCode(),
                    this.hostEqtypeOrderReceiptParams.getAccountCode());
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strPassword = l_crypt.decrypt(l_mainAccount.getTradingPassword());

            // 1.2.2.14 setBusinessTimestamp( )
            WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

            // 1.2.2.15 submitNewCashBasedOrder( )
            WEB3GentradeSubAccount l_subAccount = null;
            if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                l_subAccount = (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            else
            {
                l_subAccount = (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
            EqTypeOrderSubmissionResult l_orderSubmissionResult =
                l_eqOrderMgr.submitNewCashBasedOrder(
                    l_subAccount,
                    l_eqNewCashBasedOrderSpec,
                    l_lngOrderId,
                    l_strPassword,
                    true);
            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // 1.2.2.16 余力再計算( )
            if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                l_subAccount = (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            else
            {
                l_subAccount = (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);

            //一件処理内部へ移す.start
            log.debug("株式注文入力通知キューテーブル.処理区分をupdateする");
            hostEqtypeOrderReceiptParams.setStatus(WEB3StatusDef.DEALT);
            hostEqtypeOrderReceiptParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            Processors.getDefaultProcessor().doUpdateQuery(hostEqtypeOrderReceiptParams);
            //.end
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        catch (DataNetworkException l_dne)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dne)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
