head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionDelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҕ\���ݒ�폜�T�[�r�XImpl(WEB3AdminPvInfoConditionDelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/28 ������(���u) �쐬
Revesion History : 2004/11/2  鰊](���u) �C��
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PvInfoBlinkDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoConditionDef;
import webbroker3.common.define.WEB3PvInfoEffectiveFlagDef;
import webbroker3.common.define.WEB3PvInfoLastUpdateTimeDisplayFlagDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.data.BrowseHistoryParams;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmResponse;
import webbroker3.pvinfo.message.WEB3PvInfoAccountInformationUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionDelService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҕ\���ݒ�폜�T�[�r�XImpl)<BR>
 * �Ǘ��ҕ\���ݒ�폜�T�[�r�X�����N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionDelServiceImpl extends WEB3ClientRequestService implements WEB3AdminPvInfoConditionDelService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionDelServiceImpl.class);

    /**
     * �Ǘ��ҕ\���ݒ�폜�������s���B<BR>
     * <BR>
     * �����̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�\���ݒ�폜�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.validate�\���ݒ�폜()���\�b�h���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�\���ݒ�폜�������N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�\���ݒ�폜()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415D2A6D01F4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3AdminPvInfoConditionDelConfirmRequest)
        {
            l_response = this.validateConditionDel((WEB3AdminPvInfoConditionDelConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminPvInfoConditionDelCompleteRequest)
        {
            l_response = this.submitConditionDel((WEB3AdminPvInfoConditionDelCompleteRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = "�p�����[�^�̗ތ^���s��";
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80018.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorMessage);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�\���ݒ�폜)<BR>
     * �\���ݒ�폜�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҕ\���ݒ�폜�T�[�r�X)validate�\���ݒ�폜�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�폜�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelConfirmResponse
     * @@roseuid 415D2AA800BC
     */
    protected WEB3AdminPvInfoConditionDelConfirmResponse validateConditionDel(WEB3AdminPvInfoConditionDelConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateConditionDel(WEB3AdminPvInfoConditionDelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPvInfoConditionDelConfirmResponse l_response = null;

        //1.1.validate()
        l_request.validate();
        log.debug("validate() �����s���܂�");
        
        //1.2.getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFrom���O�C�����() �����s���܂�");

        //1.3.validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate����() �����s���܂�");

        //1.4.get�\�����eParams(long)
        WEB3PvInfoDataManager l_dataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        long l_lngDisplayContentsId = Long.parseLong(l_request.displayContentsId);
        DisplayContentsParams l_displayContentsParams = l_dataMgr.getDisplayContentsParams(l_lngDisplayContentsId);
        log.debug("get�\�����eParams() �����s���܂�");

        if(l_displayContentsParams == null)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01037.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.5.�\�����e���()
        WEB3PvInfoDisplayContentsUnit l_displayContentsUnit = new WEB3PvInfoDisplayContentsUnit();
        log.debug("�\�����e���() �����s���܂�");
        
        //1.6. (*)�v���p�e�B�Z�b�g
        l_displayContentsUnit.displayContentsId =
            WEB3StringTypeUtility.formatNumber(l_displayContentsParams.display_contents_id);
        log.debug("�\�����eID = " + l_displayContentsUnit.displayContentsId);
        
        l_displayContentsUnit.conditionNumber = l_displayContentsParams.condition_no;
        log.debug("�\�������ԍ� = " + l_displayContentsUnit.conditionNumber);
        
        l_displayContentsUnit.priorityDiv = l_displayContentsParams.priority_div;
        log.debug("�D��敪 = " + l_displayContentsUnit.priorityDiv);
             
        l_displayContentsUnit.listStartDate = WEB3DateUtility.formatDate(l_displayContentsParams.term_from, "yyyyMMddHHmm");
        log.debug("�\������From = " + l_displayContentsUnit.listStartDate);
        
        l_displayContentsUnit.listEndDate = WEB3DateUtility.formatDate(l_displayContentsParams.term_to, "yyyyMMddHHmm");
        log.debug("�\������To = " + l_displayContentsUnit.listEndDate);
        
        l_displayContentsUnit.displayTitle = l_displayContentsParams.display_title;
        log.debug("�\���^�C�g�� = " + l_displayContentsUnit.displayTitle);
        
        l_displayContentsUnit.displayMessage = l_displayContentsParams.display_message;
        log.debug("�\������ = " + l_displayContentsUnit.displayMessage);
        
        l_displayContentsUnit.displayColor = l_displayContentsParams.display_color;
        log.debug("�\���F = " + l_displayContentsUnit.displayColor);

        if(WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES.equals(l_displayContentsParams.blink_display_flag))
        {
            l_displayContentsUnit.blinkDisplayFlag = true;
            log.debug("�_�ŕ\���t���O = " + l_displayContentsUnit.blinkDisplayFlag);
        }
        else
        {
            l_displayContentsUnit.blinkDisplayFlag = false;
            log.debug("�_�ŕ\���t���O = " + l_displayContentsUnit.blinkDisplayFlag);
        }
        l_displayContentsUnit.displayUrl = l_displayContentsParams.display_url;
        log.debug("URL�w�� = " + l_displayContentsUnit.displayUrl);

        if(WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES.equals(l_displayContentsParams.last_update_time_display_flag))
        {
            l_displayContentsUnit.lastUpdateTimeDisplayFlag = false;
            log.debug("�ŏI�X�V�����\���t���O = " + l_displayContentsUnit.lastUpdateTimeDisplayFlag);
        }
        else
        {
            l_displayContentsUnit.lastUpdateTimeDisplayFlag = true;
            log.debug("�ŏI�X�V�����\���t���O = " + l_displayContentsUnit.lastUpdateTimeDisplayFlag);
        }

        if(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES.equals(l_displayContentsParams.effective_flag))
        {
            l_displayContentsUnit.effectiveFlag = false;
            log.debug("�L��/�����t���O = " + l_displayContentsUnit.effectiveFlag);
        }
        else
        {
            l_displayContentsUnit.effectiveFlag = true;
            log.debug("�L��/�����t���O = " + l_displayContentsUnit.effectiveFlag);
        }
        l_displayContentsUnit.displayDevice = l_displayContentsParams.display_device;
        log.debug("�\���}�� = " + l_displayContentsUnit.displayDevice);
        
        l_displayContentsUnit.lastUpdateMember = l_displayContentsParams.last_update_member;
        log.debug("�ŏI�X�V�� = " + l_displayContentsUnit.lastUpdateMember);
        
        l_displayContentsUnit.lastUpdatedTimestamp = l_displayContentsParams.last_updated_timestamp;
        log.debug("�ŏI�X�V���� = " + l_displayContentsUnit.lastUpdatedTimestamp);

        //1.7.(*)����t���[
        WEB3PvInfoAccountInformationUnit[] l_accountInformationUnits = null;

        if(WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_displayContentsParams.condition_no))
        {
            //1.7.1.create�Ώیڋq���ꗗ(�\�����eParams)
            l_accountInformationUnits = this.createTargetAccountInfoList(l_displayContentsParams);
            log.debug("create�Ώیڋq���ꗗ() �����s���܂�");            
        }

        //1.8.createResponse()
        l_response = (WEB3AdminPvInfoConditionDelConfirmResponse)l_request.createResponse();
        log.debug("createResponse() �����s���܂�");

        //1.9. (*)�v���p�e�B�Z�b�g
        l_response.displayContentsUnit = l_displayContentsUnit;
        if(l_accountInformationUnits == null || !WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_displayContentsParams.condition_no))
        {
            l_response.accountInformationUnits = null;
        }
        else
        {
            l_response.accountInformationUnits = l_accountInformationUnits;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�\���ݒ�폜)<BR>
     * �\���ݒ�폜�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҕ\���ݒ�폜�T�[�r�X)submit�\���ݒ�폜�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�폜�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDelCompleteResponse
     * @@roseuid 415D2AA800DB
     */
    protected WEB3AdminPvInfoConditionDelCompleteResponse submitConditionDel(WEB3AdminPvInfoConditionDelCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitConditionDel(WEB3AdminPvInfoConditionDelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPvInfoConditionDelCompleteResponse l_response = null;

        //1.1.validate()
        l_request.validate();
        log.debug("validate() �����s���܂�");
        
        //1.2.getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFrom���O�C�����() �����s���܂�");

        //1.3validate����(�@@�\�R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate����() �����s���܂�");

        //1.4.validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        log.debug("validate����p�X���[�h() �����s���܂�");
        
        //1.5.delete�{������(long)
        WEB3PvInfoDataManager l_dataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        log.debug("delete�{������() �����s���܂�");        
        long l_lngDisplayContentsID = Long.parseLong(l_request.displayContentsId);
        l_dataMgr.deleteBrowseHistory(l_lngDisplayContentsID);

        //1.6.delete�\�����e(long)
        l_dataMgr.deleteDisplayContents(l_lngDisplayContentsID);
        log.debug("delete�\�����e() �����s���܂�");        

        //1.7.createResponse()
        l_response = (WEB3AdminPvInfoConditionDelCompleteResponse)l_request.createResponse();
        log.debug("createResponse() �����s���܂�");

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�Ώیڋq���ꗗ)<BR>
     * �Ώیڋq���̈ꗗ���쐬���A�ԋp����B<BR>
     * <BR>
     * �P�j�{�������e�[�u����������������̍쐬<BR>
     * �@@(��������������&���������f�[�^�R���e�i)<BR>
     * <BR>
     * �@@�ȉ��̌���������\���A���������������<BR>
     * �@@ArrayList(���������f�[�^�R���e�i)���쐬����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�\�����eParams.�،���ЃR�[�h�@@����<BR>
     * �@@�@@�\�����eID = �p�����[�^.�\�����eParams.�\�����eID<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = " institution_code = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "display_contents_id = ? "<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B<BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B<BR>
     * �@@�@@�@@�E�p�����[�^.�\�����eParams.�،���ЃR�[�h<BR>
     * �@@�@@�@@�E�p�����[�^.�\�����eParams.�\�����eID<BR>
     * <BR>
     * �Q�j�\�[�g�������쐬����B<BR>
     * �@@�@@�����X �����A�ڋq ����<BR>
     * �@@�@@�\�[�g���� = " branch_code asc, account_code asc"<BR>
     * <BR>
     * �R�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.get�{������Params�ꗗ()<BR>
     * �@@���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[get�{������Params�ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@��������������F�@@�쐬������������������<BR>
     * �@@�@@���������f�[�^�R���e�i�F�@@�쐬����ArrayList.toArray()<BR>
     * �@@�@@�\�[�g�����F�@@�쐬�����\�[�g����<BR>
     * <BR>
     * �@@���\�b�h�̖߂�l == null�̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �S�jArrayList���쐬����B<BR>
     * <BR>
     * �T�j�R�j�̃��\�b�h�̖߂�l�̗v�f(=�{������Params)�����A<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�T�|�P�j�Ώیڋq���I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�T�|�Q�j�Ώیڋq���Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�Ώیڋq���.���X�R�[�h = �{������Params.���X�R�[�h<BR>
     * �@@�@@�@@�@@�Ώیڋq���.�ڋq�R�[�h = �{������Params.�ڋq�R�[�h<BR>
     * �@@�@@�@@�@@�Ώیڋq���.�ŏI�{������ = �{������Params.�ŏI�{������ <BR>
     * �@@�T�|�R�j��������ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �U�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_displayContentsParams - (�\�����eParams)<BR>
     * �\�����eParams<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoAccountInformationUnit[]
     * @@roseuid 4160FFA00338
     */
    protected WEB3PvInfoAccountInformationUnit[] createTargetAccountInfoList(DisplayContentsParams l_displayContentsParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createTargetAccountInfoList(DisplayContentsParams)";
        log.entering(STR_METHOD_NAME);

        //�P�j�{�������e�[�u����������������̍쐬
        //�P�|�P�j��L������������ɁA����������������쐬����B
        String l_strQuery = " institution_code = ? and display_contents_id = ? " ;

        //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
        List l_lisQueryVars = new ArrayList();
        l_lisQueryVars.add(l_displayContentsParams.institution_code);
        log.debug("�،���ЃR�[�h = " + l_displayContentsParams.institution_code);
        
        l_lisQueryVars.add(WEB3StringTypeUtility.formatNumber(l_displayContentsParams.display_contents_id));
        log.debug("�\�����eID = " + l_displayContentsParams.display_contents_id);

        //�Q�j�\�[�g�������쐬����B
        String l_strSort = " branch_code asc, account_code asc";

        //�R�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.get�{������Params�ꗗ()
        WEB3PvInfoDataManager l_dataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        String[] l_strArryQueryVars = new String[l_lisQueryVars.size()];
        l_lisQueryVars.toArray(l_strArryQueryVars);
        List l_lisBrowseHistoryParams = l_dataMgr.getBrowseHistoryParamsList(l_strQuery, l_strArryQueryVars, l_strSort);
        log.debug("get�{������Params�ꗗ() �����s���܂�");
                
        if(l_lisBrowseHistoryParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�S�jArrayList���쐬����B
        List l_lisAccountInformationUnit = new ArrayList();

        //�T�j�R�j�̃��\�b�h�̖߂�l�̗v�f(=�{������Params)�����A
        int l_intLisBrowseHistoryParamsCnt = l_lisBrowseHistoryParams.size();
        for(int i = 0; i < l_intLisBrowseHistoryParamsCnt; i++)
        {
            //�T�|�P�j�Ώیڋq���I�u�W�F�N�g�𐶐�����B
            WEB3PvInfoAccountInformationUnit l_accountInformationUnit = new WEB3PvInfoAccountInformationUnit();

            //�T�|�Q�j�Ώیڋq���Ɉȉ��̃v���p�e�B���Z�b�g����B
            BrowseHistoryParams l_bhParams = (BrowseHistoryParams)l_lisBrowseHistoryParams.get(i);
            
            l_accountInformationUnit.branchCode  = l_bhParams.getBranchCode();
            log.debug("���X�R�[�h = " + l_accountInformationUnit.branchCode);
            
            l_accountInformationUnit.accountCode = l_bhParams.getAccountCode();
            log.debug("�ڋq�R�[�h = " + l_accountInformationUnit.accountCode);
            
            l_accountInformationUnit.lastBrowseDate = l_bhParams.getLastReadTimestamp();
            log.debug("�ŏI�{������ = " + l_accountInformationUnit.lastBrowseDate);

            //�T�|�R�j��������ArrayList�ɒǉ�����B
            l_lisAccountInformationUnit.add(l_accountInformationUnit);
        }

        //�U��������ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3PvInfoAccountInformationUnit[] l_arryAccountInformationUnit = new WEB3PvInfoAccountInformationUnit[l_lisAccountInformationUnit.size()];
        l_lisAccountInformationUnit.toArray(l_arryAccountInformationUnit);

        log.exiting(STR_METHOD_NAME);
        return l_arryAccountInformationUnit;
    }
}
@
