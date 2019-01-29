head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityDataAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����f�[�^�A�_�v�^(WEB3EquityDataAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/03/02 �i�R (SRA)
Revesion History : 2006/07/04 �юu�� (���u) �d�l�ύX���f��943
Revesion History : 2006/08/29 �ęԍg (���u) �d�l�ύX���f��970
Revesion History : 2006/11/01 �юu�� (���u) �d�l�ύX���f��951
Revesion History : 2006/11/07 �đo�g (���u) �d�l�ύX���f��No.990,No.994,No.996,No.1007,No.1015,No.1047,No.1049
Revesion History : 2006/11/07 �đo�g (���u) �d�l�ύX���f��No.1055,No.1059
Revesion History : 2006/11/28 ������ (���u) �d�l�ύX���f��No.1076
Revesion History : 2007/06/05 �����q (���u) �d�l�ύX���f��No.1164
Revesion History : 2007/07/24 �����q (���u) �d�l�ύX���f��No.1188
Revesion History : 2007/10/10 �����q (���u) �d�l�ύX���f��No.1194
*/

package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3ForcedExpireType;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.define.WEB3TriggerOrderStatusDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.equity.define.WEB3EquityAcceptTypeDef;
import webbroker3.equity.define.WEB3EquityDelayDivDef;
import webbroker3.equity.define.WEB3EquityExecStatusTypeDef;
import webbroker3.equity.define.WEB3EquityExpirationStatusDef;
import webbroker3.equity.define.WEB3EquityOrderSpecTypeDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�����f�[�^�A�_�v�^�j�B<BR>
 * <BR>
 * ���������̃f�[�^�ϊ����s���N���X�B <BR>
 * <BR>
 * @@version 1.0
 */

public class WEB3EquityDataAdapter
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityDataAdapter.class);

    /**
     * (get����敪)<BR>
     * <BR>
     * �����̒�����ʂ��APR�w�Ŏg�p�������敪��ԋp����B<BR>
     *<BR>
     *�P�j�p�����[�^.������ʂɂ�蕪�򂵁A�Ή�����l��ԋp����B<BR>  
     *<BR>
     *�@@�p�����[�^.������ʂ��A<BR>  
     *�@@[OrderTypeEnum.�������t�����̏ꍇ]<BR>  
     *�@@�@@"�������t����"��ԋp����B<BR>  
     *�@@[OrderTypeEnum.�������t�����̏ꍇ]<BR>  
     *�@@�@@"�������t����"��ԋp����B<BR>  
     *�@@[OrderTypeEnum.�V�K���������̏ꍇ]<BR>  
     *�@@�@@"�V�K��������"��ԋp����B  <BR>
     *�@@[OrderTypeEnum.�V�K���������̏ꍇ]<BR>  
     *�@@�@@"�V�K��������"��ԋp����B  <BR>
     *�@@[OrderTypeEnum.�����ԍϒ����̏ꍇ]<BR>  
     *�@@�@@"�����ԍϒ���"��ԋp����B  <BR>
     *�@@[OrderTypeEnum.�����ԍϒ����̏ꍇ]<BR>  
     *�@@�@@"�����ԍϒ���"��ԋp����B<BR>  
     *�@@[OrderTypeEnum.���������̏ꍇ] <BR> 
     *�@@�@@"��������"��ԋp����B  <BR>
     *�@@[OrderTypeEnum.���n�����̏ꍇ]<BR>  
     *�@@�@@"���n����"��ԋp����B<BR>  
     *�@@[��L�ȊO�̏ꍇ]  <BR>
     *�@@�@@null��ԋp����B  <BR>
     *<BR>
     * @@param l_orderUnit
     * @@return
     * @@throws WEB3BaseException
     */
    public static String getTradingType(OrderTypeEnum l_orderType)
    {
        final String STR_METHOD_NAME =
            " getTradingType(OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        String l_strTradingType = null;

        //[OrderTypeEnum.�������t�����̏ꍇ]  
        //�@@"�������t����"��ԋp����B
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.EQUITY_BUY);

        //[OrderTypeEnum.�������t�����̏ꍇ]  
        //�@@"�������t����"��ԋp����B
        } else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.EQUITY_SELL);

        //[OrderTypeEnum.�V�K���������̏ꍇ]  
        //�@@"�V�K��������"��ԋp����B
        } else if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MARGIN_LONG);

        //[OrderTypeEnum.�V�K���������̏ꍇ]  
        //�@@"�V�K��������"��ԋp����B
        } else if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MARGIN_SHORT);
        //�@@[OrderTypeEnum.�����ԍϒ����̏ꍇ]  
        //�@@�@@"�����ԍϒ���"��ԋp����B
        } else if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.CLOSE_MARGIN_LONG);
        //[OrderTypeEnum.�����ԍϒ����̏ꍇ]  
        //�@@"�����ԍϒ���"��ԋp����B
        } else if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.CLOSE_MARGIN_SHORT);

        //[OrderTypeEnum.���������̏ꍇ] 
        //�@@"��������"��ԋp����B
        } else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.SWAP_MARGIN_LONG);

        //[OrderTypeEnum.���n�����̏ꍇ]  
        //�@@"���n����"��ԋp����B
        } else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.SWAP_MARGIN_SHORT);

        //[��L�ȊO�̏ꍇ]
        // null��ԋp����B
        } else
        {
            l_strTradingType = null;
        }

        log.exiting(STR_METHOD_NAME);

        return l_strTradingType;
    }
    
    /**
     * (get������ԋ敪)<BR>
     * �w�肳�ꂽ�����P�ʂ�PR�w�Ŏg�p���钍����ԋ敪��ԋp����B  <BR>
     * <BR>
     * (1)������.�����P�ʂ����������P�ʌ^�ɃL���X�g����B<BR>
     * <BR>
     * (2)�g�����������}�l�[�W��.is�o����܂Œ����P��(�����̒����P��)�̖߂�l == true�A<BR>
     * �@@�@@�@@���@@�����̒����P��.�����������t���Ɩ����t�iGtlUtils.getTradingSystem().getBizDate()�j�A<BR>
     * �@@�@@�@@���@@�����P��.�����L����ԁ�CLOSED�i�N���[�Y�j�A<BR>
     * �@@�@@�@@���@@�����P��.�����敪��EXPIRED�i�����ρj�A<BR>
     * �@@�@@�@@���@@�����P��.�����G���[���R�R�[�h��"0000�F����"�̏ꍇ�A"�J�z���s"��Ԃ��B<BR>
     * <BR>
     * (3)�����̒����P��.isUnexecuted( ) == true�A <BR>
     * �@@�@@�@@���@@�����P��.�����L����ԁ�CLOSED�i�N���[�Y�j�A <BR>
     * �@@�@@�@@���@@�����P��.�����敪��INVALIDATED_BY_MKT�i�}�[�P�b�g���ہj�̏ꍇ�A<BR>
     * �@@�@@�@@"�S������"��Ԃ��B  <BR>
     * <BR>
     * (4)�����̒����P��.isPartiallyExecuted( ) == true�A <BR>
     * �@@�@@�@@���@@�����P��.�����L����ԁ�CLOSED�i�N���[�Y�j�A  <BR>
     * �@@�@@�@@���@@�����P��.�����敪 ==��INVALIDATED_BY_MKT�i�}�[�P�b�g���ہj�̏ꍇ�A<BR>
     * �@@�@@�@@"�ꕔ����"��Ԃ��B  <BR>
     * <BR>
     * (5)�����̒����P��.�����L����ԁ�CLOSED�i�N���[�Y�j�A  <BR>
     * �@@�@@�@@���@@�����P��.�����敪��EXPIRED�i�����ρj�̏ꍇ�A"����"��Ԃ��B <BR>
     * �@@�@@�@@���o���I���ʒm�Œ��������������ꍇ <BR>
     * <BR>
     * (6)�g�����������}�l�[�W��.is�J�z�����P��(�����̒����P��)�̖߂�l == true�A<BR>
     * �@@�@@�@@���@@�����P��.������ԁ�ACCEPTED�i��t�ρi�V�K�����j�j�̏ꍇ�A"�J�z��"��Ԃ��B<BR>
     * <BR>
     * (7)�����P��.������ԁ�MODIFY_ACCEPTED�i��t��(�ύX����)�j<BR>
     * �@@�@@�@@���@@�����P��.���������E����敪��"W�w�l�����ؑ֒�"�̏ꍇ�A"�ؑ֒���"��Ԃ��B<BR>
     * <BR>
     * (8)�����P��.������ԁ�MODIFYING�i������(�ύX����)�j<BR>
     * �@@�@@�@@���@@�����P��.���������E����敪��"W�w�l�����ؑ֒�"�̏ꍇ�A"�ؑ֎�t"��Ԃ��B<BR>
     * <BR>
     * (9)�����P��.������ԁ�MODIFIED�i�����ς�(�ύX����)�j<BR>
     * �@@�@@�@@���@@�����P��.���������E����敪��"W�w�l�����ꕔ�ؑ֊���"�A"W�w�l�����S���ؑ֊���"�̏ꍇ�A<BR>
     * �@@�@@�@@"�ؑ֊���"��Ԃ��B<BR>
     * <BR>
     * (10)�����P��.������ԁ�NOT_MODIFIED�i�������s(�ύX����)�j<BR>
     * �@@�@@�@@���@@�����P��.���������E����敪��"W�w�l�����ؑ֎��s"�̏ꍇ�A"�ؑ֒���(���s)"��Ԃ��B<BR>
     * <BR>
     * (11)��L�ȊO�̏ꍇ�́A�����P��.�������.intValue�𕶎���ŕԂ��B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * ���������P��<BR>
     * @@return String<BR>
     */
    public static String getOrderState(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderState(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_orderStatus = null;
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        //�����P��.�������
        OrderStatusEnum l_orderStatusEnum = l_orderUnitRow.getOrderStatus();
        //�����P��.���������E����敪
        String l_strModifyCancelType = l_orderUnitRow.getModifyCancelType();

        //(2)�g�����������}�l�[�W��.is�o����܂Œ����P��(�����̒����P��)�̖߂�l == true�A
        //  ���@@�����̒����P��.�����������t���Ɩ����t�iGtlUtils.getTradingSystem().getBizDate()�j�A
        //�@@���@@�����P��.�����L����ԁ�CLOSED�i�N���[�Y�j�A
        //�@@���@@�����P��.�����敪��EXPIRED�i�����ρj�A
        //�@@���@@�����P��.�����G���[���R�R�[�h��"0000�F����"�̏ꍇ�A"�J�z���s"��Ԃ��B
        if (l_orderManager.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit) &&
             (WEB3DateUtility.compareToDay(
             l_orderUnit.getExpirationTimestamp(), GtlUtils.getTradingSystem().getBizDate()) >= 0) &&
             OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()) &&
             OrderExpirationStatusEnum.EXPIRED.equals(l_orderUnit.getExpirationStatus()) &&
            !WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED.equals(l_orderUnitRow.getErrorReasonCode()) &&
            !WEB3ErrorReasonCodeDef.NORMAL.equals(l_orderUnitRow.getErrorReasonCode()))
        {
            l_orderStatus = WEB3OrderStatusDef.NOT_TRANSFERED;
        }
        
        //�����̒����P��.isUnexecuted( ) == true�A
        //���@@�����P��.�����L����ԁ�CLOSED�i�N���[�Y�j�A
        //���@@�����P��.�����敪��INVALIDATED_BY_MKT�i�}�[�P�b�g���ہj�̏ꍇ�A
        //"�S������"��Ԃ��B
        else if (l_orderUnit.isUnexecuted() &&
                  OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()) &&
                  OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
        {
            l_orderStatus = WEB3OrderStatusDef.FULL_INAFFECTED;
        }
        
        //�����̒����P��.isPartiallyExecuted( ) == true�A
        //���@@�����P��.�����L����ԁ�CLOSED�i�N���[�Y�j 
        //���@@�����P��.�����敪 ==��INVALIDATED_BY_MKT�i�}�[�P�b�g���ہj�̏ꍇ�A
        //"�ꕔ����"��Ԃ��B
        else if (l_orderUnit.isPartiallyExecuted() &&
                  OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()) &&
                  OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
        {
            l_orderStatus = WEB3OrderStatusDef.PART_INAFFECTED;
        }
        
        //�����̒����P��.�����L����ԁ�CLOSED�i�N���[�Y�j�A
        //���@@�����P��.�����敪��EXPIRED�i�����ρj�̏ꍇ�A"����"��Ԃ��B
        //���o���I���ʒm�Œ��������������ꍇ 
        else if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()) &&
                  OrderExpirationStatusEnum.EXPIRED.equals(l_orderUnit.getExpirationStatus()))
        {
            l_orderStatus = WEB3OrderStatusDef.CLOSED;
        }

        //�g�����������}�l�[�W��.is�J�z�����P��(�����̒����P��)�̖߂�l == true�A
        //���@@�����P��.������ԁ�ACCEPTED�i��t�ρi�V�K�����j�j�̏ꍇ�A"�J�z��"��Ԃ��B
        else if (l_orderManager.isCarryOverOrderUnit((EqTypeOrderUnit)l_orderUnit) && 
                  OrderStatusEnum.ACCEPTED.equals(l_orderStatusEnum))
        {            
            l_orderStatus = WEB3OrderStatusDef.TRANSFERED;            
        }

        //(7)�����P��.������ԁ�MODIFY_ACCEPTED�i��t��(�ύX����)�j
        //�@@�@@���@@�����P��.���������E����敪��"W�w�l�����ؑ֒�"�̏ꍇ�A"�ؑ֒���"��Ԃ��B
        else if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatusEnum) &&
            WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(l_strModifyCancelType))
        {
            l_orderStatus = WEB3OrderStatusDef.TRANSFER_ORDER;
        }

        //(8)�����P��.������ԁ�MODIFYING�i������(�ύX����)�j
        //  ���@@�����P��.���������E����敪��"W�w�l�����ؑ֒�"�̏ꍇ�A"�ؑ֎�t"��Ԃ��B
        else if (OrderStatusEnum.MODIFYING.equals(l_orderStatusEnum) &&
            WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(l_strModifyCancelType))
        {
            l_orderStatus = WEB3OrderStatusDef.TRANSFER_ACCEPT;
        }

        //(9)�����P��.������ԁ�MODIFIED�i�����ς�(�ύX����)�j
        //���@@�����P��.���������E����敪��"W�w�l�����ꕔ�ؑ֊���"�A"W�w�l�����S���ؑ֊���"�̏ꍇ�A
        //"�ؑ֊���"��Ԃ��B
        else if (OrderStatusEnum.MODIFIED.equals(l_orderStatusEnum) &&
            (WEB3ModifyCancelTypeDef.W_LIMIT_PARTIALLY_TRANSFERED.equals(l_strModifyCancelType)
            || WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERED.equals(l_strModifyCancelType)))
        {
            l_orderStatus = WEB3OrderStatusDef.TRANSFERRED;
        }

        //(10)�����P��.������ԁ�NOT_MODIFIED�i�������s(�ύX����)�j
        // ���@@�����P��.���������E����敪��"W�w�l�����ؑ֎��s"�̏ꍇ�A"�ؑ֒���(���s)"��Ԃ��B
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_orderStatusEnum) &&
            WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR.equals(l_strModifyCancelType))
        {
            l_orderStatus = WEB3OrderStatusDef.TRANSFER_ORDER_FAIL;
        }
        //(11)��L�ȊO�̏ꍇ�́A�����P��.�������.intValue�𕶎���ŕԂ��B
        else
        {
            l_orderStatus = Integer.toString(l_orderUnit.getOrderStatus().intValue());
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderStatus;
    }


    /**
     * (get����ԋ敪)<BR>
     * �p�����[�^.�����P�ʂ��APR�w�Ŏg�p�������ԋ敪��ԋp����B<BR>
     * <BR>
     * �P�j�p�����[�^.�����P�ʂ̕ێ�����f�[�^�ɂ�蕪��<BR>
     * �@@�@@�Ή��������ԋ敪��ԋp����B<BR>
     * <BR>
     * �@@�P�|�P�j�p�����[�^.�����P��.isPartiallyExecuted( ) == true�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@"�ꕔ����"��Ԃ��B<BR>
     * <BR>
     * �@@�P�|�Q�j�p�����[�^.�����P��.isFullyExecuted( ) == true�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@"�S������"��Ԃ��B<BR>
     * <BR>
     * �@@�P�|�R�j��L�ȊO�̏ꍇ�́A"�����"��Ԃ��B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * @@return String
     */
    public static String getExecType(EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "getExecType(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_result;

        //�p�����[�^.�����P��.isPartiallyExecuted( ) == true�̏ꍇ�́A
        //"�ꕔ����"��Ԃ��B
        if (l_orderUnit.isPartiallyExecuted())
        {
            l_result = WEB3EquityExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE;
        }
        
        //�p�����[�^.�����P��.isFullyExecuted( ) == true�̏ꍇ�́A
        //"�S������"��Ԃ��B
        else if (l_orderUnit.isFullyExecuted())
        {
            l_result = WEB3EquityExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE;
        }
        
        //��L�ȊO�̏ꍇ�́A"�����"��Ԃ��B
        else
        {
            l_result = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * (get�t�w�l�����󋵋敪)<BR>
     * �p�����[�^.�����P�ʂ��<BR>
     * PR�w�Ŏg�p����t�w�l�����̔����󋵋敪��ԋp����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�����󋵋敪�̔���<BR>
     * <BR>
     * �@@�P�|�P�j�@@�����x���G���[�̔���<BR>
     * �@@�@@�g�����������}�l�[�W��.is�������x������(�����P��) == true �̏ꍇ�A<BR>
     * �@@�@@"�����x���G���["��ԋp����B<BR>
     * <BR>
     * �@@�P�|2�j�@@�ҋ@@���̔���<BR>
     * �@@�@@�����P��.���N�G�X�g�^�C�v��"DEFAULT"�̏ꍇ�A"�ҋ@@��"��ԋp����B<BR>
     * <BR>
     * �@@�P�|�R�j�@@�������A���������̔���<BR>
     * �@@�@@�����P��.���N�G�X�g�^�C�v��"�����T�[�o"�̏ꍇ�A<BR>
     * �@@�@@�|�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null �̏ꍇ�A"������"��ԋp����B<BR>
     * �@@�@@�|�ȊO�A"��������"��ԋp����B<BR>
     * <BR>
     * �@@�P�|�S�j�@@�����R���G���[�̔���<BR>
     * �@@�@@�����P��.���N�G�X�g�^�C�v��"�������s"�̏ꍇ�A"�����R���G���["��ԋp����B<BR>
     * <BR>
     * �@@�P�|�T�j�@@��L�ȊO�̏ꍇ�A"���̑�"��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * ���������P��<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getStopTriggerOrderStatusType(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getStopTriggerOrderStatusType(EquityOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_strTriggerOrderStatus = null;
        EqtypeOrderUnitRow l_orderUnitRow =
             (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //�g�����������}�l�[�W�����擾
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        //�@@�P�|�P�j�@@�����x���G���[�̔��� 
        //�@@�g�����������}�l�[�W��.is�������x������(�����P��) == true �̏ꍇ�A 
        //�@@"�����x���G���["��ԋp����B
        boolean l_blnNotOrderedDelay =
            l_equityOrderManager.isNotOrderedDelayOrder(l_orderUnit);
        if (l_blnNotOrderedDelay)
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR;
        }

        //�P�|�Q�j�@@�ҋ@@���̔���
        else if (WEB3RequestTypeDef.DEFAULT.equals(l_orderUnitRow.getRequestType()))
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_WAITING;
        }
        //�P�|�R�j�@@�������A���������̔���
        else if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_orderUnitRow.getRequestType()))
        {
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
     * (get�v�w�l�p���s�����ꗗ)<BR>
     * �����̎��s�����ꗗ���A  <BR>
     * �v�w�l�p�̎��s�����ꗗ���쐬���ԋp����B<BR>
     * <BR>
     * [�����̔��������ꗗ��"�v�w�l"���܂܂�Ă��Ȃ��ꍇ]  <BR>
     * �@@�@@null��ԋp����B <BR>
     * <BR>
     * [�����̎��s�����ꗗ��"�s�o���������s"���܂܂��ꍇ]<BR>
     * �@@�@@�ȉ��̎��s������v�f�Ƃ���z���ԋp����B <BR>
     * �@@�@@�E"������"  <BR>
     * �@@�@@�E"�s�o���������s"  <BR>
     * [��L�ȊO�̏ꍇ]  <BR>
     * �@@"������"�݂̂�v�f�Ƃ���z���ԋp����B<BR>
     * @@param l_strExecutionConditionTypeList - (���s�����ꗗ)<BR>
     * ���s�����ꗗ  <BR>
     * ���ȉ��̒l����\�������z��  <BR>
     *  <BR>
     * 1�F������  <BR>
     * 3�F��t  <BR>
     * 4�F����  <BR>
     * 7�F�s�o���������s<BR>
     * @@param l_strOrderConditionList - (���������ꗗ)<BR>
     * ���������ꗗ <BR>
     * ���ȉ��̒l����\�������z�� <BR>
     * <BR>
     * 0�FDEFAULT�i�����w��Ȃ��j<BR>
     * 1�F�t�w�l <BR>
     * 2�FW�w�l<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    public static String[] getWLimitExecutionConditionTypeList(
        String[] l_strExecutionConditionTypeList,
        String[] l_strOrderConditionList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitExecutionConditionTypeList(String[], String[])";
        log.entering(STR_METHOD_NAME);

        if (l_strExecutionConditionTypeList == null
            || l_strExecutionConditionTypeList.length == 0
            || l_strOrderConditionList == null
            || l_strOrderConditionList.length == 0)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        int l_intTypeListLength = l_strExecutionConditionTypeList.length;
        int l_intConditionLisLength = l_strOrderConditionList.length;
        boolean l_blnReturn = true;
        for (int i = 0; i < l_intConditionLisLength; i++)
        {
            //[�����̔��������ꗗ��"�v�w�l"���܂܂�Ă��Ȃ��ꍇ]
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

        for (int i = 0; i < l_intTypeListLength; i++)
        {
            //[�����̎��s�����ꗗ��"�s�o���������s"���܂܂��ꍇ]
            //�@@�ȉ��̎��s������v�f�Ƃ���z���ԋp����B
            //�@@�E"������"
            //�@@�E"�s�o���������s"
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

        //[��L�ȊO�̏ꍇ]
        //"������"�݂̂�v�f�Ƃ���z���ԋp����B
        String[] l_strWLimitExecutionConditionTypeList =
            {WEB3ExecutionConditionDef.NO_CONDITION};
        log.exiting(STR_METHOD_NAME);
        return l_strWLimitExecutionConditionTypeList;
    }

    /**
     * (get�v�w�l�p���s�����ꗗ)<BR>
     * �igetW�w�l�p���s�����ꗗ�̃I�[�o�[���[�h���\�b�h�j <BR>
     * �����̒����P�ʁA���s�����ꗗ���A  <BR>
     * �v�w�l�p�̎��s�����ꗗ���쐬���ԋp����B  <BR>
     * <BR>
     * �P�j�@@�����̒����P��.����������"�v�w�l"�łȂ��ꍇ�A<BR>
     * �@@�@@�@@null��ԋp����B <BR>
     * <BR>
     * �Q�j�@@�����̒����P�ʂ��o����܂Œ���  <BR>
     * �@@�i�g�����������}�l�[�W��.is�o����܂Œ����P��(�����̒����P��) == true�j�̏ꍇ�A<BR>
     * �@@"������"�݂̂�v�f�Ƃ���z���ԋp����B  <BR>
     * <BR>
     * �R�j�@@��L�ȊO�̏ꍇ�A  <BR>
     * �@@this.getW�w�l�p���s�����ꗗ(�����̎��s�����ꗗ,�����̒����P��.��������)���\�b�h��<BR>
     * �@@�R�[�����A�߂�l��ԋp����B<BR>
     * @@param l_strExecutionConditionTypeList - (���s�����ꗗ)<BR>
     * ���s�����ꗗ  <BR>
     * ���ȉ��̒l����\�������z��  <BR>
     *  <BR>
     * 1�F������  <BR>
     * 3�F��t  <BR>
     * 4�F����  <BR>
     * 7�F�s�o���������s<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    public static String[] getWLimitExecutionConditionTypeList(
        String[] l_strExecutionConditionTypeList,
        EqTypeOrderUnit l_eqTypeOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitExecutionConditionTypeList(String[], EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //�P�j�@@�����̒����P��.����������"�v�w�l"�łȂ��ꍇ�A
        //�@@null��ԋp����B
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strOrderCondetionType =
            l_orderUnitRow.getOrderConditionType();
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�����̒����P�ʂ��o����܂Œ���
        //�@@�i�g�����������}�l�[�W��.is�o����܂Œ����P��(�����̒����P��) == true�j�̏ꍇ�A
        //�@@"������"�݂̂�v�f�Ƃ���z���ԋp����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager.isCarriedOrderUnit(l_eqTypeOrderUnit))
        {
            String[] l_strWLimitExecutionConditionTypeList =
                {WEB3ExecutionConditionDef.NO_CONDITION};
            log.exiting(STR_METHOD_NAME);
            return l_strWLimitExecutionConditionTypeList;
        }

        //�R�j�@@��L�ȊO�̏ꍇ�A
        //�@@this.getW�w�l�p���s�����ꗗ(�����̎��s�����ꗗ,�����̒����P��.��������)���\�b�h��
        //�@@�R�[�����A�߂�l��ԋp����B
        String[] l_strWLimitExecutionConditionTypeList =
            WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(
                l_strExecutionConditionTypeList,
                new String[]{l_strOrderCondetionType});

        log.exiting(STR_METHOD_NAME);
        return l_strWLimitExecutionConditionTypeList;
    }

    /**
     * �iget���i�敪�j<BR>
     * <BR>
     * �����̒����P�ʂ��PR�w�Ŏg�p���鏤�i�敪��ԋp����B<BR>  
     *<BR>
     *�P�j�ȉ��̏����ɂ�蕪�򂵁A�Ή����鏤�i�敪��ԋp����B<BR>  
     *<BR>
     *�@@[�p�����[�^.�����P��.�����J�e�S�� == "��������"�@@����<BR>  
     *�@@  �p�����[�^.�����P��.������� ==<BR>  
     *�@@�@@�@@("����������" or "����������")�̏ꍇ]<BR>  
     *�@@�@@�@@"��������"��ԋp����B  <BR>
     *<BR>
     *�@@[�p�����[�^.�����P��.�����J�e�S�� ==<BR>  
     *�@@�@@�@@("�V�K������" or "�ԍϒ���" or "�������n����")�̏ꍇ]<BR>  
     *�@@�@@�@@"�M�p���"��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return String
     */
    public static String getProductType(EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "getProductType()";
        log.entering(STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = null;
        String l_strWEB3ProductTypeDef = null;
        l_eqtypeOrderUnitParams = (EqtypeOrderUnitParams) l_orderUnit.getDataSourceObject();

        // [�p�����[�^.�����P��.�����J�e�S�� == "��������"�̏ꍇ]
        if (l_eqtypeOrderUnitParams.order_categ.equals(OrderCategEnum.ASSET))
        {
            //[�p�����[�^.�����P��.������� ==
            //    ("����������" or "����������")�̏ꍇ]
            //    "��������"��ԋp����B
            if ((l_eqtypeOrderUnitParams.order_type.equals(OrderTypeEnum.
                            EQUITY_BUY))
             || (l_eqtypeOrderUnitParams.order_type.equals(OrderTypeEnum.
                            EQUITY_SELL)))
            {
                l_strWEB3ProductTypeDef = WEB3CommodityDivDef.EQUITY;
            }
        }
        
        //[�p�����[�^.�����P��.�����J�e�S�� ==
        //        ("�V�K������" or "�ԍϒ���" or "�������n����")�̏ꍇ]
        //        "�M�p���"��ԋp����B
        if ((l_eqtypeOrderUnitParams.order_categ.equals(OrderCategEnum.OPEN_MARGIN))
         || (l_eqtypeOrderUnitParams.order_categ.equals(OrderCategEnum.CLOSE_MARGIN))
         || (l_eqtypeOrderUnitParams.order_categ.equals(OrderCategEnum.SWAP_MARGIN)))
        {
            l_strWEB3ProductTypeDef = WEB3CommodityDivDef.MARGIN;
        }
        log.exiting(STR_METHOD_NAME);
        return l_strWEB3ProductTypeDef;
    }
    
    /**
     * (get�����󋵋敪)<BR>
     * �w�肳�ꂽ�����P�ʂ̏����󋵋敪��Ԃ��B<BR> 
     * <BR>
     * �߂�l�̏����󋵋敪�F<BR> 
     * ���R�[�h�l�̓��b�Z�[�W��`�t�H���_�ȉ���<BR> 
     * �uү���ޒ�`��_�M�p���(����).xls�v�̏����󋵋敪��`���Q�ƁB<BR> 
     * <BR>
     * (1)��t�敪�𔻒肷��B<BR> 
     * �@@this.get��t�敪()���R�[������B<BR> 
     *<BR> 
     * (2)this.get����ԋ敪(�p�����[�^.�����P��)���R�[�����A���敪���擾����B<BR> 
     *<BR> 
     * (3) �����敪�𔻒肷��B<BR> 
     * �@@�@@this.get������ԋ敪(�p�����[�^.�����P��)���R�[������B<BR> 
     * �@@�@@�@@�Ethis.get������ԋ敪()���h�ꕔ�����h�̏ꍇ�́A�h�ꕔ�����h�B<BR> 
     * �@@�@@�@@�Ethis.get������ԋ敪()���h�S�������h�̏ꍇ�́A�h�S�������h�B<BR> 
     * �@@�@@�@@�E��L�ȊO�̏ꍇ�́A�h�����Ȃ��h�B<BR> 
     *<BR> 
     * (4) (1)�Ŏ擾������t�敪=="��t�G���["�̏ꍇ�́A"��t�G���["�������敪�Ƃ��ĕԂ��B<BR> 
     * �@@�@@��L�ȊO�̏ꍇ�́A(1)�A(2)�A(3)�̎擾�l�A�y�ђ����P��.���������E����敪���A<BR> 
     * �@@�@@�Y�����鏈���󋵋敪��ԋp����B<BR> 
     * �@@�@@�g�ݍ��킹�\�ɋL�ڂ̂Ȃ��g�ݍ��킹�̏ꍇ���A���̕���������̂܂ܕԋp����B<BR> 
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return String
     */
    public static String getTransactionStatusType(EqTypeOrderUnit l_orderUnit)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTransactionStatusType(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        log.debug("�����Ώے���ID:[" + l_orderUnit.getOrderId() + "]");

        //��t�敪
        String l_strAcceptType = getAcceptType(l_orderUnit);
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //���敪
        String l_strExecType = getExecType(l_orderUnit);
        
        //�����敪
        String l_strExpirationType;        
        String l_strOrderStatusType = getOrderState(l_orderUnit);
        if (WEB3OrderStatusDef.PART_INAFFECTED.equals(l_strOrderStatusType))
        {
            l_strExpirationType = WEB3EquityExpirationStatusDef.EXPIRATION_TYPE_ONE_COMPLETE;
        }
        else if (WEB3OrderStatusDef.FULL_INAFFECTED.equals(l_strOrderStatusType))
        {
            l_strExpirationType = WEB3EquityExpirationStatusDef.EXPIRATION_TYPE_ALL_COMPLETE;
        }
        else
        {
            l_strExpirationType = WEB3EquityExpirationStatusDef.EXPIRATION_TYPE_NOT_PROMISE;
        }
        
        //�����P��.���������E����敪
        String l_strModifyCancelType = l_orderUnitRow.getModifyCancelType();
        
        //�����󋵋敪
        String l_strReturn = null;
        
        //��t�敪����t�G���[�̏ꍇ                         
        if (WEB3EquityAcceptTypeDef.EXEC_TYPE_ERROR.equals(l_strAcceptType))                           
        {                           
            l_strReturn = l_strAcceptType 
            + WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE 
            + WEB3EquityExpirationStatusDef.EXPIRATION_TYPE_NOT_PROMISE
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
     * ������J�����_�R���e�L�X�g��ݒ�̏�A�Ăяo�����ƁB<BR>
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
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@ �g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[get�s��()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.getMarketId()<BR>
     * <BR>
     *�@@�@@ �@@�@@�@@�@@�@@�@@�A ������ԊǗ�.get�s��ǎ���()���R�[������B<BR>
     *�@@�@@�@@ �@@�@@�@@�@@�@@�@@�@@�@@[get�s��ǎ���()�ɐݒ肷�����]<BR>
     *�@@�@@�@@�@@ �@@�@@�@@�@@�@@�@@�@@�s��R�[�h�F�@@get�s��()�̖߂�l.get�s��R�[�h()<BR>
     *�@@�@@�@@�@@�@@ �@@�@@�@@�@@�@@�@@���i�R�[�h�F�@@"0�FDEFAULT"<BR>
     * <BR>
     * �Q�j�@@�������̗L���ȋt�w�l�����Ŏs��J�ǎ��ԑт̏ꍇ<BR>
     * <BR>
     * �@@�����P��.����������"�t�w�l"�A���A<BR>
     * �@@�����P��.���N�G�X�g�^�C�v��"DEFAULT"�A���A<BR>
     * �@@�����P��.�����L����ԁ�"�I�[�v��"�A���A<BR>
     * �@@������ԊǗ�.is�s��J�ǎ��ԑс�true�̏ꍇ�A"�����҂�"��ԋp����B<BR>
     * <BR>
     * 3 �j�@@�����������̏ꍇ<BR>
     * <BR>
     * �@@�����P��.�s�ꂩ��m�F�ς̐��ʁ�NaN�̏ꍇ�A<BR>
     * �@@ - �����P��.������ԁ�"�������s(�V�K����)"�̏ꍇ�́A"��t�G���["�A<BR>
     * �@@ - �����P��.������ԁ�"�������s(�V�K����)"�̏ꍇ�́A"��t����"��ԋp����B<BR>
     * <BR>
     * 4 �j�@@��L�ȊO�̏ꍇ�́A"��t��"��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getAcceptType(EqTypeOrderUnit l_orderUnit)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAcceptType(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_strAcceptType = null;

        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        try
        {
            //������ԊǗ�.get������()
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();        
            Date l_datOrderUnitBizDate = WEB3DateUtility.getDate(
                                            l_orderUnitRow.getBizDate(), 
                                            WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                                            
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
                String l_strBizDateType = 
                    WEB3GentradeTradingTimeManagement.getBizDateType(l_tsSysTimestamp);
                
                //�����P��.������ �� �Ɩ����t�̏ꍇ
                if (WEB3DateUtility.compareToDay(l_datOrderUnitBizDate, l_datBizDate) > 0)
                {
                    l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_NEXT_ORDER;
                    return l_strAcceptType;
                }                
                //������ԊǗ�.get�c�Ɠ��敪(���ݓ���)��"��c�Ɠ�"�̏ꍇ    
                else if (l_strBizDateType.compareTo(WEB3BizDateTypeDef.NO_BIZ_DATE) == 0)
                {
                    l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_NEXT_ORDER;
                    return l_strAcceptType;
                }            
                //�����P��.������ �� �Ɩ����t�̏ꍇ
                else if (WEB3DateUtility.compareToDay(l_datOrderUnitBizDate, l_datBizDate) == 0)
                {
                    //������ԊǗ�.is�I�����C���T�[�r�X�J�n��()��false�̏ꍇ
                    if (!(WEB3GentradeTradingTimeManagement.isOnlineServiceStartAfter()))
                    {
                        l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_NEXT_ORDER;
                        return l_strAcceptType;
                    }
                    
                    //�s����擾����
                    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                    WEB3GentradeFinObjectManager l_finObjManager =
                        (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                    WEB3GentradeMarket l_market = null;                

                    l_market = 
                        (WEB3GentradeMarket)l_finObjManager.getMarket(
                                                l_orderUnitRow.getMarketId());
                    
                    //�s��ǎ��Ԃ��擾����
                    String l_strTradeCloseTime = 
                        WEB3GentradeTradingTimeManagement.getTradeCloseTime(
                            l_market.getMarketCode(), 
                            WEB3MarketCodeDef.DEFAULT);
                    
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
                        l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_UNORDER;
                        return l_strAcceptType;                      
                    }                
                }
            }
    
            //�������̗L���ȋt�w�l�����Ŏs��J�ǎ��ԑт̏ꍇ        
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()) &&
                WEB3RequestTypeDef.DEFAULT.equals(l_orderUnitRow.getRequestType()) &&
                OrderOpenStatusEnum.OPEN.equals(l_orderUnitRow.getOrderOpenStatus()) &&
                WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
            {
                l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_WAITING;
            }

            //�����������̏ꍇ
            else if (l_orderUnitRow.getConfirmedQuantityIsNull())
            {
                if (OrderStatusEnum.NOT_ORDERED.equals(l_orderUnitRow.getOrderStatus()))
                {
                    l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_ERROR;
                }
                else
                {
                    l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_NAN;
                }
            }
            else
            {
                l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_NOT_NAN;
            }
            
            return l_strAcceptType;
            
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3EquityDataAdapter.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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
     * ���Q�l�܂łɁA���������̍��ڂ�PR�w�ɕԂ��u�������e�敪�v�Ƃ̑Ή��́A<BR>
     * �u�M�p����E�����f�[�^�X�V�d�l.xls�v�́u��������o�^�d�l�v�V�[�g���Q�ƁB<BR>
     * <BR>
     * �߂�l�̒������e�敪�F<BR>
     * 00�F�V�K���� 01�F������t 02�F�V�K����(���s) 03�F��������<BR>
     * 04�F������t 05�F�������� 06�F��������(���s) 07�F�������<BR>
     * 08�F�����t 09�F������� 10�F�������(���s) 11�F�ꕔ���<BR>
     * 12�F�S����� 13�F����� 14�F���� 15�F������� 16�F����<BR>
     * 17�F�����J�z 18�F�����J�z(���s) 19�F�����(�������n)<BR>
     * 20�F������ 21�F�����x��<BR>
     * 22�F�ؑ֒x�� 23�F�ؑ֒��� 24�F�ؑ֎�t 25�F�ؑ֊���<BR>
     * 26�F�ؑ֒���(���s) 27�F�X�g�b�v�������� 29�F�������� 30�F���F�� 99�F���̑�<BR>
     * --------------------------------------------------------------------------<BR>
     * (1)���<BR>
     * �@@��������.�����C�x���g�^�C�v��EXECUTE(���)�̏ꍇ�A<BR>
     * �@@�E��������.�������ʁ���������.��萔�ʂȂ�΁A"�S�����"��Ԃ��B<BR>
     * �@@�E��L�ȊO�Ȃ�΁A"�ꕔ���"��Ԃ��B<BR>
     * <BR>
     * (2)��������<BR>
     * �@@�����P��.���������敪 == "����������"����<BR>
     * �@@��������.�����C�x���g�^�C�v��EXPIRE(�����ς�) ����<BR>
     * �@@��������.���������X�e�[�^�X��INVALIDATED_BY_MKT(�}�[�P�b�g����)�̏ꍇ�A<BR>
     * "��������"��Ԃ��B<BR>
     * <BR>
     * (3)����<BR>
     * �@@��������.�����C�x���g�^�C�v��EXPIRE(�����ς�) ����<BR>
     * �@@��������.���������X�e�[�^�X��INVALIDATED_BY_MKT(�}�[�P�b�g����)�̏ꍇ�A<BR>
     * "����"��Ԃ��B<BR>
     * <BR>
     * (4)�����A�����J�z(���s)<BR>
     * �@@��������.�����C�x���g�^�C�v��EXPIRE(�����ς�) ����<BR>
     * �@@��������.���������X�e�[�^�X��EXPIRED(�I��)�̏ꍇ�A<BR>
     * �@@�E��������.�����G���[���R�R�[�h��"0000�F����"�Ȃ�΁A"�����J�z(���s)"��Ԃ��B<BR>
     * �@@�E��L�ȊO�ł���΁A"����"��Ԃ��B<BR>
     * <BR>
     * (5)�X�g�b�v��������<BR>
     * �@@��������.�����C�x���g�^�C�v��EXPIRE(�����ς�) ����<BR>
     * �@@��������.���N�G�X�g�^�C�v��"����"�̏ꍇ�A"�X�g�b�v��������"��Ԃ��B<BR>
     * <BR>
     * (6)�������<BR>
     * �@@��������.�����C�x���g�^�C�v��UNDO_INVALIDATION_BY_MKT(�������)�̏ꍇ�A<BR>
     * "�������"��Ԃ��B<BR>
     * <BR>
     * (7)������A�����(�������n)<BR>
     * �@@��������.�����C�x���g�^�C�v��UNDO_EXECUTION(�����)�̏ꍇ�A<BR>
     * �@@�E�����P��.�����J�e�S����SWAP_MARGIN(�������n����)�Ȃ�΁A"�����(�������n)"��Ԃ��B<BR>
     * �@@�E��L�ȊO�Ȃ�΁A"�����"��Ԃ��B<BR>
     * <BR>
     * (8)�����x��<BR>
     * �@@��������.�����C�x���g�^�C�v��ORDER_DELAY�i�����x���j�̏ꍇ�A<BR>
     * �@@"�����x��"��Ԃ��B<BR>
     * <BR>
     * (9)�ؑ֒x��<BR>
     * �@@��������.�����C�x���g�^�C�v��SWITCH_DELAY�i�ؑ֒x���j�̏ꍇ�A<BR>
     * �@@"�ؑ֒x��"��Ԃ��B<BR>
     * <BR>
     * (10)���F��<BR>
     * �@@��������.�����C�x���g�^�C�v��APPROVED�i���F�ρj�̏ꍇ�A<BR>
     * �@@"���F��"��Ԃ��B<BR>
     * <BR>
     * (11)�V�K�����A�����J�z<BR>
     * �@@��������.������ԁ�ACCEPTED(��t��(�V�K����))�̏ꍇ�A<BR>
     * �@@�E�g�����������}�l�[�W��.is�J�z�����P��(�����P��.�����P��ID) == false�i�����񒍕��j�Ȃ�΁A<BR>
     * �@@�@@"�V�K����"��Ԃ��B<BR>
     * �@@�E��L�ȊO�Ȃ�΁A"�����J�z"��Ԃ��B<BR>
     * <BR>
     * (12)������t<BR>
     * �@@��������.������ԁ�ORDERED(�����ς݁i�V�K����))�̏ꍇ�A"������t"��Ԃ��B<BR>
     * <BR>
     * (13)�V�K����(���s)<BR>
     * �@@��������.������ԁ�NOT_ORDERED(�������s(�V�K����))�̏ꍇ�A<BR>
     * �@@�@@"�V�K����(���s)"��Ԃ��B<BR>
     * <BR>
     * (14)���������A�ؑ֒���<BR>
     * �@@��������.������ԁ�MODIFY_ACCEPTED(��t��(�ύX����))�̏ꍇ�A<BR>
     * �@@�@@�E��������.�����E����敪��"W�w�l�����ؑ֒�"�̏ꍇ�A"�ؑ֒���"��Ԃ��B<BR>
     * �@@�@@�E��L�ȊO�̏ꍇ�A"��������"��Ԃ��B<BR>
     * <BR>
     * (15)������t�A�ؑ֎�t<BR>
     * �@@��������.������ԁ�MODIFYING(������(�ύX����))�̏ꍇ�A<BR>
     * �@@�@@�E��������.�����E����敪��"W�w�l�����ؑ֒�"�̏ꍇ�A"�ؑ֎�t"��Ԃ��B<BR>
     * �@@�@@�E��L�ȊO�̏ꍇ�A"������t"��Ԃ��B<BR>
     * <BR>
     * (16)���������A�ؑ֊���<BR>
     * �@@��������.������ԁ�MODIFIED(�����ς�(�ύX����))�̏ꍇ�A<BR>
     * �@@�@@�E��������.�����E����敪���i"W�w�l�����ꕔ�ؑ֊���"�܂���"W�w�l�����S���ؑ֊���"�j�̏ꍇ�A<BR>
     * �@@�@@�@@"�ؑ֊���"��Ԃ��B<BR>
     * �@@�@@�E��L�ȊO�̏ꍇ�A"��������"��Ԃ��B<BR>
     * <BR>
     * (17)��������(���s)�A�ؑ֒���(���s)<BR>
     * �@@��������.������ԁ�NOT_MODIFIED(�������s(�ύX����))�̏ꍇ�A<BR>
     * �@@�@@�E��������.�����E����敪��"W�w�l�����ؑ֎��s"�̏ꍇ�A"�ؑ֒���(���s)"��Ԃ��B<BR>
     * �@@�@@�E��L�ȊO�̏ꍇ�A"��������(���s)"��Ԃ��B<BR>
     * <BR>
     * (18)�������<BR>
     * �@@��������.������ԁ�CANCEL_ACCEPTED(��t��(�������))�̏ꍇ�A"�������"��Ԃ��B<BR>
     * <BR>
     * (19)�����t<BR>
     * �@@��������.������ԁ�CANCELLING(������(�������))�̏ꍇ�A"�����t"��Ԃ��B<BR>
     * <BR>
     * (20)�������<BR>
     * �@@��������.������ԁ�CANCELLED(�����ς�(�������))�̏ꍇ�A"�������"��Ԃ��B<BR>
     * <BR>
     * (21)�������(���s)<BR>
     * �@@��������.������ԁ�NOT_CANCELLED(�������s(�������))�̏ꍇ�A"�������(���s)"��Ԃ��B<BR>
     * <BR>
     * (22)������<BR>
     * �@@��������.�����C�x���g�^�C�v��SEND_TO_MKT�i�}�[�P�b�g���M�ρi�V�K�����j�j�̏ꍇ�A<BR>
     * �@@"������"��Ԃ��B<BR>
     * <BR>
     * (23)��L�ȊO�̏ꍇ�A"���̑�"��Ԃ��B <BR>
     * --------------------------------------------------------------------------<BR>
     * <BR>
     * @@param l_orderAction - (��������)<BR>
     * ���������I�u�W�F�N�g�B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return java.lang.String
     * @@throws WEB3BaseException
     */
    public static String getOrderType(
        EqTypeOrderAction l_orderAction,
        EqTypeOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOrderType(EqTypeOrderAction, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_orderSpecType = null;
        // �����P��.���������敪
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strForcedExpireType = l_orderUnitRow.getForcedExpireType();
        OrderEventTypeEnum l_orderEventType = l_orderAction.getOrderEventType();
        OrderStatusEnum l_orderStatus = l_orderAction.getOrderStatus();
        OrderExpirationStatusEnum l_orderExpirationStatus = l_orderAction.getExpirationStatus();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqtypeOrderActionRow l_orderActionRow =
            (EqtypeOrderActionRow)l_orderAction.getDataSourceObject();
        //��������.�����E����敪
        String l_strModifyCancelType = l_orderActionRow.getModifyCancelType();
        
        if (OrderEventTypeEnum.EXECUTE.equals(l_orderEventType))
        {
            if (l_orderAction.getQuantity() == l_orderAction.getExecutionQuantity())
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.FULL_EXECUTE;
            }
            else if (l_orderAction.getExecutionQuantity() != 0.0D)
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.PART_EXECUTE;
            }
        }
        // (2)��������
        // �����P��.���������敪 == "����������"����
        // ��������.�����C�x���g�^�C�v��EXPIRE(�����ς�) ����
        // ��������.���������X�e�[�^�X��INVALIDATED_BY_MKT(�}�[�P�b�g����)�̏ꍇ
        else if (WEB3ForcedExpireType.FORCED_EXPIRED.equals(l_strForcedExpireType) &&
            OrderEventTypeEnum.EXPIRE.equals(l_orderEventType) &&
            OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderExpirationStatus))
        {
            // ��������
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.FORCED_EXPIRE;
        }
        else if (OrderEventTypeEnum.EXPIRE.equals(l_orderEventType) &&
                  OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderExpirationStatus))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.EXPIRE;
        }
        else if (OrderEventTypeEnum.EXPIRE.equals(l_orderEventType) &&
                  OrderExpirationStatusEnum.EXPIRED.equals(l_orderExpirationStatus))
        {
            if (!WEB3ErrorReasonCodeDef.NORMAL.equals(l_orderActionRow.getErrorReasonCode()))
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.ORDER_CARRY_OVER_REJECT;
            }
            else
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.INVALID;
            }
        }
        //(4)�X�g�b�v��������
        //��������.�����C�x���g�^�C�v��EXPIRE(�����ς�) ����
        //��������.���N�G�X�g�^�C�v��"����"�̏ꍇ�A"�X�g�b�v��������"��Ԃ��B
        else if (OrderEventTypeEnum.EXPIRE.equals(l_orderEventType) &&
            WEB3RequestTypeDef.INVALIDATE.equals(l_orderActionRow.getRequestType()))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.STOP_ORDER_EXPIRE;
        }
        else if (OrderEventTypeEnum.UNDO_INVALIDATION_BY_MKT.equals(l_orderEventType))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.UNDO_EXPIRE;
        }
		else if (OrderEventTypeEnum.UNDO_EXECUTION.equals(l_orderEventType))
		{
			if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()))
			{
				l_orderSpecType = WEB3EquityOrderSpecTypeDef.UNDO_EXECUTE_SWAP;
			}
			else
			{
				l_orderSpecType = WEB3EquityOrderSpecTypeDef.UNDO_EXECUTE;
			}
		}
        else if (OrderEventTypeEnum.ORDER_DELAY.equals(l_orderEventType))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.ORDER_DELAY;
        }
        //(8)�ؑ֒x��
        //��������.�����C�x���g�^�C�v��SWITCH_DELAY�i�ؑ֒x���j�̏ꍇ�A
        //"�ؑ֒x��"��Ԃ��B
        else if (OrderEventTypeEnum.SWITCH_DELAY.equals(l_orderEventType))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.SWITCH_DELAY;
        }
        // (10)���F��
        // ��������.�����C�x���g�^�C�v��APPROVED�i���F�ρj�̏ꍇ�A
        // "���F��"��Ԃ��B
        else if (OrderEventTypeEnum.APPROVED.equals(l_orderEventType))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.APPROVED;
        }
        else if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus))
        {
            boolean isCarriyOverOrderUnit =
                l_orderManager.isCarryOverOrderUnit(l_orderUnit);
            if (!isCarriyOverOrderUnit)
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.NEW_ORDER;
            }
            else
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.ORDER_CARRY_OVER;
            }
        }
        else if (OrderStatusEnum.ORDERED.equals(l_orderStatus))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.NEW_ORDER_ACCEPT;
        }
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderStatus))
        {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.NEW_ORDER_REJECT;
        }
        //(12)���������A�ؑ֒���
        //��������.������ԁ�MODIFY_ACCEPTED(��t��(�ύX����))�̏ꍇ�A
        //�E��������.�����E����敪��"W�w�l�����ؑ֒�"�̏ꍇ�A"�ؑ֒���"��Ԃ��B
        //�E��L�ȊO�̏ꍇ�A"��������"��Ԃ��B
        else if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(l_strModifyCancelType))
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.SWITCH_ORDER;
            }
            else
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.CHANGE;
            }
        }
        //(13)������t�A�ؑ֎�t
        //��������.������ԁ�MODIFYING(������(�ύX����))�̏ꍇ�A
        //�E��������.�����E����敪��"W�w�l�����ؑ֒�"�̏ꍇ�A"�ؑ֎�t"��Ԃ��B
        //�E��L�ȊO�̏ꍇ�A"������t"��Ԃ��B
        else if (OrderStatusEnum.MODIFYING.equals(l_orderStatus))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(l_strModifyCancelType))
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.SWITCH_ACCEPT;
            }
            else
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.CHANGE_ACCEPT;
            }
        }
        //(14)���������A�ؑ֊���
        //��������.������ԁ�MODIFIED(�����ς�(�ύX����))�̏ꍇ�A
        //�E��������.�����E����敪���i"W�w�l�����ꕔ�ؑ֊���"�܂���"W�w�l�����S���ؑ֊���"�j�̏ꍇ�A
        //"�ؑ֊���"��Ԃ��B
        //�E��L�ȊO�̏ꍇ�A"��������"��Ԃ��B
        else if (OrderStatusEnum.MODIFIED.equals(l_orderStatus))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_PARTIALLY_TRANSFERED.equals(l_strModifyCancelType)
                || WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERED.equals(l_strModifyCancelType))
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.SWITCH_OVER;
            }
            else
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.CHANGE_COMPLETE;
            }
        }
        //(15)��������(���s)�A�ؑ֒���(���s)
        //��������.������ԁ�NOT_MODIFIED(�������s(�ύX����))�̏ꍇ�A
        //�E��������.�����E����敪��"W�w�l�����ؑ֎��s"�̏ꍇ�A"�ؑ֒���(���s)"��Ԃ��B
        //�E��L�ȊO�̏ꍇ�A"��������(���s)"��Ԃ��B
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_orderStatus))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR.equals(l_strModifyCancelType))
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.SWITCH_ORDER_FAIL;
            }
            else
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.CHANGE_REJECT;
            }
        }
        else if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.CANCEL;
        }
        else if (OrderStatusEnum.CANCELLING.equals(l_orderStatus))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.CANCEL_ACCEPT;
        }
        else if (OrderStatusEnum.CANCELLED.equals(l_orderStatus))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.CANCEL_COMPLETE;
        }
        else if (OrderStatusEnum.NOT_CANCELLED.equals(l_orderStatus))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.CANCEL_REJECT;
        }
        else if (OrderEventTypeEnum.SEND_TO_MKT.equals(l_orderEventType))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.NEW_ORDER_ORDERING;
        }
        else
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.OTHER;
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderSpecType;
    }

    /**
     * (get��t���ʋ敪)<BR>
     * �w�肳�ꂽ���������̎�t���ʋ敪��Ԃ��B<BR>
     * <BR>
     * ���������̍��ڂƁAPR�w�ɕԂ��u��t���ʋ敪�v�Ƃ̑Ή��́A<BR>
     * �u���������E�����f�[�^�X�V�d�l.xls�v�́u��������o�^�d�l�v�V�[�g���Q�ƁB<BR>
     * <BR>
     * �߂�l�̎�t���ʋ敪�F<BR>
     * 0000�F���� 1001�F��t�G���[ 1002�F�����G���[ 1003�F����G���[ 1004�F�ؑփG���[<BR>
     * 0001�F�l���G���[ 0002�F�a����s���G���[ 0003�F�����c���s���G���[ 0004�F�ۏ؋��s���G���[<BR>
     * 0005�F�����c���s���G���[ 0006�F������~�����G���[ 0007�F�s��ύX�����G���[<BR>
     * 0008�F���t�]�̓G���[ 0009�F���t�\���ʃG���[ 0010�F��������G���[<BR>
     * 0011�F�����J�z�X�L�b�v�����G���[<BR>
     * 9001�F���̑��G���[<BR>
     * <BR>
     * --------------------------------------------------------------------------<BR>
     * �E��������.���������E����敪��"4"�i������s�j�̏ꍇ<BR>
     * <BR>
     * �@@"1003"�i����G���[�j��Ԃ��B<BR>
     * <BR>
     * �E��������.���������E����敪��"8"�i�������s�j�̏ꍇ<BR>
     * <BR>
     * �@@"1002"�i�����G���[�j��Ԃ��B<BR>
     * <BR>
     * �E��������.���������E����敪��"D"�iW�w�l�����ؑ֎��s�j�̏ꍇ<BR>
     * <BR>
     * �@@"1004"�i�ؑփG���[�j��Ԃ��B<BR>
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
     * ���������I�u�W�F�N�g�B<BR>
     * @@return java.lang.String
     */
    public static String getAcceptedResultDiv(EqTypeOrderAction l_orderAction)
    {
        final String STR_METHOD_NAME = "getAcceptedResultDiv(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderActionRow l_orderActionRow =
            (EqtypeOrderActionRow)l_orderAction.getDataSourceObject();
        String l_result = null;
        
        // �E��������.���������E����敪��"4"�i������s�j�̏ꍇ
        if (WEB3ModifyCancelTypeDef.CANCEL_ERROR.equals(l_orderActionRow.getModifyCancelType()))
        {
            l_result = WEB3ErrorReasonCodeDef.CANCEL_ERROR;
        }
        // �E��������.���������E����敪��"8"�i�������s�j�̏ꍇ
        else if (WEB3ModifyCancelTypeDef.CHANGE_ERROR.equals(l_orderActionRow.getModifyCancelType()))
        {
            l_result = WEB3ErrorReasonCodeDef.CHANGE_ERROR;
        }
        //�E��������.���������E����敪��"D"�iW�w�l�����ؑ֎��s�j�̏ꍇ
        //"1004"�i�ؑփG���[�j��Ԃ��B
        else if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR.equals(l_orderActionRow.getModifyCancelType()))
        {
            l_result = WEB3ErrorReasonCodeDef.TRANSFER_ERROR;
        }
        // �E��������.������ԁ�NOT_ORDERED�i�������s�i�V�K�����j�j
        // �@@���@@��������.�����C�x���g�^�C�v��REJECTED_BY_MARKET�i�}�[�P�b�g���ہi�V�K�����j�j
        // �@@�̏ꍇ
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderAction.getOrderStatus()) &&
                  OrderEventTypeEnum.REJECTED_BY_MKT.equals(l_orderAction.getOrderEventType()))
        {
            l_result = WEB3ErrorReasonCodeDef.ACCEPT_ERROR;
        }
        // �E��L�ȊO�̏ꍇ
        else
        {
            l_result = l_orderActionRow.getErrorReasonCode();
        }

        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * �iget�����敪�j<BR>
     * <BR>
     * �����̐ŋ敪���PR�w�Ŏg�p��������敪��ԋp����B<BR>  
     * <BR>
     * TaxTypeEnum.NORMAL == �p�����[�^.�ŋ敪�̏ꍇ�A<BR> 
     * �@@WEB3TaxTypeDef.NORMAL�i"��ʌ���"�j��Ԃ��B<BR> 
     * <BR>
     * TaxTypeEnum.STOCK_OPTION == �p�����[�^.�ŋ敪�̏ꍇ�A<BR>
     * �@@WEB3TaxTypeDef.STOCK_OPTION�i"�X�g�b�N�I�v�V��������"�j��Ԃ��B<BR>
     * <BR>
     * TaxTypeEnum.SPECIAL == �p�����[�^.�ŋ敪 <BR> 
     * �@@�܂��́ATaxTypeEnum.SPECIAL_WITHHOLD == �p�����[�^.�ŋ敪�̏ꍇ�A<BR> 
     * �@@�@@WEB3TaxTypeDef.SPECIAL�i"�������"�j��Ԃ��B <BR>
     * <BR>
     * @@param l_taxTypeEnum - (�ŋ敪)<BR>
     * �ŋ敪<BR>
     * @@return String
     */
    public static String getTaxType(TaxTypeEnum l_taxTypeEnum)
    {
        final String STR_METHOD_NAME = " getTaxType(TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        String l_strTaxType = null;
    	if(TaxTypeEnum.NORMAL.equals(l_taxTypeEnum))
    	{
    	    l_strTaxType = WEB3TaxTypeDef.NORMAL;
    	}
    	else if(TaxTypeEnum.STOCK_OPTION.equals(l_taxTypeEnum))
    	{
          l_strTaxType = WEB3TaxTypeDef.STOCK_OPTION;
    	}
    	else if(TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum) ||
            TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeEnum))
    	{    		
    	    l_strTaxType = WEB3TaxTypeDef.SPECIAL;
    	}
    	
    	log.exiting(STR_METHOD_NAME);
    	return l_strTaxType;
    }
    
    /**
     * (get��������)<BR>
     * �����̔��������A�����������̓��A<BR> 
     * null�łȂ����̔���������ԋp����B<BR> 
     * <BR>
     * [�p�����[�^.���������� != null�̏ꍇ] <BR>
     * �@@�p�����[�^.������������ԋp����B<BR> 
     * <BR>
     * [�p�����[�^.�������� != null�̏ꍇ] <BR>
     * �@@�p�����[�^.����������ԋp����B<BR>
     * @@param l_strOrderConditionType - (��������)<BR>
     * ��������<BR>
     * @@param l_strOrgOrderConditionType - (����������)<BR>
     * ����������<BR>
     * @@return String
     * 
     */
    public static String getOrderConditionType(
        String l_strOrderConditionType, String l_strOrgOrderConditionType)
    {
        final String STR_METHOD_NAME = "getOrderConditionType(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^.���������� != null�̏ꍇ
        if (l_strOrgOrderConditionType != null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_strOrgOrderConditionType;
        }
        
        //�p�����[�^.�������� != null�̏ꍇ
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
     * �����̔����������Z�q�A�������������Z�q�̓��A <BR>
     * �L���Ȕ����������Z�q��ԋp����B <BR>
     * <BR>
     * [�p�����[�^.�������������Z�q != null�̏ꍇ] <BR>
     * �@@�p�����[�^.�������������Z�q��ԋp����B <BR>
     * <BR>
     * [�p�����[�^.�����������Z�q != null�̏ꍇ] <BR>
     * �@@�p�����[�^.�����������Z�q��ԋp����B<BR>
     * @@param l_strOrderCondOperator - (�����������Z�q)<BR>
     * �����������Z�q<BR>
     * @@param l_strOrgOrderCondOperator - (�������������Z�q)<BR>
     * �������������Z�q<BR>
     * @@return String
     */
    public static String getOrderCondOperator(
        String l_strOrderCondOperator, String l_strOrgOrderCondOperator)
    {
        final String STR_METHOD_NAME = "getOrderCondOperator(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^.�������������Z�q != null�̏ꍇ
        if (l_strOrgOrderCondOperator != null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_strOrgOrderCondOperator;
        }
        
        //�p�����[�^.�����������Z�q != null�̏ꍇ
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
     * �P�j�@@���������w��Ȃ��̏ꍇ <BR>
     * <BR>
     * this.get��������(�j��"DEFAULT�i�����w��Ȃ��j"�̏ꍇ�A<BR> 
     * null��ԋp����B <BR>
     * <BR>
     * [get��������()�Ɏw�肷�����] <BR>
     * �@@���������F�@@�����P��.�������� <BR>
     * �@@�����������F�@@�����P��.���������� <BR>
     * <BR>
     * �Q�j�@@�ȊO�̏ꍇ <BR>
     * <BR>
     * this.get�����������Z�q()�ɏ������Ϗ�����B<BR> 
     * <BR>
     * [get�����������Z�q()�Ɏw�肷�����] <BR>
     * �@@�����������Z�q�F�@@�����P��.�����������Z�q <BR>
     * �@@�������������Z�q�F�@@�����P��.�������������Z�q<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrderCondOperator(EqTypeOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderCondOperator(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //�P�j�@@���������w��Ȃ��̏ꍇ
        EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        String l_strOrderConditionType = 
            getOrderConditionType(
                l_row.getOrderConditionType(), 
                l_row.getOrgOrderConditionType());
        
        //this.get��������(�j��"DEFAULT�i�����w��Ȃ��j"�̏ꍇ
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�Q�j�@@�ȊO�̏ꍇ
        String l_strOrderCondOperator = 
            getOrderCondOperator(
                l_row.getOrderCondOperator(), 
                l_row.getOrgOrderCondOperator());
        
        log.exiting(STR_METHOD_NAME);
        return l_strOrderCondOperator;
    }
    
    /**
     * (get�t�w�l��l)<BR>
     * �����̋t�w�l��l�A���t�w�l��l�̓��A<BR> 
     * �L���ȋt�w�l��l��ԋp����B <BR>
     * <BR>
     * [�p�����[�^.���t�w�l��l != null�̏ꍇ]<BR> 
     * �@@�p�����[�^.���t�w�l��l��ԋp����B<BR> 
     * <BR>
     * [�p�����[�^.�t�w�l��l != null�̏ꍇ] <BR>
     * �@@�p�����[�^.�t�w�l��l��ԋp����B<BR>
     * @@param l_strStopOrderPrice - (�t�w�l��l)<BR>
     * �t�w�l��l<BR>
     * @@param l_strOrgStopOrderPrice - (���t�w�l��l)<BR>
     * ���t�w�l��l<BR> 
     * @@return String
     */
    public static String getStopOrderPrice(
        String l_strStopOrderPrice, String l_strOrgStopOrderPrice)
    {
        final String STR_METHOD_NAME = "getStopOrderPrice(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�p�����[�^.���t�w�l��l != null�̏ꍇ
        if (l_strOrgStopOrderPrice != null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_strOrgStopOrderPrice;
        }
        
        //�p�����[�^.�t�w�l��l != null�̏ꍇ
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
     * �����̒����P�ʂ��A�L���ȋt�w�l��l��ԋp����B <BR>
     * <BR>
     * �P�j�@@���������w��Ȃ��̏ꍇ <BR>
     * <BR>
     * this.get��������(�j��"DEFAULT�i�����w��Ȃ��j"�̏ꍇ�A <BR>
     * null��ԋp����B <BR>
     * <BR>
     * [get��������()�Ɏw�肷�����] <BR>
     * �@@���������F�@@�����P��.�������� <BR>
     * �@@�����������F�@@�����P��.���������� <BR>
     * <BR>
     * �Q�j�@@�ȊO�̏ꍇ <BR>
     * <BR>
     * this.get�t�w�l��l()�ɏ������Ϗ�����B <BR>
     * <BR>
     * [get�t�w�l��l()�Ɏw�肷�����] <BR>
     * �@@�t�w�l��l�F�@@�����P��.�t�w�l��l <BR>
     * �@@���t�w�l��l�F�@@�����P��.���t�w�l��l<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getStopOrderPrice(EqTypeOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderCondOperator(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //�P�j�@@���������w��Ȃ��̏ꍇ
        EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        String l_strOrderConditionType = 
            getOrderConditionType(
                l_row.getOrderConditionType(), 
                l_row.getOrgOrderConditionType());
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�Q�j�@@�ȊO�̏ꍇ
        String l_strStopOrderPrice = null;
        String l_strOrgStopOrderPrice = null;
        
        if (!l_row.getStopOrderPriceIsNull())
        {
            l_strStopOrderPrice = WEB3StringTypeUtility.formatNumber(l_row.getStopOrderPrice());
        }
        
        if (!l_row.getOrgStopOrderPriceIsNull())
        {
            l_strOrgStopOrderPrice = WEB3StringTypeUtility.formatNumber(l_row.getOrgStopOrderPrice());
        }
        
        String l_strRetStopOrderPrice = 
            getStopOrderPrice(l_strStopOrderPrice, l_strOrgStopOrderPrice);
        
        log.exiting(STR_METHOD_NAME);
        return l_strRetStopOrderPrice;
    }
    
    /**
     * (getAP�����敪)<BR>
     * <BR>
     * �����̐ŋ敪���AP�w�Ŏg�p��������敪��ԋp����B<BR>
     * <BR>
     * WEB3TaxTypeDef.NORMAL == �p�����[�^.�ŋ敪�̏ꍇ�A<BR>
     * �@@TaxTypeEnum.NORMAL�i"��ʌ���"�j��Ԃ��B<BR>
     * <BR>
     * WEB3TaxTypeDef.STOCK_OPTION == �p�����[�^.�ŋ敪�̏ꍇ�A<BR>
     * �@@TaxTypeEnum.STOCK_OPTION�i"�X�g�b�N�I�v�V��������"�j��Ԃ��B <BR>
     * <BR>
     * WEB3TaxTypeDef.SPECIAL == �p�����[�^.�ŋ敪�̏ꍇ�A<BR>
     * �@@TaxTypeEnum.SPECIAL�i"�������"�j��Ԃ��B<BR>
     * <BR>
     * @@param l_strTaxType - (�ŋ敪)<BR>
     * �ŋ敪<BR>
     * @@return String
     */
    public static String getApTaxType(String l_strTaxType)
    {
        final String STR_METHOD_NAME = " getApTaxType(String)";
        log.entering(STR_METHOD_NAME);
        
        String l_strTaxTypeTemp = null;
        if(WEB3TaxTypeDef.NORMAL.equals(l_strTaxType))
        {
            l_strTaxTypeTemp = TaxTypeEnum.NORMAL.intValue() + "";
        }
        else if(WEB3TaxTypeDef.STOCK_OPTION.equals(l_strTaxType))
        {
            l_strTaxTypeTemp = TaxTypeEnum.STOCK_OPTION.intValue() + "";
        }
        else if(WEB3TaxTypeDef.SPECIAL.equals(l_strTaxType))
        {
            l_strTaxTypeTemp = TaxTypeEnum.SPECIAL.intValue() + "";
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strTaxTypeTemp;
    }
    
    /**
     * (get�v�w�l�p�L����ԋ敪)<BR>
     * �����̒����P�ʂ��v�w�l�p�L����ԋ敪��ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.��������<BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.����������<BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ]<BR>
     * �@@�@@"�X�g�b�v�����L��"��ԋp����B<BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ]  <BR>
     * �@@�@@"�X�g�b�v����������"��ԋp����B<BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@"���~�b�g�����L��"��ԋp����B<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitEnableStatusDiv(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitEnableStatusDiv(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        // null��ԋp����B
        //[get��������()�Ɏw�肷�����]
        // ���������F�@@�p�����[�^.�����P��.��������
        // �����������F�@@�p�����[�^.�����P��.����������
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strCondetionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B
        // [is�X�g�b�v�����L��()�Ɏw�肷�����]
        //  �����P�ʁF�@@�p�����[�^.�����P��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B
        // [is�X�g�b�v����������()�Ɏw�肷�����]
        // �����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnIsStopOrderExpired =
            l_orderManager.isStopOrderExpired(l_eqTypeOrderUnit);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B
        //�@@[�Q�j�̖߂�l == true�̏ꍇ]
        // "�X�g�b�v�����L��"��ԋp����B
        if (l_blnIsStopOrderValid)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE;
        }

        //[�R�j�̖߂�l == true�̏ꍇ]
        // "�X�g�b�v����������"��ԋp����B
        if (l_blnIsStopOrderExpired)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3EquityWlimitEnableStatusDivDef.STOP_UN_ENABLE;
        }

        //[��L�ȊO�̏ꍇ]
        // "���~�b�g�����L��"��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE;
    }

    /**
     * (get�v�w�l�p�L����ԋ敪)<BR>
     * �����̒����������v�w�l�p�L����ԋ敪��ԋp����B  <BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.��������.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.���������� <BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.��������  <BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.��������  <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ]  <BR>
     * �@@�@@"�X�g�b�v�����L��"��ԋp����B<BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ]  <BR>
     * �@@�@@"�X�g�b�v����������"��ԋp����B<BR>
     * �@@[��L�ȊO�̏ꍇ]  <BR>
     * �@@�@@"���~�b�g�����L��"��ԋp����B<BR>
     * @@param l_eqTypeOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitEnableStatusDiv(EqTypeOrderAction l_eqTypeOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitEnableStatusDiv(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        // null��ԋp����B
        //[get��������()�Ɏw�肷�����]
        // ���������F�@@�p�����[�^.��������.��������
        // �����������F�@@�p�����[�^.��������.����������
        EqtypeOrderActionRow l_orderActionRow =
            (EqtypeOrderActionRow)l_eqTypeOrderAction.getDataSourceObject();
        String l_strConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderActionRow.getOrderConditionType(),
                l_orderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B
        //[is�X�g�b�v�����L��()�Ɏw�肷�����]
        // ���������F�@@�p�����[�^.��������
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderAction);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B
        // [is�X�g�b�v����������()�Ɏw�肷�����]
        // ���������F�@@�p�����[�^.��������
        boolean l_blnIsStopOrderExpired =
            l_orderManager.isStopOrderExpired(l_eqTypeOrderAction);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B
        //[�Q�j�̖߂�l == true�̏ꍇ]
        //�@@"�X�g�b�v�����L��"��ԋp����B
        if (l_blnIsStopOrderValid)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE;
        }
        //[�R�j�̖߂�l == true�̏ꍇ]
        //�@@"�X�g�b�v����������"��ԋp����B
        if (l_blnIsStopOrderExpired)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3EquityWlimitEnableStatusDivDef.STOP_UN_ENABLE;
        }
        //[��L�ȊO�̏ꍇ]
        //�@@"���~�b�g�����L��"��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE;
    }

    /**
     * (get�v�w�l�p�֑ؑO�����P��)<BR>
     * �����̒����P�ʂ��v�w�l�p�֑ؑO�����P����ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B  <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.��������  <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.���������� <BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]  <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��  <BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����]  <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��  <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B <BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ]  <BR>
     * �@@�@@�p�����[�^.�����P��.�iW�w�l�j�֑ؑO�w�l��ԋp����B<BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ]  <BR>
     * �@@�@@�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l��ԋp����B <BR>
     * �@@[��L�ȊO]  <BR>
     * �@@�@@�p�����[�^.�����P��.�w�l��ԋp����B<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitBefSwitchPrice(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitBefSwitchPrice(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        //null��ԋp����B
        //[get��������()�Ɏw�肷�����]
        //�@@���������F�@@�p�����[�^.�����P��.��������
        //�@@�����������F�@@�p�����[�^.�����P��.����������
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strCondetionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B
        // [is�X�g�b�v�����L��()�Ɏw�肷�����]
        //�@@�����P�ʁF�@@�p�����[�^.�����P��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B
        //[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����]
        //�@@�����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnIsStopOrderExpired =
            l_orderManager.isStopOrderSwitching(l_eqTypeOrderUnit);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B
        //[�Q�j�̖߂�l == true�̏ꍇ]
        //�@@�p�����[�^.�����P��.�iW�w�l�j�֑ؑO�w�l��ԋp����B
        if (l_blnIsStopOrderValid)
        {
            String l_strWLimitBeforeLimitPrice = null;
            if (!l_orderUnitRow.getWLimitBeforeLimitPriceIsNull())
            {
                l_strWLimitBeforeLimitPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_orderUnitRow.getWLimitBeforeLimitPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strWLimitBeforeLimitPrice;
        }

        //[�R�j�̖߂�l == true�̏ꍇ]
        //�@@�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l��ԋp����B
        if (l_blnIsStopOrderExpired)
        {
            String l_strConfirmedPrice = null;
            if (!l_orderUnitRow.getConfirmedPriceIsNull())
            {
                l_strConfirmedPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_orderUnitRow.getConfirmedPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strConfirmedPrice;
        }

        //[��L�ȊO]
        //�@@�p�����[�^.�����P��.�w�l��ԋp����B
        String l_strLimitPric = null;
        if (!l_orderUnitRow.getLimitPriceIsNull())
        {
            l_strLimitPric = WEB3StringTypeUtility.formatNumber(
                l_orderUnitRow.getLimitPrice());
        }

        log.exiting(STR_METHOD_NAME);
        return l_strLimitPric;
    }

    /**
     * (get�v�w�l�p�֑ؑO�����P��)<BR>
     * �����̒����������v�w�l�p�֑ؑO�����P����ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.��������.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.���������� <BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.��������  <BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.��������  <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ]  <BR>
     * �@@�@@�p�����[�^.��������.�iW�w�l�j�֑ؑO�w�l��ԋp����B<BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ]  <BR>
     * �@@�@@�p�����[�^.��������.�s�ꂩ��m�F�ς݂̎w�l��ԋp����B  <BR>
     * �@@[��L�ȊO]  <BR>
     * �@@�@@�p�����[�^.��������.�����P����ԋp����B <BR>
     * @@param l_eqTypeOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitBefSwitchPrice(EqTypeOrderAction l_eqTypeOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitBefSwitchPrice(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        // null��ԋp����B
        //[get��������()�Ɏw�肷�����]
        // ���������F�@@�p�����[�^.��������.��������
        // �����������F�@@�p�����[�^.��������.����������
        EqtypeOrderActionRow l_orderActionRow =
            (EqtypeOrderActionRow)l_eqTypeOrderAction.getDataSourceObject();
        String l_strConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderActionRow.getOrderConditionType(),
                l_orderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B
        //[is�X�g�b�v�����L��()�Ɏw�肷�����]
        //�@@���������F�@@�p�����[�^.��������
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderAction);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B
        //[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����]
        //�@@���������F�@@�p�����[�^.��������
        boolean l_blnIsStopOrderSwitching =
            l_orderManager.isStopOrderSwitching(l_eqTypeOrderAction);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B
        //[�Q�j�̖߂�l == true�̏ꍇ]
        //�@@�p�����[�^.��������.�iW�w�l�j�֑ؑO�w�l��ԋp����B
        if (l_blnIsStopOrderValid)
        {
            String l_strWLimitBeforeLimitPrice = null;
            if (!l_orderActionRow.getWLimitBeforeLimitPriceIsNull())
            {
                l_strWLimitBeforeLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_orderActionRow.getWLimitBeforeLimitPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strWLimitBeforeLimitPrice;
        }
        //[�R�j�̖߂�l == true�̏ꍇ]
        //�@@�p�����[�^.��������.�s�ꂩ��m�F�ς݂̎w�l��ԋp����B
        if (l_blnIsStopOrderSwitching)
        {
            String l_strConfirmedPrice = null;
            if (!l_orderActionRow.getConfirmedPriceIsNull())
            {
                l_strConfirmedPrice = WEB3StringTypeUtility.formatNumber(
                    l_orderActionRow.getConfirmedPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strConfirmedPrice;
        }
        //[��L�ȊO]
        //�@@�p�����[�^.��������.�����P����ԋp����B
        String l_strPrice = null;
        if (!l_orderActionRow.getPriceIsNull())
        {
            l_strPrice = WEB3StringTypeUtility.formatNumber(l_orderActionRow.getPrice());
        }
        log.exiting(STR_METHOD_NAME);
        return l_strPrice;
    }

    /**
     * (get�v�w�l�p�֑ؑO���s����)<BR>
     * �����̒����P�ʂ��v�w�l�p�֑ؑO���s������ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.���������� <BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��  <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A���s���������肷��B <BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@���s���� = �p�����[�^.�����P��.�iW�w�l�j�֑ؑO���s����<BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@���s���� = �p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎��s���� <BR>
     * �@@[��L�ȊO]<BR>
     * �@@�@@���s���� = �p�����[�^.�����P��.���s����<BR>
     * <BR>
     * �T�j�@@�g�����������}�l�[�W��.get���s�����iSONAR�j()���\�b�h���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[get���s�����iSONAR�j()�Ɏw�肷�����] <BR>
     * �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s���� <BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitBefSwitchExecCondType(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitBefSwitchExecCondType(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        //null��ԋp����B
        //[get��������()�Ɏw�肷�����]
        //�@@���������F�@@�p�����[�^.�����P��.��������
        //�@@�����������F�@@�p�����[�^.�����P��.����������
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strCondetionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B
        // [is�X�g�b�v�����L��()�Ɏw�肷�����]
        //�@@�����P�ʁF�@@�p�����[�^.�����P��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B
        //[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����]
        //�@@�����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnIsStopOrderExpired =
            l_orderManager.isStopOrderSwitching(l_eqTypeOrderUnit);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A���s���������肷��B
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType = null;

        //[�Q�j�̖߂�l == true�̏ꍇ]
        //�@@���s���� = �p�����[�^.�����P��.�iW�w�l�j�֑ؑO���s����
        if (l_blnIsStopOrderValid)
        {
            l_eqTypeExecutionConditionType = l_orderUnitRow.getWLimitBeforeExecCondType();
        }

        //[�R�j�̖߂�l == true�̏ꍇ]
        //�@@���s���� = �p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎��s����
        else if (l_blnIsStopOrderExpired)
        {
            l_eqTypeExecutionConditionType = l_orderUnitRow.getConfirmedExecConditionType();
        }
        //[��L�ȊO]
        //�@@���s���� = �p�����[�^.�����P��.���s����
        else
        {
            l_eqTypeExecutionConditionType = l_orderUnitRow.getExecutionConditionType();
        }

        //�T�j�@@�g�����������}�l�[�W��.get���s�����iSONAR�j()���\�b�h���R�[�����A
        // �߂�l��ԋp����B
        String l_strEqTypeExecutionConditionType =
            l_orderManager.getExecutionConditionTypeSonar(l_eqTypeExecutionConditionType);

        log.exiting(STR_METHOD_NAME);
        return l_strEqTypeExecutionConditionType;
    }

    /**
     * (get�v�w�l�p�֑ؑO���s����)<BR>
     * �����̒����������v�w�l�p�֑ؑO���s������ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.��������.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.���������� <BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.��������  <BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.��������  <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A���s���������肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@���s���� = �p�����[�^.��������.�iW�w�l�j�֑ؑO���s����<BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ]<BR>
     * �@@�@@���s���� = �p�����[�^.��������.�s�ꂩ��m�F�ς݂̎��s����<BR>
     * �@@[��L�ȊO]<BR>
     * �@@�@@���s���� = �p�����[�^.��������.���s����<BR>
     * <BR>
     * �T�j�@@�g�����������}�l�[�W��.get���s�����iSONAR�j()���\�b�h���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[get���s�����iSONAR�j()�Ɏw�肷�����]<BR>
     * �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s����<BR>
     * @@param l_eqTypeOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitBefSwitchExecCondType(EqTypeOrderAction l_eqTypeOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitBefSwitchExecCondType(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        // null��ԋp����B
        //[get��������()�Ɏw�肷�����]
        // ���������F�@@�p�����[�^.��������.��������
        // �����������F�@@�p�����[�^.��������.����������
        EqtypeOrderActionRow l_orderActionRow =
            (EqtypeOrderActionRow)l_eqTypeOrderAction.getDataSourceObject();
        String l_strConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderActionRow.getOrderConditionType(),
                l_orderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B
        //[is�X�g�b�v�����L��()�Ɏw�肷�����]
        //�@@���������F�@@�p�����[�^.��������
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderAction);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����ؑ֒�()���\�b�h���R�[������B
        //[is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����]
        //�@@���������F�@@�p�����[�^.��������
        boolean l_blnIsStopOrderSwitching =
            l_orderManager.isStopOrderSwitching(l_eqTypeOrderAction);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A���s���������肷��B
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType = null;
        //[�Q�j�̖߂�l == true�̏ꍇ]
        //�@@���s���� = �p�����[�^.��������.�iW�w�l�j�֑ؑO���s����
        if (l_blnIsStopOrderValid)
        {
            l_eqTypeExecutionConditionType = l_orderActionRow.getWLimitBeforeExecCondType();
        }
        //[�R�j�̖߂�l == true�̏ꍇ]
        //�@@���s���� = �p�����[�^.��������.�s�ꂩ��m�F�ς݂̎��s����
        else if (l_blnIsStopOrderSwitching)
        {
            l_eqTypeExecutionConditionType = l_orderActionRow.getConfirmedExecConditionType();
        }
        //[��L�ȊO]
        //�@@���s���� = �p�����[�^.��������.���s����
        else
        {
            l_eqTypeExecutionConditionType = l_orderActionRow.getExecutionConditionType();
        }

        //�T�j�@@�g�����������}�l�[�W��.get���s�����iSONAR�j()���\�b�h���R�[�����A
        // �߂�l��ԋp����B
        String l_strExecutionConditionType =
            l_orderManager.getExecutionConditionTypeSonar(l_eqTypeExecutionConditionType);

        log.exiting(STR_METHOD_NAME);
        return l_strExecutionConditionType;
    }

    /**
     * (get�x���敪)<BR>
     * �����̒����P�ʂ��g���K�[�����̒x���敪��ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l == "DEFAULT(�����w��Ȃ�)"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.���������� <BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�x������() == true �̏ꍇ�A<BR>
     * �@@"�x��"��ԋp����B<BR>
     * �@@�ȊO�A"����"��ԋp����B<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getDelayDiv(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDelayDiv(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l == "DEFAULT(�����w��Ȃ�)"�̏ꍇ�A
        //null��ԋp����B
        //[get��������()�Ɏw�肷�����]
        //�@@���������F�@@�p�����[�^.�����P��.��������
        //�@@�����������F�@@�p�����[�^.�����P��.����������
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strCondetionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�g�����������}�l�[�W��.is�x������() == true �̏ꍇ�A
        // "�x��"��ԋp����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsDelayOrder = l_orderManager.isDelayOrder(l_eqTypeOrderUnit);
        if (l_blnIsDelayOrder)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3EquityDelayDivDef.DELAY;
        }

        //�ȊO�A"����"��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return WEB3EquityDelayDivDef.NORMAL;
    }

    /**
     * (getW�w�l�����󋵋敪)<BR>
     * �p�����[�^.�����P�ʂ��  <BR>
     * PR�w�Ŏg�p����W�w�l�����̔����󋵋敪��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����󋵋敪�̔��� <BR>
     * <BR>
     * �@@�P�|�P�j�@@�����x���G���[�i�ؑ֒x���G���[�j�̔���<BR>
     * �@@�@@�g�����������}�l�[�W��.is�������x������() == true �̏ꍇ�A<BR>
     * �@@�@@"�����x���G���["��ԋp����B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�ҋ@@���i�֖ؑ��ρj�̔���<BR>
     * �@@�@@�����P��.���N�G�X�g�^�C�v��"DEFAULT"�̏ꍇ�A"�ҋ@@��"��ԋp����B<BR>
     * <BR>
     * �@@�P�|�R�j�@@�������i�ؑ֒��j�̔���@@<BR>
     * �@@�@@�����P��.���N�G�X�g�^�C�v��"�����T�[�o"�̏ꍇ�A"������"��ԋp����B<BR>
     * <BR>
     * �@@�P�|�S�j�@@���������i�ؑ֊����j�̔��� <BR>
     * �@@�@@�����P��.���N�G�X�g�^�C�v��"�ؑ֊���"�̏ꍇ�A"��������"��ԋp����B<BR>
     * <BR>
     * �@@�P�|�T�j�@@�X�g�b�v���������̔���<BR>
     * �@@�@@�����P��.���N�G�X�g�^�C�v��"����"�̏ꍇ�A"�X�g�b�v��������"��ԋp����B<BR>
     * <BR>
     * �@@�P�|�U�j�@@��L�ȊO�̏ꍇ�A"���̑�"��ԋp����B<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitOrderStatusType(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitOrderStatusType(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //�P�j�@@�����󋵋敪�̔���
        //�@@�P�|�P�j�@@�����x���G���[�i�ؑ֒x���G���[�j�̔���
        //�@@�g�����������}�l�[�W��.is�������x������() == true �̏ꍇ�A
        //�@@"�����x���G���["��ԋp����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsNotOrderedDelay =
            l_orderManager.isNotOrderedDelayOrder(l_eqTypeOrderUnit);
        if (l_blnIsNotOrderedDelay)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR;
        }

        //�@@�P�|�Q�j�@@�ҋ@@���i�֖ؑ��ρj�̔���
        //�@@�����P��.���N�G�X�g�^�C�v��"DEFAULT"�̏ꍇ�A"�ҋ@@��"��ԋp����B
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strRequestType = l_orderUnitRow.getRequestType();
        if (WEB3RequestTypeDef.DEFAULT.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3TriggerOrderStatusDef.ORDER_WAITING;
        }

        //�@@�P�|�R�j�@@�������i�ؑ֒��j�̔���
        //�@@�����P��.���N�G�X�g�^�C�v��"�����T�[�o"�̏ꍇ�A"������"��ԋp����B
        if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3TriggerOrderStatusDef.ORDERING;
        }

        //�@@�P�|�S�j�@@���������i�ؑ֊����j�̔���
        //�@@�����P��.���N�G�X�g�^�C�v��"�ؑ֊���"�̏ꍇ�A"��������"��ԋp����B
        if (WEB3RequestTypeDef.TRANSFERED.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3TriggerOrderStatusDef.ORDER_COMPLETE;
        }

        //�@@�P�|�U�j�@@�X�g�b�v���������̔���
        //�@@�����P��.���N�G�X�g�^�C�v��"����"�̏ꍇ�A"�X�g�b�v��������"��ԋp����B
        if (WEB3RequestTypeDef.INVALIDATE.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3TriggerOrderStatusDef.STOP_ORDER_INVALIDATION;
        }

        //�@@�P�|�V�j�@@��L�ȊO�̏ꍇ�A"���̑�"��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return WEB3TriggerOrderStatusDef.OTHER;
    }

    /**
     * (get�v�w�l�p���s����)<BR>
     * �����̒����P�ʂ��v�w�l�p���s������ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.��������  <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.����������  <BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]  <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��  <BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]  <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��  <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp���鎷�s���������肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ]  <BR>
     * �@@�@@���s���� = �p�����[�^.�����P��.���s����<BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ]  <BR>
     * �@@�@@���s���� = �p�����[�^.�����P��.���iW�w�l�j���s����<BR>
     * �@@[��L�ȊO]<BR>
     * �@@�@@���s���� = �p�����[�^.�����P��.�iW�w�l�j���s����<BR>
     * <BR>
     * �T�j�@@�g�����������}�l�[�W��.get���s�����iSONAR�j()���\�b�h���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[get���s�����iSONAR�j()�Ɏw�肷�����]<BR>
     * �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s����<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitExecCondType(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitExecCondType(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        //null��ԋp����B
        //[get��������()�Ɏw�肷�����]
        //�@@���������F�@@�p�����[�^.�����P��.��������
        //�@@�����������F�@@�p�����[�^.�����P��.����������
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strCondetionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B
        // [is�X�g�b�v�����L��()�Ɏw�肷�����]
        //�@@�����P�ʁF�@@�p�����[�^.�����P��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B
        // [is�X�g�b�v����������()�Ɏw�肷�����]
        // �����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnIsStopOrderExpired =
            l_orderManager.isStopOrderExpired(l_eqTypeOrderUnit);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp���鎷�s���������肷��B
        EqTypeExecutionConditionType l_executionConditionType = null;
        //[�Q�j�̖߂�l == true�̏ꍇ]
        //�@@���s���� = �p�����[�^.�����P��.���s����
        if (l_blnIsStopOrderValid)
        {
            l_executionConditionType = l_orderUnitRow.getExecutionConditionType();
        }
        //[�R�j�̖߂�l == true�̏ꍇ]
        //�@@���s���� = �p�����[�^.�����P��.���iW�w�l�j���s����
        else if (l_blnIsStopOrderExpired)
        {
            l_executionConditionType = l_orderUnitRow.getOrgWLimitExecCondType();
        }
        //[��L�ȊO]
        //�@@���s���� = �p�����[�^.�����P��.�iW�w�l�j���s����
        else
        {
            l_executionConditionType = l_orderUnitRow.getWLimitExecCondType();
        }

        //�T�j�@@�g�����������}�l�[�W��.get���s�����iSONAR�j()���\�b�h���R�[�����A
        //�߂�l��ԋp����B
        String l_strExecutionConditionType =
            l_orderManager.getExecutionConditionTypeSonar(l_executionConditionType);

        log.exiting(STR_METHOD_NAME);
        return l_strExecutionConditionType;
    }

    /**
     * (get�v�w�l�p�����P���敪)<BR>
     * �����̒����P�ʂ��v�w�l�p�����P���敪��ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.��������<BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.����������<BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]  <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��  <BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]  <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��  <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�̒����P���敪�����肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�����P��.isMarketOrder == true�ł���΁A"���s"�A<BR>
     * �@@�@@�ȊO��"�w�l"��ԋp����B<BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ]  <BR>
     * �@@�@@�p�����[�^.�����P��.���iW�w�l�j�����w�l == 0�ł���΁A"���s"�A<BR>
     * �@@�@@�ȊO��"�w�l"��ԋp����B<BR>
     * �@@[��L�ȊO]<BR>
     * �@@�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l == 0�ł���΁A"���s"�A<BR>
     * �@@�@@�ȊO��"�w�l"��ԋp����B<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitOrderPriceDiv(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitOrderPriceDiv(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        //null��ԋp����B
        //[get��������()�Ɏw�肷�����]
        //�@@���������F�@@�p�����[�^.�����P��.��������
        //�@@�����������F�@@�p�����[�^.�����P��.����������
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strCondetionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B
        // [is�X�g�b�v�����L��()�Ɏw�肷�����]
        //�@@�����P�ʁF�@@�p�����[�^.�����P��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B
        // [is�X�g�b�v����������()�Ɏw�肷�����]
        // �����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnIsStopOrderExpired =
            l_orderManager.isStopOrderExpired(l_eqTypeOrderUnit);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�̒����P���敪�����肷��B
        //�@@[�Q�j�̖߂�l == true�̏ꍇ]
        //�@@�@@�p�����[�^.�����P��.isMarketOrder == true�ł���΁A"���s"�A
        //�@@�@@�ȊO��"�w�l"��ԋp����B
        if (l_blnIsStopOrderValid)
        {
            if (l_eqTypeOrderUnit.isMarketOrder())
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
        //�@@[�R�j�̖߂�l == true�̏ꍇ]
        //�@@�@@�p�����[�^.�����P��.���iW�w�l�j�����w�l == 0�ł���΁A"���s"�A
        //�@@�@@�ȊO��"�w�l"��ԋp����B
        if (l_blnIsStopOrderExpired)
        {
            double l_dblOrgWLimitPrice = l_orderUnitRow.getOrgWLimitPrice();
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
        //�@@[��L�ȊO]
        //�@@�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l == 0�ł���΁A"���s"�A
        //�@@�@@�ȊO��"�w�l"��ԋp����B
        if (l_orderUnitRow.getWLimitPrice() == 0)
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
     * �����̒����P�ʂ��v�w�l�p�����P����ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.���������� <BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]  <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��  <BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]  <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��  <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�̏ꍇ]  <BR>
     * �@@�@@�p�����[�^.�����P��.�w�l��ԋp����B<BR>
     * �@@[�R�j�̖߂�l == true�̏ꍇ]  <BR>
     * �@@�@@�p�����[�^.�����P��.���iW�w�l�j�����w�l��ԋp����B<BR>
     * �@@[��L�ȊO]<BR>
     * �@@�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l��ԋp����B<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitOrderPrice(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitOrderPrice(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^�l��NULL
        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        //�@@null��ԋp����B
        //�@@[get��������()�Ɏw�肷�����]
        //�@@�@@���������F�@@�p�����[�^.�����P��.��������
        //�@@�@@�����������F�@@�p�����[�^.�����P��.����������
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
            l_eqtypeOrderUnitRow.getOrderConditionType(),
            l_eqtypeOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //�g�����������}�l�[�W�����擾
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B
        //�@@[is�X�g�b�v�����L��()�Ɏw�肷�����]
        //�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnStopOrderValid =
            l_equityOrderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B
        //�@@[is�X�g�b�v����������()�Ɏw�肷�����]
        //�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnStopOrderExpired =
            l_equityOrderManager.isStopOrderExpired(l_eqTypeOrderUnit);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B
        //�@@[�Q�j�̖߂�l == true�̏ꍇ]
        //�@@�@@�p�����[�^.�����P��.�w�l��ԋp����B
        if (l_blnStopOrderValid)
        {
            String l_strLimitPrice = null;
            if (!l_eqtypeOrderUnitRow.getLimitPriceIsNull())
            {
                l_strLimitPrice =
                    WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitRow.getLimitPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strLimitPrice;
        }

        //�@@[�R�j�̖߂�l == true�̏ꍇ]
        //�@@�@@�p�����[�^.�����P��.���iW�w�l�j�����w�l��ԋp����B
        else if (l_blnStopOrderExpired)
        {
            String l_strOrgWLimitPrice = null;
            if (!l_eqtypeOrderUnitRow.getOrgWLimitPriceIsNull())
            {
                l_strOrgWLimitPrice =
                    WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitRow.getOrgWLimitPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strOrgWLimitPrice;
        }

        //�@@[��L�ȊO]
        //�@@�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l��ԋp����B
        String l_strWLimitPrice = null;
        if (!l_eqtypeOrderUnitRow.getWLimitPriceIsNull())
        {
            l_strWLimitPrice =
                WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitRow.getWLimitPrice());
        }
        log.exiting(STR_METHOD_NAME);
        return l_strWLimitPrice;
    }

    /**
     * (get���v�w�l�p�����P��)<BR>
     * �����̒����P�ʂ�茳�v�w�l�p�����P����ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.��������<BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.����������<BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]  <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��  <BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@�p�����[�^.�����P��.���iW�w�l�j�����w�l��ԋp����B<BR>
     * �@@[��L�ȊO]<BR>
     * �@@�@@null��ԋp����B<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getOrgWLimitOrderPrice(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPrice(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^�l��NULL
        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        //�@@null��ԋp����B
        //�@@[get��������()�Ɏw�肷�����]
        //�@@�@@���������F�@@�p�����[�^.�����P��.��������
        //�@@�@@�����������F�@@�p�����[�^.�����P��.����������
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
            l_eqtypeOrderUnitRow.getOrderConditionType(),
            l_eqtypeOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //�g�����������}�l�[�W�����擾
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B
        //�@@[is�X�g�b�v�����L��()�Ɏw�肷�����]
        //�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnStopOrderValid =
            l_equityOrderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B
        //�@@[is�X�g�b�v����������()�Ɏw�肷�����]
        //�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnStopOrderExpired =
            l_equityOrderManager.isStopOrderExpired(l_eqTypeOrderUnit);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B
        //�@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]
        //�@@�@@�p�����[�^.�����P��.���iW�w�l�j�����w�l��ԋp����B
        if (l_blnStopOrderValid || l_blnStopOrderExpired)
        {
            String l_strOrgWLimitPrice = null;
            if (!l_eqtypeOrderUnitRow.getOrgWLimitPriceIsNull())
            {
                l_strOrgWLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_eqtypeOrderUnitRow.getOrgWLimitPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strOrgWLimitPrice;
        }

        //�@@[��L�ȊO]
        //�@@�@@null��ԋp����B
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
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.��������<BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.��������<BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.��������.���iW�w�l�j�����w�l��ԋp����B<BR>
     * �@@[��L�ȊO]<BR>
     * �@@�@@null��ԋp����B<BR>
     * @@param l_eqTypeOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getOrgWLimitOrderPrice(EqTypeOrderAction l_eqTypeOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPrice(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^�l��NULL
        if (l_eqTypeOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        //�@@null��ԋp����B
        //�@@[get��������()�Ɏw�肷�����]
        //�@@�@@���������F�@@�p�����[�^.��������.��������
        //�@@�@@�����������F�@@�p�����[�^.��������.����������
        EqtypeOrderActionRow l_eqtypeOrderActionRow =
            (EqtypeOrderActionRow)l_eqTypeOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
            l_eqtypeOrderActionRow.getOrderConditionType(),
            l_eqtypeOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //�g�����������}�l�[�W�����擾
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B
        //�@@[is�X�g�b�v�����L��()�Ɏw�肷�����]
        //�@@�@@���������F�@@�p�����[�^.��������
        boolean l_blnStopOrderValid =
            l_equityOrderManager.isStopOrderValid(l_eqTypeOrderAction);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B
        //�@@[is�X�g�b�v����������()�Ɏw�肷�����]
        //�@@�@@���������F�@@�p�����[�^.��������
        boolean l_blnStopOrderExpired =
            l_equityOrderManager.isStopOrderExpired(l_eqTypeOrderAction);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B
        //�@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]
        //�@@�@@�p�����[�^.��������.���iW�w�l�j�����w�l��ԋp����B
        if (l_blnStopOrderValid || l_blnStopOrderExpired)
        {
            String l_strOrgWLimitPrice = null;
            if (!l_eqtypeOrderActionRow.getOrgWLimitPriceIsNull())
            {
                l_strOrgWLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_eqtypeOrderActionRow.getOrgWLimitPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strOrgWLimitPrice;
        }

        //�@@[��L�ȊO]
        //�@@�@@null��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get���v�w�l�p�����P���敪)<BR>
     * �����̒����P�ʂ�茳�v�w�l�p�����P���敪��ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.����������<BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]  <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��  <BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��  <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@�p�����[�^.�����P��.���iW�w�l�j�����w�l == 0�ł���΁A"���s"�A<BR>
     * �@@�@@�ȊO��"�w�l"��ԋp����B  <BR>
     * �@@[��L�ȊO]  <BR>
     * �@@�@@null��ԋp����B<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getOrgWLimitOrderPriceDiv(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPriceDiv(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^�l��NULL
        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        //�@@null��ԋp����B
        //�@@[get��������()�Ɏw�肷�����]
        //�@@�@@���������F�@@�p�����[�^.�����P��.��������
        //�@@�@@�����������F�@@�p�����[�^.�����P��.����������
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
            l_eqtypeOrderUnitRow.getOrderConditionType(),
            l_eqtypeOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //�g�����������}�l�[�W�����擾
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���R�[������B
        //�@@[is�X�g�b�v�����L��()�Ɏw�肷�����]
        //�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnStopOrderValid =
            l_equityOrderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B
        //�@@[is�X�g�b�v����������()�Ɏw�肷�����]
        //�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnStopOrderExpired =
            l_equityOrderManager.isStopOrderExpired(l_eqTypeOrderUnit);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B
        //�@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]
        if (l_blnStopOrderValid || l_blnStopOrderExpired)
        {
            double l_dblOrgWLimitPrice = l_eqtypeOrderUnitRow.getOrgWLimitPrice();
            //�@@�@@�p�����[�^.�����P��.���iW�w�l�j�����w�l == 0�ł���΁A"���s"�A
            if (l_dblOrgWLimitPrice == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            //�@@�@@�ȊO��"�w�l"��ԋp����B
            else
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
        }

        //�@@[��L�ȊO]
        //�@@�@@null��ԋp����B
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
     * �@@[get��������()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.��������.��������<BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.���������� <BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.�������� <BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.��������  <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@�p�����[�^.��������.���iW�w�l�j�����w�l == 0�ł���΁A"���s"�A<BR>
     * �@@�@@�ȊO��"�w�l"��ԋp����B<BR>
     * �@@[��L�ȊO]  <BR>
     * �@@�@@null��ԋp����B<BR>
     * @@param l_eqTypeOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getOrgWLimitOrderPriceDiv(EqTypeOrderAction l_eqTypeOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPriceDiv(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^�l��NULL
        if (l_eqTypeOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        //�@@null��ԋp����B
        //�@@[get��������()�Ɏw�肷�����]
        //�@@�@@���������F�@@�p�����[�^.��������.��������
        //�@@�@@�����������F�@@�p�����[�^.��������.����������
        EqtypeOrderActionRow l_eqtypeOrderActionRow =
            (EqtypeOrderActionRow)l_eqTypeOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
            l_eqtypeOrderActionRow.getOrderConditionType(),
            l_eqtypeOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //�g�����������}�l�[�W�����擾
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���R�[������B
        //�@@[is�X�g�b�v�����L��()�Ɏw�肷�����]
        //�@@�@@���������F�@@�p�����[�^.��������
        boolean l_blnStopOrderValid =
            l_equityOrderManager.isStopOrderValid(l_eqTypeOrderAction);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B
        //�@@[is�X�g�b�v����������()�Ɏw�肷�����]
        //�@@�@@���������F�@@�p�����[�^.��������
        boolean l_blnStopOrderExpired =
            l_equityOrderManager.isStopOrderExpired(l_eqTypeOrderAction);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp�l�����肷��B
        //�@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]
        if (l_blnStopOrderValid || l_blnStopOrderExpired)
        {
            double l_dblOrgWLimitPrice = l_eqtypeOrderActionRow.getOrgWLimitPrice();
            //�@@�@@�p�����[�^.��������.���iW�w�l�j�����w�l == 0�ł���΁A"���s"�A
            if (l_dblOrgWLimitPrice == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            //�@@�@@�ȊO��"�w�l"��ԋp����B
            else
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
        }

        //�@@[��L�ȊO]
        //�@@�@@null��ԋp����B
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
     * �@@[get��������()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.��������<BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.���������� <BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]  <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��  <BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp���鎷�s���������肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ] <BR>
     * �@@�@@���s���� = �p�����[�^.�����P��.���iW�w�l�j���s����<BR>
     * �@@[��L�ȊO] <BR>
     * �@@�@@null��ԋp����B<BR>
     * <BR>
     * �T�j�@@�g�����������}�l�[�W��.get���s�����iSONAR�j()���\�b�h���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[get���s�����iSONAR�j()�Ɏw�肷�����]<BR>
     * �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s����<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getOrgWLimitExecCondType(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitExecCondType(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^�l��NULL
        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        //�@@null��ԋp����B
        //�@@[get��������()�Ɏw�肷�����]
        //�@@�@@���������F�@@�p�����[�^.�����P��.��������
        //�@@�@@�����������F�@@�p�����[�^.�����P��.����������
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
            l_eqtypeOrderUnitRow.getOrderConditionType(),
            l_eqtypeOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //�g�����������}�l�[�W�����擾
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B
        //�@@[is�X�g�b�v�����L��()�Ɏw�肷�����]
        //�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnStopOrderValid =
            l_equityOrderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B
        //�@@[is�X�g�b�v����������()�Ɏw�肷�����]
        //�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnStopOrderExpired =
            l_equityOrderManager.isStopOrderExpired(l_eqTypeOrderUnit);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp���鎷�s���������肷��B
        //�@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]
        //�@@�@@���s���� = �p�����[�^.�����P��.���iW�w�l�j���s����
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType = null;
        if (l_blnStopOrderValid || l_blnStopOrderExpired)
        {
            l_eqTypeExecutionConditionType =
                l_eqtypeOrderUnitRow.getOrgWLimitExecCondType();
        }

        //�@@[��L�ȊO]
        //�@@�@@null��ԋp����B
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�T�j�@@�g�����������}�l�[�W��.get���s�����iSONAR�j()���\�b�h���R�[�����A
        //�@@�߂�l��ԋp����B
        //�@@[get���s�����iSONAR�j()�Ɏw�肷�����]
        //�@@�@@���s�����F�@@�S�j�ɂČ��肵�����s����
        String l_strReturn =
            l_equityOrderManager.getExecutionConditionTypeSonar(l_eqTypeExecutionConditionType);

        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (get���v�w�l�p���s����)<BR>
     * �����̒���������茳�v�w�l�p���s������ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A<BR>
     * �@@null��ԋp����B <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.��������.��������<BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.����������<BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v�����L��()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.��������  <BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B<BR>
     * �@@[is�X�g�b�v����������()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.��������  <BR>
     * <BR>
     * �S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp���鎷�s���������肷��B<BR>
     * �@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]<BR>
     * �@@�@@���s���� = �p�����[�^.��������.���iW�w�l�j���s���� <BR>
     * �@@[��L�ȊO]  <BR>
     * �@@�@@null��ԋp����B  <BR>
     * <BR>
     * �T�j�@@�g�����������}�l�[�W��.get���s�����iSONAR�j()���\�b�h���R�[�����A<BR>
     * �@@�߂�l��ԋp����B  <BR>
     * <BR>
     * �@@[get���s�����iSONAR�j()�Ɏw�肷�����]  <BR>
     * �@@�@@���s�����F�@@�S�j�ɂČ��肵�����s����<BR>
     * @@param l_eqTypeOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getOrgWLimitExecCondType(EqTypeOrderAction l_eqTypeOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitExecCondType(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^�l��NULL
        if (l_eqTypeOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@this.get��������()�̖߂�l != "W�w�l"�̏ꍇ�A
        //�@@null��ԋp����B
        //�@@[get��������()�Ɏw�肷�����]
        //�@@�@@���������F�@@�p�����[�^.��������.��������
        //�@@�@@�����������F�@@�p�����[�^.��������.����������
        EqtypeOrderActionRow l_eqtypeOrderActionRow =
            (EqtypeOrderActionRow)l_eqTypeOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
            l_eqtypeOrderActionRow.getOrderConditionType(),
            l_eqtypeOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get��������()�̖߂�l != W�w�l�̏ꍇ�B");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //�g�����������}�l�[�W�����擾
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //�Q�j�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��()���\�b�h���R�[������B
        //�@@[is�X�g�b�v�����L��()�Ɏw�肷�����]
        //�@@�@@���������F�@@�p�����[�^.��������
        boolean l_blnStopOrderValid =
            l_equityOrderManager.isStopOrderValid(l_eqTypeOrderAction);

        //�R�j�@@�g�����������}�l�[�W��.is�X�g�b�v����������()���\�b�h���R�[������B
        //�@@[is�X�g�b�v����������()�Ɏw�肷�����]
        //�@@�@@���������F�@@�p�����[�^.��������
        boolean l_blnStopOrderExpired =
            l_equityOrderManager.isStopOrderExpired(l_eqTypeOrderAction);

        //�S�j�@@�Q�j�A�R�j�̖߂�l�ɂ��A�ԋp���鎷�s���������肷��B
        //�@@[�Q�j�̖߂�l == true�܂��́A�R�j�̖߂�l == true�̏ꍇ]
        //�@@�@@���s���� = �p�����[�^.��������.���iW�w�l�j���s����
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType = null;
        if (l_blnStopOrderValid || l_blnStopOrderExpired)
        {
            l_eqTypeExecutionConditionType =
                l_eqtypeOrderActionRow.getOrgWLimitExecCondType();
        }

        //�@@[��L�ȊO]
        //�@@�@@null��ԋp����B
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�T�j�@@�g�����������}�l�[�W��.get���s�����iSONAR�j()���\�b�h���R�[�����A
        //�@@�߂�l��ԋp����B
        //�@@[get���s�����iSONAR�j()�Ɏw�肷�����]
        //�@@�@@���s�����F�@@�S�j�ɂČ��肵�����s����
        String l_strReturn =
            l_equityOrderManager.getExecutionConditionTypeSonar(l_eqTypeExecutionConditionType);

        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (get�����󋵋敪)<BR>
     * PR�w�Ŏg�p���锭���󋵋敪��ԋp����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.����������ʁ�"�t�w�l"�̏ꍇ�A<BR>
     * �@@this.get�t�w�l�����󋵋敪()���R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.����������ʁ�"W�w�l"�̏ꍇ�A<BR>
     * �@@this.getW�w�l�����󋵋敪()���R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_strTriggerOrderType - (�����������)<BR>
     * �����������<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getOrderStatusType(
        EqTypeOrderUnit l_orderUnit,
        String l_strTriggerOrderType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderStatusType(EqTypeOrderUnit, String)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@�p�����[�^.����������ʁ�"�t�w�l"�̏ꍇ�A
        //this.get�t�w�l�����󋵋敪()���R�[�����A�߂�l��ԋp����B
        if (WEB3TriggerOrderTypeDef.STOP.equals(l_strTriggerOrderType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3EquityDataAdapter.getStopTriggerOrderStatusType(l_orderUnit);
        }

        //�Q�j�@@�p�����[�^.����������ʁ�"W�w�l"�̏ꍇ�A
        //this.getW�w�l�����󋵋敪()���R�[�����A�߂�l��ԋp����B
        if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_strTriggerOrderType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3EquityDataAdapter.getWLimitOrderStatusType(l_orderUnit);
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
