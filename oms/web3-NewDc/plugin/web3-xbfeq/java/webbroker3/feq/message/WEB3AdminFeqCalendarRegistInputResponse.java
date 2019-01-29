head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.29.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCalendarRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式カレンダー登録入力レスポンス(WEB3AdminFeqCalendarRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/26 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式カレンダー登録入力レスポンス)<BR>
 * 管理者外国株式カレンダー登録入力レスポンスクラス
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqCalendarRegistInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_calendarRegistInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L; 
    
    /**
     * (カレンダー情報一覧)<BR>
     * 現地カレンダー情報の配列
     */
    public WEB3AdminFeqLocalCalendarUnit[] feqLocalCalendarUnit;
    
    /**
     * @@roseuid 42CE3A000109
     */
    public WEB3AdminFeqCalendarRegistInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqCalendarRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
