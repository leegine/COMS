head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����T�[�r�X�����N���X(WEB3MutualSellServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ��O�� (���u) �V�K�쐬
Revesion History : 2004/08/23 ������ (���u) ���r���[ 
Revesion History : 2004/12/13 ������ (���u) �c�Ή�
Revesion History : 2005/10/21 ���� (���u) �t�B�f���e�B�Ή�     
Revesion History : 2006/10/16 ���� �d�l�ύX�E���f��500
Revesion History : 2007/02/07 ������ (���u) ���f�� No.528   
Revesion History : 2007/02/25 ������ (���u) ���f�� No.542
Revesion History : 2007/03/26 ��іQ (���u) ���f�� No.550
Revesion History : 2007/04/09 ��іQ (���u) ���f�� No.556,����005
Revesion History : 2008/02/14 �đo�g (���u) ���f�� No.591
Revesion History : 2008/05/07 ���{ (SRA) �b��Ή�
Revesion History : 2008/05/12 ���g (���u) �d�l�ύX No602
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendar;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioProductTypeOrderManagerReusableValidations;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.aio.WEB3AioCashTransOrderUpdateInterceptor;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioProductTypeOrderManagerReusableValidations;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioPaymentApplicationDivDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3MfPaymentMethodCheckDef;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundEstimatedPrice;
import webbroker3.mf.WEB3MutualFundNewOrderConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundNewOrderSpec;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualSellCompleteRequest;
import webbroker3.mf.message.WEB3MutualSellCompleteResponse;
import webbroker3.mf.message.WEB3MutualSellConfirmRequest;
import webbroker3.mf.message.WEB3MutualSellConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualSellService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���M���T�[�r�XImpl)<BR>
 * �����M�����T�[�r�X�����N���X<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualSellServiceImpl extends WEB3MutualClientRequestService implements WEB3MutualSellService 
{ 
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellServiceImpl.class);
        
    /**
     * �����M�����T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate()���܂��́A<BR>
     * submit���()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException 
     * @@roseuid 40556A1F0156
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (l_request instanceof WEB3MutualSellConfirmRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u���M���m�F���N�G�X�g�v�̏ꍇ
            log.exiting(STR_METHOD_NAME);
            WEB3MutualSellConfirmResponse l_confirmResponse = 
                this.validateSell((WEB3MutualSellConfirmRequest) l_request);
            return l_confirmResponse;
        }
        else if (l_request instanceof WEB3MutualSellCompleteRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u���M��񐳊������N�G�X�g�v�̏ꍇ
            log.exiting(STR_METHOD_NAME);
            WEB3MutualSellCompleteResponse l_completeResponse = 
                this.submitSell((WEB3MutualSellCompleteRequest) l_request);
            return l_completeResponse;
        }
        else
        {
            log.debug(STR_METHOD_NAME + "�p�����[�^�^�C�v�s��");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (validate���)<BR>
     * �����M�����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���M)���R���v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)���R���v: <BR>
     *        20((�ۗL�c���������߃`�F�b�N <BR>
     *        �擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��������()�� <BR>
     *         �擾�������\�c�������A�̏ꍇ�͗�O���X���[����B<BR>
     *        �i�ۗL�c���������߃G���[�j<BR>
     *         ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00387<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)���R���v: <BR>
     *        10((�ۗL���Y�̎擾 <BR>
     *        ���M�g���|�W�V�����}�l�[�W��.getAsset( )���R�[������ <BR>
     *         �������ʂ�0���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00204<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)���R���v: <BR>
     *       15.2) is�������S��������()�̖߂�l��true�̏ꍇ�A��O���X���[����<BR>
     *         ���\�c������==0 �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02269<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualSellConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40556A7C0127
     */
    protected WEB3MutualSellConfirmResponse validateSell(WEB3MutualSellConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateSell(" +
            "WEB3MutualSellConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1�j���͓��e�`�F�b�N 
        l_request.validate();

        //1.2�j�⏕�����擾
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) this.getSubAccount();
        //�g�����M�����}�l�[�W�����擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_web3MfProductMgr =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        MutualFundProduct l_mfProduct = null;
        WEB3MutualFundProduct l_web3MfProduct = null;
        WEB3MutualSellConfirmResponse l_response = null;
        try
        {            
            //1.3) �g�����M�����}�l�[�W��.get���M����()���R�[��
            l_mfProduct =
                l_web3MfProductMgr.getMutualFundProduct(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode);
                    
            //�擾�ł��Ȃ��ꍇ�͗�O���X���[����        
            if (l_mfProduct == null)
            {
                log.debug("���M�����擾�ł��Ȃ��ꍇ�G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //�g�����M�����擾
            l_web3MfProduct = (WEB3MutualFundProduct) l_mfProduct;

            //�ڋq�ʎ����~�����`�F�b�N 
            //�g�����M�����}�l�[�W�����擾����
            WEB3MutualFundOrderManager l_web3MfOrderMgr =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();

            //1.4) FinApp�N���X��getCommonOrderValidator()���R�[����
            //�����`�F�b�N�I�u�W�F�N�g���擾����      
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

            //�������擾          
            Timestamp l_tsBizDate = 
                new Timestamp(l_request.orderedDate.getTime());
            
            WEB3GentradeMainAccount l_genMainAccount = 
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            
            //1.5) validate����\�ڋq(�ڋq, ������)
            OrderValidationResult l_validationResult =  
                l_orderValidator.validateAccountForTrading(
                    l_genMainAccount,
                    l_tsBizDate);
            
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N 
            //1.6) ���M������ԊǗ�.validete������t�\()���R�[������
            WEB3MutualFundTradingTimeManagement.validateOrderAccept();
            
            //������Ԃ̍Đݒ�
            //1.7) ���M������ԊǗ�.reset�����R�[�h()���R�[��
            WEB3MutualFundTradingTimeManagement.resetProductCode(
                l_request.mutualProductCode);
                
            //1.8) ��t�����A���t���[�����Z�b�g����
            WEB3MutualFundTradingTimeManagement.setTimestamp();
            
            //�g�����M�|�W�V�����}�l�[�W�����擾����
            WEB3MutualFundPositionManager l_web3MfPositionMgr =
                (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getPositionManager();
            
            //1.9)getAsset(long)
            //�|���M�g���|�W�V�����}�l�[�W��.getAsset( )���R�[������B 
            //�@@[����] 
            //�@@���N�G�X�g.ID�F���YID
            Asset l_asset = null;
            try
            {
                l_asset = l_web3MfPositionMgr.getAsset(Long.parseLong(l_request.id));
            }
            catch (NotFoundException l_ex)
            {
                //1.10)��getAsset()�̖߂�l��0���̏ꍇ�A��O���X���[����B��
                log.error("�ۗL���Y�I�u�W�F�N�g�f�[�^�Ȃ��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
  
            //1.11�j���\�c���������擾����  
            //   �⏕�����F �擾�����⏕�����I�u�W�F�N�g 
            //   �g�����M�����F �擾�����g�����M�����I�u�W�F�N�g
            //   ���YID�F�@@���N�G�X�g.ID
            double l_dblSellPossiblePositionQty = 0;  
            l_dblSellPossiblePositionQty = 
                l_web3MfPositionMgr.calcSellPossiblePositionQty(
                    l_subAccount, l_web3MfProduct, l_request.id);

            //1.12) ���\�c������==0 �̏ꍇ�A��O���X���[����
            if(l_dblSellPossiblePositionQty == 0)
            {
                log.error("�c���Ȃ��G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00390,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                    );    
            }
                     
            //�����R��
            String l_strProcessDiv = null;  //�����敪
            //(*) �擾�����g�����M�����I�u�W�F�N�g.is�O��MMF()==true�ꍇ��
            //�h2�F���h���w��
            //(*) ���M���m�F���N�G�X�g.�������@@�̒l���h0�F��񐿋��h�̏ꍇ�� 
            //  �h2�F���h���w��
            if (l_web3MfProduct.isFrgnMmf() 
                || WEB3ClaimDivDef.SELL.equals(l_request.sellBuyDiv))
            {
                l_strProcessDiv = WEB3ProcessDivDef.SELL;
            }
            //(*) ���M���m�F���N�G�X�g.�������@@�̒l���h1�F���搿���h�̏ꍇ�� 
            //  �h4�F����h���w��
            else if (WEB3ClaimDivDef.BUY.equals(l_request.sellBuyDiv))
            {
                l_strProcessDiv = WEB3ProcessDivDef.PURCHASE;
            }

            log.debug("�������@@ = " + l_strProcessDiv);
            NewOrderValidationResult l_newOrderValidationResult = null;
            
            //1.13)�|�g�����M�����}�l�[�W��.validate�V�K����()���R�[�����A�����R�����s��
			//�mvalidate�V�K�����ɓn���p�����^�n  
			//�⏕�����F �擾�����⏕�����I�u�W�F�N�g  
			//�g�����M�����F �擾�����g�����M�����I�u�W�F�N�g  
			//�������z���ʁF ���N�G�X�g�f�[�^.����  
			//�����敪�F
            //(*) �擾�����g�����M�����I�u�W�F�N�g.is�O��MMF()==true�ꍇ��
            //�h2�F���h���w��
			//(*) ���N�G�X�g�f�[�^.�������@@�̒l���h0�F��񐿋��h�̏ꍇ�� 
			//�h2�F���h���w�� 
			//(*) ���N�G�X�g�f�[�^.�������@@�̒l���h1�F���搿���h�̏ꍇ�� 
			//�h4�F����h���w�� 
			//��n���@@�F ���N�G�X�g�f�[�^.��n���@@ 
			//�w����@@�F ���N�G�X�g�f�[�^.�w����@@ 
			//�抷������F null 
			//�ŋ敪�F�ۗL���Y�e�[�u��Params.get�ŋ敪()�̖߂�l
            //���ϕ��@@�F ���N�G�X�g�f�[�^.���ϕ��@@
            double l_dblOrderQuantity = 0.0;
            if (!WEB3StringTypeUtility.isEmpty(l_request.mutualOrderQuantity))
            {
                l_dblOrderQuantity = 
                    Double.parseDouble(l_request.mutualOrderQuantity);
            }

            l_newOrderValidationResult = l_web3MfOrderMgr.validateNewOrder(
                l_subAccount, 
                l_web3MfProduct, 
                l_dblOrderQuantity,
                l_strProcessDiv,
                l_request.deliveryDiv,
                l_request.specifyDiv,
                null,
                l_asset.getTaxType(),
                l_request.settleDiv);

            if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("�����R���G���[");
                throw new WEB3BusinessLayerException(
                    l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            // 1.14�jis���z�w����
			//[����] 
			// �⏕�����F�擾�����⏕���� 
			// �g�����M�����F�擾�����g�����M���� 
			// �ŋ敪�FgetAsset()�ɂ���Ď擾�����ۗL���Y�̐ŋ敪
            WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
                (WEB3MutualFundOrderManagerReusableValidationsCheck)
                    MutualFundProductTypeOrderManagerReusableValidations.getInstance();
            
            boolean l_blnIsPriceDesignateCancelling =
                l_validationsCheck.isPriceDesignateCancelling(
                    l_subAccount, 
                    l_web3MfProduct, 
                    l_asset.getTaxType());
            
            //1.15) ����F
            //�ȉ��ɓ��Ă͂܂�ꍇ�A�������S�������߃`�F�b�N������B
            //���N�G�X�g�f�[�^.�w����@@ != �h�S���h and ((���N�G�X�g�f�[�^.�w����@@ == �h���z
            //and (���M����.is�O��MMF == false or ���N�G�X�g�f�[�^.���ϋ敪 != �h�O�݁h))
            //or is���z�w���񒆁i�j�̖߂�l == true�j
            boolean l_blnIsSellQtyLimitRateExcess = false;
            if (!WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) && 
                ((WEB3SellDivDef.MONEY_DESIGNATE.equals(l_request.specifyDiv) &&
                    (!l_web3MfProduct.isFrgnMmf() ||
                    !WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_request.settleDiv))) ||
                    l_blnIsPriceDesignateCancelling))
            {
                String l_strTaxType = null;
                if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
                {
                    l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
                }
                else if(TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
                {
                    l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
                }
                //1.15.1)is�������S��������
    			//�⏕�����F �擾�����⏕�����I�u�W�F�N�g 
    			//�����F �擾�����抷���g�����M�����I�u�W�F�N�g 
    			//�����i�抷��j�F null 
    			//���YID�F���N�G�X�g.ID 
    			//���\�c�������Fcalc���\�c������()�̖߂�l 
    			//�����敪�F�h2�F���h���w��  
    			//�������ʁF ���N�G�X�g.���� 
    			//�w����@@�F ���N�G�X�g.�w����@@ 
    			//���ϕ��@@�F ���N�G�X�g.���ϕ��@@ 
    			//�������@@�F ���N�G�X�g.�������@@ 
    			//�����敪�F �擾�����ۗL���Y�e�[�u��Params.get�ŋ敪()�̖߂�l  
    			//�������F ���N�G�X�g.������
                l_blnIsSellQtyLimitRateExcess =
    	            l_validationsCheck.isSellQtyLimitRateExcess(
    	                l_subAccount,
    	                l_web3MfProduct,
    	                null,
    	                l_request.id,
    	                l_dblSellPossiblePositionQty,
    	                WEB3ProcessDivDef.SELL,
    	                l_dblOrderQuantity,
    	                l_request.specifyDiv,
    	                l_request.settleDiv,
    	                l_request.sellBuyDiv,
    	                l_strTaxType,
    	                l_request.orderedDate);
                
                //1.15.2) is�������S��������()�̖߂�l��true�̏ꍇ�A��O���X���[����
                if (l_blnIsSellQtyLimitRateExcess)
                {
                    log.error("�������S�����𒴉߂��Ă���B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02269,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�������S�����𒴉߂��Ă���B");
                }   
            } 
            
            //1.16 <*>����F���N�G�X�g�f�[�^.�w����@@�̒l���h�S���h�̏ꍇ�A 
            //���N�G�X�g�f�[�^.���ʂɉ��\�c������ ��ݒ肷��B
            if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv)) 
            {
                l_dblOrderQuantity = l_dblSellPossiblePositionQty;   
            }
            
            //1.17) <*>����F���N�G�X�g�f�[�^.��n���@@�̒l == �h1�F��s�U���݁h�̏ꍇ�A
            //��n���@@�̃`�F�b�N�����{����B
            //���N�G�X�g�f�[�^.��n���@@�̒l == �h1�F��s�U���݁h�̏ꍇ�A��n���@@�̃`�F�b�N�����{����B
            if (WEB3PaymentMethodDef.BANK_TRANSFER.equals(l_request.deliveryDiv))
            {
                //1.17.1)validate��n���@@(SubAccount, String,�g�����M����)
				//�m�ɓn���p�����^�n 
				//�⏕�����F�擾�����⏕�����I�u�W�F�N�g 
				//���YID�F���N�G�X�g�f�[�^.ID
                //�g�����M�����F�擾�����g�����M����
                this.validatePaymentMethod(l_subAccount, l_request.id, l_web3MfProduct);
            }

            // 1.18�j�������̎擾�@@(*)�������������̏ꍇ
            long l_lngUnitProductId = l_web3MfProduct.getProductId();
            Date l_datOrderBizDate = null;
            if (l_lngUnitProductId == 3303910181800L || l_lngUnitProductId == 3303911181800L)
            {
                // 1.18.1�j�@@get��������������񔭒���(Date)
                //[get�������ɓn���p�����^]  
                // �m�F���������F�@@���N�G�X�g�f�[�^.������ 
                l_datOrderBizDate = WEB3MutualFundTradingTimeManagement.getUnitTypeProductSellOrderBizDate(
                    l_request.orderedDate);
            }
            // 1.19�j�������̎擾�@@(*)�������������ȊO�̏ꍇ
            else
            {
                // 1.19.1�j�@@get������(Date, OrderTypeEnum, boolean)
                //[get�������ɓn���p�����^]  
                // �m�F���������F�@@���N�G�X�g�f�[�^.������ 
                // ������ʁ@@�@@�@@�F  OrderTypeEnum.�����M�������� 
                // �ꊇ�敪�@@�@@�@@�F�@@�擾�����g�����M�����Dis������������ 
                // [is�������������̃p�����^] 
                // ������� �F OrderTypeEnum.�����M��������
                l_datOrderBizDate = WEB3MutualFundTradingTimeManagement.getOrderBizDate(
                    l_request.orderedDate,
                    OrderTypeEnum.MF_SELL,
                    l_web3MfProduct.isUnitTypeProduct(OrderTypeEnum.MF_SELL));

            }

            //1.19.get����(Institution, String, Date)
            //�mget�����ɓn���p�����^�n 
            //  �،���ЁF �擾�����⏕����.getInstitution()�̖߂�l 
            //  �����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h 
            //  �������@@�@@�F�擾����������
            Date l_datExecutedDate = 
                l_web3MfProductMgr.getExecutedDate(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode,
                    l_datOrderBizDate);

            // �������������̏ꍇ�̖����擾�y�b��Ή��z
            long l_lngProductId = l_web3MfProduct.getProductId();
            if (l_lngProductId == 3303910181800L || l_lngProductId == 3303911181800L)
            {
                TradingCalendar l_tradingCalendar = null;
                if (l_lngProductId == 3303910181800L)
                {
                    l_tradingCalendar = GtlUtils.getFinObjectManager().getTradingCalendar(
                        330003910181800L);
                }
                else
                {
                    l_tradingCalendar = GtlUtils.getFinObjectManager().getTradingCalendar(
                        330003911181800L);
                }
                l_datExecutedDate = l_tradingCalendar.roll(l_datOrderBizDate, 2);
            }

            //1.20.get��n��(Institution, String, boolean, Date)
            //�mget��n���ɓn���p�����^�n 
            //�،���ЁF �擾�����⏕����.getInstitution()�̖߂�l 
            //�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h 
            //is���t�F false 
            //�����F�擾��������
            Timestamp l_tsDeliveryDate =
	            l_web3MfProductMgr.getDeliveryDate(
	                l_subAccount.getInstitution(),
	                l_request.mutualProductCode,
	                false,
                    l_datExecutedDate);
            
            //1.21)�T�Z��n������Z�o����B
            WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = null;

            //�T�Z��n����擾 
			//�mcalc�T�Z��n����ɓn���p�����^�n 
			//�⏕�����F �擾�����⏕�����I�u�W�F�N�g 
			//�����F �擾�����g�����M�����I�u�W�F�N�g 
			//�����i�抷��j�F null 
			//�����敪�F�h2�F���h���w�� 
			//�������ʁF ���N�G�X�g�f�[�^.���� 
			//�w����@@�F 
			//(*) ���N�G�X�g�f�[�^.�w����@@���h2�F�S���h�̏ꍇ�́h4�F�����h��ݒ肷��B 
			//(*) �����łȂ��ꍇ�̓��N�G�X�g�f�[�^.�w����@@��ݒ肷��B 
			//���ϕ��@@�F ���N�G�X�g�f�[�^.���ϕ��@@ 
			//�������@@�F ���N�G�X�g�f�[�^.�������@@ 
			//�����敪�F �擾�����ۗL���Y�e�[�u��Params.get�ŋ敪()�̖߂�l
			//�����`���l���F �Z�b�V��������擾���������`���l�� 
			//�������F ���N�G�X�g�f�[�^.������ 

            //�w����@@�F
            String l_strSpecifyDiv = null;
            log.debug("���M���m�F���N�G�X�g.�w����@@" + l_request.specifyDiv);
            //(*) ���M���m�F���N�G�X�g.�w����@@���h2�F�S���h�̏ꍇ�́h4�F�����h��ݒ肷��
            if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
            {                
                l_strSpecifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
                log.debug("�w����@@ change 4");
            }
            //(*) �����łȂ��ꍇ�͓��M���m�F���N�G�X�g.�w����@@��ݒ肷��
            else
            {
                l_strSpecifyDiv = l_request.specifyDiv;
            }
                
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);  
            String l_strOrderChanel = 
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);
                
            String l_strTaxType = null;
            if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
            {
                l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
            }
            else if(TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
            {
                l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
            //�g�����M�����}�l�[�W��.calc�T�Z��n���()���R�[�����āA
            //�T�Z��n����I�u�W�F�N�g���擾����
            l_mfEstimatedPrice = l_web3MfOrderMgr.calcEstimateDeliveryAmount(
                l_subAccount,
                l_web3MfProduct,
                null,
                WEB3ProcessDivDef.SELL,
                l_dblOrderQuantity,
                l_strSpecifyDiv,
                l_request.settleDiv,
                l_request.sellBuyDiv,
                l_strTaxType,
                l_strOrderChanel,
                l_request.orderedDate);
              
            //1.22) �擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��������() �� 
            //�擾�������\�c�������̏ꍇ�A��O���X���[����    
            if (l_mfEstimatedPrice.getEstimatedQty() > l_dblSellPossiblePositionQty)
            {
                log.debug("�ۗL�c���������߃`�F�b�N�G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00387,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.23)is���M��񎞏o����������( )
            WEB3GentradeBranch l_branch = 
                (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
            boolean l_blnIsPaymentOrderCreate = l_branch.isPaymentOrderCreate();
            
            //1.24) is���M��񎞏o����������()�̖߂�l == true and 
            //      ���N�G�X�g�f�[�^.��n���@@�@@== 1:��s�U�� �̏ꍇ�A�o�����z�͈̓`�F�b�N���s�Ȃ��B
            if (l_blnIsPaymentOrderCreate && 
                WEB3PaymentMethodDef.BANK_TRANSFER.equals(l_request.deliveryDiv))
            {
                //1.24.1)validate�o�����z�͈�(double, Date)
    			//[validate�o�����z�͈�()�ɓn���p�����^] 
    			//�T�Z��n����F�@@�擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��n���()�̖߂�l 
    			//��n���F�@@get��n��()�̖߂�l
                this.validatePaymentMoney(
                    l_mfEstimatedPrice.getEstimatedPrice(), 
                    l_tsDeliveryDate);
            }
            
            //1.25) ����ID���̔Ԃ���
            long l_lngOrderId = l_web3MfOrderMgr.createNewOrderId();
          
            //1.26�j���M���m�F���X�|���X�I�u�W�F�N�g�𐶐�
            l_response = (WEB3MutualSellConfirmResponse) l_request.createResponse();
            
			//1.27) �v���p�e�B�E�Z�b�g[���M���m�F���X�|���X�ɐݒ肷��l] 
            //��񒍕����e�x���敪�F
            if (l_blnIsSellQtyLimitRateExcess)
            {
                l_response.sellWarningType = "1";
            }
            else
            {
                l_response.sellWarningType = null;
            }
            
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_request.settleDiv))
            {
                l_response.estimatedPriceCurrencyCode = WEB3MFOrderQuantityType.EN;
            }
            else if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_request.settleDiv))
            {
                l_response.estimatedPriceCurrencyCode =
                    l_web3MfProduct.getCurrencyCode();
            }
            
            //�T�Z��n����F
            l_response.estimatedPrice = 
                WEB3StringTypeUtility.formatNumber(l_mfEstimatedPrice.getEstimatedPrice());
            //�T�Z���������F
            l_response.estimatedQty = 
                WEB3StringTypeUtility.formatNumber(l_mfEstimatedPrice.getEstimatedQty());
            
            l_response.orderId = l_lngOrderId + "";

        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit���)<BR>
     * �����M�����o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���M)���o�^�v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)���o�^�v: <BR>
     *       23.2((�ۗL�c���������߃`�F�b�N <BR>
     *        �擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��������()�� <BR>
     *         �擾�������\�c�������A�̏ꍇ�͗�O���X���[����B<BR>
     *        �i�ۗL�c���������߃G���[�j<BR>
     *         ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00387<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)���o�^�v: <BR>
     *        11((�ۗL���Y�̎擾 <BR>
     *        ���M�g���|�W�V�����}�l�[�W��.getAsset( )���R�[������ <BR>
     *         �������ʂ�0���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00204<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)���o�^�v: <BR>
     *       17.2) is�������S��������()�̖߂�l��true�̏ꍇ�A��O���X���[����<BR>
     *         ���\�c������==0 �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02269<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualSellCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40556A8301A4
     */
    protected WEB3MutualSellCompleteResponse submitSell(WEB3MutualSellCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitSell(" +
            "WEB3MutualSellCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1�j���͓��e�`�F�b�N 
        l_request.validate();

        //1.2�j�⏕�����擾
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount) getSubAccount();
        //�g�����M�����}�l�[�W�����擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_web3MfProductMgr =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        MutualFundProduct l_mfProduct = null;
        WEB3MutualFundProduct l_web3MfProduct = null;
        WEB3MutualSellCompleteResponse l_response = null;
        try
        {
            //1.3) �g�����M�����}�l�[�W��.get���M����()���R�[��
            l_mfProduct =
                l_web3MfProductMgr.getMutualFundProduct(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode);
                    
            //�擾�ł��Ȃ��ꍇ�͗�O���X���[����        
            if (l_mfProduct == null)
            {
                log.debug("Error in ���M�������擾");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //�g�����M�����擾
            l_web3MfProduct = (WEB3MutualFundProduct) l_mfProduct;
            
            //1.4) �ڋq�ʎ����~�����`�F�b�N 
            //�g�����M�����}�l�[�W�����擾����
            WEB3MutualFundOrderManager l_web3MfOrderMgr =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();
                    
            //FinApp�N���X��getCommonOrderValidator()���R�[����
            //�����`�F�b�N�I�u�W�F�N�g���擾����      
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();          

			//�������̎擾
            Timestamp l_datBizDate = 
                new Timestamp(l_request.orderedDate.getTime());
            
            //1.5) validate����\�ڋq
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
            
            //1.6)get�㗝���͎�( )
            Trader l_trader = this.getTrader();

            //1.7) validate����p�X���[�h( )���R�[������B  
            OrderValidationResult l_validationResultPassword = 
                l_orderValidator.validateTradingPassword(
                    this.getTrader(),
                    l_subAccount,
                    l_request.password);
            
            if (l_validationResultPassword.getProcessingResult().isFailedResult())
            {
                log.debug("����p�X���[�h���s���ł�");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00009,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            // ��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N 
            //1.8) ���M������ԊǗ�.validete������t�\()���R�[������
            WEB3MutualFundTradingTimeManagement.validateOrderAccept();

            // ������Ԃ̍Đݒ�
            //1.9) ���M������ԊǗ�.reset�����R�[�h()���R�[��
            WEB3MutualFundTradingTimeManagement.resetProductCode(
                l_request.mutualProductCode);
                
            //1.10) ��t�����A���t���[�����Z�b�g����
            WEB3MutualFundTradingTimeManagement.setTimestamp();
            
            //�g�����M�|�W�V�����}�l�[�W�����擾����
            WEB3MutualFundPositionManager l_web3MfPositionMgr =
                (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getPositionManager();
            
            //1.11) getAsset(���YID)
            //getAsset()�̖߂�l��0���̏ꍇ�A��O���X���[����
            //�|���M�g���|�W�V�����}�l�[�W��.getAsset( )���R�[������B 
            //�@@[����] 
            //�@@���N�G�X�g.ID�F���YID
            //�������ʂ�0���̏ꍇ�A��O���X���[����B 
            Asset l_asset = null;
            try
            {
                l_asset = l_web3MfPositionMgr.getAsset(Long.parseLong(l_request.id));
            }
            //1.12 �Y���ۗL���Y0���̏ꍇ�A��O���X���[����
            catch (NotFoundException l_ex)
            {
                log.error("�ۗL���Y�I�u�W�F�N�g�f�[�^�Ȃ��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            
            if(l_asset == null)
            {
                log.debug("�ۗL���Y�I�u�W�F�N�g�f�[�^�Ȃ��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.13�j���\�c���������擾���� 
            // calc���\�c������(�⏕����, �g�����M����, ���YID)
            // �mcalc���\�c�������ɓn���p�����^�n
            // �@@�⏕�����F �擾�����⏕�����I�u�W�F�N�g 
            // �@@�g�����M�����F �擾�����g�����M�����I�u�W�F�N�g
            //  ���YID�F�@@���N�G�X�g.ID
            double l_dblSellPossiblePositionQty = 0;  
            l_dblSellPossiblePositionQty = 
                l_web3MfPositionMgr.calcSellPossiblePositionQty(
                    l_subAccount, l_web3MfProduct, l_request.id);

            // 1.14) ���\�c������==0 �̏ꍇ�A��O���X���[����
            if(l_dblSellPossiblePositionQty == 0)
            {
                log.error("���\�c������������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00390,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                    );    
            }

            //1.15�j�����R��
			//�mvalidate�V�K�����ɓn���p�����^�n  
			//�⏕�����F �擾�����⏕�����I�u�W�F�N�g  
			//�g�����M�����F �擾�����g�����M�����I�u�W�F�N�g  
			//�������z���ʁF ���N�G�X�g�f�[�^.����  
			//�����敪�F
            //(*) �擾�����g�����M�����I�u�W�F�N�g.is�O��MMF()==true�ꍇ��
            //�h2�F���h���w��
			//(*) ���N�G�X�g�f�[�^.�������@@�̒l���h0�F��񐿋��h�̏ꍇ�� 
			//�h2�F���h���w�� 
			//(*) ���N�G�X�g�f�[�^.�������@@�̒l���h1�F���搿���h�̏ꍇ�� 
			//�h4�F����h���w�� 
			//��n���@@�F ���N�G�X�g�f�[�^.��n���@@ 
			//�w����@@�F ���N�G�X�g�f�[�^.�w����@@ 
			//�抷������F null 
			//�ŋ敪�F �擾�����ۗL���YParams.get�ŋ敪()�̖߂�l
            //���ϕ��@@�F ���N�G�X�g�f�[�^.���ϕ��@@

            String l_strProcessDiv = null;  //�����敪
            //(*) ���M���m�F���N�G�X�g.�������@@�̒l���h0�F��񐿋��h�̏ꍇ��
            //  �h2�F���h���w��
            //(*) �擾�����g�����M�����I�u�W�F�N�g.is�O��MMF()==true�ꍇ��
            //  �h2�F���h���w��
            if (l_web3MfProduct.isFrgnMmf() 
                || WEB3ClaimDivDef.SELL.equals(l_request.sellBuyDiv))
            {
                l_strProcessDiv = WEB3ProcessDivDef.SELL;
            }
            //(*) ���M���m�F���N�G�X�g.�������@@�̒l���h1�F���搿���h�̏ꍇ�� 
            //  �h4�F����h���w��
            else if (WEB3ClaimDivDef.BUY.equals(l_request.sellBuyDiv))
            {
                l_strProcessDiv = WEB3ProcessDivDef.PURCHASE;
            }
            
            double l_dblOrderQuantity = 0.0;
            if (!WEB3StringTypeUtility.isEmpty(l_request.mutualOrderQuantity))
            {
                l_dblOrderQuantity = 
                    Double.parseDouble(l_request.mutualOrderQuantity);
            }
            NewOrderValidationResult l_newOrderValidationResult = null;
            //�|�g�����M�����}�l�[�W��.validate�V�K����()���R�[�����A�����R�����s��
            l_newOrderValidationResult = l_web3MfOrderMgr.validateNewOrder(
                l_subAccount, 
                l_web3MfProduct, 
                l_dblOrderQuantity,
                l_strProcessDiv,
                l_request.deliveryDiv,
                l_request.specifyDiv,
                null,
                l_asset.getTaxType(),
                l_request.settleDiv);

            if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("�����R���G���[");
                throw new WEB3BusinessLayerException(
                    l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.16) is���z�w����
            WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
                (WEB3MutualFundOrderManagerReusableValidationsCheck)
                    MutualFundProductTypeOrderManagerReusableValidations.getInstance();
            boolean l_blnIsPriceDesignateCancelling =
                l_validationsCheck.isPriceDesignateCancelling(
                    l_subAccount, 
                    l_web3MfProduct, 
                    l_asset.getTaxType());
            
            //1.17) ����F
            //�ȉ��ɓ��Ă͂܂�ꍇ�A�������S�������߃`�F�b�N������B
            //���N�G�X�g�f�[�^.�w����@@ != �h�S���h and ((���N�G�X�g�f�[�^.�w����@@ == �h���z
            //and (���M����.is�O��MMF == false or ���N�G�X�g�f�[�^.���ϋ敪 != �h�O�݁h))
            //or is���z�w���񒆁i�j�̖߂�l == true�j
            if (!WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) && 
                ((WEB3SellDivDef.MONEY_DESIGNATE.equals(l_request.specifyDiv) &&
                    (!l_web3MfProduct.isFrgnMmf() ||
                    !WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_request.settleDiv))) ||
                    l_blnIsPriceDesignateCancelling))
            {
                String l_strTaxType = null;
                if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
                {
                    l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
                }
                else if(TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
                {
                    l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
                }
                //1.17.1)is�������S��������
    			//�⏕�����F �擾�����⏕�����I�u�W�F�N�g 
    			//�����F �擾�����g�����M�����I�u�W�F�N�g 
    			//�����i�抷��j�F null 
    			//���YID�F���N�G�X�g.ID 
    			//���\�c�������Fcalc���\�c������()�̖߂�l 
    			//�����敪�F�h2�F���h���w�� 
    			//�������ʁF ���N�G�X�g.���� 
    			//�w����@@�F ���N�G�X�g.�w����@@ 
    			//���ϕ��@@�F ���N�G�X�g.���ϕ��@@ 
    			//�������@@�F ���N�G�X�g.�������@@ 
    			//�����敪�F �擾�����ۗL���Y�e�[�u��Params.get�ŋ敪()�̖߂�l  
    			//�������F ���N�G�X�g.������
                boolean l_blnIsSellQtyLimitRateExcess =
    	            l_validationsCheck.isSellQtyLimitRateExcess(
    	                l_subAccount,
    	                l_web3MfProduct,
    	                null,
    	                l_request.id,
    	                l_dblSellPossiblePositionQty,
    	                WEB3ProcessDivDef.SELL,
    	                l_dblOrderQuantity,
    	                l_request.specifyDiv,
    	                l_request.settleDiv,
    	                l_request.sellBuyDiv,
    	                l_strTaxType,
    	                l_request.orderedDate);
                
                //1.17.2) is�������S��������()�̖߂�l��true�̏ꍇ�A��O���X���[����
                if (l_blnIsSellQtyLimitRateExcess)
                {
                    log.error("�������S�����𒴉߂��Ă���B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02269,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�������S�����𒴉߂��Ă���B");
                }   
            } 
            
            //1.18) ����F���N�G�X�g�f�[�^.�w����@@�̒l���h�S���h�̏ꍇ�A
            //���N�G�X�g�f�[�^.���ʂ� ���\�c������ ��ݒ肷��B
            if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
            {
                l_dblOrderQuantity = l_dblSellPossiblePositionQty;
            }
            
            //1.19) <*>����F���N�G�X�g�f�[�^.��n���@@�̒l���h1�F��s�U���݁h�̏ꍇ
            //�A��n���@@�̃`�F�b�N�����{����B
            if (WEB3PaymentMethodDef.BANK_TRANSFER.equals(l_request.deliveryDiv))
            {
                //1.19.1)validate��n���@@(SubAccount, String,�g�����M����)
                this.validatePaymentMethod(l_subAccount, l_request.id, l_web3MfProduct);
            }

            // 1.20�j�������̎擾�@@(*)�������������̏ꍇ
            long l_lngUnitProductId = l_web3MfProduct.getProductId();
            Date l_datOrderBizDate = null;
            if (l_lngUnitProductId == 3303910181800L || l_lngUnitProductId == 3303911181800L)
            {
                // 1.20.1�j�@@get��������������񔭒���(Date)
                //[get�������ɓn���p�����^]  
                // �m�F���������F�@@���N�G�X�g�f�[�^.������ 
                l_datOrderBizDate = WEB3MutualFundTradingTimeManagement.getUnitTypeProductSellOrderBizDate(
                    l_request.orderedDate);
            }
            // 1.21�j�������̎擾�@@(*)�������������ȊO�̏ꍇ
            else
            {
                //1.21.1get������(Date, OrderTypeEnum, boolean)
                //[get�������ɓn���p�����^]  
                //�m�F���������F�@@���N�G�X�g�f�[�^.������ 
                //������ʁ@@�@@�@@�F  OrderTypeEnum.�����M�������� 
                //�ꊇ�敪�@@�@@�@@�F�@@�擾�����g�����M�����Dis������������ 
                //[is�������������̃p�����^] 
                //������� �F OrderTypeEnum.�����M��������
                l_datOrderBizDate = 
                    WEB3MutualFundTradingTimeManagement.getOrderBizDate(
                        l_request.orderedDate, 
                        OrderTypeEnum.MF_SELL, 
                        l_web3MfProduct.isUnitTypeProduct(OrderTypeEnum.MF_SELL));

            }

            //1.21.get����(Institution, String, Date)
            Date l_datExecutedDate = null;
            //�mget�����ɓn���p�����^�n 
            //�،���ЁF �擾�⏕����.getInstitution()�̖߂�l 
            //�����R�[�h�F �擾�����g�����M����.getProductCode()�̖߂�l 
            //�������F�擾����������
            l_datExecutedDate = l_web3MfProductMgr.getExecutedDate(
                l_subAccount.getInstitution(),
                l_web3MfProduct.getProductCode(),
                l_datOrderBizDate);

            // �������������̏ꍇ�̖����擾�y�b��Ή��z
            long l_lngProductId = l_web3MfProduct.getProductId();
            if (l_lngProductId == 3303910181800L || l_lngProductId == 3303911181800L)
            {
                TradingCalendar l_tradingCalendar = null;
                if (l_lngProductId == 3303910181800L)
                {
                    l_tradingCalendar = GtlUtils.getFinObjectManager().getTradingCalendar(
                        330003910181800L);
                }
                else
                {
                    l_tradingCalendar = GtlUtils.getFinObjectManager().getTradingCalendar(
                        330003911181800L);
                }
                l_datExecutedDate = l_tradingCalendar.roll(l_datOrderBizDate, 2);
            }

            //1.22.get��n��(Institution, String, boolean, Date)
            Timestamp l_tsDeliveryDate = null;
            //�mget��n���ɓn���p�����^�n 
            //�،���ЁF �擾�����⏕����.getInstitution()�̖߂�l 
            //�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h 
            //is���t�F false 
            //�����F�擾��������
            l_tsDeliveryDate = l_web3MfProductMgr.getDeliveryDate(
                l_subAccount.getInstitution(),
                l_request.mutualProductCode,
                false,
                l_datExecutedDate);
            
            //1.23 �T�Z��n������Z�o����B
            WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = null;
            
            //�T�Z��n����擾 
			//�mcalc�T�Z��n����ɓn���p�����^�n 
			//�⏕�����F �擾�����⏕�����I�u�W�F�N�g 
			//�����F �擾�����g�����M�����I�u�W�F�N�g 
			//�����i�抷��j�F null 
			//�����敪�F�h2�F���h���w�� 
			//�������ʁF ���N�G�X�g�f�[�^.���� 
			//�w����@@�F 
			//(*) ���N�G�X�g�f�[�^.�w����@@���h2�F�S���h�̏ꍇ�́h4�F�����h��ݒ肷��B 
			//(*) �����łȂ��ꍇ�̓��N�G�X�g�f�[�^.�w����@@��ݒ肷��B 
			//���ϕ��@@�F ���N�G�X�g�f�[�^.���ϕ��@@ 
			//�������@@�F ���N�G�X�g�f�[�^.�������@@ 
			//�����敪�F �擾�����ۗL���Y�e�[�u��Params.get�ŋ敪()�̖߂�l  
			//�����`���l���F �Z�b�V��������擾���������`���l�� 
			//�������F ���N�G�X�g�f�[�^.������ 

            //�w����@@�F
            String l_strSpecifyDiv = null;
            log.debug("���M���m�F���N�G�X�g.�w����@@" + l_request.specifyDiv);
            //(*) ���M���m�F���N�G�X�g.�w����@@���h2�F�S���h�̏ꍇ�́h4�F�����h��ݒ肷��
            if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
            {                
                l_strSpecifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
                log.debug("�w����@@ change 4");
            }
            //(*) �����łȂ��ꍇ�͓��M���m�F���N�G�X�g.�w����@@��ݒ肷��
            else
            {
                l_strSpecifyDiv = l_request.specifyDiv;
            }
                
            String l_strOrderChanel = this.getLoginChannel();
                
            String l_strTaxType = null;
            if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
            {
                l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
            }
            else if(TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
            {
                l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
            //�g�����M�����}�l�[�W��.calc�T�Z��n���()���R�[�����āA
            //�T�Z��n����I�u�W�F�N�g���擾����
            l_mfEstimatedPrice = l_web3MfOrderMgr.calcEstimateDeliveryAmount(
                l_subAccount,
                l_web3MfProduct,
                null,
                WEB3ProcessDivDef.SELL,
                l_dblOrderQuantity,
                l_strSpecifyDiv,
                l_request.settleDiv,
                l_request.sellBuyDiv,
                l_strTaxType,
                l_strOrderChanel,
                l_request.orderedDate);
                
            //1.24) �擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��������() �� 
            //�擾�������\�c�������̏ꍇ�A��O���X���[����   
            if (l_mfEstimatedPrice.getEstimatedQty() > l_dblSellPossiblePositionQty)
            {
                log.debug("�ۗL�c���������߃`�F�b�N�G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00387,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ۗL�c���������߃`�F�b�N�G���[");
            }
            
            //1.25) is���M��񎞏o����������( )
            WEB3GentradeBranch l_branch = 
                (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
            boolean l_blnIsPaymentOrderCreate = l_branch.isPaymentOrderCreate();
            
            //1.26) is���M��񎞏o����������()�̖߂�l == true and 
            //���N�G�X�g�f�[�^.��n���@@�@@== 1:��s�U�� �̏ꍇ�A�o���`�[���쐬����B
            String l_strPaymentOrderReqNumber = null;
            if (l_blnIsPaymentOrderCreate && 
                WEB3PaymentMethodDef.BANK_TRANSFER.equals(l_request.deliveryDiv))
            {
                //1.26.1) create�o���`�[�쐬
				//[create�o���`�[�쐬�ɓn���p�����^] 
				//�㗝���͎ҁF�@@this.get�㗝���͎�()�̖߂�l 
				//�T�Z��n����F�擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��n���()�̖߂�l 
				//��n���F�@@get��n��()�̖߂�l 
				//�Ïؔԍ��F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
                l_strPaymentOrderReqNumber =
	                this.createPaymentTicket(
	                    l_trader, 
	                    l_mfEstimatedPrice.getEstimatedPrice(), 
	                    l_tsDeliveryDate, 
	                    l_request.password);
            }

            //1.27�j���M�V�K�����m��C���^�Z�v�^�𐶐�����
            WEB3MutualFundNewOrderConfirmInterceptor l_mfNewOrderConfirmInterceptor = 
                new WEB3MutualFundNewOrderConfirmInterceptor();
            
            //1.28) �p�����[�^�̃Z�b�g
            // ���M�V�K�����m��C���^�Z�v�^�Ɏ�n����ݒ肷��
            // ���M�V�K�����m��C���^�Z�v�^.set��n��()���R�[������
            l_mfNewOrderConfirmInterceptor.setDeliveryDate(l_tsDeliveryDate);
            
            // ���M�V�K�����m��C���^�Z�v�^�ɒ����`���l����ݒ肷��
            // ���M�V�K�����m��C���^�Z�v�^.set�����`���l��()
            l_mfNewOrderConfirmInterceptor.setOrderChannel(
                this.getLoginChannel());
                
            // ���M�V�K�����m��C���^�Z�v�^�Ɍv�Z����z��ݒ肷��
            // ���M�V�K�����m��C���^�Z�v�^.set�v�Z����z()���R�[������
            if (l_web3MfProduct.isFrgnMmf())
            {
                l_mfNewOrderConfirmInterceptor.setConstantValue(Double.NaN);
            }
            else
            {
                l_mfNewOrderConfirmInterceptor.setConstantValue(
                    l_web3MfProduct.getSellValue());
            }
            // ���M�V�K�����m��C���^�Z�v�^�Ɍv�Z����z�i�抷��j��ݒ肷��
            // ���M�V�K�����m��C���^�Z�v�^.set�v�Z����z�i�抷��j()���R�[������
            l_mfNewOrderConfirmInterceptor.setSwitchingConstantValue(Double.NaN);   
            
            // ���M�V�K�����m��C���^�Z�v�^�Ɋ���z�K�p����ݒ肷��
            // ���M�V�K�����m��C���^�Z�v�^.set����z�K�p��()���R�[������
            l_mfNewOrderConfirmInterceptor.setConstantValueAppDate(
                l_web3MfProduct.getConstantValueAppDate());
                
            // ���M�V�K�����m��C���^�Z�v�^�ɊT�Z��n�����ݒ肷�� 
            // ���M�V�K�����m��C���^�Z�v�^.set�T�Z��n���()���R�[������    
            l_mfNewOrderConfirmInterceptor.setEstimatedPrice(
                l_mfEstimatedPrice.getEstimatedPrice());
            
            // ���M�V�K�����m��C���^�Z�v�^�ɊT�Z����������ݒ肷��
            // ���M�V�K�����m��C���^�Z�v�^.set�T�Z��������()���R�[������ 
            l_mfNewOrderConfirmInterceptor.setEstimatedQty(
                l_mfEstimatedPrice.getEstimatedQty());
                
            // ���M�V�K�����m��C���^�Z�v�^�ɊT�Z���t�����i�抷��j��ݒ肷��B 
            // ���M�V�K�����m��C���^�Z�v�^.set�T�Z���t�����i�抷��j()���R�[������B
            l_mfNewOrderConfirmInterceptor.setSwitchingEstimatedQty(Double.NaN);
            
            // ���M�V�K�����m��C���^�Z�v�^�ɐŋ敪�i�抷��j��ݒ肷��B 
            // ���M�V�K�����m��C���^�Z�v�^.set�ŋ敪�i�抷��j()���R�[������B
            l_mfNewOrderConfirmInterceptor.setSwitchingSubjectTaxDivision(null);
            
            // ���M�V�K�����m��C���^�Z�v�^�Ɏ�n���@@��ݒ肷��B 
            // ���M�V�K�����m��C���^�Z�v�^.set��n���@@()���R�[������B
            l_mfNewOrderConfirmInterceptor.setDeliveryDiv(l_request.deliveryDiv);
            
            // ���M�V�K�����m��C���^�Z�v�^�ɓ��M�^�C�v��ݒ肷��B 
            // ���M�V�K�����m��C���^�Z�v�^.set���M�^�C�v()���R�[������B
            l_mfNewOrderConfirmInterceptor.setMutualFundType(
                l_web3MfProduct.getMutualFundType().intValue() + "");
                
            // ���M�V�K�����m��C���^�Z�v�^�ɓ��M���敪��ݒ肷��B 
            // ���M�V�K�����m��C���^�Z�v�^.set���M���敪()���R�[������B 
            l_mfNewOrderConfirmInterceptor.setMutualFundSellDiv(l_request.specifyDiv);
            
            // ���M�V�K�����m��C���^�Z�v�^�ɖ�����ݒ肷��B 
            // ���M�V�K�����m��C���^�Z�v�^.set����()���R�[������B
            Timestamp l_tsExecutedDate = new Timestamp(l_datExecutedDate.getTime());
            l_mfNewOrderConfirmInterceptor.setExecutionTimestamp(l_tsExecutedDate);
            
            // ���M�V�K�����m��C���^�Z�v�^�Ɍ��ϋ敪��ݒ肷��B
            // ���M�V�K�����m��C���^�Z�v�^.set���ϋ敪()���R�[������B
            l_mfNewOrderConfirmInterceptor.setSettlementType(l_request.settleDiv);
            
            // ���M�V�K�����m��C���^�Z�v�^�ɖ��萔���敪��ݒ肷��B 
            // ���M�V�K�����m��C���^�Z�v�^.set���萔���敪()���R�[������B
            l_mfNewOrderConfirmInterceptor.setNoCommissionDivision(" ");
            
            // ���M�V�K�����m��C���^�Z�v�^�ɖ����R�[�h�i�抷��j��ݒ肷��B 
            // ���M�V�K�����m��C���^�Z�v�^.set�����R�[�h�i�抷��j()���R�[������B
            l_mfNewOrderConfirmInterceptor.setSwitchingSubjectMutualProductCode(null);
            
            // ���M�V�K�����m��C���^�Z�v�^�ɐ����敪��ݒ肷��B 
            // ���M�V�K�����m��C���^�Z�v�^.set�����敪()���R�[������B
            l_mfNewOrderConfirmInterceptor.setRequestDivision(
                l_request.sellBuyDiv);            
            
            // ���M�V�K�����m��C���^�Z�v�^�ɒ����o�H�敪��ݒ肷��B 
            // ���M�V�K�����m��C���^�Z�v�^.set�����o�H�敪()���R�[������B
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            l_mfNewOrderConfirmInterceptor.setOrderChannelDivision(
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
            
			//�R���M�V�K�����m��C���^�Z�v�^�ɏo���������ʃR�[�h��ݒ肷��B 
			//[set�o���������ʃR�[�h�ɓn���p�����^] 
			//�o���������ʃR�[�h�F�@@create�o���`�[�쐬�i�j�̖߂�l 
			//�A���A���N�G�X�g�f�[�^.��n���@@����s�U�� �̏ꍇ�Anull
            l_mfNewOrderConfirmInterceptor.setPaymentOrderReqNumber(l_strPaymentOrderReqNumber);
            
            //�S���M�V�K�����m��C���^�Z�v�^�Ɉꊇ�敪��ݒ肷��B 
            //[set�ꊇ�敪�ɓn���p�����^] 
            //�@@�@@�ꊇ�敪�F�@@�擾�����g�����M�����Dis������������ 
            //�@@�@@[is�������������̃p�����^] 
            //�@@�@@������� �F OrderTypeEnum�D�����M�������� 
            l_mfNewOrderConfirmInterceptor.setNorealDiv(
                l_web3MfProduct.isUnitTypeProduct(OrderTypeEnum.MF_SELL));
            //21���M�V�K�����m��C���^�Z�v�^�ɒ����I������ݒ肷��B 
            //[set�����I�����ɓn���p�����^] 
            //�@@�@@�����I�����F�@@�擾�����g�����M�����DgetDataSourceObject().get���抷�I����()
            MutualFundProductRow l_productRow = 
                (MutualFundProductRow) l_web3MfProduct.getDataSourceObject();
            l_mfNewOrderConfirmInterceptor.setOrderEndDate(l_productRow.getSellSwtEndDate());
            
            // �������ʃ^�C�v
            QuantityTypeEnum l_qtyTypeEnum = null;
            if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
            {
                l_qtyTypeEnum = QuantityTypeEnum.QUANTITY;
            }
            else if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_request.specifyDiv))
            {
                l_qtyTypeEnum = QuantityTypeEnum.AMOUNT;
            }
            else if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_request.specifyDiv))
            {
                l_qtyTypeEnum = QuantityTypeEnum.QUANTITY;
            }
            
			//1.29�j���M�V�K�����m��C���^�Z�v�^���g�����M�����}�l�[�W���ɐݒ肷��
			l_web3MfOrderMgr.setThreadLocalPersistenceEventInterceptor(
				l_mfNewOrderConfirmInterceptor);           
              
            //1.30�j�V�K�������e�̐��� 
            //�g�����M�V�K�������e�𐶐�����B
			//[�g�����M�V�K�������e�̃R���X�g���N�^�ɓn���p�����^] 
			//�㗝���͎ҁF this.get�㗝���͎�()�̖߂�l 
			//is���t�F false 
			//�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h 
			//�������ʁF 
			//(*) ���N�G�X�g�f�[�^.�w����@@==�h�S���h and 
			//is���z�w����()�̖߂�l==true �̏ꍇ�� 
			//0���w��B  
			//(*)����ȊO�̏ꍇ�́A ���N�G�X�g�f�[�^.���ʂ��w��B 
			//�������ʃ^�C�v�F 
			//(*) ���N�G�X�g�f�[�^.�w����@@���h2�F�S���h�̏ꍇ�� 
			//QuantityTypeEnum.���ʂ��w��B 
			//(*) ���N�G�X�g�f�[�^.�w����@@���h3�F���z�h�̏ꍇ�� 
			//QuantityTypeEnum.���z���w��B 
			//(*) ���N�G�X�g�f�[�^.�w����@@���h4�F�����h�̏ꍇ�� 
			//QuantityTypeEnum.���ʂ��w��B 
			//�ŋ敪�F �擾�����ۗL���YParams.get�ŋ敪()�̖߂�l
			
			if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) && 
				    l_blnIsPriceDesignateCancelling)
			{
			    l_dblOrderQuantity = 0;
			}
			
            WEB3MutualFundNewOrderSpec l_mfNewOrderSpec = 
                new WEB3MutualFundNewOrderSpec(
                    this.getTrader(),
                    false,
                    l_request.mutualProductCode,
                    l_dblOrderQuantity,
                    l_qtyTypeEnum,
                    l_asset.getTaxType());
                     
            //1.31) �g�����M�����}�l�[�W��.submitNewOrder()���R�[������B
            OrderSubmissionResult l_orderSubmissionResult = 
            l_web3MfOrderMgr.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.MUTUAL_FUND,
                l_mfNewOrderSpec,
                Long.parseLong(l_request.orderId),
                l_request.password,
                true);
                
            //�g�����M�����}�l�[�W��.submitNewOrder()�̖߂�l����
            if (!l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                log.debug("Error In submitNewOrder()�̖߂�l");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.32�j�]�͎c�����X�V���� 
            //����]�̓T�[�r�X���擾���A�]�͍Čv�Z( )���R�[������B 
            //[�]�͍Čv�Z�ɓn������] 
            //�⏕�����F�擾�����⏕�����I�u�W�F�N�g
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
            
            //1.33�j���������̎擾
            Date l_datProcessDate = null;
            l_datProcessDate = (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3MutualFundTradingTimeManagement.TIMESTAMP_TAG);
            
            //1.34�j���M��񊮗����X�|���X�I�u�W�F�N�g�𐶐� 
            l_response = (WEB3MutualSellCompleteResponse) l_request.createResponse();
            
			//1.35) ���M��񊮗����X�|���X�I�u�W�F�N�g�ɁA�ȉ��̒l��ݒ肷��B
            l_response.lastUpdatedTimestamp = l_datProcessDate;
            l_response.orderActionId = l_request.orderId;
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate��n���@@)<BR>
     * ��n���@@�̃`�F�b�N�����{����B<BR>
     * <BR>
     * �P�j �⏕����.getMainAccount()���R�[�����āA�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j �ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B 
     *  [����] 
     *  ���XID = �P�j�Ŏ擾�����ڋq�̕��XID
     *  �v���t�@@�����X�� = "mf.payment.method.check" 
     *  �v���t�@@�����X���̘A�� = 1
     * �R�j �擾�������R�[�h.�v���t�@@�����X�̒l == �h��n���@@�`�F�b�N�s�v�h �̏ꍇ�A�ȉ��̏������s�킸�����𔲂���B
     * <BR>
     * �S�j �ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *  �mgetAsset�ɓn���p�����^�n<BR>
     *   �A�Z�b�gID�F ����.���YID<BR>
     * �|getAsset()��NotFoundException���X���[�����ꍇ�́A <BR>
     *     ��O���X���[���� <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00204<BR>
     *  <BR>
     * �T�j�ڋq�I�u�W�F�N�g.getDataSourceObject().get�ŋ敪  <BR>
     *    == �h3�F������������򒥎��h AND   <BR>
     * �ۗL���Y�I�u�W�F�N�g.getDataSourceObject().get�ŋ敪 == �h�Q�F��������h <BR>
     *     �̏ꍇ�A��O���X���[����B�i��n���@@�`�F�b�N�G���[�j<BR>
     *  <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02271<BR>
     * <BR>
     * �U�j�g�����M����.is�O��MMF()==true�̏ꍇ�́A��O���X���[����B<BR>
     *  �i�O��MMF��n���@@�`�F�b�N�G���[�j<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02732<BR>
     *  <BR>
     * @@param l_subAccount - �⏕����
     * @@param l_strId - ���YID
     * @@param l_web3MfProduct - (�g�����M����)<BR>
     * �g�����M����<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40556A8301A4
     */
    public void validatePaymentMethod(SubAccount l_subAccount, String l_strId,
            WEB3MutualFundProduct l_web3MfProduct) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePaymentMethod(SubAccount, String, WEB3MutualFundProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
		//�P�j �⏕����.getMainAccount()���R�[�����āA�ڋq�I�u�W�F�N�g���擾����B 
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();

		//�Q�j �ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B 
		//[����] 
		//�@@���XID = �P�j�Ŏ擾�����ڋq�̕��XID
		//�@@�v���t�@@�����X�� = "mf.payment.method.check" 
		//�@@�v���t�@@�����X���̘A�� = 1 
        BranchPreferencesRow l_branchPreferencesRow = null;
        try
        {
            l_branchPreferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
            	l_mainAccountRow.getBranchId(), 
                WEB3BranchPreferencesNameDef.MF_PAYMENT_METHOD_CHECK,
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
		//�R�j �擾�������R�[�h.�v���t�@@�����X�̒l == �h��n���@@�`�F�b�N�s�v�h �̏ꍇ�A�ȉ��̏������s�킸�����𔲂���B
        if(l_branchPreferencesRow !=null && l_branchPreferencesRow.getValue().equals(
		    WEB3MfPaymentMethodCheckDef.NO_CHECK))
        {
        	log.debug("validate��n���@@ ���{�s�v");
        	log.exiting(STR_METHOD_NAME);
        	return;
        }

        //�S�j�ۗL���Y�I�u�W�F�N�g���擾����B  
		//�mgetAsset�ɓn���p�����^�n  
		//�A�Z�b�gID�F ����.���YID  
		//�|getAsset()��NotFoundException���X���[�����ꍇ�́A��O���X���[����B
        //�g�����M�|�W�V�����}�l�[�W�����擾����
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundPositionManager l_web3MfPositionMgr =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getPositionManager();
        
        //1.9)getAsset(long)
        //�|���M�g���|�W�V�����}�l�[�W��.getAsset( )���R�[������B 
        //�@@[����] 
        //�@@���N�G�X�g.ID�F���YID
        AssetRow l_assetRow = null;
        try
        {
           Asset l_asset =l_web3MfPositionMgr.getAsset(Long.parseLong(l_strId));
           l_assetRow = (AssetRow) l_asset.getDataSourceObject();
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("�ۗL���Y�I�u�W�F�N�g�f�[�^�Ȃ��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    
		//�T�j�ڋq�I�u�W�F�N�g.getDataSourceObject().get�ŋ敪 == �h3�F������������򒥎��h�@@
        //AND �ۗL���Y�I�u�W�F�N�g.getDataSourceObject().get�ŋ敪 == �h�Q�F��������h
		//�̏ꍇ�A��O���X���[����B 
        
        if (TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_mainAccountRow.getTaxType()) &&
            TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType()))
        {
            log.error("��n���@@�`�F�b�N�G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02271 ,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�U�j�g�����M����.is�O��MMF()==true�̏ꍇ�́A��O���X���[����B
        //�i�O��MMF��n���@@�`�F�b�N�G���[�j
        if (l_web3MfProduct.isFrgnMmf())
        {
            log.error("�O��MMF��n���@@�`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02732 ,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�o�����z�͈�)<BR>
     * �o�����z�͈̓`�F�b�N���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���M)validate�o�����z�͈́v�Q�ƁB<BR>
     * <BR>
     * @@param l_dblEstimatedPrice - �T�Z��n���
     * @@param l_datDeliveryDate - ��n��
     * @@throws WEB3BaseException
     * @@roseuid 40556A7C0127
     */
    public void validatePaymentMoney(double l_dblEstimatedPrice, Date l_datDeliveryDate) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePaymentMoney(double, Date)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getService(Class)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //1.2 getTradingModule(ProductTypeEnum)
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
        //1.2.1 getOrderManager( )    
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_tradingModule.getOrderManager();
        
        //���o�������R���ʃ`�F�b�N�̃I�u�W�F�N�g���擾����B
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
       
        //1.3 get�⏕����(SubAccountTypeEnum)
        //[����] 
        //�⏕�����^�C�v�F �h�a��������h 
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
       
        //1.4 �o�����z�`�F�b�N���s�Ȃ��B
        //validate�o�����z(subAccount, double, Date)
        //[validate�o�����z�i�j�ɓn���p�����^] 
    	//�⏕�����F�@@�擾�����⏕���� 
    	//���z�F�@@����.�T�Z��n��� 
    	//��n���F�@@����.��n��
        l_reusableValidations.validatePaymentAmount(
            l_subAccount, 
            l_dblEstimatedPrice, 
            l_datDeliveryDate);
       
       log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create�o���`�[�쐬)<BR>
     * ���o���`�[�̍쐬���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���M)create�o���`�[�쐬�v�Q�ƁB<BR>
     * <BR>
     * @@param l_trader - �㗝���͎�
     * @@param l_dblEstimatedPrice - �T�Z��n���
     * @@param l_tsDeliveryDate - ��n��
     * @@param l_strPassword - �Ïؔԍ�
     * @@Return String
     * @@throws WEB3BaseException
     * @@roseuid 40556A7C0127
     */
    public String createPaymentTicket(
        Trader l_trader, 
        double l_dblEstimatedPrice, 
        Timestamp l_tsDeliveryDate,
        String l_strPassword) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createPaymentTicket(Trader, double, Timestamp, String)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getService(Class)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //1.2 getTradingModule(ProductTypeEnum)
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
        //1.2.1 getOrderManager()
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_tradingModule.getOrderManager();
            
        //1.3 get�⏕����(SubAccountTypeEnum)
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.4 �o�����z�`�F�b�N���s�Ȃ��B
        //validate�o�����z(subAccount, double, Date)
    	//�o��������z�͈̔͂𒴂��Ă��Ȃ����`�F�b�N����B 
    	//[validate�o�����z()�ɓn���p�����^] 
    	//�⏕�����F�@@�擾�����⏕�����I�u�W�F�N�g 
    	//���z�F�@@����.�T�Z��n����̖߂�l 
    	//��n���F�@@����.��n��
            
        //���o�������R���ʃ`�F�b�N�̃I�u�W�F�N�g���擾����B
        WEB3AioProductTypeOrderManagerReusableValidations l_reusableValidations = 
            (WEB3AioProductTypeOrderManagerReusableValidations)
                AioProductTypeOrderManagerReusableValidations.getInstance();
            
        l_reusableValidations.validatePaymentAmount(
            l_subAccount, 
            l_dblEstimatedPrice, 
            l_tsDeliveryDate);
        
        //1.5 get���iID(Institution)
        Institution l_institution = l_subAccount.getInstitution();
        
        //1.6 createNewOrderId( )
       long l_lngOrderId = l_aioOrderManager.createNewOrderId();
       
       //���iID
       long l_lngProductId = l_aioOrderManager.getProductId(l_institution);
       
        //1.7 ���o���������e
		//���o���������e�𐶐�����B 
		//[���o���������e�R���X�g���N�^�ɓn���p�����^]  
		//�㗝���͎ҁF�@@����.�㗝���͎҃I�u�W�F�N�g  
		//������ʁF�@@1001(�o������) 
		//�U�փ^�C�v�F�@@2�i�o���j  
		//���iID�F�@@get���iID()�̖߂�l  
		//���z�F�@@����.�T�Z������� 
		//�L�q�F�@@null  
		//�U�֗\����F�@@����.��n�� 
		//���ϋ@@��ID�F�@@null  
		//����ID�F�@@createNewOrderId( )�̖߂�l 
       WEB3AioNewOrderSpec l_aioNewOrderSpec = 
           new WEB3AioNewOrderSpec(
               l_trader,
               OrderTypeEnum.CASH_OUT,
               AssetTransferTypeEnum.CASH_OUT,
               l_lngProductId,
               l_dblEstimatedPrice,
               null,
               l_tsDeliveryDate,
               null,
               new Long(l_lngOrderId));

       //1.8 ���o�������X�V�C���^�Z�v�^(���o���������e)
       WEB3AioCashTransOrderUpdateInterceptor l_interceptor = 
           new WEB3AioCashTransOrderUpdateInterceptor(l_aioNewOrderSpec);
       
       //1.9 set��n��(Date)
       l_interceptor.setDeliveryDate(l_tsDeliveryDate);
       
       //1.10 �c�Ɠ��v�Z(Timestamp)
       WEB3GentradeBizDate l_gentradeBizDate =
           new WEB3GentradeBizDate(l_tsDeliveryDate);
       
       //1.11 roll(int)
		Timestamp l_tsBizDate = l_gentradeBizDate.roll(-1);

       //1.12 set������(Date)
		l_interceptor.setBizDate(l_tsBizDate);
		
       //1.13 get�V�K���ʃR�[�h(String, String, ProductTypeEnum)
		//[get�V�K���ʃR�[�h()�ɓn���p�����^]  
		// �،���ЃR�[�h�F �⏕��������擾�����،���ЃR�[�h  
		// ���X�R�[�h�F �⏕��������擾�������X�R�[�h  
		// �����^�C�v�F 5�i�����j �iProductTypeEnum) 
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        //(2)
        String l_strNewNumber = 
            l_hostReqOrderNumberManageService.getNewNumber(
                l_institution.getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.CASH);
        log.debug("get�V�K���ʃR�[�h() = " + l_strNewNumber);
        

        //1.14 set���ʃR�[�h(String)
        l_interceptor.setOrderRequestNumber(l_strNewNumber);
        
        //1.15 set�o���\���敪(String)
        l_interceptor.setPaymentApplicationDiv(WEB3AioPaymentApplicationDivDef.MF);
        
        //1.16 setThreadLocalPersistenceEventInterceptor(AioOrderManagerPersistenceEventInterceptor)
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //1.17 submitNewOrder
		//[submitNewOrder�ɓn���p�����^] 
		//�⏕�����F�@@�擾�����⏕�����I�u�W�F�N�g 
		//�����^�C�v�F ProductTypeEnum.5�F���� 
		//�V�K�������e�F �����������o���������e 
		//����ID�F �擾��������ID 
		//����p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� 
		//is�����R���ȗ��F true
        OrderSubmissionResult l_orderSubmissionResult =
            l_aioOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_aioNewOrderSpec,
                l_lngOrderId,
                l_strPassword,
                true);
        
        //�g�����M�����}�l�[�W��.submitNewOrder()�̖߂�l����
        if (!l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
        {
            log.debug("�V�K�������s");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�V�K�������s");
        }
        
        //1.18 ���ʃR�[�h
        //get�V�K���ʃR�[�h()�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strNewNumber;
    }
}
@
