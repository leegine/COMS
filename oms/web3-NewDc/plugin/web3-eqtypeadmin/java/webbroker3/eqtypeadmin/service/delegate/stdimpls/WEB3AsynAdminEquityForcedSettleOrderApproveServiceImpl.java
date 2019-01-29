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
filename	WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （非同期）管理者・強制決済仮注文承認／非承認サービスImpl(WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 張騰宇 (中訊) 新規作成 仕様変更モデルNo.129
Revision History : 2007/05/16 張騰宇 (中訊) モデル152
Revision History : 2008/01/17 張騰宇 (中訊) 仕様変更モデル170 180
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityApproveTypeDef;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveUnitService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.util.WEB3LogUtility;

/**
 * （（非同期）管理者・強制決済仮注文承認／非承認サービスImpl）<BR>
 * （非同期）管理者・強制決済仮注文承認／非承認サービス実装クラス<BR>
 * <BR>
 * 承認／非承認処理を非同期で行う。<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl implements Runnable
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl.class);

    /**
     * (リクエストデータ)<BR>
     * 管理者・強制決済仮注文承認／非承認メインリクエストオブジェクト<BR>
     */
    public WEB3AdminEquityForcedSettleOrderApproveMainRequest request;

    /**
     * @@roseuid 462CAC42039E
     */
    public WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl()
    {

    }

    /**
     * (（非同期）管理者・強制決済仮注文承認／非承認サービスImpl)<BR>
     * デフォルトコンストラクタ。<BR>
     * <BR>
     * 引数をthis.リクエストデータにセットする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・強制決済仮注文承認／非承認メインリクエストオブジェクト<BR>
     * @@return WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl
     * @@roseuid 460329EA02BF
     */
    public WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl(
        WEB3AdminEquityForcedSettleOrderApproveMainRequest l_request)
    {
        this.request = l_request;
    }

    /**
     * （非同期）強制決済仮注文承認／非承認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（非同期）管理者強制決済仮注文承認／非承認）run」参照。<BR>
     * @@roseuid 460329EA029F
     */
    public void run()
    {
        final String STR_METHOD_NAME = " run()";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;    
        List l_lisDaemonTrigger = null;
        boolean l_blnIsExeError = false;

        try
        { 
            //管理者・強制決済仮注文承認／非承認デーモントリガーTransactionCallback(long)
            WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback l_daemonTriggerCallBack = 
                new WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback(
                    this.request.threadNo.longValue());

            //getDefaultProcessor()
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //doTransaction(arg0 : int, arg1 : TransactionCallback)
            l_lisDaemonTrigger = (List)l_queryProcessor.doTransaction(
                QueryProcessor.TX_JOIN_EXISTING,
                l_daemonTriggerCallBack);

            if (l_lisDaemonTrigger == null)
            {
                log.entering(STR_METHOD_NAME);
                return;
            }

            //管理者・強制決済仮注文承認／非承認オンライン実行結果TransactionCallbackを生成する。 
            WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback l_onlineRunStatusCallBack = 
                new WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback(
                    this.request.institutionCode,
                    this.request.rangeFrom.longValue(),
                    this.request.rangeTo.longValue(),
                    this.request.approveType);

            //doTransaction(arg0 : int, arg1 : TransactionCallback)
            l_onlineRunStatus = (WEB3GentradeOnlineRunStatus)l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_onlineRunStatusCallBack);
        }
        catch (DataNetworkException l_dataNetworkException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataNetworkException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataNetworkException.getMessage(),
                l_dataNetworkException);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataQueryException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataQueryException.getMessage(),
                l_dataQueryException);
        }

        AdminEqForcedSettleOrderRow[] l_orderRows = null;
        try
        {
            l_orderRows = WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                this.request.orderIdList, 
                null, 
                this.request.rangeFrom, 
                this.request.rangeTo);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //管理者オブジェクト
        WEB3Administrator l_admin = null;
        try
        {
            l_admin = new WEB3Administrator(this.request.administratorId.longValue());
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get強制決済注文一覧()の戻り値の要素（=強制決済注文Row）数分Loop処理
        if (l_orderRows != null)
        {
            boolean l_blnIsError = false;
            int l_intLength = l_orderRows.length;
            for (int i = 0; i < l_intLength; i++)
            {
                AdminEqForcedSettleOrderRow l_row = l_orderRows[i];

                String l_strApproveType = this.request.approveType;
                WEB3AdminEquityForcedSettleOrderApproveUnitService l_service = 
                    (WEB3AdminEquityForcedSettleOrderApproveUnitService)Services.getService(
                        WEB3AdminEquityForcedSettleOrderApproveUnitService.class);

                //承認処理（this.リクエストデータ.承認区分 == "承認"）の場合
                if (WEB3AdminEquityApproveTypeDef.APPROVE.equals(l_strApproveType))
                {
                    //exec承認(強制決済注文Row)
                    try
                    {
                        l_blnIsError = l_service.execApprove(l_row, l_admin);
                    }
                    catch (Exception l_ex)
                    {
                        log.error("exec承認(強制決済注文Row)呼出時に例外発生", l_ex);
                        l_blnIsError = true;
                    }

                }

                //非承認処理（上記以外）の場合 
                else
                {
                    //exec非承認(強制決済注文Row)
                    try
                    {
                        l_blnIsError = l_service.execDisapprove(l_row, l_admin);
                    }
                    catch (Exception l_ex)
                    {
                        log.error("exec非承認(強制決済注文Row)呼出時に例外発生", l_ex);
                        l_blnIsError = true;
                    }
                }

                if (l_blnIsError)
                {
                    l_blnIsExeError = true;
                }
            }
        }

        //update実行ステータス区分(実行ステータス区分 : String)  
        String l_strRunStatusDiv = null;
        if(l_blnIsExeError)
        {
            l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
        }
        else
        {
            l_strRunStatusDiv = WEB3RunStatusDivDef.DEALED;
        }

        try
        {
            l_onlineRunStatus.updateRunStatusDiv(l_strRunStatusDiv);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //(*)排他ロックしたデーモントリガーテーブルのレコードを"未稼働"でupdateする。
        DaemonTriggerParams l_daemonTriggerParams =
            new DaemonTriggerParams((DaemonTriggerRow) l_lisDaemonTrigger.get(0));

        l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
        //現在時刻をsetする。
        l_daemonTriggerParams.setTriggerDate(GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_daemonTriggerParams);
        }
        catch (DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                new ErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (管理者・強制決済仮注文承認／非承認オンライン実行結果TransactionCallback)<BR>
     * 管理者・強制決済仮注文承認／非承認オンライン実行結果TransactionCallback<BR>
     * <BR>
     * 仮注文承認／非承認の処理開始／終了を<BR>
     * オンライン実行結果テーブルに記録する。<BR>
     */
    public class WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback
        implements TransactionCallback
    {

        /**
         * (証券会社コード)<BR>
         * 証券会社コード<BR>
         */
        public String institutionCode;

        /**
         * (From口座ID)<BR>
         * From口座ID<BR>
         */
        public long accountIdFrom;

        /**
         * (To口座ID)<BR>
         * To口座ID<BR>
         */
        public long accountIdTo;

        /**
         * (承認区分)<BR>
         * 承認区分<BR>
         */
        public String approveType;

        /**
         * @@roseuid 462CAC9D018B
         */
        public WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback()
        {

        }

        /**
         * (管理者・強制決済仮注文承認／非承認オンライン実行結果TransactionCallback)<BR>
         * コンストラクタ。<BR>
         * <BR>
         * 引数を自身の同名項目にセットする。<BR>
         * @@param l_strInstitutionCode - (証券会社コード)<BR>
         * 証券会社コード<BR>
         * @@param l_lngAccountIdFrom - (From口座ID)<BR>
         * From口座ID<BR>
         * @@param l_lngAccountIdTo - (To口座ID)<BR>
         * To口座ID<BR>
         * @@param l_strApproveType - (承認区分)<BR>
         * 承認区分<BR>
         * @@roseuid 46032C62002E
         */
        public WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback(
            String l_strInstitutionCode,
            long l_lngAccountIdFrom,
            long l_lngAccountIdTo,
            String l_strApproveType)
        {
            final String STR_METHOD_NAME =
                " WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback(" +
                "String, long, long, String)";
            log.entering(STR_METHOD_NAME);

            this.institutionCode = l_strInstitutionCode;
            this.accountIdFrom = l_lngAccountIdFrom;
            this.accountIdTo = l_lngAccountIdTo;
            this.approveType = l_strApproveType;

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * オンライン実行結果テーブルの該当レコードを<BR>
         * "処理中"でupdateする。<BR>
         * <BR>
         * １）　@オンライン実行結果.set処理中()メソッドをコールする。<BR>
         * 　@[set処理中()にセットするパラメータ]<BR>
         * 　@　@証券会社コード：　@this.証券会社コード<BR>
         * 　@　@銘柄タイプ：　@"株式"<BR>
         * 　@　@先物／オプション区分：　@"DEFAULT"<BR>
         * 　@　@オンラインサービス区分：<BR>
         * 　@　@　@[this.承認区分 == "承認"の場合]<BR>
         * 　@　@　@　@"強制決済（承認）"<BR>
         * 　@　@　@[this.承認区分 == "非承認"の場合]<BR>
         * 　@　@　@　@"強制決済（非承認）"<BR>
         * 　@　@From口座ID：　@this.From口座ID<BR>
         * 　@　@To口座ID：　@this.To口座ID<BR>
         * <BR>
         * ２）　@set処理中()の戻り値を返却する。<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 46032C62004E
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                //オンライン実行結果テーブルに処理開始レコードを反映する。  
                // 引数は以下の通りにセットする。  
                // [引数]
                //　@ 証券会社コード：　@プロパティ.証券会社コード  
                //　@ 銘柄タイプ：　@"株式"  
                //　@ 先物／オプション区分：　@"DEFAULT" 
                //   オンラインサービス区分：
                //　@　@　@[this.承認区分 == "承認"の場合] 
                //　@　@　@　@"強制決済（承認）" 
                //　@　@　@[this.承認区分 == "非承認"の場合] 
                //　@　@　@　@"強制決済（非承認）" 
                //　@　@From口座ID：　@this.From口座ID 
                //　@　@To口座ID：　@this.To口座ID 
                String l_strApproveType = null;
                if (WEB3AdminEquityApproveTypeDef.APPROVE.equals(this.approveType))
                {
                    l_strApproveType = WEB3OnlineServiceDiv.FORCED_SETTLE_APPROVAL;
                }
                else if (WEB3AdminEquityApproveTypeDef.DISAPPROVE.equals(this.approveType))
                {
                    l_strApproveType = WEB3OnlineServiceDiv.FORCED_SETTLE_UNAPPROVAL;
                }

                l_onlineRunStatus =
                    WEB3GentradeOnlineRunStatus.setDealing(
                        this.institutionCode,
                        ProductTypeEnum.EQUITY,
                        WEB3FuturesOptionDivDef.DEFAULT,
                        l_strApproveType,
                        this.accountIdFrom,
                        this.accountIdTo);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                        new ErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
            }

            //２）１）の戻り値を返す。
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }
    }

    /**
     * (管理者・強制決済仮注文承認／非承認デーモントリガーTransactionCallback)<BR>
     * 管理者・強制決済仮注文承認／非承認デーモントリガーTransactionCallback<BR>
     * （トランザクション属性：TX_JOIN_EXISTING）<BR>
     */
    public class WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback
        implements TransactionCallback
    {

        /**
         * (スレッドNo)<BR>
         * スレッドNo<BR>
         */
        public long threadNo;

        /**
         * @@roseuid 462CAC9D01AA
         */
        public WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback()
        {

        }

        /**
         * (管理者・強制決済仮注文承認／非承認デーモントリガーTransactionCallback)<BR>
         * コンストラクタ。<BR>
         * <BR>
         * 引数を自身の同名項目にセットする。<BR>
         * @@param l_lngThreadNo - (スレッドNo)<BR>
         * スレッドNo
         * @@roseuid 46032B790177
         */
        public WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback(long l_lngThreadNo)
        {
            final String STR_METHOD_NAME =
                " WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback(long)";
            log.entering(STR_METHOD_NAME);

            this.threadNo = l_lngThreadNo;

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * this.スレッドNoに該当するデーモントリガーテーブルの<BR>
         * レコードをロックする。<BR>
         * <BR>
         * １）　@DB検索<BR>
         * 　@以下の条件に該当するデーモントリガーテーブルの<BR>
         * 　@レコードを、"for update nowait"オプションで読み込む。<BR>
         * <BR>
         * 　@[条件]<BR>
         * 　@　@スレッド番号 = this.スレッドNo<BR>
         * <BR>
         * ２）　@検索結果を返却する。<BR>
         * 　@※検索結果が取得できなかった場合、処理スレッドの<BR>
         * 　@占有ロックに失敗した旨をERRORでログに出力し、nullを返却する。<BR>
         * @@return Object
         * @@roseuid 46032B790196
         */
        public Object process()
        {
            //１）　@DB検索
            //以下の条件に該当するデーモントリガーテーブルの
            //レコードを、"for update nowait"オプションで読み込む。
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            try
            {
                String l_strWhere = " thread_no = ? ";
                String l_strCondition = " for update nowait ";
                Object l_bindVars[] = { new Long(threadNo)};
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                List l_lisRows = l_queryProcesser.doFindAllQuery(
                    DaemonTriggerRow.TYPE,
                    l_strWhere,
                    l_strCondition,
                    l_bindVars);

                //２）　@検索結果を返却する。
                //検索結果が取得できなかった場合、処理スレッドの
                // 占有ロックに失敗した旨をERRORでログに出力し、nullを返却する。
                if (l_lisRows.isEmpty())
                {
                    log.error("処理スレッドの占有ロックに失敗した");
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return l_lisRows;
                }
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.error("処理スレッドの占有ロックに失敗した");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }
    }
}
@
