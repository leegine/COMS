head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҕ\���ݒ�ꗗ�T�[�r�XImpl(WEB3AdminPvInfoConditionListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/27 ������(���u) �쐬
Revesion History : 2004/11/2  鰊](���u) �C��
Revesion History : 2005/12/8 杊��](���u) �d�l�ύXNo.059�C��
Revesion History : 2006/5/22 ������(���u) �d�l�ύXNo.063�C��
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3PvInfoBlinkDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoEffectiveFlagDef;
import webbroker3.common.define.WEB3PvInfoLastUpdateTimeDisplayFlagDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.data.DisplayContentsRow;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateResponse;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayConditionUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionListService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҕ\���ݒ�ꗗ�T�[�r�XImpl)<BR>
 * �Ǘ��ҕ\���ݒ�ꗗ�T�[�r�X�����N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionListServiceImpl extends WEB3ClientRequestService implements WEB3AdminPvInfoConditionListService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionListServiceImpl.class);

    /**
     * �\���ݒ�ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �����̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����<BR>
     * <BR>
     * ���Ǘ��ҁE�\���ݒ�ꗗ���N�G�X�g�̏ꍇ<BR>
     * �@@this.get�\���ݒ�ꗗ���()���\�b�h���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE���ݏ󋵍X�V���N�G�X�g�̏ꍇ<BR>
     * �@@this.update���ݏ�()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415BD381010E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if(l_request instanceof WEB3AdminPvInfoConditionListRequest )
        {
            l_response = this.getConditionListScreen((WEB3AdminPvInfoConditionListRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminPvInfoConditionUpdateRequest)
        {
            l_response = this.updateCondition((WEB3AdminPvInfoConditionUpdateRequest)l_request);
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
     * (get�\���ݒ�ꗗ���)<BR>
     * �\���ݒ�ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҕ\���ݒ�ꗗ�T�[�r�X)get�\���ݒ�ꗗ��ʁv�Q��<BR>
     * ===============================================<BR>
     * get�\���ݒ�ꗗ���/(�Ǘ��ҁE�\���ݒ�ꗗ�T�[�r�X)<BR>get�\���ݒ�ꗗ���/<BR>
     * 6.create�\���������ꗗ(�Ǘ���)<BR>
     * null���ԋp���ꂽ�ꍇ�́A<BR>
     * �u�\�������ݒ�Ȃ��v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01036<BR>
     * ===============================================<BR>
     * ========================================================<BR>
     * �ڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A<BR>
     * �u�ڋq�����݁v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�\���ݒ�ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionListResponse
     * @@roseuid 415BD43F018B
     */
    protected WEB3AdminPvInfoConditionListResponse getConditionListScreen(WEB3AdminPvInfoConditionListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConditionListScreen(WEB3AdminPvInfoConditionListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPvInfoConditionListResponse l_response = null;

        //1.1 validate()
        l_request.validate();
        log.debug("validate() �����s���܂�");
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFrom���O�C�����() �����s���܂�");
        
        //1.3 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, false);
        log.debug("validate����() �����s���܂�");
        
        //1.4 get���X�R�[�h()
        String l_strBranchCode = l_admin.getBranchCode();
        log.debug("���X�R�[�h() = " + l_strBranchCode);

        //1.5 (*)����t���[
        WEB3PvInfoDisplayConditionUnit[] l_dispCondUnits = null;
        WEB3PvInfoDataManager l_dataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        if(l_request.conditionNumber == null)
        {
            //1.5.1 create�\���������ꗗ(�Ǘ���)
            l_dispCondUnits = l_dataMgr.createDisplayConditionList(l_admin);

            if(l_dispCondUnits == null)
            {
                log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01036.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01036,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //1.6 create��������������(String)
        String l_strQueryString = this.createQueryString(l_request.conditionNumber);
        log.debug("��������������() = " + l_strQueryString);

        //1.7  create���������f�[�^�R���e�i(�Ǘ���, String)
        String[] l_strQueryDataContainers = this.createQueryDataContainer(l_admin, l_request.conditionNumber);
        log.debug("�\�������ԍ� = " + l_request.conditionNumber);

        //1.8 get�\�����eParams�ꗗ(String, String[], String)
        String l_strSortKeys = " effective_flag asc," + " last_updated_timestamp desc," + " term_to desc";
        List l_lisDisplayContentsParamsListTemp = l_dataMgr.getDisplayContentsParamsList(l_strQueryString, l_strQueryDataContainers, l_strSortKeys);

        //1.9get�\�����eParams�ꗗ()�̖߂�l == null����
        if(l_lisDisplayContentsParamsListTemp == null)
        {
            //1.9.1createResponse()
            l_response = (WEB3AdminPvInfoConditionListResponse)l_request.createResponse();
            //1.9.2 �v���p�e�B�Z�b�g

            l_response.displayConditionUnits = l_dispCondUnits;
            l_response.displayContentsUnits = null;
            l_response.totalPages = "1";
            l_response.totalRecords = "0";
            l_response.pageIndex = "1";

            //1.9.3
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        List l_lisDisplayContentsParamsList = new ArrayList();

        int l_intLenTemp = l_lisDisplayContentsParamsListTemp.size();
        for(int i=0; i<l_intLenTemp; i++)
        {
            l_lisDisplayContentsParamsList.add(l_lisDisplayContentsParamsListTemp.get(i));
        }

        //1.10 (*)����t���[
        if(l_request.accountCode != null && l_request.branchCodeList != null)
        {
            //1.10.1 get�ڋq(String, String)
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_gentradeMainAccount = null;
            for (int i = 0; i < l_request.branchCodeList.length; i++)
            {
                try
                {
                    l_gentradeMainAccount = l_gentradeAccountMgr.getMainAccount(l_admin.getInstitutionCode(), l_request.branchCodeList[i], l_request.accountCode);
                }
                catch (WEB3SystemLayerException l_wsex)
                {
                    continue;
                }                
                //�ڋq�I�u�W�F�N�g���擾�ł����ꍇ�́Abreak����B
                if (l_gentradeMainAccount != null)
                {
                    break;
                }
            }
            //Loop���I�������i�K�Ōڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A�u�ڋq�����݁v�̗�O���X���[����B
            if(l_gentradeMainAccount == null)
            {
                log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01035.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.10.2 (*)get�\�����eParams�ꗗ()�̖߂�l�̗v�f��(=�\�����eParams)��Loop����
            int l_intListCnt = l_lisDisplayContentsParamsList.size() - 1;
            List l_lstHistoryParams = new ArrayList();
            for(int i = l_intListCnt; i >= 0; i--)
            {
                DisplayContentsRow l_displayContnetRow = (DisplayContentsRow)l_lisDisplayContentsParamsList.get(i);

                //1.10.2.1  get�{������Params(�ڋq, long)
                l_lstHistoryParams = l_dataMgr.getBrowseHistoryParamsList(l_gentradeMainAccount.getInstitution().getInstitutionCode(), l_request.branchCodeList,l_request.accountCode,l_displayContnetRow.getDisplayContentsId());

                //1.10.2.2 (*)���ʎ擾�`�F�b�N
                if(l_lstHistoryParams == null)
                {
                    l_lisDisplayContentsParamsList.remove(i);
                }

                //1.10.2.3 (*)�c�����`�F�b�N
                if(l_lisDisplayContentsParamsList.size() == 0)
                {
                    //1.10.2.3.1 createResponse()
                    l_response = (WEB3AdminPvInfoConditionListResponse)l_request.createResponse();
                    
                    //1.10.2.3.2 �v���p�e�B�Z�b�g
                    l_response.displayConditionUnits = l_dispCondUnits;
                    l_response.displayContentsUnits = null;
                    l_response.totalPages = "1";
                    l_response.totalRecords = "0";
                    l_response.pageIndex = "1";

                    log.exiting(STR_METHOD_NAME);
                    return l_response;
                }
            }
        }

        //1.11 WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisDisplayContentsParamsList, l_intPageIndex, l_intPageSize);
        
        //1.12 getArrayReturned(l_classType : Class) 
        DisplayContentsParams[] l_displayContentsParamses = 
            (DisplayContentsParams[]) l_pageIndexInfo.getArrayReturned(DisplayContentsParams.class);
        
        //1.13 ArrayList()
        List l_lisDisplayContentsUnit = new ArrayList();

        //1.14 getArrayReturned()�̖߂�l�̗v�f����Loop����
        int l_intDisplayContentsParamsCnt = l_displayContentsParamses.length;
        for(int i = 0; i < l_intDisplayContentsParamsCnt; i++)
        {
            //1.14.1 �\�����e���()
            WEB3PvInfoDisplayContentsUnit l_displayContentsUnit = new WEB3PvInfoDisplayContentsUnit();

            //1.14.2 (*)�v���p�e�B�Z�b�g
            DisplayContentsParams l_displayContentsParams = (DisplayContentsParams)l_displayContentsParamses[i];
            l_displayContentsUnit.displayContentsId =
                WEB3StringTypeUtility.formatNumber(l_displayContentsParams.display_contents_id);
            log.debug("�\�����eID = " + l_displayContentsUnit.displayContentsId);
    
            l_displayContentsUnit.conditionNumber = l_displayContentsParams.condition_no;
            log.debug("�\�������ԍ� = " + l_displayContentsUnit.conditionNumber);
            
            l_displayContentsUnit.priorityDiv = null;
            log.debug("�D��敪 = " + l_displayContentsUnit.priorityDiv);
            
            l_displayContentsUnit.listStartDate = null;
            log.debug("�\������From = " + l_displayContentsUnit.listStartDate);
            
            l_displayContentsUnit.listEndDate = null;
            log.debug("�\������To = " + l_displayContentsUnit.listEndDate);
            
            l_displayContentsUnit.displayTitle = l_displayContentsParams.display_title;
            log.debug("�\���^�C�g�� = " + l_displayContentsUnit.displayTitle);
            
            l_displayContentsUnit.displayMessage = null;
            log.debug("�\������ = " + l_displayContentsUnit.displayMessage);
            
            l_displayContentsUnit.displayColor = null;
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
            l_displayContentsUnit.displayUrl = null;
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
            
            l_displayContentsUnit.lastUpdateMember = null;
            log.debug("�ŏI�X�V�� = " + l_displayContentsUnit.lastUpdateMember);
            
            l_displayContentsUnit.lastUpdatedTimestamp = null;
            log.debug("�ŏI�X�V���� = " + l_displayContentsUnit.lastUpdatedTimestamp);

            //1.14.3 add(�\�����e���I�u�W�F�N�g : Object)
            l_lisDisplayContentsUnit.add(l_displayContentsUnit);
        }
        //1.15 toArray()
        WEB3PvInfoDisplayContentsUnit[] l_displayContentsUnit = new WEB3PvInfoDisplayContentsUnit[l_lisDisplayContentsUnit.size()];
        l_lisDisplayContentsUnit.toArray(l_displayContentsUnit);

        //1.16 createResponse()
        l_response = (WEB3AdminPvInfoConditionListResponse)l_request.createResponse();
        log.debug("createResponse() �����s���܂�");

        //1.17 (*)�v���p�e�B�Z�b�g
        if(l_request.conditionNumber != null)
        {
            l_response.displayConditionUnits = null;
        }
        else
        {
            l_response.displayConditionUnits = l_dispCondUnits;
        }
        l_response.displayContentsUnits = l_displayContentsUnit;
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (update���ݏ�)<BR>
     * �\���ݒ�ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҕ\���ݒ�ꗗ�T�[�r�X)update���ݏ󋵁v�Q��<BR>
     * ========================================================<BR>
     * ���N�G�X�g�f�[�^.�L��/�����敪 != <BR>
     * get�\�����eParams()�̖߂�l.�L��/�����t���O�̏ꍇ�A<BR>
     * �u�L��/�����G���[�v�̗�O���X���[����B <BR>
     *   class: WEB5BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01038<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���ݏ󋵍X�V���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionUpdateResponse
     * @@roseuid 415BEE3B002D
     */
    protected WEB3AdminPvInfoConditionUpdateResponse updateCondition(WEB3AdminPvInfoConditionUpdateRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateCondition(WEB3AdminPvInfoConditionUpdateRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPvInfoConditionUpdateResponse l_response = null;

        //1.1.validate()
        l_request.validate();
        log.debug("validate() �����s���܂�");
        
        //1.2. getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFrom���O�C�����() �����s���܂�");

        //1.3.validate����(�@@�\�R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate����() �����s���܂�");

        //1.4.get�\�����eParams(long)
        WEB3PvInfoDataManager l_dataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        DisplayContentsParams l_displayContentsParams = l_dataMgr.getDisplayContentsParams(Long.parseLong(l_request.displayContentsId));
        log.debug("get�\�����eParams() �����s���܂�");

        if(l_displayContentsParams == null)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01037.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.5.(*)�L��/���������`�F�b�N
        if(l_request.effectiveFlag.equals(l_displayContentsParams.effective_flag))
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01038.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01038,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6.clone�\�����eParams(�\�����eParams)
        DisplayContentsParams l_cloneDisplayContentsParams = l_dataMgr.cloneDisplayContentsParams(l_displayContentsParams);

        //1.7.(*)�v���p�e�B�Z�b�g
        l_cloneDisplayContentsParams.setEffectiveFlag(
            WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES.equals(l_request.effectiveFlag) ?
            WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES :
            WEB3PvInfoEffectiveFlagDef.EFFECTIVE_NO);
        l_cloneDisplayContentsParams.setLastUpdateMember(l_admin.getAdministratorCode());
        l_cloneDisplayContentsParams.setLastUpdatedTimestamp((Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        //1.8.update�\�����e(�\�����eParams)
        l_dataMgr.updateDisplayContents(l_cloneDisplayContentsParams);
        log.debug("update�\�����e() �����s���܂�");

        //1.9.createResponse()
        l_response = (WEB3AdminPvInfoConditionUpdateResponse)l_request.createResponse();
        log.debug("createResponse() �����s���܂�");

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create��������������)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j�ȉ��̌���������\���A����������������쐬����B<BR>
     * �@@[��������]<BR>
     * �@@----------------------------------------------<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�Ǘ���.get�،���ЃR�[�h()�@@����<BR>
     * �@@�@@�\�������ԍ� = �p�����[�^.�\�������ԍ�<BR>
     * �@@----------------------------------------------<BR>
     * ��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = "institution_code = ? "<BR>
     * <BR>
     * �Q�j�p�����[�^.�\�������ԍ� != null�̏ꍇ�A�ȉ��̏������s���B<BR>
     * �@@�Q�|�P�j��������������Ɉȉ��̏�����ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += " and condition_no = ?"<BR>
     * <BR>
     * �R�j�쐬�������������������ԋp����B<BR>
     * @@param l_strConditionNumber - (�\�������ԍ�)<BR>
     * �\�������ԍ�<BR>
     * <BR>
     * 0000�F�@@�_�C���N�g�w��<BR>
     * 1001�F�@@������������&�M�p�����J��<BR>
     * 1002�F�@@������������&�M�p�������J��<BR>
     * 1003�F�@@���֋�����<BR>
     * 1004�F�@@���֋�����<BR>
     * 1005�F�@@�؋����s��<BR>
     * 1006�F�@@���ϊ����ԋ߁i�ꃖ���O�j�̌��ʕۗL<BR>
     * 1007�F�@@���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL<BR>
     * 1008�F�@@�M�p�����J��<BR>
     * 1009�F�@@�M�p�������J��<BR>
     * 1010�F�@@�I�v�V���������J��<BR>
     * 1011�F�@@�����ۗL<BR>
     * 1012�F�@@�M�p���ʕۗL<BR>
     * 1013�F�@@���M�ۗL<BR>
     * 1014�F�@@�ݓ��ۗL<BR>
     * 1015�F�@@�I�v�V�������ʕۗL<BR>
     * 1016�F�@@�~�j���ۗL<BR>
     * 1017�F�@@�敨�ۗL<BR>
     * 1018�F�@@�a����L��&�،��c����<BR>
     * 1019�F�@@�a�������&�،��c����<BR>
     * 1020�F�@@�����E�M�p���������i�����j<BR>
     * 1021�F�@@�����E�M�p���������i�����j<BR>
     * 1022�F�@@�����E�M�p��蔭��<BR>
     * 1023�F�@@�S�ڋq<BR>
     * 1024�F�@@���[���A�h���X���o�^<BR>
     * 1025�F�@@IPO���I<BR>
     * 1026�F�@@IPO�J�グ���I<BR>
     * 1027�F�@@�����~<BR>
     * 1028�F�@@���O�C���p�X���[�h�ύX�v<BR>
     * 1029�F�@@�O���،������J��<BR>
     * 1030�F �O���ۗL<BR>
     * 1031�F �O�����������i�����j<BR>
     * 1032�F �O�����������i�����j<BR>
     * 1033�F �O����蔭��<BR>
     * 1041�F�@@20������1����30������5���ȉ�<BR>
     * 1042�F�@@20������1����30������6��<BR>
     * 1043�F�@@20������2����30������6���ȉ�<BR>
     * 1044�F�@@20������3���ȏ�<BR>
     * 1045�F�@@30������2�`4��<BR>
     * 1046�F�@@30������5��<BR>
     * 1047�F�@@30������6��<BR>
     * 1048�F�@@30������7���ȏ�<BR>
     * @@return String
     * @@roseuid 415BD71A037F
     */
    protected String createQueryString(String l_strConditionNumber)
    {
        final String STR_METHOD_NAME = " createQueryString(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�ȉ��̌���������\���A����������������쐬����B
        String l_strCreateQuery = "institution_code = ? ";

        //�Q�j�p�����[�^.�\�������ԍ� != null�̏ꍇ�A�ȉ��̏������s���B
        if(l_strConditionNumber != null)
        {
            //�Q�|�P�j��������������Ɉȉ��̏�����ǉ�����B
            l_strCreateQuery += " and condition_no = ?";
        }

        log.exiting(STR_METHOD_NAME);
        //�R�j�쐬�������������������ԋp����B
        return l_strCreateQuery;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������������"&#63"�����ɃZ�b�g����p�����[�^���X�g(������z��)���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�ȉ��̏��ԂŃp�����[�^��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�E�p�����[�^.�Ǘ���.get�،���ЃR�[�h()<BR>
     * <BR>
     * �R�j�p�����[�^.�\�������ԍ� != null�̏ꍇ�́A<BR>
     * �@@�쐬����ArrayList�Ɉȉ��̃p�����[�^��ǉ�����B<BR>
     * �@@�@@�E�p�����[�^.�\�������ԍ�<BR>
     * <BR>
     * �S�j�쐬����ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g
     * @@param l_strConditionNumber - (�\�������ԍ�)<BR>
     * �\�������ԍ�<BR>
     * <BR>
     * 0000�F�@@�_�C���N�g�w��<BR>
     * 1001�F�@@������������&�M�p�����J��<BR>
     * 1002�F�@@������������&�M�p�������J��<BR>
     * 1003�F�@@���֋�����<BR>
     * 1004�F�@@���֋�����<BR>
     * 1005�F�@@�؋����s��<BR>
     * 1006�F�@@���ϊ����ԋ߁i�ꃖ���O�j�̌��ʕۗL<BR>
     * 1007�F�@@���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL<BR>
     * 1008�F�@@�M�p�����J��<BR>
     * 1009�F�@@�M�p�������J��<BR>
     * 1010�F�@@�I�v�V���������J��<BR>
     * 1011�F�@@�����ۗL<BR>
     * 1012�F�@@�M�p���ʕۗL<BR>
     * 1013�F�@@���M�ۗL<BR>
     * 1014�F�@@�ݓ��ۗL<BR>
     * 1015�F�@@�I�v�V�������ʕۗL<BR>
     * 1016�F�@@�~�j���ۗL<BR>
     * 1017�F�@@�敨�ۗL<BR>
     * 1018�F�@@�a����L��&�،��c����<BR>
     * 1019�F�@@�a�������&�،��c����<BR>
     * 1020�F�@@�����E�M�p���������i�����j<BR>
     * 1021�F�@@�����E�M�p���������i�����j<BR>
     * 1022�F�@@�����E�M�p��蔭��<BR>
     * 1023�F�@@�S�ڋq<BR>
     * 1024�F�@@���[���A�h���X���o�^<BR>
     * 1025�F�@@IPO���I<BR>
     * 1026�F�@@IPO�J�グ���I<BR>
     * 1027�F�@@�����~<BR>
     * 1028�F�@@���O�C���p�X���[�h�ύX�v<BR>
     * 1029�F�@@�O���،������J��<BR>
     * 1030�F �O���ۗL<BR>
     * 1031�F �O�����������i�����j<BR>
     * 1032�F �O�����������i�����j<BR>
     * 1033�F �O����蔭��<BR>
     * 1041�F�@@20������1����30������5���ȉ�<BR>
     * 1042�F�@@20������1����30������6��<BR>
     * 1043�F�@@20������2����30������6���ȉ�<BR>
     * 1044�F�@@20������3���ȏ�<BR>
     * 1045�F�@@30������2�`4��<BR>
     * 1046�F�@@30������5��<BR>
     * 1047�F�@@30������6��<BR>
     * 1048�F�@@30������7���ȏ�<BR>
     * @@return String[]
     * @@roseuid 415BD71A039E
     */
    protected String[] createQueryDataContainer(WEB3Administrator l_administrator, String l_strConditionNumber)
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(WEB3Administrator, String)";
        log.entering(STR_METHOD_NAME);

        //�P�jArrayList�𐶐�����B
        List l_lisArrayList = new ArrayList();

        //�Q�j�ȉ��̏��ԂŃp�����[�^��ArrayList�ɒǉ�����B
        l_lisArrayList.add(l_administrator.getInstitutionCode());
        log.debug("�،���ЃR�[�h = " + l_administrator.getInstitutionCode());

        //�R�j�p�����[�^.�\�������ԍ� != null�̏ꍇ�́A�쐬����ArrayList�Ɉȉ��̃p�����[�^��ǉ�����B
        if(l_strConditionNumber != null)
        {
            l_lisArrayList.add(l_strConditionNumber);
            log.debug("�\�������ԍ� = " + l_strConditionNumber);
        }

        //�S�j�쐬����ArrayList.toArray()�̖߂�l��ԋp����B
        String[] l_strArray = new String[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_strArray);

        log.exiting(STR_METHOD_NAME);
        return l_strArray;
    }
}

@
