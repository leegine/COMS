head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionCancelOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�i�A���jOP��������T�[�r�X�C���^�Z�v�^�iWEB3ToSuccOptionCancelOrderServiceInterceptor.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 ���� (���u) �V�K�쐬 ���f��No.280
*/
package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���jOP��������T�[�r�X�C���^�Z�v�^)<BR>
 * �i�A���jOP��������T�[�r�X�C���^�Z�v�^<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3ToSuccOptionCancelOrderServiceInterceptor implements Interceptor
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionCancelOrderServiceInterceptor.class);

    /**
     * @@roseuid 47FDC51403C8
     */
    public WEB3ToSuccOptionCancelOrderServiceInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^��<BR>
     * �u�i�A���j�����w���I�v�V������������������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B <BR>
     * �@@-�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g���Ĕ��ʂ���B<BR>
     * �@@-�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B <BR>
     *      ��������OpLoginSecurityService���ҏW�B <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * <BR>
     * �@@-���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * �@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h12�F�����w���敨OP�i��������j�h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �i*1) �����Y�����R�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h06�F�I�v�V�����h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h06�F����h <BR>
     * <BR>
     *  (*1) �����Y�����R�[�h�̎擾���@@ <BR>
     * �@@���N�G�X�g�f�[�^.�h�c�ɊY������\�񒍕��P�ʃI�u�W�F�N�g�𐶐����A <BR>
     * �@@�\�񒍕��P�ʃI�u�W�F�N�g.get����().get�����Y�����R�[�h ()�Ŏ擾����B <BR>
     * <BR>
     * �@@-ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@-������ԊǗ�.setTimestamp()���R�[������B <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * @@return Object
     * @@roseuid 47A914B10155
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        // �Z�L�����e�B�T�[�r�X���擾
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        // �����h�c
        long l_lngAccountId = l_opLoginSec.getAccountId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        // ����J�����_�R���e�L�X�g
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strProductCode = null;
        try
        {
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            // �،���ЃR�[�h
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            // ���X�R�[�h
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            // �����R�[�h
            l_strAccountCode = l_mainAccount.getAccountCode();

            long l_lngOrderId;

            Object l_request = l_serviceParams[0];
            // ���N�G�X�g�f�[�^�̌^���u�i�A���j�����w���I�v�V������������������N�G�X�g�v�̏ꍇ�̂݁A���������b�N����B
            if (l_request instanceof WEB3SuccOptionsCancelCompleteRequest)
            {
                WEB3SuccOptionsCancelCompleteRequest l_completeRequest =
                    (WEB3SuccOptionsCancelCompleteRequest)l_request;
                l_lngOrderId = Long.parseLong(l_completeRequest.id);

                // �g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B
                l_accMgr.lockAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
            }
            else if (l_request instanceof WEB3SuccOptionsCancelConfirmRequest)
            {
                WEB3SuccOptionsCancelConfirmRequest l_confirmRequest =
                    (WEB3SuccOptionsCancelConfirmRequest)l_request;
                l_lngOrderId = Long.parseLong(l_confirmRequest.id);
            }
            else
            {
                log.debug("�p�����[�^�^�C�v�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�p�����[�^�^�C�v�s���B");
            }

            WEB3ToSuccOrderManagerImpl l_orderManager =
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit =
                l_orderManager.getReserveIfoOrderUnit(l_lngOrderId);

            IfoProductRow l_ifoProductRow = (IfoProductRow)l_toSuccIfoOrderUnit.getProduct().getDataSourceObject();
            l_strProductCode = l_ifoProductRow.getUnderlyingProductCode();
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
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
        l_context.setInstitutionCode(l_strInstitutionCode);
        // ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
        l_context.setBranchCode(l_strBranchCode);
        // ����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h12�F�����w���敨OP�i��������j�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP_CHANGE_CANCEL);
        // ����J�����_�R���e�L�X�g.���i�R�[�h = �i*1) �����Y�����R�[�h
        l_context.setProductCode(l_strProductCode);
        // ����J�����_�R���e�L�X�g.������t���i = �h06 : �I�v�V�����h
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
        // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h06�F����h
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);

        // �|ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        try
        {
            // ������ԊǗ�.setTimestamp()���R�[������B
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
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
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * @@roseuid 47A914B10165
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object) ";
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
     * (onThrowable)<BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * @@roseuid 47A914B10175
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable) ";
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
