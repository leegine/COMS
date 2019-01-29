head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLAccountOpenApplyListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者証券担保ローン申込顧客一覧スポンス(WEB3AdminSLAccountOpenApplyListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 何文敏 (中訊) 新規作成 仕様変更・モデルNo.756
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者証券担保ローン申込顧客一覧レスポンス)<BR>
 * 管理者証券担保ローン申込顧客一覧レスポンス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminSLAccountOpenApplyListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_sl_account_open_apply_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709071027L;

    /**
     * (担保ローン申込顧客明細)<BR>
     * 担保ローン申込顧客明細<BR>
     */
    public WEB3SLAccountOpenApplyDetailUnit[] accountOpenApplyDetailList;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex;

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
     * @@param l_request - 管理者証券担保ローン申込顧客一覧リクエスト
     * @@roseuid 46E0BE47031C
     */
    public WEB3AdminSLAccountOpenApplyListResponse(
        WEB3AdminSLAccountOpenApplyListRequest l_request)
    {
        super(l_request);
    }
}
@
