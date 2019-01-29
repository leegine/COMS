head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.28.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d956928;
filename	WEB3HttpServiceMappingsImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3HttpServiceMappingsの実装クラス(WEB3HttpServiceMappingsImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/09/24 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.servlet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.log.Logit;

/**
 * WEB3HttpServiceMappingsの実装クラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3HttpServiceMappingsImpl implements WEB3HttpServiceMappings
{

    /**
     * ログユーティリティ
     */
    private static final Logit log =
        Logit.getInstance(WEB3HttpServiceMappingsImpl.class);

    /**
     * デフォルトで使用するWEB3HttpServiceのサービス名
     */
    private static final String DEFAULT_SERVICE_NAME = "DEFAULT_SERVICE_NAME";

    /**
     * サービス名とサービスのマッピングを保持するハッシュマップ
     */
    private Map map;

    /**
     * コンストラクタ
     */
    public WEB3HttpServiceMappingsImpl()
    {
        map = Collections.synchronizedMap(new HashMap());
    }

    /**
     * @@see webbroker3.servlet.WEB3HttpServiceMappings#addService(java.lang.String, java.lang.Class)
     */
    public void addService(String l_serviceName, Class l_serviceInterface)
    {

        if (l_serviceName == null || l_serviceName.length() == 0)
        {
            throw new IllegalArgumentException("serviceName required.");
        }

        if (l_serviceInterface == null)
        {
            throw new IllegalArgumentException("serviceInterface required.");
        }

        map.put(l_serviceName, l_serviceInterface);

        if (log.ison())
        {
            log.debug(
                "New entry registered. ServiceName="
                    + l_serviceName
                    + ", ServiceInterface="
                    + l_serviceInterface.getName());
        }
    }

    /**
     * @@see webbroker3.servlet.WEB3HttpServiceMappings#getService(java.lang.String)
     */
    public WEB3HttpService getService(String l_serviceName)
    {
        if (l_serviceName == null || l_serviceName.length() == 0)
        {
            throw new IllegalArgumentException("serviceName required.");
        }
        Class l_serviceInterface = (Class) map.get(l_serviceName);
        if (l_serviceInterface == null)
        {
            if (log.ison())
            {
                log.debug("No entry found. serviceName=" + l_serviceName);
            }
            return null;
        }
        return (WEB3HttpService) Services.getService(l_serviceInterface);
    }

    /**
     * @@see webbroker3.servlet.WEB3HttpServiceMappings#setDefaultService(java.lang.Class)
     */
    public void setDefaultService(Class serviceInterface)
    {
        addService(DEFAULT_SERVICE_NAME, serviceInterface);
    }

    /**
     * @@see webbroker3.servlet.WEB3HttpServiceMappings#getDefaultService()
     */
    public WEB3HttpService getDefaultService()
    {
        return getService(DEFAULT_SERVICE_NAME);
    }

    /**
     * @@see webbroker3.servlet.WEB3HttpServiceMappings#removeService(java.lang.String)
     */
    public void removeService(String l_serviceName)
    {
        if (l_serviceName == null || l_serviceName.length() == 0)
        {
            return;
        }
        map.remove(l_serviceName);
    }

}
@
