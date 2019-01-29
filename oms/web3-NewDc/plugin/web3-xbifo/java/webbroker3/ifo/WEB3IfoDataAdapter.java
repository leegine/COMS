head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoDataAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�f�[�^�A�_�v�^(WEB3IfoDataAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/24 �L�R �ˎq (SRA) �V�K�쐬
                 : 2006/7/5 ���G��(���u)�d�l�ύX���f��499
                 : 2006/7/6 ���G��(���u)�d�l�ύX���f��513
                 : 2006/7/6 �юu��(���u)�d�l�ύX���f��455,472,482,484,485,487,497,500,510,523,539
                 : 2006/10/9 ������(���u)�@@�d�l�ύX���f��No.555�@@
                   2006/11/29 �����(���u)�d�l�ύX���f��583
Revesion History : 2007/06/08 �����F(���u) ���f��654�A663
Revesion History : 2007/06/14 �юu��(���u) ���f��718
Revesion History : 2007/06/21 �����F(���u) ���f��745
Revesion History : 2007/06/27 �����F(���u) ���f��759�A766
Revesion History : 2008/07/28 ������(���u) ���f��891
*/

package webbroker3.ifo;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderAction;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3ContractCheckDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3SonarExecutionConditionDef;
import webbroker3.common.define.WEB3TriggerOrderStatusDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.define.WEB3DelayDivDef;
import webbroker3.ifo.define.WEB3IfoAcceptTypeDef;
import webbroker3.ifo.define.WEB3IfoExecStatusTypeDef;
import webbroker3.ifo.define.WEB3IfoExpirationStatusDef;
import webbroker3.ifo.define.WEB3IfoOrderSpecDivDef;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�敨OP�f�[�^�A�_�v�^)<BR>
 * �敨OP�̃f�[�^�ϊ����Ǘ�����N���X�B<BR>
 * @@author  �L�R �ˎq�iSRA�j
 * @@version 1.0
 */
public class WEB3IfoDataAdapter
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3IfoDataAdapter.class);

    /**
     * (get����敪)<BR>
     * �p�����[�^.������ʂ��APR�w�Ŏg�p�������敪��ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.������ʂɂ�蕪�򂵁A�Ή�����l��ԋp����B<BR>
     * <BR>
     * �p�����[�^.������ʂ��A<BR>
     * �@@[OrderTypeEnum.�w���敨�V�K���������̏ꍇ]<BR>
     * �@@�@@"�w���敨�V�K��������"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���敨�V�K���������̏ꍇ]<BR>
     * �@@�@@"�w���敨�V�K��������"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���敨�����ԍϒ����̏ꍇ]<BR>
     * �@@�@@"�w���敨�����ԍϒ���"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���敨�����ԍϒ����̏ꍇ]<BR>
     * �@@�@@"�w���敨�����ԍϒ���"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���I�v�V�����V�K���������̏ꍇ]<BR>
     * �@@�@@"�w���I�v�V�����V�K��������"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���I�v�V�����V�K���������̏ꍇ]<BR>
     * �@@�@@"�w���I�v�V�����V�K��������"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���I�v�V���������ԍϒ����̏ꍇ]<BR>
     * �@@�@@"�w���I�v�V���������ԍϒ���"��ԋp����B<BR>
     * �@@[OrderTypeEnum.�w���I�v�V���������ԍϒ����̏ꍇ]<BR>
     * �@@�@@"�w���I�v�V���������ԍϒ���"��ԋp����B<BR>
     * @@param l_orderType - (�������)<BR>
     * �������<BR>
     * @@return String
     */
    public static String getTradingType(OrderTypeEnum l_orderType) 
    {
        final String STR_METHOD_NAME =
            " getTradingType(OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        String l_strTradingType = null;
        //[OrderTypeEnum.�w���敨�V�K���������̏ꍇ]
        //�@@"�w���敨�V�K��������"��ԋp����B
        if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue());
        }
        //[OrderTypeEnum.�w���敨�V�K���������̏ꍇ]
        //�@@"�w���敨�V�K��������"��ԋp����B
        else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.intValue());
        }
        //[OrderTypeEnum.�w���敨�����ԍϒ����̏ꍇ]
        //�@@"�w���敨�����ԍϒ���"��ԋp����B
        else if (OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.intValue());
        }
        //[OrderTypeEnum.�w���敨�����ԍϒ����̏ꍇ]
        //�@@"�w���敨�����ԍϒ���"��ԋp����B
        else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.intValue());
        }
        //[OrderTypeEnum.�w���I�v�V�����V�K���������̏ꍇ]
        //�@@"�w���I�v�V�����V�K��������"��ԋp����B
        else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.intValue());
        }
        //[OrderTypeEnum.�w���I�v�V�����V�K���������̏ꍇ]
        //�@@"�w���I�v�V�����V�K��������"��ԋp����B
        else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.intValue());
        }
        //[OrderTypeEnum.�w���I�v�V���������ԍϒ����̏ꍇ]
        //�@@"�w���I�v�V���������ԍϒ���"��ԋp����B
        else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.intValue());
        }
        //[OrderTypeEnum.�w���I�v�V���������ԍϒ����̏ꍇ]
        //�@@"�w���I�v�V���������ԍϒ���"��ԋp����B
        else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.intValue());
        }
        log.debug("����敪 = " + l_strTradingType);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_strTradingType;
    }
    
    /**
     * (get������ԋ敪)<BR>
     * �p�����[�^.�����P�ʂ��APR�w�Ŏg�p���钍����ԋ敪��ԋp����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����P�ʂ̕ێ�����f�[�^�ɂ�蕪�򂵁A<BR>
     * �@@�Ή����钍����ԋ敪��ԋp����B<BR>
     * <BR>
     * �@@�P�|�P�j�@@�ꕔ�����̔���<BR>
     * �@@�@@�p�����[�^.�����P��.isPartiallyExecuted()��true ����<BR>
     * �@@�@@�����P��.�����L����ԁ�CLOSED�i�N���[�Y�j ����<BR>
     * �@@�@@�����P��.�����敪��INVALIDATED_BY_MKT�i�}�[�P�b�g���ہj�̏ꍇ�A<BR>
     * �@@�@@�@@"�ꕔ����"��Ԃ��B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�S�������̔���<BR>
     * �@@�@@�p�����[�^.�����P��.isUnexecuted()�� true ����<BR>
     * �@@�@@�����P��.�����L����ԁ�CLOSED�i�N���[�Y�j ����<BR>
     * �@@�@@�����P��.�����敪��INVALIDATED_BY_MKT�i�}�[�P�b�g���ہj�̏ꍇ�A<BR>
     * �@@�@@"�S������"��Ԃ��B<BR>
     * <BR>
     * �@@�P�|�R�j�@@�J�z���s�̔���<BR>
     * <BR>
     * �@@�@@�敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�߂�l��"��������"�ȊO ����<BR>
     * �@@�@@�����P��.�����������t >�� �Ɩ����t(*1) ����<BR>
     * �@@�@@�����P��.�����L����ԁ�CLOSED�i�N���[�Y�j ����<BR>
     * �@@�@@�����P��.�����敪��EXPIRED�i�I���j ����<BR>
     * �@@�@@�����P��.�����G���[���R�R�[�h��"�g���K�[�����Ǘ��Ҏ蓮������" ����<BR>
     * �@@�@@�����P��.�����G���[���R�R�[�h��"0000�F����"�̏ꍇ�A<BR>
     * �@@�@@"�J�z���s"��Ԃ��B<BR>
     * <BR>
     * �@@�P�|�S�j�@@�����̔���<BR>
     * �@@�@@�p�����[�^.�����P��.�����L����ԁ�CLOSED�i�N���[�Y�j ����<BR>
     * �@@�@@�����P��.�����敪��EXPIRED�i�I���j�̏ꍇ�A<BR>
     * �@@�@@"����"��Ԃ��B<BR>
     * �@@�@@���o���I���ʒm�Œ��������������ꍇ<BR>
     * <BR>
     * �@@�P�|�T�j�@@�J�z�ς̔���<BR>
     * <BR>
     * �@@�@@�����P��.������� ��ACCEPTED�i��t�ρi�V�K�����j�j ���A<BR>
     * �@@�@@�����P��.���񒍕��̒����P��ID > 0�i=�J�z����) �̏ꍇ�A<BR>
     * �@@�@@"�J�z��"��Ԃ��B<BR>
     * <BR>
     * �@@�P�|�U�j�@@�ؑ֒����̔��� <BR>
     * �@@�@@�����P��.������ԁ�MODIFY_ACCEPTED(��t��(�ύX����)) ���A <BR>
     * �@@�@@�����P��.���������E����敪��"W�w�l�����ؑ֒�"�̏ꍇ�A"�ؑ֒���"��Ԃ��B<BR> 
     * <BR>
     * �@@�P�|�V�j�@@�ؑ֎�t�̔��� <BR>
     * �@@�@@�����P��.������ԁ�MODIFYING(������(�ύX����)) ���A<BR> 
     * �@@�@@�����P��.���������E����敪��"W�w�l�����ؑ֒�"�̏ꍇ�A"�ؑ֎�t"��Ԃ��B<BR> 
     * <BR>
     * �@@�P�|�W�j�@@�ؑ֊����̔��� <BR>
     * �@@�@@�����P��.������ԁ�MODIFIED(�����ς�(�ύX����)) ���A <BR>
     * �@@�@@�����P��.���������E����敪���i"W�w�l�����ꕔ�ؑ֊���"�A"W�w�l�����S���ؑ֊���"�j<BR>
     * �@@�@@�̏ꍇ�A"�ؑ֊���"��Ԃ��B <BR>
     * <BR>
     * �@@�P�|�X�j�@@�ؑ֎��s�̔��� <BR>
     * �@@�@@�����P��.������ԁ�NOT_MODIFIED(�������s(�ύX����)) ���A<BR> 
     * �@@�@@�����P��.���������E����敪��"W�w�l�����ؑ֎��s"�̏ꍇ�A"�ؑ֒���(���s)"��Ԃ��B<BR> 
     * <BR>
     * �@@�P�|�P�O�j�@@��L�ȊO�̏ꍇ�́A�����P��.�������.intValue�𕶎���ŕԂ��B<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem( ).getBizDate( )<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrderStatusType(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getOrderStatusType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        IfoOrderUnitRow l_orderUnitRow =
             (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderStatus = null;

        //�敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)
        String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);

        //�P�|�P�j�@@�ꕔ�����̔���
        if (l_orderUnit.isPartiallyExecuted()
            && OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
            && OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
        {
            l_strOrderStatus = WEB3OrderStatusDef.PART_INAFFECTED;
        }
        //�P�|�Q�j�@@�S�������̔���
        else if (l_orderUnit.isUnexecuted()
            && OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
            && OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
        {
            l_strOrderStatus = WEB3OrderStatusDef.FULL_INAFFECTED;
        }
        //�P�|�R�j�@@�J�z���s�̔���
        else if ((!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_strExpirationDateType))
            && (WEB3DateUtility.compareToDay(
                l_orderUnit.getExpirationTimestamp(), GtlUtils.getTradingSystem().getBizDate()) >= 0)
            && OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()) 
            && OrderExpirationStatusEnum.EXPIRED.equals(l_orderUnit.getExpirationStatus()) 
            && (!WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED.equals(l_orderUnitRow.getErrorReasonCode()))
            && (!WEB3ErrorReasonCodeDef.NORMAL.equals(l_orderUnitRow.getErrorReasonCode())))
        {
            l_strOrderStatus = WEB3OrderStatusDef.NOT_TRANSFERED;
        }
        //�P�|�S�j�@@�����̔���
        else if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
            && OrderExpirationStatusEnum.EXPIRED.equals(l_orderUnit.getExpirationStatus()))
        {
            l_strOrderStatus = WEB3OrderStatusDef.CLOSED;
        }
        //�P�|�T�j�@@�J�z�ς̔���
        else if (OrderStatusEnum.ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
            && l_orderUnitRow.getFirstOrderUnitId() > 0)
        {
            l_strOrderStatus = WEB3OrderStatusDef.TRANSFERED;
        }
        // �@@�P�|�U�j�@@�ؑ֒����̔��� 
        // �@@�@@�����P��.������ԁ�MODIFY_ACCEPTED(��t��(�ύX����)) ���A 
        // �@@�@@�����P��.���������E����敪��"W�w�l�����ؑ֒�"�̏ꍇ�A"�ؑ֒���"��Ԃ��B 
        else if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
    		&& WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(
				l_orderUnitRow.getModifyCancelType()))
        {
        	l_strOrderStatus = WEB3OrderStatusDef.TRANSFER_ORDER;
        }
        
        // �@@�P�|�V�j�@@�ؑ֎�t�̔��� 
        // �@@�@@�����P��.������ԁ�MODIFYING(������(�ύX����)) ���A 
        // �@@�@@�����P��.���������E����敪��"W�w�l�����ؑ֒�"�̏ꍇ�A"�ؑ֎�t"��Ԃ��B 
        else if (OrderStatusEnum.MODIFYING.equals(l_orderUnitRow.getOrderStatus())
    		&& WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(l_orderUnitRow.getModifyCancelType()))
        {
        	l_strOrderStatus = WEB3OrderStatusDef.TRANSFER_ACCEPT;
        }
        
        // �@@�P�|�W�j�@@�ؑ֊����̔��� 
        // �@@�@@�����P��.������ԁ�MODIFIED(�����ς�(�ύX����)) ���A 
        // �@@�@@�����P��.���������E����敪���i"W�w�l�����ꕔ�ؑ֊���"�A"W�w�l�����S���ؑ֊���"�j
        // �@@�@@�̏ꍇ�A"�ؑ֊���"��Ԃ��B 
        else if (OrderStatusEnum.MODIFIED.equals(l_orderUnitRow.getOrderStatus())
    		&& (WEB3ModifyCancelTypeDef.W_LIMIT_PARTIALLY_TRANSFERED.equals(
				l_orderUnitRow.getModifyCancelType()) 
				|| WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERED.equals(l_orderUnitRow.getModifyCancelType())))
        {
        	l_strOrderStatus = WEB3OrderStatusDef.TRANSFERRED;
        }
        
        // �@@�P�|�X�j�@@�ؑ֎��s�̔��� 
        // �@@�@@�����P��.������ԁ�NOT_MODIFIED(�������s(�ύX����)) ���A 
        // �@@�@@�����P��.���������E����敪��"W�w�l�����ؑ֎��s"�̏ꍇ�A"�ؑ֒���(���s)"��Ԃ��B 
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_orderUnitRow.getOrderStatus())
    		&& WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR.equals(
				l_orderUnitRow.getModifyCancelType()))
        {
        	l_strOrderStatus = WEB3OrderStatusDef.TRANSFER_ORDER_FAIL;
        }
        
        // �@@�P�|�P�O�j�@@��L�ȊO�̏ꍇ�́A�����P��.�������.intValue�𕶎���ŕԂ��B
        else
        {
            l_strOrderStatus = Integer.toString(l_orderUnit.getOrderStatus().intValue());
        }
        
        log.debug("������ԋ敪 = " + l_strOrderStatus);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_strOrderStatus;
    }
    
    /**
     * (get����ԋ敪)<BR>
     * �p�����[�^.�����P�ʂ��APR�w�Ŏg�p�������ԋ敪��ԋp����B<BR>
     * <BR>
     * �P�|�P�j�p�����[�^.�����P��.isPartiallyExecuted( ) == true�̏ꍇ�́A<BR>
     * "�ꕔ����"��Ԃ��B<BR>  
     * �P�|�Q�j�����P��.isFullyExecuted( ) == true�̏ꍇ�́A<BR>
     * "�S������"��Ԃ��B<BR> 
     * �P�|�R�j��L�ȊO�̏ꍇ�́A"�����"��Ԃ��B<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g�B
     * @@return String
     */
    public static String getExecStatusType(IfoOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "getExecStatusType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�ԊҒl�̐ݒ�
        String l_strReturn = null;
        
        if (l_orderUnit.isPartiallyExecuted())
        {
            l_strReturn = WEB3IfoExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE;  
        }
        else if (l_orderUnit.isFullyExecuted())
        {
            l_strReturn = WEB3IfoExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE;
        }
        else
        {
            l_strReturn = WEB3IfoExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
        }

        log.debug("����ԋ敪 = " + l_strReturn);
      
        log.exiting(STR_METHOD_NAME);
      
        return l_strReturn;
    }
    
    /**
     * (get���s����)<BR>
     * �����̎��s�����iSONAR�j���AWeb�V�ɂ����鎷�s�������擾���ԋp����B<BR> 
     * <BR>
     * �������̎��s�����iSONAR�j��null�̏ꍇ <BR>
     * �@@�@@null��Ԃ��B <BR>
     * <BR>
     * �������̎��s�����iSONAR�j��"1"�i�������j �̏ꍇ<BR> 
     * �@@�@@IfoOrderExecutionConditionType.NONE�i�����Ȃ��j��Ԃ��B<BR> 
     * <BR>
     * �������̎��s�����iSONAR�j��"3"�i��t�j �̏ꍇ <BR>
     * �@@�@@IfoOrderExecutionConditionType.AT_MARKET_OPEN�i���j��Ԃ��B <BR>
     * <BR>
     * �������̎��s�����iSONAR�j��"4"�i�����j �̏ꍇ <BR>
     * �@@�@@IfoOrderExecutionConditionType.AT_MARKET_CLOSE�i�����j��Ԃ��B<BR> 
     * <BR>
     * �������̎��s�����iSONAR�j��"7"�i�s�o���������s)�j �̏ꍇ <BR>
     * �@@�@@IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED�i�s�o���������s�j<BR> 
     * �@@�@@��Ԃ��B <BR>
     * <BR>
     * �������̎��s�����iSONAR�j����L�ȊO�̏ꍇ <BR>
     * �@@�@@��O��throw����B <BR>
     * �@@�@@class:WEB3BusinessLayerException<BR>
     * �@@�@@tag  :BUSINESS_ERROR_00127<BR>
     * @@param l_strExecutionConditionSONAR - (���s�����iSONAR�j)<BR>
     * SONAR�̎��s�����B <BR>
     * <BR>
     * 1�F�@@������ <BR>
     * 3�F�@@��t <BR>
     * 4�F�@@���� <BR>
     * 7�F�@@�s�o���������s<BR>
     * @@return IfoOrderExecutionConditionType
     * @@throws WEB3BaseException 
     */
    public static IfoOrderExecutionConditionType getExecutionConditionType(
        String l_strExecutionConditionSONAR) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutionConditionType(String)";
        log.entering(STR_METHOD_NAME);
        
        //�ԊҒl�̐ݒ�
        IfoOrderExecutionConditionType l_orderExecutionConditionTypeRen = null;
        
        // �����̎��s�����iSONAR�j��null�̏ꍇ 
        // �@@�@@null��Ԃ��B 
    	if (l_strExecutionConditionSONAR == null)
    	{
    		l_orderExecutionConditionTypeRen = null;
    	}
    	
        // �����̎��s�����iSONAR�j��"1"�i�������j �̏ꍇ 
        // �@@�@@IfoOrderExecutionConditionType.NONE�i�����Ȃ��j��Ԃ��B 
    	else if (WEB3SonarExecutionConditionDef.UNCONDITIONDNESS.equals(l_strExecutionConditionSONAR))
    	{
    		l_orderExecutionConditionTypeRen = IfoOrderExecutionConditionType.NONE;
    	}
    	
        // �����̎��s�����iSONAR�j��"3"�i��t�j �̏ꍇ 
        // �@@�@@IfoOrderExecutionConditionType.AT_MARKET_OPEN�i���j��Ԃ��B
    	else if (WEB3SonarExecutionConditionDef.AT_MARKET_OPEN.equals(l_strExecutionConditionSONAR))
    	{
            l_orderExecutionConditionTypeRen = 
            	IfoOrderExecutionConditionType.AT_MARKET_OPEN;
    	}
    	
        // �����̎��s�����iSONAR�j��"4"�i�����j �̏ꍇ 
        // �@@�@@IfoOrderExecutionConditionType.AT_MARKET_CLOSE�i�����j��Ԃ��B
    	else if (WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE.equals(l_strExecutionConditionSONAR))
    	{
            l_orderExecutionConditionTypeRen = 
            	IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
    	}
    	
        // �����̎��s�����iSONAR�j��"7"�i�s�o���������s)�j �̏ꍇ 
        // �@@�@@IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED�i�s�o���������s�j
        // �@@�@@��Ԃ��B 
    	else if (WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER.equals(
			l_strExecutionConditionSONAR))
    	{
    		l_orderExecutionConditionTypeRen = 
    			IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED;
    	}

        // �����̎��s�����iSONAR�j����L�ȊO�̏ꍇ 
        // �@@�@@��O��throw����B 
        else
        {
            log.debug("BUSINESS_ERROR_00127:���s�����̒l�����݂��Ȃ��R�[�h�l�ł��B");
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00127,
                WEB3IfoDataAdapter.class.getName() + "." + STR_METHOD_NAME,
                "���s�����̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }
    	log.exiting(STR_METHOD_NAME);
		return l_orderExecutionConditionTypeRen;
    }
    
    /**
     * (get���s�����iPR�w�j)<BR>
     * �p�����[�^.���s�������APR�w�p�̎��s�����̃R�[�h��ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.���s������IfoOrderExecutionConditionType.NONE<BR>
     * �i�����Ȃ��j�̏ꍇ�A"������"��Ԃ��B<BR>
     * <BR>
     * �Q�j�p�����[�^.���s������IfoOrderExecutionConditionType.AT_MARKET_OPEN<BR>
     * �i���j�̏ꍇ�A"��t"��Ԃ��B<BR>
     * <BR>
     * �R�j�p�����[�^.���s������<BR>
     * IfoOrderExecutionConditionType.AT_MARKET_CLOSE�i�����j�̏ꍇ�A<BR>
     * "����"��Ԃ��B<BR>
     * <BR>
     * �S�j�p�����[�^.���s������<BR>
     * IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED
     * <BR>
     * �i�s�o���������s�j�̏ꍇ�A"�s�o���������s"��Ԃ��B<BR>
     * <BR>
     * �T�j�p�����[�^.���s��������L�ȊO�̏ꍇ�́A��O��throw����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00127<BR>
     * @@param l_executionCondition - ���s�����B
     * @@throws WEB3BusinessLayerException
     * @@return String
     */
    public static String getExecutionCondByPr(IfoOrderExecutionConditionType l_executionCondition)
    throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME =
            "getExecutionCondByPr(IfoOrderExecutionConditionType)";

        log.entering(STR_METHOD_NAME);

        //�ԊҒl�̐ݒ�
        String l_strReturn = null;

        if (IfoOrderExecutionConditionType.NONE.equals(l_executionCondition))
        {
            //����.���s�����F�����Ȃ�
            l_strReturn = WEB3ExecutionConditionDef.NO_CONDITION;
        }
        else if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_executionCondition))
        {
            //����.���s�����F���
            l_strReturn = WEB3ExecutionConditionDef.AT_MARKET_OPEN;
        }
        else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_executionCondition))
        {
            //����.���s�����F����
            l_strReturn = WEB3ExecutionConditionDef.AT_MARKET_CLOSE;
        }
        else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_executionCondition))
        {
            //����.���s�����F�s�o���������s
            l_strReturn = WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED;
        }
        else
        {
            log.error("BUSINESS_ERROR_00127:���s�����̒l�����݂��Ȃ��R�[�h�l�ł��B");
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00127,
                WEB3IfoDataAdapter.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }
        log.debug("���s���� = " + l_strReturn);

        log.exiting(STR_METHOD_NAME);

        return l_strReturn;
    }
    
    /**
     * (get�v�w�l�p���s�����ꗗ)<BR>
     * �����̎��s�����ꗗ���A<BR> 
     * �v�w�l�p�̎��s�����ꗗ���쐬���ԋp����B <BR>
     * <BR>
     * [�����̔��������ꗗ��"W�w�l"���܂܂�Ă��Ȃ��ꍇ]  <BR>
     * �@@�@@null��ԋp����B <BR>
     * <BR>
     * [�����̎��s�����ꗗ��"�s�o���������s"���܂܂��ꍇ] <BR>
     * �@@�ȉ��̎��s������v�f�Ƃ���z���ԋp����B<BR> 
     * �@@�@@�E"������" <BR>
     * �@@�@@�E"�s�o���������s" <BR>
     * [��L�ȊO�̏ꍇ] <BR>
     * �@@"������"�݂̂�v�f�Ƃ���z���ԋp����B<BR>
     * @@param l_strExecutionConditionTypeList - (���s�����ꗗ)<BR>
     * ���s�����ꗗ <BR>
     * ���ȉ��̒l����\�������z�� <BR>
     * <BR>
     * 1�F������ <BR>
     * 3�F��t <BR>
     * 4�F���� <BR>
     * 7�F�s�o���������s<BR>
     * @@param l_strOrderConditionList - (���������ꗗ)<BR>
     * ���������ꗗ <BR>
     * @@return String[]
     * @@throws WEB3BaseException 
     */
    public static String[] getWLimitExecutionConditionTypeList(
        String[] l_strExecutionConditionTypeList,
        String[] l_strOrderConditionList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getWLimitExecutionConditionTypeList(String[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_strExecutionConditionTypeList == null 
            || l_strExecutionConditionTypeList.length == 0)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3IfoDataAdapter." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        int l_intConditionLisLength = l_strOrderConditionList.length;
        boolean l_blnReturn = true;
        for (int i = 0; i < l_intConditionLisLength; i++)
        {
            //[�����̔��������ꗗ��"W�w�l"���܂܂�Ă��Ȃ��ꍇ]
            //�@@null��ԋp����B
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionList[i]))
            {
                l_blnReturn = false;
                break;
            }
        }
        if (l_blnReturn)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // [�����̎��s�����ꗗ��"�s�o���������s"���܂܂��ꍇ] 
        // �@@�ȉ��̎��s������v�f�Ƃ���z���ԋp����B 
        // �@@�@@�E"������" 
        // �@@�@@�E"�s�o���������s"  
        int l_intListLength = 0;
        if (l_strExecutionConditionTypeList != null && 
            l_strExecutionConditionTypeList.length > 0)
        {
            l_intListLength = l_strExecutionConditionTypeList.length;
        }
        
        for (int i = 0; i < l_intListLength; i++)
        {
        	if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(
    			l_strExecutionConditionTypeList[i]))
        	{
        		String[] l_strWLimitExecutionConditionTypeList = 
                    {WEB3ExecutionConditionDef.NO_CONDITION, 
    				WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED};
                log.exiting(STR_METHOD_NAME);
        		return l_strWLimitExecutionConditionTypeList;
        	}
        }
        
        // [��L�ȊO�̏ꍇ] 
        // �@@"������"�݂̂�v�f�Ƃ���z���ԋp����B
		String[] l_strWLimitExecutionConditionTypeList = 
            {WEB3ExecutionConditionDef.NO_CONDITION};
        log.exiting(STR_METHOD_NAME);
        return l_strWLimitExecutionConditionTypeList;
    }

	/**
	 * (get�v�w�l�p���s�����ꗗ)<BR>
	 * (get�v�w�l�p���s�����ꗗ�̃I�[�o�[���[�h���\�b�h)<BR>
	 * <BR>
	 * �����̒����P�ʁA���s�����ꗗ���A<BR>
	 * �v�w�l�p�̎��s�����ꗗ���쐬���ԋp����B<BR>
	 * <BR>
     * �P�j�@@�����̒����P��.����������"W�w�l"�łȂ��ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
	 * �Q�j�@@�����̒����P�ʂ��o����܂Œ���<BR>
	 * �@@�iOP�����}�l�[�W��.is�o����܂Œ����P��(�����̒����P��) == true�j�̏ꍇ�A<BR>
	 * �@@"������"�݂̂�v�f�Ƃ���z���ԋp����B<BR>
	 * <BR>
     * �R�j�@@�����̒����P�ʂ��[��܂Œ��� <BR>
     * �@@�iOP�����}�l�[�W��.is�[��܂Œ���(�����̒����P��) == true�j�̏ꍇ�A <BR>
     * �@@"������"�݂̂�v�f�Ƃ���z���ԋp����B <BR>
     * <BR>
     * �S�j�@@��L�ȊO�̏ꍇ�A <BR>
     * �@@this.getW�w�l�p���s�����ꗗ(�����̎��s�����ꗗ, �����̒����P��.��������)���\�b�h�� <BR>
     * �@@�R�[�����A�߂�l��ԋp����B<BR>
	 * <BR>
	 * @@param l_strExecutionConditionTypeList - (���s�����ꗗ)<BR>
	 * ���s�����ꗗ <BR>
	 * ���ȉ��̒l����\�������z�� <BR>
	 * <BR>
	 * 1�F������<BR>
	 * 3�F��t<BR>
	 * 4�F����<BR>
	 * 7�F�s�o���������s<BR>
	 * @@param l_ifoOrderUnit - (�����P�ʃI�u�W�F�N�g) <BR>
	 * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String[]
     * @@throws WEB3BaseException 
	 */
	public static String[] getWLimitExecutionConditionTypeList(
			String[] l_strExecutionConditionTypeList,
			IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getWLimitExecutionConditionTypeList(String[]. IfoOrderUnit)";
		log.entering(STR_METHOD_NAME);

        // �@@�����̒����P��.����������"W�w�l"�łȂ��ꍇ�A<BR>
        // �@@null��ԋp����B<BR>
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        String l_strOrderCondetionType =
            l_orderUnitRow.getOrderConditionType();
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

		FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
		TradingModule l_tradingModule = 
			l_finApp.getTradingModule(ProductTypeEnum.IFO);
		WEB3OptionOrderManagerImpl l_orderManager = 
			(WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

		// �@@�����̒����P�ʂ��o����܂Œ���
		// �@@�iOP�����}�l�[�W��.is�o����܂Œ����P��(�����̒����P��) == true�j�̏ꍇ�A 
		// �@@"������"�݂̂�v�f�Ƃ���z���ԋp����B 
        //�@@�����̒����P�ʂ��[��܂Œ���
        //�@@�iOP�����}�l�[�W��.is�[��܂Œ���(�����̒����P��) == true�j�̏ꍇ�A
        //�@@"������"�݂̂�v�f�Ƃ���z���ԋp����B
		if (l_orderManager.isCarriedOrderUnit(l_ifoOrderUnit)
            || l_orderManager.isEveningSessionOrder(l_ifoOrderUnit))
		{
			String[] l_strWLimitExecutionConditionTypeList =
				{WEB3ExecutionConditionDef.NO_CONDITION};
            
			log.exiting(STR_METHOD_NAME);
			return l_strWLimitExecutionConditionTypeList;
		}

		// �@@��L�ȊO�̏ꍇ�A 
		// �@@this.getW�w�l�p���s�����ꗗ(�����̎��s�����ꗗ, �����̒����P��.��������)���\�b�h�� 
		// �@@�R�[�����A�߂�l��ԋp����B 
		log.exiting(STR_METHOD_NAME);
		return getWLimitExecutionConditionTypeList(
			l_strExecutionConditionTypeList,
            new String[] {l_strOrderCondetionType});
	}

    /**
     * (get�����󋵋敪)<BR>
     * �p�����[�^.�����P�ʁA����������ʂ��<BR>
     * PR�w�Ŏg�p���锭���󋵋敪��ԋp����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.����������ʁ�"�t�w�l"�̏ꍇ�A<BR>
     * �@@�@@this.get�t�w�l�����󋵋敪()���R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.����������ʁ�"W�w�l"�̏ꍇ�A<BR> 
     * �@@�@@this.getW�w�l�����󋵋敪()���R�[�����A�߂�l��ԋp����B <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_strTriggerOrderType - (�����������)<BR>
     * �����������<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getTriggerOrderStatusType(
		IfoOrderUnit l_orderUnit, 
		String l_strTriggerOrderType) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getTriggerOrderStatusType(IfoOrderUnit, String)";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3TriggerOrderTypeDef.STOP.equals(l_strTriggerOrderType))
        {
            String l_strManualOrder = getStopTriggerOrderStatusType(l_orderUnit);
            
            log.exiting(STR_METHOD_NAME);
            return l_strManualOrder;
        }
        // �Q�j�@@�p�����[�^.����������ʁ�"W�w�l"�̏ꍇ�A 
        // �@@�@@this.getW�w�l�����󋵋敪()���R�[�����A�߂�l��ԋp����B 
        if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_strTriggerOrderType))
        {
            String l_strOrderStatusType = getWLimitOrderStatusType(l_orderUnit);
             
            log.exiting(STR_METHOD_NAME);
            return l_strOrderStatusType;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get�t�w�l�����󋵋敪)<BR>
     * �p�����[�^.�����P�ʂ��<BR>
     * PR�w�Ŏg�p����t�w�l�����̔����󋵋敪��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����󋵋敪�̔���<BR>
     * <BR>
     * �@@�P�|�P�j�@@�����x���G���[�̔��� 
     *�@@�@@OP�����}�l�[�W��.is�������x������(�����P��) == true�̏ꍇ�A 
     *�@@�@@"�����x���G���["��ԋp����B
     * �@@�P�|�Q�j�@@�ҋ@@���̔���<BR>
     * �@@�@@�����P��.���N�G�X�g�^�C�v��"DEFAULT"�̏ꍇ�A"�ҋ@@��"��ԋp����B<BR>
     * <BR>
     * �@@�P�|�R�j�@@�������A���������̔��� <BR>
     * �@@�@@�����P��.���N�G�X�g�^�C�v��"�����T�[�o"�̏ꍇ�A<BR> 
     * �@@�@@�|�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ�A"������"<BR>
     * �@@�@@�|�ȊO�A"��������" <BR>
     * �@@�@@��ԋp����B<BR>
     * <BR>
     * �@@�P�|�S�j�@@�����R���G���[�̔���<BR>
     * �@@�@@�����P��.���N�G�X�g�^�C�v��"�������s"�̏ꍇ�A"�����R���G���["��ԋp����B<BR>
     * <BR>
     * �@@�P�|�T�j�@@��L�ȊO�̏ꍇ�A"���̑�"��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     */
    public static String getStopTriggerOrderStatusType(IfoOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            " getStopTriggerOrderStatusType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        String l_strTriggerOrderStatus = null;
        IfoOrderUnitRow l_orderUnitRow =
             (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
        //�P�|1 �j�@@�����x���G���[�̔���
        //�@@�@@OP�����}�l�[�W��.is�������x������(�����P��) == true�̏ꍇ�A
        //�@@�@@"�����x���G���["��ԋp����B
        if (l_orderManager.isNotOrderedDelay(l_orderUnit))
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR;
        }
        //�P�|2 �j�@@�ҋ@@���̔���
        else if (WEB3RequestTypeDef.DEFAULT.equals(l_orderUnitRow.getRequestType()))
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_WAITING;
        }
        //�P�|3�j�@@�������A���������̔���
        else if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_orderUnitRow.getRequestType()))
        {
        	//�|�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ�A"������"
        	if (l_orderUnitRow.getConfirmedQuantityIsNull())
        	{
        		l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDERING;
        	}
        	else
        	{
                l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_COMPLETE;
        	}
        }
        //�P�|�S�j�@@�����R���G���[�̔���
        else if (WEB3RequestTypeDef.ORDER_FAILURE.equals(l_orderUnitRow.getRequestType()))
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_VALIDATE_ERROR;
        }
        //�P�|�T�j�@@��L�ȊO�̏ꍇ�A"���̑�"��ԋp����B
        else
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.OTHER;
        }
        log.debug("�t�w�l�����󋵋敪 = " + l_strTriggerOrderStatus);

        log.exiting(STR_METHOD_NAME);

        return l_strTriggerOrderStatus;
    }

    /**
     * (get���i�敪)<BR>
     * �����̒����P�ʂ��PR�w�Ŏg�p���鏤�i�敪��ԋp����B<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ɂ�蕪�򂵁A�Ή����鏤�i�敪��ԋp����B<BR>
     * <BR>
     * �@@[�p�����[�^.�����P��.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@"�I�v�V����"��ԋp����B<BR>
     * <BR>
     * �@@[�p�����[�^.�����P��.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ]<BR>
     * �@@�@@"�敨"��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getCommodityDiv(IfoOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "getCommodityDiv(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        IfoOrderUnitRow l_orderUnitRow = 
            (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        String l_strFutureOptionDiv = l_orderUnitRow.getFutureOptionDiv();
        String l_strReturn = null;
        
        if (WEB3FuturesOptionDivDef.OPTION.equals(l_strFutureOptionDiv))
        {
            //[�p�����[�^.�����P��.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ]
            //�@@"�I�v�V����"��ԋp����B
            l_strReturn = WEB3CommodityDivDef.OPTION;
        }
        else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFutureOptionDiv))
        {
            //[�p�����[�^.�����P��.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ]
            //�@@"�敨"��ԋp����B
            l_strReturn = WEB3CommodityDivDef.FUTURE;
        }
        
        log.debug("���i�敪 = " + l_strReturn);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_strReturn;
    }
    
    /**
     * (get�����󋵋敪)<BR>
     * �w�肳�ꂽ�����P�ʂ̏����󋵋敪��Ԃ��B<BR>
     * <BR>
     * �߂�l�̏����󋵋敪�F<BR>
     * ���R�[�h�l�̓��b�Z�[�W��`�t�H���_�ȉ���<BR>
     * �uү���ޒ�`��_�����w���敨(����).xls�v�̏����󋵋敪��`���Q�ƁB<BR>
     * <BR>
     * �P�j��t�敪���擾����B<BR>
     *   this.get��t�敪()���R�[������B<BR>
     * <BR>
     *  [����]<BR>
     *      �����P�ʁF�@@�p�����[�^.�����P��<BR> 
     * <BR>
     * �Q�j ���敪���擾����B<BR>
     * �@@this.get����ԋ敪()���R�[������B<BR>
     * <BR>
     *  [����]<BR>
     *      �����P�ʁF�@@�p�����[�^.�����P��<BR> 
     * <BR>
     * �R�j �����敪�𔻒肷��B<BR>
     * �@@this.get������ԋ敪()���R�[������B<BR>
     * <BR>
     *  [����]<BR>
     *      �����P�ʁF�@@�p�����[�^.�����P��<BR> 
     * <BR>
     * �@@�߂�l���h�ꕔ�����h�̏ꍇ�́A�����敪�F"�ꕔ����"�B<BR>
     * �@@�߂�l���h�S�������h�̏ꍇ�́A�����敪�F"�S������"�B<BR>
     * �@@��L�ȊO�̏ꍇ�́A�����敪�F"�����Ȃ�"�B<BR>
     * <BR>
     * �S�j �����󋵋敪�𔻒肷��B<BR>
     * �@@��t�敪 ��"��t�G���["�̏ꍇ�A �u��t�G���[�v��ԋp����B<BR>
     * �@@�ȊO�̏ꍇ�A��t�敪 + ���敪 + �����敪 + �����P��.���������E����敪�̕�����A���l��ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return String
     */
    public static String getTransactionStatusType(IfoOrderUnit l_orderUnit)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTransactionStatusType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //��t�敪
        String l_strAcceptType = getAcceptType(l_orderUnit);
        
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //���敪
        String l_strExecType = getExecStatusType(l_orderUnit);
        
        //�����敪
        String l_strExpirationType;        
        String l_strOrderStatusType = getOrderStatusType(l_orderUnit);
        if (WEB3OrderStatusDef.PART_INAFFECTED.equals(l_strOrderStatusType))
        {
            l_strExpirationType = WEB3IfoExpirationStatusDef.PARTIALLY_CLOSE;
        }
        else if (WEB3OrderStatusDef.FULL_INAFFECTED.equals(l_strOrderStatusType))
        {
            l_strExpirationType = WEB3IfoExpirationStatusDef.ALL_CLOSE;
        }
        else
        {
            l_strExpirationType = WEB3IfoExpirationStatusDef.DEFAULT;
        }
        
        //�����P��.���������E����敪
        String l_strModifyCancelType = l_orderUnitRow.getModifyCancelType();
        
        //�����󋵋敪
        String l_strReturn = null;
        
        //��t�敪����t�G���[�̏ꍇ                         
        if (WEB3IfoAcceptTypeDef.EXEC_TYPE_ERROR.equals(l_strAcceptType))                           
        {                           
            l_strReturn = l_strAcceptType 
            + WEB3IfoExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE 
            + WEB3IfoExpirationStatusDef.DEFAULT
            + WEB3ModifyCancelTypeDef.INITIAL_VALUE; 
        }
        else
        {                                                   
            l_strReturn = l_strAcceptType
            + l_strExecType 
            + l_strExpirationType 
            + l_strModifyCancelType;
        }
        
        log.debug("�����󋵋敪 = " + l_strReturn);
        
        log.exiting(STR_METHOD_NAME);                           
        
        return l_strReturn;
    }
    
    /**
     * (get��t�敪)<BR>
     * �w�肳�ꂽ�����P�ʂ̎�t�敪��Ԃ��B<BR>
     * <BR>
     * �߂�l�̎�t�敪�F<BR>
     * 0:��t���ρ@@1:��t�ρ@@2:��t�G���[  4:�����҂��@@6:���c�Ɠ������@@7:����������<BR>
     * <BR>
     * �P�j�@@�ȉ������S�ĂɊY������ꍇ�̂ݏ�������B<BR>
     * �@@�@@�@@�@@�E�����P��.������ �� ������ԊǗ�.get������()<BR>
     * �@@�@@�@@�@@�E�s��ǒ��@@�i������ԊǗ�.is�s��J�ǎ��ԑ�()��"false"�̏ꍇ�j<BR>
     * <BR>
     *�@@�P�|�P�j�@@�����P��.������ �� �Ɩ����t�̏ꍇ<BR>
     *�@@�@@�@@�@@�@@�@@�@@"���c�Ɠ�����"��ԋp����B<BR>
     * <BR>
     *�@@�P�|�Q�j�@@������ԊǗ�.get�c�Ɠ��敪(���ݓ���)��"��c�Ɠ�"�̏ꍇ<BR>
     *�@@�@@�@@�@@�@@�@@�@@"���c�Ɠ�����"��ԋp����B<BR>
     * <BR>
     *�@@�P�|�R�j�@@�����P��.������ �� �Ɩ����t�̏ꍇ<BR>
     *�@@�@@�@@�@@�@@�@@ - ������ԊǗ�.is�I�����C���T�[�r�X�J�n��()��false�̏ꍇ<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"���c�Ɠ�����"��ԋp����B<BR>
     *�@@�@@�@@�@@�@@�@@ - ������ԊǗ�.is�I�����C���T�[�r�X�J�n��()��true���A<BR>
     *�@@�@@�@@�@@�@@�@@�@@  ���ݓ����̎��ԕ��� �� �s��ǎ��ԁi���j�̏ꍇ<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"����������"��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@ �i���j�s��ǎ���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@ �����P��.get����().get�����Y�����R�[�h()���R�[������B<BR>
     * <BR>
     *�@@�@@ �@@�@@�@@�@@�@@�@@�A ������ԊǗ�.get�s��ǎ���()���R�[������B<BR>
     *�@@�@@�@@ �@@�@@�@@�@@�@@�@@�@@�@@[get�s��ǎ���()�ɐݒ肷�����]<BR>
     *�@@�@@�@@�@@ �@@�@@�@@�@@�@@�@@�@@�s��R�[�h�F�@@"0�FDEFAULT"<BR>
     *�@@�@@�@@�@@�@@ �@@�@@�@@�@@�@@�@@���i�R�[�h�F�@@�@@�����̖߂�l<BR>
     * <BR>
     * �Q�j�@@�ȉ������S�ĂɊY������ꍇ�̂ݏ�������B<BR>
     * �@@�@@�@@�@@�E�s��J�ǒ��@@�i������ԊǗ�.is�s��J�ǎ��ԑ�()��"true"�̏ꍇ�j<BR>
     * �@@�@@�@@�@@�E����ԑтł͂Ȃ��i������ԊǗ�.is����ԑ�()��"false"�̏ꍇ�j <BR>
     * <BR>
     * �@@�Q�|�P�j�@@�����P��.����敪 �� ������ԊǗ�.����敪�̏ꍇ<BR>
     * �@@�@@�@@�@@- �����P��.�������� ������ԊǗ�.get������()�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@"����������"��ԋp����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@��L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�R�j�ȍ~�̏����ΏۂƂ���<BR>
     * <BR>
     * �R�j�@@�������̗L���ȋt�w�l�����Ŏs��J�ǎ��ԑт̏ꍇ<BR>
     * <BR>
     * �@@�����P��.����������"�t�w�l"�A���A<BR>
     * �@@�����P��.���N�G�X�g�^�C�v��"DEFAULT"�A���A<BR>
     * �@@�����P��.�����L����ԁ�"�I�[�v��"�A���A<BR>
     * �@@������ԊǗ�.is����ԑ�()��true�̏ꍇ�A"�����҂�"��ԋp����B<BR>
     * <BR>
     * �S�j�@@�����������̏ꍇ<BR>
     * <BR>
     *�@@�@@�����P��.�s�ꂩ��m�F�ς̐��ʁ�NaN�̏ꍇ�A<BR>
     *�@@�@@- �����P��.������ԁ�"�������s(�V�K����)"�̏ꍇ�́A"��t�G���["�A<BR>
     *�@@�@@- �����P��.������ԁ�"�������s(�V�K����)"�̏ꍇ�́A"��t����"��ԋp����B<BR>
     * <BR>
     * �T�j�@@��L�ȊO�̏ꍇ�́A"��t��"��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getAcceptType(IfoOrderUnit l_orderUnit)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAcceptType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_strAcceptType = null;

        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        try
        {
            //������ԊǗ�.get������()
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //�����P��.������
            Date l_datOrderUnitBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            //�ȉ������S�ĂɊY������ꍇ�̂ݏ�������B
            //�@@�E�����P��.������ �� ������ԊǗ�.get������()
            //�@@�E�s��ǒ��@@�i������ԊǗ�.is�s��J�ǎ��ԑ�()��"false"�̏ꍇ�j
            if ((WEB3DateUtility.compareToDay(l_datOrderUnitBizDate, l_datOrderBizDate) >= 0) &&
                !(WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone()))
            {
                //�Ɩ����t�擾
                Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

                //���ݓ����擾
                Timestamp l_tsSysTimestamp = GtlUtils.getSystemTimestamp();

                //�c�Ɠ��敪�擾
                String l_strBizDateType = 
                    WEB3GentradeTradingTimeManagement.getBizDateType(l_tsSysTimestamp);

                //�����P��.������ �� �Ɩ����t�̏ꍇ
                if (WEB3DateUtility.compareToDay(l_datOrderUnitBizDate, l_datBizDate) > 0)
                {
                    l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_NEXT_ORDER;
                    return l_strAcceptType;
                }
                //������ԊǗ�.get�c�Ɠ��敪(���ݓ���)��"��c�Ɠ�"�̏ꍇ    
                else if (l_strBizDateType.compareTo(WEB3BizDateTypeDef.NO_BIZ_DATE) == 0)
                {
                    l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_NEXT_ORDER;
                    return l_strAcceptType;
                }
                //�����P��.������ �� �Ɩ����t�̏ꍇ
                else if (WEB3DateUtility.compareToDay(l_datOrderUnitBizDate, l_datBizDate) == 0)
                {
                    //������ԊǗ�.is�I�����C���T�[�r�X�J�n��()��false�̏ꍇ
                    if (!(WEB3GentradeTradingTimeManagement.isOnlineServiceStartAfter()))
                    {
                        l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_NEXT_ORDER;
                        return l_strAcceptType;
                    }

                    //�s��ǎ��Ԃ��擾����
                    String l_strTradeCloseTime = 
                        WEB3GentradeTradingTimeManagement.getTradeCloseTime(
                            WEB3MarketCodeDef.DEFAULT, 
                            ((IfoProductImpl)l_orderUnit.getProduct()).getUnderlyingProductCode());

                    String l_strBizDate = 
                        GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datBizDate);

                    Date l_datTradeCloseTime = WEB3DateUtility.getDate(
                            l_strBizDate + l_strTradeCloseTime,
                            "yyyyMMddHHmmss");

                    //������ԊǗ�.is�I�����C���T�[�r�X�J�n��()��true����
                    //���ݓ����̎��ԕ��� �� ������ԊǗ�.get�s��ǎ���()�̏ꍇ
                    if (WEB3GentradeTradingTimeManagement.isOnlineServiceStartAfter() &&
                        WEB3DateUtility.compareToSecond(l_tsSysTimestamp, l_datTradeCloseTime) <= 0)
                    {
                        l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_UNORDER;
                        return l_strAcceptType;
                    }
                }
            }

            //�Q�j�@@�ȉ������S�ĂɊY������ꍇ�̂ݏ�������0�B
            //�@@�@@�@@�@@�E�s��J�ǒ��@@�i������ԊǗ�.is�s��J�ǎ��ԑ�()��"true"�̏ꍇ�j
            //�@@�@@�@@�@@�E����ԑтł͂Ȃ��i������ԊǗ�.is����ԑ�()��"false"�̏ꍇ�j
            //           �����P��.����敪 �� ������ԊǗ�.����敪�̏ꍇ
            //           - �����P��.�������� ������ԊǗ�.get������()�̏ꍇ
            if (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone()
                && !WEB3GentradeTradingTimeManagement.isSessionTimeZone()
                && WEB3Toolkit.isEquals(l_orderUnitRow.getSessionType(),
                    WEB3GentradeTradingTimeManagement.getSessionType())
                && (WEB3DateUtility.compareToDay(l_datOrderUnitBizDate, l_datOrderBizDate) == 0))
            {
                //"����������"��ԋp����B
                l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_UNORDER;
            }

            //�������̗L���ȋt�w�l�����Ŏs��J�ǎ��ԑт̏ꍇ
            else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()) &&
                WEB3RequestTypeDef.DEFAULT.equals(l_orderUnitRow.getRequestType()) &&
                OrderOpenStatusEnum.OPEN.equals(l_orderUnitRow.getOrderOpenStatus()) &&
                WEB3GentradeTradingTimeManagement.isSessionTimeZone())
            {
                l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_WAITING;
            }
            //�����������̏ꍇ
            else if (l_orderUnitRow.getConfirmedQuantityIsNull())
            {
                if (OrderStatusEnum.NOT_ORDERED.equals(l_orderUnitRow.getOrderStatus()))
                {
                    l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_ERROR;
                }
                else
                {
                    l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_NAN;
                }
            }
            else
            {
                l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_NOT_NAN;
            }

            log.debug("��t�敪 = " + l_strAcceptType);

            log.exiting(STR_METHOD_NAME);

            return l_strAcceptType;

        }
        finally
        {
            log.debug("��t�敪 = " + l_strAcceptType);

            log.exiting(STR_METHOD_NAME);

            return l_strAcceptType;
        }
    }

    /**
     * (get�������e�敪)<BR>
     * �w�肳�ꂽ���������̒������e�敪��Ԃ��B<BR>
     * <BR>
     * �߂�l�̒������e�敪�F<BR>
     * 00�F�V�K���� 01�F������t 02�F�V�K����(���s)<BR>
     * 03�F�������� 04�F������t 05�F�������� 06�F��������(���s)<BR>
     * 07�F������� 08�F�����t 09�F������� 10�F�������(���s)<BR>
     * 11�F�ꕔ��� 12�F�S����� 13�F�����<BR>
     * 14�F���� 15�F������� 16�F����<BR>
     * 17�F�����J�z 18�F�����J�z(���s) 20�F������ 21�F�����x�� <BR>
     * 22�F�ؑ֒x�� 23�F�ؑ֒��� 24�F�ؑ֎�t 25�F�ؑ֊��� 26�F�ؑ֒���(���s)<BR>
     * 27�F�X�g�b�v�������� 99�F���̑�<BR>
     * --------------------------------------------------------------------------<BR>
     * (1)���<BR>
     * �@@��������.�����C�x���g�^�C�v��EXECUTE(���)�̏ꍇ�A<BR>
     * �@@�E��������.�������ʁ���������.��萔�ʂȂ�΁A"�S�����"��Ԃ��B<BR>
     * �@@�E��L�ȊO�Ȃ�΁A"�ꕔ���"��Ԃ��B<BR>
     * <BR>
     * (2)����<BR>
     * �@@��������.�����C�x���g�^�C�v��EXPIRE(�����ς�) ����<BR>
     * �@@��������.���������X�e�[�^�X��INVALIDATED_BY_MKT(�}�[�P�b�g����)�̏ꍇ�A"����"��Ԃ��B<BR>
     * <BR>
     * (3)�����J�z(���s)�A����<BR>
     * �@@��������.�����C�x���g�^�C�v��EXPIRE(�����ς�) ����<BR>
     * �@@��������.���������X�e�[�^�X��EXPIRED(�I��)�̏ꍇ�A<BR>
     * �@@�E��������.�����G���[���R�R�[�h��"0000�F����"�Ȃ�΁A"�����J�z(���s)"��Ԃ��B<BR>
     * �@@�E��L�ȊO�ł���΁A"����"��Ԃ��B<BR>
     * <BR>
     * (4)�X�g�b�v�������� <BR>
     * �@@��������.�����C�x���g�^�C�v��EXPIRE(�����ς�) ���� <BR>
     * �@@��������.���N�G�X�g�^�C�v��"����"�̏ꍇ�A"�X�g�b�v��������"��Ԃ��B<BR> 
     * <BR>
     * (5)�������<BR>
     * �@@��������.�����C�x���g�^�C�v��UNDO_INVALIDATION_BY_MKT(�������)�̏ꍇ�A"�������"��Ԃ��B<BR>
     * <BR>
     * (6)�����<BR>
     * �@@��������.�����C�x���g�^�C�v��UNDO_EXECUTION(�����)�̏ꍇ�A"�����"��Ԃ��B<BR>
     * <BR>
     * (7)�����x��<BR>
     * �@@��������.�����C�x���g�^�C�v��ORDER_DELAY�i�����x���j�̏ꍇ�A<BR>
     * �@@"�����x��"��Ԃ��B<BR>
     *<BR>
     * (8)�ؑ֒x�� <BR>
     * �@@��������.�����C�x���g�^�C�v��SWITCH_DELAY�i�ؑ֒x���j�̏ꍇ�A <BR>
     * �@@"�ؑ֒x��"��Ԃ��B <BR>
     * <BR>
     * (9)�V�K�����A�����J�z<BR>
     * �@@��������.������ԁ�ACCEPTED(��t��(�V�K����))�̏ꍇ�A<BR>
     * �@@�E�����P��.���񒍕��̒����P��ID��(null, 0)�Ȃ�΁A"�V�K����"��Ԃ��B<BR>
     * �@@�E��L�ȊO�Ȃ�΁A"�����J�z"��Ԃ��B<BR>
     * <BR>
     * (10)������t<BR>
     * �@@��������.������ԁ�ORDERD(�����ς݁i�V�K����))�̏ꍇ�A"������t"��Ԃ��B<BR>
     * <BR>
     * (11)�V�K����(���s)<BR>
     * �@@��������.������ԁ�NOT_ORDERD(�������s(�V�K����))�̏ꍇ�A<BR>
     * �@@�E"�V�K����(���s)"��Ԃ��B<BR>
     * <BR>
     * (12)���������A�ؑ֒��� <BR>
     * �@@��������.������ԁ�MODIFY_ACCEPTED(��t��(�ύX����))�̏ꍇ�A <BR>
     * �@@�E��������.���������E����敪��"W�w�l�����ؑ֒�"�Ȃ�΁A"�ؑ֒���"��Ԃ��B<BR> 
     * �@@�E��L�ȊO�Ȃ�΁A"��������"��Ԃ��B <BR>
     * <BR>
     * (13)������t�A�ؑ֎�t <BR>
     * �@@��������.������ԁ�MODIFYING(������(�ύX����))�̏ꍇ�A <BR>
     * �@@�E��������.���������E����敪��"W�w�l�����ؑ֒�"�Ȃ�΁A"�ؑ֎�t"��Ԃ��B<BR> 
     * �@@�E��L�ȊO�Ȃ�΁A"������t"��Ԃ��B <BR>
     * <BR>
     * (14)���������A�ؑ֊��� <BR>
     * �@@��������.������ԁ�MODIFIED(�����ς�(�ύX����))�̏ꍇ�A<BR> 
     * �@@�E��������.���������E����敪���i"W�w�l�����ꕔ�ؑ֊���"�A<BR>
     * �@@�@@"W�w�l�����S���ؑ֊���"�j�Ȃ�΁A"�ؑ֊���"��Ԃ��B <BR>
     * �@@�E��L�ȊO�Ȃ�΁A"��������"��Ԃ��B <BR>
     * <BR>
     * (15)��������(���s)�A�ؑ֒���(���s) <BR>
     * �@@��������.������ԁ�NOT_MODIFIED(�������s(�ύX����))�̏ꍇ�A <BR>
     * �@@�E��������.���������E����敪��"W�w�l�����ؑ֎��s"�Ȃ�΁A"�ؑ֒���(���s)"��Ԃ��B<BR> 
     * �@@�E��L�ȊO�Ȃ�΁A"��������(���s)"��Ԃ��B <BR>
     * <BR>
     * (16)�������<BR>
     * �@@��������.������ԁ�CANCEL_ACCEPTED(��t��(�������))�̏ꍇ�A"�������"��Ԃ��B<BR>
     * <BR>
     * (17)�����t<BR>
     * �@@��������.������ԁ�CANCELLING(������(�������))�̏ꍇ�A"�����t"��Ԃ��B<BR>
     * <BR>
     * (18)�������<BR>
     * �@@��������.������ԁ�CANCELLED(�����ς�(�������))�̏ꍇ�A"�������"��Ԃ��B<BR>
     * <BR>
     * (19)�������(���s)<BR>
     * �@@��������.������ԁ�NOT_CANCELLED(�������s(�������))�̏ꍇ�A"�������(���s)"��Ԃ��B<BR>
     * <BR>
     * (20)������<BR>
     * �@@��������.�����C�x���g�^�C�v��SEND_TO_MKT�i�}�[�P�b�g���M�ρi�V�K�����j�j�̏ꍇ�A<BR>
     * �@@"������"��Ԃ��B<BR>
     * <BR>
     * (21)��L�ȊO�̏ꍇ�A"���̑�"��Ԃ��B<BR>
     * <BR>
     * @@param l_orderAction - ���������I�u�W�F�N�g�B
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g�B
     * @@return String
     */
    public static String getOrderSpecType(IfoOrderAction l_orderAction, IfoOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "getOrderSpecType(IfoOrderAction, IfoOrderUnit)";

        log.entering(STR_METHOD_NAME);
        //�ԋp�l�̐ݒ�
        String l_strReturn = null;

        //���������̎擾
        IfoOrderActionRow l_ifoOrderActionRow =
            (IfoOrderActionRow)l_orderAction.getDataSourceObject();
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        OrderEventTypeEnum l_orderEventType = l_ifoOrderActionRow.getOrderEventType();
        String l_strRequestType = l_ifoOrderActionRow.getRequestType();
        
        log.debug("��������.�����C�x���g�^�C�v��" + l_orderEventType);
        OrderExpirationStatusEnum l_orderExpirationStatus = l_ifoOrderActionRow.getExpirationStatus();
        log.debug("���������D���������X�e�[�^�X��" + l_orderExpirationStatus);

        //(1)���
        if (OrderEventTypeEnum.EXECUTE.equals(l_orderEventType))
        {
            if (l_ifoOrderActionRow.getQuantity() == l_ifoOrderActionRow.getExecutedQuantity())
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.FULLY_EXECUTED;
            }
            else
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.PARTIALLY_EXECUTED;
            }
        }
        //(2)���� 
        else if (OrderEventTypeEnum.EXPIRE.equals(l_orderEventType) &&
        OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderExpirationStatus))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.CLOSE;
        }
        //(3)�����J�z(���s)�A����
        else if (OrderEventTypeEnum.EXPIRE.equals(l_orderEventType)
            && OrderExpirationStatusEnum.EXPIRED.equals(l_orderExpirationStatus))
        {                                                                               
            if (!WEB3ErrorReasonCodeDef.NORMAL.equals(l_ifoOrderActionRow.getErrorReasonCode()))
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.ORDER_CARRYOVER_FAIL;
            }
            else
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.INEFFECTIVE;
            }
        }
        
        //(4)�X�g�b�v��������
        else if (OrderEventTypeEnum.EXPIRE.equals(l_orderEventType)
            && WEB3RequestTypeDef.INVALIDATE.equals(l_strRequestType))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.STOP_ORDER_EXPIRE;
        }
        //(5)������� 
        else if (OrderEventTypeEnum.UNDO_INVALIDATION_BY_MKT.equals(l_orderEventType))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.CLOSE_FAIL;
        }
        //(6)����� 
        else if (OrderEventTypeEnum.UNDO_EXECUTION.equals(l_orderEventType))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.EXCUTED_CANCEL;
        }
        //(7)�����x��
        else if (OrderEventTypeEnum.ORDER_DELAY.equals(l_orderAction.getOrderEventType()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.ORDER_DELAY;
        }
        //(8)�ؑ֒x��
        else if (OrderEventTypeEnum.SWITCH_DELAY.equals(l_orderAction.getOrderEventType()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.SWITCH_DELAY;
        }
        //(9)�V�K�����A�����J�z
        else if (OrderStatusEnum.ACCEPTED.equals(l_orderAction.getOrderStatus()))
        {
            if (l_orderUnitRow.getFirstOrderUnitIdIsNull() || (l_orderUnitRow.getFirstOrderUnitId() == 0))
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.OPEN_ORDER;
            }
            else
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.ORDER_CARRYOVER;
            }
        }
        //(10)������t 
        else if (OrderStatusEnum.ORDERED.equals(l_orderAction.getOrderStatus()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.ORDER_ACCEPT;
        }
        //(11)�V�K����(���s)
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderAction.getOrderStatus()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.OPEN_ORDER_FAIL;
        }
        //(12)���������A�ؑ֒���
        else if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderAction.getOrderStatus()))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(
                l_ifoOrderActionRow.getModifyCancelType()))
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.SWITCH_ORDER;
            }
            else
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.CHANGE_ORDER;
            }
        }
        //(13)������t�A�ؑ֎�t
        else if (OrderStatusEnum.MODIFYING.equals(l_orderAction.getOrderStatus()))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(
                l_ifoOrderActionRow.getModifyCancelType()))
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.SWITCH_ACCEPT;
            }
            else
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.CHANGE_ACCEPT;
            }
        }
        //(14)���������A�ؑ֊���
        else if (OrderStatusEnum.MODIFIED.equals(l_orderAction.getOrderStatus()))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_PARTIALLY_TRANSFERED.equals(
                l_ifoOrderActionRow.getModifyCancelType()) ||
                WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERED.equals(
                l_ifoOrderActionRow.getModifyCancelType()))
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.SWITCH_OVER;
            }
            else
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.CHANGE_FINISH;
            }
        }
        //(15)��������(���s)�A�ؑ֒���(���s)
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_orderAction.getOrderStatus()))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR.equals(
                l_ifoOrderActionRow.getModifyCancelType()))
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.SWITCH_ORDER_FAIL;
            }
            else
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.CHANGE_ORDER_FAIL;
            }
        }
        //(16)�������
        else if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderAction.getOrderStatus()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.CANCEL_ORDER;
        }
        //(17)�����t
        else if (OrderStatusEnum.CANCELLING.equals(l_orderAction.getOrderStatus()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.CANCEL_ACCEPT;
        }
        //(18)�������
        else if (OrderStatusEnum.CANCELLED.equals(l_orderAction.getOrderStatus()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.CANCEL_FINISH;
        }
        //(19)�������(���s)
        else if (OrderStatusEnum.NOT_CANCELLED.equals(l_orderAction.getOrderStatus()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.CANCEL_ORDER_FAIL;
        }
        //(20)������
        else if (OrderEventTypeEnum.SEND_TO_MKT.equals(l_orderAction.getOrderEventType()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.ORDERING;
        }
        //(21)��L�ȊO�̏ꍇ�A"���̑�"��Ԃ��B
        else
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.OTHER;
        }
        
        log.debug("�������e�敪 = " + l_strReturn);
        
        log.exiting(STR_METHOD_NAME);

        return l_strReturn;
    }
    
    /**
     * (get��t���ʋ敪)<BR>
     * �w�肳�ꂽ���������̎�t���ʋ敪��Ԃ��B<BR>
     * <BR>
     * �߂�l�̎�t���ʋ敪�F<BR>
     * 0000�F���� 1001�F��t�G���[ 1002�F�����G���[ <BR>
     * 1003�F����G���[ 1004�F�ؑփG���[ <BR>
     * 0001�F�l���E���ݒl�G���[ 0002�F�a����s���G���[ 0003�F�����w���敨�I�v�V�����c���s���G���[<BR>
     * 0004�F�ۏ؋��s���G���[ 0005�F���ʎc���s���G���[ 0006�F������~�����G���[<BR>
     * 0007�F�s��ύX�����G���[ 0008�F���t�]�̓G���[ 0009�F���t�\���ʃG���[<BR>
     * 0010�F��������G���[ 0011�F�����J�z�X�L�b�v�����G���[ 0012�F��K���`�F�b�N�G���[<BR>
     * 0013�F�������`�F�b�N�G���[ 9001�F���̑��G���[<BR>
     * <BR>
     * --------------------------------------------------------------------------<BR>
     * �E��������.���������E����敪��"������s"�̏ꍇ<BR>
     * <BR>
     * �@@"1003"�i����G���[�j��Ԃ��B<BR>
     * <BR>
     * �E��������.���������E����敪��"�������s"�̏ꍇ<BR>
     * <BR>
     * �@@"1002"�i�����G���[�j��Ԃ��B<BR>
     * <BR>
     * �E��������.���������E����敪��"W�w�l�����ؑ֎��s"�̏ꍇ�A <BR>
     * �@@"1004"�i�ؑփG���[�j��Ԃ��B <BR>
     * <BR>
     * �E��������.������ԁ�NOT_ORDERED�i�������s�i�V�K�����j�j<BR>
     * �@@���@@��������.�����C�x���g�^�C�v��REJECTED_BY_MARKET�i�}�[�P�b�g���ہi�V�K�����j�j<BR>
     * �@@�̏ꍇ<BR>
     * <BR>
     * �@@"1001"�i��t�G���[�j��Ԃ��B<BR>
     * <BR>
     * �E��L�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@��������.�����G���[���R�R�[�h ��Ԃ��B<BR>
     * --------------------------------------------------------------------------<BR>
     * <BR>
     * @@param l_orderAction - (��������)<BR>
     * ���������I�u�W�F�N�g�B
     * @@return String
     */
    public static String getAcceptResultType(IfoOrderAction l_orderAction)
    {
        final String STR_METHOD_NAME =
            "getAcceptResultType(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        IfoOrderActionRow l_orderActionRow =
            (IfoOrderActionRow)l_orderAction.getDataSourceObject();
        String l_strReturn = null;
        
        // �E��������.���������E����敪���i������s�j�̏ꍇ
        if (WEB3ModifyCancelTypeDef.CANCEL_ERROR.equals(l_orderActionRow.getModifyCancelType()))
        {
            l_strReturn = WEB3ErrorReasonCodeDef.CANCEL_ERROR;
        }
        // �E��������.���������E����敪���i�������s�j�̏ꍇ
        else if (WEB3ModifyCancelTypeDef.CHANGE_ERROR.equals(l_orderActionRow.getModifyCancelType()))
        {
            l_strReturn = WEB3ErrorReasonCodeDef.CHANGE_ERROR;
        }
        //��������.���������E����敪��"W�w�l�����ؑ֎��s"�̏ꍇ
        else if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR.equals(l_orderActionRow.getModifyCancelType()))
        {
            l_strReturn = WEB3ErrorReasonCodeDef.TRANSFER_ERROR;
        }
        // �E��������.������ԁ�NOT_ORDERED�i�������s�i�V�K�����j�j
        // �@@���@@��������.�����C�x���g�^�C�v��REJECTED_BY_MARKET�i�}�[�P�b�g���ہi�V�K�����j�j
        // �@@�̏ꍇ
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderAction.getOrderStatus()) &&
                OrderEventTypeEnum.REJECTED_BY_MKT.equals(l_orderAction.getOrderEventType()))
        {
            l_strReturn = WEB3ErrorReasonCodeDef.ACCEPT_ERROR;
        }
        // �E��L�ȊO�̏ꍇ
        else
        {
            l_strReturn = l_orderActionRow.getErrorReasonCode();
        }

        log.debug("��t���ʋ敪 = " + l_strReturn);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_strReturn;
    }
    
    /**
     * (get�v�w�l�p�L����ԋ敪)<BR>
     * �����̒����P�ʂ��v�w�l�p�L����ԋ敪��ԋp����B<BR> 
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR> 
     * �@@null��ԋp����B <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.���������� <BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR> 
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B <BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B <BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@"�X�g�b�v����������"��ԋp����B <BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@"�X�g�b�v�����L��"��ԋp����B <BR>
     * �@@[��L�ȊO�̏ꍇ] <BR>
     * �@@�@@"���~�b�g�����L��"��ԋp����B <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitEnableStatusDiv(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWLimitEnableStatusDiv(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�����P��.�������� 
        // �@@�@@�����������F�@@�p�����[�^.�����P��.���������� 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
    		l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
       
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(l_orderUnit);
               
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v����������()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(l_orderUnit);
        
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B 
        // �@@[�R�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@"�X�g�b�v����������"��ԋp����B 
        // �@@[�Q�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@"�X�g�b�v�����L��"��ԋp����B 
        // �@@[��L�ȊO�̏ꍇ] 
        // �@@�@@"���~�b�g�����L��"��ԋp����B 
        if (l_blnStopOrderExpire)
        {
        	String l_strStatusDiv = WEB3IfoWLimitEnableStatusDivDef.STOP_UN_ENABLE;
        	log.exiting(STR_METHOD_NAME);
        	return l_strStatusDiv;
        }       
        else if (l_blnStopOrderOpen)
        {
        	String l_strStatusDiv = WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE;
        	log.exiting(STR_METHOD_NAME);
        	return l_strStatusDiv;
        }
        
        String l_strStatusDiv = WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE;
    	log.exiting(STR_METHOD_NAME);       
        return l_strStatusDiv;
    }
    
    /**
     * (get�v�w�l�p�L����ԋ敪)<BR>
     * �����̒����������v�w�l�p�L����ԋ敪��ԋp����B<BR> 
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR> 
     * �@@null��ԋp����B <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.��������.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.���������� <BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B <BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�������� <BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR> 
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�������� <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B <BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@"�X�g�b�v����������"��ԋp����B <BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@"�X�g�b�v�����L��"��ԋp����B <BR>
     * �@@[��L�ȊO�̏ꍇ] <BR>
     * �@@�@@"���~�b�g�����L��"��ԋp����B<BR>
     * @@param l_ifoOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitEnableStatusDiv(IfoOrderAction l_ifoOrderAction)
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWLimitEnableStatusDiv(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.��������.�������� 
        // �@@�@@�����������F�@@�p�����[�^.��������.���������� 
        IfoOrderActionRow l_ifoOrderActionRow =
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderActionRow.getOrderConditionType(), 
    		l_ifoOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
       
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�������� 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_ifoOrderAction);
               
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v����������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�������� 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_ifoOrderAction);
        
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B 
        // �@@[�R�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@"�X�g�b�v����������"��ԋp����B 
        // �@@[�Q�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@"�X�g�b�v�����L��"��ԋp����B 
        // �@@[��L�ȊO�̏ꍇ] 
        // �@@�@@"���~�b�g�����L��"��ԋp����B
        if (l_blnStopOrderExpire)
        {
        	String l_strStatusDiv = WEB3IfoWLimitEnableStatusDivDef.STOP_UN_ENABLE;
        	log.exiting(STR_METHOD_NAME);
        	return l_strStatusDiv;
        }       
        else if (l_blnStopOrderOpen)
        {
        	String l_strStatusDiv = WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE;
        	log.exiting(STR_METHOD_NAME);
        	return l_strStatusDiv;
       }
        
        String l_strStatusDiv = WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE;
    	log.exiting(STR_METHOD_NAME);       
        return l_strStatusDiv;
    }
    
    /**
     * (get�v�w�l�p�֑ؑO�����P��)<BR>
     * �����̒����P�ʂ��v�w�l�p�֑ؑO�����P����ԋp����B<BR> 
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR> 
     * �@@null��ԋp����B <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.���������� <BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR> 
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B <BR>
     * �@@[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B <BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l��ԋp����B <BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@�p�����[�^.�����P��.�iW�w�l�j�֑ؑO�w�l��ԋp����B <BR>
     * �@@[��L�ȊO] <BR>
     * �@@�@@�p�����[�^.�����P��.�w�l��ԋp����B <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitBefSwitchPrice(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWLimitBefSwitchPrice(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�����P��.�������� 
        // �@@�@@�����������F�@@�p�����[�^.�����P��.���������� 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
    		l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� 
        boolean l_blnIsStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_orderUnit);
        
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� 
        boolean l_blnIsStopOrderChanging = l_optionOrderManagerImpl.isStopOrderSwitching(
    		l_orderUnit);
        
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B 
        // �@@[�R�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l��ԋp����B 
        if (l_blnIsStopOrderChanging)
        {
            String l_strConfirmedPrice = null;
            if (!l_ifoOrderUnitRow.getConfirmedPriceIsNull())
            {
                l_strConfirmedPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitRow.getConfirmedPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strConfirmedPrice;
        }
        
        // �@@[�Q�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@�p�����[�^.�����P��.�iW�w�l�j�֑ؑO�w�l��ԋp����B
        else if (l_blnIsStopOrderOpen)
        {
            String l_strWLimitBeforeLimitPrice = null;
            if (!l_ifoOrderUnitRow.getWLimitBeforeLimitPriceIsNull())
            {
                l_strWLimitBeforeLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitRow.getWLimitBeforeLimitPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strWLimitBeforeLimitPrice;
        }
        
        // �@@[��L�ȊO] 
        // �@@�@@�p�����[�^.�����P��.�w�l��ԋp����B
        String l_strLimitPrice = null;
        if (!l_ifoOrderUnitRow.getLimitPriceIsNull())
        {
            l_strLimitPrice = WEB3StringTypeUtility.formatNumber(
                l_ifoOrderUnitRow.getLimitPrice());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strLimitPrice;
    }
    
    /**
     * (get�v�w�l�p�֑ؑO�����P��)<BR>
     * �����̒����������v�w�l�p�֑ؑO�����P����ԋp����B<BR> 
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR> 
     * �@@null��ԋp����B <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.��������.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.���������� <BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR> 
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�������� <BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B<BR> 
     * �@@[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.��������<BR> 
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B <BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@�p�����[�^.��������.�s�ꂩ��m�F�ς݂̎w�l��ԋp����B<BR> 
     * �@@[�Q�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@�p�����[�^.��������.�iW�w�l�j�֑ؑO�w�l��ԋp����B <BR>
     * �@@[��L�ȊO] <BR>
     * �@@�@@�p�����[�^.��������.�����P����ԋp����B<BR>
     * @@param l_ifoOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitBefSwitchPrice(IfoOrderAction l_ifoOrderAction) 
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWlimitBefSwitchPrice(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.��������.�������� 
        // �@@�@@�����������F�@@�p�����[�^.��������.���������� 
        IfoOrderActionRow l_ifoOrderActionRow =
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderActionRow.getOrderConditionType(), 
    		l_ifoOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        	
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�������� 
        boolean l_blnIsStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_ifoOrderAction);
        
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�������� 
        boolean l_blnIsStopOrderChanging = l_optionOrderManagerImpl.isStopOrderSwitching(
    		l_ifoOrderAction);
        
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B 
        // �@@[�R�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@�p�����[�^.��������.�s�ꂩ��m�F�ς݂̎w�l��ԋp����B 
        if (l_blnIsStopOrderChanging)
        {
            String l_strConfirmedPrice = null;
            if (!l_ifoOrderActionRow.getConfirmedPriceIsNull())
            {
                l_strConfirmedPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderActionRow.getConfirmedPrice());
            }
        	log.exiting(STR_METHOD_NAME);
        	return l_strConfirmedPrice;
        }
        
        // �@@[�Q�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@�p�����[�^.��������.�iW�w�l�j�֑ؑO�w�l��ԋp����B 
        else if (l_blnIsStopOrderOpen)
        {
            String l_strWLimitBeforeLimitPrice = null;
            if (!l_ifoOrderActionRow.getWLimitBeforeLimitPriceIsNull())
            {
                l_strWLimitBeforeLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderActionRow.getWLimitBeforeLimitPrice());
            }
        	log.exiting(STR_METHOD_NAME);
        	return l_strWLimitBeforeLimitPrice;
        }
        
        // �@@[��L�ȊO] 
        // �@@�@@�p�����[�^.��������.�����P����ԋp����B
        String l_strPrice = null;
        if (!l_ifoOrderActionRow.getPriceIsNull())
        {
            l_strPrice = WEB3StringTypeUtility.formatNumber(
                l_ifoOrderActionRow.getPrice());
        }
        log.exiting(STR_METHOD_NAME);
        return l_strPrice;
    }
    
    /**
     * (get�v�w�l�p�֑ؑO���s����)<BR>
     * �����̒����P�ʂ��v�w�l�p�֑ؑO���s������ԋp����B<BR> 
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A <BR>
     * �@@null��ԋp����B <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.���������� <BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR> 
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B <BR>
     * �@@[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A���s���������肷��B<BR> 
     * �@@[�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@���s���� = �p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎��s���� <BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@���s���� = �p�����[�^.�����P��.�iW�w�l�j�֑ؑO���s���� <BR>
     * �@@[��L�ȊO] <BR>
     * �@@�@@���s���� = �p�����[�^.�����P��.���s���� <BR>
     * <BR>
     * �T�j�@@this.get���s�����iPR�w�j()���\�b�h���R�[�����A <BR>
     * �@@�߂�l��ԋp����B <BR>
     * <BR>
     * �@@[get���s�����iPR�w�j()�Ɏw�肷�����] <BR>
     * �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s���� <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitBefSwitchExecCondType(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWLimitBefSwitchExecCondType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�����P��.�������� 
        // �@@�@@�����������F�@@�p�����[�^.�����P��.���������� 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
    		l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        	
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� 
        boolean l_blnIsStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_orderUnit);
        
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� 
        boolean l_blnIsStopOrderChanging = l_optionOrderManagerImpl.isStopOrderSwitching(
    		l_orderUnit);
        
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A���s���������肷��B 
        // �@@[�R�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@���s���� = �p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎��s����        
        IfoOrderExecutionConditionType l_execConditionType = null;
        if (l_blnIsStopOrderChanging)
        {
        	l_execConditionType = 
    			l_ifoOrderUnitRow.getConfirmedExecConditionType();
        }
        
        // �@@[�Q�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@���s���� = �p�����[�^.�����P��.�iW�w�l�j�֑ؑO���s���� 
        else if (l_blnIsStopOrderOpen)
        {
        	l_execConditionType = 
    			l_ifoOrderUnitRow.getWLimitBeforeExecCondType();
        }
        else
        {
            // �@@[��L�ȊO] 
            // �@@�@@���s���� = �p�����[�^.�����P��.���s���� 
        	l_execConditionType = l_ifoOrderUnitRow.getExecutionConditionType();
        }

        // �T�j�@@this.get���s�����iPR�w�j()���\�b�h���R�[�����A 
        // �@@�߂�l��ԋp����B 
        // �@@[get���s�����iPR�w�j()�Ɏw�肷�����] 
        // �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s����
        String l_strWLimitBefChgExecCondType = getExecutionCondByPr(l_execConditionType);
        log.exiting(STR_METHOD_NAME);
        return l_strWLimitBefChgExecCondType;
    }
    
    /**
     * (get�v�w�l�p�֑ؑO���s����)<BR>
     * �����̒����������v�w�l�p�֑ؑO���s������ԋp����B <BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR> 
     * �@@null��ԋp����B <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.��������.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.���������� <BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B <BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�������� <BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B<BR> 
     * �@@[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�������� <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A���s���������肷��B <BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@���s���� = �p�����[�^.��������.�s�ꂩ��m�F�ς݂̎��s���� <BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@���s���� = �p�����[�^.��������.�iW�w�l�j�֑ؑO���s���� <BR>
     * �@@[��L�ȊO] <BR>
     * �@@�@@���s���� = �p�����[�^.��������.���s���� <BR>
     * <BR>
     * �T�j�@@this.get���s�����iPR�w�j()���\�b�h���R�[�����A<BR> 
     * �@@�߂�l��ԋp����B <BR>
     * <BR>
     * �@@[get���s�����iPR�w�j()�Ɏw�肷�����] <BR>
     * �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s���� <BR>
     * @@param l_ifoOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitBefSwitchExecCondType(IfoOrderAction l_ifoOrderAction) 
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWLimitBefSwitchExecCondType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.��������.�������� 
        // �@@�@@�����������F�@@�p�����[�^.��������.���������� 
        IfoOrderActionRow l_ifoOrderActionRow =
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderActionRow.getOrderConditionType(), 
    		l_ifoOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        	
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�������� 
        boolean l_blnIsStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_ifoOrderAction);
        
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�������� 
       boolean l_blnIsStopOrderChanging = l_optionOrderManagerImpl.isStopOrderSwitching(
    		l_ifoOrderAction);
        
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A���s���������肷��B 
        // �@@[�R�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@���s���� = �p�����[�^.��������.�s�ꂩ��m�F�ς݂̎��s����    
        IfoOrderExecutionConditionType l_execConditionType = null;
        if (l_blnIsStopOrderChanging)
        {
        	l_execConditionType = 
        		l_ifoOrderActionRow.getConfirmedExecConditionType();
        }
        
        // �@@[�Q�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@���s���� = �p�����[�^.��������.�iW�w�l�j�֑ؑO���s���� 
        else if (l_blnIsStopOrderOpen)
        {
        	l_execConditionType = 
        		l_ifoOrderActionRow.getWLimitBeforeExecCondType();
        }
        else
        {
            // �@@[��L�ȊO] 
            // �@@�@@���s���� = �p�����[�^.��������.���s���� 
        	l_execConditionType = l_ifoOrderActionRow.getExecutionConditionType();
        }

        // �T�j�@@this.get���s�����iPR�w�j()���\�b�h���R�[�����A 
        // �@@�߂�l��ԋp����B 
        // �@@[get���s�����iPR�w�j()�Ɏw�肷�����] 
        // �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s����
        String l_strWLimitBefChgExecCondType = getExecutionCondByPr(l_execConditionType);
        log.exiting(STR_METHOD_NAME);
        return l_strWLimitBefChgExecCondType;
    }

    /**
     * (get�x���敪)<BR>
     * �����̒����P�ʂ��g���K�[�����̒x���敪��ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l == "DEFAULT(�����w��Ȃ�)"�̏ꍇ�A <BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.���������� <BR>
     * <BR>
     * �Q�jOP�����}�l�[�W��.is�x������(�p�����[�^.�����P��) == true�̏ꍇ�A<BR>
     *�@@�@@�@@"�x��"��ԋp����B <BR>
     *�@@�@@�@@�ȊO�A"����"��ԋp����<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getDelayDiv(IfoOrderUnit l_orderUnit)
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getDelayDiv(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }

        // �P�j�@@this.get��������()�̖߂�l == "DEFAULT(�����w��Ȃ�)"�̏ꍇ�A
        // �@@null��ԋp����B
        // �@@[get��������()�Ɏw�肷�����]
        // �@@�@@���������F�@@�p�����[�^.�����P��.��������
        // �@@�@@�����������F�@@�p�����[�^.�����P��.����������
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(),
    		l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l == 'DEFAULT(�����w��Ȃ�)'�̏�B");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@OP�����}�l�[�W��.is�x������(�p�����[�^.�����P��) == true�̏ꍇ�A
        //�@@"�x��"��ԋp����B
        //�@@�ȊO�A"����"��ԋp����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
        if (l_orderManager.isDelayOrder(l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
        	return WEB3DelayDivDef.DELAY;
        }

        log.exiting(STR_METHOD_NAME);
        return WEB3DelayDivDef.NORMAL;
    }

    /**
     * (getW�w�l�����󋵋敪)<BR>
     * �p�����[�^.�����P�ʂ�� <BR>
     * PR�w�Ŏg�p����W�w�l�����̔����󋵋敪��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����󋵋敪�̔��� <BR>
     * <BR>
     *�@@ �P�|�P�j�@@�����x���G���[�i�ؑ֒x���G���[�j�̔���
     *�@@�@@OP�����}�l�[�W��.is�������x������(�����P��) == true�̏ꍇ�A
     *�@@�@@"�����x���G���["��ԋp����B
     * �@@�P�|�Q�j�@@�ҋ@@���i�֖ؑ��ρj�̔��� <BR>
     * �@@�@@�����P��.���N�G�X�g�^�C�v��"DEFAULT"�̏ꍇ�A"�ҋ@@��"��ԋp����B<BR>
     * <BR>
     * �@@�P�|�R�j�@@�������i�ؑ֒��j�̔���<BR>
     * �@@�@@�����P��.���N�G�X�g�^�C�v��"�����T�[�o"�̏ꍇ�A"������"��ԋp����B<BR>
     * <BR>
     * �@@�P�|�S�j�@@���������i�ؑ֊����j�̔��� <BR>
     * �@@�@@�����P��.���N�G�X�g�^�C�v��"�ؑ֊���"�̏ꍇ�A"��������"��ԋp����B<BR>
     * <BR>
     * �@@�P�|�T�j�@@�X�g�b�v���������̔���<BR>
     * �@@�@@�����P��.���N�G�X�g�^�C�v��"����"�̏ꍇ�A"�X�g�b�v��������"��ԋp����B<BR>
     * <BR>
     * �@@�P�|�U�j�@@��L�ȊO�̏ꍇ�A"���̑�"��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitOrderStatusType(IfoOrderUnit l_orderUnit)
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWLimitOrderStatusType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strRequestType = l_ifoOrderUnitRow.getRequestType();

        //�P�j�@@�����󋵋敪�̔���
        //�@@�P�|�P�j�@@�����x���G���[�i�ؑ֒x���G���[�j�̔���
        //�@@�@@OP�����}�l�[�W��.is�������x������(�����P��) == true�̏ꍇ�A
        //�@@�@@"�����x���G���["��ԋp����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
        if (l_orderManager.isNotOrderedDelay(l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR;
        }

        // �P�j�@@�����󋵋敪�̔���
        // �@@�P�|2 �j�@@�ҋ@@���i�֖ؑ��ρj�̔���
        // �@@�@@�����P��.���N�G�X�g�^�C�v��"DEFAULT"�̏ꍇ�A"�ҋ@@��"��ԋp����B
        if (WEB3RequestTypeDef.DEFAULT.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
        	return WEB3TriggerOrderStatusDef.ORDER_WAITING;
        }

        // �@@�P�|3�j�@@�������i�ؑ֒��j�̔���
        // �@@�@@�����P��.���N�G�X�g�^�C�v��"�����T�[�o"�̏ꍇ�A"������"��ԋp����B
        if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
        	return WEB3TriggerOrderStatusDef.ORDERING;
        }

        // �@@�P�|4�j�@@���������i�ؑ֊����j�̔���
        // �@@�@@�����P��.���N�G�X�g�^�C�v��"�ؑ֊���"�̏ꍇ�A"��������"��ԋp����B
        if (WEB3RequestTypeDef.TRANSFERED.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
        	return WEB3TriggerOrderStatusDef.ORDER_COMPLETE;
        }

        // �@@�P�|�U�j�@@�X�g�b�v���������̔���
        // �@@�@@�����P��.���N�G�X�g�^�C�v��"����"�̏ꍇ�A"�X�g�b�v��������"��ԋp����B
        if (WEB3RequestTypeDef.INVALIDATE.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
        	return WEB3TriggerOrderStatusDef.STOP_ORDER_INVALIDATION;
        }

        // �@@�P�|�V�j�@@��L�ȊO�̏ꍇ�A"���̑�"��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return WEB3TriggerOrderStatusDef.OTHER;

    }

    /**
     * (get�v�w�l�p���s����)<BR>
     * �����̒����P�ʂ��v�w�l�p���s������ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.���������� <BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp���鎷�s���������肷��B<BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@���s���� = �p�����[�^.�����P��.���iW�w�l�j���s���� <BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@���s���� = �p�����[�^.�����P��.���s���� <BR>
     * �@@[��L�ȊO] <BR>
     * �@@�@@���s���� = �p�����[�^.�����P��.�iW�w�l�j���s���� <BR>
     * <BR>
     * �T�j�@@this.get���s�����iPR�w�j()���\�b�h���R�[�����A<BR>
     * �@@�߂�l��ԋp����B <BR>
     * <BR>
     * �@@[get���s�����iPR�w�j()�Ɏw�肷�����] <BR>
     * �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s���� <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitExecCondType(IfoOrderUnit l_orderUnit)
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWLimitExecCondType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�����P��.�������� 
        // �@@�@@�����������F�@@�p�����[�^.�����P��.���������� 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
    		l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(l_orderUnit);
               
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v����������()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(l_orderUnit);
               
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp���鎷�s���������肷��B 
        // �@@[�R�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@���s���� = �p�����[�^.�����P��.���iW�w�l�j���s���� 
        IfoOrderExecutionConditionType l_executionConditionType = null;
        if (l_blnStopOrderExpire)
        {
            l_executionConditionType = 
    			l_ifoOrderUnitRow.getOrgWLimitExecCondType();        	
        }
        
        // �@@[�Q�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@���s���� = �p�����[�^.�����P��.���s���� 
        else if (l_blnStopOrderOpen)
        {
            l_executionConditionType = 
        		l_ifoOrderUnitRow.getExecutionConditionType();
        }
        else
        {
            // �@@[��L�ȊO] 
            // �@@�@@���s���� = �p�����[�^.�����P��.�iW�w�l�j���s���� 
            l_executionConditionType = 
    			l_ifoOrderUnitRow.getWLimitExecCondType();  
        }
        
        // �T�j�@@this.get���s�����iPR�w�j()���\�b�h���R�[�����A 
        // �@@�߂�l��ԋp����B 
        // �@@[get���s�����iPR�w�j()�Ɏw�肷�����] 
        // �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s����                             
        String l_strWLimitExecCondType = getExecutionCondByPr(l_executionConditionType);
        log.exiting(STR_METHOD_NAME);
        return l_strWLimitExecCondType;
    }
    
    /**
     * (get�v�w�l�p�����P���敪)<BR>
     * �����̒����P�ʂ��v�w�l�p�����P���敪��ԋp����B<BR> 
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A <BR>
     * �@@null��ԋp����B <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.���������� <BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���R�[������B <BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR> 
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�̒����P���敪�����肷��B<BR> 
     * �@@[�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@�p�����[�^.�����P��.���iW�w�l�j�����w�l == 0�ł���΁A"���s"�A <BR>
     * �@@�@@�ȊO��"�w�l"��ԋp����B <BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@�p�����[�^.�����P��.isMarketOrder == true�ł���΁A"���s"�A <BR>
     * �@@�@@�ȊO��"�w�l"��ԋp����B <BR>
     * �@@[��L�ȊO] <BR>
     * �@@�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l == 0�ł���΁A"���s"�A <BR>
     * �@@�@@�ȊO��"�w�l"��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitOrderPriceDiv(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPriceDiv(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^�l��NULL
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�����P��.�������� 
        // �@@�@@�����������F�@@�p�����[�^.�����P��.���������� 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
			l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
      
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���R�[������B 
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_orderUnit);
        
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v����������()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P��    
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_orderUnit);
        
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�̒����P���敪�����肷��B 
        // �@@[�R�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@�p�����[�^.�����P��.���iW�w�l�j�����w�l == 0�ł���΁A"���s"�A 
        // �@@�@@�ȊO��"�w�l"��ԋp����B 
        if (l_blnStopOrderExpire)
        {
        	double l_dblOrgWLimitPrice = l_ifoOrderUnitRow.getOrgWLimitPrice();
        	if (l_dblOrgWLimitPrice == 0)
        	{
        		log.exiting(STR_METHOD_NAME);
        		return WEB3OrderPriceDivDef.MARKET_PRICE;
        	}
        	else
        	{
           		log.exiting(STR_METHOD_NAME);
        		return WEB3OrderPriceDivDef.LIMIT_PRICE;
        	}
        }
        
        // �@@[�Q�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@�p�����[�^.�����P��.isMarketOrder == true�ł���΁A"���s"�A 
        // �@@�@@�ȊO��"�w�l"��ԋp����B 
        if (l_blnStopOrderOpen)
        {
        	if (l_orderUnit.isMarketOrder())
        	{
        		log.exiting(STR_METHOD_NAME);
        		return WEB3OrderPriceDivDef.MARKET_PRICE;
        	}
        	else
        	{
           		log.exiting(STR_METHOD_NAME);
        		return WEB3OrderPriceDivDef.LIMIT_PRICE;
        	}
        }
        
        // �@@[��L�ȊO] 
        // �@@�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l == 0�ł���΁A"���s"�A 
        // �@@�@@�ȊO��"�w�l"��ԋp����B
        else if (l_ifoOrderUnitRow.getWLimitPrice() == 0)
        {
    		log.exiting(STR_METHOD_NAME);
    		return WEB3OrderPriceDivDef.MARKET_PRICE;
    	}
    	else
    	{
       		log.exiting(STR_METHOD_NAME);
    		return WEB3OrderPriceDivDef.LIMIT_PRICE;
    	}     
    }
    
    /**
     * (get�v�w�l�p�����P��)<BR>
     * �����̒����P�ʂ��v�w�l�p�����P����ԋp����B <BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A <BR>
     * �@@null��ԋp����B <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.���������� <BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR> 
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B <BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B <BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@�p�����[�^.�����P��.���iW�w�l�j�����w�l��ԋp����B<BR> 
     * �@@[�Q�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@�p�����[�^.�����P��.�w�l��ԋp����B <BR>
     * �@@[��L�ȊO] <BR>
     * �@@�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitOrderPrice(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPrice(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^�l��NULL
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�����P��.�������� 
        // �@@�@@�����������F�@@�p�����[�^.�����P��.���������� 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
			l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
        	new WEB3OptionOrderManagerImpl();
        
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_orderUnit);
        
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v����������()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_orderUnit);
        
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B 
        // �@@[�R�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@�p�����[�^.�����P��.���iW�w�l�j�����w�l��ԋp����B 
        if (l_blnStopOrderExpire)
        {
        	String l_strOrgWLimitPrice = null;
            if (!l_ifoOrderUnitRow.getOrgWLimitPriceIsNull()) 
            {
                l_strOrgWLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitRow.getOrgWLimitPrice()); 
            }
            log.exiting(STR_METHOD_NAME);
        	return l_strOrgWLimitPrice;
        }
        
        // �@@[�Q�j�̖߂�l == true�̏ꍇ] 
        // �@@�@@�p�����[�^.�����P��.�w�l��ԋp����B 
        else if (l_blnStopOrderOpen)
        {
            String l_strLimitPrice = null;
            if (!l_ifoOrderUnitRow.getLimitPriceIsNull())
            {
                l_strLimitPrice = WEB3StringTypeUtility.formatNumber(
                        l_ifoOrderUnitRow.getLimitPrice());
            }
        	log.exiting(STR_METHOD_NAME);
        	return l_strLimitPrice;
        }
        
        // �@@[��L�ȊO] 
        // �@@�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l��ԋp����B
        String l_strWLimitPrice = null;
        if (!l_ifoOrderUnitRow.getWLimitPriceIsNull())
        {
            l_strWLimitPrice = WEB3StringTypeUtility.formatNumber(
                l_ifoOrderUnitRow.getWLimitPrice());
        }
    	log.exiting(STR_METHOD_NAME);
        return l_strWLimitPrice;
    }

    /**
     * (get��������)<BR>
     * �����̔��������A�����������̓��A<BR> 
     * null�łȂ����̔���������ԋp����B<BR>
     * <BR>                                 
     * [�p�����[�^.���������� != null�̏ꍇ]<BR>
     * �@@�p�����[�^.������������ԋp����B<BR>
     * <BR>                                
     * [�p�����[�^.�������� != null�̏ꍇ]<BR>
     * �@@�p�����[�^.����������ԋp����B<BR>
     * @@param l_strOrderConditionType - (��������)<BR>
     * ��������<BR>
     * @@param l_strOrgOrderConditionType - (����������)<BR>
     * ����������<BR>
     * @@return String
     */
    public static String getOrderConditionType(String l_strOrderConditionType, 
		String l_strOrgOrderConditionType)
    {
        final String STR_METHOD_NAME = "getOrderConditionType(String, String)";
        log.entering(STR_METHOD_NAME);
        
        // �����̔��������A�����������̓��A 
        // null�łȂ����̔���������ԋp����B   
        // [�p�����[�^.���������� != null�̏ꍇ]
        // �@@�p�����[�^.������������ԋp����B
        if (l_strOrgOrderConditionType != null)
        {
            log.exiting(STR_METHOD_NAME);
        	return l_strOrgOrderConditionType;
        }
                    
        // [�p�����[�^.�������� != null�̏ꍇ]
        // �@@�p�����[�^.����������ԋp����B
        if (l_strOrderConditionType != null)
        {
            log.exiting(STR_METHOD_NAME);
        	return l_strOrderConditionType;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get�����������Z�q)<BR>
     * �����̔����������Z�q�A�������������Z�q�̓��A<BR>
     * �L���Ȕ����������Z�q��ԋp����B<BR>            
     * <BR>                                            
     * [�p�����[�^.�������������Z�q != null�̏ꍇ] <BR>
     * �@@�p�����[�^.�������������Z�q��ԋp����B<BR>   
     * <BR>                                            
     * [�p�����[�^.�����������Z�q != null�̏ꍇ]<BR>   
     * �@@�p�����[�^.�����������Z�q��ԋp����B<BR>     
     * @@param l_strOrderCondOperator - (�����������Z�q)<BR>
     * �����������Z�q<BR>
     * @@param l_strOrgOrderCondOperator - (�������������Z�q)<BR>
     * �������������Z�q<BR>
     * @@return String
     */
    public static String getOrderCondOperator(String l_strOrderCondOperator, 
		String l_strOrgOrderCondOperator)
    {
        final String STR_METHOD_NAME = "getOrderCondOperator(String , String )";
        log.entering(STR_METHOD_NAME);    
        
        // �����̔����������Z�q�A�������������Z�q�̓��A
        // �L���Ȕ����������Z�q��ԋp����B                                                     
        // [�p�����[�^.�������������Z�q != null�̏ꍇ] 
        // �@@�p�����[�^.�������������Z�q��ԋp����B  
        if (l_strOrgOrderCondOperator != null)
        {
            log.exiting(STR_METHOD_NAME);
        	return l_strOrgOrderCondOperator;
        }
                     
        // [�p�����[�^.�����������Z�q != null�̏ꍇ]        
        // �@@�p�����[�^.�����������Z�q��ԋp����B    
        if (l_strOrderCondOperator != null)
        {
            log.exiting(STR_METHOD_NAME);
        	return l_strOrderCondOperator;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get�����������Z�q)<BR>
     * �����̒����P�ʂ��A�L���Ȕ����������Z�q��ԋp����B<BR>
     * <BR>
     * �P�j�@@���������w��Ȃ��̏ꍇ<BR>
     * <BR>
     * this.get��������(�j��"DEFAULT�i�����w��Ȃ��j"�̏ꍇ�A<BR>
     * null��ԋp����B<BR>
     * <BR>
     * [get��������()�Ɏw�肷�����]<BR>
     * �@@���������F�@@�����P��.��������<BR>
     * �@@�����������F�@@�����P��.����������<BR>
     * <BR>
     * �Q�j�@@�ȊO�̏ꍇ<BR>
     * <BR>
     * this.get�����������Z�q()�ɏ������Ϗ�����B<BR>
     * <BR>
     * [get�����������Z�q()�Ɏw�肷�����]<BR>
     * �@@�����������Z�q�F�@@�����P��.�����������Z�q<BR>
     * �@@�������������Z�q�F�@@�����P��.�������������Z�q<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrderCondOperator(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderCondOperator(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        String l_strOrderCondOperatorReturn = null;
        
        //�p�����[�^�l��NULL
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
       
        // �����̒����P�ʂ��A�L���Ȕ����������Z�q��ԋp����B                                                                          
        // �P�j�@@���������w��Ȃ��̏ꍇ<BR>                                                                                                   
        // this.get��������(�j��"DEFAULT�i�����w��Ȃ��j"�̏ꍇ�A       
        // null��ԋp����B      
        // [get��������()�Ɏw�肷�����]                                   
        // �@@���������F�@@�����P��.��������                                
        // �@@�����������F�@@�����P��.����������      
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
			l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))  
        {
            log.debug("this.get��������(�j��DEFAULT�i�����w��Ȃ��j�̏ꍇ�B");   
        }
        else 
        {
            // �Q�j�@@�ȊO�̏ꍇ                                                                                                                
            // this.get�����������Z�q()�ɏ������Ϗ�����B                                                                                   
            // [get�����������Z�q()�Ɏw�肷�����]                           
            // �@@�����������Z�q�F�@@�����P��.�����������Z�q                     
            // �@@�������������Z�q�F�@@�����P��.�������������Z�q 
        	l_strOrderCondOperatorReturn = getOrderCondOperator(
    			l_ifoOrderUnitRow.getOrderCondOperator(), 
    			l_ifoOrderUnitRow.getOrgOrderCondOperator());
            log.debug("this.get��������(�j��DEFAULT�i�����w��Ȃ��j�ȊO�̏ꍇ�B");   
        }          
        
        log.debug("�����������Z�q = " + l_strOrderCondOperatorReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strOrderCondOperatorReturn;
    }
    
    /**
     * (get�t�w�l��l�^�C�v)<BR>
     * �����̋t�w�l��l�^�C�v�A���t�w�l��l�^�C�v�̓��A<BR>
     * �L���ȋt�w�l��l�^�C�v��ԋp����B<BR>
     * <BR>
     * [�p�����[�^.���t�w�l��l�^�C�v != null�̏ꍇ]<BR>
     * �@@�p�����[�^.���t�w�l��l�^�C�v��ԋp����B<BR>
     * <BR>
     * [�p�����[�^.�t�w�l��l�^�C�v != null�̏ꍇ]<BR>
     * �@@�p�����[�^.�t�w�l��l�^�C�v��ԋp����B<BR>
     * @@param l_strStopPriceType - (�t�w�l��l�^�C�v)<BR>
     * �t�w�l��l�^�C�v<BR>
     * @@param l_strOrgStopPriceType - (���t�w�l��l�^�C�v)<BR>
     * ���t�w�l��l�^�C�v<BR>
     * @@return String
     */
    public static String getStopPriceType(String l_strStopPriceType, 
		String l_strOrgStopPriceType)
    {
        final String STR_METHOD_NAME = "getStopPriceType(String, String )";
        log.entering(STR_METHOD_NAME);
       
        // �����̋t�w�l��l�^�C�v�A���t�w�l��l�^�C�v�̓��A
        // �L���ȋt�w�l��l�^�C�v��ԋp����B
        // [�p�����[�^.���t�w�l��l�^�C�v != null�̏ꍇ]
        // �@@�p�����[�^.���t�w�l��l�^�C�v��ԋp����B
        if (l_strOrgStopPriceType != null)
        {
            log.exiting(STR_METHOD_NAME);
        	return l_strOrgStopPriceType;
        }
        
        // [�p�����[�^.�t�w�l��l�^�C�v != null�̏ꍇ]
        // �@@�p�����[�^.�t�w�l��l�^�C�v��ԋp����B     
        if (l_strStopPriceType != null)
        {
            log.exiting(STR_METHOD_NAME);
        	return l_strStopPriceType;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get�t�w�l��l�^�C�v)<BR>
     * �����̒����P�ʂ��A�L���ȋt�w�l��l�^�C�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@���������w��Ȃ��̏ꍇ<BR>
     * <BR>
     * this.get��������(�j��"DEFAULT�i�����w��Ȃ��j"�̏ꍇ�A<BR>
     * null��ԋp����B<BR>
     * <BR>
     * [get��������()�Ɏw�肷�����]<BR>
     * �@@���������F�@@�����P��.��������<BR>
     * �@@�����������F�@@�����P��.����������<BR>
     * <BR>
     * �Q�j�@@�ȊO�̏ꍇ<BR>
     * <BR>
     * this.get�t�w�l��l�^�C�v()�ɏ������Ϗ�����B<BR>
     * <BR>
     * [get�t�w�l��l�^�C�v()�Ɏw�肷�����]<BR>
     * �@@�t�w�l��l�^�C�v�F�@@�����P��.�t�w�l��l�^�C�v<BR>
     * �@@���t�w�l��l�^�C�v�F�@@�����P��.���t�w�l��l�^�C�v<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getStopPriceType(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getStopPriceType(IfoOrderUnit )";
        log.entering(STR_METHOD_NAME);
        String l_strStopPriceTypeReturn = null;
        
        //�p�����[�^�l��NULL
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }  
       
        // �����̒����P�ʂ��A�L���ȋt�w�l��l�^�C�v��ԋp����B
        // �P�j�@@���������w��Ȃ��̏ꍇ
        // this.get��������(�j��"DEFAULT�i�����w��Ȃ��j"�̏ꍇ�A
        // null��ԋp����B
        // [get��������()�Ɏw�肷�����]
        // �@@���������F�@@�����P��.��������
        // �@@�����������F�@@�����P��.����������
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
			l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������(�j��DEFAULT�i�����w��Ȃ��j�̏ꍇ�B");   
        }
        else 
        {
            // �Q�j�@@�ȊO�̏ꍇ
            // this.get�t�w�l��l�^�C�v()�ɏ������Ϗ�����B
            // [get�t�w�l��l�^�C�v()�Ɏw�肷�����]
            // �@@�t�w�l��l�^�C�v�F�@@�����P��.�t�w�l��l�^�C�v
            // �@@���t�w�l��l�^�C�v�F�@@�����P��.���t�w�l��l�^�C�v 
        	l_strStopPriceTypeReturn = getStopPriceType(l_ifoOrderUnitRow.getStopPriceType(),
    			l_ifoOrderUnitRow.getOrgStopPriceType());
            log.debug("this.get��������(�j��DEFAULT�i�����w��Ȃ��j�ȊO�̏ꍇ�B");   
        }        
        
        log.debug("�t�w�l��l�^�C�v = " + l_strStopPriceTypeReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strStopPriceTypeReturn;
    }
    
    /**
     * (get�t�w�l��l)<BR>
     * �����̋t�w�l��l�A���t�w�l��l�̓��A<BR>
     * �L���ȋt�w�l��l��ԋp����B<BR>
     * <BR>
     * [�p�����[�^.���t�w�l��l != null�̏ꍇ]<BR>
     * �@@�p�����[�^.���t�w�l��l��ԋp����B<BR>
     * <BR>
     * [�p�����[�^.�t�w�l��l != null�̏ꍇ]<BR>
     * �@@�p�����[�^.�t�w�l��l��ԋp����B<BR>
     * @@param l_strStopOrderPrice - (�t�w�l��l)<BR>
     * �t�w�l��l<BR>
     * @@param l_strOrgStopOrderPrice - (���t�w�l��l)<BR>
     * ���t�w�l��l<BR>
     * @@return String
     */
    public static String getStopOrderPrice(String l_strStopOrderPrice, 
		String l_strOrgStopOrderPrice)
    {
        final String STR_METHOD_NAME = "getStopOrderPrice(String, String)";
        log.entering(STR_METHOD_NAME);
       
        // �����̋t�w�l��l�A���t�w�l��l�̓��A
        // �L���ȋt�w�l��l��ԋp����B
        // [�p�����[�^.���t�w�l��l != null�̏ꍇ]
        // �@@�p�����[�^.���t�w�l��l��ԋp����B
        if (l_strOrgStopOrderPrice != null)
        {
            log.exiting(STR_METHOD_NAME);
        	return l_strOrgStopOrderPrice;
        }

        // [�p�����[�^.�t�w�l��l != null�̏ꍇ]
        // �@@�p�����[�^.�t�w�l��l��ԋp����B   
        if (l_strStopOrderPrice != null)
        {
            log.exiting(STR_METHOD_NAME);
        	return l_strStopOrderPrice;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get�t�w�l��l)<BR>
     * �����̒����P�ʂ��A�L���ȋt�w�l��l��ԋp����B<BR>
     * <BR>
     * �P�j�@@���������w��Ȃ��̏ꍇ<BR>
     * <BR>
     * this.get��������(�j��"DEFAULT�i�����w��Ȃ��j"�̏ꍇ�A<BR>
     * null��ԋp����B<BR>
     * <BR>
     * [get��������()�Ɏw�肷�����]<BR>
     * �@@���������F�@@�����P��.��������<BR>
     * �@@�����������F�@@�����P��.����������<BR>
     * <BR>
     * �Q�j�@@�ȊO�̏ꍇ<BR>
     * <BR>
     * this.get�t�w�l��l()�ɏ������Ϗ�����B<BR>
     * <BR>
     * [get�t�w�l��l()�Ɏw�肷�����]<BR>
     * �@@�t�w�l��l�F�@@�����P��.�t�w�l��l<BR>
     * �@@���t�w�l��l�F�@@�����P��.���t�w�l��l<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getStopOrderPrice(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getStopOrderPrice(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        String l_strStopOrderPriceReturn = null;
        
        //�p�����[�^�l��NULL
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }

        // �����̒����P�ʂ��A�L���ȋt�w�l��l��ԋp����B
        // �P�j�@@���������w��Ȃ��̏ꍇ
        // this.get��������(�j��"DEFAULT�i�����w��Ȃ��j"�̏ꍇ�A
        // null��ԋp����B
        // [get��������()�Ɏw�肷�����]
        // �@@���������F�@@�����P��.��������
        // �@@�����������F�@@�����P��.����������
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
			l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������(�j��DEFAULT�i�����w��Ȃ��j�̏ꍇ�B");   
        }
        else 
        {
            // �Q�j�@@�ȊO�̏ꍇ
            // this.get�t�w�l��l()�ɏ������Ϗ�����B
            // [get�t�w�l��l()�Ɏw�肷�����]
            // �@@�t�w�l��l�F�@@�����P��.�t�w�l��l
            // �@@���t�w�l��l�F�@@�����P��.���t�w�l��l 
            String l_strStopOrderPrice = null;
            String l_strOrgStopOrderPrice = null;
            if (!l_ifoOrderUnitRow.getStopOrderPriceIsNull()) 
            {
                l_strStopOrderPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitRow.getStopOrderPrice());
            }
            if (!l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull()) 
            {
                l_strOrgStopOrderPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitRow.getOrgStopOrderPrice());
            }
        	l_strStopOrderPriceReturn = getStopOrderPrice(
    			l_strStopOrderPrice, l_strOrgStopOrderPrice);
            log.debug("this.get��������(�j��DEFAULT�i�����w��Ȃ��j�ȊO�̏ꍇ�B");   
        }        

        log.debug("�t�w�l��l = " + l_strStopOrderPriceReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strStopOrderPriceReturn;
    }
    
    /**
     * (get���v�w�l�p�����P��)<BR>
     * �����̒����P�ʂ�茳�v�w�l�p�����P����ԋp����B<BR> 
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.��������<BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.����������<BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@�p�����[�^.�����P��.���iW�w�l�j�����w�l��ԋp����B <BR>
     * �@@[��L�ȊO] <BR>
     * �@@�@@null��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrgWLimitOrderPrice(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPrice(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^�l��NULL
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�����P��.�������� 
        // �@@�@@�����������F�@@�p�����[�^.�����P��.���������� 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
			l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
        	new WEB3OptionOrderManagerImpl();
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_orderUnit);
        
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B
        // �@@[is�X�g�b�v����������()�Ɏw�肷�����]
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P��       
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_orderUnit);
        
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B
        //   [�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]  
        //     �p�����[�^.�����P��.���iW�w�l�j�����w�l��ԋp����B  
        // �@@[��L�ȊO]
        // �@@�@@null��ԋp����B
        if (l_blnStopOrderOpen || l_blnStopOrderExpire)
        {
            String l_strOrgWLimitPrice = null;
            if (!l_ifoOrderUnitRow.getOrgWLimitPriceIsNull()) 
            {
                l_strOrgWLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitRow.getOrgWLimitPrice());
            }
        	log.exiting(STR_METHOD_NAME);
        	return l_strOrgWLimitPrice;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get���v�w�l�p�����P��)<BR>
     * �����̒���������茳�v�w�l�p�����P����ԋp����B<BR> 
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.��������.��������<BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.����������<BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.��������<BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.��������<BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.��������.���iW�w�l�j�����w�l��ԋp����B<BR>
     * �@@[��L�ȊO]<BR>
     * �@@�@@null��ԋp����B<BR>
     * @@param l_ifoOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrgWLimitOrderPrice(IfoOrderAction l_ifoOrderAction) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPrice(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^�l��NULL
        if (l_ifoOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.��������.�������� 
        // �@@�@@�����������F�@@�p�����[�^.��������.���������� 
        IfoOrderActionRow l_ifoOrderActionRow =
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderActionRow.getOrderConditionType(), 
    		l_ifoOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        	
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�������� 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_ifoOrderAction);
        
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v����������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�������� 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_ifoOrderAction);
        
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B 
        //   [�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]
        //     �p�����[�^.��������.���iW�w�l�j�����w�l��ԋp����B
        // �@@[��L�ȊO] 
        // �@@�@@null��ԋp����B 
        if (l_blnStopOrderOpen || l_blnStopOrderExpire)
        {
            String l_strOrgWLimitPrice = null;
            if (!l_ifoOrderActionRow.getOrgWLimitPriceIsNull())
            {
                l_strOrgWLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderActionRow.getOrgWLimitPrice());
            }
        	log.exiting(STR_METHOD_NAME);
        	return l_strOrgWLimitPrice;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get���v�w�l�p�����P���敪)<BR>
     * �����̒����P�ʂ�茳�v�w�l�p�����P���敪��ԋp����B <BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.��������<BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.����������<BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�����P��.���iW�w�l�j�����w�l == 0�ł���΁A"���s"�A<BR>
     * �@@�@@�ȊO��"�w�l"��ԋp����B<BR>
     * �@@[��L�ȊO]<BR>
     * �@@�@@null��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrgWLimitOrderPriceDiv(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPriceDiv(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^�l��NULL
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�����P��.�������� 
        // �@@�@@�����������F�@@�p�����[�^.�����P��.���������� 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
			l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        	
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���R�[������B 
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_orderUnit);
        
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v����������()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P��     
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_orderUnit);
        
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B 
        //   [�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]
        //     �p�����[�^.�����P��.���iW�w�l�j�����w�l == 0�ł���΁A"���s"�A
        //     �ȊO��"�w�l"��ԋp����B
        // �@@[��L�ȊO] 
        // �@@�@@null��ԋp����B 
        if (l_blnStopOrderOpen || l_blnStopOrderExpire)
        {
    		double 	l_dblOrgWLimitPrice = l_ifoOrderUnitRow.getOrgWLimitPrice();
        	if (l_dblOrgWLimitPrice == 0)
        	{
        		log.exiting(STR_METHOD_NAME);
        		return WEB3OrderPriceDivDef.MARKET_PRICE;
        	}
        	else
        	{
           		log.exiting(STR_METHOD_NAME);
        		return WEB3OrderPriceDivDef.LIMIT_PRICE;
        	}
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get���v�w�l�p�����P���敪)<BR>
     * �����̒���������茳�v�w�l�p�����P���敪��ԋp����B<BR> 
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.��������.��������<BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.����������<BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.��������<BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.��������<BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.��������.���iW�w�l�j�����w�l == 0�ł���΁A"���s"�A<BR>
     * �@@�@@�ȊO��"�w�l"��ԋp����B<BR>
     * �@@[��L�ȊO]<BR>
     * �@@�@@null��ԋp����B<BR>
     * @@param l_ifoOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrgWLimitOrderPriceDiv(IfoOrderAction l_ifoOrderAction) 
    	throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPriceDiv(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^�l��NULL
        if (l_ifoOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.��������.�������� 
        // �@@�@@�����������F�@@�p�����[�^.��������.���������� 
        IfoOrderActionRow l_ifoOrderActionRow =
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderActionRow.getOrderConditionType(), 
    		l_ifoOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
        	new WEB3OptionOrderManagerImpl();
        
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���R�[������B 
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�������� 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_ifoOrderAction);
        
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v����������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�������� 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_ifoOrderAction);
        
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B 
        // [�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]
        //   �p�����[�^.��������.���iW�w�l�j�����w�l == 0�ł���΁A"���s"�A
        //   �ȊO��"�w�l"��ԋp����B
        // [��L�ȊO]
        //   null��ԋp����B
        if (l_blnStopOrderOpen || l_blnStopOrderExpire)
        {
    		double l_dblOrgWLimitPrice = l_ifoOrderActionRow.getOrgWLimitPrice();
        	if (l_dblOrgWLimitPrice == 0)
        	{
        		log.exiting(STR_METHOD_NAME);
        		return WEB3OrderPriceDivDef.MARKET_PRICE;
        	}
        	else
        	{
           		log.exiting(STR_METHOD_NAME);
        		return WEB3OrderPriceDivDef.LIMIT_PRICE;
        	}
        }
        // �@@[��L�ȊO] 
        // �@@�@@null��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get���v�w�l�p���s����)<BR>
     * �����̒����P�ʂ�茳�v�w�l�p���s������ԋp����B<BR> 
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.��������<BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.����������<BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp���鎷�s���������肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]<BR>
     * �@@�@@���s���� = �p�����[�^.�����P��.���iW�w�l�j���s����<BR>
     * �@@[��L�ȊO]<BR>
     * �@@�@@null��ԋp����B<BR>
     * <BR>
     * �T�j�@@this.get���s�����iPR�w�j()���\�b�h���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[get���s�����iPR�w�j()�Ɏw�肷�����]<BR>
     * �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s����<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrgWLimitExecCondType(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitExecCondType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^�l��NULL
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�����P��.�������� 
        // �@@�@@�����������F�@@�p�����[�^.�����P��.���������� 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
    		l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(l_orderUnit);
        
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v����������()�Ɏw�肷�����] 
        // �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(l_orderUnit);
        
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp���鎷�s���������肷��B 
        // [�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]
        //   ���s���� = �p�����[�^.�����P��.���iW�w�l�j���s����
        IfoOrderExecutionConditionType l_orgexecutionConditionType = null;
        if (l_blnStopOrderOpen || l_blnStopOrderExpire)
        {
        	l_orgexecutionConditionType = 
    			l_ifoOrderUnitRow.getOrgWLimitExecCondType();        	
        }
        else
        {
            // �@@[��L�ȊO] 
            // �@@�@@null��ԋp����B
            log.exiting(STR_METHOD_NAME);
        	return null;
        }
        
        // �T�j�@@this.get���s�����iPR�w�j()���\�b�h���R�[�����A 
        // �@@�߂�l��ԋp����B 
        // �@@[get���s�����iPR�w�j()�Ɏw�肷�����] 
        // �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s����                               
        String l_strExecutionCondByPr = getExecutionCondByPr(l_orgexecutionConditionType);
        log.exiting(STR_METHOD_NAME);
        return l_strExecutionCondByPr;
    }
    
    /**
     * (get���v�w�l�p���s����)<BR>
     * �����̒���������茳�v�w�l�p���s������ԋp����B<BR> 
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.��������.��������<BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.����������<BR>
     * <BR>
     * �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.��������<BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.��������<BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp���鎷�s���������肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]<BR>
     * �@@�@@���s���� = �p�����[�^.��������.���iW�w�l�j���s����<BR>
     * �@@[��L�ȊO]<BR>
     * �@@�@@null��ԋp����B<BR>
     * <BR>
     * �T�j�@@this.get���s�����iPR�w�j()���\�b�h���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[get���s�����iPR�w�j()�Ɏw�肷�����]<BR>
     * �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s����<BR>
     * @@param l_ifoOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrgWLimitExecCondType(IfoOrderAction l_ifoOrderAction) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitExecCondType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^�l��NULL
        if (l_ifoOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A 
        // �@@null��ԋp����B 
        // �@@[get��������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.��������.�������� 
        // �@@�@@�����������F�@@�p�����[�^.��������.���������� 
        IfoOrderActionRow l_ifoOrderActionRow =
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderActionRow.getOrderConditionType(), 
    		l_ifoOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        
        // �Q�j�@@OP�����}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�������� 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_ifoOrderAction);
        
        // �R�j�@@OP�����}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B 
        // �@@[is�X�g�b�v����������()�Ɏw�肷�����] 
        // �@@�@@���������F�@@�p�����[�^.�������� 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_ifoOrderAction);
        
        // �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp���鎷�s���������肷��B 
        // [�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]
        //   ���s���� = �p�����[�^.��������.���iW�w�l�j���s����
        IfoOrderExecutionConditionType l_orgexecutionConditionType = null;
        if (l_blnStopOrderOpen || l_blnStopOrderExpire)
        {
        	l_orgexecutionConditionType = 
    			l_ifoOrderActionRow.getOrgWLimitExecCondType();        	
        }
        else
        {
            // �@@[��L�ȊO] 
            // �@@�@@null��ԋp����B 
            log.exiting(STR_METHOD_NAME);
        	return null;
        }
        
        // �T�j�@@this.get���s�����iPR�w�j()���\�b�h���R�[�����A 
        // �@@�߂�l��ԋp����B 
        // �@@[get���s�����iPR�w�j()�Ɏw�肷�����] 
        // �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s����                           
        String l_strReturn = getExecutionCondByPr(l_orgexecutionConditionType);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (get���񒍕��̒����P��ID)<BR>
     * �p�����[�^.���������敪���A���񒍕��̒����P��ID��ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.���������敪�ɂ�蕪�򂵁A�Ή�����l��ԋp����B <BR>
     * <BR>
     * �p�����[�^.���������敪���A  <BR>
     * �@@["��������"�̏ꍇ]  <BR>
     * �@@�@@null��ԋp����B  <BR>
     * �@@["�o����܂Œ���"�̏ꍇ]  <BR>
     * �@@�@@0��ԋp����B  <BR>
     * �@@["�[��܂Œ���"�̏ꍇ]  <BR>
     * �@@�@@null��ԋp����B<BR>
     * @@param l_strExpirationDateType - (���������敪)<BR>
     * @@return Long
     */
    public static Long getFirstOrderUnitId(String l_strExpirationDateType)
    {
        final String STR_METHOD_NAME = "getFirstOrderUnitId(String)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.���������敪���A["��������"�̏ꍇ]null��ԋp����B
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_strExpirationDateType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //["�o����܂Œ���"�̏ꍇ]0��ԋp����B
        else if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
        {
            log.exiting(STR_METHOD_NAME);
            return new Long(0);
        }
        //["�[��܂Œ���"�̏ꍇ]null��ԋp����B
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get�[��O�J�z�Ώۃt���O)<BR>
     * �[��O�J�z�Ώۃt���O��ԋp����B   <BR>
     * <BR>
     * �@@�P�j���X�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@�@@�@@�p�����[�^.���XID�ɂĎ擾����B <BR>
     * <BR>
     * �@@�Q�j���X�I�u�W�F�N�g.is�[����{()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[����] <BR>
     * �@@�@@�@@�����^�C�v�F "�敨�E�I�v�V����" <BR>
     * <BR>
     * �@@�R�j�p�����[�^.���������敪�ƂQ�j�̖߂�l�ɂ�蕪�򂵁A�Ή�����l��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�R�|�P�j�p�����[�^.���������敪���A"��������"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@false�i�[��O�J�z�Ȃ��j��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�R�|�Q�j�p�����[�^.���������敪���A"�o����܂Œ���"�@@����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�Q�j�̖߂�l = true�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@true�i�[��O�J�z����j��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�R�|�R�j�p�����[�^.���������敪���A"�o����܂Œ���"�@@����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�Q�j�̖߂�l = false�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@false�i�[��O�J�z�Ȃ��j��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�R�|�S�j�p�����[�^.���������敪���A"�[��܂Œ���"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@true�i�[��O�J�z����j��ԋp����B<BR>
     * @@param l_strExpirationDateType - (���������敪)<BR>
     * @@param l_branchId - (���XID)<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public static boolean getEveningSessionCarryOverFlag(
        String l_strExpirationDateType,
        long l_branchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEveningSessionCarryOverFlag(String, long)";
        log.entering(STR_METHOD_NAME);

        //�P�j���X�I�u�W�F�N�g���擾����B
        //�p�����[�^.���XID�ɂĎ擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(l_branchId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "WEB3IfoDataAdapter." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���X�I�u�W�F�N�g.is�[����{()���R�[������B
        //[����] �����^�C�v�F "�敨�E�I�v�V����"
        boolean l_blnIsEveningSession = l_branch.isEveningSessionEnforcemented(ProductTypeEnum.IFO);

        //�R�j�p�����[�^.���������敪�ƂQ�j�̖߂�l�ɂ�蕪�򂵁A�Ή�����l��ԋp����B
        //�R�|�P�j�p�����[�^.���������敪���A"��������"�̏ꍇ
        //false�i�[��O�J�z�Ȃ��j��ԋp����B
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_strExpirationDateType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�R�|�Q�j�p�����[�^.���������敪���A"�o����܂Œ���"�@@���Q�j�̖߂�l = true�̏ꍇ
        //true�i�[��O�J�z����j��ԋp����B
        else if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType)
            && l_blnIsEveningSession)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�R�|�R�j�p�����[�^.���������敪���A"�o����܂Œ���"�@@����
        //�@@�Q�j�̖߂�l = false�̏ꍇ
        //�@@false�i�[��O�J�z�Ȃ��j��ԋp����B
        else if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType)
            && !l_blnIsEveningSession)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�R�|�S�j�p�����[�^.���������敪���A"�[��܂Œ���"�̏ꍇ
        //�@@true�i�[��O�J�z����j��ԋp����B
        else if (WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(l_strExpirationDateType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get�[��O�J�z�Ώۃt���O�iPR�w�j)<BR>
     * �����̒����P�ʂ��APR�w�Ŏg�p����[��O�J�z�Ώۃt���O��ԋp����B <BR> 
     * <BR>
     * �P�j�@@�ȉ��̏����ɂ�蕪�򂵁A�[��O�J�z�Ώۃt���O��ԋp����B  <BR>
     * <BR>
     * �@@�@@�@@[�p�����[�^.�����P��.�[��O�J�z�Ώۃt���O == "�[��O�J�z�Ȃ�"�̏ꍇ]  <BR>
     * <BR>
     * �@@�@@�@@�@@�@@false�i�[��O�J�z�Ȃ��j��ԋp����B  <BR>
     * <BR>
     * �@@�@@�@@[�p�����[�^.�����P��.�[��O�J�z�Ώۃt���O == "�[��O�J�z����"�̏ꍇ]  <BR>
     * <BR>
     * �@@�@@�@@�@@�@@true�i�[��O�J�z����j��ԋp����B<BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     */
    public static boolean getEveningSessionCarryOverFlagPr(IfoOrderUnit l_ifoOrderUnit)
    {
        final String STR_METHOD_NAME = "getEveningSessionCarryOverFlagPr(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�����P��.�[��O�J�z�Ώۃt���O == "�[��O�J�z�Ȃ�"�̏ꍇ
        //false�i�[��O�J�z�Ȃ��j��ԋp����B
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        if (BooleanEnum.FALSE.equals(l_ifoOrderUnitRow.getEveningSessionCarryoverFlag()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //�p�����[�^.�����P��.�[��O�J�z�Ώۃt���O == "�[��O�J�z����"�̏ꍇ
        //true�i�[��O�J�z����j��ԋp����B
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (get���������敪)<BR>
     * �����̒����P�ʂ��A�Y�����钍�������敪��ԋp����B <BR>
     * <BR>
     * �P�j�@@OP�����}�l�[�W��.is�[��܂Œ���()���R�[������B<BR>
     * <BR>
     * �@@[is�[��܂Œ���()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �Q�j�@@�[��܂Œ����i�P�j�̖߂�l == true�j�̏ꍇ�A <BR>
     * �@@"�[��܂Œ���"��ԋp����B <BR>
     * <BR>
     * �R�j�@@�[��܂Œ����łȂ��i��L�ȊO�́j�ꍇ�A <BR>
     * �@@OP�����}�l�[�W��.is�o����܂Œ����P��()���R�[������B <BR>
     * <BR>
     * �@@[is�o����܂Œ����P��()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * �@@ <BR>
     * �@@true�i�o����܂Œ����j���ԋp���ꂽ�ꍇ�A"�o����܂Œ���"��ԋp����B <BR>
     * �@@false�i�������蒍���j���ԋp���ꂽ�ꍇ�A"��������"��ԋp����B<BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getExpirationDateType(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDateType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^�l��NULL
        if (l_ifoOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3IfoDataAdapter." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        String l_strExpirationDateType = null;

        //�P�j�@@OP�����}�l�[�W��.is�[��܂Œ���()���R�[������B
        //[is�[��܂Œ���()�Ɏw�肷�����]
        //�����P�ʁF�@@�p�����[�^.�����P��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
        boolean l_blnIsEveningSessionOrder = l_orderMgr.isEveningSessionOrder(l_ifoOrderUnit);

        //�Q�j�@@�[��܂Œ����i�P�j�̖߂�l == true�j�̏ꍇ�A
        //�@@"�[��܂Œ���"��ԋp����B
        if (l_blnIsEveningSessionOrder)
        {
            l_strExpirationDateType = WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER;
        }
        //�R�j�@@�[��܂Œ����łȂ��i��L�ȊO�́j�ꍇ�A
        //�@@OP�����}�l�[�W��.is�o����܂Œ����P��()���R�[������B
        //[is�o����܂Œ����P��()�Ɏw�肷�����]
        //�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        //�@@true�i�o����܂Œ����j���ԋp���ꂽ�ꍇ�A"�o����܂Œ���"��ԋp����B
        //�@@false�i�������蒍���j���ԋp���ꂽ�ꍇ�A"��������"��ԋp����B
        else
        {
            boolean l_blnIsCarriedOrderUnit = l_orderMgr.isCarriedOrderUnit(l_ifoOrderUnit);
            if (l_blnIsCarriedOrderUnit)
            {
                l_strExpirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            }
            else
            {
                l_strExpirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_strExpirationDateType;
    }

    /**
     * (get���v��敪)<BR>
     * �����̓��v��敪��ϊ����ĕԋp����B<BR>
     * <BR>
     * [�p�����[�^.���v��敪 = null�̏ꍇ]<BR>
     * �@@"1"�i���v��ȊO�j��ԋp����B<BR>
     * [��L�ȊO�̏ꍇ]<BR>
     * �@@�p�����[�^.���v��敪��ԋp����B<BR>
     * @@param l_strDayTradeType - (���v��敪)<BR>
     * @@return String
     */
    public static String getDayTradeType(String l_strDayTradeType)
    {
        final String STR_METHOD_NAME = "getDayTradeType(String)";
        log.entering(STR_METHOD_NAME);
        if (l_strDayTradeType == null)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ContractCheckDef.CONTRACT_CHECK;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_strDayTradeType;
        }
    }
}
@
