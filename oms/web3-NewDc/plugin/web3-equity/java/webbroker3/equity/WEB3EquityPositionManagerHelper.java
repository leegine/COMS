head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPositionManagerHelper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張ポジションヘルパー(WEB3EquityPositionManagerHelper.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/25 呉艶飛(中訊) 新規作成
Revesion History : 2006/07/15 栄イ 【株式】仕様変更管理台帳・モデル952、953、954を対応
Revesion History : 2006/08/14 栄イ 【株式】仕様変更管理台帳・モデル973、DB更新仕様165を対応
Revesion History : 2006/08/18 栄イ 【株式】仕様変更管理台帳・モデル975を対応
*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypePositionManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypePositionManagerHelper;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ConsolidatedCommissionInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GeneralizedFinTransaction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CapitalGainStatusDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （拡張ポジションヘルパー）。<BR>
 * xTrade標準実装からの拡張ポジションヘルパークラス<BR>
 * <BR>
 * 残高更新の手続きを記述
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3EquityPositionManagerHelper extends EqTypePositionManagerHelper
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityPositionManagerHelper.class);

    /**
     * 拡張データマネージャオブジェクト。<BR>
     */
    private static WEB3EquityPersistentDataManager dataManager = new WEB3EquityPersistentDataManager();

    /**
     * コンストラクタ。<BR>
     * <BR>
     * ［処理概要］<BR>
     * １）スーパークラスの処理を呼び出す。<BR>
     * 
     * @@param l_tradingModuleType 商品タイプ
     */
    public WEB3EquityPositionManagerHelper(ProductTypeEnum l_tradingModuleType)
    {
        super(l_tradingModuleType);
    }

    /**
     * 拡張データマネージャオブジェクトを返す。
     */
    public PersistentDataManager getPersistenceManager()
    {
        return dataManager;
    }

    /**
     * (一口約定処理)<BR>
     * 一口約定として手数料の再計算をおこない結果を<BR>
     * 保有資産、株式顧客勘定明細、顧客勘定、補助口座に反映させる。<BR>
     * <BR>
     * シーケンス図「（残高）一口約定処理」参照。<BR>
     * <BR>
     * １）一口計算対象となる株式顧客勘定明細Paramオブジェクトのリストを取得する。<BR>
     * 　@－拡張データマネージャー.get株式顧客勘定明細Paramリスト()をコールする。<BR>
     * <BR>
     * ３）株式顧客勘定明細Paramsオブジェクトのリストより株式顧客勘定明細の配列を作成する。<BR>
     * 　@－リストの件数を取得する。<BR>
     * 　@　@件数が１件だった時は、以下の通りとする。<BR>
     * 　@　@－約定処理からのコールであれば（＝引数のis約定取消==false）処理を終了する。<BR>
     * 　@　@－約定取消処理からのコールであれば（＝引数のis約定取消==true）<BR>
     * 　@　@　@以下の処理を続行する。<BR>
     * 　@　@　@※（約定取消後の注文に対する委託手数料の再計算が必要なため）<BR>
     * 　@－リストの件数のサイズで株式顧客勘定明細の配列を作成する。<BR>
     * <BR>
     * ４）株式計算サービス.calc手数料（按分）()をコールする。<BR>
     * <BR>
     * ５）一口約定計算後の誤差を保有資産に反映させる。※買約定の時のみこの処理を実施<BR>
     * 　@－一口約定計算前の委託手数料の合計を取得する。<BR>
     * 　@－一口約定計算前の消費税の合計を取得する。<BR>
     * 　@－一口約定計算後の委託手数料の合計を取得する。<BR>
     * 　@－一口約定計算後の委託手数料の合計を取得する。<BR>
     * 　@－一口約定計算後の諸経費（委託手数料＋消費税）の誤差を保有資産テーブルに反映させる。<BR>
     * <BR>
     * ６）株式顧客勘定明細Paramsの委託手数料・消費税・受渡代金の値を一口約定計算後の値に更新する。<BR>
     * 　@－一口約定計算後の委託手数料を取得し株式顧客勘定明細Paramsにセットする。<BR>
     * 　@－一口約定計算後の消費税を取得し株式顧客勘定明細Paramsにセットする。<BR>
     * 　@－株式顧客勘定明細Paramsより約定単価を取得する。<BR>
     * 　@－株式顧客勘定明細Paramsより約定数量を取得する。<BR>
     * 　@－一口約定計算後の受渡代金(*1)を計算し株式顧客勘定明細Paramsにセットする。<BR>
     * 　@　@※買約定時：受渡代金＝約定代金（約定数量×約定単価）＋諸経費（委託手数料＋消費税）<BR>
     * 　@　@※売約定時：受渡代金＝約定代金（約定数量×約定単価）－諸経費（委託手数料＋消費税）<BR>
     * <BR>
     * 　@－一口約定計算後の譲渡益金額を計算し、株式顧客勘定明細Paramsにセットする。<BR>
     * 　@　@　@（売り約定時のみ）<BR>
     * 　@　@　@－株式計算サービス.calc譲渡損益( )をコールする。戻り値を「譲渡益金額」にセット。<BR>
     * 　@　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@　@＜calc譲渡損益(double, double, long, SubAccount, TaxTypeEnum)：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@　@金額：　@一口約定計算後の受渡代金(*1)<BR>
     * 　@　@　@　@売数量：　@株式顧客勘定明細Params.約定数量<BR>
     * 　@　@　@　@銘柄ID：　@株式顧客勘定明細Params.銘柄ID<BR>
     * 　@　@　@　@補助口座：　@株式顧客勘定明細Params.口座ID、補助口座IDに該当する<BR>
     * 　@　@　@　@　@　@　@　@　@　@補助口座オブジェクト<BR>
     * 　@　@　@　@税区分：　@株式顧客勘定明細Params.税区分<BR>
     * 　@　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * 　@－一口約定計算後の譲渡益税額を計算し、株式顧客勘定明細Paramsにセットする。<BR>
     * 　@　@　@（売り約定時のみ）<BR>
     * 　@　@　@－株式計算サービス.calc譲渡益税( )をコールする。戻り値を「譲渡益税額」にセット。<BR>
     * 　@　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@　@＜calc譲渡益税(補助口座, TaxTypeEnum, double, Timestamp, TaxTypeEnum)：引数設定仕様＞<BR>
     * 　@　@　@　@補助口座：　@取得した補助口座オブジェクト<BR>
     * 　@　@　@　@税区分：　@株式顧客勘定明細Params.税区分<BR>
     * 　@　@　@　@金額：　@計算した譲渡益金額（calc譲渡損益( )の戻り値）<BR>
     * 　@　@　@　@基準日：　@株式顧客勘定明細Params.受渡日<BR>
     * 　@　@　@　@顧客税区分：　@取得した顧客税区分(*2)<BR>
     * 　@　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@(*2)取得した顧客税区分<BR>
     * 　@　@　@　@　@拡張アカウントマネージャ.get顧客(株式顧客勘定明細Params.口座ID)で<BR>
     * 　@　@　@　@　@顧客オブジェクトを取得してから、<BR>
     * 　@　@　@　@　@顧客.get受渡日税区分(株式顧客勘定明細Params.受渡日)により、顧客税区分を取得する。<BR>
     * <BR>
     * 　@－株式顧客勘定明細Paramsを更新する。<BR>
     * 　@－株式顧客勘定明細Paramsの変更をGTL層に通知する。<BR>
     * @@param l_lngOrderUnitId 注文単位ID
     * @@param l_lngAssetId 資産ID
     * @@param l_isExecuteCancel is約定取消<BR>
     * 約定取消かどうかのフラグ。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4143F6420231
     */
    public void shareContractExecution(
        long l_lngOrderUnitId,
        long l_lngAssetId,
        boolean l_isExecuteCancel)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "shareContractExecution(long, long, boolean)";
        log.entering(STR_METHOD_NAME);

        // 株式顧客勘定明細データの取得
        List l_lisTrans = dataManager.getEqtypeFinTransactionParams(l_lngOrderUnitId, l_lngAssetId);
        int l_intSize = 0;
        if (l_lisTrans != null)
        {
            l_intSize = l_lisTrans.size();
        }

        if (l_intSize < 1)
        {
            // 株式顧客勘定明細のデータが０件の場合は、これ以下の処理を行わない。
            log.debug("size = 0 and return.");
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else if (l_intSize == 1)
        {
            if (!l_isExecuteCancel)
            {
                // 株式顧客勘定明細のデータが１件以下の場合は、これ以下の処理を行わない。
                log.debug("size = 1 and return.");
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }
        log.debug("fin transcation size:" + Integer.toString(l_lisTrans.size()));
        // １口約定計算を行う
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_bizLogic = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();

        EqtypeFinTransactionRow[] l_arrayEqtypeFinTransaction = new EqtypeFinTransactionRow[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_arrayEqtypeFinTransaction[i] = (EqtypeFinTransactionRow) l_lisTrans.get(i);
        }
        ConsolidatedCommissionInfo l_commInfo = null;
        try
        {
            l_commInfo = l_bizLogic.calcCommission(l_arrayEqtypeFinTransaction);
        }
        catch (WEB3BaseException e)
        {
            log.error("WEB3BaseException", e);
        }

        WEB3EquityOrderManager l_orderMgr = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        EqTypeOrderUnit l_orderUnit = null;

        try
        {
            l_orderUnit = (EqTypeOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUnitId);
			EqtypeFinTransactionRow l_eqtypeFinTranRow = l_arrayEqtypeFinTransaction[0];
			SubAccount l_subAccount =
				l_finApp.getAccountManager().getSubAccount(
				l_eqtypeFinTranRow.getAccountId(), l_eqtypeFinTranRow.getSubAccountId());
			WEB3GentradeMainAccount l_account =
				(WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(
				l_eqtypeFinTranRow.getAccountId());
			TaxTypeEnum l_accountTaxType =
				l_account.getDeliveryDateTaxType(l_eqtypeFinTranRow.getDeliveryDate());

            // 買約定のときのみ以下を実行
            if (l_orderUnit.getSide().equals(SideEnum.BUY))
            {
                // 委託手数料合計の取得
                double l_dbCommFeeAmount = getCommissionFeeAmount(l_lisTrans);
                log.debug("委託手数料合計：[" + l_dbCommFeeAmount + "]");

                // 消費税合計の取得
                double l_dbSalesTaxAmount = getSalesTaxAmount(l_lisTrans);
                log.debug("消費税合計：[" + l_dbSalesTaxAmount + "]");
                
                log.debug("（按分）委託手数料合計:[" + l_commInfo.getTotalCommission() + "]");
                log.debug("（按分）消費税合計:[" + l_commInfo.getTotalSalesTax() + "]");
                // 一口約定保有資産更新
                updateShareContractAsset(l_lngAssetId, l_dbCommFeeAmount + l_dbSalesTaxAmount, l_commInfo.getTotalCommission() + l_commInfo.getTotalSalesTax());
            }

            EqtypeFinTransactionParams l_row;
            double l_dblCommission;
            double l_dblSalesTax;
            double l_dblPrice;
            double l_dblQuantity;
            BigDecimal l_bdNetAmount;
            int l_intLisTransSize = 0;
            if (l_lisTrans != null)
            {
                l_intLisTransSize = l_lisTrans.size();
            }

            for (int i = 0; i < l_intLisTransSize; i++)
            {
                l_row = (EqtypeFinTransactionParams) l_lisTrans.get(i);

                l_dblCommission = l_commInfo.getCommission(i);
                l_dblSalesTax = l_commInfo.getSalesTax(i);
                l_dblPrice = l_row.getPrice();
                l_dblQuantity = l_row.getQuantity();
                log.debug("委託手数料[" + i + "]：[" + l_dblCommission + "]");
                log.debug("消費税[" + i + "]：[" + l_dblSalesTax + "]");
                log.debug("約定単価[" + i + "]：[" + l_dblPrice + "]");
                log.debug("約定数量[" + i + "]：[" + l_dblQuantity + "]");

                // 受渡代金の計算
                if (l_orderUnit.getSide().equals(SideEnum.BUY))
                {
                    // 買付の場合
                    l_bdNetAmount = new BigDecimal(l_dblPrice);
                    l_bdNetAmount = l_bdNetAmount.multiply(new BigDecimal(l_dblQuantity));
                    l_bdNetAmount = l_bdNetAmount.add(new BigDecimal(l_dblCommission));
                    l_bdNetAmount = l_bdNetAmount.add(new BigDecimal(l_dblSalesTax));
                    l_bdNetAmount = new BigDecimal("0.0").subtract(l_bdNetAmount);
                }
                else if (l_orderUnit.getSide().equals(SideEnum.SELL))
                {
                    // 売付の場合
                    l_bdNetAmount = new BigDecimal(l_dblPrice);
                    l_bdNetAmount = l_bdNetAmount.multiply(new BigDecimal(l_dblQuantity));
                    l_bdNetAmount = l_bdNetAmount.subtract(new BigDecimal(l_dblCommission));
                    l_bdNetAmount = l_bdNetAmount.subtract(new BigDecimal(l_dblSalesTax));
                }
                else
                {
                    throw new RuntimeSystemException("FinTransactionType error!");
                }
                log.debug("受渡代金[" + i + "]：[" + l_bdNetAmount.doubleValue() + "]");
                
				double l_dblCaptalGain = 0.0D;
				double l_dblCaptalGainTax = 0.0D;
                String l_strCapitalGainStatus = WEB3CapitalGainStatusDef.INVALIDITY;
                // 譲渡益金額、譲渡益税額の計算（売付の場合のみ）
				if (l_orderUnit.getSide().equals(SideEnum.SELL))
				{
					//1.12.1 calc概算譲渡損益(double, double, long, SubAccount, TaxTypeEnum)
					l_dblCaptalGain = l_bizLogic.calcEstimatedCapitalGain(
						l_bdNetAmount.doubleValue(),
						l_row.getQuantity(),
					    l_row.getProductId(),
					    l_subAccount,
					    l_row.getTaxType());

					//1.12.2 calc譲渡益税(補助口座, TaxTypeEnum, double, Timestamp, TaxTypeEnum)
					l_dblCaptalGainTax =
					    l_bizLogic.calcCapitalGainTax(l_subAccount, l_row.getTaxType(),
					    l_dblCaptalGain, l_row.getDeliveryDate(),l_accountTaxType);

					//1.12.3 get譲渡益有効状態(long, long, long, TaxTypeEnum, FinTransactionType)
					l_strCapitalGainStatus = l_bizLogic.getCapitalGainStatus(
						l_row.getAccountId(),
						l_row.getSubAccountId(), 
						l_row.getProductId(), 
						l_row.getTaxType(),
						l_row.getFinTransactionType());

					//1.12.4 setCapitalGainStaus()
					l_row.setCapitalGainStatus(l_strCapitalGainStatus);

					log.debug("譲渡益金額[" + i + "]：[" + l_dblCaptalGain + "]");
					log.debug("譲渡益税額[" + i + "]：[" + l_dblCaptalGainTax + "]");
				}

                // 株式顧客勘定明細テーブル更新
                l_row.setCommissionFee(l_dblCommission);
                l_row.setCommissionFeeTax(l_dblSalesTax);
                l_row.setNetAmount(l_bdNetAmount.doubleValue());
                HashMap l_map = new HashMap();
                l_map.put("commission_fee", new BigDecimal(l_row.getCommissionFee()));
                l_map.put("commission_fee_tax", new BigDecimal(l_row.getCommissionFeeTax()));
                l_map.put("net_amount", l_bdNetAmount);
                if (l_orderUnit.getSide().equals(SideEnum.SELL))
                {
					l_map.put("capital_gain", new BigDecimal(l_dblCaptalGain));
					l_map.put("capital_gain_tax", new BigDecimal(l_dblCaptalGainTax));
                    l_map.put("capital_gain_status", l_strCapitalGainStatus);
                }
                l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                dataManager.updateFinTransaction(l_row, l_map);

                // 顧客勘定更新
                notifyGtl(l_row);
                log.debug("EqtypeFinTransaction.トランザクションID：[" + l_row.getFinTransactionId() + "]");
                log.debug("EqtypeFinTransaction.委託手数料：[" + l_row.getCommissionFee() + "]");
                log.debug("EqtypeFinTransaction.委託手数料消費税：[" + l_row.getCommissionFeeTax() + "]");
            }
        }
        catch (NotFoundException l_nfe)
        {
            String l_strMessage =
                "株式注文単位データ／補助口座データ／口座データが見つかりません： order_unit_id("
                + l_lngOrderUnitId + ")";
            log.error(l_strMessage, l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_dne.getMessage(), l_dne);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (applyTo保有資産ポジション)<BR>
     * 株式顧客勘定明細Paramsの内容によって保有資産を更新する。<BR>  
     * （applyToAssetPositionのオーバーライド）<BR>               
     * <BR>                                                 
     * ※シーケンス図「（残高）applyTo保有資産ポジション」参照。<BR>
     * <BR>
     * １）約定した資産を条件に保有資産オブジェクトを取得する。 <BR> 
     * 　@　@（* 株式ポジションマネージャ.get保有資産( )をコール）<BR> 
     * <BR>                                   
     * ２）保有資産が検索できた時は、保有資産の更新を行う。 <BR>     
     * 　@　@保有資産が検索できなかった時は保有資産の挿入を行う。
     * @@param l_trans
     * @@return List
     * @@throws DataException
     * @@roseuid 4143F64203C1
     */
    public List applyToAssetPosition(
        EqtypeFinTransactionParams l_trans)
        throws DataException
    {
        final String STR_METHOD_NAME = "applyToAssetPosition(EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_trans == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3EquityPersistentDataManager l_dataManager =
            (WEB3EquityPersistentDataManager) this.getPersistenceManager();
        
        //2. get保有資産Params()
        AssetParams l_assetParams = l_dataManager.getAsset(l_trans);
        
        //4. getFinTransactionType()
        FinTransactionType l_finTransactionType = l_trans.getFinTransactionType();
        
        //5. getSide()
        SideEnum l_sideEnum = this.getSide(l_finTransactionType);
        
        // 買約定(SideEnum.BUY)で保有資産Paramsがnullの時
        if (l_assetParams == null && SideEnum.BUY.equals(l_sideEnum))
        {
            l_assetParams = new AssetParams();
            //6. set保有資産デフォルト値()
            this.setAssetDefaultValues(l_assetParams);
            //7. set保有資産Params()
            this.setNewAssetParamsFromMarketTradedTrans(l_assetParams, l_trans);
            //9. saveNewAsset()
            l_dataManager.saveNewAsset(l_assetParams);
        }
        // 買約定(SideEnum.BUY)で保有資産Paramsがnullでない時
        else if (l_assetParams != null && SideEnum.BUY.equals(l_sideEnum))
        {
            //10. update保有資産Params()
            this.updateAssetParamsFromMarketTradedTrans(l_assetParams, l_trans);
            //12. updateAssetByTrans()
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_dataManager.updateAssetByTrans(l_assetParams);
        }
        // 売約定(SideEnum.SELL)の時
        else if (SideEnum.SELL.equals(l_sideEnum))
        {
            //13. update保有資産Params()
            this.updateAssetParamsFromMarketTradedTrans(l_assetParams, l_trans);
            //14. updateAssetByTrans()
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_dataManager.updateAssetByTrans(l_assetParams);
        }
        //15. setAssetId()
        l_trans.setAssetId(l_assetParams.getAssetId());
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (株式約定残高更新処理)<BR>
     * 株式売買約定時の顧客残高を更新する。<BR>
     * （processCashBasedOrderExecutionをオーバーライド）<BR>
     * <BR>
     * ［処理概要］<BR>
     * １）トランザクションテーブルの１行を表す。株式顧客勘定明細Paramsの<BR>
     *    インスタンスを生成する。<BR>
     * <BR>
     * ２）株式顧客勘定明細Paramsにデフォルト値をセットする。<BR>
     * <BR>
     * ３）set株式顧客勘定明細Paramsメソッドをコールする。<BR>　@　@
     * 　@　@－株式顧客勘定明細Paramsのプロパティに約定情報をセットする。<BR>
     * 　@　@－計算サービスより、委託手数料計算・消費税を求める。<BR>
     * 　@　@　@ 売り約定の場合のみ、計算サービス.calc譲渡損益( )、<BR> 
     *         計算サービス.calc譲渡益税( )を行う。<BR>
     * 　@　@－受渡代金を求める。<BR>
     * 　@　@　@（買約定時）<BR>
     * 　@　@　@　@受渡代金 ＝０－（約定金額＋委託手数料＋消費税）<BR>
     * 　@　@　@（売約定時）<BR>
     * 　@　@　@　@受渡代金＝約定金額－（委託手数料＋消費税）<BR>
     * <BR>
     * ４）applyTo保有資産ポジション( )メソッドをコールする。<BR>
     * <BR>
     * ５）株式顧客勘定明細Paramsにセットされた情報でトランザクション<BR>
     *     データテーブルに１行挿入する。<BR>
     * <BR>
     * ６）一口約定処理をコールする。
     * @@param l_exec 約定
     * @@throws DataException
     * @@roseuid 401864E900CE
     */
    public void processCashBasedOrderExecution(EqTypeOrderExecution l_exec) throws DataException
    {
        final String STR_METHOD_NAME = "processCashBasedOrderExecution(" + "EqTypeOrderExecution)";

        log.entering(STR_METHOD_NAME);

        try
        {
            EqtypeFinTransactionParams l_trans = new EqtypeFinTransactionParams();
            setMarketOrderedTransDefaultValues(l_trans);
            setEqtypeFinTransactionParams(l_trans, l_exec);
            applyToAssetPosition(l_trans);
            dataManager.saveNewFinTransaction(l_trans);
            notifyGtl(l_trans);
            shareContractExecution(l_trans.getOrderUnitId(), l_trans.getAssetId(), false);
        }
        catch (WEB3BaseException l_wbex)
        {
            throw new WEB3BaseRuntimeException(l_wbex.getErrorInfo(), STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （set株式顧客勘定明細Params）<BR>
     * <BR>
     * 株式顧客勘定明細Paramsのプロパティに<BR>
     * 約定情報・委託手数料・消費税・譲渡益金額・譲渡益税・受渡代金をセットする。<BR>
     * 譲渡益金額・譲渡益税については、マイナス値の場合もそのままセットする。<BR>
     * （* 現物株式の出来通知サービスにて使用）<BR>
     * <BR>
     * １）拡張ポジションヘルパー.set約定情報To株式顧客勘定明細(株式顧客勘定明細,約定)<BR>
     * 　@　@をコールし、<BR>
     * 　@　@株式顧客勘定明細Paramsに約定オブジェクトの値をセットする。<BR>
     * <BR>
     * ２）約定オブジェクトより注文ID、注文単位ID、口座ID、補助口座IDを取得する。<BR>
     * <BR>
     * ３）注文単位IDを条件に注文単位オブジェクトを取得する。<BR>
     * 　@　@－拡張株式注文マネージャーオブジェクトを取得する。<BR>
     * 　@　@－拡張株式注文マネージャーオブジェクト.getOrderUnit()をコールする。<BR>
     * <BR>
     * ４）口座ID、補助口座IDより補助口座オブジェクトを取得する。<BR>
     * 　@　@－拡張アカウントマネージャーオブジェクトを取得する。<BR>
     * 　@　@－拡張アカウントマネージャ.getSubAccount()をコールする。<BR>
     * <BR>
     * ５）手数料オブジェクトを作成する。<BR>
     * <BR>
     * 　@　@株式計算サービス.create手数料( ２）で取得した注文ID)をコールする。<BR>
     * <BR>
     * ６）手数料オブジェクト.諸経費計算用代金に、約定代金をセットする。<BR>
     * <BR>
     * 　@　@・諸経費計算用代金<BR>
     * 　@　@　@⇒約定代金（約定数量×約定単価）※約定数量、約定単価は約定オブジェクトより取得する。<BR>
     * <BR>
     * ７）約定代金に対する、委託手数料を取得する。<BR>
     * 　@　@－株式計算サービスオブジェクトを取得する。<BR>
     * 　@　@－株式計算サービス.calc委託手数料をコールする。<BR>
     * 　@　@－手数料オブジェクト.get手数料金額をコールする。<BR>
     * <BR>
     * ８）委託手数料に対する、消費税を取得する。<BR>
     * 　@　@－株式計算サービス.calc消費税をコールする。<BR>
     * 　@　@　@※注文単位オブジェクト.発注日を基準の日時とする。<BR>
     * <BR>
     * ９）委託手数料／消費税を株式顧客勘定明細Paramsにセットする。<BR>
     * <BR>
     * １０）受渡代金を株式顧客勘定明細Paramsにセットする。<BR>
     * 　@　@[計算式]<BR>
     * 　@　@（買約定時）<BR>
     * 　@　@　@受渡代金 ＝０－（約定金額＋委託手数料＋消費税）<BR>
     * 　@　@（売約定時）<BR>
     * 　@　@　@受渡代金＝約定金額－（委託手数料＋消費税）<BR>
     * <BR>
     * １１）売約定時のみ、約定代金に対する譲渡益金額、譲渡益税額を取得し、<BR>
     * 　@　@　@株式顧客勘定明細Paramsにセットする。（マイナス値の場合もそのままセット）<BR>
     * <BR>
     * 　@　@－株式計算サービス.calc譲渡損益( )をコールする。戻り値を「譲渡益金額」にセット。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc譲渡損益(double, double, long, SubAccount, TaxTypeEnum)：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@金額：　@１０）で計算した受渡代金<BR>
     * 　@　@　@売数量：　@引数の約定.約定数量<BR>
     * 　@　@　@銘柄ID：　@引数の約定.銘柄ID<BR>
     * 　@　@　@補助口座：　@引数の約定.口座ID、補助口座IDに該当する<BR>
     * 　@　@　@　@　@　@　@　@　@補助口座オブジェクト<BR>
     * 　@　@　@税区分：　@引数の約定.注文単位IDに該当する注文単位.税区分<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * 　@　@－株式計算サービス.calc譲渡益税( )をコールする。戻り値を「譲渡益税額」にセット。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc譲渡益税(補助口座, TaxTypeEnum, double, Timestamp, TaxTypeEnum)：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@補助口座：　@取得した補助口座オブジェクト<BR>
     * 　@　@　@税区分：　@引数の約定.注文単位IDに該当する注文単位.税区分<BR>
     * 　@　@　@金額：　@計算した譲渡益金額（calc譲渡損益( )の戻り値）<BR>
     * 　@　@　@基準日：　@引数の約定.受渡日<BR>
     * 　@　@　@顧客税区分：　@取得した顧客税区分(*1)<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@(*1)取得した顧客税区分<BR>
     * 　@　@　@　@　@拡張アカウントマネージャ.get顧客(引数の株式顧客勘定明細Params.口座ID)で<BR>
     * 　@　@　@　@　@顧客オブジェクトを取得してから、<BR>
     * 　@　@　@　@　@顧客.get受渡日税区分(引数の約定.受渡日)により、顧客税区分を取得する。<BR>
     * <BR>
     * @@param l_trans 株式顧客勘定明細Params
     * @@param l_exec 約定
     * @@throws DataQueryException, DataNetworkException
     * @@roseuid 413D15BA0329
     */
    public void setEqtypeFinTransactionParams(
        EqtypeFinTransactionParams l_trans,
        EqTypeOrderExecution l_exec)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "setEqtypeFinTransactionParams(EqtypeFinTransactionParams, EqTypeOrderExecution)";
        String l_strMessage;

        log.entering(STR_METHOD_NAME);
        if (l_exec == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            // １）拡張ポジションヘルパー.setExecutionInfoToMarketOrderedTrans(株式顧客勘定明細,約定)
            // 　@　@をコールし、株式顧客勘定明細Paramsに約定オブジェクトの値をセットする。
            setExecutionInfoToMarketOrderedTrans(l_trans, l_exec);

            // ２）約定オブジェクトより注文ID、注文単位ID、口座ID、補助口座IDを取得する。
            EqtypeOrderExecutionRow l_execRow =
                (EqtypeOrderExecutionRow)l_exec.getDataSourceObject();
            long l_lngOrderID = l_execRow.getOrderId();
            long l_lngOrderUnitID = l_exec.getOrderUnitId();
            long l_lngAccountID = l_exec.getAccountId();
            long l_lngSubAccountID = l_exec.getSubAccountId();

            // ３）注文単位IDを条件に注文単位オブジェクトを取得する。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderMgr = (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_orderUnit = null;
            l_orderUnit = (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_lngOrderUnitID);
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            // ４）口座ID、補助口座IDより補助口座オブジェクトを取得する。
            SubAccount l_subAccount = null;
            l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_lngAccountID,
                    l_lngSubAccountID);

            // ５）手数料オブジェクトを作成する。
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(l_orderUnit, l_exec);

            // ６）手数料オブジェクト.諸経費計算用代金に、約定代金をセットする。
            double l_dblAmount = l_exec.getExecutionQuantity() * l_exec.getExecutionPrice();
            l_commission.setExpensesCalcAmount(l_dblAmount);

            // ７）約定代金に対する、委託手数料を取得する。
            WEB3EquityBizLogicProvider l_bizLogic = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            l_bizLogic.calcCommission(l_commission, l_subAccount);
            double l_dblCommissionFee = l_commission.getCommission();

            // ８）委託手数料に対する、消費税を取得する。
            Date l_datBizDate = null;
            try
            {
                l_datBizDate =
                    GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().parse(l_orderUnitRow.getBizDate());
            }
            catch (ParseException l_pe)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_pe.getMessage(),
                    l_pe);
            }
            double l_dblCommissionFeeTax = 0.0;
            l_dblCommissionFeeTax =
                l_bizLogic.calcSalesTax(
                    l_dblCommissionFee,
                    new Timestamp(l_datBizDate.getTime()),
                    l_subAccount);
            
            // ９）委託手数料／消費税を株式顧客勘定明細Paramsにセットする。
            l_trans.setCommissionFee(l_dblCommissionFee);
            l_trans.setCommissionFeeTax(l_dblCommissionFeeTax);
            
            // １０）受渡代金を株式顧客勘定明細Paramsにセットする。
			 double l_dblNetAmount = 0.0D;
			 if (l_orderUnit.getSide().equals(SideEnum.BUY))
			 {
				 // 買約定の場合
				l_dblNetAmount = 0.0D - (l_dblAmount + l_dblCommissionFee + l_dblCommissionFeeTax);

			 }
			 else if (l_orderUnit.getSide().equals(SideEnum.SELL))
			 {
				 // 売約定の場合
				l_dblNetAmount = l_dblAmount - (l_dblCommissionFee + l_dblCommissionFeeTax);
			 }
			 else
			 {
				 throw new RuntimeSystemException("FinTransactionType error!");
			 }
			 l_trans.setNetAmount(l_dblNetAmount);

            // １１）売約定時のみ、約定代金に対する譲渡益金額、譲渡益税額を取得し、
            // 　@　@　@株式顧客勘定明細Paramsにセットする。（マイナス値の場合もそのままセット）
            if (l_orderUnit.getSide().equals(SideEnum.SELL))
            {
                double l_dblCaptalGain =
                    l_bizLogic.calcEstimatedCapitalGain(
				        l_dblNetAmount,
                        l_exec.getExecutionQuantity(),
                        l_exec.getProduct().getProductId(),
                        l_subAccount,
                        l_orderUnitRow.getTaxType());
                
                WEB3GentradeMainAccount l_account =
                    (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_lngAccountID);
                TaxTypeEnum l_accountTaxType =
                    l_account.getDeliveryDateTaxType(l_exec.getDeliveryDate());
                double l_dblCaptalGainTax =
                    l_bizLogic.calcCapitalGainTax(
                        l_subAccount,
                        l_orderUnitRow.getTaxType(),
                        l_dblCaptalGain,
                        new Timestamp(l_exec.getDeliveryDate().getTime()),
                        l_accountTaxType);
                l_trans.setCapitalGain(l_dblCaptalGain);
                l_trans.setCapitalGainTax(l_dblCaptalGainTax);
                
                //1.9.6 get譲渡益有効状態(long, long, long, TaxTypeEnum, FinTransactionType)
                String l_strCapitalGainStatus = 
					l_bizLogic.getCapitalGainStatus(
						l_trans.getAccountId(),
						l_trans.getSubAccountId(), 
						l_trans.getProductId(),
						l_trans.getTaxType(),
						l_trans.getFinTransactionType());

                //1.9.7 setCapitalGainStatus()
                l_trans.setCapitalGainStatus(l_strCapitalGainStatus);
            }
        }
        catch (WEB3BaseException e)
        {
            l_strMessage = "set株式顧客勘定明細Paramsが異常終了しました";
            log.error(l_strMessage, e);
            throw new RuntimeSystemException(l_strMessage, e);
        }
        catch (NotFoundException e)
        {
            log.error(e.getMessage());
            throw new RuntimeSystemException("set株式顧客勘定明細Paramsが異常終了しました", e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （set保有資産Params）<BR>
     * <BR>
     * ［処理概要］<BR>
     * 株式顧客勘定明細Paramsより保有資産Paramsの各プロパティに値をセットする。<BR>
     * （setNewAssetParamsFromMarketTradedTransをオーバーライド）<BR>
     * <BR>
     * １）保有資産Paramの各属性に値をセットする。<BR>
     * <BR>
     * 　@・保有資産Params.税区分<BR>
     * 　@　@⇒   ・引数の株式顧客勘定明細Params.トランザクションタイプ<BR>
     * 　@　@　@　@　@　@＝現引取引（EQTYPE_SWAP_MARGIN_LONG）の場合は、<BR>
     * 　@　@　@　@　@　@引数の株式顧客勘定明細Params.注文単位IDに該当する<BR>
     * 　@　@　@　@　@　@注文単位オブジェクト.税区分（現引現渡） をセットする。<BR>
     * 　@　@　@　@　@・引数の株式顧客勘定明細Params.トランザクションタイプ<BR>
     * 　@　@　@　@　@　@≠現引取引（EQTYPE_SWAP_MARGIN_LONG）の場合は、<BR>
     * 　@　@　@　@　@　@引数の株式顧客勘定明細Params.税区分 をセットする。<BR>
     * 　@<BR>
     * 　@・保有資産Params.簿価（簿価単価計算用）<BR>
     * 　@　@⇒　@・以下の通りに値をセットする。<BR>
     * 　@　@　@　@　@　@　@　@------------------------------------------------------------------<BR>
     * 　@　@　@　@　@　@　@　@○引数の株式顧客勘定明細Params.トランザクションタイプ<BR>
     * 　@　@　@　@　@　@　@　@　@　@＝現引取引（EQTYPE_SWAP_MARGIN_LONG）の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@（１）this.calc受渡代金（現引現渡）( )コールにより、受渡代金を計算する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@-------------------------------------------<BR>
     * 　@　@　@　@　@　@　@　@　@　@＜calc受渡代金（現引現渡）( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@株式顧客勘定明細Params：　@<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@：　@引数の株式顧客勘定明細Paramsをそのままセットする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@-------------------------------------------<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@（２）calc受渡代金（現引現渡）( )の戻り値の符号を反転した値をセットする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@※calc受渡代金（現引現渡）( )の戻り値×（－１）<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@------------------------------------------------------------------<BR>
     * 　@　@　@　@　@　@　@　@○引数の株式顧客勘定明細Params.トランザクションタイプ<BR>
     * 　@　@　@　@　@　@　@　@　@　@≠現引取引（EQTYPE_SWAP_MARGIN_LONG）の場合<BR>
     * <BR>
     * 　@　@　@　@　@ 　@　@　@　@株式顧客勘定明細Params.受渡代金 の符号を反転した値をセットする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@※株式顧客勘定明細Params.受渡代金×（－１）<BR>
     * 　@　@　@　@　@　@　@　@------------------------------------------------------------------<BR>
     * <BR>
     * 　@・保有資産Params.口座ID<BR>
     * 　@　@⇒株式顧客勘定明細Params.口座IDをセットする。<BR>
     * <BR>
     * 　@・保有資産Params.補助口座ID<BR>
     * 　@　@⇒株式顧客勘定明細Params.補助口座IDをセットする。<BR>
     * <BR>
     * 　@・保有資産Params.銘柄タイプ<BR>
     * 　@　@⇒株式顧客勘定明細Params.銘柄タイプをセットする。<BR>
     * <BR>
     * 　@・保有資産Params.数量<BR>
     * 　@　@⇒株式顧客勘定明細Params.約定数量をセットする。<BR>
     * <BR>
     * 　@・保有資産Params.数量（簿価単価計算用）<BR>
     * 　@　@⇒株式顧客勘定明細Params.約定数量をセットする。<BR>
     * <BR>
     * 　@・保有資産Params.銘柄ID<BR>
     * 　@　@⇒株式顧客勘定明細Params.銘柄IDをセットする。
     * @@param l_assetParams 保有資産Params
     * @@param l_trans 株式顧客勘定明細Params>
     * @@throws RuntimeSystemException
     * @@roseuid 413D15BB0059l_orderUnitId
     */
    public void setNewAssetParamsFromMarketTradedTrans(
        AssetParams l_assetParams,
        EqtypeFinTransactionParams l_trans)
    {
        final String STR_METHOD_NAME = "setNewAssetParamsFromMarketTradedTrans(EqtypeAssetParams, EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_trans == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        try
        {
            long l_orderUnitId = l_trans.getOrderUnitId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            if (FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(l_trans.getFinTransactionType()))
            {
                OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_orderUnitId);
                EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                l_assetParams.setTaxType(l_orderUnitRow.getSwapTaxType());
                try
                {
                    l_assetParams.setBookValue(this.calcNetAmountSwap(l_trans) * -1.0D);
                }
                catch (WEB3BaseException l_ex)
                {
                    l_assetParams.setBookValue(0.0D);
                }
            }
            else
            {
                l_assetParams.setTaxType(l_trans.getTaxType());
                l_assetParams.setBookValue(l_trans.getNetAmount() * -1.0D);
            }
            l_assetParams.setAccountId(l_trans.getAccountId());
            l_assetParams.setSubAccountId(l_trans.getSubAccountId());
            l_assetParams.setProductType(l_trans.getProductType());
            l_assetParams.setQuantity(l_trans.getQuantity());
            l_assetParams.setQuantityForBookValue(l_trans.getQuantity());
            l_assetParams.setProductId(l_trans.getProductId());
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_nfe)
        {
            String l_strMessage = "注文単位テーブル検索 error";
            log.error(l_strMessage, l_nfe);
            log.error(l_nfe.getMessage());
            throw new RuntimeSystemException(l_strMessage, l_nfe);
        }
    }

    /**
     * （update保有資産Params）<BR>
     * <BR>
     * 取得した保有資産Paramsの値を更新する。<BR>
     * （void updateAssetParamsFromMarketTradedTrans(AssetParams aparams,<BR>
     * 　@EqtypeFinTransactionParams trans)のオーバーライド）<BR>
     * <BR>
     * ------------------------------------------------------------------------<BR>
     * （現物買約定、現引約定の時）<BR>
     * １）保有資産Params.簿価（簿価単価計算用） の値を変更する。<BR>
     * 　@　@（* 保有資産Params.税区分＝（"特定口座"または"特定口座かつ源泉徴収"）の場合は、<BR>
     * 　@　@　@　@無条件で更新対象とする。<BR>
     * 　@　@　@　@保有資産Params.税区分≠（"特定口座"または"特定口座かつ源泉徴収"）の場合は、<BR>
     * 　@　@　@　@　@－（保有資産Params.数量（簿価単価計算用）＞0<BR>
     * 　@　@　@　@　@　@　@かつ 保有資産Params.簿価（簿価単価計算用）＝0）の場合は更新対象外<BR>
     * 　@　@　@　@　@－上記以外の場合は更新対象<BR>
     * 　@　@　@　@とする。）<BR>
     * <BR>
     * 　@　@保有資産Params.簿価（簿価単価計算用）<BR>
     * 　@　@⇒　@・以下の通りに値を更新する。<BR>
     * 　@　@　@　@　@　@　@　@------------------------------------------------------------------<BR>
     * 　@　@　@　@　@　@　@　@○引数の株式顧客勘定明細Params.トランザクションタイプ<BR>
     * 　@　@　@　@　@　@　@　@　@　@＝現引取引（EQTYPE_SWAP_MARGIN_LONG）の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@（１）this.calc受渡代金（現引現渡）( )コールにより、受渡代金を計算する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@-------------------------------------------<BR>
     * 　@　@　@　@　@　@　@　@　@　@＜calc受渡代金（現引現渡）( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@株式顧客勘定明細Params：　@<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@：　@引数の株式顧客勘定明細Paramsをそのままセットする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@-------------------------------------------<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@（２）calc受渡代金（現引現渡）( )の戻り値の符号を反転した値を加算する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@※calc受渡代金（現引現渡）( )の戻り値×（－１）<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@------------------------------------------------------------------<BR>
     * 　@　@　@　@　@　@　@　@○引数の株式顧客勘定明細Params.トランザクションタイプ<BR>
     * 　@　@　@　@　@　@　@　@　@　@≠現引取引（EQTYPE_SWAP_MARGIN_LONG）の場合<BR>
     * <BR>
     * 　@　@　@　@　@ 　@　@　@　@株式顧客勘定明細Params.受渡代金 の符号を反転した値を加算する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@※株式顧客勘定明細Params.受渡代金×（－１）<BR>
     * 　@　@　@　@　@　@　@　@------------------------------------------------------------------<BR>
     * <BR>
     * ２）保有資産Params.数量の値を変更する。<BR>
     * <BR>
     * 　@　@保有資産Params.数量　@<BR>
     * 　@　@＝　@<BR>
     * 　@　@保有資産Params.数量 ＋ 株式顧客勘定明細Params.約定数量<BR>
     * <BR>
     * ３）保有資産Params.数量（簿価単価計算用）の値を変更する。<BR>
     * <BR>
     * 　@　@保有資産Params.数量（簿価単価計算用）　@<BR>
     * 　@　@＝　@<BR>
     * 　@　@保有資産Params.数量（簿価単価計算用） ＋ 株式顧客勘定明細Params.約定数量<BR>
     * <BR>
     * ------------------------------------------------------------------------<BR>
     * （現物売約定、現渡約定の時）<BR>
     * １）保有資産残数量チェックを行う。<BR>
     * <BR>
     * 　@　@保有資産残数量(*1) ＜ 0.0D の場合、<BR>
     * 　@　@「保有資産残数量チェックエラー」の例外（業務エラー）をthrowする。<BR>
     * 　@　@class: WEB3BaseRuntimeException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01931<BR>
     * <BR>
     * 　@　@(*1)保有資産残数量<BR>
     * 　@　@　@　@（保有資産Params.数量 ＋ 保有資産Params.売付不能数量）<BR>
     *            － 株式顧客勘定明細Params.約定数量<BR>
     * <BR>
     * ２）保有資産Params.数量の値を変更する。<BR>
     * <BR>
     * 　@　@保有資産Params.数量　@<BR>
     * 　@　@＝　@<BR>
     * 　@　@保有資産Params.数量　@－　@株式顧客勘定明細Params.約定数量<BR>
     * <BR>
     * 　@　@ただし、（保有資産Params.数量 ＞ 株式顧客勘定明細Params.約定数量）の場合は、<BR>
     * 　@　@足りない分の数量を 保有資産Params.売付不能数量 から減算する。<BR>
     * ------------------------------------------------------------------------<BR>
     * <BR>
     * @@param l_assetParams 保有資産Params
     * @@param l_trans 株式顧客勘定明細Params
     * @@roseuid 413D15BB00E5
     */
    public void updateAssetParamsFromMarketTradedTrans(
        AssetParams l_assetParams,
        EqtypeFinTransactionParams l_trans)
        throws RuntimeSystemException
    {
        final String STR_METHOD_NAME = "updateAssetParamsFromMarketTradedTrans(AssetParams, EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_trans == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // （現物買約定、現引約定の時）
        if (FinTransactionType.EQTYPE_EQUITY_BUY.equals(l_trans.getFinTransactionType()) ||
            FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(l_trans.getFinTransactionType()))
        {
            // １）保有資産Params.簿価（簿価単価計算用） の値を変更する。
            if (!TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_assetParams.getTaxType()) &&
                !TaxTypeEnum.SPECIAL.equals(l_assetParams.getTaxType()) &&
                (l_assetParams.getQuantityForBookValue() > 0.0D && l_assetParams.getBookValue() == 0.0D))
            {
                // 更新対象外
            }
            else
            {
                if (FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(l_trans.getFinTransactionType()))
                {
                    try
                    {
                        l_assetParams.setBookValue(
	                        l_assetParams.getBookValue() + (this.calcNetAmountSwap(l_trans) * -1.0D));
                    }
                    catch (WEB3BaseException l_ex)
                    {
                        l_assetParams.setBookValue(0.0D);
                    }
                }
                else
                {
                    l_assetParams.setBookValue(
                        l_assetParams.getBookValue() + (l_trans.getNetAmount() * -1.0D));
                }
            }
            // ２）保有資産Params.数量の値を変更する。
            l_assetParams.setQuantity(l_assetParams.getQuantity() + l_trans.getQuantity());
            // ３）保有資産Params.数量（簿価単価計算用）の値を変更する。
            l_assetParams.setQuantityForBookValue(l_assetParams.getQuantityForBookValue() + l_trans.getQuantity());
        }
        // （現物売約定、現渡の時）
        else if (FinTransactionType.EQTYPE_EQUITY_SELL.equals(l_trans.getFinTransactionType()) ||
                  FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(l_trans.getFinTransactionType()))
        {
            // １）保有資産残数量チェックを行う。
            double l_dblQuantity = (l_assetParams.getQuantity() + l_assetParams.getQuantityCannotSell())
                 - l_trans.getQuantity();
            if (l_dblQuantity < 0.0D)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01931,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            // ２）保有資産Params.数量の値を変更する。
            // 数量だけでは足りない場合は、売付不能数量から減算する。
            if (l_assetParams.getQuantity() >= l_trans.getQuantity())
            {
				l_assetParams.setQuantity(l_assetParams.getQuantity() - l_trans.getQuantity());
            }
            else
            {
				l_assetParams.setQuantity(0);
				l_assetParams.setQuantityCannotSell(
				    l_assetParams.getQuantityCannotSell()
				        - (l_trans.getQuantity() - l_assetParams.getQuantity()));
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get委託手数料合計)<BR>
     * ［処理概要］<BR>                                                          
     * 引数の株式顧客勘定明細Paramsリストの、委託手数料の合計を取得する。<BR>    
     * <BR>                                                               
     * １）　@株式顧客勘定明細Paramsリストに格納されている <BR>                
     * 　@　@　@株式顧客勘定明細Paramsオブジェクトを、一件ずつ取得する。<BR>
     * 　@-株式顧客勘定明細Paramsオブジェクトより、委託手数料の値を取得する。<BR>
     * 　@-取得した委託手数料を合計する。<BR>                                   
     *   <BR>                                                                    
     * ２）　@委託手数料の合計を返却する。 <BR>                                   
     * <BR>
     * @@param l_lisTrans 株式顧客勘定明細Paramsリスト
     * @@return double
     * @@roseuid 4143F6430386
     */
    public double getCommissionFeeAmount(List l_lisTrans)
    {
        final String STR_METHOD_NAME = "getCommissionFeeAmount(List)";
        log.entering(STR_METHOD_NAME);
        
        if (l_lisTrans == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        double l_dbCommissionFee = 0.0D;
        EqtypeFinTransactionParams l_row;
        ListIterator l_iterator = l_lisTrans.listIterator();
        while (l_iterator.hasNext())
        {
            l_row = (EqtypeFinTransactionParams)l_iterator.next();
            l_dbCommissionFee += l_row.getCommissionFee();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dbCommissionFee;
    }

    /**
     * (一口約定保有資産更新)<BR>
     * <BR>
     * ［処理概要］<BR>
     * １）資産IDより保有資産Paramを取得する。<BR>
     * <BR>
     * ２）保有資産Params.税区分＝"一般口座"かつ<BR>
     * 　@　@保有資産Param.数量（簿価単価計算用）＞0 かつ<BR>
     * 　@　@保有資産Param.簿価（簿価単価計算用）＝0の場合、<BR>
     * 　@　@何もせず処理をreturnする。 <BR>
     * <BR>
     * ３）上記以外の場合<BR>
     * <BR>
     * 　@３－１）保有資産Param簿価（簿価単価計算用） の値を更新する。<BR>
     * 　@　@　@※更新後簿価 = 更新前簿価－（計算前）諸経費合計＋（計算後）諸経費合計<BR>
     * 　@　@　@※諸経費（委託手数料＋消費税）<BR>
     * <BR>
     * 　@３－２）更新された保有資産Paramsを保有資産テーブルに保存する。<BR>
     * 　@　@　@※拡張データマネージャー.updateAssetByTrans()をコール<BR>
     * @@param l_lngAssetId 資産ID
     * @@param l_bdBefor 一口約定計算前の諸経費合計
     * @@param l_bdAfter 一口約定計算後の諸経費合計
     * @@throws DataNetworkException, DataQueryException
     * @@roseuid 401A33AB0371
     */
    public void updateShareContractAsset(long l_lngAssetId, double l_bdBefor, double l_bdAfter) throws DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = "updateShareContractAsset(long, double, double)";
        log.entering(STR_METHOD_NAME);

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            EqTypePositionManager l_positionManager = (EqTypePositionManager) l_tradingModule.getPositionManager();
            EqTypeAsset l_asset = null;
            l_asset = (EqTypeAsset) l_positionManager.getAsset(l_lngAssetId);
            AssetParams l_row = (AssetParams) l_asset.getDataSourceObject();
            if (TaxTypeEnum.NORMAL.equals(l_row.getTaxType()) && 
                l_row.getQuantityForBookValue() > 0 &&
                l_row.getBookValue() == 0)
            {
                return;
            }
            BigDecimal l_bdBookValue = new BigDecimal(l_asset.getBookValue());
            log.debug("更新前簿価（簿価単価計算用）：[" + l_bdBookValue.doubleValue() + "]");
            double l_dblBookValue = l_bdBookValue.doubleValue() - l_bdBefor + l_bdAfter;

            l_row.setBookValue(l_dblBookValue);
            l_row.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            dataManager.updateAssetByTrans(l_row);
        }
        catch (NotFoundException e)
        {
            String l_strMessage = "一口約定保有資産を更新できません";
            log.error(l_strMessage, e);
            throw new RuntimeSystemException(l_strMessage, e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (顧客勘定更新)<BR>
     * ［処理概要］<BR>
     * <BR>
     * 株式顧客勘定明細Paramsに対して起こった更新を、顧客勘定明細に反映させる。<BR>
     * （notifyGtlをオーバーライド）<BR>
     * <BR>
     * １）株式顧客勘定明細Paramsより、顧客勘定明細Paramsオブジェクトを作成する。<BR>
     * 　@　@（GeneralizedFinTransactionのコンストラクタをコール）<BR>
     * <BR>
     * ２）顧客勘定明細オブジェクトをGTL層に通知する。<BR>
     * 　@　@（汎用トランザクションマネージャー.notifyTransactionメソッド）<BR>
     * <BR>
     * ３）更新された受渡金額で顧客勘定明細レコードを更新する。
     * @@param l_trans 株式顧客勘定明細Params
     * @@roseuid 413D15BB026C
     */
    public void notifyGtl(EqtypeFinTransactionParams l_trans)
    {
        final String STR_METHOD_NAME = "notifyGtl(EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_trans == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        try
        {
            FinTransactionType l_finTransactionType = l_trans.getFinTransactionType();
            GeneralizedFinTransaction l_genFinTransaction =
                new GeneralizedFinTransaction(
                    l_trans.getFinTransactionId(),
                    l_trans.getAccountId(),
                    l_trans.getSubAccountId(),
                    l_trans.getDeliveryDate(),
                    l_finTransactionType,
                    l_trans.getNetAmount(),
                    "EqtypeFinTransaction productId("
                        + l_trans.getProductId()
                        + "), marketId("
                        + l_trans.getMarketId()
                        + "), quantity("
                        + l_trans.getQuantity()
                        + "), price("
                        + l_trans.getPrice()
                        + ")",
                    getTradingModule().getTradingModuleName(),
                    l_trans.getCreatedTimestamp());
            ProcessingResult l_result = m_finApp.getGeneralizedFinTransactionManager().notifyTransaction(l_genFinTransaction);

            if (l_result.isFailedResult())
            {
                throw new RuntimeSystemException("Method addTransaction failed:" + l_result.getErrorInfo());
            }

            String l_strWhere = " transaction_id=? ";
            Map l_mapChanges = new HashMap();

            l_mapChanges.put("net_amount", new Double(l_trans.getNetAmount()));
            Object[] l_objBindVars = { new Double(l_trans.getFinTransactionId())};
            Processors.getDefaultProcessor().doUpdateAllQuery(GenFinTransactionRow.TYPE, l_strWhere, l_objBindVars, l_mapChanges);
        }
        catch (DataNetworkException e)
        {
            String l_strMessage = "受渡金額を更新できません";
            log.error(l_strMessage, e);
            throw new RuntimeSystemException(l_strMessage, e);
        }
        catch (DataQueryException e)
        {
            String l_strMessage = "受渡金額を更新できません";
            log.error(l_strMessage, e);
            throw new RuntimeSystemException(l_strMessage, e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get委託手数料消費税合計)<BR>
     * ［処理概要］<BR>                                                                       
     * 引数の株式顧客勘定明細Paramsリストの、委託手数料消費税の合計を取得する。<BR>               
     *  <BR>                                                                             
     * １）　@株式顧客勘定明細Paramsリストに格納されている<BR>
     *       株式顧客勘定明細Paramsオブジェクトを一件ずつ取得する。<BR>                                                          
     * 　@-株式顧客勘定明細Paramsオブジェクトより、委託手数料消費税の値を取得する。<BR>            
     * 　@-取得した委託手数料消費税を合計する。<BR>                                            
     *  <BR>                                                        
     * ２）　@委託手数料消費税の合計を返却する。<BR>                                           
     * <BR>
     * @@param l_lisTrans 株式顧客勘定Paramsリスト
     * @@return double
     * @@roseuid 4143F64400F3
     */
    public double getSalesTaxAmount(List l_lisTrans)
    {
        final String STR_METHOD_NAME = "getSalesTaxAmount(List)";
        log.entering(STR_METHOD_NAME);
        
        if (l_lisTrans == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        double l_dbCommissionFeeTax = 0.0D;
        EqtypeFinTransactionParams l_row;
        ListIterator l_iterator = l_lisTrans.listIterator();
        while (l_iterator.hasNext())
        {
            l_row = (EqtypeFinTransactionParams)l_iterator.next();
            l_dbCommissionFeeTax += l_row.getCommissionFeeTax();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dbCommissionFeeTax;
    }

    /**
     * (get建株)<BR>
     * 【建株テーブル】から、引数の値を指定し該当する建株Paramsオブジェクトを<BR>
     * 取得し返却する。<BR>
     * （EqtypeContractParams getContract(EqtypeFinTransactionParams <BR>
     * tparams)<BR>
     * のオーバーライド）<BR>
     * <BR>
     * １）　@拡張データマネージャ.get建株(口座ID, 補助口座ID, 市場ID, 銘柄ID, 建区分, 建単価,<BR>
     * 　@　@　@ 建日, 期日, 税区分, 弁済区分, 弁済期限値, 当初建日)に委譲する。<BR>
     * <BR>
     * 　@　@　@上記メソッドのパラメータのうち、<BR>
     * 　@　@　@以下の項目には、当メソッドの引数（株式顧客勘定明細Params）<BR>
     * の同項目を設定する。<BR>
     * 　@　@　@　@　@　@口座ID<BR>
     * 　@　@　@　@　@　@補助口座ID<BR>
     * 　@　@　@　@　@　@市場ID<BR>
     * 　@　@　@　@　@　@銘柄ID<BR>
     * 　@　@　@　@　@　@税区分<BR>
     * <BR>
     * 　@　@　@以下の項目には、当メソッドの引数（株式顧客勘定明細Params）から、<BR>
     * 　@　@　@以下の通りに設定する。<BR>
     * 　@　@　@　@　@　@建区分：　@トランザクションタイプより設定(*1)。<BR>
     * 　@　@　@　@　@　@建単価：　@約定単価<BR>
     * 　@　@　@　@　@　@建日：　@約定IDに該当する約定オブジェクト(*2).約定日時のYYYYMMDD<BR>
     * 　@　@　@　@　@　@当初建日：　@建日に同じ<BR>
     * <BR>
     * 　@　@　@以下の項目には、該当する注文単位オブジェクト(*3)の同項目を設定：<BR>
     * 　@　@　@　@　@　@弁済区分<BR>
     * 　@　@　@　@　@　@弁済期限値<BR>
     * <BR>
     * 　@　@　@以下の項目には、this.get建株期日( )の戻り値を設定：<BR>
     * 　@　@　@　@　@　@期日<BR>
     * 　@　@　@------------------------------------------------------<BR>
     * 　@　@　@＜get建株期日(Date, double)：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@建日：　@約定IDに該当する約定オブジェクト(*2).約定日時のYYYYMMDD<BR>
     * 　@　@　@弁済期限値：　@該当する注文単位オブジェクト(*3).弁済期限値<BR>
     * 　@　@　@------------------------------------------------------<BR>
     * 
     * 　@　@　@(*1)ContractTypeEnum.getContractType(<BR>
     * 　@　@　@　@　@　@引数の株式顧客勘定明細Params.トランザクションタイプ)<BR>
     * <BR>
     * 　@　@　@(*2)拡張株式注文マネージャ.getOrderExecution(<BR>
     * 　@　@　@当メソッドの引数（株式顧客勘定明細Params.約定ID) により<BR>
     * 　@　@　@取得した約定オブジェクトを使用する。<BR>
     * <BR>
     * 　@　@　@(*3)拡張株式注文マネージャ.getOrderUnit(<BR>
     * 　@　@　@当メソッドの引数（株式顧客勘定明細Params.注文単位ID) により<BR>
     * 　@　@　@取得した注文単位オブジェクトを使用する。<BR>
     * <BR>
     * ２）　@委譲メソッドの戻り値オブジェクトをそのまま返却する。
     * @@param l_eqtypeFinTransactionParams (株式顧客勘定明細Params)<BR>
     * 　@　@　@株式顧客勘定明細Paramsオブジェクト。
     * @@return EqtypeContractParams
     * @@throws DataException
     * @@roseuid 40A96955013B
     */
    protected EqtypeContractParams getContract(EqtypeFinTransactionParams l_eqtypeFinTransactionParams) throws DataException
    {
        final String STR_METHOD_NAME = "getContract";
        log.entering(STR_METHOD_NAME);
        
        if (l_eqtypeFinTransactionParams == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        
        try
        {
            long l_accountId = l_eqtypeFinTransactionParams.getAccountId();
            long l_subAccountId = l_eqtypeFinTransactionParams.getSubAccountId();
            long l_marketId = l_eqtypeFinTransactionParams.getMarketId();
            long l_productId = l_eqtypeFinTransactionParams.getProductId();
            TaxTypeEnum l_taxType = l_eqtypeFinTransactionParams.getTaxType();
            ContractTypeEnum l_contractType = ContractTypeEnum.getContractType(l_eqtypeFinTransactionParams.getFinTransactionType());
            double l_price = l_eqtypeFinTransactionParams.getPrice();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            
            //(*2)拡張株式注文マネージャ.getOrderExecution(
            //当メソッドの引数（株式顧客勘定明細Params.約定ID) により
            //取得した約定オブジェクトを使用する。
            OrderExecution l_orderExecution = l_orderManager.getOrderExecution(l_eqtypeFinTransactionParams.getOrderExecutionId());
            Date l_datexecutiontimestamp = l_orderExecution.getExecutionTimestamp();
            
            //(*3)拡張株式注文マネージャ.getOrderUnit(
            //当メソッドの引数（株式顧客勘定明細Params.注文単位ID) により
            //取得した注文単位オブジェクトを使用する。
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_eqtypeFinTransactionParams.getOrderUnitId());
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            String l_strRepaymentType = l_orderUnitRow.getRepaymentType();
            int l_intReparmentNum = l_orderUnitRow.getRepaymentNum();
            Date l_datCloseDate = this.getContractCloseDate(l_datexecutiontimestamp, l_intReparmentNum);
            WEB3EquityPersistentDataManager l_dataManager = (WEB3EquityPersistentDataManager) this.getPersistenceManager();
            
            //拡張データマネージャ.get建株(口座ID, 補助口座ID, 市場ID, 銘柄ID, 建区分, 建単価,
            //建日, 期日, 税区分, 弁済区分, 弁済期限値, 当初建日)に委譲する。
			log.exiting(STR_METHOD_NAME);
            return l_dataManager.getContract(
                l_accountId,
                l_subAccountId,
                l_marketId,
                l_productId,
                l_contractType,
                l_price,
                l_datexecutiontimestamp,
                l_datCloseDate,
                l_taxType,
                l_strRepaymentType,
                l_intReparmentNum,
                l_datexecutiontimestamp);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        catch (WEB3BaseException l_wbe)
        {
            String l_strMessage = "株式顧客勘定明細Paramsが見つかりません";
            log.error(l_strMessage, l_wbe);
            throw new RuntimeSystemException(l_strMessage, l_wbe);
        }
    }

    /**
     * （set約定情報To株式顧客勘定明細）<BR>
     * <BR>
     * 約定データ及び注文データより、株式顧客勘定明細Paramsに値をセットする。<BR>
     * （void setExecutionInfoToMarketOrderedTrans(<BR>
     * 　@　@EqtypeFinTransactionParams,<BR>
     * 　@　@EqTypeOrderExecution,EqtypeOrderUnitRow)のオーバーライド）<BR>
     * <BR>
     * １）　@引数の株式顧客勘定明細Paramsの各プロパティをセットする。<BR>
     * <BR>
     * 口座ID：　@引数の約定.口座ID<BR>
     * 補助口座ID：　@引数の約定.補助口座ID<BR>
     * 銘柄ID：　@引数の約定.銘柄ID<BR>
     * 銘柄タイプ：　@引数の約定.銘柄タイプ<BR>
     * 市場ID：　@引数の約定.市場ID<BR>
     * 注文ID：　@引数の注文単位Row.注文ID<BR>
     * 注文単位ID：　@引数の約定.注文単位ID<BR>
     * 約定ID：　@引数の約定.約定ID<BR>
     * トランザクションタイプ：　@引数の注文単位Row.getOrderType().getFinTransactionType()<BR>
     * トランザクションカテゴリ：　@<BR>
     * 　@　@引数の注文単位Row.getOrderType().getFinTransactionType().getCateg()<BR>
     * 税区分：　@引数の注文単位Row.税区分<BR>
     * 受渡日：　@GtlUtils.truncateDate(引数の約定.受渡日)<BR>
     * <BR>
     * ２）　@引数の株式顧客勘定明細Paramsの約定単価、約定数量、建株の受渡金額 をセットする。<BR>
     * <BR>
     * 約定単価：　@引数の約定.約定単価<BR>
     * 約定数量：　@引数の約定.約定数量<BR>
     * 建株の受渡金額：　@引数の株式顧客勘定明細Params.トランザクションカテゴリ<BR>
     * 　@　@　@＝現物取引（EQTYPE_ASSET） または新規建（EQTYPE_OPEN_MARGIN）の場合は、0。<BR>
     * 　@　@　@引数の株式顧客勘定明細Params.トランザクションカテゴリ<BR>
     * 　@　@　@≠新規建（EQTYPE_OPEN_MARGIN）の場合は、<BR>
     * 　@　@　@引数の約定.約定単価 × 引数の約定.約定数量。<BR>
     * <BR>
     * ３）　@引数の株式顧客勘定明細Params.トランザクションカテゴリ＝現物取引（EQTYPE_ASSET）<BR>
     * 　@　@　@の場合は、<BR>
     * 　@　@　@処理を終了する。<BR>
     * 　@　@　@※現物の場合、委託手数料の計算は set株式顧客勘定明細Params( )の中で、<BR>
     * 　@　@　@※一口約定計算は 株式約定残高更新処理( )の中で、<BR>
     * 　@　@　@※それぞれ実行されるため、当メソッドでは処理不要。<BR>
     * <BR>
     * 　@　@　@引数の株式顧客勘定明細Params.トランザクションカテゴリ＝現引現渡<BR>
     * 　@　@　@（EQTYPE_SWAP_MARGIN）の場合は、<BR>
     * 　@　@　@引数の株式顧客勘定明細Params.委託手数料、委託手数料消費税に0をセットし、<BR>
     * 　@　@　@処理を終了する。<BR>
     * <BR>
     * 　@　@　@引数の株式顧客勘定明細Params.トランザクションカテゴリ≠<BR>
     * 　@　@　@（現物取引（EQTYPE_ASSET）、現引現渡（EQTYPE_SWAP_MARGIN））の場合は、<BR>
     * 　@　@　@以降の処理を続行する。<BR>
     * <BR>
     * ４）　@株式顧客勘定明細への委託手数料／委託手数料消費税 のセットを行う。<BR>
     * 　@　@　@また、新規建注文に対する約定の場合は、既約定分の株式顧客勘定明細の、<BR>
     * 　@　@　@受渡代金、譲渡益金額、譲渡益税額の再計算を行う。<BR>
     * 　@　@　@※再計算により、委託手数料／委託手数料消費税 の金額の変動を反映する。<BR>
     * <BR>
     * ４－１）　@既約定分の株式顧客勘定明細ParamsのListを取得する。<BR>
     * <BR>
     * 　@　@拡張データマネージャ.get株式顧客勘定明細ListBy注文単位(引数の約定.注文単位ID)<BR>
     * 　@　@コールにより取得する。<BR>
     * <BR>
     * 以下、（返済注文に対する約定の場合 or 初回約定の場合（４－２））と<BR>
     * （既約定がある場合（４－３）以降）とで、処理分岐する。<BR>
     * <BR>
     * ４－２）　@以下の条件のいずれかに該当する場合は、<BR>
     * 　@　@　@　@　@今回約定した約定金額に対する委託手数料、委託手数料消費税を計算し、<BR>
     * 　@　@　@　@　@引数の株式顧客勘定明細Paramsにセット後、returnする。<BR>
     * <BR>
     * 　@　@　@　@　@--------------------------------------------------------<BR>
     * 　@　@　@　@　@＜条件＞<BR>
     * <BR>
     * 　@　@　@　@　@・引数の株式顧客勘定明細Params.トランザクションカテゴリ＝返済取引<BR>
     * 　@　@　@　@　@　@（EQTYPE_CLOSE_MARGIN）である。<BR>
     * 　@　@　@　@　@・引数の株式顧客勘定明細Params.トランザクションカテゴリ＝新規建取引<BR>
     * 　@　@　@　@　@　@（EQTYPE_OPEN_MARGIN）、<BR>
     * 　@　@　@　@　@かつ　@４－１）で取得したList.isEmpty( )==true（＝初回約定）である。<BR>
     * 　@　@　@　@　@--------------------------------------------------------<BR>
     * <BR>
     * ４－２－１）　@株式計算サービス.create手数料( )により、<BR>
     * 　@　@　@　@　@　@　@　@約定の付いた注文単位オブジェクトをもとに手数料オブジェクトを作成する。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜create手数料( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@注文ID：　@引数の注文単位Row.注文ID<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ４－２－２）　@手数料オブジェクトの、プロパティ「諸経費計算用代金」をセットする。<BR>
     * <BR>
     * 　@　@----------------------------------------------------------<BR>
     * 　@　@４－１）で取得したList.isEmpty( )==true（＝初回約定）の場合：<BR>
     * 　@　@　@　@　@引数の株式顧客勘定明細Params.約定単価<BR>
     * 　@　@　@　@　@× 引数の株式顧客勘定明細Params.約定数量<BR>
     * <BR>
     * 　@　@上記以外の場合：<BR>
     * 　@　@　@　@　@ （引数の株式顧客勘定明細Params.約定単価<BR>
     * 　@　@　@　@　@　@× 引数の株式顧客勘定明細Params.約定数量）<BR>
     * 　@　@　@　@　@ ＋ （４－１）で取得した既約定分の株式顧客勘定明細ParamsのListの全要素の<BR>
     * 　@　@　@　@　@　@　@　@（約定単価×約定数量）のSUM値）<BR>
     * 　@　@----------------------------------------------------------<BR>
     * <BR>
     * ４－２－３）　@株式計算サービス.calc委託手数料( )により委託手数料を取得し、<BR>
     * 　@　@　@　@　@　@　@　@引数の株式顧客勘定明細Params.委託手数料にセットする。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc委託手数料( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@手数料：　@作成した手数料オブジェクト<BR>
     * 　@　@　@補助口座：　@引数の約定.口座ID、補助口座IDに該当する補助口座オブジェクト<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@取得した委託手数料を、以下の通りに<BR>
     * 　@　@　@引数の株式顧客勘定明細Params.委託手数料にセットする。<BR>
     * 　@　@----------------------------------------------------------<BR>
     * 　@　@４－１）で取得したList.isEmpty( )==true（＝初回約定）の場合：<BR>
     * 　@　@　@　@　@calc委託手数料( )コール後の手数料.手数料金額 をそのままセット。<BR>
     * <BR>
     * 　@　@上記以外の場合：<BR>
     * 　@　@　@　@　@ calc委託手数料( )コール後の手数料.手数料金額<BR>
     * 　@　@　@　@　@ － （４－１）で取得した既約定分の株式顧客勘定明細ParamsのListの全要素の<BR>
     * 　@　@　@　@　@　@　@　@　@委託手数料のSUM値）<BR>
     * 　@　@----------------------------------------------------------<BR>
     * <BR>
     * ４－２－４）　@株式計算サービス.calc消費税( )により委託手数料消費税を取得し、<BR>
     * 　@　@　@　@　@　@　@　@引数の株式顧客勘定明細Params.委託手数料消費税にセットする。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc消費税( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@金額：　@calc委託手数料( )コール後の手数料.手数料金額<BR>
     * 　@　@　@基準日：　@引数の注文単位Row.発注日<BR>
     * 　@　@　@補助口座：　@引数の約定.口座ID、補助口座IDに該当する補助口座オブジェクト<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@取得した委託手数料消費税を、以下の通りに<BR>
     * 　@　@　@引数の株式顧客勘定明細Params.委託手数料消費税にセットする。<BR>
     * 　@　@----------------------------------------------------------<BR>
     * 　@　@４－１）で取得したList.isEmpty( )==true（＝初回約定）の場合：<BR>
     * 　@　@　@　@　@calc消費税( )の戻り値をそのままセット。<BR>
     * <BR>
     * 　@　@上記以外の場合：<BR>
     * 　@　@　@　@　@ calc消費税( )の戻り値<BR>
     * 　@　@　@　@　@ － （４－１）で取得した既約定分の株式顧客勘定明細ParamsのListの全要素の<BR>
     * 　@　@　@　@　@　@　@　@　@委託手数料消費税のSUM値）<BR>
     * 　@　@----------------------------------------------------------<BR>
     * <BR>
     * ４－２－５）　@returnする。<BR>
     * <BR>
     * ４－３）　@４－２）以外の場合<BR>
     * 　@　@　@　@　@（新規建注文に対する約定、<BR>
     * 　@　@　@　@　@　@かつ　@４－１）で取得したList.isEmpty( )==false（≠初回約定）の場合）、<BR>
     * 　@　@　@　@　@４－１）で取得したListに、今回約定分の株式顧客勘定明細Params<BR>
     * 　@　@　@　@　@（＝引数の株式顧客勘定明細Params）をaddする。<BR>
     * <BR>
     * ４－４）　@出来通知の来た注文の株式顧客勘定明細Paramsの各レコードに対する、<BR>
     * 　@　@　@　@　@委託手数料、委託手数料消費税の按分値を取得する。<BR>
     * <BR>
     * 　@　@株式計算サービス.calc手数料（按分）( )をコールする。<BR>
     *  　@　@引数には、４－３）で作成したListをtoArray( )で配列に変換して設定する。<BR>
     * <BR>
     * ４－５）　@４－４）の戻り値ConsolidatedCommissionInfoを、<BR>
     * 　@　@　@　@　@４－３）で作成したListの各要素の委託手数料、委託手数料消費税にセットする。<BR>
     * 　@　@　@　@　@既約定分の株式顧客勘定明細Paramsについては、<BR>
     * 　@　@　@　@　@【トランザクションテーブル（株式顧客勘定明細）】のupdateを行う。<BR>
     * <BR>
     * 　@　@　@以下、４－３）で作成したListの要素（index）分Loopする。<BR>
     * <BR>
     * ↓↓↓START LOOP↓↓↓<BR>
     * <BR>
     * ４－５－１）　@委託手数料、委託手数料消費税、更新日付をセットする。<BR>
     * <BR>
     * 　@　@　@　@委託手数料：　@ConsolidatedCommissionInfo.getCommission(index)<BR>
     * 　@　@　@　@委託手数料消費税：　@ConsolidatedCommissionInfo.getSalesTax(index)<BR>
     * 　@　@　@　@更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * ４－５－２）　@　@株式顧客勘定明細Paramsが既約定分のデータである場合のみ、<BR>
     * 　@　@　@　@　@　@　@　@【トランザクションテーブル（株式顧客勘定明細）】のupdateを行う。<BR>
     * <BR>
     * 　@　@　@　@拡張データマネージャ.updateFinTransaction(株式顧客勘定明細Params(index), <BR>
     * 　@　@　@　@更新対象プロパティMap)により、updateを行う。<BR>
     * 　@　@　@　@※更新対象プロパティMapには、４－５－１）でセットしたプロパティ及び値をセットする。<BR>
     * <BR>
     * ４－５－３）　@株式顧客勘定明細Paramsが既約定分のデータである場合のみ、<BR>
     * 　@　@　@　@　@　@　@　@【トランザクションテーブル（株式顧客勘定明細）】のupdateをGTL層に通知する。<BR>
     * <BR>
     * 　@　@　@　@this.顧客勘定更新(株式顧客勘定明細Params(index))により<BR>
     * 　@　@　@　@GTL層への通知を行う。<BR>
     * <BR>
     * ↑↑↑END  LOOP↑↑↑<BR>
     * <BR>
     * ４－６）　@returnする。<BR>
     * <BR>
     * @@param l_finTransactionParams 株式顧客勘定明細Params。
     * @@param l_orderExecution 約定オブジェクト。
     * @@param l_orderUnitRow 注文単位Rowオブジェクト。
     * @@throws DataQueryException
     * @@throws DataNetworkException 
     * @@roseuid 40CEE03C028A
     */
    protected void setExecutionInfoToMarketOrderedTrans(
        EqtypeFinTransactionParams l_finTransactionParams,
        EqTypeOrderExecution l_orderExecution,
        EqtypeOrderUnitRow l_orderUnitRow)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "setExecutionInfoToMarketOrderedTrans(EqtypeFinTransactionParams, EqTypeOrderExecution, EqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        if (l_orderExecution == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        try
        {
            //１）　@引数の株式顧客勘定明細Paramsの各プロパティをセットする。
            //口座ID：　@引数の約定.口座ID
            l_finTransactionParams.setAccountId(l_orderExecution.getAccountId());

            //補助口座ID：　@引数の約定.補助口座ID
            l_finTransactionParams.setSubAccountId(l_orderExecution.getSubAccountId());

            //銘柄ID：　@引数の約定.銘柄ID
            l_finTransactionParams.setProductId(l_orderExecution.getProduct().getProductId());

            //銘柄タイプ：　@引数の約定.銘柄タイプ
            l_finTransactionParams.setProductType(l_orderExecution.getProduct().getProductType());

            //市場ID：　@引数の約定.市場ID
            l_finTransactionParams.setMarketId(l_orderExecution.getMarketId());

            //注文ID：　@引数の注文単位Row.注文ID
            l_finTransactionParams.setOrderId(l_orderUnitRow.getOrderId());

            //注文単位ID：　@引数の約定.注文単位ID
            l_finTransactionParams.setOrderUnitId(l_orderExecution.getOrderUnitId());

            //約定ID：　@引数の約定.約定ID
            l_finTransactionParams.setOrderExecutionId(l_orderExecution.getOrderExecutionId());

            //トランザクションタイプ：　@引数の注文単位Row.getOrderType().getFinTransactionType()
            l_finTransactionParams.setFinTransactionType(l_orderUnitRow.getOrderType().toFinTransactionType());

            //トランザクションカテゴリ：引数の注文単位Row.getOrderType().getFinTransactionType().getCateg()
            l_finTransactionParams.setFinTransactionCateg(l_orderUnitRow.getOrderType().toFinTransactionType().toFinTransactionCateg());
            
            //税区分：　@引数の注文単位Row.税区分
            l_finTransactionParams.setTaxType(l_orderUnitRow.getTaxType());

            //受渡日：　@GtlUtils.truncateDate(引数の約定.受渡日)
            l_finTransactionParams.setDeliveryDate(GtlUtils.truncateDate(l_orderExecution.getDeliveryDate()));

            //２）　@引数の株式顧客勘定明細Paramsの約定単価、約定数量、建株の受渡金額 をセットする。
            //約定単価：　@引数の約定.約定単価
            double l_dblexecutionPrice = l_orderExecution.getExecutionPrice();
            l_finTransactionParams.setPrice(l_dblexecutionPrice);

            //約定数量：　@引数の約定.約定数量
            double l_dblexecutionQuantity = l_orderExecution.getExecutionQuantity();
            l_finTransactionParams.setQuantity(l_dblexecutionQuantity);

            //建株の受渡金額：　@引数の株式顧客勘定明細Params.トランザクションカテゴリ
            //＝新規建（EQTYPE_OPEN_MARGIN）の場合は、0。
            //引数の株式顧客勘定明細Params.トランザクションカテゴリ
            //≠新規建（EQTYPE_OPEN_MARGIN）の場合は、
            //引数の約定.約定単価 × 引数の約定.約定数量。
            double l_dblDeliveryDate = 0D;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityBizLogicProvider l_bizLogicProvider = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            
            WEB3EquityPersistentDataManager l_dataManager = (WEB3EquityPersistentDataManager) this.getPersistenceManager();
            if (FinTransactionCateg.EQTYPE_ASSET.equals(l_finTransactionParams.getFinTransactionCateg()) ||
                FinTransactionCateg.EQTYPE_OPEN_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg()))
            {
                l_dblDeliveryDate = 0D;
            }
            else
            {
                l_dblDeliveryDate = l_dblexecutionPrice * l_dblexecutionQuantity;
            }
            l_finTransactionParams.setImportedPaidValue(l_dblDeliveryDate);

            //３）　@引数の株式顧客勘定明細Params.トランザクションカテゴリ＝現物取引（EQTYPE_ASSET）
            //の場合は処理を終了する。
            //引数の株式顧客勘定明細Params.トランザクションカテゴリ＝（EQTYPE_SWAP_MARGIN）の場合は、
            //引数の株式顧客勘定明細Params.委託手数料、委託手数料消費税に0をセットし、
            //処理を終了する。
            if (FinTransactionCateg.EQTYPE_ASSET.equals(l_finTransactionParams.getFinTransactionCateg()))
            {
                return;
            }
            if (FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg()))
            {
                l_finTransactionParams.setCommissionFee(0);
                l_finTransactionParams.setCommissionFeeTax(0);
                return;
                
            }
            //　@　@　@引数の株式顧客勘定明細Params.トランザクションカテゴリ≠現引現渡
            //（EQTYPE_SWAP_MARGIN）の場合は、
            //以降の処理を続行する。
            //株式顧客勘定明細への委託手数料／委託手数料消費税 のセットを行う。
            //また、新規建注文に対する約定の場合は、既約定分の株式顧客勘定明細の、
            //受渡代金、譲渡益金額、譲渡益税額の再計算を行う。
            // ※再計算により、委託手数料／委託手数料消費税 の金額の変動を反映する。

            //４－１）　@既約定分の株式顧客勘定明細ParamsのListを取得する。
            List l_lstFinTransaction = l_dataManager.getFinTransactionListByOrderUnit(l_orderExecution.getOrderUnitId());

            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnitRow.getOrderUnitId());
            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(l_orderUnit, l_orderExecution);
            double l_dblCalcAmount = 0D;

            //・引数の株式顧客勘定明細Params.トランザクションカテゴリ＝返済取引
            //（EQTYPE_CLOSE_MARGIN）である。
            //・引数の株式顧客勘定明細Params.トランザクションカテゴリ＝新規建取引
            //（EQTYPE_OPEN_MARGIN）、
            //かつ　@４－１）で取得したList.isEmpty( )==true（＝初回約定）である。
            if (FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg())
                || (FinTransactionCateg.EQTYPE_OPEN_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg()) && l_lstFinTransaction.isEmpty()))
            {
                WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(l_orderExecution.getAccountId(), l_orderExecution.getSubAccountId());
                
                //取得したList.isEmpty( )==true（＝初回約定）の場合：<BR>
                //引数の株式顧客勘定明細Params.約定単価<BR>
                // 　@　@　@　@　@× 引数の株式顧客勘定明細Params.約定数量<BR>
                //  上記以外の場合：<BR>
                //　@　@　@ （引数の株式顧客勘定明細Params.約定単価<BR>
                //     　@　@　@× 引数の株式顧客勘定明細Params.約定数量）<BR>
                //    　@　@　@ ＋ （４－１）で取得した既約定分の株式顧客勘定明細ParamsのListの全要素の
                //           (約定単価×約定数量）のSUM値）
                l_dblCalcAmount = l_finTransactionParams.getPrice() * l_finTransactionParams.getQuantity();
                
                if (l_lstFinTransaction != null && !l_lstFinTransaction.isEmpty())
                {
                    int l_intFinTransactionLength = l_lstFinTransaction.size();
                    for (int i = 0; i < l_intFinTransactionLength; i++)
                    {
                        EqtypeFinTransactionParams l_eqtypeFinTransactionParams = (EqtypeFinTransactionParams)l_lstFinTransaction.get(i);
                        l_dblCalcAmount += l_eqtypeFinTransactionParams.getPrice() * l_eqtypeFinTransactionParams.getQuantity();
                    }
                }
                //手数料オブジェクトの、プロパティ「諸経費計算用代金」をセットする。
                l_commission.setExpensesCalcAmount(l_dblCalcAmount);
                
                //株式計算サービス.calc委託手数料( )により委託手数料を取得                
                l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
                
                //引数の株式顧客勘定明細Params.委託手数料にセットする。
                //○初回約定（isEmpty( )==true）の場合
                //    calc委託手数料( )コール後の手数料.手数料金額 をそのままセット。
                //○上記以外の場合（返済注文に対する約定で、既約定あり）
                //（calc委託手数料( )コール後の手数料.手数料金額
                //  － get株式顧客勘定明細ListBy注文単位( )で取得した
                //  全ての株式顧客勘定明細.委託手数料のSUM値）をセット。
                double l_dblCommission = 0D;
                l_dblCommission = l_commission.getCommission();
                if (l_lstFinTransaction != null && !l_lstFinTransaction.isEmpty())
                {
                    int l_intFinTransactionLength = l_lstFinTransaction.size();
                    for (int i = 0; i < l_intFinTransactionLength; i++)
                    {                        
                        EqtypeFinTransactionParams l_eqtypeFinTransactionParams = (EqtypeFinTransactionParams)l_lstFinTransaction.get(i);
                        l_dblCommission -= l_eqtypeFinTransactionParams.getCommissionFee();
                    }
                }
                
                double l_dblSalesTax = l_bizLogicProvider.calcSalesTax(
                    l_commission.getCommission(), 
                    new Timestamp(WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd").getTime()), 
                    l_subAccount);
                
                if (l_lstFinTransaction != null)
                {
                    int l_intFinTransactionLength = l_lstFinTransaction.size();
                    for (int i = 0; i < l_intFinTransactionLength; i++)
                    {
                        EqtypeFinTransactionParams l_eqtypeFinTransactionParams = (EqtypeFinTransactionParams)l_lstFinTransaction.get(i);
                        l_dblSalesTax -= l_eqtypeFinTransactionParams.getCommissionFeeTax();
                    }
                }
                
                l_finTransactionParams.setNetAmount(l_dblCalcAmount);
                l_finTransactionParams.setCommissionFee(l_dblCommission);
                l_finTransactionParams.setCommissionFeeTax(l_dblSalesTax);
                
                log.exiting(STR_METHOD_NAME);
                return;
            }

            //（新規建注文に対する約定、
            //かつ　@４－１）で取得したList.isEmpty( )==false（≠初回約定）の場合）、
            //４－１）で取得したListに、今回約定分の株式顧客勘定明細Params
            //（＝引数の株式顧客勘定明細Params）をaddする。
            else
            {
                if (l_lstFinTransaction.isEmpty() == false)
                {
                    l_lstFinTransaction.add(l_finTransactionParams);
                }
                int l_lstFintransactionLength = l_lstFinTransaction.size();
                if (l_lstFintransactionLength > 0)
                {
                    EqtypeFinTransactionRow[] l_finTransaction =
                        new EqtypeFinTransactionRow[l_lstFintransactionLength];
                    l_lstFinTransaction.toArray(l_finTransaction);
                    
                    //株式計算サービス.calc手数料（按分）( )をコールする
                    ConsolidatedCommissionInfo l_commissionInfo =
                        l_bizLogicProvider.calcCommission(l_finTransaction);
    
                    for (int i = 0; i < l_lstFintransactionLength; i++)
                    {
                        //委託手数料、委託手数料消費税、更新日付をセットする。                            
                        EqtypeFinTransactionParams l_params =
                            (EqtypeFinTransactionParams)l_lstFinTransaction.get(i);
                        l_params.setCommissionFee(l_commissionInfo.getCommission(i));
                        l_params.setCommissionFeeTax(l_commissionInfo.getSalesTax(i));
                        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
    
                        //拡張データマネージャ.updateFinTransaction(株式顧客勘定明細Params(index), 
                        //更新対象プロパティMap)によりupdateを行う。
                        HashMap l_map = new HashMap();
                        l_map.put("commission_fee", new Double(l_params.getCommissionFee()));
                        l_map.put("commission_fee_tax", new Double(l_params.getCommissionFeeTax()));
                        l_map.put("last_updated_timestamp", l_params.getLastUpdatedTimestamp());
                        l_dataManager.updateFinTransaction(l_params, l_map);
    
                        //this.顧客勘定更新(株式顧客勘定明細Params(index))によりGTL層への通知を行う。
                        if (l_params != l_finTransactionParams)
                        {
                            this.notifyGtl(l_params);
                        }
                    }
                }
            }
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_wbe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_wbe.getMessage(), l_wbe);
        }
        catch (NotFoundException l_nfex)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfex);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfex.getMessage(), l_nfex);
        }
        catch (DataException l_de)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_de);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_de.getMessage(), l_de);
        }
    }

    /**
     * (takeIn諸経費From建株To株式顧客勘定明細)<BR>
     * 建株Paramsの諸経費を、株式顧客勘定明細Paramsに按分して移動させる。<BR>
     * （ただし、管理費、管理費消費税のみは、約定の度に<BR>
     * 　@建株Paramsに載っている全額を、株式顧客勘定明細Paramsに移動させる。）<BR>
     * 株式顧客勘定明細Paramsに移動させた金額は、建株Paramsから減算する。<BR>
     * （void takeInCostFromContractToTrans(double factor, <BR>
     * EqtypeContractParams cparams, <BR>
     * 　@EqtypeFinTransactionParams tparams)のオーバーライド）<BR>
     * <BR>
     * =========================================================<BR>
     * １）　@建株Paramsの諸経費の按分値を計算し、<BR>
     * 　@　@　@計算した按分値を建株Paramsから株式顧客勘定明細Paramsへ移動させる。<BR>
     * <BR>
     * ---------------------------------------------<BR>
     * ○建株Params、株式顧客勘定明細Paramsの<BR>
     * 　@按分計算による更新対象プロパティ（諸経費項目）は以下の通り。<BR>
     * 　@※建株Paramsと株式顧客勘定明細Paramsとで、プロパティの物理名が異なるので注意。
     * <BR>
     * <BR>
     * 建手数料<BR>
     * 建手数料消費税<BR>
     * 名義書換料<BR>
     * 名義書換料消費税<BR>
     * 順日歩<BR>
     * 逆日歩<BR>
     * 貸株料(*1)<BR>
     * その他<BR>
     * <BR>
     * (*1)引数の建株Params.建区分＝"売建"（SHORT）の場合のみ、<BR>
     * 　@　@　@按分の計算式を使用して計算した結果をセットする。<BR>
     * 　@　@　@引数の建株Params.建区分＝"買建"（LONG）の場合は、<BR>
     * 　@　@　@建株Params.貸株料には現在の値を、<BR>
     * 　@　@　@株式顧客勘定明細Params.貸株料には0を固定でセットする。<BR>
     * <BR>
     * ---------------------------------------------<BR>
     * ○按分の計算式は以下の通り。<BR>
     * <BR>
     * １－１）　@Math.floor(引数の按分比率 × 建株Paramsの更新対象プロパティ) 
     * により、<BR>
     * 　@　@　@建株Paramsから株式顧客勘定明細Paramsに移動させる金額を求める。<BR>
     * 　@　@　@※Math.floor( )：引数の値以下で、計算上の整数と等しい、<BR>
     * 　@　@　@※最大の (無限大にもっとも近い) double 値を返すメソッド。<BR>
     * <BR>
     * １－２）　@株式顧客勘定明細Paramsの更新対象プロパティに、<BR>
     * １－１）で求めた金額を加算する。<BR>
     * <BR>
     * １－３）　@建株Paramsの更新対象プロパティから、<BR>
     * １－１）で求めた金額を減算する。<BR>
     * <BR>
     * ============================================================================<BR>
     * ２）　@建株Paramsの諸経費の全額を、<BR>
     * 　@　@　@建株Paramsから株式顧客勘定明細Paramsへ移動させる。<BR>
     * <BR>
     * ---------------------------------------------<BR>
     * ○建株Params、株式顧客勘定明細Paramsの<BR>
     * 　@全額移動による更新対象プロパティ（諸経費項目）は以下の通り。<BR>
     * 　@※建株Paramsと株式顧客勘定明細Paramsとで、プロパティの物理名が異なるので注意。
     * <BR>
     * <BR>
     * 管理費<BR>
     * 管理費消費税<BR>
     * <BR>
     * ---------------------------------------------<BR>
     * ○全額移動の計算式は以下の通り。<BR>
     * <BR>
     * ２－１）　@株式顧客勘定明細Paramsの更新対象プロパティに、建株Paramsの値を加算する
     * 。<BR>
     * <BR>
     * ２－２）　@建株Paramsの更新対象プロパティに、0をセットする。
     * @@param l_dblFactorRatio (按分比率)<BR>
     * 　@　@　@按分比率（ファ@クター）。
     * @@param l_contractParams 建株Params。
     * @@param l_finTransactionParams 株式顧客勘定明細Params。
     * @@roseuid 40CEEE3400CE
     */
    protected void takeInCostFromContractToTrans(double l_dblFactorRatio, EqtypeContractParams l_contractParams, EqtypeFinTransactionParams l_finTransactionParams)
    {
        final String STR_METHOD_NAME = "takeInCostFromContractToTrans";
        log.entering(STR_METHOD_NAME);

        //１）　@建株Paramsの諸経費の按分値を計算し、計算した按分値を建株Paramsから株式顧客勘定明細Paramsへ移動させる。
        //建手数料
        if (l_contractParams == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        double l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getSetupFee());
        l_finTransactionParams.setImportedSetupFee(l_finTransactionParams.getImportedSetupFee() + l_dblval);
        l_contractParams.setSetupFee(l_contractParams.getSetupFee() - l_dblval);

        //建手数料消費税
        l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getSetupFeeTax());
        l_finTransactionParams.setImportedSetupFeeTax(l_finTransactionParams.getImportedSetupFeeTax() + l_dblval);
        l_contractParams.setSetupFeeTax(l_contractParams.getSetupFeeTax() - l_dblval);

        //名義書換料
        l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getNameTransferFee());
        l_finTransactionParams.setImportedNameTransferFee(l_finTransactionParams.getImportedNameTransferFee() + l_dblval);
        l_contractParams.setNameTransferFee(l_contractParams.getNameTransferFee() - l_dblval);

        //名義書換料消費税
        l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getNameTransferFeeTax());
        l_finTransactionParams.setImportedNameTransferFeeTax(l_finTransactionParams.getImportedNameTransferFeeTax() + l_dblval);
        l_contractParams.setNameTransferFeeTax(l_contractParams.getNameTransferFeeTax() - l_dblval);

        //順日歩
        l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getInterestFee());
        l_finTransactionParams.setImportedInterestFee(l_finTransactionParams.getImportedInterestFee() + l_dblval);
        l_contractParams.setInterestFee(l_contractParams.getInterestFee() - l_dblval);

        //逆日歩
        l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getPayInterestFee());
        l_finTransactionParams.setImportedPayInterestFee(l_finTransactionParams.getImportedPayInterestFee() + l_dblval);
        l_contractParams.setPayInterestFee(l_contractParams.getPayInterestFee() - l_dblval);

        //     * (*1)引数の建株Params.建区分＝"売建"（SHORT）の場合のみ、<BR>
        //按分の計算式を使用して計算した結果をセットする。<BR>
        //引数の建株Params.建区分＝"買建"（LONG）の場合は、<BR>
        //建株Params.貸株料には現在の値を、<BR>
        //株式顧客勘定明細Params.貸株料には0を固定でセットする。
        if (ContractTypeEnum.LONG.equals(l_contractParams.getContractType()))
        {
            l_finTransactionParams.setImportedLoanEquityFee(0);
        }
        else
        {
            l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getLoanEquityFee());
            l_finTransactionParams.setImportedLoanEquityFee(l_finTransactionParams.getImportedLoanEquityFee() + l_dblval);
            l_contractParams.setLoanEquityFee(l_contractParams.getLoanEquityFee() - l_dblval);
        }
        //その他
        l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getOther());
        l_finTransactionParams.setImportedOther(l_finTransactionParams.getImportedOther() + l_dblval);
        l_contractParams.setOther(l_contractParams.getOther() - l_dblval);

        //２）　@建株Paramsの諸経費の全額を、建株Paramsから株式顧客勘定明細Paramsへ移動させる。
        // 管理費
        l_finTransactionParams.setImportedManagementFee(l_finTransactionParams.getImportedManagementFee() + l_contractParams.getManagementFee());
        l_contractParams.setManagementFee(0);

        // 管理費消費税
        l_finTransactionParams.setImportedManagementFeeTax(l_finTransactionParams.getImportedManagementFeeTax() + l_contractParams.getManagementFeeTax());
        l_contractParams.setManagementFeeTax(0);
    }

    /**
     * （set建株デフォルト値）<BR>
     * <BR>
     * 引数の建株Paramsのプロパティに、デフォルト値をセットする。<BR>
     * （void setContractDefaultValues(EqtypeContractParams cparams)のオーバーライド）<BR>
     * <BR>
     * 引数の建株Paramsのプロパティに、以下の通りに値をセット：<BR>
     * ----------------------------------<BR>
     * 元建株数：　@0<BR>
     * 建株数：　@0<BR>
     * 元建手数料：　@0<BR>
     * 建手数料：　@0<BR>
     * 元建手数料消費税：　@0<BR>
     * 建手数料消費税：　@0<BR>
     * 名義書換料：　@0<BR>
     * 名義書換料消費税：　@0<BR>
     * 管理費：　@0<BR>
     * 管理費消費税：　@0<BR>
     * 順日歩：　@0<BR>
     * 順日歩消費税：　@0<BR>
     * 逆日歩：　@0<BR>
     * 逆日歩消費税：　@0<BR>
     * 貸株料：　@0<BR>
     * その他：　@0<BR>
     * 建株評価損益：　@0<BR>
     * 作成日付：　@GtlUtils.getSystemTimestamp()<BR>
     * 更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * ----------------------------------
     * @@param l_contractParams 建株オブジェクト。
     * @@roseuid 40CFCC09029F
     */
    protected void setContractDefaultValues(EqtypeContractParams l_contractParams)
    {
        final String STR_METHOD_NAME = "setContractDefaultValues(EqtypeContractParams)";
        log.entering(STR_METHOD_NAME);
        if (l_contractParams == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        //元建株数
        l_contractParams.setOriginalQuantity(0);
        //建株数
        l_contractParams.setQuantity(0);
        //元建手数料
        l_contractParams.setOriginalSetupFee(0);
        //建手数料
        l_contractParams.setSetupFee(0);
        //元建手数料消費税
        l_contractParams.setOriginalSetupFeeTax(0);
        //建手数料消費税
        l_contractParams.setSetupFeeTax(0);
        //名義書換料
        l_contractParams.setNameTransferFee(0);
        //名義書換料消費税
        l_contractParams.setNameTransferFeeTax(0);
        //管理費
        l_contractParams.setManagementFee(0);
        //管理費消費税
        l_contractParams.setManagementFeeTax(0);
        //順日歩
        l_contractParams.setInterestFee(0);
        //順日歩消費税
        l_contractParams.setInterestFeeTax(0);
        //逆日歩
        l_contractParams.setPayInterestFee(0);
        //逆日歩消費税
        l_contractParams.setPayInterestFeeTax(0);
        //貸株料
        l_contractParams.setLoanEquityFee(0);
        //その他
        l_contractParams.setOther(0);
        //建株評価損益
        l_contractParams.setContractAssetProfitLoss(0);
        //作成日付
        l_contractParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付
        l_contractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （update建株新規建From株式顧客勘定明細）<BR>
     * <BR>
     * 株式顧客勘定明細より、新規建注文時約定時の建株Paramsのプロパティ更新を行う。<BR>
     * また、建手数料等の按分額の変動を、決済注文約定時の株式顧客勘定明細へ反映する。<BR>
     * 　@－（１）今回約定対象の建株（＝引数の建株Paramsオブジェクト）へのプロパティ設定<BR>
     * 　@－（２）既約定時に作成された【建株テーブル】建株データのUpdate<BR>
     * 　@－（３）既約定時に作成された決済注文約定時の【トランザクションテーブル（株式顧客勘定明細）】<BR>
     * 　@　@　@　@　@決済データの建手数料、建手数料消費税のUpdate<BR>
     * ※（２）（３）は、建手数料、建手数料消費税への、一口約定による手数料金額変動の反映。<BR>
     * （void updateContractOpenFromMarketOrderedTrans(EqtypeContractParams cparams, <BR>
     * 　@EqtypeFinTransactionParams tparams)のオーバーライド）<BR>
     * <BR>
     * シーケンス図「（残高）update建株新規建From株式顧客勘定明細」<BR>
     * <BR>
     * １）　@今回約定対象の、建株Paramsのプロパティ設定を行う。<BR>
     * <BR>
     * １－１）　@引数の建株Paramsのプロパティに、引数の株式顧客勘定明細Paramsより、<BR>
     * 　@　@　@　@　@以下の通りに値をセットする。<BR>
     * <BR>
     * 　@　@　@元建株数：　@建株Params.元建株数 + 株式顧客勘定明細Params.約定数量<BR>
     * 　@　@　@建株数：　@建株Params.建株数 + 株式顧客勘定明細Params.約定数量<BR>
     * 　@　@　@元建手数料：　@株式顧客勘定明細Params.委託手数料 のSUM値(*1)<BR>
     * 　@　@　@建手数料：　@株式顧客勘定明細Params.委託手数料 のSUM値(*1)<BR>
     * 　@　@　@元建手数料消費税：　@株式顧客勘定明細Params.委託手数料消費税 のSUM値(*2)<BR>
     * 　@　@　@建手数料消費税：　@株式顧客勘定明細Params.委託手数料消費税 のSUM値(*2)<BR>
     * 　@　@　@更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * 　@　@　@(*1)引数の建株Params.建日 == nullの場合（＝建株の新規Insert時）は、<BR>
     * 　@　@　@　@　@引数の株式顧客勘定明細Params.委託手数料 をセット。<BR>
     * 　@　@　@　@　@引数の建株Params.建日 != nullの場合は、<BR>
     * 　@　@　@　@　@拡張データマネージャ.get株式顧客勘定明細ListOf新規建注文By建株(<BR>
     * 　@　@　@　@　@引数の建株Params.建株ID)で取得した、<BR>
     * 　@　@　@　@　@株式顧客勘定明細ParamsのListの全要素の委託手数料 のSUM値<BR>
     * 　@　@　@　@　@＋引数の株式顧客勘定明細Params.委託手数料 をセット。<BR>
     * <BR>
     * 　@　@　@(*2)引数の建株Params.建日 == nullの場合（＝建株の新規Insert時）は、<BR>
     * 　@　@　@　@　@引数の株式顧客勘定明細Params.委託手数料消費税 をセット。<BR>
     * 　@　@　@　@　@引数の建株Params.建日 != nullの場合は、<BR>
     * 　@　@　@　@　@拡張データマネージャ.get株式顧客勘定明細ListOf新規建注文By建株(<BR>
     * 　@　@　@　@　@引数の建株Params.建株ID)で取得した、<BR>
     * 　@　@　@　@　@株式顧客勘定明細ParamsのListの全要素の委託手数料消費税 のSUM値<BR>
     * 　@　@　@　@　@＋引数の株式顧客勘定明細Params.委託手数料消費税 をセット。<BR>
     * <BR>
     * １－２）　@引数の建株Params.建日 == nullの場合（＝建株の新規Insert時）のみ、<BR>
     * 　@　@　@　@　@引数の建株Paramsのプロパティに、以下の通りに値をセットする。<BR>
     * <BR>
     * １－２－１）　@引数の株式顧客勘定明細Paramsの同項目をセットする。<BR>
     * <BR>
     * 　@　@　@口座ＩＤ<BR>
     * 　@　@　@補助口座ＩＤ<BR>
     * 　@　@　@市場ＩＤ<BR>
     * 　@　@　@銘柄ＩＤ<BR>
     * 　@　@　@銘柄タイプ<BR>
     * 　@　@　@税区分<BR>
     * <BR>
     * １－２－２）　@引数の株式顧客勘定明細Params.注文単位IDの注文単位オブジェクト(*3)の<BR>
     * 　@　@　@　@　@　@　@　@同項目をセットする。<BR>
     * <BR>
     * 　@　@　@弁済区分<BR>
     * 　@　@　@弁済期限値<BR>
     * <BR>
     * 　@　@　@(*3)拡張株式注文マネージャ.getOrderUnit(<BR>
     * 　@　@　@　@　@株式顧客勘定明細Params.注文単位ID) により<BR>
     * 　@　@　@　@　@取得した注文単位オブジェクトを使用する。<BR>
     * <BR>
     * １－２－３）　@引数の株式顧客勘定明細Paramsより、以下の通りにセットする。<BR>
     * <BR>
     * 　@　@　@元建単価：　@株式顧客勘定明細Params.約定単価<BR>
     * 　@　@　@建単価：　@株式顧客勘定明細Params.約定単価<BR>
     * 　@　@　@建区分：　@ContractTypeEnum.getContractType(<BR>
     * 　@　@　@　@　@　@株式顧客勘定明細Params.トランザクションタイプ)<BR>
     * 　@　@　@建日：　@株式顧客勘定明細Params.約定IDに該当する約定オブジェクト.約定日時のYYYYMMDD<BR>
     * 　@　@　@期日：　@this.get建株期日(建日, 弁済期限値)<BR>
     * 　@　@　@　@　@　@※引数の建日、弁済期限値は、当メソッド内で取得した値を使用する。<BR>
     * 　@　@　@保証金率：　@建区分＝"買建"（LONG）の場合は、取引銘柄.買保証金率(*4)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@建区分＝"売建"（SHORT）の場合は、取引銘柄.売保証金率(*4)<BR>
     * 　@　@　@現金保証金率：　@建区分＝"買建"（LONG）の場合は、取引銘柄.買現金保証金率(*4)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@建区分＝"売建"（SHORT）の場合は、取引銘柄.売現金保証金率(*4)<BR>
     * 　@　@　@当初建日：　@建日に同じ<BR>
     * <BR>
     * 　@　@　@(*4)拡張プロダクトマネージャ.getTradedProduct(引数の株式顧客勘定明細Params.銘柄ID,<BR>
     * 　@　@　@　@　@引数の株式顧客勘定明細Params.市場ID)で取得した取引銘柄オブジェクトを<BR>
     * 　@　@　@　@　@使用する。<BR>
     * <BR>
     * ２）　@既約定時に作成された建株データを【建株テーブル】より取得し、<BR>
     * 　@　@　@元建手数料、建手数料、元建手数料消費税、建手数料消費税 のUpdateを行う。<BR>
     * <BR>
     * ２－１）　@既約定時に作成された建株ParamsのListを取得する。<BR>
     * <BR>
     * 　@　@　@　@　@拡張データマネージャ.get建株ListBy注文単位(<BR>
     * 　@　@　@　@　@引数の株式顧客勘定明細Params.注文単位ID)により取得する。<BR>
     * <BR>
     * 　@　@　@　@　@既存建株のUpdate時、<BR>
     * 　@　@　@　@　@かつ　@取得した建株Listに引数の建株Paramsに該当する建株が存在しない場合は、<BR>
     * 　@　@　@　@　@戻り値のListに引数の建株Paramsを追加する。<BR>
     * <BR>
     * ２－２）　@以下、２－１）で取得した建株ParamsのListの要素（index）分Loopする。<BR>
     * <BR>
     * 　@　@　@　@　@なお、建株Params(index).建株ID＝引数の建株Params.建株ID の場合は、<BR>
     * 　@　@　@　@　@その建株Params(index)の替わりに、引数の建株Paramsを使用する。<BR>
     * 　@　@　@　@　@（引数の建株Paramsは今回約定対象の建株であるため、<BR>
     * 　@　@　@　@　@　@今回約定で作成される株式顧客勘定明細の値が載っている、<BR>
     * 　@　@　@　@　@　@１）で作成した建株Paramsを使用する。）<BR>
     * <BR>
     * ↓↓↓↓↓↓START LOOP（１）↓↓↓↓↓↓<BR>
     * <BR>
     * ２－２－１）　@建株Params(index).建株ID≠引数の建株Params.建株ID の場合のみ、<BR>
     * 　@　@　@　@　@　@　@　@建株Params(index)に対し、<BR>
     * 　@　@　@　@　@　@　@　@元建手数料、建手数料、元建手数料消費税、建手数料消費税 の設定を行う。<BR>
     * 　@　@　@　@　@　@　@　@※新規建注文約定時の委託手数料を載せる。<BR>
     * <BR>
     * 　@　@　@元建手数料：　@株式顧客勘定明細Params.委託手数料 のSUM値(*5)<BR>
     * 　@　@　@建手数料：　@株式顧客勘定明細Params.委託手数料 のSUM値(*5)<BR>
     * 　@　@　@元建手数料消費税：　@株式顧客勘定明細Params.委託手数料消費税 のSUM値(*6)<BR>
     * 　@　@　@建手数料消費税：　@株式顧客勘定明細Params.委託手数料消費税 のSUM値(*6)<BR>
     * 　@　@　@更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * 　@　@　@(*5)拡張データマネージャ.get株式顧客勘定明細ListOf新規建注文By建株(<BR>
     * 　@　@　@　@　@建株Params(index).建株ID)で取得した、<BR>
     * 　@　@　@　@　@株式顧客勘定明細ParamsのListの全要素の委託手数料 のSUM値。<BR>
     * 　@　@　@　@　@※該当するデータが存在しない場合は、0とする。<BR>
     * <BR>
     * 　@　@　@(*6)拡張データマネージャ.get株式顧客勘定明細ListOf新規建注文By建株(<BR>
     * 　@　@　@　@　@建株Params(index).建株ID)で取得した、<BR>
     * 　@　@　@　@　@株式顧客勘定明細ParamsのListの全要素の委託手数料消費税 のSUM値<BR>
     * 　@　@　@　@　@※該当するデータが存在しない場合は、0とする。<BR>
     * <BR>
     * <BR>
     * ２－２－２）　@建株Params(index)に対する、決済注文約定時の株式顧客勘定明細Paramsを<BR>
     * 　@　@　@　@　@　@　@　@全て取得する。<BR>
     * <BR>
     * 　@　@　@　@　@拡張データマネージャ.get株式顧客勘定明細ListOf決済注文By建株(<BR>
     * 　@　@　@　@　@建株Params(index).建株ID)により取得する。<BR>
     * <BR>
     * ２－２－３）　@決済済数量（Ｙ）を０で初期化する。<BR>
     * <BR>
     * ２－２－４）　@以下、２－２－２）で取得した株式顧客勘定明細ParamsのListの要素（index2）分<BR>
     * 　@　@　@　@　@　@　@　@Loopする。<BR>
     * <BR>
     * ↓↓↓START LOOP（２）↓↓↓<BR>
     * <BR>
     * ２－２－４－１）　@株式顧客勘定明細Params(index2)に対し、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@建株.建手数料、建手数料消費税を再按分する。<BR>
     * <BR>
     * 　@　@　@建手数料：　@建株Params(index).建手数料 ×<BR>
     * 　@　@　@　@　@　@　@　@　@　@（株式顧客勘定明細Params(index2).約定数量 ÷<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@（建株Params(index).元建株数 － Ｙ））(*7)<BR>
     * 　@　@　@建手数料消費税：　@建株Params(index).建手数料消費税 ×<BR>
     * 　@　@　@　@　@　@　@　@　@　@（株式顧客勘定明細Params(index2).約定数量 ÷<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@（建株Params(index).元建株数 － Ｙ））(*7)<BR>
     * 　@　@　@更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * 　@　@　@(*7)最終計算結果を、円未満切捨てする（計算過程では、丸めは行わない）<BR>
     * <BR>
     * ２－２－４－２）　@受渡代金、譲渡益金額、譲渡益税額に、建手数料等の変動を反映する。<BR>
     * <BR>
     * 　@　@　@　@this.set信用注文約定時金額(株式顧客勘定明細Params(index2))コールにより反映する。<BR>
     * <BR>
     * ２－２－４－３）　@　@【トランザクションテーブル（株式顧客勘定明細）】のUpdateを行う。<BR>
     * <BR>
     * 　@　@　@　@拡張データマネージャ.updateFinTransaction(株式顧客勘定明細Params(index2,<BR>
     * 　@　@　@　@更新対象プロパティMap)により、updateを行う。<BR>
     * 　@　@　@　@※更新対象プロパティMapには、２－２－４－１）でセットしたプロパティ及び値をセットする。<BR>
     * <BR>
     * ２－２－４－４）　@【トランザクションテーブル（株式顧客勘定明細）】のupdateをGTL層に通知する。<BR>
     * <BR>
     * 　@　@　@　@this.顧客勘定更新(株式顧客勘定明細Params(index2))により<BR>
     * 　@　@　@　@GTL層への通知を行う。<BR>
     * <BR>
     * ２－２－４－５）　@決済済数量（Ｙ）を更新する。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@Ｙ ＝ Ｙ ＋ 株式顧客勘定明細Params(index2).約定数量<BR>
     * <BR>
     * ２－２－４－６）　@建株Params(index)に対し、建手数料、建手数料消費税 の再設定を行う。<BR>
     * 　@　@　@　@　@　@　@　@※決済注文約定時の建手数料、建手数料消費税の徴収を、建株に反映する。<BR>
     * <BR>
     * 　@　@　@建手数料：　@（既存値） － 株式顧客勘定明細Params(index2).建手数料<BR>
     * 　@　@　@建手数料消費税：　@（既存値） － 株式顧客勘定明細Params(index2).建手数料消費税<BR>
     * <BR>
     * ↑↑↑END  LOOP（２）↑↑↑<BR>
     * <BR>
     * ２－２－２）　@【建株テーブル】のUpdateを行う。<BR>
     * <BR>
     * 　@　@　@　@建株Params(index).建株ID≠引数の建株Params.建株ID の場合のみ、<BR>
     * 　@　@　@　@拡張データマネージャ.updateContractByTrans(建株Params(index))により<BR>
     * 　@　@　@　@updateを行う。<BR>
     * <BR>
     * 　@　@　@　@※引数の建株Paramsは今回約定対象の建株であるため、プロパティ設定のみ行う。<BR>
     * 　@　@　@　@※今回約定対象の建株のDB更新は、processNewMarginOrderExecution( )の最後で<BR>
     * 　@　@　@　@※xTrade標準実装により実行されるため、このメソッド内では行わない。<BR>
     * <BR>
     * ↑↑↑↑↑↑END  LOOP（１）↑↑↑↑↑↑<BR>
     * <BR>
     * ３）　@returnする。
     * @@param l_contractParams (建株Params)<BR>
     * 　@　@　@今回約定対象の、建株Params。
     * @@param l_finTransactionParams 株式顧客勘定明細Params。
     * @@throws RuntimeSystemException
     * @@roseuid 40CFCDFC003E
     */
    protected void updateContractOpenFromMarketOrderedTrans(
        EqtypeContractParams l_contractParams,
        EqtypeFinTransactionParams l_finTransactionParams)
        throws RuntimeSystemException
    {
        final String STR_METHOD_NAME = "updateContractOpenFromMarketOrderedTrans(EqtypeContractParams, EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnInsert = false;
        try
        {
            WEB3EquityPersistentDataManager l_dataManager = (WEB3EquityPersistentDataManager) this.getPersistenceManager();
            List l_lstfinParams = l_dataManager.getFinTransactionListOfOpenOrderByContract(l_contractParams.getContractId());
            //// １）　@今回約定対象の、建株Paramsのプロパティ設定を行う。
            //元建株数：　@建株Params.元建株数 + 株式顧客勘定明細Params.約定数量
            l_contractParams.setOriginalQuantity(l_contractParams.getOriginalQuantity() + l_finTransactionParams.getQuantity());
            //建株数：　@建株Params.建株数 + 株式顧客勘定明細Params.約定数量
            l_contractParams.setQuantity(l_contractParams.getQuantity() + l_finTransactionParams.getQuantity());
            //更新日付：　@GtlUtils.getSystemTimestamp()
            l_contractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            if (l_contractParams.getOpenDate() != null)
            {
                int l_intParamsLength = 0;
                if (l_lstfinParams != null)
                {
                    l_intParamsLength = l_lstfinParams.size();
                }
                
                double l_dblCommissionFee = 0D;
                double l_dblCommissionFeeTax = 0D;
                for (int i = 0; i < l_intParamsLength; i++)
                {
                    EqtypeFinTransactionParams l_params = (EqtypeFinTransactionParams) l_lstfinParams.get(i);
                    l_dblCommissionFee += l_params.getCommissionFee();
                    l_dblCommissionFeeTax += l_params.getCommissionFeeTax();
                }
                //建手数料：　@株式顧客勘定明細Params.委託手数料 のSUM値(*1)
                l_contractParams.setSetupFee(l_dblCommissionFee + l_finTransactionParams.getCommissionFee());
                //元建手数料：　@株式顧客勘定明細Params.委託手数料 のSUM値(*1)
                l_contractParams.setOriginalSetupFee(l_dblCommissionFee + l_finTransactionParams.getCommissionFee());
                //建手数料消費税：　@株式顧客勘定明細Params.委託手数料消費税 のSUM値(*2)
                l_contractParams.setSetupFeeTax(l_dblCommissionFeeTax + l_finTransactionParams.getCommissionFeeTax());
                //元建手数料消費税：　@株式顧客勘定明細Params.委託手数料消費税 のSUM値(*2)
                l_contractParams.setOriginalSetupFeeTax(l_dblCommissionFeeTax + l_finTransactionParams.getCommissionFeeTax());
            }
            //(*1)引数の建株Params.建日 == nullの場合（＝建株の新規Insert時）は、
            else
            {
                l_blnInsert = true;
                
                //建手数料：　@引数の株式顧客勘定明細Params.委託手数料 をセット。
                l_contractParams.setSetupFee(l_finTransactionParams.getCommissionFee());
                //元建手数料：　@引数の株式顧客勘定明細Params.委託手数料 をセット。
                l_contractParams.setOriginalSetupFee(l_finTransactionParams.getCommissionFee());
                //建手数料消費税：　@引数の株式顧客勘定明細Params.委託手数料消費税 をセット。
                l_contractParams.setSetupFeeTax(l_finTransactionParams.getCommissionFeeTax());
                //元建手数料消費税：　@引数の株式顧客勘定明細Params.委託手数料消費税 をセット。
                l_contractParams.setOriginalSetupFeeTax(l_finTransactionParams.getCommissionFeeTax());
                //１－２－１）　@引数の株式顧客勘定明細Paramsの同項目をセットする。
                //口座ＩＤ
                l_contractParams.setAccountId(l_finTransactionParams.getAccountId());
                //補助口座ＩＤ
                l_contractParams.setSubAccountId(l_finTransactionParams.getSubAccountId());
                //市場ＩＤ
                l_contractParams.setMarketId(l_finTransactionParams.getMarketId());
                //銘柄ＩＤ
                l_contractParams.setProductId(l_finTransactionParams.product_id);
                //銘柄タイプ
                l_contractParams.setProductType(l_finTransactionParams.getProductType());
                //税区分
                l_contractParams.setTaxType(l_finTransactionParams.getTaxType());

                //取得拡張株式注文マネージャ
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
                //１－２－２）　@引数の株式顧客勘定明細Params.注文単位IDの注文単位オブジェクト(*3)の同項目をセットする。
                OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_finTransactionParams.getOrderUnitId());
                EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
                //弁済区分
                l_contractParams.setRepaymentType(l_orderUnitRow.getRepaymentType());
                //弁済期限値
                l_contractParams.setRepaymentNum(l_orderUnitRow.getRepaymentNum());
                //元建単価：　@株式顧客勘定明細Params.約定単価
                l_contractParams.setOriginalContractPrice(l_finTransactionParams.getPrice());
                //建単価：　@株式顧客勘定明細Params.約定単価
                l_contractParams.setContractPrice(l_finTransactionParams.getPrice());
                //建区分：　@ContractTypeEnum.getContractType(株式顧客勘定明細Params.トランザクションタイプ)
                ContractTypeEnum l_contractType = ContractTypeEnum.getContractType(l_finTransactionParams.getFinTransactionType());
                l_contractParams.setContractType(l_contractType);
                //建日：　@株式顧客勘定明細Params.約定IDに該当する約定オブジェクト.約定日時のYYYYMMDD
                OrderExecution l_orderExecution =
                    l_orderManager.getOrderExecution(l_finTransactionParams.getOrderExecutionId());
                Date l_datOpenDate = WEB3DateUtility.toDay(l_orderExecution.getExecutionTimestamp());
                l_contractParams.setOpenDate(l_datOpenDate);
                //期日：　@this.get建株期日(建日, 弁済期限値)
                l_contractParams.setCloseDate(this.getContractCloseDate(l_datOpenDate, l_orderUnitRow.getRepaymentNum()));

                WEB3EquityProductManager l_productManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();
                WEB3EquityTradedProduct l_tradedProduct = (WEB3EquityTradedProduct) l_productManager.getTradedProduct(l_finTransactionParams.getProductId(), l_finTransactionParams.getMarketId());

                EqtypeTradedProductRow l_productRow = (EqtypeTradedProductRow) l_tradedProduct.getDataSourceObject();
                if (ContractTypeEnum.LONG.equals(l_contractType))
                {
                    //保証金率：　@建区分＝"買建"（LONG）の場合は、取引銘柄.買保証金率(*4)
                    l_contractParams.setMarginDepositRate(l_productRow.getLongMarginDepositRate());
                    //現金保証金率：　@建区分＝"買建"（LONG）の場合は、取引銘柄.買現金保証金率(*4)
                    l_contractParams.setCashMarginDepositRate(l_productRow.getLongCashMarginDepositRate());
                }
                else if (ContractTypeEnum.SHORT.equals(l_contractType))
                {
                    //建区分＝"売建"（SHORT）の場合は、取引銘柄.売保証金率(*4)
                    l_contractParams.setMarginDepositRate(l_productRow.getShortMarginDepositRate());
                    //建区分＝"売建"（SHORT）の場合は、取引銘柄.売現金保証金率(*4)
                    l_contractParams.setCashMarginDepositRate(l_productRow.getShortCashMarginDepositRate());
                }
                //当初建日：　@建日に同じ
                l_contractParams.setFirstOpenDate(l_contractParams.getOpenDate());

            }
            //２）　@既約定時に作成された建株データを【建株テーブル】より取得し、建手数料、建手数料消費税 のUpdateを行う。
            //２－１）　@既約定時に作成された建株ParamsのListを取得する。
            List l_lstContract = l_dataManager.getContractListByOrderUnit(l_finTransactionParams.getOrderUnitId());
            int l_intContractLength = 0;
            if (l_lstContract != null)
            {
                l_intContractLength = l_lstContract.size();
            }
            if (l_blnInsert == false)
            {
                boolean l_blnExistContract = false;
                for (int i = 0; i < l_intContractLength; i++)
                {
                    EqtypeContractParams l_params = (EqtypeContractParams) l_lstContract.get(i);
                    if (l_params.getContractId() == l_contractParams.getContractId())
                    {
                        l_blnExistContract = true;
                        break;
                    }
                }
                if (l_blnExistContract == false)
                {
                    l_lstContract.add(l_contractParams);
                    l_intContractLength = l_lstContract.size();
                }
            }

            //以下、２－１）で取得した建株ParamsのListの要素（index）分Loopする。
            for (int i = 0; i < l_intContractLength; i++)
            {

                EqtypeContractParams l_params = (EqtypeContractParams) l_lstContract.get(i);
                //２－２）　@建株Params(index)に対し、建手数料、建手数料消費税 の設定を行う。※新規建注文約定時の委託手数料を載せる。
                //なお、建株Params(index).建株ID＝引数の建株Params.建株ID の場合は、その建株Params(index)の替わりに、引数の建株Paramsを使用する。
                if (l_params.getContractId() == l_contractParams.getContractId())
                {
                    l_params = l_contractParams;
                }
                
                // ２－２－１）建株Params(index).建株ID≠引数の建株Params.建株ID の場合のみ、 
                //     建株Params(index)に対し、 
                //     元建手数料、建手数料、元建手数料消費税、建手数料消費税 の設定を行う。 
                //     ※新規建注文約定時の委託手数料を載せる。 
	            double l_commissionFee = 0D;
	            double l_commissionFeeTax = 0D;
                if (l_params.getContractId() != l_contractParams.getContractId())
                {
	                //拡張データマネージャ.get株式顧客勘定明細ListOf新規建注文By建株
	                List l_lstFin = l_dataManager.getFinTransactionListOfOpenOrderByContract(l_params.getContractId());
	                int l_intFinLength = 0;
	                if (l_lstFin != null)
	                {
	                    l_intFinLength = l_lstFin.size();
	                }
	                for (int j = 0; j < l_intFinLength; j++)
	                {
	                    EqtypeFinTransactionParams l_finParams = (EqtypeFinTransactionParams) l_lstFin.get(j);
	                    l_commissionFee += l_finParams.getCommissionFee();
	                    l_commissionFeeTax += l_finParams.getCommissionFeeTax();
	                }
	                //元建手数料：　@株式顧客勘定明細Params.委託手数料 のSUM値(*5)
	                l_params.setOriginalSetupFee(l_commissionFee);
	                //建手数料：　@株式顧客勘定明細Params.委託手数料 のSUM値(*5)
	                l_params.setSetupFee(l_commissionFee);
	                //元建手数料消費税：　@株式顧客勘定明細Params.委託手数料消費税 のSUM値(*6)
	                l_params.setOriginalSetupFeeTax(l_commissionFeeTax);
	                //建手数料消費税：　@株式顧客勘定明細Params.委託手数料消費税 のSUM値(*6)
	                l_params.setSetupFeeTax(l_commissionFeeTax);
	                //　@更新日付：　@GtlUtils.getSystemTimestamp()
	                l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                }
                
                //２－２－２）　@建株Params(index)に対する、決済注文約定時の株式顧客勘定明細Paramsを全て取得する。
                List l_lstFinTransaction = l_dataManager.getFinTransactionListOfCloseOrderByContract(l_params.getContractId());
                int l_intTransactionlength = 0;
                if (l_lstFinTransaction != null )
                {
                    l_intTransactionlength = l_lstFinTransaction.size();
                }

                //２－２－３）　@決済済数量（Ｙ）を０で初期化する。
                double l_Y = 0D;
                //２－２－４）　@以下、２－２－２）で取得した株式顧客勘定明細ParamsのListの要素（index2）分Loopする。
                //２－２－４－１）　@株式顧客勘定明細Params(index2)に対し、建株.建手数料、建手数料消費税を再按分する。
                for (int k = 0; k < l_intTransactionlength; k++)
                {
                    EqtypeFinTransactionParams l_finParams1 = (EqtypeFinTransactionParams) l_lstFinTransaction.get(k);
                    //　@建手数料：　@建株Params(index).建手数料 ×（株式顧客勘定明細Params(index2).約定数量 ÷（建株Params(index).元建株数 － Ｙ））(*7)
                    l_commissionFee = Math.floor(l_params.getSetupFee() * (l_finParams1.getQuantity() / (l_params.getOriginalQuantity() - l_Y)));
                    l_finParams1.setImportedSetupFee(l_commissionFee);
                    //建手数料消費税：　@建株Params(index).建手数料消費税 ×（株式顧客勘定明細Params(index2).約定数量 ÷（建株Params(index).元建株数 － Ｙ））(*7)
                    l_commissionFeeTax = Math.floor(l_params.getSetupFeeTax() * (l_finParams1.getQuantity() / (l_params.getOriginalQuantity() - l_Y)));
                    l_finParams1.setImportedSetupFeeTax(l_commissionFeeTax);
                    //更新日付：　@GtlUtils.getSystemTimestamp()
                    l_finParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    //this.set信用注文約定時金額(株式顧客勘定明細Params(index2))コールにより反映する。
                    this.setMarginNetAmount(l_finParams1);
                    //拡張データマネージャ.updateFinTransaction(株式顧客勘定明細Params(index2), 更新対象プロパティMap)によりupdateを行う。
                    HashMap l_map = new HashMap();
                    l_map.put("net_amount", new Double(l_finParams1.getNetAmount()));
                    l_map.put("capital_gain", new Double(l_finParams1.getCapitalGain()));
                    l_map.put("capital_gain_tax", new Double(l_finParams1.getCapitalGainTax())); 
                    l_map.put("imported_setup_fee", new Double(l_finParams1.getImportedSetupFee()));
                    l_map.put("imported_setup_fee_tax", new Double(l_finParams1.getImportedSetupFeeTax()));
                    l_map.put("last_updated_timestamp", l_finParams1.getLastUpdatedTimestamp());
                    l_dataManager.updateFinTransaction(l_finParams1, l_map);
                    //this.顧客勘定更新(株式顧客勘定明細Params(index2))によりGTL層への通知を行う。
                    this.notifyGtl(l_finParams1);
                    //２－２－４－５）　@決済済数量（Ｙ）を更新する。
                    //Ｙ ＝ Ｙ ＋ 株式顧客勘定明細Params(index2).約定数量
                    l_Y += l_finParams1.getQuantity();
                    //　@　@　@建手数料：　@（既存値） － 株式顧客勘定明細Params(index2).建手数料
                    l_params.setSetupFee(l_params.getSetupFee() - l_finParams1.getImportedSetupFee());
                    //建手数料消費税：　@（既存値） － 株式顧客勘定明細Params(index2).建手数料消費税
                    l_params.setSetupFeeTax(l_params.getSetupFeeTax() - l_finParams1.getImportedSetupFeeTax());
                }
                //建株Params(index).建株ID≠引数の建株Params.建株ID の場合のみ、拡張データマネージャ.updateContractByTrans(建株Params(index))によりupdateを行う。
                if (l_params.getContractId() != l_contractParams.getContractId())
                {
                    l_dataManager.updateContractByTrans(l_params);
                }
            }
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_wbe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_wbe.getMessage(), l_wbe);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        catch (DataException l_dfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_dfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dfe.getMessage(), l_dfe);
        }
    }

    /**
     * (get建株期日)<BR>
     * 建株.建日に設定する期日（closeDate）を計算し返す。<BR>
     * （Date getContractCloseDate(Date openDate)のオーバーロード）<BR>
     * <BR>
     * 引数の建日、弁済区分、弁済期限値から、期日を計算する。<BR>
     * <BR>
     * ---------------------------------------------------------<BR>
     * ○弁済期限値＝無期限(*) の場合<BR>
     * <BR>
     * 　@　@"99991231"を返す。<BR>
     * <BR>
     * ○弁済期限値≠無期限(*) の場合<BR>
     * <BR>
     * 　@　@建日の、Ｘヶ月後の日付を求めて返す。<BR>
     * 　@　@※Ｘ＝弁済期限値<BR>
     * <BR>
     * 　@　@営業日計算.get指定営業日( )メソッドをコールし求める。<BR>
     * 　@　@　@-----------------------------------------------------<BR>
     * 　@　@　@＜get指定営業日( )：引数設定仕様＞<BR>
     * 　@　@　@基準日：　@引数の建日<BR>
     * 　@　@　@年数：　@0<BR>
     * 　@　@　@月数：　@引数の弁済期限値<BR>
     * 　@　@　@日数：　@0<BR>
     * 　@　@　@加算／減算：　@加算<BR>
     * 　@　@　@-----------------------------------------------------<BR>
     * <BR>
     * 　@　@・Ｘヶ月後の日付がカレンダー日付でない場合（６月３１日等）は、<BR>
     * 　@　@　@当該月の最終営業日が期日となる。<BR>
     * 　@　@・Ｘヶ月後の日付が非営業日の場合は、<BR>
     * 　@　@　@その日より前の日付を持つ営業日で、Ｘヶ月後の日付に最も近い日付が期日となる。<BR>
     * ---------------------------------------------------------<BR>
     * <BR>
     * (*)無期限<BR>
     * 　@　@株式ポジションマネージャ.is無期限(引数の弁済期限値)＝trueの場合、<BR>
     * 　@　@無期限であると判定する。<BR>
     * 　@　@以外、無期限でないと判定する。
     * @@param l_datOpenDate 建日。
     * @@param l_dblRepaymentNum 弁済期限値。
     * @@return Date
     * @@roseuid 40CFD51B033B
     */
    public Date getContractCloseDate(Date l_datOpenDate, double l_dblRepaymentNum) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractCloseDate";
        log.entering(STR_METHOD_NAME);
        //引数の建日、弁済区分、弁済期限値から、期日を計算する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        // 弁済期限値＝無期限(*) の場合<BR>"99991231"を返す
        if (l_positionManager.isIndefinite(l_dblRepaymentNum))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3DateUtility.getDate("99991231", "yyyyMMdd");
        }
        else
        {
            //営業日計算.get指定営業日( )メソッドをコールし求める。
            Timestamp l_timestamp = new Timestamp(l_datOpenDate.getTime());
            log.exiting(STR_METHOD_NAME);
            return WEB3GentradeBizDate.getAppointmentBizDate(l_timestamp, 0L, Math.round(l_dblRepaymentNum), 0L, 1);
        }

    }

    /**
     * (set信用注文約定時金額)<BR>
     * 今回約定分について、受渡代金、譲渡益金額、譲渡益税額を計算し、<BR>
     * 株式顧客勘定明細Paramsの同名プロパティにセットする。<BR>
     * （void setMarginNetAmount(EqtypeFinTransactionParams tparams)のオーバーライド）<BR>
     * <BR>
     * 引数.トランザクションカテゴリが<BR>
     * 　@　@新規建（EQTYPE_OPEN_MARGIN）<BR>
     * 　@　@返済（EQTYPE_CLOSE_MARGIN）<BR>
     * 　@　@現引現渡（EQTYPE_SWAP_MARGIN）<BR>
     * 以外の場合は、例外をthrowし処理を終了する。<BR>
     * <BR>
     * =======================================================================<BR>
     * ○新規建の場合<BR>
     * 　@（＝ 引数.トランザクションカテゴリ＝FinTransactionCateg.EQTYPE_OPEN_MARGIN）<BR>
     * <BR>
     * １）　@受渡代金に、0をセットする。<BR>
     * <BR>
     * ２）　@譲渡益金額に、0をセットする。<BR>
     * <BR>
     * ３）　@譲渡益税額に、0をセットする。<BR>
     * <BR>
     * =======================================================================<BR>
     * ○返済の場合<BR>
     * 　@（＝ 引数.トランザクションカテゴリ＝FinTransactionCateg.EQTYPE_CLOSE_MARGIN）<BR>
     * <BR>
     * １）　@受渡代金（＝決済損益代金）をセットする。<BR>
     * <BR>
     * １－１）　@決済損益代金を計算する。<BR>
     * <BR>
     * 　@　@　@this.calc決済損益代金( )コールにより、決済損益代金を計算する。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc決済損益代金( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@株式顧客勘定明細Params：　@<BR>
     * 　@　@　@　@　@　@　@：　@株式顧客勘定明細Params(index)をセットする。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * １－２）　@受渡代金に、１－１）で計算した決済損益代金をセットする。<BR>
     * <BR>
     * ２）　@譲渡益金額をセットする。<BR>
     * <BR>
     * 　@　@　@引数の株式顧客勘定明細Params.税区分＝（"特定"、"特定口座かつ源泉徴収"）の場合、<BR>
     * 　@　@　@譲渡益金額に、１－２）でセットした受渡代金をそのままセットする。<BR>
     * <BR>
     * 　@　@　@引数の株式顧客勘定明細Params.税区分≠（"特定"、"特定口座かつ源泉徴収"）の場合、<BR>
     * 　@　@　@譲渡益金額に0をセットする。<BR>
     * <BR>
     * ３）　@譲渡益税額を計算する。<BR>
     * <BR>
     * ３－１）　@引数の株式顧客勘定明細Params.建株IDに該当する建株オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@　@　@株式ポジションマネージャ.get建株(引数の株式顧客勘定明細Params.建株ID)を<BR>
     * 　@　@　@　@　@コールする。<BR>
     * <BR>
     * ３－２）　@譲渡益税額を計算する。<BR>
     * <BR>
     * ３－２－１）　@顧客オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜拡張アカウントマネージャ.get顧客(long)：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@口座ID：　@引数の株式顧客勘定明細Params.口座ID<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ３－２－２）　@顧客.get受渡日信用取引税区分( )により、信用取引の顧客税区分を取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get受渡日信用取引税区分(Date)：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@受渡日：　@(*C1)<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ３－２－３）　@株式計算サービス.calc譲渡益税( )コールにより、譲渡益税を計算する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc譲渡益税(補助口座, TaxTypeEnum, double, Timestamp, TaxTypeEnum)：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@補助口座：　@引数の株式顧客勘定明細Paramsの口座ID、補助口座IDに該当する補助口座<BR>
     * 　@　@　@税区分：　@３－１）で取得した建株.税区分<BR>
     * 　@　@　@金額：　@２）で計算した譲渡益金額<BR>
     * 　@　@　@基準日：　@返済注文の受渡日(*C1)<BR>
     * 　@　@　@顧客税区分：　@顧客.get受渡日信用取引税区分( )の戻り値<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@(*C1)引数の株式顧客勘定明細Params.注文単位IDに該当する注文単位オブジェクト.受渡日<BR>
     * <BR>
     * =======================================================================<BR>
     * ○現引現渡の場合<BR>
     * 　@（＝ 引数.トランザクションカテゴリ＝FinTransactionCateg.EQTYPE_SWAP_MARGIN）<BR>
     * <BR>
     * 引数.トランザクションタイプが<BR>
     * 　@　@現引（EQTYPE_SWAP_MARGIN_LONG）<BR>
     * 　@　@現渡（EQTYPE_SWAP_MARGIN_SHORT）<BR>
     * 以外の場合は、例外をthrowし処理を終了する。<BR>
     * <BR>
     * １）　@受渡代金を計算する。<BR>
     * <BR>
     * １－１）　@今回約定分について、受渡代金を計算する。<BR>
     * <BR>
     * 　@　@　@this.calc受渡代金（現引現渡）( )コールにより、<BR>
     * 　@　@　@受渡代金を計算する。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc受渡代金（現引現渡）( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@株式顧客勘定明細Params：　@<BR>
     * 　@　@　@　@　@　@　@：　@株式顧客勘定明細Params(index)をセットする。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * １－２）　@受渡代金に、１－１）で計算した受渡代金をセットする。<BR>
     * <BR>
     * ２）　@譲渡益金額を計算する。<BR>
     * <BR>
     * ２－１）　@現引の場合（＝ 引数.トランザクションタイプ＝<BR>
     * 　@　@　@　@　@　@　@FinTransactionType.EQTYPE_SWAP_MARGIN_LONG））は、<BR>
     * 　@　@　@譲渡益金額に、0をセットする。<BR>
     * <BR>
     * 　@　@　@現渡の場合（＝ 引数.トランザクションタイプ＝<BR>
     * 　@　@　@　@　@　@　@FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT））は、<BR>
     * 　@　@　@株式計算サービス.calc譲渡損益( )コールにより、<BR>
     * 　@　@　@譲渡益金額を計算する。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc譲渡損益(double, double, long, SubAccount, TaxTypeEnum)：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@金額：　@１－１）で計算した受渡代金<BR>
     * 　@　@　@売数量：　@引数の株式顧客勘定明細Params.約定数量<BR>
     * 　@　@　@銘柄ID：　@引数の株式顧客勘定明細Params.銘柄ID<BR>
     * 　@　@　@補助口座：　@引数の株式顧客勘定明細Params.口座ID、補助口座IDに該当する<BR>
     * 　@　@　@　@　@　@　@　@　@補助口座オブジェクト<BR>
     * 　@　@　@税区分：　@引数の株式顧客勘定明細Params.注文単位IDに該当する<BR>
     * 　@　@　@　@　@　@　@　@　@注文単位.税区分（現引現渡）<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@計算結果を、譲渡益金額にセットする。<BR>
     * <BR>
     * ３）　@譲渡益税額を計算する。<BR>
     * <BR>
     * ３－１）　@現引の場合（＝ 引数.トランザクションタイプ＝<BR>
     * 　@　@　@　@　@　@　@FinTransactionType.EQTYPE_SWAP_MARGIN_LONG））は、<BR>
     * 　@　@　@　@　@譲渡益税額に、0をセットする。<BR>
     * <BR>
     * ３－２）　@現渡の場合（＝ 引数.トランザクションタイプ＝<BR>
     * 　@　@　@　@　@FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT））は、<BR>
     * 　@　@　@　@　@以下の処理を行う。<BR>
     * <BR>
     * ３－２－１）　@顧客オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜拡張アカウントマネージャ.get顧客(long)：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@口座ID：　@引数の株式顧客勘定明細Params.口座ID<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ３－２－２）　@顧客.get受渡日税区分( )により、現物の顧客税区分を取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get受渡日税区分(Date)：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@受渡日：　@(*S1)<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ３－２－３）　@株式計算サービス.calc譲渡益税( )コールにより、譲渡益税を計算する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc譲渡益税(補助口座, TaxTypeEnum, double, Timestamp, TaxTypeEnum)：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@補助口座：　@引数の株式顧客勘定明細Paramsの口座ID、補助口座IDに該当する補助口座<BR>
     * 　@　@　@税区分：　@引数の株式顧客勘定明細Params.注文単位IDに該当する<BR>
     * 　@　@　@　@　@　@　@　@　@注文単位.税区分（現引現渡）<BR>
     * 　@　@　@金額：　@２）で計算した譲渡益金額<BR>
     * 　@　@　@基準日：　@現渡注文の受渡日(*S1)<BR>
     * 　@　@　@顧客税区分：　@顧客.get受渡日税区分( )の戻り値<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@計算結果を、譲渡益税額にセットする。<BR>
     * <BR>
     * 　@　@　@(*S1)引数の株式顧客勘定明細Params.注文単位IDに該当する注文単位オブジェクト.受渡日<BR>
     * <BR>
     * @@param l_finTransactionParams 株式顧客勘定明細Params。
     * @@throws DataException
     * @@roseuid 40CFDC6E03E7
     */
    protected void setMarginNetAmount(EqtypeFinTransactionParams l_finTransactionParams) throws DataException
    {
        final String STR_METHOD_NAME = "setMarginNetAmount";
        log.entering(STR_METHOD_NAME);
        try
        {
            //引数の株式顧客勘定明細Params.建株IDに該当する建株オブジェクトを取得する。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //株式ポジションマネージャ.get建株(引数の株式顧客勘定明細Params.建株ID)を<BR>コールする。
            WEB3EquityContract l_contract = (WEB3EquityContract) l_positionManager.getContract(l_finTransactionParams.getContractId());

            //株式計算サービス.calc諸経費( )コールにより、諸経費合計値(*2)を計算する。
            WEB3EquityBizLogicProvider l_bizLogicProvider = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();

            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_finTransactionParams.getOrderUnitId());
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contract.getDataSourceObject();
            SubAccount l_subAccount = l_accountManager.getSubAccount(l_finTransactionParams.getAccountId(), l_finTransactionParams.getSubAccountId());
            WEB3GentradeMainAccount l_account =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_finTransactionParams.getAccountId());
            
            //引数.トランザクションカテゴリが新規建（EQTYPE_OPEN_MARGIN）
            //返済（EQTYPE_CLOSE_MARGIN）
            //現引現渡（EQTYPE_SWAP_MARGIN）
            //以外の場合は、例外をthrowし処理を終了する。
            if (!FinTransactionCateg.EQTYPE_OPEN_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg())
                && !FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg())
                && !FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg()))
            {
                String msg = "setMarginNetAmount: too much to settle(" + l_finTransactionParams.getFinTransactionCateg() + ")";
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00824, msg);
            }
            //○新規建の場合（＝ 引数.トランザクションカテゴリ＝FinTransactionCateg.EQTYPE_OPEN_MARGIN）
            else if (FinTransactionCateg.EQTYPE_OPEN_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg()))
            {
                //１）　@受渡代金に、0をセットする。
                l_finTransactionParams.setNetAmount(0);
                //２）　@譲渡益金額に、0をセットする。
                l_finTransactionParams.setCapitalGain(0);
                //３）　@譲渡益税額に、0をセットする。
                l_finTransactionParams.setCapitalGainTax(0);

            }
            //○返済の場合（＝ 引数.トランザクションカテゴリ＝FinTransactionCateg.EQTYPE_CLOSE_MARGIN）
            else if (FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg()))
            {
                //１）　@受渡代金（＝決済損益代金）をセットする。
                l_finTransactionParams.setNetAmount(this.calcRealizedProfitAndLossAmount(l_finTransactionParams));

                if (TaxTypeEnum.SPECIAL.equals(l_finTransactionParams.getTaxType()) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_finTransactionParams.getTaxType()))
                {
                    //　@　@　@引数の株式顧客勘定明細Params.税区分＝（"特定"、"特定口座かつ源泉徴収"）の場合、
                    //譲渡益金額に、１－２）でセットした受渡代金をそのままセットする。
                    l_finTransactionParams.setCapitalGain(this.calcRealizedProfitAndLossAmount(l_finTransactionParams));
                }
                else
                {
                    //引数の株式顧客勘定明細Params.税区分≠（"特定"、"特定口座かつ源泉徴収"）の場合、
                    //譲渡益金額に0をセットする。
                    l_finTransactionParams.setCapitalGain(0);
                }
                //３）　@譲渡益税額を計算する。
                //３－２）　@株式計算サービス.calc譲渡益税( )コールにより、譲渡益税額を計算する。
                TaxTypeEnum l_accountTaxType =
                    l_account.getDeliveryDateMarginTaxType(l_orderUnit.getDeliveryDate());
                double l_dblCapitalGainTax =
                    l_bizLogicProvider.calcCapitalGainTax(
                        l_subAccount,
                        l_contractRow.getTaxType(),
                        this.calcRealizedProfitAndLossAmount(l_finTransactionParams),
                        new Timestamp(l_orderUnit.getDeliveryDate().getTime()),
                        l_accountTaxType);
                l_finTransactionParams.setCapitalGainTax(l_dblCapitalGainTax);
            }

            //○現引現渡の場合（＝ 引数.トランザクションカテゴリ＝FinTransactionCateg.EQTYPE_SWAP_MARGIN）
            else if (FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg()))
            {
                //引数.トランザクションタイプが現引（EQTYPE_SWAP_MARGIN_LONG）現渡（EQTYPE_SWAP_MARGIN_SHORT）以外の場合は、例外をthrowし処理を終了する。
                if (!FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(l_finTransactionParams.getFinTransactionType())
                    && !FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(l_finTransactionParams.getFinTransactionType()))
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "トランザクションタイプが不正です。");
                }
                else
                {
                    //１）　@受渡代金を計算する。
                    l_finTransactionParams.setNetAmount(this.calcNetAmountSwap(l_finTransactionParams));
                    //２）　@譲渡益金額を計算する。
                    if (FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(l_finTransactionParams.getFinTransactionType()))
                    {
                        //２－１）　@現引の場合（＝ 引数.トランザクションタイプ＝FinTransactionType.EQTYPE_SWAP_MARGIN_LONG））は、譲渡益金額に、0をセットする。
                        l_finTransactionParams.setCapitalGain(0);
                        //３）　@譲渡益税額を計算する。
                        l_finTransactionParams.setCapitalGainTax(0);
                    }
                    else if (FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(l_finTransactionParams.getFinTransactionType()))
                    {
                        //現渡の場合（＝ 引数.トランザクションタイプ＝FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT））は、
                        //株式計算サービス.calc譲渡損益( )コールにより、
                        //譲渡益金額を計算する。

                        double l_capitaGanin =
                            l_bizLogicProvider.calcEstimatedCapitalGain(
                                l_finTransactionParams.getNetAmount(),
                                l_finTransactionParams.getQuantity(),
                                l_finTransactionParams.getProductId(),
                                l_subAccount,
                                l_orderUnitRow.getSwapTaxType());
                        l_finTransactionParams.setCapitalGain(l_capitaGanin);
                        //　@　@　@計算結果を、譲渡益税額にセットする。
                        TaxTypeEnum l_accountTaxType =
                            l_account.getDeliveryDateTaxType(l_orderUnit.getDeliveryDate());
                        double l_dblCapitalGainTax =
                            l_bizLogicProvider.calcCapitalGainTax(
                                l_subAccount,
                                l_orderUnitRow.getSwapTaxType(),
                                l_capitaGanin,
                                new Timestamp(l_orderUnit.getDeliveryDate().getTime()),
                                l_accountTaxType);
                        l_finTransactionParams.setCapitalGainTax(l_dblCapitalGainTax);

                        //1.3.4.6 get譲渡益有効状態(long, long, long, TaxTypeEnum, FinTransactionType)
                        String l_strCapitalGainStatus = 
                        	l_bizLogicProvider.getCapitalGainStatus(
                        		l_finTransactionParams.getAccountId(), 
    							l_finTransactionParams.getSubAccountId(), 
    							l_finTransactionParams.getProductId(), 
                                l_orderUnitRow.getSwapTaxType(), 
    							l_finTransactionParams.getFinTransactionType());
                        
                        //1.3.4.7  setCapitalGainStatus()
                        l_finTransactionParams.setCapitalGainStatus(l_strCapitalGainStatus);
                    }
                }
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_wbe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_wbe.getMessage(), l_wbe);
        }

    }

    /**
     * （update既約定データ）<BR>
     * <BR>
     * 新規建注文に対する約定取消時に、既約定データに対し<BR>
     * －委託手数料の一口按分<BR>
     * －委託手数料の一口按分の計算結果を、関連するエンティティへ反映<BR>
     * を行う。<BR>
     * <BR>
     * ＜更新対象エンティティ＞<BR>
     * 株式顧客勘定（EqTypeFinTransaction）<BR>
     * 顧客勘定（GenFinTransaction）<BR>
     * 建株（EqTypeContract）：新規建注文の場合のみ<BR>
     * <BR>
     * シーケンス図「（残高）update既約定データ」参照<BR>
     * <BR>
     * １）　@引数の約定.getOrderType( )が、<BR>
     *      新規買建注文（MARGIN_LONG）、新規売建注文（MARGIN_SHORT）<BR>
     *      買建返済注文（CLOSE_MARGIN_LONG）、売建返済注文（CLOSE_MARGIN_SHORT）の<BR>
     * 　@　@　@いずれかの場合は、以下の処理を行う。<BR>
     * 　@　@　@引数の約定.getOrderType( )が上記以外の場合は、処理を終了しreturnする。<BR>
     * <BR>
     * ２）　@引数の取消対象の約定が紐付く注文単位について、<BR>
     * 　@　@　@紐付く株式顧客勘定明細データを全て取得し、<BR>
     * 　@　@　@手数料の一口按分計算を行う。<BR>
     * <BR>
     * ２－１）　@既約定分の株式顧客勘定明細ParamsのListを取得する。<BR>
     * <BR>
     * 　@　@拡張データマネージャ.get株式顧客勘定明細ListBy注文単位(引数の約定.注文単位ID)<BR>
     * 　@　@コールにより取得する。<BR>
     * 　@　@取消対象の注文が返済注文の場合のみ、 <BR>
     *　@　@ 該当データなしの場合は、処理を終了しreturnする。（一口按分計算不要のため） <BR>
     * <BR>
     * ２?２）　@２?１）で該当するデータがあった場合は、 <BR>
     * 　@　@　@　@　@委託手数料の一口約定計算、及び【トランザクションテーブル（株式顧客勘定明細）】の <BR>
     * 　@　@　@　@　@更新及びGTL層への通知を行う。 <BR>
     * <BR>
     * 　@　@株式計算サービス.calc手数料（按分）( )をコールする。<BR>
     *  　@　@引数には、２－１）で取得したListをtoArray( )で配列に変換して設定する。<BR>
     * <BR>
     * 　@　@　@以下、２－１）で取得したListの要素（index）分Loopする。<BR>
     * <BR>
     * ↓↓↓START LOOP↓↓↓<BR>
     * <BR>
     * ２－２－１）　@委託手数料、委託手数料消費税、更新日付をセットする。<BR>
     * <BR>
     * 　@　@　@　@委託手数料：　@ConsolidatedCommissionInfo.getCommission(index)<BR>
     * 　@　@　@　@委託手数料消費税：　@ConsolidatedCommissionInfo.getSalesTax(index)<BR>
     * 　@　@　@　@更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * ２－２－２）　@返済注文の場合<BR>
     *         (引数の約定.getOrderType( )が、<BR>
     *          買建返済注文(CLOSE_MARGIN_LONG)、売建返済注文(CLOSE_MARGIN_SHORT)の<BR>
     *          いずれかの場合)のみ、<BR>
     *          受渡代金、譲渡損益金額、譲渡損益税額に、決済手数料の変動を反映する。<BR>
     * <BR>
     *          this.set信用注文約定時金額(株式顧客勘定Params(index))コールにより反映する。<BR>
     * <BR>
     * ２－２－３）　@　@【トランザクションテーブル（株式顧客勘定明細）】のupdateを行う。<BR>
     * <BR>
     * 　@　@　@　@拡張データマネージャ.updateFinTransaction(株式顧客勘定明細Params(index), <BR>
     * 　@　@　@　@更新対象プロパティMap)により、updateを行う。<BR>
     * 　@　@　@　@※更新対象プロパティMapには、２－２－１）でセットしたプロパティ及び値をセットする。<BR>
     * <BR>
     * ２－２－４）　@【トランザクションテーブル（株式顧客勘定明細）】のupdateをGTL層に通知する。<BR>
     * <BR>
     * 　@　@　@　@this.顧客勘定更新(株式顧客勘定明細Params(index))により<BR>
     * 　@　@　@　@GTL層への通知を行う。<BR>
     * <BR>
     * ↑↑↑END  LOOP↑↑↑<BR>
     * <BR>
     * 返済注文の場合<BR>
     *  (引数の約定.getOrderType( )が、<BR>
     *   買建返済注文(CLOSE_MARGIN_LONG)、売建返済注文(CLOSE_MARGIN_SHORT)の<BR>
     *   いずれかの場合)は、以降の処理は行わずreturnする。<BR>
     *  新規建注文の場合は、以下の処理を行う。<BR>
     * <BR>
     * ３）　@引数の取消対象の約定が紐付く注文単位で作成された建株について、<BR>
     * 　@　@　@２）の手数料の一口按分計算の結果を反映し更新する。<BR>
     * <BR>
     * ３－１）　@既約定時に作成された建株ParamsのListを取得する。<BR>
     * <BR>
     * 　@　@　@　@　@拡張データマネージャ.get建株ListBy注文単位(<BR>
     * 　@　@　@　@　@引数の約定.注文単位ID)により取得する。<BR>
     * <BR>
     * ３－２）　@以下、３－１）で取得したListの要素（index）分Loopする。<BR>
     * <BR>
     * ↓↓↓↓↓↓START LOOP（１）↓↓↓↓↓↓<BR>
     * <BR>
     * ３－２－１）　@建株Params(index)に対し、<BR>
     *　@　@　@　@　@　@　@ 元建手数料、建手数料、元建手数料消費税、建手数料消費税 の設定を行う。<BR>
     * 　@　@　@　@　@　@　@※新規建注文約定時の委託手数料を載せる。<BR>
     * <BR>
     * 　@　@　@元建手数料：　@株式顧客勘定明細Params.委託手数料 のSUM値(*1)<BR>
     * 　@　@　@建手数料：　@株式顧客勘定明細Params.委託手数料 のSUM値(*1)<BR>
     * 　@　@　@元建手数料消費税：　@株式顧客勘定明細Params.委託手数料消費税 のSUM値(*2)<BR>
     * 　@　@　@建手数料消費税：　@株式顧客勘定明細Params.委託手数料消費税 のSUM値(*2)<BR>
     * 　@　@　@更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * 　@　@　@(*1)拡張データマネージャ.get株式顧客勘定明細ListOf新規建注文By建株(<BR>
     * 　@　@　@　@　@建株Params(index).建株ID)で取得した、<BR>
     * 　@　@　@　@　@株式顧客勘定明細ParamsのListの全要素の委託手数料 のSUM値<BR>
     * 　@　@　@　@　@※該当するデータが存在しない場合は、0とする。<BR>
     * <BR>
     * 　@　@　@(*2)拡張データマネージャ.get株式顧客勘定明細ListOf新規建注文By建株(<BR>
     * 　@　@　@　@　@建株Params(index).建株ID)で取得した、<BR>
     * 　@　@　@　@　@株式顧客勘定明細ParamsのListの全要素の委託手数料消費税 のSUM値<BR>
     * 　@　@　@　@　@※該当するデータが存在しない場合は、0とする。<BR>
     * <BR>
     * <BR>
     * ３－２－２）　@建株Params(index)に対する、決済注文約定時の株式顧客勘定明細Paramsを<BR>
     * 　@　@　@　@　@　@　@　@全て取得する。<BR>
     * <BR>
     * 　@　@　@　@　@拡張データマネージャ.get株式顧客勘定明細ListOf決済注文By建株(<BR>
     * 　@　@　@　@　@建株Params(index).建株ID)により取得する。<BR>
     * <BR>
     * ３－２－３）　@決済済数量（Ｙ）を０で初期化する。<BR>
     * <BR>
     * ３－２－４）　@以下、３－２－２）で取得した株式顧客勘定明細ParamsのListの要素（index2）分<BR>
     * 　@　@　@　@　@　@　@　@Loopする。<BR>
     * <BR>
     * ↓↓↓START LOOP（２）↓↓↓<BR>
     * <BR>
     * ３－２－４－１）　@株式顧客勘定明細Params(index2)に対し、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@建株.建手数料、建手数料消費税を再按分する。<BR>
     * <BR>
     * 　@　@　@建手数料：　@建株Params(index).建手数料 ×<BR>
     * 　@　@　@　@　@　@　@　@　@　@（株式顧客勘定明細Params(index2).約定数量 ÷<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@（建株Params(index).元建株数 － Ｙ））(*3)<BR>
     * 　@　@　@建手数料消費税：　@建株Params(index).建手数料消費税 ×<BR>
     * 　@　@　@　@　@　@　@　@　@　@（株式顧客勘定明細Params(index2).約定数量 ÷<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@（建株Params(index).元建株数 － Ｙ））(*3)<BR>
     * 　@　@　@更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * 　@　@　@(*3)最終計算結果を、円未満切捨てする（計算過程では、丸めは行わない）<BR>
     * <BR>
     * ３－２－４－２）　@受渡代金、譲渡益金額、譲渡益税額に、建手数料等の変動を反映する。<BR>
     * <BR>
     * 　@　@　@　@this.set信用注文約定時金額(株式顧客勘定明細Params(index2))コールにより反映する。<BR>
     * <BR>
     * ３－２－４－３）　@　@【トランザクションテーブル（株式顧客勘定明細）】のUpdateを行う。<BR>
     * <BR>
     * 　@　@　@　@拡張データマネージャ.updateFinTransaction(株式顧客勘定明細Params(index2), <BR>
     * 　@　@　@　@更新対象プロパティMap)により、updateを行う。<BR>
     * 　@　@　@　@※更新対象プロパティMapには、３－２－４－１）及び３－２－４－２）でセットした<BR>
     * 　@　@　@　@※プロパティ及び値をセットする。<BR>
     * <BR>
     * ３－２－４－４）　@【トランザクションテーブル（株式顧客勘定明細）】のupdateをGTL層に通知する。<BR>
     * <BR>
     * 　@　@　@　@this.顧客勘定更新(株式顧客勘定明細Params(index2))により<BR>
     * 　@　@　@　@GTL層への通知を行う。<BR>
     * <BR>
     * ３－２－４－５）　@決済済数量（Ｙ）を更新する。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@Ｙ ＝ Ｙ ＋ 株式顧客勘定明細Params(index2).約定数量<BR>
     * <BR>
     * ３－２－４－６）　@建株Params(index)に対し、建手数料、建手数料消費税 の再設定を行う。<BR>
     * 　@　@　@　@　@　@　@　@※決済注文約定時の建手数料、建手数料消費税の徴収を、建株に反映する。<BR>
     * <BR>
     * 　@　@　@建手数料：　@（既存値） － 株式顧客勘定明細Params(index2).建手数料<BR>
     * 　@　@　@建手数料消費税：　@（既存値） － 株式顧客勘定明細Params(index2).建手数料消費税<BR>
     * <BR>
     * ↑↑↑END  LOOP（２）↑↑↑<BR>
     * <BR>
     * ３－２－５）　@【建株テーブル】のUpdateを行う。<BR>
     * <BR>
     * 　@　@　@　@拡張データマネージャ.updateContractByTrans(建株Params(index))により<BR>
     * 　@　@　@　@updateを行う。<BR>
     * <BR>
     * ↑↑↑↑↑↑END  LOOP（１）↑↑↑↑↑↑<BR>
     * <BR>
     * ４）　@returnする。
     * @@param l_orderExecution (約定)<BR>
     * 　@　@　@取消対象の約定データ。
     * @@throws WEB3BaseException
     * @@roseuid 40D0EB7403BD
     */
    protected void updateExecutedData(EqTypeOrderExecution l_orderExecution) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateExecutedData(EqTypeOrderExecution)";
        log.entering(STR_METHOD_NAME);

        // １）　@引数の約定.getOrderType( )が、
        //      新規買建注文（MARGIN_LONG）、新規売建注文（MARGIN_SHORT）
        //      買建返済注文（CLOSE_MARGIN_LONG）、売建返済注文（CLOSE_MARGIN_SHORT）の
        // 　@　@　@いずれかの場合は、以下の処理を行う。
        // 　@　@　@引数の約定.getOrderType( )が上記以外の場合は、処理を終了しreturnする。
        WEB3EquityPositionManagerHelper l_positionManagerHelper = new WEB3EquityPositionManagerHelper(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManagerHelper.WEB3EquityPersistentDataManager l_persistentDataManager =
            (WEB3EquityPositionManagerHelper.WEB3EquityPersistentDataManager) l_positionManagerHelper.getPersistenceManager();
        EqtypeOrderExecutionRow l_orderExecutionRow = (EqtypeOrderExecutionRow) l_orderExecution.getDataSourceObject();
        //EqtypeFinTransactionRow[]  l_finTransactionRow = null;
        if (!OrderTypeEnum.MARGIN_LONG.equals(l_orderExecutionRow.getOrderType()) && !OrderTypeEnum.MARGIN_SHORT.equals(l_orderExecutionRow.getOrderType())
            && !OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderExecution.getOrderType()) && !OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderExecution.getOrderType()))
        {
            return;
        }

        // ２）　@引数の取消対象の約定が紐付く注文単位について、
        // 　@　@　@紐付く株式顧客勘定明細データを全て取得し、
        // 　@　@　@手数料の一口按分計算を行う。
        // ２－１）　@既約定分の株式顧客勘定明細ParamsのListを取得する。
        List l_lstEqtypeFinTransaction = l_persistentDataManager.getFinTransactionListByOrderUnit(l_orderExecution.getOrderUnitId());
        
        // 約定取消対象の注文 == 返済注文、かつ 該当データなしの場合は、処理を終了しreturnする。（一口按分計算不要のため）
        if ((OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderExecution.getOrderType()) 
            || OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderExecution.getOrderType()))
            && l_lstEqtypeFinTransaction.size() == 0)
        {
            return;
        }
        
        // ２－２）　@２－１）で該当するデータがあった場合は、
        // 　@　@　@　@　@委託手数料の一口約定計算、及び【トランザクションテーブル（株式顧客勘定明細）】の
        // 　@　@　@　@　@更新及びGTL層への通知を行う。
        // 　@　@株式計算サービス.calc手数料（按分）( )をコールする
        if (l_lstEqtypeFinTransaction.size() != 0)
        {
	        EqtypeFinTransactionParams[] l_finTransactionParams = null;
	        l_finTransactionParams = new EqtypeFinTransactionParams[l_lstEqtypeFinTransaction.size()];
	        l_lstEqtypeFinTransaction.toArray(l_finTransactionParams);
	        
	        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
	        WEB3EquityBizLogicProvider l_ifoBizLogicProvider = null;
	        l_ifoBizLogicProvider = (WEB3EquityBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
	        ConsolidatedCommissionInfo l_CommissionInfo = l_ifoBizLogicProvider.calcCommission(l_finTransactionParams);
	        int l_intParams = 0;
	        if (l_finTransactionParams != null)
	        {
	            l_intParams = l_finTransactionParams.length;
	        }
            // 以下、２－１）で取得したListの要素（index）分Loopする。
            for (int i = 0; i < l_intParams; i++)
            {
                // ２－２－１）　@委託手数料、委託手数料消費税、更新日付をセットする。
                double l_dblCommission = l_CommissionInfo.getCommission(i);
                l_finTransactionParams[i].setCommissionFee(l_dblCommission);
                double ldblSalesTax = l_CommissionInfo.getSalesTax(i);
                l_finTransactionParams[i].setCommissionFeeTax(ldblSalesTax);
                l_finTransactionParams[i].setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                HashMap l_map = new HashMap();
                // ２－２－２）　@返済注文の場合<BR>
                //         (引数の約定.getOrderType( )が、<BR>
                //          買建返済注文(CLOSE_MARGIN_LONG)、売建返済注文(CLOSE_MARGIN_SHORT)の<BR>
                //          いずれかの場合)のみ、<BR>
                //          受渡代金、譲渡損益金額、譲渡損益税額に、決済手数料の変動を反映する。<BR>
                if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderExecution.getOrderType())
                    || OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderExecution.getOrderType()))
                {
                    try
                    {
	                    this.setMarginNetAmount(l_finTransactionParams[i]);
                    }
                    catch (DataException l_ex)
                    {
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "set信用注文約定時金額()に失敗しました トランザクションID:[" + l_finTransactionParams[i].getFinTransactionId() + "]",
                            l_ex);
                    }
	                l_map.put("net_amount", new Double(l_finTransactionParams[i].getNetAmount()));
	                l_map.put("capital_gain", new Double(l_finTransactionParams[i].getCapitalGain()));
	                l_map.put("capital_gain_tax", new Double(l_finTransactionParams[i].getCapitalGainTax()));
                    l_map.put("capital_gain_status", WEB3CapitalGainStatusDef.INVALIDITY);
                }
                
                // ２－２－３）　@　@【トランザクションテーブル（株式顧客勘定明細）】のupdateを行う。
                l_map.put("commission_fee", new Double(l_finTransactionParams[i].getCommissionFee()));
                l_map.put("commission_fee_tax", new Double(l_finTransactionParams[i].getCommissionFeeTax()));
                l_map.put("last_updated_timestamp", l_finTransactionParams[i].getLastUpdatedTimestamp());
                try
                {
	                l_persistentDataManager.updateFinTransaction(l_finTransactionParams[i], l_map);
                }
                catch (DataException l_ex)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "トランザクションの更新処理に失敗しました。" + l_finTransactionParams[i].toString(),
                        l_ex);
                }
                //２－２－４）　@【トランザクションテーブル（株式顧客勘定明細）】のupdateをGTL層に通知する。
                this.notifyGtl(l_finTransactionParams[i]);
            }
        }
            
        // 返済注文の場合
        //  (引数の約定.getOrderType( )が、
        //   買建返済注文(CLOSE_MARGIN_LONG)、売建返済注文(CLOSE_MARGIN_SHORT)の
        //   いずれかの場合)は、以降の処理は行わずreturnする。
        //  新規建注文の場合は、以下の処理を行う。
        if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderExecution.getOrderType())
            || OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderExecution.getOrderType()))
        {
            return;
        }
            
        //  ３）　@引数の取消対象の約定が紐付く注文単位で作成された建株について、
        // 　@　@　@２）の手数料の一口按分計算の結果を反映し更新する。
        // ３－１）　@既約定時に作成された建株ParamsのListを取得する。
        EqtypeFinTransactionParams[] l_finTransactionParams = null;
        List l_lstByOrderUnit = l_persistentDataManager.getContractListByOrderUnit(l_orderExecution.getOrderUnitId());
        EqtypeContractParams[] l_eqtypeContractParams = null;
        l_eqtypeContractParams = new EqtypeContractParams[l_lstByOrderUnit.size()];
        l_lstByOrderUnit.toArray(l_eqtypeContractParams);
        int l_intContractParams = 0;
        if (l_eqtypeContractParams != null)
        {
            l_intContractParams = l_eqtypeContractParams.length;
        }
        // ３－２）　@以下、３－１）で取得したListの要素（index）分Loopする。
        for (int i = 0; i < l_intContractParams; i++)
        {
            // ３－２－１）　@建株Params(index)に対し、
            //　@　@　@　@　@　@　@ 元建手数料、建手数料、元建手数料消費税、建手数料消費税 の設定を行う。
            //　@　@　@　@　@　@　@ ※新規建注文約定時の委託手数料を載せる。
            List l_openOrderByContract = l_persistentDataManager.getFinTransactionListOfOpenOrderByContract(l_eqtypeContractParams[i].getContractId());
            l_finTransactionParams = new EqtypeFinTransactionParams[l_openOrderByContract.size()];
            l_openOrderByContract.toArray(l_finTransactionParams);
            double l_dblCommissionFee = 0D;
            double l_dblCommissionFeeTax = 0D;
            int l_intTransactionParamsLength = 0;
            if (l_finTransactionParams != null)
            {
                l_intTransactionParamsLength = l_finTransactionParams.length;
            }
            for (int j = 0; j < l_intTransactionParamsLength; j++)
            {
                l_dblCommissionFee += l_finTransactionParams[j].getCommissionFee();
                l_dblCommissionFeeTax += l_finTransactionParams[j].getCommissionFeeTax();
            }

            // 元建手数料
            l_eqtypeContractParams[i].setOriginalSetupFee(l_dblCommissionFee);
            // 建手数料
            l_eqtypeContractParams[i].setSetupFee(l_dblCommissionFee);
            // 元建手数料消費税
            l_eqtypeContractParams[i].setOriginalSetupFeeTax(l_dblCommissionFeeTax);
            // 建手数料消費税
            l_eqtypeContractParams[i].setSetupFeeTax(l_dblCommissionFeeTax);
            // 更新日付
            l_eqtypeContractParams[i].setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            // ３－２－２）　@建株Params(index)に対する、決済注文約定時の株式顧客勘定明細Paramsを全て取得する。
            List l_lsteqtypeFinTransaction = l_persistentDataManager.getFinTransactionListOfCloseOrderByContract(l_eqtypeContractParams[i].getContractId());
            int l_intTransactionlength = 0;
            if (l_lsteqtypeFinTransaction != null)
            {
                l_intTransactionlength = l_lsteqtypeFinTransaction.size();
            }

            // ３－２－３）　@決済済数量（Ｙ）を０で初期化する。
            double l_Y = 0D;

            // ３－２－４）　@以下、３－２－２）で取得した株式顧客勘定明細ParamsのListの要素（index2）分Loopする。
            for (int k = 0; k < l_intTransactionlength; k++)
            {
                EqtypeFinTransactionParams l_finParams1 = (EqtypeFinTransactionParams) l_lsteqtypeFinTransaction.get(k);
                // ３－２－４－１）　@株式顧客勘定明細Params(index2)に対し、建株.建手数料、建手数料消費税を再按分する。
                l_dblCommissionFee = Math.floor(l_eqtypeContractParams[i].getSetupFee() * (l_finParams1.getQuantity() / (l_eqtypeContractParams[i].getOriginalQuantity() - l_Y)));
                l_dblCommissionFeeTax = Math.floor(l_eqtypeContractParams[i].getSetupFeeTax() * (l_finParams1.getQuantity() / (l_eqtypeContractParams[i].getOriginalQuantity() - l_Y)));
                l_finParams1.setImportedSetupFee(l_dblCommissionFee);
                l_finParams1.setImportedSetupFeeTax(l_dblCommissionFeeTax);
                l_finParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                // ３－２－４－２）　@受渡代金、譲渡益金額、譲渡益税額に、建手数料等の変動を反映する。
                try
                {
	                this.setMarginNetAmount(l_finParams1);
                }
                catch (DataException l_ex)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "set信用注文約定時金額()に失敗しました トランザクションID:[" + l_finParams1.getFinTransactionId() + "]",
                        l_ex);
                }
                // ３－２－４－３）　@　@【トランザクションテーブル（株式顧客勘定明細）】のUpdateを行う。
                HashMap l_map = new HashMap();
                l_map.put("net_amount", new Double(l_finParams1.getNetAmount()));
                l_map.put("capital_gain", new Double(l_finParams1.getCapitalGain()));
                l_map.put("capital_gain_tax", new Double(l_finParams1.getCapitalGainTax()));
                l_map.put("imported_setup_fee", new Double(l_finParams1.getImportedSetupFee()));
                l_map.put("imported_setup_fee_tax", new Double(l_finParams1.getImportedSetupFeeTax()));
                l_map.put("last_updated_timestamp", l_finParams1.getLastUpdatedTimestamp());
                l_map.put("capital_gain_status", l_finParams1.getCapitalGainStatus());
                
                try
                {
	                l_persistentDataManager.updateFinTransaction(l_finParams1, l_map);
                }
                catch (DataException l_ex)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "トランザクションの更新処理に失敗しました。" + l_finParams1.toString(),
                        l_ex);
                }
                // ３－２－４－４）　@【トランザクションテーブル（株式顧客勘定明細）】のupdateをGTL層に通知する。
                this.notifyGtl(l_finParams1);

                // ３－２－４－５）　@決済済数量（Ｙ）を更新する。
                // 　@　@　@　@　@　@　@Ｙ ＝ Ｙ ＋ 株式顧客勘定明細Params(index2).約定数量
                l_Y += l_finParams1.getQuantity();
                // ３－２－４－６）　@建株Params(index)に対し、建手数料、建手数料消費税 の再設定を行う。
                // 　@　@　@建手数料：　@（既存値） － 株式顧客勘定明細Params(index2).建手数料
                l_eqtypeContractParams[i].setSetupFee(l_eqtypeContractParams[i].getSetupFee() - l_finParams1.getImportedSetupFee());
                // 　@　@　@建手数料消費税：　@（既存値） － 株式顧客勘定明細Params(index2).建手数料消費税
                l_eqtypeContractParams[i].setSetupFeeTax(l_eqtypeContractParams[i].getSetupFeeTax() - l_finParams1.getImportedSetupFeeTax());
            }
            // ３－２－５）　@【建株テーブル】のUpdateを行う。
            try
            {
	            l_persistentDataManager.updateContractByTrans(l_eqtypeContractParams[i]);
            }
            catch (DataException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "建株テーブルの更新処理に失敗しました。" + l_eqtypeContractParams[i].toString(),
                    l_ex);
            }
        }
    }

    /**
     * (calc決済損益代金)<BR>
     * 決済損益代金を計算し返却する。<BR>
     * （* 返済注文約定時の、決済損益代金の計算を行うメソッド）<BR>
     * <BR>
     * １）　@引数の株式顧客勘定明細Params.建株IDに該当する建株オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@株式ポジションマネージャ.get建株(引数の株式顧客勘定明細Params.建株ID)を<BR>
     * 　@　@　@コールする。<BR>
     * <BR>
     * ２）　@今回約定分について、建代金(*1)を計算する。<BR>
     * <BR>
     * 　@　@　@建代金(*1) ＝ 取得した建株.建単価 × 引数の株式顧客勘定明細<BR>
     * Params.約定数量<BR>
     * <BR>
     * 　@　@　@※株式分割等により、建単価には小数点以下第２位まで値が入っていることがあるので、<BR>
     * 　@　@　@※計算結果の円未満切捨を行う。<BR>
     * <BR>
     * ３）　@今回約定分について、諸経費合計値(*2)を計算する。<BR>
     * <BR>
     * 　@　@　@株式計算サービス.calc諸経費( )コールにより、諸経費合計値(*2)を計算する。<BR>
     * 　@　@　@-------------------------------------------------------<BR>
     * 　@　@　@＜calc諸経費( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@委託手数料、委託手数料消費税、<BR>
     * 　@　@　@建手数料、建手数料消費税、名義書換料、<BR>
     * 名義書換料消費税、管理費、管理費消費税、<BR>
     * 　@　@　@順日歩、逆日歩、貸株料、その他<BR>
     * 　@　@　@　@　@　@　@：　@引数の株式顧客勘定明細Paramsの同名プロパティをそれぞれセットする。<BR>
     * <BR>
     * 　@　@　@建区分：　@取得した建株.建区分<BR>
     * 　@　@　@-------------------------------------------------------<BR>
     * <BR>
     * ４）　@今回約定分について、売買代金（返済）(*3)を計算する。<BR>
     * <BR>
     * 　@　@　@売買代金（返済）(*3) ＝<BR>
     *  引数の株式顧客勘定明細Params.約定単価 ×<BR>
     * 　@　@　@　@　@引数の株式顧客勘定明細Params.約定数量<BR>
     * <BR>
     * ５）　@決済損益代金を計算する。<BR>
     * <BR>
     * 　@　@　@＜取得した建株.建区分＝”買建”の場合＞<BR>
     * 　@　@　@決済損益代金 ＝ <BR>
     * 売買代金（返済）(*3) － 建代金(*1) － 諸経費合計値(*2)<BR>
     * <BR>
     * 　@　@　@＜取得した建株.建区分＝”売建”の場合＞<BR>
     * 　@　@　@決済損益代金 ＝<BR>
     *  建代金(*1) － 売買代金（返済）(*3) － 諸経費合計値(*2)<BR>
     * <BR>
     * ６）　@５）の計算結果を返却する。
     * @@param l_finTransactionParams 株式顧客勘定明細Params。
     * @@return double
     * @@roseuid 40DBC85D036F
     */
    public double calcRealizedProfitAndLossAmount(EqtypeFinTransactionParams l_finTransactionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcRealizedProfitAndLossAmount";
        log.entering(STR_METHOD_NAME);
        //引数の株式顧客勘定明細Params.建株IDに該当する建株オブジェクトを取得する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        //株式ポジションマネージャ.get建株(引数の株式顧客勘定明細Params.建株ID)を<BR>コールする。
        WEB3EquityContract l_contract = null;
        try
        {
            l_contract = (WEB3EquityContract) l_positionManager.getContract(l_finTransactionParams.getContractId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }

        // ２）　@今回約定分について、建代金(*1)を計算する。
        //建代金(*1) ＝ 取得した建株.建単価 × 引数の株式顧客勘定明細Params.約定数量
        double l_dblContractPrice = new BigDecimal(l_contract.getContractPrice() * l_finTransactionParams.getQuantity()).longValue();
        EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contract.getDataSourceObject();
        //建区分：　@取得した建株.建区分
        ContractTypeEnum l_contractTypeEnum = l_contractRow.getContractType();
        // ３）　@今回約定分について、諸経費合計値(*2)を計算する。
        //株式計算サービス.calc諸経費( )コールにより、諸経費合計値(*2)を計算する。
        WEB3EquityBizLogicProvider l_bizLogicProvider = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
        //委託手数料、委託手数料消費税、建手数料、建手数料消費税、名義書換料、
        //名義書換料消費税、管理費、管理費消費税、順日歩、逆日歩、貸株料、その他
        //：　@引数の株式顧客勘定明細Paramsの同名プロパティをそれぞれセットする。
        double l_dblExpenses =
            l_bizLogicProvider.calcExpenses(
                l_finTransactionParams.getCommissionFee(),
                l_finTransactionParams.getCommissionFeeTax(),
                l_finTransactionParams.getImportedSetupFee(),
                l_finTransactionParams.getImportedSetupFeeTax(),
                l_finTransactionParams.getImportedNameTransferFee(),
                l_finTransactionParams.getImportedNameTransferFeeTax(),
                l_finTransactionParams.getImportedManagementFee(),
                l_finTransactionParams.getImportedManagementFeeTax(),
                l_finTransactionParams.getImportedInterestFee(),
                l_finTransactionParams.getImportedPayInterestFee(),
                l_finTransactionParams.getImportedLoanEquityFee(),
                l_finTransactionParams.getImportedOther(),
                l_contractTypeEnum);

        //今回約定分について、売買代金（返済）(*3)を計算する。 
        //売買代金（返済）(*3) ＝引数の株式顧客勘定明細Params.約定単価 ×引数の株式顧客勘定明細Params.約定数量
        double l_price = l_finTransactionParams.getPrice() * l_finTransactionParams.getQuantity();

        // ５）　@決済損益代金を計算する。

        //＜取得した建株.建区分＝”買建”の場合＞決済損益代金 ＝ 売買代金（返済）売買代金（返済）(*3) － 建代金(*1) － 諸経費合計値(*2)
        double l_dblIncome = 0D;
        if (ContractTypeEnum.LONG.equals(l_contractTypeEnum))
        {
            l_dblIncome = l_price - l_dblContractPrice - l_dblExpenses;
        }
        //＜取得した建株.建区分＝”売建”の場合＞決済損益代金 ＝建代金(*1) － 売買代金（返済）(*3) － 諸経費合計値(*2)
        else if (ContractTypeEnum.SHORT.equals(l_contractTypeEnum))
        {
            l_dblIncome = l_dblContractPrice - l_price - l_dblExpenses;
        }

        //６）　@５）の計算結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblIncome;
    }

    /**
     * (set株式顧客勘定明細デフォルト値)<BR>
     * 引数の株式顧客勘定明細Paramsのプロパティに、デフォルト値をセットする。<BR>
     * （void setMarketOrderedTransDefaultValues(EqtypeFinTransactionParams  tparams)<BR>
     *   のオーバーライド）<BR>
     * <BR>
     * 引数の株式顧客勘定明細Paramsのプロパティに、以下の通りに値をセット：<BR>
     * ----------------------------------<BR>
     * トランザクション発生日時：GtlUtils.getSystemTimestamp()<BR>
     * 委託手数料：0<BR>
     * 委託手数料消費税：0<BR>
     * 建株の受渡金額：0<BR>
     * 建手数料：0<BR>
     * 建手数料消費税：0<BR>
     * 名義書換料：0<BR>
     * 名義書換料消費税：0<BR>
     * 譲渡益金額：0<BR>
     * 譲渡益税額：0<BR>
     * 管理費：0<BR>
     * 管理費消費税：0<BR>
     * 順日歩：0<BR>
     * 順日歩消費税：0<BR>
     * 逆日歩：0<BR>
     * 逆日歩消費税：0<BR>
     * 貸株料：0<BR>
     * その他：0<BR>
     * 売却保有資産の管理費：0<BR>
     * 売却保有資産の管理費消費税：0<BR>
     * 売却保有資産の手数料：0<BR>
     * 売却保有資産の手数料消費税：0<BR>
     * 資産の簿価：0<BR>
     * 削除フラグ：0（FALSE）<BR>
     * 債券所得経過利子：0<BR>
     * 作成日付：GtlUtils.getSystemTimestamp()<BR>
     * 更新日付：GtlUtils.getSystemTimestamp()<BR>
     * ----------------------------------
     * @@param l_finTransactionParams 株式顧客勘定明細Paramsオブジェクト。
     * @@roseuid 40DF561103DE
     */
    protected void setMarketOrderedTransDefaultValues(EqtypeFinTransactionParams l_finTransactionParams)
    {
        final String STR_METHOD_NAME = "setMarketOrderedTransDefaultValues";
        log.entering(STR_METHOD_NAME);
        //引数の株式顧客勘定明細Paramsのプロパティに、以下の通りに値をセット：
        //トランザクション発生日時：GtlUtils.getSystemTimestamp()
        l_finTransactionParams.setFinTransactionTimestamp(GtlUtils.getSystemTimestamp());
        //委託手数料
        l_finTransactionParams.setCommissionFee(0);
        //委託手数料消費税
        l_finTransactionParams.setCommissionFeeTax(0);
        //建株の受渡金額
        l_finTransactionParams.setImportedPaidValue(0);
        //建手数料
        l_finTransactionParams.setImportedSetupFee(0);
        //建手数料消費税
        l_finTransactionParams.setImportedSetupFeeTax(0);
        //名義書換料
        l_finTransactionParams.setImportedNameTransferFee(0);
        //名義書換料消費税
        l_finTransactionParams.setImportedNameTransferFeeTax(0);
        //譲渡益金額
        l_finTransactionParams.setCapitalGain(0);
        //譲渡益税額
        l_finTransactionParams.setCapitalGainTax(0);
        //管理費
        l_finTransactionParams.setImportedManagementFee(0);
        //管理費消費税
        l_finTransactionParams.setImportedManagementFeeTax(0);
        //順日歩
        l_finTransactionParams.setImportedInterestFee(0);
        //順日歩消費税
        l_finTransactionParams.setImportedInterestFeeTax(0);
        //逆日歩
        l_finTransactionParams.setImportedPayInterestFee(0);
        //逆日歩消費税
        l_finTransactionParams.setImportedPayInterestFeeTax(0);
        //貸株料
        l_finTransactionParams.setImportedLoanEquityFee(0);
        //その他
        l_finTransactionParams.setImportedOther(0);
        //売却保有資産の管理費
        l_finTransactionParams.setTransferedAssetMngFee(0);
        //売却保有資産の管理費消費税
        l_finTransactionParams.setTransferedAssetMngFeeTax(0);
        //売却保有資産の手数料
        l_finTransactionParams.setTransferedAssetSetupFee(0);
        //売却保有資産の手数料消費税
        l_finTransactionParams.setTransferedAssetMngFeeTax(0);
        //資産の簿価
        l_finTransactionParams.setTransferedAssetBookValue(0);
        //削除フラグ
        l_finTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
        //債券所得経過利子
        l_finTransactionParams.setAccruedInterest(0);
        //作成日付
        l_finTransactionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付
        l_finTransactionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc受渡代金（現引現渡）)<BR>
     * 現引現渡注文約定時の受渡代金を計算し返却する。<BR>
     * <BR>
     * １）　@引数の株式顧客勘定明細Params.建株IDに該当する建株オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@株式ポジションマネージャ.get建株(引数の株式顧客勘定明細Params.建株ID)を<BR>
     * 　@　@　@コールする。<BR>
     * <BR>
     * ２）　@受渡代金を計算し、計算結果を返却する。<BR>
     * <BR>
     * 　@　@＜取得した建株.建区分＝”買建”の場合＞<BR>
     * 　@　@受渡代金 ＝ － （約定数量 × 約定単価） － （諸経費合計値(*)）<BR>
     * <BR>
     * 　@　@＜取得した建株.建区分＝”売建”の場合＞<BR>
     * 　@　@受渡代金 ＝ （約定数量 × 約定単価） － （諸経費合計値(*)）<BR>
     * <BR>
     * 　@　@(*)諸経費合計値：<BR>
     * 　@　@株式計算サービス.calc諸経費( )コールにより、諸経費合計値を計算する。<BR>
     * 　@　@---------------------------------------------------------<BR>
     * 　@　@＜calc諸経費( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@委託手数料、委託手数料消費税、<BR>
     * 　@　@建手数料、建手数料消費税、名義書換料、<BR>
     * 名義書換料消費税、管理費、管理費消費税、<BR>
     * 　@　@順日歩、逆日歩、貸株料、その他<BR>
     * 　@　@　@　@　@　@：　@引数の株式顧客勘定明細Paramsの同名プロパティをそれぞれセットする。<BR>
     * <BR>
     * 　@　@建区分：　@取得した建株.建区分<BR>
     * 　@　@------------------------------------------------------
     * @@param l_finTransactionParams 株式顧客勘定明細Params。
     * @@return double
     * @@roseuid 40E21C6B027F
     */
    public double calcNetAmountSwap(EqtypeFinTransactionParams l_finTransactionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcNetAmountSwap";
        log.entering(STR_METHOD_NAME);
        try
        {
            //引数の株式顧客勘定明細Params.建株IDに該当する建株オブジェクトを取得する。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

            //株式ポジションマネージャ.get建株(引数の株式顧客勘定明細Params.建株ID)を<BR>コールする。
            WEB3EquityContract l_contract = (WEB3EquityContract) l_positionManager.getContract(l_finTransactionParams.getContractId());

            EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contract.getDataSourceObject();
            //建区分：　@取得した建株.建区分
            ContractTypeEnum l_contractTypeEnum = l_contractRow.getContractType();

            //株式計算サービス.calc諸経費( )コールにより、諸経費合計値(*2)を計算する。
            WEB3EquityBizLogicProvider l_bizLogicProvider = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            //委託手数料、委託手数料消費税、建手数料、建手数料消費税、名義書換料、
            //名義書換料消費税、管理費、管理費消費税、順日歩、逆日歩、貸株料、その他
            //：　@引数の株式顧客勘定明細Paramsの同名プロパティをそれぞれセットする。
            double l_dblExpenses =
                l_bizLogicProvider.calcExpenses(
                    l_finTransactionParams.getCommissionFee(),
                    l_finTransactionParams.getCommissionFeeTax(),
                    l_finTransactionParams.getImportedSetupFee(),
                    l_finTransactionParams.getImportedSetupFeeTax(),
                    l_finTransactionParams.getImportedNameTransferFee(),
                    l_finTransactionParams.getImportedNameTransferFeeTax(),
                    l_finTransactionParams.getImportedManagementFee(),
                    l_finTransactionParams.getImportedManagementFeeTax(),
                    l_finTransactionParams.getImportedInterestFee(),
                    l_finTransactionParams.getImportedPayInterestFee(),
                    l_finTransactionParams.getImportedLoanEquityFee(),
                    l_finTransactionParams.getImportedOther(),
                    l_contractTypeEnum);

            double l_dblDeliveryAmount = 0D;
            //取得した建株.建区分＝”買建”の場合
            if (ContractTypeEnum.LONG.equals(l_contractTypeEnum))
            {
                //受渡代金 ＝ － （約定数量 × 約定単価） － （諸経費合計値(*)）
                l_dblDeliveryAmount = - (l_finTransactionParams.getPrice() * l_finTransactionParams.getQuantity()) - l_dblExpenses;
            }
            //取得した建株.建区分＝”売建”の場合
            else if (ContractTypeEnum.SHORT.equals(l_contractTypeEnum))
            {
                //受渡代金 ＝ （約定数量 × 約定単価） － （諸経費合計値(*)）
                l_dblDeliveryAmount = (l_finTransactionParams.getPrice() * l_finTransactionParams.getQuantity()) - l_dblExpenses;
            }

            log.exiting(STR_METHOD_NAME);
            return l_dblDeliveryAmount;
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
    }

    /**
     * (giveBack諸経費To建株By株式顧客勘定明細)<BR>
     * 引数の株式顧客勘定明細Paramsの諸経費を、建株Paramsの諸経費に戻す。<BR>
     * （void giveBackCostToContractByTrans(EqtypeContractParams cparams,<BR> 
     * 　@EqtypeFinTransactionParams tparams)のオーバーライド）<BR>
     * <BR>
     * ---------------------------------------------------------<BR>
     * ○引数の建株Paramsの更新対象プロパティ（諸経費項目）は以下の通り。<BR>
     * ※建株Paramsと株式顧客勘定明細Paramsとで、プロパティの物理名が異なるので注意。<BR>
     * <BR>
     * ----------------------------------<BR>
     * 建手数料<BR>
     * 建手数料消費税<BR>
     * 名義書換料<BR>
     * 名義書換料消費税<BR>
     * 管理費<BR>
     * 管理費消費税<BR>
     * 順日歩<BR>
     * 逆日歩<BR>
     * 貸株料(*1)<BR>
     * その他<BR>
     * <BR>
     * (*1)引数の建株Params.建区分＝"売建"（SHORT）の場合のみ、<BR>
     * 　@　@　@建株Params.貸株料の設定値を更新する。<BR>
     * 　@　@　@引数の建株Params.建区分＝"買建"（LONG）の場合は、<BR>
     * 　@　@　@建株Params.貸株料の設定値は更新しない。（既存値のままとする）<BR>
     * <BR>
     * ---------------------------------------------------------<BR>
     * ○引数の建株Paramsの更新の計算式は以下の通り。<BR>
     * <BR>
     * 引数の建株Paramsのプロパティ ＝ 現在の値<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@＋ 引数の株式顧客勘定明細Paramsの同名プロパティの値<BR>
     * <BR>
     * ex.) 引数の建株Params.建手数料 ＝ 引数の建株Params.建手数料<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@＋ 引数の株式顧客勘定明細Params.建<BR>
     * 手数料
     * @@param l_contractParams - (建株Params)<BR>
     * 　@　@　@今回約定対象の、建株Params。
     * @@param l_finTransactionParams 株式顧客勘定明細Params。
     * @@roseuid 40EBA03501F2
     */
    protected void giveBackCostToContractByTrans(EqtypeContractParams l_contractParams, EqtypeFinTransactionParams l_finTransactionParams)
    {
        final String STR_METHOD_NAME = "giveBackCostToContractByTrans";
        log.entering(STR_METHOD_NAME);

        //○引数の建株Paramsの更新対象プロパティ（諸経費項目）は以下の通り。
        // 建手数料
        l_contractParams.setSetupFee(l_contractParams.getSetupFee() + l_finTransactionParams.getImportedSetupFee());
        //建手数料消費税
        l_contractParams.setSetupFeeTax(l_contractParams.getSetupFeeTax() + l_finTransactionParams.getImportedSetupFeeTax());
        //名義書換料
        l_contractParams.setNameTransferFee(l_contractParams.getNameTransferFee() + l_finTransactionParams.getImportedNameTransferFee());
        //名義書換料消費税
        l_contractParams.setNameTransferFeeTax(l_contractParams.getNameTransferFeeTax() + l_finTransactionParams.getImportedNameTransferFeeTax());
        //管理費
        l_contractParams.setManagementFee(l_contractParams.getManagementFee() + l_finTransactionParams.getImportedManagementFee());
        //管理費消費税
        l_contractParams.setManagementFeeTax(l_contractParams.getManagementFeeTax() + l_finTransactionParams.getImportedManagementFeeTax());
        //順日歩
        l_contractParams.setInterestFee(l_contractParams.getInterestFee() + l_finTransactionParams.getImportedInterestFee());
        //逆日歩
        l_contractParams.setPayInterestFee(l_contractParams.getPayInterestFee() + l_finTransactionParams.getImportedPayInterestFee());

        //引数の建株Params.建区分＝"売建"（SHORT）の場合のみ、建株Params.貸株料の設定値を更新する。
        if (ContractTypeEnum.SHORT.equals(l_contractParams.getContractType()))
        {
            l_contractParams.setLoanEquityFee(l_contractParams.getLoanEquityFee() + l_finTransactionParams.getImportedLoanEquityFee());
        }
        //その他
        l_contractParams.setOther(l_contractParams.getOther() + l_finTransactionParams.getImportedOther());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （reverse保有資産ParamsBy株式顧客勘定明細）<BR>
     * <BR>
     * 引数の株式顧客勘定明細Paramsより、約定取消を保有資産Paramsに反映する。<BR>
     * （void reverseAssetParamsByMarketTradedTrans(AssetParams aparams, <BR>
     * 　@EqtypeFinTransactionParams trans)のオーバーライド）<BR>
     * <BR>
     * ○保有資産Paramsの更新対象プロパティ、及び更新仕様は以下の通り。<BR>
     * <BR>
     * 数量：　@<BR>
     * 　@　@　@　@　@買約定（SideEnum.BUY）(*1)の約定取消の場合は、保有資産残数量チェックを行う。<BR>
     * 　@　@　@　@　@-------------------------------------------------------------------<BR>
     * 　@　@　@　@　@保有資産残数量(*) ＜ 0.0D の場合、<BR>
     * 　@　@　@　@　@「保有資産残数量チェックエラー」の例外（業務エラー）をthrowする。<BR>
     * 　@　@　@　@　@　@class: WEB3BaseRuntimeException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_01931<BR>
     * <BR>
     * 　@　@　@　@　@(*)保有資産残数量<BR>
     * 　@　@　@　@　@　@　@　@　@保有資産Params.数量 － 株式顧客勘定明細Params.約定数量<BR>
     * 　@　@　@　@　@-------------------------------------------------------------------<BR>
     * 　@　@　@　@　@保有資産残数量チェックがOKの場合、引数の株式顧客勘定明細Params.約定数量を減算。<BR>
     * 　@　@　@　@　@売約定（SideEnum.SELL）(*1)の約定取消の場合は、<BR>
     * 　@　@　@　@　@引数の株式顧客勘定明細Params.約定数量 を加算。<BR>
     * <BR>
     * 数量（簿価単価計算用）：<BR>
     * 　@　@　@　@　@買約定（SideEnum.BUY）(*1)の約定取消の場合は、<BR>
     * 　@　@　@　@　@引数の株式顧客勘定明細Params.約定数量 を減算。<BR>
     * 　@　@　@　@　@売約定（SideEnum.SELL）(*1)の約定取消の場合は、更新なし。<BR>
     * <BR>
     * 簿価（簿価単価計算用）：<BR>
     * 　@　@　@　@　@買約定（SideEnum.BUY）(*1)の約定取消の場合は、<BR>
     * 　@　@　@　@　@更新対象とする場合(*2)のみ、<BR>
     * 　@　@　@　@　@「引数の株式顧客勘定明細Params.受渡代金 × （－１）」を減算。<BR>
     * 　@　@　@　@　@売約定（SideEnum.SELL）(*1)の約定取消の場合は、更新なし。<BR>
     * <BR>
     * 更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * (*1)買約定／売約定の判定<BR>
     * 拡張ポジションヘルパー.getSide(引数の株式顧客勘定明細Params.トランザクションタイプ)により<BR>
     * 取得したSideEnumを使用し、判定を行う。<BR>
     * <BR>
     * (*2)更新対象とする場合<BR>
     * （（保有資産Params.税区分≠"特定口座"または"特定口座かつ源泉徴収"） <BR>
     * 　@かつ 更新前の保有資産Params.簿価（簿価単価計算用）==0）の場合のみは更新なし。<BR>
     * 以外、更新する。<BR>
     * ※一般口座の場合、簿価==0だったら更新しない。
     * @@param l_assetParams - (保有資産Params)<BR>
     * 　@　@　@約定取消対象の、保有資産Params。
     * @@param l_finTransactionParams 株式顧客勘定明細Params。
     * @@ throws DataException
     * @@roseuid 40ECD6A900E0
     */
    protected void reverseAssetParamsByMarketTradedTrans(
        AssetParams l_assetParams,
        EqtypeFinTransactionParams l_finTransactionParams)
        throws DataException
    {
        final String STR_METHOD_NAME = "reverseAssetParamsByMarketTradedTrans(AssetParams, EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);

        // 買約定（SideEnum.BUY）の約定取消の場合
        if (SideEnum.BUY.equals(getSide(l_finTransactionParams.getFinTransactionType())))
        {
            // 数量
            double l_dblQuantity = l_assetParams.getQuantity() - l_finTransactionParams.getQuantity();
            if (l_dblQuantity < 0.0D)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01931,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            l_assetParams.setQuantity(l_assetParams.getQuantity() - l_finTransactionParams.getQuantity());
            // 数量（簿価単価計算用）
            l_assetParams.setQuantityForBookValue(l_assetParams.getQuantityForBookValue() - l_finTransactionParams.getQuantity());
            // 簿価（簿価単価計算用）
            if (!TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_assetParams.getTaxType()) &&
                !TaxTypeEnum.SPECIAL.equals(l_assetParams.getTaxType()) &&
                l_assetParams.getBookValue() == 0.0D)
            {
                // 一般口座の場合、簿価==0だったら更新しない。
            }
            else
            {
                l_assetParams.setBookValue(l_assetParams.getBookValue() - (l_finTransactionParams.getNetAmount() * -1.0D));
            }
        }
        // 売約定（SideEnum.SELL）の約定取消の場合
        else if (SideEnum.SELL.equals(getSide(l_finTransactionParams.getFinTransactionType())))
        {
            // 数量
            l_assetParams.setQuantity(l_assetParams.getQuantity() + l_finTransactionParams.getQuantity());
        }
        log.exiting(STR_METHOD_NAME);
        l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
    }

    /**
     * (reverse取得単位での保有資産PositionBy株式顧客勘定明細)<BR>
     * 引数の株式顧客勘定明細Paramsより、<BR>
     * 約定取消を【取得単位での保有資産テーブル】に反映する。<BR>
     * （void reverseAssetUnitPositionByTrans(EqtypeFinTransactionParams trans, <BR>
     * 　@SideEnum side, AssetParams asset)のオーバーライド）<BR>
     * <BR>
     * １）　@何もせずにreturnする。<BR>
     * 　@　@　@※xTrade標準実装を無効にするために、オーバーライド<BR>
     * 　@　@　@※（WebⅢでは【取得単位での保有資産テーブル】（EqTypeAssetUnit）を<BR>
     * 　@　@　@※　@使用していないため）<BR>
     * @@param l_finTransactionParams 株式顧客勘定明細Params。
     * @@param l_side (売買)<BR>
     * 　@　@　@売買の別。
     * @@param l_assetParams (保有資産Params)<BR>
     * 　@　@　@約定取消対象の、保有資産Params。
     * @@throws DataException
     * @@roseuid 40F2471F005C
     */
    protected void reverseAssetUnitPositionByTrans(EqtypeFinTransactionParams l_finTransactionParams, SideEnum l_side, AssetParams l_assetParams) throws DataException
    {

    }
    
    /**
     * （update建株返済From株式顧客勘定明細）<BR>
     * <BR>
     * 株式顧客勘定明細より、返済注文時約定時の建株Paramsのプロパティ更新を行う。<BR>
     * 引数の株式顧客勘定明細Paramsの諸経費プロパティ更新も行う。<BR>
     * （void updateContractCloseFromMarketOrderedTrans(EqtypeContractParams cparams, <BR>
     * 　@EqtypeFinTransactionParams tparams)のオーバーライド）<BR>
     * <BR>
     * １）　@建株の残数量チェックを行う。<BR>
     * <BR>
     * 　@　@　@（引数の建株Params.建株数 － 引数の株式顧客勘定明細Params.約定数量） ＜ 0.0D<BR>
     * 　@　@　@の場合、「建株残数量チェックエラー」の例外（業務エラー）をthrowする。<BR>
     * 　@　@　@class: WEB3BaseRuntimeException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_01934<BR>
     * <BR>
     * ２）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。<BR>
     * <BR>
     * 　@　@this.calc諸経費按分比率(引数の建株Params.建株数, <BR>
     * 　@　@　@　@引数の株式顧客勘定明細Params,約定数量)コールにより取得する。<BR>
     * <BR>
     * ３）　@引数の建株Paramsの諸経費を、引数の株式顧客勘定明細Paramsに移動させる。<BR>
     * <BR>
     * 　@　@ this.takeIn諸経費From建株To株式顧客勘定明細( )メソッドをコールする。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜takeIn諸経費From建株To株式顧客勘定明細( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@按分比率：　@２）で求めた按分比率（factor）<BR>
     * 　@　@　@建株Params：　@引数の建株Params<BR>
     * 　@　@　@株式顧客勘定明細Params：　@引数の株式顧客勘定明細Params<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ４）　@引数の建株Paramsのプロパティを更新する。<BR>
     * <BR>
     * 　@　@　@建株数：　@（既存値） － 引数の株式顧客勘定明細Params.約定数量<BR>
     * 　@　@　@更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * ５）　@returnする。
     * @@param l_contractParams (建株Params)<BR>
     * 　@　@　@今回約定対象の、建株Params。
     * @@param l_finTransactionParams 株式顧客勘定明細Params。
     * @@throws RuntimeSystemException
     * @@roseuid 40F75EBC0121
     */
    protected void updateContractCloseFromMarketOrderedTrans(
        EqtypeContractParams l_contractParams,
        EqtypeFinTransactionParams l_finTransactionParams)
        throws RuntimeSystemException
    {
        final String STR_METHOD_NAME =
            "updateContractCloseFromMarketOrderedTrans(EqtypeContractParams, EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);

        // １）　@建株の残数量チェックを行う。
        // 　@　@　@（引数の建株Params.建株数 － 引数の株式顧客勘定明細Params.約定数量） ＜ 0.0D
        // 　@　@　@の場合、「建株残数量チェックエラー」の例外（業務エラー）をthrowする。
        if (l_contractParams.getQuantity() - l_finTransactionParams.getQuantity() < 0.0D)
        {
            String msg = "updateContractCloseFromMarketOrderedTrans: too much to settle(" + l_finTransactionParams.getQuantity() + ") for (" + l_contractParams.getQuantity() + ")";
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_01934, msg);
        }
        // ２）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。
        double factor = this.calcExpensesFactorRatio(l_contractParams.getQuantity(), l_finTransactionParams.getQuantity());

        // ３）　@引数の建株Paramsの諸経費を、引数の株式顧客勘定明細Paramsに移動させる。
        this.takeInCostFromContractToTrans(factor, l_contractParams, l_finTransactionParams);

        // ４）　@引数の建株Paramsのプロパティを更新する。
        l_contractParams.setQuantity(l_contractParams.getQuantity() - l_finTransactionParams.getQuantity());
        l_contractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （update建株現引現渡From株式顧客勘定明細）<BR>
     * <BR>
     * 株式顧客勘定明細より、現引現渡注文時約定時の建株Paramsのプロパティ更新を行う。<BR>
     * 引数の株式顧客勘定明細Paramsの諸経費プロパティ更新も行う。<BR>
     * （void updateContractSwapFromMarketOrderedTrans(EqtypeContractParams cparams, <BR>
     * 　@EqtypeFinTransactionParams tparams)のオーバーライド）<BR>
     * <BR>
     * １）　@建株の残数量チェックを行う。<BR>
     * <BR>
     * 　@　@　@（引数の建株Params.建株数 － 引数の株式顧客勘定明細Params.約定数量） ＜ 0.0D<BR>
     * 　@　@　@の場合、「建株残数量チェックエラー」の例外（業務エラー）をthrowする。<BR>
     * 　@　@　@class: WEB3BaseRuntimeException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_01934<BR>
     * <BR>
     * ２）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。<BR>
     * <BR>
     * 　@　@this.calc諸経費按分比率(引数の建株Params.建株数, <BR>
     * 　@　@　@　@引数の株式顧客勘定明細Params,約定数量)コールにより取得する。<BR>
     * <BR>
     * ３）　@引数の建株Paramsの諸経費を、引数の株式顧客勘定明細Paramsに移動させる。<BR>
     * <BR>
     * 　@　@ this.takeIn諸経費From建株To株式顧客勘定明細( )メソッドをコールする。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜takeIn諸経費From建株To株式顧客勘定明細( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@按分比率：　@２）で求めた按分比率（factor）<BR>
     * 　@　@　@建株Params：　@引数の建株Params<BR>
     * 　@　@　@株式顧客勘定明細Params：　@引数の株式顧客勘定明細Params<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ４）　@現引現渡注文約定の内容を、【保有資産テーブル】に反映する。<BR>
     * <BR>
     * 　@　@　@this.applyTo保有資産ポジション( )メソッドをコールする。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜applyTo保有資産ポジション( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@株式顧客勘定明細Params：　@引数の株式顧客勘定明細Params<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ５）　@引数の建株Paramsのプロパティを更新する。<BR>
     * <BR>
     * 　@　@　@建株数：　@（既存値） － 引数の株式顧客勘定明細Params.約定数量<BR>
     * 　@　@　@更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * ６）　@returnする。<BR>

     * <BR>
     * 　@　@　@トランザクションタイプ：　@４－１）で設定し直す前の値<BR>
     * 　@　@　@建株数：　@（既存値） － 引数の株式顧客勘定明細Params.約定数量<BR>
     * 　@　@　@更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * 　@　@　@※トランザクションタイプ：株式顧客勘定明細Params<BR>
     * 　@　@　@※建株数、更新日付：建株Params<BR>
     * <BR>
     * ６）　@returnする。
     * @@param l_contractParams (建株Params)<BR>
     * 　@　@　@今回約定対象の、建株Params。
     * @@param l_finTransactionParams 株式顧客勘定明細Params。
     * @@throws DataException
     * @@roseuid 40F77CBE016F
     */
    protected List updateContractSwapFromMarketOrderedTrans(
        EqtypeContractParams l_contractParams,
        EqtypeFinTransactionParams l_finTransactionParams)
        throws DataException
    {
        final String STR_METHOD_NAME =
            "updateContractSwapFromMarketOrderedTrans(EqtypeContractParams, EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);

        // １）　@建株の残数量チェックを行う。
        // 　@　@　@（引数の建株Params.建株数 － 引数の株式顧客勘定明細Params.約定数量） ＜ 0.0D
        // 　@　@　@の場合、「建株残数量チェックエラー」の例外（業務エラー）をthrowする。
        if (l_contractParams.getQuantity() - l_finTransactionParams.getQuantity() < 0.0D)
        {
            String msg = "updateContractCloseFromMarketOrderedTrans: too much to settle(" + l_finTransactionParams.getQuantity() + ") for (" + l_contractParams.getQuantity() + ")";
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_01934, msg);
        }
        // ２）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。
        double factor = this.calcExpensesFactorRatio(l_contractParams.getQuantity(), l_finTransactionParams.getQuantity());

        // ３）　@引数の建株Paramsの諸経費を、引数の株式顧客勘定明細Paramsに移動させる。
        takeInCostFromContractToTrans(factor, l_contractParams, l_finTransactionParams);

        // ４）　@現引現渡注文約定の内容を、【保有資産テーブル】に反映する。
        // 　@　@　@this.applyTo保有資産ポジション( )メソッドをコールする。
        this.applyToAssetPosition(l_finTransactionParams);

        // ５）　@引数の建株Paramsのプロパティを更新する。
        l_contractParams.setQuantity(l_contractParams.getQuantity() - l_finTransactionParams.getQuantity());
        l_contractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * （calc諸経費按分比率）<BR>
     * <BR>
     * 引数の建株残数、及び決済数量より、諸経費計算時に使用する按分比率を計算し<BR>
     * 返却する。<BR>
     * <BR>
     * １）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。<BR>
     * <BR>
     * 　@　@　@-------------------------------------------------------------------<BR>
     * 　@　@　@＜引数の建株残数 == 引数の決済数量の場合＞<BR>
     * 　@　@　@按分比率（factor）＝1.0D<BR>
     * <BR>
     * 　@　@　@＜上記以外の場合＞<BR>
     * 　@　@　@按分比率（factor）＝引数の決済数量 ÷ 引数の建株残数<BR>
     * 　@　@　@※除算結果の丸めは行わない。<BR>
     * 　@　@　@-------------------------------------------------------------------<BR>
     * <BR>
     * ２）　@１）で求めた按分比率（factor）をreturnする。
     * @@param l_dblBalance (建株残数)<BR>
     * 　@　@　@建株残数。
     * @@param l_dblClosingExecutedQuantity (決済約定数量)<BR>
     * 　@　@　@決済約定数量（今回約定の、該当建株への割り当て分数量）。
     * @@return double
     */
    protected double calcExpensesFactorRatio(double l_dblBalance, double l_dblClosingExecutedQuantity)
    {
        final String STR_METHOD_NAME = "calcExpensesFactorRatio(double, double)";
        log.entering(STR_METHOD_NAME);

        double factor = 0.0D;
        // １）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。
        // ＜引数の建株残数 == 引数の決済数量の場合＞
        if (l_dblBalance == l_dblClosingExecutedQuantity)
        {
            // 按分比率（factor）＝1.0D
            factor = 1.0D;
        }
        else
        {
            // 按分比率（factor）＝引数の決済数量 ÷ 引数の建株残数
            // ※除算結果の丸めは行わない。
            if (l_dblBalance != 0.0D)
            {
                factor = l_dblClosingExecutedQuantity / l_dblBalance;
            }
        }

        // ２）　@１）で求めた按分比率（factor）をreturnする。
        log.exiting(STR_METHOD_NAME);
        return factor;

    }

    /**
      * 注文可能な資産かどうかの判定。<BR>
      * true を返す。<BR>
      *
      * @@param l_exec 約定
      * @@return true
      */
    public boolean isAssetOrderExecution(EqTypeOrderExecution l_exec)
    {
        return true;
    }

    /**
     * (process新規建注文約定)<BR>
     * (processNewMarginOrderExecutionのオーバーライド)<BR>
     * ［処理概要］<BR>                                                          
     * 新規建注文約定処理を行う。<BR>    
     * シーケンス図<BR>
     * 「（残高）新規建注文約定」参照                                     
     * @@param l_eqtypeOrderExecution (約定)
     * @@return void
     */
    protected void processNewMarginOrderExecution(EqTypeOrderExecution l_eqtypeOrderExecution)
        throws DataException, RuntimeSystemException
    {
		final String STR_METHOD_NAME = "processNewMarginOrderExecution(EqTypeOrderExecution)";
		log.entering(STR_METHOD_NAME);
		
        EqtypeFinTransactionParams tparams = new EqtypeFinTransactionParams();
        setMarketOrderedTransDefaultValues(tparams);
        setExecutionInfoToMarketOrderedTrans(tparams, l_eqtypeOrderExecution);
        EqtypeContractParams cparams = getContract(tparams);
        if (cparams == null)
        {
            cparams = new EqtypeContractParams();
            setContractDefaultValues(cparams);
            updateContractOpenFromMarketOrderedTrans(cparams, tparams);
            getPersistenceManager().saveNewContract(cparams);
        }
        else
        {
            updateContractOpenFromMarketOrderedTrans(cparams, tparams);
            getPersistenceManager().updateContractByTrans(cparams);
        }
        tparams.setContractId(cparams.getContractId());
        setMarginNetAmount(tparams);
        getPersistenceManager().saveNewFinTransaction(tparams);
        notifyGtl(tparams);
		log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （拡張データマネージャ）。
     */
    public static class WEB3EquityPersistentDataManager extends EqTypePositionManagerHelper.PersistentDataManager
    {

        /**
         * (get保有資産Params)<BR>
         * 約定した資産をすでに顧客が保有しているかどうかを確認する為、<BR>
         * 保有資産を検索する。<BR>
         * （AssetParams getAsset(EqtypeFinTransactionParams tparams)のオーバーライド）<BR>
         * <BR>
         * １）　@保有資産オブジェクトを取得する。<BR>
         * 　@　@（* 株式ポジションマネージャ.get保有資産(long,long,long.TaxTypeEnum)をコール）<BR>
         * <BR>
         * 　@　@　@-----------------------------------------------------<BR>
         * 　@　@　@＜get保有資産( )：引数設定仕様＞<BR>
         * <BR>
         * 　@　@　@口座ID：　@引数の株式顧客勘定明細Params.口座ID<BR>
         * 　@　@　@補助口座ID：　@引数の株式顧客勘定明細Params.補助口座ID<BR>
         * 　@　@　@銘柄ID：　@引数の株式顧客勘定明細Params.銘柄ID<BR>
         * 　@　@　@税区分：<BR>
         * 　@　@　@　@　@・引数の株式顧客勘定明細Params.トランザクションカテゴリ<BR>
         * 　@　@　@　@　@　@＝現引現渡注文（EQTYPE_SWAP_MARGIN）の場合は、<BR>
         * 　@　@　@　@　@　@引数の株式顧客勘定明細Params.注文単位IDに該当する<BR>
         * 　@　@　@　@　@　@注文単位オブジェクト.税区分（現引現渡）<BR>
         * 　@　@　@　@　@・引数の株式顧客勘定明細Params.トランザクションカテゴリ<BR>
         * 　@　@　@　@　@　@≠現引現渡注文（EQTYPE_SWAP_MARGIN）の場合は、<BR>
         * 　@　@　@　@　@　@引数の株式顧客勘定明細Params.税区分<BR>
         * 　@　@　@-----------------------------------------------------<BR>
         * <BR>
         * ２）　@取得した保有資産オブジェクトの保有資産Paramsを返却する。<BR>
         * 　@　@※該当データなし時はnullを返却する。<BR>
         * <BR>
         * @@param l_trans　@株式顧客勘定明細Params
         * @@return 保有資産Params
         * @@throws DataQueryException, DataNetworkException
         * @@roseuid 4143F64401BB
         */
        public AssetParams getAsset(EqtypeFinTransactionParams l_trans) throws DataQueryException, DataNetworkException
        {
            final String STR_METHOD_NAME = "getAssetParams";
            log.entering(STR_METHOD_NAME);

            try
            {
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

                WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
                TaxTypeEnum l_taxType;
                //保有資産オブジェクトを取得する。
                //引数の株式顧客勘定明細Params.トランザクションカテゴリ＝現引現渡注文（EQTYPE_SWAP_MARGIN）の場合は、
                if (FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_trans.getFinTransactionCateg()))
                {
                    Order order = l_orderManager.getOrder(l_trans.getOrderId());
                    OrderUnit orderUnits[] = order.getOrderUnits();
                    EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)orderUnits[0].getDataSourceObject();
                    l_taxType = l_orderUnitRow.getSwapTaxType();
                }
                //・引数の株式顧客勘定明細Params.トランザクションカテゴリ≠現引現渡注文（EQTYPE_SWAP_MARGIN）の場合は
                else
                {
                    l_taxType = l_trans.getTaxType();
                }

                WEB3EquityAsset l_asset = (WEB3EquityAsset) l_positionManager.getAsset(l_trans.getAccountId(), l_trans.getSubAccountId(), l_trans.getProductId(), l_taxType);
                //保有資産Params
                if (l_asset != null)
                {
                    log.exiting(STR_METHOD_NAME);
                    AssetParams l_assetParams = new AssetParams((AssetRow) l_asset.getDataSourceObject());
                    return l_assetParams;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }
            }

            catch (NotFoundException l_dqe)
            {
                log.exiting(STR_METHOD_NAME);
                log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
            }
            catch (WEB3BaseException l_wbe)
            {
                log.exiting(STR_METHOD_NAME);
                log.error("Error In Method: " + STR_METHOD_NAME, l_wbe);
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_wbe.getMessage(), l_wbe);
            }
        }

        /**
         * (get株式顧客勘定明細Paramsリスト)<BR>
         * ［処理概要］<BR>
         * 一口約定計算対象となる株式顧客勘定明細Paramsのリストを取得する。<BR>
         * <BR>
         * １）注文単位ID、資産IDを条件に株式トランザクションデータテーブルを検索する。<BR>
         * 　@　@＜検索条件＞<BR>
         * 　@　@　@　@注文単位ID<BR>
         * 　@　@　@　@資産ID<BR>
         * 　@　@　@　@削除フラグ<BR>
         * <BR>
         * ２）可変長配列オブジェクトに検索された株式顧客勘定明細Paramsを一件づつ配列に追加する。<BR>
         * <BR>
         * ３）可変長配列オブジェクトを返却する。
         * @@param l_lngOrderUnitId 注文単位ID
         * @@param l_lngAssetId 資産ID
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 4143F6440306
         */
        public List getEqtypeFinTransactionParams(long l_lngOrderUnitId, long l_lngAssetId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getEqtypeFinTransactionParams";
            log.entering(STR_METHOD_NAME);
            try
            {

                String l_strWhere = "order_unit_id = ? and asset_id = ? and delete_flag= ? ";
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                List l_lisRows = null;
                Object[] l_objWhereValues = { new Long(l_lngOrderUnitId), new Long(l_lngAssetId), new Long(0)};
                l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_objWhereValues);
                int l_intRowSize = l_lisRows.size();
                List l_lstParams = new ArrayList();
                for (int i = 0; i < l_intRowSize; i++)
                {
                    EqtypeFinTransactionRow l_finTransactionRow = (EqtypeFinTransactionRow) l_lisRows.get(i);
                    EqtypeFinTransactionParams l_params = new EqtypeFinTransactionParams(l_finTransactionRow);
                    l_lstParams.add(l_params);
                }
                log.exiting(STR_METHOD_NAME);
                return l_lstParams;
            }
            catch (DataNetworkException l_dnwe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dnwe.getMessage(), l_dnwe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
            }
        }

        /**
         * (set更新建株属性)<BR>
         * <BR>
         * 建株Paramsの更新対象のプロパティに、更新値をセットする。<BR>
         * （void setUpdateContractAttributes(EqtypeContractParams cparams, Map vals)の<BR>
         * 　@オーバーライド）<BR>
         * <BR>
         * 【建株テーブル】の更新対象の項目毎に、建株Paramsの同名プロパティをセットする。<BR>
         * ex.) 「元建株数」に、引数の建株Paramsの同名プロパティをセットする。<BR>
         * 　@　@　@→　@vals.put("original_quantity", new Double(cparams.getOriginalQuantity()));<BR>
         * <BR>
         * --------------------------------------------------<BR>
         * ＜更新対象項目＞<BR>
         * <BR>
         * 元建株数<BR>
         * 建株数<BR>
         * 元建単価<BR>
         * 元建手数料<BR>
         * 建手数料<BR>
         * 元建手数料消費税<BR>
         * 建手数料消費税<BR>
         * 名義書換料<BR>
         * 名義書換料消費税<BR>
         * 管理費<BR>
         * 管理費消費税<BR>
         * 順日歩<BR>
         * 順日歩消費税<BR>
         * 逆日歩<BR>
         * 逆日歩消費税<BR>
         * 貸株料<BR>
         * その他<BR>
         * 更新日付<BR>
         * --------------------------------------------------<BR>
         * <BR>
         * @@param l_contractParams (建株Params)<BR>
         * 　@　@　@建株オブジェクト。
         * @@param l_updateMap (更新値Map)<BR>
         * 　@　@　@建株オブジェクトのプロパティに設定する値。
         * @@roseuid 40CFDB3D003E
         */
        public void setUpdateContractAttributes(
            EqtypeContractParams l_contractParams,
            Map l_updateMap)
        {
			final String STR_METHOD_NAME = "setUpdateContractAttributes";
			log.entering(STR_METHOD_NAME);
			
            // 元建株数
            l_updateMap.put("original_quantity", new Double(l_contractParams.getOriginalQuantity()));
            // 建株数
            l_updateMap.put("quantity", new Double(l_contractParams.getQuantity()));
            // 元建単価
            l_updateMap.put("original_contract_price", new Double(l_contractParams.getOriginalContractPrice()));
            // 元建手数料
            l_updateMap.put("original_setup_fee", new Double(l_contractParams.getOriginalSetupFee()));
            // 建手数料
            l_updateMap.put("setup_fee", new Double(l_contractParams.getSetupFee()));
            // 元建手数料消費税
            l_updateMap.put("original_setup_fee_tax", new Double(l_contractParams.getOriginalSetupFeeTax()));
            // 建手数料消費税
            l_updateMap.put("setup_fee_tax", new Double(l_contractParams.getSetupFeeTax()));
            // 名義書換料
            l_updateMap.put("name_transfer_fee", new Double(l_contractParams.getNameTransferFee()));
            // 名義書換料消費税
            l_updateMap.put("name_transfer_fee_tax", new Double(l_contractParams.getNameTransferFeeTax()));
            // 管理費
            l_updateMap.put("management_fee", new Double(l_contractParams.getManagementFee()));
            // 管理費消費税
            l_updateMap.put("management_fee_tax", new Double(l_contractParams.getManagementFeeTax()));
            // 順日歩
            l_updateMap.put("interest_fee", new Double(l_contractParams.getInterestFee()));
            // 順日歩消費税
            l_updateMap.put("interest_fee_tax", new Double(l_contractParams.getInterestFeeTax()));
            // 逆日歩
            l_updateMap.put("pay_interest_fee", new Double(l_contractParams.getPayInterestFee()));
            // 逆日歩消費税
            l_updateMap.put("pay_interest_fee_tax", new Double(l_contractParams.getPayInterestFeeTax()));
            // 貸株料
            l_updateMap.put("loan_equity_fee", new Double(l_contractParams.getLoanEquityFee()));
            // その他
            l_updateMap.put("other", new Double(l_contractParams.getOther()));
            // 更新日付
            l_updateMap.put("last_updated_timestamp", l_contractParams.getLastUpdatedTimestamp());
            
			log.exiting(STR_METHOD_NAME);
        }

        /**
         * (get建株)<BR>
         * 【建株テーブル】から、<BR>
         * 引数の値を指定し該当する建株Paramsオブジェクトを取得し返却する。<BR>
         * （EqtypeContractParams getContract<BR>
         * (long accountId, long subAccountId, long productId, <BR>
         * 　@long marketId, ContractTypeEnum type, double price, <BR>
         * 　@Date openDate, Date closeDate)のオーバーロード）<BR>
         * <BR>
         * １）　@引数オブジェクトの以下の項目を検索条件に指定し、<BR>
         * 　@　@　@QueryProcessorを使用し【建株テーブル】<BR>
         * から建株Paramsオブジェクトを取得する。<BR>
         * <BR>
         * 　@　@＜検索条件＞<BR>
         * 　@　@口座ID = 引数.口座ID<BR>
         * 　@　@かつ　@補助口座ID = 引数.補助口座ID<BR>
         * 　@　@かつ　@市場ID = 引数.市場ID<BR>
         * 　@　@かつ　@銘柄ID = 引数.銘柄ID<BR>
         * 　@　@かつ　@建区分 = 引数.建区分<BR>
         * 　@　@かつ　@建単価 = 引数.建単価<BR>
         * 　@　@かつ　@建日 = 引数.建日<BR>
         * 　@　@かつ　@期日 = 引数.期日<BR>
         * 　@　@かつ　@税区分 = 引数.税区分<BR>
         * 　@　@かつ　@弁済区分 = 引数.弁済区分<BR>
         * 　@　@かつ　@弁済期限値 = 引数.弁済期限値<BR>
         * 　@　@かつ　@当初建日 = 引数.当初建日<BR>
         * <BR>
         * ２）　@取得した建株Paramsオブジェクトを返却する。<BR>
         * @@param l_lngAccountId 口座ID。
         * @@param l_lngSubAccountId 補助口座ID。
         * @@param l_lngMarketId 市場ID。
         * @@param l_lngProductId 銘柄ID。
         * @@param l_contractType 建区分。
         * @@param l_dblContractPrice 建単価。
         * @@param l_datOpenDate 建日。
         * @@param l_datCloseDate 期日。
         * @@param l_taxType 税区分。
         * @@param l_strRepaymentType 弁済区分。<BR>
         * 　@　@　@（1：制度信用　@2：一般信用）
         * @@param l_dblRepaymentNum 弁済期限値。
         * @@param l_datFirstOpenDate 当初建日。
         * @@return EqtypeContractParams
         * @@throws DataNetworkException, DataNetworkException, DataFindException, DataQueryException
         * @@roseuid 40D039DD00B1
         */
        public EqtypeContractParams getContract(
            long l_lngAccountId,
            long l_lngSubAccountId,
            long l_lngMarketId,
            long l_lngProductId,
            ContractTypeEnum l_contractType,
            double l_dblContractPrice,
            Date l_datOpenDate,
            Date l_datCloseDate,
            TaxTypeEnum l_taxType,
            String l_strRepaymentType,
            double l_dblRepaymentNum,
            Date l_datFirstOpenDate)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getContract";
            log.entering(STR_METHOD_NAME);
            try
            {
                //＜検索条件＞
                String l_strWhere =
                    "account_id = ? and sub_account_id = ? and market_id= ? and product_id = ? and contract_type = ? and contract_price = ? and to_char(open_date,'yyyyMMdd') = ? and to_char(close_date,'yyyyMMdd') = ? and tax_type = ? and repayment_type = ? and repayment_num = ? and to_char(first_open_date,'yyyyMMdd') = ?";
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                List l_lisRows = null;
                Object[] l_objWhereValues =
                    {
                        new Long(l_lngAccountId),
                        new Long(l_lngSubAccountId),
                        new Long(l_lngMarketId),
                        new Long(l_lngProductId),
                        new Long(l_contractType.intValue()),
                        new Double(l_dblContractPrice),
                        WEB3DateUtility.formatDate(l_datOpenDate, "yyyyMMdd"),
                        WEB3DateUtility.formatDate(l_datCloseDate, "yyyyMMdd"),
                        new Long(l_taxType.intValue()),
                        new String(l_strRepaymentType),
                        new Double(l_dblRepaymentNum),
                        WEB3DateUtility.formatDate(l_datFirstOpenDate, "yyyyMMdd")};
                //取得した建株Paramsオブジェクトを返却する。
                log.exiting(STR_METHOD_NAME);
                l_lisRows = l_qp.doFindAllQuery(EqtypeContractRow.TYPE, l_strWhere, l_objWhereValues);

                if (l_lisRows.size() == 0)
                {
                    return null;
                }

                EqtypeContractParams l_eqtypeContractParams = new EqtypeContractParams((EqtypeContractRow) l_lisRows.get(0));
                return l_eqtypeContractParams;
            }
            catch (DataNetworkException l_dnwe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dnwe.getMessage(), l_dnwe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
            }

        }

        /**
         * (get株式顧客勘定明細ListBy注文単位)<BR>
         * 指定された注文データに対する、<BR>
         * 一口約定計算対象となる株式顧客勘定明細ParamsのListを取得する。<BR>
         * <BR>
         * １）　@注文単位IDを条件に、【トランザクションテーブル（株式顧客勘定明細）】を検索する。<BR>
         * 　@　@-------------------------------<BR>
         * 　@　@＜検索条件＞<BR>
         * 　@　@　@　@　@注文単位ID＝引数.注文単位ID<BR>
         * 　@　@かつ　@削除フラグ＝FALSE<BR>
         * 　@　@-------------------------------<BR>
         * <BR>
         * ２）　@可変長配列オブジェクトに、検索された株式顧客勘定明細Paramsを<BR>
         * 　@　@　@一件ずつ配列に追加する。<BR>
         * <BR>
         * ３）　@可変長配列オブジェクトを返却する。
         * @@param l_lngOrderUnitId 注文単位ID。
         * @@return List
         * @@throws DataNetworkException, DataQueryException
         * @@roseuid 40D23DA6038C
         */
        public List getFinTransactionListByOrderUnit(long l_lngOrderUnitId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionListByOrderUnit";
            log.entering(STR_METHOD_NAME);
            try
            {
                //    注文単位IDを条件に、【トランザクションテーブル（株式顧客勘定明細）】を検索する。     
                String l_strWhere = "order_unit_id = ? and delete_flag= ? ";
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                List l_lisRows = null;

                //＜検索条件＞注文単位ID＝引数.注文単位IDかつ　@削除フラグ＝FALSE
                Object[] l_objWhereValues = { new Long(l_lngOrderUnitId), BooleanEnum.FALSE};

                //可変長配列オブジェクトに、検索された株式顧客勘定明細Paramsを一件ずつ配列に追加する。
                l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_objWhereValues);

                int l_intRowSize = 0;
                if (!l_lisRows.isEmpty())
                {
                    l_intRowSize = l_lisRows.size();
                }
                List l_lstParams = new ArrayList();
                for (int i = 0; i < l_intRowSize; i++)
                {
                    EqtypeFinTransactionRow l_finTransactionRow = (EqtypeFinTransactionRow) l_lisRows.get(i);
                    EqtypeFinTransactionParams l_params = new EqtypeFinTransactionParams(l_finTransactionRow);
                    l_lstParams.add(l_params);
                }
                log.exiting(STR_METHOD_NAME);

                //３）　@可変長配列オブジェクトを返却する。
                return l_lstParams;

            }
            catch (DataNetworkException l_dnwe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dnwe.getMessage(), l_dnwe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
            }
        }

        /**
         * （get株式顧客勘定明細ListBy注文単位Plus建株）。<BR>
         * <BR>
         * 指定された注文データ＋建株データに対する、<BR>
         * 一口約定計算対象となる株式顧客勘定明細ParamsのListを取得する。<BR>
         * <BR>
         * １）　@注文単位ID、建株IDを条件に、<BR>
         * 　@　@【トランザクションテーブル（株式顧客勘定明細）】を検索する。<BR>
         * 　@　@-------------------------------<BR>
         * 　@　@＜検索条件＞<BR>
         * <BR>
         * 　@　@　@　@　@注文単位ID＝引数.注文単位ID<BR>
         * 　@　@かつ　@建株ID＝引数.建株ID<BR>
         * 　@　@かつ　@削除フラグ＝FALSE<BR>
         * 　@　@-------------------------------<BR>
         * <BR>
         * ２）　@可変長配列オブジェクトに、検索された株式顧客勘定明細Paramsを、<BR>
         * 　@　@一件ずつ配列に追加する。<BR>
         * <BR>
         * ３）　@可変長配列オブジェクトを返却する。
         * @@param l_lngOrderUnitId 注文単位ID。
         * @@param l_lngContractId 建株ID。
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 40D66B2501F0
         */
        public List getFinTransactionListByOrderUnitPlusContract(long l_lngOrderUnitId, long l_lngContractId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionListByOrderUnitPlusContract";
            log.entering(STR_METHOD_NAME);
            try
            {
                //１）　@注文単位ID、建株IDを条件に、【トランザクションテーブル（株式顧客勘定明細）】を検索する。
                String l_strWhere = "order_unit_id = ? and contract_id = ? and delete_flag = ?";
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                List l_lisRows = null;
                Object[] l_objWhereValues = { new Long(l_lngOrderUnitId), new Long(l_lngContractId), new Long(0)};

                //可変長配列オブジェクトに、検索された株式顧客勘定明細Paramsを、一件ずつ配列に追加する。
                l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_objWhereValues);

                int l_intRowSize = 0;
                if (!l_lisRows.isEmpty())
                {
                    l_intRowSize = l_lisRows.size();
                }
                List l_lstParams = new ArrayList();
                for (int i = 0; i < l_intRowSize; i++)
                {
                    EqtypeFinTransactionRow l_finTransactionRow = (EqtypeFinTransactionRow) l_lisRows.get(i);
                    EqtypeFinTransactionParams l_params = new EqtypeFinTransactionParams(l_finTransactionRow);
                    l_lstParams.add(l_params);
                }

                log.exiting(STR_METHOD_NAME);

                //３）　@可変長配列オブジェクトを返却する。
                return l_lstParams;
            }
            catch (DataNetworkException l_dnwe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dnwe.getMessage(), l_dnwe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
            }
        }

        /**
         * （get建株ListBy注文単位）。<BR>
         * <BR>
         * 指定された注文データに対する、建株データを全て取得し、<BR>
         * 建株ParamsのListを作成して返す。<BR>
         * <BR>
         * １）　@引数の注文単位IDに紐付く、株式顧客勘定明細ParamsのListを取得する。<BR>
         * <BR>
         *　@　@　@ クエリプロセッサにより、引数の注文単位IDを持つ株式顧客勘定明細Paramsを<BR>
         *　@　@　@ 全て取得する。（削除フラグ==TRUEのデータも取得対象とする）<BR>
         * <BR>
         * <BR>
         * ２）　@取得した株式顧客勘定明細ParamsのListから、建株IDのListを作成する。<BR>
         * 　@　@　@建株IDのListに同じ建株IDが重複して含まれる場合は、重複分はListから除く。<BR>
         * <BR>
         * ３）　@以下、２）で作成した建株IDのList数分、以下の処理を行い、<BR>
         * 　@　@　@指定された注文データに対する建株ParamsオブジェクトのListを作成する。<BR>
         * <BR>
         * ３－１）　@株式顧客勘定明細Params.建株IDに該当する建株Paramsオブジェクトを取得する。<BR>
         * <BR>
         * 　@　@　@拡張データマネージャ.getContract(株式顧客勘定明細Params.建株ID)コールにより<BR>
         * 　@　@　@取得する。<BR>
         * <BR>
         * ３－２）　@取得した建株Paramsオブジェクトを、戻り値Listに追加する。<BR>
         * <BR>
         * ４）　@作成した建株ParamsのListを返す。
         * @@param l_lngOrderUnitId 注文単位Id。
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 40E3802D0164
         */
        public List getContractListByOrderUnit(long l_lngOrderUnitId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getContractListByOrderUnit";
            log.entering(STR_METHOD_NAME);
            try
            {
                //１）　@引数の注文単位IDに紐付く、株式顧客勘定明細ParamsのListを取得する。
                //      クエリプロセッサにより、引数の注文単位IDを持つ株式顧客勘定明細Paramsを
                //      全て取得する。（削除フラグ==TRUEのデータも取得対象とする）
                String l_strWhere = " order_unit_id = ? ";
                Object[] l_objWhereValues = { new Long(l_lngOrderUnitId)};
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lstParams =
                    l_queryProcessor.doFindAllQuery(EqtypeFinTransactionRow.TYPE,
                        l_strWhere,
                        l_objWhereValues);
                 
                int l_intLength = 0;
                if (l_lstParams != null)
                {
                    l_intLength = l_lstParams.size();
                }
                List l_lstContract = new ArrayList();
                Map l_hashContractId = new Hashtable();

                //２）　@取得した株式顧客勘定明細ParamsのListから、建株IDのListを作成する。建株IDのListに同じ建株IDが重複して含まれる場合は、重複分はListから除
                for (int i = 0; i < l_intLength; i++)
                {
                    EqtypeFinTransactionParams l_finTransactionParams = (EqtypeFinTransactionParams) l_lstParams.get(i);
                    long l_lngContractId = l_finTransactionParams.getContractId();
                    if (!l_hashContractId.containsKey(Long.toString(l_lngContractId)))
                    {
                        l_hashContractId.put(Long.toString(l_lngContractId), "1");

                        //３）　@以下、２）で作成した建株IDのList数分、以下の処理を行い、指定された注文データに対する建株ParamsオブジェクトのListを作成する。
                        EqtypeContractParams l_eqtypeContractParams = this.getContract(l_lngContractId);

                        //３－２）　@取得した建株Paramsオブジェクトを、戻り値Listに追加する。
                        l_lstContract.add(l_eqtypeContractParams);
                    }
                }
                log.exiting(STR_METHOD_NAME);
                //４）　@作成した建株ParamsのListを返す。
                return l_lstContract;
            }
            catch (DataException l_dex)
            {
                log.exiting(STR_METHOD_NAME);
                log.error("Error In Method: " + STR_METHOD_NAME, l_dex);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dex.getMessage(), l_dex);
            }

        }

        /**
         * (get株式顧客勘定明細ListOf決済注文By建株)<BR>
         * 指定された建株データに対する、<BR>
         * 決済注文の約定時に作成された株式顧客勘定明細ParamsのListを取得する。<BR>
         * <BR>
         * １）　@建株ID等を条件に、決済注文約定時に作成された<BR>
         * 　@　@【トランザクションテーブル（株式顧客勘定明細）】を検索する。<BR>
         * 　@　@-------------------------------<BR>
         * 　@　@＜検索条件＞<BR>
         * <BR>
         * 　@　@　@　@　@建株ID＝引数.建株ID<BR>
         * 　@　@かつ　@トランザクションカテゴリ＝（"返済取引"（EQTYPE_CLOSE_MARGIN）<BR>
         * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@or "現引現渡取引"（<BR>
         * EQTYPE_SWAP_MARGIN））<BR>
         * 　@　@かつ　@削除フラグ＝FALSE<BR>
         * <BR>
         * 　@　@※トランザクション発生日時（fin_transaction_timestamp）昇順で取得する。<BR>
         * 　@　@-------------------------------<BR>
         * <BR>
         * ２）　@可変長配列オブジェクトに、検索された株式顧客勘定明細Paramsを、<BR>
         * 　@　@一件ずつ配列に追加する。<BR>
         * <BR>
         * ３）　@可変長配列オブジェクトを返却する。<BR>
         * @@param l_lngContractId - 建株ID。
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 40FC6955037E
         */
        public List getFinTransactionListOfCloseOrderByContract(long l_lngContractId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionListOfCloseOrderByContract";
            log.entering(STR_METHOD_NAME);
            try
            {
                // １）　@建株ID等を条件に、決済注文約定時に作成された【トランザクションテーブル（株式顧客勘定明細）】を検索する。       
                String l_strWhere = "contract_id = ? and (fin_transaction_categ = ? or fin_transaction_categ = ?) and delete_flag = ? ";
                String l_strOrderBy = "fin_transaction_timestamp";
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                List l_lisRows = null;
                Object[] l_objWhereValues =
                    { new Long(l_lngContractId), new Long(FinTransactionCateg.EQTYPE_CLOSE_MARGIN.intValue()), new Long(FinTransactionCateg.EQTYPE_SWAP_MARGIN.intValue()), new Long(0)};

                // ２）　@可変長配列オブジェクトに、検索された株式顧客勘定明細Paramsを、一件ずつ配列に追加する。
                l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_strOrderBy, null, l_objWhereValues);
                //l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_objWhereValues);
                int l_intRowSize = l_lisRows.size();
                List l_lstParams = new ArrayList();
                for (int i = 0; i < l_intRowSize; i++)
                {
                    EqtypeFinTransactionRow l_finTransactionRow = (EqtypeFinTransactionRow) l_lisRows.get(i);
                    EqtypeFinTransactionParams l_params = new EqtypeFinTransactionParams(l_finTransactionRow);
                    l_lstParams.add(l_params);
                }

                log.exiting(STR_METHOD_NAME);

                //３）　@可変長配列オブジェクトを返却する。
                return l_lstParams;

            }
            catch (DataNetworkException l_dnwe)
            {
                log.exiting(STR_METHOD_NAME);
                log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dnwe.getMessage(), l_dnwe);
            }
            catch (DataQueryException l_dqe)
            {
                log.exiting(STR_METHOD_NAME);
                log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
            }
        }

        /**
         * (get株式顧客勘定明細ListOf新規建注文By建株)<BR>
         * 指定された建株データに対する、<BR>
         * 新規建注文の約定時に作成された株式顧客勘定明細ParamsのListを取得する。<BR>
         * <BR>
         * １）　@建株ID等を条件に、新規建注文約定時に作成された<BR>
         * 　@　@【トランザクションテーブル（株式顧客勘定明細）】を検索する。<BR>
         * 　@　@-------------------------------<BR>
         * 　@　@＜検索条件＞<BR>
         * <BR>
         * 　@　@　@　@　@建株ID＝引数.建株ID<BR>
         * 　@　@かつ　@トランザクションカテゴリ＝（"新規建取引"（EQTYPE_OPEN_MARGIN））<BR>
         * 　@　@かつ　@削除フラグ＝FALSE<BR>
         * <BR>
         * 　@　@※トランザクション発生日時（fin_transaction_timestamp）昇順で取得する。<BR>
         * 　@　@-------------------------------<BR>
         * <BR>
         * ２）　@可変長配列オブジェクトに、検索された株式顧客勘定明細Paramsを、<BR>
         * 　@　@一件ずつ配列に追加する。<BR>
         * <BR>
         * ３）　@可変長配列オブジェクトを返却する。<BR>
         * @@param l_lngContractId - 建株ID。
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 410449CB034D
         */
        public List getFinTransactionListOfOpenOrderByContract(long l_lngContractId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionListOfOpenOrderByContract";
            log.entering(STR_METHOD_NAME);
            try
            {
                //１）　@建株ID等を条件に、新規建注文約定時に作成された【トランザクションテーブル（株式顧客勘定明細）】を検索する。  
                String l_strWhere = "contract_id = ? and fin_transaction_categ = ? and delete_flag= ? ";
                String l_strOrderBy = "fin_transaction_timestamp";
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                List l_lisRows = null;
                // ２）　@可変長配列オブジェクトに、検索された株式顧客勘定明細Paramsを、一件ずつ配列に追加する。
                Object[] l_objWhereValues = { new Long(l_lngContractId), new Long(FinTransactionCateg.EQTYPE_OPEN_MARGIN.intValue()), BooleanEnum.FALSE};
                l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_strOrderBy, null, l_objWhereValues);
                int l_intRowSize = 0;
                if (l_lisRows != null)
                {
                    l_intRowSize = l_lisRows.size();
                }
                List l_lstParams = new ArrayList();
                for (int i = 0; i < l_intRowSize; i++)
                {
                    EqtypeFinTransactionRow l_finTransactionRow = (EqtypeFinTransactionRow) l_lisRows.get(i);
                    EqtypeFinTransactionParams l_params = new EqtypeFinTransactionParams(l_finTransactionRow);
                    l_lstParams.add(l_params);
                }
                log.exiting(STR_METHOD_NAME);
                //３）　@可変長配列オブジェクトを返却する。
                return l_lstParams;

            }
            catch (DataNetworkException l_dnwe)
            {
                log.exiting(STR_METHOD_NAME);
                log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dnwe.getMessage(), l_dnwe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
            }
        }
        
        /**
         * (set更新保有資産属性)<BR>
         * <BR>
         * 保有資産Paramsの更新対象のプロパティに、更新値をセットする。<BR>
         * （void setUpdateAssetAttributes(AssetParams asset, Map vals)のオーバーライド）<BR>
         * <BR>
         * 【保有資産テーブル】の更新対象の項目毎に、保有資産Paramsの同名プロパティをセットする。<BR>
         * <BR>
         * １）　@super.setUpdateAssetAttributes(引数の保有資産Params, 引数の更新値Map)をコールし、<BR>
         * 　@　@　@xTrade標準プロパティをセットする。<BR>
         * <BR>
         * ２）　@拡張プロパティをセットする。<BR>
         * <BR>
         * 　@　@　@--------------------------------------------------<BR>
         * 　@　@　@＜セット対象項目＞<BR>
         * <BR>
         * 　@　@　@売付不能数量<BR>
         * 　@　@　@数量（簿価単価計算用）<BR>
         * 　@　@　@--------------------------------------------------<BR>
         * <BR>
         * @@param l_aparams - 保有資産Params<BR>
         * 保有資産Params。
         * @@param l_value - 更新値Map<BR>
         * 更新値Map。
         */
        public void setUpdateAssetAttributes(AssetParams l_aparams, Map l_values)
        {
			final String STR_METHOD_NAME = "setUpdateAssetAttributes";
			log.entering(STR_METHOD_NAME);
			
            super.setUpdateAssetAttributes(l_aparams, l_values);
            l_values.put("quantity_cannot_sell",
                new Double(l_aparams.getQuantityCannotSell()));
            l_values.put("quantity_for_book_value",
                new Double(l_aparams.getQuantityForBookValue()));
                
			log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * （set保有資産デフォルト値）<BR>
     * <BR>
     * 引数の保有資産Paramsのプロパティに、デフォルト値をセットする。<BR>
     * （void setAssetDefaultValues(AssetParams aparams)のオーバーライド）<BR>
     * <BR>
     * 引数の保有資産Paramsのプロパティに、以下の通りに値をセット：<BR>
     * ----------------------------------<BR>
     * 数量：　@0<BR>
     * 数量（簿価単価計算用）：　@0<BR>
     * 簿価（簿価単価計算用）：　@0<BR>
     * 売付不能数量：　@0<BR>
     * 名義書換料：　@0<BR>
     * 名義書換料消費税：　@0<BR>
     * 口座管理費：　@0<BR>
     * 口座管理費消費税：　@0<BR>
     * ミニ株区分：　@0（DEFAULT（ミニ株でない））<BR>
     * 分配金：　@0<BR>
     * 30日未経過残高口数：　@0<BR>
     * 作成日付：　@GtlUtils.getSystemTimestamp()<BR>
     * 更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * ----------------------------------<BR>
     * <BR>
     * @@param l_aparams - 保有資産Params
     */
    protected void setAssetDefaultValues(AssetParams l_aparams)
    {
        final String STR_METHOD_NAME = "setAssetDefaultValues(AssetParams)";
        log.entering(STR_METHOD_NAME);

        l_aparams.setQuantity(0.0D);
        l_aparams.setQuantityForBookValue(0.0D);
        l_aparams.setBookValue(0.0D);
        l_aparams.setQuantityCannotSell(0.0D);
        l_aparams.setSetupFee(0.0D);
        l_aparams.setSetupFeeTax(0.0D);
        l_aparams.setManagementFee(0.0D);
        l_aparams.setManagementFeeTax(0.0D);
        l_aparams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        l_aparams.setProfitDistribution(0.0D);
        l_aparams.setCountBeforePenalty(0.0D);
        l_aparams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_aparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (revert建株新規建From株式顧客勘定明細)
     * <BR>
     * 引数の株式顧客勘定明細Paramsより、約定取消を建株Paramsに反映する。<BR>
     * （void revertContractOpenFromMarketOrderedTrans(EqtypeContractParams cparams,<BR>
     * 　@EqtypeFinTransactionParams tparams)のオーバーライド）<BR>
     * <BR>
     * １）　@建株の残数量チェックを行う。<BR>
     * <BR>
     * 　@　@　@建株残数量(*1) ＜ 0.0D の場合、<BR>
     * 　@　@　@「建株残数量チェックエラー」の例外（業務エラー）をthrowする。<BR>
     * 　@　@　@class: WEB3BaseRuntimeException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_01934<BR>
     * <BR>
     * 　@　@　@(*1)建株残数量<BR>
     * 　@　@　@　@　@引数の建株Params.建株数 － 引数の株式顧客勘定明細Params.約定数量<BR>
     * <BR>
     * ２）　@引数の建株Paramsのプロパティを更新する。<BR>
     * <BR>
     * 　@　@　@元建株数：　@引数の建株Params.元建株数 － 引数の株式顧客勘定明細Params.約定数量<BR>
     * 　@　@　@建株数：　@引数の建株Params.建株数 － 引数の株式顧客勘定明細Params.約定数量<BR>
     * 　@　@　@更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * @@param l_cparams - (建株Params)<BR>
     * 今回約定対象の、建株Params。<BR>
     * @@param l_tparams - (株式顧客勘定明細Params)<BR>
     * 株式顧客勘定明細Params。<BR>
     * @@throws RuntimeSystemException
     */
    protected void revertContractOpenFromMarketOrderedTrans(
        EqtypeContractParams l_cparams,
        EqtypeFinTransactionParams l_tparams)
        throws RuntimeSystemException
    {
        final String STR_METHOD_NAME =
            "revertContractOpenFromMarketOrderedTrans(EqtypeContractParams, EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        
        // １）　@建株の残数量チェックを行う。
        double l_dblQuantity = l_cparams.getQuantity() - l_tparams.getQuantity();
        if (l_dblQuantity < 0.0D)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01934,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // ２）　@引数の建株Paramsのプロパティを更新する。
        l_cparams.setOriginalQuantity(l_cparams.getOriginalQuantity() - l_tparams.getQuantity());
        l_cparams.setQuantity(l_dblQuantity);
        l_cparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (reverse保有資産ポジションByTrans(株式顧客勘定明細Params)<BR>
     * <BR>
     * 引数の株式顧客勘定明細Paramsより、約定取消を保有資産に反映する。<BR>
     * （protected void reverseAssetPositionByTrans(EqtypeFinTransactionParams trans,<BR>
     * 　@SideEnum side)のオーバーライド）<BR>
     * <BR>
     * １）　@対象の保有資産を取得する。<BR>
     * <BR>
     * 　@　@　@拡張データマネージャ.getAsset(株式顧客勘定明細Params.資産ID)にて<BR>
     * 　@　@　@取得する。<BR>
     * <BR>
     * ２）　@買約定（SideEnum.BUY）(*1)の約定取消の場合は、保有資産残数量チェックを行う。<BR>
     * 　@　@　@　@　@-------------------------------------------------------------------<BR>
     * 　@　@　@　@　@保有資産残数量(*) ＜ 0.0D の場合、<BR>
     * 　@　@　@　@　@「保有資産残数量チェックエラー」の例外（業務エラー）をthrowする。<BR>
     * <BR>
     * 　@　@　@　@　@(*)保有資産残数量<BR>
     * 　@　@　@　@　@　@　@　@保有資産Params.数量 － 株式顧客勘定明細Params.約定数量<BR>
     * 　@　@　@　@　@-------------------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@(*1)買約定／売約定の判定<BR>
     * 　@　@　@引数の売買＝"買"の場合は、買約定。<BR>
     * 　@　@　@上記以外の場合は、売約定。<BR>
     * <BR>
     * ３）　@super.reverseAssetPositionByTrans( )をコールする。<BR>
     * <BR>
     * @@param l_trans - (株式顧客勘定明細Params)<BR>
     * 株式顧客勘定明細Params。
     * @@param l_side - (売買)<BR>
     * 売買の別。
     * @@throws DataException
     * @@throws RuntimeSystemException
     */
    protected void reverseAssetPositionByTrans(EqtypeFinTransactionParams l_trans, SideEnum l_side)
        throws DataException, RuntimeSystemException
    {
        final String STR_METHOD_NAME = "reverseAssetPositionByTrans(EqtypeContractParams, SideEnum)";
        log.entering(STR_METHOD_NAME);
        
        AssetParams l_asset = getPersistenceManager().getAsset(l_trans.getAssetId());
        if (l_side == SideEnum.BUY)
        {
            double l_dblQuantity = l_asset.getQuantity() - l_trans.getQuantity();
            if (l_dblQuantity < 0.0D)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01931,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        super.reverseAssetPositionByTrans(l_trans, l_side);
        log.exiting(STR_METHOD_NAME);
    }
}
@
