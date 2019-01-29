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
filename	WEB3EquityOrderExecNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������o���ʒm�ꌏ�T�[�r�X�C���^�Z�v�^(WEB3EquityOrderExecNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 �A�C��(���u) �V�K�쐬
Revesion History : 2004/12/02 �������F(SRA) �c�Č��Ή��ɂ��C��
Revesion History : 2005/01/05 �����a��(SRA) JavaDoc�C��
Revesion History : 2006/02/20 ���V��@@ (SRA) �d�l�ύX�Ǘ��䒠�i���f���j��862�̑Ή�
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.define.WEB3EquityBackServiceOnlineDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

/**
 * �i���������o���ʒm�ꌏ�T�[�r�X�C���^�Z�v�^�j�B
 * @@version 1.0
 */
public class WEB3EquityOrderExecNotifyUnitServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3EquityOrderExecNotifyUnitServiceInterceptor.class);

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3EquityOrderExecNotifyUnitServiceInterceptor()
    {
    }

    /**
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����A�T�[�r�X���\�b�h�J�n���ɃR�[�������j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�𒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|�T�[�r�X�̈���[1]�������o���ʒm�L���[Params�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|�����o���ʒm�L���[Params�I�u�W�F�N�g�̓��e��������ԃR���e�L�X�g�̃v���p�e�B��<BR>
     * �@@�@@�@@�Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �����o���ʒm�L���[Params�̓�����<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �����o���ʒm�L���[Params�̓�����<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �����P�ʃI�u�W�F�N�g.�s��ID�ɊY������<BR>
     * �@@�@@�@@�s��I�u�W�F�N�g.�s��R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = null<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �R�j�@@�����ꒆ�̊�l�擾�̂��߂̑������Z�b�g����B <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�@@�u���菈���ŕK���ꒆ�����v���Z�b�g����B <BR>
     * �@@�@@�@@�@@�ݒ�L�[�F �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE<BR>
     * �@@�|������ԊǗ�.set�������v�Z�p�����( )�ɂ� <BR>
     * �@@�@@�������v�Z�̊�����ɁA�����P��.�������{HHMMSS�Ƃ���"000000"���Z�b�g����B<BR>
     * <BR>
     * �S�j�@@���������b�N����B<BR>
     * <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h(*1))���R�[������B<BR>
     * �@@�@@�@@�������͊����o���ʒm�L���[Params���ҏW�B<BR>
     * �@@�@@�@@���������A(*1)==�i0 or null�j�̏ꍇ�́A�����P��.����ID�ɊY������ڋq�I�u�W�F�N�g.�����R�[�h��<BR>
     * �@@�@@�@@���ҏW����B<BR>
     * <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParams - �T�[�r�X���\�b�h����
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_serviceParams[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        HostEquityOrderExecNotifyParams l_execNotifyParams =
            (HostEquityOrderExecNotifyParams)l_serviceParams[1];
        String l_strInstitutionCode = l_execNotifyParams.getInstitutionCode();
        String l_strBranchCode = l_execNotifyParams.getBranchCode();
        String l_strAccountCode = l_execNotifyParams.getAccountCode();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        String l_strMarketCode = null;
        long l_lngMarketId = l_orderUnitRow.getMarketId();
        try
        {
            WEB3GentradeFinObjectManager l_objectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_objectManager.getMarket(l_lngMarketId);
            l_strMarketCode = l_market.getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME);
        }

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = �����o���ʒm�L���[Params�̓�����
        l_context.setInstitutionCode(l_strInstitutionCode);
        // ����J�����_�R���e�L�X�g.���X�R�[�h = �����o���ʒm�L���[Params�̓�����
        l_context.setBranchCode(l_strBranchCode);
        // ����J�����_�R���e�L�X�g.�s��R�[�h = �����P�ʃI�u�W�F�N�g.�s��ID�ɊY������s��I�u�W�F�N�g.�s��R�[�h
        l_context.setMarketCode(l_strMarketCode);
        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h01�F�����E�M�p�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        // ����J�����_�R���e�L�X�g.�����R�[�h = 0 �F DEFAULT
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        // ����J�����_�R���e�L�X�g.������t���i = null
        l_context.setOrderAcceptProduct(null);
        // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null
        l_context.setOrderAcceptTransaction(null);

        // ������ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        // �Q�j�@@��t�����A���t���[�����Z�b�g����B
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp(); //������ԊǗ�
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

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
            WEB3EquityBackServiceOnlineDef.ONLINE);
        Date l_bizDate = 
        	WEB3DateUtility.getDate(
        	l_orderUnitRow.getBizDate() + WEB3GentradeTimeDef.MIN_RETURN, 
			WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
		WEB3GentradeTradingTimeManagement.setBaseTimestampForOrderBizDate(
			new Timestamp(l_bizDate.getTime()));
        
        // �S�j�@@���������b�N����B
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        if (l_strAccountCode == null ||
            Integer.parseInt(l_strAccountCode.trim()) == 0)
        {
            try
            {
                MainAccount l_account =
                    l_accountManager.getMainAccount(l_orderUnit.getAccountId());
                l_strAccountCode = l_account.getAccountCode();
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
            }
        }
        try
        {
            l_accountManager.lockAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode);
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
     * �ionReturn�j<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * �����J�z�ꌏ�T�[�r�X�I�����ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * ������ԊǗ�.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * �@@��������ԊǗ�.clear�������v�Z�p�����( )���R�[���B<BR>
     * �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE<BR>
     * <BR>
     * @@param l_returnValue - onCall���^�[���l
     * @@param l_context - �T�[�r�X���\�b�h���^�[���l
     */
    public void onReturn(Object l_returnValue, Object l_context)
    {
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
        
		WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
    }

    /**
     * �ionThrowable�j<BR>
     * <BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * ������ԊǗ�.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * �@@��������ԊǗ�.clear�������v�Z�p�����( )���R�[���B<BR>
     * �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE<BR>
     * <BR>
     * @@param l_returnValue - onCall���^�[���l
     * @@param l_context - �T�[�r�X���\�b�h���^�[���l
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
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
            
		WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
    }
}
@
