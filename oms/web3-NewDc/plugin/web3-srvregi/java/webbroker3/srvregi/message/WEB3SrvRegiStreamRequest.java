head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStreamRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用債券連携リクエスト(WEB3SrvRegiStreamRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/19 馮海濤 新規作成 モデル370、モデル375
Revision History : 2008/06/20 武波 (中訊) モデルNo.394
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.srvregi.define.WEB3SrvRegiTradingTypeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用債券連携リクエスト)<BR>
 * サービス利用債券連携リクエスト<BR>
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public class WEB3SrvRegiStreamRequest extends WEB3GenRequest
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStreamRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_stream";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200805191654L;

   /**
    * (サービス区分)<BR>
    * サービス区分<BR>
    */
   public String serviceDiv;

   /**
    * (取引区分)<BR>
    * 取引区分<BR>
    * 1：買付処理<BR>
    * 2：買付注文取消処理<BR>
    * 3：目論見書閲覧チェック<BR>
    */
   public String tradingType;

   /**
    * (銘柄コード)<BR>
    * 銘柄コード<BR>
    */
   public String productCode;

   /**
    * (種別コード)<BR>
    * 種別コード<BR>
    */
   public String batTypeCode;

   /**
    * (注文№)<BR>
    * 注文№<BR>
    */
   public String orderNo;

   /**
    * (受渡日)<BR>
    * 受渡日(YYYYMMDD)<BR>
    */
   public String deliveryDate;

   /**
    * (発注日)<BR>
    * 発注日(YYYYMMDD)<BR>
    */
   public String orderBizDate;

   /**
    * (金額)<BR>
    * 金額<BR>
    */
   public String amount;

   /**
    * (サービス利用債券連携リクエスト)
    * デフォルトコンストラクタ<BR>
    * @@roseuid 481554D00210
    */
   public WEB3SrvRegiStreamRequest()
   {

   }

   /**
    * 当リクエストデータの整合性チェックを行う。 <BR>
    * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
    * <BR>
    * １）必須チェック<BR>
    * 　@①@サービス区分のチェック<BR>
    * 　@　@-this.サービス区分==nullの場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_00758<BR>
    * 　@　@-this.サービス区分の桁数が4桁を超えている場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_00831<BR>
    * 　@　@-this.サービス区分が半角数字以外の場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_00882<BR>
    * <BR>
    * 　@②取引区分のチェック<BR>
    * 　@　@-this.取引区分==nullの場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_00601<BR>
    * 　@　@-this.取引区分が以下の値（半角）以外の場合、例外をスローする。<BR>
    * 　@　@　@・1：買付処理<BR>
    * 　@　@　@・2：買付注文取消処理<BR>
    * 　@　@　@・3：目論見書閲覧チェック<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_00602<BR>
    * <BR>
    * ２）取引区分が『1：買付処理』または、取引区分が『3：目論見書閲覧チェック』の場合<BR>
    * <BR>
    * 　@①@銘柄コードのチェック<BR>
    * 　@　@-this.銘柄コード==nullの場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_00079<BR>
    * 　@　@-this.銘柄コードの桁数が10桁を超えている場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_00439<BR>
    * 　@　@-this.銘柄コードが半角英数字以外の場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_01067<BR>
    * <BR>
    * 　@②種別コードのチェック<BR>
    * 　@　@-this.種別コード==nullの場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_02202<BR>
    * 　@　@-this.種別コードの桁数が3桁を超えている場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_03083<BR>
    * 　@　@-this.種別コードが半角数字以外の場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_03084<BR>
    * <BR>
    * <BR>
    * ３）取引区分が『1：買付処理』または取引区分が『2：買付注文取消処理』場合<BR>
    * 　@①@注文№のチェック<BR>
    * 　@　@-this.注文№==nullの場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_03085<BR>
    * 　@　@-this.注文№の桁数が10桁を超えている場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_03086<BR>
    * 　@　@-this.注文№が半角数字以外の場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_03087<BR>
    * <BR>
    * <BR>
    * ４）取引区分が『1：買付処理』の場合<BR>
    * 　@①@受渡日のチェック<BR>
    * 　@　@-this.受渡日==nullの場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_01079<BR>
    * 　@　@-this.受渡日が不正な日付の場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_02865<BR>
    * <BR>
    * 　@②発注日のチェック<BR>
    * 　@　@-this.発注日==nullの場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_00406<BR>
    * 　@　@-this.発注日が不正な日付の場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_02160<BR>
    * <BR>
    * 　@③金額のチェック<BR>
    * 　@　@-this.金額==nullの場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_03088<BR>
    * 　@　@-this.金額の桁数が12桁を超えている場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_03089<BR>
    * 　@　@-this.金額が半角数字以外の場合、例外をスローする。<BR>
    * 　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@tag:   BUSINESS_ERROR_03090<BR>
    * @@throws WEB3BaseException
    * @@roseuid 48152DB600F1
    */
   public void validate() throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "validate()";
       log.entering(STR_METHOD_NAME);

       //-this.サービス区分==nullの場合、例外をスローする。
       if (this.serviceDiv == null)
       {
           log.debug("サービス区分が未指定です。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00758,
               getClass().getName() + "." + STR_METHOD_NAME,
               "サービス区分が未指定です。");
       }

       // -this.サービス区分の桁数が4桁を超えている場合、例外をスローする。
       if (this.serviceDiv.length() > 4)
       {
           log.debug("サービス区分の桁数が不正です。。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00831,
               getClass().getName() + "." + STR_METHOD_NAME,
               "サービス区分の桁数が不正です。。");
       }

       //-this.サービス区分が半角数字以外の場合、例外をスローする。
       if (!WEB3StringTypeUtility.isDigit(this.serviceDiv))
       {
           log.debug("サービス区分が数値以外の値です。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00882,
               getClass().getName() + "." + STR_METHOD_NAME,
               "サービス区分が数値以外の値です。");
       }

       // ②取引区分のチェック
       //-this.取引区分==nullの場合、例外をスローする。
       if (this.tradingType == null)
       {
           log.debug("取引区分が未指定です。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00601,
               getClass().getName() + "." + STR_METHOD_NAME,
               "取引区分が未指定です。");
       }

        //   -this.取引区分が以下の値（半角）以外の場合、例外をスローする。
        //   ・1：買付処理
        //   ・2：買付注文取消処理
        //   ・3：目論見書閲覧チェック
       if (!WEB3SrvRegiTradingTypeDef.BUY_PROCESS.equals(this.tradingType)
           && !WEB3SrvRegiTradingTypeDef.BUY_ORDER_CANCEL_PROCESS.equals(this.tradingType)
           && !WEB3SrvRegiTradingTypeDef.PROSPECTUS_CHECK.equals(this.tradingType))
       {
           log.debug("取引区分が存在しないコード値です。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00602,
               getClass().getName() + "." + STR_METHOD_NAME,
               "取引区分が存在しないコード値です。");
       }

        //   ２）取引区分が『1：買付処理』または、取引区分が『3：目論見書閲覧チェック』の場合
        //   ①@銘柄コードのチェック
       if (WEB3SrvRegiTradingTypeDef.BUY_PROCESS.equals(this.tradingType)
           || WEB3SrvRegiTradingTypeDef.PROSPECTUS_CHECK.equals(this.tradingType))
       {
           //-this.銘柄コード==nullの場合、例外をスローする。
           if (this.productCode == null)
           {
               log.debug("銘柄コードが未指定です。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "銘柄コードが未指定です。");
           }
           //-this.銘柄コードの桁数が10桁を超えている場合、例外をスローする。
           if (this.productCode.length() > 10)
           {
               log.debug("銘柄コードのサイズが不正です。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "銘柄コードのサイズが不正です。");
           }
           //-this.銘柄コードが半角英数字以外の場合、例外をスローする。
           if (!WEB3StringTypeUtility.isLetterOrDigit(this.productCode))
           {
               log.debug("銘柄コードの入力が不正です。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "銘柄コードの入力が不正です。");
           }

           //   ②種別コードのチェック
           //   -this.種別コード==nullの場合、例外をスローする。
           if (this.batTypeCode == null)
           {
               log.debug("種別コードが未指定です。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_02202,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "種別コードが未指定です。");
           }
           //   -this.種別コードの桁数が3桁を超えている場合、例外をスローする。
           if (this.batTypeCode.length() > 3)
           {
               log.debug("種別コードの桁数が3桁を越えています。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03083,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "種別コードの桁数が3桁を越えています。");
           }
           //   -this.種別コードが半角数字以外の場合、例外をスローする。
           if (!WEB3StringTypeUtility.isDigit(this.batTypeCode))
           {
               log.debug("種別コードが半角数字以外の値です。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03084,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "種別コードが半角数字以外の値です。");
           }
       }
       //３）取引区分が『1：買付処理』または取引区分が『2：買付注文取消処理』場合
       //①@注文№のチェック
       if (WEB3SrvRegiTradingTypeDef.BUY_PROCESS.equals(this.tradingType)
           || WEB3SrvRegiTradingTypeDef.BUY_ORDER_CANCEL_PROCESS.equals(this.tradingType))
       {
           //-this.注文№==nullの場合、例外をスローする。
           if (this.orderNo == null)
           {
               log.debug("注文№が未指定です。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03085,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "注文№が未指定です。");
           }
           // -this.注文№の桁数が10桁を超えている場合、例外をスローする。
           if (this.orderNo.length() > 10)
           {
               log.debug("注文№の桁数が10桁を超えています。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03086,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "注文№の桁数が10桁を超えています。");
           }
           // -this.注文№が半角数字以外の場合、例外をスローする。
           if (!WEB3StringTypeUtility.isDigit(this.orderNo))
           {
               log.debug("注文№が半角数字以外の値です。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03087,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "注文№が半角数字以外の値です。");
           }
       }

        //   ４）取引区分が『1：買付処理』の場合
       if (WEB3SrvRegiTradingTypeDef.BUY_PROCESS.equals(this.tradingType))
       {
            //   ①@受渡日のチェック
            //    -this.受渡日==nullの場合、例外をスローする。
           if (this.deliveryDate == null)
           {
               log.debug("受渡日が未指定です。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_01079,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "受渡日が未指定です。");
           }
            //-this.受渡日が不正な日付の場合、例外をスローする。
           if (this.deliveryDate.length() != 8
               || WEB3DateUtility.getDate(this.deliveryDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD) == null)
           {
               log.debug("受渡日が不正です。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_02865,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "受渡日が不正です。");
           }

           //②発注日のチェック
           //    -this.発注日==nullの場合、例外をスローする。
           if (this.orderBizDate == null)
           {
               log.debug("発注日が未指定です。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "発注日が未指定です。");
           }

           //-this.発注日が不正な日付の場合、例外をスローする。
           if (this.orderBizDate.length() != 8
               || WEB3DateUtility.getDate(this.orderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD) == null)
           {
               log.debug("発注日エラー。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_02160,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "発注日エラー。");
           }

            //③金額のチェック
            //    -this.金額==nullの場合、例外をスローする。
           if (this.amount == null)
           {
               log.debug("金額が未指定です。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03088,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "金額が未指定です。");
           }

            //    -this.金額の桁数が12桁を超えている場合、例外をスローする。
           if (this.amount.length() > 12)
           {
               log.debug("金額の桁数が12桁を超えています。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03089,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "金額の桁数が12桁を超えています。");
           }

            //    -this.金額が半角数字以外の場合、例外をスローする。
           if (!WEB3StringTypeUtility.isNumber(this.amount))
           {
               log.debug("金額が半角数字以外の値です。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_03090,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "金額が半角数字以外の値です。");
           }
       }
       log.exiting(STR_METHOD_NAME);
   }

   /**
    * (createレスポンス)
    * サービス利用債券連携レスポンスを生成して返却する。
    * @@return WEB3GenResponse
    * @@roseuid 4815564302DC
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3SrvRegiStreamResponse(this);
   }
}@
