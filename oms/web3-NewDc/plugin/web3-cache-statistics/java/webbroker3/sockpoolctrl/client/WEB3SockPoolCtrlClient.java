head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3SockPoolCtrlClient.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.sockpoolctrl.client;

import com.fitechlabs.xtrade.kernel.comm.client.*;

public class WEB3SockPoolCtrlClient
{

    public static void main(String args[]) throws Exception
    {
        if (args.length == 1)
        {
            System.out.println(doCloseSocketPoolServer(args[0]));
        }
        System.exit(0);
    }

    private static String doCloseSocketPoolServer(String url) throws Exception
    {
        ServerAccessor l_accessor = null;
        if (url.startsWith("sockpool:"))
        {
            l_accessor = new SocketPoolAccessor(url);
        }
        else
        {
            l_accessor = new HttpServerAccessor(url);
        }
        String l_strWEB3SockPoolCtrlRequest = "<request p_type=\""
            + "sockpool_ctrl"
            + "\">"
            + "</request>";
        String l_strResponse = "Server InitialConnectException! ";
        try
        {
            l_strResponse = l_accessor.doRequest(l_strWEB3SockPoolCtrlRequest);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return l_strResponse;
    }

}
@
