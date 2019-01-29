head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引履歴一覧リクエスト(WEB3HistoryTradeHistoryListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26  温 顕 法@(中訊) 新規作成
                   2005/11/08  王維（日本中訊）外株対応 
                   2006/10/19  張騰宇 (中訊) モデル 057
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
 * (取引履歴一覧リクエスト)
 * 取引履歴一覧リクエストクラス
 * 
 * @@author 温 顕 法@
 * @@version 1.0
 */
public class WEB3HistoryTradeHistoryListRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。 
     */
     private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3HistoryTradeHistoryListRequest.class); 
  
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tradeHistory_tradeHistoryList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410221710L;
     
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
     * L：　@債券<BR>
     */
    public String commodityType;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     */
    public String pageSize;
    /**
     * ソートキー
     */
    public WEB3HistorySortKeyUnit[] sortKeys;
    /**
     * (取引履歴一覧リクエスト)<BR>
     * @@roseuid 41368C40004F
     */
    public WEB3HistoryTradeHistoryListRequest() 
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
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            : BUSINESS_ERROR_01065               <BR>
     * <BR>
     * 　@１－２）this.表示期間ToをDate型に変換し、エラーが発生した場合は、<BR>
     * 　@　@　@　@　@「表示期間(至)日付エラー」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            : BUSINESS_ERROR_01066               <BR>
     * <BR>
     * 　@１－３）this.表示期間From > this.表示期間Toである場合は、<BR>
     * 　@　@　@　@　@「表示期間(自)(至)整合性エラー」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            : BUSINESS_ERROR_01051              <BR>
     * <BR>
     * ２）　@銘柄コードチェック<BR>
     * 　@this.銘柄コード != nullの場合は、以下のチェックを行う。<BR>
     * 　@２－１）this.銘柄コードが以下の条件に該当する場合は、<BR>
     * 　@　@　@　@　@「銘柄コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@・this.銘柄コード.length() != 4桁 かつ 5桁<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            : BUSINESS_ERROR_01067               <BR>
     * <BR>
     * 　@２－２）this.商品区分が以下に該当しない場合、<BR>
     * 　@　@　@　@　@「商品整合性エラー」の例外をスローする。<BR>
     * 　@　@　@　@　@・”全商品”<BR>
     * 　@　@　@　@　@・”現物・信用”<BR>
     * 　@　@　@　@　@・”現物”<BR>
     * 　@　@　@　@　@・”信用”<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            : BUSINESS_ERROR_01068               <BR>
     * <BR>
     * ３）　@ソートキーチェック<BR>
     * 　@３－１）this.ソートキー == nullであった場合<BR>
     * 　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            : BUSINESS_ERROR_00231               <BR>
     * <BR>
     * 　@３－２）this.ソートキー.要素数 == 0だった場合<BR>
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            : BUSINESS_ERROR_00232               <BR>
     * <BR>
     * 　@３－３）this.ソートキーの全要素に対して<BR>
     * 　@　@　@　@下記のチェックを行う。<BR>
     * 　@　@３－３－１）ソートキー.validate()をコールする。<BR>
     * <BR> 
     * ４）要求ページ番号チェック<BR>
     * 　@４－１）this.要求ページ番号 == nullであった場合、<BR>
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00089              <BR>
     * <BR>
     * 　@４－２）this.要求ページ番号が数字以外の値であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            : BUSINESS_ERROR_00090               <BR>
     * <BR>
     * 　@４－３）this.要求ページ番号 <= 0であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            : BUSINESS_ERROR_00616               <BR>
     * <BR>
     * ５）ページ内表示行数チェック<BR>
     * 　@５－１）this.ページ内表示行数 == nullであった場合、<BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            : BUSINESS_ERROR_00091               <BR>
     * <BR>
     * 　@５－２）this.ページ内表示行数が数字以外の値であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            : BUSINESS_ERROR_00092               <BR>
     * <BR>
     * 　@５－３）this.ページ内表示行数 <= 0であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            : BUSINESS_ERROR_00617               <BR>
     * @@roseuid 41368C40003F
     */
    public void validate() throws  WEB3BaseException
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
         // １）　@表示期間チェック
         // 　@this.表示期間From != null かつ this.表示期間To != nullの場合は、
         // 　@以下のチェックを行う。
         if (this.listStartDate != null && this.listEndDate != null)
         {
               
              // 　@１－１）this.表示期間FromをDate型に変換し、エラーが発生した場合は、
              // 　@　@　@　@　@「表示期間(自)日付エラー」の例外をスローする。
              if (!(WEB3StringTypeUtility.isDateStr(this.listStartDate, "yyyyMMdd"))) 
              {
                   log.error(" 表示期間(自)日付エラー 。");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01065,
                       this.getClass().getName() + STR_METHOD_NAME);              
              }

              // 　@１－２）this.表示期間ToをDate型に変換し、エラーが発生した場合は、
              // 　@　@　@　@　@「表示期間(至)日付エラー」の例外をスローする。
              if (!(WEB3StringTypeUtility.isDateStr(this.listEndDate, "yyyyMMdd"))) 
              {
                   log.error(" 表示期間(至)日付エラー 。");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01066,
                       this.getClass().getName() + STR_METHOD_NAME);              
              }               
               
              // 　@１－３）this.表示期間From > this.表示期間Toである場合は、
              // 　@　@　@　@　@「表示期間(自)(至)整合性エラー」の例外をスローする。
              Date l_datListStartDate = WEB3DateUtility.getDate(this.listStartDate, "yyyyMMdd");
              Date l_datListEndDate = WEB3DateUtility.getDate(this.listEndDate, "yyyyMMdd");
              
              if (WEB3DateUtility.compareToDay(l_datListStartDate,l_datListEndDate) > 0)
              {
                   log.error(" 表示期間(自)(至)整合性エラー 。");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01051,
                       this.getClass().getName() + STR_METHOD_NAME);              
              }               
         }

         // ２）　@銘柄コードチェック
         // 　@this.銘柄コード != nullの場合は、以下のチェックを行う。
         if (this.productCode != null)
         {

               // 　@２－１）this.銘柄コードが以下の条件に該当する場合は、
               // 　@　@　@　@　@「銘柄コードエラー」の例外をスローする。
               // 　@　@　@　@　@・this.銘柄コード.length() != 4桁 かつ 5桁
               if ((WEB3StringTypeUtility.getByteLength(this.productCode) != 4 
                   && WEB3StringTypeUtility.getByteLength(this.productCode) != 5 ))
               {
                   log.error(" 銘柄コードエラー 。");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                       this.getClass().getName() + STR_METHOD_NAME);
               }               
               
               // 　@２－２）this.商品区分が以下に該当しない場合、
               // 　@　@　@　@　@「商品整合性エラー」の例外をスローする。
               // 　@　@　@　@　@・”全商品”
               // 　@　@　@　@　@・”現物・信用”
               // 　@　@　@　@　@・”現物”
               // 　@　@　@　@　@・”信用”
			   // 　@　@　@　@　@・”外国株式”
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
                       this.getClass().getName() + STR_METHOD_NAME);
               }
         }
         
         // ３）　@ソートキーチェック
         // 　@３－１）this.ソートキー == nullであった場合
         // 　@　@　@　@「ソートキーがnull」の例外をスローする。
         if (this.sortKeys == null)
         {
             //例外
             log.error(" ソートキーがnull 。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                 this.getClass().getName() + STR_METHOD_NAME);               
         }
         // 　@３－２）this.ソートキー.要素数 == 0だった場合
         // 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。
         if (this.sortKeys.length == 0)
         {
             //例外
             log.error(" ソートキー.要素数が0 ");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                 this.getClass().getName() + STR_METHOD_NAME);
         
         }
         
         // 　@３－３）this.ソートキーの全要素に対して
         // 　@　@　@　@下記のチェックを行う。
         // 　@　@３－３－１）ソートキー.validate()をコールする。
         int l_intLength = this.sortKeys.length;
         for (int i = 0; i < l_intLength; i++)
         {
             this.sortKeys[i].validate();
         }
         
         
         // ４）要求ページ番号チェック
         //
         // 　@４－１）this.要求ページ番号 == nullであった場合、
         // 　@　@　@　@「要求ページ番号がnull」の例外をスローする。
         if (this.pageIndex == null)
         {
              //例外
              log.error(" 要求ページ番号がnull 。");
              log.exiting(STR_METHOD_NAME);
              throw new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00089, 
                  this.getClass().getName() + STR_METHOD_NAME);          
         }
         
         // 　@４－２）this.要求ページ番号が数字以外の値であった場合、
         // 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。
         
         if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
         {
              //例外
              log.error(" 要求ページ番号が数字以外 。");
              log.exiting(STR_METHOD_NAME);
              throw new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                  this.getClass().getName() + STR_METHOD_NAME);         
         }

         // 　@４－３）this.要求ページ番号 <= 0であった場合、
         // 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。
         if (Integer.parseInt(this.pageIndex) <= 0)
         {
              //例外
              log.error(" 要求ページ番号が0以下 。");
              log.exiting(STR_METHOD_NAME);
              throw new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                  this.getClass().getName() + STR_METHOD_NAME);         
         }         
         
         // ５）ページ内表示行数チェック
         // 　@５－１）this.ページ内表示行数 == nullであった場合、
         // 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。
         if (this.pageSize == null)
         {
              //例外
              log.error(" ページ内表示行数がnull 。");
              log.exiting(STR_METHOD_NAME);
              throw new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                  this.getClass().getName() + STR_METHOD_NAME);          
         }         
         
         // 　@５－２）this.ページ内表示行数が数字以外の値であった場合、
         // 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。
         if (!WEB3StringTypeUtility.isNumber(this.pageSize))
         {
              //例外
              log.error(" ページ内表示行数が数字以外 。");
              log.exiting(STR_METHOD_NAME);
              throw new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                  this.getClass().getName() + STR_METHOD_NAME);         
         }         
         
         // 　@５－３）this.ページ内表示行数 <= 0であった場合、
         // 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。
         if (Integer.parseInt(this.pageSize) <= 0)
         {
              //例外
              log.error("ページ内表示行数が0以下 。");
              log.exiting(STR_METHOD_NAME);
              throw new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                  this.getClass().getName() + STR_METHOD_NAME);         
         }
         
         log.exiting(STR_METHOD_NAME);
         
    }
    
    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 41789C4B0242
     */
    public WEB3GenResponse createResponse() 
    {
     return new WEB3HistoryTradeHistoryListResponse(this);
    }
}
@
