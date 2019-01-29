head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (管理者立会外分売銘柄削除サービスImpl)(WEB3AdminOffFloorDeleteServiceImpl)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.text.DecimalFormat;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;

import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.data.OffFloorOrderProductParams;
import webbroker3.equity.data.OffFloorOrderProductRow;

import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteConfirmResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorDeleteService;

/**
 * (管理者立会外分売銘柄削除サービスImpl)<BR>
 * <BR>
 * 管理者立会外分売銘柄削除サービス実装クラス<BR>
 * <BR>
 * WEB3AdminOffFloorDeleteServiceImpl<BR>
 * @@author Anil
 * @@version 1.0
 */
public class WEB3AdminOffFloorDeleteServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminOffFloorDeleteService
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorDeleteServiceImpl.class);
    /**
     * @@roseuid 421AE517038D
     */
    public WEB3AdminOffFloorDeleteServiceImpl()
    {
    }

    /**
     * 管理者立会外分売銘柄削除サービス処理を実施する。<BR>
     * <BR>
     * Execute WEB3AdminOffFloorDeleteService process<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41BD8F080113
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        try
        {
            if (l_request instanceof WEB3AdminOffFloorDeleteConfirmRequest)
            {
                l_response = this.validateDelete((WEB3AdminOffFloorDeleteConfirmRequest) l_request);

            } else if (l_request instanceof WEB3AdminOffFloorDeleteCompleteRequest)
            {
                l_response = this.submitDelete((WEB3AdminOffFloorDeleteCompleteRequest) l_request);

            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT リクエスト NOT 管理者立会外分売銘柄削除リクエスト");
            }
        } catch (NotFoundException l_nfe)
        {
            log.debug(l_nfe.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.toString(),
                l_nfe);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate削除)<BR>
     * <BR>
     * 管理者立会外分売の銘柄削除確認サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者立会外分売銘柄削除）validate削除」参照。<BR>
     * <BR>
     * ----<English>-----------------<BR>
     * <BR>
     * validateDelete<BR>
     * <BR>
     * Execute WEB3AdminOffFloorDeleteService validate process<BR>
     * <BR>
     * Refer to the sequence diagram "（administrator: off floor
     * delete）validateDelete"<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * リクエストデータ<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteConfirmResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 41BD8F080115
     */
    protected WEB3AdminOffFloorDeleteConfirmResponse
        validateDelete(WEB3AdminOffFloorDeleteConfirmRequest l_request)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = "validateDelete(WEB3AdminOffFloorDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // Step 1.1 l_request object is validated.
        l_request.validate();

        // Step 1.2 Aquires administator instance from login infomation
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        // Step 1.3 Checks transactionCategory i.e validates it.
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_OFF_FLOOR_ORDER, true);

        // Step 1.4 Acquire l_institution
        Institution l_institution = l_web3Administrator.getInstitution();

        // Step1.5 Acquire a record of off_floor_order_product table
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_web3EqtyPrdtMgr = 
            (WEB3EquityProductManager) l_tradingModule.getProductManager();

        //1.9 Call getProduct()
        EqTypeProduct l_eqTypeProduct = null; 
        
        try
        {
            l_eqTypeProduct = l_web3EqtyPrdtMgr.getProduct(
                l_institution, l_request.productKey.productCode
                );
        } catch (NotFoundException e)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        OffFloorOrderProductParams l_offFloorOrderPrdtPrms =
            l_web3EqtyPrdtMgr.getOffFloorOrderProduct(
                l_institution.getInstitutionCode(),
                l_request.productKey.productCode,
                l_request.productKey.marketCode,
                l_request.productKey.orderEndDatetime);

        // Step 1.6 If no Data, throw Exception
        if (l_offFloorOrderPrdtPrms == null)
        {
            String l_strMsg = "No data for the Off Floor Order Product.";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.7 Check if specified offFloorOrderProduct data is able to be updated or deleted
        // Step 1.8 Check if l_isCanUpdateDelete == false, throw Exception
        if (!l_web3EqtyPrdtMgr.isCanUpdateDeleteOffFloorOrderProduct(
            l_offFloorOrderPrdtPrms))
        {
            String l_strMsg = "Can not Update or Delete Off Floor Order Product.";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01388,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.10 Create the response data
        WEB3AdminOffFloorDeleteConfirmResponse l_response = 
            (WEB3AdminOffFloorDeleteConfirmResponse) l_request.createResponse();
        DecimalFormat l_decFormat = new DecimalFormat("0"); 

        // Step 1.11. Set the Property to the reponse object
        l_response.productName = 
            ((EqtypeProductRow)l_eqTypeProduct.getDataSourceObject()).getStandardName();
        l_response.orderStartDatetime = 
            l_offFloorOrderPrdtPrms.order_start_datetime;
        // If off_floor_order_price is null then set null
        if (l_offFloorOrderPrdtPrms.off_floor_order_price == null)
        {
            l_response.offFloorOrderPrice = null;
        } else
        {
            l_response.offFloorOrderPrice = 
                l_decFormat.format(l_offFloorOrderPrdtPrms.off_floor_order_price
                );
        }
        if (l_offFloorOrderPrdtPrms.max_apply_quantity == null)
        {
            l_response.maxApplyQuantity = null;
        } else
        {
            l_response.maxApplyQuantity = 
                l_decFormat.format(l_offFloorOrderPrdtPrms.max_apply_quantity);
        }

        log.exiting(STR_METHOD_NAME);

        // Step 1.12, return the l_response
        return l_response;
    }

    /**
     * (submit削除)<BR>
     * <BR>
     * 管理者立会外分売の銘柄削除完了サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者立会外分売銘柄削除）submit削除」参照。<BR>
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * submitDelete<BR>
     * <BR>
     * Execute WEB3AdminOffFloorDeleteService submit process<BR>
     * <BR>
     * Refer to the sequence diagram "（administrator: off floor
     * delete）submitDelete"<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * リクエストデータ<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteCompleteResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41BD8F080123
     */
    protected WEB3AdminOffFloorDeleteCompleteResponse
        submitDelete(WEB3AdminOffFloorDeleteCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitDelete(WEB3AdminOffFloorDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        // Step 1.1 Validate request
        l_request.validate();

        // Step 1.2 Aquires administator instance from login infomation
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        // Step1.3 Checks transactionCategory i.e validates it.
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_OFF_FLOOR_ORDER, true);

        // Step 1.4 Check tradingPassword
        l_web3Administrator.validateTradingPassword(l_request.password);

        // Step 1.5 Acquire l_institution
        Institution l_institution = l_web3Administrator.getInstitution();

        // Step 1.6 Acquire records from off_floor_order_product table by specified keys
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_web3EquityPrdtMgr = 
            (WEB3EquityProductManager) l_tradingModule.getProductManager();

        OffFloorOrderProductParams l_offFloorOrderPrdtPrms =
            l_web3EquityPrdtMgr.getOffFloorOrderProduct(
                l_institution.getInstitutionCode(),
                l_request.productKey.productCode,
                l_request.productKey.marketCode,
                l_request.productKey.orderEndDatetime);

        // Step 1.7 If no Data, throw Exception
        if (l_offFloorOrderPrdtPrms == null)
        {
            String l_strMsg = "No data for the Off Floor Order Product.";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.8 Check if specified offFloorOrderProduct data is able to be updated or deleted
        // Step 1.9 Check if l_isCanUpdateDelete == false, throw Exception
        if (!l_web3EquityPrdtMgr.isCanUpdateDeleteOffFloorOrderProduct(
            l_offFloorOrderPrdtPrms))
        {
            String l_strMsg = "Can not Update or Delete Off Floor Order Product";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01388,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        // Step 1.10 Delete one record from off_floor_order_product table
        deleteOffFloorOrderProduct(
            l_request, l_institution.getInstitutionCode()
            );

        // Step 1.11 Create response object l_response
        WEB3AdminOffFloorDeleteCompleteResponse l_response = 
            (WEB3AdminOffFloorDeleteCompleteResponse) l_request.createResponse();

        // Step 1.12 Edit the response data
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();

        // Step 1.13 return response object
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (delete立会外分売銘柄)<BR>
     * <BR>
     * 【立会外分売銘柄テーブル】からの削除を行う。<BR>
     * <BR>
     * １）　@【立会外分売銘柄テーブル】から、以下の条件に該当するレコードを削除する。<B
     * R>
     * <BR>
     * 　@　@＜削除条件＞ <BR>
     * 　@　@　@証券会社コード = 引数の証券会社コード<BR>
     * 　@　@かつ　@銘柄コード = 引数のリクエストデータ.立会外分売銘柄キー.銘柄コード<BR>
     * 　@　@かつ　@市場コード = 引数のリクエストデータ.立会外分売銘柄キー.市場コード<BR>
     * 　@　@かつ　@受付終了日時 =
     * 引数のリクエストデータ.立会外分売銘柄キー.受付終了日時<BR>
     * <BR>
     * ２）　@returnする。 <BR>
     * <BR>
     * -----<English>-------------<BR>
     * <BR>
     * deleteOffFloorOrderProduct<BR>
     * <BR>
     * Execute deletion from off_floor_order_product table<BR>
     * <BR>
     * 1)Delete the records corresponding to the following conditions from
     * off_floor_order_product table<BR>
     * <BR>
     * 　@　@＜Conditions to delete＞ <BR>
     * 　@　@　@institution_code = l_strInstitutionCode<BR>
     * 　@　@and product_code = l_request.productKey.productCode<BR>
     * 　@　@and market_code = l_request.productKey.marketCode<BR>
     * 　@　@and order_end_datetime = l_request.productKey.orderEndDatetime<BR>
     * 2)return<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * リクエストデータオブジェクト。<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * <BR>
     * 証券会社コード。<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@throws WEB3SystemLayerException WEB3SystemLayerException
     * @@roseuid 41C00E8401DE
     */
    protected void deleteOffFloorOrderProduct(WEB3AdminOffFloorDeleteCompleteRequest l_request,
        String l_strInstitutionCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "deleteOffFloorOrderProduct("
                + "WEB3AdminOffFloorChangeCompleteRequest l_request,"
                + "String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);

        // Step 1 Creating the where clause & storing it in a Stirng variable
        String l_strWhere =
            "institution_code = ? "
                + " and product_code = ? "
                + " and market_code  = ? "
                + " and order_end_datetime  = ? ";

        // Creating an arrray of objects to store the where clause fields in it
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                l_request.productKey.productCode,
                l_request.productKey.marketCode,
                l_request.productKey.orderEndDatetime };
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(OffFloorOrderProductRow.TYPE, l_strWhere, l_objWhere);
        } catch (DataNetworkException l_ne)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        } catch (DataQueryException l_qe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_qe.getMessage(),
                l_qe);
        }

        log.exiting(STR_METHOD_NAME);
    }
}@
