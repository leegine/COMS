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
filename	WEB3MarginOrderNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������ʒm�ꌏ�T�[�r�XImpl(WEB3MarginOrderNotifyUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 䈋� (���u) �V�K�쐬
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.WEB3MarginOpenMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginOrderNotifyDataAdapter;
import webbroker3.equity.service.delegate.WEB3MarginOrderNotifyUnitService;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;

/**
 * �i�M�p��������ʒm�ꌏ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p��������ʒm�ꌏ�T�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3MarginOrderNotifyUnitServiceImpl
    implements WEB3MarginOrderNotifyUnitService
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOrderNotifyUnitServiceImpl.class);
    /**
     * @@roseuid 4140066F0210
     */
    public WEB3MarginOrderNotifyUnitServiceImpl()
    {

    }

    /**
     * (notify�V�K������)<BR>
     * �V�K�������ʒm���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�M�p��������ʒm�ꌏ�T�[�r�X)notify�V�K�������v�Q��<BR>
     * @@param l_marginOrderNotifyDataAdapter - <BR>
     *   �M�p����������͒ʒm�f�[�^�A�_�v�^�I�u�W�F�N�g<BR>
     * @@roseuid 40EA5A3702F4
     */
    public void notifyOpenMarginOrder(
        WEB3MarginOrderNotifyDataAdapter l_marginOrderNotifyDataAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyOpenMarginOrder(WEB3MarginOrderNotifyDataAdapter l_marginOrderNotifyDataAdapter)";
        log.entering(STR_METHOD_NAME);
        HostEqtypeOrderReceiptParams l_eqtypeOrderReceiptParams = l_marginOrderNotifyDataAdapter.getDataSourseObject();
        try
        {
            //1.1.is����( )
            boolean l_blnisLong = l_marginOrderNotifyDataAdapter.isLong();
            //1.2.get�s��R�[�h( )
            String l_marketCode =
                l_marginOrderNotifyDataAdapter.getMarketCode();
            //1.3.get���s����( )
            EqTypeExecutionConditionType l_exeConditionType =
                l_marginOrderNotifyDataAdapter.getExecutionCondition();
            //1.4.get������( )
            Date l_datorderBizDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
            //1.5.get�ŋ敪( )
            TaxTypeEnum l_taxTypeEnum =
                l_marginOrderNotifyDataAdapter.getTaxType();
            //1.6.�l�i����( )
            String l_priceConditionType = l_marginOrderNotifyDataAdapter.getPriceConditionType();
            //1.7.get�ٍϋ敪( )
            String l_repayType =
                l_marginOrderNotifyDataAdapter.getRepaymentType();
            //1.8.get�ٍϊ����l( )
            double l_dblrepaymentNum =
                l_marginOrderNotifyDataAdapter.getRepaymentNum();
            //1.9.create�V�K���������e()
            WEB3MarginOpenContractOrderSpec l_openContractOrderSpec =
                WEB3MarginOpenContractOrderSpec.createOpenMarginOrderSpec(
                    null,
                    l_blnisLong,
                    l_eqtypeOrderReceiptParams.getProductCode(),
                    l_marketCode,
                    l_eqtypeOrderReceiptParams.getQuantity(),
                    l_eqtypeOrderReceiptParams.getLimitPrice(),
                    l_exeConditionType,
                    l_datorderBizDate,
                    l_taxTypeEnum,
                    l_priceConditionType,
                    WEB3OrderingConditionDef.DEFAULT,
                    null,
                    0,
                    0,
                    l_repayType,
                    l_dblrepaymentNum,
                    null);
            //1.10.getInstitution()
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            Institution l_insititution =
                l_accountManager.getInstitution(
                    l_eqtypeOrderReceiptParams.getInstitutionCode());
            //1.11. getInstitutionId( )
            long l_insititutionId = l_insititution.getInstitutionId();
            //1.12.getMainAccount()
            MainAccount l_mainAccount =
                l_accountManager.getMainAccount(
                    l_insititutionId,
                    l_eqtypeOrderReceiptParams.getBranchCode(),
                    l_eqtypeOrderReceiptParams.getAccountCode());
            //1.13.getSubAccount()
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            //1.14.create�萔��()
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_finApp
                    .getTradingModule(ProductTypeEnum.EQUITY)
                    .getBizLogicProvider();
            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(
                    l_subAccount,
                    l_marketCode,
                    l_datorderBizDate,
                    l_eqtypeOrderReceiptParams.getChannel(),
                    l_repayType,
                    l_dblrepaymentNum,
                    OrderCategEnum.OPEN_MARGIN);
            //1.17.get�������()
            EqTypeProductManager l_productManager =
                (EqTypeProductManager)l_finApp
                    .getTradingModule(ProductTypeEnum.EQUITY)
                    .getProductManager();
            WEB3EquityTradedProduct l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    l_insititution,
                    l_eqtypeOrderReceiptParams.getProductCode(),
                    l_marketCode);
                    
            OrderTypeEnum l_orderType = null;
            if (l_marginOrderNotifyDataAdapter.isLong())
            {
                l_orderType = OrderTypeEnum.MARGIN_LONG;
            }
            else
            {
                l_orderType = OrderTypeEnum.MARGIN_SHORT;
            }
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp
                    .getTradingModule(ProductTypeEnum.EQUITY)
                    .getOrderManager();
            // calc�S�����z�v�Z�P��
            double l_dblCalcPrice = l_orderManager.calcPriceForRestraintAmount(
                l_commission,
                l_orderType,
                l_openContractOrderSpec.getLimitPrice(),
                l_openContractOrderSpec.getWLimitPrice(),
                l_openContractOrderSpec.getOrderConditionType(),
                l_openContractOrderSpec.getExecConditionType(),
                l_openContractOrderSpec.getPriceConditionType(),
                l_tradedProduct,
                l_subAccount,
                null);
            //1.22.set�v�Z�P��()
            l_openContractOrderSpec.setCalcUnitPrice(l_dblCalcPrice);

			//1.23 �v�ZContractAmountAtOrder()
			double l_dblContractAmountAtOrder =
						   l_orderManager.calcContractAmountAtOrder(
			                   l_commission,
					           l_openContractOrderSpec.getCalcUnitPrice(),
			                   l_subAccount,
			                   l_tradedProduct,
			                   l_eqtypeOrderReceiptParams.getQuantity(),
			                   0,
			                   0,
			                   true
			                   );

			//1.24.setContractAmountAtOrder()
			l_openContractOrderSpec.setContractAmount(l_dblContractAmountAtOrder);
            //1.25.calc�ϑ��萔��(�萔��, SubAccount()
            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

            //1.26.is�󔄂�K���Ώ�()
            boolean l_shortSellOrderFlag = l_marginOrderNotifyDataAdapter.isShortSellingRestraint();

            //1.27.�M�p�V�K���X�V�C���^�Z�v�^()
            WEB3MarginOpenMarginUpdateInterceptor l_interceptor =
                new WEB3MarginOpenMarginUpdateInterceptor(
                    l_openContractOrderSpec,
                    l_commission,
                    l_eqtypeOrderReceiptParams.getChannel(),
                    WEB3OrderRootDivDef.HOST,
                    l_shortSellOrderFlag);
            //1.28.set���ʃR�[�h()
            l_interceptor.setOrderRequestNumber(
                l_eqtypeOrderReceiptParams.getOrderRequestNumber());
            //1.29.set�󒍓���()
            l_interceptor.setReceivedDateTime(
                l_eqtypeOrderReceiptParams.getCreateDatetime());
            //1.30.set��n��()
            l_interceptor.setDeliveryDate(
                WEB3DateUtility.getDate(
                    l_eqtypeOrderReceiptParams.getDeliveryDate(),
                    "yyyyMMdd"));
            //1.31.set�����o�H�敪()
            l_interceptor.setSubmitOrderRouteDiv(l_eqtypeOrderReceiptParams.getSubmitOrderRouteDiv());
            //1.32.setThreadLocalPersistenceEventInterceptor()
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_interceptor);
            //1.33.createNewOrderId( )
            long l_orderId = l_orderManager.createNewOrderId();
            //1.34.getTradingPassword( )
            String l_tradingPassword = l_mainAccount.getTradingPassword();
            //1.35.setBusinessTimestamp( )
            WEB3GentradeTradingTimeManagement.setBusinessTimestamp();
            //1.36.submitOpenContractOrder()
            EqTypeOrderSubmissionResult l_orderSubmissionResult = l_orderManager.submitOpenContractOrder(
                l_subAccount,
                l_openContractOrderSpec,
                l_orderId,
                l_tradingPassword,
                true);
            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //1.37.�]�͍Čv�Z()
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);

            //�ꌏ���������ֈڂ�.start
            //--------------------
            //�����敪�X�V�l �ݒ�@@(���펞)
            //--------------------
            l_eqtypeOrderReceiptParams.setStatus(WEB3StatusDef.DEALT);
            l_eqtypeOrderReceiptParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            Processors.getDefaultProcessor().doUpdateQuery(l_eqtypeOrderReceiptParams);
            //.end
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        catch (NotFoundException l_nfe)
        {
            throw  new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (notify�ԍϒ���)<BR>
     * �ԍϒ����ʒm���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�M�p��������ʒm�ꌏ�T�[�r�X)notify�ԍϒ����v�Q��<BR>
     * @@param l_marginOrderNotifyDataAdapter - <BR>
     *  �M�p����������͒ʒm�f�[�^�A�_�v�^�I�u�W�F�N�g<BR>
     * @@roseuid 40EA5B470343
     */
    public void notifyCloseMarginOrder(
        WEB3MarginOrderNotifyDataAdapter l_marginOrderNotifyDataAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCloseMarginOrder(WEB3MarginOrderNotifyDataAdapter l_marginOrderNotifyDataAdapter)";
        log.entering(STR_METHOD_NAME);
        HostEqtypeOrderReceiptParams l_eqtypeOrderReceiptParams =l_marginOrderNotifyDataAdapter.getDataSourseObject();
        try
        {

            //1.1.getInstitution()
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule
                = (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            Institution l_insititution =
                l_accountManager.getInstitution(
                    l_eqtypeOrderReceiptParams.getInstitutionCode());
            //1.2.getInstitutionId()
            long l_insititutionId = l_insititution.getInstitutionId();
            //1.3.getMainAccount
            MainAccount l_mainAccount =
                l_accountManager.getMainAccount(
                    l_insititutionId,
                    l_eqtypeOrderReceiptParams.getBranchCode(),
                    l_eqtypeOrderReceiptParams.getAccountCode());
            //1.4.getSubAccount()
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            //1.5.is����( )
            boolean l_blnisLong = l_marginOrderNotifyDataAdapter.isLong();
            //1.6.get�s��R�[�h( )
            String l_marketCode =
                l_marginOrderNotifyDataAdapter.getMarketCode();
            //1.7.get�s��()
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_insititution, l_marketCode);
            //1.8.get�ŋ敪( )
            TaxTypeEnum l_taxType = l_marginOrderNotifyDataAdapter.getTaxType();
            //1.9. get�ٍϋ敪( )
            String l_repaymentType =
                l_marginOrderNotifyDataAdapter.getRepaymentType();
            //1.10.get�ٍϊ����l( )
            double l_dblrepaymentNum =
                l_marginOrderNotifyDataAdapter.getRepaymentNum();
            //1.11.getProduct()
            EqTypeProductManager l_productManager =
                (EqTypeProductManager)l_finApp
                    .getTradingModule(ProductTypeEnum.EQUITY)
                    .getProductManager();
            WEB3EquityProduct l_product =
                (WEB3EquityProduct)l_productManager.getProduct(
                    l_insititution,
                    l_eqtypeOrderReceiptParams.getProductCode());
            //1.12.getProductId( )
            long l_productId = l_product.getProductId();
            //1.13.create���ό������׈ꗗ()
            WEB3EquityPositionManager l_positionManager
                = (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits =
                l_positionManager.createCloseMarginContracts(
                    l_subAccount,
                    l_blnisLong,
                    l_market.getMarketId(),
                    l_productId,
                    l_taxType,
                    l_repaymentType,
                    l_dblrepaymentNum);
            //1.14.create���ό����G���g��()
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp
                    .getTradingModule(ProductTypeEnum.EQUITY)
                    .getOrderManager();
            EqTypeSettleContractOrderEntry[] l_settleContractOrderEntry =
                l_orderManager.createClosingContractEntry(
                    0,
                    l_eqtypeOrderReceiptParams.getQuantity(),
                    l_closeMarginContractUnits,
                    true);
            //1.15.get���s����( )
            EqTypeExecutionConditionType l_executionCondType =
                l_marginOrderNotifyDataAdapter.getExecutionCondition();
            //1.16.get�l�i����
            String l_priceConditionType = l_marginOrderNotifyDataAdapter.getPriceConditionType();
            //1.17.get������( )
            Date l_bizDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
            //1.18.create�ԍϒ������e()
            WEB3MarginSettleContractOrderSpec l_spec =
                WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                    null,
                    l_settleContractOrderEntry,
                    l_eqtypeOrderReceiptParams.getLimitPrice(),
                    l_executionCondType,
                    l_bizDate,
                    l_taxType,
                    l_priceConditionType,
                    WEB3OrderingConditionDef.DEFAULT,
                    null,
                    0,
                    0,
                    WEB3ClosingOrderDef.OPEN_DATE,
                    null);
            //1.19.create�萔��()
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_finApp
                    .getTradingModule(ProductTypeEnum.EQUITY)
                    .getBizLogicProvider();
            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(
                    l_subAccount,
                    l_marketCode,
                    l_bizDate,
                    l_eqtypeOrderReceiptParams.getChannel(),
                    l_repaymentType,
                    l_dblrepaymentNum,
                    OrderCategEnum.CLOSE_MARGIN);
            //1.20.isLimitOrder( )
            boolean l_isLimitPrice = l_spec.isLimitOrder();
            //1.21.setIs�w�l()
            l_commission.setIsLimitPrice(l_isLimitPrice);
            //1.22.get�������()
            WEB3EquityTradedProduct l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    l_insititution,
                    l_eqtypeOrderReceiptParams.getProductCode(),
                    l_marketCode);
            //1.23.calc�T�Z���ϑ��v���()
            WEB3EquityRealizedProfitAndLossPrice l_profitAndLossPrice =
                l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    l_eqtypeOrderReceiptParams.getLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_settleContractOrderEntry,
                    l_eqtypeOrderReceiptParams.getQuantity(),
                    null,
                    0D,
                    0D,
                    true);
            //1.24.�M�p�ԍύX�V�C���^�Z�v�^()
            WEB3MarginCloseMarginUpdateInterceptor l_interceptor =
                new WEB3MarginCloseMarginUpdateInterceptor(
                    l_spec,
                    l_commission,
                    l_profitAndLossPrice,
                    l_repaymentType,
                    l_dblrepaymentNum,
                    l_eqtypeOrderReceiptParams.getChannel(),
                    WEB3OrderRootDivDef.HOST);
            //1.25.set���ʃR�[�h()
            l_interceptor.setOrderRequestNumber(
                l_eqtypeOrderReceiptParams.getOrderRequestNumber());
            //1.26.set�󒍓���()
            l_interceptor.setReceivedDateTime(
                l_eqtypeOrderReceiptParams.getCreateDatetime());
			//1.27.set��n��()
			l_interceptor.setDeliveryDate(
                WEB3DateUtility.getDate(
                    l_eqtypeOrderReceiptParams.getDeliveryDate(),
                    "yyyyMMdd"));
            //1.28.set�����o�H�敪()
            l_interceptor.setSubmitOrderRouteDiv(
                l_eqtypeOrderReceiptParams.getSubmitOrderRouteDiv());
            //1.29.setThreadLocalPersistenceEventInterceptor()
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_interceptor);
            //1.30.createNewOrderId( )
            long l_orderId = l_orderManager.createNewOrderId();
            //1.31.getTradingPassword( )
            String l_tradingPassword = l_mainAccount.getTradingPassword();
            //1.32.setBusinessTimestamp( )
            WEB3GentradeTradingTimeManagement.setBusinessTimestamp();
            //1.33.submitOpenContractOrder()
            EqTypeOrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitSettleContractOrder(
                    l_subAccount,
                    l_spec,
                    l_orderId,
                    l_tradingPassword,
                    true);
            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.34.�]�͍Čv�Z()
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);

            //�ꌏ���������ֈڂ�.start
            //--------------------
            //�����敪�X�V�l �ݒ�@@(���펞)
            //--------------------
            l_eqtypeOrderReceiptParams.setStatus(WEB3StatusDef.DEALT);
            l_eqtypeOrderReceiptParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            Processors.getDefaultProcessor().doUpdateQuery(l_eqtypeOrderReceiptParams);
            //.end
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        catch (NotFoundException l_nfe)
        {
            throw  new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
