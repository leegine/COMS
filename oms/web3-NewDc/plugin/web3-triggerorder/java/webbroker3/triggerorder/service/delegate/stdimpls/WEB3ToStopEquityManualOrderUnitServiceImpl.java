head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopEquityManualOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �����t�w�l�����蓮����UnitServiceImpl(WEB3ToStopEquityManualOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06 ���r(���u) �V�K�쐬
                   2006/11/29 ���r(���u) �d�l�ύX ���f��No.202
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.service.WEB3RlsRequestSenderService;
import webbroker3.triggerorder.WEB3ToRlsCoopDataManager;
import webbroker3.triggerorder.message.WEB3EquityManualUnit;
import webbroker3.triggerorder.message.WEB3ManualCommissionInfoUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityManualOrderUnitService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����t�w�l�����蓮����UnitServiceImpl)<BR>
 * �����t�w�l�����蓮����UnitService�����N���X�B<BR>
 * 
 * @@author ���r(���u) <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToStopEquityManualOrderUnitServiceImpl 
    extends WEB3ToEquityManualOrderUnitServiceImpl
    implements WEB3ToStopEquityManualOrderUnitService 
{
    
    /**
     * ���O�I�u�W�F�N�g
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3ToStopEquityManualOrderUnitServiceImpl.class);

    /**
     * @@roseuid 4349DEE70222
     */
    public WEB3ToStopEquityManualOrderUnitServiceImpl() 
    {
     
    }
    
    /**
     * (getUnit���X�|���X)<BR>
     * �����蓮����Unit�ɋt�w�l�����ŗL�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * <BR>
     * �P�j�@@�����f�[�^��"���������P�ʌ^"�ɃL���X�g����B <BR>
     * �@@�@@�i���ȉ��A�L���X�g�����I�u�W�F�N�g��"�����P��"�ƕ\�L����B�j <BR>
     * <BR>
     * �Q�j�@@���ʂ̃v���p�e�B���Z�b�g����B <BR>
     * �@@�@@�@@super.getUnit���X�|���X()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[getUnit���X�|���X()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����f�[�^�F�@@�P�j�Ő������������P�� <BR>
     * <BR>
     * �R�j�@@�t�w�l�����󋵋敪���擾����B <BR>
     * �@@�@@�@@�����f�[�^�A�_�v�^.get�t�w�l�����󋵋敪()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get�����󋵋敪()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * <BR>
     * �S�j�@@�t�w�l�����蓮�����G���[�R�[�h���擾����B <BR>
     * �@@�@@�@@���[���G���W���A�g�f�[�^�}�l�[�W��.get�t�w�l�蓮�����G���[�R�[�h()��<BR>
     * �@@�@@�@@�R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get�t�w�l�蓮�����G���[�R�[�h()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * <BR>
     * �T�j�@@���[���G���W������̒ʒm�f�[�^���擾����B <BR>
     * �@@�@@�@@���[���G���W���A�g�f�[�^�}�l�[�W��.get���[���G���W�������<BR>
     * �@@�@@�@@�@@�ʒm�f�[�^()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get���[���G���W������̒ʒm�f�[�^()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * �@@�@@�@@�@@����������ʁF�@@�h�t�w�l�����h <BR>
     * �@@�@@�@@�@@�����^�C�v�F�@@ProductTypeEnum.���� <BR>
     * <BR>
     * �U�j �萔�������擾����B<BR>
     * <BR>
     * �@@�@@�@@this.create�蓮�����萔�����()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[create�蓮�����萔�����()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * �@@�@@�@@�@@is�w�l�F�@@Not�i�p�����[�^.�����P��.isMarketOrder()�j�̏ꍇ��true�A<BR>
     *  �@@�@@�@@�@@�@@�@@�@@�@@�i�p�����[�^.�����P��.isMarketOrder()�j�̏ꍇ��false <BR>
     * <BR>
     * �V�j�@@�����P�ʁD�����J�e�S�� == "�ԍϒ���"�̏ꍇ�̂� <BR>
     * �@@�@@�@@�M�p����������ׂ��擾����B <BR>
     * <BR>
     * �@@�@@�@@this.create��������byOrder()���R�[������B <BR>
     * �@@�@@�@@ <BR>
     * �@@�@@�@@�@@[create��������byOrder()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * <BR>
     * �W�j�@@getUnit���X�|���X()�̖߂�l�i�����蓮����Unit�j�� <BR>
     * �@@�@@�@@�t�w�l�����ŗL�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����������ʁF�@@"�t�w�l����"���Z�b�g�B <BR>
     * �@@�����������Z�q�F�@@�����P��.�����������Z�q <BR>
     * �@@���������P���F�@@�����P��.�t�w�l��l <BR>
     * �@@�����󋵋敪�F�@@get�t�w�l�����󋵋敪()�̖߂�l <BR>
     * �@@�蓮�����G���[�R�[�h�F�@@get�t�w�l�蓮�����G���[�R�[�h()�̖߂�l <BR>
     * �@@��������M���ԁF�@@<BR>
     * �@@�@@���[���G���W������̒ʒmParams.tick�q�b�g�^�C���X�^���v(*1) <BR>
     * �@@�g���K�[�N�����ԁF�@@<BR>
     * �@@�@@���[���G���W������̒ʒmParams.���[���G���W���t�@@�C�A�^�C���X�^���v(*1) <BR>
     * �@@�����������ԁF<BR>
     * �@@�@@���[���G���W������̒ʒmParams.���������^�C���X�^���v(*1) <BR>
     * �@@�蓮�����萔�����F�@@create�蓮�����萔�����()�̖߂�l <BR>
     * �@@�M�p����������ׁF�@@create��������byOrder()�̖߂�l <BR>
     * <BR>
     * �@@(*1)get���[���G���W������̒ʒm()�̖߂�l��null�̏ꍇ�Z�b�g <BR>
     * <BR>
     * �X�j�@@�v���p�e�B���Z�b�g���������蓮����Unit��Ԃ��B <BR>
     * <BR>  
     * @@param l_orderData - (�����f�[�^)
     * @@return WEB3EquityManualUnit
     * @@throws WEB3BaseException
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualUnit getUnitResponse(
        EqTypeOrderUnit l_orderData) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUnitResponse(EqTypeOrderUnit)";
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
        //�P�j�@@�����f�[�^��"���������P�ʌ^"�ɃL���X�g����B
        //�i���ȉ��A�L���X�g�����I�u�W�F�N�g��"�����P��"�ƕ\�L����B�j
        EqTypeOrderUnit l_eqTypeOrderUnit = l_orderData;
        
        //�Q�j�@@���ʂ̃v���p�e�B���Z�b�g����B 
        //super.getUnit���X�|���X()���R�[������B        
        //�@@�@@[getUnit���X�|���X()�ɐݒ肷�����] 
        //�@@�����f�[�^�F�@@�P�j�Ő������������P��
        WEB3EquityManualUnit l_eqtypeMunualUnit =  
            super.getUnitResponse(l_eqTypeOrderUnit);

        //�R�j�@@�t�w�l�����󋵋敪���擾����B 
        //�����f�[�^�A�_�v�^.get�t�w�l�����󋵋敪()���R�[������B
        //�@@�@@�@@�@@[get�����󋵋敪()�ɐݒ肷�����] 
        //�@@�@@�@@�@@�����P�ʁF�@@�����P�� 
        String l_strStopTriggerOrderStatus = 
            WEB3EquityDataAdapter.getStopTriggerOrderStatusType(l_eqTypeOrderUnit);
        
        //�S�j�@@�t�w�l�����蓮�����G���[�R�[�h���擾����B 
        //���[���G���W���A�g�f�[�^�}�l�[�W��.get�t�w�l�蓮�����G���[�R�[�h()
        // ���R�[������B 
        // [get�t�w�l�蓮�����G���[�R�[�h()�ɐݒ肷�����] 
        //�@@�����P�ʁF�@@�����P��
        String l_strStopManualOrderErrCode =
            WEB3ToRlsCoopDataManager.getStopManualOrderErrCode(l_eqTypeOrderUnit);
        
        //�T�j�@@���[���G���W������̒ʒm�f�[�^���擾����B 
        //���[���G���W���A�g�f�[�^�}�l�[�W��.get���[���G���W������̒ʒm�f�[�^()���R�[������B 
        //[get���[���G���W������̒ʒm�f�[�^()�ɐݒ肷�����] 
        //�����P�ʁF�@@�����P�� 
        //�@@����������ʁF�@@�h�t�w�l�����h 
        //�@@�����^�C�v�F�@@ProductTypeEnum.���� 
        RlsConOrderHitNotifyParams l_rlsConOrderHitNotifyParams= 
            WEB3ToRlsCoopDataManager.getRLSConOrderHitNotifyData(
                l_eqTypeOrderUnit,
                WEB3TriggerOrderTypeDef.STOP,
                ProductTypeEnum.EQUITY);
        
        //�@@�萔�������擾����B
        //�@@this.create�蓮�����萔�����()���R�[������B 
        //�@@�@@[create�蓮�����萔�����()�ɐݒ肷�����] 
        //�@@�@@�����P�ʁF�@@�����P�� 
        //�@@�@@is�w�l�F�@@Not�i�p�����[�^.�����P��.isMarketOrder()�j�̏ꍇ��true�A
        // �@@�@@�@@�@@�@@�@@�i�p�����[�^.�����P��.isMarketOrder()�j�̏ꍇ��false
        boolean l_blnIsLimitPrice = false;
        if (!(l_eqTypeOrderUnit.isMarketOrder()))
        {
            l_blnIsLimitPrice = true;
        }
        WEB3ManualCommissionInfoUnit l_manualCommissionInfoUnit =
                this.createManualCommissionInfoUnit(
                    l_eqTypeOrderUnit,
                    l_blnIsLimitPrice);
        
        // �V�j�@@�����P�ʁD�����J�e�S�� == "�ԍϒ���"�̏ꍇ�̂� 
        //�M�p����������ׂ��擾����B 
        //this.create��������byOrder()���R�[������B 
        //[create��������byOrder()�ɐݒ肷�����] 
        //�����P�ʁF�@@�����P�� 
        WEB3MarginContractUnit[] l_marginContractUnits = null;
        if (OrderCategEnum.CLOSE_MARGIN.equals(l_eqTypeOrderUnit.getOrderCateg()))
        {
            l_marginContractUnits = 
                this.createContractUnitByOrder(l_eqTypeOrderUnit);
        }
        //�W�j�@@getUnit���X�|���X()�̖߂�l�i�����蓮����Unit�j�� 
        //�t�w�l�����ŗL�̃v���p�e�B���Z�b�g����B 
        //����������ʁF�@@"�t�w�l����"���Z�b�g�B
        l_eqtypeMunualUnit.triggerOrderType = WEB3TriggerOrderTypeDef.STOP;
       
        //�����������Z�q�F�@@�����P��.�����������Z�q
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = 
            (EqtypeOrderUnitRow) l_eqTypeOrderUnit.getDataSourceObject(); 
        l_eqtypeMunualUnit.condOperator = 
            l_eqtypeOrderUnitRow.getOrderCondOperator();
       
        //���������P���F�@@�����P��.�t�w�l��l
        l_eqtypeMunualUnit.orderCondPrice = 
            WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitRow.getStopOrderPrice());
       
        //�����󋵋敪�F�@@get�t�w�l�����󋵋敪()�̖߂�l 
        l_eqtypeMunualUnit.triggerOrderState = l_strStopTriggerOrderStatus;
        
        //�蓮�����G���[�R�[�h�F�@@get�t�w�l�蓮�����G���[�R�[�h()�̖߂�l
        l_eqtypeMunualUnit.manualOrderErrorCode = l_strStopManualOrderErrCode;
        
        //��������M���ԁF�@@���[���G���W������̒ʒmParams.tick�q�b�g�^�C���X�^���v(*1) 
        //�g���K�[�N�����ԁF�@@���[���G���W������̒ʒmParams.���[���G���W���t�@@�C�A�^�C���X�^���v(*1) 
        //�����������ԁF�@@���[���G���W������̒ʒmParams.���������^�C���X�^���v(*1) 
        //�M�p����������ׁF�@@create��������byOrder()�̖߂�l 
        //(*1)get���[���G���W������̒ʒm()�̖߂�l��null�̏ꍇ�Z�b�g
        if (l_rlsConOrderHitNotifyParams != null)
        {
            l_eqtypeMunualUnit.currentPriceInfoAcceptTime = 
                l_rlsConOrderHitNotifyParams.getHitTickTimestamp();
            l_eqtypeMunualUnit.triggerStartTime = 
                l_rlsConOrderHitNotifyParams.getRlsHitTimestamp();
            l_eqtypeMunualUnit.orderCompleteTime = 
                l_rlsConOrderHitNotifyParams.getOrderSubmitTimestamp();
                
        }
        l_eqtypeMunualUnit.commissionInfo = l_manualCommissionInfoUnit;
        l_eqtypeMunualUnit.contractUnits = l_marginContractUnits;
        
        log.exiting(STR_METHOD_NAME);
        
        //�X�j�@@�v���p�e�B���Z�b�g���������蓮����Unit��Ԃ��B
        return  l_eqtypeMunualUnit;
    }
    
    /**
     * (sendRLSRequest)<BR>
     * ���[���G���W������̒ʒm�e�[�u���ւ�INSERT���\�b�h���R�[������B<BR> 
     *  �P�j�@@�⏕�������擾����B <BR> 
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W���[.get�⏕����()���R�[������B<BR>  
     * <BR> 
     * �@@�@@�@@�@@[getSubAccount()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�@@�ڋqID�F�@@�����f�[�^.getAccountId()�̖߂�l  
     * �@@�@@�@@�@@�⏕����ID�F�@@�����f�[�^.getSubAccountId()�̖߂�l<BR>  
     * <BR> 
     * �Q�j�@@���[���G���W������̒ʒm�e�[�u����INSERT����B<BR>   
     * �@@�@@�@@WEB3RlsRequestSenderServiceImpl.sendManualSubmitConOrder()<BR>
     * �@@�@@�@@���R�[������B<BR>  
     * <BR> 
     * �@@�@@�@@�@@[sendManualSubmitConOrder()�Ɏw�肷�����]<BR>  
     * �@@�@@�@@�@@�����҃��O�C��ID�F�@@�p�����[�^.�����҃��O�C��ID<BR>  
     * �@@�@@�@@�@@�ʒm�o�H�F�@@�p�����[�^.�ʒm�o�H<BR>  
     * �@@�@@�@@�@@�⏕�����F�@@getSubAccount()�̖߂�l  <BR> 
     * �@@�@@�@@�@@�����t�����^�C�v�F�@@�����������."�t�w�l����"<BR>  
     * �@@�@@�@@�@@�����̖����^�C�v�F�@@�����^�C�v."����"<BR>  
     * �@@�@@�@@�@@����ID�F�@@�����f�[�^.����ID<BR>   
     * �@@�@@�@@�@@�e�����̖����^�C�v�F�@@null<BR>   
     * �@@�@@�@@�@@�e�����̒���ID�F�@@null<BR>   
     * �@@�@@�@@�@@�������ԁF�@@0<BR> 
     * @@param l_orderData - (�����f�[�^)
     * @@param l_submitterLoginId - (�����҃��O�C��ID)
     * @@param l_strNotifyType - (�ʒm�o�H)
     * @@throws WEB3BaseException
     */
    protected void sendRLSRequest(
        EqTypeOrderUnit l_orderData,
        Long l_submitterLoginId,
        String l_strNotifyType) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "sendRLSRequest(EqTypeOrderUnit,Long,String)";
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

        //  �P�j�@@�⏕�������擾����B <BR> 
        //�@@�g���A�J�E���g�}�l�[�W���[.get�⏕����()���R�[������B�B
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
            log.error("�⏕�����e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // �Q�j�@@���[���G���W������̒ʒm�e�[�u����INSERT����
        
        // �@@�@@�@@�@@[sendManualSubmitConOrder()�Ɏw�肷�����] 
        //�@@�@@�@@�@@�����҃��O�C��ID�F�@@�p�����[�^.�����҃��O�C��ID  
        //�@@�@@�@@�@@�ʒm�o�H�F�@@�p�����[�^.�ʒm�o�H  
        //�@@�@@�@@�@@�⏕�����F�@@getSubAccount()�̖߂�l   
        //�@@�@@�@@�@@�����t�����^�C�v�F�@@�����������."�t�w�l����"  
        //�@@�@@�@@�@@�����̖����^�C�v�F�@@�����^�C�v."����"
        //�@@�@@�@@�@@����ID�F�@@�����f�[�^.����ID  
        //�@@�@@�@@�@@�e�����̖����^�C�v�F�@@null
        //�@@�@@�@@�@@�e�����̒���ID�F�@@null
        //�@@�@@�@@�@@�������ԁF�@@0
        WEB3RlsRequestSenderService l_requestSenderService = 
            (WEB3RlsRequestSenderService) Services.getService(WEB3RlsRequestSenderService.class);
        
        l_requestSenderService.sendManualSubmitConOrder(
            l_submitterLoginId,
            l_strNotifyType,
            l_subAccount,
            Integer.parseInt(WEB3TriggerOrderTypeDef.STOP),
            ProductTypeEnum.EQUITY,
            new Long(l_orderData.getOrderId()),
            null,
            null,
            0);
        log.exiting(STR_METHOD_NAME);
    }        
}
 

@
