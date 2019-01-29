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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O���������ꗗ�T�[�r�X�����N���X(WEB3AdminOffFloorProductListServiceImpl.java)
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
 * (�Ǘ��җ���O���������ꗗ�T�[�r�XImpl)<BR>
 * <BR>
 * �Ǘ��җ���O���������ꗗ�T�[�r�X�����N���X<BR>
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
     * �Ǘ��җ���O���������ꗗ�T�[�r�X���������{����B<BR>
     * <BR>
     * Execute WEB3AdminOffFloorProductListService<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
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
                    "INPUT ���N�G�X�g NOT �Ǘ��җ���O���������ꗗ���N�G�X�g");
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
     * (get�����ꗗ)<BR>
     * <BR>
     * �Ǘ��җ���O�����̖����ꗗ�\���T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��җ���O���������ꗗ�jget�����ꗗ�v�Q�ƁB<BR>
     * <BR>
     * -----<English>----------------------<BR>
     * <BR>
     * getProductList<BR>
     * <BR>
     * Execute WEB3AdminOffFloorProductListService<BR>
     * <BR>
     * Refer to the sequence diagram, "�iadministrator: off floor product
     * list�jgetProductList"<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * ���N�G�X�g�f�[�^<BR>
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
            
        // get����O���������P��
        //  ���Y����O�����ɑ΂��ēo�^����Ă��钍�������擾����B
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
                // get�s��
                l_finApp = (FinApp) Services.getService(FinApp.class);
                WEB3GentradeFinObjectManager l_gentradeFinObjManager = 
                    (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                Market l_market = l_gentradeFinObjManager.getMarket(
                    l_institution, 
                    l_offFloorOrderProdParams[i].getMarketCode()
                    );
                    
                // get��t�I������(YYYYMMDD)
                DateFormat l_dFormat = new SimpleDateFormat("yyyyMMdd");
                String l_strOrderEndDatetime = l_dFormat.format(
                    l_offFloorOrderProdParams[i].getOrderEndDatetime());  

                // get����O���������P��( )�Ŏ擾���������P�ʃI�u�W�F�N�g�̃��R�[�h�������Ɍ������A
                // 2��ڈȍ~�͑O��̌����ŏI�s���猟������B
                BigDecimal l_bdQuantity = new BigDecimal(0.0d);
                BigDecimal l_bdExecQuantity = new BigDecimal(0.0d);

                for (j=k; j < l_eqtypeOrderUnitRows.length; j++)
                {
                    
                    // �擾���������P�ʃI�u�W�F�N�g.����ID�A�s��ID�A��������
                    // �����i*1)�Ɉ�v����ꍇ�A�������ʁA��萔�ʂ�SUM�l�����߂�B
                    //   (*1)����ID = �g���v���_�N�g�}�l�[�W��.getProduct( )�Ŏ擾������������.����ID
                    //       �s��ID = �擾�����s��.�s��ID
                    //       ������ = �擾��������O��������.��t�I��������YYYYMMDD
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
                    // ��v���Ȃ��ꍇ�͏������ʂ���B
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

        //sort�Ǘ��җ���O������������
        this.sortAdminOffFloorProductGroup(l_adminOffFloorProdGrps,l_request.sortKeys);

        // Set array to response
        l_response.productList = l_adminOffFloorProdGrps;

        log.exiting(STR_METHOD_NAME);

        // Return responce
        return l_response;

    }

    /**
     * (get����O���������ꗗ)<BR>
     * <BR>
     * �y����O���������e�[�u���z���A�w�肳�ꂽ�،���Е��̕��������̈ꗗ���擾����B
     * <BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@����O���������e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = �����̏،����.�،���ЃR�[�h<BR>
     * <BR>
     * �@@�������R�[�h�A�s��R�[�h�A��t�I�������̏����Ń\�[�g����B<BR>
     * <BR>
     * �Q�j�@@�擾�����S���R�[�h�̔z���Ԃ��B<BR>
     * <BR>
     * @@param l_institution - (�،����)<BR>
     * <BR>
     * �،���ЃI�u�W�F�N�g�B<BR>
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

        // �����R�[�h�A�s��R�[�h�A��t�I�������̏������\�[�g�Ɏw��B
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
     * (get����O���������P��)<BR>
     * <BR>
     * �w��̕����ɑ΂��钍���P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@���������P�ʃe�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@[����]<BR>
     * �@@���XID in �����̏،���Ђɑ����镔�X(*1).���XID �̂����ꂩ<BR>
     * �@@���@@������� = "����������"<BR>
     * �@@���@@�����^�C�v = "����"<BR>
     * �@@���@@������� != "�����ρi��������j"<BR>
     * �@@���@@����R�[�h�iSONAR�j = "����O����"<BR>
     *   ���@@�������@@>= "���ݓ���(*2)�iYYYYMMDD)"<BR>
     * <BR>
     * �@@(*1)�����̏،����.getBranches( )�Ŏ擾����B<BR>
     *   (*2)GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �@@������ID�A�s��ID�A�������̏����Ń\�[�g����B<BR>
     * <BR>
     * �Q�j�@@�擾�������������P�ʃI�u�W�F�N�g��ArrayList��ԋp����B<BR>
     * <BR>
     * @@param l_institution - (�،����)<BR>
     * <BR>
     * �،���ЃI�u�W�F�N�g�B<BR>
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

        // ���X�h�c����
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

        // �������
        l_sbWhere.append(" and order_type = ?");
        l_container.add(OrderTypeEnum.EQUITY_BUY);
        
        // �����^�C�v
        l_sbWhere.append(" and product_type = ?");
        l_container.add(ProductTypeEnum.EQUITY);
        
        // �������
        l_sbWhere.append(" and order_status != ?");
        l_container.add(OrderStatusEnum.CANCELLED);
        
        // ����R�[�h�iSONAR�j
        l_sbWhere.append(" and sonar_traded_code = ?");
        l_container.add(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
        
        // ������
        l_sbWhere.append(" and biz_date >= ?");
        DateFormat l_dFormat = new SimpleDateFormat("yyyyMMdd");
        l_container.add(l_dFormat.format(
            GtlUtils.getSystemTimestamp()));
        
        // ����ID�A�s��ID�A�������̏������\�[�g�Ɏw��B
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

        // �擾�������������P�ʃI�u�W�F�N�g�̔z���ԋp����B
        return l_eqtypeOrderUnitRows;

    }
    
    /**
     * (sort�Ǘ��җ���O������������)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��ĊǗ��җ���O�����������ׂ̃\�[�g���s���B <BR>
     * <BR>
     * �P�jArrayList�𐶐�����B  <BR>
     * <BR>
     * �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B <BR>
     * �@@�Q�|�P�j�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ������r���ڂ�Comparator�𐶐����A <BR>
     * �@@�@@�@@ArrayList�ɒǉ�����B  <BR>
     * <BR>
     * �@@�@@�@@�@@�ȉ��̈����ɂĊǗ��җ���O������������Comparator�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]  <BR>
     * �@@�@@�@@�@@�@@�@@orderBy�F �\�[�g�L�[.�����^�~�� <BR>
     * �@@�@@�@@�@@�@@�@@��r���ځF�@@�\�[�g�L�[.�L�[���� <BR>
     * <BR>
     * �@@�@@�@@�AArrayList�ɐ�������Comparator��ǉ�����B <BR>
     * <BR>
     * �R�jWEB3ArraysUtility.sort()���\�b�h���R�[������B  <BR>
     * <BR>
     * �@@[sort()�ɃZ�b�g����p�����[�^]  <BR>
     * �@@�@@obj�F�@@�p�����[�^.�c���Ɖ�� <BR>
     * �@@�@@com�F�@@��������ArrayList.toArray()�̖߂�l <BR>
     * <BR>
     * @@param l_adminOffFloorProductGroup - (�Ǘ��җ���O������������)<BR>
     * �Ǘ��җ���O�����������׃I�u�W�F�N�g�̔z��<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �Ǘ��җ���O�����\�[�g�L�[�̔z��<BR>
     * @@roseuid 421979C60148
     */
    protected void sortAdminOffFloorProductGroup(
        WEB3AdminOffFloorProductGroup[] l_adminOffFloorProductGroup,
        WEB3AdminOffFloorSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "sortAdminOffFloorProductGroup";
        log.entering(STR_METHOD_NAME);
        // ArrayList�𐶐�����B
        ArrayList l_lisComparators = new ArrayList();
        
        // �p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B 
        //   �\�[�g�L�[.�L�[���ڂ̒l�ɑΉ������r���ڂ�Comparator�𐶐����A 
        //   ArrayList�ɒǉ�����B  
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
        
        // ArrayList�ɐ�������Comparator��ǉ�����B 
        Comparator[] l_comparator = new Comparator[l_lisComparators.size()];
        l_lisComparators.toArray(l_comparator);
        
        // WEB3ArraysUtility.sort()���\�b�h���R�[������B  
        //  [sort()�ɃZ�b�g����p�����[�^]  
        // �@@ obj�F�@@�p�����[�^.�Ǘ��җ���O������������ 
        // �@@ com�F�@@��������ArrayList.toArray()�̖߂�l 
        WEB3ArraysUtility.sort(l_adminOffFloorProductGroup,l_comparator);
        log.exiting(STR_METHOD_NAME);

    }

}
@
