head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenTradeAgreementRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX口座開設取引同意リクエスト(WEB3FXAccOpenTradeAgreementRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 屈陽 (中訊) 新規作成   
Revesion History : 2008/05/19 柴双紅(中訊) 仕様変更 モデルNo.865
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX口座開設取引同意リクエスト) <BR>
 * FX口座開設取引同意リクエストクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXAccOpenTradeAgreementRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_acc_open_trade_agreement";

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
     * @@roseuid 41E784690109
     */
    public WEB3FXAccOpenTradeAgreementRequest()
    {
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E784690148
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXAccOpenTradeAgreementResponse(this);
    }
}@
