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
filename	WEB3FuturesIndividualSettleContractListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物個別返済一覧表示サービス実装(WEB3FuturesIndividualSettleContractListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 鄒鋭 (中訊) 新規作成
Revesion History : 2007/06/21 張騰宇(中訊) モデル730
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.message.WEB3FuturesIndividualCloseMarginListRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3FuturesSessionTypeComparator;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOpenDateComparator;
import webbroker3.ifo.message.WEB3FuturesIndividualCloseMarginListResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesIndividualSettleContractListService;

/**
 * (先物個別返済一覧表示サービスImpl)<BR>
 * 株価指数先物個別返済一覧表示サービス実装クラス
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesIndividualSettleContractListServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesIndividualSettleContractListService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesIndividualSettleContractListServiceImpl.class);

    /**
     * @@roseuid 40F7A2CE001F
     */
    public WEB3FuturesIndividualSettleContractListServiceImpl()
    {

    }

    /**
     * 株価指数先物個別返済一覧表示サービス処理を実施する。<BR>
     * <BR>
     * 「（先物返済入力）個別返済一覧表示」参照。<BR>
     * ==========================================================<BR>
     * シーケンス図(「（先物返済入力）個別返済一覧表示」):<BR>
     *  8 get銘柄(Institution, String, String, String, double, String, String)<BR>
     * (銘柄コードチェック)<BR>
     * get銘柄で指定の銘柄コードが取得できなかった場合は、<BR>
     * 「銘柄コードチェックエラー」の例外をthrowする。<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00301<BR>
     * ==========================================================<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.ifo.message.WEB3FuturesＩndividualCloseMarginListResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A9929202D7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_inRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3FuturesIndividualCloseMarginListRequest l_inRequest = null;
        if (l_request instanceof WEB3FuturesIndividualCloseMarginListRequest)
        {
            l_inRequest = (WEB3FuturesIndividualCloseMarginListRequest) l_request;
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
        }
        //リクエストデータの整合性をチェックする。
        l_inRequest.validate();

        WEB3FuturesIndividualCloseMarginListResponse l_response = (WEB3FuturesIndividualCloseMarginListResponse) l_inRequest.createResponse();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl) l_tradingModule.getPositionManager();
        log.debug("get : l_positionManager = " + l_positionManager);
        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
        log.debug("get : l_productMgr = " + l_productMgr);
        try
        {
            WEB3IfoContractImpl l_contractFirst = (WEB3IfoContractImpl) l_positionManager.getContract(Long.parseLong(l_inRequest.id[0]));
            IfoProduct l_productFirst = (IfoProduct)l_contractFirst.getProduct();
            WEB3GentradeTradingTimeManagement.resetProductCode(l_productFirst.getUnderlyingProductCode());
            //補助口座を取得する。
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
            log.debug("get : l_subAccount = " + l_subAccount);



            //返済注文受付審査を実施する。
            WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();
            log.debug("get : l_orderMgr = " + l_orderMgr);

            l_orderMgr.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.FUTURES);
            log.debug("l_orderMgr.validateOrder ended!! >>>>");

            ArrayList l_lstContractReferenceUnits = new ArrayList();

            for (int i = 0; i < l_inRequest.id.length; i++)
            {
                //指定建玉IDに該当する建玉オブジェクトを取得する。
                WEB3IfoContractImpl l_contract = (WEB3IfoContractImpl) l_positionManager.getContract(Long.parseLong(l_inRequest.id[i])); //throw NotFoundException
                log.debug("get : l_contract = " + l_contract);
                //銘柄オブジェクトを取得する。
                WEB3IfoProductImpl l_tmpProduct = (WEB3IfoProductImpl) l_productMgr.getProduct(l_contract.getProduct().getProductId());
                try
                {
                    log.debug("l_subAccount.getInstitution() = " + l_subAccount.getInstitution().getInstitutionCode());
                    log.debug("get : l_contract.getProduct().getProductId() = " + l_contract.getProduct().getProductId());
                    WEB3IfoProductImpl l_product = l_productMgr.getIfoProduct(l_subAccount.getInstitution(), l_tmpProduct.getProductCode());

                    log.debug("get : l_product = " + l_product);
                }
                catch (NotFoundException l_wex)
                {
                    //該当する銘柄が存在しない場合は例外をスローする。
                    throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, this.getClass().getName() + STR_METHOD_NAME);
                }

                //未決済の建玉照会明細の1明細を作成する。
                l_positionManager.createFuturesUnSettledContractInquiryDetails(l_lstContractReferenceUnits, //建玉照会明細
                l_contract //先物OP建玉
                );

            }

            log.debug("l_lstContractReferenceUnits.size() = " + l_lstContractReferenceUnits.size());
            WEB3FuturesContractReferenceUnit[] l_contractReferenceUnits = new WEB3FuturesContractReferenceUnit[l_lstContractReferenceUnits.size()];
            l_lstContractReferenceUnits.toArray(l_contractReferenceUnits);
            log.debug("l_contractReferenceUnits.length = " + l_contractReferenceUnits.length);
            List l_lstContractUnits = new ArrayList();

            for (int i = 0; i < l_contractReferenceUnits.length; i++)
            {
                //指定の建玉照会明細から建玉明細を作成する。
                WEB3FuturesOptionsContractUnit l_contractUnit = this.createContractUnit(l_contractReferenceUnits[i]);

                l_lstContractUnits.add(l_contractUnit);
                log.debug("get :l_contractUnit = " + l_contractUnit);
            }

            WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[l_lstContractUnits.size()];
            l_lstContractUnits.toArray(l_contractUnits);
            log.debug("before sorted :l_contractUnits = " + l_contractUnits);

            //指定されたソートキー、昇降順にもどついて建玉明細のソートを行う。
            l_contractUnits = sortContractUnit(l_contractUnits, l_inRequest.futOpSortKeys);
            log.debug("after sorted :l_contractUnits = " + l_contractUnits);

            //レスポンス.銘柄名 = 建玉照会明細[0].銘柄名
            l_response.futProductName = l_contractReferenceUnits[0].futProductName;
            log.debug("set :l_response.futProductName = " + l_response.futProductName);
            //レスポンス.建区分 = 建玉照会明細[0].建区分
            l_response.contractType = l_contractReferenceUnits[0].contractType;
            log.debug("set :l_response.contractType = " + l_response.contractType);
            //レスポンス.取引市場 = 建玉照会明細[0].取引市場
            l_response.marketCode = l_contractReferenceUnits[0].marketCode;
            log.debug("set :l_response.marketCode = " + l_response.marketCode);
            l_response.messageSuspension = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.FUTURES);

            WEB3IfoContractImpl l_contract0 = (WEB3IfoContractImpl) l_positionManager.getContract(Long.parseLong(l_contractReferenceUnits[0].id));
            log.debug("l_contract0 = " + l_contract0);

            //レスポンス.現在値 = get建玉時価の返り値
            double l_dblCurrentPrice = l_positionManager.getContractCurrentPrice(l_contract0);
            if (Double.isNaN(l_dblCurrentPrice))
            {
                l_dblCurrentPrice = 0D;
            }
            l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
            log.debug("get :l_positionManager.getContractCurrentPrice(l_contract0) = " + l_positionManager.getContractCurrentPrice(l_contract0));
            log.debug("set :l_response.currentPrice = " + l_response.currentPrice);
            //レスポンス.建玉明細 = sort建玉明細の返り値
            l_response.contractUnits = l_contractUnits;

        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
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
     * 建玉明細.返済約定数量 = NULL<BR>
     * 建玉明細.決済順位 = NULL<BR>
     * 建玉明細.立会区分 = パラメータ.建玉照会明細.立会区分<BR>
     * <BR>
     * (3)(2)の建玉明細オブジェクトを返却する<BR>
     * @@param l_contractReferenceUnit - (建玉照会明細)<BR>
     * 株価指数先物建玉照会明細オブジェクト
     * @@return WEB3FuturesOptionsContractUnit
     * @@roseuid 40A9929202F7
     */
    protected WEB3FuturesOptionsContractUnit createContractUnit(WEB3FuturesContractReferenceUnit l_contractReferenceUnit)
    {
        final String STR_METHOD_NAME = "createContractDetails(WEB3FuturesContractReferenceUnit l_contractReferenceDetails)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesOptionsContractUnit l_contractUnit = new WEB3FuturesOptionsContractUnit();

        l_contractUnit.id = l_contractReferenceUnit.id; //ID
        l_contractUnit.contractPrice = l_contractReferenceUnit.contractPrice; //建単価
        l_contractUnit.openDate = WEB3DateUtility.toDay(l_contractReferenceUnit.openDate); //建日
        l_contractUnit.contractQuantity = l_contractReferenceUnit.contractOrderQuantity; //建玉数
        l_contractUnit.contractExecPrice = l_contractReferenceUnit.contractExecPrice; //建約定金額
        l_contractUnit.contractCommission = l_contractReferenceUnit.contractCommission; //建手数料
        l_contractUnit.income = l_contractReferenceUnit.income; //損益
        l_contractUnit.incomeCost = l_contractReferenceUnit.incomeCost; //損益(諸経費込)
        l_contractUnit.contractOrderQuantity = null; //返済数量
        l_contractUnit.contractExecQuantity = null; //返済約定数量
        l_contractUnit.settlePriority = null; //決済順位
        l_contractUnit.sessionType = l_contractReferenceUnit.sessionType; //立会区分

        log.debug("l_contractUnit.id = " + l_contractUnit.id);
        log.debug("l_contractUnit.contractPrice = " + l_contractUnit.contractPrice);
        log.debug("l_contractUnit.openDate = " + l_contractUnit.openDate);
        log.debug("l_contractUnit.contractQuantity = " + l_contractUnit.contractQuantity);
        log.debug("l_contractUnit.contractExecPrice = " + l_contractUnit.contractExecPrice);
        log.debug("l_contractUnit.contractCommission = " + l_contractUnit.contractCommission);
        log.debug("l_contractUnit.income = " + l_contractUnit.income);
        log.debug("l_contractUnit.incomeCost = " + l_contractUnit.incomeCost);
        log.debug("l_contractUnit.sessionType = " + l_contractUnit.sessionType);
        log.exiting(STR_METHOD_NAME);

        return l_contractUnit;
    }

    /**
     * (sort建玉明細)<BR>
     * 指定されたソートキー、昇降順にもどついて建玉明細のソートを行う。<BR>
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
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@roseuid 40A992920306
     */
    protected WEB3FuturesOptionsContractUnit[] sortContractUnit(WEB3FuturesOptionsContractUnit[] l_contractUnit, WEB3FuturesOptionsSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = " sortContractUnit(WEB3FuturesOptionsContractUnit[] l_contractUnit, WEB3FuturesOptionsSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);

        List l_lstComparators = new ArrayList();

        for (int i = 0; i < l_sortKey.length; i++)
        {

            String l_strKeyItem = l_sortKey[i].keyItem;
            String l_strAscDesc = l_sortKey[i].ascDesc;
            log.debug("l_sortKey[" + i + "].keyItem = " + l_strKeyItem);
            log.debug("l_sortKey[" + i + "].ascDesc = " + l_strAscDesc);

            Comparator l_com = null;

            if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {
                //キー項目が建日であった場合、先物建日Comparator、先物立会区分Comparatorを生成
                l_com = new WEB3FuturesOpenDateComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
                l_com = new WEB3FuturesSessionTypeComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
            }
        }

        log.debug("l_lstComparators.size() = " + l_lstComparators.size());
        Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
        l_lstComparators.toArray(l_comparators);
        log.debug("l_comparators succeed!>>>>");

        WEB3ArraysUtility.sort(l_contractUnit, l_comparators);
        log.debug("sort completed!>>>>");
        log.exiting(STR_METHOD_NAME);

        return l_contractUnit;
    }

}
@
