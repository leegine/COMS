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
filename	WEB3MstkExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����������Ɖ�T�[�r�XImpl(WEB3MstkExecuteReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12  �d��(���u) �V�K�쐬
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityFinTransactionManager;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3MiniClientRequestService;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityOrderTypeDivisionDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.equity.message.WEB3MstkExecuteGroup;
import webbroker3.equity.message.WEB3MstkExecuteReferenceRequest;
import webbroker3.equity.message.WEB3MstkExecuteReferenceResponse;
import webbroker3.equity.message.WEB3MstkProductCodeNameUnit;
import webbroker3.equity.message.WEB3MstkSortKey;
import webbroker3.equity.service.delegate.WEB3MstkExecuteReferenceService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�����~�j�����������Ɖ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �����~�j�����������Ɖ�T�[�r�X�����N���X
 * @@author �d��
 * @@version 1.0
 */
public class WEB3MstkExecuteReferenceServiceImpl extends WEB3MiniClientRequestService implements WEB3MstkExecuteReferenceService 
{
    


    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkExecuteReferenceServiceImpl.class);

    
    /**
     * 
     */
    public WEB3MstkExecuteReferenceServiceImpl() 
    {
     
    }
    
    /**
     * �iexecute�j�B<BR>
     * <BR>
     * �����~�j�����������Ɖ�������{����B<BR>
     * <BR>
     * search�������Ɖ�()���R�[������B
     * @@param l_request (���N�G�X�g)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("l_request is null!");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }        

        WEB3GenResponse l_response = null;

        //search�������Ɖ�
        if (l_request instanceof WEB3MstkExecuteReferenceRequest)
        {
            l_response =
                searchOrderExecuteReference(
                    (WEB3MstkExecuteReferenceRequest)l_request);
        }
        else
        {
            log.error(" __Error[���͒l���s���ł�]");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * �isearch�������Ɖ�j�B<BR>
     * <BR>
     * �����~�j�����������Ɖ�������{����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�~�j���������Ɖ�T�[�r�X�jsearch�������Ɖ�v�Q�ƁB
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �����~�j�����������Ɖ�N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkExecuteReferenceResponse
     */
    protected WEB3MstkExecuteReferenceResponse searchOrderExecuteReference(WEB3MstkExecuteReferenceRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "searchOrderExecuteReference(WEB3MstkExecuteReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);  
        //validate( )  
        l_request.validate();
        
        // get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //(*1) ����ꗗ�̏ꍇ�̂ݏ������{
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_request.referenceType))
        {
            //reset������t�g�����U�N�V����(String)
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
        } 
        //validate�~�j������(�⏕����)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_equityOrderManager = 
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        l_equityOrderManager.validateMiniStockOrder(l_subAccount);
            
        //createResponse( )  
        WEB3MstkExecuteReferenceResponse l_response =
            (WEB3MstkExecuteReferenceResponse)l_request.createResponse();
            
        //is�~�j������I���x��(���X)
        boolean l_blnIsMiniStockSuspension = 
            WEB3GentradeTradingTimeManagement.isMiniStockSuspension(l_subAccount.getWeb3GenBranch());  
            
        //create�����R�[�h����(�����~�j�����������Ɖ�X�|���X, �⏕����, �����~�j�����������Ɖ�N�G�X�g)   
        this.createProductCodeName(l_response, l_subAccount, l_request); 
            
        // create�������Ɖ�(�����~�j�����������Ɖ�X�|���X, �⏕����, �����~�j�����������Ɖ�N�G�X�g)
        this.createOrderExecuteReference(l_response, l_subAccount, l_request); 

        
        //(*2) �v���p�e�B�Z�b�g
		l_response.messageSuspensionFlag = l_blnIsMiniStockSuspension; 
        log.exiting(STR_METHOD_NAME); 
        return l_response;       
    }
    
    /**
     * �icreate�����R�[�h���́j�B<BR>
     * <BR>
     * �@@�w������̕ێ�����~�j�������̖����R�[�h�Ɩ�������<BR>
     * �ꗗ���쐬���A���X�|���X�f�[�^�ɃZ�b�g����B <BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�null���Z�b�g����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�~�j���������Ɖ�T�[�r�X�jcreate�����R�[�h���́v�Q�ƁB
     * @@param l_response (���X�|���X�f�[�^)<BR>
     * �����~�j�����������Ɖ�X�|���X�f�[�^�I�u�W�F�N�g
     * @@param l_subAccount (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �����~�j�����������Ɖ�N�G�X�g�f�[�^�I�u�W�F�N�g
     */
    protected void createProductCodeName(WEB3MstkExecuteReferenceResponse l_response, WEB3GentradeSubAccount l_subAccount, WEB3MstkExecuteReferenceRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
             "createProductCodeName" +
             " (WEB3MstkExecuteReferenceResponse l_response, " +
             "WEB3GentradeSubAccount l_subAccount, " +
             "WEB3MstkExecuteReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //create��������������(String, String, String)
        String l_searchCondCharacterString =
            this.createSearchCondCharacterString(null, l_request.referenceType, null);
        //create���������f�[�^�R���e�i(String, String, String) 
        String[] l_searchCondDataContainer =  
            this.searchCondDataContainer(
                null, l_request.referenceType, null); 
        
        //create�\�[�g����(�����~�j�����\�[�g�L�[[])
        String l_strSortCond = this.createSortCond(
            l_request.sortKeys);   
        
        //get�����P�ʈꗗ(�⏕����, ProductTypeEnum, String, String[], String)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_equityOrderManager = 
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        List l_lisOrderUnits = l_equityOrderManager.getOrderUnits(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            l_searchCondCharacterString,
            l_searchCondDataContainer,
            l_strSortCond);  
        
        Map l_hashMap = new HashMap();
        List l_listNewOrderUnits = new ArrayList();
        int l_intLength = l_lisOrderUnits.size();
        
        //(*1) get�����P�ʈꗗ()�̖߂�l�̐���LOOP����
        for(int i = 0; i < l_intLength; i++)
        {
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOrderUnits.get(i);
            
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
            if(l_hashMap.get("" + l_product.getProductId()) == null)
            {
                //11) �����~�j���������R�[�h����()
                WEB3MstkProductCodeNameUnit l_productCodeNameUnit = 
                    new WEB3MstkProductCodeNameUnit();
                
                //12) (*1.2) �v���p�e�B�Z�b�g
                l_productCodeNameUnit.productCode = l_product.getProductCode();
                EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
                l_productCodeNameUnit.productName = l_productRow.getStandardName();
                
                //put(arg0�i=�����P��.�����h�c.toString()�j : Object, arg1�i=�����~�j���������R�[�h���́j : Object)
                l_hashMap.put("" + l_product.getProductId(), l_productCodeNameUnit); 
                l_listNewOrderUnits.add(l_productCodeNameUnit);                                                                          
            }  
        }
        // (*2) �v���p�e�B�Z�b�g
        if(l_intLength != 0)
        {

        	WEB3MstkProductCodeNameUnit[] l_productCodeNameUnit =
            	new WEB3MstkProductCodeNameUnit[l_listNewOrderUnits.size()];
        	l_listNewOrderUnits.toArray(l_productCodeNameUnit);
        	l_response.productCodeNames = l_productCodeNameUnit;
		}
		else
		{
			l_response.productCodeNames = null;
		}
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * �icreate�������Ɖ�j�B<BR>
     * <BR>�@@
     * �w������̕ێ�����~�j���������A�������Ɖ�ׂ��쐬���A<BR>�@@
     * ���X�|���X�f�[�^�ɃZ�b�g����B <BR>�@@
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�null���Z�b�g����B <BR>�@@
     * <BR>�@@
     * �V�[�P���X�} <BR>�@@
     * �u�i�~�j���������Ɖ�T�[�r�X�jcreate�������Ɖ�v�Q�ƁB�@@
     * @@param l_response (���X�|���X�f�[�^)<BR>
     * �����~�j�����������Ɖ�X�|���X�f�[�^�I�u�W�F�N�g
     * @@param l_subAccount (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �����~�j�����������Ɖ�N�G�X�g�f�[�^�I�u�W�F�N�g
     */
    protected void createOrderExecuteReference(WEB3MstkExecuteReferenceResponse l_response, WEB3GentradeSubAccount l_subAccount, WEB3MstkExecuteReferenceRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "createOrderExecuteReference(WEB3MstkExecuteReferenceResponse l_response, " +
            "WEB3GentradeSubAccount l_subAccount, WEB3MstkExecuteReferenceRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        //2. create��������������(String, String, String)
        String l_searchCondCharacterString =
            this.createSearchCondCharacterString(
                l_request.productCode,
                l_request.referenceType,
                l_request.miOrderState);
              
        //3. create���������f�[�^�R���e�i(String, String, String)         
        String[] l_searchCondDataContainer =  
            this.searchCondDataContainer(
                l_request.productCode,
                l_request.referenceType,
                l_request.miOrderState
                );
        //4. create�\�[�g����(�����~�j�����\�[�g�L�[[])
        String l_strSortCond = this.createSortCond(
            l_request.sortKeys);
            
        //5. get�����P�ʈꗗ(�⏕����, ProductTypeEnum, String, String[], String)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_equityOrderManager = 
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        List l_lisOrderUnits = 
            l_equityOrderManager.getOrderUnits(
                l_subAccount, 
                ProductTypeEnum.EQUITY, 
                l_searchCondCharacterString, 
                l_searchCondDataContainer, 
                l_strSortCond);    
                           
        //6. ArrayList( )
        ArrayList l_lisArrayList = new ArrayList(); 
              
        //7. (*1) get�����P�ʈꗗ()�̖߂�l�̂����A�\���Ώۍs�ifromIndex �` toIndex) 
        int l_intPageSize = Integer.parseInt(l_request.pageSize);//�y�[�W���\���s��
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);//�v���y�[�W�ԍ�
        
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_lisOrderUnits, l_intPageIndex, l_intPageSize);
        
        List l_lisTemp = l_pageIndexInfo.getListReturned();
        
        for(int i = 0; i < l_lisTemp.size(); i++)
        {

            //�����P�ʂ̎擾
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisTemp.get(i);
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //8. getOrderId( )
            long l_lngOrderId = l_orderUnit.getOrderId(); 
            
            //9. getProduct( )         
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
            EqtypeProductRow l_ProductRow =
                            (EqtypeProductRow)l_product.getDataSourceObject();
                            
            //10. getTradedProduct( )
            TradedProduct l_tradedProduct = null;
            try
            {
                l_tradedProduct = l_orderUnit.getTradedProduct();
            }
            catch (RuntimeSystemException l_rse) {}
            
            //11. getSide( )
            SideEnum l_side = l_orderUnit.getSide();
            
            //12. getQuantity( )
            double l_dblQuantiy = l_orderUnit.getQuantity();
            
            //13. getExecutedQuantity( )
            double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
                        
            //14. is����\�����P�ʁi�~�j��)
            boolean l_blnIsMiniStockCancelOrderUnit = 
                l_equityOrderManager.isMiniStockCancelOrderUnit(l_orderUnit);
            
            //15. getExecutions( )
            OrderExecution[] l_execution = l_orderUnit.getExecutions();
            //16. get�g�����U�N�V����(�����P��)
            WEB3EquityFinTransactionManager l_equityFinTransactionManager = 
                (WEB3EquityFinTransactionManager)l_finApp.getTradingModule
                (ProductTypeEnum.EQUITY).getFinTransactionManager();
                
            List l_lisTransactions = l_equityFinTransactionManager.getTransactions(l_orderUnit);
            
         
            
            
            //17. �����~�j����������薾��( )
            WEB3MstkExecuteGroup l_group = new WEB3MstkExecuteGroup();
            
            //18. (*1.1) �v���p�e�B�Z�b�g

                
            l_group.id = Long.toString(l_lngOrderId);
            
            l_group.productCode = l_ProductRow.getProductCode();
            
            l_group.productName = l_ProductRow.getStandardName();
            
            if (l_tradedProduct == null)
            {
                l_group.marketCode = null;
            }
            else
            {
                l_group.marketCode = l_tradedProduct.getMarket().getMarketCode();
            }
            
            if ((SideEnum.BUY).equals(l_side))
            {
                l_group.dealingType = "" + (SideEnum.BUY.intValue());
            }
            else
            {
                l_group.dealingType = "" + (SideEnum.SELL.intValue());
            }
            String l_strQuantiy = WEB3StringTypeUtility.formatNumber(l_dblQuantiy);

            l_group.orderQuantity = l_strQuantiy;
            
            l_group.orderDate = l_orderUnitRow.getReceivedDateTime();
             
            Date l_orderBizDateDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");                
            l_group.orderExecuteDate = WEB3DateUtility.toDay(l_orderBizDateDate);
            
            if (OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus()))
            {
                l_group.miOrderState = WEB3EquityOrderTypeDivisionDef.ORDRING;   
            }
            
            if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
                && l_orderUnit.getQuantity() != 0 )
            {
                l_group.miOrderState = WEB3EquityOrderTypeDivisionDef.EXECUTED;   
            }
            
            if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
                && OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
            {
                l_group.miOrderState = WEB3EquityOrderTypeDivisionDef.ORDERED;    
            }
            if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
                && OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
            {
                l_group.miOrderState = WEB3EquityOrderTypeDivisionDef.CANCELED;    
            }
            
            if (l_tradedProduct == null)
            {
                l_group.cancelFlag = false;
            }
            else
            {
                l_group.cancelFlag = l_blnIsMiniStockCancelOrderUnit;
            }
            
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;
            }

            if (l_dblExecutedQuantity != 0)
            {
                l_group.executionTimestamp = l_execution[0].getExecutionTimestamp();
                l_group.deliveryDate = l_execution[0].getDeliveryDate();
                l_group.execQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
                EqtypeFinTransactionRow l_eqtypeFinTransactionRow = 
                             (EqtypeFinTransactionRow)l_lisTransactions.get(0);
                l_group.execPrice = 
                    WEB3StringTypeUtility.formatNumber(l_execution[0].getExecutionPrice());
                double l_dblNetAmount = l_eqtypeFinTransactionRow.getNetAmount();
				if ((SideEnum.BUY).equals(l_side))
                {
					l_dblNetAmount = l_dblNetAmount * (-1);
                }
                l_group.deliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblNetAmount);
            }
            
            if (l_dblExecutedQuantity == 0)
            {
                l_group.executionTimestamp = null;
                l_group.deliveryDate = null;
                l_group.execQuantity = null;
                l_group.execPrice = null;
                l_group.deliveryPrice = null;
            }
            //19. add(arg0�i=�����~�j����������薾�ׁj
            l_lisArrayList.add(l_group);
        }
            //20. toArray( )
            //21. (*2) �v���p�e�B�Z�b�g 
        if(l_lisTemp.size() != 0)
        {
        	WEB3MstkExecuteGroup[] l_productCodeNameUnit = 
            	new WEB3MstkExecuteGroup[l_lisArrayList.size()];
           	 	l_lisArrayList.toArray(l_productCodeNameUnit);       
            	l_response.executeGroups = l_productCodeNameUnit;
		}
		else
		{
			l_response.executeGroups = null;
		}
        //(�\���y�[�W�ԍ�)
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + ""; 
        //���y�[�W��
        l_response.totalPages= l_pageIndexInfo.getTotalPages() + ""; 
        //�����R�[�h��
        l_response.totalRecords= l_pageIndexInfo.getTotalRecords() + ""; 
    }
    
    /**
     * �icreate��������������j�B<BR>
     * <BR>
     * ���������������ҏW����B<BR>
     * <BR>
     * �P�j�@@�߂�l����<BR>
     * �@@�߂�l�̌�������������C���X�^���X�i�FString�j�𐶐� <BR>
     * <BR>
     * �Q�j�@@������ʏ����ǉ�<BR>
     * �@@������ʂ̎w�蕶�������������������ɒǉ�<BR>
     * <BR>
     * �@@" and order_type in (*���P, *���Q)"<BR>
     * <BR>
     * <BR>�@@---------------------------------------------------------<BR>
     * �@@*���P�F�@@OrderTypeEnum.MINI_STOCK_BUY<BR>
     * �i101�F�����~�j�����������j�@@�𕶎���ɕϊ������l<BR>
     * �@@*���Q�F�@@OrderTypeEnum.MINI_STOCK_SELL<BR>
     * �i102�F�����~�j�����������j�@@�𕶎���ɕϊ������l<BR>
     * <BR>�@@---------------------------------------------------------<BR>
     * <BR>
     * �R�j�@@�����������ǉ�<BR>
     * �@@�������͈̔͂𕶎���C���X�^���X�ɐݒ� <BR>
     * <BR>
     * �@@�|�������Ɖ�̏ꍇ�i�Ɖ�敪 == 0�F�������Ɖ�j<BR>
     * �@@�@@�@@" and biz_date >= ?" <BR>
     * <BR>
     * �@@�|����ꗗ�̏ꍇ�i�Ɖ�敪 == 1�F��������ꗗ�j<BR>
     * �@@�@@�@@" and biz_date = ?" <BR>
     * <BR>
     * �S�j�@@���������ǉ��i�� �����R�[�h�w��i�����R�[�h != null�j<BR>
     * �̏ꍇ�̂݁j<BR>
     * �@@�����h�c�w���ǉ�����B<BR>
     * <BR>
     * �@@" and product_id = ?" <BR>
     * <BR>
     * �T�j�@@�����󋵏����ǉ�<BR>
     * <BR>
     * �@@���@@����ꗗ�܂��́A�������w��̏ꍇ<BR>
     * �i�Ɖ�敪 == 1�F��������ꗗ Or �����󋵋敪 == 0�F�������j<BR>
     * �@@�@@�@@�������w���ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@" and order_open_status = *���R "<BR>
     * <BR>
     * �@@���@@���ώw��̏ꍇ�i�����󋵋敪 == �P�F���ρj<BR>
     * �@@�@@�@@���ώw���ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@" and order_open_status = *���S and executed_quantity != 0 "<BR>
     * <BR>
     * �@@���@@�����ώw��̏ꍇ�i�����󋵋敪 == �Q�F�����ρj<BR>
     * �@@�@@�@@�����ώw���ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@" and order_open_status = *���S and expiration_status = <BR>
     *���T "<BR>
     * <BR>
     * �@@���@@����ώw��̏ꍇ�i�����󋵋敪 == �R�F����ρj<BR>
     * �@@�@@�@@����ώw���ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@" and order_open_status = *���S and order_status = *���U "<BR>
     * <BR>
     * <BR>�@@
     * -------------------------------------------------------<BR>
     * �@@*���R�F�@@OrderOpenStatusEnum.OPEN�i1�F�I�[�v���j<BR>
     * �𕶎���ɕϊ������l<BR>
     * �@@*���S�F�@@OrderOpenStatusEnum.CLOSE�i2�F�N���[�Y�j<BR>
     * �𕶎���ɕϊ������l<BR>
     * �@@*���T�F�@@OrderExpirationStatusEnum.INVALIDATED_BY_MKT<BR>
     * �i3�F�}�[�P�b�g���ہj�𕶎���ɕϊ������l<BR>
     * �@@*���U�F�@@OrderStatusEnum.CANCELLED�i14�F�����ρi��������j�j<BR>
     * �𕶎���ɕϊ������l<BR>
     * <BR>
     * -------------------------------------------------------<BR>
     * <BR>
     * �U�j�@@������C���X�^���X��ԋp
     * @@param l_strProductCode (�����R�[�h)
     * @@param l_strReferenceDivision (�Ɖ�敪)<BR>
     * 0�F�������Ɖ�i�f�t�H���g�j <BR>
     * 1�F��������ꗗ�i��������\�Ȃ��̂̂ݕ\���j
     * @@param l_strOrderSituationDivision (�����󋵋敪)<BR>
     * null�F �w�薳��<BR>
     * 0�F�������@@1�F���ρ@@2�F�����ρ@@3�F�����
     * @@return String
     */
    protected String createSearchCondCharacterString(String l_strProductCode, String l_strReferenceDivision, String l_strOrderSituationDivision) 
    {
        final String STR_METHOD_NAME =
            "createSearchCondCharacter" +
            "(String l_strProductCode, String l_strReferenceDivision, String l_strOrderSituationDivision)";
        log.entering(STR_METHOD_NAME);
        String l_strSearchCond;
        //�Q�j�@@������ʏ����ǉ�<BR>
        // �@@������ʂ̎w�蕶�������������������ɒǉ�<BR>
        // �@@" and order_type in (*���P, *���Q)"<BR>
        l_strSearchCond 
            = " order_type in ( " + OrderTypeEnum.MINI_STOCK_BUY.intValue() + 
                "," + OrderTypeEnum.MINI_STOCK_SELL.intValue() + " )";
            
        //     * �R�j�@@�����������ǉ�<BR>
        // �@@�������͈̔͂𕶎���C���X�^���X�ɐݒ� <BR>
        // <BR>
        // �@@�|�������Ɖ�̏ꍇ�i�Ɖ�敪 == 0�F�������Ɖ�j<BR>
        // �@@�@@�@@" and biz_date >= ?" <BR>
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(l_strReferenceDivision))
        {
            l_strSearchCond 
                += " and biz_date >= ?";
        }
        // �@@�|����ꗗ�̏ꍇ�i�Ɖ�敪 == 1�F��������ꗗ�j<BR>
        // �@@�@@�@@" and biz_date = ?" <BR>
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_strReferenceDivision))
        {
            l_strSearchCond
                += " and biz_date = ?";
        }
        //     * �S�j�@@���������ǉ��i�� �����R�[�h�w��i�����R�[�h != null�j<BR>
        // �̏ꍇ�̂݁j<BR>
        // �@@�����h�c�w���ǉ�����B<BR>
        // <BR>
        // �@@" and product_id = ?" <BR>
        // <BR>
        //����ID�w���ǉ��i�����R�[�h�ɑΉ��������ID�Ō������s��)
        if (l_strProductCode != null && !("").equals(l_strProductCode))
        {
            //����ID�w���ǉ�����
            l_strSearchCond 
                += " and product_id = ?";
        }
        // �T�j�@@�����󋵏����ǉ�<BR>
        // <BR>
        // �@@���@@����ꗗ�܂��́A�������w��̏ꍇ<BR>
        // �i�Ɖ�敪 == 1�F��������ꗗ Or �����󋵋敪 == 0�F�������j<BR>
        // �@@�@@�@@�������w���ǉ�����B<BR>
        // <BR>
        // �@@�@@�@@" and order_open_status = *���R "<BR>
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_strReferenceDivision)
            || WEB3EquityOrderTypeDivisionDef.ORDRING.equals(l_strOrderSituationDivision))
        {
            l_strSearchCond +=" and order_open_status = " + OrderOpenStatusEnum.OPEN.intValue();
        }
        // �@@���@@���ώw��̏ꍇ�i�����󋵋敪 == �P�F���ρj<BR>
        // �@@�@@�@@���ώw���ǉ�����B<BR>
        // <BR>
        // �@@�@@�@@" and order_open_status = *���S and executed_quantity != 0 "<BR>
        if (WEB3EquityOrderTypeDivisionDef.EXECUTED.equals(l_strOrderSituationDivision))
        {
            l_strSearchCond
                += " and order_open_status = " 
                + OrderOpenStatusEnum.CLOSED.intValue() + " and  executed_quantity != 0 ";
        }
        //     * �@@���@@�����ώw��̏ꍇ�i�����󋵋敪 == �Q�F�����ρj<BR>
        // �@@�@@�@@�����ώw���ǉ�����B<BR>
        // <BR>
        // �@@�@@�@@" and order_open_status = *���S and expiration_status = ���T " <BR>
        if (WEB3EquityOrderTypeDivisionDef.ORDERED.equals(l_strOrderSituationDivision))
        {
            l_strSearchCond
                += " and order_open_status = " + OrderOpenStatusEnum.CLOSED.intValue() 
                + " and expiration_status = " + OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue();
        }
        // <BR>
        // �@@���@@����ώw��̏ꍇ�i�����󋵋敪 == �R�F����ρj<BR>
        // �@@�@@�@@����ώw���ǉ�����B<BR>
        // <BR>
        // �@@�@@�@@" and order_open_status = *���S and order_status = *���U "<BR>
        // <BR>
        if (WEB3EquityOrderTypeDivisionDef.CANCELED.equals(l_strOrderSituationDivision))
        {
            l_strSearchCond
                += " and order_open_status = " + OrderOpenStatusEnum.CLOSED.intValue() + 
                " and order_status = " + OrderStatusEnum.CANCELLED.intValue();
        }
        
        
        return l_strSearchCond;
    }
    
    /**
     * �icreate���������f�[�^�R���e�i�j�B<BR>
     * <BR>
     * �f�[�^�R���e�i������̔z���ҏW����B<BR>
     * <BR>
     * �ȉ��̒ʂ�A�f�[�^�R���e�i�z��i�FString[]�j��ԋp����B <BR>
     * <BR>
     * ���@@�����R�[�h�w��łȂ��i�����R�[�h == null�j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�f�[�^�R���e�i[0]�i�������w��j = (*���P)<BR>
     * <BR>
     * ���@@�����R�[�h�w��i�����R�[�h != null�j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�f�[�^�R���e�i[0]�i�������w��j = (*���P)<BR>
     * �@@�@@�f�[�^�R���e�i[1]�i�����R�[�h�w��j =<BR>
     *  �i�����R�[�h�ɊY����������h�c���j<BR>
     * <BR>
     * �@@�@@���@@�����R�[�h�ɊY����������h�c<BR>
     * �@@�@@�v���_�N�g�}�l�[�W��.get����<BR> 
     * �i�،����(*���Q), �����R�[�h�j.getProductId()<BR>
     * <BR>
     * <BR>�@@
     * ---------------------------------------------------------<BR>
     * �@@*���P�@@�������w��̃Z�b�g<BR>
     * �@@�|�������Ɖ�̏ꍇ�i�Ɖ�敪 == 0�F�������Ɖ�j<BR>
     * �@@�@@�@@�{���������̑O�c�Ɠ�<BR>
     * �i��������ԊǗ�.get������()�̑O�c�Ɠ��j<BR>
     * <BR>
     * �@@�|����ꗗ�̏ꍇ�i�Ɖ�敪 == 1�F��������ꗗ�j<BR>
     * �@@�@@�@@�{���������i��������ԊǗ�.get������()�j<BR>
     * <BR>
     * �@@*���Q�@@�،���ЃI�u�W�F�N�g�́A�⏕����.getInstitution( )<BR>
     * �Ŏ擾���ݒ� <BR>
     * <BR>
     * --------------------------------------------------------
     * @@param l_strProductCode (�����R�[�h)
     * @@param l_strReferenceDivision (�Ɖ�敪)
     * @@param l_strOrderSituationDivision (�����󋵋敪)<BR>
     * null�F �w�薳��<BR>
     * 0�F�������@@1�F���ρ@@2�F�����ρ@@3�F�����
     * @@return String[]
     */
    protected String[] searchCondDataContainer(String l_strProductCode, String l_strReferenceDivision,  String l_strOrderSituationDivision) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME =
            "searchCondDataContainer(String l_strProductCode, String l_strOrderSituationDivision)";
        log.entering(STR_METHOD_NAME);
        List l_lisSearchCond = new Vector();
        String[] l_strsearchCond = null;
        //     * ���@@�����R�[�h�w��łȂ��i�����R�[�h == null�j�̏ꍇ<BR>
        // <BR>
        //�@@�@@�f�[�^�R���e�i[0]�i�������w��j = (*���P)<BR>
        try
        {

            if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(l_strReferenceDivision))
            {
                Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                WEB3GentradeBizDate l_bizDateCalcUtil = new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));
                Timestamp l_tsDevidendRightDate = l_bizDateCalcUtil.roll(-1);
                l_lisSearchCond.add(WEB3DateUtility.formatDate(l_tsDevidendRightDate, "yyyyMMdd"));       
                log.debug("l_bizDateCalcUtil" + l_bizDateCalcUtil);      
                log.debug("l_datOrderBizDate" + l_datOrderBizDate);               
            }    

            if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_strReferenceDivision))
            {
                l_lisSearchCond.add(WEB3DateUtility.formatDate
                    (WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd"));                   
            }            

            //     * ���@@�����R�[�h�w��i�����R�[�h != null�j�̏ꍇ<BR>
            // <BR>
            // �@@�@@�f�[�^�R���e�i[0]�i�������w��j = (*���P)<BR>
            // �@@�@@�f�[�^�R���e�i[1]�i�����R�[�h�w��j =<BR>
            //  �i�����R�[�h�ɊY����������h�c���j<BR>
            // <BR>
            if (l_strProductCode != null)
            {

                //     �����R�[�h�ɊY����������h�c<BR>
                // �@@�@@�v���_�N�g�}�l�[�W��.get����<BR> 
                // �i�،����(*���Q), �����R�[�h�j.getProductId()<BR>
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
                
                WEB3EquityProductManager l_equityProductManager = 
                    (WEB3EquityProductManager)
                        l_finApp.getTradingModule
                        (ProductTypeEnum.EQUITY).getProductManager();
                WEB3GentradeSubAccount l_subAccount =
                    (WEB3GentradeSubAccount)this.getSubAccount(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);   
                              
         
                long l_productId = l_equityProductManager.getProduct(
                        l_subAccount.getInstitution(),
                        l_strProductCode).getProductId();
                log.debug("l_productId =" + l_productId);
                log.debug("l_subAccount.getInstitution() =" + l_subAccount.getInstitution());
                l_lisSearchCond.add(l_productId + "");       
            }
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00717,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }            
        if(l_lisSearchCond != null && l_lisSearchCond.size() != 0)
        {
            l_strsearchCond = new String[l_lisSearchCond.size()];
            l_lisSearchCond.toArray(l_strsearchCond);
        }
        log.exiting(STR_METHOD_NAME);      
        return l_strsearchCond;
    }
    
    /**
     * �icreate�\�[�g�����j�B<BR>
     * <BR>
     * �\�[�g�����iSortKeySpec�j�������ҏW����B<BR>
     * <BR>
     * �P�j�@@�\�[�g�L�[�����ҏW<BR>
     * �@@�����~�j�����\�[�g�L�[.�L�[���ڂ̐����A�Ή�����e�[�u����<BR>
     * �񕨗����������^�~���w���t�����ăZ�b�g����B<BR>
     * �A���A�w��Ȃ��i�����~�j�����\�[�g�L�[ == null�j<BR>
     * �̏ꍇ�A�����R�[�h�̏����Ƃ���B<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ� <BR>
     * �@@�@@�@@���e�[�u�����F�����P�ʃe�[�u��(eqtype_order_unit) <BR>
     * �@@�@@�@@���L�[���ڕ�����i�V���{�����j�́A���b�Z�[�W��`�����Q�� <BR>
     * �@@�@@�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q�� <BR>
     * <BR>
     * �@@�@@�@@�ϊ��O �@@�@@�@@�@@�@@   �ϊ��� <BR>
     * �@@�@@�@@-------------   ----------------------------- <BR>
     * �@@�@@�@@�����R�[�h        �F�����P�ʃe�[�u���D����ID <BR>
     * �@@�@@�@@�s��R�[�h        �F�����P�ʃe�[�u���D�s��ID <BR>
     * �@@�@@�@@�����敪         �F�����P�ʃe�[�u���D������� <BR>
     * �@@�@@�@@��������         �F�����P�ʃe�[�u���D�󒍓��� <BR>
     * <BR>
     * �@@�@@�E�����^�~���w��́A�M�p����\�[�g�L�[.�����^�~�� <BR>
     * �w��ɏ]���ݒ� <BR>
     * <BR>
     * �Q�j�@@�ǉ������ҏW<BR>
     * �@@�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt�����A<BR>
     * �\�[�g�����������ԋp����B
     * @@param l_mstkSortKey (�����~�j�����\�[�g�L�[)<BR>
     * �����~�j�����\�[�g�L�[�I�u�W�F�N�g
     * @@return String
     */
    protected String createSortCond(WEB3MstkSortKey[] l_mstkSortKey) 
    {
        final String STR_METHOD_NAME =
            "createSortCond(WEB3MstkSortKey[] l_mstkSortKey)";

        log.entering(STR_METHOD_NAME);
        StringBuffer l_strReturn = new StringBuffer();
        if (l_mstkSortKey == null)
        {
            l_strReturn.append("product_id");
            l_strReturn.append(" ");
            l_strReturn.append("ASC");
			//(2)�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt��
			l_strReturn.append(", ");
			l_strReturn.append("last_updated_timestamp");
			l_strReturn.append(" ");
			l_strReturn.append("ASC");
			log.exiting(STR_METHOD_NAME);
			return l_strReturn.toString();
        }
        int l_length = 0; 
        if(l_mstkSortKey != null)
        {
             l_length = l_mstkSortKey.length;
        }

        for (int i = 0; i < l_length; i++)
        {
            //�����R�[�h�F�����P�ʃe�[�u���D����ID         
            if (l_mstkSortKey[i]
                .keyItem
                .equals(WEB3EquityKeyItemDef.PRODUCTCODE))
            {
                if (l_strReturn.length() != 0) 
                {
                    l_strReturn.append(", ");
                }
                l_strReturn.append("product_id");
            }

            //�s��R�[�h�F�����P�ʃe�[�u���D�s��ID                         
            else if (
                l_mstkSortKey[i].keyItem.equals(
                    WEB3EquityKeyItemDef.TRADEMARKET))
            {
                if (l_strReturn.length() != 0) 
                {
                    l_strReturn.append(", ");
                }
                l_strReturn.append("market_id");
            }
            //�����敪�F�����P�ʃe�[�u���D�������                                     
            else if (
                l_mstkSortKey[i].keyItem.equals(
                    WEB3EquityKeyItemDef.DEALINGTYPE))
            {
                if (l_strReturn.length() != 0) 
                {
                    l_strReturn.append(", ");
                }
                l_strReturn.append("order_type");
            }

            //���������F�����P�ʃe�[�u���D�󒍓���
            else if (
                l_mstkSortKey[i].keyItem.equals(
                    WEB3EquityKeyItemDef.ORDER_TIME))
            {
                if (l_strReturn.length() != 0) 
                {
                    l_strReturn.append(", ");
                }
                l_strReturn.append("received_date_time");
            }
 
            else
            {
                continue;
            }
            l_strReturn.append(" ");
            if (WEB3AscDescDef.ASC.equals(l_mstkSortKey[i].ascDesc))
            {
                l_strReturn.append("ASC");
            }
            else
            {
                l_strReturn.append("DESC");
            }
           
        }
        //(2)�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt��
        l_strReturn.append(", ");
        l_strReturn.append("last_updated_timestamp");
        l_strReturn.append(" ");
        l_strReturn.append("ASC");
        log.exiting(STR_METHOD_NAME);
        return l_strReturn.toString();
     
    }
}
@
