head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderExecutedInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文約定照会サービスImpl(WEB3OptionOrderExecutedInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 馬振田 (中訊) 新規作成
              001: 2006/07/27 柴雙紅 (中訊)　@ 仕様変更　@モデル471、495、497、518
Revesion History : 2007/06/15 肖志偉 (中訊)仕様変更 モデル685,686,687,688
Revesion History : 2007/06/21 張騰宇 (中訊)仕様変更 モデル741、746、747
Revesion History : 2007/10/16 張騰宇 (中訊)仕様変更 モデル786 787
Revesion History : 2008/08/18 劉剣 (中訊) IFO小数点対応
**/

package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Contract;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContract;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderAction;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoFinTransactionManagerImpl;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3IfoReferenceTypeDef;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.define.WEB3IfoTradeTypeDef;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3OptionsChangeCancelHistoryGroup;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractGroup;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractListRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractListResponse;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsResponse;
import webbroker3.ifo.message.WEB3OptionsExecuteGroup;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceRequest;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceResponse;
import webbroker3.ifo.message.WEB3OptionsExecuteUnit;
import webbroker3.ifo.message.WEB3OptionsOrderHistoryRequest;
import webbroker3.ifo.message.WEB3OptionsOrderHistoryResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecutedInquiryService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (OP注文約定照会サービスImpl)<BR>
 * 株価指数オプション注文約定照会サービス実装クラス<BR>
 * @@author  : 馬振田
 * @@version : 1.0
 */
public class WEB3OptionOrderExecutedInquiryServiceImpl extends WEB3OptionClientRequestService implements WEB3OptionOrderExecutedInquiryService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionOrderExecutedInquiryServiceImpl.class);

    /**
     * FinApp
     */
    private FinApp finApp = (FinApp)Services.getService(FinApp.class);

    /**
     * TradingModule
     */
    private TradingModule tradingModule =
        finApp.getTradingModule(ProductTypeEnum.IFO);

    /**
     * OrderManager
     */
    private WEB3OptionOrderManagerImpl opOrderManager =
        (WEB3OptionOrderManagerImpl)tradingModule.getOrderManager();

    /**
     * @@roseuid 40C0BAF302EE
     */
    public WEB3OptionOrderExecutedInquiryServiceImpl()
    {

    }

    /**
     * 株価指数オプション注文約定照会サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、get注文約定照会()、get注文約定詳細()、<BR>
     * get注文履歴照会()、get返済建玉一覧()のいずれかのメソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057FB7D0263
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3OptionsExecuteReferenceRequest)
        {
            //「株価指数オプション注文約定照会リクエスト」の場合
            l_response = getOrderExecutedInquiry(
                (WEB3OptionsExecuteReferenceRequest)l_request);
        }
        else if (l_request instanceof WEB3OptionsExecuteDetailsRequest)
        {
            //「株価指数オプション当日注文約定詳細リクエスト」の場合
            l_response = getOrderExecutedDetail(
                (WEB3OptionsExecuteDetailsRequest)l_request);
        }
        else if (l_request instanceof WEB3OptionsOrderHistoryRequest)
        {
            //「株価指数オプション注文履歴照会リクエスト」の場合
            l_response = getOrderActionInquiry(
                (WEB3OptionsOrderHistoryRequest)l_request);
        }
        else if (l_request instanceof WEB3OptionsCloseMarginContractListRequest)
        {
            //「株価指数オプション返済建玉一覧リクエスト」の場合
            l_response = getSettleContractList(
                (WEB3OptionsCloseMarginContractListRequest)l_request);
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get注文約定照会)<BR>
     * 株価指数オプション注文約定照会、株価指数オプション訂正取消一覧の処理を行う。
     * <BR>
     * <BR>
     * シーケンス図「（OP注文約定照会サービス）get注文約定照会」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション注文約定照会リクエストオブジェクト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsExecuteReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 406A8790001E
     */
    protected WEB3OptionsExecuteReferenceResponse getOrderExecutedInquiry(WEB3OptionsExecuteReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOrderExecutedInquiry(WEB3OptionsExecuteReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //get補助口座()
        SubAccount l_subAccount = getSubAccount();
        
        WEB3IfoProductImpl l_ifoProduct = null;
        WEB3IfoProductManagerImpl l_ProductManager = 
            (WEB3IfoProductManagerImpl)tradingModule.getProductManager();

        //リクエストデータに銘柄コードが設定されている場合
        if (l_request.opProductCode != null
            && l_request.opProductCode.length() != 0)
        {
            try
            {
                l_ifoProduct = l_ProductManager.getIfoProduct(
					l_subAccount.getInstitution(), 
                	l_request.opProductCode);
                	
                if (l_ifoProduct == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (NotFoundException l_nfex)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //原資産銘柄コードを取得
            String l_strUnderlyingProductCode = l_ifoProduct.getUnderlyingProductCode();
            //reset銘柄コード
            WEB3GentradeTradingTimeManagement.resetProductCode(l_strUnderlyingProductCode);

        }
        
        //リクエストデータに銘柄特定項目(取引市場、指数種別、限月、オプション商品区分、行使価格)が設定されている場合
		if (l_request.marketCode != null 
			&& l_request.targetProductCode != null 
			&& l_request.strikePrice != null 
			&& l_request.delivaryMonth != null 
			&& l_request.opProductType != null)           
		{         
			IfoDerivativeTypeEnum l_ifoDerivativeTypeEnum = null;     
			if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_request.opProductType))       
			{     
				l_ifoDerivativeTypeEnum = IfoDerivativeTypeEnum.CALL_OPTIONS; 
			}     
			else if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_request.opProductType))       
			{     
				l_ifoDerivativeTypeEnum = IfoDerivativeTypeEnum.PUT_OPTIONS;  
			}
            else
            {
                //オプション商品区分が存在しない値
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00270,
                    getClass().getName() + "." + STR_METHOD_NAME);     
            }
                
			double l_dblStrikePrice = 0;      
			if (l_request.strikePrice != null && !"".equals(l_request.strikePrice))       
			{     
				l_dblStrikePrice = Double.parseDouble(l_request.strikePrice); 
			}     
                
			try       
			{     
				l_ifoProduct = l_ProductManager.getIfoProduct(        
					l_subAccount.getInstitution(),    
					l_request.targetProductCode,  
					l_request.delivaryMonth,  
					l_ifoDerivativeTypeEnum,  
					l_dblStrikePrice, 
					WEB3DivisionTypeDef.DIVISION_DEFAULT, 
					l_request.marketCode);    
					
				if (l_ifoProduct == null)
				{
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00301,
						getClass().getName() + "." + STR_METHOD_NAME);
				}
			}     
			catch (NotFoundException l_nfex)      
			{     
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
					STR_METHOD_NAME);   
			}
			
			//原資産銘柄コードを取得
			String l_strUnderlyingProductCode = l_ifoProduct.getUnderlyingProductCode();
			//reset銘柄コード
			WEB3GentradeTradingTimeManagement.resetProductCode(l_strUnderlyingProductCode);
		}
        
		//is取引可能顧客(補助口座)
		//リクエストデータ.照会区分="訂正取消一覧"
		//かつ、is取引可能顧客がfalseの場合、例外をthrowする
		boolean l_blnIsTradedPossibleAccount =
			opOrderManager.isTradedPossibleCustomer(
				(WEB3GentradeSubAccount)l_subAccount);
		if (WEB3IfoReferenceTypeDef.CORRECTION_LIST.equals(l_request.referenceType) &&
			l_blnIsTradedPossibleAccount == false)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00275,
				getClass().getName() + "." + STR_METHOD_NAME);
		}
        
		//validate注文受付可能()
		//リクエストデータ.照会区分="注文約定照会"の場合のみ、
		//受付時間チェック、システム売買停止チェック
		if (WEB3IfoReferenceTypeDef.ORDER_PROMISE.equals(l_request.referenceType))
		{
			//受付時間チェック、システム売買停止チェック
			WEB3GentradeTradingTimeManagement.validateOrderAccept();
		}

        //get取引店()
        WEB3GentradeBranch l_branch = ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch();
        //取引時間管理.get市場閉局警告指数()
        String[] l_TradeCloseMarket =
            WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                l_branch,
                WEB3FuturesOptionDivDef.OPTION);

        //リクエストデータ.照会区分="訂正取消一覧"の場合のみ、処理を実施する
        if (WEB3IfoReferenceTypeDef.CORRECTION_LIST.equals(l_request.referenceType))
        {
            //reset受付時間区分(受付時間区分："12：株価指数先物OP（訂正取消）)
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.INDEX_FUTURE_OP_CHANGE_CANCEL);

            boolean l_blnChangeAcceptFail = false;
            boolean l_blnCancelAcceptFail = false;            
            //エラーフラグ
            boolean l_blnCancelErrBATCH = false;
            boolean l_blnCancelErrSCRAM = false;
            boolean l_blnCancelErrTRADINGTIME = false;

            //reset注文受付トランザクション(注文受付トランザクション : "05：訂正")
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CHANGE);

            //validate注文受付可能()
            //注文受付トランザクション="05：訂正"
            //受付時間区分="株価指数先物OP（訂正取消）"で
            //注文受付可能チェック（受付時間チェック、
            //システム売買停止チェック）を行う。
            try
            {   
                WEB3GentradeTradingTimeManagement.validateOrderAccept();                  
            }
            catch (WEB3BusinessLayerException l_ex)
            {
                log.debug("訂正：注文受付不可", l_ex);
                l_blnChangeAcceptFail= true;
            }

            //reset注文受付トランザクション(注文受付トランザクション : "06:取消")
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CANCEL);

            //validate注文受付可能()
            //注文受付トランザクション="06：取消"
            //受付時間区分="株価指数先物OP（訂正取消）"で
            //注文受付可能チェック（受付時間チェック、
            //システム売買停止チェック）を行う。
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BusinessLayerException l_ex)
            {
				log.debug("取消：注文受付不可", l_ex);
				l_blnCancelAcceptFail = true;
				if (l_ex.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00011)
				{
					l_blnCancelErrBATCH = true;
				}
				else if (l_ex.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00012)
				{
					l_blnCancelErrSCRAM = true;
				}
				else if (l_ex.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00013)
				{
					l_blnCancelErrTRADINGTIME = true;
				}
				else
				{
					throw new WEB3SystemLayerException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
						this.getClass().getName() + STR_METHOD_NAME,
						l_ex.getMessage(),
						l_ex);
				}
			}

			//訂正、取消ともに受付不可の場合、例外をthrowする。
			if (l_blnChangeAcceptFail && l_blnCancelAcceptFail)
			{
				if (l_blnCancelErrBATCH)
				{
					//バッチ処理中
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00011,
						this.getClass().getName() + "." + STR_METHOD_NAME,
						"訂正と取消が共に受付不可");
				}
				else if (l_blnCancelErrSCRAM)
				{
					//緊急停止中
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00012,
						this.getClass().getName() + "." + STR_METHOD_NAME,
						"訂正と取消が共に受付不可");
				}
				else
				{
					//受付可能時間外
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00013,
						this.getClass().getName() + "." + STR_METHOD_NAME,
						"訂正と取消が共に受付不可");            		
				}
			}
		}

        //発注日一覧の作成
        TreeMap l_orderBizDateMap = new TreeMap();
        Date l_datBizdate = GtlUtils.getTradingSystem().getBizDate();
        Timestamp l_tsBizdate = new Timestamp(l_datBizdate.getTime());
        
        //業務日付をセット
        Date l_datSysdate = WEB3DateUtility.toDay(l_tsBizdate);
        l_orderBizDateMap.put(l_datSysdate, l_datSysdate);
        
        //業務日付の前営業日をセット
        WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsBizdate);
        Date l_datBizDate = WEB3DateUtility.toDay(l_genBizDate.roll(-1));
        l_orderBizDateMap.put(l_datBizDate, l_datBizDate);
        
        //(部店指数別)取扱条件オブジェクトを取得する
        String l_instCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_branchCode = l_branch.getBranchCode();
        
		WEB3GentradeBranchIndexDealtCond[] l_branchIndexDealtCond =
			WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
				l_instCode, l_branchCode, WEB3FuturesOptionDivDef.OPTION);
            
        //取得した(部店指数別)取扱条件の要素数分、LOOPする
        int l_intIndexSize = 0;
        if (l_branchIndexDealtCond != null)
        {
            l_intIndexSize = l_branchIndexDealtCond.length;
        }
        for (int i = 0; i < l_intIndexSize; i++)
        {
            //reset銘柄コード
            WEB3GentradeTradingTimeManagement.resetProductCode(l_branchIndexDealtCond[i].getUnderlyingProductCode());
            //発注日を取得
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            //取得した発注日が発注日一覧に含まれていない日付の場合、発注日一覧に追加
            if (!l_orderBizDateMap.containsKey(l_datBizDate))
            {                                      
                l_orderBizDateMap.put(l_datBizDate, l_datBizDate);
            }
        }
        
        //取得した発注日一覧をセット
        int l_intListSize = l_orderBizDateMap.size();
        Date[] l_bizDateList = new Date[l_intListSize];
        Collection l_collection = l_orderBizDateMap.values();
        l_collection.toArray(l_bizDateList); 
        
        //createResponse()
        WEB3OptionsExecuteReferenceResponse l_response = null;
        l_response = (WEB3OptionsExecuteReferenceResponse)
            l_request.createResponse();
 
        //create銘柄コード名称
        WEB3FuturesOptionsProductCodeNameUnit[] l_productCodeNameUnits;
        l_productCodeNameUnits = createProductCodeName(
            (WEB3GentradeSubAccount)l_subAccount,
            l_request);
   
        //create銘柄コード名称の返り値がNULLの場合、
        //レスポンス作成、プロパティセット処理を行う。
        //（注文約定照会作成、ページング処理は行わない）
        if (l_productCodeNameUnits == null)
        {
            log.debug("create銘柄コード名称の返り値がNULL");
            //レスポンス.注文約定照会注文単位 = NULL
            l_response.opExecuteGroups = null;
            //レスポンス.発注日一覧
            l_response.orderBizDateList = l_bizDateList;
            //レスポンス.総レコード数 = 0
            l_response.totalRecords = "0";
            //レスポンス.総ページ数 = 0
            l_response.totalPages = "0";
            //レスポンス.表示ページ番号 = 0
            l_response.pageIndex = "0";
            //レスポンス.ID一覧 = NULL
            l_response.idList = null;
            //レスポンス.取引終了警告文言 = get市場閉局警告指数の返り値
            l_response.messageSuspension = l_TradeCloseMarket;
            //レスポンス.株価指数先物オプション銘柄コード名称 = NULL
            l_response.futOpProductCodeNames = null;
            
            //レスポンス.顧客ロック区分 =  is取引可能顧客の返り値がfalseならtrue
            //※ロック顧客の場合：true、ロック顧客でない場合：falseとなる
            if (l_blnIsTradedPossibleAccount == false)
            {
                l_response.accountLock = true;
            }
            else
            {
                l_response.accountLock = false;
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //create注文約定照会
        WEB3OptionsExecuteGroup[] l_executeGroups;
        l_executeGroups = createOrderExecutedInquiry(
            (WEB3GentradeSubAccount)l_subAccount,
            l_request,
            l_response,
			l_ifoProduct);
                                          
        //create注文約定照会の返り値がNULLの場合、
        //レスポンス作成、プロパティセット処理を行う。
        //(ページング処理は行わない)
        if (l_executeGroups == null)
        {
            log.debug("create注文約定照会の返り値がNULL");
            //レスポンス.注文約定照会注文単位 = NULL
            l_response.opExecuteGroups = null;
            //レスポンス.発注日一覧
            l_response.orderBizDateList = l_bizDateList;
            //レスポンス.総レコード数 = 0
            l_response.totalRecords = "0";
            //レスポンス.総ページ数 = 0
            l_response.totalPages = "0";
            //レスポンス.表示ページ番号 = 0
            l_response.pageIndex = "0";
            //レスポンス.ID一覧 = NULL
            l_response.idList = null;
            //レスポンス.取引終了警告文言 = get市場閉局警告指数の返り値
            l_response.messageSuspension = l_TradeCloseMarket;
            //レスポンス.株価指数先物オプション銘柄コード名称
            l_response.futOpProductCodeNames = l_productCodeNameUnits;
            //レスポンス.顧客ロック区分 =  is取引可能顧客の返り値がfalseならtrue
            //※ロック顧客の場合：true、ロック顧客でない場合：falseとなる
            if (l_blnIsTradedPossibleAccount == false)
            {
                l_response.accountLock = true;
            }
            else
            {
                l_response.accountLock = false;
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.debug("total record count: " + l_executeGroups.length);
        log.debug("****** レスポンス．注文情報一覧の要素数：[" + l_response.opExecuteGroups.length + "]");
        log.debug("****** レスポンス．ID一覧の要素数：[" + l_response.idList.length + "]");

        //レスポンス.取引終了警告文言 = get市場閉局警告指数の返り値
        l_response.messageSuspension = l_TradeCloseMarket;

        //レスポンス.株価指数先物オプション銘柄コード名称 = create銘柄コード名称の返り値
        l_response.futOpProductCodeNames = l_productCodeNameUnits;

        //レスポンス.顧客ロック区分 = is取引可能顧客の返り値がfalseならtrue
        //※ロック顧客の場合：true、ロック顧客でない場合：falseとなる
        if (l_blnIsTradedPossibleAccount == false)
        {
            l_response.accountLock = true;
        }
        else
        {
            l_response.accountLock = false;
        }
        
        // レスポンス.発注日一覧
        l_response.orderBizDateList = l_bizDateList;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get注文約定詳細)<BR>
     * 指定された注文IDを持つ注文単位オブジェクトの内容を画面表示用に編集し、<BR>
     * レスポンスに設定して返す。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP注文約定照会サービス）get注文約定詳細」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション当日注文約定詳細リクエストオブジェクト<BR>
     * @@return WEB3OptionsExecuteDetailsResponse
     * @@throws WEB3BaseException
     * @@roseuid 406A879202FD
     */
    protected WEB3OptionsExecuteDetailsResponse getOrderExecutedDetail(WEB3OptionsExecuteDetailsRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOrderExecutedDetail(WEB3OptionsExecuteDetailsRequest)";
        log.entering(STR_METHOD_NAME);

        //1.リクエストデータの整合性をチェックする。
        l_request.validate();

        //2.補助口座を取得する。
        SubAccount l_subAccount = getSubAccount();

        //3.受付時間チェック、システム売買停止チェック。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //4.注文IDに対応する注文単位オブジェクトを取得する。
        OrderUnit[] l_orderUnits =
            opOrderManager.getOrderUnits(Long.parseLong(l_request.id));

		if (l_orderUnits.length == 0)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00398,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"指定した注文IDに該当するデータが存在しません。");
		}

        IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderUnits[0];
        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        //銘柄を取得
        WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_orderUnit.getProduct();
        IfoProductRow l_productRow = (IfoProductRow)l_ifoProduct.getDataSourceObject();

        //5.reset銘柄コード(銘柄コード)
        WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProduct.getUnderlyingProductCode());

        //6.注文単位の注文状態区分を取得する。
        String l_strOrderStatus = WEB3IfoDataAdapter.getOrderStatusType(l_orderUnit);

        //7.注文単位の約定状態区分を取得する。
        String l_strExecType = WEB3IfoDataAdapter.getExecStatusType(l_orderUnit);

        //8.PR層用の執行条件のコードを取得する。
        String l_strPRExcCondType = WEB3IfoDataAdapter.getExecutionCondByPr(l_orderUnit.getExecutionConditionType());

        //9.get処理状況区分(注文単位)
        String l_strTransStatusType = WEB3IfoDataAdapter.getTransactionStatusType(l_orderUnit);
        
        //10  getＷ指値用有効状態区分(注文単位 : IfoOrderUnit)
        String l_strWLimitEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);
        
        //11 getＷ指値用切替前注文単価(注文単位 : IfoOrderUnit)        
        String l_strWLimitBefSwitchPrice = WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);
        
        //12 getＷ指値用切替前執行条件(注文単位 : IfoOrderUnit)
        String l_strWLimitBefSwitchExecCondType = WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);
        
        //13 is手動発注可能(IfoOrderUnit)
        boolean l_blnManualOrderPossible = opOrderManager.isManualOrderPossible(l_orderUnit);
        
        //14 get遅延区分(IfoOrderUnit)
        String l_strDelayDiv = WEB3IfoDataAdapter.getDelayDiv(l_orderUnit);        

        //15.注文単位にひもづく約定の一覧を取得する。
        OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();

        //16.注文単位にひもづくトランザクションの一覧を取得する。
        WEB3IfoFinTransactionManagerImpl l_finTransactionManager =
            (WEB3IfoFinTransactionManagerImpl)tradingModule.getFinTransactionManager();
        List l_transactionList = l_finTransactionManager.getTransactions(l_orderUnit);

        double l_dblDeliveryPrice = 0.0;    //受渡金額
        double l_dblCommission = 0.0;       //手数料
        double l_dblConsumptionTax = 0.0;   //消費税

        //17.受渡金額を取得する｡
        l_dblDeliveryPrice = opOrderManager.getNetAmount(l_orderUnit);

        BigDecimal l_bdCommission = new BigDecimal(l_dblCommission + "");
        BigDecimal l_bdConsumptionTax = new BigDecimal(l_dblConsumptionTax + "");
        int l_intListLength = l_transactionList.size();
        for (int i = 0; i < l_intListLength; i++)
        {
            IfoFinTransactionParams l_FinTransactionParams
                 = new IfoFinTransactionParams((IfoFinTransactionRow)l_transactionList.get(i));
            l_bdCommission = l_bdCommission.add(
                new BigDecimal(l_FinTransactionParams.getCommissionFee() + ""));
            l_bdConsumptionTax = l_bdConsumptionTax.add(
                new BigDecimal(l_FinTransactionParams.getCommissionFeeTax() + ""));
        }

        //16.レスポンスデータを生成する。
        WEB3OptionsExecuteDetailsResponse l_response = null;
        l_response = (WEB3OptionsExecuteDetailsResponse)l_request.createResponse();
           
        //レスポンス.銘柄名
        l_response.opProductName = l_productRow.getStandardName();
        //レスポンス.指数種別
        l_response.targetProductCode = l_ifoProduct.getUnderlyingProductCode();
        //レスポンス.限月
        l_response.delivaryMonth = l_ifoProduct.getMonthOfDelivery();
        //レスポンス.オプション商品区分
        if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProduct.getDerivativeType()))
        {
            l_response.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;                           
        }
        else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProduct.getDerivativeType()))
        {
            l_response.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;            
        }
        //レスポンス.行使価格
        l_response.strikePrice = WEB3StringTypeUtility.formatNumber(l_ifoProduct.getStrikePrice()); 
        //レスポンス.発注日
        l_response.orderBizDate = WEB3DateUtility.toDay(WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd"));

        WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //レスポンス.部店コード
        l_response.branchCode = l_account.getBranch().getBranchCode();
        //レスポンス.顧客コード(表示用)
        l_response.accountCode = l_account.getDisplayAccountCode();
        //レスポンス.顧客名(表示用)
        l_response.accountName = l_account.getDisplayAccountName();            
        //レスポンス.訂正取消区分
        l_response.changeCancelDiv = l_orderUnitRow.getModifyCancelType();
        //レスポンス.注文経路区分
        l_response.orderRootDiv = l_orderUnitRow.getOrderRootDiv();
        //レスポンス.処理状況
        l_response.transactionStateType = l_strTransStatusType;

        //拡張金融オブジェクトマネージャ
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)finApp.getFinObjectManager();
        //市場の取得
        Market l_market;
        try
        {
            l_market = l_gentradeFinObjectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003, STR_METHOD_NAME,
                l_nfe.getMessage(), l_nfe); 
        }
        //レスポンス.取引市場 = 拡張金融オブジェクトマネージャ.getMarket(注文単位.市場ID).市場コード
        l_response.marketCode = l_market.getMarketCode();
        //レスポンス.取引区分 = 注文単位.注文種別     
        l_response.tradingType = getPRLevelTradeTypeDiv(l_orderUnit.getOrderType());
        //レスポンス.建日 = nullをセット
        l_response.openDate = null;
        //レスポンス.建単価 = nullをセット
        l_response.contractPrice = null;
        //レスポンス.注文数量 = 注文単位.注文数量
        l_response.opOrderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        //レスポンス.注文単価区分 = 注文単位.isMarketOrderの返り値がtrueの場合は、"成行"を、falseの場合は、"指値"
        if (l_orderUnit.isMarketOrder())
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_response.limitPrice = null;
        }
        else
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        }
        //レスポンス.概算受渡代金 = 注文単位.概算受渡代金
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());

        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(
            WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit)))
        {
            //レスポンス.注文有効期限 = 注文単位.注文失効日付
            l_response.expirationDate = WEB3DateUtility.toDay(l_orderUnitRow.getExpirationDate());                
        }
        //レスポンス.執行条件 = get執行条件(PR層)の返り値
        l_response.execCondType = l_strPRExcCondType;
        //レスポンス.発注条件区分 = 注文単位.発注条件
        l_response.orderCondType = l_orderUnitRow.getOrderConditionType();
        //注文単位.発注条件が"逆指値"の場合
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_response.orderCondType))
        {
            //レスポンス.逆指値用プレミアム／原資産価格 = 注文単位.逆指値基準値タイプをセット
            l_response.stopPremium_underlyingAssets = l_orderUnitRow.getStopPriceType();
            //レスポンス.逆指値用発注条件単価 = 注文単位.逆指値基準値をセット
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            //レスポンス.逆指値用発注条件演算子 = 注文単位.発注条件演算子をセット
            l_response.stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
        }
        //注文単位.発注条件が"W指値"の場合
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_response.orderCondType))
        {
            //レスポンス.W指値用プレミアム／原資産価格 = 注文単位.逆指値基準値タイプをセット
            l_response.wlimitPremium_underlyingAssets = l_orderUnitRow.getStopPriceType();
            //レスポンス.W指値用発注条件単価 = 注文単位.逆指値基準値をセット
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            //レスポンス.W指値用発注条件演算子 = 注文単位.発注条件演算子をセット
            l_response.wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
            //レスポンス.W指値用注文単価区分 
            //レスポンス.W指値用注文単価
            if (l_orderUnitRow.getWLimitPrice() == 0)
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                l_response.wLimitPrice = null;
            }
            else
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
            }
            
            //レスポンス.W指値用執行条件
            l_response.wlimitExecCondType = 
                WEB3IfoDataAdapter.getExecutionCondByPr(l_orderUnitRow.getWLimitExecCondType()); 
        }
        
        //レスポンス.W指値用有効状態区分
        l_response.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
               
        //レスポンス.W指値用切替前注文単価
        l_response.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;
               
        //レスポンス.W指値用切替前執行条件
        l_response.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;
          
        //レスポンス.注文受付日 = 注文単位.受注日時
        l_response.orderDate = l_orderUnitRow.getReceivedDateTime();
        //レスポンス.注文状態区分 = get注文状態区分()の返り値
        l_response.orderState = l_strOrderStatus;
        //レスポンス.繰越エラーコード = get注文状態区分()の返り値が"繰越失敗"の場合のみ、注文単位.注文エラー理由コードをセット
        if (WEB3OrderStatusDef.NOT_TRANSFERED.equals(l_strOrderStatus))
        {
            l_response.transferErrCode = l_orderUnitRow.getErrorReasonCode();
        }
        //レスポンス.約定状態区分 = get約定状態区分()の返り値
        l_response.execType = l_strExecType;
               
        //レスポンス.遅延区分
        l_response.delayDiv = l_strDelayDiv;    
               
        //レスポンス.手動発注可能フラグ
        l_response.manualFlag = l_blnManualOrderPossible;

        //レスポンス.元発注条件区分 = 注文単位.元発注条件
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();
                          
        //レスポンス.元プレミアム/原資産価格 = 注文単位.元逆指値基準値タイプ
        l_response.orgPremium_underlyingAssets = l_orderUnitRow.getOrgStopPriceType();
          
        if(!l_orderUnitRow.getOrgStopOrderPriceIsNull())      
        {
            //レスポンス.元発注条件単価 = 注文単位.元逆指値基準値
            l_response.orgOrderCondPrice = 
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }      
                
        //レスポンス.元発注条件演算子 = 注文単位.元発注条件演算子
        l_response.orgCondOperator = l_orderUnitRow.getOrgOrderCondOperator();
                
        //レスポンス.元W指値用注文単価区分
        String l_strOrgWLimitOrderPriceDiv = WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);
        l_response.orgWLimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;
               
        //レスポンス.元W指値用注文単価
        if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))    
        {
            l_response.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }
               
        //レスポンス.元W指値用執行条件
        l_response.orgWlimitExecCondType = WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //レスポンス.夕場前繰越対象フラグ
        l_response.eveningSessionCarryoverFlag =
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

        //レスポンス.立会区分
        l_response.sessionType = l_orderUnitRow.getSessionType();

        //<約定系情報>(*2)
        //約定がある場合(注文単位.isUnexecutedがfalse)のみセット
        if (!l_orderUnit.isUnexecuted())
        {
            //レスポンス.受渡日 = 注文単位.受渡日
            l_response.deliveryDate = WEB3DateUtility.toDay(l_orderUnit.getDeliveryDate());
                
            WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProduct.getUnderlyingProductCode());
            WEB3IfoTradedProductImpl l_tradedProduct = (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct();
            double l_dblExecutedAmount = l_orderUnit.getExecutedAmount();
            double l_ExecutedQuantity = l_orderUnit.getExecutedQuantity();
            double l_dblUnitsize = l_tradedProduct.getUnitSize();

            BigDecimal l_bdExecutedAmount = new BigDecimal(l_dblExecutedAmount + "");
            BigDecimal l_bdExecutedQuantity = new BigDecimal(l_ExecutedQuantity + "");
            BigDecimal l_bdUnitsize = new BigDecimal(l_dblUnitsize + "");
            //レスポンス.約定単価 = 注文単位.合計約定金額 ÷ 注文単位.約定数量 ÷ 指数乗数(円未満は四捨五入)
            double l_dblExecPrice = l_bdExecutedAmount.divide(
                l_bdExecutedQuantity, 10, BigDecimal.ROUND_HALF_UP).divide(
                    l_bdUnitsize, 0, BigDecimal.ROUND_HALF_UP).doubleValue();
            l_response.execPrice = WEB3StringTypeUtility.formatNumber(l_dblExecPrice);
            //レスポンス.約定数量 = 注文単位.約定数量
            l_response.execQuantity = WEB3StringTypeUtility.formatNumber(l_ExecutedQuantity);
            //レスポンス.約定金額 = 注文単位.合計約定金額
            l_response.execTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblExecutedAmount);
            //レスポンス.受渡金額 = トランザクション.getNetAmountの合計値
            l_response.deliveryPrice = WEB3StringTypeUtility.formatNumber(l_dblDeliveryPrice);
            //レスポンス.手数料 = トランザクション.getCommissionFeeの合計値
            l_response.commission = WEB3StringTypeUtility.formatNumber(l_bdCommission.doubleValue());
            //レスポンス.消費税 = トランザクション.getCommissionFeeTaxの合計値
            l_response.consumptionTax = WEB3StringTypeUtility.formatNumber(l_bdConsumptionTax.doubleValue());             
            //レスポンス.注文約定詳細約定
            WEB3OptionsExecuteUnit[] l_executeDetailUnit =
                new WEB3OptionsExecuteUnit[l_orderExecutions.length];
            for (int i = 0; i < l_orderExecutions.length; i++)
            {
                l_executeDetailUnit[i] = new WEB3OptionsExecuteUnit();
     
                //株価指数オプション注文約定詳細分割約定.約定日時の設定
                l_executeDetailUnit[i].executionTimestamp =
                    l_orderExecutions[i].getExecutionTimestamp();
                //株価指数オプション注文約定詳細分割約定.約定株数の設定
                l_executeDetailUnit[i].execQuantity =
                    WEB3StringTypeUtility.formatNumber(l_orderExecutions[i].getExecutionQuantity());
                //株価指数オプション注文約定詳細分割約定.約定単価の設定
                l_executeDetailUnit[i].execPrice =
                    WEB3StringTypeUtility.formatNumber(l_orderExecutions[i].getExecutionPrice());
            }
            l_response.opExecuteUnits = l_executeDetailUnit;
        }
    log.exiting(STR_METHOD_NAME);
    return l_response;
    }

    /**
     * (get注文履歴照会)<BR>
     * 指定された注文IDを持つ注文単位オブジェクトの注文履歴を取得して<BR>
     * 画面表示用に編集し、レスポンスに設定して返す。<BR>
     * 出来るまで注文の場合は、原注文〜最新の注文までの注文履歴を対象とする。<BR>
     * <BR>
     * シーケンス図「（OP注文約定照会サービス）get注文履歴照会」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション注文履歴照会リクエストオブジェクト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsOrderHistoryResponse
     * @@throws WEB3BaseException
     * @@roseuid 409EE13103DB
     */
    protected WEB3OptionsOrderHistoryResponse getOrderActionInquiry(WEB3OptionsOrderHistoryRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderActionInquiry(WEB3OptionsOrderHistoryRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 リクエストデータの整合性をチェックする。
        l_request.validate();

        //受付時間チェック、システム売買停止チェック。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.4 注文IDに対応する注文単位オブジェクトを取得する。
        OrderUnit[] l_orderUnits =
            opOrderManager.getOrderUnits(Long.parseLong(l_request.id));

		if (l_orderUnits.length == 0)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00398,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"指定した注文IDに該当するデータが存在しません。");
		}

        OrderUnit l_orderUnit = l_orderUnits[0];

        IfoOrderUnit[] l_executedOrderUnits = null;
        if (!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(
            WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit)))
        {
            //get出来るまで注文単位FromFirstToLast(IfoOrderUnit)
            l_executedOrderUnits = getCarriedOrderUnitFromFirstToLast(
                (IfoOrderUnit)l_orderUnit);
        }
        //1.6 ArrayList()
        ArrayList l_orderHistoryList = new ArrayList();

        //注文履歴要素
        IfoOrderUnit[] l_targetOrderUnits = null;
        if (!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(
            WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit)))
        {
            l_targetOrderUnits = l_executedOrderUnits;
        }
        else
        {
            //当日限り
            l_targetOrderUnits = new IfoOrderUnit[1];
            l_targetOrderUnits[0] = (IfoOrderUnit)l_orderUnits[0];
        }

        OrderAction[] l_orderAction = null;
        
        //注文履歴要素ごとのLoop処理。
        for (int i = 0; i < l_targetOrderUnits.length; i++)
        {
            //1.7 get注文履歴一覧(注文単位 : IfoOrderUnit)
            l_orderAction = l_targetOrderUnits[i].getOrderActions();

            IfoOrderUnit l_curOrderUnit = (IfoOrderUnit)l_targetOrderUnits[i];
            for (int j = 0; j < l_orderAction.length; j++)
            {
                IfoOrderAction l_curAction = (IfoOrderAction)l_orderAction[j];
                IfoOrderActionRow l_curOrderActionRow = 
                    (IfoOrderActionRow)l_curAction.getDataSourceObject();

                //1.8 株価オプション注文履歴一覧行()
                WEB3OptionsChangeCancelHistoryGroup l_action =
                    new WEB3OptionsChangeCancelHistoryGroup();

                //1.9 get執行条件(PR層)
                String l_strPRExecCond = WEB3IfoDataAdapter.getExecutionCondByPr(
                    l_curAction.getExecutionConditionType());

                //1.10 get注文内容区分()
                String l_strOrderType = WEB3IfoDataAdapter.getOrderSpecType(
                    l_curAction,
                    l_curOrderUnit);
                    
                // get受付結果区分()
                String l_strAcceptedResultDiv = WEB3IfoDataAdapter.getAcceptResultType(l_curAction);                     
                //1.11 (*2)プロパティセット
                // 注文NO = 注文履歴.注文履歴ID
                l_action.orderActionId = String.valueOf(l_curAction.getOrderActionId());

                // 注文受付日 = 注文履歴.作成日付
                l_action.orderDate = l_curAction.getOrderActionTimestamp();
                
                // (*3)約定時に作成された履歴でない場合(注文履歴.isUnexecuted() == true)のみセット
                if (l_curAction.isUnexecuted())
                {                 
                    //1.11.1  getＷ指値用有効状態区分(注文単位 : IfoOrderUnit)
                    String l_strWLimitEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_curAction);               
        
                    //1.11.2 getＷ指値用切替前注文単価(注文単位 : IfoOrderUnit)        
                    String l_strWLimitBefSwitchPrice = WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_curAction);
                    
                    //1.11.3 getＷ指値用切替前執行条件(注文単位 : IfoOrderUnit)
                    String l_strWLimitBefSwitchExecCondType = WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_curAction);                                                    

                    // 注文数量 = (*3)注文履歴.注文数量
                    l_action.opOrderQuantity = WEB3StringTypeUtility.formatNumber(l_curAction.getQuantity());

                    // 注文単価区分 = (*3)注文履歴.isMarketOrderの返り値が
                    // trueの場合は、"成行"を、falseの場合は、"指値"をセット
                    if (l_curAction.isMarketOrder())
                    {
                        l_action.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                        l_action.limitPrice = null;
                    }
                    else
                    {
                        l_action.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                        // 注文単価 = (*3)注文単価区分が"指値"の場合のみ、
                        // 注文履歴.注文単価 をセット
                        l_action.limitPrice = WEB3StringTypeUtility.formatNumber(l_curAction.getPrice());
                    }

                    // 執行条件 =　@(*3)get執行条件(PR層)の戻り値
                    l_action.execCondType = l_strPRExecCond;

                    // 発注条件区分 = (*3)注文履歴.発注条件
                    l_action.orderCondType = l_curOrderActionRow.getOrderConditionType();

                    // 逆指値用プレミアム／原資産価格 = (*3)注文履歴.発注条件 == "逆指値"の場合のみ、注文履歴.逆指値基準値タイプ
                    // 逆指値用発注条件単価 = (*3)注文履歴.発注条件 == "逆指値"の場合のみ、注文履歴.逆指値基準値
                    // 逆指値用発注条件演算子 = (*3)注文履歴.発注条件 == "逆指値"の場合のみ、注文履歴.発注条件演算子
                    if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                        l_curOrderActionRow.getOrderConditionType()))
                    {
                        l_action.stopPremium_underlyingAssets = l_curOrderActionRow.getStopPriceType();

                        l_action.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                            l_curOrderActionRow.getStopOrderPrice());

                        l_action.stopOrderCondOperator = l_curOrderActionRow.getOrderCondOperator();
                    }
                    // W指値用プレミアム／原資産価格 = (*3)注文履歴.発注条件 == "W指値"の場合のみ、注文履歴.逆指値基準値タイプ
                    // W指値用発注条件単価 = (*3)注文履歴.発注条件 == "W指値"の場合のみ、注文履歴.逆指値基準値
                    // W指値用発注条件演算子 = (*3)注文履歴.発注条件 == "W指値"の場合のみ、注文履歴.発注条件演算子
                    // W指値用注文単価区分 = (*3)注文履歴.発注条件 == "W指値"の場合のみ、(W指値)訂正指値が0の場合は"成行"、0以外の場合は"指値"
                    // W指値用注文単価 = (*3)注文履歴.発注条件 == "W指値"&& 注文履歴.(W指値)訂正指値 != 0(指値)の場合のみ、注文履歴.(W指値)訂正指値
                    else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                        l_curOrderActionRow.getOrderConditionType()))
                    {
                        l_action.wlimitPremium_underlyingAssets = l_curOrderActionRow.getStopPriceType();

                        l_action.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                            l_curOrderActionRow.getStopOrderPrice());

                        l_action.wlimitOrderCondOperator = l_curOrderActionRow.getOrderCondOperator();
                        
                        if (l_curOrderActionRow.getWLimitPrice() == 0)
                        {
                            l_action.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                            l_action.wLimitPrice = null;
                        }
                        else
                        {
                            l_action.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

                            l_action.wLimitPrice = WEB3StringTypeUtility.formatNumber(
                                l_curOrderActionRow.getWLimitPrice());
                        }
                        
                        //W指値用執行条件
                        l_action.wlimitExecCondType = 
                            WEB3IfoDataAdapter.getExecutionCondByPr(l_curOrderActionRow.getWLimitExecCondType()); 
                        
                    }

                    //W指値用有効状態区分
                    l_action.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;                    
        
                    //W指値用切替前注文単価
                    l_action.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;
        
                    //W指値用切替前執行条件
                    l_action.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

                    //元発注条件区分 = 注文単位.元発注条件
                    l_action.orgOrderCondType = l_curOrderActionRow.getOrgOrderConditionType();
                
                    //元プレミアム/原資産価格 = 注文単位.元逆指値基準値タイプ
                    l_action.orgPremium_underlyingAssets = l_curOrderActionRow.getOrgStopPriceType();
                
                    if(!l_curOrderActionRow.getOrgStopOrderPriceIsNull())      
                    {
                        //元発注条件単価 = 注文単位.元逆指値基準値
                        l_action.orgOrderCondPrice = 
                            WEB3StringTypeUtility.formatNumber(l_curOrderActionRow.getOrgStopOrderPrice());                
                    } 

                    //元発注条件演算子 = 注文単位.元発注条件演算子
                    l_action.orgCondOperator = l_curOrderActionRow.getOrgOrderCondOperator();
                
                    //元W指値用注文単価区分
                    String l_strOrgWLimitOrderPriceDiv = WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_curAction);
                    l_action.orgWLimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;
               
                    //元W指値用注文単価
                    if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))    
                    {
                        l_action.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_curAction);
                    }
               
                    //元W指値用執行条件
                    l_action.orgWlimitExecCondType = WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_curAction);
                    
                    // 注文有効期限  = 先物OPデータアダプタ.get注文期限区分(注文単位)の戻り値が"出来るまで注文"の場合のみ
                    // 注文履歴.注文失効日付
                    if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(
                        WEB3IfoDataAdapter.getExpirationDateType(l_curOrderUnit)))
                    {
                        l_action.expirationDate = WEB3DateUtility.toDay(l_curOrderActionRow.getExpirationDate());
                    }                   
                    
                    //夕場前繰越対象フラグ
                    l_action.eveningSessionCarryoverFlag =
                        WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_curOrderUnit);

                    //立会区分
                    l_action.sessionType =
                        ((IfoOrderUnitRow)l_curOrderUnit.getDataSourceObject()).getSessionType();
                }

                //(*4)約定がある場合(注文履歴.isUnexecutedがfalse)のみセット
                if (l_curAction.isUnexecuted() == false)
                {
                    //約定数量 = (*4)注文履歴.約定数量
                    l_action.execQuantity = WEB3StringTypeUtility.formatNumber(
                        l_curAction.getExecutionQuantity());

                    //約定単価 = (*4)注文履歴.約定単価
                    l_action.execPrice = WEB3StringTypeUtility.formatNumber(
                        l_curAction.getExecutionPrice());
                }

                //注文内容区分 = get注文内容区分()の戻り値
                l_action.orderType = l_strOrderType;

                //備考(決済順序) = 注文履歴.決済順序（NULLの場合はNULLをセット)
                l_action.closingOrder = l_curOrderActionRow.getClosingOrder();

                //受付結果区分 = get受付結果区分()の戻り値
                l_action.acceptedResultDiv = l_strAcceptedResultDiv;

                //1.12 add(株価指数オプション注文履歴一覧行 : Object)
                l_orderHistoryList.add(l_action);                
            }
        }

        //1.13 toArray()
        WEB3OptionsChangeCancelHistoryGroup[] l_changeCancelHistoryGroup = 
            new WEB3OptionsChangeCancelHistoryGroup[l_orderHistoryList.size()];
        l_orderHistoryList.toArray(l_changeCancelHistoryGroup);

        //1.14 createResponse()
        WEB3OptionsOrderHistoryResponse l_response = 
            (WEB3OptionsOrderHistoryResponse)l_request.createResponse();

        //1.15 レスポンスデータにプロパティをセットし、返却する。
        l_response.opChangeCancelHistoryGroups = l_changeCancelHistoryGroup;        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get返済建玉一覧)<BR>
     * 指定された注文IDを持つ注文単位オブジェクトの返済建玉情報を取得して<BR>
     * 画面表示用に編集し、レスポンスに設定して返す。<BR>
     * 返済注文の場合のみ、コールされる。<BR>
     * <BR>
     * シーケンス図「（OP注文約定照会サービス）get返済建玉一覧」参照。<BR>
     * <BR>
     * =============================================== <BR>
     *    シーケンス図 : (先物注文約定照会サービス)get返済建玉一覧 <BR>
     *    具体位置     : 1.5 (*)注文カテゴリチェック返済注文以外の場合<BR>
     * 　@　@　@　@　@　@　@　@　@　@(注文単位.注文カテゴリ != ”OP返済注文)”)<BR>
     * 　@　@　@　@　@　@　@　@　@　@「処理対象外」の例外をthrowする。<BR>
     *          class　@　@　@　@: WEB3BusinessLayerException <BR>
     *          tag          　@: SYSTEM_ERROR_80025 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション返済建玉一覧リクエストオブジェクト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginContractListResponse
     * @@throws WEB3BaseException
     * @@roseuid 409EE1CD0060
     */
    protected WEB3OptionsCloseMarginContractListResponse getSettleContractList(WEB3OptionsCloseMarginContractListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSettleContractList(WEB3OptionsCloseMarginContractListRequest)";

        log.entering(STR_METHOD_NAME);
        
        WEB3IfoPositionManagerImpl l_positionManager = 
            (WEB3IfoPositionManagerImpl)tradingModule.getPositionManager();
        WEB3IfoFinTransactionManagerImpl l_finTransactionManager =
            (WEB3IfoFinTransactionManagerImpl)tradingModule.getFinTransactionManager();
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)finApp.getFinObjectManager();
        
        WEB3OptionsCloseMarginContractListResponse l_response = null;
        
        try
        {
            //リクエストデータの整合性をチェックする
            l_request.validate();

            //受付時間チェック、システム売買停止チェック
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            long l_lngOrderid = Long.parseLong(l_request.id);
            OrderUnit[] l_orderUnits = (OrderUnit[])
                opOrderManager.getOrderUnits(l_lngOrderid);

		    if (l_orderUnits.length == 0)
		    {
			    throw new WEB3BusinessLayerException(
				    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
				    this.getClass().getName() + "." + STR_METHOD_NAME,
				    "指定した注文IDに該当するデータが存在しません。");
		    }

            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject();
        
            //返済注文以外の場合
            if (!OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnits[0].getOrderCateg()))
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80025,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }

            IfoContractSettleOrderUnit l_ifoContractSettleOrderUnit = 
                (IfoContractSettleOrderUnit)l_orderUnits[0];
            IfoClosingContractSpec[] l_arrIfoClosingContractSpec =
                l_ifoContractSettleOrderUnit.getContractsToClose();
        
            //注文単位に該当する返済指定情報が取得できなかった場合    
            if (l_arrIfoClosingContractSpec.length == 0)
            {
			    throw new WEB3BusinessLayerException(
				    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
				    getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //reset銘柄コード
            IfoContract l_ifoContract1 = (IfoContract)l_positionManager.getContract(l_arrIfoClosingContractSpec[0].getContractId());
            IfoProduct l_ifoProduct1 = (IfoProduct)l_ifoContract1.getProduct();
            WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProduct1.getUnderlyingProductCode());        
        
            //株価指数オプション返済建玉一覧明細行オブジェクトを格納する返済建玉明細行リストを生成
            ArrayList l_closingContractList = new ArrayList();
            
            //建順位カウンター
            int l_intCloseMarginOrderNumber = 0; 

            //建玉返済指定情報要素ごとのLoop処理
            for (int i = 0; i < l_arrIfoClosingContractSpec.length; i++)
            {
                //建順位カウンターのカウントアップ
                ++ l_intCloseMarginOrderNumber;
                
                IfoClosingContractSpec l_curIfoClosingContractSpec = l_arrIfoClosingContractSpec[i];

                //返済注文数量を取得する
                double l_dblQuantity = l_curIfoClosingContractSpec.getQuantity();
            
                // 返済注文数量が0の場合、リストへの追加を行わない                 
                if (l_dblQuantity == 0.0D)                  
                {                   
                    //建順位カウンターのカウントダウン
                    -- l_intCloseMarginOrderNumber;
                    continue;               
                }                   

                //返済約定数量を取得する
                double l_dblExecutedQuantity = l_curIfoClosingContractSpec.getExecutedQuantity();
                //建玉IDを取得する
                long l_lngContractId = l_curIfoClosingContractSpec.getContractId();
                //返済指定情報にひもづく建玉オブジェクトを取得する
                WEB3IfoContractImpl l_ifoContract = 
                    (WEB3IfoContractImpl)l_positionManager.getContract(l_lngContractId);
                //建単価を取得する
                double l_dblContractPrice = l_ifoContract.getContractPrice();
                //建日を取得する
                Date l_datOpenDate = l_ifoContract.getOpenDate();
                //建約定代金を取得する
                double l_dblExecTotalPrice = l_ifoContract.getContractExecutedAmount(l_dblQuantity);
                //建手数料消費税
                double l_dblCommissionConsumptionTax = 0;
                //建手数料
                double l_dblCommission = 0;
                //約定金額
                BigDecimal l_bdExecuteAmount = new BigDecimal("0");
                //損益      
                BigDecimal l_bdLostProfit = new BigDecimal("0");
            
                //返済未約定、一部約定の場合
                if (l_dblQuantity > l_dblExecutedQuantity)
                {
                    //建手数料を取得する
                    l_dblCommission =
                        l_ifoContract.getContractCommission(l_dblQuantity - l_dblExecutedQuantity);
              
                    //建手数料消費税を取得する
                    l_dblCommissionConsumptionTax =
                        l_ifoContract.getContractCommissionConsumptionTax(l_dblQuantity - l_dblExecutedQuantity);
                }
            
                //返済一部約定、全部約定の場合
                if (l_dblExecutedQuantity > 0)
                {
                    //注文単位IDと建玉IDに該当するトランザクション(取引勘定明細)を取得する
                    List l_transacions = l_finTransactionManager.getTransactions(l_ifoContractSettleOrderUnit.getOrderUnitId(),l_ifoContract.getContractId());
                    for (int j = 0; j < l_transacions.size(); j++)
                    {
                        IfoFinTransactionRow l_transacionRow = (IfoFinTransactionRow)l_transacions.get(j); 
                        BigDecimal l_bdQuantity = new BigDecimal("" + l_transacionRow.getQuantity());
                        BigDecimal l_bdPrice = new BigDecimal("" + l_transacionRow.getPrice());
                        BigDecimal l_bdCapitalGain = new BigDecimal("" + l_transacionRow.getCapitalGain());
                        BigDecimal l_bdImportedSetupFee = new BigDecimal("" + l_transacionRow.getImportedSetupFee());
                        BigDecimal l_bdCommission = new BigDecimal("" + l_dblCommission);
                        BigDecimal l_bdImportedSetupFeeTax = new BigDecimal("" + l_transacionRow.getImportedSetupFeeTax());
                        BigDecimal l_bdCommissionConsumptionTax = new BigDecimal("" + l_dblCommissionConsumptionTax);
                        l_bdExecuteAmount = l_bdQuantity.multiply(l_bdPrice).add(l_bdExecuteAmount);
                        l_bdLostProfit = l_bdCapitalGain.add(l_bdLostProfit);
                        l_bdCommission = l_bdImportedSetupFee.add(l_bdCommission);
                        l_dblCommission = l_bdCommission.doubleValue();
                        l_bdCommissionConsumptionTax = l_bdImportedSetupFeeTax.add(l_bdCommissionConsumptionTax);
                        l_dblCommissionConsumptionTax = l_bdCommissionConsumptionTax.doubleValue();
                    }
                }

                //株価指数オプション返済建玉一覧明細行オブジェクトを生成する
                WEB3OptionsCloseMarginContractGroup l_opCloseMarginContractGroup;
                l_opCloseMarginContractGroup = new WEB3OptionsCloseMarginContractGroup();
                l_opCloseMarginContractGroup.contractPrice = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
                l_opCloseMarginContractGroup.openDate = l_datOpenDate;
                l_opCloseMarginContractGroup.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
                if (l_orderUnitRow.getLimitPrice() == 0)
                {
                    l_opCloseMarginContractGroup.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    l_opCloseMarginContractGroup.limitPrice = null;
                }
                else
                {
                    l_opCloseMarginContractGroup.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_opCloseMarginContractGroup.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
                }
            
                if(l_dblExecutedQuantity > 0)
                {
                    BigDecimal l_bdExecutedQuantity = new BigDecimal("" + l_dblExecutedQuantity);
                    //約定数量 = 先物OP返済指定情報.返済約定数量
                    l_opCloseMarginContractGroup.execQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
                    l_opCloseMarginContractGroup.execPrice = WEB3StringTypeUtility.formatNumber(
                        l_bdExecuteAmount.divide(l_bdExecutedQuantity,
                        0,
                        BigDecimal.ROUND_HALF_UP).doubleValue());
                    l_opCloseMarginContractGroup.income = WEB3StringTypeUtility.formatNumber(l_bdLostProfit.doubleValue());
                }
                l_opCloseMarginContractGroup.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblCommission);
                l_opCloseMarginContractGroup.contractCommissionConsumptionTax =
                    WEB3StringTypeUtility.formatNumber(l_dblCommissionConsumptionTax);
                l_opCloseMarginContractGroup.contractExecTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblExecTotalPrice);
                l_opCloseMarginContractGroup.closeMarginOrderNumber = String.valueOf(l_intCloseMarginOrderNumber);

                //立会区分
                l_opCloseMarginContractGroup.sessionType =
                    ((IfoContractRow)l_ifoContract.getDataSourceObject()).getSessionType();

                //プロパティをセットした株価指数オプション返済建玉一覧明細行オブジェクトを返済建玉一覧明細行リストに追加
                l_closingContractList.add(l_opCloseMarginContractGroup);
            }

            //createResponse()
            l_response = (WEB3OptionsCloseMarginContractListResponse)l_request.createResponse();
			
            l_response.marketCode =
                l_gentradeFinObjectManager.getMarket(l_ifoContract1.getMarketId()).getMarketCode();
            l_response.productName = ((IfoProductRow)l_ifoProduct1.getDataSourceObject()).getStandardName();
            if (l_ifoContract1.isLong())
            {
                l_response.contractType = WEB3IfoContractTypeDef.OPEN_BUY;
            }
            else
            {
                l_response.contractType = WEB3IfoContractTypeDef.OPEN_SELL;
            }
            l_response.closingOrder = l_orderUnitRow.getClosingOrder();
            l_response.closeMarginContractGroups = 
                (WEB3OptionsCloseMarginContractGroup[])l_closingContractList.toArray
                (new WEB3OptionsCloseMarginContractGroup[l_closingContractList.size()]);
        }
        catch(NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
    
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create銘柄コード名称)<BR>
     * 指定口座の保持する株価指数オプション注文の銘柄コードと銘柄名の一覧を取得する。<BR>
     * <BR>
     * 該当データが存在しない場合にはNULLを返却する。<BR>
     * <BR>
     * シーケンス図「（OP注文約定照会サービス）create銘柄コード名称」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション注文約定照会リクエストオブジェクト<BR>
     * @@return WEB3FuturesOptionsProductCodeNameUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 409F610B01BD
     */
    protected WEB3FuturesOptionsProductCodeNameUnit[] createProductCodeName
        (WEB3GentradeSubAccount l_subAccount, 
        WEB3OptionsExecuteReferenceRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createProductCodeName(WEB3GentradeSubAccount,WEB3OptionsExecuteReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //検索条件文字列を作成する
        String l_strSerachCond = createQueryString();

        //検索条件データコンテナを作成する
        String[] l_SearchCondDataContainer = createQueryContainer();

        //リクエストデータ.株価指数先物オプションソートキー
        String l_strSort = createSortCond(null);
        
        //指定条件に一致する注文の注文単位オブジェクトの一覧を返却する。
        List l_orderUnitList = opOrderManager.getOrderUnits(
            l_subAccount,
            ProductTypeEnum.IFO,
            l_strSerachCond,
            l_SearchCondDataContainer,
            l_strSort
            );

        //get注文単位一覧の返り値がNULLの場合には、NULLを返却し終了する。
        if (l_orderUnitList == null || l_orderUnitList.size() == 0)
        {
            return null;
        }

        TreeMap l_productList = new TreeMap();
   
        //注文単位要素ごとのLoop処理。
        int l_intListLength = l_orderUnitList.size();
        for (int i = 0; i < l_intListLength; i++)
        {
            //注文単位の取得
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderUnitList.get(i);
            //銘柄の取得
            WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_orderUnit.getProduct();
            IfoProductRow l_productRow = (IfoProductRow)l_ifoProduct.getDataSourceObject();
            //reset銘柄コード
            WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProduct.getUnderlyingProductCode());

            //リクエスト.照会区分 = "訂正取消一覧"の場合、訂正取消可能チェックを行う
            if (WEB3IfoReferenceTypeDef.CORRECTION_LIST.equals(l_request.referenceType))
            {
                //訂正取消不可の場合、以降の処理を行わない
                if (isChangeCancelEnable(l_subAccount, l_orderUnit) == false)
                {
                    continue;
                }
            }
            
            String l_strProductCode = l_productRow.getProductCode();
            String l_strProductName = l_productRow.getStandardName();

            if (!l_productList.containsKey(l_strProductCode))
            {
                WEB3FuturesOptionsProductCodeNameUnit l_productCodeName
                     = new WEB3FuturesOptionsProductCodeNameUnit();
                l_productCodeName.productCode = l_strProductCode;
                l_productCodeName.productName = l_strProductName;
                l_productList.put(
                    l_productCodeName.productCode,
                    l_productCodeName
                    );
            }
        }
        int l_size = l_productList.size();
        if(l_size == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        WEB3FuturesOptionsProductCodeNameUnit[] l_retrunValues = new WEB3FuturesOptionsProductCodeNameUnit[l_size];
        l_productList.values().toArray(l_retrunValues);
        
        log.exiting(STR_METHOD_NAME);
        return l_retrunValues;
    }

    /**
     * (create注文約定照会)<BR>
     * 注文約定照会画面／訂正取消一覧画面に表示する注文約定照会注文単位の一覧を<BR>
     * 作成する。<BR>
     * <BR>
     * 該当データが存在しない場合にはNULLを返却する。<BR>
     * <BR>
     * シーケンス図「（OP注文約定照会サービス）create注文約定照会」参照。<BR>
     * @@param l_subAccount - 補助口座オブジェクト
     * @@param l_request - 株価指数オプション注文約定照会リクエストオブジェクト
     * @@param l_response - 株価指数オプション注文約定照会レスポンスオブジェクト
     * @@param l_ifoProduct - 先物OP銘柄オブジェクト
     * @@return webbroker3.ifo.message.WEB3OptionsExecuteGroup[]
     * @@throws WEB3BaseException
     * @@roseuid 409F8859009C
     */
    protected WEB3OptionsExecuteGroup[] createOrderExecutedInquiry
        (WEB3GentradeSubAccount l_subAccount, 
         WEB3OptionsExecuteReferenceRequest l_request,
         WEB3OptionsExecuteReferenceResponse l_response,
         WEB3IfoProductImpl l_ifoProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOrderExecutedInquiry()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionsExecuteGroup[] l_ret = null;

        //1.1 検索条件文字列を作成する
        String l_strSerachCond =
            createQueryString(l_ifoProduct, l_request.orderBizDate, l_request.orderCondType);

        //1.2 検索条件データコンテナを作成する
        String[] l_SearchCondDataContainer =
            createQueryContainer(l_ifoProduct,l_request.orderBizDate, l_request.orderCondType);

        //1.3 ソート条件
        String l_strSort = createSortCond(l_request.futOpSortKeys);

        //指定条件に一致する注文の注文単位オブジェクトの一覧を返却する。
        List l_orderUnitList = opOrderManager.getOrderUnits(
            l_subAccount,
            ProductTypeEnum.IFO,
            l_strSerachCond,
            l_SearchCondDataContainer,
            l_strSort
            );

        //1.4 get注文単位一覧の返り値がNULLの場合には、NULLを返却し終了する。
        if (l_orderUnitList == null || l_orderUnitList.size() == 0)
        {
            return null;
        }

        //1.5 リクエストデータ.約定状態区分≠NULLの場合のみ、以下の処理を実施する。
        if (l_request.execType != null)
        {
            ListIterator l_iterator = l_orderUnitList.listIterator();
            while (l_iterator.hasNext())
            {
                IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_iterator.next();
                boolean l_excuteStatusDivIs = isDesignateExecutedStatus(l_request.execType, l_orderUnit);
                //指定の約定状態と異なる場合、注文単位一覧から除去
                if (l_excuteStatusDivIs == false)
                {
                    l_iterator.remove();
                }
            }
        }
        //注文単位一覧要素数が0になった場合はNULLを返却し終了する。
        if (l_orderUnitList.size() == 0)
        {
            return null;
        }

        //1.6 繰越元注文単位の除去
        IfoOrderUnit[] l_arrOrderUnitBeforeRemove =
            (IfoOrderUnit[])l_orderUnitList.toArray(new IfoOrderUnit[l_orderUnitList.size()]);
        IfoOrderUnit[] l_arrOrderUnitAfterRemove =
            opOrderManager.removeCarryOverOriginalOrderUnit(l_arrOrderUnitBeforeRemove);

        //1.7 remove繰越元注文単位の返り値がNULLの場合はNULLを返却し終了する。
        if (l_arrOrderUnitAfterRemove == null ||
            l_arrOrderUnitAfterRemove.length == 0)
        {
            return null;
        }

        //注文約定照会注文単位リストを生成
        ArrayList l_OrderUnitList = new ArrayList(l_arrOrderUnitAfterRemove.length);
        //注文単位リストを生成
        ArrayList l_SelectedOrderUnitList = new ArrayList(l_arrOrderUnitAfterRemove.length);
        
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)finApp.getFinObjectManager();
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)
            tradingModule.getPositionManager();

        try
        {

            for (int i = 0; i < l_arrOrderUnitAfterRemove.length; i++)
            {
                //注文単位の取得
                IfoOrderUnit l_curOrderUnit = l_arrOrderUnitAfterRemove[i];
                IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_curOrderUnit.getDataSourceObject();

                //市場の取得
                Market l_market = l_finObjectManager.getMarket(l_ifoOrderUnitRow.getMarketId());

                //銘柄の取得
                WEB3IfoProductImpl l_curIfoProduct = (WEB3IfoProductImpl)l_curOrderUnit.getProduct();
                IfoProductRow l_curProductRow = (IfoProductRow)l_curIfoProduct.getDataSourceObject();

                //1.8 reset銘柄コード
                WEB3GentradeTradingTimeManagement.resetProductCode(l_curIfoProduct.getUnderlyingProductCode());

                //1.9 取引銘柄の取得 
                WEB3IfoTradedProductImpl l_ifoTradedProduct = (WEB3IfoTradedProductImpl)l_curOrderUnit.getTradedProduct();

                //1.10 注文種別の取得
                OrderTypeEnum l_orderType = l_curOrderUnit.getOrderType();

                //is買建 
                boolean l_blnIsCallOptions = false;
                if(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType) ||
                    OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType))
                {
                    l_blnIsCallOptions = true;
                }
                //is新規建
                boolean l_blnIsOpenContractOrder = false;
                if(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType) ||
                  OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType))
                {
                    l_blnIsOpenContractOrder = true;
                }
                //1.11 is取引規制
                boolean l_blnTradingSuspended = false;
                l_blnTradingSuspended = l_ifoTradedProduct.isTradingSuspended(
                    l_blnIsCallOptions,
                    l_blnIsOpenContractOrder);

                //1.12 リクエスト.照会区分 = "訂正取消一覧"の場合、訂正取消不可ならば、以降の処理は行わない
                boolean l_blnCanChangeCancel = isChangeCancelEnable(l_subAccount, l_curOrderUnit);
                if (WEB3IfoReferenceTypeDef.CORRECTION_LIST.equals(l_request.referenceType))
                {
                    if (l_blnCanChangeCancel == false)
                    {
                        continue;
                    }
                }

                //1.13 株価指数オプション注文約定照会注文単位()
                WEB3OptionsExecuteGroup l_referenceOrderUnit = new WEB3OptionsExecuteGroup();

                //1.14 プロパティセット
                //ID =  注文単位.注文ID
                l_referenceOrderUnit.id = WEB3StringTypeUtility.formatNumber(l_curOrderUnit.getOrderId());
                //銘柄名
                l_referenceOrderUnit.opProductName = l_curProductRow.getStandardName();
                //取引市場
                l_referenceOrderUnit.marketCode = l_market.getMarketCode();
                // 銘柄コード
                l_referenceOrderUnit.opProductCode = l_curIfoProduct.getProductCode();
                // 指数種別
                l_referenceOrderUnit.targetProductCode = l_curIfoProduct.getUnderlyingProductCode();
                // 限月
                l_referenceOrderUnit.delivaryMonth = l_curIfoProduct.getMonthOfDelivery();
                // オプション商品区分
                if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_curIfoProduct.getDerivativeType()))
                {
                    l_referenceOrderUnit.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
                }
                else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_curIfoProduct.getDerivativeType()))
                {
                    l_referenceOrderUnit.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
                }
                // 行使価格
                l_referenceOrderUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_curIfoProduct.getStrikePrice());

                //訂正可能フラグ
                //以下のいずれかにあてはまる場合はfalseをセット。以外はtrueをセット。
                //・売買規制チェックがチェックNG(is取引規制がtrue)
                //・訂正取消可能チェックがチェックNG
                //・validate注文訂正可能状態が例外をthrow
                WEB3IfoOrderManagerReusableValidations l_ifoOrderValidator =
                    (WEB3IfoOrderManagerReusableValidations)
                        WEB3IfoOrderManagerReusableValidations.getInstance();

                Order l_order = l_curOrderUnit.getOrder();
                
                if (l_blnTradingSuspended == true || l_blnCanChangeCancel == false)
                {
                    l_referenceOrderUnit.changeFlag = false;
                }
                else
                {
                    try
                    {
                        l_ifoOrderValidator.validateOrderForChangeability(l_order);
                        l_referenceOrderUnit.changeFlag = true;
                    }
                    catch (OrderValidationException l_ovE) 
                    {
                        l_referenceOrderUnit.changeFlag = false;
                    }
                }

                //取消可能フラグ
                //以下のいずれかにあてはまる場合はfalseをセット。以外はtrueをセット。
                //・訂正取消可能チェックがチェックNG
                //・validate注文取消可能状態が例外をthrow                
                if (l_blnCanChangeCancel == false)
                {
                    l_referenceOrderUnit.cancelFlag = false;
                }
                else
                {
                    try
                    {
                        l_ifoOrderValidator.validateOrderForCancellation(l_order);
                        l_referenceOrderUnit.cancelFlag = true;
                    }
                    catch (OrderValidationException l_ovE)
                    {
                        l_referenceOrderUnit.cancelFlag = false;
                    }
                }

                //1.15 注文約定照会注文単位リストに追加
                l_OrderUnitList.add(l_referenceOrderUnit);

                //1.16 注文単位リストに追加
                l_SelectedOrderUnitList.add(l_curOrderUnit);
            }

            if(l_OrderUnitList.size() == 0)
            {
                return null;
            }

            //1.17 toArray
            l_ret = new WEB3OptionsExecuteGroup[l_OrderUnitList.size()];
            l_OrderUnitList.toArray(l_ret);

            //レスポンスのページ関連項目設定
            //1.18 レスポンス.ID一覧：　@検索条件に該当する注文IDを全てセット
            // （要素数は、総レコード数に等しい）
            if (l_OrderUnitList != null)
            {
                int l_intOrderSize = l_ret.length;
                l_response.idList = new String[l_intOrderSize];
                for (int i = 0;i < l_intOrderSize; i++)
                {
                    l_response.idList[i] = l_ret[i].id;
                }
            }

            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            int l_intPageSize = Integer.parseInt(l_request.pageSize);

            //1.19 表示対象となる注文約定照会注文単位リストの作成
            WEB3PageIndexInfo l_pageIndexInfo =
                new WEB3PageIndexInfo(l_ret, l_intPageIndex, l_intPageSize);
            l_response.opExecuteGroups
                = (WEB3OptionsExecuteGroup[])l_pageIndexInfo.getArrayReturned(WEB3OptionsExecuteGroup.class);

            //レスポンス.表示ページ番号
            l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
            //レスポンス.総ページ数
            l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
            //レスポンス.総レコード数
            l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());

            //表示対象となる注文単位リストの作成
            WEB3PageIndexInfo l_ordersAtPage =
                new WEB3PageIndexInfo(l_SelectedOrderUnitList, l_intPageIndex, l_intPageSize);
            Object[] l_objReturned = l_ordersAtPage.getArrayReturned(IfoOrderUnit.class);

            for (int i = 0; i < l_response.opExecuteGroups.length; i++)
            {
                WEB3OptionsExecuteGroup l_referenceOrderUnit = l_response.opExecuteGroups[i];
                IfoOrderUnit l_curOrderUnit = (IfoOrderUnit)l_objReturned[i];
                IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_curOrderUnit.getDataSourceObject();

                //銘柄の取得
                WEB3IfoProductImpl l_curIfoProduct = (WEB3IfoProductImpl)l_curOrderUnit.getProduct();

                //1.20 reset銘柄コード
                WEB3GentradeTradingTimeManagement.resetProductCode(l_curIfoProduct.getUnderlyingProductCode());

                //取引銘柄の取得 
                WEB3IfoTradedProductImpl l_ifoTradedProduct = (WEB3IfoTradedProductImpl)l_curOrderUnit.getTradedProduct();

                //1.21 get執行条件(PR層)
                String l_PRExcCondType = WEB3IfoDataAdapter.getExecutionCondByPr(
                    l_ifoOrderUnitRow.getExecutionConditionType());

                //1.23 get処理状況区分()
                String l_transactionStateType =
                    WEB3IfoDataAdapter.getTransactionStatusType(l_curOrderUnit);
                 
                //1.24 getＷ指値用有効状態区分(注文単位 : IfoOrderUnit)
                String l_strWLimitEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_curOrderUnit);           
                
                //1.25  getＷ指値用切替前注文単価(注文単位 : IfoOrderUnit)
                String l_strWLimitBefSwitchPrice = WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_curOrderUnit);
                
                //1.26 getＷ指値用切替前執行条件(注文単位 : IfoOrderUnit)
                String l_strWLimitBefSwitchExecCondType = WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_curOrderUnit);
                
                //1.27 is手動発注可能(IfoOrderUnit)
                boolean l_blnManualOrderPossible = opOrderManager.isManualOrderPossible(l_curOrderUnit);
                
                //1.28 get遅延区分(IfoOrderUnit)
                String l_strDelayDiv = WEB3IfoDataAdapter.getDelayDiv(l_curOrderUnit);
                
                //建日、建単価
                // [注文単位.注文カテゴリ == ”OP新規建注文”の場合]
                if(OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_ifoOrderUnitRow.getOrderCateg()))
                {
                    l_referenceOrderUnit.openDate = null;
                    l_referenceOrderUnit.contractPrice = null;    
                }
                // [注文単位.注文カテゴリ == ”OP返済注文”の場合]
                else if(OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_ifoOrderUnitRow.getOrderCateg())) 
                {
                    //建玉返済指定情報 = 返済注文単位.getContractsToClose()で取得
                    IfoContractSettleOrderUnit l_contractSettleOrderUnit = (IfoContractSettleOrderUnit)l_curOrderUnit;
                    IfoClosingContractSpec[] l_spec = l_contractSettleOrderUnit.getContractsToClose();
                        
                    if(l_ifoOrderUnitRow.getClosingOrder() == null)
                    {
                        l_referenceOrderUnit.openDate = l_positionManager.getContract(l_spec[0].getContractId()).getOpenDate();
                        l_referenceOrderUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_positionManager.getContract(l_spec[0].getContractId()).getContractPrice());
                    }
                    else
                    {
                        l_referenceOrderUnit.openDate = null;
                        double l_dblTotalContractAmount = 0.0D;
                        double l_dblTotalQuantity = 0.0D;

                        BigDecimal l_bdTotalContractAmount = new BigDecimal(l_dblTotalContractAmount + "");
                        //建玉返済指定情報の要素ごとに建代金を算出し、集計してSUM値を取得する
                        for (int j = 0;j < l_spec.length;j++)
                        {
                            //建代金 += (建玉返済指定情報.建玉IDから取得した建玉.建単価 * 建玉返済指定情報.返済注文数量)
                            Contract l_contract = l_positionManager.getContract(l_spec[j].getContractId());
                            double l_dblQuantity = l_spec[j].getQuantity();
                            l_bdTotalContractAmount = l_bdTotalContractAmount.add(
                                new BigDecimal(l_contract.getContractPrice() + "").multiply(
                                    new BigDecimal(l_dblQuantity + "")));
                            l_dblTotalQuantity += l_dblQuantity;
                        }
                        //建単価の平均値 = 建代金 /　@建玉返済指定情報.返済注文数量のSUM値(円未満は四捨五入)
                        l_referenceOrderUnit.contractPrice =
                            WEB3StringTypeUtility.formatNumber(l_bdTotalContractAmount.divide(
                                new BigDecimal(l_dblTotalQuantity + ""), 0, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                }
                //取引区分 = 注文単位.注文種別
                l_referenceOrderUnit.tradingType = getPRLevelTradeTypeDiv(l_curOrderUnit.getOrderType());
                //注文時間 = 注文単位.受注日時
                l_referenceOrderUnit.orderDate = l_ifoOrderUnitRow.getReceivedDateTime();
                //発注日 = 注文単位.発注日
                l_referenceOrderUnit.orderBizDate = WEB3DateUtility.toDay(WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd"));
                //注文数量 = 注文単位.注文数量
                l_referenceOrderUnit.opOrderQuantity = WEB3StringTypeUtility.formatNumber(l_curOrderUnit.getQuantity());
                //注文単価区分
                //注文単価
                if (l_curOrderUnit.isMarketOrder())
                {
                    l_referenceOrderUnit.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    l_referenceOrderUnit.limitPrice = null;
                }
                else
                {
                    l_referenceOrderUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_referenceOrderUnit.limitPrice = WEB3StringTypeUtility.formatNumber(l_curOrderUnit.getLimitPrice());
                }
                //執行条件 = get執行条件(PR層)の返り値
                l_referenceOrderUnit.execCondType = l_PRExcCondType;
                //発注条件区分 = 注文単位.発注条件
                l_referenceOrderUnit.orderCondType = l_ifoOrderUnitRow.getOrderConditionType();

                //注文単位.発注条件が"逆指値"の場合
                if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_referenceOrderUnit.orderCondType))
                {
                    l_referenceOrderUnit.stopPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType();
                    l_referenceOrderUnit.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());
                    l_referenceOrderUnit.stopOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
                }
                //注文単位.発注条件が"W指値"の場合
                else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_referenceOrderUnit.orderCondType))
                {
                    l_referenceOrderUnit.wlimitPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType();
                    l_referenceOrderUnit.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());
                    l_referenceOrderUnit.wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
                    if (l_ifoOrderUnitRow.getWLimitPrice() == 0D)
                    {
                        l_referenceOrderUnit.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    }
                    else
                    {
                        l_referenceOrderUnit.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                        l_referenceOrderUnit.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getWLimitPrice());
                    }
                    
                    //W指値用執行条件
                    l_referenceOrderUnit.wlimitExecCondType = 
                        WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getWLimitExecCondType()); 
                 }
                
                //W指値用有効状態区分
                l_referenceOrderUnit.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
               
                //W指値用切替前注文単価
                l_referenceOrderUnit.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;
               
                //W指値用切替前執行条件
                l_referenceOrderUnit.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;
                           
                //(*7)約定数量、約定単価、注文約定詳細約定
                //1.29 約定がある場合(注文単位.isUnexecutedがfalse)のみセット
                if (!l_curOrderUnit.isUnexecuted())
                {
                    //1.29.1 getExecutions()
                    OrderExecution[] l_orderExecution = l_curOrderUnit.getExecutions();

                    //取引銘柄の取得 
                    l_ifoTradedProduct = (WEB3IfoTradedProductImpl)l_curOrderUnit.getTradedProduct();
                    //注文単位.合計約定金額の取得
                    double l_dblExecutedAmount = l_curOrderUnit.getExecutedAmount();
                    //注文単位.約定数量の取得
                    double l_dblExecutedQuantity = l_curOrderUnit.getExecutedQuantity();
                    //指数乗数の取得
                    double l_dblUnitSize = l_ifoTradedProduct.getUnitSize();
                    //約定単価 = 注文単位.合計約定金額 ÷ 注文単位.約定数量 ÷指数乗数(円未満は四捨五入)
                    BigDecimal l_bdExecutedAmount = new BigDecimal(l_dblExecutedAmount + "");
                    BigDecimal l_bdExecutedQuantity = new BigDecimal(l_dblExecutedQuantity + "");
                    BigDecimal l_bdUnitSize = new BigDecimal(l_dblUnitSize + "");
                    double l_execPrice = l_bdExecutedAmount.divide(
                        l_bdExecutedQuantity, 10, BigDecimal.ROUND_HALF_UP).divide(
                            l_bdUnitSize, 0, BigDecimal.ROUND_HALF_UP).doubleValue();
                    l_referenceOrderUnit.execPrice = WEB3StringTypeUtility.formatNumber(l_execPrice);
                    //約定数量
                    l_referenceOrderUnit.execQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
                    //注文約定詳細約定
                    WEB3OptionsExecuteUnit[] l_executeUnit =
                        new WEB3OptionsExecuteUnit[l_orderExecution.length];
                    for (int j = 0; j < l_orderExecution.length; j++)
                    {
                        l_executeUnit[j] = new WEB3OptionsExecuteUnit();
                        //OP株式注文約定照会約定.約定単価の設定
                        l_executeUnit[j].execPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderExecution[j].getExecutionPrice());
                        //OP株式注文約定照会約定.約定株数の設定
                        l_executeUnit[j].execQuantity =
                            WEB3StringTypeUtility.formatNumber(l_orderExecution[j].getExecutionQuantity());
                        //OP株式注文約定照会約定.約定日時の設定
                        l_executeUnit[j].executionTimestamp =
                            l_orderExecution[j].getExecutionTimestamp();
                    }
                    l_referenceOrderUnit.opExecuteUnits = l_executeUnit;

                    //1.29.2 受渡金額の取得
                    double l_dblDeliveryAmt = opOrderManager.getNetAmount(l_curOrderUnit);
                    l_referenceOrderUnit.deliveryPrice = WEB3StringTypeUtility.formatNumber(l_dblDeliveryAmt);
                }
                         
                //概算受渡代金 = 注文単位.概算受渡代金
                l_referenceOrderUnit.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getEstimatedPrice());
                //処理状況 = get処理状況区分()の返り値
                l_referenceOrderUnit.transactionStateType =
                    l_transactionStateType;
                //注文有効期限 = get注文期限区分が出来るまでの場合のみ、
                //注文単位.注文失効日付をセット。以外、NULLをセット
                if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(
                    WEB3IfoDataAdapter.getExpirationDateType(l_curOrderUnit)))
                { 
                    l_referenceOrderUnit.expirationDate =
                                        l_curOrderUnit.getExpirationTimestamp();              
                }
                else
                {
                    l_referenceOrderUnit.expirationDate = null;          
                }
                
                if (this.getTrader() != null)
                {
                    //注文チャネル = 注文単位.初回注文の注文チャネルをセット
                    l_referenceOrderUnit.orderChannel = l_ifoOrderUnitRow.getOrderChanel();
                    //注文経路区分 = 注文単位.注文経路区分をセット
                    l_referenceOrderUnit.orderRootDiv = l_ifoOrderUnitRow.getOrderRootDiv();
                    //注文単位.取引者ID≠nullの場合のみ、扱者コードをセット
                    if (!l_ifoOrderUnitRow.getTraderIdIsNull())
                    {
                        try
                        {
                                //拡張金融オブジェクトマネージャ.getTrader()
                                Trader l_trader = null;
                                l_trader = l_finObjectManager.getTrader(l_curOrderUnit.getTraderId());
                                l_referenceOrderUnit.operatorCode = l_trader.getTraderCode();
                        }
                        catch (NotFoundException l_nfe)
                        {
                                //注文単位.取引者IDに該当するデータが扱者テーブルに存在しない場合
                                l_referenceOrderUnit.operatorCode = null;
                        }
                    }
                }
               
                //遅延区分
                l_referenceOrderUnit.delayDiv = l_strDelayDiv;
                
                //手動発注可能フラグ
                l_referenceOrderUnit.manualFlag = l_blnManualOrderPossible;    

                //元発注条件区分 = 注文単位.元発注条件
                l_referenceOrderUnit.orgOrderCondType = l_ifoOrderUnitRow.getOrgOrderConditionType();
                
                //元プレミアム/原資産価格 = 注文単位.元逆指値基準値タイプ
                l_referenceOrderUnit.orgPremium_underlyingAssets = l_ifoOrderUnitRow.getOrgStopPriceType();
                
                if(!l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull())      
                {
                    //元発注条件単価 = 注文単位.元逆指値基準値
                    l_referenceOrderUnit.orgOrderCondPrice = 
                        WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getOrgStopOrderPrice());                
                } 
                
                //元発注条件演算子 = 注文単位.元発注条件演算子
                l_referenceOrderUnit.orgCondOperator = l_ifoOrderUnitRow.getOrgOrderCondOperator();
                
                //元W指値用注文単価区分
                String l_strOrgWLimitOrderPriceDiv = WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_curOrderUnit);
                l_referenceOrderUnit.orgWLimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;
               
                //元W指値用注文単価
                if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))    
                {
                    l_referenceOrderUnit.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_curOrderUnit);
                }
               
                //元W指値用執行条件
                l_referenceOrderUnit.orgWlimitExecCondType = WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_curOrderUnit);

                //夕場前繰越対象フラグ
                l_referenceOrderUnit.eveningSessionCarryoverFlag =
                    WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_curOrderUnit);

                //立会区分
                l_referenceOrderUnit.sessionType = l_ifoOrderUnitRow.getSessionType();
            }
        }
        catch (NotFoundException l_nfE)
        {
            String l_strMsg = "データ取得エラー";
            log.error(l_strMsg, l_nfE);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_ret;
    }

    /**
     * (create検索条件文字列)<BR>
     * 検索条件（where以下指定の文字列）を作成する。<BR>
     * <BR>
     * (1)戻り値となる文字列のインスタンスを生成<BR>
     * <BR>
     * (2)先物／オプション区分を文字列インスタンスに設定<BR>
     * <BR>
     *     " future_option_div = ?"<BR>
     * <BR>
     * (3)発注日指定を文字列インスタンスに設定<BR>
     * <BR>
     * (3-1)パラメータ.発注日≠NULL（発注日指定）の場合<BR>
     * <BR>
     *     " and biz_date = ?" <BR>
     * <BR>
     * (3-2)パラメータ.発注日＝NULL（発注日指定なし）の場合<BR>
     * <BR>
     *     " and biz_date >= ?" <BR>
     * <BR>
     * (4)パラメータ.先物OP銘柄≠NULL（銘柄指定）の場合、<BR>
     * 銘柄ID指定を追加（銘柄IDで検索を行う)<BR>
     * <BR> 
     *     " and product_id=?" <BR>
     * <BR>
     * (5)パラメータ.発注条件区分≠NULL（発注条件区分指定）の場合、<BR>
     * 　@　@発注条件区分指定を追加 <BR>
     * 　@　@（先物OP注文単位テーブル.元発注条件に値が設定されている場合は、<BR>
     * 　@　@　@　@　@元発注条件をもとに検索する。  <BR>
     * 　@　@　@元発注条件に値が設定されていない場合は、<BR>
     * 　@　@　@　@　@先物OP注文単位テーブル.発注条件をもとに検索する。) <BR>
     *   <BR>
     * 　@　@　@" and nvl(org_order_condition_type,order_condition_type) = ?"  <BR>
     * <BR>
     * (6)文字列インスタンスを返却 <BR>
     * @@param l_ifoProduct - 先物OP銘柄オブジェクト<BR>
     * @@param l_datBizDate - 発注日<BR>
     * @@param l_strOrderCondType - (発注条件区分)<BR>
     * 発注条件区分<BR>
     * @@return String
     * @@roseuid 40A07C680399
     */
    protected String createQueryString(
        WEB3IfoProductImpl l_ifoProduct,Date l_datBizDate, String l_strOrderCondType)
    {
        final String STR_METHOD_NAME =
            "createQueryString(WEB3IfoProductImpl,Date,String)";

        log.entering(STR_METHOD_NAME);

        //先物／オプション区分、および発注日の範囲を文字列インスタンスに設定
        String l_strSearchCond = " and future_option_div = ? ";

        //パラメータ.発注日≠NULLの場合
        if (l_datBizDate != null && !l_datBizDate.equals(""))
        {
            l_strSearchCond += "and biz_date = ? ";
        }
        else
        {
            l_strSearchCond += "and biz_date >= ? ";
        }
        
        //パラメータ.先物OP銘柄≠NULL
        if(l_ifoProduct != null)
        {
            l_strSearchCond += "and product_id = ? ";            
        }
        
        //パラメータ.発注条件区分≠NULL（発注条件区分指定）の場合
        if (l_strOrderCondType != null)
        {
            l_strSearchCond += "and nvl(org_order_condition_type,order_condition_type) = ? "; 
        }
        log.debug("検索条件文字列 = " + l_strSearchCond);
        log.exiting(STR_METHOD_NAME);

        return l_strSearchCond;
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件（where以下指定の文字列）のパラメータの文字列配列を作成する。<BR>
     * <BR>
     * (1)文字列配列を生成<BR>
     * <BR>
     * (2)先物／オプション区分を文字列配列にセット<BR>
     * 　@　@　@先物／オプション区分 ＝ "オプション"<BR>
     * 　@　@　@※先物／オプション区分 1：先物　@2：オプション<BR>
     * <BR>
     * (3)発注日指定値を文字列配列にセット <BR>
     * (3-1)パラメータ.発注日≠NULL（発注日指定）の場合 <BR>
     * 発注日指定値 ＝ パラメータ.発注日 <BR>
     * <BR>
     * (3-2)パラメータ.発注日＝NULL（発注日指定なし）の場合 <BR>
     * 　@　@　@発注日指定値 ＝ 業務日付(*1) <BR>
     * <BR>
     * (4)パラメータ.先物OP銘柄≠NULL（銘柄指定）の場合、<BR>
     * 銘柄IDを文字列配列にセット（銘柄IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@銘柄ID ＝ パラメータ.先物OP銘柄.銘柄ID,<BR>
     * <BR>
     * (5)パラメータ.発注条件区分≠NULL（発注条件区分指定）の場合、<BR>
     * 　@　@パラメータ.発注条件区分を文字列配列にセット <BR>
     * <BR>
     * 　@　@　@発注条件区分 ＝ パラメータ.発注条件区分 <BR>
     * <BR>
     * (6)(2)、(3)、(4)、(5)にてパラメータをセットした文字列配列を返却<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_ifoProduct - 先物OP銘柄オブジェクト<BR>
     * @@param l_datBizDate - 発注日<BR>
     * @@param l_strOrderCondType - (発注条件区分)<BR>
     * 発注条件区分<BR>
     * @@return String[]
     * @@roseuid 40A07C6803C8
     */
    protected String[] createQueryContainer(
        WEB3IfoProductImpl l_ifoProduct,Date l_datBizDate, String l_strOrderCondType)
    {
        final String STR_METHOD_NAME =
            "createQueryContainer(WEB3IfoProductImpl,Date,String)";

        log.entering(STR_METHOD_NAME);

        //検索条件の数量を設定する。
        int l_intCondCount = 2;

        if (l_ifoProduct != null)
        {
            l_intCondCount++;
        }
        if (l_strOrderCondType != null)
        {
            l_intCondCount++;
        }

        //文字列配列を生成
        String[] l_strParam = new String[l_intCondCount];

        //先物／オプション区分: 2 "オプション"
        l_strParam[0] = WEB3FuturesOptionDivDef.OPTION;

        //パラメータ.発注日＝NULL
        if (l_datBizDate != null && !"".equals(l_datBizDate))
        {
            l_strParam[1] = WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd");
        }
        else
        {
            //業務日付を取得                 
            Date l_datDate = GtlUtils.getTradingSystem().getBizDate();
            l_strParam[1] = WEB3DateUtility.formatDate(l_datDate,"yyyyMMdd");
        }

        //パラメータ.先物OP銘柄≠NULL
        if (l_ifoProduct != null)
        {
            //銘柄IDをセットする
            l_strParam[2] = String.valueOf(l_ifoProduct.getProductId());
            //パラメータ.発注条件区分≠NULL（発注条件区分指定）の場合
            if (l_strOrderCondType != null)
            {
                l_strParam[3] = l_strOrderCondType;
            }
        }
        else
        {
            //パラメータ.発注条件区分≠NULL（発注条件区分指定）の場合
            if (l_strOrderCondType != null)
            {
                l_strParam[2] = l_strOrderCondType;
            }
        }
        log.debug("検索条件データコンテナ配列数 = " + l_strParam.length);
        log.exiting(STR_METHOD_NAME);

        return l_strParam;
    }


    /**
     * (create検索条件文字列)<BR>
     * 検索条件（where以下指定の文字列）を作成する。<BR>
     * <BR>
     * (1)戻り値となる文字列のインスタンスを生成<BR>
     * <BR>
     * (2)先物／オプション区分を文字列インスタンスに設定<BR>
     * <BR>
     *     " future_option_div = ?"<BR>
     * <BR>
     * (3)発注日指定を文字列インスタンスに設定<BR>
     * <BR>
     *     " and biz_date >= ?" <BR>
     * <BR>
     * (4)文字列インスタンスを返却 <BR>
     */
    protected String createQueryString()
    {
        final String STR_METHOD_NAME = "createQueryString()";
        log.entering(STR_METHOD_NAME);

        //先物／オプション区分、および発注日の範囲を文字列インスタンスに設定
        String l_strSearchCond = " and future_option_div = ? ";

        //発注日指定を文字列インスタンスに設定
        l_strSearchCond += "and biz_date >= ? ";

        log.exiting(STR_METHOD_NAME);
        return l_strSearchCond;
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件（where以下指定の文字列）のパラメータの文字列配列を作成する。<BR>
     * <BR>
     * (1)文字列配列を生成<BR>
     * <BR>
     * (2)先物／オプション区分を文字列配列にセット<BR>
     * 　@　@　@先物／オプション区分 ＝ "オプション"<BR>
     * 　@　@　@※先物／オプション区分 1：先物　@2：オプション<BR>
     * <BR>
     * (3)発注日指定値を文字列配列にセット <BR>
     * 　@　@　@発注日指定値 ＝ 業務日付(*1)の前営業日<BR>
     * <BR>
     * (4)(2)、(3)にてパラメータをセットした文字列配列を返却<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@return String[]
     */
    protected String[] createQueryContainer() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryContainer()";
        log.entering(STR_METHOD_NAME);

        //文字列配列を生成
        String[] l_strParam = new String[2];

        //先物／オプション区分: 2 "オプション"
        l_strParam[0] = WEB3FuturesOptionDivDef.OPTION;

        //業務日付の前営業日を取得
        Date l_datDate = GtlUtils.getTradingSystem().getBizDate();
		Date l_datFormerDate = (Date)WEB3GentradeUtils.getBizDate(l_datDate, -1);

        //発注日指定値を文字列配列にセット
        l_strParam[1] = WEB3DateUtility.formatDate(l_datFormerDate,"yyyyMMdd");

        log.exiting(STR_METHOD_NAME);
        return l_strParam;
    }

    /**
     * (createソート条件)<BR>
     *ソート条件文字列を作成し返す。<BR>
     *<BR>
     *(1)パラメータ.ソートキー == nullの場合は、 <BR>
     *　@"銘柄ID(*) 昇順"のソート条件文字列を返却する。 <BR>
     *<BR>
     *　@(*)注文単位テーブル.銘柄ID<BR>
     *<BR>
     *(2)パラメータ.ソートキー.キー項目の数分、<BR>
     *対応するテーブルの列物理名を昇順／降順指定を付加しソート条件文字列をセットしていく。<BR>
     *<BR>
     *　@　@  ・キー項目とテーブルの列名称との対応は以下の通り<BR>
     *　@　@　@　@　@　@・銘柄コード　@　@ 　@　@  ：注文単位テーブル．銘柄ID<BR>
     *　@　@　@　@　@　@・取引市場　@　@ 　@　@  　@：注文単位テーブル．市場ID<BR>
     *　@　@　@　@　@　@・取引区分　@　@　@　@　@　@：注文単位テーブル．注文種別<BR>
     *　@　@　@　@　@　@・注文時間　@　@　@　@　@　@：注文単位テーブル．受注日時<BR>
     *　@　@　@　@　@　@・発注日　@　@　@　@　@　@　@　@：注文単位テーブル．発注日<BR>
     *　@　@　@　@　@　@・注文有効期限　@　@　@：注文単位テーブル．注文失効日付<BR>
     *<BR>
     *　@　@・昇順／降順指定は、ソートキー.昇順／降順 指定に従い設定<BR>
     *<BR>
     *(3)ソート条件文字列末尾に、注文単位テーブル.更新日付を昇順指定で付加<BR>
     *<BR>
     *(4)作成したソート条件文字列を返却<BR>
     *<BR>
     *※キー項目文字列（シンボル名）は、メッセージ定義書を参照<BR>
     *※テーブル名：注文単位テーブル(ifo_order_unit)<BR>
     *※テーブルの列物理名は、テーブルレイアウトを参照<BR>
     * @@param l_futuresOptionsSortKey - (株価指数先物オプションソートキー)<BR>
     * 株価指数先物オプションソートキー<BR>
     * @@return String
     * @@roseuid 40A07C69002E
     */
    protected String createSortCond(WEB3FuturesOptionsSortKey[] l_futuresOptionsSortKey)
    {
        final String STR_METHOD_NAME =
            "createSortCond(WEB3FuturesOptionsSortKey)";

        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_strReturn = new StringBuffer();
        if (l_futuresOptionsSortKey == null)
        {
            l_strReturn.append("product_id");
            l_strReturn.append(" ");
            l_strReturn.append("ASC");
            return l_strReturn.toString();
        }

        //返還の値の設定        
        for (int i = 0; i < l_futuresOptionsSortKey.length; i++)
        {
            log.debug(" キー項目" + i + " = "+ l_futuresOptionsSortKey[i].keyItem);
            log.debug(" 昇順／降順" + i + " = "+ l_futuresOptionsSortKey[i].keyItem);
            //・銘柄コード　@　@ 　@　@　@：注文単位テーブル．銘柄ID
            if(l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.PRODUCTCODE))
            {
                l_strReturn.append("PRODUCT_ID");
            }
            //・取引市場　@　@　@　@　@  ：注文単位テーブル．市場ID
            else if(l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.TRADE_MARKET))
            {
                l_strReturn.append("MARKET_ID");
            }
            //・取引区分            ：注文単位テーブル．注文種別
            else if(l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.TRADE_DIVISION))
            {
                l_strReturn.append("ORDER_TYPE");
            }
            //・注文時間　@　@　@　@　@　@：注文単位テーブル．受注日時
            else if(l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.ORDER_TIME))
            {
                l_strReturn.append("RECEIVED_DATE_TIME");
            }
            //・発注日 　@　@　@　@　@ ：注文単位テーブル．発注日
            else if (l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.BIZ_DATE)) 
            {
                l_strReturn.append("BIZ_DATE");
            }
            //・注文期限　@　@　@　@　@　@：注文単位テーブル．注文失効日付
            else if(l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.ORDER_EXPIRATION_DATE))
            {
                l_strReturn.append("EXPIRATION_DATE");
            }
            else
            {
                continue;
            }
            l_strReturn.append(" ");
            if(WEB3AscDescDef.ASC.equals(l_futuresOptionsSortKey[i].ascDesc))
            {
                l_strReturn.append("ASC");
            }
            else
            {
                l_strReturn.append("DESC");
            }
            if(i < l_futuresOptionsSortKey.length)
            {
                l_strReturn.append(" , ");
            }
        }
        l_strReturn.append("last_updated_timestamp");
        l_strReturn.append(" ");
        l_strReturn.append("ASC");
        log.exiting(STR_METHOD_NAME);
        return l_strReturn.toString();
    }

    /**
     * (getPR層取引区分)<BR>
     * 注文種別からレスポンスに編集するPR層用の取引区分のコードを判定し、返却する。 <BR>
     *<BR>
     * 戻り値のPR層用の取引区分コード： <BR>
     * 3：新規買建注文　@4：新規売建注文　@5：買建返済注文(売返済)　@6：売建返済注文(買返済) <BR>
     *<BR>
     *・パラメータ.注文種別＝"605"（OP新規買建注文）の場合、"3"(新規買建注文)を返す。<BR> 
     *<BR>
     *・パラメータ.注文種別＝"606"（OP新規売建注文）の場合、"4"(新規売建注文)を返す。<BR> 
     *<BR>
     *・パラメータ.注文種別＝"608"（OP買建返済注文）の場合、"5"(買建返済注文(売返済))を返す。 <BR>
     *<BR>
     *・パラメータ.注文種別＝"607"（OP売建返済注文）の場合、"6"(売建返済注文(買返済))を返す。<BR> 
     *<BR>
     *・パラメータ.注文種別が上記以外の場合は、例外をthrowする。<BR>
     * @@return String
     * @@roseuid 40A36C160227
     */

    protected String getPRLevelTradeTypeDiv(OrderTypeEnum l_orderTypeEnum) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPRLevelTradeTypeDiv(OrderTypeEnum l_orderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        String l_prTradeTypeDiv = "";
        if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderTypeEnum))
        {
            l_prTradeTypeDiv = WEB3IfoTradeTypeDef.OPEN_LONG_CONTRACT;
        }
        else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderTypeEnum))
        {
            l_prTradeTypeDiv = WEB3IfoTradeTypeDef.OPEN_SHORT_CONTRACT;
        }
        else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderTypeEnum))
        {
            l_prTradeTypeDiv = WEB3IfoTradeTypeDef.CLOSE_SELL_CONTRACT;
        }
        else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderTypeEnum))
        {
            l_prTradeTypeDiv = WEB3IfoTradeTypeDef.CLOSE_BUY_CONTRACT;
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01439,
                STR_METHOD_NAME);            
        }
        log.exiting(STR_METHOD_NAME);
        return l_prTradeTypeDiv;
    }

    /**
     * (is指定約定状態)<BR>
     * 指定された約定状態に合致しているかどうかを判定し、<BR>
     * 合致している場合はtrueを、合致していない場合はfalseを、それぞれ返す。<BR>
     * <BR>
     * 先物OPデータアダプタ.get約定状態区分()をコールする。<BR>
     * <BR>
     * [引数]<BR>
     *   注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * 　@戻り値の約定状態区分＝パラメータ.約定状態区分の場合は、trueを返す。<BR>
     * 　@以外、falseを返す。<BR>
     * @@param l_strExecutedStatus - 約定状態区分。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return boolean
     * @@roseuid 40A36C160227
     */
    protected boolean isDesignateExecutedStatus(String l_strExecutedStatusDivision, IfoOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "isDesignateExecutedStatus(String, IfoOrderUnit)";

        log.entering(STR_METHOD_NAME);

        boolean l_bReturn = false; //返却値

        if (l_strExecutedStatusDivision == null)
        {
            //引数.約定状態区分＝nullの場合
            l_bReturn = true;
        }
        else
        {
            //引数.約定状態区分≠nullの場合
            String l_execType = WEB3IfoDataAdapter.getExecStatusType(l_orderUnit);
            l_bReturn = l_execType.equals(l_strExecutedStatusDivision);
        }

        log.exiting(STR_METHOD_NAME);

        return l_bReturn;

    }
    
    /**
     * (get出来るまで注文単位FromFirstToLast)<BR>
     *指定された「夕場まで注文」や「出来るまで注文」の注文単位オブジェクトに対する、<BR>
     *原注文〜最新の注文までの注文単位オブジェクトの一覧を取得する。<BR>
     *<BR>
     *(1)パラメータ.注文単位.初回注文の注文単位ID == 0　@<BR>
     *|| パラメータ.注文単位.初回注文の注文単位ID == null）の場合<BR>
     *　@（原注文で未繰越状態の場合）<BR>
     *<BR>
     *　@パラメータ.注文単位を配列にセットして返却する。<BR>
     *<BR>
     *(2)原注文〜最新の注文までの注文単位オブジェクトを下記抽出条件にて取得する。 <BR>
     *<BR>
     *　@　@　@＜抽出条件＞<BR>
     *　@　@      初回注文の注文単位ID == パラメータ.注文単位.初回注文の注文単位ID　@<BR>
     *　@　@　@　@　@または、注文単位ID == パラメータ.注文単位.初回注文の注文単位ID(*) <BR>
     *<BR>
     *　@　@　@    (*)原注文.初回注文の注文単位IDには、0がセットされている為。 <BR>
     *<BR>
     *(3)取得した注文単位オブジェクトを作成日時順に昇順でソートし、配列にして返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return IfoOrderUnit[]
     * @@roseuid 40A45DF500AB
     */
    protected IfoOrderUnit[] getCarriedOrderUnitFromFirstToLast(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getCarriedOrderUnitFromFirstToLast(IfoOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        // 原注文〜最新の注文までの注文単位オブジェクトを取得する。
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        List l_lisRecords = null;
        IfoOrderUnit[] l_orderUnits = null;
        
        if (l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull()
            || l_ifoOrderUnitRow.getFirstOrderUnitId() == 0)
        {
            l_orderUnits = new IfoOrderUnit[1];
            l_orderUnits[0] = l_orderUnit;
            return l_orderUnits;
        }
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strWhere = "order_unit_id=? or first_order_unit_id=?";
            Object l_objWhere[] =
                new Object[] {
                    new Long(l_ifoOrderUnitRow.getFirstOrderUnitId()),
                    new Long(l_ifoOrderUnitRow.getFirstOrderUnitId())};
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    l_strWhere,
                    "created_timestamp asc",
                    null,
                    l_objWhere);
            l_orderUnits = new IfoOrderUnit[l_lisRecords.size()];

           for(int i = 0;i<l_lisRecords.size();i++)
           {
               IfoOrderUnitRow l_orderUnitRowNew = (IfoOrderUnitRow)l_lisRecords.get(i);
               l_orderUnits[i] = (IfoOrderUnit)opOrderManager.getOrderUnit(l_orderUnitRowNew.getOrderUnitId());
           }
        }
        catch(NotFoundException l_nfe)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME, l_nfe);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);            
        }
        catch (DataException l_de)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME, l_de);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_de.getMessage(),
                    l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnits;
    }

    /** 
     * (is訂正取消可能)<BR>
     * 訂正取消が可能であるかを判定する。<BR>
     * 訂正取消可能である場合trueを、以外、falseを返却する。<BR>
     * <BR>
     * シーケンス図「（OP注文約定照会サービス）is訂正取消可能」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@return boolean
     * @@throws WEB3BaseException
     */
    private boolean isChangeCancelEnable(WEB3GentradeSubAccount l_subAccount, IfoOrderUnit l_orderUnit)                              
        throws WEB3BaseException                             
    {                                
        final String STR_METHOD_NAME =                               
            "isChangeCancelEnable(WEB3GentradeSubAccount, IfoOrderUnit)";                                
                                
        log.entering(STR_METHOD_NAME);                               
                                
        IfoOrderUnitRow l_orderUnitRow =                             
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();                              
                                        
        //注文マネージャ.getOrder(注文単位.注文ID)                                
        Order l_order = null;                                
        try                              
        {                                
            l_order = opOrderManager.getOrder(l_orderUnit.getOrderId());                             
        }                                
        catch (NotFoundException l_nfE)                              
        {                                
            log.error(getClass().getName() + "." + STR_METHOD_NAME,l_nfE);                               
            throw new WEB3SystemLayerException(                              
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,                             
                STR_METHOD_NAME,                             
                l_nfE.getMessage(),                              
                l_nfE);                              
        }                                
                                
        //注文マネージャ.validate注文訂正可能状態(注文 : 注文)                              
        boolean l_blnOrderChange = true;                             
        try                              
        {                                
            opOrderManager.validateOrderChangePossibleStatus(l_order);                               
        }                                
        catch (WEB3BaseException l_wbe)                              
        {                                
            l_blnOrderChange = false;                                
        }                                
                                
        //注文マネージャ.validate注文取消可能状態(注文 : 注文)                              
        boolean l_blnOrderCancel = true;                             
        try                              
        {                                
            opOrderManager.validateOrderCancelPossibleStatus(l_order);                               
        }                                
        catch (WEB3BaseException l_wbe)                              
        {                                
            l_blnOrderCancel = false;                                
        }                                
                                
        //�@@注文単位.注文状態が訂正取消可能な状態であること                              
        if (l_blnOrderChange == false && l_blnOrderCancel == false)                              
        {                                
            log.debug("訂正取消可能チェック：注文訂正取消不可");                                
            log.exiting(STR_METHOD_NAME);                                
            return false;                                
        }                                

        //発注日
        Date l_datBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");

        //validate閉局後訂正取消受付可能
        // [引数]
        //  銘柄タイプ：　@”先物オプション” 
        //  先物／オプション区分：　@”オプション” 
        //  部店：　@補助口座.get取扱店() 
        //  立会区分：　@注文単位.立会区分 
        //  発注日：　@注文単位.発注日
        try
        {
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                ProductTypeEnum.IFO, 
                WEB3FuturesOptionDivDef.OPTION,
                (WEB3GentradeBranch)l_subAccount.getWeb3GenBranch(),
                l_orderUnitRow.getSessionType(),
                l_datBizDate);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.debug("訂正取消可能チェック：市場閉局後 訂正取消不可時間");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
                     
        //�B取引停止顧客ではないこと（Ｙ客、考査ロック、支店ロック、管理ロックのチェック）                                
        WEB3GentradeOrderValidator l_gentradeOrderValidator = null;                              
        l_gentradeOrderValidator = (WEB3GentradeOrderValidator)this.finApp.getCommonOrderValidator();                                
                                        
        OrderValidationResult l_validationResult =                               
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);                             
                                
        if(l_validationResult.getProcessingResult().isFailedResult())                                
        {                                
            log.debug("訂正取消可能チェック：取引不可顧客");                              
            log.exiting(STR_METHOD_NAME);                                
            return false;                                
        }                                
                                
        //全チェックOKの場合                             
        log.exiting(STR_METHOD_NAME);                                
        return true;                             
    }
}@
