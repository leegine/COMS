head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionIndividualSettleContractListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP個別返済一覧表示サービスImpl(WEB3OptionIndividualSettleContractListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/24 張宝楠 (中訊) 新規作成
              001: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
              002: 2004/08/14 王暁傑 対応バグ BUG137
              003: 2004/08/15 王暁傑 対応バグ BUG83
Revesion History : 2007/06/11 張騰宇 モデル　@690
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
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3OptionsOpenDateComparator;
import webbroker3.ifo.message.WEB3OptionsContractReferenceUnit;
import webbroker3.ifo.message.WEB3OptionsIndividualCloseMarginListRequest;
import webbroker3.ifo.message.WEB3OptionsIndividualCloseMarginListResponse;
import webbroker3.ifo.message.WEB3OptionsSessionTypeComparator;
import webbroker3.ifo.service.delegate.WEB3OptionIndividualSettleContractListService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (OP個別返済一覧表示サービスImpl)<BR>
 * <BR>
 * 株価指数オプション個別返済一覧表示サービス実装クラス<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3OptionIndividualSettleContractListServiceImpl extends WEB3OptionClientRequestService
    implements WEB3OptionIndividualSettleContractListService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionIndividualSettleContractListServiceImpl.class);


    /**
     * 株価指数オプション個別返済一覧表示サービス処理を実施する。<BR>
     * <BR>
     * 「（OP返済入力）個別返済一覧表示」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4083BDA00149
     */
    public WEB3GenResponse execute(WEB3GenRequest l_inRequest) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_inRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3OptionsIndividualCloseMarginListRequest l_request = (WEB3OptionsIndividualCloseMarginListRequest)l_inRequest;

        //リクエストデータの整合性をチェックする。
        l_request.validate();

        WEB3OptionsIndividualCloseMarginListResponse l_response = (WEB3OptionsIndividualCloseMarginListResponse)l_request.createResponse();



        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        //返済注文受付審査を実施する。
        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();

        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

        try
        {
            WEB3IfoContractImpl l_contractFirst = (WEB3IfoContractImpl)l_positionManager.getContract(Long.parseLong(l_request.id[0])); //throw NotFoundException
            IfoProduct l_productFirst = (IfoProduct)l_contractFirst.getProduct();
            WEB3GentradeTradingTimeManagement.resetProductCode(l_productFirst.getUnderlyingProductCode());
            //補助口座を取得する。
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
            l_orderMgr.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);
            ArrayList l_lstContractReferenceUnits = new ArrayList();

            for (int i = 0; i < l_request.id.length; i++)
            {
                //指定建玉IDに該当する建玉オブジェクトを取得する。
                WEB3IfoContractImpl l_contract = (WEB3IfoContractImpl)l_positionManager.getContract(Long.parseLong(l_request.id[i])); //throw NotFoundException

                //銘柄オブジェクトを取得する。
                WEB3IfoProductImpl l_tmpProduct =  (WEB3IfoProductImpl)l_productMgr.getProduct(l_contract.getProduct().getProductId());
                try
                {

                    WEB3IfoProductImpl l_product = l_productMgr.getIfoProduct(l_subAccount.getInstitution(), l_tmpProduct.getProductCode());

                }
                catch (NotFoundException l_nfex)
                {
                    //該当する銘柄が存在しない場合は例外をスローする。
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                              this.getClass().getName() + STR_METHOD_NAME);
                }

                //未決済の建玉照会明細の1明細を作成する。
                l_positionManager.createOptionUnSettledContractInquiryDetails(
                    l_lstContractReferenceUnits, //建玉照会明細
                    l_contract                   //先物OP建玉
                    );

            }

            WEB3OptionsContractReferenceUnit[] l_contractReferenceUnits =
                new WEB3OptionsContractReferenceUnit[l_lstContractReferenceUnits.size()];
            l_lstContractReferenceUnits.toArray(l_contractReferenceUnits);

            List l_lstContractUnits = new ArrayList();

            for (int i = 0; i < l_contractReferenceUnits.length; i++)
            {
                //指定の建玉照会明細から建玉明細を作成する。
                WEB3FuturesOptionsContractUnit l_contractUnit = this.createContractDetails(l_contractReferenceUnits[i]);

                l_lstContractUnits.add(l_contractUnit);

            }

            WEB3FuturesOptionsContractUnit[] l_contractUnits =
                new WEB3FuturesOptionsContractUnit[l_lstContractUnits.size()];
            l_lstContractUnits.toArray(l_contractUnits);


            //指定されたソートキー、昇降順にもどついて建玉明細のソートを行う。
            l_contractUnits = sortContractDetails(l_contractUnits, l_request.futOpSortKeys);


            //レスポンス.銘柄名 = 建玉照会明細[0].銘柄名
            l_response.opProductName = l_contractReferenceUnits[0].opProductName;

            //レスポンス.建区分 = 建玉照会明細[0].建区分
            l_response.contractType = l_contractReferenceUnits[0].contractType;

            //レスポンス.取引市場 = 建玉照会明細[0].取引市場
            l_response.marketCode = l_contractReferenceUnits[0].marketCode;


            WEB3IfoContractImpl l_contract0 = (WEB3IfoContractImpl)l_positionManager.getContract(Long.parseLong(l_contractReferenceUnits[0].id));

            //レスポンス.現在値 = get建玉時価の返り値
            double l_dblCurrentPrice = l_positionManager.getContractCurrentPrice(l_contract0);
            if (Double.isNaN(l_dblCurrentPrice))
            {
                l_dblCurrentPrice = 0D;
            }

            l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);

            //レスポンス.建玉明細 = sort建玉明細の返り値
            l_response.contractUnits = l_contractUnits;


            //市場閉局警告指数
            String[] l_strTradeCloseSuspensions = null;
            l_strTradeCloseSuspensions = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.OPTION);
            l_response.messageSuspension = l_strTradeCloseSuspensions;

        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (create建玉明細)<BR>
     * <BR>
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
     * 建玉明細.立会区分 = パラメータ.建玉照会明細.立会区分 <BR>
     * <BR>
     * (3)(2)の建玉明細オブジェクトを返却する<BR>
     * @@param l_contractReferenceUnit - 株価指数オプション建玉照会明細オブジェクト
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsContractUnit
     * @@roseuid 4087B22E0211
     */
    protected WEB3FuturesOptionsContractUnit createContractDetails(WEB3OptionsContractReferenceUnit l_contractReferenceUnit)
    {
        final String STR_METHOD_NAME = "createContractDetails(WEB3OptionsContractReferenceUnit l_contractReferenceDetails)";
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
        l_contractUnit.settlePriority = null; //決済順位
        l_contractUnit.contractExecQuantity = null; //返済約定数量
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
     * <BR>
     * 指定されたソートキー、昇降順にもどついて建玉明細のソートを行う。<BR>
     * <BR>
     * (1)ArrayListを作成<BR>
     * <BR>
     * (2)パラメータ.ソートキーの配列数分Loop処理<BR>
     * 　@(2-1)パラメータ.ソートキー.キー項目を取得<BR>
     * <BR>
     * 　@(2-2)パラメータ.ソートキー.昇順/降順を取得<BR>
     * <BR>
     * 　@(2-3)キー項目による分岐処理 <BR>
     * 　@　@キー項目が建日であった場合、 <BR>
     * 　@　@　@建日Comparator、立会区分Comparatorを生成 <BR>
     * 　@　@[コンストラクタのパラメータ(*)=(2-2)で取得した昇順/降順] <BR>
     * 　@　@(*)パラメータに建日のキー項目で取得した値を設定する。<BR>
     * <BR>
     * 　@(2-4)(2-3)にて作成したComparatorをArrayListに追加<BR>
     * <BR>
     * (3)ArrayListからComparatorの配列を作成<BR>
     * <BR>
     * (4)Comparatorの配列順のソート処理<BR>
     * (web3-common)WEB3ArraysUtility.sort(パラメータ.建玉明細、Comparator[])<BR>
     * <BR>
     * (5)ソートされた建玉明細の配列を返却<BR>
     * @@param l_contractUnits - 建玉明細の配列
     * @@param l_sortKey - 株価指数先物オプションソートキーの配列
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsContractUnit[]
     * @@roseuid 4087B30402FE
     */
    protected WEB3FuturesOptionsContractUnit[] sortContractDetails(WEB3FuturesOptionsContractUnit[] l_contractUnits, WEB3FuturesOptionsSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = " sortContractDetails(WEB3FuturesOptionsContractUnit[] l_contractDetails, WEB3FuturesOptionsSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);

        List l_lstComparators = new ArrayList();

        for (int i = 0; i < l_sortKey.length; i++)
        {

            String l_strKeyItem = l_sortKey[i].keyItem;
            String l_strAscDesc = l_sortKey[i].ascDesc;


            Comparator l_com = null;

            if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {
                //キー項目が建日であった場合、建日Comparatorを生成
                l_com = new WEB3OptionsOpenDateComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
                //立会区分Comparatorを生成
                l_com = new WEB3OptionsSessionTypeComparator(l_strAscDesc);
                l_lstComparators.add(l_com);
            }
        }

        Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
        l_lstComparators.toArray(l_comparators);

        WEB3ArraysUtility.sort(l_contractUnits, l_comparators);
        log.exiting(STR_METHOD_NAME);

        return l_contractUnits;
    }
}
@
