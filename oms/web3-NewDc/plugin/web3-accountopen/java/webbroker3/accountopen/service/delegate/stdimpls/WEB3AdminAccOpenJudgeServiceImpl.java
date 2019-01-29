head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenJudgeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐR���T�[�r�XImpl (WEB3AdminAccOpenJudgeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 ����(���u) �V�K�쐬
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AccOpenJudgeWaiting;
import webbroker3.accountopen.data.AccOpenWaitingParams;
import webbroker3.accountopen.message.WEB3AccOpenInspectInfo;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListSearchRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListSearchResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInfoCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenJudgeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CheckDivDef;
import webbroker3.common.define.WEB3ReviewCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (�Ǘ��Ҍ����J�ݐR���T�[�r�XImpl)<BR>
 * �Ǘ��Ҍ����J�ݐR���T�[�r�X�����N���X
 *   
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminAccOpenJudgeServiceImpl implements WEB3AdminAccOpenJudgeService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance
        (WEB3AdminAccOpenJudgeServiceImpl.class);
    
    /**
     * @@roseuid 44912C1C0119
     */
    public WEB3AdminAccOpenJudgeServiceImpl() 
    {
     
    }
    
    /**
     * �����J�ݐR�����������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐR���Ώۈꗗ�������N�G�X�g�̏ꍇ<BR> 
     * �@@�|get�R���Ώۈꗗ�������()���R�[������B<BR> 
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐR���ꗗ���N�G�X�g�̏ꍇ<BR>
     * �@@�|get�R���ꗗ()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐR�����F�m�F���N�G�X�g�̏ꍇ<BR> 
     * �@@�|validate���F()���R�[������B<BR> 
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐR�����F�������N�G�X�g�̏ꍇ<BR> 
     * �@@�|submit���F()���R�[������B<BR> 
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐR���۔F�m�F���N�G�X�g�̏ꍇ<BR> 
     * �@@�|validate�۔F()���R�[������B<BR> 
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐR���۔F�������N�G�X�g�̏ꍇ<BR> 
     * �@@�|submit�۔F()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4473B1140125
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐR���Ώۈꗗ�������N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminAccOpenInspectListSearchRequest)
        {
            //get�R���Ώۈꗗ�������()���R�[������B
            log.info("WEB3AdminAccOpenInspectListSearchRequest");
            WEB3AdminAccOpenInspectListSearchResponse l_response = getInspectObjectListSearchScreen(
                (WEB3AdminAccOpenInspectListSearchRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        else if (l_request instanceof WEB3AdminAccOpenInspectListRequest)
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐR���ꗗ���N�G�X�g�̏ꍇ
        {
            //get�R���ꗗ()���R�[������B
            log.info("WEB3AdminAccOpenInspectListRequest");
            WEB3AdminAccOpenInspectListResponse l_response = getInspectList(
                (WEB3AdminAccOpenInspectListRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        else if (l_request instanceof WEB3AdminAccOpenInspectConsentConfirmRequest)
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐR�����F�m�F���N�G�X�g�̏ꍇ
        {
            //validate���F()���R�[������B
            log.info("WEB3AdminAccOpenInspectConsentConfirmRequest");
            WEB3AdminAccOpenInspectConsentConfirmResponse l_response = validateConsent(
                (WEB3AdminAccOpenInspectConsentConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        else if (l_request instanceof WEB3AdminAccOpenInspectConsentCompleteRequest)
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐR�����F�������N�G�X�g�̏ꍇ 
        {
            // submit���F����()���R�[������B
            log.info("WEB3AdminAccOpenInspectConsentCompleteRequest");
            WEB3AdminAccOpenInspectConsentCompleteResponse l_response = submitConsent(
                (WEB3AdminAccOpenInspectConsentCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        else if (l_request instanceof WEB3AdminAccOpenInspectDenyConfirmRequest)
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐR���۔F�m�F���N�G�X�g�̏ꍇ 
        {
            //validate�۔F�m�F()���R�[������B
            log.info("WEB3AdminAccOpenInspectDenyConfirmRequest");
            WEB3AdminAccOpenInspectDenyConfirmResponse l_response = validateDeny(
                (WEB3AdminAccOpenInspectDenyConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenInspectDenyCompleteRequest)
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐR���۔F�������N�G�X�g�̏ꍇ
        {
            // submit�۔F����()���R�[������B
            log.info("WEB3AdminAccOpenInspectDenyCompleteRequest");
            WEB3AdminAccOpenInspectDenyCompleteResponse l_response = submitDeny(
                (WEB3AdminAccOpenInspectDenyCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }                 
    }
    
    /**
     * (get�R���Ώۈꗗ�������)<BR>
     * �����J�ݐR���Ώۈꗗ������ʕ\���������s���B<BR> 
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�����J�݁i�R���jget�R���Ώۈꗗ������ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݐR���Ώۈꗗ�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenInspectListSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473C74B0019
     */
    protected WEB3AdminAccOpenInspectListSearchResponse getInspectObjectListSearchScreen(
        WEB3AdminAccOpenInspectListSearchRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInspectObjectListSearchScreen(WEB3AdminAccOpenInspectListSearchRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        
        //1.2. validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.JUDGE, false);
        
        //1.3. createResponse( )
        WEB3AdminAccOpenInspectListSearchResponse l_response = 
            (WEB3AdminAccOpenInspectListSearchResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�R���ꗗ)<BR>
     * �����J�ݐR���ꗗ��ʕ\���������s���B<BR> 
     * <BR>
     * �V�[�P���X�}<BR> 
     * �u�����J�݁i�R���jget�R���ꗗ�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݐR���ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenInspectListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473C7510365
     */
    protected WEB3AdminAccOpenInspectListResponse getInspectList(
        WEB3AdminAccOpenInspectListRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInspectList(WEB3AdminAccOpenInspectListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate( )
        l_request.validate();
        
        //1.2. getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo(); 
        
        //1.3. validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.JUDGE, false);
        
        //1.4. validate���X����(���X�R�[�h : String)
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.5. to�����J�ݐR���҂�( )
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        
        //1.5.1. �����J�ݐR���҂�( )
        WEB3AccOpenJudgeWaiting l_judgeWaiting = 
            l_infoCreatedService.toAccOpenJudgeWaiting();
        String l_strOccurredDateFrom = null; 
        String l_strOccurredDateTo = null;
        
        if (l_request.occurredDateFrom != null)
        {
            l_strOccurredDateFrom = WEB3DateUtility.formatDate(l_request.occurredDateFrom, "yyyyMMdd");
        }
        
        if (l_request.occurredDateTo != null)
        {
            l_strOccurredDateTo = WEB3DateUtility.formatDate(l_request.occurredDateTo, "yyyyMMdd");
        }
        
        //1.6. get�،���ЃR�[�h( )
        String l_strInstitutionCode= l_admin.getInstitutionCode();
        
        //1.7. select�R���Ώێ��ʃR�[�h�ꗗ(String, String, String, String, String, 
        //     String, String, String, String, �����J�݃\�[�g�L�[[])
        String[] l_strRequestNumberLists = 
            l_judgeWaiting.selectJudgeObjectAccOpenRequestNumberList(
                l_strInstitutionCode, 
                l_request.branchCode,
                l_request.accountCode,
                l_request.requestNumber,
                l_strOccurredDateFrom,
                l_strOccurredDateTo,
                l_request.reviewCode,
                l_request.checkerCode,
                l_request.checkStatus,
                l_request.sortKeys);
        
        //1.8. (*)���ʃR�[�h�ꗗ��ҏW����  
        int l_intPageSize = 0;
        int l_intPageIndex = 0;
        
        l_intPageSize = Integer.parseInt(l_request.pageSize);
        l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        
        WEB3PageIndexInfo l_pageIndexInfo = 
            new WEB3PageIndexInfo(
                l_strRequestNumberLists,
                l_intPageIndex,
                l_intPageSize);
        
        //���y�[�W��
        String l_strTotalPages = l_pageIndexInfo.getTotalPages() + "";
        
        //�����R�[�h��
        String l_strTotalRecords = l_pageIndexInfo.getTotalRecords() + "";
        
        //�\���y�[�W�ԍ�
        String l_strPageIndex = l_pageIndexInfo.getPageIndex() + "";
        
        l_strRequestNumberLists =
            (String[]) l_pageIndexInfo.getArrayReturned(String.class);
        
        //1.8.1
        if (l_strRequestNumberLists.length == 0)
        {
            WEB3AdminAccOpenInspectListResponse l_response = 
                (WEB3AdminAccOpenInspectListResponse)l_request.createResponse();
            //�����J�ݐR���Ώۈꗗ�Fnull
            l_response.accopenInspectList = null;
            //���y�[�W���F0
            l_response.totalPages = l_strTotalPages;
            
            //�����R�[�h���F0
            l_response.totalRecords = l_strTotalRecords;
            
            //�\���y�[�W�ԍ��F�\���y�[�W�ԍ�
            l_response.pageIndex = l_strPageIndex;
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //1.9. select�R���Ώۈꗗ(String, String[], �����J�݃\�[�g�L�[[])
        l_judgeWaiting.selectJudgeObjectList(l_strInstitutionCode, l_strRequestNumberLists, l_request.sortKeys);
        
        //1.10. get�����J�ݐR���҂��s��( )
        int l_intAccOpenWaitingParams = l_judgeWaiting.getAccOpenWaitingParamsNumber();
        
        //1.11. (*)�����J�ݐR�����̔z��I�u�W�F�N�g�̒�`
        WEB3AccOpenInspectInfo[] l_accOpenInspectInfos = 
            new WEB3AccOpenInspectInfo[l_intAccOpenWaitingParams];
        
        //1.12. (*)get�����J�ݐR���҂��s��()�̖߂�l�̌������ALoop�������{
        for (int i = 0; i < l_intAccOpenWaitingParams; i++)
        {
            //1.12.1. get�����J�ݐR���҂��s(int)
            AccOpenWaitingParams l_accOpenWaitingParams = 
                l_judgeWaiting.getAccOpenWaitingParams(i);
            WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
            
            //1.12.2. �����J�݌����q(String, String)
            try
            {
                l_expAccountOpen = 
                    new WEB3AccOpenExpAccountOpen(
                        l_strInstitutionCode, 
                        l_accOpenWaitingParams.getAccOpenRequestNumber());
            }
            catch(NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
            
            l_accOpenInspectInfos[i] = new WEB3AccOpenInspectInfo();
            //1.12.3. (*)�����J�ݐR�����I�u�W�F�N�g�̒�`
            //���X�R�[�h�F�����J�ݐR���҂�Params�I�u�W�F�N�g.���X�R�[�h 
            l_accOpenInspectInfos[i].branchCode = l_accOpenWaitingParams.getBranchCode();
            
            //�ڋq�R�[�h�F�����J�ݐR���҂�Params�I�u�W�F�N�g.�ڋq�R�[�h 
            l_accOpenInspectInfos[i].accountCode = l_accOpenWaitingParams.getAccountCode();
            
            //���ʃR�[�h�F�����J�ݐR���҂�Params�I�u�W�F�N�g.���ʃR�[�h 
            l_accOpenInspectInfos[i].requestNumber = l_accOpenWaitingParams.getAccOpenRequestNumber();
            
            //�ڋq���i�J�i�j�F�����J�݌����q�I�u�W�F�N�g.get�ڋq���i�J�i�j() + "�@@" + �����J�݌����q�I�u�W�F�N�g.get�ڋq���i�J�i�j() 
            l_accOpenInspectInfos[i].accountNameKana = 
                l_expAccountOpen.getFamilyNameAlt1() + "�@@" + l_expAccountOpen.getGivenNameAlt1();
            
            //���N�����N���F�����J�݌����q�I�u�W�F�N�g.get���N�����N��() 
            l_accOpenInspectInfos[i].eraBorn = l_expAccountOpen.getEraBorn();
            
            //���N�����F�����J�݌����q�I�u�W�F�N�g.get���N����() 
            l_accOpenInspectInfos[i].bornDate = l_expAccountOpen.getBornDate();

            //�d�b�ԍ��F�����J�݌����q�I�u�W�F�N�g.get�d�b�ԍ�() 
            l_accOpenInspectInfos[i].telephone = l_expAccountOpen.getTelephone();
            
            //�g�єԍ��F�����J�݌����q�I�u�W�F�N�g.get�A����d�b�ԍ��i�g�сj() 
            l_accOpenInspectInfos[i].mobileTelephone = l_expAccountOpen.getMobile();
            
            //���[���A�h���X�F�����J�݌����q�I�u�W�F�N�g.getemail�A�h���X() 
            l_accOpenInspectInfos[i].mailAddress = l_expAccountOpen.getEmailAddress();
            
            //�Z���P�F�����J�݌����q�I�u�W�F�N�g.get�Z���P() 
            l_accOpenInspectInfos[i].address1 = l_expAccountOpen.getAddressLine1();
            
            //�Z���Q�F�����J�݌����q�I�u�W�F�N�g.get�Z���Q() 
            l_accOpenInspectInfos[i].address2 = l_expAccountOpen.getAddressLine2();
            
            //�Z���R�F�����J�݌����q�I�u�W�F�N�g.get�Z���R() 
            l_accOpenInspectInfos[i].address3 = l_expAccountOpen.getAddressLine3();
            
            //���������F�����J�ݐR���҂�Params�I�u�W�F�N�g.�쐬���� 
            l_accOpenInspectInfos[i].occurredDate = l_accOpenWaitingParams.getCreatedTimestamp();
            
            //�R����ʁF�����J�ݐR���҂�Params�I�u�W�F�N�g.�R����� 
            l_accOpenInspectInfos[i].reviewCode = l_accOpenWaitingParams.getReviewCode();
            
            //�R���敪�F�����J�ݐR���҂�Params�I�u�W�F�N�g.�R���敪 
            l_accOpenInspectInfos[i].checkDiv = l_accOpenWaitingParams.getCheckDiv();
            
            //�d���敪�F�����J�ݐR���҂�Params�I�u�W�F�N�g.�d���敪 
            l_accOpenInspectInfos[i].duplicateDiv = l_accOpenWaitingParams.getDuplicationDiv();
            
            //�����J�ݐR���҂�Params�I�u�W�F�N�g.�R���敪 != "0"(�R���҂�)�̏ꍇ
            if (!WEB3CheckDivDef.CHECK_WAITING.equals(l_accOpenWaitingParams.getCheckDiv()))
            {
	            //�R�������F�����J�ݐR���҂�Params�I�u�W�F�N�g.�X�V���� 
	            l_accOpenInspectInfos[i].checkDate = l_accOpenWaitingParams.getLastUpdatedTimestamp();
	            
	            //�R���S���ҁF�����J�ݐR���҂�Params�I�u�W�F�N�g.�R���҃R�[�h
	            l_accOpenInspectInfos[i].checkerCode = l_accOpenWaitingParams.getCheckerCode();
            }
            //���̑��̏ꍇ
            else
            {
	            //�R�������Fnull 
	            l_accOpenInspectInfos[i].checkDate = null;
	            
	            //�R���S���ҁFnull
	            l_accOpenInspectInfos[i].checkerCode = null;
            }

            //1.12.5. (*)�R����ʂ� 1�F����ڋq�`�F�b�N �̏ꍇ
            if (WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_MAIN.equals(l_accOpenInspectInfos[i].reviewCode))
            {
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_gentradeAccountManager = 
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                WEB3GentradeMainAccount l_mainAccount = null;
                MainAccountRow l_mainAccountRow = null;
                MainAccountAllRow l_mainAccountAllRow = null;
                
                try
                {
                    //1.12.5.1. get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
                    l_mainAccount = 
                        l_gentradeAccountManager.getMainAccount(
                            l_strInstitutionCode, 
                            l_accOpenWaitingParams.getDiploBranchCode(), 
                            l_accOpenWaitingParams.getDiploAccountCode());
                    
                    // get�ڋq�s( )
                    l_mainAccountRow = l_mainAccount.getMainAccountRow();
                }
                catch(Exception l_ex)
                {
                    l_mainAccountRow = null;
                }
                
                try
                {
                    //1.12.5.2. (*)get�ڋq�ŌڋqRow�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ
                    //1.12.5.2.1 get�ڋq�i�S���X���j(�،���ЃR�[�h : String, ���X�R�[�h : String, �ڋq�R�[�h : String)
                    if (l_mainAccountRow == null)
                    {
                        l_mainAccountAllRow = 
                            l_gentradeAccountManager.getMainAccountAll(
                                l_strInstitutionCode, 
                                l_accOpenWaitingParams.getDiploBranchCode(), 
                                l_accOpenWaitingParams.getDiploAccountCode());
                    }
                }
                catch(Exception l_ex)
                {
                    l_mainAccountAllRow = null; 
                }
                
                //�ڋqRow�I�u�W�F�N�g���擾�ł����ꍇ
                if (l_mainAccountRow != null)
                {
                    //�d���ڋq���i�J�i�j�F�ڋqRow�I�u�W�F�N�g.���O(�c��1) 
                    l_accOpenInspectInfos[i].dupliAccountNameKana = l_mainAccountRow.getFamilyNameAlt1();
                    
                    //�d�����N�����N���F�ڋqRow�I�u�W�F�N�g.���N�����N�� 
                    l_accOpenInspectInfos[i].dupliEraBorn = l_mainAccountRow.getEraBorn();
                    
                    //�d�����N�����F�ڋqRow�I�u�W�F�N�g.���N���� 
                    l_accOpenInspectInfos[i].dupliBornDate = l_mainAccountRow.getBornDate();
                    
                    //�d���d�b�ԍ��F�ڋqRow�I�u�W�F�N�g.�d�b�ԍ� 
                    l_accOpenInspectInfos[i].dupliTelephone = l_mainAccountRow.getTelephone();
                    
                    //�d���g�єԍ��F�ڋqRow�I�u�W�F�N�g.�A����d�b�ԍ��i�g�сj
                    l_accOpenInspectInfos[i].dupliMobileTelephone = l_mainAccountRow.getMobile();
                    
                    //�d�����[���A�h���X�F�ڋqRow�I�u�W�F�N�g.email�A�h���X 
                    l_accOpenInspectInfos[i].dupliMailAddress = l_mainAccountRow.getEmailAddress();
                    
                    //�d���Z���P�F�ڋqRow�I�u�W�F�N�g.�Z���P 
                    l_accOpenInspectInfos[i].dupliAddress1 = l_mainAccountRow.getAddressLine1();
                    
                    //�d���Z���Q�F�ڋqRow�I�u�W�F�N�g.�Z���Q 
                    l_accOpenInspectInfos[i].dupliAddress2 = l_mainAccountRow.getAddressLine2();
                    
                    //�d���Z���R�F�ڋqRow�I�u�W�F�N�g.�Z���R
                    l_accOpenInspectInfos[i].dupliAddress3 = l_mainAccountRow.getAddressLine3();
                    
                    //�d�����X�R�[�h�F�����J�ݐR���҂�Params�I�u�W�F�N�g.�d���Ώە��X�R�[�h 
                    l_accOpenInspectInfos[i].dupliBranchCode = l_accOpenWaitingParams.getDiploBranchCode();
                    
                    //�d���ڋq�R�[�h�F�����J�ݐR���҂�Params�I�u�W�F�N�g.�d���Ώیڋq�R�[�h 
                    l_accOpenInspectInfos[i].dupliAccountCode = l_accOpenWaitingParams.getDiploAccountCode();
                }
                
                //�ڋq�i�S���X���jRow�I�u�W�F�N�g���擾�ł����ꍇ
                else if (l_mainAccountAllRow != null)
                {
                    //�d���ڋq���i�J�i�j�F�ڋq�i�S���X���jRow�I�u�W�F�N�g.�ڋq���i�J�i�j 
                    l_accOpenInspectInfos[i].dupliAccountNameKana = l_mainAccountAllRow.getFamilyNameAlt1();
                    
                    //�d�����N�����N���F�ڋq�i�S���X���jRow�I�u�W�F�N�g.���N�����i�����j 
                    l_accOpenInspectInfos[i].dupliEraBorn = l_mainAccountAllRow.getEraBorn();
                    
                    //�d�����N�����F�ڋq�i�S���X���jRow�I�u�W�F�N�g.���N���� 
                    if (l_mainAccountAllRow.getBornDate().length() == 8)
                    {
                        l_accOpenInspectInfos[i].dupliBornDate = 
                            l_mainAccountAllRow.getBornDate().substring(2, 8);
                    }
                    else
                    {
                        l_accOpenInspectInfos[i].dupliBornDate = null;
                    }
                    
                    //�d���d�b�ԍ��F�ڋq�i�S���X���jRow�I�u�W�F�N�g.�d�b�ԍ� 
                    l_accOpenInspectInfos[i].dupliTelephone = l_mainAccountAllRow.getTelephone();
                    
                    //�d���g�єԍ��F�ڋq�i�S���X���jRow�I�u�W�F�N�g.�A����d�b�ԍ� 
                    l_accOpenInspectInfos[i].dupliMobileTelephone = l_mainAccountAllRow.getContactAddressTelephone();
                    
                    //�d�����[���A�h���X�Fnull  
                    l_accOpenInspectInfos[i].dupliMailAddress = null;
                    
                    //�d���Z���P�F�ڋq�i�S���X���jRow�I�u�W�F�N�g.�Z�������P 
                    l_accOpenInspectInfos[i].dupliAddress1 = l_mainAccountAllRow.getAddressLine1();
                    
                    //�d���Z���Q�F�ڋq�i�S���X���jRow�I�u�W�F�N�g.�Z������2 
                    l_accOpenInspectInfos[i].dupliAddress2 = l_mainAccountAllRow.getAddressLine2();
                    
                    //�d���Z���R�F�ڋq�i�S���X���jRow�I�u�W�F�N�g.�Z������3
                    l_accOpenInspectInfos[i].dupliAddress3 = l_mainAccountAllRow.getAddressLine3();
                    
                    //�d�����X�R�[�h�F�����J�ݐR���҂�Params�I�u�W�F�N�g.�d���Ώە��X�R�[�h 
                    l_accOpenInspectInfos[i].dupliBranchCode = l_accOpenWaitingParams.getDiploBranchCode();
                    
                    //�d���ڋq�R�[�h�F�����J�ݐR���҂�Params�I�u�W�F�N�g.�d���Ώیڋq�R�[�h 
                    l_accOpenInspectInfos[i].dupliAccountCode = l_accOpenWaitingParams.getDiploAccountCode();
                }
                
                //�ڋq�ƌڋq�i�S���X���j�������Ƃ��擾�ł��Ȃ��ꍇ
                else
                {
                    //�d�����X�R�[�h�F�����J�ݐR���҂�Params�I�u�W�F�N�g.�d���Ώە��X�R�[�h 
                    l_accOpenInspectInfos[i].dupliBranchCode = l_accOpenWaitingParams.getDiploBranchCode();
                    
                    //�d���ڋq�R�[�h�F�����J�ݐR���҂�Params�I�u�W�F�N�g.�d���Ώیڋq�R�[�h 
                    l_accOpenInspectInfos[i].dupliAccountCode = l_accOpenWaitingParams.getDiploAccountCode();
                }
                
            }
            
            //1.12.6. (*)�R����ʂ� 2�F����ڋq�`�F�b�N�i�����j �̏ꍇ
            else if (WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_EXP.equals(l_accOpenInspectInfos[i].reviewCode))
            {
                //1.12.6.1. �����J�݌����q(String, String, String)
                try
                {
                    l_expAccountOpen = 
                        new WEB3AccOpenExpAccountOpen(
                            l_strInstitutionCode, 
                            l_accOpenWaitingParams.getDiploBranchCode(), 
                            l_accOpenWaitingParams.getDiploAccountCode());
                }
                catch(NotFoundException l_ex)
                {
                    //�����J�݌����q���擾�ł��Ȃ��ꍇ
                    l_expAccountOpen = null;
                    
                    //�d�����X�R�[�h�F�����J�ݐR���҂�Params�I�u�W�F�N�g.�d���Ώە��X�R�[�h 
                    l_accOpenInspectInfos[i].dupliBranchCode = l_accOpenWaitingParams.getDiploBranchCode();
                    
                    //�d���ڋq�R�[�h�F�����J�ݐR���҂�Params�I�u�W�F�N�g.�d���Ώیڋq�R�[�h 
                    l_accOpenInspectInfos[i].dupliAccountCode = l_accOpenWaitingParams.getDiploAccountCode();
                }
                
                if(l_expAccountOpen != null)
                {
                    //1.12.6.1.1. (*)�v���p�e�B�Z�b�g
                    //�d���ڋq���i�J�i�j�F�����J�݌����q.get�ڋq���i�J�i�j + "�@@" + �����J�݌����q.get�ڋq���i�J�i�j
                    l_accOpenInspectInfos[i].dupliAccountNameKana = 
                        l_expAccountOpen.getFamilyNameAlt1() + "�@@" + l_expAccountOpen.getGivenNameAlt1();
                    
                    //�d�����N�����N���F�����J�݌����q.get���N�����N�� 
                    l_accOpenInspectInfos[i].dupliEraBorn = l_expAccountOpen.getEraBorn();
                    
                    //�d�����N�����F�����J�݌����q.get���N���� 
                    l_accOpenInspectInfos[i].dupliBornDate = l_expAccountOpen.getBornDate();
                    
                    //�d���d�b�ԍ��F�����J�݌����q.get�d�b�ԍ� 
                    l_accOpenInspectInfos[i].dupliTelephone = l_expAccountOpen.getTelephone();
                    
                    //�d���g�єԍ��F�����J�݌����q.get�A����d�b�ԍ��i�g�сj 
                    l_accOpenInspectInfos[i].dupliMobileTelephone = l_expAccountOpen.getMobile();                
                    
                    //�d�����[���A�h���X�F�����J�݌����q.getemail�A�h���X 
                    l_accOpenInspectInfos[i].dupliMailAddress = l_expAccountOpen.getEmailAddress();
                    
                    //�d���Z���P�F�����J�݌����q.get�Z���P 
                    l_accOpenInspectInfos[i].dupliAddress1 = l_expAccountOpen.getAddressLine1();
                    
                    //�d���Z���Q�F�����J�݌����q.get�Z���Q 
                    l_accOpenInspectInfos[i].dupliAddress2 = l_expAccountOpen.getAddressLine2();
                    
                    //�d���Z���R�F�����J�݌����q.get�Z���R 
                    l_accOpenInspectInfos[i].dupliAddress3 = l_expAccountOpen.getAddressLine3();
                    
                    //�d�����X�R�[�h�F�����J�ݐR���҂�Params�I�u�W�F�N�g.�d���Ώە��X�R�[�h 
                    l_accOpenInspectInfos[i].dupliBranchCode = l_accOpenWaitingParams.getDiploBranchCode();
                    
                    //�d���ڋq�R�[�h�F�����J�ݐR���҂�Params�I�u�W�F�N�g.�d���Ώیڋq�R�[�h 
                    l_accOpenInspectInfos[i].dupliAccountCode = l_accOpenWaitingParams.getDiploAccountCode();
                }
            }
            
            //1.12.7. (*)�R����ʂ� 3�FY�q�`�F�b�N �̏ꍇ
            else if (WEB3ReviewCodeDef.YELLOW_ACCOUNT_CHECK.equals(l_accOpenInspectInfos[i].reviewCode))
            {
                //1.12.7.1. (*)�v���p�e�B�Z�b�g
                //Y�qID�F�����J�ݐR���҂�Params�I�u�W�F�N�g.Y�qID
                l_accOpenInspectInfos[i].yellowAccountId = String.valueOf(l_accOpenWaitingParams.getYCustomerId());
                
                //Y�q�Ǘ����X�R�[�h�F�����J�ݐR���҂�Params�I�u�W�F�N�g.Y�q�Ǘ����X�R�[�h 
                l_accOpenInspectInfos[i].yAccountBranchCode = l_accOpenWaitingParams.getControlBranchCode();
                
                //Y�q�Ɩ��敪�F�����J�ݐR���҂�Params�I�u�W�F�N�g.Y�q�Ɩ��敪 
                l_accOpenInspectInfos[i].yAccountBusinessDiv = l_accOpenWaitingParams.getOperationDiv();
                
                //Y�q�Ǘ�No�F�����J�ݐR���҂�Params�I�u�W�F�N�g.Y�q�Ǘ�No 
                l_accOpenInspectInfos[i].yAccountMngNo = String.valueOf(l_accOpenWaitingParams.getControlNumber());
            }
        }
        
        //1.13. (*)�v���p�e�B�Z�b�g
        WEB3AdminAccOpenInspectListResponse l_response = (WEB3AdminAccOpenInspectListResponse)l_request.createResponse();
        
        //�����J�ݐR���Ώۈꗗ�F�����J�ݐR�����I�u�W�F�N�g�̔z��
        l_response.accopenInspectList = l_accOpenInspectInfos;
        
        //���y�[�W���F���y�[�W��
        l_response.totalPages = l_strTotalPages;
        
        //�����R�[�h���F�����R�[�h��
        l_response.totalRecords = l_strTotalRecords;
        
        //�\���y�[�W�ԍ��F�\���y�[�W�ԍ�
        l_response.pageIndex = l_strPageIndex;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate���F)<BR>
     * �����J�ݐR�����F�m�F��ʕ\���������s���B<BR> 
     * <BR>
     * �V�[�P���X�}<BR> 
     * �u�����J�݁i�R���jvalidate���F�v�Q�ƁB<BR> 
     * ==========================================================<BR>
     * ��̈ʒu     :�@@1.9.(*) ���F�ρE�۔F�ς̃f�[�^�����݂���ꍇ<BR>
     * �iis�R����() == true�j�A��O���X���[����B<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_02448 <BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݐR�����F�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenInspectConsentConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473C757029A
     */
    protected WEB3AdminAccOpenInspectConsentConfirmResponse validateConsent(
        WEB3AdminAccOpenInspectConsentConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateConsent(WEB3AdminAccOpenInspectConsentConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate( )
        l_request.validate();
        
        //1.2. getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo(); 
        
        //1.3. validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.JUDGE, true);
        
        //1.4. to�����J�ݐR���҂�( )
        //1.4.1. �����J�ݐR���҂�( )
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        WEB3AccOpenJudgeWaiting l_judgeWaiting = 
            l_infoCreatedService.toAccOpenJudgeWaiting();
        
        //1.5. get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6. select�R���Ώۈꗗ(String, String[], �����J�݃\�[�g�L�[[])
        l_judgeWaiting.selectJudgeObjectList(l_strInstitutionCode, l_request.requestNumber, l_request.sortKeys);
        
        //1.7. get�����J�ݐR���҂��s��( )
        int l_intAccOpenWaitingParams = l_judgeWaiting.getAccOpenWaitingParamsNumber();
        
        //1.8. is�R����( )   
        //1.9. (*) ���F�ρE�۔F�ς̃f�[�^�����݂���ꍇ�iis�R����() == true�j�A��O���X���[����B
        if (l_judgeWaiting.isChecked())
        {
            log.debug("�Ώۂ̃f�[�^�͐R���ςł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02448,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ώۂ̃f�[�^�͐R���ςł��B");
        }
        
        //1.10. (*)�����J�ݐR�����̔z��I�u�W�F�N�g�̒�`
        WEB3AccOpenInspectInfo[] l_accOpenInspectInfos = 
            new WEB3AccOpenInspectInfo[l_intAccOpenWaitingParams];
        
        //1.11. (*)get�����J�ݐR���҂��s��()�̖߂�l�̌������ALoop�������{
        for (int i = 0; i < l_intAccOpenWaitingParams; i++)
        {
            //1.11.1 get�����J�ݐR���҂��s(int)
            AccOpenWaitingParams l_accOpenWaitingParams = 
                l_judgeWaiting.getAccOpenWaitingParams(i);
            WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
            //1.11.2 �����J�݌����q(String, String)
            try
            {
                l_expAccountOpen = 
                    new WEB3AccOpenExpAccountOpen(
                        l_strInstitutionCode, 
                        l_accOpenWaitingParams.getAccOpenRequestNumber());
            }
            catch(NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B",l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
            
            //1.11.3. (*)�����J�ݐR�����I�u�W�F�N�g�̒�`
            //1.11.4. (*)�v���p�e�B�Z�b�g
            l_accOpenInspectInfos[i] = new WEB3AccOpenInspectInfo();
            l_accOpenInspectInfos[i].branchCode = null;
            l_accOpenInspectInfos[i].accountCode = null;
            l_accOpenInspectInfos[i].requestNumber = l_accOpenWaitingParams.getAccOpenRequestNumber();
            l_accOpenInspectInfos[i].accountNameKana = 
                l_expAccountOpen.getFamilyNameAlt1() + "�@@" + l_expAccountOpen.getGivenNameAlt1();
            l_accOpenInspectInfos[i].bornDate = l_expAccountOpen.getBornDate();
            l_accOpenInspectInfos[i].eraBorn = l_expAccountOpen.getEraBorn();
            l_accOpenInspectInfos[i].telephone = l_expAccountOpen.getTelephone();
            l_accOpenInspectInfos[i].mobileTelephone = l_expAccountOpen.getMobile();
            l_accOpenInspectInfos[i].mailAddress = l_expAccountOpen.getEmailAddress();
            l_accOpenInspectInfos[i].address1 = l_expAccountOpen.getAddressLine1();
            l_accOpenInspectInfos[i].address2 = l_expAccountOpen.getAddressLine2();
            l_accOpenInspectInfos[i].address3 = l_expAccountOpen.getAddressLine3();
            l_accOpenInspectInfos[i].occurredDate = l_accOpenWaitingParams.getCreatedTimestamp();
            l_accOpenInspectInfos[i].reviewCode = l_accOpenWaitingParams.getReviewCode();
            l_accOpenInspectInfos[i].checkDiv = l_accOpenWaitingParams.getCheckDiv();
            l_accOpenInspectInfos[i].checkDate = l_accOpenWaitingParams.getLastUpdatedTimestamp();
            l_accOpenInspectInfos[i].checkerCode = l_accOpenWaitingParams.getCheckerCode();
            l_accOpenInspectInfos[i].duplicateDiv = l_accOpenWaitingParams.getDuplicationDiv();
        }
        
        //1.12. (*)�v���p�e�B�Z�b�g
        WEB3AdminAccOpenInspectConsentConfirmResponse l_response = (WEB3AdminAccOpenInspectConsentConfirmResponse)l_request.createResponse();
        
        l_response.accopenInspectList = l_accOpenInspectInfos;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit���F)<BR>
     * �����J�ݐR�����F������ʕ\���������s���B<BR> 
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�����J�݁i�R���jsubmit���F�v�Q�ƁB<BR>
     * ==========================================================<BR>
     * ��̈ʒu     :�@@1.10.(*) ���F�ρE�۔F�ς̃f�[�^�����݂���ꍇ<BR>
     * �iis�R����() == true�j�A��O���X���[����B<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_02448 <BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݐR�����F�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenInspectConsentCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473C75C025C
     */
    protected WEB3AdminAccOpenInspectConsentCompleteResponse submitConsent(
        WEB3AdminAccOpenInspectConsentCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitConsent(WEB3AdminAccOpenInspectConsentCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate( )
        l_request.validate();
        
        //1.2. getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo(); 
        
        //1.3. validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.JUDGE, true);
        
        //1.4. to�����J�ݐR���҂�( )
        //1.4.1. �����J�ݐR���҂�( )
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        WEB3AccOpenJudgeWaiting l_judgeWaiting = 
            l_infoCreatedService.toAccOpenJudgeWaiting();
        
        //1.5. get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6. select�R���Ώۈꗗ(String, String[], �����J�݃\�[�g�L�[[])
        l_judgeWaiting.selectJudgeObjectList(l_strInstitutionCode, l_request.requestNumber, l_request.sortKeys);
        
        //1.7. get�����J�ݐR���҂��s��( )
        int l_intAccOpenWaitingParams = l_judgeWaiting.getAccOpenWaitingParamsNumber();
        
        //1.8. validate����p�X���[�h(�p�X���[�h : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.9. is�R����( )   
        //1.10. (*) ���F�ρE�۔F�ς̃f�[�^�����݂���ꍇ�iis�R����() == true�j�A��O���X���[����B
        if (l_judgeWaiting.isChecked())
        {
            log.debug("�Ώۂ̃f�[�^�͐R���ςł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02448,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ώۂ̃f�[�^�͐R���ςł��B");
        }
        
        //1.11. get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();
        
        //1.12. (*)�����J�ݐR�����̔z��I�u�W�F�N�g�̒�`
        WEB3AccOpenInspectInfo[] l_accOpenInspectInfos = 
            new WEB3AccOpenInspectInfo[l_intAccOpenWaitingParams];
        
        //1.13. (*)get�����J�ݐR���҂��s��()�̖߂�l�̌������ALoop�������{
        for (int i = 0; i < l_intAccOpenWaitingParams; i++)
        {
            //1.13.1. get�����J�ݐR���҂��s(int)
            AccOpenWaitingParams l_accOpenWaitingParams = 
                l_judgeWaiting.getAccOpenWaitingParams(i);
            WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
            
            //1.13.2. �����J�݌����q(String, String)
            try
            {
                l_expAccountOpen = 
                    new WEB3AccOpenExpAccountOpen(
                        l_strInstitutionCode, 
                        l_accOpenWaitingParams.getAccOpenRequestNumber());
            }
            catch(NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
            
            //1.13.3. (*)�v���p�e�B�Z�b�g
            //�����J�݌����q.���X�R�[�h
            l_accOpenWaitingParams.setBranchCode(l_expAccountOpen.getBranchCode());
            l_accOpenWaitingParams.setAccountCode(l_expAccountOpen.getAccountCode());
            l_accOpenWaitingParams.setCheckDiv(WEB3CheckDivDef.AGREE);
            l_accOpenWaitingParams.setCheckerCode(l_strAdministratorCode);
            l_accOpenWaitingParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            
            //1.13.4. set�����J�ݐR���҂��s(�����J�ݐR���҂�Param, int)
            l_judgeWaiting.setAccOpenWaitingParams(l_accOpenWaitingParams, i);
            
            //1.13.5. create�����J�ݓ`�[(�����J�݌����q)
            WEB3AccOpenVoucherCreatedService l_voucherCreatedService = 
                (WEB3AccOpenVoucherCreatedService)Services.getService(WEB3AccOpenVoucherCreatedService.class);
            l_voucherCreatedService.createAccOpenVoucher(l_expAccountOpen);
            
            //1.13.6. (*)�����J�ݐR�����I�u�W�F�N�g�̒�`
            //1.13.7. (*)�v���p�e�B�Z�b�g
            l_accOpenInspectInfos[i] = new WEB3AccOpenInspectInfo();
            l_accOpenInspectInfos[i].branchCode = null;
            l_accOpenInspectInfos[i].accountCode = null;
            l_accOpenInspectInfos[i].requestNumber = l_accOpenWaitingParams.getAccOpenRequestNumber();
            l_accOpenInspectInfos[i].accountNameKana = 
                l_expAccountOpen.getFamilyNameAlt1() + "�@@" + l_expAccountOpen.getGivenNameAlt1();
            l_accOpenInspectInfos[i].bornDate = l_expAccountOpen.getBornDate();
            l_accOpenInspectInfos[i].eraBorn = l_expAccountOpen.getEraBorn();
            l_accOpenInspectInfos[i].telephone = l_expAccountOpen.getTelephone();
            l_accOpenInspectInfos[i].mobileTelephone = l_expAccountOpen.getMobile();
            l_accOpenInspectInfos[i].mailAddress = l_expAccountOpen.getEmailAddress();
            l_accOpenInspectInfos[i].address1 = l_expAccountOpen.getAddressLine1();
            l_accOpenInspectInfos[i].address2 = l_expAccountOpen.getAddressLine2();
            l_accOpenInspectInfos[i].address3 = l_expAccountOpen.getAddressLine3();
            l_accOpenInspectInfos[i].occurredDate = l_accOpenWaitingParams.getCreatedTimestamp();
            l_accOpenInspectInfos[i].reviewCode = l_accOpenWaitingParams.getReviewCode();
            l_accOpenInspectInfos[i].checkDiv = l_accOpenWaitingParams.getCheckDiv();
            l_accOpenInspectInfos[i].checkDate = l_accOpenWaitingParams.getLastUpdatedTimestamp();
            l_accOpenInspectInfos[i].checkerCode = l_accOpenWaitingParams.getCheckerCode();
            l_accOpenInspectInfos[i].duplicateDiv = l_accOpenWaitingParams.getDuplicationDiv();
        }
        
        //1.14. update�����J�ݐR���҂�( )
        l_judgeWaiting.updateAccOpenWaiting();
        
        //1.15. (*)�v���p�e�B�Z�b�g
        WEB3AdminAccOpenInspectConsentCompleteResponse l_response = (WEB3AdminAccOpenInspectConsentCompleteResponse)l_request.createResponse();
        
        l_response.accopenInspectList = l_accOpenInspectInfos;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�۔F)<BR>
     * �����J�ݐR���۔F�m�F��ʕ\���������s���B<BR> 
     * <BR>
     * �V�[�P���X�}<BR> 
     * �u�����J�݁i�R���jvalidate�۔F�v�Q�ƁB<BR> 
     * ==========================================================<BR>
     * ��̈ʒu     :�@@1.9.(*) ���F�ρE�۔F�ς̃f�[�^�����݂���ꍇ<BR>
     * �iis�R����() == true�j�A��O���X���[����B<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_02448 <BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݐR���۔F�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenInspectDenyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473C76300A6
     */
    protected WEB3AdminAccOpenInspectDenyConfirmResponse validateDeny(
            WEB3AdminAccOpenInspectDenyConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateDeny(WEB3AdminAccOpenInspectDenyConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate( )
        l_request.validate();
        
        //1.2. getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo(); 
        
        //1.3. validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.JUDGE, true);
        
        //1.4. to�����J�ݐR���҂�( )
        //1.4.1. �����J�ݐR���҂�( )
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        WEB3AccOpenJudgeWaiting l_judgeWaiting = 
            l_infoCreatedService.toAccOpenJudgeWaiting();
        
        //1.5. get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6. select�R���Ώۈꗗ(String, String[], �����J�݃\�[�g�L�[[])
        l_judgeWaiting.selectJudgeObjectList(l_strInstitutionCode, l_request.requestNumber, l_request.sortKeys);
        
        //1.7. get�����J�ݐR���҂��s��( )
        int l_intAccOpenWaitingParams = l_judgeWaiting.getAccOpenWaitingParamsNumber();
        
        //1.8. is�R����( )   
        //1.9. (*) ���F�ρE�۔F�ς̃f�[�^�����݂���ꍇ�iis�R����() == true�j�A��O���X���[����B
        if (l_judgeWaiting.isChecked())
        {
            log.debug("�Ώۂ̃f�[�^�͐R���ςł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02448,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ώۂ̃f�[�^�͐R���ςł��B");
        }
        
        //1.10. (*)�����J�ݐR�����̔z��I�u�W�F�N�g�̒�`
        WEB3AccOpenInspectInfo[] l_accOpenInspectInfos = 
            new WEB3AccOpenInspectInfo[l_intAccOpenWaitingParams];
        
        //1.11. (*)get�����J�ݐR���҂��s��()�̖߂�l�̌������ALoop�������{
        for (int i = 0; i < l_intAccOpenWaitingParams; i++)
        {
            //1.11.1 get�����J�ݐR���҂��s(int)
            AccOpenWaitingParams l_accOpenWaitingParams = 
                l_judgeWaiting.getAccOpenWaitingParams(i);
            WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
            
            //1.11.2 �����J�݌����q(�����J�݌����qParams)
            try
            {
                l_expAccountOpen = 
                    new WEB3AccOpenExpAccountOpen(
                        l_strInstitutionCode, 
                        l_accOpenWaitingParams.getAccOpenRequestNumber());
            }
            catch(NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
            
            //1.11.3. (*)�����J�ݐR�����I�u�W�F�N�g�̒�`
            //1.11.4. (*)�v���p�e�B�Z�b�g
            l_accOpenInspectInfos[i] = new WEB3AccOpenInspectInfo();
            l_accOpenInspectInfos[i].branchCode = null;
            l_accOpenInspectInfos[i].accountCode = null;
            l_accOpenInspectInfos[i].requestNumber = l_accOpenWaitingParams.getAccOpenRequestNumber();
            l_accOpenInspectInfos[i].accountNameKana = 
                l_expAccountOpen.getFamilyNameAlt1() + "�@@" + l_expAccountOpen.getGivenNameAlt1();
            l_accOpenInspectInfos[i].bornDate = l_expAccountOpen.getBornDate();
            l_accOpenInspectInfos[i].eraBorn = l_expAccountOpen.getEraBorn();
            l_accOpenInspectInfos[i].telephone = l_expAccountOpen.getTelephone();
            l_accOpenInspectInfos[i].mobileTelephone = l_expAccountOpen.getMobile();
            l_accOpenInspectInfos[i].mailAddress = l_expAccountOpen.getEmailAddress();
            l_accOpenInspectInfos[i].address1 = l_expAccountOpen.getAddressLine1();
            l_accOpenInspectInfos[i].address2 = l_expAccountOpen.getAddressLine2();
            l_accOpenInspectInfos[i].address3 = l_expAccountOpen.getAddressLine3();
            l_accOpenInspectInfos[i].occurredDate = l_accOpenWaitingParams.getCreatedTimestamp();
            l_accOpenInspectInfos[i].reviewCode = l_accOpenWaitingParams.getReviewCode();
            l_accOpenInspectInfos[i].checkDiv = l_accOpenWaitingParams.getCheckDiv();
            l_accOpenInspectInfos[i].checkDate = l_accOpenWaitingParams.getLastUpdatedTimestamp();
            l_accOpenInspectInfos[i].checkerCode = l_accOpenWaitingParams.getCheckerCode();
            l_accOpenInspectInfos[i].duplicateDiv = l_accOpenWaitingParams.getDuplicationDiv();
        }
        
        //1.12. (*)�v���p�e�B�Z�b�g
        WEB3AdminAccOpenInspectDenyConfirmResponse l_response = (WEB3AdminAccOpenInspectDenyConfirmResponse)l_request.createResponse();
        
        l_response.accopenInspectList = l_accOpenInspectInfos;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�۔F)<BR>
     * �����J�ݐR���۔F������ʕ\���������s���B<BR> 
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�����J�݁i�R���jsubmit�۔F�v�Q�ƁB<BR> 
     * ==========================================================<BR>
     * ��̈ʒu     :�@@1.10.(*) ���F�ρE�۔F�ς̃f�[�^�����݂���ꍇ<BR>
     * �iis�R����() == true�j�A��O���X���[����B<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_02448 <BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݐR���۔F�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenInspectDenyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473C76701DF
     */
    protected WEB3AdminAccOpenInspectDenyCompleteResponse submitDeny(
        WEB3AdminAccOpenInspectDenyCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitDeny(WEB3AdminAccOpenInspectDenyCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate( )
        l_request.validate();
        
        //1.2. getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo(); 
        
        //1.3. validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.JUDGE, true);
        
        //1.4. to�����J�ݐR���҂�( )
        //1.4.1. �����J�ݐR���҂�( )
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        WEB3AccOpenJudgeWaiting l_judgeWaiting = 
            l_infoCreatedService.toAccOpenJudgeWaiting();
        
        //1.5. get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6. select�R���Ώۈꗗ(String[], �����J�݃\�[�g�L�[[])
        l_judgeWaiting.selectJudgeObjectList(l_strInstitutionCode, l_request.requestNumber, l_request.sortKeys);
        
        //1.7. get�����J�ݐR���҂��s��( )
        int l_intAccOpenWaitingParams = l_judgeWaiting.getAccOpenWaitingParamsNumber();
        
        //1.8. validate����p�X���[�h(�p�X���[�h : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.9. is�R����( )   
        //1.10. (*) ���F�ρE�۔F�ς̃f�[�^�����݂���ꍇ�iis�R����() == true�j�A��O���X���[����B
        if (l_judgeWaiting.isChecked())
        {
            log.debug("�Ώۂ̃f�[�^�͐R���ςł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02448,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ώۂ̃f�[�^�͐R���ςł��B");
        }
        
        //1.11. get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();
        
        //1.12. (*)�����J�ݐR�����̔z��I�u�W�F�N�g�̒�`
        WEB3AccOpenInspectInfo[] l_accOpenInspectInfos = 
            new WEB3AccOpenInspectInfo[l_intAccOpenWaitingParams];
        
        //1.13. (*)get�����J�ݐR���҂��s��()�̖߂�l�̌������ALoop�������{
        for (int i = 0; i < l_intAccOpenWaitingParams; i++)
        {
            //1.13.1. get�����J�ݐR���҂��s(int)
            AccOpenWaitingParams l_accOpenWaitingParams = 
                l_judgeWaiting.getAccOpenWaitingParams(i);
            WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
            
            //1.13.2. �����J�݌����q(�����J�݌����qParams)
            try
            {
                l_expAccountOpen = 
                    new WEB3AccOpenExpAccountOpen(
                        l_strInstitutionCode, 
                        l_accOpenWaitingParams.getAccOpenRequestNumber());
            }
            catch(NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
            
            //1.13.3. (*)�v���p�e�B�Z�b�g
            //�����J�݌����q.���X�R�[�h
            l_accOpenWaitingParams.setCheckDiv(WEB3CheckDivDef.DISAGREE);
            l_accOpenWaitingParams.setCheckerCode(l_strAdministratorCode);
            l_accOpenWaitingParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            
            //1.13.4. set�����J�ݐR���҂��s(�����J�ݐR���҂�Param, int)
            l_judgeWaiting.setAccOpenWaitingParams(l_accOpenWaitingParams, i);
            
            //1.13.5. (*)�����J�ݐR�����I�u�W�F�N�g�̒�`
            //1.13.6. (*)�v���p�e�B�Z�b�g
            l_accOpenInspectInfos[i] = new WEB3AccOpenInspectInfo();
            l_accOpenInspectInfos[i].branchCode = null;
            l_accOpenInspectInfos[i].accountCode = null;
            l_accOpenInspectInfos[i].requestNumber = l_accOpenWaitingParams.getAccOpenRequestNumber();
            l_accOpenInspectInfos[i].accountNameKana = 
                l_expAccountOpen.getFamilyNameAlt1() + "�@@" + l_expAccountOpen.getGivenNameAlt1();
            l_accOpenInspectInfos[i].bornDate = l_expAccountOpen.getBornDate();
            l_accOpenInspectInfos[i].eraBorn = l_expAccountOpen.getEraBorn();
            l_accOpenInspectInfos[i].telephone = l_expAccountOpen.getTelephone();
            l_accOpenInspectInfos[i].mobileTelephone = l_expAccountOpen.getMobile();
            l_accOpenInspectInfos[i].mailAddress = l_expAccountOpen.getEmailAddress();
            l_accOpenInspectInfos[i].address1 = l_expAccountOpen.getAddressLine1();
            l_accOpenInspectInfos[i].address2 = l_expAccountOpen.getAddressLine2();
            l_accOpenInspectInfos[i].address3 = l_expAccountOpen.getAddressLine3();
            l_accOpenInspectInfos[i].occurredDate = l_accOpenWaitingParams.getCreatedTimestamp();
            l_accOpenInspectInfos[i].reviewCode = l_accOpenWaitingParams.getReviewCode();
            l_accOpenInspectInfos[i].checkDiv = l_accOpenWaitingParams.getCheckDiv();
            l_accOpenInspectInfos[i].checkDate = l_accOpenWaitingParams.getLastUpdatedTimestamp();
            l_accOpenInspectInfos[i].checkerCode = l_accOpenWaitingParams.getCheckerCode();
            l_accOpenInspectInfos[i].duplicateDiv = l_accOpenWaitingParams.getDuplicationDiv();
        }
        
        //1.14. update�����J�ݐR���҂�( )
        l_judgeWaiting.updateAccOpenWaiting();
        
        //1.15. (*)�v���p�e�B�Z�b�g
        WEB3AdminAccOpenInspectDenyCompleteResponse l_response = (WEB3AdminAccOpenInspectDenyCompleteResponse)l_request.createResponse();
        
        l_response.accopenInspectList = l_accOpenInspectInfos;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
}
@
