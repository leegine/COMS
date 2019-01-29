head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderVoucherListInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式当日注文伝票一覧入力リクエスト(WEB3AdminFeqOrderVoucherListInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式当日注文伝票一覧入力リクエスト)<BR>
 * 管理者外国株式当日注文伝票一覧入力リクエストクラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqOrderVoucherListInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderVoucherListInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * @@roseuid 42CE39FB01C5
     */
    public WEB3AdminFeqOrderVoucherListInputRequest() 
    {
     
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqOrderVoucherListInputResponse(this);
    }
}
@
