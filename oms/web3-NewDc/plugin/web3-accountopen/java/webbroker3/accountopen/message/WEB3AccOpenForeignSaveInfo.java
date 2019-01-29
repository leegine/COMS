head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.07.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenForeignSaveInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨預金口座情報(WEB3AccOpenForeignSaveInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/11 柴雙紅(中訊) 新規作成
*/

package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (外貨預金口座情報)<BR>
 * 外貨預金口座情報<BR>
 *   
 * @@author 柴雙紅
 * @@version 1.0
 */
public class WEB3AccOpenForeignSaveInfo extends Message
{
    /**
     * (口座番号)<BR>
     * 口座番号<BR>
     */
    public String financialAccountCode;
    
    /**
     * (口座名義人)<BR>
     * 口座名義人<BR>
     */
    public String financialAccountName;
    
    /**
     * (口座名義人英数)<BR>
     * 口座名義人英数<BR>
     */
    public String financialAccountNameAlpha;
    
    /**
     * (預金区分)<BR>
     * 預金区分<BR>
     */
    public String financialAccountDiv;

    public WEB3AccOpenForeignSaveInfo()
    {
        
    }
}
@
