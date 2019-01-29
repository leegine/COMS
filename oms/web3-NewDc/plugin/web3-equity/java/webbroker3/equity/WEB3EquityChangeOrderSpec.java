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
filename	WEB3EquityChangeOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������e(WEB3EquityChangeOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 ����� (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2006/11/01 ��іQ (���u) ���f�� No.995
Revesion History : 2007/12/18 ���n(���u) ���f��No.1242
Revesion History : 2007/12/27 ���n(���u) ���f��No.1275
*/
package webbroker3.equity;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������������e�j�B
 * @@version 1.0
 */
public class WEB3EquityChangeOrderSpec extends EqTypeChangeOrderSpec
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeOrderSpec.class);

    /**
     * �i�،���ЃR�[�h�j<BR>
     */
    private String institutionCode;

    /**
     * �i�����`���l���j<BR>
     */
    private String orderChannel;

    /**
     * �i���ҁj<BR>
     */
    private Trader trader;

    /**
     * (�����������e)<BR>
     */
    private WEB3EquityNewCashBasedOrderSpec newCachBasedOrderSpec;

    /**
     * (�R���X�g���N�^�B)<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * �@@�X�[�p�[�N���X�̃R���X�g���N�^�iEqTypeChangeOrderSpec�j���R�[������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@orderId�F�@@�i�������ҏW�j<BR>
     * �@@�������������l�ڍׁF�@@�i�������ҏW�j<BR>
     *    <BR>
     * �Q�j�@@�g���v���p�e�B���Z�b�g����<BR>
     * <BR>
     * �@@�|�،���ЃR�[�h�F�@@�i�������ҏW�j<BR>
     * �@@�|�����`���l���F�@@�i�������ҏW�j<BR>
     * �@@�|���ҁF�@@�i�������ҏW�j<BR>
     * @@param l_lngOrderId - (�����h�c)<BR>
     * @@param l_eqTypeChangeOrderUnitEntry - (���������������e)<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strOrderChannel - (�����`���l��)<BR>
     * @@param l_trader - (����)<BR>
     * @@roseuid 4043EE5E0394<BR>
     */
    public WEB3EquityChangeOrderSpec(
        long l_lngOrderId,
        EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry,
        String l_strInstitutionCode,
        String l_strOrderChannel,
        Trader l_trader)
    {
        //�X�[�p�[�N���X�̃R���X�g���N�^���R�[����
        super(l_lngOrderId, l_eqTypeChangeOrderUnitEntry);

        //�g���v���p�e�B���Z�b�g����
        setInstitutionCode(l_strInstitutionCode);
        setOrderChannel(l_strOrderChannel);
        setTrader(l_trader);
    }

    /**
     * (create�����������e)<BR>
     * <BR>
     * ���������������e���A�����������e�I�u�W�F�N�g���쐬���ĕԋp����B<BR>
     * <BR>
     * �P�j�@@�����C���X�^���X�`�F�b�N<BR>
     * �@@�|this.�����������e��null�łȂ��ꍇ�Athis.�����������e��ԋp���������I������B<BR>
     * <BR>
     * �Q�j�@@�����������P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�|this.get�����������P��( )�ɂāA�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �R�j�@@�C���X�^���X�����B<BR>
     * �@@�|�����������e�I�u�W�F�N�g.create�����������e( )�ɂĊ����������e�C���X�^���X�𐶐�����B<BR>
     * �@@�|this.�����������e�ɁA�������������������e�C���X�^���X���Z�b�g����B<BR>
     * �@@�|this.�����������e��ԋp����B<BR>
     * <BR>
     * [create�����������e�@@����]<BR>
     * �،���ЃR�[�h�F�@@this.�،���ЃR�[�h<BR>
     * ���ҁF�@@this.����<BR>
     * �s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��R�[�h<BR>
     * �����R�[�h�F�@@�����P��.����ID�ɊY����������R�[�h<BR>
     * �����F�@@this.get�����l�ڍ�( ).getAfterChangeOriginalQuantity( )<BR>
     * �w�l�F�@@this.get�����l�ڍ�( ).getAfterChangePrice( )<BR>
     * ���s�����F�@@this.get�����l�ڍ�( ).get�����㎷�s����( )<BR>
     * �ŋ敪�F�@@�����P��.�ŋ敪<BR>
     * �����������F�@@this.get�����l�ڍ�( ).get�����㒍��������( )<BR>
     * is�������F�@@�����P��.������ʂ�蔻�肷��B�i�h�����������h�̏ꍇ��false�A�h�����������h�̏ꍇ��true�j<BR>
     * �����`���l���F�@@�����P��.���񒍕��̒����`���l��<BR>
     * �l�i�����F�@@this.get�����l�ڍ�( ).get������l�i����( )<BR>
     * ���������F�@@this.get�����l�ڍ�( ).get�����㔭������( )<BR>
     * �����������Z�q�F�@@this.get�����l�ڍ�( ).get�����㔭���������Z�q( )<BR>
     * �t�w�l��l�F�@@this.get�����l�ڍ�( ).get������t�w�l��l( )<BR>
     * �iW�w�l�j�����w�l�F�@@this.get�����l�ڍ�( ).get������iW�w�l�j�����w�l( )<BR>
     * ���񒍕��̒����P��ID�F�@@�����P��.���񒍕��̒����P��ID<BR>
     * �iW�w�l�j���s�����F this.get�����l�ڍ�( ).get������iW�w�l�j���s����( ) <BR>
     * <BR>
     * �ȍ~�̏����菇�ɂ��ẮA�V�[�P���X�}�u�i�����j���������R���v����<BR>
     * ���������������e.create�����������e( )�������Q�ƁB<BR>
     * <BR>
     * @@return webbroker3.equity.WEB3EquityNewCashBasedOrderSpec<BR>
     * @@roseuid 4043E1B30087<BR>
     */
    public WEB3EquityNewCashBasedOrderSpec createOrderSpec() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOrderSpec() ";
        log.entering(STR_METHOD_NAME);

        //�����C���X�^���X�`�F�b�N
        if (this.newCachBasedOrderSpec != null)
        {
            log.exiting(STR_METHOD_NAME);
            return this.newCachBasedOrderSpec;
        }

        //�����������P�ʃI�u�W�F�N�g���擾����
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)this.getOrgChangeOrderUnit();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //�s��R�[�h���擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        String l_strMarketCode = null;
        try
        {
            l_strMarketCode =
                l_finApp.getFinObjectManager().getMarket(l_orderUnitRow.getMarketId()).getMarketCode();
        }
        catch (NotFoundException nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }

        //�����R�[�h���擾
        EqTypeProduct l_eqTypeProduct =
            (EqTypeProduct) l_orderUnit.getProduct();
        String l_strProductCode = l_eqTypeProduct.getProductCode();

        //�������擾
        WEB3EquityChangeOrderUnitEntry l_eqChangeOrderUnitEntry =
            (WEB3EquityChangeOrderUnitEntry)this.getChangeOrderUnitEntry();
        double l_dblQuantity = l_eqChangeOrderUnitEntry.getAfterChangeOriginalQuantity();

        //�P�����擾
        double l_dblPrice =
            l_eqChangeOrderUnitEntry.getAfterChangePrice();

        //���s�������擾
        EqTypeExecutionConditionType l_eqExecutionConditionType =
            l_eqChangeOrderUnitEntry.getAfterChangeExecutionConditionType();

        //�ŋ敪:�����P��.�ŋ敪
        TaxTypeEnum l_taxType = l_orderUnit.getTaxType();

        //�����������F�@@this.get�����l�ڍ�( ).get�����㒍��������
        Timestamp l_expirationDate = new Timestamp(l_eqChangeOrderUnitEntry.getModifiedExpirationDate().getTime());

        //is�������F�@@�����P��.������ʂ�蔻�肷��
        boolean l_isSellOrder = false;
        if (OrderTypeEnum.EQUITY_SELL.equals(l_orderUnit.getOrderType()))
        {
            l_isSellOrder = true;
        }

        //�����`���l���F�@@�����P��.���񒍕��̒����`���l��
        String l_strOrderChanel = l_orderUnitRow.getOrderChanel();

        //�l�i����
        String l_strPriceConditionType = l_eqChangeOrderUnitEntry.getChangeAfterPriceConditionType();
        
        //���������F�@@this.get�����l�ڍ�( ).get�����㔭������
        String l_strOrderCondType =
            ((WEB3EquityChangeOrderUnitEntry) l_eqChangeOrderUnitEntry).getChangeAfterOrderCondType();

        //�����������Z�q�F�@@this.get�����l�ڍ�( ).get�����㔭���������Z�q
        String l_strChangeAfterOrderCondOperator =
            l_eqChangeOrderUnitEntry.getChangeAfterOrderCondOperator();

        //�t�w�l��l�F�@@this.get�����l�ڍ�( ).get������t�w�l��l
        double l_dblChangeAfterStopOrderCondPriceBasePrice =
            l_eqChangeOrderUnitEntry.getChangeAfterStopOrderCondPriceBasePrice();

        //�iW�w�l�j�����w�l�F�@@this.get�����l�ڍ�( ).get������iW�w�l�j�����w�l
        double l_dblChangeAfterWlimitOrderCondPrice =
            l_eqChangeOrderUnitEntry.getChangeAfterWlimitOrderCondPrice();

        //���񒍕��̒����P��ID�F�@@�����P��.���񒍕��̒����P��ID
        Long l_beforeCarryoverOrderUnitId = null;
        if (l_orderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_beforeCarryoverOrderUnitId = null;
        }
        else
        {
            l_beforeCarryoverOrderUnitId =
                new Long(l_orderUnitRow.getFirstOrderUnitId());
        }

        //�iW�w�l�j���s�����F this.get�����l�ڍ�( ).get������iW�w�l�j���s����( )
        EqTypeExecutionConditionType l_wlimitExecCondType =
            l_eqChangeOrderUnitEntry.getModifiedWlimitExecCondType();

        this.newCachBasedOrderSpec =
            WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                this.getInstitutionCode(), //�،���ЃR�[�h
                (WEB3GentradeTrader)this.getTrader(), //����
                l_strMarketCode, //�s��R�[�h
                l_strProductCode, //�����R�[�h
                l_dblQuantity, //����
                l_dblPrice, //�P��
                l_eqExecutionConditionType, //���s����
                l_taxType, //�ŋ敪
                l_expirationDate, //����������
                l_isSellOrder, //is������
                l_strOrderChanel, //�����`���l��
                l_strPriceConditionType, //�l�i����
                l_strOrderCondType, //��������
                l_strChangeAfterOrderCondOperator, //�����������Z�q
                l_dblChangeAfterStopOrderCondPriceBasePrice, //�t�w�l��l
                l_dblChangeAfterWlimitOrderCondPrice, //�iW�w�l�j�����w�l
                l_beforeCarryoverOrderUnitId, //���񒍕��̒����P��ID
                l_wlimitExecCondType //�iW�w�l�j���s����
            );

        //���X���擾
        AccountManager l_accountManager = l_finApp.getAccountManager();
        Branch l_branch = null;        
        try
        {
            l_branch = l_accountManager.getBranch(l_orderUnit.getBranchId());
        }
        catch (NotFoundException nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DB�̃A�N�Z�X�Ɏ��s���܂���",
                nfe);
        }
        //set�萔�����i�R�[�h�i10�F��ꊔ���j
        newCachBasedOrderSpec.setCommissionProductCode(
            WEB3CommisionProductCodeDef.LISTING_STOCK);

        //�����������e::create�萔��
        newCachBasedOrderSpec.createCommission(
            l_branch,
            l_orderUnitRow.getSonarTradedCode());

        //�����������e::get�萔�� 
        WEB3GentradeCommission l_equityCommission =
            newCachBasedOrderSpec.getCommission();
        
        //set���������萔������(�萔��)
        this.setOriginalOrderCommCond(l_equityCommission);

        //set�萔��
        newCachBasedOrderSpec.setCommission(l_equityCommission);

        log.exiting(STR_METHOD_NAME);
        return this.newCachBasedOrderSpec;
    }

    /**
     * �iset�،���ЃR�[�h�j<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@roseuid 4043195C019B<BR>
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        this.institutionCode = l_strInstitutionCode;
    }

    /**
     * (get�،���ЃR�[�h)<BR>
     * @@return String<BR>
     * @@roseuid 4043195D03AE<BR>
     */
    public String getInstitutionCode()
    {
        return this.institutionCode;
    }

    /**
     * (set�����`���l��)<BR>
     * @@roseuid 4043195E039E<BR>
     */
    public void setOrderChannel(String l_strOrderChannel)
    {
        this.orderChannel = l_strOrderChannel;
    }

    /**
     * (get�����`���l��)<BR>
     * @@return String<BR>
     * @@roseuid 4043195F037F<BR>
     */
    public String getOrderChannel()
    {
        return this.orderChannel;
    }

    /**
     * (set����)<BR>
     * @@param l_trader - (����)<BR>
     * @@roseuid 40431961011E<BR>
     */
    public void setTrader(Trader l_trader)
    {
        this.trader = l_trader;
    }

    /**
     * (get����)<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Trader<BR>
     * @@roseuid 40431962015C<BR>
     */
    public Trader getTrader()
    {
        return this.trader;
    }

    /**
     * (get�����l�ڍ�)<BR>
     * <BR>
     * �ύX�����P�ʃG���g�����擾����B<BR>
     * <BR>
     * getChangeOrderEntries( )�ɂĒ������̓f�[�^���擾�A<BR>
     * �ԋp�lList��0�Ԗڂ̗v�f��<BR>
     * EqTypeChangeOrderUnitEntry�ɃL���X�g���ĕԋp����B<BR>
     * @@return <BR>
     * com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry<BR>
     * @@roseuid 40431B1600B0<BR>
     */
    public EqTypeChangeOrderUnitEntry getChangeOrderUnitEntry()
    {
        EqTypeChangeOrderUnitEntry[] l_eqChangeOrderUnitEntres =
            getChangeOrderUnitEntries();

        EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry =
            l_eqChangeOrderUnitEntres[0];
        return l_eqTypeChangeOrderUnitEntry;
    }

    /**
     * (get�����������P��)<BR>
     * <BR>
     * �����������̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �P�j�@@�����l�ڍ׃I�u�W�F�N�g�擾<BR>
     * this.get�����l�ڍ�()�ɂĊ������������l�ڍ׃I�u�W�F�N�g<BR>
     * ���擾����B<BR>
     * <BR>
     * �Q�j�@@�����P�ʃI�u�W�F�N�g�ԋp<BR>
     * �擾���������l�ڍ�.get�����P��()�̖߂�l��ԋp����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit<BR>
     * @@roseuid 40431C1E01F3<BR>
     */
    public OrderUnit getOrgChangeOrderUnit()
    {
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            (WEB3EquityChangeOrderUnitEntry) this.getChangeOrderUnitEntry();

        OrderUnit l_orderUnit = l_changeOrderUnitEntry.getOrderUnit();
        return l_orderUnit;
    }

    /**
     * (set���������萔������)<BR>
     * <BR>
     * �@@�����̎萔���I�u�W�F�N�g�ɁA�������������Z�b�g����B<BR>
     * <BR>
     * �@@�萔���������擾���A�������̎萔���v�Z��<BR>
     * �g�p���鍀�ڂ�ǉ��ŃZ�b�g����B<BR>
     * <BR>
     * �P�j�@@�����������P�ʂ̎擾<BR>
     * �@@get�����������P��()�Œ�������<BR>
     * �@@�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@���������̏������ڂ��Z�b�g����B<BR>
     * �@@�|�萔��.�����������`���l���ɁA�擾����<BR>
     * �@@�@@�����P�ʃI�u�W�F�N�g.���񒍕��̒����`���l�����Z�b�g����B<BR>
     * �@@�|�萔��.�������萔��No.�ɁA�擾����<BR>
     * �@@�@@�����P�ʃI�u�W�F�N�g.���񒍕��̎萔��No.���Z�b�g����B<BR>
     * �@@�|�萔��.�������萔��No.�}�ԂɁA�擾����<BR>
     * �@@�@@�����P�ʃI�u�W�F�N�g.���񒍕��̎萔��No.�}�Ԃ��Z�b�g����B<BR>
     * <BR>
     * @@see OrderUnit#getOrderActions()<BR>
     * @@param l_commission - (�萔��)<BR>
     * @@roseuid 404562EC0109<BR>
     */
    private void setOriginalOrderCommCond(WEB3GentradeCommission l_commission)
    {

        //�����P�ʂ̎擾
        EqTypeOrderUnit l_orderUnit =
            (EqTypeOrderUnit) this.getOrgChangeOrderUnit();

        EqtypeOrderUnitRow l_eqOrderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //�萔��.�����������`���l�����Z�b�g����
        l_commission.setOrgOrderChannel(l_eqOrderUnitRow.getOrderChanel());
        //�萔��.�������萔��No
        l_commission.setOrgCommissionNo(l_eqOrderUnitRow.getCommTblNo());
        //�萔��.�������萔��No.�}�Ԃ��Z�b�g����
        l_commission.setOrgCommissionRevNo(
            l_eqOrderUnitRow.getCommTblSubNo());

    }

    /**
     * (get�����������e)<BR>
     * �����������e�I�u�W�F�N�g���擾����B<BR>
     *<BR> 
     * @@return WEB3EquityNewCashBasedOrderSpec<BR>
     */
    public WEB3EquityNewCashBasedOrderSpec getNewCachBasedOrderSpec()
    {
        return this.newCachBasedOrderSpec;
    }
    
    /**
     * (createPTS�����������e)<BR>
     * <BR>
     * ���������������e���A�����������e�I�u�W�F�N�g���쐬���ĕԋp����B<BR> 
     * <BR>
     * �P�j�@@�����C���X�^���X�`�F�b�N <BR>
     * �@@�|this.�����������e��null�łȂ��ꍇ�Athis.�����������e��ԋp���������I������B <BR>
     * <BR>
     * �Q�j�@@�����������P�ʃI�u�W�F�N�g���擾����B <BR>
     * �@@�|this.get�����������P��( )�ɂāA�����P�ʃI�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �R�j�@@�C���X�^���X�����B <BR>
     * �@@�|�����������e�I�u�W�F�N�g.createPTS�����������e( )�ɂĊ����������e�C���X�^���X�𐶐�����B<BR> 
     * �@@�|this.�����������e�ɁA�������������������e�C���X�^���X���Z�b�g����B <BR>
     * �@@�|this.�����������e��ԋp����B <BR>
     * <BR>
     * [createPTS�����������e�̈���] <BR>
     * <BR>
     * �،���ЃR�[�h�F�@@this.�،���ЃR�[�h <BR>
     * ���ҁF�@@this.���� <BR>
     * �s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��R�[�h <BR>
     * �����R�[�h�F�@@�����P��.����ID�ɊY����������R�[�h <BR>
     * �����F�@@this.get�����l�ڍ�( ).getAfterChangeOriginalQuantity( ) <BR>
     * �w�l�F�@@this.get�����l�ڍ�( ).getAfterChangePrice( ) <BR>
     * ���s�����F�@@this.get�����l�ڍ�( ).get�����㎷�s����( ) <BR>
     * �ŋ敪�F�@@�����P��.�ŋ敪 <BR>
     * �����������F�@@this.get�����l�ڍ�( ).get�����㒍��������( ) <BR>
     * is�������F�@@�����P��.������ʂ�蔻�肷��B�i�h�����������h�̏ꍇ��false�A�h�����������h�̏ꍇ��true�j <BR>
     * �����`���l���F�@@�����P��.���񒍕��̒����`���l�� <BR>
     * �l�i�����F�@@this.get�����l�ڍ�( ).get������l�i����( ) <BR>
     * ���������F�@@this.get�����l�ڍ�( ).get�����㔭������( ) <BR>
     * �����������Z�q�F�@@this.get�����l�ڍ�( ).get�����㔭���������Z�q( ) <BR>
     * �t�w�l��l�F�@@this.get�����l�ڍ�( ).get������t�w�l��l( )  <BR>
     * �iW�w�l�j�����w�l�F�@@this.get�����l�ڍ�( ).get������iW�w�l�j�����w�l( ) <BR>
     * ���񒍕��̒����P��ID�F�@@�����P��.���񒍕��̒����P��ID<BR>
     * <BR>
     * �S�j�@@�萔�������쐬���� <BR>
     * <BR>
     * �@@�S�|�P�j�@@this.�����������e.set�萔�����i�R�[�h( )���R�[������B<BR>
     * �@@[set�萔�����i�R�[�h( )�̈���] <BR>
     * �@@�@@�萔�����i�R�[�h�F�@@"��ꊔ��"�i�Œ�j<BR>
     * <BR>
     * �@@�S�|�Q�j�@@this.�����������e.create�萔��( )���R�[������B<BR>
     * �@@[create�萔��( )�̈���] <BR>
     * �@@�@@���X�F�@@�擾�����������̒����P�ʃI�u�W�F�N�g.���XID�ɊY�����镔�X�I�u�W�F�N�g<BR>
     * �@@�@@����R�[�h�iSONAR�j : �擾�����������̒����P�ʃI�u�W�F�N�g.����R�[�h�iSONAR�j<BR>
     * <BR>
     * �@@�S�|�R�j�@@this.set���������萔������( )���R�[������B<BR>
     * �@@[set���������萔������( )�̈���]<BR>
     * �@@�@@�萔���F�@@this.�����������e.get�萔��( )�̖߂�l�I�u�W�F�N�g<BR>
     * <BR>
     * �@@�S�|�S�j�@@this.�����������e.set�萔��( )���R�[������B<BR>
     * �@@[set�萔��( )�̈���]<BR>
     * �@@�@@�萔���F�@@this.�����������e.get�萔��( )�̖߂�l�I�u�W�F�N�g<BR>
     * <BR>
     * @@return WEB3EquityNewCashBasedOrderSpec
     * @@throws WEB3BaseException 
     */
    public WEB3EquityNewCashBasedOrderSpec createPTSOrderSpec() throws WEB3BaseException   
    {

        final String STR_METHOD_NAME = "createPTSOrderSpec() ";
        log.entering(STR_METHOD_NAME);

        //�����C���X�^���X�`�F�b�N
        if (this.newCachBasedOrderSpec != null)
        {
            log.exiting(STR_METHOD_NAME);
            return this.newCachBasedOrderSpec;
        }

        //�����������P�ʃI�u�W�F�N�g���擾����
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)this.getOrgChangeOrderUnit();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //�s��R�[�h���擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        String l_strMarketCode = null;
        try
        {
            l_strMarketCode =
                l_finApp.getFinObjectManager().getMarket(l_orderUnitRow.getMarketId()).getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //�����R�[�h���擾
        EqTypeProduct l_eqTypeProduct =
            (EqTypeProduct) l_orderUnit.getProduct();
        String l_strProductCode = l_eqTypeProduct.getProductCode();

        //�������擾
        WEB3EquityChangeOrderUnitEntry l_eqChangeOrderUnitEntry =
            (WEB3EquityChangeOrderUnitEntry)this.getChangeOrderUnitEntry();
        double l_dblQuantity = l_eqChangeOrderUnitEntry.getAfterChangeOriginalQuantity();

        //�w�l���擾
        double l_dblPrice =
            l_eqChangeOrderUnitEntry.getAfterChangePrice();

        //���s�������擾
        EqTypeExecutionConditionType l_eqExecutionConditionType =
            l_eqChangeOrderUnitEntry.getAfterChangeExecutionConditionType();

        //�ŋ敪:�����P��.�ŋ敪
        TaxTypeEnum l_taxType = l_orderUnit.getTaxType();

        //�����������F�@@this.get�����l�ڍ�( ).get�����㒍��������
        Timestamp l_expirationDate = new Timestamp(l_eqChangeOrderUnitEntry.getModifiedExpirationDate().getTime());

        //is�������F�@@�����P��.������ʂ�蔻�肷��
        boolean l_blnIsSellOrder = false;
        if (OrderTypeEnum.EQUITY_SELL.equals(l_orderUnit.getOrderType()))
        {
            l_blnIsSellOrder = true;
        }

        //�����`���l���F�@@�����P��.���񒍕��̒����`���l��
        String l_strOrderChanel = l_orderUnitRow.getOrderChanel();

        //�l�i����
        String l_strPriceConditionType = l_eqChangeOrderUnitEntry.getChangeAfterPriceConditionType();
        
        //���������F�@@this.get�����l�ڍ�( ).get�����㔭������
        String l_strOrderCondType =
            ((WEB3EquityChangeOrderUnitEntry) l_eqChangeOrderUnitEntry).getChangeAfterOrderCondType();

        //�����������Z�q�F�@@this.get�����l�ڍ�( ).get�����㔭���������Z�q
        String l_strChangeAfterOrderCondOperator =
            l_eqChangeOrderUnitEntry.getChangeAfterOrderCondOperator();

        //�t�w�l��l�F�@@this.get�����l�ڍ�( ).get������t�w�l��l
        double l_dblChangeAfterStopOrderCondPriceBasePrice =
            l_eqChangeOrderUnitEntry.getChangeAfterStopOrderCondPriceBasePrice();

        //�iW�w�l�j�����w�l�F�@@this.get�����l�ڍ�( ).get������iW�w�l�j�����w�l
        double l_dblChangeAfterWlimitOrderCondPrice =
            l_eqChangeOrderUnitEntry.getChangeAfterWlimitOrderCondPrice();

        //���񒍕��̒����P��ID�F�@@�����P��.���񒍕��̒����P��ID
        Long l_beforeCarryoverOrderUnitId = null;
        if (l_orderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_beforeCarryoverOrderUnitId = null;
        }
        else
        {
            l_beforeCarryoverOrderUnitId =
                new Long(l_orderUnitRow.getFirstOrderUnitId());
        }

        this.newCachBasedOrderSpec =
            WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                this.getInstitutionCode(),
                (WEB3GentradeTrader)this.getTrader(),
                l_strMarketCode,
                l_strProductCode,
                l_dblQuantity,
                l_dblPrice,
                l_eqExecutionConditionType,
                l_taxType,
                l_expirationDate,
                l_blnIsSellOrder, 
                l_strOrderChanel,
                l_strPriceConditionType,
                l_strOrderCondType,
                l_strChangeAfterOrderCondOperator,
                l_dblChangeAfterStopOrderCondPriceBasePrice,
                l_dblChangeAfterWlimitOrderCondPrice, 
                l_beforeCarryoverOrderUnitId);

       //�萔�������쐬���� 
       //this.�����������e.set�萔�����i�R�[�h( )���R�[������B 
       //[set�萔�����i�R�[�h( )�̈���] 
       //�萔�����i�R�[�h�F�@@"��ꊔ��"�i�Œ�j 
       this.newCachBasedOrderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);

       //this.�����������e.create�萔��( )���R�[������B 
       //[create�萔��( )�̈���] 
       //���X�F�@@�擾�����������̒����P�ʃI�u�W�F�N�g.���XID�ɊY�����镔�X�I�u�W�F�N�g 
       //����R�[�h�iSONAR�j : �擾�����������̒����P�ʃI�u�W�F�N�g.����R�[�h�iSONAR�j 
       AccountManager l_accountManager = l_finApp.getAccountManager();
       Branch l_branch = null;        
       try
       {
           l_branch = l_accountManager.getBranch(l_orderUnit.getBranchId());
       }
       catch (NotFoundException l_ex)
       {
           log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "�e�[�u���ɊY������f�[�^������܂���B",
               l_ex);
       }
       this.newCachBasedOrderSpec.createCommission(l_branch, l_orderUnitRow.getSonarTradedCode());
       //this.set���������萔������( )���R�[������B 
       //[set���������萔������( )�̈���] 
       //�萔���F�@@this.�����������e.get�萔��( )�̖߂�l�I�u�W�F�N�g 
       this.setOriginalOrderCommCond(this.newCachBasedOrderSpec.getCommission());
           
       //this.�����������e.set�萔��( )���R�[������B 
       //[set�萔��( )�̈���] 
       //�萔���F�@@this.�����������e.get�萔��( )�̖߂�l�I�u�W�F�N�g
       this.newCachBasedOrderSpec.setCommission(this.newCachBasedOrderSpec.getCommission());
         
       log.exiting(STR_METHOD_NAME);
       return this.newCachBasedOrderSpec;
    }
}
@
