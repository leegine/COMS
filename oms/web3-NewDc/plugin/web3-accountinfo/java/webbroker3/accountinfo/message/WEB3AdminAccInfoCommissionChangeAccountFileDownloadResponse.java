head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.07.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料変更申込顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報手数料変更申込顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報手数料変更申込顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ<BR>
 * @@author 彭巍
 * @@version 1.0 
 */
public class WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_commissionChangeAccountFileDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082146L;

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
     * @@roseuid 418F386B0119
     */
    public WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse()
    {

    }

    /**
     * (管理者お客様情報手数料変更申込顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse
     * @@roseuid 4150E83A009B
     */
    public WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
