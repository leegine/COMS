head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.25.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3AffinityDescendHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 下り処理ハンドラ（Affinity）(WEB3AffinityDescendHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/09/08 劉 新規作成
 */

package webbroker3.system.tune.affinity.handler;

import com.fitechlabs.xtrade.kernel.handler.*;
import com.fitechlabs.xtrade.kernel.message.*;
import com.fitechlabs.xtrade.kernel.util.*;
import webbroker3.system.tune.affinity.message.*;

/**
 *  下り処理ハンドラ（Affinity）
 */
public class WEB3AffinityDescendHandler
    implements MessageHandler
{

    public WEB3AffinityDescendResponse handleWEB3AffinityDescendRequest(
        WEB3AffinityDescendRequest l_req) throws Exception
    {

        WEB3AffinityDescendResponse l_resp = new WEB3AffinityDescendResponse();
        if (l_req.request == null || l_req.request.length == 0)
        {
            return l_resp;
        }

        try
        {

            /*
                         if (l_req.account_id != null)
                         {
                ThreadLocalSystemAttributesRegistry.
                    setAttribute(
                    ContextDrivenMultiPoolDataSource.ATTRIBUTE_NAME_FOR_CTX_NAME,
                    "account-id");
                ;
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    ContextDrivenMultiPoolDataSource.ATTRIBUTE_NAME_FOR_KEY_VALUE,
                    String.valueOf(l_req.account_id));
                         }
                         else if (l_req.head2_order_request_number != null)
                         {
                ThreadLocalSystemAttributesRegistry.
                    setAttribute(
                    ContextDrivenMultiPoolDataSource.ATTRIBUTE_NAME_FOR_CTX_NAME,
                    "request_number");
                ;
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    ContextDrivenMultiPoolDataSource.ATTRIBUTE_NAME_FOR_KEY_VALUE,
                    String.valueOf(l_req.head2_order_request_number));
                         }
             */

            int n = l_req.request.length;
            l_resp.response = new Response[n];
            for (int i = 0; i < n; i++)
            {
                com.fitechlabs.xtrade.kernel.message.Request request = l_req.request[i];
                l_resp.response[i] = MessageHandlerDispatcher.Handle(request);
            }
            return l_resp;
        }
        finally
        {
            ThreadLocalSystemAttributesRegistry.clearAttributes();
        }

    }

}
@
