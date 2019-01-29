head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.18.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMProductStopStartChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者商品別取扱停止再開変更サービスImpl (WEB3AdminTMProductStopStartChangeServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 中尾寿彦(SRA) 新規作成
*/
package webbroker3.trademanagement.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.OrderAcceptStatusRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.trademanagement.define.WEB3AdminTMProductRegistDivDef;
import webbroker3.trademanagement.message.WEB3AdminTMBranchTradingStatusUnit;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMProductTradingStatusUnit;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMProductStopStartChangeService;

/**
 * (管理者商品別取扱停止再開変更サービスImpl)<BR>
 * <BR>
 * 管理者商品別取扱停止再開変更サービス実装クラス<BR>
 * <BR>
 * WEB3AdminTMProductStopStartChangeServiceServiceImpl<BR>
 * <BR>
 * WEB3AdminTMProductStopStartChangeServiceServiceImpl class<BR>
 * <BR>
 * @@author shruti
 * @@version 1.0
 */

public class WEB3AdminTMProductStopStartChangeServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminTMProductStopStartChangeService
{

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMProductStopStartChangeServiceImpl.class);

    /**
      * @@roseuid 41DD402700FB
      */
    public WEB3AdminTMProductStopStartChangeServiceImpl()
    {

    }

    /**
     * 商品別取扱停止／再開処理を行う。<BR>
     * <BR>
     * 引数のリクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・商品別取扱停止再開変更確認リクエストの場合<BR>
     * 　@this.validate変更()をコールする。<BR>
     * <BR>
     * ○管理者・商品別取扱停止再開変更完了リクエストの場合<BR>
     * 　@this.submit変更()をコールする。<BR>
     * <BR>
     * ----<English>--------<BR>
     * <BR>
     * Execute WEB3AdminTMProductStopStartChangeService process。<BR>
     * <BR>
     * Call one of the following methods according to request data type of the
     * argument.<BR>
     * <BR>
     * ○If WEB3AdminTMPStopStartConfirmRequest<BR>
     *       Call this.validateChange()<BR>
     * <BR>
     * ○If WEB3AdminTMPStopStartCompleteRequest<BR>
     *       Call this.submitChange() <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41747AFA0216
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3AdminTMProductStopStartChangeServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;

        try
        {
            if (l_request instanceof WEB3AdminTMPStopStartConfirmRequest)
            {
                l_response = this.validateChange((WEB3AdminTMPStopStartConfirmRequest) l_request);

            } else if (l_request instanceof WEB3AdminTMPStopStartCompleteRequest)
            {
                l_response = this.submitChange((WEB3AdminTMPStopStartCompleteRequest) l_request);
            }  else
            {
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "INPUT リクエスト NOT 管理者商品別取扱停止再開変更サービスリクエスト");
           }
        } catch (DataQueryException l_dqex)
        {
                log.debug(l_dqex.getMessage());
                String l_strMsg = "Error while aquiring the Data ";
                log.error(l_strMsg, l_dqex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqex.toString(),
                    l_dqex);

            } catch (DataNetworkException l_dnex)
            {
                log.info(l_dnex.getMessage());
                String l_strMsg = "Error while aquiring the Data ";
                log.error(l_strMsg, l_dnex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dnex.toString(),
                    l_dnex);
            }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate変更)<BR>
     * <BR>
     * 商品別取扱停止再開変更確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者商品別取扱停止再開変更サービス)validate変更」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>------------------<BR>
     * <BR>
     * validateChange<BR>
     * <BR>
     * Execute WEB3AdminTMProductStopStartChangeService validate process.<BR>
     * <BR>
     * Refer to the sequence diagram "(Administrator product trade stop start
     * change)validateChange".<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・商品別取扱停止再開変更確認リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMPStopStartConfirmRequest object<BR>
     * <BR>
     * @@return WEB3AdminTMPStopStartConfirmResponse
     * @@exception WEB3BaseException
     *      * webbroker3.trademnagement.message.WEB3AdminTMPStopStartConfirmResponse
     * @@roseuid 41747AF70300
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     */
    protected WEB3AdminTMPStopStartConfirmResponse validateChange(
        WEB3AdminTMPStopStartConfirmRequest l_request)
             throws WEB3BaseException, DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME =
            "WEB3AdminTMProductStopStartChangeServiceImpl.validateChange()";
        log.entering(STR_METHOD_NAME);

        OrderAcceptStatusParams l_orderAcceptStatusParams = null;
        WEB3AdminTMPStopStartConfirmResponse l_response = null;
        String l_strInstituionCode = null;
        String l_strBranchCode = null;
        String l_strOrderAcceptCode = null;
        String l_strOrderAcceptTransaction = null;
        String l_strOrderAcceptStatus = null;
        boolean l_isUpdate = true;
        int l_brnchTradStatusLength = 0;
        int l_prdctTradStatusLength = 0;
        WEB3AdminTMBranchTradingStatusUnit[] l_branchTradingStatusList = null;
        WEB3AdminTMProductTradingStatusUnit[] l_productTradingStatus = null;
        WEB3Administrator l_web3Administrator = null;

        //Step 1.1, The l_request object is validated
        l_request.validate();

        // l_branchTradingStatusList & l_productTradingStatus acquired from the request object
        l_branchTradingStatusList = l_request.branchTradingStatusList;
        l_productTradingStatus = l_branchTradingStatusList[0].productTradingStatus;

        //Step  1.2, Aquires administator instance from login infomation
        l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step1.3, Checks transactionCategory i.e validates it.
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.TRADE_MANAGEMENT_PRODUCT,
            l_isUpdate);
        
        // Step 1.3.1 call validate Branch Permission of  Administrator.
        String[] l_branchCodeList = new String[l_branchTradingStatusList.length];
        for (int k= 0;k < l_branchTradingStatusList.length;k++)
        {
            l_branchCodeList[k]=l_branchTradingStatusList[k].branchCode;
        }
        l_web3Administrator.validateBranchPermission(l_branchCodeList);

        //Step  1.4, Aquires institutionCode.
        l_strInstituionCode = l_web3Administrator.getInstitutionCode();

        /*Step  1.5
         * First acquire the branchTradingStatusList from WEB3AdminTMBranchTradingStatusUnit[]
         * Loop through as many times as l_request.branchTradingStatusList
         */
        l_brnchTradStatusLength = l_branchTradingStatusList.length;
        l_branchTradingStatusList = l_request.branchTradingStatusList;

        for (int i = 0; i < l_brnchTradStatusLength; i++)
        {
            /*Step  1.5.1
              * First acquire the productTradingStatus from WEB3AdminTMProductTradingStatusUnit[]
              * Loop through as many times as productTradingStatus
              */
            l_productTradingStatus = l_branchTradingStatusList[i].productTradingStatus;
            l_prdctTradStatusLength = l_productTradingStatus.length;

            for (int j = 0; j < l_prdctTradStatusLength; j++)
            {
                // Step 1.5.1.1, During batch ptocess check for inputScreen process.
                if (WEB3AdminTMProductRegistDivDef
                    .BATCH
                    .equals(l_productTradingStatus[j].productRegistDiv))
                {
                    continue;
                }

                //Step 1.5.1.2, Aquires the orderAcceptStatusParams
                l_strBranchCode = l_branchTradingStatusList[i].branchCode;
                l_strOrderAcceptCode = l_productTradingStatus[j].orderProduct;
                l_strOrderAcceptTransaction = l_productTradingStatus[j].orderProductTran;

                l_orderAcceptStatusParams =
                         getOrderAcceptStatusParams(
                                 l_strInstituionCode,
                                 l_strBranchCode,
                                 l_strOrderAcceptCode,
                                 l_strOrderAcceptTransaction);

                /*Step 1.5.1.3
                 * Setting the productRegistDiv &afterProductRegistDiv during
                 * the batch process check2
                 */
                l_strOrderAcceptStatus = l_orderAcceptStatusParams.order_accept_status;

                if (WEB3AdminTMProductRegistDivDef.BATCH.equals(l_strOrderAcceptStatus))
                {
                    l_productTradingStatus[j].productRegistDiv = l_strOrderAcceptStatus;
                    l_productTradingStatus[j].afterProductRegistDiv = l_strOrderAcceptStatus;
                    continue;
                }

                /* Step 1.5.1.4
                 * Set the property of WEB3AdminTMLoginPermissionStatusUnit instance
                 * that has been created to set the latest database data
                 */
                l_productTradingStatus[j].productRegistDiv = l_strOrderAcceptStatus;
            }
        }

        //Step 1.6, Create response data
        l_response = (WEB3AdminTMPStopStartConfirmResponse) l_request.createResponse();

        //Step 1.7, Set the property of response data
        l_response.currentDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_response.branchTradingStatusList = l_branchTradingStatusList;

        log.exiting(STR_METHOD_NAME);

        //Step1.8, Return the response object
        return l_response;
    }

    /**
     * (submit変更)<BR>
     * <BR>
     * 商品別取扱停止再開変更完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者商品別取扱停止再開変更サービス)submit変更」<BR>
     * 参照<BR>
     * <BR>
     * ----<English><BR>
     * <BR>
     * submitConditionSetting<BR>
     * <BR>
     * Execute WEB3AdminTMProductStopStartChangeService submit process<BR>
     * <BR>
     * Refer to the sequence diagram "(Administrator product trade stop start change)
     * submitChange".<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・商品別取扱停止再開変更完了リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMPStopStartCompleteRequest object<BR>
     * <BR>
     * <BR>
     * @@return WEB3AdminTMMStopStartChgCompleteResponse
     * @@throws DataQueryException DataQueryException
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws DataNetworkException DataNetworkException
     * webbroker3.trademnagement.message.WEB3AdminTMMStopStartChgCompleteResponse
     * @@roseuid 41747AF70310
     */
    protected WEB3AdminTMPStopStartCompleteResponse submitChange(
        WEB3AdminTMPStopStartCompleteRequest l_request)
        throws WEB3BaseException, DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME =
            "WEB3AdminTMProductStopStartChangeServiceImpl.submitChange()";
        log.entering(STR_METHOD_NAME);

        OrderAcceptStatusParams l_orderAcceptStatusParams = null;
        WEB3AdminTMPStopStartCompleteResponse l_response = null;
        String l_strTransactionCategory = null;
        String l_password = null;
        String l_strInstituionCode = null;
        String l_strBranchCode = null;
        String l_strOrderAcceptCode = null;
        String l_strOrderAcceptTransaction = null;
        String l_strOrderAcceptStatus = null;
        boolean l_isUpdate = true;
        Timestamp l_currentTime = null;
        int l_brnchTradStatusLength = 0;
        int l_prdctTradStatusLength = 0;
        WEB3AdminTMBranchTradingStatusUnit[] l_branchTradingStatusList = null;
        WEB3AdminTMProductTradingStatusUnit[] l_productTradingStatus = null;

        l_currentTime =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        //Step 1.1, The l_request object is validated
        l_password = l_request.password;
        l_request.validate();

        //l_branchTradingStatusList & l_productTradingStatus acquired from the request object
        l_branchTradingStatusList = l_request.branchTradingStatusList;
        l_productTradingStatus = l_branchTradingStatusList[0].productTradingStatus;

        //Step  1.2, Aquires administator instance from login infomation
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step1.3, Checks transactionCategory i.e validates it.
        l_strTransactionCategory = WEB3TransactionCategoryDef.TRADE_MANAGEMENT_PRODUCT;
        l_web3Administrator.validateAuthority(l_strTransactionCategory, l_isUpdate);

        // Step 1.3.1 call validate Branch Permission of  Administrator.
        String[] l_branchCodeList = new String[l_branchTradingStatusList.length];
        for (int k= 0;k < l_branchTradingStatusList.length;k++)
        {
            l_branchCodeList[k]=l_branchTradingStatusList[k].branchCode;
        }
        l_web3Administrator.validateBranchPermission(l_branchCodeList);

        //Step 1.4,Checks the password
        l_web3Administrator.validateTradingPassword(l_password);

        //1.5, Aquires the institutionCode
        l_strInstituionCode = l_web3Administrator.getInstitutionCode();

        /*Step1.6
         * First acquire the branchTradingStatusList from WEB3AdminTMBranchTradingStatusUnit[]
         * Loop through as many elements of  l_request.branchTradingStatusList
         */
        l_brnchTradStatusLength = l_branchTradingStatusList.length;
        l_branchTradingStatusList = l_request.branchTradingStatusList;

        for (int i = 0; i < l_brnchTradStatusLength; i++)
        {
            /*Step 1.6.1
             * First acquire the productTradingStatus from WEB3AdminTMProductTradingStatusUnit[]
             * Loop through as many times as productTradingStatus
             */
            l_productTradingStatus = l_branchTradingStatusList[i].productTradingStatus;
            l_prdctTradStatusLength = l_productTradingStatus.length;

            for (int j = 0; j < l_prdctTradStatusLength; j++)
            {
                //Step 1.6.1.1, Aquires orderAccountStatusParams
                l_strBranchCode = l_branchTradingStatusList[i].branchCode;
                l_strOrderAcceptCode = l_productTradingStatus[j].orderProduct;
                l_strOrderAcceptTransaction = l_productTradingStatus[j].orderProductTran;
                l_orderAcceptStatusParams =
                    getOrderAcceptStatusParams(
                        l_strInstituionCode,
                        l_strBranchCode,
                        l_strOrderAcceptCode,
                        l_strOrderAcceptTransaction);

                //Step 1.6.1.2, Set the property i.e. the latest database data
                l_strOrderAcceptStatus = l_orderAcceptStatusParams.order_accept_status;
                l_productTradingStatus[j].productRegistDiv = l_strOrderAcceptStatus;

                //Step 1.6.1.3, During the divergence flow to check for any change
                String l_strProductTradStautus = l_productTradingStatus[j].productRegistDiv;

                if (!(l_strProductTradStautus
                    .equals(l_productTradingStatus[j].afterProductRegistDiv)))
                {
                    //Step 1.6.1.3.1,During the batch process check,afterProductRegistDiv is set.
                    if (WEB3AdminTMProductRegistDivDef.BATCH.equals(l_strProductTradStautus))
                    {
                        l_productTradingStatus[j].afterProductRegistDiv = l_strProductTradStautus;
                        continue;
                    }

                    /*Step 1.6.1.3.2
                     * Calls the method cloneOrderAcceptStatusParams() &
                     * Creates a clone of orderAcceptStatusParams
                     */
                    l_orderAcceptStatusParams =
                        cloneOrderAcceptStatusParams(l_orderAcceptStatusParams);

                    //Step 1.6.1.3.3, Set afterProductRegistDiv into orderAccountStatusParams
                    l_orderAcceptStatusParams.order_accept_status = l_productTradingStatus[j].afterProductRegistDiv;

                    l_orderAcceptStatusParams.last_updated_timestamp = l_currentTime;
                    l_orderAcceptStatusParams.last_updater =
                     l_web3Administrator.getAdministratorCode();

                    /* Step 1.6.1.3.4, Calls the method updateOrderAcceptStatus
                      * &Updates orderAcceptStatus
                      */
                    updateOrderAcceptStatus(l_orderAcceptStatusParams);
                }
            }
        }

        //Step 1.7,Create response data
        l_response = (WEB3AdminTMPStopStartCompleteResponse) l_request.createResponse();

        //Step 1.8, Set the property of response data
        l_response.currentDate = l_currentTime;
        l_response.branchTradingStatusList = l_branchTradingStatusList;

        log.exiting(STR_METHOD_NAME);

        //Step 1.9, Return the response object
        return l_response;
    }

    /**
     * (clone注文受付ステイタスParams)<BR>
     * <BR>
     * 引数の注文受付ステイタスParamsをコピーして、 <BR>
     * 同じ内容の別インスタンスを作成し、返却する。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * cloneOrderAcceptStatusParams<BR>
     * <BR>
     * <BR>
     * Copy argument of orderAcceptStatusParams, make another instance of the same
     * content, and return it. <BR>
     * <BR>
     * @@param l_orderAcceptStatusParams - (注文受付ステイタスParams)<BR>
     * <BR>
     * l_orderAcceptStatusParams<BR>
     * <BR>
     * @@return OrderAcceptStatusParams
     * @@roseuid 417CF4E4036B
     */
    protected OrderAcceptStatusParams cloneOrderAcceptStatusParams(
        OrderAcceptStatusParams l_orderAcceptStatusParams)
    {

        final String STR_METHOD_NAME =
           "WEB3AdminTMProductStopStartChangeServiceImpl.cloneOrderAcceptStatusParams("
           + "OrderAcceptStatusParams l_orderAcceptStatusParams)";
       log.entering(STR_METHOD_NAME);

       //Creating a copy of orderAcceptStatusParams,&returning the instance
        OrderAcceptStatusParams l_orderAcceptStatusParamsClone =
            new OrderAcceptStatusParams(l_orderAcceptStatusParams);

        log.exiting(STR_METHOD_NAME);
        return l_orderAcceptStatusParamsClone;
    }

    /**
     * (get注文受付ステイタスParams)<BR>
     * <BR>
     * getOrderAcceptStatusParams<BR>
     * <BR>
     * 引数の条件に該当する注文受付ステイタスParamsを返却する。<BR>
     * <BR>
     * １）DB検索<BR>
     * 　@以下の条件で注文受付ステイタスParamsを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@　@部店コード = パラメータ.部店コード<BR>
     * 　@　@注文受付商品 = パラメータ.注文受付商品<BR>
     * 　@　@注文受付トランザクション = パラメータ.注文受付トランザクション<BR>
     * <BR>
     * ２）検索結果を返却する。<BR>
     * <BR>
     * ----<English>------------<BR>
     * <BR>
     * (get order receipt status Params)<BR>
     * <BR>
     * getOrderAcceptStatusParams<BR>
     * <BR>
     * Return orderAcceptStatusParams that corresponds to the condition of the
     * argument.<BR>
     * <BR>
     * １）DB search<BR>
     * 　@Search orderAcceptStatusParams with the following conditions.<BR>
     * <BR>
     * 　@[Condition]<BR>
     * 　@　@institutionCode = Parameter.institutionCode<BR>
     * 　@　@branchCode = Parameter.branchCode<BR>
     * 　@　@orderAcceptProduct = Parameter.orderAcceptProduct<BR>
     * 　@　@orderAcceptTransaction = Parameter.orderAcceptTransaction<BR>
     * <BR>
     * ２）Return the search result.<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * <BR>
     * l_strBranchCode<BR>
     * <BR>
     * @@param l_strOrderAcceptProduct - (注文受付商品)<BR>
     * <BR>
     * l_strOrderAcceptProduct<BR>
     * <BR>
     * @@param l_strOrderAcceptTransaction - (注文受付トランザクション)<BR>
     * <BR>
     * l_strOrderAcceptTransaction<BR>
     * <BR>
     * @@return OrderAcceptStatusParams
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@throws IllegalStateException IllegalStateException
     * @@roseuid 41947E7201D6
     */
    protected OrderAcceptStatusParams getOrderAcceptStatusParams(
        String l_strInstitutionCode, String l_strBranchCode, String l_strOrderAcceptProduct,
        String l_strOrderAcceptTransaction) throws WEB3BusinessLayerException,
            DataQueryException, DataNetworkException, IllegalStateException
    {

        final String STR_METHOD_NAME =
            "getOrderAcceptStatusParams"
                + "(String l_strInstitutionCode,"
                + "String l_strBranchCode,"
                + "String l_strOrderAcceptProduct,"
                + "String l_strOrderAcceptTransaction)";
        log.entering(STR_METHOD_NAME);

        // variable for OrderAcceptStatusParams
        OrderAcceptStatusParams l_orderAcceptStatusParams = null;

        // variable for the search result list
        List l_lisSearchResult = null;

        //Creating the where clause & storing it in a Stirng variable
        String l_strWhere =
            " institution_code = ? "
                + " and branch_code = ? "
                + " and order_acc_product  = ? "
                + " and order_acc_transaction  = ? ";

        // Creating an arrray of objects to store the where clause fields in it
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strOrderAcceptProduct,
                l_strOrderAcceptTransaction };

        //Execution of the query through the query processor
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

        l_lisSearchResult =
            l_queryProcessor.doFindAllQuery(
                OrderAcceptStatusRow.TYPE,
                l_strWhere,
                null,
                l_objWhere);

        if (!l_lisSearchResult.isEmpty())
        {
            l_orderAcceptStatusParams = new OrderAcceptStatusParams();
            l_orderAcceptStatusParams = (OrderAcceptStatusParams) l_lisSearchResult.get(0);

        } else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderAcceptStatusParams;
    }

    /**
     * (update注文受付ステイタス)<BR>
     * <BR>
     * 引数の注文受付ステイタスParamsで<BR>
     * 注文受付ステイタステーブルを更新する。<BR>
     * <BR>
     * １）QueryProcessor.doUpdateQuery()メソッドを<BR>
     * 　@コールする。<BR>
     * <BR>
     * 　@[doUpdateQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@パラメータ.注文受付ステイタスParams<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * updateOrderAcceptStatus<BR>
     * <BR>
     * Update the order_accept_status table. <BR>
     * <BR>
     * １）Call QueryProcessor.doUpdateQuery() method<BR>
     * <BR>
     * 　@[Parameter to set in doUpdateQuery()]<BR>
     * 　@　@arg0：　@Parameter.orderAcceptStatusParams<BR>
     * @@param l_orderAcceptStatusParams - (注文受付ステイタスParams)<BR>
     * <BR>
     * l_orderAcceptStatusParams<BR>
     * <BR>
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@roseuid 41947E7201E6
     */
    protected void updateOrderAcceptStatus(OrderAcceptStatusParams l_orderAcceptStatusParams)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME =
          "WEB3AdminTMProductStopStartChangeServiceImpl.updateOrderAcceptStatus("
          + "OrderAcceptStatusParams l_orderAcceptStatusParams)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_queryProcessor.doUpdateQuery(l_orderAcceptStatusParams);

        log.exiting(STR_METHOD_NAME);
    }
}
@
