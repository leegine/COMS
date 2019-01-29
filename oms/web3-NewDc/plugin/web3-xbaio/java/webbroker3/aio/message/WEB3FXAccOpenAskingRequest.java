head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenAskingRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX口座開設依頼リクエスト(WEB3FXAccOpenAskingRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 屈陽 (中訊) 新規作成   
                   2006/2/9 余新敏(中訊) 仕様変更・モデル457
                   2006/2/23 玉岡(SRA) 仕様変更・モデル499
                   2006/3/27 玉岡(SRA) 仕様変更・モデル523
Revesion History : 2008/05/19 柴双紅(中訊) 仕様変更 モデルNo.865
Revesion History : 2009/10/09 張騰宇(中訊) 仕様変更 モデルNo.1236
*/

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioAgreementDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX口座開設依頼リクエスト) <BR>
 * FX口座開設依頼リクエストクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXAccOpenAskingRequest extends WEB3FXAskingCommonRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_acc_open_asking";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (FXメールアドレス) <BR>
     * 為替保証金取引用に登録するメールアドレス <BR>
     */
    public String fxMailAddress;

    /**
     * (FX暗証番号) <BR>
     * 為替保証金取引用に登録する暗証番号 <BR>
     */
    public String fxPassword;

    /**
     * (FX取引同意質問情報一覧) <BR>
     * FX取引同意質問情報の一覧 <BR>
     */
    public WEB3FXTradeAgreementUnit[] fxTradeAgreementList;

    /**
     * (暗証番号) <BR>
     * WEB3暗証番号 <BR>
     */
    public String password;

    /**
     * (約諾書区分)<BR>
     * 約諾書区分<BR>
     * <BR>
     * 0： 未送信<BR>
     * 1： 送信済<BR>
     * 2： 受領済<BR>
     */
    public String agreementDiv;

    /**
     * (FXシステムコード)<BR>
     * FXシステムコード
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E78299000F
     */
    public WEB3FXAccOpenAskingRequest()
    {
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenAskingRequest.class);

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １） スーパークラスのvalidateメソッドを呼び出す。 <BR>
     * <BR>
     * ２） FXメールアドレスチェック <BR>
     * 以下のいずれかに当てはまる場合、例外をthrowする。 <BR>
     * <BR>
     * ２－１） this.FXメールアドレス＝null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01700 <BR>
     * <BR>
     * ２－２） this.FXメールアドレス<BR>
     *   ≠適切な内容（WEB3StringTypeUtility.isMailAddress ()＝false） <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00777 <BR>
     * <BR>
     * ３） FX暗証番号チェック <BR>
     * 以下のいずれかに当てはまる場合、例外をthrowする。 <BR>
     * <BR>
     * ３－１） this.FX暗証番号＝null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01090 <BR>
     * <BR>
     * ３－２） this.FX暗証番号≠半角英数字 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01302 <BR>
     * <BR>
     * ３－３） this.FX暗証番号＜4桁 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * ３－４） this.FX暗証番号＞12桁 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * ４）　@約諾書区分チェック<BR>
     * 　@以下の値以外の場合、例外をスローする。<BR>
     * 　@　@・”未送信”<BR>
     * 　@　@・”送信済”<BR>
     * 　@　@・”受領済”<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02348<BR>
     * <BR>
     * ５） FX取引同意質問情報チェック <BR>
     * this.FX取引同意質問情報一覧＝nullの場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * ６） FX取引同意質問情報内容チェック <BR>
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
     * 
     * @@roseuid 41C95A9E033D
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // １） スーパークラスのvalidateメソッドを呼び出す。 
        super.validate();
 
        // ２） FXメールアドレスチェック 
        // 以下のいずれかに当てはまる場合、例外をthrowする。  
        // ２－１） this.FXメールアドレス＝null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01700 
        if (WEB3StringTypeUtility.isEmpty(this.fxMailAddress))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01700,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FXメールアドレス = null"); 
        }
         
        // ２－２） this.FXメールアドレス
        //   ≠適切な内容（WEB3StringTypeUtility.isMailAddress ()＝false）  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00777
        if (!WEB3StringTypeUtility.isMailAddress(this.fxMailAddress))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FXメールアドレス≠適切な内容" + 
                "リクエストデータ.FXメールアドレス = " + this.fxMailAddress); 
        }
 
        // ３） FX暗証番号チェック 
        // 以下のいずれかに当てはまる場合、例外をthrowする。  
        // ３－１） this.FX暗証番号＝null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01090
        if (WEB3StringTypeUtility.isEmpty(this.fxPassword))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FX暗証番号＝null"); 
        } 
 
        // ３－２）this.FX暗証番号≠半角英数字 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01302 
        if (!WEB3StringTypeUtility.isLetterOrDigit(this.fxPassword))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01302,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.this.FX暗証番号≠半角英数字 " + 
                "リクエストデータ.FX暗証番号 = " + this.fxPassword); 
        }
 
        // ３－３） this.FX暗証番号＜4桁 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01784 
        if (this.fxPassword.length() < 4)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01784,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FX暗証番号＜4桁" +
                "リクエストデータ.FX暗証番号 = " + this.fxPassword); 
        }
 
        // ３－４） this.FX暗証番号＞12桁  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01784 
        if (this.fxPassword.length() > 12)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01784,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FX暗証番号＞12桁" + 
                "リクエストデータ.FX暗証番号 = " + this.fxPassword); 
        }
 
        //４）　@約諾書区分チェック
        //以下の値以外の場合、例外をスローする。
        //・”未送信”
        //・”送信済”
        //・”受領済”
        if(!(WEB3AioAgreementDivDef.NOT_SEND.equals(this.agreementDiv)
           || WEB3AioAgreementDivDef.SENDED.equals(this.agreementDiv)
           || WEB3AioAgreementDivDef.RECIEVED.equals(this.agreementDiv)))           
        {
            log.debug("約諾書区分の値が不正です。");
            log.exiting(l_strMethodName);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02348,
                this.getClass().getName() + "." + l_strMethodName, 
                "約諾書区分の値が不正です。");
        }
        
        // ５） FX取引同意質問情報チェック 
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

        // ６） FX取引同意質問情報内容チェック 
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
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E7829802FD
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXAccOpenAskingResponse(this);
    }
}@
