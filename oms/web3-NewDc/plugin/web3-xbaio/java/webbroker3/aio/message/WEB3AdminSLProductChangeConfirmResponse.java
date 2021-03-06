head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録変更確認レスポンス(WEB3AdminSLProductChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 トウ鋒鋼(中訊) 新規作成 仕様変更モデル760
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (担保銘柄登録変更確認レスポンス)<BR>
 * 担保銘柄登録変更確認レスポンスクラス<BR>
 * <BR>
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3AdminSLProductChangeConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_change_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200709141055L;

    /**
     * @@roseuid 46E89085006A
     */
    public WEB3AdminSLProductChangeConfirmResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminSLProductChangeConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
