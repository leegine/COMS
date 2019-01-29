head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXへの振替入力レスポンス(WEB3FXTransToFXInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 屈陽 (中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FXへの振替入力レスポンス) <BR>
 * FXへの振替入力レスポンスクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTransToFXInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_to_fx_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (FX口座情報一覧) <BR>
     * FX口座情報の一覧 <BR>
     */
    public WEB3FXAccInformationUnit[] fxAccInformationList;

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
     * (振替可能額) <BR>
     * 顧客の現時点での振替可能額 <BR>
     */
    public String transferableAmt;

    /**
     * @@roseuid 41E780B201C5
     */
    public WEB3FXTransToFXInputResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FXTransToFXInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}@
