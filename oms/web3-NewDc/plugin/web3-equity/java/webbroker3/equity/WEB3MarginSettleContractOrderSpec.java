head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSettleContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�ԍϒ������e(WEB3MarginCloseMarginOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 ������ (���u) �V�K�쐬
Revesion History : 2004/12/09 �������F(SRA) �c�Č��Ή��ɂ��C��
Revesion History : 2004/12/09 �����a��(SRA) ���\�b�h�̕ϐ�����ύX
                 : 2006/11/01 ��іQ (���u) ���f�� No.1010. 1051
*/

package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�ԍϒ������e�j�B<BR>
 * <BR>
 * �M�p����E�ԍϒ������e�̓��͂�\������B<BR>
 * �����}�l�[�W���ɓn���p�����^�ɂȂ�B<BR>
 * <BR>
 * xTrade��EqTypeSettleContractOrderSpec���g�������N���X�B
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginSettleContractOrderSpec extends EqTypeSettleContractOrderSpec 
{
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityNewCashBasedOrderSpec.class);
    
    /**
     * �R���X�g���N�^�B<BR>
     * @@param l_trader - (����)<BR>
     * @@param l_closeMarginContractEntry - (���ό����G���g��)<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * @@param l_executionCondition - (���s����)<BR>
     * @@param l_datExpirationDate - (����������)<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     */
    protected WEB3MarginSettleContractOrderSpec(
        Trader l_trader,
        EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_executionCondition,
        Date l_datExpirationDate,
        TaxTypeEnum l_taxType)
    {
        super(
            l_trader,
            l_closeMarginContractEntry,
            l_dblLimitPrice,
            l_executionCondition,
            l_datExpirationDate,
            l_taxType);
    }

    /**
     * (�������B)<BR>
     * �s��ɔ������鏈�����t�B<BR>
     * �i����J�����_���擾����j<BR>
     */
    private Date bizDate;
    
    /**
     * (�l�i����)�B<BR>
     * �i0�F�@@DEFAULT(�����w��Ȃ�)<BR>
     * �@@1�F�@@���ݒl�w�l����<BR>
     * �@@3�F�@@�D��w�l����<BR>
     * �@@5�F�@@���s�c���w�l����<BR>
     * �@@7�F�@@���s�c����������j<BR>
     */
    private String priceConditionType;
    
    /**
     * (���������B)<BR>
     * �i0�FDEFAULT�i�����w��Ȃ��j�@@1�F�t�w�l�@@2�FW�w�l�j<BR>
     */
    private String orderConditionType;
    
    /**
     * (�����������Z�q�B)<BR>
     * �i0�FEqual�i��l�Ɠ����l�ɂȂ�����j�@@<BR>
     * 1�F��l�ȏ�@@<BR>
     * 2�F��l�ȉ��j<BR>
     */
    private String orderCondOperator;
    
    /**
     * (�t�w�l��l�B)<BR>
     * �i�t�w�l�AW�w�l�̏ꍇ�̂݃Z�b�g�j<BR>
     */
    private double stopOrderPrice;
    
    /**
     * (�iW�w�l�j�����w�l�B)<BR>
     * �iW�w�l�̏ꍇ�̂݃Z�b�g�j<BR>
     */
    private double wLimitPrice;
    
    /**
     * (���Ϗ����敪�B)<BR>
     * (0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������)<BR>
     * �ꊇ�ԍς̏ꍇ�Z�b�g�B<BR>
     */
    private String closingOrderType;
    
    /**
     * (���񒍕��̒����P��ID)<BR>
     */
    private Long firstOrderUnitId;

    /**
     * (�i�v�w�l�j���s����)<BR>
     * �i�v�w�l�j���s����<BR>
     */
    private EqTypeExecutionConditionType wlimitExecCondType;

    /**
     * (create�ԍϒ������e)<BR>
     * �istatic���\�b�h�j<BR>
     * �M�p�ԍϒ������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �葱���̓V�[�P���X�}�u�i�M�p�����jcreate�ԍϒ������e�v���Q�ƁB<BR>
     * @@param l_trader - (����)<BR>
     * ���҃I�u�W�F�N�g�B<BR>
     * @@param l_closeMarginContractEntry - (���ό����G���g��)<BR>
     * ���ό����G���g���̔z��B<BR>
     * @@param l_dblLimitPrice - �w�l�B<BR>
     * @@param l_executionCondition - ���s����<BR>
     * (0�F���̑��@@1�F�����Ȃ��@@2�F���@@3�F�����@@6�F�s�o���������s�j<BR>
     * @@param l_datExpirationDate - �����������B<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * 0:���̑�,�@@1:���,�@@2:����,�@@3:������������򒥎�<BR>
     * �iTaxTypeEnum�ɂĒ�`�j<BR>
     * @@param l_strPriceConditionType - (�l�i����)<BR>
     * �l�i�����B<BR>
     * @@param l_strOrderConditionType - ���������B<BR>
     * @@param l_strOrderCondOperator - �����������Z�q�B<BR>
     * @@param l_dblStopOrderPrice - �t�w�l��l�B<BR>
     * @@param l_dblWLimitPrice - (W�w�l)�����w�l�B<BR>
     * @@param l_strClosingOrderType - ���Ϗ����敪�B<BR>
     * (0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������)
     * @@param l_lngFirstOrderUnitId - (���񒍕��̒����P��ID)<BR>
     * �����J�z�ł̒����쐬���ɂ́A�J�z�Ώۂ̒����P��.�����P��ID���Z�b�g�B<BR>
     * ��L�ȊO�̐V�K�����o�^���ɂ́Anull���Z�b�g�B<BR>
     * @@return WEB3MarginCloseMarginOrderSpec
     * @@throws WEB3SystemLayerException
     * @@roseuid 40B2FDB30326
     */
    public static WEB3MarginSettleContractOrderSpec createCloseMarginOrderSpec(
        Trader l_trader, 
        EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry, 
        double l_dblLimitPrice, 
        EqTypeExecutionConditionType l_executionCondition, 
        Date l_datExpirationDate, 
        TaxTypeEnum l_taxType,
        String l_strPriceConditionType,
        String l_strOrderConditionType, 
        String l_strOrderCondOperator, 
        double l_dblStopOrderPrice, 
        double l_dblWLimitPrice, 
        String l_strClosingOrderType, 
        Long l_lngFirstOrderUnitId)
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME="createCloseMarginOrderSpec()";
        log.entering(STR_METHOD_NAME);
        
        //1 ������������null�̏ꍇ�A���������w�肷��
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        if(l_datExpirationDate == null)
        {
            l_datExpirationDate = l_datOrderBizDate;
            
        }
        
        //2 �ԍϒ������e�C���X�^���X�𐶐�����B
        WEB3MarginSettleContractOrderSpec l_orderSpec = 
            new WEB3MarginSettleContractOrderSpec(
                l_trader,
                l_closeMarginContractEntry,
                l_dblLimitPrice,
                l_executionCondition,
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
        l_orderSpec.setClosingOrderType(l_strClosingOrderType);
        
        //10 ���񒍕��̒����P��ID���Z�b�g
        l_orderSpec.setFirstOrderUnitId(l_lngFirstOrderUnitId);
        
        log.exiting(STR_METHOD_NAME);
        
        return  l_orderSpec;
    }

    /**
     * (create�ԍϒ������e)<BR>
     * �istatic���\�b�h�j<BR>
     * �M�p�ԍϒ������e�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �P�j�@@�M�p�ԍϒ������e.create�ԍϒ������e( )��delegate����B<BR>
     * <BR>
     * �@@�@@�@@�����ݒ�͈ȉ��̂悤�ɍs���B<BR>
     * <BR>
     * �@@���� :�@@�p�����[�^.������ <BR>
     * �@@���ό����G���g�� : �p�����[�^.������ <BR>
     * �@@�w�l : �p�����[�^.������ <BR>
     * �@@���s���� :�@@�p�����[�^.������ <BR>
     * �@@���������� : �p�����[�^.������ <BR>
     * �@@�ŋ敪 : �p�����[�^.������ <BR>
     * �@@�l�i���� : �p�����[�^.������ <BR>
     * �@@�������� : �p�����[�^.������ <BR>
     * �@@�����������Z�q : �p�����[�^.������ <BR>
     * �@@�t�w�l��l : �p�����[�^.������ <BR>
     * �@@(W�w�l)�����w�l : �p�����[�^.������ <BR>
     * �@@���Ϗ����敪 : �p�����[�^.������ <BR>
     * �@@���񒍕��̒����P��ID : �p�����[�^.������ <BR>
     * <BR>
     * <BR>
     * �Q�j�@@set�i�v�w�l�j���s����( )��call����B<BR>
     * <BR>
     * �@@�@@�@@�����ݒ�͈ȉ��̂悤�ɍs���B<BR>
     * <BR>
     * �@@�@@�@@�i�v�w�l�j���s�����F�p�����[�^.������<BR>
     * @@param l_trader - (����)<BR>
     * ���҃I�u�W�F�N�g�B<BR>
     * @@param l_closeMarginContractEntrys - (���ό����G���g��)<BR>
     * ���ό����G���g���̔z��B<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �w�l�B<BR>
     * @@param l_execCondType - (���s����)<BR>
     * ���s����<BR>
     * (0�F���̑��@@1�F�����Ȃ��@@2�F���@@3�F�����@@6�F�s�o���������s�j<BR>
     * @@param l_datExpirationDate - (����������)<BR>
     * �����������B<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * 0:���̑�,�@@1:���,�@@2:����,�@@3:������������򒥎�<BR>
     * �iTaxTypeEnum�ɂĒ�`�j<BR>
     * @@param l_strPriceConditionType - (�l�i����)<BR>
     * �l�i�����B<BR>
     * @@param l_strOrderConditionType - (��������)<BR>
     * ���������B<BR>
     * @@param l_strOrderCondOperator - (�����������Z�q)<BR>
     * �����������Z�q�B<BR>
     * @@param l_dblStopOrderPrice - (�t�w�l��l)<BR>
     * �t�w�l��l�B<BR>
     * @@param l_dblWLimitPrice - ((W�w�l)�����w�l)<BR>
     * (W�w�l)�����w�l�B<BR>
     * @@param l_strClosingOrderType - (���Ϗ����敪)<BR>
     * ���Ϗ����敪�B<BR>
     * (0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������)<BR>
     * @@param l_lngFirstOrderUnitId - (���񒍕��̒����P��ID)<BR>
     * �����J�z�ł̒����쐬���ɂ́A�J�z�Ώۂ̒����P��.�����P��ID���Z�b�g�B<BR>
     * ��L�ȊO�̐V�K�����o�^���ɂ́Anull���Z�b�g�B<BR>
     * @@param l_wlimitExecCondType - (�i�v�w�l�j���s����)<BR>
     * ���s����<BR>
     * (0�F���̑��@@1�F�����Ȃ��@@2�F���@@3�F�����@@6�F�s�o���������s�j<BR>
     * @@return WEB3MarginCloseMarginOrderSpec
     * @@throws WEB3SystemLayerException
     * @@roseuid 40B2FDB30326
     */
    public static WEB3MarginSettleContractOrderSpec createCloseMarginOrderSpec(
        Trader l_trader,
        EqTypeSettleContractOrderEntry[] l_closeMarginContractEntrys,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_execCondType,
        Date l_datExpirationDate,
        TaxTypeEnum l_taxType,
        String l_strPriceConditionType,
        String l_strOrderConditionType,
        String l_strOrderCondOperator,
        double l_dblStopOrderPrice,
        double l_dblWLimitPrice,
        String l_strClosingOrderType,
        Long l_lngFirstOrderUnitId,
        EqTypeExecutionConditionType l_wlimitExecCondType)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "createCloseMarginOrderSpec(Trader, "
            + "EqTypeSettleContractOrderEntry[], double, EqTypeExecutionConditionType, "
            + "Date, TaxTypeEnum, String, String, String, double, double, String, Long, "
            + "EqTypeExecutionConditionType";
        log.entering(STR_METHOD_NAME);

        //�M�p�V�K���������e�I�u�W�F�N�g�𐶐�
        //�M�p�ԍϒ������e.create�ԍϒ������e( )��delegate����B
        WEB3MarginSettleContractOrderSpec l_orderSpec =
            WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                l_trader,
                l_closeMarginContractEntrys,
                l_dblLimitPrice,
                l_execCondType,
                l_datExpirationDate,
                l_taxType,
                l_strPriceConditionType,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_dblStopOrderPrice,
                l_dblWLimitPrice,
                l_strClosingOrderType,
                l_lngFirstOrderUnitId);

        //�Q�jset�i�v�w�l�j���s����( )��call����B
        //�����ݒ�͈ȉ��̂悤�ɍs���B
        //�i�v�w�l�j���s�����F�@@������
        l_orderSpec.setWlimitExecCondType(l_wlimitExecCondType);

        log.exiting(STR_METHOD_NAME);

        return l_orderSpec;
    }

    /**
     * (set������)<BR>
     * ���������Z�b�g����B
     * @@param l_datBizDate - ������
     * @@roseuid 40B30A970182
     */
    public void setBizDate(Date l_datBizDate) 
    {
        this.bizDate = l_datBizDate;
    }
    
    /**
     * (get������)<BR>
     * ���������擾����B
     * @@return Date
     * @@roseuid 40B30A970190
     */
    public Date getBizDate() 
    {
        return this.bizDate;
    }
    
    /**
     * (set�l�i����)<BR>
     * �l�i�������Z�b�g����B
     * @@param l_strPriceConditionType - �l�i����
     */
    public void setPriceConditionType(String l_strPriceConditionType) 
    {
        this.priceConditionType = l_strPriceConditionType;
    }
    
    /**
     * (get�l�i����)<BR>
     * �l�i�������擾����B
     * @@return String
     */
    public String getPriceConditionType() 
    {
        return this.priceConditionType;
    }
    
    /**
     * (set��������)<BR>
     * �����������Z�b�g����B
     * @@param l_strOrderConditionType - ��������
     * @@roseuid 40B30A9701A1
     */
    public void setOrderConditionType(String l_strOrderConditionType) 
    {
        this.orderConditionType = l_strOrderConditionType;
    }
    
    /**
     * (get��������)<BR>
     * �����������擾����B
     * @@return String
     * @@roseuid 40B30A9701A0
     */
    public String getOrderConditionType() 
    {
        return this.orderConditionType;
    }
    
    /**
     * (set�����������Z�q)<BR>
     * �����������Z�q���Z�b�g����B
     * @@param l_strOrderCondOperator - �����������Z�q
     * @@roseuid 40B30A9701A3
     */
    public void setOrderCondOperator(String l_strOrderCondOperator) 
    {
        this.orderCondOperator = l_strOrderCondOperator;
    }
    
    /**
     * (get�����������Z�q)<BR>
     * �����������Z�q���擾����B
     * @@return String
     * @@roseuid 40B30A9701AF
     */
    public String getOrderCondOperator() 
    {
        return this.orderCondOperator;
    }
    
    /**
     * (set�t�w�l��l)<BR>
     * �t�w�l��l���Z�b�g����B
     * @@param l_dblStopOrderPrice - �t�w�l��l
     * @@roseuid 40B30A9701B0
     */
    public void setStopOrderPrice(double l_dblStopOrderPrice) 
    {
        this.stopOrderPrice = l_dblStopOrderPrice;
    }
    
    /**
     * (get�t�w�l��l)<BR>
     * �t�w�l��l���擾����B
     * @@return double
     * @@roseuid 40B30A9701B2
     */
    public double getStopOrderPrice() 
    {
        return this.stopOrderPrice;
    }
    
    /**
     * (set�iW�w�l�j�����w�l)<BR>
     * (W�w�l)�����w�l���Z�b�g����B
     * @@param l_dblWLimitPrice - (W�w�l)�����w�l
     * @@roseuid 40B30A9701BF
     */
    public void setWLimitPrice(double l_dblWLimitPrice) 
    {
        this.wLimitPrice = l_dblWLimitPrice;
    }
    
    /**
     * (get�iW�w�l�j�����w�l)<BR>
     * (W�w�l)�����w�l���擾����B
     * @@return double
     * @@roseuid 40B30A9701C1
     */
    public double getWLimitPrice() 
    {
        return this.wLimitPrice;
    }
    
    /**
     * (set���񒍕��̒����P��ID)<BR>
     * ���񒍕��̒����P��ID���Z�b�g����B
     * @@param l_lngFirstOrderUnitId - ���񒍕��̒����P��ID�B<BR>
     * �i�����P�ʃe�[�u��.���񒍕��̒����P��ID�j
     * @@roseuid 40B30A97020D
     */
    public void setFirstOrderUnitId(Long l_lngFirstOrderUnitId) 
    {
        this.firstOrderUnitId = l_lngFirstOrderUnitId;
    }
    
    /**
     * (get���񒍕��̒����P��ID)<BR>
     * ���񒍕��̒����P��ID���擾����B<BR>
     * <BR>
     * �i�����P�ʃe�[�u��.���񒍕��̒����P��ID�j<BR>
     * @@return Long
     * @@roseuid 40B30A97020F
     */
    public Long getFirstOrderUnitId() 
    {
        return this.firstOrderUnitId;
    }
    
    /**
     * (set���Ϗ����敪)<BR>
     * ���Ϗ����敪���Z�b�g����B
     * @@param l_strClosingOrderType - ���Ϗ����敪�B<BR>
     * (0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������)<BR>
     * �ꊇ�ԍς̏ꍇ�Z�b�g�B<BR>
     * @@roseuid 40B30A970210
     */
    public void setClosingOrderType(String l_strClosingOrderType) 
    {
        this.closingOrderType = l_strClosingOrderType;
    }
    
    /**
     * (get���Ϗ����敪)<BR>
     * ���Ϗ����敪���擾����B
     * @@return String
     * @@roseuid 40B30A97021D
     */
    public String getClosingOrderType() 
    {
        return this.closingOrderType;
    }
    
    /**
     * (is�o����܂Œ���)<BR>
     * �Y���������o����܂Œ����̏ꍇtrue<BR>
     * �����̂ݒ����̏ꍇfalse��Ԃ��B<BR>
     * <BR>
     * �P�j�@@this.���񒍕��̒����P��ID != null�̏ꍇ�́Atrue��Ԃ��B<BR>
     * �@@�@@�@@this.���񒍕��̒����P��ID == null�̏ꍇ�́Afalse��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 40B30A97021E
     */
    public boolean isCarriedOrder() 
    {
        if(this.firstOrderUnitId != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * (is�iW�w�l�j�����w�l)<BR>
     * �EW�w�l�����̏ꍇ�́u�iW�w�l�j�����w�l�v���w�l�ł��邩�ǂ�����Ԃ��B<BR>
     * �@@�w�l�̏ꍇ��true���A���s�̏ꍇ��false��Ԃ��B<BR>
     * �EW�w�l�����łȂ��ꍇ�́A��O��throw����B<BR>
     * <BR>
     * �P�j�@@this.get��������( ) != �hW�w�l�h�̏ꍇ�́A��O��throw����B<BR>
     * <BR>
     * �Q�j�@@this.get�iW�w�l�j�����w�l( ) == 0 �̏ꍇ�́Afalse��Ԃ��B�ȊO�Atrue��Ԃ��B<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40B30A97022C
     */
    public boolean isWLimitPrice()
        throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME = "isWLimitPrice()";
        log.entering(STR_METHOD_NAME);
         
        //�P�j�@@this.get��������( ) != �hW�w�l�h�̏ꍇ�́Afalse��Ԃ��B 
        if( !(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.getOrderConditionType())))
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
