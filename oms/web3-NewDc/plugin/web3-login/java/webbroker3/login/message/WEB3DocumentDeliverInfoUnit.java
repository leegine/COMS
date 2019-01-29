head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3DocumentDeliverInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 書面交付管理情報(WEB3DocumentDeliverInfoUnit.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/01/28 金シュ (中訊) 新規作成 モデル050
*/

package webbroker3.login.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (書面交付管理情報)<BR>
 * 書面交付管理情報<BR>
 * <BR>
 * @@author 金シュ
 * @@version 1.0
 */
public class WEB3DocumentDeliverInfoUnit extends Message
{

    /**
     * (書面区分)<BR>
     * 種別コード<BR>
     */
    public String documentDiv;

    /**
     * (書面種類)<BR>
     * 書面種類<BR>
     */
    public String documentCategory;

    /**
     * (電子鳩銘柄コード)<BR>
     * 書面区分が金商法@の場合<BR>
     * 　@書面種類+書面通番<BR>
     */
    public String batoProductCode;

    /**
     * (交付済フラグ)<BR>
     * 0：交付未済<BR>
     * 1：交付済<BR>
     * 2：交付不要<BR>
     */
    public String deliverFlag;

    /**
     * @@roseuid 4021A07F0167
     */
    public WEB3DocumentDeliverInfoUnit()
    {

    }
}
@
