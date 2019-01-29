head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoChangeSettleContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ԍϒ������e(WEB3IfoChangeSettleContractOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/10 Ḗ@@�� (���u) �V�K�쐬
                 : 2006/7/13 ���G�� (���u) �d�l�ύX�@@���f��476
Revesion History : 2007/06/08   ���^�] (���u) �d�l�ύX���f��No.660
*/
package webbroker3.ifo;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeSettleContractOrderSpec;

import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ԍϒ������e)<BR>
 * �ԍϒ������e�N���X<BR>
 * �ԍϒ������e�̓��͂�\������B<BR>
 * �����}�l�[�W���ɓn���p�����^�B<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3IfoChangeSettleContractOrderSpec extends IfoChangeSettleContractOrderSpec
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance( WEB3IfoChangeSettleContractOrderSpec.class);
            
    /**
     * ������
     */
    private Date orderBizDate;

    /**
     * �،���ЃR�[�h
     */
    private String institutionCode;

    /**
     * �������s����
     */
    private IfoOrderExecutionConditionType changeExecCondType;

    /**
     * ����������
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
     * �t�w�l��l
     */
    private double stopOrderBasePrice;    
    
    /**
     * �iW�w�l�j�����w�l
     */
    private double wLimitPriceChange;
    
    /**
     * �iW�w�l�j���s����<BR>
     */
    private IfoOrderExecutionConditionType wLimitExecCondType;
    
    /**
     * �iW�w�l�j�L����ԋ敪<BR>
     */
    private String wLimitEnableStatusDiv;

    /**
     * �����㒍�������敪<BR>
     * 1�F��������@@2�F�o����܂Œ���
     */
    private String expirationDateType;
    
    /**
     * (�[��O�J�z�Ώۃt���O)<BR>
     * false�F�[��O�J�z�Ȃ�<BR>
     * true�F�[��O�J�z����<BR>
     */
    private boolean eveningSessionCarryoverFlag;
    
    /**
     * �R���X�g���N�^�B
     * @@param l_lngOrderId
     *  - �����h�c
     * @@param l_lngOrderUnitId
     *  - �����P��ID
     * @@param l_dblChangedLimitPrice
     *  - ������w�l
     * @@param l_SettleContractEntry - �ԍό��ʃG���g��<BR>
     * �ԍό��ʃG���g���I�u�W�F�N�g�̔z��<BR>
     * @@return webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec
     * @@roseuid 406A36FB0290
     */
    public WEB3IfoChangeSettleContractOrderSpec(long l_lngOrderId, long l_lngOrderUnitId, double l_dblChangedLimitPrice, SettleContractEntry[] l_SettleContractEntry)
    {

        super(l_lngOrderId, l_lngOrderUnitId, l_dblChangedLimitPrice, l_SettleContractEntry);
        log.debug("Enter the path that test the constructor.");
        log.debug("Succeeding in executing the constructor method.");

    }
    
    /**
     * (create�ԍϒ������e)<BR>
	 * �istatic���\�b�h�j<BR>
	 * �ԍϒ������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID<BR>
     * @@param l_dblChangeLimitPrice - (�����w�l)<BR>
     * �����w�l<BR>
     * @@param l_settleContractEntries - (�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g��<BR>
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
     * @@return WEB3IfoChangeSettleContractOrderSpec
     */
    
    public static WEB3IfoChangeSettleContractOrderSpec createIfoChangeSettleContractOrderSpec(
        long l_lngOrderId,
        long l_lngOrderUnitId,
        double l_dblChangeLimitPrice,
        SettleContractEntry[]  l_settleContractEntries,
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
            final String STR_METHOD_NAME =  "createIfoChangeSettleContractOrderSpec()";      
            log.entering(STR_METHOD_NAME) ;
            
            //1.1.IfoChangeOpenContractOrderSpec(arg0 : long, arg1 : long, arg2 : double, arg3 : double)
            WEB3IfoChangeSettleContractOrderSpec l_ifoChangeSettleContractOrderSpec = 
                new  WEB3IfoChangeSettleContractOrderSpec(
                    l_lngOrderId, 
                    l_lngOrderUnitId, 
                    l_dblChangeLimitPrice, 
                    l_settleContractEntries);
            
            // 1.2.set�������s���� 
            //���N�G�X�g�f�[�^���s�������h�������h�̏ꍇ
            l_ifoChangeSettleContractOrderSpec.setChangeExecCondType(l_changeExecCondType);
                
            //1.3.set����������(Date)
            l_ifoChangeSettleContractOrderSpec.setChangeExpirationDate(l_datChangeExpirationDate);
                
            //1.4.set������(Date)
            l_ifoChangeSettleContractOrderSpec.setOrderBizDate(l_datOrderBizDate);
                
            //1.5.set��������(String)
            l_ifoChangeSettleContractOrderSpec.setOrderCond(l_strOrderCond);
                
            //1.6.set�����������Z�q(String)
            l_ifoChangeSettleContractOrderSpec.setOrderCondOperator(l_strOrderCondOperator);

            //1.7.set�t�w�l��l�^�C�v
            l_ifoChangeSettleContractOrderSpec.setStopOrderBasePriceType(l_strStopPriceType);
                
            //1.8.set�t�w�l��l(double)
            l_ifoChangeSettleContractOrderSpec.setStopOrderBasePrice(l_dblStopOrderPrice);
              
            //1.9.set�iW�w�l�j�����w�l(double)
            l_ifoChangeSettleContractOrderSpec.setWLimitPriceChange(l_dblWLimitPrice);
           
            //1.10.set�iW�w�l�j���s����(IfoOrderExecutionConditionType)
            l_ifoChangeSettleContractOrderSpec.setWLimitExecCondType(l_wLimitExecCondType);
            
            //1.11.set�iW�w�l�j�L����ԋ敪(String)
            l_ifoChangeSettleContractOrderSpec.setWLimitEnableStatusDiv(l_strWLimitEnableStatusDiv);
            
            //1.12.set�����㒍�������敪(String)
            l_ifoChangeSettleContractOrderSpec.setExpirationDateType(l_strExpirationDateType);

            //set�[��O�J�z�Ώۃt���O(boolean)
            l_ifoChangeSettleContractOrderSpec.setEveningSessionCarryoverFlag(l_blnEveningSessionCarryoverFlag);

            log.exiting(STR_METHOD_NAME);
            return l_ifoChangeSettleContractOrderSpec;
        }

    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���擾����B<BR>
     * @@return String
     * @@roseuid 406788290078
     */
    public String getInstitutionCode()
    {
        return this.institutionCode;
    }

    /**
     * (set�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���Z�b�g����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@roseuid 406788290086
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        this.institutionCode = l_strInstitutionCode;
    }

    /**
     * (get������)<BR>
     * ���������擾����B<BR>
     * @@return Date
     * @@roseuid 409F53070317
     */
    public Date getOrderBizDate()
    {
        return this.orderBizDate;
    }

    /**
     * (set������)<BR>
     * ���������Z�b�g����B<BR>
     * @@param l_datOrderBizDate - ������
     * @@roseuid 409F53070327
     */
    public void setOrderBizDate(Date l_datOrderBizDate)
    {
        this.orderBizDate = l_datOrderBizDate;
    }

    /**
     * (get�������s����)<BR>
     * �������s�������擾����B<BR>
     * @@return IfoOrderExecutionConditionType
     * @@roseuid 409F53070329
     */
    public IfoOrderExecutionConditionType getChangeExecCondType()
    {
        return this.changeExecCondType;
    }

    /**
     * (set�������s����)<BR>
     * �������s�������Z�b�g����B<BR>
     * @@param l_executionConditionTypeChange - �������s����
     * @@roseuid 409F5307032A
     */
    public void setChangeExecCondType(IfoOrderExecutionConditionType l_executionConditionTypeChange)
    {
        this.changeExecCondType = l_executionConditionTypeChange;

    }

    /**
     * (get����������)<BR>
     * �������������擾����B<BR>
     * @@return Date
     * @@roseuid 409F53070356
     */
    public Date getChangeExpirationDate()
    {
        return this.changeExpirationDate;
    }

    /**
     * (set����������)<BR>
     * �������������Z�b�g����B<BR>
     * @@param l_datExpirationDateChange - ����������
     * @@roseuid 409F53070357
     */
    public void setChangeExpirationDate(Date l_datExpirationDateChange)
    {
        this.changeExpirationDate = l_datExpirationDateChange;

    }

    /**
     * (set��������)<BR>
     * �����������Z�b�g����B<BR>
     * @@param l_strOrderCond - ��������
     * @@roseuid 409F53070359
     */
    public void setOrderCond(String l_strOrderCond)
    {
        this.orderCond = l_strOrderCond;

    }

    /**
     * (get��������)<BR>
     * �����������擾����B<BR>
     * @@return String
     * @@roseuid 409F5307035B
     */
    public String getOrderCond()
    {
        return this.orderCond;
    }

    /**
     * (get�����������Z�q)<BR>
     * �����������Z�q���擾����B<BR>
     * @@return String
     * @@roseuid 409F5307035C
     */
    public String getOrderCondOperator()
    {
        return this.orderCondOperator;
    }

    /**
     * (set�����������Z�q)<BR>
     * �����������Z�q���Z�b�g����B<BR>
     * @@param l_strOrderCondOperator - �����������Z�q
     * @@roseuid 409F53070366
     */
    public void setOrderCondOperator(String l_strOrderCondOperator)
    {
        this.orderCondOperator = l_strOrderCondOperator;

    }

    /**
     * (get�t�w�l��l�^�C�v)<BR>
     * �t�w�l��l�^�C�v���擾����B<BR>
     * @@return String
     * @@roseuid 409F53070368
     */
    public String getStopOrderBasePriceType()
    {
        return this.stopOrderBasePriceType;
    }

    /**
     * (set�t�w�l��l�^�C�v)<BR>
     * �t�w�l��l�^�C�v���Z�b�g����B<BR>
     * @@param l_strStopOrderBasePriceType - �t�w�l��l�^�C�v
     * @@roseuid 409F53070369
     */
    public void setStopOrderBasePriceType(String l_strStopOrderBasePriceType)
    {
        this.stopOrderBasePriceType = l_strStopOrderBasePriceType;
    }

    /**
     * (get�t�w�l��l)<BR>
     * �t�w�l��l���擾����B<BR>
     * @@return double
     * @@roseuid 409F5307036B
     */
    public double getStopOrderBasePrice()
    {
        return this.stopOrderBasePrice;
    }

    /**
     * (set�t�w�l��l)<BR>
     * �t�w�l��l���Z�b�g����B<BR>
     * @@param l_dblStopOrderBasePrice - �t�w�l��l
     * @@roseuid 409F5307036C
     */
    public void setStopOrderBasePrice(double l_dblStopOrderBasePrice)
    {
        this.stopOrderBasePrice = l_dblStopOrderBasePrice;

    }

    /**
     * (set�iW�w�l�j�����w�l)<BR>
     * (W�w�l)�����w�l���Z�b�g����B<BR>
     * @@param l_strWLimitPriceChange - (W�w�l)�����w�l
     * @@roseuid 409F53070375
     */
    public void setWLimitPriceChange(double l_dblWLimitPriceChange)
    {
        this.wLimitPriceChange = l_dblWLimitPriceChange;
    }

    /**
     * (get�iW�w�l�j�����w�l)<BR>
     * (W�w�l)�����w�l���擾����B<BR>
     * @@return String
     * @@roseuid 409F53070377
     */
    public double getWLimitPriceChange()
    {
        return this.wLimitPriceChange;
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
    * @@param l_ifoOrderExecutionConditionType - �iW�w�l�j���s���� <BR>
    */
   public void setWLimitExecCondType(IfoOrderExecutionConditionType 
		l_ifoOrderExecutionConditionType)
   {
       this.wLimitExecCondType = l_ifoOrderExecutionConditionType;
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
    * @@param l_strWLimitEnableStatusDiv - �iW�w�l�j�L����ԋ敪<BR>
    */
   public void setWLimitEnableStatusDiv(String l_strWLimitEnableStatusDiv)
   {
       this.wLimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
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

   /**
    * (is�X�g�b�v�����L��)<BR>
    * this.�iW�w�l�j�L����ԋ敪 == "�X�g�b�v�����L��"�̏ꍇ�Atrue�A <BR>
    * �ȊO�Afalse��ԋp����B<BR>
    * @@return boolean
    */
   public boolean isStopOrderOpen()
   {
       final String STR_METHOD_NAME =  "isStopOrderOpen()";      
       log.entering(STR_METHOD_NAME) ;
       if ( WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE.equals(this.wLimitEnableStatusDiv))
       {
           log.exiting(STR_METHOD_NAME);
           return true;
       }
       log.exiting(STR_METHOD_NAME);
       return false;
   }
}
@
