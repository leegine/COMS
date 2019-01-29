head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositPersistentDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 証拠金計算データソースアクセス管理クラス(WEB3IfoDepositPersistentDataManager.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/19 山田　@卓司(FLJ) 新規作成
 Revesion History : 2007/06/28 hijikata(SRA) 夕場対応 モデルNo.073
 */
package webbroker3.ifodeposit;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.IfoDepositDao;
import webbroker3.gentrade.data.IfoDepositParams;
import webbroker3.gentrade.data.ProcessManagementDao;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.tradingpower.data.TpCashBalanceDao;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (証拠金計算データソースアクセス管理)
 * 証拠金計算に用いるデータソースへのアクセスを管理するクラス。
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositPersistentDataManager
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepositPersistentDataManager.class);

    /**
     * (get建玉Params一覧)<BR>
     * 
     * （staticメソッド）<BR>
     * 建玉Paramsの一覧を取得する。<BR>
     * （新規建約定取消により取消されたレコードは除く)<BR>
     * 
     * 建玉テーブル(ifo_contract)を引数の条件で検索した結果を返却する。<BR>
     * ※該当レコードが存在しない場合には、nullを返却する。<BR>
     * 
     * [検索条件]<BR>
     * 建玉テーブル.口座ID　@==　@引数.口座ID<BR>
     * 建玉テーブル.補助口座ID　@==　@引数.補助口座ID<BR>
     * 建玉テーブル.建玉元数量　@!=　@0<BR>
     * @@param l_lngAccountId - (口座ID)
     * @@param l_lngSubAccountId - (補助口座ID)。
     * @@return IfoContractParams[]
     * @@roseuid 411234A70109
     */
    public static IfoContractParams[] getContractParamsList(
        long l_lngAccountId,
        long l_lngSubAccountId)
    {

        IfoContractParams[] l_results = null;
        String l_strWhere = "account_id=? and sub_account_id=? and original_quantity<>?";
        Object[] l_objBindVars =
            { new Long(l_lngAccountId), new Long(l_lngSubAccountId), new Long(0)};
        try
        {

            log.debug(
                "Finding IfoContractRows where="
                    + l_strWhere
                    + ", bindVars="
                    + objectsToString(l_objBindVars));

            List l_list =
                Processors.getDefaultProcessor().doFindAllQuery(
                    IfoContractRow.TYPE,
                    l_strWhere,
                    l_objBindVars);
            if (l_list != null && l_list.size() > 0)
            {
                l_results = new IfoContractParams[l_list.size()];
                for (int i = 0; i < l_list.size(); i++)
                {
                    l_results[i] = (IfoContractParams)l_list.get(i);
                }
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getContractParamsList(long, long)",
                de);
        }

        return l_results;

    }

    /**
     * (getトランザクションParams一覧)。<BR>
     * <BR>
     * （staticメソッド）<BR>
     * トランザクション（取引勘定明細）Paramsの一覧を取得する。 <BR>
     * トランザクション（取引勘定明細）テーブル(ifo_fin_transaction)を引数の条件で検索した結果を返却する。 <BR>
     * ※該当レコードが存在しない場合には、nullを返却する。 <BR>
     * <BR>
     * [検索条件]<BR> 
     * トランザクション（取引勘定明細）.口座ID　@==　@引数.口座ID<BR> 
     * トランザクション（取引勘定明細）.補助口座ID　@==　@引数.補助口座ID<BR> 
     * トランザクション（取引勘定明細）.トランザクション発生日付(yyyymmdd) == 引数.発生日(yyyymmdd)<BR> 
     * トランザクション（取引勘定明細）.削除フラグ == false <BR>
     * <BR>
     * ※トランザクション発生日付については日付部分まで(ｙｙｙｙｍｍｄｄ)が一致するデータを取得する。<BR>
     * <BR>
     * @@param l_lngAccountId - (口座ID)。
     * @@param l_lngSubAccountId - (補助口座ID)。
     * @@param l_datBizDate - (発生日)<BR>
     * @@return IfoFinTransactionParams[]
     */
    public static IfoFinTransactionParams[] getFinTransactionParamsList(
        long l_lngAccountId,
        long l_lngSubAccountId,
        Date l_datBizDate)
    {

        IfoFinTransactionParams[] l_results = null;
        String l_strWhere =
            "account_id=? and sub_account_id=? and TRUNC(fin_transaction_timestamp) = ? AND delete_flag=?";
        Object[] l_objBindVars =
            new Object[] {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                l_datBizDate,
                BooleanEnum.FALSE };

        try
        {
            log.debug(
                "Finding IfoFinTransactionRows where="
                    + l_strWhere
                    + ", bindVars="
                    + objectsToString(l_objBindVars));

            List l_list =
                Processors.getDefaultProcessor().doFindAllQuery(
                    IfoFinTransactionRow.TYPE,
                    l_strWhere,
                    l_objBindVars);

            if (l_list != null && l_list.size() > 0)
            {
                l_results = new IfoFinTransactionParams[l_list.size()];
                for (int i = 0; i < l_list.size(); i++)
                {
                    l_results[i] = (IfoFinTransactionParams)l_list.get(i);
                }
            }

        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getFinTransactionParamsList(long, long)",
                de);
        }

        return l_results;

    }

    /**
     * (get当日新規建注文単位Params一覧)<BR>
     * 
     * （staticメソッド）<BR>
     * 当日の新規建の注文単位レコードの一覧を取得する。<BR>
     * 
     * 注文単位テーブル(ifo_order_unit)を引数の条件で検索した結果を返却する。<BR>
     * ※該当レコードが存在しない場合には、nullを返却する。<BR>
     * 
     * [検索条件]<BR>
     * 注文単位テーブル.口座ID　@==　@引数.口座ID<BR>
     * 注文単位テーブル.補助口座ID　@==　@引数.補助口座ID<BR>
     * 注文単位テーブル.注文カテゴリ in (”先物新規建注文”、”OP新規建注文”)<BR>
     * 注文単位テーブル.注文有効状態 == ”オープン”<BR>
     * 注文単位テーブル.発注日　@>=　@引数.発注日<BR>
     * @@param l_lngAccountId - (口座ID)
     * @@param l_lngSubAccountId - (補助口座ID)
     * @@param l_datBizDate - (発注日)<BR>
     * 
     * 営業日[T+0]を指定する。<BR>
     * @@return IfoOrderUnitParams[]
     * @@roseuid 4112370E00A3
     */
    public static IfoOrderUnitParams[] getTodayOpenOrderUnitParamsList(
        long l_lngAccountId,
        long l_lngSubAccountId,
        Date l_datBizDate)
    {

        IfoOrderUnitParams[] l_results = null;
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        String l_strWhere =
            "account_id=? and sub_account_id=? and order_categ in (?,?) and order_open_status=? and biz_date>=?";
        Object[] l_objBindVars =
            new Object[] {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                OrderCategEnum.IDX_FUTURES_OPEN,
                OrderCategEnum.IDX_OPTIONS_OPEN,
                OrderOpenStatusEnum.OPEN,
                l_strBizDate };

        try
        {

            log.debug(
                "Finding IfoDepositOrderUnitRows. where="
                    + l_strWhere
                    + ", bindVars="
                    + objectsToString(l_objBindVars));

            List l_list =
                Processors.getDefaultProcessor().doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objBindVars);
            if (l_list != null && l_list.size() > 0)
            {
                l_results = new IfoOrderUnitParams[l_list.size()];
                for (int i = 0; i < l_list.size(); i++)
                {
                    l_results[i] = (IfoOrderUnitParams)l_list.get(i);
                }
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getTodayOpenOrderUnitParamsList(long, long, Date)",
                de);
        }

        return l_results;

    }

    /**
     * （get当日振替注文単位Params一覧）<BR>
     * 
     * （staticメソッド）<BR>
     * 当日に振替注文をした入出金の注文単位Paramsの一覧を取得する。<BR>
     * 
     * 
     * 入出金の注文単位テーブル(aio_order_unit)を引数の条件で検索した結果を返却する。<B
     * R>
     * ※該当レコードが存在しない場合には、nullを返却する。<BR>
     * 
     * [検索条件]<BR>
     * 注文単位テーブル.口座ID　@==　@引数.口座ID<BR>
     * 注文単位テーブル.補助口座ID　@==　@引数.補助口座ID<BR>
     * 注文単位テーブル.注文種別 in 
     * （”振替注文（預り金から株先証拠金”、”振替注文（株先証拠金から預り金）”）<BR>
     * 注文単位テーブル.注文状態 in 
     * (”受付済(新規注文)”、”発注中(新規注文)”、”発注済(新規注文)”)<BR>
     * 注文単位テーブル.発注日　@>=　@引数.発注日<BR>
     * @@param l_lngAccountId - (口座ID)
     * @@param l_lngSubAccountId - (補助口座ID)
     * @@param l_datBizDate - (発注日)<BR>
     * 
     * 営業日[T+0]を指定する。<BR>
     * @@return AioOrderUnitParams[]
     * @@roseuid 4112DDAB01C5
     */
    public static AioOrderUnitParams[] getTodayAioOrderUnitParamsList(
        long l_lngAccountId,
        long l_lngSubAccountId,
        Date l_datBizDate)
    {
        AioOrderUnitParams[] l_results = null;
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        String l_strWhere =
            "account_id=? and sub_account_id=? and order_type in (?,?) and order_status in (?,?,?) and biz_date>=?";
        Object[] l_objBindVars =
            new Object[] {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,
                OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT,
                OrderStatusEnum.ACCEPTED,
                OrderStatusEnum.ORDERING,
                OrderStatusEnum.ORDERED,
                l_strBizDate };
        try
        {
            log.debug(
                "Finding AioOrderUnitParams. where="
                    + l_strWhere
                    + ",bindVars="
                    + objectsToString(l_objBindVars));
            List l_list =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objBindVars);
            if (l_list != null && l_list.size() > 0)
            {
                l_results = new AioOrderUnitParams[l_list.size()];
                for (int i = 0; i < l_list.size(); i++)
                {
                    l_results[i] = (AioOrderUnitParams)l_list.get(i);
                }
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getTodayAioOrderUnitParamsList(long, long, Date)",
                de);
        }

        return l_results;
    }

    /**
     * (get銘柄Params)<BR>
     * 
     * （staticメソッド）<BR>
     * 引数で指定された条件に一致する先物OP銘柄Paramsを取得する。<BR>
     * 
     * 先物OP銘柄テーブル(ifo_product)を引数の条件で検索した結果を返却する。<BR>
     * 
     * [検索条件]<BR>
     * 先物OP銘柄テーブル.銘柄ID == 引数.銘柄ID<BR>
     * @@param l_lngProductId - (銘柄ＩＤ)
     * @@return IfoProductParams
     * @@roseuid 41255D0D0198
     */
    public static IfoProductParams getProductParams(long l_lngProductId)
    {
        try
        {
            return (IfoProductParams)IfoProductDao.findRowByProductId(l_lngProductId);
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getProductParams(long)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getProductParams(long)",
                de);
        }
    }

    /**
     * (get取引銘柄Params)<BR>
     * 
     * （staticメソッド）<BR>
     * 引数で指定された条件に一致する先物OP取引銘柄Paramsを取得する。<BR>
     * 
     * 先物OP取引銘柄マスタ(ifo_traded_product)を引数の条件で検索した結果を返却する。<B
     * R>
     * 
     * [検索条件]<BR>
     * 先物OP取引銘柄マスタ.銘柄ID == 引数.銘柄ID<BR>
     * 先物OP取引銘柄マスタ.市場ID == 引数.市場ID<BR>
     * @@param l_lngProductId - (銘柄ＩＤ)
     * @@param l_lngMarketId - (市場ID)
     * @@return IfoTradedProductParams
     * @@roseuid 41255F3F03CA
     */
    public static IfoTradedProductParams getTradedProductParams(
        long l_lngProductId,
        long l_lngMarketId)
    {
        try
        {
            return (IfoTradedProductParams)IfoTradedProductDao.findRowByProductIdMarketId(
                l_lngProductId,
                l_lngMarketId);
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getTradedProductParams(long, long)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getTradedProductParams(long, long)",
                de);
        }
    }

    /**
     * (get取引銘柄Params)<BR>
     * （staticメソッド）<BR>
     * 引数で指定された条件に一致する先物OP取引銘柄Paramsを取得する。(有効日指定)<BR>
     * 
     * 先物OP取引銘柄マスタ(ifo_traded_product)を引数の条件で検索した結果を返却する。<B
     * R>
     * 
     * [検索条件]<BR>
     * 先物OP取引銘柄マスタ.銘柄ID == 引数.銘柄ID<BR>
     * 先物OP取引銘柄マスタ.市場ID == 引数.市場ID<BR>
     * 先物OP取引銘柄マスタ.有効日 == 引数.有効日<BR>
     * @@param l_lngProductId - (銘柄ＩＤ)
     * @@param l_lngMarketId - (市場ID)
     * @@param l_datValidDate - (有効日)
     * @@return IfoTradedProductParams
     * @@roseuid 4125601D031E
     */
    public static IfoTradedProductParams getTradedProductParams(
        long l_lngProductId,
        long l_lngMarketId,
        Date l_datValidDate)
    {
        IfoTradedProductParams l_tp = getTradedProductParams(l_lngProductId, l_lngMarketId);
        String l_strValidDate =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd").format(l_datValidDate);
        if (l_tp != null && l_strValidDate.equals(l_tp.getValidForBizDate()))
        {
            return l_tp;
        }
        else
        {
            return null;
        }
    }

    /**
     * (get取引銘柄一時Params)<BR>
     * 
     * （staticメソッド）<BR>
     * 引数で指定された条件に一致する先物OP取引銘柄一時Paramsを取得する。<BR>
     * 
     * 先物OP取引銘柄マスタ(一時テーブル)(ifo_traded_product_updq)を引数の条件で検索し?
     * 結果を返却する。<BR>
     * 
     * [検索条件]<BR>
     * 先物OP取引銘柄マスタ(一時テーブル).銘柄ID == 引数.銘柄ID<BR>
     * 先物OP取引銘柄マスタ(一時テーブル).市場ID == 引数.市場ID<BR>
     * 先物OP取引銘柄マスタ(一時テーブル).証券会社コード == 引数.証券会社コード<BR>
     * 先物OP取引銘柄マスタ(一時テーブル).有効日 == 引数.有効日
     * @@param l_lngProductId - (銘柄ＩＤ)
     * @@param l_lngMarketId - (市場ID)
     * @@param l_stInstitutionCode - (証券会社コード)
     * @@param l_datValidDate - (有効日)
     * @@return IfoTradedProductUpdqParams
     * @@roseuid 4125610802B1
     */
    public static IfoTradedProductUpdqParams getTradedProductUpdqParams(
        long l_lngProductId,
        long l_lngMarketId,
        String l_strInstitutionCode,
        Date l_datValidDate)
    {
        String l_strValidForBizDate =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd").format(l_datValidDate);
        try
        {
            return (
                IfoTradedProductUpdqParams)IfoTradedProductUpdqDao
                    .findRowByValidForBizDateInstitutionCodeMarketIdProductId(
                l_strValidForBizDate,
                l_strInstitutionCode,
                l_lngMarketId,
                l_lngProductId);
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getTradedProductUpdqParams(long, long, String Date)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getTradedProductUpdqParams(long, long, String Date)",
                de);
        }
    }

    /**
     * (get余力条件Params)<BR>
     * 
     * （staticメソッド）<BR>
     * 引数で指定された条件に一致する余力条件Paramsを取得する。<BR>
     * 
     * 余力条件テーブル(tradingpower_calc_condition)を引数の条件で検索した結果を返却す?
     * 
     * 
     * 。<BR>
     * 
     * [検索条件]<BR>
     * 余力条件テーブル.口座ID == 引数.口座ID<BR>
     * @@param l_lngAccountId - (口座ID)
     * @@return webbroker3.gentrade.data.TradingpowerCalcConditionParams
     * @@roseuid 4132E4790376
     */
    public static TradingpowerCalcConditionParams getTradingpowerCalcConditionParams(long l_lngAccountId)
    {
        try
        {
            log.debug("Finding TradingpowerCalcConditionRow by account_id=" + l_lngAccountId);
            return (
                TradingpowerCalcConditionParams)TradingpowerCalcConditionDao.findRowByAccountId(
                l_lngAccountId);
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getTradingPowerCalcConditionParams(long)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getTradingPowerCalcConditionParams(long)",
                de);
        }
    }

    /**
     * (get証拠金Params)<BR>
     * 
     * （staticメソッド）<BR>
     * 引数で指定された条件に一致する証拠金Paramsを取得する。<BR>
     * ※該当レコードが存在しない場合には、nullを返却する。<BR>
     * 
     * 証拠金テーブル(ifo_deposit)を引数の条件で検索した結果を返却する。<BR>
     * 
     * [検索条件]<BR>
     * 証拠金テーブル.証券会社コード == 引数.証券会社コード<BR>
     * 証拠金テーブル.部店コード == 引数.部店コード<BR>
     * 証拠金テーブル.顧客コード == 引数.顧客コード<BR>
     * 証拠金テーブル.計算日 == 引数.計算日<BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strAccountCode - 顧客コード。
     * @@param l_datEstimateDate - 計算日。
     * @@return webbroker3.gentrade.data.IfoDepositParams
     * @@roseuid 4132E52201C0
     */
    public static IfoDepositParams getIfoDepositParams(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        Date l_datEstimateDate)
    {

        Timestamp l_datCalcDate = null;
        if (l_datEstimateDate instanceof Timestamp)
        {
            l_datCalcDate = (Timestamp)l_datEstimateDate;
        }
        else
        {
            l_datCalcDate = new Timestamp(l_datEstimateDate.getTime());
        }

        try
        {
            return (
                IfoDepositParams)IfoDepositDao
                    .findRowByInstitutionCodeBranchCodeAccountCodeCalcDate(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_datCalcDate);
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getIfoDepositParams(String, String, String, Date)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getIfoDepositParams(String, String, String, Date)",
                de);
        }
    }

    /**
     * (get顧客勘定残高マスタ情報Params)
     * 
     * （staticメソッド）
     * 引数で指定された条件に一致する顧客勘定残高マスタ情報Paramsを取得する。
     * 
     * 顧客勘定残高(マスタ情報)テーブル(tp_cash_balance)を引数の条件で検索した結果を返却する。
     * 
     * [検索条件]
     * 顧客勘定残高(マスタ情報)テーブル.口座ID == 引数.口座ID
     * 顧客勘定残高(マスタ情報)テーブル.補助口座ID == 引数.補助口座ID
     * 
     * @@param l_lngAccountId 口座ID
     * @@param l_lngSubAccountId 補助口座ID
     * @@return 顧客勘定残高マスタ情報Params
     */
    public static TpCashBalanceParams getTpCashBalanceParams(
        long l_lngAccountId,
        long l_lngSubAccountId)
    {
        try
        {
            return (TpCashBalanceParams)TpCashBalanceDao.findRowByAccountIdSubAccountId(
                l_lngAccountId,
                l_lngSubAccountId);
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getTpCashBalanceParams(long, long)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getTpCashBalanceParams(long, long)",
                de);
        }
    }

    /**
     * (getプロセス管理Params)
     * （staticメソッド）
     * 引数で指定された条件に一致するプロセス管理Paramsを取得する。
     * 
     * プロセス管理テーブル(process_management)を引数の条件で検索した結果を返却する。
     * ※該当レコードが存在しない場合には、nullを返却する。
     * [検索条件]
     * プロセス管理テーブル.プロセスID == 引数.プロセスID
     * プロセス管理テーブル.会社コード == 引数.証券会社コード
     * プロセス管理テーブル.部店コード == 引数.部店コード
     * 
     * @@param l_strProcessId プロセスID
     * @@param l_strInstCode 証券会社コード
     * @@param l_strBranCode 部店コード
     * @@return プロセス管理Params
     */
    public static ProcessManagementParams getProcessManagementParams(
        String l_strProcessId,
        String l_strInstCode,
        String l_strBranCode)
    {
        try
        {
            return (
                ProcessManagementParams)ProcessManagementDao
                    .findRowByProcessIdInstitutionCodeBranchCode(
                l_strProcessId,
                l_strInstCode,
                l_strBranCode);
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getTpCashBalanceParams(long, long)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getTpCashBalanceParams(long, long)",
                de);
        }
    }
    
    /**
     * (get部店用プリファ@レンスParams一覧)
     * 
     * （staticメソッド）
     * 引数で指定された条件に一致する部店用プリファ@レンスParamsの一覧を取得する。 
     * 
     * 部店用プリファ@レンステーブル(branch_preferences)を引数の条件で検索した結果を返却する。 
     * ※該当レコードが存在しない場合には、nullを返却する。 
     * 
     * [検索条件]
     * 部店用プリファ@レンステーブル.部店ID == 引数.部店ID
     * 
     * @@param l_lngBranchId 部店ID
     * @@return 部店用プリファ@レンスParams
     */
    public static BranchPreferencesParams[] getBranchPreferencesParamsList(
        long l_lngBranchId)
    {
    
        BranchPreferencesParams[] l_results = null;
        try
        {
            List l_list = BranchPreferencesDao.findRowsByBranchId(l_lngBranchId);
            
            if (l_list != null && l_list.size() > 0)
            {
                l_results = new BranchPreferencesParams[l_list.size()];
                for (int i = 0; i < l_list.size(); i++)
                {
                    l_results[i] = (BranchPreferencesParams)l_list.get(i);
                }
            }
            
            return l_results;
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getBranchPreferencesParamsList(long)",
                de);
        }
    }
    
    /**
     * (get注文単位Params)
     * 
     * （staticメソッド）
     * 該当の注文単位レコードを取得する。 
     * 
     * 注文単位テーブル(ifo_order_unit)を引数の条件で検索した結果を返却する。
     * ※該当レコードが存在しない場合には、nullを返却する。 
     * 
     * [検索条件]
     * 注文単位テーブル.注文単位ID　@==　@引数.注文単位ID
     * 
     * @@param l_lngOrderUnitId   注文単位ID
     * @@return Ifo注文単位Params
     */
    public static IfoOrderUnitParams getIfoOrderUnitParams(
        long l_lngOrderUnitId)
    {
    
        try
        {
            return (IfoOrderUnitParams) IfoOrderUnitDao.findRowByOrderUnitId(
            l_lngOrderUnitId);

        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getIfoOrderUnitParams(long)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getIfoOrderUnitParams(long)",
                de);
        }
    }
    
    private static String objectsToString(Object[] l_objBindVars)
    {

        StringBuffer l_sb = new StringBuffer();
        if (l_objBindVars != null)
        {
            for (int i = 0; i < l_objBindVars.length; i++)
            {
                if (i > 0)
                {
                    l_sb.append(",");
                }
                l_sb.append("[").append(i).append("]=");
                l_sb.append(String.valueOf(l_objBindVars[i]));
            }
        }
        return l_sb.toString();
    }

}
@
