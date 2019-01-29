head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOrderNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�����ʒmUnitService�C���^�Z�v�^(WEB3OptionsOrderNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3TradeTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.data.HostFotypeOrderReceiptParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP�����ʒmUnitService�C���^�Z�v�^)<BR>
 * �����w���I�v�V���������ʒm�P���T�[�r�X�C���^�Z�v�^�N���X<BR>
 * Plugin����OP�����ʒmUnitService�ɑ΂��Đݒ肷��B<BR>
 * @@author  : ������
 * @@version : 1.0
 */
public class WEB3OptionsOrderNotifyUnitServiceInterceptor implements Interceptor 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionsOrderNotifyUnitServiceInterceptor.class);
   
    /**
     * @@roseuid 41AAE84401C5
     */
    public WEB3OptionsOrderNotifyUnitServiceInterceptor() 
    {
        
    }
   
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����[OP�����ʒmUnit�T�[�r�X]���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|����.�T�[�r�X�̈���[0]��敨OP�����ʒm�L���[Params�ɃL���X�g����B<BR>
     * �@@�|�敨OP�����ʒm�L���[Params�̓��e���A<BR>
     *     ����J�����_�R���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h    = 
     * �敨OP�����ʒm�L���[Params.�،���ЃR�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h    = �敨OP�����ʒm�L���[Params.���X�R�[�h 
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h    = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪    = �h11�F�����w���敨OP�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h    = 
     * �敨OP�����ʒm�L���[Params.�����Y�����R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i    = �h06�F�I�v�V�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = (*)<BR>
     * <BR>
     * �@@(*)������t�g�����U�N�V�����̐ݒ���@@<BR>
     * �@@�E�敨OP�����ʒm�L���[Params.����R�[�h(SONAR) == "�敨OP��"�̏ꍇ�A<BR>
     * �@@�@@�| �敨OP�����ʒm�L���[Params.�����敪 == "����"�Ȃ�΁A�h���t�h�B<BR>
     * �@@�@@�| �敨OP�����ʒm�L���[Params.�����敪 == "����"�Ȃ�΁A�h���t�h�B<BR>
     * �@@�E�敨OP�����ʒm�L���[Params.����R�[�h(SONAR) == 
     * "�敨OP��"�̏ꍇ�A�h�ԍρh���Z�b�g�B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( 
     * )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �R�j�@@��t�����Ƃ��āASONAR���ł̔�������(=��������(biz_datetime))���g�p����ׁA<BR>
     * �@@�@@�@@������ԊǗ�.setTimestamp()�ɂĐݒ肳�ꂽ��t�������㏑������B�����Q�Ɓj<BR> 
     *�@@ �|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     *�@@�@@�@@�敨OP�����ʒm�L���[Params.�����������Z�b�g����B<BR> 
     * �@@�@@ �ݒ�L�[�F�@@������ԊǗ�.TIMESTAMP_TAG<BR>
     *   �� ���� �� <BR>
     *      SONAR�������̂����󒍓���(create_datetime)��<BR>
     *      �o�^�҂ɂ�����͍��ځiSONAR�����̓`�F�b�N�قƂ�ǖ����j�ł��邽�߁A<BR>
     *      ��������擾��system_timestamp�Ƃ��Ă͎g�p�ł��Ȃ��B<BR>
     *      �������ASONAR�������̂�����������(biz_datetime)�� <BR>
     *      SONAR�V�X�e�����������Ɏ����Z�b�g���鍀�ڂł���A<BR>
     *      YYYYMMDD�ɂ͓������t���A<BR>
     *      HHMMSS�ɂ͓����̊J�ǑO�`�J�ǎ��ԑт̎��Ԃ������Ă��邽�߁A<BR>
     *      ���̃T�[�r�X�ɂ����Ă͔�������(biz_datetime)��<BR>
     *      ��������擾��system_timestamp�Ƃ��Ďg�p����B<BR>
     * <BR>
     * �S�j�@@���������b�N����B<BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h,<BR> 
     *      �����R�[�h)���R�[������B<BR>
     *      �������͐敨OP�����ʒm�L���[Params���ҏW�B<BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParams - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 4163B1F4026E
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
       
        // ����.�T�[�r�X�̈���[0]��敨OP�����ʒm�L���[Params�ɃL���X�g����B<BR>
        HostFotypeOrderReceiptParams l_receiptParams = null;
        if (l_serviceParams[0] instanceof HostFotypeOrderReceiptParams)
        {
            l_receiptParams = (HostFotypeOrderReceiptParams)l_serviceParams[0];
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                STR_METHOD_NAME);
        }
        
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
            
        // ����J�����_�R���e�L�X�g.�،���ЃR�[�h = �敨OP�����ʒm�L���[Params.�،���ЃR�[�h
        l_context.setInstitutionCode(l_receiptParams.getInstitutionCode());

        // ����J�����_�R���e�L�X�g.���X�R�[�h = �敨OP�����ʒm�L���[Params.���X�R�[�h 
        l_context.setBranchCode(l_receiptParams.getBranchCode());

        // ����J�����_�R���e�L�X�g.�s��R�[�h   = �h0�FDEFAULT�h<BR>
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);

        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

        // ����J�����_�R���e�L�X�g.���i�R�[�h = �敨OP�����ʒm�L���[Params.�����Y�����R�[�h
        l_context.setProductCode(l_receiptParams.getUnderlyingProductCode());

        // ����J�����_�R���e�L�X�g.������t���i = �h06�F�I�v�V�����h
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);

        // ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = (*)
        //  (*)������t�g�����U�N�V�����̐ݒ���@@<BR>
        //  �E�敨OP�����ʒm�L���[Params.����R�[�h(SONAR) == "�敨OP��"�̏ꍇ�A<BR>
        //  �| �敨OP�����ʒm�L���[Params.�����敪 == "����"�Ȃ�΁A�h���t�h�B<BR>
        //  �| �敨OP�����ʒm�L���[Params.�����敪 == "����"�Ȃ�΁A�h���t�h�B<BR>
        //  �E�敨OP�����ʒm�L���[Params.����R�[�h(SONAR) == "�敨OP��"�̏ꍇ�A�h�ԍρh���Z�b�g�B<BR>
        String l_strTradeType = null;
        if (WEB3TransactionTypeSONARDef.OPEN_CONTRACT.equals(l_receiptParams.getSonarTradedCode()))
        {
            if (WEB3TradeTypeDef.OPEN_LONG_MARGIN.equals(l_receiptParams.getTradeType()))
            {
                l_strTradeType = WEB3BuySellTypeDef.BUY;
            }
            else if (WEB3TradeTypeDef.OPEN_SHORT_MARGIN.equals(l_receiptParams.getTradeType()))
            {
                l_strTradeType = WEB3BuySellTypeDef.SELL;
            }
        }
        else if (WEB3TransactionTypeSONARDef.SETTLE_CONTRACT.equals(l_receiptParams.getSonarTradedCode()))
        {
            l_strTradeType = WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
        }
        l_context.setOrderAcceptTransaction(l_strTradeType);
        
        // ������ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);         

        try
        {
            // �Q�j�@@��t�����A���t���[�����Z�b�g����                    
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

        // �R�j�|ThreadLocalSystemAttributesRegistry.setAttribute(<BR> 
        //  )�ɂĐ敨OP�����ʒm�L���[Params.�����������Z�b�g����B <BR>
        //  �@@�@@�ݒ�L�[�F�@@������ԊǗ�.TIMESTAMP_TAG <BR>
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, 
            l_receiptParams.getBizDatetime());

        // �S�j�@@���������b�N����B<BR>
        // �|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, 
        // �����R�[�h)���R�[������B<BR>
        // �������͐敨OP�����ʒm�L���[Params���ҏW�B<BR>
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            l_accountManager.lockAccount(
                l_receiptParams.getInstitutionCode(),
                l_receiptParams.getBranchCode(),
                l_receiptParams.getAccountCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
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
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 4163B1F4028D
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
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
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 4163B1F402AC
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
    }
}
@
