head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerInfoCommon.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用顧客サービス情報一覧共通情報(WEB3AdminSrvRegiCustomerInfoCommon.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 李頴淵 新規作成
*/

package webbroker3.srvregi.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (サービス利用顧客サービス情報一覧共通情報)<BR>
 * サービス利用顧客サービス情報一覧共通情報データクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiCustomerInfoCommon extends Message 
{
    
    /**
     * (サービス区分)
     */
    public String serviceDiv;
    
    /**
     * (申込登録ID)
     */
    public String applyRegId;
    
    /**
     * (サービス名称)
     */
    public String serviceName;
    
    /**
     * (申込抽選区分)<BR>
     * 0:試用　@1:申込　@2:当選／本申込　@3:落選　@4:取消　@5:自動当選　@6:全て<BR>
     * （null:未登録）<BR>
     */
    public String applyLotteryDiv;
    
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
     * (初期申込区分)<BR>
     * null:表示対象外 <BR>
     * 0:無 <BR>
     * 1:有<BR>
     */
    public String firstApplyDiv;
    
    /**
     * (最終更新日)
     */
    public Date lastUpdateTime;
    
    /**
     * (最終更新者)
     */
    public String lastUpdater;
    
    /**
     * (サービス利用顧客サービス情報一覧共通情報)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE56420064
     */
    public WEB3AdminSrvRegiCustomerInfoCommon() 
    {
     
    }
}
@
