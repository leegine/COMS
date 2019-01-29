head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcBreakEvenPointInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス損益分岐点計算入力リクエスト(WEB3TrialCalcBreakEvenPointInputRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （計算サービス損益分岐点計算入力リクエスト）<BR>
 * <BR>
 * 計算サービス損益分岐点計算サービス（入力画面表示）のリクエストデータ。<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3TrialCalcBreakEvenPointInputRequest<BR>
 * @@author Prabhu
 * @@version 1.0
 *
 * <BR>
 */
public class WEB3TrialCalcBreakEvenPointInputRequest extends WEB3GenRequest
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
    * @@roseuid 41C815A6030E
    */
   public WEB3TrialCalcBreakEvenPointInputRequest()
   {
   }

   /**
    * レスポンスデータを作成する。<BR>
    * @@return webbroker3.common.message.WEB3GenResponse
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3TrialCalcBreakEvenPointInputResponse(this);
   }
}
@
