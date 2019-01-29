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
filename	WEB3EquityProductInformationServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式銘柄情報表示サービスImpl(WEB3EquityProductInformationServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 坂上(SRA) 新規作成
Revesion History : 2007/12/19 趙林鵬 (中訊) モデル No.1238,1245,1266
Revesion History : 2009/09/21 車進 (中訊) モデル No.1334,No.1335,No.1341,No.1349
Revesion History : 2009/10/14 張騰宇 (中訊) モデル No.1351
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3MarginSysProductTypeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MarketOrderDesignateStopDef;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3PriceRangeTypeDef;
import webbroker3.common.define.WEB3TradeStopDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.define.WEB3EquityMarginAttributeDef;
import webbroker3.equity.define.WEB3EquityRegulationTypeDef;
import webbroker3.equity.message.WEB3EquityProductInformationRequest;
import webbroker3.equity.message.WEB3EquityProductInformationResponse;
import webbroker3.equity.service.delegate.WEB3EquityProductInformationService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

/**
 * （株式銘柄情報表示サービスImpl）。<BR>
 * <BR>
 * 株式銘柄情報表示サービス実装クラス
 * @@version 1.0
 */
public class WEB3EquityProductInformationServiceImpl
	extends WEB3EquityClientRequestService
	implements WEB3EquityProductInformationService 
{

	/**
	 * (ログ出力ユーティリティ。)
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3EquityProductInformationServiceImpl.class);
	/**
	 * @@roseuid XXXXXXXXXXX
	 */
	public WEB3EquityProductInformationServiceImpl()
	{

	}

	/**
	 * 株式銘柄情報表示サービス処理を実施する。<BR>
	 * <BR>
	 * シーケンス図「（株式銘柄情報表示サービス）execute」参照。<BR>
	 * @@param l_request - リクエストデータ
	 * @@return webbroker3.common.message.WEB3GenResponse
	 * @@throws webbroker3.common.WEB3BaseException
	 * @@roseuid XXXXXXXXXXXX
	 */
	public WEB3GenResponse execute(WEB3GenRequest l_request)
		throws WEB3BaseException 
	{
		final String STR_METHOD_NAME =
			"WEB3EquityProductInformationServiceImpl.execute()";
		log.entering(STR_METHOD_NAME);
		            
		boolean executeFlg = true ; // 処理を終了させる場合falseをセットして、処理をスキップさせる

		EqtypeTradedProductRow l_row = new EqtypeTradedProductParams();
        BranchRow l_rowBranch = new BranchParams();
        WEB3EquityProductInformationRequest l_prodInfoRequest = null; 
        WEB3EquityProductInformationResponse l_prodInfoResponse =null;                   
               
		if (l_request instanceof WEB3EquityProductInformationRequest)
		{
			l_prodInfoRequest =  (WEB3EquityProductInformationRequest) l_request;
		}
		else
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"INPUT リクエスト NOT 株式銘柄情報表示リクエスト");
		}

		String l_strProductCode = l_prodInfoRequest.productCode;
		String l_strMarketCode = l_prodInfoRequest.marketCode;
		String l_strOrderCommodityCode = l_prodInfoRequest.orderCommodityCode;

		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule =
            (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
		WEB3EquityOrderManager l_orderMgr =
		    (WEB3EquityOrderManager) l_tradingModule.getOrderManager();	
		WEB3GentradeFinObjectManager l_gentradeFinObjectManager = 
		    (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        WEB3EquityProductManager l_equityProductManager =
            (WEB3EquityProductManager) l_tradingModule.getProductManager();

		try
        {
            //1.1.validate
            l_prodInfoRequest.validate();
        }
        //1.2.例外がスローされた場合は処理を終了する
        catch (WEB3BaseException b_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, b_ex);
            executeFlg = false;
        }
        
        //銘柄コード未指定時は全項目nullのレスポンスを返す
        if (l_strProductCode == null)
        {
            return l_prodInfoRequest.createResponse();
        }
        
        if (executeFlg)
        {
			if (l_strOrderCommodityCode == null)
			{
                boolean l_blStockBatch = false; 
                boolean l_blMiniBatch = false; 
                boolean l_blMarginBatch = false; 
                boolean l_blStockSysStop = false; 
                boolean l_blMiniSysStop = false; 
                boolean l_blMarginSysStop = false;
                boolean l_blStockEx = false; 
                boolean l_blMiniEx = false; 
                boolean l_blMarginEx = false;        
        		//1.3.分岐処理：リクエスト.注文受付商品==nullの場合
        		////1.3.1.reset注文受付商品(01：株式)
				WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);

                try
                {
                    ////1.3.2.validate注文受付可能
                    WEB3GentradeTradingTimeManagement.validateOrderAccept();
                }
                catch (WEB3BaseException b_ex)
                {
                    if (b_ex.getErrorInfo().equals(WEB3ErrorCatalog.BUSINESS_ERROR_00011))
                    {
                        l_blStockBatch = true;
                    }
                    else if (b_ex.getErrorInfo().equals(WEB3ErrorCatalog.BUSINESS_ERROR_00012))
                    {
                        l_blStockSysStop = true;
                    }
                    else
                    {
                        l_blStockEx = true;
                    }
                }
		
				////1.3.3.reset注文受付商品(02：株式ミニ投資)
				WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.MINI_STOCK);

                try
                {
                    ////1.3.4.validate注文受付可能
                    WEB3GentradeTradingTimeManagement.validateOrderAccept();
                }
                catch (WEB3BaseException b_ex)
                {
                    if (b_ex.getErrorInfo().equals(WEB3ErrorCatalog.BUSINESS_ERROR_00011))
                    {
                        l_blMiniBatch = true;
                    }
                    else if (b_ex.getErrorInfo().equals(WEB3ErrorCatalog.BUSINESS_ERROR_00012))
                    {
                        l_blMiniSysStop = true;
                    }
                    else
                    {
                        l_blMiniEx = true;
                    }
                }
		
				////1.3.5.reset注文受付商品(03：信用取引)
				WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);

                try
                {
                    ////1.3.6.validate注文受付可能
                    WEB3GentradeTradingTimeManagement.validateOrderAccept();
                }
                catch (WEB3BaseException b_ex)
                {
                    if (b_ex.getErrorInfo().equals(WEB3ErrorCatalog.BUSINESS_ERROR_00011))
                    {
                        l_blMarginBatch = true;
                    }
                    else if (b_ex.getErrorInfo().equals(WEB3ErrorCatalog.BUSINESS_ERROR_00012))
                    {
                        l_blMarginSysStop = true;
                    }
                    else
                    {
                        l_blMarginEx = true;
                    }
                }

                //1.4.全て緊急停止、またはいずれかバッチ処理中の場合処理を終了させる
                if ((l_blStockBatch) || (l_blMiniBatch) || (l_blMarginBatch))
                {
                    executeFlg = false;
                }
                else if ((l_blStockSysStop) && (l_blMiniSysStop) && (l_blMarginSysStop))
                {
                    executeFlg = false;
                }
                else if ((l_blStockEx) || (l_blMiniEx) || (l_blMarginEx))
                {
                    executeFlg = false;
                }               
            }
        }
        
		if (executeFlg)
		{
        	//1.5.分岐処理：リクエスト.注文受付商品!=nullの場合
        	if (l_strOrderCommodityCode != null)
        	{
				try
				{
					//1.5.1.validate注文受付可能
					WEB3GentradeTradingTimeManagement.validateOrderAccept();
				}
                //1.6.緊急停止中、またはバッチ処理中の場合処理を終了させる
				catch (WEB3BaseException b_ex)
				{
                    log.error(this.getClass().getName() + "." + STR_METHOD_NAME, b_ex);
					executeFlg = false; 
				}
        	}
		}
        
        WEB3GentradeSubAccount l_subAccount = null;
        Institution l_institution = null;
        String l_strInstitutionCode = null;
        WEB3GentradeBranch l_branch = null;
        List l_arryListedMarketCodes = new ArrayList();
        WEB3EquityProduct l_equityProduct = null;
        Market[] l_markets = null;
		if (executeFlg)
		{
			//1.7.get補助口座
			l_subAccount = this.getSubAccount();

			l_institution = l_subAccount.getInstitution();
			l_strInstitutionCode = l_institution.getInstitutionCode();
            try
            {
    			l_branch =
    				(WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
    			//1.9.getProduct
	       		l_equityProduct = 
                    (WEB3EquityProduct)l_equityProductManager.getProduct(l_institution,l_strProductCode);
                //get取引市場一覧()
                l_markets = l_equityProduct.getTradedMarkets();
            }
            catch (NotFoundException nf_ex)
            {
                executeFlg = false;
            }
        }

        WEB3GentradeMarket l_primMarket = null;
        if (executeFlg)
        {
			//1.10.Loop処理：株式銘柄.get取引市場一覧()の戻り値要素分、繰返す。
			int l_lngLength;
			if (l_markets == null)
			{
				l_lngLength = 0;
			}
			else
			{
				l_lngLength = l_markets.length;
			}

			int i = 0;
            Market l_market = null;
            boolean l_blisListing = true;
			for (int j = 0; j < l_lngLength; j++)
			{
                try
                {
				    //1.10.1.reset市場コード(市場コード:String)
				    WEB3GentradeTradingTimeManagement.resetMarketCode(l_markets[j].getMarketCode());

					//1.10.2.validate取引銘柄()
					WEB3EquityTradedProduct l_tradedProduct = 
						(WEB3EquityTradedProduct)l_orderMgr.validateTradedProduct(l_equityProduct, l_markets[j]);
					if (l_tradedProduct == null)
					{
                        if (l_markets[j].getMarketCode().equals(l_strMarketCode))
                        {
                            l_blisListing = false;                      
                        }
						continue;
					}
                }
				catch (WEB3BaseException l_ex)
				{	
                    if(l_markets[j].getMarketCode().equals(l_strMarketCode))
                    {
                        l_blisListing = false;
                    }
					continue;
				}
				//取引銘柄が取得でき、上場区分が非上場でなく、例外が発生しないときは、
				//有効な市場コード一覧としてセットする
                l_arryListedMarketCodes.add(l_markets[j].getMarketCode()) ;
			}
            
            //1.11.分岐処理：優先市場を取得する            				
            if (l_strMarketCode == null || (l_strMarketCode != null && !l_blisListing))
            {
                //1.11.1.get優先市場()
                l_primMarket = (WEB3GentradeMarket)l_equityProduct.getPrimaryMarket();

                if (l_primMarket == null)
                {
                    //1.12.優先市場が取得できない場合は銘柄名のみセットして処理を終了させる
                    executeFlg = false;
                }
            }
		}
		
        WEB3EquityTradedProduct l_eqtradedProduct = null;
		if (executeFlg)
		{
            try
            {
			    //1.13.reset市場コード
                //取得したリクエストの市場コードで取引銘柄を検索。
                if (l_strMarketCode != null && l_primMarket == null)
                {
                    WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        
                    //1.14.get取引銘柄()
                    l_eqtradedProduct = 
                        (WEB3EquityTradedProduct) l_equityProductManager.getTradedProduct(
                                    l_institution,
                                    l_strProductCode,
                                    l_strMarketCode);
                }
                //取得した優先市場の市場コードで取引銘柄を検索。
                else
                {
                    WEB3GentradeTradingTimeManagement.resetMarketCode(l_primMarket.getMarketCode());

                    //1.14.get取引銘柄()
                    l_eqtradedProduct = 
                        (WEB3EquityTradedProduct) l_equityProductManager.getTradedProduct(
                                    l_institution,
                                    l_strProductCode,
                                    l_primMarket.getMarketCode());
                }                   
            }
            catch (NotFoundException nf_ex) //1.15.取引銘柄が取得できない場合は処理を終了させる  
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, nf_ex);
                executeFlg = false;
            }
		}
		
        String l_strstopHighPrice = null;
        String l_strstopLowPrice = null;  
        String[] l_tradeRegInfos = null;
        boolean l_blmarketMakeFlg = false;
        boolean l_isAdditionalCollateralRegulateBuyContract = false;
        boolean l_isAdditionalCollateralRegulateSellContract = false;
		if (executeFlg)
		{
            try
            {
                l_isAdditionalCollateralRegulateBuyContract =
                    l_equityProductManager.isAdditionalCollateralRegulateProduct(
                        l_subAccount,
                        l_eqtradedProduct,
                        Boolean.TRUE);
                
                l_isAdditionalCollateralRegulateSellContract =
                    l_equityProductManager.isAdditionalCollateralRegulateProduct(
                        l_subAccount,
                        l_eqtradedProduct,
                        Boolean.FALSE);

                //リクエスト.市場コード ≠ null の場合
                boolean l_blnIsPTSMarket = false;
                if (l_strMarketCode != null)
                {
                    //拡張金融オブジェクトマネージャ
                    WEB3GentradeFinObjectManager l_finObjectManager =
                        (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                    //get市場(証券会社コード : , 市場コード : )
                    //証券会社：　@証券会社.getInstitutionCode()の戻り値
                    //市場コード：　@リクエスト.市場コード
                    WEB3GentradeMarket l_market = null;
                    try
                    {
                        l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                            l_strInstitutionCode,
                            l_strMarketCode);
                    }
                    catch(NotFoundException l_ex)
                    {
                        log.error("テーブルに該当するデータがありません。", l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }

                    //isPTS市場( )
                    l_blnIsPTSMarket = l_market.isPTSMarket();
                }

                ////取引銘柄Rowオブジェクトの取得
                l_row = (EqtypeTradedProductRow)l_eqtradedProduct.getDataSourceObject();
                l_rowBranch = (BranchRow)l_branch.getDataSourceObject();
                String l_strHandlingMM = String.valueOf(l_rowBranch.getHandlingMarketMake());

                //株式銘柄Rowの取得
                EqtypeProductRow l_prodRow =
                    (EqtypeProductRow)l_eqtradedProduct.getProduct().getDataSourceObject();
                Timestamp l_tsDevidendRightDate = l_prodRow.getYearlyBooksClosingDate();

                //リクエスト.市場コード == null、または　@PTS市場でない場合（isPTS市場() == false））
                boolean l_blnIsDevRightDate = false;
                if (l_strMarketCode == null || !l_blnIsPTSMarket)
                {
                    //get発注日()
                    Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                    Timestamp l_tsOrderBizDate = new Timestamp(l_datOrderBizDate.getTime());                

                    //is権利落ち日()
                    l_blnIsDevRightDate =
                        l_orderMgr.isDevidendRightDate(l_tsOrderBizDate,l_tsDevidendRightDate);
                }

                WEB3EquityPTSOrderManager l_equityPTSOrderManager =
                    (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
                //PTS市場の場合（isPTS市場() == true））
                if (l_blnIsPTSMarket)
                {
                    //get発注日( )
                    Date l_datPTSOrderBizDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
                    Timestamp l_tsPTSOrderBizDate = new Timestamp(l_datPTSOrderBizDate.getTime());         

                    // is権利落ち日（PTS）(Timestamp, Timestamp)
                    //発注日：　@PTS取引時間管理.get発注日()
                    //権利確定日：　@株式銘柄.決算日（取引銘柄.getProduct()で取得できる株式銘柄オブジェクトより）
                    l_blnIsDevRightDate =
                        l_equityPTSOrderManager.isPTSDevidendRightDate(
                            l_tsPTSOrderBizDate, l_tsDevidendRightDate);
                }

			    //1.18.分岐処理：値幅チェック対象外銘柄の場合
                boolean l_isRightDate = false;

                //発注日（（入力）取引銘柄.有効日）
                Date l_datOrderBizDate = WEB3DateUtility.getDate(
                    l_row.getValidUntilBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);

                //（*6）以下の条件に満たしているのであれば、翌日注文時間帯の場合と判断する。
                // ・　@発注日(（入力）取引銘柄.有効日)＝受付日時の翌営業日である
                //  ・　@受付日時は営業日である。
                Timestamp l_orderTime = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
                Date l_datGentradeBizDate =
                    new WEB3GentradeBizDate(l_orderTime).roll(1);
                String l_strBizDateType = 
                    WEB3GentradeTradingTimeManagement.getBizDateType(l_orderTime);

                if (!(l_blnIsDevRightDate
                    && WEB3DateUtility.compare(WEB3DateUtility.toDay(l_datOrderBizDate),
                        WEB3DateUtility.toDay(l_datGentradeBizDate)) == 0
                    && !WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType)
                    && !l_equityProductManager.isNextDayBasePriceMail(l_row.getInstitutionCode())))
                {
                    l_isRightDate = true;
                }
                boolean l_isJasdaq;
                if (l_strMarketCode != null)
                {
                    l_isJasdaq = WEB3MarketCodeDef.JASDAQ.equals(l_strMarketCode);
                }
                else
                {
                    l_isJasdaq = WEB3MarketCodeDef.JASDAQ.equals(l_primMarket.getMarketCode());
                }
                
                String l_strOpenOtcDiv = null;
                if (l_row.getOpenOtcDiv() != null)
                {
                    l_strOpenOtcDiv = l_row.getOpenOtcDiv();
                }
                else
                {
                    l_strOpenOtcDiv = WEB3OpenOtcDivDef.DEFAULT;
                }                
                
                if (l_isJasdaq && l_strOpenOtcDiv.equals(WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT))
                {
                    if (l_strHandlingMM.equals(WEB3DealtDef.CAN_DEALT))
                    {
                        l_blmarketMakeFlg = true;
                    }
                    else
                    {
                        executeFlg = false;
                    }
                }
                
                if (executeFlg)
                {
                    if (l_row.getPriceRangeType() != null
                         && !(l_row.getPriceRangeType().equals(WEB3PriceRangeTypeDef.NO_CHECK)) 
                         && l_isRightDate)
                    {
                        //PTS市場でない場合(isPTS市場() == false))
                        if (!l_blnIsPTSMarket)
                        {
                            ////1.18.1.get値幅上限値()
                            l_strstopHighPrice = WEB3StringTypeUtility.formatNumber(
                                l_orderMgr.getStopHighPrice(l_eqtradedProduct));

                            ////1.18.2.get値幅下限値()
                            l_strstopLowPrice = WEB3StringTypeUtility.formatNumber(
                                l_orderMgr.getStopLowPrice(l_eqtradedProduct));
                        }
                    }
                    //（分岐処理：値幅チェック対象銘柄の場合）
                    if (l_row.getPriceRangeType() != null
                        && !(l_row.getPriceRangeType().equals(WEB3PriceRangeTypeDef.NO_CHECK))
                        && !(l_blnIsDevRightDate))
                    {
                        //PTS市場の場合
                        if (l_blnIsPTSMarket)
                        {
                            //get値幅上限値（PTS）(取引銘柄)
                            l_strstopHighPrice = WEB3StringTypeUtility.formatNumber(
                                l_equityPTSOrderManager.getPTSStopHighPrice(l_eqtradedProduct));
                            
                            //get値幅下限値（PTS）(取引銘柄)
                            l_strstopLowPrice = WEB3StringTypeUtility.formatNumber(
                                l_equityPTSOrderManager.getPTSStopLowPrice(l_eqtradedProduct));
                        }
                    }
                    //1.19.get取引規制情報               
                    l_tradeRegInfos = this.getTradeRegulationInfo(l_eqtradedProduct);        
                }
            }
            catch(WEB3BaseException b_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, b_ex);
                executeFlg = false;
            }
		}

        try
        {  		
    		//1.20.createResponse
    		 l_prodInfoResponse = (WEB3EquityProductInformationResponse) l_prodInfoRequest.createResponse();
          
    		if (executeFlg) //正常終了(executeFlg==true)時のレスポンスセット
    		{
    			////銘柄名
                EqtypeProductRow l_productRow = (EqtypeProductRow) l_equityProduct.getDataSourceObject();
    			l_prodInfoResponse.productName = l_productRow.getStandardName();

    			////市場コード（表示用）
    			if (l_strMarketCode != null && l_primMarket == null)
    			{
    				l_prodInfoResponse.marketCodePriority = l_strMarketCode;
    			}
    			else
    			{
    				l_prodInfoResponse.marketCodePriority = l_primMarket.getMarketCode();
    			}

    			////市場コード一覧
    			l_prodInfoResponse.marketList = (String[])l_arryListedMarketCodes.toArray(new String[0]);

    			////マーケットメイク銘柄フラグ(Boolean型)
    			l_prodInfoResponse.marketMakeFlag = Boolean.valueOf(l_blmarketMakeFlg);

    			////売買単位
                l_prodInfoResponse.dealingUnit =
                    WEB3StringTypeUtility.formatNumber(l_row.getLotSize());  			

    			////値幅上限値
    			l_prodInfoResponse.upperPriceRange = l_strstopHighPrice;
    			
    			////値幅下限値
    			l_prodInfoResponse.lowerPriceRange = l_strstopLowPrice;
    			
    			////信用属性
                if (l_row.getMarginSysProductType() != null)
                {
                    if (l_row.getMarginSysProductType().equals(
                        WEB3MarginSysProductTypeDef.MARGIN_SYS_PRODUCT_LOAN))
                    {
                        l_prodInfoResponse.marginAttribute =
                            WEB3EquityMarginAttributeDef.MARGIN_ATTRIBUTE_PRODUCT_LOAN;
                    }
                    else if (l_row.getMarginSysProductType().equals(
                        WEB3MarginSysProductTypeDef.MARGIN_SYS_PRODUCT_NO_LOAN))
                    {
                        l_prodInfoResponse.marginAttribute =
                            WEB3EquityMarginAttributeDef.MARGIN_ATTRIBUTE_PRODUCT_NOLOAN;
                    }
                    else
                    {
                        l_prodInfoResponse.marginAttribute =
                            WEB3EquityMarginAttributeDef.MARGIN_ATTRIBUTE_PRODUCT_OTHER;
                    }
                }
                else
                {
                    l_prodInfoResponse.marginAttribute =
                        WEB3EquityMarginAttributeDef.MARGIN_ATTRIBUTE_PRODUCT_OTHER;
                }
    			
    			////取引規制
    			l_prodInfoResponse.tradingRegulation = l_tradeRegInfos;

    			////特別値幅制限銘柄フラグ(Boolean型)
                if (l_row.getPriceRangeType() != null)
                {
                    if (l_row.getPriceRangeType().equals(WEB3PriceRangeTypeDef.NO_CHECK))
                    {
                        l_prodInfoResponse.specialPriceRangeFlag = Boolean.valueOf(true);
                    }
                    else
                    {
                        l_prodInfoResponse.specialPriceRangeFlag = Boolean.valueOf(false);
                    }
                }
                else
                {
                    l_prodInfoResponse.specialPriceRangeFlag = Boolean.valueOf(false);
                }

    			////代金即日徴収銘柄フラグ(Boolean型)
                if (l_row.getTodayDepFundReg() != null)
                {
                    if (l_row.getTodayDepFundReg().equals(BooleanEnum.TRUE))
                    {
                        l_prodInfoResponse.sameDayCollectionFlag = Boolean.valueOf(true);
                    }
                    else
                    {
                        l_prodInfoResponse.sameDayCollectionFlag = Boolean.valueOf(false);
                    }
                }
                else
                {
                    l_prodInfoResponse.sameDayCollectionFlag = Boolean.valueOf(false);
                }

    			////代用掛目
    			l_prodInfoResponse.marginRatio = WEB3StringTypeUtility.formatNumber(l_equityProduct.getMarginRatio());
                
                if (l_isAdditionalCollateralRegulateBuyContract ||
                    l_isAdditionalCollateralRegulateSellContract)
                {
                    l_prodInfoResponse.additionalCollateralRegulateFlag = Boolean.TRUE;
                    if (l_isAdditionalCollateralRegulateBuyContract)
                    {
                        l_prodInfoResponse.buyMarginDepositRate =
                            WEB3StringTypeUtility.formatNumber(l_row.getLongMarginDepositRate());
                        l_prodInfoResponse.buyCashMarginDepositRate =
                            WEB3StringTypeUtility.formatNumber(l_row.getLongCashMarginDepositRate());
                    }
                    if (l_isAdditionalCollateralRegulateSellContract)
                    {
                        l_prodInfoResponse.sellMarginDepositRate =
                            WEB3StringTypeUtility.formatNumber(l_row.getShortMarginDepositRate());
                        l_prodInfoResponse.sellCashMarginDepositRate =
                            WEB3StringTypeUtility.formatNumber(l_row.getShortCashMarginDepositRate());
                    }
                }
                else
                {
                    l_prodInfoResponse.additionalCollateralRegulateFlag = Boolean.FALSE;
                }
    		}
    		else  //処理失敗(executeFlg==false)時のレスポンスセット
    		{
    			//全ての項目にnullをセットする
                //ただし、銘柄名が取れている場合は銘柄名のみセット。
                if (l_equityProduct != null)
                {
                    EqtypeProductRow l_productRow = (EqtypeProductRow) l_equityProduct.getDataSourceObject();
                    l_prodInfoResponse.productName = l_productRow.getStandardName();
                }
                else
                {
                    l_prodInfoResponse.productName = null; 
                }
                
    			l_prodInfoResponse.marketCodePriority = null;
    			l_prodInfoResponse.marketList = null;
    			l_prodInfoResponse.marketMakeFlag = null;
    			l_prodInfoResponse.dealingUnit = null;
    			l_prodInfoResponse.upperPriceRange = null;
    			l_prodInfoResponse.lowerPriceRange = null;
    			l_prodInfoResponse.marginAttribute = null;
    			l_prodInfoResponse.tradingRegulation = null;
    			l_prodInfoResponse.specialPriceRangeFlag = null;
    			l_prodInfoResponse.sameDayCollectionFlag = null;
    			l_prodInfoResponse.marginRatio = null;
                l_prodInfoResponse.additionalCollateralRegulateFlag = null;
                l_prodInfoResponse.buyMarginDepositRate = null;
                l_prodInfoResponse.buyCashMarginDepositRate = null;
                l_prodInfoResponse.sellMarginDepositRate = null;
                l_prodInfoResponse.sellCashMarginDepositRate = null;
    		}
        }
        catch (Exception ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, ex);
            //全ての項目にnullをセットする
            //ただし、銘柄名が取れている場合は銘柄名のみセット。
            if (l_equityProduct != null)
            {
                EqtypeProductRow l_productRow = (EqtypeProductRow)l_equityProduct.getDataSourceObject();
                l_prodInfoResponse.productName = l_productRow.getStandardName();
            }
            else
            {
                l_prodInfoResponse.productName = null; 
            }
            l_prodInfoResponse.marketCodePriority = null;
            l_prodInfoResponse.marketList = null;
            l_prodInfoResponse.marketMakeFlag = null;
            l_prodInfoResponse.dealingUnit = null;
            l_prodInfoResponse.upperPriceRange = null;
            l_prodInfoResponse.lowerPriceRange = null;
            l_prodInfoResponse.marginAttribute = null;
            l_prodInfoResponse.tradingRegulation = null;
            l_prodInfoResponse.specialPriceRangeFlag = null;
            l_prodInfoResponse.sameDayCollectionFlag = null;
            l_prodInfoResponse.marginRatio = null;
            l_prodInfoResponse.additionalCollateralRegulateFlag = null;
            l_prodInfoResponse.buyMarginDepositRate = null;
            l_prodInfoResponse.buyCashMarginDepositRate = null;
            l_prodInfoResponse.sellMarginDepositRate = null;
            l_prodInfoResponse.sellCashMarginDepositRate = null;
        }

		log.exiting(STR_METHOD_NAME);
		return l_prodInfoResponse;
    }
    

	/**
	 * (get取引規制情報)<BR>
	 * 取引銘柄より取引規制情報を取得する。<BR>
	 * （レスポンス.取引規制[]配列へ設定する。）<BR>
	 * １）　@引数の取引銘柄オブジェクトより<BR>
	 * 　@株式銘柄オブジェクトを取得する。<BR>
	 * 　@拡張プロダクトマネージャ.getProduct(証券会社オブジェクト，<BR>
	 * 　@引数の取引銘柄.銘柄IDに該当する銘柄コード）<BR>
	 * <BR>
	 * ２）　@現引現渡の取引規制情報取得用の株式取引銘柄オブジェクトを取得する。<BR>
	 * <BR>
	 * ２?１）　@取引カレンダコンテキスト.受付時間区分 を再セットする。 <BR>
	 * <BR>
	 * 　@受付時間区分：　@"現引現渡"をセット。 <BR>
	 * <BR>
	 * ２?２）　@現引現渡の発注日ベースの株式取引銘柄オブジェクトを取得する。 <BR>
	 * <BR>
	 * 　@拡張プロダクトマネージャ.getTradedProduct(引数の取引銘柄.取引銘柄ID)をコールする。 <BR>
	 * <BR>
	 * ３）　@ミニ株の取引規制情報取得用の株式取引銘柄オブジェクトを取得する。 <BR>
	 * <BR>
	 * ３?１）　@取引カレンダコンテキスト.受付時間区分、市場コード を再セットする。 <BR>
	 * <BR>
	 * 　@受付時間区分：　@"株式ミニ投資"をセット。 <BR>
	 * 　@市場コード：　@"DEFAULT"をセット。 <BR>
	 * <BR>
	 * ３?２）　@ミニ株の発注日ベースの、ミニ株取扱市場における株式取引銘柄オブジェクトを取得する。 <BR>
	 * <BR>
	 * 　@拡張プロダクトマネージャ.getミニ株取扱取引銘柄(１）で取得した株式銘柄オブジェクト)を <BR>
	 * 　@コールする。 <BR>
	 * 　@※戻り値がnull（==ミニ株取扱なしの銘柄）または例外がthrowされた場合、<BR>
	 * 　@※ミニ株の取引規制はチェックしない。 <BR>
	 * <BR>
	 * ４）　@１)で取得した株式銘柄オブジェクト、引数の取引銘柄オブジェクト、<BR>
	 * 　@２）及び３）で取得した株式取引銘柄オブジェクトより取引規制情報を取得する。<BR>
	 * ※株式銘柄、取引銘柄の参照項目については以下記述。<BR>
	 * （表の項目説明参照。） <BR>
	 * ※取引規制情報取得方法@については<BR>
	 * 『機@能定義書（2.1.銘柄情報画面表示処理）<BR>
	 * 8)取引規制情報を取得する。』の表を参照。<BR>
     * <BR>
     * ※メソッド内で例外が発生した場合、nullを返す。<BR>
	 * <BR>
	 * @@param l_equityTradedProduct- 取引銘柄
     * @@return java.lang.String[]
	 * @@roseuid XXXXXXXXXXX
	 */
	protected String[] getTradeRegulationInfo(WEB3EquityTradedProduct l_equityTradedProduct)
	{
		final String STR_METHOD_NAME =
			"WEB3EquityProductInformationServiceImpl.getTradeRegulationInfo()";
		log.entering(STR_METHOD_NAME);

		List l_arryTrdRegInfolist = new ArrayList(); //取引規制情報格納用リスト
        String[] l_strTrdRegInfolist = new String[0];

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3EquityTradingModule l_tradingModule
                       = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
               
            ////拡張プロダクトマネージャを取得 
            WEB3EquityProductManager l_EqProdMgr
                = (WEB3EquityProductManager) l_tradingModule.getProductManager();

            //銘柄コードを取得
            String l_strProdCd = l_equityTradedProduct.getProductCode(); 
            
            //補助口座から証券会社オブジェクトを取得
            SubAccount l_subAccount = this.getSubAccount();
            Institution l_instInstitution = l_subAccount.getInstitution();
            
			//株式銘柄Rowオブジェクト取得
			WEB3EquityProduct l_equityProduct = 
				(WEB3EquityProduct) l_EqProdMgr.getProduct(l_instInstitution,l_strProdCd);
			EqtypeProductRow l_rowEqProd =
				(EqtypeProductRow) l_equityTradedProduct.getProduct().getDataSourceObject();
			
            //取引銘柄Rowオブジェクト取得
			EqtypeTradedProductRow l_rowEqTrdProd =
			    (EqtypeTradedProductRow) l_equityTradedProduct.getDataSourceObject();
            
            //現引現渡の取引規制情報取得用の株式取引銘柄オブジェクト取得
			WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
			EqTypeTradedProduct l_swapEqTrdProd =
			    (EqTypeTradedProduct)l_EqProdMgr.getTradedProduct(l_rowEqTrdProd.getTradedProductId());
			EqtypeTradedProductRow l_rowSwapEqTrdProd =
			    (EqtypeTradedProductRow) l_swapEqTrdProd.getDataSourceObject();

			//ミニ株の取引規制情報取得用の株式取引銘柄オブジェクト取得
			WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.MINI_STOCK);
			WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);
			WEB3EquityTradedProduct l_miniEqTrdProd =
				l_EqProdMgr.getMiniStockTradedProduct(l_equityProduct);
            EqtypeTradedProductRow l_rowMiniEqTrdProd = null;
            if (l_miniEqTrdProd != null)
            {
			    l_rowMiniEqTrdProd =
				    (EqtypeTradedProductRow) l_miniEqTrdProd.getDataSourceObject();
            }
            
            //①@売買中止
            String l_strStockTrdStop = String.valueOf(l_rowEqProd.getTradeStop());//現物株式売買停止
            String l_strMarSysTrdStop = String.valueOf(l_rowEqProd.getMarginSysTradeStop());//制度信用取引売買停止
            String l_strMarGenTrdStop = String.valueOf(l_rowEqProd.getMarginGenTradeStop());//一般信用取引売買停止                                           
            //②現物取引停止
            String l_strBuyCashStop = String.valueOf(l_rowEqTrdProd.getBuyCashStop());//買現物停止
            String l_strSellCashStop = String.valueOf(l_rowEqTrdProd.getSellCashStop());//売現物停止          
            //③ミニ株取引停止
			String l_strBuyMiniStockStop = WEB3TradeStopDef.ACTIVE;
			String l_strSellMiniStockStop = WEB3TradeStopDef.ACTIVE;
            if (l_miniEqTrdProd != null)
            {
				l_strBuyMiniStockStop = String.valueOf(l_rowMiniEqTrdProd.getBuyMiniStockStop());//買ミニ株停止
				l_strSellMiniStockStop = String.valueOf(l_rowMiniEqTrdProd.getSellMiniStockStop());//売ミニ株停止
            }
            //④信用新規建停止
            String l_strLongMarginSysStop = String.valueOf(l_rowEqTrdProd.getLongMarginSysStop());//買制度信用停止
            String l_strShortMarginSysStop = String.valueOf(l_rowEqTrdProd.getShortMarginSysStop());//売制度信用停止
            String l_strLongMarginGenStop = String.valueOf(l_rowEqTrdProd.getLongMarginGenStop());//買一般信用停止
            String l_strShortMarginGenStop = String.valueOf(l_rowEqTrdProd.getShortMarginGenStop());//売一般信用停止
            //⑤信用返済停止
            String l_strLongCloseMarginSysStop = String.valueOf(l_rowEqTrdProd.getLongCloseMarginSysStop());//買建返済制度信用停止
            String l_strShortCloseMarginSysStop = String.valueOf(l_rowEqTrdProd.getShortCloseMarginSysStop());//売建返済制度信用停止
            String l_strLongCloseMarginGenStop = String.valueOf(l_rowEqTrdProd.getLongCloseMarginGenStop());//買建返済一般信用停止
            String l_strShortCloseMarginGenStop = String.valueOf(l_rowEqTrdProd.getShortCloseMarginGenStop());//売建返済一般信用停止
            //⑥信用現引現渡停止
            String l_strLongSwapMarginSysStop = String.valueOf(l_rowSwapEqTrdProd.getLongSwapMarginSysStop());//現引制度信用停止
            String l_strShortSwapMarginSysStop = String.valueOf(l_rowSwapEqTrdProd.getShortSwapMarginSysStop());//現渡制度信用停止
            String l_strLongSwapMarginGenStop = String.valueOf(l_rowSwapEqTrdProd.getLongSwapMarginGenStop());//現引一般信用停止
            String l_strShortSwapMarginGenStop = String.valueOf(l_rowSwapEqTrdProd.getShortSwapMarginGenStop());//現渡一般信用停止
            //⑦成行現物取引停止
            String l_strBuySpotMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getBuySpotMarketOrdDesStop());//買現物成行指定停止
            String l_strSellSpotMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getSellSpotMarketOrdDesStop());//売現物成行指定停止
            //⑧成行信用新規建停止
            String l_strLongMsMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getLongMsMarketOrdDesStop());//買制度信用成行指定停止
            String l_strShortMsMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getShortMsMarketOrdDesStop());//売制度信用成行指定停止
            String l_strLongMgMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getLongMgMarketOrdDesStop());//買一般信用成行指定停止
            String l_strShortMgMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getShortMgMarketOrdDesStop());//売一般信用成行指定停止
            //⑨成行信用返済停止
            String l_strLongCmsMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getLongCmsMarketOrdDesStop());//買建返済制度信用成行指定停止
            String l_strShortCmsMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getShortCmsMarketOrdDesStop());//売建返済制度信用成行指定停止
            String l_strLongCmgMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getLongCmgMarketOrdDesStop());//買建返済一般信用成行指定停止
            String l_strShortCmgMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getShortCmgMarketOrdDesStop());//売建返済一般信用成行指定停止
        
            //各サービスの取引規制状態①@～⑨を２次元配列に格納
            String[][] l_strTrdReglists = 
            {
                {l_strStockTrdStop,l_strMarSysTrdStop,l_strMarGenTrdStop},
                {l_strBuyCashStop,l_strSellCashStop},
                {l_strBuyMiniStockStop,l_strSellMiniStockStop},
                {l_strLongMarginSysStop,l_strShortMarginSysStop,l_strLongMarginGenStop,l_strShortMarginGenStop},
                {l_strLongCloseMarginSysStop,l_strShortCloseMarginSysStop,l_strLongCloseMarginGenStop,l_strShortCloseMarginGenStop},
                {l_strLongSwapMarginSysStop,l_strShortSwapMarginSysStop,l_strLongSwapMarginGenStop,l_strShortSwapMarginGenStop},
                {l_strBuySpotMarketOrdDesStop,l_strSellSpotMarketOrdDesStop},
                {l_strLongMsMarketOrdDesStop,l_strShortMsMarketOrdDesStop,l_strLongMgMarketOrdDesStop,l_strShortMgMarketOrdDesStop},
                {l_strLongCmsMarketOrdDesStop,l_strShortCmsMarketOrdDesStop,l_strLongCmgMarketOrdDesStop,l_strShortCmgMarketOrdDesStop}                      
            };
            
            int i = 0;
            //◆売買中止(i==0)
            if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
               && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
               && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2])))
            {
                //売買停止中
                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_TRDSTOP);   
            }
            else
            {
                if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0])))
                {
                    //現物取引停止中
                    l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP);
                }
            
                if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                         && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2])))
                {
                    //信用取引停止中
                    l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MARTRDSTOP);
                }
            
                if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                         && WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                {
                    //制度信用取引停止中
                    l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP);
                }
            
                if(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1])
                         && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2])))
                {
                    //一般信用取引停止中
                    l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP);
                } 
            }
            
            //◆現物取引停止(i==1)・ミニ株取引停止(i==2)
            if(!l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_TRDSTOP))
            {
                for(i = 1 ; i<3 ; i++)
                {
                    if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0])) 
                        && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1])))
                    {
                        //取引停止中
                        if(i == 1 &&
                           !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP))
                        {
                            //現物
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP);
                        }
                        else if(i == 2 &&
                                 !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP))
                        {
                            //ミニ株
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MINISTOCKSTOP);
                        }
                    }
                    else if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                             && WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                    {
                        //買付停止中
                        if(i == 1 &&
                           !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP))
                        {
                            //現物
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_BUYCASHSTOP);
                        }
                        else if(i == 2 &&
                                 !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP) &&
                                 !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_BUYCASHSTOP))
                        {
                            //ミニ株
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_BUYMINISTOCKSTOP);
                        }                    
                    }
                    else if(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0])
                             && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1])))
                    {
                        //売付停止中
                        if(i == 1 &&
                           !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP))
                        {
                            //現物
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SELLCASHSTOP);
                        }
                        else if(i == 2 &&
                                 !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP) &&
                                 !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SELLCASHSTOP))
                        {
                            //ミニ株
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SELLMINISTOCKSTOP);
                        }                    
                    }
                }                
            }

       
            //◆信用新規建停止(i==3)・信用返済停止(i==4)・信用現引現渡停止(i==5)
            if(!l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_TRDSTOP) &&
               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARTRDSTOP))
            {
                for(i = 3 ; i<6 ; i++)
                {
                    if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                        && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                        && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                        && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                    {
                        //信用○○停止中
                        if(i == 3)
                        {
                            //新規建
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP);
                        }
                        else if(i == 4)
                        {
                            //返済
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP);
                        }
                        else if(i == 5)
                        {
                            //現引現渡
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SWAPMARGINSTOP);                  
                        }
                    }
                    else
                    {
                        if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                                  && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2])))
                        {
                            //信用○○買停止中
                            if(i == 3)
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMARGINSTOP);
                            }
                            else if(i == 4)
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCLOSEMARGINSTOP);
                            }
                            else if(i == 5)
                            {
                                //現引現渡
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGSWAPMARGINSTOP);                   
                            }                    
                        }
                        if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                                  && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                        {
                            //信用○○売停止中
                            if(i == 3)
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMARGINSTOP);
                            }
                            else if(i == 4)
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCLOSEMARGINSTOP);
                            }
                            else if(i == 5)
                            {
                                //現引現渡
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTSWAPMARGINSTOP);               
                            }                    
                        }
                        if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                                  && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1])))
                        {
                            //制度信用○○停止中
                            if(i == 3 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSYSSTOP);
                            }
                            else if(i == 4 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSYSSTOP);
                            }
                            else if(i == 5 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //現引現渡
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SWAPMARGINSYSSTOP);                  
                            }                    
                        }
                        if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                                  && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                        {
                            //一般信用○○停止中
                            if(i == 3 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINGENSTOP);
                            }
                            else if(i == 4 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINGENSTOP);
                            }
                            else if(i == 5 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //現引現渡
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SWAPMARGINGENSTOP);                    
                            }                    
                        }
                        if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                                  && WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1])
                                  && WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                        {
                            //制度信用○○買停止中
                            if(i == 3 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMARGINSYSSTOP);
                            }
                            else if(i == 4 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCLOSEMARGINSYSSTOP);
                            }
                            else if(i == 5 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //現引現渡
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGSWAPMARGINSYSSTOP);                    
                            }                    
                        }
                        if(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0])
                                  && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                                  && WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][3]))
                        {
                            //制度信用○○売停止中
                            if(i == 3 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMARGINSYSSTOP);
                            }
                            else if(i == 4 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCLOSEMARGINSYSSTOP);
                            }
                            else if(i == 5 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //現引現渡
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTSWAPMARGINSYSSTOP);                   
                            }
                        }
                        if(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0])
                                  && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                                  && WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][3]))
                        {
                            //一般信用○○買停止中
                            if(i == 3 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMARGINGENSTOP);
                            }
                            else if(i == 4 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCLOSEMARGINGENSTOP);
                            }
                            else if(i == 5 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //現引現渡
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGSWAPMARGINGENSTOP);                    
                            }                    
                        }
                        if(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1])
                                  && WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2])
                                  && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                        {
                            //一般信用○○売停止中
                            if(i == 3 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMARGINGENSTOP);
                            }
                            else if(i == 4 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCLOSEMARGINGENSTOP);
                            }
                            else if(i == 5 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //現引現渡
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTSWAPMARGINGENSTOP);                   
                            }                    
                        }
                    }                                       
                }                          
            }

            i = 6;
            //◆成行現物取引停止(i==6)
            if(!l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_TRDSTOP) &&
               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP))
            {
                if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0])) 
                    && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1])))
                {
                    //成行現物取引停止中
                    l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SPOTMARKETORDDESSTOP);
                }
                else if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                         && WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1])
                         && !(l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_BUYCASHSTOP)))
                {
                    //成行現物買付停止中
                    l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_BUYSPOTMARKETORDDESSTOP);
                }
                else if(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0])
                         && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                         && !(l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SELLCASHSTOP)))
                {
                    //成行現物売付停止中
                    l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SELLSPOTMARKETORDDESSTOP);
                }                 
            }
       
        
            //◆成行信用新規建停止(i==7)・成行信用返済停止(i==8)
            if(!l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_TRDSTOP) &&
               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARTRDSTOP))
            {
                for(i = 7 ; i<9 ; i++)
                {
                    if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                        && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                        && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                        && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                    {
                        //成行信用○○停止中
                        if(i == 7 &&
                           !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP))
                        {
                            //新規建
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MMARKETORDDESSTOP);
                        }
                        else if(i == 8 &&
                                 !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP))
                        {
                            //返済
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_CMMARKETORDDESSTOP);
                        }
                    }
                    else
                    {
                        if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                                  && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][2])))
                        {
                            //成行信用○○買停止中
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMARGINSTOP))
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCLOSEMARGINSTOP))
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCMMARKETORDDESSTOP);
                            }
                        }
                        if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                                  && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                        {
                            //成行信用○○売停止中
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMARGINSTOP))
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCLOSEMARGINSTOP))
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCMMARKETORDDESSTOP);
                            }
                        }
                        if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                                  && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1])))
                        {
                            //成行制度信用○○停止中
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSYSSTOP))
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MSMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSYSSTOP))
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_CMSMARKETORDDESSTOP);
                            }
                        }
                        if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                                  && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                        {
                            //成行一般信用○○停止中
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINGENSTOP))
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MGMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINGENSTOP))
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_CMGMARKETORDDESSTOP);
                            }
                        }
                        if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                                  && WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1])
                                  && WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                        {
                            //成行制度信用○○買停止中
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSYSSTOP))
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMSMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSYSSTOP))
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCMSMARKETORDDESSTOP);
                            }
                        }
                        if(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0])
                                  && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                                  && WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][3]))
                        {
                            //成行制度信用○○売停止中
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSYSSTOP))
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMSMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSYSSTOP))
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCMSMARKETORDDESSTOP);
                            }
                        }
                        if(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0])
                                  && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                                  && WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][3]))
                        {
                            //成行一般信用○○買停止中
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINGENSTOP))
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMGMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINGENSTOP))
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCMGMARKETORDDESSTOP);
                            }
                        }
                        if(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1])
                                  && WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][2])
                                  && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                        {
                            //成行一般信用○○売停止中
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINGENSTOP))
                            {
                                //新規建
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMGMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINGENSTOP))
                            {
                                //返済
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCMGMARKETORDDESSTOP);
                            }
                        }
                    }
                }
            }
        }
        catch(WEB3BaseException b_ex)
        {
            log.error(this.getClass().getName() , b_ex);
            log.error("取引規制情報の取得に失敗しました");
			log.exiting(STR_METHOD_NAME);
            return null;                      
        }
        catch(NotFoundException nf_ex)
        {
            log.error(this.getClass().getName() , nf_ex);
            log.error("取引規制情報の取得に失敗しました");
			log.exiting(STR_METHOD_NAME);
            return null; 
        }        
		log.exiting(STR_METHOD_NAME);           
        return (String[]) l_arryTrdRegInfolist.toArray(l_strTrdRegInfolist);
    }
}
@
