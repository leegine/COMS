head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioBondOnPaymentCooperationServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券出金連携サービスImpl(WEB3AioBondOnPaymentCooperationServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 徐宏偉 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

import webbroker3.aio.WEB3AioBondOnPaymentInfo;
import webbroker3.aio.message.WEB3AioBondOnPaymentCooperationRequest;
import webbroker3.aio.message.WEB3AioBondOnPaymentCooperationResponse;
import webbroker3.aio.service.delegate.WEB3AioBondOnPaymentCooperationService;
import webbroker3.aio.service.delegate.WEB3AioBondOnPaymentCooperationUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondDivDef;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券出金連携サービスImpl)<BR>
 * 債券出金連携サービス実装クラス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioBondOnPaymentCooperationServiceImpl
    implements WEB3AioBondOnPaymentCooperationService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioBondOnPaymentCooperationServiceImpl.class);

    /**
     * 債券出金連携サービス処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（債券出金連携）execute」 参照<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3AioBondOnPaymentCooperationRequest l_bondOnPaymentCooperationRequest = null;
        WEB3AioBondOnPaymentCooperationResponse l_bondOnPaymentCooperationResponse = null;

        if (l_request instanceof WEB3AioBondOnPaymentCooperationRequest)
        {
            //リクエストデータの具象データ型が「 債券出金連携リクエスト 」の場合
            l_bondOnPaymentCooperationRequest =
                (WEB3AioBondOnPaymentCooperationRequest)l_request;
        }
        else
        {
            log.debug("Error[入力値が不正です]");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            //1.1.validate( )
            //リクエストのチェックを行う。
            //簡易チェックのみとする。
            l_bondOnPaymentCooperationRequest.validate();

            //1.2. 債券出金連携TransactionCallback(String)
            //債券出金連携トランザクションコールバックインスタンスを生成する。
            //[引数]
            //証券会社コード： 引数.リクエストデータ.証券会社コード
            WEB3AioBondOnPaymentCooperationTransactionCallback
            l_bondOnPaymentCooperationTransactionCallback =
                new WEB3AioBondOnPaymentCooperationTransactionCallback(
                    l_bondOnPaymentCooperationRequest.institutionCode);

            //1.3.getDefaultProcessor( )
            //クエリプロセッサを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //1.4.doTransaction(トランザクション属性 : int,
            //トランザクションコールバック : TransactionCallback)
            //DBトランザクション処理を実施する。
            //[doTransaction()に指定する引数]
            //トランザクション属性：　@TX_CREATE_NEW
            //トランザクションコールバック：　@債券出金連携TransactionCallbackインスタンス
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_bondOnPaymentCooperationTransactionCallback);

            //1.5.createResponse( )
            l_bondOnPaymentCooperationResponse =
                (WEB3AioBondOnPaymentCooperationResponse)l_request.createResponse();

            //－生成した債券出金連携レスポンスを返す。
            log.exiting(STR_METHOD_NAME);
            return l_bondOnPaymentCooperationResponse;
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
    }

    /**
     * (債券出金連携TransactionCallback)<BR>
     * 債券出金連携トランザクションコールバッククラス<BR>
     */
    public class WEB3AioBondOnPaymentCooperationTransactionCallback
        implements TransactionCallback
    {
        /**
         * ログユーティリティ<BR>
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3AioBondOnPaymentCooperationTransactionCallback.class);

        /**
         * 証券会社コード
         */
        private String institutionCode;

        /**
         * (債券出金連携TransactionCallback)<BR>
         * コンストラクタ<BR>
         * <BR>
         * １）以下のとおりにプロパティをセットする。<BR>
         * <BR>
         * ・this.証券会社コード = 引数.証券会社コード<BR>
         * <BR>
         * @@param l_strInstitutionCode - 証券会社コード
         */
        public WEB3AioBondOnPaymentCooperationTransactionCallback(
            String l_strInstitutionCode)
        {
            final String STR_METHOD_NAME =
                "WEB3AioBondOnPaymentCooperationTransactionCallback(String)";
            m_log.entering(STR_METHOD_NAME);

            //this.証券会社コード = 引数.証券会社コード
            this.institutionCode = l_strInstitutionCode;

            m_log.exiting(STR_METHOD_NAME);
        }

        /**
         * 債券出金連携処理を行う。 <BR>
         * <BR>
         * シーケンス図 <BR>
         * 「（債券出金連携）process」 参照<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         */
        public Object process()
            throws DataNetworkException, DataQueryException
        {
            final String STR_METHOD_NAME = "process()";
            m_log.entering(STR_METHOD_NAME);

            Institution l_institution = null;
            //債券出金連携UnitServiceを取得する。
            WEB3AioBondOnPaymentCooperationUnitService
                l_bondOnPaymentCooperationUnitService =
                    (WEB3AioBondOnPaymentCooperationUnitService)Services.getService(
                        WEB3AioBondOnPaymentCooperationUnitService.class);

            //1.1. getInstitution(証券会社コード : String)
            //証券会社オブジェクトを取得する。
            //[引数]
            //証券会社コード = this.証券会社コード
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            //アカウントマネージャ取得する
            AccountManager l_accountManager = l_finApp.getAccountManager();
            try
            {
                //===========================NotFoundException====================
                l_institution = l_accountManager.getInstitution(this.institutionCode);
            }
            catch (NotFoundException l_ex)
            {
                ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
                l_errorInfo.setErrorClass(l_ex.getClass().getName());
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_errorInfo);
            }
            //1.2.getBranches( )
            //所属する部店オブジェクトを配列で取得する。
            Branch[] l_branchs = l_institution.getBranches();
            int l_intLength = 0;

            if (l_branchs != null && l_branchs.length != 0)
            {
                l_intLength = l_branchs.length;
                log.debug("Branchオブジェクトの配列.length := " + l_intLength);
            }

            //1.3.(*1) 取得した部店配列の要素分ループ
            log.debug("(*1) 取得した部店配列の要素分ループ" + l_intLength);
            for (int i = 0; i < l_intLength; i++)
            {
                List l_lisBondOrderUnitRows = null;

                Map l_japaneseOrderMap = null;
                try
                {
                    //1.3.1.is債券出金連携実行(long)
                    //債券出金連携を実行するかどうかを判定する。
                    //[引数]
                    //部店ID： 部店[index].get部店ID()
                    log.debug("部店ID： 部店[index].get部店ID()" + l_branchs[i].getBranchId());
                    boolean l_blnBondOnPaymentCooperationExecute =
                        this.isBondOnPaymentCooperationExecute(l_branchs[i].getBranchId());

                    //1.3.2.債券出金連携を実行しない場合
                    if (!l_blnBondOnPaymentCooperationExecute)
                    {
                        //1.3.2.1.continue
                        log.debug("債券出金連携を実行しない場合");
                        continue;
                    }
                    //1.3.3.円貨注文Mapオブジェクトの生成
                    //顧客別に円貨注文のGrossを管理するHashMap
                    //key： 口座ID
                    //value： 債券出金情報
                    l_japaneseOrderMap = new HashMap();

                    //1.3.4.(*2) 債券注文単位テーブル読込み
                    //    (*2) 以下の条件で、債券注文単位テーブルから
                    // 　@　@　@行ロックオプションにてレコードを取得する。
                    // [検索条件]
                    // 部店ID = 部店[index].get部店ID()
                    // 注文種別 = 402（債券売り注文）
                    // 受渡日 = 現在日付の翌営業日
                    // 注文約定区分 = 1（約定済）
                    StringBuffer l_sbWhere = new StringBuffer();
                    l_sbWhere.append(" branch_id = ? ");
                    l_sbWhere.append(" and order_type = ? ");
                    l_sbWhere.append(" and to_char(delivery_date, 'YYYYMMDD') = ? ");
                    l_sbWhere.append(" and order_exec_status = ? ");

                    Object[] l_objValues =
                    {
                        new Long(l_branchs[i].getBranchId()),
                        OrderTypeEnum.BOND_SELL,
                        WEB3DateUtility.formatDate(
                            new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(1), "yyyyMMdd"),
                        WEB3BondOrderExecStatusDef.EXECUTED,
                    };
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_lisBondOrderUnitRows =
                        l_queryProcessor.doFindAllQuery(
                            BondOrderUnitRow.TYPE,
                            l_sbWhere.toString(),
                            null,
                            l_objValues);
                }
                catch (Exception l_ex)
                {
                    log.debug("Error in Exception...", l_ex);
                    log.debug("(*1)ループ内で例外が発生した場合");
                    continue;
                }

                //1.3.5.(*3) 取得したレコード分ループ
                for (Iterator l_iter = l_lisBondOrderUnitRows.iterator(); l_iter.hasNext();)
                {
                    try
                    {
                        log.debug("(*3) 取得したレコード分ループ");
                        BondOrderUnitRow l_bondOrderUnitRow =
                            (BondOrderUnitRow)l_iter.next();
                        BondOrderUnitParams l_bondOrderUnitParams =
                            new BondOrderUnitParams(l_bondOrderUnitRow);

                        //1.3.5.1.円貨の場合
                        //債券注文単位Params.決済区分 = "1"（円貨）の場合
                        if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(
                            l_bondOrderUnitParams.getSettlementDiv()))
                        {
                            log.debug("債券注文単位Params.決済区分 = 1（円貨）の場合");
                            //1.3.5.1.1.calc円貨注文(Map, 債券注文単位Params)
                            //顧客別に円貨注文をGrossする。
                            //[引数]
                            //円貨注文Map： 円貨注文Mapオブジェクト
                            //債券注文単位Params： 債券注文単位Paramsオブジェクト
                            this.calcJapaneseOrder(
                                l_japaneseOrderMap,
                                l_bondOrderUnitParams);
                        }
                        //1.3.5.2.外貨の場合
                        //債券注文単位Params.決済区分 = "2"（外貨）の場合
                        if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(
                            l_bondOrderUnitParams.getSettlementDiv()))
                        {
                            log.debug("債券注文単位Params.決済区分 = 2（外貨）の場合");
                            //債券出金連携UnitServiceImpl生成
                            WEB3AioBondOnPaymentCooperationUnitServiceImpl
                                l_bondOnPaymentCooperationUnitServiceImpl =
                                    new WEB3AioBondOnPaymentCooperationUnitServiceImpl();

                            //1.3.5.2.1.create外貨注文(債券注文単位Params)
                            //外貨用の債券出金情報インスタンスを生成する。
                            //[引数]
                            //債券注文単位Params： 債券注文単位Paramsオブジェクト
                            WEB3AioBondOnPaymentInfo l_bondOnPaymentInfo =
                                l_bondOnPaymentCooperationUnitServiceImpl.createForeignOrder(
                                    l_bondOrderUnitParams);

                            //1.3.5.2.2.submit注文(債券出金情報)
                            //新規注文の登録を行う。
                            //[引数]
                            //債券出金情報： create外貨注文()の戻り値
                            l_bondOnPaymentCooperationUnitService.submitOrder(
                                l_bondOnPaymentInfo);
                        }
                    }
                    catch (Exception l_ex)
                    {
                        log.debug("Error in Exception...", l_ex);
                        log.debug("(*3)ループ内で例外が発生した場合");
                        continue;
                    }
                }
                //1.3.6.円貨注文Mapが管理する債券出金情報を配列で取得
                //Map.values().toArray()
                int l_intJapaneseOrderCnt = l_japaneseOrderMap.size();
                log.debug("l_japaneseOrderMap.size() = " + l_intJapaneseOrderCnt);

                WEB3AioBondOnPaymentInfo[] l_bondOnPaymentInfos =
                    new WEB3AioBondOnPaymentInfo[l_intJapaneseOrderCnt];
                l_japaneseOrderMap.values().toArray(l_bondOnPaymentInfos);

                //1.3.7.(*4) 取得した債券出金情報配列の要素分ループ
                //(*1) (*3) (*4)
                //ループ内で例外が発生した場合
                //ログを出力し、continueする。
                for (int j = 0; j < l_intJapaneseOrderCnt; j++)
                {
                    try
                    {
                        log.debug("(*4) 取得した債券出金情報配列の要素分ループ"
                            + l_intJapaneseOrderCnt);
                        //1.3.7.1.submit注文(債券出金情報)
                        //新規注文の登録を行う。
                        //[引数]
                        //債券出金情報： 債券出金情報オブジェクト
                        l_bondOnPaymentCooperationUnitService.submitOrder(
                            l_bondOnPaymentInfos[j]);
                    }
                    catch (Exception l_ex)
                    {
                        log.debug("Error in Exception...", l_ex);
                        log.debug("(*4)ループ内で例外が発生した場合");
                        continue;
                    }
                }
            }

            m_log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (is債券出金連携実行)<BR>
         * 債券出金連携を実行するかどうかを判定する。<BR>
         * <BR>
         * [戻り値]<BR>
         * true： 債券出金連携を実行する。<BR>
         * false： 債券出金連携を実行しない。 <BR>
         * <BR>
         * １）以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。<BR>
         * <BR>
         * [条件]  <BR>
         * 部店ID = 引数.部店ID <BR>
         * プリファ@レンス名 = "aio.bond.payment.cooperation"  <BR>
         * プリファ@レンス名の連番 = 1  <BR>
         * <BR>
         * ２）取得したレコード.プリファ@レンスの値 = ”1” の場合、trueを返却する。 <BR>
         * <BR>
         * ３）それ以外の場合は、falseを返却する。  <BR>
         * ※レコードが取得できなかった場合も含む。 <BR>
         * <BR>
         * @@param l_lngBranchId - 部店ID
         * @@return boolean
         * @@throws WEB3BaseException
         */
        private boolean isBondOnPaymentCooperationExecute(long l_lngBranchId)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "isBondOnPaymentCooperationExecute(long l_lngBranchId)";
            m_log.entering(STR_METHOD_NAME);

            // １）以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。
            // [条件]
            // 部店ID = 引数.部店ID
            // プリファ@レンス名 = "aio.bond.payment.cooperation"
            // プリファ@レンス名の連番 = 1
            BranchPreferencesRow l_branchReferencesRow = null;
            try
            {
                l_branchReferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId,
                    WEB3BranchPreferencesNameDef.AIO_BOND_PAYMENT_COOPERATION,
                    1);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました:", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            // ２）取得したレコード.プリファ@レンスの値 = ”1” の場合、trueを返却する。
            if (l_branchReferencesRow != null
                && WEB3BondDivDef.ENFORCEMENT.equals(l_branchReferencesRow.getValue()))
            {
                log.debug("取得したレコード.プリファ@レンスの値 = ”1” の場合");
                log.exiting(STR_METHOD_NAME);
                return true;
            }

            // ３）それ以外の場合は、falseを返却する。
            m_log.exiting(STR_METHOD_NAME);
            return false;
        }

        /**
         * (calc円貨注文)<BR>
         * 顧客別に円貨注文をGrossする。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（債券出金連携）calc円貨注文」 参照<BR>
         * <BR>
         * @@param l_japanexeOrderMap - (円貨注文Map)<BR>
         * 円貨注文Mapオブジェクト<BR>
         * @@param l_bondOrderUnitParams - (債券注文単位Params)<BR>
         * 債券注文単位Paramsオブジェクト<BR>
         */
        private void calcJapaneseOrder(Map l_japanexeOrderMap,
            BondOrderUnitParams l_bondOrderUnitParams)
        {
            final String STR_METHOD_NAME = "calcJapaneseOrder(Map, BondOrderUnitParams)";
            m_log.entering(STR_METHOD_NAME);

            //債券出金連携UnitServiceImpl生成
            WEB3AioBondOnPaymentCooperationUnitServiceImpl l_bondOnPaymentCooperationUnitServiceImpl =
                new WEB3AioBondOnPaymentCooperationUnitServiceImpl();

            //1.1.円貨注文Mapから債券出金情報を取得
            WEB3AioBondOnPaymentInfo l_bondOnPaymentInfo =
                (WEB3AioBondOnPaymentInfo)l_japanexeOrderMap.get(
                    new Long(l_bondOrderUnitParams.getAccountId()));

            //1.2.債券出金情報を取得できない場合
            if (l_bondOnPaymentInfo == null)
            {
                log.debug("債券出金情報を取得できない場合");
                //1.2.1.create円貨注文(債券注文単位Params)
                //円貨用の債券出金情報インスタンスを生成する。
                //[引数]
                //債券注文単位Params： 引数.債券注文単位Paramsオブジェクト
                l_bondOnPaymentInfo =
                    l_bondOnPaymentCooperationUnitServiceImpl.createJapaneseOrder(
                    l_bondOrderUnitParams);

                //1.2.2. 円貨注文Mapに生成した債券出金情報を追加
                l_japanexeOrderMap.put(l_bondOnPaymentInfo.getAccountId(),
                    l_bondOnPaymentInfo);
            }
            //1.3.債券出金情報を取得できる場合
            else
            {
                log.debug("債券出金情報を取得できる場合");
                //1.3.1.calc円貨注文(債券出金情報, 債券注文単位Params)
                //円貨注文をGrossする。
                //[引数]
                //債券出金情報： 取得した債券出金情報オブジェクト
                //債券注文単位Params： 引数.債券注文単位Paramsオブジェクト
                l_bondOnPaymentCooperationUnitServiceImpl.calcJapaneseOrder(
                    l_bondOnPaymentInfo,
                    l_bondOrderUnitParams);
            }

            m_log.exiting(STR_METHOD_NAME);
        }
    }
}
@
