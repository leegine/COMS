head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAfterRepayTransitionReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (返済後余力)余力推移画面表示レスポンス(WEB3TPAfterRepayTransitionReferenceResponse.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/04/08 nakazato(ACT) 新規作成
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * ((返済後余力)余力推移画面表示レスポンス)
 */
public class WEB3TPAfterRepayTransitionReferenceResponse
        extends WEB3TPTransitionReferenceResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "afterrepay_transition_reference";

    /**
     * (コンストラクタ)
     * 
     * @@param l_request
     * @@roseuid 4255D81E0010
     */
    protected WEB3TPAfterRepayTransitionReferenceResponse(
            WEB3GenRequest l_request)
    {
        super(l_request);
    }

    /**
     * (コンストラクタ)
     * 
     * @@roseuid 4255D81E002F
     */
    public WEB3TPAfterRepayTransitionReferenceResponse()
    {

    }
}@
