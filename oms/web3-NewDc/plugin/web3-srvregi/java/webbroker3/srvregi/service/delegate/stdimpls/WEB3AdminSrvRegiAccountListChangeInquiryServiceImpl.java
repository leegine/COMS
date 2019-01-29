head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�T�[�r�XImpl(WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
Revesion History : 2007/06/19 �����Q(���u) �d�l�ύX���f��No.249 No.264
Revesion History : 2007/06/21 �����Q(���u) �d�l�ύX���f��No.270
Revesion History : 2007/07/24 ����(���u) �d�l�ύX���f��No.292
Revesion History : 2007/07/30 ����(���u) �d�l�ύX���f��No.298
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AppliAttributeDef;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiKeyItemDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerReferenceGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerReferenceResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountListChangeInquiryService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�T�[�r�XImpl)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�T�[�r�X�����N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiAccountListChangeInquiryService 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl.class);
    
    /**
     * @@roseuid 416F392900AB
     */
    public WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl() 
    {
     
    }
    
    /**
     * �T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj�ڋq�ꗗ�Ɖ�v<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj�ڋq�ꗗ�Ɖ�v): <BR>
     *         1.6.1.1.:getLotDiv( )<BR>
     *        <�⑫���̓`�F�b�N>�T�[�r�X���p�\�[�g�L�[�̃`�F�b�N<BR>
     *        (1)get���I�ݒ�( )�̖߂�l��"��"�̏ꍇ<BR>
     *      �@@�@@���N�G�X�g�f�[�^.�T�[�r�X���p�\�[�g�L�[�̗v�f�����A�ȉ����J��Ԃ��B<BR>
     *      �@@�@@���N�G�X�g�f�[�^.�T�[�r�X���p�\�[�g�L�[.�L�[���ڂ��ȉ��̒l�ȊO�̏ꍇ�A<BR>
     *          ��O���X���[����B<BR>
     *      �@@�@@�u���X�v�u�ڋq�v�u�\�������敪�v�u�\�����I�敪�v�u�K�p�J�n���v�u�K�p�I�����v<BR>
     *      �@@�@@�u�o�^�敪�v�u���p�����v�u�ŏI�X�V���v�u�ŏI�X�V�ҁv<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     *      �@@�A���N�G�X�g�f�[�^.�\�����I�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     *      �@@�@@�u���p�v�u���I�^�{�\���v�u����v�u�S�āv�u�����Ώہv�u�\���s�v�u�S�āi�����p�j�v<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00995<BR>
     * <BR>
     *       (2)get���I�ݒ�( )�̖߂�l��"�L"�̏ꍇ<BR>
     *      �@@�@@���N�G�X�g�f�[�^.�T�[�r�X���p�\�[�g�L�[�̗v�f�����A�ȉ����J��Ԃ��B<BR>
     *      �@@�@@���N�G�X�g�f�[�^.�T�[�r�X���p�\�[�g�L�[.�L�[���ڂ��ȉ��̒l�ȊO�̏ꍇ�A<BR>
     *          ��O���X���[����B<BR>
     *      �@@�@@�u���X�v�u�ڋq�v�u�\�����I�敪�v�u�\�����v�u�K�p�J�n���v�u�K�p�I�����v<BR>
     *      �@@�@@�u�o�^�敪�v�u���p�����v�u�ŏI�X�V���v�u�ŏI�X�V�ҁv<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     *      �@@�A���N�G�X�g�f�[�^.�\�����I�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     *      �@@�@@�u�\���v�u���I�^�{�\���v�u���I�v�u����v�u�S�āv<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00997<BR>
     * ==========================================================<BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj�ڋq�ꗗ�Ɖ�v): <BR>
     *         1.10: <�y�[�W���O����><BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01080<BR>
     * ==========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F51DF703C1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME );
        
        WEB3AdminSrvRegiCustomerReferenceRequest l_customerReferenceRequest = null;

        
        if (l_request instanceof WEB3AdminSrvRegiCustomerReferenceRequest)
        {
            l_customerReferenceRequest = (WEB3AdminSrvRegiCustomerReferenceRequest)l_request;
        }
        else
        {
            String l_strErrorMessage = "�p�����[�^�̗ތ^���s���B";
            log.debug(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        
        //1.1 validate( )
        l_customerReferenceRequest.validate();
        
        //1.3 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //��Q�Ή� NO_1990
        //1.4 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, false);
        
        //1.5 validate���X����(String[])
        l_admin.validateBranchPermission(l_customerReferenceRequest.branchCode);
        
        //1.6 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode(); 
        
        //1.7 get�T�[�r�X�}�X�^�[
        WEB3SrvRegiServiceInfoManagement l_serviceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_serviceMaster = l_serviceInfoManagement.getSrvMaster(
            l_strInstitutionCode,
            l_customerReferenceRequest.serviceDiv,
            false);;
            
        WEB3SrvRegiApplicationRequiredService l_reqSrv = l_serviceMaster.getAppliRequiredSrv(false);
        
        if (l_reqSrv == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        String l_strLotDiv = l_reqSrv.getLotDiv();
        
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            //sortkeys' check
            if (l_customerReferenceRequest.sortKeys != null)
            {
                int l_intKeyLenth = l_customerReferenceRequest.sortKeys.length;
                final String[] l_strValidKeys = new String[]
                {
                    WEB3SrvRegiKeyItemDef.BRANCH_CODE,
                    WEB3SrvRegiKeyItemDef.ACCOUNT_CODE,
                    WEB3SrvRegiKeyItemDef.APPLI_ATTRIBUTE,
                    WEB3SrvRegiKeyItemDef.APPLI_LOT_DIV,
                    WEB3SrvRegiKeyItemDef.APPLI_START_DATE,
                    WEB3SrvRegiKeyItemDef.APPLI_END_DATE,
                    WEB3SrvRegiKeyItemDef.PAYMENT_DIV,
                    WEB3SrvRegiKeyItemDef.USE_AMT,
                    WEB3SrvRegiKeyItemDef.LAST_UPDATED_TIMESTAMP,
                    WEB3SrvRegiKeyItemDef.LAST_UPDATER
                };
                for (int i = 0; i < l_intKeyLenth; i++)
                {
                    boolean l_blnValidate = false;
                    for (int j = 0; j < l_strValidKeys.length; j++)
                    {
                        if (l_strValidKeys[j].equals(l_customerReferenceRequest.sortKeys[i].keyItem))
                        {
                            l_blnValidate = true;
                            break;
                        }
                    }
                    if (!l_blnValidate)
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                            this.getClass().getName() + STR_METHOD_NAME,
                            "���N�G�X�g�f�[�^.�T�[�r�X���p�\�[�g�L�[.�L�[���ڂ��u���X�v�u�ڋq�v�u�\�������敪�v�u�\�����I�敪�v�u�K�p�J�n���v�u�K�p�I�����v�u�o�^�敪�v�u���p�����v�u�ŏI�X�V���v"
                            + "�u�ŏI�X�V�ҁv�̒l�ȊO�̏ꍇ�A��O���X���[����B");
                    }
                }
            }
            
            //applilotdiv' check
            if (!WEB3SrvRegiAppliLotDivDef.TRIAL_APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.CANCEL.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.EVERYTHING.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.FREE_OBJECT.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.CANNOT_APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.EVERYTHING_ATTRIBUTE.equals(l_customerReferenceRequest.applyLotteryDiv))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00995, 
                    this.getClass().getName() + STR_METHOD_NAME);           
            }
        }
        else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            //sortkeys' check
            if (l_customerReferenceRequest.sortKeys != null)
            {
                int l_intKeyLenth = l_customerReferenceRequest.sortKeys.length;
                final String[] l_strValidKeys = new String[]
                {
                    WEB3SrvRegiKeyItemDef.BRANCH_CODE,
                    WEB3SrvRegiKeyItemDef.ACCOUNT_CODE,
                    WEB3SrvRegiKeyItemDef.APPLI_LOT_DIV,
                    WEB3SrvRegiKeyItemDef.APPLI_DATE,
                    WEB3SrvRegiKeyItemDef.APPLI_START_DATE,
                    WEB3SrvRegiKeyItemDef.APPLI_END_DATE,
                    WEB3SrvRegiKeyItemDef.PAYMENT_DIV,
                    WEB3SrvRegiKeyItemDef.USE_AMT,
                    WEB3SrvRegiKeyItemDef.LAST_UPDATED_TIMESTAMP,
                    WEB3SrvRegiKeyItemDef.LAST_UPDATER
                };
                for (int i = 0; i < l_intKeyLenth; i++)
                {
                    boolean l_blnValidate = false;
                    for (int j = 0; j < l_strValidKeys.length; j++)
                    {
                        if (l_strValidKeys[j].equals(l_customerReferenceRequest.sortKeys[i].keyItem))
                        {
                            l_blnValidate = true;
                            break;
                        }
                    }
                    if (!l_blnValidate)
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                            this.getClass().getName() + STR_METHOD_NAME);           
                    }
                }
            }
            
            //applilotdiv' check
            if (!WEB3SrvRegiAppliLotDivDef.APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.DEFEAT.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.CANCEL.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.EVERYTHING.equals(l_customerReferenceRequest.applyLotteryDiv))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00995, 
                    this.getClass().getName() + STR_METHOD_NAME);           
            }
        }
        
        String l_strAccountCodeInDb = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        //1.8 �����N�G�X�g�f�[�^.�ڋq�R�[�h != null�̏ꍇ��
        if (l_customerReferenceRequest.accountCode != null)
        {
        	//��Q�Ή� NO_2052
			int l_cntAccount = 0;
			
            for (int i = 0; i < l_customerReferenceRequest.branchCode.length; i++)
            {
                WEB3GentradeMainAccount l_mainAccount = null;
                try
                {
                    //1.8.1 get�ڋq(String, String, String)
                    l_mainAccount = l_accountManager.getMainAccount(l_strInstitutionCode, l_customerReferenceRequest.branchCode[i], l_customerReferenceRequest.accountCode);
        
                    //1.8.2 getAccountCode( )
                    l_strAccountCodeInDb = l_mainAccount.getAccountCode();
                    
                    //�ڋq�}�X�^�[�ɑ��݂��鐔��count
                    l_cntAccount++;
                    
					log.debug("�y�ڋq�R�[�h���݃`�F�b�NOK�z : " + l_customerReferenceRequest.accountCode);
                    break;
                }
                catch (WEB3BaseException l_e)
                {
                    continue;
                }
            }
            
            //���͂��ꂽ�ڋq�R�[�h���ڋq�}�X�^�[�ɑ��݂��Ȃ��ꍇ
            if(l_cntAccount == 0){
            	log.debug("�y�ڋq�R�[�h���݃`�F�b�NNG�z : " + l_customerReferenceRequest.accountCode);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01987,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"�ڋq�}�X�^�e�[�u���Ōڋq�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ");
            }
            
        }

        //1.9 get�T�[�r�X�\���o�^�ꗗ
        WEB3SrvRegiRegistService l_appliRegiService = 
            (WEB3SrvRegiRegistService) Services.getService(WEB3SrvRegiRegistService.class);
        
        Timestamp l_tsTrialStartFrom = null;
        Timestamp l_tsTrialStartTo = null;
        Timestamp l_tsApplyDateFrom = null;
        Timestamp l_tsApplyDateTo = null;
        if (l_customerReferenceRequest.trialStartFrom != null)
        {
            l_tsTrialStartFrom = new Timestamp(l_customerReferenceRequest.trialStartFrom.getTime());
        }
        if (l_customerReferenceRequest.trialStartTo != null)
        {
            l_tsTrialStartTo = new Timestamp(l_customerReferenceRequest.trialStartTo.getTime());
        }
        if (l_customerReferenceRequest.applyDateFrom != null)
        {
            l_tsApplyDateFrom = new Timestamp(l_customerReferenceRequest.applyDateFrom.getTime());
        }
        if (l_customerReferenceRequest.applyDateTo != null)
        {
            l_tsApplyDateTo = new Timestamp(l_customerReferenceRequest.applyDateTo.getTime());        
        }

        SrvRegiApplicationParams[] l_appParams = null;
        SrvAppliAttributeParams[] l_srvAppliAttributeParams = null;

        int l_intRowCount = 0;
        if (!WEB3SrvRegiAppliLotDivDef.FREE_OBJECT.equals(l_customerReferenceRequest.applyLotteryDiv)
            && !WEB3SrvRegiAppliLotDivDef.CANNOT_APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
            && !WEB3SrvRegiAppliLotDivDef.EVERYTHING_ATTRIBUTE.equals(l_customerReferenceRequest.applyLotteryDiv))
        {
            //get�T�[�r�X�\���o�^�ꗗ(String, String[], String, String, String, String, Timestamp, Timestamp, Timestamp,
            // Timestamp, �T�[�r�X���p�\�[�g�L�[[ ])
            l_appParams = l_appliRegiService.getServiceRegistLists(
                l_strInstitutionCode,
                l_customerReferenceRequest.branchCode,
                l_customerReferenceRequest.serviceDiv,
                l_strAccountCodeInDb,
                l_customerReferenceRequest.registDiv,
                l_customerReferenceRequest.applyLotteryDiv,
                l_tsTrialStartFrom,
                l_tsTrialStartTo,
                l_tsApplyDateFrom,
                l_tsApplyDateTo,
                l_customerReferenceRequest.sortKeys);
            l_intRowCount = l_appParams.length;
        }
        else
        {
            //get�T�[�r�X�\�������ꗗ(String, String[], String, String, String, Timestamp, �T�[�r�X���p�\�[�g�L�[[ ])
            l_srvAppliAttributeParams = l_appliRegiService.getServiceAttributeLists(
                l_strInstitutionCode,
                l_customerReferenceRequest.branchCode,
                l_customerReferenceRequest.serviceDiv,
                l_strAccountCodeInDb,
                l_customerReferenceRequest.applyLotteryDiv,
                l_tsTrialStartFrom,
                l_customerReferenceRequest.sortKeys);
            l_intRowCount = l_srvAppliAttributeParams.length;
        }


		//��Q�Ή� �ڋq���݃`�F�b�NNG�Ή�
        //1.10 <�J��Ԃ�����>
		ArrayList l_customerReferenceList = new ArrayList();
		
        for (int i = 0; i < l_intRowCount; i++)
        {
            SrvRegiApplicationParams l_params = null;
            SrvAppliAttributeParams l_srvAppliAttrParams = null;
            
			WEB3GentradeMainAccount l_mainAccount = null;
            
            try
            {
				//1.10.1 get�ڋq(String, String, String)
                if (!WEB3SrvRegiAppliLotDivDef.FREE_OBJECT.equals(l_customerReferenceRequest.applyLotteryDiv)
                    && !WEB3SrvRegiAppliLotDivDef.CANNOT_APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
                    && !WEB3SrvRegiAppliLotDivDef.EVERYTHING_ATTRIBUTE.equals(
                        l_customerReferenceRequest.applyLotteryDiv))
                {
                    l_params = l_appParams[i];
                    //�i���N�G�X�g.�\�����I�敪�� '7', '8', '9' �ł͂Ȃ��ꍇ�j
                    l_mainAccount = 
                        l_accountManager.getMainAccount(
                            l_strInstitutionCode,
                            l_params.getBranchCode(),
                            l_params.getAccountCode());
                }
                else
                {
                    l_srvAppliAttrParams = l_srvAppliAttributeParams[i];
                    //�i���N�G�X�g.�\�����I�敪�� '7', '8', '9' �̏ꍇ�j
                    l_mainAccount = 
                        l_accountManager.getMainAccount(
                            l_strInstitutionCode,
                            l_srvAppliAttrParams.getBranchCode(),
                            l_srvAppliAttrParams.getAccountCode());
                }
            }
            //�ڋq���݃`�F�b�NNG�Ή�
            catch(WEB3BaseException ex)
            {
            	//���̖��ׂփX�L�b�v
            	continue;
            }

            //1.10.2 �T�[�r�X���p�ڋq�ꗗ�ύX�Ɖ�׈ꗗ�s( )
            WEB3AdminSrvRegiCustomerReferenceGroup l_group = new WEB3AdminSrvRegiCustomerReferenceGroup();

            //1.10.3 <�v���p�e�B�E�Z�b�g>
            if (!WEB3SrvRegiAppliLotDivDef.FREE_OBJECT.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.CANNOT_APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.EVERYTHING_ATTRIBUTE.equals(l_customerReferenceRequest.applyLotteryDiv))
            {
                //(�\�����I�敪=���N�G�X�g�f�[�^.�\�����I�敪 != '7' �` '9' �̏ꍇ�j
                //���\���o�^ID=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�\���o�^ID( )
                l_group.applyRegId = WEB3StringTypeUtility.formatNumber(l_params.getRegistId());
                
                //�����I�敪=�\���v�T�[�r�X�I�u�W�F�N�g.get���I�ݒ�( ) 
                l_group.lotteryDiv = l_strLotDiv;
                
                //�����X�R�[�h=�T�[�r�X�\���o�^�I�u�W�F�N�g.get���X�R�[�h( )
                l_group.branchCode = l_params.getBranchCode();
                
                //���ڋq�R�[�h=�擾�����ڋq�I�u�W�F�N�g.get�\���ڋq�R�[�h( )
                l_group.accountCode = l_mainAccount.getDisplayAccountCode();
                
                //���\�����I�敪=(*1)
                if (WEB3SrvRegiCancelDivDef.USUAL_DEFAULT.equals(l_params.getCancelDiv()))
                {
                    //(*1-1) �T�[�r�X�\���o�^�I�u�W�F�N�g.get����敪( )="�ʏ�"�̏ꍇ
                    if (WEB3AppliLotDivDef.AUTO_ELECTION.equals(l_params.getAppliLotDiv()))
                    {
                        //��Q�Ή� NO_2082
                        //(*1-1-1) �T�[�r�X�\���o�^�I�u�W�F�N�g.get�\�����I�敪( )="�������I"�̏ꍇ 
                        if (WEB3DateUtility.compareToDay(l_params.getCancelLimitDate(),
                            GtlUtils.getTradingSystem().getSystemTimestamp()) >= 0)
                        { 
                            //(*1-1-1-1) �T�[�r�X�\���o�^�I�u�W�F�N�g.get�������I���������( )�����ݓ��t�̏ꍇ"�\��"���Z�b�g����B�@@�@@            
                            l_group.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.APPLI;
                        }
                        else
                        {
                            //(*1-1-1-2) �T�[�r�X�\���o�^�I�u�W�F�N�g.get�������I���������( )�����ݓ��t�̏ꍇ"���I�^�{�\��"���Z�b�g����B �@@�@@            
                            l_group.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI;                        
                        }
                    }
                    else
                    {
                        //(*1-1-2) �T�[�r�X�\���o�^�I�u�W�F�N�g.get�\�����I�敪( )="�������I"�ȊO�̏ꍇ 
                        //�@@�@@            �T�[�r�X�\���o�^�I�u�W�F�N�g.get�\�����I�敪( )���Z�b�g����B 
                        l_group.applyLotteryDiv = l_params.getAppliLotDiv();
                    }
                }
                else if (WEB3SrvRegiCancelDivDef.CANCEL.equals(l_params.getCancelDiv()))
                {
                    //(*1-2) �T�[�r�X�\���o�^�I�u�W�F�N�g.get����敪( )="���"�̏ꍇ"���"���Z�b�g����B
                    l_group.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.CANCEL;
                }
                
                //���\����=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�\����( )
                l_group.applyDate = l_params.getAppliDate();
                
                //���K�p�J�n��=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�J�n��( )
                l_group.trialStartDate = l_params.getAppliStartDate();
                
                //���K�p�I����=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�I����( )
                l_group.trialEndDate = l_params.getAppliEndDate();
                 
                //���o�^�敪=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�o�^�敪( )
                l_group.registDiv = l_params.getPaymentDiv();
                
                //�����p����=�T�[�r�X�\���o�^�I�u�W�F�N�g.get���p����( )
                if (!l_params.getUseAmtIsNull())
                {
                    l_group.chargeAmt = WEB3StringTypeUtility.formatNumber(l_params.getUseAmt());
                } 
                 
                //���ŏI�X�V��=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�ŏI�X�V��( )
                l_group.lastUpdateTime = l_params.getLastUpdatedTimestamp();
                 
                //���ŏI�X�V��=�T�[�r�X�\���o�^�I�u�W�F�N�g.get�ŏI�X�V��( )
                l_group.lastUpdater = l_params.getLastUpdater();
            }
            else
            {
                //(�\�����I�敪=���N�G�X�g�f�[�^.�\�����I�敪 == '7' �` '9' �̏ꍇ�j
                //�����X�R�[�h=�T�[�r�X�\�������I�u�W�F�N�g.get���X�R�[�h( )
                l_group.branchCode = l_srvAppliAttrParams.getBranchCode();

                //���ڋq�R�[�h=�擾�����ڋq�I�u�W�F�N�g.get�\���ڋq�R�[�h( )
                l_group.accountCode = l_mainAccount.getDisplayAccountCode();

                //���\�����I�敪=(*1)
                if (WEB3AppliAttributeDef.FREE_OBJECT.equals(l_srvAppliAttrParams.getAppliAttribute()))
                {
                    //�T�[�r�X�\�������I�u�W�F�N�g.get�\�������敪( )="�����Ώ�"�̏ꍇ
                    //'7' ���Z�b�g����B
                    l_group.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.FREE_OBJECT;
                }
                else if(WEB3AppliAttributeDef.CANNOT_APPLI.equals(l_srvAppliAttrParams.getAppliAttribute()))
                {
                    //�T�[�r�X�\�������I�u�W�F�N�g.get�\�������敪( )="�\���s��"�̏ꍇ
                    //'8' ���Z�b�g����B
                    l_group.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.CANNOT_APPLI;
                }

                //���K�p�J�n��=�T�[�r�X�\�������I�u�W�F�N�g.get�K�p����From( ) 
                l_group.trialStartDate = l_srvAppliAttrParams.getAppliStartDate();

                //���K�p�I����=�T�[�r�X�\�������I�u�W�F�N�g.get�K�p����To( )
                l_group.trialEndDate = l_srvAppliAttrParams.getAppliEndDate();

                //�������敪=�T�[�r�X�\�������I�u�W�F�N�g.get�����敪( )
                l_group.transactionDiv = l_srvAppliAttrParams.getProcDiv();

                //���ŏI�X�V��=�T�[�r�X�\�������I�u�W�F�N�g.get�ŏI�X�V��( )
                l_group.lastUpdateTime = l_srvAppliAttrParams.getLastUpdatedTimestamp();

                //���ŏI�X�V��=�T�[�r�X�\�������I�u�W�F�N�g.get�ŏI�X�V��( )
                l_group.lastUpdater = l_srvAppliAttrParams.getLastUpdater();
            }
            
            //���ׂ����X�g��
			l_customerReferenceList.add(l_group);
        }
        
        //���X�g�����̔z����쐬
		WEB3AdminSrvRegiCustomerReferenceGroup[] l_customerReferenceGroups = new 
			WEB3AdminSrvRegiCustomerReferenceGroup[l_customerReferenceList.size()];
		
		//���X�g����z��֕ϊ�
		l_customerReferenceList.toArray(l_customerReferenceGroups);
        
        //1.11 create���X�|���X( )
        WEB3AdminSrvRegiCustomerReferenceResponse l_response = 
            (WEB3AdminSrvRegiCustomerReferenceResponse)l_customerReferenceRequest.createResponse();
        
        //1.12 <�y�[�W���O����>
        //�P�j���X�|���X�̈ȉ��̍��ڂ�ݒ肷��B 
        //�����X�|���X.���y�[�W�����ڋq�ꗗ�ύX�Ɖ�ׂ̗v�f�������N�G�X�g.�y�[�W���\���s�� 
        //�@@�@@�@@        ���]�肪�o��ꍇ�́A�{�P�����l��ݒ�B 
        //�@@�@@�@@        ���ڋq�ꗗ�ύX�Ɖ�ׂ̗v�f����0(�\���Ώۃf�[�^�Ȃ�)�̏ꍇ�A0���Z�b�g�B
        int l_intPages = l_customerReferenceGroups.length / Integer.parseInt(l_customerReferenceRequest.pageSize);
        if (l_customerReferenceGroups.length % Integer.parseInt(l_customerReferenceRequest.pageSize) != 0)
        { 
            l_intPages++;
        }
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_intPages);
        
        //�����X�|���X.�����R�[�h��:�@@�ڋq�ꗗ�ύX�Ɖ�ׂ̗v�f�� 
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_customerReferenceGroups.length);
        
        //�����X�|���X.�\���y�[�W�ԍ�(�\�������y�[�W�ڂɂ����邩): 
        //�@@�@@�@@�@@        �ȉ��̏����ɍ��v����̂ł���΁A���N�G�X�g.�v���y�[�W�ԍ��B 
        //[�ڋq�ꗗ�ύX�Ɖ�ׂ̗v�f�� > (���N�G�X�g.�y�[�W���\���s���~(���N�G�X�g.�v���y�[�W�ԍ�-1) )] 
        //�@@�@@�@@�@@        ��L�ȊO�̏ꍇ�́A���X�|���X.���y�[�W�������̂܂ܐݒ�B 
        //�@@�@@�@@        ���������ʂ�PR�w����̗v���y�[�W�ԍ��ɖ����Ȃ��ꍇ�́A�ŏI�y�[�W�ɊY������f�[�^�����X�|���X�ɐݒ肷��B
        int l_intRequestPageIndex = Integer.parseInt(l_customerReferenceRequest.pageIndex);
        int l_intRequestPageSize = Integer.parseInt(l_customerReferenceRequest.pageSize);
        
        if (l_customerReferenceGroups.length > (l_intRequestPageIndex - 1) * l_intRequestPageSize)
        {
            l_response.pageIndex = l_customerReferenceRequest.pageIndex;
        }
        else
        {
            l_response.pageIndex = l_response.totalPages;
        }
         
        //�Q�j�ݒ��A���X�|���X.���y�[�W����0 �̏ꍇ�́A���X�|���X.�ڋq�ꗗ�ύX�Ɖ�׍s 
        //(�T�[�r�X���p�ڋq�ꗗ�ύX�Ɖ�׈ꗗ�s[ ])��null���Z�b�g����O���X���[����B 
        if ("0".equals(l_response.totalPages))
        {
            l_response.customerList = null;
            String l_strErrorMessage = "���X�|���X.���y�[�W����0�G���[.";
            log.debug(l_strErrorMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01080, 
                this.getClass().getName() + STR_METHOD_NAME);           
        }

        //�y�m�肵���ڋq�ꗗ�ύX�Ɖ�ׂ̂����A���X�|���X�ɐݒ肷�閾�ׂ����߂�B�z 
        //�P)�@@(���N�G�X�g.�y�[�W���\���s���~(���X�|���X.�\���y�[�W�ԍ�-1)�����A�m�肵���ڋq�ꗗ�ύX�Ɖ�ׂ̗v�f�� 
        //�X�L�b�v����B 
        //�Q)�@@��L�Ō��肵���ڋq�ꗗ�ύX�Ɖ�ׂ̗v�f�ԍ��` 
        //(�ڋq�ꗗ�ύX�Ɖ�ׂ̗v�f�ԍ��{���N�G�X�g.�y�[�W���\���s��) 
        //�܂łɊY������ڋq�ꗗ�ύX�Ɖ�ׂ��A���X�|���X�f�[�^.�ڋq�ꗗ�ύX�Ɖ�׍s�Z�b�g����B
        
        //save not displayed records
        WEB3AdminSrvRegiCustomerReferenceGroup[] l_validCustomerReferenceGroups = null;
        
        int l_intResponsePageIndex = Integer.parseInt(l_response.pageIndex);
        
        //save records count that alredy displayed.
        int l_intRolledCount = l_intRequestPageSize * (l_intResponsePageIndex - 1);
        
        int l_intValidRecordCount = l_customerReferenceGroups.length - l_intRolledCount;
        if (l_intValidRecordCount > l_intRequestPageSize)
        {
            l_intValidRecordCount = l_intRequestPageSize;
        }
        
        l_validCustomerReferenceGroups = new WEB3AdminSrvRegiCustomerReferenceGroup[l_intValidRecordCount];
        
        //copy the record that have not displayed.
        for (int i = 0; i < l_intValidRecordCount; i++)
        { 
            l_validCustomerReferenceGroups[i] = 
                l_customerReferenceGroups[l_intRolledCount + i];
        }
        
        //�y���X�|���X�̐ݒ�z 
        //�����X�|���X.�ڋq�ꗗ�ύX�Ɖ�׍s���y�[�W���O������s���Ċm�肳�����ڋq�ꗗ�ύX�Ɖ�ׂ̔z��
        l_response.customerList = l_validCustomerReferenceGroups;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
