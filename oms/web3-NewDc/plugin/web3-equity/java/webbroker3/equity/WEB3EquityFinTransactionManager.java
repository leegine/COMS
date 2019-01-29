head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityFinTransactionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張トランザクションマネージャクラス(WEB3EquityFinTransactionManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/29 呉艶飛(中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正

*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.AssetTransferFinTransactionImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.CashTransferFinTransactionImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeCashBasedOrderTransactionImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractOpenTransactionImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractSettleTransactionImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractSwapTransactionImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeFinTransactionManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransaction;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CapitalGainStatusDef;

/**
 * （拡張トランザクションマネージャ）。<BR>
 * <BR>
 * 株式顧客勘定明細（トランザクション）に対する手続きを表現する。<BR>
 * xTradeのEqTypeFinTransactionManagerを拡張したクラス。
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3EquityFinTransactionManager extends EqTypeFinTransactionManagerImpl
{

    /**
     * (ログユーティリティ)
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityFinTransactionManager.class);

    /**
     * 引数で与えられたEqtypeFinTransactionRowオブジェクトのトランザクションタイプ<BR>
     * に対応したトランザクションオブジェクトを返す。<BR>
     * <BR> 
     * @@param EqtypeFinTransactionRowオブジェクト
     * @@param l_r
     * @@return FinTransaction
     * @@roseuid 4042EC5F0259
     */
    protected FinTransaction toFinTransaction(Row l_r)
    {
        EqtypeFinTransactionRow l_row = (EqtypeFinTransactionRow) l_r;
        switch (l_row.getFinTransactionType().intValue())
        {
            case 70 : // 'F'
            case 80 : // 'P'
            case 201 :
            case 202 :
                return new EqTypeCashBasedOrderTransactionImpl(l_row);

            case 90 : // 'Z'
            case 100 : // 'd'
                return new EqTypeContractOpenTransactionImpl(l_row);

            case 110 : // 'n'
            case 120 : // 'x'
                return new EqTypeContractSettleTransactionImpl(l_row);

            case 130 :
            case 140 :
                return new EqTypeContractSwapTransactionImpl(l_row);

            case 1003 :
            case 1004 :
                return new AssetTransferFinTransactionImpl(l_row);

            case 10 : // '\n'
            case 20 : // '\024'
                return new CashTransferFinTransactionImpl(l_row);
        }
        return new WEB3EquityFinTransaction(l_row);
    }

    /**
     * (getトランザクション)<BR>
     * （getTransactionsのオーバーロード）<BR>
     * <BR>
     * 引数で指定した注文単位に該当する株式トランザクションを<BR>
     * 取得する。<BR>
     * <BR>
     * １）　@株式顧客勘定明細テーブル検索<BR>
     * 　@以下の条件で株式顧客勘定明細テーブルを検索し、<BR>
     *   取得結果をListで返却する。 <BR>
     * <BR>
     * [検索条件]<BR>
     * 注文単位.注文ID<BR>
     * 注文単位.注文単位ID<BR>
     * 削除フラグ == ”FALSE”<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 40BE43F800DA
     */
    public List getTransactions(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransaction(OrderUnit)";

        log.entering(STR_METHOD_NAME);

        // (1)戻り値オブジェクトのインスタンスを生成する
        List l_lisReturnValue = new ArrayList();

        // (2)検索条件追加
        // (2-1）検索条件文字列を作成
        String l_strWhere = "order_id = ? and order_unit_id = ? and delete_flag = ? ";
        long l_lngDeleteFlag = 0L;
        // (2-2)文字列配列を生成し
        Object[] l_objWhereValue = { new Long(l_orderUnit.getOrderId()), new Long(l_orderUnit.getOrderUnitId()), new Long(l_lngDeleteFlag)};

        // (3)QueryProcessor.doFindAllQuery( )により、
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "株式顧客勘定明細テーブルを検索  error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "株式顧客勘定明細テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        if (l_lisReturnValue.size() == 0 || l_lisReturnValue == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisReturnValue;
    }

    /**
     * (getトランザクション)<BR>
     * （getTransactionsのオーバーロード）<BR>
     * <BR>
     * 指定した建株ID、発生日付に該当する株式顧客勘定明細の一覧を取得する。<BR>
     * <BR>
     * １）　@株式顧客勘定明細テーブル検索<BR>
     * 以下の条件で株式顧客勘定明細テーブルを検索する<BR>
     * <BR>
     * [検索条件]<BR>
     * 建株ID＝引数.建株ID<BR>
     * トランザクション発生日時＝引数.発生日付と同じ日付<BR>
     * 削除フラグ＝”FALSE”<BR>
     * <BR>
     * ２）　@１）の取得結果の一覧を返却<BR>
     * ※データが存在しない場合にはnullを返却する<BR>
     * @@param l_lngContractId - 建株ID
     * @@param l_datTimestamp - (発生日付)<BR>
     * YYYYMMDD
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 40EB858103BA
     */
    public List getTransactions(long l_lngContractId, Date l_datTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransactions";

        log.entering(STR_METHOD_NAME);

        // (1)戻り値オブジェクトのインスタンスを生成する
        List l_lisReturnValue = new ArrayList();

        // (2)検索条件追加
        // (2-1）検索条件文字列を作成
        String l_strWhere = "contract_id = ? and to_char(fin_transaction_timestamp,'yyyyMMdd')  = ? and delete_flag = ?";
        long l_lngDeleteFlag = 0L;
        // (2-2)文字列配列を生成し
        Object[] l_objWhereValue = { new Long(l_lngContractId), WEB3DateUtility.formatDate(l_datTimestamp, "yyyyMMdd"), new Long(l_lngDeleteFlag)};

        // (3)QueryProcessor.doFindAllQuery( )により、
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "株式顧客勘定明細テーブルを検索  error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "株式顧客勘定明細テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        if (l_lisReturnValue.size() == 0 || l_lisReturnValue == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisReturnValue;
    }

    /**
     * (get受渡金額合計)<BR>
     * （getNetAmount）<BR>
     * <BR>
     * 指定注文に関連するトランザクションデータより、<BR>
     * 注文に対応する合計受渡金額を算出する。<BR>
     * <BR>
     * １）　@株式トランザクションテーブル検索<BR>
     * 　@引数で指定した注文単位に該当する株式トランザクションを<BR>
     * getトランザクション()にて取得する。<BR>
     * <BR>
     * [引数]<BR>
     * 注文単位：　@引数の注文単位オブジェクト<BR>
     * <BR>
     * ２）　@合計金額計算<BR>
     * 　@取得したすべての株式トランザクションの受渡代金を集計し、<BR>
     * 計算結果を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * 
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40BE44C30251
     */
    public double getNetAmountTotal(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNetAmountTotal(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        double l_dblAmout = 0;
        //getトランザクション
        List l_lsttransaction = this.getTransactions(l_orderUnit);
        //合計金額計算
        if (l_lsttransaction != null)
        {
            EqtypeFinTransactionRow l_transactionRow = null;
            int l_intSize = l_lsttransaction.size();
            for (int i = 0; i < l_intSize; i++)
            {
                //受渡金額を集計し、計算結果
                l_transactionRow = (EqtypeFinTransactionRow) l_lsttransaction.get(i);
                l_dblAmout = l_dblAmout + l_transactionRow.getNetAmount();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblAmout;
    }

    /**
     * (get委託手数料合計)<BR>
     * 指定注文に関連するトランザクションデータより、<BR>
     * 注文に対応する委託手数料合計を算出する。<BR>
     * <BR>
     * １）　@株式トランザクションテーブル検索<BR>
     * 　@引数で指定した注文単位に該当する株式トランザクションを<BR>
     * getトランザクション()にて取得する。<BR>
     * <BR>
     * [引数]<BR>
     * 注文単位：　@引数の注文単位オブジェクト<BR>
     * <BR>
     * ２）　@合計金額計算<BR>
     * 　@取得したすべての株式トランザクションの委託手数料を集計し、<BR>
     * 　@計算結果を返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)注文単位オブジェクト
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40A40EA000BB
     */
    public double getCommTotal(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCommTotal(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        //委託手数料合計
        double l_dblCommtatle = 0;
        //getトランザクション
        List l_lsttransaction = this.getTransactions(l_orderUnit);
        if (l_lsttransaction != null)
        {
            EqtypeFinTransactionRow l_transactionRow = null;
            int l_strSize = l_lsttransaction.size();
            for (int i = 0; i < l_strSize; i++)
            {
                //委託手数料を集計し、計算結果
                l_transactionRow = (EqtypeFinTransactionRow) l_lsttransaction.get(i);
                l_dblCommtatle = l_dblCommtatle + l_transactionRow.getCommissionFee();
            }
        }
        log.debug("委託手数料を集計し、計算結果 l_commtatle = " + l_dblCommtatle);

        log.exiting(STR_METHOD_NAME);
        return l_dblCommtatle;
    }

    /**
     * (get委託手数料消費税合計)<BR>
     * 指定注文に関連するトランザクションデータより、<BR>
     * 注文に対応する委託手数料消費税合計を算出する。<BR>
     * <BR>
     * １）　@株式トランザクションテーブル検索<BR>
     * 　@引数で指定した注文単位に該当する株式トランザクションを<BR>
     * 　@getトランザクション()にて取得する。<BR>
     * <BR>
     * [引数]<BR>
     * 注文単位：　@引数の注文単位オブジェクト<BR>
     * <BR>
     * ２）　@合計金額計算<BR>
     * 　@取得したすべての株式トランザクションの委託手数料消費税を集計し、<BR>
     * 　@計算結果を返却する。<BR>
     * @@param l_orderUnit - (注文単位)注文単位オブジェクト
     * 
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40A40EFE029F
     */
    public double getCommConsumptionTaxTotal(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCommConsumptionTaxTotal(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        //委託手数料消費税合計
        double l_dblCommtatleTax = 0;
        //getトランザクション
        List l_lsttransaction = this.getTransactions(l_orderUnit);
        if (l_lsttransaction != null)
        {
            EqtypeFinTransactionRow l_transactionRow = null;
            int l_intSize = l_lsttransaction.size();
            for (int i = 0; i < l_intSize; i++)
            {
                //委託手数料消費税を集計し、計算結果
                l_transactionRow = (EqtypeFinTransactionRow) l_lsttransaction.get(i);
                l_dblCommtatleTax = l_dblCommtatleTax + l_transactionRow.getCommissionFeeTax();
            }
        }
        log.debug("委託手数料消費税を集計し、計算結果 l_commtatleTax = " + l_dblCommtatleTax);

        log.exiting(STR_METHOD_NAME);
        return l_dblCommtatleTax;
    }

    /**
     * (get概算損益(顧客勘定明細))<BR>
     * <BR>
     * 概算譲渡益金額の集計を行う。<BR>
     * <BR>
     * １）譲渡損益の集計を行う。<BR>
     * 　@１－１）引数.顧客勘定明細.譲渡損益有効状態がすべて"有効"の場合、<BR>
     * 　@　@　@　@　@引数.顧客勘定明細.譲渡益金額をすべて加算して返却する。<BR>
     * 　@１－２）上記以外の場合、nullを返却する。 <BR>
     * <BR>
     * @@param l_lisTransactions - (List)<BR>
     * 顧客勘定明細。 <BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getEstimatedProfitLoss(
        List l_lisTransactions) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEstimatedProfitLoss(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //１）譲渡損益の集計を行う。
        //概算損益
        String l_strEstimatedProfitLoss = null;
        if(l_lisTransactions != null)
        {
            //概算譲渡益金額の集計
            BigDecimal l_bdEstimatedProfitLoss = new BigDecimal(0);

            //有効、無効を判断
            boolean l_blnIsValidity = true;

            for(int i = 0; i < l_lisTransactions.size(); i++)
            {
                EqtypeFinTransactionRow l_eqtypeFinTransaction = (EqtypeFinTransactionRow) l_lisTransactions.get(i);

                //１－１）引数.顧客勘定明細.譲渡損益有効状態がすべて"有効"の場合、
                //　@　@　@　@引数.顧客勘定明細.譲渡益金額をすべて加算して返却する。
                if(WEB3CapitalGainStatusDef.VALIDITY.equals(l_eqtypeFinTransaction.getCapitalGainStatus()))
                {
                    BigDecimal l_bdCapitalGain = new BigDecimal(l_eqtypeFinTransaction.getCapitalGain());
                    l_bdEstimatedProfitLoss = l_bdEstimatedProfitLoss.add(l_bdCapitalGain);
                }
                
                //１－２）上記以外の場合、nullを返却する。
                else
                {
                    l_blnIsValidity = false;
                    break;
                }
            }

            if(l_blnIsValidity)
            {
                l_strEstimatedProfitLoss = "" + l_bdEstimatedProfitLoss;
            }
        }
        
        log.debug("【概算損益】= " + l_strEstimatedProfitLoss);
        log.exiting(STR_METHOD_NAME);
        
        //概算損益を返却する。
        return l_strEstimatedProfitLoss;
    }

    /**
     * (get受渡金額合計(顧客勘定明細))<BR>
     * （getNetAmount）<BR>
     * <BR>
     * トランザクションデータに対応する合計受渡金額を算出する。<BR>
     * <BR>
     * １）合計金額計算<BR>
     * 　@全ての引数.顧客勘定明細の受渡代金を集計し、計算結果を返却する。
     * @@param l_lisTransactions - (List)<BR>
     * 株式顧客勘定明細<BR>
     * 
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40BE44C30251
     */
    public double getNetAmountTotal(List l_lisTransactions) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNetAmountTotal(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        double l_dblAmout = 0;

        //合計金額計算
        if (l_lisTransactions != null)
        {
            EqtypeFinTransactionRow l_transactionRow = null;
            int l_intSize = l_lisTransactions.size();
            for (int i = 0; i < l_intSize; i++)
            {
                //受渡金額を集計し、計算結果
                l_transactionRow = (EqtypeFinTransactionRow) l_lisTransactions.get(i);
                l_dblAmout = l_dblAmout + l_transactionRow.getNetAmount();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblAmout;
    }

}
@
