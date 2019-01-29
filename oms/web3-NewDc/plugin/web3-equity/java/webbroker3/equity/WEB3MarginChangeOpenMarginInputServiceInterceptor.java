head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeOpenMarginInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������V�K�����̓T�[�r�X�C���^�Z�v�^(WEB3MarginChangeOpenMarginInputServiceInterceptor.java)
Author Name      : 2004/10/8 Ḗ@@��(���u) �V�K�쐬
*/

package webbroker3.equity;

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
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeInputRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p��������V�K�����̓T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �M�p��������V�K�����̓T�[�r�X�C���^�Z�v�^�N���X
 * @@author �@@��
 * @@version 1.0
 */
public class WEB3MarginChangeOpenMarginInputServiceInterceptor implements Interceptor 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3MarginChangeOpenMarginInputServiceInterceptor.class);
    /**
     * @@roseuid 4142B67A002C
     */
    public WEB3MarginChangeOpenMarginInputServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁA<BR>
     *       OpLoginSecurityService�̓��e��������ԃR<BR>
     *       ���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     *      OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h05�F�����h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     *        �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - �T�[�r�X���\�b�h����<BR>
     * @@return Object<BR>
     * @@roseuid 407CAB0A03E4
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
         final String STR_METHOD_NAME = "onCall(Method, Object[])";
            
         log.entering(STR_METHOD_NAME);
         if(l_serviceParams[0] == null)
         {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3MarginChangeOpenMarginInputServiceInterceptor" + "." + STR_METHOD_NAME);
         }
         //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
         WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
         //�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
         try
         {      
             Object l_request = l_serviceParams[0];
             log.debug("OnCall�̈����^�C�v���`�F�b�N_start");    
             if (l_request == null)
             {   
                 throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + "." + STR_METHOD_NAME);                                
             }
             if (l_request instanceof WEB3MarginOpenMarginChangeInputRequest )
             {
                 WEB3MarginOpenMarginChangeInputRequest   l_request0 =
                         (WEB3MarginOpenMarginChangeInputRequest ) l_request;                 
             }
             else
             {
                 throw new WEB3BaseRuntimeException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                         this.getClass().getName() + "." + STR_METHOD_NAME);                       
                        
             }                                
             // �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService��<BR>
             // ���e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
             String l_strInstitutionCode = null; //�،���ЃR�[�h
             String l_strBranchCode      = null; //���X�R�[�h
             long l_lngAccountId = 0;    // �����R�[�h
             FinApp l_finApp = null;         
             AccountManager l_accMgr = null; 
             MainAccount l_mainAccount = null;

             OpLoginSecurityService l_opLoginSec =
                 (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                   
             //MainAccount���擾
             log.debug("MainAccount���擾");
             l_lngAccountId = l_opLoginSec.getAccountId();
             l_finApp = (FinApp) Services.getService(FinApp.class);
             l_accMgr = l_finApp.getAccountManager();
                        
             l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);

             log.debug("l_orderUnit�擾���܂����B");
             //�،���ЃR�[�h���擾
             log.debug("�،���ЃR�[�h���擾");
             l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
             //���X�R�[�h���擾
             log.debug("���X�R�[�h���擾");
             l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
             //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
             l_context.setInstitutionCode(l_strInstitutionCode);
             //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
             l_context.setBranchCode(l_strBranchCode); 
             //����J�����_�R���e�L�X�g.�s��R�[�h = null
             l_context.setMarketCode(null);
             //����J�����_�R���e�L�X�g.��t���ԋ敪 =   �h01�F�����E�M�p�h�j�h
             l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY); 
             //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
             l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);        
             //����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p���
             l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);         
             //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h05�F����
             l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);         
             //������ԃR���e�L�X�g���Z�b�g����B
             ThreadLocalSystemAttributesRegistry.setAttribute(
                 WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                 l_context);                     
             //3�j�@@��t�����A���t���[�����Z�b�g����B
             WEB3GentradeTradingTimeManagement.setTimestamp();            
             log.exiting(STR_METHOD_NAME);
             return l_context;
         }
         catch (NotFoundException l_ex)
         {           
             log.error(STR_METHOD_NAME , l_ex);
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() +"." + STR_METHOD_NAME,
                 l_ex.toString(),
                 l_ex); 
            
         }       
         catch (WEB3BaseException l_ex)
         {   
             log.error(STR_METHOD_NAME,l_ex);
             throw new WEB3BaseRuntimeException(
                 l_ex.getErrorInfo(),
                 this.getClass().getName() +"." + STR_METHOD_NAME,
                 l_ex.toString(),
                 l_ex);      
         }        
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 407CAB0B000B
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object,Object)";           
        log.entering(STR_METHOD_NAME);
        log.debug("������ԊǗ�.TIMESTAMP_TAG = " + WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        log.debug("������ԊǗ�.OFFSET_TAG = " + WEB3GentradeTradingTimeManagement.OFFSET_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.debug("������ԊǗ�.TRADING_CAL_CONTEXT_PATH = " + WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 407CAB0B002B
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object,Throwable)";
        log.entering(STR_METHOD_NAME);       
        log.debug("������ԊǗ�.TIMESTAMP_TAG = " + WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        log.debug("������ԊǗ�.OFFSET_TAG = " + WEB3GentradeTradingTimeManagement.OFFSET_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.debug("������ԊǗ�.TRADING_CAL_CONTEXT_PATH = " + WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        log.exiting(STR_METHOD_NAME);
    }
}
@
