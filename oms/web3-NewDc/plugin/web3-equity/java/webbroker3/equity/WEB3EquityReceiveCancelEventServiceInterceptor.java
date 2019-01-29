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
filename	WEB3EquityReceiveCancelEventServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������������ʒm����ꌏ�T�[�r�X�C���^�Z�v�^(WEB3EquityReceiveCancelEventServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �������F(SRA) �V�K�쐬
Revesion History : 2005/01/05 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity;

import java.lang.reflect.Method;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.define.WEB3EquityBackServiceOnlineDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i����������������ʒm����ꌏ�T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * ����������������ʒm����ꌏ�T�[�r�X�C���^�Z�v�^�N���X
 * @@author �������F
 * @@version 1.0
 */
public class WEB3EquityReceiveCancelEventServiceInterceptor
    implements Interceptor
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityReceiveCancelEventServiceInterceptor.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3EquityReceiveCancelEventServiceInterceptor()
    {
    }

    /**
     * (onCall)<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����A[��������ʒm����ꌏ�T�[�r�X]���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]��������������ʒm�L���[Params�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|�T�[�r�X�̈���[1]�𒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|������������ʒm�L���[Params�A�����P�ʂ̓��e���A������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@���،���ЃR�[�h�A���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@������������ʒm�L���[Params�̓����ڂ��Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@���s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��.�s��ID �ɊY������s��I�u�W�F�N�g�̎s��R�[�h���Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@����t���ԋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�h�����E�M�p�h ���Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@��������t���i<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�h�����h���Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@��������t�g�����U�N�V����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�h����h���Z�b�g<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �R�j�@@��t�����Ɋ��������P��.�쐬���� ���Z�b�g����B<BR>
     * �@@�@@��t�����Ƃ��āA���������P��.�쐬���� ���g�p����B<BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂāA���������P��.�쐬���� ���Z�b�g����B<BR>
     * �@@�@@�ݒ�L�[�F�@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * <BR>
     * �S�j�@@�����ꒆ�̊�l�擾�̂��߂̑������Z�b�g����B<BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�@@�u���菈���ŕK���ꒆ�����v���Z�b�g����B<BR>
     * �@@�@@�@@�@@�ݒ�L�[�F �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE<BR>
     * <BR>
     * �T�j�@@���������b�N����B<BR>
     * <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@�@@�@@�������͊�����������ʒm�L���[���ҏW�B<BR>
     * <BR>
     * @@param l_method - (���\�b�h)<BR>
     * ���\�b�h<BR>
     * @@param l_serviceParams - (�T�[�r�X�̈���)<BR>
     * �T�[�r�X�̃��\�b�h�ɓn���������B<BR>
     * ����������M�ꌏ�T�[�r�X�̏ꍇ�A�����������͒ʒm�L���[Params�I�u�W�F�N�g�B<BR>
     * @@return Object
     * @@roseuid 403EE66F025C<BR>
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        // �@@�|�T�[�r�X�̈���[0]��������������ʒm�L���[Params�I�u�W�F�N�g�ɃL���X�g����B
        HostEqtypeOrderClmdReceiptParams l_params =
            (HostEqtypeOrderClmdReceiptParams)l_serviceParam[0];
        // �@@�|�T�[�r�X�̈���[1]�𒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B<BR>
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_serviceParam[1];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        // �@@�|������������ʒm�L���[Params�A�����P�ʂ̓��e���A������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        Market l_market = null;
        try
        {
            l_market =
                l_finApp.getFinObjectManager().getMarket(
                    l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = ������������ʒm�L���[Params�̓����ڂ��Z�b�g
        l_context.setInstitutionCode(
            l_params.getInstitutionCode());
        // ����J�����_�R���e�L�X�g.���X�R�[�h = ������������ʒm�L���[Params�̓����ڂ��Z�b�g
        l_context.setBranchCode(
            l_params.getBranchCode());
        // ����J�����_�R���e�L�X�g.�s��R�[�h =
        //       �����P��.�s��ID �ɊY������s��I�u�W�F�N�g�̎s��R�[�h
        l_context.setMarketCode(l_market.getMarketCode());
        // ����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        // ����J�����_�R���e�L�X�g.�����~���i = �h�����h
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        // ����J�����_�R���e�L�X�g.�����~�g�����U�N�V���� =�h����h
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);

        // ������ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
            Timestamp l_tsReceivedDateTime =
                l_orderUnitRow.getCreatedTimestamp();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                l_tsReceivedDateTime);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
                WEB3EquityBackServiceOnlineDef.ONLINE);
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
     * (onReturn)<BR>
     * <BR>
     * ��������ʒm����ꌏ�T�[�r�X�I�����ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE<BR>
     * <BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * @@param l_returnValue - (�߂�l)<BR>
     * @@roseuid 403EE66F025F<BR>
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

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (onThrowable)<BR>
     * <BR>
     * ��������ʒm����ꌏ�T�[�r�X����O���X���[�����ꍇ�ɃR�[�������<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE<BR>
     * <BR>
     * @@param l_obj - (onCall���^�[���l)<BR>
     * @@param l_throwable - (��O�I�u�W�F�N�g)<BR>
     * @@roseuid 40A436F402E1<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
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

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
