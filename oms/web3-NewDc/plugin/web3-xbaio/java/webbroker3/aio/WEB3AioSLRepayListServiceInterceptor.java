head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.29.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSLRepayListServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍψꗗ�T�[�r�X�C���^�Z�v�^(WEB3AioSLRepayListServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/12 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.757
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
 * (�،��S�ۃ��[���ԍψꗗ�T�[�r�X�C���^�Z�v�^)<BR>
 * �،��S�ۃ��[���ԍψꗗ�T�[�r�X�C���^�Z�v�^�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AioSLRepayListServiceInterceptor implements Interceptor
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayListServiceInterceptor.class);

    /**
     * @@roseuid 46E890830154
     */
    public WEB3AioSLRepayListServiceInterceptor()
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
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���������<BR>
     * �@@�R���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h37�F�،��S�ۃ��[���h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h08�F�o���h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ������<BR>
     * �@@�R���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 46DE36910388
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        // �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        // �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���������
        // �R���e�L�X�g�̃v���p�e�B���Z�b�g����B
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(
            OpLoginSecurityService.class);
        long l_lngAccountId = l_opLoginSecurityService.getAccountId();

        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
        l_context.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
        // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
        l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());
        // ����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h37�F�،��S�ۃ��[���h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SECURITY_LOAN);
        // ����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        // ����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.PAYMENT);
        // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h08�F�o���h
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.PAYMENT);

        // ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
        // �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        // �Q�j�@@��t�����A���t���[�����Z�b�g����B
        // �@@�|������ԊǗ�.setTimestamp()���R�[������B
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_context;
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
     * <BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 46DE3691038B
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 46DE3691038E
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

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

        log.exiting(STR_METHOD_NAME);
    }
}
@
