head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionChangeOpenContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）OP訂正新規建入力サービスImpl（WEB3ToSuccOptionChangeOpenContractInputServiceImpl.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/11 トウ鋒鋼 (中訊) 新規作成 モデル267
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;

import webbroker3.common.WEB3BaseException;
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
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeInputRequest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeOpenContractInputServiceImpl;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）OP訂正新規建入力サービスImpl)<BR>
 * （連続）OP訂正新規建入力サービス実装クラス<BR>
 *
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3ToSuccOptionChangeOpenContractInputServiceImpl
    extends WEB3OptionChangeOpenContractInputServiceImpl
    implements WEB3ToSuccOptionChangeOpenContractInputService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionChangeOpenContractInputServiceImpl.class);

    /**
     * @@roseuid 47FDBE400242
     */
    public WEB3ToSuccOptionChangeOpenContractInputServiceImpl()
    {

    }

    /**
     * （連続）株価指数オプション訂正新規建入力サービス処理を実施する。<BR>
     * <BR>
     * this.create入力画面()をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A92193029D
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

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3SuccOptionsOpenChangeInputRequest)
        {
            l_response = this.createInputScreen((WEB3SuccOptionsOpenChangeInputRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get訂正対象注文単位)<BR>
     * 訂正対象の注文単位オブジェクトを取得する。  <BR>
     * （継承元クラスの同名メソッドのオーバーライド）  <BR>
     * <BR>
     * １）　@先物OP予約注文単位を取得する。  <BR>
     * 　@連続注文マネージャImpl.get先物OP予約注文単位(注文ID)をコールする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@注文ID ： リクエスト.ID <BR>
     * <BR>
     * ２）　@先物OP注文単位オブジェクトを生成する。  <BR>
     * 　@連続注文マネージャImpl.create先物OP注文単位(予約注文単位)をコールし、戻り値を返却する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@予約注文単位 ： １）の戻り値<BR>
     * @@param l_request - (株価指数オプション訂正新規建入力画面リクエスト)<BR>
     * 株価指数オプション訂正新規建入力画面リクエスト<BR>
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 47D71DEC00BB
     */
    protected IfoOrderUnit getChangeOrderUnit(WEB3OptionsOpenMarginChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderUnit(WEB3OptionsOpenMarginChangeInputRequest)";
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

        // 連続注文マネージャImplを取得
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // 先物OP予約注文単位を取得する。
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));

        // 先物OP注文単位オブジェクトを生成する。
        IfoOrderUnit l_ifoOrderUnit = l_orderManager.createIfoOrderUnit(l_ifoOrderUnitImpl);

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnit;
    }

    /**
     * (validate注文訂正可能)<BR>
     * 訂正対象注文が、訂正可能な状態かどうかをチェックする。  <BR>
     * （継承元クラスの同名メソッドのオーバーライド）  <BR>
     * <BR>
     * １）　@先物OP予約注文単位を取得する。  <BR>
     * 　@連続注文マネージャImpl.get先物OP予約注文単位(注文ID)をコールする。  <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@注文ID ： 引数のIfoOrderUnit.注文ID <BR>
     * <BR>
     * ２）　@１）の戻り値.validate訂正可能状態()をコールする。<BR>
     * @@param l_orderUnit - IfoOrderUnit<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D71DFB025D
     */
    protected void validateOrderForChangeability(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // 連続注文マネージャImplを取得
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // １）先物OP予約注文単位を取得する。
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl =
            l_orderManager.getReserveIfoOrderUnit(l_orderUnit.getOrderId());

        //２） １）の戻り値.validate訂正可能状態()をコールする。
        l_ifoOrderUnitImpl.validateOrderForChangeability();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create入力画面)<BR>
     * 入力画面表示処理 <BR>
     * <BR>
     * 「(（連続）OP訂正新規建入力)入力画面表示データ取得」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccOptionsOpenChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47D71E12011E
     */
    protected WEB3SuccOptionsOpenChangeInputResponse createInputScreen(
        WEB3SuccOptionsOpenChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createInputScreen(WEB3SuccOptionsOpenChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        // リクエストデータの整合性をチェックする。
        l_request.validate();

        // 連続注文マネージャImplを取得
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // get先物OP予約注文単位(long)
        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit =
            l_orderManager.getReserveIfoOrderUnit(Long.parseLong(l_request.id));

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
            (RsvIfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

        // get親注文の注文単位( )
        IfoOrderUnit l_parentOrderUnit = l_ifoOrderUnit.getParentOrderUnit();

        // get補助口座( )
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

        // validate連続注文(補助口座, ProductTypeEnum, String, String, OrderUnit)
        l_orderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.IFO,
            WEB3FuturesOptionDivDef.OPTION,
            l_rsvIfoOrderUnitRow.getReserveOrderTradingType(),
            l_parentOrderUnit);

        // create入力画面(リクエストデータ : 株価指数オプション訂正新規建入力画面リクエスト)
        WEB3SuccOptionsOpenChangeInputResponse l_response =
            (WEB3SuccOptionsOpenChangeInputResponse)super.createInputScreen(l_request);

        // 取扱可能注文条件オブジェクトを生成する。
        WEB3GentradeHandlingOrderCond l_handlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.IFO,
                WEB3FuturesOptionDivDef.OPTION,
                WEB3MarginTradingDivDef.DEFAULT);

        IfoTradedProduct l_tradedProduct = (IfoTradedProduct)l_ifoOrderUnit.getTradedProduct();

        // set取引最終日(取引最終日 : Date)
        l_handlingOrderCond.setTradingEndDate(l_tradedProduct.getLastTradingDate());

        // is出来るまで注文取扱可能<取引最終日考慮>( )
        boolean l_blnIsOrderUntilDeadLinePossibleHandling =
            l_handlingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering();

        Date l_datExpirationStartDate = null;
        Date l_datExpirationEndDate = null;
        Date[] l_datHolidayLists = null;
        // ＜分岐＞is出来るまで注文取扱可能<取引最終日考慮>( )　@==　@true　@の場合
        if (l_blnIsOrderUntilDeadLinePossibleHandling)
        {
            // get出来るまで注文開始日<取引最終日考慮>(出来るまで注文開始日 : Date)
            l_datExpirationStartDate =
                l_handlingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(null);

            // get出来るまで注文最終日<取引最終日考慮>(原注文発注日 : Date)
            l_datExpirationEndDate =
                l_handlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(null);

            // get注文期限内祝日一覧( )
            l_datHolidayLists = l_handlingOrderCond.getExpirationDateHoliday();
        }

        // ●（連続）株価指数先物訂正新規建入力画面レスポンス固有のプロパティ
        // 連続注文共通情報：　@先物OP予約注文単位.create連続注文共通情報()をセット。
        l_response.succCommonInfo = l_ifoOrderUnit.createSuccCommonInfo();

        // 単価調整値情報：　@　@先物OP予約注文単位.create単価調整値情報()をセット。
        l_response.priceAdjustmentValueInfo = l_ifoOrderUnit.createSuccPriceAdjustmentValueInfo();

        // ●異なる値をセットするプロパティ（再セット）
        // 執行条件一覧：　@"無条件"のみをセット。
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};

        // 発注条件区分一覧：　@"指定なし"のみをセット。
        l_response.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};

        // Ｗ指値用執行条件一覧：　@nullをセット。
        l_response.wlimitExecCondList = null;

        // 有効期限開始日：　@(*1)取扱可能注文条件.get出来るまで注文開始日<取引最終日考慮>()の戻り値をセット。
        l_response.expirationStartDate = l_datExpirationStartDate;

        // 有効期限最終日：　@(*1)取扱可能注文条件.get出来るまで注文最終日<取引最終日考慮>()の戻り値をセット。
        l_response.expirationEndDate = l_datExpirationEndDate;

        // 有効期限内祝日一覧：　@(*1)取扱可能注文条件.get注文期限内祝日一覧()の戻り値をセット。
        l_response.holidayList = l_datHolidayLists;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
