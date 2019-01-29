head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoMRFOrderAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資MRF注文受付レスポンスクラス(WEB3RuitoMRFOrderAcceptResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * 累積投資MRF注文受付レスポンスクラス<BR>
 */
public class WEB3RuitoMRFOrderAcceptResponse extends WEB3BackResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_mrf_order_accept";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;  
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3RuitoMRFOrderAcceptResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }    

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922BCE01B5
     */
    public WEB3RuitoMRFOrderAcceptResponse()
    {

    }
}
@
