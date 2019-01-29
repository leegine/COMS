head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMarginExecuteReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����E�M�p�������Ɖ�T�[�r�X�C���^�Z�v�^(WEB3EquityMarginExecuteReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 �����Q(���u) �V�K�쐬
Revesion History : 2007/03/14 �ʉ�(SRA) �d�l�ύX���f��1134
Revesion History : 2007/04/16 �����Q(���u) �d�l�ύX���f��1314
*/

package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�����E�M�p�������Ɖ�T�[�r�X�C���^�Z�v�^)<BR>
 * �����E�M�p�������Ɖ�T�[�r�X�C���^�Z�v�^�N���X<BR>
 * <BR>
 * @@ author �����Q <BR>
 * @@ version 1.0<BR>
 */
public class WEB3EquityMarginExecuteReferenceServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3EquityMarginExecuteReferenceServiceInterceptor.class);

    /**
     * @@roseuid 45A3606E0251
     */
    public WEB3EquityMarginExecuteReferenceServiceInterceptor()
    {

    }

    /**
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * �P�j ����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     *   �|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * <BR>
     * �|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g
     * �̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     *   ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     *   ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     *   ����J�����_�R���e�L�X�g.�s��R�[�h = null<BR>
     *   ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h<BR>
     *   ����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     *   ����J�����_�R���e�L�X�g.������t���i = �h�����h<BR>
     *   ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = (*1)<BR>
     * <BR>
     *   (*1)���N�G�X�g.�Ɖ�敪 == �h�������Ɖ�h �̏ꍇ�A�h�Ɖ�h<BR>
     *        ���N�G�X�g.�Ɖ�敪 == �h��������ꗗ�h �̏ꍇ�A�h�����h<BR>
     * <BR>
     *   �|ThreadLocalSystemAttributesRegistry.setAttribute(
     * )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     *      �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j ��t�����A���t���[�����Z�b�g����B<BR>
     *   �|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 455D3CB901C9
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = ".onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null)
        {
            log.debug("�p�����[�^��null�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //�P�j ����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        // �|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
        WEB3EquityMarginExecuteReferenceRequest l_request = (WEB3EquityMarginExecuteReferenceRequest)l_serviceParams[0];

        //�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g
        //�̃v���p�e�B���Z�b�g����B
        OpLoginSecurityService l_opLoginSecurityService = (OpLoginSecurityService)
            Services.getService(OpLoginSecurityService.class);

        try
        {
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            long l_lngAccountId = l_opLoginSecurityService.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            Institution l_institution = l_mainAccount.getInstitution();
            l_context.setInstitutionCode(l_institution.getInstitutionCode());

            // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_mainAccount.getBranch().getBranchCode());

            //����J�����_�R���e�L�X�g.�s��R�[�h = null
            l_context.setMarketCode(null);

            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

            //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //����J�����_�R���e�L�X�g.������t���i = �h�����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);

            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = (*1)
            if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(l_request.referenceType))
            {
                //(*1)���N�G�X�g.�Ɖ�敪 == �h�������Ɖ�h �̏ꍇ�A�h�Ɖ�h
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
            }
            else if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_request.referenceType))
            {
                //���N�G�X�g.�Ɖ�敪 == �h��������ꗗ�h �̏ꍇ�A�h�����h
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
            }

            //�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�ڋq�I�u�W�F�N�g���������Ȃ�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        }

        //�Q�j ��t�����A���t���[�����Z�b�g����B
        //������ԊǗ�.setTimestamp()���R�[������
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return null;
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
     * @@roseuid 455D3CB901F7
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = ".onReturn(Object, Object)";
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
     * @@roseuid 455D3CB90217
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = ".onThrowable(Object, Throwable)";
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
