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
filename	WEB3MarginChangeCancelNotifyCancelUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�����������ʒm����ꌏ�T�[�r�X�C���^�Z�v�^(WEB3MarginChangeCancelNotifyCancelUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 䈋� (���u) �V�K�쐬
                   2004/12/17 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
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
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.define.WEB3EquityBackServiceOnlineDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�����������ʒm����ꌏ�T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �M�p�����������ʒm����ꌏ�T�[�r�X�C���^�Z�v�^�N���X
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3MarginChangeCancelNotifyCancelUnitServiceInterceptor
    implements Interceptor
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MarginChangeCancelNotifyChangeUnitServiceInterceptor.class);
    /**
     * @@roseuid 4142B32E03B8
     */
    public WEB3MarginChangeCancelNotifyCancelUnitServiceInterceptor()
    {

    }

    /**
     * (onCall)<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����A[�M�p�����������ʒm�����ꌏ�T�[�r�X]���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]��������������ʒm�L���[Params�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|�T�[�r�X�̈���[1]�����������P�ʃI�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|������������ʒm�L���[Params�A�����P�ʂ̓��e���A������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�،���ЃR�[�h�@@���@@������������ʒm�L���[Params.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@���X�R�[�h�@@�@@�@@�@@���@@������������ʒm�L���[Params.���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�s��R�[�h�@@�@@�@@�@@���@@���������P��.�s��ID �ɊY������s��I�u�W�F�N�g�̎s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@��t���ԋ敪�@@ ���@@�h01�F�����E�M�p�h<BR>
     * �@@�@@�@@�@@�@@�@@���i�R�[�h�@@�@@�@@�@@���@@�h0�FDEFAULT�h<BR>
     * �@@�@@�@@�@@�@@�@@������t���i�@@ ���@@�h03�F�M�p����h<BR>
     * �@@�@@�@@�@@�@@�@@������t�g�����U�N�V�����@@���@@�h06�F����h<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�@@�ݒ�L�[�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�E������ԊǗ�.setTimestamp()���R�[������B<BR>
     * �@@�E��t�����Ƃ��āA���������P��.�쐬���� ���g�p����ׁi�������̎���������擾���邽�߁j�A<BR>
     * �@@�@@������ԊǗ�.setTimestamp()�ɂĐݒ肳�ꂽ��t�������㏑������B<BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂāA���������P��.�쐬���� ���Z�b�g����B<BR>
     * �@@�@@�ݒ�L�[�F�@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * <BR>
     * �R�j�@@�����ꒆ�̊�l�擾�̂��߂̑������Z�b�g����B<BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�@@�u���菈���ŕK���ꒆ�����v���Z�b�g����B<BR>
     * �@@�@@�@@�@@�ݒ�L�[�F �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE<BR>
     * <BR>
     * �S�j�@@���������b�N����B<BR>
     * <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@�@@�@@�������͊�����������ʒm�L���[Params���ҏW�B<BR>
     * <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 405905910193
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        Object l_obj0 = l_serviceParams[0];
        Object l_obj1 = l_serviceParams[1];
        HostEqtypeOrderClmdReceiptParams l_params = null;
        EqTypeOrderUnit l_orderUnit = null;
        
        // �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        // �@@�|�T�[�r�X�̈���[0]��������������ʒm�L���[Params�I�u�W�F�N�g�ɃL���X�g����B
        if (l_obj0 instanceof HostEqtypeOrderClmdReceiptParams)
        {
            l_params = (HostEqtypeOrderClmdReceiptParams)l_obj0;
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // �@@�|�T�[�r�X�̈���[1]�����������P�ʃI�u�W�F�N�g�ɃL���X�g����B
        if (l_obj1 instanceof EqTypeOrderUnit)
        {
            l_orderUnit = (EqTypeOrderUnit)l_obj1;
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            // �@@�|������������ʒm�L���[Params�A�����P�ʂ̓��e���A
            // �@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            // �،���ЃR�[�h�@@�� ������������ʒm�L���[Params.�،���ЃR�[�h
            l_context.setInstitutionCode(l_params.getInstitutionCode());
            // ���X�R�[�h�@@�@@�@@���@@������������ʒm�L���[Params.���X�R�[�h
            l_context.setBranchCode(l_params.getBranchCode());
            // �s��R�[�h�@@�@@�@@���@@���������P��.�s��ID �ɊY������s��I�u�W�F�N�g�̎s��R�[�h
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(
                    l_eqtypeOrderUnitRow.getMarketId());
            l_context.setMarketCode(l_market.getMarketCode());
            // ��t���ԋ敪�@@  ���@@�h01�F�����E�M�p�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            // ���i�R�[�h�@@�@@�@@���@@�h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            // ������t���i�@@  ���@@�h03�F�M�p����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            // ������t�g�����U�N�V�����@@���@@�h06�F����h
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CANCEL);
            // �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )
            //        �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
            // �@@�@@�ݒ�L�[�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            // �Q�j�@@��t�����A���t���[�����Z�b�g����B
            // �@@�E������ԊǗ�.setTimestamp()���R�[������B
            // �@@�E��t�����Ƃ��āA���������P��.�쐬�������g�p����ׁi�������̎���������擾���邽�߁j�A
            // �@@�@@������ԊǗ�.setTimestamp()�ɂĐݒ肳�ꂽ��t�������㏑������B
            // �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂāA���������P��.�쐬���� ���Z�b�g����B
            // �@@�@@�ݒ�L�[�F�@@������ԊǗ�.TIMESTAMP_TAG
            WEB3GentradeTradingTimeManagement.setTimestamp();
            Timestamp l_tsReceivedDateTime =
                l_eqtypeOrderUnitRow.getCreatedTimestamp();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                l_tsReceivedDateTime);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
                WEB3EquityBackServiceOnlineDef.ONLINE);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        catch (WEB3BaseException l_wsle)
        {
            log.error(STR_METHOD_NAME, l_wsle);
            throw new WEB3BaseRuntimeException(
                l_wsle.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wsle.getMessage(),
                l_wsle);
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (onReturn)<BR>
     * <BR>
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE<BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 4059059101A2
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
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
            null);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (onThrowable)<BR>
     * <BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE<BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 4142B32E03E0
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
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
            null);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
