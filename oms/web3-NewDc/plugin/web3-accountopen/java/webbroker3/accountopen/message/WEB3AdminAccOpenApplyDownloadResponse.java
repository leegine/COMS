head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.02.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設申込ダウンロードレスポンス(WEB3AdminAccOpenApplyDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者口座開設申込ダウンロードレスポンス)<BR>
 * 管理者口座開設申込ダウンロードレスポンス<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDownloadResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_applyDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081613L;

    /**
     * (ダウンロードファ@イル)<BR>
     * ダウンロードファ@イル<BR>
     * <BR>
     * ※ CSVファ@イル行の配列<BR>
     */
    public String[] downloadFile;
    
    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords;

    /**
     * (現在日時)<BR>
     * 現在日時<BR>
     */
    public Date currentDate;

    /**
     * @@roseuid 41B45E7B02EE
     */
    public WEB3AdminAccOpenApplyDownloadResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccOpenApplyDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
