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
filename	WEB3IfoSettleContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 返済注文内容クラス(WEB3IfoSettleContractOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/17 艾興 (中訊) 新規作成
              001: 2004/07/28 王暁傑 (中訊) 対応QA WEB3-XBIFO-A-CD-0055
                 : 2006/07/12 肖志偉 (中訊) 仕様変更　@モデル460
Revesion History : 2007/06/08 孫洪江 (中訊) 仕様変更モデルNo.641
*/
package webbroker3.ifo;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoSettleContractOrderSpec;

import webbroker3.common.WEB3BaseException;

import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (返済注文内容)<BR>
 * 返済注文内容クラス<BR>
 * @@author  艾興
 * @@version 1.0
 */
public class WEB3IfoSettleContractOrderSpec extends IfoSettleContractOrderSpec 
{  
	
	/**
	 * ログ出力ユーティリティ<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3IfoSettleContractOrderSpec.class); 
		
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
     * 0：DEFAULT（条件指定なし）1：逆指値　@2：W指値<BR>
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
	 * 注文期限区分<BR>
	 * 1：当日限り　@2：出来るまで注文<BR>
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
     * @@param entries 
     * @@param price 
     * @@param execType 
     * @@param orderExpDate 
     * @@param taxType
     */
    private WEB3IfoSettleContractOrderSpec(
    	Trader trader, 
        SettleContractEntry[] entries, 
        double price,
        IfoOrderExecutionConditionType execType, 
        Date orderExpDate, 
        TaxTypeEnum taxType)
    {
        super(trader,
			entries,
			price,
			execType,
			orderExpDate,
			taxType);
    }
   
    /**
     * (create返済注文内容)<BR>
     *（staticメソッド）<BR>
     * 先物OP返済注文内容オブジェクトを生成し返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_trader - 扱者オブジェクト<BR>
     * @@param l_dblLimitPrice - 指値<BR>
     * @@param l_executeCond - 執行条件<BR>
     * @@param l_datOrderExpirationDate - 注文失効日<BR>
     * @@param l_settleContractOrderEntry - 返済建玉エントリ<BR>
     * 返済建玉エントリの配列<BR>
     * @@param l_strOrderCond - 発注条件<BR>
     * @@param l_dblStopOrderPrice - 逆指値基準値<BR>
     * @@param l_dblWLimitPrice - (W指値)訂正指値<BR>
     * @@param l_wLimitExecCondType - (W指値)執行条件<BR>
     * @@param l_strExpirationDateType - 注文期限区分<BR>
     * @@param l_lngFirstOrderUnitId - 初回注文の注文単位ID<BR>
     * @@param l_blnEveningSessionCarryoverFlag - 夕場前繰越対象フラグ<BR>
     * @@return webbroker3.ifo.WEB3IfoSettleContractOrderSpec
     * @@roseuid 4056DE8603A8
     */
    public static WEB3IfoSettleContractOrderSpec createSettleContractOrderSpec(
        String l_strInstitutionCode,
        Trader l_trader,
        double l_dblLimitPrice,
        IfoOrderExecutionConditionType l_executeCond,
        Date l_datOrderExpirationDate,
        SettleContractEntry[] l_settleContractOrderEntry,
        String l_strOrderCond,
        double l_dblStopOrderPrice,
        double l_dblWLimitPrice,
        IfoOrderExecutionConditionType l_wLimitExecCondType,
		String l_strExpirationDateType,
		Long l_lngFirstOrderUnitId,
        boolean l_blnEveningSessionCarryoverFlag)
            throws WEB3BaseException
    {
		String STR_METHOD_NAME="createSettleContractOrderSpec()";
		log.entering(STR_METHOD_NAME);
        
        if (l_datOrderExpirationDate == null)
        {
            l_datOrderExpirationDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        
        WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec = 
                new WEB3IfoSettleContractOrderSpec(
                	l_trader,
                	l_settleContractOrderEntry,
                	l_dblLimitPrice,
                	l_executeCond,
                	l_datOrderExpirationDate,
                	TaxTypeEnum.UNDEFINED);
        
        l_ifoSettleContractOrderSpec.setInstitutionCode(l_strInstitutionCode);
        l_ifoSettleContractOrderSpec.setOrderCond(l_strOrderCond);
        l_ifoSettleContractOrderSpec.setWLimitPriceChange(l_dblWLimitPrice);
        l_ifoSettleContractOrderSpec.setOrderBizDate(WEB3GentradeTradingTimeManagement.getOrderBizDate());
		l_ifoSettleContractOrderSpec.setExpirationDateType(l_strExpirationDateType);
		l_ifoSettleContractOrderSpec.setFirstOrderUnitId(l_lngFirstOrderUnitId);
        l_ifoSettleContractOrderSpec.setStopOrderPrice(l_dblStopOrderPrice);
        l_ifoSettleContractOrderSpec.setWLimitExecCondType(l_wLimitExecCondType);
        // set夕場前繰越対象フラグ(boolean)
        l_ifoSettleContractOrderSpec.setEveningSessionCarryoverFlag(l_blnEveningSessionCarryoverFlag);
        
		log.exiting(STR_METHOD_NAME);
        return l_ifoSettleContractOrderSpec;
    }

    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを取得する。<BR>
     * @@return String
     * @@roseuid 405A9EA102DA
     */
    public String getInstitutionCode() 
    {
     return this.institutionCode;
    }
    
    /**
     * (set証券会社コード)<BR>
     * set証券会社コードをセットする。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@roseuid 405A9EA102DB
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.institutionCode = l_strInstitutionCode;   
    }
    
    /**
     * (get発注日)<BR>
     * 発注日を取得する。<BR>
     * get発注日<BR>
     * @@return Date
     * @@roseuid 406CF0EC006F
     */
    public Date getOrderBizDate() 
    {
        return this.orderBizDate;
    }
    
    /**
     * (set発注日)<BR>
     * 発注日をセットする。<BR>
     * @@param l_datOrderDate - 発注日
     * @@roseuid 406CF0EC007D
     */
    public void setOrderBizDate(Date l_datOrderDate) 
    {
        this.orderBizDate = l_datOrderDate;
    }
    
    /**
     * (get発注条件)<BR>
     * 発注条件を取得する。<BR>
     * @@return String
     * @@roseuid 406CF0EC007F
     */
    public String getOrderCond() 
    {
        return this.orderCond;
    }
    
    /**
     * (set発注条件)<BR>
     * 発注条件をセットする。<BR>
     * @@param l_strOrderCond - 発注条件
     * @@roseuid 406CF0EC0080
     */
    public void setOrderCond(String l_strOrderCond) 
    {
        this.orderCond = l_strOrderCond;
    }
    
    /**
     * (get逆指値基準値)<BR>
     * 逆指値基準値を取得する。<BR>
     * @@return double
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
     * (get（W指値）訂正指値)<BR>
     * (W指値)訂正指値を取得する。<BR>
     * @@return double
     * @@roseuid 406CF0EC008C
     */
    public double getWLimitPriceChange() 
    {
        return this.wLimitPriceChange;
    }
    
    /**
     * (set（W指値）訂正指値)<BR>
     * (W指値)訂正指値をセットする。<BR>
     * @@param l_dblWLimitPrice - (W指値)訂正指値
     * @@roseuid 406CF0EC008D
     */
    public void setWLimitPriceChange(double l_dblWLimitPrice) 
    {
        this.wLimitPriceChange = l_dblWLimitPrice;
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
	 * (get注文期限区分)<BR>
	 * 注文期限区分を取得する。<BR>
	 * @@return String
	 * @@roseuid 405E71F8012E
	 */
	public String getExpirationDateType() 
	{
		return this.expirationDateType ;
	}
    
	/**
	 * (set注文期限区分)<BR>
	 * 注文期限区分をセットする。<BR>
	 * @@param l_strOrderCond - 注文期限区分<BR>
	 * @@roseuid 405E71EF02A5
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
