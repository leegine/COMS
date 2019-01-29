head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.01.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCampaignInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料割引キャンペーン条件情報(WEB3AccInfoCampaignInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  齊珂(中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (手数料割引キャンペーン条件情報)<BR>
 * 手数料割引キャンペーン個別顧客指定情報<BR>
 * <BR>
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AccInfoCampaignInfo extends Message 
{
    
    /**
     * (手数料割引キャンペーン条件ID)<BR>
     * 手数料割引キャンペーン条件ID<BR>
     */
    public String campaignId;
    
    /**
     * (キャンペーン名称)<BR>
     * キャンペーン名称<BR>
     */
    public String campaignName;
    
    /**
     * (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    public String institutionCode;
    
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
     * (顧客名)<BR>
     * 顧客名<BR>
     */
    public String accountName;
    
    /**
     * (商品コード)<BR>
     * 商品コード<BR>
     */
    public String[] itemCode;
    
    /**
     * (対象期間From)<BR>
     * 対象期間From<BR>
     */
    public Date targetPeriodFrom;
    
    /**
     * (対象期間To)<BR>
     * 対象期間To<BR>
     */
    public Date targetPeriodTo;
    
    /**
     * (徴収率)<BR>
     * 徴収率<BR>
     */
    public String collectRate;
    
    /**
     * (口座開設経過期間（月）)<BR>
     * 口座開設経過期間（月）<BR>
     */
    public String accopenPassPeriodMonth;
    
    /**
     * (口座開設経過期間（日）)<BR>
     * 口座開設経過期間（日）<BR>
     */
    public String accopenPassPeriodDay;
    
    /**
     * (扱者コード)<BR>
     * 扱者コード<BR>
     */
    public String traderCode;
    
    /**
     * (口座開設区分)<BR>
     * 口座開設区分<BR>
     */
    public String accountOpenDiv;
    
    /**
     * (口座開設日From)<BR>
     * 口座開設日From<BR>
     */
    public Date accountOpenDateFrom;
    
    /**
     * (口座開設日To)<BR>
     * 口座開設日To<BR>
     */
    public Date accountOpenDateTo;
    
    /**
     * (登録タイプ)<BR>
     * 登録タイプ<BR>
     * <BR>
     * 0： 口座開設条件設定<BR>
     * 1： 個別顧客指定<BR>
     * 2： 強制個別顧客指定<BR>
     */
    public String registType;
    
    /**
     * (削除フラグ)<BR>
     * 削除フラグ<BR>
     */
    public String deleteFlag;
    
    /**
     * (処理区分)<BR>
     * 処理区分<BR>
     */
    public String transactionDiv;
    
    /**
     * (登録者)<BR>
     * 登録者<BR>
     */
    public String registrant;
    
    /**
     * (登録日)<BR>
     * 登録日<BR>
     */
    public Date registDate;
    
    /**
     * (更新日)<BR>
     * 更新日<BR>
     */
    public Date updateDate;
    
    /**
     * @@roseuid 45C0875E0063
     */
    public WEB3AccInfoCampaignInfo() 
    {
     
    }
}
@
