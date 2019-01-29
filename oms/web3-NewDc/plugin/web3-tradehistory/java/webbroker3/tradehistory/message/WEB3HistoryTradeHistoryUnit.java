head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引履歴情報(WEB3HistoryTradeHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27  温 顕 法@(中訊) 新規作成
                   2006/10/19  張騰宇 (中訊) モデル 057
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (取引履歴情報)<BR>
 * 取引履歴情報クラス<BR>
 * 
 * @@author 温 顕 法@
 * @@version 1.0
 */
public class WEB3HistoryTradeHistoryUnit extends Message 
{
    /**
     * (取引履歴ID)<BR>
     * 取引履歴ID<BR>
     */
    public String tradeHistoryId;
    
    /**
     * (約定日)<BR>
     * 約定日<BR>
     */
    public Date execDate;
    
    /**
     * (商品コード)<BR>
     * 商品コード<BR>
     * <BR>
     * 10:　@株式<BR>
     * 11:　@信用(*1)<BR>
     * 20:　@投信<BR>
     * 21:　@外投<BR>
     * 22:　@累投<BR>
     * 23:　@MRF<BR>
     * 30:　@債券<BR>
     * 40:　@外株<BR>
     * 50:　@株先<BR>
     * 51:　@株指数ＯＰ<BR>
     * 52:　@債先<BR>
     * 53:　@債先OP<BR>
     * 54:　@店OP<BR>
     * 55:　@外先<BR>
     * 56:　@外先OP<BR>
     * 57:　@株OP<BR>
     * 60:　@外債<BR>
     * 70:　@金<BR>
     * 71:　@金GP<BR>
     * 80:　@特殊<BR>
     * 91:　@CD<BR>
     * 92:　@CP<BR>
     * 93:　@BA<BR>
     * 99:　@金銭<BR>
     * <BR>
     * ※nullの場合は、"99:　@金銭"を表示する。<BR>
     * (*1)"11:　@信用"の場合はthis.弁済区分が、<BR>
     * 　@　@nullの場合は"信用"、<BR>
     * 　@　@"1:制度信用"の場合は"信用半年"、<BR>
     * 　@　@"3:一般信用"の場合は"信用無期"と表示する。<BR>
     * <BR>
     */
    public String commodityCode;
    
    /**
     * (弁済区分)<BR>
     * 1：　@制度信用<BR>
     * 3：　@一般信用<BR>
     */
    public String repaymentDiv;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (口座区分)<BR>
     * 0：　@一般<BR>
     * 1：　@特定<BR>
     * <BR>
     */
    public String taxType;
    
    /**
     * (翻訳摘要名)<BR>
     * 翻訳摘要名<BR>
     */
    public String remarkName;
    
    /**
     * (数量)<BR>
     * 数量<BR>
     */
    public String quantity;
    
    /**
     * (数量単位)<BR>
     * 数量単位<BR>
     */
    public String quantityUnit;
    
    /**
     * (単価)<BR>
     * 単価<BR>
     */
    public String price;
    
    /**
     * (売買区分)<BR>
     * 1：現物:売付,信用先物:売建買返済<BR>
     * 2：現物:買付,信用先物:買建売返済<BR>
     */
    public String historyDealingType;
    
    /**
     * (受渡金額)<BR>
     * 受渡金額<BR>
     */
    public String deliveryAmount;
    
    /**
     * (譲渡損益)<BR>
     * 譲渡損益<BR>
     */
    public String capitalProfitLoss;
    
    /**
     * (明細管理番号)<BR>
     * 明細管理番号<BR>
     * ※画面には表示しない。<BR>
     */
    public String detailsManageNo;
    
    /**
     * (簿価単価明細リンクフラグ)<BR>
     * 簿価単価明細リンクフラグ<BR>
     * <BR>
     * false：　@リンクなし<BR>
     * true：　@リンクあり<BR>
     */
    public boolean bookValueLink;
       
    /**
     * (出入区分)<BR>
     * 出入区分<BR>
     * １：出<BR>　@
     * ２：入<BR>
     */
    public String ioDiv;
    
    /**
     * (取引コード)<BR>
     * 取引コード<BR>
     * SONARから取得<BR>
     */
    public String tradeCode;
    
    /**
     * (摘要コード)<BR>
     * 摘要コード<BR>
     * SONARから取得<BR>
     */
    public String remarkCode;
    
    /**
     * (通貨単位)<BR>
     * 通貨単位<BR>
     * ブランク:円　@ブランク×２:円  A0:US$　@A1:C$　@A2:A$<BR>
     * A3:HK$　@A4:S$  A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS M0:DKr　@M1:NKr<BR>
     * M2:SKr　@N0:Pts　@T0:円  T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     */
    public String monetaryUnit;

    /**
     * (決済区分)<BR>
     * 決済区分
     * <BR>
     * 0 or null：　@円貨決済<BR>
     * 1：　@外貨決済<BR>
     */
    public String settleDiv;

    /**
     * @@roseuid 41789C4C0167
     */
    public WEB3HistoryTradeHistoryUnit() 
    {
     
    }
}
@
