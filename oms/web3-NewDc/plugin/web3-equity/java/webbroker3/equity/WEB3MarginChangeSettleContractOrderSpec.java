head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeSettleContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�ԍϒ����������e(WEB3MarginChangeSettleContractOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 ������ (���u) �V�K�쐬
                   2004/12/09 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2004/12/29 �����a��(SRA) �p�����[�^�����C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2006/11/01 ��іQ (���u) ���f�� No.1020, 1048, 1050
*/

package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�ԍϒ����������e�j�B<BR>
 * <BR>
 * �M�p����̕ԍϒ����������e�̓��͂�\������B<BR>
 * �����}�l�[�W���ɓn���p�����^�ɂȂ�B<BR>
 * <BR>
 * xTrade��EqTypeChangeSettleContractOrderSpec���g�������N���X�B
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginChangeSettleContractOrderSpec extends EqTypeChangeSettleContractOrderSpec 
{
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityNewCashBasedOrderSpec.class);
    
    /**
     * �R���X�g���N�^�B<BR>
     * @@param l_lngOrderId - (����ID)
     * @@param l_changeOrderUnitEntry - (���������ڍ�)
     */
    public WEB3MarginChangeSettleContractOrderSpec(
        long l_lngOrderId,
        EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry)
    {
        super(l_lngOrderId, l_changeOrderUnitEntry);
    }

    /**
     * (�����㔭������)<BR>
     * �������͂̔��������B<BR>
     * �i0�FDEFAULT�i�����w��Ȃ��j�@@1�F�t�w�l�@@2�FW�w�l�j
     */
    private String modifiedOrderConditionType;
    
    /**
     * (�����㔭���������Z�q)<BR>
     * �������͂̔����������Z�q�B<><BR>
     * �i0�FEqual�i��l�Ɠ����l�ɂȂ�����j�@@<BR>
     * 1�F��l�ȏ�@@<BR>
     * 2�F��l�ȉ��j<BR>
     */
    private String modifiedOrderCondOperator;
    
    /**
     * (������t�w�l��l)<BR>
     * �������͂̋t�w�l��l�B<BR>
     * �i�t�w�l�AW�w�l�̏ꍇ�̂݃Z�b�g�j<BR>
     */
    private double modifiedStopOrderPrice;
    
    /**
     * (������iW�w�l�j�����w�l)<BR>
     * �������͂́iW�w�l�j�����w�l�B<BR>
     * �iW�w�l�̏ꍇ�̂݃Z�b�g�j<BR>
     */
    private double modifiedWLimitPrice;
    
    /**
     * (�����㎷�s����)<BR>
     * �������͂̎��s�����B<BR>
     * �i1�F�����Ȃ��@@2�F���@@3�F�����@@6�F�s�o���������s�j<BR>
     */
    private EqTypeExecutionConditionType modifiedExecutionCondition;
    
    /**
     * (�����㒍��������)<BR>
     * �������͂̒����������B
     */
    private Date modifiedExpirationDate;

    /**
     * (������l�i����)<BR>
     * �������͂̒l�i�����B<BR>
     */
    private String modifiedPriceConditionType;

    /**
     * (������is�o����܂Œ���)<BR>
     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B<BR>
     * �o����܂Œ����ł����true�A�ȊO��false�B<BR>
     */
    private boolean modifiedIsCarriedOrder;

    /**
     * (������iW�w�l�j���s����)<BR>
     * ������iW�w�l�j���s����<BR>
     * �iW�w�l�̏ꍇ�̂݃Z�b�g�j<BR>
     */
    private EqTypeExecutionConditionType modifiedWlimitExecCondType;

    /**
     * (�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪<BR>
     */
    private String wlimitEnableStatusDiv;

    /**
     * (create�ԍϒ����������e)<BR>
     * �istatic���\�b�h�j<BR>
     * �M�p�ԍϒ����������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �葱���̓V�[�P���X�}�u�i�M�p�����jcreate�ԍϒ����������e�v���Q�ƁB<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * �������̒���ID<BR>
     * @@param l_changeOrderUnitEntry - ���������ڍ�<BR>
     * @@param l_strModifiedPriceConditionType - (������l�i����)<BR>
     * �������͂̒l�i����<BR>
     * @@param l_strModifiedOrderConditionType - (�����㔭������)<BR>
     * �������͂̔��������B<BR>
     * �i0�FDEFAULT�i�����w��Ȃ��j�@@1�F�t�w�l�@@2�FW�w�l�j<BR>
     * @@param l_strModifiedOrderCondOperator - (�����㔭���������Z�q)<BR>
     * �������͂̔����������Z�q�B<BR>
     * �i0�FEqual�i��l�Ɠ����l�ɂȂ�����j�@@<BR>
     * 1�F��l�ȏ�@@<BR>
     * 2�F��l�ȉ��j<BR>
     * @@param l_dblModifiedStopOrderPrice - (������t�w�l��l)<BR>
     * �������͂̋t�w�l��l�B<BR>
     * �i�t�w�l�AW�w�l�̏ꍇ�̂݃Z�b�g�j<BR>
     * @@param l_dblModifiedWLimitPrice - (������iW�w�l�j�����w�l)<BR>
     * �������͂́iW�w�l�j�����w�l�B<BR>
     * �iW�w�l�̏ꍇ�̂݃Z�b�g�j<BR>
     * @@param l_modifiedExecutionCondition - (�����㎷�s����)<BR>
     * �������͂̎��s�����B<BR>
     * �i1�F�����Ȃ��@@2�F���@@3�F�����@@6�F�s�o���������s�j<BR>
     * @@param l_datModifiedExpirationDate - (�����㒍��������)<BR>
     * �������͂̒����������B
     * @@param l_blnModifiedIsCarriedOrder - (������is�o����܂Œ���)<BR>
     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B<BR>
     * �o����܂Œ����ł����true�A�ȊO��false�B<BR>
     * @@return WEB3MarginCloseMarginChangeOrderSpec
     * @@roseuid 40C8504C0130
     */
    public static WEB3MarginChangeSettleContractOrderSpec createCloseMarginChangeOrderSpec(
        long l_lngOrderId, 
        EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry,
        String l_strModifiedPriceConditionType,
        String l_strModifiedOrderConditionType, 
        String l_strModifiedOrderCondOperator, 
        double l_dblModifiedStopOrderPrice, 
        double l_dblModifiedWLimitPrice, 
        EqTypeExecutionConditionType l_modifiedExecutionCondition, 
        Date l_datModifiedExpirationDate, 
        boolean l_blnModifiedIsCarriedOrder) 
    {
        String STR_METHOD_NAME="createCloseMarginChangeOrderSpec()";
        log.entering(STR_METHOD_NAME);
        
        //1 �ԍϒ����������e�C���X�^���X�𐶐�����B
        WEB3MarginChangeSettleContractOrderSpec l_orderSpec = 
            new WEB3MarginChangeSettleContractOrderSpec(l_lngOrderId, l_changeOrderUnitEntry);
        
        //2 ������l�i�������Z�b�g
        l_orderSpec.setModifiedPriceConditionType(l_strModifiedPriceConditionType);
        
        //3 �����㔭���������Z�b�g
        l_orderSpec.setModifiedOrderConditionType(l_strModifiedOrderConditionType);
        
        //4 �����㔭���������Z�q���Z�b�g
        l_orderSpec.setModifiedOrderCondOperator(l_strModifiedOrderCondOperator);
        
        //5 ������t�w�l��l���Z�b�g
        l_orderSpec.setModifiedStopOrderPrice(l_dblModifiedStopOrderPrice);
        
        //6 ������iW�w�l�j�����w�l���Z�b�g
        l_orderSpec.setModifiedWLimitPrice(l_dblModifiedWLimitPrice);
        
        //7 �����㎷�s�����iW�w�l�j�����w�l���Z�b�g
        l_orderSpec.setModifiedExecutionCondition(l_modifiedExecutionCondition);
        
        //8 �����㒍�����������Z�b�g
        l_orderSpec.setModifiedExpirationDate(l_datModifiedExpirationDate);
        
        //9 ������is�o����܂Œ������Z�b�g
        l_orderSpec.setModifiedIsCarriedOrder(l_blnModifiedIsCarriedOrder);
        
        log.exiting(STR_METHOD_NAME);
        
        return  l_orderSpec; 
    }

    /**
     * (create�ԍϒ����������e)<BR>
     * �istatic���\�b�h�j<BR>
     * �M�p�ԍϒ����������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �P�j�@@this.create�M�p�ԍϒ����������e()��call����B<BR>
     * �@@[����] <BR>
     * �@@�@@����ID�F�@@�i�������ҏW�j <BR>
     * �@@�@@���������ڍׁF�@@�i�������ҏW�j <BR>
     * �@@�@@������l�i�����F�@@�i�������ҏW�j <BR>
     * �@@�@@�����㔭�������F�@@�i�������ҏW�j <BR>
     * �@@�@@�����㔭���������Z�q�F�@@�i�������ҏW�j <BR>
     * �@@�@@������t�w�l��l�F�@@�i�������ҏW�j <BR>
     * �@@�@@������(W�w�l)�����w�l�F�@@�i�������ҏW�j <BR>
     * �@@�@@�����㎷�s�����F�@@�i�������ҏW�j <BR>
     * �@@�@@�����㒍���������F�@@�i�������ҏW�j <BR>
     * �@@�@@������is�o����܂Œ����F�@@�i�������ҏW�j <BR>
     * <BR>
     * �Q�j�@@�g���v���p�e�B���Z�b�g���� <BR>
     * <BR>
     * �@@�|������iW�w�l�j���s�����F�@@�i�������ҏW�j <BR>
     * �@@�|�iW�w�l�j�����L����ԁF�@@�i�������ҏW�j<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * �������̒���ID<BR>
     * @@param l_changeOrderUnitEntry - (���������ڍ�)<BR>
     * ���������ڍ�<BR>
     * @@param l_strModifiedPriceConditionType - (������l�i����)<BR>
     * �������͂̒l�i����<BR>
     * @@param l_strModifiedOrderConditionType - (�����㔭������)<BR>
     * �������͂̔��������B<BR>
     * �i0�FDEFAULT�i�����w��Ȃ��j�@@1�F�t�w�l�@@2�FW�w�l�j<BR>
     * @@param l_strModifiedOrderCondOperator - (�����㔭���������Z�q)<BR>
     * �������͂̔����������Z�q�B<BR>
     * �i0�FEqual�i��l�Ɠ����l�ɂȂ�����j�@@<BR>
     * 1�F��l�ȏ�@@<BR>
     * 2�F��l�ȉ��j<BR>
     * @@param l_dblModifiedStopOrderPrice - (������t�w�l��l)<BR>
     * �������͂̋t�w�l��l�B<BR>
     * �i�t�w�l�AW�w�l�̏ꍇ�̂݃Z�b�g�j<BR>
     * @@param l_dblModifiedWLimitPrice - (������iW�w�l�j�����w�l)<BR>
     * �������͂́iW�w�l�j�����w�l�B<BR>
     * �iW�w�l�̏ꍇ�̂݃Z�b�g�j<BR>
     * @@param l_modifiedExecutionCondition - (�����㎷�s����)<BR>
     * �������͂̎��s�����B<BR>
     * �i1�F�����Ȃ��@@2�F���@@3�F�����@@6�F�s�o���������s�j<BR>
     * @@param l_datModifiedExpirationDate - (�����㒍��������)<BR>
     * �������͂̒����������B<BR>
     * @@param l_blnModifiedIsCarriedOrder - (������is�o����܂Œ���)<BR>
     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B<BR>
     * �o����܂Œ����ł����true�A�ȊO��false�B<BR>
     * @@param l_modifiedWlimitExecCondType - (������iW�w�l�j���s����)<BR>
     * ������iW�w�l�j���s����<BR>
     * @@param l_strWlimitEnableStatusDiv - (�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪<BR>
     * @@return WEB3MarginCloseMarginChangeOrderSpec
     * @@roseuid 40C8504C0130
     */
    public static WEB3MarginChangeSettleContractOrderSpec createCloseMarginChangeOrderSpec(
        long l_lngOrderId,
        EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry,
        String l_strModifiedPriceConditionType,
        String l_strModifiedOrderConditionType,
        String l_strModifiedOrderCondOperator,
        double l_dblModifiedStopOrderPrice,
        double l_dblModifiedWLimitPrice,
        EqTypeExecutionConditionType l_modifiedExecutionCondition,
        Date l_datModifiedExpirationDate,
        boolean l_blnModifiedIsCarriedOrder,
        EqTypeExecutionConditionType l_modifiedWlimitExecCondType,
        String l_strWlimitEnableStatusDiv)
    {
        final String STR_METHOD_NAME = "createCloseMarginChangeOrderSpec(long, "
            + "EqTypeContractSettleChangeOrderUnitEntry, String, String, String, "
            + "double, double, EqTypeExecutionConditionType, Date, boolean, "
            + "EqTypeExecutionConditionType, String";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@this.create�M�p�ԍϒ����������e()��call����B
        WEB3MarginChangeSettleContractOrderSpec l_orderSpec =
            WEB3MarginChangeSettleContractOrderSpec.createCloseMarginChangeOrderSpec(
                l_lngOrderId,
                l_changeOrderUnitEntry,
                l_strModifiedPriceConditionType,
                l_strModifiedOrderConditionType,
                l_strModifiedOrderCondOperator,
                l_dblModifiedStopOrderPrice,
                l_dblModifiedWLimitPrice,
                l_modifiedExecutionCondition,
                l_datModifiedExpirationDate,
                l_blnModifiedIsCarriedOrder);

        //�Q�j�@@�g���v���p�e�B���Z�b�g����
        //�|������iW�w�l�j���s�����F�@@�i�������ҏW�j
        l_orderSpec.setModifiedWlimitExecCondType(l_modifiedWlimitExecCondType);

        //�|�iW�w�l�j�����L����ԁF�@@�i�������ҏW�j
        l_orderSpec.setWlimitEnableStatusDiv(l_strWlimitEnableStatusDiv);

        log.exiting(STR_METHOD_NAME);

        return l_orderSpec;
    }

    /**
     * (get���������ڍ�)<BR>
     * �igetChangeOrderUnitEntry�j<BR>
     * <BR>
     * ���������ڍׂ��擾����B<BR>
     * <BR>
     * getChangeOrderUnitEntries( )�ɂĒ��������ڍ׈ꗗ���擾�A<BR>
     * �߂�lList��0�Ԗڂ̗v�f��ԋp����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry
     * @@roseuid 40C9203F02ED
     */
    public EqTypeContractSettleChangeOrderUnitEntry getChangeOrderUnitEntry() 
    {
        return this.getChangeOrderUnitEntries()[0];
    }
    
    /**
     * (set�����㔭������)<BR>
     * �����㔭���������Z�b�g����B
     * @@param l_strModifiedOrderConditionType - �����㔭������
     * @@roseuid 40C56E56034C
     */
    public void setModifiedOrderConditionType(String l_strModifiedOrderConditionType) 
    {
        this.modifiedOrderConditionType = l_strModifiedOrderConditionType;
    }
    
    /**
     * (get�����㔭������)<BR>
     * �����㔭���������擾����B
     * @@return String
     * @@roseuid 40C56E56034B
     */
    public String getModifiedOrderConditionType() 
    {
        return this.modifiedOrderConditionType;
    }
    
    /**
     * (set�����㔭���������Z�q)<BR>
     * �����㔭���������Z�q���Z�b�g����B
     * @@param l_strModifiedOrderCondOperator - �����㔭���������Z�q
     * @@roseuid 40C56E56034E
     */
    public void setModifiedOrderCondOperator(String l_strModifiedOrderCondOperator) 
    {
        this.modifiedOrderCondOperator = l_strModifiedOrderCondOperator;
    }
    
    /**
     * (get�����㔭���������Z�q)<BR>
     * �����㔭���������Z�q���擾����B
     * @@return String
     * @@roseuid 40C56E560350
     */
    public String getModifiedOrderCondOperator() 
    {
        return this.modifiedOrderCondOperator;
    }
    
    /**
     * (set������t�w�l��l)<BR>
     * ������t�w�l��l���Z�b�g����B
     * @@param l_dblModifiedStopOrderPrice - ������t�w�l��l
     * @@roseuid 40C56E560351
     */
    public void setModifiedStopOrderPrice(double l_dblModifiedStopOrderPrice) 
    {
        this.modifiedStopOrderPrice = l_dblModifiedStopOrderPrice;
    }
    
    /**
     * (get������t�w�l��l)<BR>
     * ������t�w�l��l���擾����B
     * @@return double
     * @@roseuid 40C56E560353
     */
    public double getModifiedStopOrderPrice() 
    {
        return this.modifiedStopOrderPrice;
    }
    
    /**
     * (set������iW�w�l�j�����w�l)<BR>
     * ������(W�w�l)�����w�l���Z�b�g����B
     * @@param l_dblModifiedWLimitPrice - ������(W�w�l)�����w�l
     * @@roseuid 40C56E560354
     */
    public void setModifiedWLimitPrice(double l_dblModifiedWLimitPrice) 
    {
        this.modifiedWLimitPrice = l_dblModifiedWLimitPrice;
    }
    
    /**
     * (get������iW�w�l�j�����w�l)<BR>
     * ������(W�w�l)�����w�l���擾����B
     * @@return double
     * @@roseuid 40C56E56035A
     */
    public double getModifiedWLimitPrice() 
    {
        return this.modifiedWLimitPrice;
    }
    
    /**
     * (set�����㎷�s����)<BR>
     * �����㎷�s�������Z�b�g����B<BR>
     * @@param l_modifiedExecutionCondition - (�����㎷�s����)<BR>
     * ������̎��s����
     * @@roseuid 40C5706E007C
     */
    public void setModifiedExecutionCondition(EqTypeExecutionConditionType l_modifiedExecutionCondition) 
    {
        this.modifiedExecutionCondition = l_modifiedExecutionCondition;
    }
    
    /**
     * (get�����㎷�s����)<BR>
     * �����㎷�s�������擾����B<BR>
     * <BR>
     * �i�����P�ʃe�[�u��.���s�����j<BR>
     * @@return EqTypeExecutionConditionType
     * @@roseuid 40C57082004D
     */
    public EqTypeExecutionConditionType getModifiedExecutionCondition() 
    {
        return this.modifiedExecutionCondition;
    }
    
    /**
     * (set�����㒍��������)<BR>
     * �����㒍�����������Z�b�g����B
     * @@param l_datModifiedExpirationDate - �����㒍��������
     * @@roseuid 40C5709801A5
     */
    public void setModifiedExpirationDate(Date l_datModifiedExpirationDate) 
    {
        this.modifiedExpirationDate = l_datModifiedExpirationDate;
    }
    
    /**
     * (get�����㒍��������)<BR>
     * �����㒍�����������擾����B<BR>
     * <BR>
     * �i�����P�ʃe�[�u��.�����������t�j<BR>
     * @@return Date
     * @@roseuid 40C570AA01D4
     */
    public Date getModifiedExpirationDate() 
    {
        return this.modifiedExpirationDate;
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
     * (set������is�o����܂Œ���)<BR>
     * ������is�o����܂Œ������Z�b�g����B
     * @@param l_blnModifiedIsCarriedOrder - (������is�o����܂Œ���)<BR>
     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B<BR>
     * �o����܂Œ����ł����true�A�ȊO��false�B<BR>
     * @@roseuid 40C714480054
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
     * @@roseuid 40C56E560361
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
     * �Q�j�@@this.get������iW�w�l�j�����w�l( ) == 0 �̏ꍇ�́Afalse��Ԃ��B<BR>
     * �ȊO�Atrue��Ԃ��B<BR>
     * @@return boolean
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40C56E560389
     */
    public boolean isModifiedWLimitPrice()
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "isWLimitPrice()";
        log.entering(STR_METHOD_NAME);
         
        //�P�j�@@this.get�����㔭������( ) != �hW�w�l�h�̏ꍇ�́Afalse��Ԃ��B 
        if( !(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.getModifiedOrderConditionType())))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00726, STR_METHOD_NAME);
        }
        
        boolean l_result = true;
        //�Q�j�@@this.get������iW�w�l�j�����w�l( ) == 0 �̏ꍇ�́Afalse��Ԃ��B�ȊO�Atrue��Ԃ�
        if(String.valueOf(this.getModifiedWLimitPrice()) != null)
        {
            if(this.getModifiedWLimitPrice() == 0)
            {
                l_result = false;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
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

}
@
