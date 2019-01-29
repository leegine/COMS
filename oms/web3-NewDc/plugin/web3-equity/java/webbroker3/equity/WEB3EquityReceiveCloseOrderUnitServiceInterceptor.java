head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityReceiveCloseOrderUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm�ꌏ�T�[�r�X�C���^�Z�v�^(WEB3EquityReceiveCloseOrderUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 羐� (���u) �V�K�쐬
Revesion History : 2004/12/15 ���� (SRA) �c�Č��Ή��̂��ߏC��
Revesion History : 2005/01/05 ���� (SRA) JavaDoc�C��
Revesion History : 2006/02/20 ���V��@@ (SRA) �d�l�ύX�Ǘ��䒠�i���f���j��863�̑Ή�
*/
package webbroker3.equity;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.define.WEB3EquityBackServiceOnlineDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

/**
 * �i���������ʒm�ꌏ�T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * ���������ʒm�ꌏ�T�[�r�XImpl�ɑ΂��Đݒ肷��B
 * @@version 1.0
 */
public class WEB3EquityReceiveCloseOrderUnitServiceInterceptor
    implements Interceptor
{
    /**
     * onCall()�̌ďo�����\�b�h��
     */
    private String methodName = null;
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityReceiveCloseOrderUnitServiceInterceptor.class);

    /**
     * @@roseuid 4142B67C02E2
     */
    public WEB3EquityReceiveCloseOrderUnitServiceInterceptor()
    {

    }

    /**
     * �ionCall�j�B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����A�T�[�r�X���\�b�h�J�n���ɃR�[�������j<BR>
     * �����T�[�r�X�i�o���ʒm�j����R�[������Ă���ꍇ�́A�������Ȃ��B<BR>
     * <BR>
     * �P�j�@@���\�b�h��=="exec����"�̏ꍇ�́A����������return����B<BR>
     * <BR>
     * �ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����������ʒm�L���[Params�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���������ʒm�L���[Params�I�u�W�F�N�g�̓��e��������ԃR���e�L�X�g��<BR>
     * �v���p�e�B��<BR>
     * �@@�@@�@@�Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h ���@@���������ʒm�L���[Params�̓�����<BR>
     *   ����J�����_�R���e�L�X�g.���X�R�[�h ���@@���������ʒm�L���[Params�̓�����<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 ���@@�h01�F�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h�@@ ���@@�h0�FDEFAULT�h<BR> 
     * <BR>
     * �@@���s��R�[�h�͒����P�ʂ��ƂɃT�[�r�X���ɂĐݒ�<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �@@�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �R�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �S�j�@@�����ꒆ�̊�l�擾�̂��߂̑������Z�b�g����B<BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�u���菈���ŕK���ꒆ�����v���Z�b�g����B<BR>
     * �@@�@@�ݒ�L�[�F �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE<BR>
     * �@@�|������ԊǗ�.set�������v�Z�p�����( )�ɂ�<BR>
     * �@@�@@�������v�Z�̊�����ɁA�����P��.�������{HHMMSS�Ƃ���"000000"���Z�b�g����B<BR>
     * �@@�@@�������P�ʃI�u�W�F�N�g�́A���\�b�h�̑�������cast���Ďg�p�B<BR>
     * <BR>
     * �T�j�@@���������b�N����B<BR>
     * <BR>
     * �g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR> 
     * �������͊��������ʒm�L���[Params���ҏW�B
     * @@param l_method �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParams �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 4105DA920318
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���\�b�h��=="exec����"�̏ꍇ�́A����������return����B
        methodName = l_method.getName();
        if ("execCloseOrder".equals(methodName))
        {
        	return null;
        }
        
        //�ȊO�A�ȉ��̏������s���B
        //�Q�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����       
        
        //�T�[�r�X�̈���[0]�����������ʒm�L���[Params�I�u�W�F�N�g�ɃL���X�g����B
        HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams = 
            (HostEqtypeCloseOrderNotifyParams)l_serviceParams[0];
        
        String l_strAccountCode = l_hostEqtypeCloseOrderNotifyParams.getAccountCode();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
        
        //����J�����_�R���e�L�X�g.�،���ЃR�[�h �� ���������ʒm�L���[Params�̓�����
        l_context.setInstitutionCode(l_hostEqtypeCloseOrderNotifyParams.getInstitutionCode());
        
        //����J�����_�R���e�L�X�g.���X�R�[�h ���@@���������ʒm�L���[Params�̓�����
        l_context.setBranchCode(l_hostEqtypeCloseOrderNotifyParams.getBranchCode());
        
        //����J�����_�R���e�L�X�g.��t���ԋ敪  ���@@�h01�F�����E�M�p�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        
        //����J�����_�R���e�L�X�g.�����R�[�h  ���@@�h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        //������ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);        
        
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();        
        try
        {
            //�R�j�@@��t�����A���t���[�����Z�b�g����
            WEB3GentradeTradingTimeManagement.setTimestamp();
            //�S�j�@@�����ꒆ�̊�l�擾�̂��߂̑������Z�b�g����B
			EqTypeOrderUnit l_eqTypeOrderUnit = 
            	(EqTypeOrderUnit)l_serviceParams[1];
            EqtypeOrderUnitRow l_orderUnitRow = 
            	(EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE, 
				WEB3EquityBackServiceOnlineDef.ONLINE);
			Date l_bizDate = 
				WEB3DateUtility.getDate(
				l_orderUnitRow.getBizDate() + WEB3GentradeTimeDef.MIN_RETURN, 
				WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
			WEB3GentradeTradingTimeManagement.setBaseTimestampForOrderBizDate(
				new Timestamp(l_bizDate.getTime()));
            //�T�j�@@���������b�N����B 
            l_accountManager.lockAccount(
                l_hostEqtypeCloseOrderNotifyParams.getInstitutionCode(),
                l_hostEqtypeCloseOrderNotifyParams.getBranchCode(),
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
     * �ionReturn�j�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * �����T�[�r�X�i�o���ʒm�j����R�[������Ă���ꍇ�́A�������Ȃ��B<BR>
     * <BR>
     * ���\�b�h��=="exec����"�̏ꍇ�́A����������return����B<BR> 
     * �ȊO�A����J�����_�R���e�L�X�g�N���A�������s���B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * ������ԊǗ�.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * �@@��������ԊǗ�.clear�������v�Z�p�����( )���R�[���B
     * �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE<BR>
     * @@param l_context onCall���^�[���l
     * @@param l_returnValue �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 4105DA920337
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
		if ("execCloseOrder".equals(methodName))
		{
			return;
		}

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
     * �ionThrowable�j�B<BR>
     * <BR>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * �����T�[�r�X�i�o���ʒm�j����R�[������Ă���ꍇ�́A�������Ȃ��B<BR>
     * <BR>
     * ���\�b�h��=="exec����"�̏ꍇ�́A����������return����B<BR>
     * �ȊO�A����J�����_�R���e�L�X�g�N���A�������s���B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * ������ԊǗ�.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * �@@��������ԊǗ�.clear�������v�Z�p�����( )���R�[���B<BR>
     * �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE<BR>
     * @@param l_obj onCall���^�[���l
     * @@param l_throwable ��O�I�u�W�F�N�g
     * @@roseuid 4105DA920347
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
		if ("execCloseOrder".equals(methodName))
		{
			return;
		}

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
