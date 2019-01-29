head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoPositionManagerHelper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OPポジションヘルパー(WEB3IfoPositionManagerHelper.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/20 王蘭芬(中訊)新規作成
Revesion History : 2007/06/08 肖志偉(中訊)仕様変更 モデル643,734 DB更新仕様167
Revesion History : 2008/03/13 金傑(中訊) モデル 824
Revesion History : 2008/08/18 安陽(中訊) IFO小数点対応
Revesion History : 2008/08/19 安陽(中訊) 仕様変更 モデルNo.902
*/

package webbroker3.ifo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ConsolidatedCommissionInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderExecutionImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoPositionManagerHelper;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OPポジションヘルパー)<BR>
 * 先物OPポジションヘルパークラス<BR>
 * ポジションマネージャヘルパークラス。<BR>
 * 残高更新の手続きを行う。<BR>
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */

public class WEB3IfoPositionManagerHelper extends IfoPositionManagerHelper
{

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoPositionManagerHelper.class);

    /**
     * コンストラクタ。
     *
     * @@param l_tradingModuleType - 商品タイプ<BR>
     * @@roseuid 40C0750C0186
     */
    public WEB3IfoPositionManagerHelper(ProductTypeEnum l_tradingModuleType)
    {
        super(l_tradingModuleType);
    }

	/**
	 * (set約定情報Toトランザクション（新規建の場合に呼ばれる）)<BR>
	 * <BR>
	 * （setExecutionInfoToMarketOrderedTransのオーバーライド）<BR>
	 * <BR>
	 * 約定データをトランザクション（取引勘定明細）行オブジェクトにセットする。<BR>
	 * ※新規建で使用<BR>
	 * <BR>
	 * setExecutionInfoToMarketOrderedTrans(取引勘定明細Params,約定,注文単位Row,建玉ID)をコールする。
	 * <BR>
	 * [setExecutionInfoToMarketOrderedTrans()に指定する引数]<BR>
	 *   取引勘定明細Params:　@引数.取引勘定明細Params<BR>
	 *   約定：　@引数.約定<BR>
	 * 　@注文単位Row：　@引数.注文単位Row<BR>
	 * 　@建玉ID：　@0<BR>
	 * <BR>
	 * @@param l_ifoFinTransactionParams - トランザクション（取引勘定明細）行オブジェクト<BR>
	 * @@param l_ifoOrderExecution - 約定オブジェクト<BR>
	 * @@param l_ifoOrderUnitRow - 注文単位 行オブジェクト<BR>
	 * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
	 * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
	 * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
	 * @@roseuid 40735F710281
	 */
	protected void setExecutionInfoToMarketOrderedTrans(
		IfoFinTransactionParams l_ifoFinTransactionParams,
		IfoOrderExecution l_ifoOrderExecution,
		IfoOrderUnitRow l_ifoOrderUnitRow)
		throws DataQueryException, DataNetworkException,
			RuntimeSystemException
	{
		this.setExecutionInfoToMarketOrderedTrans(
				l_ifoFinTransactionParams,
				l_ifoOrderExecution,
				l_ifoOrderUnitRow,
				0
				);
	}
	
    /**
	 * (set約定情報Toトランザクション（返済の場合に呼ばれる）)<BR>
     * <BR>
     * （setExecutionInfoToMarketOrderedTransのオーバーロード）<BR>
     * <BR>
     * 約定データをトランザクション（取引勘定明細）行オブジェクトにセットする。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP残高）set約定情報Toトランザクション」参照。<BR>
     * <BR>
	 * @@param l_ifoFinTransactionParams - トランザクション（取引勘定明細）行オブジェクト<BR>
     * @@param l_ifoOrderExecution - 約定オブジェクト<BR>
     * @@param l_ifoOrderUnitRow - 注文単位 行オブジェクト<BR>
     * @@param l_contractId - 建玉ID
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
     */
    protected void setExecutionInfoToMarketOrderedTrans(
        IfoFinTransactionParams l_ifoFinTransactionParams,
        IfoOrderExecution l_ifoOrderExecution,
        IfoOrderUnitRow l_ifoOrderUnitRow,
        long l_contractId)
        throws DataQueryException, DataNetworkException,
            RuntimeSystemException
    {
        final String STR_METHOD_NAME = "setExecutionInfoToMarketOrderedTrans";
        log.entering(STR_METHOD_NAME);

        try
        {
            // 1.スーパークラスの処理をコールする。
            super.setExecutionInfoToMarketOrderedTrans(
                l_ifoFinTransactionParams,
                l_ifoOrderExecution,
                l_ifoOrderUnitRow);
            
            WEB3IfoPersistentDataManager l_persistentDataManager =
                new WEB3IfoPersistentDataManager();

            //注文単位ＩＤ：　@注文単位Params.getOrderUnitId()
            long l_lngOrderUnitId = l_ifoOrderUnitRow.getOrderUnitId();

            // 2.同一注文に関連する、既に約定済の取引勘定明細をリストで取得する。
            List l_lisRet =
                l_persistentDataManager.getFinTransactionDetailForOrderUnit(
                    l_lngOrderUnitId);

            // 3.取得した取引勘定明細リストに要素があるかを判定する。
            //   －ない場合は、以降の処理を行わずに処理を終了する。
            if (l_lisRet.isEmpty())
            {
                //○既約定がない場合（isEmpty() == true）
                //－引数の取引勘定明細Paramsを１番目の要素（index=0）に格納したsize1の配
                l_lisRet = new ArrayList();
                l_lisRet.add(l_ifoFinTransactionParams);
            }
            else
            {
                // 4.引数の取引勘定明細Paramsオブジェクトを、
                //   get取引勘定明細ForOrderUnit()で取得したリストの末尾に追加する。
                l_lisRet.add(l_ifoFinTransactionParams);
            }

            // 5.配列に変換する。
            IfoFinTransactionParams[] l_ifoFinTransactionParamses =
                new IfoFinTransactionParams[l_lisRet.size()];
            l_lisRet.toArray(l_ifoFinTransactionParamses);

            // 6.先物の日計り返済(手数料徴収なし)以外の場合、手数料の按分を行う
            ConsolidatedCommissionInfo l_consolidatedCommissionInfo = null;
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider =
                (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
            l_consolidatedCommissionInfo = l_ifoBizLogicProvider.calcCommission(l_ifoFinTransactionParamses);

            // 7.取引勘定明細Paramsリストの要素分LOOP
            for (int i = 0; i < l_ifoFinTransactionParamses.length; i++)
            {
                // 按分後の計算結果を取引勘定明細Paramsにセットする。
                
                // 8.calc手数料（按分）()メソッドの戻り値より、
                //   指定要素番号の委託手数料を取得する。
                double l_dblCommission = l_consolidatedCommissionInfo.getCommission(i);
                
                // 9.委託手数料をトランザクション（取引勘定明細）にセットする。
                l_ifoFinTransactionParamses[i].setCommissionFee(l_dblCommission);
                
                // 10.calc手数料（按分）()メソッドの戻り値より、
                //    指定要素番号の委託手数料消費税を取得する。
                double l_dblSalesTax = l_consolidatedCommissionInfo.getSalesTax(i);
                
                // 11.委託手数料消費税をトランザクション（取引勘定明細）にセットする
                l_ifoFinTransactionParamses[i].setCommissionFeeTax(l_dblSalesTax);

                //set金額(IfoFinTransactionParams)
                setMarginNetAmount(l_ifoFinTransactionParamses[i]);

                Map l_mapFinTransaction = new Hashtable();
                l_mapFinTransaction.put("commission_fee",Double.toString(l_ifoFinTransactionParamses[i].getCommissionFee()));
                l_mapFinTransaction.put("commission_fee_tax",Double.toString(l_ifoFinTransactionParamses[i].getCommissionFeeTax()));
                l_mapFinTransaction.put("capital_gain",new Double(l_ifoFinTransactionParamses[i].getCapitalGain()));
                l_mapFinTransaction.put("net_amount",new Double(l_ifoFinTransactionParamses[i].getNetAmount()));
                l_mapFinTransaction.put("last_updated_timestamp",GtlUtils.getSystemTimestamp());

                // 12.メッセージ (*)最終要素でない場合、更新を実施する。
                if ( i == l_ifoFinTransactionParamses.length - 1)
                {
                    continue;
                }

                // 13.トランザクション（取引勘定明細）行オブジェクトをDBに更新する
                l_persistentDataManager.updateFinTransaction(l_ifoFinTransactionParamses[i],l_mapFinTransaction);

                //notifyUpdateGtl(IfoFinTransactionParams)
                // --- トランザクション（取引勘定明細）行を元に、
                // --- トランザクション（顧客勘定明細）を更新する。
                notifyUpdateGtl(l_ifoFinTransactionParamses[i]);
            }
        }
        catch (DataException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                STR_METHOD_NAME);
        }
        

        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (set金額)<BR>
     * <BR>
     * （setMarginNetAmountのオーバーライド）<BR>
     * <BR>
     * トランザクション（取引勘定明細）行オブジェクトに以下の金額項目をセットする。<BR>
     * <BR>
     * －受渡金額<BR>
     * －譲渡益金額（評価損益）<BR>
     * <BR>
     * ※ 新規建の場合は、両方0。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP残高）set金額」参照。<BR>
     * @@param l_ifoFinTransactionParams - 取引トランザクション 行オブジェクト<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataException
     * @@roseuid 40736407004F
     */
    protected void setMarginNetAmount(
        IfoFinTransactionParams
        l_ifoFinTransactionParams)
        throws DataException
    {
        final String l_strMethodName = "setMarginNetAmount";
        log.entering(l_strMethodName);

        // 1.銘柄ＩＤを取得する
        long l_lngProductId = l_ifoFinTransactionParams.getProductId();

        // 2.先物OP銘柄オブジェクトを生成する。
        WEB3IfoProductImpl l_web3IfoProductImpl =
            new WEB3IfoProductImpl(l_lngProductId);

        // 3.トランザクションタイプを取得する。
        FinTransactionType l_finTransactionType =
            l_ifoFinTransactionParams.getFinTransactionType();
        log.debug("トランザクションタイプ = " + l_finTransactionType);

        // 4.委託手数料を取得する。
        double l_dblCommissionFee =
            l_ifoFinTransactionParams.getCommissionFee();
        log.debug("委託手数料 = " + l_dblCommissionFee);

        // 5.委託手数料消費税を取得する。
        double l_dblCommissionFeeTax =
            l_ifoFinTransactionParams.getCommissionFeeTax();
        log.debug("委託手数料消費税 = " + l_dblCommissionFeeTax);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoBizLogicProvider l_web3IfoBizLogicProvider =
            (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();

        WEB3IfoProductManagerImpl l_productManagerImpl = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
        // 売買：calc受渡代金()に指定する引数1
        SideEnum l_dealing = null;

        // 諸費用計算用代金：calc受渡代金()に指定する引数2
        double l_dblExpenseCalculationAmount;

        // 委託手数料：calc受渡代金()に指定する引数3
        double l_dblConsignmentCommission = l_dblCommissionFee;

        // 委託手数料消費税：calc受渡代金()に指定する引数4
        double l_dblCommConsumptionTax = l_dblCommissionFeeTax;

        // 取引トランザクション.getFinTransactionType()==”買建返済取引（売返済）”
        // または、”新規建売取引”の場合、－”売”（SideEnum.SELL）
        if(FinTransactionType.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE.equals(
            l_finTransactionType) ||
            FinTransactionType.EQTYPE_IDX_OPTIONS_SELL_TO_OPEN .equals(
            l_finTransactionType) ||
            FinTransactionType.EQTYPE_IDX_FUTURES_SELL_TO_CLOSE.equals(
            l_finTransactionType) ||
            FinTransactionType.EQTYPE_IDX_FUTURES_SELL_TO_OPEN.equals(
            l_finTransactionType))
        {
            log.debug("取引トランザクション.getFinTransactionType()==”買建返済取引（売返済）” または、”新規建売取引”の場合");
            l_dealing = SideEnum.SELL;
        }

        // 取引トランザクション.getFinTransactionType()==”売建返済取引（買返済）”
        // または、”新規建買取引”の場合、－”買”（SideEnum.BUY）
        if (FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE.equals(
            l_finTransactionType) ||
            FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_OPEN.equals(
            l_finTransactionType) ||
            FinTransactionType.EQTYPE_IDX_FUTURES_BUY_TO_CLOSE.equals(
            l_finTransactionType) ||
            FinTransactionType.EQTYPE_IDX_FUTURES_BUY_TO_OPEN.equals(
            l_finTransactionType))
        {
            log.debug("取引トランザクション.getFinTransactionType()==”売建返済取引（買返済）” または、”新規建買取引”の場合");
            l_dealing = SideEnum.BUY;
        }
        log.debug("l_dealing = " + l_dealing);

        //取引トランザクション.約定数量×取引トランザクション.約定単価
        WEB3IfoTradedProductImpl l_tradedProductImpl = null;
        try
        {
            l_tradedProductImpl = (WEB3IfoTradedProductImpl)l_productManagerImpl.getTradedProduct(l_lngProductId,l_ifoFinTransactionParams.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + l_strMethodName, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + l_strMethodName,
                l_nfe.getMessage(),
                l_nfe);
        }
        BigDecimal l_bdQuantity = new BigDecimal("" + l_ifoFinTransactionParams.getQuantity());
        BigDecimal l_bdPrice = new BigDecimal("" + l_ifoFinTransactionParams.getPrice());
        BigDecimal l_bdUnitSize = new BigDecimal("" + l_tradedProductImpl.getUnitSize());
        l_dblExpenseCalculationAmount = l_bdQuantity.multiply(l_bdPrice).multiply(l_bdUnitSize).doubleValue();
        log.debug("l_dblExpenseCalculationAmount = "  + l_dblExpenseCalculationAmount);

        // 6.今回約定の受渡代金を計算する。
        double l_dblNetAmountKY = l_web3IfoBizLogicProvider.calcDeliveryAmount(
            l_dealing,
            l_dblExpenseCalculationAmount,
            l_dblConsignmentCommission,
            l_dblCommConsumptionTax);


        // 7.オプション銘柄かを判定する。
        boolean l_blnFlag = l_web3IfoProductImpl.isOptionProduct();
        log.debug("l_blnFlag = "  + l_blnFlag);
        // 8.メッセージ (*) isオプション銘柄() == trueの場合処理実施
        if (l_blnFlag)
        {

            if (FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE.equals(
                l_finTransactionType) ||
                FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_OPEN.equals(
                l_finTransactionType) ||
                FinTransactionType.EQTYPE_IDX_FUTURES_BUY_TO_CLOSE.equals(
                l_finTransactionType) ||
                FinTransactionType.EQTYPE_IDX_FUTURES_BUY_TO_OPEN.equals(
                l_finTransactionType))
            {
                l_ifoFinTransactionParams.setNetAmount(
                    new BigDecimal(l_dblNetAmountKY + "").negate().doubleValue());
            }
            else
            {
                // 9.受渡代金をセットする
                l_ifoFinTransactionParams.setNetAmount(l_dblNetAmountKY);
            }

        }
        else
        {
            // 先物の場合は初期値として0をセットする
            l_ifoFinTransactionParams.setNetAmount(0);
        }


        // 10.トランザクションカテゴリを取得する。
        FinTransactionCateg l_finTransactionCateg =
            l_ifoFinTransactionParams.getFinTransactionCateg();

        // 返済取引の場合,評価損益をセットする
        if (FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE .equals(l_finTransactionCateg) ||
            FinTransactionCateg.EQTYPE_IDX_OPTIONS_CLOSE .equals(l_finTransactionCateg))
        {
            // 11.建玉金額を取得する。
            double l_dblImportedPaidValue =
                l_ifoFinTransactionParams.getImportedPaidValue();
            log.debug("建玉金額 = " + l_dblImportedPaidValue);

            // 12.建委託手数料を取得する。
            double l_dblImportedSetupFee =
                l_ifoFinTransactionParams.getImportedSetupFee();
            log.debug("建委託手数料 = " + l_dblImportedSetupFee);

            // 13.建委託手数料消費税を取得する。
            double l_dblImportedSetupFeeTax =
                l_ifoFinTransactionParams.getImportedSetupFeeTax();
            log.debug("建委託手数料消費税 = " + l_dblImportedSetupFeeTax);

            SideEnum l_sideEnumForContract = null;
            log.debug("l_finTransactionType = " + l_finTransactionType);
            //取引トランザクション.getFinTransactionType()==”買建返済取引（売返済）”の場合、”買”（SideEnum.BUY）
            if(FinTransactionType.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE.equals(l_finTransactionType)
                    || FinTransactionType.EQTYPE_IDX_FUTURES_SELL_TO_CLOSE.equals(l_finTransactionType))
            {

                l_sideEnumForContract = SideEnum.BUY;
            }
            //取引トランザクション.getFinTransactionType()==”売建返済取引（買返済）”の場合、”売”（SideEnum.SELL）
            else if (FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE.equals(l_finTransactionType)
                || FinTransactionType.EQTYPE_IDX_FUTURES_BUY_TO_CLOSE.equals(l_finTransactionType))
            {

                l_sideEnumForContract = SideEnum.SELL;
            }
            log.debug("l_sideEnumForContract = " + l_sideEnumForContract);
            // 14.受渡代金（建）を計算する。
            double l_dblNetAmountKenn = l_web3IfoBizLogicProvider.calcDeliveryAmount(
                l_sideEnumForContract,
                l_dblImportedPaidValue,
                l_dblImportedSetupFee,
                l_dblImportedSetupFeeTax);

            log.debug("l_dblNetAmountKenn = " + l_dblNetAmountKenn);

            // 15.評価損益をセットする。

            // 売返済の場合（getFinTransactionType()==”買建売返済”）：
            //   set 今回約定の受渡代金－受渡代金（建）
            BigDecimal l_bdNetAmountKY = new BigDecimal("" + l_dblNetAmountKY);
            BigDecimal l_bdNetAmountKenn = new BigDecimal("" + l_dblNetAmountKenn);
            if(FinTransactionType.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE.equals(l_finTransactionType)
                || FinTransactionType.EQTYPE_IDX_FUTURES_SELL_TO_CLOSE.equals(l_finTransactionType))
            {
                log.debug("売返済の場合 回約定の受渡代金－受渡代金（建）");
                l_ifoFinTransactionParams.setCapitalGain(
                        l_bdNetAmountKY.subtract(l_bdNetAmountKenn).doubleValue());

            }

            // 買返済の場合（getFinTransactionType()==”売建買返済”）：
            //   受渡代金（建）－今回約定の受渡代金
            if (FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE.equals(l_finTransactionType)
                || FinTransactionType.EQTYPE_IDX_FUTURES_BUY_TO_CLOSE.equals(l_finTransactionType))
            {
                log.debug("買返済の場合 受渡代金（建）－今回約定の受渡代金 ");
                l_ifoFinTransactionParams.setCapitalGain(
                        l_bdNetAmountKenn.subtract(l_bdNetAmountKY).doubleValue());
            }
            log.debug("l_ifoFinTransactionParams.getCapitalGain() = " + l_ifoFinTransactionParams.getCapitalGain());

            if(!l_blnFlag)
            {
                // 先物銘柄の場合のみ実施
                // 16.受渡代金をセットする。
                log.debug("先物（isオプション銘柄() == false）の場合");
                l_ifoFinTransactionParams.setNetAmount(l_ifoFinTransactionParams.getCapitalGain());
            }
        }

        log.exiting(l_strMethodName);

    }

    /**
     * (update新規建建玉Fromトランザクション)<BR>
     * <BR>
     * （updateContractOpenFromMarketOrderedTransのオーバーライド）<BR>
     * <BR>
     * トランザクションデータを元に、建玉行の建情報を更新する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP残高）update新規建建玉Fromトランザクション」参照。<BR>
     * <BR>
     * @@param l_ifoContractParams - 建玉行オブジェクト<BR>
     *
     * @@param l_ifoFinTransactionParams - (トランザクション（取引勘定明細）Params)<BR>
     * <BR>
     * トランザクション（取引勘定明細）行オブジェクト<BR>
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataException;
     * @@roseuid 4075216D03A9
     */
    protected void updateContractOpenFromMarketOrderedTrans(
        IfoContractParams l_ifoContractParams,
        IfoFinTransactionParams l_ifoFinTransactionParams)
        throws RuntimeSystemException, DataException
    {
        final String l_strMethodName =
            "updateContractOpenFromMarketOrderedTrans";
        log.entering(l_strMethodName);

        //getOpenDateIsSet( )
        // --- 建玉Paramsに建日がセットされているかチェックする。
        boolean l_blnOpenDateIsSet = l_ifoContractParams.getOpenDateIsSet();

        //建日がセットされてない場合(建玉の新規INSERT時)
        // 建玉オブジェクト値をセット
        if (!l_blnOpenDateIsSet)
        {

            //setAccountId(arg0 : long)
            l_ifoContractParams.setAccountId(
                l_ifoFinTransactionParams.getAccountId());

            //setSubAccountId(arg0 : long)
            l_ifoContractParams.setSubAccountId(
                l_ifoFinTransactionParams.getSubAccountId());

            //setContractType(arg0 : ContractTypeEnum)
            l_ifoContractParams.setContractType(
                ContractTypeEnum.getContractType(
                    l_ifoFinTransactionParams.getFinTransactionType()));

            //setMarketId(arg0 : long)
            l_ifoContractParams.setMarketId(
                l_ifoFinTransactionParams.getMarketId());

            //setProductId(arg0 : long)
            l_ifoContractParams.setProductId(
                l_ifoFinTransactionParams.getProductId());

            //setProductType(arg0 : ProductTypeEnum)
            l_ifoContractParams.setProductType(
                l_ifoFinTransactionParams.getProductType());

            //setContractPrice(arg0 : double)
            l_ifoContractParams.setContractPrice(
                l_ifoFinTransactionParams.getPrice());

            //setOriginalContractPrice(arg0 : double)
            l_ifoContractParams.setOriginalContractPrice(
                l_ifoFinTransactionParams.getPrice());

            //setOpenDate(arg0 : 論理ビュー::java::sql::Timestamp)
            // 建日をセットする。
            IfoOrderExecutionImpl l_ifoOrderExecution =
                new IfoOrderExecutionImpl(
                    l_ifoFinTransactionParams.getOrderExecutionId());
            l_ifoContractParams.setOpenDate(
                WEB3DateUtility.toDay(l_ifoOrderExecution.getExecutionTimestamp()));

            //setCloseDate(arg0 : Date)
            Date l_closeDate = IfoTradedProductDao.findRowByProductIdMarketId(
                l_ifoContractParams.product_id,
                l_ifoContractParams.market_id).getLastTradingDate();
            l_ifoContractParams.setCloseDate(l_closeDate);

            //setUnitSize(arg0 : double)
            // 1単位当り乗数をセットする。
            double l_dblUnitSize =
                IfoTradedProductDao.findRowByProductIdMarketId(
                    l_ifoContractParams.product_id,
                    l_ifoContractParams.market_id).getUnitSize();
            l_ifoContractParams.setUnitSize(l_dblUnitSize);

            //setDeliveryDate(arg0 : Date)  受渡日
            l_ifoContractParams.setDeliveryDate(l_ifoFinTransactionParams.getDeliveryDate());

            // getOrderUnit()
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_orderManagerImpl =
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = null;
            try
            {
                l_ifoOrderUnit = (IfoOrderUnit)l_orderManagerImpl.getOrderUnit(
                    l_ifoFinTransactionParams.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("Error In Method: " + l_strMethodName, l_nfe);
                log.exiting(l_strMethodName);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + l_strMethodName,
                    l_nfe.getMessage(),
                    l_nfe);
            }

            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

            //setSessionType() 立会区分
            l_ifoContractParams.setSessionType(l_ifoOrderUnitRow.getSessionType());
        }
        //setOriginalQuantity(arg0 : double)
        l_ifoContractParams.setOriginalQuantity(
            l_ifoContractParams.getOriginalQuantity() + l_ifoFinTransactionParams.getQuantity());

        //setQuantity(arg0 : double)
        l_ifoContractParams.setQuantity(
            l_ifoContractParams.getQuantity() + l_ifoFinTransactionParams.getQuantity());

		//setSetupFee(arg0 : double)
		l_ifoContractParams.setSetupFee(
			l_ifoContractParams.getSetupFee() +
			l_ifoFinTransactionParams.getCommissionFee()
			);

		//setSetupFeeTax(arg0 : double)
		l_ifoContractParams.setSetupFeeTax(
			l_ifoContractParams.getSetupFeeTax() +
			l_ifoFinTransactionParams.getCommissionFeeTax()
			);

        //setLastUpdatedTimestamp()
        l_ifoContractParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());

		try
		{
			//getOrderId() --- 注文ＩＤを取得する
			long l_lngOrderId = l_ifoFinTransactionParams.getOrderId();

			//get建玉ＩＤByOrder(long) --- 注文に該当する建玉ＩＤを配列で取得する。

			WEB3IfoPersistentDataManager l_persistentDataManager =	
				new WEB3IfoPersistentDataManager();
			long[] l_lngReturns =
				l_persistentDataManager.getContractIDByOrder(l_lngOrderId);
		
			int l_intSize =l_lngReturns.length;
			long[] l_lngContractIDs;
			l_lngContractIDs =
				new long[l_intSize];
			for (int i = 0; i < l_intSize; i++)
			{
				l_lngContractIDs[i] =l_lngReturns[i];
			}
			
			//建玉Params.建玉ID≠0の場合、
			//取得したIDの配列に引数の建玉Params.建玉IDを追加する。
			if(l_ifoContractParams.getContractId() != 0)
			{
				l_intSize++;
				l_lngContractIDs = new long[l_intSize];
				for (int i = 0; i < l_intSize; i++)
				{
					if (i == l_intSize-1)
					{
						l_lngContractIDs[i] =
							l_ifoContractParams.getContractId();
					}
					else
					{
						l_lngContractIDs[i] = l_lngReturns[i];
					}
				}
			}



            //建玉ＩＤ配列の要素分LOOP
            for (int i = 0; i < l_intSize; i++)
            {
                //（建玉Params.建玉ＩＤ != 建玉ＩＤ[index]）の場合のみ実施
                if (l_ifoContractParams.getContractId() != l_lngContractIDs[i])
                {
                    WEB3IfoContractImpl l_ifocontractImpl = null;
                    l_ifocontractImpl = new WEB3IfoContractImpl(l_lngContractIDs[i]);
                    //getDataSourceObject() --- 建玉行オブジェクトを取得する。
					IfoContractParams l_updateContact = new IfoContractParams(
                            (IfoContractRow)l_ifocontractImpl.getDataSourceObject());
					//set建玉金額(建玉Params,トランザクション（取引勘定明細）Params)
                    setContractAmount(l_updateContact, null);

                    l_persistentDataManager.updateContractByTrans(l_updateContact);
                }
				//（建玉Params.建玉ＩＤ == 建玉ＩＤ[index]）の場合のみ実施
                if (l_ifoContractParams.getContractId() == l_lngContractIDs[i])
                {
					//set建玉金額(建玉Params,トランザクション（取引勘定明細）Params)
					setContractAmount(l_ifoContractParams, l_ifoFinTransactionParams);
                }
                
             }
        }
        catch (WEB3BaseException l_webex)
        {
            log.error(l_strMethodName,l_webex);
            throw new WEB3BaseRuntimeException(l_webex.getErrorInfo(),l_strMethodName);
        }

        log.exiting(l_strMethodName);
    }

	/**
	 * (updateトランザクション)<BR>
	 * <BR>
	 * 手数料按分計算（一口約定）を実施し、トランザクションデータを更新する。<BR>
	 * <BR>
	 * シーケンス図<BR>
	 * 「（先物OP残高）updateトランザクション」参照。<BR>
	 * @@param l_lngOrderUnitID - 注文単位ＩＤ<BR>
	 * @@throws webbroker3.common.WEB3BaseException
	 * @@roseuid 40999828002D
	 */
	public void updateTransaction(long l_lngOrderUnitID)
		throws WEB3BaseException
	{
		final String l_strMethodName = "updateTransaction";
		log.entering(l_strMethodName);

		WEB3IfoPersistentDataManager l_persistentDataManager =
			new WEB3IfoPersistentDataManager();

		//		タイムスタンプに設定されている受付日時を取得する 
		//　@		 ?ThreadLocalSystemAttributesRegistry.getAttribute( ) 
		//		  設定キー： 取引時間管理.TIMESTAMP_TAG 

		Timestamp l_timestamp =
			(Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

		//		実時間用タイムスタンプから実時間を取得する 
		//　@		 ?ThreadLocalSystemAttributesRegistry.getAttribute( ) 
		//		  設定キー： 設定キー定数定義インタフェイス.REAL_TIMESTAMP 

		Timestamp l_realTimestamp =
			(Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3IfoAttributeNameDef.REAL_TIMESTAMP);
		//		実時間が設定されている場合(REAL_TIMESTAMP != null) 
		if (l_realTimestamp != null)
		{
			//			受付日時のリセット　@ 
			//　@　@			  ?ThreadLocalSystemAttributesRegistry.setAttribute( )にて受付日時に 
			//　@　@　@			   取得した実時間をセットする。 
			//　@			   設定キー： 取引時間管理.TIMESTAMP_TAG 

			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
				l_realTimestamp);
		}

		try
		{
			// get取引勘定明細ForOrderUnit(long)
			//  --- 同一注文に関連する、既に約定済の取引勘定明細をリストで取得する。
			List l_lisRet =
				l_persistentDataManager.getFinTransactionDetailForOrderUnit(
					l_lngOrderUnitID);

			FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
			TradingModule l_tradingModule =
				l_finApp.getTradingModule(ProductTypeEnum.IFO);
			WEB3IfoBizLogicProvider l_ifoBizLogicProvider =
				(WEB3IfoBizLogicProvider) l_tradingModule.getBizLogicProvider();

			// getOrderUnit(long)
			// --- 注文単位オブジェクトを取得する。
			WEB3OptionOrderManagerImpl l_orderManagerImpl =
				(WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
			OrderUnit l_orderUnit =
				l_orderManagerImpl.getOrderUnit(l_lngOrderUnitID);

			// getOrderCateg()
			// --- 注文カテゴリを取得する。
			OrderCategEnum l_orderCategEnum = l_orderUnit.getOrderCateg();

			if (l_lisRet != null && !l_lisRet.isEmpty())
			{
				// toArray() --- 配列に変換する。
				IfoFinTransactionParams[] l_ifoFinTransactionParams =
					new IfoFinTransactionParams[l_lisRet.size()];
				l_lisRet.toArray(l_ifoFinTransactionParams);

				//注文単位.注文カテゴリ == ”先物返済注文”の場合、実施
				if (OrderCategEnum
					.IDX_FUTURES_CLOSE
					.equals(l_orderUnit.getOrderCateg()))
				{
					IfoContractParams l_contractParams =
						this.getContract(l_ifoFinTransactionParams[0]);
					IfoOrderExecution l_ifoOrderExecution =
						(
							IfoOrderExecution) l_orderManagerImpl
								.getOrderExecution(
							l_ifoFinTransactionParams[0].getOrderExecutionId());
					//建玉の建日 == 注文の約定日 の場合（日計り返済の場合）
					if (l_contractParams != null
						&& WEB3DateUtility.compareToDay(
							l_contractParams.getOpenDate(),
							l_ifoOrderExecution.getExecutionTimestamp())
							== 0)
					{
						WEB3GentradeAccountManager l_accountManager =
							(WEB3GentradeAccountManager) l_finApp
								.getAccountManager();
						SubAccount l_subAccount =
							l_accountManager.getSubAccount(
								l_orderUnit.getAccountId(),
								l_orderUnit.getSubAccountId());
						Institution l_institution =
							l_subAccount.getInstitution();
						InstitutionRow l_institutionRow =
							(InstitutionRow) l_institution
								.getDataSourceObject();

					}
				}

				// 手数料の按分を行う
				ConsolidatedCommissionInfo l_consolidatedCommissionInfo = null;
				l_consolidatedCommissionInfo =
					l_ifoBizLogicProvider.calcCommission(
						l_ifoFinTransactionParams);

				// 取引勘定明細Paramsリストの要素分LOOP
				for (int i = 0; i < l_ifoFinTransactionParams.length; i++)
				{
					// 按分後の計算結果を取引勘定明細Paramsにセットする。
                    // calc手数料（按分）()メソッドの戻り値より、
                    //   指定要素番号の委託手数料を取得する。
                    double l_dblCommission =
                        l_consolidatedCommissionInfo.getCommission(i);

                    // 委託手数料をトランザクション（取引勘定明細）にセットする。
                    l_ifoFinTransactionParams[i].setCommissionFee(
                        l_dblCommission);

                    // calc手数料（按分）()メソッドの戻り値より、
                    //    指定要素番号の委託手数料消費税を取得する。
                    double l_dblSalesTax =
                        l_consolidatedCommissionInfo.getSalesTax(i);

                    // 委託手数料消費税をトランザクション（取引勘定明細）にセットする
                    l_ifoFinTransactionParams[i].setCommissionFeeTax(
                        l_dblSalesTax);

					// set金額(IfoFinTransactionParams)
					setMarginNetAmount(l_ifoFinTransactionParams[i]);

					Map l_mapFinTransaction = new Hashtable();
					l_mapFinTransaction.put(
						"capital_gain",
						new Double(
							l_ifoFinTransactionParams[i].getCapitalGain()));
					l_mapFinTransaction.put(
						"net_amount",
						new Double(
							l_ifoFinTransactionParams[i].getNetAmount()));
					l_mapFinTransaction.put(
						"commission_fee",
						new Double(
							l_ifoFinTransactionParams[i].getCommissionFee()));
					l_mapFinTransaction.put(
						"commission_fee_tax",
						new Double(
							l_ifoFinTransactionParams[i]
								.getCommissionFeeTax()));
					l_mapFinTransaction.put(
						"last_updated_timestamp",
						GtlUtils.getSystemTimestamp());

					// updateFinTransaction(IfoFinTransactionParams)
					// --- トランザクション（取引勘定明細）行オブジェクトをDBに更新する。
					l_persistentDataManager.updateFinTransaction(
						l_ifoFinTransactionParams[i],
						l_mapFinTransaction);

					// notifyUpdateGtl(IfoFinTransactionParams)
					// --- トランザクション（取引勘定明細）行を元に、
					// --- トランザクション（顧客勘定明細）を更新する。
					notifyUpdateGtl(l_ifoFinTransactionParams[i]);
				}
			}

			if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderCategEnum)
				|| (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderCategEnum)))
			{

				// 注文ＩＤを取得する。
				long l_lngOrderId = l_orderUnit.getOrderId();

				// get建玉ＩＤByOrder(long)
				// --- 注文に該当する建玉ＩＤを配列で取得する
				long[] l_lngContractIDs =
					l_persistentDataManager.getContractIDByOrder(l_lngOrderId);

				// 建玉ＩＤ配列の要素分LOOP
				for (int j = 0; j < l_lngContractIDs.length; j++)
				{
					WEB3IfoContractImpl l_ifoContractImpl = null;
					l_ifoContractImpl =
						new WEB3IfoContractImpl(l_lngContractIDs[j]);

					//建玉行オブジェクトを取得する。
					IfoContractParams l_ifoContractParams =
						new IfoContractParams(
							(IfoContractRow) l_ifoContractImpl
								.getDataSourceObject());

					// set建玉金額(IfoContractParams)
					setContractAmount(l_ifoContractParams, null);

					// updateContractByTrans(IfoContractParams)
					// --- 建玉を更新する。
					l_persistentDataManager.updateContractByTrans(
						l_ifoContractParams);
				}
			}
		}
		catch (DataException l_ex)
		{
			log.error("Error In Method: " + l_strMethodName, l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + l_strMethodName,
				l_ex.getMessage(),
				l_ex);

		}
		catch (NotFoundException l_ex)
		{
			log.error("Error In Method: " + l_strMethodName, l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + l_strMethodName,
				l_ex.getMessage(),
				l_ex);

		}

		//		実時間が設定されている場合(REAL_TIMESTAMP != null) 
		if (l_realTimestamp != null)
		{
			//			受付日時の再リセット 
			//　@　@			  ?ThreadLocalSystemAttributesRegistry.setAttribute( )にて受付日時に 
			//　@　@　@　@			で取得したタイムスタンプの設定値をセットする。 
			//　@			   設定キー： 取引時間管理.TIMESTAMP_TAG 
			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
				l_timestamp);
		}
		log.exiting(l_strMethodName);
	}

    /**
     * (set建玉金額)<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OP残高）set建玉金額」参照。<BR>
     * @@param l_ifoContractParams - 建玉Params<BR>
	 * @@param l_ifoFinTransactionParams - トランザクション（取引勘定明細）行オブジェクト<BR>
     * @@throws webbroker3.common.WEB3BaseException<BR>
     * @@roseuid 40999ECF03E6
     */
	protected void setContractAmount(
		IfoContractParams l_ifoContractParams,
		IfoFinTransactionParams l_ifoTransParams)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "setContractAmount()";
		log.entering(STR_METHOD_NAME);

		try
		{
			//新規建の取引勘定明細オブジェクトのリストを取得する。 
			WEB3IfoPersistentDataManager l_ifoPersistentDatamanager =
				new WEB3IfoPersistentDataManager();
			List l_lisOpenFinTransaction =
				l_ifoPersistentDatamanager.getFinTransactionDetailForOpenCont(
					l_ifoContractParams.getContractId());

			IfoFinTransactionParams[] l_ifoOpenFinTransactionParams =
				new IfoFinTransactionParams[l_lisOpenFinTransaction.size()];
			l_lisOpenFinTransaction.toArray(l_ifoOpenFinTransactionParams);

			// 委託手数料
			double l_dblSetupFee = 0.0D;
            BigDecimal l_bdSetupFee = new BigDecimal("0");
			// 委託手数料消費税
			double l_dblSetupFeeTax = 0.0D;
            BigDecimal l_bdSetupFeeTax = new BigDecimal("0");

			//引数のトランザクションParamsがnullでない場合に加算。		
			if (l_ifoTransParams != null)
			{
				// 委託手数料
				l_dblSetupFee = l_ifoTransParams.getCommissionFee();
                l_bdSetupFee = new BigDecimal(l_dblSetupFee + "");
				// 委託手数料消費税
				l_dblSetupFeeTax = l_ifoTransParams.getCommissionFeeTax();
                l_bdSetupFeeTax = new BigDecimal(l_dblSetupFeeTax + "");
			}

			for (int i = 0; i < l_ifoOpenFinTransactionParams.length; i++)
			{
                BigDecimal l_bdCommissionFee =
                    new BigDecimal(l_ifoOpenFinTransactionParams[i].getCommissionFee() + "");
                l_bdSetupFee = l_bdSetupFee.add(l_bdCommissionFee);
                l_dblSetupFee = l_bdSetupFee.doubleValue();

                BigDecimal l_bdCommissionFeeTax =
                    new BigDecimal(l_ifoOpenFinTransactionParams[i].getCommissionFeeTax() + "");
                l_bdSetupFeeTax = l_bdSetupFeeTax.add(l_bdCommissionFeeTax);
                l_dblSetupFeeTax = l_bdSetupFeeTax.doubleValue();
			}
			
			//引数の建玉Paramsに該当建玉の新規建時の委託手数料をセットする。
			l_ifoContractParams.setSetupFee(l_dblSetupFee);

			//引数の建玉Paramsに該当建玉の新規建時の委託手数料消費税をセットする。
			l_ifoContractParams.setSetupFeeTax(l_dblSetupFeeTax);

			//返済の取引勘定明細オブジェクトのリストを取得する。 
			List l_lisCloseFinTransaction =
				l_ifoPersistentDatamanager.getFinTransactionDetailForCloseCont(
					l_ifoContractParams.getContractId());

			IfoFinTransactionParams[] l_ifoCloseFinTransactionParams =
				new IfoFinTransactionParams[l_lisCloseFinTransaction.size()];
			l_lisCloseFinTransaction.toArray(l_ifoCloseFinTransactionParams);

			// 返済時の建委託手数料
			double l_dblCloseSetupFee = 0.0D;
            BigDecimal l_bdCloseSetupFee = new BigDecimal("0");
			// 返済時の建委託手数料消費税
			double l_dblCloseSetupFeeTax = 0.0D;
            BigDecimal l_bdCloseSetupFeeTax = new BigDecimal("0");
			// 返済済み数量を初期化しておく。
			double l_dblSettleQuantity = 0.0D;
            BigDecimal l_bdSettleQuantity = new BigDecimal("0");

            //建玉Params.建玉元数量
            BigDecimal l_bdOriginalQuantity =
                new BigDecimal(l_ifoContractParams.getOriginalQuantity() + "");
            //建玉Params.建委託手数料
            BigDecimal l_bdContractSetupFee = new BigDecimal("0");
            //建玉Params.建委託手数料消費税
            BigDecimal l_bdContractSetupFeeTax = new BigDecimal("0");

			//(*)取得した返済の取引勘定明細毎のLOOP処理
			for (int i = 0; i < l_ifoCloseFinTransactionParams.length; i++)
			{
                //返済の取引勘定明細Params.約定数量
                BigDecimal l_bdQuantity =
                    new BigDecimal(l_ifoCloseFinTransactionParams[i].getQuantity() + "");

                //取引勘定明細Params.建委託手数料 =
                // (建玉Params.建委託手数料 * 返済の取引勘定明細Params.約定数量) / (建玉Params.建玉元数量 - 決済済み数量)(#2)
                //(#2)小数点以下は切り捨て。
                l_bdContractSetupFee =
                    new BigDecimal(l_ifoContractParams.getSetupFee() + "");
                l_bdCloseSetupFee =
                    l_bdContractSetupFee.multiply(l_bdQuantity).divide(
                        l_bdOriginalQuantity.subtract(l_bdSettleQuantity),
                        0,
                        BigDecimal.ROUND_FLOOR);

                l_dblCloseSetupFee = l_bdCloseSetupFee.doubleValue();

				l_ifoCloseFinTransactionParams[i].setImportedSetupFee(l_dblCloseSetupFee);

				//取引勘定明細Params.建委託手数料消費税 =
				//　@(建玉Params.建委託手数料消費税 * 返済の取引勘定明細Params.約定数量) / (建玉Params.建玉元数量 - 決済済み数量)(#2)
                //(#2)小数点以下は切り捨て。
                l_bdContractSetupFeeTax =
                    new BigDecimal(l_ifoContractParams.getSetupFeeTax() + "");
                l_bdCloseSetupFeeTax =
                    l_bdContractSetupFeeTax.multiply(l_bdQuantity).divide(
                        l_bdOriginalQuantity.subtract(l_bdSettleQuantity),
                        0,
                        BigDecimal.ROUND_FLOOR);

                l_dblCloseSetupFeeTax = l_bdCloseSetupFeeTax.doubleValue();

				l_ifoCloseFinTransactionParams[i].setImportedSetupFeeTax(l_dblCloseSetupFeeTax);

				//取引勘定明細Params.更新日付=現在時刻
				l_ifoCloseFinTransactionParams[i].setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
				
				//受渡代金、評価損益（決済損益）の再計算を行う。
				setMarginNetAmount(l_ifoCloseFinTransactionParams[i]);
				
				//取引勘定明細を更新する。
				Map l_mapFinTransaction = new Hashtable();
				l_mapFinTransaction.put("imported_setup_fee",Double.toString(l_dblCloseSetupFee));
				l_mapFinTransaction.put("imported_setup_fee_tax",Double.toString(l_dblCloseSetupFeeTax));
				l_mapFinTransaction.put("capital_gain",Double.toString(l_ifoCloseFinTransactionParams[i].getCapitalGain()));
				l_mapFinTransaction.put("net_amount",Double.toString(l_ifoCloseFinTransactionParams[i].getNetAmount()));
				l_mapFinTransaction.put("last_updated_timestamp",l_ifoCloseFinTransactionParams[i].getLastUpdatedTimestamp());
				l_ifoPersistentDatamanager.updateFinTransaction(
					l_ifoCloseFinTransactionParams[i],l_mapFinTransaction);

				//トランザクション（取引勘定明細）行を元に、トランザクション（顧客勘定明細）を更新する。
				notifyUpdateGtl(l_ifoCloseFinTransactionParams[i]);

				//引数の建玉Paramsに
				//再按分した建委託手数料をセットする。
                BigDecimal l_bdImportedSetupFee =
                    new BigDecimal(l_ifoCloseFinTransactionParams[i].getImportedSetupFee() + "");
				l_ifoContractParams.setSetupFee(
                    l_bdContractSetupFee.subtract(l_bdImportedSetupFee).doubleValue());

				//引数の建玉Paramsに
				//再按分した建委託手数料消費税をセットする。
                BigDecimal l_bdImportedSetupFeeTax =
                    new BigDecimal(l_ifoCloseFinTransactionParams[i].getImportedSetupFeeTax() + "");
				l_ifoContractParams.setSetupFeeTax(
                    l_bdContractSetupFeeTax.subtract(l_bdImportedSetupFeeTax).doubleValue());

				//引数の建玉Params.更新日付に現在時刻をセットする。
				l_ifoContractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

				//決済済み数量=+取引勘定明細Params.約定数量			
				l_dblSettleQuantity+=l_ifoCloseFinTransactionParams[i].getQuantity();
                l_bdSettleQuantity = new BigDecimal(l_dblSettleQuantity + "");
			}
		}
		catch (DataException l_ex)
		{
			log.error("Error In Method: " + STR_METHOD_NAME, l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);

		}

		log.exiting(STR_METHOD_NAME);
	}

    /**
     * 取引勘定の更新を顧客勘定に通知する。<BR>
     * <BR>
     * １）　@トランザクションＩＤ取得<BR>
     * 取引勘定明細Params.getFinTransactionId()にてトランザクションＩＤを取得する。<BR>
     * <BR>
     * ２）　@顧客勘定明細取得<BR>
     * 取得したトランザクションＩＤに該当するトランザクション（顧客勘定明細）Paramsを取得する。<BR>
     * <BR>
     * ３）　@受渡代金更新<BR>
     * 顧客勘定明細行に以下の通り値をセットし、DBに更新する。<BR>
     * <BR>
     * トランザクション（顧客勘定明細）Params.受渡代金 = 取引勘定明細Params.受渡代金<BR>
     * トランザクション（顧客勘定明細）Params.更新日付 = 取引勘定明細Params.更新日時<BR>
     * <BR>
     * @@param l_ifoFinTransactionParams - (取引勘定明細Params)<BR>
     * <BR>
     * トランザクション（取引勘定明細）行オブジェクト<BR>
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
     * @@roseuid 4099AC6D003C
     */
	protected void notifyUpdateGtl(IfoFinTransactionParams l_ifoFinTransactionParams)
		throws RuntimeSystemException, DataFindException,
			DataNetworkException, DataQueryException, WEB3BaseException
	{
		final String STR_METHOD_NAME = "notifyUpdateGtl";
		log.entering(STR_METHOD_NAME);

		Timestamp l_realTimestamp = 
			(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3IfoAttributeNameDef.REAL_TIMESTAMP
				);

		// １）トランザクションＩＤ取得
		long l_lngTransactionId =
			l_ifoFinTransactionParams.getFinTransactionId();
		log.debug("トランザクションＩＤ取得 = " + l_lngTransactionId);

		// ２）顧客勘定明細取得
		GenFinTransactionParams l_genFinTransactionParams =
			new GenFinTransactionParams(GenFinTransactionDao.findRowByTransactionId(l_lngTransactionId));

		if (l_genFinTransactionParams == null)
		{
			log.error("Error in Method: " + STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + STR_METHOD_NAME);
		}

		// ３）受渡代金更新

		//トランザクション（顧客勘定明細）Params.受渡代金 = 取引勘定明細Params.受渡代金
		log.debug("トランザクション（顧客勘定明細）Params.受渡代金 = 取引勘定明細Params.受渡代金");
		double l_dblNetAmount = l_ifoFinTransactionParams.getNetAmount();
		l_genFinTransactionParams.setNetAmount(l_dblNetAmount);
		log.debug("l_dblNetAmount = " + l_dblNetAmount);

		//トランザクション（顧客勘定明細）Params.更新日付 = 取引勘定明細Params.更新日時
		log.debug("トランザクション（顧客勘定明細）Params.更新日付 = 取引勘定明細Params.更新日時");
		if (l_realTimestamp == null)
		{
			l_realTimestamp =
			l_ifoFinTransactionParams.getLastUpdatedTimestamp();
		}    
		l_genFinTransactionParams.setLastUpdatedTimestamp(l_realTimestamp);
		log.debug("l_realTimestamp = " + l_realTimestamp);

		QueryProcessor processor = Processors.getDefaultProcessor();
		processor.doUpdateQuery(l_genFinTransactionParams);

		log.exiting(STR_METHOD_NAME);
	}



    /**
     * (getContractのオーバーライド)<BR>
     * <BR>
     * １）　@トランザクション（取引勘定明細）Paramsが持つ約定IDから、約定日時を取得し、<BR>
     * 約定日とする。<BR>
     * <BR>
     * 約定日時： IfoOrderExecutionImpl(<BR>
     *      トランザクション（取引勘定明細）Params<BR>
     *          .getOrderExecutionId()).getExecutionTimestamp() <BR>
     * <BR>
     * ２）　@先物OP更新データマネージャ.get建玉(口座ID, 補助口座ID, 市場ID, 銘柄ID, <BR>
     * 建区分, 建単価, 建日, 期日, 受渡日)に委譲する。<BR>
     * <BR>
     * [引数] <BR>
     * 口座ID： トランザクション（取引勘定明細）Params.getAccountId() <BR>
     * 補助口座ID： トランザクション（取引勘定明細）Params.getSubAccountId() <BR>
     * 銘柄ID： トランザクション（取引勘定明細）Params.getProductId() <BR>
     * 市場ID： トランザクション（取引勘定明細）Params.getMarketId() <BR>
     * 建区分： ContractTypeEnum.getContractType( <BR>
     *      トランザクション（取引勘定明細）Params.getFinTransactionType()) <BR>
     * 建単価： トランザクション（取引勘定明細）Params.getPrice() <BR>
     * 建日： (１）で取得した約定日) <BR>
     * 期日： 先物OP取引銘柄.売買最終日 <BR>
     * 受渡日： トランザクション（取引勘定明細）Params.getDeliveryDate()<BR>
     * <BR>
     * ３）　@委譲メソッドの戻り値オブジェクトをそのまま返却する。<BR>
     * <BR>
     * @@param l_ifoFinTransactionParams
     *      - トランザクション（取引勘定明細）Params<BR>
     * @@return IfoContractParams
     * @@throws DataException
     * @@roseuid
     */
    protected IfoContractParams getContract(
        IfoFinTransactionParams l_ifoFinTransactionParams)
        throws DataException
    {
        final String STR_METHOD_NAME = "getContract";
        log.entering(STR_METHOD_NAME);

        // 建玉Params
        IfoContractParams l_ifoContractParams = new IfoContractParams();

        // １）建玉の約定日を取得
        IfoOrderExecutionImpl l_ifoOrderExecution =
            new IfoOrderExecutionImpl(
                l_ifoFinTransactionParams.getOrderExecutionId());
        Date l_executionTimestamp = WEB3DateUtility.toDay(l_ifoOrderExecution.getExecutionTimestamp());

		//取引銘柄の売買最終日を取得
		Date l_closeDate = IfoTradedProductDao.findRowByProductIdMarketId(
		l_ifoFinTransactionParams.product_id,
		l_ifoFinTransactionParams.getMarketId()).getLastTradingDate();

        WEB3IfoPersistentDataManager l_ifoPersistentDatamanager =
            new WEB3IfoPersistentDataManager();

        // ２）getPersistenceManager().getContract()をコールする。
        l_ifoContractParams = l_ifoPersistentDatamanager.getContract(
        	//  --- 口座ID
        	l_ifoFinTransactionParams.getAccountId(),
        	// --- 補助口座ID
        	l_ifoFinTransactionParams.getSubAccountId(),
            // --- 市場ID
            l_ifoFinTransactionParams.getMarketId(),
        	// --- 銘柄ID
        	l_ifoFinTransactionParams.getProductId(),
        	// --- 建区分
        	ContractTypeEnum.getContractType(
       		l_ifoFinTransactionParams.getFinTransactionType()),
        	// --- 建単価
        	l_ifoFinTransactionParams.getPrice(),
        	// --- 建日
        	l_executionTimestamp,
        	// --- 期日
			l_closeDate,
            // --- 受渡日
            l_ifoFinTransactionParams.getDeliveryDate());

        return l_ifoContractParams;

    }



    /**
      *（processCloseMarginOrderExecutionのオーバーライド）<BR>
      * <BR>
      *１）　@トランザクション情報作成<BR>
      *　@set約定情報Toトランザクション()をコールしてトランザクション情報（：取引勘定明細Params）を作成する。<BR>
      *　@作成したトランザクション情報（：取引勘定明細Params）に、引数の建玉ＩＤをセットする。<BR>
      * <BR>
      *　@[set約定情報Toトランザクション()に指定する引数]<BR>
      *　@取引勘定明細Params：　@new IfoFinTransactionParams()<BR>
      *　@約定：　@約定<BR>
      *　@注文単位Params：　@約定.getOrderUnitId()に該当する注文単位行<BR>
      *　@建玉ＩＤ：　@建玉ＩＤ<BR>
      * <BR>
      *２）　@建玉情報セット<BR>
      *　@updateContractCloseFromMarketOrderedTrans()をコールし、建玉情報をセットする。<BR>
      * <BR>
      *　@[updateContractCloseFromMarketOrderedTrans()に指定する引数]<BR>
      *　@建玉Params：　@建玉ＩＤに該当する建玉Params<BR>
      *　@取引勘定明細Params：　@取引勘定明細Params<BR>
      * <BR>
      *３）　@建玉更新<BR>
      *　@更新データマネージャ.updateContractByTrans()にて、建玉を更新（DB-Update）する。<BR>
      * <BR>
      *　@[updateContractByTrans()に指定する引数]<BR>
      *　@建玉Params：　@２）でセットした建玉Params<BR>
      * <BR>
      *４）　@受渡代金、評価損益セット<BR>
      *　@set金額()をコールし、取引勘定明細Paramsに受渡代金、評価損益をセットする。<BR>
      * <BR>
      *　@[set金額()に指定する引数]<BR>
      *　@取引勘定明細Params：　@取引勘定明細Params<BR>
      * <BR>
      *５）　@トランザクション更新<BR>
      *　@更新データマネージャ.saveNewFinTransaction()にて、トランザクション（取引勘定明細）を更新（DB-Insert）する。<BR>
      * <BR>
      *　@[saveNewFinTransaction()に指定する引数]<BR>
      *　@取引勘定明細Params：　@取引勘定明細Params<BR>
      * <BR>
      *６）　@ＧＴＬ通知<BR>
      *　@notifyGtl()にて、トランザクション（顧客勘定明細）を更新する。<BR>
      * <BR>
      *　@[notifyGtl()に指定する引数]<BR>
      *　@取引勘定明細Params：　@取引勘定明細Params<BR>
      * <BR>
      * @@param l_ifoOrderExecution - 約定オブジェクト<BR>
      * @@param l_contractId - 建玉ID <BR>
      * @@throws com.fitechlabs.xtrade.kernel.data.DataException
      * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
      */
    protected void processCloseMarginOrderExecution(IfoOrderExecution l_ifoOrderExecution, long l_contractId)
        throws DataException, RuntimeSystemException
    {
        IfoFinTransactionParams l_ifoFinTransactionParams = new IfoFinTransactionParams();
        setMarketOrderedTransDefaultValues(l_ifoFinTransactionParams);
        
        //orderUnitRowを取得
        IfoOrderUnitRow l_ifoOrderUnitRow = IfoOrderUnitDao.findRowByPk(l_ifoOrderExecution.getOrderUnitId());
        if(l_ifoOrderUnitRow == null)
        {
            String msg = "processCloseMarginOrderExecution: No corresponding order unit for execId(" + l_ifoOrderExecution.getOrderExecutionId() + "),acct(" + l_ifoOrderExecution.getAccountId() + "),subAcct(" + l_ifoOrderExecution.getSubAccountId() + "),product(" + l_ifoOrderExecution.getProduct().getProductId() + "),market(" + l_ifoOrderExecution.getMarketId() + ")";
            log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        
        //set約定情報Toトランザクションをコール
        setExecutionInfoToMarketOrderedTrans(l_ifoFinTransactionParams, l_ifoOrderExecution, l_ifoOrderUnitRow, l_contractId);
        IfoContractParams l_ifoContractParams = getPersistenceManager().getContract(l_contractId);
        if(l_ifoContractParams == null)
        {
            String msg = "processCloseMarginOrderExecution dosen't exist for acct(" + l_ifoFinTransactionParams.getAccountId() + "fType(" + l_ifoFinTransactionParams.getFinTransactionType() + "), subAcct(" + l_ifoFinTransactionParams.getSubAccountId() + "), product(" + l_ifoFinTransactionParams.getProductId() + "), price(" + l_ifoFinTransactionParams.getPrice() + "), openDate(" + l_ifoFinTransactionParams.getDeliveryDate() + ")";
            log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        else
        {
            l_ifoFinTransactionParams.setContractId(l_ifoContractParams.getContractId());
            updateContractCloseFromMarketOrderedTrans(l_ifoContractParams, l_ifoFinTransactionParams);
            getPersistenceManager().updateContractByTrans(l_ifoContractParams);
            setMarginNetAmount(l_ifoFinTransactionParams);
            getPersistenceManager().saveNewFinTransaction(l_ifoFinTransactionParams);
            notifyGtl(l_ifoFinTransactionParams);
            return;
        }
    }
    
	/**
	  * （notifyGtlのオーバーライド）<BR>
	  * <BR>
      *トランザクションの更新をGTL層に通知する。<BR>
	  * <BR>
      *１）　@タイムスタンプに設定されている受付日時を取得する<BR>
      *　@－ThreadLocalSystemAttributesRegistry.getAttribute( )<BR>
      *  設定キー： 取引時間管理.TIMESTAMP_TAG<BR>
	  * <BR>
      *２）　@実時間用タイムスタンプから実時間を取得する<BR>
      *　@－ThreadLocalSystemAttributesRegistry.getAttribute( )<BR>
      *  設定キー： 設定キー定数定義インタフェイス.REAL_TIMESTAMP<BR>
	  * <BR>
      *３）　@実時間が設定されている場合(REAL_TIMESTAMP != null)<BR>
      *　@受付日時に実時間をセットしてからトランザクション更新の通知処理を行う。<BR>
	  * <BR>
      *　@３－１）　@受付日時のリセット<BR>
      *　@　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて受付日時に<BR>
      *　@　@　@２）で取得した実時間をセットする。<BR>
      *  　@設定キー： 取引時間管理.TIMESTAMP_TAG<BR>
	  * <BR>
      *　@３－２）　@GTL層への通知処理<BR>
      *　@　@super.notifyGtl()をコールする<BR>
	  * <BR>
      *　@３－３）　@受付日時の再リセット<BR>
      *　@　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて受付日時に<BR>
      *　@　@　@　@１）で取得したタイムスタンプの設定値をセットする。<BR>
      *  　@設定キー： 取引時間管理.TIMESTAMP_TAG<BR>
	  * <BR>
      *４）　@実時間が設定されていない場合(REAL_TIMESTAMP == null)<BR>
	  * <BR>
      *　@４－１）　@GTL層への通知処理<BR>
      *　@　@super.notifyGtl()をコールする<BR>
	  * <BR>
	  * @@param tparams - トランザクションParams<BR>
	  * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
	  */
	protected void notifyGtl(IfoFinTransactionParams tparams)
		throws RuntimeSystemException
	{
		final String STR_METHOD_NAME = "notifyGtl";
		log.entering(STR_METHOD_NAME);
		
		Timestamp l_timestamp =
			(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG
				);
		Timestamp l_realTimestamp = 
			(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3IfoAttributeNameDef.REAL_TIMESTAMP
				);
		if (l_realTimestamp != null)
		{
			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
				l_realTimestamp
				);
			super.notifyGtl(tparams);
			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
				l_timestamp
				);
		}
		else
		{
			super.notifyGtl(tparams);
		}
		log.exiting(STR_METHOD_NAME);
	}

	/**
	  * （undoExecutionのオーバーライド）<BR>
	  * <BR>
	  * 約定取消処理を行う。<BR>
	  * <BR>
      *１）　@タイムスタンプに設定されている受付日時を取得する<BR>
      *　@－ThreadLocalSystemAttributesRegistry.getAttribute( )<BR>
      *  設定キー： 取引時間管理.TIMESTAMP_TAG<BR>
	  * <BR>
      *２）　@実時間用タイムスタンプから実時間を取得する<BR>
      *　@－ThreadLocalSystemAttributesRegistry.getAttribute( )<BR>
      *  設定キー： 設定キー定数定義インタフェイス.REAL_TIMESTAMP<BR>
	  * <BR>
      *３）　@実時間が設定されている場合(REAL_TIMESTAMP != null)<BR>
      *　@受付日時に実時間をセットしてから約定取消処理を行う。<BR>
	  * <BR>
      *　@３－１）　@受付日時のリセット<BR>
      *　@　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて受付日時に<BR>
      *　@　@　@２）で取得した実時間をセットする。<BR>
      *  　@設定キー： 取引時間管理.TIMESTAMP_TAG<BR>
	  * <BR>
      *　@３－２）　@約定取消処理<BR>
      *　@　@super.undoExecution()をコールする<BR>
	  * <BR>
      *　@３－３）　@受付日時の再リセット<BR>
      *　@　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて受付日時に<BR>
      *　@　@　@　@１）で取得したタイムスタンプの設定値をセットする。<BR>
      *  　@設定キー： 取引時間管理.TIMESTAMP_TAG<BR>
	  * <BR>
      *４）　@実時間が設定されていない場合(REAL_TIMESTAMP == null)<BR>
	  * <BR>
      *　@４－１）　@約定取消処理<BR>
      *　@　@super.undoExecution()をコールする<BR>
	  * <BR>
	  * @@param execId - 約定ID<BR>
      * @@throws com.fitechlabs.xtrade.kernel.data.DataException
	  * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
	  */
	public void undoExecution(long execId)
		throws DataException, RuntimeSystemException
	{
		final String STR_METHOD_NAME = "undoExecution";
		log.entering(STR_METHOD_NAME);
		
		Timestamp l_timestamp =
			(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG
				);
		Timestamp l_realTimestamp = 
			(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3IfoAttributeNameDef.REAL_TIMESTAMP
				);
		if (l_realTimestamp != null)
		{
			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
				l_realTimestamp
				);
			super.undoExecution(execId);
			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
				l_timestamp
				);
		}
		else
		{
			super.undoExecution(execId);
		}
		log.exiting(STR_METHOD_NAME);
	}


    /**
     * (先物OP更新データマネージャ)<BR>
     *
     * @@author 王蘭芬(中訊)
     * @@version 1.0
     */
    public class WEB3IfoPersistentDataManager
        extends IfoPositionManagerHelper.PersistentDataManager
    {
        /**
         * ログユーティリティ
         */
        private WEB3LogUtility sublog =
            WEB3LogUtility.getInstance(WEB3IfoPersistentDataManager.class);

        /**
         * @@roseuid 40C0750C02AF
         */
        public WEB3IfoPersistentDataManager()
        {
            super();
        }
        
		/**
		 * (get取引勘定明細ForContract)<BR>
		 * <BR>
		 * 建玉に関連するトランザクション行オブジェクトのリストを取得する。<BR>
		 * レコードが見つからなかった場合は、サイズ0のListを返却する。<BR>
		 * <BR>
		 * 以下の条件にてトランザクション（取引勘定情報）テーブルを検索し、<BR>
		 * 一致する行をリストで返却する。<BR>
		 * <BR>
		 * [条件]<BR>
		 * トランザクション.建玉ＩＤ = 引数.建玉ＩＤ<BR>
		 * トランザクション.削除フラグ = BooleanEnum.FALSE<BR>
		 * @@param l_lngContractID - 建玉ＩD<BR>
		 * @@return List
		 * @@throws webbroker3.common.WEB3BaseException
		 */
		public List getFinTransactionDetailForContract(
			long l_lngContractID)
			throws WEB3BaseException
		{
			final String STR_METHOD_NAME = "getFinTransactionDetailForContract()";
			sublog.entering(STR_METHOD_NAME);

			List l_returnList = null;
			String l_strWhere = " contract_id = ? and delete_flag = ? ";

			Object[] l_objBindValue = new Object[2];
			l_objBindValue[0] = new Long(l_lngContractID);
			l_objBindValue[1] = new Integer(BooleanEnum.FALSE.intValue());

			try
			{
				//データ検索
				QueryProcessor processor = Processors.getDefaultProcessor();
				l_returnList = processor.doFindAllQuery(
					IfoFinTransactionRow.TYPE,
					l_strWhere,
					l_objBindValue);
			}
			catch (DataQueryException l_ex)
			{
				String l_strMessage = "トランザクション（取引勘定明細）テーブルを検索 error !!!";
				sublog.error(l_strMessage, l_ex);

				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}
			catch (DataNetworkException l_ex)
			{
				String l_strMessage = "トランザクション（取引勘定明細）テーブルを検索 error !!!";
				sublog.error(l_strMessage, l_ex);

				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}

			sublog.exiting(STR_METHOD_NAME);
			return l_returnList;
		}

        /**
         * (get取引勘定明細ForOrderUnit)<BR>
         * <BR>
         * トランザクション（取引勘定明細）テーブルより、以下の条件で行を検索し、<BR>
         * トランザクション（取引勘定明細）行オブジェクトのリストを取得する。<BR>
         * レコードが見つからなかった場合は、サイズ0のListを返却する。<BR>
         * <BR>
         * [検索条件]<BR>
         * トランザクション（取引勘定明細）.注文単位ＩＤ = 引数の注文単位ＩＤ<BR>
         * トランザクション.削除フラグ = BooleanEnum.FALSE<BR>
         * @@param l_lngOrderUnitID - 注文単位ＩＤ<BR>
         * @@return List
         * @@throws webbroker3.common.WEB3BaseException
         * @@roseuid 40736CF9003F
         */
        public List getFinTransactionDetailForOrderUnit(long l_lngOrderUnitID)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionDetailForOrderUnit";
            sublog.entering(STR_METHOD_NAME);

            List l_returnList = null;

            String l_strWhere = " order_unit_id = ?  and delete_flag = ? ";
            Object[] l_objBindValue = new Object[2];

            l_objBindValue[0] = new Long(l_lngOrderUnitID);
            l_objBindValue[1] = BooleanEnum.FALSE;

            try
            {
                //データ査詢
                QueryProcessor processor = Processors.getDefaultProcessor();
                l_returnList = processor.doFindAllQuery(
                        IfoFinTransactionRow.TYPE,
                        l_strWhere,
                        l_objBindValue);

            }
            catch (DataQueryException l_ex)
            {
                String l_strMessage = "トランザクション（取引勘定明細）テーブルを検索 error !!!";
                sublog.error(l_strMessage, l_ex);

                //throw new WEB3SystemLayerException
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                String l_strMessage = "トランザクション（取引勘定明細）テーブルを検索 error !!!";
                sublog.error(l_strMessage, l_ex);

                //throw new WEB3SystemLayerException
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            sublog.exiting(STR_METHOD_NAME);

            return l_returnList;
        }

        /**
         * (get取引勘定明細ForOpenCont)<BR>
         * <BR>
         * 建玉に関連する新規建トランザクション行オブジェクトのリストを取得する。<BR>
         * レコードが見つからなかった場合は、サイズ0のListを返却する。<BR>
         * <BR>
         * 以下の条件にてトランザクション（取引勘定情報）テーブルを検索し、<BR>
         * 一致する行をリストで返却する。<BR>
         * <BR>
         * [条件]<BR>
         * トランザクション.トランザクションカテゴリ = ”91：先物新規建取引” or ”93：OP新規建取引”<BR>
         * トランザクション.建玉ＩＤ = 引数.建玉ＩＤ<BR>
         * トランザクション.削除フラグ = BooleanEnum.FALSE<BR>
         * @@param l_lngContractID - 建玉ＩD<BR>
         * @@return List
         * @@throws webbroker3.common.WEB3BaseException
         * @@roseuid 407523AD03B9
         */
        public List getFinTransactionDetailForOpenCont(
            long l_lngContractID)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionDetailForOpenCont()";
            sublog.entering(STR_METHOD_NAME);

            List l_returnList = null;
            String l_strWhere = " (fin_transaction_categ = ? or fin_transaction_categ = ?) and contract_id = ? and delete_flag = ? ";

            Object[] l_objBindValue = new Object[4];
            l_objBindValue[0] = new Integer(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN.intValue());
            l_objBindValue[1] = new Integer(FinTransactionCateg.EQTYPE_IDX_OPTIONS_OPEN.intValue());
            l_objBindValue[2] = new Long(l_lngContractID);
            l_objBindValue[3] = new Integer(BooleanEnum.FALSE.intValue());

            try
            {
                //データ査詢
                QueryProcessor processor = Processors.getDefaultProcessor();
                l_returnList = processor.doFindAllQuery(
                    IfoFinTransactionRow.TYPE,
                    l_strWhere,
                    l_objBindValue);
            }
            catch (DataQueryException l_ex)
            {
                String l_strMessage = "トランザクション（取引勘定明細）テーブルを検索 error !!!";
                sublog.error(l_strMessage, l_ex);

                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                String l_strMessage = "トランザクション（取引勘定明細）テーブルを検索 error !!!";
                sublog.error(l_strMessage, l_ex);

                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            sublog.exiting(STR_METHOD_NAME);
            return l_returnList;
        }

        /**
         * (get建玉ＩＤByOrder)<BR>
         * <BR>
         * 注文に関連する建玉ＩＤを配列で取得する。<BR>
         * <BR>
         * 注文ＩＤでトランザクション（取引勘定明細）テーブルを検索し、<BR>
         * 一致する行の建玉ＩＤを配列で返却する。<BR>
         * （重複要素は削除する）<BR>
         * <BR>
         * @@param l_lngOrderＩＤ - 注文ＩＤ<BR>
         * @@return long[]
         * @@throws webbroker3.common.WEB3BaseException
         * @@roseuid 40A2E5720226
         */
        public long[] getContractIDByOrder(long l_lngOrderID)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getContractIDByOrder";
            sublog.entering(STR_METHOD_NAME);

            long[] l_lngContractIDs;
            try
            {
                // 注文ＩＤでトランザクション（取引勘定明細）テーブルを検索し
                List l_lisTransactionParams =
                    IfoFinTransactionDao.findRowsByOrderId(l_lngOrderID);

                l_lngContractIDs =
                    new long[l_lisTransactionParams.size()];
                IfoFinTransactionParams[] l_lngTransactionParams =
                    new IfoFinTransactionParams[l_lisTransactionParams.size()];
                l_lisTransactionParams.toArray(l_lngTransactionParams);

                // 一致する行の建玉ＩＤを配列で返却する。
                for (int i = 0; i < l_lngTransactionParams.length; i++)
                {
                    l_lngContractIDs[i] =
                        l_lngTransactionParams[i].getContractId();

                }

            }
            catch (DataException l_ex){
                log.error("Error In Method: " + STR_METHOD_NAME, l_ex);

                // throws WEB3SystemLayerException
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            sublog.exiting(STR_METHOD_NAME);
            return l_lngContractIDs;
        }


        /**
         * (get取引勘定明細ForCloseCont)<BR>
         * 建玉に関連する返済トランザクション行オブジェクトのリストを取得する。<BR>
         *レコードが見つからなかった場合は、サイズ0のListを返却する。<BR>
         *以下の条件にてトランザクション（取引勘定情報）テーブルを検索し、一致する行をリストで返却する。<BR>
         *<BR>
         *    [条件]<BR>
         *    トランザクション.トランザクションカテゴリ = ”92：先物返済取引” or ”94：OP返済取引”<BR>
         *    トランザクション.建玉ＩＤ = 引数.建玉ＩＤ<BR>
         *    トランザクション.削除フラグ = BooleanEnum.FALSE<BR>
         *※トランザクション発生日時（fin_transaction_timestamp）昇順で取得する。<BR>
         * @@param l_lngContractId<BR>
         * @@throws webbroker3.common.WEB3BaseException
         * @@return
         */
        public List getFinTransactionDetailForCloseCont(long l_lngContractId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionDetailForCloseCont";
            sublog.entering(STR_METHOD_NAME);

            List l_returnList = new ArrayList();
            //データ査詢
            QueryProcessor processor = null;
            try
            {
                // //検索条件文字列の作成以下に示す順序で検索条件文字列を作成する。
                String l_strWhere = " (fin_transaction_categ = ? or fin_transaction_categ = ?) and contract_id = ? and delete_flag = ?";

                Object[] l_strBindValue = new Object[4];
                l_strBindValue[0] = new Integer(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE.intValue());
                l_strBindValue[1] = new Integer(FinTransactionCateg.EQTYPE_IDX_OPTIONS_CLOSE.intValue());
                l_strBindValue[2] = new Long(l_lngContractId);
                l_strBindValue[3] = new Integer(BooleanEnum.FALSE.intValue());

                String l_strOrderBy = "fin_transaction_timestamp";

                processor = Processors.getDefaultProcessor();
                l_returnList = processor.doFindAllQuery(
                    IfoFinTransactionRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_strBindValue);
            }
            catch (DataQueryException l_ex)
            {
                String l_strMessage = "トランザクション（取引勘定明細）テーブルを検索 error !!!";
                sublog.error(l_strMessage, l_ex);

                //throw new WEB3SystemLayerException
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                String l_strMessage = "トランザクション（取引勘定明細）テーブルを検索 error !!!";
                sublog.error(l_strMessage, l_ex);

                //throw new WEB3SystemLayerException
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            return l_returnList;
        }
        
        /**
         * (get建玉)<BR>
         * 【建玉テーブル】から、<BR>
         * 引数の値を指定し該当する建玉Paramsオブジェクトを取得し返却する。<BR>
         * （IfoContractParams getContract(long accountId, <BR>
         * long subAccountId, long productId, long marketId, ContractTypeEnum type,<BR>
         *  double price, Date openDate, Date closeDate)のオーバーロード） <BR>
         * <BR>
         * １）　@引数オブジェクトの以下の項目を検索条件に指定し、<BR>
         * 　@　@　@QueryProcessorを使用し【建玉テーブル】から建玉Paramsオブジェクトを取得する。<BR>
         * <BR>
         * 　@　@＜検索条件＞  <BR>
         * 　@　@口座ID = 引数.口座ID  <BR>
         * 　@　@かつ　@補助口座ID = 引数.補助口座ID  <BR>
         * 　@　@かつ　@市場ID = 引数.市場ID  <BR>
         * 　@　@かつ　@銘柄ID = 引数.銘柄ID  <BR>
         * 　@　@かつ　@建区分 = 引数.建区分  <BR>
         * 　@　@かつ　@建単価 = 引数.建単価  <BR>
         * 　@　@かつ　@建日 = 引数.建日  <BR>
         * 　@　@かつ　@期日 = 引数.期日  <BR>
         * 　@　@かつ　@受渡日 = 引数.受渡日 <BR>
         * <BR>
         * ２）　@取得した建玉Paramsオブジェクトを返却する。<BR>
         * 　@　@　@該当する建玉が存在しない場合は、nullを返却する。 <BR>
         * @@param l_lngAccountId - (口座ID)<BR>
         * 口座ID。<BR>
         * @@param l_lngSubAccountId - (補助口座ID)<BR>
         * 補助口座ID。<BR>
         * @@param l_lngMarketId - (市場ID)<BR>
         * 市場ID。<BR>
         * @@param l_lngProductId - (銘柄ID)<BR>
         * 銘柄ID。<BR>
         * @@param l_contractType - (建区分)<BR>
         * 建区分。<BR>
         * @@param l_dblPrice - (建単価)<BR>
         * 建単価。<BR>
         * @@param l_datOpenDate - (建日)<BR>
         * 建日。<BR>
         * @@param l_datCloseDate - (期日)<BR>
         * 期日。<BR>
         * @@param l_datDeliveryDate - (受渡日)<BR>
         * 受渡日。<BR>
         * @@return IfoContractParams
         * @@throws WEB3BaseException
         */
        public IfoContractParams getContract(
            long l_lngAccount,
            long l_lngSubAccountId,
            long l_lngMarketId,
            long l_lngProductId,
            ContractTypeEnum l_contractType,
            double l_dblPrice,
            Date l_datOpenDate,
            Date l_datCloseDate,
            Date l_datDeliveryDate) throws DataException
        {
            final String STR_METHOD_NAME = "getContract(long, long, long, long, ContractTypeEnum, "
                + "double, Date, Date, Date)";
            sublog.entering(STR_METHOD_NAME);

            //検索条件
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? ");
            l_sbWhere.append(" and sub_account_id = ? ");
            l_sbWhere.append(" and market_id = ? ");
            l_sbWhere.append(" and product_id = ? ");
            l_sbWhere.append(" and contract_type = ? ");
            l_sbWhere.append(" and contract_price = ? ");
            l_sbWhere.append(" and open_date = ? ");
            l_sbWhere.append(" and close_date = ? ");
            l_sbWhere.append(" and delivery_date = ? ");

            List l_lisBindValue = new ArrayList();
            l_lisBindValue.add(new Long(l_lngAccount));
            l_lisBindValue.add(new Long(l_lngSubAccountId));
            l_lisBindValue.add(new Long(l_lngMarketId));
            l_lisBindValue.add(new Long(l_lngProductId));
            l_lisBindValue.add(l_contractType);
            l_lisBindValue.add(new Double(l_dblPrice));
            l_lisBindValue.add(l_datOpenDate);
            l_lisBindValue.add(l_datCloseDate);
            l_lisBindValue.add(l_datDeliveryDate);
            Object[] l_objBindValues = new Object[l_lisBindValue.size()];
            l_lisBindValue.toArray(l_objBindValues);

            List l_lisRecords = null;

            //QueryProcessorを使用し【建玉テーブル】から建玉Paramsオブジェクトを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                IfoContractRow.TYPE,
                l_sbWhere.toString(),
                l_objBindValues);

            //該当する建玉が存在しない場合は、nullを返却する。
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                sublog.exiting(STR_METHOD_NAME);
                return null;
            }
            //取得した建玉Paramsオブジェクトを返却する。
            sublog.exiting(STR_METHOD_NAME);
            return (IfoContractParams)l_lisRecords.get(0);
        }
        
        /**
         * (get建玉ListBy注文単位)<BR>
         * 指定された注文データに対する、建玉データを全て取得し、<BR>
         * 建玉ParamsのListを作成して返す。<BR>
         * <BR>
         * １）　@this.get建玉ＩＤByOrder(引数の注文ID)メソッドのコールにより、<BR>
         * 建玉IDのListを取得する。<BR>
         * ２）　@１）で作成した建玉IDのList数分、以下の処理を行い、<BR>
         * 　@　@　@　@指定された注文データに対する建玉ParamsオブジェクトのListを作成する。<BR>
         * 　@２－１）　@取引勘定明細Params.建玉IDに該当する建玉Paramsオブジェクトを取得する。<BR>
         * 　@　@　@　@先物OP更新データマネージャ.getContract(取引勘定明細Params.建玉ID)コールにより<BR>
         * 　@　@　@　@取得する。<BR>
         * <BR>
         * 　@２－２）　@取得した建玉Paramsオブジェクトを、戻り値Listに追加する。<BR>
         * <BR>
         * ３）　@作成した建玉ParamsのListを返す。<BR>
         * <BR>
         * @@param l_lngOrderId - (注文ID)<BR>
         * 注文ID<BR>
         * @@return List
         * @@throws WEB3BaseException
         *
         */
        public List getContractListByOrderUnit(long l_lngOrderId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getContractListByOrderUnit(long)";
            log.entering(STR_METHOD_NAME);

            List l_lisIfoContractParams = new ArrayList();
            // １）　@this.get建玉ＩＤByOrder(引数の注文ID)メソッドのコールにより、建玉IDのListを取得する。
            long[] l_lngContractIds = this.getContractIDByOrder(l_lngOrderId);

            // ２）指定された注文データに対する建玉ParamsオブジェクトのListを作成する。
            int l_intContractIdsCnt = l_lngContractIds.length;
            for (int i = 0; i < l_intContractIdsCnt; i++)
            {
                try
                {
                    l_lisIfoContractParams.add(super.getContract(l_lngContractIds[i]));
                }
                catch (DataFindException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }

            // ３）　@作成した建玉ParamsのListを返す。
            log.exiting(STR_METHOD_NAME);
            return l_lisIfoContractParams;
        }

        /**
         * (get取引勘定明細ListBy注文単位Plus建玉)<BR>
         * 指定された注文データ＋建玉データに対する、<BR>
         * 一口約定計算対象となる取引勘定明細ParamsのListを取得する。<BR>
         * <BR>
         * １）　@注文単位ID、建玉IDを条件に、<BR>
         * 　@【トランザクションテーブル（取引勘定明細）】を検索する。<BR>
         * 　@-------------------------------<BR>
         * 　@＜検索条件＞<BR>
         * 　@　@　@　@注文単位ID＝引数.注文単位ID<BR>
         * 　@かつ　@建玉ID＝引数.建玉ID<BR>
         * 　@かつ　@削除フラグ＝FALSE <BR>
         * 　@-------------------------------<BR>
         * <BR>
         * ２）　@可変長配列オブジェクトに、検索された取引勘定明細Paramsを、<BR>
         * 　@一件ずつ配列に追加する。<BR>
         * <BR>
         * ３）　@可変長配列オブジェクトを返却する。<BR>
         * <BR>
         * @@param l_lngOrderUnitId - (注文単位ID)<BR>
         * 注文単位ID<BR>
         * @@param l_lngContractId - (建玉ID)<BR>
         * 建玉ID<BR>
         * @@return List
         * @@throws WEB3BaseException
         *
         */
        public List getTransactionsListByOrderUnitPlusContract(long l_lngOrderUnitId, long l_lngContractId)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getTransactionsListByOrderUnitPlusContract(long, long)";
            log.entering(STR_METHOD_NAME);

            //　@【トランザクションテーブル（取引勘定明細）】を検索する。
            //　@-------------------------------
            //　@＜検索条件＞
            //　@　@　@　@　@注文単位ID＝引数.注文単位ID
            //　@　@かつ　@建玉ID＝引数.建玉ID
            //　@　@かつ　@削除フラグ＝FALSE
            //　@　@-------------------------------
            List l_lisTransactions = null;

            //検索条件
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" order_unit_id = ? ");
            l_sbWhere.append(" and contract_id = ? ");
            l_sbWhere.append(" and delete_flag = ? ");

            Object[] l_objBindValues = {
                new Long(l_lngOrderUnitId),
                new Long(l_lngContractId),
                new Integer(0)};
            try
            {
                QueryProcessor l_queryProcessor = null;
                l_queryProcessor = Processors.getDefaultProcessor();
                l_lisTransactions = l_queryProcessor.doFindAllQuery(
                    IfoFinTransactionRow.TYPE,
                    l_sbWhere.toString(),
                    l_objBindValues);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            log.exiting(STR_METHOD_NAME);
            return l_lisTransactions;
        }
    }

    public PersistentDataManager getPersistenceManager()
    {
        return new WEB3IfoPersistentDataManager();
    }
}
@
