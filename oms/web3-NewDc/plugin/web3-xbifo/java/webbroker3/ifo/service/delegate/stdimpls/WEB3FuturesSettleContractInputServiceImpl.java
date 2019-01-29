head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesSettleContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済入力サービス実装(WEB3FuturesSettleContractInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/23 鄒鋭 (中訊) 新規作成
                 : 2006/07/28 肖志偉 (中訊) 仕様変更　@モデル486
Revesion History : 2007/06/21 張騰宇(中訊) モデル713、717、721、722、724、729
Revesion History : 2007/11/20 周墨洋 (中訊) 仕様変更 モデル799
Revesion History : 2007/11/28 周墨洋 (中訊) 仕様変更 Javaソース（基本設計と合っていない実装）No.010
Revesion History : 2008/03/12 張騰宇(中訊) モデル827 834 839 863
Revesion History : 2008/04/14 張騰宇(中訊) モデル872
Revesion History : 2008/08/18 劉剣 (中訊) IFO小数点対応
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.define.WEB3IfoSettlementStateDef;
import webbroker3.ifo.message.WEB3FuturesCloseMarginGroup;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginListRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginListResponse;
import webbroker3.ifo.message.WEB3FuturesContractDivisionComparator;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOpenDateComparator;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3FuturesProductCodeComparator;
import webbroker3.ifo.message.WEB3FuturesProfitAndLossComparator;
import webbroker3.ifo.message.WEB3FuturesProfitAndLossCostComparator;
import webbroker3.ifo.message.WEB3FuturesSessionTypeComparator;
import webbroker3.ifo.message.WEB3FuturesSettlementStatusTypeComparator;
import webbroker3.ifo.message.WEB3FuturesContractPriceComparator;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractInputService;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (先物返済入力サービスImpl)<BR>
 * 株価指数先物返済入力サービス実装クラス
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesSettleContractInputServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesSettleContractInputService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesSettleContractInputServiceImpl.class);

    /**
     * @@roseuid 40F7A2CD003E
     */
    public WEB3FuturesSettleContractInputServiceImpl()
    {

    }

    /**
     * 株価指数先物返済入力サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、get返済一覧()または、<BR>
     * get返済入力画面()メソッドのいずれかをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A9699B0168
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3FuturesCloseMarginListRequest)
        {
            l_response = this.getCloseMarginList((WEB3FuturesCloseMarginListRequest) l_request);
        }
        else if (l_request instanceof WEB3FuturesCloseMarginInputRequest)
        {
            l_response = this.getColseMarginInput((WEB3FuturesCloseMarginInputRequest) l_request);
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get返済一覧)<BR>
     * 株価指数先物の返済一覧画面表示サービスを実施する。<BR>
     * <BR>
     * 「（先物返済入力）get返済一覧」参照。
     * ==========================================================<BR>
     * シーケンス図(「（先物返済入力）get返済一覧」):<BR>
     *  6 get銘柄(Institution, String, String, String, double, String, String)<BR>
     * (銘柄コードチェック)<BR>
     * get銘柄で指定の銘柄コードが取得できなかった場合は、<BR>
     * 「銘柄コードチェックエラー」の例外をthrowする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00301<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数先物返済一覧画面表示リクエストオブジェクト
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginListResponse
     * @@roseuid 40A9699B0187
     */
    protected WEB3FuturesCloseMarginListResponse getCloseMarginList(WEB3FuturesCloseMarginListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCloseMarginList(WEB3FuturesCloseMarginListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();

        WEB3FuturesCloseMarginListResponse l_response = (WEB3FuturesCloseMarginListResponse) l_request.createResponse();

        //補助口座を取得する。
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        //返済注文受付審査を実施する。
        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();
        l_orderMgr.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);

        Institution l_institution = l_subAccount.getInstitution();
        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
        WEB3IfoProductImpl l_ifoProduct = null;

        //リクエストデータに銘柄コードがセットされている場合
        if (l_request.futProductCode != null)
        {
            try
            {
                //銘柄オブジェクトを取得する。
                l_ifoProduct = l_productMgr.getIfoProduct(
                    l_institution,
                    l_request.futProductCode);
                    
                 if (l_ifoProduct == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (NotFoundException l_nfex)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //リクエストデータに銘柄特定項目(取引市場、指数種別、限月)が設定されている場合
        if (l_request.marketCode != null &&
            l_request.targetProductCode != null &&
            l_request.delivaryMonth != null)
        {
            IfoDerivativeTypeEnum l_ifoDerivativeTypeEnum = null;
            l_ifoDerivativeTypeEnum = IfoDerivativeTypeEnum.FUTURES;
            
            //銘柄オブジェクトを取得する。
            try
            {
                l_ifoProduct = l_productMgr.getIfoProduct(
                    l_institution,
                    l_request.targetProductCode,
                    l_request.delivaryMonth,
                    l_ifoDerivativeTypeEnum,
                    0,
                    WEB3DivisionTypeDef.DIVISION_DEFAULT,
                    l_request.marketCode);
                    
                if (l_ifoProduct == null)
               {
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                       this.getClass().getName() + "." + STR_METHOD_NAME);
               }
           }
           catch (NotFoundException l_nfex)
           {
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                   this.getClass().getName() + "." + STR_METHOD_NAME);
           }
        }
        
        //市場閉局間近の指数を配列で取得する。
        String[] l_closeSuspensions = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.FUTURES);

        //指定口座の保持する未決済建玉の銘柄コードと銘柄名の一覧を取得する。
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl) l_tradingModule.getPositionManager();
        WEB3FuturesOptionsProductCodeNameUnit[] l_productCodeNameUnits = l_positionManager.createProductCodeNameFromContract(l_subAccount, true, WEB3FuturesOptionDivDef.FUTURES);

        //create銘柄コード名称from建玉の返り値がNULLの場合、レスポンス作成、
        //プロパティセット処理を行う。
        if (l_productCodeNameUnits == null)
        {
            l_response.closeMarginGroups = null; //株価指数オプション返済一覧行
            l_response.totalPages = "0"; //総ページ数
            l_response.totalRecords = "0"; //総レコード数
            l_response.pageIndex = "0"; //表示ページ番号
            l_response.messageSuspension = l_closeSuspensions; //取引終了警告文言
            //l_response.opProductCodeNames = null; //株価指数先物オプション銘柄コード名称
            l_response.futOpProductCodeNames = null; //株価指数先物オプション銘柄コード名称
            return l_response;
        }
        //検索条件(銘柄コード指定)の作成
        String l_strQueryString = this.createSearchCondCharacter(l_ifoProduct);
        String[] l_strQueryContainer = this.createSearchCondDataContainer(l_ifoProduct);

        WEB3FuturesContractReferenceUnit[] l_referenceUnits =
            l_positionManager.createFuturesContractInquiryDetails(l_subAccount, WEB3FuturesOptionDivDef.FUTURES, null, l_strQueryString, l_strQueryContainer);

        //create建玉照会明細の返り値がNULLの場合、
        //レスポンス作成、プロパティセット処理を行う。
        if (l_referenceUnits == null)
        {
            l_response.closeMarginGroups = null; //株価指数オプション返済一覧行
            l_response.totalPages = "0"; //総ページ数
            l_response.totalRecords = "0"; //総レコード数
            l_response.pageIndex = "0"; //表示ページ番号
            l_response.messageSuspension = l_closeSuspensions; //取引終了警告文言
            l_response.futOpProductCodeNames = l_productCodeNameUnits; //株価指数先物オプション銘柄コード名称
            return l_response;
        }

        List l_lstReferenceUnits = new ArrayList();

        //建玉照会明細要素ごとのLoop処理, (返済一覧作成対象の建玉照会明細作成処理)
        log.debug("l_referenceUnits.length = " + l_referenceUnits.length);
        for (int i = 0; i < l_referenceUnits.length; i++)
        {
            //指定された銘柄が該当顧客の会社・部店で取扱可能な銘柄であるかを判定する。
            boolean l_blnIsInstHandlingProduct = this.isPertinentInstDealtProduct(l_subAccount, l_referenceUnits[i].futProductCode);
            log.debug("l_blnIsInstHandlingProduct = " + l_blnIsInstHandlingProduct);
            if (l_blnIsInstHandlingProduct && !WEB3IfoSettlementStateDef.SETTLEMENT_END.equals(l_referenceUnits[i].settlementState))
            {
                log.debug("l_referenceUnits[i] = " + l_referenceUnits[i]);

                l_lstReferenceUnits.add(l_referenceUnits[i]);
            }
        }

        //返済一覧作成対象の建玉照会明細作成処理によって建玉照会明細要素数が0になった場合
        if (l_lstReferenceUnits.size() == 0)
        {
            l_response.closeMarginGroups = null; //株価指数オプション返済一覧行
            l_response.totalPages = "0"; //総ページ数
            l_response.totalRecords = "0"; //総レコード数
            l_response.pageIndex = "0"; //表示ページ番号
            l_response.messageSuspension = l_closeSuspensions; //取引終了警告文言
            l_response.futOpProductCodeNames = l_productCodeNameUnits; //株価指数先物オプション銘柄コード名称
            return l_response;
        }

        //株価指数先物オプションソートキー配列を作成する。
        WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[4];

        //株価指数先物オプションソートキー[0].キー項目 = 銘柄コード
        //株価指数先物オプションソートキー[0].昇順／降順 = "昇順"
        l_sortKeys[0] = new WEB3FuturesOptionsSortKey();
        l_sortKeys[0].keyItem = WEB3IfoKeyItemDef.FUTPRODUCTCODE;
        l_sortKeys[0].ascDesc = WEB3AscDescDef.ASC;

        //株価指数先物オプションソートキー[1].キー項目 = 建区分
        //株価指数先物オプションソートキー[1].昇順／降順 = "昇順"
        l_sortKeys[1] = new WEB3FuturesOptionsSortKey();
        l_sortKeys[1].keyItem = WEB3IfoKeyItemDef.CONTRACT_DIVISION;
        l_sortKeys[1].ascDesc = WEB3AscDescDef.ASC;

        //株価指数先物オプションソートキー[2].キー項目 = 決済状態区分
        //株価指数先物オプションソートキー[2].昇順／降順 = "昇順"
        l_sortKeys[2] = new WEB3FuturesOptionsSortKey();
        l_sortKeys[2].keyItem = WEB3IfoKeyItemDef.SETTLEMENT_STATUS_DIVISION;
        l_sortKeys[2].ascDesc = WEB3AscDescDef.ASC;

        //株価指数先物オプションソートキー[3].キー項目 = 建日
        //株価指数先物オプションソートキー[3].昇順／降順 = "昇順"
        l_sortKeys[3] = new WEB3FuturesOptionsSortKey();
        l_sortKeys[3].keyItem = WEB3IfoKeyItemDef.OPEN_DATE;
        l_sortKeys[3].ascDesc = WEB3AscDescDef.ASC;

        WEB3FuturesContractReferenceUnit[] l_returnReferenceUnits = new WEB3FuturesContractReferenceUnit[l_lstReferenceUnits.size()];
        l_lstReferenceUnits.toArray(l_returnReferenceUnits);
        //建玉照会明細のソート処理を行う。
        l_returnReferenceUnits = this.sortContractReferenceUnit(l_returnReferenceUnits, l_sortKeys);

        //株価指数先物返済一覧行の配列を作成する。
        WEB3FuturesCloseMarginGroup[] l_closeMarginGroups = this.createCloseMarginList(l_returnReferenceUnits);

        //返済一覧行のソート処理を行う。
        l_closeMarginGroups = this.sortCloseMarginListLine(l_closeMarginGroups, l_request.futOpSortKeys);

        //要求ページ番号の返済一覧行配列を作成する。
        WEB3FuturesCloseMarginGroup[] l_closeMarginGroupsPage = createPage(l_request, l_closeMarginGroups);

        l_response.closeMarginGroups = l_closeMarginGroupsPage; //株価指数オプション返済一覧行
        int l_intPageSize = Integer.parseInt(l_request.pageSize); //ページ内表示行数
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex); //要求ページ番号
        int l_intTotalRecords = l_closeMarginGroups.length; //総レコード数

        //総ページ数 = 総レコード数 ÷ リクエストデータ.ページ内表示行数 ※計算結果が1未満、または余りが存在する場合には更に1を加算する
        int l_intTotalPages = (int) Math.ceil(l_intTotalRecords / l_intPageSize);
        if (l_intTotalPages < 1 || l_intTotalRecords % l_intPageSize > 0)
        {
            l_intTotalPages = l_intTotalPages + 1;
        }
        //※レスポンス.総レコード数 <= (リクエストデータ.ページ内表示行数 * (リクエストデータ.要求ページ番号 - 1))の場合は、レスポンス.総ページ数をセット(最終ページ番号をセット)
        if (l_intTotalRecords <= l_intPageSize * (l_intPageIndex - 1))
        {
            l_intPageIndex = l_intTotalPages;
        }

        l_response.totalPages = "" + l_intTotalPages; //総ページ数
        l_response.totalRecords = "" + l_intTotalRecords; //総レコード数
        l_response.pageIndex = "" + l_intPageIndex; //表示ページ番号
        l_response.messageSuspension = l_closeSuspensions; //取引終了警告文言
        l_response.futOpProductCodeNames = l_productCodeNameUnits; //株価指数先物オプション銘柄コード名称


        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get返済入力)<BR>
     * 株価指数先物の返済入力画面表示サービスを実施する。<BR>
     * <BR>
     * 「（先物返済入力）get返済入力画面」参照。<BR>
     * ==========================================================<BR>
     * シーケンス図(「（先物返済入力）get返済入力画面」): 4 (*2)<BR>
     * (*2)分岐フロー<BR>
     * 注文数量 = リクエストデータ.注文数量とする<BR>
     * 注文数量がNULLでない場合のみ下記返済可能数量チェックを実施する。<BR>
     *<BR>
     * （返済可能数量チェック)<BR>
     * 注文数量 > 返済可能数量の場合、<BR>
     * 「返済可能数量不足エラー」の例外をthrowする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00303<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3FuturesCloseMarginInputResponse
     * @@roseuid 40A9699B0197
     */
    protected WEB3FuturesCloseMarginInputResponse getColseMarginInput(WEB3FuturesCloseMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getColseMarginInput(WEB3FuturesCloseMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();

        WEB3FuturesCloseMarginInputResponse l_response = (WEB3FuturesCloseMarginInputResponse) l_request.createResponse();

        try
        {
            //get建玉(株価指数先物返済入力画面リクエスト)
            WEB3IfoContractImpl l_contract = getContract(l_request);
            //reset銘柄コード
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl)l_contract.getProduct();
            WEB3GentradeTradingTimeManagement.resetProductCode(l_product.getUnderlyingProductCode());
            //補助口座を取得する。
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

            //返済注文受付審査を実施する。
            WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();
            l_orderMgr.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);


            Market l_market = l_finObjMgr.getMarket(l_contract.getMarketId());
            Institution l_institution = l_subAccount.getInstitution();

            //銘柄、取引市場チェックを実施する。
            WEB3IfoOrderManagerReusableValidations l_validations = (WEB3IfoOrderManagerReusableValidations) WEB3IfoOrderManagerReusableValidations.getInstance();

            WEB3GentradeMarket l_gentradeMarket = (WEB3GentradeMarket)l_validations.validateMarket(l_market.getMarketCode(), l_institution.getInstitutionCode());

            //取引、可能銘柄チェックを実施する。
            WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product0 = (WEB3IfoProductImpl) l_productMgr.getProduct(l_contract.getProduct().getProductId());
            l_validations.validateProductCode(l_product0.getProductCode(), //銘柄コード
            l_institution.getInstitutionCode() //証券会社コード
            );

            //取引銘柄のチェックを行い、先物OP取引銘柄オブジェクトを返却する。
            WEB3IfoTradedProductImpl l_tradedProductImpl = l_validations.validateTradedProduct(l_product0,l_gentradeMarket,l_contract.isLong(),false);           
            Date l_datLastTradingDate = l_tradedProductImpl.getLastTradingDate();
            
            //市場閉局間近の指数を配列で取得する。
            String[] l_closeSuspensions = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.FUTURES);

            //create建玉照会明細一覧(株価指数先物返済入力画面リクエスト)
            WEB3FuturesContractReferenceUnit[] l_contractReferenceUnits =
                createContractReferenceUnitList(l_request);

            List l_lstContractUnits = new ArrayList();
            int l_intCloseMarginAvailableQuantity = 0;

            for (int i = 0; i < l_contractReferenceUnits.length; i++)
            {
                //指定の建玉照会明細から建玉明細を作成する。
                WEB3FuturesOptionsContractUnit l_contractUnit = this.createContractUnit(l_contractReferenceUnits[i]);

                //返済可能数量のセット
                l_intCloseMarginAvailableQuantity += Double.parseDouble(l_contractUnit.contractQuantity);

                l_lstContractUnits.add(l_contractUnit);
            }

            WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[l_lstContractUnits.size()];
            l_lstContractUnits.toArray(l_contractUnits);
            log.debug("l_contractUnits = " + l_contractUnits);
            if (l_contractUnits.length > 1) //一括返済の場合
            {
                //注文数量がNULLでない場合のみ下記返済可能数量チェックを実施する。
                if (l_request.futOrderQuantity != null)
                {
                    int l_intOrderQuantity = Integer.parseInt(l_request.futOrderQuantity);

                    if (l_intOrderQuantity > l_intCloseMarginAvailableQuantity) //注文数量 > 返済可能数量の場合
                    {
                        throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00303, this.getClass().getName() + STR_METHOD_NAME);
                    }
                }

                //sort建玉明細一覧(建玉明細[], 株価指数先物返済入力画面リクエスト)
                sortContractUnitList(l_contractUnits, l_request);
                
                double l_intOrderQuantity = 0D;
                if (WEB3StringTypeUtility.isNumber(l_request.futOrderQuantity))
                {
                    l_intOrderQuantity = Double.parseDouble(l_request.futOrderQuantity);
                }
            
                for (int i = 0; i < l_contractUnits.length; i++)
                {
                    //返済数量、決済順位のプロパティ再セット処理

                    double l_dblCloseMarginQuantity = 0;
                    double l_dblContractQuantity = Double.parseDouble(l_contractUnits[i].contractQuantity);

                    if (l_request.futOrderQuantity == null)
                    {
                        //注文数量がNULLの場合
                        //返済数量 = 建玉明細.建玉数
                        l_dblCloseMarginQuantity = l_dblContractQuantity;
                    }
                    else
                    {

                        if (l_intOrderQuantity >= l_dblContractQuantity)
                        {
                            //返済数量 = 建玉明細.建玉数
                            l_dblCloseMarginQuantity = l_dblContractQuantity;
                        }
                        else
                        {
                            //返済数量 = 注文数量
                            l_dblCloseMarginQuantity = l_intOrderQuantity;
                        }

                        //注文数量 = 注文数量 - 返済数量
                        l_intOrderQuantity -= l_dblCloseMarginQuantity;

                    }

                    //建玉明細.返済数量 = 返済数量
                    l_contractUnits[i].contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblCloseMarginQuantity);

                    //建玉明細.決済順位 = 建玉明細要素のインデックス番号 + 1
                    l_contractUnits[i].settlePriority = "" + (i + 1);
                }
            }

            //取扱可能注文条件オブジェクトを生成する。
            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(l_institution.getInstitutionCode(), //証券会社コード
                ProductTypeEnum.IFO, //銘柄タイプ
                WEB3FuturesOptionDivDef.FUTURES, //先物／オプション区分
                WEB3MarginTradingDivDef.DEFAULT); //信用取引区分

            //set取引最終日(取引最終日 : Date)
            l_handlingOrderCond.setTradingEndDate(l_datLastTradingDate);

            //レスポンスデータにプロパティをセットする。

            //<株価指数オプション共通入力レスポンス部分>

            //レスポンス.注文単価区分一覧 = 取扱可能注文単価区分取得の返り値
            if(WEB3IfoContractTypeDef.OPEN_BUY.equals(l_contractReferenceUnits[0].contractType))
            {
                l_response.orderPriceDivList = l_handlingOrderCond.getHandlingPossibleOrderPriceDiv(false, true);
            }
            if(WEB3IfoContractTypeDef.OPEN_SELL.equals(l_contractReferenceUnits[0].contractType))
            {
                l_response.orderPriceDivList = l_handlingOrderCond.getHandlingPossibleOrderPriceDiv(false, false);
            }

            //レスポンス.執行条件一覧 = 取扱可能執行条件取得の返り値
            String[] l_strPossibleExecCond = l_handlingOrderCond.getHandlingPossibleExecCond();
            l_strPossibleExecCond = l_orderMgr.getHandlingPossibleExecConds(l_response.orderPriceDivList,l_strPossibleExecCond);
            l_response.execCondList = l_strPossibleExecCond;

            //レスポンス.発注条件一覧 = 取扱可能発注条件取得の返り値
            l_response.orderCondTypeList = l_handlingOrderCond.getHandlingPossibleOrderCond();

            //レスポンス.注文期限区分一覧 = 取扱可能注文期限区分取得の返り値
            l_response.expirationDateTypeList = l_handlingOrderCond.getHandlingPossibleExpirationDateType();

            //is出来るまで注文取扱可能<取引最終日考慮>の返り値がtrueの場合
            if (l_handlingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering())
            {
                //レスポンス.有効期限開始日 = get出来るまで注文開始日<取引最終日考慮>の返り値
                //get出来るまで注文開始日<取引最終日考慮>(出来るまで注文開始日 : Date)
                //売買最終日を考慮した出来るまで注文開始日を取得する。 
                //[引数]  
                //出来るまで注文開始日：　@null
                l_response.expirationStartDate =
                    l_handlingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(null);

                //レスポンス.有効期限最終日 = get出来るまで注文最終日<取引最終日考慮>の返り値
                //get出来るまで注文最終日<取引最終日考慮>(原注文発注日 : Date)
                //売買最終日を考慮した出来るまで注文最終日を取得する。 
                //[引数]  
                //出来るまで注文開始日：　@null
                l_response.expirationEndDate =
                    l_handlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(null);

                //レスポンス.有効期限内祝日一覧 = get注文期限内祝日一覧の返り値
                Date[] l_datHoliday = l_handlingOrderCond.getExpirationDateHoliday();    
                l_response.holidayList = l_datHoliday;
            }
            else
            {
                //レスポンス.有効期限開始日 = null
                l_response.expirationStartDate = null;

                //レスポンス.有効期限最終日 = null
                l_response.expirationEndDate = null;

                //レスポンス.有効期限内祝日一覧 = null
                l_response.holidayList = null;
            }
            
            //W指値用の執行条件一覧を取得する。
            String[] l_strWLimitExecutionConditionTypeList = 
                WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(l_strPossibleExecCond,
                    l_handlingOrderCond.getHandlingPossibleOrderCond());
            
            //レスポンス.W指値用執行条件一覧 = getW指値用執行条件一覧()の戻り値
            l_response.wlimitExecCondList = l_strWLimitExecutionConditionTypeList;

            //レスポンス.立会区分 = 取引時間管理.get立会区分の戻り値
            l_response.sessionType = WEB3GentradeTradingTimeManagement.getSessionType();

            //<株価指数オプション返済入力画面レスポンス部分>

            //レスポンス.取引区分 = 建玉照会明細[0].建区分が"買建"ならば"買建返済注文(売返済）"を、"売建"ならば"売建返済注文(買返済）"を設定
            if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_contractReferenceUnits[0].contractType))
            {
                l_response.tradingType = "" + OrderTypeEnum.CLOSE_MARGIN_LONG.intValue();
            }
            else
            {
                l_response.tradingType = "" + OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue();
            }

            //レスポンス.取引市場 = 建玉照会明細[0].取引市場
            l_response.marketCode = l_contractReferenceUnits[0].marketCode;

            //レスポンス.指数種別 = 建玉照会明細[0].指数種別
            l_response.targetProductCode = l_contractReferenceUnits[0].targetProductCode;

            //レスポンス.限月 = 建玉照会明細[0].限月
            l_response.delivaryMonth = l_contractReferenceUnits[0].delivaryMonth;

            FinApp finApp = (FinApp) Services.getService(FinApp.class);
            WEB3QuoteDataSupplierService l_quoteDataSupplierService = (WEB3QuoteDataSupplierService) finApp.getTradingModule(ProductTypeEnum.IFO).getQuoteDataSupplierService();

            WEB3GentradeMainAccount l_gentradeMainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

            WEB3IfoQuoteData l_quoteData = null;
            if (l_gentradeMainAccount.isRealCustomer())
            {
                l_quoteData = l_quoteDataSupplierService.getIfoQuote(l_tradedProductImpl, RealType.REAL);
            }
            else
            {
                l_quoteData = l_quoteDataSupplierService.getIfoQuote(l_tradedProductImpl, RealType.DELAY);
            }

            double l_dblCurrentPrice = l_quoteData.getCurrentPrice();
            //getCurrentPriceの返り値                                   
            //時価が0の場合、nullを設定する                                 
            if (l_dblCurrentPrice == 0D)                                    
            {                                   
                l_response.currentPrice = null;                                       
            }                                   
            else                                    
            {                                   
                //時価が0でない場合、取得した現在値を設定する                                     
                l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);                                      
            }                                   

            //レスポンス.取引時間 = getCurrentPriceTimeの返り値(返り値がNULLの場合は、NULLを設定)
            l_response.currentPriceTime = l_quoteData.getCurrentPriceTime();

            log.debug("取引時間  = " + l_quoteData.getCurrentPriceTime());
            //レスポンス.前日比 = getChangeの返り値
            double l_dblChange = l_quoteData.getChange();

            l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);

            log.debug("前日比  = " + l_dblChange);
            //レスポンス.建玉明細 =（*上記で編集した建玉明細の配列）
            l_response.contractUnits = l_contractUnits;

            log.debug("建玉明細  = " + l_contractUnits[0].contractExecPrice);
            //レスポンス.取引終了警告文言 = get市場閉局警告指数の返り値
            l_response.messageSuspension = l_closeSuspensions;

            log.debug("取引終了警告文言  = " + l_closeSuspensions);
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。", l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get建玉)<BR>
     * 建玉を取得し返却する。<BR> 
     * <BR>
     * ※処理の詳細はシーケンス図「（先物返済入力）get返済入力画面１」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 先物返済入力リクエストデータオブジェクト。<BR>
     * @@return WEB3IfoContractImpl
     * @@throws WEB3BaseException
     */
    protected WEB3IfoContractImpl getContract(WEB3FuturesCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContract(WEB3FuturesCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoPositionManagerImpl l_positionManager =
            (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
        WEB3IfoContractImpl l_ifoContract = null;
        try
        {
            //リクエスト.返済建玉[0].IDに該当する建玉
            l_ifoContract = (WEB3IfoContractImpl)l_positionManager.getContract(Long.parseLong(l_request.id[0]));
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_ifoContract;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * リクエストデータをもとに、検索条件（where以下指定の文字列）を作成する。<BR>
     * <BR>
     * (1)戻り値となる文字列のインスタンスを生成<BR>
     * <BR>
     * (2)パラメータ.先物OP銘柄≠NULL（銘柄指定）の場合、<BR>
     * 　@　@銘柄ID指定を文字列インスタンスに追加（銘柄IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@" and product_id = ?"<BR>
     * <BR>
     * (3)文字列インスタンスを返却<BR>
     * （パラメータ.先物OP銘柄＝NULLの場合、NULLを返却する）<BR>
     * @@param l_ifoProduct - 先物OP銘柄オブジェクト
     * @@return String
     * @@roseuid 40A9699B01A7
     */
    protected String createSearchCondCharacter(WEB3IfoProductImpl l_ifoProduct)
    {
        final String STR_METHOD_NAME = "createSearchCondCharacter(String l_strProductCode)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.銘柄コード＝NULLの場合、NULLを返却する
        if (l_ifoProduct == null)
        {
            return null;
        }

        String l_strQueryString = " and product_id = ? ";

        log.exiting(STR_METHOD_NAME);

        return l_strQueryString;
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * リクエストデータから、検索条件（where以下指定の文字列）のパラメータリストを作成する。<BR>
     * <BR>
     * (1)文字列配列を作成<BR>
     * <BR>
     * (2)パラメータ.先物OP銘柄≠NULL（銘柄指定）の場合、<BR>
     * 　@　@銘柄IDを文字列配列にセット（銘柄IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@銘柄ID ＝ パラメータ.先物OP銘柄.銘柄ID<BR>
     * <BR>
     * (3)文字列配列を返却<BR>
     * （パラメータ.先物OP銘柄＝NULLの場合、NULLを返却する）<BR>
     * @@param l_ifoProduct - 先物OP銘柄オブジェクト
     * @@return String[]
     * @@roseuid 40A9699B01C6
     */
    protected String[] createSearchCondDataContainer(WEB3IfoProductImpl l_ifoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSearchCondDataContainer(String l_strProductCode)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.先物OP銘柄オブジェクト＝NULLの場合、NULLを返却する
        if (l_ifoProduct == null)
        {
            return null;
        }

        String[] l_strQueryContainer = new String[] { "" + l_ifoProduct.getProductId()};

        log.exiting(STR_METHOD_NAME);

        return l_strQueryContainer;

    }

    /**
     * (createページ)<BR>
     * 要求ページ番号に該当する株価指数先物返済一覧行の配列を<BR>
     * 作成する。<BR>
     * <BR>
     * (1)ページ内表示行数、要求ページ番号の取得<BR>
     * ページ内表示行数 = パラメータ.リクエストデータ.ページ内表示行数<BR>
     * 要求ページ番号 = パラメータ.リクエストデータ.要求ページ番号<BR>
     * <BR>
     * (1)要求ページ開始位置を決定<BR>
     * fromIndex = ページ内表示行数 * (要求ページ番号 - 1)<BR>
     * <BR>
     * (2)要求ページ終了位置を決定<BR>
     * toIndex = (ページ内表示行数 * 要求ページ番号) - 1<BR>
     * <BR>
     * ※パラメータ.返済一覧行の要素数 <= fromIndexの場合、<BR>
     * (総レコード数が要求ページ番号に満たない場合)<BR>
     * fromIndex =　@パラメータ.返済一覧行の要素数 - ページ内表示行数<BR>
     * toIndex = パラメータ.返済一覧行の要素数 - 1<BR>
     * <BR>
     * (3)ArrayListを作成<BR>
     * <BR>
     * (4)パラメータ.返済一覧行数分Loop処理<BR>
     * <BR>
     * 返済一覧行のインデックスが<BR>
     * fromIndexとtoIndexの範囲内(fromIndex以上、toIndex以下)<BR>
     * である場合は、作成したArrayListに返済一覧行オブジェクトを追加<BR>
     * <BR>
     * (5)ArrayList.toArrayで該当ページ番号の返済一覧行の配列を返却する
     * @@param l_request - (リクエストデータ)<BR>
     * 株式指数先物返済一覧画面表示リクエストオブジェクト
     * @@param l_closeMarginListLine - (返済一覧行)<BR>
     * 株価指数先物返済一覧行の配列<BR>
     * (総レコード数分の配列)
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginGroup[]
     * @@roseuid 40A9699B01D6
     */
    protected WEB3FuturesCloseMarginGroup[] createPage(WEB3FuturesCloseMarginListRequest l_request, WEB3FuturesCloseMarginGroup[] l_closeMarginListLine)
    {
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_closeMarginListLine, 
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));
                       
         return (WEB3FuturesCloseMarginGroup[])l_pageIndexInfo.getArrayReturned(WEB3FuturesCloseMarginGroup.class);
    }

    /**
     * (create返済一覧)<BR>
     * 指定の建玉照会明細の配列から株価指数先物返済一覧行の<BR>
     * 配列を作成する。<BR>
     * <BR>
     * 「（先物返済入力）create返済一覧」参照。<BR>
     * @@param l_contractReferenceUnit - (建玉照会明細)<BR>
     * 株価指数先物建玉照会明細の配列
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginGroup[]
     * @@roseuid 40A9699B0214
     */
    protected WEB3FuturesCloseMarginGroup[] createCloseMarginList(WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCloseMarginList(WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit)";
        log.entering(STR_METHOD_NAME);

        List l_lstCloseMarginGroups = new ArrayList();
        List l_lstContractUnits = new ArrayList();

        log.debug("l_contractReferenceUnit = " + l_contractReferenceUnit.length);
        //基準返済一覧行作成
        WEB3FuturesCloseMarginGroup l_baseCloseMarginGroup = createCloseMarginListLine(l_contractReferenceUnit[0]);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
         
        //基準日計り対象フラグの設定処理
        boolean l_blnBaseDayTradeFlag = l_orderMgr.isDayTrade(
            l_contractReferenceUnit[0].openDate, 
            l_contractReferenceUnit[0].sessionType);        

        for (int i = 0; i < l_contractReferenceUnit.length; i++)
        {
            //日計り対象フラグ
            boolean l_blnDayTradeFlag = l_orderMgr.isDayTrade(
                l_contractReferenceUnit[i].openDate, 
                l_contractReferenceUnit[i].sessionType);
            
            //以下の条件のいずれかに当てはまる場合のみ、処理を実施する。
            //※Loop処理の初回はこの分岐に入ることはない
			//基準返済一覧行と建玉照会明細[インデックス]について
			//１）　@銘柄コードが不一致。
			//２）　@建区分が不一致。
			//３）　@銘柄コード、建区分は一致するが、決済状態が不一致。
			//４）　@銘柄コード、建区分、決済状態が一致、かつ、日計り対象フラグが不一致(*)
			//     （＝日計り返済の場合）
			//※建玉照会明細は銘柄コード・建区分・決済状態区分・建日の昇順でソートされた状態である
            if (!l_baseCloseMarginGroup.futProductCode.equals(l_contractReferenceUnit[i].futProductCode)
                || !l_baseCloseMarginGroup.contractType.equals(l_contractReferenceUnit[i].contractType)
                || (l_baseCloseMarginGroup.futProductCode.equals(l_contractReferenceUnit[i].futProductCode)
                && l_baseCloseMarginGroup.contractType.equals(l_contractReferenceUnit[i].contractType)
                && !l_baseCloseMarginGroup.settlementState.equals(l_contractReferenceUnit[i].settlementState))
                || (l_baseCloseMarginGroup.futProductCode.equals(l_contractReferenceUnit[i].futProductCode)
                && l_baseCloseMarginGroup.contractType.equals(l_contractReferenceUnit[i].contractType)
                && l_baseCloseMarginGroup.settlementState.equals(l_contractReferenceUnit[i].settlementState)
                && l_blnBaseDayTradeFlag != l_blnDayTradeFlag))
            {
                WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[l_lstContractUnits.size()];
                l_lstContractUnits.toArray(l_contractUnits);

                //指定の返済一覧行の建玉明細のマージ処理とプロパティセットを行う。
                l_baseCloseMarginGroup = this.setCloseMarginListLine(l_baseCloseMarginGroup, l_contractUnits);

                l_lstCloseMarginGroups.add(l_baseCloseMarginGroup);

                //新たに作成した返済一覧行を基準返済一覧行としてセットする
                l_baseCloseMarginGroup = this.createCloseMarginListLine(l_contractReferenceUnit[i]);

                //基準日計り対象フラグの設定処理
                l_blnBaseDayTradeFlag = l_orderMgr.isDayTrade(
                    l_contractReferenceUnit[i].openDate, 
                    l_contractReferenceUnit[i].sessionType);

                l_lstContractUnits = new ArrayList();
            }
            //指定の建玉照会明細から建玉明細を作成する。
            WEB3FuturesOptionsContractUnit l_contractUnit = this.createContractUnit(l_contractReferenceUnit[i]);

            l_lstContractUnits.add(l_contractUnit);

        }

        WEB3FuturesOptionsContractUnit[] l_contractUnits1 = new WEB3FuturesOptionsContractUnit[l_lstContractUnits.size()];
        l_lstContractUnits.toArray(l_contractUnits1);
        l_baseCloseMarginGroup = this.setCloseMarginListLine(l_baseCloseMarginGroup, l_contractUnits1);
        l_lstCloseMarginGroups.add(l_baseCloseMarginGroup);
        log.debug("l_lstCloseMarginGroups = " + l_lstCloseMarginGroups.size());
        WEB3FuturesCloseMarginGroup[] l_closeMarginGroups = new WEB3FuturesCloseMarginGroup[l_lstCloseMarginGroups.size()];
        l_lstCloseMarginGroups.toArray(l_closeMarginGroups);

        log.exiting(STR_METHOD_NAME);

        return l_closeMarginGroups;
    }

    /**
     * (create返済一覧行)<BR>
     * 指定の建玉照会明細から株価指数先物返済一覧行を作成する。<BR>
     * <BR>
     * (1)返済一覧行の生成<BR>
     * 株価指数先物返済一覧行(以下、返済一覧行)オブジェクトを生成する<BR>
     * <BR>
     * (2)時価の取得<BR>
     * 　@先物OPポジションマネージャ.getContract(パラメータ.建玉照会明細.ID)<BR>
     * 　@先物OPポジションマネージャ.get建玉時価(建玉)<BR>
     * <BR>
     * (3)プロパティのセット<BR>
     * 以下のプロパティセットを行う<BR>
     * <BR>
     * 返済一覧行.銘柄コード = パラメータ.建玉照会明細.銘柄コード<BR>
     * 返済一覧行.銘柄名 = パラメータ.建玉照会明細.銘柄名<BR>
     * 返済一覧行.指数種別 = パラメータ.建玉照会明細.指数種別<BR>
     * 返済一覧行.限月 = パラメータ.建玉照会明細.限月<BR>
     * 返済一覧行.取引市場 = パラメータ.建玉照会明細.取引市場<BR>
     * 返済一覧行.建区分 = パラメータ.建玉照会明細.建区分<BR>
     * 返済一覧行.決済状態区分 = パラメータ.建玉照会明細.決済状態区分<BR>
     * 返済一覧行.現在値 = (2)で取得した時価<BR>
     * 返済一覧行.立会区分 = パラメータ.建玉照会明細.立会区分<BR>
     * <BR>
     * (4)(3)の返済一覧行オブジェクトを返却する<BR>
     * @@param l_contractReferenceUnit - (建玉照会明細)<BR>
     * 株価指数先物建玉照会明細オブジェクト
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginGroup
     * @@roseuid 40A9699B0243
     */
    protected WEB3FuturesCloseMarginGroup createCloseMarginListLine(WEB3FuturesContractReferenceUnit l_contractReferenceUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCloseMarginListLine(WEB3FuturesContractReferenceUnit l_contractReferenceUnit)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesCloseMarginGroup l_closeMarginGroup = new WEB3FuturesCloseMarginGroup();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl) l_tradingModule.getPositionManager();

        //時価の取得
        try
        {
            WEB3IfoContractImpl l_contract = (WEB3IfoContractImpl) l_positionManager.getContract(Long.parseLong(l_contractReferenceUnit.id)); //throw NotFoundException
            double l_dblContractCurrentPrice = l_positionManager.getContractCurrentPrice(l_contract);

            //返済一覧行.銘柄コード = パラメータ.建玉照会明細.銘柄コード
            l_closeMarginGroup.futProductCode = l_contractReferenceUnit.futProductCode; //銘柄コード
            //返済一覧行.銘柄名 = パラメータ.建玉照会明細.銘柄名
            l_closeMarginGroup.futProductName = l_contractReferenceUnit.futProductName; //銘柄名
            //返済一覧行.指数種別 = パラメータ.建玉照会明細.指数種別
            l_closeMarginGroup.targetProductCode = l_contractReferenceUnit.targetProductCode; //指数種別
            //返済一覧行.限月 = パラメータ.建玉照会明細.限月
            l_closeMarginGroup.delivaryMonth = l_contractReferenceUnit.delivaryMonth; //限月

            //返済一覧行.取引市場 = パラメータ.建玉照会明細.取引市場
            l_closeMarginGroup.marketCode = l_contractReferenceUnit.marketCode; //取引市場
            //返済一覧行.建区分 = パラメータ.建玉照会明細.建区分
            l_closeMarginGroup.contractType = l_contractReferenceUnit.contractType; //建区分
            //返済一覧行.決済状態区分 = パラメータ.建玉照会明細.決済状態区分
            l_closeMarginGroup.settlementState = l_contractReferenceUnit.settlementState; //決済状態区分
            //返済一覧行.現在値 = 取得した時価
            l_closeMarginGroup.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblContractCurrentPrice); //現在
            //返済一覧行.立会区分 = パラメータ.建玉照会明細.立会区分
            l_closeMarginGroup.sessionType = l_contractReferenceUnit.sessionType;
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。", l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_closeMarginGroup;
    }

    /**
     * (create建玉明細)<BR>
     * 指定の建玉照会明細から建玉明細を作成する。<BR>
     * <BR>
     * (1)建玉明細の生成<BR>
     * 建玉明細オブジェクトを生成する<BR>
     * <BR>
     * (2)プロパティのセット<BR>
     * 以下のプロパティセットを行う<BR>
     * <BR>
     * 建玉明細.ID = パラメータ.建玉照会明細.ID<BR>
     * 建玉明細.建日 = パラメータ.建玉照会明細.建日<BR>
     * 建玉明細.建玉数 = パラメータ.建玉照会明細.建数量<BR>
     * 建玉明細.建単価 = パラメータ.建玉照会明細.建単価<BR>
     * 建玉明細.建約定金額 = パラメータ.建玉照会明細.建約定金額<BR>
     * 建玉明細.建手数料 = パラメータ.建玉照会明細.建手数料<BR>
     * 建玉明細.損益 = パラメータ.建玉照会明細.損益<BR>
     * 建玉明細.損益(諸経費込) = パラメータ.建玉照会明細.損益(諸経費込)<BR>
     * 建玉明細.返済数量 = NULL<BR>
     * 建玉明細.決済順位 = NULL<BR>
     * 建玉明細.返済約定数量 = NULL<BR>
     * 建玉明細.立会区分 = パラメータ.建玉照会明細.立会区分<BR>
     * <BR>
     * (3)(2)の建玉明細オブジェクトを返却する<BR>
     * @@param l_contractReferenceUnit - (建玉照会明細)<BR>
     * 株価指数先物建玉照会明細オブジェクト
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsContractUnit
     * @@roseuid 40A9699B0253
     */
    protected WEB3FuturesOptionsContractUnit createContractUnit(WEB3FuturesContractReferenceUnit l_contractReferenceUnit)
    {
        final String STR_METHOD_NAME = "createContractDetails(WEB3FuturesContractReferenceUnit l_contractReferenceUnit)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesOptionsContractUnit l_contractUnit = new WEB3FuturesOptionsContractUnit();

        l_contractUnit.id = l_contractReferenceUnit.id; //ID
        l_contractUnit.openDate = WEB3DateUtility.toDay(l_contractReferenceUnit.openDate); //建日
        l_contractUnit.contractPrice = l_contractReferenceUnit.contractPrice; //建単価
        l_contractUnit.contractQuantity = l_contractReferenceUnit.contractOrderQuantity; //建玉数
        l_contractUnit.contractExecPrice = l_contractReferenceUnit.contractExecPrice; //建約定金額
        l_contractUnit.contractCommission = l_contractReferenceUnit.contractCommission; //建手数料
        l_contractUnit.income = l_contractReferenceUnit.income; //損益
        l_contractUnit.incomeCost = l_contractReferenceUnit.incomeCost; //損益(諸経費込)
        l_contractUnit.contractOrderQuantity = null; //返済数量
        l_contractUnit.settlePriority = null; //決済順位
        l_contractUnit.contractExecQuantity = null; //返済約定数量
        l_contractUnit.sessionType = l_contractReferenceUnit.sessionType; //立会区分
        log.exiting(STR_METHOD_NAME);

        return l_contractUnit;
    }
    
    /**
     * (create建玉照会明細一覧)<BR>
     * リクエストデータより建玉照会明細の一覧を作成する。  <BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（先物返済入力）get返済入力画面１」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 返済注文入力リクエストデータオブジェクト。
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesContractReferenceUnit[] createContractReferenceUnitList(
        WEB3FuturesCloseMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractReferenceUnitList(WEB3FuturesCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesContractReferenceUnit[] l_contractReferenceUnits = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoPositionManagerImpl l_positionManager =
            (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
        //ArrayList
        //建玉照会明細を格納する建玉照会明細リストを生成する
        ArrayList l_lisContractReferenceUnits = new ArrayList();

        try
        {
            for (int i = 0; i < l_request.id.length; i++)
            {
                //指定建玉IDに該当する先物OP建玉オブジェクトを取得する。
                WEB3IfoContractImpl l_contract =
                    (WEB3IfoContractImpl)l_positionManager.getContract(Long.parseLong(l_request.id[i]));

                //未決済の建玉照会明細の1明細を作成する。
                l_positionManager.createFuturesUnSettledContractInquiryDetails(
                    l_lisContractReferenceUnits,
                    l_contract);
            }

            l_contractReferenceUnits =
                new WEB3FuturesContractReferenceUnit[l_lisContractReferenceUnits.size()];
            l_lisContractReferenceUnits.toArray(l_contractReferenceUnits);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_contractReferenceUnits;
    }

    /**
     * (sort建玉照会明細)<BR>
     * 指定されたソートキー、昇降順にもどついて建玉照会明細のソートを行う。<BR>
     * <BR>
     * (1)ArrayListを作成<BR>
     * <BR>
     * (2)パラメータ.ソートキーの配列数分Loop処理<BR>
     * 　@(2-1)パラメータ.ソートキー.キー項目を取得<BR>
     * <BR>
     * 　@(2-2)パラメータ.ソートキー.昇順/降順を取得<BR>
     * <BR>
     * 　@(2-3)キー項目による分岐処理<BR>
     * 　@　@キー項目が銘柄コードであった場合、先物銘柄コードComparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@キー項目が損益であった場合、先物損益Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@キー項目が建日であった場合、
     * 　@　@先物建日Comparator、先物立会区分Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@キー項目が建区分であった場合、先物建区分Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@キー項目が決済状態区分であった場合、決済状態区分Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@(2-4)(2-3)にて作成したComparatorをArrayListに追加<BR>
     * <BR>
     * (3)ArrayListからComparatorの配列を作成<BR>
     * <BR>
     * (4)Comparatorの配列順のソート処理<BR>
     * (web3-common)WEB3ArraysUtility.sort(パラメータ.建玉照会明細、<BR>
     * Comparator[])<BR>
     * <BR>
     * (5)ソートされた建玉照会明細の配列を返却<BR>
     * @@param l_contractReferenceUnit - (建玉照会明細)<BR>
     * 株価指数先物建玉照会明細の配列
     * @@param l_sortKey - (ソートキー)<BR>
     * 株価指数先物オプションソートキーの配列
     * @@return webbroker3.ifo.message.WEB3FuturesContractReferenceUnit[]
     * @@roseuid 40A9699B0291
     */
    protected WEB3FuturesContractReferenceUnit[] sortContractReferenceUnit(WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit, WEB3FuturesOptionsSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = "sortContractReferenceUnit(WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit, WEB3FuturesOptionsSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);

        List l_lstComparators = new ArrayList();

        for (int i = 0; i < l_sortKey.length; i++)
        {
            String l_strKeyItem = l_sortKey[i].keyItem;
            String l_strAscDesc = l_sortKey[i].ascDesc;

            Comparator l_com = null;
            Comparator l_sessionTypeComparator = null;

            if (WEB3IfoKeyItemDef.FUTPRODUCTCODE.equals(l_strKeyItem))
            {
                //キー項目が銘柄コードであった場合、銘柄コードComparatorを生成
                l_com = new WEB3FuturesProductCodeComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.INCOME.equals(l_strKeyItem))
            {
                //キー項目が損益であった場合、損益Comparatorを生成
                l_com = new WEB3FuturesProfitAndLossComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {
                //キー項目が建日であった場合、建日Comparator、先物立会区分Comparatorを生成
                l_com = new WEB3FuturesOpenDateComparator(l_strAscDesc);
                l_sessionTypeComparator = new WEB3FuturesSessionTypeComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(l_strKeyItem))
            {
                //キー項目が建区分であった場合、建区分Comparatorを生成
                l_com = new WEB3FuturesContractDivisionComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.SETTLEMENT_STATUS_DIVISION.equals(l_strKeyItem))
            {
                //キー項目が決済状態区分であった場合、決済状態区分Comparatorを生成
                l_com = new WEB3FuturesSettlementStatusTypeComparator(l_strAscDesc);
            }

            if (l_com != null)
            {
                l_lstComparators.add(l_com);
            }
            if (l_sessionTypeComparator != null)
            {
                l_lstComparators.add(l_sessionTypeComparator);
            }

        }

        Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
        l_lstComparators.toArray(l_comparators);

        WEB3ArraysUtility.sort(l_contractReferenceUnit, l_comparators);

        log.exiting(STR_METHOD_NAME);

        return l_contractReferenceUnit;
    }

    /**
     * (sort建玉明細)<BR>
     * 指定されたソートキー、昇降順に基づいて建玉明細のソートを行う。<BR>
     * <BR>
     * (1)ArrayListを作成<BR>
     * <BR>
     * (2)パラメータ.ソートキーの配列数分Loop処理<BR>
     * 　@(2-1)パラメータ.ソートキー.キー項目を取得<BR>
     * <BR>
     * 　@(2-2)パラメータ.ソートキー.昇順/降順を取得<BR>
     * <BR>
     * 　@(2-3)キー項目による分岐処理<BR>
     * 　@　@キー項目が建日であった場合、<BR>
     * 　@　@先物建日Comparator、先物立会区分Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@キー項目が建単価であった場合、先物建単価Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@(2-4)(2-3)にて作成したComparatorをArrayListに追加<BR>
     * <BR>
     * (3)ArrayListからComparatorの配列を作成<BR>
     * <BR>
     * (4)Comparatorの配列順のソート処理<BR>
     * (web3-common)WEB3ArraysUtility.sort(パラメータ.建玉明細、<BR>Comparator[])<BR>
     * <BR>
     * (5)ソートされた建玉明細の配列を返却<BR>
     * @@param l_contractUnit - (建玉明細)<BR>
     * 建玉明細の配列
     * @@param l_sortKey - (ソートキー)<BR>
     * 株価指数先物オプションソートキーの配列
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsContractUnit[]
     * @@roseuid 40A9699B02EF
     */
    protected WEB3FuturesOptionsContractUnit[] sortContractUnit(WEB3FuturesOptionsContractUnit[] l_contractUnit, WEB3FuturesOptionsSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = " sortContractDetails(WEB3FuturesOptionsContractUnit[] l_contractUnits, WEB3FuturesOptionsSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);

        List l_lstComparators = new ArrayList();

        for (int i = 0; i < l_sortKey.length; i++)
        {
            String l_strKeyItem = l_sortKey[i].keyItem;
            String l_strAscDesc = l_sortKey[i].ascDesc;

            Comparator l_com = null;

            if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {
                //キー項目が建日であった場合、建日Comparator、先物立会区分Comparatorを生成
                l_com = new WEB3FuturesOpenDateComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
                l_com = new WEB3FuturesSessionTypeComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
            }
            else if (WEB3IfoKeyItemDef.CONTRACT_PRICE.equals(l_strKeyItem))
            {
                //キー項目が建単価であった場合、建単価Comparatorを生成
                l_com = new WEB3FuturesContractPriceComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
            }
        }

        Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
        l_lstComparators.toArray(l_comparators);

        WEB3ArraysUtility.sort(l_contractUnit, l_comparators);

        log.exiting(STR_METHOD_NAME);

        return l_contractUnit;
    }

    /**
     * (sort返済一覧行)<BR>
     * 指定されたソートキー、昇降順に基づいて返済一覧行のソートを行う。<BR>
     * <BR>
     * (1)ArrayListを作成<BR>
     * <BR>
     * (2)パラメータ.ソートキーの配列数分Loop処理<BR>
     * 　@(2-1)パラメータ.ソートキー.キー項目を取得<BR>
     * <BR>
     * 　@(2-2)パラメータ.ソートキー.昇順/降順を取得<BR>
     * <BR>
     * 　@(2-3)キー項目による分岐処理<BR>
     * 　@　@キー項目が銘柄コードであった場合、<BR>
     *     先物銘柄コードComparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@キー項目が損益であった場合、先物損益Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@キー項目が損益(諸経費込)であった場合、損益(諸経費込)Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@キー項目が建区分であった場合、先物建区分Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@(2-4)(2-3)にて作成したComparatorをArrayListに追加<BR>
     * <BR>
     * (3)ArrayListからComparatorの配列を作成<BR>
     * <BR>
     * (4)Comparatorの配列順のソート処理<BR>
     * (web3-common)WEB3ArraysUtility.sort(パラメータ.返済一覧行、<BR>Comparator[])<BR>
     * <BR>
     * (5)ソートされた返済一覧行の配列を返却<BR>
     * @@param l_closeMarginListLine - (返済一覧行)<BR>
     * 株価指数先物返済一覧行の配列
     * @@param l_sortKey - (ソートキー)<BR>
     * 株価指数先物オプションソートキーの配列
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginGroup[]
     * @@roseuid 40A9699B033D
     */
    protected WEB3FuturesCloseMarginGroup[] sortCloseMarginListLine(WEB3FuturesCloseMarginGroup[] l_closeMarginListLine, WEB3FuturesOptionsSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = "sortCloseMarginListLine(WEB3FuturesCloseMarginGroup[] l_closeMarginListLine, WEB3FuturesOptionsSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);

        List l_lstComparators = new ArrayList();

        for (int i = 0; i < l_sortKey.length; i++)
        {
            String l_strKeyItem = l_sortKey[i].keyItem;
            String l_strAscDesc = l_sortKey[i].ascDesc;

            Comparator l_com = null;

            if (WEB3IfoKeyItemDef.FUTPRODUCTCODE.equals(l_strKeyItem))
            {
                //キー項目が銘柄コードであった場合、先物銘柄コードComparatorを生成
                l_com = new WEB3FuturesProductCodeComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.INCOME.equals(l_strKeyItem))
            {
                //キー項目が損益であった場合、先物損益Comparatorを生成
                l_com = new WEB3FuturesProfitAndLossComparator(l_strAscDesc);
            }
            else if (WEB3IfoKeyItemDef.INCOME_COST.equals(l_strKeyItem))
            {
                //キー項目が損益(諸経費込)であった場合、先物損益(諸経費込)Comparatorを生成
                l_com = new WEB3FuturesProfitAndLossCostComparator(l_strAscDesc);
            }
            else
            {
                //キー項目が建区分であった場合、先物建区分Comparatorを生成
                l_com = new WEB3FuturesContractDivisionComparator(l_strAscDesc);
            }

            if (l_com != null)
            {
                l_lstComparators.add(l_com);
            }
        }

        Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
        l_lstComparators.toArray(l_comparators);

        WEB3ArraysUtility.sort(l_closeMarginListLine, l_comparators);

        log.exiting(STR_METHOD_NAME);

        return l_closeMarginListLine;
    }

    /**
     * (sort建玉明細一覧)<BR>
     * 指定されたソートキー、昇降順にもどついて建玉明細のソートを行う。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（先物返済入力）get返済入力画面２」参照<BR>
     * @@param l_contractUnits - (建玉明細一覧)<BR>
     * 建玉明細の配列。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 返済注文入力リクエストオブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    protected void sortContractUnitList(
        WEB3FuturesOptionsContractUnit[] l_contractUnits, WEB3FuturesCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "sortContractUnitList(WEB3FuturesOptionsContractUnit[], WEB3FuturesCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        //株価指数先物ートキー配列を作成する。
        //リクエストデータ.ソートキーの項目に応じて以下を追加したソートキーの配列を設定する。
        WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[2];
        //1. リクエストデータ.ソートキー[0].キー項目が建日の場合
        if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_request.futOpSortKeys[0].keyItem))
        {
            l_sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_sortKeys[0].keyItem = WEB3IfoKeyItemDef.OPEN_DATE;
            l_sortKeys[0].ascDesc = l_request.futOpSortKeys[0].ascDesc;
            //ソートキー[1].キー項目 = 建単価
            //ソートキー[1].昇順／降順 = "降順"
            l_sortKeys[1] = new WEB3FuturesOptionsSortKey();
            l_sortKeys[1].keyItem = WEB3IfoKeyItemDef.CONTRACT_PRICE;
            l_sortKeys[1].ascDesc = WEB3AscDescDef.DESC;
        }
        //2. リクエストデータ.ソートキー[0].キー項目が建単価の場合
        else if (WEB3IfoKeyItemDef.CONTRACT_PRICE.equals(l_request.futOpSortKeys[0].keyItem))
        {
            l_sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_sortKeys[0].keyItem = WEB3IfoKeyItemDef.CONTRACT_PRICE;
            l_sortKeys[0].ascDesc = l_request.futOpSortKeys[0].ascDesc;
            //ソートキー[1].キー項目 = 建日
            //ソートキー[1].昇順／降順 = "昇順"
            l_sortKeys[1] = new WEB3FuturesOptionsSortKey();
            l_sortKeys[1].keyItem = WEB3IfoKeyItemDef.OPEN_DATE;
            l_sortKeys[1].ascDesc = WEB3AscDescDef.ASC;
        }
        //指定されたソートキー、昇降順にもどついて建玉明細のソートを行う。
        l_contractUnits = this.sortContractUnit(l_contractUnits, l_sortKeys);

        log.exiting(STR_METHOD_NAME);
    }
    

    /**
     * (set返済一覧行)<BR>
     * 指定の返済一覧行の建玉明細のマージ処理とプロパティセットを行う。<BR>
     * <BR>
     * (1)建玉明細ごとのLoop処理。<BR>
     * パラメータ.建玉明細の要素ごとのLoop処理にて、以下の値を取得する<BR>
     * <BR>
     * 合計建玉数 += 建玉明細[インデックス].建玉数<BR>
     * 合計建単価 += (建玉明細[インデックス].建単価 × 建玉明細[インデックス].建玉数)<BR>
     * 合計建約定金額 += 建玉明細[インデックス].建約定金額<BR>
     * 合計建建手数料 += 建玉明細[インデックス].建手数料<BR>
     * 合計損益 += 建玉明細[インデックス].損益<BR>
     * 合計損益(諸経費込） += 建玉明細[インデックス].損益（諸経費込） <BR>
     * <BR>
     * (2)プロパティセット。<BR>
     * パラメータ.返済一覧行に以下の通りプロパティをセットする<BR>
     * <BR>
     * 返済一覧行.建玉数 = 合計建玉数<BR>
     * 返済一覧行.建単価 = 返済一覧行.建単価 = 合計建単価 ÷ 合計建玉数(円未満は四捨五入)<BR>
     * 返済一覧行.建代金 = 合計建約定金額<BR>
     * 返済一覧行.建手数料 = 合計建手数料<BR>
     * 返済一覧行.損益 = 合計損益<BR>
     * 返済一覧行.損益（諸経費込） = 合計損益（諸経費込）<BR>
     * 返済一覧行.建玉明細 = パラメータ.建玉明細<BR>
     * <BR>
     * (3)(2)でプロパティセットした返済一覧行を返却する。<BR>
     * @@param l_closeMarginListLine - (返済一覧行)<BR>
     * 株価指数先物返済一覧行オブジェクト
     * @@param l_contractUnit - (建玉明細)<BR>
     * 建玉明細の配列
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginGroup
     * @@roseuid 40A9699B039B
     */
    protected WEB3FuturesCloseMarginGroup setCloseMarginListLine(WEB3FuturesCloseMarginGroup l_closeMarginListLine, WEB3FuturesOptionsContractUnit[] l_contractUnit)
    {
        final String STR_METHOD_NAME = "setCloseMarginListLine(WEB3FuturesCloseMarginGroup l_closeMarginListLine, WEB3FuturesOptionsContractUnit[] l_contractUnit)";
        log.entering(STR_METHOD_NAME);

        long l_lngTotalQuantity = 0; //合計建玉数
        double l_dblTotalPrice = 0; //合計建単価
        double l_dblTotalExecPrice = 0; //合計建約定金額
        double l_dblcontractCommission = 0; //合計建手数料
        double l_dblTotalIncome = 0; //合計損益
        double l_dblTotalIncomeCost = 0; //合計損益（諸経費込）

        BigDecimal l_bdTotalPrice = new BigDecimal(l_dblTotalPrice + "");
        BigDecimal l_bdTotalExecPrice = new BigDecimal(l_dblTotalExecPrice + "");
        BigDecimal l_bdContractCommission = new BigDecimal(l_dblcontractCommission + "");
        BigDecimal l_bdTotalIncome = new BigDecimal(l_dblTotalIncome + "");
        BigDecimal l_bdTotalIncomeCost = new BigDecimal(l_dblTotalIncomeCost + "");

        for (int i = 0; i < l_contractUnit.length; i++)
        {
            //合計建玉数
            l_lngTotalQuantity += Long.parseLong(l_contractUnit[i].contractQuantity);
            //合計建単価
            l_bdTotalPrice = l_bdTotalPrice.add(
                new BigDecimal(l_contractUnit[i].contractPrice).multiply(
                    new BigDecimal(l_contractUnit[i].contractQuantity)));
            //合計建約定金額
            l_bdTotalExecPrice = l_bdTotalExecPrice.add(new BigDecimal(l_contractUnit[i].contractExecPrice));
            //合計建手数料
            l_bdContractCommission = l_bdContractCommission.add(new BigDecimal(l_contractUnit[i].contractCommission));
            //合計損益
            l_bdTotalIncome = l_bdTotalIncome.add(new BigDecimal(l_contractUnit[i].income));
            //合計損益（諸経費込）
            l_bdTotalIncomeCost = l_bdTotalIncomeCost.add(new BigDecimal(l_contractUnit[i].incomeCost));
        }
        //返済一覧行.建玉数 = 合計建玉数
        l_closeMarginListLine.contractQuantity = WEB3StringTypeUtility.formatNumber(l_lngTotalQuantity);
        //返済一覧行.建単価 = 返済一覧行.建単価 = 合計建単価 ÷ 合計建玉数(円未満は四捨五入)
        l_closeMarginListLine.contractPrice = WEB3StringTypeUtility.formatNumber(
            l_bdTotalPrice.divide(new BigDecimal(l_lngTotalQuantity + ""), 0, BigDecimal.ROUND_HALF_UP).doubleValue());
        //返済一覧行.建代金 = 合計建約定金額
        l_closeMarginListLine.contractExecPrice =
            WEB3StringTypeUtility.formatNumber(l_bdTotalExecPrice.doubleValue());
        //返済一覧行.建手数料 = 合計建手数料
		l_closeMarginListLine.contractCommission =
		    WEB3StringTypeUtility.formatNumber(l_bdContractCommission.doubleValue());
        //返済一覧行.損益 = 合計損益
        l_closeMarginListLine.income = WEB3StringTypeUtility.formatNumber(l_bdTotalIncome.doubleValue());
        //返済一覧行.損益（諸経費込） = 合計損益（諸経費込）
		l_closeMarginListLine.incomeCost = WEB3StringTypeUtility.formatNumber(l_bdTotalIncomeCost.doubleValue());
        //返済一覧行.建玉明細 = パラメータ.建玉明細
        l_closeMarginListLine.contractUnits = l_contractUnit;

        log.exiting(STR_METHOD_NAME);

        return l_closeMarginListLine;
    }

    /**
     * (is該当会社取扱銘柄)<BR>
     * 指定された銘柄が該当顧客の会社・部店で取扱可能<BR>
     * な銘柄であるかを判定する。<BR>
     * 取扱可能銘柄の場合はtrueを、取扱不能銘柄の場合はfalseを返却<BR>
     * する。<BR>
     * <BR>
     * (1)原資産銘柄コードの取得<BR>
     * <BR>
     * 先物OP銘柄 = 先物OPプロダクトマネージャ.getProduct<BR>
     * (パラメータ.銘柄コード)<BR>
     * 原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()<BR>
     * <BR>
     * (2)取扱可能銘柄の判定<BR>
     * <BR>
     * 　@(2-1)証券会社コード、部店コードの取得<BR>
     * 　@口座 = パラメータ.補助口座.getMainAccount()<BR>
     * 　@証券会社コード = 口座.get証券会社コード()<BR>
     * 　@部店コード = 口座.get部店コード()<BR>
     * <BR>
     * 　@(2-2)(部店指数別)取扱条件配列の取得<BR>
     * 　@取扱条件配列 = (部店指数別)取扱条件.get(部店指数別)<BR>
     * 取扱条件(証券会社コード、部店コード)<BR>
     * <BR>
     * 　@(2-3)取扱条件配列要素分のLoop処理<BR>
     * 　@取扱条件.get原資産銘柄コード == (2)<BR>
     * の原資産銘柄コードならば、breakしてtrueを返却する<BR>
     * 　@breakせずLoopが終了したらfalseを返却する<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_strProductCode - 銘柄コード
     * 
     * @@return boolean
     * @@roseuid 40A9699B03CA
     */
    protected boolean isPertinentInstDealtProduct(WEB3GentradeSubAccount l_subAccount, String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPertinentInstDealtProduct(WEB3GentradeSubAccount l_subAccount, String l_strProductCode)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        try
        {
            WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productMgr.getIfoProduct(l_subAccount.getInstitution(), l_strProductCode);

            //原資産銘柄コードの取得
            String l_strUnderlyingProductCode = l_product.getUnderlyingProductCode();

            MainAccount l_account = l_subAccount.getMainAccount();
            String l_institutionCode = l_account.getInstitution().getInstitutionCode(); //証券会社コード
            String l_branchCode = l_account.getBranch().getBranchCode(); //部店コード
            WEB3GentradeBranchIndexDealtCond[] l_condBranchs =
                WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
                    l_institutionCode, l_branchCode, WEB3FuturesOptionDivDef.FUTURES);

            for (int i = 0; i < l_condBranchs.length; i++)
            {
                if (l_strUnderlyingProductCode.equals(l_condBranchs[i].getUnderlyingProductCode()))
                {
                    return true;
                }
            }

        }
        catch (Exception l_ex)
        {
            log.error("データ不整合エラー。", l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return false;
    }
}
@
