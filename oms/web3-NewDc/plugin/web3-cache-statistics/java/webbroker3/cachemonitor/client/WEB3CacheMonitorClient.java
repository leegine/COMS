head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.49.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheMonitorClient.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.cachemonitor.client;

import com.fitechlabs.xtrade.kernel.comm.client.*;

public class WEB3CacheMonitorClient
{

    public static void main(String args[]) throws Exception
    {
        if (args.length == 1)
        {
            System.out.println(getStatus(args[0]));
        }
        System.exit(0);
    }

    private static String getStatus(String url) throws Exception
    {
        FailoverAccessor failoveraccessor = new FailoverAccessor(new String[]
            {
            url
        });
        String l_strWEB3CacheMonitorRequest = "<request p_type=\""
            + "cache_monitor"
            + "\">"
            + "</request>";
        String l_strResponse = failoveraccessor.doRequest(l_strWEB3CacheMonitorRequest);
        return l_strResponse;
    }

}
@
