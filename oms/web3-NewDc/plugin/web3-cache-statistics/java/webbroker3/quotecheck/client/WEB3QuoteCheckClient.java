head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuoteCheckClient.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 時価情報定点チェックサービスクライアント(WEB3QuoteCheckClient.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2009/02/03 許 (FLJ)新規作成
 */
package webbroker3.quotecheck.client;

import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.SocketPoolAccessor;

/**
 * （時価情報定点チェックサービスクライアント）。
 * @@version 1.0
 */
public class WEB3QuoteCheckClient
{
    /**
     * スレッドチェック時間＝100ミリ秒
     */
    final static int SLEEP_TIME = 100;
       
    public static void main(String args[]) throws Exception
    {
        if (args.length == 1 )
        {
            //Paramsを取得
            String l_strUrls = args[0];
            String[] l_urls = l_strUrls.split(";");
            
            //スレッドを作成して、スタート
            WEB3QuoteCheckClientThread[] l_ths = new WEB3QuoteCheckClientThread[l_urls.length];
            for (int i = 0; i < l_ths.length; i++)
            {
                //Accessorを作成
                ServerAccessor accessor = new SocketPoolAccessor(l_urls[i]);
                
                l_ths[i] = new WEB3QuoteCheckClientThread(accessor);
                Thread l_th = new Thread(l_ths[i]);
                l_th.start();
            }
            
            //すべてのスレッド終了したら、exit
            while(true)
            {
                boolean l_isAllStopped = true;
            
                for (int i = 0; i < l_ths.length; i++)
                {
                    if(!l_ths[i].isStopped())
                    {
                        l_isAllStopped = l_ths[i].isStopped();
                    }
                }
                
                if(l_isAllStopped)
                {
                    break;
                }
                
                try
                {
                    Thread.sleep(SLEEP_TIME);
                }
                catch(InterruptedException e)
                {
                    ;
                }
            }

                
        }
        else
        {
            System.out.println("ERROR: Usage: java WEB3QuoteCheckClient $urls");
        }
        
        System.exit(0);
    }

}
@
