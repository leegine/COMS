head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoCommissionCampaignUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料割引キャンペーン情報(WEB3PvInfoCommissionCampaignUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/26 関博(中訊) 新規作成
Revision History : 2007/03/07 関博(中訊) 仕様変更モデル074
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (手数料割引キャンペーン情報)<BR>
 * 手数料割引キャンペーン情報クラス<BR>
 * @@author 関博
 * @@version 1.0
 */
public class WEB3PvInfoCommissionCampaignUnit extends Message
{

    /**
     * (キャンペーン名称)<BR>
     * 手数料割引キャンペーン名称<BR>
     */
    public String campaignName;

    /**
     * (商品コード)<BR>
     * 商品コード<BR>
     * <BR>
     * 10：上場株式<BR>
     * 11：JASDAQ<BR>
     * 12：ミニ株式<BR>
     * 30：債権<BR>
     * 31：債権（店頭）<BR>
     * 40：外国株式<BR>
     * 50：先物<BR>
     * 51：株価指数OP<BR>
     */
    public String[] commodityCodeList;
        
    /**
     * (割引率)<BR>
     * 割引率<BR>
     */
    public String discountRate;

    /**
     * (適用期間From)<BR>
     * 適用期間from（ＹＹＹＹＭＭＤＤ）<BR>
     */
    public Date applyStartDate;
        
    /**
     * (適用期間Ｔｏ)<BR>
     * 適用期間To（YYYYMMDD）<BR>
     */
    public Date applyEndDate;
    
    /**
     * (手数料割引キャンペーン情報)<BR>
     * コンストラクタ。<BR>
     */
    public WEB3PvInfoCommissionCampaignUnit()
    {
        
    }
    
}
@
