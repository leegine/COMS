head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �g���K�[���s�����T�[�r�XImpl(WEB3AdminDirSecTriggerIssueServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/04/17 �đo�g(���u) �V�K�쐬 ���f��No.116�ANo.118�ANo.119�ANo.120�ANo.121�ANo.123
                                             ���f��No.127�ANo.129
                    : 2008/07/14 ���� �E��(SCS) MQ���b�Z�[�W���M�����̏C��
                                 �E�C���O�̑��M���@@�cWEB3MQGatewayService�N���X��send(����:WEB3MQMessageSpec)
Revision History    : 2008/08/06 ����(���u) ���f��No.137
Revision History    : 2008/08/07 ����(���u) ���f��No.138
*/

package webbroker3.dirsec.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.util.rac.RoundRobinBasedMultiPoolDataSource;
import com.fitechlabs.xtrade.plugin.util.rac.data.MpdsSettingsDao;
import com.fitechlabs.xtrade.plugin.util.rac.data.MpdsSettingsRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3ProductHandlingDivDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.data.SubmitTriggerInfoRow;
import webbroker3.dirsec.define.WEB3AdminDirSecSortKeyItemDef;
import webbroker3.dirsec.define.WEB3AdminDirTableNameDef;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueRecordDetail;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueSortKey;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecTriggerIssueService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mqgateway.manager.message.WEB3MQSendMessageRequest;
import webbroker3.mqgateway.manager.service.delegate.WEB3MQManagementService;
import webbroker3.mqgateway.stdimpls.data.MqMessageIdMappingsRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���K�[���s�����T�[�r�XImpl)<BR>
 * �g���K�[���s�����T�[�r�XImpl�N���X�B<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueServiceImpl implements WEB3AdminDirSecTriggerIssueService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecTriggerIssueServiceImpl.class);

    /**
     * ��t���ԋ敪
     */
    private String tradingTimeType;

    /**
     * @@roseuid 4806E05302F0
     */
    public WEB3AdminDirSecTriggerIssueServiceImpl()
    {

    }

    /**
     * �Ǘ��ҁE�g���K�[���s�������J�n����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�g���K�[���s�����ꗗ���N�G�X�g�̏ꍇ<BR>
     * �@@this.get�g���K�[���s�����ꗗ��ʕ\��()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�g���K�[���s�������̓��N�G�X�g�̏ꍇ<BR>
     * �@@this.get�g���K�[���s�������͉�ʕ\��()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�g���K�[���s�����m�F���N�G�X�g�̏ꍇ<BR>
     * �@@��his.validate�g���K�[���s�����m�F��ʕ\��()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�g���K�[���s�����������N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�g���K�[���s����������ʕ\��()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C2665A0340
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_resposne;

        //�Ǘ��ҁE�g���K�[���s�����ꗗ���N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminDirSecTriggerIssueListRequest)
        {
            l_resposne =
                this.getTriggerIssueListScreenDisplay(
                    (WEB3AdminDirSecTriggerIssueListRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminDirSecTriggerIssueInputRequest)
        {
            l_resposne =
                this.getTriggerIssueInputScreenDisplay(
                    (WEB3AdminDirSecTriggerIssueInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminDirSecTriggerIssueConfirmRequest)
        {
            l_resposne =
                this.validateTriggerIssueConfirmScreenDisplay(
                    (WEB3AdminDirSecTriggerIssueConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminDirSecTriggerIssueCompleteRequest)
        {
            l_resposne =
                this.submitTriggerIssueCompleteScreenDisplay(
                    (WEB3AdminDirSecTriggerIssueCompleteRequest)l_request);
        }

        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_resposne;
    }

    /**
     * (get�g���K�[���s�����ꗗ��ʕ\��)<BR>
     * �g���K�[���s�����ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uget�g���K�[���s�����ꗗ�v�Q�ƁB<BR>
     * ==================================================<BR>
     * �@@�@@��̈ʒu : DIR�Ǘ��҈ȊO�iisDIR�Ǘ���()==false�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_00857<BR>
     * ==================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�g���K�[���s�����ꗗ���N�G�X�g�B<BR>
     * @@return WEB3AdminDirSecTriggerIssueListResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C27BA400BD
     */
    protected WEB3AdminDirSecTriggerIssueListResponse getTriggerIssueListScreenDisplay(
        WEB3AdminDirSecTriggerIssueListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTriggerIssueListScreenDisplay(" +
            "WEB3AdminDirSecTriggerIssueListRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��Ҍ����`�F�b�N������B
        //[validate����()�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"Z0101"(�V�X�e���Ǘ� �g���K�[���s����)
        //is�X�V�Ffalse
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, false);

        //���O�C���Ǘ��҂�"�،���ЊǗ���"
        //�iisDIR�Ǘ���()==false�j�̏ꍇ�A ��O���X���[����B
        if (!l_administrator.isDirAdministrator())
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857 ,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //�f�[�^�擾�����̕�����𐶐�����B
        String l_strSearchConditionList =
            this.createQueryString(WEB3AdminDirTableNameDef.SUBMIT_TRIGGER_INFO);

        //�f�[�^�擾�����̃f�[�^�R���e�i�𐶐�����B
        //[create���������f�[�^�R���e�i()�ɃZ�b�g����p�����[�^]
        //�Q�Ɛ�e�[�u���� �F "submit_trigger_info"
        //�،���ЃR�[�h    �F get�،���ЃR�[�h() �̖߂�l
        //���N�G�X�g�f�[�^     �F null
        Object[] l_objWheres = this.createQueryDataContainer(
            WEB3AdminDirTableNameDef.SUBMIT_TRIGGER_INFO,
            l_strInstitutionCode,
            null);

        //�\�[�g�����������ҏW����B
        String l_strSortKey = this.createSortKey(l_request.sortKeys);

        try
        {
            //���ׂẴN�G���ɗ��p�ł���f�t�H���g�N�G���v���Z�b�T���擾�B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //�u�g���K�[���s���e�[�u���v�ɑ΂��č쐬����SQL�����s���鏈���B
            //[doFindAllQuery()�ɃZ�b�g����p�����[�^]
            //arg0 �F �g���K�[���s���e�[�u��RowType�I�u�W�F�N�g
            //arg1 �F create�擾����������()�̖߂�l
            //arg2 �F create�\�[�g�L�[()�̖߂�l
            //arg3 �F null
            //arg4 �F create���������f�[�^�R���e�i()�̖߂�l
            List l_lisTriggerIssueInfos = l_queryProcessor.doFindAllQuery(
                SubmitTriggerInfoRow.TYPE,
                l_strSearchConditionList,
                l_strSortKey,
                null,
                l_objWheres);

            //�v���ɂ��A���X�g�̃y�[�W���߂��鏈�����s����B
            //[WEB3PageIndexInfo�i�j�Ɏw�肷�����]
            //l_list �F QueryProcessor.doFindAllQuery()�̖߂�l
            //l_intRequestPageIndex �F�@@���N�G�X�g�f�[�^.�\���y�[�W�ԍ���int�^�ɕϊ������l
            //l_intRequestPageSize �F�@@���N�G�X�g�f�[�^.�y�[�W���\���s����int�^�ɕϊ������l
            WEB3PageIndexInfo l_lisViewPageIndexInfo =
                new WEB3PageIndexInfo(
                    l_lisTriggerIssueInfos,
                    Integer.parseInt(l_request.pageIndex),
                    Integer.parseInt(l_request.pageSize));

            //���׃f�[�^�ꗗ�̃��X�g���擾����B
            List l_lisReturnedInfos = l_lisViewPageIndexInfo.getListReturned();

            //�g���K�[���s���Params���A�ꗗ�����쐬����B
            //[create�g���K�[���s���ꗗ���i�j�Ɏw�肷�����]
            //�g���K�[���s���ꗗList �F getListReturned�i�j�Ŏ擾�����l�B
            WEB3AdminDirSecTriggerIssueRecordDetail[] l_adminDirSecTriggerIssueRecordDetails =
            	this.createTriggerIssueInfoList(l_lisReturnedInfos);

            //���X�|���X�f�[�^�𐶐�����B
            WEB3AdminDirSecTriggerIssueListResponse l_response =
                (WEB3AdminDirSecTriggerIssueListResponse)l_request.createResponse();

            //�v���p�e�B�Z�b�g
            //�g���K�[���s���ꗗ:create�g���K�[���s���ꗗ�̖߂�l
            l_response.triggerIssueInfo =
                l_adminDirSecTriggerIssueRecordDetails;
            //���y�[�W��:WEB3StringTypeUtility.formatNumber(getTotalPages()�̖߂�l)
            l_response.totalPages =
                WEB3StringTypeUtility.formatNumber(l_lisViewPageIndexInfo.getTotalPages());
            //�����R�[�h��:WEB3StringTypeUtility.formatNumber(getTotalRecords()�̖߂�l)
            l_response.totalRecords =
                WEB3StringTypeUtility.formatNumber(l_lisViewPageIndexInfo.getTotalRecords());
            //�\���y�[�W�ԍ�:���N�G�X�g.�\���y�[�W�ԍ�
            l_response.pageIndex = l_request.pageIndex;

            return l_response;
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get�g���K�[���s�������͉�ʕ\��)<BR>
     * �g���K�[���s�������͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uget�g���K�[���s�������́v�Q�ƁB<BR>
     * ==================================================<BR>
     * �@@�@@��̈ʒu : DIR�Ǘ��҈ȊO�iisDIR�Ǘ���()==false�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_00857<BR>
     * ==================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�g���K�[���s�������̓��N�G�X�g�B<BR>
     * @@return WEB3AdminDirSecTriggerIssueInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C27BC7004F
     */
    protected WEB3AdminDirSecTriggerIssueInputResponse getTriggerIssueInputScreenDisplay(
        WEB3AdminDirSecTriggerIssueInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTriggerIssueInputScreenDisplay(WEB3AdminDirSecTriggerIssueInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����]
        //�@@�@@�\�J�e�S���R�[�h�F"Z0101"(�V�X�e���Ǘ� �g���K�[���s����)
        //�@@is�X�V�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
            true);

        //isDIR�Ǘ���( )
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();

        //DIR�Ǘ��҈ȊO�iisDIR�Ǘ���()==false�j�̏ꍇ�A��O���X���[����B
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857 ,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //createResponse( )
        WEB3AdminDirSecTriggerIssueInputResponse l_response =
            (WEB3AdminDirSecTriggerIssueInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�g���K�[���s�����m�F��ʕ\��)<BR>
     * �g���K�[���s�����m�F��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uvalidate�g���K�[���s�����m�F�v�Q�ƁB<BR>
     * ==================================================<BR>
     * �@@�@@��̈ʒu : DIR�Ǘ��҈ȊO�iisDIR�Ǘ���()==false�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_00857<BR>
     * ==================================================<BR>
     * <BR>
     * ==================================================<BR>
     * �@@�@@��̈ʒu : �f�[�^�R�[�h�����݂��Ȃ������ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@(doFindAlQuery()�̖߂�l.size = 0) �̓G���[������Ԃ�<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_03070<BR>
     * ==================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�g���K�[���s�����m�F���N�G�X�g�B<BR>
     * @@return WEB3AdminDirSecTriggerIssueConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C27BDD0234
     */
    protected WEB3AdminDirSecTriggerIssueConfirmResponse validateTriggerIssueConfirmScreenDisplay(
        WEB3AdminDirSecTriggerIssueConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTriggerIssueConfirmScreenDisplay(WEB3AdminDirSecTriggerIssueConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //[validate����()�Ɏw�肷�����]
        //�@@�@@�\�J�e�S���R�[�h�F"Z0101"(�V�X�e���Ǘ� �g���K�[���s����)
        //�@@is�X�V�Ftrue
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR�Ǘ���( )
        //�Ǘ��҂�"DIR�Ǘ���"�ł���ꍇ�Atrue��ԋp����B
        //�Ǘ��҂�"�،���ЊǗ���"�ł���ꍇ�Afalse��ԋp����B
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //DIR�Ǘ��҈ȊO�iisDIR�Ǘ���()==false�j�̏ꍇ�A��O���X���[����B
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //�g���K�[���s���e�[�u���Č�������(String, String, String)
        //[�g���K�[���s���e�[�u���Č�������()�ɃZ�b�g����p�����[�^]
        //�@@�،���ЃR�[�h �F get�،���ЃR�[�h() �̖߂�l
        //�@@�f�[�^�R�[�h �@@�F ���N�G�X�g�f�[�^.�g���K�[���s���.�f�[�^�R�[�h
        //�@@�V�F������ �F ���N�G�X�g�f�[�^.�g���K�[���s���.�V�F������
        List l_lisReQueryResults = this.triggerIssueInfoTableReQuery(
            l_strInstitutionCode,
            l_request.triggerIssueInfo.dataCode,
            l_request.triggerIssueInfo.shellName);

        List l_lisTradingTimeRows = new ArrayList();
        WEB3AdminDirSecTriggerIssueRecordDetail l_adminDirSecTriggerIssueRecord = null;
        //�g���K�[���s���e�[�u���Č��������̖߂�l.size() != 0 �̏ꍇ
        //�g���K�[���s���e�[�u���Č��������̖߂�l.size() = 0
        //�̏ꍇ�́Acreate�x�����b�Z�[�W() �̏����ֈڍs�B
        if (l_lisReQueryResults.size() != 0)
        {
            //create�g���K�[���s���R�[�h����(List)
            //[�g���K�[���s���e�[�u���Č������ʎ擾����()�ɃZ�b�g����p�����[�^]
            //�Č������� �F �g���K�[���s���e�[�u���Č�������() �̖߂�l
            l_adminDirSecTriggerIssueRecord = this.createTriggerIssueRecordDetail(l_lisReQueryResults);

            //create��������������(String)
            //[create��������������()�ɃZ�b�g����p�����[�^]
            //�e�[�u���� �F  "MQ_MESSAGE_ID_MAPPINGS"
            String l_strMqMessageQueryString =
                this.createQueryString(WEB3AdminDirTableNameDef.MQ_MESSAGE_ID_MAPPINGS);

            //create���������f�[�^�R���e�i(String, String, �g���K�[���s���R�[�h�ڍ�)
            //[create���������f�[�^�R���e�i()�ɃZ�b�g����p�����[�^]
            //�@@�Q�Ɛ�e�[�u���� �F "MQ_MESSAGE_ID_MAPPINGS"
            //�@@�،���ЃR�[�h    �F get�،���ЃR�[�h() �̖߂�l
            //�@@�������           �F ���N�G�X�g�f�[�^.�g���K�[���s���
            Object[] l_mqMessageSearchDataContainers = this.createQueryDataContainer(
                WEB3AdminDirTableNameDef.MQ_MESSAGE_ID_MAPPINGS,
                l_strInstitutionCode,
                l_request.triggerIssueInfo);

            //doFindAllQuery(arg0 : RowType, arg1 : String, arg2 : Object[])
            //�uMQ_MESSAGE_ID_MAPPING�v�ɑ΂��č쐬����SQL�����s���鏈���B
            //[doFindAllQuery()�ɃZ�b�g����p�����[�^]
            //�@@arg0 �F MQ_MESSAGE_ID_MAPPING�e�[�u��RowType�I�u�W�F�N�g
            //�@@arg1 �F create�擾����������() �̖߂�l
            //�@@arg2 �F create���������f�[�^�R���e�i() �̖߂�l
            //�� arg1�Aarg2 �̒l�ɂ�MQ_MESSAGE_ID_MAPPING�e�[�u�����Q�Ƃ���悤�ɐݒ肵���߂�l���Z�b�g���鎖�B
            List l_lisMqMessageIdMappingsRows = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisMqMessageIdMappingsRows = l_queryProcessor.doFindAllQuery(
                    MqMessageIdMappingsRow.TYPE,
                    l_strMqMessageQueryString,
                    l_mqMessageSearchDataContainers);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() +  "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() +  "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //���b�Z�[�W �f�[�^�R�[�h�����݂��Ȃ������ꍇ(doFindAlQuery()�̖߂�l.size = 0) �̓G���[������Ԃ��B
            //���b�Z�[�W���e�uMQ_MESSAGE_ID_MAPPINGS�e�[�u���Ƀf�[�^������܂���B�v���o�͂���B
            if (l_lisMqMessageIdMappingsRows.size() == 0)
            {
                log.debug("MQ_MESSAGE_ID_MAPPINGS�e�[�u���Ƀf�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03070,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "MQ_MESSAGE_ID_MAPPINGS�e�[�u���Ƀf�[�^������܂���B");
            }

            //create��������������(String)
            //�f�[�^�擾�����̕�����𐶐�����B
            //[create��������������()�ɃZ�b�g����p�����[�^]
            //�@@�e�[�u���� �F  "trading_time"
            String l_strTradingTimeQueryString =
                this.createQueryString(WEB3AdminDirTableNameDef.TRADING_TIME);

            //create���������f�[�^�R���e�i(String, String, �g���K�[���s���R�[�h�ڍ�)
            //[create���������f�[�^�R���e�i()�ɃZ�b�g����p�����[�^]
            //�@@�Q�Ɛ�e�[�u���� �F "trading_time"
            //�@@�،���ЃR�[�h    �F get�،���ЃR�[�h() �̖߂�l
            //�@@�������           �F ���N�G�X�g�f�[�^.�g���K�[���s���
            Object[] l_tradingTimeDataContainers = this.createQueryDataContainer(
                WEB3AdminDirTableNameDef.TRADING_TIME,
                l_strInstitutionCode,
                l_request.triggerIssueInfo);

            //is������ԃe�[�u������(String, Object[])
            //[is������ԃe�[�u�������ɃZ�b�g����p�����[�^]
            //�@@�擾���������� = create��������������()�̖߂�l
            //�@@�f�[�^�R���e�i = creata���������f�[�^�R���e�i()�̖߂�l
            l_lisTradingTimeRows = this.isTradingTimeTableQuery(
                l_strTradingTimeQueryString,
                l_tradingTimeDataContainers);
        }

        //[create�x�����b�Z�[�W() �ɃZ�b�g����p�����[�^]
        //�@@�Č������� �F �g���K�[���s���e�[�u���Č������� �̖߂�l
        //�@@�������TBL���R�[�h�ꗗ �F is������ԃe�[�u������() �̖߂�l
        String[] l_strWarningMessages =
            this.createWarningMessage(l_lisReQueryResults, l_lisTradingTimeRows);

        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminDirSecTriggerIssueConfirmResponse l_response =
            (WEB3AdminDirSecTriggerIssueConfirmResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ɉȉ��̓��e��ݒ肷��B
        //�g���K�[���s��� : create�g���K�[���s���R�[�h����()�̖߂�l
        //�x�����b�Z�[�W : create�x�����b�Z
        l_response.triggerIssueInfo = l_adminDirSecTriggerIssueRecord;
        l_response.messageWarning = l_strWarningMessages;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�g���K�[���s����������ʕ\��)<BR>
     * �g���K�[���s����������ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usubmit�g���K�[���s���������v�Q�ƁB<BR>
     * ==================================================<BR>
     * �@@�@@��̈ʒu : DIR�Ǘ��҈ȊO�iisDIR�Ǘ���()==false�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_00857<BR>
     * ==================================================<BR>
     * <BR>
     * ==================================================<BR>
     * �@@�@@��̈ʒu : �f�[�^�R�[�h�����݂��Ȃ������ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@(doFindAlQuery()�̖߂�l.size = 0) �̓G���[������Ԃ�<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_03070<BR>
     * ==================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�g���K�[���s�����������N�G�X�g�B<BR>
     * @@return WEB3AdminDirSecTriggerIssueCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C27BFA00EC
     */
    protected WEB3AdminDirSecTriggerIssueCompleteResponse submitTriggerIssueCompleteScreenDisplay(
        WEB3AdminDirSecTriggerIssueCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitTriggerIssueCompleteScreenDisplay(WEB3AdminDirSecTriggerIssueCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //[validate����()�Ɏw�肷�����]
        //�@@�@@�\�J�e�S���R�[�h�F"Z0101"(�V�X�e���Ǘ� �g���K�[���s����)
        //�@@is�X�V�Ftrue
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR�Ǘ���( )
        //�Ǘ��҂�"DIR�Ǘ���"�ł���ꍇ�Atrue��ԋp����B
        //�Ǘ��҂�"�،���ЊǗ���"�ł���ꍇ�Afalse��ԋp����B
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //DIR�Ǘ��҈ȊO�iisDIR�Ǘ���()==false�j�̏ꍇ�A��O���X���[����B
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //validate����p�X���[�h(�p�X���[�h : String)
        //[validate����p�X���[�h()�ɃZ�b�g����p�����[�^]
        //�@@�p�X���[�h �F ���N�G�X�g�f�[�^.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //create��������������(String)
        //[create��������������()�ɃZ�b�g����p�����[�^]
        //�e�[�u���� �F  "MQ_MESSAGE_ID_MAPPINGS"
        String l_strQueryString =
            this.createQueryString(WEB3AdminDirTableNameDef.MQ_MESSAGE_ID_MAPPINGS);

        //create���������f�[�^�R���e�i(String, String, �g���K�[���s���R�[�h�ڍ�)
        //[create���������f�[�^�R���e�i()�ɃZ�b�g����p�����[�^]
        //�@@�Q�Ɛ�e�[�u���� �F "MQ_MESSAGE_ID_MAPPINGS"
        //�@@�،���ЃR�[�h    �F get�،���ЃR�[�h() �̖߂�l
        //�@@�������           �F ���N�G�X�g�f�[�^.�g���K�[���s���
        Object[] l_queryDataContainers = this.createQueryDataContainer(
            WEB3AdminDirTableNameDef.MQ_MESSAGE_ID_MAPPINGS,
            l_strInstitutionCode,
            l_request.triggerIssueInfo);

        //getDefaultProcessor( )
        //���ׂẴN�G���ɗ��p�ł���f�t�H���g�N�G���v���Z�b�T���擾�B
        List l_lisMqMessageIdMappingsRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //doFindAllQuery(arg0 : RowType, arg1 : String, arg2 : Object[])
            //�uMQ_MESSAGE_ID_MAPPING�v�ɑ΂��č쐬����SQL�����s���鏈���B
            //[doFindAllQuery()�ɃZ�b�g����p�����[�^]
            //�@@arg0 �F MQ_MESSAGE_ID_MAPPING�e�[�u��RowType�I�u�W�F�N�g
            //�@@arg1 �F create�擾����������() �̖߂�l
            //�@@arg2 �F create���������f�[�^�R���e�i() �̖߂�l
            l_lisMqMessageIdMappingsRows = l_queryProcessor.doFindAllQuery(
                MqMessageIdMappingsRow.TYPE,
                l_strQueryString,
                l_queryDataContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�f�[�^�R�[�h�����݂��Ȃ������ꍇ(doFindAlQuery()�̖߂�l.size = 0) �̓G���[������Ԃ��B
        //���b�Z�[�W���e�uMQ_MESSAGE_ID_MAPPINGS�e�[�u���Ƀf�[�^������܂���B�v���o�͂���B
        if (l_lisMqMessageIdMappingsRows.size() == 0)
        {
            log.debug("MQ_MESSAGE_ID_MAPPINGS�e�[�u���Ƀf�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03070,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "MQ_MESSAGE_ID_MAPPINGS�e�[�u���Ƀf�[�^������܂���B");
        }

        /* 2008/07/14 add start */
        //MQ���N�G�X�g�̍쐬
        WEB3MQSendMessageRequest mqRequest = new WEB3MQSendMessageRequest();

        //���N�G�X�g�p�����[�^�̃Z�b�g
        mqRequest.institutionCode = l_strInstitutionCode;
        mqRequest.dataCode = l_request.triggerIssueInfo.dataCode;
        mqRequest.userData = l_request.triggerIssueInfo.userData;

        //�ڋqID(���`��)�̐ݒ�
        mqRequest.accountIdStart = "100000000000000";
        mqRequest.accountIdEnd   = "999999999999999";

        // OracleSID �擾����
        mqRequest.oracleSID = getDefaultOracleSid();

        //���ݓ��t�̎擾
        Timestamp l_mqSystemTimestamp = GtlUtils.getSystemTimestamp();
        String l_strSystemDate =
        	WEB3DateUtility.formatDate(l_mqSystemTimestamp, "yyyyMMdd");
        mqRequest.bizDate = l_strSystemDate;

        //MQ���b�Z�[�W���M����
        WEB3MQManagementService l_mqManagementService =
        	(WEB3MQManagementService)Services.getService(WEB3MQManagementService.class);
        l_mqManagementService.execute(mqRequest);
        /* 2008/07/14 add end */

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminDirSecTriggerIssueCompleteResponse l_response =
            (WEB3AdminDirSecTriggerIssueCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create��������������)<BR>
     * �f�[�^���擾����ׂ̏����𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�������̍쐬<BR>
     * �@@�Q�|�P�j �P�j�ō쐬������̕�����ɏ��������Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�Z�b�g����������͈����œn���ꂽ�e�[�u�����ɂ���ĕω��B<BR>
     * <BR>
     * �@@(�e�[�u���� �F ������)<BR>
     * �@@�@@�Esubmit_trigger_info �F "institution_code=? and<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@product_handling_div = ?"<BR>
     * <BR>
     * �@@�@@�EMQ_MESSAGE_ID_MAPPINGS �F "institution_code=? and<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@data_code=?"<BR>
     * <BR>
     * �@@�@@�Etrading_time �F "institution_code = ? and<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@branch_code = ? and<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@trading_time_type = ? and<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@biz_date_type = ? and<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@start_time <= ? and<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@end_time >= ?�@@and<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@submit_market_trigger = ?"<BR>
     * <BR>
     * �R�j �������ԋp����B<BR>
     * @@param l_strTableName - (�e�[�u����)<BR>
     * �����Ώې�̃e�[�u�����B<BR>
     * @@return String
     * @@roseuid 47C6153100BD
     */
    private String createQueryString(String l_strTableName)
    {
        final String STR_METHOD_NAME = "createQueryString(String)";
        log.entering(STR_METHOD_NAME);

        //��̕�����𐶐�����B
        String l_strSearchConditionList = null;

        //�쐬������̕�����ɏ��������Z�b�g�B
        //�Z�b�g����������͈����œn���ꂽ�e�[�u�����ɂ���ĕω��B
        //submit_trigger_info �F "institution_code=? and
        //product_handling_div = ?"
        if (WEB3AdminDirTableNameDef.SUBMIT_TRIGGER_INFO.equals(l_strTableName))
        {
        	l_strSearchConditionList = "institution_code = ? and product_handling_div = ?";
        }
        //MQ_MESSAGE_ID_MAPPINGS �F "institution_code = ? and data_code = ?"
        else if (WEB3AdminDirTableNameDef.MQ_MESSAGE_ID_MAPPINGS.equals(l_strTableName))
        {
        	l_strSearchConditionList = "institution_code = ? and data_code = ?";
        }
        //trading_time �F "institution_code = ? and
        //        branch_code = ? and
        //�@@�@@�@@�@@trading_time_type = ? and
        //�@@�@@�@@�@@biz_date_type = ? and
        //�@@�@@�@@�@@start_time <= ? and
        //�@@�@@�@@�@@end_time >= ?�@@and
        //�@@�@@�@@�@@submit_market_trigger = ?"
        else if (WEB3AdminDirTableNameDef.TRADING_TIME.equals(l_strTableName))
        {
        	l_strSearchConditionList =
                "institution_code = ? and " +
                "branch_code = ? and " +
                "trading_time_type = ? and " +
                "biz_date_type = ? and " +
                "start_time <= ? and " +
                "end_time >= ? and " +
                "submit_market_trigger = ?";
        }

        //�������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strSearchConditionList;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * �擾�����f�[�^�R���e�i�쐬�B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�����F�،���ЃR�[�h ���P�j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�����F�Q�Ɛ�e�[�u���� �ɂ���ăZ�b�g����l��ω�������B<BR>
     * <BR>
     * �@@�R�|�P�j�e�[�u���� �F submit_trigger_info �̏ꍇ�B<BR>
     * �@@�@@�@@�R�|�P�|�P�j �P�j��ArrayList�� "0" ��ǉ�����B<BR>
     * <BR>
     * �@@�R�|�Q�j�e�[�u���� �F MQ_MESSAGE_ID_MAPPINGS �̏ꍇ�B<BR>
     * �@@�@@�@@�R�|�Q�|�P�j �����F�������.�f�[�^�R�[�h ���P�j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�R�|�R�j�e�[�u���� �F trading_time �̏ꍇ�B<BR>
     * �@@�@@�@@�R�|�R�|�P�j �Ǘ��҃N���X�uWEB3Administrator�v�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�R�|�R�|�Q�j �R�|�R�|�P�j�ō쐬�����C���X�^���X.getBranchCode()�����s�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@getBranchCode() �̖߂�l���P�j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�R�|�R�|�R�j ��t���ԋ敪�̃Z�b�g����B<BR>
     * �@@�@@�@@�@@�R�|�R�|�R�|�P�j this.get��t���ԋ敪() �̖߂�l != null �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.get��t���ԋ敪() �̖߂�l ���P�j��ArrayList�ɒǉ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�R�|�R�|�R�|�Q�j this.get��t���ԋ敪() �̖߂�l == null �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@return null �����s�������𔲂���B<BR>
     * <BR>
     * �@@�@@�@@�R�|�R�|�S�j ������ԊǗ��N���X����AgetBizDateType() ���Ăяo���B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@getBizDateType() �̖߂�l���P�j��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[getBizDateType�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@TimeStamp �F TradingSystem.getSystemTimestamp()�̖߂�l<BR>
     * <BR>
     * �@@�@@�@@�R�|�R�|�T�j �P�j��ArrayList�� ���ݎ��� ��ǉ��B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(������ start_time �ɑΉ�)<BR>
     * <BR>
     * �@@�@@�@@�R�|�R�|�U�j �P�j��ArrayList�� ���ݎ��� ��ǉ��B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(������ end_time �ɑΉ�)<BR>
     * <BR>
     * �@@�@@�@@�R�|�R�|�V�j �P�j��ArrayList�� "1" ��ǉ��B<BR>
     * <BR>
     * �S�j�ǉ�����ArrayList�ɑ΂��āAtoArray()�����s�B<BR>
     * �@@�@@�z����擾���A�ԋp����B<BR>
     * @@param l_strReferencedTableName - (�Q�Ɛ�e�[�u����)<BR>
     * from �ɂ�����e�[�u������ێ�<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_queryInfo - (�������)<BR>
     * �O��������󂯎�������N�G�X�g�f�[�^��ێ�<BR>
     * @@return Object[]
     * @@roseuid 47C6163F00EE
     */
    private Object[] createQueryDataContainer(
        String l_strReferencedTableName,
        String l_strInstitutionCode,
        WEB3AdminDirSecTriggerIssueRecordDetail l_queryInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String," +
            " String, WEB3AdminDirSecTriggerIssueRecordDetail)";
        log.entering(STR_METHOD_NAME);

        //���ArrayList�𐶐�����B
        List l_lisSearchConditionDataContainers = new ArrayList();

        //�����F�،���ЃR�[�h ���P�j��ArrayList�ɒǉ�����B
        l_lisSearchConditionDataContainers.add(l_strInstitutionCode);

        //�����F�Q�Ɛ�e�[�u���� �ɂ���ăZ�b�g����l��ω�������B
        //�e�[�u���� �F submit_trigger_info �̏ꍇ�BArrayList�� "0" ��ǉ�����B
        if (WEB3AdminDirTableNameDef.SUBMIT_TRIGGER_INFO.equals(l_strReferencedTableName))
        {
            l_lisSearchConditionDataContainers.add(WEB3ProductHandlingDivDef.HANDLING);
        }
        //�e�[�u���� �F MQ_MESSAGE_ID_MAPPINGS �̏ꍇ�B
        //�R�|�Q�|�P�j �����F�������.�f�[�^�R�[�h ���P�j��ArrayList�ɒǉ�����B
        else if (WEB3AdminDirTableNameDef.MQ_MESSAGE_ID_MAPPINGS.equals(l_strReferencedTableName))
        {
            l_lisSearchConditionDataContainers.add(l_queryInfo.dataCode);
        }
        //�e�[�u���� �F trading_time �̏ꍇ�B
        else if (WEB3AdminDirTableNameDef.TRADING_TIME.equals(l_strReferencedTableName))
        {
            //�Ǘ��҃N���X�uWEB3Administrator�v�̃C���X�^���X�𐶐�����B
            WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

            //�쐬�����C���X�^���X.getBranchCode() �����s�BgetBranchCode() �̖߂�l���P�j��ArrayList�ɒǉ�����B
            l_lisSearchConditionDataContainers.add(l_administrator.getBranchCode());

            //��t���ԋ敪�̃Z�b�g����
            String l_strTradingTimeType = this.getTradingTimeType();
            if (l_strTradingTimeType != null)
            {
                //this.get��t���ԋ敪() �̖߂�l != null �̏ꍇ
                //this.get��t���ԋ敪() �̖߂�l ���P�j��ArrayList�ɒǉ�
                l_lisSearchConditionDataContainers.add(l_strTradingTimeType);
            }
            else
            {
                //this.get��t���ԋ敪() �̖߂�l == null �̏ꍇ
                // return null �����s�������𔲂���
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //������ԊǗ��N���X����AgetBizDateType() ���Ăяo���B
            //  getBizDateType() �̖߂�l���P�j��ArrayList�ɒǉ�����
            String l_strBizDateType =
                WEB3GentradeTradingTimeManagement.getBizDateType(GtlUtils.getSystemTimestamp());
            l_lisSearchConditionDataContainers.add(l_strBizDateType);

            //ArrayList�� ���ݎ��� ��ǉ��B(������ start_time �ɑΉ�)
            l_lisSearchConditionDataContainers.add(
                WEB3DateUtility.formatDate(
                    GtlUtils.getSystemTimestamp(), WEB3GentradeTimeDef.TIME_FORMAT_HMS));

            //ArrayList�� ���ݎ��� ��ǉ��B(������ end_time �ɑΉ�)
            l_lisSearchConditionDataContainers.add(
                WEB3DateUtility.formatDate(
                    GtlUtils.getSystemTimestamp(), WEB3GentradeTimeDef.TIME_FORMAT_HMS));

            //ArrayList�� "1" ��ǉ��B
            l_lisSearchConditionDataContainers.add(WEB3SubmitMarketTriggerDef.TRIGGER);
        }

        //�ǉ�����ArrayList�ɑ΂��āAtoArray()�����s�B
        Object[] l_searchConditionDataContainers = new Object[l_lisSearchConditionDataContainers.size()];
        l_lisSearchConditionDataContainers.toArray(l_searchConditionDataContainers);

        //�z����擾���A�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_searchConditionDataContainers;
    }

    /**
     * (create�\�[�g�L�[)<BR>
     * �\�[�g�����������ҏW����B<BR>
     * <BR>
     * �e�[�u���񕨗������g�p���A�ȉ��̒ʂ�A�\�[�g����������iorder by��j��ҏW����B<BR>
     * <BR>
     * �P�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A<BR>
     * �@@�@@�@@�\�[�g������������쐬����B<BR>
     * <BR>
     * �@@�P�|�P�j�\�[�g�L�[.�L�[���ڂɑΉ�����e�[�u���񕨗������\�[�g�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@[�\�[�g�L�[.�L�[���� = �f�[�^�R�[�h �̏ꍇ]<BR>
     * �@@�@@�@@�g���K�[���s���e�[�u��.request_code<BR>
     * <BR>
     * �@@�@@[�\�[�g�L�[.�L�[���� = �Ĕ��s�\�t���O �̏ꍇ]<BR>
     * �@@�@@�@@�g���K�[���s���e�[�u��.enable_submit_trigger_flag<BR>
     * <BR>
     * �@@�P�|�Q�j�\�[�g�����ɊY������\�[�g������ҏW����B<BR>
     * �@@�@@�@@�@@�@@�����Fasc<BR>
     * �@@�@@�@@�@@�@@�~���Fdesc<BR>
     * <BR>
     * �Q�j �쐬�����\�[�g�����������ԋp����B<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[�ێ�<BR>
     * @@return String
     * @@roseuid 47C6199A0357
     */
    private String createSortKey(WEB3AdminDirSecTriggerIssueSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortKey(WEB3AdminDirSecTriggerIssueSortKey[])";
        log.entering(STR_METHOD_NAME);

        //�\�[�g����������I�u�W�F�N�g(�FString)���쐬����B
        StringBuffer l_sbSortKey = new StringBuffer();

        //�\�[�g�L�[.�L�[���ڂɑΉ�����e�[�u���񕨗������\�[�g�����ɒǉ�����
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            if (WEB3AdminDirSecSortKeyItemDef.DATA_CODE.equals(l_sortKeys[i].keyItem))
            {
                //[�\�[�g�L�[.�L�[���� = �f�[�^�R�[�h �̏ꍇ]
                // �g���K�[���s���e�[�u��.request_code
                l_sbSortKey.append(" request_code ");
            }
            else if (WEB3AdminDirSecSortKeyItemDef.REISSUE_POSSIBLE_FLAG.equals(l_sortKeys[i].keyItem))
            {
                //[�\�[�g�L�[.�L�[���� = �Ĕ��s�\�t���O �̏ꍇ]
                //�g���K�[���s���e�[�u��.enable_submit_trigger_flag
                l_sbSortKey.append(" enable_submit_trigger_flag ");
            }
            else
            {
                continue;
            }

            //�\�[�g�����ɊY������\�[�g������ҏW����
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                //�����Fasc
                l_sbSortKey.append(" ASC ,");
            }
            else
            {
                //�~���Fdesc
                l_sbSortKey.append(" DESC ,");
            }
        }

        //�쐬�����\�[�g�����������ԋp����B
        if (l_sbSortKey.length() > 0)
        {
            l_sbSortKey = l_sbSortKey.deleteCharAt(l_sbSortKey.length() - 1);
        }

        log.exiting(STR_METHOD_NAME);
        return l_sbSortKey.toString();
    }

    /**
     * (create�g���K�[���s���ꗗ)<BR>
     * �g���K�[���s���Params���A�ꗗ�����쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�I�u�W�F�N�g�̐����B<BR>
     * <BR>
     * �Q�j�@@���� : �g���K�[���s���ꗗList�̗v�f���ALoop�������s���B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�g���K�[���s���R�[�h�ڍ׃N���X�̃I�u�W�F�N�g�𐶐��B<BR>
     * �@@�Q�|�Q�j�@@�g���K�[���s���ꗗList����g���K�[���s���Row�����o���B<BR>
     * �@@�Q�|�R�j�@@�Q�|�P�j�Ő��������I�u�W�F�N�g�Ɉȉ��̓��e���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�E �g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�V�F������<BR>
     * �@@�@@�@@�@@�@@= �g���K�[���s���e�[�u��Row.get�V�F������<BR>
     * �@@�@@�E �g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�g���K�[����<BR>
     * �@@�@@�@@�@@�@@= �g���K�[���s���e�[�u��Row.get�g���K�[����<BR>
     * �@@�@@�E �g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�Ĕ��s�\�t���O<BR>
     * �@@�@@�@@�@@�@@= �g���K�[���s���e�[�u��Row.get�Ĕ��s�\�t���O<BR>
     * �@@�@@�E �g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.���[�U�[�f�[�^<BR>
     * �@@�@@�@@�@@�@@= �g���K�[���s���e�[�u��Row.get���[�U�[�f�[�^<BR>
     * �@@�@@�E �g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�f�[�^�R�[�h<BR>
     * �@@�@@�@@�@@�@@= �g���K�[���s���e�[�u��Row.get�f�[�^�R�[�h<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�P�j�Ő�������ArrayList�I�u�W�F�N�g<BR>
     * �@@�@@�Ƀg���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g��add()����B<BR>
     * <BR>
     * �R�j�@@�g���K�[���s���R�[�h�ڍ׃N���X�^�̔z��I�u�W�F�N�g<BR>
     * �@@�@@��ArrayList�I�u�W�F�N�g�̃T�C�Y�Ő�������B<BR>
     * <BR>
     * �S�j�@@toArray()�ŁA���X�g���̗v�f���i�[����z��I�u�W�F�N�g�ɕϊ�����B<BR>
     * <BR>
     * �@@�@@ArrayList�I�u�W�F�N�g.toArray(�g���K�[���s���R�[�h�ڍ׃N���X�^�̔z��I�u�W�F�N�g); <BR>
     * <BR>
     * �T�j�@@�ϊ������z��I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_lisTriggerIssueInfos - (�g���K�[���s���ꗗList)<BR>
     * �g���K�[���s���ꗗList<BR>
     * @@return WEB3AdminDirSecTriggerIssueRecordDetail[]
     * @@roseuid 47E0E17D0011
     */
    private WEB3AdminDirSecTriggerIssueRecordDetail[] createTriggerIssueInfoList(List l_lisTriggerIssueInfos)
    {
        final String STR_METHOD_NAME = "createTriggerIssueInfoList(List)";
        log.entering(STR_METHOD_NAME);

        //ArrayList�I�u�W�F�N�g�̐����B
        List l_lisTriggerIssueInfoLists = new ArrayList();

        //���� : �g���K�[���s���ꗗList�̗v�f���ALoop�������s���B
        Iterator l_iterator = l_lisTriggerIssueInfos.iterator();
        WEB3AdminDirSecTriggerIssueRecordDetail l_adminDirSecTriggerIssueRecordDetail = null;
        SubmitTriggerInfoRow l_submitTriggerInfoRow = null;
        while (l_iterator.hasNext())
        {
        	//�g���K�[���s���R�[�h�ڍ׃N���X�̃I�u�W�F�N�g�𐶐��B
        	l_adminDirSecTriggerIssueRecordDetail = new WEB3AdminDirSecTriggerIssueRecordDetail();
        	//�g���K�[���s���ꗗList����g���K�[���s���Row�����o���B
        	l_submitTriggerInfoRow = (SubmitTriggerInfoRow)l_iterator.next();

            //���������I�u�W�F�N�g�Ɉȉ��̓��e���Z�b�g����B
            //�g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�V�F������=�g���K�[���s���e�[�u��Row.get�V�F������
            //�g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�g���K�[����=�g���K�[���s���e�[�u��Row.get�g���K�[����
            //�g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�Ĕ��s�\�t���O=�g���K�[���s���e�[�u��Row.get�Ĕ��s�\�t���O
            //�g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.���[�U�[�f�[�^=�g���K�[���s���e�[�u��Row.get���[�U�[�f�[�^
            //�g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�f�[�^�R�[�h=�g���K�[���s���e�[�u��Row.get�f�[�^�R�[�h
        	l_adminDirSecTriggerIssueRecordDetail.shellName = l_submitTriggerInfoRow.getJobId();
        	l_adminDirSecTriggerIssueRecordDetail.triggerName = l_submitTriggerInfoRow.getTriggerId();
            l_adminDirSecTriggerIssueRecordDetail.reissuePossibleFlag =
                l_submitTriggerInfoRow.getEnableSubmitTriggerFlag();
        	l_adminDirSecTriggerIssueRecordDetail.userData = l_submitTriggerInfoRow.getUserData();
        	l_adminDirSecTriggerIssueRecordDetail.dataCode = l_submitTriggerInfoRow.getRequestCode();

        	//��������ArrayList�I�u�W�F�N�g�Ƀg���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g��add()����B
        	l_lisTriggerIssueInfoLists.add(l_adminDirSecTriggerIssueRecordDetail);
        }

        //�g���K�[���s���R�[�h�ڍ׃N���X�^�̔z��I�u�W�F�N�g��ArrayList�I�u�W�F�N�g�̃T�C�Y�Ő�������B
        WEB3AdminDirSecTriggerIssueRecordDetail[] l_adminDirSecTriggerIssueRecordDetails =
        	new WEB3AdminDirSecTriggerIssueRecordDetail[l_lisTriggerIssueInfoLists.size()];

        //toArray()�ŁA���X�g���̗v�f���i�[����z��I�u�W�F�N�g�ɕϊ�����B
        l_lisTriggerIssueInfoLists.toArray(l_adminDirSecTriggerIssueRecordDetails);

        //�ϊ������z��I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_adminDirSecTriggerIssueRecordDetails;
    }

    /**
     * (�g���K�[���s���e�[�u���Č�������)<BR>
     * ���N�G�X�g����n���ꂽ�l�����ɁA�g���K�[���s���e�[�u�����ēx�������s�������B<BR>
     * <BR>
     * �P�j�����O�����B<BR>
     * �@@�P�|�P�j ���L�̕ϐ���p�ӁB<BR>
     * �@@�@@�@@ ���String<BR>
     * �@@�@@�A ���ArrayList<BR>
     * <BR>
     * �@@�P�|�Q�j �@@�̕ϐ��ɉ��L�̕�������i�[����B<BR>
     * �@@�@@�@@"institution_code = ? and request_code = ? and job_id = ?"<BR>
     * <BR>
     * �@@�P�|�R�j �A�̕ϐ��Ɉ����œn���ꂽ�l<BR>
     * �@@�@@�@@���u�،���ЃR�[�h�v�u�f�[�^�R�[�h�v�u�V�F�����́v�̏��Œǉ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@ArrayList.add(����:�،���ЃR�[�h);<BR>
     * �@@�@@�@@�@@�@@�@@ArrayList.add(����:�f�[�^�R�[�h);<BR>
     * �@@�@@�@@�@@�@@�@@ArrayList.add(����:�V�F������);<BR>
     * <BR>
     * �Q�jgetDefaultProcessor() �����s�B<BR>
     * <BR>
     * �R�jdoFindAllQuery() �����s�A�����͈ȉ��̒ʂ�B<BR>
     * �@@�@@arg0 �F �g���K�[���s���e�[�u��RowType�I�u�W�F�N�g<BR>
     * �@@�@@arg1 �F �@@�̕ϐ�<BR>
     * �@@�@@arg2 �F �A�̕ϐ�.toArray<BR>
     * <BR>
     *�S�j��t���ԋ敪���擾�B<BR>
     * �@@�S�|�P�j �R�j�̖߂�l����Row�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�S�|�Q�j this.set��t���ԋ敪���Ăяo���B<BR>
     * �@@�@@�@@�@@[set��t���ԋ敪�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�Č������t���ԋ敪 �F<BR>
     * �@@�@@�@@�@@�@@�S�|�P�j�Ŏ擾����Row�I�u�W�F�N�g.getColumn("trading_time_type")�̖߂�l�B<BR>
     * <BR>
     * �T�j �R�j�̖߂�l��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �g���K�[���s�������͉�ʂ���擾����"�،���ЃR�[�h"<BR>
     * @@param l_strDataCode - (�f�[�^�R�[�h)<BR>
     * �g���K�[���s�������͉�ʂ���擾����"�f�[�^�R�[�h"<BR>
     * @@param l_strShellName - (�V�F������)<BR>
     * �g���K�[���s�������͉�ʂ���擾����"�V�F������"<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 47E712BF001B
     */
    private List triggerIssueInfoTableReQuery(
        String l_strInstitutionCode,
        String l_strDataCode,
        String l_strShellName) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "triggerIssueInfoTableReQuery(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //String�̕ϐ��ɉ��L�̕�������i�[����B
        //"institution_code = ? and request_code = ? and job_id = ?"
        String l_strSql = " institution_code = ? and request_code = ? and job_id = ? ";

        //ArrayList�̕ϐ��Ɉ����œn���ꂽ�l���u�،���ЃR�[�h�v�u�f�[�^�R�[�h�v�u�V�F�����́v
        //�̏��Œǉ��B
        //  ArrayList.add(����:�،���ЃR�[�h);
        //  ArrayList.add(����:�f�[�^�R�[�h);
        //  ArrayList.add(����:�V�F������);
        List l_lisSqlValues = new ArrayList();
        l_lisSqlValues.add(l_strInstitutionCode);
        l_lisSqlValues.add(l_strDataCode);
        l_lisSqlValues.add(l_strShellName);

        //doFindAllQuery() �����s�A�����͈ȉ��̒ʂ�B
        //�@@arg0 �F �g���K�[���s���e�[�u��RowType�I�u�W�F�N�g
        //�@@arg1 �F �@@�̕ϐ�
        //�@@arg2 �F �A�̕ϐ�.toArray
        List l_lisResults;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults = l_queryProcessor.doFindAllQuery(
                SubmitTriggerInfoRow.TYPE,
                l_strSql,
                l_lisSqlValues.toArray());
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //��t���ԋ敪���擾�B
        if (l_lisResults.size() > 0)
        {
            SubmitTriggerInfoRow l_submitTriggerInfoRow =
                (SubmitTriggerInfoRow)l_lisResults.get(0);
            //this.set��t���ԋ敪���Ăяo���B
            //�@@[set��t���ԋ敪�ɃZ�b�g����p�����[�^]
            //�@@�Č������t���ԋ敪 �F
            //   �S�|�P�j�Ŏ擾����Row�I�u�W�F�N�g.getColumn("trading_time_type")�̖߂�l�B
            this.setTradingTimeType(l_submitTriggerInfoRow.getTradingTimeType());
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisResults;
    }

    /**
     * (set��t���ԋ敪)<BR>
     * �g���K�[���s���e�[�u���Č�������() �ɂĎ擾������t���ԋ敪���Z�b�g���郁�\�b�h�B<BR>
     * <BR>
     * set��t���ԋ敪(�Č������t���ԋ敪){<BR>
     * �@@�@@this.��t���ԋ敪 = �Č������t���ԋ敪<BR>
     * }<BR>
     * @@param l_strTradingTimeType - (�Č������t���ԋ敪)<BR>
     * �Č������t���ԋ敪��ێ��B<BR>
     */
    private void setTradingTimeType(String l_strTradingTimeType)
    {
        final String STR_METHOD_NAME = "setTradingTimeType(String)";
        log.entering(STR_METHOD_NAME);

        //this.��t���ԋ敪 = �Č������t���ԋ敪
        this.tradingTimeType = l_strTradingTimeType;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get��t���ԋ敪)<BR>
     * �g���K�[���s���e�[�u���Č�������() �ɂĎ擾������t���ԋ敪���擾���郁�\�b�h�B<BR>
     * <BR>
     * get��t���ԋ敪(){<BR>
     * �@@�@@return this.��t���ԋ敪<BR>
     * }<BR>
     * @@return String
     */
    private String getTradingTimeType()
    {
        return this.tradingTimeType;
    }

    /**
     * (create�g���K�[���s���R�[�h����)<BR>
     * �u�g���K�[���s���e�[�u���Č��������v�̖߂�l����Č������ʂ��擾����B<BR>
     * <BR>
     * �P�j�g���K�[���s���R�[�h���׃I�u�W�F�N�g�𐶐��B<BR>
     * <BR>
     * �Q�j����:�Č������ʂ���g���K�[���s���e�[�u��Row�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �R�j �P�j�ō쐬�����I�u�W�F�N�g�ɑ΂��āA�Č������ʂ��Z�b�g����B<BR>
     * �@@�@@�E �g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�V�F������<BR>
     * �@@�@@�@@�@@�@@= �g���K�[���s���e�[�u��Row.get�V�F������<BR>
     * �@@�@@�E �g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�g���K�[����<BR>
     * �@@�@@�@@�@@�@@= �g���K�[���s���e�[�u��Row.get�g���K�[����<BR>
     * �@@�@@�E �g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�Ĕ��s�\�t���O<BR>
     * �@@�@@�@@�@@�@@= �g���K�[���s���e�[�u��Row.get�Ĕ��s�\�t���O<BR>
     * �@@�@@�E �g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.���[�U�[�f�[�^<BR>
     * �@@�@@�@@�@@�@@= �g���K�[���s���e�[�u��Row.get���[�U�[�f�[�^<BR>
     * �@@�@@�E �g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�f�[�^�R�[�h<BR>
     * �@@�@@�@@�@@�@@= �g���K�[���s���e�[�u��Row.get�f�[�^�R�[�h<BR>
     * <BR>
     * �S�j�������ʂ�ԋp����B<BR>
     * @@param l_lisReQueryResults - (�Č�������)<BR>
     * �u�g���K�[���s���e�[�u���Č��������v�̖߂�l��ێ��B<BR>
     * @@return WEB3AdminDirSecTriggerIssueRecordDetail
     * @@roseuid 47E754020156
     */
    private WEB3AdminDirSecTriggerIssueRecordDetail createTriggerIssueRecordDetail(List l_lisReQueryResults)
    {
        final String STR_METHOD_NAME = "createTriggerIssueRecordDetail(List)";
        log.entering(STR_METHOD_NAME);

        //�g���K�[���s���R�[�h���׃I�u�W�F�N�g�𐶐��B
        WEB3AdminDirSecTriggerIssueRecordDetail l_adminDirSecTriggerIssueRecordDetail =
            new WEB3AdminDirSecTriggerIssueRecordDetail();

        SubmitTriggerInfoRow l_submitTriggerInfoRow = (SubmitTriggerInfoRow)l_lisReQueryResults.get(0);
        //�g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�V�F������= �g���K�[���s���e�[�u��Row.get�V�F������
        l_adminDirSecTriggerIssueRecordDetail.shellName = l_submitTriggerInfoRow.getJobId();
        //�g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�g���K�[����= �g���K�[���s���e�[�u��Row.get�g���K�[����
        l_adminDirSecTriggerIssueRecordDetail.triggerName = l_submitTriggerInfoRow.getTriggerId();
        //�g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�Ĕ��s�\�t���O= �g���K�[���s���e�[�u��Row.get�Ĕ��s�\�t���O
        l_adminDirSecTriggerIssueRecordDetail.reissuePossibleFlag =
            l_submitTriggerInfoRow.getEnableSubmitTriggerFlag();
        //�g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.���[�U�[�f�[�^= �g���K�[���s���e�[�u��Row.get���[�U�[�f�[�^
        l_adminDirSecTriggerIssueRecordDetail.userData = l_submitTriggerInfoRow.getUserData();
        //�g���K�[���s���R�[�h�ڍ׃I�u�W�F�N�g.�f�[�^�R�[�h= �g���K�[���s���e�[�u��Row.get�f�[�^�R�[�h
        l_adminDirSecTriggerIssueRecordDetail.dataCode = l_submitTriggerInfoRow.getRequestCode();

        log.exiting(STR_METHOD_NAME);
        return l_adminDirSecTriggerIssueRecordDetail;
    }

    /**
     * (is������ԃe�[�u������)<BR>
     * create���������f�[�^�R���e�i() �̖߂�l�ɂ���āA<BR>
     * ������ԃe�[�u���Ɍ������s����������s���B<BR>
     * <BR>
     * �P�j����.�f�[�^�R���e�i == null �̏ꍇ�A���L�̏���<BR>
     * <BR>
     * �@@�@@List lst = new ArrayList();<BR>
     * <BR>
     * �@@�@@�����s����List��߂�l�Ƃ��ă��^�[������B<BR>
     * �@@�@@�@@����L�̏����͎�������create�x�����b�Z�[�W() ��<BR>
     * �@@�@@�@@�@@�u�������܂̎��Ԃ�MQ�g���K�[�𔭍s�ł��Ȃ��\��������܂��v<BR>
     * �@@�@@�@@�@@���o�͂���ׂɍs���Ă��鏈���ł��B<BR>
     * <BR>
     * �Q�j����.�f�[�^�R���e�i != null �̏ꍇ�A<BR>
     * �@@�@@�u������ԃe�[�u���v�ɑ΂���doFindAllQuery() �����s���A<BR>
     * �@@�@@�߂�l�����^�[������B<BR>
     * <BR>
     * [doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0 �F ������ԃe�[�u��RowType�I�u�W�F�N�g<BR>
     * �@@arg1 �F ����.�擾����������<BR>
     * �@@arg2 �F ����.�f�[�^�R���e�i<BR>
     * @@param l_strQueryString - (�擾����������)<BR>
     * �擾����������<BR>
     * @@param l_dateContainers - (�f�[�^�R���e�i)<BR>
     * �f�[�^�R���e�i<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    private List isTradingTimeTableQuery(
        String l_strQueryString,
        Object[] l_dateContainers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTradingTimeTableQuery(String, Object[])";
        log.entering(STR_METHOD_NAME);

        List l_lisResults = new ArrayList();

        if (l_dateContainers == null)
        {
            //����.�f�[�^�R���e�i == null �̏ꍇ�A���L�̏���
            log.exiting(STR_METHOD_NAME);

            return l_lisResults;
        }
        else
        {
            //����.�f�[�^�R���e�i != null �̏ꍇ
            //�u������ԃe�[�u���v�ɑ΂���doFindAllQuery() �����s���A
            //�@@�߂�l�����^�[������B
            //[doFindAllQuery()�ɃZ�b�g����p�����[�^]
            //�@@arg0 �F ������ԃe�[�u��RowType�I�u�W�F�N�g
            //�@@arg1 �F ����.�擾����������
            //�@@arg2 �F ����.�f�[�^�R���e�i
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisResults = l_queryProcessor.doFindAllQuery(
                    TradingTimeRow.TYPE,
                    l_strQueryString,
                    l_dateContainers);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisResults;
    }

    /**
     * (create�x�����b�Z�[�W)<BR>
     * �s���Ȓl�ɑ΂��Čx�����b�Z�[�W���쐬����B<BR>
     * <BR>
     * �P�jString�^�̕ϐ���z��ŗp�ӂ���B(String[] str = new String[2];)<BR>
     * <BR>
     * �Q�j�g���K�[���s���e�[�u���Č��������̌x�����b�Z�[�W�쐬<BR>
     * �@@�Q�|�P�j�����F�Č�������.size() == 0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�x�����b�Z�[�W�u�g���K�[���s���e�[�u���ɓ��͂����f�[�^�R�[�h�����݂��܂���v<BR>
     * �@@�@@�@@�@@�@@�� String[0]�Ɋi�[����B<BR>
     * �@@�@@�@@�@@�@@�����F�Č�������.size() != 0 �̏ꍇ�� null ���i�[�B<BR>
     * <BR>
     * �R�j������ԃe�[�u���������ʗp�̌x�����b�Z�[�W�쐬<BR>
     * �@@�R�|�P�j�����F������ԃe�[�u�����R�[�h�ꗗ.size() == 0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�x�����b�Z�[�W�u�������܂̎��Ԃ�MQ�g���K�[�𔭍s�ł��Ȃ��\��������܂��v<BR>
     * �@@�@@�@@�@@�@@��String[1]�Ɋi�[����B<BR>
     * �@@�@@�@@�@@�@@�����F������ԃe�[�u�����R�[�h�ꗗ.size() �̒l != 0 �̏ꍇ�� null���i�[�B<BR>
     * <BR>
     * �S�j �P�j�ɂĒ�`�����ϐ������^�[���B<BR>
     * @@param l_lisReQueryResults - (�Č�������)<BR>
     * �g���K�[���s���e�[�u�����Č����������ʂ�ێ��B<BR>
     * @@param l_lisTradingTimeTableRecordLists - (������ԃe�[�u�����R�[�h�ꗗ)<BR>
     * ������ԃe�[�u�����Q�Ƃ������ʂ�ێ��B<BR>
     * @@return String[]
     * @@roseuid 47C66F3403C0
     */
    private String[] createWarningMessage(
        List l_lisReQueryResults,
        List l_lisTradingTimeTableRecordLists)
    {
        final String STR_METHOD_NAME = "createWarningMessage(List, List)";
        log.entering(STR_METHOD_NAME);

        //String�^�̕ϐ���z��ŗp�ӂ���B(String[] str = new String[2];)
        String[] l_strWarningMessages = new String[2];

        //�g���K�[���s���e�[�u���Č��������̌x�����b�Z�[�W�쐬
        //�@@�����F�Č�������.size() == 0 �̏ꍇ�A
        //�@@�@@�@@�@@�@@�x�����b�Z�[�W �u�g���K�[���s���e�[�u���ɓ��͂����f�[�^�R�[�h�����݂��܂���v
        //�@@�@@�@@�@@�@@�� String[0]�Ɋi�[����B
        //�@@�@@�@@�@@�@@�����F�Č�������.size() != 0 �̏ꍇ�� null ���i�[�B
        if (l_lisReQueryResults.size() == 0)
        {
            l_strWarningMessages[0] = "�g���K�[���s���e�[�u���ɓ��͂����f�[�^�R�[�h�����݂��܂���";
        }
        else
        {
            l_strWarningMessages[0] = null;
        }

        //������ԃe�[�u���������ʗp�̌x�����b�Z�[�W�쐬
        //�@@�����F������ԃe�[�u�����R�[�h�ꗗ.size() == 0 �̏ꍇ�A
        //�@@�@@�@@�@@�@@�x�����b�Z�[�W �u�������܂̎��Ԃ�MQ�g���K�[�𔭍s�ł��Ȃ��\��������܂��v
        //�@@�@@�@@�@@�@@��String[1]�Ɋi�[����B
        //�@@�@@�@@�@@�@@�����F������ԃe�[�u�����R�[�h�ꗗ.size() �̒l != 0 �̏ꍇ�� null ���i�[�B
        if (l_lisTradingTimeTableRecordLists.size() == 0)
        {
            l_strWarningMessages[1] = "�������܂̎��Ԃ�MQ�g���K�[�𔭍s�ł��Ȃ��\��������܂�";
        }
        else
        {
            l_strWarningMessages[1] = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strWarningMessages;
    }

    /* 2008/07/14 add start */
    /**
     * �R���e�L�X�g��񂪐ݒ肳��Ă��Ȃ��ꍇ�Ɏg�p�����OracleSID��
     * ���E���h���r���E���W�b�N�Ŏ擾����B
     * 
     * @@return OracleSID
     * @@throws WEB3SystemLayerException
     */
    private String getDefaultOracleSid() throws WEB3SystemLayerException
    {
        String l_strJndiName = getRoundRobinBasedJndiName();
        return getOracleSid(l_strJndiName);
    }

    /**
     * RoundRobinBasedMultiPoolDataSource���g�p����f�[�^�\�[�X��JNDI�����擾����B
     * 
     * @@return JNDI��
     */
    private String getRoundRobinBasedJndiName()
    {
        RoundRobinBasedMultiPoolDataSource l_ds =
            (RoundRobinBasedMultiPoolDataSource) Services.getService(
                RoundRobinBasedMultiPoolDataSource.class);
        return l_ds.getJndiName();
    }
    /**
     * �g�p����DB�ڑ��v�[���Ƀ}�b�s���O����Ă���OracleSID���擾����B
     * 
     * @@param l_strJndiName
     *            DB�ڑ��v�[����JNDI��
     * @@return Oracle SID
     * @@throws WEB3SystemLayerException
     */
    private String getOracleSid(String l_strJndiName) throws WEB3SystemLayerException
    {
        try
        {
            MpdsSettingsRow l_mpdsSetting = MpdsSettingsDao.findRowByPk(
                    "db.cluster.sid", l_strJndiName);
            return l_mpdsSetting.getSettingValue();
        } catch (DataFindException l_dfe)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + ".getSid(String)", 
                    l_dfe.getMessage(), 
                    l_dfe);
        } catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    getClass().getName() + ".getSid(String)", 
                    l_de.getMessage(), 
                    l_de);
        }
    }
    /* 2008/07/14 add end */
}
@
