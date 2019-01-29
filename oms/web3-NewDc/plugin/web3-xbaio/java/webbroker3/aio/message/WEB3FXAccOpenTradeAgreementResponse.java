head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenTradeAgreementResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX口座開設取引同意レスポンス(WEB3FXAccOpenTradeAgreementResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 屈陽 (中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX口座開設取引同意レスポンス) <BR>
 * FX口座開設取引同意レスポンスクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXAccOpenTradeAgreementResponse extends WEB3GenResponse
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
     * (顧客名) <BR>
     * 顧客名 <BR>
     */
    public String accountName;

    /**
     * (取引同意質問情報一覧) <BR>
     * 取引同意質問情報の一覧 <BR>
     */
    public WEB3FXTradeAgreementUnit[] fxTradeAgreementList;

    /**
     * @@roseuid 41E783E30271
     */
    public WEB3FXAccOpenTradeAgreementResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FXAccOpenTradeAgreementResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
