head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginExecuteReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引注文約定照会リクエストクラス(WEB3MarginExecuteReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 李松峰 (中訊) 新規作成
Revesion History : 2007/10/16 金傑(中訊) 仕様変更モデル1197
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderExecStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （信用取引注文約定照会リクエスト）。<br>
 * <br>
 * 信用取引注文約定照会リクエストクラス
 * @@version 1.0
 */
public class WEB3MarginExecuteReferenceRequest extends WEB3GenRequest 
{
    /**
     * （Logger）。
     */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginExecuteReferenceRequest.class);
    /**
     * （PTYPE）。
     */
    public static final String PTYPE = "margin_executeReference";

    /**
     * （SerialVersionUID）。
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * （照会区分）。<BR>
     * <BR>
     * 0：注文約定照会（デフォルト）<BR>
     * 1：訂正取消一覧（訂正取消可能なもののみ表示）
     */
    public String referenceType;
    
    /**
     * （銘柄コード）。
     */
    public String productCode;
    
    /**
     * （市場コード）。
     */
    public String marketCode;
    
    /**
     * （約定状態区分）。
     * <BR>
     * 0：未約定　@1：一部成立　@2：全部成立
     */
    public String execType;
    
    /**
     * （発注日）。<BR>
     * <BR>
     * 発注日
     */
    public Date orderBizDate;

    /**
     * (発注条件区分)<BR>
     * 発注条件区分<BR>
     * <BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String orderCondType;
    
    /**
     * （信用取引ソートキー）。<BR>
     * <BR>
     * 対象項目：銘柄コード、口座区分、市場コード、<BR>
     * 取引区分、値段条件、執行条件、発注条件、注文時間、<BR>
     * 発注日、注文期限、弁済区分、弁済期限値
     */
    public WEB3MarginSortKey[] sortKeys;
    
    /**
     * （要求ページ番号）。<BR>
     * <BR>
     * 表示させたいページ位置を指定　@※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * （ページ内表示行数）。<BR>
     * <BR>
     * １ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;
    
    /**
     * @@roseuid 414048CA01BC
     */
    public WEB3MarginExecuteReferenceRequest() 
    {
     
    }
    
    /**
     * （validate）。<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>                                                                   
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>                                                     
     * <BR>                                                                                                             
     * １）　@照会区分チェック<BR>                                                                                       
     * 　@１－１）this.照会区分＝nullであった場合、「照会区分がnull」の例外をスローする。<BR>                            
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00081<BR>                                                                          
     * <BR>                                                                                                             
     * 　@１－２）this.照会区分が下記の値以外が設定されていたら、「照会区分が未定義の値」の例外をスローする。<BR>        
     * 　@　@　@　@・「0：注文約定照会」<BR>                                                                                
     * 　@　@　@　@・「1：訂正取消一覧(訂正取消可能なもののみ表示)」<BR>                                                    
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00082<BR>                                                                          
     * <BR>                                                                                                             
     * ２）　@約定状態区分チェック<BR>                                                                                   
     * 　@２－１）this.約定状態区分≠null かつ、<BR>                                                                     
     * 　@　@　@　@this.約定状態区分が下記の値以外が設定されていたら、「約定状態区分が未定義の値」の例外をスローする。<BR>  
     * 　@　@　@　@・「0：未約定」<BR>                                                                                      
     * 　@　@　@　@・「1：一部成立」<BR>                                                                                    
     * 　@　@　@　@・「2：全部成立」<BR>                                                                                                                                                                       
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00626<BR>                                                                          
     * <BR>                                                                                                             
     * ３）　@ソートキーチェック<BR>                                                                                     
     * 　@３－１）this.ソートキー＝nullであった場合、　@「ソートキーがnull」の例外をスローする。<BR>                      
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00231<BR>                                                                          
     * <BR>                                                                                                             
     * 　@３－２）this.ソートキー.要素数＝０だった場合、「ソートキー.要素数が0」の例外をスローする。<BR>                 
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00232<BR>                                                                          
     * <BR>                                                                                                             
     * 　@３－３）this.ソートキーの全要素に対して　@下記のチェックを行う。<BR>                                            
     * 　@　@３－３－１）ソートキー.validate()をコールする。<BR>                                                          
     * <BR>                                                                                                             
     * 　@　@３－３－２）ソートキー.キー項目に下記の項目以外が　@設定されていたら、                                        
     * 　@　@　@　@　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>                                        
     * 　@　@　@　@　@　@　@・「銘柄コード」<BR>                                                                               
     * 　@　@　@　@　@　@　@・「口座区分」<BR>                                                                                 
     * 　@　@　@　@　@　@　@・「市場コード」<BR>                                                                               
     * 　@　@　@　@　@　@　@・「取引区分」<BR>
     *              ・「値段条件」<BR>                                                                                  
     * 　@　@　@　@　@　@　@・「執行条件」<BR>                                                                                 
     * 　@　@　@　@　@　@　@・「発注条件」<BR>                                                                                 
     * 　@　@　@　@　@　@　@・「注文時間」<BR>
     *              ・「発注日」<BR>                                                                                 
     * 　@　@　@　@　@　@　@・「注文期限」<BR>                                                                                 
     * 　@　@　@　@　@　@　@・「弁済区分」<BR>                                                                                 
     * 　@　@　@　@　@　@　@・「弁済期限値」<BR>                                                                               
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00086<BR>                                                                          
     * <BR>                                                                                                             
     * ４）要求ページ番号チェック<BR>                                                                                   
     * 　@４－１）this.要求ページ番号＝nullであった場合、<BR>
     *        「要求ページ番号がnull」の例外をスローする。<BR>                
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00089<BR>                                                                          
     * <BR>                                                                                                             
     * 　@４－２）this.要求ページ番号が数字以外の値であった場合、<BR>
     *         「要求ページ番号が数字以外」の例外をスローする。<BR>    
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00090<BR>                                                                          
     * <BR>                                                                                                             
     * 　@４－３）this.要求ページ番号≦０であった場合、<BR>
     *         「要求ページ番号が0以下」の例外をスローする。<BR>                 
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00616<BR>                                                                          
     * <BR>                                                                                                             
     * ５）ページ内表示行数チェック<BR>                                                                                 
     * 　@５－１）this.ページ内表示行数＝nullであった場合、<BR>
     *         「ページ内表示行数がnull」の例外をスローする。<BR>            
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00091<BR>                                                                          
     * <BR>                                                                                                             
     * 　@５－２）this.ページ内表示行数が数字以外の値であった場合、<BR>
     *         「ページ内表示行数が数字以外」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00092<BR>                                                                          
     * 　@<BR>                                                                                                           
     * 　@５－３）this.ページ内表示行数≦０であった場合、
     *        「ページ内表示行数が0以下」の例外をスローする。<BR>             
     *         class: WEB3BusinessLayerException<BR>                                                                    
     *         tag:   BUSINESS_ERROR_00617<BR> 
     * <BR>
     *   ６）　@市場コードチェック 
     *        this.市場コード≠null、かつ下記の値以外の場合、<BR>
     *        「市場コードが未定義の値」の例外をスローする。
     * 　@　@　@　@　@・１：東京 
     * 　@　@　@　@　@・２：大阪 
     * 　@　@　@　@　@・３：名古屋 
     * 　@　@　@　@　@・６：福岡 
     * 　@　@　@　@　@・８：札幌 
     * 　@　@　@　@　@・９：NNM 
     *          ・１０：JASDAQ
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     * ７）　@発注条件区分チェック<BR>
     * 　@this.発注条件区分≠null、<BR>
     * 　@かつ下記の値以外の場合、<BR>
     *　@「発注条件区分が未定義の値」の例外をスローする。<BR>
     * 　@　@・０：指定なし<BR>
     * 　@　@・１：逆指値<BR>
     * 　@　@・２：W指値<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00212<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40866C06027E
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@照会区分チェック
        if (referenceType == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00081,STR_METHOD_NAME);
        }

        if (!WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(referenceType) && !WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(referenceType))
        {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00082,STR_METHOD_NAME); 
        }
        
        // ２）　@約定状態区分チェック            
        if ((execType != null) &&
            !(WEB3OrderExecStatusDef.EXECUTED.equals(execType) || WEB3OrderExecStatusDef.PARTIALLY_EXECUTED.equals(execType)
            || WEB3OrderExecStatusDef.UNEXECUTED.equals(execType)))
        {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00626,STR_METHOD_NAME);            
        }
        
        // ３）　@ソートキーチェック
        if (sortKeys == null)
        {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231,STR_METHOD_NAME);
        }

        if (sortKeys.length == 0)
        {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232,STR_METHOD_NAME);
        }                                        
       
         for (int i = 0;i < sortKeys.length;i++)
         {
    
            sortKeys[i].validate();
            
            if (!WEB3EquityKeyItemDef.PRODUCTCODE.equals(sortKeys[i].keyItem) && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(sortKeys[i].keyItem) 
                && !WEB3EquityKeyItemDef.TRADEMARKET.equals(sortKeys[i].keyItem) && !WEB3EquityKeyItemDef.TRADETYPE.equals(sortKeys[i].keyItem) 
                && !WEB3EquityKeyItemDef.EXECUTE_COND.equals(sortKeys[i].keyItem) && !WEB3EquityKeyItemDef.SEND_COND.equals(sortKeys[i].keyItem) 
                && !WEB3EquityKeyItemDef.ORDER_TIME.equals(sortKeys[i].keyItem) && !WEB3EquityKeyItemDef.ORDER_TIMELIMIT.equals(sortKeys[i].keyItem) 
                && !WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(sortKeys[i].keyItem) && !WEB3EquityKeyItemDef.REPAYMENTNUM.equals(sortKeys[i].keyItem)
			    && !WEB3EquityKeyItemDef.PRICE_COND.equals(sortKeys[i].keyItem) && !WEB3EquityKeyItemDef.SEND_DATE.equals(sortKeys[i].keyItem))
            {
                
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086,STR_METHOD_NAME);
            }    
         } 

         // ４）要求ページ番号チェック                                                               
         if (pageIndex == null)
         {
            
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00089,STR_METHOD_NAME);
            
         }

         if (!WEB3StringTypeUtility.isNumber(pageIndex))
         {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090,STR_METHOD_NAME);
         }
    
         long l_longTemp= Long.parseLong(pageIndex);
         if (l_longTemp <= 0)
         {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616,STR_METHOD_NAME);
         }

         if (pageSize == null)
         {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091,STR_METHOD_NAME);
         }

         if (!WEB3StringTypeUtility.isNumber(pageSize))
         {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092,STR_METHOD_NAME);
         }
         if (Long.parseLong(pageSize) <= 0)
         {
            throw new  WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617,STR_METHOD_NAME);     
         }   
 
		//  ６）　@市場指定チェック
		
		if (this.marketCode != null
				&& !WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
				&& !WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
				&& !WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
				&& !WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
				&& !WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
				&& !WEB3MarketCodeDef.NNM.equals(this.marketCode)
				&& !WEB3MarketCodeDef.JASDAQ.equals(this.marketCode))
		{
			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00608,STR_METHOD_NAME);
		}

        // ７）　@発注条件区分チェック
        // this.発注条件区分≠null、且つ下記の値以外の場合、「発注条件区分が未定義の値」の例外をスローする。
        if (this.orderCondType != null)
        {
            if (!(WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
                || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
                || WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)))
            {
                log.debug("発注条件区分の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00212,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "発注条件区分の値が存在しないコード値です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 414048CA01E4
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginExecuteReferenceResponse(this);
    }
}
@
