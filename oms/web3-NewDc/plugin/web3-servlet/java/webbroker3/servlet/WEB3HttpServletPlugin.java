head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.28.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d956928;
filename	WEB3HttpServletPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ○○○○○クラス(WEB3HttpServletPlugin.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/09/24 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.servlet;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.log.Logit;

/**
 * クラスの説明<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3HttpServletPlugin extends Plugin
{

    private static final Logit log = Logit.getInstance(WEB3HttpServletPlugin.class);
    
    private static boolean isPlugged = false;

    public WEB3HttpServletPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3HttpServletPlugin.class);
    }

    public static void onPlug() throws Exception
    {
        KernelPlugin.plug();

        WEB3HttpServiceMappings mappings = new WEB3HttpServiceMappingsImpl();
        Services.registerService(WEB3HttpServiceMappings.class, mappings);
        
        synchronized (WEB3HttpServletPlugin.class)
        {
            isPlugged = true; 
        }

        log.info("WEB3HttpServletPlugin bootstrap succeeded.");

    }
    
    public static boolean isPlugged()
    {
        synchronized (WEB3HttpServletPlugin.class)
        {
            return isPlugged; 
        }
    }

}
@
