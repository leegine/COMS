head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式銘柄情報一覧レスポンス(WEB3AdminFeqProductListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 郭英 (中訊) 新規作成
*/


package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式銘柄情報一覧レスポンス)<BR>
 * 管理者外国株式銘柄情報一覧レスポンスクラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqProductListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_productList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (市場コード一覧)<BR>
     * 市場コードの配列
     */
    public String[] marketList;
    
    /**
     * (銘柄情報一覧)<BR>
     * 銘柄情報の配列
     */
    public WEB3FeqProductUnit[] productCodeNames;
    
    /**
     * (総ページ数)<BR>
     * 総ページ数
     */
    public String totalPages;
    
    /**
     * (総レコード数)<BR>
     * 総レコード数
     */
    public String totalRecords;
    
    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号
     */
    public String pageIndex;
    
    /**
     * @@roseuid 42CE39FB008C
     */
    public WEB3AdminFeqProductListResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqProductListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
