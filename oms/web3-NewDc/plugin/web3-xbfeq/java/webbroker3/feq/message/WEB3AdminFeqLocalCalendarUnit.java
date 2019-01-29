head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.25.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqLocalCalendarUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現地カレンダー情報(WEB3AdminFeqLocalCalendarUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (現地カレンダー情報)<BR>
 * 現地カレンダー情報クラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqLocalCalendarUnit extends Message 
{
    
    /**
     * (日付)<BR>
     * 日付
     */
    public Date bizDate;
    
    /**
     * (日付区分)<BR>
     * 日付区分<BR>
     * <BR>
     * 0：非営業日<BR>
     * 1：営業日<BR>
     * <BR>
     * ※新規登録、未選択の場合は、null<BR>
     * ※リクエストでセットされる値は、変更後の値。
     */
    public String bizDateDiv;
    
    /**
     * (更新日)<BR>
     * 更新日<BR>
     * <BR>
     * ※新規登録の場合は、null
     */
    public Date updateDate;
    
    /**
     * (現地カレンダー情報)<BR>
     * コンストラクタ
     * @@roseuid 4200C1A30133
     */
    public WEB3AdminFeqLocalCalendarUnit() 
    {
     
    }
}
@
