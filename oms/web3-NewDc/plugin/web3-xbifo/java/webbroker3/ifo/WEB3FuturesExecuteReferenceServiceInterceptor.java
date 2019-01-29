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
filename	WEB3FuturesExecuteReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨�������Ɖ�T�[�r�X�C���^�Z�v�^(WEB3FuturesExecuteReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 ���Q (���u) �V�K�쐬
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
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
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.ifo.message.WEB3FuturesCloseMarginContractListRequest;
import webbroker3.ifo.message.WEB3FuturesExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3FuturesExecuteReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesOrderHistoryRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP�������Ɖ�T�[�r�X�C���^�Z�v�^)<BR>
 * �����w���I�v�V�����������Ɖ�T�[�r�X�C���^�Z�v�^�N���X<BR>
 */
public class WEB3FuturesExecuteReferenceServiceInterceptor implements Interceptor 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesExecuteReferenceServiceInterceptor.class);
    
    /**
     * @@roseuid 40C07A3A008C
     */
    public WEB3FuturesExecuteReferenceServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>    
     *    �A�C�e���̒�`<BR>
     *    �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR> 
     * <BR>
     *    �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR> 
     *�@@    �|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR> 
     *�@@�@@�@@    ������.�T�[�r�X���\�b�h��"get�������Ɖ�"�̏ꍇ�̂݁B<BR> 
     *�@@    �|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��� <BR>
     *    ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR> 
     * <BR>
     *�@@    ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR> 
     *�@@    ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     *�@@    ����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR> 
     *�@@    ����J�����_�R���e�L�X�g.��t���ԋ敪 =�h11�F�����w���敨OP�h<BR> 
     *�@@    ����J�����_�R���e�L�X�g.���i�R�[�h = null�i*)<BR>
     *�@@    ����J�����_�R���e�L�X�g.������t���i = �h05�F�敨�h<BR> 
     *�@@    ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h07�F�Ɖ�h<BR> 
     * <BR>
     *    (*)���i�R�[�h��execute( )����R�[������郁�\�b�h���ŁA ���Z�b�g����B<BR> 
     * <BR>
     *�@@    ��OpLoginSecurityService���擾��������ID == 0�̏ꍇ�A<BR>
     *�@@    ���N�G�X�g.����ID�ɊY�����钍��.����ID��������ԃR���e�L�X�g�ɃZ�b�g����B<BR>
     *�@@    �ݒ�L�[�F ACCOUNT_ID<BR>
     * <BR>
     *�@@    �|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     *�@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B <BR>
     *    �@@�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     *    �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR> 
     *�@@    �|������ԊǗ�.setTimestamp()���R�[������B<BR>
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
             
        if ((l_serviceParam == null) || (l_serviceParam[0] == null)) 
        {

            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + "onCall");
        } 
        
        if (!(l_serviceParam[0] instanceof WEB3GenRequest))
        {
            log.error("�p�����[�^�^�C�v�s���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);               
        }
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        try
        { 

            String l_strInstitutionCode = null; //�،���ЃR�[�h
            String l_strBranchCode      = null; //���X�R�[�h

            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
                        
            //MainAccount���擾
            long l_lngAccountId = l_opLoginSec.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            //�Ǘ��҃Z�b�V��������R�[�����ꂽ�ꍇ
            if (l_lngAccountId == 0L)
            {
                long l_lngOrderId = 0L;
                //���N�G�X�g == �敨�������ڍׂ̏ꍇ
                if (l_request instanceof WEB3FuturesExecuteDetailsRequest)
                {
                    WEB3FuturesExecuteDetailsRequest l_detailRequest =
                        (WEB3FuturesExecuteDetailsRequest)l_request;
                    l_lngOrderId = Long.parseLong(l_detailRequest.id);
                }
                //���N�G�X�g == �敨������藚���̏ꍇ
                else if (l_request instanceof WEB3FuturesOrderHistoryRequest)
                {
                    WEB3FuturesOrderHistoryRequest l_historyRequest =
                        (WEB3FuturesOrderHistoryRequest)l_request;
                    l_lngOrderId = Long.parseLong(l_historyRequest.id);
                }
                //���N�G�X�g == �敨�ԍό��ʈꗗ�̏ꍇ
                else if (l_request instanceof WEB3FuturesCloseMarginContractListRequest)
                {
                    WEB3FuturesCloseMarginContractListRequest l_contractRequest =
                        (WEB3FuturesCloseMarginContractListRequest)l_request;
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
            
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
                
            //�،���ЃR�[�h���擾
            Institution l_institution = l_mainAccount.getInstitution();
            l_strInstitutionCode = l_institution.getInstitutionCode();
            //���X�R�[�h���擾
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();   
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW        
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode);
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.���i�R�[�h = null
            l_context.setProductCode(null); 
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            //����J�����_�R���e�L�X�g.������t���i = �h05�F�敨�h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =�h07�F�Ɖ�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);   
            
            //������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context); 
                
            //��t�����A���t���[�����Z�b�g����B   
            WEB3GentradeTradingTimeManagement.setTimestamp();
              
            log.exiting(STR_METHOD_NAME);
      
        } 
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
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
     * @@roseuid 409EFFA3014F
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object,Object)";
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
            WEB3IfoAttributeNameDef.ACCOUNT_ID,
            null);
            
        log.exiting(STR_METHOD_NAME);
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
        final String STR_METHOD_NAME = "onThrowable(Object,Throwable)";
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
            WEB3IfoAttributeNameDef.ACCOUNT_ID,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
