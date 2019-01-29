head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������t�P���T�[�r�XImpl(WEB3FeqOrderAcceptUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ����� (���u) �V�K�쐬
                   2006/10/17 �����(���u) ���f���@@No.289�Ή�
                   2006/11/20 �����(���u) ���f���@@No.299,300�Ή�
                   2006/11/22 �����(���u) ���f���@@No.306�Ή�
                   2006/12/14 ꎉ�(���u) ���f���@@No.311�Ή�
                   2006/12/15 ꎉ�(���u) ���f���@@No.312�Ή�                      
                   2006/12/19 ���G��(���u) ���f���@@No.313,318�Ή�
                   2006/12/19 ���G��(���u) ���f���@@No.322�Ή�
                   2007/05/17 ��c�iSRA�j ���f�� H00149�iNo.350�j�Ή�
Revesion History : 2008/02/26 �g�C��(���u) ���f���@@No.401-No.409�Ή�
                   2008/07/08 ����(SRA) ���f���@@No.450�Ή�
*/

package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.message.WEB3FeqOrderAcceptCancelUnit;
import webbroker3.feq.service.delegate.WEB3FeqAcceptUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAcceptUnitService;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.utils.SleGLRejectTypeDef;
import webbroker3.slegateway.define.WEB3SleCallbackConstantsDef.AckdCommand;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O������������t�P���T�[�r�XImpl)<BR>
 * �O������������t�P���T�[�r�X�����N���X<BR>
 * 
 * @@author �����
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptUnitServiceImpl implements WEB3FeqOrderAcceptUnitService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptUnitServiceImpl.class);
        
    /**
     * @@roseuid 42D0CED203D8
     */
    public WEB3FeqOrderAcceptUnitServiceImpl() 
    {
     
    }
    
    /**
     * (notify������t)<BR>
     * ������t�P���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������������t�T�[�r�X�j������t�P�������v�Q�ƁB<BR>
     * ========================================================<BR>
     * �V�[�P���X�}(�u�i�O������������t�T�[�r�X�j������t�P�������v)<BR>
     * �@@�@@1.3����t���[�F�@@�o���I����iis�o���I��() == true�j�ꍇ�A<BR> 
     * �@@�@@�u�o���I����̒����̏ꍇ�́A������t�s�v��O���X���[����<BR> 
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02166<BR>
     * ==========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}(�u�i�O������������t�T�[�r�X�j������t�P�������v)<BR>
     * �@@�@@1.5����t���[�F�@@��t�s�iis������t�\() == false�j�ꍇ�A
     * �@@�@@�u������t�s�̏�ԁv��O���X���[����<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02649<BR>
     * ==========================================================<BR>
     * <BR> 
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_cancelUnit - (�O������������t������)<BR>
     * �O������������t������<BR>
     * @@param l_sleRvcdQParams - (RCVD_Q�f�[�^)<BR>
     * SLE_ECVD_Q�@@Params<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public void notifyOrderAccept(
        WEB3FeqOrderUnit l_orderUnit,
        WEB3FeqOrderAcceptCancelUnit l_cancelUnit,
        SleRcvdQParams l_sleRvcdQParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyOrderAccept(WEB3FeqOrderUnit, WEB3FeqOrderAcceptCancelUnit, SleRcvdQParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null || l_cancelUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //1.1 getOrderUnit(long)
        //�����P�ʂ��Ď擾����B 
        //���������b�N����O�ɁA�ʂ̃g�����U�N�V�����ɂ���Ē����f�[�^�� 
        //�X�V����Ă���\�������邽�߁B
        //[getOrderUnit()�Ɏw�肷�����]  
        //�����P��ID�F�@@����.�����P��.getOrderUnitId()�̖߂�l
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager(); 
        WEB3FeqOrderUnit l_feqOrderUnit = null;
        try
        {
            l_feqOrderUnit = 
                (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
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
        
        //1.2 is�o���I��()
        //�o���I����̒������𔻒肷��B
        if (l_feqOrderUnit.isExecEnd())
        {
            //1.3 �i����t���[�F�@@�o���I����iis�o���I��() == true�j�ꍇ�A
            //�o���I����̒����̏ꍇ�́A������t�s�v��O���X���[����j
            log.debug("�o���I����̒����́A������t���ʃA�b�v���[�h�s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02166, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�o���I����̒����́A������t���ʃA�b�v���[�h�s�ł��B");
        }

        //RCVD_Q�f�[�^.���ی����R�[�h��1������ == "M"�j �̏ꍇ�A
        //�O������������t������.�ύX���t�敪���f�����f�ɐݒ肷��
        if (l_sleRvcdQParams.getRejectCode() != null &&
            SleGLRejectTypeDef.FIRST_CHAR_SUSPEND.equals(l_sleRvcdQParams.getRejectCode().substring(0,1)))
        {
            l_cancelUnit.aftChangeAcceptDiv = WEB3FeqAcceptTypeDef.NOT_EXECUTED;
        }

        //����t���[�F����.RCVD_Q�f�[�^.���͌o�H�敪�� 3�F������t�̏ꍇ
        if (WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT.equals(l_sleRvcdQParams.getRouteDiv()))
        {
            //������Ԃ�������t�������s���Ă悢��Ԃ��ǂ������`�F�b�N����
            //[validate�������()�Ɏw�肷�����]
            //�@@�����P�ʁF�@@�����̒����P��
            //�@@���ی����R�[�h�F�@@����.RCVD_Q�f�[�^�D���ی����R�[�h

            this.validateOrderStatus(l_orderUnit, l_sleRvcdQParams.getRejectCode());

            //�i����t���[�F�@@�G���[�ʒm�i�ύX���t�敪==������t�G���[
            //or�ύX���t�敪==������t�G���[or�ύX���t�敪==�����t�G���[�j�̏ꍇ
            if (WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR.equals(l_cancelUnit.aftChangeAcceptDiv)
                || WEB3FeqAcceptTypeDef.CHANGE_ERROR.equals(l_cancelUnit.aftChangeAcceptDiv)
                || WEB3FeqAcceptTypeDef.CANCEL_ERROR.equals(l_cancelUnit.aftChangeAcceptDiv))
            {
                //�����F�؎���������s(�O�����������P��)
                l_feqOrderManager.executeOrderAcceptCancel(l_feqOrderUnit);
       
                //getOrderUnit(arg0 : long)
                try
                {
                    l_feqOrderUnit = 
                        (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
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
            }
        }

        //�i�����P��.�����L����� == "�N���[�Y"�j���i
        //RCVD_Q�f�[�^.���ی����R�[�h��1������ == "M"�j�̏ꍇ
        if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()) &&
            l_sleRvcdQParams.getRejectCode() != null &&
            SleGLRejectTypeDef.FIRST_CHAR_SUSPEND.equals(l_sleRvcdQParams.getRejectCode().substring(0,1)))
        {
            //�����P��.������� != "�V�K�����G���["�@@�̏ꍇ
            if (!OrderStatusEnum.NOT_ORDERED.equals(l_orderUnit.getOrderStatus()) )
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }

        //is������t�\
        //��t�\���𔻒肷��B
        //[is������t�\()�Ɏw�肷�����]
        //�ύX���t�敪�F�@@����.�O������������t������.�ύX���t�敪
        boolean l_blnOrderAcceptPoss =
            l_feqOrderUnit.isOrderAcceptPoss(l_cancelUnit.aftChangeAcceptDiv);

        if (!l_blnOrderAcceptPoss)
        {
            //�i����t���[�F�@@��t�s�iis������t�\() == false�j�ꍇ�A
            //������t�s�̏�ԁv��O���X���[����j
            log.debug("������t�s�̏��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02649,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������t�s�̏��");
        }

        //(�iRCVD_Q�f�[�^�DACK �R�}���h�^�C�v == "�ύX���F"�j����
        //�i�O������������t������.�ύX���t�敪 == "������"�j)�������� or
        //(�iRCVD_Q�f�[�^�DACK �R�}���h�^�C�v == "�V�K���F"�j����
        //�i�O������������t������.�ύX���t�敪 == "������t��"�j)�̏ꍇ
        if ((AckdCommand.MODIFY_ACK == l_sleRvcdQParams.getAckdCommand() &&
            WEB3FeqAcceptTypeDef.CHANGED.equals(l_cancelUnit.aftChangeAcceptDiv))
            || (AckdCommand.NEW_ORDER_ACK == l_sleRvcdQParams.getAckdCommand() &&
            WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE.equals(l_cancelUnit.aftChangeAcceptDiv)))
        {
            //validate��t�d��(�O�������RCVD_Q, �O�����������P��)
            //[����]
            //�O�������RCVD_Q�F�@@���N�G�X�g�f�[�^.RCVD_Q�f�[�^
            //�����P�ʁF�@@�Ď擾���������P�ʁigetOrderUnit�̖߂�l�j
            //�����V�[�P���X���Ŏ擾�������������P��
            //�i���V�����擾�������̂��g�p����j
            try
            {
                l_feqOrderUnit =
                    (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
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
            this.validateAcceptTelegram(l_sleRvcdQParams, l_feqOrderUnit);
        }

        //get�s�ꃌ�X�|���X���b�Z�[�W
        //�ύX���t�敪�ɑΉ�����s�ꃌ�X�|���X���b�Z�[�W���擾����B
        //[get�s�ꃌ�X�|���X���b�Z�[�W()�Ɏw�肷�����]
        //�����P�ʁF�@@����.����Id
        //�ύX���t�敪�F�@@����.�O������������t������.�ύX���t�敪
        WEB3FeqAcceptUpdateService l_feqAcceptUpdateService =
            (WEB3FeqAcceptUpdateService)Services.getService(
                WEB3FeqAcceptUpdateService.class);
        MarketResponseMessage l_marketResponseMessage =
            l_feqAcceptUpdateService.getMarketResponseMessage(
                l_orderUnit.getOrderId(),
                l_cancelUnit.aftChangeAcceptDiv);

        //update��t(MarketResponseMessage)
        //��t�X�V�������s���B
        //[update��t()�Ɏw�肷�����]
        //�s�ꃌ�X�|���X���b�Z�[�W�F�@@this.get�s�ꃌ�X�|���X���b�Z�[�W()�̖߂�l
        l_feqAcceptUpdateService.updateAccept(l_marketResponseMessage);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�������)<BR>
     * �Ώے������A������t���s���ėǂ���Ԃł��邩�`�F�b�N����B<BR>
     * <BR>
     * �i�`�F�b�N���e�j<BR>
     * �P�D����.���ی����R�[�h=='GL 007'�@@�̎��ȉ��̏������s���B<BR>
     * �@@�@@�@@�@@�i����.�����P��.�������==�������@@���� <BR>
     * �@@�@@�@@�@@isPartiallyExecuted()==true�i�F�ꕔ���j�j�@@�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�����ΏۊO�f�[�^�v�̗�O��throw����B<BR>
     * <BR>
     * �Q�D����.���ی����R�[�h=='GL 022'�@@�̎��ȉ��̏������s���B<BR>
     * �@@�@@�@@�@@�i����.�����P��.�������==�������@@���� <BR>
     * �@@�@@�@@�@@isFullyExecuted()==true�i�F�S���j�j�@@�@@�@@�@@�ȊO�̏ꍇ <BR>
     * �@@�@@�@@�@@�u�����ΏۊO�f�[�^�v�̗�O��throw����B<BR>
     * <BR>
     * �R�D�V�K�����������A���������������A��������������̏ꍇ�ł���
     *     ����.���ی����R�[�h��1������=='M' �̎��ȉ��̏������s���B <BR>
     * �@@  �u�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁv�̗�O��throw����B<BR>
     * �@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag   : BUSINESS_ERROR_01975<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_strRejectCode - (���ی����R�[�h)<BR>
     * ���ی����R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public void validateOrderStatus(
        WEB3FeqOrderUnit l_orderUnit,
        String l_strRejectCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrderStatus(WEB3FeqOrderUnit, String)";
        log.entering(STR_METHOD_NAME);

        //�P�D����.���ی����R�[�h=='GL 007'�@@�̎��ȉ��̏������s���B
        if (SleGLRejectTypeDef.CHANGE_REJECT_AF_PART_EXEC.equals(l_strRejectCode))
        {
            //�i����.�����P��.�������==�������@@����
            // isPartiallyExecuted()==true�i�F�ꕔ���j�j�@@�ȊO�̏ꍇ�A
            if (!(OrderStatusEnum.MODIFYING.equals(l_orderUnit.getOrderStatus()) 
                && l_orderUnit.isPartiallyExecuted()))
            {
                //�u�����ΏۊO�f�[�^�v�̗�O��throw����B
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02178,
                    getClass().getName() + STR_METHOD_NAME,
                    "�����ΏۊO�f�[�^�B");
            }
        }

        //�Q�D����.���ی����R�[�h=='GL 022'�@@�̎��ȉ��̏������s���B
        if (SleGLRejectTypeDef.CHANGE_REJECT_AF_ALL_EXEC.equals(l_strRejectCode))
        {
            //�i����.�����P��.�������==�������@@����
            // isFullyExecuted()==true�i�F�S���j�j�@@�@@�@@�@@�ȊO�̏ꍇ
            if (!(OrderStatusEnum.MODIFYING.equals(l_orderUnit.getOrderStatus()) 
                && l_orderUnit.isFullyExecuted()))
            {
                //�u�����ΏۊO�f�[�^�v�̗�O��throw����B
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02178,
                    getClass().getName() + STR_METHOD_NAME,
                    "�����ΏۊO�f�[�^�B");
            }
        }

        //�R�D�V�K�����������A���������������A��������������̏ꍇ�ł���
        //    ����.���ی����R�[�h��1������=='M' �̎��ȉ��̏������s���B
        //�@@�@@�u�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁv�̗�O��throw����B
        if ((OrderStatusEnum.ORDERING.equals(l_orderUnit.getOrderStatus())
            || OrderStatusEnum.MODIFYING.equals(l_orderUnit.getOrderStatus())
            || OrderStatusEnum.CANCELLING.equals(l_orderUnit.getOrderStatus()))
            && l_strRejectCode != null
            && SleGLRejectTypeDef.FIRST_CHAR_SUSPEND.equals(l_strRejectCode.substring(0,1)))
        {
            log.debug("�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁB");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁB");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate��t�d��)<BR>
     * ������t�d���̒������ʁE���i���A<BR>
     * �V�X�e���i�����P�ʁj�Ŕc�����Ă��钍�����ʁE���i�Ƒ���Ȃ����`�F�b�N����B<BR>
     * <BR>
     * �P�D���i�`�F�b�N<BR>
     * �@@�@@����.�O�������RCVD_Q�D�w�l���i�iprice�j != ����.�����P�ʁD�w�l�ilimit_price�j�̏ꍇ�A<BR>
     * �@@�@@�f�[�^�s�����G���[��throw����B<BR>
     * �@@�@@�@@�@@class : WEB3SystemLayerException<BR>
     * �@@�@@�@@�@@tag   : SYSTEM_ERROR_80006<BR>
     * <BR>
     * �Q�D���ʃ`�F�b�N<BR>
     * �@@�@@����.�O�������RCVD_Q�D�������ʁiquantity�j != ����.�����P�ʁD�������ʁiquantity�j�̏ꍇ�A<BR>
     * �@@�@@�f�[�^�s�����G���[��throw����B<BR>
     * �@@�@@�@@�@@class : WEB3SystemLayerException<BR>
     * �@@�@@�@@�@@tag   : SYSTEM_ERROR_80006<BR>
     * <BR>
     * @@param l_sleRvcdQParams - (�O�������RCVD_Q)<BR>
     * SLE_RCVD_Q�@@Params<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4214980A032E
     */
    private void validateAcceptTelegram(
        SleRcvdQParams l_sleRvcdQParams,
        WEB3FeqOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAcceptTelegram(SleRcvdQParams, WEB3FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�P�D���i�`�F�b�N
        //�@@�@@����.�O�������RCVD_Q�D�w�l���i�iprice�j != ����.�����P�ʁD�w�l
        //�@@�@@�ilimit_price�j�̏ꍇ�A�f�[�^�s�����G���[��throw����B
        if (l_sleRvcdQParams.getPrice() != l_orderUnit.getLimitPrice())
        {
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        //�Q�D���ʃ`�F�b�N
        //����.�O�������RCVD_Q�D�������ʁiquantity�j != ����.�����P�ʁD��������
        //�iquantity�j�@@�̏ꍇ�A�f�[�^�s�����G���[��throw����B
        if (l_sleRvcdQParams.getQuantity() != l_orderUnit.getQuantity())
        {
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
