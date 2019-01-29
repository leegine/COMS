head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAction.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��������������(WEB3FeqOrderAction.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14  ����(���u) �V�K�쐬
                   2006/10/30  �����(���u) ����̍X���f��295
Revesion History : 2008/10/02 ���V(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.466
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderActionImpl;

import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.feq.define.WEB3FeqActionStatusDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�O��������������)<BR>
 * �O��������������<BR>
 * 
 * @@ author ���� 
 * @@ version 1.0 
 */
public class WEB3FeqOrderAction extends FeqOrderActionImpl 
{
    
    /**
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAction.class);

    /**
     * @@roseuid 42CE39EA01A5
     */
    public WEB3FeqOrderAction(long order_action_id) 
        throws DataQueryException, DataNetworkException
    {
        super(order_action_id);
    }
    
    /**
     * @@roseuid 42CE39EA01A5
     */
    public WEB3FeqOrderAction(FeqOrderActionRow row)
    {
       super(row);
    }
    
    /**
     * (get������ԋ敪)<BR>
     * ���������̏�Ԃ�ԋp����B<BR>
     * <BR>
     * �߂�l�̗�����ԋ敪�F<BR>
     * 00�F�V�K���� 01�F������t 02�F�V�K����(���s) 03�F��������<BR>
     * 04�F������t 05�F�������� 06�F��������(���s) 07�F�������<BR>
     * 08�F�����t 09�F������� 10�F�������(���s) 11�F�ꕔ���<BR>
     * 12�F�S����� 13�F����� 14�F���� 15�F������� 16�F����<BR>
     * 17�F�����J�z 18�F�����J�z(���s) 28�F������t���<BR>
     * 31�F��菈�����i�ꕔ���j 32�F��菈�����i�S�����j<BR>
     * 99�F���̑�<BR>
     * <BR>
     * -----------------------------------------------------<BR>
     * ---------------------<BR>
     * �P�j�@@���<BR>
     * �@@this.�����C�x���g�^�C�v == EXECUTE(���)����<BR>
     * �@@�p�����[�^.�����P��.�����t���O == "0�FDEFAULT"�̏ꍇ<BR>
     * �@@�E�ꕔ���ithis.isPartiallyExecuted( ) == true�j�Ȃ��<BR>
     * �@@�@@"�ꕔ���"��Ԃ��B<BR>
     * �@@�E�S�����ithis.isFullyExecuted( ) == true�j�Ȃ�΁A<BR>
     * �@@�@@"�S�����"��Ԃ��B<BR>
     * <BR>
     * �@@�p�����[�^.�����P��.�����t���O == "1�F�����"�̏ꍇ<BR>
     * �@@�E�ꕔ���ithis.isPartiallyExecuted( ) == true�j�Ȃ�΁A<BR>
     * �@@�@@"��菈�����i�ꕔ���j"��Ԃ��B<BR>
     * �@@�E�S�����ithis.isFullyExecuted( ) == true�j�Ȃ�΁A<BR>
     * �@@�@@"��菈�����i�S�����j"��Ԃ��B<BR>
     * <BR>
     * �Q�j�@@����<BR>
     * �@@this.�����C�x���g�^�C�v == EXPIRE(�����ς�) ����<BR>
     * �@@this.���������X�e�[�^�X == INVALIDATED_BY_MKT<BR>
     * �@@(�}�[�P�b�g����)�̏ꍇ�A"����"��Ԃ��B<BR>
     * <BR>
     * �R�j�@@�����A�����J�z(���s)<BR>
     * �@@this.�����C�x���g�^�C�v == EXPIRE(�����ς�) ����<BR>
     * �@@this.���������X�e�[�^�X == EXPIRED(�I��)�̏ꍇ�A<BR>
     * �@@�Ethis.�����G���[���R�R�[�h != "0000�F<BR>
     * �@@����"�Ȃ�΁A"�����J�z(���s)"��Ԃ��B<BR>
     * �@@�E��L�ȊO�ł���΁A"����"��Ԃ��B<BR>
     * <BR>
     * �S�j�@@�������<BR>
     * �@@this.�����C�x���g�^�C�v == UNDO_INVALIDATION_BY_MKT<BR>
     * �@@(�������)�̏ꍇ�A"�������"��Ԃ��B<BR>
     * <BR>
     * �T�j�@@�����<BR>
     * �@@this.�����C�x���g�^�C�v == UNDO_EXECUTION(�����)�̏ꍇ�A<BR>
     * �@@"�����"��Ԃ��B<BR>
     * <BR>
     * �U�j�@@�V�K�����A�����J�z<BR>
     * �@@this.������� == ACCEPTED(��t��(�V�K����))�̏ꍇ�A<BR>
     * �@@�E�O�����������}�l�[�W��.is�J�z�����P��(�p�����[�^.�����P��)<BR> 
     * �@@== false�i == ���񒍕��j�Ȃ�΁A<BR>
     * �@@�@@"�V�K����"��Ԃ��B<BR>
     * �@@�E��L�ȊO�Ȃ�΁A"�����J�z"��Ԃ��B<BR>
     * <BR>
     * �V�j�@@������t<BR>
     * �@@this.������� == ORDERED(�����ς݁i�V�K����))�̏ꍇ�A<BR>
     * �@@"������t"��Ԃ��B<BR>
     * <BR>
     * �W�j�@@�V�K����(���s)<BR>
     * �@@this.������� == NOT_ORDERED(�������s(�V�K����))�̏ꍇ�A<BR>
     * �@@�@@"�V�K����(���s)"��Ԃ��B<BR>
     * <BR>
     * �X�j�@@��������<BR>
     * �@@this.������� == MODIFY_ACCEPTED(��t��(�ύX����))<BR>
     * �@@�̏ꍇ�A"��������"��Ԃ��B<BR>
     * <BR>
     * �P�O�j�@@������t<BR>
     * �@@this.������� == MODIFYING(������(�ύX����))�̏ꍇ�A<BR>
     * �@@"������t"��Ԃ��B<BR>
     * <BR>
     * �P�P�j�@@��������<BR>
     * �@@this.������� == MODIFIED(�����ς�(�ύX����))�̏ꍇ�A<BR>
     * �@@"��������"��Ԃ��B<BR>
     * <BR>
     * �P�Q�j�@@��������(���s)<BR>
     * �@@this.������� == NOT_MODIFIED(�������s(�ύX����))�̏ꍇ�A<BR>
     * �@@"��������(���s)"��Ԃ��B<BR>
     * <BR>
     * �P�R�j�@@�������<BR>
     * �@@this.������� == CANCEL_ACCEPTED(��t��(�������))<BR>
     * �@@�̏ꍇ�A"�������"��Ԃ��B<BR>
     * <BR>
     * �P�S�j�@@�����t<BR>
     * �@@this.������� == CANCELLING(������(�������))<BR>
     * �@@�̏ꍇ�A"�����t"��Ԃ��B<BR>
     * <BR>
     * �P�T�j�@@�������<BR>
     * �@@this.������� == CANCELLED(�����ς�(�������))�̏ꍇ�A<BR>
     * �@@"�������"��Ԃ��B<BR>
     * <BR>
     * �P�U�j�@@�������(���s)<BR>
     * �@@this.������� == NOT_CANCELLED(�������s(�������))�̏ꍇ�A<BR>
     * �@@"�������(���s)"��Ԃ��B<BR>
     * <BR>
     * �P�V�j�@@������t���<BR>
     * �@@this.������� == ORDERING(������(�V�K����))�@@����<BR>
     * �@@this.�����C�x���g�^�C�v==SEND_TO_MKT�i�}�[�P�b�g���M�ς݁j�̏ꍇ�A"������t���"��Ԃ��B<BR>
     * <BR>
     * �P�W�j�@@��L�ȊO�̏ꍇ�A"���̑�"��Ԃ��B<BR>
     * ---------------------------------------------------<BR>
     * -----------------------<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �O�����������P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@roseuid 42A55401007B
     */
    public String getActionStateDiv(WEB3FeqOrderUnit l_orderUnit) 
    {
        final String STR_METHOD_NAME = "getActionStateDiv()";
        log.entering(STR_METHOD_NAME);

        String l_strReturn; 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_orderManager =
            (WEB3FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        boolean l_blnIsCarryOverOrderUnit = l_orderManager.isCarryOverOrderUnit(l_orderUnit);               
        OrderEventTypeEnum l_oetEventType = this.getOrderEventType();
        OrderExpirationStatusEnum l_oetExpirationStatus = this.getExpirationStatus();
        OrderStatusEnum l_oetStatus = this.getOrderStatus();
        FeqOrderUnitRow l_feqOrderUnitRow = 
            (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
            
        //�P�j�@@���
        //�@@this.�����C�x���g�^�C�v == EXECUTE(���)����
        //�@@�p�����[�^.�����P��.�����t���O == "0�FDEFAULT"�̏ꍇ
        if (OrderEventTypeEnum.EXECUTE.equals(l_oetEventType))
        {
            if (WEB3TemporaryExecutionFlagDef.DEFAULT.equals(
                    l_feqOrderUnitRow.getTemporaryExecutionFlag()))
            {
                if (this.isPartiallyExecuted() == true)
                {
                    l_strReturn = WEB3FeqActionStatusDivDef.PART_EXECUTE;
                }
                else
                {
                    l_strReturn = WEB3FeqActionStatusDivDef.FULL_EXECUTE;
                }
            }
            else
            {
                if (this.isPartiallyExecuted() == true)
                {
                    l_strReturn = WEB3FeqActionStatusDivDef.PROCESSING_PART_EXECUTE;
                }
                else
                {
                    l_strReturn = WEB3FeqActionStatusDivDef.PROCESSING_FULL_EXECUTE;
                }
            }
        }
        //�Q�j�@@����
        //this.�����C�x���g�^�C�v == EXPIRE(�����ς�) ����
        //this.���������X�e�[�^�X == INVALIDATED_BY_MKT
        else if (OrderEventTypeEnum.EXPIRE.equals(l_oetEventType) 
            && OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_oetExpirationStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.EXPIRE;
        }
        //�R�j�@@�����A�����J�z(���s)   
        //this.�����C�x���g�^�C�v == EXPIRE(�����ς�) ����
        //this.���������X�e�[�^�X == EXPIRED(�I��)�̏ꍇ 
        else if (OrderEventTypeEnum.EXPIRE.equals(l_oetEventType) 
            && OrderExpirationStatusEnum.EXPIRED.equals(l_oetExpirationStatus))
        {
            //this.�����G���[���R�R�[�h != "0000�F����"�̏ꍇ
            if (!WEB3ErrorReasonCodeDef.NORMAL.equals(super.m_row.getErrorReasonCode()))
            {
                l_strReturn = WEB3FeqActionStatusDivDef.ORDER_CARRY_OVER_REJECT;   
            } 
            else 
            {
                l_strReturn = WEB3FeqActionStatusDivDef.INVALID;   
            }
        }
        //�S�j�@@�������
        //this.�����C�x���g�^�C�v == UNDO_INVALIDATION_BY_MKT(�������)�̏ꍇ
        else if (OrderEventTypeEnum.UNDO_INVALIDATION_BY_MKT.equals(l_oetEventType))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.UNDO_EXPIRE;
        }
        //�T�j�@@�����
        //this.�����C�x���g�^�C�v == UNDO_EXECUTION(�����)�̏ꍇ
        else if (OrderEventTypeEnum.UNDO_EXECUTION.equals(l_oetEventType))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.UNDO_EXECUTE; 
        }
        //�U�j�@@�V�K�����A�����J�z
        //this.������� == ACCEPTED(��t��(�V�K����))�̏ꍇ 
        else if (OrderStatusEnum.ACCEPTED.equals(l_oetStatus))
        {
            // �O�����������}�l�[�W��.is�J�z�����P��(�p�����[�^.�����P��) == false�i == ���񒍕��j
            if (!l_blnIsCarryOverOrderUnit )
            {
                l_strReturn = WEB3FeqActionStatusDivDef.NEW_ORDER;
            } 
            else 
            {
                l_strReturn = WEB3FeqActionStatusDivDef.ORDER_CARRY_OVER;         
            }
        }
        //�V�j�@@������t
        //this.������� == ORDERED(�����ς݁i�V�K����))�̏ꍇ
        else if (OrderStatusEnum.ORDERED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.NEW_ORDER_ACCEPT; 
        }
        //�W�j�@@�V�K����(���s) 
        //this.������� == NOT_ORDERED(�������s(�V�K����))�̏ꍇ
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.NEW_ORDER_REJECT;  
        }
        //�X�j�@@�������� 
        //this.������� == MODIFY_ACCEPTED(��t��(�ύX����))�̏ꍇ
        else if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CHANGE;    
        }    
        //�P�O�j�@@������t
        //this.������� == MODIFYING(������(�ύX����))�̏ꍇ
        else if (OrderStatusEnum.MODIFYING.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CHANGE_ACCEPT; 
        }   
        //�P�P�j�@@�������� 
        //this.������� == MODIFIED(�����ς�(�ύX����))�̏ꍇ
        else if (OrderStatusEnum.MODIFIED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CHANGE_COMPLETE;    
        }   
        //�P�Q�j�@@��������(���s) 
        //this.������� == NOT_MODIFIED(�������s(�ύX����))�̏ꍇ
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CHANGE_REJECT;     
        }   
        //�P�R�j�@@�������
        //this.������� == CANCEL_ACCEPTED(��t��(�������))�̏ꍇ
        else if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CANCEL;    
        }
        //�P�S�j�@@�����t
        //this.������� == CANCELLING(������(�������))�̏ꍇ
        else if (OrderStatusEnum.CANCELLING.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CANCEL_ACCEPT;     
        }   
        //�P�T�j�@@�������
        //this.������� == CANCELLED(�����ς�(�������))�̏ꍇ
        else if (OrderStatusEnum.CANCELLED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CANCEL_COMPLETE;   
        }
        //�P�U�j�@@�������(���s) 
        //this.������� == NOT_CANCELLED(�������s(�������))�̏ꍇ
        else if (OrderStatusEnum.NOT_CANCELLED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CANCEL_REJECT;  
        } 
        //�P�V�j�@@������t��� 
        //this.������� == ORDERING(������(�V�K����))�@@���� 
        //this.�����C�x���g�^�C�v==SEND_TO_MKT�i�}�[�P�b�g���M�ς݁j�̏ꍇ�A"������t���"��Ԃ��B 
        else if (OrderStatusEnum.ORDERING.equals(l_oetStatus)
            && OrderEventTypeEnum.SEND_TO_MKT.equals(l_oetEventType))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.ORDER_ACCEPT_CANCEL;  
        } 
        //��L�ȊO�̏ꍇ
        else 
        {
            l_strReturn = WEB3FeqActionStatusDivDef.OTHER;
        }

        log.debug(STR_METHOD_NAME + ".get������ԋ敪 : " + l_strReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }
    
    /**
     * (get��t���ʋ敪)<BR>
     * ���������̎�t���ʋ敪��ԋp����B<BR>
     * <BR>
     * �߂�l�̎�t���ʋ敪�F<BR>
     * 0000�F���� 1001�F��t�G���[ 1002�F�����G���[ 1003�F����G���[<BR>
     * 0001�F�l���G���[ 0002�F�a����s���G���[ 0003�F<BR>
     * �����c���s���G���[ 0004�F�ۏ؋��s���G���[<BR>
     * 0005�F�����c���s���G���[ 0006�F������~�����G���[ 0007�F<BR>
     * �s��ύX�����G���[<BR>
     * 0008�F���t�]�̓G���[ 0009�F���t�\���ʃG���[ 0010�F<BR>
     * ��������G���[<BR>
     * 0011�F�����J�z�X�L�b�v�����G���[<BR>
     * 9001�F���̑��G���[<BR>
     * <BR>
     * --------------------------------------------------<BR>
     * ------------------------<BR>
     * �Ethis.���������E����敪 == "������s"�̏ꍇ<BR>
     * <BR>
     * �@@"����G���["��Ԃ��B<BR>
     * <BR>
     * �Ethis.���������E����敪 == "�������s"�̏ꍇ<BR>
     * <BR>
     * �@@"�����G���["��Ԃ��B<BR>
     * <BR>
     * �Ethis.������� == NOT_ORDERED�i�������s�i�V�K�����j�j<BR>
     * �@@���@@this.�����C�x���g�^�C�v == REJECTED_BY_MARKET<BR>
     * �@@�i�}�[�P�b�g���ہi�V�K�����j�j<BR>
     * �@@�̏ꍇ<BR>
     * <BR>
     * �@@"��t�G���["��Ԃ��B<BR>
     * <BR>
     * �E��L�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@this.�����G���[���R�R�[�h ��Ԃ��B<BR>
     * --------------------------------------------------<BR>
     * ------------------------<BR>
     * @@return String
     * @@roseuid 42A555C100D9
     */
    public String getAcceptStatusDiv() 
    {
        final String STR_METHOD_NAME = "getAcceptStatusDiv()";
        log.entering(STR_METHOD_NAME); 
        
        String l_strReturn;       
        String l_mdyCancelType = this.m_row.getModifyCancelType();
        OrderEventTypeEnum l_oetEventType = this.getOrderEventType();
        OrderStatusEnum l_oetStatus = this.getOrderStatus();
        
        //this.���������E����敪 == "������s"�̏ꍇ
        if (WEB3ModifyCancelTypeDef.CANCEL_ERROR.equals(l_mdyCancelType))
        {
            l_strReturn = WEB3ErrorReasonCodeDef.CANCEL_ERROR;
        }
        //this.���������E����敪 == "�������s"�̏ꍇ
        else if (WEB3ModifyCancelTypeDef.CHANGE_ERROR.equals(l_mdyCancelType))
        {
            l_strReturn = WEB3ErrorReasonCodeDef.CHANGE_ERROR;
        }
        //this.������� == NOT_ORDERED�i�������s�i�V�K�����j�j
        //���@@this.�����C�x���g�^�C�v == REJECTED_BY_MARKET 
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_oetStatus)
            && OrderEventTypeEnum.REJECTED_BY_MKT.equals(l_oetEventType))
        {
            l_strReturn = WEB3ErrorReasonCodeDef.ACCEPT_ERROR;
        } 
        else 
        {
            l_strReturn = this.m_row.getErrorReasonCode();
        }

        log.debug(STR_METHOD_NAME + ".get��t���ʋ敪 : " + l_strReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }
}
@
