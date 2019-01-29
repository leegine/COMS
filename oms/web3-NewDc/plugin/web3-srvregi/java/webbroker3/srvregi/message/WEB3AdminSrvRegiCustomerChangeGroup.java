head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerChangeGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客変更一覧行(WEB3AdminSrvRegiCustomerChangeGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (サービス利用管理者顧客変更一覧行)<BR>
 * サービス利用管理者顧客変更一覧行データクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
*/
public class WEB3AdminSrvRegiCustomerChangeGroup extends Message 
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
     * 0:試用　@1:申込　@2:当選／本申込　@3:落選　@4:取消　@5:自動当選（　@6:全て）<BR>
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
     * (登録区分)<BR>
     * 0:有料　@1:無料<BR>
     */
    public String registDiv;
    
    /**
     * (利用料金)
     */
    public String chargeAmt;
    
    /**
     * (サービス利用管理者顧客変更一覧行)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE7C6C0229
     */
    public WEB3AdminSrvRegiCustomerChangeGroup() 
    {
     
    }
}
@
