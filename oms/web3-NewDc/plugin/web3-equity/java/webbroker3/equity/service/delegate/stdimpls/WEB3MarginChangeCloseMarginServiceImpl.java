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
filename	WEB3MarginChangeCloseMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������ԍσT�[�r�XImpl(WEB3MarginChangeCloseMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ������(���u) �V�K�쐬
Revesion History : 2004/12/15 �������F(SRA) �c�Č��Ή��ɂ��C��
Revesion History : 2005/01/06 �����a��(SRA) JavaDoc�C��
Revesion History : 2006/11/28 ��іQ (���u) ���f�� No.1021
Revesion History : 2007/06/14 �����q (���u) ���f�� 1172
Revesion History : 2007/08/08 ���n�m (���u) ���f�� 1192
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginChangeCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginChangeSettleContractOrderSpec;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeCompleteResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeConfirmRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeConfirmResponse;
import webbroker3.equity.message.WEB3MarginCommissionInfoUnit;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginRequestAdapter;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginService;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;


/**
 * �i�M�p��������ԍσT�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p��������ԍσT�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3MarginChangeCloseMarginServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginChangeCloseMarginService 
{ 
   /**
    * ���O�o�̓��[�e�B���e�B�B<BR>
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginServiceImpl.class);
    /**
    
    /**
     * @@roseuid 414006710168
     */
    public WEB3MarginChangeCloseMarginServiceImpl() 
    {
     
    }
    
    /**
     * �M�p��������ԍσT�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()���\�b�h�A<BR>
     * submit����()���\�b�h�̂����ꂩ���R�[������B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4058288B024F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";

        log.entering(STR_METHOD_NAME);

        if (l_request instanceof WEB3MarginCloseMarginChangeConfirmRequest)
        {
            return this.validateOrder((WEB3MarginCloseMarginChangeConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3MarginCloseMarginChangeCompleteRequest)
        {
            return this.submitOrder((WEB3MarginCloseMarginChangeCompleteRequest)l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + " __Error[���͒l���s���ł�]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (validate����)<BR>
     * �M�p����̒����ԍϔ����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p��������ԍσT�[�r�X�jvalidate�����P�v�y��<BR>
     * �u�i�M�p��������ԍσT�[�r�X�jvalidate�����Q�v�Q�ƁB<BR>
     * �u�M�p��������ԍ� / �i�M�p��������ԍσT�[�r�X�j�M�p�ԍϒ����������e�쐬�v<BR>
     *    (���������`�F�b�N)<BR>
     *     �����������������_���ȊO(*)�ŁA���A�M�p��������ԍσ��N�G�X�g�A�_�v�^.get��������( )��0�̏ꍇ�A<BR>
     *     ��O���X���[����B<BR>
     *     class: WEB3BusinessLayerException <BR>
     *     tag:   BUSINESS_ERROR_00650 <BR>
     * <BR>
     * @@param l_request - �M�p��������ԍϊm�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MarginCloseMarginChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4058288B026E
     */
    protected WEB3MarginCloseMarginChangeConfirmResponse validateOrder(
        WEB3MarginCloseMarginChangeConfirmRequest l_request)
        throws WEB3BaseException 
    { 
        final String STR_METHOD_NAME = "validateOrder(WEB3MarginCloseMarginChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate(); 

        //1.2. get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.4. get����()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tm.getPositionManager();
        long l_lngContractId = Long.parseLong(l_request.closeMarginContractUnits[0].id);
        WEB3EquityContract l_equityContract = null;
        try
        {
            l_equityContract =
                (WEB3EquityContract)l_positionManager.getContract(l_lngContractId);
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //1.7. get������()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.8. �M�p�ԍϒ����������e�쐬
        //1.8.1. create()
        WEB3MarginChangeCloseMarginRequestAdapter l_adapter = 
            WEB3MarginChangeCloseMarginRequestAdapter.create(l_request);
        //1.8.2. get��������()
        double l_dblOrderQuantity = l_adapter.getOrderQuantity();
        //1.8.3. getOrderUnits()
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnits(l_lngOrderId)[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        //1.8.4. ���������`�F�b�N
        String l_strclosingOrderType = l_orderUnitRow.getClosingOrderType(); 
        if (l_strclosingOrderType == null || 
            WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(l_strclosingOrderType) ||
            WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(l_strclosingOrderType) ||
            WEB3ClosingOrderDef.OPEN_DATE.equals(l_strclosingOrderType)) 
        {
            if (l_adapter.getOrderQuantity() == 0.0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00650,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        //1.8.5. create���ό����G���g��
        if (l_request.orderQuantity == null)
        {
            l_dblOrderQuantity = 0.0D; 
        }
        EqTypeSettleContractOrderEntry[] l_contractOrderEntry = 
            l_orderManager.createClosingContractEntry(
                l_orderUnit.getOrderUnitId(),
                l_dblOrderQuantity,
                l_request.closeMarginContractUnits);
        //1.8.6. EqTypeContractSettleChangeOrderUnitEntry()
        double l_dblAfterChangePrice;
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_dblAfterChangePrice = 0.0D;
        }
        else
        {
            l_dblAfterChangePrice = Double.parseDouble(l_request.limitPrice);
        }
        EqTypeContractSettleChangeOrderUnitEntry l_orderUnitEntry =
            new EqTypeContractSettleChangeOrderUnitEntry(
                l_orderUnit.getOrderUnitId(),
                l_dblAfterChangePrice,
                l_contractOrderEntry);
        //1.8.7. get���s����()
        EqTypeExecutionConditionType l_executeConType =
            l_adapter.getExecutionCondition();
        //1.8.8. is�o����܂Œ���()
        boolean l_isCarryOrder = l_adapter.isCarriedOrder();
        //1.8.9. create�ԍϒ����������e()
        String l_strModifiedOrderCondOperator;  // �����㔭���������Z�q
        double l_dblModifiedStopOrderPrice;     // ������t�w�l��l
        double l_dblModifiedWLimitPrice;        // ������iW�w�l�j�����w�l
        // ���N�G�X�g.���������敪���h�w��Ȃ��h�̏ꍇ
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_request.orderCondType))
        {
            l_strModifiedOrderCondOperator = "0";
            l_dblModifiedStopOrderPrice = 0.0D;
            l_dblModifiedWLimitPrice = 0.0D;
        }
        // ���N�G�X�g.���������敪���h�t�w�l�h�̏ꍇ
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strModifiedOrderCondOperator = l_request.stopOrderCondOperator;               
            l_dblModifiedStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            l_dblModifiedWLimitPrice = 0.0D;
        }
        // ���N�G�X�g.���������敪���hW�w�l�h�̏ꍇ�́A
        else
        {
            l_strModifiedOrderCondOperator = l_request.wlimitOrderCondOperator;
            l_dblModifiedStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);               
              
            if (l_request.wLimitPrice != null)
            {
                l_dblModifiedWLimitPrice =Double.parseDouble(l_request.wLimitPrice); 
            }
            else
            {
                l_dblModifiedWLimitPrice = 0.0D;
            }
        }
        // �����㒍��������
        // �M�p��������ԍσ��N�G�X�g�A�_�v�^.get�����㒍��������( )�̖߂�l�B
        Date l_datModifiedExpirationDate = l_adapter.getModifiedExpirationDate();

        WEB3MarginChangeSettleContractOrderSpec l_changeSettleContractOrderSpec = 
            WEB3MarginChangeSettleContractOrderSpec.createCloseMarginChangeOrderSpec(
                l_lngOrderId,
                l_orderUnitEntry,
                l_request.priceCondType,
                l_request.orderCondType,
                l_strModifiedOrderCondOperator,
                l_dblModifiedStopOrderPrice,
                l_dblModifiedWLimitPrice,
                l_executeConType,
                l_datModifiedExpirationDate,
                l_isCarryOrder,
                l_adapter.getModifiedWLimitExecCondType(),
                l_request.wlimitEnableStatusDiv);

        //1.9. validate�ԍϒ�������()
		EqTypeOrderValidationResult l_result = l_orderManager.validateChangeSettleContractOrder(
		l_subAccount,
		l_changeSettleContractOrderSpec);

        //throw�����R�����ʃG���[���(OrderValidationResult, �،����, String)
        //�����R�����ʁF�@@validate�ԍϒ�������()�̖߂�l
        //�،���ЁF�@@�⏕����.getInstitution()
        //�����R�[�h�F�@@����.getProduct().getProductCode()
        l_orderManager.throwOrderValidationResultErrorInfo(
            l_result,
            l_subAccount.getInstitution(),
            ((WEB3EquityProduct)(l_equityContract.getProduct())).getProductCode());

        //1.10. create�萔��()
        WEB3EquityBizLogicProvider l_bizLogicProvider = (WEB3EquityBizLogicProvider)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
        WEB3GentradeCommission l_commission =
            l_bizLogicProvider.createCommission(l_lngOrderId);
        
        //1.11. setIs�w�l()
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.orderPriceDiv))
        {
            l_commission.setIsLimitPrice(true);
        }
        else
        {
            l_commission.setIsLimitPrice(false);
        }
        
        //1.12. getTradedProduct()
        WEB3EquityTradedProduct l_tradedProduct =
            (WEB3EquityTradedProduct)l_equityContract.getTradedProduct();
        
        //1.13. get���������ڍ�()
        EqTypeContractSettleChangeOrderUnitEntry l_contractSettleChangeOrderUnitEntry = 
            l_changeSettleContractOrderSpec.getChangeOrderUnitEntry();
        
        //1.14. getAfterChangeTotalQuantity()
        double l_dblTotalQuantity =
            l_contractSettleChangeOrderUnitEntry.getAfterChangeTotalQuantity();
        
        //1.15. calc�T�Z���ϑ��v���()
        WEB3EquityRealizedProfitAndLossPrice l_realizedProfitAndLossPrice = 
            l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                l_commission,
                l_dblAfterChangePrice,
                l_subAccount,
                l_tradedProduct,
                l_contractOrderEntry,
                l_dblTotalQuantity,
                l_orderUnit,
                0D,
                0D,
                false);
        
        //1.16. createResponse()
        WEB3MarginCloseMarginChangeConfirmResponse l_response =
            (WEB3MarginCloseMarginChangeConfirmResponse)l_request.createResponse();
        
        //1.17. ���ό����G���g���iEqtypeSettleContractOrderEntry[]�j�v�f����Loop����
        WEB3MarginContractUnit l_contractUnit;
        long l_lngContractOrderId;
        double l_dblCloseQuantity;
        List l_arrayList = new ArrayList();
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tm.getProductManager();
        for (int i = 0;i < l_contractOrderEntry.length;i++)
        {
            //1.17.1. �M�p�����������()
            l_contractUnit = new WEB3MarginContractUnit();
            //1.17.2. getContractId()
            l_lngContractOrderId = l_contractOrderEntry[i].getContractId();
            //1.17.3. getQuantity()
            l_dblCloseQuantity = l_contractOrderEntry[i].getQuantity();
            //1.17.4. get����()
            WEB3EquityContract l_contract = null;
            try
            {
                l_contract = 
                    (WEB3EquityContract)l_positionManager.getContract(l_lngContractOrderId);
            }
            catch (NotFoundException l_ne)
            {
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //1.17.5. getOpenDate()
            Date l_openDate = l_contract.getOpenDate();
            //1.17.6. getQuantity()
            double l_dblQuantity = l_contract.getQuantity();
            //1.17.7. getContractPrice()
            double l_dblcontractPrice = l_contract.getContractPrice();
            //1.17.8. get�����()
            double l_dblcontractAmount = l_contract.getContractAmount(l_dblCloseQuantity);
            //1.17.9. get�]�����v�i�������o��l���j()
            double l_dblCalcUnitPrice = 0.0D;
            if (!l_orderUnitEntry.isAfterChangePriceMarket())
            {
                l_dblCalcUnitPrice =
                    l_productManager.getCurrentPrice(
                        (EqTypeTradedProduct)l_contract.getTradedProduct());
            }
            else
            {
                l_dblCalcUnitPrice = l_realizedProfitAndLossPrice.getCalcUnitPrice();
            }
            double l_appraisalProfitOrLoss =
                l_contract.getAppraisalProfitOrLossExpenses(
                    l_dblCalcUnitPrice,
                    l_dblCloseQuantity,
                    l_orderUnit.getOrderUnitId());
            //1.17.10. get�ԍϖ��ϐ���()
            double l_dblClosingExecutedQuantity =
                l_contract.getClosingExecutedQuantity(l_orderUnit.getOrderUnitId());
            
            //1.17.11. �v���p�e�B�Z�b�g
            // �M�p�����������.ID
            l_contractUnit.id = String.valueOf(l_lngContractOrderId);
            // �M�p�����������.����
            l_contractUnit.openDate = WEB3DateUtility.toDay(l_openDate);
            // �M�p�����������.���P��
            l_contractUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_dblcontractPrice);
            // �M�p�����������.������
            l_contractUnit.contractQuantity =WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            // �M�p�����������.�����
            l_contractUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblcontractAmount);
            // �M�p�����������.�]�����v
            l_contractUnit.appraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_appraisalProfitOrLoss);
            // �M�p�����������.��������
            l_contractUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_dblCloseQuantity);
            // �M�p�����������.���o������
            l_contractUnit.partContQuantity = WEB3StringTypeUtility.formatNumber(l_dblClosingExecutedQuantity);
            // �M�p�����������.���Ϗ���
            l_contractUnit.settlePriority = WEB3StringTypeUtility.formatNumber(i + 1);
            l_arrayList.add(l_contractUnit);
        }
        WEB3MarginContractUnit[] l_Contractunits = new WEB3MarginContractUnit[l_arrayList.size()]; 
        l_arrayList.toArray(l_Contractunits);
        
        //1.18. �M�p����萔�����
        WEB3MarginCommissionInfoUnit l_commissionInfoUnit = new WEB3MarginCommissionInfoUnit();
        
        //1.19. calc�����
        double l_dblcalSalesTax =
            l_bizLogicProvider.calcSalesTax(
                l_commission.getCommission(),
                l_commission.getOrderBizDate(),
                l_subAccount);
        
        //1.20. �v���p�e�B�Z�b�g
        //�M�p����萔�����.�萔���R�[�X
        l_commissionInfoUnit.commissionCourse = l_commission.getCommissionCourseDiv();
        // �M�p����萔�����.�萔��
        l_commissionInfoUnit.commission = WEB3StringTypeUtility.formatNumber(l_commission.getCommission());
        // �M�p����萔�����.�萔�������
        l_commissionInfoUnit.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_dblcalSalesTax);
        
        //1.21. get�s��ǌx���s��()
        EqtypeContractRow l_contractRow =
            (EqtypeContractRow)l_equityContract.getDataSourceObject();
        String[] l_strmessageSuspensions =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_subAccount.getWeb3GenBranch(),
                ProductTypeEnum.EQUITY,
                l_contractRow.getRepaymentType());

        //1.22. get�T�Z���ϑ��v���()
        double l_dblEstimatedRealizedProfitAndLossAmount = 
                l_realizedProfitAndLossPrice.getEstimatedRealizedProfitAndLossAmount();
        
        //1.23. get�v�Z�P��()
        double l_dblCalcUnitPrice = l_realizedProfitAndLossPrice.getCalcUnitPrice();
        
        //1.24. is�C���T�C�_�[�x���\��(�⏕���� : �⏕����, ����ID : long)
        boolean l_isInsiderMessageSuspension = l_orderManager.isInsiderMessageSuspension(
            l_subAccount,
            l_orderUnitRow.getProductId());
        
        //1.25. �v���p�e�B�Z�b�g
        //���X�|���X.�m�F��������
        l_response.checkDate =  l_datBizDate;
        // ���X�|���X.�T�Z��n���
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_dblEstimatedRealizedProfitAndLossAmount);
        // ���X�|���X.����I���x���s��R�[�h�ꗗ
        l_response.messageSuspension = l_strmessageSuspensions;
        // ���X�|���X.�������׈ꗗ
        l_response.contractUnits = l_Contractunits;
        // ���X�|���X.�萔�����
        l_response.commissionInfo = l_commissionInfoUnit;
        // ���X�|���X.�m�F���P��
        l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_dblCalcUnitPrice);
        // ���X�|���X.�C���T�C�_�[�x���\���t���O
        l_response.insiderWarningFlag = l_isInsiderMessageSuspension;
        // ���X�|���X.�����L������
        l_response.expirationDate = l_adapter.getExpirationDate();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * �M�p��������ԍϒ�����o�^����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p��������ԍσT�[�r�X�jsubmit�����P�v�y��<BR>
     * �u�i�M�p��������ԍσT�[�r�X�jsubmit�����Q�v�Q�ƁB<BR>
     * �u�M�p��������ԍ� / �i�M�p��������ԍσT�[�r�X�j�M�p�ԍϒ����������e�쐬�v<BR>
     *    (���������`�F�b�N)<BR>
     *     �����������������_���ȊO(*)�ŁA���A�M�p��������ԍσ��N�G�X�g�A�_�v�^.get��������( )��0�̏ꍇ�A<BR>
     *     ��O���X���[����B<BR>
     *     class: WEB3BusinessLayerException <BR>
     *     tag:   BUSINESS_ERROR_00650 <BR>
     * <BR>
     * @@param l_request - �M�p��������ԍϊ������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MarginCloseMarginChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4058288B027E
     */
    protected WEB3MarginCloseMarginChangeCompleteResponse submitOrder(
        WEB3MarginCloseMarginChangeCompleteRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3MarginCloseMarginChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate(); 

        //1.2. get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.3. get�㗝���͎�()
        Trader l_trader = this.getTrader();
        
        //1.4. get����()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tm.getPositionManager();
        long l_lngContractId = Long.parseLong(l_request.closeMarginContractUnits[0].id);
        WEB3EquityContract l_equityContract = null;
        try
        {
            l_equityContract =
                (WEB3EquityContract)l_positionManager.getContract(l_lngContractId);
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.7. get������()
        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.8. �M�p�ԍϒ����������e�쐬
        //1.8.1. create()
        WEB3MarginChangeCloseMarginRequestAdapter l_adapter = 
            WEB3MarginChangeCloseMarginRequestAdapter.create(l_request);
        //1.8.2. get��������()
        double l_dblOrderQuantity = l_adapter.getOrderQuantity();
        //1.8.3. getOrderUnits()
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnits(l_lngOrderId)[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        //1.8.4. ���������`�F�b�N
        String l_strclosingOrderType = l_orderUnitRow.getClosingOrderType(); 
        if (l_strclosingOrderType == null || 
            WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(l_strclosingOrderType) ||
            WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(l_strclosingOrderType) ||
            WEB3ClosingOrderDef.OPEN_DATE.equals(l_strclosingOrderType)) 
        {
            if (l_adapter.getOrderQuantity() == 0.0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00650,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        //1.8.5. create���ό����G���g��
        if (l_request.orderQuantity == null)
        {
            l_dblOrderQuantity = 0.0D; 
        }
        EqTypeSettleContractOrderEntry[] l_contractOrderEntry = 
            l_orderManager.createClosingContractEntry(
                l_orderUnit.getOrderUnitId(),
                l_dblOrderQuantity,
                l_request.closeMarginContractUnits);
        //1.8.6. EqTypeContractSettleChangeOrderUnitEntry()
        double l_dblAfterChangePrice;
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
        {
            l_dblAfterChangePrice = 0.0D;
        }
        else
        {
            l_dblAfterChangePrice = Double.parseDouble(l_request.limitPrice);
        }
        EqTypeContractSettleChangeOrderUnitEntry l_orderUnitEntry =
            new EqTypeContractSettleChangeOrderUnitEntry(
                l_orderUnit.getOrderUnitId(),
                l_dblAfterChangePrice,
                l_contractOrderEntry);
        //1.8.7. get���s����()
        EqTypeExecutionConditionType l_executeConType =
            l_adapter.getExecutionCondition();
        //1.8.8. is�o����܂Œ���()
        boolean l_isCarryOrder = l_adapter.isCarriedOrder();
        //1.8.9. create�ԍϒ����������e()
        String l_strModifiedOrderCondOperator;  // �����㔭���������Z�q
        double l_dblModifiedStopOrderPrice;     // ������t�w�l��l
        double l_dblModifiedWLimitPrice;        // ������iW�w�l�j�����w�l
        // ���N�G�X�g.���������敪���h�w��Ȃ��h�̏ꍇ
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_request.orderCondType))
        {
            l_strModifiedOrderCondOperator = "0";
            l_dblModifiedStopOrderPrice = 0.0D;
            l_dblModifiedWLimitPrice = 0.0D;
        }
        // ���N�G�X�g.���������敪���h�t�w�l�h�̏ꍇ
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strModifiedOrderCondOperator = l_request.stopOrderCondOperator;               
            l_dblModifiedStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            l_dblModifiedWLimitPrice = 0.0D;
        }
        // ���N�G�X�g.���������敪���hW�w�l�h�̏ꍇ�́A
        else
        {
            l_strModifiedOrderCondOperator = l_request.wlimitOrderCondOperator;
            l_dblModifiedStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);               
              
            if (l_request.wLimitPrice != null)
            {
                l_dblModifiedWLimitPrice =Double.parseDouble(l_request.wLimitPrice); 
            }
            else
            {
                l_dblModifiedWLimitPrice = 0.0D;
            }
        }
        // �����㒍��������
        // �M�p��������ԍσ��N�G�X�g�A�_�v�^.get�����㒍��������( )�̖߂�l�B
        Date l_datModifiedExpirationDate = l_adapter.getModifiedExpirationDate();

        WEB3MarginChangeSettleContractOrderSpec l_changeSettleContractOrderSpec = 
            WEB3MarginChangeSettleContractOrderSpec.createCloseMarginChangeOrderSpec(
                l_lngOrderId,
                l_orderUnitEntry,
                l_request.priceCondType,
                l_request.orderCondType,
                l_strModifiedOrderCondOperator,
                l_dblModifiedStopOrderPrice,
                l_dblModifiedWLimitPrice,
                l_executeConType,
                l_datModifiedExpirationDate,
                l_isCarryOrder,
                l_adapter.getModifiedWLimitExecCondType(),
                l_request.wlimitEnableStatusDiv);

        //1.9. validate�ԍϒ�������()
        
		EqTypeOrderValidationResult l_result = l_orderManager.validateChangeSettleContractOrder(
		l_subAccount,
		l_changeSettleContractOrderSpec);

        //throw�����R�����ʃG���[���(OrderValidationResult, �،����, String)
        //�����R�����ʁF�@@validate�ԍϒ�������()�̖߂�l
        //�،���ЁF�@@�⏕����.getInstitution()
        //�����R�[�h�F�@@����.getProduct().getProductCode()
        l_orderManager.throwOrderValidationResultErrorInfo(
            l_result,
            l_subAccount.getInstitution(),
            ((WEB3EquityProduct)(l_equityContract.getProduct())).getProductCode());

        //1.10. create�萔��()
        WEB3EquityBizLogicProvider l_bizLogicProvider = (WEB3EquityBizLogicProvider)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
        WEB3GentradeCommission l_commission =
            l_bizLogicProvider.createCommission(l_lngOrderId);
        
        //1.11. setIs�w�l()
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.orderPriceDiv))
        {
            l_commission.setIsLimitPrice(true);
        }
        else
        {
            l_commission.setIsLimitPrice(false);
        }
        
        //1.12. getTradedProduct()
        WEB3EquityTradedProduct l_tradedProduct =
            (WEB3EquityTradedProduct)l_equityContract.getTradedProduct();
        
        //1.13. get���������ڍ�()
        EqTypeContractSettleChangeOrderUnitEntry l_contractSettleChangeOrderUnitEntry = 
            l_changeSettleContractOrderSpec.getChangeOrderUnitEntry();
        
        //1.14. getAfterChangeTotalQuantity()
        double l_dblTotalQuantity =
            l_contractSettleChangeOrderUnitEntry.getAfterChangeTotalQuantity();
        
        //1.15. calc�T�Z���ϑ��v���()
        if (l_request.checkPrice == null)
        {
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                l_request.checkPrice = "0";
            }
            else
            {
                l_request.checkPrice = l_request.limitPrice;
            }
        }
        WEB3EquityRealizedProfitAndLossPrice l_realizedProfitAndLossPrice = 
            l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                l_commission,
                Double.parseDouble(l_request.checkPrice),
                l_subAccount,
                l_tradedProduct,
                l_contractOrderEntry,
                l_dblTotalQuantity,
                l_orderUnit,
                0D,
                0D,
                false);
        
        //�Z�L�����e�B�T�[�r�X���擾
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class); 
        // �����o�H�敪�擾
        String l_strOrderRootDiv =
            l_opLoginSec.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
                
        //1.16. �M�p�ԍϒ����X�V�C���^�Z�v�^()
        WEB3MarginChangeCloseMarginUpdateInterceptor l_updateInterceptor =
            new WEB3MarginChangeCloseMarginUpdateInterceptor(
                l_changeSettleContractOrderSpec,
                l_realizedProfitAndLossPrice,
                l_strOrderRootDiv,
                (WEB3GentradeTrader)this.getTrader());

        //1.17. setThreadLocalPersistenceEventInterceptor()
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        //1.19. submitChangeSettleContractOrder()
        String l_strTradingPassword;
        if (l_trader == null)
        {
            l_strTradingPassword = l_request.password;
        }
        else
        {
            WEB3Crypt l_crypt = new WEB3Crypt();
            l_strTradingPassword =
                l_crypt.decrypt(l_subAccount.getMainAccount().getTradingPassword());
        }
        EqTypeOrderSubmissionResult l_orderSubmissionResult =
            l_orderManager.submitChangeSettleContractOrder(
                l_subAccount,
                l_changeSettleContractOrderSpec,
                l_strTradingPassword,
                true);
        if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            throw new WEB3BusinessLayerException(
                l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        List l_lisOrderUnits = null;
        if (l_orderManager.isReserveOrderConfirmRequire(l_orderUnit))
        {
            WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationEqTypeOrderUpdateService.class);
            l_lisOrderUnits =
                l_updateService.getOpenReserveEqtypeOrderUnits(l_orderUnit.getOrderId());
        }
        
        //1.20. �]�͍Čv�Z
        WEB3TPTradingPowerService l_tradingpowerService =
        (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        l_tradingpowerService.reCalcTradingPower(l_subAccount);

        //1.21. createResponse()
        WEB3MarginCloseMarginChangeCompleteResponse l_response = 
            (WEB3MarginCloseMarginChangeCompleteResponse)l_request.createResponse();
        
        //1.22. is�C���T�C�_�[�x���\��()
		l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnits(l_lngOrderId)[0];
        l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        boolean l_isInsiderMessageSuspension =
            l_orderManager.isInsiderMessageSuspension(
            l_subAccount,
            l_orderUnitRow.getProductId());
        
        //1.23. �v���p�e�B�Z�b�g
        //l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
              
        // ���X�|���X.�X�V����
        l_response.lastUpdatedTimestamp = l_orderUnitRow.getLastUpdatedTimestamp();

        // ���X�|���X.���ʔԍ�
        l_response.orderActionId = String.valueOf(l_orderUnitRow.getOrderId()); 
        // ���X�|���X.�C���T�C�_�[�x���\���t���O
        l_response.insiderWarningFlag = l_isInsiderMessageSuspension;
        // ���X�|���X.�����L������
        l_response.expirationDate = l_adapter.getExpirationDate();
        if (l_lisOrderUnits != null)
        {
            l_response.succSettingFlag = true;
        }
        else
        {
            l_response.succSettingFlag = false;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
