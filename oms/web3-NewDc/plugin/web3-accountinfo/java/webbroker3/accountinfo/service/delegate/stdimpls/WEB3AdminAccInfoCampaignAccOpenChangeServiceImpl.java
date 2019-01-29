head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݏ����w��ύX�T�[�r�XImpl(WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 � (���u) �V�K�쐬
Revision History : 2007/02/02 � (���u) ���f��No.169
Revision History : 2007/02/02 � (���u) ���f��No.172
Revision History : 2007/02/02 � (���u) ���f��No.174
Revision History : 2007/02/05 � (���u) ���f��No.179
Revision History : 2007/02/28 Inomata(SCS)���f��No.204
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon;
import webbroker3.accountinfo.WEB3AdminAccInfoCampaignSearchCondition;
import webbroker3.accountinfo.define.WEB3AccInfoUpdateFlagDef;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignAccOpenChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * �����J�ݏ����w��ύX�T�[�r�XImpl<BR>
 * @@author  �
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl implements WEB3AdminAccInfoCampaignAccOpenChangeService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.class);

    /**
     * @@roseuid 45C08B53006D
     */
    public WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl()
    {

    }

    /**
     * �����J�ݏ����w��ύX���������{����B<BR>
     * <BR>
     * �P�j ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR> �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔�������L�����y�[��<BR>
     * �����J�ݏ������̓��N�G�X�g�̏ꍇ <BR>
     * �|get���͉��()���R�[������B <BR>
     * <BR> �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔�������L�����y�[��<BR>
     * �����J�ݏ����m�F���N�G�X�g�̏ꍇ <BR>
     * �|validate�ύX()���R�[������B <BR>
     * <BR> �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔�������L�����y�[��<BR>
     * �����J�ݏ����������N�G�X�g�̏ꍇ <BR>
     * �|submit�ύX()���R�[������B <BR>
     *�@@<BR>
     * @@param l_request -
     *            ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B046E800AF
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g�����w��(null)�ł��B");
        }

        WEB3GenResponse l_response = null;
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔�������L�����y�[�������J�ݏ������̓��N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminAccInfoCampaignAccOpenInputRequest)
        {
            //get���͉��()
            l_response =
                this.getInputScreen((WEB3AdminAccInfoCampaignAccOpenInputRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔�������L�����y�[�������J�ݏ����m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminAccInfoCampaignAccOpenConfirmRequest)
        {
            //validate�ύX()
            l_response =
                this.validateChange((WEB3AdminAccInfoCampaignAccOpenConfirmRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���萔�������L�����y�[�������J�ݏ����������N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminAccInfoCampaignAccOpenCompleteRequest)
        {
            //submit�ύX()
            l_response =
                this.submitChange((WEB3AdminAccInfoCampaignAccOpenCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * �����J�ݏ����w��ύX���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�ݏ����w��ύXget���͉�ʁv�Q�ƁB <BR>
     *�@@<BR>
     * @@param l_request -
     *            �Ǘ��҂��q�l���萔�������L�����y�[�������J�ݏ����ύX���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccInfoCampaignAccOpenInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B421390073
     */
    protected WEB3AdminAccInfoCampaignAccOpenInputResponse getInputScreen(
            WEB3AdminAccInfoCampaignAccOpenInputRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoCampaignAccOpenInputRequest) ";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����()
        WEB3Administrator l_web3Administrator  = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN, true);

        //�ύX�����̏ꍇ
        WEB3AccInfoCampaignInfo l_campaignInfo = null;
        if(WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_request.updateFlag))
        {
            //get�L�����y�[������(String)
            WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon = 
                WEB3AdminAccInfoCampaignCommon.getInstance();
            l_campaignInfo = l_accInfoCampaignCommon.getCampaignCondition(l_request.campaignId);
            //validate���X����(���X�R�[�h : String)
            l_web3Administrator.validateBranchPermission(l_campaignInfo.branchCode);
        }

        //createResponse( )
        WEB3AdminAccInfoCampaignAccOpenInputResponse l_response =
            (WEB3AdminAccInfoCampaignAccOpenInputResponse) l_request.createResponse();
        l_response.commissionCampaignInfo = l_campaignInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�ύX)<BR>
     * �����J�ݏ����w��ύX�m�F��ʕ\�����������{����B<BR>
     * <BR>
     * =============================================== <BR>
     * �V�[�P���X�} : �u�����J�ݏ����w��ύXvalidate�ύX�v�Q�ƁB<BR>
     * ��̈ʒu :1.6.1 is�ύX���()�̖߂�l == false�̏ꍇ�A��O���X���[<BR>
     * class : WEB3BusinessLayerException <BR>
     * tag : BUSINESS_ERROR_02723 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * �V�[�P���X�} : �u�����J�ݏ����w��ύXvalidate�ύX�v�Q�ƁB<BR>
     * ��̈ʒu :1.7.1.1  is���X()�̖߂�l == false�̏ꍇ�A��O���X���[<BR>
     * class : WEB3BusinessLayerException <BR>
     * tag : BUSINESS_ERROR_00779 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * �V�[�P���X�} : �u�����J�ݏ����w��ύXvalidate�ύX�v�Q�ƁB<BR>
     * ��̈ʒu :1.7.4 get�d���L�����y�[������()�̖߂�l��null�ȊO�A����<BR>
     * ���N�G�X�g�f�[�^.�萔�������L�����y�[���������.�����J�ݓ�From != NULL�̏ꍇ�A��O���X���[<BR>
     * class : WEB3BusinessLayerException <BR>
     * tag : BUSINESS_ERROR_02724 <BR>
     * =============================================== <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�ݏ����w��ύXvalidate�ύX�v�Q�ƁB <BR>
     *�@@<BR>
     * @@param l_request -
     *            �Ǘ��҂��q�l���萔�������L�����y�[�������J�ݏ����ύX�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccInfoCampaignAccOpenConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B422700228
     */
    protected WEB3AdminAccInfoCampaignAccOpenConfirmResponse validateChange(
            WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest) ";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoCampaignInfo[] l_campaignInfos = null;
        
        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����()
         WEB3Administrator l_web3Administrator  = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN, true);

        if (!WEB3AccInfoUpdateFlagDef.DELETE.equals(l_request.updateFlag))
        {
			//validate���X����(���X�R�[�h : String)
			l_web3Administrator.validateBranchPermission(l_request.commissionCampaignInfo.branchCode);
        }

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon = 
            WEB3AdminAccInfoCampaignCommon.getInstance();

        String l_strComparePeriod = null;
        //�ύX�����E�폜�����̏ꍇ�i�X�V�����t���O == 1 or 2�j
        if(WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_request.updateFlag)
            || WEB3AccInfoUpdateFlagDef.DELETE.equals(l_request.updateFlag))
        {
            //validate�Ώۊ���(�萔�������L�����y�[���������, �X�V�����t���O)
            l_strComparePeriod =
                l_accInfoCampaignCommon.validateTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);
        }

        //�ύX�����̏ꍇ�i�X�V�����t���O == 1�j
        if(WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_request.updateFlag))
        {
            // is�ύX���(�萔�������L�����y�[���������)
            boolean l_blnChangeInfo =
                l_accInfoCampaignCommon.isChangeInfo(l_request.commissionCampaignInfo);
            if (!l_blnChangeInfo)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02723,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ɕύX������܂���B");
            }
        }

        //�o�^�����̏ꍇ�i�X�V�����t���O == 0�j
        if(WEB3AccInfoUpdateFlagDef.LOGIN.equals(l_request.updateFlag))
        {
            boolean l_blnBranch = false;
            WEB3AdminAccInfoCampaignSearchCondition l_condition = new WEB3AdminAccInfoCampaignSearchCondition();;
            // ���X�R�[�h != NULL�̏ꍇ
            if (l_request.commissionCampaignInfo.branchCode != null)
            {
				l_blnBranch = this.isBranch(l_request.commissionCampaignInfo.institutionCode,
					l_request.commissionCampaignInfo.branchCode);

				if (!l_blnBranch)
				{
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_00779,
							this.getClass().getName() + "." + STR_METHOD_NAME,
							"���X�R�[�h�̓��͂��s���ł��B");
				}
            }
            
			//validate���͑Ώۊ���(�萔�������L�����y�[���������, �X�V�����t���O)
			l_accInfoCampaignCommon.validateInputTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);

            //�،���ЃR�[�h���Z�b�g����
            l_condition.setInstitutionCode(l_request.commissionCampaignInfo.institutionCode);

            //���X�R�[�h���Z�b�g����
            if (l_request.commissionCampaignInfo.branchCode != null)
            {
				l_condition.setBranchCode(l_request.commissionCampaignInfo.branchCode);
            }
            else
            {
				l_condition.setBranchCode("");
            }

            //�Ώۊ���From���Z�b�g����
            if (l_request.commissionCampaignInfo.targetPeriodFrom != null)
            {
				l_condition.setTargetPeriodFrom(
					l_accInfoCampaignCommon.formatDate(l_request.commissionCampaignInfo.targetPeriodFrom));
            }

            //���҃R�[�h���Z�b�g����
            if (l_request.commissionCampaignInfo.traderCode != null)
            {
				l_condition.setTraderCode(l_request.commissionCampaignInfo.traderCode);
            }
            else
            {
				l_condition.setTraderCode("");
            }

            //�����J�݋敪���Z�b�g����
            if (l_request.commissionCampaignInfo.accountOpenDiv != null)
            {
				l_condition.setAccountOpenDiv(l_request.commissionCampaignInfo.accountOpenDiv);
            }
            else
            {
				l_condition.setAccountOpenDiv("");
            }

            //�����J�ݓ�From���Z�b�g����
            if (l_request.commissionCampaignInfo.accountOpenDateFrom != null)
            {
                l_condition.setAccountOpenDateFrom(l_request.commissionCampaignInfo.accountOpenDateFrom);
            }

            //���i�R�[�h���Z�b�g����
            l_condition.setItemCode(l_request.commissionCampaignInfo.itemCode);

            //�o�^�^�C�v���Z�b�g����B
            String[] l_registType = {l_request.commissionCampaignInfo.registType};
            l_condition.setRegisterType(l_registType);
            
            //get�d���L�����y�[������(�L�����y�[����������)
            l_campaignInfos = l_accInfoCampaignCommon.getSameCampaignCondition(l_condition);

			//���N�G�X�g�f�[�^.�萔�������L�����y�[���������.�����J�ݓ�From != NULL�̏ꍇ�A
            if (l_campaignInfos != null && l_request.commissionCampaignInfo.accountOpenDateFrom != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02724,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�L�����y�[�������ɑ��݂���B");
            }
        }

        WEB3AdminAccInfoCampaignAccOpenConfirmResponse l_response =
            (WEB3AdminAccInfoCampaignAccOpenConfirmResponse) l_request.createResponse();

		//�폜�����̏ꍇ�i�X�V�����t���O==2�j
        if(WEB3AccInfoUpdateFlagDef.DELETE.equals(l_request.updateFlag))
        {
            //get�L�����y�[������(String)
            WEB3AccInfoCampaignInfo l_campaignInfo =
                l_accInfoCampaignCommon.getCampaignCondition(l_request.commissionCampaignInfo.campaignId);

			//validate���X����(���X�R�[�h : String)
			l_web3Administrator.validateBranchPermission(l_campaignInfo.branchCode);

            l_response.commissionCampaignInfo = l_campaignInfo;
        }

        if(WEB3AccInfoUpdateFlagDef.LOGIN.equals(l_request.updateFlag)
                || WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_request.updateFlag))
        {
            l_response.commissionCampaignInfo = null;
        }

        l_response.alertFlag = l_strComparePeriod;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�ύX)<BR>
     * �����J�ݏ����w��ύX������ʕ\�����������{����B<BR>
     * <BR>
     * =============================================== <BR>
     * �V�[�P���X�} : �u�����J�ݏ����w��ύXsubmit�ύX�v�Q�ƁB<BR>
     * ��̈ʒu :1.8.1 is�ύX���()�̖߂�l == false�̏ꍇ�A��O���X���[<BR>
     * class : WEB3BusinessLayerException <BR>
     * tag : BUSINESS_ERROR_02723 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * �V�[�P���X�} : �u�����J�ݏ����w��ύXsubmit�ύX�v�Q�ƁB<BR>
     * ��̈ʒu :1.9.1.1  is���X()�̖߂�l == false�̏ꍇ�A��O���X���[<BR>
     * class : WEB3BusinessLayerException <BR>
     * tag : BUSINESS_ERROR_00779 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * �V�[�P���X�} : �u�����J�ݏ����w��ύXsubmit�ύX�v�Q�ƁB<BR>
     * ��̈ʒu :1.9.4 get�d���L�����y�[������()�̖߂�l��null�ȊO�A����<BR>
     * ���N�G�X�g�f�[�^.�萔�������L�����y�[���������.�����J�ݓ�From != NULL�̏ꍇ�A��O���X���[<BR>
     * class : WEB3BusinessLayerException <BR>
     * tag : BUSINESS_ERROR_02724 <BR>
     * =============================================== <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�ݏ����w��ύXsubmit�ύX�v�Q�ƁB <BR>
     *�@@<BR>
     * @@param l_request -
     *            �Ǘ��҂��q�l���萔�������L�����y�[�������J�ݏ����ύX�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccInfoCampaignAccOpenCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B4227100A1
     */
    protected WEB3AdminAccInfoCampaignAccOpenCompleteResponse submitChange(
            WEB3AdminAccInfoCampaignAccOpenCompleteRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminAccInfoCampaignAccOpenCompleteRequest) ";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoCampaignInfo[] l_campaignInfos = null;
        
        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����()
        WEB3Administrator l_web3Administrator  = WEB3Administrator.getInstanceFromLoginInfo();

       //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
       l_web3Administrator.validateAuthority(
           WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN, true);

       //validate���X����(���X�R�[�h : String)
       l_web3Administrator.validateBranchPermission(l_request.commissionCampaignInfo.branchCode);

       //validate����p�X���[�h
       l_web3Administrator.validateTradingPassword(l_request.password);

       //�ύX�����̏ꍇ
       WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon = 
           WEB3AdminAccInfoCampaignCommon.getInstance();

       String l_strTargetPeriod = null;
       //�ύX�����E�폜�����̏ꍇ�i�X�V�����t���O == 1 or 2�j
       if(WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_request.updateFlag)
           || WEB3AccInfoUpdateFlagDef.DELETE.equals(l_request.updateFlag))
       {
           //validate�Ώۊ���(�萔�������L�����y�[���������, �X�V�����t���O)
           l_strTargetPeriod =
               l_accInfoCampaignCommon.validateTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);
       }

       //get�Ǘ��҃R�[�h( )
       String l_strAdministratorCode = l_web3Administrator.getAdministratorCode();

       //�ύX�����̏ꍇ�i�X�V�����t���O == 1�j
       if(WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_request.updateFlag))
       {
           // is�ύX���(�萔�������L�����y�[���������)
           boolean l_blnChangeInfo =
               l_accInfoCampaignCommon.isChangeInfo(l_request.commissionCampaignInfo);

           if (!l_blnChangeInfo)
           {
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_02723,
                       this.getClass().getName() + "." + STR_METHOD_NAME,
                       "���ɕύX������܂���B");
           }

           //update�L�����y�[������(�萔�������L�����y�[���������, String)
           l_accInfoCampaignCommon.updateCampaignCondition(l_request.commissionCampaignInfo, l_strAdministratorCode);
       }

       //�o�^�����̏ꍇ�i�X�V�����t���O == 0�j
       if(WEB3AccInfoUpdateFlagDef.LOGIN.equals(l_request.updateFlag))
       {
           boolean l_blnBranch = false;
           WEB3AdminAccInfoCampaignSearchCondition l_condition = new WEB3AdminAccInfoCampaignSearchCondition();;
           // ���X�R�[�h != NULL�̏ꍇ
           if (l_request.commissionCampaignInfo.branchCode != null)
           {
				l_blnBranch = this.isBranch(l_request.commissionCampaignInfo.institutionCode,
					l_request.commissionCampaignInfo.branchCode);
	
				if (!l_blnBranch)
				{
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_00779,
							this.getClass().getName() + "." + STR_METHOD_NAME,
							"���X�R�[�h�̓��͂��s���ł��B");
				}
           }

           //validate���͑Ώۊ���(�萔�������L�����y�[���������,�X�V�����t���O)
           l_accInfoCampaignCommon.validateInputTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);

           //�،���ЃR�[�h���Z�b�g����
           l_condition.setInstitutionCode(l_request.commissionCampaignInfo.institutionCode);

           //���X�R�[�h���Z�b�g����
           if (l_request.commissionCampaignInfo.branchCode != null)
           {
               l_condition.setBranchCode(l_request.commissionCampaignInfo.branchCode);
           }
           else
           {
               l_condition.setBranchCode("");
           }

           //�Ώۊ���From���Z�b�g����
           if (l_request.commissionCampaignInfo.targetPeriodFrom != null)
           {
			l_condition.setTargetPeriodFrom(
				l_accInfoCampaignCommon.formatDate(l_request.commissionCampaignInfo.targetPeriodFrom));
           }

           //���҃R�[�h���Z�b�g����
           if (l_request.commissionCampaignInfo.traderCode != null)
           {
               l_condition.setTraderCode(l_request.commissionCampaignInfo.traderCode);
           }
           else
           {
               l_condition.setTraderCode("");
           }

           //�����J�݋敪���Z�b�g����
           if (l_request.commissionCampaignInfo.accountOpenDiv != null)
           {
			   l_condition.setAccountOpenDiv(l_request.commissionCampaignInfo.accountOpenDiv);
           }
           else
           {
			   l_condition.setAccountOpenDiv("");
           }

           //�����J�ݓ�From���Z�b�g����
           if (l_request.commissionCampaignInfo.accountOpenDateFrom != null)
           {
               l_condition.setAccountOpenDateFrom(l_request.commissionCampaignInfo.accountOpenDateFrom);
           }

           //���i�R�[�h���Z�b�g����
           l_condition.setItemCode(l_request.commissionCampaignInfo.itemCode);

           //�o�^�^�C�v���Z�b�g����B
           String[] l_registType = {l_request.commissionCampaignInfo.registType};
           l_condition.setRegisterType(l_registType);
            
           //get�d���L�����y�[������(�L�����y�[����������)
           l_campaignInfos = l_accInfoCampaignCommon.getSameCampaignCondition(l_condition);

           if (l_campaignInfos != null && l_request.commissionCampaignInfo.accountOpenDateFrom != null)
           {
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_02724,
                       this.getClass().getName() + "." + STR_METHOD_NAME,
                       "�L�����y�[�������ɑ��݂���B");
           }
           // insert�L�����y�[������(�萔�������L�����y�[���������, String)
           l_accInfoCampaignCommon.insertCampaignCondition(l_request.commissionCampaignInfo, l_strAdministratorCode);
       }

       //�폜�����̏ꍇ�i�X�V�����t���O == 2�j
       if(WEB3AccInfoUpdateFlagDef.DELETE.equals(l_request.updateFlag))
       {
           //delete�L�����y�[������(String, String)
           l_accInfoCampaignCommon.deleteCampaignCondition(
                   l_request.commissionCampaignInfo.campaignId, l_strAdministratorCode);
       }

       //createResponse( )
       WEB3AdminAccInfoCampaignAccOpenCompleteResponse l_response =
           (WEB3AdminAccInfoCampaignAccOpenCompleteResponse) l_request.createResponse();
       l_response.alertFlag = l_strTargetPeriod;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (is���X)<BR>
     * �i�����j���X�R�[�h�����X�e�[�u����ɑ��݂��邩�`�F�b�N���s���B<BR>
     * <BR>
     * ���݂���ꍇ��TRUE�A���݂��Ȃ��ꍇ��FALSE��ԋp����B<BR>
     * <BR>
     * �P�j �����f�[�^�R���e�i�p����2��Object�z����쐬����B<BR>
     * Object[0] = �i�����j�،���ЃR�[�h<BR>
     * Object[1] = �i�����j���X�R�[�h<BR>
     * <BR>
     * �Q�j QueryProcessor.doFindAllQuery( )�ɂ��A���X�e�[�u�����R�[�h���擾����B <BR>
     * <BR>
     * [doFindAllQuery()�Ɏw�肷�����] <BR>
     * arg1�F ���X�e�[�u��Row.TYPE <BR>
     * arg2�F "institution_code = ? and branch_code = ?"<BR>
     * arg3�F �P�j�ō쐬����Object�z��<BR>
     * <BR>
     * �R�j �Q�j�̖߂�lList�̒��� > 0 �̏ꍇ�ATRUE ��ԋp����B<BR>
     * <BR>
     * �S�j �R�j�ȊO�̏ꍇ�AFALSE ��ԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_strInstitution -
     *            �،���ЃR�[�h<BR>
     * @@param l_strBranchCode -
     *            ���X�R�[�h<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 45B5BF9D0184
     */
    private boolean isBranch(String l_strInstitution, String l_strBranchCode)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isBranch(String, String) ";
        log.entering(STR_METHOD_NAME);

        if (l_strInstitution == null || l_strBranchCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�،���ЃR�[�h���͕��X�R�[�h�����w��(null)�ł��B");
        }

        //�����f�[�^�R���e�i�p����2��Object�z����쐬����B
        Object[] l_objQueryContainers = new Object[2];

        l_objQueryContainers[0] = l_strInstitution;
        l_objQueryContainers[1] = l_strBranchCode;

        String l_strWhere = " institution_code = ? and branch_code = ? ";
        List l_lisRecords = new ArrayList();
        QueryProcessor l_queryProcessor;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                    BranchRow.TYPE,
                    l_strWhere,
                    l_objQueryContainers);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRecords != null && l_lisRecords.size() > 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
}
@
