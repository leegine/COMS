head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopUpdConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止変更確認リクエスト(WEB3AdminToTradeStopUpdConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/05 張　@芳(中訊) 新規作成
                 : 2006/04/26 黄建(中訊) 仕様変更・モデルNo.062  
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (トリガー注文管理者・取扱停止変更確認リクエスト)<BR>
 * トリガー注文管理者・取扱停止変更確認リクエストクラス<BR>
 * 
 * @@author 張　@芳
 * @@version 1.0
 */
public class WEB3AdminToTradeStopUpdConfirmRequest extends WEB3AdminToCommonRequest 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopUpdConfirmRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_upd_confirm";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 20060403164900L;
    
    /**
     * (取扱停止情報一覧)<BR>
     * 取扱停止情報一覧<BR>
     */
    public WEB3AdminToTradeStopInfoUnit[] tradeStopInfoList;
    
    /**
     * @@roseuid 4430D3B90222
     */
    public WEB3AdminToTradeStopUpdConfirmRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@super.validate()をコールする。<BR>
     * <BR>
     * ２）　@取扱停止情報一覧チェック<BR>
     * 　@２－１）　@this.取扱停止情報一覧 == nullの場合、<BR>
     * 　@　@「取扱停止情報が未入力」の例外をスローする。<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_02431<BR>
     * <BR>
     * 　@２－２）　@this.取扱停止情報一覧の要素数分、<BR>
     * 　@　@以下の処理をLoopする。<BR>
     * <BR>
     * 　@　@２－２－１）　@変更後店頭公開区分チェック<BR>
     * 　@　@　@this.変更後店頭公開区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@　@　@２－２－１－１）　@this.変更後店頭公開区分に<BR>
     * 　@　@　@　@下記の項目以外が設定されていたら、<BR>
     * 　@　@　@　@「店頭公開区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@・"DEFAULT"　@※オークション銘柄<BR>
     * 　@　@　@　@　@・"マーケットメイク銘柄"<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_02428<BR>
     * <BR>
     * 　@　@２－２－２）　@変更後停止理由チェック<BR>
     * 　@　@　@this.変更後停止理由 != nullの場合、以下のチェックを行う。<BR>
     * 　@　@　@２－２－２－１）　@this.変更後停止理由のbyte数 > 50byteの場合、<BR>
     * 　@　@　@　@「入力理由エラー(桁数超過)」の例外をスローする。<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_01435<BR>
     * <BR>
     * 　@　@２－２－３）　@変更後有効期限チェック<BR>
     * 　@　@　@２－２－３－１）　@this.変更後有効期限To == nullの場合、<BR>
     * 　@　@　@　@「有効期限Toが未指定です。」の例外をスローする。<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_01432<BR>
     * <BR>
     * 　@　@　@２－２－３－２）　@this.変更後有効期限Toが日付に変換できない場合、<BR>
     * 　@　@　@　@「有効期限Toエラー(存在しない日付)」の例外をスローする。<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_01433<BR>
     * <BR>
     * 　@　@　@２－２－３－３）　@this.有効期限From > this.変更後有効期限Toの場合、<BR>
     * 　@　@　@　@「有効期限整合性エラー」の例外をスローする。<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_01434<BR>
     * <BR>
     * 　@　@２－２－４）　@取扱停止情報.validate()をコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4406BCD100CC
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）super.validate()をコールする。
        super.validate();
        
        // ２）取扱停止情報一覧チェック
        // 　@２－１）this.取扱停止情報一覧 == nullの場合、
        // 　@　@     「取扱停止情報が未入力」の例外をスローする。
        //           class : WEB3BusinessLayerException
        //           tag : BUSINESS_ERROR_02431
        if (this.tradeStopInfoList == null || this.tradeStopInfoList.length == 0)
        {
            log.debug("取扱停止情報が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02431,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取扱停止情報が未入力です。");            
        }
        
        // ２－２）　@this.取扱停止情報一覧の要素数分、以下の処理をLoopする。
        int l_intLength = this.tradeStopInfoList.length;  
        for (int i = 0; i < l_intLength; i++)
        {
            // ２－２－１）変更後店頭公開区分チェック
            // 　@　@　@     this.変更後店頭公開区分 != nullの場合、以下のチェックを行う。
            if (WEB3StringTypeUtility.isNotEmpty(tradeStopInfoList[i].aftOtcOpenDiv))
            {
                // ２－２－１－１）this.変更後店頭公開区分に下記の項目以外が設定されていたら、
                // 　@　@　@　@      「店頭公開区分が未定義の値」の例外をスローする。
                // 　@　@　@　@　@     ・"DEFAULT"　@※オークション銘柄
                // 　@　@　@　@　@     ・"マーケットメイク銘柄"
                //               class : WEB3BusinessLayerException
                //               tag : BUSINESS_ERROR_02428
                if (!(WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(tradeStopInfoList[i].aftOtcOpenDiv)
                    || WEB3OpenOtcDivDef.DEFAULT.equals(tradeStopInfoList[i].aftOtcOpenDiv)))
                {
                    log.debug("店頭公開区分が存在しないコード値です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02428,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "店頭公開区分が存在しないコード値です。");               
                }
            }
            
            // ２－２－２）変更後停止理由チェック
            // 　@　@　@      this.変更後停止理由 != nullの場合、以下のチェックを行う。
            if (WEB3StringTypeUtility.isNotEmpty(tradeStopInfoList[i].aftChangeStopReason))
            {
                // ２－２－２－１）　@this.変更後停止理由のbyte数 > 50byteの場合、
                // 　@　@　@　@「入力理由エラー(桁数超過)」の例外をスローする。
                //     class : WEB3BusinessLayerException
                //     tag : BUSINESS_ERROR_01435
                if (tradeStopInfoList[i].aftChangeStopReason.getBytes().length > 50)
                {
                    log.debug("入力理由エラー(桁数超過)。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01435,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "入力理由エラー(桁数超過)。"); 
                 }                      
            }
            
            // ２－２－３）変更後有効期限チェック
            //　@　@　@２－２－３－１）this.変更後有効期限To == nullの場合、
            // 　@　@　@　@           「有効期限Toが未指定です。」の例外をスローする。
            //                     class : WEB3BusinessLayerException
            //                     tag : BUSINESS_ERROR_01432
            if (WEB3StringTypeUtility.isEmpty(tradeStopInfoList[i].aftChangeExpirationEndDate))
            {
                log.debug("有効期限Toが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01432,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "有効期限Toが未指定です。");                
            }
            
            // ２－２－３－２）this.変更後有効期限Toが日付に変換できない場合、
            // 　@　@　@　@      「有効期限Toエラー(存在しない日付)」の例外をスローする。
            //               class : WEB3BusinessLayerException
            //               tag : BUSINESS_ERROR_01433
            if (!WEB3StringTypeUtility.isDateStr(tradeStopInfoList[i].aftChangeExpirationEndDate, "yyyyMMdd"))
            {
                log.debug("有効期限Toエラー(存在しない日付)。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01433,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "有効期限Toエラー(存在しない日付)。");                                 
            }
            
            // ２－２－３－３）this.有効期限From > this.変更後有効期限Toの場合、
            // 　@　@　@　@       「有効期限整合性エラー」の例外をスローする。
            //                class : WEB3BusinessLayerException
            //                tag : BUSINESS_ERROR_01434
            if (WEB3DateUtility.compare(
                WEB3DateUtility.getDate(tradeStopInfoList[i].expirationStartDate, "yyyyMMdd"),
                WEB3DateUtility.getDate(tradeStopInfoList[i].aftChangeExpirationEndDate, "yyyyMMdd")) > 0)
            {
                log.debug("有効期限整合性エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01434,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "有効期限整合性エラー。");                         
            }
            
            // ２－２－４）取扱停止情報.validate()をコールする。
            tradeStopInfoList[i].validate();               
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (createResponseの実装)<BR>
     * <BR>
     * トリガー注文管理者・取扱停止変更確認レスポンスオブジェクトを返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToTradeStopUpdConfirmResponse(this);
    }
}
@
