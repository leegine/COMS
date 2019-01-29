head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済注文ダウンロードレスポンス（WEB3AdminForcedSettleDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/21 于瀟(中訊) 新規作成モデル175
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (管理者・強制決済注文ダウンロードレスポンス)<BR>
 * 管理者・強制決済注文ダウンロードレスポンス<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3AdminForcedSettleDownloadResponse extends WEB3AdminForcedSettleReferenceResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_download";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801211115L;

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
     * @@roseuid 47903208036B
     */
    public WEB3AdminForcedSettleDownloadResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminForcedSettleDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
