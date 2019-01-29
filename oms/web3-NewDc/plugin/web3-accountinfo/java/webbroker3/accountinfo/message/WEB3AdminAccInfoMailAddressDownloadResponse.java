head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoMailAddressDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressDownloadResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082115L;

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
     * (顧客メールアドレス情報一覧)<BR>
     * 顧客メールアドレス情報一覧<BR>
     */
    public WEB3AccInfoAccountMailAddressInfo[] accountMailAddressList;

    /**
     * @@roseuid 418F38570138
     */
    public WEB3AdminAccInfoMailAddressDownloadResponse()
    {

    }

    /**
     * (管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressDownloadResponse
     * @@roseuid 4136B90D03C8
     */
    public WEB3AdminAccInfoMailAddressDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
