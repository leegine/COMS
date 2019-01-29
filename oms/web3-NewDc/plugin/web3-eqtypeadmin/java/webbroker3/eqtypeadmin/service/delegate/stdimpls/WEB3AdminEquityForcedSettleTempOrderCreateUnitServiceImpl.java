head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済仮注文作成一件サービスImpl(WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 何文敏(中訊) 新規作成 仕様変更モデルNo.131、モデルNo.146、モデルNo.142
Revision History : 2007/06/04 柴双紅(中訊) 仕様変更 モデルNo.151、No.153、No.156、No.158、ＤＢ更新仕様No.012
Revision History : 2007/06/13 柴双紅(中訊) ＤＢ更新仕様No.013
Revision History : 2007/10/09 何文敏(中訊) 仕様変更 モデルNo.165
Revision History : 2008/01/17 孟亞南(中訊) ＤＢ更新仕様No.018
Revision History : 2008/12/10 張少傑(中訊) 仕様変更 モデルNo.214
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ApproveStatusType;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExpirationReasonCodeDef;
import webbroker3.common.define.WEB3ForcedExpireType;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.eqtypeadmin.WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqDao;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqParams;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqRow;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleTempOrderCreateUnitService;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (強制決済仮注文作成一件サービスImpl)<BR>
 * 抽象クラス<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public abstract class WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl implements
    WEB3AdminEquityForcedSettleTempOrderCreateUnitService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl.class);

    /**
     * @@roseuid 462CA4230014
     */
    public WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl()
    {
     
    }
    
    /**
     * (exec仮注文作成)<BR>
     * 強制失効と、仮注文の登録を実施する。<BR>
     * <BR>
     * １）　@失効対象注文の取得<BR>
     * 　@商品管理（株式）データマネージャ.get決済中注文一覧()をcallする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@建株Row：　@引数.建株Rowオブジェクト<BR>
     * 　@　@is予約注文考慮要：　@引数.is連続注文取扱可能<BR>
     * <BR>
     * ２）　@強制失効の実施<BR>
     * 　@１）で取得したArrayListをLoopし、要素毎に失効処理を行う。<BR>
     * <BR>
     * 　@２－１）　@要素の型が”株式注文単位Row”の場合<BR>
     * 　@　@２－１－１）　@強制決済注文の判定<BR>
     * 　@　@　@拡張株式注文マネージャ.is強制決済注文() ＝ trueの場合、<BR>
     * 　@　@　@次の要素に処理を移行する。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@注文単位：　@拡張株式注文マネージャ.toOrderUnit(株式注文単位Row)<BR>
     * <BR>
     * 　@　@　@　@※強制決済注文は失効させない。<BR>
     * <BR>
     * 　@　@２－１－２）　@注文取引キューをロックする。<BR>
     * 　@　@　@株式発注サービス.lock株式注文取引キュー()をｃａｌｌする。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@注文単位：　@拡張株式注文マネージャ.toOrderUnit(株式注文単位Row)<BR>
     * <BR>
     * 　@　@２－１－３）　@市場未送信のキューを検索する。<BR>
     * 　@　@　@株式発注サービス.get株式注文取引キュー()をｃａｌｌする。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@注文単位：　@拡張株式注文マネージャ.toOrderUnit(株式注文単位Row)<BR>
     * <BR>
     * 　@　@２－１－４）　@エラー注文の登録<BR>
     * 　@　@　@現引・現渡注文、市場送信済みの返済注文は失効させないで、<BR>
     * 　@　@　@エラー注文の登録を行う。<BR>
     * <BR>
     * 　@　@　@株式注文単位Row.注文カテゴリ ＝ "現引・現渡注文"の場合、<BR>
     * 　@　@　@または、<BR>
     * 　@　@　@株式注文単位Row.注文カテゴリ ＝ "返済注文"かつ、<BR>
     * 　@　@　@！（株式注文単位Row.発注条件 ＝ "逆指値"かつ、<BR>
     * 　@　@　@　@拡張株式注文マネージャ.is未発注注文()＝true）かつ、<BR>
     * 　@　@　@２－１－３）の戻り値 ＝ nullの場合、<BR>
     * 　@　@　@this.insert強制決済エラー注文()をcallし、次の要素に処理を移行する。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@建株Row：　@引数.建株Row<BR>
     * 　@　@　@　@強制決済理由区分：　@引数.強制決済理由区分<BR>
     * 　@　@　@　@強制決済余力計算結果：　@引数.強制決済余力計算結果<BR>
     * 　@　@　@　@注文ID：　@株式注文単位Row.注文ID<BR>
     * 　@　@　@　@注文数量：　@株式注文単位Row.注文数量<BR>
     * 　@　@　@　@指値：　@null<BR>
     * 　@　@　@　@注文エラー理由コード：<BR>
     * 　@　@　@　@　@[現引・現渡注文の場合]<BR>
     * 　@　@　@　@　@　@"現引・現渡注文登録済エラー"<BR>
     * 　@　@　@　@　@[市場送信済の返済注文の場合]<BR>
     * 　@　@　@　@　@　@"建株残高不足エラー"<BR>
     * <BR>
     * 　@　@２－１－５）　@注文失効処理<BR>
     * 　@　@　@this.expire決済注文()をcallし、該当注文の失効処理を実施する。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@株式注文単位Row：　@処理対象の要素<BR>
     * <BR>
     * 　@２－２）　@要素の型が”株式予約注文単位Row”の場合<BR>
     * 　@　@　@this.expire予約決済注文()をcallし、該当注文の失効処理を実施する。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@株式予約注文単位Row：　@処理対象の要素<BR>
     * <BR>
     * ３）　@RACコンテキストに値を再セットする。<BR>
     * 　@※this.expire決済注文()内でコールしている失効通知一件サービスと紐付く<BR>
     * 　@※株式下り処理RACコンテキストインタセプタにてRACのクリアをしているため、再設定が必要。<BR>
     * <BR>
     * 　@３－１）　@RACコンテキストに口座IDをセットする。<BR>
     * 　@　@WEB3DescendRacCtxService.setAccountIdCtx()をcallする。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@引数.建株Row.口座ID<BR>
     * <BR>
     * ４）　@仮注文の作成<BR>
     * 　@this.submit返済仮注文()をcallし、仮注文を登録する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@建株Row：　@引数.建株Rowオブジェクト<BR>
     * 　@　@強制決済理由区分：　@引数.強制決済理由区分<BR>
     * 　@　@強制決済余力計算結果：　@引数.強制決済余力計算結果 <BR>
     * <BR>
     * @@param l_eqtypeContractRow - (建株Row)<BR>
     * 強制決済対象の建株Rowオブジェクト<BR>
     * @@param l_strForcedSettleReasonType - (強制決済理由区分)<BR>
     * 強制決済理由区分<BR>
     * @@param l_contractForceSettleResult - (強制決済余力計算結果)<BR>
     * 余力で計算した建玉強制決済結果オブジェクト<BR>
     * @@param l_blnIsSuccOrderHandling - (is連続注文取扱可能)<BR>
     * 連続注文が取扱可能かどうかのフラグ<BR>
     * <BR>
     * true：　@連続注文取扱可能<BR>
     * false：　@連続注文取扱不可<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46105996030D
     */
    public void execTempOrderCreation(
        EqtypeContractRow l_eqtypeContractRow,
        String l_strForcedSettleReasonType,
        WEB3TPContractForcedSettleResult l_contractForceSettleResult,
        boolean l_blnIsSuccOrderHandling) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execTempOrderCreation(" +
            "EqtypeContractRow, String, WEB3TPContractForcedSettleResult, boolean)";
        log.entering(STR_METHOD_NAME);

        // １）　@失効対象注文の取得
        //　@商品管理（株式）データマネージャ.get決済中注文一覧()をcallする。    
        ArrayList l_lisCloseOrders = WEB3AdminPMEquityDataManager.getCloseOrderList(
            l_eqtypeContractRow, l_blnIsSuccOrderHandling);

        // ２）　@強制失効の実施
        // １）で取得したArrayListをLoopし、要素毎に失効処理を行う。
        int l_intSize = l_lisCloseOrders.size();
        for (int i = 0; i < l_intSize; i++)
        {
            // ２－１）　@要素の型をinstanceofで判定し、失効メソッドを呼び分ける。
            // ２－１－１）　@要素の型が”株式注文単位Row”の場合
            if (l_lisCloseOrders.get(i) instanceof EqtypeOrderUnitRow)
            {
                EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_lisCloseOrders.get(i);

                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderMgr =
                    (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
                // 注文単位：　@拡張株式注文マネージャ.toOrderUnit(株式注文単位Row)
                OrderUnit l_orderUnit =
                    l_orderMgr.toOrderUnit(l_eqtypeOrderUnitRow);
                EqTypeOrderUnit l_eqTypeorderUnit = (EqTypeOrderUnit)l_orderUnit;
                // 拡張株式注文マネージャ.is強制決済注文()
                boolean l_blnIsForcedSettleOrder = l_orderMgr.isForcedSettleOrder(l_eqTypeorderUnit);
                // 拡張株式注文マネージャ.is強制決済注文() ＝ trueの場合、
                // 次の要素に処理を移行する。
                if (l_blnIsForcedSettleOrder)
                {
                    continue;
                }

                // ２－１－２）　@注文取引キューをロックする。
                // 株式発注サービス.lock株式注文取引キュー()をｃａｌｌする。
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(
                        WEB3EquityFrontOrderService.class);
                l_frontOrderService.lockHostEqtypeOrderAll(l_eqTypeorderUnit);

                // ２－１－３）　@市場未送信のキューを検索する。
                // 　@　@　@株式発注サービス.get株式注文取引キュー()をｃａｌｌする。
                HostEqtypeOrderAllParams l_hosrEqtypeOrderAll =
                    l_frontOrderService.getHostEqtypeOrderAll(l_eqTypeorderUnit);

                // ２－１－４）　@エラー注文の登録
                // 　@　@　@現引・現渡注文、市場送信済みの返済注文は失効させないで、
                // 　@　@　@エラー注文の登録を行う。
                // 株式注文単位Row.注文カテゴリ ＝ "現引・現渡注文"の場合、
                // または、 
                // 株式注文単位Row.注文カテゴリ ＝ "返済注文"かつ、
                // ！（株式注文単位Row.発注条件 ＝ "逆指値"かつ、
                // 拡張株式注文マネージャ.is未発注注文()＝true）かつ、
                // ２－１－３）の戻り値 ＝ nullの場合、
                // this.insert強制決済エラー注文()をcallし、次の要素に処理を移行する。
                boolean l_blnIsNotOrderedOrder = l_orderMgr.isNotOrderedOrder(l_eqTypeorderUnit);
                if (OrderCategEnum.SWAP_MARGIN.equals(l_eqtypeOrderUnitRow.getOrderCateg()))
                {
                    this.insertForceSettleErrorOrder(
                        l_eqtypeContractRow,
                        l_strForcedSettleReasonType,
                        l_contractForceSettleResult,
                        new Long(l_eqtypeOrderUnitRow.getOrderId()),
                        l_eqtypeOrderUnitRow.getQuantity(),
                        null,
                        WEB3ErrorReasonCodeDef.SWAP_MARGIN_REGISTED_ERROR);

                    continue;
                }
                if (OrderCategEnum.CLOSE_MARGIN.equals(l_eqtypeOrderUnitRow.getOrderCateg())
                    && !(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                        l_eqtypeOrderUnitRow.getOrderConditionType())
                    && l_blnIsNotOrderedOrder)
                    && l_hosrEqtypeOrderAll == null)
                {
                    this.insertForceSettleErrorOrder(
                        l_eqtypeContractRow,
                        l_strForcedSettleReasonType,
                        l_contractForceSettleResult,
                        new Long(l_eqtypeOrderUnitRow.getOrderId()),
                        l_eqtypeOrderUnitRow.getQuantity(),
                        null,
                        WEB3ErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR);

                    continue;
                }

                // ２－１－５）　@注文失効処理
                // this.expire決済注文()をcallし、該当注文の失効処理を実施する。
                this.expireSettleOrder(l_eqtypeOrderUnitRow);
            }

            // ２－２）　@要素の型が”株式予約注文単位Row”の場合
            // this.expire予約決済注文()をcallし、該当注文の失効処理を実施する。
            else if (l_lisCloseOrders.get(i) instanceof RsvEqOrderUnitRow)
            {
                //株式予約注文単位Row：　@処理対象の要素
                RsvEqOrderUnitRow l_rscEqOrderUnitRow =
                    (RsvEqOrderUnitRow)l_lisCloseOrders.get(i);
                // this.expire予約決済注文()をcallし、該当注文の失効処理を実施する
                this.expireRsvSettleOrder(l_rscEqOrderUnitRow);
            }             
        }

        //３）　@RACコンテキストに値を再セットする。
        // ※this.expire決済注文()内でコールしている失効通知一件サービスと紐付く
        // ※株式下り処理RACコンテキストインタセプタにてRACのクリアをしているため、再設定が必要。
        // ３－１）　@RACコンテキストに口座IDをセットする。
        //    WEB3DescendRacCtxService.setAccountIdCtx()をcallする。
        //    [引数]
        //       引数.建株Row.口座ID
        WEB3DescendRacCtxService l_descendRacCtxService =
            (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
        if (l_descendRacCtxService != null)
        {
            l_descendRacCtxService.setAccountIdCtx(l_eqtypeContractRow.getAccountId());
        }

        // ３）　@仮注文の作成
        // this.submit返済仮注文()をcallし、仮注文を登録する。
        this.submitRepayTempOrder(l_eqtypeContractRow,
            l_strForcedSettleReasonType,
            l_contractForceSettleResult);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit返済仮注文)<BR>
     * 返済仮注文作成処理を行う。<BR>
     * <BR>
     * 処理のシーケンスは、 <BR>
     * シーケンス図「（強制決済仮注文作成）submit返済仮注文」を参照。<BR>
     * =============================================== <BR>
     * 具体位置    :  1.17 （建株決済期日チェック）<BR>
     * （建株決済期日チェック）<BR>
     * 該当建株が決済期日を超えている場合、<BR>
     * 「建株決済期日チェックエラー」の例外をthrowする。<BR>
     * （発注日(get発注日()) ＞ 決済期日（建株Row.getCloseDate()<BR>
     *  class         :  WEB3BusinessLayerException       <BR>
     *  tag            :  BUSINESS_ERROR_00748      <BR>
     * =============================================== <BR>
     * @@param l_eqtypeContractRow - (建株Row)<BR>
     * 建株Row<BR>
     * @@param l_strForcedSettleResonDiv - (強制決済理由区分)<BR>
     * 強制決済理由区分<BR>
     * @@param l_contractForceSettleResult - (強制決済余力計算結果)<BR>
     * 建玉強制決済結果<BR>
     * @@throws WEB3BaseException
     * @@roseuid 460899CA004D
     */
    protected void submitRepayTempOrder(
        EqtypeContractRow l_eqtypeContractRow,
        String l_strForcedSettleResonDiv,
        WEB3TPContractForcedSettleResult l_contractForceSettleResult) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitRepayTempOrder(EqtypeContractRow, String, " +
            "WEB3TPContractForcedSettleResult)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3EquityProductManager l_productMgr =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        WEB3EquityTradedProduct l_tradedProduct;
        WEB3GentradeSubAccount l_subAccount;
        WEB3GentradeMarket l_market ;
        MainAccount l_mainAccount ;
        WEB3EquityContract l_contract;
        
        // 建株(建株ＩＤ : long)
        try
        {
            l_contract =
                (WEB3EquityContract) l_positionManager.getContract(l_eqtypeContractRow.getContractId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("__テーブルに該当するデータがありません。__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        // getQuantity()
        // 建株数量を取得する。
        double l_dbQuantity = l_contract.getQuantity();

        // getLockedQuantity()
        // 建株に対する、全てのロック中数量を取得する。 
        double l_dbLockedQuantity = l_contract.getLockedQuantity();

        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dbQuantity));
        BigDecimal l_bdLockedQuantity = new BigDecimal(String.valueOf(l_dbLockedQuantity));
        BigDecimal l_bdSubQuantity = l_bdQuantity.subtract(l_bdLockedQuantity);
        if (l_bdQuantity.compareTo(l_bdLockedQuantity) == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        // EqTypeSettleContractOrderEntry(arg0 : long, arg1 : double)
        // 決済建株エントリを生成する。
        EqTypeSettleContractOrderEntry l_entry =
            new EqTypeSettleContractOrderEntry(
                l_eqtypeContractRow.getContractId(), l_bdSubQuantity.doubleValue());

        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys =
            new EqTypeSettleContractOrderEntry[]{l_entry};
        try
        {
            // getTradedProduct
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productMgr.getTradedProduct(
                    l_eqtypeContractRow.getProductId(), l_eqtypeContractRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("__テーブルに該当するデータがありません。__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        double l_dblLimitPrice = 0;
        // get注文単価(取引銘柄, boolean)
        // 注文単価を取得する。
        if (ContractTypeEnum.SHORT.equals(l_eqtypeContractRow.getContractType()))
        {
            l_dblLimitPrice = this.getLimitPrice(l_tradedProduct, true, l_contract);
        }
        else
        {
            l_dblLimitPrice = this.getLimitPrice(l_tradedProduct, false, l_contract);
        }

        // get発注日( )
        // 発注日を取得する。
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        TaxTypeEnum l_taxType = l_eqtypeContractRow.getTaxType();
        // create返済注文内容(扱者 : 扱者, 決済建株エントリ : EqTypeSettleContractOrderEntry[],
        // 指値 : double, 執行条件 : EqTypeExecutionConditionType, 注文失効日 : Date, 税区分 : TaxTypeEnum,
        // 値段条件 : String, 発注条件 : String, 発注条件演算子 : String, 逆指値基準値 : double,
        // (W指値)訂正指値 : double, 決済順序区分 : String, 初回注文の注文単位ID : Long)
        WEB3MarginSettleContractOrderSpec l_settleContractOrderSpec =
            WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                null,
                l_settleContractOrderEntrys,
                l_dblLimitPrice,
                EqTypeExecutionConditionType.NONE,
                l_datBizDate,
                l_taxType,
                WEB3PriceConditionDef.DEFAULT,
                WEB3OrderingConditionDef.DEFAULT,
                null,
                0D,
                0D,
                null,
                null);

        // get補助口座(口座ID : long, 補助口座ID : long)
        // 補助口座を取得する。

        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_accMgr.getSubAccount(
                l_eqtypeContractRow.getAccountId(), l_eqtypeContractRow.getSubAccountId());
            // getMarket(市場ID : long)
            // 市場オブジェクトを取得する。
            
           l_market =
                (WEB3GentradeMarket)l_finObjectManager.getMarket(l_eqtypeContractRow.getMarketId());
        } 
        catch (NotFoundException l_ex)
        {
            log.error("__テーブルに該当するデータがありません。__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // create手数料(補助口座 : 補助口座, 市場コード : String, 発注日 : Date,
        // 注文チャネル : String, 信用取引区分 : String, 弁済期限値 : double, 注文カテゴリ : OrderCategEnum)
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
        WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(
            l_subAccount, 
            l_market.getMarketCode(), 
            l_datBizDate, 
            WEB3ChannelDef.CALL_CENTER, 
            l_eqtypeContractRow.getRepaymentType(), 
            l_eqtypeContractRow.getRepaymentNum(), 
            OrderCategEnum.CLOSE_MARGIN);

        // setIs指値(is指値 : boolean)
        // 手数料に指値/成行の別をsetする。 
        l_commission.setIsLimitPrice(l_settleContractOrderSpec.isLimitOrder());

        //  calc概算決済損益代金
        WEB3EquityRealizedProfitAndLossPrice l_profitAndLossPrice =
            l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                l_commission,
                l_dblLimitPrice,
                l_subAccount,
                l_tradedProduct,
                l_settleContractOrderEntrys,
                l_settleContractOrderSpec.getTotalQuantity(),
                null,
                0D,
                0D,
                true);

        // get顧客(口座ID : long)
        try
        {
            l_mainAccount =
                l_accMgr.getMainAccount(l_eqtypeContractRow.getAccountId());
        } 
        catch (NotFoundException l_ex)
        {
            log.error("__テーブルに該当するデータがありません。__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // getBranch( )
        //部店オブジェクトを取得する。
        l_mainAccount.getBranch();

        try
        {
            //validate決済期日超過(建株 : 建株)
            WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations =
                (WEB3EquityTypeOrderManagerReusableValidations)
                    WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            l_orderManagerReusableValidations.validateCloseDateExcess(l_contract);

            // validate返済注文
            // 発注審査を行う。
            EqTypeNewOrderValidationResult l_orderValidationResult = 
                l_orderManager.validateSettleContractOrder(
                    l_subAccount,
                    l_settleContractOrderSpec);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(), STR_METHOD_NAME);
            }
        }
        catch (Exception l_ex)
        {
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }

            //スローされた例外に対応する注文エラー理由コードを取得する。
            String l_strReasonCode = l_orderManager.getErrorReasonCode(l_errorInfo.getErrorCode());           

            // insert強制決済エラー注文
            // 強制決済エラー注文を登録する。
             this.insertForceSettleErrorOrder(
                 (EqtypeContractRow)l_contract.getDataSourceObject(),
                 l_strForcedSettleResonDiv,
                 l_contractForceSettleResult,
                 null,
                 l_settleContractOrderSpec.getTotalQuantity(),
                 new Double(l_dblLimitPrice),
                 l_strReasonCode);

             log.exiting(STR_METHOD_NAME);
             return;
        }

        //保証金預託率
        Double l_marginMaintenanceRate = null;
        //追証発生日
        Date l_datadditionalMarginDate = null;
        //追証発生日からの経過日数
        Integer l_additionalMarginAccruedDays = null;
        if (l_contractForceSettleResult != null)
        {
            l_marginMaintenanceRate = l_contractForceSettleResult.marginMaintenanceRate;
            l_datadditionalMarginDate = l_contractForceSettleResult.additionalMarginDate;
            l_additionalMarginAccruedDays = l_contractForceSettleResult.additionalMarginAccruedDays;
        }

        // setThreadLocalPersistenceEventInterceptor
        WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor l_forcedSettleTempOrderUpdateInterceptor =
            new WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor(
                l_settleContractOrderSpec,
                l_commission,
                l_profitAndLossPrice,
                l_eqtypeContractRow.getRepaymentType(),
                Double.parseDouble(l_eqtypeContractRow.getRepaymentNum() + ""),
                WEB3ChannelDef.CALL_CENTER,
                WEB3OrderRootDivDef.FORCED_SETTLE,
                l_strForcedSettleResonDiv,
                WEB3ApproveStatusType.UNAPPROVED,
                l_marginMaintenanceRate,
                l_datadditionalMarginDate,
                l_additionalMarginAccruedDays);
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_forcedSettleTempOrderUpdateInterceptor);
       
        // createNewOrderId( )
        // 新規注文IDを取得する。
        long l_lngNewOrderId = l_orderManager.createNewOrderId();

        // getTradingPassword( )
        // 取引パスワードを取得する。
        String l_strTradingPassword = l_mainAccount.getTradingPassword();

        // submitSettleContractOrder
        // 強制返済注文を登録する
        OrderSubmissionResult l_result = l_orderManager.submitSettleContractOrder(
            l_subAccount,
            l_settleContractOrderSpec,
            l_lngNewOrderId,
            l_strTradingPassword,
            true);

        if (l_result.getProcessingResult().isFailedResult())
        {
            log.error(l_result.getProcessingResult().toString());
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // 余力再計算(補助口座 : 補助口座)
        // 余力残高を更新する。
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);


        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (expire決済注文)<BR>
     * 失効通知により、引数の注文単位を失効させる。<BR>
     * <BR>
     * １）　@引数の注文単位に紐付く有効な予約注文を取得する。<BR>
     * 　@株式予約注文更新サービス.get有効予約注文単位一覧()をcallする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@親注文の注文ID：　@引数.注文単位Row.注文ID<BR>
     * <BR>
     * ２）　@失効通知をcallする。<BR>
     * 　@this.exec失効通知()をcallし、注文の失効処理を行う。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@注文単位Row：　@引数.注文単位Row<BR>
     * <BR>
     * ３）　@注文単位の更新<BR>
     * 　@３－１）　@失効後の注文の再取得<BR>
     * 　@　@拡張株式注文マネージャ.getOrderUnit()をcallし、<BR>
     * 　@　@失効後の注文単位を再取得する。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@orderUnitId：　@引数.注文単位Row.注文単位ID<BR>
     * <BR>
     * 　@３－２）　@強制失効区分に値をセットする。<BR>
     * 　@　@　@getOrderUnit()の戻り値の注文単位.強制失効区分に以下値をセットする。<BR>
     * <BR>
     * 　@　@　@　@＜強制失効区分にセットする値＞<BR>
     * 　@　@　@　@強制失効区分：　@"強制失効済"<BR>
     * <BR>
     * 　@３－３）　@注文単位を更新する<BR>
     * 　@　@　@拡張株式注文マネージャ.update注文データ()をcallする。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@注文単位：　@３－２）で値をセットした注文単位<BR>
     * 　@　@　@　@注文履歴：　@null<BR>
     * <BR>
     * ４）　@予約注文単位の更新<BR>
     * 　@１）の戻り値をLoopし、要素毎に値の更新を行う。<BR>
     * <BR>
     * 　@４－１）　@失効後の予約注文の再取得<BR>
     * 　@　@連続注文マネージャImpl.get株式予約注文単位()をcallし、<BR>
     * 　@　@株式予約注文単位を再取得する。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@注文ID：　@処理要素の株式予約注文単位Row.注文ID<BR>
     * <BR>
     * 　@４－２）　@強制失効区分に値をセットする。<BR>
     * 　@　@get株式予約注文単位()の戻り値の予約注文単位.強制失効区分に以下値をセットする。<BR>
     * <BR>
     * 　@　@　@　@＜強制失効区分にセットする値＞<BR>
     * 　@　@　@　@強制失効区分：　@"強制失効済"<BR>
     * <BR>
     * 　@４－３）　@予約注文単位を更新する。<BR>
     * 　@　@株式予約注文更新サービス.update予約注文データ()をcallする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@注文単位：　@４－２）で値をセットした注文単位<BR>
     * 　@　@　@注文履歴：　@null<BR>
     * <BR>
     * ５）　@株式注文取引キューの削除<BR>
     * 　@５－１）　@削除対象の判定<BR>
     * 　@　@注文単位.発注条件＝"逆指値"かつ、<BR>
     * 　@　@拡張株式注文マネージャ.is未発注注文()＝trueの場合、以降のキューの削除を行わない。<BR>
     * <BR>
     * 　@５－２）　@キューの論理削除<BR>
     * 　@　@株式注文取引キューの以下条件に該当するレコードを更新する。<BR>
     * 　@　@更新内容については、DB更新仕様「強制失効_株式注文取引キューテーブル.xls」参照。<BR>
     * <BR>
     * 　@　@　@＜論理削除する条件＞<BR>
     * 　@　@株式注文取引キューテーブル.データコード：　@"AI801"<BR>
     * 　@　@株式注文取引キューテーブル.証券会社コード：　@注文単位.getBranch().証券会社コード<BR>
     * 　@　@株式注文取引キューテーブル.部店コード：　@注文単位.getBranch().部店コード<BR>
     * 　@　@株式注文取引キューテーブル.識別コード：　@注文単位.識別コード<BR>
     * 　@　@株式注文取引キューテーブル.社内処理項目に含まれる注文Rev.(*1)：　@注文単位.注文Rev.<BR>
     * 　@　@株式注文取引キューテーブル.処理区分：　@"未処理"<BR>
     * <BR>
     * 　@　@(*1)開始位置、桁数は<BR>
     * 　@　@　@　@株式発注サービス.get注文Rev開始位置IN社内処理項目()、get注文Rev桁数()で取得して指定する。<BR>
     * <BR>
     * @@param l_eqtypeOrderUnitRow - (注文単位Row)<BR>
     * 失効対象の注文単位Rowオブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4607A55A02CE
     */
    protected void expireSettleOrder(EqtypeOrderUnitRow l_eqtypeOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "expireSettleOrder(EqtypeOrderUnitRow l_eqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_eqtypeOrderUnitRow == null)
        {
            log.error("パラメータ値不正。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）引数の注文単位に紐付く有効な予約注文を取得する。
        //　@株式予約注文更新サービス.get有効予約注文単位一覧()をcallする。
        //　@[引数]
        //　@　@親注文の注文ID：　@引数.注文単位Row.注文ID
        WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
        List l_lisOpenReserveEqtypeOrderUnit =
            l_updateService.getOpenReserveEqtypeOrderUnits(l_eqtypeOrderUnitRow.getOrderId());

        //２）　@失効通知をcallする。
        // this.exec失効通知()をcallし、注文の失効処理を行う。
        this.execCloseNotice(l_eqtypeOrderUnitRow);

        //３）注文単位の更新
        // ３－１）失効後の注文の再取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        OrderUnit l_orderUnit = null;
        try
        {
            // 拡張株式注文マネージャ.getOrderUnit()をcallし、
            l_orderUnit = l_orderMgr.getOrderUnit(l_eqtypeOrderUnitRow.getOrderUnitId());
            l_branch = l_accountManager.getBranch(l_eqtypeOrderUnitRow.getBranchId());

            // ３－２）　@強制失効区分に値をセットする。
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
            EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                new EqtypeOrderUnitParams(l_eqTypeOrderUnitRow);
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
            // getOrderUnit()の戻り値の注文単位.強制失効区分に以下値をセットする。
            l_eqTypeOrderUnitParams.setForcedExpireType(WEB3ForcedExpireType.FORCED_EXPIRED);

            // ３－３）　@注文単位を更新する
            // 拡張株式注文マネージャ.update注文データ()をcallする。
            EqTypeOrderUnit l_eqTypeOrderUnit1 =
                (EqTypeOrderUnit)l_orderMgr.toOrderUnit(l_eqTypeOrderUnitParams);
            l_orderMgr.updateOrderData(l_eqTypeOrderUnit1, null);

            //４）予約注文単位の更新
            //１）の戻り値をLoopし、要素毎に値の更新を行う。
            int l_intLoopCnt = 0;
            if (l_lisOpenReserveEqtypeOrderUnit != null)
            {
                l_intLoopCnt = l_lisOpenReserveEqtypeOrderUnit.size();
            }

            for (int i = 0; i < l_intLoopCnt; i++)
            {
                RsvEqOrderUnitRow l_rsvEqOrderUnitRowFromList =
                    (RsvEqOrderUnitRow)l_lisOpenReserveEqtypeOrderUnit.get(i);

                //失効後の予約注文の再取得
                //連続注文マネージャImpl.get株式予約注文単位()をcallし、
                //株式予約注文単位を再取得する。
                //[引数]
                //注文ID：　@処理要素の株式予約注文単位Row.注文ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl =
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                WEB3ToSuccEqTypeOrderUnitImpl l_eqTypeOrderUnitImpl =
                    l_orderManagerImpl.getReserveEqtypeOrderUnit(l_rsvEqOrderUnitRowFromList.getOrderId());

                RsvEqOrderUnitRow l_rsvEqOrderUnitRow =
                    (RsvEqOrderUnitRow)l_eqTypeOrderUnitImpl.getDataSourceObject();
                RsvEqOrderUnitParams l_rsvEqOrderUnitParams =
                    new RsvEqOrderUnitParams(l_rsvEqOrderUnitRow);

                //強制失効区分に値をセットする。
                //get株式予約注文単位()の戻り値の予約注文単位.強制失効区分に以下値をセットする。
                //＜強制失効区分にセットする値＞
                //強制失効区分：　@"強制失効済"
                l_rsvEqOrderUnitParams.setForcedExpireType(WEB3ForcedExpireType.FORCED_EXPIRED);

                //予約注文単位を更新する。
                //株式予約注文更新サービス.update予約注文データ()をcallする。
                //[引数]
                //注文単位：　@４－２）で値をセットした注文単位
                //注文履歴：　@null
                l_updateService.updateReserveOrderData(l_rsvEqOrderUnitParams, null);
            }

            // ５）株式注文取引キューの削除
            // 注文単位.発注条件＝"逆指値"かつ、
            // 拡張株式注文マネージャ.is未発注注文()＝trueの場合、以降のキューの削除を行わない。
            // 更新内容については、DB更新仕様「強制失効_株式注文取引キューテーブル.xls」参照。
            boolean l_blnIsNotOrderedOrder = l_orderMgr.isNotOrderedOrder(l_eqTypeOrderUnit);
            if (!(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                l_eqTypeOrderUnitParams.getOrderConditionType()) && l_blnIsNotOrderedOrder))
            {
                // 株式注文取引キューテーブル.データコード：　@"AI801"
                String l_strRequestCode = WEB3HostRequestCodeDef.EQUITY_ORDER;
                // 株式注文取引キューテーブル.証券会社コード：　@注文単位.getBranch().証券会社コード
                String l_strInstitutionCode = l_branchRow.getInstitutionCode();
                // 株式注文取引キューテーブル.部店コード：　@注文単位.getBranch().部店コード
                String l_strBranchCode = l_branchRow.getBranchCode();
                // 株式注文取引キューテーブル.識別コード：　@注文単位.識別コード
                String l_strOrderRequestNumber = l_eqTypeOrderUnitParams.getOrderRequestNumber();
                // 株式注文取引キューテーブル.社内処理項目に含まれる注文Rev.(*1)：　@注文単位.注文Rev.
                String l_strCorpCode = l_eqTypeOrderUnitParams.getOrderRev();
                // 株式注文取引キューテーブル.処理区分：　@"未処理"
                String l_strStatus = WEB3StatusDef.NOT_DEAL;

                // 株式発注サービス
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(
                        WEB3EquityFrontOrderService.class);
                // 注文Rev開始位置IN社内処理項目
                int l_intIndex = l_frontOrderService.getIndexOfOrderRevInCorpCode();
                // 注文Rev桁数
                int l_intFigure = l_frontOrderService.getFigureOfOrderRev();

                QueryProcessor l_processor = Processors.getDefaultProcessor();
                StringBuffer l_strWhere = new StringBuffer();
                l_strWhere.append("request_code = ? ");
                l_strWhere.append(" and institution_code = ? ");
                l_strWhere.append(" and branch_code = ? ");
                l_strWhere.append(" and order_request_number = ? ");
                l_strWhere.append(" and substr(corp_code, " + l_intIndex + ", " + l_intFigure + ") = ?");
                l_strWhere.append(" and status = ? ");
                Object[] l_objWhereValues = {
                    l_strRequestCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strOrderRequestNumber,
                    l_strCorpCode,
                    l_strStatus};

                List l_lisHostEqtypeOrderAllRow = l_processor.doFindAllQuery(
                    HostEqtypeOrderAllRow.TYPE,
                    l_strWhere.toString(),
                    null,
                    l_objWhereValues);

                if (l_lisHostEqtypeOrderAllRow == null && l_lisHostEqtypeOrderAllRow.size() == 0)
                {
                    log.error("テーブルに該当するデータがありません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "テーブルに該当するデータがありません。");
                }

                //株式注文取引キューテーブル
                HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow =
                    (HostEqtypeOrderAllRow)l_lisHostEqtypeOrderAllRow.get(0);
                HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams =
                     new HostEqtypeOrderAllParams(l_hostEqtypeOrderAllRow);

                // 注文履歴番号
                l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                    l_hostEqtypeOrderAllRow.getOrderActionSerialNo() + 1);
                // 処理区分（ステータス）
                l_hostEqtypeOrderAllParams.setStatus(WEB3StatusDef.ADMIN_MANUAL_EXPIRED);
                // 更新日付
                // 現在時刻
                l_hostEqtypeOrderAllParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                Processors.getDefaultProcessor().doUpdateQuery(l_hostEqtypeOrderAllParams);
            }
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
        catch (NotFoundException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (expire予約決済注文)<BR>
     * 株式予約注文更新サービスにより、引数の注文単位を失効させる。<BR>
     * <BR>
     * １）　@失効対象の判定<BR>
     * 　@１－１）　@注文の再取得<BR>
     * 　@　@連続注文マネージャImpl.get株式予約注文単位()をcallし、<BR>
     * 　@　@株式予約注文単位を再取得する。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@注文ID：　@引数.株式予約注文単位Row.getOrderId()<BR>
     * <BR>
     * 　@１－２）　@対象判定<BR>
     * 　@　@再取得した株式予約注文単位.注文有効状態 ＝ "クローズ"の場合、<BR>
     * 　@　@処理を終了する。（return;）<BR>
     * <BR>
     * ２）　@失効処理を行う<BR>
     * 　@株式予約注文更新サービスImpl.invalidate予約注文単位()をcallする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@株式予約注文単位行：　@引数.株式予約注文単位Row<BR>
     * 　@　@発注エラーコード：　@null<BR>
     * <BR>
     * ３）　@失効後の注文の再取得<BR>
     * 　@連続注文マネージャImpl.get株式予約注文単位()をcallし、<BR>
     * 　@失効後の株式予約注文単位を再取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@注文ID：　@引数.株式予約注文単位Row.getOrderId()<BR>
     * <BR>
     * ４）　@注文単位の更新<BR>
     * 　@４－１）　@強制失効区分に値をセットする。<BR>
     * 　@　@失効後の株式予約注文単位の強制失効区分に以下値をセットする。<BR>
     * <BR>
     * 　@　@　@＜強制失効区分にセットする値＞<BR>
     * 　@　@強制失効区分：　@"強制失効済"<BR>
     * <BR>
     * 　@４－２）　@注文単位を更新する<BR>
     * 　@　@株式予約注文更新サービスImpl.update予約注文データ()をcallする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@注文単位：　@４－１）で値をセットした注文単位<BR>
     * 　@　@　@注文履歴：　@null<BR>
     * <BR>
     * @@param l_rsvEqOrderUnitRow - (株式予約注文単位Row)<BR>
     * 失効対象の予約注文単位Rowオブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46133695028F
     */
    protected void expireRsvSettleOrder(RsvEqOrderUnitRow l_rsvEqOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "expireRsvSettleOrder(RsvEqOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvEqOrderUnitRow == null)
        {
            log.error("パラメータ値不正。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // 連続注文マネージャImpl
        WEB3ToSuccOrderManagerImpl l_orderManagerImpl = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        // 注文ID：　@引数.株式予約注文単位Row.getOrderId()
        long l_lngOrderId = l_rsvEqOrderUnitRow.getOrderId();
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqtypeOrderUnit = null;
        //１－１）　@注文の再取得
        // 連続注文マネージャImpl.get株式予約注文単位()をcallし、
        try
        {
            l_rsvEqtypeOrderUnit =
                l_orderManagerImpl.getReserveEqtypeOrderUnit(l_lngOrderId);
        } 
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

        // １－２）　@対象判定
        // 再取得した株式予約注文単位.注文有効状態 ＝ "クローズ"の場合
        if (OrderOpenStatusEnum.CLOSED.equals(
            l_rsvEqtypeOrderUnit.getOrderOpenStatus()))
        {
            log.debug(" 再取得した株式予約注文単位.注文有効状態 ＝ 'クローズ' ");
            log.exiting(STR_METHOD_NAME);
            return;
        }

        // ２）　@失効処理を行う
        // 株式予約注文更新サービスImpl.invalidate予約注文単位()をcallする。
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqTypeOrderUpdateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
        l_rsvEqTypeOrderUpdateService.invalidateOrderUnit(l_rsvEqOrderUnitRow, null);

        // ３）　@失効後の注文の再取得 
        // 連続注文マネージャImpl.get株式予約注文単位()をcallし、
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqtypeOrderUnit1 = null;
        try
        {
            l_rsvEqtypeOrderUnit1 =
                l_orderManagerImpl.getReserveEqtypeOrderUnit(l_lngOrderId);
        } 
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

        // ４－１）　@強制失効区分に値をセットする。
        // 失効後の株式予約注文単位の強制失効区分に以下値をセットする。
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow1 =
            (RsvEqOrderUnitRow)l_rsvEqtypeOrderUnit1.getDataSourceObject();
        RsvEqOrderUnitParams l_rsvEqOrderUnitParams =
            new RsvEqOrderUnitParams(l_rsvEqOrderUnitRow1);
        l_rsvEqOrderUnitParams.setForcedExpireType(WEB3ForcedExpireType.FORCED_EXPIRED);

        // ４－２）　@注文単位を更新する
        // 株式予約注文更新サービスImpl.update予約注文データ()をcallする。
        l_rsvEqTypeOrderUpdateService.updateReserveOrderData(l_rsvEqOrderUnitParams, null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (exec失効通知)<BR>
     * １）　@顧客オブジェクトを取得する。<BR>
     * 　@拡張アカウントマネージャ.getMainAccount()をcallする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@口座ID：　@引数.注文単位Row.getAccountId()<BR>
     * <BR>
     * ２）　@失効処理を行う。<BR>
     * 　@２－１）　@株式失効通知キューの生成<BR>
     * 　@　@株式失効通知キューParamsを生成する。<BR>
     * <BR>
     * 　@２－２）　@生成したインスタンスのプロパティセットを行う<BR>
     * <BR>
     * 　@　@　@＜各プロパティにセットする値＞<BR>
     * 　@　@データコード：　@"AI813"（株式失効通知）<BR>
     * 　@　@証券会社コード：　@getMainAccount()の戻り値.証券会社コード<BR>
     * 　@　@部店コード：　@getMainAccount()の戻り値.部店コード<BR>
     * 　@　@口座コード：　@getMainAccount()の戻り値.口座コード<BR>
     * 　@　@扱者コード：　@null（固定）<BR>
     * 　@　@識別コード：　@引数.注文単位Row.getOrderRequestNumber()<BR>
     * 　@　@約定数量：　@引数.注文単位Row.getExecutedQuantity()<BR>
     * 　@　@失効理由コード：　@"原注文破棄"<BR>
     * 　@　@失効通知区分：　@"失効"<BR>
     * 　@　@エラーメッセージ：　@"正常"<BR>
     * <BR>
     * 　@２－３）　@失効処理を行う<BR>
     * 　@　@株式失効通知一件サービスを取得し、exec失効()をcallする。<BR>
     * <BR>
     * 　@　@[exec失効の引数]<BR>
     * 　@　@　@株式失効通知キューParams：　@２－２）でプロパティセットした同名オブジェクト<BR>
     * 　@　@　@注文単位：　@拡張株式注文マネージャ.toOrderUnit(引数.注文単位Row)の戻り値<BR>
     * <BR>
     * @@param l_eqtypeOrderUnitRow - (注文単位Row)<BR>
     * 注文単位Row<BR>
     * @@roseuid 46149337004E
     * @@throws WEB3BaseException
     */
    protected void execCloseNotice(EqtypeOrderUnitRow l_eqtypeOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execCloseNotice(EqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_eqtypeOrderUnitRow == null)
        {
            log.error("パラメータ値不正。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // １）　@顧客オブジェクトを取得する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        MainAccount l_mainAccount = null;
        // 拡張アカウントマネージャ.getMainAccount()をcallする。
        try
        {
            l_mainAccount =
                l_accMgr.getMainAccount(l_eqtypeOrderUnitRow.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

        // ２）　@失効処理を行う。
        //　@２－１）　@株式失効通知キューの生成
        //　@株式失効通知キューParamsを生成する。
        HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
            new HostEqtypeCloseOrderNotifyParams();

        // 各プロパティにセットする値
        // データコード：　@"AI813"（株式失効通知）
        l_hostEqtypeCloseOrderNotifyParams.setRequestCode(
            WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);
        // 証券会社コード：　@getMainAccount()の戻り値.証券会社コード
        l_hostEqtypeCloseOrderNotifyParams.setInstitutionCode(
            l_mainAccount.getInstitution().getInstitutionCode());
        // 部店コード：　@getMainAccount()の戻り値.部店コード
        l_hostEqtypeCloseOrderNotifyParams.setBranchCode(
            l_mainAccount.getBranch().getBranchCode());
        // 口座コード：　@getMainAccount()の戻り値.口座コード
        l_hostEqtypeCloseOrderNotifyParams.setAccountCode(l_mainAccount.getAccountCode());
        // 扱者コード：　@null（固定）
        l_hostEqtypeCloseOrderNotifyParams.setTraderCode(null);
        // 識別コード：　@引数.注文単位Row.getOrderRequestNumber()
        l_hostEqtypeCloseOrderNotifyParams.setOrderRequestNumber(
            l_eqtypeOrderUnitRow.getOrderRequestNumber());
        // 約定数量：　@引数.注文単位Row.getExecutedQuantity()
        l_hostEqtypeCloseOrderNotifyParams.setExecutedQuantity(
            l_eqtypeOrderUnitRow.getExecutedQuantity());
        // 失効理由コード：　@"原注文破棄"
        l_hostEqtypeCloseOrderNotifyParams.setReasonCode(WEB3ExpirationReasonCodeDef.ORDER_CANCEL);
        // 失効通知区分：　@"失効"
        l_hostEqtypeCloseOrderNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
        // エラーメッセージ：　@"正常"
        l_hostEqtypeCloseOrderNotifyParams.setErrorMessage(WEB3ErrorReasonCodeDef.NORMAL);

        // ２－３）　@失効処理を行う
        // 株式失効通知一件サービスを取得し、exec失効()をcallする。
        WEB3EquityReceiveCloseOrderUnitService l_receiverCloseOrderUnitService =
            (WEB3EquityReceiveCloseOrderUnitService)Services.getService(
                WEB3EquityReceiveCloseOrderUnitService.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        //注文単位：　@拡張株式注文マネージャ.toOrderUnit(株式注文単位Row)
        OrderUnit l_orderUnit =
            l_orderMgr.toOrderUnit(l_eqtypeOrderUnitRow);
        EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
        l_receiverCloseOrderUnitService.execCloseOrder(l_hostEqtypeCloseOrderNotifyParams, l_eqTypeOrderUnit);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get注文単価)<BR>
     * 強制決済用の注文単価を取得する。<BR>
     * <BR>
     * ※マーケットメイク銘柄の場合、値幅上限または値幅下限を注文単価とする。<BR>
     * 　@以外、0（：成行）を注文単価とする。<BR>
     * <BR>
     * １）　@マーケットメイク銘柄以外の判定<BR>
     * 　@　@引数.取引銘柄.店頭公開区分 ≠ "マーケットメイク銘柄"の場合<BR>
     * 　@　@0を返却する。<BR>
     * <BR>
     * ２）　@基準値（終値）の判定<BR>
     * 　@　@引数.取引銘柄.基準値（終値） ＝ 0の場合<BR>
     * 　@　@引数.建株.getContractPrice()の戻り値を返却する。<BR>
     * <BR>
     * ３）　@値幅上限値/下限値を取得する。<BR>
     * 　@　@[引数.is買返済 == trueの場合]<BR>
     * 　@　@商品管理（株式）データマネージャ.get強制決済用値幅上限()をcallする。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@取引銘柄：　@引数.取引銘柄<BR>
     * <BR>
     * 　@　@[以外の場合]<BR>
     * 　@　@商品管理（株式）データマネージャ.get強制決済用値幅下限()をcallする。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@取引銘柄：　@引数.取引銘柄<BR>
     * <BR>
     * ４）　@注文単価返却<BR>
     * 　@３）で取得した値を返却する。<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 株式取引銘柄<BR>
     * @@param isCloseMarginShort - (is買返済)<BR>
     * 買返済注文の場合、true。<BR>
     * 以外、false<BR>
     * @@param l_eqtypeContract - (建株)<BR>
     * 建株<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4609FA75037F
     */
    protected double getLimitPrice(
        TradedProduct l_tradedProduct,
        boolean isCloseMarginShort,
        WEB3EquityContract l_eqtypeContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLimitPrice(TradedProduct, boolean, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.error("パラメータ値不正。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // １）　@マーケットメイク銘柄以外の判定
        // 引数.取引銘柄.店頭公開区分 ≠ "マーケットメイク銘柄"の場合 0を返却する。
        EqtypeTradedProductRow l_eqTypeTradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        if (!WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(
            l_eqTypeTradedProductRow.getOpenOtcDiv()))
        {
            return 0;
        }

        // ２）　@基準値（終値）の判定
        // 引数.取引銘柄.基準値（終値） ＝ 0の場合
        double l_dbLastClosingPrice =
            ((WEB3EquityTradedProduct)l_tradedProduct).getLastClosingPrice();
        if (l_dbLastClosingPrice == 0)
        {
            // 引数.建株.getContractPrice()の戻り値を返却する。
            double l_dbContractPrice = l_eqtypeContract.getContractPrice();
            log.exiting(STR_METHOD_NAME);
            return l_dbContractPrice;
        }

        // ３）　@値幅上限値/下限値を取得する。
        // [引数.is買返済 == trueの場合]
        // 商品管理（株式）データマネージャ
        WEB3EquityTradedProduct l_equityTradedProduct =
            (WEB3EquityTradedProduct)l_tradedProduct;
        double l_dblPrice = 0;
        if (isCloseMarginShort)
        {
            // 商品管理（株式）データマネージャ.get強制決済用値幅上限()をcallする
            l_dblPrice = WEB3AdminPMEquityDataManager.getForcedSettleHighPriceRange(l_equityTradedProduct);
        }
        else
        {
            // 商品管理（株式）データマネージャ.get強制決済用値幅下限()をcallする。
            l_dblPrice = WEB3AdminPMEquityDataManager.getForcedSettleLowPriceRange(l_equityTradedProduct);
        }

        // ４）注文単価返却  
        log.exiting(STR_METHOD_NAME);
        return l_dblPrice;
    }
   
    /**
     * (insert強制決済エラー注文)<BR>
     * 強制決済注文照会Paramsを生成し、<BR>
     * 引数の情報から値をセットして強制決済注文照会テーブルに１件insertする。<BR>
     * ただし、引数の条件で既にレコードが存在する場合は、何もしない。<BR>
     * <BR>
     * ※更新内容は、以下のDB設定論理を参照。<BR>
     * 　@　@・エラー注文登録_強制決済注文照会テーブル仕様.xls <BR>
     * <BR>
     * １）　@強制決済注文照会テーブルを検索する。<BR>
     * 　@　@QueryProcessor.doGetCountQuery()をcallし、<BR>
     * 　@　@戻り値≧1の場合は、処理を終了する。<BR>
     * <BR>
     * 　@　@＜検索条件＞<BR>
     * 　@　@　@注文ID：　@引数.注文ID(*)<BR>
     * 　@　@　@発注日：　@取引時間管理.get発注日()<BR>
     * 　@　@　@建株ID：　@引数.建株Row.建株ID<BR>
     * <BR>
     * (*)引数.注文IDがnullの場合は、" is Null "を検索条件とする。<BR>
     * <BR>
     * ２）　@強制決済注文照会Paramsを生成する。<BR>
     * <BR>
     * ３）　@１）のParamsに引数から編集した値をセットする。<BR>
     * <BR>
     * ４）　@QueryProcessor.doInsertQuery()をcallし、レコードを登録する。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@Row：　@２）でプロパティセットしたParams<BR>
     * <BR>
     * @@param l_eqtypeContractRow - (建株Row)<BR>
     * 建株Row<BR>
     * @@param l_strForcedSettleResonDiv - (強制決済理由区分)<BR>
     * 強制決済理由区分<BR>
     * @@param l_contractForceSettleResult - (強制決済余力計算結果)<BR>
     * 強制決済余力計算結果<BR>
     * @@param l_orderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_dbOrdeNumber - (注文数量)<BR>
     * エラー注文数量<BR>
     * @@param l_limitPrice - (指値)<BR>
     * 指値<BR>
     * @@param l_strOrderErrorReasonCode - (注文エラー理由コード)<BR>
     * 注文エラー理由コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 461D9F3601E3
     */
    protected void insertForceSettleErrorOrder(
        EqtypeContractRow l_eqtypeContractRow,
        String l_strForcedSettleResonDiv,
        WEB3TPContractForcedSettleResult l_contractForceSettleResult,
        Long l_orderId,
        double l_dblOrderNumber,
        Double l_limitPrice,
        String l_strOrderErrorReasonCode) throws WEB3BaseException
        
    {
        final String STR_METHOD_NAME =
            "insertForceSettleErrorOrder(EqtypeContractRow, String," +
            " WEB3TPContractForcedSettleResult, Long, double, Double, String)";
        log.entering(STR_METHOD_NAME);

        if (l_eqtypeContractRow == null)
        {
            log.error("パラメータ値不正。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //強制決済注文照会テーブルを検索する。
        // QueryProcessor.doGetCountQuery()をcallし、
        // 戻り値≧1の場合は、処理を終了する。
        // ＜検索条件＞
        //  注文ID：　@引数.注文ID(*)
        //  発注日：　@取引時間管理.get発注日()
        //  建株ID：　@引数.建株Row.建株ID
        // (*)引数.注文IDがnullの場合は、" is Null "を検索条件とする。
        StringBuffer l_sbQuerySql = new StringBuffer();
        List l_lisSqlValues = new ArrayList();
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        if (l_orderId == null)
        {
            l_sbQuerySql.append(" order_id is null ");
        }
        else
        {
            l_sbQuerySql.append(" order_id = ? ");
            l_lisSqlValues.add(l_orderId);
        }

        l_sbQuerySql.append(" and biz_date = ? ");
        l_lisSqlValues.add(l_strBizDate);

        l_sbQuerySql.append(" and contract_id = ? ");
        l_lisSqlValues.add(new Long(l_eqtypeContractRow.getContractId()));
        Object[] l_sqlValues = new Object[l_lisSqlValues.size()];
        l_lisSqlValues.toArray(l_sqlValues);

        QueryProcessor l_queryProcessor = null;
        int l_intQueryCnt;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_intQueryCnt = l_queryProcessor.doGetCountQuery(ForcedSettleOrderInqRow.TYPE,
                l_sbQuerySql.toString(),
                l_sqlValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_intQueryCnt >= 1)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //強制決済注文照会Paramsを生成する
        ForcedSettleOrderInqParams l_foredSettleOrderInqParams =
            new ForcedSettleOrderInqParams();

        try
        {
            //強制決済注文照会ID    forced_settle_order_inq_id  自動採番した値
            l_foredSettleOrderInqParams.setForcedSettleOrderInqId(
                    ForcedSettleOrderInqDao.newPkValue());
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //注文ＩＤ   order_id   insert強制決済エラー注文の引数.注文ID
        l_foredSettleOrderInqParams.setOrderId(l_orderId);

        //口座ＩＤ   account_id   建株.口座ID
        l_foredSettleOrderInqParams.setAccountId(l_eqtypeContractRow.getAccountId());

        //補助口座ＩＤ    sub_account_id     建株.補助口座ID
        l_foredSettleOrderInqParams.setSubAccountId(l_eqtypeContractRow.getSubAccountId());

        //部店ＩＤ  branch_id    "顧客.部店ID*顧客の取引店)"
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                l_accManager.getMainAccount(l_eqtypeContractRow.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        l_foredSettleOrderInqParams.setBranchId(l_mainAccount.getBranch().getBranchId());

        //注文種別   order_type
        // 建株.建区分 == "買建"の場合：　@5：買建返済注文（売返済）
        // 建株.建区分 == "売建"の場合：　@6：売建返済注文（買返済）
        if (ContractTypeEnum.LONG.equals(l_eqtypeContractRow.getContractType()))
        {
            l_foredSettleOrderInqParams.setOrderType(OrderTypeEnum.CLOSE_MARGIN_LONG);
        }
        if (ContractTypeEnum.SHORT.equals(l_eqtypeContractRow.getContractType()))
        {
            l_foredSettleOrderInqParams.setOrderType(OrderTypeEnum.CLOSE_MARGIN_SHORT);
        }

        //注文カテゴリ    order_categ     5：返済注文
        l_foredSettleOrderInqParams.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);

        //市場ＩＤ   market_id  建株.市場ID
        l_foredSettleOrderInqParams.setMarketId(l_eqtypeContractRow.getMarketId());

        //注文数量   quantity      insert強制決済エラー注文の引数.注文数量
        l_foredSettleOrderInqParams.setQuantity(l_dblOrderNumber);

        //指値    limit_price   insert強制決済エラー注文の引数.指値
        l_foredSettleOrderInqParams.setLimitPrice(l_limitPrice);

        //受渡日   delivery_date
        //"取引銘柄.getDailyDeliveryDate( )の戻り値
        //（* 発注日の3営業日後。当該日が権利落ち日の場合は4営業日後が戻される）"
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityTradedProduct l_tradedProduct = null;
        WEB3EquityProduct l_product = null;
        WEB3EquityProductManager l_productMgr =
            (WEB3EquityProductManager)l_tradingMod.getProductManager();
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productMgr.getTradedProduct(
                    l_eqtypeContractRow.getProductId(),
                    l_eqtypeContractRow.getMarketId());
            l_product =
                (WEB3EquityProduct)l_productMgr.getProduct(l_eqtypeContractRow.getProductId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(), l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        l_foredSettleOrderInqParams.setDeliveryDate(l_tradedProduct.getDailyDeliveryDate());

        //発注日   biz_date   取引時間管理.get発注日()(TradingCalendar.getCurrentBizDate( ))
        l_foredSettleOrderInqParams.setBizDate(l_strBizDate);

        //銘柄ＩＤ  product_id       建株.銘柄ID
        l_foredSettleOrderInqParams.setProductId(l_eqtypeContractRow.getProductId());

        //受注日時  received_date_time
        //  サーバ側でサービスが起動された時間（計算式書（共通）(*2) 処理日付　@を参照）
        Timestamp l_tsReceivedDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_foredSettleOrderInqParams.setReceivedDateTime(l_tsReceivedDate);

        //注文エラー理由コード    error_reason_code
        // insert強制決済エラー注文の引数.注文エラー理由コード
        l_foredSettleOrderInqParams.setErrorReasonCode(l_strOrderErrorReasonCode);

        //強制決済理由区分  forced_settle_reason_type
        //insert強制決済エラー注文の引数.強制決済理由区分
        l_foredSettleOrderInqParams.setForcedSettleReasonType(l_strForcedSettleResonDiv);

        //承認状態区分    approve_status_type      9：エラー
        l_foredSettleOrderInqParams.setApproveStatusType(WEB3ApproveStatusType.ERROR);

        if (l_contractForceSettleResult != null)
        {
            //保証金維持率  margin_maintenance_rate
            //insert強制決済エラー注文の引数.強制決済余力計算結果.保証金預託率
            l_foredSettleOrderInqParams.setMarginMaintenanceRate(
                l_contractForceSettleResult.marginMaintenanceRate);

            //追証発生日   additional_margin_date
            //insert強制決済エラー注文の引数.強制決済余力計算結果.追証発生日
            l_foredSettleOrderInqParams.setAdditionalMarginDate(
                l_contractForceSettleResult.additionalMarginDate);

            //追証経過日数  additional_margin_accrued_days
            //insert強制決済エラー注文の引数.強制決済余力計算結果.追証発生日からの経過日数
            if (l_contractForceSettleResult.additionalMarginAccruedDays != null)
            {
                l_foredSettleOrderInqParams.setAdditionalMarginAccruedDays(
                    Long.parseLong((l_contractForceSettleResult.additionalMarginAccruedDays).intValue() + ""));
            }
        }
        else
        {
            l_foredSettleOrderInqParams.setMarginMaintenanceRate(null);
        }

        //建株ＩＤ    contract_id     建株.建株ID
        l_foredSettleOrderInqParams.setContractId(l_eqtypeContractRow.getContractId());

        //元建株数    org_contract_quantity   建株.元建株数
        l_foredSettleOrderInqParams.setOrgContractQuantity(l_eqtypeContractRow.getOriginalQuantity());

        //建株数 contract_quantity      建株.建株数
        l_foredSettleOrderInqParams.setContractQuantity(l_eqtypeContractRow.getQuantity());

        //元建単価    original_contract_price         建株.元建単価
        l_foredSettleOrderInqParams.setOriginalContractPrice(l_eqtypeContractRow.getOriginalContractPrice());

        //建単価 contract_price    建株.建単価
        l_foredSettleOrderInqParams.setContractPrice(l_eqtypeContractRow.getContractPrice());

        //建区分 contract_type     建株.建区分
        l_foredSettleOrderInqParams.setContractType(l_eqtypeContractRow.getContractType().intValue());

        //建日  open_date     建株.建日
        l_foredSettleOrderInqParams.setOpenDate(l_eqtypeContractRow.getOpenDate());

        //期日  close_date    建株.期日
        l_foredSettleOrderInqParams.setCloseDate(l_eqtypeContractRow.getCloseDate());

        //税区分 tax_type     建株.税区分
        if (l_eqtypeContractRow.getTaxType() != null)
        {
            l_foredSettleOrderInqParams.setTaxType(l_eqtypeContractRow.getTaxType().intValue());
        }

        //弁済区分    repayment_type     建株.弁済区分
        l_foredSettleOrderInqParams.setRepaymentType(l_eqtypeContractRow.getRepaymentType());

        //弁済期限値   repayment_num     建株.弁済期限値
        l_foredSettleOrderInqParams.setRepaymentNum(l_eqtypeContractRow.getRepaymentNum());

        //作成日付    created_timestamp     現在日時
        Timestamp l_tsSystemtime = GtlUtils.getTradingSystem().getSystemTimestamp();
        l_foredSettleOrderInqParams.setCreatedTimestamp(l_tsSystemtime);

        //更新日付    last_updated_timestamp    現在日時
        l_foredSettleOrderInqParams.setLastUpdatedTimestamp(l_tsSystemtime);

        //口座コード  account_code  顧客.口座IDに該当する顧客.get表示顧客コード()
        l_foredSettleOrderInqParams.setAccountCode(((WEB3GentradeMainAccount)l_mainAccount).getDisplayAccountCode());

        //銘柄コード   product_code 建株.銘柄IDに該当する銘柄.銘柄コード
        l_foredSettleOrderInqParams.setProductCode(l_product.getProductCode());

        //QueryProcessor.doInsertQuery()をcallし、レコードを登録する。
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_QueryProcessor.doInsertQuery(l_foredSettleOrderInqParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
