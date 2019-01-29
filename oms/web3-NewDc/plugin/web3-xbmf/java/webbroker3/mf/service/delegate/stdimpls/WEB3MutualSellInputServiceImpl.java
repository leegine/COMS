head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������̓T�[�r�X�����N���X(WEB3MutualSellInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 ��O�� (���u) �V�K�쐬
Revesion History : 2004/08/23 ������ (���u) ���r���[ 
Revesion History : 2004/12/11 ������ (���u) �c�Ή�
Revesion History : 2006/05/15 �юu��(���u) �d�l�ύX�i���f��) :411,415
Revesion History : 2006/10/16 ���� �d�l�ύX�E���f��500�A509
Revesion History : 2006/10/25  �����F (���u) ���f�� 510
Revesion History : 2007/02/05 ������ (���u) ���f�� No.527
Revesion History : 2007/03/26 ��іQ (���u) ���f�� No.551
Revesion History : 2007/04/06 ������ (���u) ����005, ���f��558
Revesion History : 2008/05/07 ���{ (SRA) �b��Ή�
Revesion History : 2008/05/12 ���g (���u) �d�l�ύX No602
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendar;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundAsset;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellSettlementDivDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3DeliveryMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFEstimatedPriceCurrencyCodeDef;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualSellInputRequest;
import webbroker3.mf.message.WEB3MutualSellInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualSellInputService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M�������̓T�[�r�X�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualSellInputServiceImpl extends WEB3MutualClientRequestService implements WEB3MutualSellInputService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellInputServiceImpl.class);
        
    /**
     * �����M�������̓T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���M)�����́v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)�����́v: <BR>
     *        12((�戵�\�����`�F�b�N�A<BR>
     *        �擾�����g�����M����.is�V�X�e���戵()���R�[������B<BR>
     *         is�V�X�e���戵()�� false ��Ԃ��ꍇ�͗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00362<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)�����́v: <BR>
     *        14((����\�����`�F�b�N�A<BR>
     *        is���抷�\()�� false ��Ԃ��ꍇ�͗�O���X���[����B<BR>
     *         �i����s�����G���[<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00363<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)�����́v: <BR>
     *        ���\�c������==0 �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00390<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B16F85034B
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
        WEB3MutualSellInputRequest l_mfSellInputRequset = null;
        if (l_request instanceof WEB3MutualSellInputRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u���M���m�F���N�G�X�g�v�̏ꍇ
            l_mfSellInputRequset = (WEB3MutualSellInputRequest) l_request;
        }
        else
        {
            log.debug(STR_METHOD_NAME + "Error[���͒l���s���ł�]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
        //�P�j���͓��e�`�F�b�N 
        l_mfSellInputRequset.validate();

        //�Q�j�⏕�����擾
        SubAccount l_subAccount = this.getSubAccount();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //�g�����M�|�W�V�����}�l�[�W�����擾����
        WEB3MutualFundPositionManager l_web3MfPositionMgr =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getPositionManager();
        
        //�R�j�@@�ۗL���Y���擾����
        MutualFundAsset l_mfAsset = null;
        try
        {
            l_mfAsset =
                (MutualFundAsset)l_web3MfPositionMgr.getAsset(
                    Long.parseLong(l_mfSellInputRequset.id));
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__ ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�g�����M�����}�l�[�W�����擾����
        WEB3MutualFundProductManager l_web3MfProductMgr =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        WEB3MutualFundProduct l_web3MfProduct = null;
        WEB3MutualSellInputResponse l_response = null;        

        try
        {
            // �S�j�@@�g�����M�����擾
            //�g�����M�����}�l�[�W��.getProduct()���R�[�����A�����I�u�W�F�N�g���擾����
            l_web3MfProduct = (WEB3MutualFundProduct)
                l_web3MfProductMgr.getProduct(l_mfAsset.getProduct().getProductId());
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
                
        //�擾�ł��Ȃ��ꍇ�͗�O���X���[����
        if (l_web3MfProduct == null)
        {
            log.debug("�g�����M�����擾�ł��Ȃ��ꍇ�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�g�����M�����擾�ł��Ȃ��ꍇ�G���[");
        }                       

        //FinApp�N���X��getCommonOrderValidator()���R�[����
        //�����`�F�b�N�I�u�W�F�N�g���擾����      
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //�T�j�@@��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N  
        //���M������ԊǗ�.validete������t�\()���R�[������
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        //�U�j�@@������Ԃ̍Đݒ�
        //���M������ԊǗ�.reset�����R�[�h()���R�[��
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_web3MfProduct.getProductCode());
        //��t�����A���t���[�����Z�b�g����
        WEB3MutualFundTradingTimeManagement.setTimestamp();
        
		//�V�j�@@�ڋq�ʎ����~�����`�F�b�N   
        //[get���M�������̈���] 
        // ������� �F OrderTypeEnum.�����M�������� 
        // �ꊇ�敪 �F �擾�����g�����M�����Dis������������()�̖߂�l 
        // [is�������������̈���] 
        // ������� �F OrderTypeEnum�D�����M��������
        //�����`�F�b�N.validate����\�ڋq()���R�[������B
		Timestamp l_datBizDateValidate = 
			new Timestamp(
                WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(
                    OrderTypeEnum.MF_SELL, 
                    l_web3MfProduct.isUnitTypeProduct(OrderTypeEnum.MF_SELL)).getTime());

        //�������������̏ꍇ�̔������擾 
        long l_lngUnitProductId = l_web3MfProduct.getProductId();
        if (l_lngUnitProductId == 3303910181800L || l_lngUnitProductId == 3303911181800L)
        {
            MutualFundProductRow l_mfProductRow = 
                (MutualFundProductRow) l_web3MfProduct.getDataSourceObject();
        	l_datBizDateValidate = l_mfProductRow.getSellSwtEndDate();
        }
		
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
        
        //�W�j�@@�戵�\�����`�F�b�N
        //����.�g�����M����.is�V�X�e���戵()���R�[������
        if (!l_web3MfProduct.isSystemHandling())
        {
            log.debug(" __�戵�s�����G���[__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�戵�s�����G���[");
        }
        
        //�X�j����\�����`�F�b�N
        //�擾�����g�����M����.is���抷�\()���R�[������
        //�X�|�P�jis���抷�\()�� false ��Ԃ��ꍇ�͗�O���X���[����B
        Date l_datArgIsSellSwitchingPossible = null;
        if (l_lngUnitProductId == 3303910181800L || l_lngUnitProductId == 3303911181800L)
        {
        	l_datArgIsSellSwitchingPossible = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        else
        {
        	l_datArgIsSellSwitchingPossible = l_datBizDateValidate;
        }
        if (!l_web3MfProduct.isSellSwitchingPossible(l_datArgIsSellSwitchingPossible))
		{
			log.debug(" __����s�����G���[__");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00363,
				this.getClass().getName() + "." + STR_METHOD_NAME,
                "����s�����G���[");
		}
		
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        
		//�擾�����g�����M����.is�������M()�̖߂�l == true 
        // AND ���M�����̓��N�G�X�g.�������@@��"����"�̏ꍇ
		if (l_web3MfProduct.isDomesticFund() && WEB3ClaimDivDef.BUY.equals(l_mfSellInputRequset.sellBuyDiv))
		{			
			//is���搿���\()�� false ��Ԃ��ꍇ�͗�O���X���[����
			if (!l_validationsCheck.isBuyingRequestPossible(
                l_datBizDateValidate,
			    l_subAccount,
			    l_web3MfProduct))
			{
				log.debug(" __����s�����G���[__");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00363,
					this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����s�����G���[");
			}
		}
		
        try
        {
            //�P�O�j�ً}��~�`�F�b�N 
            //���M�����R���ʃ`�F�b�N.validate�ً}��~()���R�[������
            l_validationsCheck.validateEmergencyStop(
                    l_web3MfProduct, WEB3ProcessDivDef.SELL);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("�ً}��~�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�P�P�j�����~���ԃ`�F�b�N
        //���M������ԊǗ�.validete������t�\()���R�[������
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();
        //validate�O��MMF��d����
        l_validationsCheck.validateFrgnMmfDoubleOrder(
                l_subAccount,
                l_web3MfProduct,
                l_datBizDateValidate);
        //�P�Q�j���\�c���������擾����  
        double l_dblSellPossiblePositionQty = 0;  
        l_dblSellPossiblePositionQty = 
            l_web3MfPositionMgr.calcSellPossiblePositionQty(
                l_subAccount, l_web3MfProduct, l_mfAsset.getAssetId() + "");
        //U00824�Ή�
        //start
        if(l_dblSellPossiblePositionQty == 0)
        {
			log.error("���\�c������������܂���B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00390,
				this.getClass().getName() + "." + STR_METHOD_NAME
				);	
        }
        //end
                
        //�P�R�j�����擾 
        Date l_datExecutedDate = null;
        //�g�����M�����}�l�[�W��.get����()���R�[�����A�������擾����
        l_datExecutedDate = l_web3MfProductMgr.getExecutedDate(
            l_subAccount.getInstitution(),
            l_web3MfProduct.getProductCode(),
            l_datBizDateValidate);

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
        	l_datExecutedDate = l_tradingCalendar.roll(l_datBizDateValidate, 2);
        }
        
        //�P�S�j��n���擾
        Date l_datDeliveryDate = null;
        //�g�����M�����}�l�[�W��.get��n��()���R�[�����A��n�����擾����
        l_datDeliveryDate = l_web3MfProductMgr.getDeliveryDate(
            l_subAccount.getInstitution(),
            l_web3MfProduct.getProductCode(),
            false,
            l_datExecutedDate);
            
        //�P�T�j���M�����̓��X�|���X�I�u�W�F�N�g�𐶐����A���^�[������B 
        //���M�����̓��X�|���X�I�u�W�F�N�g�ɂ́A�ȉ��̒l��ݒ肷��B
        l_response = (WEB3MutualSellInputResponse) l_request.createResponse();
        
        //[���M�����̓��X�|���X�ɐݒ肷��l] 
        //���\�����F
        l_response.sellAbleQty = 
            WEB3StringTypeUtility.formatNumber(l_dblSellPossiblePositionQty);
        //�]���z�F
        l_response.marketValue = 
            WEB3StringTypeUtility.formatNumber(
                l_web3MfPositionMgr.calcMarketValue(l_subAccount,l_web3MfProduct, l_mfAsset.getAssetId() + ""));
        //�����R�[�h�F
        l_response.mutualProductCode = l_web3MfProduct.getProductCode();
        log.debug("�����R�[�h = " + l_web3MfProduct.getProductCode());
            
        //�������F
        l_response.mutualProductName = l_web3MfProduct.getMutualProductName();
        
        //�����敪�F
        String l_strTaxType = null;
        //�擾�����ۗL�c���I�u�W�F�N�g.getTaxType()�̒l��TaxTypeEnum.��ʂ̏ꍇ
        if (TaxTypeEnum.NORMAL.equals(l_mfAsset.getTaxType()))
        {
            l_strTaxType = WEB3MFAccountDivDef.NORMAL;
        }            
        //�擾�����ۗL�c���I�u�W�F�N�g.getTaxType()�̒l��TaxTypeEnum.����܂��� 
        //     TaxTypeEnum.������������򒥎��̏ꍇ
        else if (TaxTypeEnum.SPECIAL.equals(l_mfAsset.getTaxType()) ||
            TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_mfAsset.getTaxType()))
        {
            l_strTaxType = WEB3MFAccountDivDef.SPECIAL;
        }
        //�擾�����ۗL�c���I�u�W�F�N�g.getTaxType()�̒l��TaxTypeEnum.���̑��̏ꍇ
        else if (TaxTypeEnum.UNDEFINED.equals(l_mfAsset.getTaxType()))
        {
            l_strTaxType = WEB3MFAccountDivDef.OTHER;
        }            
        log.debug("�����敪 = " + l_strTaxType);
        l_response.taxType = l_strTaxType;
        
        //��񉿊z�ʉ݃R�[�h�F
        l_response.sellValueCurrencyCode = l_web3MfProduct.getCurrencyCode();
        
        //��񉿊z�F
        if (!((MutualFundProductRow)l_web3MfProduct.getDataSourceObject()).getSellConstantValueIsNull())
        {
            l_response.sellValue = 
                WEB3StringTypeUtility.formatNumber(l_web3MfProduct.getSellValue());
        }
        
        //����z�K�p���F
        l_response.constantValueAppDate = 
            l_web3MfProduct.getConstantValueAppDate();
        
        //�������@@�ꗗ�F
        //�|�擾�����g�����M����.is�O��MMF�i�j�̖߂�l==false �̏ꍇ�A 
        if (!l_web3MfProduct.isFrgnMmf())
        {
            //(*) ���M�����̓��N�G�X�g.�������@@��null�łȂ��ꍇ
            log.debug("�������@@ = " + l_mfSellInputRequset.sellBuyDiv);
            if (l_mfSellInputRequset.sellBuyDiv != null)
            {
                String[] l_strSellBuyDivList = {l_mfSellInputRequset.sellBuyDiv};
                l_response.sellBuyDivList = l_strSellBuyDivList;
            }
            //(*) ���M�����̓��N�G�X�g.�������@@��null�̏ꍇ
            else
            {
                //�擾�����g�����M����.is�O�����M�i�j�̖߂�l==true
                // OR �擾����.�g�����M����.isFWF�i�j�̖߂�l==true�̏ꍇ�A
                //�h�P�F����h��ݒ�B
                boolean l_blnIsForeignFund = l_web3MfProduct.isForeignFund();
                boolean l_blnIsFWF = l_web3MfProduct.isFWF();
                if (l_blnIsForeignFund || l_blnIsFWF)
                {
                    String[] l_strSpecifyDivList = {WEB3ClaimDivDef.BUY};
                    l_response.sellBuyDivList = l_strSpecifyDivList; 
                }
                else
                {
                    //�擾�����g�����M����.is���搿���\()���R�[������
                    //is���搿���\()�� true ��Ԃ��ꍇ�́h0�F���h�Ɓh1�F����h
                    boolean l_blnBuyPossible = false; 
                    l_blnBuyPossible = 
                        l_validationsCheck.isBuyingRequestPossible(
                            l_datBizDateValidate,
                            l_subAccount,
                            l_web3MfProduct);
                
                    log.debug("is���搿���\()" + l_blnBuyPossible);
                    if (l_blnBuyPossible)
                    {
                        String[] l_strSellBuyDiv = 
                            {
                                WEB3ClaimDivDef.SELL,
                                WEB3ClaimDivDef.BUY
                            };
                        l_response.sellBuyDivList = l_strSellBuyDiv;                
                    }
                    //is���搿���\()�� false ��Ԃ��ꍇ�́h0�F���h��ݒ肷��
                    else
                    {
                        String[] l_strSpecifyDivList = {WEB3ClaimDivDef.SELL};
                        l_response.sellBuyDivList = l_strSpecifyDivList; 
                    }
                }
            }
        }
        else
        {
            l_response.sellBuyDivList = null;
        }
        //�w����@@�ꗗ�F
        //(*) �擾�����g�����M����.get�w����@@�i���j()�̖߂�l���h0�F�I���w��h 
        if (WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(l_web3MfProduct.getSellSelectable()))
        {
            String[] l_strSpecifyDivList = 
                {
                    WEB3SellDivDef.ALL_DESIGNATE,
                    WEB3SellDivDef.MONEY_DESIGNATE,
                    WEB3SellDivDef.COUNT_DESIGNATE
                };
            l_response.specifyDivList = l_strSpecifyDivList;           
        }
        else if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_web3MfProduct.getSellSelectable()))
        {
            String[] l_strSpecifyDivList = 
                {
                    WEB3SellDivDef.ALL_DESIGNATE,
                    WEB3SellDivDef.MONEY_DESIGNATE,                    
                };
            l_response.specifyDivList = l_strSpecifyDivList;           
        }
        else if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_web3MfProduct.getSellSelectable()))
        {
            String[] l_strSpecifyDivList = 
                {
                    WEB3SellDivDef.ALL_DESIGNATE,
                    WEB3SellDivDef.COUNT_DESIGNATE,
                };
            l_response.specifyDivList = l_strSpecifyDivList;
        }
        
        //��񎞒P�ʌ����F
        //(*) �擾�����g�����M����.get�P�ʌ����i���j()�̖߂�l��ݒ肷��
        l_response.sellUnitQty = l_web3MfProduct.getSellUnitQty() + "";
        
        //��񎞍Œ�����F
        //(*) �擾�����g�����M����.get�Œ�����i���j()�̖߂�l��ݒ肷��
        l_response.sellMinQty = l_web3MfProduct.getSellMinQty() + "";
        
        //��񎞒P�ʋ��z�F
        //(*) �擾�����g�����M����.get�P�ʋ��z�i���j()�̖߂�l��ݒ肷��
        l_response.sellUnitAmt = l_web3MfProduct.getSellUnitAmt() + "";
        
        //��񎞍Œ���z�F
        //(*) �擾�����g�����M����.get�Œ���z�i���j()�̖߂�l��ݒ肷��
        l_response.sellMinAmt = l_web3MfProduct.getSellMinAmt() + "";
        
        //���ϕ��@@�ꗗ�F
        //(*) �擾�����g�����M����.get���ρi���j()�̖߂�l���h0�F�I���w��h�̏ꍇ
        if (WEB3BuySellSettlementDivDef.SELECT_DESIGNATE.equals(
            l_web3MfProduct.getSellSettlement()))
        {
            String[] l_strSettleDivList = 
                {
                    WEB3SettlementDivDef.JAPANESE_CURRENCY,
                    WEB3SettlementDivDef.FOREIGN_CURRENCY,
                };
            l_response.settleDivList = l_strSettleDivList;           
        }
        else if (WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY.equals(
            l_web3MfProduct.getSellSettlement()))
        {
            String[] l_strSettleDivList = {WEB3SettlementDivDef.JAPANESE_CURRENCY};
            l_response.settleDivList = l_strSettleDivList; 
        }
        else if (WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY.equals(
            l_web3MfProduct.getSellSettlement()))
        {
            String[] l_strSettleDivList = {WEB3SettlementDivDef.FOREIGN_CURRENCY};
            l_response.settleDivList = l_strSettleDivList;
        }
        
        //��n���@@�ꗗ�F
        //(*) �擾�����g�����M����.get��n���@@()�̖߂�l���h0�F�I���w��h�̏ꍇ
        if (WEB3DeliveryMethodDef.SELECT_DESIGNATE.equals(
            l_web3MfProduct.getDeliveryDiv()))
        {
            String[] l_strDeliveryDivList = 
                {
                    WEB3DeliveryMethodDef.BANK_TRANSFER,
                    WEB3DeliveryMethodDef.SECURITIES_ACCOUNT_INPUT_SELL,
                };
            l_response.deliveryDivList = l_strDeliveryDivList;
        }
        else if (WEB3DeliveryMethodDef.BANK_TRANSFER.equals(
            l_web3MfProduct.getDeliveryDiv()))
        {
            String[] l_strDeliveryDivList = {WEB3DeliveryMethodDef.BANK_TRANSFER};
            l_response.deliveryDivList = l_strDeliveryDivList;
        }
        else if (WEB3DeliveryMethodDef.SECURITIES_ACCOUNT_INPUT_SELL.equals(
            l_web3MfProduct.getDeliveryDiv()))
        {
            String[] l_strDeliveryDivList = 
                {WEB3DeliveryMethodDef.SECURITIES_ACCOUNT_INPUT_SELL};
            l_response.deliveryDivList = l_strDeliveryDivList;            
        }
        //�������F �擾����������
        l_response.orderBizDate = l_datBizDateValidate;            
        //�����F �擾�������� 
        l_response.executionTimestamp = l_datExecutedDate;            
        //��n���F �擾������n�� 
        l_response.deliveryDate = l_datDeliveryDate;

        //�E�~�]����z 
        //�@@�@@1�j���M����.get�ʉ݃R�[�h( )��T0�@@�܂���  
        //�@@�@@�@@�@@�g�����M����.is�O��MMF = true�@@�̏ꍇ
        //�@@�@@�@@�@@null��ݒ肷��B
        //�@@�@@2�j���M����.get�ʉ݃R�[�h( )��T0�łȂ��ꍇ 
        //�@@�@@�@@�@@�g�����M����.get�~�]����z(WEB3MFProcessDivDef.���)���Z�b�g����B 
        //�E�Q�l���[�g
        //�@@�@@1�j���M����.get�ʉ݃R�[�h( )��T0�̏ꍇ
        //�@@�@@�@@ null���Z�b�g����B
        //�@@�@@2�j���M����.get�ʉ݃R�[�h( )��T0�łȂ��ꍇ
        //       2-1) ���M����.is�O��MMF �� true�̏ꍇ
        //              �g�����M����.get�O��MMF�בփ��[�g()�̖߂�l�O��MMF�בփ��[�gParams��TTB
        //              ���Z�b�g����B�i������R�ʂŎl�̌ܓ��j
        //       2-2) ���M����.is�O��MMF �� false�̏ꍇ
        //              �g�����M����.get�בփ��[�g()�̖߂�l�בփ��[�gParams��TTB / ���בփ��[�g�v�Z�P��
        //              ���Z�b�g����B�i������R�ʂŎl�̌ܓ��j
        //�E�Q�l���[�g�m���
        //�@@�@@1�j���M����.get�ʉ݃R�[�h( )��T0�̏ꍇ 
        //�@@�@@�@@ null���Z�b�g����B
        //�@@�@@2�j���M����.get�ʉ݃R�[�h( )��T0�łȂ��ꍇ 
        //        2-1) ���M����.is�O��MMF �� true�̏ꍇ
        //             �g�����M����.get�O��MMF�בփ��[�g()�̖߂�l
        //             �O��MMF�בփ��[�gParams�̈בփ��[�g�m������Z�b�g����B
        //        2-2) ���M����.is�O��MMF �� false�̏ꍇ
        //             �g�����M����.get�בփ��[�g()�̖߂�l
        //             �בփ��[�gParams�̈בփ��[�g�m������Z�b�g����B
        if (l_web3MfProduct.isFrgnMmf())
        {
            l_response.yenConstantValue = null;
        }
        if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_web3MfProduct.getCurrencyCode()))
        {
            l_response.yenConstantValue = null;
            l_response.referenceRate = null;
            l_response.referenceRateFixedDay = null;
        }
        else 
        {
            if (l_web3MfProduct.isFrgnMmf())
            {
                BigDecimal l_bdTtBuyingRate =
                    new BigDecimal(l_web3MfProduct.getFrgnMmfExchangeRate().getTtBuyingRate());
                l_response.referenceRate =
                    WEB3StringTypeUtility.formatNumber(
                        l_bdTtBuyingRate.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                l_response.referenceRateFixedDay = 
                    l_web3MfProduct.getFrgnMmfExchangeRate().getExecTimestamp();
            }
            else
            {
                l_response.yenConstantValue = 
                    l_web3MfProduct.getYenConstantValue(WEB3MFProcessDivDef.SELL);
                BigDecimal l_bdTtBuyingRate = 
                    new BigDecimal(l_web3MfProduct.getExchangeRate().getTtBuyingRate());
                BigDecimal l_bdExchangeCalcUnit = 
                    new BigDecimal(l_web3MfProduct.getExchangeRate().getExchangeCalcUnit());      
                l_response.referenceRate = 
                    WEB3StringTypeUtility.formatNumber(
                        l_bdTtBuyingRate.divide(l_bdExchangeCalcUnit, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
                l_response.referenceRateFixedDay = 
                    l_web3MfProduct.getExchangeRate().getExecTimestamp();
            }
        }

        MutualFundProductRow l_mfProductRow = 
            (MutualFundProductRow)l_web3MfProduct.getDataSourceObject();
        //��񎞊O�ݒP�ʋ��z�F
        // (*) �擾�����g�����M����.get�O�ݒP�ʋ��z�i���j()�̖߂�l��ݒ肷��B
        if (!l_mfProductRow.getFrgnSellUnitAmtIsNull())
        {
            l_response.sellFrgnUnitAmt =
                l_web3MfProduct.getFrgnSellUnitAmt() + "";
        }

        //��񎞊O�ݍŒ���z�F
        // (*) �擾�����g�����M����.get�O�ݍŒ���z�i���j()�̖߂�l��ݒ肷��B
        if (!l_mfProductRow.getFrgnSellMinAmtIsNull())
        {
            l_response.sellFrgnMinAmt =
                l_web3MfProduct.getFrgnSellMinAmt() + "";
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
