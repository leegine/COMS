head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoStockLoanAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン口座開設情報(WEB3AccInfoStockLoanAccountInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 武波 (中訊) 新規作成 仕様変更・モデルNo.223
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (証券担保ローン口座開設情報)<BR>
 * 証券担保ローン口座開設情報クラス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AccInfoStockLoanAccountInfo extends Message
{

    /**
     * (開設状況)<BR>
     * 開設状況<BR>
     * <BR>
     * 0：　@未開設<BR>
     * 1：　@開設済<BR>
     */
    public String stockLoanAccOpenDiv;

    /**
     * (開設日)<BR>
     * 開設日<BR>
     */
    public Date stockLoanAccOpenDate;

    /**
     * @@roseuid 418F39F7034B
     */
    public WEB3AccInfoStockLoanAccountInfo()
    {

    }
}
@
