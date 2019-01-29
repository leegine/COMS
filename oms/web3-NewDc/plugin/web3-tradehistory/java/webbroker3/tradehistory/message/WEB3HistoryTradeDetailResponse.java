head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引明細レスポンス(WEB3HistoryTradeDetailResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25  温 顕 法@(中訊) 新規作成
                   2006/10/19  張騰宇 (中訊) モデル 057
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (取引明細レスポンス)<BR>
 * 取引明細レスポンスクラス<BR>
 * 
 * @@author 温 顕 法@
 * @@version 1.0
 */
public class WEB3HistoryTradeDetailResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tradeHistory_tradeDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410221708L;
        
    /**
     * (翻訳摘要名)<BR>
     * 翻訳摘要名<BR>
     */
    public String remarkName;
    
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
     * (市場区分)<BR>
     * 1：　@東証<BR>
     * 2：　@大証<BR>
     * 3：　@名証<BR>
     * 4：　@札証<BR>
     * 5：　@NNM<BR>
     * 6：　@JASDAQ<BR>
     * 9：　@福証<BR>
     */
    public String marketDiv;
    
    /**
     * (数量)<BR>
     * 数量<BR>
     */
    public String quantity;
    
    /**
     * (単価)<BR>
     * 単価<BR>
     */
    public String price;
    
    /**
     * (口座区分)<BR>
     * 0：　@一般<BR>
     * 1：　@特定<BR>
     */
    public String taxType;
    
    /**
     * (約定日)<BR>
     * 約定日<BR>
     */
    public Date execDate;
    
    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date deliveryDate;
    
    /**
     * (約定金額)<BR>
     * 約定金額<BR>
     */
    public String execAmount;
    
    /**
     * (手数料)<BR>
     * 手数料<BR>
     */
    public String commission;
    
    /**
     * (手数料消費税)<BR>
     * 手数料消費税<BR>
     */
    public String commissionTax;
    
    /**
     * (受渡金額)<BR>
     * 受渡金額<BR>
     */
    public String deliveryAmount;
    
    /**
     * (商品コード)<BR>
     * 商品コード<BR>
     */
    public String commodityCode;

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
     * (市場区分（外株）)<BR>
     * 市場区分（外株）<BR>
     * N1：香港 N2：深セン X1：上海<BR>
     */
    public String fstkExchDiv;

    /**
     * (約定金額（外貨）)<BR>
     * 約定金額（外貨）<BR>
     * 小数部6桁あり<BR>
     */
    public String monetaryUnitExecutedAmount;

    /**
     * (現地手数料（外貨）)<BR>
     * 現地手数料（外貨）<BR>
     * 小数部6桁あり<BR>
     */
    public String monetaryUnitComission;

    /**
     * (現地取引税（外貨）)<BR>
     * 現地取引税（外貨）<BR>
     * 小数部6桁あり<BR>
     */
    public String monetaryUnitTradeTax;

    /**
     * (その他手数料（外貨）)<BR>
     * その他手数料（外貨）<BR>
     * 小数部6桁あり<BR>
     */
    public String monetaryUnitInterest;

    /**
     * (現地受渡代金（外貨）)<BR>
     * 現地受渡代金（外貨）<BR>
     * 小数部6桁あり<BR>
     */
    public String localSettleAmount;
    
    /**
     * (現地受渡代金（円貨）)<BR>
     * 現地受渡代金（円貨）<BR>
     * 小数部6桁あり<BR>
     */
    public String localSettleAmountYen;
    
    /**
     * (約定為替)<BR>
     * 約定為替<BR>
     * 小数部7桁あり<BR>
     */
    public String execExchange;

    /**
     * (国内手数料（円貨）)<BR>
     * 国内手数料（円貨）<BR>
     * 小数部6桁あり<BR>
     */
    public String domesticCommission;

    /**
     * (額面)<BR>
     * 額面<BR>
     */
    public String faceAmount;

    /**
     * (決済区分)<BR>
     * 決済区分<BR>
     * <BR>
     * 0 or null ：円貨決済<BR>
     * 1：外貨決済<BR>
     */
    public String settleDiv;

    /**
     * (経過利子(円貨))<BR>
     * 経過利子(円貨)<BR>
     */
    public String yenAccruedInterest;

    /**
     * (経過利子(外貨))<BR>
     * 経過利子(外貨)<BR>
     */
    public String foreignAccruedInterest;

    /**
     * @@roseuid 41789C4B00CB
     */
    public WEB3HistoryTradeDetailResponse() 
    {
     
    }

    /**
     * デフォルトコンストラクタ<BR>
     * デフォルトコンストラクタ 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3HistoryTradeDetailResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
