// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LWSServlet.java

package com.fitechlabs.xtrade.kernel.comm.server;

import com.fitechlabs.xtrade.kernel.error.ErrorResponseRegistry;
import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.util.xml.ObjectToXMLConverter;
import com.fitechlabs.xtrade.kernel.util.xml.XmlConversionException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.server:
//            XMLBinding

public abstract class LWSServlet extends HttpServlet
{

    public LWSServlet()
    {
    }

    protected abstract XMLBinding getXMLBinding();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType("text/html; charset=Shift_JIS");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>LWS Servlet</title></head><body>");
        out.println("<p>(Modified) LWS Servlet Running.</p></body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String contentType = request.getContentType();
        if("text/xml".equalsIgnoreCase(contentType))
            doXmlHttpRequest(request, response);
        else
            doMessageHttpRequest(request, response);
    }

    protected void doXmlHttpRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        InputStream input = null;
        OutputStream output = null;
        String xmlResponse = null;
        try
        {
            try
            {
                input = request.getInputStream();
                Response responseObj = handleRequest(input);
                xmlResponse = responseToXMLString(responseObj);
            }
            catch(IOException ioe)
            {
                xmlResponse = ErrorResponseRegistry.deriveLastGaspResponse(ErrorResponseRegistry.OBJECT_READ_ERROR, ioe, null);
            }
            response.setContentType("text/xml");
            output = response.getOutputStream();
            byte bytes[] = xmlResponse.getBytes("UTF8");
            output.write(bytes);
            output.flush();
        }
        finally
        {
            if(input != null)
                try
                {
                    input.close();
                }
                catch(Exception ignore) { }
            if(output != null)
                try
                {
                    output.close();
                }
                catch(Exception ignore) { }
        }
    }

    private String responseToXMLString(Response response)
    {
        String xml;
        try
        {
            xml = getXMLBinding().toXML(response);
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

    protected void doMessageHttpRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        ObjectInputStream input = new ObjectInputStream(request.getInputStream());
        ObjectOutputStream output = new ObjectOutputStream(response.getOutputStream());
        output.flush();
        Object reply = readObjectAndReply(input);
        output.writeObject(reply);
    }

    private Object readObjectAndReply(ObjectInputStream input)
    {
        Object object;
        try
        {
            object = input.readObject();
        }
        catch(Exception e)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.OBJECT_READ_ERROR, e);
        }
        if(object instanceof Request)
        {
            Response response = handleRequest((Request)object);
            return response;
        }
        if(object instanceof String)
        {
            Response response = handleRequest((String)object);
            return response;
        }
        if(object instanceof byte[])
        {
            Response response = handleRequest(new ByteArrayInputStream((byte[])object));
            return response;
        } else
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.REQUEST_CAST_ERROR, object != null ? "Unusable object class: " + object.getClass() : "No object found");
        }
    }

    private Response handleRequest(InputStream xmlRequestStream)
    {
        Request requestObj;
        try
        {
            requestObj = getXMLBinding().toRequest(xmlRequestStream);
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
        return handleRequest(requestObj);
    }

    private Response handleRequest(String xmlRequest)
    {
        Request request;
        try
        {
            request = getXMLBinding().toRequest(xmlRequest);
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

    protected Response handleRequest(Request requestObj)
    {
        Response responseObj;
        try
        {
            responseObj = getXMLBinding().handleRequest(requestObj);
        }
        catch(Throwable t)
        {
            responseObj = ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.INTERNAL_ERROR, t);
        }
        return responseObj;
    }

    public static final String toString(SAXParseException spe)
    {
        return " (line " + spe.getLineNumber() + ")" + spe.getMessage();
    }
}
