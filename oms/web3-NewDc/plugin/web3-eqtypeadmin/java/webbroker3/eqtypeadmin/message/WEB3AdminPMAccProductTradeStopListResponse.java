head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccProductTradeStopListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・顧客銘柄別取引停止一覧レスポンス (WEB3AdminPMAccProductTradeStopListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 中尾寿彦(SRA) 新規作成
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （管理者・顧客銘柄別取引停止一覧レスポンス）<BR>
 * <BR>
 * 管理者・顧客銘柄別取引停止一覧レスポンスクラス<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopListResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
	public final static String PTYPE = "admin_p_m_acc_product_trade_stop_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * （総ページ数）<BR>
     * <BR>
     * 総ページ数<BR>
     * <BR>
     * totalPages<BR>
     * <BR>
     */
    public String totalPages;

    /**
     * （総レコード数）<BR>
     * <BR>
     * 総レコード数<BR>
     * <BR>
     * totalRecords<BR>
     * <BR>
     */
    public String totalRecords;

    /**
     * （表示ページ番号）<BR>
     * <BR>
     * 表示ページ番号<BR>
     * <BR>
     * pageIndex<BR>
     * <BR>
     */
    public String pageIndex;

    /**
     * （顧客銘柄別取引停止情報一覧）
     * 顧客銘柄別取引停止情報一覧
     * ----<English>--------------------
     * accProductTradeStopInfo
     */
    public WEB3AdminPMAccProductTradeStopInfoUnit[] accProductTradeStopInfo;

    /**
     * @@roseuid 41FD930902FD
     */
    public WEB3AdminPMAccProductTradeStopListResponse()
    {

    }

    /**
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMAccProductTradeStopListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
