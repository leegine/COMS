head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoDirectChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃_�C���N�g�w��ύX�T�[�r�XImpl(WEB3AdminPvInfoDirectChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/27 ���O�B(���u) �쐬
Revesion History : 2007/06/27 ���G��(���u) ���f��078�A�����̖��002
Revesion History : 2009/07/06 ���g(���u) ���f��115,116,120
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3PvInfoBlinkDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoEffectiveFlagDef;
import webbroker3.common.define.WEB3PvInfoLastUpdateTimeDisplayFlagDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.WEB3PvInfoRegistTargetAccountUploadCsv;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.define.WEB3PvInfoUploadStateDivDef;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputResponse;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.pvinfo.message.WEB3PvInfoUploadHistoryUnit;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoDirectChangeService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��҃_�C���N�g�w��ύX�T�[�r�XImpl)<BR>
 * �Ǘ��҃_�C���N�g�w��ύX�T�[�r�X�����N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoDirectChangeServiceImpl extends WEB3ClientRequestService implements WEB3AdminPvInfoDirectChangeService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectChangeServiceImpl.class);

    /**
     * �_�C���N�g�w��ύX�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�_�C���N�g�w��ύX���̓��N�G�X�g�̏ꍇ<BR>
     * �@@this.get�_�C���N�g�w��ύX���͉��()���\�b�h���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�_�C���N�g�w��ύX�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.validate�_�C���N�g�w��ύX()���\�b�h���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�_�C���N�g�w��ύX�������N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�_�C���N�g�w��ύX()���\�b�h���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�_�C���N�g�w��ύX�A�b�v���[�h���~���N�G�X�g�̏ꍇ<BR>
     * �@@this.undo�_�C���N�g�w��ύX()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 416103B401C1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminPvInfoDirectChangeInputRequest)
        {
            l_response = this.getDirectChangeInputScreen((WEB3AdminPvInfoDirectChangeInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminPvInfoDirectChangeConfirmRequest)
        {
            l_response = this.validateDirectChange((WEB3AdminPvInfoDirectChangeConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminPvInfoDirectChangeCompleteRequest)
        {
            l_response = this.submitDirectChange((WEB3AdminPvInfoDirectChangeCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminPvInfoDirectChangeCancelRequest)
        {
            l_response = this.undoDirectChangeUpload((WEB3AdminPvInfoDirectChangeCancelRequest)l_request);
        }
        else
        {
            String l_strErrorMessage =
                "�p�����[�^�̗ތ^���s���A�Y������WEB3AdminPvInfoDirectChangeInputRequest," +
                "WEB3AdminPvInfoDirectChangeConfirmRequest,WEB3AdminPvInfoDirectChangeCancelRequest,WEB3AdminPvInfoDirectChangeCompleteRequest�ތ^�B";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�_�C���N�g�w��ύX���͉��)<BR>
     * �_�C���N�g�w��ύX���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X)get�_�C���N�g�w��ύX���͉�ʁv�Q��<BR>
     * ========================================================<BR>
     * get�\�����eParams(�\�����eID:long)<BR>
     * <BR>
     * null���ԋp���ꂽ�ꍇ�́A<BR>
     * �u�Y���f�[�^�����݁v�̗�O���X���[����B<BR>
     *   class: WEB5BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01037<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��ύX���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeInputResponse
     * @@roseuid 41610416027D
     */
    protected WEB3AdminPvInfoDirectChangeInputResponse getDirectChangeInputScreen(WEB3AdminPvInfoDirectChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDirectChangeInputScreen(WEB3AdminPvInfoDirectChangeInputRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3Administrator l_administrator = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1 validate()
        l_request.validate();

        //2 getInstanceFrom���O�C�����()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //3 validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);

        //4 get�\�����eParams(long)
        long l_lngDisplayContentsId = Long.valueOf(l_request.displayContentsId).longValue();
        DisplayContentsParams l_dispContentsParams = l_dataManager.getDisplayContentsParams(l_lngDisplayContentsId);

        if (l_dispContentsParams == null)
        {
            log.error("get�\�����eParams���s����.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                getClass().getName() + STR_METHOD_NAME);
        }

        //5 �\�����e���()
        WEB3PvInfoDisplayContentsUnit l_dispContentsUnit = new WEB3PvInfoDisplayContentsUnit();

        //6 (*)�v���p�e�B�Z�b�g
        l_dispContentsUnit.displayContentsId = WEB3StringTypeUtility.formatNumber(l_dispContentsParams.display_contents_id);
        log.debug("�\�����e���.displayContentsId" + "=" + l_dispContentsUnit.displayContentsId);
        l_dispContentsUnit.conditionNumber = l_dispContentsParams.condition_no;
        log.debug("�\�����e���.conditionNumber" + "=" + l_dispContentsUnit.conditionNumber);
        l_dispContentsUnit.priorityDiv = l_dispContentsParams.priority_div;
        log.debug("�\�����e���.priorityDiv" + "=" + l_dispContentsUnit.priorityDiv);
        l_dispContentsUnit.listStartDate = WEB3DateUtility.formatDate(l_dispContentsParams.term_from, "yyyyMMddHHmm");
        log.debug("�\�����e���.listStartDate" + "=" + l_dispContentsUnit.listStartDate);
        l_dispContentsUnit.listEndDate = WEB3DateUtility.formatDate(l_dispContentsParams.term_to, "yyyyMMddHHmm");
        log.debug("�\�����e���.listEndDate" + "=" + l_dispContentsUnit.listEndDate);
        l_dispContentsUnit.displayTitle = l_dispContentsParams.display_title;
        log.debug("�\�����e���.displayTitle" + "=" + l_dispContentsUnit.displayTitle);
        l_dispContentsUnit.displayMessage = l_dispContentsParams.display_message;
        log.debug("�\�����e���.displayMessage" + "=" + l_dispContentsUnit.displayMessage);
        l_dispContentsUnit.displayColor = l_dispContentsParams.display_color;
        log.debug("�\�����e���.displayColor" + "=" + l_dispContentsUnit.displayColor);

        if (WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES.equals(l_dispContentsParams.blink_display_flag))
        {
            l_dispContentsUnit.blinkDisplayFlag = true;
        }
        else
        {
            l_dispContentsUnit.blinkDisplayFlag = false;
        }
        log.debug("�\�����e���.blinkDisplayFlag" + "=" + l_dispContentsUnit.blinkDisplayFlag);

        l_dispContentsUnit.displayUrl = l_dispContentsParams.display_url;
        log.debug("�\�����e���.displayUrl" + "=" + l_dispContentsUnit.displayUrl);

        if (WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES.equals(l_dispContentsParams.last_update_time_display_flag))
        {
            l_dispContentsUnit.lastUpdateTimeDisplayFlag = false;
        }
        else
        {
            l_dispContentsUnit.lastUpdateTimeDisplayFlag = true;
        }
        log.debug("�\�����e���.lastUpdateTimeDisplayFlag" + "=" + l_dispContentsUnit.lastUpdateTimeDisplayFlag);

        if (WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES.equals(l_dispContentsParams.effective_flag))
        {
            l_dispContentsUnit.effectiveFlag = false;
        }
        else
        {
            l_dispContentsUnit.effectiveFlag = true;
        }
        log.debug("�\�����e���.effectiveFlag" + "=" + l_dispContentsUnit.effectiveFlag);
        l_dispContentsUnit.displayDevice = l_dispContentsParams.display_device;
        log.debug("�\�����e���.displayDevice" + "=" + l_dispContentsUnit.displayDevice);
        l_dispContentsUnit.lastUpdateMember = l_dispContentsParams.last_update_member;
        log.debug("�\�����e���.lastUpdateMember" + "=" + l_dispContentsUnit.lastUpdateMember);
        l_dispContentsUnit.lastUpdatedTimestamp = l_dispContentsParams.last_updated_timestamp;
        log.debug("�\�����e���.lastUpdatedTimestamp" + "=" + l_dispContentsUnit.lastUpdatedTimestamp);

        //7 �o�^�Ώیڋq�A�b�v���[�hCSV()
        WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv();

        //8 get�ŐV�A�b�v���[�h����(long)[get�ŐV�A�b�v���[�h����()�Ɏw�肷�����]�A�f�[�^�L�[�F�@@0
        AdministratorUploadRow l_adminUploadRow = l_registTargetAccountUploadCsv.getLatestUploadAction(0);

        //9 (*)�A�b�v���[�h���������݂���ꍇ�A�������{
        WEB3PvInfoUploadHistoryUnit l_uploadHistoryUnit = null;
        if (l_adminUploadRow != null)
        {
            // 9.1  ��ײ�ްĲ�̫Ұ��݃A�b�v���[�h���𖾍�()
            l_uploadHistoryUnit = new WEB3PvInfoUploadHistoryUnit();

            //9.2(*)�v���p�e�B�Z�b�g
            if (l_adminUploadRow.getUploadEndTimestamp() == null)
            {
                l_uploadHistoryUnit.uploadStateDiv = WEB3PvInfoUploadStateDivDef.UPLOAD_STATUS_PROCESS;
            }
            else
            {
                l_uploadHistoryUnit.uploadStateDiv = WEB3PvInfoUploadStateDivDef.UPLOAD_STATUS_COMPLETE;
            }
            log.debug("��ײ�ްĲ�̫Ұ��݃A�b�v���[�h���𖾍�.uploadStateDiv" + "=" + l_uploadHistoryUnit.uploadStateDiv);
            l_uploadHistoryUnit.uploadNumber = WEB3StringTypeUtility.formatNumber(l_adminUploadRow.getUploadCount());
            log.debug("��ײ�ްĲ�̫Ұ��݃A�b�v���[�h���𖾍�.uploadNumber" + "=" + l_uploadHistoryUnit.uploadNumber);
            l_uploadHistoryUnit.uploadStartDate = l_adminUploadRow.getUploadStartTimestamp();
            log.debug("��ײ�ްĲ�̫Ұ��݃A�b�v���[�h���𖾍�.uploadStartDate" + "=" + l_uploadHistoryUnit.uploadStartDate);
            l_uploadHistoryUnit.uploadEndDate = l_adminUploadRow.getUploadEndTimestamp();
            log.debug("��ײ�ްĲ�̫Ұ��݃A�b�v���[�h���𖾍�.uploadEndDate" + "=" + l_uploadHistoryUnit.uploadEndDate);
            l_uploadHistoryUnit.pvInfoErrorId = l_adminUploadRow.getMessageCode();
            log.debug("��ײ�ްĲ�̫Ұ��݃A�b�v���[�h���𖾍�.pvInfoErrorId" + "=" + l_uploadHistoryUnit.pvInfoErrorId);
        }

        //10 create Response()
        WEB3AdminPvInfoDirectChangeInputResponse l_response = (WEB3AdminPvInfoDirectChangeInputResponse)l_request.createResponse();

        //11 (*)�v���p�e�B�Z�b�g
        l_response.displayContentsUnit = l_dispContentsUnit;
        l_response.uploadHistoryUnit = l_uploadHistoryUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�_�C���N�g�w��ύX)<BR>
     * �_�C���N�g�w��ύX�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X)validate�_�C���N�g�w��ύX�v�Q��<BR>
     * ========================================================<BR>
     * get���X(�،���ЃR�[�h:String, ���X�R�[�h:String)<BR>
     * <BR>
     * ���Âꂩ����O���X���[�����ꍇ�́A<BR>�u�ڋq�����݃G���[�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ========================================================<BR>
     * ========================================================<BR>
     * get�ڋq(�،���ЃR�[�h:String, ���X�R�[�h:String)<BR>
     * <BR>
     * ���Âꂩ����O���X���[�����ꍇ�́A<BR>�u�ڋq�����݃G���[�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��ύX�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeConfirmResponse
     * @@roseuid 41610416029C
     */
    protected WEB3AdminPvInfoDirectChangeConfirmResponse validateDirectChange(WEB3AdminPvInfoDirectChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDirectChange(WEB3AdminPvInfoDirectChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3Administrator l_administrator = null;

        //1 validate()
        l_request.validate();

        //2 getInstanceFrom���O�C�����()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //3 validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);

        //4  (*1)����t���[
        int l_intRowCnt = 0;
        long l_lngAdminUploadId = 0;

        if (l_request.uploadFile != null)
        {
            //4.1 reset��t���ԋ敪(String)
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD_DAYLONG);

            //4.2reset������t�g�����U�N�V����(String)
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);

            //4.3 validate������t�\()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            //4.4 �A�b�v���[�hTemp�e�[�u���o�^����
            //4.4.1 �o�^�Ώیڋq�A�b�v���[�hCSV()
            WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv();

            //4.4.2 validate�����A�b�v���[�h(long)[validate�����A�b�v���[�h()�Ɏw�肷�����]�A�b�v���[�h�h�c�Fnull
            l_registTargetAccountUploadCsv.validateSameTimeUpload(null);

            //4.4.3 get�Ǘ��҃R�[�h()
            String l_strAdminCode = l_administrator.getAdministratorCode();
            log.debug("�Ǘ��҃R�[�h:l_strAdminCode" + l_strAdminCode);

            //4.4.4 save�A�b�v���[�h�J�n(long, String, String, String, String)
            l_registTargetAccountUploadCsv.saveUpLoadStart(0, null, null, null, l_strAdminCode);

            //4.4.5 (*1) ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[]�̊e�v�f����LOOP����
            int l_intUploadFileCnt = l_request.uploadFile.length;
            log.debug("�A�b�v���[�h�t�@@�C��[]�̊e�v�f����LOOP��:l_intUploadFileCnt = " + l_intUploadFileCnt);
            for (int i = 0; i < l_intUploadFileCnt; i ++)
            {
                int l_intCnt = 0;
                
                //4.4.5.1  add���׍s(���׍s������i=�A�b�v���[�h�t�@@�C��[index]�j : String)
                try
                {
                    l_intCnt = l_registTargetAccountUploadCsv.addRow(l_request.uploadFile[i]);
                    log.debug("add���׍s(���׍s������i=�A�b�v���[�h�t�@@�C��[index]�j : String)�����s���܂�");
                }
                catch (WEB3SystemLayerException l_ex)
                {
                    //4.4.5.2 add���׍s()�ŗ�O���X���[���ꂽ�ꍇ
                    //4.4.5.2.1 save�A�b�v���[�h�G���[(ErrorInfo)
                    l_registTargetAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());

                    //4.4.5.2.2 throw(��O)
                    log.error("add���׍s���s����");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException (
                        WEB3ErrorCatalog.BUSINESS_ERROR_00856,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getErrorMessage(),
                        l_ex);
                }
                
                //��s�̏ꍇ�iadd���׍s()�̖߂�l < 0�j�A���Y�v�f�̏����𒆒f�icontinue;�j
                if (l_intCnt < 0)
                {
                    continue;
                }
                
                try
                {
                    //4.4.5.3 validate���׍s(long)
                    l_registTargetAccountUploadCsv.validateDetailsLine(l_intCnt);
                    log.debug("validate���׍s(long)�����s���܂�");
                }
                catch (WEB3BaseException l_ex)
                {
                    //4.4.5.5 (*1.2) validate���׍s()�Cvalidate�d���ڋq()�ɂė�O���X���[���ꂽ�ꍇ
                    //4.4.5.5.1 save�A�b�v���[�h�G���[(ErrorInfo)
                    l_registTargetAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());

                    //4.4.5.5.2 throw�i��O�j
                    log.error("validate���׍s or validate�d���ڋq���s����");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseException (
                        WEB3ErrorCatalog.BUSINESS_ERROR_00856,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_registTargetAccountUploadCsv.getBranchCode(l_intCnt) + ","
                            + l_registTargetAccountUploadCsv.getAccountCode(l_intCnt));
                }

            }

            //4.4.6 get���׍s��()
            l_intRowCnt = l_registTargetAccountUploadCsv.getRowCount();
            log.debug("���׍s��:l_intRowCnt = " + l_intRowCnt);

            //4.4.7 get�A�b�v���[�h�h�c()
            l_lngAdminUploadId = l_registTargetAccountUploadCsv.getAdministratorUploadId();
            log.debug("�A�b�v���[�h�h�c:l_lngAdminUploadId = " + l_lngAdminUploadId);

            //4.4.8 save�A�b�v���[�hTemp()
            l_registTargetAccountUploadCsv.saveUploadTemp();
        }
        //5 (*2)����t���[
        else
        {

            //5.1 get���X(String, String)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_accMgr;

            try
            {
                //�،���ЃR�[�h���擾
                String l_strInstitutionCode = l_administrator.getInstitutionCode();
                log.debug("�،���ЃR�[�h:l_strInstitutionCode =" + l_strInstitutionCode);
                WEB3GentradeBranch l_branch = l_accountManager.getWeb3GenBranch(l_strInstitutionCode, l_request.branchCode);
                log.debug("get���X(String, String)�����s���܂�");

                //5.2 get�ڋq(String, String, String)
                String l_strInstCode = l_branch.getInstitution().getInstitutionCode();
                String l_strBranchCode = l_branch.getBranchCode();
                l_accountManager.getMainAccount(l_strInstCode, l_strBranchCode, l_request.accountCode);
                log.debug("get�ڋq(String, String, String)�����s���܂�");
            }
            catch (NotFoundException l_ex)
            {
                log.error("���X or �ڋq�擾�����s����");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //6 creat Response
        WEB3AdminPvInfoDirectChangeConfirmResponse l_response = (WEB3AdminPvInfoDirectChangeConfirmResponse)l_request.createResponse();

        //7 �v���p�e�B�Z�b�g
        if (l_request.uploadFile == null)
        {
            l_response.uploadNumber = null;
            l_response.uploadId = null;
        }
        else
        {
            l_response.uploadNumber = WEB3StringTypeUtility.formatNumber(l_intRowCnt);
            l_response.uploadId = WEB3StringTypeUtility.formatNumber(l_lngAdminUploadId);
        }

        log.debug("*************l_response.uploadNumber = " + l_response.uploadNumber);
        log.debug("*************l_response.uploadId = " + l_response.uploadId);
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�_�C���N�g�w��ύX)<BR>
     * �_�C���N�g�w��ύX�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X)submit�_�C���N�g�w��ύX�v�Q��<BR>
     * ========================================================<BR>
     * get���X(�،���ЃR�[�h:String, ���X�R�[�h:String)<BR>
     * <BR>
     * ���Âꂩ����O���X���[�����ꍇ�́A<BR>�u�ڋq�����݃G���[�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * =========================================================<BR>
     * ========================================================<BR>
     * get�ڋq(�،���ЃR�[�h:String, ���X�R�[�h:String)<BR>
     * <BR>
     * ���Âꂩ����O���X���[�����ꍇ�́A<BR>�u�ڋq�����݃G���[�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * =========================================================<BR>
     * ========================================================<BR>
     * �d���`�F�b�N<BR>
     * <BR>
     * �ȉ��@@�ƇA���r���A�����l�̏ꍇ��O���X���[<BR>
     * �@@(*1)�̒l<BR>
     * �A[get�ڋq�R�[�h()�̖߂�l]��[get���X�R�[�h()�̖߂�l]�����������l<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00517<BR>
     * =========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��ύX�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCompleteResponse
     * @@roseuid 4161041602BB
     */
    protected WEB3AdminPvInfoDirectChangeCompleteResponse submitDirectChange(WEB3AdminPvInfoDirectChangeCompleteRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " submitDirectChange(WEB3AdminPvInfoDirectChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3Administrator l_administrator = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        int l_intRowCnt = 0;

        //1 validate()
        l_request.validate();

        //2 getInstanceFrom���O�C�����()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //3 validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);

        //4 validate����p�X���[�h(String)
        l_administrator.validateTradingPassword(l_request.password);

        try
        {
            //�Ǘ��҃_�C���N�g�w��ύXTransactionCallback(�\�����e��� : �\�����e���)
            //�\�����e���F ���N�G�X�g�f�[�^.�\�����e���
            WEB3AdminPvInfoDirectChangeTransactionCallback l_transactionCallback =
                new WEB3AdminPvInfoDirectChangeTransactionCallback(l_request.displayContentsUnit);

            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //doTransaction(arg0 : int, arg1 : TransactionCallback)
            //[����]
            //�g�����U�N�V���������F�@@TX_CREATE_NEW
            //�g�����U�N�V�����R�[���o�b�N�F�@@�Ǘ��҃_�C���N�g�w��ύXTransactionCallback�C���X�^���X
            l_queryProcessor.doTransaction(
                TransactionalInterceptor.TX_CREATE_NEW, l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error( "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //7 ����t���[
        String l_strInstCode = l_administrator.getInstitutionCode();
        long l_lngDisplayContentsId = Long.parseLong(l_request.displayContentsUnit.displayContentsId);
        if (l_request.uploadId != null)
        {
            //7.1 reset��t���ԋ敪(String)
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.UPLOAD_DAYLONG);

            //7.2reset������t�g�����U�N�V����(String)
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);

            //7.3 validate������t�\()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            //7.4 ( * CSV�ꊇ�ڋq�ύX����)
            //7.4.1 �o�^�Ώیڋq�A�b�v���[�hCSV(�A�b�v���[�hID : long)
            long l_lngUploadId = Long.parseLong(l_request.uploadId);
            WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv(l_lngUploadId);

            //7.4.2 validate�����A�b�v���[�h(long)
            l_registTargetAccountUploadCsv.validateSameTimeUpload(new Long(l_lngUploadId));

            //7.4.3 setDataFrom�A�b�v���[�hTemp(long)
            l_registTargetAccountUploadCsv.setDataFromUploadTemp(l_lngUploadId);

            //7.4.4 get���׍s��()
            l_intRowCnt = l_registTargetAccountUploadCsv.getRowCount();
            log.debug("********���׍s�� = " + l_intRowCnt);

            //7.4.5  delete�{������(long)            
            l_dataManager.deleteBrowseHistory(l_lngDisplayContentsId);
            log.debug("delete�{������(long)�����s���܂�");

            //(*1)�C���X�^���X�𐶐�
            String l_strAccountAndBranchCode = null;

            //7.4.6 ���׍s�̐���LOOP����
            for (int i = 0; i < l_intRowCnt; i++)
            {
                //7.4.6.1 get���X�R�[�h(int)
                String l_strBranchCode = l_registTargetAccountUploadCsv.getBranchCode(i);
                log.debug("���X�R�[�h = " + l_strBranchCode);

                //7.4.6.2 get�ڋq�R�[�h(int)
                String l_strAccountCode = l_registTargetAccountUploadCsv.getAccountCode(i);
                log.debug("�ڋq�R�[�h = " + l_strAccountCode);

                try
                {
                    //(*1)��null�ȊO�̏ꍇ
                    if (l_strAccountAndBranchCode != null)
                    {
                        //(*4)�d���`�F�b�N
                        //�ȉ��@@�ƇA���r���A�����l�̏ꍇ��O���X���[
                        //�@@(*1)�̒l
                        //�A[get�ڋq�R�[�h()�̖߂�l]��[get���X�R�[�h()�̖߂�l]�����������l
                        if (l_strAccountAndBranchCode.equals(l_strAccountCode + l_strBranchCode))
                        {
                            log.debug(l_strBranchCode + "," + l_strAccountCode
                                + "�d���ڋq�`�F�b�N�G���[�i�����l�̍s�����݂���j�B");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                                this.getClass().getName() + STR_METHOD_NAME,
                                l_strBranchCode + "," + l_strAccountCode);
                        }
                    }

                    //7.4.6.3 insert�{������(String, String, String, long, boolean)
                    l_dataManager.insertBrowseHistory(
                        l_strInstCode, l_strBranchCode, l_strAccountCode, l_lngDisplayContentsId, false);
                    log.debug("insert�{������(String, String, String, long, boolean)�����s���܂�");
                }
                catch (WEB3BaseException l_ex)
                {
                    //(*4)�d���`�F�b�N�Cinsert�{������()�ɂė�O���X���[���ꂽ�ꍇ
                    //�@@�|�A�b�v���[�h�G���[���X�V����B
                    //�@@�|��ʂɗ�O���X���[����B
                    //save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
                    //�A�b�v���[�h�G���[���A�b�v���[�h�e�[�u���ɍX�V����
                    //[save�A�b�v���[�h�G���[()�Ɏw�肷�����]
                    //�G���[���F�@@�icatch������O�j.getErrorInfo()
                    l_registTargetAccountUploadCsv.saveUploadError(l_ex.getErrorInfo());
                    log.debug(l_ex.getMessage());
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseException(
                        l_ex.getErrorInfo(),
                        getClass().getName() + STR_METHOD_NAME,
                        l_strBranchCode + "," + l_strAccountCode);
                }

                //(*1)�Ɉȉ��̒l���Z�b�g
                //[get�ڋq�R�[�h()�̖߂�l]��[get���X�R�[�h()�̖߂�l]�����������l���Z�b�g
                l_strAccountAndBranchCode = l_strAccountCode + l_strBranchCode;
            }

            //7.4.7save�A�b�v���[�h�I��()
            l_registTargetAccountUploadCsv.saveUploadEnd();
            log.debug("save�A�b�v���[�h�I��()�����s���܂�");

            //7.4.8 delete�A�b�v���[�hTemp()
            l_registTargetAccountUploadCsv.deleteUploadTemp();
            log.debug("delete�A�b�v���[�hTemp()�����s���܂�");
        }
        else
        {
            //8 ����t���[
            try
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                AccountManager l_accMgr = l_finApp.getAccountManager();
                WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_accMgr;
                //8.1 get���X(String, String)
                WEB3GentradeBranch l_branch = l_accountManager.getWeb3GenBranch(l_strInstCode, l_request.branchCode);
                log.debug("get���X(String, String)�����s���܂�");

                //8.2 get�ڋq(String, String, String)
                String l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
                String l_strBranchCode = l_branch.getBranchCode();
                l_accountManager.getMainAccount(l_strInstitutionCode, l_strBranchCode, l_request.accountCode);
                log.debug("get�ڋq(String, String, String)�����s���܂�");
            }
            catch (NotFoundException l_ex)
            {
                log.error("���X or �ڋq�擾�����s����");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //8.3 delete�{������(long)
            l_dataManager.deleteBrowseHistory(l_lngDisplayContentsId);
            log.debug("delete�{������(long)�����s���܂�");

            //8.4 insert�{������(String, String, String, long, boolean)
            l_dataManager.insertBrowseHistory(l_strInstCode, l_request.branchCode, l_request.accountCode, Long.parseLong(l_request.displayContentsUnit.displayContentsId), false);
            log.debug("insert�{������(String, String, String, long, boolean)�����s���܂�");

        }
        //9 createResponse()
        WEB3AdminPvInfoDirectChangeCompleteResponse l_response = (WEB3AdminPvInfoDirectChangeCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (undo�_�C���N�g�w��ύX�A�b�v���[�h)<BR>
     * �_�C���N�g�w��ύX�A�b�v���[�h���~�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X)undo�_�C���N�g�w��ύX�A�b�v���[�h�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�_�C���N�g�w��ύX�A�b�v���[�h���~���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoDirectChangeCancelResponse
     * @@roseuid 4161041602CB
     */
    protected WEB3AdminPvInfoDirectChangeCancelResponse undoDirectChangeUpload(WEB3AdminPvInfoDirectChangeCancelRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " undoDirectChangeUpload(WEB3AdminPvInfoDirectChangeCancelRequest)";
        log.entering(STR_METHOD_NAME );

        //1 validate()
        l_request.validate();

        //2 �o�^�Ώیڋq�A�b�v���[�hCSV(�A�b�v���[�hID : long)
        long l_lngUploadId = Long.parseLong(l_request.uploadId);
        WEB3PvInfoRegistTargetAccountUploadCsv l_registTargetAccountUploadCsv = new WEB3PvInfoRegistTargetAccountUploadCsv(l_lngUploadId);

        //3 delete�A�b�v���[�hTemp()
        l_registTargetAccountUploadCsv.deleteUploadTemp();
        log.debug("delete�A�b�v���[�hTemp()�����s���܂�");

        //4 save�A�b�v���[�h���~()
        l_registTargetAccountUploadCsv.saveUploadStop();
        log.debug("save�A�b�v���[�h���~()�����s���܂�");

        //5  createResponse()
        WEB3AdminPvInfoDirectChangeCancelResponse l_response = (WEB3AdminPvInfoDirectChangeCancelResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�Ǘ��҃_�C���N�g�w��ύXTransactionCallback)<BR>
     * �Ǘ��҃_�C���N�g�w��ύXTransactionCallback�N���X<BR>
     */
    public class WEB3AdminPvInfoDirectChangeTransactionCallback implements TransactionCallback
    {
        /**
         * ���O���[�e�B���e�B
         */
        private  WEB3LogUtility l_log =
            WEB3LogUtility.getInstance(WEB3AdminPvInfoDirectChangeTransactionCallback.class);

        /**
         * (�\�����e���)<BR>
         * �\�����e���<BR>
         */
        private WEB3PvInfoDisplayContentsUnit displayContentsUnit;

        /**
         * (�Ǘ��҃_�C���N�g�w��ύXTransactionCallback)<BR>
         * �R���X�g���N�^<BR>
         * <BR>
         * ����.�\�����e�����Y���̕ϐ��ɕۑ�����B<BR>
         * @@param l_displayContentsUnit - (�\�����e���)<BR>
         * �\�����e���<BR>
         */
        public WEB3AdminPvInfoDirectChangeTransactionCallback(
            WEB3PvInfoDisplayContentsUnit l_displayContentsUnit)
        {
            final String STR_METHOD_NAME = "WEB3AdminPvInfoDirectRegistTransactionCallback("
                + "WEB3PvInfoDisplayContentsUnit)";
            log.entering(STR_METHOD_NAME);

            //����.�\�����e�����Y���̕ϐ��ɕۑ�����B
            this.displayContentsUnit = l_displayContentsUnit;

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * �\�����e�X�V�������s���B<BR>
         * <BR>
         * �P�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.create�\�����eParams()���R�[������B<BR>
         * <BR>
         * [����]<BR>
         * �\�����e���F this.�\�����e���<BR>
         * <BR>
         * �Q�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.update�\�����e()���R�[������B<BR>
         * <BR>
         * [����]<BR>
         * �\�����eParams�F create�\�����eParams()�̖߂�l<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         */
        public Object process()
            throws
            DataNetworkException,
            DataQueryException,
            DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            try
            {
                //�P�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.create�\�����eParams()���R�[������B
                //�\�����e���F this.�\�����e���
                WEB3PvInfoDataManager l_dataManager =
                    (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

                DisplayContentsParams l_dispContentsParam =
                    l_dataManager.createDisplayContentsParams(this.displayContentsUnit);

                //�Q�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.update�\�����e()���R�[������B
                //�\�����eParams�F create�\�����eParams()�̖߂�l
                l_dataManager.updateDisplayContents(l_dispContentsParam);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
