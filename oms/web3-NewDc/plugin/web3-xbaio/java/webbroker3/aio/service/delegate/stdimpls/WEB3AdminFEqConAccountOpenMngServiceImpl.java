head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݊Ǘ��T�[�r�XImpl(WEB3AdminFEqConAccountOpenMngServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/3/21 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.aio.WEB3AdminFEqConAccountOpenMngListCsv;
import webbroker3.aio.WEB3FEqConTransferDataControlService;
import webbroker3.aio.data.UwgAccountOpenStatusParams;
import webbroker3.aio.define.WEB3AioFeqTransferDivMessageDef;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngListDownloadRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngListDownloadResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngListRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngListResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngSrcInputRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngSrcInputResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse;
import webbroker3.aio.message.WEB3FEqConAccountOpenApplyUnit;
import webbroker3.aio.message.WEB3FEqConAccountOpenQuestionInfo;
import webbroker3.aio.service.delegate.WEB3AdminFEqConAccountOpenMngService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.define.WEB3ForeignSecAccOpenDiv;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (�O�������J�݊Ǘ��T�[�r�XImpl)<BR>
 * �O�������J�݊Ǘ��T�[�r�X�����N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountOpenMngServiceImpl 
    implements WEB3AdminFEqConAccountOpenMngService 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountOpenMngServiceImpl.class);  
    
    /**
     * @@roseuid 423563EA0261
     */
    public WEB3AdminFEqConAccountOpenMngServiceImpl() 
    {
     
    }
    
    /**
     * �O�������J�݊Ǘ��������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *   get���͉��()<BR>
     *   get�ꗗ���()<BR>
     *   get�X�e�[�^�X�X�V���()<BR>
     *   validate�X�e�[�^�X�X�V()<BR>
     *   submit�X�e�[�^�X�X�V()<BR>
     *   get�_�E�����[�h�t�@@�C��()<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B1E700F5
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
        
        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B 
        //get���͉��() 
        //get�ꗗ���() 
        //get�X�e�[�^�X�X�V���() 
        //validate�X�e�[�^�X�X�V() 
        //submit�X�e�[�^�X�X�V() 
        if (l_request instanceof WEB3AdminFEqConAccountOpenMngSrcInputRequest)
        {
            l_response = 
                getInputScreen((WEB3AdminFEqConAccountOpenMngSrcInputRequest)l_request);   
        }
        else if (l_request instanceof WEB3AdminFEqConAccountOpenMngListRequest)
        {
            l_response =
                getListScreen((WEB3AdminFEqConAccountOpenMngListRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest)
        {
            l_response =
                getStatusUpdScreen((WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest)
        {
            l_response =
                validateStatusUpd((WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest)
        {
            l_response =
                submitStatusUpd((WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFEqConAccountOpenMngListDownloadRequest)
        {
            l_response =
                getDownloadFile((WEB3AdminFEqConAccountOpenMngListDownloadRequest)l_request);
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
     * (get���͉��)<BR>
     * ���͉�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������J�݊Ǘ��jget���͉�ʁv �Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountOpenMngSrcInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B370028B
     */
    protected WEB3AdminFEqConAccountOpenMngSrcInputResponse getInputScreen(
        WEB3AdminFEqConAccountOpenMngSrcInputRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "getInputScreen(WEB3AdminFEqConAccountOpenMngSrcInputRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����()
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�O�������U�֘A�g(�����Ǘ��E�����J�݊Ǘ�) 
        //is�X�V�F�@@false(�X�V�Ȃ�)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, false);
        
        //1.4 validate���X����(String[])
        //[����] 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5 createResponse()
        WEB3AdminFEqConAccountOpenMngSrcInputResponse l_response =
            (WEB3AdminFEqConAccountOpenMngSrcInputResponse)l_request.createResponse();
        
        //1.6 (*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //(*1)���ݎ��� 
        //ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TIMESTAMP_TAG)
        //�ɂĎ擾�������ݎ���
        Timestamp l_currentTime = GtlUtils.getSystemTimestamp();
        
        //���X�|���X�f�[�^.�\����(��) ���@@���ݎ���(*1)�̑O�c�Ɠ�
        Date l_datApplyDateFrom = 
            new WEB3GentradeBizDate(new Timestamp(l_currentTime.getTime())).roll(-1);                 
        l_response.applyDateFrom = WEB3DateUtility.formatDate(l_datApplyDateFrom, "yyyyMMdd");               
        log.debug("���X�|���X�f�[�^.�\����(��) �� " + l_response.applyDateFrom);
        
        //���X�|���X�f�[�^.�\����(��) ���@@���ݎ����̑O��
        Date l_datApplyDateTo = WEB3DateUtility.addDay(l_currentTime, -1);        
        l_response.applyDateTo = WEB3DateUtility.formatDate(l_datApplyDateTo, "yyyyMMdd");  
        log.debug("���X�|���X�f�[�^.�\����(��) �� " + l_response.applyDateTo);
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get�ꗗ���)<BR>
     * �ꗗ��ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������J�݊Ǘ��jget�ꗗ��ʁv �Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AdminFEqConAccountOpenMngListResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B37002AA
     */
    protected WEB3AdminFEqConAccountOpenMngListResponse getListScreen(
        WEB3AdminFEqConAccountOpenMngListRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "getListScreen(WEB3AdminFEqConAccountOpenMngListRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����()
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�O�������U�֘A�g(�����Ǘ��E�����J�݊Ǘ�) 
        //is�X�V�F�@@false(�X�V�Ȃ�)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, false);
        
        //1.4 get�،���ЃR�[�h()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.5 create��������������(String[], String, String, String)
        //[����] 
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h 
        //�X�e�[�^�X�敪�F�@@���N�G�X�g�f�[�^.�X�e�[�^�X�敪 
        //�\�����i���j�F�@@���N�G�X�g�f�[�^.�\�����i���j 
        //�\�����i���j�F�@@���N�G�X�g�f�[�^.�\�����i���j
        String l_strCondition = 
            this.createQueryString(
                l_request.branchCode,
                l_request.statusDiv,
                l_request.applyDateFrom,
                l_request.applyDateTo);
        log.debug("create�������������� = " + l_strCondition);
        
        //1.6 create���������f�[�^�R���e�i(String, String[], String, String, String)
        //[����] 
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h 
        //�X�e�[�^�X�敪�F�@@���N�G�X�g�f�[�^.�X�e�[�^�X�敪 
        //�\�����i���j�F�@@���N�G�X�g�f�[�^.�\�����i���j 
        //�\�����i���j�F�@@���N�G�X�g�f�[�^.�\�����i���j
        String[] l_strValue = 
            this.createQueryContainer(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.statusDiv,
                l_request.applyDateFrom,
                l_request.applyDateTo);
        
        //1.7 create�\�[�g����()
        String l_strSortCond = this.createSortCond();
        log.debug("create�\�[�g���� = " + l_strSortCond);
        
        //1.8 getUWG�����J�ݏ�(String, String[], String)
        //[����] 
        //��������������F�@@create��������������()�̖߂�l 
        //���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l 
        //�\�[�g�����F�@@create�\�[�g����()�̖߂�l
        
        //get �O���U�֘A�g�f�[�^����T�[�r�XImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        UwgAccountOpenStatusParams[] l_accountOpenStatusParams =
            l_conTransferDataControlService.getUwgAccountOpenStatus(
                l_strCondition,
                l_strValue,
                l_strSortCond);
        
        //1.9 ArrayList()
        List l_lisAccountOpenApplyUnit = new ArrayList();
        
        //1.10 getUWG�����J�ݏ�()�̖߂�l�̂����A
        //�\���Ώۍs(fromIndex�`toIndex)�̊ԁALoop���������{����
        //[�\���Ώۍs�ifromIndex�CtoIndex�j�̌v�Z]
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        
        WEB3PageIndexInfo l_pageIndexInfo = 
            new WEB3PageIndexInfo(
                l_accountOpenStatusParams,
                l_intPageIndex,
                l_intPageSize);
        
        //�w�肵�����R�[�h���擾(��{�݌v�ƈقȂ�A�u�y�[�W���߂��鏈���N���X�v�𗘗p�����̂�)
        l_accountOpenStatusParams = 
            (UwgAccountOpenStatusParams[])l_pageIndexInfo.getArrayReturned(
                UwgAccountOpenStatusParams.class); 
        
        //�g���A�J�E���g�}�l�[�W���擾����   
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        UwgAccountOpenStatusParams l_params = null;
        
        //1.10.5 �O�������J�ݐ\�����׃C���X�^���X�𐶐�����

        log.debug("l_accountOpenStatusParams.length = " + l_accountOpenStatusParams.length);
        WEB3FEqConAccountOpenApplyUnit l_accountOpenApplyUnit = null;
        
        for (int i = 0; i < l_accountOpenStatusParams.length; i++)
        {           
            l_params = l_accountOpenStatusParams[i];
            log.debug("UwgAccountOpenStatusParams = " + l_params);
            
            //1.10.1 get�����(String, String, String)
            //[����] 
            //�،���ЃR�[�h�F�@@UWG�����J�ݏ�Params.�،���ЃR�[�h 
            //���X�R�[�h�F�@@UWG�����J�ݏ�Params.���X�R�[�h 
            //���ʃR�[�h�F�@@UWG�����J�ݏ�Params.���ʃR�[�h
            QuestionAnswerParams[] l_questionAnswerParams =
                l_conTransferDataControlService.getQuestionAnswer(
                    l_params.getInstitutionCode(),
                    l_params.getBranchCode(),
                    l_params.getOrderRequestNumber());           
            
            //1.10.2 create�O�������J�ݎ�����ꗗ(�����Params[])
            //[����] 
            //����񓚈ꗗ�F�@@get�����()�̖߂�l
            WEB3FEqConAccountOpenQuestionInfo[] l_accountOpenQuestionInfo = 
                this.createFeqAccOpenQuestionInfoList(l_questionAnswerParams);
            
            // update start
            
            String l_strOperationDiv;
            String l_mainAccountName;
            boolean l_mainAccountFlg = true;
            try
            {
				//1.10.3 get�ڋq(String, String, String)
				//[����] 
				//�،���ЃR�[�h�F�@@UWG�����J�ݏ�Params.�،���ЃR�[�h 
				//���X�R�[�h�F�@@UWG�����J�ݏ�Params.���X�R�[�h 
				//�����R�[�h�F�@@UWG�����J�ݏ�Params.�ڋq�R�[�h
				WEB3GentradeMainAccount l_mainAccount = 
					l_accountManager.getMainAccount(
						l_params.getInstitutionCode(),
						l_params.getBranchCode(),
						l_params.getAccountCode()
						);
            
				//1.10.4 get�����敪(UWG�����J�ݏ�Params)
				//[����] 
				//UWG�����J�ݏ�Params�F�@@UWG�����J�ݏ�Params
				l_strOperationDiv = this.getOperationDiv(l_params);
				
				//�ڋq�����擾
				l_mainAccountName = l_mainAccount.getDisplayAccountName();
            }
            catch(WEB3BaseException l_ex)
            {
            	l_strOperationDiv = WEB3AioFeqTransferDivMessageDef.ACCEPT_RESULT_CODE_90000009;
            	l_mainAccountName = null;
            	l_mainAccountFlg = false;
            }
            
            //1.10.6 (*)�O�������J�ݐ\�����ׂɈȉ��̃v���p�e�B���Z�b�g����B
            l_accountOpenApplyUnit = new WEB3FEqConAccountOpenApplyUnit();
            
            //���ʃR�[�h  �� UWG�����J�ݏ�Params.���ʃR�[�h
            l_accountOpenApplyUnit.requestNumber = l_params.getOrderRequestNumber();
            log.debug("���ʃR�[�h  �� " + l_accountOpenApplyUnit.requestNumber);
            
            //���X�R�[�h  �� UWG�����J�ݏ�Params.���X�R�[�h
            l_accountOpenApplyUnit.branchCode = l_params.getBranchCode();
            log.debug("���X�R�[�h  �� " + l_accountOpenApplyUnit.branchCode);
            
            //�ڋq�R�[�h  �� UWG�����J�ݏ�Params.�ڋq�R�[�h�̏�6��
            l_accountOpenApplyUnit.accountCode = l_params.getAccountCode().substring(0, 6);
            
            //�ڋq��   �� get�ڋq()�̖߂�l.�ڋq��
            //get�ڋq()�ɂė�O�����������ꍇ�A�uNULL�v���Z�b�g
			l_accountOpenApplyUnit.accountName = l_mainAccountName;
            log.debug("�ڋq�� �� " + l_accountOpenApplyUnit.accountName);
            
            //�\������    �� UWG�����J�ݏ�Params.�쐬���t
            l_accountOpenApplyUnit.applyDate = l_params.getCreatedTimestamp();
            log.debug("�\������ �� " + l_accountOpenApplyUnit.applyDate);
            
            //�J�ݓ���      ���@@�i�ȉ��̂Ƃ���j
            //  UWG�����J�ݏ�Params.�����J�ݏ󋵋敪 == "�����J�݊���"�̏ꍇ�AUWG�����J�ݏ�Params.�X�V���t
            if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(
                    l_params.getAccountOpenStatusDiv()))
            {
                l_accountOpenApplyUnit.openDate = l_params.getLastUpdatedTimestamp();
            }
            //UWG�����J�ݏ�Params.�����J�ݏ󋵋敪 != "�����J�݊���"�̏ꍇ�Anull
            else
            {
                l_accountOpenApplyUnit.openDate = null;
            }
            log.debug("�J�ݓ��� �� " + l_accountOpenApplyUnit.openDate);

            //�X�e�[�^�X�敪 ��UWG�����J�ݏ�Params.�����J�ݏ󋵋敪
            l_accountOpenApplyUnit.statusDiv = l_params.getAccountOpenStatusDiv();
            log.debug("�X�e�[�^�X�敪 �� " + l_accountOpenApplyUnit.statusDiv);
            
            //����M�敪  �� UWG�����J�ݏ�Params.����M�敪
            l_accountOpenApplyUnit.sendRcvDiv = l_params.getSendRcvDiv();
            log.debug("����M�敪 �� " + l_accountOpenApplyUnit.sendRcvDiv);
            
            //���O(��)    ���@@UWG�����J�ݏ�Params.���O(��)
            l_accountOpenApplyUnit.familyName = l_params.getLastName();
            log.debug("���O(��) �� " + l_accountOpenApplyUnit.familyName);
            
            //���O(��)    ���@@UWG�����J�ݏ�Params.���O(��)
            l_accountOpenApplyUnit.name = l_params.getFirstName();
            log.debug("���O(��) �� " + l_accountOpenApplyUnit.name);
            
            //�O�������ԍ� ���@@UWG�����J�ݏ�Params.�O�����������ԍ�
            l_accountOpenApplyUnit.fstkAccountCode = l_params.getFeqAccountCode();
            log.debug("�O�������ԍ� �� " + l_accountOpenApplyUnit.fstkAccountCode);
            
            //���[���A�h���X  ���@@UWG�����J�ݏ�Params.���[���A�h���X
            l_accountOpenApplyUnit.mailAddress = l_params.getMailAddress();
            log.debug("���[���A�h���X �� " + l_accountOpenApplyUnit.mailAddress);
            
            //���l   ���@@get�����敪()�̖߂�l
            //get�ڋq()�ɂė�O�����������ꍇ�A�u90000009(��������)�v���Z�b�g
            l_accountOpenApplyUnit.biko = l_strOperationDiv;
            log.debug("���l �� " + l_accountOpenApplyUnit.biko);
            
            //������ꗗ ���@@create�O�������J�ݎ�����ꗗ()�̖߂�l
            l_accountOpenApplyUnit.questionInfoList = l_accountOpenQuestionInfo;
            
            //�X�V�\�t���O  ���i�ȉ��̂Ƃ���j
            //  UWG�����J�ݏ�Params.�����J�ݏ󋵋敪 == "�����J�ݒ�"�̏ꍇ�Atrue
            if (l_mainAccountFlg)
            {
				if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
						l_params.getAccountOpenStatusDiv()))
				{
					l_accountOpenApplyUnit.updateFlag = true;
				}
				//  UWG�����J�ݏ�Params.�����J�ݏ󋵋敪 != "�����J�ݒ�"�̏ꍇ�Afalse
				else
				{
					l_accountOpenApplyUnit.updateFlag = false;
				}
            }
            else
            {
				l_accountOpenApplyUnit.updateFlag = false;
            }
            
            log.debug("�X�V�\�t���O  �� " + l_accountOpenApplyUnit.updateFlag);

            //1.10.7 ArrayList�ɊO�������J�ݐ\�����ׂ�ǉ�����
            //[����] 
            //arg0�F�@@�O�������J�ݐ\�����׃I�u�W�F�N�g
            
            l_lisAccountOpenApplyUnit.add(l_accountOpenApplyUnit);
        }
        
        //1.11 �O�������J�ݐ\�����ׂ̔z��𐶐�����
        WEB3FEqConAccountOpenApplyUnit[] l_accountOpenApplyUnits = 
            new WEB3FEqConAccountOpenApplyUnit[l_lisAccountOpenApplyUnit.size()];
        
        l_lisAccountOpenApplyUnit.toArray(l_accountOpenApplyUnits);
        
        //1.12 createResponse()
        WEB3AdminFEqConAccountOpenMngListResponse l_response = 
            (WEB3AdminFEqConAccountOpenMngListResponse)l_request.createResponse();
        
        //1.13 (*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�O�������J�ݐ\�����׈ꗗ  ���@@toArray()�̖߂�l
        l_response.fstkAccountOpenApplyList = l_accountOpenApplyUnits;
        //�\���y�[�W�ԍ�           ���@@toIndex / ���N�G�X�g�f�[�^.�y�[�W���\���s��
        //                            ���v�Z���ʂ͏����_�ȉ���ʂ�؂�グ�������l�Ƃ���B
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        log.debug("�\���y�[�W�ԍ�  �� " + l_response.pageIndex);
        
        //�����R�[�h��             ���@@getUWG�����J�ݏ�()�̖߂�l�̗v�f��
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        log.debug("�����R�[�h��  �� " + l_response.totalRecords);
        
        //���y�[�W��               ���@@�����R�[�h�� / ���N�G�X�g�f�[�^.�y�[�W���\���s��
        //                           ���v�Z���ʂ͏����_�ȉ���ʂ�؂�グ�������l�Ƃ���B
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        log.debug("���y�[�W��  �� " + l_response.totalPages);
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �_�E�����[�h�t�@@�C���̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������J�݊Ǘ��jget�_�E�����[�h�t�@@�C���v �Q��
     * ------------------------------------------------
     * 1.8 getUWG�����J�ݏ�(String, String[], String)
     *  null���ԋp���ꂽ�ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01948<BR>
     * <BR>
     * ------------------------------------------------
     * 1.11 get���׍s��( )
     *  get���׍s��()�̖߂�l > ���N�G�X�g�f�[�^.�_�E�����[�h���� �̏ꍇ�A
     *  ��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01957<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountOpenMngListDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 41F8D68400A3
     */
    protected WEB3AdminFEqConAccountOpenMngListDownloadResponse getDownloadFile(
        WEB3AdminFEqConAccountOpenMngListDownloadRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "getDownloadFile(WEB3AdminFEqConAccountOpenMngListDownloadRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�O�������U�֘A�g(�����Ǘ��E�����J�݊Ǘ�) 
        //is�X�V�F�@@false(�X�V�Ȃ�)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, false);
        
        //1.4 get�،���ЃR�[�h()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.5 create��������������(String[], String, String, String)
        //[����] 
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h 
        //�X�e�[�^�X�敪�F�@@���N�G�X�g�f�[�^.�X�e�[�^�X�敪 
        //�\�����i���j�F�@@���N�G�X�g�f�[�^.�\�����i���j 
        //�\�����i���j�F�@@���N�G�X�g�f�[�^.�\�����i���j
        String l_strCondition = 
            this.createQueryString(
                l_request.branchCode,
                l_request.statusDiv,
                l_request.applyDateFrom,
                l_request.applyDateTo);
        log.debug("create�������������� = " + l_strCondition);
        
        //1.6 create���������f�[�^�R���e�i(String, String[], String, String, String)
        //[����] 
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h 
        //�X�e�[�^�X�敪�F�@@���N�G�X�g�f�[�^.�X�e�[�^�X�敪 
        //�\�����i���j�F�@@���N�G�X�g�f�[�^.�\�����i���j 
        //�\�����i���j�F�@@���N�G�X�g�f�[�^.�\�����i���j
        String[] l_strValue = 
            this.createQueryContainer(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.statusDiv,
                l_request.applyDateFrom,
                l_request.applyDateTo);
        
        //1.7 create�\�[�g����()
        String l_strSortCond = this.createSortCond();
        log.debug("create�\�[�g���� = " + l_strSortCond);
        
        //1.8 getUWG�����J�ݏ�(String, String[], String)
        //[����] 
        //��������������F�@@create��������������()�̖߂�l 
        //���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l 
        //�\�[�g�����F�@@create�\�[�g����()�̖߂�l
        
        //get �O���U�֘A�g�f�[�^����T�[�r�XImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        UwgAccountOpenStatusParams[] l_accountOpenStatusParams =
            l_conTransferDataControlService.getUwgAccountOpenStatus(
                l_strCondition,
                l_strValue,
                l_strSortCond);
        
        //getUWG�����J�ݏ�(String, String[], String)null���ԋp���ꂽ�ꍇ�A��O���X���[����B
        if (l_accountOpenStatusParams == null)
        {
            log.debug("getUWG�����J�ݏ�(String, String[], String)�߂�l��null");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01948,
                this.getClass().getName() + "." + l_strMethodName,
                "getUWG�����J�ݏ�(String, String[], String)�߂�l��null");            
        }
        
        //1.9 �O�������J�݊Ǘ��ꗗCSV�C���X�^���X�𐶐�����
        WEB3AdminFEqConAccountOpenMngListCsv l_accountOpenMngListCsv =
            new WEB3AdminFEqConAccountOpenMngListCsv();
        
        log.debug("l_accountOpenStatusParams.length = " + l_accountOpenStatusParams.length);
        
        //1.10 getUWG�����J�ݏ�()�̖߂�l�̊e�v�f�ɂ��āALoop���������{����
        for (int i = 0; i < l_accountOpenStatusParams.length; i++)
        {
            //1.10.1 add���׍s()
            int l_intRow = l_accountOpenMngListCsv.addRow();
            
            //1.10.2 set�،���ЃR�[�h(int, String)
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //�l�F UWG�����J�ݏ�Params.�،���ЃR�[�h 
            l_accountOpenMngListCsv.setInstitutionCode(
                l_intRow, l_accountOpenStatusParams[i].getInstitutionCode());
            
            //1.10.3 set���X�R�[�h(int, String)
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //�l�F UWG�����J�ݏ�Params.���X�R�[�h
            l_accountOpenMngListCsv.setBranchCode(
                l_intRow, l_accountOpenStatusParams[i].getBranchCode());
            
            //1.10.4 set�ڋq�R�[�h(int, String)
            //[����] 
            // �s�ԍ��F add���׍s()�̖߂�l 
            // �l�F UWG�����J�ݏ�Params.�ڋq�R�[�h 
            l_accountOpenMngListCsv.setAccountCode(
                l_intRow, l_accountOpenStatusParams[i].getAccountCode());
            
            //1.10.5 set���ʃR�[�h(int, String)
            //[����] 
            // �s�ԍ��F add���׍s()�̖߂�l 
            // �l�F UWG�����J�ݏ�Params.���ʃR�[�h 
            l_accountOpenMngListCsv.setRequestNumber(
                l_intRow, l_accountOpenStatusParams[i].getOrderRequestNumber());
            
            //1.10.6 set���O�i���j(int, String)
            //[����] 
            // �s�ԍ��F add���׍s()�̖߂�l 
            // �l�F UWG�����J�ݏ�Params.���O�i���j
            l_accountOpenMngListCsv.setFamilyName(
                l_intRow, l_accountOpenStatusParams[i].getLastName());
            
            //1.10.7 set���O�i���j(int, String)
            //[����] 
            // �s�ԍ��F add���׍s()�̖߂�l 
            // �l�F UWG�����J�ݏ�Params.���O�i���j
            l_accountOpenMngListCsv.setName(
                l_intRow, l_accountOpenStatusParams[i].getFirstName());

            //1.10.8 set���[���A�h���X(int, String)
            //[����] 
            // �s�ԍ��F add���׍s()�̖߂�l 
            // �l�F UWG�����J�ݏ�Params.���[���A�h���X 
            l_accountOpenMngListCsv.setMailAddress(
                l_intRow, l_accountOpenStatusParams[i].getMailAddress());
            
            //1.10.9 set�O�����������ԍ�(int, String)
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //�l�F UWG�����J�ݏ�Params.�O�����������ԍ�
            l_accountOpenMngListCsv.setFeqAccountCode(
                l_intRow, l_accountOpenStatusParams[i].getFeqAccountCode());
            
            //1.10.10 set�����J�ݏ󋵋敪(int, String)
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //�l�F UWG�����J�ݏ�Params.�����J�ݏ󋵋敪
            l_accountOpenMngListCsv.setAccountOpenStatusDiv(
                l_intRow, l_accountOpenStatusParams[i].getAccountOpenStatusDiv());
            
            //1.10.11 set����M�敪(int, String)
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //�l�F UWG�����J�ݏ�Params.����M�敪
            l_accountOpenMngListCsv.setSendRcvDiv(
                l_intRow, l_accountOpenStatusParams[i].getSendRcvDiv());
            
            //1.10.12 set��t���ʃR�[�h(int, String)
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //�l�F UWG�����J�ݏ�Params.��t���ʃR�[�h 
            l_accountOpenMngListCsv.setResultCode(
                l_intRow, l_accountOpenStatusParams[i].getResultCode());
            
            //1.10.13 set�G���[���R�R�[�h(int, String)
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //�l�F UWG�����J�ݏ�Params.�G���[���R�R�[�h 
            l_accountOpenMngListCsv.setErrorReasonCode(
                l_intRow, l_accountOpenStatusParams[i].getErrorReasonCode());
            
            //1.10.14 set�X�V�҃R�[�h(int, String)
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //�l�F UWG�����J�ݏ�Params.�X�V�҃R�[�h 
            l_accountOpenMngListCsv.setLastUpdater(
                l_intRow, l_accountOpenStatusParams[i].getLastUpdater());
            
            //1.10.15 set�쐬���t(int, Date)
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //�l�F UWG�����J�ݏ�Params.�쐬���t
            l_accountOpenMngListCsv.setCreatedTimestamp(
                l_intRow, l_accountOpenStatusParams[i].getCreatedTimestamp());
            
            //1.10.16 set�X�V���t(int, Date)
            //[����] 
            //�s�ԍ��F add���׍s()�̖߂�l 
            //�l�F UWG�����J�ݏ�Params.�X�V���t
            l_accountOpenMngListCsv.setLastUpdatedTimestamp(
                l_intRow, l_accountOpenStatusParams[i].getLastUpdatedTimestamp());            
        }
        
        //1.11 get���׍s��()
        int l_intRowCount = l_accountOpenMngListCsv.getRowCount();
        log.debug("get���׍s��() �� " + l_intRowCount);
        log.debug("���N�G�X�g�f�[�^.�_�E�����[�h���� �� " + l_request.downloadNumber);
        
        //get���׍s��()�̖߂�l > ���N�G�X�g�f�[�^.�_�E�����[�h���� �̏ꍇ�A
        //��O���X���[����B
        if (l_intRowCount > Integer.parseInt(l_request.downloadNumber))
        {
            log.debug("get���׍s��()�̖߂�l > ���N�G�X�g�f�[�^.�_�E�����[�h����");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01957,
                this.getClass().getName() + "." + l_strMethodName,
                "get���׍s��()�̖߂�l = [" + l_intRowCount +"], " +
                "���N�G�X�g�f�[�^.�_�E�����[�h���� = [" + l_request.downloadNumber + "]");            
        }
        
        //1.12 getCSV�t�@@�C���s()
        String[] l_strCsvFileLines = l_accountOpenMngListCsv.getCsvFileLines();
         
        //1.13 createResponse()
        WEB3AdminFEqConAccountOpenMngListDownloadResponse l_response = 
            (WEB3AdminFEqConAccountOpenMngListDownloadResponse)l_request.createResponse();
        
        //1.14 (*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�_�E�����[�h�t�@@�C��  ���@@getCSV�t�@@�C��()�̖߂�l
        l_response.downloadFile = l_strCsvFileLines;
        
        //�����R�[�h��  ���@@get���׍s��()�̖߂�l
        l_response.totalRecords = l_intRowCount + "";
        log.debug("�����R�[�h�� �� " + l_response.totalRecords);
        
        //���ݓ���  ���@@�V�X�e���^�C���X�^���v
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        log.debug("���ݓ��� �� " + l_response.currentDate);
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get�X�e�[�^�X�X�V���)<BR>
     * �X�e�[�^�X�X�V��ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������J�݊Ǘ��jget�X�e�[�^�X�X�V��ʁv �Q��
     * ------------------------------------------------
     * 1.6 getUWG�����J�ݏ�(String, String, String)
     *  null���ԋp���ꂽ�ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01948<BR>
     * <BR>
     * ------------------------------------------------
     * 1.7 (*)�����J�ݒ��`�F�b�N
     *  (�����J�ݒ��`�F�b�N)
     *  getUWG�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 != "�����J�ݒ�"�̏ꍇ�A
     *  ��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01807<BR>
     * <BR>
     * ------------------------------------------------
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B37002BA
     */
    protected WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse getStatusUpdScreen(
        WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "getStatusUpdScreen(WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�O�������U�֘A�g(�����Ǘ��E�����J�݊Ǘ�) 
        //is�X�V�F�@@true(�X�V�Ȃ�)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, true);
        
        //1.4 validate���X����(String)
        //[����] 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5 get�،���ЃR�[�h()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.6 getUWG�����J�ݏ�(String, String, String)
        //[����] 
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h 
        //���ʃR�[�h�F�@@���N�G�X�g�f�[�^.���ʃR�[�h
        
        //get �O���U�֘A�g�f�[�^����T�[�r�XImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        UwgAccountOpenStatusParams l_accountOpenStatusParams =
            l_conTransferDataControlService.getUwgAccountOpenStatus(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.requestNumber);
        
        //getUWG�����J�ݏ�(String, String, String)null���ԋp���ꂽ�ꍇ�A��O���X���[����B
        if (l_accountOpenStatusParams == null)
        {
            log.debug("getUWG�����J�ݏ�(String, String[], String)�߂�l��null");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01948,
                this.getClass().getName() + "." + l_strMethodName,
                "getUWG�����J�ݏ�(String, String[], String)�߂�l��null");            
        }
        
        //1.7 (�����J�ݒ��`�F�b�N)
        //getUWG�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 != "�����J�ݒ�"�̏ꍇ�A
        //��O���X���[����B
        if (!WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
                l_accountOpenStatusParams.getAccountOpenStatusDiv()))
        {
            log.debug("getUWG�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 != �����J�ݒ�");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01807,
                this.getClass().getName() + "." + l_strMethodName,
                "getUWG�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 != �����J�ݒ�");            
        }
        
        //1.8 createResponse()
        WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse l_response =
            (WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse)l_request.createResponse();
        
        //1.9 (*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //���X�R�[�h       ���@@UWG�����J�ݏ�Params.���X�R�[�h
        l_response.branchCode = l_accountOpenStatusParams.getBranchCode();
        
        //�ڋq�R�[�h       ���@@UWG�����J�ݏ�Params.�ڋq�R�[�h�̏�6��
        l_response.accountCode = l_accountOpenStatusParams.getAccountCode().substring(0, 6);
        
        //���O(��)       ���@@UWG�����J�ݏ�Params.���O(��)
        l_response.accountFamilyName = l_accountOpenStatusParams.getLastName();
        log.debug("���O(��) : " + l_response.accountFamilyName);
        
        //���O(��)       ���@@UWG�����J�ݏ�Params.���O(��)
        l_response.accountName = l_accountOpenStatusParams.getFirstName();
        log.debug("���O(��) : " + l_response.accountName);
        
        //���[���A�h���X ���@@UWG�����J�ݏ�Params.���[���A�h���X
        l_response.mailAddress = l_accountOpenStatusParams.getMailAddress();
        log.debug("���[���A�h���X : " + l_response.mailAddress);
        
        //�O�������ԍ�  ���@@UWG�����J�ݏ�Params.�O�����������ԍ�
        l_response.fstkAccountCode = l_accountOpenStatusParams.getFeqAccountCode();
        log.debug("�O�������ԍ� : " + l_response.fstkAccountCode);
        
        //�X�e�[�^�X�敪 ���@@UWG�����J�ݏ�Params.�����J�ݏ󋵋敪
        l_response.statusDiv = l_accountOpenStatusParams.getAccountOpenStatusDiv();
        log.debug("�X�e�[�^�X�敪 : " + l_response.statusDiv);
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (validate�X�e�[�^�X�X�V)<BR>
     * �X�e�[�^�X�X�V�̊m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������J�݊Ǘ��jvalidate�X�e�[�^�X�X�V�v �Q��
     * ------------------------------------------------
     * 1.6 getUWG�����J�ݏ�(String, String, String)
     *  null���ԋp���ꂽ�ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01948<BR>
     * <BR>
     * ------------------------------------------------
     * 1.7 (*)�����J�ݒ��`�F�b�N
     *  (�����J�ݒ��`�F�b�N)
     *  getUWG�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 != "�����J�ݒ�"�̏ꍇ�A
     *  ��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01807<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B37002D9
     */
    protected WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse validateStatusUpd(
        WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "validateStatusUpd(WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�O�������U�֘A�g(�����Ǘ��E�����J�݊Ǘ�) 
        //is�X�V�F�@@true(�X�V�Ȃ�)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, true);
        
        //1.4 validate���X����(String)
        //[����] 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5 get�،���ЃR�[�h()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.6 getUWG�����J�ݏ�(String, String, String)
        //[����] 
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h 
        //���ʃR�[�h�F�@@���N�G�X�g�f�[�^.���ʃR�[�h
        
        //get �O���U�֘A�g�f�[�^����T�[�r�XImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        UwgAccountOpenStatusParams l_accountOpenStatusParams =
            l_conTransferDataControlService.getUwgAccountOpenStatus(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.requestNumber);
        
        //getUWG�����J�ݏ�(String, String, String)null���ԋp���ꂽ�ꍇ�A��O���X���[����B
        if (l_accountOpenStatusParams == null)
        {
            log.debug("getUWG�����J�ݏ�(String, String[], String)�߂�l��null");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01948,
                this.getClass().getName() + "." + l_strMethodName,
                "getUWG�����J�ݏ�(String, String[], String)�߂�l��null");            
        }
        
        //1.7 (�����J�ݒ��`�F�b�N)
        //getUWG�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 != "�����J�ݒ�"�̏ꍇ�A
        //��O���X���[����B
        if (!WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
                l_accountOpenStatusParams.getAccountOpenStatusDiv()))
        {
            log.debug("getUWG�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 != �����J�ݒ�");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01807,
                this.getClass().getName() + "." + l_strMethodName,
                "getUWG�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 != �����J�ݒ�");            
        }
        
        //1.8 createResponse()
        WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse l_response =
            (WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse)l_request.createResponse();
        
        log.exiting(l_strMethodName);
        
     return l_response;
    }
    
    /**
     * (submit�X�e�[�^�X�X�V)<BR>
     * �X�e�[�^�X�X�V�̊����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������J�݊Ǘ��jsubmit�X�e�[�^�X�X�V�v �Q��
     * ------------------------------------------------
     * 1.8 getUWG�����J�ݏ�(String, String, String)
     *  null���ԋp���ꂽ�ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01948<BR>
     * <BR>
     * ------------------------------------------------
     * 1.9 (*)�����J�ݒ��`�F�b�N
     *  (�����J�ݒ��`�F�b�N)
     *  getUWG�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 != "�����J�ݒ�"�̏ꍇ�A
     *  ��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01807<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3B37002F8
     */
    protected WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse submitStatusUpd(
            WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "submitStatusUpd(WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�O�������U�֘A�g(�����Ǘ��E�����J�݊Ǘ�) 
        //is�X�V�F�@@true(�X�V�Ȃ�)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ACCOUNT_MANAGE, true);
        
        //1.4 validate����p�X���[�h(String)
        //[����] 
        //�p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);
        
        //1.5 validate���X����(String)
        //[����] 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.6 get�Ǘ��҃R�[�h()
        String l_strAdministratorCode = l_administrator.getAdministratorCode();
        log.debug("get�Ǘ��҃R�[�h() = " + l_strAdministratorCode);
        
        //1.7 get�،���ЃR�[�h()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.8 getUWG�����J�ݏ�(String, String, String)
        //[����] 
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h 
        //���ʃR�[�h�F�@@���N�G�X�g�f�[�^.���ʃR�[�h
        
        //get �O���U�֘A�g�f�[�^����T�[�r�XImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        UwgAccountOpenStatusParams l_accountOpenStatusParams =
            l_conTransferDataControlService.getUwgAccountOpenStatus(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.requestNumber);
        
        //getUWG�����J�ݏ�(String, String, String)null���ԋp���ꂽ�ꍇ�A��O���X���[����B
        if (l_accountOpenStatusParams == null)
        {
            log.debug("getUWG�����J�ݏ�(String, String[], String)�߂�l��null");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01948,
                this.getClass().getName() + "." + l_strMethodName,
                "�ꗗ�ɕ\������f�[�^������܂���");            
        }
                 
        log.debug("l_accountOpenStatusParams = " + l_accountOpenStatusParams);
        log.debug("getUWG�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 = " + 
                l_accountOpenStatusParams.getAccountOpenStatusDiv());
        
        //1.9 (�����J�ݒ��`�F�b�N)
        //getUWG�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 != "�����J�ݒ�"�̏ꍇ�A
        //��O���X���[����B
        if (!WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
                l_accountOpenStatusParams.getAccountOpenStatusDiv()))
        {
            log.debug("getUWG�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 != �����J�ݒ�");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01807,
                this.getClass().getName() + "." + l_strMethodName,
                "�O�������������J�݂���Ă��܂���");            
        }
        
        //1.10 updateUWG�����J�ݏ�(UWG�����J�ݏ�Params, String, String)
        //[����] 
        //UWG�����J�ݏ�Params�F�@@getUWG�����J�ݏ�Params()�̖߂�l 
        //�X�V��X�e�[�^�X�敪�F�@@���N�G�X�g�f�[�^.�X�V��X�e�[�^�X�敪 
        //�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
        l_conTransferDataControlService.updateUwgAccountOpenStatus(
            l_accountOpenStatusParams,
            l_request.updatedStatusDiv,
            l_strAdministratorCode);
        
        log.debug("���N�G�X�g�f�[�^.�X�V��X�e�[�^�X�敪 = " + l_request.updatedStatusDiv);
        
        //1.11 ���N�G�X�g�f�[�^.�X�V��X�e�[�^�X�敪 == "�����J�݊���"�̏ꍇ
        if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(l_request.updatedStatusDiv))
        {
            //1.11.1 insert�O�������ڋq(UWG�����J�ݏ�Params, String)
            //[����] 
            //UWG�����J�ݏ�Params�F�@@getUWG�����J�ݏ�()�̖߂�l 
            //�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
            l_conTransferDataControlService.insertFeqAccount(
                l_accountOpenStatusParams, l_strAdministratorCode);
            
            //1.11.2 get�ڋq(String, String, String)
            //[����] 
            //�،���ЃR�[�h�F�@@getUWG�����J�ݏ�()�̖߂�l.�،���ЃR�[�h 
            //���X�R�[�h�F�@@getUWG�����J�ݏ�()�̖߂�l.���X�R�[�h 
            //�����R�[�h�F�@@getUWG�����J�ݏ�()�̖߂�l.�ڋq�R�[�h
            
            //�g���A�J�E���g�}�l�[�W���擾����   
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            WEB3GentradeMainAccount l_mainAccount = 
                l_accountManager.getMainAccount(
                    l_accountOpenStatusParams.getInstitutionCode(),
                    l_accountOpenStatusParams.getBranchCode(),
                    l_accountOpenStatusParams.getAccountCode());
            
            log.debug("get�ڋq()�̖߂�l.MRF�����J�݋敪 = " + ((MainAccountRow)
                    l_mainAccount.getDataSourceObject()).getMrfAccOpenDiv());
                    
            //1.11.3 MRF�������J��(get�ڋq()�̖߂�l.MRF�����J�݋敪 == "DEFAULT(�����Ȃ�)")�̏ꍇ
            if (WEB3AccountOpenDef.NOT_OPEN.equals(
                    ((MainAccountRow)l_mainAccount.getDataSourceObject()).getMrfAccOpenDiv()))
            {
                //1.11.3.1 update�O�������J�݋敪(String, String, String, String, String)
                //[����] 
                //�،���ЃR�[�h�F�@@getUWG�����J�ݏ�()�̖߂�l.�،���ЃR�[�h 
                //���X�R�[�h�F�@@getUWG�����J�ݏ�()�̖߂�l.���X�R�[�h 
                //�ڋq�R�[�h�F�@@getUWG�����J�ݏ�()�̖߂�l.�ڋq�R�[�h 
                //�X�V��O�������J�݋敪�F�@@"�J��" 
                //�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
                l_conTransferDataControlService.updateFeqAccountOpenDiv(
                        l_accountOpenStatusParams.getInstitutionCode(),
                        l_accountOpenStatusParams.getBranchCode(),
                        l_accountOpenStatusParams.getAccountCode(),
                        WEB3ForeignSecAccOpenDiv.OPEN,
                        l_strAdministratorCode);
            }
        }
        
        //1.12 createResponse()
        WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse l_response =
            (WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse)l_request.createResponse();
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (create��������������)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j��̌�������������(�FString)�𐶐�����B<BR>
     * <BR>
     * �Q�j�،���Џ��������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += " institution_code = ? "<BR>
     * <BR>
     * �R�j���X���������������ɒǉ�����B<BR>
     * �@@�p�����[�^.���X�R�[�h[]�̗v�f����"?"��ǉ�����B<BR>
     * <BR>
     * �@@�������������� += " and branch_code in (?,?,,,) "<BR>
     * <BR>
     * �S�j�p�����[�^.�X�e�[�^�X�敪 != null�̏ꍇ�A<BR>
     * �@@�����J�ݏ󋵋敪�����������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and account_open_status_div = ? "<BR>
     * <BR>
     * �T�j�p�����[�^.�\�����i���j != null�̏ꍇ�A<BR>
     * �@@�\�����̉��������������ɒǉ�����B<BR>
     * <BR>
     *  �������������� += "and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? "<BR>
     * <BR>
     * �U�j�p�����[�^.�\�����i���j != null�̏ꍇ�A<BR>
     * �@@�\�����̉��������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and to_char(created_timestamp, 'YYYYMMDDHH24') < ? "<BR>
     * <BR>
     * �V�j�쐬�������������������ԋp����B
     * @@param l_strBranchCodes - ���X�R�[�h�̔z��
     * @@param l_strStatusDiv - �X�e�[�^�X�敪
     * 
     * 0�F�����J�ݒ�
     * 1�F�����J�݊���
     * 2�F�����J�݃G���[
     * 9�F�폜
     * 
     * @@param l_strApplyDateFrom - �\�����i���j
     * (YYYYMMDDhh)
     * @@param l_strApplyDateTo - �\�����i���j
     * (YYYYMMDDhh)
     * @@return String
     * @@roseuid 41E5E3FB01EA
     */
    protected String createQueryString(
            String[] l_strBranchCodes, 
            String l_strStatusDiv, 
            String l_strApplyDateFrom, 
            String l_strApplyDateTo) 
    {
        String l_strMethodName = 
            "createQueryString(String[] l_strBranchCodes, String l_strStatusDiv, String l_strApplyDateFrom, String l_strApplyDateTo)";
        log.entering(l_strMethodName);
        
        if (l_strBranchCodes == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //�P�j��̌�������������(�FString)�𐶐�����B
        StringBuffer l_strBuffer = new StringBuffer();
        
        //�Q�j�،���Џ��������������ɒǉ�����B       
        //�@@�������������� += " institution_code = ? "
        l_strBuffer.append(" institution_code = ? ");
        
        //�R�j���X���������������ɒǉ�����B
        //�@@�p�����[�^.���X�R�[�h[]�̗v�f����"?"��ǉ�����B        
        //�@@�������������� += " and branch_code in (?,?,,,) "
        StringBuffer l_strBufferBranch = new StringBuffer();
        
        l_strBufferBranch.append(" and branch_code in ( ? ");        
        for (int i = 1; i < l_strBranchCodes.length; i++)
        {
            l_strBufferBranch.append(", ? ");
        }        
        l_strBufferBranch.append(")");
        
        l_strBuffer.append(l_strBufferBranch);
        
        //�S�j�p�����[�^.�X�e�[�^�X�敪 != null�̏ꍇ�A
        //�@@�����J�ݏ󋵋敪�����������ɒǉ�����B        
        //�@@�������������� += "and account_open_status_div = ? "
        if (!WEB3StringTypeUtility.isEmpty(l_strStatusDiv))
        {
            l_strBuffer.append(" and account_open_status_div = ? ");
        }
        
        //�T�j�p�����[�^.�\�����i���j != null�̏ꍇ�A
        //�@@�\�����̉��������������ɒǉ�����B        
        // �������������� += "and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? "
        if (!WEB3StringTypeUtility.isEmpty(l_strApplyDateFrom))
        {
            l_strBuffer.append(" and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? ");
        }
        
        //�U�j�p�����[�^.�\�����i���j != null�̏ꍇ�A
        //�@@�\�����̉��������������ɒǉ�����B        
        //�@@�������������� += "and to_char(created_timestamp, 'YYYYMMDDHH24') < ? "
        if (!WEB3StringTypeUtility.isEmpty(l_strApplyDateTo))
        {
            l_strBuffer.append(" and to_char(created_timestamp, 'YYYYMMDDHH24') < ? ");
        }
        
        //�V�j�쐬�������������������ԋp����B
        String l_strCondition = l_strBuffer.toString();
        
        log.exiting(l_strMethodName);
        
        return l_strCondition;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�،���Џ�����ǉ�����B<BR>
     * �@@�p�����[�^.�،���ЃR�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j���X������ǉ�����B<BR>
     * �@@�p�����[�^.���X�R�[�h�̑S�v�f��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�j�p�����[�^.�X�e�[�^�X�敪 != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�X�e�[�^�X�敪��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �T�j�p�����[�^.�\�����i���j != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�\�����i���j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �U�j�p�����[�^.�\�����i���j != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�\�����i���j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �V�j��������ArrayList.toArray()�̖߂�l��ԋp����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCodes - ���X�R�[�h�̔z��
     * @@param l_strStatusDiv - �X�e�[�^�X�敪
     * 
     * 0�F�����J�ݒ�
     * 1�F�����J�݊���
     * 2�F�����J�݃G���[
     * 9�F�폜
     * @@param l_strApplyDateFrom - �\�����i���j
     * (YYYYMMDDhh)
     * @@param l_strApplyDateTo - �\�����i���j
     * (YYYYMMDDhh)
     * @@return String[]
     * @@roseuid 41E5E3FB01F0
     */
    protected String[] createQueryContainer(
            String l_strInstitutionCode, 
            String[] l_strBranchCodes, 
            String l_strStatusDiv, 
            String l_strApplyDateFrom, 
            String l_strApplyDateTo) 
    {
        String l_strMethodName = 
            "createQueryContainer(String l_strInstitutionCode, String[] l_strBranchCodes, String l_strStatusDiv, String l_strApplyDateFrom, String l_strApplyDateTo)";
        log.entering(l_strMethodName);
        
        if (l_strBranchCodes == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //�P�jArrayList�𐶐�����B
        List l_lisValue = new ArrayList();
        
        //�Q�j�،���Џ�����ǉ�����B
        //�@@�p�����[�^.�،���ЃR�[�h��ArrayList�ɒǉ�����B
        l_lisValue.add(l_strInstitutionCode);
        
        //�R�j���X������ǉ�����B
        //�@@�p�����[�^.���X�R�[�h�̑S�v�f��ArrayList�ɒǉ�����B
        for (int i = 0; i < l_strBranchCodes.length; i++)
        {
            l_lisValue.add(l_strBranchCodes[i]);
        }       
        
        //�S�j�p�����[�^.�X�e�[�^�X�敪 != null�̏ꍇ�A
        //�@@�p�����[�^.�X�e�[�^�X�敪��ArrayList�ɒǉ�����B
        if (!WEB3StringTypeUtility.isEmpty(l_strStatusDiv))
        {
            l_lisValue.add(l_strStatusDiv);
        }
        
        //�T�j�p�����[�^.�\�����i���j != null�̏ꍇ�A
        //�@@�p�����[�^.�\�����i���j��ArrayList�ɒǉ�����B
        if (!WEB3StringTypeUtility.isEmpty(l_strApplyDateFrom))
        {
            l_lisValue.add(l_strApplyDateFrom);
        }
        
        //�U�j�p�����[�^.�\�����i���j != null�̏ꍇ�A
        //�@@�p�����[�^.�\�����i���j��ArrayList�ɒǉ�����B
        if (!WEB3StringTypeUtility.isEmpty(l_strApplyDateTo))
        {
            l_lisValue.add(l_strApplyDateTo);
        }
        
        //�V�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        String[] l_strValue = new String[l_lisValue.size()];
        l_lisValue.toArray(l_strValue);
        
        log.exiting(l_strMethodName);
        
        return l_strValue;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�������쐬����B<BR>
     * <BR>
     * �P�j��̃\�[�g����������(�FString)�𐶐�����B<BR>
     * <BR>
     * �Q�j�ȉ��̃\�[�g������\���\�[�g������������쐬����B<BR>
     * �@@�@@�X�V���t�@@�~��<BR>
     * <BR>
     * �@@�\�[�g���������� = " last_updated_timestamp desc"<BR>
     * <BR>
     * �R�j�쐬�����\�[�g�����������ԋp����B
     * @@return String
     * @@roseuid 41E5E3FB01F6
     */
    protected String createSortCond() 
    {
        String l_strMethodName = "createSortCond()";
        log.entering(l_strMethodName);
        
        //�P�j��̃\�[�g����������(�FString)�𐶐�����B
        String l_strSort = new String();
        
        //�Q�j�ȉ��̃\�[�g������\���\�[�g������������쐬����B
        //�@@�쐬���t�@@�~��
       //�\�[�g���������� = " created_timestamp desc"
        l_strSort = " created_timestamp desc";
        
        //�R�j�쐬�����\�[�g�����������ԋp����B
        log.exiting(l_strMethodName);
        
        return l_strSort;
    }
    
    /**
     * (create�O�������J�ݎ�����ꗗ)<BR>
     * �����̎���񓚈ꗗ���A�O�������J�ݎ������<BR>
     * �ꗗ���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.����񓚈ꗗ�̗v�f�����A<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�O�������J�ݎ�����C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B�Z�b�g���s���B<BR>
     * �@@�@@����ԍ� = �����Params.����ԍ�<BR>
     * �@@�@@������e = null<BR>
     * �@@�@@����� = �����Params.�����<BR>
     * <BR>
     * �@@�Q�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �R�j��������ArrayList.toArray()�̖߂�l��ԋp����B
     * @@param l_questionAnswerParams - �����Params�I�u�W�F�N�g�̔z��
     * @@return WEB3FEqConAccountOpenQuestionInfo[]
     * @@throws WEB3BaseException
     * @@roseuid 41E5F5B402CD
     */
    protected WEB3FEqConAccountOpenQuestionInfo[] createFeqAccOpenQuestionInfoList(
        QuestionAnswerParams[] l_questionAnswerParams) 
    {
        String l_strMethodName = 
            "createfeqAccOpenQuestionInfoList(QuestionAnswerParams[] l_questionAnswerParams)";
        log.entering(l_strMethodName);
        
        if (l_questionAnswerParams == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //�P�jArrayList�𐶐�����B
        List l_lisInfo = new ArrayList();
        
        //�Q�j�p�����[�^.����񓚈ꗗ�̗v�f�����A
        //�@@�ȉ��̏������J��Ԃ��B
        WEB3FEqConAccountOpenQuestionInfo l_conAccountOpenQuestionInfo = null;

        for (int i = 0; i < l_questionAnswerParams.length; i++)
        {
            //�@@�Q�|�P�j�O�������J�ݎ�����C���X�^���X�𐶐�����B              
            l_conAccountOpenQuestionInfo = new WEB3FEqConAccountOpenQuestionInfo();
            
            //�@@�Q�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B�Z�b�g���s���B
            //�@@�@@����ԍ� = �����Params.����ԍ�
            l_conAccountOpenQuestionInfo.questionNumber = 
                l_questionAnswerParams[i].getQuestionNo();
            //�@@�@@������e = null
            l_conAccountOpenQuestionInfo.questionContent = null;
            //�@@�@@����� = �����Params.����� 
            l_conAccountOpenQuestionInfo.questionAnswer =
                l_questionAnswerParams[i].getQuestionAnswer();
            
            //�@@�Q�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
            l_lisInfo.add(l_conAccountOpenQuestionInfo);
        }        
        
        //�R�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3FEqConAccountOpenQuestionInfo[] l_info = 
            new WEB3FEqConAccountOpenQuestionInfo[l_lisInfo.size()];
        l_lisInfo.toArray(l_info);
        
        log.exiting(l_strMethodName);
        
        return l_info;
    }
    
    /**
     * (get�����敪)<BR>
     * ������UWG�����J�ݏ�Params���A<BR>
     * ���݂̏����敪�𔻕ʂ��A�ԋp����B<BR>
     * ���ԋp�����l�́A�O�������J�ݐ\������.���l�̃A�C�e����`���Q�ƁB<BR>
     * <BR>
     * ��UWG�V�X�e���̃��^�[���R�[�h�����m��̂��߁A�d�l�ύX�̉\������B<BR>
     * <BR>
     * �@@�p�����[�^.UWG�����J�ݏ�Params��<BR>
     * �@@�@@[�����J�ݏ󋵋敪 == "�����J�ݒ�" ���� <BR>
     * �@@�@@����M�敪 == "�����M" �̏ꍇ] <BR>
     * �@@�@@"�����J�ݎ�t��"��ԋp����B <BR>
     * <BR>
     * �@@[�����J�ݏ󋵋敪 == "�����J�ݒ�" ���� <BR>
     * �@@�@@����M�敪 == "���M��" �̏ꍇ] <BR>
     * �@@�@@"�����J�ݒ�"��ԋp����B <BR>
     * <BR>
     * �@@[�����J�ݏ󋵋敪 == "�����J�ݍ�" �̏ꍇ]<BR>
     *      "�����J�݊���"��ԋp����B<BR>
     * <BR>
     * �@@[�����J�ݏ󋵋敪 == "�����J�݃G���[" ���� <BR>
     * �@@�@@"����M�敪" == "��M��" �̏ꍇ] <BR>
     * �@@�@@�p�����[�^.UWG�����J�ݏ�Params.�G���[���R�R�[�h��ԋp����B <BR>
     * <BR>
     * �@@[�����J�݋敪 == "�폜"�̏ꍇ] <BR>
     * �@@�@@"�����"��ԋp����B <BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ] <BR>
     * �@@�@@"�V�X�e���G���["��ԋp����B <BR>
     * <BR>
     * @@param l_uwgAccOpenStatusParams - UWG�����J�ݏ�Params�I�u�W�F�N�g
     * @@return String
     * @@roseuid 41E5F6DF006B
     */
    protected String getOperationDiv(UwgAccountOpenStatusParams l_uwgAccOpenStatusParams) 
    {
        String l_strMethodName = 
            "getOperationDiv(UwgAccountOpenStatusParams l_uwgAccOpenStatusParams)";
        log.entering(l_strMethodName);
        
        if (l_uwgAccOpenStatusParams == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        String l_strReturn = null;
        
        //�@@�p�����[�^.UWG�����J�ݏ�Params��
        //�@@[�����J�ݏ󋵋敪 == "�����J�ݒ�" ���� 
        //   ����M�敪 == "�����M" �̏ꍇ] 
        //  "�����J�ݎ�t��"��ԋp����B
        if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
                l_uwgAccOpenStatusParams.getAccountOpenStatusDiv()) &&
            WEB3SendRcvDivDef.NOT_SEND.equals(
                l_uwgAccOpenStatusParams.getSendRcvDiv()))
        {
            l_strReturn = WEB3AioFeqTransferDivMessageDef.ACCEPT_RESULT_CODE_10000000;
        }
        
        //�@@[�����J�ݏ󋵋敪 == "�����J�ݒ�" ���� 
        //   ����M�敪 == "���M��" �̏ꍇ] 
        //  "�����J�ݒ�"��ԋp����B
        else if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
                l_uwgAccOpenStatusParams.getAccountOpenStatusDiv()) &&
             WEB3SendRcvDivDef.SEND_COMPLETE.equals(
                l_uwgAccOpenStatusParams.getSendRcvDiv()))
        {
            l_strReturn = WEB3AioFeqTransferDivMessageDef.ACCEPT_RESULT_CODE_20000000;
        }
        
        //�@@[�����J�ݏ󋵋敪 == "�����J�ݍ�"�̏ꍇ] 
        //  "�����J�݊���"��ԋp����B
        else if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(
                    l_uwgAccOpenStatusParams.getAccountOpenStatusDiv()))                    
        {
            l_strReturn = WEB3AioFeqTransferDivMessageDef.ACCEPT_RESULT_CODE_00000000;
        }
        // [�����J�ݏ󋵋敪 == "�����J�݃G���[" ���� 
        //  "����M�敪" == "��M��" �̏ꍇ] 
        // �p�����[�^.UWG�����J�ݏ�Params.�G���[���R�R�[�h��ԋp����B
        else if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(
                    l_uwgAccOpenStatusParams.getAccountOpenStatusDiv()) &&
                 WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(
                    l_uwgAccOpenStatusParams.getSendRcvDiv()))                    
        {
            l_strReturn = l_uwgAccOpenStatusParams.getErrorReasonCode();
        }        
        //�@@[�����J�ݏ󋵋敪 == "�폜"�̏ꍇ]
        //�@@�@@"�����"��ԋp����B
        else if (WEB3AccountOpenStatusDivDef.DELETE.equals(
                l_uwgAccOpenStatusParams.getAccountOpenStatusDiv()))
        {
            l_strReturn = WEB3AioFeqTransferDivMessageDef.ACCEPT_RESULT_CODE_90000000;
        }
        //�@@[��L�ȊO�̏ꍇ]
        //�@@�@@"�V�X�e���G���["��ԋp����B
        else
        {
            l_strReturn = WEB3AioFeqTransferDivMessageDef.ACCEPT_RESULT_CODE_99999999;
        }
        
        log.exiting(l_strMethodName);
        
        return l_strReturn;
    }
}
@
