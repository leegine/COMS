head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXから振替入力レスポンス(WEB3FXTransFromFXInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 屈陽 (中訊) 新規作成
Revesion History : 2009/06/25 柴双紅 (中訊) モデルNo.1166
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FXから振替入力レスポンス) <BR>
 * FXから振替入力レスポンスクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTransFromFXInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_from_fx_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (FXから振替可能額情報一覧)<BR>
     * FXから振替可能額情報の一覧<BR>
     */
    public WEB3FXTransferAbleAmtUnit[] fxTransferAbleAmtList;

    /**
     * (振替上限回数) <BR>
     * 顧客の1日の振替上限回数 <BR>
     */
    public String transferCountUpper;

    /**
     * (振替回数) <BR>
     * 顧客の現時点での1日の振替回数 <BR>
     */
    public String transferCount;

    /**
     * @@roseuid 41E779B20290
     */
    public WEB3FXTransFromFXInputResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FXTransFromFXInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}@
