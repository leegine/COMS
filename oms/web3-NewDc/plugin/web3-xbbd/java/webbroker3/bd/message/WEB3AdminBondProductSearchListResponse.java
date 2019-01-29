head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductSearchListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券銘柄一覧検索レスポンス(WEB3AdminBondProductSearchListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者債券銘柄一覧検索レスポンス)<BR>
 * 管理者債券銘柄一覧検索レスポンス
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondProductSearchListResponse  extends WEB3GenResponse
{
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_search_list";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号
     */
    public String pageIndex;
    
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
     * (債券銘柄照会情報一覧)<BR>
     * 債券銘柄照会情報一覧<BR>
     * 　@<BR>
     * 　@債券銘柄照会情報の配列
     */
    public WEB3AdminBondProductConditionUnit[] conditionList;
    
    /**
     * @@roseuid 44E3363E01F4
     */
    public WEB3AdminBondProductSearchListResponse() 
    {
     
    }
    
    /**
     *　@コンストラクタ。<BR>
     *　@指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     *　@@@param l_request - リクエストオブジェクト
     */
    public WEB3AdminBondProductSearchListResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
        
    }
    
}
@
