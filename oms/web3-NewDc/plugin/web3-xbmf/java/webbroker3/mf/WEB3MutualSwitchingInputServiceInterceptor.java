head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�抷���̓T�[�r�X�C���^�Z�v�^(WEB3MutualSwitchingInputServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/24 ��O�� (���u) ���r���[    
*/

package webbroker3.mf;

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
 * ���M�抷���̓T�[�r�X�C���^�Z�v�^
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualSwitchingInputServiceInterceptor implements Interceptor
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualSwitchingInputServiceInterceptor.class);

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g��<BR>
     * �@@�@@�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = WEB3TradingTimeTypeDef.�����M��<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t���i = �h07�F�����M���h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h02�F���t�i�V�K�����j�h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute()<BR>
     * �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�@@�msetAttribute�ɓn���p�����^�n<BR>
     * �@@�@@�@@�ݒ�L�[�F ������ԃR���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * �@@�@@�@@�ݒ�l�F ������ԃR���e�L�X�g<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 40B1747600DA
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParam == null)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (l_serviceParam.length == 0)
        {
            log.debug("__parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�g���A�J�E���g�}�l�[�W�����擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_manager =
            (AccountManager) l_finApp.getAccountManager();

        //�،���ЃR�[�h�擾�p
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);                
        String l_strInstitutionCode = "";
        String l_strBranchCode = "";
        
        try
        {
            //�،���ЃR�[�h�F OpLoginSecurityService���ҏW
            MainAccount l_mainAccount = l_manager.getMainAccount(l_opLoginSec.getAccountId());
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            
            //���X�R�[�h = OpLoginSecurityService���ҏW
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
                            
            //�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
            //������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();  
                
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = WEB3TradingTimeTypeDef.�����M��
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
            
            //����J�����_�R���e�L�X�g.������t���i = �h07�F�����M���h<BR>
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MUTUAL_FUND);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h02�F���t�i�V�K�����j�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
          
            //-ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
            
            WEB3GentradeTradingTimeManagement.setTimestamp(); 
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "__an unexpected error__ when "
                    + " l_manager.getInstitution(l_strInstitutionCode)"
                    + " with l_strInstitutionCode = "
                    + l_strInstitutionCode,l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()",l_ex);
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
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@������ԊǗ�.OFFSET_TAG<BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 40B1747600FA
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
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@������ԊǗ�.OFFSET_TAG<BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 40B174760119
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
