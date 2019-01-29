head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.09.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 張宝楠 (中訊) 新規作成
*/


package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_loginPasswordChangeAccountFileDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082125L;

    /**
     * (ダウンロードファ@イル)<BR>
     * ダウンロードファ@イル<BR>
     * <BR>
     * ※ CSVファ@イル行の配列<BR>
     */
    public String[] downloadFile;

    /**
     * (現在日時)<BR>
     * 現在日時<BR>
     */
    public Date currentDate;

    /**
     * @@roseuid 418F385502FD
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse()
    {

    }

    /**
     * (管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse
     * @@roseuid 4136B91D0195
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
