head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordResetServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���p�X���[�h���Z�b�g�T�[�r�XImpl(WEB3AdminAccInfoLoginPasswordResetServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginPK;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.PasswordUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginPasswordResetService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginDisabledFlagDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���p�X���[�h���Z�b�g�T�[�r�XImpl)<BR>
 * �Ǘ��҂��q�l���p�X���[�h���Z�b�g�T�[�r�X�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordResetServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoLoginPasswordResetService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordResetServiceImpl.class);    
         
    /**
     * @@roseuid 418F3A0003B9
     */
    public WEB3AdminAccInfoLoginPasswordResetServiceImpl() 
    {
     
    }
    
    /**
     * �p�X���[�h���Z�b�g���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���p�X���[�h���Z�b�g���̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���p�X���[�h���Z�b�g���N�G�X�g�̏ꍇ <BR>
     * �@@�|submit���Z�b�g()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4158E8500191
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminAccInfoLoginPasswordResetInputRequest)
        {
            l_response = getInputScreen((WEB3AdminAccInfoLoginPasswordResetInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoLoginPasswordResetRequest)
        {
            l_response = submitReset((WEB3AdminAccInfoLoginPasswordResetRequest)l_request);
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request
                );
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * �p�X���[�h���Z�b�g���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i�p�X���[�h���Z�b�g�jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���p�X���[�h���Z�b�g���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B72700366
     */
    protected WEB3AdminAccInfoLoginPasswordResetInputResponse getInputScreen(WEB3AdminAccInfoLoginPasswordResetInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoLoginPasswordResetInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //�Ǘ��҂̌����`�F�b�N���s���B
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_PASSWORD, true);
        
        //�،���ЃR�[�h���擾����B 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //�ڋq�I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        l_accountMgr.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        
        //���X�������`�F�b�N����B
        l_admin.validateBranchPermission(l_request.branchCode);

        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAccInfoLoginPasswordResetInputResponse l_response = (WEB3AdminAccInfoLoginPasswordResetInputResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit���Z�b�g)<BR>
     * �p�X���[�h���Z�b�g�����{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i�p�X���[�h���Z�b�g�jsubmit���Z�b�g�v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���p�X���[�h���Z�b�g���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetResponse
     * @@roseuid 4158E78A02CA
     */
    protected WEB3AdminAccInfoLoginPasswordResetResponse submitReset(WEB3AdminAccInfoLoginPasswordResetRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitReset(WEB3AdminAccInfoLoginPasswordResetRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //�Ǘ��҂̌����`�F�b�N���s���B
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_PASSWORD, true);
        
        //����p�X���[�h���`�F�b�N����B 
        l_admin.validateTradingPassword(l_request.password);
        
        //�،���ЃR�[�h���擾����B 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //�ڋq�I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = 
            l_accountMgr.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        
        //���X�������`�F�b�N����B
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //�ڋq�̃��O�C���h�c���擾����B
        long l_lngLoginId = l_mainAccount.getLoginId();
        
        //���O�C���h�c�ɊY�����郍�O�C���������擾����B
        OpLoginAdminService l_opLoginAdminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
        
        Map l_loginAttr = l_opLoginAdminService.getLoginAttributes(l_lngLoginId);
        
        //���O�C���������A�����p�X���[�h���擾����B
        String l_strInitPwd = (String)l_loginAttr.get(WEB3LoginAttributeKeyDef.INITIAL_PASSWORD);

        //�p�X���[�h�̕��������s���B
        WEB3Crypt l_crypt = new WEB3Crypt();
        String l_strDecryptPwd = l_crypt.decrypt(l_strInitPwd); 
        
        //�p�X���[�h���n�b�V��������B 
        String l_strHashPwd = PasswordUtils.hashPassword(l_strDecryptPwd);
        
        //���O�C���e�[�u�����X�V����B
        LoginPK l_loginPk = new LoginPK(l_lngLoginId);
        
        Map l_changeMap = new HashMap();
        
        //���O�C��.�p�X���[�h = hashPassword()�̖߂�l �������p�X���[�h��hash�������́B
        l_changeMap.put("hashed_password", l_strHashPwd);
        
        //���O�C��.���O�C���L���� = enabled
        l_changeMap.put("disabled", WEB3LoginDisabledFlagDef.ACCINFO_ENABLED);
        
        //���O�C��.���O�C���G���[�� = null
        //Need Test to verify - zhang-bn
        l_changeMap.put("failure_count", null);
        
        //���O�C��.�ŏI���O�C���G���[���� = null
        //Need Test to verify - zhang-bn
        l_changeMap.put("last_failure_timestamp", null);
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            l_queryProcessor.doUpdateQuery(l_loginPk, l_changeMap);
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
        
        //�O��p�X���[�h�X�V�҃R�[�h���Z�b�g����B
        l_loginAttr.put(WEB3LoginAttributeKeyDef.LAST_PASSWORDCHANGE_UPDATER, l_admin.getAdministratorCode());

        //�O��p�X���[�h�ύX���t���Z�b�g����B
        l_loginAttr.put(
            WEB3LoginAttributeKeyDef.LAST_PWDCHANGE, 
            WEB3PasswordUtility.loginAttributeDateFormat.format(GtlUtils.getSystemTimestamp())); 

        //�������\�p�X���[�h���Z�b�g����B        
        //arg0�ikey�j�F�@@���O�C��������.�������\�p�X���[�h�iWEB3_ENCRYPTED_PASSWORD�j
        //arg1�ivalue�j�F�@@�������\�����p�X���[�h��
        //���������\�����p�X���[�h
        //���O�C������.get(���O�C��������.�������\�����p�X���[�h)�̖߂�l
        l_loginAttr.put(WEB3LoginAttributeKeyDef.PASSWORD,l_strInitPwd);
        
        //���O�C���������X�V����B
        l_opLoginAdminService.setLoginAttributes(l_lngLoginId, l_loginAttr); 

        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAccInfoLoginPasswordResetResponse l_response = (WEB3AdminAccInfoLoginPasswordResetResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
