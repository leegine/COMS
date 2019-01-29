head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordResetServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ïؔԍ����Z�b�g�T�[�r�XImpl(WEB3AdminAccInfoPasswordResetServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountPK;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoPasswordResetService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���Ïؔԍ����Z�b�g�T�[�r�XImpl)<BR>
 * �Ǘ��҂��q�l���Ïؔԍ����Z�b�g�T�[�r�X�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordResetServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoPasswordResetService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoPasswordResetServiceImpl.class);   
    
    /**
     * @@roseuid 418F3A03030D
     */
    public WEB3AdminAccInfoPasswordResetServiceImpl() 
    {
     
    }
    
    /**
     * �Ïؔԍ����Z�b�g���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���Ïؔԍ����Z�b�g<BR>
     * ���̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���Ïؔԍ����Z�b�g<BR>
     * ���N�G�X�g�̏ꍇ <BR>
     * �@@�|submit���Z�b�g()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41649D6401C2
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminAccInfoPasswordResetInputRequest)
        {
            l_response = getInputScreen((WEB3AdminAccInfoPasswordResetInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoPasswordResetRequest)
        {
            l_response = submitReset((WEB3AdminAccInfoPasswordResetRequest)l_request);
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
     * �Ïؔԍ����Z�b�g���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i�Ïؔԍ����Z�b�g�jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ïؔԍ����Z�b�g���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B77A101B1
     */
    protected WEB3AdminAccInfoPasswordResetInputResponse getInputScreen(WEB3AdminAccInfoPasswordResetInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoPasswordResetInputRequest l_request)";
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
        WEB3AdminAccInfoPasswordResetInputResponse l_response = (WEB3AdminAccInfoPasswordResetInputResponse)l_request.createResponse();        
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit���Z�b�g)<BR>
     * �Ïؔԍ����Z�b�g�����{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i�Ïؔԍ����Z�b�g�jsubmit���Z�b�g�v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ïؔԍ����Z�b�g���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetResponse
     * @@roseuid 41649D6401C4
     */
    protected WEB3AdminAccInfoPasswordResetResponse submitReset(WEB3AdminAccInfoPasswordResetRequest l_request) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " submitReset(WEB3AdminAccInfoPasswordResetRequest l_request)";
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
        
        //�ڋq�}�X�^�e�[�u�����X�V����B
        MainAccountPK l_mainAccountPk = new MainAccountPK(l_mainAccount.getAccountId());
        
        Map l_changeMap = new HashMap();
        
        //�ڋq�}�X�^.����p�X���[�h = get(���O�C��������.�������\�����p�X���[�h)�̖߂�l
        l_changeMap.put("trading_password", l_strInitPwd);
        
        //�ڋq�}�X�^.����p�X���[�h�X�V�҃R�[�h = �Ǘ���.�Ǘ��҃R�[�h
        l_changeMap.put("trading_password_updater", l_admin.getAdministratorCode());
        
        //��������
        Date l_datProcessDate = GtlUtils.getSystemTimestamp();
        
        //�ڋq�}�X�^.����p�X���[�h�X�V���� = TradingSystem.getSystemTymestamp()
        l_changeMap.put("tr_pwd_last_update_timestamp", l_datProcessDate);
        
        //�ڋq�}�X�^.�X�V���� = TradingSystem.getSystemTymestamp()
        l_changeMap.put("last_updated_timestamp", l_datProcessDate);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            l_queryProcessor.doUpdateQuery(l_mainAccountPk, l_changeMap);
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
        WEB3AdminAccInfoPasswordResetResponse l_response = (WEB3AdminAccInfoPasswordResetResponse)l_request.createResponse();        
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
