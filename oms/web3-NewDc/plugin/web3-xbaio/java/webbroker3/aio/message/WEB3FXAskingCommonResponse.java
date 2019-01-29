head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAskingCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX依頼共通レスポンス(WEB3FXAskingCommonResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 屈陽 (中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX依頼共通レスポンス) <BR>
 * FX依頼共通レスポンスクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXAskingCommonResponse extends WEB3GenResponse
{
    /**
     * (URL) <BR>
     * 為替保証金管理システム（GFT）のURL <BR>
     */
    public String fxUrl;

    /**
     * (GFT依頼電文明細) <BR>
     * GFT依頼電文の明細 <BR>
     */
    public WEB3FXGftAskingTelegramUnit fxGftAskingTelegramUnit;

    /**
     * @@roseuid 41E7693500FA
     */
    public WEB3FXAskingCommonResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FXAskingCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}@
