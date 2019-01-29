head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�X�C���^�Z�v�^(WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/17 �����F(���u) �V�K�쐬 �d�l�ύX���f��No.180
*/
package webbroker3.eqtypeadmin;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�X�C���^�Z�v�^)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�X�C���^�Z�v�^�N���X <BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor implements Interceptor
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor.class);

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor()
    {

    }

    /**
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B  <BR>
     * �ixTrade�J�[�l�����A�T�[�r�X���\�b�h�J�n���ɃR�[�������j  <BR>
     * <BR>
     * �P�j�@@�������ϒ���Row�I�u�W�F�N�g���擾����B <BR>
     * �@@�@@�T�[�r�X�̈���[0]���������ϒ���Row�ɃL���X�g����B <BR>
     * <BR>
     * �Q�j�@@�Ǘ��҃I�u�W�F�N�g���擾����B <BR>
     * �@@�@@�T�[�r�X�̈���[1]���Ǘ��҂ɃL���X�g����B <BR>
     * <BR>
     * �R�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B  <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h()�̖߂�l <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �������ϒ���Row.���XID�ɊY�����镔�X�R�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �������ϒ���Row.�s��ID�ɊY������s��R�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h  <BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h  <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = "�M�p���"  <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "�ԍ�" <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�ݒ�L�[�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
     * <BR>
     * �S�j�@@��t�����A���t���[�����Z�b�g����B  <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �T�j�@@�������ϒ����̔����R���X�L�b�v�t���O���Z�b�g����B <BR>
     * <BR>
     * �@@�@@"�������ϒ��������R���X�L�b�v�t���O"��LocalThread�ɃZ�b�g����B <BR>
     * �@@�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( <BR>
     * �@@�@@�@@�@@WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP, <BR>
     * �@@�@@�@@�@@BooleanEnum.True ) <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@�������ϒ���Row�I�u�W�F�N�g���擾����B
        //�@@�@@�T�[�r�X�̈���[0]���������ϒ���Row�ɃL���X�g����B
        AdminEqForcedSettleOrderRow l_forcedSettleOrderRow = null;
        if (l_serviceParams.length > 0 && l_serviceParams[0] instanceof AdminEqForcedSettleOrderRow)
        {
            l_forcedSettleOrderRow = (AdminEqForcedSettleOrderRow)l_serviceParams[0];
        }

        //�Q�j�@@�Ǘ��҃I�u�W�F�N�g���擾����B
        //�@@�@@�T�[�r�X�̈���[1]���Ǘ��҂ɃL���X�g����B
        WEB3Administrator l_administrator = (WEB3Administrator)l_serviceParams[1];

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        String l_strBranchCode = null;
        String l_strMarketCode = null;
        try
        {
            //���X�R�[�h = �������ϒ���Row.���XID�ɊY�����镔�X�R�[�h
            long l_lngBranchId = l_forcedSettleOrderRow.getBranchId();
            WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            l_strBranchCode = l_accMgr.getBranch(l_lngBranchId).getBranchCode();

            //�s��R�[�h = �������ϒ���Row.�s��ID�ɊY������s��R�[�h
            long l_lngMarketId = l_forcedSettleOrderRow.getMarketId();
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_finObjectManager.getMarket(l_lngMarketId);
            l_strMarketCode = l_market.getMarketCode();
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

        //�R�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h()�̖߂�l
        l_context.setInstitutionCode(l_administrator.getInstitutionCode());
        //����J�����_�R���e�L�X�g.���X�R�[�h = �������ϒ���Row.���XID�ɊY�����镔�X�R�[�h
        l_context.setBranchCode(l_strBranchCode);
        //����J�����_�R���e�L�X�g.�s��R�[�h = �������ϒ���Row.�s��ID�ɊY������s��R�[�h
        l_context.setMarketCode(l_strMarketCode);
        //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        //����J�����_�R���e�L�X�g.������t���i = "�M�p���"
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "�ԍ�"
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);

        //�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
        //�ݒ�L�[�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //�S�j�@@��t�����A���t���[�����Z�b�g����B
        //�|������ԊǗ�.setTimestamp()���R�[������B
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�T�j�@@�������ϒ����̔����R���X�L�b�v�t���O���Z�b�g����B
        //"�������ϒ��������R���X�L�b�v�t���O"��LocalThread�ɃZ�b�g����B
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP,
            BooleanEnum.TRUE);

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B  <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B  <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B  <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG  <BR>
     * ������ԊǗ�.OFFSET_TAG  <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
     * WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP<BR>
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        //WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP, null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B  <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B  <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B  <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG  <BR>
     * ������ԊǗ�.OFFSET_TAG  <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
     * WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP <BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * ��O�I�u�W�F�N�g<BR>
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

        //WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP,
            null);
        log.exiting(STR_METHOD_NAME);
    }
}
@
