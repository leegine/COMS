head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�z�ꌏ�T�[�r�XImpl(WEB3EquityOrderCarryOverUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/02 羐� (���u) �V�K�쐬
Revesion History : 2006/11/20 ������@@(���u)���f��No.1029,No.1030,No.1070
Revesion History : 2007/06/04 �����q (���u) �d�l�ύX���f��No.1160
*/
package webbroker3.equity.service.delegate.stdimpls;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractOpenOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderManagerPersistenceEventInterceptor;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations;
import webbroker3.equity.WEB3MarginCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginNewOrderValidationResult;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.WEB3MarginOpenMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverUnitService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;

/**
 * �i�����J�z�ꌏ�T�[�r�XImpl�j�B
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverUnitServiceImpl
    implements WEB3EquityOrderCarryOverUnitService
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderCarryOverUnitServiceImpl.class);

    /**
     * @@roseuid 40B2A3100101
     */
    public WEB3EquityOrderCarryOverUnitServiceImpl()
    {

    }

    /**
     * (insert�J�z����)<BR>
     * <BR>
     * �����Ɏw�肳�ꂽ�����P�ʃI�u�W�F�N�g����A
     *  �J�z�̐V�K�����f�[�^�i�������� or �V�K�� or �ԍρj���쐬����B
     *  �V�[�P���X�}�u�i�����J�z�j�ڋq�P�ʌJ�z���s�v��
     *  �����J�z�ꌏ�T�[�r�XImpl.insert�J�z����(�����P��)�Q��
     * @@param l_orderUnit - �����P��<BR>
     * �J�z�Ώۂ̒����P�ʃI�u�W�F�N�g�B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4137CF8A0219
     */
    public boolean insertCarryOverOrder(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertCarryOverOrder(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnOk = true;
        
        //�����P��.�����J�e�S���ɂ�肢���ꂩ���R�[������
        try
        {
            if (OrderCategEnum.ASSET.equals(l_orderUnit.getOrderCateg()))
            {
                insertEquityCarryOverOrder((EqTypeOrderUnit)l_orderUnit);
            }
            else if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                insertOpenMarginCarryOverOrder(
                    (EqTypeContractOpenOrderUnit)l_orderUnit);
            }
            else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                insertCloseMarginCarryOverOrder(
                    (EqTypeContractSettleOrderUnit)l_orderUnit);
            }
        }
        catch (WEB3BaseException l_exp)
        {
            log.debug(STR_METHOD_NAME, l_exp);
            log.debug("error order_unit_id = [" + l_orderUnit.getOrderUnitId() + "]");
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            if (l_errorInfo.getErrorTag().startsWith("SYSTEM_ERROR"))
            {
                throw new WEB3SystemLayerException(
                    l_errorInfo,
                    l_exp.getErrorMethod(),
                    l_exp.getMessage(),
                    l_exp.getException());
            }
            
            // get�����G���[���R�R�[�h
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_traModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_traModule.getOrderManager();
            String l_strErrorReasonCode = l_orderManager.getErrorReasonCode(l_errorInfo.getErrorCode());
            log.debug("l_strErrorReasonCode = " + l_strErrorReasonCode);
            
            log.debug("update�J�z�������̎��s");
            this.updateOriginalOrder(
                (EqTypeOrderUnit)l_orderUnit,
                l_strErrorReasonCode);
            
            l_blnOk = false;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_blnOk;
    }

    /**
     * (insert�����J�z����)<BR>
     * ���������̔��������̌J�z�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����J�z�ꌏ�T�[�r�X�j�����J�z�����쐬�v�Q�ƁB<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �J�z�Ώۂ̒����P�ʃI�u�W�F�N�g�B
     * @@roseuid 4104BB210299
     */
    public void insertEquityCarryOverOrder(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertEquityCarryOverOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            TradingModule l_traModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_traModule.getOrderManager();
            
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //1.1. �����P��.�����ID��null�̏ꍇ�̂�
            WEB3GentradeTrader l_trader = null;
            if (!l_orderUnitRow.getTraderIdIsNull())
            {
                l_trader = (WEB3GentradeTrader)l_finObjectManager.getTrader(l_orderUnitRow.getTraderId());
            }
            
            //1.2. create�������e()
            WEB3GentradeBranch l_branch =
                (WEB3GentradeBranch)l_accountManager.getBranch(l_orderUnitRow.getBranchId());
                    
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
                
            EqTypeProduct l_product =
                (EqTypeProduct)l_traModule.getProductManager().getProduct(
                    l_orderUnitRow.getProductId());
            EqtypeProductRow l_productRow =
                (EqtypeProductRow)l_product.getDataSourceObject();

            boolean l_isSellOrder;
            if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnitRow.getOrderType()))
            {
                l_isSellOrder = false;
            }
            else
            {
                l_isSellOrder = true;
            }
            
            OrderUnit l_firstOrderUnit = l_orderManager.getFirstOrderUnit(l_orderUnit);
            WEB3EquityNewCashBasedOrderSpec l_newCashBasedOrderSpec =
                WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                    l_branch.getInstitution().getInstitutionCode(),
                    l_trader,
                    l_market.getMarketCode(),
                    l_productRow.getProductCode(),
                    l_orderUnitRow.getQuantity() - l_orderUnitRow.getExecutedQuantity(),
                    l_orderUnitRow.getLimitPrice(),
                    l_orderUnitRow.getExecutionConditionType(),
                    l_orderUnitRow.getTaxType(),
                    l_orderUnitRow.getExpirationDate(),
                    l_isSellOrder,
                    l_orderUnitRow.getOrderChanel(),
                    l_orderUnitRow.getPriceConditionType(),
                    l_orderUnitRow.getOrderConditionType(),
                    l_orderUnitRow.getOrderCondOperator(),
                    l_orderUnitRow.getStopOrderPrice(),
                    l_orderUnitRow.getWLimitPrice(),
                    new Long(l_firstOrderUnit.getOrderUnitId()),
                    l_orderUnitRow.getWLimitExecCondType());
                    
            //1.3. set�萔�����i�R�[�h()
            l_newCashBasedOrderSpec.setCommissionProductCode(
                WEB3CommisionProductCodeDef.LISTING_STOCK);
                
            //1.4. create�萔��()
            WEB3GentradeCommission l_commission =
                l_newCashBasedOrderSpec.createCommission(
                    l_branch,
                    l_orderUnitRow.getSonarTradedCode());
                    
            //1.5. validate������������()
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_orderUnitRow.getAccountId(),
                    l_orderUnitRow.getSubAccountId());
            EqTypeNewOrderValidationResult l_orderValidationResult = 
                l_orderManager.validateNewCashBasedOrder(
                l_subAccount,
                l_newCashBasedOrderSpec);
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.6. getTradedProduct( )
            WEB3EquityTradedProduct l_tradedProduct = null;
            if (l_orderUnit.getTradedProduct() != null)
            {
                l_tradedProduct =
                    (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
            }

            //calc�T�Z��n���()
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //�萔�� : �쐬�����萔���I�u�W�F�N�g
            //�w�l : �����������e.getLimitPrice()
            //�iW�w�l�j�����w�l : �����������e.get�iW�w�l�j�����w�l()
            //�t�w�l��l : �����������e.get�t�w�l��l()
            //���s���� : �����������e.get���s����()
            //�iW�w�l�j���s���� : �����������e.get�iW�w�l�j���s����()
            //�l�i���� : �����������e.get�l�i����()
            //�������� : �����������e.get��������()
            //�m�F���擾���� : null
            //is�X�g�b�v�����L�� : �g�����������}�l�[�W��.is�X�g�b�v�����L��(�����P��)
            //�⏕���� : �擾�����⏕����
            //������� : �����P��.getTradedProduct()
            //���� : �����������e.getQuantity( )�i���o�����̐��ʁj
            //is������ : �����������e.isSellOrder( )
            //��萔�� : 0
            //���v�����z : 0
            //isSkip���z�`�F�b�N : false�i�X�L�b�v���Ȃ��j
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice =
                l_orderManager.calcEstimateDeliveryAmount(
                    l_commission,
                    l_newCashBasedOrderSpec.getLimitPrice(),
                    l_newCashBasedOrderSpec.getWLimitPriceChange(),
                    l_newCashBasedOrderSpec.getStopLimitPriceBasePrice(),
                    l_newCashBasedOrderSpec.getExecConditionType(),
                    l_newCashBasedOrderSpec.getWlimitExecCondType(),
                    l_newCashBasedOrderSpec.getPriceConditionType(),
                    l_newCashBasedOrderSpec.getOrderCond(),
                    null,
                    l_orderManager.isStopOrderValid(l_orderUnit),
                    l_subAccount,
                    l_tradedProduct,
                    l_newCashBasedOrderSpec.getQuantity(),
                    l_newCashBasedOrderSpec.isSellOrder(),
                    0.0D,
                    0.0D,
                    false);

            // set�����P��()
            //�T�Z��n����v�Z����.get�v�Z�P��()�̖߂�l���Z�b�g����B
            l_newCashBasedOrderSpec.setOrderUnitPrice(l_deliveryPrice.getCalcUnitPrice());
            log.debug("�����P���F[" + l_deliveryPrice.getCalcUnitPrice() + "]");

            //1.10. set�T�Z��n���()
            l_newCashBasedOrderSpec.setEstimateDeliveryAmount(
                l_deliveryPrice.getEstimateDeliveryAmount());
            log.debug("�T�Z��n����F[" + l_deliveryPrice.getEstimateDeliveryAmount() + "]");
                
            //1.11. ���������C���^�Z�v�^()
            WEB3EquityOrderManagerPersistenceEventInterceptor l_interceptor =
                new WEB3EquityOrderManagerPersistenceEventInterceptor();
                
            //1.12. set�����������e(�����������e)
            l_interceptor.setEquityOrderSpec(l_newCashBasedOrderSpec);
            
            //1.13. set�J�z�������P��()
            l_interceptor.setCarryoverOrderUnit(l_orderUnit);
            
            //1.14. validate����]��()
            WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_orderSpecIntercepter =
            {
                l_interceptor
            };
            Object[] l_orderSpec =
            {
                l_newCashBasedOrderSpec
            };
            
            WEB3TPTradingPowerResult l_tpResult = null;
            l_tpResult = l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepter,
                l_orderSpec,
                l_orderUnit.getOrderType(),
                false);

            // throw�]�̓G���[�ڍ׏��
            // �]�̓G���[�̏�񂩂�G���[�R�[�h�����肵�Athrow����B
            l_orderManager.throwTpErrorInfo(l_tpResult, l_orderUnitRow.getOrderType());

            //1.15. setThreadLocalPersistenceEventInterceptor()
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_interceptor);
            
            //1.16. createNewOrderId( )
            long l_newOrderId = l_orderManager.createNewOrderId();
            
            //1.17. submitNewCashBasedOrder()
            MainAccount l_mainAccount = l_subAccount.getMainAccount();
            WEB3Crypt l_crypt = new WEB3Crypt();
            EqTypeOrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitNewCashBasedOrder(
                    l_subAccount,
                    l_newCashBasedOrderSpec,
                    l_newOrderId,
                    l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);
                    
            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                ErrorInfo l_errorInfo = l_orderSubmissionResult.getProcessingResult().getErrorInfo();
                throw new WEB3BaseException(
                    l_errorInfo,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_errorInfo.getErrorMessage());
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
    }

    /**
     * (insert�V�K���J�z����)<BR>
     * �V�K�������̌J�z�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����J�z�ꌏ�T�[�r�X�j�V�K���J�z�����쐬�v�Q�ƁB<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �J�z�Ώۂ̒����P�ʃI�u�W�F�N�g�i�V�K���j�B
     * @@roseuid 4110BAA50045
     */
    public void insertOpenMarginCarryOverOrder(EqTypeContractOpenOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertOpenMarginCarryOverOrder(EqTypeContractOpenOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            TradingModule l_traModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_traModule.getOrderManager();
            
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //1.1. �����P��.�����ID��null�̏ꍇ�̂�
            WEB3GentradeTrader l_trader = null;
            if (!l_orderUnitRow.getTraderIdIsNull())
            {
                l_trader = (WEB3GentradeTrader)l_finObjectManager.getTrader(l_orderUnitRow.getTraderId());
            }
            
            //1.2. create�V�K���������e()
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            EqTypeProduct l_product =
                (EqTypeProduct)l_traModule.getProductManager().getProduct(
                    l_orderUnitRow.getProductId());
                    
            EqtypeProductRow l_productRow =
                (EqtypeProductRow)l_product.getDataSourceObject();

            boolean l_isLong;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_isLong = true;
            }
            else
            {
                l_isLong = false;
            }
            
            OrderUnit l_firstOrderUnit = l_orderManager.getFirstOrderUnit(l_orderUnit);
            WEB3MarginOpenContractOrderSpec l_marginOpenContractOrderSpec =
                WEB3MarginOpenContractOrderSpec.createOpenMarginOrderSpec(
                    l_trader,
                    l_isLong,
                    l_productRow.getProductCode(),
                    l_market.getMarketCode(),
                    (l_orderUnitRow.getQuantity()
                        - l_orderUnitRow.getExecutedQuantity()),
                    l_orderUnitRow.getLimitPrice(),
                    l_orderUnitRow.getExecutionConditionType(),
                    l_orderUnitRow.getExpirationDate(),
                    l_orderUnitRow.getTaxType(),
                    l_orderUnitRow.getPriceConditionType(),
                    l_orderUnitRow.getOrderConditionType(),
                    l_orderUnitRow.getOrderCondOperator(),
                    l_orderUnitRow.getStopOrderPrice(),
                    l_orderUnitRow.getWLimitPrice(),
                    l_orderUnitRow.getRepaymentType(),
                    l_orderUnitRow.getRepaymentNum(),
                    new Long(l_firstOrderUnit.getOrderUnitId()),
                    l_orderUnitRow.getWLimitExecCondType());
            
            //1.3. create�萔��()
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_traModule.getBizLogicProvider();
            WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(l_orderUnitRow.getOrderId());
            
            //1.6. get������()
            Date l_datBizDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
                
            //1.7. set������()
            l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
            
            //1.10. getSubAccount()
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_orderUnitRow.getAccountId(),
                    l_orderUnitRow.getSubAccountId());

            //calc�����������()
            //[calc�����������( )�F�����ݒ�d�l]
            //�萔�� : �쐬�����萔���I�u�W�F�N�g
            //�w�l : �V�K���������e.getLimitPrice()
            //�iW�w�l�j�����w�l : �V�K���������e.get�iW�w�l�j�����w�l()
            //�t�w�l��l : �V�K���������e.get�t�w�l��l()
            //���s���� : �V�K���������e.get���s����()
            //�iW�w�l�j���s���� : �V�K���������e.get�iW�w�l�j���s����()
            //�l�i���� : �V�K���������e.get�l�i����()
            //�������� : �V�K���������e.get��������()
            //�m�F���擾���� : null
            //is�X�g�b�v�����L�� : �g�����������}�l�[�W��.is�X�g�b�v�����L��(�����P��)
            //is���� : �V�K���������e.isShortOrder( )
            //�⏕���� : �擾�����⏕�����I�u�W�F�N�g
            //������� : �擾������������I�u�W�F�N�g
            //���� : �M�p�V�K���������e.getQuantity( )
            //��萔�� : 0
            //���v�����z : 0
            //isSkip���z�`�F�b�N : false�i�X�L�b�v���Ȃ��j�Œ�
            WEB3EquityEstimatedContractPrice l_contractAmountAtOrder =
                l_orderManager.calcContractAmountAtOrder(
                    l_commission,
                    l_marginOpenContractOrderSpec.getLimitPrice(),
                    l_marginOpenContractOrderSpec.getWLimitPrice(),
                    l_marginOpenContractOrderSpec.getStopOrderPrice(),
                    l_marginOpenContractOrderSpec.getExecConditionType(),
                    l_marginOpenContractOrderSpec.getWlimitExecCondType(),
                    l_marginOpenContractOrderSpec.getPriceConditionType(),
                    l_marginOpenContractOrderSpec.getOrderConditionType(),
                    null,
                    l_orderManager.isStopOrderValid(l_orderUnit),
                    l_marginOpenContractOrderSpec.isShortOrder(),
                    l_subAccount,
                    (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct(),
                    l_marginOpenContractOrderSpec.getQuantity(),
                    0.0D,
                    0.0D,
                    false);
            log.debug("������������F[" + l_contractAmountAtOrder + "]");

            //set�v�Z�P��()
            //calc�����������()�̖߂�l.get�v�Z�P��()�̖߂�l���Z�b�g����B
            l_marginOpenContractOrderSpec.setCalcUnitPrice(
                l_contractAmountAtOrder.getCalcUnitPrice());

            //1.15. set�����()
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //������F�@@calc�����������( ).get�T�Z�����( )�̖߂�l���Z�b�g
            l_marginOpenContractOrderSpec.setContractAmount(
                l_contractAmountAtOrder.getEstimatedContractPrice());

            //1.16. validate�V�K������()
            WEB3MarginNewOrderValidationResult l_orderValidationResult = 
                (WEB3MarginNewOrderValidationResult)l_orderManager.validateOpenContractOrder(
                    l_subAccount,
                    l_marginOpenContractOrderSpec);
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);                        
            }
            
            //1.17. calc�ϑ��萔��()
            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            
            //1.18. get�󔄂�K���Ώۃt���O()
            boolean l_strShortSellOrderFlag = l_orderValidationResult.getShortSellingRestraint();
            
            //1.19. �M�p�V�K���X�V�C���^�Z�v�^()
            WEB3MarginOpenMarginUpdateInterceptor l_openUpdateInterceptor =
                new WEB3MarginOpenMarginUpdateInterceptor(
                    l_marginOpenContractOrderSpec,
                    l_commission,
                    l_orderUnitRow.getOrderChanel(),
                    l_orderUnitRow.getOrderRootDiv(),
                    l_strShortSellOrderFlag);
                    
            //1.20. set�J�z�������P��()
            l_openUpdateInterceptor.setCarryoverOrderUnit(l_orderUnit);
            
            //1.21. validate����]��()
            WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_orderSpecIntercepter =
            {
                l_openUpdateInterceptor
            };
            Object[] l_orderSpec =
            {
                l_marginOpenContractOrderSpec
            };
            
            WEB3TPTradingPowerResult l_tpResult = null;
            l_tpResult = l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepter,
                l_orderSpec,
                l_orderUnit.getOrderType(),
                false);

            // throw�]�̓G���[�ڍ׏��
            // �]�̓G���[�̏�񂩂�G���[�R�[�h�����肵�Athrow����B
            l_orderManager.throwTpErrorInfo(l_tpResult, l_orderUnitRow.getOrderType());

            //1.22. setThreadLocalPersistenceEventInterceptor()
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_openUpdateInterceptor);
            
            //1.23. createNewOrderId( )
            long l_newOrderId = l_orderManager.createNewOrderId();
            
            //1.24. submitOpenContractOrder()
            MainAccount l_mainAccount = l_subAccount.getMainAccount();
            WEB3Crypt l_crypt = new WEB3Crypt();
            EqTypeOrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitOpenContractOrder(
                    l_subAccount,
                    l_marginOpenContractOrderSpec,
                    l_newOrderId,
                    l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);
            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                ErrorInfo l_errorInfo = l_orderSubmissionResult.getProcessingResult().getErrorInfo();
                throw new WEB3BaseException(
                    l_errorInfo,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_errorInfo.getErrorMessage());
            }
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (insert�ԍόJ�z����)<BR>
     * �ԍϒ����̌J�z�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����J�z�ꌏ�T�[�r�X�j�ԍόJ�z�����쐬�v�Q�ƁB<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �J�z�Ώۂ̒����P�ʃI�u�W�F�N�g�i�ԍρj�B
     * @@roseuid 4105BE8C0290
     */
    public void insertCloseMarginCarryOverOrder(EqTypeContractSettleOrderUnit l_orderUnit)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "insertCloseMarginCarryOverOrder(EqTypeContractSettleOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            TradingModule l_traModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_traModule.getOrderManager();
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_traModule.getPositionManager();
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //1.1. getContractsToClose()
            EqTypeClosingContractSpec[] l_specs = l_orderUnit.getContractsToClose();
            
            //1.2. ArrayList()
            ArrayList l_list = new ArrayList();
            
            //1.3. getContractsToClose( )�̖߂�l�i�������ԍώw����j�v�f��(index)���ALoop
            for (int i = 0; i < l_specs.length; i++)
            {
                //1.3.1. �����c���`�F�b�N
                //�i�P�j�ԍϐ��ʂ��Z�o����B
                EqtypeClosingContractSpecRow l_specRow =
                    (EqtypeClosingContractSpecRow)l_specs[i].getDataSourceObject();
                double l_returnQuantity =
                    l_specRow.getQuantity() - l_specRow.getExecutedQuantity();
                log.debug("�ԍϐ��ʁF[" + l_returnQuantity + "]");
                if (l_returnQuantity == 0D)
                {
                    continue;
                }
                
                //�i�Q�j�ԍω\�����c�����Z�o����B
                WEB3EquityContract l_contract =
                    (WEB3EquityContract)l_positionManager.getContract(
                        l_specs[i].getContractId());
                double l_posiblePosition =
                    l_contract.getQuantity()
                        - l_contract.getLockedQuantity();
                log.debug("�ԍω\�����c���F[" + l_posiblePosition + "]");
                
                //�i�R�j�ԍϐ��ʁ��ԍω\�����c���̏ꍇ�A�u�����c���s���G���[�v�̗�O��throw����B
                if (l_returnQuantity > l_posiblePosition)
                {
                    throw new WEB3BaseException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00808,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        WEB3EquityTypeOrderManagerReusableValidations.ORDER_ERROR_TAG
                        + WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR);
                }

                // validate���ϊ�������(����)
                // �����̌��ϊ����`�F�b�N���s���B
                WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResValidations =
                    (WEB3EquityTypeOrderManagerReusableValidations)
                        WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                l_orderMgrResValidations.validateCloseDateExcess(l_contract);

                //1.3.2. EqTypeSettleContractOrderEntry()
                EqTypeSettleContractOrderEntry l_contractOrderEntry =
                    new EqTypeSettleContractOrderEntry(
                        l_specs[i].getContractId(),
                        l_returnQuantity);
                        
                //1.3.3. add()
                l_list.add(l_contractOrderEntry);
            }
            
            //1.4. toArray()            
            EqTypeSettleContractOrderEntry[] l_entry =
                new EqTypeSettleContractOrderEntry[l_list.size()];
            l_list.toArray(l_entry);
            
            //1.5. �����P��.�����ID��null�̏ꍇ�̂�
            WEB3GentradeTrader l_trader = null;
            if (!l_orderUnitRow.getTraderIdIsNull())
            {
                l_trader = (WEB3GentradeTrader)l_finObjectManager.getTrader(l_orderUnitRow.getTraderId());
            }
            
            //1.6. create�ԍϒ������e()
            OrderUnit l_firstOrderUnit = l_orderManager.getFirstOrderUnit(l_orderUnit);
            WEB3MarginSettleContractOrderSpec l_settleContractOrderSpec =
                WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                    l_trader,
                    l_entry,
                    l_orderUnitRow.getLimitPrice(),
                    l_orderUnitRow.getExecutionConditionType(),
                    l_orderUnitRow.getExpirationDate(),
                    l_orderUnitRow.getTaxType(),
                    l_orderUnitRow.getPriceConditionType(),
                    l_orderUnitRow.getOrderConditionType(),
                    l_orderUnitRow.getOrderCondOperator(),
                    l_orderUnitRow.getStopOrderPrice(),
                    l_orderUnitRow.getWLimitPrice(),
                    l_orderUnitRow.getClosingOrderType(),
                    new Long(l_firstOrderUnit.getOrderUnitId()),
                    l_orderUnitRow.getWLimitExecCondType());
                    
            //1.7. getSubAccount()
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());
                        
            //1.8. validate�ԍϒ���()
            EqTypeNewOrderValidationResult l_orderValidationResult = 
                l_orderManager.validateSettleContractOrder(
                    l_subAccount,
                    l_settleContractOrderSpec);
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.9. create�萔��()
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_traModule.getBizLogicProvider();
            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(
                    l_orderUnitRow.getOrderId());
                    
            //1.10. isLimitOrder()
            boolean l_isLimitOrder = l_settleContractOrderSpec.isLimitOrder();
            
            //1.11. setIs�w�l()
            l_commission.setIsLimitPrice(l_isLimitOrder);
            
            //1.12. get������()
            Date l_datBizDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
                
            //1.13. set������()
            l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
            
            //1.14. get����()
            WEB3EquityContract l_equityContract =
                (WEB3EquityContract)l_positionManager.getContract(
                    l_entry[0].getContractId());
                    
            //1.15. getTradedProduct()
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_traModule.getProductManager();
            WEB3EquityTradedProduct l_tradedProduct = null;
            EqtypeContractRow l_contractRow =
                (EqtypeContractRow)l_equityContract.getDataSourceObject();
            try
            {
                l_tradedProduct =
                    (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                        l_contractRow.getProductId(),
                        l_contractRow.getMarketId());
            }
            catch (Exception l_exp)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00638,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exp.getMessage(),
                    l_exp);
            }
            
            //1.16. calc�T�Z���ϑ��v���()
            WEB3EquityRealizedProfitAndLossPrice l_profitAndLossPrice =
                l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    l_orderUnitRow.getLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_entry,
                    l_settleContractOrderSpec.getTotalQuantity(),
                    null,
                    0D,
                    0D,
                    false);
            log.debug("�T�Z���ϑ��v����F[" + l_profitAndLossPrice + "]");
            
            //1.17. �M�p�ԍύX�V�C���^�Z�v�^()
            WEB3MarginCloseMarginUpdateInterceptor l_interceptor =
                new WEB3MarginCloseMarginUpdateInterceptor(
                    l_settleContractOrderSpec,
                    l_commission,
                    l_profitAndLossPrice,
                    l_contractRow.getRepaymentType(),
                    l_contractRow.getRepaymentNum(),
                    l_orderUnitRow.getOrderChanel(),
                    l_orderUnitRow.getOrderRootDiv());

            //1.18. set�J�z�������P��()
            l_interceptor.setCarryoverOrderUnit(l_orderUnit);
            
            //1.19. setThreadLocalPersistenceEventInterceptor()
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_interceptor);
            
            //1.20. createNewOrderId()
            long l_newOrderId = l_orderManager.createNewOrderId();
            
            //1.21. submitSettleContractOrder()
            MainAccount l_mainAccount = l_subAccount.getMainAccount();
            WEB3Crypt l_crypt = new WEB3Crypt();
            EqTypeOrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitSettleContractOrder(
                    l_subAccount,
                    l_settleContractOrderSpec,
                    l_newOrderId,
                    l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);
            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                ErrorInfo l_errorInfo = l_orderSubmissionResult.getProcessingResult().getErrorInfo();
                throw new WEB3BaseException(
                        l_errorInfo,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_errorInfo.getErrorMessage());
            }
            
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
    }

    /**
     * (update�J�z������)<BR>
     * �����R���ŃG���[�ƂȂ����J�z�������̒����G���[���R�R�[�h�Ȃǂ��X�V����B<BR>
     * <BR>
     * �P�j�@@�J�z�������̒����G���[���R�R�[�h ��update����B<BR>
     * <BR>
     * �P�|�P�j�@@�ȉ��̏����ɊY������J�z�������̒����P�ʃ��R�[�h��update����B<BR>
     * �@@�@@<����><BR>
     * �@@�@@�@@�����P�ʃe�[�u��.�����P��ID = �p�����[�^.�����P��.�����P��ID<BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�@@�����P�ʃ��R�[�h.�����G���[���R�R�[�h = <BR>
     * �p�����[�^.�����G���[���R�R�[�h<BR>
     * �@@�@@�@@�����P�ʃ��R�[�h.�X�V���t = ���ݓ���<BR>
     * <BR>
     * �P�|�Q�j�@@�ȉ��̏����ɊY������J�z�������̒��������́A<BR>
     * �@@�@@�@@�@@�@@�ŏI�������R�[�h�̒����G���[���R�R�[�h ��update����B<BR>
     * <BR>
     * �@@�@@<����><BR>
     * �@@�@@�����e�[�u��.�����P��ID�@@���@@<BR>
     * �p�����[�^.�����P��.�����P��ID�@@����<BR>
     * �@@�@@�����e�[�u��.��������ԍ��@@���@@<BR>
     * �p�����[�^.�����P��.���������ŏI�ʔ�<BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�������R�[�h.�����G���[���R�R�[�h�@@���@@<BR>
     * �p�����[�^.�����G���[���R�R�[�h<BR>
     * �@@�@@�������R�[�h.�X�V���t�@@���@@���ݓ���<BR>
     * <BR>
     * �P�|�R�j�@@�ȉ��̏����ɊY������A<BR>
     * �J�z�������̒����i�w�b�_�j�̍X�V������update����B<BR>
     * <BR>
     * �@@�@@<����><BR>
     * �@@�@@�����i�w�b�_�j�e�[�u��.����ID�@@���@@�p�����[�^.�����P��.����ID<BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�����i�w�b�_�j���R�[�h.�X�V���t�@@���@@���ݓ���<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �J�z���̒����P��
     * @@param l_strOrderErrReasonCode - (�����G���[���R�R�[�h)<BR>
     * �����G���[���R
     * @@roseuid 4121D908036F
     */
    public void updateOriginalOrder(
        EqTypeOrderUnit l_orderUnit,
        String l_strOrderErrReasonCode) 
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "updateCarryOverOrder(OrderUnit, String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            EqtypeOrderUnitRow l_orderUnitRow =
                EqtypeOrderUnitDao.findRowByOrderUnitId(
                    l_orderUnit.getOrderUnitId());
                    
            EqtypeOrderUnitParams l_orderUnitParams =
                new EqtypeOrderUnitParams(l_orderUnitRow);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�����P�ʃ��R�[�h.�����G���[���R�R�[�h =�p�����[�^.�����G���[���R�R�[�h<BR>
            //�����P�ʃ��R�[�h.�X�V���t = ���ݓ���
            l_orderUnitParams.setErrorReasonCode(l_strOrderErrReasonCode);            
            l_orderUnitParams.setLastUpdatedTimestamp(
                l_finApp.getTradingSystem().getSystemTimestamp());
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);

            //�����e�[�u��.�����P��ID�@@���@@�p�����[�^.�����P��.�����P��ID�@@����
            //�����e�[�u��.��������ԍ��@@���@@�p�����[�^.�����P��.���������ŏI�ʔ�

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" order_unit_id = ? ");
            l_sbWhere.append(" and order_action_serial_no = ? ");

            Object[] l_objWhere =
            {
                new Long(l_orderUnitRow.getOrderUnitId()),
                new Integer(l_orderUnitRow.getLastOrderActionSerialNo())
            };
                    
            List l_lisResults =
                l_queryProcessor.doFindAllQuery(
                    EqtypeOrderActionRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    null,
                    l_objWhere);
                    
            EqtypeOrderActionParams l_orderActionParams =
                new EqtypeOrderActionParams((EqtypeOrderActionRow)l_lisResults.get(0));
            //�������R�[�h.�����G���[���R�R�[�h�@@���@@�p�����[�^.�����G���[���R�R�[�h
            //�������R�[�h.�X�V���t�@@���@@���ݓ���
            l_orderActionParams.setErrorReasonCode(l_strOrderErrReasonCode);
            l_orderActionParams.setLastUpdatedTimestamp(
                l_finApp.getTradingSystem().getSystemTimestamp());
            l_queryProcessor.doUpdateQuery(l_orderActionParams);
            
            //�����i�w�b�_�j�e�[�u��.����ID�@@���@@�p�����[�^.�����P��.����ID
            EqtypeOrderRow l_orderRow =
                EqtypeOrderDao.findRowByPk(l_orderUnitRow.getOrderId());
                
            //�����i�w�b�_�j���R�[�h.�X�V���t�@@���@@���ݓ���
            EqtypeOrderParams l_orderParams = new EqtypeOrderParams(l_orderRow);
            l_orderParams.setLastUpdatedTimestamp(
                l_finApp.getTradingSystem().getSystemTimestamp());
                
            l_queryProcessor.doUpdateQuery(l_orderParams);
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
    }
}
@
