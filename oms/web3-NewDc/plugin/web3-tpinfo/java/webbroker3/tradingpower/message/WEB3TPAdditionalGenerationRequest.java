head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAdditionalGenerationRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 追証発生画面表示リクエスト(WEB3TPAdditionalGenerationRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/10 劉剣（中訊）新規作成 モデルNo.312
*/
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (追証発生画面表示リクエスト) <BR>
 * 追証発生画面表示リクエストクラス。<BR>
 *
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3TPAdditionalGenerationRequest extends WEB3GenRequest
{
    /**
     * (PTYPE) <BR>
     */
    public static final String PTYPE = "additional_generation";

    /**
     * (SerialVersionUID) <BR>
     */
    public static final long serialVersionUID = 200810101032L;

    /**
     * (コンストラクタ) <BR>
     */
    public WEB3TPAdditionalGenerationRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3TPAdditionalGenerationResponse(this);
    }

}
@
