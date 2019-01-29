head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConAccountOpenServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݃T�[�r�XImpl(WEB3FEqConAccountOpenServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/3/21 ���z (���u) �V�K�쐬   
                 : 2007/1/2  �����q (���u) �d�l�ύX�E���f��668
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FEqConTransferDataControlService;
import webbroker3.aio.data.UwgAccountOpenStatusParams;
import webbroker3.aio.message.WEB3FEqConAccountOpenAgreementRequest;
import webbroker3.aio.message.WEB3FEqConAccountOpenAgreementResponse;
import webbroker3.aio.message.WEB3FEqConAccountOpenCompleteRequest;
import webbroker3.aio.message.WEB3FEqConAccountOpenCompleteResponse;
import webbroker3.aio.message.WEB3FEqConAccountOpenConfirmRequest;
import webbroker3.aio.message.WEB3FEqConAccountOpenConfirmResponse;
import webbroker3.aio.message.WEB3FEqConAccountOpenInputRequest;
import webbroker3.aio.message.WEB3FEqConAccountOpenInputResponse;
import webbroker3.aio.message.WEB3FEqConAccountOpenQuestionInfo;
import webbroker3.aio.service.delegate.WEB3FEqConAccountOpenService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.gentrade.define.WEB3GentradeBatoFunctionDivDef;
import webbroker3.gentrade.define.WEB3GentradeBatoServiceRegServiceResultDef;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������J�݃T�[�r�XImpl)<BR>
 * �O�������J�݃T�[�r�X�����N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FEqConAccountOpenServiceImpl extends WEB3ClientRequestService 
    implements WEB3FEqConAccountOpenService 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConAccountOpenServiceImpl.class);  
    
    /**
     * @@roseuid 423562E500BB
     */
    public WEB3FEqConAccountOpenServiceImpl() 
    {
     
    }
    
    /**
     * �O�������J�ݏ������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̏������R�[������B<BR>
     * <BR>
     *   get������Ӊ��()<BR>
     *   get���͉��()<BR>
     *   validate�\��()<BR>
     *   submit�\��()
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E37DBE03B4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        
        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̏������R�[������B
        //get������Ӊ��()
        //get���͉��()
        //validate�\��()
        //submit�\��()
        if (l_request instanceof WEB3FEqConAccountOpenAgreementRequest)
        {
            l_response = 
                getAgreementScreen((WEB3FEqConAccountOpenAgreementRequest)l_request);   
        }
        else if (l_request instanceof WEB3FEqConAccountOpenInputRequest)
        {
            l_response =
                getInputScreen((WEB3FEqConAccountOpenInputRequest)l_request);
        }
        else if (l_request instanceof WEB3FEqConAccountOpenConfirmRequest)
        {
            l_response =
                validateApply((WEB3FEqConAccountOpenConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3FEqConAccountOpenCompleteRequest)
        {
            l_response =
                submitApply((WEB3FEqConAccountOpenCompleteRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get������Ӊ��)<BR>
     * ������Ӊ�ʎ擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������J�݁jget������Ӊ�ʁv �Q��
     * ------------------------------------------------
     * 1.4 validate�d�q�����{(�@@�\�敪 : String)
     *  �߂�l���h�����ӌڋq�h�̏ꍇ�A��O��throw����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01956<BR>
     * <BR>
     * ------------------------------------------------
     * 1.5 get����(String, String)
     *  �߂�l��null�̏ꍇ�A��O��thorw����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   SYSTEM_ERROR_80005<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConAccountOpenAgreementResponse
     * @@roseuid 41E38B5E026C
     */
    protected WEB3FEqConAccountOpenAgreementResponse getAgreementScreen(
        WEB3FEqConAccountOpenAgreementRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "getAgreementScreen(WEB3FEqConAccountOpenAgreementRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 get�⏕����(SubAccountTypeEnum)
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.2 validate����(SubAccount)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        //(1)FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //(2)validate
        l_orderManager.validateOrder(l_subAccount);
        
        //1.3 validate�O�������J�݉\(SubAccount)
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l
        l_orderManager.validateFeqConAccOpenPossible(l_subAccount);

        //validate���Z��(SubAccount)
        //�⏕�����F�@@get�⏕����()�̖߂�l
        l_orderManager.validateResident(l_subAccount);

        //1.4 validate�d�q�����{
        //[����] 
        //�@@�\�敪�F �h����񍐏����{�`�F�b�N�h
        WEB3GentradeBatoClientService l_batoClientService =
            (WEB3GentradeBatoClientService)Services.getService(
                WEB3GentradeBatoClientService.class);
       
        log.debug(" Real l_batoClientService = " +  l_batoClientService.getClass().getName());
        
        String l_strBato = 
            l_batoClientService.validateBato(
                WEB3GentradeBatoFunctionDivDef.BATO_TRAN_HIST_SERVICE);
        
        if (WEB3GentradeBatoServiceRegServiceResultDef.NOT_AGREEMENT.equals(l_strBato))
        {
            log.debug("validate�d�q�����{()�߂�l�������ӌڋq");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01956,
                this.getClass().getName() + "." + l_strMethodName,
                "validate�d�q�����{()�߂�l�������ӌڋq");            
        }
        
        //1.5 get����(String, String)
        //[����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        String l_strInstitutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        //���X�R�[�h�F�@@�⏕����.get����X.getBranchCode()
        String l_strBranchCode = 
            l_subAccount.getMainAccount().getBranch().getBranchCode();
        
        //get �O���U�֘A�g�f�[�^����T�[�r�XImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        QuestionParams[] l_questionParams = 
            l_conTransferDataControlService.getQuestion(
                l_strInstitutionCode, l_strBranchCode);
        
        //get����(String, String)�߂�l��null�̏ꍇ�A��O��thorw����
        if (l_questionParams == null)
        {
            log.debug("get����(String, String)�߂�l��null");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                "get����(String, String)�߂�l��null");            
        }
        
        //1.6 ArrayList()
        List l_list = new ArrayList();
        
        //1.7 get����()�̖߂�l�̎���Params�v�f�����Ƃ�Loop����
        
        //�O�������J�ݎ�����𐶐����A�v���p�e�B�Z�b�g���s���B
        WEB3FEqConAccountOpenQuestionInfo l_conAccountOpenQuestionInfo = null;
            
        
        for (int i = 0; i < l_questionParams.length; i++)
        {
            //1.7.1 �O�������J�ݎ�����I�u�W�F�N�g�𐶐�����
            l_conAccountOpenQuestionInfo = new WEB3FEqConAccountOpenQuestionInfo();

            //����ԍ��F����Params.����ԍ�
            l_conAccountOpenQuestionInfo.questionNumber = 
                l_questionParams[i].getQuestionNo();
            //������e�F����Params.������e
            l_conAccountOpenQuestionInfo.questionContent =
                l_questionParams[i].getQuestion();
            //����񓚂̐ݒ�͍s��Ȃ�           
            
            //1.7.2 ���X�g�ɊO�������J�ݎ�����I�u�W�F�N�g��ǉ�����
            l_list.add(l_conAccountOpenQuestionInfo);
        }
        
        //1.8 �z����擾����
        WEB3FEqConAccountOpenQuestionInfo[] l_conAccountOpenQuestionInfos =
            new WEB3FEqConAccountOpenQuestionInfo[l_list.size()];
        l_list.toArray(l_conAccountOpenQuestionInfos);
        
        //1.9 createResponse()
        WEB3FEqConAccountOpenAgreementResponse l_response = 
            (WEB3FEqConAccountOpenAgreementResponse)l_request.createResponse();
        
        //1.10 ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //(*1)�⏕����.getMainAccount()�ɂĎ擾
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //���X�|���X.�ڋq���F�ڋq(*1).get�ڋq�\����()�̖߂�l
        l_response.accountName = l_mainAccount.getDisplayAccountName();
        log.debug("���X�|���X.�ڋq���F" + l_response.accountName);
        
        //���X�|���X.������ꗗ�F�i*��L�ŕҏW�����O�������J�ݎ�����̔z��j
        l_response.questionInfoList = l_conAccountOpenQuestionInfos;

        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʎ擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������J�݁jget���͉�ʁv �Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConAccountOpenInputResponse
     * @@roseuid 421ADE7F03B2
     */
    protected WEB3FEqConAccountOpenInputResponse getInputScreen(
        WEB3FEqConAccountOpenInputRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "getInputScreen(WEB3FEqConAccountOpenInputRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 get�⏕����(SubAccountTypeEnum)
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.2 validate����(SubAccount)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        //(1)FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //(2)validate
        l_orderManager.validateOrder(l_subAccount);
        
        //1.3 validate�O�������J�݉\(SubAccount)
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l
        l_orderManager.validateFeqConAccOpenPossible(l_subAccount);

        //validate���Z��(SubAccount)
        //�⏕�����F�@@get�⏕����()�̖߂�l
        l_orderManager.validateResident(l_subAccount);

        //1.4 validate�O�������J�ݎ���(�O�������J�ݎ�����[])
        //[����] 
        //������ꗗ�F ���N�G�X�g�f�[�^.������ꗗ
        
        //get �O���U�֘A�g�f�[�^����T�[�r�XImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        l_conTransferDataControlService.validateFeqAccountOpenQuestion(
            l_request.questionInfoList);
        
        //1.5 createResponse()
        WEB3FEqConAccountOpenInputResponse l_response = 
            (WEB3FEqConAccountOpenInputResponse)l_request.createResponse();
        
        //1.6 �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //���X�|���X.���[���A�h���X = �ڋqParams(*1).email�A�h���X
        //(*1)�⏕����.getMainAccount().getDataSourceObject()�ɂĎ擾
        l_response.mailAddress = 
            ((MainAccountParams)l_subAccount.getMainAccount().getDataSourceObject()).getEmailAddress();
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (validate�\��)<BR>
     * �\���̊m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������J�݁jvalidate�\���v �Q��
     * <BR>
     * ------------------------------------------------
     * 1.3 validate�O�������J�݉\(SubAccount)
     * (*)�ȉ��̏����̂����ꂩ�Ɉ�v����ꍇ�A��O���X���[����B
     * ���N�G�X�g�f�[�^.�O���p�Ïؔԍ� == null
     * ���N�G�X�g�f�[�^.�O���p�Ïؔԍ��i�m�F�p�j == null
     * ���N�G�X�g�f�[�^.�O���p�Ïؔԍ� != ���N�G�X�g�f�[�^.�O���p�Ïؔԍ��i�m�F�p�j
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01963<BR>
     * <BR>
     * ------------------------------------------------
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConAccountOpenConfirmRespons
     * @@roseuid 41E38B5E02AA
     */
    protected WEB3FEqConAccountOpenConfirmResponse validateApply(
        WEB3FEqConAccountOpenConfirmRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "validateApply(WEB3FEqConAccountOpenConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 get�⏕����(SubAccountTypeEnum)
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.2 validate����(SubAccount)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        //(1)FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //(2)validate
        l_orderManager.validateOrder(l_subAccount);
        
        //1.3 validate�O�������J�݉\(SubAccount)
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l
        l_orderManager.validateFeqConAccOpenPossible(l_subAccount);

        //validate���Z��(SubAccount)
        //�⏕�����F�@@get�⏕����()�̖߂�l
        l_orderManager.validateResident(l_subAccount);

        //(*)�ȉ��̏����̂����ꂩ�Ɉ�v����ꍇ�A��O���X���[����B
        //���N�G�X�g�f�[�^.�O���p�Ïؔԍ� == null
        //���N�G�X�g�f�[�^.�O���p�Ïؔԍ��i�m�F�p�j == null
        //���N�G�X�g�f�[�^.�O���p�Ïؔԍ� != ���N�G�X�g�f�[�^.�O���p�Ïؔԍ��i�m�F�p�j
        if ((l_request.feqPassword1 == null) ||
            (l_request.feqPassword2 == null) ||
            (!l_request.feqPassword1.equals(l_request.feqPassword2)))
        {
            log.debug(
                "���N�G�X�g�f�[�^.�O���p�Ïؔԍ� == null" +
                "���N�G�X�g�f�[�^.�O���p�Ïؔԍ��i�m�F�p�j== null" +
                "���N�G�X�g�f�[�^.�O���p�Ïؔԍ� != ���N�G�X�g�f�[�^.�O���p�Ïؔԍ��i�m�F�p�j");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01963,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�O���p�Ïؔԍ� == null" +
                "���N�G�X�g�f�[�^.�O���p�Ïؔԍ��i�m�F�p�j== null" +
                "���N�G�X�g�f�[�^.�O���p�Ïؔԍ� != ���N�G�X�g�f�[�^.�O���p�Ïؔԍ��i�m�F�p�j");
        }
        
        //1.4 createResponse()
        WEB3FEqConAccountOpenConfirmResponse l_response = 
            (WEB3FEqConAccountOpenConfirmResponse)l_request.createResponse();
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (submit�\��)<BR>
     * �\���̊����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������J�݁jsubmit�\���v �Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConAccountOpenCompleteResponse
     * @@roseuid 41E38B5E02BA
     */
    protected WEB3FEqConAccountOpenCompleteResponse submitApply(
        WEB3FEqConAccountOpenCompleteRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "submitApply(WEB3FEqConAccountOpenCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 get�⏕����(SubAccountTypeEnum)
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.2 validate����(SubAccount)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        //(1)FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //(2)validate
        l_orderManager.validateOrder(l_subAccount);
        
        //1.3 validate�O�������J�݉\(SubAccount)
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l
        l_orderManager.validateFeqConAccOpenPossible(l_subAccount);

        //validate���Z��(SubAccount)
        //�⏕�����F�@@get�⏕����()�̖߂�l
        l_orderManager.validateResident(l_subAccount);

        //1.4 validate�O�������J�ݎ���(�O�������J�ݎ�����[])
        //[����] 
        //������ꗗ�F ���N�G�X�g�f�[�^.������ꗗ
        
        //get �O���U�֘A�g�f�[�^����T�[�r�XImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        l_conTransferDataControlService.validateFeqAccountOpenQuestion(
            l_request.questionInfoList);
        
        //1.5 get�㗝���͎�()
        Trader l_trader = this.getTrader();
        
        //1.6 validate����p�X���[�h(Trader, SubAccount, String)
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        //(1)
        WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
        //(2)
        OrderValidationResult result = l_orderValidator.validateTradingPassword(l_trader, l_subAccount, l_request.password);
        if(result.getProcessingResult().getErrorInfo()!=null){
                throw new WEB3BusinessLayerException(
                      result.getProcessingResult().getErrorInfo(),
                      this.getClass().getName() + "." + l_strMethodName,
                      result.getProcessingResult().getErrorInfo().getErrorMessage());
        }
        //1.7 get�V�K���ʃR�[�h(String, String, ProductTypeEnum)
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode() 
        //�����^�C�v�F 5�i�����j
        //(1)get �������ʃR�[�h�̔ԃT�[�r�X 
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        //(2)
        String l_strNewNumber = 
            l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.CASH);
        log.debug("get�V�K���ʃR�[�h() = " + l_strNewNumber);
        
        //1.8 insertUWG�����J�ݏ�(�ڋq, String)
        //[����] 
        //�ڋq�F �⏕����.getMainAccount()�̖߂�l 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�O���p�Ïؔԍ�
        //���ʃR�[�h�F get�V�K���ʃR�[�h()�̖߂�l
        l_conTransferDataControlService.insertUwgAccountOpenStatus(
            l_subAccount.getMainAccount(),
            l_request.feqPassword1,
            l_strNewNumber);
        
        //1.9 insert�����(String, String, String, �O�������J�ݎ�����[])
        //[����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F �⏕����.get����X().getBranchCode() 
        //���ʃR�[�h�F get�V�K���ʃR�[�h()�̖߂�l 
        //������ꗗ�F ���N�G�X�g�f�[�^.������ꗗ
        l_conTransferDataControlService.insertQuestionAnswer(
            l_subAccount.getInstitution().getInstitutionCode(),
            l_subAccount.getMainAccount().getBranch().getBranchCode(),
            l_strNewNumber,
            l_request.questionInfoList);
        
        //1.10 getUWG�����J�ݏ�(String, String, String)
        //[����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F �⏕����.get����X().getBranchCode() 
        //���ʃR�[�h�F get�V�K���ʃR�[�h()�̖߂�l
        UwgAccountOpenStatusParams l_uwgAccountOpenStatusParams = 
            l_conTransferDataControlService.getUwgAccountOpenStatus(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_strNewNumber);
        
        //1.11 createResponse()
        WEB3FEqConAccountOpenCompleteResponse l_response =
            (WEB3FEqConAccountOpenCompleteResponse)l_request.createResponse();
        
        //1.12 ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //��t�ԍ��F get�V�K���ʃR�[�h()�̖߂�l
        l_response.receptionId = l_strNewNumber;
        //��t�����F UWG�����J�ݏ�.�쐬���t
        l_response.receptionDate = l_uwgAccountOpenStatusParams.getCreatedTimestamp();
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
