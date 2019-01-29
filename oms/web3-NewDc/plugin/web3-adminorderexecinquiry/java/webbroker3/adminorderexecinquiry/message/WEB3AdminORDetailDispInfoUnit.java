head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORDetailDispInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 詳細画面情報 (WEB3AdminORDetailDispInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (詳細画面情報)<BR>
 * <BR>
 * 詳細画面情報クラス<BR>
 * <BR>
 * 本クラスの情報をセッションで保持し、詳細画面に表示する。<BR>
 * <BR>
 * -----<English>---------------<BR>
 * <BR>
 * WEB3AdminORDetailDispInfoUnit class <BR>
 * <BR>
 * Save the information of this class in the session and display details <BR>
 * on the screen<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORDetailDispInfoUnit extends Message
{

    /**
     * (注文ID)<BR>
     * <BR>
     * 注文ID<BR>
     * <BR>
     * orderId<BR>
     * <BR>
     */
    public String orderId;

    /**
     * (商品区分)<BR>
     * <BR>
     * 商品区分<BR>
     * <BR>
     * 0：　@現物株式<BR>
     * 1：　@信用取引<BR>
     * 2：　@株式ミニ投資<BR>
     * 3：　@オプション<BR>
     * 4：　@先物<BR>
     * 5：　@投信<BR>
     * 6：　@累投<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * productDiv<BR>
     * 0: Def.EQUITY<BR>
     * 1: Def.MARGIN<BR>
     * 2: Def.MINI_STOCK<BR>
     * 3: Def.OPTION<BR>
     * 4: Def.FUTURE<BR>
     * 5: Def.MF<BR>
     * 6: Def.RUITO<BR>
     * <BR>
     */
    public String productDiv;

    /**
     * (口座ID)<BR>
     * <BR>
     * 口座ID<BR>
     */
    public String accountID;
    
    /**
     * @@roseuid 4212FD6101EC
     */
    public WEB3AdminORDetailDispInfoUnit()
    {

    }
}
@
