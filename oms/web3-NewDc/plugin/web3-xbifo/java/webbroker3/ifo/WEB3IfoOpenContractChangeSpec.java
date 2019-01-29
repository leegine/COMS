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
filename	WEB3IfoOpenContractChangeSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �V�K���������e(WEB3IfoOpenContractChangeSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/10 䈋� (���u) �V�K�쐬
                 : 2006/7/13 �юu�� (���u) �d�l�ύX�@@���f��469,478
Revesion History : 2007/06/11 �Ј��� (���u) �d�l�ύX���f��No.677
*/
package webbroker3.ifo;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeOpenContractOrderSpec;

import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �V�K���������e<BR>
 * �V�K���������e�N���X<BR>
 * �V�K���������e�̓��͂�\������B<BR>
 * �����}�l�[�W���ɓn���p�����^�B<BR>
 * <BR>
 * xTrade��IfoChangeOrderSpec���g�������N���X�B
 * @@author  䈋�
 * @@version 1.0
 */
public class WEB3IfoOpenContractChangeSpec extends IfoChangeOpenContractOrderSpec 
{   
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoOpenContractChangeSpec.class);
            
    /**
     * �،���ЃR�[�h<BR>
     */
    private String institutionCode;
    
    /**
     * ������<BR>
     */
    private Date orderBizDate;
    
    /**
     * �������s����<BR>
     */
    private IfoOrderExecutionConditionType changeExecCondType;
    
    /**
     * ����������<BR>
     */
    private Date changeExpirationDate;
    
    /**
     * ��������<BR>
     * �@@0�FDEFAULT�i�����w��Ȃ��j�@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    private String orderCond;
    
    /**
     * �����������Z�q<BR>
     * �@@0�F Equal<BR>
     * �@@1�F ��l�ȏ�<BR>
     * �@@2�F ��l�ȉ�<BR>
     */
    private String orderCondOperator;
    
    /**
     * �t�w�l��l�^�C�v<BR>
     * �@@0�F DEFAULT�i�����Y�����j<BR>
     * �@@1�F �v���~�A��<BR>
     */
    private String stopOrderBasePriceType;
    
    /**
     * �t�w�l��l<BR>
     */
    private double stopOrderBasePrice;
    
    /**
     * �iW�w�l�j�����w�l<BR>
     */
    private double wLimitPriceChange;

    /**
     * �iW�w�l�j���s����<BR>
     */
    private IfoOrderExecutionConditionType wLimitExecCondType;
    
    /**
     * �iW�w�l�j�L����ԋ敪<BR> 
     * 0�F ���~�b�g�����L��<BR>  
     * 1�F �X�g�b�v�����L��<BR>  
     */
    private String wLimitEnableStatusDiv;
    
    /**
     * �����㒍�������敪<BR>
     * 1�F��������@@2�F�o����܂Œ���
     */
    private String expirationDateType;
    
    /**
     * �[��O�J�z�Ώۃt���O<BR>
     * false�F�[��O�J�z�Ȃ�<BR>
     * true�F�[��O�J�z����<BR>
     */
    private boolean eveningSessionCarryoverFlag;
 
    /**
     * �R���X�g���N�^�B<BR>
     * @@param l_lngOrderID - �����h�c<BR>
     * @@param l_lngOrderUnitID - �����P��ID<BR>
     * @@param l_dblQuantityAfterChange - �����㐔��<BR>
     * @@param l_dblLimitPriceAfterChange - ������w�l<BR>
     * @@return webbroker3.ifo.WEB3IfoOpenContractChangeSpec
     * @@roseuid 406A367E035C
     */
    public WEB3IfoOpenContractChangeSpec(
        long l_lngOrderID,
        long l_lngOrderUnitID,
        double l_dblQuantityAfterChange,
        double l_dblLimitPriceAfterChange) 
    {        
        super(l_lngOrderID, l_lngOrderUnitID, l_dblQuantityAfterChange, l_dblLimitPriceAfterChange);        
    }
    
    /**
     * (create�V�K���������e)<BR>
     * �istatic���\�b�h�j<BR>
     * �V�K���������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID<BR>
     * @@param l_dblQuantityAfterChange - (�����㐔��)<BR>
     * �����㐔��<BR>
     * @@param l_dblLimitPriceAfterChange - (������w�l)<BR>
     * ������w�l<BR>
     * @@param l_changeExecCondType - (�������s����)<BR>
     * �������s����<BR>
     * @@param l_datChangeExpirationDate - (����������)<BR>
     * ����������<BR>
     * @@param l_datOrderBizDate - (������)<BR>
     * ������<BR>
     * @@param l_strOrderCond - (��������)<BR>
     * ��������<BR>
     * @@param l_strOrderCondOperator - (�����������Z�q)<BR>
     * �����������Z�q<BR>
     * @@param l_strStopPriceType - (�t�w�l��l�^�C�v)<BR>
     * �t�w�l��l�^�C�v<BR>
     * @@param l_dblStopOrderPrice - (�t�w�l��l)<BR>
     * �t�w�l��l<BR>
     * @@param l_dblWLimitPrice - ((W�w�l)�����w�l)<BR>
     * (W�w�l)�����w�l<BR>
     * @@param l_wLimitExecCondType - ((W�w�l)���s����)<BR>
     * (W�w�l)���s����<BR>
     * @@param l_strWLimitEnableStatusDiv - ((W�w�l)�L����ԋ敪)<BR>
     * (W�w�l)�L����ԋ敪<BR>
     * @@param l_strExpirationDateType - (�����㒍�������敪)<BR>
     * �����㒍�������敪<BR>
     * @@param l_blnEveningSessionCarryoverFlag - (�[��O�J�z�Ώۃt���O)<BR>
     * �[��O�J�z�Ώۃt���O<BR>
     * @@return WEB3IfoOpenContractChangeSpec
     */
    public static WEB3IfoOpenContractChangeSpec createIfoOpenContractChangeSpec(
        long l_lngOrderId,
        long l_lngOrderUnitId,
        double l_dblQuantityAfterChange,
        double l_dblLimitPriceAfterChange,
        IfoOrderExecutionConditionType l_changeExecCondType,
        Date l_datChangeExpirationDate,
        Date l_datOrderBizDate,
        String l_strOrderCond,
        String l_strOrderCondOperator,
        String l_strStopPriceType,
        double l_dblStopOrderPrice,
        double l_dblWLimitPrice,
        IfoOrderExecutionConditionType l_wLimitExecCondType,
        String l_strWLimitEnableStatusDiv,
        String l_strExpirationDateType,
        boolean l_blnEveningSessionCarryoverFlag)
    {
        final String STR_METHOD_NAME =  "createIfoOpenContractChangeSpec()";      
        log.entering(STR_METHOD_NAME) ;

        //1.1.IfoChangeOpenContractOrderSpec(arg0 : long, arg1 : long, arg2 : double, arg3 : double)
        WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec = 
            new  WEB3IfoOpenContractChangeSpec(
                l_lngOrderId, 
                l_lngOrderUnitId, 
                l_dblQuantityAfterChange, 
                l_dblLimitPriceAfterChange);
            
        // 1.2.set�������s���� 
        //���N�G�X�g�f�[�^���s�������h�������h�̏ꍇ
        l_ifoOpenContractChangeSpec.setChangeExecCondType(l_changeExecCondType);
                
        //1.3.set����������(Date)
        l_ifoOpenContractChangeSpec.setChangeExpirationDate(l_datChangeExpirationDate);
                
        //1.4.set������(Date)
        l_ifoOpenContractChangeSpec.setOrderBizDate(l_datOrderBizDate);
                
        //1.5.set��������(String)
        l_ifoOpenContractChangeSpec.setOrderCond(l_strOrderCond);
                
        //1.6.set�����������Z�q(String)
        l_ifoOpenContractChangeSpec.setOrderCondOperator(l_strOrderCondOperator);

        //1.7.set�t�w�l��l�^�C�v
        l_ifoOpenContractChangeSpec.setStopOrderBasePriceType(l_strStopPriceType);
                
        //1.8.set�t�w�l��l(double)
        l_ifoOpenContractChangeSpec.setStopOrderBasePrice(l_dblStopOrderPrice);
              
        //1.9.set�iW�w�l�j�����w�l(double)
        l_ifoOpenContractChangeSpec.setWLimitPriceChange(l_dblWLimitPrice);
           
        //1.10.set�iW�w�l�j���s����(IfoOrderExecutionConditionType)
        l_ifoOpenContractChangeSpec.setWLimitExecCondType(l_wLimitExecCondType);
            
        //1.11.set�iW�w�l�j�L����ԋ敪(String)
        l_ifoOpenContractChangeSpec.setWLimitEnableStatusDiv(l_strWLimitEnableStatusDiv);
            
        //1.12.set�����㒍�������敪(String)
        l_ifoOpenContractChangeSpec.setExpirationDateType(l_strExpirationDateType);
        
        //�[��O�J�z�Ώۃt���O
        l_ifoOpenContractChangeSpec.setEveningSessionCarryoverFlag(l_blnEveningSessionCarryoverFlag);

        log.exiting(STR_METHOD_NAME);
        return l_ifoOpenContractChangeSpec;
    }

    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���擾����B<BR>
     * @@return String
     * @@roseuid 40678E930057
     */
    public String getInstitutionCode() 
    {
        return this.institutionCode;
    }
    
    /**
     * (set�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���Z�b�g����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@roseuid 40678E930058
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.institutionCode = l_strInstitutionCode;
    }
    
    /**
     * (get������)<BR>
     * ���������擾����B<BR>
     * @@return Date
     * @@roseuid 409F52DD0058
     */
    public Date getOrderBizDate() 
    {
        return this.orderBizDate;
    }
    
    /**
     * (set������)<BR>
     * ���������Z�b�g����B<BR>
     * @@param l_datOrderBizDate - ������<BR>
     * @@roseuid 409F52DD0059
     */
    public void setOrderBizDate(Date l_datOrderBizDate) 
    {
        this.orderBizDate = l_datOrderBizDate; 
    }
    
    /**
     * (get�������s����)<BR>
     * �������s�������擾����B<BR>
     * @@return IfoOrderExecutionConditionType
     * @@roseuid 409F52DD0068
     */
    public IfoOrderExecutionConditionType getChangeExecCondType() 
    {
        return this.changeExecCondType; 
    }
    
    /**
     * (set�������s����)<BR>
     * �������s�������Z�b�g����B<BR>
     * @@param l_executionConditionTypeChange - �������s����<BR>
     * @@roseuid 409F52DD0069
     */
    public void setChangeExecCondType(IfoOrderExecutionConditionType l_executionConditionTypeChange) 
    {
        this.changeExecCondType = l_executionConditionTypeChange;
    }
    
    /**
     * (get����������)<BR>
     * �������������擾����B<BR>
     * @@return Date
     * @@roseuid 409F52DD0087
     */
    public Date getChangeExpirationDate() 
    {
        return this.changeExpirationDate; 
    }
    
    /**
     * (set����������)<BR>
     * �������������Z�b�g����B<BR>
     * @@param l_datExpirationDateChange - ����������<BR>
     * @@roseuid 409F52DD0088
     */
    public void setChangeExpirationDate(Date l_datExpirationDateChange) 
    {
        this.changeExpirationDate = l_datExpirationDateChange;     
    }
    
    /**
     * (get��������)<BR>
     * �����������擾����B<BR>
     * @@return String
     * @@roseuid 409F52DD0097
     */
    public String getOrderCond() 
    {
        return this.orderCond;
    }
    
    /**
     * (set��������)<BR>
     * �����������Z�b�g����B<BR>
     * @@param l_strOrderCond - ��������<BR>
     * @@roseuid 409F52DD0098
     */
    public void setOrderCond(String l_strOrderCond) 
    {
        this.orderCond = l_strOrderCond;     
    }
    
    /**
     * (get�����������Z�q)<BR>
     * �����������Z�q���擾����B<BR>
     * @@return String
     * @@roseuid 409F52DD009A
     */
    public String getOrderCondOperator() 
    {
        return this.orderCondOperator;
    }
    
    /**
     * (set�����������Z�q)<BR>
     * �����������Z�q���Z�b�g����B<BR>
     * @@param l_strOrderCondOperator - �����������Z�q<BR>
     * @@roseuid 409F52DD009B
     */
    public void setOrderCondOperator(String l_strOrderCondOperator) 
    {
        this.orderCondOperator = l_strOrderCondOperator;   
    }
    
    /**
     * (get�t�w�l��l�^�C�v)<BR>
     * �t�w�l��l�^�C�v���擾����B<BR>
     * @@return String
     * @@roseuid 409F52DD009D
     */
    public String getStopOrderBasePriceType() 
    {
        return this.stopOrderBasePriceType;
    }
    
    /**
     * (set�t�w�l��l�^�C�v)<BR>
     * �t�w�l��l�^�C�v���Z�b�g����B<BR>
     * @@param l_strStopOrderBasePriceType - �t�w�l��l�^�C�v<BR>
     * @@roseuid 409F52DD00A6
     */
    public void setStopOrderBasePriceType(String l_strStopOrderBasePriceType) 
    {
        this.stopOrderBasePriceType = l_strStopOrderBasePriceType;    
    }
    
    /**
     * (get�t�w�l��l)<BR>
     * �t�w�l��l���擾����B<BR>
     * @@return double
     * @@roseuid 409F52DD00A8
     */
    public double getStopOrderBasePrice() 
    {
        return this.stopOrderBasePrice;
    }
    
    /**
     * (set�t�w�l��l)<BR>
     * �t�w�l��l���Z�b�g����B<BR>
     * @@param l_dblStopOrderBasePrice - �t�w�l��l<BR>
     * @@roseuid 409F52DD00A9
     */
    public void setStopOrderBasePrice(double l_dblStopOrderBasePrice) 
    {
        this.stopOrderBasePrice = l_dblStopOrderBasePrice;   
    }
    
    /**
     * (set�iW�w�l�j�����w�l)<BR>
     * (W�w�l)�����w�l���Z�b�g����B<BR>
     * @@param l_dblWLimitPriceChange - (W�w�l)�����w�l<BR>
     * @@roseuid 409F52DD00AB
     */
    public void setWLimitPriceChange(double l_dblWLimitPriceChange) 
    {
        this.wLimitPriceChange = l_dblWLimitPriceChange;     
    }
    
    /**
     * (get�iW�w�l�j�����w�l)<BR>
     * (W�w�l)�����w�l���擾����B<BR>
     * @@return double
     * @@roseuid 409F52DD00AD
     */
    public double getWLimitPriceChange() 
    {
     return this.wLimitPriceChange;
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
     * (get�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪���擾����B<BR>
     * @@return String
     */
    public String getWLimitEnableStatusDiv()
    {
        return this.wLimitEnableStatusDiv;
    }
    
    /**
     * (set�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪���Z�b�g����B<BR>
     * @@param l_strWLimitEnableStatusDiv - (�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪
     */
    public void setWLimitEnableStatusDiv(String l_strWLimitEnableStatusDiv)
    {
        this.wLimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
    }
    
    /**
     * (set�����㒍�������敪)<BR>
     * �����㒍�������敪���Z�b�g����B<BR>
     * @@param l_strExpirationDateType - �����㒍�������敪<BR>
     */
    public void setExpirationDateType(String l_strExpirationDateType) 
    {
        this.expirationDateType = l_strExpirationDateType;     
    }
    /**
     * (get�����㒍�������敪)<BR>
     * �����㒍�������敪���擾����B<BR>
     * @@return String
     */
    public String getExpirationDateType() 
    {
        return this.expirationDateType;
    }
    
    /**
     * (is�X�g�b�v�����L��)<BR>
     * this.�iW�w�l�j�L����ԋ敪 == "�X�g�b�v�����L��"�̏ꍇ�Atrue�A <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isStopOrderOpen()
    {
        final String STR_METHOD_NAME = "isStopOrderOpen()";      
        log.entering(STR_METHOD_NAME) ;

        if ( WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE.equals(this.wLimitEnableStatusDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
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
     * �[��O�J�z�Ώۃt���O��ݒ肷��B<BR>
     * @@param l_blnEveningSessionCarryoverFlag - (�[��O�J�z�Ώۃt���O)<BR>
     * �[��O�J�z�Ώۃt���O
     */
    public void setEveningSessionCarryoverFlag(boolean l_blnEveningSessionCarryoverFlag)
    {
        this.eveningSessionCarryoverFlag = l_blnEveningSessionCarryoverFlag;
    }
}
@
