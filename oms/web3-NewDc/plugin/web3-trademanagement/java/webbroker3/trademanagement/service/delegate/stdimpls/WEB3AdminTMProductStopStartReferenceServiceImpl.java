head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.18.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMProductStopStartReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (管理者商品別取扱停止再開照会サービスImpl)(WEB3AdminTMProductStopStartReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trademanagement.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import java.sql.Timestamp;
import java.util.ArrayList;
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

import webbroker3.trademanagement.message.WEB3AdminTMBranchTradingStatusUnit;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartReferenceRequest;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartReferenceResponse;
import webbroker3.trademanagement.message.WEB3AdminTMProductTradingStatusUnit;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMProductStopStartReferenceService;

/**
  * (管理者商品別取扱停止再開照会サービスImpl)<BR>
 * <BR>
 * 管理者商品別取扱停止再開照会サービス実装クラス<BR>
 * <BR>
 * WEB3AdminTMProductStopStartReferenceServiceImpl<BR>
 * <BR>
 * WEB3AdminTMProductStopStartReferenceServiceImpl class<BR>
 * <BR>
 * @@author Rajesh Sharma
 * @@version 1.0
 */
public class WEB3AdminTMProductStopStartReferenceServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminTMProductStopStartReferenceService
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMProductStopStartReferenceServiceImpl.class);
    /**
     * @@roseuid 41DD3F650205
     */
    public WEB3AdminTMProductStopStartReferenceServiceImpl()
    {

    }

    /**
     * 商品別取扱停止再開照会処理を行う。<BR>
     * <BR>
     * 引数のリクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・商品別取扱停止再開照会リクエストの場合<BR>
     * 　@this.get商品別取扱状況照会()をコールする。<BR>
     * <BR>
     * ----<English>---------<BR>
     * <BR>
     * Execute WEB3AdminTMProductStopStartReferenceService process.<BR>
     * <BR>
     * Call the following method and divided it according to the type of the argument,
     * l_request.<BR>
     * <BR>
     * ○If WEB3AdminTMPStopStartInputRequest<BR>
     *       Call this.getProductHandlingStatusReference().<BR>
     * @@param l_request - リクエスト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException<BR>
     * @@return WEB3GenResponse<BR>
     * @@roseuid 4174836003DB<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3AdminTMProductStopStartReferenceServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminTMPStopStartReferenceRequest)
        {
            try
            {
                l_response =
                    this.getProductHandlingStatusReference(
                        (WEB3AdminTMPStopStartReferenceRequest) l_request);
            } catch (DataQueryException l_exce)
            {
                log.debug(l_exce.getMessage());
                String l_strMsg = "Error while aquiring the Data ";
                log.error(l_strMsg, l_exce);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exce.toString(),
                    l_exce);
            } catch (DataNetworkException l_exce)
            {
                log.info(l_exce.getMessage());
                String l_strMsg = "Error while aquiring the Data ";
                log.error(l_strMsg, l_exce);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exce.toString(),
                    l_exce);
            }
        } else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT リクエスト NOT 管理者商品別取扱停止再開照会サービスリクエスト");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get商品別取扱状況照会)<BR>
     * <BR>
     * 商品別取扱停止再開照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者商品別取扱停止再開照会サービス)get商品別取扱状況照会」<BR>
     * 参照<BR>
     * <BR>
     * -------<English>------------<BR>
     * <BR>
     * getProductHandlingStatusReference<BR>
     * <BR>
     * Execute WEBAdminTMProductStopStartReferenceService reference process <BR>
     * <BR>
     * Refer to the sequence diagram "(Administrator product trade stop start
     * reference) getProductHandlingStatusReference".<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・商品別取扱停止再開照会リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMPStopStartInputRequest object<BR>
     * <BR>
     * @@return webbroker3.trademnagement.message.WEB3AdminTMPStopStartReferenceResponse<BR>
     * @@throws WEB3BaseException WEB3BaseException<BR>
     * @@throws DataQueryException DataQueryException<BR>
     * @@throws DataNetworkException DataNetworkException<BR>
     * @@throws IllegalStateException IllegalStateException<BR>
     * @@see WEB3ErrorCatalog
     * @@roseuid 4174835D03AC<BR>
     */
    protected WEB3AdminTMPStopStartReferenceResponse
      getProductHandlingStatusReference(WEB3AdminTMPStopStartReferenceRequest l_request)
        throws WEB3BaseException, DataQueryException, DataNetworkException, IllegalStateException
    {
        WEB3AdminTMPStopStartReferenceResponse l_response = null;
        WEB3AdminTMProductTradingStatusUnit l_productTradeStatusUnit = null;
        WEB3AdminTMBranchTradingStatusUnit l_branchTradeStatusUnit = null;
        WEB3AdminTMProductTradingStatusUnit[] l_arrProductTradeStatusUnit = null;
        WEB3AdminTMBranchTradingStatusUnit[] l_arrBranchTradeStatusUnit = null;
        OrderAcceptStatusParams l_orderAcceptStatusParams = null;
        OrderAcceptStatusParams l_nextOrderAcceptStatusParams = null;
        String l_strInstituionCode = null;
        String l_strBranchCode = null;
        String[] l_branchCodeList = null;
        List l_orderAcceptStatusParamsList = null;
        boolean l_isUpdate = false;
        int l_orderAcceptStatusParamsListCnt = 0;
        int i = 0;

        // Step 1.1 Input request values are validated
        l_request.validate();

        // Step 1.2 get the instance of Administrator
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step  1.3 validate authority
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.TRADE_MANAGEMENT_PRODUCT,
            l_isUpdate);

        // Step 1.4 if branchCode is not null
        l_branchCodeList = l_request.branchCodeList;
        if (l_branchCodeList != null)
        {
            //Step 1.4.1 call validate Branch Permission of  Administrator
            l_web3Administrator.validateBranchPermission(l_branchCodeList);
        }

        // Step 1.5 obtain the institution Code from administrator
        l_strInstituionCode = l_web3Administrator.getInstitutionCode();
        l_strBranchCode = l_request.branchCode;

        // Step 1.6 obtain the order Accept params for the given institution code and branch code
        l_orderAcceptStatusParamsList =
            this.getOrderAcceptStatusParamsList(l_strInstituionCode, l_strBranchCode);

        //If no record is found the throw the exception
        if (l_orderAcceptStatusParamsList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "getProductHandlingStatusReference()");
        }

        // Step 1.7 Create a ArrayList for Branch Trading Status
        ArrayList l_branchTradingStatusUnit = new ArrayList();

        // Step 18 Create a ArrayList for Branch Trading Status
        ArrayList l_productTradingStatusUnit = new ArrayList();

        //Step 1.9 Loop processing for as many times as the element =orderAcceptStatusParams
        l_orderAcceptStatusParamsListCnt = l_orderAcceptStatusParamsList.size();

        for (i = 0; i < l_orderAcceptStatusParamsListCnt; i++)
        {
            /* Step 1.9.1
             * Create a object of Product Trading Status to store values obtained for
             * orderAccpetParams
             */
            l_productTradeStatusUnit = new WEB3AdminTMProductTradingStatusUnit();

            //Step 19.2 Fetch each row of order Accpet Params and stored in Product Trade Status
            l_orderAcceptStatusParams =
                (OrderAcceptStatusParams) l_orderAcceptStatusParamsList.get(i);

            l_productTradeStatusUnit.orderProduct = l_orderAcceptStatusParams.order_acc_product;
            l_productTradeStatusUnit.orderProductTran =
                l_orderAcceptStatusParams.order_acc_transaction;
            l_productTradeStatusUnit.productRegistDiv =
                l_orderAcceptStatusParams.order_accept_status;
            l_productTradeStatusUnit.afterProductRegistDiv = null;

            //Step1.9.3 Add the Object of Product Trade Status into Product Trade status List
            l_productTradingStatusUnit.add(l_productTradeStatusUnit);

            /*Step1.9.3
             * IF condition is true for (count-1) of records of Order Accept Params.
             * To check for next record branch code value which is not
             * equal to current record branch code
             */
            if (i < (l_orderAcceptStatusParamsListCnt - 1))
            {
                l_nextOrderAcceptStatusParams =
                    (OrderAcceptStatusParams) l_orderAcceptStatusParamsList.get(i + 1);
            }

            /*Step 1.9.4
             * IF condition is true when it is the last record of orderAccpetParams.
             * if it is last record then add the obtained Product Trading Status List to
             * Branch Trading Status List
             */

            if (i == (l_orderAcceptStatusParamsListCnt - 1)
                || !(l_orderAcceptStatusParams
                    .getBranchCode()
                    .equals(l_nextOrderAcceptStatusParams.getBranchCode())))
            {
                //Step 1.9.4.1 Create a array of Produc Trade Status
                l_arrProductTradeStatusUnit = 
                    (WEB3AdminTMProductTradingStatusUnit[])l_productTradingStatusUnit.toArray(new WEB3AdminTMProductTradingStatusUnit[0]);

                //Step 1.9.4.2 Create a object of Branch Trading Status Unit
                l_branchTradeStatusUnit = new WEB3AdminTMBranchTradingStatusUnit();

                /*Step 1.9.4.3 Stores the branch code and product Trade Status unit to
                 * branch Trading Status object
                 */
                l_branchTradeStatusUnit.branchCode = l_orderAcceptStatusParams.branch_code;
                l_branchTradeStatusUnit.productTradingStatus = l_arrProductTradeStatusUnit;

                /*Step 1.9.4.4 Add the object of Branch Trade Status to branch Trading Status List
                 * and Clear the values stored in Product Trading Status List
                 */
                l_branchTradingStatusUnit.add(l_branchTradeStatusUnit);
                l_productTradingStatusUnit.clear();
            }
        }

        /*Step 1.10 Create a array of Branch Trading Status Unit of length order accept params.
         * to store the values of obtained Branch Trading Status List into an array
         */
        l_arrBranchTradeStatusUnit =
            (WEB3AdminTMBranchTradingStatusUnit[])l_branchTradingStatusUnit.toArray(new WEB3AdminTMBranchTradingStatusUnit[0]);

        // 1.11 Create a response object
        l_response = (WEB3AdminTMPStopStartReferenceResponse) l_request.createResponse();

        //1.12 Store the current date and Branch Trading Status Unit into response object.
        l_response.currentDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_response.branchTradingStatusList = l_arrBranchTradeStatusUnit;

        //Step 1.13 return the response object
        return l_response;
    }

    /**
     * (get注文受付ステイタスParams一覧)<BR>
     * <BR>
     * getOrderAcceptStatusParamsList <BR>
     * <BR>
     * 引数の条件に該当する注文受付ステイタスParamsの<BR>
     * 一覧を取得する。<BR>
     * <BR>
     * １）以下の検索条件を表す、検索条件文字列と <BR>
     * 　@ArrayList(パラメータセット)を作成する。 <BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@　@部店コード = パラメータ.部店コード<BR>
     * <BR>
     * 　@　@※パラメータ.部店コード == nullの場合、検索条件に加えない。<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。 <BR>
     * <BR>
     * 　@　@検索条件文字列 = " institution_code = ? " <BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。 <BR>
     * 　@　@ArrayListを生成し、以下の値をセットする。 <BR>
     * 　@　@　@・パラメータ.証券会社コード<BR>
     * <BR>
     * 　@１－３）パラメータ.部店コード != nullの場合、部店コードの条件を<BR>
     * 　@　@　@　@検索条件文字列とArrayListに追加する。<BR>
     * <BR>
     * 　@　@　@　@・検索条件文字列 += "and branch_code = ? "<BR>
     * 　@　@　@　@・生成したArrayListにパラメータ.部店コードを追加<BR>
     * <BR>
     * 　@１－４）以下のソート条件を作成する。 <BR>
     * 　@　@　@　@①@部店コード 昇順<BR>
     * 　@　@　@　@②注文受付商品 昇順<BR>
     * 　@　@　@　@③注文受付トランザクション 昇順<BR>
     * <BR>
     * 　@　@　@　@ソート条件 = " branch_code "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ ",order_acc_product "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ ",order_acc_transaction "<BR>
     * <BR>
     * ２）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@"注文受付ステイタステーブル"(order_accept_status)<BR>
     * 　@　@arg1：　@作成した検索条件文字列 <BR>
     * 　@　@arg2：　@作成したソート条件 <BR>
     * 　@　@arg3：　@null <BR>
     * 　@　@arg4：　@生成したArrayList.toArray()<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ３）検索結果を返却する。<BR>
     * <BR>
     * ----<English>------------<BR>
     * <BR>
     * (getOrderAcceptStatusParamsList)<BR>
     * <BR>
     * getOrderAcceptStatusParamsList <BR>
     * <BR>
     * Acure orderAcceptStatusParamsList that meets argument conditions.  <BR>
     * <BR>
     * １）Make search character row and ArrayList(Set parameter) that show the
     * following retrieval conditions. <BR>
     * <BR>
     * 　@[Conditions] <BR>
     * 　@　@institution_code = parameter.institutionCode<BR>
     * 　@　@branch_code = parameter.branchCode<BR>
     * <BR>
     * 　@　@※If parameter.branchCode == null, Add to search condition<BR>
     * <BR>
     * 　@１－１）Create a search character row with the above conditions<BR>
     * <BR>
     * 　@　@search character row = " institution_code = ? " <BR>
     * <BR>
     * 　@１－２）Create a parameter to set into "?" <BR>
     * 　@　@Generate ArrayList, and set the following value<BR>
     * 　@　@　@・parameter.institution_code<BR>
     * <BR>
     * 　@１－３）If parameter.branchCode != null, <BR>
     *              Add the condition of branchCode and ArrayList to the search
     * charactor row <BR>
     * <BR>
     * 　@　@　@　@・search charactor row += "and branch_code = ? "<BR>
     * 　@　@　@　@・Add parameter.branchCode to the generated ArrayList<BR>
     * <BR>
     * 　@１－４）Make sort conditions as follows。 <BR>
     * 　@　@　@　@①@branch_code in Ascending Order<BR>
     * 　@　@　@　@②order_acc_product Ascending Order<BR>
     * 　@　@　@　@③order_acc_transaction Ascending Order<BR>
     * <BR>
     * 　@　@　@　@Sort key = " branch_code "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ ",order_acc_product "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ ",order_acc_transaction "<BR>
     * <BR>
     *
     * ２）Call QueryProcessor.doFindAllQuery() method<BR>
     * <BR>
     * 　@[Parameters set in doFindAllQuery()]<BR>
     * 　@　@arg0：　@"orderAcceptStatus table"(order_accept_status)<BR>
     * 　@　@arg1：　@The created search charactor row <BR>
     * 　@　@arg2：　@sortKey that has been created<BR>
     * 　@　@arg3：　@null <BR>
     * 　@　@arg4：　@Generated ArrayList.toArray()<BR>
     * <BR>
     * 　@Return null when search result was not available. <BR>
     * <BR>
     * ３）Return the search result.<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * <BR>
     * l_strBranchCode<BR>
     * <BR>
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@throws IllegalStateException IllegalStateException
     * @@return List
     * @@roseuid 41947E1B01F5
     */
    protected List getOrderAcceptStatusParamsList(
        String l_strInstitutionCode,
        String l_strBranchCode)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        List l_searchResult = null;
        Object[] l_objWhere = null;
        String l_strSortKey = null;

        final String STR_METHOD_NAME =
            "getOrderAcceptStatusParamsList(l_strInstitutionCode, l_strBranchCode)";
        log.entering(STR_METHOD_NAME);

        /* 1-1 Add InstitutionCode in where clause. 1-2
         * Generate ArrayList, and set the l_strInstitutionCode
         */
        String l_strWhere = " institution_code = ? ";

        // 1-3 If BranchCode is not null then Add in where clause.
        if (l_strBranchCode != null)
        {
            l_objWhere = new String[2];
            l_objWhere[0] = l_strInstitutionCode;
            l_strWhere = l_strWhere + " and branch_code = ? ";
            l_objWhere[1] = l_strBranchCode;
        } else
        {
            l_objWhere = new String[1];
            l_objWhere[0] = l_strInstitutionCode;
        }
        // 1-4 Create Sort key.
        l_strSortKey = " branch_code, order_acc_product, order_acc_transaction ";
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_searchResult =
            l_queryProcessor.doFindAllQuery(
                OrderAcceptStatusRow.TYPE,
                l_strWhere,
                l_strSortKey,
                null,
                l_objWhere);

        /*
         * When the search result is not null
         */
        if (!l_searchResult.isEmpty())
        {
            return l_searchResult;
        }
        return null;
    }
}
@
