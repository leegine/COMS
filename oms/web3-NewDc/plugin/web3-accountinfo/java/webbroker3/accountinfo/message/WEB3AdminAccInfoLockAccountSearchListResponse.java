head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLockAccountSearchListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ロック顧客問合せ一覧レスポンス(WEB3AdminAccInfoLockAccountSearchListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報ロック顧客問合せ一覧レスポンス)<BR>
 * 管理者お客様情報ロック顧客問合せ一覧レスポンス<BR>
 * 
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoLockAccountSearchListResponse extends WEB3GenResponse 
{
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_lockAccountSearchList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200509191350L;
    
    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     * 
     */
    public String totalPages;
    
    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     * 
     */
    public String totalRecords;
    
    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex;
    
    /**
     * (停止情報一覧 )<BR>
     * 停止情報一覧 <BR>
     */
    public WEB3AccInfoStopInfoUnit[] stopInfoList;
    
    public WEB3AdminAccInfoLockAccountSearchListResponse()
    {

    }

    /**
     * (管理者お客様情報ロック顧客問合せ一覧レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchInputResponse
     * @@roseuid 416657DA0191
     */
    protected WEB3AdminAccInfoLockAccountSearchListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
