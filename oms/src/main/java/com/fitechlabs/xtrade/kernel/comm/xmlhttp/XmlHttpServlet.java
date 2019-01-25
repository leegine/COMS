// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlHttpServlet.java

package com.fitechlabs.xtrade.kernel.comm.xmlhttp;

import com.fitechlabs.xtrade.kernel.comm.XmlRequestHandler;
import com.fitechlabs.xtrade.kernel.error.ErrorResponseRegistry;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * @deprecated Class XmlHttpServlet is deprecated
 */

public class XmlHttpServlet extends HttpServlet
{

    public XmlHttpServlet()
    {
    }

    public void init(ServletConfig config)
        throws ServletException
    {
        if(DBG)
            log.debug("init() called");
        super.init(config);
        if(DBG)
            log.debug("init() complete");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType("text/html; charset=Shift_JIS");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Servlet1</title></head><body>");
        out.println("<p>XTrade XML Servlet Running.</p></body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        if(DBG)
            log.debug("doPost() called");
        java.io.InputStream input = null;
        OutputStream output = null;
        try
        {
            String xmlResponse = null;
            try
            {
                input = request.getInputStream();
                xmlResponse = XmlRequestHandler.handleRequest(input);
            }
            catch(IOException ioe)
            {
                xmlResponse = ErrorResponseRegistry.deriveLastGaspResponse(ErrorResponseRegistry.OBJECT_READ_ERROR, ioe, null);
            }
            if(DBG)
                log.debug("doPost() xmlResponse=" + xmlResponse);
            byte responseBytes[] = xmlResponse.getBytes("UTF8");
            response.setContentType("text/xml");
            output = response.getOutputStream();
            output.write(responseBytes);
            output.close();
            output = null;
            if(DBG)
                log.debug("doPost() delivered the response.");
        }
        catch(Throwable t)
        {
            if(DBG)
                log.debug("doPost() ignoring throwable ", t);
        }
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

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.comm.xmlhttp.XmlHttpServlet.class);
        DBG = log.ison();
    }
}
