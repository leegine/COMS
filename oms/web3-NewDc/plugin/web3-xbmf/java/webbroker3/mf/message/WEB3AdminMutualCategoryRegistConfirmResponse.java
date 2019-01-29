head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者カテゴリー登録確認レスポンス(WEB3AdminMutualCategoryRegistConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 黄建 (中訊) 新規作成 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (投信管理者カテゴリー登録確認レスポンス)<BR>
 * 投資信託管理者カテゴリー登録確認レスポンスクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualCategoryRegistConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_category_regist_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412030955L;
    
    /**
     * (投信銘柄カテゴリー一覧)<BR>
     *  投信銘柄カテゴリー一覧<BR>
     *  nullの場合はカテゴリ指定無しとする
     */
    public WEB3MutualProductCategoryUnit[] categoryList;
    
    /**
     * (投資信託管理者カテゴリー登録確認レスポンス)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 4153B67901DC
     */
    public WEB3AdminMutualCategoryRegistConfirmResponse()
    {
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminMutualCategoryRegistConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
