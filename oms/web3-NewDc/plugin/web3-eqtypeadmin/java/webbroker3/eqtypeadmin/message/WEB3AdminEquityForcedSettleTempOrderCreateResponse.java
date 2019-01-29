head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleTempOrderCreateResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済仮注文作成レスポンス(WEB3AdminEquityForcedSettleTempOrderCreateResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 趙林鵬 (中訊) 新規作成
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (強制決済仮注文作成レスポンス)<BR>
 * 強制決済仮注文作成レスポンスクラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleTempOrderCreateResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_forced_settle_temp_order_create";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;
    
    /**
     * @@roseuid 462CA4270014
     */
    public WEB3AdminEquityForcedSettleTempOrderCreateResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminEquityForcedSettleTempOrderCreateResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
