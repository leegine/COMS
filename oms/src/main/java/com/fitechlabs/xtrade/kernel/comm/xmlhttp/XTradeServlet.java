// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XTradeServlet.java

package com.fitechlabs.xtrade.kernel.comm.xmlhttp;

import com.fitechlabs.dbind.impl.InvServer;
import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.comm.XmlRequestHandler;
import com.fitechlabs.xtrade.kernel.comm.server.*;
import com.fitechlabs.xtrade.kernel.comm.sockpool.SocketPoolPlugin;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataSources;
import com.fitechlabs.xtrade.kernel.data.config.ServerConfig;
import com.fitechlabs.xtrade.kernel.license.*;
import com.fitechlabs.xtrade.kernel.message.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.kernel.util.xml.ObjectToXMLConverter;
import com.fitechlabs.xtrade.kernel.util.xml.XMLToObjectConverter;
import java.io.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Referenced classes of package com.fitechlabs.xtrade.kernel.comm.xmlhttp:
//            ServletBootstrap

public class XTradeServlet extends LWSServlet
{
    private static class MessageRegistryBinding extends XMLBinding
    {

        public void addRequest(Class requestClass, Handler handlerInstance)
        {
            throw new UnsupportedOperationException("MessageRegistryBinding cannot be extended");
        }

        public void addResponse(Class responseClass)
        {
            throw new UnsupportedOperationException("MessageRegistryBinding cannot be extended");
        }

        public Request toRequest(String xml)
            throws Exception
        {
            return (Request)XMLToObjectConverter.toObject(xml);
        }

        public Request toRequest(InputStream xml)
            throws Exception
        {
            return (Request)XMLToObjectConverter.toObject(xml);
        }

        public Response toResponse(String xml)
            throws Exception
        {
            return (Response)XMLToObjectConverter.toObject(xml);
        }

        public Response toResponse(InputStream xml)
            throws Exception
        {
            return (Response)XMLToObjectConverter.toObject(xml);
        }

        public String toXML(Response response)
            throws Exception
        {
            return ObjectToXMLConverter.toXMLString(response);
        }

        public String toXML(Request request)
            throws Exception
        {
            return ObjectToXMLConverter.toXMLString(request);
        }

        public Response handleRequest(Request request)
            throws Exception
        {
            return XmlRequestHandler.handleRequest(request);
        }

        public Class getClass(String contextTag, String tagName)
        {
            return MessageClassRegistry.getClass(contextTag, tagName);
        }

        private MessageRegistryBinding()
        {
        }

    }


    public XTradeServlet()
    {
        tracingOn = false;
    }

    public void init(ServletConfig config)
        throws ServletException
    {
        log.info("init() called with config=" + config);
        super.init(config);
        ServletBootstrap.boot(config);
        registryBinding = new MessageRegistryBinding();
        tracingOn = "true".equals(config.getInitParameter("MESSAGE_TRACING"));
        log.info(tracingOn ? "MESSAGE TRACING IS ON" : "Message tracing is off");
        log.info("init() complete");
    }

    public void destroy()
    {
        Plugin.unplugAll();
        try
        {
            log.info("stopping invalidation server");
            InvServer.stopAll();
            log.info("invalidation server was stopped");
        }
        catch(Exception ignore)
        {
            log.warn("invaliation server failed to stop (ignored)", ignore);
        }
    }

    protected XMLBinding getXMLBinding()
    {
        return registryBinding;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType("text/html; charset=Shift_JIS");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>XTrade Servlet Info</title></head><body>");
        out.println("<table width=\"100%\" cellpadding=\"5\">");
        out.println("<tr>");
        out.println("<td width=\"50%\" &nbsp; </td>");
        String contextPath = request.getContextPath();
        String jobsOverview = contextPath + "/jobs_overview_ja.jsp";
        String cacheStats = contextPath + "/cache_ja.jsp";
        String sqlStats = contextPath + "/sql_ja.jsp";
        String racMonitor = contextPath + "/rac_ja.jsp";
        out.println("<td>");
        Class plugins[] = Plugin.getInstalledPluginClasses();
        boolean jobsPluginInstalled = false;
        boolean racPluginInstalled = false;
        int i = 0;
        do
        {
            if(i >= plugins.length)
                break;
            String pName = plugins[i].getName();
            if("com.fitechlabs.xtrade.plugin.util.jobs.UtilJobsPlugin".equals(pName))
            {
                jobsPluginInstalled = true;
                break;
            }
            i++;
        } while(true);
        i = 0;
        do
        {
            if(i >= plugins.length)
                break;
            String pName = plugins[i].getName();
            if("com.fitechlabs.xtrade.plugin.util.rac.RacPlugin".equals(pName))
            {
                racPluginInstalled = true;
                break;
            }
            i++;
        } while(true);
        if(jobsPluginInstalled)
            out.println("<a href=\"" + jobsOverview + "\">\u30B8\u30E7\u30D6\u7BA1\u7406\u3078</a>");
        else
            out.println("&nbsp;");
        if(racPluginInstalled)
            out.println("<a href=\"" + racMonitor + "\">RAC\u76E3\u8996\u753B\u9762\u3078</a>");
        else
            out.println("&nbsp;");
        out.println("</td>");
        out.println("<td>");
        out.println("<a href=\"" + cacheStats + "\">\u30AD\u30E3\u30C3\u30B7\u30E5\u7D71\u8A08\u60C5\u5831\u3078</a>");
        out.println("</td>");
        out.println("<td>");
        out.println("<a href=\"" + sqlStats + "\">SQL\u7D71\u8A08\u60C5\u5831\u3078</a>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<h3>XTrade Servlet Info</h3>");
        out.println("<h4>System Properties</h4>");
        out.println("<table>");
        out.println("<tr><td>SDK_VERSION</td><td>" + KernelPlugin.getSDKVersion() + "</td></tr>");
        out.println("<tr><td>DEFAULT_DATA_SOURCE_JNDI_NAME</td><td>" + DataSources.getDefaultDataSourceJndiName() + "</td></tr>");
        out.println("<tr><td>DEFAULT_DRIVER</td><td>" + DataSources.getDefaultDriver() + "</td></tr>");
        out.println("<tr><td>DEFAULT_URL</td><td>" + DataSources.getDefaultUrl() + "</td></tr>");
        Properties props = DataSources.getDefaultProperties();
        String showProps = props != null ? props.toString() : "null";
        out.println("<tr><td>DEFAULT_PROPERTIES</td><td>" + showProps + "</td></tr>");
        try
        {
            out.println("<tr><td>CACHE_INVALIDATION_PORT</td><td>" + InvServer.getInstance().getPort() + "</td></tr>");
        }
        catch(SQLException e)
        {
            out.println("<tr><td>CACHE_INVALIDATION_PORT</td><td>Get Failure:" + e.getMessage() + "</td></tr>");
        }
        out.println("</table>");
        out.println("<h4>Installed Plugins</h4>");
        out.println("<table>");
        Class plugins[] = Plugin.getInstalledPluginClasses();
        String pnames[] = new String[plugins.length];
        for(int i = 0; i < plugins.length; i++)
            pnames[i] = plugins[i].getName();

        Arrays.sort(pnames);
        for(int i = 0; i < pnames.length; i++)
            out.println("<tr><td>" + pnames[i] + "</td></tr>");

        out.println("</table>");
        int socket_pool_port = SocketPoolPlugin.getPort();
        if(socket_pool_port != 0)
        {
            out.println("<h4>SocketPool</h4>");
            out.println("<table>");
            out.println("<tr><td>SOCKET_POOL_SERVER_PORT</td><td>" + socket_pool_port + "</td></tr>");
            out.println("</table>");
        }
        try
        {
            Map map = ServerConfig.getConfig();
            if(map != null)
            {
                out.println("<h4>Server Configuration</h4>");
                out.println("<table>");
                for(Iterator it = map.entrySet().iterator(); it.hasNext();)
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
                    String category = "<em>" + (String)entry.getKey() + "</em>";
                    Properties value = (Properties)entry.getValue();
                    Enumeration enum = value.propertyNames();
                    while(enum.hasMoreElements()) 
                    {
                        String s = (String)enum.nextElement();
                        out.println("<tr><td>" + category + "</td><td>" + s + "</td><td>" + value.getProperty(s) + "</td></tr>");
                        category = "&nbsp;";
                    }
                }

                out.println("</table>");
            } else
            {
                out.println("<h4>No Server Configuration Parameters</h4>");
            }
        }
        catch(DataNetworkException e)
        {
            out.println("<h4>Server Configuration Not Found</h4>");
        }
        LicenseService ls = Licensing.getLicenseServiceInstance();
        Collection c = ls.getInstalledLicenses();
        if(c != null && c.size() > 0)
        {
            License larray[] = (License[])c.toArray(new License[c.size()]);
            out.println("<h4>Installed Licenses</h4>");
            out.println("<table>");
            out.println("  <tr>");
            out.println("    <td valign='top' align='left'  >&nbsp;Licensee&nbsp;</td>");
            out.println("    <td valign='top' align='left'  >&nbsp;Component&nbsp;</td>");
            out.println("    <td valign='top' align='center'>&nbsp;Issued&nbsp;</td>");
            out.println("    <td valign='top' align='center'>&nbsp;Expires&nbsp;</td>");
            out.println("    <td valign='top' align 'right' >&nbsp;Attribute&nbsp;</td>");
            out.println("    <td valign='top' align 'left'  >&nbsp;Value&nbsp;</td>");
            out.println("  </tr>");
            for(int i = 0; i < larray.length; i++)
            {
                Properties a = larray[i].getAttributes();
                boolean hasAttributes = a != null && a.size() > 0;
                String rowspan = hasAttributes ? "rowspan='" + a.size() + "'" : "";
                License lic = larray[i];
                out.println("  <tr>");
                out.println("    <td " + rowspan + " valign='top' align='left'  >&nbsp;" + lic.getLicensee() + "&nbsp;</td>");
                out.println("    <td " + rowspan + " valign='top' align='left'  >&nbsp;" + lic.getComponent() + "&nbsp;</td>");
                out.println("    <td " + rowspan + " valign='top' align='center'>&nbsp;" + lic.getIssueDate() + "&nbsp;</td>");
                out.println("    <td " + rowspan + " valign='top' align='center'>&nbsp;" + lic.getExpirationDate() + "&nbsp;</td>");
                if(hasAttributes)
                {
                    Iterator it = a.entrySet().iterator();
                    do
                    {
                        if(!it.hasNext())
                            break;
                        java.util.Map.Entry me = (java.util.Map.Entry)it.next();
                        String name = (String)me.getKey();
                        String valu = (String)me.getValue();
                        out.println("    <td valign='top' align='right'>&nbsp;" + name + "&nbsp;</td>");
                        out.println("    <td valign='top' align='left' >&nbsp;" + valu + "&nbsp;</td>");
                        if(it.hasNext())
                            out.println("  </tr><tr>");
                    } while(true);
                } else
                {
                    out.println("    <td colspan='2'>&nbsp;</td>");
                }
                out.println("  </tr>");
            }

            out.println("</table>");
        } else
        {
            out.println("<h4>No Licenses Installed</h4>");
        }
        out.println("</body></html>");
    }

    protected Response handleRequest(Request requestObj)
    {
        Response responseObj = super.handleRequest(requestObj);
        if(tracingOn)
        {
            int serial = nextSerial();
            try
            {
                trace(requestObj, serial, "req");
                trace(responseObj, serial, "resp");
            }
            catch(Exception e)
            {
                log.info("Trace exception: " + e);
            }
        }
        return responseObj;
    }

    private static synchronized short nextSerial()
    {
        return ++traceSerial;
    }

    private void trace(Message message, int serial, String type)
        throws Exception
    {
        if(traceDate == null)
            traceDate = (new SimpleDateFormat("MMdd_HHmm")).format(new Date());
        log.warn("PERFORMANCE WARNING: MESSAGE_TRACING=true");
        String num = String.valueOf(serial + 9000).substring(1);
        String filename = "../traces/trace." + traceDate + "." + num + "." + type + ".xml";
        (new File("../traces")).mkdirs();
        File outfile = new File(filename);
        log.info("RECORDING TRACE MESSAGE TO FILE '" + outfile.getAbsolutePath() + "'");
        Writer out = new OutputStreamWriter(new FileOutputStream(outfile), "UTF8");
        out.write("<?xml version='1.0' encoding='UTF-8'?>\n");
        out.write(ObjectToXMLConverter.toXMLString(message));
        out.close();
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
    private MessageRegistryBinding registryBinding;
    private boolean tracingOn;
    private static String traceDate = null;
    private static short traceSerial = 0;
    private static final String TRACE_DIR = "../traces";

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.comm.xmlhttp.XTradeServlet.class);
        DBG = log.ison();
    }
}
