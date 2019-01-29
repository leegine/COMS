head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3RlsDescendRacCtxInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���[���G���W�����菈��RAC�R���e�L�X�g�C���^�Z�v�^(WEB3RlsDescendRacCtxInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/11 ��(FLT) �V�K�쐬
 */
package webbroker3.omsadaptor;

import java.lang.reflect.*;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import webbroker3.rlsgateway.data.*;
import webbroker3.system.tune.affinity.*;
import webbroker3.util.*;

/**
 * �i���[���G���W�����菈��RAC�R���e�L�X�g�C���^�Z�v�^�j�B
 * @@version 1.0
 */
public class WEB3RlsDescendRacCtxInterceptor
    implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3RlsDescendRacCtxInterceptor.class);

    private static long VID = -1;

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3RlsDescendRacCtxInterceptor()
    {
    }

    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        long l_accountId = VID;
        //�ʒm�ꌏ�T�[�r�X
        if (l_serviceParams.length > 0 &&
            l_serviceParams[0] instanceof RlsConOrderHitNotifyParams)
        {
            RlsConOrderHitNotifyParams l_params = (RlsConOrderHitNotifyParams)
                l_serviceParams[0];
            l_accountId = l_params.getAccountId();

        }
        else if (l_serviceParams.length > 0 && l_serviceParams[0] instanceof Long)
        {
            Long l_params = (Long) l_serviceParams[0];
            l_accountId = l_params.longValue();

        }

        if (log.ison())
        {
            log.debug("WEB3RlsDescendRacCtxInterceptor get account_id=" + l_accountId);
        }
        if (l_accountId > 0)
        {
            WEB3DescendRacCtxService l_ctx_serv = (WEB3DescendRacCtxService) Services.
                getService(WEB3DescendRacCtxService.class);
            if (l_ctx_serv != null)
            {
                l_ctx_serv.setAccountIdCtx(l_accountId);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    public void onReturn(Object l_returnValue, Object l_context)
    {
        //RAC�R���e�L�X�g���N���A����B
        WEB3DescendRacCtxService l_ctx_serv = (WEB3DescendRacCtxService) Services.
            getService(WEB3DescendRacCtxService.class);
        if (l_ctx_serv != null)
        {
            l_ctx_serv.clearAccountIdCtx();
        }

    }

    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        //RAC�R���e�L�X�g���N���A����B
        WEB3DescendRacCtxService l_ctx_serv = (WEB3DescendRacCtxService) Services.
            getService(WEB3DescendRacCtxService.class);
        if (l_ctx_serv != null)
        {
            l_ctx_serv.clearAccountIdCtx();
        }

    }

}
@
