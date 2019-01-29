head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替請求通知サービスImpl(WEB3AccTransChangeRequestNotifyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
Revesion History : 2004/10/26 周勇(中訊) レビュー
Revesion History : 2004/12/09 周勇 (中訊) 残対応
Revesion History : 2005/05/06 王亞洲（中訊）受入テスト障害票U02004対応
Revesion History : 2007/01/31 車進 (中訊) 仕様変更 モデル 691
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostTransferReceiptParams;
import webbroker3.aio.data.HostTransferReceiptRow;
import webbroker3.aio.message.WEB3AccTransChangeRequestNotifyResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeCompleteUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestNotifyService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3DepositTypeDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3RemarkCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (振替請求通知サービスImpl)<BR>
 * 振替請求通知サービス実装クラス<BR>
 *
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestNotifyServiceImpl
    implements WEB3AccTransChangeRequestNotifyService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeRequestNotifyServiceImpl.class);

    /**
     * 振替請求通知処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（振替請求通知）振替請求通知」参照<BR>
     * @@param l_request - (リクエストデータ)
     *
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C2C8C01A7
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            // １） 振替請求通知トランザクションコールバックインスタンスを生成する
            WEB3AccTransChangeRequestNotifyTransactionCallback l_transactionCallback =
                new WEB3AccTransChangeRequestNotifyTransactionCallback();

            // ２）　@クエリプロセッサを取得する
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // ３）DBトランザクション処理を実施する。
            // [doTransaction()に指定する引数]
            // トランザクション属性：　@TX_CREATE_NEW
            // トランザクションコールバック：　@振替請求通知TransactionCallbackインスタンス
            l_queryProcessor.doTransaction(l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        WEB3AccTransChangeRequestNotifyResponse l_response =
            (WEB3AccTransChangeRequestNotifyResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (振替請求通知TransactionCallbackクラス)
     */
    public class WEB3AccTransChangeRequestNotifyTransactionCallback
        implements TransactionCallback
    {

        /**
         * ログユーティリティ<BR>
         */
        private WEB3LogUtility l_log =
            WEB3LogUtility.getInstance(
                WEB3AccTransChangeRequestNotifyTransactionCallback.class);

        /**
         * (振替請求通知TransactionCallback)<BR>
         * デフォルトコンストラクタ<BR>
         * @@return WEB3AccTransChangeRequestNotifyTransactionCallback<BR>
         * @@roseuid 413C253A00EB
         */
        public WEB3AccTransChangeRequestNotifyTransactionCallback()
        {

        }

        /**
         * 振替請求通知処理を実施する。 <BR>
         * <BR>
         * シーケンス図 <BR>
         * 「（振替請求通知）process」参照<BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 413C2C2602C0
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "process()";
            l_log.entering(STR_METHOD_NAME);

            // 1.1)  振替入力通知テーブル読込み
            String l_strWhere = "request_code = ? " +
                                "and status = ?";
            // 李志強 U01504の暫定対応 start
//            String l_strCondition = "for update";
            String l_strCondition = null;
            // 李志強 U01504の暫定対応 end

            Object[] l_objWhere = { WEB3HostRequestCodeDef.AIO_TRANSFER_INPUT_NOTIFY ,
                WEB3StatusDef.NOT_DEAL };

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(
                    HostTransferReceiptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhere);

            int l_intResultLength = 0;
            if (l_lisSearchResult != null && l_lisSearchResult.size() != 0)
            {
                l_intResultLength = l_lisSearchResult.size();
            }

            HostTransferReceiptParams l_hostTransferReceiptParams = null;

            // 1.2) 取得したレコード毎のLoop処理
            for (int i = 0; i < l_intResultLength; i++)
            {
                // 振替入力通知キューParamsの取得
                HostTransferReceiptRow l_hostTransferAcceptRow =
                    (HostTransferReceiptRow)l_lisSearchResult.get(i);
                l_hostTransferReceiptParams =
                    new HostTransferReceiptParams(l_hostTransferAcceptRow);

                boolean l_blnIsFailProcess = false;
                Map l_map = new Hashtable();

                try
                {
                    // 1.2.1) 注文種別を取得する。
                    // ［引数］
                    // 預かり区分： 振替入力通知キューParams.預かり区分
                    // 摘要コード： 振替入力通知キューParams.摘要コード
                    log.debug("振替入力通知キューParams.預かり区分 = " +
                            l_hostTransferReceiptParams.getDepositTypeDiv());
                    log.debug("振替入力通知キューParams.摘要コード = " +
                            l_hostTransferReceiptParams.getRemarkCode());

                    OrderTypeEnum l_orderType = this.getOrderType(
                        l_hostTransferReceiptParams.getDepositTypeDiv(),
                        l_hostTransferReceiptParams.getRemarkCode());

                    log.debug("注文種別 = " + l_orderType);

                    // 李志強 U01504の暫定対応 start
                    WEB3AccTransChangeRequestNotifyNormalTransactionCallback l_transactionCallback =
                        new WEB3AccTransChangeRequestNotifyNormalTransactionCallback(
                            l_hostTransferReceiptParams,
                            l_orderType);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

//                    //1.2.2)
//                    if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType) ||
//                        OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderType) ||
//                        OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderType) ||
//                        OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderType))
//                    {
//                        AssetTransferTypeEnum l_assetTransferType = null;
//                        //　@・注文種別が以下のいずれかの場合、2（出金）
//                        //1011(為替保証金振替注文(預かり金から為替保証金))
//                        //1017(その他振替注文(預かり金からX))
//                        if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType) ||
//                            OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderType))
//                        {
//                            l_assetTransferType = AssetTransferTypeEnum.CASH_OUT;
//                        }
//                        //・注文種別が以下のいずれかの場合、1（入金）
//                        //1012(為替保証金振替注文(為替保証金から預かり金))
//                        //1018(その他振替注文(Xから預かり金))
//                        else if (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderType) ||
//                                OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderType))
//                        {
//                            l_assetTransferType = AssetTransferTypeEnum.CASH_IN;
//                        }
//                        // 1.2.2.1) 新規為替保証金振替注文の登録を行う。
//                        // [引数]
//                        // 振替入力通知キューParams： 振替入力通知キューParamsオブジェクト
//                        // 注文種別： get注文種別()の戻り値
//                        // 振替タイプ：
//                        //  ・注文種別が以下のいずれかの場合、2（出金）
//                        //      1011(為替保証金振替注文(預かり金から為替保証金))
//                        //      1017(その他振替注文(預かり金からX))
//                        //  ・注文種別が以下のいずれかの場合、1（入金）
//                        //      1012(為替保証金振替注文(為替保証金から預かり金))
//                        //      1018(その他振替注文(Xから預かり金))
//                        this.createOrder(
//                            l_hostTransferReceiptParams,
//                            l_orderType,
//                            l_assetTransferType);
//                    }
//                    //1.2.3
//                    else
//                    {
//                        //1.2.3.1 新規入金注文の登録を行う。
//                        //振替入力通知キューParams： 振替入力通知キューParamsオブジェクト
//                        //注文種別： get注文種別()の戻り値
//                        //振替タイプ： 1（入金）
//                        this.createOrder(
//                            l_hostTransferReceiptParams,
//                            l_orderType,
//                            AssetTransferTypeEnum.CASH_IN);
//
//                        //1.2.3.2 新規出金注文の登録を行う。
//                        //[引数]
//                        //振替入力通知キューParams： 振替入力通知キューParamsオブジェクト
//                        //注文種別： get注文種別()の戻り値
//                        //振替タイプ： 2（出金）
//                        this.createOrder(
//                            l_hostTransferReceiptParams,
//                            l_orderType,
//                            AssetTransferTypeEnum.CASH_OUT);
//                    }
                    // 李志強 U01504の暫定対応 end
                }
                catch (WEB3BaseException l_ex)
                {
                    l_log.debug("__an WEB3BaseException__", l_ex);
                    l_blnIsFailProcess = true;
                }
                // 李志強 U01504の暫定対応 start
                catch (Exception l_ex)
                {
                    l_log.debug("__an Exception__", l_ex);
                    l_blnIsFailProcess = true;
                }
                // 李志強 U01504の暫定対応 end

                // 李志強 U01504の暫定対応 start
//                if (l_blnIsFailProcess)
//                {
//                    // 処理対象の振替入力通知テーブル.処理区分を設定用
//                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
//                    l_log.debug("__an WEB3BaseException__ ");
//                }
//                else
//                {
//                    // 処理対象の振替入力通知テーブル.処理区分を設定用
//                    l_map.put("status", WEB3StatusDef.DEALT);
//                }
//
//                // 処理対象の振替入力通知テーブル.処理区分を設定用
//                String l_strUpdateWhere = " request_code = ? "
//                                   + " and institution_code = ? "
//                                   + " and branch_code = ? "
//                                   + "and account_code = ? "
//                                   + "and order_request_number = ? ";
//
//                String[] l_strArrayUpdateParams = {
//                    l_hostTransferReceiptParams.getRequestCode(),
//                    l_hostTransferReceiptParams.getInstitutionCode(),
//                    l_hostTransferReceiptParams.getBranchCode(),
//                    l_hostTransferReceiptParams.getAccountCode(),
//                    l_hostTransferReceiptParams.getOrderRequestNumber()
//                };
//
//                // 1.2.4　@処理対象の取消受付キューレコード.処理区分を設定”
//                l_queryProcessor.doUpdateAllQuery(
//                    HostTransferReceiptRow.TYPE,
//                    l_strUpdateWhere,
//                    l_strArrayUpdateParams,
//                    l_map);

                if (l_blnIsFailProcess)
                {
                    // 処理対象の振替入力通知テーブル.処理区分を設定用
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);

                    // 処理対象の振替入力通知テーブル.処理区分を設定用
                    String l_strUpdateWhere = "rowid = ? ";

                    String[] l_strArrayUpdateParams = {l_hostTransferReceiptParams.getRowid()};

                    // 1.2.4　@処理対象の取消受付キューレコード.処理区分を設定”
                    l_queryProcessor.doUpdateAllQuery(
                        HostTransferReceiptRow.TYPE,
                        l_strUpdateWhere,
                        l_strArrayUpdateParams,
                        l_map);
                }
                // 李志強 U01504の暫定対応 end

            }

            l_log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (get注文種別)<BR>
         * 注文種別を取得する。<BR>
         * <BR>
         * １） <BR>
         * 引数.預かり区分 = 01（預り金） and <BR>
         * 引数.摘要コード = 01（信用保証金） <BR>
         * <BR>
         * の場合、1006（振替注文（信用保証金から預り金））を返却する。 <BR>
         * <BR>
         * ２） <BR>
         * 引数.預かり区分 = 01（預り金） and <BR>
         * 引数.摘要コード = 72（株先証拠金） <BR>
         * <BR>
         * の場合、1008（振替注文（株先証拠金から預り金））を返却する。 <BR>
         * <BR>
         * ３） <BR>
         * 　@ 引数.預かり区分 = 01（預り金） and <BR>
         * 引数.摘要コード = 86（為替保証金） <BR>
         * <BR>
         * の場合、1012（為替保証金振替注文（為替保証金から預り金））を返却する。 <BR>
         * <BR>
         * ４） <BR>
         * 引数.預かり区分 = 04（保証金） <BR>
         * <BR>
         * の場合、1005（振替注文（預り金から信用保証金））を返却する。 <BR>
         * <BR>
         * ５） <BR>
         * 引数.預かり区分 = 05（株先証拠金） <BR>
         * <BR>
         * の場合、1007（振替注文（預り金から株先証拠金））を返却する。 <BR>
         * <BR>
         * ６） <BR>
　@       * 引数.預かり区分 != 01（預り金） and <BR>
         * 引数.摘要コード = 86（為替保証金） <BR>
         * <BR>
         * の場合、1011（為替保証金振替注文（預り金から為替保証金））を返却する。 <BR>
         * <BR>
         * ７）
         * 引数.預かり区分 = 01（預り金） and <BR>
         * 引数.摘要コード = (*)のいずれか <BR>
         * <BR>
         * の場合、1018（その他振替注文（Xから預り金））を返却する。 <BR>
         * <BR>
         * ８） <BR>
         * 引数.預かり区分 != 01（預り金） and <BR>
         * 引数.摘要コード = (*)のいずれか <BR>
         * <BR>
         * の場合、1017（その他振替注文（預り金からX））を返却する。 <BR>
         * <BR>
         * (*)その他振替注文に該当する摘要コード <BR>
         * <BR>
         * 02：（発）委託保証金 <BR>
         * 03：端株整理 <BR>
         * 06：名義書換料 <BR>
         * 07：（保）口座管理料 <BR>
         * 08：（外国）口座管理料 <BR>
         * 09：（金）口座管理料 <BR>
         * 10：（債券先物）委託保証金 <BR>
         * 11：（株式先物）委託保証金 <BR>
         * 14：（オプション）委託保証金 <BR>
         * 19：（株券オプション）委託保証金 <BR>
         * 24：銀行振込手数料 <BR>
         * 42：スーパーG（継続）月曜 <BR>
         * 43：スーパーG（継続）火曜 <BR>
         * 44：スーパーG（継続）水曜 <BR>
         * 45：スーパーG（継続）木曜 <BR>
         * 46：スーパーG（継続）金曜 <BR>
         * 47：金貯蓄 1ヶ月 <BR>
         * 48：金貯蓄 3ヶ月 <BR>
         * 49：金貯蓄 6ヶ月 <BR>
         * 50：金貯蓄 1年 <BR>
         * 52：中期国債ファ@ンド <BR>
         * 53：（累投）口座管理料 <BR>
         * 54：中期国債ファ@ンドキャッシング <BR>
         * 55：MMFキャッシング <BR>
         * 71：（先物オプション 東証） <BR>
         * 73：（先物オプション 名証） <BR>
         * 74：（先物オプション 利益払出） <BR>
         * 93：その他預り金 <BR>
         * 97：外貨振替 <BR>
         * 98：摘要入力内容 継続03 <BR>
         * 99：BLANK <BR>
         * <BR>
         * @@param l_strDepositDivision - (預かり区分)<BR>
         * @@param l_strRemarkCode - (摘要コード)<BR>
         * @@return OrderTypeEnum <BR>
         * @@throws WEB3BaseException
         * @@roseuid 413D3A1002D6
         */
        protected OrderTypeEnum getOrderType(
            String l_strDepositDivision,
            String l_strRemarkCode)
                throws WEB3BaseException
        {
            String STR_METHOD_NAME = "getOrderType(" +
                    "String l_strDepositDivision," +
                    "String l_strRemarkCode)";
            l_log.entering(STR_METHOD_NAME);

            //１）引数.預かり区分 = 01（預り金） and
            //   引数.摘要コード = 01（信用保証金）
            //   の場合、1006（振替注文（信用保証金から預り金））を返却する。
            if(WEB3DepositTypeDivDef.FROM_DEPOSIT.equals(l_strDepositDivision)
                && WEB3RemarkCodeDef.MARGIN_GUARANTEE.equals(l_strRemarkCode))
            {
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT;  //1006
            }

            //２）引数.預かり区分 = 01（預り金） and
            //   引数.摘要コード = 72（株先証拠金）
            //   の場合、1008（振替注文（株先証拠金から預り金））を返却する。

            if(WEB3DepositTypeDivDef.FROM_DEPOSIT.equals(l_strDepositDivision)
                && (WEB3RemarkCodeDef.STOCK_FUTURES_MARGIN.equals(l_strRemarkCode)))
            {
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT;    //1008
            }

            //３）引数.預かり区分 = 01（預り金） and
            //   引数.摘要コード = 86（為替保証金）
            //   の場合、1012（為替保証金振替注文（為替保証金から預り金））を返却する。
            if(WEB3DepositTypeDivDef.FROM_DEPOSIT.equals(l_strDepositDivision)
                && (WEB3RemarkCodeDef.EXCHANGE_GUARANTEE.equals(l_strRemarkCode)))
            {
                log.debug("1012（為替保証金振替注文（為替保証金から預り金））を返却する");
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE;  //1012
            }

            //４）引数.預かり区分 = 04（保証金）
            //   の場合、1005（振替注文（預り金から信用保証金））を返却する。
            if(WEB3DepositTypeDivDef.GUARANTEE.equals(l_strDepositDivision))
            {
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE;//1005
            }

            //５）引数.預かり区分 = 05（株先証拠金）
            //   の場合、1007（振替注文（預り金から株先証拠金））を返却する。
            if(WEB3DepositTypeDivDef.STOCK_FUTURES_MARGIN.equals(l_strDepositDivision))
            {
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN;    //1007
            }

            //６）引数.預かり区分 != 01（預り金） and
            //    引数.摘要コード = 86（為替保証金）
            //   の場合、1011（為替保証金振替注文（預り金から為替保証金））を返却する。
            if(!WEB3DepositTypeDivDef.FROM_DEPOSIT.equals(l_strDepositDivision)
                && (WEB3RemarkCodeDef.EXCHANGE_GUARANTEE.equals(l_strRemarkCode)))
            {
                log.debug("1011（為替保証金振替注文（預り金から為替保証金））を返却する");
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT;  //1011
            }

            //７）引数.預かり区分 = 01（預り金） and 引数.摘要コード = (*)のいずれか
            //の場合、1018（その他振替注文（Xから預り金））を返却する。
            if(WEB3DepositTypeDivDef.FROM_DEPOSIT.equals(l_strDepositDivision)
                && isRemarkCode(l_strRemarkCode))
            {
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.FROM_OTHER_TRANSFER;  //1018
            }

            //８）引数.預かり区分 != 01（預り金） and 引数.摘要コード = (*)のいずれか
            //の場合、1017（その他振替注文（預り金からX））を返却する。
            if(!WEB3DepositTypeDivDef.FROM_DEPOSIT.equals(l_strDepositDivision)
                && isRemarkCode(l_strRemarkCode))
            {
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.TO_OTHER_TRANSFER;  //1017
            }
            l_log.exiting(STR_METHOD_NAME);
            return null;
        }
       /**
        * isその他振替注文に該当する摘要コード <BR>
        * <BR>
        * 摘要コード = (*)のいずれか の場合、trueを返却する <BR>
        * 以外の場合、falseを返却する <BR>
        * <BR>
        * (*)その他振替注文に該当する摘要コード <BR>
        * <BR>
        * 02：（発）委託保証金 <BR>
        * 03：端株整理 <BR>
        * 06：名義書換料 <BR>
        * 07：（保）口座管理料 <BR>
        * 08：（外国）口座管理料 <BR>
        * 09：（金）口座管理料 <BR>
        * 10：（債券先物）委託保証金 <BR>
        * 11：（株式先物）委託保証金 <BR>
        * 14：（オプション）委託保証金 <BR>
        * 19：（株券オプション）委託保証金 <BR>
        * 24：銀行振込手数料 <BR>
        * 42：スーパーG（継続）月曜 <BR>
        * 43：スーパーG（継続）火曜 <BR>
        * 44：スーパーG（継続）水曜 <BR>
        * 45：スーパーG（継続）木曜 <BR>
        * 46：スーパーG（継続）金曜 <BR>
        * 47：金貯蓄 1ヶ月 <BR>
        * 48：金貯蓄 3ヶ月 <BR>
        * 49：金貯蓄 6ヶ月 <BR>
        * 50：金貯蓄 1年 <BR>
        * 52：中期国債ファ@ンド <BR>
        * 53：（累投）口座管理料 <BR>
        * 54：中期国債ファ@ンドキャッシング <BR>
        * 55：MMFキャッシング <BR>
        * 71：（先物オプション 東証） <BR>
        * 73：（先物オプション 名証） <BR>
        * 74：（先物オプション 利益払出） <BR>
        * 93：その他預り金 <BR>
        * 97：外貨振替 <BR>
        * 98：摘要入力内容 継続03 <BR>
        * 99：BLANK <BR>
        * <BR>
        * <BR>
        * @@param l_strRemarkCode (摘要コード) <BR>
        * @@return boolean <BR>
        */
        private boolean isRemarkCode(String l_strRemarkCode)
        {
            String[] l_strRemarkCodes = {
                    WEB3RemarkCodeDef.BIZ_CONSIGN_GUARANTEE,
                    WEB3RemarkCodeDef.STOCK_REF,
                    WEB3RemarkCodeDef.NAME_TRANSFER,
                    WEB3RemarkCodeDef.SAFE_ACCOUNT_MANAGE,
                    WEB3RemarkCodeDef.FOREIGN_ACCOUNT_MANAGE,
                    WEB3RemarkCodeDef.CASH_ACCOUNT_MANAGE,
                    WEB3RemarkCodeDef.BOND_FUTURE_CONSIGN_GUARANTEE,
                    WEB3RemarkCodeDef.EQUITY_FUTURE_CONSIGN_GUARANTEE,
                    WEB3RemarkCodeDef.OPTION_CONSIGN_GUARANTEE,
                    WEB3RemarkCodeDef.STOCK_BOND_OPTION_CONSIGN_GUARANTEE,
                    WEB3RemarkCodeDef.BANK_TRANSFER_COMMISSIONFEE,
                    WEB3RemarkCodeDef.SUPER_MARKET_G_CONTINUE_MONDAY,
                    WEB3RemarkCodeDef.SUPER_MARKET_G_CONTINUE_TUESDAY,
                    WEB3RemarkCodeDef.SUPER_MARKET_G_CONTINUE_WEDNESDAY,
                    WEB3RemarkCodeDef.SUPER_MARKET_G_CONTINUE_THURSDAY,
                    WEB3RemarkCodeDef.SUPER_MARKET_G_CONTINUE_FRIDAY,
                    WEB3RemarkCodeDef.SAVING_ONE_MONTH,
                    WEB3RemarkCodeDef.SAVING_THREE_MONTHS,
                    WEB3RemarkCodeDef.SAVING_SIX_MONTHS,
                    WEB3RemarkCodeDef.SAVING_ONE_YEAR,
                    WEB3RemarkCodeDef.MEDIUM_TERM_NATIONAL_BONDS_FUNDS,
                    WEB3RemarkCodeDef.RUITO_ACCOUNT_MANAGE,
                    WEB3RemarkCodeDef.MEDIUM_TERM_NATIONAL_BONDS_FUNDS_CASHING,
                    WEB3RemarkCodeDef.MMF_CASHING,
                    WEB3RemarkCodeDef.FUTURES_OPTION_TOKYO_STOCK_EXCHANGE,
                    WEB3RemarkCodeDef.FUTURES_OPTION_NAGOYA_STOCK_EXCHANGE,
                    WEB3RemarkCodeDef.FUTURES_OPTION_PROFIT_PAY,
                    WEB3RemarkCodeDef.DEFAULT_FROM_DEPOSIT,
                    WEB3RemarkCodeDef.FOREIGN_TRANSFER,
                    WEB3RemarkCodeDef.REMARK_INPUT_REQUEST_CONTINUE_THREE,
                    WEB3RemarkCodeDef.BLANK
                    };

            for (int i = 0; i < l_strRemarkCodes.length; i++)
            {
                if (l_strRemarkCodes[i].equals(l_strRemarkCode))
                {
                    return true;
                }
            }
            return false;
        }

        /**
         * (create注文)<BR>
         * 振替注文を生成する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（振替請求通知）create注文」 参照<BR>
         * @@param l_hostTransferReceiptParams - (振替入力通知キューParamsオブジェクト)<BR>
         * @@param l_orderType - (注文種別)<BR>
         * @@param l_changeType - (振替タイプ)<BR>
         * @@throws WEB3BaseException
         * @@roseuid 413D3C370036
         */
        protected void createOrder(
            HostTransferReceiptParams l_hostTransferReceiptParams,
            OrderTypeEnum l_orderType,
            AssetTransferTypeEnum l_changeType)
                throws WEB3BaseException
        {
            String STR_METHOD_NAME = "createOrder(" +
                "HostTransferReceiptParams l_hostTransferReceiptParams," +
                "OrderTypeEnum l_orderType," +
                "AssetTransferTypeEnum l_changeType)";

            l_log.entering(STR_METHOD_NAME);

            // 1) 新規注文の登録を行う。
            // [引数]
            // 振替入力通知キューParams： 振替入力通知キューParamsオブジェクト
            // 注文種別： 引数.注文種別
            // 振替タイプ： 引数.振替タイプ
            WEB3AccTransChangeRequestNotifyUnitService l_notifyUnitService =
                (WEB3AccTransChangeRequestNotifyUnitService)Services.getService(
                    WEB3AccTransChangeRequestNotifyUnitService.class);
            long l_lngOrderID = l_notifyUnitService.submitOrder(
                    l_hostTransferReceiptParams,
                    l_orderType,
                    l_changeType);
            log.debug("l_lngOrderID = " + l_lngOrderID);
            // 2) 注文単位を取得する。
            // (*配列の1番目の要素を取得する）
            // [引数]
            // 注文ID： submit注文()の戻り値
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_orderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderID);
            AioOrderUnit l_orderUnit = (AioOrderUnit) l_orderUnits[0];

            // 3）振替受付DB更新処理を行う。
            // [引数]
            // 注文単位： 注文単位オブジェクト
            // エラーコード： "0000"（正常）
            // 受付通知区分： "1"（受付完了）
            WEB3AccTransChangeAcceptUnitService l_acceptUnitService =
                (WEB3AccTransChangeAcceptUnitService) Services.getService(
                    WEB3AccTransChangeAcceptUnitService.class);

            l_acceptUnitService.execute(
                l_orderUnit,
                WEB3ErrorReasonCodeDef.NORMAL,
                WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE);

            // 4) 振替完了処理に伴う注文データの更新とトランザクションデータの生成を行う。
            // [引数]
            // 注文単位： 注文単位オブジェクト
            WEB3AccTransChangeCompleteUnitService l_completeUnitService =
                (WEB3AccTransChangeCompleteUnitService) Services.getService(
                WEB3AccTransChangeCompleteUnitService.class);
            l_completeUnitService.completeChange(l_orderUnit);

            // 5) 補助口座オブジェクトを取得する。
            // ［引数］
            // 口座ID： 注文単位.口座ID
            // 補助口座ID： 注文単位.補助口座ID
            WEB3GentradeAccountManager l_accManage =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            SubAccount l_subAccount = null;
            try
            {
                l_subAccount = l_accManage.getSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {
                l_log.error("補助口座オブジェクトを取得する:",l_ex);
                l_log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
            }

            if(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(
                l_subAccount.getSubAccountType()))
            {
                //=========remain zhou-yong NO.1 begin ===========

                // 6) 余力再計算(補助口座 : 補助口座)
                //アイテムの定義
                //余力の更新を行う。
                //[引数]
                //補助口座： get補助口座()の戻り値
                WEB3TPTradingPowerService l_service =
                    (WEB3TPTradingPowerService) Services.getService(
                        WEB3TPTradingPowerService.class);

                WEB3GentradeSubAccount l_gentradeSubAccount =
                    (WEB3GentradeSubAccount)l_subAccount;

                l_service.reCalcTradingPower(l_gentradeSubAccount);

                //=========remain zhou-yong NO.1 end ===========

            }
            l_log.exiting(STR_METHOD_NAME);
        }
    }
}
@
