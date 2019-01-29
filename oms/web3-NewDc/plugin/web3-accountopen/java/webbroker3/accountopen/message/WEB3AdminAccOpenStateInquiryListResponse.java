head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設状況問合せ一覧レスポンス(WEB3AdminAccOpenStateInquiryListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 郭英 (中訊) 新規作成
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者口座開設状況問合せ一覧レスポンス)<BR>
 * 管理者口座開設状況問合せ一覧レスポンス<BR>
 *     
 * @@author 郭英
 * @@version 1.0
 */

public class WEB3AdminAccOpenStateInquiryListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_stateInquiryList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081605L;

    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex;

    /**
     * (口座開設状況一覧)<BR>
     * 口座開設状況一覧<BR>
     */
    public WEB3AccOpenStateUnit[] accountOpenStateList;

    /**
     * @@roseuid 41B45E760203
     */
    public WEB3AdminAccOpenStateInquiryListResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccOpenStateInquiryListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
