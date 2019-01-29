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
filename	WEB3FuturesChangeClosingContractInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����ԍϓ��̓T�[�r�X�C���^�Z�v�^(WEB3FuturesChangeClosingContractInputServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/23 ���Q (���u) �V�K�쐬
*/

package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeInputRequest;
import webbroker3.util.WEB3LogUtility;



/**
 * (�����w���敨�����ԍϓ��̓T�[�r�X�C���^�Z�v�^)
 * 
 * @@author ���Q
 * @@version 1.0 
 */
public class WEB3FuturesChangeClosingContractInputServiceInterceptor implements Interceptor 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesChangeClosingContractInputServiceInterceptor.class);
                
    /**
     * @@roseuid 40F7B0E2030D
     */
    public WEB3FuturesChangeClosingContractInputServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService��<BR>
     * ���e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     *   OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     *   OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = <BR>
     *   �h12�F�����w���敨OP�i��������j�h<BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �i*1) �����Y�����R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h05�F�敨�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h05�F�����h<BR>
     * <BR>
     *  (*1) �����Y�����R�[�h�̎擾���@@<BR>
     * �@@���N�G�X�g�f�[�^.�h�c�ɊY�����钍���I�u�W�F�N�g�𐶐�����B<BR>
     * �@@����.getOrderUnits()[0].getProductId()�ɂĖ����h�c���擾����B<BR>
     * �@@�����h�c�ɊY������敨OP�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������敨OP����.get�����Y�����R�[�h()�B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     * �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 40A8BC9C039F
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        //�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
        log.debug("OnCall�̈����^�C�v���`�F�b�N_start");       
        if (!(l_serviceParam[0] instanceof WEB3FuturesCloseMarginChangeInputRequest))
        {
            log.error("�p�����[�^�^�C�v�s���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);               
        }
        
        WEB3FuturesCloseMarginChangeInputRequest l_request = (WEB3FuturesCloseMarginChangeInputRequest)l_serviceParam[0];
                
        String l_strInstitutionCode = null; //�،���ЃR�[�h
        String l_strBranchCode      = null; //���X�R�[�h
        String l_strUnderlyingProductCode = null; //�����Y�����R�[�h
        
        //���e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
        try
        {
            long l_lngAccountId = 0;    // �����R�[�h
            FinApp l_finApp = null;         
            AccountManager l_accMgr = null; 
            MainAccount l_mainAccount = null;
            OrderUnit[] l_orderUnits = null;
            
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
                   
            //MainAccount���擾
            l_lngAccountId = l_opLoginSec.getAccountId();
            l_finApp = (FinApp) Services.getService(FinApp.class);
            l_accMgr = l_finApp.getAccountManager();       
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            
            //�،���ЃR�[�h���擾
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            //���X�R�[�h���擾
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            
            WEB3FuturesOrderManagerImpl l_orderManager = 
               (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            log.debug("l_request.id = "+ l_request.id);   
            l_orderUnits = l_orderManager.getOrder(Long.parseLong(l_request.id)).getOrderUnits();
            
            if (l_orderUnits == null)
            {
                throw new NotFoundException("--no data found--");
            }
            
            //�����h�c���擾����   
            //long l_ProductId = l_orderUnit[0].getProduct().getProductId();
            long l_ProductId = ((IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject()).getProductId();
            log.debug("�����h�c���擾���܂����BProductId = " + l_ProductId);
            //�敨OP�����}�l�[�W�����擾����   
            WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
                    (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
            //�敨OP�����I�u�W�F�N�g�𐶐�����
            WEB3IfoProductImpl l_ifoProduct = null;                  
                l_ifoProduct = (WEB3IfoProductImpl) 
                    l_ifoProductManagerImpl.getProduct(l_ProductId);

            //�����Y�����R�[�h�̎擾             
            l_strUnderlyingProductCode = l_ifoProduct.getUnderlyingProductCode();
            log.debug("�����Y�����R�[�h���擾���܂����B = " + l_strUnderlyingProductCode);
            
            WEB3GentradeTradingClendarContext l_context =
                             new WEB3GentradeTradingClendarContext();
                             
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode); 
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 =   �h12�F�����w���敨OP�i��������j�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP_CHANGE_CANCEL); 
            //����J�����_�R���e�L�X�g.���i�R�[�h = �i*1) �����Y�����R�[�h  
            l_context.setProductCode(l_strUnderlyingProductCode);        
            //����J�����_�R���e�L�X�g.������t���i = �h05�F�敨�h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FUTURE);         
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h05�F�����h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);
        
            //������ԃR���e�L�X�g���Z�b�g����B
            log.debug("������ԃR���e�L�X�g���Z�b�g����");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);      
            
            //�R�j�@@��t�����A���t���[�����Z�b�g����B
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            log.exiting(STR_METHOD_NAME);
            return l_context;
        } 
        catch (NotFoundException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,            
                l_ex.toString(),
                l_ex); 
        }
        
        catch (WEB3BaseException l_ex)
        {
            log.error("�Ɩ��A�v���P�[�V�����Ŏg�p����V�X�e���G���[�̗�O");
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,                    
                l_ex.toString(),
                l_ex);                       
        }
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 40A8BC9C03BE
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object,Object)";
            
        log.entering(STR_METHOD_NAME);
        log.debug("������ԊǗ�.TIMESTAMP_TAG = " + WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        log.debug("������ԊǗ�.OFFSET_TAG = " + WEB3GentradeTradingTimeManagement.OFFSET_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.debug("������ԊǗ�.TRADING_CAL_CONTEXT_PATH = " + WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
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
     * @@roseuid 40A8BC9C03DD
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object,Throwable)";
            
        log.entering(STR_METHOD_NAME);
        
        log.debug("������ԊǗ�.TIMESTAMP_TAG = " + WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        log.debug("������ԊǗ�.OFFSET_TAG = " + WEB3GentradeTradingTimeManagement.OFFSET_TAG);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.debug("������ԊǗ�.TRADING_CAL_CONTEXT_PATH = " + WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);     
    }
}
@
