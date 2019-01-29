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
filename	WEB3IfoFrontOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP発注サービスImpl(WEB3IfoFrontOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/25 徐宏偉 (中訊) 新規作成 モデルNo.587
Revision History : 2007/03/27 徐宏偉 (中訊) 仕様変更 モデルNo.633
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3ChangeCancelEnableFlag;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3FrontOrderSystemCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP発注サービスImpl)<BR>
 * 先物OP発注サービス実装クラス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3IfoFrontOrderServiceImpl implements WEB3IfoFrontOrderService
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoFrontOrderServiceImpl.class);

    /**
     * (getフロント発注システム区分)<BR>
     * 引数の市場コードにより、フロント発注システム区分を取得し返却する。<BR>
     * <BR>
     * １）　@引数の市場コード=="東証"の場合は、<BR>
     * 　@　@"東証、JASDAQオークション、名証"を返却する。<BR>
     * <BR>
     * ２）　@引数の市場コード=="大証"の場合は、"大証"を返却する。<BR>
     * <BR>
     * <BR>
     * ３）　@引数の市場コードが上記いずれでもない場合は、"その他"を返却する。<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * WEBⅢの市場コード<BR>
     * @@return String
     */
    public String getFrontOrderSystemCode(String l_strMarketCode)
    {
        final String STR_METHOD_NAME = "getFrontOrderSystemCode(String)";
        log.entering(STR_METHOD_NAME);

        //  １）　@引数の市場コード=="東証"の場合は、
        //  　@　@"東証、JASDAQオークション、名証"を返却する。
        String l_strFrontOrderSystemCode = null;
        if (WEB3MarketCodeDef.TOKYO.equals(l_strMarketCode))
        {
            l_strFrontOrderSystemCode = WEB3FrontOrderSystemCodeDef.TOKYO_STOCK_JASDAQ_AUCTION;
        }
        //  ２）　@引数の市場コード=="大証"の場合は、"大証"を返却する。
        else if (WEB3MarketCodeDef.OSAKA.equals(l_strMarketCode))
        {
            l_strFrontOrderSystemCode = WEB3FrontOrderSystemCodeDef.OSAKA_EXCHANGE_STOCK;
        }
        //  ３）　@引数の市場コードが上記いずれでもない場合は、"その他"を返却する。
        else
        {
            l_strFrontOrderSystemCode = WEB3FrontOrderSystemCodeDef.OTHERS;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strFrontOrderSystemCode;
    }

    /**
     * (get発注経路区分)<BR>
     * 発注可能な発注経路区分を取得し返却する。<BR>
     * <BR>
     * １）　@this.getフロント発注システム区分()により、<BR>
     * 　@　@フロント発注システム区分を取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜getフロント発注システム区分()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@市場コード：　@引数の市場コード<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２）　@発注先切替.get有効発注先切替()により、有効な発注先切替オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@------------------------------------------------<BR>
     * 　@　@　@＜発注先切替.get有効発注先切替()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@証券会社コード：　@引数の証券会社コード<BR>
     * 　@　@　@銘柄タイプ：　@"先物オプション"<BR>
     * 　@　@　@市場コード：　@引数の市場コード<BR>
     * 　@　@　@フロント発注システム区分：　@１）で求めたフロント発注システム区分<BR>
     * 　@　@　@------------------------------------------------<BR>
     * <BR>
     * ３）　@２）で戻り値!=nullの場合は、戻り値.発注経路区分を返却する。<BR>
     * 　@　@　@２）で戻り値==nullの場合は、「発注経路切替対象なし」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02216<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * WEBⅢの市場コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getSubmitOrderRouteDiv(
        String l_strInstitutionCode,
        String l_strMarketCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubmitOrderRouteDiv(String, String)";
        log.entering(STR_METHOD_NAME);

        //    １）　@this.getフロント発注システム区分()により、
        //  　@　@フロント発注システム区分を取得する。
        //  　@　@　@＜getフロント発注システム区分()：引数設定仕様＞
        //  　@　@　@市場コード：　@引数の市場コード
        String l_strFrontOrderSystemCode = this.getFrontOrderSystemCode(l_strMarketCode);

        //  ２）　@発注先切替.get有効発注先切替()により、有効な発注先切替オブジェクトを取得する。
        //  　@　@　@＜発注先切替.get有効発注先切替()：引数設定仕様＞
        //  　@　@　@証券会社コード：　@引数の証券会社コード
        //  　@　@　@銘柄タイプ：　@"先物オプション"
        //  　@　@　@市場コード：　@引数の市場コード
        //  　@　@　@フロント発注システム区分：　@１）で求めたフロント発注システム区分
        WEB3GentradeOrderSwitching l_orderSwitching =
            WEB3GentradeOrderSwitching.getOnOrderSwitching(
                l_strInstitutionCode,
                ProductTypeEnum.IFO,
                l_strMarketCode,
                l_strFrontOrderSystemCode);

        //  ３）　@２）で戻り値!=nullの場合は、戻り値.発注経路区分を返却する。
        //  　@　@　@２）で戻り値==nullの場合は、「発注経路切替対象なし」の例外をthrowする。
        if (l_orderSwitching != null)
        {
            OrderSwitchingRow l_orderSwitchingRow =
                (OrderSwitchingRow)l_orderSwitching.getDataSourceObject();

            log.exiting(STR_METHOD_NAME);
            return l_orderSwitchingRow.getSubmitOrderRouteDiv();
        }
        else
        {
            log.debug("発注経路切替対象なし");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02216,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券会社コード:" + l_strInstitutionCode
                + " 市場コード:" + l_strMarketCode
                + " フロント発注システム区分:" + l_strFrontOrderSystemCode);
        }
    }

    /**
     * (get注文Rev開始位置IN社内処理項目)<BR>
     * 社内処理項目の文字列中の、注文Rev.開始位置を返す。<BR>
     * <BR>
     * 開始位置として、16を返す。<BR>
     * <BR>
     * 社内処理項目のコード体系：<BR>
     * 証券会社ID＋部店コード＋銘柄タイプ＋識別コード＋注文Rev.＋"999"<BR>
     * <BR>
     * @@return int
     */
    public int getIndexOfOrderRevInCorpCode()
    {
        final String STR_METHOD_NAME = "getIndexOfOrderRevInCorpCode()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return 16;
    }

    /**
     * (get注文Rev桁数)<BR>
     * 注文Rev.の桁数を返す。<BR>
     * <BR>
     * 桁数として、2を返す。<BR>
     * <BR>
     * @@return int
     */
    public int getFigureOfOrderRev()
    {
        final String STR_METHOD_NAME = "getFigureOfOrderRev()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return 2;
    }

    /**
     * (get社内処理項目)<BR>
     * 引数の注文単位オブジェクトより、発注に使用する「社内処理項目」設定用文字列を<BR>
     * 取得し返却する。<BR>
     * <BR>
     * １）　@部店オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@引数の注文単位.getBranch( )にて取得する。<BR>
     * <BR>
     * ２）　@以下の文字列を返却する。<BR>
     * <BR>
     * 　@　@証券会社ID＋部店コード＋銘柄タイプ＋識別コード＋注文Rev.＋"999"<BR>
     * <BR>
     * 　@　@部店オブジェクトから取得：　@証券会社ID、部店コード<BR>
     * 　@　@引数の注文単位オブジェクトから取得：　@銘柄タイプ、識別コード、注文Rev.<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getCorpCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCorpCode(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        // １）　@部店オブジェクトを取得する。
        // 　@　@　@引数の注文単位.getBranch()にて取得する。
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        BranchRow l_branchRow = null;
        try
        {
            Branch l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        // ２）　@以下の文字列を返却する。
        // 　@　@証券会社ID＋部店コード＋銘柄タイプ＋識別コード＋注文Rev.＋"999"
        // 　@　@部店オブジェクトから取得：　@証券会社ID、部店コード
        // 　@　@引数の注文単位オブジェクトから取得：　@銘柄タイプ、識別コード、注文Rev.
        StringBuffer l_strCorpCode = new StringBuffer();
        l_strCorpCode.append(Long.toString(l_branchRow.getInstitutionId()));
        l_strCorpCode.append(l_branchRow.getBranchCode());
        l_strCorpCode.append(Integer.toString(l_orderUnitRow.getProductType().intValue()));
        l_strCorpCode.append(l_orderUnitRow.getOrderRequestNumber());
        l_strCorpCode.append(l_orderUnitRow.getOrderRev());
        l_strCorpCode.append("999");

        log.exiting(STR_METHOD_NAME);
        return l_strCorpCode.toString();
    }

    /**
     * (is市場通知中注文IN休憩時間帯)<BR>
     * 取引所が休憩時間中の時間帯において、<BR>
     * 指定の注文に関係するデータ（訂正、取消を含む）が市場に通知中であるかどうかを<BR>
     * 先物OP注文取引キューテーブルのデータより判定し返す。<BR>
     * <BR>
     * １）　@以下の条件のいずれかに合致する場合はfalseを返却する。<BR>
     * <BR>
     * 　@　@　@取引時間管理.is市場開局時間帯()==falseの場合（＝引け後／非営業日）<BR>
     * 　@　@　@取引時間管理.is取引所休憩時間帯()==falseの場合（＝場中で取引所は取引中）<BR>
     * <BR>
     * 　@　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@注文が市場からの戻りを待っている状態の場合(*1)のみ、<BR>
     * 　@　@　@先物OP注文取引キューテーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@　@証券会社コード=引数の注文単位.getBranch().証券会社コード<BR>
     * 　@　@かつ　@部店コード=引数の注文単位.getBranch().部店コード<BR>
     * 　@　@かつ　@識別コード=引数の注文単位.識別コード<BR>
     * 　@　@かつ　@社内処理項目に含まれる注文Rev.(*2)=引数の注文単位.注文Rev.<BR>
     * 　@　@かつ　@処理区分!="0：未処理"<BR>
     * <BR>
     * 　@　@(*1)注文が市場からの戻りを待っている状態の場合<BR>
     * 　@　@　@　@引数の注文単位.注文状態が以下のいずれかに合致する場合、<BR>
     * 　@　@　@　@注文が市場からの戻りを待っている状態であると判定する。<BR>
     * 　@　@　@　@---------------------------------<BR>
     * 　@　@　@　@　@　@ACCEPTED<BR>
     * 　@　@　@　@　@　@MODIFY_ACCEPTED<BR>
     * 　@　@　@　@　@　@ORDERING<BR>
     * 　@　@　@　@---------------------------------<BR>
     * <BR>
     * 　@　@(*2)開始位置、桁数は<BR>
     * 　@　@　@　@this.get注文Rev開始位置IN社内処理項目()、get注文Rev桁数()で<BR>
     * 　@　@　@　@取得し指定する。<BR>
     * <BR>
     * ３）　@該当するデータが存在しない場合は、falseを返却する。<BR>
     * 　@　@　@該当データが存在する場合は、trueを返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isMarketNotifyingOrderInBreakTimeZone(
        IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isMarketNotifyingOrderInBreakTimeZone(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //    １）　@以下の条件のいずれかに合致する場合はfalseを返却する。
        // 　@　@　@取引時間管理.is市場開局時間帯()==falseの場合（＝引け後／非営業日）
        // 　@　@　@取引時間管理.is取引所休憩時間帯()==falseの場合（＝場中で取引所は取引中）
        // 　@　@　@以外、以下の処理を行う。
        if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone()
            || !WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if (!OrderStatusEnum.ACCEPTED.equals(l_orderStatus)
            && !OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
            && !OrderStatusEnum.ORDERING.equals(l_orderStatus))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        // ２）　@注文が市場からの戻りを待っている状態の場合(*1)のみ、
        // 　@　@　@先物OP注文取引キューテーブルを以下の条件で検索する。
        // 　@　@[条件]
        // 　@　@　@証券会社コード=引数の注文単位.getBranch().証券会社コード
        // 　@　@かつ　@部店コード=引数の注文単位.getBranch().部店コード
        // 　@　@かつ　@識別コード=引数の注文単位.識別コード
        // 　@　@かつ　@社内処理項目に含まれる注文Rev.(*2)=引数の注文単位.注文Rev.
        // 　@　@かつ　@処理区分!="0：未処理"
        // 　@　@(*1)注文が市場からの戻りを待っている状態の場合
        // 　@　@　@　@引数の注文単位.注文状態が以下のいずれかに合致する場合、
        // 　@　@　@　@注文が市場からの戻りを待っている状態であると判定する。
        // 　@　@　@　@---------------------------------
        // 　@　@　@　@　@　@ACCEPTED
        // 　@　@　@　@　@　@MODIFY_ACCEPTED
        // 　@　@　@　@　@　@ORDERING
        // 　@　@　@　@---------------------------------
        // 　@　@(*2)開始位置、桁数は
        // 　@　@　@　@this.get注文Rev開始位置IN社内処理項目()、get注文Rev桁数()で
        // 　@　@　@　@取得し指定する。
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        Object[] l_objWhere =
        {
            l_branchRow.getInstitutionCode(),
            l_branchRow.getBranchCode(),
            l_orderUnitRow.getOrderRequestNumber(),
            l_orderUnitRow.getOrderRev(),
            WEB3FrontOrderStatusDef.NOT_DEAL
        };
        int l_intIndex = this.getIndexOfOrderRevInCorpCode();
        int l_intFigure = this.getFigureOfOrderRev();
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append("institution_code = ?");
        l_strWhere.append(" and branch_code = ?");
        l_strWhere.append(" and order_request_number = ?");
        l_strWhere.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ") = ?");
        l_strWhere.append(" and status != ?");
        List l_lisSearchResult = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisSearchResult = l_processor.doFindAllQuery(
                HostFotypeOrderAllRow.TYPE,
                l_strWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataException l_de)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        // ３）　@該当するデータが存在しない場合は、falseを返却する。
        // 　@　@　@該当データが存在する場合は、trueを返却する。
        log.exiting(STR_METHOD_NAME);

        int l_intListSize = 0;
        if (l_lisSearchResult != null)
        {
            l_intListSize = l_lisSearchResult.size();
        }
        if (l_intListSize == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * (get先物OP注文取引キュー)<BR>
     * 指定の注文単位に該当する先物OP注文取引キューを取得し返す。<BR>
     * <BR>
     * １）　@先物OP注文取引キューテーブルを、以下の条件で検索する。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@証券会社コード = 引数の注文単位.getBranch().証券会社コード<BR>
     * 　@　@かつ　@部店コード = 引数の注文単位.getBranch().部店コード<BR>
     * 　@　@かつ　@識別コード = 引数の注文単位.識別コード<BR>
     * 　@　@かつ　@社内処理項目に含まれる注文Rev.(*1) =  引数の注文単位.注文Rev.<BR>
     * 　@　@かつ　@処理区分 = "未処理"<BR>
     * <BR>
     * 　@　@(*1)開始位置、桁数は<BR>
     * 　@　@　@　@this.get注文Rev開始位置IN社内処理項目()、get注文Rev桁数()で<BR>
     * 　@　@　@　@取得し指定する。<BR>
     * <BR>
     * ２）　@取得した先物OP注文取引キューParamsを返却する。<BR>
     * 　@　@　@該当するデータが存在しない場合は、nullを返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return HostFotypeOrderAllParams
     * @@throws WEB3BaseException
     */
    public HostFotypeOrderAllParams getHostFotypeOrderAll(
        IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHostFotypeOrderAll(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        Object[] l_objWhere =
        {
            l_branchRow.getInstitutionCode(),
            l_branchRow.getBranchCode(),
            l_orderUnitRow.getOrderRequestNumber(),
            l_orderUnitRow.getOrderRev(),
            WEB3FrontOrderStatusDef.NOT_DEAL
        };
        int l_intIndex = this.getIndexOfOrderRevInCorpCode();
        int l_intFigure = this.getFigureOfOrderRev();
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append("institution_code = ?");
        l_strWhere.append(" and branch_code = ?");
        l_strWhere.append(" and order_request_number = ?");
        l_strWhere.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ") = ?");
        l_strWhere.append(" and status = ?");
        List l_lisSearchResult = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisSearchResult = l_processor.doFindAllQuery(
                HostFotypeOrderAllRow.TYPE,
                l_strWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        HostFotypeOrderAllParams l_params = null;
        int l_intListSize = 0;
        if (l_lisSearchResult != null)
        {
            l_intListSize = l_lisSearchResult.size();
        }
        if (l_intListSize > 0)
        {
            l_params = (HostFotypeOrderAllParams)l_lisSearchResult.get(0);
        }
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }

    /**
     * (get訂正時注文Rev)<BR>
     * 引数の訂正後注文単位オブジェクトより、<BR>
     * 訂正時に注文単位テーブル.注文Revに設定する文字列を取得し返す。<BR>
     * ------------------- <BR>
     * ○注文単位テーブル.注文Rev.設定値について<BR>
     * 　@－失敗した訂正を含む、顧客の訂正オペレーションのカウンタ。市場へ送る訂正の回数が設定される。<BR>
     * 　@－あくまで市場へ送る訂正の回数のみを管理する必要があるため、<BR>
     * 　@　@　@以下のケースに該当する訂正の場合はカウントアップしてはいけない。<BR>
     * 　@　@　@（１）市場未送信注文に対する訂正時<BR>
     * 　@　@　@（２）訂正内容が市場通知不要な場合<BR>
     * ------------------- <BR>
     * <BR>
     * １）　@市場通知不要の訂正(*1)の場合は、引数の注文単位.注文Rev.をそのまま返却する。<BR>
     * <BR>
     * 　@　@(*1)市場通知不要の訂正<BR>
     * 　@　@　@　@OP注文マネージャ.is内容通知済注文(引数の注文単位)==trueの場合は、<BR>
     * 　@　@　@　@市場通知不要の訂正と判定する。<BR>
     * <BR>
     * ２）　@市場閉局時間帯(*2) または 市場未送信注文（*3)の場合は、<BR>
     * 　@　@　@引数の注文単位.注文Rev.をそのまま返却する。<BR>
     * <BR>
     * 　@　@(*2)市場閉局時間帯<BR>
     * 　@　@　@　@取引時間管理.is市場開局時間帯()==falseの場合は、<BR>
     * 　@　@　@　@市場閉局時間帯と判定する。<BR>
     * <BR>
     * 　@　@(*3)市場未送信注文<BR>
     * 　@　@　@　@引数の注文単位.市場から確認済の数量 == NaNの場合は、<BR>
     * 　@　@　@　@市場未送信注文と判定する。<BR>
     * <BR>
     * ３）　@上記以外の場合は、以下の通りとする。<BR>
     * 　@　@　@・戻り値の文字列は、this.get注文Rev桁数()の桁数固定（先頭は0埋め）とする。<BR>
     * 　@　@　@・桁数を超える値となった場合は、「注文Rev.の値が最大桁数を超過」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02185<BR>
     * <BR>
     * ３－１）　@取引所が取引中の時間帯(*4)の場合は、<BR>
     * 　@　@　@　@　@引数の注文単位.注文Rev.に＋１した文字列を返却する。<BR>
     * <BR>
     * 　@　@(*4)取引所が取引中<BR>
     * 　@　@　@　@取引時間管理.is取引所休憩時間帯()==falseの場合は、<BR>
     * 　@　@　@　@取引所が取引中の時間帯と判定する。<BR>
     * <BR>
     * ３－２）　@上記以外の場合は、先物OP注文取引キューテーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@　@証券会社コード = 引数の訂正後注文単位.getBranch().証券会社コード<BR>
     * 　@　@かつ　@部店コード = 引数の訂正後注文単位.getBranch().部店コード<BR>
     * 　@　@かつ　@識別コード = 引数の訂正後注文単位.識別コード <BR>
     * 　@　@かつ　@社内処理項目に含まれる注文Rev.(*5) =  引数の訂正後注文単位.注文Rev.<BR>
     * 　@　@かつ　@処理区分 = "未処理"<BR>
     * <BR>
     * 　@　@(*5)開始位置、桁数は<BR>
     * 　@　@　@　@先物OP発注サービス.get注文Rev開始位置IN社内処理項目()、get注文Rev桁数()で<BR>
     * 　@　@　@　@取得し指定する。<BR>
     * <BR>
     * ３－３）　@訂正時の注文Rev.の値を求める。<BR>
     * <BR>
     * 　@　@○３－２）で該当するレコードが存在しない場合<BR>
     * 　@　@　@　@引数の注文単位.注文Rev.に＋１した値を返却する。<BR>
     * <BR>
     * 　@　@○３－２）で該当するレコードが存在する場合<BR>
     * 　@　@　@引数の注文単位.注文Rev.をそのまま返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (訂正後注文単位)<BR>
     * 訂正後の注文単位オブジェクト。<BR>
     * （xTrade標準項目に、訂正後の値が設定されているオブジェクト）<BR>
     * ※更新インタセプタ.mutate()内部からコールされることを前提としている。<BR>
     * <BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeOrderRev(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderRev(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //    １）　@市場通知不要の訂正(*1)の場合は、引数の注文単位.注文Rev.をそのまま返却する。
        //  　@　@(*1)市場通知不要の訂正
        //  　@　@　@　@OP注文マネージャ.is内容通知済注文(引数の注文単位)==trueの場合は、
        //  　@　@　@　@市場通知不要の訂正と判定する。
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderRev = l_orderUnitRow.getOrderRev();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        if (l_orderManagerImpl.isNotifyEndOrder(l_orderUnit))
        {
            log.debug(STR_METHOD_NAME);
            return l_strOrderRev;
        }

        //  ２）　@市場閉局時間帯(*2)または市場未送信注文（*3)の場合は、
        //  　@　@　@引数の注文単位.注文Rev.をそのまま返却する。
        //  　@　@(*2)市場閉局時間帯
        //  　@　@　@　@取引時間管理.is市場開局時間帯()==falseの場合は、
        //  　@　@　@　@市場閉局時間帯と判定する。
        //
        //  　@　@(*3)市場未送信注文
        //  　@　@　@　@引数の注文単位.市場から確認済の数量==NaNの場合は、
        //  　@　@　@　@市場未送信注文と判定する。
        if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone()
            || Double.isNaN(l_orderUnit.getConfirmedQuantity()))
        {
            log.debug(STR_METHOD_NAME);
            return l_strOrderRev;
        }

        //
        //  ３）　@上記以外の場合は、以下の通りとする。
        //  　@　@　@・戻り値の文字列は、this.get注文Rev桁数()の桁数固定（先頭は0埋め）とする。
        //  　@　@　@・桁数を超える値となった場合は、「注文Rev.の値が最大桁数を超過」の例外をthrowする。
        //  ３－１）　@取引所が取引中の時間帯(*4)の場合は、
        //  　@　@　@　@　@引数の注文単位.注文Rev.に＋１した文字列を返却する。
        //  　@　@(*4)取引所が取引中
        //  　@　@　@　@取引時間管理.is取引所休憩時間帯()==falseの場合は、
        //  　@　@　@　@取引所が取引中の時間帯と判定する。
        if (!WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
        {
            int l_intOrderRev = Integer.parseInt(l_strOrderRev);
            l_intOrderRev += 1;
            l_strOrderRev = Integer.toString(l_intOrderRev);
            int l_intFigureOfOrderRev = this.getFigureOfOrderRev();
            int l_intLength = l_strOrderRev.length();
            if (l_intLength > l_intFigureOfOrderRev)
            {
                log.debug("注文Rev.の値が最大桁数を超過。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02185,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            int l_intSize = l_intFigureOfOrderRev - l_intLength;
            StringBuffer l_sbOrderRev = new StringBuffer();
            for (int i = 0; i < l_intSize; i++)
            {
                l_sbOrderRev.append("0");
            }
            l_sbOrderRev.append(l_strOrderRev);

            log.exiting(STR_METHOD_NAME);
            return l_sbOrderRev.toString();
        }

        //  ３－２）　@上記以外の場合は、先物OP注文取引キューテーブルを以下の条件で検索する。
        //  　@　@[条件]
        //  　@　@　@証券会社コード=引数の訂正後注文単位.getBranch().証券会社コード
        //  　@　@かつ　@部店コード=引数の訂正後注文単位.getBranch().部店コード
        //  　@　@かつ　@識別コード=引数の訂正後注文単位.識別コード
        //  　@　@かつ　@社内処理項目に含まれる注文Rev.(*5)=引数の訂正後注文単位.注文Rev.
        //  　@　@かつ　@処理区分="未処理"
        //  　@　@(*5)開始位置、桁数は
        //  　@　@　@　@先物OP発注サービス.get注文Rev開始位置IN社内処理項目()、get注文Rev桁数()で
        //  　@　@　@　@取得し指定する。
        else
        {
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Branch l_branch = null;
            try
            {
                l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }

            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
            Object[] l_objWhere =
            {
                l_branchRow.getInstitutionCode(),
                l_branchRow.getBranchCode(),
                l_orderUnitRow.getOrderRequestNumber(),
                l_orderUnitRow.getOrderRev(),
                WEB3FrontOrderStatusDef.NOT_DEAL
            };

            int l_intIndex = this.getIndexOfOrderRevInCorpCode();
            int l_intFigure = this.getFigureOfOrderRev();
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append("institution_code = ?");
            l_strWhere.append(" and branch_code = ?");
            l_strWhere.append(" and order_request_number = ?");
            l_strWhere.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ") = ?");
            l_strWhere.append(" and status = ?");
            List l_lisSearchResult = null;

            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_lisSearchResult = l_processor.doFindAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    l_strWhere.toString(),
                    null,
                    l_objWhere);
            }
            catch (DataException l_de)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_de.getMessage(),
                    l_de);
            }

            int l_intListSize = 0;
            if (l_lisSearchResult != null)
            {
                l_intListSize = l_lisSearchResult.size();
            }

            if (l_intListSize == 0)
            {
                //  ３－３）　@訂正時の注文Rev.の値を求める。
                //  　@　@○３－２）で該当するレコードが存在しない場合
                //  　@　@　@　@引数の注文単位.注文Rev.に＋１した値を返却する。
                int l_lngOrderRev = Integer.parseInt(l_strOrderRev);
                l_lngOrderRev += 1;
                l_strOrderRev = Integer.toString(l_lngOrderRev);
                int l_intFigureOfOrderRev = this.getFigureOfOrderRev();
                int l_intLength = l_strOrderRev.length();
                if (l_intLength > l_intFigureOfOrderRev)
                {
                    log.debug("注文Rev.の値が最大桁数を超過。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02185,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                int l_intSize = l_intFigureOfOrderRev - l_intLength;
                StringBuffer l_sbOrderRev = new StringBuffer();
                for (int i = 0; i < l_intSize; i++)
                {
                    l_sbOrderRev.append("0");
                }
                l_sbOrderRev.append(l_strOrderRev);

                log.exiting(STR_METHOD_NAME);
                return l_sbOrderRev.toString();
            }
            //  　@　@○３－２）で該当するレコードが存在する場合
            //  　@　@　@引数の注文単位.注文Rev.をそのまま返却する。
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_strOrderRev;
            }
        }
    }

    /**
     * (lock先物OP注文取引キュー)<BR>
     * 指定の注文単位の先物OP注文取引キューデータに共有ロックをかける。<BR>
     * <BR>
     * １）　@引数の注文単位オブジェクトを指定し、<BR>
     * 　@　@　@先物OP注文取引キューTransactionCallbackクラスを生成する。<BR>
     * <BR>
     * ２）　@生成したTransactionCallbackクラスを指定し、<BR>
     * 　@　@　@QueryProcessor.doTransaction()を実行する。<BR>
     * 　@　@　@（トランザクション属性： TX_JOIN_EXISTING）<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    public void lockHostFotypeOrderAll(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "lockHostFotypeOrderAll(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //１）　@引数の注文単位オブジェクトを指定し、
        //　@　@　@先物OP注文取引キューTransactionCallbackクラスを生成する。
        WEB3IfoOrderAllTransactionCallback l_transactionCallback =
            new WEB3IfoOrderAllTransactionCallback(l_orderUnit);

        //２）　@生成したTransactionCallbackクラスを指定し、
        //　@　@　@QueryProcessor.doTransaction()を実行する。
        //　@　@　@（トランザクション属性：TX_JOIN_EXISTING）
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doTransaction(
                QueryProcessor.TX_JOIN_EXISTING,
                l_transactionCallback);
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (get発注時MQデータコード)<BR>
     * 発注経路区分、先物オプション区分により、<BR>
     * 発注時に使用するMQデータコード設定用文字列を取得し返却する。<BR>
     * <BR>
     * １）　@引数の注文単位.発注経路区分=="9：発注停止"の場合は、nullを返却する。<BR>
     * <BR>
     * ２）　@引数の注文単位.発注経路区分==　@"SONAR正系"の場合は、<BR>
     * <BR>
     * 　@２－１）引数の注文単位.先物/オプション区分 == ”先物”の場合、”EI803T”（先物）<BR>
     * <BR>
     * 　@２－２）２－１）以外、”EI801T”（オプション）<BR>
     * <BR>
     * 　@を返却する。<BR>
     * <BR>
     * ３）　@引数の注文単位.発注経路区分==　@"フロント副系"の場合は、"EI8X2T"を返却する。<BR>
     * <BR>
     * <BR>
     * ４）　@引数の注文単位.発注経路区分が上記以外の場合は、nullを返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getOrderMQDataCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderMQDataCode(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        //１）　@引数の注文単位.発注経路区分=="9：発注停止"の場合は、nullを返却する。
        if (WEB3SubmitOrderRouteDivDef.ORDER_STOP.equals(l_ifoOrderUnitRow.getSubmitOrderRouteDiv()))
        {
            log.debug("引数の注文単位.発注経路区分==9：発注停止の場合");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@引数の注文単位.発注経路区分==　@"SONAR正系"の場合は、
        //　@２－１）引数の注文単位.先物/オプション区分==”先物”の場合、”EI803T”（先物）
        //　@２－２）２－１）以外、”EI801T”（オプション）
        //　@を返却する。
        else if (WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION.equals(
            l_ifoOrderUnitRow.getSubmitOrderRouteDiv()))
        {
            log.debug("引数の注文単位.発注経路区分==　@SONAR正系の場合");
            if (WEB3FuturesOptionDivDef.FUTURES.equals(l_ifoOrderUnitRow.getFutureOptionDiv()))
            {
                log.debug("引数の注文単位.先物/オプション区分==”先物”の場合");
                log.exiting(STR_METHOD_NAME);
                return "EI803T";
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return "EI801T";
            }
        }

        //３）　@引数の注文単位.発注経路区分==　@"フロント副系"の場合は、"EI8X2T"を返却する。
        else if (WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION.equals(
            l_ifoOrderUnitRow.getSubmitOrderRouteDiv()))
        {
            log.debug("引数の注文単位.発注経路区分==　@フロント副系の場合");
            log.exiting(STR_METHOD_NAME);
            return "EI8X2T";
        }

        //４）　@引数の注文単位.発注経路区分が上記以外の場合は、nullを返却する
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get訂正取消時MQデータコード)<BR>
     * 発注経路区分、先物オプション区分により、<BR>
     * 訂正取消時に使用するMQデータコード設定用文字列を取得し返却する。<BR>
     * <BR>
     * １）　@引数の注文単位.発注経路区分=="9：発注停止"の場合は、nullを返却する。<BR>
     * <BR>
     * ２）　@引数の注文単位.発注経路区分==　@"SONAR正系"の場合は、<BR>
     * 　@２－１）　@引数の注文単位.先物/オプション区分 == ”先物”の場合、”EI804T”（先物）<BR>
     * 　@２－２）　@２－１）以外、”EI802T”（オプション）<BR>
     * 　@を返却する。<BR>
     * <BR>
     * ３）　@引数の注文単位.発注経路区分==　@"フロント副系"の場合は、"EI8X2T"を返却する。<BR>
     * <BR>
     * ４）　@引数の注文単位.発注経路区分が上記以外の場合は、nullを返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeCancelMQDataCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeCancelMQDataCode(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）　@引数の注文単位.発注経路区分=="9：発注停止"の場合は、nullを返却する。
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (WEB3SubmitOrderRouteDivDef.ORDER_STOP.equals(l_ifoOrderUnitRow.getSubmitOrderRouteDiv()))
        {
            log.debug("引数の注文単位.発注経路区分==9：発注停止の場合");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@引数の注文単位.発注経路区分==　@"SONAR正系"の場合は、
        //　@２－１）　@引数の注文単位.先物/オプション区分==”先物”の場合、”EI804T”（先物）
        //　@２－２）　@２－１）以外、”EI802T”（オプション）
        //　@を返却する。
        else if (WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION.equals(
            l_ifoOrderUnitRow.getSubmitOrderRouteDiv()))
        {
            log.debug("引数の注文単位.発注経路区分==　@SONAR正系の場合");
            if (WEB3FuturesOptionDivDef.FUTURES.equals(l_ifoOrderUnitRow.getFutureOptionDiv()))
            {
                log.debug("引数の注文単位.先物/オプション区分==”先物”の場合");
                log.exiting(STR_METHOD_NAME);
                return "EI804T";
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return "EI802T";
            }
        }

        //３）　@引数の注文単位.発注経路区分==　@"フロント副系"の場合は、"EI8X2T"を返却する。
        else if (WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION.equals(
            l_ifoOrderUnitRow.getSubmitOrderRouteDiv()))
        {
            log.debug("引数の注文単位.発注経路区分==　@フロント副系の場合");
            log.exiting(STR_METHOD_NAME);
            return "EI8X2T";
        }
        //４）　@引数の注文単位.発注経路区分が上記以外の場合は、nullを返却する
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get訂正取消時発注経路区分)<BR>
     * 引数の訂正取消対象の注文単位オブジェクトより、発注可能な発注経路区分を取得し返却する。<BR>
     * －原則として、現在有効な発注経路を返却する。(BR)
     * －発注経路変更不可の経路を通して発注した注文の場合は、注文時の経路を返却する。<BR>
     * <BR>
     * <BR>
     * １）　@引数の注文単位.発注経路区分 != "発注停止"の場合のみ、<BR>
     * 　@　@　@以下の処理を行う。<BR>
     * 　@　@　@※引数の注文単位.発注経路区分=="発注停止"の場合は、<BR>
     * 　@　@　@※発注経路変更可否のチェックは行わない。<BR>
     * <BR>
     * １－１）　@this.getフロント発注システム区分()により、<BR>
     * 　@　@フロント発注システム区分を取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜getフロント発注システム区分()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@市場コード：　@引数の注文単位.getMarket().市場コード<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * １－２）　@訂正取消時に経路の変更が可能な発注経路かどうかをチェックする。<BR>
     * 　@　@　@変更が不可な発注経路の場合は、発注時の発注経路を返却する。<BR>
     * <BR>
     * １－２－１）　@発注時の発注経路区分を使用し発注先切替クラスのインスタンスを取得する。<BR>
     * <BR>
     * 　@　@　@------------------------------------------------<BR>
     * 　@　@　@＜発注先切替()：コンストラクタ引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@証券会社コード：　@引数の注文単位.getBranch().証券会社コード <BR>
     * 　@　@　@銘柄タイプ：　@"先物オプション"  <BR>
     * 　@　@　@市場コード：　@引数の注文単位.getMarket().市場コード <BR>
     * 　@　@　@発注経路区分：　@引数の注文単位.発注経路区分 <BR>
     * 　@　@　@フロント発注システム区分：　@１－１）で求めたフロント発注システム区分 <BR>
     * 　@　@　@------------------------------------------------  <BR>
     * <BR>
     * １－２－２）　@以下の条件のいずれかに該当する場合は、 <BR>
     * 　@　@　@　@　@引数の注文単位.発注経路区分を返却する。 <BR>
     * <BR>
     * 　@　@・取得したインスタンス.訂正取消可能フラグ == "不可"の場合 <BR>
     * 　@　@・取得したインスタンス.isSONAR() == trueの場合 <BR>
     * 　@　@・該当する発注先切替データが存在しない場合 <BR>
     * 　@　@　@　@（訂正／取消対象の注文単位が、フロント発注対応市場に対する注文で、 <BR>
     * 　@　@　@　@　@かつ　@SONAR入力注文の場合は、該当する発注先切替データが存在しない。 <BR>
     * 　@　@　@　@　@（SONAR入力注文の場合、発注経路区分にはSONARの経路が設定されているため）） <BR>
     * <BR>
     * ２）　@発注先切替.get有効発注先切替()により、有効な発注先切替オブジェクトを取得する。 <BR>
     * <BR>
     * 　@　@　@------------------------------------------------  <BR>
     * 　@　@　@＜発注先切替.get有効発注先切替()：引数設定仕様＞ <BR>
     * <BR>
     * 　@　@　@証券会社コード：　@引数の注文単位.getBranch().証券会社コード <BR>
     * 　@　@　@銘柄タイプ：　@"先物オプション"  <BR>
     * 　@　@　@市場コード：　@引数の注文単位.getMarket().市場コード <BR>
     * 　@　@　@フロント発注システム区分：　@１－１）で求めたフロント発注システム区分 <BR>
     * 　@　@　@------------------------------------------------  <BR>
     * <BR>
     * ３）　@２）で戻り値 != nullの場合は、戻り値.発注経路区分 を返却する。 <BR>
     * 　@　@　@２）で戻り値 == nullの場合は、「発注経路切替対象なし」の例外をthrowする。 <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02216<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeSubmitOrderRouteDiv(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeSubmitOrderRouteDiv(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        String l_strSubmitOrderRouteDiv = l_orderUnitRow.getSubmitOrderRouteDiv();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        String l_strMarketCode = null;
        try
        {
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            l_strMarketCode = l_market.getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        String l_strFrontOrderSystemCode =
            this.getFrontOrderSystemCode(l_strMarketCode);

        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        String l_strInstitutionCode = null;
        try
        {
            Branch l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
            l_strInstitutionCode = l_branchRow.getInstitutionCode();
        }
        catch (NotFoundException l_nfe)
        {
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        WEB3GentradeOrderSwitching l_orderSwitching = null;
        OrderSwitchingRow l_orderSwitchingRow = null;
        if (!WEB3SubmitOrderRouteDivDef.ORDER_STOP.equals(l_strSubmitOrderRouteDiv))
        {
            try
            {
                l_orderSwitching =
                    new WEB3GentradeOrderSwitching(
                        l_strInstitutionCode,
                        ProductTypeEnum.IFO,
                        l_strMarketCode,
                        l_strSubmitOrderRouteDiv,
                        l_strFrontOrderSystemCode);
            }
            catch (WEB3SystemLayerException l_sle)
            {
                if (!l_sle.getErrorInfo().equals(WEB3ErrorCatalog.SYSTEM_ERROR_80005))
                {
                    throw l_sle;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return l_strSubmitOrderRouteDiv;
                }
            }

            l_orderSwitchingRow =
                (OrderSwitchingRow)l_orderSwitching.getDataSourceObject();
            if (WEB3ChangeCancelEnableFlag.DISABLE.equals(l_orderSwitchingRow.getChangeCancelEnableFlag()))
            {
                log.exiting(STR_METHOD_NAME);
                return l_strSubmitOrderRouteDiv;
            }
            if (l_orderSwitching.isSONAR())
            {
                log.exiting(STR_METHOD_NAME);
                return l_strSubmitOrderRouteDiv;
            }
        }

        l_orderSwitching =
            WEB3GentradeOrderSwitching.getOnOrderSwitching(
                l_strInstitutionCode,
                ProductTypeEnum.IFO,
                l_strMarketCode,
                l_strFrontOrderSystemCode);

        log.exiting(STR_METHOD_NAME);
        if (l_orderSwitching != null)
        {
            l_orderSwitchingRow =
                (OrderSwitchingRow)l_orderSwitching.getDataSourceObject();
            return l_orderSwitchingRow.getSubmitOrderRouteDiv();
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02216,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券会社コード:" + l_strInstitutionCode
                + " 市場コード:" + l_strMarketCode
                + " フロント発注システム区分:" + l_strFrontOrderSystemCode);
        }
    }

    /**
     * (get（被訂正）社内処理項目)<BR>
     * 引数の注文単位オブジェクトより、<BR>
     * 発注に使用する「（被訂正）社内処理項目」設定用文字列を取得し返却する。<BR>
     * <BR>
     * １）　@部店オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@引数の注文単位.getBranch( )にて取得する。<BR>
     * <BR>
     * ２）　@以下の文字列を返却する。<BR>
     * <BR>
     * 　@　@証券会社ID＋部店コード＋銘柄タイプ＋識別コード＋市場から確認済の注文Rev.＋"999"<BR>
     * <BR>
     * 　@　@部店オブジェクトから取得：　@証券会社ID、部店コード<BR>
     * 　@　@引数の注文単位オブジェクトから取得：　@銘柄タイプ、識別コード、市場から確認済の注文Rev.<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getOrgCorpCode(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgCorpCode(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //    １）　@部店オブジェクトを取得する。
        // 　@　@　@引数の注文単位.getBranch()にて取得する。
        // ２）　@以下の文字列を返却する。
        // 　@　@証券会社ID＋部店コード＋銘柄タイプ＋識別コード＋市場から確認済の注文Rev.＋"999"
        // 　@　@部店オブジェクトから取得：　@証券会社ID、部店コード
        // 　@　@引数の注文単位オブジェクトから取得：　@銘柄タイプ、識別コード、市場から確認済の注文Rev.
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        BranchRow l_branchRow = null;
        try
        {
            Branch l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        StringBuffer l_strCorpCode = new StringBuffer();
        l_strCorpCode.append(Long.toString(l_branchRow.getInstitutionId()));
        l_strCorpCode.append(l_branchRow.getBranchCode());
        l_strCorpCode.append(Integer.toString(l_orderUnitRow.getProductType().intValue()));
        l_strCorpCode.append(l_orderUnitRow.getOrderRequestNumber());
        l_strCorpCode.append(l_orderUnitRow.getConfirmedOrderRev());
        l_strCorpCode.append("999");

        log.exiting(STR_METHOD_NAME);
        return l_strCorpCode.toString();
    }

    /**
     * (getフロント発注取引所区分コード)<BR>
     * 引数の市場コードより、フロント発注取引所区分コードを取得し返却する。<BR>
     * <BR>
     * １）　@引数の市場コード＝"1"（東証） の場合<BR>
     * 　@　@引数の市場コードの値をそのまま返す。<BR>
     * <BR>
     * ２）　@引数の市場コード＝"2"（大証） の場合<BR>
     * 　@　@引数の市場コードの値をそのまま返す。<BR>
     * <BR>
     * ３）　@引数の市場コードが上記以外の場合<BR>
     * 　@　@nullを返す。<BR>
     * <BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * WebⅢの市場コード。
     * @@return String
     */
    public String getFrontOrderExchangeCode(String l_strMarketCode)
    {
        final String STR_METHOD_NAME = "getFrontOrderExchangeCode(String)";
        log.entering(STR_METHOD_NAME);

        //１）　@引数の市場コード＝"1"（東証）の場合
        //  　@　@引数の市場コードの値をそのまま返す。
        if (WEB3MarketCodeDef.TOKYO.equals(l_strMarketCode))
        {
            log.exiting(STR_METHOD_NAME);
            return l_strMarketCode;
        }
        //  ２）　@引数の市場コード＝"2"（大証）の場合
        //  　@　@引数の市場コードの値をそのまま返す。
        else if (WEB3MarketCodeDef.OSAKA.equals(l_strMarketCode))
        {
            log.exiting(STR_METHOD_NAME);
            return l_strMarketCode;
        }
        //  ３）　@引数の市場コードが上記以外の場合
        //  　@　@nullを返す。
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get発注先切替)<BR>
     * 指定の注文単位に合致する発注先切替オブジェクトを取得し返す。<BR>
     * <BR>
     * １）　@引数の注文単位.発注経路区分 == "9：発注停止"の場合は、nullを返却する。<BR>
     * <BR>
     * ２）　@this.getフロント発注システム区分()にて、フロント発注システム区分を取得する。<BR>
     * <BR>
     * 　@　@　@------------------------------------------------ <BR>
     * 　@　@　@＜getフロント発注システム区分()：引数設定仕様＞ <BR>
     * <BR>
     * 　@　@　@市場コード：　@引数の注文単位.市場IDに該当する市場コード<BR>
     * <BR>
     * 　@　@　@------------------------------------------------ <BR>
     * <BR>
     * ３）　@発注先切替クラスをインスタンス化する。 <BR>
     * <BR>
     * 　@　@　@------------------------------------------------ <BR>
     * 　@　@　@＜発注先切替クラス：コンストラクタ引数設定仕様＞ <BR>
     * <BR>
     * 　@　@　@証券会社コード：　@引数の注文単位.部店IDに該当する部店.証券会社コード <BR>
     * 　@　@　@銘柄タイプ：　@"先物オプション" <BR>
     * 　@　@　@市場コード：　@引数の注文単位.市場IDに該当する市場コード <BR>
     * 　@　@　@発注経路区分：　@引数の注文単位.発注経路区分 <BR>
     * 　@　@　@フロント発注システム区分：　@２）で取得したフロント発注システム区分 <BR>
     * 　@　@　@------------------------------------------------ <BR>
     * <BR>
     * ４）　@発注先切替クラスのインスタンスを返却する。 <BR>
     * 　@　@　@３）で該当するデータが存在しない場合は、nullを返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return 発注先切替
     * @@throws WEB3BaseException
     */
    public WEB3GentradeOrderSwitching getOrderSwitching(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderSwitching(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）　@引数の注文単位.発注経路区分=="9：発注停止"の場合は、nullを返却する。
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (WEB3SubmitOrderRouteDivDef.ORDER_STOP.equals(l_orderUnitRow.getSubmitOrderRouteDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@this.getフロント発注システム区分()にて、フロント発注システム区分を取得する。
        //　@　@　@------------------------------------------------
        //　@　@　@＜getフロント発注システム区分()：引数設定仕様＞
        //
        //　@　@　@市場コード：　@引数の注文単位.市場IDに該当する市場コード
        //　@　@　@------------------------------------------------
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        String l_strMarketCode = null;

        try
        {
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            l_strMarketCode = l_market.getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        String l_strFrontOrderSystemCode =
            this.getFrontOrderSystemCode(
                l_strMarketCode);

        //３）　@発注先切替クラスをインスタンス化する。
        //　@　@　@------------------------------------------------
        //　@　@　@＜発注先切替クラス：コンストラクタ引数設定仕様＞
        //　@　@　@証券会社コード：　@引数の注文単位.部店IDに該当する部店.証券会社コード
        //　@　@　@銘柄タイプ：　@"先物オプション"
        //　@　@　@市場コード：　@引数の注文単位.市場IDに該当する市場コード
        //　@　@　@発注経路区分：　@引数の注文単位.発注経路区分
        //　@　@　@フロント発注システム区分：　@２）で取得したフロント発注システム区分
        //　@　@　@------------------------------------------------
        //４）　@発注先切替クラスのインスタンスを返却する。
        //　@　@　@３）で該当するデータが存在しない場合は、nullを返却する。
        WEB3GentradeOrderSwitching l_orderSwitching = null;
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        String l_strInstitutionCode = null;
        try
        {
            Branch l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
            l_strInstitutionCode = l_branchRow.getInstitutionCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        try
        {
            l_orderSwitching =
                new WEB3GentradeOrderSwitching(
                    l_strInstitutionCode,
                    ProductTypeEnum.IFO,
                    l_strMarketCode,
                    l_orderUnitRow.getSubmitOrderRouteDiv(),
                    l_strFrontOrderSystemCode);
        }
        catch (WEB3SystemLayerException l_sle)
        {
            if (!l_sle.getErrorInfo().equals(WEB3ErrorCatalog.SYSTEM_ERROR_80005))
            {
                throw l_sle;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderSwitching;
    }

    /**
     * (update先物OP注文取引キューAT受付時間外)<BR>
     * 前場受付時間外注文を再発注するためのキューデータ更新を行う。<BR>
     * <BR>
     * １）　@引数のis取消 == true（取消）の場合、<BR>
     * 　@　@以下の条件に合致するレコード.処理区分を"未処理"に更新する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@　@取消区分 = "取消"<BR>
     * 　@　@かつ　@社内処理項目 = this.get（被訂正）社内処理項目(引数の注文単位（更新前）)の戻り値<BR>
     * 　@　@かつ　@全訂正処理区分 = "全訂正以外" <BR>
     * <BR>
     * ２）　@引数のis取消 == false（取消以外）の場合、<BR>
     * 　@　@以下の条件に合致するレコードを更新する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@　@取消区分 = "取消以外"<BR>
     * 　@　@かつ　@社内処理項目 = this.get社内処理項目(引数の注文単位（更新前）)の戻り値<BR>
     * 　@　@かつ　@全訂正処理区分 = "全訂正以外" <BR>
     * <BR>
     * 　@　@[更新対象項目] <BR>
     * 　@　@　@社内処理項目：　@this.get社内処理項目(引数の注文単位（更新後）)の戻り値 <BR>
     * 　@　@　@処理区分：　@"未処理" <BR>
     * <BR>
     * ※いずれの場合も更新日付を現在日時に更新する。<BR>
     * <BR>
     * @@param l_orderUnitAfter - (注文単位（更新後）)<BR>
     * 更新後の注文単位オブジェクト。
     * @@param l_orderUnitBefore - (注文単位（更新前）)<BR>
     * 更新前の注文単位オブジェクト。
     * @@param l_blnIsCancel - (is取消)<BR>
     * 取消かどうかを判定するフラグ。<BR>
     * （true：取消、false：取消以外）
     * @@throws WEB3BaseException
     */
    public void updateHostFotypeOrderAllAtAcceptOvertime(
        IfoOrderUnit l_orderUnitAfter,
        IfoOrderUnit l_orderUnitBefore,
        boolean l_blnIsCancel) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateHostFotypeOrderAllAtAcceptOvertime(IfoOrderUnit, IfoOrderUnit, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitBefore == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            if (l_blnIsCancel)
            {
                //    １）　@引数のis取消==true（取消）の場合、
                //  　@　@以下の条件に合致するレコード.処理区分を"未処理"に更新する。
                //  　@　@[条件]
                //  　@　@　@取消区分="取消"
                //  　@　@かつ　@社内処理項目=this.get（被訂正）社内処理項目(引数の注文単位（更新前）)の戻り値
                //  　@　@かつ　@全訂正処理区分="全訂正以外"
                String l_strWhere =
                    "cancel_div=? and corp_code=? and all_order_change_div=?";
                Object[] l_objWhere =
                {
                    WEB3CancelDivDef.CANCEL,
                    this.getOrgCorpCode(l_orderUnitBefore),
                    WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE
                };

                HashMap l_map = new HashMap();
                l_map.put("status", WEB3FrontOrderStatusDef.NOT_DEAL);
                l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                log.debug("GtlUtils.getSystemTimestamp()" + GtlUtils.getSystemTimestamp());
                l_processor.doUpdateAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    l_strWhere,
                    l_objWhere,
                    l_map);
            }
            else
            {
                //  ２）　@引数のis取消==false（取消以外）の場合、
                //  　@　@以下の条件に合致するレコードを更新する。
                //  　@　@[条件]
                //  　@　@　@取消区分="取消以外"
                //  　@　@かつ　@社内処理項目=this.get社内処理項目(引数の注文単位（更新前）)の戻り値
                //  　@　@かつ　@全訂正処理区分="全訂正以外"
                //  　@　@[更新対象項目]
                //  　@　@　@社内処理項目：　@this.get社内処理項目(引数の注文単位（更新後）)の戻り値
                //  　@　@　@処理区分：　@"未処理"
                String l_strWhere =
                    "cancel_div=? and corp_code=? and all_order_change_div=?";

                Object[] l_objWhere =
                {
                    WEB3CancelDivDef.EXCEPT_CANCEL,
                    this.getCorpCode(l_orderUnitBefore),
                    WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE
                };

                HashMap l_map = new HashMap();
                l_map.put("corp_code", this.getCorpCode(l_orderUnitAfter));
                l_map.put("status", WEB3FrontOrderStatusDef.NOT_DEAL);
                l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                log.debug("GtlUtils.getSystemTimestamp()" + GtlUtils.getSystemTimestamp());
                l_processor.doUpdateAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    l_strWhere,
                    l_objWhere,
                    l_map);
            }
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getNext注文Rev)<BR>
     * 引数の注文Revに1加算した値を返す。<BR>
     * <BR>
     * <BR>
     * １）　@引数の注文Revに1加算する。<BR>
     * <BR>
     * ２）　@注文Revの桁数を取得する。<BR>
     * 　@　@this.get注文Rev桁数() <BR>
     * <BR>
     * ３）　@算出した注文Revが注文Revの桁数を超えた場合は、<BR>
     * 　@　@「注文Rev.の値が最大桁数を超過」の例外をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02185<BR>
     * <BR>
     * @@param l_strOrderRev - (注文Rev)<BR>
     * @@return String
     * @@throws WEB3BusinessLayerException
     */
    public String getNextOrderRev(String l_strOrderRev) throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "getNextOrderRev(String)";
        log.entering(STR_METHOD_NAME);

        //１）　@引数の注文Revに1加算する。
        //２）　@注文Revの桁数を取得する。
        //　@　@this.get注文Rev桁数()
        //３）　@算出した注文Revが注文Revの桁数を超えた場合は、
        //　@　@「注文Rev.の値が最大桁数を超過」の例外をthrowする。
        int l_intNextOrderRev = Integer.parseInt(l_strOrderRev) + 1;
        String l_strNextOrderRev = Integer.toString(l_intNextOrderRev);
        int l_intMaxOrderRevLen = this.getFigureOfOrderRev();
        int l_intNextOrderRevLen = l_strNextOrderRev.length();

        if (l_intNextOrderRevLen > l_intMaxOrderRevLen)
        {
            log.debug("注文Rev.の値が最大桁数を超過。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02185,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        StringBuffer l_strBufNextOrderRev = new StringBuffer();
        for (int i = l_intNextOrderRevLen; i < l_intMaxOrderRevLen; i++)
        {
            l_strBufNextOrderRev.append("0");
        }

        l_strBufNextOrderRev.append(l_strNextOrderRev);

        log.exiting(STR_METHOD_NAME);
        return l_strBufNextOrderRev.toString();
    }
}
@
