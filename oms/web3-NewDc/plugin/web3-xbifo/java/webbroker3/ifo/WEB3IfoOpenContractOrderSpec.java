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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �V�K���������e�N���X(WEB3IfoOpenContractOrderSpec)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11   ���Ō� (Sinocom) �V�K�쐬 
                 : 2006/07/12   �юu�� (���u) �d�l�ύX�@@���f��466 
Revesion History : 2007/06/08   ���^�] (���u) �d�l�ύX���f��No.641
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
 * (�V�K���������e)<BR>
 * �V�K���������e�N���X<BR>
 * xTrade��IfoOpenContractOrderSpec���g�������N���X�B<BR>
 * @@author  ���Ō�
 * @@version 1.0
 */
public class WEB3IfoOpenContractOrderSpec extends IfoOpenContractOrderSpec 
{

	/**
	 * ���O�o�̓��[�e�B���e�B<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3IfoOpenContractOrderSpec.class);
		
    /**
     * �،���ЃR�[�h<BR>
     */
    private String institutionCode;
    
    /**
     * ������<BR>
     */
    private Date orderBizDate;
    
    /**
     * ��������<BR>
     * �@@0�FDEFAULT�i�����w��Ȃ��j�@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    private String orderCond;
    
    /**
     * �t�w�l��l<BR>
     */
    private double stopOrderPrice;
    
    /**
     * �iW�w�l�j�����w�l<BR>
     */
    private double wLimitPriceChange;

    /**
     *�iW�w�l�j���s����<BR>
     */
    private IfoOrderExecutionConditionType wLimitExecCondType;
    
    /**
     * �����R�[�h<BR>
     */
    private String ProductCode;
      
	/**
	 * ���������敪<BR>
	 * 1�F��������@@2�F�o����܂Œ���
	 */
	private String expirationDateType;
	
    /**
     * ���񒍕��̒����P��ID<BR>
     */
    private Long firstOrderUnitId;
    
    /**
     * (�[��O�J�z�Ώۃt���O)<BR>
     * false�F�[��O�J�z�Ȃ�<BR>
     * true�F�[��O�J�z����<BR>
     */
    private boolean eveningSessionCarryoverFlag;
    
	/**
	 * �R���X�g���N�^�B
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
     * (create�V�K���������e)<BR>
     * �istatic���\�b�h�j<BR>
     * �V�K���������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_trader - ���҃I�u�W�F�N�g
     * @@param l_blnIsBuyToOpenOrder - (is����)<BR>
     * �iisBuyToOpenOrder�j<BR>
     * �������ǂ����̔���B<BR>
     * �����̏ꍇ��true�A�����̏ꍇ��false�B<BR>
     * @@param l_strMarket - �s��R�[�h<BR>
     * @@param l_product - �敨OP�����I�u�W�F�N�g<BR>
     * @@param l_dblQuantity - ���ʁi�����j<BR>
     * @@param l_dblLimitPrice - �w�l<BR>
     * @@param l_executionConditionType - ���s����
     * @@param l_datExpirationDate - ����������<BR>
     * @@param l_strOrderCond - ��������<BR>
     * @@param l_dblStopOrderBasePrice - (�t�w�l��l)<BR>
     * @@param l_dblWLimitPriceChange - (W�w�l)�����w�l<BR>
     * @@param l_wLimitExecCondType - ((W�w�l)���s����)<BR>
     * @@param l_strExpirationDateType - ���������敪<BR>
     * @@param l_lngFirstOrderUnitId - ���񒍕��̒����P��ID<BR>
     * @@param l_blnEveningSessionCarryoverFlag - �[��O�J�z�Ώۃt���O<BR>
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

        //�敨OP����ROW�I�u�W�F�N�g���擾
        IfoProductRow l_ifoProductRow = null;
        l_ifoProductRow = ((IfoProductRow)l_product.getDataSourceObject()); 
        
        if (l_datExpirationDate == null)
        {
            l_datExpirationDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        
        //�V�K���������e�I�u�W�F�N�g�𐶐�����
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
        //set�[��O�J�z�Ώۃt���O(boolean)
        l_ifoOpenContractOrderSpec.setEveningSessionCarryoverFlag(l_blnEveningSessionCarryoverFlag);
        
		log.exiting(STR_METHOD_NAME); 
        //�������ꂽ�V�K���������e�I�u�W�F�N�g��ԋp����
        return l_ifoOpenContractOrderSpec;        
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���擾����B<BR>
     * @@return String
     * @@roseuid 4051688F0069
     */
    public String getInstitutionCode() 
    {
        return this.institutionCode ;       
    }
    
    /**
     * (set�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���Z�b�g����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@roseuid 4051688F006A
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.institutionCode = l_strInstitutionCode;       
    }
    
    /**
     * (get������)<BR>
     * ���������擾����B<BR>
     * @@return Date
     * @@roseuid 405166CC0318
     */
    public Date getOrderBizDate() 
    {
        return this.orderBizDate ;        
    }
    
    /**
     * (set������)<BR>
     * ���������Z�b�g����B<BR>
     * @@param l_datOrderBizDate - ������<BR>
     * @@roseuid 405166E502BB
     */
    public void setOrderBizDate(Date l_datOrderBizDate) 
    {
        this.orderBizDate = l_datOrderBizDate;        
    }
    
    /**
     * (get��������)<BR>
     * �����������擾����B<BR>
     * @@return String
     * @@roseuid 405E71F8012E
     */
	public String getOrderCond() 
	{
		return this.orderCond ;
	}
    
    /**
     * (set��������)<BR>
     * �����������Z�b�g����B<BR>
     * @@param l_strOrderCond - ��������<BR>
     * @@roseuid 405E71EF02A5
     */
	public void setOrderCond(String l_strOrderCond) 
	{
		this.orderCond = l_strOrderCond;        
	}
    
    /**
     * (get�iW�w�l�j�����w�l)<BR>
     * (W�w�l)�����w�l���擾����B<BR>
     * @@return double
     * @@roseuid 406CED970198
     */
    public double getWLimitPriceChange() 
    {
        return this.wLimitPriceChange ;        
    }
    
    /**
     * (set�iW�w�l�j�����w�l)<BR>
     * (W�w�l)�����w�l���Z�b�g����B<BR>
     * @@param l_dblWLimitPriceChange - (W�w�l)�����w�l<BR>
     * @@roseuid 406CED970196
     */
    public void setWLimitPriceChange(double l_dblWLimitPriceChange) 
    {
        this.wLimitPriceChange = l_dblWLimitPriceChange;        
    }
	
    /**
     * (get�����R�[�h)<BR>
     * �����R�[�h���擾����B<BR>
     * @@return String
     * @@roseuid 406CED970198
     */
    public String getProductCode() 
    {
        return this.ProductCode;        
    }
    
    /**
     * (set�����R�[�h)<BR>
     * �����R�[�h���Z�b�g����B<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@roseuid 406CED970196
     */
    public void setProductCode(String l_strProductCode) 
    {
        this.ProductCode = l_strProductCode;        
    }
    
	/**
	 * (get���������敪)<BR>
	 * ���������敪���擾����B<BR>
	 * @@return String
	 */
	public String getExpirationDateType() 
	{
		return this.expirationDateType ;
	}
    
	/**
	 * (set���������敪)<BR>
	 * ���������敪���Z�b�g����B<BR>
	 * @@param l_strOrderCond - ���������敪<BR>
	 */
	public void setExpirationDateType(String l_strExpirationDateType) 
	{
		this.expirationDateType = l_strExpirationDateType;        
	}
    
	/**
	 * (get���񒍕��̒����P��ID)<BR>
	 * ���񒍕��̒����P��ID���擾����B<BR>
	 * @@return String
	 */
	public Long getFirstOrderUnitId() 
	{
		return this.firstOrderUnitId;        
	}
    
	/**
	 * (set���񒍕��̒����P��ID)<BR>
	 * ���񒍕��̒����P��ID���Z�b�g����B<BR>
	 * @@param l_lngFirstOrderUnitId - ���񒍕��̒����P��ID<BR>
	 */
	public void setFirstOrderUnitId(Long l_lngFirstOrderUnitId) 
	{
		this.firstOrderUnitId = l_lngFirstOrderUnitId;        
	}
    
    /**
     *(get�t�w�l��l)<BR>
     *�t�w�l��l���擾����B<BR>
     *@@return double
     */
    public double getStopOrderPrice()
    {
        return this.stopOrderPrice;
    }
    
    /**
     * (set�t�w�l��l)<BR>
     * �t�w�l��l���Z�b�g����B<BR>
     * @@param l_dblStopOrderPrice - (�t�w�l��l)<BR>
     * �t�w�l��l
     */
    public void setStopOrderPrice(double l_dblStopOrderPrice)
    {
        this.stopOrderPrice = l_dblStopOrderPrice;
    }
    
    /**
     * (get�iW�w�l�j���s����)<BR>
     * �iW�w�l�j���s�������擾����B<BR>
     * @@return IfoOrderExecutionConditionType
     */
    public IfoOrderExecutionConditionType getWLimitExecCondType()
    {
        return this.wLimitExecCondType;
    }
    
    /**
     * (set�iW�w�l�j���s����)<BR>
     * �iW�w�l�j���s�������Z�b�g����B<BR>
     * @@param l_wLimitExecCondType - ((W�w�l)���s����)<BR>
     * �iW�w�l�j���s���� 
     */
    public void setWLimitExecCondType(
        IfoOrderExecutionConditionType l_wLimitExecCondType)
    {
        this.wLimitExecCondType = l_wLimitExecCondType;
    }
    
    /**
     * (get�[��O�J�z�Ώۃt���O)<BR>
     * �[��O�J�z�Ώۃt���O���擾����B<BR>
     * @@return boolean
     */
    public boolean getEveningSessionCarryoverFlag()
    {
        return this.eveningSessionCarryoverFlag;
    }

    /**
     * (set�[��O�J�z�Ώۃt���O)<BR>
     * �[��O�J�z�Ώۃt���O���Z�b�g����B<BR>
     * @@param l_blnEveningSessionCarryoverFlag - (�[��O�J�z�Ώۃt���O)<BR>
     */
    public void setEveningSessionCarryoverFlag(boolean l_blnEveningSessionCarryoverFlag)
    {
        this.eveningSessionCarryoverFlag = l_blnEveningSessionCarryoverFlag;
    }
}
@
