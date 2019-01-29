head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQGatewayInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3MQGatewayInterceptor�N���X(WEB3MQGatewayInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/26 �R�c�@@��i (FLJ) �V�K�쐬
                  : 2005/02/18 �R�c�@@��i (FLJ) onReturn�ŃR���e�L�X�g�ɏ�񂪐ݒ肳��Ă��Ȃ��ꍇ�̏�����ύX
                  : 2003/03/07 �R�c�@@��i (FLJ) �����̃��b�Z�[�W���M�ł���悤�ɕύX
 */
package webbroker3.mqgateway;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

/**
 * 
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3MQGatewayInterceptor implements Interceptor
{

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static final WEB3LogUtility LOG = WEB3LogUtility
            .getInstance(WEB3MQGatewayInterceptor.class);
    
    /**
     * �f�o�b�N�t���O
     */
    private static final boolean DBG = LOG.ison();

    /**
     * @@see com.fitechlabs.xtrade.kernel.interceptor.Interceptor#onCall(java.lang.reflect.Method, java.lang.Object[])
     */
    public Object onCall(Method l_method, Object[] l_objArguments)
    {
        // �R���e�L�X�g����ݒ�
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MQGatewayPlugin.MQ_GATEAY_INTERCEPTOR_ATTRIBUTE_NAME,
                new ArrayList());
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MQGatewayPlugin.MQ_GATEAY_INTERCEPTOR_ATTRIBUTE_NAME,
                Boolean.TRUE);
        return null;
    }

    /**
     * @@see com.fitechlabs.xtrade.kernel.interceptor.Interceptor#onReturn(java.lang.Object, java.lang.Object)
     */
    public void onReturn(Object l_objContext, Object l_objReturnValue)
    {

        // ���M���e���擾
        List l_container = (List) ThreadLocalSystemAttributesRegistry
                .getAttribute(WEB3MQGatewayPlugin.MESSAGE_SPEC_ATTRIBUTE_NAME);

        // ���M���e���ݒ肳��Ă��Ȃ��ꍇ
        if (l_container == null || l_container.size() == 0)
        {
            removeContext();
            LOG.debug("WEB3MQMessageSpec���ݒ肳��Ă��Ȃ�����MQ�g���K�[�𔭍s���܂���ł����B");
            return;
        }

        // MQ�g���K�[�𔭍s
        for (int i = 0; i < l_container.size(); i++)
        {
            WEB3MQMessageSpec l_messageSpec = 
                (WEB3MQMessageSpec) l_container.get(i);
            if (l_messageSpec != null)
            {
                send(l_messageSpec);
            }
        }

        // �R���e�L�X�g�����N���A
        removeContext();

    }

    /**
     * @@see com.fitechlabs.xtrade.kernel.interceptor.Interceptor#onThrowable(java.lang.Object, java.lang.Throwable)
     */
    public void onThrowable(Object l_objContext, Throwable l_throwable)
    {
        // �R���e�L�X�g�����N���A
        removeContext();
    }

    /**
     * MQ�g���K�[�𔭍s����B
     * 
     * @@param l_messageSpec ���M���e
     */
    private void send(WEB3MQMessageSpec l_messageSpec)
    {
        // Sender�T�[�r�X���擾
        WEB3MQMessageSenderService l_sender = (WEB3MQMessageSenderService) Services
                .getService(WEB3MQMessageSenderService.class);
        try
        {
            // MQ�g���K�[���s
            WEB3MQSendResult l_result = l_sender.send(l_messageSpec);
            if (l_result.isSuccessResult())
            {
                if (DBG) {
                    LOG.debug("MQ�g���K�[�𔭍s���܂���!!! [WEB3MQMessageSpec="
                            + l_messageSpec.toString() + "]");
                }
            } else
            {
                LOG.warn("MQ�g���K�[�̔��s�Ɏ��s���܂���!!! [WEB3MQMessageSpec="
                        + l_messageSpec.toString() + ", �G���[���"
                        + l_result.getErrorInfo().toString() + "]");
            }
        } catch (WEB3SystemLayerException l_sle)
        {
            LOG.error("MQ�g���K�[�̔��s�Ɏ��s���܂���!!!  [WEB3MQMessageSpec="
                    + l_messageSpec.toString() + "]", l_sle);
        }
    }

    /**
     * �R���e�L�X�g�����N���A����B
     */
    private void removeContext()
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MQGatewayPlugin.MQ_GATEAY_INTERCEPTOR_ATTRIBUTE_NAME, null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MQGatewayPlugin.MESSAGE_SPEC_ATTRIBUTE_NAME, null);
    }

}@
