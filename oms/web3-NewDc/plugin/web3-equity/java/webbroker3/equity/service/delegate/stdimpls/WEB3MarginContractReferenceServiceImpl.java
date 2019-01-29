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
filename	WEB3MarginContractReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引建株照会サービスImpl(WEB3MarginContractReferenceServiceImpl.java)
Author Name      : 2004/9/24  盧法@旭(中訊) 新規作成         
Revesion History : 2004/12/14 森川   (SRA)  残案件対応  
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
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
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginCloseDateComparator;
import webbroker3.equity.WEB3MarginCloseStatus;
import webbroker3.equity.WEB3MarginContractInfo;
import webbroker3.equity.WEB3MarginContractTypeComparator;
import webbroker3.equity.WEB3MarginContractUnitAppraisalProfitOrLossComparator;
import webbroker3.equity.WEB3MarginMarketCodeComparator;
import webbroker3.equity.WEB3MarginOpenDateComparator;
import webbroker3.equity.WEB3MarginProductCodeComparator;
import webbroker3.equity.WEB3MarginRepaymentNumComparator;
import webbroker3.equity.WEB3MarginRepaymentTypeComparator;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.message.WEB3MarginContractReferenceRequest;
import webbroker3.equity.message.WEB3MarginContractReferenceResponse;
import webbroker3.equity.message.WEB3MarginContractReferenceUnit;
import webbroker3.equity.message.WEB3MarginProductCodeNameUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.message.WEB3MarginSortKey;
import webbroker3.equity.service.delegate.WEB3MarginContractReferenceService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeOtherConstDef;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引建株照会サービスImpl）。<BR>
 * <BR>
 * 信用取引建株照会サービス実装クラス
 * @@author 法@旭
 * @@version 1.0
 */
public class WEB3MarginContractReferenceServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginContractReferenceService 
{
    
    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginContractReferenceServiceImpl.class);

    /**
     * (コンストラクタ)。<BR>
     * @@roseuid 4140066D0040
     */
    public WEB3MarginContractReferenceServiceImpl() 
    {
    }
    
    /**
     * (execute)。<BR>
     * <BR>
     * 信用取引建株照会サービス処理を実施する。<BR>
     * シーケンス図<BR>
     * 「（信用取引建株照会サービス）建株照会」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     *   シーケンス図(「（信用取引建株照会サービス）建株照会」) : (5*)　@。<BR>   
     *   (銘柄コードチェック)<BR>
     *   getProduct( )で株式銘柄が取得できなかった場合は、「銘柄コードエラー」の例外をthrowし、処理を終了する。<BR>   
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00301<BR>
     *   ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4056A0210336
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "EXECUTE";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                                 
        }
        Institution l_institution = null;
        //1.1: validate
        WEB3MarginContractReferenceRequest l_request1 = (WEB3MarginContractReferenceRequest)l_request;
        l_request1.validate();
        
        
        boolean l_blnIsCloseMarginOrderAcceptPossible = true;
        boolean l_blnIsSwapMarginOrderAcceptPossible = true;
        WEB3BaseException l_wbeReturn = null;

        //validate注文受付可能 - 照会
        try
        {
            //注文受付時間区分          -> 01:株式・信用 (onCall()で設定済み)
            //注文受付トランザクション  -> 07:照会        (onCall()で設定済み)
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            //照会不可の場合はエラー終了する。
            throw new WEB3BusinessLayerException(
                l_wbe.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //validate注文受付可能 - 返済
        try
        {
            //注文受付時間区分          -> 01:株式・信用 (onCall()で設定済み)
            //注文受付トランザクション  -> 07:返済をセット
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_wbe)
        {
            l_wbeReturn = l_wbe;
            l_blnIsCloseMarginOrderAcceptPossible = false;
        }

        //validate注文受付可能 - 現引・現渡
        try
        {
            //注文受付時間区分          -> 19:現引・現渡をセット
            //注文受付トランザクション  -> 04:現引・現渡をセット
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
        
        
        //1.2: get sub account
        SubAccount l_subAccount = this.getSubAccount();
        //1.3:(分岐フロー) 銘柄コード指定時(リクエストデータ.銘柄コード!=null)のみ下記処理を実施する。
        FinApp l_fin = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_fin.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tm.getPositionManager();
        AccountManager l_accMa = l_fin.getAccountManager();
        WEB3EquityProductManager l_productMa = (WEB3EquityProductManager) l_tm.getProductManager(); 
        Market l_market = null;
        if (l_request1.productCode != null)
        {
            //1.3.1
            try 
            {
                l_institution = l_accMa.getInstitution(l_subAccount.getInstitution().getInstitutionCode());
                l_productMa.getProduct(l_institution,l_request1.productCode);
            } 
            catch (NotFoundException l_ex) 
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
            
            }           
        }
        //1.4
        WEB3MarginContractReferenceResponse l_response = (WEB3MarginContractReferenceResponse) l_request1.createResponse();
        //1.5銘柄・市場プルダウン作成
        //1.5.1get建株一覧(補助口座, ProductTypeEnum, String, String, String[])
        List l_listContracts = l_positionManager.getContracts(
            (WEB3GentradeSubAccount) l_subAccount,
            ProductTypeEnum.EQUITY,
            null,
            "product_id asc",
            null);
        //1.5.2下記プロパティセットを行い、戻り値を返却し、処理を終了する。
        if (l_listContracts.size() == 0)
        {
            l_response.productCodeNames = null;
            l_response.marketList = null;
            l_response.pageIndex = WEB3GentradeOtherConstDef.FORMAT_NUM5;
            l_response.totalRecords = WEB3GentradeOtherConstDef.FORMAT_NUM5;
            l_response.totalPages = WEB3GentradeOtherConstDef.FORMAT_NUM5;
            l_response.contractReferenceUnits = null;
            return l_response;
        }
        //1.5.3 product map
        Map l_productMap = new TreeMap();
        //1.5.4 market code map
        Map l_marketMap = new TreeMap();
        //1.5.5 loop to deal with contracts
        int l_intNum = l_listContracts.size();
        WEB3MarginCloseStatus l_status = null;
        WEB3EquityContract l_contractImpl = null;
        for(int i=0;i<l_intNum;i++)
        {
            //1.5.5.1get決済状態(建株 : 建株)
            long  l_lngContractId = ((EqtypeContractRow) l_listContracts.get(i)).getContractId();      
            try 
            {
                l_contractImpl = (WEB3EquityContract) l_positionManager.getContract(l_lngContractId);
            } catch (NotFoundException l_nfe) 
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            l_status =
                l_positionManager.getMarginCloseStatus(l_contractImpl);      
            //1.5.2
            if (l_status.closedMarginFlag ==false&&
                l_status.closeMarginFlag == false&&
                l_status.closingMarginFlag == false)
            {
                continue;
            }
            //1.5.5.3 get product            
            Product l_product = l_contractImpl.getProduct();
            //1.5.5.4 get standard name
            WEB3EquityProduct l_productImpl = null;
            try 
            {
                l_productImpl = (WEB3EquityProduct) l_productMa.getProduct(l_product.getProductId());
            } catch (NotFoundException l_nfe) 
            {
                log.exiting(STR_METHOD_NAME);
                log.error(STR_METHOD_NAME,l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            EqtypeProductRow l_productRow = (EqtypeProductRow) l_productImpl.getDataSourceObject();
            String l_strName = l_productRow.getStandardName();
            //1.5.5.5 get product code
            String l_strProductCode =l_productImpl.getProductCode();
            //1.5.5.6
            WEB3MarginProductCodeNameUnit l_productCodeName = new WEB3MarginProductCodeNameUnit();
            //1.5.5.7 set property
            l_productCodeName.productCode = l_strProductCode;
            l_productCodeName.productName = l_strName;
            //1.5.5.8
            l_productMap.put(l_strProductCode,l_productCodeName);
            //1.5.9 get market id
            long l_lngMarketId = l_contractImpl.getMarketId();
            //1.5.5.10
            try 
            {
                l_market = l_fin.getFinObjectManager().getMarket(l_lngMarketId);
            } 
            catch (NotFoundException l_ex) 
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
            }
            //1.5.5.11 get market code
            String l_strMarketCode = l_market.getMarketCode();
            //1.5.5.12
            l_marketMap.put(new Long(l_lngMarketId), l_strMarketCode);
            //1.5.6 check product map
            //1.5.6.1
            if (l_productMap.size() == 0)
            {
                l_response.productCodeNames = null;
                l_response.marketList = null;
                l_response.pageIndex = WEB3GentradeOtherConstDef.FORMAT_NUM5;
                l_response.totalRecords = WEB3GentradeOtherConstDef.FORMAT_NUM5;
                l_response.totalPages = WEB3GentradeOtherConstDef.FORMAT_NUM5;
                l_response.contractReferenceUnits = null;
                return l_response;
            }
            //1.5.7
            Collection l_collection =l_productMap.values(); ;
            //1.5.8
            WEB3MarginProductCodeNameUnit[] l_objProducts = (WEB3MarginProductCodeNameUnit[]) l_collection.toArray(new WEB3MarginProductCodeNameUnit[l_collection.size()]);
            //1.5.9
            Collection l_collectionMarket = l_marketMap.values();
            //1.5.10
            String[] l_objMarkets = (String[]) l_collectionMarket.toArray(new String[l_collectionMarket.size()]);
            //1.5.11 set response プロパティ
            l_response.productCodeNames = l_objProducts;
            l_response.marketList = l_objMarkets;
        }
        //1.6 reate検索条件文字列(String, String)
        String l_strResult = this.createSearchCondCharacter(l_request1.productCode,l_request1.marketCode);
        //1.7reate検索条件データコンテナ(String, 
        String[] l_strDatas = this.createSearchCondDataContainers(l_request1.productCode,l_request1.marketCode);
        //1.8eate建株情報一覧(補助口座, String, String, Strin
        String l_prodcutCode = l_request1.productCode;
        boolean l_blnProductCode ;
        if (l_prodcutCode != null)
        {
            l_blnProductCode = true; 
        }
        else
        {
            l_blnProductCode = false;    
        }
        WEB3MarginContractInfo[] l_listCreatreContact = this.createContractInfoList(
            (WEB3GentradeSubAccount) l_subAccount,
            l_request1.settlementState,
            l_strResult,
            l_strDatas,
            l_blnProductCode);
        //1.9 検索条件該当データチェック）
        //create建株情報一覧( )の戻り値がnullの場合(＝検索条件該当データなし)、
        //下記プロパティセットを行い、戻り値を返却し、処理を終了する。
        if (l_listCreatreContact == null|| l_listCreatreContact.length == 0)
        {
            l_response.pageIndex = WEB3GentradeOtherConstDef.FORMAT_NUM5;
            l_response.totalRecords = WEB3GentradeOtherConstDef.FORMAT_NUM5;
            l_response.totalPages = WEB3GentradeOtherConstDef.FORMAT_NUM5;
            l_response.contractReferenceUnits = null;
            return l_response;                
        }
        //1.10sort建株情報一覧(信用取引建株情報[], 信用取引ソートキー[])
        this.sortContractInfoList(l_listCreatreContact,l_request1.sortKeys);
        //1.11create建株照会明細一覧from建株情報一覧(信用取引建株情報[])
        WEB3MarginContractReferenceUnit[] l_objUnits = this.createContractReferenceUnitListFromContractInfoList(l_listCreatreContact);
        //1.12createページ(信用取引建株照会リクエスト, 信用取引建株照会明細[])
        WEB3MarginContractReferenceUnit[] l_units = this.createPage(l_request1,l_objUnits);
        //1.13 プロパティセット
        int l_intTotalRecord = l_objUnits.length;
        int l_intPageTotalLine = Integer.parseInt(l_request1.pageSize);
        int l_intNeedPage = Integer.parseInt(l_request1.pageIndex);
        int l_intLastPage = l_intTotalRecord/l_intPageTotalLine;
        int l_intPageMod = l_intTotalRecord%l_intPageTotalLine;
        if (l_intPageMod != 0)
        {
            l_intLastPage++;    
        }
        
        l_response.totalPages = new Integer(l_intLastPage).toString();
        l_response.pageIndex = l_request1.pageIndex;
        if (l_intTotalRecord <= l_intPageTotalLine * (l_intNeedPage - 1))
        {
            l_response.pageIndex = new Integer(l_intLastPage).toString();
        }

        l_response.totalRecords = new Integer(l_intTotalRecord).toString();

        l_response.contractReferenceUnits = l_units;
        
        return l_response;
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
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@param l_strMarketCode - 市場コード<BR>
     * @@return String
     * @@roseuid 40E3F3B002B0
     */
    protected String createSearchCondCharacter(String l_strProductCode, String l_strMarketCode) 
    {
        final String METHOD_NAME = "createSearchCondCharacter";
        log.debug(METHOD_NAME);
        String  l_strQuery ;        
        if (l_strProductCode ==null)
        {
            return null;
        }
        else
        {
            l_strQuery = " and product_id = ？";
        }
        if (l_strMarketCode == null)
        {
            return null;
        }
        else
        {
            l_strQuery = l_strQuery + " and market_id = ? " ;
        }
        return l_strQuery;
    }
    
    /**
     * (create検索条件データコンテナ)。<BR>
     * <BR>
     * リクエストデータから、検索条件（where以下指定の文字列）<BR>
     * のパラメータリストを作成する。<BR>
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
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@param l_strMarketCode - 市場コード<BR>
     * @@return String[]<BR>
     * @@roseuid 40E3F6000095
     */
    protected String[] createSearchCondDataContainers(String l_strProductCode, String l_strMarketCode) throws WEB3BaseException  
    {
        final String METHOD_NAME = "createSearchCondDataContainers";
        FinApp l_fin = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_fin.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager = (WEB3EquityProductManager) l_tm.getProductManager();
        FinObjectManager l_finObject = l_fin.getFinObjectManager();
        long l_lngProductId = 0;
        long l_lngMarketId = 0;
        
        if (l_strProductCode == null)
        {
            return null; 
        }
        if (l_strMarketCode == null)
        {
            return null;
        }
        SubAccount l_subAccount = this.getSubAccount();
        Institution l_institution = l_subAccount.getInstitution();
        try 
        {
            l_lngProductId = l_productManager.getProduct(l_institution,l_strProductCode).getProductId();
            l_lngMarketId = l_finObject.getMarket(l_institution,l_strMarketCode).getMarketId();
            
        }
        catch (NotFoundException l_ex) 
        {
            log.error(METHOD_NAME,l_ex);
            throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + "." + METHOD_NAME);          
        }
        String[] l_strQury = {" "+ l_lngProductId," "+ l_lngMarketId } ;
        
        return l_strQury;
    }
    
    /**
     * (create建株情報一覧)。<BR>
     * <BR>
     * 建株照会画面に表示する建株照会明細の元となる建株情報の一覧を作成する。<BR>
     * 以下のいずれかの決済状態に当てはまる建株情報を抽出する。<BR>
     * (決済状態の指定がある場合には、指定決済状態のみの建株情報とする)<BR>
     * ・決済済<BR>
     * ・未決済<BR>
     * ・決済中<BR>
     * <BR>
     * ※該当データが存在しない場合にはnullを返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引建株照会サービス）create建株情報一覧」参照。<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_strDesCloseMarginStatus - (指定決済状態)<BR>
     * 下記のいずれか。<BR>
     * <BR>
     * null：指定なし <BR>
     * 0：決済済<BR>
     * 1：未決済<BR>
     * 2：決済中<BR>
     * <BR>
     * @@param l_strSearchCondCharacter - 検索条件 文字列<BR>
     * @@param l_strSearchCondDataContainers - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ　@
     * @@param l_blnIsProductDesignate - (is銘柄指定)<BR>
     * 銘柄コードが指定されている場合、true。以外、false。<BR>
     * @@return WEB3MarginContractInfo[]
     * @@roseuid 40E519BF00E9
     */
    protected WEB3MarginContractInfo[] createContractInfoList(
        WEB3GentradeSubAccount l_subAccount, 
        String l_strDesCloseMarginStatus, 
        String l_strSearchCondCharacter, 
        String[] l_strSearchCondDataContainers, 
        boolean l_blnIsProductDesignate) throws WEB3BaseException
    {
        final String METHOD_NAME ="createContractInfoList";
        FinApp l_fin = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_fin.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tm.getPositionManager();       
        FinObjectManager l_finObject = l_fin.getFinObjectManager();
        Market l_market = null;
        ArrayList l_listContractIfo = null;
        //1.1 get 建株一覧
        List l_listContracts = l_positionManager.getContracts(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            l_strSearchCondCharacter,           
            l_strSearchCondDataContainers);
        //1.2 戻り値判定
        if(l_listContracts == null)
        {
            return null;
        }
        //1.3
        List l_arrayLists = new ArrayList();
        //1.4建株要素のloop処理
        int l_intNum = l_listContracts.size();
        for(int i=0;i<l_intNum; i++)
        {
            WEB3EquityContract l_contract = null;
            long  l_lngContractId = ((EqtypeContractRow) l_listContracts.get(i)).getContractId();      
            try 
            {
                l_contract = (WEB3EquityContract) l_positionManager.getContract(l_lngContractId);
            } 
            catch (NotFoundException l_nfe) 
            {            
                log.error(METHOD_NAME,l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + 
                    l_nfe.getMessage(),
                    l_nfe);
            }
            //1.4.1
            long l_lngMarketId = l_contract.getMarketId();
            //1.4.2 get market　@object
            try 
            {
                l_market = l_finObject.getMarket(l_lngMarketId);
            } 
            catch (NotFoundException l_ex) 
            {
                log.error(METHOD_NAME,l_ex);
                throw new WEB3BaseException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + "." + METHOD_NAME);                     
            }
            //1.4.3 get market code
            String l_strMarketCode = l_market.getMarketCode();
            //1.4.4 reset 市場コード
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            //1.4.5 get 決済状態
            WEB3MarginCloseStatus l_closeStatus = l_positionManager.getMarginCloseStatus(l_contract);
            //1.4.6 
            if (l_closeStatus.closedMarginFlag && ! l_closeStatus.closeMarginFlag && ! l_closeStatus.closingMarginFlag)
            {
                //1.4.6.1
                WEB3MarginContractInfo l_contractIfo = l_positionManager.createClosedMarginContractInfo(l_contract);
                //1.4.6.2
                l_arrayLists.add(l_contractIfo);
            }
            //1.4.7 
            else if (! l_closeStatus.closedMarginFlag && l_closeStatus.closeMarginFlag && ! l_closeStatus.closingMarginFlag)
            {
                //1.4.7.1
                WEB3MarginContractInfo l_contractIfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);
                //1.4.7.2 set決済可能フラグto建株情報
                this.setCloseMarginPossibleFlagToContractInfo(l_contractIfo, l_blnIsProductDesignate);
                //1.4.7.3 add object
                l_arrayLists.add(l_contractIfo);
            }
            //1.4.8
            else if (!l_closeStatus.closedMarginFlag&&!l_closeStatus.closeMarginFlag&&l_closeStatus.closingMarginFlag)
            {
                //1.4.8.1
                WEB3MarginContractInfo l_contractIfo = l_positionManager.createClosingMarginContractInfo(l_contract);
                //1.4.8.2
                l_arrayLists.add(l_contractIfo);
            }
            //1.4.9 
            else
            {
                //1.4.9.1
                this.createMultipleContractInfo(l_arrayLists,l_contract,l_closeStatus,l_blnIsProductDesignate);                                
            }
        }
        //1.5決済状態の指定がある場合のみ、
        //(引数.指定決済状態 != null)
        //以下の処理を実施する。
        if (l_strDesCloseMarginStatus !=null)
        {
            //1.5.1 get 指定決済状態建株情報一覧
            l_listContractIfo = this.getCloseMarginContractInfoList(l_strDesCloseMarginStatus,l_arrayLists);
            //1.5.2
            if (l_listContractIfo ==null)
            {
                return null;
            }
            else
            {
                int l_intSize = l_listContractIfo.size();
                WEB3MarginContractInfo[] l_contractIfos = (WEB3MarginContractInfo[]) l_listContractIfo.toArray(new WEB3MarginContractInfo[l_intSize]);
                return l_contractIfos;    
            }
        }
        else
        {
            int l_intSize = l_arrayLists.size();
            WEB3MarginContractInfo[] l_contractIfos = (WEB3MarginContractInfo[]) l_arrayLists.toArray(new WEB3MarginContractInfo[l_intSize]);
            return l_contractIfos;               
        }       
}
    

    /**
     * (create複数建株情報)。<BR>
     * <BR>
     * 1つの建株で複数の建株情報を作成する場合の処理を行う。 <BR>
     * <BR>
     * 引数の決済状態にもとづき、  <BR>
     * 株式ポジションマネージャ.create決済済建株情報( ) <BR>
     * 株式ポジションマネージャ.create未決済建株情報( ) <BR>
     * 株式ポジションマネージャ.create決済中建株情報( ) <BR>
     * メソッドのいずれかをコールする。 <BR>
     * <BR>
     * １）　@決済状態：下記の場合(未決済と決済中)、未決済と決済中の2明細を作成する。  <BR>
     * 　@引数.決済状態.決済済フラグ＝false  <BR>
     * 　@引数.決済状態.未決済フラグ＝true   <BR>
     * 　@引数.決済状態.決済中フラグ＝true <BR>
     * <BR>
     * １－１）　@株式ポジションマネージャ.create未決済建株情報(引数.建株)  <BR>
     * １－２）　@this.set決済可能フラグto建株情報(１?１の戻り値の信用取引建株情報、 <BR>
     * 　@　@　@　@　@引数.is銘柄指定、引数.is返済注文受付可能、引数.is現引現渡注文受付可能) <BR>
     * １－３）　@引数.建株情報リスト.add(１?２でプロパティセットした信用取引建株情報) <BR>
     * １－４）　@株式ポジションマネージャ.create決済中建株情報(引数.建株)  <BR>
     * １－５）　@引数.建株情報リスト.add(１?４の戻り値の信用取引建株情報) <BR>
     * <BR>
     * ２）　@決済状態：下記の場合(決済済と未決済)、決済済と未決済の2明細を作成する。  <BR>
     * 　@引数.決済状態.決済済フラグ＝true <BR>
     * 　@引数.決済状態.未決済フラグ＝true   <BR>
     * 　@引数.決済状態.決済中フラグ＝false <BR>
     * <BR>
     * ２－１）　@株式ポジションマネージャ.create決済済建株情報(引数.建株)  <BR>
     * ２－２）　@引数.建株情報リスト.add(２?１の戻り値の信用取引建株情報) <BR>
     * ２－３）　@株式ポジションマネージャ.create未決済建株情報(引数.建株)  <BR>
     * ２－４）　@this.set決済可能フラグto建株情報(２?３の戻り値の信用取引建株情報、 <BR>
     * 　@　@　@　@　@引数.is銘柄指定、引数.is返済注文受付可能、引数.is現引現渡注文受付可能) <BR>
     * ２－５）　@引数.建株情報リスト.add(２?４でプロパティセットした信用取引建株情報) <BR>
     * <BR>
     * ３）　@決済状態：下記の場合(決済済と決済中)、決済済と決済中の2明細を作成する。  <BR>
     * 　@引数.決済状態.決済済フラグ＝true <BR>
     * 　@引数.決済状態.未決済フラグ＝false   <BR>
     * 　@引数.決済状態.決済中フラグ＝true <BR>
     * <BR>
     * ３－１）　@株式ポジションマネージャ.create決済済建株情報(引数.建株)  <BR>
     * ３－２）　@引数.建株情報リスト.add(３?１の戻り値の信用取引建株情報) <BR>
     * ３－３）　@株式ポジションマネージャ.create決済中建株情報(引数.建株)  <BR>
     * ３－４）　@引数.建株情報リスト.add(３?３の戻り値の信用取引建株情報) <BR>
     * <BR>
     * ４）　@決済状態：下記の場合(決済済と未決済と決済中)、決済済と未決済と決済中の <BR>
     * 　@　@　@3明細を作成する。  <BR>
     * 　@引数.決済状態.決済済フラグ＝true <BR>
     * 　@引数.決済状態.未決済フラグ＝true   <BR>
     * 　@引数.決済状態.決済中フラグ＝true <BR>
     * <BR>
     * ４－１）　@株式ポジションマネージャ.create決済済建株情報(引数.建株)  <BR>
     * ４－２）　@引数.建株情報リスト.add(４?１の戻り値の信用取引建株情報) <BR>
     * ４－３）　@株式ポジションマネージャ.create未決済建株情報(引数.建株)  <BR>
     * ４－４）　@this.set決済可能フラグto建株情報(４?３の戻り値の信用取引建株情報、 <BR>
     * 　@　@　@　@　@引数.is銘柄指定、引数.is返済注文受付可能、引数.is現引現渡注文受付可能) <BR>
     * ４－５）　@引数.建株情報リスト.add(４?４でプロパティセットした信用取引建株情報) <BR>
     * ４－６）　@株式ポジションマネージャ.create決済中建株情報(引数.建株)  <BR>
     * ４－７）　@引数.建株情報リスト.add(４?６の戻り値の信用取引建株情報)<BR>
     * <BR>
     * @@param l_lisContractInfoList - (建株情報リスト)<BR>
     * 作成した建株情報を格納するリスト
     * @@param l_contract - (建株)<BR>
     * 建株情報を作成する対象の建株<BR>
     * @@param l_closeMarginStatus - (決済状態)<BR>
     * @@param l_blnIsProductDesignate<BR>
     * 信用取引決済状態<BR>
     * @@roseuid 40ED1E020103
     */
    protected void createMultipleContractInfo(
        List l_lisContractInfoList, 
        WEB3EquityContract l_contract, 
        WEB3MarginCloseStatus l_closeStatus,
        boolean l_blnIsProductDesignate) throws WEB3BaseException
    {
        
        final String METHOD_NAME ="createContractInfoList";
        log.debug(METHOD_NAME);
        if (l_lisContractInfoList == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + METHOD_NAME);                                 
        }
        if (l_closeStatus == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + METHOD_NAME);                                 
        }
        FinApp l_fin = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_fin.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tm.getPositionManager();       

        WEB3MarginContractInfo l_contractIfo = null;

        //1) false true true の場合
        if ( ! l_closeStatus.closedMarginFlag && l_closeStatus.closeMarginFlag && l_closeStatus.closingMarginFlag)
        {
            //１－１）　@株式ポジションマネージャ.create未決済建株情報(引数.建株) <BR>
            l_contractIfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);
            //１－２）　@this.set決済可能フラグto建株情報(１－１の戻り値の信用取引建株情報、<BR>
            //引数.is銘柄市場指定)<BR>
            this.setCloseMarginPossibleFlagToContractInfo(
                l_contractIfo, l_blnIsProductDesignate);
            //１－３）　@引数.建株情報リスト.add(１－２でプロパティセットした信用取引建株情報)<BR>
            l_lisContractInfoList.add(l_contractIfo);
            //１－４）　@株式ポジションマネージャ.create決済中建株情報(引数.建株) <BR>
            l_contractIfo = l_positionManager.createClosingMarginContractInfo(l_contract);
            //１－５）　@引数.建株情報リスト.add(１－４の戻り値の信用取引建株情報)<BR>
            l_lisContractInfoList.add(l_contractIfo);
                   
        }
         //2)true true false
        else if (l_closeStatus.closedMarginFlag && l_closeStatus.closeMarginFlag && ! l_closeStatus.closingMarginFlag)
        {
            //* ２－１）　@株式ポジションマネージャ.create決済済建株情報(引数.建株) <BR>
            l_contractIfo = l_positionManager.createClosedMarginContractInfo(l_contract);
            //* ２－２）　@引数.建株情報リスト.add(２－１の戻り値の信用取引建株情報)<BR>
            l_lisContractInfoList.add(l_contractIfo);
            //* ２－３）　@株式ポジションマネージャ.create未決済建株情報(引数.建株) <BR>
            l_contractIfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);
            //* ２－４）　@this.set決済可能フラグto建株情報(２－３の戻り値の信用取引建株情報、<BR>
            //* 　@　@　@　@　@引数.is銘柄市場指定)<BR>
            this.setCloseMarginPossibleFlagToContractInfo(
                l_contractIfo, l_blnIsProductDesignate);
            //* ２－５）　@引数.建株情報リスト.add(２－４でプロパティセットした信用取引建株情報)<BR>
            l_lisContractInfoList.add(l_contractIfo);

        }
        //3 true false true
        else if ( l_closeStatus.closedMarginFlag && ! l_closeStatus.closeMarginFlag && l_closeStatus.closingMarginFlag)
        {
            //* ３－１）　@株式ポジションマネージャ.create決済済建株情報(引数.建株) <BR>
            l_contractIfo = l_positionManager.createClosedMarginContractInfo(l_contract);
            //* ３－２）　@引数.建株情報リスト.add(３－１の戻り値の信用取引建株情報)<BR>
            l_lisContractInfoList.add(l_contractIfo);
            //* ３－３）　@株式ポジションマネージャ.create決済中建株情報(引数.建株) <BR>
            l_contractIfo = l_positionManager.createClosingMarginContractInfo(l_contract);
            //* ３－４）　@引数.建株情報リスト.add(３－３の戻り値の信用取引建株情報)<BR>
            l_lisContractInfoList.add(l_contractIfo);            
        }
        //4 true true true 
        else if (l_closeStatus.closedMarginFlag && l_closeStatus.closeMarginFlag && l_closeStatus.closingMarginFlag)
        {
            
            //* ４－１）　@株式ポジションマネージャ.create決済済建株情報(引数.建株) <BR>
            l_contractIfo = l_positionManager.createClosedMarginContractInfo(l_contract);
            //* ４－２）　@引数.建株情報リスト.add(４－１の戻り値の信用取引建株情報)<BR>
            l_lisContractInfoList.add(l_contractIfo);
            //* ４－３）　@株式ポジションマネージャ.create未決済建株情報(引数.建株) <BR>
            l_contractIfo = l_positionManager.createUnCloseMarginContractInfo(l_contract);
            //* ４－４）　@this.set決済可能フラグto建株情報(４－３の戻り値の信用取引建株情報、<BR>
            //* 　@　@　@　@　@引数.is銘柄市場指定)<BR>
            this.setCloseMarginPossibleFlagToContractInfo(
                l_contractIfo, l_blnIsProductDesignate);
            //* ４－５）　@引数.建株情報リスト.add<BR>
            //*         (４－４でプロパティセットした信用取引建株情報)<BR>
            l_lisContractInfoList.add(l_contractIfo);
            //* ４－６）　@株式ポジションマネージャ.create決済中建株情報(引数.建株) <BR>
            l_contractIfo = l_positionManager.createClosingMarginContractInfo(l_contract);
            //* ４－７）　@引数.建株情報リスト.add(４－６の戻り値の信用取引建株情報)<BR>            
            l_lisContractInfoList.add(l_contractIfo);
        }
    }
    
    /**
     * (create建株照会明細一覧from建株情報一覧)。<BR>
     * 建株情報の一覧から信用取引建株照会明細の一覧を作成する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@引数.建株情報一覧要素数分のLoop処理。<BR>
     * 　@２－１）　@信用取引建株照会明細オブジェクトを生成する。<BR>
     * <BR>
     * 　@２－２）　@信用取引弁済オブジェクトを生成し、下記プロパティセットを行う。<BR>
     * 　@　@　@　@　@　@信用取引弁済.弁済区分 = 建株情報[インデックス].弁済区分<BR>
     * 　@　@　@　@　@　@信用取引弁済.弁済期限値 = 建株情報[インデックス].弁済期限値<BR>
     * <BR>
     * 　@２－３）　@建株情報の各プロパティ設定値を、生成した信用取引建株照会明細の<BR>
     * 各プロパティに設定する。<BR>
     * 　@建株照会明細.ID = 建株情報[インデックス].ID<BR>
     * 　@建株照会明細.銘柄コード = 建株情報[インデックス].銘柄コード<BR>
     * 　@建株照会明細.銘柄名 = 建株情報[インデックス].銘柄名<BR>
     * 　@建株照会明細.市場コード = 建株情報[インデックス].市場コード<BR>
     * 　@建株照会明細.口座区分 = 建株情報[インデックス].口座区分<BR>
     * 　@建株照会明細.建区分 = 建株情報[インデックス].建区分<BR>
     * 　@建株照会明細.弁済 =　@２－２）にてプロパティセットした信用取引弁済 <BR>
     * 　@建株照会明細.建日 = 建株情報[インデックス].建日<BR>
     * 　@建株照会明細.建単価 = 建株情報[インデックス].建単価<BR>
     * 　@建株照会明細.建株数 = 建株情報[インデックス].建株数<BR>
     * 　@建株照会明細.期日 = 建株情報[インデックス].期日<BR>
     * 　@建株照会明細.建代金 = 建株情報[インデックス].建代金<BR>
     * 　@建株照会明細.建手数料 = 建株情報[インデックス].建手数料<BR>
     * 　@建株照会明細.順日歩 = 建株情報[インデックス].順日歩<BR>
     *   建株照会明細.逆日歩 = 建株情報[インデックス].逆日歩<BR>
     * 　@建株照会明細.貸株料 = 建株情報[インデックス].貸株料<BR>
     * 　@建株照会明細.書換料 = 建株情報[インデックス].書換料<BR>
     * 　@建株照会明細.管理費 = 建株情報[インデックス].管理費<BR>
     * 　@建株照会明細.その他 = 建株情報[インデックス].その他<BR>
     * 　@建株照会明細.評価損益 = 建株情報[インデックス].評価損益<BR>
     * 　@建株照会明細.決済状態区分 = 建株情報[インデックス].決済状態区分<BR>
     * 　@建株照会明細.返済可能フラグ = 建株情報[インデックス].返済可能フラグ<BR>
     * 　@建株照会明細.現引現渡可能フラグ = <BR>
     *              建株情報[インデックス].現引現渡可能フラグ<BR>
     * <BR>
     * 　@２－４）　@２－３）でプロパティをセットした信用取引建株照会明細<BR>
     *                オブジェクトを<BR>
     * ArrayListに追加する。<BR>
     * <BR>
     * ３）　@ArrayList.toArray( )により信用取引建株照会明細の配列を返却する。<BR>
     * <BR>
     * @@param l_contractInfoList - (建株情報一覧)<BR>
     * 信用取引建株情報の一覧<BR>
     * @@return WEB3MarginContractReferenceUnit[]
     * @@roseuid 40E4F5170260
     */
    protected WEB3MarginContractReferenceUnit[] createContractReferenceUnitListFromContractInfoList(
        WEB3MarginContractInfo[] l_contractInfoList) 
    {
        final String STR_METHOD_NAME = "createContractReferenceUnitListFromContractInfoList";
        if (l_contractInfoList == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                                 
        }
        //1　@ArrayListを生成する
        List l_lists = new ArrayList();
        //2件株要素一覧loop
        int l_intNum = 0;
        if ( l_contractInfoList != null)
        {
            l_intNum = l_contractInfoList.length;
        }
        for (int i=0; i<l_intNum; i++)
        {
            //信用取引建株照会明細オブジェクトを生成する
            WEB3MarginContractReferenceUnit  l_referenceUnit = new WEB3MarginContractReferenceUnit();
            //信用取引弁済オブジェクトを生成し
            WEB3MarginRepaymentUnit l_payUnit = new WEB3MarginRepaymentUnit();
            //set 信用取引弁済おオブジェクト
            l_payUnit.repaymentDiv = l_contractInfoList[i].repaymentType;
            l_payUnit.repaymentTimeLimit = l_contractInfoList[i].repaymentNum;
            //set 建株照会明細プロパティ
            //* 　@建株照会明細.ID = 建株情報[インデックス].ID
            l_referenceUnit.id = l_contractInfoList[i].id;
            //* 　@建株照会明細.銘柄コード = 建株情報[インデックス].銘柄コード
            l_referenceUnit.productCode = l_contractInfoList[i].productCode;
            //* 　@建株照会明細.銘柄名 = 建株情報[インデックス].銘柄名
            l_referenceUnit.productName = l_contractInfoList[i].standardName;
            //* 　@建株照会明細.市場コード = 建株情報[インデックス].市場コード
            l_referenceUnit.marketCode = l_contractInfoList[i].marketCode;
            //* 　@建株照会明細.口座区分 = 建株情報[インデックス].口座区分
            l_referenceUnit.taxType = l_contractInfoList[i].accountType;
            //* 　@建株照会明細.建区分 = 建株情報[インデックス].建区分
            l_referenceUnit.contractType = l_contractInfoList[i].contractType;
            //* 　@建株照会明細.弁済 =　@２－２）にてプロパティセットした信用取引弁済
            l_referenceUnit.repayment = l_payUnit;
            //* 　@建株照会明細.建日 = 建株情報[インデックス].建日
            l_referenceUnit.openDate = l_contractInfoList[i].openDate;
            //* 　@建株照会明細.建単価 = 建株情報[インデックス].建単価
            l_referenceUnit.contractPrice = l_contractInfoList[i].contractPrice;
            //* 　@建株照会明細.建株数 = 建株情報[インデックス].建株数>
            l_referenceUnit.contractOrderQuantity = l_contractInfoList[i].quantity;
            //* 　@建株照会明細.期日 = 建株情報[インデックス].期日
            l_referenceUnit.closeDate = l_contractInfoList[i].closeDate;
            //* 　@建株照会明細.建代金 = 建株情報[インデックス].建代金
            l_referenceUnit.contractExecPrice = l_contractInfoList[i].contractExecPrice;
            //* 　@建株照会明細.建手数料 = 建株情報[インデックス].建手数料
            l_referenceUnit.contractCommission = l_contractInfoList[i].setupFee;
            //* 　@建株照会明細.順日歩 = 建株情報[インデックス].順日歩
            l_referenceUnit.interestFee = l_contractInfoList[i].interestFee;
            //*   建株照会明細.逆日歩 = 建株情報[インデックス].逆日歩
            l_referenceUnit.payInterestFee = l_contractInfoList[i].payInterestFee;
            //* 　@建株照会明細.貸株料 = 建株情報[インデックス].貸株料
            l_referenceUnit.loanEquityFee = l_contractInfoList[i].loanEquityFee;
            //* 　@建株照会明細.書換料 = 建株情報[インデックス].書換料
            l_referenceUnit.setupFee = l_contractInfoList[i].transferFee;
            //* 　@建株照会明細.管理費 = 建株情報[インデックス].管理費
            l_referenceUnit.managementFee = l_contractInfoList[i].managementFee;
            //* 　@建株照会明細.その他 = 建株情報[インデックス].その他
            l_referenceUnit.otherwise = l_contractInfoList[i].other;
            //* 　@建株照会明細.評価損益 = 建株情報[インデックス].評価損益<BR>
            l_referenceUnit.appraisalProfitLoss = l_contractInfoList[i].takeExpensesOffEvaluationIncome;
            //* 　@建株照会明細.決済状態区分 = 建株情報[インデックス].決済状態区分
            l_referenceUnit.settlementState = l_contractInfoList[i].closingStatusType;
            //* 　@建株照会明細.返済可能フラグ = 建株情報[インデックス].返済可能フラグ
            l_referenceUnit.closeMarginFlag = l_contractInfoList[i].closingPossibleFlag;
            //* 　@建株照会明細.現引現渡可能フラグ = <BR>
            //*   建株情報[インデックス].現引現渡可能フラグ<BR>
            l_referenceUnit.swapFlag = l_contractInfoList[i].swapPossibleFlag;
            // add object
            l_lists.add(l_referenceUnit);       
        }
        //3
        WEB3MarginContractReferenceUnit[] l_referenceUnit1 = (WEB3MarginContractReferenceUnit[]) l_lists.toArray(new WEB3MarginContractReferenceUnit[l_intNum]);
        return  l_referenceUnit1;
    }
    
    /**
     * (createページ)<BR>
     * <BR>
     * 要求ページ番号に該当する信用取引建株照会明細の配列を作成する。<BR>
     * <BR>
     * １）　@ページ内表示行数、要求ページ番号の取得<BR>
     * ページ内表示行数 = 引数.リクエストデータ.ページ内表示行数<BR>
     * 要求ページ番号 = 引数.リクエストデータ.要求ページ番号<BR>
     * <BR>
     * ２）要求ページ開始位置を決定<BR>
     * fromIndex ＝ ページ内表示行数 × (要求ページ番号 - 1)<BR>
     * <BR>
     * ※但し、総レコード数が要求ページ番号に満たない場合<BR>
     * 　@(パラメータ.信用取引建株照会明細の要素数 <= fromIndex)、<BR>
     * 　@fromIndex ＝ パラメータ.信用取引建株照会明細の要素数 - ページ内表示行数<BR>
     * 　@とする。<BR>
     * <BR>
     * ３）要求ページ終了位置を決定<BR>
     * toIndex ＝ (ページ内表示行数 × 要求ページ番号) - 1<BR>
     * <BR>
     * ※但し、総レコード数が要求ページ番号の最大値に満たない場合<BR>
     * 　@(toIndex > (パラメータ.信用取引建株照会明細の要素数 - 1))、<BR>
     * 　@toIndex    ＝　@パラメータ.信用取引建株照会明細の要素数 - 1<BR>
     * 　@とする。<BR>
     * <BR>
     * ４）ArrayListを作成<BR>
     * <BR>
     * ５）fromIndex～toIndexの間、以下の処理をLoopする<BR>
     * 　@作成したArrayListにインデックス番号の信用取引建株照会明細オブジェクトを追加<BR>
     * <BR>
     * ６）　@ArrayList.toArrayで該当ページ番号の建株照会明細の一覧を返却する<BR>
     * <BR>
     * ※上記計算において、-1を行っているのは５）にて建株照会明細一覧の要素数で<BR>
     * Loop処理を行った場合に、インデックス0から開始されるため。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引建株照会リクエストオブジェクト
     * @@param l_contractReferenceUnitList - (建株照会明細一覧)<BR>
     * 信用取引建株照会明細の配列
     * @@return WEB3MarginContractReferenceUnit[]
     * @@roseuid 40E3F7370173
     */
    protected WEB3MarginContractReferenceUnit[] createPage(
        WEB3MarginContractReferenceRequest l_request, 
        WEB3MarginContractReferenceUnit[] l_contractReferenceUnitList) 
    {
        final String STR_METHOD_NAME = "create page";
        if (l_request == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                                 
        }
        if (l_contractReferenceUnitList == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                                 
        }
        //1　@ページ内表示行数、要求ページ番号の取得
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        //2 要求ページ開始位置を決定
        int l_intNum = l_contractReferenceUnitList.length;
        int l_fromIndex = l_intPageSize*(l_intPageIndex - 1);
        if (l_fromIndex >= l_intNum)
        {
            l_fromIndex = l_intNum - l_intPageSize;
        }
        //3 要求ページ終了位置を決定
        int l_intToIndex = l_intPageSize*l_intPageIndex - 1;
        if (l_intToIndex > (l_intNum -1))
        {
            l_intToIndex = l_intNum - 1;
        }
        //4ArrayListを作成
        List l_lists = new ArrayList();
        //5
        for (int i = l_fromIndex; i<= l_intToIndex; i++)
        {
            l_lists.add(l_contractReferenceUnitList[i]);
        }
        int l_intNum1 = l_lists.size();
        //6
        WEB3MarginContractReferenceUnit[] l_conRefUnit = (WEB3MarginContractReferenceUnit[]) l_lists.toArray(new WEB3MarginContractReferenceUnit[l_intNum1]);
        return l_conRefUnit;
    }
    
    /**
     * (get指定決済状態建株情報一覧)。<BR>
     * <BR>
     * 指定決済状態の建株のみの建株情報の一覧を取得する。 <BR>
     * <BR>
     * １）　@建株情報一覧ごとのLoop処理 <BR>
     * <BR>
     * 　@１－１）　@建株情報の決済状態区分を取得する。<BR>
     * <BR>
     * 　@１－２）　@引数.指定決済状態　@!=　@１－１）の決済状態区分の場合は、<BR>
     * 　@　@　@　@　@　@該当の建株情報を建株情報一覧から削除する。<BR>
     * <BR>
     * ２）　@指定決済状態のみの建株情報一覧を返却する。<BR>
     * <BR>
     * ※１）の結果、建株情報一覧のサイズが0になった場合には、nullを返却する。<BR>
     * @@param l_strCloseStatus - (指定決済状態)<BR>
     * 下記のいずれか。 <BR>
     * <BR>
     * 0：決済済 <BR>
     * 1：未決済 <BR>
     * 2：決済中 <BR>
     * @@param l_lisContractInfoList - (建株情報リスト)<BR>
     * 建株情報の一覧が格納されたリスト
     * @@return ArrayList
     * @@roseuid 40ED1E020132
     */
    protected ArrayList getCloseMarginContractInfoList(String l_strCloseStatus, List l_lisContractInfoList) 
    {
        final String STR_METHOD_NAME = "getCloseMarginContractInfoList";
        if (l_lisContractInfoList == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                                 
        }
        int l_intNum = l_lisContractInfoList.size();
        if (l_intNum ==0)
        {
            return null;
        }
        for (int i=0;i < l_intNum; i++)
        {
            WEB3MarginContractInfo l_infoList = (WEB3MarginContractInfo) l_lisContractInfoList.get(i);
            //1-1 get 決済状態
            String l_strStatus = l_infoList.closingStatusType;
            //1-2
            if (!l_strCloseStatus.equals(l_strStatus))
            {
                l_lisContractInfoList.remove(i);        
            }                
        }
        return (ArrayList) l_lisContractInfoList;
    }
    
    /**
     * (set決済可能フラグto建株情報)。<BR>
     * <BR>
     * 指定の建株情報の返済可能フラグ、および現引現渡可能フラグを設定を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引建株照会サービス）set決済可能フラグto建株情報」参照。<BR>
     * <BR>
     * @@param l_contractInfo - (建株情報)<BR>
     * 返済可能フラグ、現引現渡可能フラグをセットする対象の建株情報
     * @@param l_blnIsProductDesignate - (is銘柄指定)<BR>
     * 銘柄コードが指定されている場合、true。以外、false。<BR>
     * @@roseuid 40ED28FE03D1
     */
    protected void setCloseMarginPossibleFlagToContractInfo(
        WEB3MarginContractInfo l_contractInfo,
        boolean l_blnIsProductDesignate) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "setCloseMarginPossibleFlagToContractInfo";
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
            = (l_contractInfo.contractType.equals(ContractTypeEnum.SHORT));
        OrderTypeEnum l_OrderTypeEnumForCls
            = l_blnIsShortContract ? OrderTypeEnum.CLOSE_MARGIN_SHORT : OrderTypeEnum.CLOSE_MARGIN_LONG;
        OrderTypeEnum l_OrderTypeEnumForSwp
            = l_blnIsShortContract ? OrderTypeEnum.SWAP_MARGIN_LONG : OrderTypeEnum.SWAP_MARGIN_SHORT;
        
        
        //get補助口座（および、補助口座から証券会社コード、部店を取得）
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
        l_strInstitutionCode                = l_subAccount.getInstitution().getInstitutionCode();
        l_branch                            = l_subAccount.getWeb3GenBranch();        


        //validate取引可能顧客
        OrderValidationResult l_orderValidationResult = l_orderValidator.validateSubAccountForTrading(l_subAccount);
        l_blnIsValidTradingSubAccount = l_orderValidationResult.getProcessingResult().isSuccessfulResult();
        
        
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
                    l_contractInfo.marketCode, l_strInstitutionCode);
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
            l_blnIsValidProductCode = false;
        }
        
        
        //validateインサイダー
        try
        {
            l_orderManager.validateInsider(l_subAccount, l_product);
            l_blnIsNotInsider = true;
        }
        catch (WEB3BaseException l_wbe)
        {
            l_blnIsNotInsider = false;
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
                l_blnIsValidHandlingMarket = false;
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
        l_contractInfo.closingPossibleFlag =
            l_blnIsValidTradingSubAccount &&
            l_blnIsSwpMgnOdrAptPossible &&
            l_blnIsValidProductCode &&
            l_blnIsNotInsider &&
            l_blnIsNotStpdPdctForSwp &&
            l_blnIsValidTrddPdctForSwp &&
            l_blnIsValidHandlingMarket;
            
        log.debug("返済可能フラグ          -> [" + String.valueOf(l_contractInfo.closingPossibleFlag) + "]");
        log.debug("現引・現渡可能フラグ    -> [" + String.valueOf(l_contractInfo.closingPossibleFlag) + "]");

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (sort建株情報一覧)。<BR>
     * <BR>
     * 指定されたソートキー、昇降順にもどついて建株情報の配列のソートを行う。<BR>
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
     * <BR>
     * 　@　@・キー項目が建区分であった場合、信用建区分Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が弁済区分であった場合、信用弁済区分Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が弁済期限値であった場合、信用弁済期限値Comparatorを生<BR>
     * 成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が建日であった場合、信用建日Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が期日であった場合、信用期日Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が評価損益であった場合、信用評価損益Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@２－４）　@２－３）にて作成したComparatorをArrayListに追加<BR>
     * <BR>
     * ３）　@ArrayListからComparatorの配列を作成<BR>
     * <BR>
     * ４）　@Comparatorの配列順に引数.建株情報をソート<BR>
     * (web3-common)WEB3ArraysUtility.sort(引数.建株情報一覧、<BR>
     * ３）で作成したComparator[])<BR>
     * <BR>
     * @@param l_contractInfoList - (建株情報一覧)<BR>
     * 信用取引建株情報の一覧<BR>
     * @@param l_sortKey - (ソートキー)<BR>
     * 信用取引ソートキーの一覧<BR>
     * @@roseuid 40E3FA6A019D
     */
    protected void sortContractInfoList(WEB3MarginContractInfo[] l_contractInfoList, WEB3MarginSortKey[] l_sortKey) 
    {
        final String STR_METHOD_NAME = "sortContractInfoList";
        //1ArrayListを作成
        List l_infoList = new ArrayList();
        //2引数.ソートキーの配列数分Loop処理
        if (l_sortKey == null)
        {   
            log.error("parameter is null type");            
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                                 
        }
        int l_intNum = l_sortKey.length;
        for  (int i=0; i < l_intNum; i++)
        {
            //２－１）　@引数.ソートキー.キー項目を取得
            String l_strKeyItem = l_sortKey[i].keyItem;
            //２－２）　@引数.ソートキー.昇順/降順を取得
            String l_strAD = l_sortKey[i].ascDesc;
            //２－３）　@キー項目による分岐処理
            // ２－４）　@２－３）にて作成したComparatorをArrayListに追加
            //キー項目が銘柄コードであった場合、信用銘柄コードComparatorを生成<BR>
            if (WEB3EquityKeyItemDef.PRODUCTCODE.equals(l_strKeyItem))
            {
                WEB3MarginProductCodeComparator l_product = new WEB3MarginProductCodeComparator(l_strAD);                
                l_infoList.add(l_product);
            }
            //キー項目が市場コードであった場合、信用市場コードComparatorを生成
            else if (WEB3EquityKeyItemDef.TRADEMARKET.equals(l_strKeyItem))
            {
                WEB3MarginMarketCodeComparator l_market = new WEB3MarginMarketCodeComparator(l_strAD);    
                l_infoList.add(l_market);
            }
            //キー項目が口座区分であった場合、信用口座区分Comparatorを生成
            else if (WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(l_strKeyItem))
            {
                WEB3MarginAccountTypeComparator l_account = new WEB3MarginAccountTypeComparator(l_strAD);     
                l_infoList.add(l_account);
            }
            //キー項目が建区分であった場合、信用建区分Comparatorを生成
            else if (WEB3EquityKeyItemDef.CONTRACT_DIVISION.equals(l_strKeyItem))
            {
                WEB3MarginContractTypeComparator l_contract = new WEB3MarginContractTypeComparator(l_strAD);    
                l_infoList.add(l_contract);
            }
            //キー項目が弁済区分であった場合、信用弁済区分Comparatorを生成
            else if (WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(l_strKeyItem))
            {
                WEB3MarginRepaymentTypeComparator l_repayment = new WEB3MarginRepaymentTypeComparator(l_strAD);    
                l_infoList.add(l_repayment);
            }
            //キー項目が弁済期限値であった場合、信用弁済期限値Comparatorを生
            else if (WEB3EquityKeyItemDef.REPAYMENTNUM.equals(l_strKeyItem))
            {
                WEB3MarginRepaymentNumComparator l_num = new WEB3MarginRepaymentNumComparator(l_strAD);   
                l_infoList.add(l_num);
            }
            //キー項目が建日であった場合、信用建日Comparatorを生成
            else if (WEB3EquityKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {
                WEB3MarginOpenDateComparator l_openDate = new WEB3MarginOpenDateComparator(l_strAD);    
                l_infoList.add(l_openDate);
            }
            //キー項目が期日であった場合、信用期日Comparatorを生成
            else if (WEB3EquityKeyItemDef.CLOSEDATE.equals(l_strKeyItem))
            {
                WEB3MarginCloseDateComparator l_closeDate = new  WEB3MarginCloseDateComparator(l_strAD);    
                l_infoList.add(l_closeDate);
            }
            //キー項目が評価損益であった場合、信用評価損益Comparatorを生成
            else if (WEB3EquityKeyItemDef.INCOME.equals(l_strKeyItem))
            {
                WEB3MarginContractUnitAppraisalProfitOrLossComparator l_income = new WEB3MarginContractUnitAppraisalProfitOrLossComparator(l_strAD);    
                l_infoList.add(l_income);
            }              
        }
        //３）ArrayListからComparatorの配列を作成<BR>
        int l_intNum1 = l_infoList.size();
        Comparator[] l_objects = (Comparator[]) l_infoList.toArray(new Comparator[l_intNum1]);
        //４）Comparatorの配列順に引数.建株情報をソート
        WEB3ArraysUtility.sort(l_contractInfoList,l_objects); 
    }
}
@
