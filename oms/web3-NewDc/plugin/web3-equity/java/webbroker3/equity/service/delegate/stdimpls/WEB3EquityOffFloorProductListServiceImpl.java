head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOffFloorProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 立会外分売銘柄一覧サービス(WEB3EquityOffFloorProductListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 坂上(SRA) 新規作成
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.data.OffFloorOrderProductRow;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.message.WEB3EquitySortKey;
import webbroker3.equity.message.WEB3EquityOffFloorProductGroup;
import webbroker3.equity.message.WEB3EquityOffFloorProductListRequest;
import webbroker3.equity.message.WEB3EquityOffFloorProductListResponse;
import webbroker3.equity.service.delegate.WEB3EquityOffFloorProductListService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

/**
 * （立会外分売銘柄一覧サービスImpl）。<BR>
 * <BR>
 * 立会外分売銘柄一覧サービス実装クラス
 * @@version 1.0
 */
public class WEB3EquityOffFloorProductListServiceImpl
	extends WEB3EquityClientRequestService
	implements WEB3EquityOffFloorProductListService 
{

	/**
	 * (ログ出力ユーティリティ。)
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3EquityOffFloorProductListServiceImpl.class);
	/**
	 * @@roseuid XXXXXXXXXXX
	 */
	public WEB3EquityOffFloorProductListServiceImpl()
	{

	}

    /**
     * 立会外分売銘柄一覧サービス処理を実施する。<BR>
     * <BR>
     * get銘柄一覧()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.common.message.WEB3GenResponse<BR>
     * @@throws webbroker3.common.WEB3BaseException<BR>
     * @@roseuid XXXXXXXXXXXX
     */ 
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String l_strMethodName =
            getClass().getName() + ".execute(WEB3GenRequest)";

        if (l_request == null)
        {
			log.error("*******リクエストの値がNullです*******");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_strMethodName);
        }
        else if (l_request instanceof WEB3EquityOffFloorProductListRequest)
        {
            return this.getProductList((WEB3EquityOffFloorProductListRequest) l_request);
        }
        else
        {
            log.error("*******リクエストの型が不正です*******");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_strMethodName);
        }
    }

    /**
     * (get銘柄一覧)<BR>
     * 銘柄一覧<BR>
     * の処理を行う。<BR>
     * <BR>
     * シーケンス図「（立会外分売銘柄一覧）get銘柄一覧」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 立会外分売銘柄一覧リクエストオブジェクト<BR>
     * @@return webbroker3.equity.message.WEB3EquityExecuteReferenceResponse<BR>
     * @@throws webbroker3.common.WEB3BaseException<BR>
     * @@roseuid XXXXXXXXXXX
     */
    protected WEB3EquityOffFloorProductListResponse getProductList(WEB3EquityOffFloorProductListRequest l_request) throws WEB3BaseException 
	{
		final String STR_METHOD_NAME = "getProductList()";
		log.entering(STR_METHOD_NAME);
   
        WEB3EquityOffFloorProductListResponse l_response = null;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule
                   = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);            
        WEB3EquityOrderManager l_eqOrderMgr =
                     (WEB3EquityOrderManager) l_tradingModule.getOrderManager();    
                            
        WEB3EquityProductManager l_eqProdMgr
                = (WEB3EquityProductManager) l_tradingModule.getProductManager();

        l_request.validate();
    
        //validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        SubAccount l_subAccount = this.getSubAccount(); 
    
        //validate取引可能顧客(補助口座)
        OrderValidationResult l_validationResult = null;
    
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
             (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        l_validationResult = l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
    
        if (l_validationResult != OrderValidationResult.VALIDATION_OK_RESULT)
        {
            log.error("*******取引可能顧客ではありません*******");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 l_validationResult.getProcessingResult().getErrorInfo(),
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GentradeBranch l_branch = null;
        l_branch =
             (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
       
        Institution l_institution = null;
        l_institution = l_subAccount.getInstitution();   
         
		l_response = (WEB3EquityOffFloorProductListResponse) l_request.createResponse();
		
		//get立会外分売銘柄一覧(ソートキー　@現物株式ソートキー[])
		WEB3EquitySortKey[] l_sortKeys = l_request.sortKeys;
		OffFloorOrderProductRow[] l_strOffFloorProdRow = this.getOffFloorProductList(l_sortKeys);
		if (l_strOffFloorProdRow == null)
		{
			l_response.messageSuspension = null;
			l_response.productList =  null;
			return l_response;
		}        

		//get市場閉局警告市場
		String[] l_TradeCloseMarket = null;
		l_TradeCloseMarket = 
			WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
					l_branch,
					ProductTypeEnum.EQUITY,
					"0");

        //銘柄一覧格納用  
        ArrayList l_lstProductList = new ArrayList();

         //(取得した立会外分売銘柄オブジェクト数分Loop)        
		for (int i = 0;i<l_strOffFloorProdRow.length;i++)
		{
			Date l_datBizdate = GtlUtils.getTradingSystem().getBizDate();
            //受付終了日（YYYYMMDD）を過ぎている場合は、一覧の表示対象外とする。
            if (WEB3DateUtility.compareToDay(l_strOffFloorProdRow[i].getOrderEndDatetime(), l_datBizdate) < 0)
            {
                continue;
            }
             
            WEB3EquityOffFloorProductGroup l_product = new WEB3EquityOffFloorProductGroup();

            EqTypeProduct l_eqProd;
            WEB3EquityTradedProduct l_eqTrdProd;
			WEB3GentradeTradingTimeManagement.resetMarketCode(l_strOffFloorProdRow[i].getMarketCode());
            try
            {
                l_eqProd = l_eqProdMgr.getProduct(l_institution,l_strOffFloorProdRow[i].getProductCode());
                l_eqTrdProd = 
					(WEB3EquityTradedProduct) l_eqProdMgr.getTradedProduct(
					l_institution, l_strOffFloorProdRow[i].getProductCode(), l_strOffFloorProdRow[i].getMarketCode());
            }
            catch (NotFoundException e)
            {
                continue;
            }

            //プロパティをセットする
            l_product.productCode = l_strOffFloorProdRow[i].getProductCode();
            EqtypeProductRow l_productRow = (EqtypeProductRow) l_eqProd.getDataSourceObject();
            l_product.productName = l_productRow.getStandardName();
            l_product.marketCode  = l_strOffFloorProdRow[i].getMarketCode();
            l_product.orderStartDatetime = l_strOffFloorProdRow[i].getOrderStartDatetime();
            l_product.orderEndDatetime   = l_strOffFloorProdRow[i].getOrderEndDatetime();
            if (l_strOffFloorProdRow[i].getOffFloorOrderPriceIsNull() == false)
            {
				l_product.offFloorOrderPrice
				= WEB3StringTypeUtility.formatNumber(l_strOffFloorProdRow[i].getOffFloorOrderPrice());
            }
            if (l_strOffFloorProdRow[i].getMaxApplyQuantityIsNull() == false)
            {
				l_product.maxApplyQuantity
				= WEB3StringTypeUtility.formatNumber(l_strOffFloorProdRow[i].getMaxApplyQuantity());
            }
            l_product.applyUnit = WEB3StringTypeUtility.formatNumber(l_eqTrdProd.getLotSize());

            //実施日前営業日終値を取得し、同名プロパティにセットする
            double l_dbllastClosingPrice = 0.0D;
			Date l_orderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            if (WEB3DateUtility.compareToDay(l_strOffFloorProdRow[i].getOrderEndDatetime(),l_orderBizDate) <= 0)
            {
                if (l_strOffFloorProdRow[i].getLastClosingPriceIsNull())
                {
                    //時価(*1)の取得
                    //get時価(取引銘柄　@EqTypeTradedProduct)
					l_dbllastClosingPrice = l_eqProdMgr.getCurrentPrice(l_eqTrdProd);
                    l_product.lastClosingPrice = WEB3StringTypeUtility.formatNumber(l_dbllastClosingPrice);
                }
                else if (l_strOffFloorProdRow[i].getLastClosingPrice() == 0)
                {
                    l_product.lastClosingPrice = null;
                }
                else
                {
					l_dbllastClosingPrice = l_strOffFloorProdRow[i].getLastClosingPrice();
                    l_product.lastClosingPrice = WEB3StringTypeUtility.formatNumber(l_dbllastClosingPrice);
                }
            }
            else
            {
                l_product.lastClosingPrice = null; 
            }
            if (l_dbllastClosingPrice == 0)
            {
				l_product.lastClosingPrice = null;            
            }
            
            //割引率を計算し、同名のプロパティにセットする
            if ((l_strOffFloorProdRow[i].getOffFloorOrderPriceIsNull() == false)
                 && (l_product.lastClosingPrice != null))
            {
				double l_dbldiscountRate = 
                     (1 - (l_strOffFloorProdRow[i].getOffFloorOrderPrice() / l_dbllastClosingPrice)) * 100;
				//結果は小数点第２位未満を切り捨てる。マイナス値の場合は0に近づける。
				BigDecimal l_bddiscountRate = new BigDecimal(l_dbldiscountRate);
				l_dbldiscountRate
				    = l_bddiscountRate.divide(new BigDecimal(1.0D), 2, BigDecimal.ROUND_DOWN).doubleValue();
				l_product.discountRate = WEB3StringTypeUtility.formatNumber(l_dbldiscountRate);
            }
            else
            {
                l_product.discountRate = null;
            }
        
            //買付可能かどうかチェックする
            //is取扱可能市場(市場コード　@銘柄タイプ)
            boolean l_blisPossibleMarket = 
                 l_branch.isHandlingPossibleMarket(l_strOffFloorProdRow[i].getMarketCode(),
                                                    ProductTypeEnum.EQUITY);
        
            boolean l_blInsiderOrderStop = true;
            try
            {
                //validateインサイダー(補助口座　@株式銘柄)
                l_eqOrderMgr.validateInsider(l_subAccount,l_eqProd);
            
                //validate顧客銘柄別取引停止(補助口座　@銘柄ID　@注文種別)
                l_eqOrderMgr.validateAccountProductOrderStop(l_subAccount,
                                                              l_eqProd.getProductId(),
                                                              OrderTypeEnum.EQUITY_BUY);
            }
            catch(WEB3BaseException b_ex)
            {
                l_blInsiderOrderStop = false;
            }
        
            //プロパティ「買付可能フラグ」をセット
            if(!(l_strOffFloorProdRow[i].getOffFloorOrderPriceIsNull())
                && !(l_strOffFloorProdRow[i].getMaxApplyQuantityIsNull())
                && (l_blisPossibleMarket)
                && (l_blInsiderOrderStop)
                && (WEB3DateUtility.compareToSecond(
                     l_strOffFloorProdRow[i].getOrderStartDatetime(),GtlUtils.getSystemTimestamp()) <= 0)
                && (WEB3DateUtility.compareToSecond(l_strOffFloorProdRow[i].getOrderEndDatetime(), 
                     GtlUtils.getSystemTimestamp()) > 0))
            {
                l_product.buyPossFlag = true; 
            }
            else
			{
                l_product.buyPossFlag = false;
			}
			//(生成・プロパティをセットした立会外分売銘柄明細のインスタンスを、レスポンス.銘柄一覧にaddする)
			l_lstProductList.add(l_product);
		}
		//レスポンスデータ(プロパティ)のセット
		l_response.messageSuspension = l_TradeCloseMarket;
		WEB3EquityOffFloorProductGroup[] l_pList = new WEB3EquityOffFloorProductGroup[l_lstProductList.size()];
		l_lstProductList.toArray(l_pList);
		l_response.productList = l_pList;

		log.exiting(STR_METHOD_NAME);
		return l_response;
    }
    

    /**
     * (get立会外分売銘柄一覧)<BR>
     * 【立会外分売銘柄テーブル】より、分売銘柄の一覧を取得する。<BR>
     * １）　@DB検索<BR>
     * 立会外分売銘柄テーブルを以下の条件で検索する。<BR>
     * [条件] <BR>
     * 証券会社コード = this.get補助口座( ).証券会社コード<BR>
     * <BR>
     * ※ソート条件に、引数のソートキー配列の内容を指定する。 <BR>
     * <BR>
     * (*1)現在日時：GtlUtils.getSystemTimestamp()で取得。<BR>
     * <BR>
     * ２）　@取得した全レコードの配列を返す。<BR>
     * <BR>
     * <BR>
     * @@param l_equitySortKeys- 現物株式ソートキーの配列
     * @@return java.lang.String[]
     * @@roseuid XXXXXXXXXXX
     */
    protected OffFloorOrderProductRow[] getOffFloorProductList(WEB3EquitySortKey[] l_equitySortKeys) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "WEB3EquityOffFloorProductListServiceImpl.getOffFloorProductList";
        log.entering(STR_METHOD_NAME);
	
        OffFloorOrderProductRow[] l_rows = null;
        
        SubAccount l_subAccount = this.getSubAccount();
        Institution l_institution = null;
        l_institution = l_subAccount.getInstitution();           
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        
        String l_strWhere =
            " institution_code = ?";
                      
        String l_strOrderBy = " ";
        for (int i = 0; i < l_equitySortKeys.length; i++)
        {
            l_strOrderBy = l_strOrderBy + " ";
            String l_strKeyItem = l_equitySortKeys[i].keyItem;
            if(WEB3EquityKeyItemDef.PRODUCTCODE.equals(l_strKeyItem))
            {
                //銘柄コード 
                l_strOrderBy = l_strOrderBy + "product_code";
            }
            else if(WEB3EquityKeyItemDef.TRADEMARKET.equals(l_strKeyItem))
            {
                //市場コード
                l_strOrderBy = l_strOrderBy + "to_number(market_code)";
            }
            else if(WEB3EquityKeyItemDef.ORDER_START_DATE_TIME.equals(l_strKeyItem))
            {
                //受付開始日時
                l_strOrderBy = l_strOrderBy + "order_start_datetime";
            }
            else if(WEB3EquityKeyItemDef.ORDER_END_DATE_TIME.equals(l_strKeyItem))
            {
                //受付終了日時
                l_strOrderBy = l_strOrderBy + "order_end_datetime";
            }
            
            l_strOrderBy = l_strOrderBy + " ";
            if (WEB3AscDescDef.ASC.equals(l_equitySortKeys[i].ascDesc)) 
            {
                l_strOrderBy = l_strOrderBy + "asc";
            } 
            else if (WEB3AscDescDef.DESC.equals(l_equitySortKeys[i].ascDesc)) 
            {
                l_strOrderBy = l_strOrderBy + "desc";
            }

            if (i != (l_equitySortKeys.length -1))
            {
                l_strOrderBy = l_strOrderBy + ", ";
            }
        }        
        log.debug("ソート条件 = " + l_strOrderBy);

        Object l_bindVars[] =
        {
            l_strInstitutionCode
        };
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    OffFloorOrderProductRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_bindVars);
                    
            int l_intSize = l_lisRows.size();
            if (l_intSize == 0)
            {
                return null;
            }
            else
            {
                l_rows = (OffFloorOrderProductRow[])l_lisRows.toArray(new OffFloorOrderProductRow[0]);
            }

        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        log.exiting(STR_METHOD_NAME);
        return l_rows;
    }
}@
