head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcBreakEvenPointInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス損益分岐点計算入力レスポンス(WEB3TrialCalcBreakEvenPointInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （計算サービス損益分岐点計算入力レスポンス）<BR>
 * <BR>
 * 計算サービス損益分岐点計算サービス（入力画面表示）のレスポンスデータ。<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3TrialCalcBreakEvenPointInputResponse<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3TrialCalcBreakEvenPointInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_breakevenpoint_input";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200503301100L;

   /**
    * @@roseuid 41C8158B004F
    */
   public WEB3TrialCalcBreakEvenPointInputResponse()
   {

   }

   /**
    *
    * @@param l_request WEB3TrialCalcBreakEvenPointInputRequest
    */
   public WEB3TrialCalcBreakEvenPointInputResponse(WEB3TrialCalcBreakEvenPointInputRequest
    l_request)
   {
        super(l_request);
   }
}
@
