head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.34.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqRcvdQueueReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式RCVDキュー照会一覧レスポンス(WEB3AdminFeqRcvdQueueReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 何文敏 (中訊) 新規作成
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者外国株式RCVDキュー照会一覧レスポンス)<BR>
 * 管理者外国株式RCVDキュー照会一覧レスポンスクラス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */

public class WEB3AdminFeqRcvdQueueReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_rcvd_queue_reference";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD005D
     */
    public WEB3AdminFeqRcvdQueueReferenceResponse() 
    {
     
    }
    
    /**
     * (外国株式RCVDキュー情報一覧)<BR>
     * 外国株式RCVDキュー情報の配列<BR>
     */
    public WEB3FeqRcvdQueueInfo[] feqRcvdQueueInfoList;
    
    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     */
    public String totalPages;
    
    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords;
    
    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex;
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqRcvdQueueReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
    
}
@
