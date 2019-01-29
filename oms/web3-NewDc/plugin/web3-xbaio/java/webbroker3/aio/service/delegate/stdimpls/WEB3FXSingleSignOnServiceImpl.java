head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXSingleSignOnServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �V���O���T�C���I���T�[�r�XImpl(WEB3FXSingleSignOnServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/8/28 ��O�� (���u) �V�K�쐬   
Revesion History : 2008/05/20 �đo�g (���u) �d�l�ύX ���f��No.849
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxUnnecessaryExplanationRow;
import webbroker3.aio.data.SsoMessageParams;
import webbroker3.aio.define.WEB3AioHashAlgorithmDef;
import webbroker3.aio.define.WEB3GftTelegramFormatDef;
import webbroker3.aio.message.WEB3FXSingleSignOnRequest;
import webbroker3.aio.message.WEB3FXSingleSignOnResponse;
import webbroker3.aio.message.WEB3FXTradeAgreementRequest;
import webbroker3.aio.message.WEB3FXTradeAgreementResponse;
import webbroker3.aio.service.delegate.WEB3FXSingleSignOnService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�V���O���T�C���I���T�[�r�XImpl) <BR>
 * �V���O���T�C���I���T�[�r�X�����N���X <BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FXSingleSignOnServiceImpl extends WEB3ClientRequestService
    implements WEB3FXSingleSignOnService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSingleSignOnServiceImpl.class);  
    
    /**
     * @@roseuid 41E77F4A01D4
     */
    public WEB3FXSingleSignOnServiceImpl()
    {
    }

    /**
     * �V���O���T�C���I���T�[�r�X�������s���B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B <BR>
     * �@@�EgetFX������Ӊ��() <BR>
     * �@@�Esubmit�O���בփV�X�e���\��() <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@roseuid 41C7B2080071
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response;
        
        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B 
        // �EgetFX������Ӊ��()
        // �Esubmit�O���בփV�X�e���\��()
        if (l_request instanceof WEB3FXTradeAgreementRequest)
        {
            l_response = 
                getFXTradeAgreementScreen((WEB3FXTradeAgreementRequest)l_request);   
        }
        else if (l_request instanceof WEB3FXSingleSignOnRequest)
        {
            l_response =
                submitDisplayExterFxSystem((WEB3FXSingleSignOnRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }

    /**
     * (getFX������Ӊ��) <BR>
     * FX������Ӊ�ʕ\���f�[�^�̎擾�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�V���O���T�C���I���jgetFX������Ӊ�ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTradeAgreementResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C7B2080090
     */
    protected WEB3FXTradeAgreementResponse getFXTradeAgreementScreen(
        WEB3FXTradeAgreementRequest l_request)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "getTradeAgreementScreen(WEB3FXTradeAgreementRequest l_request)";
        log.entering(STR_METHOD_NAME);   
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 get�⏕����(SubAccountTypeEnum)
        //�⏕�����I�u�W�F�N�g���擾����B  
        //[����]  
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.2 validate������t�\( )
        //�ȉ��̃`�F�b�N���s���B  
        //�@@�|��t���ԃ`�F�b�N  
        //�@@�|�V�X�e����~���`�F�b�N  
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.3 get��Е�FX�V�X�e������(String, String, String)
        //��Е�FX�V�X�e������Params���擾����B  
        //[����]  
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h  
        //���X�R�[�h�F�@@�⏕����.get����X.getBranchCode()
        //FX�V�X�e���R�[�h�F���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        WEB3FXDataControlService l_dataControlService = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        
        CompFxConditionParams l_compFxConditionparams = null;
        try
        {
            l_compFxConditionparams =
                l_dataControlService.getCompFxCondition(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in get��Е�FX�V�X�e������()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.4 validate�O���V�X�e����t�\(String)
        //FX�V�X�e���̎�t���ԃ`�F�b�N���s���B  
        //[����]  
        //�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        
        l_orderManager.validateOtherSystemAcceptPossible(
            l_compFxConditionparams.getFxSystemCode());
        
        //1.5 validateFX�����J��(SubAccount, String)
        //FX��������J�݂̃`�F�b�N���s���B 
        //[����] 
        //�⏕�����Fget�⏕����()�̖߂�l 
        //FX�V�X�e���R�[�h�F��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        l_orderManager.validateFXAccOpen(
            l_subAccount, 
            l_compFxConditionparams.getFxSystemCode());
        
        //1.6  createResponse()
        WEB3FXTradeAgreementResponse l_response = 
            (WEB3FXTradeAgreementResponse)l_request.createResponse();
            
        //1.7 ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        MainAccountRow l_mainAccountRow = (MainAccountRow)
            l_subAccount.getMainAccount().getDataSourceObject();
        
        String l_strFamilyName = l_mainAccountRow.getFamilyName();
             
        //�ڋq��: �ڋq(*1).�c��
        //(*1) �⏕����.getMainAccount()�ɂĎ擾
        l_response.accountName = l_strFamilyName;
                                     
        log.exiting(STR_METHOD_NAME);            
        //1.8 
        return l_response;
    }

    /**
     * (submit�O���בփV�X�e���\��) <BR>
     * �O���בփV�X�e���\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�V���O���T�C���I���jsubmit�O���בփV�X�e���\���v�Q�ƁB <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXSingleSignOnResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C7B20800AF
     */
    protected WEB3FXSingleSignOnResponse submitDisplayExterFxSystem(
        WEB3FXSingleSignOnRequest l_request)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "submitDisplayExterFxSystem(WEB3FXSingleSignOnRequest l_request)";
        log.entering(STR_METHOD_NAME);        

        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 validate( )
        //�N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //1.2 get�⏕����(SubAccountTypeEnum)
        //�⏕�����I�u�W�F�N�g���擾����B  
        //[����]  
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.3 validate������t�\( )
        //�ȉ��̃`�F�b�N���s���B  
        //�@@�|��t���ԃ`�F�b�N  
        //�@@�|�V�X�e����~���`�F�b�N  
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.4 get��Е�FX�V�X�e������(String, String, String)
        //��Е�FX�V�X�e������Params���擾����B  
        //[����]  
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h  
        //���X�R�[�h�F�@@�⏕����.get����X.getBranchCode()
        //FX�V�X�e���R�[�h�F���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        WEB3FXDataControlService l_dataControlService = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        
        CompFxConditionParams l_compFxConditionparams = null;
        try
        {
            l_compFxConditionparams =
                l_dataControlService.getCompFxCondition(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in get��Е�FX�V�X�e������()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.5 validate�O���V�X�e����t�\(String)
        //FX�V�X�e���̎�t���ԃ`�F�b�N���s���B  
        //[����]  
        //�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        
        l_orderManager.validateOtherSystemAcceptPossible(
            l_compFxConditionparams.getFxSystemCode());
        
        //1.6 validateFX�����J��(SubAccount, String)
        //FX��������J�݂̃`�F�b�N���s���B 
        //[����] 
        //�⏕�����Fget�⏕����()�̖߂�l 
        //FX�V�X�e���R�[�h�F��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        l_orderManager.validateFXAccOpen(
            l_subAccount, 
            l_compFxConditionparams.getFxSystemCode());        
        
        //1.7 if�@@���N�G�X�g�f�[�^.�d�q���`�F�b�N�t���O==true
        if (l_request.batoCheckFlag)
        {
            //1.7.1 validateFX�h�L�������g�{������(String, String[])
            //FX�h�L�������g�{�������̃`�F�b�N���s�� 
            //[����] 
            //���N�G�X�g�f�[�^.��ʃR�[�h 
            //���N�G�X�g�f�[�^.���ʃR�[�h[]
            String[] l_strReadHistorys = 
                l_dataControlService.validateFxDocReadHistory(
                    l_request.typeCode, 
                    l_request.requestCode);

            int l_intLength = 0;
            if (l_strReadHistorys != null)
            {
                l_intLength = l_strReadHistorys.length;
            }
            //1.7.2 if�@@validateFX�h�L�������g�{������()�̖߂�l != null or 
            //          validateFX�h�L�������g�{������()�̖߂�l.length != 0
            if (l_strReadHistorys != null || l_intLength != 0)
            {
                //1.7.2.1  createResponse()
                WEB3FXSingleSignOnResponse l_response = 
                    (WEB3FXSingleSignOnResponse)l_request.createResponse(); 
                
                //1.7.2.2 �i*�j�v���p�e�B�Z�b�g
                //���ʃR�[�h = validateFX�h�L�������g�{�������̖߂�l
                l_response.requestCode = l_strReadHistorys;
                
                //�����s�v���������`�F�b�N���� = false;
                l_response.noExplainAgreeHistoryCheck = false;
                
                //�Í��������� = null
                l_response.encryptString = null;
                
                //�閧�� = null
                l_response.secretKey = null;
                
                //�n�b�V���l = null
                l_response.hashValue = null;
                
                //URL = null
                l_response.fxUrl = null;
                
                //1.7.2.3
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }            
        }
        
        //1.8 if�@@���N�G�X�g�f�[�^.�����s�v���������쐬�t���O == true
        if (l_request.noExplainAgreeHistoryFlag)
        {
            //1.8.1 insert�����s�v��������(String, String, String)
            //�����s�v���������̍쐬���s�� 
            //[����]  
            //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h  
            //���X�R�[�h�F �⏕����.get����X.getBranchCode()  
            //�ڋq�R�[�h�F �⏕����.getMainAccount().getAccountCode() 
            l_dataControlService.insertUnnecessaryExplanation(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                l_subAccount.getMainAccount().getAccountCode());
        }
        
        //1.9 create��������������(String)
        //��������������𐶐�����B 
        //[����] 
        //�����s�v�����L�����ԁF��Е�FX�V�X�e������Params.�����s�v�����L������
        String l_strQueryString = 
            this.createQueryString(l_compFxConditionparams.getValidTerm());
        log.debug("�������������� " + l_strQueryString);
        
        //1.10 create���������f�[�^�R���e�i(String, String, String, String)
        //���������f�[�^�R���e�i���쐬����B  
        //[����]  
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h  
        //���X�R�[�h�F �⏕����.get����X.getBranchCode()  
        //�ڋq�R�[�h�F �⏕����.getMainAccount().getAccountCode() 
        //�����s�v�����L�����ԁF ��Е�FX�V�X�e������Params.�����s�v�����L������ 
        Object[] l_queryContainers = 
            this.createQueryContainer(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                l_subAccount.getMainAccount().getAccountCode(), 
                l_compFxConditionparams.getValidTerm());
        
        //1.11 create�\�[�g����( )(�V���O���T�C���I���T�[�r�XImpl::create�\�[�g����)
        //�\�[�g�������쐬����B
        String l_strSortCond = this.createSortCond();
        log.debug("�\�[�g���� " + l_strSortCond);

        //1.12 is�����s�v��������(String, Object[], String)
        //�����s�v���������`�F�b�N���s�� 
        //[����]  
        //��������������F�@@create��������������()�̖߂�l  
        //���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l  
        //�\�[�g�����F�@@create�\�[�g����()�̖߂�l                
        boolean l_blnIsUnnecessary = 
            this.isUnnecessaryExplanation(
                l_strQueryString, 
                l_queryContainers, 
                l_strSortCond);

        //1.13 if�@@is�����s�v��������()�̖߂�l == false
        if (!l_blnIsUnnecessary)
        {
            log.debug("is�����s�v��������()�̖߂�l == false");
            
            //1.13.1  createResponse()
            WEB3FXSingleSignOnResponse l_response = 
                (WEB3FXSingleSignOnResponse)l_request.createResponse();     
            
            //1.13.2 �i*�j�v���p�e�B�Z�b�g
            //���ʃR�[�h = null
            l_response.requestCode = null;
            
            //�����s�v���������`�F�b�N���� = is�����s�v���������̖߂�l;
            l_response.noExplainAgreeHistoryCheck = l_blnIsUnnecessary;
            
            //�Í��������� = null
            l_response.encryptString = null;
            
            //�閧�� = null
            l_response.secretKey = null;
            
            //�n�b�V���l = null
            l_response.hashValue = null;
            
            //URL = null
            l_response.fxUrl = null;
            
            //1.13.3
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //1.14 create����( )
        //�����i4���j�𐶐�����B
        String l_strRandom = this.createRandom();
        log.debug("�����i4���j= " + l_strRandom);
        
        //1.15 create�閧��( )(�V���O���T�C���I���T�[�r�XImpl::create�閧��)
        //�閧���i8���j�������_���ɐ�������B
        String l_strSecretKey = this.creatSecretKey();        
        log.debug("�閧���i8���j= " + l_strSecretKey);
        
        //1.16 getFX�ڋq(String, String, String, String)
        //FX�ڋqParams���擾����B 
        //[����] 
        //�،���ЃR�[�h�F�⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�⏕����.get����X.getBranchCode() 
        //FX�V�X�e���R�[�h�F��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h 
        //�ڋq�R�[�h�F�⏕����.getMainAccount().getAccountCode() 
        FxAccountParams l_fxAccountParams = null;
        try
        {
            l_fxAccountParams = 
                l_dataControlService.getFXAccount(
                    l_subAccount.getInstitution().getInstitutionCode(), 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    l_compFxConditionparams.getFxSystemCode(), 
                    l_subAccount.getMainAccount().getAccountCode());
        }
        catch(NotFoundException l_ex)
        {
            log.error("__an notFoundexpected error__ ",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //1.17 create�|�X�g�p�����O�f�[�^(String, String)
        //�|�X�g�p�����O�f�[�^�𐶐�����B 
        //[����] 
        //FX���O�C��ID�FFX�ڋq.FX���O�C��ID 
        //�����Fcreate����()�̖߂�l
        String l_strFxLoginId = l_fxAccountParams.getFxLoginId() + "";
        
        String l_strPostCreateData = 
            this.createPostCreateData(
                l_strFxLoginId, 
                l_strRandom);
        
        log.debug("�|�X�g�p�����O�f�[�^ = " + l_strPostCreateData);

        //1.18 create�Í���������(String, String)
        //�Í���������𐶐�����B 
        //[����] 
        //�閧���Fcreate�閧��()�̖߂�l 
        //�|�X�g�p�����O�f�[�^�Fcreate�|�X�g�p�����O�f�[�^�̖߂�l
        String l_strEncryptString = 
            this.creatEncryptString(
                l_strSecretKey, 
                l_strPostCreateData);
        
        log.debug("�Í��������� = " + l_strEncryptString);
        
        //1.19 create�n�b�V���l(String, String)
        //�n�b�V���l�𐶐�����B 
        //[����] 
        //�Í���������Fcreate�Í���������()�̖߂�l 
        //�閧���Fcreate�閧��()�̖߂�l
        String l_strHashValue = 
            this.creatHashValue(l_strEncryptString, l_strSecretKey);
        
        log.debug("�n�b�V���l = " + l_strHashValue);
            
        //1.20 insert���M�d��(SubAccount, ��Е�FX�V�X�e������Params, String, String, String, String)
        //���M�d�����V���O���T�C���I���ۑ��e�[�u����insert����B 
        //[����] 
        //�⏕�����Fget�⏕����()�̖߂�l 
        //�|�X�g�p�����O�f�[�^�Fcreate�|�X�g�p�����O�f�[�^()�̖߂�l 
        //�Í���������Fcreate�Í���������()�̖߂�l 
        //�閧���Fcreate�閧��()�̖߂�l 
        //�n�b�V���l�Fcreate�n�b�V���l()�̖߂�l
        this.insertSsoMessage(
            l_subAccount, 
            l_strPostCreateData, 
            l_strEncryptString, 
            l_strSecretKey, 
            l_strHashValue);
        
        //1.21 createResponse()
        WEB3FXSingleSignOnResponse l_response = 
            (WEB3FXSingleSignOnResponse)l_request.createResponse();            
        
        //1.22 ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B       
        //���ʃR�[�h = null
        l_response.requestCode = null;
        
        //�����s�v���������`�F�b�N���� = is�����s�v���������̖߂�l;
        l_response.noExplainAgreeHistoryCheck = l_blnIsUnnecessary;
        
        //�Í��������� = create�Í���������()�̖߂�l 
        l_response.encryptString = l_strEncryptString;
        
        //�閧�� = create�閧��()�̖߂�l 
        l_response.secretKey = l_strSecretKey;
        
        //�n�b�V���l = create�n�b�V���l()�̖߂�l
        l_response.hashValue = l_strHashValue;
        
        //URL = ��Е�FX�V�X�e������Params.Single Sign-On URL
        l_response.fxUrl = l_compFxConditionparams.getSingleSignOnUrl();
                         
        //1.23
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (is�����s�v��������) <BR>
     * FX�����s�v���������`�F�b�N���s��  <BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>  
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]  <BR>
     * �@@�@@arg0�F�@@FX�����s�v���������Ǘ�Row.TYPE  <BR>
     * �@@�@@arg1�F�@@����.��������������  <BR>
     * �@@�@@arg2�F�@@����.�\�[�g����  <BR>
     * �@@�@@arg3�F�@@null  <BR>
     * �@@�@@arg4�F�@@����.���������f�[�^�R���e�i  <BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Afalse��ԋp����B  <BR>
     * <BR>
     * �Q�j�������ʂ��擾�o�����ꍇ�Atrue��ԋp����B <BR>
     * <BR>
     * @@param l_strQueryString - ��������������
     * @@param l_queryContainers - ���������f�[�^�R���e�i
     * @@param l_strSortCond - �\�[�g����
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41C7B20800BF
     */
    protected boolean isUnnecessaryExplanation(
        String l_strQueryString, 
        Object[] l_queryContainers, 
        String l_strSortCond) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "isUnnecessaryExplanation(" +
            "String l_strQueryString, Object[] l_queryContainers, " +
            "String l_strSortCond)";
        log.entering(STR_METHOD_NAME);
        
        if (l_queryContainers == null || l_queryContainers.length == 0)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B  
        //[doFindAllQuery()�ɃZ�b�g����p�����[�^]  
        //�@@arg0�F�@@FX�����s�v���������Ǘ�Row.TYPE  
        //�@@arg1�F�@@����.��������������  
        //�@@arg2�F�@@����.�\�[�g����  
        //�@@arg3�F�@@null  
        //�@@arg4�F�@@����.���������f�[�^�R���e�i  
        //�������ʂ��擾�ł��Ȃ������ꍇ�Afalse��ԋp����B  

        List l_lisRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            l_lisRows = l_queryProcessor.doFindAllQuery(
                FxUnnecessaryExplanationRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null, 
                l_queryContainers);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�g�����U�N�V���������̗�O", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�g�����U�N�V���������̗�O", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�������ʂ��擾�ł��Ȃ������ꍇ�Afalse��ԋp����B
        if (l_lisRows == null || l_lisRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //�Q�j�������ʂ��擾�o�����ꍇ�Atrue��ԋp����B 
        log.exiting(STR_METHOD_NAME);        
        return true;
    }
    
    /**
     * (create�Í���������) <BR>
     * �Í���������𐶐�����B <BR>
     * <BR>
     * �P�j����.�閧���A����.�|�X�g�p�����O�f�[�^���g�p���ĈÍ���������𐶐�����B�i*1�j <BR>
     * <BR>
     * �Q�j�P�j�Ő��������Í����������ԋp����B <BR>
     * <BR>
     * �i*1�jDES���g�p<BR>
     * <BR>
     * @@param l_strSecretKey - �閧��
     * @@param l_strPostCreateData - �|�X�g�p�����O�f�[�^     
     * @@return String
     * @@roseuid 41C7B20800BF
     */
    protected String creatEncryptString(
        String l_strSecretKey, String l_strPostCreateData)           
    {
        String STR_METHOD_NAME = "creatEncryptString(" +
            "String l_strSecretKey, String l_strPostCreatData)";
        
        log.entering(STR_METHOD_NAME);        
        
        //�P�j����.�閧���A����.�|�X�g�p�����O�f�[�^���g�p���ĈÍ���������𐶐�����B�i*1�j 
        //�i*1�jDES���g�p
        String l_strEncryptSring = this.encrypt(l_strPostCreateData, l_strSecretKey);
      
        //�Q�j�P�j�Ő��������Í����������ԋp����B
        log.exiting(STR_METHOD_NAME);  
        return l_strEncryptSring;
    }
    
    /**
     * (create�閧��) <BR>
     * �閧���i8���j�������_���ɐ������A�ԋp����B<BR>
     *     
     * @@return String
     * @@roseuid 41C7B20800BF
     */
    protected String creatSecretKey()
    {
        String STR_METHOD_NAME = "creatSecretKey()";
        log.entering(STR_METHOD_NAME);        
        
        //�閧���i8���j�������_���ɐ������A�ԋp����B
        String l_strSecretKey = this.generateRandom(8) + "";
      
        log.exiting(STR_METHOD_NAME);
        
        return l_strSecretKey;
    }
    
    /**
     * (create�n�b�V���l) <BR>
     * �Í���������Ɣ閧�����g�p���ăn�b�V���l�𐶐����A�ԋp����B <BR>
     * <BR>
     * �P�j�n�b�V�����ڂ̘A��������̍쐬  <BR>
     * <BR>
     * �u�L�[��=�l�v���Z�b�g�ɂ���������̔z����ȉ��̏��ō쐬����B <BR>
     * <BR>
     * encryptedData=����.�Í���������  <BR>
     * secretKey=����.�閧�� <BR>
     * <BR>
     * ���L�[���͓d��Format�̃L�[�����g�p�B <BR>
     * ���p�����[�^�Ԃ́A'&'���Z�b�g����B  <BR>
     * <BR>
     * �Q�j�n�b�V���l�̐���  <BR>
     * <BR>
     * WEB3StringTypeUtility.createHashValue()��p���ăn�b�V���l�𐶐�����B <BR> 
     * [����]  <BR>
     * �v�Z�����F�@@"MD5"  <BR>
     * �v�Z�ΏہF�@@�P�j�ɂč쐬����������z��  <BR>
     * <BR>
     * �R�j�@@���������n�b�V���l��ԋp����B <BR>
     * <BR>
     * @@param l_strEncryptString - �Í��������� 
     * @@param l_strSecretKey - �閧��
     * @@return String
     * @@roseuid 41C7B20800BF
     */
    protected String creatHashValue(
        String l_strEncryptString, 
        String l_strSecretKey)
    {
        String STR_METHOD_NAME = 
            "creatHashValue(String l_strEncryptString, String l_strSecretKey)";
        log.entering(STR_METHOD_NAME);        
        
        //�P�j�n�b�V�����ڂ̘A��������̍쐬  
        //�u�L�[��=�l�v���Z�b�g�ɂ���������̔z����ȉ��̏��ō쐬����B 
        //encryptedData=����.�Í���������  
        //secretKey=����.�閧�� 
        //���L�[���͓d��Format�̃L�[�����g�p�B 
        //���p�����[�^�Ԃ́A'&'���Z�b�g����B 
        List l_lisWhere = new Vector();
        
        //encryptedData=����.�Í���������
        l_lisWhere.add(WEB3GftTelegramFormatDef.encryptedData + "=" + 
            ((l_strEncryptString == null) ? "" : l_strEncryptString));
        
        //secretKey=����.�閧�� 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.secretKey + "=" + 
            ((l_strSecretKey == null) ? "" : l_strSecretKey));        

        //�Q�j�n�b�V���l�̐���  
        //WEB3StringTypeUtility.createHashValue()��p���ăn�b�V���l�𐶐�����B  
        //[����]  
        //�v�Z�����F�@@"MD5"  
        //�v�Z�ΏہF�@@�P�j�ɂč쐬����������z�� 
        String[] l_strAlgorithmObj = new String[l_lisWhere.size()];
        l_lisWhere.toArray(l_strAlgorithmObj);
        
        String l_strHashValue = 
            WEB3StringTypeUtility.createHashValue(
                WEB3AioHashAlgorithmDef.MD5, l_strAlgorithmObj); 
      
        //�R�j�@@���������n�b�V���l��ԋp����B 
        log.exiting(STR_METHOD_NAME);
        
        return l_strHashValue;
    }
    
    /**
     * (create�|�X�g�p�����O�f�[�^) <BR>
     * �|�X�g�p�����O�f�[�^�𐶐����A�ԋp����B <BR>
     * <BR>
     * �P�j����.FX���O�C��ID�i9���j�A�������ԁiYYYYMMDDHHMM�j�i*1�j�A<BR>
     *  ����.������A�����|�X�g�p�����O�f�[�^�𐶐�����B <BR>
     * <BR>
     * �Q�j�P�j�Ő��������|�X�g�p�����O�f�[�^��ԋp����B <BR>
     * <BR>
     * �i*1�j���ݓ��t����擾<BR>
     * <BR>
     * @@param l_strFxLoginId - FX���O�C��ID
     * @@param l_strRandom - ����
     * @@return String
     * @@roseuid 41C7B20800BF
     */
    protected String createPostCreateData(String l_strFxLoginId, String l_strRandom)
    {
        String STR_METHOD_NAME = "createPostCreateData(" +
                "String l_strFxLoginId, String l_strRandom)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strFxLoginId.length() != 9)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j����.FX���O�C��ID�i9���j�A�������ԁiYYYYMMDDHHMM�j�i*1�j�A����.����
        //   ��A�����|�X�g�p�����O�f�[�^�𐶐�����B        
        //�i*1�j���ݓ��t����擾

        String l_strOperationTime = WEB3DateUtility.formatDate(
            GtlUtils.getSystemTimestamp(), "yyyyMMddHHmm");
        
        String l_strPostCreateData = 
            l_strFxLoginId.substring(0, 9) + l_strOperationTime + l_strRandom;
      
        //�Q�j�P�j�Ő��������|�X�g�p�����O�f�[�^��ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return l_strPostCreateData;
    }
    
    /**
     * (create����) <BR>
     * �����i4���j�𐶐����A�ԋp����B<BR>
     * <BR>
     * @@return String
     * @@roseuid 41C7B20800BF
     */
    protected String createRandom()
    {
        String STR_METHOD_NAME = "createRandom()";
        log.entering(STR_METHOD_NAME);        
        
        //�����i4���j�𐶐����A�ԋp����B
        String l_strRandom = this.generateRandom(4) + "";
        log.exiting(STR_METHOD_NAME);
        
        return l_strRandom;
    }
    
    /**
     * (create��������������) <BR>
     * �����s�v���������Ǘ��e�[�u������f�[�^���擾����ۂ̏����𐶐����A�ԋp����B <BR>
     * <BR>
     * �P�j��̕�����𐶐�����  <BR>
     * <BR>
     * �Q�j�،���ЃR�[�h  <BR>
     * <BR>
     * " institution_code = ?"���P�j�̕�����ɒǉ�����B  <BR>
     * <BR>
     * �R�j���X�R�[�h  <BR>
     * <BR>
     * " and branch_code = ?"���P�j�̕�����ɒǉ�����B  <BR>
     * <BR>
     * �S�j�ڋq�R�[�h <BR>
     * <BR>
     * " and account_code = ?"���P�j�̕�����ɒǉ�����B  <BR>
     * <BR>
     * �T�j�L���t���O <BR>
     * <BR>
     * " and fx_valid_flag = ?"���P�j�̕�����ɒǉ�����B  <BR>
     * <BR>
     * �U�j�쐬���t <BR>
     * ����.�����s�v�����L������ != null�̏ꍇ�A <BR>
     * " and created_timestamp > ?"���P�j�̕�����ɒǉ�����B  <BR>
     * <BR>
     * �V�j�������ԋp����<BR>
     * <BR>
     * @@param l_strNoExplainAgreeValidTerm - �����s�v�����L������
     * @@return String
     * @@roseuid 41C7B20800BF
     */
    protected String createQueryString(String l_strNoExplainAgreeValidTerm)
    {
        String STR_METHOD_NAME = 
            "createQueryString(String l_strNoExplainAgreeValidTerm)";
        log.entering(STR_METHOD_NAME);        
        
        //�P�j��̕�����𐶐����� 
        StringBuffer l_strBuffer = new StringBuffer();
        
        //�Q�j�،���ЃR�[�h 
        //" institution_code = ?"���P�j�̕�����ɒǉ�����B 
        l_strBuffer.append(" institution_code = ?");
        
        //�R�j���X�R�[�h  
        //" and branch_code = ?"���P�j�̕�����ɒǉ�����B  
        l_strBuffer.append(" and branch_code = ?");
      
        //�S�j�ڋq�R�[�h 
        //" and account_code = ?"���P�j�̕�����ɒǉ�����B  
        l_strBuffer.append(" and account_code = ?");
        
        //�T�j�L���t���O 
        //" and fx_valid_flag = ?"���P�j�̕�����ɒǉ�����B  
        l_strBuffer.append(" and fx_valid_flag = ?");
        
        //�U�j�쐬���t 
        //����.�����s�v�����L������ != null�̏ꍇ�A 
        //" and created_timestamp > ?"���P�j�̕�����ɒǉ�����B  
        if (l_strNoExplainAgreeValidTerm != null)
        {
            l_strBuffer.append(" and to_char(created_timestamp, 'YYYYMMDDhh24miss') > ?");
        }
        
        //�V�j�������ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_strBuffer.toString();
    }
    
    /**
     * (create���������f�[�^�R���e�i) <BR>
     * FX�����s�v���������Ǘ��e�[�u������f�[�^���擾����ۂ̏����̃f�[�^�R���e�i�𐶐����A�ԋp����B <BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B  <BR>
     * <BR>
     * �Q�j�،���ЃR�[�h  <BR>
     * <BR>
     * ����.�،���ЃR�[�h���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h  <BR>
     * <BR>
     * ����.���X�R�[�h���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �S�j�ڋq�R�[�h <BR>
     * <BR>
     * ����.�ڋq�R�[�h���P�j��List�ɒǉ�����B  <BR>
     * <BR>
     * �T�j�L���t���O <BR>
     * <BR>
     * �h0�h���P�j��List�ɒǉ�����B <BR>
     * <BR>
     * �U�j�쐬���t <BR>
     * ����.�����s�v�����L������ != null�̏ꍇ�A <BR>
     * ���ݓ��t - X(*1)�������P�j��List�ɒǉ�����B�iYYYYMMDDHH24MISS�j<BR> 
     * <BR>
     * �V�jList����z����擾���āA�ԋp����B  <BR>
     * <BR>
     * (*1)����.�����s�v�����L������<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strNoExplainAgreeValidTerm - �����s�v�����L������
     * @@return Object[]
     * @@roseuid 41C7B20800BF
     */
    protected Object[] createQueryContainer(
        String l_strInstitutionCode, 
        String l_strBranchCode,
        String l_strAccountCode, 
        String l_strNoExplainAgreeValidTerm)
    {
        String STR_METHOD_NAME = "createQueryString(" +
            "String l_strInstitutionCode, String l_strBranchCode," +
            "String l_strAccountCode, String l_strNoExplainAgreeValidTerm)";
        log.entering(STR_METHOD_NAME);        
        
        //�P�j���ArrayList�𐶐�����B
        List l_lisValue = new ArrayList();
        
        //�Q�j�،���ЃR�[�h 
        //����.�،���ЃR�[�h���P�j��List�ɒǉ�����B 
        l_lisValue.add(l_strInstitutionCode);
      
        //�R�j���X�R�[�h  
        //����.���X�R�[�h���P�j��List�ɒǉ�����B 
        l_lisValue.add(l_strBranchCode);
        
        //�S�j�ڋq�R�[�h 
        //����.�ڋq�R�[�h���P�j��List�ɒǉ�����B  
        l_lisValue.add(l_strAccountCode);

        //�T�j�L���t���O 
        //�h0�h���P�j��List�ɒǉ�����B 
        l_lisValue.add("0");
        
        //�U�j�쐬���t 
        //����.�����s�v�����L������ != null�̏ꍇ�A 
        //���ݓ��t - X(*1)�������P�j��List�ɒǉ�����B�iYYYYMMDDHH24MISS�j 
        //(*1)����.�����s�v�����L������

        if (l_strNoExplainAgreeValidTerm != null)
        {            
            String l_strBeforeYM = 
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMM");
            
            int l_intMonthDate = 
                Integer.parseInt(l_strBeforeYM.substring(0, 4)) * 12 + 
                Integer.parseInt(l_strBeforeYM.substring(4, 6)) - 
                Integer.parseInt(l_strNoExplainAgreeValidTerm);
            
            String l_strAfterYM = 
                WEB3StringTypeUtility.formatNumber((int)(l_intMonthDate / 12)) + 
                WEB3StringTypeUtility.formatNumber(l_intMonthDate % 12, 2);
            
            String l_strSystemAll = 
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMddHHmmss");
            
            String l_strHMS = l_strSystemAll.substring(6, 14);
            
            String l_strCreatedTime = l_strAfterYM + l_strHMS;            
                       
            l_lisValue.add(l_strCreatedTime);
        }
        //�V�jList����z����擾���āA�ԋp����B
        String[] l_strValue = new String[l_lisValue.size()];
        l_lisValue.toArray(l_strValue);
        
        for (int i = 0; i < l_strValue.length; i++)
        {
            log.debug("�����̃f�[�^�R���e�i = " + l_strValue[i]);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strValue;
    }
    
    /**
     * (create�\�[�g����) <BR>
     * �\�[�g�������쐬���A�ԋp����B <BR>
     * <BR>
     * �P�j�e�[�u���񕨗������A�ȉ��̃\�[�g������\���\�[�g������������쐬����B<BR>  
     * <BR>
     * FX�����s�v���������Ǘ�.����ԍ��@@�~��  <BR>
     * <BR>
     * �Q�j�쐬�����\�[�g�����������ԋp����B <BR>
     * <BR>
     * @@return String
     * @@roseuid 41C7B20800BF
     */
    protected String createSortCond()
    {
        String STR_METHOD_NAME = "createSortCond()";
        log.entering(STR_METHOD_NAME);        
        
        String l_strSort = new String();
        //�P�j�e�[�u���񕨗������A�ȉ��̃\�[�g������\���\�[�g������������쐬����B  
        //FX�����s�v���������Ǘ�.����ԍ��@@�~��  
        l_strSort = " fx_serial_no desc";
        
        //�Q�j�쐬�����\�[�g�����������ԋp����B        
        log.exiting(STR_METHOD_NAME);        
        return l_strSort;
    }
    
    /**
     * (insert���M�d��) <BR>
     * �\�[�g�������쐬���A�ԋp����B <BR>
     * <BR>
     * ���M�d�����V���O���T�C���I���ۑ��e�[�u����insert����B <BR>
     * <BR>
     * �}������s�̓��e�Ɋւ��ẮA���L���Q�ƁB  <BR>
     * �y��Trade�z�⑫����.DB�X�V  <BR>
     * �u�V���O���T�C���I���f�[�^�ۑ��e�[�u��.xls�v <BR>
     * <BR>
     * @@param l_subAccount - �⏕����
     * @@param l_strPostCreateData - �|�X�g�p�����O�f�[�^
     * @@param l_strEncryptString - �Í���������
     * @@param l_strSecretKey - �閧��
     * @@param l_strHashValue - �n�b�V���l
     * @@throws WEB3BaseException
     * @@roseuid 41C7B20800BF
     */
    protected void insertSsoMessage(
        SubAccount l_subAccount, 
        String l_strPostCreateData, 
        String l_strEncryptString, 
        String l_strSecretKey, 
        String l_strHashValue)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "insertSsoMessage(" +
            "SubAccount, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        SsoMessageParams l_ssoMessageparams = new SsoMessageParams();
        
        //�،���ЃR�[�h = ����.�⏕����.�،���ЃR�[�h
        l_ssoMessageparams.setInstitutionCode(
            l_subAccount.getInstitution().getInstitutionCode());
        
        //���X�R�[�h = ����.�⏕����.���X�R�[�h
        l_ssoMessageparams.setBranchCode(
            l_subAccount.getMainAccount().getBranch().getBranchCode());
        
        //�ڋq�R�[�h = ����.�⏕����.�ڋq�R�[�h
        l_ssoMessageparams.setAccountCode(
            l_subAccount.getMainAccount().getAccountCode());
        
        //FX���O�C��ID = ����.�|�X�g�p�����O�f�[�^��1���ڂ���9����
        l_ssoMessageparams.setFxLoginId(
            Long.parseLong(l_strPostCreateData.substring(0, 9)));
        
        //���� = ����.�|�X�g�p�����O�f�[�^��22���ڂ���25����
        l_ssoMessageparams.setRandom(
            l_strPostCreateData.substring(21, 25));
        
        //�������� = ����.�|�X�g�p�����O�f�[�^��10���ځ`21���ځiYYYYMMDDHH24MI�j
        l_ssoMessageparams.setOperationTime(
            l_strPostCreateData.substring(9, 21));
        
        //�Í��������� = ����.�Í���������
        l_ssoMessageparams.setEncryptString(l_strEncryptString);
        
        //����.�閧�� = ����.�閧��
        l_ssoMessageparams.setSecretKey(l_strSecretKey);
        
        //�n�b�V���l = ����.�n�b�V���l
        l_ssoMessageparams.setHashKey(l_strHashValue);
        
        //�쐬���t = �V�X�e���^�C���X�^���v�iYYYYMMDDHH24MISS�j
        Timestamp l_timestamp = GtlUtils.getSystemTimestamp();
        
        l_ssoMessageparams.setCreatedTimestamp(l_timestamp);
        
        //�X�V���t = �V�X�e���^�C���X�^���v�iYYYYMMDDHH24MISS�j
        l_ssoMessageparams.setLastUpdatedTimestamp(l_timestamp);
        
        try
        {
            WEB3DataAccessUtility.insertRow(l_ssoMessageparams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In ���M�d�����V���O���T�C���I���ۑ��e�[�u����insert����", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In ���M�d�����V���O���T�C���I���ۑ��e�[�u����insert����", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        log.exiting(STR_METHOD_NAME);
    }    
    
    private long generateRandom(int digit)
    {
        double l_dblMax = Math.pow(10, digit);
        
        double l_lngMin = Math.pow(10, digit - 1);        
        
        double l_dblRandom = Math.random();
        
        long l_lngRandom = 
            (long)((l_dblMax - l_lngMin) * l_dblRandom + l_lngMin);
        
        return l_lngRandom;
    }
    
    /**
     * DES�ŕ�����̈Í������s���B<BR>
     *<BR>
     * @@param l_strPlane �Í���������������
     * @@param l_strKeyData �閧���f�[�^
     * @@return �Í�������������
     */
    private String encrypt(String l_strPlane, String l_strKeyData)
    {
        final String STR_METHOD_NAME = "encrypt(String, String)";
        log.entering(STR_METHOD_NAME);

        final String TRANSFORMATION = "DES";

        //�閧��
        SecretKeySpec l_key;
        byte[] l_btDesKeyData = l_strKeyData.getBytes();
        //�Í����̋@@�\��񋟂���I�u�W�F�N�g
        Cipher l_cipher;
        byte[] l_btArray = null;

        l_key = new SecretKeySpec(l_btDesKeyData, TRANSFORMATION);
        try
        {
            l_cipher = Cipher.getInstance(TRANSFORMATION);

            l_cipher.init(Cipher.ENCRYPT_MODE, l_key);
            // �Í������s��
            l_btArray = l_cipher.doFinal(l_strPlane.getBytes());
        }
        catch (Exception l_exp)
        {
            throw new RuntimeSystemException(l_exp.getMessage(), l_exp);
        }

        BASE64Encoder l_encoder = new BASE64Encoder();

        log.exiting(STR_METHOD_NAME);
        return l_encoder.encode(l_btArray);
    }

}@
