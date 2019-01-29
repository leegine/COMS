head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToIfoOrderRefRefResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・先物OP注文照会レスポンス(WEB3AdminToIfoOrderRefRefRespons.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (トリガー注文管理者・先物OP注文照会レスポンス)<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToIfoOrderRefRefResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_ifo_order_ref_ref";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602141850L;
    
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
     * (先物OP注文照会Unit一覧)<BR>
     */
    public WEB3AdminToIfoOrderRefUnit[] ifoOrderList;
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F1B3C9007D
     */
    public WEB3AdminToIfoOrderRefRefResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminToIfoOrderRefRefResponse(WEB3AdminToIfoOrderRefRefRequest l_request)
    {
        super(l_request);
    } 
}
@
