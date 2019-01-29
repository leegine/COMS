head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.30.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProductListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式銘柄一覧レスポンス(WEB3FeqProductListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (外国株式銘柄一覧レスポンス)<BR>
 * 外国株式銘柄一覧レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqProductListResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_productList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (市場コード一覧)<BR>
     * 市場コードの配列<BR>
     */
    public String[] marketList;
    
    /**
     * (銘柄情報一覧)<BR>
     * 外国株式銘柄明細の配列<BR>
     */
    public WEB3FeqProductUnit[] productCodeNames;
    
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
     * @@roseuid 42CE3A0A003E
     */
    public WEB3FeqProductListResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FeqProductListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
