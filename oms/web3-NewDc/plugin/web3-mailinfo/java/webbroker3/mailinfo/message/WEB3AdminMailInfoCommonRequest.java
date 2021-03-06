head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.14.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報共通リクエスト(WEB3AdminMailInfoCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
Revesion History : 2004/10/20  奚靖(中訊) 作成
*/
package webbroker3.mailinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (メール情報共通リクエスト)<BR>
 * メール情報共通リクエストクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoCommonRequest extends WEB3GenRequest
{
    /**
    * ログ出力ユーティリティ。
    */
     private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoChangeInputRequest.class);
    
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (送信メール区分)<BR>
     */
    public String sendMailDiv;
    
    /**
     * 識別ID<BR>
     */
    public String discernId;

    /**
     * (メール名称)<BR>
     */
    public String mailName;

    /**
     * (差出人)<BR>
     */
    public String mailFrom;

    /**
     * (送信先メールアドレス)<BR>
     */
    public String sendAddress;

    /**
     * (件名)<BR>
     */
    public String mailSubject;

    /**
     * (メールヘッダー)<BR>
     */
    public String mailHeader;

    /**
     * (メール本文)<BR>
     */
    public String mailBody;

    /**
     * (メールフッター)<BR>
     */
    public String mailFooter;

    /**
     * @@roseuid 416F1DCE02CE
     */
    public WEB3AdminMailInfoCommonRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) プログラムIDのチェック<BR>
     *  1-1) this.送信メール区分==nullの場合、例外をスローする。<BR>
     *     =============================================== <BR>
     *     　@　@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00862              <BR>
     *     =============================================== <BR>
     *  1-2) this.送信メール区分の桁数＞4桁の場合、例外をスローする。<BR>
     *     =============================================== <BR>
     *     　@　@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00863              <BR>
     *     =============================================== <BR>
     *  1-3) this.送信メール区分の値が数値以外の場合、例外をスローする。<BR>
     *     =============================================== <BR>
     *     　@　@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00864              <BR>
     *     =============================================== <BR>
     * <BR>
     * 2) 識別IDのチェック<BR>
     *  2-1) this.識別ID==nullの場合、例外をスローする。<BR>
     *     =============================================== <BR>
     *     　@　@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00865              <BR>
     *     =============================================== <BR>
     *  2-2) this.識別IDの桁数＞4桁の場合、または桁数==0桁の場合、<BR>
     * 　@　@例外をスローする。<BR>
     *     =============================================== <BR>
     *     　@　@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00866              <BR>
     *     =============================================== <BR>
     *  2-3) this.識別IDの値に、半角英数以外の文字が存在する場合、<BR>
     * 　@　@例外をスローする。<BR>
     *     =============================================== <BR>
     *     　@　@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00867              <BR>
     *     =============================================== <BR>
     * <BR>
     * 3) メール名称のチェック<BR>
     *  3-1) this.メール名称の値＞200バイトの場合、例外をスローする。<BR>
     *     =============================================== <BR>
     *     　@　@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00868              <BR>
     *     =============================================== <BR>
     * <BR>
     * 4) 件名のチェック<BR>
     *  4-1) this.件名==nullの場合、例外をスローする。<BR>
     *     =============================================== <BR>
     *     　@　@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00871              <BR>
     *     =============================================== <BR>
     *  4-2) this.件名の値＞1000バイトの場合、例外をスローする。<BR>
     *     =============================================== <BR>
     *     　@　@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00872              <BR>
     *     =============================================== <BR>
     * <BR>
     * 5) メールヘッダー／メール本文／メールフッターのチェック<BR>
     *  5-1) this.メールヘッダー、this.メール本文、this.メールフッターの<BR>
     * 　@　@全てがnullの場合、例外をスローする。<BR>
     *     =============================================== <BR>
     *     　@　@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00873              <BR>
     *     =============================================== <BR>
     *  5-2) this.メールヘッダー!=nullであり、かつ値＞4000バイトの場合、<BR>
     * 例外をスローする。<BR>
     *     =============================================== <BR>
     *     　@　@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00874           <BR>
     *     =============================================== <BR>
     *  5-3) this.メール本文!=nullであり、かつ値＞4000バイトの場合、<BR>
     * 例外をスローする。<BR>
     *     =============================================== <BR>
     *     　@　@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00875           <BR>
     *     =============================================== <BR>
     *  5-4) this.メールフッター!=nullであり、かつ値＞4000バイトの場合、<BR>
     * 例外をスローする。<BR>
     *     =============================================== <BR>
     *     　@　@class         :  WEB3BusinessLayerException          <BR>
     *         tag            :  BUSINESS_ERROR_00876           <BR>
     *     =============================================== <BR>
     * <BR>
     * @@roseuid 413C11F903A9
     */
    public void validate() throws WEB3BaseException
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //(1) 送信メール区分のチェック
        //(1-1) this.送信メール区分==nullの場合、例外をスローする
        if(this.sendMailDiv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00862, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(1-2) this.送信メール区分の桁数＞4桁の場合、例外をスローする
        if(WEB3StringTypeUtility.getByteLength(this.sendMailDiv) > 4)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00863, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(1-3) this.送信メール区分の値が数値以外の場合、例外をスローする
        if( WEB3StringTypeUtility.isNumber(this.sendMailDiv) == false)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00864, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //( 2) 識別IDのチェック
        //(2-1) this.識別ID==nullの場合、例外をスローする。
        if(this.discernId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00865, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(2-2) this.識別IDの桁数＞4桁の場合、または桁数==0桁の場合、
        //例外をスローする。
        if((WEB3StringTypeUtility.getByteLength(this.discernId) > 4) || (WEB3StringTypeUtility.getByteLength(this.discernId) == 0 ))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00866, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //( 2-3) this.識別IDの値に、半角英数以外の文字が存在する場合、
        //例外をスローする。
        if(WEB3StringTypeUtility.isSingle(this.discernId) == false)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00867, 
                this.getClass().getName() + STR_METHOD_NAME); 
           
        }
        //(3) メール名称のチェック
        //(3-1) this.メール名称の値＞200バイトの場合、例外をスローする
        if(WEB3StringTypeUtility.getByteLength(this.mailName) > 200 )
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00868, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }

        //(4) 件名のチェック
        //(4-1) this.件名==nullの場合、例外をスローする。
        if(this.mailSubject == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00871, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(4-2) this.件名の値＞1000バイトの場合、例外をスローする
        if(WEB3StringTypeUtility.getByteLength(this.mailSubject) > 1000)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00872, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(5) メールヘッダー／メール本文／メールフッターのチェック
        //(5-1) this.メールヘッダー、this.メール本文、this.メールフッターの全てがnullの場合、例外をスローする。
        if(this.mailHeader == null && this.mailBody == null && this.mailFooter == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00873, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(5-2) this.メールヘッダー!=nullであり、かつ値＞4000バイトの場合、例外をスローする。
        if(this.mailHeader != null && WEB3StringTypeUtility.getByteLength(this.mailHeader) > 4000)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00874, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(5-3) this.メール本文!=nullであり、かつ値＞4000バイトの場合、例外をスローする。
        if(this.mailBody != null && WEB3StringTypeUtility.getByteLength(this.mailBody) > 4000)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00875, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        //(5-4) this.メールフッター!=nullであり、かつ値＞4000バイトの場合例外をスローする。
        if(this.mailFooter != null && WEB3StringTypeUtility.getByteLength(this.mailFooter) > 4000)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00876, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 416F1DCE031C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMailInfoCommonResponse(this);
    }
}
@
