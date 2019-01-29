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
filename	WEB3EquityOrderNotifyPartServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm�ꌏ�T�[�r�X�C���^�Z�v�^(WEB3EquityOrderNotifyPartServiceIntercepter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 �R�w�� (���u) �V�K�쐬
                   2004/12/10 �����a��(SRA) �c�Č��Ή� �m��.�P�T�S���m��.�Q�R�X
                   2005/01/05 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.service.delegate.WEB3EquityOrderInputNotifyAdapter;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������ʒm�ꌏ�T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * ���������ʒm�ꌏ�T�[�r�X�ɓo�^����C���^�Z�v�^�N���X�B<BR>
 * ���������R���ʃ`�F�b�N�Dcalc���s���v�Z�P��()�Ŏg�p����<BR>
 * ��������I�u�W�F�N�g�������̎���J�����_�R���e�L�X�g�����E�N���A���s���B
 * @@version 1.0
 */
public class WEB3EquityOrderNotifyPartServiceInterceptor implements Interceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderNotifyPartServiceInterceptor.class);
        
    /**
     * @@roseuid 40B42272029F
     */
    public WEB3EquityOrderNotifyPartServiceInterceptor() 
    {
    
    }
   
    /**
     * �ionCall�j�B<br>
     * <br>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<br>
     * �ixTrade�J�[�l�����A[���������ʒm�ꌏ�T�[�r�X]���s�O�ɌĂяo�����j<br>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�T�[�r�X�̈���[0]�������������͒ʒm�f�[�^�A�_�v�^�ɃL���X�g����B <BR>
     * �@@�|�����������͒ʒm�f�[�^�A�_�v�^.�����������͒ʒm�L���[Params�̓��e��� <BR>
     *    ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * �@@�@@�@@�@@�@@�@@���،���ЃR�[�h�A���X�R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����������͒ʒm�L���[Params�̓����ڂ��Z�b�g <BR>
     * �@@�@@�@@�@@�@@�@@���s��R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����������͒ʒm�f�[�^�A�_�v�^.get�s��R�[�h( )���Z�b�g <BR>
     * �@@�@@�@@�@@�@@�@@����t���ԋ敪 <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@01�F�����E�M�p ���Z�b�g <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ� <BR>
     *    ������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F"web3.tradingcalendarcontext" <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * �R�j�@@��t������SONAR���ł̔�������(����������(biz_datetime)) ���Z�b�g����B<BR>
     * �@@�@@��t�����Ƃ��āASONAR���ł̔�������(����������(biz_datetime))��<BR>
     * �@@�@@�g�p����B�i�����@@���Q�Ɓj<BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂāA�����������͒ʒm�L���[Params.�������� ���Z�b�g����B<BR>
     * �ݒ�L�[�F"xblocks.gtl.attributes.system_timestamp"<BR>
     * <BR>
     * �� ���� �� <BR>
     * SONAR�������̂����󒍓���(create_datetime)�� <BR>
     * �o�^�҂ɂ�����͍��ځiSONAR�����̓`�F�b�N�قƂ�ǖ����j�ł��邽�߁A <BR>
     * ��������擾��system_timestamp�Ƃ��Ă͎g�p�ł��Ȃ��B <BR>
     * �������ASONAR�������̂�����������(biz_datetime)�� <BR>
     * SONAR�V�X�e�����������Ɏ����Z�b�g���鍀�ڂł���A <BR>
     * YYYYMMDD�ɂ͓������t���A <BR>
     * HHMMSS�ɂ͓����̊J�ǑO�`�J�ǎ��ԑт̎��Ԃ������Ă��邽�߁A <BR>
     * ���̃T�[�r�X�ɂ����Ă͔�������(biz_datetime)��<BR>
     * ��������擾��system_timestamp�Ƃ��Ďg�p����B<BR>
     * <BR>
     * �S�j�@@���������b�N����B<BR>
     * <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     * �@@�@@�@@�������͊����������͒ʒm�L���[Params���ҏW�B<BR>
     * <BR>
     * @@param l_method - ���\�b�h <BR>
     * @@param l_serviceParam - �T�[�r�X�̃��\�b�h�ɓn���������B <BR>
     * ����������M�ꌏ�T�[�r�X�̏ꍇ�A <BR>
     * �����������͒ʒm�L���[Params�I�u�W�F�N�g�B <BR>
     * @@return Object
     * @@roseuid 4021F6C6029B
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        WEB3EquityOrderInputNotifyAdapter l_orderInputNotifyAdapter = (WEB3EquityOrderInputNotifyAdapter) l_serviceParam[0];

        HostEqtypeOrderReceiptParams l_orderReceiptParams = l_orderInputNotifyAdapter.getHostEqtypeOrderReceipt();
        
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        String l_strInstitutionCode = null; //�،���ЃR�[�h
        String l_strBranchCode  = null; //���X�R�[�h

        l_strInstitutionCode = l_orderReceiptParams.getInstitutionCode();
        l_strBranchCode = l_orderReceiptParams.getBranchCode();

        //�،���ЃR�[�h�A���X�R�[�h�����������͒ʒm�L���[Params�̓����ڂ��Z�b�g
        l_context.setInstitutionCode(l_strInstitutionCode);
        l_context.setBranchCode(l_strBranchCode);
        
        //�����������͒ʒm�f�[�^�A�_�v�^.get�s��R�[�h( )���Z�b�g
        WEB3EquityOrderInputNotifyAdapter l_adapter =
            WEB3EquityOrderInputNotifyAdapter.create(l_orderReceiptParams);
        String l_strMarketCode = null;
        try
        {
            l_strMarketCode = l_adapter.getMarketCode();
        }
        catch (WEB3BaseException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        l_context.setMarketCode(l_strMarketCode);
        
        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        
        //�����R�[�h���Z�b�g����
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

        // ������ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
            
        //�Q�j��t�����A���t���[�����Z�b�g����
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp(); //������ԊǗ�
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(
                "__WEB3GentradeTradingTimeManagement.setTimestamp Error__", l_ex);

            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�R�j�@@��t������SONAR���ł̔�������(����������(biz_datetime)) ���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute( 
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                l_orderReceiptParams.biz_datetime);

        // �S�j�@@���������b�N����B
        log.debug("�g���A�J�E���g�}�l�[�W���̌��������b�N���܂��B");
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        // �g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B
        try {
            l_accountManager.lockAccount(l_strInstitutionCode, l_strBranchCode, l_orderReceiptParams.getAccountCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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
     * �ionReturn�j�B<br>
     * <br>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * ���������ʒm�ꌏ�T�[�r�X�I�����ɃR�[�������B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * "system_timestamp" <BR>
     * "xblocks.gtl.attributes.bizdate.offset" <BR>
     * "web3.tradingcalendarcontext" <BR>
     * <BR>
     * @@param l_onCallReturnValue - onCall���^�[���l <BR>
     * @@param l_serviceMethodReturnValue - �T�[�r�X���\�b�h���^�[���l <BR>
     * @@roseuid 4021F6C602AA
     */
    public void onReturn(Object l_onCallReturnValue, Object l_serviceMethodReturnValue)
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
    }
    
    /**
     * �ionThrowable�j�B<br>
     * <br>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * @@param l_onCallReturnValue
     * @@param l_throwable - ��O�I�u�W�F�N�g <BR>
     * @@roseuid 40B2D17D004E
     */
    public void onThrowable(Object l_onCallReturnValue, Throwable l_throwable)
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
    }
}
@
