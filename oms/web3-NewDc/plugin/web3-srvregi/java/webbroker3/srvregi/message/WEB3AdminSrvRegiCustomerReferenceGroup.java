head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerReferenceGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用顧客一覧変更照会明細一覧行(WEB3AdminSrvRegiCustomerReferenceGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
Revesion History : 2007/07/24 栄イ(中訊) 仕様変更モデル294
*/

package webbroker3.srvregi.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (サービス利用顧客一覧変更照会明細一覧行)<BR>
 * サービス利用顧客一覧変更照会明細一覧行データクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiCustomerReferenceGroup extends Message 
{
   
    /**
     * (申込登録ID)
     */
    public String applyRegId;
    
    /**
     * (抽選区分)<BR>
     * 0:無　@1:有<BR>
     */
    public String lotteryDiv;
    
    /**
     * (部店コード)
     */
    public String branchCode;
    
    /**
     * (顧客コード)
     */
    public String accountCode;
    
    /**
     * (申込抽選区分)<BR>
     * 0:試用　@1:申込　@2:当選／本申込　@3:落選　@4:取消　@5:自動当選　@6:全て<BR>
     */
    public String applyLotteryDiv;
    
    /**
     * (申込日)
     */
    public Date applyDate;
    
    /**
     * (適用開始日)
     */
    public Date trialStartDate;
    
    /**
     * (適用終了日)
     */
    public Date trialEndDate;
    
    /**
     * (処理区分)<BR>
     * 0 or null:未処理　@1:処理済<BR>
     */
    public String transactionDiv;
    
    /**
     * (登録区分)<BR>
     * 0:有料　@1:無料<BR>
     */
    public String registDiv;
    
    /**
     * (利用料金)
     */
    public String chargeAmt;
    
    /**
     * (最終更新日)
     */
    public Date lastUpdateTime;
    
    /**
     * (最終更新者)
     */
    public String lastUpdater;
    
    /**
     * (サービス利用顧客一覧変更照会明細一覧行)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE5AF2020A
     */
    public WEB3AdminSrvRegiCustomerReferenceGroup() 
    {
     
    }
}
@
