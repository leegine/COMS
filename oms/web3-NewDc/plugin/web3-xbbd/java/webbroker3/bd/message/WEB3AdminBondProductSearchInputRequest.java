head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.36.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductSearchInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券銘柄一覧画面表示リクエスト(WEB3AdminBondProductSearchInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者債券銘柄一覧画面表示リクエスト)<BR>
 * 管理者債券銘柄一覧画面表示リクエストクラス
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondProductSearchInputRequest extends WEB3GenRequest
{
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_search_input";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * @@roseuid 44E3363D02EE
     */
    public WEB3AdminBondProductSearchInputRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     * @@roseuid 44BC636000EF
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminBondProductSearchInputResponse(this);
    }
}
@
