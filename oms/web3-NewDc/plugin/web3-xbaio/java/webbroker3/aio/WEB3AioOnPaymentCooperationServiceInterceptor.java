head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.33.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOnPaymentCooperationServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���A�g�T�[�r�X�C���^�Z�v�^ (WEB3AioOnPaymentCooperationServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.aio.message.WEB3AioOnPaymentCooperationRequest;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o���A�g�T�[�r�X�C���^�Z�v�^) <BR>
 * �o���A�g�T�[�r�X�C���^�Z�v�^ �N���X <BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationServiceInterceptor implements Interceptor
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOnPaymentCooperationServiceInterceptor.class);
    
    /**
     * @@roseuid 41E77F4C0203
     */
    public WEB3AioOnPaymentCooperationServiceInterceptor()
    {
    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B  <BR> 
     * <BR> 
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B  <BR> 
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>  
     * <BR> 
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B  <BR> 
     * �@@�|�T�[�r�X�̈���[0]�̔z��̍ŏ��̗v�f�𒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B<BR>  
     * �@@�|�����P�ʂ̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B  <BR> 
     * <BR> 
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = ���N�G�X�g.�،���ЃR�[�h <BR> 
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = null <BR> 
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h  <BR> 
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h16�F�o���h  <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h  <BR> 
     * �@@����J�����_�R���e�L�X�g.������t���i = null  <BR> 
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null <BR> 
     * <BR> 
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR> 
     * ������ԃR���e�L�X�g���Z�b�g����B  <BR> 
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR> 
     * <BR> 
     * @@param l_method - �T�[�r�X���\�b�h
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 41BCF2EB0056
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B  
        //�@@�|�T�[�r�X�̈���[0]�̔z��̍ŏ��̗v�f�𒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B  
        //�@@�|�����P�ʂ̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B 
        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.debug("array is null OR the array's length is 0");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        WEB3AioOnPaymentCooperationRequest l_request = null;
        if(l_serviceParams[0] instanceof WEB3AioOnPaymentCooperationRequest)
        {
            l_request = (WEB3AioOnPaymentCooperationRequest) l_serviceParams[0];
        }
        else
        {
            log.debug(" �p�����[�^Error ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }               
       
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();       
                
        //����J�����_�R���e�L�X�g.�،���ЃR�[�h = ���N�G�X�g.�،���ЃR�[�h 
        String l_strInstitutionCode = l_request.institutionCode;            
        l_context.setInstitutionCode(l_strInstitutionCode);
        
        //����J�����_�R���e�L�X�g.���X�R�[�h = null   
        l_context.setBranchCode(null);
        
        //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        
        //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h16�F�o���h 
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.PAYMENT);
        
        //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        //����J�����_�R���e�L�X�g.������t���i = null   
        l_context.setOrderAcceptProduct(null);
        
        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null 
        l_context.setOrderAcceptTransaction(null);

        //�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B  
        //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
   
        log.exiting(STR_METHOD_NAME);        
        return null;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * 
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 41BCF2EB0076
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        String l_strMethodName = "onReturn(Object l_context, Object l_returnValue)";
        log.entering(l_strMethodName);
        
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(l_strMethodName);
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * 
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 41BCF2EB0095
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        String l_strMethodName = "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(l_strMethodName);
        
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����

        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(l_strMethodName);
    }
}@
