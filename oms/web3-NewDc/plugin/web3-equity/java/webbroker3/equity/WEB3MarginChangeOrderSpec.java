head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  * (�M�p�V�K�������������e)<BR>
                 :  * �M�p����̐V�K�������������e�̓��͂�\������B<BR>
                 :  * �����}�l�[�W���ɓn���p�����^�ɂȂ�B<BR>
                 :  * xTrade��EqTypeChangeOrderSpec���g�������N���X�B(WEB3MarginChangeOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
                   2004/12/15 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2004/12/29 �����a��(SRA) �p�����[�^�����C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2006/11/01 ��іQ (���u) ���f�� No.1008
*/
package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�V�K�������������e�j�B<BR>
 * <BR>
 * �M�p����̐V�K�������������e�̓��͂�\������B<BR>
 * �����}�l�[�W���ɓn���p�����^�ɂȂ�B<BR>
 * <BR>
 * xTrade��EqTypeChangeOrderSpec���g�������N���X�B
 * @@version 1.0
 */
public class WEB3MarginChangeOrderSpec extends EqTypeChangeOrderSpec 
{ 
   /**
    * log<BR>
    */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3MarginChangeOrderSpec.class);

    
    /**
     * �i�M�p�V�K�������������e�j�B<BR>
     * <BR>
     * �R���X�g���N�^�B
     * @@param l_lngOrderId (����ID)
     * @@param l_lngOrderUnitId (�����P��ID)
     * @@param l_dblModifiedOrderQuantity (�����㒍������)
     * @@param l_dblModifiedCalcUnitPrice (������w�l)
     */
    public WEB3MarginChangeOrderSpec(
        long l_lngOrderId,
        long l_lngOrderUnitId,
        double l_dblModifiedOrderQuantity,
        double l_dblModifiedCalcUnitPrice)
    {
        super(
            l_lngOrderId,
            l_lngOrderUnitId,
            l_dblModifiedOrderQuantity,
            l_dblModifiedCalcUnitPrice);
    }

    /**
     * (�����㒍������)<BR>
     * �������͂̒�������
     */
    private double modifiedOrderQuantity;
    
    /**
     * (������v�Z�P��)<BR>
     * �������͂̒����P�����Z�o�����v�Z�P��
     */
    private double modifiedCalcUnitPrice;
    
    /**
     * (�����㎷�s����)<BR>
     * �������͂̎��s�����B<BR>
     * �i1�F�����Ȃ��@@2�F���@@3�F�����@@6�F�s�o���������s�j<BR>
     */
    private EqTypeExecutionConditionType modifiedExecutionType;
    
    /**
     * (�����㒍��������)<BR>
     * �������͂̒���������
     * (�o����܂Œ����̏ꍇ�̂�)
     */
    private Date modifiedExpirationDate;
    
    /**
     * (������l�i����)<BR>
     * �������͂̒l�i�����B<BR>
     */
    private String modifiedPriceConditionType;
    
    /**
     * (�����㔭������)<BR>
     * �������͂̔�������<BR>
     * 0�FDEFAULT�i�����w��Ȃ��j<BR>
     * 1�F�t�w�l<BR>
     * 2�FW�w�l<BR>
     */
    private String modifiedOrderConditionType;
    
    /**
     * (�����㔭���������Z�q)<BR>
     * �������͂̔����������Z�q<BR>
     * �i0�FEqual�i��l�Ɠ����l�ɂȂ�����j�@@<BR>
     * 1�F��l�ȏ�@@<BR>
     * 2�F��l�ȉ��j<BR>
     */
    private String modifiedOrderCondOperator;
    
    /**
     * (������t�w�l��l)<BR>
     * �������͂̋t�w�l��l<BR>
     * �i�t�w�l�AW�w�l�̏ꍇ�̂݃Z�b�g�j<BR>
     */
    private double modifiedStopOrderPrice;
    
    /**
     * (������(W�w�l)�����w�l)<BR>
     * �������͂́iW�w�l�j�����w�l�B
     * �iW�w�l�̏ꍇ�̂݃Z�b�g�j
     */
    private double modifiedWLimitPrice;

    /**
     * (������iW�w�l�j���s����)<BR>
     * ������iW�w�l�j���s����<BR>
     * �iW�w�l�̏ꍇ�̂݃Z�b�g�j<BR>
     */
    private EqTypeExecutionConditionType modifiedWlimitExecCondType;

    /**
     * (�����㌚���)<BR>
     */
    private double modifiedContractAmount;
    
    /**
     * (������is�o����܂Œ���)<BR>
     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B<BR>
     * �o����܂Œ����ł����true�A�ȊO��false�B<BR>
     */
    private boolean modifiedIsCarriedOrder;
    
    /**
     * (����)<BR>
     * �㗝���͈��ҁB
     */
    private Trader trader;

    /**
     * (�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪<BR>
     */
    private String wlimitEnableStatusDiv;

    /**
     * (create�V�K�������������e)<BR>
     * �istatic���\�b�h�j<BR>
     * �M�p�V�K�������������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �葱���̓V�[�P���X�}�u�i�M�p�����jcreate�V�K�������������e�v���Q�ƁB<BR>
     * @@param l_lngOrderId - ����ID
     * @@param l_lngOrderUnitId - �����P��ID
     * @@param l_dblModifiedOrderQuantity - (�����㒍������)<BR>
     * �������͂̒�������
     * @@param l_dblModifiedCalcUnitPrice - (������w�l)<BR>
     * �������͂̒����P��
     * @@param l_modifiedExecutionType - (�����㎷�s����)<BR>
     * �������͂̎��s����
     * @@param l_datModifiedExpirationDate - (�����㒍��������)<BR>
     * �������͂̒���������
     * @@param l_strModifiedPriceConditionType - (������l�i����)<BR>
     * �������͂̒l�i����
     * @@param l_strOrderConditionType - ��������
     * @@param l_strModifiedOrderCondOperat - (�����㔭���������Z�q)<BR>
     * �������͂̔����������Z�q
     * @@param l_dblModifiedStopOrderPrice - (������t�w�l��l)<BR>
     * �������͂̋t�w�l��l
     * @@param l_dblModifiedWLimitPrice - (������iW�w�l�j�����w�l)<BR>
     * �������͂�(W�w�l)�����w�l
     * @@param l_blnModifiedIsCarriedOrder - (������is�o����܂Œ���)<BR>
     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B<BR>
     * �o����܂Œ����ł����true�A�ȊO��false�B<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * ���҃I�u�W�F�N�g
     * @@return WEB3MarginChangeOrderSpec
     * @@roseuid 40E11F070120
     */
    public static WEB3MarginChangeOrderSpec createOpenMarginChangeOrderSpec(
        long l_lngOrderId,
        long l_lngOrderUnitId,
        double l_dblModifiedOrderQuantity,
        double l_dblModifiedCalcUnitPrice,
        EqTypeExecutionConditionType l_modifiedExecutionType,
        Date l_datModifiedExpirationDate,
        String l_strModifiedPriceConditionType,
        String l_strOrderConditionType,
        String l_strModifiedOrderCondOperat,
        double l_dblModifiedStopOrderPrice,
        double l_dblModifiedWLimitPrice,
        boolean l_blnModifiedIsCarriedOrder,
        Trader l_trader) 
    {
        String STR_METHOD_NAME="createOpenMarginChangeOrderSpec(long,long,double,double,EqTypeExecutionConditionType,Date,String,String,double,double,boolean,Trader)";
        log.entering(STR_METHOD_NAME);
        //�葱���̓V�[�P���X�}�u�i�M�p�����jcreate�V�K�������������e�v���Q�ƁB<BR>
        //    * @@param l_lngOrderId - ����ID
        //    * @@param l_lngOrderUnitId - �����P��ID
        //    * @@param l_dblModifiedOrderQuantity - (�����㒍������)<BR>
        //    * @@param l_dblModifiedCalcUnitPrice - (������w�l)<BR>
        WEB3MarginChangeOrderSpec l_marginChangeOrderSpec = new WEB3MarginChangeOrderSpec(l_lngOrderId,l_lngOrderUnitId,l_dblModifiedOrderQuantity,l_dblModifiedCalcUnitPrice);
        // * @@param l_modifiedExecutionType - (�����㎷�s����)<BR>
        //*(�����㒍������)
        l_marginChangeOrderSpec.setModifiedOrderQuantity(l_dblModifiedOrderQuantity);
        //* �������͂̎��s����
        l_marginChangeOrderSpec.setModifiedExecutionType(l_modifiedExecutionType);
        //    * @@param l_datModifiedExpirationDate - (�����㒍��������)<BR>
        //    * �������͂̒���������
        l_marginChangeOrderSpec.setModifiedExpirationDate(l_datModifiedExpirationDate);
        //    * �������͂̒l�i����
        l_marginChangeOrderSpec.setModifiedPriceConditionType(l_strModifiedPriceConditionType);
        //    * @@param l_strOrderConditionType - ��������
        l_marginChangeOrderSpec.setModifiedOrderConditionType(l_strOrderConditionType);
        //    * @@param l_strModifiedOrderCondOperat - (�����㔭���������Z�q)<BR>
        //    * �������͂̔����������Z�q
        l_marginChangeOrderSpec.setModifiedOrderCondOperator(l_strModifiedOrderCondOperat);
        //* @@param l_dblModifiedStopOrderPrice - (������t�w�l��l)<BR>
        //     * �������͂̋t�w�l��l
        l_marginChangeOrderSpec.setModifiedStopOrderPrice(l_dblModifiedStopOrderPrice);
        //     * @@param l_dblModifiedWLimitPrice - (������iW�w�l�j�����w�l)<BR>
        //     * �������͂�(W�w�l)�����w�l
        l_marginChangeOrderSpec.setModifiedWLimitPrice(l_dblModifiedWLimitPrice);
        //     * @@param l_blnModifiedIsCarriedOrder - (������is�o����܂Œ���)<BR>
        //     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B<BR>
        l_marginChangeOrderSpec.setModifiedIsCarriedOrder(l_blnModifiedIsCarriedOrder);
        //     * @@param l_trader - (�㗝���͎�)<BR>
        //     * ���҃I�u�W�F�N�g
        l_marginChangeOrderSpec.setTrader(l_trader);
        log.exiting(STR_METHOD_NAME);
        return l_marginChangeOrderSpec;
    }

    /**
     * (create�V�K�������������e)<BR>
     * �istatic���\�b�h�j<BR>
     * �M�p�V�K�������������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �P�j�@@this.create�M�p�V�K�������������e()��call����B<BR>
     * �@@[����] <BR>
     * �@@�@@����ID�F�@@�i�������ҏW�j<BR>
     * �@@�@@�����P��ID�F�@@�i�������ҏW�j <BR>
     * �@@�@@�����㒍�������F�@@�i�������ҏW�j <BR>
     * �@@�@@������w�l�F�@@�i�������ҏW�j <BR>
     * �@@�@@�����㎷�s�����F�@@�i�������ҏW�j <BR>
     * �@@�@@�����㒍���������F�@@�i�������ҏW�j <BR>
     * �@@�@@������l�i�����F�@@�i�������ҏW�j <BR>
     * �@@�@@���������F�@@�i�������ҏW�j <BR>
     * �@@�@@�����㔭���������Z�q�F�@@�i�������ҏW�j <BR>
     * �@@�@@������t�w�l��l�F�@@�i�������ҏW�j <BR>
     * �@@�@@������(W�w�l)�����w�l�F�@@�i�������ҏW�j <BR>
     * �@@�@@������is�o����܂Œ����F�@@�i�������ҏW�j <BR>
     * �@@�@@�㗝���͎ҁF�@@�i�������ҏW�j <BR>
     * <BR>
     * �Q�j�@@�g���v���p�e�B���Z�b�g���� <BR>
     * <BR>
     * �@@�|������iW�w�l�j���s�����F�@@�i�������ҏW�j <BR>
     * �@@�|�iW�w�l�j�����L����ԁF�@@�i�������ҏW�j<BR>
     * @@param l_lngOrderId - (����ID)
     * ����ID
     * @@param l_lngOrderUnitId - (�����P��ID)
     * �����P��ID
     * @@param l_dblModifiedOrderQuantity - (�����㒍������)<BR>
     * �������͂̒�������
     * @@param l_dblModifiedCalcUnitPrice - (������w�l)<BR>
     * �������͂̒����P��
     * @@param l_modifiedExecutionType - (�����㎷�s����)<BR>
     * �������͂̎��s����
     * @@param l_datModifiedExpirationDate - (�����㒍��������)<BR>
     * �������͂̒���������
     * @@param l_strModifiedPriceConditionType - (������l�i����)<BR>
     * �������͂̒l�i����
     * @@param l_strOrderConditionType - (��������)
     * ��������
     * @@param l_strModifiedOrderCondOperat - (�����㔭���������Z�q)<BR>
     * �������͂̔����������Z�q
     * @@param l_dblModifiedStopOrderPrice - (������t�w�l��l)<BR>
     * �������͂̋t�w�l��l
     * @@param l_dblModifiedWLimitPrice - (������iW�w�l�j�����w�l)<BR>
     * �������͂�(W�w�l)�����w�l
     * @@param l_modifiedWlimitExecCondType - (������iW�w�l�j���s����)<BR>
     * �������͂́iW�w�l�j���s����<BR>
     * @@param l_blnModifiedIsCarriedOrder - (������is�o����܂Œ���)<BR>
     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B<BR>
     * �o����܂Œ����ł����true�A�ȊO��false�B<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * ���҃I�u�W�F�N�g
     * @@param l_strWlimitEnableStatusDiv - (�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪<BR>
     * @@return WEB3MarginChangeOrderSpec
     * @@roseuid 40E11F070120
     */
    public static WEB3MarginChangeOrderSpec createOpenMarginChangeOrderSpec(
        long l_lngOrderId,
        long l_lngOrderUnitId,
        double l_dblModifiedOrderQuantity,
        double l_dblModifiedCalcUnitPrice,
        EqTypeExecutionConditionType l_modifiedExecutionType,
        Date l_datModifiedExpirationDate,
        String l_strModifiedPriceConditionType,
        String l_strOrderConditionType,
        String l_strModifiedOrderCondOperat,
        double l_dblModifiedStopOrderPrice,
        double l_dblModifiedWLimitPrice,
        EqTypeExecutionConditionType l_modifiedWlimitExecCondType,
        boolean l_blnModifiedIsCarriedOrder,
        Trader l_trader,
        String l_strWlimitEnableStatusDiv)
    {
        final String STR_METHOD_NAME = "createOpenMarginChangeOrderSpec(long, long, "
            + "double, double, EqTypeExecutionConditionType, Date, "
            + "String, String, String, double, double, EqTypeExecutionConditionType, boolean, "
            + "Trader, String";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@this.create�M�p�V�K�������������e()��call����B
        WEB3MarginChangeOrderSpec l_orderSpec =
            WEB3MarginChangeOrderSpec.createOpenMarginChangeOrderSpec(
                l_lngOrderId,
                l_lngOrderUnitId,
                l_dblModifiedOrderQuantity,
                l_dblModifiedCalcUnitPrice,
                l_modifiedExecutionType,
                l_datModifiedExpirationDate,
                l_strModifiedPriceConditionType,
                l_strOrderConditionType,
                l_strModifiedOrderCondOperat,
                l_dblModifiedStopOrderPrice,
                l_dblModifiedWLimitPrice,
                l_blnModifiedIsCarriedOrder,
                l_trader);

        //�Q�j�@@�g���v���p�e�B���Z�b�g����
        //�|������iW�w�l�j���s�����F�@@�i�������ҏW�j
        l_orderSpec.setModifiedWlimitExecCondType(l_modifiedWlimitExecCondType);

        //�@@�|�iW�w�l�j�����L����ԁF�@@�i�������ҏW�j
        l_orderSpec.setWlimitEnableStatusDiv(l_strWlimitEnableStatusDiv);

        log.exiting(STR_METHOD_NAME);

        return l_orderSpec;
    }

    /**
     * (get�V�K�������������e�ڍ�)<BR>
     * �igetChangeOrderUnitEntry�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �V�K�������������e�ڍׂ��擾����B<BR>
     * <BR>
     * getChangeOrderUnitEntries( )�ɂĐV�K�������������e�ڍ׈ꗗ���擾�A<BR>
     * �߂�lList��0�Ԗڂ̗v�f��ԋp����B<BR>
     * @@return EqTypeChangeOrderUnitEntry
     * @@roseuid 40E3D15C03E6
     */
    public EqTypeChangeOrderUnitEntry getChangeOrderUnitEntry() 
    {
        return super.getChangeOrderUnitEntries()[0];
    }
    
    /**
     * (get�����㒍������)<BR>
     * �����㒍���������擾����B
     * @@return double
     * @@roseuid 40E11A9B0362
     */
    public double getModifiedOrderQuantity() 
    {
        return modifiedOrderQuantity;
    }
    
    /**
     * (set�����㒍������)<BR>
     * �����㒍���������Z�b�g����B
     * @@param l_dblModifiedOrderQuantity - �������͂̒�������
     * @@roseuid 40E11ADB0017
     */
    public void setModifiedOrderQuantity(double l_dblModifiedOrderQuantity) 
    {
        this.modifiedOrderQuantity = l_dblModifiedOrderQuantity;
    }
    
    /**
     * (get������v�Z�P��)<BR>
     * ������v�Z�P�����擾����B
     * @@return double
     * @@roseuid 40E11B3E019D
     */
    public double getModifiedCalcUnitPrice() 
    {
        return modifiedCalcUnitPrice;
    }
    
    /**
     * (set������v�Z�P��)<BR>
     * ������v�Z�P�����Z�b�g����B
     * @@param l_dblModifiedCalcUnitPrice - (������v�Z�P��)<BR>
     * �������͂̒����P�����Z�o�����v�Z�P��
     * @@roseuid 40E11B3E01AD
     */
    public void setModifiedCalcUnitPrice(double l_dblModifiedCalcUnitPrice) 
    {
        this.modifiedCalcUnitPrice = l_dblModifiedCalcUnitPrice;
    }
    
    /**
     * (get�����㎷�s����)<BR>
     * �����㎷�s�������擾����B
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType
     * @@roseuid 40E11B7E0036
     */
    public EqTypeExecutionConditionType getModifiedExecutionType() 
    {
        return this.modifiedExecutionType ;
    }
    
    /**
     * (set�����㎷�s����)<BR>
     * �����㎷�s�������Z�b�g����B
     * @@param l_modifiedExecutionType - (�����㎷�s����)<BR>
     * �������͂̎��s����
     * @@roseuid 40E11B7E0055
     */
    public void setModifiedExecutionType(EqTypeExecutionConditionType l_modifiedExecutionType) 
    {
        this.modifiedExecutionType = l_modifiedExecutionType;
    }
    
    /**
     * (get�����㒍��������)<BR>
     * �����㒍�����������擾����B
     * @@return Date
     * @@roseuid 40E11C170074
     */
    public Date getModifiedExpirationDate() 
    {
        return this.modifiedExpirationDate;
    }
    
    /**
     * (set�����㒍��������)<BR>
     * �����㒍�����������Z�b�g����B
     * @@param l_datModifiedExpirationDate - (�����㒍��������)<BR>
     * �������͂̒���������
     * @@roseuid 40E11C170084
     */
    public void setModifiedExpirationDate(Date l_datModifiedExpirationDate) 
    {
        this.modifiedExpirationDate = l_datModifiedExpirationDate;
    }
    
    /**
     * (set������l�i����)<BR>
     * �l�i�������Z�b�g����B<BR>
     * @@param l_strChangeAfterPriceConditionType - ������l�i����<BR>
     * ������l�i����<BR>
     * 0�F�@@DEFAULT(�����w��Ȃ�)<BR>
     * 1�F�@@���ݒl�w�l����<BR>
     * 3�F�@@�D��w�l����<BR>
     * 5�F�@@���s�c���w�l����<BR>
     * 7�F�@@���s�c���������<BR>
     */
    public void setModifiedPriceConditionType(String l_strChangeAfterPriceConditionType) 
    {
        this.modifiedPriceConditionType = l_strChangeAfterPriceConditionType;
    }
    
    /**
     * (get������l�i����)<BR>
     * ������̒l�i�������擾����B<BR>
     * @@return String
     */
    public String getModifiedPriceConditionType() 
    {
        return this.modifiedPriceConditionType;
    }
    
    /**
     * (get�����㔭������)<BR>
     * ���������敪���擾����B
     * @@return String
     * @@roseuid 40E11C790391
     */
    public String getModifiedOrderConditionType() 
    {
     return this.modifiedOrderConditionType;
    }
    
    /**
     * (set�����㔭������)<BR>
     * ���������敪���Z�b�g����B
     * @@param l_strModifiedOrderConditionType - �����㔭������<BR>
     * 0�FDEFAULT�i�����w��Ȃ��j<BR>
     * 1�F�t�w�l<BR>
     * 2�FW�w�l<BR>
     * @@roseuid 40E11C7903B1
     */
    public void setModifiedOrderConditionType(String l_strModifiedOrderConditionType) 
    {
        this.modifiedOrderConditionType = l_strModifiedOrderConditionType;
    }
    
    /**
     * (get�����㔭���������Z�q)<BR>
     * �����㔭���������Z�q���擾����B
     * @@return String
     * @@roseuid 40E11D940065
     */
    public String getModifiedOrderCondOperator() 
    {
        return this.modifiedOrderCondOperator;
    }
    
    /**
     * (set�����㔭���������Z�q)<BR>
     * �����㔭���������Z�q���Z�b�g����B
     * @@param l_strModifiedOrderCondOperator - (�����㔭���������Z�q)<BR>
     * �������͂̔����������Z�q<BR>
     * �i0�FEqual�i��l�Ɠ����l�ɂȂ�����j�@@<BR>
     * 1�F��l�ȏ�@@<BR>
     * 2�F��l�ȉ��j<BR>
     * @@roseuid 40E11D940074
     */
    public void setModifiedOrderCondOperator(String l_strModifiedOrderCondOperator) 
    {
        this.modifiedOrderCondOperator = l_strModifiedOrderCondOperator;
    }
    
    /**
     * (get������t�w�l��l)<BR>
     * ������t�w�l��l���擾����B
     * @@return double
     * @@roseuid 40E11D4B02A7
     */
    public double getModifiedStopOrderPrice() 
    {
        return this.modifiedStopOrderPrice;
    }
    
    /**
     * (set������t�w�l��l)<BR>
     * ������t�w�l��l���Z�b�g����B
     * @@param l_dblModifiedStopOrderPrice - (������t�w�l��l)<BR>
     * �������͂̋t�w�l��l
     * @@roseuid 40E11D4B02C6
     */
    public void setModifiedStopOrderPrice(double l_dblModifiedStopOrderPrice) 
    {
        this.modifiedStopOrderPrice = l_dblModifiedStopOrderPrice;
    }
    
    /**
     * (get������iW�w�l�j�����w�l)<BR>
     * ������(W�w�l)�����w�l���擾����B
     * @@return double
     * @@roseuid 40E11DE0014F
     */
    public double getModifiedWLimitPrice() 
    {
     return this.modifiedWLimitPrice;
    }
    
    /**
     * (set������iW�w�l�j�����w�l)<BR>
     * ������(W�w�l)�����w�l���Z�b�g����B
     * @@param l_dblModifiedWLimitPrice - (������iW�w�l�j�����w�l)<BR>
     * �������͂�(W�w�l)�����w�l
     * @@roseuid 40E11DE0015F
     */
    public void setModifiedWLimitPrice(double l_dblModifiedWLimitPrice) 
    {
        this.modifiedWLimitPrice = l_dblModifiedWLimitPrice;
    }
    
    /**
     * (get�����㌚���)<BR>
     * �����㌚������擾����B
     * @@return double
     * @@roseuid 40E8D99E0139
     */
    public double getModifiedContractAmount() 
    {
        return this.modifiedContractAmount;
    }
    
    /**
     * (set�����㌚���)<BR>
     * �����㌚������Z�b�g����B
     * @@param l_dblPrice - ������B
     * @@roseuid 40E8D99E0129
     */
    public void setModifiedContractAmount(double l_dblContractAmount) 
    {
        this.modifiedContractAmount = l_dblContractAmount;
    }
    
    /**
     * (set������is�o����܂Œ���)<BR>
     * ������is�o����܂Œ������Z�b�g����B
     * @@param l_blnModifiedIsCarriedOrder - (������is�o����܂Œ���)<BR>
     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B
     * �o����܂Œ����ł����true�A�ȊO��false�B
     * @@roseuid 40E4CFF30370
     */
    public void setModifiedIsCarriedOrder(boolean l_blnModifiedIsCarriedOrder) 
    {
        this.modifiedIsCarriedOrder = l_blnModifiedIsCarriedOrder;
    }
    
    /**
     * (is������o����܂Œ���)<BR>
     * �����オ�u�o����܂Œ����v�ł��邩�ǂ�����Ԃ��B<BR>
     * this.������is�o����܂Œ����̒l��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 40E4CFBA0276
     */
    public boolean isModifiedIsCarriedOrder() 
    {
        return this.modifiedIsCarriedOrder;
    }
    
    /**
     * (is������iW�w�l�j�����w�l)<BR>
     * �EW�w�l�����̏ꍇ�́u�iW�w�l�j�����w�l�v���w�l�ł��邩�ǂ�����Ԃ��B<BR>
     * �@@�w�l�̏ꍇ��true���A���s�̏ꍇ��false��Ԃ��B<BR>
     * �EW�w�l�����łȂ��ꍇ�́A��O��throw����B<BR>
     * <BR>
     * �P�j�@@this.get�����㔭������( ) != �hW�w�l�h�̏ꍇ�́A��O��throw����B<BR>
     * <BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00726<BR>   
     * <BR>
     * �Q�j�@@this.get������iW�w�l�j�����w�l( ) == 0 �̏ꍇ�́Afalse��Ԃ��B�ȊO�A<BR>
     * true��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 40E4C1A8039E
     */
    public boolean isModifiedWLimitPrice() 
        throws WEB3BusinessLayerException
    {
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.getModifiedOrderConditionType()))
        {
            //throw new  
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00726, "isModifiedWLimitPrice()");
        } 
        if(this.getModifiedWLimitPrice() == 0)
        {
            return false;
        }
        return true;
    }
    
    /**
     * (get����)<BR>
     * ���҂��擾����B
     * @@return Trader
     * @@roseuid 40ED1C9F0216
     */
    public Trader getTrader() 
    {
        return this.trader;
    }
    
    /**
     * (set����)<BR>
     * ���҂��Z�b�g����B
     * @@param l_trader - (�㗝���͎�)<BR>
     * ���҃I�u�W�F�N�g
     * @@roseuid 40ED1C9F0225
     */
    public void setTrader(Trader l_trader) 
    {
        this.trader = l_trader;
    }

    /**
     * (set������iW�w�l�j���s���� )<BR>
     * ������iW�w�l�j���s�������Z�b�g����B<BR>
     * @@param l_modifiedWlimitExecCondType - (������iW�w�l�j���s����)<BR>
     * ������iW�w�l�j���s����<BR>
     * @@roseuid 40B30A97020D
     */
    public void setModifiedWlimitExecCondType(
        EqTypeExecutionConditionType l_modifiedWlimitExecCondType)
    {
        this.modifiedWlimitExecCondType = l_modifiedWlimitExecCondType;
    }

    /**
     * (get������iW�w�l�j���s����)<BR>
     * ������iW�w�l�j���s�������擾����B<BR>
     * @@return EqTypeExecutionConditionType
     * @@roseuid 40B30A97021E
     */
    public EqTypeExecutionConditionType getModifiedWlimitExecCondType()
    {
        return this.modifiedWlimitExecCondType;
    }

    /**
     * (set�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪���Z�b�g����B<BR>
     * @@param l_wlimitEnableStatusDiv - (�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪<BR>
     * @@roseuid 40B30A97020D
     */
    public void setWlimitEnableStatusDiv(String  l_strWlimitEnableStatusDiv)
    {
        this.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;
    }

    /**
     * (get�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪���擾����B<BR>
     * @@return String
     * @@roseuid 40B30A97021E
     */
    public String  getWlimitEnableStatusDiv()
    {
        return this.wlimitEnableStatusDiv;
    }

    /**
     * (is�X�g�b�v�����L��)<BR>
     * this.�iW�w�l�j�L����ԋ敪 == "�X�g�b�v�����L��"�̏ꍇ�Atrue�A<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isStopOrderEnable()
    {
        if (WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE.equals(
            this.wlimitEnableStatusDiv))
        {
            return true;
        }
        return false;
    }

}
@
