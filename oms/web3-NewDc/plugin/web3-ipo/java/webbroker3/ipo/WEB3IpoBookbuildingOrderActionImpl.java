head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingOrderActionImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ブックビルディング申告履歴(WEB3IpoBookbuildingOrderActionImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo;

import java.sql.Timestamp;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.data.IpoBookbuildingOrderActionDao;
import webbroker3.ipo.data.IpoBookbuildingOrderActionParams;
import webbroker3.util.WEB3LogUtility;

/**
 * ブックビルディング申告履歴
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingOrderActionImpl implements OrderAction 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingOrderActionImpl.class);           
    /**
     * (ブックビルディング申告履歴行)<BR>
     * ブックビルディング申告履歴行オブジェクト<BR>
     * ※ ブックビルディング申告履歴ParamsクラスはDDLより自動生成する。
     */
    private IpoBookbuildingOrderActionParams ipoBookbuildingOrderActionParams;
    
    /**
     * @@roseuid 411308340380
     */
    public WEB3IpoBookbuildingOrderActionImpl() 
    {
     
    }
    
    /**
     * (ブックビルディング申告履歴)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 指定したブックビルディング申告履歴ＩＤに該当する行をブックビルディング<BR>
     * 申告履歴テーブルより検索する。<BR>
     * 検索結果のブックビルディング申告履歴行オブジェクトを引数に指定して、<BR>
     * コンストラクタをコールする。<BR>
     * コンストラクタの戻り値を返却する。
     * @@param l_lngBookbuildingOrderActionId - ブックビルディング申告履歴ID
     * @@return webbroker3.ipo.WEB3IpoBookbuildingOrderActionImpl
     * @@roseuid 40BFFBC3039A
     */
    public WEB3IpoBookbuildingOrderActionImpl(long l_lngBookbuildingOrderActionId) 
    {
        final String STR_METHOD_NAME = " WEB3IpoBookbuildingOrderActionImpl(long)";
        try
        {
            this.ipoBookbuildingOrderActionParams = (IpoBookbuildingOrderActionParams)
                IpoBookbuildingOrderActionDao.findRowByPk(l_lngBookbuildingOrderActionId);//DataFindException, DataQueryException, DataNetworkException
        }
        catch (DataFindException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
        catch (DataQueryException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
    }
    
    /**
     * (ブックビルディング申告履歴)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 行指定行オブジェクトをプロパティにセットし、インスタンスを生成する。
     * @@param l_bookbuildingOrderActionParams - (ブックビルディング申告履歴Params)<BR>
     * ブックビルディング申告履歴行オブジェクト<BR>
     * ※ ブックビルディング申告履歴ParamsクラスはDDLより自動生成する。
     * 
     * @@return webbroker3.ipo.WEB3IpoBookbuildingOrderActionImpl
     * @@roseuid 40BFFBC3037B
     */
    public WEB3IpoBookbuildingOrderActionImpl(Row l_bookbuildingOrderActionParams) 
    {
        final String STR_METHOD_NAME = " WEB3IpoBookbuildingOrderActionImpl(Row)";
        log.entering(STR_METHOD_NAME);
        
        if(!(l_bookbuildingOrderActionParams instanceof IpoBookbuildingOrderActionParams))
        {
            String l_strErrorMessage = 
                "パラメータの類型が不正、該当する'IpoBookbuildingOrderActionParams' 類型。";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }

        this.ipoBookbuildingOrderActionParams = (IpoBookbuildingOrderActionParams)l_bookbuildingOrderActionParams;
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （getDataSourceObjectの実装）<BR>
     * <BR>
     * this.ブックビルディング申告履歴行を返却する。
     * @@return Object
     * @@roseuid 40BFFAAB0223
     */
    public Object getDataSourceObject() 
    {
        return this.ipoBookbuildingOrderActionParams;
    }
    
    /**
     * (getブックビルディング申告履歴ＩＤ)<BR>
     * （getOrderActionIdの実装）<BR>
     * <BR>
     * this.ブックビルディング申告履歴行.ブックビルディング申告履歴ＩＤ を返却する。
     * @@return long
     * @@roseuid 40BFFAAB0242
     */
    public long getOrderActionId() 
    {
        return this.ipoBookbuildingOrderActionParams.bookbuilding_order_action_id ;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * 0を返却する。
     * @@return long
     * @@roseuid 40BFFAAB0262
     */
    public long getOrderId() 
    {
         return 0;
    }
    
    /**
     * (get申告履歴番号)<BR>
     * （getOrderActionSerialNoの実装）<BR>
     * <BR>
     * this.ブックビルディング申告履歴行.申告履歴番号 を返却する。
     * @@return int
     * @@roseuid 40BFFAAB0290
     */
    public int getOrderActionSerialNo() 
    {
        return this.ipoBookbuildingOrderActionParams.order_action_serial_no;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * falseを返却する。
     * @@return boolean
     * @@roseuid 40BFFAAB02B0
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
     * @@roseuid 40BFFAAB02BF
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
     * @@roseuid 40BFFAAB02CF
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
     * @@roseuid 40BFFAAB02DF
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
     * @@roseuid 40BFFAAB02EE
     */
    public double getExecutionQuantity() 
    {
        return 0;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * 0を返却する。
     * @@return double
     * @@roseuid 40BFFAAB02FE
     */
    public double getExecutionPrice() 
    {
        return 0;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * 0を返却する。
     * @@return double
     * @@roseuid 40BFFAAB032D
     */
    public double getConfirmedQuantity() 
    {
        return 0;
    }
    
    /**
     * (get指値)<BR>
     * （getPriceの実装）<BR>
     * <BR>
     * this.ブックビルディング申告履歴行.指値 を返却する。
     * @@return double
     * @@roseuid 40BFFAAB033C
     */
    public double getPrice() 
    {
        if(this.ipoBookbuildingOrderActionParams.getLimitPriceIsSet())
        {
            return this.ipoBookbuildingOrderActionParams.getLimitPrice();
        }
        else
        {
            return 0.0D/0.0D; 
        }
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * 0を返却する。
     * @@return double
     * @@roseuid 40BFFAAB035C
     */
    public double getConfirmedPrice() 
    {
        return 0;
    }
    
    /**
     * (get作成日時)<BR>
     * （getOrderActionTimestampの実装）<BR>
     * <BR>
     * this.ブックビルディング申告履歴行.作成日時 を返却する。
     * @@return Timestamp
     * @@roseuid 40BFFAAB037B
     */
    public Timestamp getOrderActionTimestamp() 
    {
        return this.ipoBookbuildingOrderActionParams.created_timestamp;
    }
    
    /**
     * SideEnum.BUYを返却する。
     * @@return SideEnum
     * @@roseuid 40BFFAAB038A
     */
    public SideEnum getSide() 
    {
        return SideEnum.BUY;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return OrderExpirationStatusEnum
     * @@roseuid 40BFFAAB03AA
     */
    public OrderExpirationStatusEnum getExpirationStatus() 
    {
        return null;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return OrderTypeEnum
     * @@roseuid 40BFFAAB03B9
     */
    public OrderTypeEnum getOrderType() 
    {
        return null;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return OrderEventTypeEnum
     * @@roseuid 40BFFAAB03C9
     */
    public OrderEventTypeEnum getOrderEventType() 
    {
        return null;
    }
    
    /**
     * (getブックビルディング申告状態)<BR>
     * （getOrderStatusの実装）<BR>
     * <BR>
     * this.ブックビルディング申告履歴行.ブックビルディング申告状態 を返却する。
     * @@return OrderStatusEnum
     * @@roseuid 40BFFAAB03D9
     */
    public OrderStatusEnum getOrderStatus() 
    {
        return this.ipoBookbuildingOrderActionParams.order_status;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return TradedProduct
     * @@roseuid 40BFFAAC0000
     */
    public TradedProduct getTradedProduct() 
    {
        return null;
    }
    
    /**
     * ブックビルディング申告履歴ＩＤを新規採番(*1)し、this.ブックビルディング<BR>
     * 申告履歴行.ブックビルディング申告履歴ＩＤにセットする。<BR>
     * <BR>
     * (*1) ブックビルディング申告履歴ＩＤの新規採番<BR>
     * 　@ブックビルディング申告履歴DAO.newPkValue()メソッドにて取得する。<BR>
     * 　@※ ブックビルディング申告履歴DAOクラスはDDLより自動生成する。
     * @@roseuid 40C03E6301EF
     */
    public void setNewId() 
    {
        final String STR_METHOD_NAME = " setNewId()"; 
        
        try
        {
            this.ipoBookbuildingOrderActionParams.bookbuilding_order_action_id =
                IpoBookbuildingOrderActionDao.newPkValue();//DataQueryException, DataNetworkException
        }
        catch (DataNetworkException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
        catch (DataQueryException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
    }
    
    /**
     * (getブックビルディング申告ＩＤ)<BR>
     * （getOrderUnitIdの実装）<BR>
     * <BR>
     * this.ブックビルディング申告履歴行.ブックビルディング申告ＩＤ を返却する。
     * @@return long
     * @@roseuid 40D15E330342
     */
    public long getOrderUnitId() 
    {
        return this.ipoBookbuildingOrderActionParams.bookbuilding_order_action_id ;
    }
    
    /**
     * (get口座ID)<BR>
     * （getAccountIdの実装）<BR>
     * <BR>
     * this.ブックビルディング申告履歴行.口座ＩＤ を返却する。
     * @@return long
     * @@roseuid 40D15E4A014E
     */
    public long getAccountId() 
    {
        return this.ipoBookbuildingOrderActionParams.account_id;
    }
    
    /**
     * (get補助口座ID)<BR>
     * （getSubAccountIdの実装）<BR>
     * <BR>
     * this.ブックビルディング申告履歴行.補助口座ＩＤ を返却する。
     * @@return long
     * @@roseuid 40D15E56015E
     */
    public long getSubAccountId() 
    {
         return this.ipoBookbuildingOrderActionParams.sub_account_id;
    }
    
    /**
     * (get数量)<BR>
     * （getQuantityの実装）<BR>
     * <BR>
     * this.ブックビルディング申告履歴行.数量 を返却する。
     * @@return double
     * @@roseuid 40D15E9A0229
     */
    public double getQuantity() 
    {
        if(this.ipoBookbuildingOrderActionParams.getQuantityIsSet())
        {
            return this.ipoBookbuildingOrderActionParams.getQuantity();
        }
        else
        {
            return 0.0D/0.0D;
        }
             
    }
    
    /**
     * (is成行)<BR>
     * （isMarketOrderの実装）<BR>
     * <BR>
     * －（this.ブックビルディング申告履歴行.指値 == 0）の場合、trueを返却する。<BR>
     * －以外、falseを返却する。
     * @@return boolean
     * @@roseuid 40D15F2A02C5
     */
    public boolean isMarketOrder() 
    {
        return this.ipoBookbuildingOrderActionParams.getLimitPrice() == 0;
    }
    
    /**
     * (getIPO銘柄)<BR>
     * （getProductの実装）<BR>
     * <BR>
     * this.ブックビルディング申告履歴行.IPO銘柄IDに該当するIPO銘柄オブジェクトを<BR>返却する。<BR>
     * <BR>
     * １）　@IPO銘柄IDを指定し、IPO銘柄を生成する。<BR>
     * 　@[コンストラクタの引数]<BR>
     * 　@IPO銘柄ID：　@this.ブックビルディング申告行.IPO銘柄ID<BR>
     * <BR>
     * ２）　@生成したオブジェクトを返却する。
     * @@return Product
     * @@roseuid 40D15F490286
     */
    public Product getProduct()
    {
        final String STR_METHOD_NAME = " getProduct()";
        log.entering(STR_METHOD_NAME);
        
        long l_lngIpoProductID = this.ipoBookbuildingOrderActionParams.getIpoProductId();
        Product l_ipoProduct = null;
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);
            WEB3IpoProductManagerImpl l_productManager = 
                (WEB3IpoProductManagerImpl)l_tradingModule.getProductManager();
                
            l_productManager.getProduct(l_lngIpoProductID);
        }
        catch (NotFoundException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }

        
        log.exiting(STR_METHOD_NAME);
        return l_ipoProduct ;
    }
    
    /**
     * (createForUpdateParams ())<BR>
     * this.ブックビルディング申告履歴行をコピーして、<BR>
     * 同じ内容の別インスタンスを作成する（clone）。<BR>
     * <BR>
     * 作成したコピーを自身のthis.ブックビルディング申告履歴行にセットする。 <BR>
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        IpoBookbuildingOrderActionParams l_ipoProductParams = new IpoBookbuildingOrderActionParams(this.ipoBookbuildingOrderActionParams);
        this.ipoBookbuildingOrderActionParams = l_ipoProductParams;
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
