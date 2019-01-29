head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ʌڋq�w��ύX�T�[�r�XImpl(WEB3AdminAccInfoCampaignIndiviChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  ꎉ�(���u) �V�K�쐬
Revision History : 2007/2/1  ���f��No.180
Revision History : 2007/2/1  ���f��No.189
Revision History : 2007/3/2  ����(SCS)���f��No.205
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon;
import webbroker3.accountinfo.WEB3AdminAccInfoCampaignSearchCondition;
import webbroker3.accountinfo.define.WEB3AccInfoUpdateFlagDef;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignIndiviChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ʌڋq�w��ύX�T�[�r�XImpl)<BR>
 * �ʌڋq�w��ύX�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviChangeServiceImpl
    implements WEB3AdminAccInfoCampaignIndiviChangeService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAccInfoCampaignIndiviChangeServiceImpl.class);
    
    /**
     * @@roseuid 45C08B520242
     */
    public WEB3AdminAccInfoCampaignIndiviChangeServiceImpl() 
    {
     
    }
    
    /**
     * �ʌڋq�w��ύX���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔�������L�����y�[��<BR>
     * �ʌڋq�w����̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔�������L�����y�[��<BR>
     * �ʌڋq�w��m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�ύX()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔�������L�����y�[��<BR>
     * �ʌڋq�w�芮�����N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�ύX()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@roseuid 45B0709B00AC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;

        //�P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B  
        // �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔�������L�����y�[���ʌڋq�w����̓��N�G�X�g�̏ꍇ
        //�@@�|get���͉��()���R�[������B  
        if (l_request instanceof WEB3AdminAccInfoCampaignIndiviInputRequest)
        {
            l_response =
                this.getInputScreen(
                    (WEB3AdminAccInfoCampaignIndiviInputRequest)l_request);
        }

        //�� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔�������L�����y�[���ʌڋq�w��m�F���N�G�X�g�̏ꍇ
        //�@@�|validate�ύX()���R�[������B  
        else if (l_request instanceof WEB3AdminAccInfoCampaignIndiviConfirmRequest)
        {
            l_response =
                this.validateChange(
                    (WEB3AdminAccInfoCampaignIndiviConfirmRequest)l_request);
        }

        //�� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔�������L�����y�[���ʌڋq�w�芮�����N�G�X�g�̏ꍇ
        //�@@�|submit�ύX()���R�[������B 
        else if (l_request instanceof WEB3AdminAccInfoCampaignIndiviCompleteRequest)
        {
            l_response =
                this.submitChange(
                    (WEB3AdminAccInfoCampaignIndiviCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * �ʌڋq�w��ύX���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�ʌڋq�w��ύXget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҂��q�l���萔�������L�����y�[��<BR>
     * �ʌڋq�w����̓��N�G�X�g<BR>
     * @@return WEB3AdminAccInfoCampaignIndiviInputResponse
     * @@roseuid 45A7197300C6
     */
    protected WEB3AdminAccInfoCampaignIndiviInputResponse getInputScreen(
        WEB3AdminAccInfoCampaignIndiviInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoCampaignIndiviInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = null;
        //validate
        l_request.validate();
        
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN, true);
        
        //���N�G�X�g�f�[�^.�X�V�t���O = '1'(�ύX)�̏ꍇ
        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_request.updateFlag))
        {
            //get�L�����y�[������(String)
            //���N�G�X�g�f�[�^.�萔�������L�����y�[������ID
            WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon = 
                WEB3AdminAccInfoCampaignCommon.getInstance();
            
            l_accInfoCampaignInfo = l_accInfoCampaignCommon.getCampaignCondition(l_request.campaignId);
            
            //validate���X����(���X�R�[�h : String)
            //���X�R�[�h�F�@@get�L�����y�[������.���X�R�[�h 
            l_administrator.validateBranchPermission(l_accInfoCampaignInfo.branchCode);
        }
        
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminAccInfoCampaignIndiviInputResponse l_response =
            (WEB3AdminAccInfoCampaignIndiviInputResponse)l_request.createResponse();
        
        //�v���p�e�B�Z�b�g
        l_response.commissionCampaignInfo = l_accInfoCampaignInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�ύX)<BR>
     * �ʌڋq�w��ύX�m�F��ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�ʌڋq�w��ύXvalidate�ύX�v�Q�ƁB <BR>
     *  ========================================================<BR>
     *   �V�[�P���X�}(�u�ʌڋq�w��ύXvalidate�ύX�v) : <BR>   
     *   get�d���L�����y�[������()�Ŗ߂�l��null�ł͖����ꍇ�A��O���X���[����<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02726<BR>
     *  ========================================================<BR>
     * <BR>  
     *  ========================================================<BR>
     *   �V�[�P���X�}(�u�ʌڋq�w��ύXvalidate�ύX�v) : <BR>   
     *   is�ύX���(�ύX���� : �萔�������L�����y�[���������) �� FALSE �̏ꍇ�A
     *   ��O���X���[����<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02723<BR>
     *   =======================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҂��q�l���萔�������L�����y�[��<BR>
     * �ʌڋq�w��m�F���N�G�X�g<BR>
     * @@return WEB3AdminAccInfoCampaignIndiviConfirmResponse
     * @@throws WEB3BaseException 
     * @@roseuid 45AC32D7013A
     */
    protected WEB3AdminAccInfoCampaignIndiviConfirmResponse validateChange(
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminAccInfoCampaignIndiviConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoCampaignInfo[] l_campaignInfos = null;
        WEB3AccInfoCampaignInfo l_info = null;
        String l_strComparePeriod = null;
        WEB3GentradeMainAccount l_gentradeMainAccount = null;
        
        //validate()
        l_request.validate();
        
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN, true);
        
		String l_strUpdateFlag = l_request.updateFlag;

        //���N�G�X�g�f�[�^.�X�V�t���O != '2'�i�폜�j�̏ꍇ
        if (!WEB3AccInfoUpdateFlagDef.DELETE.equals(l_strUpdateFlag))
        {
			//validate���X����(���X�R�[�h : String)
			//���X�R�[�h�F�@@�萔�������L�����y�[���������.���X�R�[�h
			l_administrator.validateBranchPermission(l_request.commissionCampaignInfo.branchCode);
        }
             
        WEB3AdminAccInfoCampaignSearchCondition l_campaignSearchCondition = 
            new WEB3AdminAccInfoCampaignSearchCondition();
        
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon = 
            WEB3AdminAccInfoCampaignCommon.getInstance();
        
        //���N�G�X�g�f�[�^.�X�V�t���O = '0'(�o�^)�̏ꍇ
        if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(l_strUpdateFlag))
        {
            //validate���͑Ώۊ��ԃ`�F�b�N(�萔�������L�����y�[��������� �F WEB3AccInfoCampaignInfo, �X�V�����t���O �F String)
        	l_accInfoCampaignCommon.validateInputTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);        	
        	        	
            //set�،���ЃR�[�h(String)
            l_campaignSearchCondition.setInstitutionCode(l_request.commissionCampaignInfo.institutionCode);
            
            //set���X�R�[�h(String)
            l_campaignSearchCondition.setBranchCode(l_request.commissionCampaignInfo.branchCode);
            
            //set�ڋq�R�[�h(String)
            l_campaignSearchCondition.setAccountCode(l_request.commissionCampaignInfo.accountCode);
            
            //set���i�R�[�h(String[])
            l_campaignSearchCondition.setItemCode(l_request.commissionCampaignInfo.itemCode);
            
			//set�Ώۊ���From(Date)
			if (l_request.commissionCampaignInfo.targetPeriodFrom != null)
			{
				l_campaignSearchCondition.setTargetPeriodFrom(
					l_accInfoCampaignCommon.formatDate(l_request.commissionCampaignInfo.targetPeriodFrom));
			}
			else
			{
				l_campaignSearchCondition.setTargetPeriodFrom("");
			}

            //set�o�^�^�C�v(String[])                                            
            String registTypes[] = {l_request.commissionCampaignInfo.registType};
            l_campaignSearchCondition.setRegisterType(registTypes);              

            //get�d���L�����y�[������(�L�����y�[����������)
            //�d�����������F �L�����y�[���������� 
            l_campaignInfos = l_accInfoCampaignCommon.getSameCampaignCondition(l_campaignSearchCondition);
            
            //get�d���L�����y�[������()�Ŗ߂�l��null�ł͖����ꍇ�A����
            //���N�G�X�g�f�[�^.�萔�������L�����y�[���������.�Ώۊ���From��null�ł͖����ꍇ�A��O���X���[����B
            if (l_campaignInfos != null && l_request.commissionCampaignInfo.targetPeriodFrom != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02726,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�L�����y�[�������͊��ɓo�^�ς݂ł��B");
            }
            
            //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            l_gentradeMainAccount =
                l_accountManager.getMainAccount(
                    l_request.commissionCampaignInfo.institutionCode, 
                    l_request.commissionCampaignInfo.branchCode, 
                    l_request.commissionCampaignInfo.accountCode);

        }
        
        //���N�G�X�g�f�[�^.�X�V�t���O = '1'(�ύX) �Ⴕ���� '2'(�폜)�̏ꍇ
        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_strUpdateFlag)
            || WEB3AccInfoUpdateFlagDef.DELETE.equals(l_strUpdateFlag))
        {
            l_strComparePeriod = l_accInfoCampaignCommon.validateTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);
        }
        
        //���N�G�X�g�f�[�^.�X�V�t���O = '1'(�ύX)�̏ꍇ
        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_strUpdateFlag))
        {
            boolean l_blnIsChangeInfo = l_accInfoCampaignCommon.isChangeInfo(l_request.commissionCampaignInfo);
            
            if (!l_blnIsChangeInfo)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02723,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ɕύX���Ȃ��B");
            }
        }
                
        //���N�G�X�g�f�[�^.�X�V�t���O = '2'(�폜)�̏ꍇ
        if (WEB3AccInfoUpdateFlagDef.DELETE.equals(l_strUpdateFlag))
        {
            //get�L�����y�[������(String)
            l_info = l_accInfoCampaignCommon.getCampaignCondition(l_request.commissionCampaignInfo.campaignId);

			//validate���X����(���X�R�[�h : String)
			//���X�R�[�h�F�@@�萔�������L�����y�[���������.���X�R�[�h
			l_administrator.validateBranchPermission(l_info.branchCode);
        }
        
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminAccInfoCampaignIndiviConfirmResponse l_response =
            (WEB3AdminAccInfoCampaignIndiviConfirmResponse)l_request.createResponse();
        
        //�v���p�e�B�Z�b�g
        l_response.alertFlag = l_strComparePeriod;
        l_response.commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(l_strUpdateFlag)
            || WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_strUpdateFlag))
        {
            l_response.commissionCampaignInfo.campaignId = null;
            l_response.commissionCampaignInfo.campaignName = null;
            l_response.commissionCampaignInfo.institutionCode = null;
            l_response.commissionCampaignInfo.branchCode = null;
            l_response.commissionCampaignInfo.accountCode = null;
            l_response.commissionCampaignInfo.itemCode = null;
            l_response.commissionCampaignInfo.targetPeriodFrom = null;
            l_response.commissionCampaignInfo.targetPeriodTo = null;
            l_response.commissionCampaignInfo.collectRate = null;
            l_response.commissionCampaignInfo.registType = null;
            l_response.commissionCampaignInfo.deleteFlag = null;
            l_response.commissionCampaignInfo.transactionDiv = null;
            l_response.commissionCampaignInfo.registrant = null;
            l_response.commissionCampaignInfo.registDate = null;
            l_response.commissionCampaignInfo.updateDate = null;
            l_response.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_response.commissionCampaignInfo.accopenPassPeriodDay = null;
            l_response.commissionCampaignInfo.traderCode = null;
            l_response.commissionCampaignInfo.accountOpenDiv = null;
            l_response.commissionCampaignInfo.accountOpenDateFrom = null;
            l_response.commissionCampaignInfo.accountOpenDateTo = null;
        }
        
        if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(l_strUpdateFlag))
        {
            l_response.commissionCampaignInfo.accountName = 
                l_gentradeMainAccount.getDisplayAccountName();
        }
        else if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_strUpdateFlag))
        {
            l_response.commissionCampaignInfo.accountName = null;
        }
        
        if (WEB3AccInfoUpdateFlagDef.DELETE.equals(l_strUpdateFlag))
        {
            l_response.commissionCampaignInfo = l_info;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�ύX)<BR>
     * �ʌڋq�w��ύX������ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�ʌڋq�w��ύXsubmit�ύX�v�Q�ƁB <BR>
     *  ========================================================<BR>
     *   �V�[�P���X�}(�u�ʌڋq�w��ύXsubmit�ύX�v) : <BR>   
     *   get�d���L�����y�[������()�Ŗ߂�l��null�ł͖����ꍇ�A��O���X���[����<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02726<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *   �V�[�P���X�}(�u�ʌڋq�w��ύXsubmit�ύX�v) : <BR>   
     *   is�ύX���(�ύX���� : �萔�������L�����y�[���������) �� FALSE �̏ꍇ�A
     *   ��O���X���[����<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02723<BR>
     *   =======================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҂��q�l���萔�������L�����y�[��<BR>
     * �ʌڋq�w�芮�����N�G�X�g<BR>
     * @@return WEB3AdminAccInfoCampaignIndiviCompleteResponse
     * @@throws WEB3BaseException 
     * @@roseuid 45AED5AC02AB
     */
    protected WEB3AdminAccInfoCampaignIndiviCompleteResponse submitChange(
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminAccInfoCampaignIndiviCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon = 
            WEB3AdminAccInfoCampaignCommon.getInstance();
                
        WEB3AccInfoCampaignInfo[] l_campaignInfos = null;
        String l_strTargetPeriod = null;
        
        WEB3AdminAccInfoCampaignSearchCondition l_campaignSearchCondition = 
            new WEB3AdminAccInfoCampaignSearchCondition();
        
        //validate()
        l_request.validate();
        
        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN, true);
        
        //validate���X����(���X�R�[�h : String)
        //���X�R�[�h�F�@@�萔�������L�����y�[���������.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.commissionCampaignInfo.branchCode);
        
        //validate����p�X���[�h(�p�X���[�h : String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //���N�G�X�g�f�[�^.�X�V�t���O = '1'(�ύX) �Ⴕ���� '2'(�폜)�̏ꍇ
        String l_strUpdateFlag = l_request.updateFlag;
        
        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_strUpdateFlag)
            || WEB3AccInfoUpdateFlagDef.DELETE.equals(l_strUpdateFlag))
        {
            //validate�Ώۊ���(�萔�������L�����y�[���������)
            l_strTargetPeriod = 
                l_accInfoCampaignCommon.validateTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);
        }
        
        //get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();
        
        //���N�G�X�g�f�[�^.�X�V�t���O = '0'(�o�^)�̏ꍇ
        if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(l_strUpdateFlag))
        {
        	//validate���͑Ώۊ��ԃ`�F�b�N(�萔�������L�����y�[��������� �F WEB3AccInfoCampaignInfo, �X�V�����t���O �F String)
        	l_accInfoCampaignCommon.validateInputTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);        	
        	
        	//set�،���ЃR�[�h(String)
            l_campaignSearchCondition.setInstitutionCode(l_request.commissionCampaignInfo.institutionCode);
            
            //set���X�R�[�h(String)
            l_campaignSearchCondition.setBranchCode(l_request.commissionCampaignInfo.branchCode);
            
            //set�ڋq�R�[�h(String)
            l_campaignSearchCondition.setAccountCode(l_request.commissionCampaignInfo.accountCode);
            
            //set���i�R�[�h(String[])
            l_campaignSearchCondition.setItemCode(l_request.commissionCampaignInfo.itemCode);
            
			//set�Ώۊ���From(Date)
			if (l_request.commissionCampaignInfo.targetPeriodFrom != null)
			{
				l_campaignSearchCondition.setTargetPeriodFrom(
					l_accInfoCampaignCommon.formatDate(l_request.commissionCampaignInfo.targetPeriodFrom));
			}
			else
			{
				l_campaignSearchCondition.setTargetPeriodFrom("");
			}
			
            //set�o�^�^�C�v(String[])                                            
            String registTypes[] = {l_request.commissionCampaignInfo.registType};
            l_campaignSearchCondition.setRegisterType(registTypes);              
            
            //get�d���L�����y�[������(�L�����y�[����������)
            //�d�����������F �L�����y�[���������� 
            l_campaignInfos = l_accInfoCampaignCommon.getSameCampaignCondition(l_campaignSearchCondition);
            
            //get�d���L�����y�[������()�Ŗ߂�l��null�ł͖����ꍇ�A����
            //���N�G�X�g�f�[�^.�萔�������L�����y�[���������.�Ώۊ���From��null�ł͖����ꍇ�A��O���X���[����B
            if (l_campaignInfos != null && l_request.commissionCampaignInfo.targetPeriodFrom != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02726,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�L�����y�[�������͊��ɓo�^�ς݂ł��B");
            }
            
            //insert�L�����y�[������(�萔�������L�����y�[���������, String)
            //�o�^���F ���N�G�X�g.�萔�������L�����y�[���������I�u�W�F�N�g 
            //�X�V�҃R�[�h�F get�Ǘ��҃R�[�h()�̖߂�l
            l_accInfoCampaignCommon.insertCampaignCondition(
                l_request.commissionCampaignInfo, l_strAdministratorCode);
        }
        
        //���N�G�X�g�f�[�^.�X�V�t���O = '1'(�ύX)�̏ꍇ
        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_strUpdateFlag))
        {
            //is�ύX���(�萔�������L�����y�[���������)
            boolean l_blnIsChangeInfo = 
                l_accInfoCampaignCommon.isChangeInfo(l_request.commissionCampaignInfo);
            
            //is�ύX���(�ύX���� : �萔�������L�����y�[���������) �� FALSE �̏ꍇ�A��O���X���[����B
            if (!l_blnIsChangeInfo)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02723,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ɕύX������܂���B");
            }
            
            //update�L�����y�[������(�萔�������L�����y�[���������, String)
            l_accInfoCampaignCommon.updateCampaignCondition(
                l_request.commissionCampaignInfo, l_strAdministratorCode);
        }
        
        //���N�G�X�g�f�[�^.�X�V�t���O�� ' 2 '(�폜)�̏ꍇ
        if (WEB3AccInfoUpdateFlagDef.DELETE.equals(l_strUpdateFlag))
        {
            //delete�L�����y�[������(String, String)
            //�萔�������L�����y�[������ID�F 
            //         ���N�G�X�g.�萔�������L�����y�[���������.�萔�������L�����y�[������ID 
            //�X�V�҃R�[�h�F get�Ǘ��҃R�[�h()�̖߂�l
            l_accInfoCampaignCommon.deleteCampaignCondition(
                l_request.commissionCampaignInfo.campaignId, 
                l_strAdministratorCode); 
        }
        
        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAccInfoCampaignIndiviCompleteResponse l_response =
            (WEB3AdminAccInfoCampaignIndiviCompleteResponse)l_request.createResponse();
        
        //�v���p�e�B�Z�b�g
        l_response.alertFlag = l_strTargetPeriod;

        log.exiting(STR_METHOD_NAME);
        return l_response;  
    }
}
@
