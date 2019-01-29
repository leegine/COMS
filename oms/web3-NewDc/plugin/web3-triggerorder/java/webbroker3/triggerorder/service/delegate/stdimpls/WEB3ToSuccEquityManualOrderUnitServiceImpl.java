head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityManualOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �����A�������蓮����UnitServiceImpl(WEB3ToSuccEquityManualOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06 ���g(���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.service.WEB3RlsRequestSenderService;
import webbroker3.triggerorder.WEB3ToRlsCoopDataManager;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.message.WEB3EquityManualUnit;
import webbroker3.triggerorder.message.WEB3ManualCommissionInfoUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccDataGettingService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityManualOrderUnitService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����A�������蓮����UnitServiceImpl)<BR>
 * �����A�������蓮����UnitService�����N���X�B<BR>
 * 
 * @@author ���g(���u) <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccEquityManualOrderUnitServiceImpl 
    extends WEB3ToEquityManualOrderUnitServiceImpl
    implements WEB3ToSuccEquityManualOrderUnitService 
{
    
    /**
     * ���O�I�u�W�F�N�g
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3ToSuccEquityManualOrderUnitServiceImpl.class);

    /**
     * @@roseuid 4349DEE70222
     */
    public WEB3ToSuccEquityManualOrderUnitServiceImpl() 
    {
     
    }  

    /**
     * (get�����f�[�^)<BR>
     * �p�����[�^.����ID�ɊY������\�񒍕��P�ʂ��擾����B<BR> 
     * <BR>
     * �P�j�@@�\�񒍕��P�ʂ��擾����B <BR>
     * �@@�@@�@@�A�������}�l�[�W��.get�����\�񒍕��P��()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[getOrderUnits()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@����ID�F�@@�p�����[�^.����ID <BR>
     * <BR>
     * <BR>
     * �Q�j�@@�Y���f�[�^�����݂��Ȃ��ꍇ�A <BR>
     * �@@�@@�@@�@@�@@null��Ԃ��B <BR>
     * <BR>
     * �@@�@@�@@�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�P�j�Ŏ擾�����\�񒍕��P�ʂ�Ԃ��B <BR> 
     * @@param l_strOrderId - (����ID)
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 432175DD01A0
     */
    protected EqTypeOrderUnit getOrderData(String l_strOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderData(String)";
        log.entering(STR_METHOD_NAME);        
        
        //�P�j�@@�\�񒍕��P�ʂ��擾����B 
        //      �A�������}�l�[�W��.get�����\�񒍕��P��()���R�[������B 
        //
        //�@@�@@�@@�@@[getOrderUnits()�Ɏw�肷�����] 
        //        ����ID�F�@@�p�����[�^.����ID 
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit = null;
            try
            {
                l_rsvEqOrderUnit = (WEB3ToSuccEqTypeOrderUnitImpl) 
                    l_toOrderManager.getReserveEqtypeOrderUnit(
                        Long.parseLong(l_strOrderId));
            }
            catch (NotFoundException l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

        //�Q�j�@@�Y���f�[�^�����݂��Ȃ��ꍇ�A 
        //         null��Ԃ��B 
        //
        //      �ȊO�̏ꍇ�A 
        //        �P�j�Ŏ擾�����\�񒍕��P�ʂ�Ԃ��B 
            log.exiting(STR_METHOD_NAME);
            return l_rsvEqOrderUnit;
            
    }
    
    /**
     * (getUnit���X�|���X)<BR>
     * �����蓮����Unit�ɘA�������ŗL�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * <BR>
     * �P�j�@@�\�񒍕��P�ʂ���"���������P�ʌ^"�𐶐�����B <BR>
     * �@@�@@�@@�i���ȉ��A�L���X�g�����I�u�W�F�N�g��"�����P��"�ƕ\�L����B�j <BR>
     * <BR>
     * �@@�@@�P�|�P�j�@@�����f�[�^��"�\�񒍕��P��"�ɃL���X�g����B <BR>
     * <BR>
     * �@@�@@�P�|�Q�j�@@�A�������}�l�[�W��Impl.create���������P��()���R�[������B<BR> 
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[create���������P��()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�\�񒍕��P�ʁF�@@�\�񒍕��P�� <BR>
     * <BR>
     * �Q�j�@@���ʂ̃v���p�e�B�ɒl���Z�b�g����B <BR>
     * �@@�@@�@@super.getUnit���X�|���X()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[getUnit���X�|���X()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����f�[�^�F�@@�����P�� <BR>
     * <BR>
     * �R�j�@@�����󋵂��擾����B <BR>
     * �@@�@@�@@�A�������f�[�^�擾�T�[�r�XImpl.get�A�����������󋵋敪()���R�[������B<BR> 
     * <BR>
     * �@@�@@�@@�@@[get�A�����������󋵋敪()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * <BR>
     * �S�j�@@�蓮�����G���[�R�[�h���擾����B <BR>
     * �@@�@@�@@���[���G���W���A�g�f�[�^�}�l�[�W���Dget�A�������蓮�����G���[�R�[�h()��<BR>
     * �@@�@@�@@�R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get�A�������蓮�����G���[�R�[�h()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * <BR>
     * �T�j�@@���[���G���W������̒ʒm�f�[�^���擾����B <BR>
     * �@@�@@�@@���[���G���W���A�g�f�[�^�}�l�[�W���Dget���[���G���W������̒ʒm�f�[�^()��<BR>
     * �@@�@@�@@�R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get���[���G���W������̒ʒm�f�[�^()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * �@@�@@�@@�@@����������ʁF�@@"�A������" <BR>
     * �@@�@@�@@�@@�����^�C�v�F�@@ProductTypeEnum.���� <BR>
     * <BR>
     * �U�j �����P��.�����J�e�S����"�����E���n����"�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�萔�������擾����B<BR>
     * <BR>
     * �@@�@@�@@this.create�蓮�����萔�����()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[create�蓮�����萔�����()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * �@@�@@�@@�@@is�w�l�F�@@(*1)<BR>
     * �@@�@@�@@�@@�@@(*1)�\�񒍕��P��.is�}�w�l�w��()��true�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@true���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@false�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@Not�i�p�����[�^.�����P��.isMarketOrder()�j�̏ꍇ��true�A<BR>
     *  �@@�@@�@@�@@�@@�@@�@@�i�p�����[�^.�����P��.isMarketOrder()�j�̏ꍇ��false <BR>
     * <BR>
     * �V�j�@@�����P�ʁD�����J�e�S�� == "�ԍϒ���"�A�܂���"�����E���n����"�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�M�p����������ׂ��擾����B <BR>
     * <BR>
     * �@@�@@�@@this�Dcreate��������byOrder()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[create��������byOrder()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * <BR>
     * �W�j�@@�����󋵋敪���擾����B <BR>
     * �@@�@@�@@�A�������f�[�^�擾�T�[�r�XImpl�Dget������()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get������()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�\�񒍕��P�� <BR>
     * <BR>
     * �X�j�@@getUnit���X�|���X()�̖߂�l�i�����蓮����Unit�j�� <BR>
     * �@@�@@�@@�A�������ŗL�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����������ʁF�@@"�A������"���Z�b�g�B <BR>
     * �@@�����󋵋敪�F�@@get�A�����������󋵋敪()�̖߂�l <BR>
     * �@@�蓮�����G���[�R�[�h�F�@@get�A�������蓮�����G���[�R�[�h()�̖߂�l <BR>
     * �@@��������M���ԁF�@@<BR>
     * �@@�@@���[���G���W������̒ʒmParams�Dtick�q�b�g�^�C���X�^���v(*2) <BR>
     * �@@�g���K�[�N�����ԁF�@@<BR>
     * �@@�@@���[���G���W������̒ʒmParams�D���[���G���W���t�@@�C�A�^�C���X�^���v(*2) <BR>
     * �@@�����������ԁF�@@���[���G���W������̒ʒmParams�D���������^�C���X�^���v(*2) <BR>
     * �@@�蓮�����萔�����F�@@create�蓮�����萔�����()�̖߂�l <BR>
     * <BR>
     * �@@�M�p����������ׁF�@@create��������byOrder()�̖߂�l <BR>
     * �@@�����󋵋敪�F�@@get������()�̖߂�l <BR>
     * �@@�P�������l�F�@@�����P��.is�}�w�l�w�肪"true"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����P��.�P�������l���Z�b�g�B<BR>
     * <BR>
     * �@@(*2)get���[���G���W������̒ʒm()�̖߂�l��null�̏ꍇ�Z�b�g <BR>
     * <BR>
     * �P�O�j�@@�v���p�e�B���Z�b�g���������蓮����Unit��Ԃ��B <BR>
     * <BR>
     * @@param l_orderData - (�����f�[�^)
     * @@return WEB3EquityManualUnit
     * @@throws WEB3BaseException
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualUnit getUnitResponse(
        EqTypeOrderUnit l_orderData) throws WEB3BaseException
    {       
        final String STR_METHOD_NAME = " getUnitResponse(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderData == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }
        
        //�P�j�@@�\�񒍕��P�ʂ���"���������P�ʌ^"�𐶐�����B 
        //   �i���ȉ��A�L���X�g�����I�u�W�F�N�g��"�����P��"�ƕ\�L����B�j 
        //
        // �P�|�P�j�@@�����f�[�^��"�\�񒍕��P��"�ɃL���X�g����B 
        WEB3ToSuccEqTypeOrderUnitImpl l_toSuccEqTypeOrderUnitImpl = null;
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = null;
        if (l_orderData instanceof WEB3ToSuccEqTypeOrderUnitImpl)
        {
            l_toSuccEqTypeOrderUnitImpl = 
                (WEB3ToSuccEqTypeOrderUnitImpl) l_orderData;
            l_rsvEqOrderUnitRow = 
                (RsvEqOrderUnitRow)l_orderData.getDataSourceObject();
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�^�C�v�s���B");           
        }
        // �P�|�Q�j�@@�A�������}�l�[�W��Impl.create���������P��()���R�[������B 
        //
        //�@@�@@�@@�@@     [create���������P��()�ɐݒ肷�����] 
        //�@@�@@�@@�@@     �\�񒍕��P�ʁF�@@�\�񒍕��P��     
        WEB3ToSuccOrderManagerImpl l_toOrderManager =
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        EqTypeOrderUnit l_eqTypeOrderUnit = 
            l_toOrderManager.createEqtypeOrderUnit(l_toSuccEqTypeOrderUnitImpl);
        
        //�Q�j�@@���ʂ̃v���p�e�B�ɒl���Z�b�g����B 
        //   super.getUnit���X�|���X()���R�[������B 
        //
        //     [getUnit���X�|���X()�ɐݒ肷�����] 
        //     �����f�[�^�F�@@�����P�� 
        WEB3EquityManualUnit l_equityManualUnit = 
            super.getUnitResponse(l_eqTypeOrderUnit);
        
        //�R�j�@@�����󋵂��擾����B 
        //  �A�������f�[�^�擾�T�[�r�XImpl.get�A�����������󋵋敪()���R�[������B 
        //
        //    [get�A�����������󋵋敪()�ɐݒ肷�����] 
        //    �����P�ʁF�@@�����P�� 
                
        WEB3ToSuccDataGettingService l_dataGettingService = 
            (WEB3ToSuccDataGettingService)Services.getService(
                WEB3ToSuccDataGettingService.class);
        
        String l_strToSuccTriggerOrderStatusType = 
            l_dataGettingService.getToSuccTriggerOrderStatusType(l_eqTypeOrderUnit);
        
        //�S�j�@@�蓮�����G���[�R�[�h���擾����B 
        //   ���[���G���W���A�g�f�[�^�}�l�[�W���Dget�A�������蓮�����G���[�R�[�h()���R�[������B 
        //
        //     [get�A�������蓮�����G���[�R�[�h()�ɐݒ肷�����] 
        //     �����P�ʁF�@@�����P�� 
        String l_strToSuccManualOrderErrCode = 
            WEB3ToRlsCoopDataManager.getToSuccManualOrderErrCode(l_eqTypeOrderUnit);
        
        //�T�j�@@���[���G���W������̒ʒm�f�[�^���擾����B 
        //   ���[���G���W���A�g�f�[�^�}�l�[�W���Dget���[���G���W������̒ʒm�f�[�^()���R�[������B 
        //
        //     [get���[���G���W������̒ʒm�f�[�^()�ɐݒ肷�����] 
        //     �����P�ʁF�@@�����P�� 
        //     ����������ʁF�@@"�A������" 
        //     �����^�C�v�F�@@ProductTypeEnum.����         
        RlsConOrderHitNotifyParams l_rlsConOrderHitNotifyParams = 
            WEB3ToRlsCoopDataManager.getRLSConOrderHitNotifyData(
                l_eqTypeOrderUnit,
                WEB3TriggerOrderTypeDef.SUCC,
                ProductTypeEnum.EQUITY);
        
        //�U�j�@@�����P��.�����J�e�S����"�����E���n����"�ȊO�̏ꍇ�A�@@
        //�@@�@@�@@�@@�萔�������擾����B
        // 
        //�@@�@@�@@this.create�蓮�����萔�����()���R�[������B
        //�@@�@@�@@[create�蓮�����萔�����()�ɐݒ肷�����] 
        //�@@�@@�@@�����P�ʁF�@@�����P��
        //�@@�@@�@@is�w�l�F�@@(*1)
        //�@@�@@�@@�@@�@@�@@(*)�\�񒍕��P��.is�}�w�l�w��()��true�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@true���Z�b�g�B
        //�@@�@@�@@�@@�@@�@@�@@�@@false�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@Not�i�p�����[�^.�����P��.isMarketOrder()�j�̏ꍇ��true�A
        // �@@�@@�@@�@@�@@�@@�@@�@@�@@�i�p�����[�^.�����P��.isMarketOrder()�j�̏ꍇ��false
        WEB3ManualCommissionInfoUnit l_manualCommissionInfoUnit = null;
        if (!(OrderCategEnum.SWAP_MARGIN.equals(l_eqTypeOrderUnit.getOrderCateg())))
        {
            boolean l_blnIsLimitPrice = false;
            if (l_toSuccEqTypeOrderUnitImpl.isExecPriceOrder())
            {
                l_blnIsLimitPrice = true;
            }
            else
            {
                if (!l_eqTypeOrderUnit.isMarketOrder())
                {
                    l_blnIsLimitPrice = true;
                }                
            }
            
            l_manualCommissionInfoUnit = 
                    this.createManualCommissionInfoUnit(
                        l_eqTypeOrderUnit,
                        l_blnIsLimitPrice);
        }
        
        //�V�j�@@�����P�ʁD�����J�e�S�� == "�ԍϒ���"�A�܂���"�����E���n����"�̏ꍇ�̂݁A�@@�@@�@@ 
        //   �M�p����������ׂ��擾����B 
        //
        //   this�Dcreate��������byOrder()���R�[������B 
        //
        //     [create��������byOrder()�ɐݒ肷�����] 
        //     �����P�ʁF�@@�����P�� 
        WEB3MarginContractUnit[] l_marginContractUnits = null;
        if (OrderCategEnum.CLOSE_MARGIN.equals(l_eqTypeOrderUnit.getOrderCateg()) ||
            OrderCategEnum.SWAP_MARGIN.equals(l_eqTypeOrderUnit.getOrderCateg()))
        {
            l_marginContractUnits = this.createContractUnitByOrder(l_eqTypeOrderUnit);
        }
        
        //�W�j�@@�����󋵋敪���擾����B 
        //   �A�������f�[�^�擾�T�[�r�XImpl�Dget������()���R�[������B 
        //
        //     [get������()�ɐݒ肷�����] 
        //     �����P�ʁF�@@�\�񒍕��P�� 
        String l_strTransactionState = 
            l_dataGettingService.getTransactionState(l_toSuccEqTypeOrderUnitImpl);
             

        //�X�j�@@getUnit���X�|���X()�̖߂�l�i�����蓮����Unit�j�� 
        //      �A�������ŗL�̃v���p�e�B���Z�b�g����B 
        //
        // ����������ʁF�@@"�A������"���Z�b�g�B 
        // �����󋵋敪�F�@@get�A�����������󋵋敪()�̖߂�l 
        // �蓮�����G���[�R�[�h�F�@@get�A�������蓮�����G���[�R�[�h()�̖߂�l 
        // ��������M���ԁF�@@���[���G���W������̒ʒmParams�Dtick�q�b�g�^�C���X�^���v(*2) 
        // �g���K�[�N�����ԁF�@@���[���G���W������̒ʒmParams�D���[���G���W���t�@@�C�A�^�C���X�^���v(*2) 
        // �����������ԁF�@@���[���G���W������̒ʒmParams�D���������^�C���X�^���v(*2) 
        // 
        // �M�p����������ׁF�@@create��������byOrder()�̖߂�l 
        // �����󋵋敪�F�@@get������()�̖߂�l 
        // �P�������l�F�@@�����P��.is�}�w�l�w�肪"true"�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�@@�@@�����P��.�P�������l���Z�b�g�B
        //
        // (*2)get���[���G���W������̒ʒm()�̖߂�l��null�̏ꍇ�Z�b�g 
        l_equityManualUnit.triggerOrderType = WEB3TriggerOrderTypeDef.SUCC;
        l_equityManualUnit.triggerOrderState = l_strToSuccTriggerOrderStatusType;
        l_equityManualUnit.manualOrderErrorCode = l_strToSuccManualOrderErrCode;
        
        if (l_rlsConOrderHitNotifyParams != null)
        {
            l_equityManualUnit.currentPriceInfoAcceptTime = 
                l_rlsConOrderHitNotifyParams.getHitTickTimestamp();
            
            l_equityManualUnit.triggerStartTime = 
                l_rlsConOrderHitNotifyParams.getRlsHitTimestamp();
            
            l_equityManualUnit.orderCompleteTime = 
                l_rlsConOrderHitNotifyParams.getOrderSubmitTimestamp();
            
        }
        l_equityManualUnit.commissionInfo = l_manualCommissionInfoUnit;
        l_equityManualUnit.contractUnits = l_marginContractUnits;
        l_equityManualUnit.transactionStateType = l_strTransactionState;
        
        if (l_toSuccEqTypeOrderUnitImpl.isExecPriceOrder())
        {
            l_equityManualUnit.priceAdjustmentValue = 
                WEB3StringTypeUtility.formatNumber(l_rsvEqOrderUnitRow.getPriceAdjustValue());
        }
        
        //�P�O�j�@@�v���p�e�B���Z�b�g���������蓮����Unit��Ԃ��B       
        return l_equityManualUnit;
    }
    
    /**
     * (create��������ByOrder)<BR> 
     * �P�j�@@�����\�񒍕��P�ʂ��擾����B  <BR>
     * �@@�@@�@@�A�������}�l�[�W��Impl.get�����\�񒍕��P��()���R�[������B  <BR>
     * <BR>
     * �@@�@@�@@�@@[get�����\�񒍕��P��()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@����ID�F�@@�����P��.����ID <BR>
     * <BR>
     * <BR>
     * �Q�j�@@�A�������}�l�[�W��Impl.create��������ByOrder()���R�[�����A <BR>
     * �@@�@@�@@�߂�l��ԋp����B  <BR>
     * <BR>
     * �@@�@@�@@�@@[get�����\�񒍕��P��()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�����\�񒍕��P�ʁF�@@�P�j�̖߂�l <BR>
     * <BR>
     * �@@�@@�@@�@@��null���ԋp���ꂽ�ꍇ�A  <BR>
     * �@@�@@�@@�@@�@@�u�\�񌈍ϑΏی����͕ʒ����ɂ�茈�ύρv�̗�O���X���[����B<BR>  
     * <BR>
     *          class :WEB3BusinessLayerException<BR>
     *          tag :  BUSINESS_ERROR_02289<BR>
     * <BR> 
     * @@param l_orderData - (�����P��)
     * @@return WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 432175DD01A0
     */
    protected WEB3MarginContractUnit[] createContractUnitByOrder(
            EqTypeOrderUnit l_orderData) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractByOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);  
        
        if (l_orderData == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }
        
        //�P�j�@@�����\�񒍕��P�ʂ��擾����B  
        //   �A�������}�l�[�W��Impl.get�����\�񒍕��P��()���R�[������B  
        //
        //     [get�����\�񒍕��P��()�Ɏw�肷�����] 
        //     ����ID�F�@@�����P��.����ID  
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit = null;    
        try
        {
            long l_lngOrderId = l_orderData.getOrderId();
            
            l_rsvEqOrderUnit = (WEB3ToSuccEqTypeOrderUnitImpl) 
                l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�Q�j�@@�A�������}�l�[�W��Impl.create��������ByOrder()���R�[�����A 
        //�߂�l��ԋp����B  
        //
        //[get�����\�񒍕��P��()�Ɏw�肷�����] 
        //    �����\�񒍕��P�ʁF�@@�P�j�̖߂�l 
        //
        //    ��null���ԋp���ꂽ�ꍇ�A  
        //�@@     �u�\�񌈍ϑΏی����͕ʒ����ɂ�茈�ύρv�̗�O���X���[����B 
        WEB3MarginContractUnit[] l_contractUnits = 
            l_toOrderManager.createContractUnitByOrder(l_rsvEqOrderUnit);
        
        if (l_contractUnits == null)
        {
            log.debug("�\�񌈍ϑΏی����͕ʒ����ɂ�茈�ύςł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02289,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�\�񌈍ϑΏی����͕ʒ����ɂ�茈�ύςł��B");
        }
        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }    
    
    /**
     * (sendRLSRequest)<BR>
     * ���[���G���W������̒ʒm�e�[�u���ւ�INSERT���\�b�h���R�[������B<BR>
     * �P�j�@@�⏕�������擾����B<BR>  
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W���[.getSubAccount()���R�[������B<BR> 
     *<BR> 
     * �@@�@@�@@�@@[getSubAccount()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�@@�ڋqID�F�@@�����f�[�^.getAccountId()�̖߂�l<BR> 
     * �@@�@@�@@�@@�⏕����ID�F�@@�����f�[�^.getSubAccountId()�̖߂�l<BR> 
     *<BR> 
     * �Q�j�@@�����f�[�^��\�񒍕��P�ʂɃL���X�g����B<BR> 
     * �R�j�@@���[���G���W������̒ʒm�e�[�u����INSERT����B  <BR>
     * �@@�@@�@@WEB3RlsRequestSenderServiceImpl.sendManualSubmitConOrder()���R�[������B<BR> 
     *<BR> 
     * �@@�@@�@@�@@[sendManualSubmitConOrder()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�@@�����҃��O�C��ID�F�@@�p�����[�^.�����҃��O�C��ID<BR> 
     * �@@�@@�@@�@@�ʒm�o�H�F�@@�p�����[�^.�ʒm�o�H <BR>
     * �@@�@@�@@�@@�⏕�����F�@@getSubAccount()�̖߂�l<BR>  
     * �@@�@@�@@�@@�����t�����^�C�v�F�@@�����������."�A������"<BR> 
     * �@@�@@�@@�@@�����̖����^�C�v�F�@@�����^�C�v."����"<BR>  
     * �@@�@@�@@�@@����ID�F�@@�\�񒍕��P��.����ID<BR>  
     * �@@�@@�@@�@@�e�����̖����^�C�v�F�@@�����^�C�v."����"<BR> 
     * �@@�@@�@@�@@�e�����̒���ID�F�@@�\�񒍕��P��.�e�����̒���ID<BR> 
     * �@@�@@�@@�@@�������ԁF�@@0<BR>  
     *<BR> 
     * @@param l_orderData - (�����f�[�^)
     * @@param l_submitterLoginId - (�����҃��O�C��ID)
     * @@param l_strNotifyType - (�ʒm�o�H)
     * @@throws WEB3BaseException
     */
    protected void sendRLSRequest(
        EqTypeOrderUnit l_orderData,
        Long l_submitterLoginId,
        String l_strNotifyType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " sendRLSRequest(EqTypeOrderUnit, Long, String)";
        log.entering(STR_METHOD_NAME);
 
        if (l_orderData == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        } 
        
        //�P�j�@@�⏕�������擾����B  
        //  �g���A�J�E���g�}�l�[�W���[.getSubAccount()���R�[������B 
        //
        //    [getSubAccount()�Ɏw�肷�����] 
        //    �ڋqID�F�@@�����f�[�^.getAccountId()�̖߂�l 
        //    �⏕����ID�F�@@�����f�[�^.getSubAccountId()�̖߂�l 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_orderData.getAccountId(),
                    l_orderData.getSubAccountId());
          
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�Q�j�@@�����f�[�^��\�񒍕��P�ʂɃL���X�g����B         
        WEB3ToSuccEqTypeOrderUnitImpl l_toSuccEqTypeOrderUnitImpl = null;
        if (l_orderData instanceof WEB3ToSuccEqTypeOrderUnitImpl)
        {
            l_toSuccEqTypeOrderUnitImpl = (WEB3ToSuccEqTypeOrderUnitImpl) l_orderData;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�^�C�v�s���B");           
        }

        //�R�j�@@���[���G���W������̒ʒm�e�[�u����INSERT����B  
        //   WEB3RlsRequestSenderServiceImpl.sendManualSubmitConOrder()���R�[������B 
        //
        //     [sendManualSubmitConOrder()�Ɏw�肷�����] 
        //     �����҃��O�C��ID�F�@@�p�����[�^.�����҃��O�C��ID 
        //     �ʒm�o�H�F�@@�p�����[�^.�ʒm�o�H 
        //     �⏕�����F�@@getSubAccount()�̖߂�l  
        //     �����t�����^�C�v�F�@@�����������."�A������" 
        //     �����̖����^�C�v�F�@@�����^�C�v."����"  
        //     ����ID�F�@@�\�񒍕��P��.����ID  
        //     �e�����̖����^�C�v�F�@@�����^�C�v."����" 
        //     �e�����̒���ID�F�@@�\�񒍕��P��.�e�����̒���ID 
        //     �������ԁF�@@0    
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = 
            (RsvEqOrderUnitRow) l_toSuccEqTypeOrderUnitImpl.getDataSourceObject();
        
        long l_lngParentOrderId = l_rsvEqOrderUnitRow.getParentOrderId();
        WEB3RlsRequestSenderService l_requestSenderService =
            (WEB3RlsRequestSenderService) Services.getService(
                WEB3RlsRequestSenderService.class);
        
        l_requestSenderService.sendManualSubmitConOrder(
            l_submitterLoginId,
            l_strNotifyType,
            l_subAccount,
            Integer.parseInt(WEB3TriggerOrderTypeDef.SUCC),
            ProductTypeEnum.EQUITY,
            new Long(l_toSuccEqTypeOrderUnitImpl.getOrderId()),
            ProductTypeEnum.EQUITY,
            new Long(l_lngParentOrderId),
            0);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set����J�����_�R���e�L�X�g)
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * <BR>
     * �P�j�@@���X�I�u�W�F�N�g���擾�B <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[getBranch()�ɐݒ肷�����] <BR>
     * �@@�@@�@@arg0�F�@@�����f�[�^.getBranchId()�̖߂�l <BR>
     * <BR>
     * �Q�j�@@�،���ЃI�u�W�F�N�g���擾�B <BR>
     * �@@�@@�@@getBranch()�̖߂�l.getInstitution()���R�[������B <BR>
     * <BR>
     * �R�j�@@�s��I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�R�|�P�j�@@�\�񒍕��P��Row�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����f�[�^.getDataSourceObject()���R�[������B<BR>
     * <BR>
     * �@@�@@�R�|�Q�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[������B<BR>
     * <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@[getMarket()�ɐݒ肷�����]<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�\�񒍕��P��Row.getMarketId()<BR>
     * <BR>
     * �S�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B    <BR>
     * �@@�|�p�����[�^.�����f�[�^�̓��e��������ԃR���e�L�X�g�� <BR>
     * �@@�@@�@@�v���p�e�B���Z�b�g����B  <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     * �@@�@@�@@getInstitution()�̖߂�l.getInstitutionCode() <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     * �@@�@@�@@getBranch()�̖߂�l.getBranchCode() <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = <BR>
     * �@@�@@�@@getMarket()�̖߂�l.getMarketCode()<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*1) <BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = (*2) <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = (*3) <BR>
     * <BR>
     * �@@(*1)��t���ԋ敪 <BR>
     * �@@�@@�@@�E�����J�e�S��(�����f�[�^.getOrderCateg())���h�����E���n�����h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�h�����E���n�h���Z�b�g����B <BR>
     * �@@�@@�@@�E�����J�e�S������L�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�h�����E�M�p�h���Z�b�g����B <BR>
     * <BR>
     * �@@(*2)������t���i <BR>
     * �@@�@@�@@�E�����J�e�S���i�����f�[�^.getOrderCateg()�j���h���������h�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�@@�h�����h���Z�b�g����B <BR>
     * �@@�@@�@@�E�����J�e�S������L�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�h�M�p�h���Z�b�g����B <BR>
     * <BR>
     * �@@(*3)������t�g�����U�N�V���� <BR>
     * �@@�@@�@@�E������� == �i�h�����������h�܂��́h�V�K���������h�j�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�h���t�i�V�K�����j�h���Z�b�g����B <BR>
     * �@@�@@�@@�E������� == �i�h�����������h�܂��́h�V�K���������h�j�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�h���t�i�V�K�����j�h���Z�b�g����B <BR>
     * �@@�@@�@@�E�����J�e�S�����h�ԍρh�̏ꍇ�A"�ԍ�"���Z�b�g����B  <BR>
     * �@@�@@�@@�E�����J�e�S�����h�������n�h�̏ꍇ�A"�������n"���Z�b�g����B  <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�    <BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B    <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH    <BR>
     * <BR>
     * �T�j�@@��t�����A���t���[�����Z�b�g����B    <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * @@param l_orderData - (�����f�[�^)<BR>
     * @@throws WEB3BaseException<BR>
     */
    protected void setTradingClendarContext(EqTypeOrderUnit l_orderData) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setTradingClendarContext(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderData == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        } 

        //�P�j�@@���X�I�u�W�F�N�g���擾�B 
        //�@@�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B
        //�@@[getBranch()�ɐݒ肷�����] 
        //�@@arg0�F�@@�����f�[�^.getBranchId()�̖߂�l 
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        Branch l_branch = null;
        
        try
        {
            l_branch = l_accountManager.getBranch(l_orderData.getBranchId());           
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@�،���ЃI�u�W�F�N�g���擾�B 
        //�@@�@@getBranch()�̖߂�l.getInstitution()���R�[������B 
        Institution l_institution = l_branch.getInstitution();
        
        //�s��I�u�W�F�N�g���擾����B
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = 
            (RsvEqOrderUnitRow)l_orderData.getDataSourceObject();
            
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
             
        long l_lngMarketId = l_rsvEqOrderUnitRow.getMarketId();        
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
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�S�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B    
        //�|�p�����[�^.�����f�[�^�̓��e��������ԃR���e�L�X�g�� 
        //�@@�@@�v���p�e�B���Z�b�g����B  
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        
        //�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = getInstitution()�̖߂�l.getInstitutionCode() 
        l_context.setInstitutionCode(l_institution.getInstitutionCode());
        
        //�@@����J�����_�R���e�L�X�g.���X�R�[�h = getBranch()�̖߂�l.getBranchCode()
        l_context.setBranchCode(l_branch.getBranchCode());
        
        //�@@����J�����_�R���e�L�X�g.�s��R�[�h = getMarket()�̖߂�l.getMarketCode() 
        l_context.setMarketCode(l_market.getMarketCode());
        
        //�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*1) 
        //�@@(*1)��t���ԋ敪 
        //�@@�E�����J�e�S��(�����f�[�^.getOrderCateg())���h�����E���n�����h�̏ꍇ�A 
        //�@@�@@�@@�h�����E���n�h���Z�b�g����B 
        
        if (OrderCategEnum.SWAP_MARGIN.equals(l_orderData.getOrderCateg()))
        {
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
        }
        //  �E�����J�e�S������L�ȊO�̏ꍇ�A 
        //�@@�@@�@@�h�����E�M�p�h���Z�b�g����B
        else
        {
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        }      
        
        //�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        //�@@����J�����_�R���e�L�X�g.������t���i = (*2) 
        //�@@(*2)������t���i 
        //�@@�@@�E�����J�e�S���i�����f�[�^.getOrderCateg()�j���h���������h�̏ꍇ�A 
        //�@@�@@�@@�@@�h�����h���Z�b�g����B 

        if (OrderCategEnum.ASSET.equals(l_orderData.getOrderCateg()))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        }
        //�@@  �E�����J�e�S������L�ȊO�̏ꍇ�A 
        //�@@�@@�@@�@@�h�M�p�h���Z�b�g����B
        else
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        }        
        
        //�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = (*3) 
        
        //�@@(*3)������t�g�����U�N�V���� 
        //�@@�E������� == �i�h�����������h�܂��́h�V�K���������h�j�̏ꍇ�A         
        //�@@�@@    �h���t�i�V�K�����j�h���Z�b�g����B 
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderData.getOrderType()) ||
            OrderTypeEnum.MARGIN_LONG.equals(l_orderData.getOrderType()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
        }
        
        //  �E������� == �i�h�����������h�܂��́h�V�K���������h�j�̏ꍇ�A 
        //�@@�@@    �h���t�i�V�K�����j�h���Z�b�g����B 
        else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderData.getOrderType()) ||
            OrderTypeEnum.MARGIN_SHORT.equals(l_orderData.getOrderType()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
        }
        
        //  �E�����J�e�S�����h�ԍρh�̏ꍇ�A"�ԍ�"���Z�b�g����B  
        else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderData.getOrderCateg()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CLOSE_MARGIN);
        }
        
        //  �E�����J�e�S�����h�������n�h�̏ꍇ�A"�������n"���Z�b�g����B 
        else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderData.getOrderCateg()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.SWAP_MARGIN);
        }
        
        //  �|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�    
        //�@@�@@������ԃR���e�L�X�g���Z�b�g����B    
        //    �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH    

        //�T�j�@@��t�����A���t���[�����Z�b�g����B    
        //�@@�|������ԊǗ�.setTimestamp()���R�[������B  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
    
        //������ԊǗ�
        WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException

        log.exiting(STR_METHOD_NAME);
    }

}
@
