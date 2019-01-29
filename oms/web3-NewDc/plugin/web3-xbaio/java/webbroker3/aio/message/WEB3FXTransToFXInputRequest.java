head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXへの振替入力リクエスト(WEB3FXTransToFXInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 屈陽 (中訊) 新規作成   
                 : 2006/04/26 李小健 (中訊) 仕様変更・モデル533
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FXへの振替入力リクエスト) <BR>
 * FXへの振替入力リクエストクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTransToFXInputRequest extends WEB3GenRequest
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
     * (FXシステムコード)<BR>
     * FXシステムコード<BR>
     */
    public String fxSystemCode;
    
    /**
     * @@roseuid 41E780B2008C
     */
    public WEB3FXTransToFXInputRequest()
    {
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E780B200CB
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXTransToFXInputResponse(this);
    }
}@
