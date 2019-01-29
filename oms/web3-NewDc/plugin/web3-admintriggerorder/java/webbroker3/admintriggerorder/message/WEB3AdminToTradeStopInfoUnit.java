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
filename	WEB3AdminToTradeStopInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取扱停止情報(WEB3AdminToTradeStopInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 張　@芳(中訊) 新規作成
                 : 2006/04/06 黄浩澎(中訊) 仕様変更・モデル056
                 : 2006/04/26 黄建(中訊) 仕様変更・モデルNo.061、062                  
*/

package webbroker3.admintriggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (取扱停止情報)<BR>
 * 取扱停止情報クラス<BR>
 * 
 * @@author 張　@芳
 * @@version 1.0
 */
public class WEB3AdminToTradeStopInfoUnit extends Message 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopInfoUnit.class);
    
    /**
     * (ID)<BR>
     * 特殊執行条件取扱停止ID<BR>
     * <BR>
     * ※登録処理時のみnull。<BR>
     */
    public String id = null;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     * <BR>
     * ※「商品別」の場合、設定対象の部店コードをセット。<BR>
     * 　@以外、"000"をセット。<BR>
     */
    public String branchCode;
    
    /**
     * (商品区分)<BR>
     * 商品区分 <BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション <BR>
     * <BR>
     * ※「商品別」の場合、設定対象の商品区分をセット。<BR>
     * 　@以外、nullをセット。<BR>
     */
    public String productDiv = null;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     * <BR>
     * ※「市場別」の場合、設定対象の市場コードをセット。<BR>
     * 　@以外、nullをセット。<BR>
     */
    public String marketCode = null;
    
    /**
     * (店頭公開区分)<BR>
     * 店頭公開区分<BR>
     * <BR>
     * 0：　@DEFAULT　@※オークション銘柄<BR>
     * 3：　@マーケットメイク銘柄<BR>
     * null：　@両方<BR>
     * <BR>
     * ※市場コード == "JASDAQ"の場合のみ有効。<BR>
     */
    public String otcOpenDiv = null;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     * <BR>
     * ※「銘柄別」の場合、設定対象の銘柄コードをセット。<BR>
     * 　@以外、nullをセット。<BR>
     */
    public String productCode = null;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     * <BR>
     * ※「銘柄別」の場合、銘柄コードに該当する銘柄名をセット。<BR>
     * 　@以外、nullをセット。<BR>
     */
    public String productName = null;
    
    /**
     * (停止理由)<BR>
     * 停止理由<BR>
     */
    public String stopReason = null;
    
    /**
     * (有効期限From)<BR>
     * 有効期限From<BR>
     * （YYYYMMDD）<BR>
     * <BR>
     * ※無期限の場合は、00010101をセット。<BR>
     * 　@（商品別、市場別の場合も上記値をセット。）<BR>
     */
    public String expirationStartDate;
    
    /**
     * (有効期限To)<BR>
     * 有効期限To<BR>
     * （YYYYMMDD）<BR>
     * <BR>
     * ※無期限の場合は、99991231をセット。<BR>
     * 　@（商品別、市場別の場合も上記値をセット。）<BR>
     */
    public String expirationEndDate;
    
    /**
     * (変更後店頭公開区分)<BR>
     * 変更後店頭公開区分<BR>
     * <BR>
     * 0：　@DEFAULT　@※オークション銘柄<BR>
     * 3：　@マーケットメイク銘柄<BR>
     * null：　@両方<BR>
     * <BR>
     * ※市場コード == "JASDAQ"の場合のみ有効。<BR>
     */
    public String aftOtcOpenDiv = null;
    
    /**
     * (変更後停止理由)<BR>
     * 変更後停止理由<BR>
     * <BR>
     * ※変更入力後の停止理由をセットする。<BR>
     * 　@変更なしの場合は、this.停止理由と同じ値をセットする。<BR>
     */
    public String aftChangeStopReason = null;
    
    /**
     * (変更後有効期限To)<BR>
     * 変更後有効期限To<BR>
     * （YYYYMMDD）<BR>
     * <BR>
     * ※変更入力後の有効期限Toをセットする。<BR>
     * 　@変更なしの場合は、this.有効期限Toと同じ値をセットする。<BR>
     */
    public String aftChangeExpirationEndDate = null;
    
    /**
     * (注文停止状況一覧)<BR>
     * 注文停止状況一覧<BR>
     */
    public WEB3AdminToOrderStopStateUnit[] orderStopStateList;
    
    /**
     * (取扱停止情報)<BR>
     * コンストラクタ<BR>
     * @@roseuid 4406AFCB02EE
     */
    public WEB3AdminToTradeStopInfoUnit() 
    {
     
    }
    
    /**
     * 当クラスの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@部店コードチェック <BR>
     * 　@１－１）　@this.部店コード == nullの場合、 <BR>
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_02174<BR>
     * <BR>
     * 　@１－２）　@this.部店コードが以下の条件に該当する場合、 <BR>
     * 　@　@　@　@　@「部店コードエラー」の例外をスローする。 <BR>
     * 　@　@　@　@　@・部店コード != 数字 <BR>
     * 　@　@　@　@　@・部店コード.length != 3 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * ２）　@商品区分チェック<BR>
     * 　@this.商品区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@２－１）　@this.商品区分に下記の項目以外が設定されていたら、 <BR>
     * 　@　@「商品区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・"現物株式" <BR>
     * 　@　@　@　@・"信用取引" <BR>
     * 　@　@　@　@・"先物"<BR>
     * 　@　@　@　@・"オプション" <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01068<BR>
     * <BR>
     * ３）　@市場コードチェック<BR>
     * 　@this.市場コード != nullの場合、以下のチェックを行う。<BR>
     * 　@３－１）　@　@this.市場コードに下記の項目以外が設定されていたら、<BR>
     * 　@　@「市場コードが未定義の値」の例外をスローする。 <BR>
     * 　@　@　@・"東京" <BR>
     * 　@　@　@・"大阪" <BR>
     * 　@　@　@・"名古屋" <BR>
     * 　@　@　@・"福岡" <BR>
     * 　@　@　@・"札幌" <BR>
     * 　@　@　@・"NNM" <BR>
     * 　@　@　@・"JASDAQ" <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * ４）　@店頭公開区分チェック<BR>
     * 　@this.店頭公開区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@４－１）　@this.店頭公開区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@「店頭公開区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・"登録"　@※オークション銘柄<BR>
     * 　@　@　@・"マーケットメイク銘柄"<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_02428<BR>
     * <BR>
     * ５）　@銘柄コードチェック <BR>
     * 　@this.銘柄コード != nullの場合、以下のチェックを行う。 <BR>
     * 　@５－１）　@this.銘柄コードが以下の条件に該当する場合、 <BR>
     * 　@　@　@　@「銘柄コードエラー」の例外をスローする。 <BR>
     * 　@　@　@　@・銘柄コード != 数字 <BR>
     * 　@　@　@　@・銘柄コード.length != 5 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * ６）　@停止理由チェック<BR>
     * 　@this.停止理由 != nullの場合、以下のチェックを行う。<BR>
     * 　@６－１）　@this.停止理由のbyte数 > 50byteの場合、<BR>
     * 　@　@「入力理由エラー(桁数超過)」の例外をスローする。<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01435<BR>
     * <BR>
     * ７）　@有効期限チェック<BR>
     * 　@７－１）　@this.有効期限From == nullの場合、<BR>
     * 　@　@「有効期限Fromが未指定です。」の例外をスローする。<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01430<BR>
     * <BR>
     * 　@７－２）　@this.有効期限Fromが日付に変換できない場合、<BR>
     * 　@　@「有効期限Fromエラー(存在しない日付)」の例外をスローする。<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01431<BR>
     * <BR>
     * 　@７－３）　@this.有効期限To == nullの場合、<BR>
     * 　@　@「有効期限Toが未指定です。」の例外をスローする。<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01432<BR>
     * <BR>
     * 　@７－４）　@this.有効期限Toが日付に変換できない場合、<BR>
     * 　@　@「有効期限Toエラー(存在しない日付)」の例外をスローする。<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01433<BR>
     * <BR>
     * 　@７－５）　@this.有効期限From > this.有効期限Toの場合、<BR>
     * 　@　@「有効期限整合性エラー」の例外をスローする。<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_01434<BR>
     * <BR>
     * ８）　@注文停止状況一覧チェック<BR>
     * 　@８－１）　@this.注文停止状況一覧 == nullの場合、<BR>
     * 　@　@「注文停止状況が未入力」の例外をスローする。<BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_02429<BR>
     * <BR>
     * 　@８－２）　@this.注文停止状況一覧の要素数分、<BR>
     * 　@　@以下の処理をLoopする。<BR>
     * 　@　@８－２－１）　@注文停止状況.validate()をコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 441135FE03B7
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）部店コードチェック 
        // 　@１－１）this.部店コード == nullの場合、「部店コードがnull」の例外をスローする。 
        //          class : WEB3BusinessLayerException
        //          tag : BUSINESS_ERROR_02174
        if (WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            log.debug("部店コードがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードがnullです。");
        }
        
        //  １－２）this.部店コードが以下の条件に該当する場合、「部店コードエラー」の例外をスローする。 
        // 　@　@　@　@　@・部店コード != 数字 
        // 　@　@　@　@　@・部店コード.length != 3 
        //           class : WEB3BusinessLayerException
        //           tag : BUSINESS_ERROR_00779
        if (!WEB3StringTypeUtility.isDigit(this.branchCode)
            || this.branchCode.length() != 3)
        {
            log.debug("部店コードの入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードの入力が不正です。");            
        }
        
        // ２）商品区分チェック
        // 　@  this.商品区分 != nullの場合、以下のチェックを行う。
        if (WEB3StringTypeUtility.isNotEmpty(this.productDiv))
        {
            //２－１）this.商品区分に下記の項目以外が設定されていたら、
            //　@　@　@「商品区分が未定義の値」の例外をスローする。
            //　@　@　@   ・"現物株式" 
            // 　@　@　@　@・"信用取引" 
            // 　@　@　@　@・"先物"
            // 　@　@　@　@・"オプション" 
            //         class : WEB3BusinessLayerException
            //         tag : BUSINESS_ERROR_01068
            
            if (!(WEB3CommodityDivDef.EQUITY.equals(this.productDiv)
                || WEB3CommodityDivDef.MARGIN.equals(this.productDiv)
                || WEB3CommodityDivDef.FUTURE.equals(this.productDiv)
                || WEB3CommodityDivDef.OPTION.equals(this.productDiv)))
            {
                log.debug("商品区分が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "商品区分が存在しないコード値です。");
            }
        }
        
        // ３）市場コードチェック
        // 　@  this.市場コード != nullの場合、以下のチェックを行う。
        if (WEB3StringTypeUtility.isNotEmpty(this.marketCode))
        {            
            // ３－１）this.市場コードに下記の項目以外が設定されていたら、
            // 　@　@     「市場コードが未定義の値」の例外をスローする。 
            // 　@　@　@    ・"東京" 
            // 　@　@　@    ・"大阪" 
            // 　@　@　@    ・"名古屋" 
            // 　@　@　@    ・"福岡" 
            // 　@　@　@    ・"札幌" 
            // 　@　@　@    ・"NNM" 
            // 　@　@　@    ・"JASDAQ" 
            //          class : WEB3BusinessLayerException
            //          tag : BUSINESS_ERROR_00608
            if (!(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
                || WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
                || WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
                || WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
                || WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
                || WEB3MarketCodeDef.NNM.equals(this.marketCode)
                || WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)))
            {
                log.debug("市場コードが存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "市場コードが存在しないコード値です。");
            }            
        }
        
        // ４）店頭公開区分チェック
        //　@   this.店頭公開区分 != nullの場合、以下のチェックを行う。
        if (WEB3StringTypeUtility.isNotEmpty(this.otcOpenDiv))
        {
            // 　@４－１）this.店頭公開区分に下記の項目以外が設定されていたら、
            // 　@　@     「店頭公開区分が未定義の値」の例外をスローする。
            // 　@　@　@    ・"DEFAULT"　@※オークション銘柄
            // 　@　@　@    ・"マーケットメイク銘柄"
            //           class : WEB3BusinessLayerException
            //           tag : BUSINESS_ERROR_02428
            if (!(WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(this.otcOpenDiv)
                || WEB3OpenOtcDivDef.DEFAULT.equals(this.otcOpenDiv)))
            {
                log.debug("店頭公開区分が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02428,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "店頭公開区分が存在しないコード値です。");               
            }
        }
        
        // ５）銘柄コードチェック 
        // 　@  this.銘柄コード != nullの場合、以下のチェックを行う。
        if (WEB3StringTypeUtility.isNotEmpty(this.productCode))
        {
            //５－１）this.銘柄コードが以下の条件に該当する場合、 
            // 　@　@　@ 銘柄コードエラー」の例外をスローする。 
            // 　@　@　@ ・銘柄コード != 数字 
            // 　@　@　@ ・銘柄コード.length != 5
            //        class : WEB3BusinessLayerException
            //        tag : BUSINESS_ERROR_01067
            if (!WEB3StringTypeUtility.isDigit(this.productCode)
                || this.productCode.length() != 5)
            {
                log.debug("銘柄コードの入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄コードの入力が不正です。");                
            }            
        }
        
        // ６）停止理由チェック
        // 　@this.停止理由 != nullの場合、以下のチェックを行う。
        if (WEB3StringTypeUtility.isNotEmpty(this.stopReason))
        {
            //６－１）this.停止理由のbyte数 > 50byteの場合、
            // 　@　@  「入力理由エラー(桁数超過)」の例外をスローする。
            //        class : WEB3BusinessLayerException
            //        tag : BUSINESS_ERROR_01435
            if (this.stopReason.getBytes().length > 50)
            {
                log.debug("入力理由エラー(桁数超過)。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01435,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "入力理由エラー(桁数超過)。");   
            }           
        }
        
        // ７）有効期限チェック
        // 　@７－１）this.有効期限From == nullの場合、
        // 　@　@     「有効期限Fromが未指定です。」の例外をスローする。
        //           class : WEB3BusinessLayerException
        //          tag : BUSINESS_ERROR_01430
        if (WEB3StringTypeUtility.isEmpty(this.expirationStartDate))
        {
            log.debug("有効期限Fromが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01430,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "有効期限Fromが未指定です。"); 
        }
        
        // 　@７－２）this.有効期限Fromが日付に変換できない場合、
        // 　@　@     「有効期限Fromエラー(存在しない日付)」の例外をスローする。
        //           class : WEB3BusinessLayerException
        //           tag : BUSINESS_ERROR_01431
        if (!WEB3StringTypeUtility.isDateStr(this.expirationStartDate, "yyyyMMdd"))
        {
            log.debug("有効期限Fromエラー(存在しない日付)。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01431,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "有効期限Fromエラー(存在しない日付)。"); 
        }
        
        //　@７－３）this.有効期限To == nullの場合、
        // 　@　@    「有効期限Toが未指定です。」の例外をスローする。
        //          class : WEB3BusinessLayerException
        //          tag : BUSINESS_ERROR_01432
        if (WEB3StringTypeUtility.isEmpty(this.expirationEndDate))
        {
            log.debug("有効期限Toが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01432,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "有効期限Toが未指定です。"); 
        }
        
        // 　@７－４）this.有効期限Toが日付に変換できない場合、
        // 　@　@     「有効期限Toエラー(存在しない日付)」の例外をスローする。
        //           class : WEB3BusinessLayerException
        //           tag : BUSINESS_ERROR_01433
        if (!WEB3StringTypeUtility.isDateStr(this.expirationEndDate, "yyyyMMdd"))
        {
            log.debug("有効期限Toエラー(存在しない日付)。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01433,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "有効期限Toエラー(存在しない日付)。"); 
        }
        
        // 　@７－５）this.有効期限From > this.有効期限Toの場合、
        // 　@　@    「有効期限整合性エラー」の例外をスローする。
        //          class : WEB3BusinessLayerException
        //          tag : BUSINESS_ERROR_01434
        if (WEB3DateUtility.compare(
            WEB3DateUtility.getDate(this.expirationStartDate, "yyyyMMdd"),
            WEB3DateUtility.getDate(this.expirationEndDate, "yyyyMMdd")) > 0)
        {
            log.debug("有効期限整合性エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01434,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "有効期限整合性エラー。");             
        }
        
        // ８）　@注文停止状況一覧チェック
        // 　@８－１）this.注文停止状況一覧 == nullの場合、
        // 　@　@     「注文停止状況が未入力」の例外をスローする。
        //           class : WEB3BusinessLayerException
        //           tag : BUSINESS_ERROR_02429
        if (this.orderStopStateList == null || this.orderStopStateList.length == 0)
        {
            log.debug("注文停止状況が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02429,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文停止状況が未入力です。"); 
        }
        
        // 　@８－２）this.注文停止状況一覧の要素数分、以下の処理をLoopする。
        //　@　@８－２－１）注文停止状況.validate()をコールする。
        int l_intLength = this.orderStopStateList.length;        
        for (int i = 0; i < l_intLength; i++)
        {
            this.orderStopStateList[i].validate();            
        }
        
        log.exiting(STR_METHOD_NAME);            
    }
}
@
