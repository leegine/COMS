head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoBuyOrderInputInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������t�������̓C���^�Z�v�^(WEB3RuitoBuyOrderInputInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/07 �m�X (���u) �V�K�쐬
*/

package webbroker3.xbruito;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �ݐϓ������t�������̓C���^�Z�v�^<BR>
 */
public class WEB3RuitoBuyOrderInputInterceptor implements Interceptor
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoBuyOrderInputInterceptor.class);

    /**
     *  �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     *  ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j  ����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@  �|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     *       ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     *                                   OpLoginSecurityService���ҏW<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.���X�R�[�h =<BR>
     *                                   OpLoginSecurityService���ҏW<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = <BR>
     *                                   WEB3TradingTimeTypeDef.����F<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t���i = �h08�F�ݐϓ����h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     *                                   �h01�F���t�i�V�K�����j�h<BR>
     * <BR>
     * �@@  �|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     *       �ɂĎ������ �R���e�L�X�g���Z�b�g����B<BR>
     * �@@ �@@�mgetAttribute�ɓn���p�����^�n<BR>
     * �@@�@@�@@�ݒ�L�[�F ������ԃR���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * �@@�@@�@@�ݒ�l�F ������ԃR���e�L�X�g<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@  �|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParam - �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 40693315001F
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME =
                            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);    
        long l_lngAccountId; // �����R�[�h
        String l_strInstitutionCode = null; // �،���ЃR�[�h
        String l_strBranchCode = null; // ���X�R�[�h

        //�Z�L�����e�B�T�[�r�X���擾
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService
            (OpLoginSecurityService.class);
        //AccountId���擾
        l_lngAccountId = l_opLoginSec.getAccountId();
        //FinApp�T�[�r�X���擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        try
        {
            MainAccount l_acc;
            l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            l_strBranchCode = l_acc.getBranch().getBranchCode();

            WEB3GentradeTradingClendarContext l_context = 
                new WEB3GentradeTradingClendarContext();
            
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h�ҏW  
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //����J�����_�R���e�L�X�g.���X�R�[�h�ҏW
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪=WEB3TradingTimeTypeDef.����F
            l_context.setTradingTimeType(
                WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
            
            //����J�����_�R���e�L�X�g.������t���i = �h08�F�ݐϓ����h
            l_context.setOrderAcceptProduct(
                WEB3OrderAccProductDef.MONTHLY_INVESTMENT_PLAN);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V����=�h01�F���t�h
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
            
            log.debug("�،���ЃR�[�h=" + l_context.getInstitutionCode());
            log.debug("���X�R�[�h=" + l_context.getBranchCode());
            log.debug("�s��R�[�h=" + l_context.getMarketCode());
            log.debug("�����R�[�h=" + l_context.getProductCode());
            log.debug("��t���ԋ敪=" + l_context.getTradingTimeType());
            log.debug("������t���i=" + l_context.getOrderAcceptProduct());
            log.debug("������t�g�����U�N�V����=" + l_context.getOrderAcceptTransaction());
            
            
            //������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
                
            //������ԊǗ�
            WEB3GentradeTradingTimeManagement.setTimestamp(); 

        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFoundException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("__WEB3GentradeTradingTimeManagement.setTimestamp Error__");
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
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ���<BR>
     *        ���e���N���A����B<BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@������ԊǗ�.OFFSET_TAG<BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 40693315003E
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
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ���<BR>
     *        ���e���N���A����B<BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@������ԊǗ�.OFFSET_TAG<BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 40693315004D
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
