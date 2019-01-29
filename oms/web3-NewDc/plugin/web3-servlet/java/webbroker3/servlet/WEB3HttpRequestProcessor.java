head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.28.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d956928;
filename	WEB3HttpRequestProcessor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : HttpServletRequestを処理するクラス(WEB3HttpRequestProcessor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/09/29 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitechlabs.xtrade.kernel.util.log.Logit;

/**
 * HttpServletRequestを処理するクラス<BR>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3HttpRequestProcessor
{

    /**
     * ログユーティリティ
     */
    private static final Logit log =
        Logit.getInstance(WEB3HttpRequestProcessor.class);

    /**
     * WEB3HttpServiceMappings
     */
    private WEB3HttpServiceMappings serviceMappings;

    /**
     * コンストラクタ
     */
    public WEB3HttpRequestProcessor(WEB3HttpServiceMappings l_serviceMappings)
    {
        serviceMappings = l_serviceMappings;
    }

    /**
     * 指定されたHttpRequestを処理するWEB3HttpServiceに処理をディスパッチする。
     * 
     * @@param request
     * @@param response
     * @@throws IOException
     * @@throws ServletException
     */
    public void process(
        HttpServletRequest l_req,
        HttpServletResponse l_res)
        throws IOException, ServletException
    {

        String l_serviceName = getServiceName(l_req);
        WEB3HttpService l_service = getService(l_serviceName);
        if (l_service == null)
        {
            return;
        }
        l_service.execute(l_req, l_res);
    }

    /**
     * 指定されたサービス名にマッピングされたサービスを取得する。
     * サービス名に<code>null</code>が指定された場合や、
     * サービスが登録されていない場合は、デフォルトで使用するサービスを返す。
     * 
     * @@param l_serviceName サービス名
     * @@return WEB3HttpService
     */
    protected WEB3HttpService getService(String l_serviceName)
    {
        WEB3HttpService l_service = null;
        if (l_serviceName != null)
        {
            l_service = getMappings().getService(l_serviceName);
            if (l_service == null)
            {
                log.debug("Required Service not found, use default service.");
                l_service = getMappings().getDefaultService();
            }
        } else {
            log.debug("ServiceName is null, use default service.");
            l_service = getMappings().getDefaultService();
        }
        return l_service;
    }

    /**
     * HttpSerlvetRequestから使用するサービス名を取得する。<br>
     * サービス名が設定されていない場合は、<code>null</code>を返す。
     * 
     * @@param l_req HttpServletRequest
     * @@return サービス名
     */
    protected String getServiceName(HttpServletRequest l_req)
    {
        String l_strServiceName = null;
        String l_strPath = l_req.getPathInfo();
        if (l_strPath != null && l_strPath.length() > 1)
        {
            if (l_strPath.startsWith("/"))
            {
                l_strServiceName = l_strPath.substring(1);
            }
        }
        return l_strServiceName;
    }

    /**
     * WEB3HttpServiceMappingsを取得する。
     * 
     * @@return WEB3HttpServiceMappings
     */
    protected WEB3HttpServiceMappings getMappings()
    {
        return serviceMappings;
    }

}
@
