head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCampaignSearchCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料割引キャンペーン検索条件(WEB3AccInfoCampaignSearchCondition.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  齊珂(中訊) 新規作成
Revision History : 2007/2/1  モデルNo.165
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (手数料割引キャンペーン検索条件)<BR>
 * キャンペーン検索条件<BR>
 * <BR>
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AccInfoCampaignSearchCondition extends Message 
{
    
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
     * (商品コード)<BR>
     * 商品コード<BR>
     */
    public String itemCode;
    
    /**
     * (キャンペーン名称)<BR>
     * キャンペーン名称
     */
    public String campaignName;
    
    /**
     * (対象日)<BR>
     * 対象日
     */
    public Date targetDate;
    
    /**
     * (徴収率)<BR>
     * 徴収率<BR>
     */
    public String collectRate;
    
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
     * (削除フラグ)<BR>
     * 削除フラグ
     */
    public String deleteFlag;
    
    /**
     * @@roseuid 45C0875E0312
     */
    public WEB3AccInfoCampaignSearchCondition() 
    {
     
    }
}
@
