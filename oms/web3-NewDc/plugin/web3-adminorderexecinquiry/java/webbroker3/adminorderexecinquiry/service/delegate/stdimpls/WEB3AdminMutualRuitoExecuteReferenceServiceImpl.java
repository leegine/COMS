head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMutualRuitoExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者投信累投注文約定照会サービスImpl(WEB3AdminMutualRuitoExecuteReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/19 周捷 (中訊) 仕様変更・モデル061、070、073
Revision History : 2006/12/19 唐性峰(中訊) モデルNo.087
Revision History : 2007/01/12 周捷 (中訊) 仕様変更・モデル089
Revesion History : 2007/02/26 徐大方(中訊)仕様変更モデルNo.093
*/
package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductRow;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderUnitIntroduceDiv;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;

import webbroker3.adminorderexecinquiry.WEB3AdminOrderExecuteDataManager;
import webbroker3.adminorderexecinquiry.WEB3AdminProductExecutionInfo;
import webbroker3.adminorderexecinquiry.define.WEB3AdminKeyItemDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminProductDivDef;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommonRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionSortKeyUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORProductNameSetUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORTradingProductUnit;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminMutualRuitoExecuteReferenceService;

/**
 * (管理者投信累投注文約定照会サービスImpl)<BR>
 * <BR>
 * 管理者投信累投注文約定照会サービス実装クラス<BR>
 * <BR>
 * WEB3AdminMutualRuitoExecuteReferenceServiceImpl<BR>
 * <BR>
 * @@author Vijay Kumar
 * @@version 1.0
 */
public class WEB3AdminMutualRuitoExecuteReferenceServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminMutualRuitoExecuteReferenceService
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualRuitoExecuteReferenceServiceImpl.class);

    /**
     * @@roseuid 4212FBE60315
     */
    public WEB3AdminMutualRuitoExecuteReferenceServiceImpl()
    {
    }

    /**
     * 投信累投注文約定照会処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・投信累投注文約定照会入力リクエストの場合<BR>
     * 　@this.get入力画面()をコールする。<BR>
     * <BR>
     * ○管理者・投信累投注文約定照会リクエストの場合<BR>
     * 　@this.get照会画面()をコールする。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * WEB3AdminMutualRuitoExecuteReferenceService process<BR>
     * <BR>
     * Call one of the following methods based on the type of the argument,
     * l_request.<<BR>
     * <BR>
     * ○If WEB3AdminORMutualRuitoOrderExecutionRefInputRequest<BR>
     * 　@Call this.getInputScreen()<BR>
     * <BR>
     * ○If WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest<BR>
     * 　@Call this.getReferenceScreen()<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41AE9E830308
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        try
        {
            if (l_request instanceof WEB3AdminORMutualRuitoOrderExecutionRefInputRequest)
            {
                l_response =
                    this.getInputScreen(
                        (WEB3AdminORMutualRuitoOrderExecutionRefInputRequest) l_request);
            } else if (
                l_request instanceof WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest)
            {
                l_response =
                    this.getReferenceScreen(
                        (WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest) l_request);
            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT リクエスト NOT 管理者・投信累投注文約定照会リクエスト");
            }
        } catch (NotFoundException l_notFoundException)
        {
            log.error(l_notFoundException.getMessage(), l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_notFoundException.getMessage(),
                l_notFoundException);
        } catch (DataFindException l_dataException)
        {
            log.error(l_dataException.getMessage(), l_dataException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataException.getMessage(),
                l_dataException);
        } catch (DataNetworkException l_dataException)
        {
            log.error(l_dataException.getMessage(), l_dataException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataException.getMessage(),
                l_dataException);
        } catch (DataQueryException l_dataException)
        {
            log.error(l_dataException.getMessage(), l_dataException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataException.getMessage(),
                l_dataException);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * <BR>
     * 投信累投注文約定照会入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者投信累投注文約定照会サービス)get入力画面」<BR>
     * 参照<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     * Execute WEB3AdminMutualRuitoExecuteReferenceService input screen process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: mutual ruito order execution
     * inquiry service)getInputScreen"<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・投信累投注文約定照会入力リクエストオブジェクト<BR>
     * <BR>
     * ---------<English>----------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminORMutualRuitoOrderExecutionRefInputRequest object<BR>
     * <BR>
     * @@return
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRef
     * InputResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@throws IllegalStateException IllegalStateException
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@throws WEB3SystemLayerException WEB3SystemLayerException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 41AE9E830327
     */
    protected WEB3AdminORMutualRuitoOrderExecutionRefInputResponse getInputScreen
        (WEB3AdminORMutualRuitoOrderExecutionRefInputRequest l_request)
        throws
            WEB3BaseException,
            DataQueryException,
            DataNetworkException,
            IllegalStateException,
            WEB3BusinessLayerException,
            WEB3SystemLayerException,
            NotFoundException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminORMutualRuitoOrderExecutionRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminORMutualRuitoOrderExecutionRefInputResponse l_response = null;
        WEB3Administrator l_web3Administrator = null;
        WEB3AdminOrderExecuteDataManager l_webAdminOrderExecuteDataManager =
            new WEB3AdminOrderExecuteDataManager();
        WEB3AdminORTradingProductUnit[] l_tradingProductUnits = null;
        WEB3AdminProductExecutionInfo l_web3ProductExecutionInfo = null;
        WEB3AdminORProductNameSetUnit[] l_web3AdminORProductNameSetUnits = null;
        WEB3GentradeBizDate l_gentradeBizDate = null;
        String l_strInstitutionCode = null;
        String[] l_branchCodes = null;
        Date l_datOrderBizDate = null;
        Date[] l_orderBizDates = null;
        ArrayList l_lisOrderBizDate = null;

        // Step 1.1, The l_request object is validated
        l_request.validate();

        // Step  1.2, Acquires administator instance from login infomation
        l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step1.3, Checks transactionCategory i.e validates it.
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.XBMF_XBRUITO_ORDER_EXEC_INQUIRY,
            false);

        // Step 1.4, Acquires institutionCode.
        l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        // Step 1.5, Acquire productExecutionInfo about a handling possible branch
        l_branchCodes = l_request.branchCode;
        l_web3ProductExecutionInfo =
            l_webAdminOrderExecuteDataManager.getProductExecutionInfo(
                l_strInstitutionCode,
                l_branchCodes);

        // Step 1.6,Create a list of handling porssible products
        l_tradingProductUnits = this.createTradingProductList(l_web3ProductExecutionInfo);

        // Step 1,7, creating an arraylist
        l_lisOrderBizDate = new ArrayList();

        // Step 1.8, adding the bizdate to the arraylist
		Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
		l_gentradeBizDate = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
		Timestamp l_tsPreBizdate = l_gentradeBizDate.roll(-1);
        l_lisOrderBizDate.add(WEB3DateUtility.toDay(l_tsPreBizdate));
        l_lisOrderBizDate.add(WEB3DateUtility.toDay(l_datBizDate));

        // Step 1.9,if following condition is true then execute Steps 1.9.1 to 1.9.9
        if (l_web3ProductExecutionInfo.ruitoFlag)
        {
            // Step 1.9.1,Set tradingTimeType again
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);

            // Step 1.9.2,Acquire orderBizDate
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            // Step 1.9.3, Adding acquired orderBizDate to ArrayList if it is not included in List
            if (!l_lisOrderBizDate.contains(l_datOrderBizDate))
            {
                l_lisOrderBizDate.add(l_datOrderBizDate);
            }

            // Step 1.9.4, Set tradingTimeType again
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.MMF_SET);

            // Step 1.9.5, Acquire the OrderBizDate
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            // Step 1.9.6 Adding acquired orderBizDate to ArrayList if it is not included in List
            if (!l_lisOrderBizDate.contains(l_datOrderBizDate))
            {
                l_lisOrderBizDate.add(l_datOrderBizDate);
            }

            // Step 1.9.7, Set tradingTimeType again
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.MMF_SET_CANCEL);

            // Step 1.9.8, Acquire the OrederBizDate
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            // Step 1.9.9 Add the acquired orderBizDate to ArrayList if it is not included in List
            if (!l_lisOrderBizDate.contains(l_datOrderBizDate))
            {
                l_lisOrderBizDate.add(l_datOrderBizDate);
            }
        }

        // Step 1.10,if the following condition is true execute steps 1.10.1 to 1.10.3
        if (l_web3ProductExecutionInfo.mutualFlag)
        {
            // Step 1.10.1, Set tradingTimeType again
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.MUTUAL_FUND);

            // Step 1.10.2,Acquire mfOrderBizDate
            l_datOrderBizDate = WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();

            // Step 1.10.3,adding acquired orderBizDate to ArrayList if it is not included in List
            if (!l_lisOrderBizDate.contains(l_datOrderBizDate))
            {
                l_lisOrderBizDate.add(l_datOrderBizDate);
            }
        }

        // Step 1.11, call createMutualRuitoProductName()
        l_web3AdminORProductNameSetUnits =
            (WEB3AdminORProductNameSetUnit[]) this.createMutualRuitoProductName(
                l_strInstitutionCode,
                l_web3ProductExecutionInfo);

        // Step 1.12, Create response data
        l_response =
            (WEB3AdminORMutualRuitoOrderExecutionRefInputResponse) l_request.createResponse();

        // Step 1.13,Set the property to the reponse object
        l_orderBizDates = new Date[l_lisOrderBizDate.size()];
        l_orderBizDates = (Date[]) l_lisOrderBizDate.toArray(l_orderBizDates);
        l_response.orderBizDateList = l_orderBizDates;
        l_response.productNameSetList = l_web3AdminORProductNameSetUnits;
        l_response.tradingProductList = l_tradingProductUnits;
        l_response.orderRootList = new String[] {
            WEB3OrderRootDivDef.CALLCENTER,
            WEB3OrderRootDivDef.PC,
            WEB3OrderRootDivDef.SLINGSHOT,
            WEB3OrderRootDivDef.I_MODE,
            WEB3OrderRootDivDef.VODAFONE,
            WEB3OrderRootDivDef.AU,
            WEB3OrderRootDivDef.HOST
        };
        
        // Step 1.14, Return the repsonse.
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get照会画面)<BR>
     * <BR>
     * 投信累投注文約定照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者投信累投注文約定照会サービス)get照会画面」<BR>
     * 参照<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getReferenceScreen<BR>
     * <BR>
     * Execute WEB3AdminMutualRuitoExecuteReferenceService process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: mutual ruito order execution
     * inquiry service)getReferenceScreen"<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・投信累投注文約定照会リクエストオブジェクト<BR>
     * <BR>
     * ---------<English>----------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest object<BR>
     * <BR>
     * @@return WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRef
     * ReferenceResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@throws IllegalStateException IllegalStateException
     * @@throws NotFoundException NotFoundException
     *      * @@roseuid 41AE9E830347
     */
    protected WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse getReferenceScreen
        (WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest l_request)
        throws
            WEB3BaseException,
            DataQueryException,
            DataNetworkException,
            IllegalStateException,
            NotFoundException
    {
        final String STR_METHOD_NAME =
            "getReferenceScreen(WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse l_response = null;
        WEB3Administrator l_administrator = null;
        WEB3AdminOrderExecuteDataManager l_orderExecuteDataManager = null;
        WEB3AdminOROrderExecutionSortKeyUnit[] l_sortKeyUnits = null;
        WEB3AdminORMutualRuitoOrderExecutionRefUnit[] l_orMutualRuitoOrderExecutionRefUnits = null;
        OrderUnit[] l_orderUnits = null;
        WEB3PageIndexInfo l_pageIndexInfo = null;
        String l_strInstitutionCode = null;
        String l_strProductId = null;
        String l_strTaxType = null;
        String l_strDeliveryDiv = null;
        String l_strQueryCond = null;
        String l_strProductDiv = null;
        String l_strQuery = null;
        String l_strCommonQuery = null;
        String l_strSortCond = null;
        ArrayList l_lisOrderUnits = null;
        String[] l_strBranchCodes = null;
        String[] l_strQueryCondDataContainers = null;
        String[] l_strQueryDataContainers = null;
        String[] l_strCommonQueryDataContainers = null;
        int l_intCommonDataContainer = 0;
        int l_intQueryDataContainer = 0;
        int l_intDataContainer = 0;
        int l_intPageIndex = 0;
        int l_intPageSize = 0;

        // Step 1.1 Check l_request
        l_request.validate();

        //投信・外貨MMF表示区分
        //※nullの場合、「2:両方」とする
        if (l_request.mutualFrgnMmfDisplayDiv == null)
        {
            l_request.mutualFrgnMmfDisplayDiv =
                WEB3MutualFrgnMmfDisplayDivDef.BOTH;
        }

        // Step 1.2 Acquire WEB3Administrator object from loginInfo
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step 1.3 Check validateAuthority
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.XBMF_XBRUITO_ORDER_EXEC_INQUIRY,
            false);

        // Step 1.4 Check validateBranchPermission
        l_strBranchCodes = l_request.branchCode;
        l_administrator.validateBranchPermission(l_strBranchCodes);

        // Step 1.5 Acquire institutionCode
        l_strInstitutionCode = l_administrator.getInstitutionCode();
        l_orderExecuteDataManager = new WEB3AdminOrderExecuteDataManager();

        // Step 1.6 Create commonQueryString
        l_strCommonQuery =
            l_orderExecuteDataManager.createCommonQueryString(
                l_strInstitutionCode,
                (WEB3AdminOROrderExecutionRefCommonRequest) l_request);

        l_strProductId = l_request.productId;
        l_strTaxType = l_request.taxType;
        l_strDeliveryDiv = l_request.deliveryDiv;

        // Step 1.7 Create l_strQueryCond special to xbmf
        l_strQuery = this.createQueryString(
            l_strProductId,
            l_strTaxType,
            l_strDeliveryDiv,
            l_request.productDiv,
            l_request.mutualFrgnMmfDisplayDiv);

        // Step 1.8 Create commonQueryDataContainer
        l_strCommonQueryDataContainers =
            l_orderExecuteDataManager.createCommonQueryDataContainer(
                l_strInstitutionCode,
                (WEB3AdminOROrderExecutionRefCommonRequest) l_request);

        // Step 1.9 Create QueryDataContainer special to xbmf
        l_strQueryDataContainers =
            this.createQueryDataContainer(
                l_strProductId,
                l_strTaxType,
                l_strDeliveryDiv,
                l_request.productDiv,
                l_request.mutualFrgnMmfDisplayDiv);

        // Step 1.10 Create sortCond
        l_sortKeyUnits = l_request.sortKeys;
        l_strSortCond = this.createSortCond(l_sortKeyUnits);

        // Step 1.11Acquire orderUnits corresponding to conditions
        l_strProductDiv = l_request.productDiv;
        l_strQueryCond = l_strCommonQuery + l_strQuery;

        l_intCommonDataContainer = l_strCommonQueryDataContainers.length;
        l_intQueryDataContainer = l_strQueryDataContainers.length;
        l_intDataContainer = l_intCommonDataContainer + l_intQueryDataContainer;
        l_strQueryCondDataContainers = new String[l_intDataContainer];
        System.arraycopy(
            l_strCommonQueryDataContainers,
            0,
            l_strQueryCondDataContainers,
            0,
            l_intCommonDataContainer);

        System.arraycopy(
            l_strQueryDataContainers,
            0,
            l_strQueryCondDataContainers,
            l_intCommonDataContainer,
            l_intQueryDataContainer);

        l_lisOrderUnits =
            this.getOrderUnits(
                l_strProductDiv,
                l_strQueryCond,
                l_strQueryCondDataContainers,
                l_strSortCond);

        // Step 1.12 If return value of getOrderUnits() != null, execute the following process
        if (l_lisOrderUnits != null)
        {
            // Step 1.12.1Extract only elements of lines to be displayed
            l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            l_intPageSize = Integer.parseInt(l_request.pageSize);
            l_pageIndexInfo = new WEB3PageIndexInfo(l_lisOrderUnits, l_intPageIndex, l_intPageSize);
            l_orderUnits = (OrderUnit[]) l_pageIndexInfo.getArrayReturned(OrderUnit.class);

            // Step 1.12.2 Create mutualRuitoExecutionRefReferenceUnitList
            l_orMutualRuitoOrderExecutionRefUnits =
                this.createMutualRuitoExecutionRefReferenceUnitList(l_orderUnits);
        }

        // Step 1.13 Create response data
        l_response =
            (WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse) l_request.createResponse();

        // Step 1.14 Property Set
        if (l_lisOrderUnits == null)
        {
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";
            l_response.mutualRuitoOrderExecutionRefList = null;
        } else
        {
            l_response.totalPages = WEB3StringTypeUtility.formatNumber(
                l_pageIndexInfo.getTotalPages());
            l_response.totalRecords = WEB3StringTypeUtility.formatNumber(
                l_pageIndexInfo.getTotalRecords());
            l_response.pageIndex = l_request.pageIndex;
            l_response.mutualRuitoOrderExecutionRefList = l_orMutualRuitoOrderExecutionRefUnits;
        }

        // Step 1.15 Return the response
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create取扱商品一覧)<BR>
     * <BR>
     * 引数の商品実施情報により取扱可能な商品と取引の組み合わせを作成し、<BR>
     * 返却する。<B  R>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）パラメータ.商品実施情報.投信実施フラグ == trueの場合、<BR>
     * 　@投信の商品区分と取引区分の組み合わせを作成する。<BR>
     * 　@処理対象の取引区分(*1)について、以下の処理を繰り返す。<BR>
     * <BR>
     * 　@２－１）取扱商品インスタンスを生成する。<BR>
     * 　@２－２）生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@商品区分 = "投信"<BR>
     * 　@　@取引区分 = 処理対象の取引区分<BR>
     * 　@２－３）生成したArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * <BR>
     * 　@(*1)投信の処理対象取引区分<BR>
     * 　@　@・"投資信託買注文"<BR>
     * 　@　@・"投資信託売注文"<BR>
     * 　@　@・"投資信託乗換注文"<BR>
     * 　@　@・"投資信託募集注文"<BR>
     * <BR>
     * ３）パラメータ.商品実施情報.累投実施フラグ == trueの場合、<BR>
     * 　@累投の商品区分と取引区分の組み合わせを作成する。<BR>
     * 　@処理対象の取引区分(*2)について、以下の処理を繰り返す。<BR>
     * <BR>
     * 　@３－１）取扱商品インスタンスを生成する。<BR>
     * 　@３－２）生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@商品区分 = "累投"<BR>
     * 　@　@取引区分 = 処理対象の取引区分<BR>
     * 　@３－３）生成したArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * <BR>
     * 　@(*2)累投の処理対象取引区分<BR>
     * 　@　@・"累投買注文"<BR>
     * 　@　@・"累投売注文"<BR>
     * <BR>
     * ４）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * ---------<English>------------<BR>
     * <BR>
     * createTradingProductList<BR>
     * <BR>
     * Make a combination of a handling possible product and a trading by the argument,
     * l_productExecutionInfo, and return it.<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)If l_productExecutionInfo.mfFlag == true<BR>
     * 　@Create a combination of productDiv of Def.MF and tradingDiv<BR>
     * 　@Loop the following process for tradingDiv(*1) to be processed<BR>
     * <BR>
     * 　@2-1)Create a WEB3AdminORTradingProductUnit instance<BR>
     * 　@2-2)Set the following properties into the created instance.<BR>
     * 　@　@productDiv = Def.MF<BR>
     * 　@　@tradingDiv = tradingDiv to be processed<BR>
     * 　@2-3)Add the instance set into 'Property set' into the created ArrayList.<BR>
     * <BR>
     * 　@(*1)The tradingDiv about mutual funds<BR>
     * 　@　@・Def.MF_BUY<BR>
     * 　@　@・Def.MF_SELL<BR>
     * <BR>
     * 3)If l_productExecutionInfo.ruitoFlag == true<BR>
     * 　@Create a combonation of productDiv of Def.RUITO and tradingDiv<BR>
     * 　@Loop the following process for tradingDiv(*2) to be processed<BR>
     * <BR>
     * 　@3-1)Create a WEB3AdminORTradingProductUnit instance<BR>
     * 　@3-2)Set the following properties into the created instance.<BR>
     * 　@　@productDiv = Def.RUITO<BR>
     * 　@　@tradingDiv = tradingDiv to be processed<BR>
     * 　@3-3)Add the instance set into 'Property set' into the created ArrayList.<BR>
     * <BR>
     * 　@(*2)The tradingDiv about ruito<BR>
     * 　@　@・Def.RUITO_BUY<BR>
     * 　@　@・Def.RUITO_SELL<BR>
     * <BR>
     * 4)Return return values of the created ArrayList.toArray()<BR>
     * <BR>
     * @@param l_productExecutionInfo - l_productExecutionInfo
     * (商品実施情報)<BR>
     * <BR>
     * 商品実施情報オブジェクト<BR>
     * <BR>
     * l_productExecutionInfo<BR>
     * <BR>
     * @@return WEB3AdminORTradingProductUnit[]
     * @@roseuid 41D001F600D3
     */
    protected WEB3AdminORTradingProductUnit[] createTradingProductList
        (WEB3AdminProductExecutionInfo l_productExecutionInfo)
    {
        final String STR_METHOD_NAME =
            "createTradingProductList(WEB3ProductExecutionInfo)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminORTradingProductUnit l_web3AdminORTradingProductUnit = null;
        WEB3AdminORTradingProductUnit[] l_tradingProductUnits = null;

        // Step 1, creating an arraylist
        ArrayList l_lisTradingProductUnits = new ArrayList();

        // Step 2 If l_productExecutionInfo.mfFlag == true
        if (l_productExecutionInfo.mutualFlag)
        {
            // Step 2-1 Create a WEB3AdminORTradingProductUnit instance
            l_web3AdminORTradingProductUnit = new WEB3AdminORTradingProductUnit();

            // Step 2-2 Set the following properties into the created instance
            l_web3AdminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MF;
            l_web3AdminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.MF_BUY.intValue());

            // Step 2-3 Add the instance set into 'Property set' into the created ArrayList
            l_lisTradingProductUnits.add(l_web3AdminORTradingProductUnit);
            
			// Step 2-1 Create a WEB3AdminORTradingProductUnit instance
			l_web3AdminORTradingProductUnit = new WEB3AdminORTradingProductUnit();

            // Step 2-2 Set the following properties into the created instance
			l_web3AdminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MF;
            l_web3AdminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.MF_SELL.intValue());

            // Step 2-3 Add the instance set into 'Property set' into the created ArrayList
            l_lisTradingProductUnits.add(l_web3AdminORTradingProductUnit);
                        
            // Step 2-1 Create a WEB3AdminORTradingProductUnit instance
            l_web3AdminORTradingProductUnit = new WEB3AdminORTradingProductUnit();

            // Step 2-2 Set the following properties into the created instance
            l_web3AdminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MF;
            l_web3AdminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.MF_SWITCHING.intValue());

            // Step 2-3 Add the instance set into 'Property set' into the created ArrayList
            l_lisTradingProductUnits.add(l_web3AdminORTradingProductUnit);
            
            // Step 2-1 Create a WEB3AdminORTradingProductUnit instance
            l_web3AdminORTradingProductUnit = new WEB3AdminORTradingProductUnit();

            // Step 2-2 Set the following properties into the created instance
            l_web3AdminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.MF;
            l_web3AdminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.MF_RECRUIT.intValue());

            // Step 2-3 Add the instance set into 'Property set' into the created ArrayList
            l_lisTradingProductUnits.add(l_web3AdminORTradingProductUnit);
        }

        // Step 3 If l_productExecutionInfo.ruitoFlag == true
        if (l_productExecutionInfo.ruitoFlag)
        {
            // Step 3-1 Create a WEB3AdminORTradingProductUnit instance
            l_web3AdminORTradingProductUnit = new WEB3AdminORTradingProductUnit();

            // Step 3-2 Set the following properties into the created instance
            l_web3AdminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.RUITO;
            l_web3AdminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.RUITO_BUY.intValue());

            // Step 3-3 Add the instance set into 'Property set' into the created ArrayList
            l_lisTradingProductUnits.add(l_web3AdminORTradingProductUnit);
            
			// Step 3-1 Create a WEB3AdminORTradingProductUnit instance
			l_web3AdminORTradingProductUnit = new WEB3AdminORTradingProductUnit();

            // Step 3-2 Set the following properties into the created instance
			l_web3AdminORTradingProductUnit.productDiv = WEB3AdminProductDivDef.RUITO;
            l_web3AdminORTradingProductUnit.tradingType = String.valueOf(OrderTypeEnum.RUITO_SELL.intValue());

            // Step 3-3 Add the instance set into 'Property set' into the created ArrayList
            l_lisTradingProductUnits.add(l_web3AdminORTradingProductUnit);
        }

        // Step 4, Return return values of the created ArrayList.toArray()
        l_tradingProductUnits = new WEB3AdminORTradingProductUnit[l_lisTradingProductUnits.size()];
        l_tradingProductUnits =
            (WEB3AdminORTradingProductUnit[]) l_lisTradingProductUnits.toArray(
                l_tradingProductUnits);

        log.exiting(STR_METHOD_NAME);
        return l_tradingProductUnits;
    }

    /**
     * （create投信累投銘柄ID名称）<BR>
     * <BR>
     * 証券会社が取り扱っている投信・累投の銘柄ID名称の<BR>
     * 一覧を作成し、返却する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@パラメータ.商品実施情報.投信実施フラグ == trueの場合、<BR>
     * 　@以下の処理を行う。<BR>
     * <BR>
     * 　@２－１）投信銘柄テーブルから、以下の条件に該当する<BR>
     * 　@　@投信銘柄Paramsを銘柄IDの昇順で取得する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@　@証券会社コード = パラメータ.証券会社コード かつ<BR>
     * 　@　@　@システム取扱区分 = "WEBBROKERⅢで取り扱う"<BR>
     * <BR>
     * 　@２－２）取得できた場合、取得した投信銘柄Paramsの要素数分、<BR>
     * 　@　@以下の処理を繰り返す。<BR>
     * <BR>
     * 　@　@２－２－１）管理者注文約定照会銘柄ID名称インスタンスを<BR>
     * 　@　@　@生成する。<BR>
     * <BR>
     * 　@　@２－２－２）生成したインスタンスに以下のプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@生成したインスタンス.銘柄ID = 投信銘柄Params.銘柄ID<BR>
     * 　@　@　@生成したインスタンス.銘柄名 = 投信銘柄Params.銘柄名<BR>
     * <BR>
     * 　@　@２－２－３）プロパティセットしたインスタンスをArrayListに追加する。<BR>
     * <BR>
     * ３）　@パラメータ.商品実施情報.累投実施フラグ == trueの場合、<BR>
     * 　@以下の処理を行う。<BR>
     * <BR>
     * 　@３－１）累投銘柄テーブルから、パラメータ.証券会社コードに<BR>
     * 　@　@該当する累投銘柄Paramsを銘柄IDの昇順で取得する。<BR>
     * <BR>
     * 　@３－２）取得した累投銘柄Paramsの要素数分、<BR>
     * 　@　@以下の処理を繰り返す。<BR>
     * <BR>
     * 　@　@３－２－１）管理者注文約定照会銘柄ID名称インスタンスを<BR>
     * 　@　@　@生成する。<BR>
     * <BR>
     * 　@　@３－２－２）生成したインスタンスに以下のプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@生成したインスタンス.銘柄ID = 累投銘柄Params.銘柄ID<BR>
     * 　@　@　@生成したインスタンス.銘柄名 = 累投銘柄Params.銘柄名<BR>
     * <BR>
     * 　@　@３－２－３）プロパティセットしたインスタンスをArrayListに追加する。<BR>
     * <BR>
     * ４）ArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * ------<English>----------<BR>
     * <BR>
     * createMutualRuitoProductName<BR>
     * <BR>
     * Create a list of product names of mutual and ruito dealt by an institution and
     * return it.<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)If l_productExecutionInfo.mutualFlag == true<BR>
     * 　@Execute the following process.<BR>
     * <BR>
     * 　@2-1)Acquire mutualFundProductParams corresponding to the following
     * conditions<BR>
     *        from mutual_fund_product table in the ascending order of productId<BR>
     * <BR>
     * 　@　@[Condition]<BR>
     * 　@　@　@institution_code = l_strInstitutionCode and<BR>
     * 　@　@　@system_handling_div = Def.WEBBROKERⅢ_TREAT_IT_IN<BR>
     * <BR>
     * 　@2-2)If they are acquired, loop the following process for as many times as the
     * number<BR>
     *         of the elements of the acquired mutualFundProductParams<BR>
     * <BR>
     * 　@　@2-2-1)Create an instance of WEB3AdminORProductNameSetUnit<BR>
     * <BR>
     * 　@　@2-2-2)Set the following properties to the created instance<BR>
     * <BR>
     * 　@　@　@created instance.producrId = mutualFundProductParams.product_id<BR>
     * 　@　@　@created instance.productName = mutualFundProductParams.standard_name<BR>
     * <BR>
     * 　@　@2-2-3)Add the instance set in 'Property Set' to ArrayList<BR>
     * <BR>
     * 3)If l_productExecutionInfo.ruitoFlag == true<BR>
     * 　@Execute the following process.<BR>
     * <BR>
     * 　@3-1)Acquire ruitoProductParams corresponding to l_strInstitutionCode from<BR>
     *         ruito_product table in the ascending order of productId<BR>
     * <BR>
     * 　@3-2)Loop the following process for as many times as the number of the elements
     * of<BR>
     *         the acquired ruitoProductParams<BR>
     * <BR>
     * 　@　@3-2-1)Create an instance of WEB3AdminORProductNameSetUnit<BR>
     * <BR>
     * 　@　@3-2-2)Set the following properties to the created instance<BR>
     * <BR>
     * 　@　@　@created instance.producrId = ruitoProductParams.product_id<BR>
     * 　@　@　@created instance.productName = ruitoProductParams.standard_name<BR>
     * <BR>
     * 　@　@3-2-3)Add the instance set in 'Property Set' to ArrayList<BR>
     * <BR>
     * 4)Return return values of ArrayList.toArray()<BR>
     * <BR>
     * @@param l_strInstitutionCode - （証券会社コード）<BR>
     * <BR>
     * 証券会社コード<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_productExecutionInfo - （商品実施情報）<BR>
     * <BR>
     * 商品実施情報オブジェクト<BR>
     * <BR>
     * l_productExecutionInfo<BR>
     * <BR>
     * l_productExecutionInfo object<BR>
     * <BR>
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@throws IllegalStateException IllegalStateException
     * @@throws IllegalStateException IllegalStateException
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@return WEB3AdminORProductNameSetUnit[]
     * @@roseuid 41D2688E02C1
     */

    protected WEB3AdminORProductNameSetUnit[] createMutualRuitoProductName(
        String l_strInstitutionCode,
        WEB3AdminProductExecutionInfo l_productExecutionInfo)
        throws DataQueryException,
            DataNetworkException,
            IllegalStateException,
            WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME =
            "createMutualRuitoProductName(String, WEB3ProductExecutionInfo )";
        log.entering(STR_METHOD_NAME);

        List l_lisSearchResult = null;
        MutualFundProductParams l_mutualFundProductParam = null;
        RuitoProductParams l_ruitoProductParam = null;
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        WEB3AdminORProductNameSetUnit l_web3AdminORProductNameSetUnit = null;
        WEB3AdminORProductNameSetUnit[] l_productNameSetUnits = null;
        ArrayList l_lisProductNameSetUnit = null;
        String l_strWhere = null;
        Object[] l_objWhere = null;
        int l_intCount = 0;
        String l_strSortCond = "product_id asc";

        // Step 1, creating an arraylist
        l_lisProductNameSetUnit = new ArrayList();

        // Step 2, If l_productExecutionInfo == true, Execute the following process.
        if (l_productExecutionInfo.mutualFlag)
        {
            // Step 2-1 Creating the where clause & storing it in a Stirng variable
            l_strWhere = " institution_code = ? and system_handling_div = ?";

            // Creating an arrray of objects to store the where clause fields in it
            l_objWhere = new Object[]
                {l_strInstitutionCode, WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN};

            l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(
                    MutualFundProductRow.TYPE,
                    l_strWhere,
                    l_strSortCond,
                    null,
                    l_objWhere);
            l_intCount = l_lisSearchResult.size();

            // Step 2-2,loop through the elements of the acquired mutualFundProductParams
            for (int i = 0; i < l_intCount; i++)
            {
                // Step 2-2-1, Create an instance of WEB3AdminORProductNameSetUnit
                l_mutualFundProductParam = new MutualFundProductParams();
                l_mutualFundProductParam = (MutualFundProductParams) l_lisSearchResult.get(i);

                // Step 2-2-2, Set the following properties to the created instance
                l_web3AdminORProductNameSetUnit = new WEB3AdminORProductNameSetUnit();
                l_web3AdminORProductNameSetUnit.productId =
                    WEB3StringTypeUtility.formatNumber(l_mutualFundProductParam.product_id);
                l_web3AdminORProductNameSetUnit.productName =
                    l_mutualFundProductParam.standard_name;

                // Step 2-2-3, Add the created instance to ArrayList
                l_lisProductNameSetUnit.add(l_web3AdminORProductNameSetUnit);
            }
        }

        // Step 3, If l_productExecutionInfo == true,Execute the following process
        if (l_productExecutionInfo.ruitoFlag)
        {
            l_strWhere = "institution_code = ? ";

            // Creating an arrray of objects to store the where clause fields in it
            l_objWhere = new Object[] {l_strInstitutionCode};

            /* Step 3-1,Acquire ruitoProductParams corresponding to l_strInstitutionCode from
                  ruito_product table in the ascending order of productId   */
            l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(
                    RuitoProductRow.TYPE,
                    l_strWhere,
                    l_strSortCond,
                    null,
                    l_objWhere);

            l_intCount = l_lisSearchResult.size();

            // Step 3-2, Loop  through the number of elements of acquired ruitoProductParams
            for (int i = 0; i < l_intCount; i++)
            {
                // Step 3-2-1,Create an instance of WEB3AdminORProductNameSetUnit
                l_ruitoProductParam = new RuitoProductParams();
                l_ruitoProductParam = (RuitoProductParams) l_lisSearchResult.get(i);

                // Step 3-2-2, Set the following properties to the created instance
                l_web3AdminORProductNameSetUnit = new WEB3AdminORProductNameSetUnit();
                l_web3AdminORProductNameSetUnit.productId =
                    WEB3StringTypeUtility.formatNumber(l_ruitoProductParam.product_id);
                l_web3AdminORProductNameSetUnit.productName = l_ruitoProductParam.standard_name;

                // Step 3-2-3, Add the instance set in 'Property Set' to ArrayList
                l_lisProductNameSetUnit.add(l_web3AdminORProductNameSetUnit);
            }
        }

        // Step 4 ,Return return values of ArrayList.toArray()
        if (l_lisProductNameSetUnit.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        l_productNameSetUnits = new WEB3AdminORProductNameSetUnit[l_lisProductNameSetUnit.size()];
        l_productNameSetUnits =
            (WEB3AdminORProductNameSetUnit[]) l_lisProductNameSetUnit.toArray(
                l_productNameSetUnits);
        log.exiting(STR_METHOD_NAME);
        return l_productNameSetUnits;
    }

    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）検索条件文字列インスタンス(：String)を生成する。<BR>
     * <BR>
     * ２）パラメータ.銘柄ID != nullの場合、<BR>
     * 　@銘柄IDを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and product_id = ? "<BR>
     * <BR>
     * ３）パラメータ.口座区分 != nullの場合、<BR>
     * 　@税区分を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@[パラメータ.口座区分 == "一般"の場合]<BR>
     * 　@　@検索条件文字列 += "and tax_type = ? "<BR>
     * 　@[パラメータ.口座区分 == "特定"の場合]<BR>
     * 　@　@検索条件文字列 += "and tax_type in (?, ?) "<BR>
     * <BR>
     * ４）パラメータ.受渡方法@ != nullの場合、<BR>
     * 　@受渡方法@を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and payment_method = ? "<BR>
     * <BR>
     * -------- 投信独自の処理 --------<BR>
     * <BR>
     * ５) パラメータ.商品区分が"投信"の場合<BR>
     * <BR>
     *   5-1） パラメータ.投信･外貨MMF表示区分 == "投信のみ"の場合、<BR>
     *   　@     投信タイプを検索条件文字列に追加する。<BR>
     * <BR>
     *  　@     検索条件文字列 += "and fund_type <> ? "<BR>
     * <BR>
     *   5-2） パラメータ.投信･外貨MMF表示区分 == "外貨MMFのみ"の場合<BR>
     *          投信タイプを検索条件文字列に追加する。<BR>
     * <BR>
     *  　@     検索条件文字列 += "and fund_type = ? "<BR>
     * <BR>
     * --------------------------------<BR>
     * <BR>
     * ６）作成した検索条件文字列を返却する。<BR>
     * <BR>
     * -------<English>----------------------<BR>
     * <BR>
     * createQueryString<BR>
     * <BR>
     * create the condition string for search<BR>
     * <BR>
     * 1)Create an instance of l_strQueryCond<BR>
     * <BR>
     * 2)If l_strProductId != null<BR>
     * 　@Add l_strProductId to l_strQueryCond<BR>
     * <BR>
     * 　@l_strQueryCond += "and product_id = ? "<BR>
     * <BR>
     * 3)If l_taxType != null<BR>
     * 　@Add l_taxType to l_strQueryCond<BR>
     * <BR>
     * 　@If [l_taxType == Def.NORMAL]<BR>
     * 　@　@l_strQueryCond += "and tax_type = ? "<BR>
     * 　@If [l_taxType == Df.SPECIAL]<BR>
     * 　@　@l_strQueryCond += "and tax_type in (?, ?) "<BR>
     * <BR>
     * 4)If l_deliveryDiv != null<BR>
     * 　@Add l_deliveryDiv to l_strQueryCond<BR>
     * <BR>
     * 　@l_strQueryCond += "and payment_method = ? "<BR>
     * <BR>
     * 5)Return the created l_strQueryCond<BR>
     * <BR>
     * @@param l_strProductId - 銘柄ID<BR>
     * <BR>
     * 銘柄ID<BR>
     * <BR>
     * l_strProductId<BR>
     * <BR>
     * @@param l_strTaxType - 口座区分<BR>
     * <BR>
     * 口座区分<BR>
     * <BR>
     * l_strTaxType<BR>
     * <BR>
     * @@param l_strDeliveryDiv - 受渡方法@<BR>
     * <BR>
     * 受渡方法@<BR>
     * <BR>
     * l_strDeliveryDiv<BR>
     * <BR>
     * @@param l_strProductType - 商品区分<BR>
     * <BR>
     * 商品区分<BR>
     * <BR>
     * l_strProductType<BR>
     * <BR>
     * @@param l_strMutualFrgnMmfDisplayDiv - 投信･外貨MMF表示区分<BR>
     * <BR>
     * 投信･外貨MMF表示区分<BR>
     * <BR>
     * 表示対象の銘柄を、投信･外貨MMFで切り替えるための区分<BR>
     * <BR>
     * 0:投信のみ<BR>
     * 1:外貨MMFのみ<BR>
     * 2:両方<BR>
     * <BR>
     * ※nullの場合、「2:両方」とする<BR>
     * <BR>
     * l_strMutualFrgnMmfDisplayDiv<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41D001F60112
     */
    protected String createQueryString(
        String l_strProductId,
        String l_strTaxType,
        String l_strDeliveryDiv,
        String l_strProductType,
        String l_strMutualFrgnMmfDisplayDiv)
    {
        final String STR_METHOD_NAME = "createQueryString(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        String l_strQuery = null;

        // Step 1Create an instance of l_strQueryCond
        StringBuffer l_sbQueryCond = new StringBuffer("");

        // Step 2 If l_strProductId != null
        if (l_strProductId != null)
        {
            l_sbQueryCond.append(" and product_id = ? ");
        }

        // Step 3 If l_taxType != null
        if (l_strTaxType != null)
        {
            if (WEB3TaxTypeDef.NORMAL.equals(l_strTaxType))
            {
                l_sbQueryCond.append(" and tax_type = ? ");
            }
            if (WEB3TaxTypeDef.SPECIAL.equals(l_strTaxType))
            {
                l_sbQueryCond.append(" and tax_type in (?, ?) ");
            }
        }

        // Step 4 If l_deliveryDiv != null
        if (l_strDeliveryDiv != null)
        {
            l_sbQueryCond.append(" and payment_method = ? ");
        }

        //5) パラメータ.商品区分が"投信"の場合
        if (WEB3AdminProductDivDef.MF.equals(l_strProductType))
        {
            //5-1） パラメータ.投信･外貨MMF表示区分 == "投信のみ"の場合、
            //投信タイプを検索条件文字列に追加する。
            //検索条件文字列 += "and fund_type <> ? "
            if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(l_strMutualFrgnMmfDisplayDiv))
            {
                l_sbQueryCond.append(" and fund_type <> ? ");
            }
            //  5-2） パラメータ.投信･外貨MMF表示区分 == "外貨MMFのみ"の場合
            //投信タイプを検索条件文字列に追加する。
            //検索条件文字列 += "and fund_type = ? "
            else if (WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(l_strMutualFrgnMmfDisplayDiv))
            {
                l_sbQueryCond.append(" and fund_type = ? ");
            }
        }

        l_strQuery = l_sbQueryCond.toString();
        // Step  5 Return the created l_strQueryCond
        log.exiting(STR_METHOD_NAME);
        return l_strQuery;
    }

    /**
     * （create検索条件データコンテナ）<BR>
     * <BR>
     * 検索条件データコンテナを作成する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）パラメータ.銘柄ID != nullの場合、<BR>
     * 　@銘柄IDを生成したArrayListに追加する。<BR>
     * <BR>
     * ３）パラメータ.口座区分 != nullの場合、<BR>
     * 　@以下の条件により、税区分を生成したArrayListに追加する。<BR>
     * <BR>
     * 　@[パラメータ.口座区分 == "一般"の場合]<BR>
     * 　@　@以下の値を追加<BR>
     * 　@　@・TaxTypeEnum.一般<BR>
     * <BR>
     * 　@[パラメータ.口座区分 == "特定"の場合]<BR>
     * 　@　@以下の値を追加<BR>
     * 　@　@・TaxTypeEnum.特定<BR>
     * 　@　@・TaxTypeEnum.特定かつ源泉徴収<BR>
     * <BR>
     * ４）パラメータ.受渡方法@ != nullの場合、<BR>
     * 　@パラメータ.受渡方法@を生成したArrayListに追加する。<BR>
     * <BR>
     * -------- 投信独自の処理 --------<BR>
     * <BR>
     * ５) パラメータ.商品区分が"投信"の場合<BR>
     * <BR>
     * 5-1) パラメータ.投信･外貨MMF表示区分が"投信のみ"または"外貨MMFのみ"の場合、生成したArrayListに以下を追加する。<BR>
     *     ・MutualFundTypeEnum.外貨MMF<BR>
     * <BR>
     * --------------------------------<BR>
     * <BR>
     * ６）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * -----<English>--------------------------<BR>
     * <BR>
     * createQueryDataContainer<BR>
     * <BR>
     * create queryDataContainer<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)If l_strProductId != null<BR>
     * 　@Add l_strProductId to the created ArrayList<BR>
     * <BR>
     * 2)If l_taxType != null<BR>
     * 　@Add l_taxType to the created ArrayList according to the following
     * conditions<BR>
     * <BR>
     * 　@If [l_taxType == Def.NORMAL]<BR>
     * 　@　@Add the following value<BR>
     * 　@　@・TaxTypeEnum.Def.NORMAL<BR>
     * <BR>
     * 　@If [l_taxType == Def.SPECIAL]<BR>
     * 　@　@Add the following value<BR>
     * 　@　@・TaxTypeEnum.Def.SPECIAL<BR>
     * 　@　@・TaxTypeEnum.Def.SPECIAL_WITHHOLD<BR>
     * <BR>
     * 4)If l_deliveryDiv != null<BR>
     * 　@Add l_deliveryDiv to the created ArrayList<BR>
     * <BR>
     * 5)Return the return value of the created ArrayList.toArray()<BR>
     * <BR>
     * @@param l_strProductId - 銘柄ID<BR>
     * <BR>
     * 銘柄ID<BR>
     * <BR>
     * l_strProductId<BR>
     * <BR>
     * @@param l_strTaxType - （口座区分）<BR>
     * <BR>
     * 口座区分<BR>
     * <BR>
     * l_strTaxType<BR>
     * <BR>
     * @@param l_strDeliveryDiv - （受渡方法@）<BR>
     * <BR>
     * 受渡方法@<BR>
     * <BR>
     * l_strDeliveryDiv<BR>
     * <BR>
     * @@param l_strProductType - 商品区分<BR>
     * <BR>
     * 商品区分<BR>
     * <BR>
     * l_strProductType<BR>
     * <BR>
     * @@param l_strMutualFrgnMmfDisplayDiv - 投信･外貨MMF表示区分<BR>
     * <BR>
     * 投信･外貨MMF表示区分<BR>
     * <BR>
     * l_strMutualFrgnMmfDisplayDiv<BR>
     * <BR>
     * @@return String[]
     * @@roseuid 41D001F60131
     */
    protected String[] createQueryDataContainer(
        String l_strProductId,
        String l_strTaxType,
        String l_strDeliveryDiv,
        String l_strProductType,
        String l_strMutualFrgnMmfDisplayDiv)
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String , String , String , String , String)";
        log.entering(STR_METHOD_NAME);

        String[] l_strDataContainers = null;

        // Step 1 Create ArrayList
        ArrayList l_lisDataContainer = new ArrayList();

        // Step 2 If l_strProductId != null
        if (l_strProductId != null)
        {
            l_lisDataContainer.add(l_strProductId);
        }

        // Step 3 If l_taxType != null
        if (l_strTaxType != null)
        {
            if (WEB3TaxTypeDef.NORMAL.equals(l_strTaxType))
            {
                l_lisDataContainer.add(
                    WEB3StringTypeUtility.formatNumber(TaxTypeEnum.IntValues.NORMAL));
            }
            if (WEB3TaxTypeDef.SPECIAL.equals(l_strTaxType))
            {
                l_lisDataContainer.add(
                    WEB3StringTypeUtility.formatNumber(TaxTypeEnum.IntValues.SPECIAL));
                l_lisDataContainer.add(
                    WEB3StringTypeUtility.formatNumber(TaxTypeEnum.IntValues.SPECIAL_WITHHOLD));
            }
        }

        // Step 4 If l_deliveryDiv != null
        if (l_strDeliveryDiv != null)
        {
            l_lisDataContainer.add(l_strDeliveryDiv);
        }

        //5) パラメータ.商品区分が"投信"の場合
        if (WEB3AdminProductDivDef.MF.equals(l_strProductType))
        {
            //5-1) パラメータ.投信･外貨MMF表示区分が"投信のみ"または"外貨MMFのみ"の場合、生成したArrayListに以下を追加する。
            //  ・MutualFundTypeEnum.外貨MMF
            if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(l_strMutualFrgnMmfDisplayDiv)
                || WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(l_strMutualFrgnMmfDisplayDiv))
            {
                l_lisDataContainer.add(MutualFundTypeEnum.FOREIGN_MMF.intValue() + "");
            }
        }

        l_strDataContainers = new String[l_lisDataContainer.size()];
        l_strDataContainers = (String[]) l_lisDataContainer.toArray(l_strDataContainers);

        // Step 5 Return the return value of the created ArrayList.toArray()
        log.exiting(STR_METHOD_NAME);
        return l_strDataContainers;
    }

    /**
     * (createソート条件)<BR>
     * <BR>
     * ソート条件を作成する。<BR>
     * <BR>
     * １）パラメータ.ソートキー == nullの場合、<BR>
     * 　@　@"銘柄ID 昇順"のソート条件を返却する。<BR>
     * <BR>
     * ２）ソート条件文字列(：String)を作成する。<BR>
     * <BR>
     * ３）パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@３－１）ソートキー.キー項目を対応する列物理名に変換し、<BR>
     * 　@　@作成したソート条件文字列に追加する。<BR>
     * <BR>
     * 　@　@・「部店コード」　@→　@投信注文単位.部店ID<BR>
     * 　@　@・「顧客コード」　@→　@投信注文単位.口座ID<BR>
     * 　@　@・「銘柄コード」　@→　@投信注文単位.銘柄ID<BR>
     * 　@　@・「取引区分」　@→　@投信注文単位.注文種別<BR>
     * 　@　@・「注文時間」　@→　@投信注文単位.受注日時<BR>
     * 　@　@・「発注日」　@→　@投信注文単位.発注日<BR>
     * 　@　@・「受渡日」　@→　@投信注文単位.受渡日<BR>
     * 　@　@・「扱者コード(SONAR)」　@→　@注文単位.扱者コード(SONAR)<BR>
     * <BR>
     * 　@３－２）ソートキー.昇順／降順に対応する取得順序<BR>
     * 　@　@(asc or desc)をソート条件文字列に追加する。<BR>
     * <BR>
     * ４）ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加<BR>
     * <BR>
     * ５）作成したソート条件文字列を返却する。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * createSortCond<BR>
     * <BR>
     * Create sortCondition<BR>
     * 1)Create sortCond(:String)<BR>
     * <BR>
     * 2)Loop the following process for as many times as the number of the elements of
     * sortKeys<BR>
     * 　@2-1)Convert sortKeys.ketItem to the corresponding field names, and<BR>
     * 　@　@ add them to the created sortCond string<BR>
     * <BR>
     * 　@　@・branchCode　@→　@mutual_fund_order_unit.branch_id<BR>
     * 　@　@・accountCode　@→　@mutual_fund_order_unit.account_id<BR>
     * 　@　@・productCode　@→　@mutual_fund_order_unit.product_id<BR>
     * 　@　@・tradingDiv　@→　@mutual_fund_order_unit.order_type<BR>
     * 　@　@・orderDate　@→　@mutual_fund_order_unit.received_date_time<BR>
     * 　@　@・orderBizDate　@→　@mutual_fund_order_unit.biz_date<BR>
     * 　@　@・deliveryDate　@→　@mutual_fund_order_unit.delivery_date<BR>
     * <BR>
     * 　@2-2)Acquired order by sortKeys.ascDesc<BR>
     * 　@　@Add (asc or desc) to sortCond string<BR>
     * <BR>
     * 3)Add order_unit.last_updated_timestamp to the end of sortCond in ascending
     * order<BR>
     * <BR>
     * 4)Return the created sortCond string<BR>
     * <BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * <BR>
     * ソートキー<BR>
     * <BR>
     * l_sortKeys<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41D001F60150
     */
    protected String createSortCond(WEB3AdminOROrderExecutionSortKeyUnit[] l_sortKeys)
    {
        final String STR_METHOD_NAME =
            "createSortCond(WEB3AdminOROrderExecutionSortKeyUnit[])";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbSortCond = null;
        String l_strSortCond = null;
        int l_intSortKeysCnt = l_sortKeys.length;

        // Step 2 Create sortCond
        l_sbSortCond = new StringBuffer("");

        // Step 3 Loop the following process for as many times as the number of the elements
        for (int i = 0; i < l_intSortKeysCnt; i++)
        {
            /* If sortCondList is not empty(""), add ","(comma) to sortCondList
                before processing. */
            if (!l_sbSortCond.toString().equals(""))
            {
                l_sbSortCond.append(", ");
            }

            // Step 3.1Add conditions to created sortCondList based on the values of keyItem
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.BRANCH_CODE))
            {
                l_sbSortCond.append("branch_id");
            }
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.ACCOUNT_CODE))
            {
                l_sbSortCond.append("substr(account_id,9,6)");
            }
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.PRODUCT_CODE))
            {
                l_sbSortCond.append("product_id");
            }
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.TRADING_TYPE))
            {
                l_sbSortCond.append("order_type");
            }
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.ORDER_DATE))
            {
                l_sbSortCond.append("received_date_time");
            }
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.ORDER_BIZ_DATE))
            {
                l_sbSortCond.append("biz_date");
            }
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.DELIVERY_DATE))
            {
                l_sbSortCond.append("delivery_date");
            }
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.SONAR_TRADER_CODE))
            {
                l_sbSortCond.append("sonar_trader_code");
            }

            // Step 3.2: Add ascDesc to sortCondList according to the value of sortKeys.ascDesc
            if (l_sortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
            {
                l_sbSortCond.append(" asc");
            }
            if (l_sortKeys[i].ascDesc.equals(WEB3AscDescDef.DESC))
            {
                l_sbSortCond.append(" desc");
            }
        }

        // Step 4 Add info_order_unit.last_updated_timestamp to the end of sortCond in ascending
        l_sbSortCond.append(" , last_updated_timestamp asc");
        l_strSortCond = l_sbSortCond.toString();
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }

    /**
     * （get注文単位一覧）<BR>
     * <BR>
     * 引数の商品区分に該当する注文単位Paramsの一覧を返却する。<BR>
     * ※商品区分がnullの場合は、投信・累投両方の注文単位を取得する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）パラメータ.商品区分 == nullの場合、<BR>
     * 　@以下の処理を行う。<BR>
     * <BR>
     * 　@２－１）投信注文単位Paramsを取得する。<BR>
     * 　@　@QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@　@arg0：　@投信注文単位Row.TYPE<BR>
     * 　@　@　@arg1：　@パラメータ.検索条件文字列<BR>
     * 　@　@　@arg2：　@パラメータ.ソート条件<BR>
     * 　@　@　@arg3：　@null<BR>
     * 　@　@　@arg4：　@パラメータ.検索条件データコンテナ<BR>
     * <BR>
     * 　@　@検索結果が取得できた場合、検索結果をArrayListに追加する。<BR>
     * <BR>
     * 　@２－２）累投注文単位Paramsを取得する。<BR>
     * 　@　@QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@　@arg0：　@累投注文単位Row.TYPE<BR>
     * 　@　@　@arg1：　@パラメータ.検索条件文字列<BR>
     * 　@　@　@arg2：　@パラメータ.ソート条件<BR>
     * 　@　@　@arg3：　@null<BR>
     * 　@　@　@arg4：　@パラメータ.検索条件データコンテナ<BR>
     * <BR>
     * 　@　@検索結果が取得できた場合、検索結果をArrayListに追加する。<BR>
     * <BR>
     * ３）パラメータ.商品区分 != nullの場合、商品区分に該当する<BR>
     * 　@注文単位を取得する。<BR>
     * 　@QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@<BR>
     * 　@　@　@[パラメータ.商品区分 == "投信"の場合]<BR>
     * 　@　@　@　@投信注文単位Row.TYPEをセットする。<BR>
     * 　@　@　@[パラメータ.商品区分 == "累投"の場合]<BR>
     * 　@　@　@　@累投注文単位Row.TYPEをセットする。<BR>
     * 　@　@arg1：　@パラメータ.検索条件文字列<BR>
     * 　@　@arg2：　@パラメータ.ソート条件<BR>
     * 　@　@arg3：　@null<BR>
     * 　@　@arg4：　@パラメータ.検索条件データコンテナ<BR>
     * <BR>
     * 　@　@検索結果が取得できた場合、検索結果をArrayListに追加する。<BR>
     * <BR>
     * ４）ArrayListを返却する。<BR>
     * 　@※ArrayListの要素数が0の場合、nullを返却する。<BR>
     * <BR>
     * -----<English>----------------<BR>
     * <BR>
     * getOrderUnits<BR>
     * <BR>
     * Return the list of orderUnits corresponding to the argument, l_productDiv<BR>
     * ※If l_productDiv is null, acquire l_orderUnit of both mutual and ruito<BR>
     * <BR>
     * １）Create ArrayList<BR>
     * <BR>
     * 2)If l_productDiv == null<BR>
     * 　@execute the following processes<BR>
     * <BR>
     * 　@2-1)Acquire mutualFundOrderUnitParams<BR>
     * 　@　@Call QueryProcessor.doFindAllQuery() method<BR>
     * <BR>
     * 　@　@[Parameter set into doFindAllQuery()]<BR>
     * 　@　@arg0: l_mutualFundOrderUnitRow.TYPE<BR>
     * 　@　@arg1: l_strQueryCond<BR>
     * 　@　@arg2: l_strSortCond<BR>
     * 　@　@arg3: null<BR>
     * 　@　@arg4: l_strQueryCondDataContainer<BR>
     * <BR>
     * 　@　@If it is able to acquire the search result, convert result to MutualFundOrderUnit then
     *     add it to ArrayList<BR>
     * <BR>
     * 　@2-2)Acquire ruitoOrderUnitParams<BR>
     * 　@　@Call QueryProcessor.doFindAllQuery() method<BR>
     * <BR>
     * 　@　@[Parameter set into doFindAllQuery()]<BR>
     * 　@  arg0: l_ruitoOrderUnitRow.TYPE<BR>
     * 　@　@arg1: l_strQueryCond<BR>
     * 　@　@arg2: l_strSortCond<BR>
     * 　@  arg3: null<BR>
     * 　@　@arg4: l_strQueryCondDataContainer<BR>
     * <BR>
     * 　@　@If it is able to acquire the search result, convert result to RuitoOrderUnit then
     *     add it to ArrayList<BR>
     * <BR>
     * 3)If l_productDiv != null,<BR>
     * 　@acquire l_orderUnit corresponding to l_productDiv<BR>
     * 　@Call QueryProcessor.doFindAllQuery() method<BR>
     * <BR>
     * 　@[Parameter set into doFindAllQuery()]<BR>
     * 　@　@arg0:<BR>
     * 　@　@　@[l_productDiv == Def.MF]<BR>
     * 　@　@　@　@Set mutualFundOrderUnitRow.TYPE<BR>
     * 　@　@　@[l_productDiv == Def.RUITO]<BR>
     * 　@　@　@　@Set ruitoOrderUnitRow.TYPE<BR>
     * 　@　@arg1: l_strQueryCond<BR>
     * 　@　@arg2: l_strSortCond<BR>
     * 　@　@arg3: null<BR>
     * 　@　@arg4: l_strQueryCondDataContainer<BR>
     * <BR>
     * 　@　@If it is able to acquire the search result, convert result to
     *     either MutualOrderUnit or RuitoOrderUnit depends upon l_productDiv then
     *     add it to ArrayList<BR>
     * <BR>
     * 4)Return ArrayList<BR>
     * 　@※If the number of the elements of ArrayList is 0, set null<BR>
     * <BR>
     * @@param l_strProductDiv - 商品区分<BR>
     * <BR>
     * 商品区分<BR>
     * <BR>
     * l_strProductDiv<BR>
     * <BR>
     * @@param l_strQueryCond - (検索条件文字列)<BR>
     * <BR>
     * 検索条件文字列<BR>
     * <BR>
     * l_strQueryCond<BR>
     * <BR>
     * @@param l_strQueryCondDataContainers - (検索条件データコンテナ)<BR>
     * <BR>
     * 検索条件データコンテナ<BR>
     * <BR>
     * l_strQueryCondDataContainer<BR>
     * <BR>
     * @@param l_strSortCond - l_strSortCond
     * (ソート条件)<BR>
     * <BR>
     * ソート条件<BR>
     * <BR>
     * l_strSortCond<BR>
     * <BR>
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@throws IllegalStateException IllegalStateException
     * @@return ArrayList
     * @@roseuid 41D001F6018F
     */
    protected ArrayList getOrderUnits(
        String l_strProductDiv,
        String l_strQueryCond,
        String[] l_strQueryCondDataContainers,
        String l_strSortCond)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        final String STR_METHOD_NAME = "getOrderUnits(String, String, String[], String)";
        log.entering(STR_METHOD_NAME);

        List l_lisMutual = null;
        List l_lisRuito = null;
        MutualFundOrderManager l_mutualOrderManager = null;
        MutualFundOrderUnit l_mutualOrderUnit = null;
        RuitoOrderManager l_ruitoOrderManager = null;
        RuitoOrderUnit l_ruitoOrderUnit = null;
        int l_intCount = 0;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // Step １Create ArrayList
        ArrayList l_lisOrderUnit = new ArrayList();

        // Step 2 If l_productDiv == null
        if (l_strProductDiv == null)
        {
            // Step 2-1Acquire mutualFundOrderUnitParams
            l_lisMutual =
                l_queryProcessor.doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_strQueryCond,
                    l_strSortCond,
                    null,
                    l_strQueryCondDataContainers);
            l_mutualOrderManager =
                (MutualFundOrderManager) l_finApp
                    .getTradingModule(ProductTypeEnum.MUTUAL_FUND)
                    .getOrderManager();
            l_intCount = l_lisMutual.size();
            for (int i = 0; i < l_intCount; i++)
            {
                l_mutualOrderUnit =
                    (MutualFundOrderUnit) l_mutualOrderManager.toOrderUnit(
                        (MutualFundOrderUnitParams) l_lisMutual.get(i));
                l_lisOrderUnit.add(l_mutualOrderUnit);
            }

			l_queryProcessor = Processors.getDefaultProcessor();
            // Step 2-2 Acquire ruitoOrderUnitParams
            l_lisRuito =
                l_queryProcessor.doFindAllQuery(
                    RuitoOrderUnitRow.TYPE,
                    l_strQueryCond,
                    l_strSortCond,
                    null,
                    l_strQueryCondDataContainers);
            l_ruitoOrderManager =
                (RuitoOrderManager) l_finApp
                    .getTradingModule(ProductTypeEnum.RUITO)
                    .getOrderManager();
            l_intCount = l_lisRuito.size();
            for (int i = 0; i < l_intCount; i++)
            {
                l_ruitoOrderUnit =
                    (RuitoOrderUnit) l_ruitoOrderManager.toOrderUnit(
                        (RuitoOrderUnitParams) l_lisRuito.get(i));
                l_lisOrderUnit.add(l_ruitoOrderUnit);
            }
        } else
        {
            if (WEB3AdminProductDivDef.MF.equals(l_strProductDiv))
            {
                l_lisMutual =
                    l_queryProcessor.doFindAllQuery(
                        MutualFundOrderUnitRow.TYPE,
                        l_strQueryCond,
                        l_strSortCond,
                        null,
                        l_strQueryCondDataContainers);
                l_mutualOrderManager =
                    (MutualFundOrderManager) l_finApp
                        .getTradingModule(ProductTypeEnum.MUTUAL_FUND)
                        .getOrderManager();
                l_intCount = l_lisMutual.size();
                for (int i = 0; i < l_intCount; i++)
                {
                    l_mutualOrderUnit =
                        (MutualFundOrderUnit) l_mutualOrderManager.toOrderUnit(
                            (MutualFundOrderUnitParams) l_lisMutual.get(i));
                    l_lisOrderUnit.add(l_mutualOrderUnit);
                }
            }
            if (WEB3AdminProductDivDef.RUITO.equals(l_strProductDiv))
            {
                l_lisRuito =
                    l_queryProcessor.doFindAllQuery(
                        RuitoOrderUnitRow.TYPE,
                        l_strQueryCond,
                        l_strSortCond,
                        null,
                        l_strQueryCondDataContainers);
                l_ruitoOrderManager =
                    (RuitoOrderManager) l_finApp
                        .getTradingModule(ProductTypeEnum.RUITO)
                        .getOrderManager();
                l_intCount = l_lisRuito.size();
                for (int i = 0; i < l_intCount; i++)
                {
                    l_ruitoOrderUnit =
                        (RuitoOrderUnit) l_ruitoOrderManager.toOrderUnit(
                            (RuitoOrderUnitParams) l_lisRuito.get(i));
                    l_lisOrderUnit.add(l_ruitoOrderUnit);
                }
            }
        }

        // Step 4 Return ArrayList
        log.exiting(STR_METHOD_NAME);
        if (l_lisOrderUnit.size() == 0)
        {
            return null;
        }
        return l_lisOrderUnit;
    }

    /**
     * 引数の注文単位一覧より、投信累投注文約定照会Unitの一覧を
     * 作成し、返却する。
     *
     * シーケンス図
     * 「(管理者投信累投注文約定照会サービス)create投信累投注文約定照会Unit一覧」
     * 参照
     * <BR>
     * ----<English>-----------<BR>
     * <BR>
     * createMutualRuitoExecutionRefReferenceUnitList<BR>
     * <BR>
     * Create munualRuitoOrderExecutionRefList from the argument, l_orderUnits and
     * return it.
     * <BR>
     * Refer to the sequence diagram "(administrator: mutual ruito order execution
     * inquiry service)createMutualRuitoExecutionRefReferenceUnitList"
     * <BR>
     * @@param l_orderUnits - (注文単位一覧)<BR>
     * <BR>
     * 先物OP注文単位の配列<BR>
     * <BR>
     * l_orderUnits<BR>
     * <BR>
     * An array of futures option order unit<BR>
     * <BR>
     * @@throws NotFoundException NotFoundException
     * @@throws WEB3BaseException WEB3BaseException
     * @@return WEB3AdminORMutualRuitoOrderExecutionRefUnit[]
     * @@roseuid 41D001F6019E
     */
    protected WEB3AdminORMutualRuitoOrderExecutionRefUnit[]
        createMutualRuitoExecutionRefReferenceUnitList(OrderUnit[] l_orderUnits)
        throws NotFoundException, WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createMutualRuitoExecutionRefReferenceUnitList(OrderUnit[])";
        log.entering(STR_METHOD_NAME);

        final String STR_DATE_FORMAT = "yyyyMMdd";
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager = null;
        WEB3GentradeAccountManager l_genTradeAccountManager = null;
        Branch l_branch = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        Trader l_trader = null;
        WEB3AdminOrderExecuteDataManager l_orderExecuteDataManager = null;
        WEB3AdminORMutualRuitoOrderExecutionRefUnit l_orMutualRuitoOrderExecutionRefUnit = null;
        WEB3AdminORMutualRuitoOrderExecutionRefUnit[] l_orMutualRuitoOrderExecutionRefUnits = null;
        MutualFundOrderUnit l_mutualFundOrderUnit = null;
        RuitoOrderUnit l_ruitoOrderUnit = null;
        WEB3MutualFundProduct l_mfProduct = null;
        WEB3MutualFundProductManager l_mfProductManager = null;
        TradingModule l_tradingModule = null;
        MutualFundOrderUnitParams l_mutualFundOrderUnitParam = null;
        RuitoOrderUnitParams l_ruitoOrderUnitParam = null;
        OrderTypeEnum l_orderTypeEnum = null;
        RuitoProductManager l_ruitoProductManager = null;
        RuitoProduct l_ruitoProduct = null;
        TaxTypeEnum l_taxTypeEnum = null;
        Date l_orderBizDate = null;
        ArrayList l_lisMutualRuitoOrderExecutionRefUnit = null;
        String l_strTradingType = null;
        String l_strOrderExecute = null;
        long l_lngBranchId = 0L;
        long l_lngMainAccount = 0L;
        long l_lngTraderId = 0L;
        int l_intCount = 0;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_genTradeAccountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        l_orderExecuteDataManager = new WEB3AdminOrderExecuteDataManager();

        l_gentradeFinObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        // Step 1.1 Create ArrayList
        l_lisMutualRuitoOrderExecutionRefUnit = new ArrayList();
        l_intCount = l_orderUnits.length;

        // Step 1.2 Loop process for as many times as the number of the elements of l_orderUnits
        for (int i = 0; i < l_intCount; i++)
        {
            l_lngBranchId = l_orderUnits[i].getBranchId();

            // Step 1.2.1 Acquire l_branch
            l_branch = l_genTradeAccountManager.getBranch(l_lngBranchId);

            // Step 1.2.2 Acquire l_account
            l_lngMainAccount = l_orderUnits[i].getAccountId();
            l_mainAccount =
                (WEB3GentradeMainAccount)l_genTradeAccountManager.getMainAccount(l_lngMainAccount);

            // Step 1.2.5 Acquire tradingType
            l_orderTypeEnum = l_orderUnits[i].getOrderType();
            l_strTradingType = l_orderExecuteDataManager.getTradingType(l_orderTypeEnum);

            // Step 1.2.6 Acquire orderStatus
            l_strOrderExecute = l_orderExecuteDataManager.getOrderStateDivPR(l_orderUnits[i]);

            //1.2.7.注文単位紹介区分(注文単位ID : long, 銘柄タイプ : long)
			//注文単位紹介区分オブジェクトを取得する。 
			//[引数] 
			//注文単位ID:注文単位.注文単位ID 
			//銘柄タイプ:注文単位.get銘柄.銘柄タイプ
            WEB3GentradeOrderUnitIntroduceDiv l_orderDiv = null;
            String l_strIntroduceDiv = "";
            String l_strIntroduceCode = "";
            try
            {
                l_orderDiv = 
                	new WEB3GentradeOrderUnitIntroduceDiv(l_orderUnits[i].getOrderUnitId(),
            			l_orderUnits[i].getProduct().getProductType());
                
                //1.2.8.get紹介区分( )
                l_strIntroduceDiv = l_orderDiv.getIntroduceBranchDiv();
                
                //1.2.9.get紹介店コード( )
                l_strIntroduceCode = l_orderDiv.getIntroduceBranchCode();
            }
            catch(WEB3SystemLayerException l_ex)
            {
                l_strIntroduceDiv = null;
                l_strIntroduceCode = null;
            }
            
            // Step 1.2.10 Create WEB3AdminORMutualRuitoOrderExecutionRefUnit instance
            l_orMutualRuitoOrderExecutionRefUnit =
                new WEB3AdminORMutualRuitoOrderExecutionRefUnit();

            // Step 1.2.11 Property Set
            l_orMutualRuitoOrderExecutionRefUnit.id =
                WEB3StringTypeUtility.formatNumber(l_orderUnits[i].getOrderId());
            l_orMutualRuitoOrderExecutionRefUnit.branchCode = l_branch.getBranchCode();
            l_orMutualRuitoOrderExecutionRefUnit.accountCode = l_mainAccount.getDisplayAccountCode();
            //顧客名       ＝　@getMainAccount()の戻り値.get顧客表示名 ()
            l_orMutualRuitoOrderExecutionRefUnit.accountName =
                l_mainAccount.getDisplayAccountName();
            l_orMutualRuitoOrderExecutionRefUnit.tradingType = l_strTradingType;
            l_orMutualRuitoOrderExecutionRefUnit.orderQuantity =
                WEB3StringTypeUtility.formatNumber(l_orderUnits[i].getQuantity());
            l_orMutualRuitoOrderExecutionRefUnit.orderState = l_strOrderExecute;
            l_orMutualRuitoOrderExecutionRefUnit.deliveryDate = l_orderUnits[i].getDeliveryDate();
            l_orMutualRuitoOrderExecutionRefUnit.introduceStoreDiv = l_strIntroduceDiv;
            l_orMutualRuitoOrderExecutionRefUnit.introduceStoreCode = l_strIntroduceCode;


            l_lngTraderId = l_orderUnits[i].getTraderId();

            // Step 1.2.12 Type of orderUnit to be processed == MutualFundOrderUnit
            if (l_orderUnits[i] instanceof MutualFundOrderUnit)
            {
                // Step 1.2.12.1 Acquire mutualFundProduct
                l_mutualFundOrderUnit = (MutualFundOrderUnit) l_orderUnits[i];
                l_mutualFundOrderUnitParam =
                  (MutualFundOrderUnitParams) l_mutualFundOrderUnit.getDataSourceObject();

                l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
                l_mfProductManager = (WEB3MutualFundProductManager) l_tradingModule.getProductManager();
                l_mfProduct =
                    (WEB3MutualFundProduct) l_mfProductManager.getProduct(
                        l_mutualFundOrderUnitParam.getProductId());
                        
				MutualFundProductParams l_mutualFundProductParams =
					(MutualFundProductParams)l_mfProduct.getDataSourceObject();
					
                // Step 1.2.9.2 Property Set
                l_orMutualRuitoOrderExecutionRefUnit.productDiv = WEB3AdminProductDivDef.MF;
                l_orMutualRuitoOrderExecutionRefUnit.estimatedPrice =
                    WEB3StringTypeUtility.formatNumber(
                    l_mutualFundOrderUnitParam.getEstimatedPrice());
                l_orMutualRuitoOrderExecutionRefUnit.orderChannel =
                    l_mutualFundOrderUnitParam.getOrderChanel();
                l_orMutualRuitoOrderExecutionRefUnit.orderDate =
                    l_mutualFundOrderUnitParam.getReceivedDateTime();
                l_orderBizDate =
                    WEB3DateUtility.getDate(
                        l_mutualFundOrderUnitParam.getBizDate(),
                        STR_DATE_FORMAT);
                l_orMutualRuitoOrderExecutionRefUnit.orderBizDate = l_orderBizDate;
                l_orMutualRuitoOrderExecutionRefUnit.orderRootDiv =
                    l_mutualFundOrderUnitParam.getOrderRootDiv();
                l_taxTypeEnum = l_mutualFundOrderUnitParam.getTaxType();
                if (TaxTypeEnum.NORMAL.equals(l_taxTypeEnum))
                {
                    l_orMutualRuitoOrderExecutionRefUnit.taxType =
                        WEB3MFAccountDivDef.NORMAL;
                }

                // Checks if l_taxTypeEnum = 2 or l_taxTypeEnum = 3
                if (TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum)
                    || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeEnum))
                {
                    l_orMutualRuitoOrderExecutionRefUnit.taxType =
                        WEB3MFAccountDivDef.SPECIAL;
                }

                if (TaxTypeEnum.UNDEFINED.equals(l_taxTypeEnum))
                {
                    l_orMutualRuitoOrderExecutionRefUnit.taxType =
                        WEB3MFAccountDivDef.OTHER;
                }
                l_orMutualRuitoOrderExecutionRefUnit.settleDiv =
                    l_mutualFundOrderUnitParam.getSettlementDiv();
                l_orMutualRuitoOrderExecutionRefUnit.productId = 
                	WEB3StringTypeUtility.formatNumber(l_mutualFundProductParams.getProductId());
                l_orMutualRuitoOrderExecutionRefUnit.productName = l_mutualFundProductParams.getStandardName();
				if(l_mutualFundOrderUnitParam.getFundSellDiv() == null)
				{
					if(l_mutualFundOrderUnitParam.getQuantityType().equals(QuantityTypeEnum.QUANTITY))
					{
						l_orMutualRuitoOrderExecutionRefUnit.sellDiv =
							WEB3SellDivDef.COUNT_DESIGNATE;
					}
					else if(l_mutualFundOrderUnitParam.getQuantityType().equals(QuantityTypeEnum.AMOUNT))
					{
						l_orMutualRuitoOrderExecutionRefUnit.sellDiv =
							WEB3SellDivDef.MONEY_DESIGNATE;
					}
				}
				else
				{
                	l_orMutualRuitoOrderExecutionRefUnit.sellDiv =
                    	l_mutualFundOrderUnitParam.getFundSellDiv();
				}
                l_orMutualRuitoOrderExecutionRefUnit.deliveryDiv =
                    l_mutualFundOrderUnitParam.getPaymentMethod();
                l_orMutualRuitoOrderExecutionRefUnit.executionTimestamp =
                    l_mutualFundOrderUnitParam.getExecDate();
                if (l_mutualFundOrderUnitParam.trader_id != null)
                {
                    l_trader = l_gentradeFinObjectManager.getTrader(l_lngTraderId);
                    l_orMutualRuitoOrderExecutionRefUnit.traderCode = l_trader.getTraderCode();
                }
                l_orMutualRuitoOrderExecutionRefUnit.sonarTraderCode = 
                	l_mutualFundOrderUnitParam.getSonarTraderCode();

                l_orMutualRuitoOrderExecutionRefUnit.sellBuyDiv =
                    l_mutualFundOrderUnitParam.getRequestDiv();

                WEB3MutualFundOrderManager l_orderManager =
                    (WEB3MutualFundOrderManager)l_finApp.getTradingModule(
                        ProductTypeEnum.MUTUAL_FUND).getOrderManager();

                //注文数量区分＝拡張投信注文マネージャ.get注文数量区分( 投信注文単位 )
                l_orMutualRuitoOrderExecutionRefUnit.mutualOrderQuantityType =
                    l_orderManager.getOrderQuantityDiv(l_mutualFundOrderUnit);

                //概算受渡代金通貨コード＝拡張投信注文マネージャ.get概算受渡代金通貨コード( 投信注文単位 )
                l_orMutualRuitoOrderExecutionRefUnit.estimatedPriceCurrencyCode =
                    l_orderManager.getEstimateDeliveryAmountCurrencyCode(l_mutualFundOrderUnit);

                //外貨MMFフラグ＝投信注文単位.get投信タイプ()が"外貨MMF"の場合、true
                //それ以外の場合、false
                MutualFundTypeEnum l_mutualFundTypeEnum =
                    ((MutualFundOrderUnitRow)l_mutualFundOrderUnit.getDataSourceObject()).getFundType();
                if (MutualFundTypeEnum.FOREIGN_MMF.equals(l_mutualFundTypeEnum))
                {
                    l_orMutualRuitoOrderExecutionRefUnit.frgnMmfFlag = true;
                }
                else
                {
                    l_orMutualRuitoOrderExecutionRefUnit.frgnMmfFlag = false;
                }
            }

            // Step 1.2.13 Type of orderUnit to be processed == RuitoOrderUnit
            if (l_orderUnits[i] instanceof RuitoOrderUnit)
            {
                // Step 1.2.13.1 Acquire ruito product
                l_ruitoOrderUnit = (RuitoOrderUnit) l_orderUnits[i];
                l_ruitoOrderUnitParam = (RuitoOrderUnitParams)
                         l_ruitoOrderUnit.getDataSourceObject();
                l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
                l_ruitoProductManager = (RuitoProductManager) l_tradingModule.getProductManager();
                l_ruitoProduct =
                    (RuitoProduct) l_ruitoProductManager.getProduct(
                        l_ruitoOrderUnitParam.getProductId());

                // Step 1.2.13.2 Property Set
                l_orMutualRuitoOrderExecutionRefUnit.productDiv = WEB3AdminProductDivDef.RUITO;
                l_orMutualRuitoOrderExecutionRefUnit.orderChannel =
                    l_ruitoOrderUnitParam.getOrderChanel();
                l_orMutualRuitoOrderExecutionRefUnit.orderDate =
                    l_ruitoOrderUnitParam.getReceivedDateTime();
                l_orderBizDate =
                    WEB3DateUtility.getDate(l_ruitoOrderUnitParam.getBizDate(), STR_DATE_FORMAT);
                l_orMutualRuitoOrderExecutionRefUnit.orderBizDate = l_orderBizDate;
                l_orMutualRuitoOrderExecutionRefUnit.orderRootDiv =
                    l_ruitoOrderUnitParam.getOrderRootDiv();
                l_orMutualRuitoOrderExecutionRefUnit.productId =
                	WEB3StringTypeUtility.formatNumber(l_ruitoProduct.getProductId());
                l_orMutualRuitoOrderExecutionRefUnit.productName = l_ruitoProduct.getStandardName();
				if(l_ruitoOrderUnitParam.getGpSellDiv() == null)
				{
					if(l_ruitoOrderUnitParam.getQuantityType().equals(QuantityTypeEnum.QUANTITY))
					{
						l_orMutualRuitoOrderExecutionRefUnit.sellDiv =
							WEB3SellDivDef.COUNT_DESIGNATE;
					}
					else if(l_ruitoOrderUnitParam.getQuantityType().equals(QuantityTypeEnum.AMOUNT))
					{
						l_orMutualRuitoOrderExecutionRefUnit.sellDiv =
							WEB3SellDivDef.MONEY_DESIGNATE;
					}
				}
				else
				{
                	l_orMutualRuitoOrderExecutionRefUnit.sellDiv = l_ruitoOrderUnitParam.getGpSellDiv();
				}
                l_orMutualRuitoOrderExecutionRefUnit.deliveryDiv =
                    l_ruitoOrderUnitParam.getPaymentMethod();
                if (l_ruitoOrderUnitParam.trader_id != null)
                {
                    l_trader = l_gentradeFinObjectManager.getTrader(l_lngTraderId);
                    l_orMutualRuitoOrderExecutionRefUnit.traderCode = l_trader.getTraderCode();
                }
                l_orMutualRuitoOrderExecutionRefUnit.sonarTraderCode = 
                	l_ruitoOrderUnitParam.getSonarTraderCode();
            }

            // Step 1.2.11 Add WEB3AdminORMutualRuitoOrderExecutionRefUnit to ArrayList
            l_lisMutualRuitoOrderExecutionRefUnit.add(l_orMutualRuitoOrderExecutionRefUnit);
        }

        // Step 1.3 Create an array of WEB3AdminORMutualRuitoOrderExecutionRefUnit
        l_orMutualRuitoOrderExecutionRefUnits =
            new WEB3AdminORMutualRuitoOrderExecutionRefUnit[l_lisMutualRuitoOrderExecutionRefUnit
                .size()];
        l_orMutualRuitoOrderExecutionRefUnits =
            (
                WEB3AdminORMutualRuitoOrderExecutionRefUnit[]) l_lisMutualRuitoOrderExecutionRefUnit
                    .toArray(
                l_orMutualRuitoOrderExecutionRefUnits);
        log.exiting(STR_METHOD_NAME);
        return l_orMutualRuitoOrderExecutionRefUnits;
    }
}@
