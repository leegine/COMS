head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�V�K���������e(WEB3MarginOpenMarginOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 ������ (���u) �V�K�쐬
                   2004/12/09 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2004/12/29 �����a��(SRA) �p�����[�^�̕ϐ������C��
                   2005/01/06 �����a�� (SRA) JavaDoc�C��
                   2006/11/02 ��іQ (���u) ���f�� No.1001
*/

package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�V�K���������e�j�B<BR>
 * <BR>
 * �M�p����E�V�K���������e�̓��͂�\������B<BR>
 * �����}�l�[�W���ɓn���p�����^�ɂȂ�B<BR>
 * <BR>
 * xTrade��EqTypeOpenContractOrderSpec���g�������N���X�B
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginOpenContractOrderSpec extends EqTypeOpenContractOrderSpec 
{
    /**
     * �i���O���[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityNewCashBasedOrderSpec.class);

    /**
     * �i�M�p�V�K���������e�j�B<BR>
     * �R���X�g���N�^�B
     * @@param l_trader (����)
     * @@param l_blnIsLong (is����)
     * @@param l_strProductCpde �����R�[�h�B
     * @@param l_strMarketCode �s��R�[�h
     * @@param l_dblQuantity ���ʁi�����j
     * @@param l_dblLimitPrice �w�l
     * @@param l_executionType ���s����
     * @@param l_datExpirationDate ����������
     * @@param l_taxType (�ŋ敪)
     */
    protected WEB3MarginOpenContractOrderSpec(
        Trader l_trader, 
        boolean l_blnIsLong, 
        String l_strProductCpde, 
        String l_strMarketCode, 
        double l_dblQuantity, 
        double l_dblLimitPrice, 
        EqTypeExecutionConditionType l_executionType, 
        Date l_datExpirationDate, 
        TaxTypeEnum l_taxType)
    {
        super(l_trader,
            l_blnIsLong,
            l_strProductCpde,
            l_strMarketCode,
            l_dblQuantity,
            l_dblLimitPrice,
            l_executionType,
            l_datExpirationDate,
            l_taxType);
    }

    /**
     * �i�������j�B<BR>
     * <BR>
     * �s��ɔ������鏈�����t�B<BR>
     * �i����J�����_���擾����j<BR>
     */
    private Date bizDate;
    
    /**
     * �i�v�Z�P���j�B<BR>
     * <BR>
     * �i�����P��.�����P���ɃZ�b�g�j
     */
    private double calcUnitPrice;
    
    /**
     * �i�l�i����)�B<BR>
     * <BR>
     * �i0�F�@@DEFAULT(�����w��Ȃ�)<BR>
     * �@@1�F�@@���ݒl�w�l����<BR>
     * �@@3�F�@@�D��w�l����<BR>
     * �@@5�F�@@���s�c���w�l����<BR>
     * �@@7�F�@@���s�c����������j<BR>
     */
    private String priceConditionType;
    
    /**
     * �i���������j�B<BR>
     * <BR>
     * �i0�FDEFAULT�i�����w��Ȃ��j�@@1�F�t�w�l�@@2�FW�w�l�j
     */
    private String orderConditionType;
    
    /**
     * �i�����������Z�q�j�B<BR>
     * <BR>
     * �i0�FEqual�i��l�Ɠ����l�ɂȂ�����j<BR>
     * 1�F��l�ȏ�@@<BR>
     * 2�F��l�ȉ��j<BR>
     */
    private String orderCondOperator;
    
    /**
     * �i�t�w�l��l�j�B<BR>
     * <BR>
     * �i�t�w�l�AW�w�l�̏ꍇ�̂݃Z�b�g�j
     */
    private double stopOrderPrice;
    
    /**
     * �i�iW�w�l�j�����w�l�B�j�B<BR>
     * <BR>
     * �iW�w�l�̏ꍇ�̂݃Z�b�g�j
     */
    private double wLimitPrice;
    
    /**
     * �i�ٍϋ敪�j�B<BR>
     * <BR>
     * �i0�FDEFAULT�@@1�F���x�M�p�@@2�F��ʐM�p�j
     */
    private String repaymentType;
    
    /**
     * �i�ٍϊ����l�j�B<BR>
     * <BR>
     * �i���P�ʂŐݒ�B�������̏ꍇ�́AALL9��ݒ�j
     */
    private double repaymentNum;
    
    /**
     * �i���񒍕��̒����P��ID�j�B
     */
    private Long firstOrderUnitId;
    
    /**
     * �i������j�B
     */
    private double contractAmount;

    /**
     * (�i�v�w�l�j���s����)<BR>
     * �i�v�w�l�j���s����<BR>
     */
    private EqTypeExecutionConditionType wlimitExecCondType;

    /**
     * �icreate�V�K���������e�j�B<BR>
     * <BR>
     * �istatic���\�b�h�j<BR>
     * �M�p�V�K���������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �葱���̓V�[�P���X�}�u�i�M�p�����jcreate�V�K���������e�v���Q�ƁB<BR>
     * @@param l_trader - (����)<BR>
     * ���҃I�u�W�F�N�g<BR>
     * @@param l_blnIsLong - (is����)<BR>
     * �iisBuyToOpenOrder�j<BR>
     * �������ǂ����̔���B<BR>
     * �����̏ꍇ��true�A�����̏ꍇ��false�B
     * @@param l_strProductCpde �����R�[�h�B
     * @@param l_strMarketCode �s��R�[�h
     * @@param l_dblQuantity ���ʁi�����j
     * @@param l_dblLimitPrice �w�l
     * @@param l_executionType ���s����
     * @@param l_datExpirationDate ����������
     * @@param l_taxType (�ŋ敪)<BR>
     * �@@�@@�@@0:���̑�,�@@1:���,�@@2:����,�@@3:������������򒥎�<BR>
     * �@@�@@�@@�iTaxTypeEnum�ɂĒ�`�j
     * @@param l_strPriceConditionType (�l�i����)
     * @@param l_strOrderConditionType ��������
     * @@param l_strOrderCondOperator �����������Z�q
     * @@param l_dblStopOrderPrice �t�w�l��l
     * @@param l_dblWLimitPrice (W�w�l)�����w�l
     * @@param l_strRepaymentType (�ٍϋ敪)<BR>
     * �@@�@@�@@0�FDEFAULT<BR>
     * �@@�@@�@@1�F���x�M�p<BR>
     * �@@�@@�@@2�F��ʐM�p
     * @@param l_dblRepaymentNum �ٍϊ����l
     * @@param l_lngFirstOrderUnitId (���񒍕��̒����P��ID)<BR>
     * �@@�@@�@@�����J�z�ł̒����쐬���ɂ́A�J�z�Ώۂ̒����P��.���񒍕���<BR>
     * �@@�@@�@@�����P��ID ���Z�b�g�B<BR>
     * �@@�@@�@@��L�ȊO�̐V�K�����o�^���ɂ́Anull���Z�b�g�B
     * @@return WEB3MarginOpenMarginOrderSpec
     * @@throws WEB3SystemLayerException
     * @@roseuid 40A8B9580038
     */
    public static WEB3MarginOpenContractOrderSpec createOpenMarginOrderSpec(
        Trader l_trader,
        boolean l_blnIsLong, 
        String l_strProductCpde, 
        String l_strMarketCode, 
        double l_dblQuantity, 
        double l_dblLimitPrice, 
        EqTypeExecutionConditionType l_executionType,
        Date l_datExpirationDate,
        TaxTypeEnum l_taxType,
        String l_strPriceConditionType,
        String l_strOrderConditionType, 
        String l_strOrderCondOperator, 
        double l_dblStopOrderPrice, 
        double l_dblWLimitPrice, 
        String l_strRepaymentType, 
        double l_dblRepaymentNum, 
        Long l_lngFirstOrderUnitId) 
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME="createOpenMarginOrderSpec()";
        log.entering(STR_METHOD_NAME);
        
        //1  ������������null�̏ꍇ�A���������w�肷��
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        if(l_datExpirationDate == null)
        {
            l_datExpirationDate = l_datOrderBizDate;
        }
        
        //2 �V�K���������e�C���X�^���X�𐶐�����B
        WEB3MarginOpenContractOrderSpec l_orderSpec = 
            new WEB3MarginOpenContractOrderSpec(
                l_trader,
                l_blnIsLong,
                l_strProductCpde,
                l_strMarketCode,
                l_dblQuantity,
                l_dblLimitPrice,
                l_executionType,
                l_datExpirationDate,
                l_taxType);
       
        //3 ���������Z�b�g
        l_orderSpec.setBizDate(l_datExpirationDate);
        
        //4 �l�i�������Z�b�g
        l_orderSpec.setPriceConditionType(l_strPriceConditionType);
        
        //5 �����������Z�b�g
        l_orderSpec.setOrderConditionType(l_strOrderConditionType);
        
        //6 �����������Z�q���Z�b�g
        l_orderSpec.setOrderCondOperator(l_strOrderCondOperator);
        
        //7 �t�w�l��l���Z�b�g
        l_orderSpec.setStopOrderPrice(l_dblStopOrderPrice);
        
        //8�iW�w�l�j�����w�l���Z�b�g
        l_orderSpec.setWLimitPrice(l_dblWLimitPrice);
        
        //9 �ٍϋ敪���Z�b�g
        l_orderSpec.setRepaymentType(l_strRepaymentType);
        
        //10 �ٍϊ����l���Z�b�g
        l_orderSpec.setRepaymentNum(l_dblRepaymentNum);
        
        //11 ���񒍕��̒����P��ID���Z�b�g
        l_orderSpec.setFirstOrderUnitId(l_lngFirstOrderUnitId);
        
        log.exiting(STR_METHOD_NAME);
        
        return  l_orderSpec;        
    }

    /**
     * �icreate�V�K���������e�j�B<BR>
     * <BR>
     * �istatic���\�b�h�j<BR>
     * �M�p�V�K���������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �葱���̓V�[�P���X�}�u�i�M�p�����jcreate�V�K���������e�v���Q�ƁB<BR>
     * <BR>
     * �P�j�M�p�V�K���������e.create�V�K���������e( ) ��delegate����B<BR>
     * <BR>
     * �@@�@@�����ݒ�͈ȉ��̂悤�ɍs���B<BR>
     * <BR>
     * ���ҁF�@@�p�����[�^.������ <BR>
     * is�����F�@@�p�����[�^.������ <BR>
     * �����R�[�h�F�@@�p�����[�^.������ <BR>
     * �s��R�[�h�F�@@�p�����[�^.������ <BR>
     * ���ʁF�@@�p�����[�^.������ <BR>
     * �w�l�F�@@�p�����[�^.������ <BR>
     * ���s�����F�@@�p�����[�^.������ <BR>
     * �����������F�@@�p�����[�^.������ <BR>
     * �ŋ敪�F�@@�p�����[�^.������ <BR>
     * �l�i�����F�@@�p�����[�^.������ <BR>
     * ���������F�@@�p�����[�^.������ <BR>
     * �����������Z�q�F�@@�p�����[�^.������ <BR>
     * �t�w�l��l�F�@@�p�����[�^.������ <BR>
     * �iW�w�l�j�����w�l�F�@@�p�����[�^.������ <BR>
     * �ٍϋ敪�F�@@�p�����[�^.������ <BR>
     * �ٍϊ����l�F�@@�p�����[�^.������ <BR>
     * ���񒍕��̒����P��ID�F�@@�p�����[�^.������ <BR>
     * <BR>
     * <BR>
     * �Q�jset�i�v�w�l�j���s����( )��call����B<BR>
     * <BR>
     * �@@�@@�����ݒ�͈ȉ��̂悤�ɍs���B<BR>
     * <BR>
     * �@@�@@�@@�i�v�w�l�j���s�����F�@@������<BR>
     * @@param l_trader - (����)<BR>
     * ���҃I�u�W�F�N�g<BR>
     * @@param l_blnIsLong - (is����)<BR>
     * �iisBuyToOpenOrder�j<BR>
     * �������ǂ����̔���B<BR>
     * �����̏ꍇ��true�A�����̏ꍇ��false�B<BR>
     * @@param l_strProductCpde - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@param l_dblQuantity - ����<BR>
     * ���ʁi�����j<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �w�l<BR>
     * @@param l_execCondType - (���s����)<BR>
     * ���s����<BR>
     * @@param l_datExpirationDate - (����������)<BR>
     * ����������<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * 0:���̑�,�@@1:���,�@@2:����,�@@3:������������򒥎�<BR>
     * �iTaxTypeEnum�ɂĒ�`�j<BR>
     * @@param l_strPriceConditionType - (�l�i����)<BR>
     * �l�i����<BR>
     * @@param l_strOrderConditionType - (��������)<BR>
     * ��������<BR>
     * @@param l_strOrderCondOperator - (�����������Z�q)<BR>
     * �����������Z�q<BR>
     * @@param l_dblStopOrderPrice - (�t�w�l��l)<BR>
     * �t�w�l��l<BR>
     * @@param l_dblWLimitPrice - ((W�w�l)�����w�l)<BR>
     * (W�w�l)�����w�l<BR>
     * @@param l_strRepaymentType - (�ٍϋ敪)<BR>
     * 0�FDEFAULT<BR>
     * 1�F���x�M�p<BR>
     * 2�F��ʐM�p<BR>
     * @@param l_dblRepaymentNum - (�ٍϊ����l)<BR>
     * �ٍϊ����l<BR>
     * @@param l_lngFirstOrderUnitId - (���񒍕��̒����P��ID)<BR>
     * �����J�z�ł̒����쐬���ɂ́A�J�z�Ώۂ̒����P��.���񒍕���<BR>
     * �����P��ID ���Z�b�g�B<BR>
     * ��L�ȊO�̐V�K�����o�^���ɂ́Anull���Z�b�g�B<BR>
     * @@param l_wlimitExecCondType - (�i�v�w�l�j���s����)<BR>
     * �i�v�w�l�j���s����<BR>
     * @@return WEB3MarginOpenMarginOrderSpec
     * @@throws WEB3SystemLayerException
     * @@roseuid 40A8B9580038
     */
    public static WEB3MarginOpenContractOrderSpec createOpenMarginOrderSpec(
        Trader l_trader,
        boolean l_blnIsLong,
        String l_strProductCpde,
        String l_strMarketCode,
        double l_dblQuantity,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_execCondType,
        Date l_datExpirationDate,
        TaxTypeEnum l_taxType,
        String l_strPriceConditionType,
        String l_strOrderConditionType,
        String l_strOrderCondOperator,
        double l_dblStopOrderPrice,
        double l_dblWLimitPrice,
        String l_strRepaymentType,
        double l_dblRepaymentNum,
        Long l_lngFirstOrderUnitId,
        EqTypeExecutionConditionType l_wlimitExecCondType)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "createOpenMarginOrderSpec(Trader, boolean, "
            + "String, String, double, double, EqTypeExecutionConditionType, Date, "
            + "TaxTypeEnum, String, String, String, double, double, String, double, Long, "
            + "EqTypeExecutionConditionType";
        log.entering(STR_METHOD_NAME);

        //�M�p�V�K���������e�I�u�W�F�N�g�𐶐�
        //�P�j�M�p�V�K���������e.create�V�K���������e( ) ��delegate����B
        WEB3MarginOpenContractOrderSpec l_orderSpec =
            WEB3MarginOpenContractOrderSpec.createOpenMarginOrderSpec(
                l_trader,
                l_blnIsLong,
                l_strProductCpde,
                l_strMarketCode,
                l_dblQuantity,
                l_dblLimitPrice,
                l_execCondType,
                l_datExpirationDate,
                l_taxType,
                l_strPriceConditionType,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_dblStopOrderPrice,
                l_dblWLimitPrice,
                l_strRepaymentType,
                l_dblRepaymentNum,
                l_lngFirstOrderUnitId);

        //�Q�jset�i�v�w�l�j���s����( )��call����B
        //�����ݒ�͈ȉ��̂悤�ɍs���B
        //�i�v�w�l�j���s�����F�@@������
        l_orderSpec.setWlimitExecCondType(l_wlimitExecCondType);

        log.exiting(STR_METHOD_NAME);

        return l_orderSpec;
    }

    /**
     * �iset�������j�B<BR>
     * <BR>
     * ���������Z�b�g����B
     * @@param l_datBizDate ������
     * @@roseuid 40A8B9580058
     */
    public void setBizDate(Date l_datBizDate) 
    {
        this.bizDate = l_datBizDate;
    }
    
    /**
     * (get�������j�B<BR>
     * <BR>
     * ���������擾����B
     * @@return Date
     * @@roseuid 40A8B958004A
     */
    public Date getBizDate() 
    {
        return this.bizDate;
    }
    
    /**
     * �iset�v�Z�P���j�B<BR>
     * <BR>
     * �v�Z�P�����Z�b�g����B
     * @@param l_dblCalcUnitPrice �v�Z�P���B
     * @@roseuid 40AC517502DA
     */
    public void setCalcUnitPrice(double l_dblCalcUnitPrice) 
    {
        this.calcUnitPrice = l_dblCalcUnitPrice;
    }
    
    /**
     * �iget�v�Z�P���j�B<BR>
     * <BR>
     * �v�Z�P�����擾����B
     * @@return double
     * @@roseuid 40AC517502E9
     */
    public double getCalcUnitPrice() 
    {
        return this.calcUnitPrice;
    }
    
    /**
     * �iset�l�i�����j�B<BR>
     * <BR>
     * �l�i�������Z�b�g����B
     * @@param l_strPriceConditionType �l�i����
     */
    public void setPriceConditionType(String l_strPriceConditionType) 
    {
        this.priceConditionType = l_strPriceConditionType;
    }
    
    /**
     * �iget�l�i�����j�B<BR>
     * <BR>
     * �l�i�������擾����B
     * @@return String
     */
    public String getPriceConditionType() 
    {
        return this.priceConditionType;
    }
    
    /**
     * �iget���������j�B<BR>
     * <BR>
     * �����������擾����B
     * @@return String
     * @@roseuid 40A8B958005A
     */
    public String getOrderConditionType() 
    {
        return this.orderConditionType;
    }
    
    /**
     * �iset���������j�B<BR>
     * <BR>
     * �����������Z�b�g����B
     * @@param l_strOrderConditionType ��������
     * @@roseuid 40A8B9580067
     */
    public void setOrderConditionType(String l_strOrderConditionType) 
    {
        this.orderConditionType = l_strOrderConditionType;
    }
    
    /**
     * �iset�����������Z�q�j�B<BR>
     * <BR>
     * �����������Z�q���Z�b�g����B
     * @@param l_strOrderCondOperator �����������Z�q
     * @@roseuid 40AC522E021E
     */
    public void setOrderCondOperator(String l_strOrderCondOperator) 
    {
        this.orderCondOperator = l_strOrderCondOperator;
    }
    
    /**
     * �iget�����������Z�q�j�B<BR>
     * <BR>
     * �����������Z�q���擾����B
     * @@return String
     * @@roseuid 40AC522501EF
     */
    public String getOrderCondOperator() 
    {
        return this.orderCondOperator;
    }
    
    /**
     * �iset�t�w�l��l�j�B<BR>
     * <BR>
     * �t�w�l��l���Z�b�g����B
     * @@param l_dblStopOrderPrice �t�w�l��l
     * @@roseuid 40AC524301B1
     */
    public void setStopOrderPrice(double l_dblStopOrderPrice) 
    {
        this.stopOrderPrice = l_dblStopOrderPrice;
    }
    
    /**
     * �iget�t�w�l��l�j�B<BR>
     * <BR>
     * �t�w�l��l���擾����B
     * @@return double
     * @@roseuid 40AC524C0124
     */
    public double getStopOrderPrice() 
    {
        return this.stopOrderPrice;
    }
    
    /**
     * �iset�iW�w�l�j�����w�l�j�B<BR>
     * <BR>
     * (W�w�l)�����w�l���Z�b�g����B
     * @@param l_dblWLimitPrice (W�w�l)�����w�l
     * @@roseuid 40A8B958006A
     */
    public void setWLimitPrice(double l_dblWLimitPrice) 
    {
        this.wLimitPrice = l_dblWLimitPrice;
    }
    
    /**
     * �iget�iW�w�l�j�����w�l�j�B<BR>
     * <BR>
     * (W�w�l)�����w�l���擾����B
     * @@return double
     * @@roseuid 40A8B9580069
     */
    public double getWLimitPrice() 
    {
        return this.wLimitPrice;
    }
    
    /**
     * �iset�ٍϋ敪�j�B<BR>
     * <BR>
     * �ٍϋ敪���Z�b�g����B
     * @@param l_strRepaymentType �ٍϋ敪�B
     * @@roseuid 40AACDDC027F
     */
    public void setRepaymentType(String l_strRepaymentType) 
    {
        this.repaymentType = l_strRepaymentType;
    }
    
    /**
     * �iget�ٍϋ敪�j�B<BR>
     * <BR>
     * �ٍϋ敪���擾����B
     * @@return String
     * @@roseuid 40AACDDC026F
     */
    public String getRepaymentType() 
    {
        return this.repaymentType;
    }
    
    /**
     * �iset�ٍϊ����l�j�B<BR>
     * <BR>
     * �ٍϊ����l���Z�b�g����B
     * @@param l_dblRepaymentNum �ٍϊ����l�B
     * @@roseuid 40ADAEA301E2
     */
    public void setRepaymentNum(double l_dblRepaymentNum) 
    {
        this.repaymentNum = l_dblRepaymentNum;
    }
    
    /**
     * �iget�ٍϊ����l�j�B<BR>
     * <BR>
     * �ٍϊ����l���擾����B
     * @@return double
     * @@roseuid 40ADAEA301F1
     */
    public double getRepaymentNum() 
    {
        return this.repaymentNum;
    }
    
    /**
     * �iset���񒍕��̒����P��ID�j�B<BR>
     * <BR>
     * ���񒍕��̒����P��ID���Z�b�g����B
     * @@param l_lngFirstOrderUnitId ���񒍕��̒����P��ID�B<BR>
     * �@@�@@�@@�i�����P�ʃe�[�u��.���񒍕��̒����P��ID�j
     * @@roseuid 40AB1B4600C9
     */
    public void setFirstOrderUnitId(Long l_lngFirstOrderUnitId) 
    {
        this.firstOrderUnitId = l_lngFirstOrderUnitId;
    }
    
    /**
     * �iget���񒍕��̒����P��ID�j�B<BR>
     * <BR>
     * ���񒍕��̒����P��ID���擾����B<BR>
     * <BR>
     * �i�����P�ʃe�[�u��.���񒍕��̒����P��ID�j
     * @@return Long
     * @@roseuid 40AB1B4600D9
     */
    public Long getFirstOrderUnitId() 
    {
        return this.firstOrderUnitId;
    }
    
    /**
     * �iset������j�B<BR>
     * <BR>
     * ��������Z�b�g����B
     * @@param l_dblPrice ������B
     * @@roseuid 40B1AFFF0128
     */
    public void setContractAmount(double l_dblPrice) 
    {
        this.contractAmount = l_dblPrice;
    }
    
    /**
     * �iget������j�B<BR>
     * <BR>
     * ��������擾����B
     * @@return double
     * @@roseuid 40B1AFFF0137
     */
    public double getContractAmount() 
    {
        return this.contractAmount;
    }
    
    /**
     * �iis�o����܂Œ����j�B<BR>
     * <BR>
     * �Y���������o����܂Œ����̏ꍇtrue<BR>
     * �����̂ݒ����̏ꍇfalse��Ԃ��B<BR>
     * <BR>
     * �P�j�@@this.���񒍕��̒����P��ID != null�̏ꍇ�́Atrue��Ԃ��B<BR>
     * �@@�@@�@@this.���񒍕��̒����P��ID == null�̏ꍇ�́Afalse��Ԃ��B
     * @@return boolean
     * @@roseuid 40AB1B710211
     */
    public boolean isCarriedOrder() 
    {
        if (this.firstOrderUnitId != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * �iis�iW�w�l�j�����w�l�j�B<BR>
     * <BR>
     * �EW�w�l�����̏ꍇ�́u�iW�w�l�j�����w�l�v���w�l�ł��邩�ǂ�����Ԃ��B<BR>
     * �@@�w�l�̏ꍇ��true���A���s�̏ꍇ��false��Ԃ��B<BR>
     * �EW�w�l�����łȂ��ꍇ�́A��O��throw����B<BR>
     * <BR>
     * �P�j�@@this.get��������( ) != �hW�w�l�h�̏ꍇ�́A��O��throw����B<BR>
     * <BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00726<BR>
     * <BR>
     * �Q�j�@@this.get�iW�w�l�j�����w�l( ) == 0 �̏ꍇ�́Afalse��Ԃ��B�ȊO�Atrue��Ԃ��B<BR>
     * @@return boolean
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40AB1B710221
     */
    public boolean isWLimitPrice()
        throws WEB3BusinessLayerException  
    {
        final String STR_METHOD_NAME = "isWLimitPrice()";
        log.entering(STR_METHOD_NAME);
         
        //�P�j�@@this.get��������( ) != �hW�w�l�h�̏ꍇ�́Afalse��Ԃ��B 
        if (!(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(getOrderConditionType())))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00726, STR_METHOD_NAME);
        }
        
        boolean l_result = true;
        //�Q�j�@@this.get�iW�w�l�j�����w�l( ) == 0 �̏ꍇ�́Afalse��Ԃ��B�ȊO�Atrue��Ԃ�
        if(String.valueOf(this.getWLimitPrice()) != null)
        {
            if(this.getWLimitPrice() == 0)
            {
                l_result = false;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * (set�i�v�w�l�j���s����)<BR>
     * �i�v�w�l�j���s�������Z�b�g����B<BR>
     * @@param l_wlimitExecCondType - (�i�v�w�l�j���s����)<BR>
     * �i�v�w�l�j���s����<BR>
     * @@roseuid 40B30A97020D
     */
    public void setWlimitExecCondType(EqTypeExecutionConditionType l_wlimitExecCondType)
    {
        this.wlimitExecCondType = l_wlimitExecCondType;
    }

    /**
     * (get�i�v�w�l�j���s����)<BR>
     * �i�v�w�l�j���s�������擾����B<BR>
     * @@return EqTypeExecutionConditionType
     * @@roseuid 40B30A97021E
     */
    public EqTypeExecutionConditionType getWlimitExecCondType()
    {
        return this.wlimitExecCondType;
    }
}
@
