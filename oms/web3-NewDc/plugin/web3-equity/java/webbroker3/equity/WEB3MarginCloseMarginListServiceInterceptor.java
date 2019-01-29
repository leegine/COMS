head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginListServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p������ψꗗ�T�[�r�X�C���^�Z�v�^�N���X(WEB3MarginCloseMarginListServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/30 ������ (���u) �V�K�쐬       
                   2005/01/06 �����a�� (SRA) JavaDoc�C��                 
*/
package webbroker3.equity;

import java.lang.reflect.Method;
import java.util.Hashtable;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
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
 * �i�M�p������ψꗗ�T�[�r�X�C���^�Z�v�^�N���X�j�B
 * @@author ������(���u)
 * @@version 1.0
 */

public class WEB3MarginCloseMarginListServiceInterceptor implements Interceptor
{

    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginCloseMarginListServiceInterceptor.class);

    /**
     * �ionCall�j�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@�����̕ϐ����Z�b�g����B<BR>
     * ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�ThreadLocal��<BR>
     * �����̕ϐ����Z�b�g����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�ݒ�L�[�F�@@CURRENT_PRICE<BR>
     * �@@�@@�@@�@@�@@�@@�@@�l�F�@@Hashtable�i�V�K�ɐ�������Hashtable�j<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     *      OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     *      OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null(*)<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h03�F�ԍρh<BR>
     * <BR>
     * �@@(*) �s��R�[�h�́Aexecute( )����R�[������郁�\�b�h���ŁA<BR>
     * �@@�@@�@@�������擾���Đݒ肷��B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParams �T�[�r�X���\�b�h����
     * @@return Object<BR>
     * @@roseuid 407CB55B0144
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = " onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);

            //�����̕ϐ����Z�b�g����B
            ThreadLocalSystemAttributesRegistry.setAttribute("CURRENT_PRICE", new Hashtable());

            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);

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
            //����J�����_�R���e�L�X�g.�s��R�[�h = null
            l_context.setMarketCode(null);

            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //����J�����_�R���e�L�X�g.������t���i = �h03�F�M�p����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);

            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h03�F�ԍρh
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);

            //������ԃR���e�L�X�g���Z�b�g����B
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
            //��t�����A���t���[�����Z�b�g����
            WEB3GentradeTradingTimeManagement.setTimestamp();


        }
        catch (NotFoundException l_ex)
        {
            log.error("�Y���f�[�^�Ȃ��B", new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex));
        }

        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_context;
    }

    /**
     * �ionReturn�j�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�A����ю����̃N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * CURRENT_PRICE
     * @@param l_context onCall���^�[���l
     * @@param l_returnValue �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 407CB55B0163
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = " onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);

        //����J�����_�R���e�L�X�g�A����ю����̃N���A�����B

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            "CURRENT_PRICE",
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ionThrowable�j�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�A����ю����̃N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * CURRENT_PRICE
     * @@param l_obj onCall���^�[���l
     * @@param l_throwable ��O�I�u�W�F�N�g
     * @@roseuid 407CB55B0182
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = " onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);

        //����J�����_�R���e�L�X�g�A����ю����̃N���A�����B

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            "CURRENT_PRICE",
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
