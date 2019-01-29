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
filename	WEB3AdminEquityPTSInputExecServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式（PTS）出来入力サービスImpl（WEB3AdminEquityPTSInputExecServiceImpl.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/22 金傑 (中訊) 新規作成モデル177、ＤＢ更新仕様 019
Revision History : 2008/01/22 金傑 (中訊) モデル187
Revision History : 2008/01/29 金傑 (中訊) モデル192
Revision History : 2008/02/18 趙林鵬(中訊) モデルNo.197
Revision History : 2008/02/27 趙林鵬(中訊) モデルNo.198,201
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealedTypeDef;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSExecHistory;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputCancelExecCommonRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSOrderDetailUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSInputExecService;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・株式（PTS）出来入力サービスImpl)<BR>
 * 管理者・株式（PTS）出来入力サービス実装クラス<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3AdminEquityPTSInputExecServiceImpl extends WEB3ClientRequestService
    implements WEB3AdminEquityPTSInputExecService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSInputExecServiceImpl.class);

    /**
     * (管理者・株式（PTS）出来入力サービスImpl)<BR>
     * コンストラクタ。<BR>
     * @@roseuid 4795A0F70284
     */
    public WEB3AdminEquityPTSInputExecServiceImpl()
    {

    }

    /**
     * 株式（PTS）出来入力処理を行う。<BR>
     * <BR>
     * 引数のリクエストデータの型により、以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・株式（PTS）出来入力リクエストの場合 <BR>
     * 　@　@this.get入力画面()をコールする。 <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@リクエストデータ：　@引数.リクエストデータ <BR>
     * <BR>
     * ○管理者・株式（PTS）出来入力確認リクエストの場合 <BR>
     * 　@　@this.validate出来入力()をコールする。 <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@リクエストデータ：　@引数.リクエストデータ <BR>
     * <BR>
     * ○管理者・株式（PTS）出来入力完了リクエストの場合 <BR>
     * 　@　@this.submit出来入力()をコールする。 <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@リクエストデータ：　@引数.リクエストデータ <BR>
     * @@param l_request - (入力データ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47723D36009F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)  throws WEB3BaseException
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
        //引数の株式注文リクエストのオブジェクト型より、
        //サービスメソッドを判定しコールする。

        // 管理者・株式（PTS）出来入力リクエストの場合
        if (l_request instanceof WEB3AdminEquityPTSInputExecInputRequest)
        {
            //　@this.get入力画面()をコールする。
            l_response = this.getInputScreen((WEB3AdminEquityPTSInputExecInputRequest)l_request);
        }
        // 管理者・株式（PTS）出来入力確認リクエストの場合
        else if (l_request instanceof WEB3AdminEquityPTSInputExecConfirmRequest)
        {
            // this.validate出来入力()をコールする。
            l_response = this.validateInputExec((WEB3AdminEquityPTSInputExecConfirmRequest)l_request);
        }
        // 管理者・株式（PTS）出来入力完了リクエストの場合
        else if (l_request instanceof WEB3AdminEquityPTSInputExecCompleteRequest)
        {
            // this.submit出来入力()をコールする。
            l_response = this.submitInputExec((WEB3AdminEquityPTSInputExecCompleteRequest)l_request);
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
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * 出来入力の入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図「（管理者・株式（PTS）出来入力）get入力画面」 参照。 <BR>
     * @@param l_request - (入力データ)<BR>
     * 管理者・株式(PTS)出来入力リクエストデータ<BR>
     * @@return WEB3AdminEquityPTSInputExecInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47723C820077
     */
    protected WEB3AdminEquityPTSInputExecInputResponse getInputScreen(
        WEB3AdminEquityPTSInputExecInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminEquityPTSInputExecInputRequest)";
        log.entering(STR_METHOD_NAME);

        // validate()
        l_request.validate();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        // getOrderUnits
        // [引数]
        // arg0：　@リクエストデータ.注文ID
        // ※取得した注文オブジェクトの配列の先頭要素から注文単位を取得する。
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        EqTypeOrderUnit l_eqOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();

        // getInstanceFrom
        // ログイン情報より管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        // 管理者権限をチェックする。
        // [引数]
        // 管理者：　@getInstanceFromログイン情報()の戻り値
        // 部店ID：　@取得した注文単位.部店ID
        this.validateAdminAuthorities(l_administrator, l_orderUnitRow.getBranchId());

        // validate出来入力可能注文(注文単位)
        this.validateInputExecPossibilityOrder(l_eqOrderUnit);

        // set取引カレンダコンテキスト(注文単位)
        // [引数]
        // 注文単位：　@取得した注文単位
        this.setTradingClendarContext(l_eqOrderUnit);

        // validate出来入力出来取消可能時間帯()
        // 出来入力が可能な時間帯かチェックする。
        WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();

        // get注文詳細(注文単位)
        WEB3AdminEquityPTSOrderDetailUnit l_ptsOrderDetailUnit =
            WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_eqOrderUnit);

        // get約定履歴(注文単位)
        WEB3AdminEquityPTSExecHistory[] l_ptsExecHistories =
            WEB3AdminPMEquityDataManager.getExecHistory(l_eqOrderUnit);

        WEB3AdminEquityPTSInputExecInputResponse l_response =
            (WEB3AdminEquityPTSInputExecInputResponse)l_request.createResponse();

        l_response.orderDetail = l_ptsOrderDetailUnit;
        l_response.execHistories = l_ptsExecHistories;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate出来入力)<BR>
     * 出来入力の確認画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図「（管理者・株式（PTS）出来入力）validate出来入力」 参照。 <BR>
     *
     * @@param l_request - (入力データ)<BR>
     * 管理者・株式(PTS)出来入力確認リクエストデータ<BR>
     * @@return WEB3AdminEquityPTSInputExecConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47723C88021C
     */
    protected WEB3AdminEquityPTSInputExecConfirmResponse validateInputExec(
        WEB3AdminEquityPTSInputExecConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInputExec(WEB3AdminEquityPTSInputExecConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // validate()
        l_request.validate();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        // getOrderUnits
        // [引数]
        // arg0：　@リクエストデータ.注文ID
        // ※取得した注文オブジェクトの配列の先頭要素から注文単位を取得する。
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        EqTypeOrderUnit l_eqOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();

        // getInstanceFrom
        // ログイン情報より管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 管理者権限をチェックする。
        // [引数]
        // 管理者：　@getInstanceFromログイン情報()の戻り値
        // 部店ID：　@取得した注文単位.部店ID
        this.validateAdminAuthorities(l_administrator, l_orderUnitRow.getBranchId());

        // validate出来入力可能注文(注文単位)
        this.validateInputExecPossibilityOrder(l_eqOrderUnit);

        // validate出来入力可能約定
        // [引数]
        // 注文単位：　@取得した注文単位
        // 入力約定株数：　@リクエスト.約定株数
        // 入力約定日時：　@リクエスト.約定日時
        this.validateInputExecPossibilityExecuted(
            l_eqOrderUnit, l_request.execQuantity, l_request.executionTimeStamp);

        // set取引カレンダコンテキスト(注文単位)
        // [引数]
        // 注文単位：　@取得した注文単位
        this.setTradingClendarContext(l_eqOrderUnit);

        // validate出来入力出来取消可能時間帯()
        // 出来入力が可能な時間帯かチェックする。
        WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();

        // get注文詳細(注文単位)
        WEB3AdminEquityPTSOrderDetailUnit l_ptsOrderDetailUnit =
            WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_eqOrderUnit);

        // get約定履歴(注文単位)
        WEB3AdminEquityPTSExecHistory[] l_ptsExecHistories =
            WEB3AdminPMEquityDataManager.getExecHistory(l_eqOrderUnit);

        // get約定履歴（確認・完了）
        // [引数]
        // 管理者：　@getInstanceFromログイン情報()の戻り値
        // 約定履歴：　@get約定履歴()の戻り値
        // リクエストデータ：　@引数.リクエストデータ
        WEB3AdminEquityPTSExecHistory[] l_histories = this.getExecutedHistory(
            l_administrator, l_ptsExecHistories, l_request);

        WEB3AdminEquityPTSInputExecConfirmResponse l_response =
            (WEB3AdminEquityPTSInputExecConfirmResponse)l_request.createResponse();

        l_response.orderDetail = l_ptsOrderDetailUnit;
        l_response.execHistories = l_histories;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit出来入力)<BR>
     * 出来入力の完了画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図「（管理者・株式（PTS）出来入力）submit出来入力」 参照。 <BR>
     * @@param l_request - (入力データ)<BR>
     * 管理者・株式（PTS）出来入力完了リクエストデータ<BR>
     * @@return WEB3AdminEquityPTSInputExecCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47723C8F0269
     */
    protected WEB3AdminEquityPTSInputExecCompleteResponse submitInputExec(
        WEB3AdminEquityPTSInputExecCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitInputExec(WEB3AdminEquityPTSInputExecCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        // validate()
        l_request.validate();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        // getOrderUnits
        // [引数]
        // arg0：　@リクエストデータ.注文ID
        // ※取得した注文オブジェクトの配列の先頭要素から注文単位を取得する。
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        EqTypeOrderUnit l_eqOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();

        // getInstanceFrom
        // ログイン情報より管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 管理者権限をチェックする。
        // [引数]
        // 管理者：　@getInstanceFromログイン情報()の戻り値
        // 部店ID：　@取得した注文単位.部店ID
        this.validateAdminAuthorities(l_administrator, l_orderUnitRow.getBranchId());

        //暗証番号のチェックを行う
        //[引数]
        //パスワード: リクエストデータ.暗証番号
        //validate取引パスワード(パスワード : String)
        l_administrator.validateTradingPassword(l_request.password);

        // 顧客オブジェクトを取得する。
        // [引数]
        // 口座ID：　@取得した注文単位.口座ID
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                l_orderUnitRow.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // lock口座
        // 口座をロックする。
        // [引数]
        // 証券会社コード：　@顧客.getInstitution().getInstitutionCode()
        // 部店コード：　@顧客.getBranch().getBranchCode()
        // 顧客コード：　@顧客.getAccountCode()
        l_accountManager.lockAccount(
            l_mainAccount.getInstitution().getInstitutionCode(),
            l_mainAccount.getBranch().getBranchCode(),
            l_mainAccount.getAccountCode());

        // getOrderUnit
        // [引数]
        // 注文単位ID：　@取得した注文単位.注文単位ID
        try
        {
            l_eqOrderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnitRow.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // validate出来入力可能注文(注文単位)
        // [引数]
        // 注文単位：　@取得した注文単位
        this.validateInputExecPossibilityOrder(l_eqOrderUnit);

        // validate出来入力可能約定(注文単位, String, Date)
        // [引数]
        // 注文単位：　@取得した注文単位
        // 入力約定株数：　@リクエスト.約定株数
        // 入力約定日時：　@リクエスト.約定日時
        this.validateInputExecPossibilityExecuted(
            l_eqOrderUnit, l_request.execQuantity, l_request.executionTimeStamp);

        // set取引カレンダコンテキスト(注文単位)
        // 注文単位のデータを取引カレンダコンテキストにセットする。
        // [引数]
        // 注文単位：　@取得した注文単位
        this.setTradingClendarContext(l_eqOrderUnit);

        // validate出来入力出来取消可能時間帯()
        // 出来入力が可能な時間帯かチェックする。
        WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();

        // 注文が受付未済の場合（注文単位.市場から確認済みの数量 == null
        if (l_orderUnitRow.getConfirmedQuantityIsNull())
        {
            //  insert注文受付(顧客, 注文単位)
            // 注文受付キューに１件insertする。

            // [引数]
            // 顧客：　@get顧客()の戻り値
            // 注文単位：　@取得した注文単位
            this.insertOrderAccept(l_mainAccount, l_eqOrderUnit);
        }
        // get注文詳細(注文単位)
        WEB3AdminEquityPTSOrderDetailUnit l_ptsOrderDetailUnit =
            WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_eqOrderUnit);

        // get約定履歴(注文単位)
        WEB3AdminEquityPTSExecHistory[] l_ptsExecHistories =
            WEB3AdminPMEquityDataManager.getExecHistory(l_eqOrderUnit);

        // get約定履歴（確認・完了）
        // [引数]
        // 管理者：　@getInstanceFromログイン情報()の戻り値
        // 約定履歴：　@get約定履歴()の戻り値
        // リクエストデータ：　@引数.リクエストデータ
        WEB3AdminEquityPTSExecHistory[] l_histories = this.getExecutedHistory(
            l_administrator, l_ptsExecHistories, l_request);

        //  insert出来通知
        // 株式出来通知キューに1件insertする。

        // [引数]
        // 顧客：　@get顧客()の戻り値
        // 管理者：　@getInstanceFromログイン情報()の戻り値
        // 注文単位：　@取得した注文単位
        // リクエストデータ：　@引数.リクエストデータ
        this.insertExecNotify(l_mainAccount, l_administrator, l_eqOrderUnit, l_request);

        WEB3AdminEquityPTSInputExecCompleteResponse l_response =
            (WEB3AdminEquityPTSInputExecCompleteResponse)l_request.createResponse();

        l_response.orderDetail = l_ptsOrderDetailUnit;
        l_response.execHistories = l_histories;

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (validate管理者権限)<BR>
     * 管理者・部店権限をチェックする。<BR>
     * <BR>
     * １）　@引数.管理者.validate権限()をコールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@機@能カテゴリコード：　@機@能カテゴリコード.国内株式（出来入力・出来取消）<BR>
     * 　@　@is更新：　@true(更新あり)<BR>
     * <BR>
     * 　@　@※機@能カテゴリコードは、DBレイアウト「管理者権限テーブル.xls<BR>
     * 　@　@　@　@#（補足資料）機@能カテゴリコード一覧」参照。<BR>
     * <BR>
     * ２）　@拡張アカウントマネージャ.getBranch()をコールする。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@arg0：　@引数.部店ID<BR>
     * <BR>
     * ３）　@引数.管理者.validate部店権限()をコールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@部店コード：　@getBranch().get部店コード()の戻り値<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者<BR>
     * @@param l_lngBranchId - (部店ID)<BR>
     * 部店ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 477253830158
     */
    protected void validateAdminAuthorities(WEB3Administrator l_administrator, long l_lngBranchId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAdminAuthorities(WEB3Administrator, long)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeBranch l_branch = null;

        // １）　@引数.管理者.validate権限()をコールする。

        // [引数]
        // 機@能カテゴリコード：　@機@能カテゴリコード.国内株式（出来入力・出来取消）
        // is更新：　@true(更新あり)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.DOMESTIC_EQUITY_EXEC_INPUT_CANCEL, true);

        // ２）　@拡張アカウントマネージャ.getBranch()をコールする。

        // [引数]
        // arg0：　@引数.部店ID
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(l_lngBranchId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ３）　@引数.管理者.validate部店権限()をコールする。

        //　@[引数]
        //　@部店コード：　@getBranch().get部店コード()の戻り値
        l_administrator.validateBranchPermission(l_branch.getBranchCode());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set取引カレンダコンテキスト)<BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * <BR>
     * １）　@部店オブジェクトを取得。 <BR>
     * 　@　@　@拡張アカウントマネージャ.getBranch()をコールする。 <BR>
     * <BR>
     * 　@　@　@[引数] <BR>
     * 　@　@　@arg0：　@引数.注文単位.部店ID<BR>
     * <BR>
     * ２）　@証券会社オブジェクトを取得。 <BR>
     * 　@　@　@getBranch()の戻り値.getInstitution()をコールする。 <BR>
     * <BR>
     * ３）　@市場オブジェクトを取得する。 <BR>
     * 　@　@　@拡張金融オブジェクトマネージャ.getMarket()をコールする。 <BR>
     * <BR>
     * 　@　@　@[引数] <BR>
     * 　@　@　@arg0：　@引数.注文単位.市場ID<BR>
     * <BR>
     * ４）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－パラメータ.注文データの内容より取引時間コンテキストの <BR>
     * 　@　@　@プロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = getInstitution()の戻り値.getInstitutionCode() <BR>
     * 　@取引カレンダコンテキスト.部店コード = getBranch()の戻り値.getBranchCode() <BR>
     * 　@取引カレンダコンテキスト.市場コード = getMarket()の戻り値.getMarketCode() <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”株式・信用”<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”株式”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて <BR>
     * 　@　@　@取引カレンダコンテキストをセットする。<BR>
     * 　@　@　@設定キー： PTS取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ５）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－PTS取引時間管理.setTimestamp()をコールする。 <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4773292D013C
     */
    protected void setTradingClendarContext(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setTradingClendarContext(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        try
        {
            // １）　@部店オブジェクトを取得。
            // 拡張アカウントマネージャ.getBranch()をコールする。

            // [引数]
            // arg0：　@引数.注文単位.部店ID
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(
                l_eqOrderUnitRow.getBranchId());

            // ２）　@証券会社オブジェクトを取得。
            // getBranch()の戻り値.getInstitution()をコールする。
            WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_branch.getInstitution();

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            //３）　@市場オブジェクトを取得する。
            // 拡張金融オブジェクトマネージャ.getMarket()をコールする。

            // [引数]
            // arg0：　@引数.注文単位.市場ID
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                l_eqOrderUnitRow.getMarketId());

            // ４）　@取引カレンダコンテキストに内容をセットする。
            // －パラメータ.注文データの内容より取引時間コンテキストの
            // プロパティをセットする。
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

            // 取引カレンダコンテキスト.証券会社コード = getInstitution()の戻り値.getInstitutionCode()
            l_context.setInstitutionCode(l_institution.getInstitutionCode());

            // 取引カレンダコンテキスト.部店コード = getBranch()の戻り値.getBranchCode()
            l_context.setBranchCode(l_branch.getBranchCode());

            // 取引カレンダコンテキスト.市場コード = getMarket()の戻り値.getMarketCode()
            l_context.setMarketCode(l_market.getMarketCode());

            // 取引カレンダコンテキスト.受付時間区分 = ”株式・信用”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

            // 取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            // 取引カレンダコンテキスト.注文受付商品 = ”株式”
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);

            // 取引カレンダコンテキスト.注文受付トランザクション = null
            l_context.setOrderAcceptTransaction(null);

            //－ThreadLocalSystemAttributesRegistry.setAttribute( )にて
            //取引時間コンテキストをセットする。
            //設定キー： PTS取引時間管理.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

            // ５）　@受付日時、日付ロールをセットする。
            // －PTS取引時間管理.setTimestamp()をコールする。
            WEB3EquityPTSTradingTimeManagement.setTimestamp();
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate出来入力可能注文)<BR>
     * 出来入力処理が可能な注文かチェックする。<BR>
     * <BR>
     * １）　@PTS市場かどうかチェックする。<BR>
     * <BR>
     * 　@１－１）　@拡張金融オブジェクトマネージャ.getMarket()をコールし<BR>
     * 　@　@　@　@　@　@市場オブジェクトを取得する。 <BR>
     * <BR>
     * 　@　@　@　@　@　@[引数] <BR>
     * 　@　@　@　@　@　@市場ID：　@引数.注文単位.市場ID <BR>
     * <BR>
     * 　@１－２）　@PTS市場でない場合(市場.isPTS市場() == false)、<BR>
     * 　@　@　@　@　@　@「注文がPTS市場でないため、出来入力不可」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02988<BR>
     * <BR>
     * ２）　@注文有効状態がCLOSEDの場合、<BR>
     * 　@　@　@「注文状態が出来入力不可」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02983<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47724F4200EE
     */
    protected void validateInputExecPossibilityOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInputExecPossibilityOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        // １）　@PTS市場かどうかチェックする。

        // １－１）　@拡張金融オブジェクトマネージャ.getMarket()をコールし
        // 市場オブジェクトを取得する。

        // [引数]
        // 市場ID：　@引数.注文単位.市場ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_eqOrderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // １－２）　@PTS市場でない場合(市場.isPTS市場() == false)
        //「注文がPTS市場でないため、出来入力不可」の例外をスローする。
        if (!l_market.isPTSMarket())
        {
            log.debug("注文がPTS市場でないため、出来入力不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02988,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文がPTS市場でないため、出来入力不可。");
        }

        // ２）　@注文有効状態がCLOSEDの場合、
        //「注文状態が出来入力不可」の例外をスローする。
        if ((OrderOpenStatusEnum.CLOSED).equals(l_eqOrderUnitRow.getOrderOpenStatus()))
        {
            log.debug("注文状態が出来入力不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02983,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文状態が出来入力不可。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate出来入力可能約定)<BR>
     * 入力された内容の整合性をチェックする。<BR>
     * <BR>
     * １）　@約定数量チェック<BR>
     * <BR>
     * 　@　@引数.入力約定株数 ＞ 未約定数量（*1） の場合、<BR>
     * 　@　@「入力した約定株数が未約定株数を超えている」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02987<BR>
     * <BR>
     * 　@　@（*1） 未約定数量 ＝ 注文単位.市場から確認済みの数量（*2） － 注文単位.約定数量<BR>
     * 　@　@（*2） 注文受付未済の場合（注文単位.市場から確認済みの数量 == null）、<BR>
     * 　@　@　@　@　@ 注文単位.注文数量の値を使用する。<BR>
     * <BR>
     * ２）　@約定日時チェック<BR>
     * <BR>
     * 　@　@引数.入力約定日時 ＜ 注文単位.受注日時 の場合、<BR>
     * 　@　@「入力した約定日時が、受注日時より過去日時」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02984<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_strInputExecQuantity -(入力約定株数)<BR>
     * 入力約定株数<BR>
     * @@param l_datInputExecutionTimeStamp -(入力約定日時)<BR>
     * 入力約定日時<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47725A7D0024
     */
    protected void validateInputExecPossibilityExecuted(
        EqTypeOrderUnit l_orderUnit,
        String l_strInputExecQuantity, Date l_datInputExecutionTimeStamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInputExecPossibilityExecuted(EqTypeOrderUnit, String, Date)";
        log.entering(STR_METHOD_NAME);

        // １）　@約定数量チェック

        // 引数.入力約定株数 ＞ 未約定数量（*1） の場合、
        //「入力した約定株数が未約定株数を超えている」の例外をスローする。

        //（*1） 未約定数量 ＝ 注文単位.市場から確認済みの数量（*2） － 注文単位.約定数量
        //（*2） 注文受付未済の場合（注文単位.市場から確認済みの数量 == null）
        // 注文単位.注文数量の値を使用する。
        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        BigDecimal l_bdConfirmedQuantity = null;

        // 注文受付未済の場合（注文単位.市場から確認済みの数量 == null）
        if (l_eqOrderUnitRow.getConfirmedQuantityIsNull())
        {
            // 注文単位.注文数量の値を使用する。
            l_bdConfirmedQuantity = new BigDecimal(String.valueOf(l_eqOrderUnitRow.getQuantity()));

        }
        else
        {
            l_bdConfirmedQuantity = new BigDecimal(String.valueOf(l_eqOrderUnitRow.getConfirmedQuantity()));
        }

        // 未約定数量 ＝ 注文単位.市場から確認済みの数量 － 注文単位.約定数量

        double l_dbNotExectedQuantity = (l_bdConfirmedQuantity.subtract(
            new BigDecimal(String.valueOf(l_eqOrderUnitRow.getExecutedQuantity())))).doubleValue();

        // 引数.入力約定株数 ＞ 未約定数量（*1） の場合、
        //「入力した約定株数が未約定株数を超えている」の例外をスローする。
        if (Double.parseDouble(l_strInputExecQuantity) > l_dbNotExectedQuantity)
        {
            log.debug("入力した約定株数が未約定株数を超えている。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02987,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入力した約定株数が未約定株数を超えている。");
        }

        // ２）　@約定日時チェック
        //引数.入力約定日時 ＜ 注文単位.受注日時 の場合、
        //「入力した約定日時が、受注日時より過去日時」の例外をスローする。
        if (WEB3DateUtility.compareToSecond(
            l_datInputExecutionTimeStamp, l_eqOrderUnitRow.getReceivedDateTime()) < 0)
        {
            log.debug("入力した約定日時が、受注日時より過去日時。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02984,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入力した約定日時が、受注日時より過去日時。");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get約定履歴（確認・完了）)<BR>
     * 入力した約定内容を管理者・株式（PTS）約定履歴に設定し返却する。<BR>
     * <BR>
     * １）　@ArrayListを生成し、引数.約定履歴の全要素（*）をArrayListに追加する。<BR>
     * <BR>
     * 　@　@　@（*）引数.約定履歴 != null の場合のみ。<BR>
     * <BR>
     * ２）　@管理者・株式（PTS）約定履歴オブジェクトを作成し、<BR>
     * 　@　@　@以下項目に値をセットする。<BR>
     * <BR>
     * 　@　@　@・取消可能フラグ：　@false<BR>
     * 　@　@　@・約定日時：　@引数.リクエストデータ.約定日時<BR>
     * 　@　@　@・約定株数：　@引数.リクエストデータ.約定株数<BR>
     * 　@　@　@・約定単価：　@引数.リクエストデータ.約定単価<BR>
     * 　@　@　@・約定・取消区分：　@"一部約定"<BR>
     * 　@　@　@・更新者コード：　@管理者.管理者コード<BR>
     * 　@　@　@・処理区分：　@"未処理"<BR>
     * <BR>
     * ３）　@２）をArrayListに追加する。<BR>
     * <BR>
     * ４）　@ArrayListを約定履歴の配列に変換後、返却する。<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者<BR>
     * @@param l_executedHistory - (約定履歴)<BR>
     * 約定履歴<BR>
     * @@param l_request - (入力データ)<BR>
     * 管理者・株式(PTS)出来入力取消共通リクエストデータ<BR>
     * @@return WEB3AdminEquityPTSExecHistory[]
     * @@throws WEB3BaseException
     * @@roseuid 477349920093
     */
    protected WEB3AdminEquityPTSExecHistory[] getExecutedHistory(
        WEB3Administrator l_administrator,
        WEB3AdminEquityPTSExecHistory[] l_executedHistory,
        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutedHistory(WEB3Administrator, WEB3AdminEquityPTSExecHistory[],"
            + "WEB3AdminEquityPTSInputCancelExecCommonRequest)";
        log.entering(STR_METHOD_NAME);

        List l_lisPtsExecHistories = new ArrayList();

        // 引数.約定履歴 != null の場合のみ。
        // １）　@ArrayListを生成し、引数.約定履歴の全要素（*）をArrayListに追加する。

        if (l_executedHistory != null)
        {
            int l_intPtsExecHistoryCnt = l_executedHistory.length;
            for (int i = 0; i < l_intPtsExecHistoryCnt; i++)
            {
                l_lisPtsExecHistories.add(l_executedHistory[i]);
            }
        }


        //２）　@管理者・株式（PTS）約定履歴オブジェクトを作成し、
        // 以下項目に値をセットする。
        WEB3AdminEquityPTSExecHistory l_ptsExecHistory = new WEB3AdminEquityPTSExecHistory();

        // ・取消可能フラグ：　@false
        l_ptsExecHistory.cancelFlag = false;
        // ・約定日時：　@引数.リクエストデータ.約定日時
        l_ptsExecHistory.executionTimeStamp = l_request.executionTimeStamp;
        // ・約定株数：　@引数.リクエストデータ.約定株数
        l_ptsExecHistory.execQuantity = l_request.execQuantity;
        // ・約定単価：　@引数.リクエストデータ.約定単価
        l_ptsExecHistory.execPrice = l_request.execPrice;
        // ・約定・取消区分：　@"一部約定"
        l_ptsExecHistory.inputExecCancelExecDiv = WEB3DealedTypeDef.PARTIALLY_EXECUTED;
        // ・更新者コード：　@管理者.管理者コード
        l_ptsExecHistory.updaterCode = l_administrator.getAdministratorCode();
        // ・処理区分：　@"未処理"
        l_ptsExecHistory.inputExecCancelExecProcDiv = WEB3StatusDef.NOT_DEAL;

        // ３）　@２）をArrayListに追加する。
        l_lisPtsExecHistories.add(l_ptsExecHistory);

        // ４）　@ArrayListを約定履歴の配列に変換後、返却する。
        log.exiting(STR_METHOD_NAME);
        return (WEB3AdminEquityPTSExecHistory[])l_lisPtsExecHistories.toArray(
            new WEB3AdminEquityPTSExecHistory[l_lisPtsExecHistories.size()]);
    }

    /**
     * (insert注文受付)<BR>
     * 受付未済注文の場合、株式注文受付キューテーブルに1件データを登録する。<BR>
     * <BR>
     * １）　@引数.株式注文受付キューParamsに以下プロパティをセットする。<BR>
     * <BR>
     * 　@　@※設定値はDB更新仕様「出来入力_株式注文受付キューテーブル」参照<BR>
     * <BR>
     * ２）　@QueryProcessor.doInsertQuery()をコールする。 <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@arg0：　@株式注文受付キューParams<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 478CA9480009
     */
    protected void insertOrderAccept(MainAccount l_mainAccount, EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertOrderAccept(MainAccount, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //１）　@引数.株式注文受付キューParamsに以下プロパティをセットする。
        // ※設定値はDB更新仕様「出来入力_株式注文受付キューテーブル」参照
        HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams = new HostEqtypeOrderAcceptParams();

        // データコード AI80A（固定）
        l_hostEqtypeOrderAcceptParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_ACCEPT);

        // 証券会社コード 顧客の証券会社コード
        l_hostEqtypeOrderAcceptParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());

        // 部店コード 顧客の部店コード
        l_hostEqtypeOrderAcceptParams.setBranchCode(l_mainAccount.getBranch().getBranchCode());

        // 顧客コード 顧客の口座コード
        l_hostEqtypeOrderAcceptParams.setAccountCode(l_mainAccount.getAccountCode());

        // 扱者コード null
        l_hostEqtypeOrderAcceptParams.setTraderCode(null);

        // 識別コード 注文単位の識別コード
        //注文単位
        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        l_hostEqtypeOrderAcceptParams.setOrderRequestNumber(l_eqOrderUnitRow.getOrderRequestNumber());

        // 注文受付結果 1:注文受付完了
        l_hostEqtypeOrderAcceptParams.setAcceptStatus(WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE);

        // エラーメッセージ null
        l_hostEqtypeOrderAcceptParams.setErrorMessage(null);

        // 発注経路区分 注文単位の発注経路
        l_hostEqtypeOrderAcceptParams.setSubmitOrderRouteDiv(l_eqOrderUnitRow.getSubmitOrderRouteDiv());

        // 仮想サーバNo.（市場）  null
        l_hostEqtypeOrderAcceptParams.setVirtualServerNumberMarket(null);

        // 通知種別  null
        l_hostEqtypeOrderAcceptParams.setNoticeType(null);

        // 通知番号 null
        l_hostEqtypeOrderAcceptParams.setNoticeNumber(null);

        // 注文受付番号 null
        l_hostEqtypeOrderAcceptParams.setAcceptNumber(null);

        // 処理区分 0：未処理
        l_hostEqtypeOrderAcceptParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        // 作成日付 現在日時
        l_hostEqtypeOrderAcceptParams.setCreatedTimestamp(l_tsSystemTimestamp);

        // 更新日付 現在日時
        l_hostEqtypeOrderAcceptParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

        try
        {
            // ２）　@QueryProcessor.doInsertQuery()をコールする。
            //　@[引数]
            //　@arg0：　@株式注文受付キューParams
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_hostEqtypeOrderAcceptParams);
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
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert出来通知)<BR>
     * 株式出来通知キューテーブルに1件データを登録する。<BR>
     * <BR>
     * １）　@株式出来通知キューParamsを生成し、プロパティをセットする。<BR>
     * <BR>
     * 　@　@※設定値はDB更新仕様「出来入力_株式出来通知キューテーブル」参照<BR>
     * <BR>
     * ２）　@QueryProcessor.doInsertQuery()をコールする。 <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@arg0：　@株式出来通知キューParams<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_request - (入力データ)<BR>
     * 管理者・株式(PTS)出来入力取消共通リクエストデータ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47844EC903A3
     */
    protected void insertExecNotify(
        MainAccount l_mainAccount,
        WEB3Administrator l_administrator,
        EqTypeOrderUnit l_orderUnit,
        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertExecNotify(MainAccount, WEB3Adminstrator,"
            + "EqTypeOrderUnit, WEB3AdminEquityPTSInputCancelExecCommonRequest)";
        log.entering(STR_METHOD_NAME);

        // １）　@株式出来通知キューParamsを生成し、プロパティをセットする。
        // ※設定値はDB更新仕様「出来入力_株式出来通知キューテーブル」参照
        HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams =
            new HostEquityOrderExecNotifyParams();

        // データコード AI811（固定）
        l_hostEquityOrderExecNotifyParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_EXEC_NOTICE);

        // 証券会社コード 顧客の証券会社コード
        l_hostEquityOrderExecNotifyParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());

        // 部店コード 顧客の部店コード
        l_hostEquityOrderExecNotifyParams.setBranchCode(l_mainAccount.getBranch().getBranchCode());

        // 口座コード 顧客の口座コード
        l_hostEquityOrderExecNotifyParams.setAccountCode(l_mainAccount.getAccountCode());

        // 扱者コード null
        l_hostEquityOrderExecNotifyParams.setTraderCode(null);

        // 識別コード 注文単位の識別コード
        //注文単位
        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        // 注文単位の識別コード
        l_hostEquityOrderExecNotifyParams.setOrderRequestNumber(l_eqOrderUnitRow.getOrderRequestNumber());

        // 約定株数 リクエストデータの約定株数
        l_hostEquityOrderExecNotifyParams.setExecQuantity(Double.parseDouble(l_request.execQuantity));

        // 約定単価 リクエストデータの約定単価
        l_hostEquityOrderExecNotifyParams.setExecPrice(Double.parseDouble(l_request.execPrice));

        // 約定日時 リクエストデータの約定日時
        l_hostEquityOrderExecNotifyParams.setExecTimestamp(l_request.executionTimeStamp);

        // 出来通知区分 2：一部約定
        l_hostEquityOrderExecNotifyParams.setDealedType(WEB3DealedTypeDef.PARTIALLY_EXECUTED);

        // 仮想サーバNo.（市場）null
        l_hostEquityOrderExecNotifyParams.setVirtualServerNumberMarket(null);

        // 通知種別 null
        l_hostEquityOrderExecNotifyParams.setNoticeType(null);

        // 通知番号 null
        l_hostEquityOrderExecNotifyParams.setNoticeNumber(null);

        // 約定通知番号 null
        l_hostEquityOrderExecNotifyParams.setExecNumber(null);

        // 処理区分 0：未処理
        l_hostEquityOrderExecNotifyParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();

        // 作成日付 現在日時
        l_hostEquityOrderExecNotifyParams.setCreatedTimestamp(l_tsSystemTimestamp);

        // 更新日付 現在日時
        l_hostEquityOrderExecNotifyParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

        // 更新者コード 管理者の管理者コード
        l_hostEquityOrderExecNotifyParams.setLastUpdater(l_administrator.getAdministratorCode());
        try
        {
            // ２）　@QueryProcessor.doInsertQuery()をコールする。
            //　@[引数]
            //　@arg0：　@株式出来通知キューParams
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_hostEquityOrderExecNotifyParams);
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
        log.exiting(STR_METHOD_NAME);
    }
}
@
