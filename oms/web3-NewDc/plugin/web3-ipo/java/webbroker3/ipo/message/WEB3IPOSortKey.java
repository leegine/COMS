head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOソートキーメッセージデータ(WEB3IPOSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * IPOソートキーメッセージデータクラス
 *                                                                   
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IPOSortKey extends Message
{
    
    /**
     * キー項目
     */
    public String keyItem;
    
    /**
     * 昇順／降順<BR>
     * <BR>
     * Ａ： 昇順<BR>
     * Ｄ： 降順<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 4112E4E1037D
     */
    public WEB3IPOSortKey() 
    {
     
    }
}
@
