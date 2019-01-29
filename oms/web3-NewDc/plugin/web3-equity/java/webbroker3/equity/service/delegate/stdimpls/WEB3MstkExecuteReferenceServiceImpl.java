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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文約定照会サービスImpl(WEB3MstkExecuteReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12  彭巍(中訊) 新規作成
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
 * （株式ミニ投資注文約定照会サービスImpl）。<BR>
 * <BR>
 * 株式ミニ投資注文約定照会サービス実装クラス
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3MstkExecuteReferenceServiceImpl extends WEB3MiniClientRequestService implements WEB3MstkExecuteReferenceService 
{
    


    /**
     * （ログ出力ユーティリティ）。
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
     * （execute）。<BR>
     * <BR>
     * 株式ミニ投資注文約定照会処理を実施する。<BR>
     * <BR>
     * search注文約定照会()をコールする。
     * @@param l_request (リクエスト)
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

        //search注文約定照会
        if (l_request instanceof WEB3MstkExecuteReferenceRequest)
        {
            l_response =
                searchOrderExecuteReference(
                    (WEB3MstkExecuteReferenceRequest)l_request);
        }
        else
        {
            log.error(" __Error[入力値が不正です]");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * （search注文約定照会）。<BR>
     * <BR>
     * 株式ミニ投資注文約定照会処理を実施する。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（ミニ株注文約定照会サービス）search注文約定照会」参照。
     * @@param l_request (リクエストデータ)<BR>
     * 株式ミニ投資注文約定照会リクエストデータオブジェクト
     * @@return WEB3MstkExecuteReferenceResponse
     */
    protected WEB3MstkExecuteReferenceResponse searchOrderExecuteReference(WEB3MstkExecuteReferenceRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "searchOrderExecuteReference(WEB3MstkExecuteReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);  
        //validate( )  
        l_request.validate();
        
        // get補助口座( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //(*1) 取消一覧の場合のみ処理実施
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_request.referenceType))
        {
            //reset注文受付トランザクション(String)
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
        } 
        //validateミニ株注文(補助口座)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_equityOrderManager = 
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        l_equityOrderManager.validateMiniStockOrder(l_subAccount);
            
        //createResponse( )  
        WEB3MstkExecuteReferenceResponse l_response =
            (WEB3MstkExecuteReferenceResponse)l_request.createResponse();
            
        //isミニ株取引終了警告(部店)
        boolean l_blnIsMiniStockSuspension = 
            WEB3GentradeTradingTimeManagement.isMiniStockSuspension(l_subAccount.getWeb3GenBranch());  
            
        //create銘柄コード名称(株式ミニ投資注文約定照会レスポンス, 補助口座, 株式ミニ投資注文約定照会リクエスト)   
        this.createProductCodeName(l_response, l_subAccount, l_request); 
            
        // create注文約定照会(株式ミニ投資注文約定照会レスポンス, 補助口座, 株式ミニ投資注文約定照会リクエスト)
        this.createOrderExecuteReference(l_response, l_subAccount, l_request); 

        
        //(*2) プロパティセット
		l_response.messageSuspensionFlag = l_blnIsMiniStockSuspension; 
        log.exiting(STR_METHOD_NAME); 
        return l_response;       
    }
    
    /**
     * （create銘柄コード名称）。<BR>
     * <BR>
     * 　@指定口座の保持するミニ株注文の銘柄コードと銘柄名の<BR>
     * 一覧を作成し、レスポンスデータにセットする。 <BR>
     * 該当データが存在しない場合にはnullをセットする。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（ミニ株注文約定照会サービス）create銘柄コード名称」参照。
     * @@param l_response (レスポンスデータ)<BR>
     * 株式ミニ投資注文約定照会レスポンスデータオブジェクト
     * @@param l_subAccount (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_request (リクエストデータ)<BR>
     * 株式ミニ投資注文約定照会リクエストデータオブジェクト
     */
    protected void createProductCodeName(WEB3MstkExecuteReferenceResponse l_response, WEB3GentradeSubAccount l_subAccount, WEB3MstkExecuteReferenceRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
             "createProductCodeName" +
             " (WEB3MstkExecuteReferenceResponse l_response, " +
             "WEB3GentradeSubAccount l_subAccount, " +
             "WEB3MstkExecuteReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //create検索条件文字列(String, String, String)
        String l_searchCondCharacterString =
            this.createSearchCondCharacterString(null, l_request.referenceType, null);
        //create検索条件データコンテナ(String, String, String) 
        String[] l_searchCondDataContainer =  
            this.searchCondDataContainer(
                null, l_request.referenceType, null); 
        
        //createソート条件(株式ミニ投資ソートキー[])
        String l_strSortCond = this.createSortCond(
            l_request.sortKeys);   
        
        //get注文単位一覧(補助口座, ProductTypeEnum, String, String[], String)
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
        
        //(*1) get注文単位一覧()の戻り値の数分LOOP処理
        for(int i = 0; i < l_intLength; i++)
        {
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOrderUnits.get(i);
            
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
            if(l_hashMap.get("" + l_product.getProductId()) == null)
            {
                //11) 株式ミニ投資銘柄コード名称()
                WEB3MstkProductCodeNameUnit l_productCodeNameUnit = 
                    new WEB3MstkProductCodeNameUnit();
                
                //12) (*1.2) プロパティセット
                l_productCodeNameUnit.productCode = l_product.getProductCode();
                EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
                l_productCodeNameUnit.productName = l_productRow.getStandardName();
                
                //put(arg0（=注文単位.銘柄ＩＤ.toString()） : Object, arg1（=株式ミニ投資銘柄コード名称） : Object)
                l_hashMap.put("" + l_product.getProductId(), l_productCodeNameUnit); 
                l_listNewOrderUnits.add(l_productCodeNameUnit);                                                                          
            }  
        }
        // (*2) プロパティセット
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
     * （create注文約定照会）。<BR>
     * <BR>　@
     * 指定口座の保持するミニ株注文より、注文約定照会明細を作成し、<BR>　@
     * レスポンスデータにセットする。 <BR>　@
     * 該当データが存在しない場合にはnullをセットする。 <BR>　@
     * <BR>　@
     * シーケンス図 <BR>　@
     * 「（ミニ株注文約定照会サービス）create注文約定照会」参照。　@
     * @@param l_response (レスポンスデータ)<BR>
     * 株式ミニ投資注文約定照会レスポンスデータオブジェクト
     * @@param l_subAccount (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_request (リクエストデータ)<BR>
     * 株式ミニ投資注文約定照会リクエストデータオブジェクト
     */
    protected void createOrderExecuteReference(WEB3MstkExecuteReferenceResponse l_response, WEB3GentradeSubAccount l_subAccount, WEB3MstkExecuteReferenceRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "createOrderExecuteReference(WEB3MstkExecuteReferenceResponse l_response, " +
            "WEB3GentradeSubAccount l_subAccount, WEB3MstkExecuteReferenceRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        //2. create検索条件文字列(String, String, String)
        String l_searchCondCharacterString =
            this.createSearchCondCharacterString(
                l_request.productCode,
                l_request.referenceType,
                l_request.miOrderState);
              
        //3. create検索条件データコンテナ(String, String, String)         
        String[] l_searchCondDataContainer =  
            this.searchCondDataContainer(
                l_request.productCode,
                l_request.referenceType,
                l_request.miOrderState
                );
        //4. createソート条件(株式ミニ投資ソートキー[])
        String l_strSortCond = this.createSortCond(
            l_request.sortKeys);
            
        //5. get注文単位一覧(補助口座, ProductTypeEnum, String, String[], String)
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
              
        //7. (*1) get注文単位一覧()の戻り値のうち、表示対象行（fromIndex ～ toIndex) 
        int l_intPageSize = Integer.parseInt(l_request.pageSize);//ページ内表示行数
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);//要求ページ番号
        
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_lisOrderUnits, l_intPageIndex, l_intPageSize);
        
        List l_lisTemp = l_pageIndexInfo.getListReturned();
        
        for(int i = 0; i < l_lisTemp.size(); i++)
        {

            //注文単位の取得
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
                        
            //14. is取消可能注文単位（ミニ株)
            boolean l_blnIsMiniStockCancelOrderUnit = 
                l_equityOrderManager.isMiniStockCancelOrderUnit(l_orderUnit);
            
            //15. getExecutions( )
            OrderExecution[] l_execution = l_orderUnit.getExecutions();
            //16. getトランザクション(注文単位)
            WEB3EquityFinTransactionManager l_equityFinTransactionManager = 
                (WEB3EquityFinTransactionManager)l_finApp.getTradingModule
                (ProductTypeEnum.EQUITY).getFinTransactionManager();
                
            List l_lisTransactions = l_equityFinTransactionManager.getTransactions(l_orderUnit);
            
         
            
            
            //17. 株式ミニ投資注文約定明細( )
            WEB3MstkExecuteGroup l_group = new WEB3MstkExecuteGroup();
            
            //18. (*1.1) プロパティセット

                
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
            //19. add(arg0（=株式ミニ投資注文約定明細）
            l_lisArrayList.add(l_group);
        }
            //20. toArray( )
            //21. (*2) プロパティセット 
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
        //(表示ページ番号)
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + ""; 
        //総ページ数
        l_response.totalPages= l_pageIndexInfo.getTotalPages() + ""; 
        //総レコード数
        l_response.totalRecords= l_pageIndexInfo.getTotalRecords() + ""; 
    }
    
    /**
     * （create検索条件文字列）。<BR>
     * <BR>
     * 検索条件文字列を編集する。<BR>
     * <BR>
     * １）　@戻り値生成<BR>
     * 　@戻り値の検索条件文字列インスタンス（：String）を生成 <BR>
     * <BR>
     * ２）　@注文種別条件追加<BR>
     * 　@注文種別の指定文字列を検索条件文字列に追加<BR>
     * <BR>
     * 　@" and order_type in (*注１, *注２)"<BR>
     * <BR>
     * <BR>　@---------------------------------------------------------<BR>
     * 　@*注１：　@OrderTypeEnum.MINI_STOCK_BUY<BR>
     * （101：株式ミニ投資買注文）　@を文字列に変換した値<BR>
     * 　@*注２：　@OrderTypeEnum.MINI_STOCK_SELL<BR>
     * （102：株式ミニ投資売注文）　@を文字列に変換した値<BR>
     * <BR>　@---------------------------------------------------------<BR>
     * <BR>
     * ３）　@発注日条件追加<BR>
     * 　@発注日の範囲を文字列インスタンスに設定 <BR>
     * <BR>
     * 　@－注文約定照会の場合（照会区分 == 0：注文約定照会）<BR>
     * 　@　@　@" and biz_date >= ?" <BR>
     * <BR>
     * 　@－取消一覧の場合（照会区分 == 1：訂正取消一覧）<BR>
     * 　@　@　@" and biz_date = ?" <BR>
     * <BR>
     * ４）　@銘柄条件追加（※ 銘柄コード指定（銘柄コード != null）<BR>
     * の場合のみ）<BR>
     * 　@銘柄ＩＤ指定を追加する。<BR>
     * <BR>
     * 　@" and product_id = ?" <BR>
     * <BR>
     * ５）　@注文状況条件追加<BR>
     * <BR>
     * 　@○　@取消一覧または、注文中指定の場合<BR>
     * （照会区分 == 1：訂正取消一覧 Or 注文状況区分 == 0：注文中）<BR>
     * 　@　@　@注文中指定を追加する。<BR>
     * <BR>
     * 　@　@　@" and order_open_status = *注３ "<BR>
     * <BR>
     * 　@○　@約定済指定の場合（注文状況区分 == １：約定済）<BR>
     * 　@　@　@約定済指定を追加する。<BR>
     * <BR>
     * 　@　@　@" and order_open_status = *注４ and executed_quantity != 0 "<BR>
     * <BR>
     * 　@○　@失効済指定の場合（注文状況区分 == ２：失効済）<BR>
     * 　@　@　@失効済指定を追加する。<BR>
     * <BR>
     * 　@　@　@" and order_open_status = *注４ and expiration_status = <BR>
     *注５ "<BR>
     * <BR>
     * 　@○　@取消済指定の場合（注文状況区分 == ３：取消済）<BR>
     * 　@　@　@取消済指定を追加する。<BR>
     * <BR>
     * 　@　@　@" and order_open_status = *注４ and order_status = *注６ "<BR>
     * <BR>
     * <BR>　@
     * -------------------------------------------------------<BR>
     * 　@*注３：　@OrderOpenStatusEnum.OPEN（1：オープン）<BR>
     * を文字列に変換した値<BR>
     * 　@*注４：　@OrderOpenStatusEnum.CLOSE（2：クローズ）<BR>
     * を文字列に変換した値<BR>
     * 　@*注５：　@OrderExpirationStatusEnum.INVALIDATED_BY_MKT<BR>
     * （3：マーケット拒否）を文字列に変換した値<BR>
     * 　@*注６：　@OrderStatusEnum.CANCELLED（14：発注済（取消注文））<BR>
     * を文字列に変換した値<BR>
     * <BR>
     * -------------------------------------------------------<BR>
     * <BR>
     * ６）　@文字列インスタンスを返却
     * @@param l_strProductCode (銘柄コード)
     * @@param l_strReferenceDivision (照会区分)<BR>
     * 0：注文約定照会（デフォルト） <BR>
     * 1：訂正取消一覧（訂正取消可能なもののみ表示）
     * @@param l_strOrderSituationDivision (注文状況区分)<BR>
     * null： 指定無し<BR>
     * 0：注文中　@1：約定済　@2：失効済　@3：取消済
     * @@return String
     */
    protected String createSearchCondCharacterString(String l_strProductCode, String l_strReferenceDivision, String l_strOrderSituationDivision) 
    {
        final String STR_METHOD_NAME =
            "createSearchCondCharacter" +
            "(String l_strProductCode, String l_strReferenceDivision, String l_strOrderSituationDivision)";
        log.entering(STR_METHOD_NAME);
        String l_strSearchCond;
        //２）　@注文種別条件追加<BR>
        // 　@注文種別の指定文字列を検索条件文字列に追加<BR>
        // 　@" and order_type in (*注１, *注２)"<BR>
        l_strSearchCond 
            = " order_type in ( " + OrderTypeEnum.MINI_STOCK_BUY.intValue() + 
                "," + OrderTypeEnum.MINI_STOCK_SELL.intValue() + " )";
            
        //     * ３）　@発注日条件追加<BR>
        // 　@発注日の範囲を文字列インスタンスに設定 <BR>
        // <BR>
        // 　@－注文約定照会の場合（照会区分 == 0：注文約定照会）<BR>
        // 　@　@　@" and biz_date >= ?" <BR>
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(l_strReferenceDivision))
        {
            l_strSearchCond 
                += " and biz_date >= ?";
        }
        // 　@－取消一覧の場合（照会区分 == 1：訂正取消一覧）<BR>
        // 　@　@　@" and biz_date = ?" <BR>
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_strReferenceDivision))
        {
            l_strSearchCond
                += " and biz_date = ?";
        }
        //     * ４）　@銘柄条件追加（※ 銘柄コード指定（銘柄コード != null）<BR>
        // の場合のみ）<BR>
        // 　@銘柄ＩＤ指定を追加する。<BR>
        // <BR>
        // 　@" and product_id = ?" <BR>
        // <BR>
        //銘柄ID指定を追加（銘柄コードに対応する銘柄IDで検索を行う)
        if (l_strProductCode != null && !("").equals(l_strProductCode))
        {
            //銘柄ID指定を追加する
            l_strSearchCond 
                += " and product_id = ?";
        }
        // ５）　@注文状況条件追加<BR>
        // <BR>
        // 　@○　@取消一覧または、注文中指定の場合<BR>
        // （照会区分 == 1：訂正取消一覧 Or 注文状況区分 == 0：注文中）<BR>
        // 　@　@　@注文中指定を追加する。<BR>
        // <BR>
        // 　@　@　@" and order_open_status = *注３ "<BR>
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_strReferenceDivision)
            || WEB3EquityOrderTypeDivisionDef.ORDRING.equals(l_strOrderSituationDivision))
        {
            l_strSearchCond +=" and order_open_status = " + OrderOpenStatusEnum.OPEN.intValue();
        }
        // 　@○　@約定済指定の場合（注文状況区分 == １：約定済）<BR>
        // 　@　@　@約定済指定を追加する。<BR>
        // <BR>
        // 　@　@　@" and order_open_status = *注４ and executed_quantity != 0 "<BR>
        if (WEB3EquityOrderTypeDivisionDef.EXECUTED.equals(l_strOrderSituationDivision))
        {
            l_strSearchCond
                += " and order_open_status = " 
                + OrderOpenStatusEnum.CLOSED.intValue() + " and  executed_quantity != 0 ";
        }
        //     * 　@○　@失効済指定の場合（注文状況区分 == ２：失効済）<BR>
        // 　@　@　@失効済指定を追加する。<BR>
        // <BR>
        // 　@　@　@" and order_open_status = *注４ and expiration_status = 注５ " <BR>
        if (WEB3EquityOrderTypeDivisionDef.ORDERED.equals(l_strOrderSituationDivision))
        {
            l_strSearchCond
                += " and order_open_status = " + OrderOpenStatusEnum.CLOSED.intValue() 
                + " and expiration_status = " + OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue();
        }
        // <BR>
        // 　@○　@取消済指定の場合（注文状況区分 == ３：取消済）<BR>
        // 　@　@　@取消済指定を追加する。<BR>
        // <BR>
        // 　@　@　@" and order_open_status = *注４ and order_status = *注６ "<BR>
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
     * （create検索条件データコンテナ）。<BR>
     * <BR>
     * データコンテナ文字列の配列を編集する。<BR>
     * <BR>
     * 以下の通り、データコンテナ配列（：String[]）を返却する。 <BR>
     * <BR>
     * ○　@銘柄コード指定でない（銘柄コード == null）の場合<BR>
     * <BR>
     * 　@　@データコンテナ[0]（発注日指定） = (*注１)<BR>
     * <BR>
     * ○　@銘柄コード指定（銘柄コード != null）の場合<BR>
     * <BR>
     * 　@　@データコンテナ[0]（発注日指定） = (*注１)<BR>
     * 　@　@データコンテナ[1]（銘柄コード指定） =<BR>
     *  （銘柄コードに該当する銘柄ＩＤ※）<BR>
     * <BR>
     * 　@　@※　@銘柄コードに該当する銘柄ＩＤ<BR>
     * 　@　@プロダクトマネージャ.get銘柄<BR> 
     * （証券会社(*注２), 銘柄コード）.getProductId()<BR>
     * <BR>
     * <BR>　@
     * ---------------------------------------------------------<BR>
     * 　@*注１　@発注日指定のセット<BR>
     * 　@－注文約定照会の場合（照会区分 == 0：注文約定照会）<BR>
     * 　@　@　@本日発注日の前営業日<BR>
     * （※取引時間管理.get発注日()の前営業日）<BR>
     * <BR>
     * 　@－取消一覧の場合（照会区分 == 1：訂正取消一覧）<BR>
     * 　@　@　@本日発注日（※取引時間管理.get発注日()）<BR>
     * <BR>
     * 　@*注２　@証券会社オブジェクトは、補助口座.getInstitution( )<BR>
     * で取得し設定 <BR>
     * <BR>
     * --------------------------------------------------------
     * @@param l_strProductCode (銘柄コード)
     * @@param l_strReferenceDivision (照会区分)
     * @@param l_strOrderSituationDivision (注文状況区分)<BR>
     * null： 指定無し<BR>
     * 0：注文中　@1：約定済　@2：失効済　@3：取消済
     * @@return String[]
     */
    protected String[] searchCondDataContainer(String l_strProductCode, String l_strReferenceDivision,  String l_strOrderSituationDivision) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME =
            "searchCondDataContainer(String l_strProductCode, String l_strOrderSituationDivision)";
        log.entering(STR_METHOD_NAME);
        List l_lisSearchCond = new Vector();
        String[] l_strsearchCond = null;
        //     * ○　@銘柄コード指定でない（銘柄コード == null）の場合<BR>
        // <BR>
        //　@　@データコンテナ[0]（発注日指定） = (*注１)<BR>
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

            //     * ○　@銘柄コード指定（銘柄コード != null）の場合<BR>
            // <BR>
            // 　@　@データコンテナ[0]（発注日指定） = (*注１)<BR>
            // 　@　@データコンテナ[1]（銘柄コード指定） =<BR>
            //  （銘柄コードに該当する銘柄ＩＤ※）<BR>
            // <BR>
            if (l_strProductCode != null)
            {

                //     銘柄コードに該当する銘柄ＩＤ<BR>
                // 　@　@プロダクトマネージャ.get銘柄<BR> 
                // （証券会社(*注２), 銘柄コード）.getProductId()<BR>
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
     * （createソート条件）。<BR>
     * <BR>
     * ソート条件（SortKeySpec）文字列を編集する。<BR>
     * <BR>
     * １）　@ソートキー条件編集<BR>
     * 　@株式ミニ投資ソートキー.キー項目の数分、対応するテーブルの<BR>
     * 列物理名を昇順／降順指定を付加してセットする。<BR>
     * 但し、指定なし（株式ミニ投資ソートキー == null）<BR>
     * の場合、銘柄コードの昇順とする。<BR>
     * <BR>
     * 　@　@・キー項目とテーブルの列名称との対応は以下の通り <BR>
     * 　@　@　@※テーブル名：注文単位テーブル(eqtype_order_unit) <BR>
     * 　@　@　@※キー項目文字列（シンボル名）は、メッセージ定義書を参照 <BR>
     * 　@　@　@※テーブルの列物理名は、テーブルレイアウトを参照 <BR>
     * <BR>
     * 　@　@　@変換前 　@　@　@　@　@   変換後 <BR>
     * 　@　@　@-------------   ----------------------------- <BR>
     * 　@　@　@銘柄コード        ：注文単位テーブル．銘柄ID <BR>
     * 　@　@　@市場コード        ：注文単位テーブル．市場ID <BR>
     * 　@　@　@売買区分         ：注文単位テーブル．注文種別 <BR>
     * 　@　@　@注文日時         ：注文単位テーブル．受注日時 <BR>
     * <BR>
     * 　@　@・昇順／降順指定は、信用取引ソートキー.昇順／降順 <BR>
     * 指定に従い設定 <BR>
     * <BR>
     * ２）　@追加条件編集<BR>
     * 　@ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加し、<BR>
     * ソート条件文字列を返却する。
     * @@param l_mstkSortKey (株式ミニ投資ソートキー)<BR>
     * 株式ミニ投資ソートキーオブジェクト
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
			//(2)ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加
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
            //銘柄コード：注文単位テーブル．銘柄ID         
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

            //市場コード：注文単位テーブル．市場ID                         
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
            //売買区分：注文単位テーブル．注文種別                                     
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

            //注文日時：注文単位テーブル．受注日時
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
        //(2)ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加
        l_strReturn.append(", ");
        l_strReturn.append("last_updated_timestamp");
        l_strReturn.append(" ");
        l_strReturn.append("ASC");
        log.exiting(STR_METHOD_NAME);
        return l_strReturn.toString();
     
    }
}
@
