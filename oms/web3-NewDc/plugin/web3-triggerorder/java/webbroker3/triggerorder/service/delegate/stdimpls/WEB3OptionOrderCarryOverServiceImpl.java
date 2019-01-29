head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3OptionOrderCarryOverServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文繰越サービスImpl(WEB3OptionOrderCarryOverServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/17 張威 (中訊) 新規作成
Revesion History : 2006/10/19 唐性峰(中訊)　@モデルNo.568
Revesion History : 2007/03/26 齊珂  (中訊)　@モデルNo.632
Revesion History : 2007/06/21 金傑  (中訊)  モデル670
Revesion History : 2007/07/12 趙林鵬(中訊) モデルNo.775
Revesion History : 2008/04/11 趙林鵬 (中訊) モデルNo.277,278
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.service.delegate.WEB3OptionOrderCarryOverService;
import webbroker3.triggerorder.service.delegate.WEB3OptionOrderCarryOverUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP注文繰越サービスImpl)<BR>
 * OP注文繰越サービス実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクションの指定をしない。<BR>
 *
 * @@author 張威
 * @@version 1.0
 */
public class WEB3OptionOrderCarryOverServiceImpl extends WEB3IfoOrderCarryOverMainServiceImpl
    implements WEB3OptionOrderCarryOverService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionOrderCarryOverServiceImpl.class);

    /**
     * @@roseuid 40C0AFC40222
     */
    public WEB3OptionOrderCarryOverServiceImpl()
    {

    }

    /**
     * OP注文繰越サービス処理を実施する。<BR>
     * <BR>
     * スーパークラスの同メソッドに処理を委譲する。<BR>
     * @@param l_request - リクエストデータ
     *
     * @@return webbroker3.ifo.message.WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 409B126A0188
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        return super.execute(l_request);
    }

    /**
     * (create翌日注文)<BR>
     * 翌日注文を作成する。<BR>
     * <BR>
     * スーパークラスの同メソッドに処理を委譲する。<BR>
     * @@param l_mainAccount - (顧客) <BR>
     * 顧客オブジェクト <BR>
     * @@param l_strFutureOptionDiv - (先物／オプション区分)<BR>
     * 先物／オプション区分<BR>
     * @@param l_strCarryoverProcessType - (注文繰越処理区分)<BR>
     * 注文繰越処理区分<BR>
     * @@throws WEB3BaseException
     */
    public void createNextOrder(
        MainAccount l_mainAccount,
        String l_strFutureOptionDiv,
        String l_strCarryoverProcessType)
        throws WEB3BaseException
    {
        super.createNextOrder(l_mainAccount, l_strFutureOptionDiv, l_strCarryoverProcessType);
    }

    /**
     *（get先物／オプション区分）<BR>
     * 先物／オプション区分を取得する。<BR>
     * <BR>
     * "オプション"を返却する。
     * @@return String
     */
    protected String getFutureOptionDiv()
    {
        return WEB3FuturesOptionDivDef.OPTION;
    }

    /**
     * （expire繰越元注文）<BR>
     * 繰越元注文の失効処理を行う。<BR>
     * <BR>
     * OP注文繰越UnitServiceImpl.expire繰越元注文()に <BR>
     * 処理を委譲する。<BR>
     * <BR>
     * ※引数は、本メソッドの引数をそのまま設定。<BR>
     * @@param l_orderUnit - 注文単位 <BR>
     * （繰越元）注文単位オブジェクト
     * @@throws WEB3BaseException
     */
    protected void expireCarryOverOriginOrder(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "expireCarryOverOriginOrder(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        // OP注文繰越一件サービスを取得する
        WEB3OptionOrderCarryOverUnitService l_optionOrderCarryOverUnitService =
            (WEB3OptionOrderCarryOverUnitService)Services.getService(
                WEB3OptionOrderCarryOverUnitService.class);
        l_optionOrderCarryOverUnitService.expireCarryOverOriginOrder(l_orderUnit);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     *（余力再計算）<BR>
     * 余力再計算を行う。<BR>
     * <BR>
     * １）　@OP取引口座タイプを取得する。 <BR>
     * パラメータ.顧客.getOP取引口座タイプ()をコールする。<BR>
     * ２）　@１）の戻り値 != "証拠金口座"の場合、<BR>
     * 取引余力サービスImpl.余力再計算()をコールする。<BR>
     * <BR>
     * [余力再計算()に指定する引数] <BR>
     *    補助口座：　@取得した補助口座(*1) <BR>
     * (*1)補助口座 <BR>
     * パラメータ.顧客.getSubAccount(１）の戻り値)にて取得。<BR>
     * @@param l_mainAccount - 顧客 <BR>
     * 顧客オブジェクト<BR>
     * @@throws WEB3BaseException
     */
    protected void reCalcTradingPower(MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "reCalcTradingPower(MainAccount)";
        log.entering(STR_METHOD_NAME);
        SubAccountTypeEnum l_subAccountType = null;
        SubAccount l_subAccount = null;
        
        if (l_mainAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            // １）　@OP取引口座タイプを取得する。
            l_subAccountType = ((WEB3GentradeMainAccount)l_mainAccount).getOpSubAccountType();
            l_subAccount = l_mainAccount.getSubAccount(l_subAccountType);
        }
        catch (NotFoundException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
            // １）の戻り値 != "証拠金口座"の場合
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccountType))
        {
            // 取引余力サービスImplを取得する
            WEB3TPTradingPowerService l_tpTradingPowerService = (WEB3TPTradingPowerService)
                Services.getService(WEB3TPTradingPowerService.class);

            // 余力再計算
            l_tpTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （submit翌日注文）<BR>
     * 翌日注文を登録する。<BR>
     * パラメータ.注文単位.注文カテゴリにより  <BR>
     *  <BR>
     * 処理を分岐する。<BR>
     * 注文カテゴリが、<BR>
     *  <BR>
     * ["OP新規建注文"の場合] <BR>
     * OP注文繰越UnitServiceImpl.create新規建翌日注文()をコールする。<BR>
     * <BR>
     * ["OP返済注文"の場合] <BR>
     * OP注文繰越UnitServiceImpl.create返済翌日注文()をコールする。 <BR>
     *  <BR>
     * ※各メソッドの引数には、本メソッドの引数をそのまま設定する。<BR>
     * @@param l_ifoOrderUnit  - 注文単位 <BR>
     * 注文単位オブジェクト<BR>
     * @@param l_lisRsvIfoOrderUnits - (予約注文単位一覧)<BR>
     * 予約注文単位一覧<BR>
     * @@throws WEB3BaseException
     */
    protected void submitNextOrder(IfoOrderUnit l_ifoOrderUnit, List l_lisRsvIfoOrderUnits)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitNextOrder(IfoOrderUnit, List)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // OP注文繰越一件サービスを取得する
        WEB3OptionOrderCarryOverUnitService l_optionOrderCarryOverUnitService =
            (WEB3OptionOrderCarryOverUnitService)Services.getService(
                WEB3OptionOrderCarryOverUnitService.class);

        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        // OP新規建注文"の場合
        if (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_ifoOrderUnitRow.getOrderCateg()))
        {
            // OP注文繰越UnitServiceImpl.create新規建翌日注文()をコールする。
            l_optionOrderCarryOverUnitService.createOpenContractNextOrder(l_ifoOrderUnit, l_lisRsvIfoOrderUnits);
        }
        // "OP返済注文"の場合
        if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_ifoOrderUnitRow.getOrderCateg()))
        {
            // OP注文繰越UnitServiceImpl.create返済翌日注文()をコールする。
            l_optionOrderCarryOverUnitService.createSettleContractNextOrder(l_ifoOrderUnit, l_lisRsvIfoOrderUnits);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     *（update繰越元注文）<BR>
     * 繰越元注文の注文エラー理由コードを更新する。<BR>
     * OP注文繰越UnitServiceImpl.update繰越元注文()に<BR>
     * 処理を委譲する。<BR>
     * <BR>
     * ※委譲先メソッドの引数は、本メソッドの引数をそのまま設定する。<BR>
     *  <BR>
     * @@param l_orderUnit - 注文単位 <BR>
     * （繰越元）注文単位オブジェクト <BR>
     * @@param l_strOrderErrorReasonCode - 注文エラー理由コード <BR>
     * 注文エラー理由コード <BR>
     * <BR>
     * DBレイアウト <BR>
     * 注文単位テーブル仕様.xls <BR>
     * 「（注文単位テーブル補足）<BR>
     * 注文エラー理由コード」シート参照。<BR>
     * @@throws WEB3BaseException
     */
    protected void updateCarryOverOriginOrder(OrderUnit l_orderUnit, String l_strOrderErrorReasonCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateCarryOverOriginOrder(OrderUnit, String)";
        log.entering(STR_METHOD_NAME);
        WEB3OptionOrderCarryOverUnitService l_optionOrderCarryOverUnitService =
            (WEB3OptionOrderCarryOverUnitService)Services.getService(
                WEB3OptionOrderCarryOverUnitService.class);
        try
        {
            //OP注文繰越UnitServiceImpl.update繰越元注文()に処理を委譲する。
            l_optionOrderCarryOverUnitService.updateCarryOverOriginOrder(l_orderUnit, l_strOrderErrorReasonCode);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
