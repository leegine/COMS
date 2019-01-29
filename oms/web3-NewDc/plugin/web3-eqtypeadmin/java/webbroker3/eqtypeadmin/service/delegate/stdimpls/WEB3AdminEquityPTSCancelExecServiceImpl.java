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
filename	WEB3AdminEquityPTSCancelExecServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式（PTS）出来取消サービスImpl(WEB3AdminEquityPTSCancelExecServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 于瀟(中訊) 新規作成 モデル178
Revision History : 2008/01/29 于瀟(中訊) 仕様変更 モデルNo.189,192,193,DB変更仕様020
Revision History : 2008/02/18 趙林鵬(中訊) 仕様変更 モデルNo.197
Revesion History : 2008/04/02 楊夫志(中訊) 仕様変更 モデルNo.204
Revesion History : 2008/04/03 楊夫志(中訊) 仕様変更 モデルNo.205
Revesion History : 2008/04/09 楊夫志(中訊) 仕様変更 モデルNo.206
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

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
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealedTypeDef;
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
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSExecHistory;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputCancelExecCommonRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSOrderDetailUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSCancelExecService;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・株式（PTS）出来取消サービスImpl)<BR>
 * 管理者・株式（PTS）出来取消サービス実装クラス<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3AdminEquityPTSCancelExecServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminEquityPTSCancelExecService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSCancelExecServiceImpl.class);

    /**
     * @@roseuid 4795B085031C
     */
    public WEB3AdminEquityPTSCancelExecServiceImpl()
    {

    }

    /**
     * 株式（PTS）出来取消処理を行う。<BR>
     * <BR>
     * 引数.リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・株式（PTS）出来取消入力リクエストの場合<BR>
     * 　@　@this.get入力画面()をコールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@リクエストデータ:　@引数.リクエストデータ<BR>
     * 　@<BR>
     * ○管理者・株式（PTS）出来取消確認リクエストの場合<BR>
     * 　@　@this.validate出来取消()をコールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@リクエストデータ:　@引数.リクエストデータ<BR>
     * <BR>
     * ○管理者・株式（PTS）出来取消完了リクエストの場合<BR>
     * 　@　@this.submit出来取消()をコールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@リクエストデータ:　@引数.リクエストデータ<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4769CBBE0346
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

        //管理者・株式（PTS）出来取消入力リクエストの場合
        if (l_request instanceof WEB3AdminEquityPTSCancelExecInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminEquityPTSCancelExecInputRequest)l_request);
        }
        //管理者・株式（PTS）出来取消確認リクエストの場合
        else if (l_request instanceof WEB3AdminEquityPTSCancelExecConfirmRequest)
        {
            l_response = this.validateCancelExec((WEB3AdminEquityPTSCancelExecConfirmRequest)l_request);
        }
        //管理者・株式（PTS）出来取消完了リクエストの場合
        else if (l_request instanceof WEB3AdminEquityPTSCancelExecCompleteRequest)
        {
            l_response = this.submitCancelExec((WEB3AdminEquityPTSCancelExecCompleteRequest)l_request);
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
     * (get入力画面)<BR>
     * 出来取消入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図「（管理者・株式（PTS）出来取消）get入力画面」 参照。 <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置:get約定履歴()の戻り値がnullの場合、<BR>
     * 　@　@　@　@　@　@　@　@「データ不整合」の例外をスローする。<BR>
     * 　@　@　@　@　@class:WEB3SystemLayerException        <BR>
     * 　@　@　@　@　@　@tag:SYSTEM_ERROR_80006<BR>
     * =============================================== <BR>
     * @@param l_request - (取消データ)<BR>
     * 管理者・株式(PTS)出来取消リクエストデータ<BR>
     * @@return WEB3AdminEquityPTSCancelExecInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4769CBC60315
     */
    protected WEB3AdminEquityPTSCancelExecInputResponse getInputScreen(
        WEB3AdminEquityPTSCancelExecInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminEquityPTSCancelExecInputRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする
        //validate( )
        l_request.validate();

        //注文単位を取得する。
        //[引数]
        //arg0：リクエストデータ.注文ID
        //取得した注文オブジェクトの配列の先頭から注文単位を取得する
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = null;
        EqTypeOrderUnit l_orderUnit = null;
        EqtypeOrderUnitRow l_orderUnitRow = null;
        l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //ログイン情報より管理者インスタンスを取得する
        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //管理者権限をチェックする
        //[引数]
        //管理者：取得した管理者オブジェクト
        //部店ID：取得した注文単位.部店ID
        //validate管理者権限(管理者, long)
        this.validateManagerAuthority(l_admin, l_orderUnitRow.getBranchId());

        //出来取消可能な注文単位かチェックする
        //[引数]
        //注文単位：　@取得した注文単位
        //validate出来取消可能注文(注文単位)
        this.validateCancelExecEnableOrder(l_orderUnit);

        //取引カレンダコンテキストに注文単位から取得した値をセットする
        //[引数]
        //注文単位：　@取得した注文単位
        //set取引カレンダコンテキスト(注文単位)
        this.setTradingClendarContext(l_orderUnit);

        //出来取消が可能な時間帯かどうかをチェックする
        // validate出来入力出来取消可能時間帯( )
        WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();

        //引数の注文単位の値を管理者・株式（PTS）注文詳細Unitに格納する
        //[引数]
        //注文単位：　@取得した注文単位
        //get注文詳細(注文単位)
        WEB3AdminEquityPTSOrderDetailUnit l_ptsOrderDetailUnit =
            WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_orderUnit);


        //引数の注文単位に紐づく約定履歴を取得する
        //[引数]
        //注文単位：　@取得した注文単位
        //get約定履歴(注文単位)
        WEB3AdminEquityPTSExecHistory[] l_ptsExecHistories =
            WEB3AdminPMEquityDataManager.getExecHistory(l_orderUnit);

        //get約定履歴()の戻り値がnullの場合、「データ不整合」の例外をスローする
        if (l_ptsExecHistories == null)
        {
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        //入力画面用の約定履歴の編集を行う
        //[引数]
        //約定履歴：　@商品管理(株式)データマネージャ.get約定履歴()で取得した約定履歴
        //get約定履歴（入力）(管理者・株式（PTS）約定履歴[])
        l_ptsExecHistories =
            this.getHistoryInput(l_ptsExecHistories);


        //レスポンスデータを生成する
        WEB3AdminEquityPTSCancelExecInputResponse l_response =
            (WEB3AdminEquityPTSCancelExecInputResponse)l_request.createResponse();

        //プロパティセット
        // レスポンスデータにプロパティをセットする
        //注文詳細：  商品管理(株式)データマネージャ.get注文詳細()の戻り値
        //約定履歴：  this.get約定履歴（入力）()の戻り値
        l_response.orderDetail = l_ptsOrderDetailUnit;
        l_response.execHistories = l_ptsExecHistories;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate出来取消)<BR>
     * 出来取消確認処理を行う。 <BR>
     * <BR>
     * シーケンス図「（管理者・株式（PTS）出来取消）validate出来取消」 参照。 <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置:get約定履歴()の戻り値がnullの場合、<BR>
     * 　@　@　@　@　@　@　@　@「データ不整合」の例外をスローする。<BR>
     * 　@　@　@　@　@class:WEB3SystemLayerException        <BR>
     * 　@　@　@　@　@　@tag:SYSTEM_ERROR_80006<BR>
     * =============================================== <BR>
     * @@param l_request - (取消データ)<BR>
     * 管理者・株式（PTS）出来取消確認リクエストデータ<BR>
     * @@return WEB3AdminEquityPTSCancelExecConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4769CBE202C1
     */
    protected WEB3AdminEquityPTSCancelExecConfirmResponse validateCancelExec(
        WEB3AdminEquityPTSCancelExecConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCancelExec(WEB3AdminEquityPTSCancelExecConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする
        //validate( )
        l_request.validate();

        //注文単位を取得する
        //[引数]
        //注文単位ID：リクエストデータ.注文ID
        //取得した注文オブジェクトの配列の先頭から注文単位を取得する
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = null;
        EqTypeOrderUnit l_orderUnit = null;
        EqtypeOrderUnitRow l_orderUnitRow = null;
        l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //ログイン情報より管理者インスタンスを取得する
        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //管理者権限をチェックする
        //[引数]
        //管理者：取得した管理者オブジェクト
        //部店ID：取得した注文単位.部店ID
        //validate管理者権限(管理者, long)
        this.validateManagerAuthority(l_admin, l_orderUnitRow.getBranchId());

        //出来取消可能な注文単位かチェックする
        //[引数]
        //注文単位：　@取得した注文単位
        //validate出来取消可能注文(注文単位)
        this.validateCancelExecEnableOrder(l_orderUnit);

        //出来取消可能な約定データを取得する
        //引数]
        //リクエストデータ： 引数.リクエストデータ
        //注文単位： 取得した注文単位
        //validate出来取消可能約定(管理者・株式（PTS）出来入力取消共通リクエスト, 注文単位)
        OrderExecution l_orderExecution =
            this.validateCancelExecEnableExecute(l_request, l_orderUnit);

        //取引カレンダコンテキストに注文単位から取得した値をセットする
        //[引数]
        //注文単位：　@取得した注文単位
        //set取引カレンダコンテキスト(注文単位)
        this.setTradingClendarContext(l_orderUnit);

        //出来取消が可能な時間帯かどうかをチェックする
        // validate出来入力出来取消可能時間帯( )
        WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();

        //引数の注文単位の値を管理者・株式（PTS）注文詳細Unitに格納する
        //[引数]
        //注文単位：　@取得した注文単位
        //get注文詳細(注文単位)
        WEB3AdminEquityPTSOrderDetailUnit l_ptsOrderDetailUnit =
            WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_orderUnit);

        //引数の注文単位に紐づく約定履歴を取得する
        //[引数]
        //注文単位：取得した注文単位
        //get約定履歴(注文単位)
        WEB3AdminEquityPTSExecHistory[] l_ptsExecHistories =
            WEB3AdminPMEquityDataManager.getExecHistory(l_orderUnit);

        //get約定履歴()の戻り値がnullの場合、「データ不整合」の例外をスローする
        if (l_ptsExecHistories == null)
        {
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        //取得した約定履歴に出来取消データを追加し、返却する
        //[引数]
        //約定履歴：商品管理(株式)データマネージャ.get約定履歴()で取得した約定履歴
        //約定：this.validate出来取消可能約定()で取得した約定データ
        //管理者：取得した管理者
        //get約定履歴（確認・完了）(管理者・株式（PTS）約定履歴[], OrderExecution, 管理者)(
        l_ptsExecHistories = this.getHistoryConfirmComplete(
            l_ptsExecHistories, l_orderExecution, l_admin);

        //レスポンスデータを生成する
        WEB3AdminEquityPTSCancelExecConfirmResponse l_response =
            (WEB3AdminEquityPTSCancelExecConfirmResponse)l_request.createResponse();

        //プロパティセット
        //レスポンスデータにプロパティを
        //注文詳細：  商品管理(株式)データマネージャ.get注文詳細()の戻り値
        //約定履歴：  this.get約定詳細（確認・完了）()の戻り値
        l_response.orderDetail = l_ptsOrderDetailUnit;
        l_response.execHistories = l_ptsExecHistories;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit出来取消)<BR>
     * 出来取消完了処理を行う。 <BR>
     * <BR>
     * シーケンス図「（管理者・株式（PTS）出来取消）submit出来取消」 参照。 <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置:get約定履歴()の戻り値がnullの場合、<BR>
     * 　@　@　@　@　@　@　@　@「データ不整合」の例外をスローする。<BR>
     * 　@　@　@　@　@class:WEB3SystemLayerException        <BR>
     * 　@　@　@　@　@　@tag:SYSTEM_ERROR_80006<BR>
     * =============================================== <BR>
     * @@param l_request - (取消データ)<BR>
     * 管理者・株式（PTS）出来取消完了リクエストデータ<BR>
     * @@return WEB3AdminEquityPTSCancelExecCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4769CEE901BB
     */
    protected WEB3AdminEquityPTSCancelExecCompleteResponse submitCancelExec(
        WEB3AdminEquityPTSCancelExecCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitCancelExec(WEB3AdminEquityPTSCancelExecCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする
        //validate( )
        l_request.validate();

        //注文単位を取得する
        //[引数]
        //arg0：リクエストデータ.注文ID
        //※取得した注文オブジェクトの配列の先頭から注文単位を取得する
        //getOrderUnits(arg0 : long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = null;
        EqTypeOrderUnit l_orderUnit = null;
        EqtypeOrderUnitRow l_orderUnitRow = null;
        l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //ログイン情報より管理者インスタンスを取得する
        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //管理者権限をチェックする
        //[引数]
        //管理者：取得した管理者オブジェクト
        //部店ID：取得した注文単位.部店ID
        this.validateManagerAuthority(l_admin, l_orderUnitRow.getBranchId());

        //暗証番号のチェックを行う
        //[引数]
        //パスワード: リクエストデータ.暗証番号
        //validate取引パスワード(パスワード : String)
        l_admin.validateTradingPassword(l_request.password);

        //顧客オブジェクトを取得する
        //[引数]
        //arg0: 取得した注文単位.getAccountId()
        //getMainAccount(arg0 : long)
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_accountManager.getMainAccount(l_orderUnit.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

        //出来取消対象注文の口座をロックする
        //[引数]
        //証券会社コード：　@顧客.getInstitution().getInstitutionCode()
        //部店コード：　@顧客.getBranch().getBranchCode()
        //顧客コード：　@顧客.getAccountCode()
        //lock口座(証券会社コード : String, 部店コード : String, 口座コード : String)
        //証券会社コード
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();

        //部店コード
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();

        //顧客コード
        String l_strAccountCode = l_mainAccount.getAccountCode();

        l_accountManager.lockAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

        // getOrderUnit
        // [引数]
        // 注文単位ID：　@取得した注文単位.注文単位ID
        try
        {
            l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnitRow.getOrderUnitId());
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

        //出来取消可能な注文単位かチェックする
        //[引数]
        //注文単位：　@取得した注文単位
        //validate出来取消可能注文(注文単位)
        this.validateCancelExecEnableOrder(l_orderUnit);

        //出来取消可能な約定データを取得する
        //[引数]
        //リクエストデータ： 引数.リクエストデータ
        //注文単位： 取得した注文単位
        //validate出来取消可能約定(管理者・株式（PTS）出来入力取消共通リクエスト, 注文単位)
        OrderExecution l_orderExecution =
            this.validateCancelExecEnableExecute(l_request, l_orderUnit);

        //取引カレンダコンテキストに注文単位から取得した値をセットする
        //[引数]
        //注文単位：　@取得した注文単位
        //set取引カレンダコンテキスト(注文単位)
        this.setTradingClendarContext(l_orderUnit);

        //出来取消が可能な時間帯かどうかをチェックする
        // validate出来入力出来取消可能時間帯( )
        WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();

        //引数の注文単位の値を管理者・株式（PTS）注文詳細Unitに格納する
        //[引数]
        //注文単位：　@取得した注文単位
        //get注文詳細(注文単位)
        WEB3AdminEquityPTSOrderDetailUnit l_ptsOrderDetailUnit =
            WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_orderUnit);

        //引数の注文単位に紐づく約定履歴を取得する
        //[引数]
        //注文単位：取得した注文単位
        //get約定履歴(注文単位)
        WEB3AdminEquityPTSExecHistory[] l_ptsExecHistories =
            WEB3AdminPMEquityDataManager.getExecHistory(l_orderUnit);

        //get約定履歴()の戻り値がnullの場合、「データ不整合」の例外をスローする
        if (l_ptsExecHistories == null)
        {
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        //取得した約定履歴に出来取消データを追加し、返却する
        //[引数]
        //約定履歴：商品管理(株式)データマネージャ.get約定履歴()で取得した約定履歴
        //約定：this.validate出来取消可能約定()で取得した約定データ
        //管理者：取得した管理者
        //get約定履歴（確認・完了）(管理者・株式（PTS）約定履歴[], OrderExecution, 管理者)
        l_ptsExecHistories = this.getHistoryConfirmComplete(l_ptsExecHistories, l_orderExecution, l_admin);

        //出来通知キューテーブルに出来取消データをInsertする
        //[引数]
        //注文単位：取得した注文単位
        //約定データ：this.validate出来取消可能約定()で取得した約定データ
        //顧客：取得した顧客オブジェクト
        //管理者：取得した管理者オブジェクト
        //insert出来通知(注文単位, OrderExecution, 顧客, 管理者)
        this.insertExecNotify(l_orderUnit, l_orderExecution, (WEB3GentradeMainAccount)l_mainAccount, l_admin);

        //レスポンスデータを生成する
        //createResponse( )
        WEB3AdminEquityPTSCancelExecCompleteResponse l_response =
            (WEB3AdminEquityPTSCancelExecCompleteResponse)l_request.createResponse();

        //プロパティセット
        //レスポンスデータにプロパティをセットする
        //注文詳細：　@ 商品管理(株式)データマネージャ.get注文詳細()の戻り値
        //約定履歴：　@ this.get約定履歴（確認・完了）()の戻り値
        l_response.orderDetail = l_ptsOrderDetailUnit;
        l_response.execHistories = l_ptsExecHistories;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate出来取消可能注文)<BR>
     * 出来取消処理が可能な注文かチェックする。<BR>
     * <BR>
     * １）PTS市場かどうかチェックする。<BR>
     * <BR>
     * 　@　@１－１） 拡張金融オブジェクトマネージャ.getMarket()をコールし<BR>
     * 　@　@　@　@　@　@　@市場オブジェクトを取得する。 <BR>
     * <BR>
     * 　@　@　@　@[引数] <BR>
     * 　@　@　@　@市場ID:　@引数の注文単位.市場ID <BR>
     *  <BR>
     * 　@　@１－２）PTS市場でない場合(市場.isPTS市場() == false)、 <BR>
     * 　@　@　@　@　@　@「PTS市場でない場合は出来取消不可」の例外をスローする。 <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_03003<BR>
     *  <BR>
     * ２）下記条件に該当しない場合、 <BR>
     *    「注文状態が出来取消不可」の例外をスローする。 <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02986<BR>
     *  <BR>
     * 　@　@[条件] <BR>
     * 　@　@　@　@注文単位.isUnExecuted() == false (約定あり)   かつ　@ <BR>
     * 　@　@　@　@注文IDの発注日 == 業務日付 <BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 476B126A00FE
     */
    protected void validateCancelExecEnableOrder(EqTypeOrderUnit l_eqTypeOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCancelExecEnableOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //PTS市場かどうかチェックする

        // 拡張金融オブジェクトマネージャ.getMarket()をコールし
        //市場オブジェクトを取得する。
        //[引数]
        //市場ID:　@引数の注文単位.市場ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectMgr =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        try
        {
            l_market = (WEB3GentradeMarket)l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());
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

        //PTS市場でない場合(市場.isPTS市場() == false)、
        //「PTS市場でない場合は出来取消不可」の例外をスローする
        if (!l_market.isPTSMarket())
        {
            log.debug("PTS市場でない場合は出来取消不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "PTS市場でない場合は出来取消不可。");
        }

        //下記条件に該当しない場合
        //「注文状態が出来取消不可」の例外をスローする。
        //[条件]
        //　@　@　@注文単位.isUnExecuted() == false (約定あり)   かつ
        //　@　@　@注文IDの発注日 == 業務日付
        Date l_datOrderIdDate =
            WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        if (!(!l_eqTypeOrderUnit.isUnexecuted() && WEB3DateUtility.compareToDay(l_datOrderIdDate, l_datBizDate) == 0))
        {
            log.debug("注文状態が出来取消不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02986,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文状態が出来取消不可。");
        }
    }

    /**
     * (validate出来取消可能約定)<BR>
     * 指定された約定日時、約定数量、約定単価のデータが出来取消可能かどうかチェックし、<BR>
     * 取消可能な約定データを返却する。<BR>
     * <BR>
     * １） 引数の注文単位.getExecutions( )で、<BR>
     * 　@　@対象注文に紐付く約定データを全て取得する。<BR>
     * <BR>
     * 　@　@該当する約定データが存在しない場合、「約定なし」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_00676<BR>
     * <BR>
     * <BR>
     * ２）　@１）で取得した約定データの中から、以下の条件に合致するデータを<BR>
     * 　@　@検索し、合致した約定データを取得する。<BR>
     * <BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@約定データ.約定日時 ＝ 引数.リクエストデータ.約定日時<BR>
     * 　@　@約定データ.約定数量 ＝ 引数.リクエストデータ.約定数量<BR>
     * 　@　@約定データ.約定単価 ＝ 引数.リクエストデータ.約定単価<BR>
     * <BR>
     * 　@　@※検索条件に合致するデータが複数件存在する場合、<BR>
     * 　@　@　@約定順番号が一番小さいデータ（＝最も過去の約定として扱う）<BR>
     * 　@　@　@を取得する。<BR>
     * 　@　@※検索条件に合致するデータが存在しない場合、<BR>
     * 　@　@　@「約定なし」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_00676<BR>
     * <BR>
     * ３） 引数.注文単位.注文種別 ＝ 現物買注文　@の場合のみ、<BR>
     * 　@　@以下の保有資産数量チェックを行う。<BR>
     * <BR>
     * <BR>
     * ３－１）株式ポジションマネージャ.get保有資産()をコールし、<BR>
     * 　@　@　@　@保有資産オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@口座ID：引数.注文単位.口座ID<BR>
     * 　@　@　@　@補助口座ID：引数.注文単位.補助口座ID<BR>
     * 　@　@　@　@銘柄ID：引数.注文単位.銘柄ID<BR>
     * 　@　@　@　@税区分：引数.注文単位.税区分<BR>
     * <BR>
     * 　@　@　@※保有資産が取得できない場合、<BR>
     * 　@　@　@　@「保有資産該当データなし」の例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_00204<BR>
     * <BR>
     * ３－２）保有資産.数量－保有資産.ロック中数量 ＜ 引数.リクエストデータ.約定数量<BR>
     * 　@　@　@　@の場合、「保有資産残数量チェックエラー。」の例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_01931<BR>
     * <BR>
     * ４）　@２）で取得した約定データを返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・株式(PTS)出来入力取消共通リクエストデータ
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return OrderExecution
     * @@throws WEB3BaseException
     * @@roseuid 4772368B027D
     */
    protected OrderExecution validateCancelExecEnableExecute(
        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request,
        EqTypeOrderUnit l_eqTypeOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateCancelExecEnableExecute(WEB3AdminEquityPTSInputCancelExecCommonRequest, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //引数の注文単位.getExecutions( )で
        //対象注文に紐付く約定データを全て取得する
        //該当する約定データが存在しない場合、「約定なし」の例外をスローする

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        OrderExecution[] l_orderExecutions = l_eqTypeOrderUnit.getExecutions();
        OrderExecution[] l_finalOrderExecutions = null;
        OrderExecution l_finalOrderExecution = null;
        List l_lisOrderExecutions = new ArrayList();
        if (l_orderExecutions == null || l_orderExecutions.length == 0)
        {
            log.debug("取消対象の約定データが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00676,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取消対象の約定データが存在しない。");
        }
        //１）で取得した約定データの中から、以下の条件に合致するデータを検索し、
        //合致した約定データを返却する。
        //[検索条件]
        //約定データ.約定日時 ＝ 引数.リクエストデータ.約定日時
        //約定データ.約定数量 ＝ 引数.リクエストデータ.約定数量
        //約定データ.約定単価 ＝ 引数.リクエストデータ.約定単価
        //※検索条件に合致するデータが複数件存在する場合、
        //　@約定順番号が一番小さいデータ（＝最も過去の約定として扱う）
        //　@を返却する。
        //※検索条件に合致するデータが存在しない場合、
        //「約定なし」の例外をスローする。
        else
        {
            int l_intOrderExecutionsCnt = l_orderExecutions.length;
            for (int i = 0; i < l_intOrderExecutionsCnt; i++)
            {
                if (WEB3DateUtility.compareToSecond(l_orderExecutions[i].getExecutionTimestamp(),
                    l_request.executionTimeStamp) == 0
                    && l_orderExecutions[i].getExecutionQuantity() == Double.parseDouble(l_request.execQuantity)
                    && l_orderExecutions[i].getExecutionPrice() == Double.parseDouble(l_request.execPrice))
                {
                    l_lisOrderExecutions.add(l_orderExecutions[i]);
                }
            }

            l_finalOrderExecutions = new OrderExecution[l_lisOrderExecutions.size()];
            l_lisOrderExecutions.toArray(l_finalOrderExecutions);

            if (l_finalOrderExecutions == null || l_finalOrderExecutions.length == 0)
            {
                log.debug("取消対象の約定データが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00676,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取消対象の約定データが存在しない。");
            }
            else
            {
                int l_intTemp = l_finalOrderExecutions[0].getExecutionSerialNo();
                int l_intIndex = 0;
                int l_intFinalOrderExecutionsCnt = l_finalOrderExecutions.length;
                for (int i = 1; i < l_intFinalOrderExecutionsCnt; i++)
                {
                    if (l_finalOrderExecutions[i].getExecutionSerialNo() < l_intTemp)
                    {
                        l_intTemp = l_finalOrderExecutions[i].getExecutionSerialNo();
                        l_intIndex = i;
                    }
                }

                l_finalOrderExecution = l_finalOrderExecutions[l_intIndex];
            }
        }
        //３） 引数.注文単位.注文種別 ＝ 現物買注文　@の場合のみ、
        //以下の保有資産数量チェックを行う。
        if (OrderTypeEnum.EQUITY_BUY.equals(l_eqTypeOrderUnit.getOrderType()))
        {
            //３－１）株式ポジションマネージャ.get保有資産()をコールし、
            //　@　@保有資産オブジェクトを取得する。
            //  [引数]
            //　@口座ID：引数.注文単位.口座ID
            //　@補助口座ID：引数.注文単位.補助口座ID
            //　@銘柄ID：引数.注文単位.銘柄ID
            //　@税区分：引数.注文単位.税区分
            //※保有資産が取得できない場合、
            //「保有資産該当データなし」の例外をスローする。 
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();

            EqTypeAsset l_eqTypeAsset =
                l_positionManager.getAsset(
                    l_eqTypeOrderUnit.getAccountId(),
                    l_eqTypeOrderUnit.getSubAccountId(),
                    l_eqTypeOrderUnit.getProduct().getProductId(),
                    l_eqTypeOrderUnit.getTaxType());

            if (l_eqTypeAsset == null)
            {
                log.debug("保有資産該当データなし。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "保有資産該当データなし。");
            }

            //３－２）保有資産.数量－保有資産.ロック中数量 ＜ 引数.リクエストデータ.約定数量  
            //        の場合、「保有資産残数量チェックエラー。」の例外をスローする。 
            if (Double.parseDouble(l_request.execQuantity) > (l_eqTypeAsset.getQuantity() - l_eqTypeAsset.getLockedQuantity()))
            {
                log.debug("保有資産残数量チェックエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01931,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "保有資産残数量チェックエラー。");
            }
        }
        //４）　@２）で取得した約定データを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_finalOrderExecution;

    }

    /**
     * (set取引カレンダコンテキスト)<BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * <BR>
     * １）　@部店オブジェクトを取得。 <BR>
     * 　@　@　@拡張アカウントマネージャ.getBranch()をコールする。 <BR>
     * <BR>
     * 　@　@　@[引数] <BR>
     * 　@　@　@arg0：　@引数.注文単位.部店ID の戻り値 <BR>
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
     * 　@－パラメータ.注文データの内容より取引カレンダコンテキストの <BR>
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
     * 　@　@　@取引カレンダコンテキストをセットする。 <BR>
     * 　@　@　@設定キー： PTS取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ５）　@受付日時、日付ロールをセットする。 <BR>
     * 　@－PTS取引時間管理.setTimestamp()をコールする。 <BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4773375C0284
     */
    protected void setTradingClendarContext(EqTypeOrderUnit l_eqTypeOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setTradingClendarContext(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //部店オブジェクトを取得。
        //拡張アカウントマネージャ.getBranch()をコールする
        //[引数]
        //arg0：　@引数.注文単位.部店ID の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_accMgr.getBranch(l_eqTypeOrderUnit.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

        //証券会社オブジェクトを取得。
        //getBranch()の戻り値.getInstitution()をコールする
        Institution l_institution = l_branch.getInstitution();

        //市場オブジェクトを取得する。
        //拡張金融オブジェクトマネージャ.getMarket()をコールする
        //[引数]
        //arg0：　@引数.注文単位.市場ID
        WEB3GentradeFinObjectManager l_finObjectMgr =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        try
        {
            l_market = (WEB3GentradeMarket)l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());
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

        //４）　@取引カレンダコンテキストに内容をセットする。
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get約定履歴（入力）)<BR>
     * 出来取消可能な約定履歴の取消可能フラグにtrueをセットする。<BR>
     * <BR>
     * <BR>
     * １）引数.約定履歴の配列の全要素について以下の処理を行う。<BR>
     * <BR>
     * <BR>
     * 　@　@　@　@約定履歴.約定・約定取消区分≠"4" (約定取消でない) 　@かつ <BR>
     * 　@　@　@　@　@約定履歴.処理区分＝"1"(処理済)の場合、<BR>
     * <BR>
     * 　@　@　@　@約定履歴.取消可能フラグにtrueをセット<BR>
     * <BR>
     * <BR>
     * ２）約定履歴の配列を返却する。<BR>
     * @@param l_histories - (約定履歴)<BR>
     * 管理者・株式（PTS）約定履歴の配列<BR>
     * @@return WEB3AdminEquityPTSExecHistory[]
     * @@roseuid 4781F97702E5
     */
    protected WEB3AdminEquityPTSExecHistory[] getHistoryInput(WEB3AdminEquityPTSExecHistory[] l_histories)
    {
        final String STR_METHOD_NAME = "getHistoryInput(WEB3AdminEquityPTSExecHistory)";
        log.entering(STR_METHOD_NAME);

        //引数.約定履歴の配列の全要素について以下の処理を行う
        //約定履歴.約定・約定取消区分≠"4" (約定取消でない) 　@かつ
        //　@　@約定履歴.処理区分＝"1"(処理済)の場合、
        //約定履歴.取消可能フラグにtrueをセット
        int l_intHistoriesCnt = l_histories.length;
        for (int i = 0; i < l_intHistoriesCnt; i++)
        {
            if (!WEB3DealedTypeDef.CANCEL.equals(l_histories[i].inputExecCancelExecDiv)
                && WEB3StatusDef.DEALT.equals(l_histories[i].inputExecCancelExecProcDiv))
            {
                l_histories[i].cancelFlag = true;
            }
        }

        //約定履歴の配列を返却する
        log.exiting(STR_METHOD_NAME);
        return l_histories;
    }

    /**
     * (get約定履歴（確認・完了）)<BR>
     * 取得した約定履歴に出来取消データを追加し、返却する。<BR>
     * <BR>
     * １）ArrayListを生成し、引数.管理者・株式（PTS）約定履歴の配列の全要素を<BR>
     *  ArrayListに追加する。<BR>
     * <BR>
     * ２）管理者・株式（PTS）約定履歴インスタンスを生成し、下記項目をセットする。<BR>
     * <BR>
     * 　@　@・取消可能フラグ：  false<BR>
     * 　@　@・約定日時：　@引数.約定データ.約定日時<BR>
     * 　@　@・約定株数：　@引数.約定データ.約定株数<BR>
     * 　@　@・約定単価：　@引数.約定データ.約定単価<BR>
     * 　@　@・約定・約定取消区分：　@ "4"(約定取消)<BR>
     * 　@　@・更新者コード：　@ 引数.管理者.get管理者コード()<BR>
     * 　@　@・処理区分：　@"0"(未処理)<BR>
     * <BR>
     * ３）　@２)をArrayListに追加する。<BR>
     * <BR>
     * ４）ArrayListを配列に変換し、返却する。<BR>
     * @@param l_histories - (約定履歴)<BR>
     * 管理者・株式（PTS）約定履歴の配列<BR>
     * @@param l_orderExecution - (約定データ)<BR>
     * 約定データ<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者<BR>
     * @@throws WEB3BaseException
     * @@return WEB3AdminEquityPTSExecHistory[]
     * @@roseuid 4781FD920365
     */
    protected WEB3AdminEquityPTSExecHistory[] getHistoryConfirmComplete(
        WEB3AdminEquityPTSExecHistory[] l_histories,
        OrderExecution l_orderExecution,
        WEB3Administrator l_administrator) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getHistoryConfirmComplete(WEB3AdminEquityPTSExecHistory, OrderExecution, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        if (l_orderExecution == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //ArrayListを生成し、引数.管理者・株式（PTS）約定履歴の配列の全要素をArrayListに追加する
        List l_lisExecHistory = new ArrayList();
        int l_intHistoriesCnt = l_histories.length;
        for (int i = 0; i < l_intHistoriesCnt; i++)
        {
            l_lisExecHistory.add(l_histories[i]);
        }

        //管理者・株式（PTS）約定履歴インスタンスを生成する
        WEB3AdminEquityPTSExecHistory l_equityPTSExecHistory =
            new WEB3AdminEquityPTSExecHistory();

        //  ・取消可能フラグ：  false
        l_equityPTSExecHistory.cancelFlag = false;

        //　@・約定日時：　@引数.約定データ.約定日時
        l_equityPTSExecHistory.executionTimeStamp =
            l_orderExecution.getExecutionTimestamp();

        //　@・約定株数：　@引数.約定データ.約定株数
        l_equityPTSExecHistory.execQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderExecution.getExecutionQuantity());

        //　@・約定単価：　@引数.約定データ.約定単価
        l_equityPTSExecHistory.execPrice =
            WEB3StringTypeUtility.formatNumber(l_orderExecution.getExecutionPrice());

        //　@・約定・約定取消区分：　@ "4"(約定取消)
        l_equityPTSExecHistory.inputExecCancelExecDiv = WEB3DealedTypeDef.CANCEL;

        //　@・更新者コード：　@ 引数.管理者.get管理者コード()
        l_equityPTSExecHistory.updaterCode = l_administrator.getAdministratorCode();

        //　@・処理区分：　@"0"(未処理)
        l_equityPTSExecHistory.inputExecCancelExecProcDiv = WEB3StatusDef.NOT_DEAL;

        //２)をArrayListに追加する
        l_lisExecHistory.add(l_equityPTSExecHistory);

        //ArrayListを配列に変換し、返却する
        WEB3AdminEquityPTSExecHistory[] l_execHistories =
            new WEB3AdminEquityPTSExecHistory[l_lisExecHistory.size()];
        l_lisExecHistory.toArray(l_execHistories);

        log.exiting(STR_METHOD_NAME);
        return l_execHistories;
    }

    /**
     * (insert出来通知)<BR>
     * 株式出来通知キューテーブルに1件データを登録する。<BR>
     * <BR>
     * １）株式出来通知キューParamsを生成し、プロパティをセットする。<BR>
     * <BR>
     * ※設定値はDB更新仕様「出来取消_株式出来通知キューテーブル」<BR>
     * 参照<BR>
     * <BR>
     * <BR>
     * ２）QueryProcessor.doInsertQuery()をコールする。 <BR>
     * <BR>
     * 　@　@[引数]   <BR>
     * 　@　@arg0：　@株式出来通知キューParams<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_orderExecution - (約定データ)<BR>
     * 約定データ<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47832C4B01FD
     */
    protected void insertExecNotify(EqTypeOrderUnit l_eqTypeOrderUnit,
        OrderExecution l_orderExecution, WEB3GentradeMainAccount l_mainAccount,
        WEB3Administrator l_administrator) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertExecNotify(EqTypeOrderUnit, OrderExecution, WEB3GentradeMainAccount, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //株式出来通知キューParamsを生成し、プロパティをセットする
        // ※設定値はDB更新仕様「出来取消_株式出来通知キューテーブル」参照
        HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();

        // データコード AI811(固定)
        l_hostEquityOrderExecNotifyParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_EXEC_NOTICE);

        // 証券会社コード 顧客の証券会社コード
        l_hostEquityOrderExecNotifyParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());

        // 部店コード 顧客の部店コード
        l_hostEquityOrderExecNotifyParams.setBranchCode(l_mainAccount.getBranch().getBranchCode());

        // 顧客コード 顧客の口座コード
        l_hostEquityOrderExecNotifyParams.setAccountCode(l_mainAccount.getAccountCode());

        // 扱者コード null
        l_hostEquityOrderExecNotifyParams.setTraderCode(null);

        // 識別コード 注文単位の識別コード
        //注文単位
        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        l_hostEquityOrderExecNotifyParams.setOrderRequestNumber(l_eqOrderUnitRow.getOrderRequestNumber());

        //約定株数 約定データの約定株数
        l_hostEquityOrderExecNotifyParams.setExecQuantity(l_orderExecution.getExecutionQuantity());

        // 約定単価 約定データの約定単価
        l_hostEquityOrderExecNotifyParams.setExecPrice(l_orderExecution.getExecutionPrice());

        // 約定日時 約定データの約定日時
        l_hostEquityOrderExecNotifyParams.setExecTimestamp(l_orderExecution.getExecutionTimestamp());

        //出来通知区分 4：約定取消
        l_hostEquityOrderExecNotifyParams.setDealedType(WEB3DealedTypeDef.CANCEL);

        //仮想サーバNo.（市場）null
        l_hostEquityOrderExecNotifyParams.setVirtualServerNumberMarket(null);

        //通知種別 null
        l_hostEquityOrderExecNotifyParams.setNoticeType(null);

        //通知番号 null
        l_hostEquityOrderExecNotifyParams.setNoticeNumber(null);

        //約定通知番号 null
        l_hostEquityOrderExecNotifyParams.setExecNumber(null);

        //処理区分 0（固定）
        l_hostEquityOrderExecNotifyParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        // 作成日付 現在日時
        l_hostEquityOrderExecNotifyParams.setCreatedTimestamp(l_tsSystemTimestamp);

        // 更新日付 現在日時
        l_hostEquityOrderExecNotifyParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

        // 更新者コード 管理者コード
        l_hostEquityOrderExecNotifyParams.setLastUpdater(l_administrator.getAdministratorCode());
        try
        {
            //QueryProcessor.doInsertQuery()をコールする
            //[引数]
            //arg0：　@株式出来通知キューParams
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

    /**
     * (validate管理者権限)<BR>
     * 管理者・部店権限をチェックする。<BR>
     * <BR>
     * 1）　@管理者.validate権限()をコールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@機@能カテゴリコード：　@機@能カテゴリコード.国内株式（出来入力・出来取消）<BR>
     * 　@　@is更新：　@true(更新あり)<BR>
     * <BR>
     * 　@　@※機@能カテゴリコードは、<BR>
     * 　@　@DBレイアウト「管理者権限テーブル.xls#（補足資料）機@能カテゴリコード一覧」参照。<BR>
     * <BR>
     * ２）　@拡張アカウントマネージャ.getBranch()をコールする。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@arg0：　@引数.部店ID<BR>
     * <BR>
     * ３）　@管理者.validate部店権限()をコールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@部店コード：　@getBranch().get部店コード()の戻り値<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者<BR>
     * @@param l_lngBranchId - (部店ID)<BR>
     * 部店ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4785C65902BF
     */
    protected void validateManagerAuthority(WEB3Administrator l_administrator,
        long l_lngBranchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateManagerAuthority(WEB3Administrator, long)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeBranch l_branch = null;

        //管理者.validate権限()をコールする
        //[引数]
        //機@能カテゴリコード：　@機@能カテゴリコード.国内株式（出来入力・出来取消）
        //is更新：　@true(更新あり)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_EXEC_INPUT_CANCEL, true);

        //拡張アカウントマネージャ.getBranch()をコールする
        //[引数]
        //arg0：　@引数.部店ID
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

        //引数.管理者.validate部店権限()をコールする
        //[引数]
        //部店コード：　@getBranch().get部店コード()の戻り値
        l_administrator.validateBranchPermission(l_branch.getBranchCode());

        log.exiting(STR_METHOD_NAME);
    }
}
@
