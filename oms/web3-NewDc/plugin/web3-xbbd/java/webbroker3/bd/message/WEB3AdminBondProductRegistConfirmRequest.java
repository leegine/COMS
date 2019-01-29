head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.51.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券銘柄登録確認リクエスト(WEB3AdminBondProductRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者債券銘柄登録確認リクエスト)<BR>
 * 管理者債券銘柄登録確認リクエストクラス
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondProductRegistConfirmRequest extends WEB3AdminBondProductRegistCommonRequest
{
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_regist_confirm";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * @@roseuid 44E3363C035B
     */
    public WEB3AdminBondProductRegistConfirmRequest() 
    {
     
    }

    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 債券銘柄登録確認レスポンスを生成し返す
     * @@return WEB3GenResponse
     * @@roseuid 44B61F0E00B9
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminBondProductRegistConfirmResponse(this);
    }
}
@
