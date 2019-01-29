head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.34.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashoutCancelServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o������T�[�r�X�C���^�Z�v�^(WEB3AioCashoutCancelServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 ���� (���u) �V�K�쐬
                   2004/10/25 ���z (���u) ���r���[
                   2004/12/09 ���E (���u) �c�Ή�
*/

package webbroker3.aio;

import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import webbroker3.aio.message.WEB3AioCashoutCancelCompleteRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�o������T�[�r�X�C���^�Z�v�^)<BR>
 * (�o������T�[�r�X�C���^�Z�v�^�N���X)<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0   
 */

public class WEB3AioCashoutCancelServiceInterceptor implements Interceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutCancelServiceInterceptor.class);
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g��<BR>
     * �L���X�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     * OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     * OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h16�F�o���h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     * �h08�F�o���h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j�@@���N�G�X�g�f�[�^�̌^���u�o������������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B<BR> 
�@@   * �|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)
     * ���R�[������B<BR> 
     * ��������OpLoginSecurityService���ҏW�B<BR> 
     * @@param l_method - �T�[�r�X���\�b�h
     * @@param l_serviceParams - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 40F73E79007E
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
       
        // �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        
        // �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
        // ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B 
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        long l_lngAccountId = l_opLoginSec.getAccountId();
        log.debug("=============AccountID==============" + l_lngAccountId);
        
        //===========================FinApp==========================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        //�A�J�E���g�}�l�[�W���擾����
        AccountManager l_accMgr = l_finApp.getAccountManager();     
        
        try
        {
            //�ڋq���擾����
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
        
            //�|����J�����_�R���e�L�X�g�ɂ̎擾
            WEB3GentradeTradingClendarContext l_context = 
                new WEB3GentradeTradingClendarContext();
      
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            //�،���ЃR�[�h���擾����
            String l_strInstitutionCode = 
                l_mainAccount.getInstitution().getInstitutionCode();
            l_context.setInstitutionCode(l_strInstitutionCode);    
        
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            //���X�R�[�h���擾����
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            l_context.setBranchCode(l_strBranchCode);
        
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h16�F�o���h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.PAYMENT);
        
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
            //����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.PAYMENT);
        
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =  �h08�F�o�� 
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.PAYMENT);
        
            //�|ThreadLocalSystemAttributesRegistry.setAttribute()
            // �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B 
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                  WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                  l_context);
            
            // �Q�j�@@��t�����A���t���[�����Z�b�g����B
            //�|������ԊǗ�.setTimestamp()���R�[������B 
            WEB3GentradeTradingTimeManagement.setTimestamp();   
            
            //=========remain zhou-yong NO.1 begin ==============
            
            //�R�j�@@���N�G�X�g�f�[�^�̌^���u�o������������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B 
            //�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B 
            //��������OpLoginSecurityService���ҏW�B 
            
            //���N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g������擾����
            Object l_request = l_serviceParams[0];
            
            if (l_request instanceof WEB3AioCashoutCancelCompleteRequest)
            {
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager) l_accMgr;
                l_accountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_mainAccount.getAccountCode());
            }
            
            //=========remain zhou-yong NO.1 end ==============            
            
            //����J�����_�R���e�L�X�g�ɓ��e���Z�b�g���閾��
       }
        catch (NotFoundException l_ex)
        {
            log.error("__an unexpected error__ when get MainAccount", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 410098230119
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME =
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
            
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        //����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 41009823011C
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME =
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        //����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
            
        log.exiting(STR_METHOD_NAME);   
    }
}
@
