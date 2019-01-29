head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngListDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設管理一覧ダウンロードレスポンス(WEB3AdminFEqConAccountOpenMngListDownloadResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import java.util.Date;

/**
 * (外株口座開設管理一覧ダウンロードレスポンス)<BR>
 * 外株口座開設管理一覧ダウンロードレスポンスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountOpenMngListDownloadResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_list_download";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171813L;
    
    /**
     * (ダウンロードファ@イル)<BR>
     * ダウンロードファ@イル
     */
    public String[] downloadFile;
    
    /**
     * (総レコード数)<BR>
     * 総レコード数
     */
    public String totalRecords;
    
    /**
     * (現在日時)<BR>
     * 現在日時
     */
    public Date currentDate;
    
    /**
     * @@roseuid 423552EB03C8
     */
    public WEB3AdminFEqConAccountOpenMngListDownloadResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminFEqConAccountOpenMngListDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
