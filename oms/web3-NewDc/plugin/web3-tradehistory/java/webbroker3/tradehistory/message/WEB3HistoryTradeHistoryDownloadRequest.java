head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引履歴一覧ファ@イルダウンロードリクエスト(WEB3HistoryTradeHistoryDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/30 凌建平(中訊) 新規作成
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.tradehistory.define.WEB3TradeHistoryProductDivDef;

/**
 * (取引履歴一覧ファ@イルダウンロードリクエスト)
 * 取引履歴一覧ファ@イルダウンロードリクエストクラス
 * 
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3HistoryTradeHistoryDownloadRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。 
     */
    private  static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3HistoryTradeHistoryDownloadRequest.class); 
  
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tradeHistory_tradeHistoryDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511301710L;
     
    /**
     * (表示期間From)<BR>
     * 表示期間From<BR>
     * (YYYYMMDD)<BR>
     */
    public String listStartDate;
    
    /**
     * (表示期間To)<BR>
     * 表示期間To<BR>
     * (YYYYMMDD)<BR>
     */
    public String listEndDate;
    
    /**
     * (商品区分)<BR>
     * A：　@全商品<BR>
     * B：　@現物・信用<BR>
     * C：　@現物<BR>
     * D：　@信用<BR>
     * E：　@先物・オプション<BR>
     * F：　@先物<BR>
     * G：　@オプション<BR>
     * H：　@投信・累投<BR>
     * I：　@入出金<BR>
     * J：　@譲渡益税<BR>
     * K：　@外国株式<BR>
     * <BR>
     */
    public String commodityType;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (取引履歴一覧ファ@イルダウンロードリクエスト)<BR>
     * @@roseuid 41368C40004F
     */
    public WEB3HistoryTradeHistoryDownloadRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@表示期間チェック<BR>
     * 　@this.表示期間From != null かつ this.表示期間To != nullの場合は、<BR>
     * 　@以下のチェックを行う。<BR>
     * 　@１－１）this.表示期間FromをDate型に変換し、エラーが発生した場合は、<BR>
     * 　@　@　@　@　@「表示期間(自)日付エラー」の例外をスローする。<BR>
     *            class:  WEB3BusinessLayerException<BR>
     *            tag  : BUSINESS_ERROR_01065<BR>
     * <BR>
     * 　@１－２）this.表示期間ToをDate型に変換し、エラーが発生した場合は、<BR>
     * 　@　@　@　@　@「表示期間(至)日付エラー」の例外をスローする。<BR>
     *            class:  WEB3BusinessLayerException<BR>
     *            tag  : BUSINESS_ERROR_01066<BR>
     * <BR>
     * 　@１－３）this.表示期間From > this.表示期間Toである場合は、<BR>
     * 　@　@　@　@　@「表示期間(自)(至)整合性エラー」の例外をスローする。<BR>
     *            class:  WEB3BusinessLayerException<BR>
     *            tag  : BUSINESS_ERROR_01051<BR>
     * <BR>
     * ２）　@銘柄コードチェック<BR>
     * 　@this.銘柄コード != nullの場合は、以下のチェックを行う。<BR>
     * 　@２－１）this.銘柄コードが以下の条件に該当する場合は、<BR>
     * 　@　@　@　@　@「銘柄コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@・this.銘柄コード.length() != 4桁 かつ 5桁<BR>
     *            class:  WEB3BusinessLayerException<BR>
     *            tag  : BUSINESS_ERROR_00439<BR>
     * <BR>
     * 　@２－２）this.商品区分が以下に該当しない場合、<BR>
     * 　@　@　@　@　@「商品整合性エラー」の例外をスローする。<BR>
     * 　@　@　@　@　@・”全商品”<BR>
     * 　@　@　@　@　@・”現物・信用”<BR>
     * 　@　@　@　@　@・”現物”<BR>
     * 　@　@　@　@　@・”信用”<BR>
     * 　@　@　@　@　@・”外株”<BR>
     *            class:  WEB3BusinessLayerException<BR>
     *            tag  : BUSINESS_ERROR_01068<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41368C40003F
     */
    public void validate() throws  WEB3BaseException
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

         //１）　@表示期間チェック
         //　@this.表示期間From != null かつ this.表示期間To != nullの場合は、
         //　@以下のチェックを行う。
         if (this.listStartDate != null && this.listEndDate != null)
         {
              //１－１）this.表示期間FromをDate型に変換し、エラーが発生した場合は、
              //   　@　@「表示期間(自)日付エラー」の例外をスローする。
              if (!(WEB3StringTypeUtility.isDateStr(this.listStartDate, "yyyyMMdd"))) 
              {
                   log.error(" 表示期間(自)日付エラー 。");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01065,
                       this.getClass().getName() + STR_METHOD_NAME,
                       "表示期間（自）日付フォーマットエラー。");              
              }

              //１－２）this.表示期間ToをDate型に変換し、エラーが発生した場合は、
              // 　@　@　@「表示期間(至)日付エラー」の例外をスローする。
              if (!(WEB3StringTypeUtility.isDateStr(this.listEndDate, "yyyyMMdd"))) 
              {
                   log.error(" 表示期間(至)日付エラー 。");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01066,
                       this.getClass().getName() + STR_METHOD_NAME,
                       "表示期間（至）日付フォーマットエラー。");              
              }               
               
              //１－３）this.表示期間From > this.表示期間Toである場合は、
              // 　@　@　@「表示期間(自)(至)整合性エラー」の例外をスローする。
              Date l_datListStartDate = WEB3DateUtility.getDate(this.listStartDate, "yyyyMMdd");
              Date l_datListEndDate = WEB3DateUtility.getDate(this.listEndDate, "yyyyMMdd");
              if (WEB3DateUtility.compareToDay(l_datListStartDate,l_datListEndDate) > 0)
              {
                   log.error(" 表示期間(自)(至)整合性エラー 。");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01051,
                       this.getClass().getName() + STR_METHOD_NAME,
                       "表示期間（自）は表示期間（至）より大きいです。");              
              }               
         }

         //２）　@銘柄コードチェック
         // 　@this.銘柄コード != nullの場合は、以下のチェックを行う。
         if (this.productCode != null)
         {
               //２－１）this.銘柄コードが以下の条件に該当する場合は、
               // 　@　@　@「銘柄コードエラー」の例外をスローする。
               // 　@　@　@・this.銘柄コード.length() != 4桁 かつ 5桁
               if ((WEB3StringTypeUtility.getByteLength(this.productCode) != 4 
                   && WEB3StringTypeUtility.getByteLength(this.productCode) != 5 ))
               {
                   log.error(" 銘柄コードエラー 。");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                       this.getClass().getName() + STR_METHOD_NAME,
                       "銘柄コードのサイズが不正です。");
               }               
               
               //２－２）this.商品区分が以下に該当しない場合、
               // 　@　@　@「商品整合性エラー」の例外をスローする。
               // 　@　@　@・”全商品”
               // 　@　@　@・”現物・信用”
               // 　@　@　@・”現物”
               // 　@　@　@・”信用”
			   // 　@　@　@・”外国株式”
               if (!WEB3TradeHistoryProductDivDef.ALL_PRODUCT.equals(this.commodityType) 
                   && !WEB3TradeHistoryProductDivDef.EQUITY_MARGIN.equals(this.commodityType) 
                   && !WEB3TradeHistoryProductDivDef.EQUITY.equals(this.commodityType) 
                   && !WEB3TradeHistoryProductDivDef.MARGIN.equals(this.commodityType)
			       && !WEB3TradeHistoryProductDivDef.FOREIGN.equals(this.commodityType))
               {
                   log.error(" 商品整合性エラー 。");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                       this.getClass().getName() + STR_METHOD_NAME,
                       "商品区分が存在しないコード値です。");
               }
         }
         
         log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 41789C4B0242
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3HistoryTradeHistoryDownloadResponse(this);
    }
}
@
