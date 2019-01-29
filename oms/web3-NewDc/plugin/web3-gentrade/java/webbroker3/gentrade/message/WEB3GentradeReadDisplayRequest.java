head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.50.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5cc4d8b0586158f;
filename	WEB3GentradeReadDisplayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 電子鳩閲覧表示リクエスト(WEB3GentradeReadDisplayRequest.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/10 張騰宇(中訊) 新規作成 仕様変更モデルNo.353
*/

package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (電子鳩閲覧表示リクエスト)<BR>
 * 電子鳩閲覧表示リクエストクラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3GentradeReadDisplayRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "gentrade_read_display";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 201011101417L;

    /**
     * @@roseuid 423698A601D2
     */
    public WEB3GentradeReadDisplayRequest()
    {

    }

    /**
     * リクエストに対応するレスポンスオブジェクトを返却する。<BR>
     * @@return WEB3GenResponse<BR>
     * @@roseuid 4236708603DE<BR>
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3GentradeReadDisplayResponse(this);
    }
}
@
