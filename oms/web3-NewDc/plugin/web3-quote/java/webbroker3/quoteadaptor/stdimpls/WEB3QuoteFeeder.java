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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteFeeder�N���X(WEB3QuoteFeeder.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/05/12 �V��(FLJ) �V�K�쐬�i�����V�X�e��QUICK�ւ̈ڍs�j
*/
package webbroker3.quoteadaptor.stdimpls;


/**
 * (����Feeder)<BR>
 * <BR>
 * ����Feeder<BR>
 *
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3QuoteFeeder
{
    
    /** �z�X�g */
    private String host;
    
    /** �|�[�g */
    private int port;

    /**
     * @@return host ��߂��܂��B
     */
    public String getHost()
    {
        return host;
    }
    
    /**
     * @@param host host ��ݒ�B
     */
    public void setHost(String host)
    {
        this.host = host;
    }
    
    /**
     * @@return port ��߂��܂��B
     */
    public int getPort()
    {
        return port;
    }
    
    /**
     * @@param port port ��ݒ�B
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
