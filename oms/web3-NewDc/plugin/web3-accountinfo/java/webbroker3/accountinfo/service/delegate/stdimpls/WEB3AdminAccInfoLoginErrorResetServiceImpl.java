head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginErrorResetServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�T�[�r�XImpl(WEB3AdminAccInfoLoginErrorResetServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginErrorResetService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginDisabledFlagDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�T�[�r�XImpl)<BR>
 * �Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�T�[�r�X�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginErrorResetServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoLoginErrorResetService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginErrorResetServiceImpl.class); 
    
    /**
     * @@roseuid 418F3A0103B9
     */
    public WEB3AdminAccInfoLoginErrorResetServiceImpl() 
    {
     
    }
    
    /**
     * ���O�C���G���[���Z�b�g���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g<BR>
     * ���̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g<BR>
     * ���N�G�X�g�̏ꍇ <BR>
     * �@@�|submit���Z�b�g()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415934A70174
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminAccInfoLoginErrorResetInputRequest)
        {
            l_response = getInputScreen((WEB3AdminAccInfoLoginErrorResetInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoLoginErrorResetRequest)
        {
            l_response = submitReset((WEB3AdminAccInfoLoginErrorResetRequest)l_request);
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
     * ���O�C���G���[���Z�b�g���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i���O�C���G���[���Z�b�g�jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B742900B7
     */
    protected WEB3AdminAccInfoLoginErrorResetInputResponse getInputScreen(WEB3AdminAccInfoLoginErrorResetInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoLoginErrorResetInputRequest l_request)";
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
        WEB3AdminAccInfoLoginErrorResetInputResponse l_response = (WEB3AdminAccInfoLoginErrorResetInputResponse)l_request.createResponse();
        
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit���Z�b�g)<BR>
     * ���O�C���G���[���Z�b�g�����{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i���O�C���G���[���Z�b�g�jsubmit���Z�b�g�v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetResponse
     * @@roseuid 415934A70184
     */
    protected WEB3AdminAccInfoLoginErrorResetResponse submitReset(WEB3AdminAccInfoLoginErrorResetRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitReset(WEB3AdminAccInfoLoginErrorResetRequest l_request)";
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
        
        //���O�C���e�[�u�����X�V����B
        LoginPK l_loginPk = new LoginPK(l_lngLoginId);
        
        Map l_changeMap = new HashMap();
        
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

        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAccInfoLoginErrorResetResponse l_response = (WEB3AdminAccInfoLoginErrorResetResponse)l_request.createResponse();        
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
