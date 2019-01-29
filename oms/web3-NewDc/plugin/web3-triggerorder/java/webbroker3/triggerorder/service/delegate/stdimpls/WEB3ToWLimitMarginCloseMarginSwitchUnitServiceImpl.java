head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitMarginCloseMarginSwitchUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : W�w�l�����M�p����ԍϐؑֈꌏ�T�[�r�XImpl(WEB3ToWLimitMarginCloseMarginSwitchUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/11/17 ꎉ�(���u) �V�K�쐬 �i���f���jNo.176 195 197
Revision History : 2007/01/16 ���G�� (���u)�i���f���jNo.220
Revision History : 2007/01/26 ������ (���u)�i���f���jNo.226
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginChangeSettleContractOrderSpec;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitMarginCloseMarginSwitchUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (W�w�l�����M�p����ԍϐؑֈꌏ�T�[�r�XImpl)<BR>
 *
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3ToWLimitMarginCloseMarginSwitchUnitServiceImpl
    extends WEB3ToWLimitEqTypeSwitchUnitServiceImpl
    implements WEB3ToWLimitMarginCloseMarginSwitchUnitService

{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitMarginCloseMarginSwitchUnitServiceImpl.class);

    /**
     * constructor
     */
    public WEB3ToWLimitMarginCloseMarginSwitchUnitServiceImpl()
    {

    }

    /**
     *�icreate�����������e�j<BR>
     *�i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     *<BR>
     *�M�p�ԍϒ����������e���쐬����B<BR>
     * <BR>
     *�P�j�@@�p�����[�^.�����P��.getDataSourceObject()���R�[������B<BR>
     * <BR>
     *�Q�j�@@�����|�W�V�����}�l�[�W��.create���ό����G���g��()���R�[������B<BR>
     * <BR>
     *�@@�@@�@@[create���ό����G���g��()�̈���]<BR>
     *�@@�@@�@@�����P��ID�F�@@�p�����[�^.�����P��.�����P��ID <BR>
     * <BR>
     *�R�j�@@���������ڍ׃I�u�W�F�N�g�iEqTypeContractSettleChangeOrderUnitEntry()�j<BR>
     *�@@�@@�𐶐�����B<BR>
     * <BR>
     *�@@�@@�@@[EqTypeContractSettleChangeOrderUnitEntry()�̈���] <BR>
     *�@@�@@�@@�����P��ID�F�@@�p�����[�^.�����P��.�����P��ID <BR>
     *�@@�@@�@@������w�l�F�@@�����P��Row.�iW�w�l�j �����w�l <BR>
     *�@@�@@�@@���ό����G���g���F�@@create���ό����G���g��()�̖߂�l<BR>
     * <BR>
     *�S�j�@@�g�����������}�l�[�W��.is�o����܂Œ����P��()���R�[������B<BR>
     * <BR>
     *�@@�@@�@@[is�o����܂Œ����P��()�̈���] <BR>
     *�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     *�T�j�@@�M�p�ԍϒ����������e�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     *�@@�@@�@@[create�ԍϒ����������e()�̈���]<BR>
     *�@@�@@�@@����ID�F�@@�p�����[�^.�����P��.����ID<BR>
     *�@@�@@�@@���������ڍׁF�@@���������ڍ׃I�u�W�F�N�g<BR>
     *�@@�@@�@@������l�i�����F�@@�����P��Row.�l�i����<BR>
     *�@@�@@�@@�����㔭�������F�@@�����P��Row.��������<BR>
     *�@@�@@�@@�����㔭���������Z�q�F�@@�����P��Row.�����������Z�q<BR>
     *�@@�@@�@@������t�w�l��l�F �����P��Row.�t�w�l��l<BR>
     *�@@�@@�@@������iW�w�l�j�����w�l�F �����P��Row.�iW�w�l�j�����w�l<BR>
     *�@@�@@�@@�����㎷�s�����F�@@�����P��Row.�iW�w�l�j���s����<BR>
     *�@@�@@�@@�����㒍���������F�@@�����P��Row.�����������t<BR>
     *�@@�@@�@@������is�o����܂Œ����F�@@is�o����܂Œ���()�̖߂�l<BR>
     *�@@�@@�@@������iW�w�l�j���s�����F�@@�����P��Row.�iW�w�l�j���s����<BR>
     *�@@�@@�@@�iW�w�l�j�L����ԋ敪�F�@@"�X�g�b�v�����L��"�i�Œ�j<BR>
     * <BR>
     *�U�j�@@�M�p�ԍϒ����������e��ԋp����B<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@return ChangeOrderSpec <BR>
     * @@throws WEB3BaseException
     */
    public ChangeOrderSpec createChangeOrderSpec(
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createChangeOrderSpec(EqTypeOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�@@�p�����[�^.�����P��.getDataSourceObject()���R�[������B
        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();

        //�p�����[�^.�����P��.�����P��ID
        long l_lngEqOrderUnitId = l_eqTypeOrderUnit.getOrderUnitId();

        //�����P��Row.�iW�w�l�j �����w�l
        double l_dblWLimitPrice = l_eqTypeOrderUnitRow.getWLimitPrice();

        //�Q�j�@@�����|�W�V�����}�l�[�W��.create���ό����G���g��()���R�[������B
        //�@@�@@�@@[create���ό����G���g��()�̈���]
        //�@@�@@�@@�����P��ID�F�@@�p�����[�^.�����P��.�����P��ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();

        EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntry =
            l_positionManager.createCloseMarginContractEntry(l_lngEqOrderUnitId);

        //�R�j�@@���������ڍ׃I�u�W�F�N�g�iEqTypeContractSettleChangeOrderUnitEntry()�j�𐶐�����B
        //�@@�@@�@@[EqTypeContractSettleChangeOrderUnitEntry()�̈���]
        //�@@�@@�@@�����P��ID�F�@@�p�����[�^.�����P��.�����P��ID
        //�@@�@@�@@������w�l�F�@@�����P��Row.�iW�w�l�j �����w�l
        //�@@�@@�@@���ό����G���g���F�@@create���ό����G���g��()�̖߂�l
        EqTypeContractSettleChangeOrderUnitEntry l_eqTypeContractSettleChangeOrderUnitEntry =
            new EqTypeContractSettleChangeOrderUnitEntry(
                l_lngEqOrderUnitId,
                l_dblWLimitPrice,
                l_eqTypeSettleContractOrderEntry);

        //�S�j�@@�g�����������}�l�[�W��.is�o����܂Œ����P��()���R�[������B
        //�@@�@@�@@[is�o����܂Œ����P��()�̈���]
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        boolean l_blnIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(l_eqTypeOrderUnit);

        //�T�j�M�p�ԍϒ����������e�I�u�W�F�N�g�𐶐�����B
        //�@@�@@�@@[create�ԍϒ����������e()�̈���]
        //�@@�@@�@@����ID�F�@@�p�����[�^.�����P��.����ID
        //�@@�@@�@@���������ڍׁF�@@���������ڍ׃I�u�W�F�N�g
        //�@@�@@�@@������l�i�����F�@@�����P��Row.�l�i����
        //�@@�@@�@@�����㔭�������F�@@�����P��Row.��������
        //�@@�@@�@@�����㔭���������Z�q�F�@@�����P��Row.�����������Z�q
        //�@@�@@�@@������t�w�l��l�F �����P��Row.�t�w�l��l
        //�@@�@@�@@������iW�w�l�j�����w�l�F �����P��Row.�iW�w�l�j�����w�l
        //�@@�@@�@@�����㎷�s�����F�@@�����P��Row.�iW�w�l�j���s����
        //�@@�@@�@@�����㒍���������F�@@�����P��Row.�����������t
        //�@@�@@�@@������is�o����܂Œ����F�@@is�o����܂Œ���()�̖߂�l
        //�@@�@@�@@������iW�w�l�j���s�����F�@@�����P��Row.�iW�w�l�j���s����
        //�@@�@@�@@�iW�w�l�j�L����ԋ敪�F�@@"�X�g�b�v�����L��"�i�Œ�j
        WEB3MarginChangeSettleContractOrderSpec l_orderSpec =
            WEB3MarginChangeSettleContractOrderSpec.createCloseMarginChangeOrderSpec(
                l_eqTypeOrderUnit.getOrderId(),
                l_eqTypeContractSettleChangeOrderUnitEntry,
                l_eqTypeOrderUnitRow.getPriceConditionType(),
                l_eqTypeOrderUnitRow.getOrderConditionType(),
                l_eqTypeOrderUnitRow.getOrderCondOperator(),
                l_eqTypeOrderUnitRow.getStopOrderPrice(),
                l_eqTypeOrderUnitRow.getWLimitPrice(),
                l_eqTypeOrderUnitRow.getWLimitExecCondType(),
                l_eqTypeOrderUnitRow.getExpirationDate(),
                l_blnIsCarriedOrderUnit,
                l_eqTypeOrderUnitRow.getWLimitExecCondType(),
                WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE
                );

        //�U�j�@@�M�p�ԍϒ����������e��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_orderSpec;
    }
    /**
     *�i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     *<BR>
     *�ؑ֒��������R�����s���B<BR>
     *<BR>
     *�P�j�@@�p�����[�^.�����������e��"�M�p�ԍϒ����������e"�^�ɃL���X�g����B<BR>
     *<BR>
     *�Q�j�@@�g�����������}�l�[�W��.validate�ԍϒ�������()���R�[������B<BR>
     *<BR>
     *�@@�@@�@@[validate�ԍϒ�������()�̈���]<BR>
     *�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     *�@@�@@�@@�M�p�ԍϒ����������e�F�@@�M�p�ԍϒ����������e<BR>
     *�@@�@@�@@isSkip�x���󋵃`�F�b�N�F�@@true�i*1�j<BR>
     *<BR>
     *�@@�@@�@@�i*1�jisSkip�x���󋵃`�F�b�N�ɂ���<BR>
     *�@@�@@�@@�@@�ؑ֏����͒x�������̐ؑ֏������s�����߁A<BR>
     *�@@�@@�@@�@@validate���������\���()�̒x���󋵃`�F�b�N���s��Ȃ��B<BR>
     *�@@�@@�@@�@@�����true��ݒ肷��B<BR>
     *<BR>
     *�R�j�@@�Q�j�Ŏ擾����EqTypeOrderValidationResult.getProcessingResult().isFailedResult()<BR>
     * �@@�@@�@@�@@ == true �̏ꍇ<BR>
     *�@@�@@�@@EqTypeOrderValidationResult����G���[�����擾���A��O���X���[����B<BR>
     *<BR>
     *�S�j�@@validate�ԍϒ�������()�̖߂�l��ԋp����B<BR>
     * @@param l_changeOrderSpec - (�����������e)<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@return EqTypeOrderValidationResult <BR>
     * @@throws WEB3BaseException
     */
    public EqTypeOrderValidationResult validate(
        ChangeOrderSpec l_changeOrderSpec,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate(ChangeOrderSpec, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_changeOrderSpec == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�@@�p�����[�^.�����������e��"�M�p�ԍϒ����������e"�^�ɃL���X�g����B
        WEB3MarginChangeSettleContractOrderSpec l_marginChangeSettleContractOrderSpec =
            (WEB3MarginChangeSettleContractOrderSpec)l_changeOrderSpec;

        //�Q�j�@@�g�����������}�l�[�W��.validate�ԍϒ�������()���R�[������B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //�@@�@@�@@[validate�ԍϒ�������()�̈���]
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�@@�M�p�ԍϒ����������e�F�@@�M�p�ԍϒ����������e
        //�@@�@@�@@isSkip�x���󋵃`�F�b�N�F�@@true�i*1�j
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult =
            l_orderManager.validateChangeSettleContractOrder(l_subAccount,
                l_marginChangeSettleContractOrderSpec,
                true);

        //�R�j�@@�Q�j�Ŏ擾����EqTypeOrderValidationResult.getProcessingResult().isFailedResult()
        //           == true �̏ꍇ
        //�@@�@@�@@EqTypeOrderValidationResult����G���[�����擾���A��O���X���[����B
        boolean l_blnIsFailedResult =
            l_eqTypeOrderValidationResult.getProcessingResult().isFailedResult();

        if (l_blnIsFailedResult)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_eqTypeOrderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�S�j�@@validate�ԍϒ�������()�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_eqTypeOrderValidationResult;
    }

    /**
     *�iget�T�Z����v�Z���ʁj<BR>
     *�i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     *<BR>
     *�T�Z����v�Z���ʂ��擾����B<BR>
     *<BR>
     *�P�j�@@�p�����[�^.�����������e��"�M�p�ԍϒ����������e"�^�ɃL���X�g����B<BR>
     *<BR>
     *�Q�j�@@�M�p�ԍϒ����������e.get���������ڍ�()���R�[������B<BR>
     *<BR>
     *�R�j�@@�����v�Z�T�[�r�X.create�萔��()���R�[������B<BR>
     *<BR>
     *�@@�@@�@@[create�萔��()�̈���]<BR>
     *�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     *<BR>
     *�S�j�R�j�Ŏ擾�����萔��.setIs�w�l()���R�[������B<BR>
     * <BR>
     * [setIs�w�l()�̈���]<BR>
     * is�w�l�F�@@���������ڍ�.getAfterChangePrice()��"0"�̏ꍇ�̂݁Atrue���Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@�ȊO�Afalse���Z�b�g<BR>
     * <BR>
     *�T�j�@@�g�����������}�l�[�W��.calc�T�Z���ϑ��v���()���R�[������B<BR>
     *<BR>
     *�@@�@@�@@[calc�T�Z���ϑ��v���()�̈���]<BR>
     *�@@�@@�@@�萔���F�@@create�萔��()�̖߂�l() (*1)<BR>
     *�@@�@@�@@�w�l�F�@@���������ڍ�.getAfterChangePrice()<BR>
     *�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     *�@@�@@�@@��������F�@@�p�����[�^.�����P��..getTradedProduct()<BR>
     *�@@�@@�@@���ό����G���g���F�@@���������ڍ�.getAfterChangeSettleContractOrderEntries()<BR>
     *�@@�@@�@@���ʁF�@@���������ڍ�.getAfterChangeTotalQuantity()<BR>
     *�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     *�@@�@@�@@�����萔�ʁF�@@0�i�Œ�j<BR>
     *�@@�@@�@@������P���F�@@0�i�Œ�j<BR>
     *�@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j�Œ�<BR>
     *<BR>
     *�@@�@@�@@(*1)�e���ځA������i�X�g�b�v�����j�̒l���Z�b�g����B<BR>
     *<BR>
     *6�j�@@�T�Z���ϑ��v����v�Z���ʃI�u�W�F�N�g��ԋp����B<BR>
     * @@param l_changeOrderSpec - (�����������e)<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@return WEB3EquityEstimatedPrice
     * @@throws WEB3BaseException
     */
    public WEB3EquityEstimatedPrice getEstimatedPrice(
        ChangeOrderSpec l_changeOrderSpec,
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEstimatedPrice(ChangeOrderSpec, "
            + "EqTypeOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_changeOrderSpec == null || l_eqTypeOrderUnit == null || l_subAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�@@�p�����[�^.�����������e��"�M�p�ԍϒ����������e"�^�ɃL���X�g����B
        WEB3MarginChangeSettleContractOrderSpec l_marginChangeSettleContractOrderSpec =
            (WEB3MarginChangeSettleContractOrderSpec)l_changeOrderSpec;

        //�Q�j�@@�M�p�ԍϒ����������e.get���������ڍ�()���R�[������B
        EqTypeContractSettleChangeOrderUnitEntry l_changeOrderUnitEntry =
            l_marginChangeSettleContractOrderSpec.getChangeOrderUnitEntry();

        //�R�j�@@�����v�Z�T�[�r�X.create�萔��()���R�[������B
        //�@@�@@�@@[create�萔��()�̈���]
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityBizLogicProvider l_eqBizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();

        WEB3GentradeCommission l_commission =
            l_eqBizLogicProvider.createCommission((EqTypeOrderUnitImpl)l_eqTypeOrderUnit);

        //�S�j�@@�R�j�Ŏ擾�����萔��.setIs�w�l()���R�[������B
        //�@@[setIs�w�l()�̈���]
        //�@@is�w�l�F�@@���������ڍ�.getAfterChangePrice()��"0"�̏ꍇ�̂݁Atrue���Z�b�g
        //�@@�@@�@@�@@�@@�@@�@@�ȊO�Afalse���Z�b�g
        boolean l_blnIsLimitPrice = false;
        if (l_changeOrderUnitEntry.getAfterChangePrice() != 0)
        {
            l_blnIsLimitPrice = true;
        }
        l_commission.setIsLimitPrice(l_blnIsLimitPrice);

        //�S�j�@@�g�����������}�l�[�W��.calc�T�Z���ϑ��v���()���R�[������B
        //�@@�@@�@@[calc�T�Z���ϑ��v���()�̈���]
        //�@@�@@�@@�萔���F�@@create�萔��()�̖߂�l() (*1)
        //�@@�@@�@@�w�l�F�@@���������ڍ�.getAfterChangePrice()
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�@@��������F�@@�p�����[�^.�����P��..getTradedProduct()
        //�@@�@@�@@���ό����G���g���F�@@���������ڍ�.getAfterChangeSettleContractOrderEntries()
        //�@@�@@�@@���ʁF�@@���������ڍ�.getAfterChangeTotalQuantity()
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        //�@@�@@�@@�����萔�ʁF�@@0�i�Œ�j
        //�@@�@@�@@������P���F�@@0�i�Œ�j
        //�@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j�Œ�
        //�@@�@@�@@(*1)�e���ځA������i�X�g�b�v�����j�̒l���Z�b�g����B
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        SubAccountRow l_subAcctRow =
            (SubAccountRow)l_subAccount.getDataSourceObject();
        WEB3GentradeSubAccount l_subAcc = new WEB3GentradeSubAccount(l_subAcctRow);

        WEB3EquityTradedProduct l_tradeProduct =
            (WEB3EquityTradedProduct)l_eqTypeOrderUnit.getTradedProduct();

        WEB3EquityRealizedProfitAndLossPrice l_estimatedPrice =
            l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                l_commission,
                l_changeOrderUnitEntry.getAfterChangePrice(),
                l_subAcc,
                l_tradeProduct,
                l_changeOrderUnitEntry.getAfterChangeSettleContractOrderEntries(),
                l_changeOrderUnitEntry.getAfterChangeTotalQuantity(),
                l_eqTypeOrderUnit,
                0,
                0,
                false);
        
        //�T�j�@@�T�Z���ϑ��v����v�Z���ʃI�u�W�F�N�g��ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return l_estimatedPrice;
    }

    /**
     * �ivalidate����]�́j<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * ��������<BR>
     * @@param l_changeOrderSpec - (�����������e)<BR>
     * @@param l_eqTypeOrderValidationResult - (�����R������)<BR>
     * @@param l_equityEstimatedPrice - (�T�Z����v�Z����)<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@throws WEB3BaseException
     */
    public void validateTradingPower(
        ChangeOrderSpec l_changeOrderSpec,
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult,
        WEB3EquityEstimatedPrice l_equityEstimatedPrice,
        EqTypeOrderUnit l_eqTypeOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {

    }

    /**
     *�isubmit�ؑցj<BR>
     *�i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     *<BR>
     *�����̐ؑւ��s���B<BR>
     *<BR>
     *�P�j�@@�p�����[�^.�����������e��"�M�p�ԍϒ����������e"�^�ɃL���X�g����B <BR>
     *<BR>
     *�Q�j�@@�p�����[�^.�����P��.�����ID��null�̏ꍇ�A <BR>
     *�@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrader()���R�[������B<BR>
     *<BR>
     *�@@�@@�@@[getTrader()�̈���] <BR>
     *�@@�@@�@@�����ID�F�@@�p�����[�^.�����P��.�����ID <BR>
     *<BR>
     *�R�j�@@W�w�l���������ؑ֍X�V�C���^�Z�v�^()���R�[������B <BR>
     *<BR>
     *�@@�@@�@@[W�w�l���������ؑ֍X�V�C���^�Z�v�^()�̈���] <BR>
     *�@@�@@�@@�T�Z����v�Z���ʁF�@@�p�����[�^.�T�Z����v�Z���� <BR>
     *<BR>
     *�S�j�@@�g�����������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()���R�[������B<BR>
     *<BR>
     *�@@�@@�@@[setThreadLocalPersistenceEventInterceptor()�̈���] <BR>
     *�@@�@@�@@arg0�F�@@W�w�l���������ؑ֍X�V�C���^�Z�v�^ <BR>
     *<BR>
     *�T�j�@@�g�����������}�l�[�W��.submitChangeSettleContractOrder()���R�[������B <BR>
     *<BR>
     *�@@�@@�@@[submitChangeSettleContractOrder()�̈���] <BR>
     *�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     *�@@�@@�@@�ԍϒ����������e�F�@@�M�p�ԍϒ����������e <BR>
     *�@@�@@�@@����p�X���[�h�F�@@getMainAccount().getTradingPassword()�̖߂�l��decrypt�����l  <BR>
     *�@@�@@�@@isSkip�����R���F�@@true <BR>
     *<BR>
     *�U�j�@@����]�̓T�[�r�X.�]�͍Čv�Z()���R�[������B <BR>
     *<BR>
     *�@@�@@�@@[�]�͍Čv�Z()�̈���] <BR>
     *�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * @@param l_changeOrderSpec - (�����������e)<BR>
     * @@param l_equityEstimatedPrice - (�T�Z����v�Z����)<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@throws WEB3BaseException
     */
    public void submitSwitch(
        ChangeOrderSpec l_changeOrderSpec,
        WEB3EquityEstimatedPrice l_equityEstimatedPrice,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSwitch(ChangeOrderSpec, "
            + "WEB3EquityEstimatedPrice, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_changeOrderSpec == null || l_subAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�@@�p�����[�^.�����������e��"�M�p�ԍϒ����������e"�^�ɃL���X�g����B
        WEB3MarginChangeSettleContractOrderSpec l_marginChangeSettleContractOrderSpec =
            (WEB3MarginChangeSettleContractOrderSpec)l_changeOrderSpec;
        
        //�Q�j�@@�p�����[�^.�⏕����.getMainAccount()���R�[������B
        MainAccount l_mainAcc = l_subAccount.getMainAccount();

        //�R�j�@@W�w�l���������ؑ֍X�V�C���^�Z�v�^()���R�[������B
        //�@@�@@�@@[W�w�l���������ؑ֍X�V�C���^�Z�v�^()�̈���]
        //�@@�@@�@@�T�Z����v�Z���ʁF�@@�p�����[�^.�T�Z����v�Z����
        WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor l_interceptor =
            new WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor(l_equityEstimatedPrice);

        //�S�j�@@�g�����������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()���R�[������B
        //�@@�@@�@@[setThreadLocalPersistenceEventInterceptor()�̈���]
        //�@@�@@�@@arg0�F�@@W�w�l���������ؑ֍X�V�C���^�Z�v�^
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        //�T�j�@@�g�����������}�l�[�W��.submitChangeSettleContractOrder()���R�[������B
        //�@@�@@�@@[submitChangeSettleContractOrder()�̈���]
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�@@�ԍϒ����������e�F�@@�M�p�ԍϒ����������e
        //�@@�@@�@@����p�X���[�h�F�@@getMainAccount().getTradingPassword()�̖߂�l��decrypt�����l
        //�@@�@@�@@isSkip�����R���F�@@true
        WEB3Crypt l_webCrypt = new WEB3Crypt();
        EqTypeOrderSubmissionResult l_submitRes =
            l_orderManager.submitChangeSettleContractOrder(l_subAccount,
                l_marginChangeSettleContractOrderSpec,
                l_webCrypt.decrypt(l_mainAcc.getTradingPassword()),
                true);

        if (l_submitRes.getProcessingResult().isFailedResult())
        {
            log.debug(" __Error[���������X�V]__"
                + l_submitRes.getProcessingResult().getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_submitRes.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�U�j�@@����]�̓T�[�r�X.�]�͍Čv�Z()���R�[������B
        //�@@�@@�@@[�]�͍Čv�Z()�̈���]
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        SubAccountRow l_subAcctRow =
            (SubAccountRow)l_subAccount.getDataSourceObject();
        WEB3GentradeSubAccount l_subAcc = new WEB3GentradeSubAccount(l_subAcctRow);

        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        l_tradingPowerService.reCalcTradingPower(l_subAcc);

        log.exiting(STR_METHOD_NAME);
    }
}
@
