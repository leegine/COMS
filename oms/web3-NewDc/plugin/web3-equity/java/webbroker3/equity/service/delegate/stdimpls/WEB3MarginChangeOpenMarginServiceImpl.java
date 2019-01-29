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
filename	WEB3MarginChangeOpenMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������V�K���T�[�r�XImpl(WEB3MarginChangeOpenMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 Ḗ@@��(���u) �V�K�쐬
Revesion History : 2004/12/15 �������F(SRA) �c�Č��Ή��ɂ��C��
Revesion History : 2005/01/06 �����a��(SRA) JavaDoc�C��
Revesion History : 2006/11/28 ��іQ (���u) ���f�� No.1009
Revesion History : 2007/06/14 �����q (���u) ���f�� No.1170
Revesion History : 2007/08/08 �ؕk (���u)   ���f�� No.1192
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderValidationResult;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginChangeOpenMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginChangeOrderSpec;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.message.WEB3MarginCommissionInfoUnit;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeCompleteResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeConfirmRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MarginChangeOpenMarginRequestAdapter;
import webbroker3.equity.service.delegate.WEB3MarginChangeOpenMarginService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;

/**
 * �i�M�p��������V�K���T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p��������V�K���T�[�r�X�����N���X
 * @@author �@@��
 * @@version 1.0
 */
public class WEB3MarginChangeOpenMarginServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginChangeOpenMarginService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3MarginChangeOpenMarginServiceImpl.class);
    /**
     * @@roseuid 4140067003AC
     */
    public WEB3MarginChangeOpenMarginServiceImpl() 
    {
     
    }
    
    /**
     * �M�p��������V�K���T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()���\�b�h�A<BR>
     * submit����()���\�b�h�̂����ꂩ���R�[������B
     * @@param l_request - ���N�G�X�g
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40581E0B0145
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "EXECUTE";
        if (l_request == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            return this.validateOrder((WEB3MarginOpenMarginChangeConfirmRequest)l_request);   
        }
        else if (l_request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            return this.submitOrder((WEB3MarginOpenMarginChangeCompleteRequest)l_request);    
        }
        throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80018,
            this.getClass().getName() + "." + STR_METHOD_NAME);
    }
    
    /**
     * (validate����)<BR>
     * �M�p����̒����V�K�������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p��������V�K���T�[�r�X�jvalidate�����P�v�y��<BR>
     * �u�i�M�p��������V�K���T�[�r�X�jvalidate�����Q�v�Q�ƁB<BR>
     * @@param l_request - �M�p��������V�K�������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.margin.message.WEB3MarginOpenMarginChangeConfirmResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40581E0B0165
     */
    protected WEB3MarginOpenMarginChangeConfirmResponse validateOrder(
        WEB3MarginOpenMarginChangeConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3MarginOpenMarginChangeConfirmRequest)";
        if (l_request == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1. validate()
        l_request.validate();
        
        //1.2. get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.3. get�㗝���͎�()
        Trader l_trader = this.getTrader();
        
        //1.4. getOrderUnits()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);     
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tm.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.id)); 
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //1.7. get������()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.8. �M�p�V�K�������������e�쐬
        //1.8.1. �M�p��������V�K�����N�G�X�g�A�_�v�^�I�u�W�F�N�g�𐶐�����B
        WEB3MarginChangeOpenMarginRequestAdapter l_requestAdapter = WEB3MarginChangeOpenMarginRequestAdapter.create(l_request);
        //1.8.2. get�����㒍������()
        double l_dblQuantity = l_requestAdapter.getModifiedOrderQuantity();
        //1.8.3. get�����㎷�s����()
        EqTypeExecutionConditionType l_type = l_requestAdapter.getModifiedExecutionConditionType();
        //1.8.4. get�����㒍��������()
        Date l_datExpirationDate = WEB3DateUtility.toDay(l_requestAdapter.getModifiedExpirationDate());
        //1.8.5. get�����㔭���������Z�q()
        String l_strOrderCondOperator = l_requestAdapter.getModifiedOrderCondOperator();
        //1.8.6. get������t�w�l��l()
        double l_dblStopOrderPrice = l_requestAdapter.getModifiedStopOrderPrice();
        //1.8.7. is�o����܂Œ���()
        boolean l_blnIsCarriedOrder = l_requestAdapter.isCarriedOrder();

        //get������iW�w�l�j���s����
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType =
            l_requestAdapter.getModifiedWLimitExecCondType();

        //1.8.8. create�V�K�������������e
        String l_strWLimitPrice = l_request.wLimitPrice;
        if (l_strWLimitPrice == null)
        {
            l_strWLimitPrice = "0.0";
        }        
        long l_lngOrderId = l_orderUnitRow.getOrderId();
        String l_strLimitPrice = l_request.limitPrice;
        if (l_strLimitPrice == null)
        {
            l_strLimitPrice = "0.0";            
        }
        WEB3MarginChangeOrderSpec l_spec =
            WEB3MarginChangeOrderSpec.createOpenMarginChangeOrderSpec(
                l_lngOrderId,
                l_orderUnitRow.getOrderUnitId(),
                l_dblQuantity,
                Double.parseDouble(l_strLimitPrice),
                l_type,            
                l_datExpirationDate,
                l_request.priceCondType,
                l_request.orderCondType,
                l_strOrderCondOperator,
                l_dblStopOrderPrice,
                Double.parseDouble(l_strWLimitPrice),
                l_eqTypeExecutionConditionType,
                l_blnIsCarriedOrder,
                l_trader,
                l_request.wlimitEnableStatusDiv
            );
        
        //1.8.9. create�萔��()
        WEB3EquityBizLogicProvider l_provider = (WEB3EquityBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();          
        WEB3GentradeCommission l_commission = l_provider.createCommission(l_lngOrderId);
        
        //1.8.11.1. getTradeProduct()
        WEB3EquityTradedProduct l_tradeProduct = null;
        try
        {
            l_tradeProduct =(WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
        }
        catch (RuntimeSystemException l_rse)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����P��ID=[" + Long.toString(l_orderUnit.getOrderUnitId()) + "]�̒����P�ʂɕR�t�������������",
                l_rse);
        }
         
        //1.8.11.2. get�V�K�������������e�ڍ�()                 
        EqTypeChangeOrderUnitEntry l_entry =
            (EqTypeChangeOrderUnitEntry)l_spec.getChangeOrderUnitEntry();
        //1.8.11.5. getAfterChangePrice()
        double l_dblChangePrice = l_entry.getAfterChangePrice();

        //is������(EqTypeOrderUnit)
        boolean l_blnIsSellOrder = l_orderManager.isSellOrder(l_orderUnit);

        //1.8.15. get�����㒍������()
        double l_dblOrderQuantity = l_spec.getModifiedOrderQuantity();
        //1.8.16. getExecutedQuantity()
        double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
        //1.8.17. getExecutedAmmount()
        double l_dblExecutedAmmount = l_orderUnit.getExecutedAmount();
        //calc�����������
        //�萔���F�@@create�萔��()�̖߂�l
        //�w�l�F�@@getAfterChangePrice()�̖߂�l
        //�iW�w�l�j�����w�l�F�@@�M�p�V�K�������������e.get������iW�w�l�j�����w�l()�̖߂�l
        //�t�w�l��l�F�@@�M�p�V�K�������������e.get������t�w�l��l()�̖߂�l
        //���s�����F�@@�M�p�V�K�������������e.get�����㎷�s����()�̖߂�l
        //�iW�w�l�j���s�����F�@@�M�p�V�K�������������e.get������iW�w�l�j���s����()�̖߂�l
        //�l�i�����F�@@�M�p�V�K�������������e.get������l�i����()�̖߂�l
        //���������F�@@�M�p�V�K�������������e.get�����㔭������()�̖߂�l
        //�m�F���擾�����F
        //�@@�m�F�����̏ꍇ��null
        //�@@���������̏ꍇ�̓��N�G�X�g�f�[�^.�m�F���P��
        //is�X�g�b�v�����L���F�@@�M�p�V�K�������������e.is�X�g�b�v�����L��()�̖߂�l
        //is�����F�@@is������()�̖߂�l
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��������F�@@getTradedProduct()�̖߂�l
        //�����F�@@get�����㒍������()�̖߂�l
        //��萔�ʁF�@@getExecutedQuantity()�߂�l
        //���v�����z�F�@@getExecutedAmount()�̖߂�l
        //isSkip���z�`�F�b�N�F�@@false
        WEB3EquityEstimatedContractPrice l_equityEstimatedContractPrice =
            l_orderManager.calcContractAmountAtOrder(
                l_commission,
                l_dblChangePrice,
                l_spec.getModifiedWLimitPrice(),
                l_spec.getModifiedStopOrderPrice(),
                l_spec.getModifiedExecutionType(),
                l_spec.getModifiedWlimitExecCondType(),
                l_spec.getModifiedPriceConditionType(),
                l_spec.getModifiedOrderConditionType(),
                null,
                l_spec.isStopOrderEnable(),
                l_blnIsSellOrder,
                l_subAccount,
                l_tradeProduct,
                l_dblOrderQuantity,
                l_dblExecutedQuantity,
                l_dblExecutedAmmount,
                false);

        //set������v�Z�P��()
        l_spec.setModifiedCalcUnitPrice(l_equityEstimatedContractPrice.getCalcUnitPrice());
        
        //set�����㌚���()
        l_spec.setModifiedContractAmount(l_equityEstimatedContractPrice.getEstimatedContractPrice());
        
        //1.9. validate����������������()
        WEB3EquityOrderValidationResult l_result =
            (WEB3EquityOrderValidationResult)l_orderManager.validateChangeOrder(
                l_subAccount,
                l_spec);

        //throw�����R�����ʃG���[���(OrderValidationResult, �،����, String)
        //�����R�����ʁF�@@validate����������������()�̖߂�l
        //�،���ЁF�@@�⏕����.getInstitution()
        //�����R�[�h�F�@@�����P��.����ID�ɊY�����銔������.�����R�[�h
        long l_lngProductId = l_orderUnitRow.getProductId();

        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tm.getProductManager();
        WEB3EquityProduct l_product;
        try
        {
            l_product = (WEB3EquityProduct)l_productManager.getProduct(l_lngProductId);
        }
        catch (NotFoundException l_exNFE)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_exNFE);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exNFE.getMessage(),
                l_exNFE);
        }

        String l_strProductCode = l_product.getProductCode();

        l_orderManager.throwOrderValidationResultErrorInfo(
            l_result,
            l_subAccount.getInstitution(),
            l_strProductCode);

        //validate����]��(�⏕����, �M�p�V�K�������������e, boolean, ���������R������)
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //�M�p�V�K�������������e�F�@@�쐬���� �M�p�V�K�������������e�I�u�W�F�N�g
        //�]�͍X�V�t���O�F�@@false�i�m�F��)
        //�����R�����ʁF�@@validate����������������()�̖߂�l
        WEB3TPTradingPowerResult l_tpResult =
            this.validateTradingPower(
                l_subAccount,
                l_spec,
                false,
                l_result);
        
        //1.13. calc�ϑ��萔��
        l_provider.calcCommission(l_commission,l_subAccount);
        
        //1.14. create response
        WEB3MarginOpenMarginChangeConfirmResponse l_response =
            (WEB3MarginOpenMarginChangeConfirmResponse)l_request.createResponse();
        
        //1.15. �M�p����萔�����()
        WEB3MarginCommissionInfoUnit l_commissionInfo = new WEB3MarginCommissionInfoUnit();
        
        //1.16. calc�����
        double l_dblCommision = l_commission.getCommission();
        double l_dblSaleTax = l_provider.calcSalesTax(
            l_dblCommision,
            l_commission.getOrderBizDate(),
            l_subAccount);
        
        //1.17. �v���p�e�B�Z�b�g
        // �萔���R�[�X
        l_commissionInfo.commissionCourse = l_commission.getCommissionCourseDiv();
        // �萔��
        l_commissionInfo.commission = WEB3StringTypeUtility.formatNumber(l_dblCommision);
        // �萔�������
        l_commissionInfo.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_dblSaleTax);
        
        //1.18. get�s��ǌx���s��()
        String[] l_strMarkets = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(),
            ProductTypeEnum.EQUITY,
            l_orderUnitRow.getRepaymentType());
        
        //1.20. get�����㌚���()
        double l_dblContractAmmount2 = l_spec.getModifiedContractAmount();
        
        //1.21. is�C���T�C�_�[�x���\��()
        boolean l_isInsiderMessageSuspension = l_orderManager.isInsiderMessageSuspension(
            l_subAccount,
            l_orderUnitRow.getProductId());
        
        //1.22. ���X�|���X�f�[�^�Ɉȉ��̒ʂ�v���p�e�B��ݒ肷��B
        //���X�|���X.�m�F��������
        l_response.checkDate = WEB3DateUtility.toDay(l_datBizDate);
        //���X�|���X.�T�Z��n���
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmmount2);
        //���X�|���X.����I���x���s��R�[�h�ꗗ
        l_response.messageSuspension = l_strMarkets;
        //���X�|���X.���o������
        l_response.partContQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
        //���X�|���X.�萔�����
        l_response.commissionInfo = l_commissionInfo;
        // ���X�|���X.�m�F���P��
        l_response.checkPrice = l_equityEstimatedContractPrice.getCheckGetCurrentPrice();
        // ���X�|���X.�C���T�C�_�[�x���\���t���O
        l_response.insiderWarningFlag = l_isInsiderMessageSuspension;
        if (l_tpResult != null)
        {
            l_response.attentionObjectionType =
                l_tpResult.getAttentionObjectionType();
        }
        // ���X�|���X.�����L������
        l_response.expirationDate = l_requestAdapter.getExpirationDate();
        
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * �M�p��������V�K��������o�^����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p��������V�K���T�[�r�X�jsubmit�����P�v�y��<BR>
     * �u�i�M�p��������V�K���T�[�r�X�jsubmit�����Q�v�Q�ƁB
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p��������V�K�������������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MarginOpenMarginChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40581E0B0184
     */
    protected WEB3MarginOpenMarginChangeCompleteResponse submitOrder(
        WEB3MarginOpenMarginChangeCompleteRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOrder";
        if (l_request == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1. validate
        l_request.validate();
        
        //1.2. get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.3 get�㗝���͎�( )
        Trader l_trader = this.getTrader();
        
        //1.4. getOrderUnits
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);     
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tm.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.id)); 
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //1.7. get������( )
        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.8. �M�p�V�K�������������e�쐬
        //1.8.1. �M�p��������V�K�����N�G�X�g�A�_�v�^�I�u�W�F�N�g�𐶐�����B
        WEB3MarginChangeOpenMarginRequestAdapter l_requestAdapter = WEB3MarginChangeOpenMarginRequestAdapter.create(l_request);
        //1.8.2. get�����㒍������()
        double l_dblQuantity = l_requestAdapter.getModifiedOrderQuantity();
        //1.8.3. get�����㎷�s����()
        EqTypeExecutionConditionType l_type = l_requestAdapter.getModifiedExecutionConditionType();
        //1.8.4. get�����㒍��������()
        Date l_datExpirationDate = WEB3DateUtility.toDay(l_requestAdapter.getModifiedExpirationDate());
        //1.8.5. get�����㔭���������Z�q()
        String l_strOrderCondOperator = l_requestAdapter.getModifiedOrderCondOperator();
        //1.8.6. get������t�w�l��l()
        double l_dblStopOrderPrice = l_requestAdapter.getModifiedStopOrderPrice();
        //1.8.7. is�o����܂Œ���()
        boolean l_blnIsCarriedOrder = l_requestAdapter.isCarriedOrder();
        //get������iW�w�l�j���s����
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType =
            l_requestAdapter.getModifiedWLimitExecCondType();

        //1.8.8. create�V�K�������������e
        String l_strWLimitPrice = l_request.wLimitPrice;
        if (l_strWLimitPrice == null)
        {
            l_strWLimitPrice = "0.0";
        }        
        long l_lngOrderId = l_orderUnitRow.getOrderId();
        String l_strLimitPrice = l_request.limitPrice;
        if (l_strLimitPrice == null)
        {
            l_strLimitPrice = "0.0";            
        }
        WEB3MarginChangeOrderSpec l_spec =
            WEB3MarginChangeOrderSpec.createOpenMarginChangeOrderSpec(
                l_lngOrderId,
                l_orderUnitRow.getOrderUnitId(),
                l_dblQuantity,
                Double.parseDouble(l_strLimitPrice),
                l_type,            
                l_datExpirationDate,
                l_request.priceCondType,
                l_request.orderCondType,
                l_strOrderCondOperator,
                l_dblStopOrderPrice,
                Double.parseDouble(l_strWLimitPrice),
                l_eqTypeExecutionConditionType,
                l_blnIsCarriedOrder,
                l_trader,
                l_request.wlimitEnableStatusDiv
            );
        
        //1.8.9. create�萔��(����ID : long)
        WEB3EquityBizLogicProvider l_provider = (WEB3EquityBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();          
        WEB3GentradeCommission l_commission = l_provider.createCommission(l_lngOrderId);
        
        WEB3EquityTradedProduct l_tradeProduct = null;
        try
        {
	        l_tradeProduct =
	        (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
	    }
        catch (RuntimeSystemException l_rse)
        {
	        throw new WEB3BusinessLayerException(
	        WEB3ErrorCatalog.BUSINESS_ERROR_01966,
	        this.getClass().getName() + "." + STR_METHOD_NAME,
	        "�����P��ID=[" + Long.toString(l_orderUnit.getOrderUnitId()) + "]�̒����P�ʂɕR�t�������������",
	        l_rse);
	    }
                         
        EqTypeChangeOrderUnitEntry l_entry =
        (EqTypeChangeOrderUnitEntry)l_spec.getChangeOrderUnitEntry();
        double l_dblChangePrice = l_entry.getAfterChangePrice();

        //is������(EqTypeOrderUnit)
        boolean l_blnIsSellOrder = l_orderManager.isSellOrder(l_orderUnit);

        //1.8.15. get�����㒍������()
        double l_dblOrderQuantity = l_spec.getModifiedOrderQuantity();
        //1.8.16. getExecutedQuantity()
        double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
        //1.8.17. getExecutedAmmount()
        double l_dblExecutedAmmount = l_orderUnit.getExecutedAmount();

        //calc�����������
        //�萔���F�@@create�萔��()�̖߂�l
        //�w�l�F�@@getAfterChangePrice()�̖߂�l
        //�iW�w�l�j�����w�l�F�@@�M�p�V�K�������������e.get������iW�w�l�j�����w�l()�̖߂�l
        //�t�w�l��l�F�@@�M�p�V�K�������������e.get������t�w�l��l()�̖߂�l
        //���s�����F�@@�M�p�V�K�������������e.get�����㎷�s����()�̖߂�l
        //�iW�w�l�j���s�����F�@@�M�p�V�K�������������e.get������iW�w�l�j���s����()�̖߂�l
        //�l�i�����F�@@�M�p�V�K�������������e.get������l�i����()�̖߂�l
        //���������F�@@�M�p�V�K�������������e.get�����㔭������()�̖߂�l
        //�m�F���擾�����F
        //�@@�m�F�����̏ꍇ��null
        //�@@���������̏ꍇ�̓��N�G�X�g�f�[�^.�m�F���P��
        //is�X�g�b�v�����L���F�@@�M�p�V�K�������������e.is�X�g�b�v�����L��()�̖߂�l
        //is�����F�@@is������()�̖߂�l
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��������F�@@getTradedProduct()�̖߂�l
        //�����F�@@get�����㒍������()�̖߂�l
        //��萔�ʁF�@@getExecutedQuantity()�߂�l
        //���v�����z�F�@@getExecutedAmount()�̖߂�l
        //isSkip���z�`�F�b�N�F�@@false
        WEB3EquityEstimatedContractPrice l_equityEstimatedContractPrice =
            l_orderManager.calcContractAmountAtOrder(
                l_commission,
                l_dblChangePrice,
                l_spec.getModifiedWLimitPrice(),
                l_spec.getModifiedStopOrderPrice(),
                l_spec.getModifiedExecutionType(),
                l_spec.getModifiedWlimitExecCondType(),
                l_spec.getModifiedPriceConditionType(),
                l_spec.getModifiedOrderConditionType(),
                l_request.checkPrice,
                l_spec.isStopOrderEnable(),
                l_blnIsSellOrder,
                l_subAccount,
                l_tradeProduct,
                l_dblOrderQuantity,
                l_dblExecutedQuantity,
                l_dblExecutedAmmount,
                false);
        
        //set������v�Z�P��()
        l_spec.setModifiedCalcUnitPrice(l_equityEstimatedContractPrice.getCalcUnitPrice());
        
        //set�����㌚���()
        l_spec.setModifiedContractAmount(l_equityEstimatedContractPrice.getEstimatedContractPrice());
        
        //1.9. validate����������������
        WEB3EquityOrderValidationResult l_result =
            (WEB3EquityOrderValidationResult)l_orderManager.validateChangeOrder(
                l_subAccount,
                l_spec);

        //throw�����R�����ʃG���[���(OrderValidationResult, �،����, String)
        //�����R�����ʁF�@@validate����������������()�̖߂�l
        //�،���ЁF�@@�⏕����.getInstitution()
        //�����R�[�h�F�@@�����P��.����ID�ɊY�����銔������.�����R�[�h
        long l_lngProductId = l_orderUnitRow.getProductId();

        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tm.getProductManager();
        WEB3EquityProduct l_product;
        try
        {
            l_product = (WEB3EquityProduct)l_productManager.getProduct(l_lngProductId);
        }
        catch (NotFoundException l_exNFE)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_exNFE);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exNFE.getMessage(),
                l_exNFE);
        }

        String l_strProductCode = l_product.getProductCode();

        l_orderManager.throwOrderValidationResultErrorInfo(
            l_result,
            l_subAccount.getInstitution(),
            l_strProductCode);

        this.validateTradingPower(
            l_subAccount,
            l_spec,
            true,
            l_result);
        
        //1.14. submit����������������
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
        OrderSubmissionResult l_orderSubmissionResult =
            l_orderManager.submitChangeOrder(
                l_subAccount,
                l_spec,
                l_strTradingPassword,
                true);
        if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            throw new WEB3BusinessLayerException(
                l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
		l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.id)); 
		l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
		l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //1.15. is�C���T�C�_�[�x���\��(�⏕���� : �⏕����, ����ID : long)
        boolean l_isInsiderMessageSuspension = l_orderManager.isInsiderMessageSuspension(
            l_subAccount,
            l_orderUnitRow.getProductId());
        
        //1.16. create response
        WEB3MarginOpenMarginChangeCompleteResponse l_response =
            (WEB3MarginOpenMarginChangeCompleteResponse)l_request.createResponse();

        List l_lisOrderUnits = null;
        if (l_orderManager.isReserveOrderConfirmRequire(l_orderUnit))
        {
            WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationEqTypeOrderUpdateService.class);
            l_lisOrderUnits =
                l_updateService.getOpenReserveEqtypeOrderUnits(l_orderUnit.getOrderId());
        }
        
        //1.17. �v���p�e�B�Z�b�g
        // ���X�|���X.�X�V����
        l_response.lastUpdatedTimestamp = l_orderUnitRow.getLastUpdatedTimestamp();
        // ���X�|���X.���ʔԍ�
        l_response.orderActionId = String.valueOf(l_orderUnitRow.getOrderId()); 
        // ���X�|���X.�C���T�C�_�[�x���\���t���O
        l_response.insiderWarningFlag = l_isInsiderMessageSuspension;
        if (l_lisOrderUnits != null)
        {
            l_response.succSettingFlag = true;
        }
        else
        {
            l_response.succSettingFlag = false;
        }
        // ���X�|���X.�����L������
        l_response.expirationDate = l_requestAdapter.getExpirationDate();

        return l_response;
    }
    
    /**
     * (validate����]��)<BR>
     * ����]�͂��`�F�b�N����B<BR>
     * �V�[�P���X�}�u�i�M�p��������V�K���T�[�r�X�jvalidate����]�́v���Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_orderSpec - (�M�p�V�K�������������e)<BR>
     * �M�p�V�K�������������e�I�u�W�F�N�g�B
     * @@param l_blnUpdateFlg - (�]�͍X�V�t���O)<BR>
     * �]�͍X�V�t���O�B<BR>
     * �ifalse�F�@@�m�F���Atrue�F�@@�������j
     * @@param l_validationResult - (�����R������)<BR>
     * ���������R�����ʃI�u�W�F�N�g
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginChangeOrderSpec l_orderSpec,
        boolean l_blnUpdateFlg,
        WEB3EquityOrderValidationResult l_validationResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradingPower(WEB3GentradeSubAccount, WEB3MarginChangeOrderSpec, boolean, WEB3EquityOrderValidationResult)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        
        boolean l_bIsShortSellRegulationTarget =
            l_validationResult.getShortSellingRestraint();
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);     
        WEB3MarginChangeOpenMarginUpdateInterceptor l_interceptor =
            new WEB3MarginChangeOpenMarginUpdateInterceptor(
                l_orderSpec,
                l_bIsShortSellRegulationTarget,
                l_strOrderRootDiv,
                (WEB3GentradeTrader)this.getTrader());
        
        Object[] l_orderSpecIntercepters = { l_interceptor };
        Object[] l_orderSpecs = { l_orderSpec };
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_orderSpec.getOrderId());
        OrderTypeEnum l_orderType = l_orderUnits[0].getOrderType();
        WEB3TPTradingPowerService l_tradingPowerService
            = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        WEB3TPTradingPowerResult l_tpResult =
            l_tradingPowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                l_orderType,
                l_blnUpdateFlg);
        if (l_tpResult.isResultFlg() == false)
        {
            l_orderManager.throwTpErrorInfo(l_tpResult, l_orderType);
        }
        
        if (l_blnUpdateFlg)
        {
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }     
}
@
