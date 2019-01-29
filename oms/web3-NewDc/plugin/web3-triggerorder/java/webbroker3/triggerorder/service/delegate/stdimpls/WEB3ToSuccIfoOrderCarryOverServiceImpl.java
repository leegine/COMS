head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccIfoOrderCarryOverServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�A�������J�z�T�[�r�XImpl�iWEB3ToSuccIfoOrderCarryOverServiceImpl.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 ���� (���u) �V�K�쐬 ���f��No.276
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccIfoOrderCarryOverService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�敨OP�A�������J�z�T�[�r�XImpl)<BR>
 * �敨OP�A�������J�z�T�[�r�X�����N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3ToSuccIfoOrderCarryOverServiceImpl
    implements WEB3ToSuccIfoOrderCarryOverService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3ToSuccIfoOrderCarryOverServiceImpl.class);

    /**
     * @@roseuid 47FDBE3F02FD
     */
    public WEB3ToSuccIfoOrderCarryOverServiceImpl()
    {

    }

    /**
     * (exec�A�������J�z)<BR>
     * �A�������̌J�z�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A�������J�z�jexec�A�������J�z�v�Q�ƁB
     * @@param l_carryOverOriginalParentOrderUnit - (�J�z���̐e�����̒����P��)<BR>
     * �J�z���̐e�����̒����P��<BR>
     * @@param l_carryOverAfterParentOrderUnit - (�J�z��̐e�����̒����P��)<BR>
     * �J�z��̐e�����̒����P��<BR>
     * @@param l_lisRsvIfoOrderUnits - (�\�񒍕��P�ʈꗗ)<BR>
     * �\�񒍕��P�ʈꗗ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D103F6021B
     */
    public void execToSuccOrderCarryOver(
        IfoOrderUnit l_carryOverOriginalParentOrderUnit,
        IfoOrderUnit l_carryOverAfterParentOrderUnit,
        List l_lisRsvIfoOrderUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execToSuccOrderCarryOver(IfoOrderUnit, IfoOrderUnit, List)";
        log.entering(STR_METHOD_NAME);
        if (l_lisRsvIfoOrderUnits == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        //OP�����}�l�[�W��
        WEB3OptionOrderManagerImpl l_optionOrderManager =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getOrderManager();
        // �A�������}�l�[�W��Impl���擾
        WEB3ToSuccOrderManagerImpl l_toSuccOrderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // �敨OP�\�񒍕��X�V�T�[�r�X���擾����B
        WEB3ToSuccReservationIfoOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        // �J�z�����\�񒍕��P��
        WEB3ToSuccIfoOrderUnitImpl l_afterToSuccIfoOrderUnit = null;
        boolean l_blnIsSetReserveOrderExistFlag = false;
        int l_intIfoOrderUnitsCnt = l_lisRsvIfoOrderUnits.size();
        for (int i = 0; i < l_intIfoOrderUnitsCnt; i++)
        {
            // get�敨OP�\�񒍕��P��
            // �敨OP�\�񒍕��P�ʂ��擾����B
            // [����]
            // ����ID�F�@@�����Ώۂ̗v�f.����ID
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit = l_toSuccOrderManager.getReserveIfoOrderUnit(
                ((RsvIfoOrderUnitRow)l_lisRsvIfoOrderUnits.get(i)).getOrderId());

            OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;

            // is�J�z�Ώۗ\�񒍕�
            // �J�z�Ώے����ł��邩���肷��B
            //  [����]
            //   �����P�ʁF�@@get�敨OP�\�񒍕��P��()�̖߂�l
            boolean l_blnIsCarryOverReserve =
                l_toSuccOrderManager.isCarryoverReserveIfoOrderUnit(l_toSuccIfoOrderUnit);

            // �J�z�ΏۊO����(is�J�z�Ώے���() == false)�̏ꍇ
            if (!l_blnIsCarryOverReserve)
            {

                RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
                    (RsvIfoOrderUnitRow)l_toSuccIfoOrderUnit.getDataSourceObject();
                RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams(l_rsvIfoOrderUnitRow);

                // �����G���[���R�R�[�h��"���̑��G���["���Z�b�g����
                l_rsvIfoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.OTHRE_ERROR);

                // update�\�񒍕��f�[�^
                // [����]
                // �敨OP�\�񒍕��P�ʍs�F�@@get�敨OP�\�񒍕��P��()�̖߂�l.getDataSourceObject()(*)
                // �敨OP�\�񒍕������s�F�@@null
                // (*)�����G���[���R�R�[�h��"���̑��G���["���Z�b�g����B
                l_orderUpdateService.updateReserveOrderData(
                    l_rsvIfoOrderUnitParams, null);
                continue;
            }

            // ����ID���擾����B
            long l_lngNewOrderId = l_optionOrderManager.createNewOrderId();
            try
            {
                // �����Ώۂ̗\�񒍕�.�����J�e�S�� == "OP�V�K������"�̏ꍇ
                if (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_toSuccIfoOrderUnit.getOrderCateg()))
                {
                    l_orderManagerPersistenceContext = OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER;
                    // �I�v�V�����V�K�������̓o�^�������s���B
                    // insertOP�V�K���J�z����
                    // [����]
                    //  ����ID�F�@@createNewOrderId()�̖߂�l
                    //  �J�z���\�񒍕��P�ʁF�@@get�敨OP�\�񒍕��P��()�̖߂�l
                    //  �J�z��̐e�����P�ʁF�@@�p�����[�^.�J�z��̐e�����P��
                    this.insertOptionOpenContractCarryOrder(
                        l_lngNewOrderId, l_toSuccIfoOrderUnit, l_carryOverAfterParentOrderUnit);
                    l_blnIsSetReserveOrderExistFlag = true;
                }
                // �����Ώۂ̗\�񒍕�.�����J�e�S�� == "OP�ԍϒ���"�̏ꍇ
                else if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_toSuccIfoOrderUnit.getOrderCateg()))
                {
                    l_orderManagerPersistenceContext = OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER;
                    // �I�v�V�����ԍϒ����̓o�^�������s���B
                    // insertOP�V�K���J�z����
                    // [����]
                    //  ����ID�F�@@createNewOrderId()�̖߂�l
                    //  �J�z���\�񒍕��P�ʁF�@@get�敨OP�\�񒍕��P��()�̖߂�l
                    //  �J�z��̐e�����P�ʁF�@@�p�����[�^.�J�z��̐e�����P��
                    this.insertOptionCloseContractCarryOrder(
                        l_lngNewOrderId, l_toSuccIfoOrderUnit, l_carryOverAfterParentOrderUnit);
                    l_blnIsSetReserveOrderExistFlag = true;
                }
                // �����Ώۂ̗\�񒍕�.�����J�e�S�� == "�敨�V�K������"�̏ꍇ
                else if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_toSuccIfoOrderUnit.getOrderCateg()))
                {
                    l_orderManagerPersistenceContext = OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER;
                    // �敨�V�K�������̓o�^�������s���B
                    // [����]
                    // �@@����ID�F�@@createNewOrderId()�̖߂�l
                    // �@@�J�z���\�񒍕��P�ʁF�@@get�敨OP�\�񒍕��P��()�̖߂�l
                    // �@@�J�z��̐e�����P�ʁF�@@�p�����[�^.�J�z��̐e�����P��

                    this.insertFuturesOpenContractCarryOrder(
                        l_lngNewOrderId, l_toSuccIfoOrderUnit, l_carryOverAfterParentOrderUnit);
                    l_blnIsSetReserveOrderExistFlag = true;
                }
                // �����Ώۂ̗\�񒍕�.�����J�e�S�� == "�敨�ԍϒ���"�̏ꍇ
                else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_toSuccIfoOrderUnit.getOrderCateg()))
                {
                    l_orderManagerPersistenceContext = OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER;
                    // �敨�ԍϒ����̓o�^�������s���B�B
                    // [����]
                    // �@@����ID�F�@@createNewOrderId()�̖߂�l
                    // �@@�J�z���\�񒍕��P�ʁF�@@get�敨OP�\�񒍕��P��()�̖߂�l
                    // �@@�J�z��̐e�����P�ʁF�@@�p�����[�^.�J�z��̐e�����P��
                    this.insertFuturesCloseContractCarryOrder(
                        l_lngNewOrderId, l_toSuccIfoOrderUnit, l_carryOverAfterParentOrderUnit);
                    l_blnIsSetReserveOrderExistFlag = true;
                }
            }
            // insert�������ɋƖ��G���[�����������ꍇ
            catch (WEB3BaseException l_ex)
            {
                RsvIfoOrderUnitRow l_beforeRsvIfoOrderUnitRow =
                    (RsvIfoOrderUnitRow)l_toSuccIfoOrderUnit.getDataSourceObject();
                RsvIfoOrderUnitParams l_beforeRsvIfoOrderUnitParams =
                    new RsvIfoOrderUnitParams(l_beforeRsvIfoOrderUnitRow);

                // get�����G���[���R�R�[�h
                String l_strErrorCode =
                    l_optionOrderManager.getErrorReasonCode(l_ex.getErrorInfo().getErrorCode());

                // �����G���[���R�R�[�h��get�����G���[���R�R�[�h()�̖߂�l���Z�b�g����B
                l_beforeRsvIfoOrderUnitParams.setErrorReasonCode(l_strErrorCode);

                // update�\�񒍕��f�[�^
                // [����]
                // �敨OP�\�񒍕��P�ʍs�F�@@get�敨OP�\�񒍕��P��()�̖߂�l.getDataSourceObject()
                // �敨OP�\�񒍕������s�F�@@null
                l_orderUpdateService.updateReserveOrderData(
                    l_beforeRsvIfoOrderUnitParams, null);

                continue;
            }
            // get�敨OP�\�񒍕��P��
            // �J�z�����\�񒍕��P�ʂ��擾����B
            //�@@[����]
            //�@@�@@����ID�F�@@createNewOrderId()�̖߂�l
            l_afterToSuccIfoOrderUnit =
                l_toSuccOrderManager.getReserveIfoOrderUnit(l_lngNewOrderId);

            // notify���[���G���W���T�[�o
            // [����]
            // �����P�ʁF�@@get�敨OP�\�񒍕��P��()�̖߂�l(*)
            // �����F
            // �@@[�����Ώۂ̗v�f.�����J�e�S�� == "�敨�V�K������" or "OP�V�K������"�̏ꍇ]
            // �@@�@@NEW_OPEN_CONTRACT_ORDER
            //  �@@[�����Ώۂ̗v�f.�����J�e�S�� == "�敨�ԍϒ���" or "OP�ԍϒ���"�̏ꍇ]
            //�@@�@@NEW_SETTLE_CONTRACT_ORDER
            l_optionOrderManager.notifyRLS(l_afterToSuccIfoOrderUnit, l_orderManagerPersistenceContext);
        }

        // set�\�񒍕��ݒ�To�敨OP�e����
        // [����]
        // �e�����̒����P�ʁF�@@�J�z��̐e�����P��
        if (l_blnIsSetReserveOrderExistFlag)
        {
            l_toSuccOrderManager.setReserveOrderSettingToIfoParentOrder(l_carryOverAfterParentOrderUnit);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert�敨�V�K���J�z����)<BR>
     * �敨�V�K�������̓o�^�������s���B<BR>
     * <BR>
     * �P�j�@@DB�ɌJ�z�����̓o�^���s���B<BR>
     * �@@�A�������}�l�[�W��.submit�敨OP�V�K���J�z�\�񒍕�()��call����B<BR>
     * �@@[����]<BR>
     * �@@�@@����ID�F�@@�p�����[�^.����ID<BR>
     * �@@�@@�J�z���\�񒍕��P�ʁF�@@�p�����[�^.�J�z���\�񒍕��P��<BR>
     * �@@�@@�J�z��̐e�����P�ʁF�@@�p�����[�^.�J�z��̐e�����P��<BR>
     * @@param l_lngOrderID - (����ID)<BR>
     * ����ID<BR>
     * @@param l_carryOverOriginalToSuccIfoOrderUnit - (�J�z���̗\�񒍕��P��)<BR>
     * �J�z���̗\�񒍕��P��<BR>
     * @@param l_carryOverAfterParentOrderUnit - (�J�z��̐e�����P��)<BR>
     * �J�z��̐e�����P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D12CDA00ED
     */
    public void insertFuturesOpenContractCarryOrder(
        long l_lngOrderID,
        WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit,
        IfoOrderUnit l_carryOverAfterParentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertFuturesOpenContractCarryOrder(long, WEB3ToSuccIfoOrderUnitImpl, IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        // �A�������}�l�[�W��Impl���擾
        WEB3ToSuccOrderManagerImpl l_toSuccOrderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // �P�j DB�ɌJ�z�����̓o�^���s���B
        // �A�������}�l�[�W��.submit�敨OP�V�K���J�z�\�񒍕�()��call����B
        // [����]
        //   ����ID�F�@@�p�����[�^.����ID
        //   �J�z���\�񒍕��P�ʁF�@@�p�����[�^.�J�z���\�񒍕��P��
        //   �J�z��̐e�����P�ʁF�@@�p�����[�^.�J�z��̐e�����P��
        l_toSuccOrderManager.submitIfoOpenContractCarryReserveOrder(
            l_lngOrderID, l_carryOverOriginalToSuccIfoOrderUnit, l_carryOverAfterParentOrderUnit);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertOP�V�K���J�z����)<BR>
     * �I�v�V�����V�K�������̓o�^�������s���B<BR>
     * <BR>
     * �P�j�@@DB�ɌJ�z�����̓o�^���s���B<BR>
     * �@@�A�������}�l�[�W��.submit�敨OP�V�K���J�z�\�񒍕�()��call����B<BR>
     * �@@[����]<BR>
     * �@@�@@����ID�F�@@�p�����[�^.����ID<BR>
     * �@@�@@�J�z���\�񒍕��P�ʁF�@@�p�����[�^.�J�z���\�񒍕��P��<BR>
     * �@@�@@�J�z��̐e�����P�ʁF�@@�p�����[�^.�J�z��̐e�����P��<BR>
     * @@param l_lngOrderID - (����ID)<BR>
     * ����ID<BR>
     * @@param l_carryOverOriginalToSuccIfoOrderUnit - (�J�z���̗\�񒍕��P��)<BR>
     * �J�z���̗\�񒍕��P��<BR>
     * @@param l_carryOverAfterParentOrderUnit - (�J�z��̐e�����P��)<BR>
     * �J�z��̐e�����P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D60C450046
     */
    public void insertOptionOpenContractCarryOrder(
        long l_lngOrderID,
        WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit,
        IfoOrderUnit l_carryOverAfterParentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertOptionOpenContractCarryOrder(long, WEB3ToSuccIfoOrderUnitImpl, IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        // �A�������}�l�[�W��Impl���擾
        WEB3ToSuccOrderManagerImpl l_toSuccOrderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // �P�j DB�ɌJ�z�����̓o�^���s���B
        // �A�������}�l�[�W��.submit�敨OP�V�K���J�z�\�񒍕�()��call����B
        // [����]
        //   ����ID�F�@@�p�����[�^.����ID
        //   �J�z���\�񒍕��P�ʁF�@@�p�����[�^.�J�z���\�񒍕��P��
        //   �J�z��̐e�����P�ʁF�@@�p�����[�^.�J�z��̐e�����P��
        l_toSuccOrderManager.submitIfoOpenContractCarryReserveOrder(
            l_lngOrderID, l_carryOverOriginalToSuccIfoOrderUnit, l_carryOverAfterParentOrderUnit);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert�敨�ԍόJ�z����)<BR>
     * �敨�ԍϒ����̓o�^�������s���B<BR>
     * <BR>
     * �P�j�@@�ԍό��ʃG���g�����쐬����B<BR>
     * �@@�P�|�P�j�@@���Δ����̏ꍇ<BR>
     * �@@�@@�i�p�����[�^.�J�z���\�񒍕��P��.is���Δ������() == true�j<BR>
     * <BR>
     * �@@�@@�P�|�P�|�P�j�@@�ԍό��ʂ��쐬����B<BR>
     * �@@�@@�@@�ԍό��ʃC���X�^���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�E�ԍό���.�������ʁF�@@�p�����[�^.�J�z���\�񒍕��P��.��������<BR>
     * �@@�@@�P�|�P�|�Q�j�@@�ԍό��ʃG���g�����쐬����B<BR>
     * �@@�@@�@@�A�������}�l�[�W��Impl.create�ԍό��ʃG���g��()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[����]  <BR>
     * �@@�@@�@@�@@�ԍό��ʁF�@@�P�|�P�|�P�j�̖߂�l <BR>
     * <BR>
     * <BR>
     * �@@�P�|�Q�j�@@�P�|�P�j�ȊO�̏ꍇ<BR>
     * �@@�@@�P�|�Q�|�P�j�@@�\�񌚋ʕԍώw������擾����B<BR>
     * �@@�@@�@@�p�����[�^.�J�z���\�񒍕��P��.����ID�ɊY������<BR>
     * �@@�@@�@@�\�񌚋ʕԍώw������擾����B<BR>
     * �@@�@@�@@�@@���ԍϘA�Ԃ̏���sort�w��<BR>
     * <BR>
     * �@@�@@�P�|�Q�|�Q�j�@@�P�|�Q�|�P�j�̗v�f����LOOP����<BR>
     * �@@�@@�@@�ԍό��ʃG���g���𐶐����A�v�f�����̔z����쐬����B<BR>
     * <BR>
     * �@@�@@�@@�@@[�R���X�g���N�^����]<BR>
     * �@@�@@�@@�@@�@@arg0�i=����ID�j�F�@@�����Ώۂ̗v�f.����ID<BR>
     * �@@�@@�@@�@@�@@arg1�i=���ʁj�F�@@�����Ώۂ̗v�f.�ԍϒ�������<BR>
     * <BR>
     * <BR>
     * �Q�j�@@DB�ɌJ�z�����̓o�^���s���B<BR>
     * �@@�A�������}�l�[�W��.submit�敨OP�ԍόJ�z�\�񒍕�()��call����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@����ID�F�@@�p�����[�^.����ID<BR>
     * �@@�@@�J�z���\�񒍕��P�ʁF�@@�p�����[�^.�J�z���\�񒍕��P��<BR>
     * �@@�@@�J�z��̐e�����P�ʁF�@@�p�����[�^.�J�z��̐e�����P��<BR>
     * �@@�@@�ԍό��ʃG���g���F�@@�P�j�Ő��������ԍό��ʃG���g��<BR>
     * @@param l_lngOrderID - (����ID)<BR>
     * ����ID<BR>
     * @@param l_carryOverOriginalToSuccIfoOrderUnit - (�J�z���̗\�񒍕��P��)<BR>
     * �J�z���̗\�񒍕��P��<BR>
     * @@param l_carryOverAfterParentOrderUnit - (�J�z��̐e�����P��)<BR>
     * �J�z��̐e�����P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D60C76020F
     */
    public void insertFuturesCloseContractCarryOrder(
        long l_lngOrderID,
        WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit,
        IfoOrderUnit l_carryOverAfterParentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertFuturesCloseContractCarryOrder(" +
            "long, WEB3ToSuccIfoOrderUnitImpl, IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        SettleContractEntry[] l_settleContractEntries = null;

        // �A�������}�l�[�W��Impl���擾
        WEB3ToSuccOrderManagerImpl l_toSuccorderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // �P�j�@@�ԍό��ʃG���g�����쐬����B
        //  �P�|�P�j�@@���Δ����̏ꍇ
        // �@@�i�p�����[�^.�J�z���\�񒍕��P��.is���Δ������() == true�j
        if (l_carryOverOriginalToSuccIfoOrderUnit.isReversingTrade())
        {
            // �P�|�P�|�P�j�@@�ԍό��ʂ��쐬����B
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];

            l_closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();

            // �E�ԍό���.�������ʁF�@@�p�����[�^.�J�z���\�񒍕��P��.��������
            l_closeMarginContractUnits[0].contractOrderQuantity = WEB3StringTypeUtility.formatNumber(
                l_carryOverOriginalToSuccIfoOrderUnit.getQuantity());
            // �P�|�P�|�Q�j�@@�ԍό��ʃG���g�����쐬����B
            // �A�������}�l�[�W��Impl.create�ԍό��ʃG���g��()���R�[������B
            // [����]
            //   �ԍό��ʁF�@@�P�|�P�|�P�j�̖߂�l
            l_settleContractEntries =
                l_toSuccorderManager.createSettleContractEntry(l_closeMarginContractUnits);
        }
        // �P�|�Q�j�@@���Δ�������ȊO�̏ꍇ
        else
        {
            try
            {
                // �P�|�Q�|�P�j�@@�\�񌚋ʕԍώw������擾����B
                // �p�����[�^.�J�z���\�񒍕��P��.����ID�ɊY������
                // �\�񌚋ʕԍώw������擾����B
                // ���ԍϘA�Ԃ̏���sort�w��
                String l_strWhere = " order_id = ? ";
                Object[] l_bindVars = {new Long(l_carryOverOriginalToSuccIfoOrderUnit.getOrderId())};
                String l_strSort = "closing_serial_no asc";

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisRsvIfoClosingContractSpecs = l_queryProcessor.doFindAllQuery(
                    RsvIfoClosingContractSpecRow.TYPE,
                    l_strWhere,
                    l_strSort,
                    null,
                    l_bindVars);

                // �P�|�Q�|�Q�j�@@�P�|�Q�|�P�j�̗v�f����LOOP����
                int l_intRsvIfoClosingContractSpecsCnt = l_lisRsvIfoClosingContractSpecs.size();
                l_settleContractEntries = new SettleContractEntry[l_intRsvIfoClosingContractSpecsCnt];
                for (int i = 0; i < l_intRsvIfoClosingContractSpecsCnt; i++)
                {
                    // �ԍό��ʃG���g���𐶐����A�v�f�����̔z����쐬����B
                    // [�R���X�g���N�^����]
                    //   arg0�i=����ID�j�F�@@�����Ώۂ̗v�f.����ID
                    //   arg1�i=���ʁj�F�@@�����Ώۂ̗v�f.�ԍϒ�������

                    l_settleContractEntries[i] = new SettleContractEntry(
                        ((RsvIfoClosingContractSpecRow)l_lisRsvIfoClosingContractSpecs.get(i)).getContractId(),
                        ((RsvIfoClosingContractSpecRow)l_lisRsvIfoClosingContractSpecs.get(i)).getQuantity());
                }
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        // �Q�j�@@DB�ɌJ�z�����̓o�^���s���B
        // �A�������}�l�[�W��.submit�敨OP�ԍόJ�z�\�񒍕�()��call����B
        // [����]
        // ����ID�F�@@�p�����[�^.����ID
        // �J�z���\�񒍕��P�ʁF�@@�p�����[�^.�J�z���\�񒍕��P��
        // �J�z��̐e�����P�ʁF�@@�p�����[�^.�J�z��̐e�����P��
        // �ԍό��ʃG���g���F�@@�P�j�Ő��������ԍό��ʃG���g��
        l_toSuccorderManager.submitIfoCloseContractCarryReserveOrder(
            l_lngOrderID,
            l_carryOverOriginalToSuccIfoOrderUnit,
            l_carryOverAfterParentOrderUnit,
            l_settleContractEntries);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertOP�ԍόJ�z����)<BR>
     * �I�v�V�����ԍϒ����̓o�^�������s���B<BR>
     * <BR>
     * �P�j�@@�ԍό��ʃG���g�����쐬����B<BR>
     * �@@�P�|�P�j�@@���Δ����̏ꍇ<BR>
     * �@@�@@�i�p�����[�^.�J�z���\�񒍕��P��.is���Δ������() == true�j<BR>
     * <BR>
     * �@@�@@�P�|�P�|�P�j�@@�ԍό��ʂ��쐬����B<BR>
     * �@@�@@�@@�ԍό��ʃC���X�^���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�E�ԍό���.�������ʁF�@@�p�����[�^.�J�z���\�񒍕��P��.��������<BR>
     * �@@�@@�P�|�P�|�Q�j�@@�ԍό��ʃG���g�����쐬����B<BR>
     * �@@�@@�@@�A�������}�l�[�W��Impl.create�ԍό��ʃG���g��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[����]  <BR>
     * �@@�@@�@@�@@�ԍό��ʁF�@@�P�|�P�|�P�j�̖߂�l <BR>
     * <BR>
     * <BR>
     * �@@�P�|�Q�j�@@�P�|�P�j�ȊO�̏ꍇ<BR>
     * �@@�@@�P�|�Q�|�P�j�@@�\�񌚋ʕԍώw������擾����B<BR>
     * �@@�@@�@@�p�����[�^.�J�z���\�񒍕��P��.����ID�ɊY������<BR>
     * �@@�@@�@@�\�񌚋ʕԍώw������擾����B<BR>
     * �@@�@@�@@�@@���ԍϘA�Ԃ̏���sort�w��<BR>
     * <BR>
     * �@@�@@�P�|�Q�|�Q�j�@@�P�|�Q�|�P�j�̗v�f����LOOP����<BR>
     * �@@�@@�@@�ԍό��ʃG���g���𐶐����A�v�f�����̔z����쐬����B<BR>
     * <BR>
     * �@@�@@�@@�@@[�R���X�g���N�^����]<BR>
     * �@@�@@�@@�@@�@@arg0�i=����ID�j�F�@@�����Ώۂ̗v�f.����ID<BR>
     * �@@�@@�@@�@@�@@arg1�i=���ʁj�F�@@�����Ώۂ̗v�f.�ԍϒ�������<BR>
     * <BR>
     * <BR>
     * �Q�j�@@DB�ɌJ�z�����̓o�^���s���B<BR>
     * �@@�A�������}�l�[�W��.submit�敨OP�ԍόJ�z�\�񒍕�()��call����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@����ID�F�@@�p�����[�^.����ID<BR>
     * �@@�@@�J�z���\�񒍕��P�ʁF�@@�p�����[�^.�J�z���\�񒍕��P��<BR>
     * �@@�@@�J�z��̐e�����P�ʁF�@@�p�����[�^.�J�z��̐e�����P��<BR>
     * �@@�@@�ԍό��ʃG���g���F�@@�P�j�Ő��������ԍό��ʃG���g��<BR>
     * @@param l_lngOrderID - (����ID)<BR>
     * ����ID<BR>
     * @@param l_carryOverOriginalToSuccIfoOrderUnit - (�J�z���̗\�񒍕��P��)<BR>
     * �J�z���̗\�񒍕��P��<BR>
     * @@param l_carryOverAfterParentOrderUnit - (�J�z��̐e�����P��)<BR>
     * �J�z��̐e�����P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D875AD00E9
     */
    public void insertOptionCloseContractCarryOrder(
        long l_lngOrderID,
        WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit,
        IfoOrderUnit l_carryOverAfterParentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertOptionCloseContractCarryOrder(" +
            "long, WEB3ToSuccIfoOrderUnitImpl, IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        SettleContractEntry[] l_settleContractEntries = null;

        // �A�������}�l�[�W��Impl���擾
        WEB3ToSuccOrderManagerImpl l_toSuccorderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        // �P�j�@@�ԍό��ʃG���g�����쐬����B
        //  �P�|�P�j�@@���Δ����̏ꍇ
        // �@@�i�p�����[�^.�J�z���\�񒍕��P��.is���Δ������() == true�j
        if (l_carryOverOriginalToSuccIfoOrderUnit.isReversingTrade())
        {
            // �P�|�P�|�P�j�@@�ԍό��ʂ��쐬����B
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnit =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];

            l_closeMarginContractUnit[0] = new WEB3FuturesOptionsCloseMarginContractUnit();

            // �E�ԍό���.�������ʁF�@@�p�����[�^.�J�z���\�񒍕��P��.��������
            l_closeMarginContractUnit[0].contractOrderQuantity = WEB3StringTypeUtility.formatNumber(
                l_carryOverOriginalToSuccIfoOrderUnit.getQuantity());
            // �P�|�P�|�Q�j�@@�ԍό��ʃG���g�����쐬����B
            // �A�������}�l�[�W��Impl.create�ԍό��ʃG���g��()���R�[������B
            // [����]
            //   �ԍό��ʁF�@@�P�|�P�|�P�j�̖߂�l
            l_settleContractEntries =
                l_toSuccorderManager.createSettleContractEntry(l_closeMarginContractUnit);
        }
        // �P�|�Q�j�@@���Δ�������ȊO�̏ꍇ
        else
        {
            try
            {
                // �P�|�Q�|�P�j�@@�\�񌚋ʕԍώw������擾����B
                // �p�����[�^.�J�z���\�񒍕��P��.����ID�ɊY������
                // �\�񌚋ʕԍώw������擾����B
                // ���ԍϘA�Ԃ̏���sort�w��
                String l_strWhere = " order_id = ? ";
                String l_strSort = "closing_serial_no asc";
                Object[] l_bindVars = {new Long(l_carryOverOriginalToSuccIfoOrderUnit.getOrderId())};

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisRsvIfoClosingContractSpecs = l_queryProcessor.doFindAllQuery(
                    RsvIfoClosingContractSpecRow.TYPE,
                    l_strWhere,
                    l_strSort,
                    null,
                    l_bindVars);

                // �P�|�Q�|�Q�j�@@�P�|�Q�|�P�j�̗v�f����LOOP����
                int l_intRsvIfoClosingContractSpecsCnt = l_lisRsvIfoClosingContractSpecs.size();
                l_settleContractEntries = new SettleContractEntry[l_intRsvIfoClosingContractSpecsCnt];
                for (int i = 0; i < l_intRsvIfoClosingContractSpecsCnt; i++)
                {
                    // �ԍό��ʃG���g���𐶐����A�v�f�����̔z����쐬����B
                    // [�R���X�g���N�^����]
                    //   arg0�i=����ID�j�F�@@�����Ώۂ̗v�f.����ID
                    //   arg1�i=���ʁj�F�@@�����Ώۂ̗v�f.�ԍϒ�������

                    l_settleContractEntries[i] = new SettleContractEntry(
                        ((RsvIfoClosingContractSpecRow)l_lisRsvIfoClosingContractSpecs.get(i)).getContractId(),
                        ((RsvIfoClosingContractSpecRow)l_lisRsvIfoClosingContractSpecs.get(i)).getQuantity());
                }
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        // �Q�j�@@DB�ɌJ�z�����̓o�^���s���B
        // �A�������}�l�[�W��.submit�敨OP�ԍόJ�z�\�񒍕�()��call����B
        // [����]
        // ����ID�F�@@�p�����[�^.����ID
        // �J�z���\�񒍕��P�ʁF�@@�p�����[�^.�J�z���\�񒍕��P��
        // �J�z��̐e�����P�ʁF�@@�p�����[�^.�J�z��̐e�����P��
        // �ԍό��ʃG���g���F�@@�P�j�Ő��������ԍό��ʃG���g��
        l_toSuccorderManager.submitIfoCloseContractCarryReserveOrder(
            l_lngOrderID,
            l_carryOverOriginalToSuccIfoOrderUnit,
            l_carryOverAfterParentOrderUnit,
            l_settleContractEntries);

        log.exiting(STR_METHOD_NAME);
    }
}
@
