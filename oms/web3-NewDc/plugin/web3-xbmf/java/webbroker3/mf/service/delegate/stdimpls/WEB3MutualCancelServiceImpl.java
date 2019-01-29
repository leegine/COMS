head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M������T�[�r�X�����N���X(WEB3MutualCancelServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
Revesion History : 2004/08/20 ��O�� (���u) ���r���[  
Revesion History : 2004/12/10 ������ (���u) �c�Ή�  
Revesion History : 2005/10/21 ���� (���u) �t�B�f���e�B�Ή�
Revesion History : 2006/10/16 ���� �d�l�ύX�E���f��508
Revesion History : 2006/10/24 ����� �d�l�ύX�E���f��514
Revesion History : 2007/02/09 �����F (���u) �d�l�ύX�E���f��540
Revesion History : 2007/03/26 ����� (���u) �d�l�ύX�E���f��553
Revesion History : 2007/04/06 ������ (���u) ����005
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.aio.WEB3AioCashoutCancelUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioPaymentApplicationDivDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3MfRecruitMqSendDivDef;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundCancelConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualCancelCompleteRequest;
import webbroker3.mf.message.WEB3MutualCancelCompleteResponse;
import webbroker3.mf.message.WEB3MutualCancelConfirmRequest;
import webbroker3.mf.message.WEB3MutualCancelConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualCancelService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M������T�[�r�X�����N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualCancelServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualCancelService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelServiceImpl.class);

    /**
     * �����M������T�[�r�X���������{����B<BR>
     * <BR>
     * ���b�Z�[�W�N���X�̌^�ɂ��Avalidate���()���\�b�h�A<BR>
     * submit���()���\�b�h�̂����ꂩ���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 405575350108
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3MutualCancelConfirmRequest)
        {
            //validate���()���\�b�h
            l_response =
                this.validateCancel((WEB3MutualCancelConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3MutualCancelCompleteRequest)
        {
            //submit���()���\�b�h
            l_response =
                this.submitCancel((WEB3MutualCancelCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                STR_METHOD_NAME
                    + " __Request "
                    + " WEB3MutualCancelCompleteRequest "
                    + " �� WEB3MutualCancelConfirmRequest�ȊO�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate���)<BR>
     * �����M������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�����M��)����R���v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 405575AC005C
     */
    protected WEB3MutualCancelConfirmResponse validateCancel(WEB3MutualCancelConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateCancel(WEB3MutualCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�@@���͓��e�`�F�b�N
        l_request.validate();

        //�Q�j�@@�⏕�����擾
        SubAccount l_subAccount = this.getSubAccount();

        //�R�j�@@�����P�ʃI�u�W�F�N�g�̎擾
        //�|�g�������}�l�[�W���̎擾 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getOrderManager();
            
        long l_lngRequestId = 0;    
        if (WEB3StringTypeUtility.isNumber(l_request.id))
        {
            l_lngRequestId = Long.parseLong(l_request.id);
        }        

        MutualFundOrderUnitParams l_mfOrderUnitParams = null;

        //�|�g�������}�l�[�W��.getOrderUnits()���R�[�����A
        OrderUnit l_orderUnit[] =
            l_orderManager.getOrderUnits(l_lngRequestId);

        //�����M�������P��Params���擾����B
        l_mfOrderUnitParams =
            new MutualFundOrderUnitParams(
                (MutualFundOrderUnitRow) l_orderUnit[0].getDataSourceObject());

        //�S�j�@@���M�����擾
        //�|�g�����M�����}�l�[�W�����擾����
        WEB3MutualFundProductManager l_produceManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualFundProduct l_mfProduct = null;
        try
        {
            //�|�g�����M�����}�l�[�W��.getProduct()���R�[�����A
            // �����I�u�W�F�N�g���擾����
            l_mfProduct =
                (WEB3MutualFundProduct) l_produceManager.getProduct(
                    l_mfOrderUnitParams.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when "
                    + "l_produceManager.getProduct(ProductId) with "
                    + "ProductId = "
                    + l_mfOrderUnitParams.getProductId());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�|�����R�[�h�̎擾
        //�g�����M����.get�����R�[�h���R�[�����A�����R�[�h���擾����
        String l_strProductCode = l_mfProduct.getProductCode();

        // �@@�|�������̎擾 
        //�@@�@@�g�����M����.get������()���R�[�����A���������擾����
        String l_strProductName = l_mfProduct.getMutualProductName();

        // �@@�|�ʉ݃R�[�h�̎擾<BR>
        // �@@�@@�g�����M����.get�ʉ݃R�[�h()���R�[�����A�ʉ݃R�[�h���擾����
        String l_strProductCurrencyCode = l_mfProduct.getCurrencyCode();

        //FinApp�N���X��getCommonOrderValidator()���R�[����
        //�����`�F�b�N�I�u�W�F�N�g���擾����      
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        // �U�j�@@��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N
        //�|���M������ԊǗ�.validate������t�\()���R�[��
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        // ������Ԃ̍Đݒ� 
        // �V�j���M������ԊǗ�.reset�����R�[�h()���R�[�����A
        WEB3MutualFundTradingTimeManagement.resetProductCode(l_strProductCode);

        // �W�j��t�����A���t���[�����Z�b�g����
        WEB3MutualFundTradingTimeManagement.setTimestamp();
        
        // �X�j������ԊǗ�.get���M������()���R�[�����A�m�F�������̔��������擾����
        //Date l_datBizDate = 
        //   WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
          
        String l_strMutualTradeDiv = null;

        // �P�O�j�����敪�i���M�j�̎擾
        // �@@�|�g�����M�����}�l�[�W��.get�����敪�i���M�j( )���R�[��
        MutualFundOrderUnit l_mfOrderUnit = 
            (MutualFundOrderUnit) l_orderManager.toOrderUnit(l_mfOrderUnitParams);
        l_strMutualTradeDiv =
            l_orderManager.getMutualTradeDiv(l_mfOrderUnit);
        
		// �P�P�j���M���������擾���� 
        OrderTypeEnum l_orderTypeEnum = null;
        if (WEB3ProcessDivDef.BUY.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_BUY;
        }
        else if (WEB3ProcessDivDef.SELL.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_SELL;
        }
        else if (WEB3ProcessDivDef.RECRUIT.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_RECRUIT;
        }
        else if (WEB3ProcessDivDef.SWITCHING.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_SWITCHING;
        }
        boolean l_blnNorealDiv = false;
        BranchPreferencesRow l_branchPreferencesRow = null;
        try
        {
            l_branchPreferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                l_mfOrderUnit.getBranchId(), 
                WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV,
                1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
                
        boolean l_blnValue;
        if (l_branchPreferencesRow == null || WEB3MfRecruitMqSendDivDef.DEFAULT.equals(l_branchPreferencesRow.getValue()))
        {
            l_blnValue = true;
        }
        else
        {
            l_blnValue = false;
        }
        if ((WEB3ProcessDivDef.RECRUIT.equals(l_strMutualTradeDiv) && l_blnValue)
            || (WEB3ProcessDivDef.BUY.equals(l_strMutualTradeDiv) && 
                l_mfProduct.isUnitTypeProduct(l_mfOrderUnit.getOrderType()))
            || (WEB3ProcessDivDef.SELL.equals(l_strMutualTradeDiv) && 
                l_mfProduct.isUnitTypeProduct(l_mfOrderUnit.getOrderType())))
        {
            l_blnNorealDiv = true;
        }
        //�������������̏ꍇ�̔������擾 
        Date l_datBizDate = null;
        long l_lngUnitProductId = l_mfProduct.getProductId();
        if (WEB3ProcessDivDef.SELL.equals(l_strMutualTradeDiv) && 
            l_mfProduct.isUnitTypeProduct(l_mfOrderUnit.getOrderType()) &&
            (l_lngUnitProductId == 3303910181800L || l_lngUnitProductId == 3303911181800L))
        {
            MutualFundProductRow l_mfProductRow = 
                (MutualFundProductRow) l_mfProduct.getDataSourceObject();
            l_datBizDate = l_mfProductRow.getSellSwtEndDate();
        }
        //�������������ȊO�̏ꍇ�̔������擾 
        else
        {
            l_datBizDate =
                WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(l_orderTypeEnum, l_blnNorealDiv);
        }

        Timestamp l_datBizDateValidate = 
			new Timestamp(l_datBizDate.getTime());

        // �P�Q�j�ڋq�ʎ����~�����`�F�b�N
		WEB3GentradeMainAccount l_genMainAccount = 
			(WEB3GentradeMainAccount) l_subAccount.getMainAccount();
		OrderValidationResult l_validationResult =  
			l_orderValidator.validateAccountForTrading(
				l_genMainAccount,
				l_datBizDateValidate);
        
		if (l_validationResult.getProcessingResult().isFailedResult())
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

        // �P�R�j�抷��̓��M�����擾
        //�@@�|�g�����M�����}�l�[�W��.get���M����()���R�[�����A�g�����M�������擾����
        WEB3MutualFundProduct l_swtProduct = null;
        String l_strSwtProductName = null;
        String l_strSwtCurrencyCode = null;
        if(WEB3ProcessDivDef.SWITCHING.equals(l_strMutualTradeDiv))
        {
            try
            {
                //�|�g�����M�����}�l�[�W��.getProduct()���R�[�����A
                // �����I�u�W�F�N�g���擾����
                l_swtProduct =
                    (WEB3MutualFundProduct) l_produceManager.getMutualFundProduct(
                        l_subAccount.getInstitution(),
                        l_mfOrderUnitParams.getSwtProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error(
                    "__NotFoundException__ when "
                        + " l_produceManager.getMutualFundProduct() with "
                        + " Institution = "
                        + l_subAccount.getInstitution().getInstitutionCode()
                        + " ProductCode = "
                        + l_mfOrderUnitParams.getSwtProductCode());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            // �@@�|�������̎擾 
            // �@@�@@�g�����M����.get������()���R�[�����A���������擾����
            l_strSwtProductName = l_swtProduct.getMutualProductName();

            // �@@�|�ʉ݃R�[�h�̎擾 <BR>
            // �@@�@@�g�����M����.get�ʉ݃R�[�h()���R�[�����A�ʉ݃R�[�h���擾����
            l_strSwtCurrencyCode = l_swtProduct.getCurrencyCode();
        }

        // �P�S�j���M����������e�I�u�W�F�N�g�𐶐�����
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_lngRequestId);

        // �P�T�j�����R�� 
        //�|�g�����M�����}�l�[�W��.validate�������()���R�[��
        OrderValidationResult l_cancelValidationResult =
            l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_cancelValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����R���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_cancelValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R���`�F�b�N�G���[");
        }

        // �P�U�j�@@���M��������m�F���X�|���X�I�u�W�F�N�g�𐶐����A���^�[������
        WEB3MutualCancelConfirmResponse l_response = (WEB3MutualCancelConfirmResponse) l_request.createResponse();

        // �@@���M��������m�F���X�|���X�I�u�W�F�N�g��ݒ肷��        
        //�@@1. ������<BR>  ���@@�S�j�Ŏ擾����������
        l_response.mutualProductName = l_strProductName;

        //�@@2. �v�Z����z�ʉ݃R�[�h�@@���@@�S�j�Ŏ擾�����ʉ݃R�[�h
        l_response.constantValueCurrencyCode = l_strProductCurrencyCode;

        //3. �v�Z����z
        //���M�����P��Params�Dget���M�^�C�v() =�O��MMF�̏ꍇ �� null
        //����ȊO�̏ꍇ ���@@���M�����P��Params�Dget�v�Z����z()�̖߂�l
        if (MutualFundTypeEnum.FOREIGN_MMF.equals(l_mfOrderUnitParams.getFundType()))
        {
            l_response.constantValue = null;
        }
        else
        {
            l_response.constantValue =
                WEB3StringTypeUtility.formatNumber(
                    l_mfOrderUnitParams.getCalcConstantValue());
        }

        //�@@4. ����z�K�p���@@���@@���M�����P��Params�Dget����z�K�p��()�̖߂�l
        l_response.constantValueAppDate =
            WEB3DateUtility.toDay(l_mfOrderUnitParams.getConstantValueAppDate());

        //�@@5. �����敪�@@���@@(�ȉ��̎菇�Ŏ擾����)<BR>
        //        -start---------------------------------------------<BR>
        //          a)�@@����:���M�����P��.get�ŋ敪( )���R�[������B<BR>
        //          a-1)�@@�߂�l��"1:���"�̏ꍇ�A"0:���"���Z�b�g�B<BR>
        //          a-2)�@@�߂�l��"2:����"�܂���"3:������������򒥎�"�̏ꍇ�A<BR>
        //              "1:����"���Z�b�g�B<BR>
        //        -end-----------------------------------------------<BR> 
        
        if (TaxTypeEnum.NORMAL.equals(l_mfOrderUnitParams.getTaxType()))
        {
            l_response.taxType =
                WEB3MFAccountDivDef.NORMAL;
        }
        else
        {
            if (TaxTypeEnum.SPECIAL.equals(l_mfOrderUnitParams.getTaxType())
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_mfOrderUnitParams.getTaxType()))
            {
                l_response.taxType =
                    WEB3MFAccountDivDef.SPECIAL;
            }
            else if (TaxTypeEnum.UNDEFINED.equals(l_mfOrderUnitParams.getTaxType()))
            {
                l_response.taxType = WEB3MFAccountDivDef.OTHER;
            }
        }

        //�@@6. �����敪�i���M�j�@@���@@�W�j�Ŏ擾���������敪
        l_response.mutualDealingType = l_strMutualTradeDiv;

        //�@@7. �������@@�@@���@@���M�����P��Params.get�����敪()�̖߂�l
        l_response.sellBuyDiv = l_mfOrderUnitParams.getRequestDiv();

        //�@@8. �w����@@�@@���@@�i�ȉ��̎菇�ŃZ�b�g����j<BR>
        //�����M�����P��Params�Dget�������( )�̖߂�l="201�F�����M��������"
        //�܂��́@@"203�F�����M����W����"�̏ꍇ 
        //  ���M�����P��Params.get���ʃ^�C�v( )="����"�̏ꍇ�A"�����w��"���Z�b�g�B 
        //  ���M�����P��Params.get���ʃ^�C�v( )="���z"�̏ꍇ�A"���z�w��"���Z�b�g�B 
        //����L�ȊO�̏ꍇ�A���M�����P��Params�Dget���M���敪()�̖߂�l���Z�b�g����B
        if (OrderTypeEnum.MF_BUY.equals(l_mfOrderUnitParams.getOrderType()) ||
            OrderTypeEnum.MF_RECRUIT.equals(l_mfOrderUnitParams.getOrderType()))
        {
            if (QuantityTypeEnum.QUANTITY.equals(l_mfOrderUnitParams.getQuantityType()))
            {
                l_response.specifyDiv = WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE;
            }
            else if (QuantityTypeEnum.AMOUNT.equals(l_mfOrderUnitParams.getQuantityType()))
            {
                l_response.specifyDiv = WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE;
            }           
        }
        else
        {
            l_response.specifyDiv = l_mfOrderUnitParams.getFundSellDiv();
        }

        //�@@9. �������ʋ敪�@@���@@�g�����M�����}�l�[�W��.get�������ʋ敪()�̖߂�l
        l_response.mutualOrderQuantityType =
            l_orderManager.getOrderQuantityDiv(l_mfOrderUnit);

        //�@@10. �������ʁ@@���@@���M�����P��Params.get��������()�̖߂�l
        l_response.mutualOrderQuantity =
            WEB3StringTypeUtility.formatNumber(
                l_mfOrderUnitParams.getQuantity());        
        if (l_mfOrderUnitParams.getSwtProductCode() == null || "".equals(l_mfOrderUnitParams.getSwtProductCode()))
        {
            //  11. �T�Z��n����ʉ݃R�[�h�@@���@@���M�����P��.get���ϋ敪()���u�~�݁v�̏ꍇ
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_mfOrderUnitParams.getSettlementDiv()))
            {
                // "T0"���Z�b�g����
                l_response.estimatedPriceCurrencyCode = WEB3MFOrderQuantityType.EN;
            }
            else
            {
                //�S�j�Ŏ擾�����ʉ݃R�[�h���Z�b�g����
                l_response.estimatedPriceCurrencyCode = l_strProductCurrencyCode;
            }

            //�@@12. �T�Z��n����@@�� ���M�����P��Params�Dget�T�Z��n���()�̖߂�l
            l_response.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_mfOrderUnitParams.getEstimatedPrice());

            //�@@13. �T�Z���������@@�� ���M�����P��Params�Dget�T�Z��������()�̖߂�l
            l_response.estimatedQty =
            WEB3StringTypeUtility.formatNumber(
            l_mfOrderUnitParams.getEstimateDealingQty());

            // 14. �抷������ȊOnull
            l_response.switchingProductName = null;

            // 15. �抷������ȊOnul��
            l_response.switchingConstantValueCurrencyCode = null;

            // 16. �抷������ȊOnull
            l_response.switchingConstantValue = null;

            // 17. �抷������ȊOnull
            l_response.switchingTaxType = null;

            // 18. �抷������ȊOnull
            l_response.switchingEstimatedQty = null;

            //�@@19. ���ϕ��@@�@@���@@���M�����P��Params�Dget���ϋ敪()�̖߂�l
            l_response.settleDiv = l_mfOrderUnitParams.getSettlementDiv();

            //�@@20. ��n���@@�@@���@@���M�����P��Params�Dget��n���@@()�̖߂�l
            l_response.deliveryDiv = l_mfOrderUnitParams.getPaymentMethod();
        }
        else
        {
            //  11. �抷�����null
            l_response.estimatedPriceCurrencyCode = null;

            //  12. �抷�����null
            l_response.estimatedPrice = null;
            
            //  13. �抷�����null
            l_response.estimatedQty = null;

            //�@@14. �������i�抷��j�@@���@@�X�j�Ŏ擾����������
            l_response.switchingProductName = l_strSwtProductName;

            //�@@15. �v�Z����z�ʉ݃R�[�h�i�抷��j�@@���@@�X�j�Ŏ擾�����ʉ݃R�[�h
            l_response.switchingConstantValueCurrencyCode = l_strSwtCurrencyCode;

            //�@@16. �v�Z����z�i�抷��j�@@���@@���M�����P��Params�Dget�v�Z����z�i�抷��j()�̖߂�l
            l_response.switchingConstantValue = WEB3StringTypeUtility.formatNumber(
            l_mfOrderUnitParams.getSwtCalcConstantValue());

            //�@@17. �����敪�i�抷��j�@@���@@(�ȉ��̎菇�Ŏ擾����)<BR>
            //        -start---------------------------------------------
            //         b)�@@���M�����P��.getDataSourceObject( ).get�ŋ敪�i�抷��j( )���R�[������B<BR>
            //         b-1)�@@�߂�l��"1:���"�̏ꍇ�A"0:���"���Z�b�g�B<BR>
            //         b-2)�@@�߂�l��"2:����"�܂���"3:������������򒥎�"�̏ꍇ�A"1:����"���Z�b�g�B<BR>
            //        -end-----------------------------------------------
                
            if(TaxTypeEnum.NORMAL.equals(l_mfOrderUnitParams.getSwtTaxType()))
            {
                l_response.switchingTaxType =
                    WEB3TaxTypeSpecialDef.NORMAL;
            }
            else
            {
                if (TaxTypeEnum.SPECIAL.equals(l_mfOrderUnitParams.getSwtTaxType())
                    || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_mfOrderUnitParams.getSwtTaxType()))
                {
                    l_response.switchingTaxType =
                        WEB3TaxTypeSpecialDef.SPECIAL;
                }
            }

            //�@@18. �T�Z���t�����i�抷��j�@@���@@���M�����P��Params�Dget�T�Z���t�����i�抷��j
            l_response.switchingEstimatedQty = 
            WEB3StringTypeUtility.formatNumber(
            l_mfOrderUnitParams.getSwtEstimateDealingQty());

            // 19. �抷�����null
            l_response.settleDiv = null;

            // 20.�抷�����null
            l_response.deliveryDiv = null;
        }

        //�@@21. �������@@���@@�����P��Params�DgetBizDate()�̖߂�l
        l_response.orderBizDate =
            WEB3DateUtility.getDate(
                l_mfOrderUnitParams.getBizDate(),
                "yyyyMMdd");

        //�@@22. �����@@���@@���M�����P��Params�Dget����()�̖߂�l
        l_response.executionTimestamp = WEB3DateUtility.toDay(
            l_mfOrderUnitParams.getExecDate());

        //�@@23. ��n���@@���@@���M�����P��Params�Dget��n��()�̖߂�l
        l_response.deliveryDate = WEB3DateUtility.toDay(
            l_mfOrderUnitParams.getDeliveryDate());

        //�@@24. �m�F���������@@���@@�P�Q�j�Ŏ擾����������
        l_response.checkDate = WEB3DateUtility.toDay(l_datBizDate);

        return l_response;
    }

    /**
     * (submit���)<BR>
     * �����M������o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�����M��)����o�^�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 405575B50166
     */
    protected WEB3MutualCancelCompleteResponse submitCancel(WEB3MutualCancelCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitCancel(WEB3MutualCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1�@@���͓��e�`�F�b�N
        l_request.validate();

        //1.2�@@�⏕�����擾
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();

        //1.3�@@�����P�ʃI�u�W�F�N�g�̎擾
        //�|�g�������}�l�[�W���̎擾 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        long l_lngRequestId = 0;    
        if (WEB3StringTypeUtility.isNumber(l_request.id))
        {
            l_lngRequestId = Long.parseLong(l_request.id);
        } 

        MutualFundOrderUnitParams l_mfOrderUnitParams = null;
        
        //�|�g�������}�l�[�W��.getOrderUnits()���R�[�����A
        OrderUnit l_orderUnits[] =
            l_orderManager.getOrderUnits(l_lngRequestId);

        //�����M�������P��Params���擾����B
        l_mfOrderUnitParams =
            new MutualFundOrderUnitParams(
                (MutualFundOrderUnitRow) l_orderUnits[0].getDataSourceObject());

        //1.4�@@���M�����擾
        //�|�g�����M�����}�l�[�W�����擾����
        WEB3MutualFundProductManager l_produceManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualFundProduct l_mfProduct = null;
        try
        {
            //�|�g�����M�����}�l�[�W��.getProduct()���R�[�����A
            // �����I�u�W�F�N�g���擾����
            l_mfProduct =
                (WEB3MutualFundProduct) l_produceManager.getProduct(
                    l_mfOrderUnitParams.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when "
                    + "l_produceManager.getProduct(ProductId) with "
                    + "ProductId = "
                    + l_mfOrderUnitParams.getProductId());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        //�|�����R�[�h�̎擾
        //�g�����M����.get�����R�[�h���R�[�����A�����R�[�h���擾����
        String l_strProductCode = l_mfProduct.getProductCode();
        
        // 1.6�@@�ڋq�ʎ����~�����`�F�b�N
        //FinApp�N���X��getCommonOrderValidator()���R�[����
        //�����`�F�b�N�I�u�W�F�N�g���擾����      
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        // 1.7 get�㗝���͎�( )
        Trader l_trader = this.getTrader();
        
        // 1.8 �|validate����p�X���[�h( )���R�[������B  
        OrderValidationResult l_validationResultPassword = 
            l_orderValidator.validateTradingPassword(
                l_trader,
                l_subAccount,
                l_request.password);
        
        if (l_validationResultPassword.getProcessingResult().isFailedResult())
        {
            log.debug("����p�X���[�h���s���ł�");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00009,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����p�X���[�h���s���ł�");
        }

        // 1.9�@@��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N
        //�|���M������ԊǗ�.validate������t�\()���R�[��
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        // 1.10 ������Ԃ̍Đݒ� 
        // �@@�|���M������ԊǗ�.reset�����R�[�h()���R�[�����A
        WEB3MutualFundTradingTimeManagement.resetProductCode(l_strProductCode);

        // 1.11 �|��t�����A���t���[�����Z�b�g����
        WEB3MutualFundTradingTimeManagement.setTimestamp();
        
        // 1.12 �|�����`�F�b�N.validate����\�ڋq()���R�[������
        //�������擾         
        Timestamp l_datBizDate = 
            new Timestamp(l_request.checkDate.getTime());
        
        WEB3GentradeMainAccount l_genMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        OrderValidationResult l_validationResult =  
            l_orderValidator.validateAccountForTrading(
                l_genMainAccount,
                l_datBizDate);
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1.13 �����敪�i���M�j�̎擾 
        MutualFundOrderUnit l_orderUnit = 
            (MutualFundOrderUnit) l_orderManager.toOrderUnit(l_mfOrderUnitParams);
        String l_strMutualTradeDiv = l_orderManager.getMutualTradeDiv(l_orderUnit);

        // 1.14�@@���M����������e�I�u�W�F�N�g�𐶐�����
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_lngRequestId);

        // 1.15�@@�����R�� 
        //�|�g�����M�����}�l�[�W��.validate�������()���R�[��
        OrderValidationResult l_cancelValidationResult =
            l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_cancelValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����R���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_cancelValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R���`�F�b�N�G���[");
        }

        // 1.16�@@�������擾 
        OrderTypeEnum l_orderTypeEnum = null;
        if (WEB3ProcessDivDef.BUY.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_BUY;
        }
        else if (WEB3ProcessDivDef.SELL.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_SELL;
        }
        else if (WEB3ProcessDivDef.RECRUIT.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_RECRUIT;
        }
        else if (WEB3ProcessDivDef.SWITCHING.equals(l_strMutualTradeDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_SWITCHING;
        }
        boolean l_blnNorealDiv = false;
        BranchPreferencesRow l_branchPreferencesRow = null;
        try
        {
            l_branchPreferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                l_orderUnit.getBranchId(), 
                WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV,
                1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
               
        boolean l_blnValue;
        if (l_branchPreferencesRow == null || WEB3MfRecruitMqSendDivDef.DEFAULT.equals(l_branchPreferencesRow.getValue()))
        {
            l_blnValue = true;
        }
        else
        {
            l_blnValue = false;
        }
        if ((WEB3ProcessDivDef.RECRUIT.equals(l_strMutualTradeDiv) && l_blnValue)
            || (WEB3ProcessDivDef.BUY.equals(l_strMutualTradeDiv) && 
                l_mfProduct.isUnitTypeProduct(l_orderUnit.getOrderType()))
            || (WEB3ProcessDivDef.SELL.equals(l_strMutualTradeDiv) && 
                l_mfProduct.isUnitTypeProduct(l_orderUnit.getOrderType())))
        {
            l_blnNorealDiv = true;
        }
        // 1.16.1�j�������̎擾�@@(*)�������������̏ꍇ
        long l_lngUnitProductId = l_mfProduct.getProductId();
        Date l_datOrderBizDate = null;
        if (WEB3ProcessDivDef.SELL.equals(l_strMutualTradeDiv) && 
            l_mfProduct.isUnitTypeProduct(l_orderUnit.getOrderType()) &&
            (l_lngUnitProductId == 3303910181800L || l_lngUnitProductId == 3303911181800L))
        {
            // 1.16.1.1�j�@@get��������������񔭒���(Date)
            //[get�������ɓn���p�����^]  
            // �m�F���������F�@@���N�G�X�g�f�[�^.������ 
            l_datOrderBizDate = WEB3MutualFundTradingTimeManagement.getUnitTypeProductSellOrderBizDate(
                l_request.checkDate);
        }
        //�@@1.16.2�j�������̎擾�@@(*)�������������ȊO�̏ꍇ
        else
        {
            WEB3MutualFundTradingTimeManagement.getOrderBizDate(
                l_request.checkDate, 
                l_orderTypeEnum, 
                l_blnNorealDiv);
        }

        // 1.17�@@���M����m��C���^�Z�v�^�𐶐�����
        WEB3MutualFundCancelConfirmInterceptor l_cancelInterceptor =
            new WEB3MutualFundCancelConfirmInterceptor();

        // 1.18�@@���M����m��C���^�Z�v�^�𓊐M�����}�l�[�W���ɐݒ肷��
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_cancelInterceptor);

        // 1.19�@@�������
        //�@@�|�g�����M�����}�l�[�W��.submitCancelOrder()���R�[������
        OrderSubmissionResult l_submissionResult =
            l_orderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                true);
        if (l_submissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("����������s�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00394,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������s�ł���");
        }
        
		//1.20 ���M�����P��.������� == �h���M�������h and
		//���M�����P��.��n���@@ == �h��s�U���h and
		//�⏕����.get����X().is���M��񎞏o����������() == true �̏�
        WEB3GentradeBranch l_branch = 
            (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        if (OrderTypeEnum.MF_SELL.equals(l_orderUnit.getOrderType()) &&
            WEB3PaymentMethodDef.BANK_TRANSFER.equals(l_mfOrderUnitParams.getPaymentMethod()) &&
            l_branch.isPaymentOrderCreate())
        {
            //1.20.1 �o���������(String, String)
			//[����] 
			// ���ʃR�[�h�F ���M�����P��.�o���������ʃR�[�h 
			// �p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� 
            this.paymentOrderCancel(
                l_mfOrderUnitParams.getPaymentOrderReqNumber(), 
                l_request.password);
        }
        
        // 1.21�@@����]�͍X�V���� 
        //����]�̓T�[�r�X���擾���A�]�͍Čv�Z( )���R�[������B 
        //[�]�͍Čv�Z�ɓn������] 
        //�⏕�����F�擾�����⏕�����I�u�W�F�N�g
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
        
		// getAttribute(String)
		Date l_date = null;            
		l_date = (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
			WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);                
		log.debug("WEB3MutualBuyServiceImpl.submitCancelOrder::l_date = " + l_date);       

        // 1.22�@@���M��������������X�|���X�I�u�W�F�N�g�𐶐����A���^�[������
        WEB3MutualCancelCompleteResponse l_response = 
            (WEB3MutualCancelCompleteResponse) l_request.createResponse();
        
        //���M��������������X�|���X�I�u�W�F�N�g��ݒ肷��
        l_response.lastUpdatedTimestamp =l_date;
        l_response.orderActionId = Long.toString(
            l_mfOrderUnitParams.getOrderId());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�o���������)<BR>
     * �o�������̎�����s���B
     * <BR>
     * �V�[�P���X�}�u�i���M�j�o����������v�Q�ƁB<BR>
     * <BR>
     * @@param l_strRequestNumber - ���ʃR�[�h 
     * @@param l_strPassWord - �p�X���[�h
     * @@throws WEB3BaseException
     * @@roseuid 405575350108
     */
    protected void paymentOrderCancel(String l_strRequestNumber, String l_strPassWord) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "paymentOrderCancel(String l_strRequestNumber, String l_strPassWord)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 get�⏕����(SubAccountTypeEnum)
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
		//�ȉ��̏����ɊY��������o���̒����P�ʂ��擾����B
		//[����]
		//����ID = �擾�����⏕�����I�u�W�F�N�g.getAccountId()�̖߂�l
		//�⏕����ID = �擾�����⏕�����I�u�W�F�N�g.getSubAccountId()�̖߂�l
		//������� = �h�o�������h
		//�o���\���敪 = �h���M���h
		//���ʃR�[�h = ����.���ʃR�[�h
		//�����L����� = �hOPEN�h
		//���擾�ł��Ȃ������ꍇ�́A��O���X���[����B
        
        List l_lisRows = new ArrayList();

        String l_strWhere = 
            "account_id = ? and sub_account_id = ? and order_type = ? " +
            "and payment_application_div = ? and order_request_number = ? and order_open_status = ?";
        
        Object[] l_bindVars = {
            new Long(l_subAccount.getAccountId()),
            new Long(l_subAccount.getSubAccountId()),
            OrderTypeEnum.CASH_OUT, 
            WEB3AioPaymentApplicationDivDef.MF, 
            l_strRequestNumber, 
            OrderOpenStatusEnum.OPEN};   
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRows = l_queryProcessor.doFindAllQuery(
                AioOrderUnitRow.TYPE,
                l_strWhere,
                null, 
                l_bindVars);
        
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
        
        if(l_lisRows == null || l_lisRows.size() == 0)
        {
            //���擾�ł��Ȃ������ꍇ�́A��O���X���[����B
            log.debug("���o�������P�ʂ��擾�ł��Ȃ������ꍇ�B"); 
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        AioOrderUnitParams l_aioOrderUnitParams = (AioOrderUnitParams)l_lisRows.get(0);		
                
        //1.3 CancelOrderSpec(����ID : long)
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_aioOrderUnitParams.getOrderId());
        
        //1.4 �o������X�V�C���^�Z�v�^( )
        WEB3AioCashoutCancelUpdateInterceptor l_interceptor = new WEB3AioCashoutCancelUpdateInterceptor();
        
        //1.5 setThreadLocalPersistenceEventInterceptor(�C���^�Z�v�^ : AioOrderManagerPersistenceEventInterceptor)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //1.6 submitCancelOrder
		//[����] 
		// �⏕�����F �擾�����⏕�����I�u�W�F�N�g 
		// ����������e�F ����������e�I�u�W�F�N�g 
		// �p�X���[�h�F ����.�p�X���[�h 
		// isSkip�����R���F true 
        OrderSubmissionResult l_submitCancelOrderResult = 
            l_aioOrderManager.submitCancelOrder(
                l_subAccount, 
                l_cancelOrderSpec,
                l_strPassWord,
                true);
        
        if (l_submitCancelOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("submitCancelOrder Error" +
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        log.debug(STR_METHOD_NAME);
    }
}
@
