head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXResultNoticeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX結果通知共通リクエスト(WEB3FXResultNoticeCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX結果通知共通リクエスト) <BR>
 * FX結果通知共通リクエストクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXResultNoticeCommonRequest extends WEB3GenRequest
{
    /**
     * (GFT結果通知電文明細) <BR>
     * GFT結果通知電文の明細 <BR>
     */
    public WEB3FXGftResultNoticeTelegramUnit fxGftResultNoticeTelegramUnit;

    /**
     * @@roseuid 41E7693203B9
     */
    public WEB3FXResultNoticeCommonRequest()
    {
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXResultNoticeCommonRequest.class);

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １） GFT結果通知電文明細チェック <BR>
     * this.GFT結果通知電文明細＝nullの場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * ※GFT結果通知電文明細の内容については、サービス内にてチェックを行う。 <BR>
     * 
     * @@roseuid 41BE4C720015
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // １） GFT結果通知電文明細チェック 
        // this.GFT結果通知電文明細＝nullの場合、例外をthrowする。  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309 
        if (this.fxGftResultNoticeTelegramUnit == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.GFT結果通知電文明細 = null"); 
        }
        
        log.exiting(l_strMethodName);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E76933002E
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}@
