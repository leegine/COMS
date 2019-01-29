head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXAskingRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXから振替依頼リクエスト(WEB3FXTransFromFXAskingRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 屈陽 (中訊) 新規作成   
                 : 2006/04/26 李小健 (中訊) 仕様変更・モデル533
                 : 2008/05/21 三島淳一郎 (SCS) 桁数チェック修正
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FXから振替依頼リクエスト) <BR>
 * FXから振替依頼リクエストクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTransFromFXAskingRequest extends WEB3FXAskingCommonRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_from_fx_asking";

    /**
     * (FXシステムコード)<BR>
     * FXシステムコード<BR>
     */
    public String fxSystemCode;
    
    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (FX口座情報) <BR>
     * 振替を行うFX口座情報 <BR>
     */
    public WEB3FXAccInformationUnit fxAccInformationUnit;

    /**
     * (振替金額) <BR>
     * 画面から入力された振替金額 <BR>
     */
    public String transferAmount;

    /**
     * (暗証番号) <BR>
     * 画面から入力された暗証番号 <BR>
     */
    public String password;

    /**
     * @@roseuid 41E788110138
     */
    public WEB3FXTransFromFXAskingRequest()
    {
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransFromFXAskingRequest.class);

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １） スーパークラスのvalidateメソッドを呼び出す。 <BR>
     * <BR>
     * ２） FX口座情報チェック <BR>
     * ２－１） this.FX口座情報＝nullの場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * ２－２） this.FX口座情報.validateメソッドを呼び出す。 <BR>
     * <BR>
     * ３） 振替金額チェック <BR>
     * 以下のいずれかに当てはまる場合、例外をthrowする。 <BR>
     * ３－１） this.振替金額＝null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00759 <BR>
     * <BR>
     * ３－２） this.振替金額≠数字 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00811 <BR>
     * <BR>
     * ３－３） this.振替金額≦0 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00809 <BR>
     * <BR>
     * ３－４） this.振替金額＞9桁 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00810 <BR>
     * <BR>
     * 
     * @@roseuid 41BEC94A00EA
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // １） スーパークラスのvalidateメソッドを呼び出す。 
        super.validate();
 
        // ２） FX口座情報チェック 
        // ２－１） this.FX口座情報＝nullの場合、例外をthrowする。  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309 
        if (this.fxAccInformationUnit == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FX口座情報 = null"); 
        }
 
        // ２－２） this.FX口座情報.validateメソッドを呼び出す。 
        this.fxAccInformationUnit.validate();

        // ３） 振替金額チェック 
        // 以下のいずれかに当てはまる場合、例外をthrowする。 
        // ３－１） this.振替金額＝null 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00759 
        if (WEB3StringTypeUtility.isEmpty(this.transferAmount))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00759,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.振替金額＝null"); 
        }
 
        // ３－２） this.振替金額≠数字  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00811 
        if (!WEB3StringTypeUtility.isNumber(this.transferAmount))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00811,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.FX暗証番号≠数字" + 
                "リクエストデータ.FX暗証番号 = " + this.transferAmount); 
        }
 
        // ３－３） this.振替金額≦0  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00809 
        if (Double.parseDouble(this.transferAmount) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00809,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.振替金額≦0" + 
                "リクエストデータ.振替金額 = " + this.transferAmount); 
        }

        // ３－４） this.振替金額＞9桁 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00810 
        if (this.transferAmount.length() > 9)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00810,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.振替金額＞9桁" + 
                "リクエストデータ.振替金額 = " + this.transferAmount.length()); 
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E7829802FD
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXTransFromFXAskingResponse(this);
    }
}@
