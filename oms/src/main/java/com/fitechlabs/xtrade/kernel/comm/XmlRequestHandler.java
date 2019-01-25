// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlRequestHandler.java

package com.fitechlabs.xtrade.kernel.comm;

import com.fitechlabs.xtrade.kernel.error.ErrorResponseRegistry;
import com.fitechlabs.xtrade.kernel.handler.MessageHandlerDispatcher;
import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.util.ObjectPrettyPrinter;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.kernel.util.xml.*;
import java.io.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public final class XmlRequestHandler
{

    private XmlRequestHandler()
    {
    }

    public static byte[] handleRequest(byte xmlRequest[])
        throws UnsupportedEncodingException
    {
        Response responseObj = handleRequestO(xmlRequest);
        String xml = responseObjToUTF8String(responseObj);
        return xml.getBytes("UTF8");
    }

    public static String handleRequest(String xmlRequestInUTF8)
    {
        Response responseObj = handleRequestO(xmlRequestInUTF8);
        String xml = responseObjToUTF8String(responseObj);
        return xml;
    }

    public static String handleRequestFile(String fileName)
    {
        Response responseObj = handleRequestFileO(fileName);
        String xml = responseObjToUTF8String(responseObj);
        return xml;
    }

    public static Response handleRequestFileO(String fileName)
    {
        File file = new File(fileName);
        Response responseObj = handleRequestO(file);
        return responseObj;
    }

    public static String handleRequest(InputStream xmlRequest)
    {
        Response responseObj = handleRequestO(xmlRequest);
        String xml = responseObjToUTF8String(responseObj);
        return xml;
    }

    private static String responseObjToUTF8String(Response response)
    {
        String xml;
        try
        {
            xml = ObjectToXMLConverter.toXMLString(response);
        }
        catch(Exception be)
        {
            response = ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.XML_WRITE_ERROR, be);
            try
            {
                xml = ObjectToXMLConverter.toXMLString(response);
            }
            catch(Exception errorWriteException)
            {
                return ErrorResponseRegistry.deriveLastGaspResponse(ErrorResponseRegistry.INTERNAL_ERROR, be, errorWriteException);
            }
        }
        return xml;
    }

    public static Response handleRequestO(String xmlRequest)
    {
        Request request;
        try
        {
            request = (Request)XMLToObjectConverter.toObject(xmlRequest);
        }
        catch(SAXParseException spe)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.XML_PARSE_ERROR, toString(spe));
        }
        catch(IOException ioe)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.FILE_IO_ERROR, ioe);
        }
        catch(ClassCastException cce)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.REQUEST_CAST_ERROR, cce);
        }
        catch(XmlConversionException upe)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.PTYPE_NOT_FOUND, upe.getMessage());
        }
        catch(Throwable th)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.INTERNAL_ERROR, th);
        }
        return handleRequest(request);
    }

    public static Response handleRequestO(byte xmlRequestBytes[])
    {
        InputStream stream;
        try
        {
            stream = new ByteArrayInputStream(xmlRequestBytes);
        }
        catch(Throwable th)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.INTERNAL_ERROR, th);
        }
        return handleRequestO(stream);
    }

    public static Response handleRequestO(File file)
    {
        Request request;
        try
        {
            request = (Request)XMLToObjectConverter.toObject(file);
        }
        catch(SAXParseException spe)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.XML_PARSE_ERROR, toString(spe));
        }
        catch(IOException ioe)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.FILE_IO_ERROR, ioe);
        }
        catch(ClassCastException cce)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.REQUEST_CAST_ERROR, cce);
        }
        catch(XmlConversionException upe)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.PTYPE_NOT_FOUND, upe.getMessage());
        }
        catch(Throwable th)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.INTERNAL_ERROR, th);
        }
        return handleRequest(request);
    }

    public static Response handleRequestO(InputStream xmlRequestStream)
    {
        Request request;
        try
        {
            request = (Request)XMLToObjectConverter.toObject(xmlRequestStream);
        }
        catch(SAXParseException spe)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.XML_PARSE_ERROR, toString(spe));
        }
        catch(IOException ioe)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.FILE_IO_ERROR, ioe);
        }
        catch(XmlConversionException upe)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.PTYPE_NOT_FOUND, upe.getMessage());
        }
        catch(Throwable th)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.INTERNAL_ERROR, th);
        }
        return handleRequest(request);
    }

    public static Response handleRequest(Request requestObj)
    {
        if(ECHO)
            log.info("Request Object=" + ObjectPrettyPrinter.toString(requestObj));
        Response responseObj = MessageHandlerDispatcher.HandleSecurely(requestObj);
        ThreadLocalSystemAttributesRegistry.clearAttributes();
        break MISSING_BLOCK_LABEL_66;
        Throwable t;
        t;
        responseObj = ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.INTERNAL_ERROR, t);
        ThreadLocalSystemAttributesRegistry.clearAttributes();
        break MISSING_BLOCK_LABEL_66;
        Exception exception;
        exception;
        ThreadLocalSystemAttributesRegistry.clearAttributes();
        throw exception;
        if(ECHO)
            log.info("Response Object=" + ObjectPrettyPrinter.toString(responseObj));
        return responseObj;
    }

    public static final String toString(SAXParseException spe)
    {
        return " (line " + spe.getLineNumber() + ")" + spe.getMessage();
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit log;
    private static final boolean DBG;
    private static final boolean ECHO;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.comm.XmlRequestHandler.class);
        DBG = log.ison();
        ECHO = log.ison("messages");
    }
}
