head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqRcvdQueueReferenceInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式RCVDキュー照会入力レスポンス(WEB3AdminFeqRcvdQueueReferenceInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 何文敏 (中訊) 新規作成
Revesion History : 2008/10/02 水落(SRA) 【外国株式】仕様変更管理台帳（モデル）No.465
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者外国株式RCVDキュー照会入力レスポンス)<BR>
 * 管理者外国株式RCVDキュー照会入力レスポンスクラス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */

public class WEB3AdminFeqRcvdQueueReferenceInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_rcvd_queue_reference_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD005D
     */
    public WEB3AdminFeqRcvdQueueReferenceInputResponse() 
    {
     
    }
    
    /**
     * (更新者コード )<BR>
     * 更新者コード <BR>
     * <BR> 
     * ※ログインした管理者の管理者コードが設定される。<BR>
     */
    public String updaterCode;
    
    /**
     * (処理区分一覧)<BR>
     * 処理区分の配列<BR>
     * <BR>
     * 0：処理待ち<BR>
     * 1：処理済み <BR>
     * 7：約定処理中<BR>
     * 8：処理省略 <BR>
     * 9：処理エラー<BR>
     */
    public String[] transactionDivList;
    
    /**
     * (経路区分一覧)<BR>
     * 経路区分の配列 <BR>
     * <BR>
     * 0：出来通知<BR>
     * 1：出来入力<BR> 
     * 2：約定結果一括入力<BR> 
     * 3：注文受付<BR>
     * 4：注文受付取消認証<BR>
     * 5：注文受付結果一括入力<BR>
     */
    public String[] orderRootDivList;
    
    /**
     * (受付区分一覧)<BR>
     * 受付区分の配列<BR>
     * <BR>
     * 01：注文受付済<BR> 
     * 09：注文受付エラー<BR>
     * 03：注文受付済取消<BR>
     * 11：訂正済<BR>
     * 19：訂正エラー<BR> 
     * 21：取消済 <BR>
     * 29：取消エラー <BR>
     * 31：出来ず<BR>
     */
    public String[] acceptDivList;
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqRcvdQueueReferenceInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
    
}
@
