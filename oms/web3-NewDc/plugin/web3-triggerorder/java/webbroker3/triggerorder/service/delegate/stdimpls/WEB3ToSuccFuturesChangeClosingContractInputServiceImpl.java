head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesChangeClosingContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）先物訂正返済入力サービスImpl（WEB3ToSuccFuturesChangeClosingContractInputServiceImpl.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/14 孟亞南(中訊) 新規作成モデルNo.264 No.286 No.309
 Revision History : 2008/04/17 孟亞南(中訊) 仕様変更モデルNo.327
 */

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeClosingContractInputServiceImpl;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeClosingContractInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）先物訂正返済入力サービスImpl)<BR>
 * （連続）株価指数先物訂正返済入力サービス実装クラス<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3ToSuccFuturesChangeClosingContractInputServiceImpl
    extends WEB3FuturesChangeClosingContractInputServiceImpl
    implements WEB3ToSuccFuturesChangeClosingContractInputService
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesChangeClosingContractInputServiceImpl.class);

    /**
     * @@roseuid 47D6593402DE
     */
    public WEB3ToSuccFuturesChangeClosingContractInputServiceImpl()
    {

    }

    /**
     * （連続）株価指数先物訂正返済入力サービス処理を実施する。<BR>
     * <BR>
     * this.get訂正返済入力画面()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A94F7A02B0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        if (!(l_request instanceof WEB3SuccFuturesCloseChangeInputRequest))
        {
            log.debug("パラメータタイプ不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正");
        }

        WEB3SuccFuturesCloseChangeInputRequest l_inputRequest =
            (WEB3SuccFuturesCloseChangeInputRequest)l_request;

        //this.get訂正返済入力画面()
        WEB3SuccFuturesCloseChangeInputResponse l_response =
            this.getFuturesChangeClosingContractInputScreen(l_inputRequest);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get訂正返済入力画面)<BR>
     * （連続）株価指数先物訂正返済入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連続）（先物訂正返済入力）入力画面表示データ取得」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccFuturesCloseChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C226C702D8
     */
    protected WEB3SuccFuturesCloseChangeInputResponse getFuturesChangeClosingContractInputScreen(
        WEB3SuccFuturesCloseChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFuturesChangeClosingContractInputScreen("
            + "WEB3SuccFuturesCloseChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        l_request.validate();

        //連続注文マネージャを取得する
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //get先物OP予約注文単位(long)
        //注文ID：　@リクエストデータ.ID
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));
        //get親注文の注文単位
        IfoOrderUnit l_ifoOrderUnit = l_orderUnit.getParentOrderUnit();
        //get補助口座
        SubAccount l_subAccount = this.getSubAccount();

        //validate連続注文
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        l_orderManager.validateSuccOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.FUTURES,
            l_rsvIfoOrderUnitRow.getReserveOrderTradingType(),
            l_ifoOrderUnit);

        //get訂正返済入力画面(リクエストデータ : 株価指数先物訂正返済入力画面リクエスト)
        WEB3SuccFuturesCloseChangeInputResponse l_response =
            (WEB3SuccFuturesCloseChangeInputResponse)super.getCloseChangeInputScreen(l_request);

        //取扱可能注文条件
        WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.IFO,
                WEB3FuturesOptionDivDef.FUTURES,
                WEB3MarginTradingDivDef.DEFAULT);

        //先物OP取引銘柄
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl =
            (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct();
        //set取引最終日
        l_gentradeHandlingOrderCond.setTradingEndDate(l_ifoTradedProductImpl.getLastTradingDate());

        //連続注文共通情報
        l_response.succCommonInfo = l_orderUnit.createSuccCommonInfo();
        //単価調整値情報
        l_response.priceAdjustmentValueInfo = l_orderUnit.createSuccPriceAdjustmentValueInfo();
        //執行条件一覧
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};
        //発注条件区分一覧
        l_response.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};
        //Ｗ指値用執行条件一覧
        l_response.wlimitExecCondList = null;

        //is出来るまで注文取扱可能<取引最終日考慮>
        boolean l_blnDateConsidering =
            l_gentradeHandlingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering();
        if (l_blnDateConsidering)
        {
            //有効期限開始日 = get出来るまで注文開始日<取引最終日考慮>
            l_response.expirationStartDate =
                l_gentradeHandlingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(null);
            //有効期限最終日 = get出来るまで注文最終日<取引最終日考慮>
            l_response.expirationEndDate =
                l_gentradeHandlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(null);
            //有効期限内祝日一覧 = get注文期限内祝日一覧
            l_response.holidayList = l_gentradeHandlingOrderCond.getExpirationDateHoliday();
        }
        else
        {
            //is出来るまで注文取扱可能<取引最終日考慮>( ) == falseの場合はnullをセット
            //有効期限開始日 = null
            l_response.expirationStartDate = null;
            //有効期限最終日 = null
            l_response.expirationEndDate = null;
            //有効期限内祝日一覧 = null
            l_response.holidayList = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get訂正対象注文単位)<BR>
     * 訂正対象の注文単位オブジェクトを取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@先物OP予約注文単位を取得する。<BR>
     * 　@連続注文マネージャImpl.get先物OP予約注文単位(リクエストデータ.ID)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ２）　@先物OP注文単位オブジェクトを生成する。<BR>
     * 　@連続注文マネージャImpl.create先物OP注文単位(１）の戻り値)を<BR>
     * 　@コールし、戻り値を返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 47BB74DC020D
     */
    protected IfoOrderUnit getChangeOrderUnit(WEB3FuturesCloseMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderUnit(WEB3FuturesCloseMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //連続注文マネージャを取得する
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //先物OP予約注文単位を取得する
        //注文ID：　@リクエストデータ.ID
        WEB3ToSuccIfoOrderUnitImpl l_orderUnit =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));

        //先物OP注文単位オブジェクトを生成する
        IfoOrderUnit l_ifoOrderUnit = l_orderManager.createIfoOrderUnit(l_orderUnit);

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnit;
    }

    /**
     * (validate注文訂正可能)<BR>
     * 訂正対象注文が、訂正可能な状態かどうかをチェックする。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@先物OP予約注文単位を取得する。<BR>
     * 　@連続注文マネージャImpl.get先物OP予約注文単位(引数の注文単位.注文ID)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ２）　@１）の戻り値.validate訂正可能状態()をコールする。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 先物OP注文単位オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BB921C0054
     */
    protected void validateOrderForChangeability(IfoOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //連続注文マネージャを取得する
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //先物OP予約注文単位を取得する
        //注文ID：　@リクエストデータ.ID
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit = l_orderManager.getReserveIfoOrderUnit(l_orderUnit.getOrderId());

        //validate訂正可能状態
        l_ifoOrderUnit.validateOrderForChangeability();
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create建玉明細ByOrder)<BR>
     * 引数の注文単位に関連する建玉明細の一覧を作成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@先物OP予約注文単位を取得する。<BR>
     * 　@連続注文マネージャImpl.get先物OP予約注文単位(注文単位.注文ID)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ２）　@連続注文マネージャ.create建玉明細ByOrder(１）の戻り値)を<BR>
     * 　@コールし、戻り値を返却する。<BR>
     * 　@※nullが返却された場合、<BR>
     * 　@　@「予約決済対象建玉は別注文により決済済」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_03066<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 先物OP注文単位オブジェクト。<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 47BBCA7F00B6
     */
    protected WEB3FuturesOptionsContractUnit[] createContractUnitByOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractUnitByOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //連続注文マネージャを取得する
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //先物OP予約注文単位を取得する
        //注文ID：　@リクエストデータ.ID
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit = l_orderManager.getReserveIfoOrderUnit(l_orderUnit.getOrderId());

        //create建玉明細ByOrder
        WEB3FuturesOptionsContractUnit[] l_contractUnits =
            l_orderManager.createIfoContractUnitByOrder(l_ifoOrderUnit);

        if (l_contractUnits == null)
        {
            log.debug("予約決済対象建玉は別注文により決済済です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03066,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "予約決済対象建玉は別注文により決済済です。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
}
@
