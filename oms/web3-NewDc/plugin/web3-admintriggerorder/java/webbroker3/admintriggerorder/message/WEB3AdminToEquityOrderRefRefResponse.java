head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToEquityOrderRefRefResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・株式注文照会レスポンス(WEB3AdminToEquityOrderRefRefResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/03　@魏新(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (トリガー注文管理者・株式注文照会レスポンス)<BR>
 * 
 * @@author 魏新<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminToEquityOrderRefRefResponse extends WEB3GenResponse
{   
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_equity_order_ref_ref";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603031700L;
     
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
     * (株式注文照会Unit一覧)<BR>
     */
    public WEB3AdminToEquityOrderRefUnit[] equityOrderList;
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F1B3C80399
     */
    public WEB3AdminToEquityOrderRefRefResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminToEquityOrderRefRefResponse(WEB3AdminToEquityOrderRefRefRequest l_request)
    {
        super(l_request);
    } 
}
@
