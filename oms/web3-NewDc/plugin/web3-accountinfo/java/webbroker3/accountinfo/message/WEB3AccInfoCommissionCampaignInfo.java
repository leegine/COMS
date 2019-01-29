head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommissionCampaignInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料割引キャンペーン情報(WEB3AccInfoCommissionCampaignInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/25  吉麗ナ(中訊) 新規作成
Revision History : 2007/03/06  仕様変更・モデル206
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (手数料割引キャンペーン情報)<BR>
 * 手数料割引キャンペーンメッセージ<BR>
 * <BR>
 * @@author 吉麗ナ
 * @@version 1.0
 */
public class WEB3AccInfoCommissionCampaignInfo extends Message
{
    
    /**
     * (キャンペーン名称)<BR>
     * キャンペーン名称<BR>
     */
    public String campaignName;
    
    /**
     * (商品コード)<BR>
     * 商品コード<BR>
     * 10：上場株式<BR>
     * 11：JASDAQ<BR>
     * 12：ミニ株式<BR>
     * 30：債権<BR>
     * 31：債権（店頭）<BR>
     * 40：外国株式<BR>
     * 50：先物<BR>
     * 51：株価指数OP<BR>
     */
    public String[] itemCode;
    
    /**
     * (割引率)<BR>
     * 割引率<BR>
     */
    public String discountRate;
    
    /**
     * (適用期間From)<BR>
     * 適用期間from（YYYYMMDD）<BR>
     */
    public Date targetPeriodFrom;

    /**
     * (適用期間To)<BR>
     * 適用期間to（YYYYMMDD）<BR>
     */
    public Date targetPeriodTo;
    
    public WEB3AccInfoCommissionCampaignInfo() 
    {
     
    }
}
@
