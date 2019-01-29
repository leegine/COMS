head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOrderExecuteCountReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注文約定件数照会サービスImpl(WEB3AdminOrderExecuteCountReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/11 徐大方 (中訊) 仕様変更No.56修正
                   2006/10/25 徐宏偉 (中訊) モデル079
                   2006/10/26 徐宏偉 (中訊)　@ＤＢレイアウト No.025
*/
package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import webbroker3.adminorderexecinquiry.WEB3AdminProcessingObjectInfo;
import webbroker3.adminorderexecinquiry.data.OrderExecutedCountParams;
import webbroker3.adminorderexecinquiry.data.OrderExecutedCountRow;
import webbroker3.adminorderexecinquiry.define.WEB3AdminMonthlySumDivDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminOrderRootDspDivDef;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderDayNumberInfoUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderRootNumberInfoUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORProductMarketNumberInfoUnit;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminOrderExecuteCountReferenceService;
import webbroker3.bd.data.BondBranchConditionDao;
import webbroker3.bd.data.BondBranchConditionRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者注文約定件数照会サービスImpl)<BR>
 * <BR>
 * 管理者注文約定件数照会サービス実装クラス<BR>
 * <BR>
 * WEB3AdminOrderExecuteCountReferenceServiceImpl<BR>
 * <BR>
 * @@author Rajesh Sharma
 * @@version 1.0
 */
public class WEB3AdminOrderExecuteCountReferenceServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminOrderExecuteCountReferenceService
{
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOrderExecuteCountReferenceServiceImpl.class);

    /**
     * @@roseuid 4212FCE20279
     */
    public WEB3AdminOrderExecuteCountReferenceServiceImpl()
    {
    }

    /**
     * 注文約定件数照会処理を行う。
     *
     * リクエストデータの型により、
     * 以下のメソッドを呼び分ける。
     *
     * ○管理者・注文約定件数照会入力リクエストの場合
     * 　@this.get入力画面()をコールする。
     *
     * ○管理者・注文約定件数照会リクエストの場合
     * 　@this.get件数照会画面()をコールする。
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * Execute WEB3AdminOrderExecuteCountReferenceService process<BR>
     * <BR>
     * Call one of the following methods based on the type of the argument,
     * l_request.<<BR>
     * <BR>
     * ○If WEB3AdminORExecutionNumberInputRequest<BR>
     * 　@Call this.getInputScreen()<BR>
     * <BR>
     * ○If WEB3AdminORExecutionNumberReferenceRequest<BR>
     * 　@Call this.getReferenceScreen()<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 419ACFE603D9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;

        try
        {
            /*
             * Check for the condition input parameter if it is instance of InputRequest then call
             * getInputScreen() method, else if the instance of ReferenceRequest then call
             * getReferenceScreen() or else throw the exception.
             */
            if (l_request instanceof WEB3AdminORExecutionNumberInputRequest)
            {
                l_response =
                    this.getInputScreen((WEB3AdminORExecutionNumberInputRequest) l_request);
            } else if (l_request instanceof WEB3AdminORExecutionNumberReferenceRequest)
            {
                l_response =
                    this.getReferenceScreen((WEB3AdminORExecutionNumberReferenceRequest) l_request);
            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT リクエスト NOT 注文約定件数照会リクエスト");
            }
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
            log.debug(l_exce.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_exce);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exce.toString(),
                l_exce);
        } catch (NotFoundException l_exce)
        {
            log.debug(l_exce.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_exce);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exce.toString(),
                l_exce);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * <BR>
     * 注文約定件数照会入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者注文約定件数照会サービス)get入力画面」<BR>
     * 参照<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     * Execute WEB3AdminOrderExecuteCountReferenceService input screen process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: order execution count inquiry<BR>
     * service)getInputScreen"<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者注文約定件数照会入力リクエストオブジェクト<BR>
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminORExecutionNumberInputRequest object<BR>
     * <BR>
     * @@return WEB3AdminORExecutionNumberInputResponse
     * @@throws NotFoundException NotFoundException
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 419ACFE4005E
     */
    protected WEB3AdminORExecutionNumberInputResponse
        getInputScreen(WEB3AdminORExecutionNumberInputRequest l_request)
        throws NotFoundException, WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminORExecutionNumberInputRequest)";
        log.entering(STR_METHOD_NAME);

        final String UNIT_VALUE = "1";
        final String DATE_PATTERN = "yyyyMM";
        final int l_intMaxMonth = 12;
        WEB3Administrator l_administrator = null;
        Institution l_institution = null;
        WEB3AdminORExecutionNumberInputResponse l_response = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
        Branch l_branch = null;
        BranchRow l_branchRow = null;
        BranchParams l_branchParams = null;
        ArrayList l_lisOrderBizDate = null;
        Date l_datBizDate = null;
        String[] l_strBizMonths = null;
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;

        // Step 1.1 Acquire WEB3Administrator instance from loginInfo
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step 1.2 Check validateAuthority
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ORDER_COUNT_MANAGEMENT, false);

        // Step 1.3 Acquire institutionCode
        l_strInstitutionCode = l_administrator.getInstitutionCode();

        // Step 1.4 Acquire branchCode
        l_strBranchCode = l_administrator.getBranchCode();

        // Step 1.5 To get the branch Object
        WEB3GentradeAccountManager l_web3GentradeAccManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        l_institution = l_web3GentradeAccManager.getInstitution(l_strInstitutionCode);
        l_branch = l_web3GentradeAccManager.getBranch(l_institution, l_strBranchCode);
        l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        l_branchParams = new BranchParams(l_branchRow);

        // Step 1.6 Acquire bizDate
        l_tradingSystem = l_finApp.getTradingSystem();
        l_datBizDate = l_tradingSystem.getBizDate();

        // Step 1.7 Acquire ArrayList to store the 12 months dates
        l_lisOrderBizDate = new ArrayList();

        // Step 1.8 Add year and month to ArrayList
        l_lisOrderBizDate.add(WEB3DateUtility.formatDate(l_datBizDate, DATE_PATTERN));
        Calendar l_currentDate = Calendar.getInstance();

        // Step 1.9 Loop process untill years and months of the past 12 months are set
        for (int i = 1; i <= l_intMaxMonth; i++)
        {
            // Step 1.9.1 The month of bizDate is subtracted by one
            l_currentDate.setTime(l_datBizDate);
            l_currentDate.add(Calendar.MONTH, (-i));

            // Step 1.9.2 Add the subtracted year and month to ArrayList
            l_lisOrderBizDate.add(
                WEB3DateUtility.formatDate(l_currentDate.getTime(), DATE_PATTERN));
        }

        // Step 1.10 Create an array of l_strBizMonth
        l_strBizMonths = new String[l_lisOrderBizDate.size()];
        l_strBizMonths = (String[])l_lisOrderBizDate.toArray(l_strBizMonths);

        // Step 1.11 債券部店別条件テーブルの検索
        BondBranchConditionRow l_bondBranchConditionRow = null;
        try
        {
            l_bondBranchConditionRow = BondBranchConditionDao.findRowByPk(
                l_branch.getBranchId());
        }
        catch (DataFindException l_dfex)
        {
        	String l_strMsg = "Data not found branchId = "
        			+ l_branch.getBranchId();
        	log.info(l_strMsg);
        }
        catch (DataQueryException l_dqex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dqex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dnex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dnex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }

        // Step 1.12 Create response data
        l_response = (WEB3AdminORExecutionNumberInputResponse)l_request.createResponse();

        // Step 1.13 Property Set
        l_response.searchYmList = l_strBizMonths;

        /* If margin_sys_div or margin_gen_div is equal to def.ENFORCEMENT then margin flag is true
         * else false
         */
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_sys_div)
            || WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_gen_div))
        {
            l_response.marginFlag = true;
        }
        else
        {
            l_response.marginFlag = false;
        }
        if (UNIT_VALUE.equals(l_branchParams.mstk_div))
        {
            l_response.miniFlag = true;
        }
        if (UNIT_VALUE.equals(l_branchParams.fstk_div))
        {
            l_response.foreignFlag = true;
        }
        if (UNIT_VALUE.equals(l_branchParams.option_div))
        {
            l_response.optionFlag = true;
        }
        if (UNIT_VALUE.equals(l_branchParams.future_div))
        {
            l_response.futureFlag = true;
        }
        if (UNIT_VALUE.equals(l_branchParams.mf_div))
        {
            l_response.mutualFlag = true;
        }
        if (UNIT_VALUE.equals(l_branchParams.ruito_div))
        {
            l_response.ruitoFlag = true;
        }
        if (l_bondBranchConditionRow != null)
        {
            String l_strEnforceDiv = l_bondBranchConditionRow.getEnforceDiv();
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strEnforceDiv))
            {
                l_response.bondFlag = true;
            }
            else
            {
                l_response.bondFlag = false;
            }
        }
        else
        {
            l_response.bondFlag = false;
        }

        // Step 1.14
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get照会画面)<BR>
     * <BR>
     * 注文約定件数照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者注文約定件数照会サービス)get件数照会画面」<BR>
     * 参照<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getReferenceScreen<BR>
     * <BR>
     * Execute WEB3AdminOrderExecuteCountReferenceService process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: order execution count inquiry
     * service)getCountReferenceScreen"<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者注文約定件数照会リクエストオブジェクト<BR>
     * <BR>
     * ---------<English>----------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminORExecutionNumberReferenceRequest object<BR>
     * <BR>
     * @@return WEB3AdminORExecutionNumberReferenceResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 419ACFE4035C
     */
    protected WEB3AdminORExecutionNumberReferenceResponse
        getReferenceScreen(WEB3AdminORExecutionNumberReferenceRequest l_request)
        throws WEB3BaseException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME =
            "getReferenceScreen(WEB3AdminORExecutionNumberReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminORExecutionNumberReferenceResponse l_response = null;

        // Step 1.1 Check l_request
        l_request.validate();

        // Step 1.2 Acquire WEB3Administrator instance from loginInfo
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //Step 1.3 Check validateAuthority
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ORDER_COUNT_MANAGEMENT, false);

        // Step 1.4 Acquire institutionCode
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //Step 1.5 Create special l_strQueryCond
        String l_strCreateQueryString =
            createQueryString(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.summryDiv,
                null,
                l_request.dailySumYm,
                l_request.monthlySumDiv,
                l_request.sumProductTypeList);

        // Step 1.6 Create special l_strQueryCond
        String[] l_strCreateQueryDataContainer =
            createQueryDataContainer(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.summryDiv,
                null,
                l_request.dailySumYm,
                l_request.monthlySumDiv,
                l_request.sumProductTypeList);

        // Step 1.7 Create sortCond
        String l_strCreateSortCond = createSortCond();

        // Step 1.8 Acquire orderCount data
        List l_lisOrderCount =
            getOrderCountList(
                l_strCreateQueryString,
                l_strCreateQueryDataContainer,
                l_strCreateSortCond);

        // If null is returned from getOrderCountList then execption is thorwn
        if (l_lisOrderCount == null)
        {
            String l_strMsg = "No corresponding data";
            log.error(l_strMsg);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //Step 1.9 Create processingObjectInfo
        OrderExecutedCountParams[] l_orderExecutedCountParams = new OrderExecutedCountParams[l_lisOrderCount.size()];
        l_orderExecutedCountParams =
            (OrderExecutedCountParams[]) l_lisOrderCount.toArray(l_orderExecutedCountParams);

        //String l_strMarketDspDiv = null;
        WEB3AdminProcessingObjectInfo l_processingObjectInfo =
            createProcessingObjectInfo(
                l_orderExecutedCountParams,
                l_request.orderRootDspDiv,
                l_request.marketDspDiv);

        // Step 1.10  Create ArrayList
        ArrayList l_lisOrderRootNumberInfoUnit = new ArrayList();

        /* Step 1.11 Loop process for as many times as the number of the elements of
         * processingObjectInfo.orderBizDateList
         */
        int l_intBizDateListSize = l_processingObjectInfo.orderBizDateList.length;
        for (int j = 0; j < l_intBizDateListSize; j++)
        {

            String l_strOrderBizDate = null;
            l_strOrderBizDate = l_processingObjectInfo.orderBizDateList[j];

            // Step 1.11.1 Create l_strQueryCond
            l_strCreateQueryString =
                createQueryString(
                    l_strInstitutionCode,
                    l_request.branchCode,
                    l_request.summryDiv,
                    l_strOrderBizDate,
                    null,
                    null,
                    l_request.sumProductTypeList);

            // Step 1.11.2 Create l_strQueryCondDataContainer
            l_strCreateQueryDataContainer =
                createQueryDataContainer(
                    l_strInstitutionCode,
                    l_request.branchCode,
                    l_request.summryDiv,
                    l_strOrderBizDate,
                    null,
                    null,
                    l_request.sumProductTypeList);

            // Step 1.11.3 Create Sort Key condition
            l_strCreateSortCond = createSortCond();

            // Step 1.11.4 Acquire orderCount data
            List l_lisGetOrderCount =
                getOrderCountList(
                    l_strCreateQueryString,
                    l_strCreateQueryDataContainer,
                    l_strCreateSortCond);
            OrderExecutedCountParams[] l_orderList =
                new OrderExecutedCountParams[l_lisGetOrderCount.size()];
            l_orderList = (OrderExecutedCountParams[]) l_lisGetOrderCount.toArray(l_orderList);

            // Step 1.11.5 If orderRootDspDiv == Def.DETAIL
            WEB3AdminOROrderRootNumberInfoUnit[] l_adminOROrderRootNumberInfoUnits = null;
            if (WEB3AdminOrderRootDspDivDef.DETAIL.equals(l_request.orderRootDspDiv))
            {
                // Step 1.11.5.1 Create orderRootCountInfoList
                l_adminOROrderRootNumberInfoUnits =
                    createOrderRootCountInfoList(
                        l_orderList,
                        l_processingObjectInfo,
                        l_request.sumProductTypeList);
            }

            // Step 1.11.6 Acquire totalCount
            String[] l_strMarketCodes = l_processingObjectInfo.marketCodeList;
            List l_lisProductMarketTotalCountInfo =
                createProductMarketTotalCountInfoList(
                    l_orderList,
                    l_request.sumProductTypeList,
                    l_strMarketCodes);

            WEB3AdminORProductMarketNumberInfoUnit[] l_adminORProductMarketNumberInfoUnits =
                new WEB3AdminORProductMarketNumberInfoUnit[l_lisProductMarketTotalCountInfo.size()];

            l_adminORProductMarketNumberInfoUnits =
                (WEB3AdminORProductMarketNumberInfoUnit[]) l_lisProductMarketTotalCountInfo.toArray
                    (l_adminORProductMarketNumberInfoUnits);

            // Step 1.11.7 Create WEB3AdminOROrderDayNumberInfoUnit
            WEB3AdminOROrderDayNumberInfoUnit l_adminOROrderDayNumberInfoUnit = new WEB3AdminOROrderDayNumberInfoUnit();

            // Step 1.11.8 Add the obtained values above to the created object
            l_adminOROrderDayNumberInfoUnit.orderBizDate = l_strOrderBizDate;
            l_adminOROrderDayNumberInfoUnit.orderRootNumberInfoList =
                l_adminOROrderRootNumberInfoUnits;
            l_adminOROrderDayNumberInfoUnit.productMarketSumNumberInfoList =
                l_adminORProductMarketNumberInfoUnits;

            // Step 1.11.9 Add the OrderDayNumberInfoUnit to the list
            l_lisOrderRootNumberInfoUnit.add(l_adminOROrderDayNumberInfoUnit);
        }

        // Step 1.12 Create a array to store the list values
        WEB3AdminOROrderDayNumberInfoUnit[] l_adminOROrderDayNumberInfoUnits =
            new WEB3AdminOROrderDayNumberInfoUnit[l_lisOrderRootNumberInfoUnit.size()];
        l_adminOROrderDayNumberInfoUnits =
            (WEB3AdminOROrderDayNumberInfoUnit[]) l_lisOrderRootNumberInfoUnit.toArray(
                l_adminOROrderDayNumberInfoUnits);

        // Step 1.13 Create the response object
        l_response = (WEB3AdminORExecutionNumberReferenceResponse) l_request.createResponse();

        // Step 1.14 Set the obtained orderDayNumberInfoUnit array to the response object
        l_response.orderBizDateCountInfoList = l_adminOROrderDayNumberInfoUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件文字列)<BR>
     * <BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）以下の条件を表す検索条件文字列を作成する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード　@かつ<BR>
     * 　@　@レコード区分 = パラメータ.集計区分<BR>
     * <BR>
     * 　@検索条件文字列 = " institution_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and record_div = ? "<BR>
     * <BR>
     * ２）部店条件を検索条件文字列に追加する。<BR>
     * 　@パラメータ.部店コードの要素数分"?"を追加する。 <BR>
     * <BR>
     * 　@検索条件文字列 += " and branch_id in (?, ?,,,) " <BR>
     * <BR>
     * ３）発注日条件<BR>
     * 　@３－１）パラメータ.日別集計対象年月 != nullの場合、<BR>
     * 　@　@以下の条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and biz_date like ? "<BR>
     * <BR>
     * 　@３－２）パラメータ.月別集計対象区分 != nullの場合、<BR>
     * 　@　@以下の条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and biz_date >= ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and biz_date < ? "<BR>
     * <BR>
     * 　@３－３）パラメータ.発注日 != nullの場合、<BR>
     * 　@　@以下の条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and biz_date = ? "<BR>
     * <BR>
     * ４）パラメータ.商品区分一覧 != nullの場合、<BR>
     * 　@以下のの条件を検索条件文字列に追加する。<BR>
     * 　@※商品区分一覧の要素数分"?"を追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and product_div in (?, ?,,,)"<BR>
     * <BR>
     * ５）作成した検索条件文字列を返却する。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * createQueryString<BR>
     * <BR>
     * Create l_strQueryCond<BR>
     * <BR>
     * 1)Create l_strQueryCond to show the following conditions<BR>
     * <BR>
     * 　@[Condition]<BR>
     * 　@　@institution_code = l_strInstitutionCode and<BR>
     * 　@　@record_div = l_summryDiv<BR>
     * <BR>
     * 　@l_strQueryCond = " institution_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and record_div = ? "<BR>
     * <BR>
     * 2)Add branch conditions to l_strQueryCond<BR>
     * 　@Add "?" as many as elements of l_branchCode<BR>
     * <BR>
     * 　@l_strQueryCond += " and branch_id in (?, ?,,,) " <BR>
     * <BR>
     * 3)Order date conditions<BR>
     * 　@3-1)If l_dailySumYm != null<BR>
     * 　@　@Add the following conditions to l_strQueryCond<BR>
     * <BR>
     * 　@l_strQueryCond += "and biz_date like ? "<BR>
     * <BR>
     * 　@3-2)If l_monthlySumDiv != null<BR>
     * 　@　@Add the following conditions to l_strQueryCond<BR>
     * <BR>
     * 　@　@l_strQueryCond += "and biz_date >= ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and biz_date < ? "<BR>
     * <BR> 3-3)If l_orderBizDate != null<BR>
     * 　@　@Add the following conditions to l_strQueryCond<BR>
     * <BR>
     * 　@　@l_strQueryCond += "and biz_date = ? "<BR>
     * <BR>
     * 4)If l_productTypeList != null<BR>
     * 　@Add the following conditions to l_strQueryCond<BR>
     * 　@※Add "?" as many as elements of l_productTypeList<BR>
     * <BR>
     * 　@l_strQueryCond += "and product_div in (?, ?,,,)"<BR>
     * <BR>
     * 5)Return the created l_strQueryCond<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * <BR>
     * 証券会社コード<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_branchCode - (部店コード)<BR>
     * <BR>
     * 部店コードの配列<BR>
     * <BR>
     * l_branchCode<BR>
     * <BR>
     * @@param l_summryDiv - (集計区分)<BR>
     * <BR>
     * 集計区分<BR>
     * <BR>
     * 0：　@日別<BR>
     * 1：　@月別<BR>
     * <BR>
     * ---<English>-------<BR>
     * <BR>
     * l_summryDiv<BR>
     * <BR>
     * 0: Def.日別<BR>
     * 1: Def.月別<BR>
     * <BR>
     * @@param l_orderBizDate - (発注日)<BR>
     * <BR>
     * 発注日<BR>
     * <BR>
     * l_orderBizDate<BR>
     * <BR>
     * @@param l_dailySumYm - l_dailySumYm
     * (日別集計対象年月)<BR>
     * <BR>
     * 日別集計対象年月<BR>
     * <BR>
     * l_dailySumYm<BR>
     * <BR>
     * @@param l_monthlySumDiv - (月別集計対象区分)<BR>
     * <BR>
     * 月別集計対象区分<BR>
     * <BR>
     * 0：　@過去3ヶ月<BR>
     * 1：　@過去6ヶ月<BR>
     * 2：　@過去12ヶ月<BR>
     * <BR>
     * ---<English>------<BR>
     * <BR>
     * l_monthlySumDiv<BR>
     * <BR>
     * 0：　@過去3ヶ月<BR>
     * 1：　@過去6ヶ月<BR>
     * 2：　@過去12ヶ月<BR>
     * <BR>
     * @@param l_productTypeList - (商品区分一覧)<BR>
     * <BR>
     * 商品区分一覧<BR>
     * ※任意の以下の値の配列<BR>
     * <BR>
     * 0：　@現物株式<BR>
     * 1：　@信用取引<BR>
     * 2：　@株式ミニ投資<BR>
     * 3：　@オプション<BR>
     * 4：　@先物<BR>
     * 5：　@投信<BR>
     * 6：　@中国F<BR>
     * 7：　@MMF<BR>
     * 8：　@外国株式<BR>
     * 9：　@債券<BR>
     * <BR>
     * ---<English>------<BR>
     * <BR>
     * l_productTypeList<BR>
     * ※An array of the following arbitrary values<BR>
     * <BR>
     * 0：　@現物株式<BR>
     * 1：　@信用取引<BR>
     * 2：　@株式ミニ投資<BR>
     * 3：　@オプション<BR>
     * 4：　@先物<BR>
     * 5：　@投信<BR>
     * 6：　@中国F<BR>
     * 7：　@MMF<BR>
     * 8：　@外国株式<BR>
     * 9：　@債券<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 419B2E38034C
     */
    protected String createQueryString(
        String l_strInstitutionCode,
        String[] l_branchCode,
        String l_summryDiv,
        String l_orderBizDate,
        String l_dailySumYm,
        String l_monthlySumDiv,
        String[] l_productTypeList)
    {
        final String STR_METHOD_NAME =
            "createQueryString(String, String[], String, String, String, String[])";
        log.entering(STR_METHOD_NAME);

        int l_intArraySize = 0;
        int l_intBuffLength = 0;
        StringBuffer l_sbBuffer = new StringBuffer();

        // Step 1.1 Create Query with following conditions
        l_sbBuffer.append("institution_code = ? and record_div = ?");

        // Step 1.2 Add branch conditions
        l_intArraySize = l_branchCode.length;
        l_sbBuffer.append(" and branch_code in (");
        for (int i = 0; i < l_intArraySize; i++)
        {
            l_sbBuffer.append("?, ");
        }
        l_intBuffLength = l_sbBuffer.length();

        //To remove the last two charachters ", " which is added at last in the for loop
        l_sbBuffer.delete(l_intBuffLength - 2, l_intBuffLength);
        l_sbBuffer.append(")");

        // Step 1.3 orderDate condition
        if (l_dailySumYm != null)
        {
            // Step 1.3.1 Add biz_date like conditions
            l_sbBuffer.append(" and biz_date like ?");
        }

        // Step 1.3.2 Add and biz_date >= ? and biz_date < conditions
        if (l_monthlySumDiv != null)
        {
            l_sbBuffer.append(" and biz_date >= ? and biz_date < ?");
        }

        // Step 1.3.3 Add biz_date conditions
        if (l_orderBizDate != null)
        {
            l_sbBuffer.append(" and biz_date = ?");
        }

        // Step 1.4 Checking l_productTypeList != null
        l_intArraySize = l_productTypeList.length;
        l_sbBuffer.append(" and product_div in (");
        for (int i = 0; i < l_intArraySize; i++)
        {
            l_sbBuffer.append("?, ");
        }
        l_intBuffLength = l_sbBuffer.length();
        l_sbBuffer.delete(l_intBuffLength - 2, l_intBuffLength);
        l_sbBuffer.append(")");

        // Step 1.5 Returning the query condition
        log.exiting(STR_METHOD_NAME);
        return l_sbBuffer.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * <BR>
     * 検索条件データコンテナを作成する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）以下の値を上から順に生成したArrayListに<BR>
     * 　@　@セットする。<BR>
     * 　@　@・パラメータ.証券会社コード<BR>
     * 　@　@・パラメータ.集計区分<BR>
     * <BR>
     * ３）パラメータ.部店コードの全要素をArrayListに追加する。<BR>
     * <BR>
     * ４）発注日条件<BR>
     * 　@４－１）パラメータ.日別集計対象年月 != nullの場合、<BR>
     * 　@　@パラメータ.日別集計対象年月 + "%"をArrayListに追加する。<BR>
     * <BR>
     * 　@４－２）パラメータ.月別集計対象区分 != nullの場合、<BR>
     * 　@　@パラメータ.月別集計対象区分により、セットする値を分岐する。<BR>
     * <BR>
     * 　@　@①@下限のセット<BR>
     * 　@　@　@["0：過去3ヶ月"の場合]<BR>
     * 　@　@　@　@業務日付(*1)の3ヶ月前の年月(YYYYMM)をセット。<BR>
     * <BR>
     * 　@　@　@["1：過去6ヶ月"の場合]<BR>
     * 　@　@　@　@業務日付(*1)の6ヶ月前の年月(YYYYMM)をセット。<BR>
     * <BR>
     * 　@　@　@["2：過去12ヶ月"の場合]<BR>
     * 　@　@　@　@業務日付(*1)の12ヶ月前の年月(YYYYMM)をセット。<BR>
     * <BR>
     * 　@　@②上限のセット<BR>
     * 　@　@　@・業務日付(*1)の年月(YYYYMM)をセット。<BR>
     * <BR>
     * 　@4-3)パラメータ.発注日 != nullの場合、<BR>
     * 　@　@パラメータ.発注日をArrayListに追加する。<BR>
     * <BR>
     * 5)パラメータ.商品区分一覧 != nullの場合、<BR>
     * 　@パラメータ.商品区分一覧の要素全てをArrayListに追加する。<BR>
     * <BR>
     * 6)作成したArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * (*1)業務日付<BR>
     * 　@TradingSystem.getBizDate()にて取得する。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * createQueryDataContainer<BR>
     * <BR>
     * create queryDataContainer<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)Set the following values sequentially into the created ArrayList<BR>
     * 　@　@・l_strInstitutionCode<BR>
     * 　@　@・l_summryDiv<BR>
     * <BR>
     * 3)Add all elements of l_branchCode to ArrayList<BR>
     * <BR>
     * 4)order date conditions<BR>
     * 　@4-1)If l_dailySumYm != null<BR>
     * 　@　@Add l_dailySumYm + "%" to ArrayList<BR>
     * <BR>
     * 　@4-2)If l_monthlySumDiv != null<BR>
     * 　@　@Set a value based on l_monthlySumDiv<BR>
     * <BR>
     * 　@　@①@Set minimum<BR>
     * 　@　@　@If [0: Def.PAST_THREE_MONTH]<BR>
     * 　@　@　@　@Set year and month 3 months ago(YYYYMM) of bizDate(*1)<BR>
     * <BR>
     * 　@　@　@If [1: Def.PAST_SIX_MONTH]<BR>
     * 　@　@　@　@Set year and month 6 months ago(YYYYMM) of bizDate(*1)<BR>
     * <BR>
     * 　@　@　@If [2: Def.PAST_TWELVE_MONTH]<BR>
     * 　@　@　@　@Set year and month 12 months ago(YYYYMM) of bizDate(*1)<BR>
     * <BR>
     * 　@　@②Set maximum<BR>
     * 　@　@　@・Set year and month(YYYYMM) of bizDate(*1)<BR>
     * <BR>
     * 　@4-3)If l_orderBizDate != null<BR>
     * 　@　@Add l_orderBizDate to ArrayList<BR>
     * <BR>
     * 5)If l_productTypeList != null<BR>
     * 　@Add all elements of l_productTypeList to ArrayList<BR>
     * <BR>
     * 6)Return return values of the created ArrayList.toArray()<BR>
     * <BR>
     * (*1)bizDate<BR>
     * 　@Acquired at TradingSystem.getBizDate()<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * <BR>
     * 証券会社コード<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_branchCode - (部店コード)<BR>
     * <BR>
     * 部店コードの配列<BR>
     * <BR>
     * l_branchCode<BR>
     * <BR>
     * @@param l_summryDiv - (集計区分)<BR>
     * <BR>
     * 集計区分<BR>
     * <BR>
     * 0：　@日別<BR>
     * 1：　@月別<BR>
     * <BR>
     * -----<English>---------<BR>
     * <BR>
     * l_summryDiv<BR>
     * <BR>
     * 0: Def.日別<BR>
     * 1: Def.月別<BR>
     * <BR>
     *
     * @@param l_orderBizDate - (発注日)<BR>
     * <BR>
     * 発注日<BR>
     * <BR>
     * l_orderBizDate<BR>
     * <BR>
     *
     * @@param l_dailySumYm - (日別集計対象年月)<BR>
     * <BR>
     * 日別集計対象年月<BR>
     * <BR>
     * l_dailySumYm<BR>
     * <BR>
     * @@param l_monthlySumDiv - (月別集計対象区分)<BR>
     * <BR>
     * 月別集計対象区分<BR>
     * <BR>
     * 0：　@過去3ヶ月<BR>
     * 1：　@過去6ヶ月<BR>
     * 2：　@過去12ヶ月<BR>
     * <BR>
     * ----<English>--------<BR>
     * <BR>
     * l_monthlySumDiv<BR>
     * <BR>
     * 0: Def.過去3ヶ月<BR>
     * 1: Def.過去6ヶ月<BR>
     * 2: Def.過去12ヶ月<BR>
     * <BR>
     * @@param l_productTypeList - (商品区分一覧)<BR>
     * <BR>
     * 商品区分一覧<BR>
     * ※任意の以下の値の配列<BR>
     * <BR>
     * 0：　@現物株式<BR>
     * 1：　@信用取引<BR>
     * 2：　@株式ミニ投資<BR>
     * 3：　@オプション<BR>
     * 4：　@先物<BR>
     * 5：　@投信<BR>
     * 6：　@中国F<BR>
     * 7：　@MMF<BR>
     * 8：　@外国株式<BR>
     * 9：　@債券<BR>
     * <BR>
     * ---<English>------------<BR>
     * <BR>
     * l_productTypeList<BR>
     * ※An Array of the following arbitrary values<BR>
     * <BR>
     * 0：　@現物株式<BR>
     * 1：　@信用取引<BR>
     * 2：　@株式ミニ投資<BR>
     * 3：　@オプション<BR>
     * 4：　@先物<BR>
     * 5：　@投信<BR>
     * 6：　@中国F<BR>
     * 7：　@MMF<BR>
     * 8：　@外国株式<BR>
     * 9：　@債券<BR>
     * <BR>
     * @@return String[]
     * @@roseuid 419B2E38035C
     */
    protected String[] createQueryDataContainer(
        String l_strInstitutionCode,
        String[] l_branchCode,
        String l_summryDiv,
        String l_orderBizDate,
        String l_dailySumYm,
        String l_monthlySumDiv,
        String[] l_productTypeList)
    {
        final String STR_METHOD_NAME =
            "createQueryDataContainer("
                + "String, String[], String, String, String, String, String[])";
        log.entering(STR_METHOD_NAME);
        final String DATE_PATTERN = "yyyyMM";
        final int INT_LAST_THREE = -03;
        final int INT_LAST_SIX = -06;
        final int INT_LAST_TWELVE = -12;
        ArrayList l_lisDataContainer = null;
        Date l_datBizDate = null;
        Calendar l_calCurrentDate = null;
        String[] l_strDataContainer = null;
        int l_intArraySize = 0;
        TradingSystem l_tradingSystem = null;
        FinApp l_finApp = null;

        // Step 1. Create ArrayList
        l_lisDataContainer = new ArrayList();

        // Step 2. Add InstitutionCode and SummaryDiv value
        l_lisDataContainer.add(l_strInstitutionCode);
        l_lisDataContainer.add(l_summryDiv);

        // Step 3. Add all elements of l_branchCode
        l_intArraySize = l_branchCode.length;
        for (int i = 0; i < l_intArraySize; i++)
        {
            l_lisDataContainer.add(l_branchCode[i]);
        }

        // Step 4. Order date condition for not null
        if (l_dailySumYm != null)
        {
            l_lisDataContainer.add(l_dailySumYm + "%");
        }

        // MonthlySumDiv condition for not null
        if (l_monthlySumDiv != null)
        {
            // Step 4.1 Set Minimum date
            l_finApp = (FinApp) Services.getService(FinApp.class);
            l_tradingSystem = l_finApp.getTradingSystem();
            l_datBizDate = l_tradingSystem.getBizDate();
            l_calCurrentDate = Calendar.getInstance();
            l_calCurrentDate.setTime(l_datBizDate);

            if (WEB3AdminMonthlySumDivDef.PAST_THREE_MONTH.equals(l_monthlySumDiv))
            {
                l_calCurrentDate.add(Calendar.MONTH, INT_LAST_THREE);
                l_lisDataContainer.add(
                    WEB3DateUtility.formatDate(l_calCurrentDate.getTime(), DATE_PATTERN));
            } else if (WEB3AdminMonthlySumDivDef.PAST_SIX_MONTH.equals(l_monthlySumDiv))
            {
                l_calCurrentDate.add(Calendar.MONTH, INT_LAST_SIX);
                l_lisDataContainer.add(
                    WEB3DateUtility.formatDate(l_calCurrentDate.getTime(), DATE_PATTERN));
            } else
            {
                l_calCurrentDate.add(Calendar.MONTH, INT_LAST_TWELVE);
                l_lisDataContainer.add(
                    WEB3DateUtility.formatDate(l_calCurrentDate.getTime(), DATE_PATTERN));
            }

            // Step 4.2 Set Maximum date
            l_lisDataContainer.add(WEB3DateUtility.formatDate(l_datBizDate, DATE_PATTERN));
        }

        //If orderBizdate is not null then add to the arraylist
        if (l_orderBizDate != null)
        {
            l_lisDataContainer.add(l_orderBizDate);
        }

        l_intArraySize = l_productTypeList.length;
        for (int i = 0; i < l_intArraySize; i++)
        {
            l_lisDataContainer.add(l_productTypeList[i]);
        }
        l_strDataContainer = new String[l_lisDataContainer.size()];
        l_lisDataContainer.toArray(l_strDataContainer);

        log.exiting(STR_METHOD_NAME);
        return l_strDataContainer;
    }

    /**
     * (createソート条件)<BR>
     * <BR>
     * ソート条件を作成する。<BR>
     * <BR>
     * １）テーブル列物理名より、以下のソート条件を表すソート条件文字列を作成する。<BR>
     *
     * <BR>
     * 　@①@注文件数テーブル.発注日　@昇順<BR>
     * 　@②注文件数テーブル.注文経路区分　@昇順<BR>
     * 　@③注文件数テーブル.商品　@昇順<BR>
     * 　@④注文件数テーブル.市場コード　@昇順<BR>
     * <BR>
     * ２）作成したソート条件文字列を返却する。<BR>
     * <BR>
     * ----<English>----------------<BR>
     * <BR>
     * createSortCond<BR>
     * <BR>
     * Create sortCond<BR>
     * <BR>
     * 1)Create sortCond showing the following sortCond from field names in the
     * table<BR>
     * <BR>
     * 　@①@order_executed_count.biz_date　@in ascending order<BR>
     * 　@②order_executed_count.order_root_div　@in ascending order<BR>
     * 　@③order_executed_count.product_div　@in ascending order<BR>
     * 　@④order_executed_count.market_code　@in ascending order<BR>
     * <BR>
     * 2)Return the created sortCond<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 419B2E380361
     */
    protected String createSortCond()
    {
        final String STR_METHOD_NAME = "createSortCond()";
        log.entering(STR_METHOD_NAME);
        StringBuffer l_sbBuffer = new StringBuffer();
        String l_strSortCond = null;

        // Step 1. Create sortCond
        l_sbBuffer.append("biz_date asc, ");
        l_sbBuffer.append("order_root_div asc, ");
        l_sbBuffer.append("product_div asc, ");
        l_sbBuffer.append("market_code asc");
        l_strSortCond = l_sbBuffer.toString();

        // Step 2. Return the created sortCond
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }

    /**
     * (get注文件数一覧)<BR>
     * <BR>
     * 引数の条件に該当する注文件数Paramsの一覧を返却する。<BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@注文件数Row.TYPE<BR>
     * 　@　@arg1：　@パラメータ.検索条件文字列<BR>
     * 　@　@arg2：　@パラメータ.ソート条件<BR>
     * 　@　@arg3：　@null<BR>
     * 　@　@arg4：　@パラメータ.検索条件データコンテナ<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ３）２）の検索結果を返却する。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getOrderCountList<BR>
     * <BR>
     * Return the list of orderCountList corresponding to the conditions of
     * arguments<BR>
     * <BR>
     * 1)Call QueryProcessor.doFindAllQuery() method<BR>
     * <BR>
     * 　@[Parameter set into doFindAllQuery()]<BR>
     * 　@　@arg0: orderExecutedCountRow.TYPE<BR>
     * 　@　@arg1: l_strQueryCond<BR>
     * 　@　@arg2: l_strSortCond<BR>
     * 　@　@arg3: null<BR>
     * 　@　@arg4: l_strQueryCondDataContainer<BR>
     * <BR>
     * 　@Return null if there is no search result<BR>
     * <BR>
     * 3)Return the search result of 2)<BR>
     * <BR>
     * @@param l_strQueryCond - (検索条件文字列)<BR>
     * <BR>
     * 検索条件文字列<BR>
     * <BR>
     * l_strQueryCond<BR>
     * <BR>
     * @@param l_strQueryCondDataContainer - (検索条件データコンテナ)<BR>
     * <BR>
     * 検索条件データコンテナ<BR>
     * <BR>
     * l_strQueryCondDataContainer<BR>
     * <BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * <BR>
     * ソート条件<BR>
     * <BR>
     * l_strSortCond<BR>
     * <BR>
     * @@return java.util.List
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 419AD10702FE
     */
    protected List getOrderCountList(
        String l_strQueryCond,
        String[] l_strQueryCondDataContainer,
        String l_strSortCond)
        throws DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = "getOrderCountList(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        List l_lisSearchResult = null;
        int l_intSearchResultLength = 0;

        l_queryProcessor = Processors.getDefaultProcessor();

        // Step 1Call QueryProcessor.doFindAllQuery() method
        l_lisSearchResult =
            l_queryProcessor.doFindAllQuery(
                OrderExecutedCountRow.TYPE,
                l_strQueryCond,
                l_strSortCond,
                null,
                l_strQueryCondDataContainer);

        // If return searchresult length is greater than zero then return the result
        l_intSearchResultLength = l_lisSearchResult.size();
        if (l_intSearchResultLength > 0)
        {
            log.exiting(STR_METHOD_NAME);
            return l_lisSearchResult;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (create処理対象情報)<BR>
     * <BR>
     * 引数の注文件数データより<BR>
     * 処理対象情報の配列を作成して返却する。<BR>
     * <BR>
     * １）発注日格納リスト生成<BR>
     * 　@TreeMapを生成する。<BR>
     * <BR>
     * ２）注文経路格納リスト生成<BR>
     * 　@パラメータ.注文経路表示区分 == "詳細"の場合、<BR>
     * 　@TreeMapを生成する。<BR>
     * <BR>
     * ３）市場コード格納リスト生成<BR>
     * 　@パラメータ.市場表示区分 == "詳細"の場合、<BR>
     * 　@TreeMapを生成する。<BR>
     * <BR>
     * ４）処理対象情報生成<BR>
     * 　@処理対象情報インスタンスを生成する。<BR>
     * <BR>
     * ５）各データ取得<BR>
     * 　@パラメータ.注文件数一覧の要素数分、<BR>
     * 　@以下の処理を繰り返す。<BR>
     * 　@５－１）発注日格納リスト.put()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[put()にセットするパラメータ]<BR>
     * 　@　@　@key：　@処理対象の要素.発注日<BR>
     * 　@　@　@value：　@処理対象の要素.発注日<BR>
     * <BR>
     * 　@５－２）パラメータ.注文経路表示区分 == "詳細"の場合、<BR>
     * 　@　@注文経路格納リスト.put()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[put()にセットするパラメータ]<BR>
     * 　@　@　@key：　@処理対象の要素.注文経路区分<BR>
     * 　@　@　@value：　@処理対象の要素.注文経路区分<BR>
     * <BR>
     * 　@５－３）パラメータ.市場表示区分 == "詳細"の場合、<BR>
     * 　@　@市場コード格納リスト.put()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[put()にセットするパラメータ]<BR>
     * 　@　@　@key：　@処理対象の要素.市場コード<BR>
     * 　@　@　@value：　@処理対象の要素.市場コード<BR>
     * <BR>
     * ３）プロパティセット<BR>
     * 　@生成した処理対象情報インスタンスに以下のプロパティを<BR>
     * 　@セットする。<BR>
     * <BR>
     * 　@発注日一覧 = 発注日格納リスト.values().toArray()の戻り値<BR>
     * 　@注文経路区分一覧 = 注文経路格納リスト.values().toArray()の戻り値<BR>
     * 　@市場コード一覧 = 市場コード格納リスト.values().toArray()の戻り値<BR>
     * <BR>
     * ４）プロパティセットした処理対象情報インスタンスを返却する。<BR>
     * <BR>
     * -----<English>--------------<BR>
     * <BR>
     * createProcessingObjectInfo<BR>
     * <BR>
     * Create an Object of processingObjectInfo from the argument, l_orderNumberList<BR>
     *
     * <BR>
     * 1)Create a list to store orderBizDate<BR>
     * 　@Create TreeMap<BR>
     * <BR>
     * 2) Create TreeMap<BR>
     * <BR>
     * 3) Create TreeMap<BR>
     * <BR>
     * 4)Create processingObjectInfo<BR>
     * 　@Create an instance of processingObjectInfo<BR>
     * <BR>
     * 5)Acquire each data<BR>
     * 　@Loop the following process for as many times as the number of the elements of
     * l_orderNumberList<BR>
     * 　@5-1)Call orderBizDateList.put() method<BR>
     * <BR>
     * 　@　@[Parameter set into put()]<BR>
     * 　@　@　@key: element to be processed.orderBizDate<BR>
     * 　@　@　@value: element to be processed.orderBizDate<BR>
     * <BR>
     * 　@5-2)If l_orderRootDspDiv == Def.DETAIL,<BR>
     * 　@　@Call orderRootDivList.put() method<BR>
     * <BR>
     * 　@　@[Parameter set into put()]<BR>
     * 　@　@　@key: element to be processed.orderRootDiv<BR>
     * 　@　@　@value: element to be processed.orderRootDiv<BR>
     * <BR>
     * 　@5-3)If l_marketDspDiv == Def.DETAIL,<BR>
     * 　@　@Call marketCodeList.put() method<BR>
     * <BR>
     * 　@　@[Parameter set into put()]<BR>
     * 　@　@　@key: element to be processed.marketCode<BR>
     * 　@　@　@value: element to be processed.marketCode<BR>
     * <BR>
     * 3)PropertySet<BR>
     * 　@Set the following properties into the created processingObjectInfo
     * instance<BR>
     * <BR>
     * 　@orderBizDateList = orderBizDateList.values().return value of toArray()<BR>
     * 　@orderRootDivList = orderRootDivList.values().return value of toArray()<BR>
     * 　@marketCodeList = marketCodeList.values().return value of toArray()<BR>
     * <BR>
     * 4)Return the instance of processingObjectInfo set into 'Property Set'<BR>
     * <BR>
     * @@param l_orderNumberList - (注文件数一覧)<BR>
     * <BR>
     * 注文件数Paramsの配列<BR>
     * <BR>
     * ----<English>--------<BR>
     * <BR>
     * l_orderNumberList<BR>
     * <BR>
     * An array of orderExecutedCountParams<BR>
     * <BR>
     * @@param l_orderRootDspDiv - (注文経路表示区分)<BR>
     * <BR>
     * 注文経路表示区分<BR>
     * <BR>
     * 0：　@詳細<BR>
     * 1：　@合計<BR>
     * <BR>
     * ----<English>--------<BR>
     * <BR>
     * l_orderRootDspDiv<BR>
     * <BR>
     * 0: Def.詳細<BR>
     * 1: Def.合計<BR>
     * <BR>
     * @@param l_marketDspDiv - (市場表示区分)<BR>
     * <BR>
     * 市場表示区分<BR>
     * <BR>
     * 0：　@詳細<BR>
     * 1：　@合計<BR>
     * <BR>
     * ----<English>--------<BR>
     * <BR>
     * l_marketDspDiv<BR>
     * <BR>
     * 0: Def.詳細<BR>
     * 1: Def.合計<BR>
     * <BR>
     *
     * @@return processingObjectInfo
     * @@roseuid 419C41D103A0
     */
    protected WEB3AdminProcessingObjectInfo createProcessingObjectInfo(
        OrderExecutedCountParams[] l_orderNumberList,
        String l_orderRootDspDiv,
        String l_marketDspDiv)
    {
        final String STR_METHOD_NAME =
            "createProcessingObjectInfo(" + "OrderExecutedCountParams[], String, String)";
        log.entering(STR_METHOD_NAME);

        TreeMap l_mapOrderBizDate = null;
        TreeMap l_mapOrderRootDiv = null;
        TreeMap l_mapMarketCode = null;
        WEB3AdminProcessingObjectInfo l_processingObjectInfo = null;
        String[] l_strMapOrders = null;
        int l_intArraySize = 0;

        // Step1. Create a list to Store orderBizDate
        l_mapOrderBizDate = new TreeMap();

        // Step2. Create a list to store orderRoot
        l_mapOrderRootDiv = new TreeMap();

        // Step3. Create a list to store marketCode
        l_mapMarketCode = new TreeMap();

        // Step4. Create processingObjectInfo
        l_processingObjectInfo = new WEB3AdminProcessingObjectInfo();

        // Step5. Acquire each data
        l_intArraySize = l_orderNumberList.length;
        for (int i = 0; i < l_intArraySize; i++)
        {
            // Step5.1 Add the bizDate to the list
            l_mapOrderBizDate.put(l_orderNumberList[i].biz_date, l_orderNumberList[i].biz_date);

            // Step5.2 orderRootDisp is equal to def.DETAIL
            if (WEB3AdminOrderRootDspDivDef.DETAIL.equals(l_orderRootDspDiv))
            {
                l_mapOrderRootDiv.put(
                    l_orderNumberList[i].order_root_div,
                    l_orderNumberList[i].order_root_div);
            }

            // Step5.3 marketDspDiv is equal to def.DETAIL
            if (WEB3AdminOrderRootDspDivDef.DETAIL.equals(l_marketDspDiv))
            {
                String l_strMarketCode;
                if (l_orderNumberList[i].market_code.matches("^[0-9]"))
                {
                    l_strMarketCode = "0" + l_orderNumberList[i].market_code;
                }
                else
                {
                    l_strMarketCode = l_orderNumberList[i].market_code;
                }
                l_mapMarketCode.put(
                    l_strMarketCode,
                    l_orderNumberList[i].market_code);
            }
        }

        // Step6. Set Property
        l_strMapOrders = new String[l_mapOrderBizDate.size()];
        l_processingObjectInfo.orderBizDateList =
            (String[]) (l_mapOrderBizDate.values()).toArray(l_strMapOrders);
        if (WEB3AdminOrderRootDspDivDef.DETAIL.equals(l_orderRootDspDiv))
        {
            l_strMapOrders = new String[l_mapOrderRootDiv.size()];
            l_processingObjectInfo.orderRootDivList =
                (String[]) (l_mapOrderRootDiv.values()).toArray(l_strMapOrders);
        } else
        {
            l_processingObjectInfo.orderRootDivList = null;
        }
        if (WEB3AdminOrderRootDspDivDef.DETAIL.equals(l_marketDspDiv))
        {
            l_strMapOrders = new String[l_mapMarketCode.size()];
            l_processingObjectInfo.marketCodeList =
                (String[]) (l_mapMarketCode.values()).toArray(l_strMapOrders);
        } else
        {
            l_processingObjectInfo.marketCodeList = null;
        }
        log.exiting(STR_METHOD_NAME);
        return l_processingObjectInfo;
    }

    /**s
     * (create注文経路別件数情報一覧)<BR>
     * <BR>
     * 引数の注文経路区分ごとの件数を算出し、<BR>
     * 注文経路別件数情報の配列として返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者注文約定件数照会サービス)create注文経路別件数情報一覧」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>----------<BR>
     * <BR>
     * createOrderRootCountInfoList<BR>
     * <BR>
     * Calculate the number of the count for each orderRootDiv and <BR>
     * return the array of WEB3AdminOROrderRootNumberInfoUnit<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: order execution count inquiry
     * service)createOrderRootCountInfoList"
     * <BR>
     * @@param l_orderNumberList - (注文件数一覧)<BR>
     * <BR>
     * 注文件数Paramsの配列<BR>
     * <BR>
     * ----<English>----------<BR>
     * <BR>
     * l_orderNumberList<BR>
     * <BR>
     * An array of orderExecutedCountParams<BR>
     * <BR>
     * @@param l_processingObjectInfo - l_processingObjectInfo
     * (処理対象情報)<BR>
     * <BR>
     * 処理対象情報<BR>
     * <BR>
     * l_processingObjectInfo<BR>
     * <BR>
     * @@param l_productTypeList - (商品区分一覧)<BR>
     * <BR>
     * 商品区分一覧<BR>
     * ※任意の以下の値の配列<BR>
     * <BR>
     * 0：　@現物株式<BR>
     * 1：　@信用取引<BR>
     * 2：　@株式ミニ投資<BR>
     * 3：　@オプション<BR>
     * 4：　@先物<BR>
     * 5：　@投信<BR>
     * 6：　@中国F<BR>
     * 7：　@MMF<BR>
     * 8：　@外国株式<BR>
     * 9：　@債券<BR>
     * <BR>
     * ----<English>----------<BR>
     * <BR>
     * l_productTypeList<BR>
     * ※An array of the following arbitrary values<BR>
     * <BR>
     * 0: Def.EQUITY<BR>
     * 1: Def.MARGIN<BR>
     * 2: Def.MINI_STOCK<BR>
     * 3: Def.OPTION<BR>
     * 4: Def.FUTURE<BR>
     * 5: Def.MF<BR>
     * 6: Def.MIDIUM_TERM_GOV_FUND<BR>
     * 7: Def.MMF<BR>
     * 8: Def.FOREGIN_STOCK<BR>
     * 9: Def.BOND<BR>
     * <BR>
     * @@return WEB3AdminOROrderRootNumberInfoUnit[]
     * @@roseuid 419C114602C9
     */
    protected WEB3AdminOROrderRootNumberInfoUnit[] createOrderRootCountInfoList(
        OrderExecutedCountParams[] l_orderNumberList,
        WEB3AdminProcessingObjectInfo l_processingObjectInfo,
        String[] l_productTypeList)
    {
        final String STR_METHOD_NAME =
            "createOrderRootCountInfoList("
                + "OrderExecutedCountParams[], ProcessingObjectInfo, String[])";
        log.entering(STR_METHOD_NAME);

        if (l_orderNumberList == null || l_orderNumberList.length == 0)
        {
            return null;
        }

        // Step 1.3
        ArrayList l_lisOrderRootNumberInfoUnits = new ArrayList();

        // Step 1.4
        for (int i = 0; i < l_processingObjectInfo.orderRootDivList.length; i++)
        {
            // Step 1.4.1 Create the array list
            ArrayList l_lisOrderExecuteCountParamsList = new ArrayList();

            // Step 1.4.2
            for (int j = 0; j < l_orderNumberList.length; j++)
            {
                // Step 1.4.2.1
                String l_strProcessOrderRoot = l_processingObjectInfo.orderRootDivList[i];
                String l_strOrderRootDiv = l_orderNumberList[j].order_root_div;

                // if the order root dic is equal to the processing object order root div
                if (l_strProcessOrderRoot.equals(l_strOrderRootDiv))
                {
                    // Step 1.4.2.1.1
                    l_lisOrderExecuteCountParamsList.add(l_orderNumberList[j]);
                } else
                {
                    if (l_lisOrderExecuteCountParamsList.size() != 0)
                    {
                        break;
                    }
                }
            }

            //Step 1.4.4 Create productMarketTotalCount
            OrderExecutedCountParams[] l_orderExecParams = 
                (OrderExecutedCountParams[])l_lisOrderExecuteCountParamsList.toArray(
                    new OrderExecutedCountParams[0]
                    );

            String[] l_strMarketCodes = l_processingObjectInfo.marketCodeList;

            List l_lisProductMarket =
                createProductMarketTotalCountInfoList(
                    l_orderExecParams,
                    l_productTypeList,
                    l_strMarketCodes
                    );

            // Step 1.4.5 Create WEB3AdminOROrderRootNumberInfoUnit instance
            WEB3AdminOROrderRootNumberInfoUnit l_adminOrOrderRootNumber 
                = new WEB3AdminOROrderRootNumberInfoUnit();

            // Step 1.4.6
            l_adminOrOrderRootNumber.orderRootDiv = 
                l_processingObjectInfo.orderRootDivList[i];

            l_adminOrOrderRootNumber.productMarketNumberInfoList = 
                (WEB3AdminORProductMarketNumberInfoUnit[])l_lisProductMarket.toArray(
                    new WEB3AdminORProductMarketNumberInfoUnit[0]
                    );

            // Step 1.4.7 add to orderRootNumber to the orderRootNumber list
            l_lisOrderRootNumberInfoUnits.add(l_adminOrOrderRootNumber);
        }

        WEB3AdminOROrderRootNumberInfoUnit[] l_orderRootNumberInfoUnits =
        (WEB3AdminOROrderRootNumberInfoUnit[])l_lisOrderRootNumberInfoUnits.toArray(
            new WEB3AdminOROrderRootNumberInfoUnit[0]);

        log.exiting(STR_METHOD_NAME);
        return l_orderRootNumberInfoUnits;
    }

    /**
     * (create商品市場別合計件数情報一覧)<BR>
     * <BR>
     * 引数の注文件数Paramsより市場、または商品ごとの<BR>
     * 合計件数を算出し、<BR>
     * 商品市場別件数情報の配列として返却する。<BR>
     * <BR>
     * １）返却値格納リスト生成<BR>
     * 　@ArrayListを生成する。<BR>
     * <BR>
     * ２）集計対象リスト生成<BR>
     * 　@ArrayListを生成し、パラメータ.注文件数Paramsの全要素をコピーする。<BR>
     * <BR>
     * ３）商品市場別合計件数算出<BR>
     * 　@３－１）パラメータ.商品区分一覧の要素数分以下の処理を繰り返す。<BR>
     * 　@　@３－１－１）パラメータ.市場コード一覧の有無により、処理を分岐する。<BR>
     * 　@　@　@※集計した注文/約定件数の初期値は0とする。<BR>
     * <BR>
     * 　@　@　@　@[パラメータ.市場コード一覧 != nullの場合] // 市場詳細表示<BR>
     * 　@　@　@　@　@パラメータ.市場コード一覧の要素数分以下の処理を繰り返す。<BR>
     * <BR>
     * 　@　@　@　@　@　@①@集計対象リスト.size() != 0の場合、集計対象リストの内<BR>
     * 　@　@　@　@　@　@　@処理対象の商品区分、市場コードに該当する要素の<BR>
     * 　@　@　@　@　@　@　@注文件数(買 + 売)、約定件数(買 + 売)をそれぞれ集計する。<BR>
     * 　@　@　@　@　@　@　@※集計完了後、処理対象の商品区分、市場コードに<BR>
     * 　@　@　@　@　@　@　@　@　@該当する要素を集計対象リストから削除する。<BR>
     * <BR>
     * 　@　@　@　@　@　@②商品市場別件数情報インスタンスを生成する。<BR>
     * 　@　@　@　@　@　@③②にて生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@　@　@　@　@　@集計対象商品区分 = 処理対象の商品区分<BR>
     * 　@　@　@　@　@　@　@市場コード = 処理対象の市場区分<BR>
     * 　@　@　@　@　@　@　@注文件数 = 集計した注文件数<BR>
     * 　@　@　@　@　@　@　@約定件数 = 集計した約定件数<BR>
     * <BR>
     * 　@　@　@　@　@　@④返却値格納リストにプロパティセットしたインスタンスを追加する。<BR>
     *
     * <BR>
     * 　@　@　@　@[パラメータ.市場コード一覧 == nullの場合] // 市場合計表示<BR>
     * 　@　@　@　@　@①@集計対象リスト.size() != 0, 集計対象リストの内<BR>
     * 　@　@　@　@　@　@　@処理対象の商品区分に該当する要素の<BR>
     * 　@　@　@　@　@　@　@注文件数(買 + 売)、約定件数(買 + 売)をそれぞれ集計する。<BR>
     * 　@　@　@　@　@　@　@※集計完了後、処理対象の商品区分に<BR>
     * 　@　@　@　@　@　@　@　@　@該当する要素を集計対象リストから削除する。<BR>
     * <BR>
     * 　@　@　@　@　@②商品市場別件数情報インスタンスを生成する。<BR>
     * 　@　@　@　@　@③②にて生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@　@　@　@　@集計対象商品区分 = 処理対象の商品区分<BR>
     * 　@　@　@　@　@　@市場コード = null<BR>
     * 　@　@　@　@　@　@注文件数 = 集計した注文件数<BR>
     * 　@　@　@　@　@　@約定件数 = 集計した約定件数<BR>
     * <BR>
     * 　@　@　@　@　@④返却値格納リストにプロパティセットしたインスタンスを追加する。<BR>
     * <BR>
     * ４）返却値格納リスト.toArray()の戻り値を返却する。<BR>
     * <BR>
     * ------<English>---------------------------<BR>
     * <BR>
     * createProductMarketTotalCountInfoList<BR>
     * <BR>
     * Calculate the totalCount for each market or product from the argument
     * l_orderExecutedCountParams, and<BR>
     * return an array of productMarketTotalCountInfoList<BR>
     * <BR>
     * 1)Create a list to store return values<BR>
     * 　@Create ArrayList<BR>
     * <BR>
     * 2)Create a summary list<BR>
     * 　@Create ArrayList and copy all elements of l_orderExecutedCountParams<BR>
     * <BR>
     * 3)Calculate productMarketTotalCount<BR>
     * 　@3-1)Loop the following process for as many times as the number of the elements
     * of l_productTypeList<BR>
     * 　@　@3-1-1)Go to either of the following processes according to the values of
     * l_marketCodeList<BR>
     * 　@　@　@※Let the default orerNumber/executeNumber 0.<BR>
     * <BR>
     * 　@　@　@　@If [l_marketCodeList != null] // showing market details<BR>
     * 　@　@　@　@　@Loop the following process for as many times as the number of the
     * elements of l_marketCodeList<BR>
     * <BR>
     * 　@　@　@　@　@　@①@If summary list.size() != 0, calculate the total of the elements
     * of<BR>
     * orderNumber(buy and sell) and executeNumber(executeCount)<BR>
     * corresponding to productDiv and marketCode in the summary list<BR>
     * ※After the calculation, remove the elements concerned from the list
     * <BR>
     * 　@　@　@　@　@　@②Create productMarketTotalCountInfo instance<BR>
     * 　@　@　@　@　@　@③Set the following properties into the instance created in ②<BR>
     * 　@　@　@　@　@　@　@sumProductType = productDiv to be processed<BR>
     * 　@　@　@　@　@　@　@marketCode = marketCode to be processed<BR>
     * 　@　@　@　@　@　@　@orderNumber = total orderNumber<BR>
     * 　@　@　@　@　@　@　@executeNumber = total executeNumber<BR>
     * <BR>
     * 　@　@　@　@　@　@④Add the instance set into 'Property Set' to a list to store return
     * values<BR>
     * <BR>
     * 　@　@　@　@If [l_marketCodeList == null] // showing market total<BR>
     * 　@　@　@　@　@①@If summary list.size() != 0, calculate the total of the elements
     * of<BR>
     * orderNumber(buy and sell) and executeNumber(executeCount)<BR>
     * corresponding to productDiv and marketCode in summary list<BR>
     * 　@　@　@　@　@　@　@※Delete the elements corresponding to the productDiv from summary
     * list<BR>
     * after calculation<BR>
     * <BR>
     * 　@　@　@　@　@②Create productMarketTotalCountInfo instance<BR>
     * 　@　@　@　@　@③Set the following properties into the instance created in ②<BR>
     * 　@　@　@　@　@　@sumProductType = productDiv to be processed<BR>
     * 　@　@　@　@　@　@marketCode = null<BR>
     * 　@　@　@　@　@　@orderNumber = total orderNumber<BR>
     * 　@　@　@　@　@　@executeNumber = total executeNumber<BR>
     * <BR>
     * 　@　@　@　@　@④Add the instance set into 'Property Set' into the list to store
     * return values<BR>
     * <BR>
     * 4)Return the list to store return values.return value of toArray()<BR>
     * <BR>
     * @@param l_orderNumberList - (注文件数一覧)<BR>
     * <BR>
     * 注文件数Paramsの配列<BR>
     * <BR>
     * ----<English>-------------------<BR>
     * <BR>
     * l_orderNumberList<BR>
     * <BR>
     * An array of orderExecutedCountParams<BR>
     * <BR>
     * @@param l_productTypeList - (商品区分一覧)<BR>
     * <BR>
     * 商品区分一覧<BR>
     * ※任意の以下の値の配列<BR>
     * <BR>
     * 0：　@現物株式<BR>
     * 1：　@信用取引<BR>
     * 2：　@株式ミニ投資<BR>
     * 3：　@オプション<BR>
     * 4：　@先物<BR>
     * 5：　@投信<BR>
     * 6：　@中国F<BR>
     * 7：　@MMF<BR>
     * 8：　@外国株式<BR>
     * 9：　@債券<BR>
     * <BR>
     * ----<English>-------------------<BR>
     * <BR>
     * l_productTypeList<BR>
     * ※An anrray of the following arbitrary values<BR>
     * <BR>
     * 0: Def.EQUITY<BR>
     * 1: Def.MARGIN<BR>
     * 2: Def.MINI_STOCK<BR>
     * 3: Def.OPTION<BR>
     * 4: Def.FUTURE<BR>
     * 5: Def.MF<BR>
     * 6: Def.MIDIUM_TERM_GOV_FUND<BR>
     * 7: Def.MMF<BR>
     * 8: Def.FOREGIN_STOCK<BR>
     * 9: Def.BOND<BR>
     * <BR>
     * @@param l_marketCodeList - (市場コード一覧)<BR>
     * <BR>
     * 市場コード一覧<BR>
     * (市場コードはWEB3MarketCodeDef.javaにて定義)<BR>
     * <BR>
     * ----<English>-------------------<BR>
     * <BR>
     * l_marketCodeList<BR>
     * (marketCode is defined at WEB3MarketCodeDef.java)<BR>
     * <BR>
     * @@return List
     * @@roseuid 419B562B02CC
     */
    protected List createProductMarketTotalCountInfoList(
        OrderExecutedCountParams[] l_orderNumberList,
        String[] l_productTypeList,
        String[] l_marketCodeList)
    {
        final String STR_METHOD_NAME =
            "createProductMarketTotalCountInfoList("
                + "OrderExecutedCountParams[], String[], String[])";
        log.entering(STR_METHOD_NAME);

        ArrayList l_lisSummaryOrderNumbers = null;
        ArrayList l_lisProductMarketTotalCountInfoUnits = null;

        // Step 1 Create a arraylist to store the return values
        l_lisProductMarketTotalCountInfoUnits = new ArrayList();

        // 2) Create a summary list
        l_lisSummaryOrderNumbers = new ArrayList();
        l_lisSummaryOrderNumbers.addAll(Arrays.asList(l_orderNumberList));

        // 3)Calculate productMarketTotalCount
        // Step 3-1 Loop the following process for as many times as l_productTypeList
        
        // 市場詳細表示
        if (l_marketCodeList != null && l_marketCodeList.length != 0)
        {
            for (int j = 0; j < l_productTypeList.length; j ++)
            {
                for (int i = 0; i < l_marketCodeList.length; i ++)
                {
                    WEB3AdminORProductMarketNumberInfoUnit l_unit =
                        getCountUnit(l_lisSummaryOrderNumbers,
                            l_productTypeList[j],
                            l_marketCodeList[i]
                            );
                    l_lisProductMarketTotalCountInfoUnits.add(l_unit);
                }
            }
        }
        // 市場合計表示
        else
        {
            for (int j = 0; j < l_productTypeList.length; j ++)
            {
                WEB3AdminORProductMarketNumberInfoUnit l_unit =
                    getCountUnit(l_lisSummaryOrderNumbers,
                        l_productTypeList[j]
                        );
                l_lisProductMarketTotalCountInfoUnits.add(l_unit);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisProductMarketTotalCountInfoUnits;
    }
    
    /**
     * 商品市場別合計件数情報（市場詳細表示）を１件作成する。
     * <br />
     * @@param orderExecCountList
     * @@param l_productType
     * @@param l_marketCode
     * @@return 商品市場別合計件数情報
     */
    private WEB3AdminORProductMarketNumberInfoUnit getCountUnit(
        ArrayList orderExecCountList,
        String l_productType, 
        String l_marketCode
        )
    {
        int l_orderCount = 0;
        int l_execCount = 0;
        
        for (Iterator iterator = orderExecCountList.iterator(); 
             iterator.hasNext(); )
        {
            OrderExecutedCountParams l_params = 
                (OrderExecutedCountParams)iterator.next();
            
            if (l_params.market_code.equals(l_marketCode) &&
                l_params.product_div.equals(l_productType))
            {
                l_orderCount = l_orderCount +  
                    l_params.buy_order_count + 
                    l_params.sell_order_count;
                l_execCount = l_execCount + 
                    l_params.execute_count;
            }
        }
        
        WEB3AdminORProductMarketNumberInfoUnit l_returnUnit = 
            new WEB3AdminORProductMarketNumberInfoUnit();
        
        l_returnUnit.marketCode = l_marketCode;
        l_returnUnit.sumProductType = l_productType;
        l_returnUnit.executeNumber = String.valueOf(l_execCount);
        l_returnUnit.orderNumber = String.valueOf(l_orderCount);
        
        return l_returnUnit;
    }

    /**
     * 商品市場別合計件数情報（市場合計表示）を１件作成する。
     * <br />
     * @@param orderExecCountList
     * @@param l_productType
     * @@param l_marketCode
     * @@return 商品市場別合計件数情報
     */
    private WEB3AdminORProductMarketNumberInfoUnit getCountUnit(
        ArrayList orderExecCountList,
        String l_productType 
        )
    {
        int l_orderCount = 0;
        int l_execCount = 0;
        
        for (Iterator iterator = orderExecCountList.iterator(); 
             iterator.hasNext(); )
        {
            OrderExecutedCountParams l_params = 
                (OrderExecutedCountParams)iterator.next();
            
            if (l_params.product_div.equals(l_productType))
            {
                l_orderCount = l_orderCount +  
                    l_params.buy_order_count + 
                    l_params.sell_order_count;
                l_execCount = l_execCount + 
                    l_params.execute_count;
            }
        }
        
        WEB3AdminORProductMarketNumberInfoUnit l_returnUnit = 
            new WEB3AdminORProductMarketNumberInfoUnit();
        
        l_returnUnit.marketCode = null;
        l_returnUnit.sumProductType = l_productType;
        l_returnUnit.orderNumber = String.valueOf(l_orderCount);
        l_returnUnit.executeNumber = String.valueOf(l_execCount);
        
        return l_returnUnit;
    }
}@
