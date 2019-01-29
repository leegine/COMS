head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報メールアドレス変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報メールアドレス変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報メールアドレス変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressChangeAccountDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082121L;

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
     * (メールアドレス変更顧客情報一覧)<BR>
     * メールアドレス変更顧客情報一覧<BR>
     */
    public WEB3AccInfoAccountMailAddressInfo[] mailAddressChangeAccountList;

    /**
     * @@roseuid 418F38560138
     */
    public WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse()
    {

    }

    /**
     * (管理者お客様情報メールアドレス変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse
     * @@roseuid 4136B8EB003D
     */
    public WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
