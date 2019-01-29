head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNettingExchangeServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �O�������בփl�b�e�B���O�T�[�r�X�C���^�Z�v�^(WEB3FeqNettingExchangeInterceptor.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/09/10 �����F (���u) �V�K�쐬 ���f��549
Revision History : 2010/09/15 �����F (���u) �d�l�ύX���f��No.553 
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������בփl�b�e�B���O�T�[�r�X�C���^�Z�v�^)<BR>
 * �O�������בփl�b�e�B���O�T�[�r�X�C���^�Z�v�^<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3FeqNettingExchangeServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqNettingExchangeServiceInterceptor.class);

    /**
     * �R���X�g���N�^�B<BR>
     */
    public WEB3FeqNettingExchangeServiceInterceptor()
    {

    }

    /**
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@�Ɩ��������[�����Z�b�g����B<BR>
     *�@@�|������ԊǗ�.setBusinessTimestamp()���R�[������B<BR>
     * 2�jThreadLocalSystemAttributesRegistry.setAttribute()�ɂĈבփl�b�e�B���O�t���O��"TRUE"���Z�b�g����B<BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * 
     * @@param l_serviceParams - (�T�[�r�X�̈���)<BR>
     * �T�[�r�X�̈����z��<BR>
     * @@return Object
     * @@roseuid 429FED39003F
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);        
         
        //�P�j�@@�Ɩ��������[�����Z�b�g����B 
        //�|������ԊǗ�.setBusinessTimestamp()���R�[������B
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();
        
        //2�jThreadLocalSystemAttributesRegistry.setAttribute()�ɂĈבփl�b�e�B���O�t���O��"TRUE"���Z�b�g����B
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.NETTING_EXCHANGE_FLAG,
            BooleanEnum.TRUE);
        
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
     * �בփl�b�e�B���O�t���O<BR>
     * @@param l_context - (onCall�ԋp�l)<BR>
     * onCall�ԋp�l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h�ԋp�l)<BR>
     * �T�[�r�X���\�b�h�ԋp�l<BR>
     * @@roseuid 429FED390042
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
            
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        //�בփl�b�e�B���O�t���O
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.NETTING_EXCHANGE_FLAG,
            null);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * �בփl�b�e�B���O�t���O<BR>
     * @@param l_obj - (onCall�ԋp�l)<BR>
     * onCall�ԋp�l<BR>
     * @@param l_throwable - (��O)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 429FED39004E
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
            
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        
        //�בփl�b�e�B���O�t���O
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3ThreadLocalSystemAttributePathDef.NETTING_EXCHANGE_FLAG,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
