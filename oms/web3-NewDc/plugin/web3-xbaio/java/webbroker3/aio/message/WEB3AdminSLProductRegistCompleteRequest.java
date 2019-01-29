head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録完了リクエスト(WEB3AdminSLProductRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 孟亞南 (中訊) 新規作成 モデルNo.760
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (担保銘柄登録完了リクエスト)<BR>
 * 担保銘柄登録完了リクエストクラス<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminSLProductRegistCompleteRequest extends WEB3AdminSLProductRegistCommonRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_regist_complete";

    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709141341L;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * @@roseuid 46E89084026D
     */
    public WEB3AdminSLProductRegistCompleteRequest()
    {
    }

    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * １）　@super.validateをコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DF907A039F
     */
    public void validate() throws WEB3BaseException
    {
        super.validate();
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E7829802FD
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSLProductRegistCompleteResponse(this);
    }
}
@
