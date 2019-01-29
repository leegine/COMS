head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資共通リクエストクラス(WEB3RuitoCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 累積投資共通リクエスト<BR>
 */
public class WEB3RuitoCommonRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_common";

    /**
     * 銘柄コード<BR>
     */
    public String ruitoProductCode;

    /**
     * 注文数量<BR>
     */
    public String ruitoOrderQuantity = null;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922C33035B
     */
    public WEB3RuitoCommonRequest()
    {

    }

    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40B6F7600222
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
