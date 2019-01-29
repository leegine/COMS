head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToRlsCoopDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[���G���W���A�g�f�[�^�}�l�[�W��(WEB3ToRlsCoopDataManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/2/18 ���(���u) �V�K�쐬
                 : 2006/8/24 ������(���u) ���f��No.158 ����
                   2006/8/24 �đo�g(���u) ���f��No.175 No.178
                   2006/11/14 �юu��(���u) ���f��No.188
                   2006/11/20 ����(���u) ���f��No.181
*/

package webbroker3.triggerorder;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyRow;
import webbroker3.triggerorder.define.WEB3ToManualOrderErrorCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (���[���G���W���A�g�f�[�^�}�l�[�W��)<BR>
 * ���[���G���W���A�g�f�[�^�}�l�[�W��<BR>
 *
 * @@author ���
 * @@version 1.0
 */
public class WEB3ToRlsCoopDataManager
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3ToRlsCoopDataManager.class);

    /**
     * @@roseuid 43F4933F03D8
     */
    public WEB3ToRlsCoopDataManager()
    {

    }

    /**
     * (validate�蓮��������)<BR>
     * �����̒����P�ʂ��蓮�����Ώۂ����肷��B<BR>
     * ���茋�ʂ��蓮�����G���[�R�[�h�ŕԋp����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.����������ʁ�"�t�w�l"�̏ꍇ�A<BR>
     * �@@�@@this.get�t�w�l�蓮�����G���[�R�[�h()���R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.����������ʁ�"W�w�l"�̏ꍇ�A<BR>
     * �@@�@@this.getW�w�l�蓮�����G���[�R�[�h()���R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_strTriggerOrderType - (�����������)<BR>
     * �����������<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43EBF7F403C5
     */
    public static String validateManualOrder(OrderUnit l_orderUnit, String l_strTriggerOrderType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateManualOrder(OrderUnit, String)";
        log.entering(STR_METHOD_NAME);

        if (WEB3TriggerOrderTypeDef.STOP.equals(l_strTriggerOrderType))
        {
            String l_strManualOrder = getStopManualOrderErrCode(l_orderUnit);

            log.exiting(STR_METHOD_NAME);
            return l_strManualOrder;
        }
        else if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_strTriggerOrderType))
        {
            String l_strManualOrder = getWLimitManualOrderErrorCode(l_orderUnit);

            log.exiting(STR_METHOD_NAME);
            return l_strManualOrder;
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get�t�w�l�蓮�����G���[�R�[�h)<BR>
     * �p�����[�^.�����P�ʂ��<BR>
     * �t�w�l�������L�������肵�A�蓮�����G���[�R�[�h��ԋp����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�^�C�v�̎擾<BR>
     * <BR>
     * �@@�P�|�P�j�@@[�p�����[�^�D�����P�ʂ̌^�����������P�ʂ̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@���������P��ROW����A���N�G�X�g�^�C�v���擾����B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@[�p�����[�^�D�����P�ʂ̌^���敨OP�����P�ʂ̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�敨OP�����P��ROW����A���N�G�X�g�^�C�v���擾����B<BR>
     * <BR>
     * �Q�j�@@�t�w�l�蓮�����L�������̔���<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�p�����[�^.�����P��.������ԁ��h14�F������(�������)�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@"����σG���["��ԋp����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�P�j�Ŏ擾�������N�G�X�g�^�C�v���h�����T�[�o�h�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@"�����σG���["��ԋp����B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�P�j�Ŏ擾�������N�G�X�g�^�C�v��"�������s"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@"�������s"��ԋp����B<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�p�����[�^.�����P�ʁD�����L����ԁ�"CLOSED(�N���[�Y)"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@"���̑��G���["��ԋp����B<BR>
     * <BR>
     * �@@�Q�|�T�j�@@��L�ȊO�̏ꍇ�A"����"��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43EC68E302F7
     */
    public static String getStopManualOrderErrCode(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getStopManualOrderErrCode(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�P�j���N�G�X�g�^�C�v�̎擾
        String l_strRequestType = "";
        //�p�����[�^�D�����P�ʂ̌^�����������P�ʂ̏ꍇ
        if (l_orderUnit instanceof EqTypeOrderUnit)
        {
            EqTypeOrderUnit l_eqOrderUnit = (EqTypeOrderUnit)l_orderUnit;
            EqtypeOrderUnitRow l_eqOrderUnitRow =
                (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();
            //���������P��ROW����A���N�G�X�g�^�C�v���擾����B
            l_strRequestType = l_eqOrderUnitRow.getRequestType();
        }
        //�p�����[�^�D�����P�ʂ̌^���敨OP�����P�ʂ̏ꍇ
        else if (l_orderUnit instanceof IfoOrderUnit)
        {
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;
            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
            //�敨OP�����P��ROW����A���N�G�X�g�^�C�v���擾����B
            l_strRequestType = l_ifoOrderUnitRow.getRequestType();
        }
        else
        {
            log.debug("�p�����[�^�̒l�������^�敨OP�����P�ʈȊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                WEB3ToRlsCoopDataManager.class.getName() + STR_METHOD_NAME,
                "�p�����[�^�̒l�������^�敨OP�����P�ʈȊO�ł��B");
        }

        //�Q�j�@@�t�w�l�蓮�����L�������̔���
        //�Q�|�P�j�����P��.������ԁ��h14�F������(�������)�h�̏ꍇ
        //�@@�@@�@@�@@"����σG���["��ԋp����B
        if (OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.CANCELED;
        }
        //�Q�|�Q�j�P�j�Ŏ擾�������N�G�X�g�^�C�v���h�����T�[�o�h�̏ꍇ
        //�@@�@@�@@�@@"�����σG���["��ԋp����B
        else if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.ORDERED;
        }
        //�Q�|�R�j�P�j�Ŏ擾�������N�G�X�g�^�C�v���h�������s�h�̏ꍇ
        //�@@�@@�@@�@@"�������s"��ԋp����B
        else if (WEB3RequestTypeDef.ORDER_FAILURE.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.ORDER_FAILURE;
        }
        //�Q�|�S�j�p�����[�^.�����P��.�����L����ԁ��hCLOSED(�N���[�Y)�h�̏ꍇ
        //�@@�@@�@@�@@"���̑��G���["��ԋp����B
        else if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.OTHER;
        }
        //�Q�|�T�j��L�ȊO�̏ꍇ�A"����"��ԋp����B
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.NORMAL;
        }
    }

    /**
     * (get�A�������蓮�����G���[�R�[�h)<BR>
     * �p�����[�^.�����P�ʂ��<BR>
     * �A���������L�������肵�A�蓮�����G���[�R�[�h��ԋp����B<BR>
     * <BR>
     * �P�j�@@�A�������蓮�����L�������̔���<BR>
     * <BR>
     * �@@�P�|�P�j�@@�����P��.������ԁ��h14�F������(�������)�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@"����σG���["��ԋp����B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�����P��.������ԁ��h3�F������(�V�K����)�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@"�����σG���["��ԋp����B<BR>
     * <BR>
     * �@@�P�|�R�j�@@�����P��.������ԁ��h6�F�������s(�V�K����)�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@"�������s"��ԋp����B<BR>
     * <BR>
     * �@@�P�|�S�j�@@�����P��.�����L����ԁ��hCLOSED(�N���[�Y)�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@"���̑��G���["��ԋp����B<BR>
     * <BR>
     * �@@�P�|�T�j�@@��L�ȊO�̏ꍇ�A"����"��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getToSuccManualOrderErrCode(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getToSuccManualOrderErrCode(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�A�������蓮�����L�������̔���
        String l_strToSuccManualOrderErrCode = null;

        //�P�|�P�j�@@�����P��.������ԁ��h14�F������(�������)�h�̏ꍇ�A
        //�@@�@@�@@�@@�@@"����σG���["��ԋp����B
        if (OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
        {
            l_strToSuccManualOrderErrCode = WEB3ToManualOrderErrorCodeDef.CANCELED;
        }
        //�P�|�Q�j�@@�����P��.������ԁ��h3�F������(�V�K����)�h�̏ꍇ
        //�@@�@@�@@�@@�@@"�����σG���["��ԋp����B
        else if (OrderStatusEnum.ORDERED.equals(l_orderUnit.getOrderStatus()))
        {
            l_strToSuccManualOrderErrCode = WEB3ToManualOrderErrorCodeDef.ORDERED;
        }
        //�P�|�R�j�@@�����P��.������ԁ��h6�F�������s(�V�K����)�h�̏ꍇ�A
        //�@@�@@�@@�@@�@@"�������s"��ԋp����B
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderUnit.getOrderStatus()))
        {
            l_strToSuccManualOrderErrCode = WEB3ToManualOrderErrorCodeDef.ORDER_FAILURE;
        }
        //�P�|�S�j�@@�����P��.�����L����ԁ��hCLOSED(�N���[�Y)�h�̏ꍇ�A
        //�@@�@@�@@�@@�@@"���̑��G���["��ԋp����B
        else if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()))
        {
            l_strToSuccManualOrderErrCode = WEB3ToManualOrderErrorCodeDef.OTHER;
        }
        //�P�|�T�j�@@��L�ȊO�̏ꍇ�A"����"��ԋp����B
        else
        {
            l_strToSuccManualOrderErrCode = WEB3ToManualOrderErrorCodeDef.NORMAL;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strToSuccManualOrderErrCode;
    }

    /**
     * (getW�w�l�蓮�����G���[�R�[�h)<BR>
     * �p�����[�^.�����P�ʂ��<BR>
     * W�w�l�������L�������肵�A�蓮�����G���[�R�[�h��ԋp����B<BR>
     * <BR>
     * �P�j�@@�������x���������ǂ����̔��� <BR>
     * <BR>
     * �@@�P�|�P�j�@@[�p�����[�^.�����P�ʂ̌^�����������P�ʂ̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�g�����������}�l�[�W��.is�������x������()���R�[������B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@[�p�����[�^.�����P�ʂ̌^���敨OP�����P�ʂ̏ꍇ]<BR>
     *   �@@�@@�@@�@@�@@OP�����}�l�[�W��.is�������x������()���R�[������B<BR>
     * <BR>
     * �Q�j�@@W�w�l�蓮�����L�������̔���<BR>
     * �@@�Q�|�P�j�@@�p�����[�^.�����P��.������Ԃ��ȉ��̒l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@"����σG���["��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�E"��t�ρi��������j"<BR>
     * �@@�@@�@@�@@�@@�@@�E"�������i��������j"<BR>
     * �@@�@@�@@�@@�@@�@@�E"�����ρi��������j"<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�p�����[�^.�����P��.isFullyExecuted( ) == true�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@"���ς݃G���["��ԋp����B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�p�����[�^.�����P��.�����L����ԁ��hCLOSED�i�N���[�Y�j�h����<BR>
     * �@@�@@�@@�@@�@@�@@�����P��.�����敪���hINVALIDATED_BY_MKT�i�}�[�P�b�g���ہj�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@"�����σG���["��ԋp����B<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�p�����[�^.�����P��.�����L����ԁ��hCLOSED(�N���[�Y)�h<BR>
     * �@@�@@�@@�@@�@@�@@�܂��́A�������x�������i�P�j�̖߂�l == false�j�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@"���̑��G���["��ԋp����B<BR>
     * <BR>
     * �@@�Q�|�T�j�@@��L�ȊO�̏ꍇ�A"����"��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitManualOrderErrorCode(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitManualOrderErrorCode(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�������x���������ǂ����̔���
        boolean l_blnIsNotOrderedDelay = true;
        // �@@�P�|�P�j�@@[�p�����[�^.�����P�ʂ̌^�����������P�ʂ̏ꍇ]
        if (l_orderUnit instanceof EqTypeOrderUnit)
        {
            //�g�����������}�l�[�W��.is�������x������()���R�[������B
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_equityOrderMgr =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            l_blnIsNotOrderedDelay =
                l_equityOrderMgr.isNotOrderedDelayOrder((EqTypeOrderUnit)l_orderUnit);
        }
        // �@@�P�|�Q�j�@@[�p�����[�^.�����P�ʂ̌^���敨OP�����P�ʂ̏ꍇ]
        else if (l_orderUnit instanceof IfoOrderUnit)
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;
            //OP�����}�l�[�W��.is�������x������()���R�[������B
            l_blnIsNotOrderedDelay = l_orderManager.isNotOrderedDelay(l_ifoOrderUnit);
        }
        else
        {
            log.debug("�p�����[�^�̒l�������^�敨OP�����P�ʈȊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                WEB3ToRlsCoopDataManager.class.getName() + STR_METHOD_NAME,
                "�p�����[�^�̒l�������^�敨OP�����P�ʈȊO�ł��B");
        }
        // �Q�j�@@W�w�l�蓮�����L�������̔���
        // �@@�Q�|�P�j�@@�p�����[�^.�����P��.������Ԃ��ȉ��̒l�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@"����σG���["��ԋp����B
        // �@@�@@�@@�@@�@@�@@�E"��t�ρi��������j"
        //  �@@�@@�@@�@@�@@ �E"�������i��������j"
        // �@@�@@�@@�@@�@@�@@�E"�����ρi��������j"
        if (OrderStatusEnum.CANCEL_ACCEPTED .equals(l_orderUnit.getOrderStatus())
            || OrderStatusEnum.CANCELLING.equals(l_orderUnit.getOrderStatus())
            || OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.CANCELED;
        }
        // �@@�Q�|�Q�j�@@�p�����[�^.�����P��.isFullyExecuted( ) == true�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@"���ς݃G���["��ԋp����B
        else if (l_orderUnit.isFullyExecuted())
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.EXECUTED;
        }

        // �@@�Q�|�R�j�@@�p�����[�^.�����P��.�����L����ԁ��hCLOSED�i�N���[�Y�j�h����
        // �@@�@@�@@�@@�@@�@@�����P��.�����敪���hINVALIDATED_BY_MKT�i�}�[�P�b�g���ہj�h�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@"�����σG���["��ԋp����B
        else if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
            && OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.EXPIREED;
        }

        // �@@�Q�|�S�j�@@�p�����[�^.�����P��.�����L����ԁ��hCLOSED(�N���[�Y)�h
        // �@@�@@�@@�@@�@@�@@�܂��́A�������x�������i�P�j�̖߂�l == false�j�̏ꍇ
        // �@@�@@�@@�@@�@@�@@"���̑��G���["��ԋp����B
        else if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
            || !l_blnIsNotOrderedDelay)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.OTHER;
        }

        // �@@�Q�|�T�j�@@��L�ȊO�̏ꍇ�A"����"��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return WEB3ToManualOrderErrorCodeDef.NORMAL;
    }


    /**
     * (get���[���G���W������̒ʒm�f�[�^)<BR>
     * �����̏����ɊY�����郋�[���G���W������̒ʒmParams��ԋp����B <BR>
     * <BR>
     * �P�j�@@���[���G���W������̒ʒm�e�[�u��(rls_con_order_hit_notify)�� <BR>
     * �����̏����Ō����������ʂ�ԋp����B <BR>
     * <BR>
     * [��������]<BR>
     * ����ID�F�@@�p�����[�^.�����P��.����ID<BR>
     * �⏕����ID�F�@@�p�����[�^.�����P��.�⏕����ID<BR>
     * �����t�������^�C�v�F�@@�p�����[�^.�����������<BR>
     * ����ID�F�@@�p�����[�^.�����P��.����ID<BR>
     * ���������^�C�v�F�@@�p�����[�^.�����^�C�v<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * ���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * ���������ʂ̃��R�[�h���������̏ꍇ�́A�f�[�^�s�����̗�O���X���[����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��
     * @@param l_strTriggerOrderType - (�����������)<BR>
     * �����������<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * @@return RlsConOrderHitNotifyParams
     * @@throws WEB3BaseException
     */
    public static RlsConOrderHitNotifyParams getRLSConOrderHitNotifyData(
        OrderUnit l_orderUnit,
        String l_strTriggerOrderType,
        ProductTypeEnum l_productType
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRLSConOrderHitNotifyData("
            + "OrderUnit l_orderUnit,"
            + "String l_strTriggerOrderType,"
            + "ProductTypeEnum l_productType) ";
        log.entering(STR_METHOD_NAME);

        String l_strQueryString = "";
        ArrayList l_lstBind = new ArrayList();

        //[��������]����ID
        l_strQueryString += " account_id = ? ";
        l_lstBind.add(new Long(l_orderUnit.getAccountId()));

        //[��������]�⏕����ID
        l_strQueryString += " and sub_account_id = ? ";
        l_lstBind.add(new Long(l_orderUnit.getSubAccountId()));

        //[��������]�����t�������^�C�v
        l_strQueryString += " and order_type = ? ";
        l_lstBind.add(l_strTriggerOrderType);

        //[��������]����ID
        l_strQueryString += " and order_id = ? ";
        l_lstBind.add(new Long(l_orderUnit.getOrderId()));

        //[��������]���������^�C�v
        l_strQueryString += " and product_type = ? ";
        l_lstBind.add(l_productType);

        //�P�j���[���G���W������̒ʒm�e�[�u��(rls_con_order_hit_notify)��
        //    �����̏����Ō����������ʂ�ԋp����B
        QueryProcessor l_queryProcessor;
		try
        {
		    l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRow = l_queryProcessor.doFindAllQuery(
                RlsConOrderHitNotifyRow.TYPE,
                l_strQueryString,
                l_lstBind.toArray()
                );

            log.debug("���[���G���W������̒ʒm�e�[�u�� �Y������ : " + l_lisRow.size());

            if (l_lisRow.isEmpty())
            {
                //���������ʂ��擾�o���Ȃ������ꍇ�Anull��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            else if (l_lisRow.size() > 1)
            {
                //���������ʂ̃��R�[�h���������̏ꍇ�́A�f�[�^�s�����̗�O���X���[����B
                log.error("���[���G���W������̒ʒm�e�[�u���������ʂ�����������");
                //��O����
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    WEB3ToRlsCoopDataManager.class.getName()
                    + "."
                    + STR_METHOD_NAME);
            }

            //�Q�j�@@�������ʂ�ԋp����B
            RlsConOrderHitNotifyParams l_returnParams =
                new RlsConOrderHitNotifyParams((RlsConOrderHitNotifyRow)l_lisRow.get(0));

            log.exiting(STR_METHOD_NAME);
            return l_returnParams;

        }
        catch (DataFindException l_ex)
        {
            //���������ʂ��擾�o���Ȃ������ꍇ�Anull��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;

        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3ToRlsCoopDataManager.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3ToRlsCoopDataManager.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

		}
    }

}
@
