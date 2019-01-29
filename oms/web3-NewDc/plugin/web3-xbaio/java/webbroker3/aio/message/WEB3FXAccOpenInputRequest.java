head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX口座開設入力リクエスト(WEB3FXAccOpenInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 屈陽 (中訊) 新規作成   
Revesion History : 2008/05/19 柴双紅(中訊) 仕様変更 モデルNo.865
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
 * (FX口座開設入力リクエスト) <BR>
 * FX口座開設入力リクエストクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXAccOpenInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_acc_open_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (FX取引同意質問情報一覧) <BR>
     * FX取引同意質問情報の一覧 <BR>
     */
    public WEB3FXTradeAgreementUnit[] fxTradeAgreementList;

    /**
     * (電子鳩チェックフラグ) <BR>
     * 電子鳩チェックフラグ  <BR>
     * 電子鳩システムへ接続を行うかを設定する。<BR> 
     * <BR>
     * true：接続をする。<BR> 
     * false：接続をしない。<BR>
     */
    public boolean batoCheckFlag;

    /**
     * (種別コード) <BR>
     * 種別コード <BR>
     */
    public String typeCode;

    /**
     * (識別コード) <BR>
     * 識別コード<BR>
     */
    public String[] requestCode;

    /**
     * (FXシステムコード)<BR>
     * FXシステムコード
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E784690232
     */
    public WEB3FXAccOpenInputRequest()
    {
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenInputRequest.class);

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １） FX取引同意質問情報チェック <BR>
     * this.FX取引同意質問情報一覧＝nullの場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * ２） FX取引同意質問情報内容チェック <BR>
     * thisFX取引同意質問情報一覧の要素ごとに以下のチェックを行う。 <BR>
     * <BR>
     * 以下のいずれかに当てはまる場合、例外をthrowする。 <BR>
     * <BR>
     * ・FX取引同意質問情報.質問番号＝nullの場合 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * ・FX取引同意質問情報.質問回答＝nullの場合 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * 電子鳩接続チェックフラグ==trueの場合以下の（３）～（４）のチェックを行う。<BR> 
     * <BR>
     * ３）　@種別コードチェック<BR> 
     * this.種別コード＝nullの場合、例外をthrowする。<BR> 
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02202 <BR>
     * ４）　@識別コードチェック<BR> 
     * this.識別コードの要素数が、「０」の場合、例外をthrowする。<BR> 
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00829 <BR>
     * @@roseuid 41C656B50132
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // １） FX取引同意質問情報チェック 
        // this.FX取引同意質問情報一覧＝nullの場合、例外をthrowする。  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309
        if (fxTradeAgreementList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FX取引同意質問情報一覧＝null"); 
        }
        
        // ２） FX取引同意質問情報内容チェック 
        // thisFX取引同意質問情報一覧の要素ごとに以下のチェックを行う。  
        // 以下のいずれかに当てはまる場合、例外をthrowする。  
        for (int i = 0; i < fxTradeAgreementList.length; i++)
        {
            // ・FX取引同意質問情報.質問番号＝nullの場合  
            // class: WEB3BusinessLayerException 
            // tag: BUSINESS_ERROR_01309 
            if (WEB3StringTypeUtility.isEmpty(fxTradeAgreementList[i].questionNumber))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                    this.getClass().getName() + "." + l_strMethodName,
                    "リクエストデータ.FX取引同意質問情報一覧.質問番号＝null"); 
            }

            // ・FX取引同意質問情報.質問回答＝nullの場合  
            // class: WEB3BusinessLayerException 
            // tag: BUSINESS_ERROR_01309
            if (WEB3StringTypeUtility.isEmpty(fxTradeAgreementList[i].questionAnswer))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                    this.getClass().getName() + "." + l_strMethodName,
                    "リクエストデータ.FX取引同意質問情報一覧.質問回答＝null"); 
            }
        }
        
        //電子鳩接続チェックフラグ==trueの場合以下の（３）～（４）のチェックを行う。 
        if (batoCheckFlag)
        {
            //３）　@種別コードチェック 
            //this.種別コード＝nullの場合、例外をthrowする。
            if (WEB3StringTypeUtility.isEmpty(typeCode))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02202,
                    this.getClass().getName() + "." + l_strMethodName,
                    "種別コードが未指定です。"); 
            }
            
            //４）　@識別コードチェック 
            //this.識別コードの要素数が、「０」の場合、例外をthrowする。 
            if (requestCode == null || requestCode.length == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                    this.getClass().getName() + "." + l_strMethodName,
                    "識別コードが未指定です。"); 
            }
        }
        log.exiting(l_strMethodName);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E784690271
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXAccOpenInputResponse(this);
    }
}@
