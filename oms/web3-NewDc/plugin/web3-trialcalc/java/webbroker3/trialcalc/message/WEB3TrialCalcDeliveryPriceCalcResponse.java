head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcDeliveryPriceCalcResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス受渡代金計算レスポンス(WEB3TrialCalcDeliveryPriceCalcResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （計算サービス受渡代金計算レスポンス）<BR>
 * <BR>
 * 計算サービス受渡代金計算サービスのレスポンスデータ。<BR>
 * <BR>
 * WEB3TrialCalcDeliveryPriceCalcResponse<BR>
 * @@author saravanan
 * @@version 1.0
 */
public class WEB3TrialCalcDeliveryPriceCalcResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_estimatedamount_calc";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200503301100L;

    /**
     * （銘柄名）<BR>
     * <BR>
     * productName<BR>
     */
    public String productName;

    /**
     * （時価取得区分）<BR>
     * <BR>
     * 時価取得区分。<BR>
     * （1：時価　@2：前日終値　@3：当日終値）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * currentPriceGetDiv<BR>
     * (1 : Current price  2 : Last close price  3 : Today close price)<BR>
     * <BR>
     */
    public String currentPriceGetDiv;

    /**
     * （時価）<BR>
     * <BR>
     * 時価。<BR>
     * （時価取得時（リクエスト.株単価＝null）のみセット）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * currentPrice<BR>
     * (Only set when current price is acquired.（リクエスト.株単価＝null）)<BR>
     */
    public String currentPrice;

    /**
     * （取引時間（時価発表時間））<BR>
     * <BR>
     * 取引時間（時価発表時間）。<BR>
     * （時価取得時（リクエスト.株単価＝null）のみセット）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * currentPrice<BR>
     * (Only set when current price is acquired.（リクエスト.株単価＝null）)<BR>
     * <BR>
     */
    public Date currentPriceTime;

    /**
     * （市場コード）<BR>
     * <BR>
     * 市場コード。<BR>
     * （1：東京　@2：大阪　@3：名古屋　@6：福岡　@8：札幌　@9：NNM　@10：JASDAQ）<BR>
     * （市場コード指定なし時のみセット）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market code.<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  8 : SAPPORO<BR>
     *  9 : NNM   10 : JASDAQ)<BR>
     * <BR>
     * <BR>
     */
    public String marketCode;

    /**
     * （手数料）<BR>
     * <BR>
     * commission<BR>
     */
    public String commission;

    /**
     * （手数料消費税）<BR>
     * <BR>
     * commissionTax<BR>
     */
    public String commissionTax;

    /**
     * （手数料コース）<BR>
     * <BR>
     * 手数料コース。<BR>
     * （02：定額手数料(スタンダード)　@03：約定代金合計　@04：約定回数<BR>
     * 　@05：一日定額制　@12：定額手数料(ハイパーボックス)）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * commissionCourse<BR>
     * (02 : Fixed commission(standard)  03 : Contract price total<BR>
     *  04 : Contract frequency             05 : One day fixed charge system<BR>
     *  12 : Fixed commission(hyper box))<BR>
     * <BR>
     */
    public String commissionCourse;

    /**
     * （受渡代金）<BR>
     * <BR>
     * deliveryPrice<BR>
     */
    public String deliveryPrice;

    /**
     * @@roseuid 41C813C80252
     */
    public WEB3TrialCalcDeliveryPriceCalcResponse()
    {
    }

    /**
     *
     * @@param l_request of WEB3TrialCalcDeliveryPriceCalcRequest
     */
    public WEB3TrialCalcDeliveryPriceCalcResponse(WEB3TrialCalcDeliveryPriceCalcRequest l_request)
    {
        super(l_request);
    }
}
@
