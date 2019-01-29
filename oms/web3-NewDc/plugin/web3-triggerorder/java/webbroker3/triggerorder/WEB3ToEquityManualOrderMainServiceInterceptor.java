head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToEquityManualOrderMainServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����蓮�������C���T�[�r�X�C���^�Z�v�^(WEB3ToEquityManualOrderMainServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/3/17 SRA���� �V�K�쐬
*/
package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����蓮�������C���T�[�r�X�C���^�Z�v�^)<BR>
 * 
 * @@author SRA����
 * @@version 1.0
 */
public class WEB3ToEquityManualOrderMainServiceInterceptor implements Interceptor 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToEquityManualOrderMainServiceInterceptor.class);
    
    /**
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[]) ";
        log.entering(STR_METHOD_NAME);
       
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>  
     * ����J�����_�R���e�L�X�g�N���A�����B   <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>   
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG   <BR>
     * ������ԊǗ�.OFFSET_TAG   <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * @@param l_context
     * @@param l_returnValue
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
            
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, 
            null);        
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>  
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR> 
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG   <BR>
     * ������ԊǗ�.OFFSET_TAG   <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * @@param l_obj
     * @@param l_throwable
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {        
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable) ";
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        log.exiting(STR_METHOD_NAME);
    }

}
@
