head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenApplyDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・FX口座開設申込ダウンロードレスポンス(WEB3AdminFXAccOpenApplyDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/09 余新敏(中訊) 新規作成
*/

package webbroker3.aio.message;

import java.util.Date;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX口座開設申込ダウンロードレスポンス)<BR>
 * 管理者・FX口座開設申込ダウンロードレスポンスクラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenApplyDownloadResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_open_apply_download";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200602091550L;
    
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
     * コンストラクタ<BR>
     */
    public WEB3AdminFXAccOpenApplyDownloadResponse()
    {
        
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - リクエストオブジェクト
     */
    protected WEB3AdminFXAccOpenApplyDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
