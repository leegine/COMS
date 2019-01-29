head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 損益明細ファ@イルダウンロードレスポンス(WEB3PLSProfitLossDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/19 趙林鵬 (中訊) 新規作成
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (損益明細ファ@イルダウンロードレスポンス)<BR>
 * 損益明細ファ@イルダウンロードレスポンス
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3PLSProfitLossDownloadResponse extends WEB3GenResponse
{
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "pLS_profit_loss_download";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610191300L;

    /**
     * (ダウンロードファ@イル)<BR>
     * ダウンロードファ@イル<BR>
     * ※CSVファ@イル行の配列<BR>
     */
    public String[] downloadFile;

    /**
     * (現在日時)<BR>
     * 現在日時<BR>
     */
    public Date currentDate;

    /**
     * @@roseuid 44E33635003E
     */
    public WEB3PLSProfitLossDownloadResponse()
    {

    }

    /**
     *　@コンストラクタ。<BR>
     *　@指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     *　@@@param l_request - リクエストオブジェクト<BR>
     */
    public WEB3PLSProfitLossDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
