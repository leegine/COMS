head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.31.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqRcvdQueueInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式RCVDキュー情報(WEB3FeqRcvdQueueInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 何文敏 (中訊) 新規作成
                   2007/01/15 徐大方 (中訊) モデル No.331
Revesion History : 2008/10/02 水落(SRA) 【外国株式】仕様変更管理台帳（モデル）No.465
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;



/**
 * (外国株式RCVDキュー情報)<BR>
 * 外国株式RCVDキュー情報クラス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */

public class WEB3FeqRcvdQueueInfo extends Message
{
    /**
     * (更新者コード)<BR>
     * 更新者コード<BR>
     */
    public String updaterCode;
    
    /**
     * (運用コード)<BR>
     * 運用コード
     */
    public String managementCode;

    /**
     * (識別コード)<BR>
     * 識別コード<BR>
     */
    public String requestNumber;

    /**
     * (注文ID)<BR>
     * 注文ID<BR>
     */
    public String orderId;
    
    /**
     * (売買区分)<BR>
     * 売買区分<BR>
     * <BR>
     * 1：買付<BR>
     * 2：売付<BR>
     */
    public String dealingType;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * (約定日時)<BR>
     * 約定日時<BR>
     */
    public Date executionTimestamp;
    
    /**
     * (約定日)<BR>
     * 約定日<BR>
     */
    public Date executionDate;
    
    /**
     * (現地受渡日)<BR>
     * 現地受渡日<BR>
     */
    public Date localDeliveryDate;
    
    /**
     * (為替レート)<BR>
     * 為替レート<BR>
     */
    public String exchangeRate;
    
    /**
     * (約定通番)<BR>
     * 約定通番<BR>
     */
    public String executeNo;
    
    /**
     * (経路区分 )<BR>
     * 経路区分 <BR>
     * <BR>
     * 0：出来通知<BR> 
     * 1：出来入力<BR>
     * 2：約定結果一括入力<BR>
     * 3：注文受付<BR>
     * 4：注文受付取消認証<BR>
     * 5：注文受付結果一括入力<BR>
     */
    public String orderRootDiv;
    
    /**
     * (受付区分)<BR>
     * 受付区分 <BR>
     * <BR>
     * 01：注文受付済<BR>
     * 09：注文受付エラー<BR>
     * 03：注文受付済取消<BR>
     * 11：訂正済<BR>    
     * 19：訂正エラー<BR>  
     * 21：取消済<BR>
     * 29：取消エラー<BR>
     * 31：出来ず <BR>
     */
    public String acceptDiv;
    
    /**
     * (約定単価)<BR>
     * 約定単価<BR>
     */
    public String execPrice;
    
    /**
     * (約定数量)<BR>
     * 約定数量<BR>
     */
    public String execQuantity;
    
    /**
     * (処理区分)<BR>
     * 処理区分  <BR>
     * <BR>
     * 0：処理待ち<BR>
     * 1：処理済み<BR> 
     * 7：約定処理中<BR>    
     * 8：処理省略<BR>     
     * 9：処理エラー<BR>
     */
    public String transactionDiv;

    /**
     * (拒否原因コード)<BR>
     * 拒否原因コード<BR>
     */
    public String rejectCauseCode;

    /**
     * (作成日付)<BR>
     * 作成日付<BR>
     */
    public Date createTimeStamp;
    
    /**
     * (更新日付)<BR>
     * 更新日付<BR>
     */
    public Date updateTimeStamp;    
    
    /**
     * (外国株式RCVDキュー情報)<BR>
     * コンストラクタ<BR>
     */ 
    public WEB3FeqRcvdQueueInfo()
    {
        
    }
}
@
