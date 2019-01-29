head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO申告クラス(WEB3IpoOrderImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 李海波 (中訊) 新規作成
Revesion History : 2004/12/29 坂上(SRA) 残対応>>>044
Revesion History : 2005/01/05 坂上(SRA) 残対応>>>054
*/

package webbroker3.ipo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3IpoOrderAcceptStatusDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.ipo.data.IpoBookbuildingOrderActionDao;
import webbroker3.ipo.data.IpoBookbuildingOrderActionRow;
import webbroker3.ipo.data.IpoOrderDao;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3OrderAppStatusDef;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO申告クラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3IpoOrderImpl implements OrderUnit 
{
    
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderImpl.class);
    
    /**
     * IPO申告行オブジェクト<BR>
     * <BR>
     * ※ IPO申告ParamsクラスはDDLより自動生成する。
     */
    private IpoOrderParams ipoOrderParams;
    
    public WEB3IpoOrderImpl()
    {
        
            
    }
    
    /**
     * (IPO申告)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 指定したIPO申告ＩＤに該当する行をIPO申告テーブルより検索する。<BR>
     * 検索結果のIPO申告行オブジェクトを引数に指定して、コンストラクタをコールする。<BR>
     * コンストラクタの戻り値を返却する。
     * @@param l_lngIpoOrderId - IPO申告ID
     * @@roseuid 40BFFB280000
     */
    public WEB3IpoOrderImpl(long l_lngIpoOrderId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        
        this(IpoOrderDao.findRowByPk(l_lngIpoOrderId));

    }
    
    /**
     * (IPO申告)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 行指定行オブジェクトをプロパティにセットし、インスタンスを生成する。
     * @@param l_ipoOrderParams - (ブックビルディング申告Params)<BR>
     * ブックビルディング申告行オブジェクト<BR>
     * ※ ブックビルディング申告ParamsクラスはDDLより自動生成する。
     * @@roseuid 40BFFB2703D9
     */
    public WEB3IpoOrderImpl(Row l_ipoOrderParams) 
    {

        ipoOrderParams = new IpoOrderParams((IpoOrderRow)l_ipoOrderParams);
        
    }
    
    /**
     * （getDataSourceObjectの実装）<BR>
     * <BR>
     * this.IPO申告行を返却する。
     * @@return Object
     * @@roseuid 40BFF9FE009C
     */
    public Object getDataSourceObject() 
    {
        
        final String STR_METHOD_NAME =
            " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        
        log.exiting(STR_METHOD_NAME);
        
        return ipoOrderParams;
        

    }
    
    /**
     * （getOrderUnitIdの実装）<BR>
     * <BR>
     * this.IPO申告行.IPO申告ＩＤ を返却する。
     * @@return long
     * @@roseuid 40BFF9FE00AC
     */
    public long getOrderUnitId() 
    {
        
        final String STR_METHOD_NAME =
            " getOrderUnitId()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        return ipoOrderParams.getIpoOrderId();

    }
    
    /**
     * (getIPO申告ＩＤ)<BR>
     * （getOrderIdの実装）<BR>
     * <BR>
     * this.IPO申告行.IPO申告ＩＤ を返却する。
     * @@return long
     * @@roseuid 40BFF9FE00BC
     */
    public long getOrderId() 
    {
        
        final String STR_METHOD_NAME =
            " getOrderId()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        return ipoOrderParams.getIpoOrderId();
        
    }
    
    /**
     * (get口座ID)<BR>
     * （getAccountIdの実装）<BR>
     * <BR>
     * this.IPO申告行.口座ＩＤ を返却する。
     * @@return long
     * @@roseuid 40BFF9FE00CB
     */
    public long getAccountId() 
    {
        
        final String STR_METHOD_NAME =
            " getAccountId()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        return ipoOrderParams.getAccountId();
        
    }
    
    /**
     * (get補助口座ID)<BR>
     * （getSubAccountIdの実装）<BR>
     * <BR>
     * this.IPO申告行.補助口座ＩＤ を返却する。
     * @@return long
     * @@roseuid 40BFF9FE00DB
     */
    public long getSubAccountId() 
    {
        
        final String STR_METHOD_NAME =
            " getSubAccountId()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        return ipoOrderParams.getSubAccountId();
        
    }
    
    /**
     * (get部店ID)<BR>
     * （getBranchIdの実装）<BR>
     * <BR>
     * this.IPO申告行.部店ＩＤ を返却する。
     * @@return long
     * @@roseuid 40BFF9FE00EB
     */
    public long getBranchId() 
    {
        
        final String STR_METHOD_NAME =
            " getBranchId()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        return ipoOrderParams.getBranchId();
        
    }
    
    /**
     * (get取引者ID)<BR>
     * （getTraderIdの実装）<BR>
     * <BR>
     * this.IPO申告行.取引者ＩＤ を返却する。
     * @@return long
     * @@roseuid 40BFF9FE00FA
     */
    public long getTraderId() 
    {

        return ipoOrderParams.getTraderId();
        
    }
    
    /**
     * (get顧客)<BR>
     * 顧客オブジェクトを取得する。<BR>
     * <BR>
     * this.get口座ＩＤに該当する顧客オブジェクトを取得し返却する。
     * @@return MainAccount
     * @@roseuid 40F65238006F
     */
    public MainAccount getMainAccount() 
    {
        
        final String STR_METHOD_NAME =
            " getMainAccount()";
        log.entering(STR_METHOD_NAME);
        
        MainAccount l_mainAccount = null;
        
        if(l_mainAccount == null)
        {
            
            try
            {
                
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                l_mainAccount = l_finApp.getAccountManager().getMainAccount(getAccountId());
                
            }
            catch (NotFoundException l_ex)
            {
                
                String l_strMsg = "MainAccount not found accountId : " + getAccountId();
                log.error(l_strMsg, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new RuntimeSystemException(l_strMsg, l_ex);                
                
            }
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_mainAccount;

    }
    
    /**
     * (get補助口座)<BR>
     * 補助口座オブジェクトを取得する。<BR>
     * <BR>
     * this.get補助口座ＩＤに該当する補助口座オブジェクトを取得し返却する。
     * @@return SubAccount
     */
    public SubAccount getSubAccount() 
    {
        
        final String STR_METHOD_NAME =
            " getSubAccount()";
        log.entering(STR_METHOD_NAME);
        
        SubAccount l_subAccount = null;
        
        if(l_subAccount == null)
        {
            
            try
            {
                
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                l_subAccount = l_finApp.getAccountManager().getSubAccount(getAccountId(),getSubAccountId());
                
            }
            catch (NotFoundException l_ex)
            {
                
                String l_strMsg = "SubAccount not found subaccountId : " + getSubAccountId();
                log.error(l_strMsg, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new RuntimeSystemException(l_strMsg, l_ex);                
                
            }
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;

    }
    
    
    
    /**
     * （未使用）<BR>
     * <BR>
     * falseを返却する。
     * @@return boolean
     * @@roseuid 40BFF9FE010A
     */
    public boolean isExpired() 
    {
        
        return false;
        
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * falseを返却する。
     * @@return boolean
     * @@roseuid 40BFF9FE0119
     */
    public boolean isFullyExecuted() 
    {
        
        return false;
        
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * falseを返却する。
     * @@return boolean
     * @@roseuid 40BFF9FE0129
     */
    public boolean isPartiallyExecuted() 
    {
        
        return false;
        
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * falseを返却する。
     * @@return boolean
     * @@roseuid 40BFF9FE0139
     */
    public boolean isUnexecuted() 
    {
        
        return false;
        
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * 0を返却する。
     * @@return double
     * @@roseuid 40BFF9FE0158
     */
    public double getConfirmedPrice() 
    {
        
        return 0;
     
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * falseを返却する。
     * @@return boolean
     * @@roseuid 40BFF9FE0168
     */
    public boolean isConfirmedPriceMarketOrder() 
    {
        
        return false;
     
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * 0を返却する。
     * @@return double
     * @@roseuid 40BFF9FE0177
     */
    public double getConfirmedQuantity() 
    {
        
        return 0;
     
    }
    
    /**
     * (get数量)<BR>
     * （getQuantityの実装）<BR>
     * <BR>
     * this.IPO申告行.数量 を返却する。
     * @@return double
     * @@roseuid 40BFF9FE0187
     */
    public double getQuantity() 
    {

        return ipoOrderParams.getQuantity();
         
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * 0を返却する。
     * @@return double
     * @@roseuid 40BFF9FE0196
     */
    public double getExecutedAmount() 
    {
        
        return 0;
     
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * 0を返却する。
     * @@return double
     * @@roseuid 40BFF9FE01A6
     */
    public double getExecutedQuantity() 
    {
        
        return 0;
     
    }
    
    /**
     * (get指値)<BR>
     * （getLimitPriceの実装）<BR>
     * <BR>
     * this.IPO申告行.指値 を返却する。
     * @@return double
     * @@roseuid 40BFF9FE01B6
     */
    public double getLimitPrice() 
    {

        return ipoOrderParams.getLimitPrice();
     
    }
    
    /**
     * (is成行)<BR>
     * （isMarketOrderの実装）<BR>
     * <BR>
     * －（this.IPO申告行.指値 == 0）の場合、trueを返却する。<BR>
     * －以外、falseを返却する。
     * @@return boolean
     * @@roseuid 40BFF9FE01C5
     */
    public boolean isMarketOrder() 
    {
        
        final String STR_METHOD_NAME = " isMarketOrder()";
        log.entering(STR_METHOD_NAME);
        
        double l_dblPrice = getLimitPrice();
        
        if(l_dblPrice == 0)
        {
            
            log.exiting(STR_METHOD_NAME);
            return true;
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
        
    }
    
    /**
     * (get作成日時)<BR>
     * （getReceivedTimestampの実装）<BR>
     * <BR>
     * this.IPO申告行.作成日時 を返却する。
     * @@return Timestamp
     * @@roseuid 40BFF9FE01F4
     */
    public Timestamp getReceivedTimestamp() 
    {
        
        return ipoOrderParams.getCreatedTimestamp();
        
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return Timestamp
     * @@roseuid 40BFF9FE0204
     */
    public Timestamp getExpirationTimestamp() 
    {
        
        return null;
     
    }
    
    /**
     * (getIPO銘柄)<BR>
     * （getProductの実装）<BR>
     * <BR>
     * this.IPO申告行.IPO銘柄IDに該当するIPO銘柄オブジェクトを返却する。<BR>
     * <BR>
     * １）　@IPO銘柄IDを指定し、IPO銘柄を生成する。<BR>
     * 　@[コンストラクタの引数]<BR>
     * 　@IPO銘柄ID：　@this.IPO申告行.IPO銘柄ID<BR>
     * <BR>
     * ２）　@生成したオブジェクトを返却する。<BR>
     * @@return Product
     * @@roseuid 40BFF9FE0213
     */
    public Product getProduct() 
    {
        
        final String STR_METHOD_NAME =
            " getProduct()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IpoProductImpl l_product = null;

        try
        {
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
                (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            
            l_product = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(ipoOrderParams.getIpoProductId());                
                
        }
        catch (NotFoundException l_ex)
        {
            
            String l_strMsg = "Order not found in ifo_order with productId : " + ipoOrderParams.getIpoProductId();
            log.error(l_strMsg, l_ex);
            log.exiting(STR_METHOD_NAME);
            
            throw new RuntimeSystemException(l_strMsg, l_ex);                
            
        }

        
        log.exiting(STR_METHOD_NAME);
        return l_product;

    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return Order
     * @@roseuid 40BFF9FE0233
     */
    public Order getOrder() 
    {
        
        return null;
     
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution[]
     * @@roseuid 40BFF9FE0242
     */
    public OrderExecution[] getExecutions() 
    {
        
        return null;
     
    }
    
    /**
     * (getIPO申告有効状態)<BR>
     * （getOrderOpenStatusの実装）<BR>
     * <BR>
     * this.IPO申告行.IPO申告有効状態 を返却する。
     * @@return OrderOpenStatusEnum
     * @@roseuid 40BFF9FE0262
     */
    public OrderOpenStatusEnum getOrderOpenStatus() 
    {
        
        return ipoOrderParams.getOrderOpenStatus();
     
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return OrderTypeEnum
     * @@roseuid 40BFF9FE0271
     */
    public OrderTypeEnum getOrderType() 
    {
        
        return null;
     
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return OrderCategEnum
     * @@roseuid 40BFF9FE0281
     */
    public OrderCategEnum getOrderCateg() 
    {
        
        return null;
     
    }
    
    /**
     * (get税区分)<BR>
     * （getTaxTypeの実装）<BR>
     * <BR>
     * this.IPO申告行.税区分 を返却する。
     * @@return TaxTypeEnum
     * @@roseuid 40BFF9FE0290
     */
    public TaxTypeEnum getTaxType() 
    {
        
        return ipoOrderParams.getTaxType();
     
    }
    
    /**
     * SideEnum.BUYを返却する。
     * @@return SideEnum
     * @@roseuid 40BFF9FE02A0
     */
    public SideEnum getSide() 
    {
        
        return SideEnum.BUY;

    }
    
    /**
     * (getブックビルディング申告状態)<BR>
     * （getOrderStatusの実装）<BR>
     * <BR>
     * this.IPO申告行.ブックビルディング申告状態 を返却する。
     * @@return OrderStatusEnum
     * @@roseuid 40BFF9FE02B0
     */
    public OrderStatusEnum getOrderStatus() 
    {

        return ipoOrderParams.getOrderStatus();

    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return OrderExpirationStatusEnum
     * @@roseuid 40BFF9FE02BF
     */
    public OrderExpirationStatusEnum getExpirationStatus() 
    {
        
        return null;
        
    }
    
    /**
     * (get公開日)<BR>
     * （getDeliveryDateの実装）<BR>
     * <BR>
     * this.IPO申告行.IPO銘柄IDに該当するIPO銘柄オブジェクトを返却する。<BR>
     * <BR>
     * １）　@IPO銘柄IDを指定し、IPO銘柄を生成する。<BR>
     * 　@[コンストラクタの引数]<BR>
     * 　@IPO銘柄ID：　@this.IPO申告行.IPO銘柄ID<BR>
     * <BR>
     * ２）　@生成したIPO銘柄.公開日を返却する。<BR>
     * @@return Date
     * @@roseuid 40BFF9FE02CF
     */
    public Date getDeliveryDate() 
    {
        
        final String STR_METHOD_NAME = " getDeliveryDate()";
        log.entering(STR_METHOD_NAME);
        
        WEB3IpoProductImpl l_product = null;
        IpoProductRow l_productRow = null;
        try
        {
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3IpoProductManagerImpl l_ipoProductManagerImpl = 
                (WEB3IpoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
            
            l_product = (WEB3IpoProductImpl)l_ipoProductManagerImpl.getProduct(ipoOrderParams.getIpoProductId());                
            l_productRow = (IpoProductRow)(l_product.getDataSourceObject());
                
        }
        catch (NotFoundException l_ex)
        {
            
            String l_strMsg = "Order not found in ifo_order with productId : " + ipoOrderParams.getIpoProductId();
            log.error(l_strMsg, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new RuntimeSystemException(l_strMsg, l_ex);                
            
        }
 
        log.exiting(STR_METHOD_NAME);
        return l_productRow.getPublicOfferingDate();

    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return TradedProduct
     * @@roseuid 40BFF9FE02DF
     */
    public TradedProduct getTradedProduct() 
    {
        
         return null;
     
    }
    
    /**
     * (get購入申込代金)<BR>
     * this.IPO申告行.購入申込代金 を返却する。
     * @@return double
     * @@roseuid 40DBDD870295
     */
    public double getPayAmount() 
    {
        
        if(ipoOrderParams.getPayAmountIsNull())
        {
            
            return 0.0D/0.0D;
            
        }
        return ipoOrderParams.getPayAmount();

    }
    
    /**
     * (get申告申込状況)<BR>
     * IPO申告内容を判断し、該当する申告申込状況区分を返却する。<BR>
     * <BR>
     * 　@[申告申込状況区分]<BR>
     * 　@01：　@ブックビルディング申告済<BR>
     * 　@02：　@ブックビルディングキャンセル<BR>
     * 　@03：　@当選<BR>
     * 　@04：　@当選キャンセル<BR>
     * 　@05：　@申込済<BR>
     * 　@06：　@約定済<BR>
     * 　@07：　@補欠<BR>
     * 　@08：　@補欠キャンセル<BR>
     * 　@09：　@補欠申込済<BR>
     * 　@10：　@補欠約定済<BR>
     * 　@11：　@補欠落選<BR>
     * 　@12：　@落選<BR>
     * <BR>
     * 
     * １）　@当選者の判定<BR>
     * 　@this.IPO申告行.抽選結果 == ”当選”の場合<BR>
     * <BR>
     * 　@　@－（this.IPO申告行.購入申込区分 == ”0：　@DEFAULT（初期値）”） &&<BR>
     * 　@　@　@（this.getIPO銘柄().is購入申込終了（当社設定）() == true） の場合、”<BR>当選キャンセル”を返却する。<BR>
     * <BR>
     * 　@　@－（this.IPO申告行.購入申込区分 == ”0：　@DEFAULT（初期値）”） &&<BR>
     * 　@　@　@（this.getIPO銘柄().is購入申込終了（当社設定）() == false） の場合、”<BR>当選”を返却する。<BR>
     * <BR>
     * 　@　@－（this.IPO申告行.購入申込区分 == ”1：　@購入申込”） &&<BR>
     * 　@　@　@（this.IPO申告行.受付状態 == ”0：　@DEFAULT（初期値）”） の場合、”<BR>申込済”を返却する。<BR>
     * <BR>
     * 　@　@－（this.IPO申告行.購入申込区分 == ”1：　@購入申込”） &&<BR>
     * 　@　@　@（this.IPO申告行.受付状態 != ”0：　@DEFAULT（初期値）”） の場合、<BR>”約定済”を返却する。<BR>
     * <BR>
     * 　@　@－（this.IPO申告行.購入申込区分 == ”2：　@辞退”） の場合、<BR>
     * ”当選キャンセル”を返却する。<BR>
     * <BR>
     * ２）　@補欠者の判定<BR>
     * 　@this.IPO申告行.抽選結果 == ”補欠”の場合<BR>
     * <BR>
     * 　@　@－（this.IPO申告行.購入申込区分 == ”0：　@DEFAULT（初期値）”） &&<BR>
     * 　@　@　@（this.getIPO銘柄().is購入申込終了（当社設定）() == true <BR>
     *           Or   （this.IPO申告行.抽選結果（繰上） == ”落選”））の場合、”<BR>補欠キャンセル”を返却する。<BR>
     * <BR>
     * 　@　@－（this.IPO申告行.購入申込区分 == ”0：　@DEFAULT（初期値）”） &&<BR>
     * 　@　@　@（this.getIPO銘柄().is購入申込終了（当社設定）() == false） の場合、”<BR>補欠”を返却する。<BR>
     * <BR>
     * 　@　@－（this.IPO申告行.購入申込区分 == ”1：　@購入申込”） &&<BR>
     * 　@　@　@（this.IPO申告行.抽選結果（繰上） == ”0：DEFAULT（未抽選）”） の<BR>場合、”補欠申込済”を返却する。<BR>
     * <BR>
     * 　@　@－（this.IPO申告行.購入申込区分 == ”1：　@購入申込”） &&<BR>
     * 　@　@　@（this.IPO申告行.抽選結果（繰上） == ”当選”） の場合、”補欠約定済”<BR>を返却する。<BR>
     * <BR>
     * 　@　@－（this.IPO申告行.購入申込区分 == ”1：　@購入申込”） &&<BR>
     * 　@　@　@（this.IPO申告行.抽選結果（繰上） == ”落選”） の場合、”補欠落選”を<BR>返却する。<BR>
     * <BR>
     * 　@　@－（this.IPO申告行.購入申込区分 == ”2：　@辞退”） の場合、<BR>
     * ”補欠キャンセル”を返却する。<BR>
     * <BR>
     * ３）　@落選者の判定<BR>
     * 　@this.IPO申告行.抽選結果 == ”落選”の場合、”落選”を返却する。<BR>
     * <BR>
     * ４）　@抽選前の判定<BR>
     * 　@this.IPO申告行.抽選結果 == ”0：DEFAULT（未抽選）”の場合<BR>
     * <BR>
     * 　@－（this.getIPO申告有効状態() == OrderOpenStatusEnum.OPEN）の場合、<BR>
     * 　@　@”ブックビルディング申告済”を返却する。<BR>
     * <BR>
     * 　@－（this.getIPO申告有効状態() == OrderOpenStatusEnum.CLOSE）の場合、<BR>
     * 　@　@”ブックビルディングキャンセル”を返却する。<BR>
     * <BR>
     * @@return String
     * @@roseuid 40DFA29000F9
     */
    public String getOrderOfferState() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME =
            " getOrderOfferState()";
        log.entering(STR_METHOD_NAME);
        
        //this.IPO申告行.抽選結果 == ”当選”の場合
        if((WEB3LotResultDef.ELECTION).equals(ipoOrderParams.getLotResult()))
        {
            
            //当選キャンセル”を返却する
            boolean l_bln = ((WEB3IpoProductImpl)getProduct()).isOfferEnd();
            if((WEB3OfferingDivDef.DEFAULT).equals(ipoOrderParams.getOfferingDiv()) && (l_bln))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.ELECTION_DEL;
                
            }
            //当選”を返却する
            if((WEB3OfferingDivDef.DEFAULT).equals(ipoOrderParams.getOfferingDiv()) && (!l_bln))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.ELECTION;
                
            }
            //申込済”を返却する
            if((WEB3OfferingDivDef.PURCHASE_APPLICATION).equals(ipoOrderParams.getOfferingDiv()) && 
                ((WEB3IpoOrderAcceptStatusDef.DEFAULT).equals(ipoOrderParams.getAcceptStatus())))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.APPLICATION;
                
            }
            //”約定済”を返却する
            if((WEB3OfferingDivDef.PURCHASE_APPLICATION).equals(ipoOrderParams.getOfferingDiv()) && 
                !((WEB3IpoOrderAcceptStatusDef.DEFAULT.equals(ipoOrderParams.getAcceptStatus()))))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.EXECUTED;
                
            }
            //当選キャンセル”を返却する。
            if((WEB3OfferingDivDef.REFUSAL).equals(ipoOrderParams.getOfferingDiv()))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.ELECTION_DEL;
                 
            }
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,"get申告申込状況");
            
        }
        else if((WEB3LotResultDef.SUPPLEMENT).equals(ipoOrderParams.getLotResult()))//補欠者の判定
        {
            
            //補欠キャンセル”を返却する
            boolean l_bln = ((WEB3IpoProductImpl)getProduct()).isOfferEnd();
            if((WEB3OfferingDivDef.DEFAULT).equals(ipoOrderParams.getOfferingDiv()) 
                && (l_bln || WEB3LotResultRetryDef.DEFEAT.equals(ipoOrderParams.getLotResultRetry())))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.SUPPLEMENT_DEL;
                
            }
            //補欠”を返却する。
            if((WEB3OfferingDivDef.DEFAULT).equals(ipoOrderParams.getOfferingDiv()) && (!l_bln))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.SUPPLEMENT;
                
            }
            //補欠申込済”を返却する
            if((WEB3OfferingDivDef.PURCHASE_APPLICATION).equals(ipoOrderParams.getOfferingDiv()) && 
                ((WEB3LotResultRetryDef.DEFAULT).equals(ipoOrderParams.getLotResultRetry())))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.SUPPLEMENT_APPLICATION;
                
            }
            //”補欠約定済”を返却する
            if((WEB3OfferingDivDef.PURCHASE_APPLICATION).equals(ipoOrderParams.getOfferingDiv()) && 
                (((WEB3LotResultRetryDef.ELECTION).equals(ipoOrderParams.getLotResultRetry()))))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.SUPPLEMENT_EXECUTED;
                
            }
            //”補欠落選”を返却する
            if((WEB3OfferingDivDef.PURCHASE_APPLICATION).equals(ipoOrderParams.getOfferingDiv()) && 
                ((WEB3LotResultRetryDef.DEFEAT).equals(ipoOrderParams.getLotResultRetry())))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.SUPPLEMENT_DEFEAT;
                
            }            
            //補欠キャンセル”を返却する。
            if((WEB3OfferingDivDef.REFUSAL).equals(ipoOrderParams.getOfferingDiv()))
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.SUPPLEMENT_DEL;
                 
            }
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,"get申告申込状況");            
            
        }
        else if((WEB3LotResultDef.DEFEAT).equals(ipoOrderParams.getLotResult()))//落選者の判定
        {
            
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderAppStatusDef.DEFEAT;
            
        }
        else if((WEB3LotResultDef.DEFAULT).equals(ipoOrderParams.getLotResult()))                     //抽選前の判定
        {
            
            if(OrderOpenStatusEnum.OPEN.equals(getOrderOpenStatus()))//”ブックビルディング申告済”を返却する
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.BOOK_BUILDING_DEMAND_COMPLETE;
                
            }
            
            if(OrderOpenStatusEnum.CLOSED.equals(getOrderOpenStatus()))//”ブックビルディングキャンセル”を返却する
            {
                
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderAppStatusDef.BOOK_BUILDING_DEL;
                
            }
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,"get申告申込状況");
            
        }
        else
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,"get申告申込状況"); 
            
        }

    }
    
    /**
     * (get申告価格)<BR>
     * 申告価格を取得する。<BR>
     * <BR>
     * this.IPO申告行.計算単価 を返却する。<BR> 
     * @@return double
     * @@roseuid 40E511ED00AF
     */
    public double getOrderPrice() 
    {
        
        final String STR_METHOD_NAME =
            " getOrderPrice()";
        log.entering(STR_METHOD_NAME);
        
        double l_dblPrice = this.ipoOrderParams.getPrice();
        
        log.exiting(STR_METHOD_NAME);
        return l_dblPrice;

    }
    
    /**
     * (get更新者コード)<BR>
     * this.IPO申告行.更新者コード を返却する。
     * @@return String
     * @@roseuid 40D912890249
     */
    public String getUpdaterCode() 
    {

        return ipoOrderParams.getLastUpdater();

    }
    
    /**
     * (get最新履歴ＩＤ)<BR>
     * 本オブジェクトが保持するブックビルディング申告履歴で、最新の履歴の<BR>
     * ブックビルディング申告履歴ＩＤを取得する。<BR>
     * <BR>
     * １）　@this.getブックビルディング申告履歴()にて履歴を取得する。<BR>
     * ２）　@取得した履歴のうち、最新の履歴(*1)のブックビルディング申告履歴ＩＤを<BR>返却する。<BR>
     * <BR>
     * (*1) 最新の履歴の判定<BR>
     * this.IPO申告行.申告履歴最終番号 == <BR>
     * ブックビルディング申告履歴.get申告履歴番号<BR>
     * <BR>
     * @@return long
     * @@roseuid 40D7C797028B
     */
    public long getLastOrderActionId() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " getLastOrderActionId()";
        log.entering(STR_METHOD_NAME);
        
        OrderAction[] l_orderAction = this.getOrderActions();
        long l_lngLength = l_orderAction.length;
        
        for(int i = 0; i < l_lngLength; i++ )
        {
            
            int l_intNo = l_orderAction[i].getOrderActionSerialNo();
            long l_lngId  = l_orderAction[i].getOrderActionId();
            
            if(this.ipoOrderParams.getLastOrderActionSerialNo() == l_intNo)
            {
                
                log.exiting(STR_METHOD_NAME);
                return l_lngId;
                
            }

        }
        log.exiting(STR_METHOD_NAME);
        throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,"get最新履歴ＩＤ");
        
    }
    
    /**
     * (getブックビルディング申告履歴)<BR>
     * （getOrderActions）<BR>
     * <BR>
     * 当該オブジェクトに関連するブックビル申告履歴を取得する。<BR>
     * <BR>
     * １）　@ブックビルディング申告履歴取得Dao.findRowByIpoOrderId()にて<BR>
     * 関連するブックビル申告履歴行のリストを取得する。<BR>
     * <BR>
     * [findRowsByOrderId()に指定する引数]<BR>
     * this.getIPO申告ＩＤ()<BR>
     * <BR>
     * ※ブックビル申告履歴DaoはDDLにて自動生成する。<BR>
     * <BR>
     * ２）　@取得した各要素を引数に指定し、ブックビルディング申告履歴オブジェクトを<BR>生成する。<BR>
     * <BR>
     * [コンストラクタの引数]<BR>
     * ブックビルディング申告履歴Params：　@ブックビルディング申告履歴行オブジェクト<BR>
     * <BR>
     * ３）　@生成したすべての要素を配列変換する。<BR>
     * <BR>
     * ４）　@sort<BR>
     * 　@３）で取得したブックビルディング申告履歴の配列を、WEB3ArraysUtility.sort()<BR>
     * にてsortして返却する。<BR>
     * <BR>
     * 　@[sortに指定する引数]<BR>
     * 　@Object[]：　@２）で取得したブックビルディング申告履歴の配列<BR>
     * 　@Comparator[]：　@<BR>
     * 　@　@com[0] = new ブックビルディング申告履歴.作成日時<BR>
     * Comparator(昇順（：asc）)<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction[]
     * @@roseuid 40E3F8C001AA
     */
    public OrderAction[] getOrderActions() 
    {
        
        final String STR_METHOD_NAME =
            " getOrderActions()";
        log.entering(STR_METHOD_NAME);
        
        List l_list;
        
        try
        {
            //１）　@ブックビルディング申告履歴取得Dao.findRowsByIpoOrderId()にて
            l_list = IpoBookbuildingOrderActionDao.findRowsByIpoOrderId(this.getOrderId());
            
        }
        catch(DataException de)
        {
            
            String msg = "Exception while getting IpoOrderActionsRows from iPo_order_action table for order_unit_id:"
                 + getOrderUnitId();
            log.exiting(STR_METHOD_NAME);
            throw new RuntimeSystemException(msg, de);
            
        }

        int l_intSize = l_list.size();
        OrderAction[] l_orderActions = new OrderAction[l_intSize];
        ArrayList l_arrayList = new ArrayList();
        for(int i = 0; i < l_intSize; i++)
        {

            WEB3IpoBookbuildingOrderActionImpl l_ipoBookbuildingOrderAction = 
                new WEB3IpoBookbuildingOrderActionImpl((IpoBookbuildingOrderActionRow)l_list.get(i));   
            l_arrayList.add(l_ipoBookbuildingOrderAction);
            
        }
        
        l_arrayList.toArray(l_orderActions);
        Comparator[] l_comparators = new Comparator[1]; 
        l_comparators[0] = new WEB3IpoBookbuildingOrderActionCreatedTimestampComparator(WEB3AscDescDef.ASC);
        WEB3ArraysUtility.sort(l_orderActions,l_comparators);

        log.exiting(STR_METHOD_NAME);
        return l_orderActions;

    }
    
    /**
     * (is購入申込)<BR>
     * 購入申込済かを判定する。<BR>
     * <BR>
     * －（this.IPO申告行.購入申込区分 == ”購入申込”）の場合、trueを返却する。<BR>
     * 　@－以外、falseを返却する。
     * @@return boolean
     * @@roseuid 40DB9C6E03A6
     */
    public boolean isOffered() 
    {

        return (WEB3OfferingDivDef.PURCHASE_APPLICATION).equals(ipoOrderParams.getOfferingDiv());
                
    }
    
    /**
     * (is辞退)<BR>
     * 辞退済かを判定する。<BR>
     * <BR>
     * －（this.IPO申告行.購入申込区分 == ”辞退”）の場合、trueを返却する。<BR>
     * 　@－以外、falseを返却する。
     * @@return boolean
     * @@roseuid 40DB9D1801E6
     */
    public boolean isDecline() 
    {
        
        return (WEB3OfferingDivDef.REFUSAL).equals(ipoOrderParams.getOfferingDiv());
          
    }
    
    /**
     * (is当選者)<BR>
     * 当選しているかを判定する。<BR>
     * <BR>
     * －（this.IPO申告行.抽選結果 == ”当選”）の場合、trueを返却する。<BR>
     * 　@－以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 40DB9D610210
     */
    public boolean isElected() 
    {
        
        final String STR_METHOD_NAME =
            " isElected()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        return (WEB3LotResultDef.ELECTION).equals(ipoOrderParams.getLotResult());

    }
    
    /**
     * (is補欠者)<BR>
     * 補欠者かを判定する。<BR>
     * <BR>
     * －（this.IPO申告行.抽選結果 == ”補欠”）の場合、trueを返却する。<BR>
     * 　@－以外、falseを返却する。
     * @@return boolean
     * @@roseuid 40DB9D8A0114
     */
    public boolean isWaiting() 
    {
        
        return (WEB3LotResultDef.SUPPLEMENT).equals(ipoOrderParams.getLotResult());

    }
    
    /**
     * (is落選者)<BR>
     * 落選かを判定する。<BR>
     * <BR>
     * －（this.IPO申告行.抽選結果 == ”落選”）の場合、trueを返却する。<BR>
     * 　@－以外、falseを返却する。
     * @@return boolean
     * @@roseuid 40DB9DAA0392
     */
    public boolean isRejected() 
    {

        return (WEB3LotResultDef.DEFEAT).equals(ipoOrderParams.getLotResult());

    }
 
    /**
     * (is繰上落選者)<BR>
     * 繰上落選かを判定する。 <BR>
     * <BR>
     *   －（this.IPO申告行.抽選結果（繰上） == ”落選”）の場合、trueを返却する。<BR>
     *   －以外、falseを返却する。
     * @@return boolean
     */
    public boolean isAdvanceRejected() 
    {

        return (WEB3LotResultRetryDef.DEFEAT).equals(ipoOrderParams.getLotResultRetry());

    } 
    
    /**
     * IPO申告ＩＤを新規採番(*1)し、this.IPO申告行.IPO申告ＩＤにセットする。<BR>
     * <BR>
     * (*1) IPO申告ＩＤの新規採番<BR>
     * 　@IPO申告DAO.newPkValue()メソッドにて取得する。<BR>
     * 　@※ IPO申告DAOクラスはDDLより自動生成する。
     * @@roseuid 40C03E2B0356
     */
    public void setNewId() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " setNewId()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            long l_lngId = IpoOrderDao.newPkValue();
            this.ipoOrderParams.setIpoOrderId(l_lngId);            
            
        }
        catch (DataNetworkException ex)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,"IPO申告DAO.newPkValue()");
            
        }
        catch(DataQueryException ex1)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,"IPO申告DAO.newPkValue()");
            
        }

    }
    
    /**
     * (set申告相当額計算結果)<BR>
     * 申告相当額計算結果の内容をオブジェクトにセットする。<BR>
     * <BR>
     * １）　@行オブジェクト取得<BR>
     * 　@引数.IPO申告.getDataSourceObject()にて行オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@行オブジェクトに計算結果をセット<BR>
     * 　@以下の通り、行オブジェクトにセットを行う。<BR>
     * <BR>
     * 　@IPO申告行.計算単価 = 申告相当額計算結果.計算単価<BR>
     * 　@IPO申告行.基準値（時価） = 申告相当額計算結果.基準値（時価）<BR>
     * 　@IPO申告行.申告相当額 = 申告相当額計算結果.申告相当額
     * @@param l_bookbuildingPriceCalcResult - 申告相当額計算結果オブジェクト
     * @@roseuid 40D9052D0372
     */
    public void setBookbuildingPriceCalcResult(WEB3IpoBookbuildingPriceCalcResult l_bookbuildingPriceCalcResult) 
    {
        
        final String STR_METHOD_NAME =
            " setBookbuildingPriceCalcResult(WEB3IpoBookbuildingPriceCalcResult)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblCalcUnitPrice = l_bookbuildingPriceCalcResult.getPrice();
        double l_dblBasePrice     = l_bookbuildingPriceCalcResult.getCurrentPrice();
        double l_EquivalentPrice  = l_bookbuildingPriceCalcResult.getBookbuildingPrice();
        
        this.ipoOrderParams.setPrice(l_dblCalcUnitPrice);
        if(!Double.isNaN(l_dblBasePrice))
        {
            this.ipoOrderParams.setCurrentPrice(l_dblBasePrice);
        }
        else
        {
            this.ipoOrderParams.setCurrentPrice(null);
        }
        this.ipoOrderParams.setBookbuildingPrice(l_EquivalentPrice);
        
        log.exiting(STR_METHOD_NAME);
         
    }
    
    /**
     * (購入申込)<BR>
     * IPO申告オブジェクトに購入申込情報をセットする。<BR>
     * <BR>
     * １）　@IPO銘柄オブジェクト取得<BR>
     * 　@this.getIPO銘柄()にてIPO銘柄オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@プロパティセット<BR>
     * 以下の通り、this.IPO銘柄行のプロパティに値をセットする。<BR>
     * <BR>
     * 　@this.IPO申告行.購入申込数量 == 購入申込数量<BR>
     * 　@this.IPO申告行.購入申込代金 == （購入申込数量 * IPO銘柄.公開価格）<BR>
     * 　@this.IPO申告行.購入申込区分 == ”購入申込”<BR>
     * 　@this.IPO申告行.税区分 == 税区分<BR>
     * 　@this.IPO申告行.購入申込／辞退日時 == 現在日時(*1)<BR>
     * 　@this.IPO申告行.更新者コード == (*2)<BR>
     * <BR>
     * 　@(*1)　@現在日時<BR>
     * 　@TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * 　@(*2)　@更新者コード<BR>
     * 　@　@（取引者 == null）の場合<BR>
     * 　@　@　@－口座ＩＤに該当する顧客.getAccountCode()<BR>
     * 　@　@以外の場合<BR>
     * 　@　@　@－取引者.getTraderCode()　@
     * @@param l_trader - (取引者)<BR>
     * 取引者（扱者）オブジェクト
     * 
     * @@param l_dblOfferQuantity - 購入申込数量
     * @@param l_taxType - 税区分
     * @@roseuid 40DBDA1F00DB
     */
    public void offer(Trader l_trader, double l_dblOfferQuantity, TaxTypeEnum l_taxType) 
    {
        
        final String STR_METHOD_NAME =
            " offer(Trader, double, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        WEB3IpoProductImpl l_product = null;
        
        l_product = (WEB3IpoProductImpl)(this.getProduct());
        IpoProductRow l_productRow = (IpoProductRow)l_product.getDataSourceObject();
        
        this.ipoOrderParams.setApplicationQuantity(l_dblOfferQuantity);
        this.ipoOrderParams.setPayAmount(l_dblOfferQuantity*(l_productRow.getPublicPrice()));
        this.ipoOrderParams.setOfferingDiv(WEB3OfferingDivDef.PURCHASE_APPLICATION);
        this.ipoOrderParams.setTaxType(l_taxType);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        
        this.ipoOrderParams.setOfferingTimestamp(l_processTime);
        
        String l_LastUpdater = null;
        
        if(l_trader == null)
        {
            
            l_LastUpdater = this.getMainAccount().getAccountCode();
            
        }
        else
        {
            
            l_LastUpdater = l_trader.getTraderCode();
            
        }

        this.ipoOrderParams.setLastUpdater(l_LastUpdater);
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (辞退)<BR>
     * IPO申告オブジェクトに辞退情報をセットする。<BR>
     * <BR>
     * １）　@プロパティセット<BR>
     * 以下の通り、this.IPO申告行のプロパティに値をセットする。<BR>
     * <BR>
     * 　@this.IPO申告行.購入申込数量 == 0<BR>
     * 　@this.IPO申告行.購入申込代金 == 0<BR>
     * 　@this.IPO申告行.購入申込区分 == ”辞退”<BR>
     * 　@this.IPO申告行.税区分 == null<BR>
     * 　@this.IPO申告行.購入申込／辞退日時 == 現在日時(*1)<BR>
     * 　@this.IPO申告行.更新者コード == (*2)<BR>
     * <BR>
     * 　@(*1)　@現在日時<BR>
     * 　@TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * 　@(*2)　@更新者コード<BR>
     * 　@　@（取引者 == null）の場合<BR>
     * 　@　@　@－口座ＩＤに該当する顧客.getAccountCode()<BR>
     * 　@　@以外の場合<BR>
     * 　@　@　@－取引者.getTraderCode()　@<BR>
     * @@param l_trader - (取引者)<BR>
     * 取引者（扱者）オブジェクト
     * @@roseuid 40DBE3C70186
     */
    public void decline(Trader l_trader) 
    {
        
        final String STR_METHOD_NAME =
            " ｄecline(Trader)";
        log.entering(STR_METHOD_NAME);
        
        this.ipoOrderParams.setApplicationQuantity(0);
        this.ipoOrderParams.setPayAmount(0);
        this.ipoOrderParams.setOfferingDiv(WEB3OfferingDivDef.REFUSAL);
        this.ipoOrderParams.setTaxType(null);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        
        this.ipoOrderParams.setOfferingTimestamp(l_processTime);
        
        String l_LastUpdater = null;
        
        if(l_trader == null)
        {
            
            l_LastUpdater = this.getMainAccount().getAccountCode();
            
        }
        else
        {
            
            l_LastUpdater = l_trader.getTraderCode();
            
        }

        
        this.ipoOrderParams.setLastUpdater(l_LastUpdater);
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (is抽選結果更新済)<BR>
     * 抽選結果項目を更新済かを判定する。<BR>
     * <BR>
     * －（this.IPO申告行.抽選結果 == 0：DEFAULT（未抽選））の場合、falseを<BR>返却する。<BR>
     * －以外、trueを返却する。
     * @@return boolean
     * @@roseuid 40F51CB200D4
     */
    public boolean isLotResultUpdated() 
    {

        return !(WEB3LotResultDef.DEFAULT).equals(ipoOrderParams.getLotResult());

    }
    
    /**
     * (get顧客コード)<BR>
     * 顧客コードを取得する。<BR>
     * <BR>
     * AccountManager.getMainAccount()にて、<BR>
     * this.get口座ＩＤ()に該当する顧客オブジェクトを取得する。<BR>
     * 取得して顧客オブジェクト.getAccountCode()を返却する。
     * @@roseuid 40F5225C0122
     */
    public String getAccountCode() 
    {
        
        final String STR_METHOD_NAME =
            " getAccountCode()";
        log.entering(STR_METHOD_NAME);
        
        MainAccount l_mainAccount = null;
        
        try
        {
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            l_mainAccount = l_finApp.getAccountManager().getMainAccount(getAccountId());
            
            log.exiting(STR_METHOD_NAME);
            return l_mainAccount.getAccountCode();
            
        }
        catch (NotFoundException l_ex)
        {
                
            String l_strMsg = "MainAccount not found accountId : " + getAccountId();
            log.error(l_strMsg, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new RuntimeSystemException(l_strMsg, l_ex); 
             
        }

    }
    
    /**
     * (createForUpdateParams ())<BR>
     * this.IPO申告行をコピーして、<BR>
     * 同じ内容の別インスタンスを作成する（clone）。<BR>
     * <BR>
     * 作成したコピーを自身のthis.IPO申告行にセットする。<BR>
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        
        IpoOrderParams l_ipoOrderParams = new IpoOrderParams(this.ipoOrderParams);
        this.ipoOrderParams = l_ipoOrderParams;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is購入申込・辞退可能)<BR>
     * 購入申込・辞退が可能かを判定する。<BR>
     * 以下の条件①@または、②に当てはまる場合、falseを返却する。<BR>
     * <BR>
     * [条件①@]　@※新規抽選で落選している場合<BR>
     * （this.is当選者() == false） && （this.is補欠者() == false）<BR>
     * <BR>
     * [条件②]　@※繰上抽選で落選している場合<BR>
     * IPO申告.is繰上落選者() == true<BR>
     * <BR>
     * <BR>
     * どちらにも当てはまらない場合、trueを返却する。<BR>
     * @@return boolean<BR>
     */
    public boolean isOfferDeclinePossible()
    {
        final String STR_METHOD_NAME = " isOfferDeclinePossible()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnOfferDeclinePossible = true;
        if (!this.isElected() && !this.isWaiting())
        {
            log.debug("新規抽選で落選している場合");
            l_blnOfferDeclinePossible = false;
        }
        if (this.isAdvanceRejected())
        {
            log.debug("繰上抽選で落選している場合");
            l_blnOfferDeclinePossible = false;
        }        
        
        log.exiting(STR_METHOD_NAME);
        return l_blnOfferDeclinePossible;
    }

}
@
