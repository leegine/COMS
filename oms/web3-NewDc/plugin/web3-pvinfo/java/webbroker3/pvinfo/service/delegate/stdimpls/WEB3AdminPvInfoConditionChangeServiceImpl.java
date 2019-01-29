head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҕ\���ݒ�ύX�T�[�r�XImpl(WEB3AdminPvInfoConditionChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/28 ������(���u) �쐬
Revesion History : 2004/11/02 ����(���u) �C��
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PvInfoBlinkDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoEffectiveFlagDef;
import webbroker3.common.define.WEB3PvInfoLastUpdateTimeDisplayFlagDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputResponse;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayConditionUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionChangeService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҕ\���ݒ�ύX�T�[�r�XImpl)<BR>
 * �Ǘ��ҕ\���ݒ�ύX�T�[�r�X�����N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionChangeServiceImpl extends WEB3ClientRequestService implements WEB3AdminPvInfoConditionChangeService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionChangeServiceImpl.class);

    /**
     * �\���ݒ�ύX�������s���B<BR>
     * <BR>
     * �����̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����<BR>
     * <BR>
     * ���Ǘ��ҁE�\���ݒ�ύX���̓��N�G�X�g�̏ꍇ<BR>
     * �@@this.get�\���ݒ�ύX���͉��()���\�b�h���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�\���ݒ�ύX�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.validate�\���ݒ�ύX()���\�b�h���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�\���ݒ�ύX�������N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�\���ݒ�ύX()���\�b�h���R�[������B<BR>
     *
     * @@param l_request
     *  - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@throws WEB3BaseException
     * @@roseuid 415D1378036B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if(l_request instanceof WEB3AdminPvInfoConditionChangeInputRequest)
        {
            l_response = this.getConditionChangeInputScreen((WEB3AdminPvInfoConditionChangeInputRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminPvInfoConditionChangeConfirmRequest)
        {
            l_response = this.validateConditionChange((WEB3AdminPvInfoConditionChangeConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminPvInfoConditionChangeCompleteRequest)
        {
            l_response = this.submitConditionChange((WEB3AdminPvInfoConditionChangeCompleteRequest)l_request);
        }
        else
        {
            String l_strErrorInfo =
                "�p�����[�^�̗ތ^���s���AWEB3AdminPvInfoConditionChangeInputRequest," +
                "WEB3AdminPvInfoConditionChangeConfirmRequest," +
                "WEB3AdminPvInfoConditionChangeCompleteRequest�ތ^�B";
            log.error(l_strErrorInfo);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorInfo);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�\���ݒ�ύX���͉��)<BR>
     * �\���ݒ�ύX���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҕ\���ݒ�ύX�T�[�r�X)get�\���ݒ�ύX���͉�ʁv�Q��<BR>
     * ========================================================<BR>
     * get�\�����eParams(�\�����eID�F�@@long)<BR>
     * <BR>
     *null���ԋp���ꂽ�ꍇ�́A<BR>
     *�u�\�������ݒ�Ȃ��v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01036<BR>
     * null���ԋp���ꂽ�ꍇ�́A<BR>
     * �u�Y���f�[�^�����݁v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01037<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�ύX���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputResponse
     * @@roseuid 415D13C001B6
     */
    protected WEB3AdminPvInfoConditionChangeInputResponse getConditionChangeInputScreen(WEB3AdminPvInfoConditionChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConditionChangeInputScreen(WEB3AdminPvInfoConditionChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPvInfoConditionChangeInputResponse l_response = null;

        //1.1.validate()
        l_request.validate();
        log.debug("validate�����s���܂�");
        
        //1.2.getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFrom���O�C���������s���܂�");

        //1.3.validate����(�@@�\�R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate���������s���܂�");



        //1.4�\���������̈ꗗ���쐬����B 
        WEB3PvInfoDataManager l_dataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        
        WEB3PvInfoDisplayConditionUnit[] l_conditonUnits = l_dataMgr.createDisplayConditionList(l_admin);
        if (l_conditonUnits == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01036.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01036,
                getClass().getName() + "." + STR_METHOD_NAME);
            
        }
        //1.5.get�\�����eParams(long)
        long l_lngDisplayContentsID = Long.parseLong(l_request.displayContentsId);
        log.debug("�\�����eID =" + l_lngDisplayContentsID);
        DisplayContentsParams l_displayContentsParams = l_dataMgr.getDisplayContentsParams(l_lngDisplayContentsID);
        log.debug("get�\�����eParams�����s���܂�");
        if(l_displayContentsParams == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01037.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6.�\�����e���()
        WEB3PvInfoDisplayContentsUnit l_displayContentsUnit = new WEB3PvInfoDisplayContentsUnit();

        //1.7.(*)�v���p�e�B�Z�b�g
        l_displayContentsUnit.displayContentsId =
            WEB3StringTypeUtility.formatNumber(l_displayContentsParams.display_contents_id);
        log.debug("�\�����eID =" + l_displayContentsUnit.displayContentsId);
        
        l_displayContentsUnit.conditionNumber = l_displayContentsParams.condition_no;
        log.debug("�\�������ԍ� =" + l_displayContentsUnit.conditionNumber);
        
        l_displayContentsUnit.priorityDiv = l_displayContentsParams.priority_div;
        log.debug("�D��敪 =" + l_displayContentsUnit.priorityDiv);
        
        l_displayContentsUnit.listStartDate =WEB3DateUtility.formatDate(l_displayContentsParams.term_from, "yyyyMMddHHmm");
        log.debug("�\������From =" + l_displayContentsUnit.listStartDate);
        
        l_displayContentsUnit.listEndDate =WEB3DateUtility.formatDate(l_displayContentsParams.term_to, "yyyyMMddHHmm");
        log.debug("�\������To =" + l_displayContentsUnit.listEndDate);
        
        l_displayContentsUnit.displayTitle = l_displayContentsParams.display_title;
        log.debug("�\���^�C�g�� =" + l_displayContentsUnit.displayTitle);
        
        l_displayContentsUnit.displayMessage = l_displayContentsParams.display_message;
        log.debug("�\������ =" + l_displayContentsUnit.displayMessage);
        
        l_displayContentsUnit.displayColor = l_displayContentsParams.display_color;
        log.debug("�\���F =" + l_displayContentsUnit.displayColor);
        
        log.debug("�_�ŕ\���t���O =" + l_displayContentsParams.blink_display_flag);
        if(WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES.equals(l_displayContentsParams.blink_display_flag))
        {
            l_displayContentsUnit.blinkDisplayFlag = true;
        }
        else
        {
            l_displayContentsUnit.blinkDisplayFlag = false;
        }

        l_displayContentsUnit.displayUrl = l_displayContentsParams.display_url;
        log.debug("URL�w�� =" + l_displayContentsUnit.displayUrl);
        
        log.debug("�ŏI�X�V�����\���t���O =" + l_displayContentsParams.last_update_time_display_flag);
        if(WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES.equals(l_displayContentsParams.last_update_time_display_flag))
        {
            l_displayContentsUnit.lastUpdateTimeDisplayFlag = false;
        }
        else
        {
            l_displayContentsUnit.lastUpdateTimeDisplayFlag = true;
        }

        log.debug("�L��/�����t���O =" + l_displayContentsParams.effective_flag);
        if(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES.equals(l_displayContentsParams.effective_flag))
        {
            l_displayContentsUnit.effectiveFlag = false;
        }
        else
        {
            l_displayContentsUnit.effectiveFlag = true;
        }
        
        l_displayContentsUnit.displayDevice = l_displayContentsParams.display_device;
        log.debug("�\���}�� =" + l_displayContentsUnit.displayDevice);
        
        l_displayContentsUnit.lastUpdateMember = l_displayContentsParams.last_update_member;
        log.debug("�ŏI�X�V�� =" + l_displayContentsUnit.lastUpdateMember);
        
        l_displayContentsUnit.lastUpdatedTimestamp = l_displayContentsParams.last_updated_timestamp;
        log.debug("�ŏI�X�V���� =" + l_displayContentsUnit.lastUpdatedTimestamp);

        //1.8.createResponse
        l_response = (WEB3AdminPvInfoConditionChangeInputResponse)l_request.createResponse();

        //1.9.(*)�v���p�e�B�Z�b�g
        l_response.displayContentsUnit = l_displayContentsUnit;
        l_response.displayConditionUnits = l_conditonUnits;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�\���ݒ�ύX)<BR>
     * �\���ݒ�ύX�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҕ\���ݒ�ύX�T�[�r�X)validate�\���ݒ�ύX�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�ύX�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmResponse
     * @@roseuid 415D13C001D5
     */
    protected WEB3AdminPvInfoConditionChangeConfirmResponse validateConditionChange(WEB3AdminPvInfoConditionChangeConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateConditionChange(WEB3AdminPvInfoConditionChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPvInfoConditionChangeConfirmResponse l_response = null;

        //1.1validate()
        l_request.validate();
        log.debug("validate�����s���܂�");

        //1.2.getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFrom���O�C���������s���܂�");

        //1.3.validate����(�@@�\�R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate���������s���܂�");

        //1.4.createResponse()
        l_response = (WEB3AdminPvInfoConditionChangeConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�\���ݒ�ύX)<BR>
     * �\���ݒ�ύX�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҕ\���ݒ�ύX�T�[�r�X)submit�\���ݒ�ύX�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�ύX�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteResponse
     * @@roseuid 415D13C001E5
     */
    protected WEB3AdminPvInfoConditionChangeCompleteResponse submitConditionChange(WEB3AdminPvInfoConditionChangeCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitConditionChange(WEB3AdminPvInfoConditionChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPvInfoConditionChangeCompleteResponse l_response = null;

        //1.1.validate()
        l_request.validate();
        log.debug("validate�����s���܂�");
        
        //1.2.getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFrom���O�C���������s���܂�");

        //1.3.validate����(�@@�\�R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate���������s���܂�");

        //1.4.validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        log.debug("validate����p�X���[�h�����s���܂�");

        //1.5.create�\�����eParams(�\�����e���)
        WEB3PvInfoDataManager l_dataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        DisplayContentsParams l_displayContentsParams = l_dataMgr.createDisplayContentsParams(l_request.displayContentsUnit);
        log.debug("create�\�����eParams�����s���܂�");
        if(l_displayContentsParams == null)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80006.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.6.update�\�����e(�\�����eParams)
        l_dataMgr.updateDisplayContents(l_displayContentsParams);
        log.debug("update�\�����e �����s���܂�");

        //1.7.createResponse();
        l_response = (WEB3AdminPvInfoConditionChangeCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
