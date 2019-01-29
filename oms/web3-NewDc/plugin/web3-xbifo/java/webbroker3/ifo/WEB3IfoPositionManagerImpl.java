head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoPositionManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OPポジションマネージャ(WEB3IfoPositionManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 王蘭芬(中訊) 新規作成
Revesion History : 2007/06/08 張騰宇(中訊) モデル645、651、691
Revesion History : 2007/06/21 金傑(中訊) モデル723、727
Revesion History : 2007/06/29 金傑(中訊) モデル753
Revesion History : 2008/02/20 趙林鵬(中訊) 先物・OP不具合対応
Revesion History : 2008/03/13 金傑(中訊) モデル824
Revesion History : 2008/04/08 趙林鵬(中訊) OP返済一覧の改善対応
Revesion History : 2008/08/19 安陽(中訊) IFO小数点対応
*/

package webbroker3.ifo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Contract;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultSortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FilterQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoClosingContractSpecImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoPositionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoPositionManagerHelper.WEB3IfoPersistentDataManager;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3IfoSettlementStateDef;
import webbroker3.ifo.define.WEB3IfoSettlementStatusDef;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsDetailUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3OptionsContractReferenceUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (先物OPポジションマネージャ)<BR>
 * 先物OPポジションマネージャクラス<BR>
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3IfoPositionManagerImpl extends IfoPositionManagerImpl
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoPositionManagerImpl.class);

    /**
     * @@roseuid 40C0885002DE
     */
    public WEB3IfoPositionManagerImpl()
    {
        m_helper = null;
        m_tradingType = null;
        m_tradingType = ProductTypeEnum.IFO;
        m_helper = new WEB3IfoPositionManagerHelper(m_tradingType);
    }

    /**
     * (create銘柄コード名称from建玉)<BR>
     * <BR>
     * 指定口座の保持する指定決済状態建玉(is未決済)の銘柄コード<BR>
     * と銘柄名の一覧を取得する。<BR>
     * (先物／オプション区分がnullの場合は、先物／オプション両方の一覧<BR>
     * を取得)<BR>
     * <BR>
     * 該当データが存在しない場合にはNULLを返却する。<BR>
     * <BR>
     * (1)建玉一覧取得時のソートキーの作成<BR>
     * ソートキー = "銘柄ID ASC"<BR>
     * ソートキーをパラメータにDefaultSortKeySpecを作成<BR>
     * <BR>
     * (2)建玉一覧を取得<BR>
     * getConstracts(パラメータ.補助口座、DefaultSortKeySpec、<BR>
     * 6(先物オプションの銘柄タイプ)) <BR>
     * <BR>
     * <BR>
     * (3)TreeMapを作成<BR>
     * <BR>
     * (4)建玉の要素数分Loop処理<BR>
     * ※getContracts()の返り値がNULL、または、返り値のリストの要素数が0の場合はNULLを返却する<BR>
     * 　@(4-1)パラメータ.先物／オプション区分 == nullの場合　@または<BR>
     * 　@　@　@　@is対象建玉(建玉[インデックス番号], 
     * パラメータ.先物／オプション区分)が<BR>
     * 　@　@　@　@trueの場合は、(4-2)以降の処理を行う<BR>
     * <BR>
     * 　@(4-2)未決済建玉のみ対象(is未決済==true)の場合<BR>
     * 　@　@　@get決済状態(建玉)の返り値が"新規建約定取消"または"前日以前決済済"または<BR>
     * "当日決済済"以外の場合、(4-4)を行う。<BR>
     * <BR>
     * 　@(4-3)未決済建玉以外も対象(is未決済==false)の場合<BR>
     * 　@　@　@get決済状態(建玉)の返り値が"新規建約定取消"または"前日以前決済済"<BR>
     * 以外の場合、(4-4)を行う。<BR>
     * <BR>
     * 　@(4-4)銘柄コードおよび銘柄名の取得<BR>
     * 　@　@銘柄ID = 建玉.get銘柄ID()<BR>
     * 　@　@先物OP銘柄 = 先物OPプロダクトマネージャ.getProduct(銘柄ID)<BR>
     * 　@　@銘柄コード = 先物OP銘柄.get銘柄コード()<BR>
     * 　@　@銘柄名 = 先物OP銘柄.get銘柄名()<BR>
     * <BR>
     * 　@  株価指数先物オプション銘柄コード名称オブジェクトを生成<BR>
     * <BR>
     * 　@　@株価指数先物オプション銘柄コード名称.銘柄コード = 銘柄コード<BR>
     * 　@　@株価指数先物オプション銘柄コード名称.銘柄名 = 銘柄名<BR>
     * 　@　@TreeMap.put(銘柄コード、株価指数先物オプション銘柄コード名称)<BR>
     * <BR>
     * (5)TreeMap.values().toArray()の結果を返却<BR>
     * 　@　@※TreeMapのサイズが0の場合はNULLを返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_blnIsUnSettlement - (is未決済)<BR>
     * <BR>
     * 対象建玉の判定。<BR>
     * 未決済建玉、決済中建玉のみが対象の場合true、未決済建玉、<BR>
     * 決済中建玉に加え当日決済済建玉を含む場合false。<BR>
     * <BR>
     * @@param l_strFuturesOptionDivision - (先物／オプション区分)<BR>
     * <BR>
     * 取得対象建玉の先物／オプション区分<BR>
     * 1： 先物<BR>
     * 2： オプション<BR>
     * ※nullの場合は先物とオプション両方のデータを返却<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407B994000F9
     */
    public WEB3FuturesOptionsProductCodeNameUnit[] createProductCodeNameFromContract(WEB3GentradeSubAccount l_subAccount, boolean l_blnIsUnSettlement, String l_strFuturesOptionDivision)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createProductCodeNameFromContract";
        log.entering(STR_METHOD_NAME);

        // (1)建玉一覧取得時のソートキーの作成
        // ソートキー = "銘柄ID ASC"
        DefaultSortKeySpec l_sortKeySpec = new DefaultSortKeySpec("product_id ASC");

        // (2)建玉一覧を取得
        String[] l_strProductType = { Integer.toString(ProductTypeEnum.IFO.intValue())};
        FilterQueryParamsSpec l_filterQueryParamsSpec = new FilterQueryParamsSpec("product_type=?", l_strProductType);
        List l_lisContracts = getContracts(l_subAccount, l_filterQueryParamsSpec, l_sortKeySpec);

        // (3)TreeMapを作成
        TreeMap l_tmUnSettlementContract = new TreeMap();

        // (4)建玉の要素数分Loop処理
        if (l_lisContracts == null || l_lisContracts.size() == 0)
        {
            //getContracts()の返り値がNULL、または返り値のリストの要素数が0の場合はNULLを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        for (int i = 0; i < l_lisContracts.size(); i++)
        {
            WEB3IfoContractImpl l_ifocontractImpl = (WEB3IfoContractImpl) l_lisContracts.get(i);

            // temp flag
            boolean l_blnFlag = false;

            // is対象建玉(建玉[インデックス番号], パラメータ.先物／オプション区分)
            boolean l_blnIsObjectContract = isObjectContract(l_ifocontractImpl, l_strFuturesOptionDivision);

            int l_intSettlementStatus = getSettlementStatus(l_ifocontractImpl);

            // (4-1)パラメータ.先物／オプション区分 == nullの場合 または
            //      l_blnIsObjectContractがtrueの場合
            if (l_strFuturesOptionDivision == null || l_blnIsObjectContract)
            {
                if (l_blnIsUnSettlement)
                {
                    // (4-2)未決済建玉のみ対象(is未決済==true)の場合
                    //  get決済状態(建玉)の返り値が新規建約定取消、前日以前決済済、
                    //  または"当日決済済"以外の場合、(4-4)を行う
                    if (l_intSettlementStatus != WEB3IfoSettlementStatusDef.OPENCONTRACT_EXECUTE_CANCLE
                            && l_intSettlementStatus != WEB3IfoSettlementStatusDef.BEFORE_YESTODAY_SETTLED 
                            && l_intSettlementStatus != WEB3IfoSettlementStatusDef.SETTLED)
                    {
                        log.debug("未決済建玉のみ対象： get決済状態(建玉)の返り値が" +
                            "「新規建約定取消」、「前日以前決済済」、「当日決済済以外の場合」");

                        l_blnFlag = true;
                    }
                }
                else
                {
                    // (4-3)未決済建玉以外も対象(is未決済==false)の場合<BR>
                    // get決済状態(建玉)の返り値が新規建約定取消、前日以前決済済以外の場合
                    if (l_intSettlementStatus !=WEB3IfoSettlementStatusDef.OPENCONTRACT_EXECUTE_CANCLE 
                            && l_intSettlementStatus != WEB3IfoSettlementStatusDef.BEFORE_YESTODAY_SETTLED)
                    {
                        log.debug("未決済建玉以外も対象： get決済状態(建玉)の返り値が" +
                            "「新規建約定取消」、「前日以前決済済以外の場合」");

                        l_blnFlag = true;
                    }
                }

            }
            // (4-4)銘柄コードおよび銘柄名の取得
            if (l_blnFlag)
            {
                try
                {
                    // 銘柄ID = 建玉.get銘柄ID()
                    IfoContractParams l_ifoContractParams = new IfoContractParams((IfoContractRow) l_ifocontractImpl.getDataSourceObject());

                    long l_lngProductId = l_ifoContractParams.getProductId();

                    // 先物OP銘柄 = 先物OPプロダクトマネージャ.getProduct(銘柄ID)
                    FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                    TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                    WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
                    Product l_product = l_productManager.getProduct(l_lngProductId);
                    // 銘柄コード = 先物OP銘柄.get銘柄コード()
                    IfoProductParams l_porductParams = new IfoProductParams((IfoProductRow) l_product.getDataSourceObject());
                    String l_strProductCode = l_porductParams.getProductCode();
                    // 銘柄名 = 先物OP銘柄.get銘柄名()
                    String l_strStandName = l_porductParams.getStandardName();
           
                    //キー項目(銘柄コード)がリストに追加済でない場合のみ、
                    //銘柄コード名称クラスを生成し、銘柄コードと銘柄名をセットし、追加する
                    if (!l_tmUnSettlementContract.containsKey(l_strProductCode))
                    {
                        WEB3FuturesOptionsProductCodeNameUnit l_ProductCodeNameUnit = new WEB3FuturesOptionsProductCodeNameUnit();
                        l_ProductCodeNameUnit.productCode = l_strProductCode;
                        l_ProductCodeNameUnit.productName = l_strStandName;
                        
                        // TreeMap.put(銘柄コード、株価指数先物オプション銘柄コード名称)
                        l_tmUnSettlementContract.put(l_strProductCode, l_ProductCodeNameUnit);
                    }
                }
                catch (NotFoundException l_ex)
                {
                    log.error("Error In Method: " + STR_METHOD_NAME, l_ex);
                    throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
                }
            }
        }

        // (5)TreeMap.values().toArray()の結果を返却
        int l_tmSize = l_tmUnSettlementContract.size();
        if (l_tmSize == 0)
        {
            //TreeMapのサイズが0の場合はNULLを返却
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3FuturesOptionsProductCodeNameUnit[] l_returnValues = new WEB3FuturesOptionsProductCodeNameUnit[l_tmSize];      
        l_tmUnSettlementContract.values().toArray(l_returnValues);

        log.exiting(STR_METHOD_NAME);
        return l_returnValues;
    }

    /**
     * (createOP建玉照会明細)<BR>
     * <BR>
     * 株価指数オプション建玉照会画面に表示する建玉照会明細の一覧を作成する。<BR>
     * <BR>
     * 該当データが存在しない場合にはNULLを返却する。<BR>
     * <BR>
     * 「（先物OP残高）createOP建玉照会明細」参照。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_strFutureOptionDivision - (先物／オプション区分)<BR>
     * <BR>
     * 1：先物<BR>
     * 2：オプション<BR>
     * @@param l_strDesSettlementStatus - (指定決済状態)<BR>
     * <BR>
     * 下記のいずれか。<BR>
     * <BR>
     * null：指定なし <BR>
     * 0：決済済<BR>
     * 1：未決済<BR>
     * 2：決済中<BR>
     * @@param l_strSearchConditionString - 検索条件文字列<BR>
     * @@param l_strSearchConditionDataContainer - (検索条件データコンテナ)<BR>
     * <BR>
     * 検索条件<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsContractReferenceUnit[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4075477303CC
     */
    public WEB3OptionsContractReferenceUnit[] createOptionsContractReferenceUnits(
        WEB3GentradeSubAccount l_subAccount,
        String l_strFuturesOptionDivision,
        String l_strDesSettlementStatus,
        String l_strSearchCondition,
        String[] l_strSearchConditionDataContainer)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOptionsContractReferenceUnits";
        log.entering(STR_METHOD_NAME);

        // 1.  get建玉一覧(補助口座, ProductTypeEnum, String, String[])
        List l_lisContracts = getContracts(l_subAccount, ProductTypeEnum.IFO, l_strSearchCondition, l_strSearchConditionDataContainer);

        // 2.戻り値判定
        if (l_lisContracts == null || l_lisContracts.size() == 0)
        {

            // 3. 建玉の要素数が0の場合nullを返却
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // 4.建玉照会明細を格納する建玉照会明細リストを生成する。
        ArrayList l_lisContractReferenceUnits = new ArrayList();

        for (int i = 0; i < l_lisContracts.size(); i++)
        {
            try
            {
                IfoContractRow l_ifoContractRow = (IfoContractRow) l_lisContracts.get(i);
                WEB3IfoContractImpl l_ifocontractImpl = new WEB3IfoContractImpl(l_ifoContractRow);

                log.debug("(WEB3IfoContractImpl)l_lisContracts.get(" + i + ") = " + l_ifocontractImpl);

                // 5.is対象建玉(先物OP建玉, String)
                boolean l_blnFlag = isObjectContract(l_ifocontractImpl, l_strFuturesOptionDivision);
                if (!l_blnFlag)
                {

                    log.debug("is対象建玉(先物OP建玉, String)がfalseの場合 continue");
                    continue;
                }
                // 6.銘柄IDを取得する。
                long l_lngProductId = l_ifoContractRow.getProductId();

                // 7.先物OP銘柄 = 先物OPプロダクトマネージャ.getProduct(銘柄ID)
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
                WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);

                // 8.銘柄コード = 先物OP銘柄.get原資産銘柄コード()   
                String l_strProductCode = l_product.getUnderlyingProductCode();

                // 9.reset銘柄コード(String)
                WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);
                // 10.get決済状態(先物OP建玉)
                int l_intSettlementStatus = getSettlementStatus(l_ifocontractImpl);

                switch (l_intSettlementStatus)
                {
                    // 11."决済済"の場合のみ
                    case WEB3IfoSettlementStatusDef.BEFORE_YESTODAY_SETTLED :
                    case WEB3IfoSettlementStatusDef.SETTLED :
                        log.debug("createOP決済済建玉照会明細( ArrayList, 先物OP建玉)");

                        // 12.createOP決済済建玉照会明細(ArrayList, 先物OP建玉)
                        createOptionSettledContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl);
                        break;
                        // 13."未决済"の場合のみ
                    case WEB3IfoSettlementStatusDef.UNSETTLED :
                        log.debug("createOP未決済建玉照会明細(ArrayList,  先物OP建玉");

                        // 14.createOP未決済建玉照会明細(ArrayList, 先物OP建玉)
                        createOptionUnSettledContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl);
                        break;
                        // 15."决済中"の場合のみ
                    case WEB3IfoSettlementStatusDef.SETTLING :
                        // --- Test Log
                        log.debug("createOP決済中建玉照会明細(ArrayList, 先物OP建玉");

                        // 16.createOP決済中建玉照会明細(ArrayList, 先物OP建玉)
                        createOptionSettlingContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl);
                        break;
                        // get決済状態の返り値が
                        // ("未決済と決済中"、"決済済と未決済"、
                        //  "決済済と未決済と決済中"、"決済済と決済中"）
                    case WEB3IfoSettlementStatusDef.UNSETTLED_SETTLING:
                    case WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED:
                    case WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED_SETTLING:
                    case WEB3IfoSettlementStatusDef.SETTLED_SETTLING:

                        // 18.createOP複数建玉照会明細(ArrayList,  先物OP建玉, int)
                        createMultiOptionContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl, l_intSettlementStatus);
                        break;
                }

            }
            catch (NotFoundException l_ex)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);

            }
        }
        // 19.指定決済状態がある場合
        if (l_strDesSettlementStatus != null)
        {

            // 20.get指定決済状態建玉明細(String, ArrayList)
            getSpecSettlementStatusContractDetails(l_strDesSettlementStatus, l_lisContractReferenceUnits);


            // 21.toArray( ) and returns
            WEB3OptionsContractReferenceUnit[] l_lisReturn = new WEB3OptionsContractReferenceUnit[l_lisContractReferenceUnits.size()];
            l_lisContractReferenceUnits.toArray(l_lisReturn);

            log.exiting(STR_METHOD_NAME);
            return l_lisReturn;
        }

        // 22.toArray( ) and returns
        WEB3OptionsContractReferenceUnit[] l_lisReturn = new WEB3OptionsContractReferenceUnit[l_lisContractReferenceUnits.size()];
        l_lisContractReferenceUnits.toArray(l_lisReturn);

        log.exiting(STR_METHOD_NAME);
        return l_lisReturn;
    }

    /**
     * (createOP決済済建玉照会明細)<BR>
     * <BR>
     * 当日決済済の1明細を作成する。（オプション）<BR>
     * <BR>
     * 「（先物OP残高）createOP決済済建玉照会明細」参照。<BR>
     * @@param l_lisContractInquiryDetails - (建玉照会明細リスト)<BR>
     * <BR>
     * 作成した明細を格納するリスト。<BR>
     * @@param l_ifoContract - (先物OP建玉)<BR>
     * <BR>
     * 明細を作成する建玉。<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407DF31202B7
     */
    public void createOptionSettledContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOptionSettledContractInquiryDetails";
        log.entering(STR_METHOD_NAME);

        // 1.株価指数オプション建玉照会明細を生成する。
        WEB3OptionsContractReferenceUnit l_contractReferenceUnit = new WEB3OptionsContractReferenceUnit();

        // 2.getContractId( ) --- 建玉IDを取得する
        long l_lngContractId = l_ifoContract.getContractId();
        IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();

        // 3.get当日返済約定数量(long, Date)
        double l_dblOrderQuantity = getDayClosingContractExecutionCnt(l_lngContractId, (Date) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        log.debug("l_lngContractId = " + l_lngContractId);
        log.debug("l_dblOrderQuantity = " + l_dblOrderQuantity);

        // 4.getトランザクション(long, EQTYPE_IDX_OPTIONS_CLOSE , Date)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoFinTransactionManagerImpl l_finTransactionManager = (WEB3IfoFinTransactionManagerImpl) l_tradingModule.getFinTransactionManager();

        FinTransactionCateg l_transactionCategory = FinTransactionCateg.EQTYPE_IDX_OPTIONS_CLOSE;

        List l_lisTransactions =
            l_finTransactionManager.getTransactions(l_lngContractId, l_transactionCategory, (Date) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        BigDecimal l_bdNetAmountTotal = new BigDecimal("0");
        BigDecimal l_bdSetupFeeTotal = new BigDecimal("0");
        for (int i = 0; i < l_lisTransactions.size(); i++)
        {
            IfoFinTransactionRow l_transactionRow = (IfoFinTransactionRow) l_lisTransactions.get(i);

            BigDecimal l_bdNetAmount = new BigDecimal(l_transactionRow.getNetAmount() + "");
            BigDecimal l_bdImportedSetupFee = new BigDecimal(l_transactionRow.getImportedSetupFee() + "");
            BigDecimal l_bdImportedSetupFeeTax = new BigDecimal(l_transactionRow.getImportedSetupFeeTax() + "");

            // 5. getNetAmount()
            l_bdNetAmountTotal = l_bdNetAmountTotal.add(l_bdNetAmount);
            // 6.getImportedSetupFee()
            l_bdSetupFeeTotal = l_bdSetupFeeTotal.add(l_bdImportedSetupFee);
            // 7.getImportedSetupFeeTax()
            l_bdSetupFeeTotal = l_bdSetupFeeTotal.add(l_bdImportedSetupFeeTax);
        }

        try
        {
            // 銘柄IDを取得する。
            long l_lngProductId = l_ifoContractParams.getProductId();
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);
            IfoProductParams l_porductParams = (IfoProductParams) l_product.getDataSourceObject();
            // 先物OP取引銘柄を取得する
            WEB3IfoTradedProductImpl l_tradedproduct = (WEB3IfoTradedProductImpl) l_productManager.getTradedProduct(l_lngProductId, l_porductParams.getTargetMarketId());

            // 8.プロパティセット
            l_contractReferenceUnit.id = "" + l_ifoContractParams.getContractId();
            l_contractReferenceUnit.opProductCode = l_porductParams.getProductCode();
            l_contractReferenceUnit.opProductName = l_porductParams.getStandardName();
            l_contractReferenceUnit.targetProductCode = l_porductParams.getUnderlyingProductCode();
            l_contractReferenceUnit.delivaryMonth = l_porductParams.getMonthOfDelivery();

            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_porductParams.getDerivativeType()))
            {
                l_contractReferenceUnit.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_porductParams.getDerivativeType()))
            {
                l_contractReferenceUnit.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }


            l_contractReferenceUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_porductParams.getStrikePrice());

            // get MarketCode
            long l_lngMardetId = l_porductParams.getTargetMarketId();
            WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            String l_strMarketCode = l_finOjbectManager.getMarket(l_lngMardetId).getMarketCode();
            l_contractReferenceUnit.marketCode = l_strMarketCode;
            
            l_contractReferenceUnit.contractType = String.valueOf(l_ifoContractParams.getContractType().intValue());

            l_contractReferenceUnit.openDate = WEB3DateUtility.toDay(l_ifoContractParams.getOpenDate());

            l_contractReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity);

            l_contractReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContractParams.getContractPrice());

            l_contractReferenceUnit.settlementState = WEB3IfoSettlementStateDef.SETTLEMENT_END;

            l_contractReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractExecutedAmount(l_dblOrderQuantity));

            l_contractReferenceUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_bdSetupFeeTotal.doubleValue());

            l_contractReferenceUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedproduct.getLastTradingDate());

            l_contractReferenceUnit.income = WEB3StringTypeUtility.formatNumber(l_bdNetAmountTotal.doubleValue());
            l_contractReferenceUnit.sessionType =
                ((IfoContractRow)l_ifoContract.getDataSourceObject()).getSessionType();

            // 9.add(arg0 : Object)
            l_lisContractInquiryDetails.add(l_contractReferenceUnit);

            try
            {
                //事前チェック
                Long.parseLong(l_contractReferenceUnit.contractCommission);
            }
            catch (NumberFormatException l_nfe)
            {
                StringBuffer l_sb = new StringBuffer(STR_METHOD_NAME);
                l_sb.append(" contract=" + l_ifoContract.getDataSourceObject());
                l_sb.append(", transactions=" + l_lisTransactions);
                l_sb.append(", setupFeeTotal=" + l_bdSetupFeeTotal);
                log.error(l_sb.toString(), l_nfe);
            }

            log.exiting(STR_METHOD_NAME);
            return;
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

    }

    /**
     * (createOP未決済建玉照会明細)<BR>
     * <BR>
     * 未決済の1明細を作成する。（オプション）<BR>
     * <BR>
     * 「（先物OP残高）createOP未決済建玉照会明細」参照。<BR>
     * @@param l_lisContractInquiryDetails - (建玉照会明細リスト)<BR>
     * <BR>
     * 作成した明細を格納するリスト。<BR>
     * @@param l_ifoContract - (先物OP建玉)<BR>
     * <BR>
     * 明細を作成する建玉。<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407DF356019E
     */
    public void createOptionUnSettledContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOptionUnSettledContractInquiryDetails";
        log.entering(STR_METHOD_NAME);

        // 株価指数オプション建玉照会明細を生成する。
        WEB3OptionsContractReferenceUnit l_contractReferenceUnit = new WEB3OptionsContractReferenceUnit();
        
        // 決済中数量を取得する。
        double l_dblLockedQuantity = l_ifoContract.getLockedQuantity();

        // 未決済数量を取得する。
        BigDecimal l_bdQuantity = new BigDecimal(l_ifoContract.getQuantity() + "");
        BigDecimal l_bdLockedQuantity = new BigDecimal(l_dblLockedQuantity + "");
        double l_dblQuantity = l_bdQuantity.subtract(l_bdLockedQuantity).doubleValue();
        
        // 時価を取得する。
        double l_dblContractcurrentPrice = getContractCurrentPrice(l_ifoContract);

        // 評価損益を取得する。
        double l_dblEvaluateIncome = l_ifoContract.getEvaluateIncome(l_dblContractcurrentPrice, l_dblQuantity);
       
        // 決済中の建手数料を取得する。
        double l_dblLockedContractCommission = l_ifoContract.getContractCommission(l_dblLockedQuantity);

        // 決済中の建手数料消費税を取得する。
        double l_dblLockedContractCommissionTax = l_ifoContract.getContractCommissionConsumptionTax(l_dblLockedQuantity);
            
        // 建手数料＝(未決済＋決済中の建手数料)－決済中の建手数料
        BigDecimal l_bdSetupFee = new BigDecimal(l_ifoContract.getSetupFee() + "");
        BigDecimal l_bdLockedContractCommission = new BigDecimal(l_dblLockedContractCommission + "");

        BigDecimal l_bdContractCommission = l_bdSetupFee.subtract(l_bdLockedContractCommission);

        // 建手数料消費税＝(未決済＋決済中の建手数料消費税)－決済中の建手数料消費税
        BigDecimal l_bdSetupFeeTax = new BigDecimal(l_ifoContract.getSetupFeeTax() + "");
        BigDecimal l_bdLockedContractCommissionTax = new BigDecimal(l_dblLockedContractCommissionTax + "");
        
        BigDecimal l_bdContractCommissionTax = l_bdSetupFeeTax.subtract(l_bdLockedContractCommissionTax);
        
        // 建手数料＋建手数料消費税
        BigDecimal l_bdCost = l_bdContractCommission.add(l_bdContractCommissionTax);
        
        IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();
        try
        {
            // 銘柄IDを取得する。
            long l_lngProductId = l_ifoContractParams.getProductId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);
            IfoProductParams l_porductParams = (IfoProductParams) l_product.getDataSourceObject();
            // 先物OP取引銘柄を取得する
            WEB3IfoTradedProductImpl l_tradedproduct = (WEB3IfoTradedProductImpl) l_productManager.getTradedProduct(l_lngProductId, l_porductParams.getTargetMarketId());

            // 8.プロパティセット
            l_contractReferenceUnit.id = "" + l_ifoContractParams.getContractId();
            l_contractReferenceUnit.opProductCode = l_porductParams.getProductCode();
            l_contractReferenceUnit.opProductName = l_porductParams.getStandardName();
            l_contractReferenceUnit.targetProductCode = l_porductParams.getUnderlyingProductCode();
            l_contractReferenceUnit.delivaryMonth = l_porductParams.getMonthOfDelivery();


            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_porductParams.getDerivativeType()))
            {
                l_contractReferenceUnit.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_porductParams.getDerivativeType()))
            {
                l_contractReferenceUnit.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }

            l_contractReferenceUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_porductParams.getStrikePrice());

            // get MarketCode
            long l_lngMardetId = l_porductParams.getTargetMarketId();
            WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            String l_strMarketCode = l_finOjbectManager.getMarket(l_lngMardetId).getMarketCode();
            
            l_contractReferenceUnit.marketCode = l_strMarketCode;
            l_contractReferenceUnit.contractType = String.valueOf(l_ifoContractParams.getContractType().intValue());

            l_contractReferenceUnit.openDate = WEB3DateUtility.toDay(l_ifoContractParams.getOpenDate());

            l_contractReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);

            l_contractReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContractParams.getContractPrice());

            l_contractReferenceUnit.settlementState = WEB3IfoSettlementStateDef.HAVE_NOT_SETTLED;

            l_contractReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractExecutedAmount(l_dblQuantity));

            l_contractReferenceUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_bdCost.doubleValue());

            l_contractReferenceUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedproduct.getLastTradingDate());

            l_contractReferenceUnit.income = WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncome);
            
            BigDecimal l_bdEvaluateIncome = new BigDecimal(l_dblEvaluateIncome + "");
            l_contractReferenceUnit.incomeCost =
                WEB3StringTypeUtility.formatNumber((l_bdEvaluateIncome.subtract(l_bdCost)).doubleValue());

            l_contractReferenceUnit.sessionType =
                ((IfoContractRow)l_ifoContract.getDataSourceObject()).getSessionType();

            l_lisContractInquiryDetails.add(l_contractReferenceUnit);

            try
            {
                //事前チェック
                Long.parseLong(l_contractReferenceUnit.contractCommission);
            }
            catch (NumberFormatException l_nfe)
            {
                StringBuffer l_sb = new StringBuffer(STR_METHOD_NAME);
                l_sb.append(" contract=" + l_ifoContract.getDataSourceObject());
                l_sb.append(", lockedQuantity=" + l_dblLockedQuantity);
                l_sb.append(", lockedContractCommission=" + l_dblLockedContractCommission);
                l_sb.append(", lockedContractCommissionTax=" + l_dblLockedContractCommissionTax);
                l_sb.append(", contractCommission=" + l_bdContractCommission);
                l_sb.append(", contractCommissionTax=" + l_bdContractCommissionTax);
                l_sb.append(", cost=" + l_bdCost);
                log.error(l_sb.toString(), l_nfe);
            }

            log.exiting(STR_METHOD_NAME);
            return;
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (createOP決済中建玉照会明細)<BR>
     * <BR>
     * 決済中の1明細を作成する。（オプション）<BR>
     * <BR>
     * 「（先物OP残高）createOP決済中建玉照会明細」参照。<BR>
     * @@param l_lisContractInquiryDetails - (建玉照会明細リスト)<BR>
     * <BR>
     * 作成した明細を格納するリスト。<BR>
     * @@param l_ifoContract - (先物OP建玉)<BR>
     * <BR>
     * 明細を作成する建玉。<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407DF37101BD
     */
    public void createOptionSettlingContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOptionSettlingContractInquiryDetails";
        log.entering(STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

            WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();

            // 1.株価指数オプション建玉照会明細を生成する。
            WEB3OptionsContractReferenceUnit l_contractReferenceUnit = new WEB3OptionsContractReferenceUnit();

            // 2.決済注文中数量を取得する。
            double l_dblLockedQuality = l_ifoContract.getLockedQuantity();

            // 3.建玉IDを取得する
            long l_lngContractId = l_ifoContract.getContractId();

            // 4.get返済指定情報(建玉ID)
            IfoClosingContractSpec[] l_lisContractSpecs = this.getClosingContractSpecs(l_lngContractId);

            IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();
            BigDecimal l_bdEvaluateIncome = new BigDecimal("0");

            // 5.時価を取得する。
            double l_dblContractcurrentPrice = this.getContractCurrentPrice(l_ifoContract);

            if (l_lisContractSpecs != null)
            {
                for (int i = 0; i < l_lisContractSpecs.length; i++)
                {
                    // 6.市場確認済返済数量を取得する
                    //市場確認済返済数量 == nullの場合、注文数量として返済注文数量を使用する
                    IfoClosingContractSpecRow l_specRow =
                        (IfoClosingContractSpecRow) l_lisContractSpecs[i].getDataSourceObject();
                    double l_dblQuantity = 0D;
                    if (!l_specRow.getConfirmedQuantityIsNull())
                    {
                        //注文数量：建玉返済指定情報.市場確認済返済数量
                        l_dblQuantity = l_specRow.getConfirmedQuantity();
                    }
                    else
                    {
                        //注文数量：建玉返済指定情報.返済注文数量
                        l_dblQuantity = l_specRow.getQuantity();
                    }

                    //返済約定数量を取得する。
                    double l_dblExecutedQuantity = l_lisContractSpecs[i].getExecutedQuantity();
                    if (Double.isNaN(l_dblExecutedQuantity))
                    {
                        l_dblExecutedQuantity = 0D;
                    }

                    //注文単位IDを取得する。
                    long l_lngOrderUintId = l_lisContractSpecs[i].getOrderUnitId();

                    // 注文単位を取得する。
                    IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUintId);

                    //注文有効状態を取得する。
                    OrderOpenStatusEnum l_statusEnum = l_orderUnit.getOrderOpenStatus();

                    //対象外注文チェック
                    //以下のいずれかに当てはまる場合は、対象外注文として次の要素に処理を移行する。
                    //・先物OP返済指定情報.getConfirmedQuantity() == 0
                    //・注文単位.getOrderOpenStatusEnum == "クローズ"
                    if (l_dblQuantity == 0 || OrderOpenStatusEnum.CLOSED.equals(l_statusEnum))
                    {
                        continue;
                    }

                    BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity + "");
                    BigDecimal l_bdExecutedQuantity = new BigDecimal(l_dblExecutedQuantity + "");
                    double l_dblSettlementCnt = l_bdQuantity.subtract(l_bdExecutedQuantity).doubleValue();
                    // 評価損益を取得する。
                    double l_dblIncome = l_ifoContract.getEvaluateIncome(
                        l_dblContractcurrentPrice,
                        l_dblSettlementCnt);

                    BigDecimal l_bdIncome = new BigDecimal(l_dblIncome + "");

                    l_bdEvaluateIncome = l_bdEvaluateIncome.add(l_bdIncome);
                }
            }

            // 10.get建手数料(double)
            double l_dblContractCommission = l_ifoContract.getContractCommission(l_dblLockedQuality);

            if (Double.isNaN(l_dblContractCommission))
            {
                l_dblContractCommission = 0D;
            }

            // 11.get建手数料消費税(double)
            double l_dblContractCommissionTax = l_ifoContract.getContractCommissionConsumptionTax(l_dblLockedQuality);

            if (Double.isNaN(l_dblContractCommissionTax))
            {
                l_dblContractCommissionTax = 0D;
            }

            //getSessionType( )
            IfoContractRow l_ifoContractRow = (IfoContractRow)l_ifoContract.getDataSourceObject();
            String l_strSessionType = l_ifoContractRow.getSessionType();

            // 建手数料＋建手数料消費税
            BigDecimal l_bdContractCommission = new BigDecimal(l_dblContractCommission + "");
            BigDecimal l_bdContractCommissionTax = new BigDecimal(l_dblContractCommissionTax + "");
            BigDecimal l_bdCost = l_bdContractCommission.add(l_bdContractCommissionTax);

            // 銘柄IDを取得する。
            long l_lngProductId = l_ifoContractParams.getProductId();

            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);
            IfoProductParams l_porductParams = (IfoProductParams) l_product.getDataSourceObject();
            // 先物OP取引銘柄を取得する
            WEB3IfoTradedProductImpl l_tradedproduct = (WEB3IfoTradedProductImpl) l_productManager.getTradedProduct(l_lngProductId, l_porductParams.getTargetMarketId());

            // プロパティセット
            l_contractReferenceUnit.id = "" + l_ifoContractParams.getContractId();
            l_contractReferenceUnit.opProductCode = l_porductParams.getProductCode();
            l_contractReferenceUnit.opProductName = l_porductParams.getStandardName();
            l_contractReferenceUnit.targetProductCode = l_porductParams.getUnderlyingProductCode();
            l_contractReferenceUnit.delivaryMonth = l_porductParams.getMonthOfDelivery();
            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_porductParams.getDerivativeType()))
            {
                l_contractReferenceUnit.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_porductParams.getDerivativeType()))
            {
                l_contractReferenceUnit.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }

            l_contractReferenceUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_porductParams.getStrikePrice());

            // get MarketCode
            long l_lngMardetId = l_porductParams.getTargetMarketId();
            WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            String l_strMarketCode = l_finOjbectManager.getMarket(l_lngMardetId).getMarketCode();
            l_contractReferenceUnit.marketCode = l_strMarketCode;
            
            l_contractReferenceUnit.contractType = String.valueOf(l_ifoContractParams.getContractType().intValue());

            l_contractReferenceUnit.openDate = WEB3DateUtility.toDay(l_ifoContractParams.getOpenDate());

            l_contractReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblLockedQuality);

            l_contractReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContractParams.getContractPrice());

            l_contractReferenceUnit.settlementState = WEB3IfoSettlementStateDef.SETTLING;

            l_contractReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractExecutedAmount(l_dblLockedQuality));

            l_contractReferenceUnit.contractCommission =
                WEB3StringTypeUtility.formatNumber(l_bdCost.doubleValue());

            l_contractReferenceUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedproduct.getLastTradingDate());

            l_contractReferenceUnit.income = WEB3StringTypeUtility.formatNumber(l_bdEvaluateIncome.doubleValue());
            
            l_contractReferenceUnit.incomeCost = WEB3StringTypeUtility.formatNumber(
                (l_bdEvaluateIncome.subtract(l_bdCost)).doubleValue());

            l_contractReferenceUnit.sessionType = l_strSessionType;

            l_lisContractInquiryDetails.add(l_contractReferenceUnit);

            try
            {
                //事前チェック
                Long.parseLong(l_contractReferenceUnit.contractCommission);
            }
            catch (NumberFormatException l_nfe)
            {
                StringBuffer l_sb = new StringBuffer(STR_METHOD_NAME);
                l_sb.append(" contract=" + l_ifoContract.getDataSourceObject());
                l_sb.append(", lockedQuantity=" + l_dblLockedQuality);
                l_sb.append(", contractCommission=" + l_dblContractCommission);
                l_sb.append(", contractCommissionTax=" + l_dblContractCommissionTax);
                l_sb.append(", cost=" + l_bdCost);
                log.error(l_sb.toString(), l_nfe);
            }

            log.exiting(STR_METHOD_NAME);
            return;
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

    }

    /**
     * (createOP複数建玉照会明細)<BR>
     * <BR>
     * 1つの建玉で複数の建玉照会明細を作成する場合の処理を行う。（オプション）<BR>
     * <BR>
     * 引数の決済状態にもとづき、<BR>
     * createOP決済済建玉照会明細<BR>
     * createOP未決済建玉照会明細<BR>
     * createOP決済中建玉照会明細<BR>
     * メソッドのいずれかをコールする。<BR>
     * <BR>
     * (1)決済状態：3の場合<BR>
     *   未決済と決済中の2明細を作成する。<BR>
     *  <BR>
     * 　@createOP未決済建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     *   createOP決済中建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     * <BR>
     * (2)決済状態：4の場合<BR>
     *   決済済と未決済の2明細を作成する。<BR>
     * <BR>
     *   createOP決済済建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     *   createOP未決済建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     * <BR>
     * (3)決済状態：5の場合<BR>
     *   決済済と未決済と決済中の3明細を作成する。<BR>
     * <BR>
     *   createOP決済済建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     *   createOP未決済建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     *   createOP決済中建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     * <BR>
     * (4)決済状態：6の場合<BR>
     *   決済済と決済中の2明細を作成する。<BR>
     *   create先物決済済建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     *   create先物決済中建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     * @@param l_lisContractInquiryDetails - (建玉照会明細リスト)<BR>
     * 
     * <BR>
     * 作成した明細を格納するリスト。<BR>
     * @@param l_ifoContract - (先物OP建玉)<BR>
     * <BR>
     * 明細を作成する建玉。<BR>
     * @@param l_intSettlementStatus - (決済状態)<BR>
     * <BR>
     * 下記のいずれか。<BR>
     * <BR>
     * 3 ：未決済と決済中<BR> 
     * 4 ：決済済と未決済 <BR>
     * 5 ：決済済と未決済と決済中<BR> 
     * 6 ：決済済と決済中<BR> 
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4075477303E3
     */
    public void createMultiOptionContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract, int l_intSettlementStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createMultiOptionContractInquiryDetails";
        log.entering(STR_METHOD_NAME);

        // 引数の決済状態にもとづき
        // createOP決済済建玉照会明細
        // createOP未決済建玉照会明細
        // createOP決済中建玉照会明細
        // メソッドのいずれかをコールする。
        switch (l_intSettlementStatus)
        {
            case 3 :
                // (1)決済状態：3の場合
                // 未決済と決済中の2明細を作成する。

                // createOP未決済建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createOptionUnSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // createOP決済中建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createOptionSettlingContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                break;
            case 4 :
                // (2)決済状態：4の場合
                // 決済済と未決済の2明細を作成する。


                // createOP決済済建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createOptionSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // createOP未決済建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createOptionUnSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);

                break;
            case 5 :
                // (3)決済状態：5の場合
                // 決済済と未決済と決済中の3明細を作成する。


                // createOP決済済建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createOptionSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // createOP未決済建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createOptionUnSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // createOP決済中建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createOptionSettlingContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                break;

            case 6 :
                // (4)決済状態：6の場合
                //決済済と決済中の2明細を作成する

                // createOP決済済建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createOptionSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // createOP決済中建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createOptionSettlingContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                break;

            default :
                log.error("Error In Method: " + STR_METHOD_NAME);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create先物建玉照会明細)<BR>
     * <BR>
     * 株価指数先物建玉照会画面に表示する建玉照会明細の一覧を作成する。<BR>
     * <BR>
     * 該当データが存在しない場合にはNULLを返却する。<BR>
     * <BR>
     * 「（先物OP残高）create先物建玉照会明細」参照。<BR>
     * @@param l_subAccount - 補助口座
     * @@param l_strFutureOptionDivision - (先物／オプション区分)<BR>
     * <BR>
     * 1：先物<BR>
     * 2：オプション<BR>
     * @@param l_strDesSettlementStatus - (指定決済状態)<BR>
     * <BR>
     * 下記のいずれか。<BR>
     * <BR>
     * null：指定なし <BR>
     * 0：決済済<BR>
     * 1：未決済<BR>
     * 2：決済中<BR>
     * 
     * @@param l_strSearchConditionString - 検索条件文字列
     * @@param l_strSearchConditionDataContainer - (検索条件データコンテナ)<BR>
     * <BR>
     * 検索条件<BR>
     * @@return 株価指数先物建玉照会明細[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A2FA3502CB
     */

    public WEB3FuturesContractReferenceUnit[] createFuturesContractInquiryDetails(
        WEB3GentradeSubAccount l_subAccount,
        String l_strFuturesOptionDivision,
        String l_strDesSettlementStatus,
        String l_strSearchConditionString,
        String[] l_strSearchConditionDataContainer)
        throws WEB3BaseException
    {
        final String METHOD_NAME = "createFuturesContractInquiryDetails()";
        log.entering(METHOD_NAME);


        // 1.  get建玉一覧(補助口座, ProductTypeEnum, String, String[])
        List l_lisContracts = getContracts(l_subAccount, ProductTypeEnum.IFO, l_strSearchConditionString, l_strSearchConditionDataContainer);

        // 2.戻り値判定
        if (l_lisContracts == null || l_lisContracts.size() == 0)
        {

            // 3. return null
            log.exiting(METHOD_NAME);
            return null;
        }

        // 4.建玉照会明細を格納する建玉照会明細リストを生成する。
        ArrayList l_lisContractReferenceUnits = new ArrayList();

        for (int i = 0; i < l_lisContracts.size(); i++)
        {
            try
            {
                IfoContractParams l_ifocontractParams = (IfoContractParams) l_lisContracts.get(i);
                WEB3IfoContractImpl l_ifocontractImpl = new WEB3IfoContractImpl(l_ifocontractParams);

                // 5.is対象建玉(先物OP建玉, String)
                boolean l_blnFlag = isObjectContract(l_ifocontractImpl, l_strFuturesOptionDivision);

                if (!l_blnFlag)
                {
                    //is対象建玉の返り値がfalseの場合は、以降のLoop内処理は実施しない
                    continue;
                }

                // 6.銘柄IDを取得する。
                long l_lngProductId = l_ifocontractParams.getProductId();

                // 7.先物OP銘柄 = 先物OPプロダクトマネージャ.getProduct(銘柄ID)
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
                WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);

                // 8.銘柄コード = 先物OP銘柄.get原資産銘柄コード()                           
                String l_strProductCode = l_product.getUnderlyingProductCode();

                // 9.reset銘柄コード(String)
                WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

                // 10.get決済状態(先物OP建玉)
                int l_intSettlementStatus = getSettlementStatus(l_ifocontractImpl);

                switch (l_intSettlementStatus)
                {
                    // 11 "决済済"の場合のみ
                    case WEB3IfoSettlementStatusDef.BEFORE_YESTODAY_SETTLED :
                    case WEB3IfoSettlementStatusDef.SETTLED :

                        log.debug("create先物決済済建玉照会明細( ArrayList, 先物OP建玉)");

                        // 11.1 create先物決済済建玉照会明細(ArrayList, 先物OP建玉)
                        createFuturesSettledContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl);
                        break;

                        // 12 "未决済"の場合のみ
                    case WEB3IfoSettlementStatusDef.UNSETTLED:

                        log.debug("create先物未決済建玉照会明細(ArrayList,  先物OP建玉");

                        // 12.1 create先物未決済建玉照会明細(ArrayList, 先物OP建玉)
                        createFuturesUnSettledContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl);
                        break;

                        // 13 "决済中"の場合のみ
                    case WEB3IfoSettlementStatusDef.SETTLING :

                        log.debug("create先物決済中建玉照会明細(ArrayList, 先物OP建玉");

                        // 13.1 create先物決済中建玉照会明細(ArrayList, 先物OP建玉)
                        createFuturesSettlingContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl);
                        break;

                        // 14 get決済状態の返り値が
                        //（"未決済と決済中"、"決済済と未決済"、"決済済と未決済と決済中"、"決済済と決済中"）
                    case WEB3IfoSettlementStatusDef.UNSETTLED_SETTLING :
                    case WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED :
                    case WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED_SETTLING :
                    case WEB3IfoSettlementStatusDef.SETTLED_SETTLING :

                        log.debug("create先物複数建玉照会明細(ArrayList, 先物OP建玉, int");

                        // 14.1 create先物複数建玉照会明細(ArrayList,  先物OP建玉, int)
                        createMultiFuturesContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl, l_intSettlementStatus);
                        break;

                }

            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + METHOD_NAME, l_ex.getMessage(), l_ex);

            }
        }

        // 15.決済状態に指定がある場合（指定決済状態 != null）
        // 指定決済状態の要素のみを取得する。
        if (l_strDesSettlementStatus != null)
        {

            //16.get指定決済状態建玉明細(String, ArrayList)

            getSpecSettlementStatusContractDetails(l_strDesSettlementStatus, l_lisContractReferenceUnits);

            //17.toArray( ) and returns
            WEB3FuturesContractReferenceUnit[] l_lisReturn = new WEB3FuturesContractReferenceUnit[l_lisContractReferenceUnits.size()];
            l_lisContractReferenceUnits.toArray(l_lisReturn);

            log.exiting(METHOD_NAME);
            return l_lisReturn;
        }

        // 18.toArray( ) and returns
        WEB3FuturesContractReferenceUnit[] l_lisReturn = new WEB3FuturesContractReferenceUnit[l_lisContractReferenceUnits.size()];
        l_lisContractReferenceUnits.toArray(l_lisReturn);

        log.exiting(METHOD_NAME);
        return l_lisReturn;
    }

    /**
     * (create先物決済済建玉照会明細)<BR>
     * <BR>
     * 当日決済済の1明細を作成する。（先物）<BR>
     * <BR>
     * 「（先物OP残高）create先物決済済建玉照会明細」参照。<BR>
     * @@param l_lisContractInquiryDetails - (建玉照会明細リスト)<BR>
     * <BR>
     * 作成した明細を格納するリスト。<BR>
     * @@param l_ifoContract - (先物OP建玉)<BR>
     * <BR>
     * 明細を作成する建玉。<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A2FAC502CB
     */

    public void createFuturesSettledContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String METHOD_NAME = "createFuturesSettledContractInquiryDetails(ArrayList, WEB3IfoContractImpl)";
        log.entering(METHOD_NAME);

        // 1.株価指数先物建玉照会明細を生成する。
        WEB3FuturesContractReferenceUnit l_contractReferenceUnit = new WEB3FuturesContractReferenceUnit();

        // 2.getContractId( ) --- 建玉IDを取得する
        long l_lngContractId = l_ifoContract.getContractId();
        IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();

        // 3.get当日返済約定数量(long, Date)      
        double l_dblOrderQuantity = getDayClosingContractExecutionCnt(l_lngContractId, (Date) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        // 4.getトランザクション(long, "返済取引" , Date)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoFinTransactionManagerImpl l_finTransactionManager = (WEB3IfoFinTransactionManagerImpl) l_tradingModule.getFinTransactionManager();

        FinTransactionCateg l_transactionCategory = FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE;

        List l_lisTransactions =
            l_finTransactionManager.getTransactions(l_lngContractId, l_transactionCategory, (Date) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        double l_dblNetAmountTotal = 0;
        BigDecimal l_bdNetAmountTotal = new BigDecimal("0");
        double l_dblSetupFeeTotal = 0;
        BigDecimal l_bdSetupFeeTotal = new BigDecimal("0");
        for (int i = 0; i < l_lisTransactions.size(); i++)
        {
            IfoFinTransactionRow l_transactionRow = (IfoFinTransactionRow) l_lisTransactions.get(i);

            // 5. getNetAmount()
            BigDecimal l_bdNetAmount = new BigDecimal(l_transactionRow.getNetAmount() + "");
            l_bdNetAmountTotal = l_bdNetAmountTotal.add(l_bdNetAmount);
            l_dblNetAmountTotal = l_bdNetAmountTotal.doubleValue();
            // 6.getImportedSetupFee()
            BigDecimal l_bdImportedSetupFee = new BigDecimal(l_transactionRow.getImportedSetupFee() + "");
            l_bdSetupFeeTotal = l_bdSetupFeeTotal.add(l_bdImportedSetupFee);
            l_dblSetupFeeTotal = l_bdSetupFeeTotal.doubleValue();
            // 7.getImportedSetupFeeTax()
            BigDecimal l_bdImportedSetupFeeTax = new BigDecimal(l_transactionRow.getImportedSetupFeeTax() + "");
            l_bdSetupFeeTotal = l_bdSetupFeeTotal.add(l_bdImportedSetupFeeTax);
            l_dblSetupFeeTotal = l_bdSetupFeeTotal.doubleValue();
        }

        try
        {
            // 銘柄IDを取得する。
            long l_lngProductId = l_ifoContractParams.getProductId();
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);
            IfoProductRow l_porductRow = (IfoProductRow) l_product.getDataSourceObject();
            // 先物OP取引銘柄を取得する
            WEB3IfoTradedProductImpl l_tradedproduct = (WEB3IfoTradedProductImpl) l_productManager.getTradedProduct(l_lngProductId, l_porductRow.getTargetMarketId());

            //ID
            l_contractReferenceUnit.id = "" + l_ifoContractParams.getContractId();
            //銘柄コード
            l_contractReferenceUnit.futProductCode = l_porductRow.getProductCode();
            //銘柄名
            l_contractReferenceUnit.futProductName = l_porductRow.getStandardName();
            //指数種別
            l_contractReferenceUnit.targetProductCode = l_porductRow.getUnderlyingProductCode();
            //限月
            l_contractReferenceUnit.delivaryMonth = l_porductRow.getMonthOfDelivery();
            //取引市場
            long l_lngMardetId = l_porductRow.getTargetMarketId();
            WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            String l_strMarketCode = l_finOjbectManager.getMarket(l_lngMardetId).getMarketCode();
            l_contractReferenceUnit.marketCode = l_strMarketCode;
            //建区分
            l_contractReferenceUnit.contractType = "" + l_ifoContractParams.getContractType().intValue();
            //建日      
            l_contractReferenceUnit.openDate = WEB3DateUtility.toDay(l_ifoContractParams.getOpenDate());
            //建数量
            l_contractReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity);
            //建単価
            l_contractReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContractParams.getContractPrice());
            //決済状態区分
            l_contractReferenceUnit.settlementState = WEB3IfoSettlementStateDef.SETTLEMENT_END;
            //建約定金額
            l_contractReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractExecutedAmount(l_dblOrderQuantity));
            //建手数料
            l_contractReferenceUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblSetupFeeTotal);
            //取引最終日
            l_contractReferenceUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedproduct.getLastTradingDate());
            //損益
            l_contractReferenceUnit.income = WEB3StringTypeUtility.formatNumber(l_dblNetAmountTotal);
            //建玉.立会区分
            l_contractReferenceUnit.sessionType = l_ifoContractParams.getSessionType();

            // 9.add(arg0 : Object)
            l_lisContractInquiryDetails.add(l_contractReferenceUnit);

            log.exiting(METHOD_NAME);
            return;
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + METHOD_NAME, l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (create先物未決済建玉照会明細)<BR>
     * <BR>
     * 未決済の1明細を作成する。（先物）<BR>
     * <BR>
     * 「（先物OP残高）create先物未決済建玉照会明細」参照。<BR>
     * @@param l_lisContractInquiryDetails - (建玉照会明細リスト)<BR>
     * <BR>
     * 作成した明細を格納するリスト。<BR>
     * @@param l_ifoContract - (先物OP建玉)<BR>
     * <BR>
     * 明細を作成する建玉。<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A2FAC502CE
     */

    public void createFuturesUnSettledContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String METHOD_NAME = "createFuturesUnSettledContractInquiryDetails(ArrayList, WEB3IfoContractImpl)";
        log.entering(METHOD_NAME);

        // 株価指数先物建玉照会明細を生成する。
        WEB3FuturesContractReferenceUnit l_contractReferenceUnit = new WEB3FuturesContractReferenceUnit();

        // 決済中数量を取得する。
        double l_dblLockedQuantity = l_ifoContract.getLockedQuantity();

        // 未決済数量を取得する。
        double l_dblQuantity = l_ifoContract.getQuantity() - l_dblLockedQuantity;
        
        // 時価を取得する。
        double l_dblContractcurrentPrice = getContractCurrentPrice(l_ifoContract);

        // 評価損益を取得する。
        double l_dblEvaluateIncome = l_ifoContract.getEvaluateIncome(l_dblContractcurrentPrice, l_dblQuantity);
        BigDecimal l_bdEvaluateIncome = new BigDecimal(l_dblEvaluateIncome + "");
       
        // 決済中の建手数料を取得する。
        double l_dblLockedContractCommission = l_ifoContract.getContractCommission(l_dblLockedQuantity);
        BigDecimal l_bdLockedContractCommission = new BigDecimal(l_dblLockedContractCommission + "");

        // 決済中の建手数料消費税を取得する。
        double l_dblLockedContractCommissionTax = l_ifoContract.getContractCommissionConsumptionTax(l_dblLockedQuantity);
        BigDecimal l_bdLockedContractCommissionTax = new BigDecimal(l_dblLockedContractCommissionTax + "");
            
        // 建手数料＝(未決済＋決済中の建手数料)－決済中の建手数料
        BigDecimal l_bdSetupFee = new BigDecimal(l_ifoContract.getSetupFee() + "");
        BigDecimal l_bdContractCommission = l_bdSetupFee.subtract(l_bdLockedContractCommission);

        // 建手数料消費税＝(未決済＋決済中の建手数料消費税)－決済中の建手数料消費税
        BigDecimal l_bdSetupFeeTax = new BigDecimal(l_ifoContract.getSetupFeeTax() + "");
        BigDecimal l_bdContractCommissionTax = l_bdSetupFeeTax.subtract(l_bdLockedContractCommissionTax);
        
        // 建手数料＋建手数料消費税
        BigDecimal l_bdCost = l_bdContractCommission.add(l_bdContractCommissionTax);
        double l_dblCost = l_bdCost.doubleValue();

        IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();
        try
        {
            // 銘柄IDを取得する。
            long l_lngProductId = l_ifoContractParams.getProductId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);
            IfoProductRow l_porductRow = (IfoProductRow) l_product.getDataSourceObject();
            //先物OP取引銘柄を取得する
            WEB3IfoTradedProductImpl l_tradedproduct = (WEB3IfoTradedProductImpl) l_productManager.getTradedProduct(l_lngProductId, l_porductRow.getTargetMarketId());

            //ID
            l_contractReferenceUnit.id = "" + l_ifoContractParams.getContractId();
            //銘柄コード
            l_contractReferenceUnit.futProductCode = l_porductRow.getProductCode();
            //銘柄名
            l_contractReferenceUnit.futProductName = l_porductRow.getStandardName();
            //指数種別
            l_contractReferenceUnit.targetProductCode = l_porductRow.getUnderlyingProductCode();
            //限月
            l_contractReferenceUnit.delivaryMonth = l_porductRow.getMonthOfDelivery();
            //取引市場
            long l_lngMardetId = l_porductRow.getTargetMarketId();
            WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            String l_strMarketCode = l_finOjbectManager.getMarket(l_lngMardetId).getMarketCode();
            l_contractReferenceUnit.marketCode = l_strMarketCode;
            //建区分
            l_contractReferenceUnit.contractType = "" + l_ifoContractParams.getContractType().intValue();
            //建日      
            l_contractReferenceUnit.openDate = WEB3DateUtility.toDay(l_ifoContractParams.getOpenDate());
            //建数量
            l_contractReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            //建単価
            l_contractReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContractParams.getContractPrice());
            //決済状態区分
            l_contractReferenceUnit.settlementState = WEB3IfoSettlementStateDef.HAVE_NOT_SETTLED;
            //建約定金額
            l_contractReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractExecutedAmount(l_dblQuantity));
            //建手数料
            l_contractReferenceUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblCost);
            //取引最終日
            l_contractReferenceUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedproduct.getLastTradingDate());
            //損益
            l_contractReferenceUnit.income = WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncome);
            //損益(諸経費込）
            l_contractReferenceUnit.incomeCost = WEB3StringTypeUtility.formatNumber(l_bdEvaluateIncome.subtract(l_bdCost).doubleValue());
            //建玉.立会区分
            l_contractReferenceUnit.sessionType = l_ifoContractParams.getSessionType();
            l_lisContractInquiryDetails.add(l_contractReferenceUnit);

            log.exiting(METHOD_NAME);
            return;
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + METHOD_NAME, l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (create先物決済中建玉照会明細)<BR>
     * <BR>
     * 決済中の1明細を作成する。（先物）<BR>
     * <BR>
     * 「（先物OP残高）create先物決済中建玉照会明細」参照。<BR>
     * @@param l_lisContractInquiryDetails - (建玉照会明細リスト)<BR>
     * <BR>
     * 作成した明細を格納するリスト。<BR>
     * @@param l_ifoContract - (先物OP建玉)<BR>
     * <BR>
     * 明細を作成する建玉。<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A2FAC502DB
     */

    public void createFuturesSettlingContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String METHOD_NAME = "createFuturesSettlingContractInquiryDetails(ArrayList, WEB3IfoContractImpl)";
        log.entering(METHOD_NAME);

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            // 1.株価指数先物建玉照会明細を生成する。
            WEB3FuturesContractReferenceUnit l_contractReferenceUnit = new WEB3FuturesContractReferenceUnit();

            // 2.決済注文中数量を取得する
            double l_dblLockedQuality = l_ifoContract.getLockedQuantity();

            // 3.建玉IDを取得する
            long l_lngContractId = l_ifoContract.getContractId();

            IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();

            // 4.get返済指定情報(建玉ID)
            IfoClosingContractSpec[] l_lisContractSpecs = this.getClosingContractSpecs(l_lngContractId);
            double l_dblEvaluateIncome = 0;
            BigDecimal l_bdEvaluateIncome = new BigDecimal("0");

            // 5.時価を取得する。
            double l_dblContractcurrentPrice = this.getContractCurrentPrice(l_ifoContract);

            if (l_lisContractSpecs != null)
            {
                for (int i = 0; i < l_lisContractSpecs.length; i++)
                {
                    // 6.市場確認済返済数量を取得する
                    //市場確認済返済数量 == nullの場合、注文数量として返済注文数量を使用する
                    IfoClosingContractSpecRow l_specRow =
                        (IfoClosingContractSpecRow) l_lisContractSpecs[i].getDataSourceObject();
                    double l_dblQuantity = 0D;
                    if (!l_specRow.getConfirmedQuantityIsNull())
                    {
                        //注文数量：建玉返済指定情報.市場確認済返済数量
                        l_dblQuantity = l_specRow.getConfirmedQuantity();
                    }
                    else
                    {
                        //注文数量：建玉返済指定情報.返済注文数量
                        l_dblQuantity = l_specRow.getQuantity();
                    }

                    //返済約定数量を取得する。
                    double l_dblExecutedQuantity = l_lisContractSpecs[i].getExecutedQuantity();
                    if (Double.isNaN(l_dblExecutedQuantity))
                    {
                        l_dblExecutedQuantity = 0D;
                    }

                    //注文単位IDを取得する。
                    long l_lngOrderUintId = l_lisContractSpecs[i].getOrderUnitId();

                    // 注文単位を取得する。
                    IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUintId);

                    //注文有効状態を取得する。
                    OrderOpenStatusEnum l_statusEnum = l_orderUnit.getOrderOpenStatus();

                    //対象外注文チェック
                    //以下のいずれかに当てはまる場合は、対象外注文として次の要素に処理を移行する。
                    //・先物OP返済指定情報.getConfirmedQuantity() == 0
                    //・注文単位.getOrderOpenStatusEnum == "クローズ"
                    if (l_dblQuantity == 0 || OrderOpenStatusEnum.CLOSED.equals(l_statusEnum))
                    {
                        continue;
                    }

                    // 評価損益を取得する。
                    double l_dblContractEvaluateIncome =
                        l_ifoContract.getEvaluateIncome(
                            l_dblContractcurrentPrice,
                            l_dblQuantity - l_dblExecutedQuantity);

                    l_bdEvaluateIncome =
                        l_bdEvaluateIncome.add(new BigDecimal(l_dblContractEvaluateIncome + ""));

                    l_dblEvaluateIncome = l_bdEvaluateIncome.doubleValue();
                }
            }

            // 10.get建手数料(double)
            double l_dblContractCommission = l_ifoContract.getContractCommission(l_dblLockedQuality);

            if (Double.isNaN(l_dblContractCommission))
            {
                l_dblContractCommission = 0D;
            }

            // 11.get建手数料消費税(double)
            double l_dblContractCommissionTax = l_ifoContract.getContractCommissionConsumptionTax(l_dblLockedQuality);

            if (Double.isNaN(l_dblContractCommissionTax))
            {
                l_dblContractCommissionTax = 0D;
            }
            
            // 建手数料＋建手数料消費税
            BigDecimal l_bdContractCommission = new BigDecimal(l_dblContractCommission + "");
            BigDecimal l_bdContractCommissionTax = new BigDecimal(l_dblContractCommissionTax + "");
            BigDecimal l_bdCost = l_bdContractCommission.add(l_bdContractCommissionTax);
            double l_dblCost = l_bdCost.doubleValue();

            // 銘柄IDを取得する。
            long l_lngProductId = l_ifoContractParams.getProductId();

            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);
            IfoProductRow l_porductRow = (IfoProductRow) l_product.getDataSourceObject();
            //先物OP取引銘柄を取得する
            WEB3IfoTradedProductImpl l_tradedproduct = (WEB3IfoTradedProductImpl) l_productManager.getTradedProduct(l_lngProductId, l_porductRow.getTargetMarketId());

            //ID
            l_contractReferenceUnit.id = "" + l_ifoContractParams.getContractId();
            //銘柄コード
            l_contractReferenceUnit.futProductCode = l_porductRow.getProductCode();
            //銘柄名
            l_contractReferenceUnit.futProductName = l_porductRow.getStandardName();
            //指数種別
            l_contractReferenceUnit.targetProductCode = l_porductRow.getUnderlyingProductCode();
            //限月
            l_contractReferenceUnit.delivaryMonth = l_porductRow.getMonthOfDelivery();
            //取引市場
            long l_lngMardetId = l_porductRow.getTargetMarketId();
            WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            String l_strMarketCode = l_finOjbectManager.getMarket(l_lngMardetId).getMarketCode();
            l_contractReferenceUnit.marketCode = l_strMarketCode;
            //建区分
            l_contractReferenceUnit.contractType = "" + l_ifoContractParams.getContractType().intValue();
            //建日      
            l_contractReferenceUnit.openDate = WEB3DateUtility.toDay(l_ifoContractParams.getOpenDate());
            //建数量
            l_contractReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblLockedQuality);
            //建単価
            l_contractReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContractParams.getContractPrice());
            //決済状態区分
            l_contractReferenceUnit.settlementState = WEB3IfoSettlementStateDef.SETTLING;
            //建約定金額
            l_contractReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractExecutedAmount(l_dblLockedQuality));
            //建手数料
            l_contractReferenceUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblCost);
            //取引最終日
            l_contractReferenceUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedproduct.getLastTradingDate());
            //損益
            l_contractReferenceUnit.income = WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncome);
            //損益（諸経費込）
            l_contractReferenceUnit.incomeCost = WEB3StringTypeUtility.formatNumber(l_bdEvaluateIncome.subtract(l_bdCost).doubleValue());
            //建玉.立会区分
            l_contractReferenceUnit.sessionType = l_ifoContractParams.getSessionType();
            l_lisContractInquiryDetails.add(l_contractReferenceUnit);

            log.exiting(METHOD_NAME);
            return;
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + METHOD_NAME, l_ex.getMessage(), l_ex);
        }

    }

    /**
     * (create先物複数建玉照会明細)<BR>
     * <BR>
     * 1つの建玉で複数の建玉照会明細を作成する場合の処理を行う。（先物）<BR>
     * <BR>
     * 引数の決済状態にもとづき、<BR>
     * create先物決済済建玉照会明細<BR>
     * create先物未決済建玉照会明細<BR>
     * create先物決済中建玉照会明細<BR>
     * メソッドのいずれかをコールする。<BR>
     * <BR>
     * (1)決済状態：3の場合<BR>
     *   未決済と決済中の2明細を作成する。<BR>
     *   <BR>
     * 　@create先物未決済建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     *   create先物決済中建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     * <BR>
     * (2)決済状態：4の場合<BR>
     *   決済済と未決済の2明細を作成する。<BR>
     * <BR>
     *   create先物決済済建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     *   create先物未決済建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     * <BR>
     * (3)決済状態：5の場合<BR>
     *   決済済と未決済と決済中の3明細を作成する。<BR>
     * <BR>
     *   create先物決済済建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     *   create先物未決済建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     *   create先物決済中建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     * <BR>
     * (4)決済状態：6の場合<BR>
     *   決済済と決済中の2明細を作成する。<BR>
     *   create先物決済済建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     *   create先物決済中建玉照会明細(パラメータ.建玉照会明細リスト、パラメータ.先物OP建玉)<BR>
     * @@param l_lisContractInquiryDetails - (建玉照会明細リスト)<BR>
     * <BR>
     * 作成した明細を格納するリスト。<BR>
     * @@param l_ifoContract - (先物OP建玉)<BR>
     * <BR>
     * 明細を作成する建玉。<BR>
     * @@param l_intSettlementStatus - (決済状態)<BR>
     * <BR>
     * 下記のいずれか。<BR>
     * <BR>
     * 3 ：未決済と決済中<BR> 
     * 4 ：決済済と未決済 <BR>
     * 5 ：決済済と未決済と決済中<BR> 
     * 6 ：決済済と決済中<BR> 
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A2FAC502EA
     */

    public void createMultiFuturesContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract, int l_intSettlementStatus) throws WEB3BaseException
    {
        final String METHOD_NAME = "createMultiFuturesContractInquiryDetails(ArrayList, WEB3IfoContractImpl, int)";
        log.entering(METHOD_NAME);

        // 引数の決済状態にもとづき
        // create先物決済済建玉照会明細
        // create先物未決済建玉照会明細
        // create先物決済中建玉照会明細
        // メソッドのいずれかをコールする。
        switch (l_intSettlementStatus)
        {
            case 3 :
                // (1)決済状態：3の場合
                // 未決済と決済中の2明細を作成する。


                // create先物未決済建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createFuturesUnSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // create先物決済中建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createFuturesSettlingContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                break;

            case 4 :
                // (2)決済状態：4の場合
                // 決済済と未決済の2明細を作成する。


                // create先物決済済建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createFuturesSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // create先物未決済建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createFuturesUnSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);

                break;

            case 5 :
                // (3)決済状態：5の場合
                // 決済済と未決済と決済中の3明細を作成する。


                // create先物決済済建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createFuturesSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // create先物未決済建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createFuturesUnSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // create先物決済中建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createFuturesSettlingContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                break;

            case 6 :
                //(4)決済状態：6の場合<BR>
                //決済済と決済中の2明細を作成する。


                // create先物決済済建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createFuturesSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // create先物決済中建玉照会明細(建玉照会明細リスト、先物OP建玉)
                createFuturesSettlingContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                break;

            default :
                log.error("Error In Method: " + METHOD_NAME);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + METHOD_NAME);
        }
        log.exiting(METHOD_NAME);

    }

    /**
     * (is対象建玉)<BR>
     * <BR>
     * 指定建玉が対象建玉(先物またはオプション)であるかを判定する。<BR>
     * <BR>
     * (1)銘柄IDを取得する<BR>
     * パラメータ.建玉.get銘柄ID()<BR>
     * <BR>
     * (2)先物OP銘柄を取得する<BR>
     * 先物OPプロダクトマネージャ.getProduct(銘柄ID：long)<BR>
     * <BR>
     * (3)先物オプション区分を取得する<BR>
     * 先物OP銘柄.get先物／オプション区分()<BR>
     * <BR>
     * (4)対象建玉チェックを行う<BR>
     * (3)で取得した先物／オプション区分が<BR>
     * パラメータ.先物／オプション区分と一致する場合にはtrueを、<BR>
     * 一致しない場合にはfalseを返す<BR>
     * @@param l_ifoContract - 先物OP建玉
     * @@param l_strFutureOptionDivision - (先物／オプション区分)<BR>
     * <BR>
     * 1： 先物<BR>
     * 2： オプション<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407549B402BA
     */
    protected boolean isObjectContract(WEB3IfoContractImpl l_ifoContract, String l_strFutureOptionDivision) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isObjectContract";
        log.entering(STR_METHOD_NAME);

        // 建玉を取得する
        IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();

        // (1)銘柄IDを取得する
        long l_lngProductId = l_ifoContractParams.getProductId();

        log.debug(" *** 銘柄ID l_lngProductId = " + l_lngProductId);

        try
        {
            // (2)先物OP銘柄を取得する
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            ProductManager l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            Product l_product = l_productManager.getProduct(l_lngProductId);

            // (3)先物オプション区分を取得する
            IfoProductParams l_porductParams = (IfoProductParams) l_product.getDataSourceObject();
            String l_strFutureOptionDiv = l_porductParams.getFutureOptionDiv();

            log.debug(" *** 先物オプション区分 l_strFutureOptionDiv = " + l_strFutureOptionDiv);

            // (4)対象建玉チェックを行う
            if (l_strFutureOptionDiv.equals(l_strFutureOptionDivision))
            {

                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get決済状態)<BR>
     * <BR>
     * 建玉の決済状態を判定する。<BR>
     * <BR>
     * 「（先物OP残高）get決済状態」参照<BR>
     * @@param l_ifoContract - 先物OP建玉
     * @@return int
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40754ABC0175
     */
    public int getSettlementStatus(WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSettlementStatus";
        log.entering(STR_METHOD_NAME);

        // 建玉を取得する
        IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();

        // 1.建玉IDを取得する。
        long l_lngContractId = l_ifoContract.getContractId();

        String l_timestamp = WEB3DateUtility.formatDate(l_ifoContractParams.getLastUpdatedTimestamp(), "yyyyMMdd"); 
        // 3.getOriginalQuantity( ) --- 建玉元数量を取得する。
        double l_dblOriginalQuantity = l_ifoContract.getOriginalQuantity();
        // 4.getQuantity( ) --- 建玉数量を取得する。
        double l_dblQuantity = l_ifoContract.getQuantity();
        // 5.getLockedQuantity( ) --- 決済注文中数量を取得する。
        double l_dblLockedQuantity = l_ifoContract.getLockedQuantity();

        Timestamp l_dteCurrentDate = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        String l_CurrentDate = WEB3DateUtility.formatDate(l_dteCurrentDate, "yyyyMMdd");

        if (GtlUtils.Double.isZero(l_dblOriginalQuantity) && GtlUtils.Double.isZero(l_dblQuantity))
        {
            //建玉元数量==0、かつ建玉数量==0の場合、-2（新規建約定取消）を返却する。
            log.debug(" *** 決済状態判定: -2新規建約定取消");
            return WEB3IfoSettlementStatusDef.OPENCONTRACT_EXECUTE_CANCLE;
        }

        // 決済状態判定
        // 前日
        if (l_timestamp.compareTo(l_CurrentDate) < 0 && GtlUtils.Double.isZero(l_dblQuantity))
        {
            log.debug(" *** -1決済状態判定: 前日以前決済済");

            log.exiting(STR_METHOD_NAME);
            return WEB3IfoSettlementStatusDef.BEFORE_YESTODAY_SETTLED;
        }

        if (l_timestamp.compareTo(l_CurrentDate) == 0 && GtlUtils.Double.isZero(l_dblQuantity))             
        {
            log.debug(" *** 決済状態判定: 0当日決済済");

            log.exiting(STR_METHOD_NAME);
            return WEB3IfoSettlementStatusDef.SETTLED;
        }
        // 6.get当日返済約定数量(long, Date) 
        // --- 指定建玉の当日返済約定数量を取得する。

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        double l_dblContractExecutionCnt = getDayClosingContractExecutionCnt(l_lngContractId, l_processTime);
        if (Double.isNaN(l_dblContractExecutionCnt))
        {
            l_dblContractExecutionCnt = 0;
        }
        log.debug(" *** get当日返済約定数量: 当日返済約定数量 = " + l_dblContractExecutionCnt);

        if (l_dblLockedQuantity == 0)
        {

            if (l_dblContractExecutionCnt == 0)
            {
                log.exiting(STR_METHOD_NAME);
                log.debug(" *** 決済状態判定: 1未決済");
                return WEB3IfoSettlementStatusDef.UNSETTLED;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                log.debug(" *** 決済状態判定: 4決済済と未決済");
                return WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED;
            }
        }

        if (l_dblLockedQuantity >= 1)
        {
            //2. 決済注文中数量≧1の場合
            if (l_dblContractExecutionCnt == 0)
            {
                //2-1. 当日返済約定数量==0の場合
                if (GtlUtils.Double.isEqual(l_dblLockedQuantity, l_dblQuantity))
                {
                    //2-1-1. 決済注文中数量==建玉数量の場合、2（決済中）を返却する。
                    log.debug("  *** 決済状態判定: 2決済中");
                    return WEB3IfoSettlementStatusDef.SETTLING;
                }
                else
                {
                    //2-1-2. 2-1-1以外の場合、3（未決済と決済中）を返却する。
                    log.debug("  *** 決済状態判定: 3未決済と決済中");
                    return WEB3IfoSettlementStatusDef.UNSETTLED_SETTLING;
                }
            }
            else if (l_dblContractExecutionCnt >= 1)
            {
                //2-2. 当日返済約定数量≧1の場合
                if (GtlUtils.Double.isEqual(l_dblLockedQuantity, l_dblQuantity))
                {
                    //2-2-1. 決済注文中数量==建玉数量の場合、6（決済済と決済中）を返却する。
                    log.debug("  *** 決済状態判定: 6決済済と決済中");
                    return WEB3IfoSettlementStatusDef.SETTLED_SETTLING;
                }
                else
                {
                    //2-2-2. 2-2-1以外の場合、5（決済済と未決済と決済中）を返却する。
                    log.debug("  *** 決済状態判定: 5決済済と未決済と決済中");
                    return WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED_SETTLING;
                }
            }
        }

        log.error("Error In Method: " + STR_METHOD_NAME);
        throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, this.getClass().getName() + STR_METHOD_NAME);
    }

    /**
     * (get指定決済状態建玉明細)<BR>
     * 
     * <BR>
     * 指定決済状態の建玉のみの建玉照会明細の一覧を取得する。<BR>
     * <BR>
     * (1)建玉照会明細一覧ごとのLoop処理<BR>
     * <BR>
     *   (1-1)建玉照会明細の決済状態区分を取得<BR>
     * <BR>
     *   (1-2)パラメータ.指定決済状態と(1-1)の決済状態区分を比較し、<BR>
     *         一致しなかった場合は、建玉照会明細を建玉照会明細一覧から<BR>
     *         削除する<BR>
     * <BR>
     *  (2)指定決済状態のみの建玉照会明細一覧を返却する<BR>
     * <BR>
     * ※(1)の結果、建玉照会明細一覧のサイズが0になった場合には、<BR>
     * NULLを返却する<BR>
     * @@param l_strSpecSettlementStatus - (指定決済状態)<BR>
     * <BR>
     * 下記のいずれか。<BR>
     * <BR>
     * 0：決済済<BR>
     * 1：未決済<BR>
     * 2：決済中<BR>
     * 
     * @@param l_lisContractInquiryDetails - (建玉照会明細リスト)<BR>
     * <BR>
     * 株価指数オプション建玉照会明細の一覧が格納されたリスト<BR>
     * @@return ArrayList
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407E7FE602AC
     */
    protected ArrayList getSpecSettlementStatusContractDetails(String l_strSpecSettlementStatus, ArrayList l_lisContractInquiryDetails) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSpecSettlementStatusContractDetails";
        log.entering(STR_METHOD_NAME);


        if (l_lisContractInquiryDetails == null || l_strSpecSettlementStatus == null)
        {
            String l_strMessage = "パラメータ建玉照会明細リストの値不正！";
            log.error(l_strMessage);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName(), STR_METHOD_NAME);
        }
        if (l_lisContractInquiryDetails.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // (1)建玉照会明細一覧ごとのLoop処理
        ListIterator l_iterator = l_lisContractInquiryDetails.listIterator();
        while (l_iterator.hasNext())
        {
            String l_strState = null;
            Object l_objUnit = l_iterator.next(); 
            if (l_objUnit instanceof WEB3OptionsContractReferenceUnit)
            {
                WEB3OptionsContractReferenceUnit l_ContractUnit = (WEB3OptionsContractReferenceUnit) l_objUnit;
                //(1-1)建玉照会明細の決済状態区分を取得
                l_strState = l_ContractUnit.settlementState;
            }
            else
            {
                WEB3FuturesContractReferenceUnit l_ContractUnit = (WEB3FuturesContractReferenceUnit) l_objUnit;
                //(1-1)建玉照会明細の決済状態区分を取得
                l_strState = l_ContractUnit.settlementState;
            }

            // (1-2)パラメータ.指定決済状態と(1-1)の決済状態区分を比較し
            // 一致しなかった場合は、建玉照会明細を建玉照会明細一覧から削除する

            if (!l_strState.equals(l_strSpecSettlementStatus))
            {

                l_iterator.remove();
            }
        }
        // (2)指定決済状態のみの建玉照会明細一覧を返却する
        log.exiting(STR_METHOD_NAME);
        return l_lisContractInquiryDetails;
    }

    /**
     * (get建玉一覧)<BR>
     * <BR>
     * （getContractsのオーバーロード）<BR>
     * 指定条件に一致する先物OP建玉オブジェクトの一覧を返却する。<BR>
     * <BR>
     * 該当データが存在しない場合にはNULLを返却する。<BR>
     * <BR>
     * (1)戻り値オブジェクトのインスタンスを生成する<BR>
     * <BR>
     * (2)検索条件追加<BR>
     * 　@　@(2-1）パラメータ.検索条件文字列に、口座ID、補助口座ID、<BR>
     *     銘柄タイプを追加した検索条件文字列を作成<BR>
     * <BR>
     * 　@　@検索条件文字列 ＝<BR>
     * 　@　@"account_id = ? and sub_account_id = ？ and product_type = ?"
     *     ＋ パラメータ.検索条件文字列<BR>
     * <BR>
     *     (2-2)文字列配列を生成し、口座ID、補助口座ID、銘柄タイプ、<BR>
     *     パラメータ.検索条件データコンテナの順にセットする。<BR>
     * <BR>
     * ※口座ID、補助口座IDはパラメータの補助口座オブジェクトより取得、<BR>
     *    銘柄タイプはパラメータ.銘柄タイプより設定する。<BR>
     * <BR>
     * (3)QueryProcessor.doFindAllQuery( )により、<BR>
     * 先物OP建玉オブジェクトのListを取得する<BR>
     * <BR>
     * 　@　@　@doFindAllQuery(,先物OP建玉Row.TYPE<BR>
     *                            (2-1)の検索条件文字列,<BR>
     *                            (2-2)の検索条件データコンテナ)<BR>
     * <BR>
     * (4)検索結果を返却する<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * <BR>
     * 補助口座オブジェクト<BR>
     * @@param l_productType - 銘柄タイプ（ProductTypeEnumオブジェクト）<BR>
     * <BR>
     * 0：その他<BR>
     * 1：株式<BR>
     * 2：債権<BR>
     * 3：投資信託<BR>
     * 4：外国株<BR>
     * 5：現金<BR>
     * 6：先物オプション<BR>
     * @@param l_strSearchConditionString - 検索条件 文字列
     * @@param l_strSearchConditionDataContainer - (検索条件データコンテナ)<BR>
     * <BR>
     * 検索条件<BR>
     * @@return List
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407A1D5001F3
     */
    protected List getContracts(WEB3GentradeSubAccount l_subAccount, ProductTypeEnum l_productType, String l_strSearchConditionString, String[] l_strSearchConditionDataContainer)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContracts";
        log.entering(STR_METHOD_NAME);

        // (1)戻り値オブジェクトのインスタンスを生成する
        List l_lisReturnValue = new ArrayList();

        // (2)検索条件追加
        // (2-1）検索条件文字列を作成
        String l_strWhere = "account_id = ? and sub_account_id = ? and product_type = ? ";
    
        Object[] l_objWhereValue = null;
        // (2-2)文字列配列を生成
        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        Object[] l_objWhereValue1 = { new Long(l_lngAccountId), new Long(l_lngSubAccountId), new Long(l_productType.intValue())};

        if ((l_strSearchConditionString != null && !l_strSearchConditionString.equals("")) && l_strSearchConditionDataContainer != null)
        {
            l_strWhere += l_strSearchConditionString;

            l_objWhereValue = new Object[l_strSearchConditionDataContainer.length + 3];
            System.arraycopy(l_objWhereValue1, 0, l_objWhereValue, 0, 3);

            System.arraycopy(l_strSearchConditionDataContainer, 0, l_objWhereValue, 3, l_strSearchConditionDataContainer.length);

        }
        else
        {
            l_objWhereValue = new Object[3];
            l_objWhereValue[0] = new Long(l_lngAccountId);
            l_objWhereValue[1] = new Long(l_lngSubAccountId);
            l_objWhereValue[2] = new Long(l_productType.intValue());
        }

        // (3)QueryProcessor.doFindAllQuery( )により、
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(IfoContractRow.TYPE, l_strWhere, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "建玉テーブルを検索  error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "建玉テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisReturnValue;
    }
    
    /**
     * (get建玉一覧)<BR>
     * <BR>
     * （getContractsのオーバーロード）<BR>
     * 指定条件に一致する先物OP建玉オブジェクトの一覧を返却する。<BR>
     * <BR>
     * 該当データが存在しない場合にはNULLを返却する。<BR>
     * <BR>
     * (1)戻り値オブジェクトのインスタンスを生成する<BR>
     * <BR>
     * (2)検索条件追加<BR>
     * 　@　@(2-1）パラメータ.検索条件文字列に、口座ID、補助口座ID、<BR>
     *     銘柄タイプを追加した検索条件文字列を作成<BR>
     * <BR>
     * 　@　@検索条件文字列 ＝<BR>
     * 　@　@"account_id = ? and sub_account_id = ？ and product_type = ?"
     *     ＋ パラメータ.検索条件文字列<BR>
     * <BR>
     *     (2-2)文字列配列を生成し、口座ID、補助口座ID、銘柄タイプ、<BR>
     *     パラメータ.検索条件データコンテナの順にセットする。<BR>
     * <BR>
     * ※口座ID、補助口座IDはパラメータの補助口座オブジェクトより取得、<BR>
     *    銘柄タイプはパラメータ.銘柄タイプより設定する。<BR>
     * <BR>
     * (3)QueryProcessor.doFindAllQuery( )により、<BR>
     * 先物OP建玉オブジェクトのListを取得する<BR>
     * <BR>
     * 　@　@　@doFindAllQuery(,先物OP建玉Row.TYPE<BR>
     *                            (2-1)の検索条件文字列,<BR>
     *                            (2-2)の検索条件データコンテナ)<BR>
     * <BR>
     * (4)検索結果を返却する<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * <BR>
     * 補助口座オブジェクト<BR>
     * @@param l_productType - 銘柄タイプ（ProductTypeEnumオブジェクト）<BR>
     * <BR>
     * 0：その他<BR>
     * 1：株式<BR>
     * 2：債権<BR>
     * 3：投資信託<BR>
     * 4：外国株<BR>
     * 5：現金<BR>
     * 6：先物オプション<BR>
     * @@param l_strSearchConditionString - 検索条件 文字列
     * @@param l_strOrderBy - ソート条件文字列
     * @@param l_strSearchConditionDataContainer - (検索条件データコンテナ)<BR>
     * <BR>
     * 検索条件<BR>
     * @@return List
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407A1D5001F3
     */
    protected List getContracts(WEB3GentradeSubAccount l_subAccount, ProductTypeEnum l_productType, String l_strSearchConditionString, String l_strOrderBy, String[] l_strSearchConditionDataContainer)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContracts";
        log.entering(STR_METHOD_NAME);

        // (1)戻り値オブジェクトのインスタンスを生成する
        List l_lisReturnValue = new ArrayList();

        // (2)検索条件追加
        // (2-1）検索条件文字列を作成
        String l_strWhere = "account_id = ? and sub_account_id = ? and product_type = ? ";
  
        Object[] l_objWhereValue = null;
        // (2-2)文字列配列を生成し
        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        Object[] l_objWhereValue1 = { new Long(l_lngAccountId), new Long(l_lngSubAccountId), new Long(l_productType.intValue())};

        if ((l_strSearchConditionString != null && !l_strSearchConditionString.equals("")) && l_strSearchConditionDataContainer != null)
        {

            l_strWhere += l_strSearchConditionString;

            l_objWhereValue = new Object[l_strSearchConditionDataContainer.length + 3];
            System.arraycopy(l_objWhereValue1, 0, l_objWhereValue, 0, 3);

            System.arraycopy(l_strSearchConditionDataContainer, 0, l_objWhereValue, 3, l_strSearchConditionDataContainer.length);

        }
        else
        {
            l_objWhereValue = new Object[3];
            l_objWhereValue[0] = new Long(l_lngAccountId);
            l_objWhereValue[1] = new Long(l_lngSubAccountId);
            l_objWhereValue[2] = new Long(l_productType.intValue());
        }

        // (3)QueryProcessor.doFindAllQuery( )により、
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(IfoContractRow.TYPE, l_strWhere, l_strOrderBy, null, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "建玉テーブルを検索  error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "建玉テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisReturnValue;
    }

    /**
     * (get建玉時価)<BR>
     * <BR>
     * 指定建玉の銘柄の時価を取得する。<BR>
     * <BR>
     * 下記手順で取得した時価を返却する。<BR>
     * <BR>
     * (1)市場ID、銘柄IDの取得<BR>
     * 市場ID = パラメータ.先物OP建玉.get市場ID()<BR>
     * 銘柄ID = パラメータ.先物OP建玉.get銘柄ID()<BR>
     * <BR>
     * (2)銘柄コードの取得<BR>
     * 先物OP銘柄 = 先物OPプロダクトマネージャ.getProduct(銘柄ID)<BR>
     * 銘柄コード = 先物OP銘柄.get銘柄コード()<BR>
     * <BR>
     * (3)時価セット判定および時価の取得<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(CURRENT_PRICE)でHashtableを取得<BR>
     * 
     * 　@(3-1)該当銘柄の時価がセットされている場合(Hashtable.get(銘柄コード)がNULLでない場合)<BR>
     * <BR>
     *         時価 = Hashtable.get(銘柄コード)<BR>
     * 
     * 　@(3-2)該当銘柄の時価がセットされていない場合(Hashtable.get(銘柄コード)がNULLの場合)<BR>
     * <BR>
     * 　@　@(3-2-1)時価の取得<BR>
     * 　@　@　@　@　@　@　@先物OP取引銘柄 = 先物OPプロダクトマネージャ.getTradedProduct(銘柄ID、市場ID）<BR>
     * 　@　@　@　@　@　@　@時価 = 先物OPプロダクトマネージャ.get時価(先物OP取引銘柄)<BR>
     * 　@　@<BR>
     * 　@　@(3-2-2)時価の追加<BR>
     * 　@　@　@　@　@　@　@取得したHashtableに該当銘柄コードと時価を追加<BR>
     * 　@　@　@　@　@　@　@Hashtable.put（銘柄コード、 時価)<BR>
     * <BR>
     * 　@　@(3-2-3)時価のセット<BR>
     * 　@　@　@　@　@　@　@ThreadLocalSystemAttributesRegistry.setAttribute()にて時価をセットする<BR>
     * 　@　@　@　@　@　@　@設定キー：　@CURRENT_PRICE<BR>
     * 　@　@　@　@　@　@　@値：　@(3-2-2)で時価を追加したHashtable<BR>
     * <BR>
     * (4)時価を返却<BR>
     * <BR>
     * ※当該メソッドを使用する場合は、各サービスインタセプタのonCallにて時価のセット処理(*)、<BR>
     *   onReturnおよびonThrowableメソッド内にて時価のクリア処理を行うこと<BR>
     * <BR>
     * (*)ThreadLocalSystemAttributesRegistry.setAttribute()にてThreadLocalに時価の変数をセットする<BR>
     * 　@　@　@　@　@　@　@設定キー：　@CURRENT_PRICE<BR>
     * 　@　@　@　@　@　@　@値：　@Hashtable(新規に作成したHashtable)<BR>　@　@　@　@
     * @@param l_ifoContract - 先物OP建玉
     * @@return double
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407A61C401E3
     */
    public double getContractCurrentPrice(WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractCurrentPrice";
        log.entering(STR_METHOD_NAME);

        log.debug("contractId = " + l_ifoContract.getContractId());

        //市場ID、銘柄IDの取得
        long l_lngMarketId = l_ifoContract.getMarketId();
        long l_lngProductId = l_ifoContract.getProduct().getProductId();

        try
        {
            //銘柄コードの取得
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            Product l_product = l_productManager.getProduct(l_lngProductId);

            //銘柄コード = 先物OP銘柄.get銘柄コード()
            IfoProductParams l_porductParams = (IfoProductParams) l_product.getDataSourceObject();
            String l_strProductCode = l_porductParams.getProductCode();

            //時価セット判定および時価の取得
            double l_dblCurrentPrice = 0;

            Hashtable l_htCurrentPrices = 
                (Hashtable) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.CURRENT_PRICE);

            //該当銘柄の時価がセットされている場合
            if (l_htCurrentPrices != null && l_htCurrentPrices.containsKey(l_strProductCode))
            {
                //時価の取得
                Double l_currentPrice = (Double)l_htCurrentPrices.get(l_strProductCode);
                l_dblCurrentPrice = l_currentPrice.doubleValue();
            }
            else
            {
                if (l_htCurrentPrices == null)
                {
                    l_htCurrentPrices = new Hashtable();
                }
                WEB3IfoTradedProductImpl l_tradeProduct = null;
                l_tradeProduct = (WEB3IfoTradedProductImpl)l_productManager.getTradedProduct(l_lngProductId, l_lngMarketId);
                //時価の取得
                try{
                    l_dblCurrentPrice = l_productManager.getCurrentPrice(l_tradeProduct);
                }
                catch(WEB3BusinessLayerException l_ble)
                {
                    if(WEB3ErrorCatalog.BUSINESS_ERROR_01997.equals(l_ble.getErrorInfo()))
                    {
                        l_dblCurrentPrice = 0;
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_ble.getErrorInfo(),
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ble.getErrorMessage(),
                            l_ble);
                    }
                }
                //時価の追加
                l_htCurrentPrices.put(l_strProductCode, new Double(l_dblCurrentPrice));
                //時価のセット
                ThreadLocalSystemAttributesRegistry.setAttribute(WEB3IfoAttributeNameDef.CURRENT_PRICE, l_htCurrentPrices);
            }
            log.exiting(STR_METHOD_NAME);
            return l_dblCurrentPrice;
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

    }

    /**
     * (get当日返済約定数量)<BR>
     * <BR>
     * 指定建玉の当日返済約定数量を取得する。<BR>
     * <BR>
     * (1)当日の返済指定情報一覧を取得<BR>
     * get返済指定情報(パラメータ.建玉ID、パラメータ.更新日付)をコール<BR>
     * <BR>
     * (2)当日返済約定数取得処理<BR>
     * 　@(2-1) (1)の返り値がNULLであった場合、0を返却する<BR>
     * <BR>
     * 　@(2-2) (2-1)以外の場合<BR>
     * 　@　@　@　@(2-2-1) 返済指定情報要素数ごとのLoop処理を行い、<BR>
     * 　@　@　@　@          返済指定情報.get返済約定数量()の値を加算していく<BR>
     * <BR>
     * 　@　@　@　@ (2-2-2) 返済約定数量の合計値を返却する<BR>
     * @@param l_lngContractID - 建玉ID
     * @@param l_datLastUpdatedTimestamp - (更新日付)<BR>
     * <BR>
     * 当日の日付<BR>
     * <BR>
     * YYYYMMDD<BR>
     * @@return double
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407DEBB50259
     */
    protected double getDayClosingContractExecutionCnt(long l_lngContractID, Date l_datLastUpdatedTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDayClosingContractExecutionCnt";
        log.entering(STR_METHOD_NAME);

        // (1)当日の返済指定情報一覧を取得
        IfoClosingContractSpec[] l_closingContractSpecs = getClosingContractSpecs(l_lngContractID, l_datLastUpdatedTimestamp);
        // (2)当日返済約定数取得処理
        // (2-1) (1)の返り値がNULLであった場合、0を返却する
        if (l_closingContractSpecs == null)
        {
            log.debug("当日の返済指定情報一覧を取得の結果はNull、 0を返却する !!!");

            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        // (2-2) (2-1)以外の場合
        // (2-2-1) 返済指定情報要素数ごとのLoop処理を行い
        //     --- 返済指定情報.get返済約定数量()の値を加算していく
        BigDecimal l_bdExecutedQuantity = new BigDecimal("0");
        for (int i = 0; i < l_closingContractSpecs.length; i++)
        {
            log.debug("返済指定情報.get返済約定数量(" + i + ")の値 = " + l_closingContractSpecs[i].getExecutedQuantity());

            BigDecimal l_bdQuantity =
                new BigDecimal(l_closingContractSpecs[i].getExecutedQuantity() + "");

            l_bdExecutedQuantity = l_bdExecutedQuantity.add(l_bdQuantity);
        }

        log.debug("Output parm: 返済約定数量の合計値返済約定数量の合計値 = " + l_bdExecutedQuantity);

        // (2-2-2) 返済約定数量の合計値を返却する
        log.exiting(STR_METHOD_NAME);
        return l_bdExecutedQuantity.doubleValue();
    }

    /**
     * (get返済指定情報)<BR>
     * <BR>
     * （getClosingContractSpecs）<BR>
     * 指定した建玉IDと更新日付に該当する建玉返済指定情報の一覧を取得する。<BR>
     * <BR>
     * (1)建玉返済指定情報テーブル検索<BR>
     * 以下の条件で建玉返済指定情報テーブルを検索する<BR>
     * <BR>
     * [検索条件]<BR>
     * 建玉ID = パラメータ.建玉ID<BR>
     * 更新日付 = パラメータ.更新日付と同じ日付<BR>
     * <BR>
     * (2)(1)の取得結果の一覧を返却する<BR>
     * ※該当するデータが存在しない場合にはNULLを返却する<BR>
     * @@param l_lngContractID - 建玉ID
     * @@param l_datLastUpdatedTimestamp - (更新日付)<BR>
     * <BR>
     * YYYYMMDD<BR>
     * @@return webbroker3.ifo.WEB3IfoClosingContractSpecImpl[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4075F6E702CB
     */
    public IfoClosingContractSpec[] getClosingContractSpecs(long l_lngContractID, Date l_datLastUpdatedTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getClosingContractSpecs";
        log.entering(STR_METHOD_NAME);



        // (1)建玉返済指定情報テーブル検索
        List l_lisReturnValue = new ArrayList();
        // [検索条件]:
        
        String l_strWhere = " contract_id = ? and to_char(last_updated_timestamp,'yyyyMMdd') = ? ";

        String l_strLastUpdatedTime = WEB3DateUtility.formatDate(l_datLastUpdatedTimestamp, "yyyyMMdd");
        Object[] l_objWhereValue = { new Long(l_lngContractID), l_strLastUpdatedTime };

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(IfoClosingContractSpecRow.TYPE, l_strWhere, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "建玉返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "建玉返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        // (2)(1)の取得結果の一覧を返却する
        if (l_lisReturnValue.size() == 0)
        {
            log.debug("建玉返済指定情報テーブル検索結果0件");

            log.exiting(STR_METHOD_NAME);
            return null;
        }
        IfoClosingContractSpec[] l_closingcontractspec = new IfoClosingContractSpec[l_lisReturnValue.size()];

        ArrayList l_lisReturnList = new ArrayList();
        for (int i = 0; i < l_lisReturnValue.size(); i++)
        {
            IfoClosingContractSpecRow l_row = (IfoClosingContractSpecParams) l_lisReturnValue.get(i);
            WEB3IfoClosingContractSpecImpl l_impl = new WEB3IfoClosingContractSpecImpl(l_row);
            l_lisReturnList.add(i, l_impl);
        }

        l_lisReturnList.toArray(l_closingcontractspec);


        log.exiting(STR_METHOD_NAME);
        return l_closingcontractspec;
    }

    /**
     * (get返済指定情報)<BR>
     * <BR>
     * （getClosingContractSpecs）<BR>
     * 指定した建玉IDに該当する建玉返済指定情報の一覧を取得する<BR>
     * <BR>
     * (1)建玉返済指定情報テーブル検索<BR>
     * 以下の条件で建玉返済指定情報テーブルを検索する<BR>
     * <BR>
     * [検索条件]<BR>
     * 建玉ID = 引数.建玉ID<BR>
     * <BR>
     * (2)(1)の取得結果の一覧を返却する<BR>
     * ※該当するデータが存在しない場合にはNULLを返却する<BR>
     * @@param l_lngContractID - 建玉ID
     * @@return webbroker3.ifo.WEB3IfoClosingContractSpecImpl[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407A6F52006C
     */
    public IfoClosingContractSpec[] getClosingContractSpecs(long l_lngContractID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getClosingContractSpecs";
        log.entering(STR_METHOD_NAME);

        log.debug("Input Parm: 建玉ID l_lngContractID = " + l_lngContractID);

        // (1)建玉返済指定情報テーブル検索
        List l_lisReturnValue = new ArrayList();
        // [検索条件]:
        String l_strWhere = " contract_id = ? ";
        String[] l_strWhereValue = { "" + l_lngContractID };
        try
        {

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(IfoClosingContractSpecRow.TYPE, l_strWhere, l_strWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "建玉返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "建玉返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        // (2)(1)の取得結果の一覧を返却する
        if (l_lisReturnValue.size() == 0)
        {
            log.debug("建玉返済指定情報テーブル検索結果0件");

            log.exiting(STR_METHOD_NAME);
            return null;
        }
        IfoClosingContractSpec[] l_closingcontractspec = new IfoClosingContractSpec[l_lisReturnValue.size()];
        ArrayList l_lisReturnList = new ArrayList();
        for (int i = 0; i < l_lisReturnValue.size(); i++)
        {
            IfoClosingContractSpecRow l_row = (IfoClosingContractSpecParams) l_lisReturnValue.get(i);
            WEB3IfoClosingContractSpecImpl l_impl = new WEB3IfoClosingContractSpecImpl(l_row);
            l_lisReturnList.add(i, l_impl);
        }
        l_lisReturnList.toArray(l_closingcontractspec);

        log.exiting(STR_METHOD_NAME);
        return l_closingcontractspec;
    }

    /**
     * (updateトランザクション)<BR>
     * <BR>
     * 手数料按分計算（一口約定）を実施し、トランザクションデータを更新する。<BR>
     * <BR>
     * 先物OPポジションヘルパー.updateトランザクション()に処理を委譲する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP残高）updateトランザクション」参照。<BR>
     * @@param l_lngOrderUnitID - 注文単位ＩＤ
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4099BCF300B1
     */
    public void updateTransaction(long l_lngOrderUnitID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateTransaction";
        log.entering(STR_METHOD_NAME);

        log.debug("Input Parm: 注文単位ＩＤ l_lngOrderUnitID = " + l_lngOrderUnitID);

        try
        {

            log.debug("先物OPポジションヘルパー.updateトランザクション()に処理を委譲する with " + ProductTypeEnum.IFO);

            WEB3IfoPositionManagerHelper l_positionHelper = new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO);
            l_positionHelper.updateTransaction(l_lngOrderUnitID);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("Error in Method: " + STR_METHOD_NAME, l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (adjust返済指定情報)<BR>
     * 返済指定情報の数量を調整する。 <BR>
     * <BR>
     * 下の様に返済指定情報の返済数量を調整する。 <BR>
     * －数量減の場合、返済連番：大→小の順に、調整数量分を <BR>
     * 　@返済割当数量のうちの未約定数量から減算していく。 <BR>
     * －数量増の場合、返済連番：小→大の順に、調整数量分を <BR>
     * 　@訂正前の返済割当数量（市場確認済返済数量）を上限値として <BR>
     * 　@訂正時の返済割当数量（返済注文数量）に加算していく。 <BR>
     * <BR>
     * １）　@調整数量を計算する。 <BR>
     * 調整数量 = （訂正前数量　@－　@訂正後数量） <BR>
     * <BR>
     * ２）　@返済指定情報読込 <BR>
     * 　@返済指定情報テーブルを行ロックオプション（for update）にて読み、<BR>
     *   返済指定情報行オブジェクトを取得する。 <BR>
     * <BR>
     * [検索条件] <BR>
     * 返済指定情報.口座ＩＤ = 口座ＩＤ <BR>
     * 返済指定情報.補助口座ＩＤ = 補助口座ＩＤ <BR>
     * 返済指定情報.注文ＩＤ = 注文ＩＤ <BR>
     * 返済指定情報.注文単位ＩＤ = 注文単位ＩＤ <BR>
     * <BR>
     * ※取得順 ･･･ 返済連番の降順（asc） <BR>
     * <BR>
     * ３）　@取得した返済指定情報の返済注文数量を調整する。 <BR>
     * <BR>
     * ３－１）　@調整数量≧０の場合（返済数量の割当てを減らす場合） <BR>
     * 　@調整数量を返済指定情報の未約定数量に割り当てる。 <BR>
     * 　@取得した返済指定情報行オブジェクトに全て対し返済連番の降順に以下処理を行う。 <BR>
     * 　@（※配列の後から） <BR>
     * <BR>
     * 　@３－１－１）　@未約定数量算出 <BR>
     * 　@　@未約定数量 = 返済指定情報.返済注文数量 － 返済指定情報.返済約定数量 <BR>
     * <BR>
     * 　@３－１－２）　@調整数量割り当て <BR>
     * 　@　@[（未約定数量 >= 調整数量）の場合] <BR>
     * 　@　@　@・（返済指定情報.返済注文数量 － 調整数量）の値を返済指定情報.返済注文数量にセットする。 <BR>
     * 　@　@　@・調整後の返済指定情報行オブジェクトの内容でDBを更新（update）する。 <BR>
     * 　@　@　@※LOOP処理を終了する。 （調整数量の割当て完了） <BR>
     * <BR>
     * 　@　@[（未約定数量 ＜ 調整数量）の場合] <BR>
     * 　@　@　@・返済指定情報.返済約定数量を返済指定情報.返済注文数量にセットする。 <BR>
     * 　@　@　@・調整数量 = 調整数量 - 未約定数量 <BR>
     * 　@　@　@・調整後の返済指定情報行オブジェクトの内容でDBを更新（update）する。 <BR>
     * <BR>
     * ３－２）そうでない場合（返済数量の割当てを増やす場合） <BR>
     * 　@市場確認済返済数量を上限として返済注文数量に調整数量を割り当てる。 <BR>
     * <BR>
     * 　@３－２－１）調整数量の符号を反転させる。 <BR>
     * <BR>
     * 　@取得した返済指定情報行オブジェクトに全て対し返済連番の昇順に以下処理を行う。 <BR>
     * 　@（※配列の前から） <BR>
     * <BR>
     * 　@　@３－２－２－１）　@調整可能数量を算出する <BR>
     * 　@　@　@調整可能数量＝返済指定情報.市場確認済返済数量 － 返済指定情報.返済注文数量 <BR>
     * <BR>
     * 　@　@３－２－２－２）調整不可能な場合（調整可能数量≦０の場合） <BR>
     * 　@　@　@次の返済指定情報の処理へ <BR>
     * <BR>
     * 　@　@３－２－２－３）　@返済注文数量に調整数量を割当てる <BR>
     * 　@　@　@[（調整可能数量 ≧ 調整数量）の場合] <BR>
     * 　@　@　@　@・（返済指定情報.返済注文数量 ＋ 調整数量）を返済指定情報.返済注文数量にセットする。 <BR>
     * 　@　@　@　@・調整後の返済指定情報行オブジェクトの内容でDBを更新（update）する。 <BR>
     * 　@　@　@　@※LOOP処理を終了する。 （調整数量の割当て完了） <BR>
     * <BR>
     * 　@　@　@[（調整可能数量 ＜ 調整数量）の場合] <BR>
     * 　@　@　@　@・（返済指定情報.返済注文数量 ＋ 調整可能数量）を返済指定情報.返済注文数量にセットする。 <BR>
     * 　@　@　@　@・調整数量 = 調整数量 - 調整可能数量 <BR>
     * 　@　@　@　@・調整後の返済指定情報行オブジェクトの内容でDBを更新（update）する。 <BR>
     * <BR>
     * @@param l_lngAccountID - 口座ＩＤ
     * @@param l_lngSubAccountID - 補助口座ID
     * @@param l_lngOrderID - 注文ID
     * @@param l_lngOrderUnitID - 注文単位ID
     * @@param l_dblCountBeforeChanged - 訂正前数量
     * @@param l_dblCountAfterChanged - 訂正後数量
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A06D790105
     */
    public void adjustClosingContractSpecs(long l_lngAccountID, long l_lngSubAccountID, long l_lngOrderID, long l_lngOrderUnitID, double l_dblCountBeforeChanged, double l_dblCountAfterChanged)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "adjustClosingContractSpecs";
        log.entering(STR_METHOD_NAME);

        try
        {

            // 調整数量を計算する。
            double l_dblAdjustCount = l_dblCountBeforeChanged - l_dblCountAfterChanged;

            log.debug("調整数量を計算する: 調整数量 =（訂正前数量－訂正後数量）= " + l_dblAdjustCount);

            // 返済指定情報読込
            List l_lisReturnValue = new ArrayList();
            String l_strWhere = " account_id = ? and sub_account_id = ? ";
            l_strWhere += "and order_id = ? and order_unit_id = ? ";
            String l_strOrderBy = "closing_serial_no asc";
            String[] l_strWhereValue = { "" + l_lngAccountID, "" + l_lngSubAccountID, "" + l_lngOrderID, "" + l_lngOrderUnitID };

            // doFindAllQuery
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(IfoClosingContractSpecRow.TYPE, l_strWhere, l_strOrderBy, null, l_strWhereValue);

            IfoClosingContractSpecParams l_rows = new IfoClosingContractSpecParams(); 
            
            //返済数量の割当てを減らす場合
            if(l_dblAdjustCount >= 0)
            {

				for (int i = l_lisReturnValue.size() - 1; i >= 0  ; i--)
	            {
	                l_rows = new IfoClosingContractSpecParams((IfoClosingContractSpecRow) l_lisReturnValue.get(i));
	
	                double l_dblExecuteQuantity = l_rows.getExecutedQuantity();
	                double l_dblQuantity = l_rows.getQuantity();
	                double l_dblNotQuality = l_dblQuantity - l_dblExecuteQuantity;
	
	                if (l_dblNotQuality >= l_dblAdjustCount)
	                {
	                    l_rows.setQuantity(l_dblQuantity - l_dblAdjustCount);
						l_queryProcessor.doUpdateQuery(l_rows);	
	                    break;
	                }
	                else if(l_dblNotQuality < l_dblAdjustCount)
	                {
	                    l_rows.setQuantity(l_dblExecuteQuantity);
	                    l_dblAdjustCount = l_dblAdjustCount - l_dblNotQuality;
						l_queryProcessor.doUpdateQuery(l_rows); 
	                }
					
	            }
			}
			//返済数量の割当てを増やす場合
			else
			{
				l_dblAdjustCount = - l_dblAdjustCount;
				for (int i = 0; i < l_lisReturnValue.size(); i++)
				{
					l_rows = new IfoClosingContractSpecParams((IfoClosingContractSpecRow) l_lisReturnValue.get(i));
                        
					double l_dblConfirmedQuantity = l_rows.getConfirmedQuantity();
					double l_dblQuantity = l_rows.getQuantity();
					double l_dblAssignableQuantity = l_dblConfirmedQuantity - l_dblQuantity;
                    
					if (l_dblAssignableQuantity <= 0) continue;
                    
					if (l_dblAssignableQuantity >= l_dblAdjustCount)
					{
						l_rows.setQuantity(l_dblQuantity + l_dblAdjustCount);
						l_queryProcessor.doUpdateQuery(l_rows);
						break;
					}
					else
					{
						l_rows.setQuantity(l_dblQuantity + l_dblAssignableQuantity);
						l_dblAdjustCount = l_dblAdjustCount - l_dblAssignableQuantity;
						l_queryProcessor.doUpdateQuery(l_rows);
					}
				}
			}
     
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "建玉返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "建玉返済指定情報テーブルを検索  error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * 建玉テーブルのレコードから返済建玉の配列を作成し、返却する。<BR>
     *シーケンス図<BR>
     *「（先物OP残高）create返済建玉一覧」参照。<BR>
     *<BR>
     * @@param l_subAccount
     * @@param l_contractTypeEnum
     * @@param l_lngMarketId
     * @@param l_lngProductId
     * @@return WEB3FuturesOptionsCloseMarginContractUnit[]
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3FuturesOptionsCloseMarginContractUnit[] createSettleContracts(WEB3GentradeSubAccount l_subAccount, ContractTypeEnum l_contractTypeEnum, long l_lngMarketId, long l_lngProductId)
        throws WEB3BaseException
    {
        final String StrMethodName = "createSettleContracts";
        log.entering(StrMethodName);

        //検索条件文字列の作成以下に示す順序で検索条件文字列を作成する。
        String l_strWhere = "and market_id = ? and contract_type = ? and product_id = ?";

        //検索条件データコンテナの作成以下に示す順序で検索条件データコンテナ(Stringの配列)を作成する。
        //※Stringでないデータは、Stringに変換してセットする。
        String[] l_strWhereValue = { Long.toString(l_lngMarketId), Integer.toString(l_contractTypeEnum.intValue()), Long.toString(l_lngProductId)};

        //(*)ソート条件(order by句)文字列の作成
        String l_strOrderBy = null;
        if (ContractTypeEnum.LONG.equals(l_contractTypeEnum))
        {
            l_strOrderBy = "open_date asc, delivery_date asc, contract_price asc";
        }
        else if (ContractTypeEnum.SHORT.equals(l_contractTypeEnum))
        {
            l_strOrderBy = "open_date asc, delivery_date asc, contract_price desc";
        }

        List l_lstContracts = new ArrayList();

        //ソートされた建玉オブジェクトの一覧を取得する。 
        l_lstContracts = this.getContracts(l_subAccount, ProductTypeEnum.IFO, l_strWhere, l_strOrderBy, l_strWhereValue);

        //該当建玉が存在しない場合は、「該当データなし」の例外をthrowする。
        if (l_lstContracts == null || l_lstContracts.size() == 0)
        {
            String l_strMessage = "建玉テーブルを検索  error";
            log.error(l_strMessage);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + StrMethodName, l_strMessage);
        }

        //返済建玉を格納する為のArrayListを生成する。
        List l_lstContractUnit = new ArrayList();

        //取得した建玉オブジェクト数分のLoop処理
        for (int i = 0; i < l_lstContracts.size(); i++)
        {
            IfoContractRow l_contractRow = (IfoContractRow) l_lstContracts.get(i);
            WEB3IfoContractImpl l_contractImpl = new WEB3IfoContractImpl(l_contractRow);

            //建玉数量を取得する。
            double l_dblQuantity = l_contractImpl.getQuantity();

            //決済注文中数量を取得する。
            double l_dblLockedQuantity = l_contractImpl.getLockedQuantity();

            if (l_dblQuantity > 0 && l_dblQuantity != l_dblLockedQuantity)
            {
                //返済建玉オブジェクトを生成する。
                WEB3FuturesOptionsCloseMarginContractUnit l_contractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();

                //返済建玉に以下のプロパティをセットする。                
                l_contractUnit.id = "" + l_contractImpl.getContractId();
                l_contractUnit.contractOrderQuantity = null;
                l_contractUnit.settlePriority = null;

                //作成した返済建玉をArrayListに追加する。 
                l_lstContractUnit.add(l_contractUnit);
            }
        }

        if (l_lstContractUnit == null || l_lstContractUnit.size() == 0)
        {
            String l_strMessage = "返済建玉がnullの値または、要素数が０である場合のエラー";
            log.error(l_strMessage);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00178, this.getClass().getName() + StrMethodName, l_strMessage);
        }
        WEB3FuturesOptionsCloseMarginContractUnit[] l_contractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[l_lstContractUnit.size()];

        //返済建玉の配列を生成する。
        l_lstContractUnit.toArray(l_contractUnits);

        return l_contractUnits;
    }

    public Contract toContract(Row row)
    {
        log.debug("toContract to WEB3IfoContractImpl:" + row);
        return new WEB3IfoContractImpl((IfoContractRow) row);
    }

    public class WEB3IfoClosingContractSpecImpl extends IfoClosingContractSpecImpl
    {
        public WEB3IfoClosingContractSpecImpl(IfoClosingContractSpecRow row)
        {
            super(row);
        }
    }


    /**
     * (create先物OP残高照会明細)<BR>
     * 建玉に該当する残高照会明細の一覧を作成し、返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(先物OP残高)create先物OP残高照会明細」<BR>
     * 参照<BR>
     * @@param l_subAccount - 補助口座オブジェクト
     * @@param l_strFuOpDiv - (先物／オプション区分)<BR>
     * 1：先物<BR>
     * 2：オプション<BR>
     * @@param l_strSettlementStatus - (指定決済状態)<BR>
     * 下記のいずれか。<BR>
     * <BR>
     * null：指定なし <BR>
     * 1：未決済<BR>
     * 2：決済中<BR>
     * 
     * @@param l_strQueryString - 検索条件文字列
     * @@param l_strQueryContainers - 検索条件データコンテナ
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsDetailUnit[]
     * @@roseuid 41AC326201F4
     */
    public WEB3FuturesOptionsDetailUnit[] createIfoBalanceReferenceDetailUnit(WEB3GentradeSubAccount l_subAccount, String l_strFuOpDiv, String l_strSettlementStatus, String l_strQueryString, String[] l_strQueryContainers) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "createIfoBalanceReferenceDetailUnit()";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_strFuOpDiv == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        List l_lstContracts = new ArrayList();
        //指定条件に一致する建玉オブジェクトの一覧を返却する。
        l_lstContracts = this.getContracts((WEB3GentradeSubAccount)l_subAccount,ProductTypeEnum.IFO,l_strQueryString,l_strQueryContainers);
            
        //残高照会明細を格納するArrayListを生成する。
        List l_lstReferenceDetailUnit = new ArrayList();
            
        //(*)get建玉一覧()の戻り値の要素(=建玉オブジェクト)数分Loop処理
        int l_intContractsLength = 0;
        if (l_lstContracts != null)
        {
            l_intContractsLength = l_lstContracts.size();
        }
                
        WEB3FuturesOptionsDetailUnit l_referenceDetailUnit = null;     
        for (int i = 0; i < l_intContractsLength; i++)
        {
            IfoContractRow l_contractRow = (IfoContractRow)l_lstContracts.get(i);
            WEB3IfoContractImpl l_contractImpl = new WEB3IfoContractImpl(l_contractRow);
            //指定建玉が対象建玉(先物またはオプション)であるかを判定する。
            if (!this.isObjectContract(l_contractImpl,l_strFuOpDiv))
            {
                continue;
            }
            //先物OP銘柄を取得する。
            WEB3IfoProductImpl l_productImpl = (WEB3IfoProductImpl)l_contractImpl.getProduct();
            //銘柄コードのリセット処理を行う。
            WEB3GentradeTradingTimeManagement.resetProductCode(l_productImpl.getUnderlyingProductCode());
            //建玉の決済状態を判定する。
            int l_intStatus = this.getSettlementStatus(l_contractImpl);
                
            //get決済状態()の戻り値 == ("新規建約定取消" or"前日以前決済済" or
            //"決済済")の場合、次の要素へ処理を以降する。(continue)
            if (WEB3IfoSettlementStatusDef.OPENCONTRACT_EXECUTE_CANCLE == l_intStatus
                     || WEB3IfoSettlementStatusDef.BEFORE_YESTODAY_SETTLED == l_intStatus 
                     || WEB3IfoSettlementStatusDef.SETTLED == l_intStatus)
            {
                continue;
            }
                
            //(*)パラメータ.指定決済状態 != nullの場合
            if (l_strSettlementStatus != null)
            {
                 //(*1)指定決済状態チェック以下の条件に該当する場合、次の要素へ処理を移行する。(continue)
                if (WEB3IfoSettlementStateDef.HAVE_NOT_SETTLED.equals(l_strSettlementStatus))
                {
                    //　@["未決済"の場合]get決済状態()の戻り値 != ("未済済" or "決済済と未決済" or"決済済と未決済と決済中" or "未決済と決済中")であること。
                    if (WEB3IfoSettlementStatusDef.UNSETTLED != l_intStatus 
                            && WEB3IfoSettlementStatusDef.UNSETTLED_SETTLING !=l_intStatus 
                            && WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED != l_intStatus 
                            && WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED_SETTLING != l_intStatus)
                    {
                        continue;
                    }
                }

                    
                //["決済中"の場合]get決済状態()の戻り値 != ("決済中" or "決済済と決済中" or
                //"決済済と未決済と決済中" or "未決済と決済中")である
                if (WEB3IfoSettlementStateDef.SETTLING.equals(l_strSettlementStatus))
                {
                    if (WEB3IfoSettlementStatusDef.SETTLING != l_intStatus 
                            && WEB3IfoSettlementStatusDef.UNSETTLED_SETTLING != l_intStatus
                            && WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED_SETTLING != l_intStatus
                            && WEB3IfoSettlementStatusDef.SETTLED_SETTLING != l_intStatus)
                    {
                        continue;
                    }
                }               
                
            }
            if (l_strSettlementStatus == null || WEB3IfoSettlementStateDef.HAVE_NOT_SETTLED.equals(l_strSettlementStatus))
            {
                //(*2)未決済明細の作成
                if (WEB3IfoSettlementStatusDef.UNSETTLED == l_intStatus 
                        || WEB3IfoSettlementStatusDef.UNSETTLED_SETTLING ==l_intStatus 
                        || WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED == l_intStatus 
                        || WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED_SETTLING == l_intStatus)
                {
                    //未決済の1明細を作成する。
                    l_referenceDetailUnit = this.createIfoUnSettledBalanceReferenceDetailUnit(l_subAccount,l_contractImpl);
                    //ArrayListに残高照会明細を追加する。
                    l_lstReferenceDetailUnit.add(l_referenceDetailUnit);
                }
            }
            if (l_strSettlementStatus == null || WEB3IfoSettlementStateDef.SETTLING.equals(l_strSettlementStatus))
            {
                //(*3)決済中明細の作成
                if (WEB3IfoSettlementStatusDef.SETTLING == l_intStatus 
                        || WEB3IfoSettlementStatusDef.UNSETTLED_SETTLING == l_intStatus
                        || WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED_SETTLING == l_intStatus
                        || WEB3IfoSettlementStatusDef.SETTLED_SETTLING == l_intStatus)
                {
                    //決済中の1明細を作成する。
                    l_referenceDetailUnit = this.createIfoSettlingBalanceReferenceDetailUnit(l_subAccount,l_contractImpl);
                    //ArrayListに残高照会明細を追加する。
                    l_lstReferenceDetailUnit.add(l_referenceDetailUnit);
                }
            }
                        
        }
            
        //残高照会明細の配列を返却する。
        WEB3FuturesOptionsDetailUnit[] l_detailUnit = new WEB3FuturesOptionsDetailUnit[l_lstReferenceDetailUnit.size()];
        l_lstReferenceDetailUnit.toArray(l_detailUnit);
            
        //toArray()の戻り値.length == 0の場合、nullを返却する。
        if (l_detailUnit.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
            
        log.exiting(STR_METHOD_NAME);
        return l_detailUnit;
    }
   
    /**
     * (create先物OP決済中残高照会明細)<BR>
     * 決済中の残高照会明細を作成し、返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(先物OP残高)create先物OP決済中残高照会明細」<BR>
     * 参照<BR>
     * @@param l_subAccount - 補助口座オブジェクト
     * @@param l_ifoContract - 先物OP建玉
     * @@return WEB3FuturesOptionsDetailUnit
     * @@roseuid 41AC32620271
     */
    public WEB3FuturesOptionsDetailUnit createIfoSettlingBalanceReferenceDetailUnit(WEB3GentradeSubAccount l_subAccount, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createIfoBalanceReferenceDetailUnit()";
        log.entering(STR_METHOD_NAME);
        //先物OP残高照会明細インスタンスを生成する。
        if (l_subAccount == null || l_ifoContract == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        WEB3FuturesOptionsDetailUnit l_referenceDetailUnit = new WEB3FuturesOptionsDetailUnit();
        try
        {
            //先物OP銘柄を取得する。
            WEB3IfoProductImpl l_productImpl = (WEB3IfoProductImpl)l_ifoContract.getProduct();
            //先物OP取引銘柄を取得する。
            WEB3IfoTradedProductImpl l_tradedProductImpl = (WEB3IfoTradedProductImpl)l_ifoContract.getTradedProduct();
            //指定建玉の時価情報を取得する。
            WEB3IfoProductQuote l_currentInfo = this.getContractCurrentInfo((WEB3GentradeSubAccount)l_subAccount,l_ifoContract);
            //決済注文中数量を取得する。
            double l_dblLockedQuantity = l_ifoContract.getLockedQuantity();
            //建約定代金を取得する。
            double l_dblExecutedAmount = l_ifoContract.getContractExecutedAmount(l_dblLockedQuantity);
            //建手数料を取得する。
            double l_dblCommission = l_ifoContract.getContractCommission(l_dblLockedQuantity);
            BigDecimal l_bdCommission = new BigDecimal(l_dblCommission + "");
            //建手数料消費税を取得する。
            double l_dblConsumptionTax = l_ifoContract.getContractCommissionConsumptionTax(l_dblLockedQuantity);
            BigDecimal l_bdConsumptionTax = new BigDecimal(l_dblConsumptionTax + "");
            //建手数料＋建手数料消費税
            BigDecimal l_bdCost = l_bdCommission.add(l_bdConsumptionTax);
            double l_dblCost = l_bdCost.doubleValue();
            
            IfoContractRow l_contractRow = (IfoContractRow)l_ifoContract.getDataSourceObject();
            //建玉IDを取得する。
            long l_lngContractId = l_contractRow.getContractId();
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            //指定建玉に該当する返済指定情報の一覧を取得する。
            IfoClosingContractSpec[] l_contractSpec = this.getClosingContractSpecs(l_lngContractId);
            
            double l_dblIncome = 0D;
            BigDecimal l_bdIncome = new BigDecimal("0");
            
            if (l_contractSpec != null)
            {
                int l_intContractSpecLength = l_contractSpec.length;
                //取得した返済指定情報要素ごとのLoop処理
                for (int i = 0; i < l_intContractSpecLength; i++)
                {
                    //注文単位IDを取得する。
                    long l_lngOrderUnitId = l_contractSpec[i].getOrderUnitId();
                    //注文単位を取得する。
                    IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
                    OrderOpenStatusEnum l_openStatus = l_orderUnit.getOrderOpenStatus();
                    
                    //市場確認済返済数量を取得する
                    //市場確認済返済数量 == nullの場合、注文数量として返済注文数量を使用する
                    IfoClosingContractSpecRow l_specRow =
                        (IfoClosingContractSpecRow) l_contractSpec[i].getDataSourceObject();
                    double l_dblQuantity = 0D;
                    if (!l_specRow.getConfirmedQuantityIsNull())
                    {
                        //注文数量：建玉返済指定情報.市場確認済返済数量
                        l_dblQuantity = l_specRow.getConfirmedQuantity();
                    }
                    else
                    {
                        //注文数量：建玉返済指定情報.返済注文数量
                        l_dblQuantity = l_specRow.getQuantity();
                    }

                    //対象外注文チェック
                    //以下のいずれかに当てはまる場合は、対象外注文として次の要素に処理を移行する。
                    //・先物OP返済指定情報.getConfirmedQuantity() == 0
                    //・注文単位.getOrderOpenStatusEnum == "クローズ"
                    
                    if (l_dblQuantity == 0 || OrderOpenStatusEnum.CLOSED.equals(l_openStatus))
                    {
                        continue;
                    }

                    double l_dblPrice = 0;

                    //時価を取得する｡
                    if (l_currentInfo != null)
                    {
                        l_dblPrice = l_currentInfo.getCurrentPrice();
                    }

                    double l_dblExecutedQuantity = l_contractSpec[i].getExecutedQuantity();
                    if (Double.isNaN(l_dblExecutedQuantity))
                    {
                        l_dblExecutedQuantity = 0;
                    }

                    //評価損益を取得する。
                    double l_dblEvaluateIncome =
                        l_ifoContract.getEvaluateIncome(
                            l_dblPrice,
                            l_dblQuantity - l_dblExecutedQuantity);

                    l_bdIncome =
                        l_bdIncome.add(new BigDecimal(l_dblEvaluateIncome + ""));

                    l_dblIncome = l_bdIncome.doubleValue();
                }
                
                //残高照会明細.ID ＝ 先物OP建玉.getContractId()
                l_referenceDetailUnit.id = ""+ l_lngContractId;
                //残高照会明細.銘柄コード ＝ 先物OP銘柄.get銘柄コード()
                l_referenceDetailUnit.productCode = l_productImpl.getProductCode();
                IfoProductRow l_productRow = (IfoProductRow) l_productImpl.getDataSourceObject();
                //残高照会明細.銘柄名 ＝ 先物OP銘柄.get銘柄名()
                l_referenceDetailUnit.productName = l_productRow.getStandardName();
                //残高照会明細.指数種別 ＝ 先物OP銘柄.get原資産銘柄コード()
                l_referenceDetailUnit.targetProductCode = l_productRow.getUnderlyingProductCode();
                //残高照会明細.限月 ＝ 先物OP銘柄.get限月()
                l_referenceDetailUnit.delivaryMonth = l_productRow.getMonthOfDelivery();
                //残高照会明細.オプション商品区分  ＝　@先物OP銘柄.get先物オプション商品()
                String l_strProductType = null;
                IfoDerivativeTypeEnum l_delivativeTypeEnum = l_productRow.getDerivativeType();
                if (IfoDerivativeTypeEnum.FUTURES.equals(l_delivativeTypeEnum))
                {
                    l_strProductType = WEB3IfoProductTypeDef.FUTURES;
                }
                else if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_delivativeTypeEnum))
                {
                    l_strProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
                }
                else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_delivativeTypeEnum))
                {
                    l_strProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
                }
                l_referenceDetailUnit.opProductType = l_strProductType;
                
                //残高照会明細.行使価格       ＝　@先物OP銘柄.get行使価格()
                l_referenceDetailUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_productRow.getStrikePrice());
                //残高照会明細.取引市場       ＝　@先物OP建玉.市場IDに該当する市場コード
                WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_ifoContract.getMarketId());
                l_referenceDetailUnit.marketCode = l_market.getMarketCode();
                //残高照会明細.建区分        ＝　@先物OP建玉.get建区分() 
                if (ContractTypeEnum.LONG.equals(l_contractRow.getContractType()))
                {
                    l_referenceDetailUnit.contractType = WEB3IfoContractTypeDef.OPEN_BUY;
                }
                else
                {
                    l_referenceDetailUnit.contractType = WEB3IfoContractTypeDef.OPEN_SELL;
                }
                //残高照会明細.建日         ＝　@先物OP建玉.getOpenDate()
                l_referenceDetailUnit.openDate = WEB3DateUtility.toDay(l_contractRow.getOpenDate());
                //残高照会明細.建数量        ＝　@getQuantity()の戻り値 - getLockedQuantity()の戻り値
                l_referenceDetailUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblLockedQuantity);
                //残高照会明細.建単価        ＝　@先物OP建玉.getContractPrice() 
                l_referenceDetailUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractPrice());
                //残高照会明細.決済状態区分       ＝　@"2：決済中"
                l_referenceDetailUnit.settlementState = WEB3IfoSettlementStateDef.SETTLING;
                //残高照会明細.建約定金額      ＝　@get建約定金額()の戻り値
                l_referenceDetailUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblExecutedAmount);
                //残高照会明細.建手数料       ＝　@get建手数料()の戻り値 + get建手数料消費税()の戻り値
                l_referenceDetailUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblCost);
                //残高照会明細.取引最終日      ＝　@先物OP取引銘柄.get売買最終日()
                l_referenceDetailUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedProductImpl.getLastTradingDate());
                if (l_currentInfo != null)
                {
                    //残高照会明細.時価         ＝　@get建玉時価情報()の戻り値.時価
                    l_referenceDetailUnit.currentPrice = WEB3StringTypeUtility.formatNumber(l_currentInfo.getCurrentPrice());
                    //残高照会明細.前日比        ＝　@get建玉時価情報()の戻り値.前日比
                    l_referenceDetailUnit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_currentInfo.getComparedPreviousDay());
                    //残高照会明細.時価取得時間     ＝　@get建玉時価情報()の戻り値.時価取得時間
                    l_referenceDetailUnit.currentPriceTime = WEB3DateUtility.formatDate(l_currentInfo.getCurrentPriceTime(),"HH:mm");
                    //残高照会明細.損益         ＝　@get評価損益()の戻り値
                    l_referenceDetailUnit.income = WEB3StringTypeUtility.formatNumber(l_dblIncome);
                    //残高照会明細.損益(諸経費込)   ＝　@get評価損益(諸経費込)の戻り値
                    l_referenceDetailUnit.incomeCost = WEB3StringTypeUtility.formatNumber(l_bdIncome.subtract(l_bdCost).doubleValue());
                }
                else
                {
                    //残高照会明細.時価         ＝　@null
                    l_referenceDetailUnit.currentPrice = null;
                    //残高照会明細.前日比        ＝　@null
                    l_referenceDetailUnit.comparedPreviousDay = null;
                    //残高照会明細.時価取得時間     ＝　@null
                    l_referenceDetailUnit.currentPriceTime = null;
                    //残高照会明細.損益         ＝　@null
                    l_referenceDetailUnit.income = null;
                    //残高照会明細.損益(諸経費込)   ＝　@null
                    l_referenceDetailUnit.incomeCost = null;
                }
                //残高照会明細.立会区分   ＝　@先物OP建玉.立会区分
                l_referenceDetailUnit.sessionType = l_contractRow.getSessionType();
                log.exiting(STR_METHOD_NAME);

            }
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }    
        return l_referenceDetailUnit;
    }
   
    /**
     * (create先物OP未決済残高照会明細)<BR>
     * 未決済の残高照会明細を作成し、返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(先物OP残高)create先物OP未決済残高照会明細」<BR>
     * 参照<BR>
     * @@param l_subAccount - 補助口座オブジェクト
     * @@param l_ifoContract - 先物OP建玉
     * @@return WEB3FuturesOptionsDetailUnit
     * @@roseuid 41AC32620261
     */
    public WEB3FuturesOptionsDetailUnit createIfoUnSettledBalanceReferenceDetailUnit(WEB3GentradeSubAccount l_subAccount, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createIfoUnSettledBalanceReferenceDetailUnit()";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_ifoContract == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        //先物OP残高照会明細インスタンスを生成する。
        WEB3FuturesOptionsDetailUnit l_referenceDetailUnit = new WEB3FuturesOptionsDetailUnit();
        try
        {
            //先物OP銘柄を取得する。
            WEB3IfoProductImpl l_productImpl = (WEB3IfoProductImpl)l_ifoContract.getProduct();
            //先物OP取引銘柄を取得する。
            WEB3IfoTradedProductImpl l_tradedProductImpl = (WEB3IfoTradedProductImpl)l_ifoContract.getTradedProduct();
            //指定建玉の時価情報を取得する。
            WEB3IfoProductQuote l_currentInfo = this.getContractCurrentInfo(l_subAccount,l_ifoContract);
            //建玉数量を取得する。
            double l_dblQuantity = l_ifoContract.getQuantity();
            //決済注文中数量を取得する。
            double l_dblLockedQuantity = l_ifoContract.getLockedQuantity();
            //建約定代金を取得する。
            double l_dblExecutedAmount = l_ifoContract.getContractExecutedAmount(l_dblQuantity - l_dblLockedQuantity);
            // 決済中の建手数料を取得する。
            double l_dblLockedContractCommission = l_ifoContract.getContractCommission(l_dblLockedQuantity);
            BigDecimal l_bdLockedContractCommission = new BigDecimal(l_dblLockedContractCommission + "");
            // 決済中の建手数料消費税を取得する。
            double l_dblLockedContractCommissionTax = l_ifoContract.getContractCommissionConsumptionTax(l_dblLockedQuantity);
            BigDecimal l_bdLockedContractCommissionTax = new BigDecimal(l_dblLockedContractCommissionTax + "");
            // 建手数料＝(未決済＋決済中の建手数料)－決済中の建手数料
            BigDecimal l_bdSetupFee = new BigDecimal(l_ifoContract.getSetupFee() + "");
            BigDecimal l_bdContractCommission = l_bdSetupFee.subtract(l_bdLockedContractCommission);
            // 建手数料消費税＝(未決済＋決済中の建手数料消費税)－決済中の建手数料消費税
            BigDecimal l_bdSetupFeeTax = new BigDecimal(l_ifoContract.getSetupFeeTax() + "");
            BigDecimal l_bdContractCommissionTax = l_bdSetupFeeTax.subtract(l_bdLockedContractCommissionTax);
            // 建手数料＋建手数料消費税
            BigDecimal l_bdCost = l_bdContractCommission.add(l_bdContractCommissionTax);
            double l_dblCost = l_bdCost.doubleValue();
            
            IfoContractRow l_contractRow = (IfoContractRow)l_ifoContract.getDataSourceObject();
            //建玉IDを取得する。
            long l_lngContractId = l_contractRow.getContractId();
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            double l_dblIncome = 0D;
            BigDecimal l_bdIncome = new BigDecimal("0");
              
            //評価損益を取得する。
            if (l_currentInfo != null)
            {
                double l_dblEvaluateIncome =
                    l_ifoContract.getEvaluateIncome(
                        l_currentInfo.getCurrentPrice(),
                        l_dblQuantity - l_dblLockedQuantity);

                BigDecimal l_bdEvaluateIncome = new BigDecimal(l_dblEvaluateIncome + "");

                l_bdIncome = l_bdIncome.add(l_bdEvaluateIncome);
                l_dblIncome = l_bdIncome.doubleValue();
            }
            else
            {
                double l_dblEvaluateIncome =
                    l_ifoContract.getEvaluateIncome(
                        0,
                        l_dblQuantity - l_dblLockedQuantity);

                BigDecimal l_bdEvaluateIncome = new BigDecimal(l_dblEvaluateIncome + "");

                l_bdIncome = l_bdIncome.add(l_bdEvaluateIncome);
                l_dblIncome = l_bdIncome.doubleValue();           
            }

            
            //残高照会明細.ID ＝ 先物OP建玉.getContractId()
            l_referenceDetailUnit.id = ""+ l_lngContractId;
            //残高照会明細.銘柄コード ＝ 先物OP銘柄.get銘柄コード()
            l_referenceDetailUnit.productCode = l_productImpl.getProductCode();
            IfoProductRow l_productRow = (IfoProductRow) l_productImpl.getDataSourceObject();
            //残高照会明細.銘柄名 ＝ 先物OP銘柄.get銘柄名()
            l_referenceDetailUnit.productName = l_productRow.getStandardName();
            //残高照会明細.指数種別 ＝ 先物OP銘柄.get原資産銘柄コード()
            l_referenceDetailUnit.targetProductCode = l_productRow.getUnderlyingProductCode();
            //残高照会明細.限月 ＝ 先物OP銘柄.get限月()
            l_referenceDetailUnit.delivaryMonth = l_productRow.getMonthOfDelivery();
            //残高照会明細.オプション商品区分  ＝　@先物OP銘柄.get先物オプション商品()
            String l_strProductType = null;
            IfoDerivativeTypeEnum l_delivativeTypeEnum = l_productRow.getDerivativeType();
            if (IfoDerivativeTypeEnum.FUTURES.equals(l_delivativeTypeEnum))
            {
                l_strProductType = WEB3IfoProductTypeDef.FUTURES;
            }
            else if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_delivativeTypeEnum))
            {
                l_strProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_delivativeTypeEnum))
            {
                l_strProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }
            l_referenceDetailUnit.opProductType = l_strProductType;
            
            //残高照会明細.行使価格       ＝　@先物OP銘柄.get行使価格()
            l_referenceDetailUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_productRow.getStrikePrice());
            //残高照会明細.取引市場       ＝　@先物OP建玉.市場IDに該当する市場コード
            WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_ifoContract.getMarketId());
            l_referenceDetailUnit.marketCode = l_market.getMarketCode();
            //残高照会明細.建区分        ＝　@先物OP建玉.get建区分() 
            if (ContractTypeEnum.LONG.equals(l_contractRow.getContractType()))
            {
                l_referenceDetailUnit.contractType = WEB3IfoContractTypeDef.OPEN_BUY;
            }
            else
            {
                l_referenceDetailUnit.contractType = WEB3IfoContractTypeDef.OPEN_SELL;
            }
            //残高照会明細.建日         ＝　@先物OP建玉.getOpenDate()
            l_referenceDetailUnit.openDate = WEB3DateUtility.toDay(l_contractRow.getOpenDate());
            //残高照会明細.建数量        ＝　@getQuantity()の戻り値 - getLockedQuantity()の戻り値
            l_referenceDetailUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity - l_dblLockedQuantity);
            //残高照会明細.建単価        ＝　@先物OP建玉.getContractPrice() 
            l_referenceDetailUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractPrice());
            //残高照会明細.決済状態区分       ＝　@"1：未決済"
            l_referenceDetailUnit.settlementState = WEB3IfoSettlementStateDef.HAVE_NOT_SETTLED;
            //残高照会明細.建約定金額      ＝　@get建約定金額()の戻り値
            l_referenceDetailUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblExecutedAmount);
            //残高照会明細.建手数料       ＝　@get建手数料()の戻り値 + get建手数料消費税()の戻り値
            l_referenceDetailUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblCost);
            //残高照会明細.取引最終日      ＝　@先物OP取引銘柄.get売買最終日()
            l_referenceDetailUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedProductImpl.getLastTradingDate());            
            if (l_currentInfo != null)
            {
                //残高照会明細.時価         ＝　@get建玉時価情報()の戻り値.時価
                l_referenceDetailUnit.currentPrice = WEB3StringTypeUtility.formatNumber(l_currentInfo.getCurrentPrice());
                //残高照会明細.前日比        ＝　@get建玉時価情報()の戻り値.前日比
                l_referenceDetailUnit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_currentInfo.getComparedPreviousDay());
                //残高照会明細.時価取得時間     ＝　@get建玉時価情報()の戻り値.時価取得時間
                l_referenceDetailUnit.currentPriceTime = WEB3DateUtility.formatDate(l_currentInfo.getCurrentPriceTime(),"HH:mm");
                //残高照会明細.損益         ＝　@get評価損益()の戻り値
                l_referenceDetailUnit.income = WEB3StringTypeUtility.formatNumber(l_dblIncome);
                //残高照会明細.損益(諸経費込)   ＝　@get評価損益(諸経費込)の戻り値
                l_referenceDetailUnit.incomeCost = WEB3StringTypeUtility.formatNumber(l_bdIncome.subtract(l_bdCost).doubleValue());
            }
            else
            {
                //残高照会明細.時価         ＝　@null
                l_referenceDetailUnit.currentPrice = null;
                //残高照会明細.前日比        ＝　@null
                l_referenceDetailUnit.comparedPreviousDay = null;
                //残高照会明細.時価取得時間     ＝　@null
                l_referenceDetailUnit.currentPriceTime = null;
                //残高照会明細.損益         ＝　@null
                l_referenceDetailUnit.income = null;
                //残高照会明細.損益(諸経費込)   ＝　@null
                l_referenceDetailUnit.incomeCost = null;
            }
            //残高照会明細.立会区分   ＝　@先物OP建玉.立会区分
            l_referenceDetailUnit.sessionType = l_contractRow.getSessionType();

            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }    
        return l_referenceDetailUnit;
    }
   
    /**
     * (get建玉時価情報)<BR>
     * 指定建玉の銘柄の時価情報(時価、時価取得時間など)を取得する。<BR>
     * <BR>
     * 下記手順で取得した時価情報を返却する。<BR>
     * <BR>
     * １）銘柄コードの取得<BR>
     * 　@先物OP銘柄 = パラメータ.先物OP建玉.getProduct()<BR>
     * 　@銘柄コード = 先物OP銘柄.get銘柄コード()<BR>
     * <BR>
     * ２）時価情報セット判定および時価情報の取得<BR>
     * 　@ThreadLocalSystemAttributesRegistry.<BR>
     * 　@getAttribute(CURRENT_PRICE_INFO)でHashtableを取得<BR>
     * <BR>
     * 　@２－１）該当銘柄の時価情報がセットされている場合<BR>
     * 　@　@　@(Hashtable.get(銘柄コード) != nullの場合)<BR>
     * <BR>
     *         時価情報 = Hashtable.get(銘柄コード)<BR>
     * <BR>
     * 　@２－２）該当銘柄の時価情報がセットされていない場合<BR>
     * 　@　@　@(Hashtable.get(銘柄コード) == nullの場合)<BR>
     * <BR>
     * 　@　@２－２－１）時価情報の取得<BR>
     * 　@　@　@　@先物OP取引銘柄 = パラメータ.先物OP建玉.getTradedProduct()<BR>
     * 　@　@　@　@時価情報 = 先物OP取引銘柄.get時価情報(パラメータ.補助口座)<BR>
     * 　@　@<BR>
     * 　@　@２－２－２）時価情報の追加<BR>
     * 　@　@　@　@取得したHashtableに該当銘柄コードと時価情報を追加<BR>
     * 　@　@　@　@Hashtable.put（銘柄コード、 ２－２－１）にて取得した時価情報)<BR>
     * <BR>
     * 　@　@２－２－３）時価のセット<BR>
     * 　@　@　@　@ThreadLocalSystemAttributesRegistry.setAttribute()にて時価情報をセットす
     * る<BR>
     * 　@　@　@　@設定キー：　@CURRENT_PRICE_INFO<BR>
     * 　@　@　@　@値：　@２－２－２）にて時価情報を追加したHashtable<BR>
     * <BR>
     * ３）取得した時価情報を返却<BR>
     * <BR>
     * ※当該メソッドを使用する場合は、各サービスインタセプタの<BR>
     * onCallにて時価情報のセット処理(*)、<BR>
     * 　@onReturn()およびonThrowable()メソッド内にて時価情報のクリア処理を行うこと<BR>
     * <BR>
     * (*)ThreadLocalSystemAttributesRegistry.setAttribute()にて<BR>
     * 　@ThreadLocalに時価情報の変数をセットする<BR>
     * 　@設定キー：　@CURRENT_PRICE_INFO<BR>
     * 　@値：　@Hashtable(新規に作成したHashtable)<BR>
     * <BR>
     * @@param l_subAccount - 補助口座オブジェクト
     * @@param l_ifoContract - 先物OP建玉
     * @@return webbroker3.ifo.WEB3IfoCurrentInfo
     * @@roseuid 41AC32620290
     */
    public WEB3IfoProductQuote getContractCurrentInfo(WEB3GentradeSubAccount l_subAccount, WEB3IfoContractImpl l_ifoContract)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getContractCurrentInfo()";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_ifoContract == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        
        //先物OP銘柄を取得する。
        WEB3IfoProductImpl l_productImpl = (WEB3IfoProductImpl)l_ifoContract.getProduct();
        //銘柄コードを取得する。
        String l_strProductCode = l_productImpl.getProductCode();
        
        // ２）時価情報セット判定および時価情報の取得
        WEB3IfoProductQuote l_ifoCurrentInfo = null;

        Hashtable l_htCurrentPrices = 
            (Hashtable) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.CURRENT_PRICE_INFO);

        //(該当銘柄の時価情報がセットされている場合(Hashtable.get(銘柄コード) != nullの場合)
        if (l_htCurrentPrices != null && l_htCurrentPrices.containsKey(l_strProductCode))
        {
            //時価情報 = Hashtable.get(銘柄コード)
            l_ifoCurrentInfo = (WEB3IfoProductQuote)l_htCurrentPrices.get(l_strProductCode);
        }
        //該当銘柄の時価情報がセットされていない場合(Hashtable.get(銘柄コード) == nullの場合)
        else
            // (3-2)該当銘柄の時価がセットされていない場合
            {
            // (3-2-1)時価の取得
            if (l_htCurrentPrices == null)
            {
                l_htCurrentPrices = new Hashtable();
            }

            WEB3IfoTradedProductImpl l_tradeProduct = null;
            //先物OP取引銘柄 = パラメータ.先物OP建玉.getTradedProduct()
            l_tradeProduct = (WEB3IfoTradedProductImpl) l_ifoContract.getTradedProduct();
            //時価情報 = 先物OP取引銘柄.get時価情報(パラメータ.補助口座
            l_ifoCurrentInfo = l_tradeProduct.getCurrentInfo(l_subAccount);
            
            if (l_ifoCurrentInfo != null)
            {
                // 時価情報の追加
                l_htCurrentPrices.put(l_strProductCode, l_ifoCurrentInfo);
                // 時価のセット
                ThreadLocalSystemAttributesRegistry.setAttribute(WEB3IfoAttributeNameDef.CURRENT_PRICE_INFO, l_htCurrentPrices);
            }
        }
        // ３）取得した時価情報を返却
        log.exiting(STR_METHOD_NAME);
        return l_ifoCurrentInfo;
    }

    /**
     * (create返済建玉エントリ)<BR>
     * 注文単位IDに該当する返済建玉のエントリを作成する。<BR>
     *（シーケンス図）「（先物OP残高）create返済建玉エントリ」参照<BR>
     *<BR>
     *=============================================== <BR>
     *シーケンス図 : （シーケンス図）「（先物OP残高）create返済建玉エントリ」 <BR>
     *具体位置     : 1.2 注文単位に該当する返済指定情報の配列を取得する。、<BR>
     *　@　@　@　@　@　@　@　@　@取得できなかった場合は、「該当データなし」の例外をthrowする。 <BR>
     *class        : WEB3BusinessLayerException <BR>
     *tag          : SYSTEM_ERROR_80005 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_lngOrderUnitId - 注文単位Id
     * @@return SettleContractEntry[]
     * @@roseuid 4010AF2C0227
     */
    public SettleContractEntry[] createSettleContractEntry(long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createSettleContractEntry(long)";
        log.entering(STR_METHOD_NAME);

        //1.1 getOrderUnit(注文単位ID : long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager = 
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
        OrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitId);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("DBへのアクセスに失敗しました。", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //1.2 getContractsToClose()
        IfoContractSettleOrderUnit l_ifoContractSettleOrderUnit = 
            (IfoContractSettleOrderUnit)l_orderUnit;
        IfoClosingContractSpec[] l_contractSpecs = 
            l_ifoContractSettleOrderUnit.getContractsToClose();

        //注文単位に該当する返済指定情報の配列を取得する。
        //取得できなかった場合は、「該当データなし」の例外をthrowする。
        if (l_contractSpecs == null || l_contractSpecs.length == 0)
        {
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.3 ArrayList()
        List l_list = new ArrayList(); 

        //1.4 返済指定情報の要素分LOOP
        for (int i = 0; i < l_contractSpecs.length; i++)
        {
            //1.4.1 getContractId( )(先物OP返済指定情報::getContractId)
            long l_lngContractId = l_contractSpecs[i].getContractId();

            //1.4.2 getQuantity( )(先物OP返済指定情報::getQuantity)
            double l_dblQuantity = l_contractSpecs[i].getQuantity();
            if (Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0;
            }
            
            //1.4.3 SettleContractEntry(long, double)(SettleContractEntry::SettleContractEntry)
            //  [コンストラクタの引数] 
            //  建玉ＩＤ：　@getContractId()の戻り値 
            //  数量：　@getQuantity()の戻り値
            SettleContractEntry l_contractEntry = new SettleContractEntry(
                l_lngContractId,
                l_dblQuantity);
                
            //1.4.4 add(arg0 : int, arg1 : Object)
            l_list.add(l_contractEntry);
        }
        
        //1.5 toArray()
        SettleContractEntry[] l_settleContractEntrys = new SettleContractEntry[l_contractSpecs.length];
        l_list.toArray(l_settleContractEntrys);

        log.exiting(STR_METHOD_NAME);
        return l_settleContractEntrys;
    }
    
    
	/**
	  * (updateロック中数量)<BR>
	  * <BR>
	  * （updateLockedQuantityのオーバーライド）<BR>
	  * <BR>
	  * ロック中数量を更新する。<BR>
	  * <BR>
	  * @@param l_lngOrderUnitId - 注文単位ID<BR>
	  * @@param l_contract - 建玉<BR>
	  * @@param l_dblLockedQtyToBeAdjusted - ロック中<BR>
	  * @@throws com.fitechlabs.xtrade.kernel.data.DataException
	  * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
	  */
	public void updateLockedQuantity(long l_lngOrderUnitId, Contract l_contract, double l_dblLockedQtyToBeAdjusted)
		throws RuntimeSystemException
	{
		super.updateLockedQuantity(l_lngOrderUnitId, l_contract, l_dblLockedQtyToBeAdjusted);
		
		if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(l_dblLockedQtyToBeAdjusted))
		{
			return;
		}
			
		//ThreadLocalに設定されているシステム処理時間を取得する			
		Timestamp l_realTimestamp = 
			(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.REAL_TIMESTAMP);
			
		//システム処理時間が設定されている場合のみ、更新日付の更新を行う
		if(l_realTimestamp != null)
		{
			long contractId = l_contract.getContractId();
			IfoLockedContractDetailsParams lockedContractParams = null;
			try{
    			QueryProcessor qp = Processors.getDefaultProcessor();
				IfoLockedContractDetailsRow lockedContractRow = IfoLockedContractDetailsDao.findRowByPk(contractId);
				lockedContractParams = new IfoLockedContractDetailsParams(lockedContractRow);
    			//更新日付を設定する
    			lockedContractParams.setLastUpdatedTimestamp(l_realTimestamp);
				qp.doUpdateQuery(lockedContractParams);
   			}
			catch(DataException de)
			{
				String msg = "DataException while updating Ifo_locked_contract_details for assetId:" + contractId;
				throw new RuntimeSystemException(msg, de);
			}
		}
	}

    /**
      * (get建玉ListBy注文単位)<BR>
      * <BR>
      *　@（指定された注文データに対する、建玉データを全て取得し、）<BR>
      * 　@建玉ParamsのListを作成して返す。<BR>
      * <BR>
      * 　@先物OPポジションヘルパー.先物OP更新データマネージャ.<BR>
      * 　@get建玉ListBy注文単位(引数の注文ID)にdelegateする。<BR>
      * <BR>
      *
      * @@param l_lngOrderId - 注文ID<BR>
      * (注文ID)<BR>
      * @@return List
      * @@throws WEB3BaseException
      */
    public List getContractListByOrderUnit(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractListByOrderUnit(long)";
        log.entering(STR_METHOD_NAME);

        WEB3IfoPersistentDataManager l_persistentDataManager = 
            new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO).new WEB3IfoPersistentDataManager();

        log.exiting(STR_METHOD_NAME);
        return l_persistentDataManager.getContractListByOrderUnit(l_lngOrderId);
    }

    /**
     * (get取引勘定明細ListBy注文単位Plus建玉)<BR>
     * <BR>
     * 　@指定された注文データ＋建玉データに対する、<BR>
     * 　@一口約定計算対象となる取引勘定明細ParamsのListを取得する。<BR>
     * <BR>
     * 先物OPポジションヘルパー.先物OP更新データマネージャ.<BR>
     * get取引勘定明細ListBy注文単位Plus建玉()へdelegateする。<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID<BR>
     * @@param l_lngContractId - (建玉ID)<BR>
     * 建玉ID
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getTransactionsListByOrderUnitPlusContract(long l_lngOrderUnitId, long l_lngContractId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransactionsListByOrderUnitPlusContract(long, long)";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoPersistentDataManager l_persistentDataManager = 
            new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO).new WEB3IfoPersistentDataManager();

        log.exiting(STR_METHOD_NAME);
        return l_persistentDataManager.getTransactionsListByOrderUnitPlusContract(
            l_lngOrderUnitId, l_lngContractId);
    }
}
@
