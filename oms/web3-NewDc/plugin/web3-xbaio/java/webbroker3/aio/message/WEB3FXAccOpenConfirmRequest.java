head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX口座開設確認リクエスト(WEB3FXAccOpenConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 屈陽 (中訊) 新規作成   
                   2006/2/23 玉岡(SRA) 仕様変更・モデル499
                   2006/3/27 玉岡(SRA) 仕様変更・モデル523
Revesion History : 2008/05/19 柴双紅(中訊) 仕様変更 モデルNo.865
Revesion History : 2009/09/25 張騰宇(中訊) 仕様変更 モデルNo.1231
Revesion History : 2009/10/27 張騰宇(中訊) 仕様変更 モデルNo.1244
*/

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioAgreementDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX口座開設確認リクエスト) <BR>
 * FX口座開設確認リクエストクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXAccOpenConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_acc_open_confirm";

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
     * (FXメールアドレス（確認用）) <BR>
     * 為替保証金取引用に登録するメールアドレス（確認用） <BR>
     */
    public String fxMailAddressCnf;

    /**
     * (FX暗証番号) <BR>
     * 為替保証金取引用に登録する暗証番号 <BR>
     */
    public String fxPassword;

    /**
     * (FX暗証番号（確認用）) <BR>
     * 為替保証金取引用に登録する暗証番号（確認用） <BR>
     */
    public String fxPasswordCnf;

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
     * (FX暗証番号２)<BR>
     * FX暗証番号２<BR>
     */
    public String fxPassword2;

    /**
     * (FX暗証番号２(確認用))<BR>
     * FX暗証番号２(確認用)<BR>
     */
    public String fxPassword2Cnf;

    /**
     * @@roseuid 41E78298029F
     */
    public WEB3FXAccOpenConfirmRequest()
    {
    }

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenConfirmRequest.class);

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １） FXメールアドレスチェック <BR>
     * 以下のいずれかに当てはまる場合、例外をthrowする。 <BR>
     * <BR>
     * １－１） this.FXメールアドレス＝null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01700 <BR>
     * <BR>
     * １－２） this.FXメールアドレス（確認用）＝null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01788 <BR>
     * <BR>
     * １－３） this.FXメールアドレス≠this.FXメールアドレス（確認用） <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01789 <BR>
     * <BR>
     * １－４） this.FXメールアドレス≠適切な内容（WEB3StringTypeUtility.isMailAddress ()＝false） <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00777 <BR>
     * <BR>
     * ２） FX暗証番号チェック <BR>
     * 以下のいずれかに当てはまる場合、例外をthrowする。 <BR>
     * <BR>
     * ２－１） this.FX暗証番号＝null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01090 <BR>
     * <BR>
     * ２－２） this.FX暗証番号（確認用）＝null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01790 <BR>
     * <BR>
     * ２－３） this.FX暗証番号≠ this.FX暗証番号（確認用） <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01791 <BR>
     * <BR>
     * ２－４） this.FX暗証番号≠半角英数字 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01302 <BR>
     * <BR>
     * ２－５） this.FX暗証番号＜4桁 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * ２－６） this.FX暗証番号＞12桁 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * ３）　@約諾書区分チェック
     * <BR>
     * 以下の値以外の場合、例外をスローする。<BR>
     * ・”未送信”<BR>
     * ・”送信済”<BR>
     * ・”受領済”<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02348 <BR>
     * <BR>
     * ４）　@FX暗証番号２チェック <BR>
     * 　@FX暗証番号２がnullじゃない場合以下チェックを行う、 <BR>
     * 　@以下のいずれかに当てはまる場合、例外をthrowする。 <BR>
     * <BR>
     * 　@４－１）　@this.FX暗証番号２≠ this.FX暗証番号２（確認用） <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01791 <BR>
     * <BR>
     * 　@４－２）　@this.FX暗証番号２≠半角英数字 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01302 <BR>
     * <BR>
     * 　@４－３）　@this.FX暗証番号２＜8桁 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * 　@４－４）　@this.FX暗証番号２＞12桁 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * 　@４－５）　@this.FX暗証番号２＝this.FX暗証番号 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_03185 <BR>
     * <BR>
     * <BR>
     * @@roseuid 41C6584C01FD
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // １） FXメールアドレスチェック 
        // 以下のいずれかに当てはまる場合、例外をthrowする。  
        // １－１） this.FXメールアドレス＝null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01700 
        if (WEB3StringTypeUtility.isEmpty(this.fxMailAddress))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01700,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FXメールアドレス = null"); 
        }
        
        // １－２） this.FXメールアドレス（確認用）＝null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01788 
        if (WEB3StringTypeUtility.isEmpty(this.fxMailAddressCnf))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01788,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FXメールアドレス（確認用） = null"); 
        }
        
        // １－３） this.FXメールアドレス≠this.FXメールアドレス（確認用）  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01789 
        if (!this.fxMailAddress.equals(this.fxMailAddressCnf))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01789,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FXメールアドレス≠リクエストデータ.FXメールアドレス（確認用）" + 
                "リクエストデータ.FXメールアドレス = " + this.fxMailAddress +
                "リクエストデータ.FXメールアドレス（確認用） = " + this.fxMailAddressCnf); 
        }
 
        // １－４） this.FXメールアドレス≠適切な内容（WEB3StringTypeUtility.isMailAddress ()＝false）  
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
 
        // ２） FX暗証番号チェック 
        // 以下のいずれかに当てはまる場合、例外をthrowする。  
        // ２－１） this.FX暗証番号＝null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01090 
        if (WEB3StringTypeUtility.isEmpty(this.fxPassword))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FX暗証番号＝null"); 
        }
 
        // ２－２） this.FX暗証番号（確認用）＝null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01790 
        if (WEB3StringTypeUtility.isEmpty(this.fxPasswordCnf))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01790,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FX暗証番号（確認用）＝null"); 
        }
 
        // ２－３） this.FX暗証番号≠ this.FX暗証番号（確認用）  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01791 
        if (!this.fxPassword.equals(this.fxPasswordCnf))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01791,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FX暗証番号≠リクエストデータ.FX暗証番号（確認用）" + 
                "リクエストデータ.FX暗証番号 = " + this.fxPassword +
                "リクエストデータ.FX暗証番号（確認用） = " + this.fxPasswordCnf); 
        }
 
        // ２－４） this.FX暗証番号≠半角英数字 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01302 
        if (!WEB3StringTypeUtility.isLetterOrDigit(this.fxPassword))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01302,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FX暗証番号≠半角英数字" + 
                "リクエストデータ.FX暗証番号 = " + this.fxPassword); 
        }
 
        // ２－５） this.FX暗証番号＜4桁  
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
 
        // ２－６） this.FX暗証番号＞12桁  
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
        
        //３）　@約諾書区分チェック
        //以下の値以外の場合、例外をスローする。
        //・”未送信”
        //・”送信済”
        //・”受領済”
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_02348
        //if (this.)
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

        //４）　@FX暗証番号２チェック
        //　@FX暗証番号２がnullじゃない場合以下チェックを行う、
        //　@以下のいずれかに当てはまる場合、例外をthrowする。
        //　@４－１）　@this.FX暗証番号２≠ this.FX暗証番号２（確認用）
        if (this.fxPassword2 != null)
        {
            if (!this.fxPassword2.equals(this.fxPassword2Cnf))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01791,
                    this.getClass().getName() + "." + l_strMethodName,
                    "リクエストデータ.FX暗証番号２≠リクエストデータ.FX暗証番号２（確認用）" +
                    "リクエストデータ.FX暗証番号２ = " + this.fxPassword2 +
                    "リクエストデータ.FX暗証番号２（確認用） = " + this.fxPassword2Cnf);
            }

            //　@４－２）　@this.FX暗証番号２≠半角英数字
            if (!WEB3StringTypeUtility.isLetterOrDigit(this.fxPassword2))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01302,
                    this.getClass().getName() + "." + l_strMethodName,
                    "リクエストデータ.FX暗証番号２≠半角英数字" +
                    "リクエストデータ.FX暗証番号２ = " + this.fxPassword2);
            }

            //　@４－３）　@this.FX暗証番号２＜8桁
            if (this.fxPassword2.length() < 8)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01784,
                    this.getClass().getName() + "." + l_strMethodName,
                    "リクエストデータ.FX暗証番号２＜8桁" +
                    "リクエストデータ.FX暗証番号２ = " + this.fxPassword2);
            }

            //　@４－４）　@this.FX暗証番号２＞12桁
            if (this.fxPassword2.length() > 12)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01784,
                    this.getClass().getName() + "." + l_strMethodName,
                    "リクエストデータ.FX暗証番号２＞12桁" +
                    "リクエストデータ.FX暗証番号２ = " + this.fxPassword2);
            }

            //　@４－５）　@this.FX暗証番号２＝this.FX暗証番号
            if (this.fxPassword.equals(this.fxPassword2))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03185,
                    this.getClass().getName() + "." + l_strMethodName,
                    "FX暗証番号２とFX暗証番号が一致です。" +
                    "リクエストデータ.FX暗証番号２ = " + this.fxPassword2);
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
        return new WEB3FXAccOpenConfirmResponse(this);
    }
}@
