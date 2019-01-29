head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.52.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceClient.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 時価終値保存サービスクライアント(WEB3QuotePriceClient.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/11/16 劉(FLJ) 新規作成
                    2007/05/31 孫(FLJ) 先物OP時価保存機@能を追加により、quote_table関数を追加する
 */
package webbroker3.quoteprice.client;

import com.fitechlabs.xtrade.kernel.comm.client.*;

/**
 * （時価終値保存サービスクライアント）。
 * @@version 1.0
 */
public class WEB3QuotePriceClient
{
    /**
     * タイムアウト＝10分
     */
    final static int DEFAULT_TIMEOUT = 10 * 60 * 1000;

    public static void main(String args[]) throws Exception
    {
        if (args.length >= 1 )
        {
            String urls[] = new String[args.length];
            String l_strUrls = args[0];
            urls = l_strUrls.split(";");
            
            String l_quoteTable = null;
            if(args.length >= 2)
            {
                l_quoteTable = args[1];
            }
            
            System.out.println(getStatus(urls,l_quoteTable));
        }
        System.exit(0);
    }

    private static String getStatus(String urls[],String l_quoteTable) throws Exception
    {

        ServerAccessor[] accessors = new ServerAccessor[urls.length];
        for (int i = 0; i < urls.length; i++)
        {

            if (urls[i].startsWith("sockpool:"))
            {
                accessors[i] = new SocketPoolAccessor(urls[i]);
                SocketPoolAccessor l_sa = (SocketPoolAccessor) accessors[i];
                l_sa.setTimeOut(DEFAULT_TIMEOUT);
            }
            else if (urls[i].startsWith("http:"))
            {
                accessors[i] = new HttpServerAccessor(urls[i]);
            }
            else
            {
                continue;
            }

        }
        FailoverAccessor failoveraccessor = new FailoverAccessor(accessors);
        String l_strWEB3QuotePriceRequest = "<request p_type=\""
            + "quote_price_save"
            + "\">"
            + "<quote_table>"
            + l_quoteTable
            + "</quote_table>"
            + "</request>";
        String l_strResponse = failoveraccessor.doRequest(l_strWEB3QuotePriceRequest);
        return l_strResponse;
    }
}
@
