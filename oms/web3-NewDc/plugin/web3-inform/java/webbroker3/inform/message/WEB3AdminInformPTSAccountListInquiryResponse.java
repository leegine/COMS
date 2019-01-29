head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListInquiryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・PTS申込客一覧問合せ検索レスポンスクラス(WEB3AdminInformPTSAccountListInquiryResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 謝旋(中訊) 新規作成 モデルNo.128
*/

package webbroker3.inform.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・PTS申込客一覧問合せ検索レスポンスクラス)<BR>
 * 管理者・PTS申込客一覧問合せ検索レスポンスクラス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListInquiryResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_pts_account_list_inquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802281635L;

    /**
     * 申込日時（自）
     */
    public Date applyDateFrom;

    /**
     * 申込日時（至）
     */
    public Date applyDateTo;

    /**
     * @@roseuid 47C522D402EA
     */
    public WEB3AdminInformPTSAccountListInquiryResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminInformPTSAccountListInquiryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
