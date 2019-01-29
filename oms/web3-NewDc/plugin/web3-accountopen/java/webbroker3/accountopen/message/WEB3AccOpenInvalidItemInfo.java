head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.03.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInvalidItemInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 不備項目情報(WEB3AccOpenInvalidItemInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 張学剛 新規作成
*/
package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (不備項目情報)<BR>
 * 不備項目情報<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AccOpenInvalidItemInfo extends Message 
{
    
    /**
     * (不備項目名)<BR>
     * 不備項目名<BR>
     */
    public String invalidItemName;
    
    /**
     * (コメント)<BR>
     * コメント<BR>
     */
    public String comment;
    
    /**
     * (完了フラグ)<BR>
     * 完了フラグ<BR>
     * <BR>
     * true：　@完了チェックあり<BR>
     * false：　@完了チェックなし<BR>
     */
    public boolean compFlag;
    
    /**
     * @@roseuid 41B45E7A033C
     */
    public WEB3AccOpenInvalidItemInfo() 
    {
     
    }
}
@
