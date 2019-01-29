head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.49.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcProfitLossBreakEvenPointCalService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス損益分岐点計算サービス(WEB3TrialCalcProfitLossBreakEvenPointCalService.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * （計算サービス損益分岐点計算サービス）<BR>
 * <BR>
 * 損益分岐点計算サービスインタフェース。<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * Break-even point calculation service interface. <BR>
 * @@author Babu Prasad
 * @@version 1.0
 */
public interface WEB3TrialCalcProfitLossBreakEvenPointCalService extends WEB3BusinessService
{

   /**
    * 損益分岐点計算サービス処理を実施する。<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * Execute the break-even point calculation service processing. <BR>
    * @@param l_request - リクエストデータ。
    * @@return WEB3GenResponse
    * @@throws
    * システム共通（web3-common）.(web3)システム実装クラス_common.WEB3BaseException
    * @@roseuid 419085520014
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
