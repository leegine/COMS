head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.08.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistChangeResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者カテゴリー変更入力画面レスポンス(WEB3AdminMutualCategoryRegistChangeResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 黄建 (中訊) 新規作成 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (投信管理者カテゴリー変更入力画面レスポンス)<BR>
 * 投資信託管理者カテゴリー変更入力画面レスポンスクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualCategoryRegistChangeResponse extends WEB3GenResponse 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_category_regist_change";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412030930L;
    
    /**
     * (投信銘柄カテゴリーコード)<BR>
     *  投信銘柄カテゴリー名称に対応した投信銘柄カテゴリーコード
     */
    public String categoryCode;
    
    /**
     * (投信銘柄カテゴリー名称)<BR>
     *  投信銘柄カテゴリーコードに対応した投信銘柄カテゴリー名称
     */
    public String categoryName;
    
    /**
     * (親カテゴリーコード)<BR>
     *  親カテゴリーコード<BR>
     *  null の場合は、親なし…つまりルートのカテゴリ
     */
    public String parentCategoryCode;
    
    /**
     * (投資信託管理者カテゴリー変更入力画面レスポンス)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 4153B66A02A8
     */
    public WEB3AdminMutualCategoryRegistChangeResponse()
    {
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminMutualCategoryRegistChangeResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
