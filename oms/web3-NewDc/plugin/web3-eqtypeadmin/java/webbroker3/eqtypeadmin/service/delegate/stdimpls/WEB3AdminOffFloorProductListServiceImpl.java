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
filename	WEB3AdminOffFloorProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄一覧サービス実装クラス(WEB3AdminOffFloorProductListServiceImpl.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.data.OffFloorOrderProductParams;
import webbroker3.equity.data.OffFloorOrderProductRow;

import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductGroup;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductKey;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductListComparator;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductListResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorSortKey;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorProductListService;

/**
 * (管理者立会外分売銘柄一覧サービスImpl)<BR>
 * <BR>
 * 管理者立会外分売銘柄一覧サービス実装クラス<BR>
 * <BR>
 * WEB3AdminOffFloorProductListServiceImpl class<BR>
 * <BR>
 * @@author Anupama and Ambha
 * @@version 1.0
 */
public class WEB3AdminOffFloorProductListServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminOffFloorProductListService
{
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorProductListServiceImpl.class);

    /**
     * @@roseuid 421AE4AE039D
     */
    public WEB3AdminOffFloorProductListServiceImpl()
    {
    }

    /**
     * 管理者立会外分売銘柄一覧サービス処理を実施する。<BR>
     * <BR>
     * Execute WEB3AdminOffFloorProductListService<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@author Ambha
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41BCF8F701A2
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        try
        {
            /* If request is of type WEB3AdminOffFloorProductListRequest then call getProductList().
             * Otherwise throw exception
             */
            if (l_request instanceof WEB3AdminOffFloorProductListRequest)
            {
                l_response = getProductList((WEB3AdminOffFloorProductListRequest) l_request);

            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT リクエスト NOT 管理者立会外分売銘柄一覧リクエスト");
            }

        } catch (NotFoundException l_notFoundException)
        {
            log.error(l_notFoundException.getMessage(), l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_notFoundException.getMessage(),
                l_notFoundException);

        } catch (DataNetworkException l_dataNetworkExp)
        {
            log.debug(l_dataNetworkExp.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dataNetworkExp);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataNetworkExp.toString(),
                l_dataNetworkExp);

        } catch (DataFindException l_dataFindExp)
        {
            log.debug(l_dataFindExp.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dataFindExp);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataFindExp.toString(),
                l_dataFindExp);

        } catch (DataQueryException l_dataQueryExp)
        {
            log.debug(l_dataQueryExp.getMessage());
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dataQueryExp);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataQueryExp.toString(),
                l_dataQueryExp);

        }

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * (get銘柄一覧)<BR>
     * <BR>
     * 管理者立会外分売の銘柄一覧表示サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者立会外分売銘柄一覧）get銘柄一覧」参照。<BR>
     * <BR>
     * -----<English>----------------------<BR>
     * <BR>
     * getProductList<BR>
     * <BR>
     * Execute WEB3AdminOffFloorProductListService<BR>
     * <BR>
     * Refer to the sequence diagram, "（administrator: off floor product
     * list）getProductList"<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * リクエストデータ<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@author anupama
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductListResponse
     * @@exception DataQueryException DataQueryException
     * @@exception WEB3BaseException WEB3BaseException
     * @@exception DataFindException DataFindException
     * @@exception DataNetworkException DataNetworkException
     * @@exception NotFoundException NotFoundException
     * @@roseuid 41BD01A6001B
     */
    protected WEB3AdminOffFloorProductListResponse getProductList(
        WEB3AdminOffFloorProductListRequest l_request)
        throws
            WEB3BaseException,
            DataFindException,
            DataNetworkException,
            DataQueryException,
            NotFoundException
    {
        final String STR_METHOD_NAME = "getProductList(WEB3AdminOffFloorProductListRequest)";
        final String DATE_FORMAT = "yyyyMMdd";
        final int DATE_LENGTH = 10;

        log.entering(STR_METHOD_NAME);

        DecimalFormat l_decFormat = new DecimalFormat("0"); 
        
        // Call validate()
        l_request.validate();

        // Call getInstanceFromLoginInfo( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        // Call validateAuthority(l_strTransactionCategory : String, l_isUpdate : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_OFF_FLOOR_ORDER, false
            );

        // Call getInstitution()
        Institution l_institution = l_administrator.getInstitution();
               
        // Call getOffFloorOrderProductList(l_institution : Institution)
        OffFloorOrderProductParams[] l_offFloorOrderProdParams = 
            this.getOffFloorOrderProductList(l_institution);
            
        // get立会外分売注文単位
        //  当該立会外分売に対して登録されている注文情報を取得する。
        EqtypeOrderUnitRow[] l_eqtypeOrderUnitRows =
            this.getOffFloorOrderUnits(
                l_institution);

        // Call createResponse( )
        WEB3AdminOffFloorProductListResponse l_response = 
            (WEB3AdminOffFloorProductListResponse) l_request.createResponse();

        /* Loop for as many times as the number of records
         * of acquired off floor order products
         */
        int l_intOffFlrProdParamsCnt = l_offFloorOrderProdParams.length;
        WEB3AdminOffFloorProductGroup[] l_adminOffFloorProdGrps = 
            new WEB3AdminOffFloorProductGroup[l_intOffFlrProdParamsCnt];

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_equityProdManager = 
            (WEB3EquityProductManager) l_tradingModule.getProductManager();

        int j = 0;
        int k = 0;
        
        for (int i = 0; i < l_intOffFlrProdParamsCnt; i++)
        {
            // Create an instance of WEB3AdminOffFloorProductGroup
            WEB3AdminOffFloorProductGroup l_adminOffFloorProdGrp 
                = new WEB3AdminOffFloorProductGroup();

            // Call getProduct(l_institution : Institution, l_strProductCode : String)
            String l_strProductCode = l_offFloorOrderProdParams[i].getProductCode();
            WEB3EquityProduct l_equityProduct =
                (WEB3EquityProduct) l_equityProdManager.getProduct(
                    l_institution, l_strProductCode
                    );

            // Set properties of WEB3AdminOffFloorProductGroup
            l_adminOffFloorProdGrp.productKey = new WEB3AdminOffFloorProductKey();
            l_adminOffFloorProdGrp.productKey.productCode = l_strProductCode;
            l_adminOffFloorProdGrp.productKey.marketCode = l_offFloorOrderProdParams[i].market_code;
            l_adminOffFloorProdGrp.productKey.orderEndDatetime =
                l_offFloorOrderProdParams[i].order_end_datetime;
            l_adminOffFloorProdGrp.productName = 
                ((EqtypeProductRow)l_equityProduct.getDataSourceObject()).getStandardName();
            l_adminOffFloorProdGrp.orderStartDatetime =
                l_offFloorOrderProdParams[i].order_start_datetime;

            // Set explicitly null even if it is set in DB level
            if (l_offFloorOrderProdParams[i].getOffFloorOrderPrice() == 0)
            {
                l_adminOffFloorProdGrp.offFloorOrderPrice = null;

            } else
            {
                l_adminOffFloorProdGrp.offFloorOrderPrice =
                    l_decFormat.format(
                        l_offFloorOrderProdParams[i].getOffFloorOrderPrice()
                        ); 
            }

            // Set explicitly null even if it is set in DB level
            if (l_offFloorOrderProdParams[i].getMaxApplyQuantity() == 0)
            {
                l_adminOffFloorProdGrp.maxApplyQuantity = null;

            } else
            {
                l_adminOffFloorProdGrp.maxApplyQuantity =
                    l_decFormat.format(
                        l_offFloorOrderProdParams[i].getMaxApplyQuantity()
                        ); 
            }

            l_adminOffFloorProdGrp.registDatetime =
                l_offFloorOrderProdParams[i].last_updated_timestamp;

            // Divergence Flow
            Timestamp l_tsSysTimestamp = GtlUtils.getSystemTimestamp();
            Date l_datSysDate = WEB3DateUtility.toDay(l_tsSysTimestamp);

            Timestamp l_tsOrderStartDateTime = l_offFloorOrderProdParams[i].order_start_datetime;
            Timestamp l_tsOrderEndDateTime = l_offFloorOrderProdParams[i].order_end_datetime;

            /* Set totalApplyQuantity and totalExecuteQuantity equal null
             * if timeStamp is before order_start_datetime or after daily_delivery_date
             */
            if (l_tsSysTimestamp.before(l_tsOrderStartDateTime)
                || (WEB3DateUtility.compareToDay(l_datSysDate, l_tsOrderEndDateTime) > 0))
            {
                // Property set
                l_adminOffFloorProdGrp.totalApplyQuantity = null;
                l_adminOffFloorProdGrp.totalExecuteQuantity = null;

            }
            // Divergence Flow for others
            else
            {             
                // get市場
                l_finApp = (FinApp) Services.getService(FinApp.class);
                WEB3GentradeFinObjectManager l_gentradeFinObjManager = 
                    (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                Market l_market = l_gentradeFinObjManager.getMarket(
                    l_institution, 
                    l_offFloorOrderProdParams[i].getMarketCode()
                    );
                    
                // get受付終了日時(YYYYMMDD)
                DateFormat l_dFormat = new SimpleDateFormat("yyyyMMdd");
                String l_strOrderEndDatetime = l_dFormat.format(
                    l_offFloorOrderProdParams[i].getOrderEndDatetime());  

                // get立会外分売注文単位( )で取得した注文単位オブジェクトのレコードを昇順に検索し、
                // 2回目以降は前回の検索最終行から検索する。
                BigDecimal l_bdQuantity = new BigDecimal(0.0d);
                BigDecimal l_bdExecQuantity = new BigDecimal(0.0d);

                for (j=k; j < l_eqtypeOrderUnitRows.length; j++)
                {
                    
                    // 取得した注文単位オブジェクト.銘柄ID、市場ID、発注日が
                    // 条件（*1)に一致する場合、注文数量、約定数量のSUM値を求める。
                    //   (*1)銘柄ID = 拡張プロダクトマネージャ.getProduct( )で取得した株式銘柄.銘柄ID
                    //       市場ID = 取得した市場.市場ID
                    //       発注日 = 取得した立会外分売銘柄.受付終了日時のYYYYMMDD
                    if (l_eqtypeOrderUnitRows[j].getProductId() == l_equityProduct.getProductId() &&
                            l_eqtypeOrderUnitRows[j].getMarketId() == l_market.getMarketId() &&
                            l_eqtypeOrderUnitRows[j].getBizDate().equals(l_strOrderEndDatetime))
                    {
                        // Call getQuantity()
                        l_bdQuantity = l_bdQuantity.add(
                            new BigDecimal(l_eqtypeOrderUnitRows[j].getQuantity())
                            );
                        // Call getExecutedQuantity()
                        if (Double.compare(l_eqtypeOrderUnitRows[j].getExecutedQuantity(), Double.NaN) != 0)
                        {
                            l_bdExecQuantity = l_bdExecQuantity.add(
                                new BigDecimal(l_eqtypeOrderUnitRows[j].getExecutedQuantity())
                                );
                        }
                    }
                    // 一致しない場合は処理をぬける。
                    else
                    {
                        k = j;
                        break;
                    }
                }

                // Property Set
                l_adminOffFloorProdGrp.totalApplyQuantity = 
                    l_decFormat.format(l_bdQuantity);
                l_adminOffFloorProdGrp.totalExecuteQuantity = 
                    l_decFormat.format(l_bdExecQuantity);

            }

            /* Call isCanUpdateDeleteOffFloorOrderProduct
             * (l_offFloorOrderProduct : OffFloorOrderProductRow)
             */
            // Property set
            l_adminOffFloorProdGrp.changeDeletePossFlag = 
            l_equityProdManager.isCanUpdateDeleteOffFloorOrderProduct(
                l_offFloorOrderProdParams[i]);

            // Add WEB3AdminOffFloorProductGroup instance to array
            l_adminOffFloorProdGrps[i] = l_adminOffFloorProdGrp;

        }

        //sort管理者立会外分売銘柄明細
        this.sortAdminOffFloorProductGroup(l_adminOffFloorProdGrps,l_request.sortKeys);

        // Set array to response
        l_response.productList = l_adminOffFloorProdGrps;

        log.exiting(STR_METHOD_NAME);

        // Return responce
        return l_response;

    }

    /**
     * (get立会外分売銘柄一覧)<BR>
     * <BR>
     * 【立会外分売銘柄テーブル】より、指定された証券会社分の分売銘柄の一覧を取得する。
     * <BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@立会外分売銘柄テーブルを以下の条件で検索する。<BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数の証券会社.証券会社コード<BR>
     * <BR>
     * 　@※銘柄コード、市場コード、受付終了日時の昇順でソートする。<BR>
     * <BR>
     * ２）　@取得した全レコードの配列を返す。<BR>
     * <BR>
     * @@param l_institution - (証券会社)<BR>
     * <BR>
     * 証券会社オブジェクト。<BR>
     * <BR>
     * l_insitution<BR>
     * <BR>
     * @@author ambha
     * @@return OffFloorOrderProductParams[]
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@exception DataFindException DataFindException
     * @@roseuid 41B6D8A5030B
     */
    protected OffFloorOrderProductParams[] getOffFloorOrderProductList(
        Institution l_institution)
        throws DataFindException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME =
            "getOffFloorOrderProductList(Institution)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        List l_lisSearchResult = null;
        OffFloorOrderProductParams[] l_offFlrOrderProdParams = null;
        StringBuffer l_sbSortCond = new StringBuffer();

        l_queryProcessor = Processors.getDefaultProcessor();

        // Build Where clause.
        String l_strWhere = " institution_code = ?";
        Object[] l_objWhere =
            {
                l_institution.getInstitutionCode()
            };

        // 銘柄コード、市場コード、受付終了日時の昇順をソートに指定。
        l_sbSortCond.append("product_code asc, market_code asc, order_end_datetime asc");

        // Search off_floor_order_product table
        l_lisSearchResult =
            l_queryProcessor.doFindAllQuery(
                OffFloorOrderProductRow.TYPE,
                l_strWhere,
                l_sbSortCond.toString(),
                null,
                l_objWhere);

        l_offFlrOrderProdParams = new OffFloorOrderProductParams[l_lisSearchResult.size()];
        l_lisSearchResult.toArray(l_offFlrOrderProdParams);
        log.exiting(STR_METHOD_NAME);

        // Return arrays of all acquired records
        return l_offFlrOrderProdParams;

    }

    /**
     * (get立会外分売注文単位)<BR>
     * <BR>
     * 指定の分売に対する注文単位オブジェクトを全て取得する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@株式注文単位テーブルを以下の条件で検索する。<BR>
     * 　@[条件]<BR>
     * 　@部店ID in 引数の証券会社に属する部店(*1).部店ID のいずれか<BR>
     * 　@かつ　@注文種別 = "現物買注文"<BR>
     * 　@かつ　@銘柄タイプ = "株式"<BR>
     * 　@かつ　@注文状態 != "発注済（取消注文）"<BR>
     * 　@かつ　@取引コード（SONAR） = "立会外分売"<BR>
     *   かつ　@発注日　@>= "現在日時(*2)（YYYYMMDD)"<BR>
     * <BR>
     * 　@(*1)引数の証券会社.getBranches( )で取得する。<BR>
     *   (*2)GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * 　@※銘柄ID、市場ID、発注日の昇順でソートする。<BR>
     * <BR>
     * ２）　@取得した株式注文単位オブジェクトのArrayListを返却する。<BR>
     * <BR>
     * @@param l_institution - (証券会社)<BR>
     * <BR>
     * 証券会社オブジェクト。<BR>
     * <BR>
     * l_insitution<BR>
     * <BR>
     * @@author ambha
     * @@return EqtypeOrderUnitRow[]
     * @@exception NotFoundException NotFoundException
     * @@exception DataFindException DataFindException
     * @@exception DataNetworkException DataNetworkException
     * @@exception DataQueryException DataQueryException
     * @@roseuid 41BD2A2603E4
     */
    protected EqtypeOrderUnitRow[] getOffFloorOrderUnits(
        Institution l_institution)
        throws NotFoundException, DataFindException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME =
            "getOffFloorOrderUnits(Institution)";
        final int DATE_LENGTH = 10;
        log.entering(STR_METHOD_NAME);

        // Build where cond
        Branch[] l_branches = l_institution.getBranches();
        ArrayList l_container = new ArrayList();
        StringBuffer l_sbWhere = new StringBuffer("");
        StringBuffer l_sbSortCond = new StringBuffer();

        // 部店ＩＤ条件
        l_sbWhere.append(" branch_id in (");
        for (int i = 0; i < l_branches.length; i ++)
        {
            l_container.add(String.valueOf(l_branches[i].getBranchId()));
            if (i == 0)
            {
                l_sbWhere.append("?");
            }
            else
            {
                l_sbWhere.append(",?");
            }
        }
        l_sbWhere.append(")");

        // 注文種別
        l_sbWhere.append(" and order_type = ?");
        l_container.add(OrderTypeEnum.EQUITY_BUY);
        
        // 銘柄タイプ
        l_sbWhere.append(" and product_type = ?");
        l_container.add(ProductTypeEnum.EQUITY);
        
        // 注文状態
        l_sbWhere.append(" and order_status != ?");
        l_container.add(OrderStatusEnum.CANCELLED);
        
        // 取引コード（SONAR）
        l_sbWhere.append(" and sonar_traded_code = ?");
        l_container.add(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
        
        // 発注日
        l_sbWhere.append(" and biz_date >= ?");
        DateFormat l_dFormat = new SimpleDateFormat("yyyyMMdd");
        l_container.add(l_dFormat.format(
            GtlUtils.getSystemTimestamp()));
        
        // 銘柄ID、市場ID、発注日の昇順をソートに指定。
        l_sbSortCond.append("product_id asc, market_id asc, biz_date asc");

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

        // Search eqtype_order_unit table
        List l_orderUnitRows =
            l_queryProcessor.doFindAllQuery(
                EqtypeOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_sbSortCond.toString(),
                null,
                l_container.toArray()
                );

        EqtypeOrderUnitRow[] l_eqtypeOrderUnitRows = null; 
        l_eqtypeOrderUnitRows = new EqtypeOrderUnitRow[l_orderUnitRows.size()];
        l_orderUnitRows.toArray(l_eqtypeOrderUnitRows);    
            
        log.exiting(STR_METHOD_NAME);

        // 取得した株式注文単位オブジェクトの配列を返却する。
        return l_eqtypeOrderUnitRows;

    }
    
    /**
     * (sort管理者立会外分売銘柄明細)<BR>
     * 指定されたソートキー、昇降順にもどついて管理者立会外分売銘柄明細のソートを行う。 <BR>
     * <BR>
     * １）ArrayListを生成する。  <BR>
     * <BR>
     * ２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。 <BR>
     * 　@２－１）ソートキー.キー項目の値に対応する比較項目のComparatorを生成し、 <BR>
     * 　@　@　@ArrayListに追加する。  <BR>
     * <BR>
     * 　@　@　@①@以下の引数にて管理者立会外分売銘柄明細Comparatorを生成する。<BR>
     * <BR>
     * 　@　@　@　@　@[コンストラクタにセットするパラメータ]  <BR>
     * 　@　@　@　@　@　@orderBy： ソートキー.昇順／降順 <BR>
     * 　@　@　@　@　@　@比較項目：　@ソートキー.キー項目 <BR>
     * <BR>
     * 　@　@　@②ArrayListに生成したComparatorを追加する。 <BR>
     * <BR>
     * ３）WEB3ArraysUtility.sort()メソッドをコールする。  <BR>
     * <BR>
     * 　@[sort()にセットするパラメータ]  <BR>
     * 　@　@obj：　@パラメータ.残高照会明細 <BR>
     * 　@　@com：　@生成したArrayList.toArray()の戻り値 <BR>
     * <BR>
     * @@param l_adminOffFloorProductGroup - (管理者立会外分売銘柄明細)<BR>
     * 管理者立会外分売銘柄明細オブジェクトの配列<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * 管理者立会外分売ソートキーの配列<BR>
     * @@roseuid 421979C60148
     */
    protected void sortAdminOffFloorProductGroup(
        WEB3AdminOffFloorProductGroup[] l_adminOffFloorProductGroup,
        WEB3AdminOffFloorSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "sortAdminOffFloorProductGroup";
        log.entering(STR_METHOD_NAME);
        // ArrayListを生成する。
        ArrayList l_lisComparators = new ArrayList();
        
        // パラメータ.ソートキーの要素数分以下の処理を繰り返す。 
        //   ソートキー.キー項目の値に対応する比較項目のComparatorを生成し、 
        //   ArrayListに追加する。  
        int l_intSortKeysLength = 0; 
        if (l_sortKeys != null)
        {
            l_intSortKeysLength = l_sortKeys.length;
        }
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
                l_lisComparators.add(new WEB3AdminOffFloorProductListComparator(
                                         l_sortKeys[i].ascDesc, l_sortKeys[i].keyItem));
        }
        
        // ArrayListに生成したComparatorを追加する。 
        Comparator[] l_comparator = new Comparator[l_lisComparators.size()];
        l_lisComparators.toArray(l_comparator);
        
        // WEB3ArraysUtility.sort()メソッドをコールする。  
        //  [sort()にセットするパラメータ]  
        // 　@ obj：　@パラメータ.管理者立会外分売銘柄明細 
        // 　@ com：　@生成したArrayList.toArray()の戻り値 
        WEB3ArraysUtility.sort(l_adminOffFloorProductGroup,l_comparator);
        log.exiting(STR_METHOD_NAME);

    }

}
@
