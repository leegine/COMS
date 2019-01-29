head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcDeliveryPriceCalcInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス受渡代金計算入力レスポンス(WEB3TrialCalcDeliveryPriceCalcInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （計算サービス受渡代金計算入力レスポンス）<BR>
 * <BR>
 * 計算サービス受渡代金計算サービス（入力画面表示）のレスポンスデータ。<BR>
 * <BR>
 * WEB3TrialCalcDeliveryPriceCalcInputResponse<BR>
 * @@author saravanan
 * @@version 1.0
 */
public class WEB3TrialCalcDeliveryPriceCalcInputResponse extends WEB3GenResponse
{
    /**
        * PTYPE <BR>
        */
    public final static String PTYPE = "trialcalc_estimatedamount_calcinput";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200503301100L;

    /**
     * （市場コードの一覧）<BR>
     * <BR>
     * 市場コードの一覧。<BR>
     * （1：東京　@2：大阪　@3：名古屋　@6：福岡　@8：札幌　@9：NNM　@10：JASDAQ）<BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market code list.<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  8 : SAPPORO<BR>
     *  9 : NNM   10 : JASDAQ)<BR>
     */
    public String[] marketCodeList;

    /**
     * @@roseuid 41C813D101C6
     */
    public WEB3TrialCalcDeliveryPriceCalcInputResponse()
    {
    }

    /**
     * @@roseuid 41C813D101C6
     * @@param l_request is object of WEB3TrialCalcDeliveryPriceCalcInputRequest
     */
    public WEB3TrialCalcDeliveryPriceCalcInputResponse(WEB3TrialCalcDeliveryPriceCalcInputRequest
        l_request)
    {
        super(l_request);
    }
}@
