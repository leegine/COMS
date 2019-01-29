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
filename	WEB3EquityChangeOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������T�[�r�X(WEB3EquityChangeOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/11 ����� (���u) �V�K�쐬
Revesion History : 2004/12/15 �������F(SRA) �c�Č��Ή��ɂ��C��
Revesion History : 2005/01/06 �����a��(SRA) JavaDoc�C��
Revesion History : 2006/11/03 �����F(���u) ���f�� 1002 1026
Revesion History : 2007/06/14 �����q (���u) ���f�� 1174
Revesion History : 2007/08/07 ���n�m (���u)���f��No.1192
Revesion History : 2007/12/04 ��іQ�@@(���u)���f��No.1228
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityChangeOrderUnitEntry;
import webbroker3.equity.WEB3EquityChangeOrderSpec;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityOrderManagerChangeOrderEventInterceptor;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.equity.message.WEB3EquityChangeCompleteResponse;
import webbroker3.equity.message.WEB3EquityChangeConfirmResponse;
import webbroker3.equity.message.WEB3EquityCommissionInfoUnit;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderRequestAdapter;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.define.WEB3TPTradingPowerErrorDivDef;
import webbroker3.tradingpower.define.WEB3TPResultAttentionObjectionTypeDef;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�������������T�[�r�XImpl�j�B<BR>
 * <BR>
 * �������������T�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3EquityChangeOrderServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityChangeOrderService
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeOrderServiceImpl.class);

    /**
     * @@roseuid 409EFFBB038B
     */
    public WEB3EquityChangeOrderServiceImpl()
    {

    }

    /**
     * (validate��������)<BR>
     * <BR>
     * ���������o�m�F���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���������T�[�r�X�j���������m�F�v�Q�ƁB<BR>
     * @@param requestData - �N���C�A���g����̓��̓f�[�^
     * @@throws WEB3BaseException
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 4021ADCA02F9
     */
    public WEB3GenResponse validateChangeOrder(WEB3EquityChangeConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateChangeOrder(WEB3EquityChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate()
        l_request.validate();

        //1.2. get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //1.3. get�㗝���͎�()
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();

        //1.4. get���O�C���`���l��()
        String l_strLoginChannel = this.getLoginChannel();

        //1.5. validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.6. create()
        WEB3EquityChangeOrderRequestAdapter l_adapter =
            WEB3EquityChangeOrderRequestAdapter.create(l_request);

        //1.7. create�������������l�ڍ�()
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            WEB3EquityChangeOrderUnitEntry.createChangeOrderUnitEntry(l_adapter);

        //1.8. ���������������e()
        WEB3EquityChangeOrderSpec l_changeOrderSpec =
            new WEB3EquityChangeOrderSpec(
                l_adapter.getRequestOrderId(),
                l_changeOrderUnitEntry,
                l_subAccount.getInstitution().getInstitutionCode(),
                l_strLoginChannel,
                l_trader);

        //1.9. validate����������������()
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        EqTypeOrderValidationResult l_res =
            l_orderMgr.validateChangeOrder(l_subAccount, l_changeOrderSpec);

        //throw�����R�����ʃG���[���(OrderValidationResult, �،����, String)
        //�����R�����ʁF�@@validate����������������()�̖߂�l
        //�،���ЁF�@@�⏕����.getInstitution()
        //�����R�[�h�F�@@���������������e.get�����������P��().����ID�ɊY�����銔������.�����R�[�h
        long l_lngProductId =
            l_changeOrderSpec.getOrgChangeOrderUnit().getProduct().getProductId();
        WEB3EquityProductManager l_equityProductManager =
            (WEB3EquityProductManager)l_tradingMod.getProductManager();
        WEB3EquityProduct l_equityProduct = null;

        try
        {
            l_equityProduct =
                (WEB3EquityProduct)l_equityProductManager.getProduct(l_lngProductId);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        String l_strProductCode = l_equityProduct.getProductCode();

        l_orderMgr.throwOrderValidationResultErrorInfo(
            l_res,
            l_subAccount.getInstitution(),
            l_strProductCode);

        //1.10. create�����������e()
        WEB3EquityNewCashBasedOrderSpec l_orderSpec = l_changeOrderSpec.createOrderSpec();

        //1.11. get�萔��()
        WEB3GentradeCommission l_commission = l_orderSpec.getCommission();

        //1.13. isSellOrder
        boolean l_blnSellOrder = l_orderSpec.isSellOrder();

        //1.14. get�����������P��()
        OrderUnit l_orderUnit = l_changeOrderSpec.getOrgChangeOrderUnit();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //1.15. getTradedProduct()
        WEB3EquityTradedProduct l_tradedProduct = null;
        WEB3EquityProductManager l_productMgr =
            (WEB3EquityProductManager)l_tradingMod.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectMgr =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        try
        {
            WEB3EquityProduct l_product =
                (WEB3EquityProduct)l_productMgr.getProduct(l_orderUnitRow.getProductId());
            Market l_market = l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productMgr.getTradedProduct(
                        l_subAccount.getInstitution(),
                        l_product.getProductCode(),
                        l_market.getMarketCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("__TradedProduct���擾�ł��܂���__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //calc�T�Z��n���(�萔��, double, double, double, 
        //EqTypeExecutionConditionType, EqTypeExecutionConditionType,
        //String, String, String, boolean, SubAccount, �������, double, 
        //boolean, double, double, boolean)
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
            l_orderMgr.calcEstimateDeliveryAmount(
                l_commission,
                l_orderSpec.getLimitPrice(),
                l_orderSpec.getWLimitPriceChange(),
                l_orderSpec.getStopLimitPriceBasePrice(),
                l_orderSpec.getExecConditionType(),
                l_orderSpec.getWlimitExecCondType(),
                l_orderSpec.getPriceConditionType(),
                l_orderSpec.getOrderCond(),
                null,
                l_changeOrderUnitEntry.isStopOrderEnable(),
                this.getSubAccount(),
                l_tradedProduct,
                l_orderSpec.getQuantity(),
                l_blnSellOrder,
                l_orderUnit.getExecutedQuantity(),
                l_orderUnit.getExecutedAmount(),
                false);

        // set�����P��
        l_orderSpec.setOrderUnitPrice(l_estimatedDeliveryPrice.getCalcUnitPrice());

        //1.19. set�T�Z��n���()
        l_orderSpec.setEstimateDeliveryAmount(
            l_estimatedDeliveryPrice.getEstimateDeliveryAmount());

        //1.20. �������������C���^�Z�v�^()
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_securityService.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        WEB3EquityOrderManagerChangeOrderEventInterceptor l_interceptor =
            new WEB3EquityOrderManagerChangeOrderEventInterceptor(
                l_strOrderRootDiv,
                l_trader);

        //1.21. set�����������e                
        l_interceptor.setEquityOrderSpec(l_changeOrderSpec.getNewCachBasedOrderSpec());
        
        //1.22. validate����]��()
        WEB3TPTradingPowerService l_tradingpowerService =
        (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        Object[] l_orderSpecIntercepters = { l_interceptor };
        Object[] l_orderSpecs = {l_changeOrderSpec };
        
        WEB3TPTradingPowerResult l_tpResult = null;
        l_tpResult = l_tradingpowerService.validateTradingPower(
            l_subAccount,
            l_orderSpecIntercepters,
            l_orderSpecs,
            l_orderUnit.getOrderType(),
            false);
            
        //1.24. ����]�͌���.is����t���O( )==false�̏ꍇ
        if (l_tpResult.isResultFlg() == false)
        {
            //1.24.1. ��K���G���[�i����]�͌���.����]�̓G���[���.����]�̓G���[�敪=="��K���G���["�j�̏ꍇ
            if (WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR.equals(l_tpResult.getTpErrorInfo().tradinPowerErrorDiv))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01928,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //1.24.2. �a����s���G���[�i����]�͌���.����]�̓G���[���.����]�̓G���[�敪��"��K���G���["�j�̏ꍇ
            else
            {
                //1.24.2.1. �������̏ꍇ
                if (!l_blnSellOrder)
                {
                    //1.24.2.2. get�a����s�����i���t�j()
                    String l_strLackAccountBalanceInfo =
                        l_orderMgr.getLackAccountBalanceInfoBuy(l_tpResult);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01929,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strLackAccountBalanceInfo);
                }
                //1.24.2.2. �������̏ꍇ
                else
                {
                    //1.24.2.2. get�a����s�����i���t�j()
                    String l_strLackAccountBalanceInfo =
                        l_orderMgr.getLackAccountBalanceInfoSell(
                            l_tpResult,
                            l_adapter.getRequestOrderQuantity());
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01930,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strLackAccountBalanceInfo);
                }
            }
        }
            
        //1.25. createResponse
        WEB3EquityChangeConfirmResponse l_changeResponse =
            (WEB3EquityChangeConfirmResponse)l_request.createResponse();

        //1.26. get�s��ǌx���s��()
        String[] l_tradeOpenMarket =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_subAccount.getWeb3GenBranch(),
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);
        
        //1.27. is�C���T�C�_�[�x���\��()
        boolean l_isInsiderMessageSuspension = 
            l_orderMgr.isInsiderMessageSuspension(
                l_subAccount,
                l_orderUnitRow.getProductId());
        
        //1.28. ���蒍���̏ꍇ�̂ݎ��s
        if (l_blnSellOrder)
        {
            //1.28.1. calc�T�Z�뉿�P��()
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingMod.getBizLogicProvider();
            
            double l_estimatedBookPrice = l_bizLogicProvider.calcEstimatedBookPrice(
                l_orderUnitRow.getProductId(),
                l_subAccount,
                l_orderUnitRow.getTaxType());
            // ���X�|���X.�T�Z�뉿�P��
            l_changeResponse.estimatedBookPrice =
                WEB3StringTypeUtility.formatNumber(l_estimatedBookPrice);
        }
        
        // ���X�|���X.�m�F��������
        Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        l_changeResponse.checkDate = WEB3DateUtility.toDay(l_bizDate);

        // ���X�|���X.�T�Z��n���
        l_changeResponse.estimatedPrice =
            WEB3StringTypeUtility.formatNumber(l_orderSpec.getEstimateDeliveryAmount());
        
        // ���X�|���X.����I���x���s��R�[�h�ꗗ
        l_changeResponse.messageSuspension = l_tradeOpenMarket;
        
        // ���X�|���X.���o������
        double l_executedQuantity = l_orderUnitRow.getExecutedQuantity();
        if (l_executedQuantity == 0.0D)
        {
            l_changeResponse.partContQuantity = null;
        }
        else
        {
            l_changeResponse.partContQuantity =
                WEB3StringTypeUtility.formatNumber(l_executedQuantity);
        }

        // ���X�|���X.�萔�����
        WEB3EquityCommissionInfoUnit l_commissionInfo = new WEB3EquityCommissionInfoUnit();
        l_commissionInfo.commissionCourse = l_commission.getCommissionCourseDiv();
        // ���X�|���X.�萔�����.�萔��
        l_commissionInfo.commission =
            WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFee());
        // ���X�|���X.�萔�����.�萔�������
        l_commissionInfo.commissionConsumptionTax =
            WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFeeTax());
        l_changeResponse.commissionInfo = l_commissionInfo;
        
        // ���X�|���X.�m�F���P��
        l_changeResponse.checkPrice = l_estimatedDeliveryPrice.getCheckGetCurrentPrice();
        
        // ���X�|���X.�C���T�C�_�[�x���\���t���O
        l_changeResponse.insiderWarningFlag = l_isInsiderMessageSuspension;
        
        //���X�|���X.���ӕ����\���敪
        //���X�|���X.�a����s���z
        //����]�͌���.get���ӕ����\���敪 == "3�F�a����s�����ӕ����\��" �܂���
        //����]�͌���.get���ӕ����\���敪 == "1�F�����s�����ӕ����\��"�̏ꍇ
        //����]�͌��ʃN���X�̓����ڂ��Z�b�g����
        l_changeResponse.attentionObjectionType = l_tpResult.getAttentionObjectionType();
        if (WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION.equals(
                l_tpResult.getAttentionObjectionType())
            || WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION.equals(
                l_tpResult.getAttentionObjectionType()))
        {
            l_changeResponse.accountBalanceInsufficiency =
                WEB3StringTypeUtility.formatNumber(l_tpResult.getLackAccountBalance());
        }
        
        // ���X�|���X.�����L������
        l_changeResponse.expirationDate = l_adapter.getOrderExpirationDate();

        log.exiting(STR_METHOD_NAME);
        return l_changeResponse;
    }

    /**
     * (submit��������)<BR>
     * <BR>
     * ������������������o�^����B<BR> 
     * �V�[�P���X�}�u�i���������T�[�r�X�j���������X�V�v�Q�ƁB<BR>
     * @@param requestData - �N���C�A���g����̓��̓f�[�^
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 4021AE040098
     */
    public WEB3GenResponse submitChangeOrder(WEB3EquityChangeCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitChangeOrder(WEB3EquityChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1. validate()
        l_request.validate();

        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.2. get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //1.3. get�㗝���͎�()
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();

        //1.4. get���O�C���`���l��()
        String l_strLoginChannel = this.getLoginChannel();

        //1.5. validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.6. create()
        WEB3EquityChangeOrderRequestAdapter l_adapter =
            WEB3EquityChangeOrderRequestAdapter.create(l_request);

        //1.7. create�������������l�ڍ�()
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            WEB3EquityChangeOrderUnitEntry.createChangeOrderUnitEntry(l_adapter);

        //1.8. ���������������e()
        WEB3EquityChangeOrderSpec l_changeOrderSpec =
            new WEB3EquityChangeOrderSpec(
                l_adapter.getRequestOrderId(),
                l_changeOrderUnitEntry,
                l_subAccount.getInstitution().getInstitutionCode(),
                l_strLoginChannel,
                l_trader);

        //1.9. validate����������������()
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        EqTypeOrderValidationResult l_res =
            l_orderMgr.validateChangeOrder(l_subAccount, l_changeOrderSpec);

        //throw�����R�����ʃG���[���(OrderValidationResult, �،����, String)
        //�����R�����ʁF�@@validate����������������()�̖߂�l
        //�،���ЁF�@@�⏕����.getInstitution()
        //�����R�[�h�F�@@���������������e.get�����������P��().����ID�ɊY�����銔������.�����R�[�h
        long l_lngProductId =
            l_changeOrderSpec.getOrgChangeOrderUnit().getProduct().getProductId();
        WEB3EquityProductManager l_equityProductManager =
            (WEB3EquityProductManager)l_tradingMod.getProductManager();
        WEB3EquityProduct l_equityProduct = null;

        try
        {
            l_equityProduct =
                (WEB3EquityProduct)l_equityProductManager.getProduct(l_lngProductId);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        String l_strProductCode = l_equityProduct.getProductCode();

        l_orderMgr.throwOrderValidationResultErrorInfo(
            l_res,
            l_subAccount.getInstitution(),
            l_strProductCode);

        //1.10. create�����������e()
        WEB3EquityNewCashBasedOrderSpec l_orderSpec = l_changeOrderSpec.createOrderSpec();

        //1.11. get�萔��()
        WEB3GentradeCommission l_commission = l_orderSpec.getCommission();

        // getQuantity()
        double  l_dbQuantity = l_orderSpec.getQuantity();

        //1.14. get�����������P��()
        OrderUnit l_orderUnit = l_changeOrderSpec.getOrgChangeOrderUnit();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //1.15. getTradedProduct()
        WEB3EquityTradedProduct l_tradedProduct = null;
        WEB3EquityProductManager l_productMgr =
            (WEB3EquityProductManager)l_tradingMod.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectMgr =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        try
        {
            WEB3EquityProduct l_product =
                (WEB3EquityProduct)l_productMgr.getProduct(l_orderUnitRow.getProductId());
            Market l_market = l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productMgr.getTradedProduct(
                        l_subAccount.getInstitution(),
                        l_product.getProductCode(),
                        l_market.getMarketCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("__TradedProduct���擾�ł��܂���__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //�����������e.isSellOrder( ) 
        boolean l_blnSellOrder = l_orderSpec.isSellOrder();
        
        // calc�T�Z��n���(�萔��, double, double, double, 
        //EqTypeExecutionConditionType, EqTypeExecutionConditionType, 
        //String, String, String, boolean, SubAccount, �������, 
        //double, boolean, double, double, boolean)
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
            l_orderMgr.calcEstimateDeliveryAmount(
                l_commission,
                l_orderSpec.getLimitPrice(),
                l_orderSpec.getWLimitPriceChange(),
                l_orderSpec.getStopLimitPriceBasePrice(),
                l_orderSpec.getExecConditionType(),
                l_orderSpec.getWlimitExecCondType(),
                l_orderSpec.getPriceConditionType(),
                l_orderSpec.getOrderCond(),
                l_request.checkPrice,
                l_changeOrderUnitEntry.isStopOrderEnable(),
                this.getSubAccount(),
                l_tradedProduct,
                l_dbQuantity,
                l_blnSellOrder,
                l_orderUnit.getExecutedQuantity(),
                l_orderUnit.getExecutedAmount(),
                false);
                    
        //1.16. set�����P��()
        l_orderSpec.setOrderUnitPrice(l_estimatedDeliveryPrice.getCalcUnitPrice());
        
        //1.18. set�T�Z��n���()
        l_orderSpec.setEstimateDeliveryAmount(
            l_estimatedDeliveryPrice.getEstimateDeliveryAmount());
        
        //1.19. �������������C���^�Z�v�^()
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_securityService.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        WEB3EquityOrderManagerChangeOrderEventInterceptor l_interceptor =
            new WEB3EquityOrderManagerChangeOrderEventInterceptor(
                l_strOrderRootDiv,
                l_trader);

        //1.20. set�����������e                
        l_interceptor.setEquityOrderSpec(l_changeOrderSpec.getNewCachBasedOrderSpec());
        
        //1.21. validate����]��()
        WEB3TPTradingPowerService l_tradingpowerService =
        (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        Object[] l_orderSpecIntercepters = { l_interceptor };
        Object[] l_orderSpecs = { l_changeOrderSpec };
        
        WEB3TPTradingPowerResult l_tpResult = null;
        l_tpResult = l_tradingpowerService.validateTradingPower(
            l_subAccount,
            l_orderSpecIntercepters,
            l_orderSpecs,
            l_orderUnit.getOrderType(),
            true);
            
        //1.23. ����]�͌���.is����t���O( )==false�̏ꍇ
        if (l_tpResult.isResultFlg() == false)
        {
            //1.23.1. ��K���G���[�i����]�͌���.����]�̓G���[���.����]�̓G���[�敪=="��K���G���["�j�̏ꍇ
            if (WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR.equals(l_tpResult.getTpErrorInfo().tradinPowerErrorDiv))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01928,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //1.23.2. �a����s���G���[�i����]�͌���.����]�̓G���[���.����]�̓G���[�敪��"��K���G���["�j�̏ꍇ
            else
            {
                //1.23.2.1. �������̏ꍇ
                if (!l_blnSellOrder)
                {
                    //1.23.2.2. get�a����s�����i���t�j()
                    String l_strLackAccountBalanceInfo =
                        l_orderMgr.getLackAccountBalanceInfoBuy(l_tpResult);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01929,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strLackAccountBalanceInfo);
                }
                //1.23.2.2. �������̏ꍇ
                else
                {
                    //1.23.2.2. get�a����s�����i���t�j()
                    String l_strLackAccountBalanceInfo =
                        l_orderMgr.getLackAccountBalanceInfoSell(
                            l_tpResult,
                            l_adapter.getRequestOrderQuantity());
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01930,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strLackAccountBalanceInfo);
                }
            }
        }
        
        //1.24. setThreadLocalPersistenceEventInterceptor
        l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //1.25. submit����������������()
        EqTypeOrderSubmissionResult l_submitRes =
            l_orderMgr.submitChangeOrder(
                l_subAccount,
                l_changeOrderSpec,
                l_request.password,
                true);
        if (l_submitRes.getProcessingResult().isFailedResult())
        {
            log.error(STR_METHOD_NAME + " __Error[���������X�V]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_submitRes.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.26. createResponse()
        WEB3EquityChangeCompleteResponse l_changeResponse =
            (WEB3EquityChangeCompleteResponse)l_request.createResponse();

        //1.27. is�C���T�C�_�[�x���\��()
        l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        boolean l_isInsiderMessageSuspension = 
            l_orderMgr.isInsiderMessageSuspension(
                l_subAccount,
                l_orderUnitRow.getProductId());            
        
        List l_lisOrderUnits = null;
        if (l_orderMgr.isReserveOrderConfirmRequire((EqTypeOrderUnit)l_orderUnit))
        {
            WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationEqTypeOrderUpdateService.class);
            l_lisOrderUnits =
                l_updateService.getOpenReserveEqtypeOrderUnits(l_orderUnit.getOrderId());
        }
        
        // ���X�|���X.�X�V����
        l_changeResponse.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        
        // ���X�|���X.���ʔԍ�
        l_changeResponse.orderActionId = String.valueOf(l_orderUnitRow.getOrderId()); 

        // ���X�|���X.�C���T�C�_�[�x���\���t���O
        l_changeResponse.insiderWarningFlag = l_isInsiderMessageSuspension;
        
        if (l_lisOrderUnits != null)
        {
            l_changeResponse.succSettingFlag = true;
        }
        else
        {
            l_changeResponse.succSettingFlag = false;
        }
        
        // ���X�|���X.�����L������
        l_changeResponse.expirationDate = l_adapter.getOrderExpirationDate();

        log.exiting(STR_METHOD_NAME);
        return l_changeResponse;
    }

    /**
     * (execute)<BR>
     * <BR>
     * ���������������������s����B<BR>
     * <BR>
     * �i�V�X�e���������j�K�C�h 4.4.�Ɩ����W�b�N�@@�Q�Ɓj<BR>
     * <BR>
     * �P�j�@@���{���\�b�h����<BR>
     * �@@�|���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�����������������m�F���N�G�X�g�v�̏ꍇ�́Athis.���������m�F( )���R�[������B<BR>
     * �@@�|���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�����������������������N�G�X�g�v�̏ꍇ�́Athis.���������o�^( )���R�[������B<BR>
     * <BR>
     * �Q�j�@@���\�b�h�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_request - (request)
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4021B24E023E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            getClass().getName() + "execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request instanceof WEB3EquityChangeConfirmRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�����������������m�F���N�G�X�g�v�̏ꍇ
            log.exiting(STR_METHOD_NAME);
            return this.validateChangeOrder(
                (WEB3EquityChangeConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3EquityChangeCompleteRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�����������������������N�G�X�g�v�̏ꍇ  
            log.exiting(STR_METHOD_NAME);
            return this.submitChangeOrder(
                (WEB3EquityChangeCompleteRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + " __Error[���͒l���s���ł�]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
}
@
