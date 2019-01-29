head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteFeeder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteFeederクラス(WEB3QuoteFeeder.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/05/12 齋藤(FLJ) 新規作成（時価システムQUICKへの移行）
*/
package webbroker3.quoteadaptor.stdimpls;


/**
 * (時価Feeder)<BR>
 * <BR>
 * 時価Feeder<BR>
 *
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3QuoteFeeder
{
    
    /** ホスト */
    private String host;
    
    /** ポート */
    private int port;

    /**
     * @@return host を戻します。
     */
    public String getHost()
    {
        return host;
    }
    
    /**
     * @@param host host を設定。
     */
    public void setHost(String host)
    {
        this.host = host;
    }
    
    /**
     * @@return port を戻します。
     */
    public int getPort()
    {
        return port;
    }
    
    /**
     * @@param port port を設定。
     */
    public void setPort(int port)
    {
        this.port = port;
    }
    
    /**
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3QuoteFeeder[");
        l_sb.append("host=").append(host);
        l_sb.append(",port=").append(port);
        l_sb.append("]");
        return l_sb.toString();
    }
}
@
