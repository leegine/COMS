head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSendQueueInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式SENDキュー情報(WEB3FeqSendQueueInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 何文敏 (中訊) 新規作成
Revesion History : 2006/12/11 齊珂   (中訊) モデル No.310
Revesion History : 2007/01/15 徐大方 (中訊) モデル No.331
Revesion History : 2007/02/02 丁昭奎 (中訊) モデル No.341
Revesion History : 2008/02/01 馮海濤 (中訊) モデル No.395
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (外国株式SENDキュー情報)<BR>
 * 外国株式SENDキュー情報クラス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */

public class WEB3FeqSendQueueInfo extends Message
{
    /**
     * (運用コード)<BR>
     * 運用コード<BR>
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
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    public String orderBizDate;
    
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
     * (オペレータタイプ)<BR>
     * オペレータタイプ<BR>
     * <BR>
     * 0：新規<BR>
     * 1：取消<BR>
     * 2：訂正<BR>
     */
    public String operatorType;
    
    /**
     * (注文種別)<BR>
     * 注文種別<BR>
     * <BR>
     * 701：買付<BR>
     * 702：売付<BR>
     */
    public String orderType;
    
    /**
     * (指値)<BR>
     * 指値<BR>
     */
    public String orderPrice;
    
    /**
     * (注文数量)<BR>
     * 注文数量<BR>
     */
    public String orderQuantity;
    
    /**
     * (訂正前指値)<BR>
     * 訂正前指値<BR>
     */
    public String beforeOrderPrice;
    
    /**
     * (訂正前数量)<BR>
     * 訂正前数量<BR>
     */
    public String beforeOrderQuantity;
    
    /**
     * (処理区分)<BR>
     * 処理区分<BR>
     * <BR>
     * 0：処理待ち<BR> 
     * 1：処理済み<BR>
     * 2：再送信待ち<BR>
     * 6：送信準備状態<BR>
     * 7：未送信<BR>
     * 8：処理省略<BR>
     * 9：処理エラー<BR>
     */
    public String transactionDiv;
    
    /**
     * (メール送信日時)<BR>
     * メール送信日時<BR>
     */
    public Date sendMailDate;
    
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
     * (キューID)<BR>
     * キューID<BR>
     */
    public String queueId;
    
    /**
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;
    
    /**
     * (外国株式SENDキュー情報)<BR>
     * コンストラクタ<BR>
     */  
    public WEB3FeqSendQueueInfo()
    {
        
    }
}
@
