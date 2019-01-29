head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.50.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcEstimatedAmountCalService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス受渡代金計算サービス(WEB3TrialCalcEstimatedAmountCalService.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * （計算サービス受渡代金計算サービス）<BR>
 * <BR>
 * 受渡代金計算サービスインタフェース。<BR>
 * <BR>
 * WEB3TrialCalcEstimatedAmountCalService<BR>
 * @@author Saravanan Krishnamoorthy
 * @@version 1.0
 */
public interface WEB3TrialCalcEstimatedAmountCalService extends WEB3BusinessService
{
    /**
    * 受渡代金計算サービス処理を実施する。<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * Delivery price calculation service processing is executed. <BR>
    * @@param l_request - リクエストデータ。
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException
    * システム共通（web3-common）.(web3)システム実装クラス_common.WEB3BaseException
    * @@roseuid 4190A5CE0020
    */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
