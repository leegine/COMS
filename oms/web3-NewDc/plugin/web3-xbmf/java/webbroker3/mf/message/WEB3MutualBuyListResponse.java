head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付一覧照会レスポンスクラス(WEB3MutualBuyListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 周勇 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/
package webbroker3.mf.message;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 投資信託買付一覧照会レスポンスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyListResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_buy_list";
    
    /**
     * 投信銘柄カテゴリー一覧<BR>
     * (nullの場合はカテゴリ指定無しとする)
     */
    public WEB3MutualProductCategoryUnit[] categoryList;
    
    /**
     * 表示ページ番号<BR>
     * <BR>
     * 実際に表示するページ位置を指定　@※先頭ページを"1"とする
     */
    public String pageIndex;
    
    /**
     * 総ページ数
     */
    public String totalPages;
    
    /**
     * 総レコード数
     */
    public String totalRecords;
    
    /**
     * 買付銘柄一覧
     */
    public WEB3MutualBuyProductGroup[] buyProductGroups;
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualBuyListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
    
    /**
     * (投信買付一覧照会レスポンス)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A8795E023A
     */
    public WEB3MutualBuyListResponse()
    {
    }
}
@
