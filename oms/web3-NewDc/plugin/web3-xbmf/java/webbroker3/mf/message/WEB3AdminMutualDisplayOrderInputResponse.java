head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualDisplayOrderInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者銘柄表示順序登録入力画面レスポンス(WEB3AdminMutualDisplayOrderInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 黄建 (中訊) 新規作成 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (投信管理者銘柄表示順序登録入力画面レスポンス)<BR>
 * 投資信託管理者銘柄表示順序登録入力画面レスポンスクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualDisplayOrderInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_display_order_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412031008L;
    
    /**
     * (銘柄表示順序登録一覧)<BR>
     * 投信管理者銘柄表示順序登録一覧行オブジェクトの配列
     */
    public WEB3MutualDisplayOrderGroup[] displayOrderGroups;
    
    /**
     * (投信管理者銘柄表示順序登録入力画面レスポンス)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 4153BC2700B4
     */
    public WEB3AdminMutualDisplayOrderInputResponse()
    {
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminMutualDisplayOrderInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
