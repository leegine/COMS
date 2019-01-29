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
filename	WEB3EquityContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 建株(WEB3EquityContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/15 呉艶飛 (中訊) 新規作成
Revesion History : 2004/12/01 中尾寿彦(SRA) 残案件対応による修正
Revesion History : 2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 2007/05/21 張騰宇(中訊)　@モデル 1162
*/

package webbroker3.equity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.util.WEB3LogUtility;

/**
 * （建株）。<BR>
 * <BR>
 * 約定ベースの建株を表現する。<BR>
 * 返済／現引現渡した分の残りをグロスで保持する。<BR>
 * xTradeのEqTypeContractImplを拡張したクラス。
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3EquityContract extends EqTypeContractImpl
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityContract.class);

    /**
     * (建株)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 建株ＩＤに一致する行を建株テーブルより取得する。<BR>
     * 取得した建株行オブジェクト（EqTypeContractParams）を指定し
     * スーパークラスのコンストラクタをコールする。<BR>
     * <BR>
     * @@param l_lngContractId - 建株ID<BR>
     * @@return WEB3EquityContract
     * @@throws DataQueryException
     * @@throws DataNetworkException
     * @@roseuid 406A6B6100B3
     */
    public WEB3EquityContract(long l_lngContractId) throws DataQueryException, DataNetworkException
    {
        super(l_lngContractId);
    }
    
    /**
     * (建株)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 引数の建株Row.建株ID、及び引数の建株Rowを<BR>
     * プロパティにセットする。<BR>
     * <BR>
     * @@param l_row - 建株Row<BR>
     * @@return WEB3EquityContract
     */
    public WEB3EquityContract(EqtypeContractRow l_row)
    {
        super(l_row);
    }

    /**
     * (getロック中数量)<BR>
     * （getLockedQuantityのオーバーロード）<BR>
     * 指定の注文単位のロック中の数量を取得する。<BR>
     * <BR>
     * １）　@建株返済指定情報読込<BR>
     * <BR>
     * 以下の条件で建株返済指定情報テーブルを読む。<BR>
     * <BR>
     * [条件]<BR>
     * 建株返済指定情報.建株ＩＤ = this.getContractId()<BR>
     * 建株返済指定情報.注文単位ＩＤ = 引数.注文単位ＩＤ<BR>
     * <BR>
     * 該当行が存在しない場合は0を返却する。<BR>
     * <BR>
     * ２）　@ロック中数量返却<BR>
     * <BR>
     * 取得した建株返済指定情報行ごとに、
     * 建株返済指定情報行.返済注文数量 － 建株返済指定情報行.返済約定数量 を計算する。<BR>
     * <BR>
     * 上記計算結果の、全ての返済指定情報行のSUM値を返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_lngOrderUnitId - 注文単位ＩＤ<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 407B88200118
     */
    public double getLockedQuantity(long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLockedQuantity";
        log.entering(STR_METHOD_NAME);

        double l_dblReturnValue = 0;
        try
        {
            // １）建株返済指定情報読込

            //建玉ＩＤ = this.getContractId()
            String l_strWhere = "contract_id = ? and order_unit_id = ?";
            long l_lngContractIdValue = this.getContractId();

            // [条件1]:建玉返済指定情報.注文単位ＩＤ = 引数.注文単位ＩＤ

            Object[] l_objWhereValues = { new Long(l_lngContractIdValue), new Long(l_lngOrderUnitId)};

            List l_lstReturnList = new ArrayList();

            // データ査詢         
            QueryProcessor processor = Processors.getDefaultProcessor();
            l_lstReturnList = processor.doFindAllQuery(EqtypeClosingContractSpecParams.TYPE, l_strWhere, l_objWhereValues);

            // ２）ロック中数量返却
            int l_intreturnListLength = l_lstReturnList.size();
            if (l_intreturnListLength == 0)
            {
                return 0;
            }
            for (int i = 0; i < l_intreturnListLength; i++)
            {
                // 取得した建株返済指定情報行ごとに、
                //建株返済指定情報行.返済注文数量 － 建株返済指定情報行.返済約定数量 を計算する。
                EqtypeClosingContractSpecRow l_contractSpecRow = (EqtypeClosingContractSpecRow) l_lstReturnList.get(i);
                double l_dblExecutedQuantity = l_contractSpecRow.getExecutedQuantity();
                if (Double.isNaN(l_dblExecutedQuantity))
                {
                    l_dblExecutedQuantity = 0;
                }
                double l_dblQuantity = l_contractSpecRow.getQuantity();
                if (Double.isNaN(l_dblQuantity))
                {
                    l_dblQuantity = 0;
                }

                l_dblReturnValue += (l_dblQuantity - l_dblExecutedQuantity);
            }

        }
        catch (DataQueryException l_ex)
        {
            log.entering(STR_METHOD_NAME);
            String l_strMessage = "建株返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.entering(STR_METHOD_NAME);
            String l_strMessage = "建株返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);

        //上記計算結果の、全ての返済指定情報行のSUM値を返却する。
        return l_dblReturnValue;
    }

    /**
     * (get返済約定済数量)<BR>
     * 指定の注文単位に関連する返済約定済数量を取得する。<BR>
     * <BR>
     * １）　@返済指定情報読込<BR>
     * <BR>
     * 以下の条件で建株返済指定情報テーブルを読む。<BR>
     * <BR>
     * [条件]<BR>
     * 建株返済指定情報.建株ＩＤ = this.getContractId()<BR>
     * 建株返済指定情報.注文単位ＩＤ = 引数.注文単位ＩＤ<BR>
     * <BR>
     * 該当行が存在しない場合は0を返却する。<BR>
     * <BR>
     * ２）　@返済約定済数量返却<BR>
     * <BR>
     * 取得した建株返済指定情報行.返済約定数量の合計値を返却する。<BR>
     * <BR>
     * @@param l_lngOrderUnitId - 注文単位ＩＤ<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 407B8A3503B8
     */
    public double getClosingExecutedQuantity(long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getClosingExecutedQuantity";
        log.entering(STR_METHOD_NAME);

        double l_dblReturnValue = 0;
        try
        {
            // １）建株返済指定情報読込

            //建玉ＩＤ = this.getContractId()
            String l_strWhere = " contract_id = ? and order_unit_id = ? ";
            long l_lngContractIdValue = this.getContractId();

            // [条件1]:建玉返済指定情報.注文単位ＩＤ = 引数.注文単位ＩＤ

            Object[] l_objWhereValues = { new Long(l_lngContractIdValue), new Long(l_lngOrderUnitId)};

            List l_lstReturnList = new ArrayList();

            // データ査詢         
            QueryProcessor processor = Processors.getDefaultProcessor();
            l_lstReturnList = processor.doFindAllQuery(EqtypeClosingContractSpecParams.TYPE, l_strWhere, l_objWhereValues);

            // ２）ロック中数量返却
            int l_intreturnListLength = l_lstReturnList.size();
            if (l_intreturnListLength == 0)
            {
                return 0;
            }
            for (int i = 0; i < l_intreturnListLength; i++)
            {
                // 取得した建株返済指定情報行ごとに、
                //取得した建株返済指定情報行ごとに、建株返済指定情報行.返済注文数量 － 建株返済指定情報行.返済約定数量 を計算する。
                EqtypeClosingContractSpecRow l_contractSpecRow = (EqtypeClosingContractSpecRow) l_lstReturnList.get(i);
                double l_dblexecutedQuantity = l_contractSpecRow.getExecutedQuantity();
                if (Double.isNaN(l_dblexecutedQuantity))
                {
                    l_dblexecutedQuantity = 0;
                }

                l_dblReturnValue += l_dblexecutedQuantity;
            }

        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "建株返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "建株返済指定情報テーブルを検索 error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);

        //取得した建株返済指定情報行.返済約定数量の合計値を返却する。
        return l_dblReturnValue;
    }

    /**
     * (get評価損益)<BR>
     * 指定単価で、指定数量を返済した場合の評価損益を取得する。<BR>
     * <BR>
     * 以下の計算結果を返却する。<BR>
     * <BR>
     * ○this.isLong( )==true（買建）の場合<BR>
     * （パラメータ.計算単価 × パラメータ.数量） － this.get建代金(パラメータ.数量)<BR>
     * <BR>
     * ○this.isLong( )==false（売建）の場合<BR>
     * this.get建代金(パラメータ.数量) － （パラメータ.計算単価 × パラメータ.数量）<BR>
     * <BR>
     * @@param l_dblCalcUnitPrice - 計算単価。<BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@return double
     * @@roseuid 40601CC8000F
     */
    public double getAppraisalProfitOrLoss(double l_dblCalcUnitPrice, double l_dblQuantity)
    {
        final String STR_METHOD_NAME = "getAppraisalProfitOrLoss";
        log.entering(STR_METHOD_NAME);

        double l_dblReturnValue = 0;
        //this.isLong( )==true（買建）の場合
        if (this.isLong())
        {
            l_dblReturnValue = (l_dblCalcUnitPrice * l_dblQuantity) - this.getContractAmount(l_dblQuantity);
        }
        //this.isLong( )==false（売建）の場合
        else
        {
            l_dblReturnValue = this.getContractAmount(l_dblQuantity) - (l_dblCalcUnitPrice * l_dblQuantity);
        }

        log.exiting(STR_METHOD_NAME);
        return new BigDecimal(l_dblReturnValue).longValue();
    }

    /**
     * (get建代金)<BR>
     * 指定数量における建代金を取得する。<BR>
     * <BR>
     * 以下の計算結果を返却する。<BR>
     * <BR>
     * ※株式分割等により、建単価には小数点以下第２位まで値が入っていることがあるので、<BR>
     * ※計算結果の円未満切捨を行う。<BR>
     * <BR>
     * getContractPrice() × パラメータ.数量<BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@return double
     * @@roseuid 4060201A0239
     */
    public double getContractAmount(double l_dblQuantity)
    {
        final String STR_METHOD_NAME = "getContractAmount";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return new BigDecimal(this.getContractPrice() * l_dblQuantity).longValue();
    }

    /**
     * （get建手数料）<BR>
     * <BR>
     * 指定数量分の建手数料を計算する。<BR>
     * <BR>
     * this.get建手数料(注文数量, 注文単位ID)にdelegateする。<BR>
     * ※注文数量には引数の数量、注文単位IDには0（＝新規注文）をセットし、<BR>
     * ※未決済分の建手数料を取得する。<BR>
     * <BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getSetupFee(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSetupFee(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getSetupFee(l_dblQuantity, 0L);
    }

    /**
     * （get建手数料消費税）<BR>
     * <BR>
     * 指定数量分の建手数料消費税を計算する。<BR>
     * <BR>
     * this.get建手数料消費税(注文数量, 注文単位ID)にdelegateする。<BR>
     * ※注文数量には引数の数量、注文単位IDには0（＝新規注文）をセットし、<BR>
     * ※未決済分の建手数料消費税を取得する。<BR>
     * <BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getSetupFeeTax(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSetupFeeTax(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getSetupFeeTax(l_dblQuantity, 0L);
    }

    /**
     * （get名義書換料）<BR>
     * <BR>
     * 指定数量分の名義書換料を計算する。<BR>
     * <BR>
     * this.get名義書換料(注文数量, 注文単位ID)にdelegateする。<BR>
     * ※注文数量には引数の数量、注文単位IDには0（＝新規注文）をセットし、<BR>
     * ※未決済分の名義書換料を取得する。<BR>
     * <BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getNameTransferFee(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNameTransferFee(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getNameTransferFee(l_dblQuantity, 0L);
    }

    /**
     * （get名義書換料消費税）<BR>
     * <BR>
     * 指定数量分の名義書換料消費税を計算する。<BR>
     * <BR>
     * this.get名義書換料消費税(注文数量, 注文単位ID)にdelegateする。<BR>
     * ※注文数量には引数の数量、注文単位IDには0（＝新規注文）をセットし、<BR>
     * ※未決済分の名義書換料消費税を取得する。<BR>
     * <BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getNameTransferFeeTax(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNameTransferFeeTax(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getNameTransferFeeTax(l_dblQuantity, 0L);
    }

    /**
     * （get管理費）<BR>
     * <BR>
     * 指定数量分の管理費を計算する。<BR>
     * <BR>
     * this.get管理費(注文数量, 注文単位ID)にdelegateする。<BR>
     * ※注文数量には引数の数量、注文単位IDには0（＝新規注文）をセットし、<BR>
     * ※未決済分の管理費を取得する。<BR>
     * <BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getManagementFee(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getManagementFee(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getManagementFee(l_dblQuantity, 0L);
    }

    /**
     * （get管理費消費税）<BR>
     * <BR>
     * 指定数量分の管理費消費税を計算する。<BR>
     * <BR>
     * this.get管理費消費税(注文数量, 注文単位ID)にdelegateする。<BR>
     * ※注文数量には引数の数量、注文単位IDには0（＝新規注文）をセットし、<BR>
     * ※未決済分の管理費消費税を取得する。<BR>
     * <BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getManagementFeeTax(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getManagementFeeTax(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getManagementFeeTax(l_dblQuantity, 0L);
    }

    /**
     * （get順日歩）<BR>
     * <BR>
     * 指定数量分の順日歩を計算する。<BR>
     * <BR>
     * this.get順日歩(注文数量, 注文単位ID)にdelegateする。<BR>
     * ※注文数量には引数の数量、注文単位IDには0（＝新規注文）をセットし、<BR>
     * ※未決済分の順日歩を取得する。<BR>
     * <BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getInterestFee(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInterestFee(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getInterestFee(l_dblQuantity, 0L);
    }

    /**
     * （get逆日歩）<BR>
     * <BR>
     * 指定数量分の逆日歩を計算する。<BR>
     * <BR>
     * this.get逆日歩(注文数量, 注文単位ID)にdelegateする。<BR>
     * ※注文数量には引数の数量、注文単位IDには0（＝新規注文）をセットし、<BR>
     * ※未決済分の逆日歩を取得する。<BR>
     * <BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getPayInterestFee(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPayInterestFee(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getPayInterestFee(l_dblQuantity, 0L);
    }

    /**
     * （get貸株料）<BR>
     * <BR>
     * 指定数量分の貸株料を計算する。<BR>
     * <BR>
     * this.get貸株料(注文数量, 注文単位ID)にdelegateする。<BR>
     * ※注文数量には引数の数量、注文単位IDには0（＝新規注文）をセットし、<BR>
     * ※未決済分の貸株料を取得する。<BR>
     * <BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getLoanEquityFee(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLoanEquityFee(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getLoanEquityFee(l_dblQuantity, 0L);
    }

    /**
     * （getその他）<BR>
     * <BR>
     * 指定数量分のその他を計算する。<BR>
     * <BR>
     * this.getその他(注文数量, 注文単位ID)にdelegateする。<BR>
     * ※注文数量には引数の数量、注文単位IDには0（＝新規注文）をセットし、<BR>
     * ※未決済分のその他を取得する。<BR>
     * <BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getOther(double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOther(double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getOther(l_dblQuantity, 0L);
    }

    /**
     * (get（部店市場弁済別）取扱条件)<BR>
     * 自身のプロパティを指定し<BR>、
     * 該当する「（部店市場弁済別）取扱条件」オブジェクトを取得する。<BR>
     * ※該当するレコードの「取扱可能」＝falseの場合は、<BR>
     * ※「該当レコードなし」と同じ扱いとする。<BR>
     * <BR>
     * １）　@this.口座ID に該当する顧客オブジェクトを取得する。<BR>
     * 　@　@　@（証券会社コード、部店コードの取得）<BR>
     * <BR>
     * ２）　@this.市場ID に該当する市場オブジェクトを取得する。<BR>
     * 　@　@　@（市場コードの取得）<BR>
     * <BR>
     * ３）　@（部店市場弁済別）取扱条件(証券会社コード, 部店コード, 市場コード, this.弁済区分,<BR> 
     * 　@　@　@this.弁済期限値)（コンストラクタ）をコールし、<BR>
     * 　@　@　@（部店市場弁済別）取扱条件オブジェクトを取得する。<BR>
     * <BR>
     * ４）　@３）で該当データなし、<BR>
     * 　@　@　@または取得した（部店市場弁済別）取扱条件オブジェクト.is取扱可能( )＝falseの場合、<BR>
     * 　@　@　@nullを返す。<BR>
     * 　@　@　@上記以外の場合は、取得した（部店市場弁済別）取扱条件オブジェクトを返す。<BR>
     * @@return WEB3GentradeBranchMarketRepayDealtCond
     * @@throws WEB3BaseException
     * @@roseuid 40C4093600E6
     */
    public WEB3GentradeBranchMarketRepayDealtCond getBranchMarketRepayDealtCond() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBranchMarketRepayDealtCond()";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeBranchMarketRepayDealtCond l_branchmarketRepayDealtCond = null;
        //１）　@this.口座ID に該当する顧客オブジェクトを取得する。
        String l_strinstitutionCode = this.getSubAccount().getInstitution().getInstitutionCode();
        String l_strbranchCode = this.getSubAccount().getMainAccount().getBranch().getBranchCode();
        try
        {
            //２）　@this.市場ID に該当する市場オブジェクトを取得する。
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(this.getMarketId());
            String l_strmarketCode = l_market.getMarketCode();
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) this.getDataSourceObject();

            //（部店市場弁済別）取扱条件(証券会社コード, 部店コード, 市場コード, this.弁済区分, this.弁済期限値)（コンストラクタ）をコールし、
            //（部店市場弁済別）取扱条件オブジェクトを取得する。
            l_branchmarketRepayDealtCond =
                new WEB3GentradeBranchMarketRepayDealtCond(l_strinstitutionCode, l_strbranchCode, l_strmarketCode, l_eqtypeContractRow.getRepaymentType(), l_eqtypeContractRow.getRepaymentNum());
            //４）　@３）で該当データなし、
            //または取得した（部店市場弁済別）取扱条件オブジェクト.is取扱可能( )＝falseの場合、
            //nullを返す。
            if (!l_branchmarketRepayDealtCond.isHandlingPossible())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

        }
        catch (DataFindException l_dfe)
        {
            String l_strMessage = "市場オブジェクトテーブルを検索 error";
            log.error(l_strMessage, l_dfe);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_dfe.getMessage(), l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            String l_strMessage = "市場オブジェクトテーブルを検索 error";
            log.error(l_strMessage, l_dqe);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            String l_strMessage = "市場オブジェクトテーブルを検索 error";
            log.error(l_strMessage, l_dne);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_dne.getMessage(), l_dne);
        }
        catch (WEB3SystemLayerException l_sle)
        {
            log.error(STR_METHOD_NAME, l_sle);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_sle.getMessage(), l_sle);
        }
        log.exiting(STR_METHOD_NAME);
        return l_branchmarketRepayDealtCond;
    }

    /**
     * （get評価損益（建株諸経費考慮））<BR>
     * <BR>
     * 指定単価で、指定数量を返済した場合の評価損益（建株諸経費の考慮あり）を取得する。<BR>
     * <BR>
     * this.get評価損益（建株諸経費考慮）(計算単価, 数量, 注文単位ID)に<BR>
     * delegateする。<BR>
     * <BR>
     * -------------------------------------------<BR>
     * ＜get評価損益（建株諸経費考慮）(計算単価, 数量, 注文単位ID)：<BR>
     * 　@　@引数設定仕様＞<BR>
     * <BR>
     * 計算単価：　@引数の計算単価<BR>
     * 数量：　@引数の数量<BR>
     * 注文単位ID：　@0（＝新規注文）<BR>
     * -------------------------------------------<BR>
     * <BR>
     * @@param l_dblCalcUnitPrice - 計算単価。<BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getAppraisalProfitOrLossExpenses(
        double l_dblCalcUnitPrice,
        double l_dblQuantity)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAppraisalProfitOrLossExpenses(double, double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getAppraisalProfitOrLossExpenses(l_dblCalcUnitPrice, l_dblQuantity, 0L);
    }

    /**
     * （get評価損益（建株諸経費考慮））<BR>
     * <BR>
     * 指定単価で、指定数量を返済した場合の評価損益（建株諸経費の考慮あり）を取得する。<BR>
     * <BR>
     * 以下の計算結果を返却する。<BR>
     * <BR>
     * ○this.isLong( )==true（買建）の場合<BR>
     * （パラメータ.計算単価 × パラメータ.数量） － this.get建代金(パラメータ.数量)　@－　@建株諸経費(*1)<BR>
     * <BR>
     * ○this.isLong( )==false（売建）の場合<BR>
     * this.get建代金(パラメータ.数量) － （パラメータ.計算単価 × パラメータ.数量）　@－　@建株諸経費(*1)<BR>
     * <BR>
     * (*1)建株諸経費<BR>
     * 株式計算サービス.calc諸経費( )の戻り値を設定する。<BR>
     * -------------------------------------------<BR>
     * ＜calc諸経費( )：引数設定仕様＞<BR>
     * <BR>
     * 委託手数料、委託手数料消費税：　@0（決済手数料は含めない）<BR>
     * <BR>
     * 以下、建株諸経費項目は、当クラスの建株諸経費取得メソッドより取得する。<BR>
     * --------------------------<BR>
     * 建手数料：　@this.get建手数料()の戻り値<BR>
     * 建手数料消費税：　@this.get建手数料消費税()の戻り値<BR>
     * 名義書換料：　@this.get名義書換料()の戻り値<BR>
     * 名義書換料消費税：　@this.get名義書換料消費税(0)の戻り値<BR>
     * 管理費：　@this.get管理費()の戻り値<BR>
     * 管理費消費税：　@this.get管理費消費税()の戻り値<BR>
     * 順日歩：　@this.get順日歩()の戻り値<BR>
     * 逆日歩：　@this.get逆日歩()の戻り値<BR>
     * 貸株料：　@this.get貸株料()の戻り値<BR>
     * その他：　@this.getその他()の戻り値<BR>
     * <BR>
     * ※建株諸経費取得メソッド（this.getXX( )メソッド）の引数は、以下の通りに設定する。<BR>
     * <BR>
     * 　@　@注文数量：　@パラメータ.数量<BR>
     * 　@　@注文単位ID：　@パラメータ.注文単位ID<BR>
     * --------------------------<BR>
     * <BR>
     * 建区分：　@this.建区分<BR>
     * -------------------------------------------<BR>
     * <BR>
     * @@param l_dblCalcUnitPrice - 計算単価。<BR>
     * @@param l_dblQuantity - 数量<BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getAppraisalProfitOrLossExpenses(
        double l_dblCalcUnitPrice,
        double l_dblQuantity,
        long l_orderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAppraisalProfitOrLossExpenses(double, double, long)";
        log.entering(STR_METHOD_NAME);

        double l_dblReturnValue = 0.0D;

        // 建株諸経費の計算を行う
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_equityBizLogicProvider = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
        EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow)this.getDataSourceObject();
        double l_dblexpenses =
            l_equityBizLogicProvider.calcExpenses(
                0,
                0,
                getSetupFee(l_dblQuantity, l_orderUnitId),
                getSetupFeeTax(l_dblQuantity, l_orderUnitId),
                getNameTransferFee(l_dblQuantity, l_orderUnitId),
                getNameTransferFeeTax(l_dblQuantity, l_orderUnitId),
                getManagementFee(l_dblQuantity, l_orderUnitId),
                getManagementFeeTax(l_dblQuantity, l_orderUnitId),
                getInterestFee(l_dblQuantity, l_orderUnitId),
                getPayInterestFee(l_dblQuantity, l_orderUnitId),
                getLoanEquityFee(l_dblQuantity, l_orderUnitId),
                getOther(l_dblQuantity, l_orderUnitId),
                l_eqtypeContractRow.getContractType());

        // ○this.isLong( )==true（買建）の場合
        // （パラメータ.計算単価 × パラメータ.数量） － this.get建代金(パラメータ.数量)　@－　@建株諸経費(*1)
        //
        // ○this.isLong( )==false（売建）の場合
        // this.get建代金(パラメータ.数量) － （パラメータ.計算単価 × パラメータ.数量）　@－　@建株諸経費(*1)
        if (this.isLong())
        {
            l_dblReturnValue = (l_dblCalcUnitPrice * l_dblQuantity) - this.getContractAmount(l_dblQuantity) - l_dblexpenses;
        }
        else
        {
            l_dblReturnValue = this.getContractAmount(l_dblQuantity) - (l_dblCalcUnitPrice * l_dblQuantity) - l_dblexpenses;
        }

        log.exiting(STR_METHOD_NAME);
        return new BigDecimal(l_dblReturnValue).longValue();
    }

    /**
     * （get建手数料）<BR>
     * <BR>
     * 指定数量分の建手数料を計算する。<BR>
     * －（未決済分(*1)の建手数料＋決済済分(*2)の建手数料）を返す。<BR>
     * 　@　@-------------------------------<BR>
     * 　@　@(*1)建株の同名プロパティから按分計算により取得。<BR>
     * 　@　@(*2)決済約定により建株から減算された金額。決済のトランザクションより取得。<BR>
     * 　@　@-------------------------------<BR>
     * <BR>
     * １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、<BR>
     * 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。<BR>
     * <BR>
     * 　@　@　@this.get返済約定済数量(引数の注文単位ID)コールにより取得する。<BR>
     * <BR>
     * ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の建手数料を取得する。<BR>
     * <BR>
     * ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。<BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc諸経費按分比率( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@建株残数：　@this.建株数<BR>
     * 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２－２）　@未決済分の建手数料を計算する。<BR>
     * <BR>
     * 　@　@　@　@建手数料（未決済分） = this.建手数料 * 取得した按分比率（factor）<BR>
     * 　@　@　@　@※円未満切捨て。<BR>
     * <BR>
     * ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の建手数料を取得する。<BR>
     * <BR>
     * ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を<BR>
     * 　@　@　@　@　@取得する。 <BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@注文単位ID：　@引数の注文単位ID<BR>
     * 　@　@　@建株ID：　@this.建株ID<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ３－２）　@取得したトランザクションの全要素の建手数料を集計する。<BR>
     * <BR>
     * 　@　@　@　@　@建手数料（決済済分） += トランザクション.建手数料<BR>
     * <BR>
     * 　@　@　@※決済約定がない場合は建手数料（決済済分）は0となる 。<BR>
     * <BR>
     * ４）　@（建手数料（未決済分） + 建手数料（決済済分））を返却する。<BR>
     * <BR>
     * @@param l_dblQuantity - 注文数量<BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getSetupFee(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSetupFee(double, long)";
        log.entering(STR_METHOD_NAME);

        // １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、
        // 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の建手数料を取得する。
        double l_dblSetupFeeOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。
            //      　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@建株残数：　@this.建株数<BR>
            // 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。
            // 　@　@　@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // ２－２）　@未決済分の建手数料を計算する。
            //
            // 　@　@　@　@建手数料（未決済分） = this.建手数料 * 取得した按分比率（factor）
            // 　@　@　@　@※円未満切捨て。
            l_dblSetupFeeOpen = Math.floor(this.getSetupFee() * l_dblFactor);
        }
        // ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の建手数料を取得する。
        double l_dblSetupFeeClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を
            // 　@　@　@　@　@取得する。 
            // 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@注文単位ID：　@引数の注文単位ID
            // 　@　@　@建株ID：　@this.建株ID
            // 　@　@　@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // ３－２）　@取得したトランザクションの全要素の建手数料を集計する。
            //
            // 　@　@　@　@　@建手数料（決済済分） += トランザクション.建手数料
            //
            // 　@　@　@※決済約定がない場合は建手数料（決済済分）は0となる 。
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblSetupFeeClose += l_finTransaction.getImportedSetupFee();
            }
        }

        // ４）　@（建手数料（未決済分） + 建手数料（決済済分））を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblSetupFeeOpen + l_dblSetupFeeClose;
    }

   /**
    * （get建手数料消費税）<BR>
    * <BR>
    * 指定数量分の建手数料消費税を計算する。<BR>
    * －（未決済分(*1)の建手数料消費税＋決済済分(*2)の建手数料消費税）を返す。<BR>
    * 　@　@-------------------------------<BR>
    * 　@　@(*1)建株の同名プロパティから按分計算により取得。<BR>
    * 　@　@(*2)決済約定により建株から減算された金額。決済のトランザクションより取得。<BR>
    * 　@　@-------------------------------<BR>
    * <BR>
    * １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、<BR>
    * 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。<BR>
    * <BR>
    * 　@　@　@this.get返済約定済数量(引数の注文単位ID)コールにより取得する。<BR>
    * <BR>
    * ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の建手数料消費税を取得する。<BR>
    * <BR>
    * ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。<BR>
    * 　@　@　@　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。<BR>
    * <BR>
    * 　@　@　@----------------------------------------------------------<BR>
    * 　@　@　@＜calc諸経費按分比率( )：引数設定仕様＞<BR>
    * <BR>
    * 　@　@　@建株残数：　@this.建株数<BR>
    * 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。<BR>
    * 　@　@　@----------------------------------------------------------<BR>
    * <BR>
    * ２－２）　@未決済分の建手数料消費税を計算する。<BR>
    * <BR>
    * 　@　@　@　@建手数料消費税（未決済分） = this.建手数料消費税 * 取得した按分比率（factor）<BR>
    * 　@　@　@　@※円未満切捨て。<BR>
    * <BR>
    * ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の建手数料消費税を取得する。<BR>
    * <BR>
    * ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を<BR>
    * 　@　@　@　@　@取得する。 <BR>
    * 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。<BR>
    * <BR>
    * 　@　@　@----------------------------------------------------------<BR>
    * 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞<BR>
    * <BR>
    * 　@　@　@注文単位ID：　@引数の注文単位ID<BR>
    * 　@　@　@建株ID：　@this.建株ID<BR>
    * 　@　@　@----------------------------------------------------------<BR>
    * <BR>
    * ３－２）　@取得したトランザクションの全要素の建手数料消費税を集計する。<BR>
    * <BR>
    * 　@　@　@　@　@建手数料消費税（決済済分） += トランザクション.建手数料消費税<BR>
    * <BR>
    * 　@　@　@※決済約定がない場合は建手数料消費税（決済済分）は0となる 。<BR>
    * <BR>
    * ４）　@（建手数料消費税（未決済分） + 建手数料消費税（決済済分））を返却する。<BR>
    * <BR>
     * @@param l_dblQuantity - 注文数量<BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getSetupFeeTax(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSetupFeeTax(double, long)";
        log.entering(STR_METHOD_NAME);

        // １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、
        // 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の建手数料消費税を取得する。
        double l_dblSetupFeeTaxOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。
            //      　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@建株残数：　@this.建株数<BR>
            // 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。
            // 　@　@　@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // ２－２）　@未決済分の建手数料消費税を計算する。
            //
            // 　@　@　@　@建手数料消費税（未決済分） = this.建手数料消費税 * 取得した按分比率（factor）
            // 　@　@　@　@※円未満切捨て。
            l_dblSetupFeeTaxOpen = Math.floor(this.getSetupFeeTax() * l_dblFactor);
        }
        // ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の建手数料消費税を取得する。
        double l_dblSetupFeeTaxClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を
            // 　@　@　@　@　@取得する。 
            // 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@注文単位ID：　@引数の注文単位ID
            // 　@　@　@建株ID：　@this.建株ID
            // 　@　@　@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // ３－２）　@取得したトランザクションの全要素の建手数料消費税を集計する。
            //
            // 　@　@　@　@　@建手数料消費税（決済済分） += トランザクション.建手数料消費税
            //
            // 　@　@　@※決済約定がない場合は建手数料消費税（決済済分）は0となる 。
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblSetupFeeTaxClose += l_finTransaction.getImportedSetupFeeTax();
            }
        }

        // ４）　@（建手数料消費税（未決済分） + 建手数料消費税（決済済分））を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblSetupFeeTaxOpen + l_dblSetupFeeTaxClose;
    }

    /**
     * （get名義書換料）<BR>
     * <BR>
     * 指定数量分の名義書換料を計算する。<BR>
     * －（未決済分(*1)の名義書換料＋決済済分(*2)の名義書換料）を返す。<BR>
     * 　@　@-------------------------------<BR>
     * 　@　@(*1)建株の同名プロパティから按分計算により取得。<BR>
     * 　@　@(*2)決済約定により建株から減算された金額。決済のトランザクションより取得。<BR>
     * 　@　@-------------------------------<BR>
     * <BR>
     * １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、<BR>
     * 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。<BR>
     * <BR>
     * 　@　@　@this.get返済約定済数量(引数の注文単位ID)コールにより取得する。<BR>
     * <BR>
     * ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の名義書換料を取得する。<BR>
     * <BR>
     * ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。<BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc諸経費按分比率( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@建株残数：　@this.建株数<BR>
     * 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２－２）　@未決済分の名義書換料を計算する。<BR>
     * <BR>
     * 　@　@　@　@名義書換料（未決済分） = this.名義書換料 * 取得した按分比率（factor）<BR>
     * 　@　@　@　@※円未満切捨て。<BR>
     * <BR>
     * ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の名義書換料を取得する。<BR>
     * <BR>
     * ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を<BR>
     * 　@　@　@　@　@取得する。 <BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@注文単位ID：　@引数の注文単位ID<BR>
     * 　@　@　@建株ID：　@this.建株ID<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ３－２）　@取得したトランザクションの全要素の名義書換料を集計する。<BR>
     * <BR>
     * 　@　@　@　@　@名義書換料（決済済分） += トランザクション.名義書換料<BR>
     * <BR>
     * 　@　@　@※決済約定がない場合は名義書換料（決済済分）は0となる 。<BR>
     * <BR>
     * ４）　@（名義書換料（未決済分） + 名義書換料（決済済分））を返却する。<BR>
     * <BR>
     * @@param l_dblQuantity - 注文数量<BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getNameTransferFee(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNameTransferFee(double, long)";
        log.entering(STR_METHOD_NAME);

        // １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、
        // 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の名義書換料を取得する。
        double l_dblNameTransferFeeOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。
            //      　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@建株残数：　@this.建株数<BR>
            // 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。
            // 　@　@　@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // ２－２）　@未決済分の名義書換料を計算する。
            //
            // 　@　@　@　@名義書換料（未決済分） = this.名義書換料 * 取得した按分比率（factor）
            // 　@　@　@　@※円未満切捨て。
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) this.getDataSourceObject();
            l_dblNameTransferFeeOpen = Math.floor(l_eqtypeContractRow.getNameTransferFee() * l_dblFactor);
        }
        // ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の名義書換料を取得する。
        double l_dblNameTransferFeeClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を
            // 　@　@　@　@　@取得する。 
            // 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@注文単位ID：　@引数の注文単位ID
            // 　@　@　@建株ID：　@this.建株ID
            // 　@　@　@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // ３－２）　@取得したトランザクションの全要素の名義書換料を集計する。
            //
            // 　@　@　@　@　@名義書換料（決済済分） += トランザクション.名義書換料
            //
            // 　@　@　@※決済約定がない場合は名義書換料（決済済分）は0となる 。
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblNameTransferFeeClose += l_finTransaction.getImportedNameTransferFee();
            }
        }

        // ４）　@（名義書換料（未決済分） + 名義書換料（決済済分））を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblNameTransferFeeOpen + l_dblNameTransferFeeClose;
    }

   /**
    * （get名義書換料消費税）<BR>
    * <BR>
    * 指定数量分の名義書換料消費税を計算する。<BR>
    * －（未決済分(*1)の名義書換料消費税＋決済済分(*2)の名義書換料消費税）を返す。<BR>
    * 　@　@-------------------------------<BR>
    * 　@　@(*1)建株の同名プロパティから按分計算により取得。<BR>
    * 　@　@(*2)決済約定により建株から減算された金額。決済のトランザクションより取得。<BR>
    * 　@　@-------------------------------<BR>
    * <BR>
    * １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、<BR>
    * 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。<BR>
    * <BR>
    * 　@　@　@this.get返済約定済数量(引数の注文単位ID)コールにより取得する。<BR>
    * <BR>
    * ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の名義書換料消費税を取得する。<BR>
    * <BR>
    * ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。<BR>
    * 　@　@　@　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。<BR>
    * <BR>
    * 　@　@　@----------------------------------------------------------<BR>
    * 　@　@　@＜calc諸経費按分比率( )：引数設定仕様＞<BR>
    * <BR>
    * 　@　@　@建株残数：　@this.建株数<BR>
    * 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。<BR>
    * 　@　@　@----------------------------------------------------------<BR>
    * <BR>
    * ２－２）　@未決済分の名義書換料消費税を計算する。<BR>
    * <BR>
    * 　@　@　@　@名義書換料消費税（未決済分） = this.名義書換料消費税 * 取得した按分比率（factor）<BR>
    * 　@　@　@　@※円未満切捨て。<BR>
    * <BR>
    * ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の名義書換料消費税を取得する。<BR>
    * <BR>
    * ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を<BR>
    * 　@　@　@　@　@取得する。 <BR>
    * 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。<BR>
    * <BR>
    * 　@　@　@----------------------------------------------------------<BR>
    * 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞<BR>
    * <BR>
    * 　@　@　@注文単位ID：　@引数の注文単位ID<BR>
    * 　@　@　@建株ID：　@this.建株ID<BR>
    * 　@　@　@----------------------------------------------------------<BR>
    * <BR>
    * ３－２）　@取得したトランザクションの全要素の名義書換料消費税を集計する。<BR>
    * <BR>
    * 　@　@　@　@　@名義書換料消費税（決済済分） += トランザクション.名義書換料消費税<BR>
    * <BR>
    * 　@　@　@※決済約定がない場合は名義書換料消費税（決済済分）は0となる 。<BR>
    * <BR>
    * ４）　@（名義書換料消費税（未決済分） + 名義書換料消費税（決済済分））を返却する。<BR>
    * <BR>
     * @@param l_dblQuantity - 注文数量<BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getNameTransferFeeTax(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNameTransferFeeTax(double, long)";
        log.entering(STR_METHOD_NAME);

        // １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、
        // 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の名義書換料消費税を取得する。
        double l_dblNameTransferFeeTaxOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。
            //      　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@建株残数：　@this.建株数<BR>
            // 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。
            // 　@　@　@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // ２－２）　@未決済分の名義書換料消費税を計算する。
            //
            // 　@　@　@　@名義書換料消費税（未決済分） = this.名義書換料消費税 * 取得した按分比率（factor）
            // 　@　@　@　@※円未満切捨て。
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) this.getDataSourceObject();
            l_dblNameTransferFeeTaxOpen = Math.floor(l_eqtypeContractRow.getNameTransferFeeTax() * l_dblFactor);
        }
        // ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の名義書換料消費税を取得する。
        double l_dblNameTransferFeeTaxClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を
            // 　@　@　@　@　@取得する。 
            // 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@注文単位ID：　@引数の注文単位ID
            // 　@　@　@建株ID：　@this.建株ID
            // 　@　@　@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // ３－２）　@取得したトランザクションの全要素の名義書換料消費税を集計する。
            //
            // 　@　@　@　@　@名義書換料消費税（決済済分） += トランザクション.名義書換料消費税
            //
            // 　@　@　@※決済約定がない場合は名義書換料消費税（決済済分）は0となる 。
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblNameTransferFeeTaxClose += l_finTransaction.getImportedNameTransferFeeTax();
            }
        }

        // ４）　@（名義書換料消費税（未決済分） + 名義書換料消費税（決済済分））を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblNameTransferFeeTaxOpen + l_dblNameTransferFeeTaxClose;
    }

    /**
     * （get管理費）<BR>
     * <BR>
     * 指定数量分の管理費を計算する。<BR>
     * －（未決済分(*1)の管理費＋決済済分(*2)の管理費）を返す。<BR>
     * 　@　@-------------------------------<BR>
     * 　@　@(*1)建株の同名プロパティから按分計算により取得。<BR>
     * 　@　@(*2)決済約定により建株から減算された金額。決済のトランザクションより取得。<BR>
     * 　@　@-------------------------------<BR>
     * <BR>
     * １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、<BR>
     * 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。<BR>
     * <BR>
     * 　@　@　@this.get返済約定済数量(引数の注文単位ID)コールにより取得する。<BR>
     * <BR>
     * ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の管理費を取得する。<BR>
     * <BR>
     * ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。<BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc諸経費按分比率( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@建株残数：　@this.建株数<BR>
     * 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２－２）　@未決済分の管理費を計算する。<BR>
     * <BR>
     * 　@　@　@　@管理費（未決済分） = this.管理費 * 取得した按分比率（factor）<BR>
     * 　@　@　@　@※円未満切捨て。<BR>
     * <BR>
     * ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の管理費を取得する。<BR>
     * <BR>
     * ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を<BR>
     * 　@　@　@　@　@取得する。 <BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@注文単位ID：　@引数の注文単位ID<BR>
     * 　@　@　@建株ID：　@this.建株ID<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ３－２）　@取得したトランザクションの全要素の管理費を集計する。<BR>
     * <BR>
     * 　@　@　@　@　@管理費（決済済分） += トランザクション.管理費<BR>
     * <BR>
     * 　@　@　@※決済約定がない場合は管理費（決済済分）は0となる 。<BR>
     * <BR>
     * ４）　@（管理費（未決済分） + 管理費（決済済分））を返却する。<BR>
     * <BR>
     * @@param l_dblQuantity - 注文数量<BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getManagementFee(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getManagementFee(double, long)";
        log.entering(STR_METHOD_NAME);

        // １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、
        // 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の管理費を取得する。
        double l_dblManagementFeeOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。
            //      　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@建株残数：　@this.建株数<BR>
            // 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。
            // 　@　@　@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // ２－２）　@未決済分の管理費を計算する。
            //
            // 　@　@　@　@管理費（未決済分） = this.管理費 * 取得した按分比率（factor）
            // 　@　@　@　@※円未満切捨て。
            l_dblManagementFeeOpen = Math.floor(this.getManagementFee() * l_dblFactor);
        }
        // ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の管理費を取得する。
        double l_dblManagementFeeClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を
            // 　@　@　@　@　@取得する。 
            // 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@注文単位ID：　@引数の注文単位ID
            // 　@　@　@建株ID：　@this.建株ID
            // 　@　@　@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // ３－２）　@取得したトランザクションの全要素の管理費を集計する。
            //
            // 　@　@　@　@　@管理費（決済済分） += トランザクション.管理費
            //
            // 　@　@　@※決済約定がない場合は管理費（決済済分）は0となる 。
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblManagementFeeClose += l_finTransaction.getImportedManagementFee();
            }
        }

        // ４）　@（管理費（未決済分） + 管理費（決済済分））を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblManagementFeeOpen + l_dblManagementFeeClose;
    }

   /**
    * （get管理費消費税）<BR>
    * <BR>
    * 指定数量分の管理費消費税を計算する。<BR>
    * －（未決済分(*1)の管理費消費税＋決済済分(*2)の管理費消費税）を返す。<BR>
    * 　@　@-------------------------------<BR>
    * 　@　@(*1)建株の同名プロパティから按分計算により取得。<BR>
    * 　@　@(*2)決済約定により建株から減算された金額。決済のトランザクションより取得。<BR>
    * 　@　@-------------------------------<BR>
    * <BR>
    * １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、<BR>
    * 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。<BR>
    * <BR>
    * 　@　@　@this.get返済約定済数量(引数の注文単位ID)コールにより取得する。<BR>
    * <BR>
    * ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の管理費消費税を取得する。<BR>
    * <BR>
    * ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。<BR>
    * 　@　@　@　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。<BR>
    * <BR>
    * 　@　@　@----------------------------------------------------------<BR>
    * 　@　@　@＜calc諸経費按分比率( )：引数設定仕様＞<BR>
    * <BR>
    * 　@　@　@建株残数：　@this.建株数<BR>
    * 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。<BR>
    * 　@　@　@----------------------------------------------------------<BR>
    * <BR>
    * ２－２）　@未決済分の管理費消費税を計算する。<BR>
    * <BR>
    * 　@　@　@　@管理費消費税（未決済分） = this.管理費消費税 * 取得した按分比率（factor）<BR>
    * 　@　@　@　@※円未満切捨て。<BR>
    * <BR>
    * ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の管理費消費税を取得する。<BR>
    * <BR>
    * ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を<BR>
    * 　@　@　@　@　@取得する。 <BR>
    * 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。<BR>
    * <BR>
    * 　@　@　@----------------------------------------------------------<BR>
    * 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞<BR>
    * <BR>
    * 　@　@　@注文単位ID：　@引数の注文単位ID<BR>
    * 　@　@　@建株ID：　@this.建株ID<BR>
    * 　@　@　@----------------------------------------------------------<BR>
    * <BR>
    * ３－２）　@取得したトランザクションの全要素の管理費消費税を集計する。<BR>
    * <BR>
    * 　@　@　@　@　@管理費消費税（決済済分） += トランザクション.管理費消費税<BR>
    * <BR>
    * 　@　@　@※決済約定がない場合は管理費消費税（決済済分）は0となる 。<BR>
    * <BR>
    * ４）　@（管理費消費税（未決済分） + 管理費消費税（決済済分））を返却する。<BR>
    * <BR>
     * @@param l_dblQuantity - 注文数量<BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getManagementFeeTax(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getManagementFeeTax(double, long)";
        log.entering(STR_METHOD_NAME);

        // １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、
        // 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の管理費消費税を取得する。
        double l_dblManagementFeeTaxOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。
            //      　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@建株残数：　@this.建株数<BR>
            // 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。
            // 　@　@　@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // ２－２）　@未決済分の管理費消費税を計算する。
            //
            // 　@　@　@　@管理費消費税（未決済分） = this.管理費消費税 * 取得した按分比率（factor）
            // 　@　@　@　@※円未満切捨て。
            l_dblManagementFeeTaxOpen = Math.floor(this.getManagementFeeTax() * l_dblFactor);
        }
        // ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の管理費消費税を取得する。
        double l_dblManagementFeeTaxClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を
            // 　@　@　@　@　@取得する。 
            // 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@注文単位ID：　@引数の注文単位ID
            // 　@　@　@建株ID：　@this.建株ID
            // 　@　@　@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // ３－２）　@取得したトランザクションの全要素の管理費消費税を集計する。
            //
            // 　@　@　@　@　@管理費消費税（決済済分） += トランザクション.管理費消費税
            //
            // 　@　@　@※決済約定がない場合は管理費消費税（決済済分）は0となる 。
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblManagementFeeTaxClose += l_finTransaction.getImportedManagementFeeTax();
            }
        }

        // ４）　@（管理費消費税（未決済分） + 管理費消費税（決済済分））を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblManagementFeeTaxOpen + l_dblManagementFeeTaxClose;
    }

    /**
     * （get順日歩）<BR>
     * <BR>
     * 指定数量分の順日歩を計算する。<BR>
     * －（未決済分(*1)の順日歩＋決済済分(*2)の順日歩）を返す。<BR>
     * 　@　@-------------------------------<BR>
     * 　@　@(*1)建株の同名プロパティから按分計算により取得。<BR>
     * 　@　@(*2)決済約定により建株から減算された金額。決済のトランザクションより取得。<BR>
     * 　@　@-------------------------------<BR>
     * <BR>
     * １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、<BR>
     * 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。<BR>
     * <BR>
     * 　@　@　@this.get返済約定済数量(引数の注文単位ID)コールにより取得する。<BR>
     * <BR>
     * ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の順日歩を取得する。<BR>
     * <BR>
     * ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。<BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc諸経費按分比率( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@建株残数：　@this.建株数<BR>
     * 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２－２）　@未決済分の順日歩を計算する。<BR>
     * <BR>
     * 　@　@　@　@順日歩（未決済分） = this.順日歩 * 取得した按分比率（factor）<BR>
     * 　@　@　@　@※円未満切捨て。<BR>
     * <BR>
     * ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の順日歩を取得する。<BR>
     * <BR>
     * ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を<BR>
     * 　@　@　@　@　@取得する。 <BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@注文単位ID：　@引数の注文単位ID<BR>
     * 　@　@　@建株ID：　@this.建株ID<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ３－２）　@取得したトランザクションの全要素の順日歩を集計する。<BR>
     * <BR>
     * 　@　@　@　@　@順日歩（決済済分） += トランザクション.順日歩<BR>
     * <BR>
     * 　@　@　@※決済約定がない場合は順日歩（決済済分）は0となる 。<BR>
     * <BR>
     * ４）　@（順日歩（未決済分） + 順日歩（決済済分））を返却する。<BR>
     * <BR>
     * @@param l_dblQuantity - 注文数量<BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getInterestFee(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInterestFee(double, long)";
        log.entering(STR_METHOD_NAME);

        // １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、
        // 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の順日歩を取得する。
        double l_dblInterestFeeOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。
            //      　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@建株残数：　@this.建株数<BR>
            // 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。
            // 　@　@　@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // ２－２）　@未決済分の順日歩を計算する。
            //
            // 　@　@　@　@順日歩（未決済分） = this.順日歩 * 取得した按分比率（factor）
            // 　@　@　@　@※円未満切捨て。
            l_dblInterestFeeOpen = Math.floor(this.getInterestFee() * l_dblFactor);
        }
        // ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の順日歩を取得する。
        double l_dblInterestFeeClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を
            // 　@　@　@　@　@取得する。 
            // 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@注文単位ID：　@引数の注文単位ID
            // 　@　@　@建株ID：　@this.建株ID
            // 　@　@　@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // ３－２）　@取得したトランザクションの全要素の順日歩を集計する。
            //
            // 　@　@　@　@　@順日歩（決済済分） += トランザクション.順日歩
            //
            // 　@　@　@※決済約定がない場合は順日歩（決済済分）は0となる 。
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblInterestFeeClose += l_finTransaction.getImportedInterestFee();
            }
        }

        // ４）　@（順日歩（未決済分） + 順日歩（決済済分））を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblInterestFeeOpen + l_dblInterestFeeClose;
    }

    /**
     * （get逆日歩）<BR>
     * <BR>
     * 指定数量分の逆日歩を計算する。<BR>
     * －（未決済分(*1)の逆日歩＋決済済分(*2)の逆日歩）を返す。<BR>
     * 　@　@-------------------------------<BR>
     * 　@　@(*1)建株の同名プロパティから按分計算により取得。<BR>
     * 　@　@(*2)決済約定により建株から減算された金額。決済のトランザクションより取得。<BR>
     * 　@　@-------------------------------<BR>
     * <BR>
     * １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、<BR>
     * 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。<BR>
     * <BR>
     * 　@　@　@this.get返済約定済数量(引数の注文単位ID)コールにより取得する。<BR>
     * <BR>
     * ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の逆日歩を取得する。<BR>
     * <BR>
     * ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。<BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc諸経費按分比率( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@建株残数：　@this.建株数<BR>
     * 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２－２）　@未決済分の逆日歩を計算する。<BR>
     * <BR>
     * 　@　@　@　@逆日歩（未決済分） = this.逆日歩 * 取得した按分比率（factor）<BR>
     * 　@　@　@　@※円未満切捨て。<BR>
     * <BR>
     * ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の逆日歩を取得する。<BR>
     * <BR>
     * ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を<BR>
     * 　@　@　@　@　@取得する。 <BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@注文単位ID：　@引数の注文単位ID<BR>
     * 　@　@　@建株ID：　@this.建株ID<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ３－２）　@取得したトランザクションの全要素の逆日歩を集計する。<BR>
     * <BR>
     * 　@　@　@　@　@逆日歩（決済済分） += トランザクション.逆日歩<BR>
     * <BR>
     * 　@　@　@※決済約定がない場合は逆日歩（決済済分）は0となる 。<BR>
     * <BR>
     * ４）　@（逆日歩（未決済分） + 逆日歩（決済済分））を返却する。<BR>
     * <BR>
     * @@param l_dblQuantity - 注文数量<BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getPayInterestFee(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "PayInterestFee(double, long)";
        log.entering(STR_METHOD_NAME);

        // １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、
        // 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の逆日歩を取得する。
        double l_dblPayInterestFeeOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。
            //      　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@建株残数：　@this.建株数<BR>
            // 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。
            // 　@　@　@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // ２－２）　@未決済分の逆日歩を計算する。
            //
            // 　@　@　@　@逆日歩（未決済分） = this.逆日歩 * 取得した按分比率（factor）
            // 　@　@　@　@※円未満切捨て。
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) this.getDataSourceObject();
            l_dblPayInterestFeeOpen = Math.floor(l_eqtypeContractRow.getPayInterestFee() * l_dblFactor);
        }
        // ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の逆日歩を取得する。
        double l_dblPayInterestFeeClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を
            // 　@　@　@　@　@取得する。 
            // 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@注文単位ID：　@引数の注文単位ID
            // 　@　@　@建株ID：　@this.建株ID
            // 　@　@　@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // ３－２）　@取得したトランザクションの全要素の逆日歩を集計する。
            //
            // 　@　@　@　@　@逆日歩（決済済分） += トランザクション.逆日歩
            //
            // 　@　@　@※決済約定がない場合は逆日歩（決済済分）は0となる 。
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblPayInterestFeeClose += l_finTransaction.getImportedPayInterestFee();
            }
        }

        // ４）　@（逆日歩（未決済分） + 逆日歩（決済済分））を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblPayInterestFeeOpen + l_dblPayInterestFeeClose;
    }

    /**
     * （get貸株料）<BR>
     * <BR>
     * 指定数量分の貸株料を計算する。<BR>
     * －（未決済分(*1)の貸株料＋決済済分(*2)の貸株料）を返す。<BR>
     * 　@　@-------------------------------<BR>
     * 　@　@(*1)建株の同名プロパティから按分計算により取得。<BR>
     * 　@　@(*2)決済約定により建株から減算された金額。決済のトランザクションより取得。<BR>
     * 　@　@-------------------------------<BR>
     * <BR>
     * ０）　@this.isLong( )==true（買建）の場合は、0を返却する。<BR>
     *       this.isLong( )==false（売建）の場合は、以下の処理を行う。<BR>
     * <BR>
     * １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、<BR>
     * 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。<BR>
     * <BR>
     * 　@　@　@this.get返済約定済数量(引数の注文単位ID)コールにより取得する。<BR>
     * <BR>
     * ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の貸株料を取得する。<BR>
     * <BR>
     * ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。<BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc諸経費按分比率( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@建株残数：　@this.建株数<BR>
     * 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２－２）　@未決済分の貸株料を計算する。<BR>
     * <BR>
     * 　@　@　@　@貸株料（未決済分） = this.貸株料 * 取得した按分比率（factor）<BR>
     * 　@　@　@　@※円未満切捨て。<BR>
     * <BR>
     * ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の貸株料を取得する。<BR>
     * <BR>
     * ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を<BR>
     * 　@　@　@　@　@取得する。 <BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@注文単位ID：　@引数の注文単位ID<BR>
     * 　@　@　@建株ID：　@this.建株ID<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ３－２）　@取得したトランザクションの全要素の貸株料を集計する。<BR>
     * <BR>
     * 　@　@　@　@　@貸株料（決済済分） += トランザクション.貸株料<BR>
     * <BR>
     * 　@　@　@※決済約定がない場合は貸株料（決済済分）は0となる 。<BR>
     * <BR>
     * ４）　@（貸株料（未決済分） + 貸株料（決済済分））を返却する。<BR>
     * <BR>
     * @@param l_dblQuantity - 注文数量<BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getLoanEquityFee(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "LoanEquityFee(double, long)";
        log.entering(STR_METHOD_NAME);

        // ０）　@this.isLong( )==true（買建）の場合は、0を返却する。
        if (this.isLong())
        {
            return 0.0D;
        }

        // １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、
        // 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分の貸株料を取得する。
        double l_dblLoanEquityFeeOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。
            //      　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@建株残数：　@this.建株数<BR>
            // 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。
            // 　@　@　@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // ２－２）　@未決済分の貸株料を計算する。
            //
            // 　@　@　@　@貸株料（未決済分） = this.貸株料 * 取得した按分比率（factor）
            // 　@　@　@　@※円未満切捨て。
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) this.getDataSourceObject();
            l_dblLoanEquityFeeOpen = Math.floor(l_eqtypeContractRow.getLoanEquityFee() * l_dblFactor);
        }
        // ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分の貸株料を取得する。
        double l_dblLoanEquityFeeClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を
            // 　@　@　@　@　@取得する。 
            // 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@注文単位ID：　@引数の注文単位ID
            // 　@　@　@建株ID：　@this.建株ID
            // 　@　@　@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // ３－２）　@取得したトランザクションの全要素の貸株料を集計する。
            //
            // 　@　@　@　@　@貸株料（決済済分） += トランザクション.貸株料
            //
            // 　@　@　@※決済約定がない場合は貸株料（決済済分）は0となる 。
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblLoanEquityFeeClose += l_finTransaction.getImportedLoanEquityFee();
            }
        }

        // ４）　@（貸株料（未決済分） + 貸株料（決済済分））を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblLoanEquityFeeOpen + l_dblLoanEquityFeeClose;
    }

    /**
     * （getその他）<BR>
     * <BR>
     * 指定数量分のその他を計算する。<BR>
     * －（未決済分(*1)のその他＋決済済分(*2)のその他）を返す。<BR>
     * 　@　@-------------------------------<BR>
     * 　@　@(*1)建株の同名プロパティから按分計算により取得。<BR>
     * 　@　@(*2)決済約定により建株から減算された金額。決済のトランザクションより取得。<BR>
     * 　@　@-------------------------------<BR>
     * <BR>
     * １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、<BR>
     * 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。<BR>
     * <BR>
     * 　@　@　@this.get返済約定済数量(引数の注文単位ID)コールにより取得する。<BR>
     * <BR>
     * ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分のその他を取得する。<BR>
     * <BR>
     * ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。<BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc諸経費按分比率( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@建株残数：　@this.建株数<BR>
     * 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２－２）　@未決済分のその他を計算する。<BR>
     * <BR>
     * 　@　@　@　@その他（未決済分） = this.その他 * 取得した按分比率（factor）<BR>
     * 　@　@　@　@※円未満切捨て。<BR>
     * <BR>
     * ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分のその他を取得する。<BR>
     * <BR>
     * ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を<BR>
     * 　@　@　@　@　@取得する。 <BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@注文単位ID：　@引数の注文単位ID<BR>
     * 　@　@　@建株ID：　@this.建株ID<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ３－２）　@取得したトランザクションの全要素のその他を集計する。<BR>
     * <BR>
     * 　@　@　@　@　@その他（決済済分） += トランザクション.その他<BR>
     * <BR>
     * 　@　@　@※決済約定がない場合はその他（決済済分）は0となる 。<BR>
     * <BR>
     * ４）　@（その他（未決済分） + その他（決済済分））を返却する。<BR>
     * <BR>
     * @@param l_dblQuantity - 注文数量<BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double getOther(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "Other(double, long)";
        log.entering(STR_METHOD_NAME);

        // １）　@引数の注文単位ID != 0（＝注文既存在のため決済約定済分の考慮要）の場合、
        // 　@　@　@this.建株ID及び引数の注文単位IDに該当する分の、決済約定数量を取得する。
        double l_dblClosingExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = this.getClosingExecutedQuantity(l_lngOrderUnitId);
        }

        // ２）　@this.建株数 > 0（＝未決済分あり）の場合、未決済分のその他を取得する。
        double l_dblOtherOpen = 0.0D;
        if (this.getQuantity() > 0.0D)
        {
            // ２－１）　@建株の諸経費を株式顧客勘定明細に載せる際に使用する、按分比率（factor）を求める。
            //      　@　@※株式ポジションマネージャ.calc諸経費按分比率( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@建株残数：　@this.建株数<BR>
            // 　@　@　@決済約定数量：　@（引数の注文株数 - １）の決済約定数量）　@※未決済数量をセット。
            // 　@　@　@----------------------------------------------------------
            //
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            double l_dblFactor = l_positionManager.calcExpensesFactorRatio(
                this.getQuantity(),
                l_dblQuantity - l_dblClosingExecutedQuantity);
            // ２－２）　@未決済分のその他を計算する。
            //
            // 　@　@　@　@その他（未決済分） = this.その他 * 取得した按分比率（factor）
            // 　@　@　@　@※円未満切捨て。
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) this.getDataSourceObject();
            l_dblOtherOpen = Math.floor(l_eqtypeContractRow.getOther() * l_dblFactor);
        }
        // ３）　@１）の決済約定数量 > 0（＝約定あり）の場合、決済済分のその他を取得する。
        double l_dblOtherClose = 0.0D;
        if (l_dblClosingExecutedQuantity > 0.0D)
        {
            // ３－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を
            // 　@　@　@　@　@取得する。 
            // 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。
            //
            // 　@　@　@----------------------------------------------------------
            // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
            //
            // 　@　@　@注文単位ID：　@引数の注文単位ID
            // 　@　@　@建株ID：　@this.建株ID
            // 　@　@　@----------------------------------------------------------
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            List l_lstReturnList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());
            // ３－２）　@取得したトランザクションの全要素のその他を集計する。
            //
            // 　@　@　@　@　@その他（決済済分） += トランザクション.その他
            //
            // 　@　@　@※決済約定がない場合はその他（決済済分）は0となる 。
            int l_intRowSize = l_lstReturnList.size();
            for (int i = 0;i < l_intRowSize;i++)
            {
                EqtypeFinTransactionRow l_finTransaction = (EqtypeFinTransactionRow)l_lstReturnList.get(i);
                l_dblOtherClose += l_finTransaction.getImportedOther();
            }
        }

        // ４）　@（その他（未決済分） + その他（決済済分））を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblOtherOpen + l_dblOtherClose;
    }

    /**
     * (get管理費)<BR>
     * 指定注文の管理費を計算する。<BR>
     * －（未決済分(*1)の管理費＋決済済分(*2)の管理費）を返す。<BR>
     * 　@　@出来通知（約定／約定取消）から呼ばれる場合、<BR>
     * 　@　@未決済分に計上されるケースも決済済分に計上されるケースもあるので<BR>
     * 　@　@合算した値を返す仕様とする。<BR>
     * 　@　@-------------------------------<BR>
     * 　@　@(*1)建株の同名プロパティを取得。（按分しない）<BR>
     * 　@　@(*2)決済約定により建株から減算された金額。決済のトランザクションより取得。<BR>
     * 　@　@-------------------------------<BR>
     * <BR>
     * １）　@未決済分の管理費を取得する。<BR>
     * <BR>
     * 　@　@　@　@管理費（未決済分） = this.管理費<BR>
     * <BR>
     * ２）　@決済済分の管理費を取得する。<BR>
     * <BR>
     * ２－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を<BR>
     * 　@　@　@　@　@取得する。 <BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@注文単位ID：　@引数の注文単位ID<BR>
     * 　@　@　@建株ID：　@this.建株ID<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２－２）　@取得したトランザクションの全要素の管理費を集計する。<BR>
     * <BR>
     * 　@　@　@　@　@管理費（決済済分） += トランザクション.管理費<BR>
     * <BR>
     * 　@　@　@※トランザクション0件時は0とする 。<BR>
     * <BR>
     * ３）　@（管理費（未決済分） + 管理費（決済済分））を返却する。<BR>
     * <BR>
     * @@param l_lngOrderUnitId - 注文単位ID<BR>
     * 注文単位ID。<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getManagementFee(
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getManagementFee(long)";
        log.entering(STR_METHOD_NAME);

        //１）　@未決済分の管理費を取得する。
        //管理費（未決済分） = this.管理費
        double l_dblManagementFeeOpen = 0.0D;
        l_dblManagementFeeOpen = this.getManagementFee();

        //２）　@決済済分の管理費を取得する。
        //２－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を
        //           取得する。 
        //           ※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。
        //         ＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
        //          注文単位ID：　@引数の注文単位ID
        //          建株ID：　@this.建株ID
        double l_dblManagementFeeClose = 0.0D;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        List l_lisFinTransactionList =
            l_positionManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, this.getContractId());

        //２－２）　@取得したトランザクションの全要素の管理費を集計する。
        //           管理費（決済済分） += トランザクション.管理費
        //          ※トランザクション0件時は0とする 。
        int l_intRowSize = l_lisFinTransactionList.size();
        for (int i = 0; i < l_intRowSize; i++)
        {
            EqtypeFinTransactionRow l_finTransactionRow = (EqtypeFinTransactionRow)l_lisFinTransactionList.get(i);
            l_dblManagementFeeClose += l_finTransactionRow.getImportedManagementFee();
        }

        // ３）　@（管理費（未決済分） + 管理費（決済済分））を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblManagementFeeOpen + l_dblManagementFeeClose;
    }

    /**
     * (get管理費消費税)<BR>
     * 指定注文の管理費消費税を計算する。<BR>
     * －（未決済分(*1)の管理費消費税＋決済済分(*2)の管理費消費税）を返す。<BR>
     * 　@　@出来通知（約定／約定取消）から呼ばれる場合、<BR>
     * 　@　@未決済分に計上されるケースも決済済分に計上されるケースもあるので <BR>
     * 　@　@合算した値を返す仕様とする。<BR>
     * 　@　@-------------------------------<BR>
     * 　@　@(*1)建株の同名プロパティを取得。（按分しない）<BR>
     * 　@　@(*2)決済約定により建株から減算された金額。決済のトランザクションより取得。<BR>
     * 　@　@-------------------------------<BR>
     * <BR>
     * １）　@未決済分の管理費消費税を取得する。<BR>
     * <BR>
     * 　@　@　@　@管理費消費税（未決済分） = this.管理費消費税<BR>
     * <BR>
     * ２）　@決済済分の管理費消費税を取得する。<BR>
     * <BR>
     * ２－１）　@当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を<BR>
     * 　@　@　@　@　@取得する。<BR>
     * 　@　@　@　@　@※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@注文単位ID：　@引数の注文単位ID<BR>
     * 　@　@　@建株ID：　@this.建株ID<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２－２）　@取得したトランザクションの全要素の管理費消費税を集計する。<BR>
     * <BR>
     * 　@　@　@　@　@管理費消費税（決済済分） += トランザクション.管理費消費税<BR>
     * <BR>
     * 　@　@　@※トランザクション0件時は0とする 。<BR>
     * <BR>
     * ３）　@（管理費消費税（未決済分） + 管理費消費税（決済済分））を返却する。<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
     public double getManagementFeeTax(long l_lngOrderUnitId)
         throws WEB3BaseException
     {
         final String STR_METHOD_NAME = "getManagementFeeTax(long)";
         log.entering(STR_METHOD_NAME);

         //１）　@未決済分の管理費消費税を取得する。
         //管理費消費税（未決済分） = this.管理費消費税
         double l_dblManagementFeeTaxOpen = this.getManagementFeeTax();

         //２）　@決済済分の管理費消費税を取得する。
         //２－１）当該建株＋指定決済注文に紐付く決済のトランザクション（株式顧客勘定明細）の一覧を
         // 取得する。
         // ※株式ポジションマネージャ.get株式顧客勘定明細ListBy注文単位Plus建株( )より取得する。
         // 　@　@　@----------------------------------------------------------
         // 　@　@　@＜get株式顧客勘定明細ListBy注文単位Plus建株( )：引数設定仕様＞
         //
         // 　@　@　@注文単位ID：　@引数の注文単位ID
         // 　@　@　@建株ID：　@this.建株ID
         // 　@　@　@----------------------------------------------------------

         FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
         TradingModule l_tradingModule =
             l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

         WEB3EquityPositionManager l_positionManager =
             (WEB3EquityPositionManager)l_tradingModule.getPositionManager();

         List l_lisFinTransactionList = l_positionManager.getFinTransactionListByOrderUnitPlusContract(
             l_lngOrderUnitId,
             this.getContractId());

         // ３－２）　@取得したトランザクションの全要素の管理費消費税を集計する。
         //管理費消費税（決済済分） += トランザクション.管理費消費税
         //※決済約定がない場合は管理費消費税（決済済分）は0となる 。
         double l_dblManagementFeeTaxClose = 0.0D;
         int l_intRowSize = l_lisFinTransactionList.size();
         for (int i = 0; i < l_intRowSize; i++)
         {
             EqtypeFinTransactionRow l_eqtypeFinTransactionRow = (EqtypeFinTransactionRow)l_lisFinTransactionList.get(i);
             l_dblManagementFeeTaxClose += l_eqtypeFinTransactionRow.getImportedManagementFeeTax();
         }

         // ４）　@（管理費消費税（未決済分） + 管理費消費税（決済済分））を返却する。
         log.exiting(STR_METHOD_NAME);
         return l_dblManagementFeeTaxOpen + l_dblManagementFeeTaxClose;
     } 
}
@
