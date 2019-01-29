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
filename	WEB3EquityNewCashBasedOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������e(WEB3EquityNewCashBasedOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 ���j (���u) �V�K�쐬
                 : 2004/12/28 ���� (SRA) �p�����[�^�̕ϐ������C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2006/11/02 ��іQ (���u) ���f�� No.988
Revesion History : 2007/12/17 ��іQ (���u) ���f�� No.1216,1251
Revesion History : 2008/01/08 ��іQ (���u) ���f�� No.1280
*/
package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

/**
 * �i�����������e�j�B<BR>
 * <BR>
 * �������e�̓��͂�\������B <BR>
 * �����}�l�[�W���ɓn���p�����^�ɂȂ�B<BR>
 * <BR>
 * xTrade��EqTypeNewCashBasedOrderSpec���g�������N���X�B
 * @@version 1.0
 */
public class WEB3EquityNewCashBasedOrderSpec
    extends EqTypeNewCashBasedOrderSpec
{

    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityNewCashBasedOrderSpec.class);

    /**
     * (�،���ЃR�[�h)
     */
    private String institutionCode;

    /**
     * (�����`���l��)
     */
    private String orderChanel;

    /**
     * (������)�B <BR>
     * �s��ɔ������鏈�����t�B <BR>
     * �i����J�����_���擾����j <BR>
     */
    private Timestamp orderBizDate;

    /**
     * (�萔�����i�R�[�h) <BR>
     * <BR>
     * �i��Е��X���i�e�[�u��.�萔�����i�R�[�h�j <BR>
     */
    private String commissionProductCode = null;

    /**
     * (�����P���B)
     * �i�����P��.�����P���ɃZ�b�g�j
     */
    private double orderUnitPrice = 0.0;

    /**
     * (�T�Z��n���) <BR>
     * �i�����R���ɂČv�Z����j <BR>
     */
    private double estimate_delivery_amount = 0.0;

    /**
     * (�萔��) <BR>
     * �萔���I�u�W�F�N�g <BR>
     * �icreate�萔��( )�ɂĐ�������j <BR>
     */
    private WEB3GentradeCommission equityCommission;

    /**
     * (���񒍕��̒����P��ID) <BR>
     * ���񒍕��̒����P��ID�B <BR>
     */
    private Long firstOrderUnitId;

    /**
     * (�l�i����)<BR>
     * �i0�F�@@DEFAULT(�����w��Ȃ�)<BR>
     * �@@1�F�@@���ݒl�w�l����<BR>
     * �@@3�F�@@�D��w�l����<BR>
     * �@@5�F�@@���s�c���w�l����<BR>
     * �@@7�F�@@���s�c����������j<BR>
     */
    private String priceConditionType;

    /**
     * (��������)�B <BR>
     * �i0�FDEFAULT�i�����w��Ȃ��j�@@1�F�t�w�l�@@2�FW�w�l�j <BR>
     */
    private String orderCond;

    /**
     * (�����������Z�q)�B <BR>
     * �i0�FEqual�i��l�Ɠ����l�ɂȂ�����j�@@ <BR>
     * 1�F��l�ȏ� <BR>
     * 2�F��l�ȉ��j <BR>
     */
    private String orderCondOperator;

    /**
     * (�t�w�l��l)�B <BR>
     * �i�t�w�l�AW�w�l�̏ꍇ�̂݃Z�b�g�j <BR>
     */
    private double stopLimitPriceBasePrice;

    /**
     * (�iW�w�l�j�����w�l�B) <BR>
     * �iW�w�l�̏ꍇ�̂݃Z�b�g�j <BR>
     */
    private double wLimitPriceChange;

    /**
     * (�i�v�w�l�j���s����)<BR>
     * �i�v�w�l�j���s����<BR>
     */
    private EqTypeExecutionConditionType wlimitExecCondType;

    /**
     * (�R���X�g���N�^�B)<BR>
     *<BR> 
     * @@param l_trader
     * @@param l_isBuyOrder
     * @@param l_strProductCode
     * @@param l_strMarketCode
     * @@param l_dbQuantity
     * @@param l_dbPrice
     * @@param l_execType
     * @@param l_datOrderExpDate
     * @@param l_taxType
     * @@roseuid 40236D290127
     */
    private WEB3EquityNewCashBasedOrderSpec(
        Trader l_trader,
        boolean l_isBuyOrder,
        String l_strProductCode,
        String l_strMarketCode,
        double l_dblQuantity,
        double l_dblPrice,
        EqTypeExecutionConditionType l_execType,
        Date l_datOrderExpDate,
        TaxTypeEnum l_taxType)
    {
        super(
            l_trader,
            l_isBuyOrder,
            l_strProductCode,
            l_strMarketCode,
            l_dblQuantity,
            l_dblPrice,
            l_execType,
            l_datOrderExpDate,
            l_taxType);
    }

    /**
     * (get�،���ЃR�[�h) <BR>
     * �،���ЃR�[�h���擾����B <BR>
     * @@return String
     * @@roseuid 3FFB7A53032B
     */
    public String getInstitutionCode()
    {
        return this.institutionCode;
    }

    /**
     * (set�،���ЃR�[�h) <BR>
     * �،���ЃR�[�h��ݒ肷��B <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@roseuid 3FFB7A59037A
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        this.institutionCode = l_strInstitutionCode;
    }

    /**
     * (set������) <BR>
     * ���������Z�b�g����B <BR>
     * <BR>
     * @@param l_tsOrderBizDate - ������
     * @@roseuid 400665430102
     */
    public void setOrderBizDate(Timestamp l_tsOrderBizDate)
    {
        this.orderBizDate = l_tsOrderBizDate;
    }

    /**
     * (get������) <BR>
     * ���������擾����B <BR>
     * @@return Timestamp
     * @@roseuid 4018CE4C0010
     */
    public Timestamp getOrderBizDate()
    {
        return this.orderBizDate;
    }

    /**
     * (set�T�Z��n���) <BR>
     * �T�Z��n������Z�b�g����B <BR>
     * @@param l_dblEstimatedPrice - �T�Z���z <BR>
     * @@roseuid 400E729203DE
     */
    public void setEstimateDeliveryAmount(double l_dblEstimatedPrice)
    {
        this.estimate_delivery_amount = l_dblEstimatedPrice;
    }

    /**
     * (get�T�Z��n���) <BR>
     * �T�Z��n������擾����B <BR>
     * @@return double
     * @@roseuid 400E729901DA
     */
    public double getEstimateDeliveryAmount()
    {
        return estimate_delivery_amount;
    }

    /**
     * (set�����P��) <BR>
     * �����P�����Z�b�g����B <BR>
     * @@param l_dblOrderUnitPrice - �����P��
     * @@roseuid 400E72C902B5
     */
    public void setOrderUnitPrice(double l_dblOrderUnitPrice)
    {
        this.orderUnitPrice = l_dblOrderUnitPrice;
    }

    /**
     * (get�����P��) <BR>
     * �����P�����擾����B <BR>
     * @@return double
     * @@roseuid 400E72CF00D0
     */
    public double getOrderUnitPrice()
    {
        return orderUnitPrice;
    }

    /**
     * (set�����`���l��) <BR>
     * �����`���l�����Z�b�g����B <BR>
     * <BR>
     * @@param l_strOrderChannel - (�����`���l��) <BR>
     * �����`���l�����Z�b�g����B <BR>
     * @@roseuid 400FBB770167
     */
    public void setOrderChannel(String l_strOrderChannel)
    {
        this.orderChanel = l_strOrderChannel;
    }

    /**
     * (get�����`���l��) <BR>
     * �����`���l�����擾����B <BR>
     * <BR>
     * @@return String
     * @@roseuid 400FBB9702CE
     */
    public String getOrderChannel()
    {
        return orderChanel;
    }

    /**
     * (create�������e) <BR>
     * �������e�𐶐�����B<BR>
     * �icreateOrderSpec�j<BR>
     * <BR>
     * �������e�C���X�^���X�𐶐����A�v���p�e�B�ɒl���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g<BR>
     * �@@�|������ԊǗ�.get������( )�Ŏ擾�������t�𔭒����ɃZ�b�g����B<BR>
     * �@@�|�،���ЃR�[�h���C���X�^���X�ɃZ�b�g����B<BR>
     * �@@�|���O�C���`���l�����C���X�^���X�ɃZ�b�g����B<BR>
     * �@@�|�l�i�������C���X�^���X�ɃZ�b�g����B<BR>
     * �@@�|�����������C���X�^���X�ɃZ�b�g����B<BR>
     * �@@�|�����������Z�q���C���X�^���X�ɃZ�b�g����B<BR>
     * �@@�|�t�w�l��l���C���X�^���X�ɃZ�b�g����B<BR>
     * �@@�|�iW�w�l�j�����w�l���C���X�^���X�ɃZ�b�g����B<BR>
     * �@@�|���񒍕��̒����P��ID���C���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@param l_trader - (����) <BR>
     * �㗝���͎��{�g���[�_�B <BR>
     * �㗝���͂łȂ��ꍇ�Anull���w��B <BR>
     * @@param l_strMarketCode - (�s��R�[�h) <BR>
     * ���͎s��R�[�h <BR>
     * @@param l_strProductCode - (�����R�[�h) <BR>
     * ���͖����R�[�h <BR>
     * @@param l_dblOrderQuantity - (����) <BR>
     * ���͊��� <BR>
     * @@param l_dblLimitPrice - (�w�l) <BR>
     * �w�l�̏ꍇ�̂ݎw��B <BR>
     * �w�l�ȊO�̏ꍇ��null���w��B <BR>
     * @@param l_eqTypeExecutionconditionType - (���s����) <BR>
     * 1:�����Ȃ�,�@@2:���,�@@3:����,�@@6:�s�o���������s <BR>
     * �iEqTypeExecutionConditionType�ɂĒ�`�j <BR>
     * @@param l_taxTypeEnum - (�ŋ敪) <BR>
     * 0:���̑�,�@@1:���,�@@2:����,�@@3:������������򒥎� <BR>
     * �iTaxTypeEnum�ɂĒ�`�j <BR>
     * @@param l_tsOrderLaspeDate - (����������)<BR>
     * �����������B<BR>
     * @@param l_isSellOrder - (is������) <BR>
     * �������̏ꍇ��true�A <BR>
     * �������̏ꍇ��false���w�肷��B <BR>
     * @@param l_strOrderChannel - (�����`���l��)<BR>
     * @@param l_strPriceConditionType - (�l�i����)<BR>
     * @@param l_strOrderCond - (��������)<BR>
     * @@param l_strOrderCondOperator - (�����������Z�q)<BR>
     * @@param l_dblStopOrderBasePrice - (�t�w�l��l)<BR>
     * @@param l_dblWLimitOrderChange - (�iW�w�l�j�����w�l)<BR>
     * @@param l_lngFirstOrderUnitId - (���񒍕��̒����P��ID) <BR>
     * �����J�z�ł̒����쐬���ɂ́A�J�z�Ώۂ̒����P��.���񒍕��̒����P��ID ���Z�b�g�B<BR>
     * ��L�ȊO�̐V�K�����o�^���ɂ́Anull���Z�b�g�B<BR>
     * @@return WEB3EquityNewCashBasedOrderSpec
     * @@roseuid 4143DDE10363
     */
    public static WEB3EquityNewCashBasedOrderSpec createOrderSpec(
        String l_strInstitutionCode,
        WEB3GentradeTrader l_trader,
        String l_strMarketCode,
        String l_strProductCode,
        double l_dblOrderQuantity,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_eqTypeExecutionconditionType,
        TaxTypeEnum l_taxTypeEnum,
        Timestamp l_tsOrderLaspeDate,
        boolean l_isSellOrder,
        String l_strOrderChannel,
        String l_strPriceConditionType,
        String l_strOrderCond,
        String l_strOrderCondOperator,
        double l_stopOrderBasePrice,
        double l_wLimitOrderChange,
        Long l_firstOrderUnitId)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
             "createOrderSpec(" 
             + "String, WEB3GentradeTrader, String, "
             + "String, double, double, EqTypeExecutionConditionType, "
             + "TaxTypeEnum, Timestamp, boolean, String, String," 
             + "String, double, double, long)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�w�l��null�i�w�l�����j�̏ꍇ�A
        //�p�����[�^.�w�l���w�肵�A�����������e�C���X�^���X�𐶐�����B
        //�ȊO�̏ꍇ�A�w�l�Ƃ���0���w�肵�A
        //�����������e�C���X�^���X�𐶐�����B
        WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec =
            new WEB3EquityNewCashBasedOrderSpec(
                (Trader) l_trader,
                !l_isSellOrder,
                l_strProductCode,
                l_strMarketCode,
                l_dblOrderQuantity,
                l_dblLimitPrice,
                l_eqTypeExecutionconditionType,
                (Date)l_tsOrderLaspeDate,
                l_taxTypeEnum);

        // �Q�j�@@�g�����ڃZ�b�g
        //������ԊǗ�.get������( )�Ŏ擾�������t�𔭒����ɃZ�b�g����
        Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        Timestamp l_tsBizDate = new Timestamp(l_bizDate.getTime());
        l_equityNewCashBasedOrderSpec.setOrderBizDate(l_tsBizDate); 
        //�،���ЃR�[�h���C���X�^���X�ɃZ�b�g����B   
        l_equityNewCashBasedOrderSpec.setInstitutionCode(
            l_strInstitutionCode);
        //���O�C���`���l�����C���X�^���X�ɃZ�b�g����
        l_equityNewCashBasedOrderSpec.setOrderChannel(
            l_strOrderChannel);
        //�l�i�������C���X�^���X�ɃZ�b�g����
        l_equityNewCashBasedOrderSpec.setPriceConditionType(
            l_strPriceConditionType);
        //�����������C���X�^���X�ɃZ�b�g����
        l_equityNewCashBasedOrderSpec.setOrderCond(
            l_strOrderCond);
        //�����������Z�q���C���X�^���X�ɃZ�b�g����
        l_equityNewCashBasedOrderSpec.setOrderCondOperator(
            l_strOrderCondOperator);
        //�t�w�l��l���C���X�^���X�ɃZ�b�g����
        l_equityNewCashBasedOrderSpec.setStopOrderBasePrice(
            l_stopOrderBasePrice);
        //�iW�w�l�j�����w�l���C���X�^���X�ɃZ�b�g����
        l_equityNewCashBasedOrderSpec.setWLimitPriceChange(
            l_wLimitOrderChange);
        //���񒍕��̒����P��ID���C���X�^���X�ɃZ�b�g����
        l_equityNewCashBasedOrderSpec.setFirstOrderUnitId(
            l_firstOrderUnitId);

        log.exiting(STR_METHOD_NAME);

        return l_equityNewCashBasedOrderSpec;
    }

    /**
     * (create�������e) <BR>
     * �������e�𐶐�����B<BR>
     * �icreateOrderSpec�j<BR>
     * <BR>
     * �P�j�@@�����������e.create�������e( )��delegate����B<BR>
     * <BR>
     * �@@�@@�@@�����ݒ�͈ȉ��̂悤�ɍs���B<BR>
     * <BR>
     * �@@�،���ЃR�[�h : �p�����[�^.������<BR>
     * �@@���� :�@@����, �s��R�[�h : �p�����[�^.������<BR>
     * �@@�����R�[�h : �p�����[�^.������<BR>
     * �@@���� : �p�����[�^.������<BR>
     * �@@�w�l : �p�����[�^.������<BR>
     * �@@���s���� : �p�����[�^.������<BR>
     * �@@�ŋ敪 : �p�����[�^.������<BR>
     * �@@���������� : �p�����[�^.������<BR>
     * �@@is������ : �p�����[�^.������<BR>
     * �@@�����`���l�� : �p�����[�^.������<BR>
     * �@@�l�i���� : �p�����[�^.������<BR>
     * �@@�������� : �p�����[�^.������<BR>
     * �@@�����������Z�q : �p�����[�^.������<BR>
     * �@@�t�w�l��l : �p�����[�^.������<BR>
     * �@@�iW�w�l�j�����w�l : �p�����[�^.������<BR>
     * �@@���񒍕��̒����P��ID : �p�����[�^.������<BR>
     * <BR>
     * <BR>
     * �Q�j�@@set�i�v�w�l�j���s����( )��call����B<BR>
     * <BR>
     * �@@�@@�@@�����ݒ�͈ȉ��̂悤�ɍs���B<BR>
     * <BR>
     * �@@�@@�@@�i�v�w�l�j���s�����F�p�����[�^.������<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@param l_trader - (����) <BR>
     * �㗝���͎��{�g���[�_�B <BR>
     * �㗝���͂łȂ��ꍇ�Anull���w��B <BR>
     * @@param l_strMarketCode - (�s��R�[�h) <BR>
     * ���͎s��R�[�h <BR>
     * @@param l_strProductCode - (�����R�[�h) <BR>
     * ���͖����R�[�h <BR>
     * @@param l_dblOrderQuantity - (����) <BR>
     * ���͊��� <BR>
     * @@param l_dblLimitPrice - (�w�l) <BR>
     * �w�l�̏ꍇ�̂ݎw��B�w�l�ȊO�̏ꍇ��0���w��B <BR>
     * @@param l_execCondType - (���s����) <BR>
     * 1:�����Ȃ�,�@@2:���,�@@3:����,�@@6:�s�o���������s <BR>
     * �iEqTypeExecutionConditionType�ɂĒ�`�j <BR>
     * @@param l_taxTypeEnum - (�ŋ敪) <BR>
     * 0:���̑�,�@@1:���,�@@2:����,�@@3:������������򒥎� <BR>
     * �iTaxTypeEnum�ɂĒ�`�j <BR>
     * @@param l_tsOrderLaspeDate - (����������)<BR>
     * �����������B<BR>
     * @@param l_blnIsSellOrder - (is������) <BR>
     * �������̏ꍇ��true�A�������̏ꍇ��false���w�肷��B <BR>
     * @@param l_strOrderChannel - (�����`���l��)<BR>
     * �����`���l��<BR>
     * @@param l_strPriceConditionType - (�l�i����)<BR>
     * �l�i����<BR>
     * @@param l_strOrderCond - (��������)<BR>
     * ��������<BR>
     * @@param l_strOrderCondOperator - (�����������Z�q)<BR>
     * �����������Z�q<BR>
     * @@param l_dblStopOrderBasePrice - (�t�w�l��l)<BR>
     * �t�w�l��l<BR>
     * @@param l_dblWLimitOrderChange - (�iW�w�l�j�����w�l)<BR>
     * �iW�w�l�j�����w�l<BR>
     * @@param l_lngFirstOrderUnitId - (���񒍕��̒����P��ID) <BR>
     * �����J�z�ł̒����쐬���ɂ́A�J�z�Ώۂ̒����P��.���񒍕��̒����P��ID ���Z�b�g�B<BR>
     * ��L�ȊO�̐V�K�����o�^���ɂ́Anull���Z�b�g�B<BR>
     * @@param l_wlimitExecCondType - (�i�v�w�l�j���s����)<BR>
     * 1:�����Ȃ�,�@@2:���,�@@3:����,�@@6:�s�o���������s <BR>
     * �iEqTypeExecutionConditionType�ɂĒ�`�j<BR>
     * @@return WEB3EquityNewCashBasedOrderSpec
     * @@throws WEB3SystemLayerException
     * @@roseuid 4143DDE10363
     */
    public static WEB3EquityNewCashBasedOrderSpec createOrderSpec(
        String l_strInstitutionCode,
        WEB3GentradeTrader l_trader,
        String l_strMarketCode,
        String l_strProductCode,
        double l_dblOrderQuantity,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_execCondType,
        TaxTypeEnum l_taxTypeEnum,
        Timestamp l_tsOrderLaspeDate,
        boolean l_blnIsSellOrder,
        String l_strOrderChannel,
        String l_strPriceConditionType,
        String l_strOrderCond,
        String l_strOrderCondOperator,
        double l_dblStopOrderBasePrice,
        double l_dblWLimitOrderChange,
        Long l_lngFirstOrderUnitId,
        EqTypeExecutionConditionType l_wlimitExecCondType)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "createOrderSpec(String, WEB3GentradeTrader, "
            + "String, String, double, double, EqTypeExecutionConditionType, TaxTypeEnum, "
            + "Timestamp,boolean, String, String, String, String, double, double, Long, "
            + "EqTypeExecutionConditionType";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����������e.create�������e( )��delegate����B
        WEB3EquityNewCashBasedOrderSpec l_orderSpec =
            WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                l_strInstitutionCode,
                l_trader,
                l_strMarketCode,
                l_strProductCode,
                l_dblOrderQuantity,
                l_dblLimitPrice,
                l_execCondType,
                l_taxTypeEnum,
                l_tsOrderLaspeDate,
                l_blnIsSellOrder,
                l_strOrderChannel,
                l_strPriceConditionType,
                l_strOrderCond,
                l_strOrderCondOperator,
                l_dblStopOrderBasePrice,
                l_dblWLimitOrderChange,
                l_lngFirstOrderUnitId);

        //�Q�j�@@set�i�v�w�l�j���s����( )��call����B
        //�����ݒ�͈ȉ��̂悤�ɍs���B
        //�i�v�w�l�j���s�����F�p�����[�^.������
        l_orderSpec.setWlimitExecCondType(l_wlimitExecCondType);

        log.exiting(STR_METHOD_NAME);

        return l_orderSpec;
    }

    /**
     * (set�萔�����i�R�[�h) <BR>
     * �萔�����i�R�[�h���Z�b�g����B <BR>
     * <BR>
     * @@param l_strCommissionProductCode - �萔�����i�R�[�h�B <BR>
     * �i��Е��X���i�e�[�u��.�萔�����i�R�[�h�j <BR>
     * @@roseuid 4018E64401A7
     */
    public void setCommissionProductCode(String l_strCommissionProductCode)
    {
        this.commissionProductCode = l_strCommissionProductCode;
    }

    /**
     * (get�萔�����i�R�[�h) <BR>
     * �萔�����i�R�[�h���擾����B <BR>
     * <BR>
     * �i��Е��X���i�e�[�u��.�萔�����i�R�[�h�j <BR>
     * @@return String
     * @@roseuid 4018E6860253
     */
    public String getCommissionProductCode()
    {
        return this.commissionProductCode;
    }

    /**
     * (create�萔��) <BR>
     * �T�Z��������v�Z�Ɏg�p����萔���I�u�W�F�N�g�𐶐�����B <BR>
     *  <BR>
     * �P�j�@@�萔���C���X�^���X�𐶐����A�v���p�e�B�Ɉȉ��̒ʂ�Z�b�g���s���B <BR>
     *  <BR>
     * �،���ЃR�[�h�F ���X.�،���ЃR�[�h <BR>
     * ���XID�F ���X.���XID <BR>
     * �萔�����i�R�[�h�F this.get�萔�����i�R�[�h( ) <BR>
     * ����R�[�h�iSONAR�j�F�@@�����̎���R�[�h�iSONAR�j <BR>
     * �������F�@@this.get������( ) <BR>
     * �ٍϋ敪�F�@@�ٍϋ敪.���̑� <BR>
     * �����`���l���F this.get�����`���l��( ) <BR>
     * is�w�l�F this.isLimitOrder( ) <BR>
     *  <BR>
     * (*) �ȊO�̍��ڂ́A�v�Z�T�[�r�X�ɂăZ�b�g����B <BR>
     *  <BR>
     * �Q�j�@@���g�̃v���p�e�B�ɐݒ肷��B <BR>
     *  <BR>
     * this.set�萔��( ) <BR>
     * <BR>
     * @@param l_branch
     * @@param l_sonarTradedCode
     * @@return WEB3BusinessLayerException
     * @@roseuid 4143DDE201D4
     */
    public WEB3GentradeCommission createCommission(
        Branch l_branch,
        String l_sonarTradedCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCommission(Branch,String)";

        log.entering(STR_METHOD_NAME);

        // �P�j�@@�萔���C���X�^���X�𐶐����A�v���p�e�B�Ɉȉ��̒ʂ�Z�b�g���s���B
        WEB3GentradeCommission l_gentradeCommission =
            new WEB3GentradeCommission();
        // �،���ЃR�[�h
        l_gentradeCommission.setInstitutionCode(
            l_branch.getInstitution().getInstitutionCode());
        // ���XID
        l_gentradeCommission.setBranchId(l_branch.getBranchId());
        // �萔�����i�R�[�h
        l_gentradeCommission.setCommissionProductCode(
            this.getCommissionProductCode());
        // ����R�[�h�iSONAR�j
        l_gentradeCommission.setSonarTradedCode(l_sonarTradedCode);
        // ������
        l_gentradeCommission.setOrderBizDate(this.getOrderBizDate());
        // �ٍϋ敪
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
        // �����`���l��
        l_gentradeCommission.setOrderChannel(this.getOrderChannel());
        // is�w�l�F this.isLimitOrder( ) 
        l_gentradeCommission.setIsLimitPrice(this.isLimitOrder( ));
		//�s��R�[�h�iSONAR�j
		if (this.getMarketCode() != null)
		{
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
			WEB3GentradeFinObjectManager l_finObjectManager =
				(WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
			try
			{
				Market l_market =
					l_finObjectManager.getMarket(this.getInstitutionCode(), this.getMarketCode());
				MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
				l_gentradeCommission.setSonarMarketCode(l_marketRow.getSonarMarketCode());
			}
			catch (NotFoundException l_nfe)
			{
				throw new WEB3SystemLayerException(
				   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				   this.getClass().getName() + "." + STR_METHOD_NAME,
				   l_nfe.getMessage(),
				   l_nfe);
			}
		} 
        
        // �Q�j�@@���g�̃v���p�e�B�ɐݒ肷��B
        this.setCommission(l_gentradeCommission);

        log.exiting(STR_METHOD_NAME);

        return l_gentradeCommission;
    }

    /**
     * (get�萔��) <BR>
     * �萔���I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * @@return WEB3EquityCommission
     * @@roseuid 4019EDAB006E
     */
    public WEB3GentradeCommission getCommission()
    {
        return this.equityCommission;
    }

    /**
     * (set�萔��) <BR>
     * �萔���I�u�W�F�N�g���Z�b�g����B <BR>
     * <BR>
     * @@param l_commission - (�萔��) <BR>
     * �萔���I�u�W�F�N�g <BR>
     * @@roseuid 403D5196039D
     */
    public void setCommission(WEB3GentradeCommission l_commission)
    {
        this.equityCommission = l_commission;
    }

    /**
     * (set���񒍕��̒����P��ID) <BR>
     * ���񒍕��̒����P��ID���Z�b�g����B <BR>
     * <BR>
     * @@param l_lngfirstOrderUnitId - ���񒍕��̒����P��ID�B <BR>
     * �i�����P�ʃe�[�u��.���񒍕��̒����P��ID�j <BR>
     * @@roseuid 405ED11502FE
     */
    public void setFirstOrderUnitId(Long l_lngfirstOrderUnitId)
    {
        this.firstOrderUnitId = l_lngfirstOrderUnitId;
    }

    /**
     * (get���񒍕��̒����P��ID) <BR>
     * ���񒍕��̒����P��ID���擾����B <BR>
     * <BR>
     * �i�����P�ʃe�[�u��.���񒍕��̒����P��ID�j <BR>
     * @@return Long
     * @@roseuid 405ED115030E
     */
    public Long getFirstOrderUnitId()
    {
        return firstOrderUnitId;
    }

    /**
     * (set��������) <BR>
     * �����������Z�b�g����B <BR>
     * @@param l_strOrderCond - �������� <BR>
     * @@roseuid 4076363E0166
     */
    public void setOrderCond(String l_strOrderCond)
    {
        this.orderCond = l_strOrderCond;
    }

    /**
     * (set�����������Z�q) <BR>
     * �����������Z�q���Z�b�g����B
     * @@param l_strOrderCondOperator �����������Z�q <BR>
     * @@roseuid 4076287C032B
     */
    public void setOrderCondOperator(String l_strOrderCondOperator)
    {
        this.orderCondOperator = l_strOrderCondOperator;
    }

    /**
     * (set�t�w�l��l) <BR>
     * �t�w�l��l���Z�b�g����B
     * @@param l_dblStopOrderBasePrice �t�w�l��l
     * @@roseuid 4076287C0389
     */
    public void setStopOrderBasePrice(double l_stopOrderBasePrice)
    {
        this.stopLimitPriceBasePrice = l_stopOrderBasePrice;
    }

    /**
     * (set�iW�w�l�j�����w�l) <BR>
     * �iW�w�l�j�����w�l���Z�b�g����B
     * @@param l_dblWLimitPriceChange �iW�w�l�j�����w�l
     * @@roseuid 4076287C03D7
     */
    public void setWLimitPriceChange(double l_dblWLimitPriceChange)
    {
        this.wLimitPriceChange = l_dblWLimitPriceChange;
    }

    /**
     * (get��������) <BR>
     * �����������擾����B
     * @@return String
     * @@roseuid 407636660231
     */
    public String getOrderCond()
    {
        return this.orderCond;
    }

    /**
     * (get�����������Z�q) <BR>
     * �����������Z�q���擾����B
     * @@return String
     * @@roseuid 407628D80389
     */
    public String getOrderCondOperator()
    {
        return this.orderCondOperator;
    }

    /**
     * (get�t�w�l��l) <BR>
     * �t�w�l��l���擾����B
     * @@return Double
     * @@roseuid 407628D803D7
     */
    public double getStopLimitPriceBasePrice()
    {
        return this.stopLimitPriceBasePrice;
    }

    /**
     * (get�iW�w�l�j�����w�l) <BR>
     * �iW�w�l�j�����w�l���擾����B 
     * @@return Double
     * @@roseuid 407628D9003D
     */
    public double getWLimitPriceChange()
    {
        return this.wLimitPriceChange;
    }

    /**
     * (is�o����܂Œ���) <BR>
     * �Y���������o����܂Œ����̏ꍇtrue <BR>
     * �����̂ݒ����̏ꍇfalse��Ԃ��B <BR>
     * <BR>
     * �P�j�@@this.���񒍕��̒����P��ID != null�̏ꍇ�́Atrue��Ԃ��B <BR>
     * �@@�@@�@@this.���񒍕��̒����P��ID == null�̏ꍇ�́Afalse��Ԃ��B 
     * @@return boolean
     * @@roseuid 4063B93202B8
     */
    public boolean isOrderUntilDeadLine()
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
     * (is�iW�w�l�j�����w�l) <BR>
     * �EW�w�l�����̏ꍇ�́u�iW�w�l�j�����w�l�v�� <BR>
     * �w�l�ł��邩�ǂ�����Ԃ��B <BR>
     * �@@�w�l�̏ꍇ��true���A���s�̏ꍇ��false��Ԃ��B <BR>
     * �EW�w�l�����łȂ��ꍇ�́A��O��throw����B <BR>
     * <BR>
     * �P�j�@@this.get��������( ) != �hW�w�l�h�̏ꍇ�́A <BR>
     *  ��O��throw����B <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag:   BUSINESS_ERROR_00157 <BR>
     * <BR>
     * �Q�j�@@this.get�iW�w�l�j�����w�l( ) == 0 �̏ꍇ�́A <BR>
     *  false��Ԃ��B�ȊO�Atrue��Ԃ��B
     * @@return boolean
     * @@throw WEB3BusinessLayerException
     * @@roseuid 40762A62029E
     */
    public boolean isWLimitPriceChange()
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "isWLimitPriceChange()";
        log.entering(STR_METHOD_NAME);
         
        //this.get��������( ) != �hW�w�l�h�̏ꍇ�́A��O��throw����B 
        if( !(this.getOrderCond().equals(WEB3OrderingConditionDef.W_LIMIT_PRICE)))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00157,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        boolean l_result = true;
        //this.get�iW�w�l�j�����w�l( ) == 0 �̏ꍇ�́Afalse��Ԃ��B�ȊO�Atrue��Ԃ�
        if(this.getWLimitPriceChange() == 0.0D)
        {
            l_result = false;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_result;

    }

    /**
     * (set�l�i����)<BR>
     * �l�i�������Z�b�g����B
     * @@param l_strPriceConditionType �l�i����
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

    /**
     * (createPTS�����������e)<BR>
     * �������e�𐶐�����B<BR>
     * <BR>
     * �������e�C���X�^���X�𐶐����A�v���p�e�B�ɒl���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * �@@�|�����������e�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g<BR>
     * �@@�|PTS������ԊǗ�.get������()�Ŏ擾�������t�𔭒����ɃZ�b�g����B<BR>
     * �@@�|�،���ЃR�[�h���C���X�^���X�ɃZ�b�g����B<BR>
     * �@@�|���O�C���`���l�����C���X�^���X�ɃZ�b�g����B<BR>
     * �@@�|�l�i�������C���X�^���X�ɃZ�b�g����B<BR>
     * �@@�|�����������C���X�^���X�ɃZ�b�g����B<BR>
     * �@@�|�����������Z�q���C���X�^���X�ɃZ�b�g����B<BR>
     * �@@�|�t�w�l��l���C���X�^���X�ɃZ�b�g����B<BR>
     * �@@�|�iW�w�l�j�����w�l���C���X�^���X�ɃZ�b�g����B<BR>
     * �@@�|���񒍕��̒����P��ID���C���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_trader - (����) <BR>
     * @@param l_strMarketCode - (�s��R�[�h) <BR>
     * @@param l_strProductCode - (�����R�[�h) <BR>
     * @@param l_dblOrderQuantity - (����) <BR>
     * @@param l_dblLimitPrice - (�w�l) <BR>
     * @@param l_execCondType - (���s����) <BR>
     * @@param l_taxTypeEnum - (�ŋ敪) <BR>
     * @@param l_tsOrderLaspeDate - (����������)<BR>
     * @@param l_blnIsSellOrder - (is������) <BR>
     * @@param l_strOrderChannel - (�����`���l��)<BR>
     * @@param l_strPriceConditionType - (�l�i����)<BR>
     * @@param l_strOrderCond - (��������)<BR>
     * @@param l_strOrderCondOperator - (�����������Z�q)<BR>
     * @@param l_dblStopOrderBasePrice - (�t�w�l��l)<BR>
     * @@param l_dblWLimitOrderChange - (�iW�w�l�j�����w�l)<BR>
     * @@param l_firstOrderUnitId - (���񒍕��̒����P��ID) <BR>
     * @@return WEB3EquityNewCashBasedOrderSpec
     * @@throws WEB3BaseException
     */
    public static WEB3EquityNewCashBasedOrderSpec createPTSEquityOrderSpec(
        String l_strInstitutionCode,
        WEB3GentradeTrader l_trader,
        String l_strMarketCode,
        String l_strProductCode,
        double l_dblOrderQuantity,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_execCondType,
        TaxTypeEnum l_taxTypeEnum,
        Timestamp l_tsOrderLaspeDate,
        boolean l_blnIsSellOrder,
        String l_strOrderChannel,
        String l_strPriceConditionType,
        String l_strOrderCond,
        String l_strOrderCondOperator,
        double l_dblStopOrderBasePrice,
        double l_dblWLimitOrderChange,
        Long l_firstOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOrderSpec(String, WEB3GentradeTrader, "
            + "String, String, double, double, EqTypeExecutionConditionType, TaxTypeEnum, "
            + "Timestamp,boolean, String, String, String, String, double, double, Long)";
        log.entering(STR_METHOD_NAME);

        //�����������e�C���X�^���X�𐶐�����B
        WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec =
            new WEB3EquityNewCashBasedOrderSpec(
                l_trader,
                !l_blnIsSellOrder,
                l_strProductCode,
                l_strMarketCode,
                l_dblOrderQuantity,
                l_dblLimitPrice,
                l_execCondType,
                l_tsOrderLaspeDate,
                l_taxTypeEnum);

        // �Q�j�@@�g�����ڃZ�b�g
        //PTS������ԊǗ�.get������()�Ŏ擾�������t�𔭒����ɃZ�b�g����B 
        l_equityNewCashBasedOrderSpec.setOrderBizDate(
            new Timestamp(WEB3EquityPTSTradingTimeManagement.getOrderBizDate().getTime()));

        //�،���ЃR�[�h���C���X�^���X�ɃZ�b�g����B   
        l_equityNewCashBasedOrderSpec.setInstitutionCode(
            l_strInstitutionCode);

        //���O�C���`���l�����C���X�^���X�ɃZ�b�g����
        l_equityNewCashBasedOrderSpec.setOrderChannel(
            l_strOrderChannel);

        //�l�i�������C���X�^���X�ɃZ�b�g����
        l_equityNewCashBasedOrderSpec.setPriceConditionType(
            l_strPriceConditionType);

        //�����������C���X�^���X�ɃZ�b�g����
        l_equityNewCashBasedOrderSpec.setOrderCond(
            l_strOrderCond);

        //�����������Z�q���C���X�^���X�ɃZ�b�g����
        l_equityNewCashBasedOrderSpec.setOrderCondOperator(
            l_strOrderCondOperator);

        //�t�w�l��l���C���X�^���X�ɃZ�b�g����
        l_equityNewCashBasedOrderSpec.setStopOrderBasePrice(
            l_dblStopOrderBasePrice);

        //�iW�w�l�j�����w�l���C���X�^���X�ɃZ�b�g����
        l_equityNewCashBasedOrderSpec.setWLimitPriceChange(
            l_dblWLimitOrderChange);

        //���񒍕��̒����P��ID���C���X�^���X�ɃZ�b�g����
        l_equityNewCashBasedOrderSpec.setFirstOrderUnitId(
            l_firstOrderUnitId);

        log.exiting(STR_METHOD_NAME);
        return l_equityNewCashBasedOrderSpec;
    }
}
@
