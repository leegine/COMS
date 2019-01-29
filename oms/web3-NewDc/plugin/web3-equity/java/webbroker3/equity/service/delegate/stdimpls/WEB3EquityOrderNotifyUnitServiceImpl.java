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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm�ꌏ�T�[�r�XImpl(WEB3EquityOrderNotifyPartServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/26 �R�w��(���u) �쐬
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
 * �i���������ʒm�ꌏ�T�[�r�XImpl�j�B<br>
 * <br>
 * �����œn���ꂽ�y�����������͒ʒm�L���[�e�[�u���z��<br>
 * ���������̈ꌏ����������B<br>
 * [���������ʒm�T�[�r�X]����R�[�������T�u�T�[�r�X�B
 * @@version 1.0
 */
public class WEB3EquityOrderNotifyUnitServiceImpl implements WEB3EquityOrderNotifyUnitService
{

    /**
     * �i�����������͒ʒm�L���[Params�j�B<br>
     * <br>
     * �y�����������͒ʒm�L���[�e�[�u���z�̂P���R�[�h�B <br>
     */
    private HostEqtypeOrderReceiptParams hostEqtypeOrderReceiptParams;

    /**
     * �i���O�o�̓��[�e�B���e�B�j�B<br>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityOrderNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 40B30B200251
     */
    public WEB3EquityOrderNotifyUnitServiceImpl()
    {

    }

    /**
     * �icalc�T�Z��n����R�[���j�B<br>
     * <br>
     * �T�Z��n����v�Z���\�b�h�̃R�[���A<br>
     * �y�ъ����������e.�T�Z��n��� �ւ̒l�̃Z�b�g���s���B<br>
     * ���V�[�P���X�} �i�����ʒm�jcalc�T�Z��n����R�[�� ���Q�ƁB<br>
     * @@param l_newCashBasedOrderSpec �����������e
     * @@throws WEB3BaseException
     * @@roseuid 402AE0D40037
     */
    public void calcEstimateDeliveryAmount(
        WEB3EquityNewCashBasedOrderSpec l_newCashBasedOrderSpec)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcEstimateDeliveryAmount(WEB3EquityNewCashBasedOrderSpec)";

        log.entering(STR_METHOD_NAME);

        // 1.1 get���s����( )
        //WEB3EquityOrderInputNotifyAdapter l_adapter =
        //  WEB3EquityOrderInputNotifyAdapter.create(this.hostEqtypeOrderReceiptParams);
        //EqTypeExecutionConditionType l_executionConditionType = l_adapter.getExecutionCondition();

        // 1.2 is�M�p�����J��(�ٍϋ敪 : String)
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
            // 1.3 is�M�p�����J��()==true�i���M�p�q�j
            if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                l_subAccount =
                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            // 1.4 is�M�p�����J��()��true�i����M�p�q�j�̏ꍇ
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

        // 1.5 get�������( )
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

        // 1.11 create�萔��( )
        l_newCashBasedOrderSpec.createCommission(
            l_branch,
            hostEqtypeOrderReceiptParams.getSonarTradedCode());

        // 1.12 get�萔��( )
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
        // calc�S�����z�v�Z�P��
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

        // set�����P��( )
        l_newCashBasedOrderSpec.setOrderUnitPrice(l_dblCalcPrice);

        // 1.13 get�����P��( )
        double l_dblOrderUnitPrice = l_newCashBasedOrderSpec.getOrderUnitPrice();

        // 1.14 isSellOrder( )
        boolean l_isSellOrder = l_newCashBasedOrderSpec.isSellOrder();

        // 1.15 getQuantity( )
        double l_dblQuantity = l_newCashBasedOrderSpec.getQuantity();

        // 1.16 calc�T�Z��n���( )
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

        // 1.17 set�T�Z��n���( )
        l_newCashBasedOrderSpec.setEstimateDeliveryAmount(
            l_EstimatedDeliveryPrice.getEstimateDeliveryAmount());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �i�ʒm�ꌏ�����j�B<br>
     * <br>
     * ���������ʒm�T�[�r�X���y�����������͒ʒm�L���[�e�[�u���z<br>
     * �̃f�[�^���ꌏ�󂯎��A�V�K�����o�^���s���B<br>
     * @@param WEB3EquityOrderInputNotifyAdapter �����������͒ʒm�f�[�^�A�_�v�^
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

            // 1.2.2.1 get�s��R�[�h( )
            String l_strMarketCode = l_adapter.getMarketCode();

            // 1.2.2.2 get���s����( )
            EqTypeExecutionConditionType l_execCondition = l_adapter.getExecutionCondition();

            // 1.2.2.3 get�ŋ敪( )
            TaxTypeEnum l_taxTypeEnum = l_adapter.getTaxDivision();

            // 1.2.2.4 is������( )
            boolean l_isSellOrder = l_adapter.isSellOrder();

            // 1.2.2.5 get�l�i����( )
            String l_strPriceConditionType = l_adapter.getPriceConditionType();

            // 1.2.2.6 create�������e( )
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

            // 1.2.2.7 set�萔�����i�R�[�h( )
            l_eqNewCashBasedOrderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);

            // 1.2.2.8 �T�Z��n����v�Z�̎��s
            this.calcEstimateDeliveryAmount(l_eqNewCashBasedOrderSpec);

            // 1.2.2.9 ���������ʒm�C���^�Z�v�^( )
            WEB3EquityOrderNotifyInterceptor l_orderNotifyIntercepter =
                new WEB3EquityOrderNotifyInterceptor(this.hostEqtypeOrderReceiptParams);

            // 1.2.2.10 set�����������e( )
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

            // 1.2.2.16 �]�͍Čv�Z( )
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

            //�ꌏ���������ֈڂ�.start
            log.debug("�����������͒ʒm�L���[�e�[�u��.�����敪��update����");
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
