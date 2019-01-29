head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEquityManualSwitchUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����W�w�l�����蓮�ؑ�UnitServiceImpl(WEB3ToWLimitEquityManualSwitchUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/18 ���G�� (���u) �V�K�쐬 �i���f���jNo.184 ,196 , 202
Revesion History : 2007/01/13 ���G�� (���u) ���f��219
Revesion History : 2007/01/30 ������ (���u) ���f��228
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.define.WEB3EquityWlimitOrderPriceDivDef;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.service.WEB3RlsRequestSenderService;
import webbroker3.triggerorder.WEB3ToRlsCoopDataManager;
import webbroker3.triggerorder.message.WEB3EquityManualUnit;
import webbroker3.triggerorder.message.WEB3ManualCommissionInfoUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquityManualSwitchUnitService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (����W�w�l�����蓮�ؑ�UnitServiceImpl)
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3ToWLimitEquityManualSwitchUnitServiceImpl
    extends WEB3ToEquityManualOrderUnitServiceImpl
    implements WEB3ToWLimitEquityManualSwitchUnitService
{
    /**
     * ���O�I�u�W�F�N�g
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3ToWLimitEquityManualSwitchUnitServiceImpl.class);

    /**
     * (set����J�����_�R���e�L�X�g)<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * <BR>
     * �P�j�@@���X�I�u�W�F�N�g���擾�B<BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[getBranch()�ɐݒ肷�����]<BR>
     * �@@�@@�@@arg0�F�@@�����f�[�^.getBranchId()�̖߂�l<BR>
     * <BR>
     * �Q�j�@@�،���ЃI�u�W�F�N�g���擾�B<BR>
     * �@@�@@�@@getBranch()�̖߂�l.getInstitution()���R�[������B<BR>
     * <BR>
     * �R�j�@@�s��I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�R�|�P�j�@@�����P��Row�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����P��.getDataSourceObject()���R�[������B<BR>
     * <BR>
     * �@@�@@�R�|�Q�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@[getMarket()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.getMarketId()<BR>
     * <BR>
     * �S�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�p�����[�^.�����f�[�^�̓��e��������ԃR���e�L�X�g��<BR>
     * �@@�@@�@@�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = getInstitution()�̖߂�l.getInstitutionCode()<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = getBranch()�̖߂�l.getBranchCode()<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = getMarket()�̖߂�l.getMarketCode()<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = (*1)<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�����h<BR>
     * <BR>
     * �@@(*1)������t���i<BR>
     * �@@�@@�@@�E�����J�e�S���i�����f�[�^.getOrderCateg()�j���h���������h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�h�����h���Z�b�g����B<BR>
     * �@@�@@�@@�E�����J�e�S������L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�h�M�p�h���Z�b�g����B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B<BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �T�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * @@param l_orderUnit - (�����f�[�^)<BR>
     * @@throws WEB3BaseException<BR>
     */
    protected void setTradingClendarContext(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setTradingClendarContext(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j
        //����J�����_�����p����R���e�L�X�g�𐶐�����B
        //�P�j�@@���X�I�u�W�F�N�g���擾�B
        //�@@�@@�@@�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B
        //�@@�@@�@@[getBranch()�ɐݒ肷�����]
        //�@@�@@�@@arg0�F�@@�����f�[�^.getBranchId()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

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
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�Q�j�@@�،���ЃI�u�W�F�N�g���擾
        //�@@�@@�@@getBranch()�̖߂�l.getInstitution()���R�[������
        Institution l_institution = l_branch.getInstitution();

        //�R�j�@@�s��I�u�W�F�N�g���擾����
        //�@@�@@�R�|�P�j�@@�����P��Row�𐶐�����
        //�@@�@@�@@�@@�@@�@@�@@�@@�����P��.getDataSourceObject()���R�[������
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //�@@�@@�R�|�Q�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[������
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@[getMarket()�ɐݒ肷�����]
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.getMarketId()
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        long l_lngMarketId = l_eqtypeOrderUnitRow.getMarketId();
        WEB3GentradeMarket l_market = null;

        try
        {
            l_market =
                (WEB3GentradeMarket)l_finObjManager.getMarket(l_lngMarketId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�S�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        //�@@�|�p�����[�^.�����f�[�^�̓��e��������ԃR���e�L�X�g��
        //�@@�@@�@@�v���p�e�B���Z�b�g����B
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        //�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = getInstitution()�̖߂�l.getInstitutionCode()
        l_context.setInstitutionCode(l_institution.getInstitutionCode());

        //�@@����J�����_�R���e�L�X�g.���X�R�[�h = getBranch()�̖߂�l.getBranchCode()
        l_context.setBranchCode(l_branch.getBranchCode());

        //�@@����J�����_�R���e�L�X�g.�s��R�[�h = getMarket()�̖߂�l.getMarketCode()
        l_context.setMarketCode(l_market.getMarketCode());

        //�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

        //�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

        //�@@����J�����_�R���e�L�X�g.������t���i = (*1)
        //�@@(*1)������t���i
        //�@@�@@�@@�E�����J�e�S���i�����f�[�^.getOrderCateg()�j���h���������h�̏ꍇ
        //�@@�@@�@@�@@�@@�@@�h�����h���Z�b�g����B
        //�@@�@@�@@�E�����J�e�S������L�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�h�M�p�h���Z�b�g����B
        if (OrderCategEnum.ASSET.equals(l_orderUnit.getOrderCateg()))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        }
        //�@@  �E�����J�e�S������L�ȊO�̏ꍇ
        //�@@�@@�@@�@@�h�M�p�h���Z�b�g����B
        else
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        }
        //�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�����h
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CHANGE);

        //�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�
        //�@@�@@�@@������ԃR���e�L�X�g���Z�b�g����
        //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //�T�j�@@��t�����A���t���[�����Z�b�g����B
        //�@@�|������ԊǗ�.setTimestamp()���R�[������B
        WEB3GentradeTradingTimeManagement.setTimestamp();
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getUnit���X�|���X)<BR>
     * �����蓮����Unit��W�w�l�����ŗL�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�����f�[�^��"���������P�ʌ^"�ɃL���X�g����B<BR>
     * �@@�@@�i���ȉ��A�L���X�g�����I�u�W�F�N�g��"�����P��"�ƕ\�L����B�j<BR>
     * <BR>
     * �Q�j�@@���ʂ̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@super.getUnit���X�|���X()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[getUnit���X�|���X()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�����f�[�^�F�@@�P�j�Ő������������P��<BR>
     * <BR>
     * �R�j�@@�����P��.�������� == "W�w�l"�̏ꍇ�̂�<BR>
     * �@@�@@�@@���s�������擾����B<BR>
     * <BR>
     * �@@�@@�@@�g�����������}�l�[�W��.get���s�����iSONAR�j()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[get���s����(SONAR)()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@���s�����F�@@�����P��.(W�w�l)���s����<BR>
     * <BR>
     * �S�j�@@�����󋵋敪���擾����B<BR>
     * �@@�@@�@@�����f�[�^�A�_�v�^.getW�w�l�����󋵋敪()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[getW�w�l�����󋵋敪()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P��<BR>
     * <BR>
     * �T�j�@@W�w�l�����蓮�����G���[�R�[�h���擾����B<BR>
     * �@@�@@�@@���[���G���W���A�g�f�[�^�}�l�[�W��.getW�w�l�蓮�����G���[�R�[�h()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[getW�w�l�蓮�����G���[�R�[�h()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P��<BR>
     * <BR>
     * �U�j�@@���[���G���W������̒ʒm�f�[�^���擾����B<BR>
     * �@@�@@�@@���[���G���W���A�g�f�[�^�}�l�[�W��.get���[���G���W������̒ʒm�f�[�^()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[get���[���G���W������̒ʒm�f�[�^()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P��<BR>
     * �@@�@@�@@�@@����������ʁF�@@�hW�w�l�����h<BR>
     * �@@�@@�@@�@@�����^�C�v�F�@@ProductTypeEnum.����<BR>
     * <BR>
     * �V�j�@@�g���A�J�E���g�}�l�[�W��.get�⏕����()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[get�⏕����()�̈���]<BR>
     * �@@�@@�@@�@@arg0�F�@@�����P��.getAccountId()<BR>
     * �@@�@@�@@�@@arg1�F�@@�����P��.getSubAccountId()<BR>
     * <BR>
     * �W�j �蓮�����萔�������擾����B<BR>
     * <BR>
     * �@@�W�|�P�j�@@�����v�Z�T�[�r�X.create�萔��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[create�萔��()�̈���]<BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �@@�W�|�Q�j�@@this.get�T�Z����v�Z����()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[get�T�Z����v�Z����()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P��<BR>
     * �@@�@@�@@�@@�⏕�����F�@@get�⏕����()�̖߂�l<BR>
     * �@@�@@�@@�@@�萔���F�@@�萔���I�u�W�F�N�g<BR>
     * <BR>
     * �@@�W�|�R�j�@@this.create�蓮�����萔�����()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[create�蓮�����萔�����()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�⏕�����F�@@get�⏕����()�̖߂�l<BR>
     * �@@�@@�@@�@@�萔���F�@@�萔���I�u�W�F�N�g<BR>
     * <BR>
     * �X�j�@@�����P��.�����J�e�S�� == "�ԍϒ���"�̏ꍇ�̂�<BR>
     * �@@�@@�@@�M�p����������ׂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@this.create��������byOrder()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[create��������byOrder()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P��<BR>
     * <BR>
     * �P�O�j�@@getUnit���X�|���X()�̖߂�l�i�����蓮����Unit�j��<BR>
     * �@@�@@�@@W�w�l�����ŗL�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����������ʁF�@@"W�w�l����"���Z�b�g<BR>
     * �@@�����������Z�q�F�@@(*1)�����P��.�����������Z�q<BR>
     * �@@���������P���F�@@(*1)�����P��.�t�w�l��l<BR>
     * �@@W�w�l�p�����P���敪�F�@@(*1)�����P��.�iW�w�l�j�����w�l��"0"�̏ꍇ"���s"���Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�A"�w�l"���Z�b�g<BR>
     * �@@W�w�l�p�����P���F�@@(*1)�����P��.�iW�w�l�j�����w�l��"0"�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��.�iW�w�l�j�����w�l���Z�b�g<BR>
     * �@@W�w�l�p���s�����F�@@(*1)get���s�����iSONAR�j()�̖߂�l<BR>
     * �@@�����󋵋敪�F�@@getW�w�l�����󋵋敪()�̖߂�l<BR>
     * �@@�T�Z��n����F�@@�T�Z����v�Z����.get�T�Z��n���()�̖߂�l<BR>
     * �@@�蓮�����G���[�R�[�h�F�@@getW�w�l�蓮�����G���[�R�[�h()�̖߂�l<BR>
     * �@@��������M���ԁF�@@(*2)���[���G���W������̒ʒmParams.tick�q�b�g�^�C���X�^���v<BR>
     * �@@�g���K�[�N�����ԁF�@@(*2)���[���G���W������̒ʒmParams.���[���G���W���t�@@�C�A�^�C���X�^���v<BR>
     * �@@�����������ԁF�@@(*2)���[���G���W������̒ʒmParams.���������^�C���X�^���v<BR>
     * �@@�蓮�����萔�����F�@@create�蓮�����萔�����()�̖߂�l<BR>
     * �@@�M�p����������ׁF�@@create��������byOrder()�̖߂�l<BR>
     * <BR>
     * �@@(*1)�����P��.����������"W�w�l"�̏ꍇ�̂݃Z�b�g<BR>
     * �@@(*2)get���[���G���W������̒ʒm()�̖߂�l��null�̏ꍇ�Z�b�g<BR>
     * <BR>
     * �P�P�j�@@�v���p�e�B���Z�b�g���������蓮����Unit��Ԃ��B<BR>
     * <BR>
     * @@param l_orderUnit - ( �����f�[�^)
     * @@return WEB3EquityManualUnit
     * @@throws WEB3BaseException
     */
    protected WEB3EquityManualUnit getUnitResponse(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUnitResponse(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�@@�����f�[�^��"���������P�ʌ^"�ɃL���X�g����
        //  �@@�@@�i���ȉ��A�L���X�g�����I�u�W�F�N�g��"�����P��"�ƕ\�L����B�j
        EqTypeOrderUnit l_eqTypeOrderUnit = l_orderUnit;
        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        //  �Q�j�@@���ʂ̃v���p�e�B���Z�b�g����
        //  �@@�@@�@@super.getUnit���X�|���X()���R�[������
        //  �@@�@@�@@�@@[getUnit���X�|���X()�ɐݒ肷�����]
        //  �@@�@@�@@�@@�����f�[�^�F�@@�P�j�Ő������������P��
        WEB3EquityManualUnit l_equityManualUnit =
            super.getUnitResponse(l_eqTypeOrderUnit);

        //�R�j�����P��.�������� == "W�w�l"�̏ꍇ
        //  �@@���s�������擾����
        //  �@@�@@�@@�g�����������}�l�[�W��.get���s�����iSONAR�j()���R�[������
        //  �@@�@@�@@�@@[get���s����(SONAR)()�ɐݒ肷�����]
        //  �@@�@@�@@�@@���s�����F�@@�����P��.(W�w�l)���s����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        String l_strExecutionConditionTypeSonar = null;
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
            l_eqTypeOrderUnitRow.getOrderConditionType()))
        {
            l_strExecutionConditionTypeSonar = l_orderManager.getExecutionConditionTypeSonar(
                    l_eqTypeOrderUnitRow.getWLimitExecCondType());
        }

        //  �S�j�@@�����󋵋敪���擾����
        //  �@@�@@�@@�����f�[�^�A�_�v�^.getW�w�l�����󋵋敪()���R�[������
        //  �@@�@@�@@�@@[getW�w�l�����󋵋敪()�ɐݒ肷�����]
        //  �@@�@@�@@�@@�����P�ʁF�@@�����P��
        String l_strWLimitOrderStatusType =
            WEB3EquityDataAdapter.getWLimitOrderStatusType(l_eqTypeOrderUnit);

        //  �T�j�@@W�w�l�����蓮�����G���[�R�[�h���擾����
        //  �@@�@@�@@���[���G���W���A�g�f�[�^�}�l�[�W��.getW�w�l�蓮�����G���[�R�[�h()���R�[������
        //  �@@�@@�@@�@@[getW�w�l�蓮�����G���[�R�[�h()�ɐݒ肷�����]
        //  �@@�@@�@@�@@�����P�ʁF�@@�����P��
        String l_strWLimitManualOrderErrorCode =
            WEB3ToRlsCoopDataManager.getWLimitManualOrderErrorCode(l_eqTypeOrderUnit);

        //  �U�j�@@���[���G���W������̒ʒm�f�[�^���擾����
        //  �@@�@@�@@���[���G���W���A�g�f�[�^�}�l�[�W��.get���[���G���W������̒ʒm�f�[�^()���R�[������
        //  �@@�@@�@@�@@[get���[���G���W������̒ʒm�f�[�^()�ɐݒ肷�����]
        //  �@@�@@�@@�@@�����P�ʁF�@@�����P��
        //  �@@�@@�@@�@@����������ʁF�@@�hW�w�l�����h
        //  �@@�@@�@@�@@�����^�C�v�F�@@ProductTypeEnum.����
        RlsConOrderHitNotifyParams l_hitNotifyParams =
            WEB3ToRlsCoopDataManager.getRLSConOrderHitNotifyData(
                l_eqTypeOrderUnit,
                WEB3TriggerOrderTypeDef.W_LlIMIT,
                ProductTypeEnum.EQUITY);

        //  �V�j�@@�g���A�J�E���g�}�l�[�W��.get�⏕����()���R�[������
        //  �@@�@@�@@�@@[get�⏕����()�̈���]
        //  �@@�@@�@@�@@arg0�F�@@�����P��.getAccountId()
        //  �@@�@@�@@�@@arg1�F�@@�����P��.getSubAccountId()
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accountManager.getSubAccount(
                l_eqTypeOrderUnit.getAccountId(),
                l_eqTypeOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //  �W�j�蓮�����萔�������擾����
        //  �@@�W�|�P�j�@@�����v�Z�T�[�r�X.create�萔��()���R�[������
        //  �@@�@@�@@�@@[create�萔��()�̈���]
        //  �@@�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        WEB3EquityBizLogicProvider l_eqBizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();

        EqTypeOrderUnitImpl l_orderUnitImpl =
            (EqTypeOrderUnitImpl)l_eqTypeOrderUnit;

        WEB3GentradeCommission l_commission =
            l_eqBizLogicProvider.createCommission(l_orderUnitImpl);

        //  �@@�W�|�Q�j�@@this.get�T�Z����v�Z����()���R�[������
        //  �@@�@@�@@�@@[get�T�Z����v�Z����()�ɐݒ肷�����]
        //  �@@�@@�@@�@@�����P�ʁF�@@�����P��
        //  �@@�@@�@@�@@�⏕�����F�@@get�⏕����()�̖߂�l
        //  �@@�@@�@@�@@�萔���F�@@�萔���I�u�W�F�N�g
        WEB3EquityEstimatedPrice l_equityEstimatedPrice =
            this.getEstimatedPrice(l_eqTypeOrderUnit, l_subAccount, l_commission);

        //  �@@�W�|�R�j�@@this.create�蓮�����萔�����()���R�[������
        //  �@@�@@�@@�@@[create�蓮�����萔�����()�ɐݒ肷�����]
        //  �@@�@@�@@�@@�⏕�����F�@@get�⏕����()�̖߂�l
        //  �@@�@@�@@�@@�萔���F�@@�萔���I�u�W�F�N�g
        WEB3ManualCommissionInfoUnit l_commissionInfoUnit =
            this.createManualCommissionInfoUnit(l_subAccount, l_commission);

        //  �X�j�@@�����P��.�����J�e�S��=="�ԍϒ���"�̏ꍇ�̂�
        //  �@@�@@�@@�M�p����������ׂ��擾����
        //  �@@�@@�@@this.create��������byOrder()���R�[������
        //  �@@�@@�@@�@@[create��������byOrder()�ɐݒ肷�����]
        //  �@@�@@�@@�@@�����P�ʁF�@@�����P��
        WEB3MarginContractUnit[] l_marginContractUnits = null;
        if (OrderCategEnum.CLOSE_MARGIN.equals(l_eqTypeOrderUnit.getOrderCateg()))
        {
            l_marginContractUnits =
                this.createContractUnitByOrder(l_eqTypeOrderUnit);
        }

        //  �P�O�j�@@getUnit���X�|���X()�̖߂�l�i�����蓮����Unit�j��
        //  �@@�@@�@@W�w�l�����ŗL�̃v���p�e�B���Z�b�g����
        //  �@@����������ʁF�@@"W�w�l����"���Z�b�g
        l_equityManualUnit.triggerOrderType = WEB3TriggerOrderTypeDef.W_LlIMIT;

        //(*1)�����P��.����������"W�w�l"�̏ꍇ�̂݃Z�b�g
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
            l_eqTypeOrderUnitRow.getOrderConditionType()))
        {
            //  �@@�����������Z�q�F�@@(*1)�����P��.�����������Z�q
            l_equityManualUnit.condOperator = l_eqTypeOrderUnitRow.getOrderCondOperator();

            //  �@@���������P���F�@@(*1)�����P��.�t�w�l��l
            if (!l_eqTypeOrderUnitRow.getStopOrderPriceIsNull())
            {
                l_equityManualUnit.orderCondPrice = WEB3StringTypeUtility.formatNumber(
                    l_eqTypeOrderUnitRow.getStopOrderPrice());
            }

            //  �@@W�w�l�p�����P���敪�F�@@(*1)�����P��.�iW�w�l�j�����w�l��"0"�̏ꍇ"���s"���Z�b�g �@@�ȊO�A"�w�l"���Z�b�g
            if (l_eqTypeOrderUnitRow.getWLimitPrice() == 0)
            {
                l_equityManualUnit.wLimitOrderPriceDiv =
                    WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_MARKET_PRICE;
            }
            else
            {
                l_equityManualUnit.wLimitOrderPriceDiv =
                    WEB3EquityWlimitOrderPriceDivDef.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE;

                //  �@@W�w�l�p�����P���F�@@(*1)�����P��.�iW�w�l�j�����w�l��"0"�ȊO�̏ꍇ�A
                //�����P��.�iW�w�l�j�����w�l���Z�b�g
                l_equityManualUnit.wLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_eqTypeOrderUnitRow.getWLimitPrice());
            }

            //  �@@W�w�l�p���s�����F�@@(*1)get���s�����iSONAR�j()�̖߂�l
            l_equityManualUnit.wlimitExecCondType = l_strExecutionConditionTypeSonar;
        }

        //  �@@�����󋵋敪�F�@@getW�w�l�����󋵋敪()�̖߂�l
        l_equityManualUnit.triggerOrderState = l_strWLimitOrderStatusType;

        //  �@@�T�Z��n����F�@@�T�Z����v�Z����.get�T�Z��n���()�̖߂�l
        if (l_equityEstimatedPrice != null)
        {
            l_equityManualUnit.estimatedPrice = WEB3StringTypeUtility.formatNumber(
                l_equityEstimatedPrice.getEstimateDeliveryAmount());
        }

        //  �@@�蓮�����G���[�R�[�h�F�@@getW�w�l�蓮�����G���[�R�[�h()�̖߂�l
        l_equityManualUnit.manualOrderErrorCode = l_strWLimitManualOrderErrorCode;

        //  �@@(*2)get���[���G���W������̒ʒm()�̖߂�l��null�̏ꍇ�Z�b�g
        if (l_hitNotifyParams != null)
        {
            //��������M���ԁF�@@(*2)���[���G���W������̒ʒmParams.tick�q�b�g�^�C���X�^���v
            l_equityManualUnit.currentPriceInfoAcceptTime = l_hitNotifyParams.getHitTickTimestamp();

            //  �@@�g���K�[�N�����ԁF�@@(*2)���[���G���W������̒ʒmParams.���[���G���W���t�@@�C�A�^�C���X�^���v
            l_equityManualUnit.triggerStartTime = l_hitNotifyParams.getRlsHitTimestamp();

            //  �@@�����������ԁF�@@(*2)���[���G���W������̒ʒmParams.���������^�C���X�^���v
            l_equityManualUnit.orderCompleteTime = l_hitNotifyParams.getOrderSubmitTimestamp();
        }

        //  �@@�蓮�����萔�����F�@@create�蓮�����萔�����()�̖߂�l
        l_equityManualUnit.commissionInfo = l_commissionInfoUnit;
        //  �@@�M�p����������ׁF�@@create��������byOrder()�̖߂�l
        l_equityManualUnit.contractUnits = l_marginContractUnits;

        //  �P�P�j�@@�v���p�e�B���Z�b�g���������蓮����Unit��Ԃ�
        log.exiting(STR_METHOD_NAME);
        return l_equityManualUnit;
    }

    /**
     * (get�T�Z����v�Z����)<BR>
     * �X�g�b�v�����̊T�Z������擾����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����P��.getDataSourceObject()���R�[������B <BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is������()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[is���蒍��()�̈���] <BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �R�j�@@�T�Z������擾����B <BR>
     * <BR>
     * �@@�R�|�P�j�@@�����P��.�����J�e�S����"��������"�̏ꍇ <BR>
     * �@@�@@�@@�g�����������}�l�[�W��.calc�T�Z��n���()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[calc�T�Z��n���()�̈���] <BR>
     * �@@�@@�@@�@@�萔���F�@@�p�����[�^.�萔�� <BR>
     * �@@�@@�@@�@@�w�l�F�@@�����P��.�iW�w�l�j�����w�l <BR>
     * �@@�@@�@@�@@�iW�w�l)�����w�l�F �����P��.�iW�w�l�j�����w�l <BR>
     * �@@�@@�@@�@@�t�w�l��l�F�@@�����P��.�t�w�l��l <BR>
     * �@@�@@�@@�@@���s�����F�@@�����P��.�iW�w�l�j���s���� <BR>
     * �@@�@@�@@�@@�iW�w�l�j���s�����F�@@�����P��.�iW�w�l�j���s���� <BR>
     * �@@�@@�@@�@@�l�i�����F�@@�����P��.�l�i���� <BR>
     * �@@�@@�@@�@@���������F�@@�����P��.�������� <BR>
     * �@@�@@�@@�@@�m�F���擾�����F�@@0�i�Œ�j <BR>
     * �@@�@@�@@�@@is�X�g�b�v�����L���F�@@true�i�Œ�j�i*�j <BR>
     * �@@�@@�@@�@@�⏕�����F�@@get�⏕����()�̖߂�l <BR>
     * �@@�@@�@@�@@��������F�@@�����P��.getTradedProduct()�̖߂�l <BR>
     * �@@�@@�@@�@@�����F�@@�����P��.�������� <BR>
     * �@@�@@�@@�@@is�������F�@@is������()�̖߂�l <BR>
     * �@@�@@�@@�@@��萔�ʁF�@@�����P��.��萔�� <BR>
     * �@@�@@�@@�@@���v�����z�F�@@�����P��.���v�����z <BR>
     * �@@�@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�Œ�j <BR>
     * <BR>
     * �@@�R�|�Q�j�@@�����P��.�����J�e�S����"�V�K������"�̏ꍇ <BR>
     * �@@�@@�@@�g�����������}�l�[�W��.calc�����������()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[calc�����������()�̈���]  <BR>
     * �@@�@@�@@�@@�萔���F�@@�p�����[�^.�萔�� <BR>
     * �@@�@@�@@�@@�w�l�F�@@�����P��.�iW�w�l�j�����w�l <BR>
     * �@@�@@�@@�@@�iW�w�l�j�����w�l�F�@@�����P��.�iW�w�l�j�����w�l  <BR>
     * �@@�@@�@@�@@�t�w�l��l�F�@@�����P��.�t�w�l��l  <BR>
     * �@@�@@�@@�@@���s�����F�@@�����P��.�iW�w�l�j���s���� <BR>
     * �@@�@@�@@�@@�iW�w�l�j���s�����F�@@�����P��.�iW�w�l�j���s���� <BR>
     * �@@�@@�@@�@@�l�i�����F�@@�����P��.�l�i���� <BR>
     * �@@�@@�@@�@@���������F�@@�����P��.��������  <BR>
     * �@@�@@�@@�@@�m�F���擾�����F�@@0�i�Œ�j <BR>
     * �@@�@@�@@�@@is�X�g�b�v�����L���F�@@true�i�Œ�j �i*�j <BR>
     * �@@�@@�@@�@@is�����F�@@is������()�̖߂�l <BR>
     * �@@�@@�@@�@@�⏕�����F�@@get�⏕����()�̖߂�l <BR>
     * �@@�@@�@@�@@��������F�@@�����P��.getTradedProduct()�̖߂�l <BR>
     * �@@�@@�@@�@@�����F�@@�����P��.�������� <BR>
     * �@@�@@�@@�@@��萔�ʁF�@@�����P��.��萔�� <BR>
     * �@@�@@�@@�@@���v�����z�F�@@�����P��.���v�����z  <BR>
     * �@@�@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�Œ�j  <BR>
     * <BR>
     * �@@�R�|�R�j�@@�����P��.�����J�e�S����"�ԍϒ���"�̏ꍇ <BR>
     * <BR>
     * �@@�@@�R�|�R�|�P�j�@@�����|�W�V�����}�l�[�W��.create���ό����G���g��()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[create���ό����G���g��()�̈���]  <BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�F�@@�����P��.�����P��ID <BR>
     * <BR>
     * �@@�@@�R�|�R�|�Q�j�@@�p�����[�^.�萔��.setIs�w�l()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[setIs�w�l()�̈���]<BR>
     * �@@�@@�@@�@@�@@�@@is�w�l�F�@@�����P��.�iW�w�l�j�����w�l��"0"�̏ꍇ�̂݁Atrue�i�w�l�j���Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�Afalse�i���s�j���Z�b�g<BR>
     * <BR>
     * �@@�@@�R�|�R�|�R�j�@@�g�����������}�l�[�W��.calc�T�Z���ϑ��v���()���R�[������B  <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[calc�T�Z���ϑ��v���()�̈���]  <BR>
     * �@@�@@�@@�@@�@@�@@�萔���F�@@�p�����[�^.�萔�� <BR>
     * �@@�@@�@@�@@�@@�@@�w�l�F�@@�����P��.�iW�w�l�j�����w�l <BR>
     * �@@�@@�@@�@@�@@�@@�⏕�����F�@@get�⏕����()�̖߂�l <BR>
     * �@@�@@�@@�@@�@@�@@��������F�@@�����P��..getTradedProduct()�̖߂�l <BR>
     * �@@�@@�@@�@@�@@�@@���ό����G���g���F�@@create���ό����G���g��()�̖߂�l <BR>
     * �@@�@@�@@�@@�@@�@@���ʁF�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�@@�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * �@@�@@�@@�@@�@@�@@�����萔�ʁF�@@0�i�Œ�j  <BR>
     * �@@�@@�@@�@@�@@�@@������P���F�@@0�i�Œ�j  <BR>
     * �@@�@@�@@�@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�Œ�j <BR>
     * <BR>
     * �S�j�@@�T�Z����v�Z���ʃI�u�W�F�N�g��ԋp����B <BR>
     * <BR>
     * <BR>
     * �i*�j�X�g�b�v�����L�����̊T�Z������v�Z����B <BR>
     * <BR>
     * @@param l_orderUnit - (�����f�[�^)
     * @@param l_subAccount - (�⏕����)
     * @@param l_commission - (�萔��)
     * @@return WEB3EquityEstimatedPrice
     * @@throws WEB3BaseException
     */
    protected WEB3EquityEstimatedPrice getEstimatedPrice(
        EqTypeOrderUnit l_orderUnit,
        SubAccount l_subAccount,
        WEB3GentradeCommission l_commission) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getEstimatedPrice(EqTypeOrderUnit, SubAccount, WEB3GentradeCommission)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�@@�p�����[�^.�����P��.getDataSourceObject()���R�[������
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //�Q�j�@@�g�����������}�l�[�W��.is������()���R�[������
        //�@@�@@�@@[is���蒍��()�̈���]
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnSellOrder = l_orderManager.isSellOrder(l_orderUnit);

        //�R�j�@@�T�Z������擾����
        //�@@�R�|�P�j�@@�����P��.�����J�e�S����"��������"�̏ꍇ
        if (OrderCategEnum.ASSET.equals(l_orderUnitRow.getOrderCateg()))
        {
            //�@@�@@�@@�g�����������}�l�[�W��.calc�T�Z��n���()���R�[������
            //�@@�@@�@@�@@[calc�T�Z��n���()�̈���]
            //�@@�@@�@@�@@�萔���F�@@�p�����[�^.�萔��
            //�@@�@@�@@�@@�w�l�F�@@�����P��.�iW�w�l�j�����w�l
            //�@@�@@�@@�@@�iW�w�l)�����w�l�F�����P��.�iW�w�l�j�����w�l
            //�@@�@@�@@�@@�t�w�l��l�F�@@�����P��.�t�w�l��l
            //�@@�@@�@@�@@���s�����F�@@�����P��.�iW�w�l�j���s����
            //�@@�@@�@@�@@�iW�w�l�j���s�����F�@@�����P��.�iW�w�l�j���s����
            //�@@�@@�@@�@@�l�i�����F�@@�����P��.�l�i����
            //�@@�@@�@@�@@���������F�@@�����P��.��������
            //�@@�@@�@@�@@�m�F���擾�����F�@@0�i�Œ�j
            //�@@�@@�@@�@@is�X�g�b�v�����L���F�@@true�i�Œ�j�i*�j
            //�@@�@@�@@�@@�⏕�����F�@@get�⏕����()�̖߂�l
            //�@@�@@�@@�@@��������F�@@�����P��.getTradedProduct()�̖߂�l
            //�@@�@@�@@�@@�����F�@@�����P��.��������
            //�@@�@@�@@�@@is�������F�@@is������()�̖߂�l
            //�@@�@@�@@�@@��萔�ʁF�@@�����P��.��萔��
            //�@@�@@�@@�@@���v�����z�F�@@�����P��.���v�����z
            //�@@�@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�Œ�j
            WEB3EquityTradedProduct l_tradedProduct = null;
            if (l_orderUnit.getTradedProduct() != null)
            {
                l_tradedProduct = (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
            }
            WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
                l_orderManager.calcEstimateDeliveryAmount(
                    l_commission,
                    l_orderUnitRow.getWLimitPrice(),
                    l_orderUnitRow.getWLimitPrice(),
                    l_orderUnitRow.getStopOrderPrice(),
                    l_orderUnitRow.getWLimitExecCondType(),
                    l_orderUnitRow.getWLimitExecCondType(),
                    l_orderUnitRow.getPriceConditionType(),
                    l_orderUnitRow.getOrderConditionType(),
                    "0",
                    true,
                    l_subAccount,
                    l_tradedProduct,
                    l_orderUnit.getQuantity(),
                    l_blnSellOrder,
                    l_orderUnit.getExecutedQuantity(),
                    l_orderUnit.getExecutedAmount(),
                    false);

            //�T�Z����v�Z���ʃI�u�W�F�N�g��ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_estimatedDeliveryPrice;
        }
        //�@@�R�|�Q�j�@@�����P��.�����J�e�S����"�V�K������"�̏ꍇ
        else if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
        {
            //�@@�@@�@@�g�����������}�l�[�W��.calc�����������()���R�[������
            //�@@�@@�@@�@@[calc�����������()�̈���]
            //�@@�@@�@@�@@�萔���F�@@�p�����[�^.�萔��
            //�@@�@@�@@�@@�w�l�F�@@�����P��.�iW�w�l�j�����w�l
            //�@@�@@�@@�@@�iW�w�l�j�����w�l�F�@@�����P��.�iW�w�l�j�����w�l
            //�@@�@@�@@�@@�t�w�l��l�F�@@�����P��.�t�w�l��l
            //�@@�@@�@@�@@���s�����F�@@�����P��.�iW�w�l�j���s����
            //�@@�@@�@@�@@�iW�w�l�j���s�����F�@@�����P��.�iW�w�l�j���s����
            //�@@�@@�@@�@@�l�i�����F�@@�����P��.�l�i����
            //�@@�@@�@@�@@���������F�@@�����P��.��������
            //�@@�@@�@@�@@�m�F���擾�����F�@@0�i�Œ�j
            //�@@�@@�@@�@@is�X�g�b�v�����L���F�@@true�i�Œ�j�i*�j
            //�@@�@@�@@�@@is�����F�@@is������()�̖߂�l
            //�@@�@@�@@�@@�⏕�����F�@@get�⏕����()�̖߂�l
            //�@@�@@�@@�@@��������F�@@�����P��.getTradedProduct()�̖߂�l
            //�@@�@@�@@�@@�����F�@@�����P��.��������
            //�@@�@@�@@�@@��萔�ʁF�@@�����P��.��萔��
            //�@@�@@�@@�@@���v�����z�F�@@�����P��.���v�����z
            //�@@�@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�Œ�j
            EqTypeTradedProduct l_tradedProduct = null;
            if (l_orderUnit.getTradedProduct() != null)
            {
                l_tradedProduct = (EqTypeTradedProduct)l_orderUnit.getTradedProduct();
            }
            WEB3EquityEstimatedContractPrice l_equityEstimatedContractPricer =
                l_orderManager.calcContractAmountAtOrder(
                    l_commission,
                    l_orderUnitRow.getWLimitPrice(),
                    l_orderUnitRow.getWLimitPrice(),
                    l_orderUnitRow.getStopOrderPrice(),
                    l_orderUnitRow.getWLimitExecCondType(),
                    l_orderUnitRow.getWLimitExecCondType(),
                    l_orderUnitRow.getPriceConditionType(),
                    l_orderUnitRow.getOrderConditionType(),
                    "0",
                    true,
                    l_blnSellOrder,
                    l_subAccount,
                    l_tradedProduct,
                    l_orderUnit.getQuantity(),
                    l_orderUnit.getExecutedQuantity(),
                    l_orderUnit.getExecutedAmount(),
                    false);

            //�T�Z����v�Z���ʃI�u�W�F�N�g��ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_equityEstimatedContractPricer;
        }
        //�@@�R�|�R�j�@@�����P��.�����J�e�S����"�ԍϒ���"�̏ꍇ
        else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
        {
            //�R�|�R�|�P�j�@@�����|�W�V�����}�l�[�W��.create���ό����G���g��()���R�[������
            //�@@�@@�@@�@@�@@�@@[create���ό����G���g��()�̈���]
            //�@@�@@�@@�@@�@@�@@�����P��ID�F�@@�����P��.�����P��ID
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            EqTypeSettleContractOrderEntry[] l_settleContractOrderEntry =
                l_positionManager.createCloseMarginContractEntry(l_orderUnit.getOrderUnitId());

            //�R�|�R�|�Q�j�@@�p�����[�^.�萔��.setIs�w�l()���R�[������B
            //�@@�@@�@@[setIs�w�l()�̈���]
            //�@@�@@�@@is�w�l�F�@@�����P��.�iW�w�l�j�����w�l��"0"�̏ꍇ�̂݁Atrue�i�w�l�j���Z�b�g
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�Afalse�i���s�j���Z�b�g
            boolean l_blnIsLimitPrice = false;
            if (l_orderUnitRow.getWLimitPrice() != 0)
            {
                l_blnIsLimitPrice = true;
            }
            l_commission.setIsLimitPrice(l_blnIsLimitPrice);

            //�@@�@@�R�|�R�|�Q�j�@@�g�����������}�l�[�W��.calc�T�Z���ϑ��v���()���R�[������
            //�@@�@@�@@�@@�@@�@@[calc�T�Z���ϑ��v���()�̈���]
            //�@@�@@�@@�@@�@@�@@�萔���F�@@�p�����[�^.�萔��
            //�@@�@@�@@�@@�@@�@@�w�l�F�@@�����P��.�iW�w�l�j�����w�l
            //�@@�@@�@@�@@�@@�@@�⏕�����F�@@get�⏕����()�̖߂�l
            //�@@�@@�@@�@@�@@�@@��������F�@@�����P��..getTradedProduct()�̖߂�l
            //�@@�@@�@@�@@�@@�@@���ό����G���g���F�@@create���ό����G���g��()�̖߂�l
            //�@@�@@�@@�@@�@@�@@���ʁF�@@�p�����[�^.�����P��.��������
            //�@@�@@�@@�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
            //�@@�@@�@@�@@�@@�@@�����萔�ʁF�@@0�i�Œ�j
            //�@@�@@�@@�@@�@@�@@������P���F�@@0�i�Œ�j
            //�@@�@@�@@�@@�@@�@@isSkip���z�`�F�b�N�F�@@false�i�Œ�j
            WEB3GentradeSubAccount l_genSubAccount = null;
            if (l_subAccount != null)
            {
                l_genSubAccount = (WEB3GentradeSubAccount)l_subAccount;
            }

            WEB3EquityTradedProduct l_equityTradedProduct = null;
            if (l_orderUnit.getTradedProduct() != null)
            {
                l_equityTradedProduct = (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
            }
            WEB3EquityRealizedProfitAndLossPrice l_realizedProfitAndLossPrice =
                l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    l_orderUnitRow.getWLimitPrice(),
                    l_genSubAccount,
                    l_equityTradedProduct,
                    l_settleContractOrderEntry,
                    l_orderUnit.getQuantity(),
                    l_orderUnit,
                    0,
                    0,
                    false);

            //�T�Z����v�Z���ʃI�u�W�F�N�g��ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_realizedProfitAndLossPrice;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (create�蓮�����萔�����)<BR>
     * �蓮�����萔�����Ƀv���p�e�B��ݒ肷��B<BR>
     * <BR>
     * �P�j�@@�ϑ��萔�����擾����B<BR>
     * �@@�@@�����v�Z�T�[�r�X.calc�ϑ��萔��()���R�[������B<BR>
     * �@@�@@�i���ȉ��A�擾�����萔���I�u�W�F�N�g��"�萔��"�ƕ\�L����j<BR>
     * <BR>
     * �@@�@@[calc�ϑ��萔��()�̈���]<BR>
     * �@@�@@�萔���F�@@�p�����[�^.�萔�� <BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * <BR>
     * �Q�j�@@����ł��擾����B <BR>
     * �@@�@@�����v�Z�T�[�r�X.calc�����()���R�[������B<BR>
     * <BR>
     * �@@�@@[calc�����()�̈���]<BR>
     * �@@�@@���z�F�@@�萔��.�萔�����z <BR>
     * �@@�@@����F�@@�萔��.������ <BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * <BR>
     * �R�j�@@�蓮�����萔�����Ɉȉ��v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�萔���R�[�X�F�@@�萔��.get�萔���R�[�X()�̖߂�l<BR>
     * �@@�@@�萔���F�@@�萔��.get�萔�����z()�̖߂�l <BR>
     * �@@�@@�萔������ŁF�@@calc�����()�̖߂�l <BR>
     * <BR>
     * �S�j�@@�蓮�����萔������ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_commission - (�萔��)
     * @@return WEB3ManualCommissionInfoUnit
     * @@throws WEB3BaseException
     */
    protected WEB3ManualCommissionInfoUnit createManualCommissionInfoUnit(
        SubAccount l_subAccount,
        WEB3GentradeCommission l_commission
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createManualCommissionInfoUnit(SubAccount, WEB3GentradeCommission)";
        log.entering(STR_METHOD_NAME);

        if (l_commission == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //    �P�j�@@�ϑ��萔�����擾����
        //  �@@�@@�����v�Z�T�[�r�X.calc�ϑ��萔��()���R�[������
        //  �@@�@@�i���ȉ��A�擾�����萔���I�u�W�F�N�g��"�萔��"�ƕ\�L����j
        //  �@@�@@[calc�ϑ��萔��()�̈���]
        //  �@@�@@�萔���F�@@�p�����[�^.�萔��
        //  �@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_eqBizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        l_eqBizLogicProvider.calcCommission(l_commission, l_subAccount);

        //  �Q�j�@@����ł��擾����
        //  �@@�@@�����v�Z�T�[�r�X.calc�����()���R�[������
        //  �@@�@@[calc�����()�̈���]
        //  �@@�@@���z�F�@@�萔��.�萔�����z
        //  �@@�@@����F�@@�萔��.������
        //  �@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        double l_dblSalesTax = l_eqBizLogicProvider.calcSalesTax(
            l_commission.getCommission(),
            l_commission.getOrderBizDate(),
            l_subAccount);

        //  �R�j�@@�蓮�����萔�����Ɉȉ��v���p�e�B�ɃZ�b�g����
        //  �@@�@@�萔���R�[�X�F�@@�萔��.get�萔���R�[�X()�̖߂�l
        //  �@@�@@�萔���F�@@�萔��.get�萔�����z()�̖߂�l
        //  �@@�@@�萔������ŁF�@@calc�����()�̖߂�l
        WEB3ManualCommissionInfoUnit l_manualCommissionInfoUnit =
            new WEB3ManualCommissionInfoUnit();
        l_manualCommissionInfoUnit.commissionCourse =
            l_commission.getCommissionCourseDiv();

        l_manualCommissionInfoUnit.commission =
            WEB3StringTypeUtility.formatNumber(l_commission.getCommission());

        l_manualCommissionInfoUnit.commissionConsumptionTax =
            WEB3StringTypeUtility.formatNumber(l_dblSalesTax);

        //  �S�j�@@�蓮�����萔������ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_manualCommissionInfoUnit;
    }

    /**
     * ���[���G���W������̒ʒm�e�[�u���ւ�INSERT���\�b�h���R�[������B<BR>
     * <BR>
     * �P�j�@@�⏕�������擾����B<BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W���[.get�⏕����()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[getSubAccount()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�ڋqID�F�@@�����f�[�^.getAccountId()�̖߂�l<BR>
     * �@@�@@�@@�@@�⏕����ID�F�@@�����f�[�^.getSubAccountId()�̖߂�l <BR>
     * <BR>
     * �Q�j�@@���[���G���W������̒ʒm�e�[�u����INSERT����B<BR>
     * �@@�@@�@@WEB3RlsRequestSenderServiceImpl.sendManualSubmitConOrder()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[sendManualSubmitConOrder()�Ɏw�肷�����]  <BR>
     * �@@�@@�@@�@@�����҃��O�C��ID�F�@@�p�����[�^.�����҃��O�C��ID  <BR>
     * �@@�@@�@@�@@�ʒm�o�H�F�@@�p�����[�^.�ʒm�o�H  <BR>
     * �@@�@@�@@�@@�⏕�����F�@@getSubAccount()�̖߂�l  <BR>
     * �@@�@@�@@�@@�����t�����^�C�v�F�@@�����������."W�w�l����"  <BR>
     * �@@�@@�@@�@@�����̖����^�C�v�F�@@�����^�C�v."����"  <BR>
     * �@@�@@�@@�@@����ID�F�@@�����f�[�^.����ID  <BR>
     * �@@�@@�@@�@@�e�����̖����^�C�v�F�@@null  <BR>
     * �@@�@@�@@�@@�e�����̒���ID�F�@@null  <BR>
     * �@@�@@�@@�@@�������ԁF�@@0<BR>
     * <BR>
     * @@param l_orderUnit - (�����f�[�^)
     * @@param l_submitterLoginId - (�����҃��O�C��ID)
     * @@param l_strNotifyType - (�ʒm�o�H)
     * @@throws WEB3BaseException
     */
    protected void sendRLSRequest(
        EqTypeOrderUnit l_orderUnit,
        Long l_submitterLoginId,
        String l_strNotifyType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "sendRLSRequest(EqTypeOrderUnit, Long, String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P�j�@@�⏕�������擾����
        //�@@�@@�@@�g���A�J�E���g�}�l�[�W���[.get�⏕����()���R�[������
        //�@@�@@�@@�@@[getSubAccount()�Ɏw�肷�����]
        //        �ڋqID�F�@@�����f�[�^.getAccountId()�̖߂�l
        //�@@�@@�@@�@@�⏕����ID�F�@@�����f�[�^.getSubAccountId()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        SubAccount l_subAccount = null;

        try
        {
            l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�⏕�����e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@���[���G���W������̒ʒm�e�[�u����INSERT����
        //�@@�@@�@@WEB3RlsRequestSenderServiceImpl.sendManualSubmitConOrder()���R�[������
        //�@@�@@�@@�@@[sendManualSubmitConOrder()�Ɏw�肷�����]
        //�@@�@@�@@�@@�����҃��O�C��ID�F�@@�p�����[�^.�����҃��O�C��ID
        //�@@�@@�@@�@@�ʒm�o�H�F�@@�p�����[�^.�ʒm�o�H
        //�@@�@@�@@�@@�⏕�����F�@@getSubAccount()�̖߂�l
        //�@@�@@�@@�@@�����t�����^�C�v�F�@@�����������."W�w�l����"
        //�@@�@@�@@�@@�����̖����^�C�v�F�@@�����^�C�v."����"
        //�@@�@@�@@�@@����ID�F�@@�����f�[�^.����ID
        //�@@�@@�@@�@@�e�����̖����^�C�v�F�@@null
        //�@@�@@�@@�@@�e�����̒���ID�F�@@null
        //�@@�@@�@@�@@�������ԁF�@@0
        WEB3RlsRequestSenderService l_requestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(WEB3RlsRequestSenderService.class);
        l_requestSenderService.sendManualSubmitConOrder(
            l_submitterLoginId,
            l_strNotifyType,
            l_subAccount,
            Integer.parseInt(WEB3TriggerOrderTypeDef.W_LlIMIT),
            ProductTypeEnum.EQUITY,
            new Long(l_orderUnit.getOrderId()),
            null,
            null,
            0);

        log.exiting(STR_METHOD_NAME);
    }
}
@
