head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformSwElecDeliApplyServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�C���^�Z�v�^�N���X�iWEB3AdminInformSwElecDeliApplyServiceInterceptor.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 �����i���u�j�V�K�쐬 ���f��No.099
*/
package webbroker3.inform;

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
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�C���^�Z�v�^)<BR>
 * �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�C���^�Z�v�^�N���X<BR>
 * <BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminInformSwElecDeliApplyServiceInterceptor implements Interceptor
{

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformSwElecDeliApplyServiceInterceptor.class);

    public WEB3AdminInformSwElecDeliApplyServiceInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     *�P�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     *  �|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������� <BR>
     *  �R���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     *  <BR>
     *  ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     *  ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     *  ����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     *  ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h35�F�ڋq���`�[�쐬�h<BR>
     *  ����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h<BR>
     *  ����J�����_�R���e�L�X�g.������t���i = �h22:�@@�ڋq�T�[�r�X�h<BR>
     *  ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h00�FDEFAULT�h<BR>
     *  <BR>
     *  �|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ������ <BR>
     *  �R���e�L�X�g���Z�b�g����B<BR>
     *  �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     *  <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - (�T�[�r�X���\�b�h) <BR>
     * �T�[�r�X���\�b�h
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����) <BR>
     * �T�[�r�X���\�b�h����
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // ����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        try
        {
            //�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
            //  ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            MainAccount l_mainAccount = null;

            String l_strInstitutionCode = null;
            String l_strBranchCode = null;
            if (l_opLoginSec.isAccountIdSet())
            {
                // �Ǘ��҂�����ȊO���𔻒肵�A
                // MainAccount���擾
                l_mainAccount = l_accountManager.getMainAccount(l_opLoginSec.getAccountId());

                // �،���ЃR�[�h���擾
                l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();

                // ���X�R�[�h���擾
                l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            }
            else
            {
                // ����ID��null���ǂ����ŁA�Ǘ��҂̏ꍇ
                // �Ǘ��҂��擾
                WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

                // �،���ЃR�[�h���擾
                l_strInstitutionCode = l_administrator.getInstitutionCode();

                // ���X�R�[�h���擾
                l_strBranchCode = l_administrator.getBranchCode();
            }
            // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);

            // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode);

            // ����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

            // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h35�F�ڋq���`�[�쐬�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.ACCOUNTINFO_VOUCHER_CREATE);

            // ����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            // ����J�����_�R���e�L�X�g.������t���i = �h22:�@@�ڋq�T�[�r�X�h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);

            // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h00�FDEFAULT�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);

            //�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
            //  �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            //�Q�j��t�����A���t���[�����Z�b�g����B
            //�|������ԊǗ�.setTimestamp()���R�[������B
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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

        log.exiting(STR_METHOD_NAME);
        return l_context;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCall���^�[���l) <BR>
     * onCall���^�[���l <BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l) <BR>
     * �T�[�r�X���\�b�h���^�[�� <BR>
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
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
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }

}
@