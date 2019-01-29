head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.28.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d956928;
filename	WEB3HttpServlet.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ○○○○○クラス(WEB3HttpServlet.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/09/24 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.log.Logit;

/**
 * クラスの説明<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3HttpServlet extends HttpServlet
{

    private static final Logit log = Logit.getInstance(WEB3HttpServlet.class);

    private WEB3HttpRequestProcessor processor;

    /**
     * @@see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
     */
    public void init(ServletConfig l_config) throws ServletException
    {
        log.info("Initializing WEB3HttpServlet ...");

        super.init(l_config);

        WEB3HttpServiceMappings l_serviceMappings =
            (WEB3HttpServiceMappings) Services.getService(
                WEB3HttpServiceMappings.class);
        processor = new WEB3HttpRequestProcessor(l_serviceMappings);

        log.info("WEB3HttpServlet initialized.");
    }

    /**
     * @@see javax.servlet.Servlet#destroy()
     */
    public void destroy()
    {
        super.destroy();
    }

    /**
     * @@see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void service(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException
    {
        process(request, response);
    }

    private void process(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException
    {
        getProcessor().process(request, response);
    }

    protected WEB3HttpRequestProcessor getProcessor()
    {
        return processor;
    }

}
@
