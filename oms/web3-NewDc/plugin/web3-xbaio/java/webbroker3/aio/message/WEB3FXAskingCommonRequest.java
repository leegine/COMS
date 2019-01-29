head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAskingCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX依頼共通リクエスト(WEB3FXAskingCommonRequest)
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
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX依頼共通リクエスト) <BR>
 * FX依頼共通リクエストクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXAskingCommonRequest extends WEB3GenRequest
{
    /**
     * (WOLFセッションキー) <BR>
     * WOLFセッションキー <BR>
     * （WEB3にて独自に設定している項目） <BR>
     */
    public String wolfSession;

    /**
     * (アプリケーションID) <BR>
     * アプリケーションID <BR>
     * （WEB3にて独自に設定している項目） <BR>
     */
    public String wolfAid;

    /**
     * (再生成サービスID) <BR>
     * 再生成サービスID <BR>
     * （WEB3にて独自に設定している項目） <BR>
     */
    public String regetServiceId;

    /**
     * (SSID) <BR>
     * SSID <BR>
     * （WEB3にて独自に設定している項目） <BR>
     */
    public String wolfSsid;

    /**
     * @@roseuid 41E772120196
     */
    public WEB3FXAskingCommonRequest()
    {
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAskingCommonRequest.class);


    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １） WOLFセッションキーチェック <BR>
     * this.WOLFセッションキー＝nullの場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * ２） アプリケーションIDチェック <BR>
     * this.アプリケーションID＝nullの場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * ３） 再生成サービスIDチェック <BR>
     * this.再生成サービスID＝nullの場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * ４） SSIDチェック <BR>
     * this.SSID＝nullの場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * 
     * @@roseuid 41BE4AD70332
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // １） WOLFセッションキーチェック 
        // this.WOLFセッションキー＝nullの場合、例外をthrowする。  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309
        if (WEB3StringTypeUtility.isEmpty(this.wolfSession))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.WOLFセッションキー = null"); 
        }
 
        // ２） アプリケーションIDチェック 
        // this.アプリケーションID＝nullの場合、例外をthrowする。  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309
        if (WEB3StringTypeUtility.isEmpty(this.wolfAid))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.アプリケーションID = null"); 
        }

        // ３） 再生成サービスIDチェック 
        // this.再生成サービスID＝nullの場合、例外をthrowする。  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309 
        if (WEB3StringTypeUtility.isEmpty(this.regetServiceId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.再生成サービスID = null"); 
        }
 
        // ４） SSIDチェック 
        // this.SSID＝nullの場合、例外をthrowする。 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309
        if (WEB3StringTypeUtility.isEmpty(this.wolfSsid))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.SSID = null"); 
        }
        
        log.exiting(l_strMethodName);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E772120203
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXAskingCommonResponse(this);
    }
}@
