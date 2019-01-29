head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.24.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioMultiBankTelegramProcessServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �}���`�o���N�d�������T�[�r�X�C���^�Z�v�^(WEB3AioMultiBankTelegramProcessServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 �����(���u) �V�K�쐬
                   2004/10/25 ���� (���u) ���r���[   
                   2005/1/11 ���E (���u) �c�Ή�  
*/

package webbroker3.aio;

import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�}���`�o���N�d�������T�[�r�X�C���^�Z�v�^)<BR>
 * �}���`�o���N�d�������T�[�r�X�C���^�Z�v�^�N���X
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AioMultiBankTelegramProcessServiceInterceptor implements Interceptor 
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AioMultiBankTelegramProcessServiceInterceptor.class);

    /**
     * @@roseuid 415A78190319
     */
    public WEB3AioMultiBankTelegramProcessServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR> 
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR> 
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR> 
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR> 
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR> 
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h14�F�����h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h10�F�����h<BR> 
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR> 
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * @@return Object
     * @@roseuid 41255D1401F8
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
               
        //======remain zhou-yong NO.1 ��Q�[U00621 begin =========
        
        //�T�[�r�X���\�b�h�J�n���ɃR�[�������B
        //����J�����_�����p����R���e�L�X�g�𐶐�����B 
        //�ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j 
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();  

        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B 

        //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        
        //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h14�F�����h 
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.RECIEPT);
        
        //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        //����J�����_�R���e�L�X�g.������t���i = �h14�F���o���h  
        l_context.setOrderAcceptProduct(
            WEB3OrderAccProductDef.PAYMENT);
        
        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h10�F�����h 
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CASH_IN);

        //======remian zhou-yong NO.1 ��Q�[U00621 end =========
        
        //-ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g
        ThreadLocalSystemAttributesRegistry.setAttribute(
              WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
              l_context);

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
     * @@param l_context - (onCall���^�[���l)<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * @@roseuid 41255D1401FB
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
            
        log.exiting(STR_METHOD_NAME);  
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
     * <BR>
     * @@param l_obj - (onCall���^�[���l)
     * @@param l_throwable - (��O�I�u�W�F�N�g)
     * @@roseuid 41255D1401FE
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
        log.exiting(STR_METHOD_NAME); 

    }
}
@
