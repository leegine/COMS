head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductRegistConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券銘柄登録確認レスポンス(WEB3AdminBondDomesticProductRegistConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 周墨洋 (中訊) 新規作成 モデル192
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者国内債券銘柄登録確認レスポンス)<BR>
 * 管理者国内債券銘柄登録確認レスポンス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductRegistConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_product_regist_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20070709100000L;

    /**
     * (管理者国内債券銘柄登録確認レスポンス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 46637CCD000F
     */
    public WEB3AdminBondDomesticProductRegistConfirmResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminBondDomesticProductRegistConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
