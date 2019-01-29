head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込問合せダウンロードレスポンス(WEB3AdminAioCashoutInqDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/31 徐大方 (中訊) 新規作成
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (出金申込問合せダウンロードレスポンス)<BR>
 * 出金申込問合せダウンロードレスポンスクラス<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0 
 */
public class WEB3AdminAioCashoutInqDownloadResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_download";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200607311248L;   
    
    /**
     * (ダウンロードファ@イル)<BR>
     * ダウンロードファ@イル<BR>
     * <BR>
     * ※ CSVファ@イル行の配列 <BR>
     */
    public String[] downloadFile;
    
    /**
     * (現在日時)<BR>
     * 現在日時<BR>
     */
    public Date currentDate;
    
    /**
     * @@roseuid 4159EB6602DD
     */
    public WEB3AdminAioCashoutInqDownloadResponse(WEB3AdminAioCashoutInqDownloadRequest l_request) 
    {
        super(l_request);
    }
}
@
