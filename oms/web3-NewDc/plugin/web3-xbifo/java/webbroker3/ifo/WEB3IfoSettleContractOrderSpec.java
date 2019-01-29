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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ԍϒ������e�N���X(WEB3IfoSettleContractOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/17 䈋� (���u) �V�K�쐬
              001: 2004/07/28 ���Ō� (���u) �Ή�QA WEB3-XBIFO-A-CD-0055
                 : 2006/07/12 �юu�� (���u) �d�l�ύX�@@���f��460
Revesion History : 2007/06/08 ���^�] (���u) �d�l�ύX���f��No.641
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
 * (�ԍϒ������e)<BR>
 * �ԍϒ������e�N���X<BR>
 * @@author  䈋�
 * @@version 1.0
 */
public class WEB3IfoSettleContractOrderSpec extends IfoSettleContractOrderSpec 
{  
	
	/**
	 * ���O�o�̓��[�e�B���e�B<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3IfoSettleContractOrderSpec.class); 
		
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
     * 0�FDEFAULT�i�����w��Ȃ��j1�F�t�w�l�@@2�FW�w�l<BR>
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
	 * ���������敪<BR>
	 * 1�F��������@@2�F�o����܂Œ���<BR>
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
     * (create�ԍϒ������e)<BR>
     *�istatic���\�b�h�j<BR>
     * �敨OP�ԍϒ������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_trader - ���҃I�u�W�F�N�g<BR>
     * @@param l_dblLimitPrice - �w�l<BR>
     * @@param l_executeCond - ���s����<BR>
     * @@param l_datOrderExpirationDate - ����������<BR>
     * @@param l_settleContractOrderEntry - �ԍό��ʃG���g��<BR>
     * �ԍό��ʃG���g���̔z��<BR>
     * @@param l_strOrderCond - ��������<BR>
     * @@param l_dblStopOrderPrice - �t�w�l��l<BR>
     * @@param l_dblWLimitPrice - (W�w�l)�����w�l<BR>
     * @@param l_wLimitExecCondType - (W�w�l)���s����<BR>
     * @@param l_strExpirationDateType - ���������敪<BR>
     * @@param l_lngFirstOrderUnitId - ���񒍕��̒����P��ID<BR>
     * @@param l_blnEveningSessionCarryoverFlag - �[��O�J�z�Ώۃt���O<BR>
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
        // set�[��O�J�z�Ώۃt���O(boolean)
        l_ifoSettleContractOrderSpec.setEveningSessionCarryoverFlag(l_blnEveningSessionCarryoverFlag);
        
		log.exiting(STR_METHOD_NAME);
        return l_ifoSettleContractOrderSpec;
    }

    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���擾����B<BR>
     * @@return String
     * @@roseuid 405A9EA102DA
     */
    public String getInstitutionCode() 
    {
     return this.institutionCode;
    }
    
    /**
     * (set�،���ЃR�[�h)<BR>
     * set�،���ЃR�[�h���Z�b�g����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@roseuid 405A9EA102DB
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.institutionCode = l_strInstitutionCode;   
    }
    
    /**
     * (get������)<BR>
     * ���������擾����B<BR>
     * get������<BR>
     * @@return Date
     * @@roseuid 406CF0EC006F
     */
    public Date getOrderBizDate() 
    {
        return this.orderBizDate;
    }
    
    /**
     * (set������)<BR>
     * ���������Z�b�g����B<BR>
     * @@param l_datOrderDate - ������
     * @@roseuid 406CF0EC007D
     */
    public void setOrderBizDate(Date l_datOrderDate) 
    {
        this.orderBizDate = l_datOrderDate;
    }
    
    /**
     * (get��������)<BR>
     * �����������擾����B<BR>
     * @@return String
     * @@roseuid 406CF0EC007F
     */
    public String getOrderCond() 
    {
        return this.orderCond;
    }
    
    /**
     * (set��������)<BR>
     * �����������Z�b�g����B<BR>
     * @@param l_strOrderCond - ��������
     * @@roseuid 406CF0EC0080
     */
    public void setOrderCond(String l_strOrderCond) 
    {
        this.orderCond = l_strOrderCond;
    }
    
    /**
     * (get�t�w�l��l)<BR>
     * �t�w�l��l���擾����B<BR>
     * @@return double
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
     * (get�iW�w�l�j�����w�l)<BR>
     * (W�w�l)�����w�l���擾����B<BR>
     * @@return double
     * @@roseuid 406CF0EC008C
     */
    public double getWLimitPriceChange() 
    {
        return this.wLimitPriceChange;
    }
    
    /**
     * (set�iW�w�l�j�����w�l)<BR>
     * (W�w�l)�����w�l���Z�b�g����B<BR>
     * @@param l_dblWLimitPrice - (W�w�l)�����w�l
     * @@roseuid 406CF0EC008D
     */
    public void setWLimitPriceChange(double l_dblWLimitPrice) 
    {
        this.wLimitPriceChange = l_dblWLimitPrice;
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
	 * (get���������敪)<BR>
	 * ���������敪���擾����B<BR>
	 * @@return String
	 * @@roseuid 405E71F8012E
	 */
	public String getExpirationDateType() 
	{
		return this.expirationDateType ;
	}
    
	/**
	 * (set���������敪)<BR>
	 * ���������敪���Z�b�g����B<BR>
	 * @@param l_strOrderCond - ���������敪<BR>
	 * @@roseuid 405E71EF02A5
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
