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
filename	WEB3AdminEquityForcedSettleOrderApproveServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済仮注文承認／非承認サービスImpl(WEB3AdminEquityForcedSettleOrderApproveServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 張騰宇 (中訊) 新規作成 仕様変更モデルNo.129
Revision History : 2007/05/16 張騰宇 (中訊) モデル152
Revision History : 2007/07/24 何文敏 (中訊) 新規作成　@仕様変更モデルNo.159
Revision History : 2008/11/20 呉鵬順 (中訊) モデル212
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.comm.client.CommunicationException;
import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.ServerConnector;
import com.fitechlabs.xtrade.kernel.comm.client.ServerException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.data.db.ServerConfigRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DaemonTriggerTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3ServerUrlAccessorDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityApproveTypeDef;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleTemporaryOrderUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveService;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.system.tune.affinity.message.WEB3AffinityDescendRequest;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・強制決済仮注文承認／非承認サービスImpl)<BR>
 * 管理者・強制決済仮注文承認／非承認サービス実装クラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderApproveServiceImpl extends WEB3ClientRequestService
    implements WEB3AdminEquityForcedSettleOrderApproveService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveServiceImpl.class);

    /**
     * ServerAccessorオブジェクト <BR>
     * <BR>
     * 承認／非承認処理を各サーバに振り分ける。<BR>
     * ※初回実行時にセットされる。<BR>
     */
    private ServerAccessor accessor;

    /**
     * @@roseuid 462CA424017C
     */
    public WEB3AdminEquityForcedSettleOrderApproveServiceImpl()
    {

    }

    /**
     * 強制決済仮注文承認／非承認処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・強制決済仮注文承認／非承認確認リクエストの場合<BR>
     * 　@　@this.validate承認／非承認()をコールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@リクエストデータ:　@パラメータ.リクエストデータ<BR>
     * <BR>
     * ○管理者・強制決済仮注文承認／非承認処理起動リクエストの場合<BR>
     * 　@　@this.run承認／非承認()をコールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@リクエストデータ:　@パラメータ.リクエストデータ<BR>
     * <BR>
     * ○管理者・強制決済仮注文承認／非承認処理ステータス確認リクエストの場合<BR>
     * 　@　@this.validate処理ステータス()をコールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@リクエストデータ:　@パラメータ.リクエストデータ<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 460323DE036B
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

        //管理者・強制決済仮注文承認／非承認確認リクエストの場合
        if (l_request instanceof WEB3AdminForcedSettleApproveConfirmRequest)
        {
            l_response = this.validateApprove((WEB3AdminForcedSettleApproveConfirmRequest)l_request);
        }
        //管理者・強制決済仮注文承認／非承認処理起動リクエストの場合
        else if (l_request instanceof WEB3AdminForcedSettleApproveRunRequest)
        {
            l_response = this.runApprove((WEB3AdminForcedSettleApproveRunRequest)l_request);
        }
        //管理者・強制決済仮注文承認／非承認処理ステータス確認リクエストの場合
        else if (l_request instanceof WEB3AdminForcedSettleApproveStatusRequest)
        {
            l_response = this.validateStatus((WEB3AdminForcedSettleApproveStatusRequest)l_request);
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
     * (validate承認／非承認)<BR>
     * 強制決済仮注文承認／非承認確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者強制決済仮注文承認／非承認サービス）validate承認／非承認」参照<BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :1.4　@get強制決済注文一覧(注文ID一覧 ：<BR>
     * 　@　@　@　@nullが返却された場合、<BR>
     * 　@　@　@　@「該当注文が存在しません。」の例外をスローする。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException       <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_02086        <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :1.5.4　@(*)処理対象チェック：<BR>
     * 　@　@　@　@(*)is承認／非承認処理対象注文()の戻り値 == falseの場合、<BR>
     * 　@　@　@　@その要素は処理対象外である為、リストから除去する。（返却しない）<BR>
     * 　@　@　@　@※取得したレコードが全て処理対象外だった場合、<BR>
     * 　@　@　@　@　@「該当注文が存在しません。」の例外をスローする。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException       <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_02086         <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・強制決済仮注文承認／非承認確認リクエストオブジェクト<BR>
     * @@return WEB3AdminForcedSettleApproveConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 460320D901C5
     */
    protected WEB3AdminForcedSettleApproveConfirmResponse validateApprove(
        WEB3AdminForcedSettleApproveConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateApprove(WEB3AdminForcedSettleApproveConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminForcedSettleApproveConfirmResponse l_response = null;

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate承認／非承認可能(管理者, WEB3GenRequest)
        this.validateApprovePossibility(l_admin, l_request);

        //get強制決済注文一覧(String[], 強制決済ソートキー[], Long, Long)
        AdminEqForcedSettleOrderRow[] l_forcedSettleOrders =
            WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                l_request.id,
                l_request.sortKeys,
                null,
                null);

        if (l_forcedSettleOrders == null)
        {
            log.debug("該当注文が存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02086,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当注文が存在しません。");
        }

        int l_intLength = l_forcedSettleOrders.length;
        ArrayList l_lisForcedSettleOrderList = new ArrayList();

        //get強制決済注文一覧()の戻り値の要素数分、Loop処理
        for (int i = 0; i < l_intLength; i++)
        {
            AdminEqForcedSettleOrderRow l_forcedSettleOrderRow = l_forcedSettleOrders[i];

            //get証券会社コード( )
            String l_strInstitutionCode = l_admin.getInstitutionCode();

            //reset取引カレンダコンテキスト(String, Long, Long, String)
            //   [引数]
            //   証券会社コード：　@get証券会社コード()の戻り値
            //   部店ID：　@処理対象の要素.部店ID
            //   市場ID：　@処理対象の要素.市場ID
            //   受付時間区分：　@"株式・信用"
            WEB3AdminPMEquityDataManager.resetTradingCalContext(
                l_strInstitutionCode,
                new Long(l_forcedSettleOrderRow.getBranchId()),
                new Long(l_forcedSettleOrderRow.getMarketId()),
                WEB3TradingTimeTypeDef.EQUITY);

            //is承認／非承認処理対象注文(EqtypeOrderUnitRow)
            //[引数]
            //注文単位Row：　@処理対象の要素.注文単位IDに該当する注文単位Rowオブジェクト
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            EqTypeOrderUnit l_eqOrderUnit = null;
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            try
            {
                l_eqOrderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_forcedSettleOrderRow.getOrderUnitId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();
            boolean l_blnIsApproveProcessTargetOrder =
                WEB3AdminPMEquityDataManager.isApproveProcessTargetOrder(l_eqtypeOrderUnitRow);

            //is承認／非承認処理対象注文()の戻り値 == falseの場合
            //その要素は処理対象外である為、リストから除去する。（返却しない）
            if (l_blnIsApproveProcessTargetOrder)
            {
                l_lisForcedSettleOrderList.add(l_forcedSettleOrders[i]);
            }
        }
        //※取得したレコードが全て処理対象外だった場合、
        //　@「該当注文が存在しません。」の例外をスローする。
        if (l_lisForcedSettleOrderList == null || l_lisForcedSettleOrderList.size() == 0)
        {
            log.debug("該当注文が存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02086,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当注文が存在しません。");
        }

        int l_intSize = l_lisForcedSettleOrderList.size();
        AdminEqForcedSettleOrderRow[] l_newForcedSettleOrders =
            new AdminEqForcedSettleOrderRow[l_intSize];
        l_lisForcedSettleOrderList.toArray(l_newForcedSettleOrders);

        //create強制決済注文照会情報一覧(強制決済注文Row[], String)
        //仮注文一覧：　@処理対象と判別された強制決済注文の一覧
        //承認区分：　@リクエストデータ.承認区分
        WEB3AdminForcedSettleTemporaryOrderUnit[] l_forcedSettleTemporaryOrderUnitList =
            WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(
                l_newForcedSettleOrders,
                l_request.approveType);

        // createResponse( )
        l_response = (WEB3AdminForcedSettleApproveConfirmResponse)l_request.createResponse();

        //現在時刻          ＝　@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        //強制決済仮注文一覧 ＝　@create強制決済注文照会情報一覧()の戻り値
        l_response.forcedSettleTemporaryOrderList = l_forcedSettleTemporaryOrderUnitList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (run承認／非承認)<BR>
     * 強制決済仮注文承認／非承認処理起動を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者強制決済仮注文承認／非承認サービス）run承認／非承認」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・強制決済仮注文承認／非承認処理起動リクエストオブジェクト<BR>
     * @@return WEB3AdminForcedSettleApproveLapseRunResponse
     * @@throws WEB3BaseException
     * @@roseuid 460320D901F4
     */
    protected WEB3AdminForcedSettleApproveRunResponse runApprove(
        WEB3AdminForcedSettleApproveRunRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "runApprove(WEB3AdminForcedSettleApproveRunRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate承認／非承認可能(管理者, WEB3GenRequest)
        this.validateApprovePossibility(l_admin, l_request);

        //get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        // deleteオンライン実行結果(String, String)
        //[引数]
        //証券会社コード：　@get証券会社コード()の戻り値
        //承認区分：　@リクエストデータ.承認区分
        this.deleteOnlineRunResult(l_strInstitutionCode, l_request.approveType);

        //getデーモントリガー一覧(String)
        List l_lisDaemonTriggerUnits = this.getDaemonTriggerUnits(l_request.approveType);

        //getServerAccessor
        ServerAccessor l_serverAccessor = this.getServerAccessor();

        //getデーモントリガー一覧()の戻り値の要素数分、Loop処理
        int l_intSize = l_lisDaemonTriggerUnits.size();
        try
        {
            for (int i = 0; i < l_intSize; i++)
            {
                DaemonTriggerRow l_row = (DaemonTriggerRow)l_lisDaemonTriggerUnits.get(i);
                //updateAP呼出中(long)
                //[引数]
                //スレッドNo：　@処理対象の要素.スレッド番号
                this.updateAPCalling(l_row.getThreadNo());

                //管理者・強制決済仮注文承認／非承認メインリクエスト( )
                WEB3AdminEquityForcedSettleOrderApproveMainRequest l_mainRequest =
                    new WEB3AdminEquityForcedSettleOrderApproveMainRequest();

                //証券会社コード   ＝　@get証券会社コード()の戻り値
                l_mainRequest.institutionCode = l_strInstitutionCode;

                //スレッドNo        ＝　@処理対象の要素.スレッド番号
                l_mainRequest.threadNo = new Long(l_row.getThreadNo());

                //From口座ID  ＝　@処理対象の要素.顧客コード（自）
                l_mainRequest.rangeFrom = new Long(l_row.getRangeFrom());

                //To口座ID        ＝　@処理対象の要素.顧客コード（至）
                l_mainRequest.rangeTo = new Long(l_row.getRangeTo());

                //承認区分      ＝　@リクエストデータ.承認区分
                l_mainRequest.approveType = l_request.approveType;

                //注文ID一覧    ＝　@リクエストデータ.ID
                l_mainRequest.orderIdList = l_request.id;

                //管理者ID    =     取得した管理者.管理者ID
                l_mainRequest.administratorId = new Long(l_admin.getAdministratorId());

                //WEB3AffinityDescendRequest()
                WEB3AffinityDescendRequest l_descendRequest = new WEB3AffinityDescendRequest();
                l_descendRequest.account_id_range = l_row.getRangeFrom() + "," + l_row.getRangeTo();
                WEB3AdminEquityForcedSettleOrderApproveMainRequest[] l_mainRequests =
                    new WEB3AdminEquityForcedSettleOrderApproveMainRequest[1];
                l_mainRequests[0] = l_mainRequest;
                l_descendRequest.request = l_mainRequests;

                //doRequest(arg0 : Request)
                //[引数]
                //arg0：　@生成したリクエストデータ（WEB3AffinityDescendRequest）
                l_serverAccessor.doRequest(l_descendRequest);
            }
        }
        catch (CommunicationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (ServerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // createResponse( )
        WEB3AdminForcedSettleApproveRunResponse l_response =
            (WEB3AdminForcedSettleApproveRunResponse)l_request.createResponse();

        //現在時刻      ＝　@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate処理ステータス)<BR>
     * 強制決済仮注文承認／非承認の処理ステータスを確認する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者強制決済仮注文承認／非承認サービス）validate処理ステータス」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・強制決済仮注文承認／非承認処理ステータス確認リクエストオブジェクト<BR>
     * @@return WEB3AdminForcedSettleApproveLapseStatusResponse
     * @@throws WEB3BaseException
     * @@roseuid 460320D90203
     */
    protected WEB3AdminForcedSettleApproveStatusResponse validateStatus(
        WEB3AdminForcedSettleApproveStatusRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "runApprove(WEB3AdminForcedSettleApproveStatusRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード：　@機@能カテゴリコード.国内株式（強制決済）
        //is更新：　@true（更新あり）
        l_admin.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_FORCED_SETTLE, true);

        //getデーモントリガー一覧(String)
        List l_lisDaemonTriggerUnits = this.getDaemonTriggerUnits(l_request.approveType);

        //get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //getオンライン実行結果一覧(String, String)
        //[引数]
        //証券会社コード：　@get証券会社コード()の戻り値
        //承認区分：　@リクエストデータ.承認区分
        List l_lisOnlineRunResultUnits =
            this.getOnlineRunResultUnits(l_strInstitutionCode, l_request.approveType);

        //createResponse( )
        WEB3AdminForcedSettleApproveStatusResponse l_response =
            (WEB3AdminForcedSettleApproveStatusResponse)l_request.createResponse();

        //処理ステータス   ＝　@以下の分岐によりセットする。
        //　@①@"処理中"をセットする条件
        //　@　@・オンライン実行結果レコードが取得できなかった場合
        //　@　@・取得したデーモントリガーレコードの件数と、オンライン実行結果レコードの件数が異なる場合
        //　@　@・取得したデーモントリガーレコード.処理状態に"処理中"が1件でも存在する場合
        //　@　@・取得したオンライン実行結果レコード.実行ステータス区分に"処理中"が1件でも存在する場合
        //　@②"処理済"をセットする条件
        //　@　@・取得した全てのデーモントリガーレコード.処理状態 == "未稼動"　@かつ
        //　@　@　@取得した全てのオンライン実行結果レコード.実行ステータス区分 == "処理済"
        //　@③"エラー"をセットする条件
        //　@　@①@、②以外の場合
        if (l_lisOnlineRunResultUnits == null || l_lisDaemonTriggerUnits.size() != l_lisOnlineRunResultUnits.size())
        {
            l_response.lapseStatus = WEB3RunStatusDivDef.DEALING;
        }
        else
        {
            int l_intSize = l_lisDaemonTriggerUnits.size();
            DaemonTriggerRow l_daemonTriggerRow = null;
            OnlineRunStatusRow l_onlineRunStatusRow = null;
            int l_intFlag = 0;
            for (int i = 0; i < l_intSize; i++)
            {
                l_daemonTriggerRow = (DaemonTriggerRow)l_lisDaemonTriggerUnits.get(i);
                l_onlineRunStatusRow = (OnlineRunStatusRow)l_lisOnlineRunResultUnits.get(i);
                //　@・取得したデーモントリガーレコード.処理状態に"処理中"が1件でも存在する場合
                //　@・取得したオンライン実行結果レコード.実行ステータス区分に"処理中"が1件でも存在する場合
                if (WEB3DaemonTriggerStatusDef.THREAD_PROCESSING.equals(l_daemonTriggerRow.getTriggerStatus())
                    || WEB3RunStatusDivDef.DEALING.equals(l_onlineRunStatusRow.getRunStatusDiv()))
                {
                    l_intFlag = 1;
                    break;
                }
                else if (!WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus())
                    || !WEB3RunStatusDivDef.DEALED.equals(l_onlineRunStatusRow.getRunStatusDiv()))
                {
                    l_intFlag = 2;
                }
            }
            //①@"処理中"をセットする条件
            if (l_intFlag == 1)
            {
                l_response.lapseStatus = WEB3RunStatusDivDef.DEALING;
            }
            //　@②"処理済"をセットする条件
            //　@・取得した全てのデーモントリガーレコード.処理状態 == "未稼動"　@かつ
            //　@取得した全てのオンライン実行結果レコード.実行ステータス区分 == "処理済"
            else if (l_intFlag == 0)
            {
                l_response.lapseStatus = WEB3RunStatusDivDef.DEALED;
            }
            //"エラー"をセットする条件
            //以外の場合
            else if (l_intFlag == 2)
            {
                l_response.lapseStatus = WEB3RunStatusDivDef.ERROR;
            }
        }

        //現在時刻      ＝　@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate承認／非承認可能)<BR>
     * 強制決済承認／非承認処理が実行可能かどうかチェックする。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者強制決済仮注文承認／非承認サービス）validate承認／非承認可能」参照。<BR>
     * <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :1.7.(*)二重起動チェック<BR>
     * 　@　@　@　@以下の条件に該当しない場合は、<BR>
     * 　@　@　@　@「指定AP起動中（二重起動エラー）。」の例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@①@getオンライン実行結果一覧()の戻り値 == null<BR>
     * 　@　@　@　@②getオンライン実行結果一覧()の戻り値の件数 ==<BR>
     * 　@　@　@　@getデーモントリガー一覧()の戻り値の件数 かつ<BR>
     * 　@　@　@　@getオンライン実行結果一覧()の戻り値の各要素の<BR>
     * 　@　@　@　@実行ステータス区分に"処理中"が存在しない かつ<BR>
     * 　@　@　@　@getデーモントリガー一覧()の戻り値の各要素の<BR>
     * 　@　@　@　@処理状態が"未稼動"。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException       <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_01992         <BR>
     * =============================================== <BR>
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータオブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 460320D90232
     */
    protected void validateApprovePossibility(WEB3Administrator l_admin, WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateApprovePossibility(WEB3Administrator, WEB3GenRequest)";
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

        //(*)リクエストの型をinstanceofにて判別し、
        //　@確認リクエスト or 処理起動リクエストにキャストする。
        //　@キャストした各リクエストから以下の項目を取得し、変数に格納しておくこと。
        //　@　@・リクエスト.承認区分
        WEB3AdminForcedSettleApproveConfirmRequest l_confirmRequest = null;
        WEB3AdminForcedSettleApproveRunRequest l_runRequest = null;
        String l_strApproveType = "";
        if (l_request instanceof WEB3AdminForcedSettleApproveConfirmRequest)
        {
            l_confirmRequest = (WEB3AdminForcedSettleApproveConfirmRequest)l_request;
            l_strApproveType = l_confirmRequest.approveType;
        }
        else if (l_request instanceof WEB3AdminForcedSettleApproveRunRequest)
        {
            l_runRequest = (WEB3AdminForcedSettleApproveRunRequest)l_request;
            l_strApproveType = l_runRequest.approveType;
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

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード：　@機@能カテゴリコード.国内株式（強制決済）
        //is更新：　@true（更新あり）
        l_admin.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_FORCED_SETTLE, true);

        // (*)処理起動リクエストの場合
        if (l_request instanceof WEB3AdminForcedSettleApproveRunRequest)
        {
            // validate取引パスワード(パスワード : String)
            //[引数]  パスワード：　@処理起動リクエスト.暗証番号
            l_admin.validateTradingPassword(l_runRequest.password);
        }

        //getデーモントリガー一覧(String)
        List l_lisDaemonTriggerUnits = this.getDaemonTriggerUnits(l_strApproveType);

        //get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //getオンライン実行結果一覧(String, String)
        //[引数]
        //証券会社コード：　@get証券会社コード()の戻り値
        //承認区分：　@取得した承認区分
        List l_lisOnlineRunResultUnits = this.getOnlineRunResultUnits(l_strInstitutionCode, l_strApproveType);

        //二重起動チェック
        //以下の条件に該当しない場合は、
        //「指定AP起動中（二重起動エラー）。」の例外をスローする。
        //①@getオンライン実行結果一覧()の戻り値 == null
        //②getオンライン実行結果一覧()の戻り値の件数 ==
        //　@getデーモントリガー一覧()の戻り値の件数 かつ
        //　@getオンライン実行結果一覧()の戻り値の各要素の
        //　@実行ステータス区分に"処理中"が存在しない かつ
        //　@getデーモントリガー一覧()の戻り値の各要素の
        //　@処理状態が"未稼動"。
        if (l_lisOnlineRunResultUnits != null)
        {
            if (l_lisOnlineRunResultUnits.size() != l_lisDaemonTriggerUnits.size())
            {
                log.debug("指定AP起動中（二重起動エラー）。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定AP起動中（二重起動エラー）。");
            }
            else
            {
                OnlineRunStatusRow l_onlineRunStatusRow = null;
                DaemonTriggerRow l_daemonTriggerRow = null;
                for (int i = 0; i < l_lisOnlineRunResultUnits.size(); i++)
                {
                    l_onlineRunStatusRow = (OnlineRunStatusRow)l_lisOnlineRunResultUnits.get(i);
                    l_daemonTriggerRow = (DaemonTriggerRow)l_lisDaemonTriggerUnits.get(i);
                    if (WEB3RunStatusDivDef.DEALING.equals(l_onlineRunStatusRow.getRunStatusDiv())
                        || !WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus()))
                    {
                        log.debug("指定AP起動中（二重起動エラー）。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "指定AP起動中（二重起動エラー）。");
                    }
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getデーモントリガー一覧)<BR>
     * 引数の条件に該当するデーモントリガーテーブルの<BR>
     * レコードを返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@以下の条件でデーモントリガーテーブルを検索する。<BR>
     * 　@[条件]<BR>
     * 　@　@[パラメータ.承認区分 == "承認"の場合]<BR>
     * 　@　@　@処理タイプ = "強制決済（承認）"<BR>
     * 　@　@[パラメータ.承認区分 == "非承認"の場合]<BR>
     * 　@　@　@処理タイプ = "強制決済（非承認）"<BR>
     * <BR>
     * 　@[ソート条件]<BR>
     * 　@　@スレッド番号　@昇順<BR>
     * <BR>
     * 　@該当データなしの場合、「該当データなし」の<BR>
     * 　@システムエラーをスローする。<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * @@param l_strApproveType - (承認区分)<BR>
     * 承認区分<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 460320D90251
     */
    protected List getDaemonTriggerUnits(String l_strApproveType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDaemonTriggerUnits(String)";
        log.entering(STR_METHOD_NAME);

        //１）　@DB検索
        //　@以下の条件でデーモントリガーテーブルを検索する。
        //　@[条件]
        //　@　@[パラメータ.承認区分 == "承認"の場合]
        //　@　@　@処理タイプ = "強制決済（承認）"
        //　@　@[パラメータ.承認区分 == "非承認"の場合]
        //　@　@　@処理タイプ = "強制決済（非承認）"
        String l_strTriggerType = null;
        if (WEB3AdminEquityApproveTypeDef.APPROVE.equals(l_strApproveType))
        {
            l_strTriggerType = WEB3DaemonTriggerTypeDef.FORCED_SETTLE_APPROVAL;
        }
        else if (WEB3AdminEquityApproveTypeDef.DISAPPROVE.equals(l_strApproveType))
        {
            l_strTriggerType = WEB3DaemonTriggerTypeDef.FORCED_SETTLE_UNAPPROVAL;
        }
        Object[] l_objValues = {l_strTriggerType};

        //　@[ソート条件]
        //　@　@スレッド番号　@昇順
        String l_strCondition = "thread_no asc";

        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                DaemonTriggerRow.TYPE,
                " trigger_type = ?",
                l_strCondition,
                null,
                l_objValues);
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

        //　@該当データなしの場合、「該当データなし」の
        //　@システムエラーをスローする。
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }

    /**
     * (getオンライン実行結果一覧)<BR>
     * 引数の条件に該当するオンライン実行結果テーブルの<BR>
     * レコードを返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@以下の条件でオンライン実行結果テーブルを検索する。<BR>
     * 　@[条件]<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@　@銘柄タイプ = "株式"<BR>
     * 　@　@先物／オプション区分 = "DEFAULT"<BR>
     * 　@　@[パラメータ.承認区分 == "承認"の場合]<BR>
     * 　@　@　@オンラインサービス区分 = "強制決済（承認）"<BR>
     * 　@　@[パラメータ.承認区分 == "非承認"の場合]<BR>
     * 　@　@　@オンラインサービス区分 = "強制決済（非承認）"<BR>
     * <BR>
     * 　@該当データなしの場合、nullを返却する。<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strApproveType - (承認区分)<BR>
     * 承認区分<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 460320D90271
     */
    protected List getOnlineRunResultUnits(String l_strInstitutionCode, String l_strApproveType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOnlineRunResultUnits(String, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@DB検索
        //　@以下の条件でオンライン実行結果テーブルを検索する。
        //　@[条件]
        //　@　@証券会社コード = パラメータ.証券会社コード
        //　@　@銘柄タイプ = "株式"
        //　@　@先物／オプション区分 = "DEFAULT"
        //　@　@[パラメータ.承認区分 == "承認"の場合]
        //　@　@　@オンラインサービス区分 = "強制決済（承認）"
        //　@　@[パラメータ.承認区分 == "非承認"の場合]
        //　@　@　@オンラインサービス区分 = "強制決済（非承認）"
        String l_strWhere = " institution_code = ? and product_type = ? " +
            "and future_option_div = ? and online_service_div = ?";

        String l_strOnlineServiceDiv = null;
        if (WEB3AdminEquityApproveTypeDef.APPROVE.equals(l_strApproveType))
        {
            l_strOnlineServiceDiv = WEB3OnlineServiceDiv.FORCED_SETTLE_APPROVAL;
        }
        else if (WEB3AdminEquityApproveTypeDef.DISAPPROVE.equals(l_strApproveType))
        {
            l_strOnlineServiceDiv = WEB3OnlineServiceDiv.FORCED_SETTLE_UNAPPROVAL;
        }

        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(ProductTypeEnum.EQUITY);
        l_lisBindValues.add(WEB3FuturesOptionDivDef.DEFAULT);
        l_lisBindValues.add(l_strOnlineServiceDiv);
        Object[] l_bindValues = new Object[l_lisBindValues.size()];
        l_lisBindValues.toArray(l_bindValues);

        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                OnlineRunStatusRow.TYPE,
                l_strWhere,
                l_bindValues);
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
        //　@該当データなしの場合、nullを返却する。
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //２）　@検索結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }

    /**
     * (deleteオンライン実行結果)<BR>
     * 条件に該当するオンライン実行結果テーブルの<BR>
     * レコードを物理削除する。<BR>
     * <BR>
     * １）　@以下の条件に該当するオンライン実行結果テーブルの<BR>
     * 　@レコードをdeleteする。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@　@銘柄タイプ = "株式"<BR>
     * 　@　@先物／オプション区分 = "DEFAULT"<BR>
     * 　@　@オンラインサービス区分 = <BR>
     * 　@　@　@[パラメータ.承認区分 == "承認"の場合]<BR>
     * 　@　@　@　@"強制決済（承認）"<BR>
     * 　@　@　@[パラメータ.承認区分 == "非承認"の場合]<BR>
     * 　@　@　@　@"強制決済（非承認）"<BR>
     * <BR>
     * 　@※削除対象のレコードがなくても例外としないこと。<BR>
     * 　@※本処理は新規トランザクションで処理を行い、<BR>
     * 　@　@処理完了時に更新が反映されるようにすること。<BR>
     * 　@　@（参考：WEB3GentradeDaemonTriggerManager.startThread()）<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strApproveType - (承認区分)<BR>
     * 承認区分<BR>
     * @@throws WEB3BaseException
     * @@roseuid 460320D90290
     */
    protected void deleteOnlineRunResult(String l_strInstitutionCode, String l_strApproveType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteOnlineRunResult(String, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@以下の条件に該当するオンライン実行結果テーブルの
        //　@レコードをdeleteする。
        //
        //　@[条件]
        //　@　@証券会社コード = パラメータ.証券会社コード
        //　@　@銘柄タイプ = "株式"
        //　@　@先物／オプション区分 = "DEFAULT"
        //　@　@オンラインサービス区分 =
        //　@　@　@[パラメータ.承認区分 == "承認"の場合]
        //　@　@　@　@"強制決済（承認）"
        //　@　@　@[パラメータ.承認区分 == "非承認"の場合]
        //　@　@　@　@"強制決済（非承認）"
        final String l_strWhere = " institution_code = ? and product_type = ? " +
            "and future_option_div = ? and online_service_div = ?";
        final String l_strCondition = "for update";

        String l_strOnlineServiceDiv = "";
        if (WEB3AdminEquityApproveTypeDef.APPROVE.equals(l_strApproveType))
        {
            l_strOnlineServiceDiv = WEB3OnlineServiceDiv.FORCED_SETTLE_APPROVAL;
        }
        else if (WEB3AdminEquityApproveTypeDef.DISAPPROVE.equals(l_strApproveType))
        {
            l_strOnlineServiceDiv = WEB3OnlineServiceDiv.FORCED_SETTLE_UNAPPROVAL;
        }

        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(ProductTypeEnum.EQUITY);
        l_lisBindValues.add(WEB3FuturesOptionDivDef.DEFAULT);
        l_lisBindValues.add(l_strOnlineServiceDiv);

        final Object[] l_bindValues = new Object[l_lisBindValues.size()];
        l_lisBindValues.toArray(l_bindValues);

        try
        {
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_queryProcesser.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                new TransactionCallback()
                    {
                        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
                        {
                            List l_lisRows = l_queryProcesser.doFindAllQuery(
                                OnlineRunStatusRow.TYPE,
                                l_strWhere,
                                l_strCondition,
                                l_bindValues);
                            int l_intSize =  l_lisRows.size();
                            for (int i = 0; i < l_intSize; i++)
                            {
                                OnlineRunStatusRow l_row = (OnlineRunStatusRow)l_lisRows.get(i);
                                WEB3DataAccessUtility.deleteRow(l_row);
                            }
                            log.exiting(STR_METHOD_NAME);
                            return null;
                        }
                    }
            );
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
    }

    /**
     * (updateAP呼出中)<BR>
     * 引数のスレッドNoに該当するデーモントリガーの<BR>
     * レコードを、"AP呼出中"でupdateする。<BR>
     * <BR>
     * １）　@以下の条件に該当するデーモントリガーテーブルの<BR>
     * 　@レコードをupdateする。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@スレッド番号 = パラメータ.スレッドNo<BR>
     * <BR>
     * 　@[更新値]<BR>
     * 　@　@処理状態 = "トリガー（AP呼出中）"<BR>
     * <BR>
     * 　@※本処理は新規トランザクションで処理を行い、<BR>
     * 　@　@処理完了時に更新が反映されるようにすること。<BR>
     * 　@　@（参考：WEB3GentradeDaemonTriggerManager.startThread()）<BR>
     * @@param l_lngThreadNo - (スレッドNo)<BR>
     * スレッドNo<BR>
     * @@throws WEB3BaseException
     * @@roseuid 460320D902AF
     */
    protected void updateAPCalling(long l_lngThreadNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateAPCalling(long)";
        log.entering(STR_METHOD_NAME);

        final int UPDATE_SUCCESS = 0; // 更新処理が正常終了した値
        final int UPDATE_FAIL = -1; // 更新対象スレッド情報の更新に失敗した場合の値
        final int NO_ROWS = -2; // 更新対象スレッド情報を取得できなかった場合の値

        //引数のスレッドNoに該当するデーモントリガーの
        //レコードを、"AP呼出中"でupdateする。
        //１）　@以下の条件に該当するデーモントリガーテーブルの
        //　@レコードをupdateする。
        //　@[条件]
        //　@　@スレッド番号 = パラメータ.スレッドNo
        //　@[更新値]
        //　@　@処理状態 = "トリガー（AP呼出中）"
        //　@※本処理は新規トランザクションで処理を行い、
        //　@　@処理完了時に更新が反映されるようにすること。
        //　@　@（参考：WEB3GentradeDaemonTriggerManager.startThread()）
        try
        {
            final HashMap l_changes = new HashMap();
            l_changes.put("trigger_status", WEB3DaemonTriggerStatusDef.THREAD_API_CALL);

            final String l_strWhere = "thread_no = ?";
            final String l_strCondition = "for update";
            final Object l_bindVars[] = {new Long(l_lngThreadNo)};
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            Integer l_intResult = (Integer)
                l_queryProcesser.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    new TransactionCallback()
                    {
                        public Object process() throws DataNetworkException,
                            DataQueryException,
                            DataCallbackException
                        {
                            Integer l_intResult = null;
                            List l_lisRows = l_queryProcesser.doFindAllQuery(
                                DaemonTriggerRow.TYPE,
                                l_strWhere, l_strCondition, l_bindVars);
                            if (l_lisRows != null && l_lisRows.size() > 0)
                            {
                                DaemonTriggerRow l_row =
                                    (DaemonTriggerRow)l_lisRows.get(0);
                                WEB3DataAccessUtility.updateRow(l_row, l_changes);
                                l_intResult = new Integer(UPDATE_SUCCESS);
                            }
                            else
                            {
                                l_intResult = new Integer(NO_ROWS);
                            }
                            return l_intResult;
                        }
                    }
                );

            if (l_intResult.intValue() == UPDATE_FAIL)
            {
                log.error("DBへのアクセスに失敗しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DBへのアクセスに失敗しました。");
            }
            else if (l_intResult.intValue() == NO_ROWS)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
        }
        catch (DataException l_ex)
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
     * 負荷分散処理を行う為のServerAccessorオブジェクトを<BR>
     * 返却する。<BR>
     * <BR>
     * １）　@this.accessor != nullの場合、this.accessorを返却する。<BR>
     * <BR>
     * ２）　@１）以外の場合、以降の手順にてServerAccessor<BR>
     * 　@オブジェクトを取得する。<BR>
     * <BR>
     * ３）　@クラスタリング先サーバーURLを取得する。<BR>
     * 　@　@QueryProcessor.doFindAllQuery()メソッドを<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@　@arg0：　@ServerConfigRow.TYPE<BR>
     * 　@　@　@arg1：　@"config_categ = ?"<BR>
     * 　@　@　@arg2：　@"cluster.urls"のみを要素とする配列<BR>
     * <BR>
     * 　@　@※"cluster.urls"は定数定義クラス参照すること。<BR>
     * <BR>
     * 　@　@検索結果の各要素.config_valueを取得し、文字列配列を<BR>
     * 　@　@作成する。<BR>
     * 　@　@※検索結果が取得できなかった場合、「該当データなし」の<BR>
     * 　@　@　@システムエラーをスローする。<BR>
     * <BR>
     * ４）　@ServerAccessorの作成<BR>
     * 　@ServerConnector.createAccessor()メソッドをコールし、<BR>
     * 　@戻り値をthis.accessorにセットした後、戻り値を返却する。<BR>
     * <BR>
     * 　@[craeteAccessor()にセットするパラメータ]<BR>
     * 　@　@arg0（ACCESSOR_KEY）：　@"web3-static-cluster"<BR>
     * 　@　@arg1（URL）：　@３）にて作成した文字列配列<BR>
     * <BR>
     * 　@　@※"web3-static-cluster"は定数定義クラス参照すること。<BR>
     * @@return ServerAccessor
     * @@throws WEB3BaseException
     * @@roseuid 460320D902EE
     */
    protected ServerAccessor getServerAccessor() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getServerAccessor()";
        log.entering(STR_METHOD_NAME);

        //１）　@this.accessor != nullの場合、this.accessorを返却する。
        if (this.accessor != null)
        {
            log.exiting(STR_METHOD_NAME);
            return this.accessor;
        }
        //２）　@１）以外の場合、以降の手順にてServerAccessor オブジェクトを取得する
        //３）　@クラスタリング先サーバーURLを取得する。
        //　@　@QueryProcessor.doFindAllQuery()メソッドを
        //　@　@コールする。
        //　@　@[doFindAllQuery()にセットするパラメータ]
        //　@　@　@arg0：　@ServerConfigRow.TYPE
        //　@　@　@arg1：　@"config_categ = ?"
        //　@　@　@arg2：　@"cluster.urls"のみを要素とする配列
        //※"cluster.urls"は定数定義クラス参照すること。
        //検索結果の各要素.config_valueを取得し、文字列配列を
        //　@　@作成する。
        //　@　@※検索結果が取得できなかった場合、「該当データなし」の
        //　@　@　@システムエラーをスローする。

        String l_strQueryWhere = "config_categ = ?";
        Object[] l_bindValues = {WEB3ServerUrlAccessorDef.CLUSTER_URLS};
        List l_lisRecords = null;

        try
        {
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcesser.doFindAllQuery(
                ServerConfigRow.TYPE,
                l_strQueryWhere,
                l_bindValues);

            if (l_lisRecords == null || l_lisRecords.size() == 0)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
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

        //４）　@ServerAccessorの作成
        //　@ServerConnector.createAccessor()メソッドをコールし、
        //　@戻り値をthis.accessorにセットした後、戻り値を返却する。
        //　@[craeteAccessor()にセットするパラメータ]
        //　@　@arg0（ACCESSOR_KEY）：　@"web3-static-cluster"
        //　@　@arg1（URL）：　@３）にて作成した文字列配列
        //　@　@※"web3-static-cluster"は定数定義クラス参照すること。
        int l_intLength = l_lisRecords.size();
        ArrayList l_lisConfigValues = new ArrayList();
        for (int i = 0; i < l_intLength; i++)
        {
            ServerConfigRow l_serverConfigRow = (ServerConfigRow)l_lisRecords.get(i);
            l_lisConfigValues.add(l_serverConfigRow.getConfigValue());
        }

        String[] l_strServerConfigList = new String[l_lisConfigValues.size()];
        l_lisConfigValues.toArray(l_strServerConfigList);

        ServerAccessor l_serverAccessor = null;
        try
        {
            l_serverAccessor = ServerConnector.createAccessor(
                WEB3ServerUrlAccessorDef.WEB3_STATIC_CLUSTER,
                l_strServerConfigList);
        }
        catch (CommunicationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (ServerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        this.accessor = l_serverAccessor;

        log.exiting(STR_METHOD_NAME);
        return l_serverAccessor;
    }
}@
