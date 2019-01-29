head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransitionReferenceUseQuoteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 余力推移(時価計算)画面表示レスポンス(WEB3TPTransitionReferenceUseQuoteResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/01/31 新規作成
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (余力推移(時価計算)画面表示レスポンス)<BR>
 * 余力推移(時価計算)画面表示レスポンスクラス。<BR>
 * 
 */
public class WEB3TPTransitionReferenceUseQuoteResponse extends WEB3TPTransitionReferenceResponse 
{

    public static final String PTYPE = "tradingpower_transition_reference_use_quote";

    /**
     * コンストラクタ
     * @@param l_request
     */
    protected WEB3TPTransitionReferenceUseQuoteResponse(WEB3GenRequest l_request) 
    {
         super( l_request );
    }
   
    /**
     * コンストラクタ
     */
    public WEB3TPTransitionReferenceUseQuoteResponse() 
    {
    
    }

}
@
