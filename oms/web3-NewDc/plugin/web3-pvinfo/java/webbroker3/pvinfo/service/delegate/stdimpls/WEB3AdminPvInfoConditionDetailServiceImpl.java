head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionDetailServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҕ\���ݒ�ڍ׃T�[�r�XImpl(WEB3AdminPvInfoConditionDetailServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/27 ���O�B(���u) �쐬
Revesion History : 2006/5/22 ������(���u) �d�l�ύXNo.063�C��
Revesion History : 2008/12/02 SCS�X �d�l�ύXNo.113�C��
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
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailResponse;
import webbroker3.pvinfo.message.WEB3PvInfoAccountInformationUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionDetailService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҕ\���ݒ�ڍ׃T�[�r�XImpl)<BR>
 * �Ǘ��ҕ\���ݒ�ڍ׃T�[�r�X�����N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionDetailServiceImpl extends WEB3ClientRequestService implements WEB3AdminPvInfoConditionDetailService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionDetailServiceImpl.class);

    /**
     * �\���ݒ�ڍ׉�ʕ\���������s���B<BR>
     * <BR>
     * this.get�\���ݒ�ڍ׉��()���\�b�h��<BR>
     * �R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415CB9920364
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminPvInfoConditionDetailRequest)
        {
            l_response = this.getConditionDetailScreen((WEB3AdminPvInfoConditionDetailRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = "�p�����[�^�̗ތ^���s���A�Y������WEB3AdminPvInfoConditionDetailRequest�ތ^�B";
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
     * (get�\���ݒ�ڍ׉��)<BR>
     * �\���ݒ�ڍ׉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҕ\���ݒ�ڍ׃T�[�r�X)get�\���ݒ�ڍ׉�ʁv�Q��<BR>
     * ========================================================<BR>
     * get�\�����eParams(�\�����eID�F�@@long)<BR>
     * <BR>
     * null���ԋp���ꂽ�ꍇ�́A<BR>
     * �u�Y���f�[�^�����݁v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01037<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�ڍ׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailResponse
     * @@roseuid 415CB9B30018
     */
    protected WEB3AdminPvInfoConditionDetailResponse getConditionDetailScreen(WEB3AdminPvInfoConditionDetailRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getConditionDetailScreen(WEB3AdminPvInfoConditionDetailRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3Administrator l_administrator = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1 validate()
        l_request.validate();

        //2 getInstanceFrom���O�C�����()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //3 validate����(�@@�\�R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, false);

        //4 get�\�����eParams(long)
        long l_lngDisplayContentsId = Long.parseLong(l_request.displayContentsId);
        DisplayContentsParams l_dispContentsParams = l_dataManager.getDisplayContentsParams(l_lngDisplayContentsId);

        if (l_dispContentsParams == null)
        {
            log.error("�f�[�^�����݂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                getClass().getName() + STR_METHOD_NAME);
        }

        //5 �\�����e���I�u�W�F�N�g�𐶐�����B
        WEB3PvInfoDisplayContentsUnit l_dispContentsUnits = new WEB3PvInfoDisplayContentsUnit();

        //6 (*)�v���p�e�B�Z�b�g
        l_dispContentsUnits.displayContentsId = WEB3StringTypeUtility.formatNumber(l_dispContentsParams.display_contents_id);
        log.debug("�\�����e���.displayContentsId" + "=" + l_dispContentsUnits.displayContentsId);
        l_dispContentsUnits.conditionNumber = l_dispContentsParams.condition_no;
        log.debug("�\�����e���.conditionNumber" + "=" + l_dispContentsUnits.conditionNumber);
        l_dispContentsUnits.priorityDiv = l_dispContentsParams.priority_div;
        log.debug("�\�����e���.priorityDiv" + "=" + l_dispContentsUnits.priorityDiv);
        l_dispContentsUnits.listStartDate = WEB3DateUtility.formatDate(l_dispContentsParams.term_from, "yyyyMMddHHmm");
        log.debug("�\�����e���.listStartDate" + "=" + l_dispContentsUnits.listStartDate);
        l_dispContentsUnits.listEndDate = WEB3DateUtility.formatDate(l_dispContentsParams.term_to, "yyyyMMddHHmm");
        log.debug("�\�����e���.listEndDate" + "=" + l_dispContentsUnits.listEndDate);
        l_dispContentsUnits.displayTitle = l_dispContentsParams.display_title;
        log.debug("�\�����e���.displayTitle" + "=" + l_dispContentsUnits.displayTitle);
        l_dispContentsUnits.displayMessage = l_dispContentsParams.display_message;
        log.debug("�\�����e���.displayMessage" + "=" + l_dispContentsUnits.displayMessage);
        l_dispContentsUnits.displayColor = l_dispContentsParams.display_color;
        log.debug("�\�����e���.displayColor" + "=" + l_dispContentsUnits.displayColor);

        if (WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES.equals(l_dispContentsParams.blink_display_flag))
        {
            l_dispContentsUnits.blinkDisplayFlag = true;
        }
        else
        {
            l_dispContentsUnits.blinkDisplayFlag = false;
        }
        log.debug("�\�����e���.blinkDisplayFlag" + "=" + l_dispContentsUnits.blinkDisplayFlag);

        l_dispContentsUnits.displayUrl = l_dispContentsParams.display_url;
        log.debug("�\�����e���.displayUrl" + "=" + l_dispContentsUnits.displayUrl);

        if (WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES.equals(l_dispContentsParams.last_update_time_display_flag))
        {
            l_dispContentsUnits.lastUpdateTimeDisplayFlag = false;
        }
        else
        {
            l_dispContentsUnits.lastUpdateTimeDisplayFlag = true;
        }
        log.debug("�\�����e���.lastUpdateTimeDisplayFlag" + "=" + l_dispContentsUnits.lastUpdateTimeDisplayFlag);

        if (WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES.equals(l_dispContentsParams.effective_flag))
        {
            l_dispContentsUnits.effectiveFlag = false;
        }
        else
        {
            l_dispContentsUnits.effectiveFlag = true;
        }
        log.debug("�\�����e���.effectiveFlag" + "=" + l_dispContentsUnits.effectiveFlag);

        l_dispContentsUnits.displayDevice = l_dispContentsParams.display_device;
        log.debug("�\�����e���.displayDevice" + "=" + l_dispContentsUnits.displayDevice);
        l_dispContentsUnits.lastUpdateMember = l_dispContentsParams.last_update_member;
        log.debug("�\�����e���.lastUpdateMember" + "=" + l_dispContentsUnits.lastUpdateMember);
        l_dispContentsUnits.lastUpdatedTimestamp = l_dispContentsParams.last_updated_timestamp;
        log.debug("�\�����e���.lastUpdatedTimestamp" + "=" + l_dispContentsUnits.lastUpdatedTimestamp);

        //7 ����t���[
		//  get�\�����eParams()�̖߂�l.�\�������ԍ���
		//     �ȉ��̂��Âꂩ�ɊY������ꍇ�A���������{����B
		//     �@@�E"1001�F������������&�M�p�����J��"
		//     �@@�E"1002�F������������&�M�p�������J��"
		//     �@@�E"1003�F���֋�����"
		//     �@@�E"1005�F�؋����s��"
		//     �@@�E"1007�F�@@���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL"
		//     �@@�E"1041�F�@@20������1����30������5���ȉ�"
		//     �@@�E"1042�F�@@20������1����30������6��"
		//     �@@�E"1043�F�@@20������2����30������6���ȉ�"
		//     �@@�E"1044�F�@@20������3���ȏ�"
		//     �@@�E"1045�F�@@30������2�`4��"
		//     �@@�E"1046�F�@@30������5��"
		//     �@@�E"1047�F�@@30������6��"
		//     �@@�E"1048�F�@@30������7���ȏ�"
		//     �@@�E"1054�F�@@�s�����������M�p�������J��"
		//     �@@�E"1055�F�@@�s�����������M�p�����J��"
		//     �@@�E"1056�F�@@��ꐅ���Ǐؔ���"
		//     �@@�E"1057�F�@@��񐅏��Ǐؔ���"
        String l_strConditionNo = l_dispContentsParams.condition_no;
        WEB3PvInfoAccountInformationUnit[] l_nonReadAccountInformationUnits = null;
        WEB3PvInfoAccountInformationUnit[] l_readAccountInformationUnits = null;
        if (WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_1DAY_AND_6DAY.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_3DAY_OVER.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_2TO4DAY.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_5DAY.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_6DAY.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.BREAK_7DAY_OVER.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_CLOSE.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_OPEN.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.FIRST_ADDITIONAL_DEPOSIT_OCCUR.equals(l_strConditionNo) ||
                WEB3PvInfoConditionDef.SECOND_ADDITIONAL_DEPOSIT_OCCUR.equals(l_strConditionNo))
        {
            //7.1 create�Ώیڋq���ꗗ(�\�����eParams, boolean)
            l_readAccountInformationUnits = this.createTargetAccountInfoList(l_dispContentsParams, true);
        }

        //8 ����t���[
        if (WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_strConditionNo))
        {
            //8.1 create�Ώیڋq���ꗗ(�\�����eParams, boolean)
            l_nonReadAccountInformationUnits = this.createTargetAccountInfoList(l_dispContentsParams, false);

            //8.2 create�Ώیڋq���ꗗ(�\�����eParams, boolean)
            l_readAccountInformationUnits = this.createTargetAccountInfoList(l_dispContentsParams, true);
        }

        //9 createResponse()
        WEB3AdminPvInfoConditionDetailResponse l_response = (WEB3AdminPvInfoConditionDetailResponse)l_request.createResponse();

        //10 �v���p�e�B�Z�b�g
        l_response.displayContentsUnit = l_dispContentsUnits;
        if (l_readAccountInformationUnits != null)
        {
            l_response.readAccountUnits = l_readAccountInformationUnits;
        }
        else
        {
            l_response.readAccountUnits = null;
        }

        if(l_nonReadAccountInformationUnits != null)
        {
            l_response.nonReadAccountUnits = l_nonReadAccountInformationUnits;
        }
        else
        {
            l_response.nonReadAccountUnits = null;
        }

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
     * �@@�@@�\�����eID = �p�����[�^.�\�����eParams.�\�����eID�@@����<BR>
     * �@@�@@���Ǌ��ǃt���O = (�p�����[�^.is���ǌڋq�̒l�ɂ��)<BR>
     * <BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = " institution_code = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "display_contents_id = ? and "<BR>
     * �P�|�P�|�P�j�쐬������������������ɖ��ǁ^���ǂ̏�����ǉ�����B <BR>
     *     [�p�����[�^.is���ǌڋq == false�̏ꍇ] <BR>
     *        �������������� += "last_read_timestamp is null" <BR>
     *      [��L�ȊO�̏ꍇ]<BR>
     *          �������������� += "last_read_timestamp is not null"  <BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B<BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���ォ�珇�ɃZ�b�g����B<BR>
     * �@@�@@��������ϊ����ăZ�b�g���邱�ƁB<BR>
     * �@@�@@�@@�E�p�����[�^.�\�����eParams.�،���ЃR�[�h<BR>
     * �@@�@@�@@�E�p�����[�^.�\�����eParams.�\�����eID<BR>
     * <BR>
     * �Q�j�\�[�g�������쐬����B<BR>
     * �@@[�p�����[�^.is���ǌڋq == false�̏ꍇ]<BR>
     * �@@�@@�����X �����A�ڋq ����<BR>
     * �@@�@@�\�[�g���� = " branch_code asc, account_code asc"<BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�����X �����A�ŏI�{������ ����<BR>
     * �@@�@@�\�[�g���� = " branch_code asc, last_updated_timestamp asc"<BR>
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
     * �@@�@@�@@�@@�Ώیڋq���.�ŏI�{������ = �{������Params.�X�V���t<BR>
     * �@@�T�|�R�j��������ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �U�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_displayContentsParams - (�\�����eParams)<BR>
     * �\�����eParams<BR>
     * @@param l_isReadAccount - (is���ǌڋq)<BR>
     * �擾�Ώۂ����ǌڋq���ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@���ǌڋq<BR>
     * true�F�@@���ǌڋq<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoAccountInformationUnit[]
     * @@roseuid 415CBE520354
     */
    protected WEB3PvInfoAccountInformationUnit[] createTargetAccountInfoList(DisplayContentsParams l_displayContentsParams, boolean l_isReadAccount) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " createTargetAccountInfoList(DisplayContentsParams, boolean)";
        log.entering(STR_METHOD_NAME);

        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        //�P�j�{�������e�[�u����������������̍쐬
        //�P�|�P�j��L������������ɁA����������������쐬����B
        String l_strQueryString = " institution_code = ? and "
                                    + "display_contents_id = ? and ";

        //�P�|�P�|�P�j�쐬������������������ɖ��ǁ^���ǂ̏�����ǉ�����B
        //[�p�����[�^.is���ǌڋq == false�̏ꍇ]  
        if (l_isReadAccount == false)
        {
            l_strQueryString += "last_read_timestamp is null";
        }
        else
        {
            l_strQueryString += "last_read_timestamp is not null";
        }
        //�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
        List l_lisArrayList = new ArrayList();
        l_lisArrayList.add(l_displayContentsParams.institution_code);
        l_lisArrayList.add(WEB3StringTypeUtility.formatNumber(l_displayContentsParams.display_contents_id));
        String[] l_strArrayLists = new String[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_strArrayLists);

        //�Q�j�\�[�g�������쐬����B
        String l_strSortString = null;
        if (!l_isReadAccount)
        {
            l_strSortString = " branch_code asc, account_code asc";
        }
        else
        {
            l_strSortString = " branch_code asc, last_updated_timestamp asc";
        }
        log.debug("l_strSortString" + "=" + l_strSortString);

        //�R�j��ײ�ްĲ�̫Ұ����ް��Ȱ�ެ.get�{������Params�ꗗ()���\�b�h���R�[������B
        List l_lisBrowseHistoryParams = l_dataManager.getBrowseHistoryParamsList(l_strQueryString, l_strArrayLists, l_strSortString);
        if (l_lisBrowseHistoryParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�S�jArrayList���쐬����B
        List l_lisAccountInformation = new ArrayList();

        //�T�j�R�j�̃��\�b�h�̖߂�l�̗v�f(=�{������Params)�����A�ȉ��̏������J��Ԃ��B
        int l_intParamSize = l_lisBrowseHistoryParams.size();
        for (int i = 0; i < l_intParamSize; i++)
        {
            BrowseHistoryParams l_browseHistoryParams = (BrowseHistoryParams)l_lisBrowseHistoryParams.get(i);
            if (l_browseHistoryParams != null)
            {
                //�T�|�P�j�Ώیڋq���I�u�W�F�N�g�𐶐�����B
                WEB3PvInfoAccountInformationUnit l_accountInformationUnit = new WEB3PvInfoAccountInformationUnit();

                //�T�|�Q�j�Ώیڋq���Ɉȉ��̃v���p�e�B���Z�b�g����B
                l_accountInformationUnit.accountCode = l_browseHistoryParams.account_code;
                log.debug("�Ώیڋq���.accountCode" + "=" + l_accountInformationUnit.accountCode);
                l_accountInformationUnit.branchCode = l_browseHistoryParams.branch_code;
                log.debug("�Ώیڋq���.branchCode" + "=" + l_accountInformationUnit.branchCode);
                l_accountInformationUnit.lastBrowseDate = l_browseHistoryParams.last_read_timestamp;
                log.debug("�Ώیڋq���.lastBrowseDate" + "=" + l_accountInformationUnit.lastBrowseDate);

                //�T�|�R�j��������ArrayList�ɒǉ�����B
                l_lisAccountInformation.add(l_accountInformationUnit);
            }
        }

        //�U�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3PvInfoAccountInformationUnit[] l_accountInformationUnit = new WEB3PvInfoAccountInformationUnit[l_lisAccountInformation.size()];
        l_lisAccountInformation.toArray(l_accountInformationUnit);

        log.exiting(STR_METHOD_NAME);
        return l_accountInformationUnit;
    }
}
@
