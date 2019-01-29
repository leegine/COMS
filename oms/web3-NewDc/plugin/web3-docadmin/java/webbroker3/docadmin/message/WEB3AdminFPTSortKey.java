head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@ソートキー(WEB3AdminFPTSortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 武波 (中訊) 新規作成
*/

package webbroker3.docadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (管理者金商法@ソートキー)<BR>
 * 管理者金商法@ソートキー<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTSortKey extends Message
{

    /**
     * (キー項目)<BR>
     * キー項目<BR>
     */
    public String keyItem;

    /**
     * (昇順降順)<BR>
     * A： 昇順<BR>
     * D： 降順<BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 46FDDD36033C
     */
    public WEB3AdminFPTSortKey()
    {

    }
}
@
