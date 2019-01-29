head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTradeAgreementResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX取引同意レスポンス(WEB3FXTradeAgreementResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/8/28 韋念瓊 (中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX取引同意レスポンス) <BR>
 * FX取引同意レスポンスクラス <BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FXTradeAgreementResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trade_agreement";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200508281026L;

    /**
     * (顧客名) <BR>
     * 顧客名 <BR>
     */
    public String accountName;

    /**
     * @@roseuid 41E780B201C5
     */
    public WEB3FXTradeAgreementResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FXTradeAgreementResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}@
