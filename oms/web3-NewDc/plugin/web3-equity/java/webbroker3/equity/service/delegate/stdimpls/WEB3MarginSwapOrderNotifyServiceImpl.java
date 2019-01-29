head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapOrderNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡注文通知サービスImpl(WEB3MarginSwapOrderNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/27 森川   (SRA)  残案件対応
Revesion History : 2005/01/06 岡村   (SRA)  JavaDoc修正
Revesion History : 2007/04/17 趙林鵬 (中訊) モデル 1139
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.equity.data.HostEqtypeSwapReceiptParams;
import webbroker3.equity.data.HostEqtypeSwapReceiptRow;
import webbroker3.equity.message.WEB3MarginSwapOrderNotifyRequest;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyDataAdapter;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyService;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeRequestCodesForRead;
import webbroker3.util.WEB3LogUtility;


/**
 * （信用取引現引現渡注文通知サービスImpl）。<BR>
 * <BR>
 * 信用取引現引現渡注文通知サービス実装クラス。
 * @@version 1.0
 */
public class WEB3MarginSwapOrderNotifyServiceImpl
    implements WEB3MarginSwapOrderNotifyService
{

    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapOrderNotifyServiceImpl.class);


    /**
     * (コンストラクタ)。<BR>
     */
    public WEB3MarginSwapOrderNotifyServiceImpl()
    {
    }


    /**
     * (execute)。<BR>
     * <BR>
     * 用取引現引現渡注文通知サービス処理を実施する。 <BR>
     * <BR>
     * シーケンス図「（信用取引現引現渡注文通知サービス）現引現渡注文通知」参照。<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);


        //--------------------
        //レスポンスの生成
        //--------------------
        WEB3MarginSwapOrderNotifyRequest l_request1 = (WEB3MarginSwapOrderNotifyRequest)l_request;
        WEB3BackResponse l_response = l_request1.createResponse();


        //--------------------
        //validate
        //--------------------
        l_request1.validate();


        try
        {
            //--------------------
            //getDefaultProcessor
            //--------------------
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();


            //--------------------
            //信用取引現引現渡注文通知TransactionCallback
            //--------------------
            WEB3MarginSwapOrderNotifyTransactionCallback l_transactionCallBack =
                new WEB3MarginSwapOrderNotifyTransactionCallback();


            //--------------------
            //set識別コードプレフィクス一覧
            //--------------------
            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_request1.orderRequestNumberPrefixGroup);


            //--------------------
            //doTransaction(トランザクション属性(=TX_CREATE_NEW)
            //--------------------
            l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_transactionCallBack);


        }
        catch (DataCallbackException l_dce)
        {
            WEB3BaseException l_wbe = (WEB3BaseException)l_dce.getDetails();
            throw l_wbe;
        }
        catch (DataException l_de)
        {
            log.debug(STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }


    /**
     * (信用取引現引現渡注文通知TransactionCallback)。<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス。<BR>
     */
    public class WEB3MarginSwapOrderNotifyTransactionCallback
        implements TransactionCallback
    {

        /**
         * (識別コードプレフィクス一覧)。<BR>
         */
        private String[] orderRequestNumberPrefixGroup;


        /**
         * (get識別コードプレフィクス一覧)。<BR>
         * <BR>
         * 識別コードプレフィクス一覧を取得する。<BR>
         * <BR>
         * @@return 識別コードプレフィクス一覧
         */
        public String[] getOrderRequestNumberPrefixGroup() {
            return this.orderRequestNumberPrefixGroup;
        }


        /**
         * (set識別コードプレフィクス一覧)。<BR>
         * <BR>
         * 引数の識別コードプレフィクス一覧をプロパティにセットする。<BR>
         * <BR>
         * @@param orderRequestNumberPrefixGroup 識別コードプレフィクス一覧
         */
        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup) {
            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
        }


        /**
         * (信用取引現引現渡注文通知TransactionCallback)。<BR>
         * <BR>
         * コンストラクタ<BR>
         * @@return WEB3MarginSwapOrderNotifyTransactionCallback
         */
        public WEB3MarginSwapOrderNotifyTransactionCallback()
        {
        }


        /**
         * (process)。<BR>
         * <BR>
         * 信用取引現引現渡注文通知処理を実施する。 <BR>
         * <BR>
         * シーケンス図「（信用取引現引現渡注文通知サービス）process」参照。<BR>
         * <BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);


            //--------------------
            //get識別コードプレフィクス一覧
            //--------------------
            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();
            int l_orderRequestNumberPrefixGroupCnt = l_orderRequestNumberPrefixGroup.length;

            //--------------------
            // 処理対象データコードテーブル読込
            //--------------------
            String[] l_requestCodesForReadList = null;
            try
            {
                l_requestCodesForReadList =
                    WEB3GentradeRequestCodesForRead.getRequestCodesForReadList(
                    WEB3MarginSwapOrderNotifyRequest.PTYPE);
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new DataCallbackException(l_wbe.getMessage(), l_wbe);
            }
            
            //--------------------
            //現引現渡入力通知キューテーブル読込
            //--------------------
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" status = ? ");

            Object[] l_objParams = new Object[l_orderRequestNumberPrefixGroupCnt + l_requestCodesForReadList.length + 1];
            l_objParams[0] = WEB3StatusDef.NOT_DEAL;

            log.debug("l_objParams[0] = [" + l_objParams[0].toString() + "]");
            int l_intSize = 1;

            // 識別コードの先頭２桁がget識別コードプレフィクス一覧()の戻り値配列のいずれかと一致
            l_sbWhere.append("and (");
            for(int i = 0 ; i < l_orderRequestNumberPrefixGroupCnt; i++)
            {
                if (i == 0)
                {
                    l_sbWhere.append("order_request_number like ?");
                }
                else
                {
                    l_sbWhere.append(" or order_request_number like ?");
                }
                l_objParams[i + l_intSize] = l_orderRequestNumberPrefixGroup[i] + "%";
                log.debug("l_objParams[" + Integer.toString(i + l_intSize) + "] = [" + l_objParams[i + l_intSize].toString() + "]");
            }
            l_sbWhere.append(")");

            l_intSize = l_intSize + l_orderRequestNumberPrefixGroupCnt;
            int l_intRequestCodesForReadListCnt = 0;
            if (l_requestCodesForReadList != null)
            {
                l_intRequestCodesForReadListCnt = l_requestCodesForReadList.length;
            }
            if (l_intRequestCodesForReadListCnt > 0)
            {
                l_sbWhere.append(" and (request_code = ?");
                l_objParams[l_intSize++] = l_requestCodesForReadList[0];
                log.debug("l_objParams[" + Integer.toString(l_intSize - 1) + "] = [" + l_objParams[l_intSize - 1].toString() + "]");
                for (int i = 1; i < l_intRequestCodesForReadListCnt; i++)
                {
                    l_sbWhere.append(" or request_code = ?");
	                l_objParams[l_intSize++] = l_requestCodesForReadList[i];
	                log.debug("l_objParams[" + Integer.toString(l_intSize - 1) + "] = [" + l_objParams[l_intSize - 1].toString() + "]");
                }
                l_sbWhere.append(")");
            }
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            log.debug("l_sbWhere = [" +l_sbWhere.toString() + "]");
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                HostEqtypeSwapReceiptRow.TYPE, l_sbWhere.toString(), null, l_objParams);
            int l_iSize = l_lisRecords.size();


            //--------------------
            //取得したキューテーブルのレコード数分、Loop処理
            //--------------------
            WEB3MarginSwapOrderNotifyUnitService l_swapOrderNotifyUnitService =
                (WEB3MarginSwapOrderNotifyUnitService) Services.getService(WEB3MarginSwapOrderNotifyUnitService.class);

            for (int i = 0; i < l_iSize; i++)
            {
                HostEqtypeSwapReceiptParams l_params
                    = new HostEqtypeSwapReceiptParams((HostEqtypeSwapReceiptRow) l_lisRecords.get(i));

                // get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
                //証券会社コード：　@現引現渡入力通知キューParams.証券会社コード  
                //部店コード：　@　@　@　@現引現渡入力通知キューParams.部店コード 
                //口座コード：　@　@　@　@現引現渡入力通知キューParams口座コー

                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountMgr =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                try
                {
                    l_accountMgr.getMainAccount(
                        l_params.getInstitutionCode(),
                        l_params.getBranchCode(),
                        l_params.getAccountCode());
                }
                catch(WEB3BaseException l_ex)
                {
                    log.debug(l_ex.getMessage(), l_ex);
                    l_params.setStatus(WEB3StatusDef.DEALT);
                    l_params.setLastUpdatedTimestamp(
                        GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_params);
                    continue;
                }

                WEB3MarginSwapOrderNotifyDataAdapter l_adapter =
                    WEB3MarginSwapOrderNotifyDataAdapter.create(l_params);

                try
                {
                    //--------------------
                    //(取消区分＝取消の場合)
                    //notify現引現渡注文取消
                    //--------------------
                    if (WEB3CancelDivDef.CANCEL.equals(l_params.cancel_div))
                    {
                        l_swapOrderNotifyUnitService.notifyCancelSwapOrder(l_adapter);
                    }
                    //--------------------
                    //(取消区分≠取消の場合)
                    //notify現引現渡注文
                    //--------------------
                    else
                    {
                        l_swapOrderNotifyUnitService.notifySwapOrder(l_adapter);
                    }

                    //--------------------
                    //処理対象キューUPDATE　@(=>処理済)
                    //--------------------
                    //一件処理内部へ移す.start
                    //l_params.setStatus(WEB3StatusDef.DEALT);
                    //l_queryProcessor.doUpdateQuery(l_params);
                    //.end

                }
                catch (Exception l_e)
                {
                    //--------------------
                    //処理対象キューUPDATE　@(エラー時)
                    //--------------------
                    log.error("一件処理にてエラー発生：" + l_params.toString());
                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
                    l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_params);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
