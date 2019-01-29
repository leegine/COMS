head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductRegiListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録一覧レスポンスクラス(WEB3AdminSLProductRegiListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 張騰宇 (中訊) 新規作成 モデル 760
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (担保銘柄登録一覧レスポンス)<BR>
 * 担保銘柄登録一覧レスポンスクラス<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminSLProductRegiListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_sl_product_regi_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709141545L;

    /**
     * (銘柄登録情報一覧)<BR>
     * 担保銘柄登録情報一覧<BR>
     */
    public WEB3SLProductInfoUnit[] stockLoanProductInfoList;

    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex;

    /**
     * @@roseuid 46E8908402FA
     */
    public WEB3AdminSLProductRegiListResponse() 
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト<BR>
     */
    public WEB3AdminSLProductRegiListResponse(WEB3AdminSLProductRegiListRequest l_request)
    {
        super(l_request);
    }
}
@
