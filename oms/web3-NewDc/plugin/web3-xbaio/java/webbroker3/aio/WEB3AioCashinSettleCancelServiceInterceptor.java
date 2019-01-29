head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinSettleCancelServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����v�����~�T�[�r�X�C���^�Z�v�^�N���X(WEB3AioCashinSettleCancelServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 ���z (���u) �V�K�쐬
                   2004/10/22 ���� (���u) ���r���[    
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

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�����v�����~�T�[�r�X�C���^�Z�v�^)<BR>
 * �����v�����~�T�[�r�X�C���^�Z�v�^�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashinSettleCancelServiceInterceptor implements Interceptor 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettleCancelServiceInterceptor.class);       
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B <BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService<BR>
     * �̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     * OpLoginSecurityService���ҏW<BR> 
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     * OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h14�F�����h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h10�F�����h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR> 
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * @@return Object
     * @@roseuid 4119A89203AB
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        String l_strMethodName = "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(l_strMethodName);                

        //�P�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����
        
        //���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
        //������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
    
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(
            OpLoginSecurityService.class);
        
        long l_lngAccountId = l_opLoginSecurityService.getAccountId();
        
        try
        {
            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            //�،���ЃR�[�h���擾����
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            //���X�R�[�h���擾����
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
    
            //����J�����_�R���e�L�X�g.set�،���ЃR�[�h
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.set���X�R�[�h
            l_context.setBranchCode(l_strBranchCode);
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h14�F�����h 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.RECIEPT);
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.PAYMENT);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h10�F�����h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CASH_IN);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g���� 
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
            //�Q�j��t�����A���t���[�����Z�b�g����
            //������ԊǗ�.setTimestamp()���R�[������  
            WEB3GentradeTradingTimeManagement.setTimestamp();            
        }
        catch (NotFoundException l_ex)
        {
            log.error("error in l_accountManager.getMainAccount", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("error in setTimestamp", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
                        
        log.exiting(l_strMethodName);
        
        return null;
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B  <BR>
     *  <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B  <BR>
     *  <BR>
     * ������ԊǗ�.TIMESTAMP_TAG  <BR>
     * ������ԊǗ�.OFFSET_TAG  <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * @@roseuid 4119A89300AD
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        String l_strMethodName = "onReturn(Object l_context, Object l_returnValue)";
        log.entering(l_strMethodName);
        
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(l_strMethodName);
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     *  <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR> 
     *  <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B  <BR>
     *  <BR>
     * ������ԊǗ�.TIMESTAMP_TAG  <BR>
     * ������ԊǗ�.OFFSET_TAG  <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * @@roseuid 4119A893014A
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        String l_strMethodName = "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(l_strMethodName);
        
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����

        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(l_strMethodName);
    }
}
@
