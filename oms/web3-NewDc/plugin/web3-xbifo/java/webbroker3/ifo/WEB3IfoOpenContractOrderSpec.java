head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOpenContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 新規建注文内容クラス(WEB3IfoOpenContractOrderSpec)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11   王暁傑 (Sinocom) 新規作成 
                 : 2006/07/12   肖志偉 (中訊) 仕様変更　@モデル466 
Revesion History : 2007/06/08   孫洪江 (中訊) 仕様変更モデルNo.641
*/
package webbroker3.ifo;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;

import webbroker3.common.WEB3BaseException;

import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (新規建注文内容)<BR>
 * 新規建注文内容クラス<BR>
 * xTradeのIfoOpenContractOrderSpecを拡張したクラス。<BR>
 * @@author  王暁傑
 * @@version 1.0
 */
public class WEB3IfoOpenContractOrderSpec extends IfoOpenContractOrderSpec 
{

	/**
	 * ログ出力ユーティリティ<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3IfoOpenContractOrderSpec.class);
		
    /**
     * 証券会社コード<BR>
     */
    private String institutionCode;
    
    /**
     * 発注日<BR>
     */
    private Date orderBizDate;
    
    /**
     * 発注条件<BR>
     * 　@0：DEFAULT（条件指定なし）　@1：逆指値　@2：W指値<BR>
     */
    private String orderCond;
    
    /**
     * 逆指値基準値<BR>
     */
    private double stopOrderPrice;
    
    /**
     * （W指値）訂正指値<BR>
     */
    private double wLimitPriceChange;

    /**
     *（W指値）執行条件<BR>
     */
    private IfoOrderExecutionConditionType wLimitExecCondType;
    
    /**
     * 銘柄コード<BR>
     */
    private String ProductCode;
      
	/**
	 * 注文期限区分<BR>
	 * 1：当日限り　@2：出来るまで注文
	 */
	private String expirationDateType;
	
    /**
     * 初回注文の注文単位ID<BR>
     */
    private Long firstOrderUnitId;
    
    /**
     * (夕場前繰越対象フラグ)<BR>
     * false：夕場前繰越なし<BR>
     * true：夕場前繰越あり<BR>
     */
    private boolean eveningSessionCarryoverFlag;
    
	/**
	 * コンストラクタ。
	 * @@param trader
	 * @@param isBuyToOpen
	 * @@param underlyingProductCode
	 * @@param derivativeType
	 * @@param monthOfDelivery
	 * @@param strikePrice
	 * @@param marketCode
	 * @@param quantity
	 * @@param limitPrice
	 * @@param execType
	 * @@param orderExpDate
	 * @@param taxType
	 */
	private WEB3IfoOpenContractOrderSpec(
		Trader trader,
		boolean isBuyToOpen,
		String underlyingProductCode,
		IfoDerivativeTypeEnum derivativeType,
		String monthOfDelivery,
		double strikePrice,
		String marketCode,
		double quantity,
		double limitPrice ,
		IfoOrderExecutionConditionType execType ,
		Date orderExpDate,
		TaxTypeEnum taxType) 
	{
		super(trader,
			isBuyToOpen,
			underlyingProductCode,
			derivativeType ,
			monthOfDelivery ,
			strikePrice,
			marketCode,
			quantity ,
			limitPrice ,
			execType ,
			orderExpDate,
			taxType);
	} 
    
    /**
     * (create新規建注文内容)<BR>
     * （staticメソッド）<BR>
     * 新規建注文内容オブジェクトを生成し返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_trader - 扱者オブジェクト
     * @@param l_blnIsBuyToOpenOrder - (is買建)<BR>
     * （isBuyToOpenOrder）<BR>
     * 買建かどうかの判定。<BR>
     * 買建の場合はtrue、売建の場合はfalse。<BR>
     * @@param l_strMarket - 市場コード<BR>
     * @@param l_product - 先物OP銘柄オブジェクト<BR>
     * @@param l_dblQuantity - 数量（枚数）<BR>
     * @@param l_dblLimitPrice - 指値<BR>
     * @@param l_executionConditionType - 執行条件
     * @@param l_datExpirationDate - 注文失効日<BR>
     * @@param l_strOrderCond - 発注条件<BR>
     * @@param l_dblStopOrderBasePrice - (逆指値基準値)<BR>
     * @@param l_dblWLimitPriceChange - (W指値)訂正指値<BR>
     * @@param l_wLimitExecCondType - ((W指値)執行条件)<BR>
     * @@param l_strExpirationDateType - 注文期限区分<BR>
     * @@param l_lngFirstOrderUnitId - 初回注文の注文単位ID<BR>
     * @@param l_blnEveningSessionCarryoverFlag - 夕場前繰越対象フラグ<BR>
     * @@return WEB3IfoOpenContractOrderSpec
     * @@roseuid 405134890182
     */
    public static  WEB3IfoOpenContractOrderSpec createOpenContractOrderSpec(
        String l_strInstitutionCode, 
        Trader l_trader, 
        boolean l_blnIsBuyToOpenOrder, 
        String l_strMarket, 
        WEB3IfoProductImpl l_product, 
        double l_dblQuantity, 
        double l_dblLimitPrice, 
        IfoOrderExecutionConditionType l_executionConditionType, 
        Date l_datExpirationDate, 
        String l_strOrderCond, 
        double l_dblStopOrderBasePrice,
        double l_dblWLimitPriceChange,
        IfoOrderExecutionConditionType l_wLimitExecCondType,
        String l_strExpirationDateType, 
        Long l_lngFirstOrderUnitId,
        boolean l_blnEveningSessionCarryoverFlag)
            throws WEB3BaseException
    {
		String STR_METHOD_NAME="createOpenContractOrderSpec()";
		log.entering(STR_METHOD_NAME); 

        //先物OP銘柄ROWオブジェクトを取得
        IfoProductRow l_ifoProductRow = null;
        l_ifoProductRow = ((IfoProductRow)l_product.getDataSourceObject()); 
        
        if (l_datExpirationDate == null)
        {
            l_datExpirationDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        
        //新規建注文内容オブジェクトを生成する
        //WEB3IfoOpenContractOrderSpec
        WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec =  
            new WEB3IfoOpenContractOrderSpec(
                l_trader,
                l_blnIsBuyToOpenOrder,
                l_product.getUnderlyingProductCode(),
                l_ifoProductRow.getDerivativeType(),
                l_ifoProductRow.getMonthOfDelivery(),
                l_ifoProductRow.getStrikePrice(),
                l_strMarket,
                l_dblQuantity,
                l_dblLimitPrice,
                l_executionConditionType,
                l_datExpirationDate,
                TaxTypeEnum.UNDEFINED 
                );
                
        l_ifoOpenContractOrderSpec.setProductCode(l_ifoProductRow.getProductCode());
        l_ifoOpenContractOrderSpec.setInstitutionCode( l_strInstitutionCode);
        l_ifoOpenContractOrderSpec.setOrderCond( l_strOrderCond);
        l_ifoOpenContractOrderSpec.setStopOrderPrice(l_dblStopOrderBasePrice);
        l_ifoOpenContractOrderSpec.setWLimitExecCondType(l_wLimitExecCondType);
        l_ifoOpenContractOrderSpec.setWLimitPriceChange( l_dblWLimitPriceChange);
        l_ifoOpenContractOrderSpec.setOrderBizDate( WEB3GentradeTradingTimeManagement.getOrderBizDate());
		l_ifoOpenContractOrderSpec.setExpirationDateType( l_strExpirationDateType);
		l_ifoOpenContractOrderSpec.setFirstOrderUnitId(l_lngFirstOrderUnitId);
        //set夕場前繰越対象フラグ(boolean)
        l_ifoOpenContractOrderSpec.setEveningSessionCarryoverFlag(l_blnEveningSessionCarryoverFlag);
        
		log.exiting(STR_METHOD_NAME); 
        //生成された新規建注文内容オブジェクトを返却する
        return l_ifoOpenContractOrderSpec;        
    }
    
    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを取得する。<BR>
     * @@return String
     * @@roseuid 4051688F0069
     */
    public String getInstitutionCode() 
    {
        return this.institutionCode ;       
    }
    
    /**
     * (set証券会社コード)<BR>
     * 証券会社コードをセットする。<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@roseuid 4051688F006A
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.institutionCode = l_strInstitutionCode;       
    }
    
    /**
     * (get発注日)<BR>
     * 発注日を取得する。<BR>
     * @@return Date
     * @@roseuid 405166CC0318
     */
    public Date getOrderBizDate() 
    {
        return this.orderBizDate ;        
    }
    
    /**
     * (set発注日)<BR>
     * 発注日をセットする。<BR>
     * @@param l_datOrderBizDate - 発注日<BR>
     * @@roseuid 405166E502BB
     */
    public void setOrderBizDate(Date l_datOrderBizDate) 
    {
        this.orderBizDate = l_datOrderBizDate;        
    }
    
    /**
     * (get発注条件)<BR>
     * 発注条件を取得する。<BR>
     * @@return String
     * @@roseuid 405E71F8012E
     */
	public String getOrderCond() 
	{
		return this.orderCond ;
	}
    
    /**
     * (set発注条件)<BR>
     * 発注条件をセットする。<BR>
     * @@param l_strOrderCond - 発注条件<BR>
     * @@roseuid 405E71EF02A5
     */
	public void setOrderCond(String l_strOrderCond) 
	{
		this.orderCond = l_strOrderCond;        
	}
    
    /**
     * (get（W指値）訂正指値)<BR>
     * (W指値)訂正指値を取得する。<BR>
     * @@return double
     * @@roseuid 406CED970198
     */
    public double getWLimitPriceChange() 
    {
        return this.wLimitPriceChange ;        
    }
    
    /**
     * (set（W指値）訂正指値)<BR>
     * (W指値)訂正指値をセットする。<BR>
     * @@param l_dblWLimitPriceChange - (W指値)訂正指値<BR>
     * @@roseuid 406CED970196
     */
    public void setWLimitPriceChange(double l_dblWLimitPriceChange) 
    {
        this.wLimitPriceChange = l_dblWLimitPriceChange;        
    }
	
    /**
     * (get銘柄コード)<BR>
     * 銘柄コードを取得する。<BR>
     * @@return String
     * @@roseuid 406CED970198
     */
    public String getProductCode() 
    {
        return this.ProductCode;        
    }
    
    /**
     * (set銘柄コード)<BR>
     * 銘柄コードをセットする。<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@roseuid 406CED970196
     */
    public void setProductCode(String l_strProductCode) 
    {
        this.ProductCode = l_strProductCode;        
    }
    
	/**
	 * (get注文期限区分)<BR>
	 * 注文期限区分を取得する。<BR>
	 * @@return String
	 */
	public String getExpirationDateType() 
	{
		return this.expirationDateType ;
	}
    
	/**
	 * (set注文期限区分)<BR>
	 * 注文期限区分をセットする。<BR>
	 * @@param l_strOrderCond - 注文期限区分<BR>
	 */
	public void setExpirationDateType(String l_strExpirationDateType) 
	{
		this.expirationDateType = l_strExpirationDateType;        
	}
    
	/**
	 * (get初回注文の注文単位ID)<BR>
	 * 初回注文の注文単位IDを取得する。<BR>
	 * @@return String
	 */
	public Long getFirstOrderUnitId() 
	{
		return this.firstOrderUnitId;        
	}
    
	/**
	 * (set初回注文の注文単位ID)<BR>
	 * 初回注文の注文単位IDをセットする。<BR>
	 * @@param l_lngFirstOrderUnitId - 初回注文の注文単位ID<BR>
	 */
	public void setFirstOrderUnitId(Long l_lngFirstOrderUnitId) 
	{
		this.firstOrderUnitId = l_lngFirstOrderUnitId;        
	}
    
    /**
     *(get逆指値基準値)<BR>
     *逆指値基準値を取得する。<BR>
     *@@return double
     */
    public double getStopOrderPrice()
    {
        return this.stopOrderPrice;
    }
    
    /**
     * (set逆指値基準値)<BR>
     * 逆指値基準値をセットする。<BR>
     * @@param l_dblStopOrderPrice - (逆指値基準値)<BR>
     * 逆指値基準値
     */
    public void setStopOrderPrice(double l_dblStopOrderPrice)
    {
        this.stopOrderPrice = l_dblStopOrderPrice;
    }
    
    /**
     * (get（W指値）執行条件)<BR>
     * （W指値）執行条件を取得する。<BR>
     * @@return IfoOrderExecutionConditionType
     */
    public IfoOrderExecutionConditionType getWLimitExecCondType()
    {
        return this.wLimitExecCondType;
    }
    
    /**
     * (set（W指値）執行条件)<BR>
     * （W指値）執行条件をセットする。<BR>
     * @@param l_wLimitExecCondType - ((W指値)執行条件)<BR>
     * （W指値）執行条件 
     */
    public void setWLimitExecCondType(
        IfoOrderExecutionConditionType l_wLimitExecCondType)
    {
        this.wLimitExecCondType = l_wLimitExecCondType;
    }
    
    /**
     * (get夕場前繰越対象フラグ)<BR>
     * 夕場前繰越対象フラグを取得する。<BR>
     * @@return boolean
     */
    public boolean getEveningSessionCarryoverFlag()
    {
        return this.eveningSessionCarryoverFlag;
    }

    /**
     * (set夕場前繰越対象フラグ)<BR>
     * 夕場前繰越対象フラグをセットする。<BR>
     * @@param l_blnEveningSessionCarryoverFlag - (夕場前繰越対象フラグ)<BR>
     */
    public void setEveningSessionCarryoverFlag(boolean l_blnEveningSessionCarryoverFlag)
    {
        this.eveningSessionCarryoverFlag = l_blnEveningSessionCarryoverFlag;
    }
}
@
