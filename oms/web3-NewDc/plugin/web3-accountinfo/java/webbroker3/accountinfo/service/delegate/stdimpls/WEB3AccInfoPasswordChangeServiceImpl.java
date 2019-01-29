head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoPasswordChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���Ïؔԍ��ύX�T�[�r�XImpl(WEB3AccInfoPasswordChangeServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoPasswordChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3SecurityLevelDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.gentrade.WEB3TradingPasswordUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l���Ïؔԍ��ύX�T�[�r�XImpl)<BR>
 * ���q�l���Ïؔԍ��ύX�T�[�r�X�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoPasswordChangeServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AccInfoPasswordChangeService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoPasswordChangeServiceImpl.class);
    
    /**
     * @@roseuid 418F39FF003E
     */
    public WEB3AccInfoPasswordChangeServiceImpl() 
    {
     
    }
    
    /**
     * �Ïؔԍ��ύX���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���q�l���Ïؔԍ��ύX���̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���q�l���Ïؔԍ��ύX���N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�ύX()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 416BBA0B0338
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AccInfoPasswordChangeInputRequest)
        {
            l_response = getInputScreen((WEB3AccInfoPasswordChangeInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AccInfoPasswordChangeCompleteRequest)
        {
            l_response = submitChange((WEB3AccInfoPasswordChangeCompleteRequest)l_request);
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
     * �Ïؔԍ��ύX���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u���q�l���i�Ïؔԍ��ύX�jget���͉�ʁv�Q�ƁB<BR>
     * @@param l_request - ���q�l���Ïؔԍ��ύX���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeInputResponse
     * @@roseuid 416BBA0B0357
     */
    protected WEB3AccInfoPasswordChangeInputResponse getInputScreen(WEB3AccInfoPasswordChangeInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AccInfoPasswordChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //��t���ԃ`�F�b�N���s���B  
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //�ڋq�I�u�W�F�N�g���擾����B 
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)getMainAccount();
        
        //���O�C��ID���擾����B
        long l_lngLoginId = l_mainAccount.getLoginId();
        
        //����p�X���[�h���[�e�B���e�B�𐶐�����B
        WEB3TradingPasswordUtility l_tradingPwdUtil = new WEB3TradingPasswordUtility(l_lngLoginId);
        
        WEB3AccInfoPasswordChangeInputResponse l_response = (WEB3AccInfoPasswordChangeInputResponse)l_request.createResponse();
        
        //�p�X���[�h�ŏ����F�@@getPasswordMinLength()�̖߂�l
        l_response.passwordLower = l_tradingPwdUtil.getPasswordMinLength();
        
        //�p�X���[�h�ő咷�F�@@getPasswordMaxLength()�̖߂�l
        l_response.passwordUpper = l_tradingPwdUtil.getPasswordMaxLength();
        
        //�p�X���[�h�`�F�b�N�����F�@@getPasswordCheckMode()�̖߂�l
        l_response.passwordCheckType = l_tradingPwdUtil.getPasswordCheckMode();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
    
    /**
     * (submit�ύX)<BR>
     * �Ïؔԍ��ύX�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u���q�l���i�Ïؔԍ��ύX�jsubmit�ύX�v�Q�ƁB<BR>
     * <BR>
     *  ==========================================================    <BR>
     *          �V�[�P���X�} :  ���q�l���i�Ïؔԍ��ύX�jsubmit�ύX                  <BR>
     *          ��̈ʒu     :  1.10  �icheckPassword() != CHECK_NORMAL�j�̏ꍇ �A <BR>
     * <BR>
     *          �V�p�X���[�h���s�� ��O���X���[����B <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag             : BUSINESS_ERROR_90216                    <BR> 
     * <BR>
     *          �V�p�X���[�h������s�� ��O���X���[����B <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag             : BUSINESS_ERROR_90214                    <BR> 
     * <BR>
     *          �V�p�X���[�h�����݃p�X���[�h�Ɠ��� ��O���X���[����B <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag             : BUSINESS_ERROR_90217                    <BR> 
     * <BR>
     *          �V�p�X���[�h�����O�C�����Ɠ��� ��O���X���[����B <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag             : BUSINESS_ERROR_90218                    <BR>  
     * <BR>
     *          �V�p�X���[�h�����p�X���[�h�i�R����ȑO�j�Ɠ��� ��O���X���[����B <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag             : BUSINESS_ERROR_90219                    <BR> 
     * <BR>
     *          �V�p�X���[�h�����p�X���[�h�i�R����ȑO�j�Ɨގ� ��O���X���[����B <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag             : BUSINESS_ERROR_90220                    <BR> 
     * <BR>
     *          �p�X���[�h�ύX���`�F�b�N�֘A�̃��O�C���^�C�v�����ɕs�������� ��O���X���[����B <BR>
     *          class           : WEB3SystemLayerException                <BR>
     *          tag             : SYSTEM_ERROR_80002                    <BR>
     *  ==========================================================    <BR>
     * <BR>
     * @@param l_request - ���q�l���Ïؔԍ��ύX���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeCompleteResponse
     * @@roseuid 416BBA0B0376
     */
    protected WEB3AccInfoPasswordChangeCompleteResponse submitChange(WEB3AccInfoPasswordChangeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AccInfoPasswordChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //��t���ԃ`�F�b�N���s���B  
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)getMainAccount();
        
        //�����`�F�b�N�C���X�^���X���擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_orderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //����p�X���[�h���`�F�b�N����B
        OrderValidationResult l_validationResult = l_orderValidator.validateTradingPassword(l_mainAccount, l_request.oldPassword);
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�`�F�b�N�G���[�̏ꍇ�͂��O���X���[����");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //���O�C��ID���擾����B
        long l_lngLoginId = l_mainAccount.getLoginId();
        
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //����p�X���[�h���[�e�B���e�B�𐶐�����B
        WEB3TradingPasswordUtility l_tradingPwdUtil = new WEB3TradingPasswordUtility(l_lngLoginId);
        
        //�Z�L�����e�B�E���x���ɏ]���āA�V�p�X���[�h�̒l�`�F�b�N�����s����B
        int l_intCheckResult = l_tradingPwdUtil.checkPassword(
            l_request.loginName,        //���N�G�X�g�f�[�^.���O�C����
            l_request.oldPassword,      //���N�G�X�g�f�[�^.���Ïؔԍ�
            l_request.newPassword1      //���N�G�X�g�f�[�^.�V�Ïؔԍ��P
            );
            
        //�icheckPassword() != CHECK_NORMAL�j�̏ꍇ�A�Ή������O���X���[����B
        switch (l_intCheckResult)
        {
            case WEB3PasswordUtility.CHECK_ERROR_LENGTH:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90216,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: " + l_lngLoginId + "�@@�V�p�X���[�h���s��."
                    );
                
            case WEB3PasswordUtility.CHECK_ERROR_CTYPE:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90214,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: " + l_lngLoginId + "�@@�V�p�X���[�h������s��."
                    );
                
            case WEB3PasswordUtility.CHECK_ERROR_SAME_CURRENT:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90217,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: " + l_lngLoginId + "�@@�V�p�X���[�h�����݃p�X���[�h�Ɠ���."
                    );
                
            case WEB3PasswordUtility.CHECK_ERROR_SAME_NAME:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90218,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: " + l_lngLoginId + "�@@�V�p�X���[�h�����O�C�����Ɠ���."
                    );
                    
            case WEB3PasswordUtility.CHECK_ERROR_SAME_BEFORE:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90219,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: " + l_lngLoginId + "�@@�V�p�X���[�h�����p�X���[�h�i�R����ȑO�j�Ɠ���."
                    );
                    
            case WEB3PasswordUtility.CHECK_ERROR_SIMILAR_BEFORE:
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_90220,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "LOGIN_ID: " + l_lngLoginId + "�@@�V�p�X���[�h�����p�X���[�h�i�R����ȑO�j�Ɨގ�."
                    );
                    
            case WEB3PasswordUtility.CHECK_ERROR_CONFIG:
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�p�X���[�h�ύX���`�F�b�N�֘A�̃��O�C���^�C�v�����ɕs��������."
                    );
        }

        //1����O�`�R����O����p�X���[�h���X�V����B
        //���@@�Z�L�����e�B�E���x�� == �h���h�܂��́A�h��⍂�h�̏ꍇ�̂�
        
        OpLoginAdminService l_opLoginAdminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            
        LoginInfo l_loginInfo = l_opLoginAdminService.getLoginInfo(l_lngLoginId);
        
        Map l_loginTypeAttr = l_opLoginAdminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());     
        String l_strSecurityLevel = (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.SECURITY_LEVEL);
        if (WEB3SecurityLevelDef.HIGH.equals(l_strSecurityLevel) || WEB3SecurityLevelDef.LITTLE_HIGH.equals(l_strSecurityLevel))
        {
            l_tradingPwdUtil.saveOldTradingPasswords(l_mainAccount.getTradingPassword());
        }
        
        //WEB3�Í����@@�\�ɂāA�V�Ïؔԍ����Í�������B
        WEB3Crypt l_crypt = new WEB3Crypt();
        String l_strEncryptPwd = l_crypt.encrypt(l_request.newPassword1);
        
        //�ڋq�}�X�^�X�V
        //getDataSourceObject()�ɂĎ擾�����ڋq�}�X�^�s�ɂ��āA����p�X���[�h���X�V�iDB Update�j����B
        Map l_changeMap = new Hashtable();
        
        //����p�X���[�h = �V�Ïؔԍ�(�Í���)
        l_changeMap.put("trading_password", l_strEncryptPwd);
        
        //����p�X���[�h�X�V�҃R�[�h = �����R�[�h
        l_changeMap.put("trading_password_updater", l_mainAccount.getAccountCode());
        
        //��������
        Date l_datProcessDate = GtlUtils.getSystemTimestamp();
        
        //����p�X���[�h�X�V���� = ��������
        l_changeMap.put("tr_pwd_last_update_timestamp", l_datProcessDate);
        
        //�X�V���� = ��������
        l_changeMap.put("last_updated_timestamp", l_datProcessDate);
               
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            l_queryProcessor.doUpdateQuery(l_mainAccountRow.getPrimaryKey(), l_changeMap);
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
        
        WEB3AccInfoPasswordChangeCompleteResponse l_response = (WEB3AccInfoPasswordChangeCompleteResponse)l_request.createResponse();
     
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
