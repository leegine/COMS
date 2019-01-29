head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBizDateUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資海外市場カレンダー登録営業日(WEB3MutualBizDateUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/15 王蘭芬(中訊) 新規作成
                   2004/08/25 周勇 (中訊) レビュー 
*/

package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * 投資海外市場カレンダー登録営業日　@データクラス
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3MutualBizDateUnit extends Message 
{
    
    /**
     * 営業日の日付
     */
    public Date bizDate;
    
    /**
     * (営業日区分)<BR>
     * 0:営業日　@1:非営業日<BR>
     */
    public String bizDateType;
    
    /**
     * (投信海外市場カレンダー登録営業日)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40C008870307
     */
    public WEB3MutualBizDateUnit() 
    {
     
    }
}
@
