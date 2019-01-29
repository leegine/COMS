head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoContractImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP建玉(WEB3IfoContractImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/21 王蘭芬(中訊)新規作成
Revesion History : 2008/04/08 趙林鵬(中訊) OP返済一覧の改善対応
Revesion History : 2008/08/18 安陽(中訊) IFO小数点対応
Revesion History : 2008/08/18 安陽(中訊) 仕様変更 モデルNo.903
*/

package webbroker3.ifo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3LogUtility;


/**
 * (先物OP建玉)<BR>
 * 先物OP建玉クラス<BR>
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3IfoContractImpl extends IfoContractImpl
{

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoContractImpl.class);

    /**
     * (get評価損益)<BR>
     * <BR>
     * 指定単価で、指定数量返済した場合の評価損益を取得する。<BR>
     * <BR>
     * 以下の計算結果を返却する。<BR>
     * <BR>
     * (1)建区分が買建の場合<BR>
     *
     * （パラメータ.返済単価 － getContractPrice()） × パラメータ.数量　@×　@(*1) 指数乗数<BR>
     * <BR>
     * (2)建区分が売建の場合<BR>
     * <BR>
     * （getContractPrice() － パラメータ.返済単価） × パラメータ.数量　@×　@(*1) 指数乗数<BR>
     * <BR>
     * (*1) 指数乗数<BR>
     * 　@－プロダクトマネージャ.getTradedProduct()にて取引銘柄を取得する。<BR>
     * 　@[getTradedProduct()に指定する引数]<BR>
     * 　@Product：　@this.getProduct()<BR>
     * 　@Market：　@this.getMarketId()に対応する市場<BR>
     * <BR>
     * 　@－取得した先物OP取引銘柄.1単位当たり乗数<BR>
     * <BR>
     * @@param l_dblSettlementUnitPrice - (返済単価)<BR>
     * <BR>
     * 返済時の単価<BR>
     * <BR>
     * 該当建玉の決済状態が未決済、または決済中・成行注文の場合：時価<BR>
     * <BR>
     * 該当建玉の決済状態が決済中・指値注文の場合：指値<BR>
     * @@param l_dblSettlementCnt - 返済数量<BR>
     *
     * @@return double
     * @@roseuid 4099B7D101DA
     */
    public double getEvaluateIncome(
        double l_dblSettlementUnitPrice,
        double l_dblSettlementCnt) throws WEB3BaseException
    {
        final String l_strMethodName = "getEvaluateIncome";
        log.entering(l_strMethodName);

        // --- Test Log
        log.debug("Input Parm: l_dblSettlementUnitPrice = " + l_dblSettlementUnitPrice);
        log.debug("Input Parm: l_dblSettlementUnitPrice = " + l_dblSettlementCnt);

        double l_dblReturnValue = 0;
        try
        {
            // (*1) 指数乗数
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                Market l_market =
                    l_gentradeFinObjectManager.getMarket(this.getMarketId());

            WEB3IfoTradedProductImpl l_tradedProduct  =
                (WEB3IfoTradedProductImpl)l_productManager.getTradedProduct(
                    this.getProduct(),
                    l_market);

            IfoTradedProductRow l_tradedProduceRow = (IfoTradedProductRow)l_tradedProduct.getDataSourceObject();
            BigDecimal l_bdUnitSize = new BigDecimal(l_tradedProduceRow.getUnitSize() + "");

            // --- Test Log
            log.debug("指数乗数: l_bdUnitSize = " + l_bdUnitSize);

            ContractTypeEnum l_contracttype = this.m_Row.getContractType();

            BigDecimal l_bdSettlementUnitPrice = new BigDecimal(l_dblSettlementUnitPrice + "");
            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice() + "");
            BigDecimal l_bdSettlementCnt = new BigDecimal(l_dblSettlementCnt + "");
            // (1)建区分が買建の場合
            // (パラメータ.返済単価－getContractPrice())×パラメータ.数量×(*1)指数乗数
            if(ContractTypeEnum.LONG.equals(l_contracttype))
            {
                // --- Test Log
                log.debug("ContractType: ContractTypeEnum.LONG" );
                l_dblReturnValue =
                    (l_bdSettlementUnitPrice.subtract(l_bdContractPrice)).multiply(
                        l_bdSettlementCnt).multiply(l_bdUnitSize).doubleValue();
            }

            // (2)建区分が売建の場合
            //（getContractPrice()－パラメータ.返済単価)×パラメータ.数量×(*1) 指数乗数
            if(ContractTypeEnum.SHORT.equals(l_contracttype))
            {
                // --- Test Log
                log.debug("ContractType: ContractTypeEnum.SHORT" );
                l_dblReturnValue =
                    (l_bdContractPrice.subtract(l_bdSettlementUnitPrice)).multiply(
                        l_bdSettlementCnt).multiply(l_bdUnitSize).doubleValue();
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        // --- Test Log
        log.debug("Output Parm: l_dblReturnValue = " + l_dblReturnValue);

        log.exiting(l_strMethodName);
        return l_dblReturnValue;
    }

    /**
     * (get建約定代金)<BR>
     * <BR>
     * 指定数量における建約定代金を取得する。<BR>
     * <BR>
     * 以下の計算結果を返却する。<BR>
     * <BR>
     * getContractPrice() × パラメータ.数量 × 指数乗数(*1)<BR>
     * <BR>
     * (*1) 指数乗数<BR>
     * 　@－プロダクトマネージャ.getTradedProduct()にて取引銘柄を取得する。<BR>
     * 　@[getTradedProduct()に指定する引数]<BR>
     * 　@Product：　@this.getProduct()<BR>
     * 　@Market：　@this.getMarketId()に対応する市場 <BR>
     * <BR>
     * 　@－取得した先物OP取引銘柄.1単位当たり乗数<BR>
     * @@param l_dblCount - 数量<BR>
     * @@return double
     * @@roseuid 4099B7D101E0
     * @@throws webbroker3.common.WEB3BaseException
     */
    public double getContractExecutedAmount(double l_dblCount)
        throws WEB3BaseException
    {
        final String l_strMethodName = "getContractExecutedAmount";
        log.entering(l_strMethodName);

        // --- Test Log
        log.debug("Input Parm: l_dblCount = " + l_dblCount);
        double l_dblReturnValue = 0;
        try
        {
            // (*1) 指数乗数
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                Market l_market =
                    l_gentradeFinObjectManager.getMarket(this.getMarketId());

            WEB3IfoTradedProductImpl l_tradedProduct  =
                (WEB3IfoTradedProductImpl)l_productManager.getTradedProduct(
                    this.getProduct(),
                    l_market);

            IfoTradedProductRow l_tradedProduceRow =
                (IfoTradedProductRow)l_tradedProduct.getDataSourceObject();
            double l_dblUnitSize = l_tradedProduceRow.getUnitSize();

            // --- Test Log
            log.debug("指数乗数: l_dblUnitSize = " + l_dblUnitSize);

            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice() + "");
            BigDecimal l_bdCount = new BigDecimal(l_dblCount + "");
            BigDecimal l_bdUnitSize = new BigDecimal(l_dblUnitSize + "");
            // getContractPrice()×パラメータ.数量×指数乗数(*1)の計算結果を返却する
            l_dblReturnValue = l_bdContractPrice.multiply(l_bdCount).multiply(l_bdUnitSize).doubleValue();
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        // --- Test Log
        log.debug("Output Parm: l_dblReturnValue = " + l_dblReturnValue);
        log.exiting(l_strMethodName);
        return l_dblReturnValue;
    }

    /**
     * (get建手数料)<BR>
     * <BR>
     * 指定数量の建手数料を計算する。<BR>
     * （未決済分の建手数料取得に使用）<BR>
     * <BR>
     * 以下の計算結果を返却する。<BR>
     * <BR>
     * (get建委託手数料() * パラメータ.数量) / get建玉数量()<BR>
     * ※小数点以下切り捨て<BR>
     * <BR>
     * @@param l_dblContractCnt - 数量<BR>
     * @@return double
     * @@roseuid 4099B7D101EA
     */
    public double getContractCommission(double l_dblContractCnt)
    {
        final String l_strMethodName = "getContractCommission";
        log.entering(l_strMethodName);

        // --- Test Log
        log.debug("Input Parm: l_dblContractCnt = " + l_dblContractCnt);
        double l_dblReturnValue = 0;

        // (get建委託手数料() * パラメータ.数量) / get建玉数量()の計算結果を返却する
        BigDecimal l_bdSetupFee = new BigDecimal(this.m_Row.getSetupFee() + "");
        BigDecimal l_bdQuantity = new BigDecimal(this.m_Row.getQuantity() + "");
        BigDecimal l_bdContractCnt = new BigDecimal(l_dblContractCnt + "");

        if (l_bdQuantity.doubleValue() != 0)
        {
            l_dblReturnValue =
                l_bdSetupFee.multiply(l_bdContractCnt).divide(l_bdQuantity, 0, BigDecimal.ROUND_FLOOR).doubleValue();
        }

        // --- Test Log
        log.debug("l_bdSetupFee = " + l_bdSetupFee);
        log.debug("l_bdQuantity = " + l_bdQuantity);
        log.debug("Output Parm: l_dblReturnValue = " + l_dblReturnValue);

        log.exiting(l_strMethodName);
        return l_dblReturnValue;
    }

    /**
     * (先物OP建玉)<BR>
     * <BR>
     * コンストラクタ。<BR>
     * <BR>
     * 建玉ＩＤに一致する行を建玉テーブルより取得する。<BR>
     * 取得した建玉行オブジェクト（IfoContractParams）を指定し<BR>
     * スーパークラスのコンストラクタをコールする。<BR>
     * @@param l_lngContractId - 建玉ＩＤ<BR>
     * @@return webbroker3.ifo.WEB3IfoContractImpl
     * @@roseuid 4099B7D101EC
     */
    public WEB3IfoContractImpl(long l_lngContractId)
        throws DataQueryException, DataNetworkException
    {
      super(l_lngContractId);
    }
    /**
     * (先物OP建玉)<BR>
     * <BR>
     * コンストラクタ。<BR>
     * <BR>
     * 建玉ＩＤに一致する行を建玉テーブルより取得する。<BR>
     * 取得した建玉行オブジェクト（IfoContractParams）を指定し<BR>
     * スーパークラスのコンストラクタをコールする。<BR>
     * @@param l_row - IfoContractRow建玉ＩＤ<BR>
     * @@return webbroker3.ifo.WEB3IfoContractImpl
     * @@roseuid 4099B7D101EC
     */
    public WEB3IfoContractImpl(IfoContractRow l_row)
    {
      super(l_row);
    }

    /**
     * (getロック中数量)<BR>
     * <BR>
     * （getLockedQuantityのオーバーロード）<BR>
     * 指定の注文単位がロック中の数量を取得する。<BR>
     * <BR>
     * １）　@返済指定情報読込<BR>
     * 以下の条件で建玉返済指定情報テーブルを読む。<BR>
     * <BR>
     * [条件]<BR>
     * 建玉返済指定情報.建玉ＩＤ = this.getContractId()<BR>
     * 建玉返済指定情報.注文単位ＩＤ = 引数.注文単位ＩＤ<BR>
     * <BR>
     * 該当行が存在しない場合は0を返却する。<BR>
     * <BR>
     * ２）　@ロック中数量返却<BR>
     *
     * 取得した建株返済指定情報行ごとに、<BR>
     * 建株返済指定情報行.返済注文数量 － 建株返済指定情報行.返済約定数量 を計算する。 <BR>
     * 上記計算結果の、全ての返済指定情報行のSUM値を返却する。<BR>
     * <BR>
     * @@param l_lngOrderUnitＩd - 注文単位ＩＤ<BR>
     * @@return double
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4099B7D101EE
     */
    public double getLockedQuantity(long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String l_strMethodName = "getLockedQuantity";
        log.entering(l_strMethodName);

        // --- Test Log
        log.debug("Input Parm: l_lngOrderUnitId = " + l_lngOrderUnitId);

        double l_dblReturnValue = 0;
        try
        {
            // １）返済指定情報読込

            // [条件1]:建玉返済指定情報.建玉ＩＤ = this.getContractId()
            String l_strWhere = " contract_id = ? and order_unit_id = ? ";
            long l_lngContractIdValue = this.getContractId();

            // [条件1]:建玉返済指定情報.注文単位ＩＤ = 引数.注文単位ＩＤ

            Object[] l_objWhereValues =
            {
                new Long(l_lngContractIdValue), new Long(l_lngOrderUnitId)
            };

            // --- Test Log
            log.debug("SQL: l_strWhere = " + l_strWhere);
            log.debug("SQL: l_objWhereValues 0 = " + l_objWhereValues[0]);
            log.debug("SQL: l_objWhereValues 1 = " + l_objWhereValues[1]);

            List l_returnList = new ArrayList();

            QueryProcessor processor = Processors.getDefaultProcessor();
            l_returnList =
                processor.doFindAllQuery(
                    IfoClosingContractSpecParams.TYPE,
                    l_strWhere,
                    l_objWhereValues);

            // ２）ロック中数量返却
            for (int i = 0; i < l_returnList.size(); i++)
            {
                //建株返済指定情報行.返済注文数量 － 建株返済指定情報行.返済約定数量 を計算する。
                //上記計算結果の、全ての返済指定情報行のSUM値を返却する。
                IfoClosingContractSpecParams l_contractSpecParams =
                    new IfoClosingContractSpecParams((IfoClosingContractSpecRow)l_returnList.get(i));
                double l_dblQuantity = l_contractSpecParams.getQuantity();
                double l_dblExecutedQuantity = l_contractSpecParams.getExecutedQuantity();
                // --- Test Log
                log.debug("DB return value l_dblQuantity No."+ i + "= " + l_dblQuantity);

                l_dblReturnValue += (l_dblQuantity - l_dblExecutedQuantity);
            }

            // --- Test Log
            log.debug("Output Parm: l_dblReturnValue = " + l_dblReturnValue);

        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "建玉返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            //throw new DataException(l_strMessage);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "建玉返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(l_strMethodName);

        return l_dblReturnValue;
    }

    /**
     * (get返済約定済数量)<BR>
     * <BR>
     * 指定の注文単位に関連する返済約定済数量を取得する。<BR>
     * <BR>
     * １）　@返済指定情報読込<BR>
     * 以下の条件で建玉返済指定情報テーブルを読む。<BR>
     * <BR>
     * [条件]<BR>
     * 建玉返済指定情報.建玉ＩＤ = this.getContractId()<BR>
     * 建玉返済指定情報.注文単位ＩＤ = 引数.注文単位ＩＤ<BR>
     * <BR>
     * 該当行が存在しない場合は0を返却する。<BR>
     * <BR>
     * ２）　@返済約定済数量返却<BR>
     * 取得した返済指定情報行.返済約定数量の合計値を返却する。<BR>
     * <BR>
     * @@param l_lngOrderUnitId - 注文単位ＩＤ<BR>
     * @@return double
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4099B7D101F0
     */
    public double getClosingExecuteContractCnt(long l_lngOrderUnitId)
        throws  WEB3BaseException
    {
        final String l_strMethodName = "getClosingExecuteContractCnt";
        log.entering(l_strMethodName);


        // --- Test Log
        log.debug("Input Parm: l_lngOrderUnitId = " + l_lngOrderUnitId);

        double l_dblReturnValue = 0;
        try
        {
            // １）返済指定情報読込

            // [条件1]:建玉返済指定情報.建玉ＩＤ = this.getContractId()
            String l_strWhere = " contract_id = ? and order_unit_id = ? ";
            long l_lngContractIdValue = this.getContractId();

            // [条件1]:建玉返済指定情報.注文単位ＩＤ = 引数.注文単位ＩＤ

            Object[] l_objWhereValues =
            {
                new Long(l_lngContractIdValue), new Long(l_lngOrderUnitId)
            };

            // --- Test Log
            log.debug("SQL: l_strWhere = " + l_strWhere);
            log.debug("SQL: l_objWhereValues contract_id = " + l_objWhereValues[0]);
            log.debug("SQL: l_objWhereValues order_unit_id = " + l_objWhereValues[1]);

            List l_returnList = new ArrayList();

            // データ査詢

            // --- Test Log
            log.debug("Excute SQL query!");

            QueryProcessor processor = Processors.getDefaultProcessor();
            l_returnList =
                processor.doFindAllQuery(
                    IfoClosingContractSpecParams.TYPE,
                    l_strWhere,
                    l_objWhereValues);

            // ２）返済約定済数量返却


            // --- Test Log
            log.debug("DB return value size = " + l_returnList.size());

            for (int i = 0; i < l_returnList.size(); i++)
            {
                // 取得した返済指定情報行.返済約定数量の合計値を返却する。
                IfoClosingContractSpecParams l_contractSpecParams =
                    new IfoClosingContractSpecParams((IfoClosingContractSpecRow)l_returnList.get(i));
                double l_dblExecuteQuantity =
                    l_contractSpecParams.getExecutedQuantity();

                // --- Test Log
                log.debug("DB return value l_dblExecuteQuantity No."+ i + "= " + l_dblExecuteQuantity);

                l_dblReturnValue += l_dblExecuteQuantity;
            }

            // --- Test Log
            log.debug("Output Parm: l_dblReturnValue = " + l_dblReturnValue);

        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "建玉返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException();
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "建玉返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);
            //throw new WEB3SystemLayerException();
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(l_strMethodName);

        return l_dblReturnValue;
    }

    /**
     * (get建手数料消費税)<BR>
     * <BR>
     * 指定数量の建手数料消費税を計算する。<BR>
     * （未決済分の建手数料消費税取得に使用）<BR>
     * <BR>
     * 以下の計算結果を返却する。<BR>
     * <BR>
     * (get建委託手数料消費税() * パラメータ.数量) / get建玉数量()<BR>
     * ※小数点以下切り捨て<BR>
     * @@param l_dblContractCnt - 数量<BR>
     * @@return double
     * @@roseuid 4099B7D101F2
     */
    public double getContractCommissionConsumptionTax(double l_dblContractCnt)
    {
        final String l_strMethodName = "getContractCommissionConsumptionTax";
        log.entering(l_strMethodName);


        // --- Test Log
        log.debug("Input Parm: l_dblContractCnt = " + l_dblContractCnt);

        double l_dblReturnValue = 0;

        // (get建委託手数料消費税() * パラメータ.数量) / get建玉数量()の計算結果を返却する
        BigDecimal l_bdSetupFeeTax = new BigDecimal(this.m_Row.getSetupFeeTax() + "");
        BigDecimal l_bdQuantity = new BigDecimal(this.m_Row.getQuantity() + "");
        BigDecimal l_bdContractCnt = new BigDecimal(l_dblContractCnt + "");

        if (l_bdQuantity.doubleValue() != 0)
        {
            l_dblReturnValue =
                l_bdSetupFeeTax.multiply(l_bdContractCnt).divide(l_bdQuantity, 0, BigDecimal.ROUND_FLOOR).doubleValue();
        }

        // --- Test Log
        log.debug("l_bdSetupFeeTax = " + l_bdSetupFeeTax);
        log.debug("l_bdQuantity = " + l_bdQuantity);
        log.debug("Output Parm: l_dblReturnValue = " + l_dblReturnValue);


        log.exiting(l_strMethodName);
     return l_dblReturnValue;
    }
    
    /**
     * (get建手数料)<BR>
     * <BR>
     *指定数量分の建手数料を計算する。<BR> 
     *（決済分を考慮した建手数料取得に使用）<BR> 
     * <BR>
     *（未決済分(*1)の建手数料＋決済済分(*2)の建手数料）を返す。<BR> 
     *-------------------------------<BR> 
     *(*1)建玉の同名プロパティから按分計算により取得。<BR> 
     *(*2)決済約定により建玉から減算された金額。決済のトランザクションより取得。<BR> 
     *-------------------------------<BR> 
     * <BR>
     *１）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、<BR> 
     *　@　@　@this.建玉ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。<BR> 
     * <BR>
     *　@　@　@this.get返済約定済数量(注文単位ID)により取得する。<BR> 
     * <BR>
     *　@　@　@----------------------------------------------------------<BR> 
     *　@　@　@[引数の設定]<BR> 
     * <BR>
     *　@　@　@注文単位ID：　@引数の注文単位ID<BR> 
     *　@　@　@----------------------------------------------------------<BR> 
     * <BR>
     *２）　@this.建玉数 > 0（＝未決済分あり）の場合、未決済分の建手数料を取得する。<BR>  
     * <BR>
     *　@　@　@this.get建手数料（数量）により取得する。<BR>  
     * <BR>
     *　@　@　@----------------------------------------------------------<BR> 
     *　@　@　@[引数の設定]<BR>  
     * <BR>
     *　@　@　@数量：　@引数の数量 - １）の決済約定数量<BR>  
     *　@　@　@----------------------------------------------------------<BR>  
     * <BR>
     *　@　@　@建手数料（未決済分） ＝ this.get建手数料（数量）戻り値 <BR> 
     * <BR>
     *３）　@１）の決済約定数量 > 0（＝決済約定あり）の場合、決済済分の建手数料を取得する。<BR>  
     * <BR>
     *　@３?１）　@当該建玉＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を <BR> 
     *　@　@　@　@　@取得する。 
     * <BR>
     *　@　@　@先物OPトランザクションマネージャ.getトランザクション(注文単位ID,建玉ID)により取得する。<BR>  
     * <BR>
     *　@　@　@----------------------------------------------------------<BR>  
     *　@　@　@[引数の設定]<BR>  
     * <BR>
     *　@　@　@注文単位ID：　@引数の注文単位ID<BR>  
     *　@　@　@建玉ID：　@this.建玉ID<BR>  
     *　@　@　@----------------------------------------------------------<BR>  
     * <BR>
     *　@３?２）　@取得したトランザクションの全要素の建手数料を集計する。<BR>  
     * <BR>
     *　@　@　@　@　@建手数料（決済済分） += トランザクション.建手数料<BR>  
     * <BR>
     *　@　@　@※決済約定がない場合は建手数料（決済済分）は0となる 。<BR>  
     * <BR>
     *４）　@（建手数料（未決済分） + 建手数料（決済済分））を返却する。<BR> 
     * <BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException<BR>
     * @@return double 
     */
    public double getContractCommission(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String l_strMethodName = "getContractCommission(double, long)";
        log.entering(l_strMethodName);

        double l_dblReturnValue = 0D;
        double l_dblClosingExecutedQuantity = 0D;
        double l_dblCommissionOpen = 0D;

        BigDecimal l_bdCommissionOpen = new BigDecimal("0");
        BigDecimal l_bdCommissionClose = new BigDecimal("0");
        
        //１）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合 
        //this.建玉ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する 
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = 
                this.getClosingExecuteContractCnt(l_lngOrderUnitId);
        }
        
        //２）　@this.建玉数 > 0（＝未決済分あり）の場合、未決済分の建手数料を取得する
        if (this.getQuantity() > 0D)
        {
            l_dblCommissionOpen =
                this.getContractCommission(l_dblQuantity - l_dblClosingExecutedQuantity);
            l_bdCommissionOpen = new BigDecimal(l_dblCommissionOpen + "");
        }

        // ３）　@１）の決済約定数量 > 0（＝決済約定あり）の場合、決済済分の建手数料を取得する  
        if(l_dblClosingExecutedQuantity > 0D)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoFinTransactionManagerImpl l_finTransactionManager = 
                (WEB3IfoFinTransactionManagerImpl) l_tradingModule.getFinTransactionManager();        
            List l_transactions = l_finTransactionManager.getTransactions(l_lngOrderUnitId, this.getContractId());  
            for (int j = 0; j < l_transactions.size(); j++)
            {
                IfoFinTransactionRow l_finTransactionRow = (IfoFinTransactionRow)l_transactions.get(j);  
                BigDecimal l_bdImportedSetupFee = new BigDecimal(l_finTransactionRow.getImportedSetupFee() + "");
                l_bdCommissionClose = l_bdCommissionClose.add(l_bdImportedSetupFee);
            }
        }
        
        //４）　@（建手数料（未決済分） + 建手数料（決済済分））を返却する 
        l_dblReturnValue = l_bdCommissionOpen.add(l_bdCommissionClose).doubleValue();
        log.debug("建手数料（未決済分＋決済済分） = " + l_dblReturnValue);
        
        log.exiting(l_strMethodName);
        return l_dblReturnValue;
    }
    
    /**
     * (get建手数料消費税)<BR>
     * <BR>
     *指定数量分の建手数料消費税を計算する。<BR> 
     *（決済分を考慮した建手数料消費税取得に使用）<BR> 
     * <BR>
     *（未決済分(*1)の建手数料消費税＋決済済分(*2)の建手数料消費税）を返す。<BR> 
     *-------------------------------<BR> 
     *(*1)建玉の同名プロパティから按分計算により取得。<BR> 
     *(*2)決済約定により建玉から減算された金額。決済のトランザクションより取得。<BR> 
     *-------------------------------<BR> 
     * <BR>
     *１）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、<BR> 
     *　@　@　@this.建玉ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。<BR> 
     * <BR>
     *　@　@　@this.get返済約定済数量(注文単位ID)により取得する。<BR> 
     * <BR>
     *　@　@　@----------------------------------------------------------<BR> 
     *　@　@　@[引数の設定]<BR> 
     * <BR>
     *　@　@　@注文単位ID：　@引数の注文単位ID<BR> 
     *　@　@　@----------------------------------------------------------<BR> 
     * <BR>
     *２）　@this.建玉数 > 0（＝未決済分あり）の場合、未決済分の建手数料消費税を取得する。<BR>  
     * <BR>
     *　@　@　@this.get建手数料消費税（数量）により取得する。<BR>  
     * <BR>
     *　@　@　@----------------------------------------------------------<BR> 
     *　@　@　@[引数の設定]<BR>  
     * <BR>
     *　@　@　@数量：　@引数の数量 - １）の決済約定数量<BR>  
     *　@　@　@----------------------------------------------------------<BR>  
     * <BR>
     *　@　@　@建手数料消費税（未決済分） ＝ this.get建手数料消費税（数量）戻り値 <BR> 
     * <BR>
     *３）　@１）の決済約定数量 > 0（＝決済約定あり）の場合、決済済分の建手数料消費税を取得する。<BR>  
     * <BR>
     *　@３－１）　@当該建玉＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を <BR> 
     *　@　@　@　@　@取得する。 
     * <BR>
     *　@　@　@先物OPトランザクションマネージャ.getトランザクション(注文単位ID,建玉ID)により取得する。<BR>  
     * <BR>
     *　@　@　@----------------------------------------------------------<BR>  
     *　@　@　@[引数の設定]<BR>  
     * <BR>
     *　@　@　@注文単位ID：　@引数の注文単位ID<BR>  
     *　@　@　@建玉ID：　@this.建玉ID<BR>  
     *　@　@　@----------------------------------------------------------<BR>  
     * <BR>
     *　@３－２）　@取得したトランザクションの全要素の建手数料消費税を集計する。<BR>  
     * <BR>
     *　@　@　@　@　@建手数料消費税（決済済分） += トランザクション.建手数料消費税<BR>  
     * <BR>
     *　@　@　@※決済約定がない場合は建手数料消費税（決済済分）は0となる 。<BR>  
     * <BR>
     *４）　@（建手数料消費税（未決済分） + 建手数料消費税（決済済分））を返却する。<BR> 
     * <BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException<BR>
     * @@return double 
     */
    public double getContractCommissionConsumptionTax(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String l_strMethodName = "getContractCommissionConsumptionTax(double, long)";
        log.entering(l_strMethodName);

        double l_dblReturnValue = 0D;
        double l_dblClosingExecutedQuantity = 0D;
        double l_dblCommissionConsumptionTaxOpen = 0D;

        BigDecimal l_bdCommissionConsumptionTaxOpen = new BigDecimal("0");
        BigDecimal l_bdCommissionConsumptionTaxClose = new BigDecimal("0");
        
        //１）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合 
        //this.建玉ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する 
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = 
                this.getClosingExecuteContractCnt(l_lngOrderUnitId);
        }
        
        //２）　@this.建玉数 > 0（＝未決済分あり）の場合、未決済分の建手数料消費税を取得する
        if (this.getQuantity() > 0D)
        {
            l_dblCommissionConsumptionTaxOpen =
                this.getContractCommissionConsumptionTax(l_dblQuantity - l_dblClosingExecutedQuantity);
            l_bdCommissionConsumptionTaxOpen =
                new BigDecimal(l_dblCommissionConsumptionTaxOpen + "");
        }

        // ３）　@１）の決済約定数量 > 0（＝決済約定あり）の場合、決済済分の建手数料消費税を取得する  
        if(l_dblClosingExecutedQuantity > 0D)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoFinTransactionManagerImpl l_finTransactionManager = 
                (WEB3IfoFinTransactionManagerImpl) l_tradingModule.getFinTransactionManager();        
            List l_transactions = l_finTransactionManager.getTransactions(l_lngOrderUnitId, this.getContractId());  
            for (int j = 0; j < l_transactions.size(); j++)
            {
                IfoFinTransactionRow l_finTransactionRow = (IfoFinTransactionRow)l_transactions.get(j);  
                BigDecimal l_bdImportedSetupFeeTax = new BigDecimal(l_finTransactionRow.getImportedSetupFeeTax() + "");
                l_bdCommissionConsumptionTaxClose = l_bdCommissionConsumptionTaxClose.add(l_bdImportedSetupFeeTax);
            }
        }
        
        //４）　@（建手数料消費税（未決済分） + 建手数料消費税（決済済分））を返却する 
        l_dblReturnValue = l_bdCommissionConsumptionTaxOpen.add(l_bdCommissionConsumptionTaxClose).doubleValue();
        log.debug("建手数料消費税（未決済分＋決済済分） = " + l_dblReturnValue);
        
        log.exiting(l_strMethodName);
        return l_dblReturnValue;
    }
}
@
