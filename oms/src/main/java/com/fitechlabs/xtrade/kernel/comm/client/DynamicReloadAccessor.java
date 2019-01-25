// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DynamicReloadAccessor.java

package com.fitechlabs.xtrade.kernel.comm.client;

import com.fitechlabs.xtrade.kernel.message.*;
import com.fitechlabs.xtrade.kernel.message.client.ClientAccessorRequest;
import com.fitechlabs.xtrade.kernel.message.client.ClientAccessorResponse;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            InitialConnectException, ServerAccessor, CommunicationException, ServerException, 
//            RequestProxy, ClientLogging

public class DynamicReloadAccessor
    implements ServerAccessor
{

    public DynamicReloadAccessor(String configName, ServerAccessor initialAccessor)
    {
        this(configName, initialAccessor, 300, 60);
    }

    public DynamicReloadAccessor(String configName, ServerAccessor initialAccessor, int expireTimeSecs, int retryTimeSecs)
    {
        this.configName = configName;
        currentAccessor = initialAccessor;
        this.initialAccessor = initialAccessor;
        expiration = 0L;
        expireMillis = (long)expireTimeSecs * 1000L;
        retryMillis = (long)retryTimeSecs * 1000L;
    }

    public String toString()
    {
        return "Reload('" + configName + "'," + (float)expireMillis / 1000F + "," + (float)retryMillis / 1000F + ",init=" + initialAccessor + ",curr=" + currentAccessor + ")";
    }

    public Response doRequestO(String xmlRequest)
        throws CommunicationException, ServerException
    {
        RequestProxy proxy = RequestProxy.getStringObjectRequestProxy();
        return (Response)doRequest(proxy, xmlRequest);
    }

    public Response doRequest(Request xmlRequest)
        throws CommunicationException, ServerException
    {
        RequestProxy proxy = RequestProxy.getObjectObjectRequestProxy();
        return (Response)doRequest(proxy, xmlRequest);
    }

    public String doRequest(String xmlRequest)
        throws CommunicationException, ServerException
    {
        RequestProxy proxy = RequestProxy.getStringStringRequestProxy();
        return (String)doRequest(proxy, xmlRequest);
    }

    private Object doRequest(RequestProxy proxy, Object request)
        throws CommunicationException, ServerException
    {
        ServerAccessor firstTry;
        checkRefresh();
        firstTry = currentAccessor;
        return proxy.doRequest(firstTry, request);
        InitialConnectException ice;
        ice;
        ClientLogging.info("DynamicReloadAccessor - ConnectException using accessor " + firstTry);
        return proxy.doRequest(initialAccessor, request);
    }

    private void checkRefresh()
    {
        long now;
        long doublecheck;
label0:
        {
            now = System.currentTimeMillis();
            if(now <= expiration)
                return;
            doublecheck = 0L;
            synchronized(this)
            {
                if(now > expiration)
                    break label0;
            }
            return;
        }
        expiration = now + retryMillis;
        doublecheck = expiration;
        dynamicreloadaccessor;
        JVM INSTR monitorexit ;
          goto _L1
        exception;
        throw exception;
_L1:
        ServerAccessor newAccessor = null;
        try
        {
            ClientLogging.debug("DynamicReloadAccessor - trying refresh using current accessor");
            newAccessor = tryRefreshAccessor(currentAccessor);
        }
        catch(Exception ignore)
        {
            try
            {
                ClientLogging.info("DynamicReloadAccessor - trying refresh using initial accessor");
                newAccessor = tryRefreshAccessor(initialAccessor);
            }
            catch(Exception ignore2)
            {
                ClientLogging.info("DynamicReloadAccessor - refresh failed, will keep current accessor", ignore2);
            }
        }
        if(newAccessor != null)
        {
            ClientLogging.debug("DynamicReloadAccessor - got new accessor: " + newAccessor);
            synchronized(this)
            {
                if(expiration == doublecheck)
                {
                    expiration = now + expireMillis;
                    currentAccessor = newAccessor;
                } else
                {
                    ClientLogging.info("DynamicReloadAccessor - double check failed, will keep current accessor");
                }
            }
        }
        return;
    }

    private ServerAccessor tryRefreshAccessor(ServerAccessor accessor)
        throws Exception
    {
        ClientAccessorRequest request = new ClientAccessorRequest();
        request.config_name = configName;
        request.requestor_accessor = initialAccessor;
        Response response = accessor.doRequest(request);
        if(response.server_exception != null)
            throw new Exception("server_exception: " + response.server_exception.error_tag + "," + response.server_exception.error_message + "," + response.server_exception.error_debug_info);
        if(!(response instanceof ClientAccessorResponse))
        {
            throw new Exception("not a ClientAccessorResponse: " + response);
        } else
        {
            ClientAccessorResponse caResponse = (ClientAccessorResponse)response;
            return caResponse.accessor;
        }
    }

    public static long serialVersionUID = 2L;
    private String configName;
    private ServerAccessor currentAccessor;
    private ServerAccessor initialAccessor;
    private long expiration;
    private long expireMillis;
    private long retryMillis;
    public static final int DEFAULT_EXPIRE_TIME_SECS = 300;
    public static final int DEFAULT_RETRY_TIME_SECS = 60;

}
