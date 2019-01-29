head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����p�U��������ύX�T�[�r�XImpl(WEB3AdminAccInfoExclusiveTransferAccountChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
                   2005/12/23 �A���� (���u) �d�l�ύXNo.073
                   2006/02/03 ���ہi���{���u�j�d�l�ύXNo.085
                   2006/02/08 ������ (���u) �c�a���C�A�E�gNo.012
                   2006/09/11 �Ԑi�@@ (���u) �d�l�ύX�Ǘ�No.123
                   2006/09/13 �Ԑi�@@ (���u) �d�l�ύX�Ǘ�No.127
                   2006/09/14 �Ԑi�@@ (���u) �d�l�ύX�Ǘ�No.128
                   2006/10/30 ��іQ (���u) ���f��134
                   2006/11/10 �����q (���u) �����̖��No.003
                   2006/11/10 ���� (���u) ���f��142
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoExclusiveTransferAccountChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExclusiveUseAccountStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.ExclusiveUseAccountCondRow;
import webbroker3.gentrade.data.ExclusiveUseAccountDao;
import webbroker3.gentrade.data.ExclusiveUseAccountPK;
import webbroker3.gentrade.data.ExclusiveUseAccountParams;
import webbroker3.gentrade.data.ExclusiveUseAccountRow;
import webbroker3.gentrade.data.FinInstitutionBankDao;
import webbroker3.gentrade.data.FinInstitutionBankRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l����p�U��������ύX�T�[�r�XImpl)<BR>
 * �Ǘ��҂��q�l����p�U��������ύX�T�[�r�X�����N���X<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoExclusiveTransferAccountChangeServiceImpl
    extends WEB3AccInfoClientRequestService
    implements WEB3AdminAccInfoExclusiveTransferAccountChangeService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoExclusiveTransferAccountChangeServiceImpl.class);

    /**
     * @@roseuid 418F3A08033C
     */
    public WEB3AdminAccInfoExclusiveTransferAccountChangeServiceImpl()
    {

    }

    /**
     * ��p�U��������ύX���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����p�U��������ύX<BR>
     * ����ظ��Ă̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����p�U��������ύX<BR>
     * �m�Fظ��Ă̏ꍇ <BR>
     * �@@�|validate�ύX()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����p�U��������ύX<BR>
     * ����ظ��Ă̏ꍇ <BR>
     * �@@�|submit�ύX()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415CC5690116
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����p�U��������ύX����ظ��Ă̏ꍇ
        if (l_request instanceof WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest)
        {
            
            l_response = this.getInputScreen((WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest) l_request);
        }
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����p�U��������ύX�m�Fظ��Ă̏ꍇ
        else if (l_request instanceof WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest)
        {
            
            l_response = this.validateChange((WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest) l_request);
        }
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����p�U��������ύX����ظ��Ă̏ꍇ 
        else if (l_request instanceof WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest)
        {
            
            l_response = this.submitChange((WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest) l_request);
        }
        else
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * ��p�U��������ύX���͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i��p�U��������ύX�jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l����p�U��������ύX����ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B5CA30366
     */
    protected WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse getInputScreen(WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest l_request)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) validate( )
        l_request.validate();

        //1.2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3) validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRANSFER_ACCOUNT, true);

        //1.4) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //1.5) get�ڋq(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, 
            l_request.branchCode, 
            l_request.accountCode);

        //1.6) validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);

        //1.7) createResponse( )
        WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse l_response =
            (WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�ύX)<BR>
     * ��p�U��������ύX�m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i��p�U��������ύX�jvalidate�ύX�v�Q�ƁB <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u�Ǘ��҂��q�l���i��p�U��������ύX�jvalidate�ύX�v�Q�ƁB<BR>
     *  1.2.1.isExist���Z�@@��(String, String)<BR>
     * �@@�@@���݂��Ȃ��ꍇ�́A�u��s�f�[�^�s�����v�G���[���X���[����B<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_01314<BR> 
     * <BR>
     *  1.9.2.1�����ԍ��͊��Ɏg�p����Ă��܂��B�v�iBUSINESS_ERROR_02640�j�̗�O���X���[����B<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02640<BR> 
     *  ========================================================<BR>
     * @@param l_request - �Ǘ��҂��q�l����p�U��������ύX�m�Fظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse
     * @@roseuid 415CC5C3025E
     */
    protected WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse validateChange(WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest l_request)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateChange(WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) validate( )
        l_request.validate();

        if ( !l_request.exclusiveTransferAccountDeleteFlag )
        {
            //1.2.1) 1.2.1.isExist���Z�@@��(String, String)
            //���݂��Ȃ��ꍇ�́A�u��s�f�[�^�s�����v�G���[���X���[����B
            if (!isExistFinInstitution(l_request.changedAccountInfo.financialInstitutionCode, 
                l_request.changedAccountInfo.financialBranchCode))
            {
                log.debug("���Z�@@�ւ����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01314,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���Z�@@�ւ����݂��Ȃ��B");
            }
        }

        //1.3) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4) validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRANSFER_ACCOUNT, true);

        //1.5) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //1.6) get�ڋq(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, 
            l_request.branchCode, 
            l_request.accountCode);

        //1.7) validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);

        //1.8) �����̔Ԃ̏ꍇ�i���N�G�X�g�f�[�^.�����̔ԃt���O == true�j
        if (l_request.codeAutoFlag)
        {
            // 1.8.1 ��p�U������������e�[�u�����A���g�p�̌����ԍ����擾����B 
            //[get���g�p�����ԍ�()�Ɏw�肷�����] 
            // �،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
            // ��s�R�[�h�F ���N�G�X�g�f�[�^.���Z�@@�փR�[�h 
            // �x�X�R�[�h�F ���N�G�X�g�f�[�^.�x�X�R�[�h 
            // ���b�N�t���O�F false
            getUnusedAccountCode(
                l_administrator.getInstitutionCode(), 
                l_request.changedAccountInfo.financialInstitutionCode, 
                l_request.changedAccountInfo.financialBranchCode, 
                false);   
        }
        
        //1.9 )�蓮�̔ԂŌ����ԍ���null�ȊO�̏ꍇ�i���N�G�X�g�f�[�^.�����̔ԃt���O == false 
        //&& ���N�G�X�g�f�[�^.��p�U��������폜�t���O == false 
        //&& ���N�G�X�g�f�[�^.�����ԍ� != null�j
        if (!l_request.codeAutoFlag && 
            !l_request.exclusiveTransferAccountDeleteFlag && 
            l_request.changedAccountInfo.financialAccountCode != null)
        {
            //1.9.1)�����ԍ�����p�U������������e�[�u���Ŏg�p�ς݂��ǂ������`�F�b�N����B 
            //[get���������e�[�u�����R�[�h()�Ɏw�肷�����] 
            //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
            //��s�R�[�h�F ���N�G�X�g�f�[�^.���Z�@@�փR�[�h 
            //�x�X�R�[�h�F ���N�G�X�g�f�[�^.�x�X�R�[�h 
            //�����ԍ��F ���N�G�X�g�f�[�^.�����ԍ� 
            //���b�N�t���O�F false
            boolean l_blnUpdate = isFinAccountNoUpdatePossible(
                l_administrator.getInstitutionCode(), 
                l_request.changedAccountInfo.financialInstitutionCode, 
                l_request.changedAccountInfo.financialBranchCode, 
                l_request.changedAccountInfo.financialAccountCode,
                false);
            
            //1.9.2�����ԍ������Ɏg�p�ς݂̏ꍇ�iis�����ԍ��X�V�\() == false�j�A��O���X���[����B
            if (!l_blnUpdate )
            {
                //�����ԍ������Ɏg�p�ς݂̏ꍇ�iis�����ԍ��X�V�\() == false�j�A 
                log.exiting(STR_METHOD_NAME);
                //1.9.2.1�����ԍ��͊��Ɏg�p����Ă��܂��B�v�iBUSINESS_ERROR_02640�j�̗�O���X���[����B
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02640,
                    this.getClass().getName()+ STR_METHOD_NAME);                 
            }
        }
        
        //1.10) createResponse( )
        WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse l_response =
            (WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
 
    /**
     * (submit�ύX)<BR>
     * ��p�U��������ύX�����������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i��p�U��������ύX�jsubmit�ύX�v�Q�ƁB <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u�Ǘ��҂��q�l���i��p�U��������ύX�jsubmit�ύX�v�Q�ƁB<BR>
     *  1.2.1.isExist���Z�@@��(String, String)<BR>
     * �@@�@@���݂��Ȃ��ꍇ�́A�u��s�f�[�^�s�����v�G���[���X���[����B<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_01314<BR> 
     * <BR>
     *  1.11.2.2 �����ԍ����g�p�ς݂̏ꍇ�iis�����ԍ��X�V�\() == false�j<BR>
     * �@@�u�����ԍ��͊��Ɏg�p����Ă��܂��B�v�iBUSINESS_ERROR_02640�j�̗�O���X���[����B<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02640<BR> 
     *  ========================================================<BR>
     * @@param l_request - �Ǘ��҂��q�l����p�U��������ύX����ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse
     * @@roseuid 415CC5C3026E
     */
    protected WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse submitChange(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest l_request)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " submitChange(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) validate( )
        l_request.validate();
               
        if ( !l_request.exclusiveTransferAccountDeleteFlag )
        {
            //1.2.1) isExist���Z�@@��(String, String) 
            //���݂��Ȃ��ꍇ�́A�u��s�f�[�^�s�����v�G���[���X���[����B
            if (!isExistFinInstitution(l_request.changedAccountInfo.financialInstitutionCode, 
                l_request.changedAccountInfo.financialBranchCode))
            {
                log.debug("���Z�@@�ւ����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01314,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���Z�@@�ւ����݂��Ȃ��B");
            }
        }
        
        //1.3) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4) validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRANSFER_ACCOUNT, true);

        //1.5) validate����p�X���[�h(String)
        l_administrator.validateTradingPassword(l_request.password);

        //1.6) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //1.7) get�ڋq(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        WEB3GentradeMainAccount l_mainAccount =
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, 
                l_request.branchCode, 
                l_request.accountCode);

        //1.8) validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);

        String l_strAccountCode = null;
        //1.9) get�Ǘ��҃R�[�h( )
        try
        {
            String l_strAdministratorCode = l_administrator.getAdministratorCode();
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //1.10) �폜�̏ꍇ�i���N�G�X�g�f�[�^.��p�U��������폜�t���O == true�j
            if (l_request.exclusiveTransferAccountDeleteFlag)
            {
                //1.10.1) doDeleteQuery(PrimaryKey)
                ExclusiveUseAccountPK l_exclusiveUseAccountPK = new ExclusiveUseAccountPK(l_mainAccount.getAccountId());
                l_queryProcessor.doDeleteQuery(l_exclusiveUseAccountPK);
            }
            
            //1.11) �폜�łȂ��ꍇ�i���N�G�X�g�f�[�^.��p�U��������폜�t���O == false�j
            else
            { 
                //1.11.1)�����̔Ԃ̏ꍇ�i���N�G�X�g�f�[�^.�����̔ԃt���O==true�j
                if (l_request.codeAutoFlag)
                {
                    //1.11.1.1)��p�U������������e�[�u�����A���g�p�̌����ԍ����擾����B 
                    //[get���g�p�����ԍ�()�Ɏw�肷�����] 
                    // �،���ЃR�[�h�F�Ǘ���.get�،���ЃR�[�h()�̖߂�l 
                    // ��s�R�[�h�F ���N�G�X�g�f�[�^.���Z�@@�փR�[�h 
                    //�x�X�R�[�h�F ���N�G�X�g�f�[�^.�x�X�R�[�h 
                    // ���b�N�t���O�F true
                    l_strAccountCode = getUnusedAccountCode(l_administrator.getInstitutionCode(), 
                        l_request.changedAccountInfo.financialInstitutionCode, 
                        l_request.changedAccountInfo.financialBranchCode, 
                        true);
                }
                
                //1.11.2 �蓮�̔ԂŌ����ԍ���null�ȊO�̏ꍇ�i���N�G�X�g�f�[�^.�����̔ԃt���O == false && ���N�G�X�g�f�[�^.�����ԍ� != null�j
                if (!l_request.codeAutoFlag && l_request.changedAccountInfo.financialAccountCode != null)
                {
                    //1.11.2.1�����ԍ�����p�U������������e�[�u���Ŏg�p�ς݂��ǂ������`�F�b�N����B 
                    //[get���������e�[�u�����R�[�h()�Ɏw�肷�����] 
                    // �،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l 
                    // ��s�R�[�h�F ���N�G�X�g�f�[�^.���Z�@@�փR�[�h 
                    // �x�X�R�[�h�F ���N�G�X�g�f�[�^.�x�X�R�[�h 
                    // �����ԍ��F ���N�G�X�g�f�[�^.�����ԍ� 
                    // ���b�N�t���O�F true
                    boolean l_blnUpdate = isFinAccountNoUpdatePossible(
                        l_administrator.getInstitutionCode(), 
                        l_request.changedAccountInfo.financialInstitutionCode, 
                        l_request.changedAccountInfo.financialBranchCode, 
                        l_request.changedAccountInfo.financialAccountCode,
                        true);
                    l_strAccountCode = l_request.changedAccountInfo.financialAccountCode;
                    
                    //1.11.2.2 �����ԍ����g�p�ς݂̏ꍇ�iis�����ԍ��X�V�\() == false�j
                    //�u�����ԍ��͊��Ɏg�p����Ă��܂��B�v�iBUSINESS_ERROR_02640�j�̗�O���X���[����B
                    if (!l_blnUpdate )
                    {
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02640,
                            this.getClass().getName()+ STR_METHOD_NAME);                 
                    }

                }
                
                Timestamp l_systemTimestamp = GtlUtils.getSystemTimestamp();
                
                //1.11.3) isExist��p�U�������(long)
                if (this.isExistExclusiveTransferAccount(l_mainAccount.getAccountId()))
                { 
                    //1.11.4.1) doUpdateQuery(Row)
                    ExclusiveUseAccountParams l_exclusiveUseAccountParams =
                        new ExclusiveUseAccountParams(
                            ExclusiveUseAccountDao.findRowByPk(l_mainAccount.getAccountId()));
                     
                    l_exclusiveUseAccountParams.setAccountId(l_mainAccount.getAccountId());
                    l_exclusiveUseAccountParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
                    l_exclusiveUseAccountParams.setBranchCode(l_mainAccount.getBranch().getBranchCode());
                    l_exclusiveUseAccountParams.setAccountCode(l_mainAccount.getAccountCode());
                    l_exclusiveUseAccountParams.setFinInstitutionName(l_request.changedAccountInfo.financialInstitutionName);
                    l_exclusiveUseAccountParams.setFinBranchName(l_request.changedAccountInfo.financialBranchName);
                    l_exclusiveUseAccountParams.setFinBranchCode(l_request.changedAccountInfo.financialBranchCode);
                    l_exclusiveUseAccountParams.setFinAccountTypeName(l_request.changedAccountInfo.financialAccountTypeName);
                    l_exclusiveUseAccountParams.setFinAccountNo(l_strAccountCode);
                    l_exclusiveUseAccountParams.setFinAccountName(l_request.changedAccountInfo.financialAccountName);
                    l_exclusiveUseAccountParams.setLastUpdater(l_strAdministratorCode);
                    l_exclusiveUseAccountParams.setLastUpdatedTimestamp(l_systemTimestamp);
                    l_exclusiveUseAccountParams.setFinInstitutionCode(l_request.changedAccountInfo.financialInstitutionCode);
                    l_queryProcessor.doUpdateQuery(l_exclusiveUseAccountParams);
                }
                //1.11.5����ID�̃��R�[�h�����݂��Ȃ��ꍇ�iisExist��p�U�������() == false�j
                else
                {
                    
                    //1.11.5.1) doInsertQuery(Row)    
                    ExclusiveUseAccountParams l_exclusiveUseAccountParams = new ExclusiveUseAccountParams();

                    l_exclusiveUseAccountParams.setAccountId(l_mainAccount.getAccountId());
                    l_exclusiveUseAccountParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
                    l_exclusiveUseAccountParams.setBranchCode(l_mainAccount.getBranch().getBranchCode());
                    l_exclusiveUseAccountParams.setAccountCode(l_mainAccount.getAccountCode());
                    l_exclusiveUseAccountParams.setFinInstitutionName(l_request.changedAccountInfo.financialInstitutionName);
                    l_exclusiveUseAccountParams.setFinBranchName(l_request.changedAccountInfo.financialBranchName);
                    l_exclusiveUseAccountParams.setFinBranchCode(l_request.changedAccountInfo.financialBranchCode);
                    l_exclusiveUseAccountParams.setFinAccountTypeName(l_request.changedAccountInfo.financialAccountTypeName);
                    l_exclusiveUseAccountParams.setFinAccountNo(l_strAccountCode);
                    l_exclusiveUseAccountParams.setFinAccountName(l_request.changedAccountInfo.financialAccountName);
                    l_exclusiveUseAccountParams.setLastUpdater(l_strAdministratorCode);
                    l_exclusiveUseAccountParams.setCreatedTimestamp(l_systemTimestamp);
                    l_exclusiveUseAccountParams.setLastUpdatedTimestamp(l_systemTimestamp);
                    l_exclusiveUseAccountParams.setFinInstitutionCode(l_request.changedAccountInfo.financialInstitutionCode);
                    l_queryProcessor.doInsertQuery(l_exclusiveUseAccountParams);
               
                }
                
                //1.11.6�X�V��������ԍ���null�ȊO�̏ꍇ�B 
                //�i���N�G�X�g�f�[�^.�����̔� == true 
                // ����  
                //���N�G�X�g�f�[�^.�����̔� == false && ���N�G�X�g�f�[�^.�����ԍ� != null�j
                if (l_request.codeAutoFlag || 
                    (!l_request.codeAutoFlag && l_request.changedAccountInfo.financialAccountCode != null))
                {
                    //1.11.6.1update��p�U������������e�[�u��(String, String, String, String, String)
                    //��p�U������������e�[�u���̍X�V�i�X�e�[�^�X���g�p�ς݁j���s���B
                    //[update��p�U������������e�[�u��()�Ɏw�肷�����]
                    //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l
                    //��s�R�[�h�F ���N�G�X�g�f�[�^.���Z�@@�փR�[�h
                    //�x�X�R�[�h�F ���N�G�X�g�f�[�^.�x�X�R�[�h
                    //�����ԍ��F
                    //�i�����̔Ԃ̏ꍇ�jget���g�p�����ԍ�()�̖߂�l
                    //�i�蓮�̔Ԃ̏ꍇ�j���N�G�X�g�f�[�^.�����ԍ�
                    //�Ǘ��҃R�[�h�F�Ǘ���.get�Ǘ��҃R�[�h()�̖߂�l
                    updateExclusiveUseAccountCond(l_administrator.getInstitutionCode(),
                        l_request.changedAccountInfo.financialInstitutionCode,
                        l_request.changedAccountInfo.financialBranchCode,
                        l_strAccountCode,
                        l_strAdministratorCode);

                }
            }
        }
        catch (DataFindException l_ex)
        {
            
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    
        //1.12 createResponse( )
        WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse l_response =
            (WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse) l_request.createResponse();

        //1.13�v���p�e�B�Z�b�g
        //���N�G�X�g�f�[�^.�����̔ԃt���O==true && ���N�G�X�g�f�[�^.��p�U��������폜�t���O==false�̏ꍇ
        //    �����ԍ� = get���g�p�����ԍ�()�̖߂�l
        //���N�G�X�g�f�[�^.�����̔ԃt���O==false && ���N�G�X�g�f�[�^.��p�U��������폜�t���O==false�̏ꍇ
        //   �����ԍ� = ���N�G�X�g�f�[�^.�����ԍ�
        //���N�G�X�g�f�[�^.��p�U��������폜�t���O==true�̏ꍇ
        //�����ԍ� = null
        l_response.accountCode = l_strAccountCode;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (isExist��p�U�������)<BR>
     * ��p�U��������e�[�u���Ɋ����s�����݂��邩�𔻒肷��B<BR>
     * <BR>
     * �P�j�@@��p�U��������e�[�u�����A�w��̌���ID�ɊY������s���擾����B<BR>
     * �Q�j�@@�s���擾�ł����true�A�擾�ł��Ȃ����false��ԋp����B<BR>
     * @@param l_lngAccountId - ����ID
     * @@return boolean
     * @@roseuid 4161161B00FF
     */
    protected boolean isExistExclusiveTransferAccount(long l_lngAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isExistExclusiveTransferAccount(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            ExclusiveUseAccountDao.findRowByPk(l_lngAccountId); 

        }
        catch (DataFindException l_ex)
        {
            
            return false;
        }
        catch (DataNetworkException l_ex)
        {
            
            log.error(" �\�����Ȃ��V�X�e���G���[���������܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (isExist���Z�@@��)<BR>
     * ���Z�@@�ցi��s�j�}�X�^�Ɋ����s�����݂��邩�𔻒肷��B<BR>
     * <BR>
     * �P�j�@@���Z�@@�ցi��s�j�}�X�^���A�w��̋�s�R�[�h�Ǝx�X�R�[�h�� <BR>
     * �@@�@@�@@�Y������s���擾����B <BR>
     * �Q�j�@@�s���擾�ł����true�A�擾�ł��Ȃ����false��ԋp����B<BR>
     * @@param l_strFinInstitutionCode - ��s�R�[�h
     * @@param l_strBranchCode - �x�X�R�[�h
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isExistFinInstitution(String l_strFinInstitutionCode, String l_strBranchCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isExistFinInstitution(String, String)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@���Z�@@�ցi��s�j�}�X�^���A�w��̋�s�R�[�h�Ǝx�X�R�[�h�ɊY������s���擾����B
        FinInstitutionBankRow l_finInstitutionBankRow = null;
        try
        {
            l_finInstitutionBankRow = FinInstitutionBankDao.findRowByPk(
                l_strFinInstitutionCode,
                l_strBranchCode);
            
            //�Q�j�@@�s���擾�ł����true�A�擾�ł��Ȃ����false��ԋp����B
            if (l_finInstitutionBankRow != null) 
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
        catch (DataFindException l_ex)
        {
            //��O���X���[
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataException l_ex)
        {
            //��O���X���[
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
        }
    }
    
    /**
     * (get���g�p�����ԍ�)<BR>
     * ��p�U������������e�[�u����薢�g�p�����ԍ����擾����B<BR>
     * <BR>
     * �@@�@@�����g�p�����̎擾�����͈ȉ��̒ʂ�B<BR>
     * �@@�@@�@@�E�Y���̏،���ЃR�[�h�A��s�R�[�h�A�x�X�R�[�h�A�X�e�[�^�X='0'�i���g�p�j�̌����ԍ����擾����B<BR>
     * �@@�@@�@@�E�쐬�����̌Â����̂���擾����<BR>
     * �@@�@@�@@�E�쐬�����̓������g�p�������������݂���ꍇ�͌����ԍ��̏��������̂���擾����B<BR>
     * �@@�@@�@@�E�i�����j���b�N�t���O==true�̏ꍇ��"FOR UPDATE NOWAIT"��t������B<BR>
     * �@@�@@�@@<BR>
     * �@@�@@�@@�@@�@@�@@�����T���v����<BR>
     * �@@�@@�@@�@@�@@�@@[select * from (select fin_account_no from exclusive_use_account_cond<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@where institution_code=�i�����j�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and fin_institution_code=�i�����j��s�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and fin_branch_code=�i�����j�x�X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and status='0'<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@order by created_timestamp, fin_account_no)<BR>
     * �@@�@@�@@�@@�@@�@@where rownum=1 for update nowait]<BR>
     * <BR>
     * <BR>
     * �P�j�@@Object�z��𐶐����A�ȉ���v�f�ɐݒ�<BR>
     * �@@�@@�@@Object[0]�i�����j�،���ЃR�[�h<BR>
     * �@@�@@�@@Object[1]�i�����j��s�R�[�h<BR>
     * �@@�@@�@@Object[2]�i�����j�x�X�R�[�h<BR>
     * <BR>
     * �Q�j�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B <BR>
     * �@@�@@�@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�@@�@@arg0�F�@@��p�U������������e�[�u��RowType<BR>
     * �@@�@@�@@�@@�@@arg1�F�@@"institution_code=?<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and fin_institution_code=?<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and fin_branch_code=?<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and status='0'"<BR>
     * �@@�@@�@@�@@�@@arg2�F�@@"created_timestamp, fin_account_no"<BR>
     * �@@�@@�@@�@@�@@arg3�F�@@"FOR UPDATE NOWAIT"�i�i�����j���b�N�t���O==true�̏ꍇ�ǉ�����B�j<BR>
     * �@@�@@�@@�@@�@@arg4�F�@@�P�j�ō쐬����Object�z��<BR>
     * �@@�@@�@@�@@�@@arg5�F�@@1<BR>
     * �@@�@@�@@�@@�@@arg6�F�@@0<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�lListPage�������ԍ����擾���ԋp����B<BR>
     * �@@�@@ListPage�̒���==0 OR ListPage==null�̏ꍇ�A<BR>
     * �@@�@@�u���g�p�̐�p�U��������ԍ����擾�ł��܂���B�v�iBUSINESS_ERROR_02639�j�̗�O�𐶐�����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02639 <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@param l_strFinInstitutionCode - (��s�R�[�h)<BR>
     * ��s�R�[�h�B<BR>
     * @@param l_strFinBranchCode - (�x�X�R�[�h)<BR>
     * �x�X�R�[�h�B<BR>
     * @@param l_bolLockFlag - (���b�N�t���O)<BR>
     * �s���b�N�t���O�B<BR>
     * <BR>
     * true�c�s���b�N���s���B<BR>
     * false�c�s���b�N���s��Ȃ��B<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44FBBAE4030E
     */
    protected String getUnusedAccountCode(String l_strInstitutionCode, 
        String l_strFinInstitutionCode, 
        String l_strFinBranchCode, 
        boolean l_blnLockFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getUnusedAccountCode(String, " + 
            "String, String, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strInstitutionCode == null || l_strFinInstitutionCode == null || 
            l_strFinBranchCode == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_strInstitutionCode] = " + l_strInstitutionCode +
                "[l_strFinInstitutionCode] = " + l_strFinInstitutionCode +
                "[l_strFinBranchCode] = " + l_strFinBranchCode
                );
        }
        
        //�P�j�@@Object�z��𐶐����A�ȉ���v�f�ɐݒ� 
        //Object[0]�i�����j�،���ЃR�[�h 
        //Object[1]�i�����j��s�R�[�h 
        //Object[2]�i�����j�x�X�R�[�h
        Object[] l_objWhere = { 
            l_strInstitutionCode, 
            l_strFinInstitutionCode, 
            l_strFinBranchCode, 
            WEB3ExclusiveUseAccountStatusDef.UNUSED_RECORD
            };
        
        //�Q�j�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B  
        //[doFindAllQuery()�ɃZ�b�g����p�����[�^]
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException
            //arg1�F�@@"institution_code=? 
            //and fin_institution_code=? 
            //and fin_branch_code=? 
            //and status='0'" 
            String l_strArg1 = " institution_code = ? and fin_institution_code = ? " + 
                " and fin_branch_code = ? and status = ? ";
            
            //arg2�F�@@"created_timestamp, fin_account_no"
            String l_strArg2 = "created_timestamp, fin_account_no";

            // arg3�F�@@"FOR UPDATE NOWAIT"�i�i�����j���b�N�t���O==true�̏ꍇ�ǉ�����B�j 
            String l_strArg3 = null;
            if (l_blnLockFlag)
            {
                l_strArg3 = "FOR UPDATE NOWAIT";
            }
            // arg4�F�@@�P�j�ō쐬����Object�z�� 
            // arg5�F�@@1
            // arg6�F�@@0 
            List l_lisRecords = l_processor.doFindAllQuery(
                ExclusiveUseAccountCondRow.TYPE,
                l_strArg1, 
                l_strArg2, 
                l_strArg3, 
                l_objWhere, 
                1, 
                0
                    );
             //�Q�j �P�j�̖߂�lListPage�������ԍ����擾���ԋp����B 
             //ListPage�̒���==0 OR ListPage==null�̏ꍇ�A 
             //�u���g�p�̐�p�U��������ԍ����擾�ł��܂���B�v�iBUSINESS_ERROR_02639�j�̗�O�𐶐�����B   
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02639,
                    this.getClass().getName()+ STR_METHOD_NAME);                       
            }

            ExclusiveUseAccountCondRow l_exclusiveUseAccountCondRow = (ExclusiveUseAccountCondRow)
                l_lisRecords.get(0);
            
            String l_strFinAccountNo = l_exclusiveUseAccountCondRow.getFinAccountNo();
                
            log.exiting(STR_METHOD_NAME);
            return l_strFinAccountNo;     
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
        
    }

    /**
     * (is�����ԍ��X�V�\)<BR>
     * ��p�U��������e�[�u���ɊY�����������݂��邩�𔻒肵�A  <BR>
     * ���݂��Ȃ��ꍇ���A�i�����j���b�N�t���O==true�̏ꍇ��  <BR>
     * ��p�U������������e�[�u���Y���������R�[�h�̍s�̃��b�N���s���B <BR>
     * <BR>
     * �P�j�@@Object�z��𐶐����A�ȉ���v�f�ɐݒ� <BR>
     *        Object[0]�i�����j�،���ЃR�[�h <BR>
     *        Object[1]�i�����j��s�R�[�h <BR>
     *        Object[2]�i�����j�x�X�R�[�h <BR>
     *        Object[3]�i�����j�����ԍ� <BR>
     * <BR>
     * �Q�j�@@��p�U��������e�[�u���Ɍ����ԍ������݂��邩��������B <BR>
     *       QueryProcessor.doFindAllQuery()���\�b�h���R�[������B  <BR>
     * <BR>
     *       [doFindAllQuery()�ɃZ�b�g����p�����[�^]  <BR>
     * �@@�@@     arg0�F�@@��p�U��������e�[�u��RowType  <BR>
     * �@@�@@     arg1�F�@@"institution_code=? <BR>
     *                     and fin_institution_code=? <BR>
     *                     and fin_branch_code=? <BR>
     *                     and fin_account_no=?" <BR>
     * �@@�@@     arg2�F�@@�P�j�ō쐬����Object�z�� <BR>
     * <BR>
     * �R�j �Q�j�̖߂�lList�̒��� > 0 �̏ꍇ�Y�������g�p���ׁ̈Afalse�i�X�V�s�j��ԋp����B <BR>
     * <BR>
     * �S�j �Q�j�̖߂�lList�̒���==0 OR List==null�̏ꍇ�A���i�����j���b�N�t���O==true�̏ꍇ�B  <BR>
     * <BR>
     *   �S�|�P�j ��p�U������������e�[�u����ɊY���������R�[�h�����݂���ꍇ�͍s�̃��b�N���s���B  <BR>
     * <BR>
     *   �@@              QueryProcessor.doFindAllQuery()���\�b�h���R�[������B   <BR>
     *     �@@            [doFindAllQuery()�ɃZ�b�g����p�����[�^]   <BR>
     * �@@  �@@     �@@        arg0�F�@@��p�U������������e�[�u��RowType   <BR>
     * �@@�@@       �@@        arg1�F�@@"institution_code=?  <BR>
     *      �@@                         and fin_institution_code=?  <BR>
     *        �@@                       and fin_branch_code=?  <BR>
     *                     �@@          and fin_account_no=?"  <BR>
     * �@@  �@@        �@@     arg2�F�@@"FOR UPDATE NOWAIT"  <BR>
     * �@@  �@@        �@@     arg3�F�@@�P�j�ō쐬����Object�z��  <BR>
     * <BR>
     * �T�j true�i�X�V�\�j��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@param l_strFinInstitutionCode - (��s�R�[�h)<BR>
     * ��s�R�[�h�B<BR>
     * @@param l_strFinBranchCode - (�x�X�R�[�h�j<BR>
     * �x�X�R�[�h�B<BR>
     * @@param l_strFinAccountNo - (�����ԍ�)<BR>
     * �����ԍ��B<BR>
     * @@param l_bolLockFlag - (���b�N�t���O)<BR>
     * �s���b�N�t���O�B<BR>
     * <BR>
     * true�c�s���b�N���s���B<BR>
     * false�c�s���b�N���s��Ȃ��B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 44F6A0D90210
     */
    protected boolean isFinAccountNoUpdatePossible(String l_strInstitutionCode, 
        String l_strFinInstitutionCode, 
        String l_strFinBranchCode, 
        String l_strFinAccountNo, 
        boolean l_blnLockFlag)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isFinAccountNoUpdatePossible(String, " + 
            "String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strInstitutionCode == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_strInstitutionCode] = " + l_strInstitutionCode
                 );
        }
        
        //�P�j�@@Object�z��𐶐����A�ȉ���v�f�ɐݒ� 
        //Object[0]�i�����j�،���ЃR�[�h 
        //Object[1]�i�����j��s�R�[�h 
        //Object[2]�i�����j�x�X�R�[�h 
        //Object[3]�i�����j�����ԍ�
        Object[] l_objWhere = { 
            l_strInstitutionCode, 
            l_strFinInstitutionCode, 
            l_strFinBranchCode, 
            l_strFinAccountNo
            };
        
        String l_strArg1 = "institution_code = ? and fin_institution_code = ? " + 
            " and fin_branch_code = ? and fin_account_no = ? ";
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException
            
            //�Q�j�@@��p�U��������e�[�u���Ɍ����ԍ������݂��邩��������B 
            //QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            //[doFindAllQuery()�ɃZ�b�g����p�����[�^]  
            //arg0�F�@@��p�U��������e�[�u��RowType 
            //arg1�F�@@"institution_code=? 
            //and fin_institution_code=? 
            //        and fin_branch_code=?
            //        and fin_account_no=?"
            // arg2�F�@@�P�j�ō쐬����Object�z�� 
            List l_lisRecords = l_processor.doFindAllQuery(ExclusiveUseAccountRow.TYPE, 
                l_strArg1,
                l_objWhere);
            
            //�R�j �Q�j�̖߂�lList�̒��� > 0 �̏ꍇ�Y�������g�p���ׁ̈Afalse�i�X�V�s�j��ԋp����B
            if ( l_lisRecords != null && l_lisRecords.size() > 0)
            {   
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            
            //�S�j �Q�j�̖߂�lList�̒���==0 OR List==null�̏ꍇ�A���i�����j���b�N�t���O==true�̏ꍇ�B
            if ((l_lisRecords == null || l_lisRecords.isEmpty()) && l_blnLockFlag)
            {
                // �S�|�P�j ��p�U������������e�[�u����ɊY���������R�[�h�����݂���ꍇ�͍s�̃��b�N���s���B  
                // �@@              QueryProcessor.doFindAllQuery()���\�b�h���R�[������B   
                //   �@@            [doFindAllQuery()�ɃZ�b�g����p�����[�^]   
                //  �@@     �@@        arg0�F�@@��p�U������������e�[�u��RowType   
                //�@@       �@@        arg1�F�@@"institution_code=?  
                //    �@@                         and fin_institution_code=?  
                //      �@@                       and fin_branch_code=?  
                //                   �@@          and fin_account_no=?"  
                //  �@@        �@@     arg2�F�@@"FOR UPDATE NOWAIT"  
                // �@@        �@@      arg3�F�@@�P�j�ō쐬����Object�z��  
                l_processor.doFindAllQuery(
                    ExclusiveUseAccountCondRow.TYPE, 
                    l_strArg1,
                    "FOR UPDATE NOWAIT",
                    l_objWhere);
            }
            
            //�T�j true�i�X�V�\�j��ԋp����B 
            log.exiting(STR_METHOD_NAME);
            return true;
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
        catch (IllegalStateException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }  
        
    }
    
    /**
     * (update��p�U������������e�[�u��)<BR>
     * ��p�U������������e�[�u���̃X�e�[�^�X�X�V���s���B<BR>
     * �i�X�V���e�́y��Trade�z�⑫����.DB�X�V�Ǘ��ҁE��p�U��������ύX_��p�U������������e�[�u��.xls�Q�Ɓj<BR>
     * <BR>
     * �P�j�@@�����̔Ԏ��{��Ђ��ǂ����𔻒肷��B<BR>
     * �@@�@@�@@��p�U������������e�[�u������،���ЃR�[�h�������Ƃ��ă��R�[�h�������擾����B<BR>
     * <BR>
     * �Q�j�@@�P�j�̌��� ���R�[�h���� > 0 �̏ꍇ�A��p�U������������e�[�u���̃X�e�[�^�X�X�V���s���B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@Object�z��𐶐����A�ȉ���v�f�ɐݒ�<BR>
     * �@@�@@�@@�@@�@@�@@Object[0] = �i�����j�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@Object[1] = �i�����j��s�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@Object[2] = �i�����j�x�X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@Object[3] = �i�����j�����ԍ�<BR>
     * <BR>
     * �@@�Q�|�Q�j ��p�U������������e�[�u���̃X�e�[�^�X���X�V����B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�P�j�@@�X�V���e���u�R������-�l�v�̃y�A�Ŏ���Map�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�R�������F "status" �l�F "1" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�R�������F "last_updater" �l�F �i�����j�Ǘ��҃R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�R�������F "last_updated_timestamp" �l�F TradingSystem.getSystemTimestamp() <BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j�@@QueryProcessor.doUpdateAllQuery()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[doUpdateAllQuery()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg0�F�@@��p�U������������e�[�u��RowType <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg1�F�@@"institution_code=?<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and fin_institution_code=?<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and fin_branch_code=?<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and fin_account_no=?"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg2�F�@@�Q�|�P�j�ō쐬����Object�z��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@arg3�F�@@�Q�|�Q�|�P�j�ō쐬����Map <BR>
     * <BR>
     * �R�j�@@�P�j�̌��� ���R�[�h���� = 0 �̏ꍇ�A�����Ȃ��B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@param l_strFinInstitutionCode - (��s�R�[�h)<BR>
     * ��s�R�[�h�B<BR>
     * @@param l_strFinBranchCode - (�x�X�R�[�h)<BR>
     * �x�X�R�[�h�B<BR>
     * @@param l_strFinAccountNo - (�����ԍ�)<BR>
     * �����ԍ��B<BR>
     * @@param l_strAdministratorCode - (�Ǘ��҃R�[�h)<BR>
     * ���O�C���Ǘ��҃R�[�h�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44F696170165
     */
    protected void updateExclusiveUseAccountCond(
        String l_strInstitutionCode,
        String l_strFinInstitutionCode,
        String l_strFinBranchCode,
        String l_strFinAccountNo,
        String l_strAdministratorCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateExclusiveUseAccountCond(String, " + 
            "String, String, String, String)";
            log.entering(STR_METHOD_NAME);
    
        if (l_strInstitutionCode == null || l_strFinInstitutionCode == null || 
            l_strFinBranchCode == null || l_strFinAccountNo == null ||
            l_strAdministratorCode == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_strInstitutionCode] = " + l_strInstitutionCode +
                "[l_strFinInstitutionCode] = " + l_strFinInstitutionCode +
                "[l_strFinBranchCode] = " + l_strFinBranchCode +
                "[l_strFinAccountNo] = " + l_strFinAccountNo +
                "[l_strAdministratorCode] = " + l_strAdministratorCode
                );
        }
        
        //�P�j �����̔Ԏ��{��Ђ��ǂ����𔻒肷��B 
        //��p�U������������e�[�u������،���ЃR�[�h�������Ƃ��ă��R�[�h�������擾����B
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException

            String l_strCondWhere = "institution_code = ? ";
            Object[] l_objCondWhere = {l_strInstitutionCode};
            
            List l_lisCondRecords = l_processor.doFindAllQuery(
                ExclusiveUseAccountCondRow.TYPE,
                l_strCondWhere,
                null,
                l_objCondWhere
                );
            int l_intCondSize = 0;
            
            if (l_lisCondRecords != null)
            {
                l_intCondSize = l_lisCondRecords.size();
            }
            
            //�Q�j �P�j�̌��� ���R�[�h���� > 0 �̏ꍇ�A��p�U������������e�[�u���̃X�e�[�^�X�X�V���s���B 
            if (l_intCondSize > 0)
            {
                //�Q�|�P�j�@@Object�z��𐶐����A�ȉ���v�f�ɐݒ� 
                //Object[0] = �i�����j�،���ЃR�[�h 
                //Object[1] = �i�����j��s�R�[�h 
                //Object[2] = �i�����j�x�X�R�[�h 
                //Object[3] = �i�����j�����ԍ� 
                Object[] l_objUpdWhere = {l_strInstitutionCode, 
                    l_strFinInstitutionCode, 
                    l_strFinBranchCode, 
                    l_strFinAccountNo};

                //�Q�|�Q�j ��p�U������������e�[�u���̃X�e�[�^�X���X�V����
                //    �Q�|�Q�|�P�j �X�V���e���u�R������-�l�v�̃y�A�Ŏ���Map�I�u�W�F�N�g�𐶐�����B
                //�R�������F "status" �l�F "1"
                //�R�������F "last_updater" �l�F �i�����j�Ǘ��҃R�[�h
                //�R�������F "last_updated_timestamp" �l�F TradingSystem.getSystemTimestamp()
                Map l_map = new Hashtable();
                l_map.put("status", WEB3StatusDef.DEALT);
                l_map.put("last_updater", l_strAdministratorCode);
                l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

                //�Q�|�Q�|�Q�j�@@QueryProcessor.doUpdateAllQuery()���\�b�h���R�[������B  
                //[doUpdateAllQuery()�ɃZ�b�g����p�����[�^]  
                // arg0�F�@@��p�U������������e�[�u��RowType  
                // arg1�F�@@"institution_code=? 
                //              and fin_institution_code=? 
                //              and fin_branch_code=? 
                //              and fin_account_no=?" 
                //  arg2�F�@@�Q�|�P�j�ō쐬����Object�z�� 
                //  arg3�F�@@�Q�|�Q�|�P�j�ō쐬����Map 
                String l_strWhere = "institution_code = ? and fin_institution_code = ? " +
                    " and fin_branch_code = ? and fin_account_no = ? ";
                l_processor.doUpdateAllQuery(ExclusiveUseAccountCondRow.TYPE, 
                    l_strWhere, 
                    l_objUpdWhere, 
                    l_map);         
            }
            
            //�R�j �P�j�̌��� ���R�[�h���� = 0 �̏ꍇ�A�����Ȃ��B
            
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
        log.exiting(STR_METHOD_NAME);
    }      
}
@
