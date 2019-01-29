head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������̓T�[�r�X�����N���X(WEB3RuitoSellInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 ���u�� (���u) �V�K�쐬
                   2004/12/06 ��O�� (���u) �c�Ή�
*/

package webbroker3.xbruito.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoAsset;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.xbruito.WEB3RuitoAssetDetail;
import webbroker3.xbruito.WEB3RuitoClientRequestService;
import webbroker3.xbruito.WEB3RuitoOrderManagerReusableValidationsCheck;
import webbroker3.xbruito.WEB3RuitoPositionManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.message.WEB3RuitoSellInputRequest;
import webbroker3.xbruito.message.WEB3RuitoSellInputResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellInputService;

/**
 * �ݓ������̓T�[�r�X�����N���X<BR>
 */
public class WEB3RuitoSellInputServiceImpl
    extends WEB3RuitoClientRequestService
    implements WEB3RuitoSellInputService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellInputServiceImpl.class);

    /**
     * �ݐϓ�����񒍕����̓T�[�r�X���������{����B<BR>
     * �V�[�P���X�}�u�i�ݓ��j�����́v�Q��<BR>
     * <BR>
     * 1.3 ����\�ڋq�`�F�b�N�����{���A�G���[�̏ꍇ�͗�O���X���[���� 
     *      classpath:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00275<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 407627ED0151
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
		final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
		log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y���p�����[�^��Null�l�͐ݒ�ł��܂���B");
        }


		WEB3RuitoSellInputRequest l_ruitoSellInputRequset = null;
		if (l_request instanceof WEB3RuitoSellInputRequest)
		{
			l_ruitoSellInputRequset = (WEB3RuitoSellInputRequest) l_request;
		}
		else
		{
			log.debug(STR_METHOD_NAME + " __Error[���͒l���s���ł�]__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80018,
			    this.getClass().getName() + "." + STR_METHOD_NAME);			
		}
	
		l_ruitoSellInputRequset.validate();
		
        //1.1 �⏕�����擾
        SubAccount l_subAccount = getSubAccount();
        
        //1.2�@@�ڋq�ʎ����~�����`�F�b�N
        //�|FinApp.getCommonOrderValidator()���R�[�����A�����`�F�b�N�I�u�W�F�N�g���擾����B
        FinApp l_finApp = GtlUtils.getFinApp();
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        //1.3 �|�����`�F�b�N.validate����\�ڋq()���R�[������B
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        
        //�|�`�F�b�N�G���[�̏ꍇ�͂��O���X���[����B
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����~�ڋq�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "�����~�ڋq�`�F�b�N�G���[");
        }        
        
        WEB3RuitoProductManager l_web3RuitoProductManager =
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getProductManager();

        RuitoProduct l_ruitoProduct = null;
        RuitoAsset l_ruitoAsset = null;
        WEB3RuitoProduct l_web3RuitoProduct = null;

        //����ID
        long l_lngProductId = 0;
        //������
        String l_strProductName = null;
        //�ݓ��^�C�v
        RuitoTypeEnum l_ruitoTypeEnum = null;
        //�w����@@
        String l_strPaymentMethodSell = null;
        //��n���@@
        String l_strPaymentMethod = null;

        WEB3RuitoPositionManager l_web3RuitoPositionManager = null;

        try
        {
            //1.4 �ݓ������I�u�W�F�N�g���擾���� 
            l_ruitoProduct =
                l_web3RuitoProductManager.getRuitoProduct(
                    l_subAccount.getInstitution(),
                    l_ruitoSellInputRequset.ruitoProductCode,
                    "0");
                    
            l_web3RuitoProduct = (WEB3RuitoProduct) l_ruitoProduct;
            
            //1.5 �ݓ��������ݓ��^�C�v���擾����
            l_ruitoTypeEnum = l_web3RuitoProduct.getRuitoType();
            log.debug("�ݓ��^�C�v = " + l_ruitoTypeEnum);
            
            //1.6 �ݓ�����������ID���擾����
            l_lngProductId = l_web3RuitoProduct.getProductId();
            log.debug("����ID = " + l_lngProductId);
            
            //1.7 �ݓ����������������擾����
            l_strProductName = l_web3RuitoProduct.getProductName();
			log.debug("������ = " + l_strProductName);

            //1.8 �ݓ��������w����@@���擾����
            l_strPaymentMethodSell = l_web3RuitoProduct.getPaymentMethodSell();
			log.debug("�w����@@ = " + l_strPaymentMethodSell);
            
            //1.9 �ݓ���������n���@@���擾����
            l_strPaymentMethod = l_web3RuitoProduct.getPaymentMethod();
			log.debug("��n���@@ = " + l_strPaymentMethod);

            //��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N
            if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum))
            {
            	log.debug("RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoTypeEnum)");
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                    WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
                WEB3GentradeTradingTimeManagement.setTimestamp();
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            else if (RuitoTypeEnum.MMF.equals(l_ruitoTypeEnum))
            {
				log.debug("RuitoTypeEnum.MMF.equals(l_ruitoTypeEnum)");
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                    WEB3TradingTimeTypeDef.MMF_SET_CANCEL);
                WEB3GentradeTradingTimeManagement.setTimestamp();
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }

            //1.10 ����\�����`�F�b�N
            WEB3RuitoOrderManagerReusableValidationsCheck l_ruitoOrderManagerReusableValidCheck =
                new WEB3RuitoOrderManagerReusableValidationsCheck();

            l_ruitoOrderManagerReusableValidCheck.validateTradedProduct(
                l_web3RuitoProduct,
                false);
			
            //1.12 �w������ۗ̕L���Y�����擾���� 
            l_web3RuitoPositionManager =
                (WEB3RuitoPositionManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getPositionManager();
            
            l_ruitoAsset =
                (RuitoAsset)l_web3RuitoPositionManager.getAsset(
                    l_subAccount, l_ruitoProduct);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
			log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (OrderValidationException l_ex)
        {
			log.error("__OrderValidationException__", l_ex);
			log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getValidationResult().toString(),
                l_ex);
        }

        //1.13 �ݓ��ۗL���Y���׎擾
        AssetRow l_ruitoAssetRow =
            (AssetRow) l_ruitoAsset.getDataSourceObject();
        double l_dblCountBeforePenalty = l_ruitoAssetRow.getCountBeforePenalty();
		log.debug("l_dblCountBeforePenalty = " + l_dblCountBeforePenalty);

        WEB3RuitoAssetDetail l_web3RuitoAssetDetail =
            new WEB3RuitoAssetDetail(
                l_ruitoProduct.getProductId(),
                l_ruitoAsset.getQuantity(),
                l_dblCountBeforePenalty,
                l_ruitoTypeEnum);

        l_web3RuitoPositionManager.getRuitoAssetGroup(
            l_subAccount,
            l_web3RuitoAssetDetail);

        //�ݓ���񒍕����̓��X�|���X�I�u�W�F�N�g����

        // Modify by Alan Wang 2004/08/11 according to Bug#:  70  ---------- Begin
//      WEB3RuitoSellInputResponse l_response = new WEB3RuitoSellInputResponse();

        WEB3RuitoSellInputResponse l_response =
            (WEB3RuitoSellInputResponse)l_request.createResponse();

        // Modify by Alan Wang 2004/08/11 according to Bug#:  70   ---------- End

        //1.15 [�ݓ���񒍕����̓��X�|���X�ɐݒ肷��l] 
        //������
        l_response.ruitoProductName = l_strProductName;
        //���\�c��

        // ----------------- Start
        // Modify by Alan wang 2004/08/13 according to formating type double to type String
//        l_response.ruitoSellPossBalance = l_web3RuitoAssetDetail.getSellPossibleBalance() + "";
        l_response.ruitoSellPossBalance = 
            WEB3StringTypeUtility.formatNumber(l_web3RuitoAssetDetail.getSellPossibleBalance());
        // ----------------- End
        
		//�w����@@�ꗗ
        if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_strPaymentMethodSell)
            || WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strPaymentMethodSell))
        {
            String l_strSpecifyDivs[] = new String[2];
			l_strSpecifyDivs[0] = WEB3SellDivDef.ALL_DESIGNATE;
            l_strSpecifyDivs[1] = l_strPaymentMethodSell;
            l_response.specifyDivList = l_strSpecifyDivs;
        }
        else
        {
            String l_strSpecifyDivs[] = new String[3];
            l_strSpecifyDivs[0] = WEB3SellDivDef.ALL_DESIGNATE;
            l_strSpecifyDivs[1] = WEB3SellDivDef.MONEY_DESIGNATE;
			l_strSpecifyDivs[2] = WEB3SellDivDef.COUNT_DESIGNATE;
            l_response.specifyDivList = l_strSpecifyDivs;
        }
        
        //��n���@@�ꗗ
        if (WEB3PaymentMethodDef.BANK_TRANSFER.equals(l_strPaymentMethod)
            || WEB3PaymentMethodDef.SECURITIES_ACCOUNT_INPUT.equals(l_strPaymentMethod))
        {
            String l_strPaymentMethods[] = new String[1];
            l_strPaymentMethods[0] = l_strPaymentMethod;
            l_response.deliveryDivList = l_strPaymentMethods;
        }
        else
        {
            String l_strPaymentMethods[] = new String[2];
            l_strPaymentMethods[0] = WEB3PaymentMethodDef.BANK_TRANSFER;
            l_strPaymentMethods[1] = WEB3PaymentMethodDef.SECURITIES_ACCOUNT_INPUT;
            l_response.deliveryDivList = l_strPaymentMethods;
        }

		log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
