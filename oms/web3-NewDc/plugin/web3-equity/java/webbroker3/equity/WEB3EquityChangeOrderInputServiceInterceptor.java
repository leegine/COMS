head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeOrderInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������������̓T�[�r�X�C���^�Z�v�^(WEB3EquityChangeOrderInputServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/12 ����� (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
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
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�������������������̓T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �������������������̓T�[�r�X�C���^�Z�v�^�N���X
 * @@version 1.0
 */
public class WEB3EquityChangeOrderInputServiceInterceptor implements Interceptor 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeOrderInputServiceInterceptor.class);
    
    /**
     * @@roseuid 40A075E7032B<BR>
     */
    public WEB3EquityChangeOrderInputServiceInterceptor() 
    {
        super();
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * 
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * 
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X<BR>
     * �g�̃v���p�e�B���Z�b�g����B<BR>
     * 
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null��ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�����h<BR>
     * 
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( <BR>
     * )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F"web3.tradingcalendarcontext"<BR>
     * 
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParam - �T�[�r�X���\�b�h����<BR>
     * @@return Object<BR>
     * @@roseuid 4064099200E4
     * */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[]) ";
        log.entering(STR_METHOD_NAME);

        //����J�����_�����p����R���e�L�X�g�𐶐�����B      
        
        long l_lngAccountId;                    // �����R�[�h
        String l_strInstitutionCode = null;     // �،���ЃR�[�h
        String l_strBranchCode = null;        // ���X�R�[�h

        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        l_lngAccountId = l_opLoginSec.getAccountId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();

        try
        {
            MainAccount l_acc;
            l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            l_strBranchCode = l_acc.getBranch().getBranchCode();

        }
        catch (NotFoundException nfe)
        {
            WEB3BaseRuntimeException l_runtimeException = new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
            log.error(STR_METHOD_NAME,l_runtimeException);
            throw l_runtimeException;
        }
        
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
            
        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
        l_context.setInstitutionCode(l_strInstitutionCode);
        // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
        l_context.setBranchCode(l_strBranchCode);
        // ����J�����_�R���e�L�X�g.�s��R�[�h = null��ҏW
        l_context.setMarketCode(null);
        // ����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        // ����J�����_�R���e�L�X�g.������t���i = �h�����h
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�����h
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
        
        // ������ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp(); //������ԊǗ�
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);                  
        }

        log.exiting(STR_METHOD_NAME);
        return null;       
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 406409920103<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

         ThreadLocalSystemAttributesRegistry.setAttribute(
             WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
             null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);     
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 406409920112<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object l_obj, Throwable l_throwable) ";
        log.entering(STR_METHOD_NAME);
          ThreadLocalSystemAttributesRegistry.setAttribute(
              WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
              null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);     
        log.exiting(STR_METHOD_NAME);
    }
}
@
