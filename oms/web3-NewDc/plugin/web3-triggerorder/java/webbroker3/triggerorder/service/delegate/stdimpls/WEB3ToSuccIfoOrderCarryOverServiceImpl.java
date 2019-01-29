head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccIfoOrderCarryOverServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP連続注文繰越サービスImpl（WEB3ToSuccIfoOrderCarryOverServiceImpl.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 金傑 (中訊) 新規作成 モデルNo.276
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccIfoOrderCarryOverService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (先物OP連続注文繰越サービスImpl)<BR>
 * 先物OP連続注文繰越サービス実装クラス<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3ToSuccIfoOrderCarryOverServiceImpl
    implements WEB3ToSuccIfoOrderCarryOverService
{

    /**
     * ログ出力ユーティリティ。 <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3ToSuccIfoOrderCarryOverServiceImpl.class);

    /**
     * @@roseuid 47FDBE3F02FD
     */
    public WEB3ToSuccIfoOrderCarryOverServiceImpl()
    {

    }

    /**
     * (exec連続注文繰越)<BR>
     * 連続注文の繰越処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連続注文繰越）exec連続注文繰越」参照。
     * @@param l_carryOverOriginalParentOrderUnit - (繰越元の親注文の注文単位)<BR>
     * 繰越元の親注文の注文単位<BR>
     * @@param l_carryOverAfterParentOrderUnit - (繰越後の親注文の注文単位)<BR>
     * 繰越後の親注文の注文単位<BR>
     * @@param l_lisRsvIfoOrderUnits - (予約注文単位一覧)<BR>
     * 予約注文単位一覧<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D103F6021B
     */
    public void execToSuccOrderCarryOver(
        IfoOrderUnit l_carryOverOriginalParentOrderUnit,
        IfoOrderUnit l_carryOverAfterParentOrderUnit,
        List l_lisRsvIfoOrderUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execToSuccOrderCarryOver(IfoOrderUnit, IfoOrderUnit, List)";
        log.entering(STR_METHOD_NAME);
        if (l_lisRsvIfoOrderUnits == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        //OP注文マネージャ
        WEB3OptionOrderManagerImpl l_optionOrderManager =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getOrderManager();
        // 連続注文マネージャImplを取得
        WEB3ToSuccOrderManagerImpl l_toSuccOrderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // 先物OP予約注文更新サービスを取得する。
        WEB3ToSuccReservationIfoOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        // 繰越した予約注文単位
        WEB3ToSuccIfoOrderUnitImpl l_afterToSuccIfoOrderUnit = null;
        boolean l_blnIsSetReserveOrderExistFlag = false;
        int l_intIfoOrderUnitsCnt = l_lisRsvIfoOrderUnits.size();
        for (int i = 0; i < l_intIfoOrderUnitsCnt; i++)
        {
            // get先物OP予約注文単位
            // 先物OP予約注文単位を取得する。
            // [引数]
            // 注文ID：　@処理対象の要素.注文ID
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit = l_toSuccOrderManager.getReserveIfoOrderUnit(
                ((RsvIfoOrderUnitRow)l_lisRsvIfoOrderUnits.get(i)).getOrderId());

            OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;

            // is繰越対象予約注文
            // 繰越対象注文であるか判定する。
            //  [引数]
            //   注文単位：　@get先物OP予約注文単位()の戻り値
            boolean l_blnIsCarryOverReserve =
                l_toSuccOrderManager.isCarryoverReserveIfoOrderUnit(l_toSuccIfoOrderUnit);

            // 繰越対象外注文(is繰越対象注文() == false)の場合
            if (!l_blnIsCarryOverReserve)
            {

                RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
                    (RsvIfoOrderUnitRow)l_toSuccIfoOrderUnit.getDataSourceObject();
                RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams(l_rsvIfoOrderUnitRow);

                // 注文エラー理由コードに"その他エラー"をセットする
                l_rsvIfoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.OTHRE_ERROR);

                // update予約注文データ
                // [引数]
                // 先物OP予約注文単位行：　@get先物OP予約注文単位()の戻り値.getDataSourceObject()(*)
                // 先物OP予約注文履歴行：　@null
                // (*)注文エラー理由コードに"その他エラー"をセットする。
                l_orderUpdateService.updateReserveOrderData(
                    l_rsvIfoOrderUnitParams, null);
                continue;
            }

            // 注文IDを取得する。
            long l_lngNewOrderId = l_optionOrderManager.createNewOrderId();
            try
            {
                // 処理対象の予約注文.注文カテゴリ == "OP新規建注文"の場合
                if (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_toSuccIfoOrderUnit.getOrderCateg()))
                {
                    l_orderManagerPersistenceContext = OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER;
                    // オプション新規建注文の登録処理を行う。
                    // insertOP新規建繰越注文
                    // [引数]
                    //  注文ID：　@createNewOrderId()の戻り値
                    //  繰越元予約注文単位：　@get先物OP予約注文単位()の戻り値
                    //  繰越後の親注文単位：　@パラメータ.繰越後の親注文単位
                    this.insertOptionOpenContractCarryOrder(
                        l_lngNewOrderId, l_toSuccIfoOrderUnit, l_carryOverAfterParentOrderUnit);
                    l_blnIsSetReserveOrderExistFlag = true;
                }
                // 処理対象の予約注文.注文カテゴリ == "OP返済注文"の場合
                else if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_toSuccIfoOrderUnit.getOrderCateg()))
                {
                    l_orderManagerPersistenceContext = OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER;
                    // オプション返済注文の登録処理を行う。
                    // insertOP新規建繰越注文
                    // [引数]
                    //  注文ID：　@createNewOrderId()の戻り値
                    //  繰越元予約注文単位：　@get先物OP予約注文単位()の戻り値
                    //  繰越後の親注文単位：　@パラメータ.繰越後の親注文単位
                    this.insertOptionCloseContractCarryOrder(
                        l_lngNewOrderId, l_toSuccIfoOrderUnit, l_carryOverAfterParentOrderUnit);
                    l_blnIsSetReserveOrderExistFlag = true;
                }
                // 処理対象の予約注文.注文カテゴリ == "先物新規建注文"の場合
                else if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_toSuccIfoOrderUnit.getOrderCateg()))
                {
                    l_orderManagerPersistenceContext = OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER;
                    // 先物新規建注文の登録処理を行う。
                    // [引数]
                    // 　@注文ID：　@createNewOrderId()の戻り値
                    // 　@繰越元予約注文単位：　@get先物OP予約注文単位()の戻り値
                    // 　@繰越後の親注文単位：　@パラメータ.繰越後の親注文単位

                    this.insertFuturesOpenContractCarryOrder(
                        l_lngNewOrderId, l_toSuccIfoOrderUnit, l_carryOverAfterParentOrderUnit);
                    l_blnIsSetReserveOrderExistFlag = true;
                }
                // 処理対象の予約注文.注文カテゴリ == "先物返済注文"の場合
                else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_toSuccIfoOrderUnit.getOrderCateg()))
                {
                    l_orderManagerPersistenceContext = OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER;
                    // 先物返済注文の登録処理を行う。。
                    // [引数]
                    // 　@注文ID：　@createNewOrderId()の戻り値
                    // 　@繰越元予約注文単位：　@get先物OP予約注文単位()の戻り値
                    // 　@繰越後の親注文単位：　@パラメータ.繰越後の親注文単位
                    this.insertFuturesCloseContractCarryOrder(
                        l_lngNewOrderId, l_toSuccIfoOrderUnit, l_carryOverAfterParentOrderUnit);
                    l_blnIsSetReserveOrderExistFlag = true;
                }
            }
            // insert処理中に業務エラーが発生した場合
            catch (WEB3BaseException l_ex)
            {
                RsvIfoOrderUnitRow l_beforeRsvIfoOrderUnitRow =
                    (RsvIfoOrderUnitRow)l_toSuccIfoOrderUnit.getDataSourceObject();
                RsvIfoOrderUnitParams l_beforeRsvIfoOrderUnitParams =
                    new RsvIfoOrderUnitParams(l_beforeRsvIfoOrderUnitRow);

                // get注文エラー理由コード
                String l_strErrorCode =
                    l_optionOrderManager.getErrorReasonCode(l_ex.getErrorInfo().getErrorCode());

                // 注文エラー理由コードにget注文エラー理由コード()の戻り値をセットする。
                l_beforeRsvIfoOrderUnitParams.setErrorReasonCode(l_strErrorCode);

                // update予約注文データ
                // [引数]
                // 先物OP予約注文単位行：　@get先物OP予約注文単位()の戻り値.getDataSourceObject()
                // 先物OP予約注文履歴行：　@null
                l_orderUpdateService.updateReserveOrderData(
                    l_beforeRsvIfoOrderUnitParams, null);

                continue;
            }
            // get先物OP予約注文単位
            // 繰越した予約注文単位を取得する。
            //　@[引数]
            //　@　@注文ID：　@createNewOrderId()の戻り値
            l_afterToSuccIfoOrderUnit =
                l_toSuccOrderManager.getReserveIfoOrderUnit(l_lngNewOrderId);

            // notifyルールエンジンサーバ
            // [引数]
            // 注文単位：　@get先物OP予約注文単位()の戻り値(*)
            // 処理：
            // 　@[処理対象の要素.注文カテゴリ == "先物新規建注文" or "OP新規建注文"の場合]
            // 　@　@NEW_OPEN_CONTRACT_ORDER
            //  　@[処理対象の要素.注文カテゴリ == "先物返済注文" or "OP返済注文"の場合]
            //　@　@NEW_SETTLE_CONTRACT_ORDER
            l_optionOrderManager.notifyRLS(l_afterToSuccIfoOrderUnit, l_orderManagerPersistenceContext);
        }

        // set予約注文設定To先物OP親注文
        // [引数]
        // 親注文の注文単位：　@繰越後の親注文単位
        if (l_blnIsSetReserveOrderExistFlag)
        {
            l_toSuccOrderManager.setReserveOrderSettingToIfoParentOrder(l_carryOverAfterParentOrderUnit);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert先物新規建繰越注文)<BR>
     * 先物新規建注文の登録処理を行う。<BR>
     * <BR>
     * １）　@DBに繰越注文の登録を行う。<BR>
     * 　@連続注文マネージャ.submit先物OP新規建繰越予約注文()をcallする。<BR>
     * 　@[引数]<BR>
     * 　@　@注文ID：　@パラメータ.注文ID<BR>
     * 　@　@繰越元予約注文単位：　@パラメータ.繰越元予約注文単位<BR>
     * 　@　@繰越後の親注文単位：　@パラメータ.繰越後の親注文単位<BR>
     * @@param l_lngOrderID - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_carryOverOriginalToSuccIfoOrderUnit - (繰越元の予約注文単位)<BR>
     * 繰越元の予約注文単位<BR>
     * @@param l_carryOverAfterParentOrderUnit - (繰越後の親注文単位)<BR>
     * 繰越後の親注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D12CDA00ED
     */
    public void insertFuturesOpenContractCarryOrder(
        long l_lngOrderID,
        WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit,
        IfoOrderUnit l_carryOverAfterParentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertFuturesOpenContractCarryOrder(long, WEB3ToSuccIfoOrderUnitImpl, IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        // 連続注文マネージャImplを取得
        WEB3ToSuccOrderManagerImpl l_toSuccOrderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // １） DBに繰越注文の登録を行う。
        // 連続注文マネージャ.submit先物OP新規建繰越予約注文()をcallする。
        // [引数]
        //   注文ID：　@パラメータ.注文ID
        //   繰越元予約注文単位：　@パラメータ.繰越元予約注文単位
        //   繰越後の親注文単位：　@パラメータ.繰越後の親注文単位
        l_toSuccOrderManager.submitIfoOpenContractCarryReserveOrder(
            l_lngOrderID, l_carryOverOriginalToSuccIfoOrderUnit, l_carryOverAfterParentOrderUnit);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertOP新規建繰越注文)<BR>
     * オプション新規建注文の登録処理を行う。<BR>
     * <BR>
     * １）　@DBに繰越注文の登録を行う。<BR>
     * 　@連続注文マネージャ.submit先物OP新規建繰越予約注文()をcallする。<BR>
     * 　@[引数]<BR>
     * 　@　@注文ID：　@パラメータ.注文ID<BR>
     * 　@　@繰越元予約注文単位：　@パラメータ.繰越元予約注文単位<BR>
     * 　@　@繰越後の親注文単位：　@パラメータ.繰越後の親注文単位<BR>
     * @@param l_lngOrderID - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_carryOverOriginalToSuccIfoOrderUnit - (繰越元の予約注文単位)<BR>
     * 繰越元の予約注文単位<BR>
     * @@param l_carryOverAfterParentOrderUnit - (繰越後の親注文単位)<BR>
     * 繰越後の親注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D60C450046
     */
    public void insertOptionOpenContractCarryOrder(
        long l_lngOrderID,
        WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit,
        IfoOrderUnit l_carryOverAfterParentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertOptionOpenContractCarryOrder(long, WEB3ToSuccIfoOrderUnitImpl, IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        // 連続注文マネージャImplを取得
        WEB3ToSuccOrderManagerImpl l_toSuccOrderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // １） DBに繰越注文の登録を行う。
        // 連続注文マネージャ.submit先物OP新規建繰越予約注文()をcallする。
        // [引数]
        //   注文ID：　@パラメータ.注文ID
        //   繰越元予約注文単位：　@パラメータ.繰越元予約注文単位
        //   繰越後の親注文単位：　@パラメータ.繰越後の親注文単位
        l_toSuccOrderManager.submitIfoOpenContractCarryReserveOrder(
            l_lngOrderID, l_carryOverOriginalToSuccIfoOrderUnit, l_carryOverAfterParentOrderUnit);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert先物返済繰越注文)<BR>
     * 先物返済注文の登録処理を行う。<BR>
     * <BR>
     * １）　@返済建玉エントリを作成する。<BR>
     * 　@１－１）　@反対売買の場合<BR>
     * 　@　@（パラメータ.繰越元予約注文単位.is反対売買取引() == true）<BR>
     * <BR>
     * 　@　@１－１－１）　@返済建玉を作成する。<BR>
     * 　@　@　@返済建玉インスタンスを生成し、以下のプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@　@・返済建玉.注文数量：　@パラメータ.繰越元予約注文単位.注文数量<BR>
     * 　@　@１－１－２）　@返済建玉エントリを作成する。<BR>
     * 　@　@　@連続注文マネージャImpl.create返済建玉エントリ()をコールする。 <BR>
     * <BR>
     * 　@　@　@[引数]  <BR>
     * 　@　@　@　@返済建玉：　@１－１－１）の戻り値 <BR>
     * <BR>
     * <BR>
     * 　@１－２）　@１－１）以外の場合<BR>
     * 　@　@１－２－１）　@予約建玉返済指定情報を取得する。<BR>
     * 　@　@　@パラメータ.繰越元予約注文単位.注文IDに該当する<BR>
     * 　@　@　@予約建玉返済指定情報を取得する。<BR>
     * 　@　@　@　@※返済連番の昇順sort指定<BR>
     * <BR>
     * 　@　@１－２－２）　@１－２－１）の要素数分LOOP処理<BR>
     * 　@　@　@返済建玉エントリを生成し、要素数分の配列を作成する。<BR>
     * <BR>
     * 　@　@　@　@[コンストラクタ引数]<BR>
     * 　@　@　@　@　@arg0（=建玉ID）：　@処理対象の要素.建玉ID<BR>
     * 　@　@　@　@　@arg1（=数量）：　@処理対象の要素.返済注文数量<BR>
     * <BR>
     * <BR>
     * ２）　@DBに繰越注文の登録を行う。<BR>
     * 　@連続注文マネージャ.submit先物OP返済繰越予約注文()をcallする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@注文ID：　@パラメータ.注文ID<BR>
     * 　@　@繰越元予約注文単位：　@パラメータ.繰越元予約注文単位<BR>
     * 　@　@繰越後の親注文単位：　@パラメータ.繰越後の親注文単位<BR>
     * 　@　@返済建玉エントリ：　@１）で生成した返済建玉エントリ<BR>
     * @@param l_lngOrderID - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_carryOverOriginalToSuccIfoOrderUnit - (繰越元の予約注文単位)<BR>
     * 繰越元の予約注文単位<BR>
     * @@param l_carryOverAfterParentOrderUnit - (繰越後の親注文単位)<BR>
     * 繰越後の親注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D60C76020F
     */
    public void insertFuturesCloseContractCarryOrder(
        long l_lngOrderID,
        WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit,
        IfoOrderUnit l_carryOverAfterParentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertFuturesCloseContractCarryOrder(" +
            "long, WEB3ToSuccIfoOrderUnitImpl, IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        SettleContractEntry[] l_settleContractEntries = null;

        // 連続注文マネージャImplを取得
        WEB3ToSuccOrderManagerImpl l_toSuccorderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // １）　@返済建玉エントリを作成する。
        //  １－１）　@反対売買の場合
        // 　@（パラメータ.繰越元予約注文単位.is反対売買取引() == true）
        if (l_carryOverOriginalToSuccIfoOrderUnit.isReversingTrade())
        {
            // １－１－１）　@返済建玉を作成する。
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];

            l_closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();

            // ・返済建玉.注文数量：　@パラメータ.繰越元予約注文単位.注文数量
            l_closeMarginContractUnits[0].contractOrderQuantity = WEB3StringTypeUtility.formatNumber(
                l_carryOverOriginalToSuccIfoOrderUnit.getQuantity());
            // １－１－２）　@返済建玉エントリを作成する。
            // 連続注文マネージャImpl.create返済建玉エントリ()をコールする。
            // [引数]
            //   返済建玉：　@１－１－１）の戻り値
            l_settleContractEntries =
                l_toSuccorderManager.createSettleContractEntry(l_closeMarginContractUnits);
        }
        // １－２）　@反対売買取引以外の場合
        else
        {
            try
            {
                // １－２－１）　@予約建玉返済指定情報を取得する。
                // パラメータ.繰越元予約注文単位.注文IDに該当する
                // 予約建玉返済指定情報を取得する。
                // ※返済連番の昇順sort指定
                String l_strWhere = " order_id = ? ";
                Object[] l_bindVars = {new Long(l_carryOverOriginalToSuccIfoOrderUnit.getOrderId())};
                String l_strSort = "closing_serial_no asc";

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisRsvIfoClosingContractSpecs = l_queryProcessor.doFindAllQuery(
                    RsvIfoClosingContractSpecRow.TYPE,
                    l_strWhere,
                    l_strSort,
                    null,
                    l_bindVars);

                // １－２－２）　@１－２－１）の要素数分LOOP処理
                int l_intRsvIfoClosingContractSpecsCnt = l_lisRsvIfoClosingContractSpecs.size();
                l_settleContractEntries = new SettleContractEntry[l_intRsvIfoClosingContractSpecsCnt];
                for (int i = 0; i < l_intRsvIfoClosingContractSpecsCnt; i++)
                {
                    // 返済建玉エントリを生成し、要素数分の配列を作成する。
                    // [コンストラクタ引数]
                    //   arg0（=建玉ID）：　@処理対象の要素.建玉ID
                    //   arg1（=数量）：　@処理対象の要素.返済注文数量

                    l_settleContractEntries[i] = new SettleContractEntry(
                        ((RsvIfoClosingContractSpecRow)l_lisRsvIfoClosingContractSpecs.get(i)).getContractId(),
                        ((RsvIfoClosingContractSpecRow)l_lisRsvIfoClosingContractSpecs.get(i)).getQuantity());
                }
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        // ２）　@DBに繰越注文の登録を行う。
        // 連続注文マネージャ.submit先物OP返済繰越予約注文()をcallする。
        // [引数]
        // 注文ID：　@パラメータ.注文ID
        // 繰越元予約注文単位：　@パラメータ.繰越元予約注文単位
        // 繰越後の親注文単位：　@パラメータ.繰越後の親注文単位
        // 返済建玉エントリ：　@１）で生成した返済建玉エントリ
        l_toSuccorderManager.submitIfoCloseContractCarryReserveOrder(
            l_lngOrderID,
            l_carryOverOriginalToSuccIfoOrderUnit,
            l_carryOverAfterParentOrderUnit,
            l_settleContractEntries);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertOP返済繰越注文)<BR>
     * オプション返済注文の登録処理を行う。<BR>
     * <BR>
     * １）　@返済建玉エントリを作成する。<BR>
     * 　@１－１）　@反対売買の場合<BR>
     * 　@　@（パラメータ.繰越元予約注文単位.is反対売買取引() == true）<BR>
     * <BR>
     * 　@　@１－１－１）　@返済建玉を作成する。<BR>
     * 　@　@　@返済建玉インスタンスを生成し、以下のプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@　@・返済建玉.注文数量：　@パラメータ.繰越元予約注文単位.注文数量<BR>
     * 　@　@１－１－２）　@返済建玉エントリを作成する。<BR>
     * 　@　@　@連続注文マネージャImpl.create返済建玉エントリ()をコールする。<BR>
     * <BR>
     * 　@　@　@[引数]  <BR>
     * 　@　@　@　@返済建玉：　@１－１－１）の戻り値 <BR>
     * <BR>
     * <BR>
     * 　@１－２）　@１－１）以外の場合<BR>
     * 　@　@１－２－１）　@予約建玉返済指定情報を取得する。<BR>
     * 　@　@　@パラメータ.繰越元予約注文単位.注文IDに該当する<BR>
     * 　@　@　@予約建玉返済指定情報を取得する。<BR>
     * 　@　@　@　@※返済連番の昇順sort指定<BR>
     * <BR>
     * 　@　@１－２－２）　@１－２－１）の要素数分LOOP処理<BR>
     * 　@　@　@返済建玉エントリを生成し、要素数分の配列を作成する。<BR>
     * <BR>
     * 　@　@　@　@[コンストラクタ引数]<BR>
     * 　@　@　@　@　@arg0（=建玉ID）：　@処理対象の要素.建玉ID<BR>
     * 　@　@　@　@　@arg1（=数量）：　@処理対象の要素.返済注文数量<BR>
     * <BR>
     * <BR>
     * ２）　@DBに繰越注文の登録を行う。<BR>
     * 　@連続注文マネージャ.submit先物OP返済繰越予約注文()をcallする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@注文ID：　@パラメータ.注文ID<BR>
     * 　@　@繰越元予約注文単位：　@パラメータ.繰越元予約注文単位<BR>
     * 　@　@繰越後の親注文単位：　@パラメータ.繰越後の親注文単位<BR>
     * 　@　@返済建玉エントリ：　@１）で生成した返済建玉エントリ<BR>
     * @@param l_lngOrderID - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_carryOverOriginalToSuccIfoOrderUnit - (繰越元の予約注文単位)<BR>
     * 繰越元の予約注文単位<BR>
     * @@param l_carryOverAfterParentOrderUnit - (繰越後の親注文単位)<BR>
     * 繰越後の親注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D875AD00E9
     */
    public void insertOptionCloseContractCarryOrder(
        long l_lngOrderID,
        WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit,
        IfoOrderUnit l_carryOverAfterParentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertOptionCloseContractCarryOrder(" +
            "long, WEB3ToSuccIfoOrderUnitImpl, IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        SettleContractEntry[] l_settleContractEntries = null;

        // 連続注文マネージャImplを取得
        WEB3ToSuccOrderManagerImpl l_toSuccorderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // １）　@返済建玉エントリを作成する。
        //  １－１）　@反対売買の場合
        // 　@（パラメータ.繰越元予約注文単位.is反対売買取引() == true）
        if (l_carryOverOriginalToSuccIfoOrderUnit.isReversingTrade())
        {
            // １－１－１）　@返済建玉を作成する。
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnit =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];

            l_closeMarginContractUnit[0] = new WEB3FuturesOptionsCloseMarginContractUnit();

            // ・返済建玉.注文数量：　@パラメータ.繰越元予約注文単位.注文数量
            l_closeMarginContractUnit[0].contractOrderQuantity = WEB3StringTypeUtility.formatNumber(
                l_carryOverOriginalToSuccIfoOrderUnit.getQuantity());
            // １－１－２）　@返済建玉エントリを作成する。
            // 連続注文マネージャImpl.create返済建玉エントリ()をコールする。
            // [引数]
            //   返済建玉：　@１－１－１）の戻り値
            l_settleContractEntries =
                l_toSuccorderManager.createSettleContractEntry(l_closeMarginContractUnit);
        }
        // １－２）　@反対売買取引以外の場合
        else
        {
            try
            {
                // １－２－１）　@予約建玉返済指定情報を取得する。
                // パラメータ.繰越元予約注文単位.注文IDに該当する
                // 予約建玉返済指定情報を取得する。
                // ※返済連番の昇順sort指定
                String l_strWhere = " order_id = ? ";
                String l_strSort = "closing_serial_no asc";
                Object[] l_bindVars = {new Long(l_carryOverOriginalToSuccIfoOrderUnit.getOrderId())};

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisRsvIfoClosingContractSpecs = l_queryProcessor.doFindAllQuery(
                    RsvIfoClosingContractSpecRow.TYPE,
                    l_strWhere,
                    l_strSort,
                    null,
                    l_bindVars);

                // １－２－２）　@１－２－１）の要素数分LOOP処理
                int l_intRsvIfoClosingContractSpecsCnt = l_lisRsvIfoClosingContractSpecs.size();
                l_settleContractEntries = new SettleContractEntry[l_intRsvIfoClosingContractSpecsCnt];
                for (int i = 0; i < l_intRsvIfoClosingContractSpecsCnt; i++)
                {
                    // 返済建玉エントリを生成し、要素数分の配列を作成する。
                    // [コンストラクタ引数]
                    //   arg0（=建玉ID）：　@処理対象の要素.建玉ID
                    //   arg1（=数量）：　@処理対象の要素.返済注文数量

                    l_settleContractEntries[i] = new SettleContractEntry(
                        ((RsvIfoClosingContractSpecRow)l_lisRsvIfoClosingContractSpecs.get(i)).getContractId(),
                        ((RsvIfoClosingContractSpecRow)l_lisRsvIfoClosingContractSpecs.get(i)).getQuantity());
                }
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        // ２）　@DBに繰越注文の登録を行う。
        // 連続注文マネージャ.submit先物OP返済繰越予約注文()をcallする。
        // [引数]
        // 注文ID：　@パラメータ.注文ID
        // 繰越元予約注文単位：　@パラメータ.繰越元予約注文単位
        // 繰越後の親注文単位：　@パラメータ.繰越後の親注文単位
        // 返済建玉エントリ：　@１）で生成した返済建玉エントリ
        l_toSuccorderManager.submitIfoCloseContractCarryReserveOrder(
            l_lngOrderID,
            l_carryOverOriginalToSuccIfoOrderUnit,
            l_carryOverAfterParentOrderUnit,
            l_settleContractEntries);

        log.exiting(STR_METHOD_NAME);
    }
}
@
