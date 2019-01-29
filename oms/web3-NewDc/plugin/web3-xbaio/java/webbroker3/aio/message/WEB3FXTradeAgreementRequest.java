head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTradeAgreementRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX取引同意リクエスト(WEB3FXTradeAgreementRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/8/28 韋念瓊 (中訊) 新規作成   
Revesion History : 2008/05/19 柴双紅(中訊) 仕様変更 モデルNo.865
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX取引同意リクエスト) <BR>
 * FX取引同意リクエストクラス <BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FXTradeAgreementRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trade_agreement";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (FXシステムコード)<BR>
     * FXシステムコード
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E780B2008C
     */
    public WEB3FXTradeAgreementRequest()
    {
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E780B200CB
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXTradeAgreementResponse(this);
    }
}@
