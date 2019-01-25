// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MsgHttpServlet.java

package com.fitechlabs.xtrade.kernel.comm.xmlhttp;

import com.fitechlabs.xtrade.kernel.comm.XmlRequestHandler;
import com.fitechlabs.xtrade.kernel.error.ErrorResponseRegistry;
import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.util.ObjectPrettyPrinter;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.xmlhttp:
//            ServletBootstrap

/**
 * @deprecated Class MsgHttpServlet is deprecated
 */

public class MsgHttpServlet extends HttpServlet
{

    public MsgHttpServlet()
    {
    }

    public void init(ServletConfig config)
        throws ServletException
    {
        log.info("init() called with config=" + config);
        super.init(config);
        ServletBootstrap.boot(config);
        log.info("init() complete");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType("text/html; charset=Shift_JIS");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Servlet1</title></head><body>");
        out.println("<p>XTrade Message Servlet Running.</p></body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        if(DBG)
            log.info("doPost() ==== called");
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        try
        {
            input = new ObjectInputStream(request.getInputStream());
            output = new ObjectOutputStream(response.getOutputStream());
            output.flush();
            Object reply = readAndReply(input);
            if(DBG)
                log.info("doPost() response object is " + ObjectPrettyPrinter.toString(reply));
            output.writeObject(reply);
        }
        catch(IOException ioe)
        {
            if(DBG)
                log.info("doPost() ignoring " + ioe);
            throw new ServletException("doPost() caught (and ignored) " + ioe);
        }
    }

    private Object readAndReply(ObjectInputStream input)
    {
        Object object;
        try
        {
            object = input.readObject();
            if(DBG)
                log.info("readAndReply() request object is " + object);
        }
        catch(Exception e)
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.OBJECT_READ_ERROR, e);
        }
        if(object instanceof Request)
        {
            com.fitechlabs.xtrade.kernel.message.Response response = XmlRequestHandler.handleRequest((Request)object);
            if(DBG)
                log.info("readAndReply() response to Request is " + response);
            return response;
        }
        if(object instanceof String)
        {
            com.fitechlabs.xtrade.kernel.message.Response response = XmlRequestHandler.handleRequestO((String)object);
            if(DBG)
                log.info("readAndReply() response to String is " + response);
            return response;
        }
        if(object instanceof byte[])
        {
            com.fitechlabs.xtrade.kernel.message.Response response = XmlRequestHandler.handleRequestO((byte[])object);
            if(DBG)
                log.info("readAndReply() response to byte array is " + response);
            return response;
        } else
        {
            return ErrorResponseRegistry.deriveResponse(ErrorResponseRegistry.REQUEST_CAST_ERROR, object != null ? "Received object class was " + object.getClass() : "Received Object was null");
        }
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static Logit log;
    private static final boolean DBG;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.comm.xmlhttp.MsgHttpServlet.class);
        DBG = log.ison();
    }
}
