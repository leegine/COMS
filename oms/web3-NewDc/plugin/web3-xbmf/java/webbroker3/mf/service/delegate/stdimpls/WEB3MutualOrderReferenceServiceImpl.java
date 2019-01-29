head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������Ɖ�T�[�r�X�����N���X(WEB3MutualOrderReferenceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ���E (���u) �V�K�쐬
Revesion History : 2004/08/23 ���� (���u) �V�K�쐬 
Revesion History : 2004/12/10 ������ (���u) �c�Ή�
Revesion History : 2005/10/23 ���� (���u) �t�B�f���e�B�Ή�
Revesion History : 2007/02/05 ������ (���u) ���f�� No.526                      
Revesion History : 2007/04/09 ������ (���u) ����005
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3ExecStatusDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEB3OrderStatusDivDef;
import webbroker3.mf.define.WEB3ReferenceDivDef;
import webbroker3.mf.message.WEB3MutualFundTradeDivComparator;
import webbroker3.mf.message.WEB3MutualOrderDateComparator;
import webbroker3.mf.message.WEB3MutualOrderGroup;
import webbroker3.mf.message.WEB3MutualOrderReferenceRequest;
import webbroker3.mf.message.WEB3MutualOrderReferenceResponse;
import webbroker3.mf.message.WEB3MutualRequestDivComparator;
import webbroker3.mf.message.WEB3MutualSortKey;
import webbroker3.mf.message.WEB3MutualTaxTypeComparator;
import webbroker3.mf.service.delegate.WEB3MutualOrderReferenceService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M�������Ɖ�T�[�r�X�����N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualOrderReferenceServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualOrderReferenceService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualOrderReferenceServiceImpl.class);
    /**
     * �����M�������Ɖ�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����M���j�����Ɖ�v�Q�ƁB<BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u�i�����M���j�����Ɖ�v: <BR>
     *         5((�����`�F�b�N.validate����\�ڋq( )���R�[������O���Ԃ��ꂽ�ꍇ�A<BR>
     *         ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00275<BR>
     * ==========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 405669DE03AB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3MutualOrderReferenceResponse l_mutualOrderReferenceResponse = null;
        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualOrderReferenceRequest l_mutualOrderReferenceRequest = null;
        if (l_request instanceof WEB3MutualOrderReferenceRequest)
        {
            l_mutualOrderReferenceRequest =
                (WEB3MutualOrderReferenceRequest) l_request;
        }
        else
        {
            log.debug(
                "the parameter of method isn't WEB3MutualOrderReferenceRequest type");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1) ���N�G�X�g�f�[�^��validate����
        l_mutualOrderReferenceRequest.validate();
        
        //���M�E�O��MMF�\���敪
        //��null�̏ꍇ�A�u0:���M�̂݁v�Ƃ���
        if (l_mutualOrderReferenceRequest.mutualFrgnMmfDisplayDiv == null)
        {
            l_mutualOrderReferenceRequest.mutualFrgnMmfDisplayDiv =
                WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND;
        }

        // --�ڋq�ʎ����~�����`�F�b�N
        //1.2) this.get�⏕����( )���R�[�����A�⏕�����I�u�W�F�N�g���擾
        SubAccount l_subAccount = this.getSubAccount();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
        
        //1.3�jFinApp.getCommonOrderValidator( )
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        // --�������擾
        //1.4) ���M������ԊǗ�.get���M������()���R�[�����A���������擾����            
        Timestamp l_datBizDate = 
            new Timestamp(WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate().getTime());
        
        //1.5) �����`�F�b�N.validate����\�ڋq( )
        WEB3GentradeMainAccount l_genMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        OrderValidationResult l_validationResult =  
            l_gentradeOrderValidator.validateAccountForTrading(
                l_genMainAccount,
                l_datBizDate);
//---->����005
//        if (l_validationResult.getProcessingResult().isFailedResult())
//        {
//            log.debug("�����~�ڋq�G���[");
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                "�����~�ڋq�G���[");
//        }
//<-----����005

        // --�h�Ɖ�h�Ƃ��Ă̎�t���ԃ`�F�b�N 
        boolean l_blnValidateOrderFlag = true;
        boolean l_blnReference =
            WEB3ReferenceDivDef.ORDER_REFERENCE.equals(
                l_mutualOrderReferenceRequest.referenceType);
                
        // 1.6) ���M�����Ɖ�N�G�X�g.�Ɖ�敪���h�Ɖ�h�̏ꍇ
        if (l_blnReference)
        {
            //1.6.1)������ԊǗ�.validate������t�\( )���R�[������B
            //������ԊǗ�.validate������t�\( )�����O���Ԃ��ꂽ�ꍇ�A
            //��O���X���[����B
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        
        // --�h����ꗗ�h�Ƃ��Ă̎�t���ԃ`�F�b�N
        //    �������Ɂh����h�̎�t���ԃ`�F�b�N�����{
        //1.7) ���M������ԊǗ�.reset������t�g�����U�N�V����( )���R�[������B
        WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.CANCEL);
            
        //1.8) ���M������ԊǗ�.setTimestamp() 
        WEB3MutualFundTradingTimeManagement.setTimestamp(); 
        
        try
        {
			//1.9) ������ԊǗ�.validate������t�\( )
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch(WEB3BaseException l_ex)
        {
            if (l_blnReference)
            {
                l_blnValidateOrderFlag = false;
            }
            else
            {
                //���M�����Ɖ�N�G�X�g.�Ɖ�敪="����ꗗ"�̏ꍇ 
                //������ԊǗ�.validate������t�\( )�����O���Ԃ��ꂽ�ꍇ�A��O���X���[����B
                log.debug("������ԊǗ�.validate������t�\( )�����O���Ԃ��ꂽ");
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        //1.10) �����P�ʌ�������
        WEB3MutualFundOrderManager l_mutualFundOrderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();
        List l_OrderUnitList =
            l_mutualFundOrderManager.getOrderUnitList(
                l_subAccount,
                l_mutualOrderReferenceRequest.mutualFrgnMmfDisplayDiv);
        int l_intOrderUnitList = l_OrderUnitList.size();
        log.debug("�����P�ʌ���.size = " + l_intOrderUnitList);
        
        // --���ׂ̍쐬        
		//1.11) get�����P�ʈꗗ�̖߂�l�̌������A
		//     �J��Ԃ��ē��M�����Ɖ���P�ʂ̔z����쐬����B  
        List l_lisOrderGroup = new Vector();
        
        for (int i = 0; i < l_intOrderUnitList; i++)
        {
            WEB3MutualOrderGroup l_mutualOrderGroup = new WEB3MutualOrderGroup();
            MutualFundOrderUnit l_mutualFundOrderUnit =
                (MutualFundOrderUnit) l_OrderUnitList.get(i);
                
            //1.11.1) is����ہi�j
            boolean l_blnCancelAbleUnable =
                this.isCancelAbleUnable(l_mutualFundOrderUnit);
            
            //1.11.2) ���ׂ̍쐬
            //�u�h����ꗗ�h�Ƃ��Ă̎�t���ԃ`�F�b�N�v�̎�����ԊǗ�.validate������t�\( ) 
            //�ŗ�O���Ԃ���Ă����ꍇ
            if (!l_blnValidateOrderFlag)
            {
                l_blnCancelAbleUnable = false;
            }                
            //�E���M�����Ɖ�N�G�X�g.�Ɖ�敪��"�����Ɖ�"�̏ꍇ�A����s�Ƃ��� 
            //    ���M�����Ɖ���P�ʂ̎���\�t���O�� false�@@���Z�b�g����B 
            //�E���M�����Ɖ�N�G�X�g.�Ɖ�敪��"����ꗗ"�̏ꍇ�A�\���ΏۊO�Ƃ��� 
            //    �ȉ��̓��M�����Ɖ���P�ʍ쐬���s��Ȃ��B
            if (l_blnReference || (!l_blnReference && l_blnCancelAbleUnable))                
            {
				//1.11.2.1) ���M�����Ɖ���P�ʂ̐���
                MutualFundOrderUnitRow l_mutualFundOrderUnitRow =
                    (MutualFundOrderUnitRow) l_mutualFundOrderUnit.getDataSourceObject();

				//1.11.2.2) �v���p�e�B�E�Z�b�g
                //ID�Z�b�g
                log.debug("l_mutualFundOrderUnitRow.getOrderId() = " + l_mutualFundOrderUnitRow.getOrderId());
                l_mutualOrderGroup.id =
                    Long.toString(l_mutualFundOrderUnitRow.getOrderId());
                    
                //���M�g�������}�l�[�W�����擾
				WEB3MutualFundProductManager l_mfProductManager =
					(WEB3MutualFundProductManager) l_finApp.getTradingModule(
						   ProductTypeEnum.MUTUAL_FUND).getProductManager();
                //�����I�u�W�F�N�g���擾
				WEB3MutualFundProduct l_Product =
					(WEB3MutualFundProduct) l_mutualFundOrderUnit.getProduct();
								
				MutualFundProductRow l_mutualFundProductRow =
					(MutualFundProductRow) l_Product.getDataSourceObject();
				if (l_mutualFundProductRow == null)
				{
					log.debug("ProductRow is null");
					throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00377,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"�g�����M�����擾�ł��Ȃ��ꍇ�G���[");
				}					
				//�g�����M�������擾
				WEB3MutualFundProduct l_mutualFundProduct =
					(WEB3MutualFundProduct) l_mfProductManager.toProduct(l_mutualFundProductRow);

				//�������Z�b�g
				l_mutualOrderGroup.mutualProductName =
					l_mutualFundProduct.getMutualProductName();

                //�����敪�Z�b�g
                if (TaxTypeEnum.NORMAL.equals(l_mutualFundOrderUnitRow.getTaxType()))
                {
                    l_mutualOrderGroup.taxType =
                        WEB3MFAccountDivDef.NORMAL;
                }
                if (TaxTypeEnum.SPECIAL.equals(l_mutualFundOrderUnit.getTaxType())
                    || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_mutualFundOrderUnit.getTaxType()))
                {
                    l_mutualOrderGroup.taxType =
                        WEB3MFAccountDivDef.SPECIAL;
                }
                if (TaxTypeEnum.UNDEFINED.equals(l_mutualFundOrderUnitRow.getTaxType()))
                {
                    l_mutualOrderGroup.taxType =
                        WEB3MFAccountDivDef.OTHER;
                }
                //�����敪(���M)�Z�b�g
                l_mutualOrderGroup.mutualDealingType =
                    l_mutualFundOrderManager.getMutualTradeDiv(
                        l_mutualFundOrderUnit);
                //�������@@�Z�b�g
                l_mutualOrderGroup.sellBuyDiv =
                    l_mutualFundOrderUnitRow.getRequestDiv();
                //�w����@@              = (�ȉ��̎菇�Ŏ擾����)
                //-start---------------------------------------------
                //���M�����P��Params�Dget�������()�̖߂�l="201�F�����M��������"�A���́A 
               //���M�����P��Params�Dget�������( )�̖߂�l="203�F�����M����W����"�̏ꍇ 
                //���M�����P��Params.get���ʃ^�C�v( )="����"�̏ꍇ�A"�����w��"���Z�b�g�B
                //���M�����P��Params.get���ʃ^�C�v( )="���z"�̏ꍇ�A"���z�w��"���Z�b�g�B
                //��L�ȊO�̏ꍇ�A���M�����P��Params�Dget���M���敪()�̖߂�l���Z�b�g����B
                //-end-----------------------------------------------
                if(OrderTypeEnum.MF_BUY.equals(l_mutualFundOrderUnitRow.getOrderType()) ||
                   OrderTypeEnum.MF_RECRUIT.equals(l_mutualFundOrderUnitRow.getOrderType()))
                {
                    if(QuantityTypeEnum.QUANTITY.equals(l_mutualFundOrderUnitRow.getQuantityType()))
                    {
                        l_mutualOrderGroup.specifyDiv = WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE;
                    }
                    else if(QuantityTypeEnum.AMOUNT.equals(l_mutualFundOrderUnitRow.getQuantityType()))
                    {
                        l_mutualOrderGroup.specifyDiv = WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE;                        
                    }
                }
                else
                {
                    l_mutualOrderGroup.specifyDiv = l_mutualFundOrderUnitRow.getFundSellDiv();
                }
                //���ϕ��@@
                l_mutualOrderGroup.settleDiv =
                    l_mutualFundOrderUnitRow.getSettlementDiv();
                //�������ʋ敪
                l_mutualOrderGroup.mutualOrderQuantityType =
                    l_mutualFundOrderManager.getOrderQuantityDiv(
                        l_mutualFundOrderUnit);
                //��������
                l_mutualOrderGroup.mutualOrderQuantity =
                    WEB3StringTypeUtility.formatNumber(
                    l_mutualFundOrderUnitRow.getQuantity());
                //����z�ʉ݃R�[�h
                l_mutualOrderGroup.constantValueCurrencyCode =
                    l_mutualFundProduct.getCurrencyCode();
                //����z
                if (l_mutualFundOrderUnitRow.getCalcConstantValueIsNull())
                {
                    l_mutualOrderGroup.constantValue = null;
                }
                else
                {
                    l_mutualOrderGroup.constantValue =
                        WEB3StringTypeUtility.formatNumber(
                            l_mutualFundOrderUnitRow.getCalcConstantValue());
                    log.debug("����:����z= " + l_mutualOrderGroup.constantValue);
                }
                //����z�K�p��
                if (!OrderTypeEnum.MF_RECRUIT.equals(l_mutualFundOrderUnitRow.getOrderType()))
                {
                    l_mutualOrderGroup.constantValueAppDate =
                        l_mutualFundOrderUnitRow.getConstantValueAppDate();
                    log.debug("����:����z�K�p��= " + l_mutualOrderGroup.constantValueAppDate);
                }
                
                //�T�Z��n����ʉ݃R�[�h
                l_mutualOrderGroup.estimatedPriceCurrencyCode =
                    l_mutualFundOrderManager.getEstimateDeliveryAmountCurrencyCode(
                        l_mutualFundOrderUnit);
                log.debug("����:�T�Z��n����ʉ݃R�[�h= " + l_mutualOrderGroup.estimatedPriceCurrencyCode);
                //�T�Z��n���
                l_mutualOrderGroup.estimatedPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_mutualFundOrderUnitRow.getEstimatedPrice());
                log.debug("����:�T�Z��n���= " + l_mutualOrderGroup.estimatedPrice);
                //�T�Z��������
                l_mutualOrderGroup.estimatedQty =
                    WEB3StringTypeUtility.formatNumber(l_mutualFundOrderUnitRow.getEstimateDealingQty());
                log.debug("����:�T�Z��������= " + l_mutualOrderGroup.estimatedQty);
                //��n���@@ = ���M�����P��.get��n���@@( )
                l_mutualOrderGroup.deliveryDiv =
                    l_mutualFundOrderUnitRow.getPaymentMethod();
                log.debug("����:��n���@@= " + l_mutualOrderGroup.deliveryDiv);
                //�������� = ���M�����P��.get�󒍓���( )
                l_mutualOrderGroup.orderDate =
                    l_mutualFundOrderUnitRow.getReceivedDateTime();
                //������
                log.debug("l_mutualFundOrderUnitRow.getBizDate() = " + l_mutualFundOrderUnitRow.getBizDate());
                l_mutualOrderGroup.orderBizDate =
                    WEB3DateUtility.getDate(
                        l_mutualFundOrderUnitRow.getBizDate(),
                        "yyyyMMdd");
                log.debug("����:������= " + l_mutualOrderGroup.orderBizDate);
                //����
                l_mutualOrderGroup.executionTimestamp =
                    l_mutualFundOrderUnitRow.getExecDate();
                //��n��
                l_mutualOrderGroup.deliveryDate =
                    l_mutualFundOrderUnitRow.getDeliveryDate();
                //������ԋ敪
                l_mutualOrderGroup.orderState =
                    this.geOrderStatusDivision(l_mutualFundOrderUnit);
                //�O��MMF�t���O
                boolean l_blnFrgnMmfFlag = false;
                if(MutualFundTypeEnum.FOREIGN_MMF.equals(
                    l_mutualFundOrderUnitRow.getFundType()))
                {
                    l_blnFrgnMmfFlag = true;
                }
                l_mutualOrderGroup.frgnMmfFlag = l_blnFrgnMmfFlag;
                
                //���M�����I�u�W�F�N�g�i�抷��j�̎擾
                String l_strSwtProductCode =
                    l_mutualFundOrderUnitRow.getSwtProductCode();
                
				//1.11.2.3) ���M�����I�u�W�F�N�g�i�抷��j!= null�̏ꍇ
                if (l_strSwtProductCode != null)
                {
                    //�T�Z���t�����i�抷��j
                    l_mutualOrderGroup.switchingEstimatedQty =
                        WEB3StringTypeUtility.formatNumber(l_mutualFundOrderUnitRow.getSwtEstimateDealingQty());

                    log.debug("����:�T�Z���t�����i�抷��= " + l_mutualOrderGroup.switchingEstimatedQty);
                
                    //1.11.2.3.1)�@@�g�����M�����}�l�[�W��.get�g�����M����( )
                    WEB3MutualFundProductManager l_mutualFundProductManager =
                        (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                            ProductTypeEnum.MUTUAL_FUND).getProductManager();
                    WEB3MutualFundProduct l_web3MutualFundProduct = null;
                    try
                    {
                        l_web3MutualFundProduct =
                            (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                                l_subAccount.getInstitution(),
                                l_strSwtProductCode);
                    }
                    catch (NotFoundException e)
                    {
                        log.debug("no find MutualFundProduct");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName()
                                + "."
                                + STR_METHOD_NAME);
                    }
                    //1.11.2.3.2)�@@�擾�����g�����M�����I�u�W�F�N�g���A�ȉ���ݒ肷��B     
                    //�������i�抷��j�@@�@@�@@ = �抷��̊g�����M�����I�u�W�F�N�g.get������( )     
                    l_mutualOrderGroup.switchingProductName =
                        l_web3MutualFundProduct.getMutualProductName();
                    //�v�Z����z�ʉ݃R�[�h�i�抷��j = �抷��̊g�����M�����I�u�W�F�N�g.get�ʉ݃R�[�h( )
                    l_mutualOrderGroup.switchingConstantValueCurrencyCode =
                        l_web3MutualFundProduct.getCurrencyCode();
                    //�v�Z����z�i�抷��j = �抷��̊g�����M�����I�u�W�F�N�g.get����z( )                       
                    l_mutualOrderGroup.switchingConstantValue =
                        WEB3StringTypeUtility.formatNumber(
                            l_web3MutualFundProduct.getConstantValue());
                    //�����敪�i�抷��j�@@�@@�@@ = (�ȉ��̎菇�Ŏ擾����)
                    //-start---------------------------------------------
                    //b)�@@���M�����P��.getDataSourceObject( ).get�ŋ敪�i�抷��j( )���R�[������B
                    //b-1)�@@�߂�l��"1:���"�̏ꍇ�A"0:���"���Z�b�g�B
                    //b-2)�@@�߂�l��"2:����"�܂���"3:������������򒥎�"�̏ꍇ�A"1:����"���Z�b�g�B
                    //-end-----------------------------------------------
                    if(TaxTypeEnum.NORMAL.equals(l_mutualFundOrderUnitRow.getSwtTaxType()))
                    {
                        l_mutualOrderGroup.switchingTaxType =
                            WEB3TaxTypeSpecialDef.NORMAL;
                    }
                    if (TaxTypeEnum.SPECIAL.equals(l_mutualFundOrderUnitRow.getSwtTaxType())
                        || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_mutualFundOrderUnitRow.getSwtTaxType()))
                    {
                        l_mutualOrderGroup.switchingTaxType =
                            WEB3TaxTypeSpecialDef.SPECIAL;
                    }
                    //���򒥎��S�����i�抷���j = �i�ȉ��̂Ƃ���j 
                    //�@@�|���M�����P��.get���򒥎��S����isNull( )==false and
                    //�@@�@@�i���M�����P��.���� == ���M�����P��.�抷������ or
                    //�@@�@@�@@���M�����P��.�����L����� != �h�N���[�Y�h�j�̏ꍇ�A���M�����P��.get���򒥎��S����( )
                    //�@@�|����ȊO�Anull�B
                    l_mutualOrderGroup.switchingWHRestraintPrice = null;
                    
                    String l_strExecDate =
                    	WEB3DateUtility.formatDate(l_mutualFundOrderUnitRow.getExecDate(),"yyyyMMdd");
                    String l_strSwtExecDate =
                    	WEB3DateUtility.formatDate(l_mutualFundOrderUnitRow.getSwtExecDate(),"yyyyMMdd");
                    
                    if (!l_mutualFundOrderUnitRow.getWithholdingTaxRestrictionIsNull() &&
                    		(l_strExecDate.equals(l_strSwtExecDate) ||
                    			!l_mutualFundOrderUnitRow.getOrderOpenStatus().equals(OrderOpenStatusEnum.CLOSED)))
                   	{
                        l_mutualOrderGroup.switchingWHRestraintPrice =
                            WEB3StringTypeUtility.formatNumber(
                                l_mutualFundOrderUnitRow
                                    .getWithholdingTaxRestriction());
                    }
                }
				//1.11.2.4) ���M�����I�u�W�F�N�g�i�抷��j== null�̏ꍇ
                else
                {
                	//1.11.2.4.1) �v���p�e�B�E�Z�b�g
                    //�������i�抷��) = �抷��̊g�����M�����I�u�W�F�N�g.get������( )     
                    l_mutualOrderGroup.switchingProductName = null;
                    //�v�Z����z�ʉ݃R�[�h�i�抷��j = �抷��̊g�����M�����I�u�W�F�N�g.get�ʉ݃R�[�h( )
                    l_mutualOrderGroup.switchingConstantValueCurrencyCode =null;
                    //�v�Z����z�i�抷��j = �抷��̊g�����M�����I�u�W�F�N�g.get����z( )                       
                    l_mutualOrderGroup.switchingConstantValue = null;
                    //�����敪�i�抷��j�@@ = ���M�����P��.getDataSourceObject( ).get�ŋ敪�i�抷��j( )
                    l_mutualOrderGroup.switchingTaxType = null;
                    //���򒥎��S�����i�抷���j = null
                    l_mutualOrderGroup.switchingWHRestraintPrice = null;
                }
                //����\�t���O = 1.11.1) is����ۂ̖߂�l
                l_mutualOrderGroup.cancelFlag = l_blnCancelAbleUnable;
                
                //1.11.2.5) get�㗝���͎ҁi�j
                //1.11.2.6) get�㗝���͎ҁi�j!= null�̏ꍇ
                if (this.getTrader() != null)
                {
					//1.11.2.6.1) �v���p�e�B�E�Z�b�g
                    //�����`���l��=���M�����P��.���񒍕�.get���񂩂�̒����`���l��( )
                    l_mutualOrderGroup.orderChannel =
                        l_mutualFundOrderUnitRow.getOrderChanel();
                    //�����o�H�敪=���M�����P��.get�����o�H�敪( )
                    l_mutualOrderGroup.orderRootDiv =
                        l_mutualFundOrderUnitRow.getOrderRootDiv();
                    //�I�y���[�^�R�[�h=���M�����P��.get�����ID( )��0�̏ꍇ�̂݁A
                    //�g�����Z�I�u�W�F�N�g�}�l�[�W��
                    WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                        (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                    if (l_mutualFundOrderUnit.getTraderId() != 0)
                    {
                        try
                        {
                            l_mutualOrderGroup.operatorCode =
                                l_gentradeFinObjectManager.getTrader(
                                    l_mutualFundOrderUnit.getTraderId()).getTraderCode();
                        }
                        catch (NotFoundException l_ex)
                        {
                            log.error("no find Trader");
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName()
                                    + "."
                                    + STR_METHOD_NAME,
                                    l_ex.getMessage(),
                                    l_ex);
                        }
                    }
                }
                l_lisOrderGroup.add(l_mutualOrderGroup);
            }
        }   //end for
        
        WEB3MutualOrderGroup[] l_mutualOrderGroups = 
            new WEB3MutualOrderGroup[l_lisOrderGroup.size()];
        l_lisOrderGroup.toArray(l_mutualOrderGroups);
        
        // --�\�[�g����
        // 1.11.3) sort�����Ɖ�ׁi�j
        WEB3MutualOrderGroup[] l_web3MutualOrderGroups =
            this.sortOrderInquiryDetails(
                l_mutualOrderGroups,
                l_mutualOrderReferenceRequest.sortKeys);
         
		// 1.11.4) ���M�����Ɖ�X�|���X�N���X�𐶐�
		l_mutualOrderReferenceResponse = 
			(WEB3MutualOrderReferenceResponse) l_mutualOrderReferenceRequest.createResponse();
			       
        // 1.11.5) �y�[�W���O����
        //�@@���M�����Ɖ�X�|���X�̈ȉ��̍��ڂ�ݒ�
        //�����M�����Ɖ�X�|���X.���y�[�W��:
        //int l_lnMutualOrderGroupLength = l_intOrderUnitList;
        int l_intMutualOrderGroupLength = l_web3MutualOrderGroups.length;
        int l_intTotalPages =
            l_intMutualOrderGroupLength
                / Integer.parseInt(l_mutualOrderReferenceRequest.pageSize);
        if (l_intMutualOrderGroupLength == 0)
        {
            l_mutualOrderReferenceResponse.totalPages = "0";
        }
        else if (l_intMutualOrderGroupLength % Integer.parseInt(l_mutualOrderReferenceRequest.pageSize) == 0)
        {
            l_mutualOrderReferenceResponse.totalPages = Integer.toString(l_intTotalPages);
        }
        else if (l_intMutualOrderGroupLength % Integer.parseInt(l_mutualOrderReferenceRequest.pageSize) > 0)
        {
            l_mutualOrderReferenceResponse.totalPages = Integer.toString(l_intTotalPages + 1);
        }
        //�����M�����Ɖ�X�|���X.�����R�[�h��:�@@���ׂ̗v�f��
        l_mutualOrderReferenceResponse.totalRecords = Integer.toString(l_web3MutualOrderGroups.length);
        
        //�����M�����Ɖ�X�|���X.�\���y�[�W�ԍ�(�\�������y�[�W�ڂɂ����邩):�ȉ����Z�b�g�B
        if (l_intMutualOrderGroupLength > Integer.parseInt(l_mutualOrderReferenceRequest.pageSize)
                * (Integer.parseInt(l_mutualOrderReferenceRequest.pageIndex) - 1))
        {
            l_mutualOrderReferenceResponse.pageIndex =
                l_mutualOrderReferenceRequest.pageIndex;
        }
        else
        {
            l_mutualOrderReferenceResponse.pageIndex = l_mutualOrderReferenceResponse.totalPages;
        }
        
        //�ݒ��A���M�����Ɖ�X�|���X.���y�[�W�� = 0 �̏ꍇ�́A
        //���M�����Ɖ�X�|���X.���t�����ꗗ(���M�����Ɖ���P��[ ])��null��
        //�Z�b�g���A���X�|���X��return���� 
        if ("0".equals(l_mutualOrderReferenceResponse.totalPages))
        {
            l_mutualOrderReferenceResponse.mutualOrderGroups = null;
            log.debug("���M�����Ɖ�X�|���X.���y�[�W�� = 0");
            return l_mutualOrderReferenceResponse;
        }
        
        //1.11.6) ���ׂ̃Z�b�g
        //�@@(���M�����Ɖ�N�G�X�g.�y�[�W���\���s���~(���M�����Ɖ�X�|���X. 
        //    �\���y�[�W�ԍ� - 1)�����A�V)�Ŋm�肵�����M�����Ɖ�X�|���X���׃f�[�^ 
        //    �ꗗ�̗v�f���X�L�b�v����B 
        int l_intRecordBegin =
            Integer.parseInt(l_mutualOrderReferenceRequest.pageSize)
                * (Integer.parseInt(l_mutualOrderReferenceResponse.pageIndex) - 1);
        int l_intRecordEnd = 0;
        if (l_mutualOrderReferenceResponse.pageIndex.equalsIgnoreCase(l_mutualOrderReferenceResponse.totalPages))
        {
            l_intRecordEnd = l_intMutualOrderGroupLength;
        }
        else
        {
            l_intRecordEnd = l_intRecordBegin + Integer.parseInt(l_mutualOrderReferenceRequest.pageSize);
        }
        // ��L�Ō��肵�����M�����Ɖ�X�|���X���׃f�[�^�ꗗ�̗v�f�ԍ��` 
        // (���M�����Ɖ�X�|���X���׃f�[�^�ꗗ�̗v�f�ԍ��{���M�����Ɖ�N�G�X�g. 
        // �y�[�W���\���s��)�܂łɊY�����铊�M�����Ɖ�X�|���X���׃f�[�^���A 
        // ���M�����Ɖ�X�|���X�f�[�^.���t�����ꗗ�Ƃ��ăZ�b�g����B
        List l_lisBuyProductGroups = new Vector();
        for (int i = l_intRecordBegin ; i < l_intRecordEnd; i++) 
        {
            l_lisBuyProductGroups.add(l_web3MutualOrderGroups[i]);
        }
        WEB3MutualOrderGroup[] l_BuyProductGroups = new WEB3MutualOrderGroup[l_lisBuyProductGroups.size()];
        l_lisBuyProductGroups.toArray(l_BuyProductGroups); 
        l_mutualOrderReferenceResponse.mutualOrderGroups = l_BuyProductGroups;

        log.exiting(STR_METHOD_NAME);
        return l_mutualOrderReferenceResponse;
    }
    /**
     * (get������ԋ敪)<BR>
     * ����:���M�����P�ʃI�u�W�F�N�g���A������ԋ敪�𔻒肵�ԋp����B<BR>
     * <BR>
     * 1)�@@get�����o�H�敪()�̖߂�l���uHOST�v�ł���ꍇ<BR>
     * �@@�@@���A������Ԃ�"������(�������)"�ł���A�������L����Ԃ�"�N���[�Y�ł���ꍇ�A<BR>
     * �@@�@@�@@�@@"���������"�����^�[������B<BR>
     * �@@�@@����ȊO�̏ꍇ�A"����s��"�����^�[������B<BR> 
�@@   *   ���B<BR> 
     * 2) �ȉ��̏����ɍ��v�����ꍇ�A"������"�����^�[������B<BR>
     * �@@������Ԃ�NULL�ł���A��������Ԃ�"��t�ρi�V�K�����j"�܂���<BR>
     *    "������(�V�K����)"�ł���A���� �����R�[�h�i�抷��j��null�ł���<BR>
     * 3)�@@�ȉ��̏����ɍ��v�����ꍇ�A"�抷��"�����^�[������B <BR>
     * �@@������Ԃ�NULL�ł���A��������Ԃ�"��t�ρi�V�K�����j"�܂���<BR>
     *    "������(�V�K����)"�ł���A���� �����R�[�h�i�抷��j��null�ȊO�ł��� <BR>
     * <BR>
     * 4)�@@�ȉ��̏����ɍ��v�����ꍇ�A"��蒆"�����^�[������B<BR>
     * �@@��������Ԃ�"������(�V�K����)"�ł���A������Ԃ�"��蒆"�ł���<BR>
     * <BR>
     * 5)�@@�ȉ��̏����ɍ��v�����ꍇ�A"���������"�����^�[������B<BR>
     * �@@��������Ԃ�"������(�������)"�ł���A�������L����Ԃ�"�N���[�Y�ł���B<BR>
     * <BR>
     * 6)�@@��L�ȊO�̏ꍇ�A������Ԃ̒l�����^�[������B<BR>
     * @@param l_mutualFundOrderUnit - (���M�����P��)<BR>
     * ���M�����P�ʃI�u�W�F�N�g
     * @@return String
     * @@roseuid 40B6A6520044
     */
    protected String geOrderStatusDivision(MutualFundOrderUnit l_mutualFundOrderUnit)
    {
        final String STR_METHOD_NAME =
            "geOrderStatusDivision(MutualFundOrderUnit l_mutualFundOrderUnit)";
        log.entering(STR_METHOD_NAME);
        if (l_mutualFundOrderUnit == null && "".equals(l_mutualFundOrderUnit))
        {
            log.debug("the parameter of method is null or blank ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        MutualFundOrderUnitRow l_mutualFundOrderUnitRow =
            (MutualFundOrderUnitRow) l_mutualFundOrderUnit.getDataSourceObject();
        OrderStatusEnum l_orderStatusEnum =
            l_mutualFundOrderUnitRow.getOrderStatus();
        String l_strExecStatus = l_mutualFundOrderUnitRow.getExecStatus();
        OrderOpenStatusEnum l_orderOpenStatusEnum =
            l_mutualFundOrderUnitRow.getOrderOpenStatus();
            
        // 1) ��get�����o�H�敪()�̖߂�l���uHOST�v�ł���ꍇ 
        if(WEB3OrderRootDivDef.HOST.equals(l_mutualFundOrderUnitRow.getOrderRootDiv()))
        {
			// ������Ԃ�"������(�������)"�ł���A�������L����Ԃ�"�N���[�Y�ł���ꍇ�A
			// "���������"�����^�[������B
			if (OrderStatusEnum.CANCELLED.intValue() == l_orderStatusEnum.intValue()
					&& OrderOpenStatusEnum.CLOSED.intValue() == l_orderOpenStatusEnum.intValue())
			{
				return WEB3OrderStatusDivDef.ORDER_CANCELED;
			}
			// ����ȊO�̏ꍇ�A"����s��"�����^�[������B
			else
			{
        		return WEB3OrderStatusDivDef.CANCEL_DISABLE;
			}
        }
  
        // 2) �ȉ��̏����ɍ��v�����ꍇ�A"������"�����^�[������B
        //�@@  ������Ԃ�NULL�ł���A��������Ԃ�"��t�ρi�V�K�����j"�܂���
        //   "������(�V�K����)"�ł���A���� �����R�[�h�i�抷��j��null�ł���
        if(l_strExecStatus == null 
           && (OrderStatusEnum.ACCEPTED.intValue() == l_orderStatusEnum.intValue() 
           || OrderStatusEnum.ORDERED.intValue() == l_orderStatusEnum.intValue())
           && l_mutualFundOrderUnitRow.getSwtProductCode() == null   
           )
        {  
            return WEB3OrderStatusDivDef.ORDERING;  
        }
        // 3) �ȉ��̏����ɍ��v�����ꍇ�A"�抷��"�����^�[������
        //  ������Ԃ�NULL�ł���A��������Ԃ�"��t�ρi�V�K�����j"�܂���
        //  "������(�V�K����)"�ł���A���� �����R�[�h�i�抷��j��null�ȊO�ł���
        if (l_strExecStatus == null 
            && (OrderStatusEnum.ACCEPTED.intValue() == l_orderStatusEnum.intValue()
            || OrderStatusEnum.ORDERED.intValue() == l_orderStatusEnum.intValue())
            && l_mutualFundOrderUnitRow.getSwtProductCode() != null)
        {
            return WEB3OrderStatusDivDef.SWITCHING;
        }
        // 4) �ȉ��̏����ɍ��v�����ꍇ�A"��蒆"�����^�[������B
        //    ��������Ԃ�"������(�V�K����)"�ł���A������Ԃ�"��蒆"�ł���
        if (OrderStatusEnum.ORDERED.intValue() == l_orderStatusEnum.intValue()
            && WEB3ExecStatusDef.EXECUTED_IN_PROCESS.equals(l_strExecStatus))
        {
            return WEB3OrderStatusDivDef.EXECUTED_IN_PROCESS;
        }
        // 5) �ȉ��̏����ɍ��v�����ꍇ�A"���������"�����^�[������
        //    ��������Ԃ�"������(�������)"�ł���A�������L����Ԃ�"�N���[�Y�ł���
        if (OrderStatusEnum.CANCELLED.intValue() == l_orderStatusEnum.intValue()
            && OrderOpenStatusEnum.CLOSED.intValue() == l_orderOpenStatusEnum.intValue())
        {
            return WEB3OrderStatusDivDef.ORDER_CANCELED;
        }
        // 6) ��L�ȊO�̏ꍇ�A������Ԃ̒l�����^�[������
        String l_strStatues = Integer.toString(l_orderStatusEnum.intValue());
        log.exiting(STR_METHOD_NAME);
        return l_strStatues;
    }
    /**
     * (sort�����Ɖ��)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���Ɋ�Â��ē��M�����Ɖ�ׂ̃\�[�g���s���B <BR>
     * <BR>
     * �P)�@@ArrayList���쐬<BR> 
     * <BR>
     * �Q)�@@����:�\�[�g�L�[�̔z�񐔕�Loop���� <BR>
     * <BR>
     * �@@�Q�|�P)�@@����:�\�[�g�L�[.�L�[���ڂ��擾 <BR>
     * <BR>
     * �@@�Q�|�Q)�@@����:�\�[�g�L�[.����/�~�����擾 <BR>
     * <BR>
     * �@@�Q�|�R)�@@�L�[���ڂɂ�镪�򏈗� <BR>
     * �@@�@@�L�[���ڂ������敪�ł������ꍇ�A�����敪Comparator�𐶐� <BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��] <BR>
     * <BR>
     * �@@�@@�L�[���ڂ������敪(���M)�ł������ꍇ�A�����敪(���M)Comparator�𐶐� <BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��] <BR>
     * <BR>
     * �@@�@@�L�[���ڂ����������ł������ꍇ�A��������Comparator�𐶐� <BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��] <BR>
     * <BR>
     * �@@�Q�|�S)�@@�Q�|�R)�ɂč쐬����Comparator��ArrayList�ɒǉ� <BR>
     * <BR>
     * �R)�@@ArrayList����Comparator�̔z����쐬 <BR>
     * <BR>
     * �S)�@@Comparator�̔z�񏇂̃\�[�g���� <BR>
     * (web3-common)WEB3ArraysUtility.sort(����:���M�����Ɖ�ׁA<BR>Comparator[]) <BR>
     * <BR>
     * �T)�@@�\�[�g���ꂽ���M�����Ɖ�ׂ̔z���ԋp<BR>
     * @@param l_mutualFundOrderInquiryDetails - ���M�����Ɖ��
     * @@param l_sortKey - �\�[�g�L�[
     * @@return webbroker3.mf.message.WEB3MutualOrderGroup
     * @@roseuid 40B6A67A0380
     */
    protected WEB3MutualOrderGroup[] sortOrderInquiryDetails(
        WEB3MutualOrderGroup[] l_mutualFundOrderInquiryDetails,
        WEB3MutualSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME =
            "sortOrderInquiryDetails(WEB3MutualOrderGroup[] l_mutualFundOrderInquiryDetails,WEB3MutualSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);
        if (l_mutualFundOrderInquiryDetails == null || l_sortKey == null)
        {
            log.debug("the parameter of method is null  ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if(l_sortKey.length == 0)
        {
            log.debug("the parameter of method is length == 0 ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);        
        }
        if(l_mutualFundOrderInquiryDetails.length == 0 )
        {
			return l_mutualFundOrderInquiryDetails;
        }
		else
		{
        //�P)�@@ArrayList���쐬 
        ArrayList l_arrayList = new ArrayList();
        //�Q)�@@����:�\�[�g�L�[�̔z�񐔕�Loop���� 
        for (int i = 0; i < l_sortKey.length; i++)
        {
            //�Q�|�P)�@@����:�\�[�g�L�[.�L�[���ڂ��擾
            String l_strKeyItem = l_sortKey[i].keyItem;
            //�@@�Q�|�Q)�@@����:�\�[�g�L�[.����/�~�����擾 
            String l_strAscDesc = l_sortKey[i].ascDesc;
            //�Q�|�R)�@@�L�[���ڂɂ�镪�򏈗�
            if (WEB3MFSortkeyItemDef.TAX_TYPE.equals(l_strKeyItem))
            {
                WEB3MutualTaxTypeComparator l_taxTypeComparator =
                    new WEB3MutualTaxTypeComparator(l_strAscDesc);
                //�Q�|�S)�@@�Q�|�R)�ɂč쐬����Comparator��ArrayList�ɒǉ�
                l_arrayList.add(l_taxTypeComparator);
            }
            if (WEB3MFSortkeyItemDef.MUTUAL_DEALING_TYPE.equals(l_strKeyItem))
            {
                WEB3MutualFundTradeDivComparator l_mutualFundTradeDivComparator =
                    new WEB3MutualFundTradeDivComparator(l_strAscDesc);
                l_arrayList.add(l_mutualFundTradeDivComparator);
            }
            if (WEB3MFSortkeyItemDef.ORDER_DATE.equals(l_strKeyItem))
            {
                WEB3MutualOrderDateComparator l_orderDateComparator =
                    new WEB3MutualOrderDateComparator(l_strAscDesc);
                l_arrayList.add(l_orderDateComparator);
            }
            if (WEB3MFSortkeyItemDef.SELL_BUY_DIV.equals(l_strKeyItem))
            {
                WEB3MutualRequestDivComparator l_requestDivComparator =
                    new WEB3MutualRequestDivComparator(l_strAscDesc);
                l_arrayList.add(l_requestDivComparator);
            }
        }
        //�R)�@@ArrayList����Comparator�̔z����쐬
        Comparator[] l_comparators = new Comparator[l_arrayList.size()];
        l_arrayList.toArray(l_comparators);
        //�S)�@@Comparator�̔z�񏇂̃\�[�g���� 
        WEB3ArraysUtility.sort(l_mutualFundOrderInquiryDetails, l_comparators);
        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderInquiryDetails;
		}
    }
    /**
     * (is�����)<BR>
     * ���M�����P�ʂ��A���̒���������\�Ȓ������ǂ����𔻒肵�A����\�ȏꍇ<BR>
     * true���A����s�̏ꍇ��"false"��ԋp����B <BR>
     * <BR>
     * �P)�@@����J�����_�R���e�L�X�g�̍X�V<BR>
     * �@@�P�|�P)�@@���M������ԊǗ�.reset�����R�[�h( )���R�[������B<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@����:���M�����P��.getProduct( ).getProductCode( )<BR>
     * <BR>
     * �@@�P�|�Q)�@@���M������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �Q)�@@����۔���<BR>
     * �@@�Q�|�P)�@@���M�����R���ʃ`�F�b�N.validate����\( )���R�[�����A<BR>
     * �@@�@@�@@�@@��O���Ԃ��ꂽ�ꍇ�Afalse�����^�[������B<BR>
     * <BR>
     * �@@�Q�|�Q)�@@��L�ȊO�̏ꍇ�Atrue�����^�[������B<BR>
     * @@param l_mutualFundOrderUnit - (���M�����P��)<BR>
     * ���M�����P�ʃI�u�W�F�N�g
     * @@return boolean
     * @@roseuid 40B6A6E403AF
     */
    protected boolean isCancelAbleUnable(MutualFundOrderUnit l_mutualFundOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isCancelAbleUnable(MutualFundOrderUnit l_mutualFundOrderUnit))";
        log.entering(STR_METHOD_NAME);
        if (l_mutualFundOrderUnit == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P)�@@����J�����_�R���e�L�X�g�̍X�V
        //�P�|�P)�@@���M������ԊǗ�.reset�����R�[�h( )���R�[������
        WEB3MutualFundProduct l_Product =
            (WEB3MutualFundProduct) l_mutualFundOrderUnit.getProduct();
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_Product.getProductCode());
        log.debug("l_Product.getProductCode() = " + l_Product.getProductCode());
        //�P�|�Q)�@@���M������ԊǗ�.setTimestamp()���R�[������B
        try
        {
            WEB3MutualFundTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException e)
        {
            log.error("���M������ԊǗ�.setTimestamp()���R�[������,��O���Ԃ��ꂽ�ꍇ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80014,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�Q)�@@����۔���
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            	(WEB3MutualFundOrderManagerReusableValidationsCheck)
            			MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        try
        {
            l_validationsCheck.validateCancelPossible(
                this.getSubAccount(),
                l_mutualFundOrderUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }
}
@
