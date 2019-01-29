head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyApplyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t�V�K�\���T�[�r�XImpl(WEB3MutualFixedBuyApplyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 �юu�� (���u) �V�K�쐬
                 : 2006/07/27 ���G�� (���u) �d�l�ύX ���f��No.466
                 : 2006/08/10 �R���@@�iSRA�j����e�X�g��Q No.U02875        
Revesion History : 2007/10/25 ���^�] (���u) �d�l�ύX ���f��No.584
Revesion History : 2007/10/30 ��іQ (���u) �d�l�ύX ���f��No.586
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFixedBuyCommonService;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyConfirmRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyInputRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyInputResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionUnit;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyApplyService;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M�莞��z���t�V�K�\���T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3MutualFixedBuyApplyServiceImpl 
    extends WEB3MutualClientRequestService 
        implements WEB3MutualFixedBuyApplyService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyApplyServiceImpl.class);

    /**
     * ���M�莞��z���t�V�K�\���T�[�r�X���������{����B<BR> 
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A <BR>
     * input�莞��z���t�V�K�\��()�Avalidate�莞��z���t�V�K�\��() <BR>
     * �����ꂩ�̃��\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        //input�莞��z���t�V�K�\��()�̏ꍇ
        if (l_request instanceof WEB3MutualFixedBuyApplyInputRequest)
        {
            l_response = this.inputFixedBuyApply((WEB3MutualFixedBuyApplyInputRequest)l_request);
        }
        
        //validate�莞��z���t�V�K�\��()�̏ꍇ
        else if (l_request instanceof WEB3MutualFixedBuyApplyConfirmRequest)
        {
            l_response = this.validateFixedBuyApply((WEB3MutualFixedBuyApplyConfirmRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
       
        log.exiting(STR_METHOD_NAME);
        return l_response;
     }
    
    /**
     * (input�莞��z���t�V�K�\��)<BR>
     * �����M���莞��z���t�V�K�\�����͂��s���B<BR> 
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(���M)�莞��z���t�V�K�\���v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3MutualFixedBuyApplyInputResponse
     * @@throws WEB3BaseException 
     */
    protected WEB3MutualFixedBuyApplyInputResponse inputFixedBuyApply(
        WEB3MutualFixedBuyApplyInputRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "inputFixedBuyApply(WEB3MutualFixedBuyApplyInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1)validate������t�\()
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();
        
        //1.2) get�⏕����()
        SubAccount l_subAccount = this.getSubAccount();       
        
        //1.3.getCommonOrderValidator( )
        //�����`�F�b�N�I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //1.4.get������( )        
        //"���M�莞��z���t"�ɑ΂��锭�������擾����B
        Date l_datOrderBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("l_datOrderBizDate = " + l_datOrderBizDate);
        
        //1.5.validate����\�ڋq(�ڋq : �ڋq, ������ : Timestamp)
        //[validate����\�ڋq�ɓn������]  
        //�@@�ڋq�F�擾�����⏕����.getMainAccount()  
        //�@@�������F������ԊǗ�.get���M������()�̖߂�l
        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
	    OrderValidationResult l_validationResult =
	        l_orderValidator.validateAccountForTrading(
	            l_genMainAccount,  
	            new Timestamp(l_datOrderBizDate.getTime()));
	            
		if (l_validationResult.getProcessingResult().isFailedResult())
		{
			log.debug("�莞��z���t�����~�ڋq�G���[");
			throw new WEB3BaseException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02524,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"�莞��z���t�����~�ڋq�G���[");
		}	                
        
        //1.6.getMainAccount()
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //1.7) get���M�����J�e�S���[���X�g()
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        List l_lisProductCategory = 
            l_mfProductManager.getMutualFundProductCategoryList(l_strInstitutionCode);
        
        //1.8)�����򏈗���get���M�����J�e�S���[���X�g()�̖߂�l > 0���̏ꍇ
        WEB3MutualProductCategoryUnit[] l_productCategoryUnits = null;
        if (l_lisProductCategory != null && l_lisProductCategory.size() > 0)
        {
            //1.8.1) create���M�����J�e�S���[�ꗗ(List)
            l_productCategoryUnits = 
                l_mfProductManager.createMutualFundProductCategoryList(l_lisProductCategory);
        }
        
        //1.9.)get�莞��z���t�\�������X�g()
        List lisBuyPosProduct = 
            l_mfProductManager.getFixedBuyPossibleProductList(l_strInstitutionCode);
        
        //1.10.)get�莞��z���t�\�������X�g()�̖߂�l��List�v�f����LOOP
        List l_lisUnits = new ArrayList();
        if (lisBuyPosProduct != null && lisBuyPosProduct.size() > 0)
        {
            int l_intLength = lisBuyPosProduct.size();
            for (int i = 0; i < l_intLength; i++)
            {
                //1.10.1)���M�莞��z���t�����s
                WEB3MutualFixedBuyConditionUnit l_unit = 
                    new WEB3MutualFixedBuyConditionUnit();
                
                //�����R�[�h = �g�����M����.getProductCode
                WEB3MutualFundProduct l_fundProduct = 
                    (WEB3MutualFundProduct)lisBuyPosProduct.get(i);
                MutualFundProductRow l_row = 
                    (MutualFundProductRow)l_fundProduct.getDataSourceObject();
                
                l_unit.mutualProductCode = l_row.getProductCode();
                
                //������ = �g�����M����.get������
                l_unit.mutualProductName = l_row.getStandardName();
                
                //���M�����J�e�S���[�R�[�h = �g�����M����.get�J�e�S���[�R�[�h
                l_unit.categoryCode = l_row.getCategoryCode();
                
                //����L�ȊO�̍��ڂɂ�null���Z�b�g����B
                l_unit.displayOrder = null;
                l_unit.increaseBuyAmount = null;
                l_unit.monthlyBuyAmount = null;
                l_unit.validStartDate = null;
                
                l_lisUnits.add(l_unit);
            }
        }
        
        WEB3MutualFixedBuyConditionUnit[] l_units = 
            new WEB3MutualFixedBuyConditionUnit[l_lisUnits.size()];
        l_lisUnits.toArray(l_units);
        
        //create���X�|���X
        WEB3MutualFixedBuyApplyInputResponse l_response = 
            (WEB3MutualFixedBuyApplyInputResponse)l_request.createResponse();
        
        //�v���p�e�B�Z�b�g
        //�ڋq�R�[�h = �擾�����ڋq.get�\���ڋq�R�[�h�i�j
		WEB3GentradeMainAccount l_gentradeMainAccount = null;                    
		try                                                                      
		{                                                                        
		 l_gentradeMainAccount =                                                 
		  (WEB3GentradeMainAccount) l_finApp.getAccountManager().getMainAccount(l_mainAccount.getAccountId());                                  
		}                                                                        
		catch (NotFoundException l_ex)                                           
		{                                                                        
		 log.error("getMainAccount not found");                                  
		 log.exiting(STR_METHOD_NAME);                                           
		 throw new WEB3SystemLayerException(                                     
		  WEB3ErrorCatalog.SYSTEM_ERROR_80005,                                   
		  this.getClass().getName() + "." + STR_METHOD_NAME,                     
		  "getMainAccount not found",l_ex);                                                                  
		}                                                                        
                                                                            
		l_response.accountCode = l_gentradeMainAccount.getDisplayAccountCode();                                                                       
                                                                                    
        //�ڋq���i�J�i�j = �擾�����ڋq.���O�i�c���P�j
        l_response.accountNameKana = l_mainAccountRow.getFamilyNameAlt1();
        
        //�ڋq���i�����j = �擾�����ڋq.���O�i�c���j
        l_response.accountName = l_mainAccountRow.getFamilyName();
        
        //�X�֔ԍ� = �擾�����ڋq.�X�֔ԍ�
        l_response.zipCode = l_mainAccountRow.getZipCode();
        
        //�Z���P = �擾�����ڋq.�Z���P
        l_response.address1 = l_mainAccountRow.getAddressLine1();
        
        //�Z���Q = �擾�����ڋq.�Z���Q
        l_response.address2 = l_mainAccountRow.getAddressLine2();
        
        //�Z���R = �擾�����ڋq.�Z���R
        l_response.address3 = l_mainAccountRow.getAddressLine3();
        
        //���M�莞��z���t�����ꗗ = ���M�莞��z���t�����s�̔z��
        l_response.conditionList = l_units;
        
        //���M�����J�e�S���[�ꗗ = create���M�����J�e�S���[�ꗗ()�̖߂�l
        l_response.categoryList = l_productCategoryUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�莞��z���t�V�K�\��)<BR>
     * �����M���莞��z���t�V�K�\���m�F���s���B<BR> 
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(���M)�莞��z���t�V�K�\���m�F�v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)�莞��z���t�V�K�\���R���v: <BR>
     * �@@�@@�@@�@@1.4.3)�����򏈗���is�莞��z���t�\()�̖߂�l == false �̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *  �@@�@@�@@�@@tag:   BUSINESS_ERROR_02480<BR>
     *  ========================================================<BR>
     *  <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3MutualFixedBuyApplyConfirmResponse
     * @@throws WEB3BaseException 
     */
    protected WEB3MutualFixedBuyApplyConfirmResponse validateFixedBuyApply(
        WEB3MutualFixedBuyApplyConfirmRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateFixedBuyApply(WEB3MutualFixedBuyApplyConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1)validate()
        l_request.validate();
        
        //1.2)validate������t�\()
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();
        
        //1.3) get�⏕����()
        SubAccount l_subAccount = this.getSubAccount();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        
        WEB3MutualFixedBuyCommonService l_service = 
            (WEB3MutualFixedBuyCommonService) Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        
        //1.4.getCommonOrderValidator( )
        //�����`�F�b�N�I�u�W�F�N�g���擾����B
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //1.5.get������( )        
        //"���M�莞��z���t"�ɑ΂��锭�������擾����B
        Date l_datOrderBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("l_datOrderBizDate = " + l_datOrderBizDate);
        
        //1.6.validate����\�ڋq(�ڋq : �ڋq, ������ : Timestamp)
        //[validate����\�ڋq�ɓn������]  
        //�@@�ڋq�F�擾�����⏕����.getMainAccount()  
        //�@@�������F������ԊǗ�.get���M������()�̖߂�l
        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        OrderValidationResult l_validationResult =
            l_orderValidator.validateAccountForTrading(
                l_genMainAccount,  
                new Timestamp(l_datOrderBizDate.getTime()));
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����~�ڋq�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����~�ڋq�G���[");
        }   
        
        //�l�̏�����
        //work�p�̓d�q����Q���t���O��false���Z�b�g�B
        boolean l_blnBatoTroubleFlag = false;

        boolean l_blnForeignSecAccOpenFlag = false;

        //���M�莞��z���t���ʏ��ꗗ.�d�q���`�F�b�N�t���O
        boolean l_blnBatoCheckFlag = false;

        //1.7)���N�G�X�g�f�[�^.���M�莞��z���t���ʏ��ꗗ�̗v�f����LOOP
        int l_intLength = l_request.commonList.length;

        List l_lisNoReadProductCodes = new ArrayList();

        List l_lisCheckResults = new ArrayList();

        String[] l_strNoReadProductCodes = null;

        WEB3GentradeBatoClientService l_batoService =
            (WEB3GentradeBatoClientService)Services.getService(
                WEB3GentradeBatoClientService.class);

        WEB3GentradeProspectusResult l_validateBataResult = null;

        for (int i = 0; i < l_intLength; i++)
        {
            //1.7.1)get���M����()
            WEB3MutualFundProduct l_mfProduct = null;
            try
            {
                l_mfProduct = 
                    (WEB3MutualFundProduct)l_mfProductManager.getMutualFundProduct(
                        l_subAccount.getInstitution(),
                        l_request.commonList[i].mutualProductCode);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�،���ЁA�����R�[�h�ɑΉ����铊�M�����f�[�^�����݂��܂���");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "",
                    l_nfe);
            }
            
            //1.7.2)is�莞��z���t�\()
            boolean l_blnFixedBuyPos = l_mfProduct.isFixedBuyPossible();
            
            //1.7.3)�����򏈗���is�莞��z���t�\()�̖߂�l == false �̏ꍇ�A
            if (!l_blnFixedBuyPos)
            {
                log.debug("�莞��z���t�s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02480,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_request.commonList[i].mutualProductCode);
            }
            
            //1.7.4) validate�O���،������J��()
            if (!l_blnForeignSecAccOpenFlag)
            {
                l_blnForeignSecAccOpenFlag = 
                    l_service.validateForeignSecAccOpen(l_subAccount, l_mfProduct);
            }
            
            //1.7.5)validate�莞��z���t���z()
            try 
            {
                l_service.validateFixedBuyAmount(
                    l_subAccount,
                    l_request.commonList[i].monthlyBuyAmount,
                    l_request.commonList[i].increaseBuyAmount);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("�莞��z���t�Œ���z�`�F�b�N�A�P�ʋ��z�`�F�b�N���s�Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_request.commonList[i].mutualProductCode);
            }

            //�����򏈗������N�G�X�g.���M�莞��z���t���ʏ��ꗗ.�d�q���`�F�b�N�t���O ==
            //true����work�p�̓d�q����Q���t���O��false�̏ꍇ
            if (l_request.commonList[i].batoCheckFlag
                && !l_blnBatoTroubleFlag)
            {
                //validate�ژ_�����{��(��ʃR�[�h : String, �����R�[�h : String)
                //�ژ_�����{���̃`�F�b�N���s�Ȃ��B
                //[validate�ژ_�����{���ɓn���p�����^]
                //    ��ʃR�[�h�F���N�G�X�g.���M�莞��z���t���ʏ��ꗗ.��ʃR�[�h
                //    �����R�[�h�F���N�G�X�g.���M�莞��z���t���ʏ��ꗗ.�����R�[�h
                l_validateBataResult = l_batoService.validateProspectus(
                    l_request.commonList[i].typeCode,
                    l_request.commonList[i].mutualProductCode);

                l_lisCheckResults.add(l_validateBataResult.checkResult);

                //�����򏈗���validate�ژ_�����{��()�̖߂�l���u1:�{�����ρv�̏ꍇ
                if (WEB3GentradeBatoCheckResultDef.UNINSPECTION.equals(l_validateBataResult.checkResult))
                {
                    //work�p�̖ژ_�����{�����ϖ����R�[�h�ꗗ�Ƀ��N�G�X�g.
                    //���M�莞��z���t���ʏ��ꗗ.�����R�[�h��ǉ�����B
                    l_lisNoReadProductCodes.add(l_request.commonList[i].mutualProductCode);
                }

                //�����򏈗���validate�ژ_�����{��()�̖߂�l���u2:�{������(��Q��)�v�̏ꍇ
                if (WEB3GentradeBatoCheckResultDef.UNINSPECTION_TROUBLE.equals(l_validateBataResult.checkResult))
                {
                    //work�p�̓d�q����Q���t���O��true���Z�b�g�B
                    l_blnBatoTroubleFlag = true;
                }

                //1���ł����N�G�X�g.���M�莞��z���t���ʏ��ꗗ.�d�q���`�F�b�N�t���O��true�̏ꍇ
                l_blnBatoCheckFlag = true;
            }
        }
        
        //1.8)create���X�|���X()
        WEB3MutualFixedBuyApplyConfirmResponse l_response = 
            (WEB3MutualFixedBuyApplyConfirmResponse)l_request.createResponse();
        
        //1.9)�v���p�e�B�Z�b�g
        //�O���،������J�ݗv�t���O 
        l_response.foreignSecAccOpenFlag = l_blnForeignSecAccOpenFlag;

        //�d�q���`�F�b�N���� = �S�Ẵ��N�G�X�g.���M�莞��z���t���ʏ��ꗗ.�d�q���`�F�b�N�t���O��false�̏ꍇ�A
        //�0:�{���ϣ���Z�b�g
        //1���ł����N�G�X�g.���M�莞��z���t���ʏ��ꗗ.�d�q���`�F�b�N�t���O��true�̏ꍇ
        //work�p�̓d�q����Q���t���O��true�̏ꍇ�A�2:�{������(��Q��)����Z�b�g�B
        //                    work�p�̓d�q����Q���t���O��false�̏ꍇvalidate�ژ_�����{��()
        //�̖߂�l���S�Ģ0:�{���ϣ�̏ꍇ�A�0:�{���ϣ���Z�b�g�B1���ł��߂�l���1:�{�����ϣ������΁A�1:�{�����ϣ���Z�b�g�B
        if (!l_blnBatoCheckFlag)
        {
            l_response.batoCheckResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
        }
        else if (l_blnBatoTroubleFlag)
        {
            l_response.batoCheckResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION_TROUBLE;
        }
        else if (l_lisCheckResults.contains(WEB3GentradeBatoCheckResultDef.UNINSPECTION))
        {
            l_response.batoCheckResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION;
        }
        else
        {
            l_response.batoCheckResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
        }

        //work�p�̖ژ_�����{�����ϖ����R�[�h�ꗗ��1�����Ȃ��ꍇ�Anull���Z�b�g�B
        if (l_lisNoReadProductCodes.size() == 0)
        {
            l_response.noReadProductCodeList = null;
        }
        //�ژ_�����{�����ϖ����R�[�h�ꗗ = work�p�̖ژ_�����{�����ϖ����R�[�h�ꗗ���Z�b�g�B
        else
        {
            l_strNoReadProductCodes = new String[l_lisNoReadProductCodes.size()];

            l_response.noReadProductCodeList =
                (String[])l_lisNoReadProductCodes.toArray(l_strNoReadProductCodes);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
