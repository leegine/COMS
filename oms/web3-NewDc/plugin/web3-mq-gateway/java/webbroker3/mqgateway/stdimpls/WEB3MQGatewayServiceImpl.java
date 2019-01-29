head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQGatewayServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : MQGatewayService�̎����N���X(WEB3DefaultMQSendResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2003/02/13 �R�c�@@��i(FLJ) �V�K�쐬
                 : 2003/03/07 �R�c�@@��i(FLJ) �����̃��b�Z�[�W���M���e��ݒ�ł���悤�ɕύX
                 : 2003/03/07 �R�c�@@��i(FLJ) �f�o�b�N�p�̃��O�o�͂�ǉ�
*/
package webbroker3.mqgateway.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mqgateway.WEB3MQGatewayPlugin;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.util.WEB3LogUtility;

/**
 * MQGatewayService�̎����N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3MQGatewayServiceImpl implements WEB3MQGatewayService
{
    
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static final WEB3LogUtility LOG = 
        WEB3LogUtility.getInstance(WEB3MQGatewayServiceImpl.class);
    
    /**
     * �f�o�b�N�t���O
     */
    private static final boolean DBG = LOG.ison();

    /**
     * @@see webbroker3.mqgateway.WEB3MQGatewayService#send(webbroker3.mqgateway.WEB3MQMessageSpec)
     */
    public WEB3MQSendResult send(WEB3MQMessageSpec l_messageSpec)
    {
        put(l_messageSpec);
        return WEB3DefaultMQSendResult.SUCCESS_RESULT_INSTANCE;
    }

    /**
     * 
     */
    protected void put(WEB3MQMessageSpec l_messageSpec)
    {
        
        if (l_messageSpec == null)
        {
            return;
        }
        
        if (!isMQGatewayInterceptorIsEnabled())
        {
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                    getClass().getName() + ".put(WEB3MQMessageSpec)", 
                    "WEB3MQGatewayInterceptor���ݒ肳��Ă��܂���B");
        }
        
        List l_container = getMQMessageSpecContainer();
        l_container.add(l_messageSpec);
        
        if (DBG)
        {
            LOG.debug(
                "WEB3MQMessageSpec���ݒ肳��܂����B [WEB3MQMessageSpec="
                + l_messageSpec.toString() + "]");
        }
    }
    
    private boolean isMQGatewayInterceptorIsEnabled()
    {
        Boolean l_blnEnabled = (Boolean) ThreadLocalSystemAttributesRegistry.
            getAttribute(WEB3MQGatewayPlugin.MQ_GATEAY_INTERCEPTOR_ATTRIBUTE_NAME);
        if (l_blnEnabled != null)
        {
            return l_blnEnabled.booleanValue();
        } else {
            return false;
        }
    }
    
    private List getMQMessageSpecContainer()
    {
        List l_container = 
            (List) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3MQGatewayPlugin.MESSAGE_SPEC_ATTRIBUTE_NAME);
        if (l_container == null)
        {
            l_container = new ArrayList();
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3MQGatewayPlugin.MESSAGE_SPEC_ATTRIBUTE_NAME,
                    l_container);
        }
        return l_container;
    }
    
}
@
