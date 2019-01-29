head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenManagementServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���FX�����J�݊Ǘ��T�[�r�X�����N���X(WEB3AdminFXAccOpenManagementServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/20 ������ (���u) �V�K�쐬
                 : 2006/02/08 杊��] (���u) �d�l�ύX�E���f��466�A467�A476�A482
                 : 2006/02/23 ���iSRA�j �d�l�ύX�E���f��501
                 : 2006/02/24 ���_�O (���u) �d�l�ύX�E���f��486
Revesion History : 2008/05/20 �đo�g (���u) �d�l�ύX�E���f��860�A870
Revesion History : 2008/09/08 ��  �O (���u) �d�l�ύX�E���f��935
Revesion History : 2008/09/08 ��  �O (���u) �����̖��ENo016
Revesion History : 2008/09/22 ���@@�g (���u) �d�l�ύX�E���f��1016,1017,1018,1019,1034,1035,1036,1069
                 : 2008/10/29 �g�� (SCS) �d�l�ύX�E���f��1081
                 : 2008/12/16 �哈 (SCS) �d�l�ύX�E���f��1088,1089,1090
Revesion History : 2009/03/21  �Ԑi (���u) �d�l�ύX�E���f��1133,�c�a�X�V�d�l215
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.WEB3AdminFXAccOpenApplyDownloadCsv;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.GftAccountOpenStatusRow;
import webbroker3.aio.define.WEB3AioAccOpenRemarkDef;
import webbroker3.aio.define.WEB3AioAgreementDivDef;
import webbroker3.aio.define.WEB3AioFxAccountOpenDivDef;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyDownloadRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyDownloadResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListConditionRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListConditionResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdInputRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdInputResponse;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXAccOpenApplyUnit;
import webbroker3.aio.message.WEB3FXTradeAgreementUnit;
import webbroker3.aio.service.delegate.WEB3AdminFXAccOpenManagementService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.define.WEB3AccOpenRealUpdateDef;
import webbroker3.common.define.WEB3AccTypeDef;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.define.WEB3QuestionDivDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeAccOpenDiv;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionAnswerRow;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ���FX�����J�݊Ǘ��T�[�r�XImpl) <BR>
 * �Ǘ���FX�����J�݊Ǘ��T�[�r�X�����N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenManagementServiceImpl extends
    WEB3ClientRequestService implements WEB3AdminFXAccOpenManagementService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenManagementServiceImpl.class);

    /**
     * (execute) <BR>
     * �Ǘ���FX�����J�݊Ǘ��������s���B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B <BR>
     * <BR>
     * ���Ǘ��ҁEFX�����J�ݐ\���ꗗ�������̓��N�G�X�g�̏ꍇ <BR>
     * this.get�������͉��()���\�b�h���R�[������B <BR>
     * <BR>
     * ���Ǘ��ҁEFX�����J�ݐ\���ꗗ���N�G�X�g�̏ꍇ <BR>
     * this.get�ꗗ���()���\�b�h���R�[������B <BR>
     * <BR>
     * ���Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V���̓��N�G�X�g�̏ꍇ <BR>
     * this.get�X�e�[�^�X�X�V���͉��()���\�b�h���R�[������B <BR>
     * <BR>
     * ���Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�m�F���N�G�X�g�̏ꍇ <BR>
     * this.validate�X�e�[�^�X�X�V()���\�b�h���R�[������B <BR>
     * <BR>
     * ���Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�������N�G�X�g�̏ꍇ <BR>
     * this.submit�X�e�[�^�X�X�V()���\�b�h���R�[������B
     * <BR>
     * ���Ǘ��ҁEFX�����J�ݐ\���_�E�����[�h���N�G�X�g�̏ꍇ <BR>
     * this.get�_�E�����[�h�t�@@�C��()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD54B9005A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
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
        
        if (l_request instanceof WEB3AdminFXAccOpenApplyListConditionRequest)
        {
            // ���Ǘ��ҁEFX�����J�ݐ\���ꗗ�������̓��N�G�X�g�̏ꍇ 
            // this.get�������͉��()���\�b�h���R�[������B 
            WEB3AdminFXAccOpenApplyListConditionResponse l_Response = 
                this.getCondInputScreen((WEB3AdminFXAccOpenApplyListConditionRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else if (l_request instanceof WEB3AdminFXAccOpenApplyDownloadRequest)
        {
            // ���Ǘ��ҁEFX�����J�ݐ\���_�E�����[�h���N�G�X�g�̏ꍇ
            // this.get�_�E�����[�h�t�@@�C��()���\�b�h���R�[������B
            WEB3AdminFXAccOpenApplyDownloadResponse l_response =
                this.getDownloadFile((WEB3AdminFXAccOpenApplyDownloadRequest) l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminFXAccOpenApplyListRequest)
        {
            // ���Ǘ��ҁEFX�����J�ݐ\���ꗗ���N�G�X�g�̏ꍇ 
            // this.get�ꗗ���()���\�b�h���R�[������B 
            WEB3AdminFXAccOpenApplyListResponse l_Response = 
                this.getListScreen((WEB3AdminFXAccOpenApplyListRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else if (l_request instanceof WEB3AdminFXAccOpenStatusUpdInputRequest)
        {
            // ���Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V���̓��N�G�X�g�̏ꍇ 
            // this.get�X�e�[�^�X�X�V���͉��()���\�b�h���R�[������B
            WEB3AdminFXAccOpenStatusUpdInputResponse l_Response = 
                this.getStatusUpdInput((WEB3AdminFXAccOpenStatusUpdInputRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else if (l_request instanceof WEB3AdminFXAccOpenStatusUpdConfirmRequest)
        {
            // ���Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�m�F���N�G�X�g�̏ꍇ 
            // this.validate�X�e�[�^�X�X�V()���\�b�h���R�[������B
            WEB3AdminFXAccOpenStatusUpdConfirmResponse l_Response = 
                this.validateStatusUpd((WEB3AdminFXAccOpenStatusUpdConfirmRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }        
        else if (l_request instanceof WEB3AdminFXAccOpenStatusUpdCompleteRequest)
        {
            // ���Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�������N�G�X�g�̏ꍇ 
            // this.submit�X�e�[�^�X�X�V()���\�b�h���R�[������B
            WEB3AdminFXAccOpenStatusUpdCompleteResponse l_Response = 
                this.submitStatusUpd((WEB3AdminFXAccOpenStatusUpdCompleteRequest) l_request);
                
            log.exiting(STR_METHOD_NAME);    
            return l_Response;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * (get�������͉��) <BR>
     * (�Ǘ���FX�����J�݊Ǘ�)�����J�ݐ\���������� <BR>
     * ���͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ���FX�����J�݊Ǘ��T�[�r�X)get�������͉�ʁv�Q��
     * @@param l_request - �Ǘ��ҁEFX�����J�ݐ\���ꗗ�������̓��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccOpenApplyListConditionResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD556E0079
     */
    protected WEB3AdminFXAccOpenApplyListConditionResponse getCondInputScreen(
        WEB3AdminFXAccOpenApplyListConditionRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCondInputScreen()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1 ) ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        // 2 ) ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) �Ǘ��Ҍ����̃`�F�b�N���s���B 
        // [����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ב֕ۏ؋��Ǘ�(�����Ǘ��E�����J�݊Ǘ�) 
        // is�X�V�F�@@false(�X�V�Ȃ�) 
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
                false
                );
        
        // 4 ) ���X�����̃`�F�b�N���s���B 
        // [����] 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h�ꗗ
        l_admin.validateBranchPermission(l_request.branchCodeList);
        
        // 5 ) ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFXAccOpenApplyListConditionResponse l_response = 
            (WEB3AdminFXAccOpenApplyListConditionResponse) l_request.createResponse();
        
        // 6 ) �v���p�e�B�Z�b�g
        // ���݂̎������擾
        Timestamp l_datSystemDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        // ���ݎ����̑O�c�Ɠ����擾
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(l_datSystemDate);
        Timestamp l_datLastBizDate = l_gentradeBizDate.roll(-1);
        // ���ݎ����̑O�����擾
        Date l_datBeforeDay = WEB3DateUtility.addDay(l_datSystemDate, -1L);
        
        // ���X�|���X�f�[�^.�\�����i���j= ���ݎ����̑O�c�Ɠ�
        l_response.applyDateFrom = 
            WEB3DateUtility.formatDate(l_datLastBizDate, "yyyyMMdd");
        // ���X�|���X�f�[�^.�\�����i���j= ���ݎ����̑O��
        l_response.applyDateTo = 
            WEB3DateUtility.formatDate(l_datBeforeDay, "yyyyMMdd");
        
        // 7 ) ���X�|���X�f�[�^��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ꗗ���) <BR>
     * (�Ǘ���FX�����J�݊Ǘ�)�����J�ݐ\���������ʈꗗ��ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ���FX�����J�݊Ǘ��T�[�r�X)get�ꗗ��ʁv�Q�� <BR>
     * @@param l_request - �Ǘ��ҁEFX�����J�ݐ\���ꗗ���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccOpenApplyListResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD556E0098
     */
    protected WEB3AdminFXAccOpenApplyListResponse getListScreen(
        WEB3AdminFXAccOpenApplyListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getListScreen()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1 ) ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        // 2 ) ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) �Ǘ��Ҍ����̃`�F�b�N���s���B 
        // [����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ב֕ۏ؋��Ǘ�(�����Ǘ��E�����J�݊Ǘ�) 
        // is�X�V�F�@@false(�X�V�Ȃ�) 
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
                false
                );
        
        // 4 ) ���X�����̃`�F�b�N���s���B 
        // [����] 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h
        l_admin.validateBranchPermission(l_request.branchCodeList);
        
        // 5 ) �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //create�擾����������(String, String[], String, String, String, String, String)
        // [����] 
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h 
        // �X�e�[�^�X�敪�F�@@���N�G�X�g�f�[�^.�X�e�[�^�X�敪 
        // �\�����i���j�F�@@���N�G�X�g�f�[�^.�\�����i���j 
        // �\�����i���j�F�@@���N�G�X�g�f�[�^.�\�����i���j
        // ������敪�F�@@���N�G�X�g�f�[�^.������敪
        // FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        String l_strWhere = 
            this.createQueryConditionList(
                l_strInstitutionCode,
                l_request.branchCodeList,
                l_request.statusDiv,
                l_request.applyHourFrom,
                l_request.applyHourTo,
                l_request.agreementDiv,
                l_request.fxSystemCode);
        
        //create�擾�����f�[�^�R���e�i(String, String[], String, String, String, String, String)
        // [����] 
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h 
        // �X�e�[�^�X�敪�F�@@���N�G�X�g�f�[�^.�X�e�[�^�X�敪 
        // �\�����i���j�F�@@���N�G�X�g�f�[�^.�\�����i���j 
        // �\�����i���j�F�@@���N�G�X�g�f�[�^.�\�����i���j
        // ������敪�F�@@���N�G�X�g�f�[�^.������敪
        // FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        String[] l_arrVars = 
            this.createQueryDataContainer(
                l_strInstitutionCode,
                l_request.branchCodeList,
                l_request.statusDiv,
                l_request.applyHourFrom,
                l_request.applyHourTo,
                l_request.agreementDiv,
                l_request.fxSystemCode);
        
        // 8 ) �\�[�g�������쐬����
        String l_strSort = this.createSortCond();
        
        // 9 ) GFT�����J�ݏ󋵂̈ꗗ���擾����B 
        // [����] 
        // ��������������F�@@create��������������()�̖߂�l 
        // ���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l 
        // �\�[�g�����F�@@create�\�[�g����()�̖߂�l
        WEB3FXDataControlService l_controlService = 
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class);  
        GftAccountOpenStatusParams[] l_statusParamses = 
            l_controlService.getGFTAccountOpenStatuses(
                l_strWhere,
                l_arrVars,
                l_strSort
                );
        
        // 10 ) �w�肳�ꂽMRF�����J�݋敪�ɊY������ 
        // �����J�ݏ󋵂̈ꗗ���擾����B 
        // [����] 
        // �����J�ݏ󋵈ꗗ�F�@@getGFT�����J�ݏ�()�̖߂�l 
        // MRF�����J�݋敪�F�@@
        //                 [���N�G�X�g�f�[�^.MRF�����J�݋敪 == "2�F���J��"�̏ꍇ]
        //                      "0�FDEFAULT(�����Ȃ�)"���Z�b�g�B
        //                 [��L�ȊO]  ���N�G�X�g�f�[�^.MRF�����J�݋敪���Z�b�g�B
        String l_strMrfAccountStatusDiv = l_request.mrfAccountStatusDiv;
        if(WEB3AioFxAccountOpenDivDef.NOT_OPEN.equals(l_strMrfAccountStatusDiv))
        {
            l_strMrfAccountStatusDiv = WEB3AccountOpenDef.NOT_OPEN;
        }
        GftAccountOpenStatusParams[] l_arrOpenStatusParams = 
            this.getObjGFTAccOpenStatusList(
                l_statusParamses,
                l_strMrfAccountStatusDiv
                );        

        // 11 ) FX�����J�ݐ\�����ׂ��i�[����ArrayList�𐶐�����B
        List l_lisAccOpenApplyUnit = new Vector();
        
        // 12 ) get�Ώ�GFT�����J�ݏ󋵈ꗗ()�̖߂�l�̂����A
        // �\���Ώۍs(fromIndex�`toIndex)�̊ԁALoop���������{����
        // [�\���Ώۍs�ifromIndex�CtoIndex�j�̌v�Z]
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        
        WEB3PageIndexInfo l_pageIndexInfo = 
            new WEB3PageIndexInfo(
                l_arrOpenStatusParams,
                l_intPageIndex,
                l_intPageSize);
        
        // �w�肵�����R�[�h���擾(��{�݌v�ƈقȂ�A�u�y�[�W���߂��鏈���N���X�v�𗘗p�����̂�)
        GftAccountOpenStatusParams[] l_selectedAccpenStatusParams = 
            (GftAccountOpenStatusParams[])l_pageIndexInfo.getArrayReturned(GftAccountOpenStatusParams.class); 
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        //�g���A�J�E���g�}�l�[�W���擾����    
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        for(int i = 0; i < l_selectedAccpenStatusParams.length; i++)
        {
            GftAccountOpenStatusParams l_params = 
                l_selectedAccpenStatusParams[i];
            // 12 - 1 ) FX�������̈ꗗ���쐬����B 
            // [����] 
            // GFT�����J�ݏ󋵁F�@@GFT�����J�ݏ�Params
            WEB3FXAccInformationUnit[] l_arrFXAccInformationUnits = 
                this.createFXAccInfoList(l_params);
            
            // 12 - 2 ) �����Params�̈ꗗ���擾����B 
            //[����] 
            //�����N�G�X�g�f�[�^.FX�V�X�e���R�[�h��null�ȊO�̏ꍇ�� 
            // �،���ЃR�[�h�F�@@GFT�����J�ݏ�Params.�،���ЃR�[�h 
            // ���X�R�[�h�F�@@GFT�����J�ݏ�Params.���X�R�[�h 
            // ����敪 :  ���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
            // ���ʃR�[�h�F�@@GFT�����J�ݏ�Params.���ʃR�[�h
            //
            //�����N�G�X�g�f�[�^.FX�V�X�e���R�[�h��null�̏ꍇ��
            // �،���ЃR�[�h�F�@@GFT�����J�ݏ�Params.�،���ЃR�[�h 
            // ���X�R�[�h�F�@@GFT�����J�ݏ�Params.���X�R�[�h 
            // ����敪 :  0001 (�ב֕ۏ؋�)
            // ���ʃR�[�h�F�@@GFT�����J�ݏ�Params.���ʃR�[�h
            
            String l_strQuestionDiv = l_request.fxSystemCode;
            
            if(l_request.fxSystemCode == null){
            	
            	l_strQuestionDiv = WEB3QuestionDivDef.FX;
            	
            }
            
            QuestionAnswerParams[] l_arrQuestionAnswers = 
                l_controlService.getQuestionAnswers(
                    l_params.getInstitutionCode(),
                    l_params.getBranchCode(),
                    l_strQuestionDiv,
                    l_params.getOrderRequestNumber()
                    );
            
            // 12 - 3 ) FX������ӎ�����̈ꗗ���쐬����B 
            // [����] 
            // ����񓚈ꗗ�F�@@get�����()�̖߂�l
            WEB3FXTradeAgreementUnit[] l_arrTradeAgreementUnits = 
                this.createFXTradeAgreeQuestionInfoList(l_arrQuestionAnswers);
            
			String l_strOperationDiv;
            String l_mainAccountName;
            boolean l_blnIsMRFBalanceHolding = true;
            boolean l_mrfAccountFlag = true;   
            boolean l_mainAccountFlag = true;                          
            try
            {
				// 12 - 4 ) �ڋq�I�u�W�F�N�g���擾����B 
				// [����] 
				// �،���ЃR�[�h�F�@@GFT�����J�ݏ�Params.�،���ЃR�[�h 
				// ���X�R�[�h�F�@@GFT�����J�ݏ�Params.���X�R�[�h 
				// �����R�[�h�F�@@GFT�����J�ݏ�Params.�ڋq�R�[�h 
				WEB3GentradeMainAccount l_mainAccount = 
					l_accountManager.getMainAccount(
						l_params.getInstitutionCode(),
						l_params.getBranchCode(),
						l_params.getAccountCode()
						);
						
				// 12 - 5 ) �ڋq��MRF�c����ۗL���Ă��邩�ǂ������ʂ���B 
				// [����] 
				// �ڋq�F�@@get�ڋq()�̖߂�l		
				l_blnIsMRFBalanceHolding = this.isMRFBalanceHolding(l_mainAccount);
				
				// 12 - 6 ) �����敪���擾����B 
				// [����] 
				// GFT�����J�ݏ�Params�F�@@GFT�����J�ݏ�Params
				l_strOperationDiv = this.getOperationDiv(l_params);
				
				//get�ڋq()�̖߂�l.�ڋq��
				l_mainAccountName = l_mainAccount.getDisplayAccountName();
				
				//get�ڋq()�̖߂�l.MRF�����J�݋敪 == "�J�ݍ�"�̏ꍇ�Atrue���Z�b�g�B
				//�ȊO�Afalse���Z�b�g�B
				MainAccountRow l_mainAccountRow = 
					(MainAccountRow) l_mainAccount.getDataSourceObject();
				if(WEB3AccountOpenDef.OPEN.equals(
					l_mainAccountRow.getMrfAccOpenDiv()))
				{
					l_mrfAccountFlag = true;
				}
				else
				{
					l_mrfAccountFlag = false;
				}																				 					
            }
            catch(WEB3BaseException l_ex)
            {
				l_blnIsMRFBalanceHolding = false;
				l_strOperationDiv = WEB3AioAccOpenRemarkDef.ACCEPT_RESULT_CODE_90000009;
				l_mainAccountName = null;
				l_mrfAccountFlag = false;
				l_mainAccountFlag = false;  	           	         	
            }
                                  
            // 12 - 7 ) FX�����J�ݐ\�����׃C���X�^���X�𐶐�����B
            WEB3FXAccOpenApplyUnit l_accOpenApplyUnit = 
                new WEB3FXAccOpenApplyUnit();
            
            // 12 - 8 ) FX�����J�ݐ\�����ׂɈȉ��̃v���p�e�B���Z�b�g����B
            //
            //�I���\�t���O	���@@�i�iGFT�����J�ݏ�Params.�����J�ݏ󋵋敪 == "�����J�ݒ�" 
            //        �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�܂��́AGFT�����J�ݏ�Params.�����J�ݏ󋵋敪 == �h�_�E�����[�h�ρh�j
            //                                 �@@�@@�@@�@@       ���AGFT�����J�ݏ�Params.����M�敪 == "���M��"�j
            //    �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�܂��́AGFT�����J�ݏ�Params.�����J�ݏ󋵋敪 == "�����J�݃G���[" �̏ꍇ�Atrue���Z�b�g�B
            // 		�@@�@@�@@�ȊO�Afalse���Z�b�g�B
            
            if(l_mainAccountFlag)
            {
				if(((WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(l_params.getAccountOpenStatusDiv())
                    || WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE.equals(l_params.getAccountOpenStatusDiv()))
					&& WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_params.getSendRcvDiv()))
					|| WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(l_params.getAccountOpenStatusDiv()))
				{
					l_accOpenApplyUnit.selectableFlag = true;
				}
				else
				{
					l_accOpenApplyUnit.selectableFlag = false;
				}
            }
            else
            {
				l_accOpenApplyUnit.selectableFlag = false;
            }

            
            // ���ʃR�[�h		���@@GFT�����J�ݏ�Params.���ʃR�[�h
            l_accOpenApplyUnit.requestNumber = l_params.getOrderRequestNumber();
            
            // ���X�R�[�h		���@@GFT�����J�ݏ�Params.���X�R�[�h
            l_accOpenApplyUnit.branchCode = l_params.getBranchCode();
            
            // �ڋq�R�[�h �� GFT�����J�ݏ�Params.�ڋq�R�[�h
            String l_strAccountCodeToSet = l_params.getAccountCode();
            if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() > 6)
            {
                l_strAccountCodeToSet = l_params.getAccountCode().substring(0, 6);
            }
            l_accOpenApplyUnit.accountCode = l_strAccountCodeToSet;
            
            // �ڋq��		���@@get�ڋq()�̖߂�l.�ڋq��
            // get�ڋq()�ɂė�O�����������ꍇ�́A�uNULL�v���Z�b�g
			l_accOpenApplyUnit.accountName = l_mainAccountName;
			            	
            // �\������ �� GFT�����J�ݏ�Params.�쐬���t
            l_accOpenApplyUnit.applyTime = l_params.getCreatedTimestamp();
            
            // �X�e�[�^�X�敪	���@@GFT�����J�ݏ�Params.�����J�ݏ󋵋敪
            l_accOpenApplyUnit.statusDiv = l_params.getAccountOpenStatusDiv();
            
            // ����M�敪	���@@GFT�����J�ݏ�Params.����M�敪
            l_accOpenApplyUnit.sendRcvDiv = l_params.getSendRcvDiv();
            
            // (FX)���O(��)	���@@GFT�����J�ݏ�Params.���O(��)
            l_accOpenApplyUnit.fxLastName = l_params.getLastName();
            
            // (FX)���O(��)	���@@GFT�����J�ݏ�Params.���O(��)
            l_accOpenApplyUnit.fxFirstName = l_params.getFirstName();
            
            // (FX)���O�C��ID	���@@GFT�����J�ݏ�Params.GFT���O�C��ID
            l_accOpenApplyUnit.fxLoginId = l_params.getLoginId();
            
            // (FX)���[���A�h���X	���@@GFT�����J�ݏ�Params.GFT���[���A�h���X
            l_accOpenApplyUnit.fxMailAddress = l_params.getMailAddress();
            
            // ������敪    ���@@GFT�����J�ݏ�Params.������敪
            l_accOpenApplyUnit.agreementDiv = l_params.getAgreementDiv();
            
            // FX�������ꗗ	���@@createFX�������ꗗ()�̖߂�l
            l_accOpenApplyUnit.fxAccInformationList = l_arrFXAccInformationUnits;
            
            // ���l		���@@get�����敪()�̖߂�l
            // get�ڋq()�ɂė�O�����������ꍇ�́A�u90000009(��������)�v���Z�b�g
            l_accOpenApplyUnit.fxRemark = l_strOperationDiv;
            
			// MRF�c���t���O	���@@isMRF�c���ۗL()�̖߂�l
			//get�ڋq()�ɂė�O�����������ꍇ�́A�ufalse�v���Z�b�g
			l_accOpenApplyUnit.mrfBalanceFlag = l_blnIsMRFBalanceHolding;
				
			// MRF�����t���O	���@@get�ڋq()�̖߂�l.MRF�����J�݋敪 == "�J�ݍ�"�̏ꍇ�Atrue���Z�b�g�B
			// �ȊO�Afalse���Z�b�g�B
			//get�ڋq()�ɂė�O�����������ꍇ�́A�ufalse�v���Z�b�g
			l_accOpenApplyUnit.mrfAccountFlag = l_mrfAccountFlag;
            
            // FX������ӎ�����ꗗ�@@���@@createFX������ӎ�����ꗗ()�̖߂�l
            l_accOpenApplyUnit.fxTradeAgreementList = l_arrTradeAgreementUnits;

            //FX�V�X�e���R�[�h = GFT�����J�ݏ�Params.FX�V�X�e���R�[�h
            l_accOpenApplyUnit.fxSystemCode = l_params.getFxSystemCode();

            // 12 - 9 ) ArrayList��FX�����J�ݐ\�����ׂ�ǉ�����B 
            // [����] 
            // arg0�F�@@createFX�����J�ݐ\������()�̖߂�l
            l_lisAccOpenApplyUnit.add(l_accOpenApplyUnit);
        }
        
        // 13 ) FX�����J�ݐ\�����ׂ̔z��𐶐�����B
        WEB3FXAccOpenApplyUnit[] l_arrAccOpenApplyUnits = 
            new WEB3FXAccOpenApplyUnit[l_lisAccOpenApplyUnit.size()];
        l_lisAccOpenApplyUnit.toArray(l_arrAccOpenApplyUnits);
        
        // 14 ) ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFXAccOpenApplyListResponse l_response = 
            (WEB3AdminFXAccOpenApplyListResponse) l_request.createResponse();
        
        // 15 ) ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B      
        // ���X�R�[�h   ���@@���N�G�X�g�f�[�^.���X�R�[�h�̗v�f�� == 1 �̏ꍇ�A���̗v�f���Z�b�g
        //   �@@�@@�@@���N�G�X�g�f�[�^.���X�R�[�h�̗v�f�� > 1 �̏ꍇ�Anull���Z�b�g
        if(l_request.branchCodeList.length == 1)
        {
            l_response.branchCode = l_request.branchCodeList[0];
        }
        else
        {
            l_response.branchCode = null;
        }
        
        // �X�e�[�^�X�敪  ���@@���N�G�X�g�f�[�^.�X�e�[�^�X�敪
        l_response.statusDiv = l_request.statusDiv;
        
        // MRF�����󋵋敪  ���@@���N�G�X�g�f�[�^.MRF�����󋵋敪
        l_response.mrfAccountStatusDiv = l_request.mrfAccountStatusDiv;
        
        // �\�����i���j  ���@@���N�G�X�g�f�[�^.�\�����i���j
        l_response.applyHourFrom = l_request.applyHourFrom;
        
        // �\�����i���j  ���@@���N�G�X�g�f�[�^.�\�����i���j
        l_response.applyHourTo = l_request.applyHourTo;
        
        // ������敪        ���@@���N�G�X�g�f�[�^.������敪
        l_response.agreementDiv = l_request.agreementDiv;
        
        // FX�����J�ݐ\�����׈ꗗ ���@@toArray()�̖߂�l
        l_response.fxAccOpenApplyList = l_arrAccOpenApplyUnits;
        
        // ���y�[�W��  ���@@�����R�[�h�� / ���N�G�X�g�f�[�^.�y�[�W���\���s��
        //   �@@�@@�@@���v�Z���ʂ͏����_�ȉ���ʂ�؂�グ�������l�Ƃ���B
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        
        // �����R�[�h��  ���@@get�Ώ�GFT�����J�ݏ󋵈ꗗ()�̖߂�l.length
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        
        // �\���y�[�W�ԍ�  ���@@toIndex /�@@���N�G�X�g�f�[�^.�y�[�W���\���s��
        //   �@@�@@�@@���v�Z���ʂ͏����_�ȉ���ʂ�؂�グ�������l�Ƃ���B
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�X�e�[�^�X�X�V���͉��) <BR>
     * (�Ǘ���FX�����J�݊Ǘ�)�����J�݃X�e�[�^�X <BR>
     * �X�V���͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ���FX�����J�݊Ǘ��T�[�r�X)get�X�e�[�^�X�X�V���͉�ʁv�Q�� <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u�ב֕ۏ؋��T�[�r�X���f���i�Ǘ��ҁj/ �Ǘ��ҁEFX�����J�݊Ǘ��v) <BR>
     * �iget�X�e�[�^�X�X�V���͉�ʁjgetStatusUpdInput <BR>
     * : 1.6 getGFT�����J�ݏ�(String, String, String)�߂�l��null<BR>
     * �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== <BR>
     * @@param l_request - �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V���̓��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccOpenStatusUpdInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD554801A2
     */
    protected WEB3AdminFXAccOpenStatusUpdInputResponse getStatusUpdInput(
        WEB3AdminFXAccOpenStatusUpdInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getStatusUpdInput()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1 ) ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        // 2 ) ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) �Ǘ��Ҍ����̃`�F�b�N���s���B 
        // [����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ב֕ۏ؋��Ǘ�(�����Ǘ��E�����J�݊Ǘ�) 
        // is�X�V�F�@@true(�X�V����) 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
            true
            );
        
        // 4 ) ���X�����̃`�F�b�N���s���B 
        // [����] 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.FX��������.���X�R�[�h
        l_admin.validateBranchPermission(
            l_request.fxSearchConditionUnit.branchCode);
        
        // 5 ) �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        // 6 ) GFT�����J�ݏ󋵂��擾����B 
        // [����] 
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.FX��������.���X�R�[�h 
        // ���ʃR�[�h�F�@@���N�G�X�g�f�[�^.FX��������.���ʃR�[�h
        WEB3FXDataControlService l_controlService = 
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class);  
        GftAccountOpenStatusParams l_accOpenStatusParams = 
            l_controlService.getGFTAccountOpenStatus(
                l_strInstitutionCode,
                l_request.fxSearchConditionUnit.branchCode,
                l_request.fxSearchConditionUnit.requestNumber
                );
        
        if( l_accOpenStatusParams == null)
        {
            log.debug("GFT�����J�ݏ󋵎擾�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT�����J�ݏ󋵎擾�G���[");
        }
        
        // 7 ) �����J�ݒ��`�F�b�N
        // getGFT�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 != "�����J�ݒ�"�̏ꍇ�A
        // ����getGFT�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 != "�_�E�����[�h��"�̏ꍇ�A
        // ����getGFT�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪 != "�����J�݃G���["�̏ꍇ�A��O���X���[����B
        if(!WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
            l_accOpenStatusParams.getAccountOpenStatusDiv())
            && !WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE.equals(l_accOpenStatusParams.getAccountOpenStatusDiv())
            && !WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(l_accOpenStatusParams.getAccountOpenStatusDiv()))
        {
            log.debug("�����J�ݒ��`�F�b�N�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01807,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�ݒ��`�F�b�N�G���[");
        }
        
        // 8 ) FX�������̈ꗗ���쐬����B 
        // [����] 
        // GFT�����J�ݏ󋵁F�@@getGFT�����J�ݏ�()�̖߂�l
        WEB3FXAccInformationUnit[] l_arrAccInformationUnits = 
            this.createFXAccInfoList(l_accOpenStatusParams);
        
        // 9 ) ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFXAccOpenStatusUpdInputResponse l_response = 
            (WEB3AdminFXAccOpenStatusUpdInputResponse) l_request.createResponse();
        
        // 10 ) ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        
        // ���X�R�[�h   ���@@getGFT�����J�ݏ�()�̖߂�l.���X�R�[�h
        l_response.branchCode = l_accOpenStatusParams.getBranchCode();
        
        // �ڋq�R�[�h �� getGFT�����J�ݏ�()�̖߂�l.�ڋq�R�[�h
        String l_strAccountCodeToSet = l_accOpenStatusParams.getAccountCode();
        if (l_strAccountCodeToSet != null && l_strAccountCodeToSet.length() > 6)
        {
            l_strAccountCodeToSet = l_accOpenStatusParams.getAccountCode().substring(0, 6);
        }
        l_response.accountCode = l_strAccountCodeToSet;
        
        // (FX)���O�C��ID  ���@@getGFT�����J�ݏ�()�̖߂�l.GFT���O�C��ID
        l_response.fxLoginId = l_accOpenStatusParams.getLoginId();
        
        // (FX)���O(��)  ���@@getGFT�����J�ݏ�()�̖߂�l.���O(��)
        l_response.fxLastName = l_accOpenStatusParams.getLastName();
        
        // (FX)���O(��)  ���@@getGFT�����J�ݏ�()�̖߂�l.���O(��)
        l_response.fxFirstName = l_accOpenStatusParams.getFirstName();
        
        // (FX)���[���A�h���X  ���@@getGFT�����J�ݏ�()�̖߂�l.GFT���[���A�h���X
        l_response.fxMailAddress = l_accOpenStatusParams.getMailAddress();
        
        // FX�������ꗗ  ���@@createFX�������ꗗ()�̖߂�l
        l_response.fxAccInformationList = l_arrAccInformationUnits;
            
        // �X�e�[�^�X�敪  ���@@getGFT�����J�ݏ�()�̖߂�l.�����J�ݏ󋵋敪
        l_response.statusDiv = l_accOpenStatusParams.getAccountOpenStatusDiv();
        
        // ������敪�@@       ���@@getGFT�����J�ݏ�()�̖߂�l.������敪
        l_response.agreementDiv = l_accOpenStatusParams.agreement_div;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�X�e�[�^�X�X�V) <BR>
     * (�Ǘ���FX�����J�݊Ǘ�)�����J�݃X�e�[�^�X�X�V�m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ���FX�����J�݊Ǘ��T�[�r�X)validate�X�e�[�^�X�X�V�v�Q�� <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u�ב֕ۏ؋��T�[�r�X���f���i�Ǘ��ҁj/ �Ǘ��ҁEFX�����J�݊Ǘ��v) <BR>
     * validate�X�e�[�^�X�X�V <BR>
     * : 1.6 getGFT�����J�ݏ�(String, String, String)�߂�l��null<BR>
     * �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�m�F���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccOpenStatusUpdConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD554801C1
     */
    protected WEB3AdminFXAccOpenStatusUpdConfirmResponse validateStatusUpd(
        WEB3AdminFXAccOpenStatusUpdConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateStatusUpd()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1 ) ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        // 2 ) ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) �Ǘ��Ҍ����̃`�F�b�N���s���B 
        // [����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ב֕ۏ؋��Ǘ�(�����Ǘ��E�����J�݊Ǘ�) 
        // is�X�V�F�@@true(�X�V����) 
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
                true
                );
        
        // 4 ) ���X�����̃`�F�b�N���s���B 
        // [����] 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.FX��������.���X�R�[�h
        l_admin.validateBranchPermission(
            l_request.fxSearchConditionUnit.branchCode);
        
        // 5 ) �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        // 6 ) GFT�����J�ݏ󋵂��擾����B 
        // [����] 
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.FX��������.���X�R�[�h 
        // ���ʃR�[�h�F�@@���N�G�X�g�f�[�^.FX��������.���ʃR�[�h
        WEB3FXDataControlService l_controlService = 
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class);  
        GftAccountOpenStatusParams l_accOpenStatusParams = 
            l_controlService.getGFTAccountOpenStatus(
                l_strInstitutionCode,
                l_request.fxSearchConditionUnit.branchCode,
                l_request.fxSearchConditionUnit.requestNumber
                );
        
        if( l_accOpenStatusParams == null)
        {
            log.debug("GFT�����J�ݏ󋵎擾�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT�����J�ݏ󋵎擾�G���[");
        }
        
        // 7 ) �X�e�[�^�X�̕ύX���\���ǂ����`�F�b�N����B
        this.validateStatusChangePossible(
            l_accOpenStatusParams.getAccountOpenStatusDiv(),
            l_request.updatedStatusDiv,
            l_accOpenStatusParams.getAgreementDiv(),            
            l_request.updatedAgreementDiv);

        // 8 ) ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFXAccOpenStatusUpdConfirmResponse l_response = 
            (WEB3AdminFXAccOpenStatusUpdConfirmResponse) l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�X�e�[�^�X�X�V) <BR>
     * (�Ǘ���FX�����J�݊Ǘ�)�����J�݃X�e�[�^�X�X�V�����������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ���FX�����J�݊Ǘ��T�[�r�X)submit�X�e�[�^�X�X�V�v�Q�� <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u�ב֕ۏ؋��T�[�r�X���f���i�Ǘ��ҁj/ �Ǘ��ҁEFX�����J�݊Ǘ��v) <BR>
     * �isubmit�X�e�[�^�X�X�V�jsubmitStatusUpd <BR>
     * : 1.8 getGFT�����J�ݏ�(String, String, String)�߂�l��null<BR>
     * �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== <BR>
     * @@param l_request - �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�������N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminFXAccOpenStatusUpdCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD554801E0
     */
    protected WEB3AdminFXAccOpenStatusUpdCompleteResponse submitStatusUpd(
        WEB3AdminFXAccOpenStatusUpdCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitStatusUpd()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1 ) ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        // 2 ) ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 3 ) �Ǘ��Ҍ����̃`�F�b�N���s���B 
        // [����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ב֕ۏ؋��Ǘ�(�����Ǘ��E�����J�݊Ǘ�) 
        // is�X�V�F�@@true(�X�V����) 
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE,
                true
                );
        
        // 4 ) ���X�����̃`�F�b�N���s���B 
        // [����] 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.FX��������.���X�R�[�h
        l_admin.validateBranchPermission(
            l_request.fxSearchConditionUnit.branchCode);
        
        // 5 ) �Ïؔԍ��̃`�F�b�N���s���B 
        // [����] 
        // �p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);

        // 6 ) �Ǘ��҃R�[�h���擾����B
        String l_strAdminCode =  l_admin.getAdministratorCode();
        
        // 7 ) �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        // 8 ) GFT�����J�ݏ󋵂��擾����B 
        // [����] 
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        // ���X�R�[�h�F�@@���N�G�X�g�f�[�^.FX��������.���X�R�[�h 
        // ���ʃR�[�h�F�@@���N�G�X�g�f�[�^.FX��������.���ʃR�[�h
        WEB3FXDataControlService l_controlService = 
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class);  
        GftAccountOpenStatusParams l_accOpenStatusParams = 
            l_controlService.getGFTAccountOpenStatus(
                l_strInstitutionCode,
                l_request.fxSearchConditionUnit.branchCode,
                l_request.fxSearchConditionUnit.requestNumber
                );
        
        if( l_accOpenStatusParams == null)
        {
            log.debug("GFT�����J�ݏ󋵎擾�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT�����J�ݏ󋵎擾�G���[");
        }

        //get�����J��FX�V�X�e���R�[�h
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        String l_strSameTimeFxSystemCode =
            l_controlService.getSameTimeFxSystemCode(l_strInstitutionCode);

        //get��Е�FX�V�X�e������
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.FX��������.���X�R�[�h
        //FX�V�X�e���R�[�h�F�@@getGFT�����J�ݏ�()�̖߂�l.FX�V�X�e���R�[�h
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams = l_controlService.getCompFxCondition(
                l_strInstitutionCode,
                l_request.fxSearchConditionUnit.branchCode,
                l_accOpenStatusParams.getFxSystemCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // 9 ) �X�e�[�^�X�̕ύX���\���ǂ����`�F�b�N����B
        this.validateStatusChangePossible(
            l_accOpenStatusParams.getAccountOpenStatusDiv(),
            l_request.updatedStatusDiv,
            l_accOpenStatusParams.getAgreementDiv(),            
            l_request.updatedAgreementDiv);
        
        // 10 ) ���N�G�X�g�f�[�^.�X�V��X�e�[�^�X�敪 == "�����J�݊���"�̏ꍇ
        if(WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(
            l_request.updatedStatusDiv))
        { 
            // 10 - 1 ) FX�ڋq�e�[�u���Ƀf�[�^��o�^����B 
            // [����] 
            // GFT�����J�ݏ�Params�F�@@getGFT�����J�ݏ�Params()�̖߂�l 
            // �X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
            l_controlService.insertFXAccount(
                l_accOpenStatusParams,
                l_strAdminCode
                );

            //get�����J��FX�V�X�e���R�[�h()�̖߂�l��null�łȂ��ꍇ
            if (l_strSameTimeFxSystemCode != null)
            {
                //insert���������J��
                //GFT�����J�ݏ�Params�F�@@getGFT�����J�ݏ�Params()�̖߂�l
                //���������J��FX�V�X�e���R�[�h�F�@@get���������J��FX�V�X�e���R�[�h()�̖߂�l
                // �X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
                l_controlService.insertSimultaneousAccountOpen(
                    l_accOpenStatusParams,
                    l_strSameTimeFxSystemCode,
                    l_strAdminCode);
            }
            // 10 - 2 ) ���N�G�X�g�f�[�^.FX�������ꗗ�̗v�f����Loop����
            for(int i = 0; i < l_request.fxAccInformationList.length; i++)
            {
                WEB3FXAccInformationUnit l_accInformationUnit = 
                    l_request.fxAccInformationList[i];
                
                // 10 - 2 - 1 ) FX�����ԍ��e�[�u���Ƀf�[�^��o�^����B 
                // [����] 
                // GFT�����J�ݏ�Params�F�@@getGFT�����J�ݏ�Params()�̖߂�l 
                // FX�������F�@@�����Ώۂ�FX������� 
                // �X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
                //�����J��FX�V�X�e���R�[�h�F�@@get�����J��FX�V�X�e���R�[�h()�̖߂�l
                l_controlService.insertFXAccountCode(
                    l_accOpenStatusParams,
                    l_accInformationUnit,
                    l_strAdminCode,
                    l_strSameTimeFxSystemCode);
            }
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
            //�g���A�J�E���g�}�l�[�W���擾����    
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            // 10 - 3 ) �ڋq�I�u�W�F�N�g���擾����B 
            // [����] 
            // �،���ЃR�[�h�F�@@getGFT�����J�ݏ�()�̖߂�l.�،���ЃR�[�h 
            // ���X�R�[�h�F�@@getGFT�����J�ݏ�()�̖߂�l.���X�R�[�h 
            // �����R�[�h�F�@@getGFT�����J�ݏ�()�̖߂�l.�ڋq�R�[�h
            WEB3GentradeMainAccount l_mainAccount = 
                l_accountManager.getMainAccount(
                    l_accOpenStatusParams.getInstitutionCode(),
                    l_accOpenStatusParams.getBranchCode(),
                    l_accOpenStatusParams.getAccountCode()
                    );

            //�����J�݋敪�F
            //�P) get��Е�FX�V�X�e�������i�j�̖߂�l.�����敪���A���X�V��"�O�F���A���X�V���Ȃ�"�̏ꍇ
            //"0:���J��"
            //�Q) get��Е�FX�V�X�e�������i�j�̖߂�l.�����敪���A���X�V��"�P�F���A���X�V"�̏ꍇ
            //"1:�J�ݍ�"
            WEB3GentradeAccOpenDiv l_genAccOpenDiv = new WEB3GentradeAccOpenDiv();
            long l_lngAccountId = l_mainAccount.getAccountId();
            String l_strAccOpenDiv = "";
            if (WEB3AccOpenRealUpdateDef.REAL_UPDATE.equals(
                l_compFxConditionParams.getAccOpenRealUpdate()))
            {
                l_strAccOpenDiv = WEB3AccountOpenDef.OPEN;
            }
            else if (WEB3AccOpenRealUpdateDef.NOT_REAL_UPDATE.equals(
                l_compFxConditionParams.getAccOpenRealUpdate()))
            {
                l_strAccOpenDiv = WEB3AccountOpenDef.NOT_OPEN;
            }

            //get��Е�FX�V�X�e�������i�j�̖߂�l.������ʁ�"01�F(FX)"�̏ꍇ
            if (WEB3AccTypeDef.FX.equals(l_compFxConditionParams.getAccType()))
            {
                //updateFX�����J�݋敪(�،���ЃR�[�h : String, ���X�R�[�h : String,
                // �ڋq�R�[�h : String, �X�V��FX�����J�݋敪 : String, �X�V�҃R�[�h : String)
                //[����]
                // �،���ЃR�[�h�F�@@getGFT�����J�ݏ�()�̖߂�l.�،���ЃR�[�h
                // ���X�R�[�h�F�@@getGFT�����J�ݏ�()�̖߂�l.���X�R�[�h
                // �ڋq�R�[�h�F�@@getGFT�����J�ݏ�()�̖߂�l.�ڋq�R�[�h
                // �X�V��FX�����J�݋敪�F�@@�i�ȉ��̂Ƃ���j 
                // �@@�P) get��Е�FX�V�X�e�������i�j�̖߂�l.�����敪���A���X�V��"�O�F���A���X�V���Ȃ�"�̏ꍇ
                //�@@�@@�@@"0:���J��"
                // �@@�Q) get��Е�FX�V�X�e�������i�j�̖߂�l.�����敪���A���X�V��"�P�F���A���X�V"�̏ꍇ
                //�@@�@@�@@"1:�J�ݍ�"
                // �X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
                l_controlService.updateFXAccountOpenDiv(
                    l_accOpenStatusParams.getInstitutionCode(),
                    l_accOpenStatusParams.getBranchCode(),
                    l_accOpenStatusParams.getAccountCode(),
                    l_strAccOpenDiv,
                    l_strAdminCode);
            }

            //insert�����J�݋敪(����ID : long, ������� : String,
            //  �����J�݋敪 : String, �X�V�҃R�[�h : String)
            //�����J�݋敪�e�[�u��(acc_open_div)�ɍs��insert���s��
            // [����]
            // ����ID�F�ڋq�}�X�^�e�[�u�����A�����̏،���ЃR�[�h�A���X�R�[�h�A
            //         �����R�[�h�ɊY���������ID���擾���ԋp����B
            // ������ʁFget��Е�FX�V�X�e�������i�j�̖߂�l.�������
            // �����J�݋敪�F�i�ȉ��̂Ƃ���j
            // �P) get��Е�FX�V�X�e�������i�j�̖߂�l.�����敪���A���X�V��"�O�F���A���X�V���Ȃ�"�̏ꍇ
            //�@@ �@@"0:���J��"
            // �Q) get��Е�FX�V�X�e�������i�j�̖߂�l.�����敪���A���X�V��"�P�F���A���X�V"�̏ꍇ
            //�@@�@@�@@"1:�J�ݍ�"
            //�X�V�҃R�[�h�Fget�Ǘ��҃R�[�h()�̖߂�l
            l_genAccOpenDiv.insertAccOpenDiv(
                l_lngAccountId,
                l_compFxConditionParams.getAccType(),
                l_strAccOpenDiv,
                l_strAdminCode);

            //get�����J��FX�V�X�e���R�[�h(�،���ЃR�[�h : String)�̖߂�l��null�łȂ��ꍇ
            if (l_strSameTimeFxSystemCode != null)
            {
                //insert�����J�݋敪(����ID : long, ������� : String,
                //  �����J�݋敪 : String, �X�V�҃R�[�h : String)
                //�����J�݋敪�e�[�u��(acc_open_div)�ɍs��insert���s��
                //[����]
                // ����ID�F�ڋq�}�X�^�e�[�u�����A�����̏،���ЃR�[�h�A���X�R�[�h�A
                //        �����R�[�h�ɊY���������ID���擾���ԋp����B
                // ������ʁF"02�FCFD"
                // �����J�݋敪�F�i�ȉ��̂Ƃ���j
                // �P) get��Е�FX�V�X�e�������i�j�̖߂�l.�����敪���A���X�V��"�O�F���A���X�V���Ȃ�"�̏ꍇ
                //�@@ "0:���J��"
                // �Q) get��Е�FX�V�X�e�������i�j�̖߂�l.�����敪���A���X�V��"�P�F���A���X�V"�̏ꍇ
                //�@@ "1:�J�ݍ�"
                //�X�V�҃R�[�h�Fget�Ǘ��҃R�[�h()�̖߂�l
                l_genAccOpenDiv.insertAccOpenDiv(
                    l_lngAccountId,
                    WEB3AccTypeDef.CFD,
                    l_strAccOpenDiv,
                    l_strAdminCode);
            }
        }
        // 11 ) GFT�����J�ݏ󋵃e�[�u�����X�V����B 
        // [����] 
        // GFT�����J�ݏ�Params�F�@@getGFT�����J�ݏ�Params()�̖߂�l 
        // �X�V��X�e�[�^�X�敪�F�@@���N�G�X�g�f�[�^.�X�V��X�e�[�^�X�敪 
        // �X�V��FX�������ꗗ�F�@@���N�G�X�g�f�[�^.FX�������ꗗ 
        // �X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()�̖߂�l
        //�X�V�������敪�F�@@���N�G�X�g�f�[�^.�X�V�������敪
        l_controlService.updateGFTAccountOpenStatus(
            l_accOpenStatusParams,
            l_request.updatedStatusDiv,
            l_request.fxAccInformationList,
            l_strAdminCode,
            l_request.updatedAgreementDiv);
        
        // 12 ) ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminFXAccOpenStatusUpdCompleteResponse l_response = 
            (WEB3AdminFXAccOpenStatusUpdCompleteResponse) l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create��������������) <BR>
     * ����������������쐬����B <BR>
     * <BR>
     * �P�j��̌�������������(�FString)�𐶐�����B <BR>
     * <BR>
     * �Q�j�،���Џ��������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += " institution_code = ? "<BR>
     * <BR>
     * �R�j���X���������������ɒǉ�����B <BR>
     * �p�����[�^.���X�R�[�h[]�̗v�f����"?"��ǉ�����B <BR>
     * <BR>
     * �������������� += "and branch_code in (?,?,,,) "<BR>
     * <BR>
     * �S�j�p�����[�^.�X�e�[�^�X�敪 != null�̏ꍇ�A <BR>
     * �����J�ݏ󋵋敪�����������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and account_open_status_div = ? "<BR>
     * <BR>
     * �T�j�p�����[�^.�\�����i���j != null�̏ꍇ�A <BR>
     * �\�����̉��������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and to_char(created_timestamp, <BR>
     * 'YYYYMMDDHH24') >= ? "<BR>
     * <BR>
     * �U�j�p�����[�^.�\�����i���j != null�̏ꍇ�A <BR>
     * �\�����̉��������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and to_char(created_timestamp, <BR>
     * 'YYYYMMDDHH24') < ? "<BR>
     * <BR>
     * �V�j�p�����[�^.������敪 != null�̏ꍇ�A<BR>
     * ������敪�����������ɒǉ�����B<BR>
     * <BR>
     * �������������� += "and agreement_div = ? "<BR>
     * <BR>
     * �W�j�쐬�������������������ԋp����B
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCodes - ���X�R�[�h�̔z��
     * @@param l_strStatusDiv - �X�e�[�^�X�敪
     * 0�F�����J�ݒ� 1�F�����J�݊��� 2�F�����J�݃G���[ 3:�_�E�����[�h�� 9�F�폜
     * @@param l_strApplyHourFrom - �\�����i���j (YYYYMMDDhh)
     * @@param l_strApplyHourTo - �\�����i���j (YYYYMMDDhh)
     * @@param l_strAgreementDiv - ������敪
     * 0�F���� 1�F�L�� null�F�S��
     * @@return String
     * @@roseuid 41BD62BE01A2
     */
    protected String createQueryString(String l_strInstitutionCode,
        String[] l_strBranchCodes, String l_strStatusDiv,
        String l_strApplyHourFrom, String l_strApplyHourTo, String l_strAgreementDiv)
    {
        final String STR_METHOD_NAME = "createQueryString()";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBranchCodes == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j��̌�������������(�FString)�𐶐�����B
        String l_strWhere = "";
        
        // �Q�j�،���Џ��������������ɒǉ�����B 
        // �������������� += " institution_code = ? " 
        l_strWhere += " institution_code = ? ";
        
        // �R�j���X���������������ɒǉ�����B 
        // �p�����[�^.���X�R�[�h[]�̗v�f����"?"��ǉ�����B 
        // �������������� += "and branch_code in (?,?,,,) "
        l_strWhere += " and branch_code in ( ? ";
        
        for(int i = 1; i < l_strBranchCodes.length; i++)
        {
            l_strWhere += " , ? ";
        }
        
        l_strWhere += " ) ";
        
        // �S�j�p�����[�^.�X�e�[�^�X�敪 != null�̏ꍇ�A 
        // �����J�ݏ󋵋敪�����������ɒǉ�����B 
        // �������������� += "and account_open_status_div = ? " 
        if(!WEB3StringTypeUtility.isEmpty(l_strStatusDiv))
        {
            l_strWhere += " and account_open_status_div = ? ";
        }
        
        // �T�j�p�����[�^.�\�����i���j != null�̏ꍇ�A 
        // �\�����̉��������������ɒǉ�����B 
        // �������������� += "and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? "
        if(!WEB3StringTypeUtility.isEmpty(l_strApplyHourFrom))
        {
            l_strWhere += " and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? ";
        }
        
        // �U�j�p�����[�^.�\�����i���j != null�̏ꍇ�A 
        // �\�����̉��������������ɒǉ�����B 
        // �������������� += "and to_char(created_timestamp, 'YYYYMMDDHH24') < ? " 
        if(!WEB3StringTypeUtility.isEmpty(l_strApplyHourTo))
        {
            l_strWhere += " and to_char(created_timestamp, 'YYYYMMDDHH24') < ? ";
        }
        
        // �V�j�p�����[�^.������敪 != null�̏ꍇ�A
        //�@@������敪�����������ɒǉ�����B
        //  �������������� += "and agreement_div = ? "
        if (null != l_strAgreementDiv)
        {
            l_strWhere += " and agreement_div = ? ";
        }
        
        // �W�j�쐬�������������������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strWhere;
    }

    /**
     * (create���������f�[�^�R���e�i) <BR>
     * ����������������쐬����B <BR>
     * <BR>
     * �P�jArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j�،���Џ�����ǉ�����B <BR>
     * �p�����[�^.�،���ЃR�[�h��ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �R�j���X������ǉ�����B <BR>
     * �p�����[�^.���X�R�[�h[]�̑S�v�f��ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �S�j�p�����[�^.�X�e�[�^�X�敪 != null�̏ꍇ�A <BR>
     * �p�����[�^.�X�e�[�^�X�敪��ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �T�j�p�����[�^.�\�����i���j != null�̏ꍇ�A <BR>
     * �p�����[�^.�\�����i���j��ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �U�j�p�����[�^.�\�����i���j != null�̏ꍇ�A <BR>
     * �p�����[�^.�\�����i���j��ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �V�j�p�����[�^.������敪 != null�̏ꍇ�A<BR>
     * �p�����[�^.������敪��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �W�j��������ArrayList.toArray()�̖߂�l��ԋp����B
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCodes - ���X�R�[�h�̔z��
     * @@param l_strStatusDiv - �X�e�[�^�X�敪
     * 0�F�����J�ݒ� 1�F�����J�݊��� 2�F�����J�݃G���[ 3:�_�E�����[�h�� 9�F�폜
     * @@param l_strApplyHourFrom - �\�����i���j (YYYYMMDDhh)
     * @@param l_strApplyHourTo - �\�����i���j (YYYYMMDDhh)
     * @@param l_strAgreementDiv - ������敪
     * 0�F���� 1�F�L�� null�F�S��
     * @@return String[]
     * @@roseuid 41BD675401A2
     */
    protected String[] createQueryContainer(String l_strInstitutionCode,
        String[] l_strBranchCodes, String l_strStatusDiv,
        String l_strApplyHourFrom, String l_strApplyHourTo, String l_strAgreementDiv)
    {
        final String STR_METHOD_NAME = "createQueryContainer()";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBranchCodes == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�jArrayList�𐶐�����B 
        List l_lisVars = new Vector();
        
        // �Q�j�،���Џ�����ǉ�����B 
        // �p�����[�^.�،���ЃR�[�h��ArrayList�ɒǉ�����B 
        l_lisVars.add(l_strInstitutionCode);
        
        // �R�j���X������ǉ�����B 
        // �p�����[�^.���X�R�[�h[]�̑S�v�f��ArrayList�ɒǉ�����B 
        for(int i = 0; i < l_strBranchCodes.length; i++)
        {
            l_lisVars.add(l_strBranchCodes[i]);
        }
        
        // �S�j�p�����[�^.�X�e�[�^�X�敪 != null�̏ꍇ�A 
        // �p�����[�^.�X�e�[�^�X�敪��ArrayList�ɒǉ�����B
        if(!WEB3StringTypeUtility.isEmpty(l_strStatusDiv))
        {
            l_lisVars.add(l_strStatusDiv);
        }
        
        // �T�j�p�����[�^.�\�����i���j != null�̏ꍇ�A 
        // �p�����[�^.�\�����i���j��ArrayList�ɒǉ�����B 
        if(!WEB3StringTypeUtility.isEmpty(l_strApplyHourFrom))
        {
            l_lisVars.add(l_strApplyHourFrom);
        }
        
        // �U�j�p�����[�^.�\�����i���j != null�̏ꍇ�A 
        // �p�����[�^.�\�����i���j��ArrayList�ɒǉ�����B 
        if(!WEB3StringTypeUtility.isEmpty(l_strApplyHourTo))
        {
            l_lisVars.add(l_strApplyHourTo);
        }
        
        // �V�j�p�����[�^.������敪 != null�̏ꍇ�A
        // �p�����[�^.������敪��ArrayList�ɒǉ�����B
        if (null != l_strAgreementDiv)
        {
            l_lisVars.add(l_strAgreementDiv);
        }
        
        // �W�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        String[] l_arrVars = new String[l_lisVars.size()];
        l_lisVars.toArray(l_arrVars);
        
        log.exiting(STR_METHOD_NAME);
        return l_arrVars;
    }

    /**
     * (create�\�[�g����) <BR>
     * �\�[�g�������쐬����B <BR>
     * <BR>
     * �P�j��̃\�[�g����������(�FString)�𐶐�����B <BR>
     * <BR>
     * �Q�j�ȉ��̃\�[�g������\���\�[�g������������쐬����B <BR>
     * �쐬���t�@@�~�� <BR>
     * <BR>
     * �\�[�g���������� = " created_timestamp desc" <BR>
     * <BR>
     * �R�j�쐬�����\�[�g�����������ԋp����B
     * 
     * @@return String
     * @@roseuid 41BD6B0C0377
     */
    protected String createSortCond()
    {
        final String STR_METHOD_NAME = "createSortCond()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j��̃\�[�g����������(�FString)�𐶐�����B
        String l_strSort = "";
        
        // �Q�j�ȉ��̃\�[�g������\���\�[�g������������쐬����B 
        // �쐬���t�@@�~�� 
        // �\�[�g���������� = " created_timestamp  desc" 
        l_strSort += " created_timestamp desc";
        
        // �R�j�쐬�����\�[�g�����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strSort;
    }

    /**
     * (get�Ώ�GFT�����J�ݏ󋵈ꗗ) <BR>
     * ������MRF�����J�݋敪�ɊY������ <BR>
     * GFT�����J�ݏ󋵂̈ꗗ��ԋp����B <BR>
     * <BR>
     * �P�j�p�����[�^.MRF�����J�݋敪 == null�̏ꍇ�A <BR>
     * �p�����[�^.GFT�����J�ݏ󋵈ꗗ��ԋp���ďI������B <BR>
     * <BR>
     * �Q�jArrayList�𐶐�����B <BR>
     * <BR>
     * �R�j�p�����[�^.GFT�����J�ݏ󋵈ꗗ�̗v�f�����A <BR>
     * �ȉ��̏������J��Ԃ��B <BR>
     * �R�|�P�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���\�b�h���R�[������B <BR>
     * <BR>
     * [get�ڋq()�ɃZ�b�g����p�����[�^] <BR>
     * �،���ЃR�[�h�F GFT�����J�ݏ�.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F GFT�����J�ݏ�.���X�R�[�h <BR>
     * �����R�[�h�F GFT�����J�ݏ�.�ڋq�R�[�h <BR>
     * <BR>
     * �R�|�Q�j�R�|�P�j�̖߂�l.MRF�����J�݋敪 == �p�����[�^.MRF�����J�݋敪�̏ꍇ�A<BR>
     * ��������ArrayList��GFT�����J�ݏ󋵂�ǉ�����B <BR>
     * <BR>
     * �S�j��������ArrayList.toArray()�̖߂�l��ԋp����B
     * 
     * @@param l_gftAccountOpenSatusParamses - GFT�����J�ݏ�Params�I�u�W�F�N�g�̔z��
     * @@param l_strMrfAccountStatusDiv - 1�F�J�� 2�F���J��
     * @@return GftAccountOpenStatusParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41BD7F1A0044
     */
    protected GftAccountOpenStatusParams[] getObjGFTAccOpenStatusList(
        GftAccountOpenStatusParams[] l_gftAccountOpenSatusParamses,
        String l_strMrfAccountStatusDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getObjGFTAccOpenStatusList()";
        log.entering(STR_METHOD_NAME);
        
        if (l_gftAccountOpenSatusParamses == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j�p�����[�^.MRF�����J�݋敪 == null�̏ꍇ�A 
        // �p�����[�^.GFT�����J�ݏ󋵈ꗗ��ԋp���ďI������B
        if(WEB3StringTypeUtility.isEmpty(l_strMrfAccountStatusDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return l_gftAccountOpenSatusParamses;
        }
        
        // �Q�jArrayList�𐶐�����B
        List l_lisAccountOpenStatusParams = new Vector();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        //�g���A�J�E���g�}�l�[�W���擾����    
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        // �R�j�p�����[�^.GFT�����J�ݏ󋵈ꗗ�̗v�f�����A 
        // �ȉ��̏������J��Ԃ��B 
        for(int i = 0; i < l_gftAccountOpenSatusParamses.length; i++)
        {
            GftAccountOpenStatusRow l_accountOpenStatusRow = 
                (GftAccountOpenStatusRow) l_gftAccountOpenSatusParamses[i];
            
            // �R�|�P�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���\�b�h���R�[������B 
            // �@@[get�ڋq()�ɃZ�b�g����p�����[�^] 
            // �@@�@@�،���ЃR�[�h�F�@@GFT�����J�ݏ�.�،���ЃR�[�h 
            // �@@�@@���X�R�[�h�F�@@GFT�����J�ݏ�.���X�R�[�h 
            // �@@�@@�����R�[�h�F�@@GFT�����J�ݏ�.�ڋq�R�[�h 
            
            try
            {
				WEB3GentradeMainAccount l_mainAccount = 
					l_accountManager.getMainAccount(
						l_accountOpenStatusRow.getInstitutionCode(),
						l_accountOpenStatusRow.getBranchCode(),
						l_accountOpenStatusRow.getAccountCode()
						);
				// �R�|�Q�j�R�|�P�j�̖߂�l.MRF�����J�݋敪 == 
				// �@@�p�����[�^.MRF�����J�݋敪�̏ꍇ�A 
				// �@@��������ArrayList��GFT�����J�ݏ󋵂�ǉ�����B
				MainAccountRow l_mainAccountRow = 
					(MainAccountRow) l_mainAccount.getDataSourceObject();
				if(l_strMrfAccountStatusDiv.equals(l_mainAccountRow.getMrfAccOpenDiv()))
				{
					l_lisAccountOpenStatusParams.add(l_accountOpenStatusRow);
				}
            }
            catch(WEB3BaseException l_ex)
            {
            	if(l_strMrfAccountStatusDiv.equals(WEB3AccountOpenDef.NOT_OPEN))
            	{
					l_lisAccountOpenStatusParams.add(l_accountOpenStatusRow);
            	}
            }           

        }
        
        // �S�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        GftAccountOpenStatusParams[] l_arrAccountOpenStatusParams = null;
        //���ԋp����z��̗v�f����0�̏ꍇ�́A�v�f����0�̔z�񂪕ԋp����B 
        if(l_lisAccountOpenStatusParams.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            l_arrAccountOpenStatusParams = new GftAccountOpenStatusParams[0];
            return l_arrAccountOpenStatusParams;
        }
        l_arrAccountOpenStatusParams = 
            new GftAccountOpenStatusParams[l_lisAccountOpenStatusParams.size()];
        l_lisAccountOpenStatusParams.toArray(l_arrAccountOpenStatusParams);
         
        log.exiting(STR_METHOD_NAME);
        return l_arrAccountOpenStatusParams;
    }

    /**
     * (createFX�������ꗗ) <BR>
     * ������GFT�����J�ݏ󋵂��AFX�������̈ꗗ���쐬����B <BR>
     * <BR>
     * �P�jArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j1���ʉ݃R�[�X�̌������쐬 <BR>
     * �@@FX�������C���X�^���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �R�[�X�敪�F "1���ʉ݃R�[�X" <BR>
     * �����ԍ��F �p�����[�^.GFT�����J�ݏ�.�����ԍ�(1���ʉ݃R�[�X) <BR>
     * <BR>
     * �AFX�������C���X�^���X.�����ԍ���null�łȂ��ꍇ�A <BR>
     *   ��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B <BR>
     * <BR>
     * �R�j10���ʉ݃R�[�X�̌������쐬 <BR>
     * �@@FX�������C���X�^���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �R�[�X�敪�F "10���ʉ݃R�[�X" <BR>
     * �����ԍ��F �p�����[�^.GFT�����J�ݏ�.�����ԍ�(10���ʉ݃R�[�X) <BR>
     * <BR>
     * �AFX�������C���X�^���X.�����ԍ���null�łȂ��ꍇ�A <BR>
     *   ��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B <BR>
     * <BR>
     * �S�j�A�g�p�����ԍ��̌������쐬<BR>
     * �@@�@@FX�������C���X�^���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�R�[�X�敪�F�@@"CFD�R�[�X"<BR>
     * �@@�@@�����ԍ��F�@@�p�����[�^.GFT�����J�ݏ�.�A�g�p�����ԍ�<BR>
     * <BR>
     * �@@�AFX�������C���X�^���X.�����ԍ���null�łȂ��ꍇ�A <BR>
     *     ��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �T�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * 
     * @@param l_gftAccountOpenSatusParams - GFT�����J�ݏ�Param�I�u�W�F�N�g
     * @@return WEB3FXAccInformationUnit[]
     * @@roseuid 41BD8EA30214
     */
    protected WEB3FXAccInformationUnit[] createFXAccInfoList(
        GftAccountOpenStatusParams l_gftAccountOpenSatusParams)
    {
        final String STR_METHOD_NAME = "createFXAccInfoList()";
        log.entering(STR_METHOD_NAME);
        
        if (l_gftAccountOpenSatusParams == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�jArrayList�𐶐�����B 
        List l_lisFXAccInformationUnits = new Vector();
        
        // �Q�j1���ʉ݃R�[�X�̌������쐬 
        // �Q�|�P�jFX�������C���X�^���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g����B
        // �@@�R�[�X�敪�F�@@"1���ʉ݃R�[�X" 
        // �@@�����ԍ��F�@@�p�����[�^.GFT�����J�ݏ�.�����ԍ�(1���ʉ݃R�[�X) 
        WEB3FXAccInformationUnit l_accInfoUnit01 = 
            new WEB3FXAccInformationUnit();
        l_accInfoUnit01.fxCourseDiv = 
            WEB3GftTransStatusCourseDivDef.ONE_COSE;
        l_accInfoUnit01.fxAccountCode = 
            l_gftAccountOpenSatusParams.getFxAccountCode01();
        
        // �Q�|�Q�jFX�������C���X�^���X.�����ԍ���null�łȂ��ꍇ�A
        //         ��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
        if (l_accInfoUnit01.fxAccountCode != null)
        {
            l_lisFXAccInformationUnits.add(l_accInfoUnit01);
        }

        // �R�j10���ʉ݃R�[�X�̌������쐬 
        // �R�|�P�jFX�������C���X�^���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g����B 
        // �@@�R�[�X�敪�F�@@"10���ʉ݃R�[�X" 
        // �@@�����ԍ��F�@@�p�����[�^.GFT�����J�ݏ�.�����ԍ�(10���ʉ݃R�[�X) 
        WEB3FXAccInformationUnit l_accInfoUnit10 = 
            new WEB3FXAccInformationUnit();
        l_accInfoUnit10.fxCourseDiv = WEB3GftTransStatusCourseDivDef.TEN_COSE;
        l_accInfoUnit10.fxAccountCode = 
            l_gftAccountOpenSatusParams.getFxAccountCode10();
        
        // �R�|�Q�jFX�������C���X�^���X.�����ԍ���null�łȂ��ꍇ�A
        //         ��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
        if (l_accInfoUnit10.fxAccountCode != null)
        {
            l_lisFXAccInformationUnits.add(l_accInfoUnit10);
        }

        //�S�j�A�g�p�����ԍ��̌������쐬
        //�@@FX�������C���X�^���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g����B
        //�R�[�X�敪�F�@@"CFD�R�[�X"
        //�����ԍ��F�@@�p�����[�^.GFT�����J�ݏ�.�A�g�p�����ԍ�
        WEB3FXAccInformationUnit l_accInfoUnitCFD =
            new WEB3FXAccInformationUnit();
        l_accInfoUnitCFD.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE;
        l_accInfoUnitCFD.fxAccountCode =
            l_gftAccountOpenSatusParams.getExtAccountCode();
        //�AFX�������C���X�^���X.�����ԍ���null�łȂ��ꍇ�A
        //  ��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
        if (l_accInfoUnitCFD.fxAccountCode != null)
        {
            l_lisFXAccInformationUnits.add(l_accInfoUnitCFD);
        }

        // �T�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3FXAccInformationUnit[] l_arrFXAccInformationUnits = 
            new WEB3FXAccInformationUnit[l_lisFXAccInformationUnits.size()];
        l_lisFXAccInformationUnits.toArray(l_arrFXAccInformationUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_arrFXAccInformationUnits;
    }

    /**
     * (createFX������ӎ�����ꗗ) <BR>
     * �����̎���񓚈ꗗ���AFX������ӎ�����̈ꗗ���쐬����B <BR>
     * <BR>
     * �P�jArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j�p�����[�^.����񓚈ꗗ�̗v�f�����A�ȉ��̏������J��Ԃ��B <BR>
     * �Q�|�P�jFX������ӎ�����C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B�Z�b�g���s���B <BR>
     * ����ԍ� = �����Params.����ԍ� <BR>
     * ������e = null <BR>
     * ����� = �����Params.����� <BR>
     * <BR>
     * �Q�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B <BR>
     * <BR>
     * �R�j��������ArrayList.toArray()�̖߂�l��ԋp����B
     * 
     * @@param l_questionAnswerParamses - �����Params�I�u�W�F�N�g�̔z��
     * @@return WEB3FXTradeAgreementUnit[]
     * @@roseuid 41BD977802B3
     */
    protected WEB3FXTradeAgreementUnit[] createFXTradeAgreeQuestionInfoList(
        QuestionAnswerParams[] l_questionAnswerParamses)
    {
        final String STR_METHOD_NAME = "createFXTradeAgreeQuestionInfoList()";
        log.entering(STR_METHOD_NAME);
        
        if (l_questionAnswerParamses == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�jArrayList�𐶐�����B 
        List l_lisFXTradeAgreementUnits = new Vector();
        
        // �Q�j�p�����[�^.����񓚈ꗗ�̗v�f�����A 
        // �ȉ��̏������J��Ԃ��B 
        for(int i = 0; i < l_questionAnswerParamses.length; i++)
        {
            QuestionAnswerRow l_questionAnswerRow = l_questionAnswerParamses[i];
            
            // �Q�|�P�jFX������ӎ�����C���X�^���X�𐶐�����B 
            WEB3FXTradeAgreementUnit l_tradeAgreementUnit = 
                new WEB3FXTradeAgreementUnit();
            
            // �Q�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B�Z�b�g���s���B 
            // �@@����ԍ� = �����Params.����ԍ� 
            // �@@������e = null 
            // �@@����� = �����Params.����� 
            l_tradeAgreementUnit.questionNumber = 
                l_questionAnswerRow.getQuestionNo();
            l_tradeAgreementUnit.questionContents = null;
            l_tradeAgreementUnit.questionAnswer = 
                l_questionAnswerRow.getQuestionAnswer();

            // �Q�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
            l_lisFXTradeAgreementUnits.add(l_tradeAgreementUnit);
        } 

        // �R�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3FXTradeAgreementUnit[] l_arrFXTradeAgreementUnits = 
            new WEB3FXTradeAgreementUnit[l_lisFXTradeAgreementUnits.size()];
        l_lisFXTradeAgreementUnits.toArray(l_arrFXTradeAgreementUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_arrFXTradeAgreementUnits;
    }

    /**
     * (isMRF�c���ۗL) <BR>
     * �����̌ڋq��MRF�c����ۗL���Ă��邩�ǂ������ʂ���B <BR>
     * <BR>
     * [�߂�l] <BR>
     * false�F �ۗL���Ă��Ȃ� <BR>
     * true�F �ۗL���Ă��� <BR>
     * <BR>
     * �P�jDB���� <BR>
     * �ȉ��̏����ŁA�ڋq����c��(tp_cash_balance)����������B <BR>
     * <BR>
     * ����ID = �p�����[�^.�ڋq.����ID <BR>
     * �⏕����ID = �⏕����(*1).�⏕����ID <BR>
     * <BR>
     * �Q�j��������.MRF�c�� == (null or 0)�̏ꍇ�Afalse��ԋp����B<BR>
     * �ȊO�Atrue��ԋp����B <BR>
     * <BR>
     * (*1)�p�����[�^.�ڋq.getSubAccount(SubAccountTypeEnum.�����������)�ɂ� <BR>
     * �擾�����⏕����
     * 
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * @@return Boolean
     * @@throws WEB3BaseException
     * @@roseuid 41BE44B8027C
     */
    protected boolean isMRFBalanceHolding(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isMRFBalanceHolding()";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�jDB���� 
        // �ȉ��̏����ŁA�ڋq����c��(tp_cash_balance)����������B 
        // ����ID = �p�����[�^.�ڋq.����ID 
        // �⏕����ID = �⏕����(*1).�⏕����ID 
        List l_lisRow = null;
        try
        {   
            // (*1)�p�����[�^.�ڋq.getSubAccount(SubAccountTypeEnum.�����������)�ɂ� 
            // �擾�����⏕����
            SubAccount l_subAccount = 
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            String l_strWhere = " account_id = ? and sub_account_id = ? ";
            Object[] l_objVars = {
                new Long(l_mainAccount.getAccountId()),
                new Long(l_subAccount.getSubAccountId())
                };
            l_lisRow = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    TpCashBalanceRow.TYPE,
                    l_strWhere,                    
                    null,
                    l_objVars);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // �Q�j��������.MRF�c�� == (null or 0)�̏ꍇ�Afalse��ԋp����B 
        // �ȊO�Atrue��ԋp����B 
        if(l_lisRow != null 
            && l_lisRow.size() > 0
            && ((TpCashBalanceRow) l_lisRow.get(0)).getMrfBalance() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (get�����敪) <BR>
     * ������GFT�����J�ݏ�Params���A���݂̏����敪�𔻕ʂ��A�ԋp����B <BR>
     * ���ԋp�����l�́AFX�����J�ݐ\������.���l�̃A�C�e����`���Q�ƁB <BR>
     * <BR>
     * [�����J�ݏ󋵋敪 == "�����J�ݍ�"�̏ꍇ]
     * "�����J�݊���"��ԋp����B<BR>
     * <BR>
     * [�i�����J�ݏ󋵋敪 == "�����J�ݒ�" �܂��� "�_�E�����[�h��")����<BR>
     * ����M�敪 == "���M��"�̏ꍇ]<BR>
     * "�����J�ݒ�"��ԋp����B <BR>
     * <BR>
     * [�����J�ݏ󋵋敪 == "�����J�݃G���[" ����"����M�敪" == "��M��"�̏ꍇ] <BR>
     * �p�����[�^.GFT�����J�ݏ�Params.��t���ʃR�[�h��ԋp����B <BR>
     * <BR>
     * [�����J�݋敪 == "�폜"�̏ꍇ]"�폜"��ԋp����B <BR>
     * <BR>
     * [��L�ȊO�̏ꍇ] <BR>
     * "�V�X�e���G���["��ԋp����B
     * 
     * @@param l_gftAccountOpenSatusParams - GFT�����J�ݏ�Params�I�u�W�F�N�g
     * @@return String
     * @@roseuid 41CBF57802ED
     */
    protected String getOperationDiv(
        GftAccountOpenStatusParams l_gftAccountOpenSatusParams)
    {
        final String STR_METHOD_NAME = "getOperationDiv()";
        log.entering(STR_METHOD_NAME);
        
        if (l_gftAccountOpenSatusParams == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strOpenStatusDiv = l_gftAccountOpenSatusParams.getAccountOpenStatusDiv();
        String l_strSendRcvDiv = l_gftAccountOpenSatusParams.getSendRcvDiv();
        
        // [�����J�ݏ󋵋敪 == "�����J�ݍ�"�̏ꍇ] 
        // "�����J�݊���"��ԋp����B 
        if(WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(
                l_strOpenStatusDiv))           
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AioAccOpenRemarkDef.ACCEPT_RESULT_CODE_00000000;
        }

        // [�i�����J�ݏ󋵋敪 == "�����J�ݒ�" �܂��� "�_�E�����[�h��")���� 
        // �@@����M�敪 == "���M��"�̏ꍇ] 
        // �@@"�����J�ݒ�"��ԋp����B 
        else if((WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
                l_strOpenStatusDiv)
            || WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE.equals(l_strOpenStatusDiv))    
            && (WEB3SendRcvDivDef.SEND_COMPLETE.equals(
                l_strSendRcvDiv))
            )
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AioAccOpenRemarkDef.ACCEPT_RESULT_CODE_10000000;
        }

        // [�����J�ݏ󋵋敪 == "�����J�݃G���[" ���� 
        // �@@"����M�敪" == "��M��"�̏ꍇ] 
        // �@@�p�����[�^.GFT�����J�ݏ�Params.��t���ʃR�[�h��ԋp����B 
        else if(WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(
                l_strOpenStatusDiv)
            && (WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(
                l_strSendRcvDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            return l_gftAccountOpenSatusParams.getResultCode();
        }
        
        // [�����J�݋敪 == "�폜"�̏ꍇ] 
        // �@@"�폜"��ԋp����B 
        else if(WEB3AccountOpenStatusDivDef.DELETE.equals(
            l_strOpenStatusDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AioAccOpenRemarkDef.ACCEPT_RESULT_CODE_90000000;
        }
        
        // [��L�ȊO�̏ꍇ] 
        // �@@"�V�X�e���G���["��ԋp����B 
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AioAccOpenRemarkDef.ACCEPT_RESULT_CODE_99999999;
        }
    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * (�Ǘ���FX�����J�݊Ǘ�)FX�����J�ݐ\���_�E�����[�h�t�@@�C���f�[�^�擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     *�u(�Ǘ���FX�����J�݊Ǘ��T�[�r�X)get�_�E�����[�h�t�@@�C���v �Q��<BR>
     * 1.10.doFindAllQuery(arg0 : RowType, arg1 : String, arg2 : String, arg3 : String,<BR>
     * arg4 : Object[])�Y�����郌�R�[�h�����݂��Ȃ������ꍇ�A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00398<BR>
     * <BR>
     * @@param  l_request - �Ǘ��ҁEFX�����J�ݐ\���_�E�����[�h���N�G�X�g
     * @@return WEB3AdminFXAccOpenApplyDownloadResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminFXAccOpenApplyDownloadResponse getDownloadFile(
        WEB3AdminFXAccOpenApplyDownloadRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDownloadFile(WEB3AdminFXAccOpenApplyDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_ACCOUNT_MANAGE, true);
        
        //1.4 validate���X����(���X�R�[�h : String[])
        l_admin.validateBranchPermission(l_request.branchCodeList);
        
        //1.5 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 create��������������(String, String[], String, String, String, String)
        String l_strWhere = this.createQueryString(
            l_strInstitutionCode,
            l_request.branchCodeList,
            l_request.statusDiv,
            l_request.applyHourFrom,
            l_request.applyHourTo,
            l_request.agreementDiv);
        
        //1.7 create���������f�[�^�R���e�i(String, String[], String, String, String, String)
        String[] l_strVars = this.createQueryContainer(
            l_strInstitutionCode,
            l_request.branchCodeList,
            l_request.statusDiv,
            l_request.applyHourFrom,
            l_request.applyHourTo,
            l_request.agreementDiv);
        
        //1.8 create�\�[�g����( )
        String l_strSort = this.createSortCond();

        QueryProcessor l_queryProcessor = null;
        List l_lisRows = null;
        try
        {
            //1.9 getDefaultProcessor( )
            l_queryProcessor = Processors.getDefaultProcessor();
            
            //1.10 doFindAllQuery(arg0 : RowType, arg1 : String, arg2 : String, arg3 : String, arg4 : Object[])
            //[����] 
            //rowType�F�@@ GFT�����J�ݏ�Row.TYPE 
            //where�F�@@   create��������������()�̖߂�l 
            //orderBy�F�@@ create�\�[�g����()�̖߂�l
            //conditions�F"for update"
            //bindVars�F�@@create���������f�[�^�R���e�i()�̖߂�l
            l_lisRows = l_queryProcessor.doFindAllQuery(
                GftAccountOpenStatusRow.TYPE,
                l_strWhere,
                l_strSort,
                "for update",
                l_strVars);
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
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Y�����郌�R�[�h�����݂��Ȃ������ꍇ�A��O���X���[����B
        if (null == l_lisRows || 0 == l_lisRows.size())
        {
            log.debug("�Y������f�[�^�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������f�[�^�����݂��܂���B");
        }
        
        //1.11 �Ǘ���FX�����J�ݐ\���_�E�����[�hCSV( )
        WEB3AdminFXAccOpenApplyDownloadCsv l_downloadCsv = new WEB3AdminFXAccOpenApplyDownloadCsv();
        
        //1.12 �擾����GFT�����J�ݏ󋵍s���ɁALoop���������{����
        GftAccountOpenStatusParams[] l_params = new GftAccountOpenStatusParams[l_lisRows.size()];
        l_lisRows.toArray(l_params);
        for (int i = 0; i < l_params.length; i++)
        {
            //1.12.1 add���׍s( )
            int l_intLineNumber = l_downloadCsv.addRow();
            
            //1.12.2 set�ǉ��ύX�敪(int)
            l_downloadCsv.setAddModifiedDiv(l_intLineNumber);
            
            //1.12.3 set���p�҃R�[�h(int, String)
            l_downloadCsv.setUserCode(l_intLineNumber, l_params[i].login_id.substring(1));
            
            //1.12.4 set���p�Җ�(int, String)
            l_downloadCsv.setUserName(l_intLineNumber, l_params[i].getLastName());
            
            //1.12.5 set���O�C��ID(int, String)
            l_downloadCsv.setLoginId(l_intLineNumber, l_params[i].login_id.substring(1));
            
            //1.12.6 set���O�C���p�X���[�h(int)
            l_downloadCsv.setLoginPassword(l_intLineNumber);
            
            //1.12.7 set�����p�X���[�h(int)
            l_downloadCsv.setOrderPassword(l_intLineNumber);
            
            //1.12.8 set���[���A�h���X�P(int, String)
            l_downloadCsv.setMailAddress1(l_intLineNumber,l_params[i].getMailAddress());
            
            //1.12.9 set���[���A�h���X�Q(int)
            l_downloadCsv.setMailAddress2(l_intLineNumber);
            
            //1.12.10 set���Ȏ���敪(int)
            l_downloadCsv.setSelfTrustDiv(l_intLineNumber);

            //set���p�ґ���(int)
            l_downloadCsv.setUserAttribute(l_intLineNumber);
            
            //set���ϕ��@@(int)
            l_downloadCsv.setTransferMethod(l_intLineNumber);
            
            //1.12.11 set���X�J�b�g�敪(int)
            l_downloadCsv.setLossCutDiv(l_intLineNumber);
            
            //1.12.12 set�萔���敪(int)
            l_downloadCsv.setCommissionDiv(l_intLineNumber);
            
            //1.12.13 set����\�敪(int)
            l_downloadCsv.setTradingDiv(l_intLineNumber);

            //set�d�q��t������(int, Date)
            l_downloadCsv.setReportAcceptDate(l_intLineNumber,l_params[i].getCreatedTimestamp());

            //set����������m�F��(int, Date)
            l_downloadCsv.setTradeInstructionsConfirmDate(l_intLineNumber,l_params[i].getCreatedTimestamp());

            //set������ԍ�(int)
            l_downloadCsv.setContractCode(l_intLineNumber);
            
            //1.12.14 set���l(int String)
            l_downloadCsv.setRemark(l_intLineNumber, l_params[i].getOrderRequestNumber());
            
            //1.12.15 set���i�R�[�h�P(int)
            l_downloadCsv.setProductCode1(l_intLineNumber);
            
            //1.12.16 set����������ʂP(int)
            l_downloadCsv.setOrderQuantityUpper1(l_intLineNumber);
            
            //1.12.17 set���i�R�[�h�Q(int)
            l_downloadCsv.setProductCode2(l_intLineNumber);
            
            //1.12.18 set����������ʂQ(int)
            l_downloadCsv.setOrderQuantityUpper2(l_intLineNumber);
            
            //1.12.19 set���i�R�[�h�R(int)
            l_downloadCsv.setProductCode3(l_intLineNumber);
            
            //1.12.20 set����������ʂR(int)
            l_downloadCsv.setOrderQuantityUpper3(l_intLineNumber);
            
            //1.12.21 set���i�R�[�h�S(int)
            l_downloadCsv.setProductCode4(l_intLineNumber);
            
            //1.12.22 set����������ʂS(int)
            l_downloadCsv.setOrderQuantityUpper4(l_intLineNumber);
            
            //1.12.23 set���i�R�[�h�T(int)
            l_downloadCsv.setProductCode5(l_intLineNumber);
            
            //1.12.24 set����������ʂT(int)
            l_downloadCsv.setOrderQuantityUpper5(l_intLineNumber);
            
            //1.12.25 set���i�R�[�h�U(int)
            l_downloadCsv.setProductCode6(l_intLineNumber);
            
            //1.12.26 set����������ʂU(int)
            l_downloadCsv.setOrderQuantityUpper6(l_intLineNumber);
            
            //1.12.27 set���i�R�[�h�V(int)
            l_downloadCsv.setProductCode7(l_intLineNumber);
            
            //1.12.28 set����������ʂV(int)
            l_downloadCsv.setOrderQuantityUpper7(l_intLineNumber);

            //set���i�R�[�h�W(int)
            l_downloadCsv.setProductCode8(l_intLineNumber);

            //set����������ʂW(int)
            l_downloadCsv.setOrderQuantityUpper8(l_intLineNumber);

            //set���i�R�[�h�X(int)
            l_downloadCsv.setProductCode9(l_intLineNumber);

            //set����������ʂX(int)
            l_downloadCsv.setOrderQuantityUpper9(l_intLineNumber);

            //set���i�R�[�h�P�O(int)
            l_downloadCsv.setProductCode10(l_intLineNumber);

            //set����������ʂP�O(int)
            l_downloadCsv.setOrderQuantityUpper10(l_intLineNumber);

            //set���i�R�[�h�P�P(int)
            l_downloadCsv.setProductCode11(l_intLineNumber);

            //set����������ʂP�P(int)
            l_downloadCsv.setOrderQuantityUpper11(l_intLineNumber);

            //set���i�R�[�h�P�Q(int)
            l_downloadCsv.setProductCode12(l_intLineNumber);

            //set����������ʂP�Q(int)
            l_downloadCsv.setOrderQuantityUpper12(l_intLineNumber);

            //set���i�R�[�h�P�R(int)
            l_downloadCsv.setProductCode13(l_intLineNumber);

            //set����������ʂP�R(int)
            l_downloadCsv.setOrderQuantityUpper13(l_intLineNumber);

            //set���i�R�[�h�P�S(int)
            l_downloadCsv.setProductCode14(l_intLineNumber);

            //set����������ʂP�S(int)
            l_downloadCsv.setOrderQuantityUpper14(l_intLineNumber);

            //set���i�R�[�h�P�T(int)
            l_downloadCsv.setProductCode15(l_intLineNumber);

            //set����������ʂP�T(int)
            l_downloadCsv.setOrderQuantityUpper15(l_intLineNumber);

            //set���i�R�[�h�P�U(int)
            l_downloadCsv.setProductCode16(l_intLineNumber);

            //set����������ʂP�U(int)
            l_downloadCsv.setOrderQuantityUpper16(l_intLineNumber);

            //set���i�R�[�h�P�V(int)
            l_downloadCsv.setProductCode17(l_intLineNumber);

            //set����������ʂP�V(int)
            l_downloadCsv.setOrderQuantityUpper17(l_intLineNumber);

            //set���i�R�[�h�P�W(int)
            l_downloadCsv.setProductCode18(l_intLineNumber);

            //set����������ʂP�W(int)
            l_downloadCsv.setOrderQuantityUpper18(l_intLineNumber);

            //set���i�R�[�h�P�X(int)
            l_downloadCsv.setProductCode19(l_intLineNumber);

            //set����������ʂP�X(int)
            l_downloadCsv.setOrderQuantityUpper19(l_intLineNumber);

            //set���i�R�[�h�Q�O(int)
            l_downloadCsv.setProductCode20(l_intLineNumber);

            //set����������ʂQ�O(int)
            l_downloadCsv.setOrderQuantityUpper20(l_intLineNumber);

            //set���i�R�[�h�Q�P(int)
            l_downloadCsv.setProductCode21(l_intLineNumber);

            //set����������ʂQ�P(int)
            l_downloadCsv.setOrderQuantityUpper21(l_intLineNumber);

            //set���i�R�[�h�Q�Q(int)
            l_downloadCsv.setProductCode22(l_intLineNumber);

            //set����������ʂQ�Q(int)
            l_downloadCsv.setOrderQuantityUpper22(l_intLineNumber);

            //set���i�R�[�h�Q�R(int)
            l_downloadCsv.setProductCode23(l_intLineNumber);

            //set����������ʂQ�R(int)
            l_downloadCsv.setOrderQuantityUpper23(l_intLineNumber);

            //set���i�R�[�h�Q�S(int)
            l_downloadCsv.setProductCode24(l_intLineNumber);

            //set����������ʂQ�S(int)
            l_downloadCsv.setOrderQuantityUpper24(l_intLineNumber);

            //set���i�R�[�h�Q�T(int)
            l_downloadCsv.setProductCode25(l_intLineNumber);

            //set����������ʂQ�T(int)
            l_downloadCsv.setOrderQuantityUpper25(l_intLineNumber);
        }

        //1.13 getCSV�t�@@�C���s( )
        String[] l_strCsvFileLines = l_downloadCsv.getCsvFileLines();
        
        //1.14 doUpdateAllQuery(arg0 : RowType, arg1 : String, arg2 : Object[], arg3 : Map)
        //[����] 
        //rowType�F�@@ GFT�����J�ݏ�Row.TYPE
        //where�F�@@   create��������������()�̖߂�l
        //bindVars�F�@@create���������f�[�^�R���e�i()�̖߂�l
        HashMap l_hashMap = new HashMap();
        l_hashMap.put("account_open_status_div", WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE);
        l_hashMap.put("last_updater", l_admin.getAdministratorCode());
        l_hashMap.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
        
        try
        {
            l_queryProcessor.doUpdateAllQuery(
                GftAccountOpenStatusRow.TYPE,
                l_strWhere,
                l_strVars,
                l_hashMap);
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
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
            
        //1.15 createResponse( )
        WEB3AdminFXAccOpenApplyDownloadResponse l_response = 
            (WEB3AdminFXAccOpenApplyDownloadResponse) l_request.createResponse();
        
        //1.16 (*)�v���p�e�B�Z�b�g
        //�_�E�����[�h�t�@@�C��   �FgetCSV�t�@@�C���s()�̖߂�l
        l_response.downloadFile = l_strCsvFileLines;

        //���ݓ���              �F�V�X�e���^�C���X�^���v
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�X�e�[�^�X�ύX�\)<BR>
     * �X�e�[�^�X�̕ύX���\���ǂ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�����J�ݒ��`�F�b�N<BR>
     * �@@�P�|�P�j���L�̏����ƈ�v����ꍇ�A��O�u�����J�ݒ��`�F�b�N�G���[�B�v���X���[����B<BR>
     * �@@�@@�@@�@@�@@�E�X�V�O�����J�ݏ󋵋敪�i"�����J�ݒ�"�܂���"�_�E�����[�h��"�j�ȊO�̏ꍇ<BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag  : BUSINESS_ERROR_01807<BR>
     * <BR>
     * �Q�j������敪�X�V�`�F�b�N<BR>
     * �@@�Q�|�P�j���L�̏����ƈ�v����ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�E�X�V�������敪��"���M��"�̏ꍇ�A�X�V�O������敪��"�����M"�ȊO�̏ꍇ�B<BR>
     * �@@�@@�@@�@@�@@�E�X�V�������敪��"�����M"�܂���"��̍�"�̏ꍇ�A�X�V�O������敪��"���M��"�ȊO�̏ꍇ�B<BR>
     * <BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag  : BUSINESS_ERROR_02403<BR>
     * <BR>
     * �R�j�����J�݊����X�V�`�F�b�N<BR>
     *  (�X�V�������敪��"�����J�݊���"�̏ꍇ�Ƀ`�F�b�N����B)<BR>
     * �@@�R�|�P�j���L�̏����ƈ�v����ꍇ�A��O�u����������G���[�B�v���X���[����B<BR>
     * �@@�@@�@@�@@�@@�E�X�V�������敪��"�����M"�܂���"���M��"�̏ꍇ�B<BR>
     * �@@�@@�@@�@@�@@�E�X�V�������敪��null�̏ꍇ�A�X�V�O������敪��"��̍�"�ȊO�̏ꍇ�B<BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag  : BUSINESS_ERROR_02350<BR>
     * <BR>
     * @@param l_strBeforeUpdOpenStatusDiv - �X�V�O�����J�ݏ󋵋敪
     * @@param l_strAfterUpdOpenStatusDiv  - �X�V������J�ݏ󋵋敪 
     * @@param l_strBeforeUpdAgreementDiv  - �X�V�O������敪
     * @@param l_strAfterUpdAgreementDiv   - �X�V�������敪
     * @@throws WEB3BaseException
     */
    protected void validateStatusChangePossible(
        String l_strBeforeUpdOpenStatusDiv,
        String l_strAfterUpdOpenStatusDiv,
        String l_strBeforeUpdAgreementDiv,        
        String l_strAfterUpdAgreementDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateStatusChangePossible(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBeforeUpdOpenStatusDiv == null || l_strBeforeUpdAgreementDiv == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�����J�ݒ��`�F�b�N
        //�E�X�V�O�����J�ݏ󋵋敪�i"�����J�ݒ�"�܂���"�_�E�����[�h��"�܂���"�����J�݃G���["�j�ȊO�̏ꍇ
        if (!(WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(l_strBeforeUpdOpenStatusDiv)
             || WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE.equals(l_strBeforeUpdOpenStatusDiv)
             || WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(l_strBeforeUpdOpenStatusDiv)))
        {
            log.debug("�����J�ݒ��`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01807,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�ݒ��`�F�b�N�G���[");
        }
        
        //�Q�j������敪�X�V�`�F�b�N
        //�E�X�V�������敪��"���M��"�̏ꍇ�A�X�V�O������敪��"�����M"�ȊO�̏ꍇ�B
        if (WEB3AioAgreementDivDef.SENDED.equals(l_strAfterUpdAgreementDiv) 
            && !(WEB3AioAgreementDivDef.NOT_SEND.equals(l_strBeforeUpdAgreementDiv)))
        {
            log.debug("������敪�X�V�`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02403,
                this.getClass().getName() + STR_METHOD_NAME,
                "������敪�X�V�`�F�b�N�G���[�B");
            
        }
        //�E�X�V�������敪��"�����M"�܂���"��̍�"�̏ꍇ�A�X�V�O������敪��"���M��"�ȊO�̏ꍇ�B
        if ((WEB3AioAgreementDivDef.NOT_SEND.equals(l_strAfterUpdAgreementDiv)
            || WEB3AioAgreementDivDef.RECIEVED.equals(l_strAfterUpdAgreementDiv))
            && !WEB3AioAgreementDivDef.SENDED.equals(l_strBeforeUpdAgreementDiv))
        {
            log.debug("������敪�X�V�`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02403,
                this.getClass().getName() + STR_METHOD_NAME,
                "������敪�X�V�`�F�b�N�G���[�B");
            
        }
        
        //�R�j�����J�݊����X�V�`�F�b�N
        // (�X�V�������敪��"�����J�݊���"�̏ꍇ�Ƀ`�F�b�N����B)
        if (WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(l_strAfterUpdOpenStatusDiv))
        {
            //�E�X�V�������敪��"�����M"�܂���"���M��"�̏ꍇ�B
            if (WEB3AioAgreementDivDef.NOT_SEND.equals(l_strAfterUpdAgreementDiv)
                || WEB3AioAgreementDivDef.SENDED.equals(l_strAfterUpdAgreementDiv))
            {
                log.debug("����������G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02350,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "����������G���[�B");
            }
            //�E�X�V�������敪��null�̏ꍇ�A�X�V�O������敪��"��̍�"�ȊO�̏ꍇ�B
            if (l_strAfterUpdAgreementDiv == null 
                && !WEB3AioAgreementDivDef.RECIEVED.equals(l_strBeforeUpdAgreementDiv))
            {
                log.debug("����������G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02350,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "����������G���[�B");
            }   
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�擾����������)<BR>
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
     * �@@�������������� += "and branch_code in (?,?,,,) "<BR>
     * <BR>
     * �S�j�p�����[�^.�X�e�[�^�X�敪 != null�̏ꍇ�A<BR>
     * �@@�����J�ݏ󋵋敪�����������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and account_open_status_div = ? "<BR>
     * <BR>
     * �T�j�p�����[�^.�\�����i���j != null�̏ꍇ�A<BR>
     * �@@�\�����̉��������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? "<BR>
     * <BR>
     * �U�j�p�����[�^.�\�����i���j != null�̏ꍇ�A<BR>
     * �@@�\�����̉��������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and to_char(created_timestamp, 'YYYYMMDDHH24') < ? "<BR>
     * <BR>
     * �V�j�p�����[�^.������敪 != null�̏ꍇ�A<BR>
     * �@@������敪�����������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and agreement_div = ? "<BR>
     * <BR>
     * �W�j�p�����[�^.FX�V�X�e���R�[�h != null�̏ꍇ�A<BR>
     * �@@FX�V�X�e���R�[�h�����������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and fx_system_code = ? "<BR>
     * <BR>
     * <BR>
     * �X�j�쐬�������������������ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��<BR>
     * @@param l_strStatusDiv - (�X�e�[�^�X�敪)<BR>
     * �X�e�[�^�X�敪<BR>
     * <BR>
     * 0�F�����J�ݒ�<BR>
     * 1�F�����J�݊���<BR>
     * 2�F�����J�݃G���[<BR>
     * 9�F�폜<BR>
     * <BR>
     * @@param l_strApplyHourFrom - (�\�����i���j)<BR>
     * �\�����i���j<BR>
     * (YYYYMMDDhh)<BR>
     * @@param l_strApplyHourTo - (�\�����i���j)<BR>
     * �\�����i���j<BR>
     * (YYYYMMDDhh)<BR>
     * @@param l_strAgreementDiv - (������敪)<BR>
     * ������敪<BR>
     * <BR>
     * 0�F����<BR>
     * 1�F�L��<BR>
     * null�F�S��<BR>
     * <BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     * @@return String
     */
    protected String createQueryConditionList(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strStatusDiv,
        String l_strApplyHourFrom,
        String l_strApplyHourTo,
        String l_strAgreementDiv,
        String l_strFxSystemCode)
    {
        final String STR_METHOD_NAME =
            "createQueryConditionList(String, String[], String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //��̌�������������(�FString)�𐶐�����
        StringBuffer l_sbSql = new StringBuffer();

        //�،���Џ��������������ɒǉ�����B
        //�@@�������������� += " institution_code = ? "
        l_sbSql.append(" institution_code = ? ");

        //���X���������������ɒǉ�����B
        //�@@�p�����[�^.���X�R�[�h[]�̗v�f����"?"��ǉ�����B
        //�@@�������������� += "and branch_code in (?,?,,,) "
        l_sbSql.append(" and branch_code in (? ");
        for (int i = 1; i < l_strBranchCodes.length; i++)
        {
            l_sbSql.append(", ? ");
        }
        l_sbSql.append(" ) ");

        //�p�����[�^.�X�e�[�^�X�敪 != null�̏ꍇ�A
        //�@@�����J�ݏ󋵋敪�����������ɒǉ�����B
        //�@@�������������� += "and account_open_status_div = ? "
        if (l_strStatusDiv != null)
        {
            l_sbSql.append(" and account_open_status_div = ? ");
        }

        //�p�����[�^.�\�����i���j != null�̏ꍇ�A
        //�@@�\�����̉��������������ɒǉ�����B
        //�@@�������������� += "and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? "
        if (l_strApplyHourFrom != null)
        {
            l_sbSql.append(" and to_char(created_timestamp, 'YYYYMMDDHH24') >= ? ");
        }

        //�p�����[�^.�\�����i���j != null�̏ꍇ�A
        //�@@�\�����̉��������������ɒǉ�����B
        //�@@�������������� += "and to_char(created_timestamp, 'YYYYMMDDHH24') < ? "
        if (l_strApplyHourTo != null)
        {
            l_sbSql.append(" and to_char(created_timestamp, 'YYYYMMDDHH24') < ? ");
        }

        //�p�����[�^.������敪 != null�̏ꍇ�A
        //�@@������敪�����������ɒǉ�����B
        //�@@�������������� += "and agreement_div = ? "
        if (l_strAgreementDiv != null)
        {
            l_sbSql.append(" and agreement_div = ? ");
        }

        //�p�����[�^.FX�V�X�e���R�[�h != null�̏ꍇ�A
        //�@@FX�V�X�e���R�[�h�����������ɒǉ�����B
        //�@@�������������� += "and fx_system_code = ? "
        if (l_strFxSystemCode != null)
        {
            l_sbSql.append(" and fx_system_code = ? ");
        }

        //�쐬�������������������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbSql.toString();
    }

    /**
     * (create�擾�����f�[�^�R���e�i)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�،���Џ�����ǉ�����B<BR>
     * �@@�p�����[�^.�،���ЃR�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j���X������ǉ�����B<BR>
     * �@@�p�����[�^.���X�R�[�h[]�̑S�v�f��ArrayList�ɒǉ�����B<BR>
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
     * �V�j�p�����[�^.������敪 != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.������敪��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �W�j�p�����[�^.FX�V�X�e���R�[�h != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.FX�V�X�e���R�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �X�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��<BR>
     * @@param l_strStatusDiv - (�X�e�[�^�X�敪)<BR>
     * �X�e�[�^�X�敪<BR>
     * <BR>
     * 0�F�����J�ݒ�<BR>
     * 1�F�����J�݊���<BR>
     * 2�F�����J�݃G���[<BR>
     * 9�F�폜<BR>
     * <BR>
     * @@param l_strApplyHourFrom - (�\�����i���j)<BR>
     * �\�����i���j<BR>
     * (YYYYMMDDhh)<BR>
     * @@param l_strApplyHourTo - (�\�����i���j)<BR>
     * �\�����i���j<BR>
     * (YYYYMMDDhh)<BR>
     * @@param l_strAgreementDiv - (������敪)<BR>
     * ������敪<BR>
     * <BR>
     * 0�F����<BR>
     * 1�F�L��<BR>
     * null�F�S��<BR>
     * <BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     * @@return String[]
     */
    protected String[] createQueryDataContainer(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strStatusDiv,
        String l_strApplyHourFrom,
        String l_strApplyHourTo,
        String l_strAgreementDiv,
        String l_strFxSystemCode)
    {
        final String STR_METHOD_NAME =
            "createQueryDataContainer(String, String[], String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //ArrayList�𐶐�����
        List l_lisValues = new ArrayList();

        //�،���Џ�����ǉ�����B
        //�@@�p�����[�^.�،���ЃR�[�h��ArrayList�ɒǉ�����B
        l_lisValues.add(l_strInstitutionCode);

        //�R�j���X������ǉ�����B
        //�@@�p�����[�^.���X�R�[�h[]�̑S�v�f��ArrayList�ɒǉ�����B
        for (int i = 0; i < l_strBranchCodes.length; i++)
        {
            l_lisValues.add(l_strBranchCodes[i]);
        }

        //�p�����[�^.�X�e�[�^�X�敪 != null�̏ꍇ�A
        //�@@�p�����[�^.�X�e�[�^�X�敪��ArrayList�ɒǉ�����B
        if (l_strStatusDiv != null)
        {
            l_lisValues.add(l_strStatusDiv);
        }

        //�p�����[�^.�\�����i���j != null�̏ꍇ�A
        //�@@�p�����[�^.�\�����i���j��ArrayList�ɒǉ�����B
        if (l_strApplyHourFrom != null)
        {
            l_lisValues.add(l_strApplyHourFrom);
        }

        //�p�����[�^.�\�����i���j != null�̏ꍇ�A
        //�@@�p�����[�^.�\�����i���j��ArrayList�ɒǉ�����B
        if (l_strApplyHourTo != null)
        {
            l_lisValues.add(l_strApplyHourTo);
        }

        //�p�����[�^.������敪 != null�̏ꍇ�A
        //�@@�p�����[�^.������敪��ArrayList�ɒǉ�����B
        if (l_strAgreementDiv != null)
        {
            l_lisValues.add(l_strAgreementDiv);
        }

        //�p�����[�^.FX�V�X�e���R�[�h != null�̏ꍇ�A
        //�@@�p�����[�^.FX�V�X�e���R�[�h��ArrayList�ɒǉ�����B
        if (l_strFxSystemCode != null)
        {
            l_lisValues.add(l_strFxSystemCode);
        }

        //��������ArrayList.toArray()�̖߂�l��ԋp����
        String[] l_strSqlValues = new String[l_lisValues.size()];
        l_lisValues.toArray(l_strSqlValues);

        log.exiting(STR_METHOD_NAME);
        return l_strSqlValues;
    }
}@
