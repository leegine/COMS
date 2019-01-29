head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.26.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d7db4ef1a89;
filename	WEB3SyncProcStatusInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3SyncProcStatusInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/04/21  劉(FLJ) 新規作成
 */

package webbroker3.syncclt;

import java.lang.reflect.*;
import java.net.*;
import java.sql.*;
import java.util.*;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.syncclt.data.*;
import webbroker3.util.*;

public class WEB3SyncProcStatusInterceptor
    implements Interceptor
{

    final private String IDLE = "0";

    final private String PROC = "1";

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SyncProcStatusInterceptor.class);

    public WEB3SyncProcStatusInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        String l_context = l_method.getDeclaringClass().getName();
        update(l_context);
        lock(l_context);
        log.exiting(STR_METHOD_NAME);
        return l_context;
    }

    /**
     * サービスメソッド終了時にコールされる。
     * @@param l_context
     * @@param l_returnValue
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(String)";
        release(l_context.toString());
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる
     * @@param l_obj
     * @@param l_throwable
     */
    public void onThrowable(Object l_context, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(String)";
        release(l_context.toString());
        log.exiting(STR_METHOD_NAME);

    }

    private void update(final String l_strServiceName)
    {
        final String STR_METHOD_NAME = "update(String)";
        log.entering(STR_METHOD_NAME);
        try
        {
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            final String l_strWhere =
                "service_name = ? ";
            final String l_strCondition = "for update";

            final Object l_bindVars[] =
                {
                new String(l_strServiceName),
            };

            l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW,
                                           new TransactionCallback()
            {
                public Object process() throws DataNetworkException,
                    DataQueryException,
                    DataCallbackException
                {

                    //DBロック
                    List l_lisRows = l_queryProcesser.doFindAllQuery(
                        SyncProcStatusRow.TYPE,
                        l_strWhere, l_strCondition, l_bindVars);
                    String l_strApp = getAppName();
                    Timestamp l_tm = GtlUtils.getSystemTimestamp();
                    if (l_lisRows.size() == 0)
                    {
                        SyncProcStatusParams l_params = new SyncProcStatusParams();
                        l_params.setServiceName(l_strServiceName);
                        l_params.setApHostName(l_strApp);
                        l_params.setStatus(PROC);
                        l_params.setCreatedTimestamp(l_tm);
                        l_params.setLastUpdatedTimestamp(l_tm);
                        l_queryProcesser.doInsertQuery(l_params);

                    }
                    else
                    {
                        //DB更新
                        Map l_changes = new HashMap();
                        l_changes.put("status",
                                      PROC);
                        l_changes.put("ap_host_name",
                                      getAppName());
                        l_changes.put("last_updated_timestamp",
                                      l_tm);
                        l_queryProcesser.doUpdateQuery(new SyncProcStatusPK(
                            l_strServiceName),
                            l_changes);
                    }

                    return null;
                }
            }
            );

        }
        catch (Throwable l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
        }
    }

    private void lock(String l_strServiceName)
    {
        final String STR_METHOD_NAME = "lock(String)";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

            final String l_strWhere =
                "service_name = ? ";
            final String l_strCondition = "for update";

            final Object l_bindVars[] =
                {
                new String(l_strServiceName),
            };

            //DBロック
            List l_lisRows = l_queryProcesser.doFindAllQuery(
                SyncProcStatusRow.TYPE,
                l_strWhere, l_strCondition, l_bindVars);

        }
        catch (Throwable l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
        }
    }

    private void release(String l_strServiceName)
    {
        final String STR_METHOD_NAME = "release(String)";
        log.entering(STR_METHOD_NAME);
        try
        {
            HashMap l_changes = new HashMap();
            l_changes.put("status",
                          IDLE);
            l_changes.put("ap_host_name",
                          "NONE");

            l_changes.put("last_updated_timestamp",
                          GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_queryProcesser.doUpdateQuery(new SyncProcStatusPK(l_strServiceName),
                                           l_changes);

            log.exiting(STR_METHOD_NAME);
        }
        catch (Throwable l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
        }
    }

    private String getAppName()
    {
        try
        {
            InetAddress l_localHost = InetAddress.getLocalHost();
            return l_localHost.getHostName();
        }
        catch (UnknownHostException ex)
        {
            log.error(ex.getMessage(), ex);
            return "";
        }

    }

}
@
