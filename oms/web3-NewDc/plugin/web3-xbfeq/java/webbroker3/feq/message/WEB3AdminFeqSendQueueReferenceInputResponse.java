head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqSendQueueReferenceInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式SENDキュー照会入力レスポンス(WEB3AdminFeqSendQueueReferenceInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 何文敏 (中訊) 新規作成
Revesion History : 2007/02/02 丁昭奎 (中訊) モデル No.341
Revesion History : 2007/02/07 丁昭奎 (中訊) モデル No.342
Revesion History : 2008/02/01 馮海濤 (中訊) モデル No.393
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者外国株式SENDキュー照会入力レスポンス)<BR>
 * 管理者外国株式SENDキュー照会入力レスポンスクラス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */

public class WEB3AdminFeqSendQueueReferenceInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_send_queue_reference_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD005D
     */
    public WEB3AdminFeqSendQueueReferenceInputResponse() 
    {
     
    }
    
    /**
     * (処理区分一覧)<BR>
     * 処理区分の配列<BR>
     * <BR>
     * 0：処理待ち<BR>
     * 1：処理済み<BR>
     * 2：再送信待ち<BR>
     * 6：送信準備状態<BR>
     * 7：未送信<BR>
     * 8：処理省略<BR>
     * 9：処理エラー<BR>
     */
    public String[] transactionDivList;

    /**
     * (発注日一覧)<BR>
     * 発注日一覧<BR>
     * <BR>
     * 発注日の配列 <BR>
     * 当日日付<BR>
     * 前日営業日 <BR>
     * 前々日営業日<BR>
     * 前々々日営業日<BR>
     */
    public Date[] orderDateList;
    
    /**
     * (市場コード一覧)<BR>
     * 市場コードの配列
     */
    public String[] marketList;
    
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqSendQueueReferenceInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
    
}
@
