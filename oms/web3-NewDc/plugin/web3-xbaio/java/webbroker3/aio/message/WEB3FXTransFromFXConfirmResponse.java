head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXから振替確認レスポンス(WEB3FXTransFromFXConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 屈陽 (中訊) 新規作成
                 : 2006/04/26 李小健 (中訊) 仕様変更・モデル537
Revesion History : 2009/06/25 柴双紅 (中訊) モデルNo.1166
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FXから振替確認レスポンス) <BR>
 * FXから振替確認レスポンスクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTransFromFXConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_from_fx_confirm";

    /**
     * (受渡日) <BR>
     * 受渡日 <BR>
     */
    public Date deliveryDate;

    /**
     * (FXから振替可能額情報)<BR>
     * FXから振替可能額情報<BR>
     */
    public WEB3FXTransferAbleAmtUnit fxTransferAbleAmtUnit;

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * @@roseuid 41E7721303D8
     */
    public WEB3FXTransFromFXConfirmResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FXTransFromFXConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}@
