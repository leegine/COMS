head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�XImpl(WEB3AdminAccOpenStateInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 �s�p (���u) �V�K�쐬
                   2006/06/09 ���� (���u) �d�l�ύX ���f��050�A053�A059
                   2006/08/15 �����F(���u) �d�l�ύX ���f��090
                   2006/08/16 �����F(���u) �d�l�ύX ���f��093
                   2006/09/11 �ęԍg(���u) �d�l�ύX ���f��101
                   2006/09/19 �ęԍg (���u) �d�l�ύX ���f��098
                   2006/09/27 �ęԍg (���u) �d�l�ύX ���f��106
                   2007/01/12 ���� (SCS) �i���f���j120�C��
Revesion History : 2009/08/13 �đo�g (���u) �d�l�ύX ���f�� No.173�A174�A184�A186
Revesion History : 2010/02/10 ���g (���u) �d�l�ύX���f�� No.217
Revesion History : 2010/02/21 �����F (���u) �d�l�ύX���f�� No.219
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AccOpenInvalidValues;
import webbroker3.accountopen.WEB3AccOpenJudgeWaiting;
import webbroker3.accountopen.WEB3AccOpenVoucherCreatedStatus;
import webbroker3.accountopen.data.AccOpenWaitingParams;
import webbroker3.accountopen.data.AccOpenWaitingRow;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.define.WEB3AccOpenAccountCodeAutoFlagDef;
import webbroker3.accountopen.define.WEB3AccOpenAccountOpenDivDef;
import webbroker3.accountopen.define.WEB3AccOpenExclusiveAccountWarningDivDef;
import webbroker3.accountopen.define.WEB3AccOpenTaxTypeDivDef;
import webbroker3.accountopen.define.WEB3AccOpenUpdateItemDef;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenKeyItemDef;
import webbroker3.accountopen.define.WEB3AccountOpenStatusDivDef;
import webbroker3.accountopen.message.WEB3AccOpenApplyInfo;
import webbroker3.accountopen.message.WEB3AccOpenChangeRequest;
import webbroker3.accountopen.message.WEB3AccOpenChangeResponse;
import webbroker3.accountopen.message.WEB3AccOpenExclusiveAccountInfo;
import webbroker3.accountopen.message.WEB3AccOpenInvalidItemInfo;
import webbroker3.accountopen.message.WEB3AccOpenSortKey;
import webbroker3.accountopen.message.WEB3AccOpenStateUnit;
import webbroker3.accountopen.message.WEB3AccOpenVoucherInfo;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenAccountCodeService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInfoCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenStateInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3CheckAlartUpdateDef;
import webbroker3.common.define.WEB3ExclusiveUseAccountStatusDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3SpecialAccDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TaxTypeDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3ValidateTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.ExclusiveUseAccountCondRow;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�XImpl)<BR>
 * �Ǘ��Ҍ����J�ݏ󋵖⍇���T�[�r�X�����N���X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */

public class WEB3AdminAccOpenStateInquiryServiceImpl implements WEB3AdminAccOpenStateInquiryService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenStateInquiryServiceImpl.class);

    /**
     * @@roseuid 41B45E7102AF
     */
    public WEB3AdminAccOpenStateInquiryServiceImpl() 
    {
     
    }
    
    /**
     * (���X�v���t�@@�����X)<BR>
     * ���X�v���t�@@�����X�l<BR> 
     * <BR>
     * <BR>
     * [�ݒ�l] <BR>
     * 0�F�`�F�b�N�s�v-�A���[�g�\����-�R���҂�UPDATE�� <BR>
     * 1�F�`�F�b�N���s-�A���[�g�\���L-�R���҂�UPDATE�� <BR>
     * 2�F�`�F�b�N���s-�A���[�g�\����-�R���҂�UPDATE�L <BR>
     * 3�F�`�F�b�N���s-�A���[�g�\���L-�R���҂�UPDATE�L <BR>
     */
    private int branchPreferences = 0;
    
    /**
     * �����J�ݏ󋵖⍇�����������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݏ󋵖⍇�����̓��N�G�X�g�̏ꍇ<BR> 
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���N�G�X�g�̏ꍇ <BR>
     * �@@�|get�����J�ݏ󋵈ꗗ()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݏ󋵖⍇���ڍ׃��N�G�X�g�̏ꍇ <BR>
     * �@@�|get�����J�ݏ󋵏ڍ�()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\���X�V�m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�\���X�V()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\���X�V�������N�G�X�g�̏ꍇ<BR> 
     * �@@�|submit�\���X�V()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݓ`�[�쐬�m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�`�[�쐬()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݓ`�[�쐬�������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�`�[�쐬()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݓ`�[����m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�`�[���()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݓ`�[����������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�`�[���()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐؑփ��N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�ؑ�()���R�[������<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41943B38039C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";

        log.entering(STR_METHOD_NAME);
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݏ󋵖⍇�����̓��N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminAccOpenStateInquiryInputRequest)
        {
            //get���͉��()���R�[������B
            log.info("WEB3AdminAccOpenStateInquiryInputRequest");
            WEB3AdminAccOpenStateInquiryInputResponse l_response = getInputScreen(
                (WEB3AdminAccOpenStateInquiryInputRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenStateInquiryListRequest)
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���N�G�X�g�̏ꍇ
        {
            //get�����J�ݏ󋵈ꗗ()���R�[������B
            log.info("WEB3AdminAccOpenStateInquiryListRequest");
            WEB3AdminAccOpenStateInquiryListResponse l_response =  getAccOpenStatusList(
                (WEB3AdminAccOpenStateInquiryListRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenStateInquiryDetailRequest)
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݏ󋵖⍇���ڍ׃��N�G�X�g�̏ꍇ
        {
            //get�����J�ݏ󋵏ڍ�()���R�[������B
            log.info("WEB3AdminAccOpenStateInquiryDetailRequest");
            WEB3AdminAccOpenStateInquiryDetailResponse l_response =  getAccOpenStatusDetail(
                (WEB3AdminAccOpenStateInquiryDetailRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenApplyUpdateConfirmRequest)
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\���X�V�m�F���N�G�X�g�̏ꍇ 
        {
            //validate�\���X�V()���R�[������B
            log.info("WEB3AdminAccOpenApplyUpdateConfirmRequest");
            WEB3AdminAccOpenApplyUpdateConfirmResponse l_response =  validateRegistUpdated(
                (WEB3AdminAccOpenApplyUpdateConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenApplyUpdateCompleteRequest)
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\���X�V�������N�G�X�g�̏ꍇ 
        {
            //submit�\���X�V()���R�[������B
            log.info("WEB3AdminAccOpenApplyUpdateCompleteRequest");
            WEB3AdminAccOpenApplyUpdateCompleteResponse l_response =  submitRegistUpdated(
                (WEB3AdminAccOpenApplyUpdateCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenVoucherMakeConfirmRequest)
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݓ`�[�쐬�m�F���N�G�X�g�̏ꍇ  
        {
            //validate�`�[�쐬()���R�[������B
            log.info("WEB3AdminAccOpenVoucherMakeConfirmRequest");
            WEB3AdminAccOpenVoucherMakeConfirmResponse l_response =  validateVoucherCreated(
                (WEB3AdminAccOpenVoucherMakeConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenVoucherMakeCompleteRequest)
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݓ`�[�쐬�������N�G�X�g�̏ꍇ 
        {
            //submit�`�[�쐬()���R�[������B
            log.info("WEB3AdminAccOpenVoucherMakeCompleteRequest");
            WEB3AdminAccOpenVoucherMakeCompleteResponse l_response =  submitVoucherCreated(
                (WEB3AdminAccOpenVoucherMakeCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenVoucherCancelConfirmRequest)
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݓ`�[����m�F���N�G�X�g�̏ꍇ 
        {
            //validate�`�[���()���R�[������B
            log.info("WEB3AdminAccOpenVoucherCancelConfirmRequest");
            WEB3AdminAccOpenVoucherCancelConfirmResponse l_response =  validateVoucherCanceled(
                (WEB3AdminAccOpenVoucherCancelConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenVoucherCancelCompleteRequest)
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݓ`�[����������N�G�X�g�̏ꍇ 
        {
            //submit�`�[���()���R�[������
            log.info("WEB3AdminAccOpenVoucherCancelCompleteRequest");
            WEB3AdminAccOpenVoucherCancelCompleteResponse l_response =  submitVoucherCanceled(
                (WEB3AdminAccOpenVoucherCancelCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AccOpenChangeRequest)
        {
            //�����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐؑփ��N�G�X�g�̏ꍇ
            log.info("WEB3AccOpenChangeRequest");
            WEB3AccOpenChangeResponse l_response = submitChange(
                (WEB3AccOpenChangeRequest)l_request);

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
     * (get���͉��)<BR>
     * �����J�ݏ󋵖⍇�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�󋵖⍇���jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݏ󋵖⍇�����̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4194378303BB
     */
    protected WEB3AdminAccOpenStateInquiryInputResponse getInputScreen(WEB3AdminAccOpenStateInquiryInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccOpenStateInquiryInputRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2:validate����(�@@�\�J�e�S���R�[�h�i=�����J�݁j : String, is�X�V�i=false�j : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, false);
        
        //1.3:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.4:get��p�U���������s�R�[�h(String)
        String[] l_strExclusiveAccountBankCodes = this.getExclusiveAccountFinancialInstitutionCode(l_strInstitutionCode);
        
        //1.5:��p�U������������i�[����ArrayList�I�u�W�F�N�g�𐶐�
        ArrayList l_arrayList = new ArrayList();
        
        if(l_strExclusiveAccountBankCodes != null)
        {
            for(int i = 0; i < l_strExclusiveAccountBankCodes.length; i++)
            {
                String l_strExclusiveAccountBankCode = l_strExclusiveAccountBankCodes[i];
                //1.6.1:get��p�U��������c�����v(String, String)
                int l_inttotalNumber = 
                    this.getExclusiveAccountTotalNumber(l_strInstitutionCode, l_strExclusiveAccountBankCode);
                
                //1.6.2:get��p�U��������x���敪(String, String, int)
                String l_strExclusiveAccountWarningDiv = 
                    this.getExclusiveAccountWarningDiv(
                        l_strInstitutionCode, 
                        l_strExclusiveAccountBankCode,
                        l_inttotalNumber);
                
                //1.6.3create��p�U����������(String, int, String)
                WEB3AccOpenExclusiveAccountInfo l_ExculsiveAccountInfo = 
                    this.createExclusiveAccountInfo(
                        l_strExclusiveAccountBankCode, 
                        l_inttotalNumber, 
                        l_strExclusiveAccountWarningDiv);
                l_arrayList.add(l_ExculsiveAccountInfo);
            }
        }

        //1.7:createResponse( )
        WEB3AdminAccOpenStateInquiryInputResponse l_response = 
            (WEB3AdminAccOpenStateInquiryInputResponse)l_request.createResponse();
            
        //1.8:(*) �v���p�e�B�Z�b�g
        WEB3AccOpenExclusiveAccountInfo[] l_exclusiveAccountInfo = 
            new WEB3AccOpenExclusiveAccountInfo[l_arrayList.size()];
        l_arrayList.toArray(l_exclusiveAccountInfo);
        l_response.exclusiveAccountInfoList = l_exclusiveAccountInfo;

        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get�����J�ݏ󋵈ꗗ)<BR>
     * �����J�ݏ󋵈ꗗ�\���������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�󋵖⍇���jget�����J�ݏ󋵈ꗗ�v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListResponse
     * @@roseuid 4194378303DA
     */
    protected WEB3AdminAccOpenStateInquiryListResponse getAccOpenStatusList(WEB3AdminAccOpenStateInquiryListRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getAccOpenStatusList(WEB3AdminAccOpenStateInquiryListRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, false);
        
        //1.4:validate���X����(String[])
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.5:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6:create��������������(�Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���N�G�X�g)
        String l_strQueryString = createQueryString(l_request);
        
        log.debug("l_strQueryString:" + l_strQueryString);
        
        //1.7:create���������f�[�^�R���e�i(�Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���N�G�X�g, String)
        String[] l_strQueryContainers = createQueryContainer(l_request, l_strInstitutionCode);
        log.debug("l_strQueryContainers:" + l_strQueryContainers.toString());
        
        //1.8:create�\�[�g����(�����J�݃\�[�g�L�[[])
        String l_strSortCond = createSortCond(l_request.sortKeys);
        log.debug("l_strQueryContainers:" + l_strSortCond.toString());

        //1.9:get�����J�݌����q�`�[(int, int, String, String[], String, Date, Date, String)
        ListPage l_lisExpAccountOpens = WEB3AccOpenExpAccountOpen.getExpAccountOpenVoucher(
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize),
            l_strQueryString, 
            l_strQueryContainers,
            l_strSortCond,
            l_request.sonarSendDateFrom,
            l_request.sonarSendDateTo,
            l_request.accountOpenStateDiv);


        int l_intLisReturnCnt = l_lisExpAccountOpens.size();
        WEB3AccOpenStateUnit[] l_accOpenStateUnits = new WEB3AccOpenStateUnit[l_intLisReturnCnt];
        
        for (int i = 0; i < l_intLisReturnCnt; i++)
        {
            log.debug("loop:" + l_intLisReturnCnt);
                
            //1.10.1:�����J�ݏ�( )
            l_accOpenStateUnits[i] = new WEB3AccOpenStateUnit();
            WEB3AccOpenExpAccountOpen l_expAccountOpen =
                new WEB3AccOpenExpAccountOpen((ExpAccountOpenParams)l_lisExpAccountOpens.get(i));

            //1.10.2:get�`�[�X�e�[�^�X( )
            WEB3AccOpenVoucherCreatedStatus[] l_voucherCreatedStatus = 
                l_expAccountOpen.getVoucherStatus();
                    
            //1.10.3: to�`�[�쐬���(�����J�ݓ`�[�쐬�X�e�[�^�X[])
            WEB3AccOpenInfoCreatedService l_service = (WEB3AccOpenInfoCreatedService)
                Services.getService(WEB3AccOpenInfoCreatedService.class);
                
            WEB3AccOpenVoucherInfo l_voucherInfo = l_service.toAccOpenVoucherInfo(l_voucherCreatedStatus);
                
            //1.10.4:get�����J�ݏ󋵐R���敪( )
            String l_strAccountOpenStatusDiv = l_expAccountOpen.getAccountOpenStatusCheckDiv();
                
            //1.10.5:is����\( )
            boolean l_blnCanceledPossible = l_expAccountOpen.isCanceledPossible();
                
            //1.10.6:getDataSourceObject( )
            ExpAccountOpenRow l_row = (ExpAccountOpenRow)l_expAccountOpen.getDataSourceObject();
                
            //1.10.7:�v���p�e�B�Z�b�g
            //���ʃR�[�h:  �����J�݌����qList[index].�����J�݌����q�s.���ʃR�[�h
            l_accOpenStateUnits[i].requestNumber = l_row.getAccOpenRequestNumber();
                
            //���X�R�[�h�F�@@�����J�݌����qList[index].�����J�݌����q�s.���X�R�[�h
            l_accOpenStateUnits[i].branchCode = l_row.getBranchCode();
                
            //�ڋq�R�[�h�F�@@�����J�݌����qList[index].�����J�݌����q�s.�����R�[�h
            l_accOpenStateUnits[i].accountCode = l_row.getAccountCode();
                
            //���͋敪�F�@@�����J�݌����qList[index].�����J�݌����q�s.���͋敪
            l_accOpenStateUnits[i].inputDiv = l_row.getOrderDiv();

            //�쐬�҃R�[�h�F  �����J�݌����qList[index].�����J�݌����q�s.�쐬�҃R�[�h
            l_accOpenStateUnits[i].creatorCode = l_row.getCreator();
                
            //�����������F�@@�����J�݌����qList[index].�����J�݌����q�s.������������
            l_accOpenStateUnits[i].infoClaimDate = l_row.getInfomationClaimDatetime();
                
            //�����J�ݓ��F�@@�����J�݌����qList[index].�����J�݌����q�s.�����o�^��
            l_accOpenStateUnits[i].accountOpenDate = l_row.getAccountOpenDate();

            //�폜�t���O�F�@@�����J�݌����qList[index].�����J�݌����q�s.�폜�t���O
            if (l_row.getDeleteFlag() != null)
            {
                l_accOpenStateUnits[i].deleteFlag = l_row.getDeleteFlag().intValue() + "";
            }

            //�폜�����F�@@�����J�݌����qList[index].�����J�݌����q�s.�폜����
            l_accOpenStateUnits[i].deleteDate = l_row.getDeleteTimestamp();

            //����t���O�F�@@�����J�݌����qList[index].�����J�݌����q�s.����t���O
            l_accOpenStateUnits[i].printFlag = l_row.getPrintFlag();

            //��̃t���O�F�@@�����J�݌����qList[index].�����J�݌����q�s.��̃t���O
            if (l_row.getReceiptFlag() != null)
            {
                l_accOpenStateUnits[i].receiveFlag = l_row.getReceiptFlag().intValue() + "";
            }

            //��������敪�F�@@��������敪�i���j
            //�����J�݌����qList[index].�����J�݌����q�s.��������敪=
            //  �u0�F��ʌ����v�̏ꍇ�A�u0�F��ʌ����v
            if (WEB3TaxTypeDivDef.NORMAL.equals(l_row.getSpecialAcc()))
            {
                l_accOpenStateUnits[i].taxTypeDiv = WEB3AccOpenTaxTypeDivDef.NORMAL;
            }
            //�����J�݌����qList[index].�����J�݌����q�s.��������敪=
            //  �u1�F��������i���򒥎��Ȃ��j�v�̏ꍇ�A�u1�F��������v
            //�����J�݌����qList[index].�����J�݌����q�s.��������敪=
            //  �u2�F��������i���򒥎�����j�v�̏ꍇ�A�u1�F����
            if (WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE.equals(l_row.getSpecialAcc())
                || WEB3TaxTypeDivDef.SPECIAL_SOURCE.equals(l_row.getSpecialAcc()))
            {
                l_accOpenStateUnits[i].taxTypeDiv = WEB3AccOpenTaxTypeDivDef.SPECIAL;
            }

            //�O���l�t���O�F�@@�����J�݌����qList[index].�����J�݌����q�s.�O���l�t���O
            if (l_row.getForeignFlag() != null)
            {
                l_accOpenStateUnits[i].foreignerFlag = l_row.getForeignFlag().intValue() + "";
            }

            //�ڋq���i�J�i�j�F�@@�����J�݌����qList[index].�����J�݌����q�s.�ڋq���i�J�i�j
            l_accOpenStateUnits[i].accountFamilyNameKana = l_row.getFamilyNameAlt1();
                
            //�ڋq���i�J�i�j�F�@@�����J�݌����qList[index].�����J�݌����q�s.�ڋq���i�J�i�j
            l_accOpenStateUnits[i].accountNameKana = l_row.getGivenNameAlt1();
   
            //�����J�ݏ󋵁F�@@get�����J�ݏ󋵐R���敪()
            l_accOpenStateUnits[i].accountOpenStateDiv = l_strAccountOpenStatusDiv;
            
            //��p�U��������ԍ�:  �����J�݌����qList[index].�����J�݌����q�s.��p�U��������ԍ�
            l_accOpenStateUnits[i].exclusiveAccountCode = l_row.getExclusiveUseAccountNo();
                
            //����\�t���O�F�@@is����\()
            l_accOpenStateUnits[i].cancelFlag = l_blnCanceledPossible;
                
            //�`�[�쐬���F�@@to�`�[�쐬���()
            l_accOpenStateUnits[i].voucherInfo = l_voucherInfo;

            //�����ғo�^�敪�F�����ғo�^�敪
            if (WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(l_row.getAccountDiv()))
            {
                l_accOpenStateUnits[i].insiderDiv = l_row.getInsiderFlag().intValue() + "";
            }
            else
            {
                l_accOpenStateUnits[i].insiderDiv = l_row.getInsiderVoucherDiv();
            }
        }        
        
        //1.11:createResponse( )
        WEB3AdminAccOpenStateInquiryListResponse l_response = 
            (WEB3AdminAccOpenStateInquiryListResponse)l_request.createResponse();
        
        //1.12:�v���p�e�B�Z�b�g
        //�����J�ݏ󋵈ꗗ�F�@@�������������J�ݏ󋵃I�u�W�F�N�g�̔z��B
        l_response.accountOpenStateList = l_accOpenStateUnits;
        
        //�����R�[�h���F�@@get�����J�݌����q�`�[()�̖߂�l.totalSize()
        l_response.totalRecords = Integer.toString(l_lisExpAccountOpens.totalSize());

        //���y�[�W���F�@@get�����J�݌����q�`�[()�̖߂�l.totalPages()
        //���v�Z���ʂ͏����_�ȉ�1�ʂ�؂�グ�������l�B
        l_response.totalPages = Integer.toString(l_lisExpAccountOpens.totalPages());
        
        //�\���y�[�W�ԍ��F�@@get�����J�݌����q�`�[()�̖߂�l.pageNumber() + 1
        //���v�Z���ʂ͏����_�ȉ�1�ʂ�؂�グ�������l�B
        //pageNumber��0����n�܂�ׁA�\���y�[�W�ԍ���ϊ�����B
        l_response.pageIndex = Integer.toString(l_lisExpAccountOpens.pageNumber() + 1);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get�����J�ݏ󋵏ڍ�)<BR>
     * �����J�ݏ󋵏ڍו\���������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�󋵖⍇���jget�����J�ݏ󋵏ڍׁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݏ󋵖⍇���ڍ׃��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailResponse
     * @@roseuid 4194386300CD
     */
    protected WEB3AdminAccOpenStateInquiryDetailResponse getAccOpenStatusDetail(WEB3AdminAccOpenStateInquiryDetailRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccOpenStatusDetail(WEB3AdminAccOpenStateInquiryDetailRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate����(�@@�\�J�e�S���R�[�h�i=�����J�݁j : String, is�X�V�i=false�j : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, false);
        
        //1.4:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
        
        //�����J�݌����q(�،���ЃR�[�h : String, ���X�R�[�h : String, ���ʃR�[�h : String, �����R�[�h : String)
        try
        {
            l_expAccountOpen = new WEB3AccOpenExpAccountOpen(
                l_strInstitutionCode, l_request.branchCode, l_request.requestNumber, l_request.accountCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                " �����J�݌����q��null�ł���B" + l_expAccountOpen);
        }

        //1.7:get���X�R�[�h( )
        String l_strBranchCode = l_expAccountOpen.getBranchCode();
        
        //1.8:validate���X����(String[])
        l_admin.validateBranchPermission(l_strBranchCode);
        
        //1.9:get�ύX�s���ڈꗗ(�����J�݌����q)
        WEB3AccOpenVoucherCreatedService l_voucherCreatedService = (WEB3AccOpenVoucherCreatedService)
            Services.getService(WEB3AccOpenVoucherCreatedService.class);
            
        String[] l_strChnagedImpossibleItemListBefores = 
            l_voucherCreatedService.getChangedImpossibleItemList(l_expAccountOpen);
        
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        
        //1.10:get�ύX�s���ڈꗗ()�߂�l�̊e�v�f����LOOP����
        int l_intChnagedImpossibleItemCnt = l_strChnagedImpossibleItemListBefores.length;
        
        log.debug("l_intChnagedImpossibleItemCnt:" + l_intChnagedImpossibleItemCnt);
        
        Hashtable l_chnagedImpossibleItemList = new Hashtable();
        
        for (int i = 0; i < l_intChnagedImpossibleItemCnt; i++)
        {
            log.debug("loop:" + i);
            
            //1.10.1:to���b�Z�[�W���ږ�(String)            
            String l_str = 
                l_infoCreatedService.toMessageItemName(l_strChnagedImpossibleItemListBefores[i]);
            if (!"".equals(l_str))
            {
                l_chnagedImpossibleItemList.put(l_str, l_str);
            }
                
            log.debug("l_strChnagedImpossibleItemListAfters[" + i + "]:" + l_str);
            log.debug("l_strChnagedImpossibleItemListBefores[" + i + "]:" + l_strChnagedImpossibleItemListBefores[i]);
        }
        String[] l_strChnagedImpossibleItemListAfters = new String[l_chnagedImpossibleItemList.size()];
        l_chnagedImpossibleItemList.values().toArray(l_strChnagedImpossibleItemListAfters);
        
        //1.11: is�`�[�쐬�\( )
        boolean l_blnIsVoucherCreatedPossible = l_expAccountOpen.isVoucherCreatedPossible();
        
        //1.12: is�X�V�\( )
        boolean l_blnIsUpdatedPossible = l_expAccountOpen.isUpdatedPossible();
        
        //1.13:get���ʃR�[�h( )
        String l_strRequestNumber = l_expAccountOpen.getRequestNumber();
        
        String l_strNote1 = null;
        String l_strNote2 = null;
        WEB3AccOpenInvalidItemInfo[] l_invalidItemInfos = null;
        
        try
        {
            //1.14:�����J�ݕs��(String, String)
            WEB3AccOpenInvalidValues l_invalidValues = 
                new WEB3AccOpenInvalidValues(l_strInstitutionCode, l_strRequestNumber);//NotFoundException
        
            //1.15:�����J�ݕs���������ł����ꍇ�̂ݏ������{
            //1.15.1:to�s�����ڏ��(�����J�ݕs��)
            l_invalidItemInfos = l_infoCreatedService.toAccOpenInvalidItemInfo(l_invalidValues);
            
            //1.15.2:get���l�P( )            
            l_strNote1 = l_invalidValues.getNote1();
            
            //1.15.2:get���l�Q( )
            l_strNote2 = l_invalidValues.getNote2();
        }
        catch (NotFoundException l_ex)
        {
            log.debug(" �����J�ݕs����null�ł���B");
        }

        //1.16:get�`�[�X�e�[�^�X( )
        WEB3AccOpenVoucherCreatedStatus[] l_voucherCreatedStatus = l_expAccountOpen.getVoucherStatus();
        
        //1.17:to�`�[�쐬���(�����J�ݓ`�[�쐬�X�e�[�^�X[])
        WEB3AccOpenVoucherInfo l_voucherInfo = 
            l_infoCreatedService.toAccOpenVoucherInfo(l_voucherCreatedStatus);
        
        //1.18:to�����J�ݐ\�����(�����J�݌����q)
        WEB3AccOpenApplyInfo l_applyInfo = 
            l_infoCreatedService.toAccOpenApplyInfo(l_expAccountOpen);
        
        //1.19:createResponse( )
        WEB3AdminAccOpenStateInquiryDetailResponse l_response = 
            (WEB3AdminAccOpenStateInquiryDetailResponse)l_request.createResponse();
            
        //1.20:�v���p�e�B�Z�b�g
        //�ύX�s���ڈꗗ�F�@@get�ύX�s���ڈꗗ()�̖߂�l��to���b�Z�[�W���ږ�()�ɂĕϊ�����������̔z��
        l_response.changeUnableItemList = l_strChnagedImpossibleItemListAfters;
        
        //�X�V�\�t���O�F�@@is�X�V�\()
        l_response.changeFlag = l_blnIsUpdatedPossible;
        
        //�`�[�쐬�\�t���O�F�@@is�`�[�쐬�\()
        l_response.voucherFlag = l_blnIsVoucherCreatedPossible;
    
        //���l�P�F�@@get���l�P()
        l_response.bikou1 = l_strNote1;
        
        //���l�Q�F�@@get���l�Q()
        l_response.bikou2 = l_strNote2;
        
        //�`�[�쐬���F�@@to�`�[�쐬���()
        l_response.voucherInfo = l_voucherInfo;
        
        //�����J�ݐ\�����F�@@to�����J�ݐ\�����()
        l_response.accoutOpenApplyInfo = l_applyInfo;
        
        //�s�����ڏ��ꗗ�F�@@to�s�����ڏ��()
        l_response.invalidItemInfo = l_invalidItemInfos;
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate�\���X�V)<BR>
     * �����J�ݐ\���X�V�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�󋵖⍇���jvalidate�\���X�V�v�Q�ƁB <BR>
     * <BR>
     * =============================================== <BR>
     *          �V�[�P���X�} : �Ǘ��ҁE�����J�ݏ󋵖⍇�� / �����J�݁i�󋵖⍇���jvalidate�\���X�V <BR>
     *          ��̈ʒu     : 1.9 �X�V�s�̏ꍇ�iis�X�V�\() == false�j�A��O���X���[����B <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01315 <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     *          �V�[�P���X�} : �Ǘ��ҁE�����J�ݏ󋵖⍇�� / �����J�݁i�󋵖⍇���jvalidate�\���X�V <BR>
     *          ��̈ʒu     : �폜�ς݂̏ꍇ�iis�폜�ς�() == true�j�A��O���X���[����B<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_03178<BR>
     * =============================================== <BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݐ\���X�V�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmResponse
     * @@roseuid 419438A7015A
     */
    protected WEB3AdminAccOpenApplyUpdateConfirmResponse validateRegistUpdated(WEB3AdminAccOpenApplyUpdateConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRegistUpdated(WEB3AdminAccOpenApplyUpdateConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4:validate���X����(String[])
        l_admin.validateBranchPermission(l_request.accoutOpenApplyInfo.branchCode);
        
        //1.5:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.6:validate�X�e�[�^�X�ύX(�`�[�쐬���, String, String)
        validateStatusUpdated(l_request.voucherInfo, 
            l_strInstitutionCode,
            l_request.accoutOpenApplyInfo.requestNumber);
        
        //1.7:to�����J�݌����q(�����J�ݐ\�����)
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = 
            l_infoCreatedService.toAccOpenExpAccountOpen(l_request.accoutOpenApplyInfo);

        //1.8:validate���X�R�[�h����(String)
        validateBranchCode(
                l_expAccountOpen.getInstitutionCode(),
                l_expAccountOpen.getBranchCode());

        //is�폜�ς�( )
        boolean l_blnIsDeleted = l_expAccountOpen.isDeleted();

        //�폜�ς݂̏ꍇ�iis�폜�ς�() == true�j�A��O���X���[����B
        if (l_blnIsDeleted)
        {
            log.debug("�����J�݌����q���폜�ςł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�݌����q���폜�ςł��B" );
        }

        //1.9:is�X�V�\( )
        boolean l_blnIsUpdatePossible = l_expAccountOpen.isUpdatedPossible();
        
        //1.10:�X�V�s�̏ꍇ�iis�X�V�\() == false�j�A��O���X���[����B
        if (!l_blnIsUpdatePossible)
        {
            log.debug("1.9:�X�V�s�̏ꍇ");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01315,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����J�݌����q���X�V�s�ł��B" + l_blnIsUpdatePossible);
        }
        
        log.debug("1.9:�X�V�̏ꍇ");
        
        //1.11:validate�����J�ݐ\�����(String,String)
        l_expAccountOpen.validateAccountOpenRegistInfo(
            WEB3ValidateTypeDef.ADMINISTRATOR_REGIST_UPDATE,
            WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);
            
        log.debug("1.10:validate�����J�ݐ\�����(String) over!");
            
        //1.12:validate�ڋq�R�[�h( )
        l_expAccountOpen.validateAccountCode();
        
        ArrayList l_lisErrorList = new ArrayList();
        
        //1.13:validate���Z�@@��( )
        l_expAccountOpen.validateFinInstitution();
        
        //1.14:validate���X�ݒ�ʃ`�F�b�N( )
        Collection l_collection =  validateBranchSetCheck(l_expAccountOpen);
        
        //1.15:(*)validate���X�ݒ�ʃ`�F�b�N�x�����b�Z�[�W�R�[�h���ԋp���ꂽ�ꍇ
        //�ivalidate���X�ݒ�ʃ`�F�b�N().size()>0�j�A�������{
        if (l_collection != null || l_collection.size() > 0)
        {
            //1.14.1: addAll(arg0�FCollection)
            l_lisErrorList.addAll(l_collection);
        }
      
        //1.16.1:toArray( )
        String[] l_strErrors = new String[l_lisErrorList.size()];
        l_lisErrorList.toArray(l_strErrors);
        
        //1.17: createResponse( )
        WEB3AdminAccOpenApplyUpdateConfirmResponse l_response = 
            (WEB3AdminAccOpenApplyUpdateConfirmResponse)l_request.createResponse();
            
        //1.18:�v���p�e�B�Z�b�g
        l_response.warningMessageList = l_strErrors;
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit�\���X�V)<BR>
     * �����J�ݐ\���X�V�����������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�󋵖⍇���jsubmit�\���X�V�v�Q�ƁB <BR>
     * <BR>
     * =============================================== <BR>
     *          �V�[�P���X�} :  �Ǘ��ҁE�����J�ݏ󋵖⍇�� / �����J�݁i�󋵖⍇���jsubmit�\���X�V <BR>
     *          ��̈ʒu     : 1.10 �X�V�s�̏ꍇ�iis�X�V�\() == false�j�A��O���X���[����B <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01315 <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     *          �V�[�P���X�} :  �Ǘ��ҁE�����J�ݏ󋵖⍇�� / �����J�݁i�󋵖⍇���jsubmit�\���X�V <BR>
     *          ��̈ʒu     : �폜�ς݂̏ꍇ�iis�폜�ς�() == true�j�A��O���X���[����B<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_03178<BR>
     * =============================================== <BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݐ\���X�V�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteResponse
     * @@roseuid 4194392B01F6
     */
    protected WEB3AdminAccOpenApplyUpdateCompleteResponse submitRegistUpdated(WEB3AdminAccOpenApplyUpdateCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitRegistUpdated(WEB3AdminAccOpenApplyUpdateCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4:validate���X����(String[])
        l_admin.validateBranchPermission(l_request.accoutOpenApplyInfo.branchCode);
        
        //1.5: validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.6:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7:validate�X�e�[�^�X�ύX(�`�[�쐬���, String, String)
        validateStatusUpdated(l_request.voucherInfo, 
            l_strInstitutionCode,
            l_request.accoutOpenApplyInfo.requestNumber);
        
        //1.8:to�����J�݌����q(�����J�ݐ\�����)
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = 
            l_infoCreatedService.toAccOpenExpAccountOpen(l_request.accoutOpenApplyInfo);

        //1.9:validate���X�R�[�h����(String)
        validateBranchCode(
                l_expAccountOpen.getInstitutionCode(),
                l_expAccountOpen.getBranchCode());

        //is�폜�ς�( )
        boolean l_blnIsDeleted = l_expAccountOpen.isDeleted();

        //�폜�ς݂̏ꍇ�iis�폜�ς�() == true�j�A��O���X���[����B
        if (l_blnIsDeleted)
        {
            log.debug("�����J�݌����q���폜�ςł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�݌����q���폜�ςł��B" );
        }

        //1.10:is�X�V�\( )
        boolean l_blnIsUpdatePossible = l_expAccountOpen.isUpdatedPossible();
        
        //1.11:�X�V�s�̏ꍇ�iis�X�V�\() == false�j�A��O���X���[����B
        if (!l_blnIsUpdatePossible)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01315,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����J�݌����q���X�V�s�ł��B" + l_blnIsUpdatePossible);
        }
        
        //1.12:validate�����J�ݐ\�����(String,String)
        l_expAccountOpen.validateAccountOpenRegistInfo(
            WEB3ValidateTypeDef.ADMINISTRATOR_REGIST_UPDATE,
            WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);
        
        //1.13:validate�ڋq�R�[�h( )
        l_expAccountOpen.validateAccountCode();
        
        //1.14:validate���Z�@@��( )
        l_expAccountOpen.validateFinInstitution();

        //1.15:get�Ǘ��҃R�[�h( )
        String l_strAdminCode = l_admin.getAdministratorCode();
        
        //1.16:save�����J�݌����q(String)
        l_expAccountOpen.saveExpAccountOpen(WEB3ValidateTypeDef.ADMINISTRATOR_REGIST_UPDATE, l_strAdminCode);
        
        //1.17:get�،���ЃR�[�h( )
        String l_strInstitutionCode2 = l_expAccountOpen.getInstitutionCode();
        
        //1.18: get���ʃR�[�h( )
        String l_strRequestNumber = l_expAccountOpen.getRequestNumber();
        
        //1.19:to�����J�ݕs��(String, String, �s�����ڏ��[])
        WEB3AccOpenInvalidValues l_invalidValues = l_infoCreatedService.toAccOpenInvalidValues(l_strInstitutionCode2,
            l_strRequestNumber,
            l_request.invalidItemInfo);
            
        //1.20:set���l(String, String)
        l_invalidValues.setNote(l_request.bikou1, l_request.bikou2);
            
        //1:21:save�����J�ݕs��(String, String, String)
            l_invalidValues.saveAccOpenInvalidValues(l_strInstitutionCode2,
            l_strRequestNumber,
            l_strAdminCode);
            
        //1.22:createResponse( )
        WEB3AdminAccOpenApplyUpdateCompleteResponse l_response = 
            (WEB3AdminAccOpenApplyUpdateCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (validate�`�[�쐬)<BR>
     * �����J�ݓ`�[�쐬�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�󋵖⍇���jvalidate�`�[�쐬�v�Q�ƁB <BR>
     *  <BR>
     * =============================================== <BR>
     *          �V�[�P���X�} : �Ǘ��ҁE�����J�ݏ󋵖⍇�� / �����J�݁i�󋵖⍇���jvalidate�`�[�쐬 <BR>
     *          ��̈ʒu     : 1.9 �`�[�쐬�s�̏ꍇ�iis�`�[�쐬�\() == false�j�A��O���X���[����B <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01316 <BR>
     * =============================================== <BR> 
     * <BR>
     * =============================================== <BR>
     *          �V�[�P���X�} : �Ǘ��ҁE�����J�ݏ󋵖⍇�� / �����J�݁i�󋵖⍇���jvalidate�`�[�쐬 <BR>
     *          ��̈ʒu     : 1.12 �̕s��������ꍇ�iis����() == false�j�A��O���X���[����B <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01317 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *          �V�[�P���X�} : �Ǘ��ҁE�����J�ݏ󋵖⍇�� / �����J�݁i�󋵖⍇���jvalidate�`�[�쐬 <BR>
     *          ��̈ʒu     : �폜�ς݂̏ꍇ�iis�폜�ς�() == true�j�A��O���X���[����B <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_03178<BR>
     * =============================================== <BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݓ`�[�쐬�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmResponse
     * @@roseuid 4194395F012B
     */
    protected WEB3AdminAccOpenVoucherMakeConfirmResponse validateVoucherCreated(WEB3AdminAccOpenVoucherMakeConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateVoucherCreated(WEB3AdminAccOpenVoucherMakeConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4:validate���X����(String[])
        l_admin.validateBranchPermission(l_request.accoutOpenApplyInfo.branchCode);
        
        //1.5:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6:validate�X�e�[�^�X�ύX(�`�[�쐬���, String, String)
        validateStatusUpdated(l_request.voucherInfo, 
            l_strInstitutionCode,
            l_request.accoutOpenApplyInfo.requestNumber);
            
        //1.7:to�����J�݌����q(�����J�ݐ\�����)
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = 
            l_infoCreatedService.toAccOpenExpAccountOpen(l_request.accoutOpenApplyInfo);

        //1.8:validate���X�R�[�h����(String)
        validateBranchCode(
                l_expAccountOpen.getInstitutionCode(),
                l_expAccountOpen.getBranchCode());

        //is�폜�ς�( )
        boolean l_blnIsDeleted = l_expAccountOpen.isDeleted();

        //�폜�ς݂̏ꍇ�iis�폜�ς�() == true�j�A��O���X���[����B
        if (l_blnIsDeleted)
        {
            log.debug("�����J�݌����q���폜�ςł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�݌����q���폜�ςł��B" );
        }

        //1.9: is�`�[�쐬�\( )
        boolean l_blnIsVoucherCreatedPossible = l_expAccountOpen.isVoucherCreatedPossible();
        
        //1.10:�`�[�쐬�s�̏ꍇ�iis�`�[�쐬�\() == false�j�A��O���X���[����B�B
        if (!l_blnIsVoucherCreatedPossible)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01316,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����J�݌����q���`�[�쐬�s�ł��B" + 
                l_blnIsVoucherCreatedPossible);
        }
        
        //1.11: to�����J�ݕs��(String, String, �s�����ڏ��[])
        WEB3AccOpenInvalidValues l_invalidValues = l_infoCreatedService.toAccOpenInvalidValues(
            l_request.accoutOpenApplyInfo.institutionCode,
            l_request.accoutOpenApplyInfo.requestNumber,
            l_request.invalidItemInfo);
        
        //1.12:is����( )
        boolean l_blnIsComplete = l_invalidValues.isComplete();
        
        //1.13:�������̕s��������ꍇ�iis����() == false�j�A��O���X���[����B
        if (!l_blnIsComplete)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01317,
                this.getClass().getName() + STR_METHOD_NAME,
                "�s�����ڂ��������ł��B" + l_blnIsComplete);
        }
            
        //1.14:validate�����J�ݐ\�����(String,String)
        l_expAccountOpen.validateAccountOpenRegistInfo(
            WEB3ValidateTypeDef.VOUCHER_CREATED,
            l_request.accoutOpenApplyInfo.accountCodeAutoFlag);          
        
        ArrayList l_errorList = new ArrayList();
        
        //1.15�i���N�G�X�g�f�[�^.�����J�ݐ\�����.�ڋq�R�[�h�����̔ԃt���O != 1�j�A�������{
        if (!WEB3AccOpenAccountCodeAutoFlagDef.AUTO.equals(l_request.accoutOpenApplyInfo.accountCodeAutoFlag))
        {
            //1.141:validate�ڋq�R�[�h( )
            l_expAccountOpen.validateAccountCode();
        }

        //validate�����̔�(�����J�ݐ\����� : �����J�ݐ\�����, �،���ЃR�[�h : String)
        //[����]
        //�����J�ݐ\�����F���N�G�X�g�f�[�^.�����J�ݐ\�����
        //�،���ЃR�[�h�Fget�،���ЃR�[�h()
        this.validateAuto(l_request.accoutOpenApplyInfo, l_strInstitutionCode);

        //1.17:validate���Z�@@��( )
        l_expAccountOpen.validateFinInstitution();
        
        //1.18:validate���X�ݒ�ʃ`�F�b�N( )
        Collection l_collection =  validateBranchSetCheck(l_expAccountOpen);

        //1.19:(*)���X�ݒ�ʃ`�F�b�N�x�����b�Z�[�W�R�[�h���ԋp����
        //���ꍇ�ivalidate���X�ݒ�ʃ`�F�b�N().size()>0�j�A�������{
        if (l_collection != null && l_collection.size() > 0)
        {
            //1.18.1:addAll(arg0 : Collection)
            l_errorList.addAll(l_collection);
        }       
        
        //1.20:validate�ڋq���T�C�Y( )
        String l_strValidateAccountNameResult = l_expAccountOpen.validateAccountNameSize();
        
        //1.21:�����T�C�Y���ߌx�����b�Z�[�W�R�[�h���ԋp���ꂽ�ꍇ�ivalidate�ڋq���T�C�Y() != null�j
        if (l_strValidateAccountNameResult != null)
        {
            //1.20.1:add(arg0�i=�ڋq�������T�C�Y���ߌx�����b�Z�[�W�R�[�h�j
            l_errorList.add(l_strValidateAccountNameResult);
        }
        
        //1.22: validate�Z���T�C�Y( )
        String[] l_strValidateAddressResult = l_expAccountOpen.validateAddressSize();
        
        //1.23:�����T�C�Y���ߌx�����b�Z�[�W�R�[�h���ԋp���ꂽ�ꍇ�ivalidate�Z���T�C�Y() != null�j
        if (l_strValidateAddressResult != null)
        {
            //1.23.1:addAll(Collection)
            List l_list = Arrays.asList(l_strValidateAddressResult);
            
            l_errorList.addAll(l_list);
        }
        
        //1.24:toArray( )
        String[] l_strErrors = new String[l_errorList.size()];
        l_errorList.toArray(l_strErrors);
        
        //1.25:createResponse( )
        WEB3AdminAccOpenVoucherMakeConfirmResponse l_response = 
            (WEB3AdminAccOpenVoucherMakeConfirmResponse)l_request.createResponse();
            
        //1.26:�v���p�e�B�Z�b�g
        l_response.warningMessageList = l_strErrors;
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (submit�`�[�쐬)<BR>
     * �����J�ݓ`�[�쐬�����������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�󋵖⍇���jsubmit�`�[�쐬�v�Q�ƁB <BR>
     * <BR>
     * =============================================== <BR>
     *          �V�[�P���X�} :  �Ǘ��ҁE�����J�ݏ󋵖⍇�� / �����J�݁i�󋵖⍇���jsubmit�`�[�쐬 <BR>
     *          ��̈ʒu     : 1.10 �`�[�쐬�s�̏ꍇ�iis�`�[�쐬�\() == false�j�A��O���X���[����B <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01316 <BR>
     * =============================================== <BR> 
     * <BR>
     * =============================================== <BR>
     *          �V�[�P���X�} :  �Ǘ��ҁE�����J�ݏ󋵖⍇�� / �����J�݁i�󋵖⍇���jsubmit�`�[�쐬 <BR>
     *          ��̈ʒu     : 1.13 �̕s��������ꍇ�iis����() == false�j�A��O���X���[����B <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01317 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *          �V�[�P���X�} :  �Ǘ��ҁE�����J�ݏ󋵖⍇�� / �����J�݁i�󋵖⍇���jsubmit�`�[�쐬 <BR>
     *          ��̈ʒu     : �폜�ς݂̏ꍇ�iis�폜�ς�() == true�j�A��O���X���[����B<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_03178<BR>
     * =============================================== <BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݓ`�[�쐬�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteResponse
     * @@roseuid 4194395F014A
     */
    protected WEB3AdminAccOpenVoucherMakeCompleteResponse submitVoucherCreated(
        WEB3AdminAccOpenVoucherMakeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitVoucherCreated(WEB3AdminAccOpenVoucherMakeCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4:validate���X����(String[])
        l_admin.validateBranchPermission(l_request.accoutOpenApplyInfo.branchCode);
        
        //1.5: validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.6:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7:validate�X�e�[�^�X�ύX(�`�[�쐬���, String, String)
        validateStatusUpdated(l_request.voucherInfo, 
            l_strInstitutionCode,
            l_request.accoutOpenApplyInfo.requestNumber);
        
        //1.8:to�����J�݌����q(�����J�ݐ\�����)
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = 
            l_infoCreatedService.toAccOpenExpAccountOpen(l_request.accoutOpenApplyInfo);

        //1.9:validate���X�R�[�h����(String)
        validateBranchCode(
                l_expAccountOpen.getInstitutionCode(),
                l_expAccountOpen.getBranchCode());

        //is�폜�ς�( )
        boolean l_blnIsDeleted = l_expAccountOpen.isDeleted();

        //�폜�ς݂̏ꍇ�iis�폜�ς�() == true�j�A��O���X���[����B
        if (l_blnIsDeleted)
        {
            log.debug("�����J�݌����q���폜�ςł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�݌����q���폜�ςł��B" );
        }

        //1.10:is�`�[�쐬�\( )
        boolean l_blnIsVoucherCreatedPossible = l_expAccountOpen.isVoucherCreatedPossible();
        
        //1.11:�`�[�쐬�s�̏ꍇ�iis�`�[�쐬�\() == false�j�A��O���X���[����B�B
        if (!l_blnIsVoucherCreatedPossible)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01316,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����J�݌����q���`�[�쐬�s�ł��B" + 
                l_blnIsVoucherCreatedPossible);
        }
        
        //1.12:to�����J�ݕs��(String, String, �s�����ڏ��[])
        WEB3AccOpenInvalidValues l_invalidValues = l_infoCreatedService.toAccOpenInvalidValues(
            l_request.accoutOpenApplyInfo.institutionCode,
            l_request.accoutOpenApplyInfo.requestNumber,
            l_request.invalidItemInfo);
        
        //1.13:is����( )
        boolean l_blnIsComplete = l_invalidValues.isComplete();

        //1.14:�������̕s��������ꍇ�iis����() == false�j�A��O���X���[����B
        if (!l_blnIsComplete)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01317,
                this.getClass().getName() + STR_METHOD_NAME,
                "�s�����ڂ��������ł��B" + l_blnIsComplete);
        }
        
        //1.15:set���l(String, String)
        l_invalidValues.setNote(l_request.bikou1, l_request.bikou2);
        
        //1.16:validate�����J�ݐ\�����(String,String)
        l_expAccountOpen.validateAccountOpenRegistInfo(
            WEB3ValidateTypeDef.VOUCHER_CREATED,
            l_request.accoutOpenApplyInfo.accountCodeAutoFlag);
        
        //1.17�i���N�G�X�g�f�[�^.�����J�ݐ\�����.�ڋq�R�[�h�����̔ԃt���O != 1�j�A�������{
        if (!WEB3AccOpenAccountCodeAutoFlagDef.AUTO.equals(l_request.accoutOpenApplyInfo.accountCodeAutoFlag))
        {
            //1.171:validate�ڋq�R�[�h( )
            l_expAccountOpen.validateAccountCode();
        }

        //validate�����̔�(�����J�ݐ\����� : �����J�ݐ\�����, �،���ЃR�[�h : String)
        //[����]
        //�����J�ݐ\�����F���N�G�X�g�f�[�^.�����J�ݐ\�����
        //�،���ЃR�[�h�Fget�،���ЃR�[�h()
        this.validateAuto(l_request.accoutOpenApplyInfo, l_strInstitutionCode);

        //1.19:validate���Z�@@��( )
        l_expAccountOpen.validateFinInstitution();
        
        //1.20:validate���X�ݒ�ʃ`�F�b�N( )
        validateBranchSetCheck(l_expAccountOpen);

        //1.21:get�Ǘ��҃R�[�h( )
        String l_strAdminCode = l_admin.getAdministratorCode();
        
        //1.22�i���N�G�X�g�f�[�^.�����J�ݐ\�����.�ڋq�R�[�h�����̔ԃt���O = 1�j�A�������{
        if (WEB3AccOpenAccountCodeAutoFlagDef.AUTO.equals(l_request.accoutOpenApplyInfo.accountCodeAutoFlag))
        {
            //1.22.1 get�V�K�ڋq�R�[�h(String, String, String)
            //[����] 
            //�@@�،���ЃR�[�h�F���N�G�X�g�f�[�^.�����J�ݐ\�����.�،���ЃR�[�h 
            //�@@���X�R�[�h�F���N�G�X�g�f�[�^.�����J�ݐ\�����.���X�R�[�h 
            //�@@�ڋq�敪�F���N�G�X�g�f�[�^.�ڋq�敪
            WEB3AccOpenAccountCodeService l_service = 
                (WEB3AccOpenAccountCodeService)Services.getService(WEB3AccOpenAccountCodeService.class);
            String l_strInstCode = l_request.accoutOpenApplyInfo.institutionCode;
            String l_strBranchCode = l_request.accoutOpenApplyInfo.branchCode;
            String l_strNewAccountCode = l_service.getNewAccountCode(
                l_strInstCode, l_strBranchCode, l_request.accountDiv);
            
            //1.22.2 set�����R�[�h(String)
            l_expAccountOpen.setAccountCode(l_strNewAccountCode);
        }
        
        //1.23:save�����J�݌����q(String, String)
        l_expAccountOpen.saveExpAccountOpen(WEB3ValidateTypeDef.VOUCHER_CREATED, l_strAdminCode);
        
        //1.24:get�،���ЃR�[�h( )
        String l_strInstitutionCode2 = l_expAccountOpen.getInstitutionCode();
        
        //1.25: get���ʃR�[�h( )
        String l_strRequestNumber = l_expAccountOpen.getRequestNumber();
        
        //1.26:save�����J�ݕs��(String, String, String)
        l_invalidValues.saveAccOpenInvalidValues(l_strInstitutionCode2,
            l_strRequestNumber,
            l_strAdminCode);
            
        //1.27:get�����J�ݐR���҂���񃊃X�g( )
        ArrayList l_judgeWaitingInfoList = l_expAccountOpen.getAccOpenJudgeWaitingInfoList();
        
        String[] l_strVouchers = null;
		int l_intWaitingCount = 0;
        
        //1.28:get�����J�ݐR���҂���񃊃X�g().size()==0�̏ꍇ�͏������s���B
        if (l_judgeWaitingInfoList == null || l_judgeWaitingInfoList.size() == 0)
        {
            //1.28.1:create�����J�ݓ`�[(�����J�݌����q)
            WEB3AccOpenVoucherCreatedService l_voucherCreatedService = 
                (WEB3AccOpenVoucherCreatedService)Services.getService(WEB3AccOpenVoucherCreatedService.class);
            l_strVouchers = l_voucherCreatedService.createAccOpenVoucher(l_expAccountOpen);
        }
        
        //1.29:get�����J�ݐR���҂���񃊃X�g().size()>0�̏ꍇ�͏������s���B
        else if (l_judgeWaitingInfoList.size() > 0)
        {
            //1.29.1:to�����J�ݐR���҂�( ) 
            //1.29.1.1:�����J�ݐR���҂�( )
            WEB3AccOpenJudgeWaiting l_judgeWaiting = 
                l_infoCreatedService.toAccOpenJudgeWaiting();
            String[] l_strRequestNumbers = new String[1];
			l_strRequestNumbers[0]=l_strRequestNumber;
			//1.29.2:select�R���Ώۈꗗ( )
			//1.29.3:select�R���Ώۈꗗ()==0�̏ꍇ�͏������s���B
			l_intWaitingCount = l_judgeWaiting.selectJudgeObjectList(
					   l_strInstitutionCode2, l_strRequestNumbers);
			if (l_intWaitingCount == 0)
			{
				//1.29.4:get�����J�ݐR���҂���񃊃X�g().size()�񐔌J��Ԃ��B
				//1.29.4.1.1:add�����J�ݐR���҂��s(�����J�ݐR���҂�Param)
				for (int i = 0; i < l_judgeWaitingInfoList.size(); i++)
				{
					AccOpenWaitingRow l_accOpenWaitingRow = (AccOpenWaitingRow)l_judgeWaitingInfoList.get(i);
					AccOpenWaitingParams l_accOpenWaitingParams = new AccOpenWaitingParams(l_accOpenWaitingRow);
					l_judgeWaiting.addAccOpenWaitingParams(l_accOpenWaitingParams);
				}
            
				//1.29.3.1:insert�����J�ݐR���҂�( )
				l_judgeWaiting.insertAccOpenWaiting();
			}          
        }
        
        //1.30:get�����J�ݐR���҂���񃊃X�g().size()>0 && select�R���Ώۈꗗ() != 0 �̏ꍇ�͏������s���B
		//�� �R�����F��̓`�[�č쐬�p����
        if ((l_judgeWaitingInfoList != null && l_judgeWaitingInfoList.size() > 0)
                  && l_intWaitingCount != 0)
        {
			//1.30.1:create�����J�ݓ`�[(�����J�݌����q)
			WEB3AccOpenVoucherCreatedService l_voucherCreatedService = 
				(WEB3AccOpenVoucherCreatedService)Services.getService(WEB3AccOpenVoucherCreatedService.class);
			l_strVouchers = l_voucherCreatedService.createAccOpenVoucher(l_expAccountOpen);
        }
        
        //1.31:createResponse( )
        WEB3AdminAccOpenVoucherMakeCompleteResponse l_response = 
            (WEB3AdminAccOpenVoucherMakeCompleteResponse)l_request.createResponse();
            
        //1.32:�v���p�e�B�Z�b�g
        l_response.creationCompleteList = l_strVouchers;
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (validate�`�[���)<BR>
     * �����J�ݓ`�[����m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�󋵖⍇���jvalidate�`�[����v�Q�ƁB <BR>
     * <BR> 
     * =============================================== <BR>
     *          �V�[�P���X�} : �Ǘ��ҁE�����J�ݏ󋵖⍇�� / �����J�݁i�󋵖⍇���jvalidate�`�[��� <BR>
     *          ��̈ʒu     : 1.9 ����\�łȂ��ꍇ�iis����\() == false�j�A��O���X���[����B <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01319 <BR>
     * =============================================== <BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݓ`�[����m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmResponse
     * @@roseuid 41943A4D037D
     */
    protected WEB3AdminAccOpenVoucherCancelConfirmResponse validateVoucherCanceled(WEB3AdminAccOpenVoucherCancelConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateVoucherCanceled(WEB3AdminAccOpenVoucherCancelConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
        
        try
        {
            //1.5:�����J�݌����q(String, String)
            l_expAccountOpen = new WEB3AccOpenExpAccountOpen(
            l_strInstitutionCode,
            l_request.requestNumber);//NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            log.debug("NotFoundException");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                " �����J�݌����q��null�ł���B" + l_expAccountOpen); 
        }
        
        //1.6: get���X�R�[�h( )
        String l_strBranchCode = l_expAccountOpen.getBranchCode();
        
        //1.7:validate���X����(String[])
        l_admin.validateBranchPermission(l_strBranchCode);
        
        //1.8: is����\( )
        boolean l_blnIsCanceledPossible = l_expAccountOpen.isCanceledPossible();
        
        //1.9: ����\�łȂ��ꍇ�iis����\() == false�j�A��O���X���[����B
        if (!l_blnIsCanceledPossible)
        {
            log.debug("1.9: ����\�łȂ��ꍇ�iis����\() == false�j");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01319,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����J�݌����q������s�ł��B" + l_blnIsCanceledPossible);
        }
        
        //1.10: getDataSourceObject( )
        ExpAccountOpenParams l_params = (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
        
        //1.11:createResponse( )
        WEB3AdminAccOpenVoucherCancelConfirmResponse l_response = 
            (WEB3AdminAccOpenVoucherCancelConfirmResponse)l_request.createResponse();
            
        //1.12:�v���p�e�B�Z�b�g
        l_response.branchCode = l_params.branch_code;
        l_response.accountCode = l_params.account_code;
        l_response.accountFamilyNameKana = l_params.family_name_alt1;
        l_response.accountNameKana = l_params.given_name_alt1;
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (submit�`�[���)<BR>
     * �����J�ݓ`�[��������������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�󋵖⍇���jsubmit�`�[����v�Q�ƁB <BR>
     * <BR>
     * =============================================== <BR>
     *          �V�[�P���X�} : �Ǘ��ҁE�����J�ݏ󋵖⍇�� / �����J�݁i�󋵖⍇���jsubmit�`�[��� <BR>
     *          ��̈ʒu     : 1.7 �����J�݌����q�����f�[�^�����݂��Ȃ��ꍇ�inull���ԋp���ꂽ�ꍇ�j�A<BR>
     *                         ��O���X���[����B <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01318 <BR>
     * =============================================== <BR> 
     * <BR> 
     * =============================================== <BR>
     *          �V�[�P���X�} : �Ǘ��ҁE�����J�ݏ󋵖⍇�� / �����J�݁i�󋵖⍇���jsubmit�`�[��� <BR>
     *          ��̈ʒu     : 1.11 ����\�łȂ��ꍇ�iis����\() == false�j�A��O���X���[����B <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01319 <BR>
     * =============================================== <BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݓ`�[����������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteResponse
     * @@roseuid 41943AB601F6
     */
    protected WEB3AdminAccOpenVoucherCancelCompleteResponse submitVoucherCanceled(WEB3AdminAccOpenVoucherCancelCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitVoucherCanceled(WEB3AdminAccOpenVoucherCancelCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5: validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
        
        //1.6:�����J�݌����q(String, String)
        try
        {
            l_expAccountOpen = new WEB3AccOpenExpAccountOpen(
                l_strInstitutionCode,
                l_request.requestNumber);//NotFoundException
        }
        //1.7:�����J�݌����q�����f�[�^�����݂��Ȃ��ꍇ�inull���ԋp���ꂽ�ꍇ�j
        catch (NotFoundException l_ex)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01318,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����J�݌����q�����f�[�^�����݂��Ȃ��B");
        }
        
        //1.8: get���X�R�[�h( )
        String l_strBranchCode = l_expAccountOpen.getBranchCode();
        
        //1.9:validate���X����(String[])
        l_admin.validateBranchPermission(l_strBranchCode);
        
        //1.10 is����\( )
        boolean l_blnIsCanceledPossible = l_expAccountOpen.isCanceledPossible();
        
        //1.11 ����\�łȂ��ꍇ�iis����\() == false�j�A��O���X���[����B
        if (!l_blnIsCanceledPossible)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01319,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����J�݌����q������s�ł��B" + l_blnIsCanceledPossible);
        }
        
        //1.12:delete�����J�ݓ`�[(�����J�݌����q)
        WEB3AccOpenVoucherCreatedService l_voucherCreatedService = 
            (WEB3AccOpenVoucherCreatedService)Services.getService(WEB3AccOpenVoucherCreatedService.class);
        l_voucherCreatedService.deleteAccOpenVoucher(l_expAccountOpen);     
            
        //1.11:createResponse( )
        WEB3AdminAccOpenVoucherCancelCompleteResponse l_response = 
            (WEB3AdminAccOpenVoucherCancelCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (validate�X�e�[�^�X�ύX)<BR>
     * �Ɖ�̃X�e�[�^�X�ƕύX���Ȃ������`�F�b�N����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�󋵖⍇���jvalidate�X�e�[�^�X�ύX�v�Q�ƁB <BR>
     * <BR>
     * =============================================== <BR>
     *          �V�[�P���X�} : �Ǘ��ҁE�����J�ݏ󋵖⍇�� / �����J�݁i�󋵖⍇���jvalidate�X�e�[�^�X�ύX <BR>
     *          ��̈ʒu     : 1.5 �Ɖ�̃X�e�[�^�X�ƌ��݂̃X�e�[�^�X����v���Ȃ��ꍇ�iequals() == false�j�A<BR>
     *                         ��O���X���[����B <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01320 <BR>
     * =============================================== <BR>
     * @@param l_accOpenVoucherInfo - �`�[�쐬��񃁃b�Z�[�W�f�[�^
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@roseuid 419C04260003
     */
    protected void validateStatusUpdated(WEB3AccOpenVoucherInfo l_accOpenVoucherInfo, String l_strInstitutionCode, String l_strRequestNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateStatusUpdated(WEB3AccOpenVoucherInfo, String, String) ";
        log.entering(STR_METHOD_NAME);        
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
        
        try
        {
            //1.1:�����J�݌����q(String, String)
            l_expAccountOpen = new WEB3AccOpenExpAccountOpen(
            l_strInstitutionCode,
            l_strRequestNumber);//NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                " �����J�݌����q��null�ł���B" + l_expAccountOpen);    
        }
          
        //1.2:get�`�[�X�e�[�^�X( )
        WEB3AccOpenVoucherCreatedStatus[] l_voucherCreatedStatus = 
            l_expAccountOpen.getVoucherStatus();
        
        //1.3:to�`�[�쐬���(�����J�ݓ`�[�쐬�X�e�[�^�X[])
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        
        WEB3AccOpenVoucherInfo l_voucherInfo = 
            l_infoCreatedService.toAccOpenVoucherInfo(l_voucherCreatedStatus);
            
        boolean l_blnFlag = false;
        
        if (l_voucherInfo != null)
        {
            //1.4:equals(Object)
            l_blnFlag = l_accOpenVoucherInfo.equals(l_voucherInfo);
        }
        else
        {
            l_voucherInfo = new WEB3AccOpenVoucherInfo();
            l_blnFlag = l_accOpenVoucherInfo.equals(l_voucherInfo);
        }
        
        //1.5:�Ɖ�̃X�e�[�^�X�ƌ��݂̃X�e�[�^�X����v���Ȃ��ꍇ�iequals() == false�j�A��O���X���[����B
        if (!l_blnFlag)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01320,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ɖ�̃X�e�[�^�X�ƌ��݂̃X�e�[�^�X���s��v�ł��B" + 
                l_blnFlag);                 
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create��������������)<BR>
     * ���������������ҏW����B <BR>
     * <BR>
     * �P�j�@@�߂�l���� <BR>
     * �@@�߂�l�̌�������������C���X�^���X�i�FString�j�𐶐� <BR>
     * <BR>
     * �Q�j�@@�،���Џ����ǉ�<BR>
     * �@@�،���ЃR�[�h������ǉ�����B<BR>
     * <BR>
     * �@@" institution_code = ? "<BR>
     * <BR>
     * �R�j�@@���X�����ǉ� <BR>
     * �@@�i���N�G�X�g�f�[�^.���X�R�[�h != null�j�̏ꍇ�A<BR>
     * �@@���X�R�[�h�z��̗v�f�����A���X�R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" and �ibranch_code = ? or branch_code = ? ����j" <BR>
     * <BR>
     * �S�j�@@���ʃR�[�h�����ǉ��@@���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.���ʃR�[�h != null�j�̏ꍇ�A���ʃR�[�h������ǉ�����B<BR>
     * <BR>
     * �@@" and acc_open_request_number like ? " <BR>
     * <BR>
     * �T�j�@@�ڋq�R�[�h�����ǉ��@@���w�肪����ꍇ�̂� <BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq�R�[�h != null�j�̏ꍇ�A�����R�[�h������ǉ�����B<BR> 
     * <BR>
     * �@@" and account_code like ? " <BR>
     * <BR>
     * �U�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null�j�̏ꍇ�A�����R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" and account_code >= ? " <BR>
     * <BR>
     * �V�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null�j�̏ꍇ�A�����R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" and account_code <= ? " <BR>
     * <BR>
     * �W�j�@@�����敪�����ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����敪 != null�j�̏ꍇ�A�����敪������ǉ�����B <BR>
     * <BR>
     * �@@" and account_div = ? " <BR>
     * <BR>
     * �X�j�@@���Џ����ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.�O���l�t���O != null�j�̏ꍇ�A���Џ�����ǉ�����B<BR>
     * <BR>
     * �@@" and foreign_flag = ? "<BR>
     * <BR>
     * �P�O�j�@@�폜�����ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.�폜�t���O != null�j�̏ꍇ�A�폜������ǉ�����B<BR>
     * <BR>
     * �@@" and delete_flag = ? "<BR>
     * <BR>
     * �P�P�j�@@��������ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.����t���O != null�j�̏ꍇ�A���������ǉ�����B<BR>
     * <BR>
     * �@@" and print_flag = ? "<BR>
     * <BR>
     * �P�Q�j�@@��̏����ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.��̃t���O != null�j�̏ꍇ�A��̏�����ǉ�����B<BR>
     * <BR>
     * �@@" and receipt_flag = ? "<BR>
     * <BR>
     * �P�R�j�@@��������敪�����ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.��������敪 != null�j�̏ꍇ�A��������敪������ǉ�����B<BR>
     * <BR>
     * �@@�@@[���N�G�X�g�f�[�^.��������敪 == "���"�̏ꍇ]<BR>
     * " and special_acc = ? "<BR>
     * [���N�G�X�g�f�[�^.��������敪 == "����"�̏ꍇ]<BR>
     * " and special_acc in (?, ?) "<BR>
     * <BR>
     * �P�S�j�@@�ڋq���i�J�i�j�����ǉ� ���w�肪����ꍇ�̂݁C�B������ <BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq���i�J�i�j != null�j�̏ꍇ�A�ڋq���i�J�i�j�����ilike�j��<BR>
     * �ǉ�����B <BR>
     * <BR>
     * �@@" and family_name_alt1 like ? " <BR>
     * <BR>
     * �P�T�j�@@�ڋq���i�J�i�j�����ǉ� ���w�肪����ꍇ�̂݁C�B������ <BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq���i�J�i�j != null�j�̏ꍇ�A�ڋq���i�J�i�j�����ilike�j��<BR>
     * �ǉ�����B <BR>
     * <BR>
     * �@@" and given_name_alt1 like ? " <BR>
     * <BR>
     * �P�U�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����������i���j != null�j�̏ꍇ�A������������������<BR>
     * �ǉ�����B <BR>
     * <BR>
     * �@@" and to_char(infomation_claim_datetime, 'YYYYMMDD') >= ? " <BR>
     * <BR>
     * �P�V�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����������i���j != null�j�̏ꍇ�A������������������<BR>
     * �ǉ�����B <BR>
     * <BR>
     * �@@" and to_char(infomation_claim_datetime, 'YYYYMMDD') <= ? " <BR>
     * <BR>
     * �P�W�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����J�ݓ��i���j != null�j�̏ꍇ�A�����J�ݓ�������<BR>
     * �ǉ�����B <BR>
     * <BR>
     * �@@" and to_char(account_open_date, 'YYYYMMDD') >= ? " <BR>
     * <BR>
     * �P�X�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����J�ݓ��i���j != null�j�̏ꍇ�A�����J�ݓ�������<BR>
     * �ǉ�����B <BR>
     * <BR>
     * �@@" and to_char(account_open_date, 'YYYYMMDD') <= ? " <BR>
     * <BR>
     * <BR>
     * �Q�O�j�@@�����ғo�^�敪�����ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����ғo�^�敪 != null�j�@@����<BR>
     * �@@�@@�i���N�G�X�g�f�[�^.�����敪=0�F�l�A�J�E���g�j�̏ꍇ�A�����ғo�^�t���O������ǉ�����B<BR> 
     * <BR>
     * �@@" and insider_flag = ? "<BR>
     * <BR>
     * �@@�i���N�G�X�g�f�[�^.�����ғo�^�敪 != null�j�@@����<BR>
     * �@@�@@�i���N�G�X�g�f�[�^.�����敪=1�F�@@�l�A�J�E���g�j�̏ꍇ�A�i�����ҁj�쐬�敪������ǉ�����B<BR> 
     * <BR>
     * �@@" and insider_voucher_div = ? "<BR>
     * <BR>
     * �Q�P�j�@@������C���X�^���X��ԋp 
     * @@param l_request - �Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return String
     * @@roseuid 419C6657009F
     */
    protected String createQueryString(WEB3AdminAccOpenStateInquiryListRequest l_request) 
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminAccOpenStateInquiryListRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�߂�l���� 
        String l_strQueryString = "";
        
        //�Q�j�@@�،���Џ����ǉ� 
        l_strQueryString = " institution_code = ? "; 

        //�R�j�@@���X�����ǉ� 
        //�i���N�G�X�g�f�[�^.���X�R�[�h != null�j�̏ꍇ�A 
        //���X�R�[�h�z��̗v�f�����A���X�R�[�h������ǉ�����B
        if (l_request.branchCode != null && l_request.branchCode.length > 0)
        {
            l_strQueryString += " and (";
            
            int l_intCnt = l_request.branchCode.length;
            for (int i = 0; i < l_intCnt; i++)
            {
                l_strQueryString += "branch_code = ? or ";
            }
            
            l_strQueryString = l_strQueryString.substring(0, l_strQueryString.length() - 4) + ") ";
        }

        //�S�j�@@���ʃR�[�h�����ǉ��@@���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.���ʃR�[�h != null�j�̏ꍇ�A���ʃR�[�h������ǉ�����B
        if (l_request.requestNumber != null)
        {
            l_strQueryString += " and acc_open_request_number like ? ";
        } 
        
        //�T�j�@@�ڋq�R�[�h�����ǉ��@@���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�ڋq�R�[�h != null�j�̏ꍇ�A�����R�[�h������ǉ�����B
        if (l_request.accountCode != null)
        {
            l_strQueryString += " and account_code like ? ";
        } 

        //�U�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null�j�̏ꍇ�A�����R�[�h������ǉ�����B
        if (l_request.accountCodeFrom != null)
        {
            l_strQueryString += " and account_code >= ? ";
        }
         
        //�V�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null�j�̏ꍇ�A�����R�[�h������ǉ�����B
        if (l_request.accountCodeTo != null)
        {
            l_strQueryString += " and account_code <= ? ";
        } 

        //�W�j�@@�����敪�����ǉ�
        //�i���N�G�X�g�f�[�^.�����敪 != null�j�̏ꍇ�A�����敪������ǉ�����B
        if (l_request.accountType != null)
        {
            l_strQueryString += " and account_div = ? ";
        } 

        //���Џ����ǉ�
        //�i���N�G�X�g�f�[�^.�O���l�t���O != null�j�̏ꍇ�A���Џ�����ǉ�����B
        // " and foreign_flag = ? "
        if (l_request.foreignerFlag != null)
        {
            l_strQueryString += " and foreign_flag = ? ";
        }

        //�폜�����ǉ�
        //�i���N�G�X�g�f�[�^.�폜�t���O != null�j�̏ꍇ�A�폜������ǉ�����B
        // " and delete_flag = ? "
        if (l_request.deleteFlag != null)
        {
            l_strQueryString += " and delete_flag = ? ";
        }

        //��������ǉ�
        //�i���N�G�X�g�f�[�^.����t���O != null�j�̏ꍇ�A���������ǉ�����B
        // " and print_flag = ? "
        if (l_request.printFlag != null)
        {
            l_strQueryString += " and print_flag = ? ";
        }

        //��̏����ǉ�
        //�i���N�G�X�g�f�[�^.��̃t���O != null�j�̏ꍇ�A��̏�����ǉ�����B
        // " and receipt_flag = ? "
        if (l_request.receiveFlag != null)
        {
            l_strQueryString += " and receipt_flag = ? ";
        }

        //��������敪�����ǉ�
        //�i���N�G�X�g�f�[�^.��������敪 != null�j�̏ꍇ�A��������敪������ǉ�����B
        if (l_request.taxTypeDiv != null)
        {
            if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxTypeDiv))
            {
                // [���N�G�X�g�f�[�^.��������敪 == "���"�̏ꍇ]
                // " and special_acc = ? "
                l_strQueryString += " and special_acc = ? ";
            }

            if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxTypeDiv))
            {
                // [���N�G�X�g�f�[�^.��������敪 == "����"�̏ꍇ]
                // " and special_acc in (?, ?) "
                l_strQueryString += " and special_acc in (?, ?) ";
            }
        }

        //�X�j�@@�ڋq���i�J�i�j�����ǉ� ���w�肪����ꍇ�̂݁C�B������ 
        //�i���N�G�X�g�f�[�^.�ڋq���i�J�i�j != null�j�̏ꍇ�A�ڋq���i�J�i�j�����ilike�j��ǉ�����B 
        if (l_request.accountFamilyNameKana != null)
        {
            l_strQueryString += " and family_name_alt1 like ? ";
        }

        //�P�O�j�@@�ڋq���i�J�i�j�����ǉ� ���w�肪����ꍇ�̂݁C�B������ 
        //�i���N�G�X�g�f�[�^.�ڋq���i�J�i�j != null�j�̏ꍇ�A�ڋq���i�J�i�j�����ilike�j��ǉ�����B
        if (l_request.accountNameKana != null)
        {
            l_strQueryString += " and given_name_alt1 like ? ";
        } 

        //�P�P�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�����������i���j != null�j�̏ꍇ�A������������������ǉ�����B
        if (l_request.infoClaimDateFrom != null)
        {
            l_strQueryString += " and to_char(infomation_claim_datetime, 'YYYYMMDD') >= ? ";
        }  

        //�P�Q�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�����������i���j != null�j�̏ꍇ�A������������������ǉ�����B
        if (l_request.infoClaimDateTo != null)
        {
            l_strQueryString += " and to_char(infomation_claim_datetime, 'YYYYMMDD') <= ? ";
        }  
        
        //�P�R�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�����J�ݓ��i���j != null�j�̏ꍇ�A�����J�ݓ�������ǉ�����B
        if (l_request.accountOpenDateFrom != null)
        {
            l_strQueryString += " and to_char(account_open_date, 'YYYYMMDD') >= ? ";
        }   

        //�P�S�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�����J�ݓ��i���j != null�j�̏ꍇ�A�����J�ݓ�������ǉ�����B
        if (l_request.accountOpenDateTo != null)
        {
            l_strQueryString += " and to_char(account_open_date, 'YYYYMMDD') <= ? ";
        }  

        //�Q�O�j�@@�����ғo�^�敪�����ǉ�
        //�i���N�G�X�g�f�[�^.�����ғo�^�敪 != null�j
        if (l_request.insiderDiv != null)
        {
            //�i���N�G�X�g�f�[�^.�����敪=0�F�l�A�J�E���g�j�̏ꍇ�A�����ғo�^�t���O������ǉ�����B
            //" and insider_flag = ? "
            if (WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(l_request.accountType))
            {
                l_strQueryString += " and insider_flag = ? ";
            }
            //�i���N�G�X�g�f�[�^.�����敪=1�F�@@�l�A�J�E���g�j�̏ꍇ�A�i�����ҁj�쐬�敪������ǉ�����B
            //" and insider_voucher_div = ? "
            else if (WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(l_request.accountType))
            {
                l_strQueryString += " and insider_voucher_div = ? ";
            }
        }

        log.exiting(STR_METHOD_NAME);
        
        //�P�T�j�@@������C���X�^���X��ԋp 
        return l_strQueryString;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i��ҏW����B <BR>
     * <BR>
     * �P�j�@@�߂�l���� <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐� <BR>
     * <BR>
     * �Q�j�@@�،���Џ����ǉ�<BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�ɁA�،���ЃR�[�h��ǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@�،���ЃR�[�h<BR>
     * <BR>
     * �R�j�@@���X�����ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.���X�R�[�h[] != null�j�̏ꍇ�A<BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�ɁA���X�R�[�h�z��̗v�f�������X�R�[�h��<BR>
     * �ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.���X�R�[�h[index]<BR>
     * <BR>
     * �S�j�@@���ʃR�[�h�����ǉ��@@���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.���ʃR�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X��<BR>
     * ���ʃR�[�h��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.���ʃR�[�h + '%'<BR>
     * <BR>
     * �T�j�@@�ڋq�R�[�h�����ǉ��@@���w�肪����ꍇ�̂� <BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq�R�[�h != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X��<BR>
     * �ڋq�R�[�h��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�ڋq�R�[�h + '%'<BR>
     * <BR>
     * �U�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X��<BR>
     * �ڋq�R�[�h�i���j��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j<BR>
     * <BR>
     * �V�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X��<BR>
     * �ڋq�R�[�h�i���j��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j<BR>
     * <BR>
     * �W�j�@@�����敪�����ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����敪 != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X��<BR>
     * �����敪��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�����敪<BR>
     * <BR>
     * �X�j�@@���Џ����ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.�O���l�t���O != null�j�̏ꍇ�A<BR>
     * �@@�@@�@@�߂�l�ҏW�p�C���X�^���X�ɊO���l�t���O��ǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@���N�G�X�g�f�[�^.�O���l�t���O<BR>
     * <BR>
     * �P�O�j�@@�폜�����ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.�폜�t���O != null�j�̏ꍇ�A<BR>
     * �@@�@@�@@�߂�l�ҏW�p�C���X�^���X�ɍ폜�t���O��ǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@���N�G�X�g�f�[�^.�폜�t���O<BR>
     * <BR>
     * �P�P�j�@@��������ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.����t���O != null�j�̏ꍇ�A<BR>
     * �@@�@@�@@�߂�l�ҏW�p�C���X�^���X�Ɉ���t���O��ǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@���N�G�X�g�f�[�^.����t���O<BR>
     * <BR>
     * �P�Q�j�@@��̏����ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.��̃t���O != null�j�̏ꍇ�A<BR>
     * �@@�@@�@@�߂�l�ҏW�p�C���X�^���X�Ɏ�̃t���O��ǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@���N�G�X�g�f�[�^.��̃t���O<BR>
     * <BR>
     * �P�R�j�@@��������敪�����ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.��������敪 != null�j�̏ꍇ�A<BR>
     * �@@�@@�@@�߂�l�ҏW�p�C���X�^���X�ɓ�������敪��ǉ�����B<BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^.��������敪 == "���"�̏ꍇ�A<BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@�u0�F��ʌ����v<BR>
     * �@@���N�G�X�g�f�[�^.��������敪 == "����"�̏ꍇ�A<BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@�u1�F��������i���򒥎��Ȃ��j�v<BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@�u2�F��������i���򒥎�����j�v<BR>
     * <BR>
     * �P�S�j�@@�ڋq���i�J�i�j�����ǉ� ���w�肪����ꍇ�̂݁C�B������ <BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq���i�J�i�j != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X��<BR>
     * �ڋq���i�J�i�j��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@'%' + ���N�G�X�g�f�[�^.�ڋq���i�J�i�j + '%'<BR>
     * <BR>
     * �P�T�j�@@�ڋq���i�J�i�j�����ǉ� ���w�肪����ꍇ�̂݁C�B������ <BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq���i�J�i�j != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X��<BR>
     * �ڋq���i�J�i�j��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@'%' + ���N�G�X�g�f�[�^.�ڋq���i�J�i�j + '%'<BR>
     * <BR>
     * �P�U�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����������i���j != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɏ����������i���j��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�����������i���j��YYYYMMDD�ɕҏW����������<BR>
     * <BR>
     * �P�V�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����������i���j != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɏ����������i���j��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�����������i���j��YYYYMMDD�ɕҏW����������
     * <BR>
     * �P�W�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����J�ݓ��i���j != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɍ����J�ݓ��i���j��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�����J�ݓ��i���j��YYYYMMDD�ɕҏW����������<BR>
     * <BR>
     * �P�X�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����J�ݓ��i���j != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɍ����J�ݓ��i���j��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�����J�ݓ��i���j��YYYYMMDD�ɕҏW����������<BR>
     * <BR>
     * �Q�O�j�@@�����ғo�^�敪�����ǉ�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����ғo�^�敪 != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɓ����ғo�^�敪��ǉ�����B<BR>
     * <BR>
     * �u�i���N�G�X�g�f�[�^.�����ғo�^�敪 != null�j�@@����<BR>
     * �@@�i���N�G�X�g�f�[�^.�����敪=0�F�l�A�J�E���g�j�̏ꍇ�v<BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@���N�G�X�g�f�[�^.�����ғo�^�敪<BR>
     * <BR>
     * �u�i���N�G�X�g�f�[�^.�����ғo�^�敪 != null�j�@@����<BR>
     * �@@�i���N�G�X�g�f�[�^.�����敪=1�F�@@�l�A�J�E���g�j�̏ꍇ�v<BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@���N�G�X�g�f�[�^.�����ғo�^�敪<BR>
     * <BR>
     * �Q�P�j�@@�z���ԋp<BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B <BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@return String[]
     * @@roseuid 419C66D702F1
     */
    protected String[] createQueryContainer(WEB3AdminAccOpenStateInquiryListRequest l_request, String l_strInstitutionCode) 
    {
        final String STR_METHOD_NAME = " createQueryContainer(WEB3AdminAccOpenStateInquiryListRequest, String) ";
        log.entering(STR_METHOD_NAME);        
        
        //�P�j�@@�߂�l���� 
        ArrayList l_queryContainerList = new ArrayList();

        //�Q�j�@@�،���Џ����ǉ� 
        l_queryContainerList.add(l_strInstitutionCode);

        //�R�j�@@���X�����ǉ�
        //�i���N�G�X�g�f�[�^.���X�R�[�h[] != null�j�̏ꍇ�A
        if (l_request.branchCode != null && l_request.branchCode.length > 0)
        {
            String[] l_strBranchCodes = l_request.branchCode;
            int l_intCnt = l_strBranchCodes.length;
            for (int i = 0; i < l_intCnt; i++)
            {
                l_queryContainerList.add(l_strBranchCodes[i]);
            }
        }

        //�S�j�@@���ʃR�[�h�����ǉ��@@���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.���ʃR�[�h != null�j�̏ꍇ�A 
        if (l_request.requestNumber != null)
        {
            l_queryContainerList.add(l_request.requestNumber + '%');
        } 

        //�T�j�@@�ڋq�R�[�h�����ǉ��@@���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�ڋq�R�[�h != null�j�̏ꍇ�A
        if (l_request.accountCode != null)
        {
            l_queryContainerList.add(l_request.accountCode + '%');
        }  

        //�U�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null�j�̏ꍇ 
        if (l_request.accountCodeFrom != null)
        {
            l_queryContainerList.add(l_request.accountCodeFrom);
        } 

        //�V�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null�j�̏ꍇ
        if (l_request.accountCodeTo != null)
        {
            l_queryContainerList.add(l_request.accountCodeTo);
        }
         
        //�W�j�@@�����敪�����ǉ� 
        //�i���N�G�X�g�f�[�^.�����敪 != null�j�̏ꍇ�A
        if (l_request.accountType != null)
        {
            l_queryContainerList.add(l_request.accountType);
        } 

        //���Џ����ǉ�
        //�i���N�G�X�g�f�[�^.�O���l�t���O != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɊO���l�t���O��ǉ�����B
        //�@@[add()�Ɏw�肷�����]
        //�@@���N�G�X�g�f�[�^.�O���l�t���O
        if (l_request.foreignerFlag != null)
        {
            l_queryContainerList.add(l_request.foreignerFlag);
        }

        //�폜�����ǉ�
        //�i���N�G�X�g�f�[�^.�폜�t���O != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɍ폜�t���O��ǉ�����B
        //�@@[add()�Ɏw�肷�����]
        //�@@���N�G�X�g�f�[�^.�폜�t���O
        if (l_request.deleteFlag != null)
        {
            l_queryContainerList.add(l_request.deleteFlag);
        }

        //��������ǉ�
        //�i���N�G�X�g�f�[�^.����t���O != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�Ɉ���t���O��ǉ�����B
        //�@@[add()�Ɏw�肷�����]
        //�@@���N�G�X�g�f�[�^.����t���O
        if (l_request.printFlag != null)
        {
            l_queryContainerList.add(l_request.printFlag);
        }

        //��̏����ǉ�
        //�i���N�G�X�g�f�[�^.��̃t���O != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�Ɏ�̃t���O��ǉ�����B
        //�@@[add()�Ɏw�肷�����]
        //�@@���N�G�X�g�f�[�^.��̃t���O
        if (l_request.receiveFlag != null)
        {
            l_queryContainerList.add(l_request.receiveFlag);
        }

        //��������敪�����ǉ�
        //�i���N�G�X�g�f�[�^.��������敪 != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɓ�������敪��ǉ�����B
        if (l_request.taxTypeDiv != null)
        {
            if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxTypeDiv))
            {
                // ���N�G�X�g�f�[�^.��������敪 == "���"�̏ꍇ�A
                // [add()�Ɏw�肷�����]�u0�F��ʌ����v
                l_queryContainerList.add(WEB3SpecialAccDef.NORMAL);
            }

            if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxTypeDiv))
            {
                // ���N�G�X�g�f�[�^.��������敪 == "����"�̏ꍇ�A
                // [add()�Ɏw�肷�����]�u1�F��������i���򒥎��Ȃ��j�v
                l_queryContainerList.add(WEB3SpecialAccDef.SPECIAL);

                // [add()�Ɏw�肷�����]�u2�F��������i���򒥎�����j�v
                l_queryContainerList.add(WEB3SpecialAccDef.SPECIAL_WITHHOLD);
            }
        }

        //�X�j�@@�ڋq���i�J�i�j�����ǉ� ���w�肪����ꍇ�̂݁C�B������ 
        //�i���N�G�X�g�f�[�^.�ڋq���i�J�i�j != null�j�̏ꍇ�A 
        if (l_request.accountFamilyNameKana != null)
        {
            l_queryContainerList.add('%' + l_request.accountFamilyNameKana + '%');
        } 

        //�P�O�j�@@�ڋq���i�J�i�j�����ǉ� ���w�肪����ꍇ�̂݁C�B������ 
        //�i���N�G�X�g�f�[�^.�ڋq���i�J�i�j != null�j�̏ꍇ�A
        if (l_request.accountNameKana != null)
        {
            l_queryContainerList.add('%' + l_request.accountNameKana + '%');
        } 
        
        //�P�P�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�����������i���j != null�j�̏ꍇ�A
        if (l_request.infoClaimDateFrom != null)
        {
            l_queryContainerList.add(WEB3DateUtility.formatDate
                (l_request.infoClaimDateFrom, "yyyyMMdd"));
        }  

        //�P�Q�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�����������i���j != null�j�̏ꍇ�A
        if (l_request.infoClaimDateTo != null)
        {
            l_queryContainerList.add(WEB3DateUtility.formatDate
                (l_request.infoClaimDateTo, "yyyyMMdd"));
        }  
        
        //�P�R�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�����J�ݓ��i���j != null�j�̏ꍇ�A
        if (l_request.accountOpenDateFrom != null)
        {
            l_queryContainerList.add(WEB3DateUtility.formatDate
                (l_request.accountOpenDateFrom, "yyyyMMdd"));
        }  

        //�P�S�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂� 
        //�i���N�G�X�g�f�[�^.�����J�ݓ��i���j != null�j�̏ꍇ�A
        if (l_request.accountOpenDateTo != null)
        {
            l_queryContainerList.add(WEB3DateUtility.formatDate
                (l_request.accountOpenDateTo, "yyyyMMdd"));
        } 

        //�Q�O�j�@@�����ғo�^�敪�����ǉ� 
        //�i���N�G�X�g�f�[�^.�����ғo�^�敪 != null�j�̏ꍇ�A�߂�l�ҏW�p�C���X�^���X�ɓ����ғo�^�敪��ǉ�����B 
        //�u�i���N�G�X�g�f�[�^.�����ғo�^�敪 != null�j�@@���@@�i���N�G�X�g�f�[�^.�����敪=0�F�l�A�J�E���g�j�̏ꍇ�v
        //[add()�Ɏw�肷�����] 
        //���N�G�X�g�f�[�^.�����ғo�^�敪 
        //
        //�u�i���N�G�X�g�f�[�^.�����ғo�^�敪 != null�j�@@���@@�i���N�G�X�g�f�[�^.�����敪=1�F�@@�l�A�J�E���g�j�̏ꍇ�v
        //[add()�Ɏw�肷�����] 
        //���N�G�X�g�f�[�^.�����ғo�^�敪
        if (l_request.insiderDiv != null)
        {
            l_queryContainerList.add(l_request.insiderDiv);
        }

        //�P�T�j�@@�z���ԋp 
        String[] l_strQueryContainers = new String[l_queryContainerList.size()];
        l_queryContainerList.toArray(l_strQueryContainers); 
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
        }
    
    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�����������ҏW����B <BR>
     * <BR>
     * �����̃\�[�g�L�[���������ڂɊY����������J�݌����q�񕨗������g�p���A<BR> 
     * �\�[�g�L�[�̎w��̒ʂ�A�\�[�g����������iorder by��j��ҏW���ԋp����B <BR>
     * <BR>
     * ���@@�\�[�g�L�[�Ɏw�肳��鍀�ڂ͈ȉ��̒ʂ�B<BR>
     * �@@ �����J�ݏ�.���X�R�[�h �i�����J�݌����q.���X�R�[�h�j<BR>
     * �@@ �����J�ݏ�.�ڋq�R�[�h �i�����J�݌����q.�����R�[�h�j<BR>
     * �@@ �����J�ݏ�.���ʃR�[�h �i�����J�݌����q.���ʃR�[�h�j<BR>
     * �@@ �����J�ݏ�.�����������i�����J�݌����q.�������������j<BR>
     * �@@ �����J�ݏ�.�����J�ݓ��i�����J�݌����q.�����o�^���j<BR>
     * @@param l_sortKeys - �����J�݃\�[�g�L�[�̔z��
     * 
     * @@return String
     * @@roseuid 419C670C014B
     */
    protected String createSortCond(WEB3AccOpenSortKey[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = " createSortCond(WEB3AccOpenSortKey[]) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKeys == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
        
        String l_strSortCond = " ";
        
        int l_intSortKeyCnt = l_sortKeys.length;
        
        for (int i = 0; i < l_intSortKeyCnt; i++)
        {
            WEB3AccOpenSortKey l_sortKey = l_sortKeys[i];
            
            if (l_sortKey == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //�����J�ݏ�.���X�R�[�h �i�����J�݌����q.���X�R�[�h�j 
            if (WEB3AccountOpenKeyItemDef.BRANCH_CODE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += WEB3AccountOpenExpAccountOpenSymbolNameDef.BRANCH_CODE;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            //�����J�ݏ�.�ڋq�R�[�h �i�����J�݌����q.�����R�[�h�j 
            else if (WEB3AccountOpenKeyItemDef.ACCOUNT_CODE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            //�����J�ݏ�.���ʃR�[�h �i�����J�݌����q.���ʃR�[�h�j
            else if (WEB3AccountOpenKeyItemDef.REQUEST_NUMBER.equals(l_sortKey.keyItem))
            {
                l_strSortCond += WEB3AccountOpenExpAccountOpenSymbolNameDef.ACC_OPEN_REQUEST_NUMBER;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            //�����J�ݏ�.�����������i�����J�݌����q.�������������j
            else if (WEB3AccountOpenKeyItemDef.INFO_CLAIM_DATE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += WEB3AccountOpenExpAccountOpenSymbolNameDef.INFOMATION_CLAIM_DATETIME;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            } 
            //�����J�ݏ�.�����J�ݓ��i�����J�݌����q.�����o�^���j
            else if (WEB3AccountOpenKeyItemDef.ACCOUNT_OPEN_DATE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_OPEN_DATE;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
        }
        
        l_strSortCond = l_strSortCond.substring(0, l_strSortCond.length() - 2) + " ";
        
        log.exiting(STR_METHOD_NAME);
        
        return l_strSortCond;
    }
    
    /**
     * (get��p�U��������c�����v)<BR>
     * ��p�U��������̎c���̍��v�l���擾����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�@@�ȉ��̏������w�肵�āA�y��p�U������������e�[�u���z����������B<BR>
     *     �Y�����郌�R�[�h�����擾����B <BR>
     * <BR>
     * �@@�@@------------------------------- <BR>
     * �@@�@@������������<BR>
     * <BR>
     * �@@�@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h and  <BR>
     * �@@�@@�@@�@@��s�R�[�h = ����.��p�U���������s�R�[�h and <BR>
     * �@@�@@�@@�@@�X�e�[�^�X = "���g�p���R�[�h"  <BR>
     * <BR>
     * �@@�@@------------------------------- <BR>
     * <BR>
     * �Q�j�@@�P�j�̌��ʂ�ԋp����B 
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strExclusiveAccountBankCode - (��p�U���������s�R�[�h)<BR>
     * ��p�U���������s�R�[�h<BR>
     * @@return int
     * @@throws WEB3BaseException
     * @@roseuid 437D2EB90349
     */
    protected int getExclusiveAccountTotalNumber(
        String l_strInstitutionCode,
        String l_strExclusiveAccountBankCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExclusiveAccountTotalNumber(String, String) ";
        log.entering(STR_METHOD_NAME);

        int l_intRowCnt = 0;

        try
        {            
            String l_strWhere = 
                " institution_code = ? " + 
                " and fin_institution_code = ? " +
                " and status = ? ";

            Object[] l_obj = 
                {l_strInstitutionCode, 
                 l_strExclusiveAccountBankCode, 
                 WEB3ExclusiveUseAccountStatusDef.UNUSED_RECORD};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();  
 
            l_intRowCnt = l_queryProcessor.doGetCountQuery(
                ExclusiveUseAccountCondRow.TYPE, 
                l_strWhere, 
                l_obj);
            log.debug("��p�U������������u���g�p�v�����F " + l_intRowCnt);

        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
            
        return l_intRowCnt;
    }
    
    /**
     * (get��p�U��������x���敪)<BR>
     * �،���Ђ��Ƃ̐ݒ茏���ɂ��A<BR>
     * ��p�U��������̎c�����v���s�����Ă��Ȃ������肵�A<BR>
     * �x���敪��ԋp����B<BR>
     * <BR>
     * [�߂�l]<BR>
     * 0�F�x���Ȃ�<BR>
     * 1�F����<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�ȉ��̏����ŁA�V�X�e���v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���� = ����.�،���ЃR�[�h + "_MIN_ACCOUNT"<BR>
     * <BR>
     * �Q�j�@@�x���敪����<BR>
     * �@@�Q�|�P�j�P�j�Ń��R�[�h���擾�ł����ꍇ�A<BR>
     * �@@�@@�P�j�Ŏ擾�����l �� ����.��p�U��������c���̏ꍇ�A"����"��ԋp����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�P�j�Ń��R�[�h���擾�ł��Ȃ������ꍇ�A<BR>
     * �@@�@@�ȉ��̏����ŁA�y�V�X�e���v���t�@@�����X�e�[�u���z���烌�R�[�h���擾����B<BR>
     * [����] <BR>  
     * �@@���� = ����.�،���ЃR�[�h + "_MIN_ACCOUNT" + "_" + ����.��p�U���������s�R�[�h <BR>
     * <BR>
     * �Q�|�R�j�@@�Q�|�Q�j�Ń��R�[�h���擾�ł����ꍇ�A<BR>  
     * �@@�Q�|�Q�j�Ŏ擾�����l �� ����.��p�U��������c���̏ꍇ�A"����"��ԋp����B
     * <BR> 
     * �Q�|�T�j�@@�ȊO�A"�x���Ȃ�"��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strExclusiveAccountBankCode - (��p�U���������s�R�[�h)<BR>
     * ��p�U���������s�R�[�h<BR>
     * @@param l_intExclusiveAccountNumber - (��p�U��������c��)<BR>
     * ��p�U��������c��<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 437D64E001A9
     */
    protected String getExclusiveAccountWarningDiv(
        String l_strInstitutionCode, 
        String l_strExclusiveAccountBankCode,
        int l_intExclusiveAccountNumber) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExclusiveAccountWarningDiv(String, String, int) ";
        log.entering(STR_METHOD_NAME);

        try
        {
            double l_dblValue = 0;

            //�P�j�@@DB����  
            //���� = ����.�،���ЃR�[�h + "_MIN_ACCOUNT"
            String l_strName = l_strInstitutionCode + WEB3SystemPreferencesNameDef.MIN_ACCOUNT;
            
            SystemPreferencesRow l_sysPreRow = SystemPreferencesDao.findRowByName(l_strName);
            //�Ń��R�[�h���擾�ł����ꍇ�A�A
            //�P�j�Ŏ擾�����l �� ����.��p�U��������c���̏ꍇ�A"����"��ԋp����B
            if (l_sysPreRow != null)
            {
                String l_strValue = l_sysPreRow.getValue();
                if (l_strValue != null && WEB3StringTypeUtility.isNumber(l_strValue))
                {
                    l_dblValue = Double.parseDouble(l_strValue);
                    
                    if(l_dblValue >= l_intExclusiveAccountNumber)
                    {
                        log.exiting(STR_METHOD_NAME);
                        return WEB3AccOpenExclusiveAccountWarningDivDef.NOTICE;
                    }
                    else
                    {
                        log.exiting(STR_METHOD_NAME);
                        return WEB3AccOpenExclusiveAccountWarningDivDef.NOT_WARNING;
                    }
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return WEB3AccOpenExclusiveAccountWarningDivDef.NOT_WARNING;
                }
            }
            //�Ń��R�[�h���擾�ł��Ȃ������ꍇ�A
            //�Ŏ擾�����l �� ����.��p�U��������c���̏ꍇ�A"����"��ԋp����B
            else
            {
                l_strName = 
                    l_strInstitutionCode + WEB3SystemPreferencesNameDef.MIN_ACCOUNT + "_" + l_strExclusiveAccountBankCode;
                l_sysPreRow = SystemPreferencesDao.findRowByName(l_strName);
                if(l_sysPreRow != null)
                {
                    String l_strValue = l_sysPreRow.getValue();
                    if (l_strValue != null && WEB3StringTypeUtility.isNumber(l_strValue))
                    {
                        l_dblValue = Double.parseDouble(l_strValue);
                        
                        if(l_dblValue >= l_intExclusiveAccountNumber)
                        {
                            log.exiting(STR_METHOD_NAME);
                            return WEB3AccOpenExclusiveAccountWarningDivDef.NOTICE;
                        }
                        else
                        {
                            log.exiting(STR_METHOD_NAME);
                            return WEB3AccOpenExclusiveAccountWarningDivDef.NOT_WARNING;
                        }
                    }
                    else
                    {
                        log.exiting(STR_METHOD_NAME);
                        return WEB3AccOpenExclusiveAccountWarningDivDef.NOT_WARNING;
                    }
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return WEB3AccOpenExclusiveAccountWarningDivDef.NOT_WARNING;
                }
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

    }
    
    /**
     * (set���X�v���t�@@�����X)<BR>
     * ���X�v���t�@@�����X�̎擾���s���B <BR>
     * <BR>
     *�P�j ���X�p�v���t�@@�����X�e�[�u������l���擾����B <BR>
     * <BR>
     *�@@[����] <BR>
     *�@@�@@���XID�ˁi�����j���XID <BR>
     *�@@�@@�v���t�@@�����X���ˁi�����j���X�v���t�@@�����X�� <BR>
     *�@@�@@�v���t�@@�����X���̘A�ԁ�1 <BR>
     *  <BR>
     *  <BR>
     *�@@[�ݒ�l] <BR>
     *�@@�@@"0"�i�`�F�b�N�s�v-�A���[�g�\����-�R���҂�UPDATE���j OR "" �� 0 ��this.���X�v���t�@@�����X�ɐݒ� <BR>
     *�@@�@@"1"�i�`�F�b�N���s-�A���[�g�\���L-�R���҂�UPDATE���j �� 1 ��this.���X�v���t�@@�����X�ɐݒ� <BR>
     *�@@�@@"2"�i�`�F�b�N���s-�A���[�g�\����-�R���҂�UPDATE�L�j �� 2 ��this.���X�v���t�@@�����X�ɐݒ� <BR>
     *�@@�@@"3"�i�`�F�b�N���s-�A���[�g�\���L-�R���҂�UPDATE�L�j �� 3 ��this.���X�v���t�@@�����X�ɐݒ� <BR>
     *�@@�@@�i���R�[�h���I������Ȃ� �� 0 ��this.���X�v���t�@@�����X�ɐݒ�j<BR> 
     *@@param l_strBranchPreferenceName - (���X�v���t�@@�����X��)<BR>
     *���X�v���t�@@�����X���B<BR>
     *@@param l_lngBranchId - (���XID)<BR>
     *���XID�B<BR>
     * @@throws WEB3BaseException 
     */
    private void setBranchPreferences(
        String l_strBranchPreferenceName, long l_lngBranchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setBranchPreference(String, long) ";
        log.entering(STR_METHOD_NAME);
        //�P�j ���X�p�v���t�@@�����X�e�[�u������l���擾����B
        //�@@[����]
        //�@@�@@���XID�ˁi�����j���XID
        //�@@�@@�v���t�@@�����X���ˁi�����j���X�v���t�@@�����X��
        //�@@�@@�v���t�@@�����X���̘A�ԁ�1
        BranchPreferencesParams l_branchPreferencesParams = null;
        BranchPreferencesRow l_branchPreferencesRow = null;
        
        try 
        {
            l_branchPreferencesRow = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId, 
                    l_strBranchPreferenceName, 
                    1);
        } 
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //[�ݒ�l] 
        // "0"�i�`�F�b�N�s�v-�A���[�g�\����-�R���҂�UPDATE���j OR "" �� 0 ��this.���X�v���t�@@�����X�ɐݒ� 
        // "1"�i�`�F�b�N���s-�A���[�g�\���L-�R���҂�UPDATE���j �� 1 ��this.���X�v���t�@@�����X�ɐݒ� 
        // "2"�i�`�F�b�N���s-�A���[�g�\����-�R���҂�UPDATE�L�j �� 2 ��this.���X�v���t�@@�����X�ɐݒ� 
        // "3"�i�`�F�b�N���s-�A���[�g�\���L-�R���҂�UPDATE�L�j �� 3 ��this.���X�v���t�@@�����X�ɐݒ� 
        // �i���R�[�h���I������Ȃ� �� 0 ��this.���X�v���t�@@�����X�ɐݒ�j
        if (l_branchPreferencesRow == null)
        {
            this.branchPreferences = 0;
        }
        else
        {      
            l_branchPreferencesParams = new BranchPreferencesParams(l_branchPreferencesRow);
            
            if (WEB3CheckAlartUpdateDef.DEFAULT.equals(l_branchPreferencesParams.getValue()) 
                || "".equals(l_branchPreferencesParams.getValue()))
            {
                this.branchPreferences = 0;
            }
            else if (WEB3CheckAlartUpdateDef.CHECK_ALART_UPD_1.equals(l_branchPreferencesParams.getValue()))
            {
                this.branchPreferences = 1;
            }
            else if (WEB3CheckAlartUpdateDef.CHECK_ALART_UPD_2.equals(l_branchPreferencesParams.getValue()))
            {
                this.branchPreferences = 2;
            }
            else if (WEB3CheckAlartUpdateDef.CHECK_ALART_UPD_3.equals(l_branchPreferencesParams.getValue()))
            {
                this.branchPreferences = 3;
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���X�ݒ�ʃ`�F�b�N)<BR>
     * ���X�ݒ�ʃ`�F�b�N���s���B<BR> 
     * <BR>
     * �P�j�x�����b�Z�[�W���X�g�iArrayList�j�𐶐� <BR>
     * <BR>
     * <BR>
     * �Q�j���XID�y�ь����敪���擾����B<BR> 
     *�@@�Q�|�P�j���XID���擾����B <BR>
     *�@@�@@���X�I�u�W�F�N�g(�Ǘ���.get�،����(),�����J�݌����q.get���X�R�[�h()).getBranchId()�̖߂�l <BR>
     * <BR>
     *�@@�Q�|�Q�j�����敪���擾����B<BR> 
     *�@@�@@�����J�݌����q.get�����敪()�̖߂�l <BR>
     * <BR>
     * <BR>
     * �R�j ����ڋq�`�F�b�N���s���B�i�����J�݌����q.validate�d���ڋq()�j<BR> 
     *�@@�R�|�P�j ���X�v���t�@@�����X���擾����B <BR>
     *�@@�@@[set���X�v���t�@@�����X()�ɐݒ肷�����] <BR>
     *�@@�@@�@@���X�v���t�@@�����X���F�Q�|�Q�j�Ŏ擾���������敪=="0"�̏ꍇ"accopen.examination.duplicateaccount.indiv" <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Q�|�Q�j�Ŏ擾���������敪=="1"�̏ꍇ"accopen.examination.duplicateaccount.corp" <BR>
     *�@@�@@�@@���XID�F �Q�|�P�j�Ŏ擾�������XID <BR>
     * <BR>
     *�@@�R�|�Q�j  this.���X�v���t�@@�����X�l !=0�i�`�F�b�N�s�v�j �̏ꍇ�A�ȉ��������s���B<BR> 
     *�@@�@@�R�|�Q�|1�j �����J�݌����q.validate�d���ڋq()�����s����B <BR>
     *�@@�@@�@@[�����J�݌����q.validate�d���ڋq()�Ɏw�肷�����] <BR>
     *�@@�@@�@@���X�v���t�@@�����X�Fthis.���X�v���t�@@�����X <BR>
     * <BR>
     *�@@�@@�@@[�߂�l] <BR>
     *�@@�@@�@@�@@����ڋq�x�����b�Z�[�W�R�[�h�iString�j <BR>
     * <BR>
     *�@@�@@�R�|�Q�|�Q�j this.���X�v���t�@@�����X�l !=2�i�A���[�g�\�����j �̏ꍇ�A�ȉ��������s���B<BR> 
     *�@@�@@�@@����ڋq�x�����b�Z�[�W�R�[�h���ԋp���ꂽ�ꍇ�i�����J�݌����q.validate�d���ڋq() != null�j�A<BR> 
     *�@@�@@�@@����ڋq�x�����b�Z�[�W�R�[�h���x�����b�Z�[�W���X�g�iArrayList�j�ɒǉ�����B <BR>
     *�@@�@@�@@[add()�Ɏw�肷�����]<BR> 
     *�@@�@@�@@�@@arg0�i����ڋq�x�����b�Z�[�W�R�[�h�j�F�@@�����J�݌����q.validate�d���ڋq()�̖߂�l <BR>
     * <BR>
     * <BR>
     * �S�j Y�q�`�F�b�N���s���B�i�����J�݌����q.validate�x�q()�j <BR>
     *�@@�S�|�P�j ���X�v���t�@@�����X���擾����B <BR>
     *�@@�@@[set���X�v���t�@@�����X()�ɐݒ肷�����] <BR>
     *�@@�@@�@@���X�v���t�@@�����X���F�Q�|�Q�j�Ŏ擾���������敪=="0"�̏ꍇ"accopen.examination.yellowaccount.indiv" <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Q�|�Q�j�Ŏ擾���������敪=="1"�̏ꍇ"accopen.examination.yellowaccount.corp" <BR>
     *�@@�@@�@@���XID�F �Q�|�P�j�Ŏ擾�������XID <BR>
     * <BR>
     *�@@�S�|�Q�j  this.���X�v���t�@@�����X�l !=0�i�`�F�b�N�s�v�j �̏ꍇ�A�ȉ��������s���B <BR>
     *�@@�@@�S�|�Q�|1�j �����J�݌����q.validate�x�q()�����s����B <BR>
     *�@@�@@�@@[�����J�݌����q.validate�x�q()�Ɏw�肷�����] <BR>
     *�@@�@@�@@���X�v���t�@@�����X�Fthis.���X�v���t�@@�����X <BR>
     * <BR>
     *�@@�@@�@@[�߂�l] <BR>
     *�@@�@@�@@�@@Y�q�x�����b�Z�[�W�R�[�h�iString�j <BR>
     * <BR>
     *�@@�@@�S�|�Q�|�Q�j this.���X�v���t�@@�����X�l !=2�i�A���[�g�\�����j �̏ꍇ�A�ȉ��������s���B<BR> 
     *�@@�@@�@@Y�q�x�����b�Z�[�W�R�[�h���ԋp���ꂽ�ꍇ�i�����J�݌����q.validate�x�q() != null�j�A<BR> 
     *�@@�@@�@@Y�q�x�����b�Z�[�W�R�[�h���x�����b�Z�[�W���X�g�iArrayList�j�ɒǉ�����B <BR>
     *�@@�@@�@@[add()�Ɏw�肷�����] <BR>
     *�@@�@@�@@�@@arg0�iY�q�x�����b�Z�[�W�R�[�h�j�F�@@�����J�݌����q.validate�x�q()�̖߂�l <BR>
     * <BR>
     * <BR>
     * �T�j ���[���A�h���X�`�F�b�N���s���B�i�����J�݌����q.validate���[���A�h���X()�j <BR>
     *�@@�T�|�P�j ���X�v���t�@@�����X���擾����B <BR>
     *�@@�@@[set���X�v���t�@@�����X()�ɐݒ肷�����] <BR>
     *�@@�@@�@@���X�v���t�@@�����X���F�Q�|�Q�j�Ŏ擾���������敪=="0"�̏ꍇ"accopen.examination.mailaddress.indiv" <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Q�|�Q�j�Ŏ擾���������敪=="1"�̏ꍇ"accopen.examination.mailaddress.corp" <BR>
     *�@@�@@�@@���XID�F �Q�|�P�j�Ŏ擾�������XID <BR>
     * <BR>
     *�@@�T�|�Q�j  this.���X�v���t�@@�����X�l !=0�i�`�F�b�N�s�v�j �̏ꍇ�A�ȉ��������s���B <BR>
     *�@@�@@�T�|�Q�|1�j �����J�݌����q.validate���[���A�h���X()�����s����B <BR>
     *�@@�@@�@@[�����J�݌����q.validate���[���A�h���X()�Ɏw�肷�����] <BR>
     *�@@�@@�@@���X�v���t�@@�����X�Fthis.���X�v���t�@@�����X <BR>
     * <BR>
     *�@@�@@�@@[�߂�l] <BR>
     *�@@�@@�@@�@@���[���A�h���X�`�F�b�N�x�����b�Z�[�W�R�[�h�iString�j <BR>
     * <BR>
     *�@@�@@�T�|�Q�|�Q�j this.���X�v���t�@@�����X�l !=2�i�A���[�g�\�����j �̏ꍇ�A�ȉ��������s���B<BR> 
     *�@@�@@�@@���[���A�h���X�`�F�b�N�x�����b�Z�[�W�R�[�h���ԋp���ꂽ�ꍇ�i�����J�݌����q.validate���[���A�h���X() != null�j�A<BR> 
     *�@@�@@�@@���[���A�h���X�`�F�b�N�x�����b�Z�[�W�R�[�h���x�����b�Z�[�W���X�g�iArrayList�j�ɒǉ�����B <BR>
     *�@@�@@�@@[add()�Ɏw�肷�����] <BR>
     *�@@�@@�@@�@@arg0�i���[���A�h���X�`�F�b�N�x�����b�Z�[�W�R�[�h�j�F�@@�����J�݌����q.validate���[���A�h���X()�̖߂�l <BR>
     * <BR>
     * <BR>
     * �U�j �d�b�ԍ��`�F�b�N���s���B�i�����J�݌����q.validate�d�b�ԍ�()�j<BR> 
     *�@@�U�|�P�j ���X�v���t�@@�����X���擾����B <BR>
     *�@@�@@[set���X�v���t�@@�����X()�ɐݒ肷�����] <BR>
     *�@@�@@�@@���X�v���t�@@�����X���F�Q�|�Q�j�Ŏ擾���������敪=="0"�̏ꍇ"accopen.examination.telno.indiv" <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Q�|�Q�j�Ŏ擾���������敪=="1"�̏ꍇ"accopen.examination.telno.corp" <BR>
     *�@@�@@�@@���XID�F �Q�|�P�j�Ŏ擾�������XID <BR>
     * <BR>
     *�@@�U�|�Q�j  this.���X�v���t�@@�����X�l !=0�i�`�F�b�N�s�v�j �̏ꍇ�A�ȉ��������s���B <BR>
     *�@@�@@�U�|�Q�|1�j �����J�݌����q.validate�d�b�ԍ�()�����s����B <BR>
     *�@@�@@�@@[�����J�݌����q.validate�d�b�ԍ�()�Ɏw�肷�����] <BR>
     *�@@�@@�@@���X�v���t�@@�����X�Fthis.���X�v���t�@@�����X <BR>
     * <BR>
     *�@@�@@�@@[�߂�l] <BR>
     *�@@�@@�@@�@@�d�b�ԍ��`�F�b�N�x�����b�Z�[�W�R�[�h�iArrayList�j <BR>
     * <BR>
     *�@@�@@�U�|�Q�|�Q�j this.���X�v���t�@@�����X�l !=2�i�A���[�g�\�����j �̏ꍇ�A�ȉ��������s���B <BR>
     *�@@�@@�@@�d�b�ԍ��`�F�b�N�x�����b�Z�[�W�R�[�h���ԋp���ꂽ�ꍇ�i�����J�݌����q.validate�d�b�ԍ�()�̒��� >0�j�A <BR>
     *�@@�@@�@@�d�b�ԍ��`�F�b�N�x�����b�Z�[�W�R�[�h���x�����b�Z�[�W���X�g�iArrayList�j�ɒǉ�����B <BR>
     *�@@�@@�@@[addAll()�Ɏw�肷�����] <BR>
     *�@@�@@�@@�@@arg0�i�d�b�ԍ��`�F�b�N�x�����b�Z�[�W�R�[�h�j�F�@@�����J�݌����q.validate�d�b�ԍ�()�̖߂�l <BR>
     * <BR>
     * �V�j �x�����b�Z�[�W���X�g��ԋp�B<BR> 
     * @@param l_expAccountOpen - (�����J�݌����q)<BR>
     * �����J�݌����q�I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException 
     * @@return Collection
     * @@throws  
     */
    protected Collection validateBranchSetCheck(
        WEB3AccOpenExpAccountOpen l_expAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateBranchSetCheck(WEB3AccOpenExpAccountOpen) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�x�����b�Z�[�W���X�g�iArrayList�j�𐶐�
        Collection l_collection = new ArrayList();
        
        //�Q�j���XID�y�ь����敪���擾����B 
        //�Q�|�P�j���XID���擾����B 
        //  ���X�I�u�W�F�N�g(�Ǘ���.get�،����(),�����J�݌����q.get���X�R�[�h()).getBranchId()�̖߂�l 
        //�Q�|�Q�j�����敪���擾����B 
        //  �����J�݌����q.get�����敪()�̖߂�l        
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        Branch l_branch = null;

        try 
        {
            l_branch = l_accountManager.getBranch(
                l_admin.getInstitution(), l_expAccountOpen.getBranchCode());
        } 
        catch (NotFoundException l_ex) 
        {
            log.error("���X�C���X�^���X�𐶐�����:", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  

        long l_lngBranchId = l_branch.getBranchId();
        String l_strAccountDiv = l_expAccountOpen.getAccountDiv();
        
        //�R�j ����ڋq�`�F�b�N���s���B�i�����J�݌����q.validate�d���ڋq()�j 
        //�R�|�P�j ���X�v���t�@@�����X���擾����B
        String l_branchPreferencesName = null;
        if (WEB3AccOpenAccountOpenDivDef.NOT_OPEN.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_DUPLO_INDV;
        }
        else if (WEB3AccOpenAccountOpenDivDef.OPEN_COMPLETE.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_DUPLO_COP;
        }
        setBranchPreferences(l_branchPreferencesName, l_lngBranchId);
        
        //�R�|�Q�j this.���X�v���t�@@�����X�l !=0�i�`�F�b�N�s�v�j �̏ꍇ�A�ȉ��������s���B 
        if (branchPreferences != 0)
        {
            //�R�|�Q�|1�j �����J�݌����q.validate�d���ڋq()�����s����B
            String l_strDuplicateAccountMessageCode = 
                l_expAccountOpen.validateDuplicateAccount(branchPreferences);
            
            //�R�|�Q�|�Q�j this.���X�v���t�@@�����X�l !=2�i�A���[�g�\�����j �̏ꍇ�A�ȉ��������s���B
            if (branchPreferences != 2 && l_strDuplicateAccountMessageCode != null)
            {
                l_collection.add(l_strDuplicateAccountMessageCode);
            }
        }
        
        //�S�j Y�q�`�F�b�N���s���B�i�����J�݌����q.validate�x�q()�j 
        //�S�|�P�j ���X�v���t�@@�����X���擾����B 
        if (WEB3AccOpenAccountOpenDivDef.NOT_OPEN.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_YACC_INDV;
        }
        else if (WEB3AccOpenAccountOpenDivDef.OPEN_COMPLETE.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_YACC_COP;
        }
        setBranchPreferences(l_branchPreferencesName, l_lngBranchId);
        
        //�S�|�Q�j this.���X�v���t�@@�����X�l !=0�i�`�F�b�N�s�v�j �̏ꍇ�A�ȉ��������s���B 

        if (branchPreferences != 0)
        {
            //�S�|�Q�|1�j �����J�݌����q.validate�x�q()�����s����B
            String l_strYellowAccountMessageCode =
                l_expAccountOpen.validateYellowAccount(branchPreferences);
            
            //�S�|�Q�|�Q�j this.���X�v���t�@@�����X�l !=2�i�A���[�g�\�����j �̏ꍇ�A�ȉ��������s���B 
            if (branchPreferences != 2 && l_strYellowAccountMessageCode != null)
            {
                l_collection.add(l_strYellowAccountMessageCode);
            }
        }
        
        //�T�j ���[���A�h���X�`�F�b�N���s���B�i�����J�݌����q.validate���[���A�h���X()�j 
        //�T�|�P�j ���X�v���t�@@�����X���擾����B 
        if (WEB3AccOpenAccountOpenDivDef.NOT_OPEN.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_MAIL_INDV;
        }
        else if (WEB3AccOpenAccountOpenDivDef.OPEN_COMPLETE.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_MAIL_COP;
        }
        setBranchPreferences(l_branchPreferencesName, l_lngBranchId);
        
        //�T�|�Q�j this.���X�v���t�@@�����X�l !=0�i�`�F�b�N�s�v�j �̏ꍇ�A�ȉ��������s���B 
 
        if (branchPreferences != 0)
        {
            //�T�|�Q�|1�j �����J�݌����q.validate���[���A�h���X()�����s����B
            String l_strMailAddressMessageCode =
                l_expAccountOpen.validateMailAddress(branchPreferences);
            if (branchPreferences != 2 && l_strMailAddressMessageCode != null)
            {
                //�T�|�Q�|�Q�j this.���X�v���t�@@�����X�l !=2�i�A���[�g�\�����j �̏ꍇ�A�ȉ��������s���B
                l_collection.add(l_strMailAddressMessageCode);
            }
        }
        
        //�U�j �d�b�ԍ��`�F�b�N���s���B�i�����J�݌����q.validate�d�b�ԍ�()�j 
        //�U�|�P�j ���X�v���t�@@�����X���擾����B 
        if (WEB3AccOpenAccountOpenDivDef.NOT_OPEN.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_TEL_INDV;
        }
        else if (WEB3AccOpenAccountOpenDivDef.OPEN_COMPLETE.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_TEL_COP;
        }
        setBranchPreferences(l_branchPreferencesName, l_lngBranchId);
        
        //�U�|�Q�j this.���X�v���t�@@�����X�l !=0�i�`�F�b�N�s�v�j �̏ꍇ�A�ȉ��������s���B 
        if (branchPreferences != 0)
        {
            //�U�|�Q�|1�j �����J�݌����q.validate�d�b�ԍ�()�����s����B 
            ArrayList l_arrayListTelephoneMessageCode =
                l_expAccountOpen.validateTelephone(branchPreferences, l_strAccountDiv);
            
            //�U�|�Q�|�Q�j this.���X�v���t�@@�����X�l !=2�i�A���[�g�\�����j �̏ꍇ�A�ȉ��������s���B 
            if (branchPreferences != 2 
                && l_arrayListTelephoneMessageCode != null 
                && l_arrayListTelephoneMessageCode.size() > 0)
            {
                l_collection.addAll(l_arrayListTelephoneMessageCode);
            }
        }
        
        //�V�j �x�����b�Z�[�W���X�g��ԋp�B 
        log.exiting(STR_METHOD_NAME);
        return l_collection;
    }
    
    /**
     * (validate�����̔�)<BR>
     *�����̔ԑI�����ɁA�ڋq���l�ō������Z�҂��`�F�b�N���� <BR>
     *<BR>
     *�P�j����.�����J�ݐ\�����.�ڋq�R�[�h�����̔ԃt���O != 1(�����̔Ԃ��s��)�̏ꍇ<BR>
     *<BR>
     *�@@���\�b�h�𐳏�I������ <BR>
     *<BR>
     * �Q�j�،����ID���擾����<BR>
     * <BR>
     * �R�j�ȉ��̏����ŁA�،���Ѓv���t�@@�����X�e�[�u�����烌�R�[�h���擾����<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�،����ID = �Q�j�Ŏ擾�����،����ID<BR>
     * �@@�@@�v���t�@@�����X�� = "accountopen.corporate.auto.div"<BR>
     * �@@�@@�v���t�@@�����X�̒l = "1"<BR>
     * <BR>
     * �S�j���R�[�h���擾�ł��Ȃ��ꍇ�A�ȉ��̃`�F�b�N���s��<BR>
     * �@@�@@����.�����J�ݐ\�����.�����敪 != 0:�l�A�J�E���g �̏ꍇ<BR>
     * �@@�@@�u�����̔Ԃł��܂���B�v�̗�O���X���[����<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@: BUSINESS_ERROR_02610<BR>
     * <BR>
     * �T�j����.�����J�ݐ\�����.���Z�^�񋏏Z�敪 != 0:�Z���ҁ@@�̏ꍇ<BR>
     * �@@�@@�u�����̔Ԃł��܂���B�v�̗�O���X���[����<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@: BUSINESS_ERROR_02610<BR>
     * <BR>
     * @@param l_accOpenApplyInfo - (�����J�ݐ\�����)<BR>
     * �����J�ݐ\�����I�u�W�F�N�g<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@throws WEB3BaseException 
     */
    private void validateAuto(WEB3AccOpenApplyInfo l_accOpenApplyInfo, String l_strInstitutionCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAuto(WEB3AccOpenApplyInfo, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j����.�����J�ݐ\�����.�ڋq�R�[�h�����̔ԃt���O != 1(�����̔Ԃ��s��)�̏ꍇ���\�b�h�𐳏�I������
        if (WEB3AccOpenAccountCodeAutoFlagDef.AUTO.equals(l_accOpenApplyInfo.accountCodeAutoFlag))
        {
            try
            {
                InstitutionRow l_institutionRow =
                    InstitutionDao.findRowByInstitutionCode(l_strInstitutionCode);
                if (l_institutionRow == null)
                {
                    log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                }
                long l_lngInstitutionId = l_institutionRow.getInstitutionId();

                //�ȉ��̏����ŁA�،���Ѓv���t�@@�����X�e�[�u�����烌�R�[�h���擾����
                //�@@[����]
                //�@@�،����ID = �Q�j�Ŏ擾�����،����ID
                //�@@�v���t�@@�����X�� = "accountopen.corporate.auto.div"
                //�@@�v���t�@@�����X�̒l = "1"
                String l_strWhere = " institution_id = ? and name = ? and value = ? ";
                Object[] l_bindVars =
                    {
                        new Long(l_lngInstitutionId),
                        WEB3InstitutionPreferencesNameDef.ACCOUNTOPEN_CORPORATE_AUTO_DIV,
                        new Integer(1)
                    };

                List l_lisInstitutionPreferences =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        InstitutionPreferencesRow.TYPE,
                        l_strWhere,
                        l_bindVars);

                if (l_lisInstitutionPreferences == null
                    || l_lisInstitutionPreferences.size() == 0)
                {
                    //���R�[�h���擾�ł��Ȃ��ꍇ�A�ȉ��̃`�F�b�N���s��
                    //  ����.�����J�ݐ\�����.�����敪 != 0:�l�A�J�E���g �̏ꍇ
                    // �u�����̔Ԃł��܂���B�v�̗�O���X���[����
                    if (!WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(l_accOpenApplyInfo.accountType))
                    {
                        log.debug("�����̔Ԃł��܂���B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                             WEB3ErrorCatalog.BUSINESS_ERROR_02610,
                             this.getClass().getName() + "." + STR_METHOD_NAME,
                             "�����̔Ԃł��܂���B");
                    }
                }
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

            //����.�����J�ݐ\�����.���Z�^�񋏏Z�敪 != 0:�Z���҂̏ꍇ
            //�u�����̔Ԃł��܂���B�v�̗�O���X���[����
            if (!WEB3ResidentDef.RESIDENT.equals(l_accOpenApplyInfo.residentDiv))
            {
                log.debug("�����̔Ԃł��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_02610,
                     this.getClass().getName() + "." + STR_METHOD_NAME,
                     "�����̔Ԃł��܂���B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
        /**
     * (get��p�U���������s�R�[�h )<BR> 
     * ��p�U������������e�[�u������p�U���������s�R�[�h���擾���A<BR>
     * �d�����Ȃ���p�U���������s�R�[�h�̔z���ԋp����B<BR>
     * <BR>
     * �P�j�@@DB���� <BR>
     * �@@�ȉ��̏������w�肵�āA�y��p�U������������e�[�u���z���������A<BR>
     * �@@�Y�����郌�R�[�h�����擾����B<BR>
     * <BR>
     * �@@[��������]<BR>
     *�@@ �،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * <BR>
     * �Q�j��p�U���������s�R�[�h���i�[����ArrayList�I�u�W�F�N�g�𐶐�<BR>
     * <BR>
     * �R�j�P�j�̖߂�l�̗v�f���ALoop�������s��<BR>
     * <BR>
     * �R�|�P�j�Q�j�̃��X�g�I�u�W�F�N�g�Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@��p�U�����������Row.get��p�U���������s�R�[�h<BR>
     * <BR>
     * �S�jWEB3Toolkit.toUnique()�ɂďd�����Ȃ���p�U���������s�R�[�h�̈ꗗ���쐬����<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�Q�j�̃��X�g�I�u�W�F�N�g��toArray()�����l<BR>
     * <BR>
     * �T�j�S�j�̖߂�l��ԋp����<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    protected String[] getExclusiveAccountFinancialInstitutionCode(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExclusiveAccountFinancialInstitutionCode(String) ";
        log.entering(STR_METHOD_NAME);
        try
        {
            //DB����  �،���ЃR�[�h = ����.�،���ЃR�[�h
            String l_strWhere = " institution_code = ? ";
            Object[] l_obj = {l_strInstitutionCode};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor(); 
            List l_listRecords = l_queryProcessor.doFindAllQuery(
                    ExclusiveUseAccountCondRow.TYPE,
                    l_strWhere,
                    l_obj);
            //�Q�j��p�U���������s�R�[�h���i�[����ArrayList�I�u�W�F�N�g�𐶐�
            ArrayList l_arrayList = new ArrayList();
            String[] l_strLists = null;
            if(l_listRecords != null && l_listRecords.size() > 0)
            {
                for(int i = 0; i < l_listRecords.size(); i++)
                {
                    ExclusiveUseAccountCondRow l_condRow = 
                        (ExclusiveUseAccountCondRow)l_listRecords.get(i);
                    l_arrayList.add(l_condRow.getFinInstitutionCode());
                }
                
                Object[] l_objs = WEB3Toolkit.toUnique(l_arrayList.toArray());
                l_strLists = new String[l_objs.length];
                for(int i = 0; i < l_objs.length; i++)
                {
                    l_strLists[i] = new String();
                    l_strLists[i] = (String)l_objs[i];
                }                
            }
            log.exiting(STR_METHOD_NAME);
            return l_strLists;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (create��p�U����������)<BR>
     * ��p�U����������I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j��p�U����������N���X�̃I�u�W�F�N�g���� <BR>
     * <BR>
     * �Q�j��p�U����������I�u�W�F�N�g.��p�U���������s�R�[�h = ����.��p�U���������s�R�[�h<BR> 
     * <BR>
     * �R�j��p�U����������I�u�W�F�N�g.��p�U��������c�� = ����.��p�U��������c���@@(��String�ɕϊ�����) <BR>
     * <BR>
     * �S�j��p�U����������I�u�W�F�N�g.��p�U��������x���敪 = ����.��p�U��������x���敪 <BR>
     * <BR>
     * �T�j��p�U����������I�u�W�F�N�g��ԋp����<BR>
     * @@param l_strExclusiveAccountBankCode - (��p�U���������s�R�[�h)<BR>
     * ��p�U���������s�R�[�h<BR>
     * @@param l_intExclusiveAccountNumber - (��p�U��������c��)<BR>
     * ��p�U��������c��<BR>
     * @@param l_strExclusiveAccountWarningDiv - (��p�U��������x���敪)<BR>
     * ��p�U��������x���敪<BR>
     * @@return WEB3AccInfoAccountInfo
     * @@throws WEB3BaseException
     */
    protected WEB3AccOpenExclusiveAccountInfo createExclusiveAccountInfo(
        String l_strExclusiveAccountBankCode,
        int l_intExclusiveAccountNumber,
        String l_strExclusiveAccountWarningDiv) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createExclusiveAccountInfo(String, int, String) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j��p�U����������N���X�̃I�u�W�F�N�g����
        WEB3AccOpenExclusiveAccountInfo l_accountInfo = new WEB3AccOpenExclusiveAccountInfo();
        
        //�Q�j��p�U����������I�u�W�F�N�g.��p�U���������s�R�[�h = ����.��p�U���������s�R�[�h
        l_accountInfo.exclusiveAccountFinancialInstitutionCode = l_strExclusiveAccountBankCode;
        
        //�R�j��p�U����������I�u�W�F�N�g.��p�U��������c�� = ����.��p�U��������c��
        l_accountInfo.exclusiveAccountNumber = l_intExclusiveAccountNumber + "";
        
        //�S�j��p�U����������I�u�W�F�N�g.��p�U��������x���敪 = ����.��p�U��������x���敪
        l_accountInfo.exclusiveAccountWarningDiv = l_strExclusiveAccountWarningDiv;
        
        log.exiting(STR_METHOD_NAME);
        return l_accountInfo;
    }

    /**
     * (validate���X�R�[�h)<BR>
     * �w�肳�ꂽ�،���ЃR�[�h�A���X�R�[�h�����X�e�[�u���ɑ��݂��邩���`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���X�c�`�n�𗘗p���ĕ��X�e�[�u������������B<BR>
     * �@@�|�Y���f�[�^���Ȃ��ꍇ�́A�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�u�Y�����X�f�[�^�Ȃ��v<BR>
     * 
     * @@param l_institutionCode �،���ЃR�[�h
     * @@param l_branchCode ���X�R�[�h
     * @@throws WEB3BaseException
     */
    private void validateBranchCode(String l_institutionCode, String l_branchCode) throws WEB3BaseException {
    	
        final String STR_METHOD_NAME = " validateBranchCode(String) ";
        log.entering(STR_METHOD_NAME);

        BranchRow l_branchRow = null;
        boolean l_existBranchCode = false;
		try
        {
			l_branchRow = BranchDao.findRowByInstitutionCodeBranchCode(
                    l_institutionCode,
                    l_branchCode);

            l_existBranchCode = (l_branchRow != null);
        }
		catch (DataFindException l_ex)
        {
            l_existBranchCode = false;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (!l_existBranchCode)
        {
            log.error("�w�肳�ꂽ���X�R�[�h�͑��݂��܂���B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�w�肳�ꂽ���X�R�[�h�͑��݂��܂���B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit�ؑ�)<BR>
     * �����J�ݐؑ֏������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�����J�݁i�󋵖⍇���jsubmit�ؑցv�Q�ƁB<BR>
     * ====================================================<BR>
     * �@@�V�[�P���X�}�@@: �����J�݁i�󋵖⍇���jsubmit�ؑ�<BR>
     * �@@��̈ʒu�@@�@@�@@: �����J�݌����q�����f�[�^�����݂��Ȃ��ꍇ�inull���ԋp���ꂽ�ꍇ�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_01318<BR>
     * ====================================================<BR>
     * ====================================================<BR>
     * �@@�V�[�P���X�}�@@: �����J�݁i�󋵖⍇���jsubmit�ؑ�<BR>
     * �@@��̈ʒu�@@�@@�@@: ���N�G�X�g.�X�V���Ԃ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����J�݌����q.����t���O����v����ꍇ�iequals() == true�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_03177<BR>
     * ====================================================<BR>
     * ====================================================<BR>
     * �@@�V�[�P���X�}�@@: �����J�݁i�󋵖⍇���jsubmit�ؑ�<BR>
     * �@@��̈ʒu�@@�@@�@@: ���N�G�X�g.�X�V���Ԃ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����J�݌����q.��̃t���O����v����ꍇ�iequals() == true�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_03177<BR>
     * ====================================================<BR>
     * ====================================================<BR>
     * �@@�V�[�P���X�}�@@: �����J�݁i�󋵖⍇���jsubmit�ؑ�<BR>
     * �@@��̈ʒu�@@�@@�@@: ���N�G�X�g.�X�V���Ԃ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����J�݌����q.�폜�t���O����v����ꍇ�iequals() == true�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_03177<BR>
     * ====================================================<BR>
     * ====================================================<BR>
     * �@@�V�[�P���X�}�@@: �����J�݁i�󋵖⍇���jsubmit�ؑ�<BR>
     * �@@��̈ʒu�@@�@@�@@: �����J�ݏ󋵋敪���u0�F�@@DEFAULT�i���J�݁j�v�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_03181<BR>
     * ====================================================<BR>
     * <BR>
     * @@param l_request - (�Ǘ��Ҍ����J�ݐؑփ��N�G�X�g)<BR>
     * �Ǘ��Ҍ����J�ݐؑփ��N�G�X�g<BR>
     * @@return WEB3AccOpenChangeResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AccOpenChangeResponse submitChange(WEB3AccOpenChangeRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AccOpenChangeRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�����J��
        //is�X�V�F�@@true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACC_OPEN, true);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //�����J�݌����q(�،���ЃR�[�h : String, ���ʃR�[�h : String)
        //[�����J�݌����q()�Ɏw�肷�����]
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        // ���ʃR�[�h�F�@@���N�G�X�g.���ʃR�[�h
        WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
        try
        {
            //�����J�݌����q�����f�[�^�����݂��Ȃ��ꍇ�inull���ԋp���ꂽ�ꍇ�j�A��O���X���[����B
            l_expAccountOpen =
                new WEB3AccOpenExpAccountOpen(
                    l_strInstitutionCode,
                    l_request.requestNumber);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����J�݌����q�����f�[�^�����݂��Ȃ��B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01318,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get���X�R�[�h( )
        String l_strBranchCode = l_expAccountOpen.getBranchCode();

        //validate���X����(���X�R�[�h : String[])
        //[validate���X����()�Ɏw�肷�����]
        // ���X�R�[�h�F�@@get���X�R�[�h()
        l_administrator.validateBranchPermission(l_strBranchCode);

        //get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        ExpAccountOpenParams l_expAccountOpenParams =
            (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();

        //���N�G�X�g�X�V���ڂ��u1�F����ؑցv�̏ꍇ�͏������s��
        if (WEB3AccOpenUpdateItemDef.PRINT_CHANGE.equals(l_request.updateItem))
        {
            //���N�G�X�g.�X�V���Ԃƌ����J�݌����q.����t���O����v����ꍇ�A
            // ��O���X���[����B
            if (WEB3Toolkit.isEquals(l_request.updateStatus, l_expAccountOpenParams.getPrintFlag()))
            {
                log.debug("�X�V���Ԃ̓��͒l���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03177,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�X�V���Ԃ̓��͒l���s���ł��B");
            }

            //save�����J�݌����q(�X�V���� : String, �X�V�l : String, �X�V�҃R�[�h : String)
            //[save�����J�݌����q()�Ɏw�肷�����]
            //�X�V���ځF�@@�u1�F����t���O�v
            //�X�V�l�F�@@���N�G�X�g.�X�V����
            //�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h( )
            l_expAccountOpen.saveExpAccountOpen(
                WEB3AccOpenUpdateItemDef.PRINT_CHANGE,
                l_request.updateStatus,
                l_strAdministratorCode);
        }

        //���N�G�X�g�X�V���ڂ��u2�F��̐ؑցv�̏ꍇ�͏������s��
        if (WEB3AccOpenUpdateItemDef.RECEIVE_CHANGE.equals(l_request.updateItem))
        {
            //���N�G�X�g.�X�V���Ԃ�
            //�����J�݌����q.��̃t���O����v����ꍇ�iequals() == true�j�A��O���X���[����B
            String l_strReceiptFlag = null;
            if (l_expAccountOpenParams.getReceiptFlag() != null)
            {
                l_strReceiptFlag = l_expAccountOpenParams.getReceiptFlag().intValue() + "";
            }

            if (WEB3Toolkit.isEquals(l_request.updateStatus, l_strReceiptFlag))
            {
                log.debug("�X�V���Ԃ̓��͒l���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03177,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�X�V���Ԃ̓��͒l���s���ł��B");
            }

            //save�����J�݌����q(�X�V���� : String, �X�V�l : String, �X�V�҃R�[�h : String)
            //[save�����J�݌����q()�Ɏw�肷�����]
            //�X�V���ځF�@@�u2�F��̃t���O�v
            //�X�V�l�F�@@���N�G�X�g.�X�V����
            //�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h( )
            l_expAccountOpen.saveExpAccountOpen(
                WEB3AccOpenUpdateItemDef.RECEIVE_CHANGE,
                l_request.updateStatus,
                l_strAdministratorCode);
        }

        //���N�G�X�g�X�V���ڂ��u3�F�폜�ؑցv�̏ꍇ�͏������s��
        if (WEB3AccOpenUpdateItemDef.DELETE_CHANGE.equals(l_request.updateItem))
        {
            //���N�G�X�g.�X�V���Ԃ�
            //�����J�݌����q.�폜�t���O����v����ꍇ�iequals() == true�j�A��O���X���[����B
            String l_strDeleteFlag = null;
            if (l_expAccountOpenParams.getDeleteFlag() != null)
            {
                l_strDeleteFlag = l_expAccountOpenParams.getDeleteFlag().intValue() + "";
            }

            if (WEB3Toolkit.isEquals(l_request.updateStatus, l_strDeleteFlag))
            {
                log.debug("�X�V���Ԃ̓��͒l���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03177,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�X�V���Ԃ̓��͒l���s���ł��B");
            }

            //get�����J�ݏ󋵋敪( )
            String l_strAccountOpenStatusDiv = l_expAccountOpen.getAccountOpenStatusDiv();

            //�����J�ݏ󋵋敪���u0�F�@@DEFAULT�i���J�݁j�v�ȊO�̏ꍇ�A��O���X���[����
            if (!WEB3AccountOpenStatusDivDef.DEFAULT.equals(l_strAccountOpenStatusDiv))
            {
                log.debug("�����J�ݏ󋵂����J�݈ȊO�̏ꍇ�A�폜�t���O�ؑ֕s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03181,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����J�ݏ󋵂����J�݈ȊO�̏ꍇ�A�폜�t���O�ؑ֕s�B");
            }

            //save�����J�݌����q(�X�V���� : String, �X�V�l : String, �X�V�҃R�[�h : String)
            //[save�����J�݌����q()�Ɏw�肷�����]
            //�X�V���ځF�@@�u3�F�폜�t���O�v
            //�X�V�l�F�@@���N�G�X�g.�X�V����
            //�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h( )
            l_expAccountOpen.saveExpAccountOpen(
                WEB3AccOpenUpdateItemDef.DELETE_CHANGE,
                l_request.updateStatus,
                l_strAdministratorCode);
        }

        //createResponse( )
        WEB3AccOpenChangeResponse l_response =
            (WEB3AccOpenChangeResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
