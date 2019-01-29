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
filename	WEB3AdminOffFloorChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄更新サービス実装クラス(WEB3AdminOffFloorChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.text.DecimalFormat;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.OffFloorOrderProductParams;

import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeInputResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorChangeService;

/**
 * (管理者立会外分売銘柄更新サービスImpl)<BR>
 * <BR>
 * 管理者立会外分売銘柄更新サービス実装クラス<BR>
 * <BR>
 * WEB3AdminOffFloorChangeServiceImpl<BR>
 * <BR>
 * @@author Wanishree and Umadevi
 * @@version 1.0
 */
public class WEB3AdminOffFloorChangeServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminOffFloorChangeService
{
    /**
    * Log Variable
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorChangeServiceImpl.class);

    /**
     * @@roseuid 421AE4F1035E
     */
    public WEB3AdminOffFloorChangeServiceImpl()
    {

    }

    /**
     * 管理者立会外分売銘柄更新サービス処理を実施する。<BR>
     * <BR>
     * Execute WEB3AdminOffFloorChangeServiceImpl<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41BD8A8F031D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;
        try
        {
            if (l_request instanceof WEB3AdminOffFloorChangeInputRequest)
            {
                l_response = getInputScreen((WEB3AdminOffFloorChangeInputRequest) l_request);
            } else if (l_request instanceof WEB3AdminOffFloorChangeConfirmRequest)
            {
                l_response = validateChange((WEB3AdminOffFloorChangeConfirmRequest) l_request);
            } else if (l_request instanceof WEB3AdminOffFloorChangeCompleteRequest)
            {
                l_response = submitChange((WEB3AdminOffFloorChangeCompleteRequest) l_request);
            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT リクエスト NOT 管理者立会外分売銘柄更新リクエスト");
            }
        } catch (NotFoundException l_notFoundException)
        {
            log.error(l_notFoundException.getMessage(), l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_notFoundException.getMessage(),
                l_notFoundException);
        } catch (DataQueryException l_dqex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dqex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.toString(),
                l_dqex);

        } catch (DataNetworkException l_dnex)
        {
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
     * (get入力画面)<BR>
     * <BR>
     * 管理者立会外分売の銘柄更新画面表示サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者立会外分売銘柄更新）get入力画面」参照。<BR>
     * <BR>
     * -----<English>------------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     * Execute WEB3AdminOffFloorChangeService<BR>
     * <BR>
     * Refer to the sequence diagram, "（administrator: off floor
     * change）getInputScreen"<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * リクエストデータ<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeInputResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 41BD8A8F031F
     */
    protected WEB3AdminOffFloorChangeInputResponse getInputScreen(WEB3AdminOffFloorChangeInputRequest l_request)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminOffFloorChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 Call validate()
        l_request.validate();

        //1.2 Call getInstanceFromLoginInfo( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 Call validateAuthority()
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_OFF_FLOOR_ORDER, true);

        //1.4 Call getInstitution( )
        Institution l_institution = l_administrator.getInstitution();

        //1.5 Call getOffFloorOrderProduct()
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(
            ProductTypeEnum.EQUITY
            );
        WEB3EquityProductManager l_equityProductManager = 
            (WEB3EquityProductManager) l_tradingModule.getProductManager();

        //1.9 Call getProduct()
        EqTypeProduct l_product = null; 
        
		try
		{
			l_product = l_equityProductManager.getProduct(
			    l_institution, l_request.productKey.productCode
			    );
		} catch (NotFoundException e)
		{
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME);
		}

        OffFloorOrderProductParams l_offFloorOrderProduct =
            l_equityProductManager.getOffFloorOrderProduct(
                l_institution.getInstitutionCode(),
                l_request.productKey.productCode,
                l_request.productKey.marketCode,
                l_request.productKey.orderEndDatetime
                );

        //1.6 Divergence Flow
        if (l_offFloorOrderProduct == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.7  Call isCanUpdateDeleteOffFloorOrderProduct()
        //1.8 Call isCanUpdateDeleteOffFloorOrderProduct( )
        if (!l_equityProductManager.isCanUpdateDeleteOffFloorOrderProduct(
            l_offFloorOrderProduct))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01388,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.10 Call createResponse( )
        WEB3AdminOffFloorChangeInputResponse l_response = 
            (WEB3AdminOffFloorChangeInputResponse) l_request.createResponse();

        //1.11 Property Set
        l_response.productName = 
            ((EqtypeProductRow)l_product.getDataSourceObject()).getStandardName();
        l_response.orderStartDatetime = l_offFloorOrderProduct.order_start_datetime;
        DecimalFormat l_dFormat = new DecimalFormat("0");
        if (l_offFloorOrderProduct.off_floor_order_price == null)
        {
            l_response.offFloorOrderPrice = null;
        } else
        {
            l_response.offFloorOrderPrice = 
                l_dFormat.format(l_offFloorOrderProduct.off_floor_order_price);
        }
        if (l_offFloorOrderProduct.max_apply_quantity == null)
        {
            l_response.maxApplyQuantity = null;
        } else
        {
            l_response.maxApplyQuantity = 
                l_dFormat.format(l_offFloorOrderProduct.max_apply_quantity);
        }
        log.exiting(STR_METHOD_NAME);

        // 1.12 Return
        return l_response;
    }

    /**
     * (validate更新)<BR>
     * <BR>
     * 管理者立会外分売の銘柄更新確認サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者立会外分売銘柄更新）validate更新」参照。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * validateChange<BR>
     * <BR>
     * Execute WEB3AdminOffFloorChangeServiceImpl validate process<BR>
     * <BR>
     * Refer to the sequence diagram, "（administrator: off floor
     * change）validateChange"<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * リクエストデータ<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeConfirmResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41BD8A8F0321
     */
    protected WEB3AdminOffFloorChangeConfirmResponse validateChange(WEB3AdminOffFloorChangeConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AdminOffFloorChangeConfirmRequest)";
        
        log.entering(STR_METHOD_NAME);

        //1.1 Call validate()
        l_request.validate();

        //1.2 Call getInstanceFromLoginInfo( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 Call validateAuthority(l_strTransactionCategory : String, l_isUpdate : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_OFF_FLOOR_ORDER, true);

        //1.4 Call getInstitutionCode( )
        // 1.5 Call getOffFloorOrderProduct()
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(
            ProductTypeEnum.EQUITY
            );

        WEB3EquityProductManager l_equityProductManager = 
            (WEB3EquityProductManager) l_tradingModule.getProductManager();

        Institution l_institution = l_administrator.getInstitution();

        OffFloorOrderProductParams l_offFloorOrderProduct =
            l_equityProductManager.getOffFloorOrderProduct(
                l_institution.getInstitutionCode(),
                l_request.productKey.productCode,
                l_request.productKey.marketCode,
                l_request.productKey.orderEndDatetime);

        // 1.6 Divergence Flow()
        if (l_offFloorOrderProduct == null)
        {
            String l_strMsg = "no corresponding data";
            log.error(l_strMsg);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        //1.7 Call isCanUpdateDeleteOffFloorOrderProduct()
        //1.8 Divergence Flow
        if (!l_equityProductManager.isCanUpdateDeleteOffFloorOrderProduct(
             l_offFloorOrderProduct))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01388,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        String l_strBranchCode = l_administrator.getBranchCode();

        // 1.9 Call isAllBranchsPermission()
        // 1.10 Call getBranchCode()
        // 1.11 Call validateOffFloorOrderProduct()
        WEB3EquityOrderManager l_equityOrderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        if (l_administrator.isAllBranchsPermission())
        {
            l_strBranchCode = null;
        }
        WEB3GentradeTradingTimeManagement.resetMarketCode(
            l_request.productKey.marketCode
            );
        WEB3EquityTradedProduct l_equityTradedProduct =
            l_equityOrderManager.validateOffFloorOrderProduct(
                l_institution.getInstitutionCode(),
                l_strBranchCode,
                l_request.productKey.productCode,
                l_request.productKey.marketCode,
                l_request.maxApplyQuantity);

        //1.12   createResponse
        WEB3AdminOffFloorChangeConfirmResponse l_response = 
            (WEB3AdminOffFloorChangeConfirmResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);

        //1.13 Return
        return l_response;
    }

    /**
     * (submit更新)<BR>
     * <BR>
     * 管理者立会外分売の銘柄更新完了サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者立会外分売銘柄更新）submit更新」参照。<BR>
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * submitChange<BR>
     * <BR>
     * Execute WEB3AdminOffFloorChangeService submit process<BR>
     * <BR>
     * Refer to the sequence diagram "（administrator: off floor
     * change）submitChange"<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * リクエストデータ<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeCompleteResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 41BD8A8F032D
     */
    protected WEB3AdminOffFloorChangeCompleteResponse submitChange(WEB3AdminOffFloorChangeCompleteRequest l_request)
        throws WEB3BaseException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AdminOffFloorChangeCompleteRequest)";

        log.entering(STR_METHOD_NAME);

        //1.1 Call validate()
        l_request.validate();

        //1.2 Call getInstanceFromLoginInfo( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 Call validateAuthority()
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_OFF_FLOOR_ORDER, true);

        // 1.4 Call validateTradingPassword()
        l_administrator.validateTradingPassword(l_request.password);

        //1.5 Call getInstitutionCode( )
        Institution l_institution = l_administrator.getInstitution();

        //1.6 Call getOffFloorOrderProduct()
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(
            ProductTypeEnum.EQUITY
            );
        WEB3EquityProductManager l_equityProductManager = 
            (WEB3EquityProductManager) l_tradingModule.getProductManager();
        OffFloorOrderProductParams l_offFloorOrderProduct =
            l_equityProductManager.getOffFloorOrderProduct(
                l_institution.getInstitutionCode(),
                l_request.productKey.productCode,
                l_request.productKey.marketCode,
                l_request.productKey.orderEndDatetime
                );

        //1.7 Divergence Flow
        if (l_offFloorOrderProduct == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.8 Call isCanUpdateDeleteOffFloorOrderProduct()
        //1.9 Divergence Flow
        if (!l_equityProductManager.isCanUpdateDeleteOffFloorOrderProduct(
            l_offFloorOrderProduct))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01388,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.10 Call isAllBranchsPermission()
        //1.11 Call getBranchCode()
        //1.12 Call validateOffFloorOrderProduct()
        String l_strBranchCode = l_administrator.getBranchCode();
        WEB3EquityOrderManager l_equityOrderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        if (l_administrator.isAllBranchsPermission())
        {
            l_strBranchCode = null;
        }
        WEB3GentradeTradingTimeManagement.resetMarketCode(
            l_request.productKey.marketCode
            );
        WEB3EquityTradedProduct l_equityTradedProduct =
            l_equityOrderManager.validateOffFloorOrderProduct(
                l_institution.getInstitutionCode(),
                l_strBranchCode,
                l_request.productKey.productCode,
                l_request.productKey.marketCode,
                l_request.maxApplyQuantity
                );

        //1.13 Call updateOffFloorOrderProduct()
        updateOffFloorOrderProduct(l_request, l_institution.getInstitutionCode());

        //1.14 createResponse
        WEB3AdminOffFloorChangeCompleteResponse l_response = 
            (WEB3AdminOffFloorChangeCompleteResponse) l_request.createResponse();

        //1.15 Set The Response Data
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        log.exiting(STR_METHOD_NAME);

        // 1.16 return
        return l_response;
    }

    /**
     * (update立会外分売銘柄)<BR>
     * <BR>
     * 【立会外分売銘柄テーブル】への更新を行う。<BR>
     * <BR>
     * １）　@【立会外分売銘柄テーブル】を更新する。 <BR>
     * <BR>
     * 　@　@＜更新条件＞ <BR>
     * 　@　@　@証券会社コード = 引数の証券会社コード<BR>
     * 　@　@かつ　@銘柄コード = 引数のリクエストデータ.立会外分売銘柄キー.銘柄コード<BR>
     * 　@　@かつ　@市場コード = 引数のリクエストデータ.立会外分売銘柄キー.市場コード<BR>
     * 　@　@かつ　@受付終了日時 =
     * 引数のリクエストデータ.立会外分売銘柄キー.受付終了日時<BR>
     * <BR>
     * 　@　@＜更新対象プロパティ＞ <BR>
     * 　@　@　@申込株数上限 = 引数のリクエストデータ.申込株数上限<BR>
     * 　@　@　@分売価格 = 引数のリクエストデータ.分売価格<BR>
     * 　@　@　@更新日付 = GtlUtils.getSystemTimestamp( ) <BR>
     * <BR>
     * ２）　@returnする。 <BR>
     * <BR>
     * -----<English>-------------<BR>
     * <BR>
     * updateOffFloorOrderProduct<BR>
     * <BR>
     * Update off_floor_order_product table<BR>
     * <BR>
     * 1)Update off_floor_order_product table<BR>
     * <BR>
     * 　@　@＜Conditions to update＞ <BR>
     * 　@　@　@institution_code = l_strInstitutionCode<BR>
     * 　@　@and product_code = l_request.productKey.productCode<BR>
     * 　@　@and market_code = l_request.productKey.marketCode<BR>
     * 　@　@and order_end_datetime = l_request.productKey.orderEndDatetime<BR>
     * <BR>
     * 　@　@＜Properties to update＞ <BR>
     * 　@　@　@max_apply_quantity = l_request.maxApplyQuantity<BR>
     * 　@　@　@off_floor_order_price = l_request.offFloorOrderPrice<BR>
     * 　@　@　@last_updated_timestamp = GtlUtils.getSystemTimestamp( ) <BR>
     * <BR>
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
     * @@roseuid 41C003290123
     * @@author Umadevi
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     */
    protected void updateOffFloorOrderProduct(
        WEB3AdminOffFloorChangeCompleteRequest l_request,
        String l_strInstitutionCode)
        throws DataNetworkException, DataQueryException
    {
        OffFloorOrderProductParams l_params = new OffFloorOrderProductParams();
        
        Double l_dblMaxApplyQuantity = null;
        if (l_request.maxApplyQuantity != null)
        {
            l_dblMaxApplyQuantity = new Double(l_request.maxApplyQuantity);
        }
        
        Double l_dblOffFloorOrderPrice = null;
        if (l_request.offFloorOrderPrice != null)
        {
            l_dblOffFloorOrderPrice = new Double(l_request.offFloorOrderPrice);
        }

        l_params.setInstitutionCode(l_strInstitutionCode);
        l_params.setProductCode(l_request.productKey.productCode);
        l_params.setMarketCode(l_request.productKey.marketCode);
        l_params.setOrderEndDatetime(l_request.productKey.orderEndDatetime);
        l_params.setMaxApplyQuantity(l_dblMaxApplyQuantity);
        l_params.setOffFloorOrderPrice(l_dblOffFloorOrderPrice);
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        WEB3DataAccessUtility.updateRow(l_params);
    }
}
@
