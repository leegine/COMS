head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���抷���̓T�[�r�X�����N���X(WEB3MutualSwitchingInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
Revesion History : 2004/08/24 ��O�� (���u) ���r���[  
Revesion History : 2004/12/11 ������ (���u) �c�Ή�  
Revesion History : 2005/10/18 ���� (���u) �t�B�f���e�B�Ή�
Revesion History : 2007/04/06 ������ (���u) ����005
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.message.WEB3MutualSwitchingInputRequest;
import webbroker3.mf.message.WEB3MutualSwitchingInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualSwitchingInputService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundAsset;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

/**
 * (���M�抷���̓T�[�r�XImpl)<BR>
 * �����M���抷���̓T�[�r�X�����N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualSwitchingInputServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualSwitchingInputService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingInputServiceImpl.class);

    /**
     * �����M���抷���̓T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���M)�抷���́v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)�抷���́v: <BR>
     *         13((�戵�\�����`�F�b�N�A<BR>
     *         is�V�X�e���戵()�� false ��Ԃ��ꍇ�͗�O���X���[����B<BR>
     *         ��O���X���[����B<BR>
     * �@@�@@   �i�戵�s�����G���[�j<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_00362<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)�抷���́v: <BR>
     *         14((����\�����`�F�b�N�A<BR>
     *         is���抷�\()�� false ��Ԃ��ꍇ�͗�O���X���[����B<BR>
     *         ��O���X���[����B<BR>
     * �@@�@@   �i����s�����G���[�j<BR>
     *              class: WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_00363<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)�抷���́v: <BR>
     *        �P�Q�j�抷�\�c���������擾����
     *         ���\�c������==0 �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00390<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B17441037A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // ���M�抷���̓��N�G�X�g�̎擾
        WEB3MutualSwitchingInputRequest l_swtInputRequest = null;
        if (l_request instanceof WEB3MutualSwitchingInputRequest)
        {
            l_swtInputRequest = (WEB3MutualSwitchingInputRequest) l_request;
        }
        else
        {
            log.debug(STR_METHOD_NAME + " __Error[���͒l���s���ł�]__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1�@@���͓��e�`�F�b�N
        // �@@���M�抷���̓��N�G�X�g.validate()���R�[������
        l_swtInputRequest.validate();

        // 1.2�@@�⏕�����擾
        SubAccount l_subAccount = this.getSubAccount();
        //�g�����M�|�W�V�����}�l�[�W�����擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundPositionManager l_web3MfPositionMgr =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getPositionManager();
        
        //1.3�@@�ۗL���Y���擾����
        MutualFundAsset l_mfAsset = null;
        try
        {
            l_mfAsset =
                (MutualFundAsset)l_web3MfPositionMgr.getAsset(
                    Long.parseLong(l_swtInputRequest.id));
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__ ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00393,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.4�@@�g�����M�����擾
        // �@@�|�g�����M�����}�l�[�W�����擾����
        WEB3MutualFundProductManager l_productManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualFundProduct l_mfProduct = null;
        try
        {
            //�|�g�����M�����}�l�[�W��.getProduct()���R�[�����A�����I�u�W�F�N�g���擾����            
            Product l_product = 
                l_productManager.getProduct(
                    l_mfAsset.getProduct().getProductId());
            
            //1.5 to����(Row)
            MutualFundProductRow l_mfProductrow = (MutualFundProductRow) l_product.getDataSourceObject();
            l_mfProduct = (WEB3MutualFundProduct) l_productManager.toProduct(l_mfProductrow);
            log.debug("�g�����M����.getProductId = " + l_mfProduct.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when l_productManager.getProduct("
                    + "productId = "
                    + l_mfAsset.getProduct().getProductId());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // 1.6 getProduct(arg0 : long)
        //�抷��̖����I�u�W�F�N�g���擾����B 
        //[����] 
        //arg0�F ���N�G�X�g.�抷�����ID 

        WEB3MutualFundProduct l_mfSwtProduct = null;
        try
        {
            //�|�g�����M�����}�l�[�W��.getProduct()���R�[�����A�����I�u�W�F�N�g���擾����
            Product l_product = 
                l_productManager.getProduct(Long.parseLong(l_swtInputRequest.switchingProductId));
            
            // 1.7 to����(Row)
            MutualFundProductRow l_mfSwtProductRow = (MutualFundProductRow) l_product.getDataSourceObject();
            l_mfSwtProduct = (WEB3MutualFundProduct) l_productManager.toProduct(l_mfSwtProductRow);
            log.debug("�抷��̖���.getProductId = " + l_mfSwtProduct.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__NotFoundException__ when l_productManager.getProduct("
                    + "productId = "
                    + l_swtInputRequest.switchingProductId);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // 1.8 FinApp�N���X��getCommonOrderValidator()���R�[����
        //�����`�F�b�N�I�u�W�F�N�g���擾����      
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        // 1.9 ��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N 
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

		//1.10 reset�����R�[�h()
		//�m�����n
		//�����R�[�h�F �擾�����g�����M����.getProductCode()�̖߂�l
		WEB3MutualFundTradingTimeManagement.resetProductCode(l_mfProduct.getProductCode());

		//1.11 setTimestamp()
		WEB3MutualFundTradingTimeManagement.setTimestamp();
		
		//1.12 get�抷������
		// �抷�����̔��������擾����B
        // [����]
        //�抷�������R�[�h�F �擾�����抷�������̖����R�[�h
        //�抷������R�[�h�F �擾�����抷������̖����R�[�h
		Timestamp l_datBizDate = 
			new Timestamp(WEB3MutualFundTradingTimeManagement.getSwtOrderBizDate(
                l_mfProduct.getProductCode(),l_mfSwtProduct.getProductCode()).getTime());
		WEB3GentradeMainAccount l_genMainAccount = 
			(WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        
        //1.13 validate����\�ڋq(�ڋq, ������)           
        // �@@�|�����`�F�b�N.validate����\�ڋq()���R�[������
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
		
		// 1.14 validate�抷�\����
		//[����] 
		//�⏕����
		//�抷�������F �抷���̓��M�����I�u�W�F�N�g 
		//�抷������F �抷��̓��M�����I�u�W�F�N�g 
		//�������F get���M������()�̖߂�l
		WEB3MutualFundOrderManager l_mfOrderManager = 
		    (WEB3MutualFundOrderManager)l_finApp.getTradingModule(
		        ProductTypeEnum.MUTUAL_FUND).getOrderManager();
		l_mfOrderManager.validateSwitchingPossProduct(
		    l_subAccount, 
		    l_mfProduct, 
		    l_mfSwtProduct, 
		    l_datBizDate);

        // �@@�|���M�����R���ʃ`�F�b�N.validate�ً}��~()���R�[������
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();

        // 1.15 ���\�c���������擾����
        // �@@�g�����M�|�W�V�����}�l�[�W��.calc���\�c������()���R�[��
        double l_dblSellPossiblePositionQty = 0;
        l_dblSellPossiblePositionQty =
            l_web3MfPositionMgr.calcSellPossiblePositionQty(
                l_subAccount,
                l_mfProduct,
                l_mfAsset.getAssetId() + "");
        
        //1.16 ���\�c������==0 �̏ꍇ�A��O���X���[����B        
        if(l_dblSellPossiblePositionQty == 0)
        {
            log.error("���\�c������������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00390,
                this.getClass().getName() + "." + STR_METHOD_NAME
                );  
        }
        
        //�d�q���`�F�b�N�t���O==true�̏ꍇ���{
        // validate�ژ_�����{��
        WEB3GentradeProspectusResult l_validateBataResult = null;
        if(l_swtInputRequest.batoCheckFlag)
        {
            // 1.17 validate�ژ_�����{��(String, String)
			//[����] 
			// ��ʃR�[�h�F ���N�G�X�g.��ʃR�[�h 
			// �����R�[�h�F �抷��̓��M����.�����R�[�h
            WEB3GentradeBatoClientService l_bataService =
                (WEB3GentradeBatoClientService)Services.getService(
                    WEB3GentradeBatoClientService.class);                
            l_validateBataResult = 
                l_bataService.validateProspectus(
                    l_swtInputRequest.typeCode, 
                    l_mfSwtProduct.getProductCode());
        }          
        
        // 1.18 get�抷����
		//�m�����n 
		//�،���ЁF �⏕����.getInstitution()�̖߂�l 
		//�抷�������R�[�h�F �抷�����M����.�����R�[�h 
		//�抷������R�[�h�F �抷�擊�M����.�����R�[�h 
        Date l_datSwtExecutedDate = 
            l_productManager.getSwtExecutedDate(
                l_subAccount.getInstitution(), 
                l_mfProduct.getProductCode(),
                l_mfSwtProduct.getProductCode());   
        
        // 1.19 get�抷��n��
		//�m�����n 
		//�،���ЁF �⏕����.getInstitution()�̖߂�l 
		//�抷�������R�[�h�F �抷�����M����.�����R�[�h 
		//�抷������R�[�h�F �抷�擊�M����.�����R�[�h 
        Date l_datSwtDeliveryDate = 
            l_productManager.getSwtDeliveryDate(
                l_subAccount.getInstitution(), 
                l_mfProduct.getProductCode(),
                l_mfSwtProduct.getProductCode());

        // 1.20 ���M�抷���̓��X�|���X�I�u�W�F�N�g�𐶐����A���^�[������
        WEB3MutualSwitchingInputResponse l_response =
            (WEB3MutualSwitchingInputResponse) l_swtInputRequest.createResponse();

        // 1.21 ���M�抷���̓��X�|���X�I�u�W�F�N�g�ɂ́A�ȉ��̒l��ݒ肷��
        // �@@�@@�抷�\�����F �擾�����抷�\�c������
        l_response.switchingAbleQty = WEB3StringTypeUtility.formatNumber(l_dblSellPossiblePositionQty);

        // �@@�@@�]���z�F�g�����M�|�W�V�����}�l�[�W��.calc�]���z()�̖߂�l
        l_response.marketValue = WEB3StringTypeUtility.formatNumber(
            l_web3MfPositionMgr.calcMarketValue(l_subAccount, l_mfProduct, l_mfAsset.getAssetId() + ""));

        // �@@�@@�����R�[�h�F �擾�����g�����M����.getProductCode()�̖߂�l
        l_response.mutualProductCode = l_mfProduct.getProductCode();

        // �@@�@@�������F �擾�����g�����M����.get������()�̖߂�l
        l_response.mutualProductName = l_mfProduct.getMutualProductName();

        //�@@�@@��񉿊z�ʉ݃R�[�h�F�擾�����g�����M����.get�ʉ݃R�[�h()�̖߂�l
        l_response.sellValueCurrencyCode = l_mfProduct.getCurrencyCode();

        // �@@�@@��񉿊z�F �擾�����g�����M����.get��񉿊z()�̖߂�l
        l_response.sellValue = WEB3StringTypeUtility.formatNumber(l_mfProduct.getSellValue());

        // �@@�@@����z�K�p���F �擾�����g�����M����.get����z�K�p��()�̖߂�l
        l_response.constantValueAppDate = WEB3DateUtility.toDay(
                    l_mfProduct.getConstantValueAppDate());

        // �@@�@@�������敪�F
        TaxTypeEnum l_taxType = l_mfAsset.getTaxType();
        if (TaxTypeEnum.NORMAL.equals(l_taxType))
        {
            l_response.taxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else
        {
            if (TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType)
                || TaxTypeEnum.SPECIAL.equals(l_taxType))
            {
                l_response.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
            else
            {
                log.debug("�ŋ敪�����̑��ł���");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00393,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ŋ敪�����̑��ł���");
            }
        }

        // �@@�@@�������@@�ꗗ�F
        String[] l_lisSellBuyDivList = null;
        //�|�擾�����g�����M����.is�O�����M�i�j�̖߂�l==true OR �擾�����g�����M����.isFWF�i�j�̖߂�l==true�̏ꍇ�A 
		//�h�P�F����h��ݒ肷��B
		if (l_mfProduct.isForeignFund() || l_mfProduct.isFWF())
		{
			l_lisSellBuyDivList = new String[1];
			l_lisSellBuyDivList[0] = WEB3ClaimDivDef.BUY;
		}
        //�|��L�ȊO�̏ꍇ�́A�擾�������M�����R���ʃ`�F�b�N.is���搿���\()���R�[������
        else if (l_validationsCheck.isBuyingRequestPossible(
            l_datBizDate,
            l_subAccount,
            l_mfProduct))
        {
            l_lisSellBuyDivList = new String[2];
            l_lisSellBuyDivList[0] = WEB3ClaimDivDef.SELL;
            l_lisSellBuyDivList[1] = WEB3ClaimDivDef.BUY;
        }
        else
        {
            l_lisSellBuyDivList = new String[1];
            l_lisSellBuyDivList[0] = WEB3ClaimDivDef.SELL;           
        }
        l_response.sellBuyDivList = l_lisSellBuyDivList;
        
        // �w����@@�ꗗ�F
        String[] l_strArraySwtDiv = null;
        String l_strSwtDiv = l_mfProduct.getSwitchingSelectable();
        if (WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(l_strSwtDiv))
        {
            l_strArraySwtDiv = new String[3];
            l_strArraySwtDiv[0] = WEB3SellDivDef.ALL_DESIGNATE;
            l_strArraySwtDiv[1] = WEB3SellDivDef.COUNT_DESIGNATE;
            l_strArraySwtDiv[2] = WEB3SellDivDef.MONEY_DESIGNATE;
        }
        else
        {
            if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strSwtDiv))
            {
                l_strArraySwtDiv = new String[2];
                l_strArraySwtDiv[0] = WEB3SellDivDef.ALL_DESIGNATE;
                l_strArraySwtDiv[1] = WEB3SellDivDef.MONEY_DESIGNATE;                
            }
            else
            {
                if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_strSwtDiv))
                {
                    l_strArraySwtDiv = new String[2];
                    l_strArraySwtDiv[0] = WEB3SellDivDef.ALL_DESIGNATE;
                    l_strArraySwtDiv[1] = WEB3SellDivDef.COUNT_DESIGNATE;
                }
                else
                {
                    log.debug("�w����@@�s��");
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�\�����Ȃ��V�X�e���G���[���������܂����B");
                }
            }
        }
        l_response.specifyDivList = l_strArraySwtDiv;

        // �@@�@@�抷���P�ʌ����F
        // �@@�@@�@@(*) �擾�����g�����M����.get�P�ʌ����i�抷�j()�̖߂�l��ݒ肷��
        l_response.switchingUnitQty = l_mfProduct.getSwitchingUnitQty() + "";

        // �@@�@@�抷���Œ�����F
        // �@@�@@�@@(*) �擾�����g�����M����.get�Œ�����i�抷�j()�̖߂�l��ݒ肷��
        l_response.switchingMinQty = l_mfProduct.getSwitchingMinQty() + "";

        // �@@�@@�抷���P�ʋ��z�F
        // �@@�@@�@@(*) �擾�����g�����M����.get�P�ʋ��z�i�抷�j()�̖߂�l��ݒ肷��
        l_response.switchingUnitAmt = l_mfProduct.getSwitchingUnitAmt() + "";

        // �@@�@@�抷���Œ���z�F
        // �@@�@@�@@(*) �擾�����g�����M����.get�Œ���z�i�抷�j()�̖߂�l��ݒ肷��
        l_response.switchingMinAmt = l_mfProduct.getSwitchingMinAmt() + "";

        // �@@�@@�������F �擾����������
        l_response.orderBizDate = WEB3DateUtility.toDay(l_datBizDate);

        // �@@�@@�����F �擾��������
        l_response.executionTimestamp = WEB3DateUtility.toDay(l_datSwtExecutedDate);

        // �@@�@@��n���F �擾������n��
        l_response.deliveryDate = WEB3DateUtility.toDay(l_datSwtDeliveryDate);
        
		//�����R�[�h�i�抷��j�F �抷��̓��M����.getProductCode()�̖߂�l 
        l_response.switchingProductCode = l_mfSwtProduct.getProductCode();
        
		//�������i�抷��j�F �抷��̓��M����.get������()�̖߂�l 
        l_response.switchingProductName = l_mfSwtProduct.getMutualProductName();
        
        //���t����z�ʉ݃R�[�h�F �抷��̓��M����.get�ʉ݃R�[�h()�̖߂�l 
        l_response.buyConstantValueCurrencyCode = l_mfSwtProduct.getCurrencyCode();
        
		//���t����z�F �抷��̓��M����.get���t����z()�̖߂�l 
        l_response.buyConstantValue = 
            WEB3StringTypeUtility.formatNumber(l_mfSwtProduct.getConstantValue());
        
        //���t����z�K�p���F �抷��̓��M����.get����z�K�p��()�̖߂�l         
        l_response.buyConstantValueAppDate = WEB3DateUtility.toDay(
            l_mfSwtProduct.getConstantValueAppDate());
		
        //���t�����敪�ꗗ�F  
		//�@@�|�ڋq.is��������J��()==true and �抷�擊�M����.is�����^()==true�̏ꍇ 
		//�@@�@@ �h��ʁh�Ɓh����h��ݒ肷��B 
		//�@@�|����ȊO�̏ꍇ 
		//�@@�@@ �h��ʁh��ݒ肷��B 
        boolean l_blnIsSpecialAccountEstablished = 
            l_genMainAccount.isSpecialAccountEstablished(
                l_datSwtDeliveryDate,
                l_subAccount);
        boolean l_blnStockType = l_mfSwtProduct.isStockType();
        

        if(l_blnIsSpecialAccountEstablished && l_blnStockType)
        {
            String[] l_strAccountDivDef = new String[2];
            l_strAccountDivDef[0] = WEB3TaxTypeSpecialDef.NORMAL;
            l_strAccountDivDef[1] = WEB3TaxTypeSpecialDef.SPECIAL;
            l_response.taxTypeList = l_strAccountDivDef;
        }
        else
        {
            String[] l_strAccountDivDef = new String[1];
            l_strAccountDivDef[0] = WEB3TaxTypeSpecialDef.NORMAL;
            l_response.taxTypeList = l_strAccountDivDef;
        }

		//�ژ_�����{���`�F�b�N���ʁF validate�ژ_�����{��()�̖߂�l 
        l_response.prospectusResult = l_validateBataResult;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
