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
filename	WEB3MstkExecuteReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����������Ɖ�T�[�r�X�C���^�Z�v�^(WEB3MstkExecuteReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 �d��(���u) �V�K�쐬
                   2004/12/29 �����a��(SRA) JavaDoc�C��
                   2005/01/05 �����a��(SRA) �������b�N�C��
*/

package webbroker3.equity;

import java.lang.reflect.Method;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

/**
 * �i�����~�j�����������Ɖ�T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �����~�j�����������Ɖ�T�[�r�X�C���^�Z�v�^
 * @@author �d��
 * @@version 1.0
 */
public class WEB3MstkExecuteReferenceServiceInterceptor implements Interceptor 
{
    
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MstkExecuteReferenceServiceInterceptor.class);
    
    /**
     * 
     */
    public WEB3MstkExecuteReferenceServiceInterceptor() 
    {
     
    }
    
    /**
     * �ionCall�j�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR> 
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService<BR>
     * �̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * 
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h =<BR>
     *  OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     * OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h05�F�����~�j�����h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h02�F�����~�j�����h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =  �h07�F�Ɖ�h <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B
     * @@param l_method arg0
     * @@param l_serviceParams arg1
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        
        final String STR_METHOD_NAME = "onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);

        OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        WEB3GenRequest l_request =(WEB3GenRequest) l_serviceParams[0];
        //����J�����_�R���e�L�X�g�𐶐�����
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        
        try
        {
            //FinApp�T�[�r�X���擾
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            AccountManager l_accountMgr = l_finApp.getAccountManager();
            MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_opLoginSec.getAccountId()); //throw NotFoundException

            l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());

            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
            l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
            l_context.setMarketCode(
                WEB3MarketCodeDef.DEFAULT);

            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h05�F�����~�j�����h
            l_context.setTradingTimeType(
                WEB3TradingTimeTypeDef.MINI_STOCK);

            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //����J�����_�R���e�L�X�g.������t���i = �h02�F�����~�j�����h
            l_context.setOrderAcceptProduct(
                WEB3OrderAccProductDef.MINI_STOCK);

            //����J�����_�R���e�L�X�g.������tWEB3TradingTimeTypeDef =�h07�F�Ɖ�h
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.REFERENCE);

            //������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            //��t�����A���t���[�����Z�b�g����B   
            WEB3GentradeTradingTimeManagement.setTimestamp();

            log.exiting(STR_METHOD_NAME);
            return l_context;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�Y���f�[�^�Ȃ��B", new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex));
        }

        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);           
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_context;
    }
    
    /**
     * �ionReturn�j�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_context arg0
     * @@param l_returnValue arg1
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME =
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * �ionThrowable�j�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_obj arg0
     * @@param l_throwable arg1
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME =
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
     
    }
}
@
