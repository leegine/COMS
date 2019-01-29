head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderExecutedInquiryServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�������Ɖ�T�[�r�X�C���^�Z�v�^(WEB3OptionOrderExecutedInquiryServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 �n�U�c (���u) �V�K�쐬
                   2004/07/26 ���@@�Q (���u)�@@�C���iUTBUG�iWEB3_IFO_UT-000028�j��Ή��j
                   2004/07/30 ���@@�Q (���u)�@@�C���iUTBUG�iWEB3_IFO_UT-000076�j��Ή��j
*/
package webbroker3.ifo;
import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrder;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.util.WEB3LogUtility;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractListRequest;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3OptionsOrderHistoryRequest;
/**
 * (OP�������Ɖ�T�[�r�X�C���^�Z�v�^)<BR>
 * �����w���I�v�V�����������Ɖ�T�[�r�X�C���^�Z�v�^�N���X<BR>
 * @@author  �n�U�c
 * @@version 1.0
 */
public class WEB3OptionOrderExecutedInquiryServiceInterceptor
    implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionOrderExecutedInquiryServiceInterceptor.class);
    /**
     * @@roseuid 40C07A3A008C
     */
    public WEB3OptionOrderExecutedInquiryServiceInterceptor()
    {
    }
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�@@�@@������.�T�[�r�X���\�b�h��"search�������Ɖ�"�̏ꍇ�̂݁B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 =�h11�F�����w���敨OP�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = null�i*)<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h06�F�I�v�V�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h<BR>
     * <BR>
     *  (*)���i�R�[�h��execute( )����R�[������郁�\�b�h���ŁA ���Z�b�g����B<BR>
     * <BR>
     * �@@��OpLoginSecurityService���擾��������ID == 0�̏ꍇ�A<BR>
     * �@@���N�G�X�g.����ID�ɊY�����钍��.����ID��������ԃR���e�L�X�g�ɃZ�b�g����B<BR>
     * �@@�ݒ�L�[�F ACCOUNT_ID<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�
     * ������ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 4057FBD200CD
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);

        Object l_request = l_serviceParam[0];
        
        if ((l_serviceParam == null)
            || (l_serviceParam[0] == null)
            || !(l_serviceParam[0] instanceof WEB3GenRequest))
        {
            log.error(
                "__�Ɖ�͖��Ή��ł�__",
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "onCall"));
        }
        
        String l_strInstitutionCode = null; //�،���ЃR�[�h
        String l_strBranchCode = null; //���X�R�[�h
        
        FinApp l_finApp =  (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        MainAccount l_mainAccount;
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
               
        try
        {
            //����ID���擾����B
            long l_lngAccountId = l_opLoginSec.getAccountId();
            //�Ǘ��҃Z�b�V��������R�[�����ꂽ�ꍇ
            if (l_lngAccountId == 0L)
            {
                long l_lngOrderId = 0L;
                //���N�G�X�g == �I�v�V�����������ڍׂ̏ꍇ
                if (l_request instanceof WEB3OptionsExecuteDetailsRequest)
                {
                    WEB3OptionsExecuteDetailsRequest l_detailRequest =
                        (WEB3OptionsExecuteDetailsRequest)l_request;
                    l_lngOrderId = Long.parseLong(l_detailRequest.id);
                }
                //���N�G�X�g == �I�v�V����������藚���̏ꍇ
                else if (l_request instanceof WEB3OptionsOrderHistoryRequest)
                {
                    WEB3OptionsOrderHistoryRequest l_historyRequest =
                        (WEB3OptionsOrderHistoryRequest)l_request;
                    l_lngOrderId = Long.parseLong(l_historyRequest.id);
                }
                //���N�G�X�g == �I�v�V�����ԍό��ʈꗗ�̏ꍇ
                else if (l_request instanceof WEB3OptionsCloseMarginContractListRequest)
                {
                    WEB3OptionsCloseMarginContractListRequest l_contractRequest =
                        (WEB3OptionsCloseMarginContractListRequest)l_request;
                    l_lngOrderId = Long.parseLong(l_contractRequest.id);
                }

                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3FuturesOrderManagerImpl l_orderManager = 
                    (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
                
                IfoOrder l_ifoOrder =
                    (IfoOrder)l_orderManager.getOrder(l_lngOrderId);
                l_lngAccountId = l_ifoOrder.getAccountId();
                
                //ThreadLocal�Ɏ擾��������ID���Z�b�g�B
                ThreadLocalSystemAttributesRegistry.setAttribute(WEB3IfoAttributeNameDef.ACCOUNT_ID, new Long(l_lngAccountId));
            }
            
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            //�،���ЃR�[�h���擾
            l_strInstitutionCode =
                l_mainAccount.getInstitution().getInstitutionCode();
            //���X�R�[�h���擾
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW        
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode);
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.���i�R�[�h = null
                l_context.setProductCode(null);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h
            l_context.setTradingTimeType(
                WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            //����J�����_�R���e�L�X�g.������t���i = �h06�F�I�v�V�����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.OPTION);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =�h07�F�Ɖ�h
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.REFERENCE);
            //������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(
                "__an unexpected error__",
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + ".onCall",
                    l_nfe));
        }
        //��t�����A���t���[�����Z�b�g����B
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
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
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 409EFFA3014F
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String l_strMethodName = "onReturn(Object,Object)";
        log.entering(l_strMethodName);
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
            WEB3IfoAttributeNameDef.ACCOUNT_ID,
            null);
        log.exiting(l_strMethodName);
    }
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 409EFFA3016F
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String l_strMethodName = "onThrowable(Object,Throwable)";
        log.entering(l_strMethodName);
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
            WEB3IfoAttributeNameDef.ACCOUNT_ID,
            null);
        log.exiting(l_strMethodName);
    }
}
@
