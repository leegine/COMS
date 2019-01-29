head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoIpoProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO当選銘柄情報(WEB3PvInfoIpoProductUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
*/
package webbroker3.pvinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (IPO当選銘柄情報)<BR>
 * IPO当選銘柄情報クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoIpoProductUnit extends Message 
{
    
    /**
     * (当選銘柄名)<BR>
     * 当選銘柄名<BR>
     */
    public String lotWinProductName;
    
    /**
     * (当選株数)<BR>
     * 当選株数<BR>
     */
    public String lotWinCount;
    
    /**
     * (IPO当選銘柄情報)<BR>
     * コンストラクタ。<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoIpoProductUnit
     * @@roseuid 4145615101A4
     */
    public WEB3PvInfoIpoProductUnit() 
    {
     
    }
}
@
