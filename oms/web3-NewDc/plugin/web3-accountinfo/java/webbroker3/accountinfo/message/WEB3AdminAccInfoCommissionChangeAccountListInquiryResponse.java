head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料変更申込顧客一覧問合せﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報手数料変更申込顧客一覧問合せﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報手数料変更申込顧客一覧問合せﾚｽﾎﾟﾝｽ<BR>
 * @@author 彭巍
 * @@version 1.0 
 */
public class WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_commissionChangeAccountListInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082142L;

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
     * (変更申込顧客一覧)<BR>
     * 変更申込顧客一覧<BR>
     */
    public WEB3AccInfoCommissionCourseChangeInfo[] changeApplyAccountList;

    /**
     * @@roseuid 418F386A02FD
     */
    public WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse()
    {

    }

    /**
     * (管理者お客様情報手数料変更申込顧客一覧問合せﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse
     * @@roseuid 4151204601A3
     */
    public WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
