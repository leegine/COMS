head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsGatewayInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3RlsGatewayInterceptor�N���X(WEB3RlsGatewayInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2003/09/14 FLJ���@@�V�K�쐬
 */
package webbroker3.rlsgateway;

import java.lang.reflect.*;

import com.fitechlabs.xtrade.kernel.interceptor.*;
import com.fitechlabs.xtrade.kernel.util.*;
import webbroker3.util.*;

/**
 * <p>
 * WEB3RlsGatewayInterceptor�N���X�B<br>
 * <br>
 * </p>
 *
 * @@author FLJ��
 * @@version 1.0
 */
public class WEB3RlsGatewayInterceptor
    implements Interceptor
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsGatewayInterceptor.class);

    /**
     * @@see com.fitechlabs.xtrade.kernel.interceptor.Interceptor#onCall(java.lang.reflect.Method, java.lang.Object[])
     */
    public Object onCall(Method l_method, Object[] l_objArguments)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME, new Object());
        return null;
    }

    /**
     * @@see com.fitechlabs.xtrade.kernel.interceptor.Interceptor#onReturn(java.lang.Object, java.lang.Object)
     */
    public void onReturn(Object l_objContext, Object l_objReturnValue)
    {
        final String STR_METHOD_NAME =
            " onReturn(Object l_objContext, Object l_objReturnValue)";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * @@see com.fitechlabs.xtrade.kernel.interceptor.Interceptor#onThrowable(java.lang.Object, java.lang.Throwable)
     */
    public void onThrowable(Object l_objContext, Throwable l_throwable)
    {
        removeContext();
    }

    /**
     * �R���e�L�X�g�����N���A����B
     */
    private void removeContext()
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME, null);

    }

}
@
