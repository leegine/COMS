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
filename	WEB3MarginCloseMarginListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引決済一覧サービスImpl(WEB3MarginCloseMarginListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/28 呉艶飛 (中訊) 新規作成              
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginAccountTypeComparator;
import webbroker3.equity.WEB3MarginAppraisalProfitOrLossComparator;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginCloseStatus;
import webbroker3.equity.WEB3MarginCloseStatusTypeComparator;
import webbroker3.equity.WEB3MarginContractInfo;
import webbroker3.equity.WEB3MarginContractTypeComparator;
import webbroker3.equity.WEB3MarginContractUnitOpenDateComparator;
import webbroker3.equity.WEB3MarginMarketCodeComparator;
import webbroker3.equity.WEB3MarginOpenDateComparator;
import webbroker3.equity.WEB3MarginProductCodeComparator;
import webbroker3.equity.WEB3MarginRepaymentNumComparator;
import webbroker3.equity.WEB3MarginRepaymentTypeComparator;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3MarginContractTypeDef;
import webbroker3.equity.message.WEB3MarginCloseMarginGroup;
import webbroker3.equity.message.WEB3MarginCloseMarginListRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginListResponse;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginIndividualCloseMarginListRequest;
import webbroker3.equity.message.WEB3MarginIndividualCloseMarginListResponse;
//import webbroker3.equity.message.WEB3MarginProductCodeNameUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.message.WEB3MarginSortKey;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginListService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3PageIndexInfo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

/**
 * （信用取引決済一覧サービスImpl）。<BR>
 * <BR>
 * 信用取引決済一覧サービス実装クラス。
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3MarginCloseMarginListServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginCloseMarginListService
{

    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginCloseMarginListServiceImpl.class);

    /**
     * (execute)。<BR>
     * <BR>
     * 信用取引決済一覧サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * get決済一覧()または、get個別決済一覧()メソッドのいずれかをコールする。<BR>
     * <BR>
     * @@param l_request - リクエスト
     * @@return message.WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407CB53F025D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request == null)
        {
            log.error(STR_METHOD_NAME + "パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        if (l_request instanceof WEB3MarginCloseMarginListRequest) //validate注文
        {
            l_response = getCloseMarginList((WEB3MarginCloseMarginListRequest) l_request);
        }
        else if (l_request instanceof WEB3MarginIndividualCloseMarginListRequest)
        {
            l_response = getIndividualCloseMarginList((WEB3MarginIndividualCloseMarginListRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get決済一覧)。<BR>
     * <BR>
     * 信用取引の決済一覧表示サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引決済一覧サービス）get決済一覧」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     *   シーケンス図(「（信用取引決済一覧サービス)get決済一覧」) : <BR>   
     *   (6*)getProduct(証券会社 : Institution, 銘柄コード : 論理ビュー::java::lang::String)<BR>   
     *   (銘柄コードチェック)<BR>
     *   getProduct( )で株式銘柄が取得できなかった場合は、「銘柄コードエラー」の例外をthrowし、処理を終了する。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00301<BR>
     *   ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3MarginCloseMarginListResponse
     * @@roseuid 40E402970206
     */
    protected WEB3MarginCloseMarginListResponse getCloseMarginList(WEB3MarginCloseMarginListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCloseMarginList(WEB3MarginCloseMarginListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //注文受付可能かの判定を行う。
        boolean l_blnIsCloseMarginOrderAcceptPossible = true;
        boolean l_blnIsSwapMarginOrderAcceptPossible = true;
        WEB3BaseException l_wbeReturn = null;
        
        //返済注文受付可能チェック
        try
        {
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            l_wbeReturn = l_wbe;
            l_blnIsCloseMarginOrderAcceptPossible = false;
        }
        
        //現引現渡注文受付可能チェック
        try
        {
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.SWAP_MARGIN);
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsSwapMarginOrderAcceptPossible = false;
        }
        
        //返済注文、現引現渡注文共に受付不可の場合はエラーを投げる
        if ( !l_blnIsCloseMarginOrderAcceptPossible && !l_blnIsSwapMarginOrderAcceptPossible)
        {
            throw new WEB3BusinessLayerException(
                l_wbeReturn.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //get補助口座
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            AccountManager l_accountManager = l_finApp.getAccountManager();

            Institution l_institution = l_accountManager.getInstitution(l_subAccount.getInstitution().getInstitutionCode());
            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();

            WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

            WEB3GentradeFinObjectManager l_finTransactionManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            WEB3EquityProduct l_product = null;

            //(分岐フロー)銘柄コード指定時(リクエストデータ.銘柄コード != null)のみ、下記処理を実施する。
            if (l_request.productCode != null)
            {
                try
                {
                    //株式銘柄を取得する。
                    l_product = (WEB3EquityProduct) l_productManager.getProduct(l_institution, l_request.productCode);
                }
                catch (NotFoundException l_nfex)
                {
                    log.error("銘柄コードチェック)getProduct( )で株式銘柄が取得できなかった場合は、。", l_nfex);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, STR_METHOD_NAME);
                }
                //(銘柄コードチェック)getProduct( )で株式銘柄が取得できなかった場合は、「銘柄コードエラー」の例外をthrowし、処理を終了する。
                if (l_product == null)
                {
                    log.error("銘柄コードチェック)getProduct( )で株式銘柄が取得できなかった場合は、。");
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, STR_METHOD_NAME);
                }
            }

            WEB3MarginCloseMarginListResponse l_response = (WEB3MarginCloseMarginListResponse)l_request.createResponse();

            //get信用現引可能額
            double l_dblSwapLongTradingPower = 0.0;
            WEB3TPTradingPowerService l_tradingPowerService
                = (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
            if (l_request.succFlag == false)
            {
                l_dblSwapLongTradingPower
                    = l_tradingPowerService.getActualReceiptTradingPower(l_subAccount);
            }
            else
            {
                l_dblSwapLongTradingPower
                    = l_tradingPowerService.getSuccActualReceiptTradingPower(l_subAccount, null);
            }

            //※顧客オブジェクトは補助口座.getMainAccount( )にて取得する。
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accountManager.getMainAccount(l_subAccount.getAccountId());

            //弁済区分：　@”制度信用”
            boolean l_isMarginSys = l_mainAccount.isMarginAccountEstablished(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            //弁済区分：　@”一般信用”
            boolean l_isMarginGen = l_mainAccount.isMarginAccountEstablished(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
            String l_strMarginTradingDiv = null;
            if (l_isMarginSys && l_isMarginGen)
            {
                l_strMarginTradingDiv = WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN;
            }
            else if (l_isMarginSys && !l_isMarginGen)
            {
                l_strMarginTradingDiv = WEB3MarginTradingDivDef.MARKET_MARGIN;
            }
            else if (!l_isMarginSys && l_isMarginGen)
            {
                l_strMarginTradingDiv = WEB3MarginTradingDivDef.INSTITUTION_MARGIN;
            }
            //閉局間近の市場コードを取得する。
			WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            String[] l_strCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY, l_strMarginTradingDiv);

            //建株一覧を取得する。
            List l_lstContract = l_positionManager.getContracts(l_subAccount, ProductTypeEnum.EQUITY, null, "product_id asc", null);

            //(該当データチェック)get建株一覧( )の戻り値がnull(＝該当データなし)の場合は、下記プロパティセットを行い、戻り値を返却
            //信用取引決済一覧レスポンスに以下の通りプロパティをセットする。
            if (l_lstContract == null || l_lstContract.size() == 0)
            {
                //銘柄一覧： null
                l_response.productCodeNames = null;
                //市場コード一覧： null
                l_response.marketList = null;
                //決済一覧：　@null
                l_response.closeMarginGroups = null;
                //総ページ数： 0
                l_response.totalPages = "0";
                //総レコード数： 0
                l_response.totalRecords = "0";
                //表示ページ番号： 0
                l_response.pageIndex = "0";
                //現引可能額：　@現引可能額取得結果
                l_response.swapLongTradingPower = WEB3StringTypeUtility.formatNumber(l_dblSwapLongTradingPower);
                //取引終了警告市場コード一覧：　@取引時間管理.get市場閉局警告市場( )の戻り値
                l_response.messageSuspension = l_strCloseMarket;

                //プロパティセットした信用取引建株照会レスポンスを返却し、処理を終了する。
                return l_response;
            }
            //市場コード一覧を格納する市場コード一覧HashMapを生成する。
            Map l_mapMarket = new TreeMap();

            int l_intContract = l_lstContract.size();

            //建株の要素数分Loop処理
            for (int i = 0; i < l_intContract; i++)
            {
                //決済状態を取得する。
                EqtypeContractRow l_row = (EqtypeContractRow) l_lstContract.get(i);
                WEB3EquityContract l_contract = new WEB3EquityContract(l_row);

                //※get決済状態( )の戻り値の信用取引決済状態が下記状態の場合（表示対象外）、
                //以降のLoop内処理は行わない（continue;)
                WEB3MarginCloseStatus l_closeStatus = l_positionManager.getMarginCloseStatus(l_contract);
                if (l_closeStatus.closedMarginFlag == false && l_closeStatus.closeMarginFlag == false && l_closeStatus.closingMarginFlag == false)
                {
                    continue;
                }
                
                //市場IDを取得する。
                long l_lngMarketId = l_row.getMarketId();
                //市場オブジェクトを取得する。
                Market l_market = l_finTransactionManager.getMarket(l_lngMarketId);
                //市場コードを取得する。
                String l_strMarketCode = l_market.getMarketCode();

                try
                {
                    //validate市場コード（市場コード, 証券会社コード）
                    WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
                    l_orderManager.validateMarket(l_strMarketCode, l_institution.getInstitutionCode());
                    
                    //validate市場コードで例外がthrowされなかった場合のみ
                    //市場コード一覧HashMapに市場コードを追加する。
                    l_mapMarket.put((new Long(l_lngMarketId)), l_strMarketCode);
                }
                catch (WEB3BaseException l_wbe)
                {
                    //例外がthrowされた場合は追加しない。
                }
            }

            String[] l_strMarketCode = new String[l_mapMarket.size()];
            Collection l_cllMarket = new ArrayList();
            l_cllMarket = l_mapMarket.values();
            l_cllMarket.toArray(l_strMarketCode);

            //信用取引決済一覧レスポンスに以下の通りプロパティをセットする。
            //l_response.productCodeNames = l_productCodeNames;
            l_response.marketList = l_strMarketCode;

            //create建株情報一覧の引数を作成する。
            String l_strSearchCond = this.createSearchCondCharacter(l_request.productCode, l_request.marketCode);
            String[] l_strSearchData = this.createSearchCondDataContainers(l_request.productCode, l_request.marketCode);
            boolean l_blnOIsProduct = l_request.productCode != null ? true : false;
            
            WEB3MarginContractInfo[] l_contractInfo = this.createContractInfoList(
                l_subAccount, l_strSearchCond, l_strSearchData, l_blnOIsProduct);

            if (l_contractInfo == null)
            {
                //決済一覧：　@null
                l_response.closeMarginGroups = null;
                //総ページ数： 0
                l_response.totalPages = "0";
                //総レコード数： 0
                l_response.totalRecords = "0";
                //表示ページ番号： 0
                l_response.pageIndex = "0";
                //現引可能額：　@現引可能額取得結果
                l_response.swapLongTradingPower = WEB3StringTypeUtility.formatNumber(l_dblSwapLongTradingPower);
                //取引終了警告市場コード一覧：　@取引時間管理.get市場閉局警告市場( )の戻り値
                l_response.messageSuspension = l_strCloseMarket;

                //プロパティセットした信用取引建株照会レスポンスを返却し、処理を終了する。
                return l_response;
            }
            //決済一覧行を作成するための一括条件による建株情報一覧のソートを行う。
            this.sortContractInfoListForColseMarginGroupList(l_contractInfo);

            //建株情報一覧から決済一覧を作成する。
            WEB3MarginCloseMarginGroup[] l_closeMarginGroup = this.createCloseMarginListFromContractInfoList(l_contractInfo);
            //決済一覧を指定されたソートキーに従ってソートする。
            this.sortCloseMarginList(l_closeMarginGroup, l_request.sortKeys);

            //要求ページ番号の決済一覧を作成する。
			int l_intPageSize = Integer.parseInt(l_request.pageSize); //ページ内表示行数
			int l_intPageIndex = Integer.parseInt(l_request.pageIndex); //要求ページ番号
			
			WEB3PageIndexInfo l_pageIndexInfo =
			new WEB3PageIndexInfo(l_closeMarginGroup, l_intPageIndex, l_intPageSize);
			
			l_response.closeMarginGroups
			= (WEB3MarginCloseMarginGroup[])l_pageIndexInfo.getArrayReturned(WEB3MarginCloseMarginGroup.class);
			
			l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
			l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
			l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());         
            
            //現引可能額：　@現引可能額取得結果
            l_response.swapLongTradingPower = WEB3StringTypeUtility.formatNumber(l_dblSwapLongTradingPower);
            //取引終了警告市場コード一覧：　@取引時間管理.get市場閉局警告市場( )の戻り値
            l_response.messageSuspension = l_strCloseMarket;

            log.exiting(STR_METHOD_NAME);
            return l_response;

        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません。", l_nfe);

            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
    }

    /**
     * (get個別決済一覧)。<BR>
     * <BR>
     * 信用取引の個別決済一覧表示サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引決済一覧サービス）get個別決済一覧」参照。<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3MarginIndividualCloseMarginListResponse
     * @@roseuid 40E403180285
     */
    protected WEB3MarginIndividualCloseMarginListResponse getIndividualCloseMarginList(WEB3MarginIndividualCloseMarginListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getIndividualCloseMarginList(WEB3MarginIndividualCloseMarginListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //リクエストデータの整合性をチェックする。
            l_request.validate();
            //建株ＩＤ：　@リクエストデータ.ＩＤ[0]
            long l_lngContractId = Long.parseLong(l_request.id[0]);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

            //建株オブジェクトを取得する。
            WEB3EquityContract l_contract = (WEB3EquityContract) l_positionManager.getContract(l_lngContractId);
            EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contract.getDataSourceObject();
            //市場コードをThreadLocalにセットし直す。
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(l_contract.getMarketId());
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());

            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();


            //注文受付可能かの判定を行う。
            boolean l_blnIsCloseMarginOrderAcceptPossible = true;
            boolean l_blnIsSwapMarginOrderAcceptPossible = true;
            WEB3BaseException l_wbeReturn = null;
        
            //返済注文受付可能チェック
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BaseException l_wbe)
            {
                l_wbeReturn = l_wbe;
                l_blnIsCloseMarginOrderAcceptPossible = false;
            }
        
            //現引現渡注文受付可能チェック
            try
            {
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.SWAP_MARGIN);
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BaseException l_wbe)
            {
                l_blnIsSwapMarginOrderAcceptPossible = false;
            }
        
            //返済注文、現引現渡注文共に受付不可の場合はエラーを投げる
            if ( !l_blnIsCloseMarginOrderAcceptPossible && !l_blnIsSwapMarginOrderAcceptPossible)
            {
                throw new WEB3BusinessLayerException(
                    l_wbeReturn.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //get補助口座
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
            //※顧客オブジェクトは補助口座.getMainAccount( )にて取得する。
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            //信用口座を開設しているかを判定する。
            l_mainAccount.isMarginAccountEstablished(l_contractRow.getRepaymentType());
            //取引銘柄オブジェクトを取得する。
            WEB3EquityTradedProduct l_product = null;
            try
            {
                l_product = (WEB3EquityTradedProduct) l_contract.getTradedProduct();
            }
            catch (RuntimeSystemException l_rse)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "建株ID=[" + Long.toString(l_contract.getContractId()) + "]の建株に紐付く取引銘柄無し",
                    l_rse);
            }
            
            //時価を取得する。
            double l_dblCurrentPrice = l_productManager.getCurrentPrice(l_product);
            //建株明細List（：ArrayList）を生成する。
            List l_lstContract = new ArrayList();
            //リクエストデータ.ＩＤ[]の要素毎のLOOP処理
            for (int i = 0; i < l_request.id.length; i++)
            {
                //建株オブジェクトを取得する。
                WEB3EquityContract l_equityContract = (WEB3EquityContract) l_positionManager.getContract(Long.parseLong(l_request.id[i]));

                //信用取引建株明細を生成する。
                WEB3MarginContractUnit l_contractUnit = new WEB3MarginContractUnit();
                //ＩＤ：　@getContractId()の戻り値
                l_contractUnit.id = "" + l_equityContract.getContractId();
                //建日：　@getOpenDate()の戻り値
                l_contractUnit.openDate = WEB3DateUtility.toDay(l_equityContract.getOpenDate());
                //建単価：　@getContractPrice()の戻り値
                l_contractUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_equityContract.getContractPrice());
                //建株数：　@（getQuantity() - getLockedQuantity()）の計算結果
                double l_dblQuantity = l_equityContract.getQuantity() - l_equityContract.getLockedQuantity();
                l_contractUnit.contractQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
                //建代金：　@get建代金()の戻り値
                l_contractUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_equityContract.getContractAmount(l_dblQuantity));
                //評価損益：　@get評価損益()の戻り値
                l_contractUnit.appraisalProfitLoss = WEB3StringTypeUtility.formatNumber(
                    l_equityContract.getAppraisalProfitOrLossExpenses(l_dblCurrentPrice, l_dblQuantity));
                //注文株数：　@null 内出来株数：　@null 決済順位：　@nul
                l_contractUnit.orderQuantity = null;
                l_contractUnit.partContQuantity = null;
                l_contractUnit.settlePriority = null;
                //建株明細List（：ArrayList）に信用取引建株明細オブジェクトを追加する。
                l_lstContract.add(l_contractUnit);
            }

            WEB3MarginContractUnit[] l_Unit = new WEB3MarginContractUnit[l_lstContract.size()];
            l_lstContract.toArray(l_Unit);
            WEB3MarginContractUnitOpenDateComparator[] l_Comparator = new WEB3MarginContractUnitOpenDateComparator[1];
            l_Comparator[0] = new WEB3MarginContractUnitOpenDateComparator(WEB3AscDescDef.ASC);
            //リクエストデータ.ソートキー[]の指定順に対応するComparatorを生成し、配列で指定する。
            //WEB3EquityKeyItemDef
            //建株明細の配列をソートする。
            WEB3ArraysUtility.sort(l_Unit, l_Comparator);
            
            //get信用現引可能額
            double l_dblSwapLongTradingPower = 0.0;
            WEB3TPTradingPowerService l_tradingPowerService
                = (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
            l_dblSwapLongTradingPower = l_tradingPowerService.getActualReceiptTradingPower(l_subAccount);
            
            //引数は以下の通り設定する。
            //閉局間近の市場を取得する。
			WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            String[] l_strCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY, l_contractRow.getRepaymentType());
            //
            WEB3MarginIndividualCloseMarginListResponse l_reponse = (WEB3MarginIndividualCloseMarginListResponse)l_request.createResponse();
            //EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow) l_product.getDataSourceObject();
            //銘柄コード：　@取引銘柄.getProduct().銘柄コード
            l_reponse.productCode = l_product.getProductCode();
            //銘柄名：　@取引銘柄.getProduct().銘柄名　@
            EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getProduct().getDataSourceObject();
            l_reponse.productName = l_productRow.getStandardName();
            //市場コード：　@取引銘柄.getMarket().市場コード
            l_reponse.marketCode = l_product.getMarketCode();
            //口座区分：　@
            if (TaxTypeEnum.NORMAL.equals(l_contractRow.getTaxType()))
            {
                l_reponse.taxType = WEB3TaxTypeDef.NORMAL;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_contractRow.getTaxType()) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_contractRow.getTaxType()))
            {
                l_reponse.taxType = WEB3TaxTypeDef.SPECIAL;
            }
            //建区分：　@
            if (l_contract.isLong() == true)
            {
                l_reponse.contractType = WEB3MarginContractTypeDef.OPEN_BUY;
            }
            else
            {
                l_reponse.contractType = WEB3MarginContractTypeDef.OPEN_SELL;
            }
            //弁済：
            //信用取引弁済.弁済区分：　@建株.弁済区分
            //信用取引弁済.弁済期限値：　@建株.弁済期限値
            WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
            l_repaymentUnit.repaymentDiv = l_contractRow.getRepaymentType();
            l_repaymentUnit.repaymentTimeLimit = "" + l_contractRow.getRepaymentNum();
            l_reponse.repayment = l_repaymentUnit;

            //現引可能額：　@calc信用現引可能額()の戻り値
            l_reponse.swapLongTradingPower = WEB3StringTypeUtility.formatNumber(l_dblSwapLongTradingPower);
            //現在値：get時価()の戻り値
            l_reponse.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
            //建株明細一覧：　@建株明細[]（建株明細List.toArray()の戻り値）
            l_reponse.contractUnits = l_Unit;
            //取引終了警告市場コード一覧：　@取引時間管理.get市場閉局警告市場( )の戻り値配列
            l_reponse.messageSuspension = l_strCloseMarket;

            log.exiting(STR_METHOD_NAME);
            return l_reponse;
        }
        catch (NotFoundException l_nfe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfe);

            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        catch (DataNetworkException l_dnw)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dnw);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_dnw.getMessage(), l_dnw);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqe);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }

    }

    /**
     * (create検索条件文字列)。<BR>
     * <BR>
     * リクエストデータをもとに、検索条件（where以下指定の文字列）を作成する。<BR>
     * <BR>
     * １）　@戻り値となる文字列のインスタンスを生成する。<BR>
     * <BR>
     * ２）　@引数.銘柄コード≠null（銘柄コード指定）の場合、<BR>
     * 銘柄ID指定を文字列インスタンスに追加する。<BR>
     * 　@　@（銘柄コードに対応する銘柄IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@" and product_id = ?"<BR>
     * <BR>
     * ３）　@引数.市場コード≠null（市場コード指定）の場合、<BR>
     * 市場ID指定を文字列インスタンスに追加する。<BR>
     * 　@　@（市場コードに対応する市場IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@" and market_id = ?"<BR>
     * <BR>
     * ４）　@文字列インスタンスを返却する。<BR>
     * （引数.銘柄コード、引数.市場コードがどちらもnullの場合、nullを返却する）<BR>
     * <BR>
     * @@param l_strProductCode - 銘柄コード
     * @@param l_strMarketCode - 市場コード
     * @@return String
     * @@roseuid 40F4B61A009A
     */
    protected String createSearchCondCharacter(String l_strProductCode, String l_strMarketCode)
    {
        final String STR_METHOD_NAME = " createSearchCondCharacter(String l_strProductCode, String l_strMarketCode)";
        log.entering(STR_METHOD_NAME);

        //１）　@戻り値となる文字列のインスタンスを生成する。
        StringBuffer l_strSearchCondCharacter = new StringBuffer();
        
        //l_strSearchCondCharacter = null;

        if (l_strProductCode == null && l_strMarketCode == null)
        {
            return null;
        }
        //引数.銘柄コード≠null（銘柄コード指定）の場合、銘柄ID指定を文字列インスタンスに追加する。
        if (l_strProductCode != null)
        {
            l_strSearchCondCharacter.append(" and product_id = ?");
        }
        //引数.市場コード≠null（市場コード指定）の場合、市場ID指定を文字列インスタンスに追加する。
        if (l_strMarketCode != null)
        {
            l_strSearchCondCharacter.append(" and market_id = ?");
        }

        //４）　@文字列インスタンスを返却する。
        return l_strSearchCondCharacter.toString();
    }

    /**
     * (create検索条件データコンテナ)。<BR>
     * <BR>
     * リクエストデータから、検索条件（where以下指定の文字列）の<BR>
     * パラメータリストを作成する。<BR>
     * <BR>
     * １）　@文字列配列を作成する。<BR>
     * <BR>
     * ２）　@引数.銘柄コード≠null（銘柄コード指定）の場合、銘柄IDを<BR>
     * 　@　@文字列配列にセットする。（銘柄コードに対応する銘柄IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@銘柄ID ＝ 拡張プロダクトマネージャ.get銘柄(証券会社オブジェクト(*1), <BR>
     * パラメータ.銘柄コード).銘柄ID<BR>
     * <BR>
     * ３）　@引数.市場コード≠null（市場コード指定）の場合、市場IDを<BR>
     * 　@　@文字列配列にセットする。（市場コードに対応する市場IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@市場ID ＝ 拡張金融オブジェクトマネージャ.getMarket<BR>
     * (証券会社オブジェクト(*1), 引数.市場コード).市場ID<BR>
     * <BR>
     * (*1)証券会社オブジェクトは、補助口座.getInstitution( )で取得し設定<BR>
     * <BR>
     * ４）　@文字列配列を返却する。<BR>
     * （引数.銘柄コード、引数.市場コードがどちらもnullの場合、nullを返却する）<BR>
     * <BR>
     * @@param l_strProductCode - 銘柄コード
     * @@param l_strMarketCode - 市場コード
     * @@return String[]
     * @@roseuid 40F4B61A009D
     */
    protected String[] createSearchCondDataContainers(String l_strProductCode, String l_strMarketCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createQueryContainer(String l_strProductCode)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.銘柄コード＝NULLの場合、NULLを返却する
        if (l_strProductCode == null && l_strMarketCode == null)
        {
            return null;
        }
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3GentradeFinObjectManager l_finTransactionManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            //補助口座を取得する。
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

            WEB3EquityProductManager l_productMgr = (WEB3EquityProductManager) l_tradingModule.getProductManager();
            WEB3EquityProduct l_product = null;
            if (l_strProductCode != null)
            {
                l_product = (WEB3EquityProduct) l_productMgr.getProduct(l_subAccount.getInstitution(), l_strProductCode);
            }
            WEB3GentradeMarket l_market = null;
            if (l_strMarketCode != null)
            {
                l_market = (WEB3GentradeMarket) l_finTransactionManager.getMarket(l_subAccount.getInstitution(), l_strMarketCode);
            }
            String[] l_strQueryContainer = null;
            if (l_strProductCode != null && l_strMarketCode == null)
            {
                l_strQueryContainer = new String[] { "" + l_product.getProductId()};
            }
            else if (l_strProductCode == null && l_strMarketCode != null)
            {
                l_strQueryContainer = new String[] { "" + l_market.getMarketId()};
            }
            else if (l_strProductCode != null && l_strMarketCode != null)
            {
                l_strQueryContainer = new String[] { "" + l_product.getProductId(), "" + l_market.getMarketId()};
            }

            log.exiting(STR_METHOD_NAME);

            return l_strQueryContainer;
        }
        catch (NotFoundException l_nfe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfe);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
    }

    /**
     * (create建株情報一覧)。<BR>
     * <BR>
     * 決済一覧画面に表示する明細の元となる建株情報の一覧を作成する。<BR>
     * <BR>
     * 以下のいずれかの決済状態に当てはまる建株情報を抽出する。<BR>
     * ・未決済<BR>
     * ・決済中<BR>
     * <BR>
     * ※該当データが存在しない場合にはnullを返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引決済一覧サービス）create建株情報一覧」参照。<BR>
     * <BR>
     * @@param l_subAccount - 補助口座
     * @@param l_strSearchCondCharacter - 検索条件 文字列
     * @@param l_strSearchCondDataContainers - (検索条件データコンテナ)<BR>
     * @@param l_blnIsProductDesignate - (is銘柄指定)<BR>
     * 銘柄コードが指定されている場合、true。以外、false。<BR>
     * @@return WEB3MarginContractInfo[]
     * @@roseuid 40F4B63800F8
     */
    protected WEB3MarginContractInfo[] createContractInfoList(
        WEB3GentradeSubAccount l_subAccount,
        String l_strSearchCondCharacter,
        String[] l_strSearchCondDataContainers,
        boolean l_blnIsProductDesignate)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " createQueryContainer(String l_strProductCode)";
        log.entering(STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
            WEB3GentradeFinObjectManager l_finTransactionManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            //建株の一覧を取得する。
            List l_lstContracts = l_positionManager.getContracts(l_subAccount, ProductTypeEnum.EQUITY, l_strSearchCondCharacter, l_strSearchCondDataContainers);
            if (l_lstContracts == null)
            {
				log.exiting(STR_METHOD_NAME);
                return null;
            }
            //建株情報を格納する建株情報リストを生成する。
            List l_lstContractInfo = new ArrayList();
            int l_intContractsLength = l_lstContracts.size();
            for (int i = 0; i < l_intContractsLength; i++)
            {
                EqtypeContractRow l_contractRow = (EqtypeContractRow) l_lstContracts.get(i);
                WEB3EquityContract l_contract = (WEB3EquityContract) l_positionManager.getContract(l_contractRow.getContractId());
                //市場コードを取得する。            
                WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_finTransactionManager.getMarket(l_contractRow.getMarketId());
                //市場コードを取得する。
                String l_strMarketCode = l_market.getMarketCode();
                //取引時間コンテキストに市場コードをセットする。
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
                //建株の決済状態を判定する。
                WEB3MarginCloseStatus l_closeStatus = l_positionManager.getMarginCloseStatus(l_contract);
				if (l_closeStatus.closeMarginFlag == false
					&& l_closeStatus.closingMarginFlag == false)										
                {
                	continue;
                }
                //get決済状態( )の戻り値の信用取引決済状態が下記状態(未決済)の場合のみ、処理を実施する。
                if (l_closeStatus.closeMarginFlag == true
                    && l_closeStatus.closedMarginFlag == false
                    && l_closeStatus.closingMarginFlag == false)
                {
                    //未決済の建株情報を作成する。
                    WEB3MarginContractInfo l_contractInfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);
                    //返済可能フラグ、現引現渡可能フラグの判定を行う。
                    this.setCloseMarginPossibleFlagToContractInfo(l_contractInfo, l_blnIsProductDesignate);
                    //作成した信用取引建株情報を建株情報リストに追加する。
					l_lstContractInfo.add(l_contractInfo);
                }
                //get決済状態( )の戻り値の信用取引決済状態が下記状態(決済中)の場合のみ、処理を実施する。
                else if (l_closeStatus.closeMarginFlag == false
                    && l_closeStatus.closedMarginFlag == false
                    && l_closeStatus.closingMarginFlag == true)
                {
                    //決済中の建株情報を作成する。
                    WEB3MarginContractInfo l_contractInfo = l_positionManager.createClosingMarginContractInfo(l_contract);
                    //作成した信用取引建株情報を建株情報リストに追加する。
                    l_lstContractInfo.add(l_contractInfo);
                }
                else
                {
                    this.createMultipleContractInfo(
                        l_lstContractInfo, l_contract,l_closeStatus, l_blnIsProductDesignate);
                }
            }
            if (l_lstContractInfo.size() == 0)
            {
				log.exiting(STR_METHOD_NAME);
            	return null;
            }
            else
            {
				WEB3MarginContractInfo[] l_marginContractInfo = new WEB3MarginContractInfo[l_lstContractInfo.size()];
				l_lstContractInfo.toArray(l_marginContractInfo);
				log.exiting(STR_METHOD_NAME);
				return l_marginContractInfo;
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfe);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
    }

    /**
     * (create複数建株情報)。<BR>
     * <BR>
     * 1つの建株で複数の建株情報を作成する場合の処理を行う。 <BR>
     * <BR>
     * 引数の決済状態にもとづき、  <BR>
     * 株式ポジションマネージャ.create未決済建株情報( ) <BR>
     * 株式ポジションマネージャ.create決済中建株情報( ) <BR>
     * メソッドのいずれかをコールする。  <BR>
     * <BR>
     * １）　@決済状態：下記の場合(未決済と決済中)、未決済と決済中の2明細を作成する。  <BR>
     * 　@引数.決済状態.決済済フラグ＝false  <BR>
     * 　@引数.決済状態.未決済フラグ＝true   <BR>
     * 　@引数.決済状態.決済中フラグ＝true <BR>
     * <BR>
     * １-１）　@株式ポジションマネージャ.create未決済建株情報(引数.建株)  <BR>
     * １-２）　@this.set決済可能フラグto建株情報(１?１の戻り値の信用取引建株情報) <BR>
     * １-３）　@引数.建株情報リスト.add(１?２でプロパティセットした信用取引建株情報) <BR>
     * １-４）　@株式ポジションマネージャ.create決済中建株情報(引数.建株)  <BR>
     * １-５）　@引数.建株情報リスト.add(１?４の戻り値の信用取引建株情報) <BR>
     * <BR>
     * ２）　@決済状態：下記の場合(決済済と未決済)、未決済の1明細を作成する。 <BR>
     * 　@　@　@(決済済の明細は必要なし)  <BR>
     * 　@引数.決済状態.決済済フラグ＝true <BR>
     * 　@引数.決済状態.未決済フラグ＝true   <BR>
     * 　@引数.決済状態.決済中フラグ＝false <BR>
     * <BR>
     * ２-１）　@株式ポジションマネージャ.create未決済建株情報(引数.建株)  <BR>
     * ２-２）　@this.set決済可能フラグto建株情報(２?１の戻り値の信用取引建株情報) <BR>
     * ２-３）　@引数.建株情報リスト.add(２?２でプロパティセットした信用取引建株情報) <BR>
     * <BR>
     * ３）　@決済状態：下記の場合(決済済と決済中)、決済中の1明細を作成する。 <BR>
     * 　@　@　@(決済済の明細は必要なし)  <BR>
     * 　@引数.決済状態.決済済フラグ＝true <BR>
     * 　@引数.決済状態.未決済フラグ＝false   <BR>
     * 　@引数.決済状態.決済中フラグ＝true <BR>
     * <BR>
     * ３-１）　@株式ポジションマネージャ.create決済中建株情報(引数.建株)  <BR>
     * ３-２）　@引数.建株情報リスト.add(３?１の戻り値の信用取引建株情報) <BR>
     * <BR>
     * ４）　@決済状態：下記の場合(決済済と未決済と決済中)、未決済と決済中の2明細を作成する。 <BR>
     * 　@　@　@(決済済の明細は必要なし)  <BR>
     * 　@引数.決済状態.決済済フラグ＝true <BR>
     * 　@引数.決済状態.未決済フラグ＝true   <BR>
     * 　@引数.決済状態.決済中フラグ＝true <BR>
     * <BR>
     * ４-１）　@株式ポジションマネージャ.create未決済建株情報(引数.建株)  <BR>
     * ４-２）　@this.set決済可能フラグto建株情報(４?２の戻り値の信用取引建株情報) <BR>
     * ４-３）　@引数.建株情報リスト.add(４?２でプロパティセットした信用取引建株情報) <BR>
     * ４-４）　@株式ポジションマネージャ.create決済中建株情報(引数.建株)  <BR>
     * ４-５）　@引数.建株情報リスト.add(４?４の戻り値の信用取引建株情報) <BR>
     * <BR>
     * @@param l_lisContractInfoList - (建株情報リスト)<BR>
     * 作成した建株情報を格納するリスト
     * @@param l_contract - (建株)<BR>
     * 建株情報を作成する対象の建株
     * @@param l_closeMarginStatus - (決済状態)<BR>
     * 信用取引決済状態<BR>
     * @@param l_blnIsProductDesignate - is銘柄指定<BR>
     * 銘柄指定有りの場合、true。以外、false。<BR>
     * @@roseuid 40F4B63E0221
     */
    protected void createMultipleContractInfo(
        List l_lisContractInfoList,
        WEB3EquityContract l_contract,
        WEB3MarginCloseStatus l_closeMarginStatus,
        boolean l_blnIsProductDesignate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMultipleContractInfo";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        WEB3MarginContractInfo l_contractInfo = null;
        //１）　@決済状態：下記の場合(未決済と決済中)、未決済と決済中の 2明細を作成する。
        if (l_closeMarginStatus.closedMarginFlag == false
            && l_closeMarginStatus.closeMarginFlag == true
            && l_closeMarginStatus.closingMarginFlag == true)
        {
            //１－１）　@株式ポジションマネージャ.create未決済建株情報(引数.建株)
            l_contractInfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);

            //１－２）　@this.set決済可能フラグto建株情報(
            this.setCloseMarginPossibleFlagToContractInfo(l_contractInfo, l_blnIsProductDesignate);
            //１－３）　@引数.建株情報リスト.add(１－２でプロパティセットした信用取引建株情報)
            l_lisContractInfoList.add(l_contractInfo);
            //１－４）　@株式ポジションマネージャ.create決済中建株情報(引数.建株) 
            //１－５）　@引数.建株情報リスト.add(１－４の戻り値の信用取引建株情報)
            l_lisContractInfoList.add(l_positionManager.createClosingMarginContractInfo(l_contract));
        }

        //２）　@決済状態：下記の場合(決済済と未決済)、未決済の1明細を作成する。
        if (l_closeMarginStatus.closedMarginFlag == true && l_closeMarginStatus.closeMarginFlag == true && l_closeMarginStatus.closingMarginFlag == false)
        {
            //２－１）　@株式ポジションマネージャ.create未決済建株情報(引数.建株) 
            l_contractInfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);
            // ２－２）　@this.set決済可能フラグto建株情報
            this.setCloseMarginPossibleFlagToContractInfo(l_contractInfo, l_blnIsProductDesignate);
            //２－３）　@引数.建株情報リスト.add(２－２でプロパティセットした信用取引建株情報)
            l_lisContractInfoList.add(l_contractInfo);
        }

        //３）　@決済状態：下記の場合(決済済と決済中)、決済中の1明細を作成する。
        if (l_closeMarginStatus.closedMarginFlag == true && l_closeMarginStatus.closeMarginFlag == false && l_closeMarginStatus.closingMarginFlag == true)
        {
            // ３－１）　@株式ポジションマネージャ.create決済中建株情報(引数.建株)             
            //３－２）　@引数.建株情報リスト.add(３－１の戻り値の信用取引建株情報)
            l_lisContractInfoList.add(l_positionManager.createClosingMarginContractInfo(l_contract));
        }

        if (l_closeMarginStatus.closedMarginFlag == true && l_closeMarginStatus.closeMarginFlag == true && l_closeMarginStatus.closingMarginFlag == true)
        {
            //４－１）　@株式ポジションマネージャ.create未決済建株情報(引数.建株) 
            l_contractInfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);
            //４－２）　@this.set決済可能フラグto建株情報(４－２の戻り値の信用取引建株情報、
            //引数.is銘柄市場指定)
            this.setCloseMarginPossibleFlagToContractInfo(l_contractInfo, l_blnIsProductDesignate);
            // ４－３）　@引数.建株情報リスト.add(４－２でプロパティセットした信用取引建株情報)
            l_lisContractInfoList.add(l_contractInfo);

            // ４－４）　@株式ポジションマネージャ.create決済中建株情報(引数.建株) 
            // ４－５）　@引数.建株情報リスト.add(４－４の戻り値の信用取引建株情報)
            l_lisContractInfoList.add(l_positionManager.createClosingMarginContractInfo(l_contract));
        }
    }

    /**
     * (create決済一覧from建株情報一覧)。<BR>
     * <BR>
     * 建株情報の一覧から信用取引決済一覧行の一覧を作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引決済一覧サービス）create決済一覧from建株情報一覧」参照。<BR>
     * <BR>
     * @@param l_contractInfoList - (建株情報一覧)<BR>
     * 信用取引建株情報の配列
     * @@return WEB3MarginCloseMarginGroup[]
     * @@roseuid 40F4CBDA00E8
     */
    protected WEB3MarginCloseMarginGroup[] createCloseMarginListFromContractInfoList(WEB3MarginContractInfo[] l_contractInfoList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createCloseMarginListFromContractInfoList";
        log.entering(STR_METHOD_NAME);

        //建株明細を格納する建株明細リストを生成する。
        List l_lstContractUnits = new ArrayList();
        //決済一覧行を格納する決済一覧行リストを生成する。
        List l_closeMarginGroups = new ArrayList();
        //create決済一覧行(信用取引建株情報)
        WEB3MarginContractUnit[] l_contractUnit = null;
        WEB3MarginCloseMarginGroup l_marginGroup = this.createCloseMarginGroup(l_contractInfoList[0]);

        for (int i = 0; i < l_contractInfoList.length; i++)
        {
            if (!l_marginGroup.productCode.equals(l_contractInfoList[i].productCode)
                || (l_marginGroup.productCode.equals(l_contractInfoList[i].productCode) && !l_marginGroup.taxType.equals(l_contractInfoList[i].accountType))
                || (l_marginGroup.productCode.equals(l_contractInfoList[i].productCode)
                    && l_marginGroup.taxType.equals(l_contractInfoList[i].accountType)
                    && !l_marginGroup.marketCode.equals(l_contractInfoList[i].marketCode))
                || (l_marginGroup.productCode.equals(l_contractInfoList[i].productCode)
                    && l_marginGroup.taxType.equals(l_contractInfoList[i].accountType)
                    && l_marginGroup.marketCode.equals(l_contractInfoList[i].marketCode)
                    && !l_marginGroup.contractType.equals(l_contractInfoList[i].contractType))
                || (l_marginGroup.productCode.equals(l_contractInfoList[i].productCode)
                    && l_marginGroup.taxType.equals(l_contractInfoList[i].accountType)
                    && l_marginGroup.marketCode.equals(l_contractInfoList[i].marketCode)
                    && l_marginGroup.contractType.equals(l_contractInfoList[i].contractType)
                    && !l_marginGroup.repayment.repaymentDiv.equals(l_contractInfoList[i].repaymentType))
				|| (l_marginGroup.productCode.equals(l_contractInfoList[i].productCode)
					&& l_marginGroup.taxType.equals(l_contractInfoList[i].accountType)
					&& l_marginGroup.marketCode.equals(l_contractInfoList[i].marketCode)
					&& l_marginGroup.contractType.equals(l_contractInfoList[i].contractType)
					&& l_marginGroup.repayment.repaymentDiv.equals(l_contractInfoList[i].repaymentType)
                    && !l_marginGroup.repayment.repaymentTimeLimit.equals(l_contractInfoList[i].repaymentNum))
                || (l_marginGroup.productCode.equals(l_contractInfoList[i].productCode)
                    && l_marginGroup.taxType.equals(l_contractInfoList[i].accountType)
                    && l_marginGroup.marketCode.equals(l_contractInfoList[i].marketCode)
                    && l_marginGroup.contractType.equals(l_contractInfoList[i].contractType)
                    && l_marginGroup.repayment.repaymentDiv.equals(l_contractInfoList[i].repaymentType)
					&& l_marginGroup.repayment.repaymentTimeLimit.equals(l_contractInfoList[i].repaymentNum)
                    && !l_marginGroup.settlementState.equals(l_contractInfoList[i].closingStatusType)))
            {
                //建株明細の配列を取得する。
                l_contractUnit = new WEB3MarginContractUnit[l_lstContractUnits.size()];
                l_lstContractUnits.toArray(l_contractUnit);
                //指定の決済一覧行の建株明細のマージ処理とプロパティセットを行う。
                this.setCloseMarginGroup(l_marginGroup, l_contractUnit);
                //決済一覧行リストに基準決済一覧行を追加する。
                l_closeMarginGroups.add(l_marginGroup);

                //新たに作成した返済一覧行を基準返済一覧行としてセットする
                l_marginGroup = this.createCloseMarginGroup(l_contractInfoList[i]);
                l_lstContractUnits = new ArrayList();
            }
            //指定の建玉照会明細から建玉明細を作成する。
            WEB3MarginContractUnit l_marginContractUnit = this.createContractUnit(l_contractInfoList[i]);
            l_lstContractUnits.add(l_marginContractUnit);
        }

        //建株明細の配列を取得する。
        WEB3MarginContractUnit[] l_marginContractUnit1 = new WEB3MarginContractUnit[l_lstContractUnits.size()];
        l_lstContractUnits.toArray(l_marginContractUnit1);
        //指定の決済一覧行の建株明細のマージ処理とプロパティセットを行う。
        this.setCloseMarginGroup(l_marginGroup, l_marginContractUnit1);
        l_closeMarginGroups.add(l_marginGroup);
        WEB3MarginCloseMarginGroup[] l_marginGroups = new WEB3MarginCloseMarginGroup[l_closeMarginGroups.size()];
        l_closeMarginGroups.toArray(l_marginGroups);

        log.exiting(STR_METHOD_NAME);
        return l_marginGroups;
    }

    /**
     * (create決済一覧行)。<BR>
     * <BR>
     * 指定の建株情報から決済一覧行を作成する。<BR>
     * <BR>
     * １）　@信用取引決済一覧行の生成。<BR>
     * 　@信用取引決済一覧行オブジェクトを生成する。<BR>
     * <BR>
     * ２）　@信用取引弁済の生成とプロパティセット。<BR>
     * 　@信用取引弁済オブジェクトを生成する。<BR>
     * 　@信用取引弁済.弁済区分 = 引数.建株情報.弁済区分<BR>
     * 　@信用取引弁済.弁済期限値 = 引数.建株情報.弁済期限値<BR>
     * <BR>
     * ３）　@時価の取得。<BR>
     * 　@建株 = 株式ポジションマネージャ.getContract(引数.建株情報.ID)<BR>
     * 　@時価 = 株式ポジションマネージャ.get建株時価(建株)<BR>
     * <BR>
     * ４）　@プロパティのセット。<BR>
     * 　@１）で生成した決済一覧行オブジェクトに以下のプロパティセットを行う。<BR>
     * <BR>
     * 　@決済一覧行.銘柄コード = 引数.建株情報.銘柄コード<BR>
     * 　@決済一覧行.銘柄名 = 引数.建株情報.銘柄名<BR>
     * 　@決済一覧行.市場コード = 引数.建株情報.市場コード<BR>
     * 　@決済一覧行.口座区分 = 引数.建株情報.口座区分<BR>
     * 　@決済一覧行.建区分 = 引数.建株情報.建区分<BR>
     * 　@決済一覧行.弁済 =　@２）にてプロパティセットした信用取引弁済 <BR>
     * 　@決済一覧行.現在値 = ３）で取得した時価<BR>
     * 　@決済一覧行.決済状態区分 = 引数.建株情報.決済状態区分<BR>
     * 　@決済一覧行.返済可能フラグ = 引数.建株情報.返済可能フラグ<BR>
     * 　@決済一覧行.現引現渡可能フラグ = 引数.建株情報.現引現渡可能フラグ<BR>
     * <BR>
     * 　@※以下のプロパティについては設定を行わない<BR>
     * 　@決済一覧行.建株数<BR>
     * 　@決済一覧行.平均建単価<BR>
     * 　@決済一覧行.評価損益<BR>
     * 　@決済一覧行.建株明細一覧<BR>
     * <BR>
     * ４）　@３）でプロパティをセットした決済一覧行オブジェクトを返却する。<BR>
     * <BR>
     * @@param l_contractInfo - (建株情報)<BR>
     * 信用取引建株情報
     * @@return WEB3MarginCloseMarginGroup
     * @@roseuid 40F4D9220038
     */
    protected WEB3MarginCloseMarginGroup createCloseMarginGroup(WEB3MarginContractInfo l_contractInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createSettleContractListLine(WEB3OptionsContractReferenceUnit l_contractReferenceUnit)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginGroup l_closeMarginGroup = new WEB3MarginCloseMarginGroup();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        //時価の取得
        try
        {
            WEB3EquityContract l_contract = (WEB3EquityContract) l_positionManager.getContract(Long.parseLong(l_contractInfo.id)); //throw NotFoundException

            double l_dblContractCurrentPrice;
            try {
                l_dblContractCurrentPrice = l_positionManager.getContractCurrentPrice(l_contract);
            }
            catch (WEB3BaseException l_be) {
                l_dblContractCurrentPrice = 0D;
            }
            if (Double.isNaN(l_dblContractCurrentPrice))
            {
                l_dblContractCurrentPrice = 0D;
            }
            WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
            l_repaymentUnit.repaymentDiv = l_contractInfo.repaymentType; //弁済区分
            l_repaymentUnit.repaymentTimeLimit = l_contractInfo.repaymentNum; //弁済期限値
            l_closeMarginGroup.productCode = l_contractInfo.productCode; //銘柄コード
            l_closeMarginGroup.productName = l_contractInfo.standardName; //銘柄名
            l_closeMarginGroup.marketCode = l_contractInfo.marketCode; //市場コード
            l_closeMarginGroup.taxType = l_contractInfo.accountType; //口座区分
            l_closeMarginGroup.contractType = l_contractInfo.contractType; //建区分
            l_closeMarginGroup.repayment = l_repaymentUnit; //信用取引弁済
            if (l_dblContractCurrentPrice == 0D)
            {
				l_closeMarginGroup.currentPrice = null;
            }
            else
            {
				l_closeMarginGroup.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblContractCurrentPrice); //取得した時価
            }            
            l_closeMarginGroup.settlementState = l_contractInfo.closingStatusType; //決済状態区分
            l_closeMarginGroup.closeMarginFlag = l_contractInfo.closingPossibleFlag; //返済可能フラグ
            l_closeMarginGroup.swapFlag = l_contractInfo.swapPossibleFlag; //現引現渡可能フラグ

        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。", l_ex);

            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_closeMarginGroup;
    }

    /**
     * (create建株明細)。<BR>
     * <BR>
     * 指定の建株情報から建株明細を作成する。<BR>
     * <BR>
     * １）　@建株明細の生成。<BR>
     * 　@信用取引建株明細オブジェクトを生成する。<BR>
     * <BR>
     * ２）　@プロパティのセット<BR>
     * 　@１）で生成した建株明細オブジェクトに以下のプロパティセットを行う。<BR>
     * <BR>
     * 　@建株明細.ID = 引数.建株情報.ID<BR>
     * 　@建株明細.建日 = 引数.建株情報.建日<BR>
     * 　@建株明細.建単価 = 引数.建株情報.建単価<BR>
     * 　@建株明細.建株数 = 引数.建株情報.建株数<BR>
     * 　@建株明細.建代金 = 引数.建株情報.建代金<BR>
     * 　@建株明細.評価損益 = 引数.建株情報.評価損益(諸経費考慮)(*)<BR>
     * 　@建株明細.注文株数 = NULL<BR>
     * 　@建株明細.内出来株数 = NULL<BR>
     * 　@建株明細.決済順位 = NULL<BR>
     * <BR>
     * 　@(*)決済一覧に表示する評価損益は諸経費を考慮したものを使用する<BR>
     * <BR>
     * ３）　@２）でプロパティをセットした建株明細オブジェクトを返却する。<BR>
     * <BR>
     * @@param l_contractInfo - (建株情報)<BR>
     * 信用取引建株情報
     * @@return webbroker3.margin.message.WEB3MarginContractUnit
     * @@roseuid 40F4D92D022C
     */
    protected WEB3MarginContractUnit createContractUnit(WEB3MarginContractInfo l_contractInfo)
    {
        WEB3MarginContractUnit l_contractUnit = new WEB3MarginContractUnit();
        l_contractUnit.id = l_contractInfo.id; //ID
        l_contractUnit.openDate = WEB3DateUtility.toDay(l_contractInfo.openDate); //建日
        l_contractUnit.contractPrice = l_contractInfo.contractPrice; //建単価
        l_contractUnit.contractQuantity = l_contractInfo.quantity; //建株数
        l_contractUnit.contractExecPrice = l_contractInfo.contractExecPrice; //建代金
        l_contractUnit.appraisalProfitLoss = l_contractInfo.takeExpensesOffEvaluationIncome; //評価損益
        l_contractUnit.orderQuantity = null; //注文株数
        l_contractUnit.partContQuantity = null; //内出来株数
        l_contractUnit.settlePriority = null; //決済順位

        return l_contractUnit;
    }

    /**
     * (set決済可能フラグto建株情報)。<BR>
     * <BR>
     * 指定の建株情報の返済可能フラグ、および現引現渡可能フラグを設定を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引決済一覧サービス）set決済可能フラグto建株情報」参照。<BR>
     * <BR>
     * @@param l_contractInfo - (建株情報)<BR>
     * 返済可能フラグ、現引現渡可能フラグをセットする対象の建株情報
     * @@param l_blnIsProductDesignate - (is銘柄指定)<BR>
     * 銘柄コードが指定されている場合、true。以外、false。<BR>
     * @@roseuid 40F4B653004C
     */
    protected void setCloseMarginPossibleFlagToContractInfo(
        WEB3MarginContractInfo l_contractInfo,
        boolean l_blnIsProductDesignate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setCloseMarginPossibleFlagToContractInfo()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeOrderValidator l_orderValidator = (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();
        WEB3GentradeFinObjectManager l_finObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();


        //各validateの結果格納用変数
        boolean l_blnIsValidTradingSubAccount  = true;     //isValid取引可能顧客
        boolean l_blnIsClsMgnOdrAptPossible    = true;     //validate注文受付可能（返済）
        boolean l_blnIsSwpMgnOdrAptPossible    = true;     //validate注文受付可能（現引・現渡）
        boolean l_blnIsValidMarketCode         = true;     //isValid市場コード
        boolean l_blnIsValidProductCode        = true;     //isValid銘柄コード
        boolean l_blnIsNotInsider              = true;     //isNotインサイダー
        boolean l_blnIsNotStpdPdctForCls       = true;     //isNot顧客銘柄取引停止（返済）
        boolean l_blnIsNotStpdPdctForSwp       = true;     //isNot顧客銘柄取引停止（現引・現渡）
        boolean l_blnIsValidTrddPdctForCls     = true;     //isValid取引銘柄（信用／返済）
        boolean l_blnIsValidTrddPdctForSwp     = true;     //isValid取引銘柄（信用／現引・現渡）
        boolean l_blnIsValidHandlingMarket     = true;     //isValid取扱可能市場（信用）

        String l_strInstitutionCode             = null;     //補助口座に紐づく証券会社コード
        WEB3GentradeBranch  l_branch            = null;     //補助口座に紐づく部店
        WEB3GentradeMarket  l_market            = null;     //validate市場コード時の市場
        WEB3EquityProduct   l_product           = null;     //validate銘柄時の銘柄
        WEB3EquityTradedProduct l_tradeProduct  = null;     //validate取引銘柄時の取引銘柄
        
        //信用取引建株情報から建区分、返済時の注文種別、現引・現渡時の注文種別を取得
        boolean l_blnIsShortContract
            = (l_contractInfo.contractType.equals(String.valueOf(ContractTypeEnum.SHORT.intValue())));
        OrderTypeEnum l_OrderTypeEnumForCls
            = l_blnIsShortContract ? OrderTypeEnum.CLOSE_MARGIN_SHORT : OrderTypeEnum.CLOSE_MARGIN_LONG;
        OrderTypeEnum l_OrderTypeEnumForSwp
            = l_blnIsShortContract ? OrderTypeEnum.SWAP_MARGIN_SHORT : OrderTypeEnum.SWAP_MARGIN_LONG;
        
        
        //get補助口座（および、補助口座から証券会社コード、部店を取得）
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
        l_strInstitutionCode                = l_subAccount.getInstitution().getInstitutionCode();
        l_branch                            = l_subAccount.getWeb3GenBranch();        


        //validate取引可能顧客
        OrderValidationResult l_orderValidationResult = l_orderValidator.validateSubAccountForTrading(l_subAccount);
        l_blnIsValidTradingSubAccount = l_orderValidationResult.getProcessingResult().isSuccessfulResult();
        if (l_blnIsValidTradingSubAccount == false)
        {
            log.debug("validate取引可能顧客()が例外をスロー ※返済／現引現渡不可");
            l_contractInfo.closingPossibleFlag = false;
            l_contractInfo.swapPossibleFlag = false;
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        
        //reset市場コード
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_contractInfo.marketCode);
        
        
        //返済注文受付可能チェック
        try
        {
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsClsMgnOdrAptPossible = false;
        }
        
        
        //現引現渡注文受付可能チェック
        try
        {
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.SWAP_MARGIN);
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsSwpMgnOdrAptPossible = false;
        }
        
        
        //validate市場コード
        try
        {
            l_market = (WEB3GentradeMarket) l_orderManager.validateMarket(
                l_contractInfo.marketCode, l_strInstitutionCode);
            l_blnIsValidMarketCode = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsValidMarketCode = false;
            try
            {
                //(*)validate市場コードが例外をthrowした場合には、
                //拡張金融オブジェクトマネージャ.getMarket(市場ID)にて取得する。
                log.debug("validate市場コードが失敗したので拡張金融オブジェクトマネージャから以下条件で市場を取得");
                log.debug("(市場コード, 証券会社コード)=(["+l_contractInfo.marketCode+"],["+l_strInstitutionCode+"])");
                l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(
                    l_strInstitutionCode, l_contractInfo.marketCode);
            }
            catch (NotFoundException l_nfe)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(), l_nfe);
            }
        }
        
        
        //validate銘柄コード
        try
        {
            l_product = (WEB3EquityProduct) l_orderManager.validateProductCode(
                l_contractInfo.productCode, l_strInstitutionCode, l_contractInfo.repaymentType);
            l_blnIsValidProductCode = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            log.debug("validate銘柄コード（信用）()が例外をスロー ※返済／現引現渡不可");
            l_contractInfo.closingPossibleFlag = false;
            l_contractInfo.swapPossibleFlag = false;
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        
        //validateインサイダー
        try
        {
            l_orderManager.validateInsider(l_subAccount, l_product);
            l_blnIsNotInsider = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            log.debug("validateインサイダー()が例外をスロー ※返済／現引現渡不可");
            l_contractInfo.closingPossibleFlag = false;
            l_contractInfo.swapPossibleFlag = false;
            log.exiting(STR_METHOD_NAME);
            return;
        } 
        
        
        //validate顧客銘柄別取引停止（返済）
        try
        {
            l_orderManager.validateAccountProductOrderStop(
                l_subAccount, l_product.getProductId(), l_OrderTypeEnumForCls);
            l_blnIsNotStpdPdctForCls = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsNotStpdPdctForCls = false;
        }
        
        
        //validate顧客銘柄別取引停止（現引・現渡）
        try
        {
            l_orderManager.validateAccountProductOrderStop(
                l_subAccount, l_product.getProductId(), l_OrderTypeEnumForSwp);
            l_blnIsNotStpdPdctForSwp = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsNotStpdPdctForSwp = false;
        }
        
        
        //validate取引銘柄（返済）
        try
        {
            l_tradeProduct = (WEB3EquityTradedProduct) l_orderManager.validateTradedProductForMarginTrading(
                l_subAccount, l_product, l_market, l_branch,
                l_contractInfo.repaymentType, OrderCategEnum.CLOSE_MARGIN, l_blnIsShortContract);
            l_blnIsValidTrddPdctForCls = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsValidTrddPdctForCls = false;
        }
        
        
        //validate取引銘柄（現引・現渡）
        try
        {
            l_tradeProduct = (WEB3EquityTradedProduct) l_orderManager.validateTradedProductForMarginTrading(
                l_subAccount, l_product, l_market, l_branch,
                l_contractInfo.repaymentType, OrderCategEnum.SWAP_MARGIN, l_blnIsShortContract);
            l_blnIsValidTrddPdctForSwp = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsValidTrddPdctForSwp = false;
        }
        
        
        //validate取扱可能市場
        if (l_blnIsValidTrddPdctForCls || l_blnIsValidTrddPdctForSwp)
        {
            try
            {
                l_orderManager.validateHandlingMarket(
                    l_branch, l_tradeProduct, l_contractInfo.marketCode,
                    l_contractInfo.repaymentType, Double.parseDouble(l_contractInfo.repaymentNum));
                l_blnIsValidHandlingMarket = true;
            }
            catch (WEB3BaseException l_wbe)
            {
                log.debug("validate取扱可能市場（信用）()が例外をスロー ※返済／現引現渡不可");
                l_contractInfo.closingPossibleFlag = false;
                l_contractInfo.swapPossibleFlag = false;
                log.exiting(STR_METHOD_NAME);
                return;
            }
            catch (NumberFormatException l_nfe)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(), l_nfe);
            }
        }
        else
        {
            //validate取引銘柄において返済時、現引・現渡時の両方でfalseだった場合
            //validate取扱可能市場をコールする必要なし。
        }
        
        //デバッグ用ログ出力
        log.debug("validate取引可能顧客                -> [" + String.valueOf(l_blnIsValidTradingSubAccount) + "]");
        log.debug("validate市場コード                  -> [" + String.valueOf(l_blnIsValidMarketCode)        + "]");
        log.debug("validate銘柄コード                  -> [" + String.valueOf(l_blnIsValidProductCode)       + "]");
        log.debug("validateインサイダー                -> [" + String.valueOf(l_blnIsNotInsider)             + "]");
        log.debug("validate顧客銘柄取引停止/返済       -> [" + String.valueOf(l_blnIsNotStpdPdctForCls)      + "]");
        log.debug("validate顧客銘柄取引停止/現引・現渡 -> [" + String.valueOf(l_blnIsNotStpdPdctForSwp)      + "]");
        log.debug("validate取引銘柄（信用）/返済       -> [" + String.valueOf(l_blnIsValidTrddPdctForCls)    + "]");
        log.debug("validate取引銘柄（信用）/現引・現渡 -> [" + String.valueOf(l_blnIsValidTrddPdctForSwp)    + "]");
        log.debug("validate取扱可能市場                -> [" + String.valueOf(l_blnIsValidHandlingMarket)    + "]");
        
        //返済可能フラグの設定
        l_contractInfo.closingPossibleFlag =
            l_blnIsValidTradingSubAccount &&
            l_blnIsClsMgnOdrAptPossible &&
            l_blnIsValidMarketCode &&
            l_blnIsValidProductCode &&
            l_blnIsNotInsider &&
            l_blnIsNotStpdPdctForCls &&
            l_blnIsValidTrddPdctForCls &&
            l_blnIsValidHandlingMarket;
        
        //現引・現渡可能フラグの設定
        l_contractInfo.swapPossibleFlag =
            l_blnIsValidTradingSubAccount &&
            l_blnIsSwpMgnOdrAptPossible &&
            l_blnIsValidProductCode &&
            l_blnIsNotInsider &&
            l_blnIsNotStpdPdctForSwp &&
            l_blnIsValidTrddPdctForSwp &&
            l_blnIsValidHandlingMarket;
            
        log.debug("返済可能フラグ          -> [" + String.valueOf(l_contractInfo.closingPossibleFlag) + "]");
        log.debug("現引・現渡可能フラグ    -> [" + String.valueOf(l_contractInfo.swapPossibleFlag) + "]");

        log.exiting(STR_METHOD_NAME);
    }
    

    /**
     * (set決済一覧行)。<BR>
     * <BR>
     * 指定の決済一覧行の建株明細のマージ処理とプロパティセットを行う。 <BR>
     * １）　@建株明細ごとのLoop処理。 <BR>
     * 　@引数.建株明細の要素ごとのLoop処理にて、以下の値を取得する。 <BR>
     * <BR>
     * 　@合計建株数 = 合計建株数 + 建株明細[インデックス].建株数 <BR>
     * 　@合計建単価 = 合計建単価 + （建株明細[インデックス].建単価 <BR>
     * 　@　@　@　@　@　@　@　@× 建株明細[インデックス].建株数） <BR>
     * 　@合計評価損益 = 合計評価損益 + 建株明細[インデックス].評価損益 <BR>
     * <BR>
     * ２）　@プロパティセット。 <BR>
     * 　@引数.決済一覧行に以下の通りプロパティをセットする。 <BR>
     * <BR>
     * 　@決済一覧行.建株数 = 合計建株数 <BR>
     * 　@決済一覧行.平均建単価 = 合計建単価 ÷ 合計建株数(円未満は四捨五入) <BR>
     * 　@決済一覧行.評価損益 = 合計評価損益 <BR>
     *     ※ただし、引数の建株明細に、評価損益==nullの明細が含まれる場合は、セットしない。<BR>
     * 　@決済一覧行.建株明細一覧 = 引数.建株明細一覧 <BR>
     * <BR>
     * @@param l_closeMarginGroup - (決済一覧行)<BR>
     * 建株明細のマージ処理とプロパティセットを行う対象の信用取引決済一覧行
     * @@param l_contractUnitList - (建株明細一覧)<BR>
     * 信用取引建株明細の配列
     * @@roseuid 40F4D9400038
     */
    protected void setCloseMarginGroup(WEB3MarginCloseMarginGroup l_closeMarginGroup, WEB3MarginContractUnit[] l_contractUnitList)
    {
        final String STR_METHOD_NAME = " setCloseMarginGroup()";
        log.entering(STR_METHOD_NAME);

        double l_dblDetailContractQuantity = 0; //1明細の建株数
        double l_dblDetailContractPrice = 0;    //1明細の建株数
        double l_dblDetailIncome = 0;           //1明細の建株数
        double l_dblTotalContractQuantity = 0;  //合計建株数
        double l_dblTotalContractPrice = 0;     //合計建単価
        double l_dblTotalIncome = 0;            //合計評価損益
        boolean l_blnNullIncome = false;

        for (int i = 0; i < l_contractUnitList.length; i++)
        {
            l_dblDetailContractQuantity =   Double.parseDouble(l_contractUnitList[i].contractQuantity);
            l_dblDetailContractPrice    =   Double.parseDouble(l_contractUnitList[i].contractPrice);
            if (l_contractUnitList[i].appraisalProfitLoss == null)
            {
				l_dblDetailIncome = 0D;
				l_blnNullIncome = true;
            }
            else
            {
				l_dblDetailIncome       =   Double.parseDouble(l_contractUnitList[i].appraisalProfitLoss);
            }
            
            l_dblTotalContractQuantity  +=  l_dblDetailContractQuantity;
            l_dblTotalContractPrice     +=  l_dblDetailContractPrice * l_dblDetailContractQuantity;
            l_dblTotalIncome            +=  l_dblDetailIncome;
        }

        l_closeMarginGroup.contractQuantity = WEB3StringTypeUtility.formatNumber(l_dblTotalContractQuantity);
        l_closeMarginGroup.averageContractPrice
            = WEB3StringTypeUtility.formatNumber(Math.round(l_dblTotalContractPrice / l_dblTotalContractQuantity));
        if (l_blnNullIncome == true)
        {
			l_closeMarginGroup.appraisalProfitLoss = null;
        }
        else
        {
			l_closeMarginGroup.appraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblTotalIncome);
        }
        l_closeMarginGroup.contractUnits = l_contractUnitList;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (sort建株情報一覧for決済一覧行作成)。<BR>
     * <BR>
     * 決済一覧行を作成するための一括条件(*1)による建株情報一覧のソートを行う。<BR>
     * <BR>
     * (*1)銘柄コード、口座区分、市場コード、建区分、弁済区分、<BR>
     * 　@　@決済状態区分(決済状態が異なる明細は別行として表示するため)<BR>
     * <BR>
     * １）　@ArrayListを作成<BR>
     * <BR>
     * ２）　@Compartorの作成<BR>
     * 　@　@下記順番にて各Comparatorを生成し、ArrayListに追加する。<BR>
     * 　@　@<BR>
     * 　@　@２－１）　@信用銘柄コードComparator（”昇順”）を生成し、ArrayListに追加<BR>
     * <BR>
     * 　@　@２－２）　@信用口座区分Comparator（”昇順”）を生成し、ArrayListに追加<BR>
     * <BR>
     * 　@　@２－３）　@信用市場コードComparator（”昇順”）を生成し、ArrayListに追加<BR>
     * <BR>
     * 　@　@２－４）　@信用建区分Comparator（”昇順”）を生成し、ArrayListに追加<BR>
     * <BR>
     * 　@　@２－５）　@信用弁済区分Comparator（”昇順”）を生成し、ArrayListに追加<BR>
     * <BR>
     * 　@　@２－６）　@信用決済状態区分Comparator（”昇順”）を生成し、ArrayListに追加<BR>
     * <BR>
     * 　@　@２－７）　@信用建日Comparator（”昇順”）を生成し、ArrayListに追加<BR>
     * 　@　@<BR>
     * ３）　@ArrayList.toArray( )にてComparatorの配列を作成<BR>
     * <BR>
     * ４）　@Comparatorの配列順に引数.建株情報をソート<BR>
     * (web3-common)WEB3ArraysUtility.sort(引数.建株情報一覧、<BR>
     * ３）で作成したComparator[])<BR>
     * <BR>
     * @@param l_contractInfoList - (建株情報一覧)<BR>
     * 信用取引建株情報の一覧
     * @@roseuid 40F4C6AC001D
     */
    protected void sortContractInfoListForColseMarginGroupList(WEB3MarginContractInfo[] l_contractInfoList)
    {
        final String STR_METHOD_NAME = " sortContractInfoListForColseMarginGroupList()";
        log.entering(STR_METHOD_NAME);
        String l_strAscDesc = WEB3AscDescDef.ASC;
        //１）　@ArrayListを作成
        List l_lstComparator = new ArrayList();
        //２－１）　@信用銘柄コードComparator（”昇順”）を生成し、ArrayListに追加
        WEB3MarginProductCodeComparator l_productCodeComparator = new WEB3MarginProductCodeComparator(l_strAscDesc);
        l_lstComparator.add(l_productCodeComparator);
        //２－２）　@信用口座区分Comparator（”昇順”）を生成し、ArrayListに追加
        WEB3MarginAccountTypeComparator l_accountTypeComparator = new WEB3MarginAccountTypeComparator(l_strAscDesc);
        l_lstComparator.add(l_accountTypeComparator);

        //２－３）　@信用市場コードComparator（”昇順”）を生成し、ArrayListに追加
        WEB3MarginMarketCodeComparator l_marketCodeComparator = new WEB3MarginMarketCodeComparator(l_strAscDesc);
        l_lstComparator.add(l_marketCodeComparator);

        //２－４）　@信用建区分Comparator（”昇順”）を生成し、ArrayListに追加
        WEB3MarginContractTypeComparator l_contractTypeComparator = new WEB3MarginContractTypeComparator(l_strAscDesc);
        l_lstComparator.add(l_contractTypeComparator);

        //２－５）　@信用弁済区分Comparator（”昇順”）を生成し、ArrayListに追加
        WEB3MarginRepaymentTypeComparator l_reparmentTypeComparator = new WEB3MarginRepaymentTypeComparator(l_strAscDesc);
        l_lstComparator.add(l_reparmentTypeComparator);
        
		WEB3MarginRepaymentNumComparator l_reparmentNumComparator = new WEB3MarginRepaymentNumComparator(l_strAscDesc);
		l_lstComparator.add(l_reparmentNumComparator);

        //２－６）　@信用決済状態区分Comparator（”昇順”）を生成し、ArrayListに追加
        WEB3MarginCloseStatusTypeComparator l_statusTypeComparator = new WEB3MarginCloseStatusTypeComparator(l_strAscDesc);
        l_lstComparator.add(l_statusTypeComparator);

        //２－７）　@信用建日Comparator（”昇順”）を生成し、ArrayListに追加
        WEB3MarginOpenDateComparator l_openDateComparator = new WEB3MarginOpenDateComparator(l_strAscDesc);
        l_lstComparator.add(l_openDateComparator);

        //３）　@ArrayList.toArray( )にてComparatorの配列を作成
        Comparator[] l_comparator = new Comparator[l_lstComparator.size()];
        l_lstComparator.toArray(l_comparator);

        // ４）　@Comparatorの配列順に引数.建株情報をソート
        WEB3ArraysUtility.sort(l_contractInfoList, l_comparator);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (sort決済一覧)。<BR>
     * <BR>
     * 指定されたソートキー、昇降順にもどついて決済一覧行の配列のソートを行う。<BR>
     * <BR>
     * １）　@ArrayListを作成<BR>
     * <BR>
     * ２）　@引数.ソートキーの配列数分Loop処理<BR>
     * 　@２－１）　@引数.ソートキー.キー項目を取得<BR>
     * <BR>
     * 　@２－２）　@引数.ソートキー.昇順/降順を取得<BR>
     * <BR>
     * 　@２－３）　@キー項目による分岐処理<BR>
     * 　@　@・キー項目が銘柄コードであった場合、信用銘柄コードComparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が市場コードであった場合、信用市場コードComparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が口座区分であった場合、信用口座区分Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * 
     * 　@　@・キー項目が建区分であった場合、信用建区分Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が弁済区分であった場合、信用弁済区分Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が弁済期限値であった場合、信用弁済期限値Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数=２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が評価損益であった場合、信用評価損益Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@２－４）　@２－３）にて作成したComparatorをArrayListに追加<BR>
     * <BR>
     * ３）　@ArrayListからComparatorの配列を作成<BR>
     * <BR>
     * ４）　@Comparatorの配列順に引数.決済一覧をソート<BR>
     * (web3-common)WEB3ArraysUtility.sort(引数.決済一覧、３）で作成した<BR>
     * Comparator[])<BR>
     * <BR>
     * @@param l_closeMarginGroupList - (決済一覧)<BR>
     * 信用取引決済一覧行の配列
     * @@param l_sortKey - (ソートキー)<BR>
     * 信用取引ソートキーの一覧
     * @@roseuid 40F4B657005B
     */
    protected void sortCloseMarginList(WEB3MarginCloseMarginGroup[] l_closeMarginGroupList, WEB3MarginSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = " sortCloseMarginList()";
        log.entering(STR_METHOD_NAME);

        // １）　@ArrayListを作成
        List l_lstComparators = new ArrayList();

        //２）　@引数.ソートキーの配列数分Loop処理
        for (int i = 0; i < l_sortKey.length; i++)
        {
            //２－１）　@引数.ソートキー.キー項目を取得
            String l_strKeyItem = l_sortKey[i].keyItem;

            //２－２）　@引数.ソートキー.昇順/降順を取得
            String l_strAscDesc = l_sortKey[i].ascDesc;

            Comparator l_com = null;

            if (WEB3EquityKeyItemDef.PRODUCTCODE.equals(l_strKeyItem))
            {
                //キー項目が銘柄コードであった場合、信用銘柄コードComparatorを生成
                l_com = new WEB3MarginProductCodeComparator(l_strAscDesc);
            }
            else if (WEB3EquityKeyItemDef.TRADEMARKET.equals(l_strKeyItem))
            {
                //キー項目が市場コードであった場合、信用市場コードComparatorを生成
                l_com = new WEB3MarginMarketCodeComparator(l_strAscDesc);
            }
            else if (WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(l_strKeyItem))
            {
                //キー項目が口座区分であった場合、信用口座区分Comparatorを生成
                l_com = new WEB3MarginAccountTypeComparator(l_strAscDesc);
            }
            else if (WEB3EquityKeyItemDef.CONTRACT_DIVISION.equals(l_strKeyItem))
            {
                //キー項目が建区分であった場合、信用建区分Comparatorを生成
                l_com = new WEB3MarginContractTypeComparator(l_strAscDesc);
            }
            else if (WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(l_strKeyItem))
            {
                //キー項目が弁済区分であった場合、信用弁済区分Comparatorを生成
                l_com = new WEB3MarginRepaymentTypeComparator(l_strAscDesc);
            }
            else if (WEB3EquityKeyItemDef.REPAYMENTNUM.equals(l_strKeyItem))
            {
                //キー項目が弁済期限値であった場合、信用弁済期限値Comparatorを生成
                l_com = new WEB3MarginRepaymentNumComparator(l_strAscDesc);
            }
            else if (WEB3EquityKeyItemDef.INCOME.equals(l_strKeyItem))
            {
                //キー項目が評価損益であった場合、信用評価損益Comparatorを生成
                l_com = new WEB3MarginAppraisalProfitOrLossComparator(l_strAscDesc);
            }
            //２－３）にて作成したComparatorをArrayListに追加
            if (l_com != null)
            {
                l_lstComparators.add(l_com);
            }
        }
        //３）　@ArrayListからComparatorの配列を作成
        Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
        l_lstComparators.toArray(l_comparators);

        //４）　@Comparatorの配列順に引数.決済一覧をソート
        //(web3-common)WEB3ArraysUtility.sort(引数.決済一覧、３）で作成した
        WEB3ArraysUtility.sort(l_closeMarginGroupList, l_comparators);

        log.exiting(STR_METHOD_NAME);
    }
}
@
