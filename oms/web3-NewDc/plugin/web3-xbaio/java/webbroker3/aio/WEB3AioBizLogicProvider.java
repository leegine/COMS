head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioBizLogicProvider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金計算サービスクラス(WEB3AioBizLogicProvider)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 屈陽 (中訊) 新規作成
                   2004/12/09 王蘭芬(中訊)残対応
                   2005/1/11 周勇 (中訊) 残対応
*/

package webbroker3.aio;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.define.WEB3AioDepositTypeDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.gentrade.WEB3GentradeBizLogicProvider;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.DailyAssetRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (入出金計算サービス)<BR>
 * 入出金計算サービスクラス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioBizLogicProvider extends WEB3GentradeBizLogicProvider 
    implements GlobalBizLogicProvider, AioBizLogicProvider
{    

    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioBizLogicProvider.class);             
       
    /**
     * (calc振替可能数量)<BR>
     * 銘柄ごとの振替可能数量を算出する。<BR>
     * <BR>
     * １）発注日の取得 <BR>
     * <BR>
     *   取引時間管理.get発注日() <BR>
     * <BR>
     * ２）保護預り保有数量、代用証券保有数量を算出する。<BR>
     * <BR>
     *   ※詳細は、計算式書参照。<BR>  
     * <BR>
     * ３）引数.預り区分に該当する保有数量を返却する。<BR>
     * @@param l_accountID - (口座ID)<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * @@param l_productID - (銘柄ID)<BR>
     * @@param l_taxType - (税区分)<BR>
     * @@param l_equityType - (預り区分)<BR>
     * @@return double
     */
    public double calcTransPossibleAmount(
        long l_accountID, ProductTypeEnum l_productType, 
        long l_productID, TaxTypeEnum l_taxType, 
        String l_equityType)
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "calcTransPossibleAmount(" +
            "long l_accountID, ProductTypeEnum l_productType, " +
            "long l_productID, TaxTypeEnum l_taxType, String l_equityType)";
        log.entering(l_strMethodName);

        //※詳細は、計算式書参照
        
        // (1)　@基準日を取得する。
        //   トレーディングシステムから、業務日付を取得する。
		TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();
		Date l_datOrderBizDate = tradingSystem.getBizDate();
        String l_strOrderBizDate = WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
        log.debug("l_strOrderBizDate = " + l_strOrderBizDate);
        
        String l_strOrderBizDate1 = 
            WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_datOrderBizDate, 1), "yyyyMMdd");
        String l_strOrderBizDate2 = 
            WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_datOrderBizDate, 2), "yyyyMMdd");
        String l_strOrderBizDate3 = 
            WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_datOrderBizDate, 3), "yyyyMMdd");
        String l_strOrderBizDate4 = 
            WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_datOrderBizDate, 4), "yyyyMMdd");
        String l_strOrderBizDate5 = 
            WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_datOrderBizDate, 5), "yyyyMMdd");
        
        // (2)　@受渡日毎の保有数量の計算結果を保持するエリアを生成する。
        //   当日（T+0）から5営業日後（T+5）までの保護預り及び代用証券の数量を保持するエリアを生成する。
        // 
        //      受渡済み保護預り数量[T+0 … T+5]
        final double[] l_dblSafeQuantities = {0, 0, 0, 0, 0, 0};

        //      受渡済み代用証券数量[T+0 … T+5]
        final double[] l_dblCollateralQuantities = {0, 0, 0, 0, 0, 0};
        
        // Utility for updating Quantity 
        class UpdateQuantities
        {
            public void process(SubAccountTypeEnum l_subAccountType, 
                    int dayNumber, double quantity, boolean isAdd)
            {
                // 補助口座が預り金口座の場合 受渡済み保護預り数量[T+dayNumber]をセート
                if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccountType))
                {
                    if (isAdd)
                    {
                        l_dblSafeQuantities[dayNumber] = 
                            l_dblSafeQuantities[dayNumber] + quantity;
                    }
                    else
                    {
                        l_dblSafeQuantities[dayNumber] = 
                            l_dblSafeQuantities[dayNumber] - quantity;
                    }
                }
                // 補助口座が保証金口座の場合　@受渡済み代用証券数量[T+dayNumber]をセート
                else if (SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT.equals(l_subAccountType))
                {
                    if (isAdd)
                    {
                        l_dblCollateralQuantities[dayNumber] = 
                            l_dblCollateralQuantities[dayNumber] + quantity;
                    }
                    else
                    {
                        l_dblCollateralQuantities[dayNumber] = 
                            l_dblCollateralQuantities[dayNumber] - quantity;
                    }
                }
            }
        }
        UpdateQuantities l_updateQuantities = new UpdateQuantities();
        
        // (3)　@前営業日時点の保有数量を算出する。
        // 引数.口座IDに該当する顧客について、引数.銘柄IDに該当する銘柄の受渡済み保有数量を保護預り及び代用証券の
        // 両方について算出する。
        // 
        // 受渡日別保有資産テーブルから、以下の抽出条件でレコードを取得し、以下の要領で集計する。
        // 
        //   [抽出条件]
        // 
        //   口座ID = 引数.口座ID
        //     銘柄ID = 引数.銘柄ID
        //     税区分 = 引数.税区分
        //     ミニ株区分 = "0"(DEFAULT)
        //     受渡日 < (1)で取得した基準日
        List l_lisDailyBeforeAssetRows = WEB3AioUtility.doFindAllQuery(
                DailyAssetRow.TYPE,
                "account_id = ? and product_id = ? and tax_type = ? and mini_stock_div = ? " +
                    "and to_char(delivery_date, 'YYYYMMDD') < ?",
                null,
                new Object[]{new Long(l_accountID), new Long(l_productID), 
                        l_taxType, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK, l_strOrderBizDate},
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "前営業日時点の受渡日別保有資産テーブのレコードを取得しエラー");
        log.debug("l_lisDailyBeforeAssetRows.size() = " + l_lisDailyBeforeAssetRows.size());
        
        //   [集計方法@]
        // 
        // １） 受渡日別保有資産テーブルの各取得レコードについて、  
        for (int i = 0; i < l_lisDailyBeforeAssetRows.size(); i++)
        {
            DailyAssetRow l_dailyAssetRow = (DailyAssetRow)l_lisDailyBeforeAssetRows.get(i);
            SubAccountTypeEnum l_subAccountType = 
                this.getSubAccountType(l_accountID, l_dailyAssetRow.getSubAccountId());
            log.debug("l_dailyAssetRow[" + i + "] = " + l_dailyAssetRow);
            log.debug("l_subAccountType[" + i + "] = " + l_subAccountType);
            
            // ○    受渡日別保有資産テーブル.補助口座IDの補助口座が預り金口座の場合
            // 
            //   受渡済み保護預り数量[T+0] ＝受渡済み保護預り数量[T+0] + 受渡日別保有資産テーブル.数量
            // 
            // ○    受渡日別保有資産テーブル.補助口座IDの補助口座が保証金口座の場合
            // 
            //   受渡済み代用証券数量[T+0] ＝受渡済み代用証券数量[T+0] + 受渡日別保有資産テーブル.数量
            l_updateQuantities.process(l_subAccountType, 0, l_dailyAssetRow.getQuantity(), true);
        }
        // ２） 保有数量[T+0]の値を[T+5]までの各要素にもセットする。
        l_dblSafeQuantities[1] = l_dblSafeQuantities[0];
        l_dblSafeQuantities[2] = l_dblSafeQuantities[0];
        l_dblSafeQuantities[3] = l_dblSafeQuantities[0];
        l_dblSafeQuantities[4] = l_dblSafeQuantities[0];
        l_dblSafeQuantities[5] = l_dblSafeQuantities[0];
        log.debug("l_dblSafeQuantities[0] = " + l_dblSafeQuantities[0]);
        
        l_dblCollateralQuantities[1] = l_dblCollateralQuantities[0];
        l_dblCollateralQuantities[2] = l_dblCollateralQuantities[0];
        l_dblCollateralQuantities[3] = l_dblCollateralQuantities[0];
        l_dblCollateralQuantities[4] = l_dblCollateralQuantities[0];
        l_dblCollateralQuantities[5] = l_dblCollateralQuantities[0];
        log.debug("l_dblCollateralQuantities[0] = " + l_dblCollateralQuantities[0]);
        log.debug("end ---------前営業日時点の保有数量を算出する ----- ");
        // ※    ここで注意すべき点は、受渡日別保有資産テーブルの補助口座が預り金口座のデータは、預り区分が保護預りのもの
        // を指しているわけではなく、保護預り＋代用のもの（つまり、保有資産の総数）を表しているということである。
        // 
        // (4) 当日以降の保有数量を取得し、加算する。
        // 引数.口座IDに該当する顧客について、引数.銘柄IDに該当する銘柄の受渡済み保有数量を保護預り及び代用証券の
        // 両方について算出する。
        
        // 受渡日別保有資産テーブルから、以下の抽出条件でレコードを取得し、以下の要領で集計する。
        // 
        //   [抽出条件]
        // 
        //   口座ID = 引数.口座ID
        //     銘柄ID = 引数.銘柄ID
        //     税区分 = 引数.税区分
		//     ミニ株区分 = "0"(DEFAULT)
        //     受渡日 ≧ (1)で取得した基準日
        List l_lisDailyAfterAssetRows = WEB3AioUtility.doFindAllQuery(
                DailyAssetRow.TYPE,
				"account_id = ? and product_id = ? and tax_type = ? and mini_stock_div = ?" +
                    " and to_char(delivery_date, 'YYYYMMDD') >= ?",
                null,
                new Object[]{new Long(l_accountID), new Long(l_productID), l_taxType,
                	WEB3MiniStockDivDef.DEFAULT_MINI_STOCK, l_strOrderBizDate},
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "当日以降の受渡日別保有資産テーブのレコードを取得しエラー");
         
        //   [集計方法@]
        for (int i = 0; i < l_lisDailyAfterAssetRows.size(); i++)
        {
            DailyAssetRow l_dailyAssetRow = (DailyAssetRow)l_lisDailyAfterAssetRows.get(i);
            log.debug("l_dailyAssetRow[" + i + "] = " + l_dailyAssetRow);
            SubAccountTypeEnum l_subAccountType = 
                this.getSubAccountType(l_accountID, l_dailyAssetRow.getSubAccountId());
            
            // 受渡日別保有資産テーブル.受渡日(T+n)により、
            String l_strAssetDeliveryDate = 
                WEB3DateUtility.formatDate(l_dailyAssetRow.getDeliveryDate(), "yyyyMMdd");
            
            // ○    受渡日別保有資産テーブル.補助口座IDの補助口座が預り金口座の場合
            // 
            //   受渡済み保護預り数量[T+x] ＝受渡済み保護預り数量[T+x] + 受渡日別保有資産テーブル.数量
            // 
            // ○    受渡日別保有資産テーブル.補助口座IDの補助口座が保証金口座の場合
            // 
            //   受渡済み代用証券数量[T+x] ＝受渡済み代用証券数量[T+x] + 受渡日別保有資産テーブル.数量

            // 受渡済み保護預り数量[T+0]
            log.debug("l_strOrderBizDate = " + l_strOrderBizDate);
            log.debug("l_strOrderBizDate1 = " + l_strOrderBizDate1);
            log.debug("l_strOrderBizDate2 = " + l_strOrderBizDate2);
            log.debug("l_strOrderBizDate3 = " + l_strOrderBizDate3);
            log.debug("l_strOrderBizDate4 = " + l_strOrderBizDate4);
            log.debug("l_strOrderBizDate5 = " + l_strOrderBizDate5);
            if (l_strOrderBizDate.equals(l_strAssetDeliveryDate))
            {
                l_updateQuantities.process(l_subAccountType, 0, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 1, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 2, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 3, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 4, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 5, l_dailyAssetRow.getQuantity(), true);
            }
            
            // 受渡済み保護預り数量[T+1]
            if (l_strOrderBizDate1.equals(l_strAssetDeliveryDate))
            {
                l_updateQuantities.process(l_subAccountType, 1, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 2, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 3, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 4, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 5, l_dailyAssetRow.getQuantity(), true);
            }

            // 受渡済み保護預り数量[T+2]
            if (l_strOrderBizDate2.equals(l_strAssetDeliveryDate))
            {
                l_updateQuantities.process(l_subAccountType, 2, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 3, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 4, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 5, l_dailyAssetRow.getQuantity(), true);
            }

            // 受渡済み保護預り数量[T+3]
            if (l_strOrderBizDate3.equals(l_strAssetDeliveryDate))
            {
                l_updateQuantities.process(l_subAccountType, 3, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 4, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 5, l_dailyAssetRow.getQuantity(), true);
            }

            // 受渡済み保護預り数量[T+4]
            if (l_strOrderBizDate4.equals(l_strAssetDeliveryDate))
            {
                l_updateQuantities.process(l_subAccountType, 4, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 5, l_dailyAssetRow.getQuantity(), true);
            }

            // 受渡済み保護預り数量[T+5]
            if (l_strOrderBizDate5.equals(l_strAssetDeliveryDate))
            {
                l_updateQuantities.process(l_subAccountType, 5, l_dailyAssetRow.getQuantity(), true);
            }
            
            // ※ ｎは、配列のindex。 n = 0 .. 5。
            // ※ xは、配列のindex。 x = n .. 5で、ｎから5までのすべての要素について上記の処理を行う。
        }
        
        log.debug("l_dblSafeQuantities[0] = " + l_dblSafeQuantities[0]);
        log.debug("l_dblSafeQuantities[1] = " + l_dblSafeQuantities[1]);
        log.debug("l_dblSafeQuantities[2] = " + l_dblSafeQuantities[2]);
        log.debug("l_dblSafeQuantities[3] = " + l_dblSafeQuantities[3]);
        log.debug("l_dblSafeQuantities[4] = " + l_dblSafeQuantities[4]);
        log.debug("l_dblSafeQuantities[5] = " + l_dblSafeQuantities[5]);
        log.debug("l_dblCollateralQuantities[0] = " + l_dblCollateralQuantities[0]);
        log.debug("l_dblCollateralQuantities[1] = " + l_dblCollateralQuantities[1]);
        log.debug("l_dblCollateralQuantities[2] = " + l_dblCollateralQuantities[2]);
        log.debug("l_dblCollateralQuantities[3] = " + l_dblCollateralQuantities[3]);
        log.debug("l_dblCollateralQuantities[4] = " + l_dblCollateralQuantities[4]);
        log.debug("l_dblCollateralQuantities[5] = " + l_dblCollateralQuantities[5]);
        log.debug("end ---------当日以降の保有数量を取得し、加算する---------");

        // (5)　@証券振替の移動数量を算出する。
        // 引数.口座IDに該当する顧客について、引数.銘柄IDに該当する銘柄の証券振替の移動数量を算出する。
        // 
        // 入出金の注文単位テーブルから、以下の抽出条件でレコードを取得し、以下の要領で集計する。
        // 
        //   [抽出条件]
        // 
        //   口座ID = 引数.口座ID
        //   補助口座ID = 引数.口座IDから取得した保証金口座の補助口座ID
        //   銘柄タイプ = 引数.銘柄タイプ
        //     銘柄ID = 引数.銘柄ID
        //     税区分 = 引数.税区分
        //     ミニ株区分 = "0"(DEFAULT)
        //     注文種別 == 1009（証券振替注文（保護預りから代用有価証券）） or 1010（証券振替注文（代用有価証券から保護預り））
        //     注文有効状態 = 1（オープン）
        SubAccount l_marginSubAccount = 
            this.getSubAccountType(l_accountID, SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        log.debug("l_subAccount.getAccountId() = " + l_marginSubAccount.getAccountId());
        log.debug("l_subAccount.getSubAccountId() = " + l_marginSubAccount.getSubAccountId());
        
        List l_lisAioOrderUnitRows = WEB3AioUtility.doFindAllQuery(
                AioOrderUnitRow.TYPE,
					"account_id = ? and sub_account_id = ? and product_type = ? and " +
					"product_id = ? and tax_type = ? and mini_stock_div = ? and (order_type = ? or order_type = ?) and " +
                    "order_open_status = ?",
                null,
                new Object[]{
                    new Long(l_accountID), 
                    new Long(l_marginSubAccount.getSubAccountId()),
                    l_productType, 
                    new Long(l_productID), 
                    l_taxType,
					WEB3MiniStockDivDef.DEFAULT_MINI_STOCK,
					OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES,
					OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT,
                    OrderOpenStatusEnum.OPEN},
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "証券振替入出金の注文単位テーブルのレコードを取得しエラー");
        log.debug("l_lisAioOrderUnitRows.size() = " + l_lisAioOrderUnitRows.size());
        
        //   [集計方法@]
        for (int i = 0; i < l_lisAioOrderUnitRows.size(); i++)
        {
            AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisAioOrderUnitRows.get(i);
            log.debug("l_aioOrderUnitRow[" + i + "] = " + l_aioOrderUnitRow);

            //   受渡済み代用証券数量[T+x] ＝受渡済み代用証券数量[T+x] + 注文単位テーブル.数量
			l_updateQuantities.process(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT, 0, l_aioOrderUnitRow.getQuantity(), true);
			l_updateQuantities.process(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT, 1, l_aioOrderUnitRow.getQuantity(), true);
			l_updateQuantities.process(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT, 2, l_aioOrderUnitRow.getQuantity(), true);
			l_updateQuantities.process(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT, 3, l_aioOrderUnitRow.getQuantity(), true);
			l_updateQuantities.process(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT, 4, l_aioOrderUnitRow.getQuantity(), true);
			l_updateQuantities.process(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT, 5, l_aioOrderUnitRow.getQuantity(), true); 
            // ※ xは、配列のindex。 x = 0 .. 5で、0から5までのすべての要素について上記の処理を行う。
        }
        log.debug("l_dblSafeQuantities[0] = " + l_dblSafeQuantities[0]);
        log.debug("l_dblSafeQuantities[1] = " + l_dblSafeQuantities[1]);
        log.debug("l_dblSafeQuantities[2] = " + l_dblSafeQuantities[2]);
        log.debug("l_dblSafeQuantities[3] = " + l_dblSafeQuantities[3]);
        log.debug("l_dblSafeQuantities[4] = " + l_dblSafeQuantities[4]);
        log.debug("l_dblSafeQuantities[5] = " + l_dblSafeQuantities[5]);
        log.debug("l_dblCollateralQuantities[0] = " + l_dblCollateralQuantities[0]);
        log.debug("l_dblCollateralQuantities[1] = " + l_dblCollateralQuantities[1]);
        log.debug("l_dblCollateralQuantities[2] = " + l_dblCollateralQuantities[2]);
        log.debug("l_dblCollateralQuantities[3] = " + l_dblCollateralQuantities[3]);
        log.debug("l_dblCollateralQuantities[4] = " + l_dblCollateralQuantities[4]);
        log.debug("l_dblCollateralQuantities[5] = " + l_dblCollateralQuantities[5]);
        log.debug("end ---------証券振替の移動数量---------");

		// (6) 特定口座振替の移動数量の算出
		// 引数.口座IDに該当する顧客について、引数.銘柄IDに該当する銘柄の特定口座振替の移動数量を算出する。
		// 
		// 入出金の注文単位テーブルから、以下の抽出条件でレコードを取得し、以下の要領で集計する。
		// 
		//   [抽出条件]
		// 
		//   口座ID = 引数.口座ID
		//   補助口座ID = 引数.口座IDから取得した預かり金口座の補助口座ID
		//   銘柄タイプ = 引数.銘柄タイプ
		//     銘柄ID = 引数.銘柄ID
		//     税区分 = 引数.税区分
		//     ミニ株区分 = "0"(DEFAULT)
		//     注文種別 == 1015（証券振替注文（特定口座から一般口座）） or 1016（証券振替注文（一般口座から特定口座））
		//     注文有効状態 = 1（オープン）
		SubAccount l_subAccount = 
			this.getSubAccountType(l_accountID, SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
		log.debug("l_subAccount.getAccountId() = " + l_subAccount.getAccountId());
		log.debug("l_subAccount.getSubAccountId() = " + l_subAccount.getSubAccountId());
        
		List l_lisSpTransOrderUnitRows = WEB3AioUtility.doFindAllQuery(
				AioOrderUnitRow.TYPE,
					"account_id = ? and sub_account_id = ? and product_type = ? and " +
					"product_id = ? and tax_type = ? and mini_stock_div = ? and (order_type = ? or order_type = ?) and " +
					"order_open_status = ?",
				null,
				new Object[]{
					new Long(l_accountID), 
					new Long(l_subAccount.getSubAccountId()),
					l_productType, 
					new Long(l_productID), 
					l_taxType,
					WEB3MiniStockDivDef.DEFAULT_MINI_STOCK,
					OrderTypeEnum.TRANSFER_TO_SPECIAL_ACCOUNT,
					OrderTypeEnum.TRANSFER_FROM_SPECIAL_ACCOUNT,
					OrderOpenStatusEnum.OPEN},
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				"特定口座振替の注文単位テーブルのレコードを取得しエラー");
		log.debug("l_lisSpTransOrderUnitRows.size() = " + l_lisSpTransOrderUnitRows.size());
        
		//   [集計方法@]
		for (int i = 0; i < l_lisSpTransOrderUnitRows.size(); i++)
		{
			AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisSpTransOrderUnitRows.get(i);
			log.debug("l_aioOrderUnitRow[" + i + "] = " + l_aioOrderUnitRow);
			// 注文単位テーブル.受渡日(T+n)により、  
			String l_strAioOrderDeliveryDate = 
				WEB3DateUtility.formatDate(l_aioOrderUnitRow.getDeliveryDate(), "yyyyMMdd");
			log.debug("l_strAioOrderDeliveryDate = " + l_strAioOrderDeliveryDate);
			//   受渡済み保護預り数量[T+x] ＝受渡済み保護預り数量[T+x] + 注文単位テーブル.数量
            
			// 受渡済み保護預り数量[T+0]
			if (l_strOrderBizDate.equals(l_strAioOrderDeliveryDate))
			{
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 0, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 1, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 2, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 3, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 4, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 5, l_aioOrderUnitRow.getQuantity(), true); 
			}

			// 受渡済み保護預り数量[T+1]
			if (l_strOrderBizDate1.equals(l_strAioOrderDeliveryDate))
			{
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 1, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 2, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 3, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 4, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 5, l_aioOrderUnitRow.getQuantity(), true); 
			}
            
			// 受渡済み保護預り数量[T+2]
			if (l_strOrderBizDate2.equals(l_strAioOrderDeliveryDate))
			{
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 2, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 3, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 4, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 5, l_aioOrderUnitRow.getQuantity(), true); 
			}
            
			// 受渡済み保護預り数量[T+3]
			if (l_strOrderBizDate3.equals(l_strAioOrderDeliveryDate))
			{
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 3, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 4, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 5, l_aioOrderUnitRow.getQuantity(), true); 
			}

			// 受渡済み保護預り数量[T+4]
			if (l_strOrderBizDate4.equals(l_strAioOrderDeliveryDate))
			{
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 4, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 5, l_aioOrderUnitRow.getQuantity(), true); 
			}

			// 受渡済み保護預り数量[T+5]
			if (l_strOrderBizDate5.equals(l_strAioOrderDeliveryDate))
			{
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 5, l_aioOrderUnitRow.getQuantity(), true); 
			}
			// ※ ｎは、配列のindex。 n = 0 .. 5。
			// ※ xは、配列のindex。 x = n .. 5で、ｎから5までのすべての要素について上記の処理を行う。
		}
		log.debug("l_dblSafeQuantities[0] = " + l_dblSafeQuantities[0]);
		log.debug("l_dblSafeQuantities[1] = " + l_dblSafeQuantities[1]);
		log.debug("l_dblSafeQuantities[2] = " + l_dblSafeQuantities[2]);
		log.debug("l_dblSafeQuantities[3] = " + l_dblSafeQuantities[3]);
		log.debug("l_dblSafeQuantities[4] = " + l_dblSafeQuantities[4]);
		log.debug("l_dblSafeQuantities[5] = " + l_dblSafeQuantities[5]);
		log.debug("l_dblCollateralQuantities[0] = " + l_dblCollateralQuantities[0]);
		log.debug("l_dblCollateralQuantities[1] = " + l_dblCollateralQuantities[1]);
		log.debug("l_dblCollateralQuantities[2] = " + l_dblCollateralQuantities[2]);
		log.debug("l_dblCollateralQuantities[3] = " + l_dblCollateralQuantities[3]);
		log.debug("l_dblCollateralQuantities[4] = " + l_dblCollateralQuantities[4]);
		log.debug("l_dblCollateralQuantities[5] = " + l_dblCollateralQuantities[5]);
		log.debug("end ---------特定口座振替の移動数量---------");

        // (7)　@対象銘柄が株式の場合、現物売約定数量と現渡数量を算出する。
        // 株式注文単位テーブルから、以下の抽出条件でレコードを取得し、以下の要領で集計する。
        // 
        //   [抽出条件]
        // 
        //   ○共通
        // 口座ID = 引数.口座ID
        //   銘柄タイプ = 引数.銘柄タイプ
        //     銘柄ID = 引数.銘柄ID
        //     発注日 = 現在日付
        //     失効区分 = 1（オープン）
        //     約定数量 != null
        // 
        // ○現物売注文の場合
        //   注文種別 = 2（現物売注文）
        //   税区分 = 引数.税区分
        // 
        //   ○現渡注文の場合
        //   注文種別 = 8（現渡注文）
        //   税区分（現引現渡） = 引数.税区分
        log.debug("l_accountID = " + l_accountID);
        log.debug("l_productType = " + l_productType);
        log.debug("l_productID = " + l_productID);
        log.debug("l_strOrderBizDate = " + l_strOrderBizDate);
        log.debug("l_taxType = " + l_taxType);
        List l_lisEqtypeOrderUnitRows = WEB3AioUtility.doFindAllQuery(
                EqtypeOrderUnitRow.TYPE,
                "account_id = ? and product_type = ? and product_id = ? " +
                    "and biz_date = ? and expiration_status = ? " +
                    "and executed_quantity is not null " +
//                    "and (executed_quantity is not null or executed_quantity != 0) " +
                    "and (order_type = ? or order_type = ?) " + "and tax_type = ?",
                null,
                new Object[]{
                    new Long(l_accountID), // 口座ID = 引数.口座ID
                    l_productType,// 銘柄タイプ = 引数.銘柄タイプ
                    new Long(l_productID),//銘柄ID = 引数.銘柄ID
                    l_strOrderBizDate,// 発注日 = 現在日付
                    OrderExpirationStatusEnum.OPEN, // 失効区分 = 1（オープン）
                    OrderTypeEnum.EQUITY_SELL,//現物売注文の場合 注文種別 = 2（現物売注文）
                    OrderTypeEnum.SWAP_MARGIN_SHORT, //現物売注文の場合 注文種別 = 8（現渡注文）
                    l_taxType},
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "株式注文単位テーブルの注文単位テーブルのレコードを取得しエラー");
        log.debug("l_lisEqtypeOrderUnitRows.size() =  " + l_lisEqtypeOrderUnitRows.size());
        //   [集計方法@]
        for (int i = 0; i < l_lisEqtypeOrderUnitRows.size(); i++)
        {
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = 
                (EqtypeOrderUnitRow)l_lisEqtypeOrderUnitRows.get(i);
            log.debug("l_eqtypeOrderUnitRow[" + i + "] = " + l_eqtypeOrderUnitRow);
            //   注文単位テーブル.受渡日(T+n)により、  
            String l_strEqtypeOrderDeliveryDate = 
                WEB3DateUtility.formatDate(l_eqtypeOrderUnitRow.getDeliveryDate(), "yyyyMMdd");

            // 受渡済み保護預り数量[T+x] ＝受渡済み保護預り数量[T+x] - 注文単位テーブル.約定数量

            // 受渡済み保護預り数量[T+0]
            if (l_strOrderBizDate.equals(l_strEqtypeOrderDeliveryDate))
            {
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        0, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        1, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        2, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        3, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        4, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        5, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
            }

            // 受渡済み保護預り数量[T+1]
            if (l_strOrderBizDate1.equals(l_strEqtypeOrderDeliveryDate))
            {
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        1, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        2, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        3, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        4, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        5, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
            }

            // 受渡済み保護預り数量[T+2]
            if (l_strOrderBizDate2.equals(l_strEqtypeOrderDeliveryDate))
            {
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        2, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        3, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        4, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        5, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
            }
            
            // 受渡済み保護預り数量[T+3]
            if (l_strOrderBizDate3.equals(l_strEqtypeOrderDeliveryDate))
            {
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        3, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        4, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        5, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
            }
            
            // 受渡済み保護預り数量[T+4]
            if (l_strOrderBizDate4.equals(l_strEqtypeOrderDeliveryDate))
            {
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        4, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        5, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
            }
            
            // 受渡済み保護預り数量[T+5]
            if (l_strOrderBizDate5.equals(l_strEqtypeOrderDeliveryDate))
            {
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        5, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
            }
            // ※ ｎは、配列のindex。 n = 0 .. 5。
            // ※ xは、配列のindex。 x = n .. 5で、ｎから5までのすべての要素について上記の処理を行う。
        }
        log.debug("l_dblSafeQuantities[0] = " + l_dblSafeQuantities[0]);
        log.debug("l_dblSafeQuantities[1] = " + l_dblSafeQuantities[1]);
        log.debug("l_dblSafeQuantities[2] = " + l_dblSafeQuantities[2]);
        log.debug("l_dblSafeQuantities[3] = " + l_dblSafeQuantities[3]);
        log.debug("l_dblSafeQuantities[4] = " + l_dblSafeQuantities[4]);
        log.debug("l_dblSafeQuantities[5] = " + l_dblSafeQuantities[5]);
        log.debug("l_dblCollateralQuantities[0] = " + l_dblCollateralQuantities[0]);
        log.debug("l_dblCollateralQuantities[1] = " + l_dblCollateralQuantities[1]);
        log.debug("l_dblCollateralQuantities[2] = " + l_dblCollateralQuantities[2]);
        log.debug("l_dblCollateralQuantities[3] = " + l_dblCollateralQuantities[3]);
        log.debug("l_dblCollateralQuantities[4] = " + l_dblCollateralQuantities[4]);
        log.debug("l_dblCollateralQuantities[5] = " + l_dblCollateralQuantities[5]);
        log.debug("end ---------対象銘柄が株式の場合、現物売約定数量と現渡数量を算出する---------");
        
        // (8)　@算出した受渡日毎の保護預り保有数量の中で、最小の数量を保護預り保有数量とする。
        //    ※保護預り保有数量が同じ受渡日が存在した場合は、代用証券保有数量の多いほうを選択する。
        
        // 保護預り保有数量
		double l_dblReturnSafeQuantity = l_dblSafeQuantities[0];
		log.debug("l_dblReturnSafeQuantity = " + l_dblReturnSafeQuantity);
        
        // 代用証券保有数量
        double l_dblReturnCollateralQuantity = l_dblCollateralQuantities[0];
        log.debug("l_dblReturnCollateralQuantity = " + l_dblReturnCollateralQuantity);
        for (int i = 1; i <=5; i++)
        {
            if (l_dblSafeQuantities[i] < l_dblReturnSafeQuantity)
            {
                l_dblReturnSafeQuantity = l_dblSafeQuantities[i];

                // (8) 確定した保護預り保有数量を持つ受渡日の代用証券保有数量を取得し、その数量を代用証券保有数量とする。
                l_dblReturnCollateralQuantity = l_dblCollateralQuantities[i];
            }
			// ※保護預り保有数量が同じ受渡日が存在した場合は、代用証券保有数量の多いほうを選択する。
            else if (l_dblSafeQuantities[i] == l_dblReturnSafeQuantity &&
            			l_dblCollateralQuantities[i] > l_dblReturnCollateralQuantity)
            {
				l_dblReturnSafeQuantity = l_dblSafeQuantities[i];
				l_dblReturnCollateralQuantity = l_dblCollateralQuantities[i];
            }
        }
        
        // (9) 最終的な保有数量を確定する。
        // 
        // ○    保護預り保有数量 ≦ 0 の場合
        if (l_dblReturnSafeQuantity <= 0)
        {
            // 保護預り保有数量 ＝ 0
            l_dblReturnSafeQuantity = 0;

            // 代用証券保有数量 ＝ 0
            l_dblReturnCollateralQuantity = 0;
        }

        // ○    保護預り保有数量 ＞ 0 の場合
        // ・保護預り保有数量 ≦ 代用証券保有数量 の場合
        else if (l_dblReturnSafeQuantity <= l_dblReturnCollateralQuantity)
        {
            // 代用証券保有数量 ＝ 保護預り保有数量
            l_dblReturnCollateralQuantity = l_dblReturnSafeQuantity;
            
            // 保護預り保有数量 ＝ 0
            l_dblReturnSafeQuantity = 0;
            
        }
        // ・保護預り保有数量 ＞ 代用証券保有数量 の場合
        else
        {
            // 保護預り保有数量 ＝ 保護預り保有数量 － 代用証券保有数量 
            l_dblReturnSafeQuantity = l_dblReturnSafeQuantity - l_dblReturnCollateralQuantity;
            
            // 代用証券保有数量 ＝ 代用証券保有数量
        }
        log.debug("l_dblReturnSafeQuantity = " + l_dblReturnSafeQuantity);   
        log.debug("l_dblReturnCollateralQuantity = " + l_dblReturnCollateralQuantity);
        //10）引数.預り区分に該当する保有数量を返却する
        if (WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(l_equityType))
        {
            log.exiting(l_strMethodName);
            return l_dblReturnSafeQuantity;
        }
        else if (WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY.equals(l_equityType))
        {
            log.exiting(l_strMethodName);
            return l_dblReturnCollateralQuantity;
        }
        return 0;
    }

    /**
     * (calc証券評価額)<BR>
     * 証券評価額を算出する。 <BR>
     * <BR>
     * １）銘柄テーブルからレコードを取得する。 <BR>
     * <BR>
     * [検索条件] <BR>
     * 銘柄ID： 引数.銘柄ID <BR>
     * <BR>
     * 2）評価額計算 <BR>
     * <BR>
     *   ※詳細は、計算式書参照。<BR> 
     * <BR>
     * 3）算出した評価額を返却する。 <BR>
     * <BR>
     * @@param l_productID - (銘柄ID)
     * @@param l_equityType - (預り区分)
     * @@param l_dblAmount - (数量)
     * @@return double  
     */
    public double calcStockEvalueAmount(
        long l_productID, String l_equityType, double l_dblAmount)
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "calcStockEvalueAmount(long l_productID, String l_equityType, double l_Amount)";
        log.entering(l_strMethodName);
        
        //１）銘柄テーブルからレコードを取得する
        //[検索条件] 
        //銘柄ID：引数.銘柄ID
        try
        {
            ProductRow l_productRow = 
                (ProductRow) ProductDao.findRowByProductId(l_productID);
            log.debug("l_productRow = " + l_productRow);
            if (l_productRow == null)
            {
                log.debug("銘柄テーブルからレコードを取得しない");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName);
            }
            
            double l_dblRatio = 0;   

            // ======remian zhou-yong begin ========

            l_dblRatio = l_productRow.getMarginRatio();              

            // ======remian zhou-yong end ========
            
            //３）評価額計算
            //※詳細は、計算式書参照
            //（保有数量(*1) × 評価単価(*2) × 掛目(*3)）÷ 計算単位(*4)
            //(*1)　@保有数量： 保護預りの評価額算出時は保護預り保有数量、代用証券の評価額算出時は代用証券保有数量
            //(*2)　@評価単価： 銘柄テーブル.評価単価
            //(*3)　@掛目： 銘柄テーブル.代用掛目
			//(*4)　@計算単位： 銘柄テーブル.計算単位（債券・投信以外は、1固定）
			//※債券の場合は、計算結果に100をかけたものを最終的な評価額とする。
            
            // 計算単位： 銘柄テーブル.計算単位（債券・投信以外は、1固定）
            double l_dblCalcSize = 1;
            if (ProductTypeEnum.MUTUAL_FUND.equals(l_productRow.getProductType())
                || ProductTypeEnum.BOND.equals(l_productRow.getProductType()))
            {
                l_dblCalcSize = l_productRow.getCalcSize();
            }
            
            // 評価単価： 銘柄テーブル.評価単価
            double l_dblEstimationPrice = l_productRow.getEstimationPrice();
            log.debug("l_dblAmount = " + l_dblAmount);
            log.debug("l_dblEstimationPrice = " + l_dblEstimationPrice);
            log.debug("l_dblRatio = " + l_dblRatio);
            log.debug("(l_dblRatio / 100.0) = " + (l_dblRatio / 100.0));
			log.debug("l_dblCalcSize = " + l_dblCalcSize);
            // 評価額計算
            // ※代用掛目は単位が％なので、100で割る必要がある
            double l_dblMarketValue = 
            	(l_dblAmount * l_dblEstimationPrice * (l_dblRatio / 100.0)) / l_dblCalcSize;
            
            //債券の場合は、さらに100をかける。
            if (ProductTypeEnum.BOND.equals(l_productRow.getProductType()))
				l_dblMarketValue *= 100.0;

            log.exiting(l_strMethodName);
            
            //４）算出した評価額を返却する。
            // ※小数点以下は切り捨て
            return (long)l_dblMarketValue;
            
        }     
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);
        }
    }
    
    private SubAccountTypeEnum getSubAccountType(long l_accountId, long l_subAccountId)
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "getSubAccountType(long l_accountId, long l_subAccountId)";
        log.entering(l_strMethodName);

        try
        {
            AccountManager l_accountManager = GtlUtils.getAccountManager();
            
            SubAccount l_subAccount = 
                l_accountManager.getSubAccount(l_accountId, l_subAccountId);
            log.debug("l_subAccount.getAccountId() = " + l_subAccount.getAccountId());
            log.debug("l_subAccount.getSubAccountId() = " + l_subAccount.getSubAccountId());
            log.debug("l_subAccount.getSubAccountType() = " + l_subAccount.getSubAccountType());
            log.exiting(l_strMethodName);
            return l_subAccount.getSubAccountType();
        }
        catch (NotFoundException l_ex)
        {
            log.error("補助口座を取得しエラー....", l_ex);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
    }

    private SubAccount getSubAccountType(long l_accountId, SubAccountTypeEnum l_subAccountType)
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "getSubAccountType(long l_accountId, SubAccountTypeEnum l_subAccountType)";
        log.entering(l_strMethodName);
    
        try
        {
            AccountManager l_accountManager = GtlUtils.getAccountManager();
            
            SubAccount l_subAccount = 
                l_accountManager.getSubAccount(l_accountId, l_subAccountType);
            log.exiting(l_strMethodName);
            return l_subAccount;
        }
        catch (NotFoundException l_ex)
        {
            log.error("補助口座を取得しエラー....", l_ex);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider#checkTradingPower(com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec)
     */
    public OrderValidationResult checkTradingPower(SubAccount arg0, OrderSpec arg1)
    {
        return null;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbaio.AioBizLogicProvider#calcCommission(com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit)
     */
    public double calcCommission(AioOrderUnit arg0)
    {
        return 0;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider#calcCommission(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution)
     */
    public double calcCommission(OrderExecution arg0)
    {
        return 0;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider#calcCapitalGainTax(com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum, double)
     */
    public double calcCapitalGainTax(SubAccount arg0, TaxTypeEnum arg1, double arg2)
    {
        return 0;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider#calcExecutionAmount(com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, long, double, double, com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum)
     */
    public double calcExecutionAmount(ProductTypeEnum arg0, long arg1, double arg2, double arg3, QuantityTypeEnum arg4)
    {
        return 0;
    }    
}
@
