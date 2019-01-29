head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引履歴一覧ファ@イルダウンロードレスポンス(WEB3HistoryTradeHistoryDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/30 凌建平(中訊) 新規作成
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (取引履歴一覧ファ@イルダウンロードレスポンス)<BR>
 * 取引履歴一覧ファ@イルダウンロードレスポンスクラス<BR>
 * 
 * @@author 凌建平
 * @@version 1.0 
 */
public class WEB3HistoryTradeHistoryDownloadResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tradeHistory_tradeHistoryDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511301710L;
         
    /**
     * (ダウンロードファ@イル)<BR>
     * ダウンロードファ@イル<BR>
     * ※ CSVファ@イル行の配列<BR>
     */
    public String[] downloadFile;
    
    /**
     * (現在日時)<BR>
     * 現在日時<BR>
     */
    public Date currentDate;
    
    /**
     * @@roseuid 41789C4B03A9
     */
    public WEB3HistoryTradeHistoryDownloadResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3HistoryTradeHistoryDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
