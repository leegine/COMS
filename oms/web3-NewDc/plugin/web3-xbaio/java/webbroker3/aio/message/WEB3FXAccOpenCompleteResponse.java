head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX口座開設完了レスポンス(WEB3FXAccOpenCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/19 屈陽 (中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX口座開設完了レスポンス) <BR>
 * FX口座開設完了レスポンスクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXAccOpenCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_acc_open_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (為替保証金ログインID) <BR>
     * 為替保証金取引用ログインID <BR>
     */
    public String fxLoginId;

    /**
     * (為替保証金口座情報一覧) <BR>
     * 為替保証金口座情報の一覧 <BR>
     */
    public WEB3FXAccInformationUnit[] fxAccInformationList;

    /**
     * @@roseuid 41E7829901F4
     */
    public WEB3FXAccOpenCompleteResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FXAccOpenCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}@
