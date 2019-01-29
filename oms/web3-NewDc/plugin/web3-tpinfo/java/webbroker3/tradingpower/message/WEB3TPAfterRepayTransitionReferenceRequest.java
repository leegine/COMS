head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAfterRepayTransitionReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (返済後余力)信用取引返済注文リクエスト(WEB3TPAfterRepayTransitionReferenceRequest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/04/08 nakazato(ACT) 新規作成
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmRequest;

/**
 * (返済後余力)信用取引返済注文リクエスト
 */
public class WEB3TPAfterRepayTransitionReferenceRequest
        extends WEB3MarginCloseMarginConfirmRequest
{

    /**
     * (PTYPE) <BR>
     */
    public static final String PTYPE = "afterrepay_transition_reference";

    /**
     * (SerialVersionUID) <BR>
     */
    public static final long serialVersionUID = 200504080958L;

    /**
     * (コンストラクタ)
     * 
     * @@roseuid 4255D7BF02C0
     */
    public WEB3TPAfterRepayTransitionReferenceRequest()
    {

    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 4255D6E1004F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3TPAfterRepayTransitionReferenceResponse(this);
    }
}@
