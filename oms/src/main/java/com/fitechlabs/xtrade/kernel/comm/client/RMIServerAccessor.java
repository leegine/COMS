// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RMIServerAccessor.java

package com.fitechlabs.xtrade.kernel.comm.client;

import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import java.rmi.Naming;
import java.rmi.RemoteException;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            InitialConnectException, CommunicationException, ClientSideException, RemoteAccessor, 
//            ServerAccessor, ServerException, ClientLogging

public final class RMIServerAccessor
    implements ServerAccessor
{
    private static abstract class RemoteProxy
    {

        abstract Object doRequest(RemoteAccessor remoteaccessor, Object obj)
            throws RemoteException;

        private RemoteProxy()
        {
        }

    }


    public RMIServerAccessor(String url)
    {
        this.url = url;
    }

    public String toString()
    {
        return "RMI(" + url + ")";
    }

    public Response doRequestO(String xmlRequest)
        throws CommunicationException, ServerException
    {
        return doRequest(stringObjectProxy, xmlRequest);
    }

    public Response doRequest(Request xmlRequest)
        throws CommunicationException, ServerException
    {
        return doRequest(objectObjectProxy, xmlRequest);
    }

    public String doRequest(String xmlRequest)
        throws CommunicationException, ServerException
    {
        throw new UnsupportedOperationException("not yet supported");
    }

    private Response doRequest(RemoteProxy proxy, Object request)
        throws CommunicationException, ServerException
    {
        try
        {
            initAccessor();
        }
        catch(Exception e)
        {
            throw new InitialConnectException("Connect failed, " + e);
        }
        Response response;
        try
        {
            response = (Response)proxy.doRequest(accessor, request);
        }
        catch(RemoteException re)
        {
            ClientLogging.debug("RMIServerAccessor.onDoRequest() threw ", re);
            throw new CommunicationException("RMIServerAccessor.onDoRequest() threw " + re);
        }
        catch(Exception excep)
        {
            ClientLogging.warn("RMIServerAccessor.onDoRequest() client side exception ", excep);
            throw new ClientSideException(excep);
        }
        return response;
    }

    private void initAccessor()
        throws Exception
    {
        if(accessor == null)
            synchronized(this)
            {
                if(accessor == null)
                    accessor = (RemoteAccessor)Naming.lookup(url);
            }
    }

    public static final long serialVersionUID = 1L;
    private static final boolean DETAILS = false;
    private String url;
    private transient RemoteAccessor accessor;
    private static RemoteProxy stringObjectProxy = new RemoteProxy() {

        Object doRequest(RemoteAccessor acc, Object xmlString)
            throws RemoteException
        {
            return acc.doRequestO((String)xmlString);
        }

    }
;
    private static RemoteProxy objectObjectProxy = new RemoteProxy() {

        Object doRequest(RemoteAccessor acc, Object xmlRequest)
            throws RemoteException
        {
            return acc.doRequest((Request)xmlRequest);
        }

    }
;

}
