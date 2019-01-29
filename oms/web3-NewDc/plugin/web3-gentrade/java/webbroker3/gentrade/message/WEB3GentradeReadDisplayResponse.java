head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.50.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5cc4d8b0586158f;
filename	WEB3GentradeReadDisplayResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 電子鳩閲覧表示レスポンス(WEB3GentradeReadDisplayResponse.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/10 張騰宇(中訊) 新規作成 仕様変更モデルNo.353
*/

package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (電子鳩閲覧表示レスポンス)<BR>
 * 電子鳩閲覧表示レスポンスクラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3GentradeReadDisplayResponse extends WEB3GentradeBatoDisplayCommonResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "gentrade_read_display";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 201011101418L;

    /**
     * @@roseuid 423698A7004B
     */
    public WEB3GentradeReadDisplayResponse()
    {

    }

    /**
     * コンストラクタ<BR>
     * @@param WEB3GenRequest - リクエストデータ<BR>
     * <BR>
     * @@return WEB3GentradeReadDisplayResponse
     * @@roseuid 4236711E0272
     */
    public WEB3GentradeReadDisplayResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
