head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityAssetInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 売付一覧サービスImpl(WEB3EquityAssetInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 任林林 (中訊) 新規作成
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;

import webbroker3.equity.WEB3EquityAsset;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityAssetUnit;
import webbroker3.equity.message.WEB3EquityProductCodeNameUnit;
import webbroker3.equity.message.WEB3EquitySellListRequest;
import webbroker3.equity.message.WEB3EquitySellListResponse;
import webbroker3.equity.message.WEB3EquitySortKey;
import webbroker3.equity.service.delegate.WEB3EquityAssetInquiryService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

/**
 * （売付一覧サービスImpl）。<BR>
 * <BR>
 * 売付一覧サービス実装クラス
 * @@version 1.0  
 */
public class WEB3EquityAssetInquiryServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityAssetInquiryService 
{

    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityAssetInquiryServiceImpl.class);
    /**
     * @@roseuid 409F383D0346
     */
    public WEB3EquityAssetInquiryServiceImpl()
    {

    }

    /**
     * 売付一覧サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図「（売付一覧ハンドラ）リクエスト」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406030A90384
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "WEB3EquityAssetInquiryServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);

        WEB3EquitySellListRequest l_inquiryRequest = null;
        String l_strProductCode = null;
        String l_strMarketCode = null;
		String l_strInstitutionCode = null; // 証券会社コード
		String l_strmarketCode = null;
        
        
        if (l_request instanceof WEB3EquitySellListRequest)
        {
            l_inquiryRequest = (WEB3EquitySellListRequest) l_request;
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT リクエスト NOT 売付一覧リクエスト");
        }

        l_strProductCode = l_inquiryRequest.productCode;
        l_strMarketCode = l_inquiryRequest.marketCode;

        //1.1.validate
        l_inquiryRequest.validate();
        
        //1.2.validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
		//1.3.get補助口座
		SubAccount l_subAccount = this.getSubAccount();

        //1.4.getEqTypeOrderValidator
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //拡張プロダクトマネージャを取得 
        WEB3EquityProductManager l_equityProductManager
                   = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        
        //拡張株式注文マネージャを生成
        WEB3EquityOrderManager l_orderMgr =
                    (WEB3EquityOrderManager) l_tradingModule.getOrderManager();

        //1.5.validate取引可能顧客
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_validationResult = 
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult != OrderValidationResult.VALIDATION_OK_RESULT)
        {
            log.error("__Error[validate取引可能顧客をチェック]__");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                "execute");
        }

		//1.6.createResponse
		WEB3EquitySellListResponse l_inquiryResponse =
			(WEB3EquitySellListResponse) l_inquiryRequest.createResponse();

        //1.7.get取引店
        WEB3GentradeBranch l_branch =
            (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();

        //1.8.get市場閉局警告市場
        String[] l_TradeOpenMarket = null;
        l_TradeOpenMarket =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_branch,
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);

        l_inquiryResponse.messageSuspension = l_TradeOpenMarket;

		try
		{        

        	//1.9.validate顧客銘柄別取引停止コール
			l_orderMgr.validateAccountProductOrderStop(
							l_subAccount,
							0,
							OrderTypeEnum.EQUITY_SELL
							);

        	//1.10.リクエスト.銘柄コードが指定されている場合
            //証券会社コードを取得
			Institution l_institution = l_subAccount.getInstitution(); //証券会社
            l_strInstitutionCode = l_institution.getInstitutionCode();
			EqTypeProduct l_eqtypeProduct = null;
            if (l_strProductCode != null && l_strProductCode.length() != 0)
            {
                //1.10.1 validate銘柄コード
				l_eqtypeProduct = l_orderMgr.validateProductCode(
                    					l_strProductCode,
                    					l_strInstitutionCode);
                    				
				//1.10.2 validateインサイダー
				l_orderMgr.validateInsider(
					l_subAccount,
					l_eqtypeProduct);
							
				//1.10.3 validate顧客銘柄別取引停止コール 
				l_orderMgr.validateAccountProductOrderStop(
					l_subAccount,
					l_eqtypeProduct.getProductId(),
					OrderTypeEnum.EQUITY_SELL
					);
								
            }
            
        	//1.11.リクエスト.市場コードが指定されている場合
			l_strmarketCode = l_inquiryRequest.marketCode;
       		Market l_market = null;
        	if (l_strmarketCode != null && l_strmarketCode.length() !=0)
        	{
        		//1.11.1.validate市場コード
        		l_market = l_orderMgr.validateMarket(
								l_strmarketCode,
								l_strInstitutionCode);
					
        	}

			//1.12.リクエスト.銘柄コード及びリクエスト.市場コードが指定されている場合
            log.debug("=====> productCode = " + l_strProductCode);
            log.debug("=====> marketCode = " + l_strmarketCode);
            
            TradedProduct l_tradedProduct = null;
            if (l_strProductCode != null && l_strmarketCode != null
                && l_strProductCode.length() != 0 && l_strmarketCode.length() != 0)
            {
                log.debug(
                    "==> productCode, marketCode not null, and length not 0!");
                
                //1.12.1.validate取引銘柄
				l_tradedProduct = l_orderMgr.validateTradedProduct(
										l_subAccount,
										l_eqtypeProduct,
										l_market,
										true					
										);
                
                //1.12.2.validate取扱可能市場    
                l_orderMgr.validateHandlingMarket(
						l_branch,
						l_tradedProduct);
                    
            }
		}
		catch (WEB3BaseException l_ex)		
		{
			log.error(STR_METHOD_NAME, l_ex);
			log.exiting(STR_METHOD_NAME);
			l_inquiryResponse.errorMessage = l_ex.getErrorInfo().getErrorMessage();
			return l_inquiryResponse;
		}

			//get保有資産一覧()の処理
			//1.create検索条件文字列
			  String l_strConditionList = createSearchConditionList(l_inquiryRequest);

			//2.create検索条件データコンテナ
			  String[] l_searchConditionDataContainer =
				  createSearchConditionDataContainer(l_inquiryRequest);

			//3.createソート条件(現物株式ソートキー[ ])
			  WEB3EquitySortKey[] l_sortKeys = l_inquiryRequest.sortKeys;
			  String l_strSortCondition = createSortCondition(l_sortKeys);		
		
			//4.get保有資産一覧()		
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
            List l_assetList =
                l_positionManager.getSellableAssets(
                    l_subAccount,
					ProductTypeEnum.EQUITY,
					null,
					null,
					l_strSortCondition);

            WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

            /**
             * 1.13.(*1)銘柄プルダウン・市場プルダウン作成
             * **/
            l_inquiryResponse =
                pulldown(
		            l_assetList,
					l_subAccount,
                    l_strInstitutionCode,
                    l_branch,
                    l_gentradeFinObjectManager,
                    l_equityProductManager,
                    l_inquiryResponse);

			if(l_inquiryResponse.productCodeNames == null || l_inquiryResponse.marketList == null)
			{
				l_inquiryResponse.equityAssetUnits = null;
				l_inquiryResponse.totalPages   = "0";
				l_inquiryResponse.totalRecords = "0";
				l_inquiryResponse.pageIndex    = "0";
				l_inquiryResponse.messageSuspension = l_TradeOpenMarket;
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01037,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}

            /**
             * 1.14.(*2)売付可能一覧作成
             **/
            l_inquiryResponse =  sellList(
                    l_inquiryRequest,
                    l_positionManager,
                    l_subAccount,
                    l_branch,
                    l_strInstitutionCode,
                    l_gentradeFinObjectManager,
                    l_equityProductManager,
                    l_inquiryResponse);
                    
            log.debug(
                "== > l_inquiryResponse.totalRecords "
                    + l_inquiryResponse.totalRecords);

            log.exiting("WEB3EquityAssetInquiryServiceImpl.execute");
            return l_inquiryResponse;
        }
    

    /**
     * 15.(*1)銘柄プルダウン・市場プルダウン作成する。<BR>
     * <BR>
     * シーケンス図「（売付一覧）銘柄・市場プルダウン作成」参照。<BR>
     * @@param l_assetList - 保有資産
     * @@param l_subAccount - 補助口座
     * @@param l_strInstitutionCode - 証券会社
     * @@param l_branch - 取引店
     * @@param l_gentradeFinObjectManager - 拡張金融オブジェクトマネージャ
     * @@param l_productManager - 拡張プロダクトマネージャ
     * @@param l_inquiryResponse - 拡張プロダクトマネージャ
     * @@return WEB3EquitySellListResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406030A90384
     */
	private WEB3EquitySellListResponse pulldown(
		List l_assetList,
		SubAccount l_subAccount,
		String l_strInstitutionCode,
		WEB3GentradeBranch l_branch,
		WEB3GentradeFinObjectManager l_gentradeFinObjectManager,
		WEB3EquityProductManager l_productManager,
		WEB3EquitySellListResponse l_inquiryResponse)
        throws WEB3BaseException 
    {

        final String STR_METHOD_NAME =
                    "private pulldown(List,String,WEB3GentradeBranch,WEB3GentradeFinObjectManager,WEB3EquityProductManager)";

        long l_lngAssetProductIdTemp;

        WEB3EquityProduct l_stockProductTemp = null;
		WEB3EquityTradedProduct l_tradedProductTemp = null;
        WEB3GentradeMarket l_market = null;
        String l_strProductCodeTemp = null;
		EqTypeProduct l_eqtypeProduct = null;
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        WEB3EquityOrderManager l_orderMgr =
                    (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
					  
		int l_intAssetList = l_assetList.size();

				
        //1.2.get取引中取扱可能市場(部店, ProductTypeEnum)
        String[] l_strHandlingPossibleMarket =
            WEB3GentradeBranchMarketDealtCond.getTradingHandlingPossibleMarket(
                l_branch,
                ProductTypeEnum.EQUITY
                );

        long l_lngLength = l_strHandlingPossibleMarket.length;

        WEB3EquityProductCodeNameUnit[] l_productCodeNameUnits =
            new WEB3EquityProductCodeNameUnit[l_intAssetList];
        for (int i = 0; i < l_intAssetList; i++)
        {
            l_productCodeNameUnits[i] = new WEB3EquityProductCodeNameUnit();    
        }
        int productIndex = 0;

		//1.3.取得した保有資産オブジェクト.銘柄ID数分Loop
        for (int i = 0; i < l_intAssetList; i++)
        {
            WEB3EquityAsset l_AssetTmp = (WEB3EquityAsset) l_assetList.get(i);
            l_lngAssetProductIdTemp = l_AssetTmp.getProduct().getProductId();
            l_strProductCodeTemp =
                ((WEB3EquityProduct) l_AssetTmp.getProduct()).getProductCode();
            
             try 
             {             
				//1.3.1.validate銘柄コード
				l_eqtypeProduct = l_orderMgr.validateProductCode(
										l_strProductCodeTemp,
										l_strInstitutionCode);
                    				
				//1.3.2 validateインサイダー
				l_orderMgr.validateInsider(
					l_subAccount,
					l_eqtypeProduct);          
 
             }
             catch (WEB3BusinessLayerException e) 
             {
                continue;                 
             }
               
               try
               {
               		l_stockProductTemp =
                    new WEB3EquityProduct(
                        l_productManager.getProduct(l_lngAssetProductIdTemp).getProductId());
                
//                	boolean isValidate = false;

				//1.3.3.取引可能市場の市場数分Loop
                for (int j = 0; j < l_lngLength; j++)
                {
                    //1.3.3.1.reset市場コード(String)
                    WEB3GentradeTradingTimeManagement.resetMarketCode(
                        l_strHandlingPossibleMarket[j]);

                    try 
                    {
                        //get取引銘柄
                        l_market =
                            new WEB3GentradeMarket(
                                l_gentradeFinObjectManager.getMarket(
                                    l_strInstitutionCode,
                                    l_strHandlingPossibleMarket[j]).getMarketId());
                        l_tradedProductTemp = (WEB3EquityTradedProduct) l_productManager.getTradedProduct(
                                l_stockProductTemp,
                                l_market);
                    } 
                    catch (NotFoundException nfe) 
                    {
                        continue;
                    }
                    
					if (l_tradedProductTemp != null) {
                        
                        try
                        {
							//1.3.3.2 validate顧客銘柄別取引停止コール 
							l_orderMgr.validateAccountProductOrderStop(
								l_subAccount,
								l_eqtypeProduct.getProductId(),
								OrderTypeEnum.EQUITY_SELL
								);   
							
                        	//1.3.3.3.validate取引銘柄
							l_orderMgr.validateTradedProduct(
								l_subAccount,
								l_eqtypeProduct,
								l_market,
								true);
                        	
							//1.3.3.4.validateJASDAQ銘柄取扱可能(部店)
                            l_tradedProductTemp.validateJASDAQProductHandling(l_branch);
                            
                        }
                        catch (WEB3BaseException l_ex)
                        {
                            continue;
                        }                       
                    }
                } 
                
                    String l_strTempCode = l_tradedProductTemp.getProductCode();
                    boolean l_tempFlag = false;
                    if (productIndex > 0)
                    {
                    
                        for (int m = 0; m < productIndex; m++)
                        {
                            if (l_strTempCode == l_productCodeNameUnits[m].productCode)
                            {
                                l_tempFlag = true;
                                break;
                            }
                        }
                    }
                    if (l_tempFlag == false)
                    {
						WEB3EquityProduct l_product = (WEB3EquityProduct)l_tradedProductTemp.getProduct();
                    	EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();
                    	                    
                        l_productCodeNameUnits[productIndex].productName =  l_productRow.getStandardName();
                        l_productCodeNameUnits[productIndex].productCode =  l_tradedProductTemp.getProductCode();
                        productIndex = productIndex + 1;
                    }
                
                       
                //1.4.getProduct(銘柄ID : long)
                WEB3EquityProduct l_productTemp =
                    (WEB3EquityProduct) l_productManager.getProduct(l_lngAssetProductIdTemp);                
				EqtypeProductRow l_productTempRow = (EqtypeProductRow)l_productTemp.getDataSourceObject();

                l_productCodeNameUnits[i] = new WEB3EquityProductCodeNameUnit();
				if (l_productTemp != null)
                {
                    l_productCodeNameUnits[i].productCode =
                        l_productTemp.getProductCode();
                    l_productCodeNameUnits[i].productName =
					    l_productTempRow.getStandardName();
                }
                
            }
            catch (NotFoundException nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            }
            catch (DataException de)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
        }
        //レスポンス.銘柄一覧 に　@null を　@セット
        
        if (productIndex == 0) {
            l_inquiryResponse.productCodeNames = null;
        }
        else
        {
            WEB3EquityProductCodeNameUnit[] l_productCodeNameUnitsEnd =
                        new WEB3EquityProductCodeNameUnit[productIndex];
            for (int n = 0; n < productIndex; n++)
            {
                l_productCodeNameUnitsEnd[n] = new WEB3EquityProductCodeNameUnit();
                l_productCodeNameUnitsEnd[n] = l_productCodeNameUnits[n];
            }
            l_inquiryResponse.productCodeNames = l_productCodeNameUnitsEnd;
        }

        //レスポンス.市場コード一覧 に　@null を　@セット
        if (l_lngLength == 0)
        {
            l_inquiryResponse.marketList = null;
        }
        else
        {
            l_inquiryResponse.marketList = l_strHandlingPossibleMarket;
        }

        return l_inquiryResponse;
    }

    /**
     * 16.(*2)売付可能一覧作成する。<BR>
     * <BR>
     * シーケンス図「（売付一覧）売付可能一覧作成」参照。<BR>
     * @@param l_inquiryRequest - リクエストデータ
     * @@param l_positionManager - 株式ポジションマネージャ 
     * @@param l_subAccount - 補助口座
     * @@param l_branch - 証券会社の部署（部店）
     * @@param l_strInstitutionCode - 証券会社
     * @@param l_gentradeFinObjectManagerTemp - 拡張金融オブジェクトマネージャ
     * @@param l_productManager - 拡張プロダクトマネージャ
     * @@param l_inquiryResponse - 拡張プロダクトマネージャ
     * @@return WEB3EquitySellListResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406030A90384
     */
    private WEB3EquitySellListResponse sellList(
        WEB3EquitySellListRequest l_inquiryRequest,
        WEB3EquityPositionManager l_positionManager,
        SubAccount l_subAccount,
        WEB3GentradeBranch l_branch,
        String l_strInstitutionCode,
        WEB3GentradeFinObjectManager l_gentradeFinObjectManagerTemp,
        WEB3EquityProductManager l_productManager,
        WEB3EquitySellListResponse l_inquiryResponse)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
                    "private sellList()";
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        WEB3EquityOrderManager l_orderMgr =
                    (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            
        //1.1.create検索条件文字列
        String l_strConditionList = createSearchConditionList(l_inquiryRequest);

        //1.2.create検索条件データコンテナ
        String[] l_searchConditionDataContainer =
            createSearchConditionDataContainer(l_inquiryRequest);

        //1.3.createソート条件(現物株式ソートキー[ ])
        WEB3EquitySortKey[] l_sortKeys = l_inquiryRequest.sortKeys;
        String l_strSortCondition = createSortCondition(l_sortKeys);

        //1.4.get売付可能保有資産一覧
        List l_list = null;
        int l_intList = 0;
        l_list =
            l_positionManager.getSellableAssets(
                l_subAccount,
                ProductTypeEnum.EQUITY,
                l_strConditionList,
                l_searchConditionDataContainer,
                l_strSortCondition);
        l_intList = l_list.size();

        //1.5.get取引中取扱可能市場(部店, ProductTypeEnum)
        String[] l_strHandlingPossibleMarket =
            WEB3GentradeBranchMarketDealtCond.getTradingHandlingPossibleMarket(
                l_branch,
                ProductTypeEnum.EQUITY
                );

        int l_intLengthTemp = l_strHandlingPossibleMarket.length;

        String l_strMarketCodeTempTemp = null;
        WEB3EquityTradedProduct l_tradedProductTempT = null;

        long l_lngAssetProductIdTempT = 0;
        String l_strAssetProductCodeTemp = null;

		WEB3GentradeMarket  l_marketTemp = null;
		EqTypeProduct l_eqtypeProduct = null;

        WEB3EquityAssetUnit[] l_assetUnits = new WEB3EquityAssetUnit[l_intList];
        for (int i = 0; i < l_intList; i++)
        {
            l_assetUnits[i] = new WEB3EquityAssetUnit();
        }

        try
        {
            int assetUnitIndex = 0;			
            //1.6.取得した保有資産オブジェクト.銘柄数分Loop
            for (int i = 0; i < l_intList; i++)
            {
                WEB3EquityAsset l_AssetTemp = (WEB3EquityAsset)  l_list.get(i);
                
                double l_lngCount = l_AssetTemp.getQuantity();
                l_lngAssetProductIdTempT = l_AssetTemp.getProduct().getProductId();
                long l_lngAssetIDTemp = l_AssetTemp.getAssetId();
                l_strAssetProductCodeTemp =
                    ((WEB3EquityProduct) l_productManager.getProduct(l_lngAssetProductIdTempT)).getProductCode();

                boolean isValidate = false;
                
                List l_marketCodeListTemp = new ArrayList();
                try
                {
					//1.6.1.validate銘柄コード
					l_eqtypeProduct = l_orderMgr.validateProductCode(
											l_strAssetProductCodeTemp,
											l_strInstitutionCode);
                    				
					//1.6.2 validateインサイダー
					l_orderMgr.validateInsider(
						l_subAccount,
						l_eqtypeProduct);        
 
                }
                catch (WEB3BusinessLayerException e)
                {
                    continue;
                }
              
                //1.6.3.リクエスト.市場コード指定ありの場合
                if (l_inquiryRequest.marketCode != null)
                {
				 try
				 {
                  	//1.6.3.1.reset市場コード(String)
                  	WEB3GentradeTradingTimeManagement.resetMarketCode(
                    	  l_inquiryRequest.marketCode);
                    	  
					//1.6.3.2 validate顧客銘柄別取引停止コール 
					l_orderMgr.validateAccountProductOrderStop(
						l_subAccount,
						l_eqtypeProduct.getProductId(),
						OrderTypeEnum.EQUITY_SELL
						);

				  	//1.6.3.3.validate取引銘柄
				  	l_marketTemp = (WEB3GentradeMarket) l_orderMgr.validateMarket(
				  		l_inquiryRequest.marketCode,
				  		l_strInstitutionCode);
				  		
				  	l_orderMgr.validateTradedProduct(
					  l_subAccount,
					  l_eqtypeProduct,
					  l_marketTemp,
					  true);  
                 
                  	l_tradedProductTempT =
                          (WEB3EquityTradedProduct) l_productManager.getTradedProduct(
								l_eqtypeProduct,
                       		    l_marketTemp);

                  	//1.6.3.4.validateJASDAQ銘柄取扱可能(部店)
                  	l_tradedProductTempT.validateJASDAQProductHandling(l_branch);
                  
                  }
				  catch (NotFoundException nfe) 
				  {
					log.error("not this traded product id" + nfe.getMessage());
				  	  continue;
				  }
                  catch(WEB3BaseException l_ex)
                  {
                      continue;
                  }
                  
				  l_marketCodeListTemp.add(l_inquiryRequest.marketCode); 
				  isValidate = true;
                }
				//1.6.4.リクエスト.市場コード指定なしの場合
                else
                {
                	//1.6.4.1.取扱可能市場数分Loop
                    for (int j = 0; j < l_intLengthTemp; j++)
                    {
                     	try
                    	{
                    	
                        	//1.6.4.1.1.reset市場コード(String)
                        	WEB3GentradeTradingTimeManagement.resetMarketCode(
                            	l_strHandlingPossibleMarket[j]);
                            
							//1.6.4.1.2 validate顧客銘柄別取引停止コール 
							l_orderMgr.validateAccountProductOrderStop(
								l_subAccount,
								l_eqtypeProduct.getProductId(),
								OrderTypeEnum.EQUITY_SELL
								);

							//1.6.4.1.3.validate取引銘柄
							l_strMarketCodeTempTemp = l_strHandlingPossibleMarket[j];
							l_marketTemp = (WEB3GentradeMarket) l_orderMgr.validateMarket(
								l_strMarketCodeTempTemp,
								l_strInstitutionCode);
				  		
							l_orderMgr.validateTradedProduct(
								l_subAccount,
								l_eqtypeProduct,
								l_marketTemp,
								true); 

                        	l_tradedProductTempT =
                                (WEB3EquityTradedProduct) l_productManager.getTradedProduct(
									l_eqtypeProduct,
                                    l_marketTemp);
                        

							//1.6.4.1.4.validateJASDAQ銘柄取扱可能(部店)
                            l_tradedProductTempT.validateJASDAQProductHandling(
                                l_branch);                                
						} 
						catch (NotFoundException nfe)
						{
							log.error("not this traded product id" + nfe.getMessage());
							continue;
                        }
                        catch (WEB3BaseException l_ex)
                        {
                            continue;
                        }
                        
                        l_marketCodeListTemp.add(l_strMarketCodeTempTemp);
                        isValidate = true;
                    }
                }

                if (isValidate)
                {
					WEB3EquityProduct l_prouctTemp = (WEB3EquityProduct)l_tradedProductTempT.getProduct();
					EqtypeProductRow l_productTempRow = (EqtypeProductRow)l_prouctTemp.getDataSourceObject();
					
                    l_assetUnits[assetUnitIndex].productCode = l_tradedProductTempT.getProductCode();                  
                    l_assetUnits[assetUnitIndex].productName = l_productTempRow.getStandardName();
                    l_assetUnits[assetUnitIndex].marketList = l_strHandlingPossibleMarket;

					//取得資産
					WEB3EquityAsset l_asset = (WEB3EquityAsset)l_positionManager.getAsset(l_lngAssetIDTemp);

					//1.7.getProduct(銘柄ID : long)
					EqTypeProduct l_product = (EqTypeProduct) l_asset.getProduct();
					EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();

                    //1.8.getLockedQuantity
                    double l_lockedQuantity = l_asset.getLockedQuantity();                   

                    //レスポンス.明細.ID
                    l_assetUnits[assetUnitIndex].id = String.valueOf(l_lngAssetIDTemp);

                    //レスポンス.明細.銘柄コード
                    l_assetUnits[assetUnitIndex].productCode = l_strAssetProductCodeTemp;
                    
					//レスポンス.明細.銘柄名
					l_assetUnits[assetUnitIndex].productName = l_productRow.getStandardName();
                    
                    //レスポンス.明細.口座区分
                    WEB3EquityAsset l_web3EquityAsset =  (WEB3EquityAsset)l_list.get(i);
                    AssetParams l_assetParams = new AssetParams((AssetRow)l_web3EquityAsset.getDataSourceObject());
                    if (TaxTypeEnum.NORMAL.equals(l_assetParams.getTaxType()))
                    {
                        l_assetUnits[assetUnitIndex].taxType = "0";
                    }
                    else if (TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_assetParams.getTaxType())
                          || TaxTypeEnum.SPECIAL.equals(l_assetParams.getTaxType()))
                    {
                          l_assetUnits[assetUnitIndex].taxType = "1";
                    }
                    
                    //レスポンス.明細.売付可能株数
                    double l_dblPossQuantity = l_lngCount - l_lockedQuantity;
                    
                    int l_intPossQuantity = (int)l_dblPossQuantity;
                    int l_intLockedQuantity = (int)l_lockedQuantity;
                    l_assetUnits[assetUnitIndex].sellPossQuantity = "" + l_intPossQuantity;
                    l_assetUnits[assetUnitIndex].orderedQuantity = "" + l_intLockedQuantity;

                    //レスポンス.明細.市場コード一覧
                    l_assetUnits[assetUnitIndex].marketList = new String[l_marketCodeListTemp.size()];
                    l_marketCodeListTemp.toArray(l_assetUnits[assetUnitIndex].marketList);
                    
                    //レスポンス.明細.売付可能フラグ
                    if (l_lngCount > l_lockedQuantity)
                    {
                        l_assetUnits[assetUnitIndex].sellPossFlag = true;
                    }
                    else
                    {
                        l_assetUnits[assetUnitIndex].sellPossFlag = false;
                    }
                    assetUnitIndex = assetUnitIndex + 1;
                }                
            }

            int l_intPageIndex = Integer.parseInt(l_inquiryRequest.pageIndex);
            int l_intPageSize = Integer.parseInt(l_inquiryRequest.pageSize);

            if (l_intPageIndex > (int)Math.ceil((double)assetUnitIndex / l_intPageSize))
            {
                l_intPageIndex = (int)Math.ceil((double)assetUnitIndex / l_intPageSize);
                l_inquiryResponse.pageIndex = "" + l_intPageIndex;
            }
            else
            {
                l_inquiryResponse.pageIndex = l_inquiryRequest.pageIndex;
            }
           
            int l_intAssertStartIndex = l_intPageSize*(l_intPageIndex -1);
            int l_intAssertEndIndex = l_intPageSize*l_intPageIndex;
            
            if (l_intAssertEndIndex > assetUnitIndex)
            {
                l_intAssertEndIndex = assetUnitIndex;
            }
            
            int l_intAssertSize = l_intAssertEndIndex - l_intAssertStartIndex;
            WEB3EquityAssetUnit[] l_assetUnitsEnd = new WEB3EquityAssetUnit[l_intAssertSize];
            for (int k = 0; k < l_intAssertSize; k++)
            {
                l_assetUnitsEnd[k] = new WEB3EquityAssetUnit();
                l_assetUnitsEnd[k] = l_assetUnits[l_intAssertStartIndex + k];
            }            

            l_inquiryResponse.equityAssetUnits = l_assetUnitsEnd;
            
           int l_lngResult =
               assetUnitIndex / Integer.parseInt(l_inquiryRequest.pageSize);
           int l_lngTemp = assetUnitIndex % Integer.parseInt(l_inquiryRequest.pageSize);
           
           if (l_lngResult == 0)
           {
               l_inquiryResponse.totalPages = "1";
           }
           else
           {
               if (l_lngTemp == 0)
               {
                   l_inquiryResponse.totalPages = String.valueOf(l_lngResult);
               }
               else
               {
                   l_inquiryResponse.totalPages = String.valueOf(l_lngResult + 1);
               }
           }
           //レスポンス.総ページ数
           if (assetUnitIndex == 0)
           {
               l_inquiryResponse.totalPages = "0";
           }
            
         //レスポンス.総レコード数                   
         l_inquiryResponse.totalRecords = String.valueOf(assetUnitIndex);
         
         //レスポンス.表示ページ番号
         if (l_inquiryResponse.totalPages == null)
         {
             l_inquiryResponse.equityAssetUnits = null;
         } 
        }
        catch (NotFoundException nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }
        return l_inquiryResponse;
    }

    /**
     * (create検索条件文字列)<BR>
     * 検索条件（where以下指定の文字列）を作成し返す。<BR>
     * <BR>
     * １）　@引数のリクエストデータ.銘柄コード≠null（銘柄コード指定）の場合、<BR>
     * 銘柄ID指定を追加する。<BR>
     * <BR>
     * ２）　@作成した検索条件文字列を返す。<BR>
     * 　@　@　@※引数のリクエストデータ.銘柄コード が指定されていない場合、<BR>
     * nullを返す。<BR>
     * @@param l_requestData - リクエストデータ<BR>
     * 売付一覧リクエストオブジェクト<BR>
     * @@return java.lang.String
     * @@roseuid 4069416D0118
     */
    protected String createSearchConditionList(WEB3EquitySellListRequest l_requestData)
    {
        log.entering(
            "WEB3EquityAssetInquiryServiceImpl.createSearchConditionList");
        if (l_requestData.productCode != null)
        {
            String l_strConditionList = "product_Id = ?";
            log.exiting(
                "WEB3EquityAssetInquiryServiceImpl.createSearchConditionList");
            return l_strConditionList;
        }
        else
        {
            log.debug("リクエストデータ.銘柄コード が指定されていない");
            log.exiting(
                "WEB3EquityAssetInquiryServiceImpl.createSearchConditionList");
            return null;
        }
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件（where以下指定の文字列）のパラメータリストを作成し返す。<BR>
     * <BR>
     * １）　@引数のリクエストデータ.銘柄コード≠null（銘柄コード指定）の場合、<BR>
     * 銘柄IDをセットする。<BR>
     * <BR>
     * 　@　@　@拡張プロダクトマネージャ.getProduct(証券会社オブジェクト(*1), <BR>
     * 引数のリクエストデータ.銘柄コード).銘柄IDをセット<BR>
     * <BR>
     * ２）　@作成したパラメータリストを返す。<BR>
     * 　@　@　@※引数のリクエストデータ.銘柄コード 
     * が指定されていない場合、nullを返す。<BR>
     * <BR>
     * (*1)証券会社オブジェクトは、補助口座.getInstitution( )で取得し設定<BR>
     * @@param l_request - 売付一覧リクエストオブジェクト
     * @@return String[]
     * @@roseuid 4069416D0127
     */
    protected String[] createSearchConditionDataContainer(WEB3EquitySellListRequest l_request)
    {
        log.entering(
            "WEB3EquityAssetInquiryServiceImpl.createSearchConditionDataContainer");
        if (l_request.productCode != null)
        {

            //拡張プロダクトマネージャ.getProduct(証券会社オブジェクト(*1), 
            //   * 引数のリクエストデータ.銘柄コード).銘柄IDをセット<BR>
            String l_strProductCode = l_request.productCode;
            Institution l_institution = null;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeProduct l_product = null;
            String l_strId = null;
            try
            {
                SubAccount l_subAccount =
                    this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

                l_institution = l_subAccount.getInstitution();

                TradingModule l_tradingMod =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager) l_tradingMod.getProductManager();

                l_product =
                    l_productManager.getProduct(
                        l_institution,
                        l_strProductCode);
                //作成したパラメータリストを返す
                long l_lngProductId = l_product.getProductId();

                l_strId = String.valueOf(l_lngProductId);
            }
            catch (NotFoundException e)
            {
                log.debug(
                    "createSearchConditionDataContainer__Error[銘柄オブジェクト取得]__");

            }
            catch (WEB3SystemLayerException e)
            {
                log.debug(
                    "createSearchConditionDataContainer__Error[銘柄オブジェクト取得]__");

            }

            String[] l_ConditionDataContainer = { l_strId };
            log.exiting(
                "WEB3EquityAssetInquiryServiceImpl.createSearchConditionDataContainer");
            return l_ConditionDataContainer;

        }
        else
        {
            log.debug("リクエストデータ.銘柄コーが指定されていない");
            log.exiting(
                "WEB3EquityAssetInquiryServiceImpl.createSearchConditionDataContainer");
            return null;
        }

    }

    /**
     * (createソート条件)<BR>
     * ソート条件文字列を作成し返す。<BR>
     * <BR>
     * １）　@引数のソートキー.キー項目の数分、対応するテーブルの列物理名<BR>
     * を昇順／降順指定を付加しセットする。<BR>
     * <BR>
     * 　@　@・キー項目とテーブルの列名称との対応は以下の通り。<BR>
     * 　@　@　@※キー項目文字列（シンボル名）は、メッセージ定義書を参照。<BR>
     * 　@　@　@※テーブルの列物理名は、テーブルレイアウトを参照。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@・（銘柄）コード　@　@　@　@：保有資産テーブル.銘柄ID<BR>
     * 　@　@　@　@　@　@　@　@・口座　@　@　@　@　@　@　@　@　@：保有資産テーブル.税区分<BR>
     * <BR>
     * 　@　@・昇順／降順指定は、ソートキー.昇順／降順 指定に従い設定する。<BR>
     * <BR>
     * ２）　@作成したソート条件文字列を返す。<BR>
     * 　@　@　@※引数のリクエストデータ.ソートキー 
     * が指定されていない場合、nullを返す。<BR>
     * @@param l_sortKeys - リクエストデータ.ソートキー
     * @@return java.lang.String
     * @@roseuid 40710E3100B4
     */
    protected String createSortCondition(WEB3EquitySortKey[] l_sortKeys) {

        log.entering("WEB3EquityAssetInquiryServiceImpl.createSortCondition()");
        if (l_sortKeys.length != 0)
        {
            String l_strSort = "";
            for (int i = 0; i < l_sortKeys.length; i++)
            {
                // 李志強 U00232の暫定対応 start
//                  l_strSort = l_strSort + l_sortKeys[i].keyItem;

                if("productCode".equals(l_sortKeys[i].keyItem))
                {
                    //（銘柄）コード ：注文単位テーブル．銘柄ID
                    l_strSort = l_strSort + "product_id";
                }
                else if("taxType".equals(l_sortKeys[i].keyItem))
                {
                    //口座 ：注文単位テーブル．税区分
                    l_strSort = l_strSort + "tax_type";
                }
                // 李志強 U00232の暫定対応 end
                
                l_strSort = l_strSort + " ";
                
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc)) 
                {
                    l_strSort = l_strSort + "Asc";
                } 
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc)) 
                {
                    l_strSort = l_strSort + "Desc";
                }
                
                if (i >= 0 && i < l_sortKeys.length - 1)
                {
                    l_strSort = l_strSort + ",";
                }
            }
            log.exiting(
                "WEB3EquityAssetInquiryServiceImpl.createSortCondition()");
            return l_strSort;
        }
        else
        {
            log.error("リクエストデータ.ソートキー  が指定されていない");
            log.exiting(
                "WEB3EquityAssetInquiryServiceImpl.createSortCondition()");
            return null;
        }
    }
}
@
