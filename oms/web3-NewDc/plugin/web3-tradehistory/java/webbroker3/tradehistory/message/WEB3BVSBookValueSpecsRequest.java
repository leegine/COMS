head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3BVSBookValueSpecsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 簿価単価明細照会リクエスト(WEB3BVSBookValueSpecsRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05  賈元春(中訊) 新規作成
*/
package webbroker3.tradehistory.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.tradehistory.define.WEB3PlsBvsDisplayTermDef;
import webbroker3.tradehistory.define.WEB3PlsBvsProductCodeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (簿価単価明細照会リクエスト)<BR>
 * 簿価単価明細照会リクエストクラス<BR>
 * 
 * @@author 賈元春
 * @@version 1.0 
 */
public class WEB3BVSBookValueSpecsRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3BVSBookValueSpecsRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PLSBVS_bookValueSpecs";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411051040L;
    
    /**
     * (表示期間)<BR>
     * 表示期間<BR>
     * <BR>
     * 0：　@前月月初以降(DEFAULT)<BR>
     * 1：　@1ヶ月分<BR>
     * 2：　@1週間分<BR>
     * 3：　@前日1日分<BR>
     */
    public String displayTerm;
    
    /**
     * (商品コード)<BR>
     * 商品コード<BR>
     * <BR>
     * 10:　@株式<BR>
     * 11:　@信用<BR>
     * 15:　@ミニ株<BR>
     * 20:　@投信<BR>
     * 21:　@外投<BR>
     * 22:　@累投<BR>
     * 23:　@MRF<BR>
     * 30:　@債券<BR>
     * 40:　@外株<BR>
     * 50:　@株先<BR>
     * 51： 株指数OP<BR>
     * 52:　@債先<BR>
     * 53:　@債先OP<BR>
     * 54:　@店OP<BR>
     * 55:　@外先<BR>
     * 56:　@外先OP<BR>
     * 57:　@株OP<BR>
     * 60:　@外債<BR>
     * 70:　@金<BR>
     * 71:　@金GP<BR>
     * 80:　@特殊<BR>
     * 91:　@CD<BR>
     * 92:　@CP<BR>
     * 93:　@BA<BR>
     * 99:　@金銭<BR>
     * <BR>
     */
    public String commodityCode;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
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
     * @@roseuid 418877BB02BF
     */
    public WEB3BVSBookValueSpecsRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@表示期間チェック<BR>
     * 　@１－１）this.表示期間 == nullの場合、<BR>
     * 　@　@　@　@　@表示期間がnull。<BR>
     *           class         :  WEB3BusinessLayerException           <BR>
     *           tag           :  BUSINESS_ERROR_01082              <BR>
     * <BR>
     * 　@１－２）this.表示期間が以下に示す値のいづれにも該当しない場合、<BR>
     * 　@　@　@　@　@「表示期間が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・"0：前月月初以降(DEFAULT)"<BR>
     * 　@　@　@　@　@　@・"1：1ヶ月分"<BR>
     * 　@　@　@　@　@　@・"2：1週間分"<BR>
     * 　@　@　@　@　@　@・"3：前日1日分"<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag           :  BUSINESS_ERROR_01083              <BR>
     * <BR>
     * ２）商品コードチェック<BR>
     * 　@２－１）this.商品コード == nullの場合、<BR>
     * 　@　@　@　@　@「商品コードがnull」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_01084              <BR>
     * <BR>
     * 　@２－２）this.商品コードが以下に示す値のいづれにも該当しない場合、<BR>
     * 　@　@　@　@　@「商品コードが未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・"10:株式"<BR>
     * 　@　@　@　@　@　@・"11:信用"<BR>
     *            ・"15:ミニ株"<BR>
     * 　@　@　@　@　@　@・"20:投信"<BR>
     * 　@　@　@　@　@　@・"21:外投"<BR>
     * 　@　@　@　@　@　@・"22:累投"<BR>
     * 　@　@　@　@　@　@・"23:MRF"<BR>
     * 　@　@　@　@　@　@・"30:債券"<BR>
     * 　@　@　@　@　@　@・"40:外株"<BR>
     * 　@　@　@　@　@　@・"50:株先"<BR>
     * 　@　@　@　@　@　@・"51:株指数OP"<BR>
     * 　@　@　@　@　@　@・"52:債先"<BR>
     * 　@　@　@　@　@　@・"53:債先OP"<BR>
     * 　@　@　@　@　@　@・"54:店OP"<BR>
     * 　@　@　@　@　@　@・"55:外先"<BR>
     * 　@　@　@　@　@　@・"56:外先OP"<BR>
     * 　@　@　@　@　@　@・"57:株OP"<BR>
     * 　@　@　@　@　@　@・"60:外債"<BR>
     * 　@　@　@　@　@　@・"70:金"<BR>
     * 　@　@　@　@　@　@・"71:金GP"<BR>
     * 　@　@　@　@　@　@・"80:特殊"<BR>
     * 　@　@　@　@　@　@・"91:CD"<BR>
     * 　@　@　@　@　@　@・"92:CP"<BR>
     * 　@　@　@　@　@　@・"93:BA"<BR>
     * 　@　@　@　@　@　@・"99:金銭"<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag           :  BUSINESS_ERROR_01085              <BR>
     * <BR>
     * ３）　@銘柄コードチェック<BR>
     * 　@３－１）this.銘柄コード == nullの場合、<BR>
     * 　@　@　@　@　@「銘柄コードがnull」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00079              <BR>
     * <BR>
     * 　@３－２）this.銘柄コードが以下の条件に該当する場合は、<BR>
     * 　@　@　@　@　@「銘柄コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@・this.銘柄コード != 数値<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00815              <BR>
     * <BR>
     * ４）銘柄名チェック<BR>
     * 　@４－１）this.銘柄名 == nullの場合、<BR>
     * 　@　@　@　@　@「銘柄名がnull」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_01062              <BR>
     * <BR>
     * ５）要求ページ番号チェック<BR>
     * 　@５－１）this.要求ページ番号 == nullであった場合、<BR>
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00089              <BR>
     * <BR>
     * 　@５－２）this.要求ページ番号が数字以外の値であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00090              <BR>
     * <BR>
     * 　@５－３）this.要求ページ番号 <= 0であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00616              <BR>
     * <BR>
     * ６）ページ内表示行数チェック<BR>
     * 　@６－１）this.ページ内表示行数 == nullであった場合、<BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00091              <BR>
     * <BR>
     * 　@６－２）this.ページ内表示行数が数字以外の値であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00092              <BR>
     * <BR>
     * 　@６－３）this.ページ内表示行数 <= 0であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00617              <BR>
     * <BR>
     * @@roseuid 416CDEDB0381
     */
    public void validate() throws  WEB3BaseException
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //表示期間チェック
        //表示期間がnull。
        if (this.displayTerm == null || "".equals(this.displayTerm))
        {
            log.error("「表示期間がnull」の例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01082,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //表示期間チェック
        //「表示期間が未定義の値」の例外をスローする。
        if (!WEB3PlsBvsDisplayTermDef.DEFAULT.equals(this.displayTerm) &&
            !WEB3PlsBvsDisplayTermDef.ONE_MONTH.equals(this.displayTerm) &&
            !WEB3PlsBvsDisplayTermDef.ONE_WEEK.equals(this.displayTerm) &&
            !WEB3PlsBvsDisplayTermDef.THE_PREVIOUS_DAY.equals(this.displayTerm))
        {
            log.error("「表示期間が未定義の値」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01083,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //商品コードチェック
        //「商品コードがnull」の例外をスローする。
        if (this.commodityCode == null || "".equals(this.commodityCode))
        {
            log.error("「商品コードがnull」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01084,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //商品コードチェック
        //「商品コードが未定義の値」の例外をスローする。
        if (!WEB3PlsBvsProductCodeDef.EQUITY.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.MARGIN.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.MINISTOCK.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.MUTUAL.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.FOREIGN_MUTUAL.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.RUITO.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.MRF.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.BOND.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.FOREIGN_STOCK.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.STOCK_FUTURES.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.STOCK_INDEX_OP.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.BOND_FUTURES.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.BOND_FUTURES_OP.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.BRANCH_OP.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.FOREIGN_FUTURES.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.FOREIGN_FUTURES_OP.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.STOCK_OP.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.FOREIGN_BOND.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.CASH.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.CASH_GP.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.SPECIAL.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.CD.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.CP.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.BA.equals(this.commodityCode) &&
            !WEB3PlsBvsProductCodeDef.MONEY.equals(this.commodityCode))
        {
            log.error("「商品コードが未定義の値」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01085,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //銘柄コードチェック
        //「銘柄コードがnull」の例外をスローする。
        if (this.productCode == null || "".equals(this.productCode))
        {
            log.error("「銘柄コードがnull」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + STR_METHOD_NAME);
        }
           
        //銘柄名チェック
        //「銘柄名がnull」の例外をスローする。
        if (this.productName == null || "".equals(this.productName))
        {
            log.error("「銘柄名がnull」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01062,
                this.getClass().getName() + STR_METHOD_NAME);
        }           
        
        //要求ページ番号チェック
        //「要求ページ番号がnull」の例外をスローする。
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            log.error("「要求ページ番号がnull」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //要求ページ番号チェック
        //「要求ページ番号が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.error("「要求ページ番号が数字以外」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME);
        }                 

        //要求ページ番号チェック
        //「要求ページ番号が0以下」の例外をスローする。
        if (Long.parseLong(this.pageIndex) <= 0)
        {
            log.error("「要求ページ番号が0以下」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME);
        }   
        
        //ページ内表示行数チェック
        //「ページ内表示行数がnull」の例外をスローする。
        if (this.pageSize == null || "".equals(this.pageSize))
        {
            log.error("「ページ内表示行数がnull」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME);
        }         
        
        //ページ内表示行数チェック
        //「ページ内表示行数が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.error("「ページ内表示行数が数字以外」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //ページ内表示行数チェック
        //「ページ内表示行数が0以下」の例外をスローする。
        if (Long.parseLong(this.pageSize) <= 0)
        {
            log.error("「ページ内表示行数が0以下」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME);
        } 
                      
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 418877BB02DE
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3BVSBookValueSpecsResponse(this);
    }
}
@
