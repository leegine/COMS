head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.31.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderVoucherListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式当日注文伝票一覧レスポンス(WEB3AdminFeqOrderVoucherListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式当日注文伝票一覧レスポンス)<BR>
 * 管理者外国株式当日注文伝票一覧レスポンスクラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqOrderVoucherListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderVoucherList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (当日注文伝票)<BR>
     * 外国株式当日注文伝票（CSV）の配列
     */
    public String[] orderVoucherList;
    
    /**
     * @@roseuid 42CE39FB0203
     */
    public WEB3AdminFeqOrderVoucherListResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqOrderVoucherListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
