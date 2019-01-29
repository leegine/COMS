head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.49.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報検索ソートキークラス(WEB3InformSortKey.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/21 凌建平(中訊) 作成
*/

package webbroker3.inform.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (連絡情報検索ソートキー)<BR>
 * 連絡情報検索ソートキークラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3InformSortKey extends Message
{
    
    /**
     * (キー項目)<BR>
     * キー項目<BR>
     * <BR>
     * ・識別コード<BR>
     * ・部店コード<BR>
     * ・顧客コード<BR>
     * ・受付日時<BR>
     */
    public String keyItem;
    
    /**
     * (昇順/降順)<BR>
     * A： 昇順<BR>
     * D： 降順<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 41EE625E0399
     */
    public WEB3InformSortKey() 
    {
     
    }
}
@
