head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者カテゴリー登録完了レスポンス(WEB3AdminMutualCategoryRegistCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 黄建 (中訊) 新規作成 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (投信管理者カテゴリー登録完了レスポンス)<BR>
 * 投資信託管理者カテゴリー登録完了レスポンスクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualCategoryRegistCompleteResponse extends WEB3GenResponse 
{   
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_category_regist_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412031046L;
    
    /**
     * (投資信託管理者カテゴリー登録完了レスポンス)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 4153B68C019E
     */
    public WEB3AdminMutualCategoryRegistCompleteResponse()
    {
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3AdminMutualCategoryRegistCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
