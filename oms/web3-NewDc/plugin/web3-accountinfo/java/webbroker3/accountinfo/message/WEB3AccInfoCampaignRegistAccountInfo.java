head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.10.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCampaignRegistAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料割引キャンペーン登録顧客情報(WEB3AccInfoCampaignRegistAccountInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  齊珂(中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (手数料割引キャンペーン登録顧客情報)<BR>
 * 手数料割引キャンペーン登録顧客情報<BR>
 * <BR>
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AccInfoCampaignRegistAccountInfo extends Message 
{
    
    /**
     * (手数料キャンペーン条件ID)<BR>
     * 手数料キャンペーン条件ID<BR>
     */
    public String campaignId;
    
    /**
     * (商品コード)<BR>
     * 商品コード<BR>
     * <BR>
     * 10 ： 上場株式<BR>
     * 11 ： JASDAQ<BR>
     * 12 ： ミニ株式 <BR>
     * 30 ： 債券 <BR>
     * 31 ： 債券（店頭） <BR>
     */
    public String[] itemCode;
    
    /**
     * (キャンペーン名称)<BR>
     * キャンペーン名称<BR>
     */
    public String campaignName;
    
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
     * (扱者コード)<BR>
     * 扱者コード<BR>
     */
    public String traderCode;
    
    /**
     * (口座開設区分)<BR>
     * 口座開設区分<BR>
     * <BR>
     * 1 ： 総合口座<BR>
     * 2 ： 信用口座<BR>
     * 3 ： 先物OP口座<BR>
     * 4 ： FX口座<BR>
     * 5 ： 中国株口座<BR>
     */
    public String accountOpenDiv;
    
    /**
     * (徴収率)<BR>
     * 徴収率<BR>
     */
    public String collectRate;
    
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
     * (登録タイプ)<BR>
     * 登録タイプ<BR>
     * <BR>
     * 0 ： 口座開設条件指定<BR>
     * 1 ： 個別顧客指定<BR>
     * 2 ： 強制個別顧客指定<BR>
     */
    public String registType;
    
    /**
     * (有効区分)<BR>
     * 有効区分<BR>
     * <BR>
     * 0 ： 無効<BR>
     * 1 ： 有効<BR>
     */
    public String activeDiv;
    
    /**
     * @@roseuid 45C0875E01F9
     */
    public WEB3AccInfoCampaignRegistAccountInfo() 
    {
     
    }
}
@
