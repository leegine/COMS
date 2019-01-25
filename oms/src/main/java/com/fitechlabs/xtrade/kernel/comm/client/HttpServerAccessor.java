// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HttpServerAccessor.java

package com.fitechlabs.xtrade.kernel.comm.client;

import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.client:
//            InitialConnectException, ClientSideException, CommunicationException, ServerAccessor, 
//            ServerException, ClientLogging

public final class HttpServerAccessor
    implements ServerAccessor
{

    public HttpServerAccessor(String urlStub)
    {
        this.urlStub = urlStub;
    }

    public String toString()
    {
        return "Http(" + urlStub + ")";
    }

    public Response doRequestO(String xmlRequest)
        throws CommunicationException, ServerException
    {
        return (Response)onDoRequest(xmlRequest, "binary/sed");
    }

    public Response doRequest(Request xmlRequest)
        throws CommunicationException, ServerException
    {
        return (Response)onDoRequest(xmlRequest, "binary/sed");
    }

    public String doRequest(String xmlRequest)
        throws CommunicationException, ServerException
    {
        return (String)onDoRequest(xmlRequest, "text/xml");
    }

    private Object onDoRequest(Object xmlRequest, String contentType)
        throws CommunicationException, ServerException
    {
        URL url = null;
        URLConnection conn = null;
        OutputStream ostream = null;
        InputStream istream = null;
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        Object response = null;
        try
        {
            url = new URL(urlStub);
            conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-type", contentType);
            conn.setRequestProperty("Accept", contentType);
            ostream = conn.getOutputStream();
        }
        catch(IOException e)
        {
            throw new InitialConnectException("Connect failed, " + e);
        }
        try
        {
            if("text/xml".equals(contentType))
            {
                byte bytes[] = ((String)xmlRequest).getBytes("UTF8");
                ostream.write(bytes);
                ostream.flush();
            } else
            {
                output = new ObjectOutputStream(ostream);
                output.writeObject(xmlRequest);
                output.close();
                output = null;
            }
            istream = conn.getInputStream();
            if("text/xml".equals(contentType))
            {
                byte responseBytes[] = getBytes(istream);
                response = new String(responseBytes, "UTF8");
                istream.close();
                istream = null;
            } else
            {
                input = new ObjectInputStream(istream);
                response = input.readObject();
            }
        }
        catch(InvalidClassException ice)
        {
            ClientLogging.debug("HttpServerAccessor.onDoRequest() client side exception", ice);
            throw new ClientSideException(ice);
        }
        catch(FileNotFoundException fnfe)
        {
            ClientLogging.debug("HttpServerAccessor.onDoRequest() mapping file-not-found to initial-connect", fnfe);
            throw new InitialConnectException("File-not-found in open-input " + fnfe);
        }
        catch(IOException ioe)
        {
            ClientLogging.debug("HttpServerAccessor.onDoRequest() communications exception", ioe);
            throw new CommunicationException("HttpServerAccessor.onDoRequest() threw " + ioe);
        }
        catch(Exception excep)
        {
            ClientLogging.warn("HttpServerAccessor.onDoRequest() client side exception ", excep);
            throw new ClientSideException(excep);
        }
        finally
        {
            if(input != null)
                try
                {
                    input.close();
                }
                catch(IOException ioe)
                {
                    ClientLogging.warn("onDoRequest() closing input: " + ioe);
                }
            if(output != null)
                try
                {
                    output.close();
                }
                catch(IOException ioe)
                {
                    ClientLogging.warn("onDoRequest() closing output: " + ioe);
                }
        }
        return response;
    }

    private static byte[] getBytes(InputStream input)
        throws Exception
    {
        byte buffer[] = new byte[1024];
        ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
        int count;
        while((count = input.read(buffer)) > 0) 
            output.write(buffer, 0, count);
        return output.toByteArray();
    }

    private static String findTag(String text, String begin, String end)
    {
        int i = text.indexOf(begin);
        if(i >= 0)
        {
            i += begin.length();
            int j = text.indexOf(end, i);
            if(j >= 0)
                return text.substring(i, j);
        }
        return null;
    }

    public static final long serialVersionUID = 2L;
    private static final boolean DETAILS = false;
    private String urlStub;
    private static final String TEXT_XML = "text/xml";
    private static final String BINARY_SED = "binary/sed";
}
