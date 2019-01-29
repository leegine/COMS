head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ�������X�V�C���^�Z�v�^(WEB3AdminBondOrderCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����� (���u) �V�K�쐬
Revesion History : 2007/03/09 ꎉ�   (���u) �c�a�X�V�d�l 028
Revesion History : 2007/07/25 ������(���u) �d�l�ύX���f��NO.240
*/

package webbroker3.bd;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

/**
 * (���Ǘ��Ғ�������X�V�C���^�Z�v�^)<BR>
 * ���Ǘ��Ғ�������X�V�C���^�Z�v�^<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0 
 */
public class WEB3AdminBondOrderCancelUpdateInterceptor extends WEB3AdminBondDefaultInterceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondOrderCancelUpdateInterceptor.class);
    
    /**
     * (���Ǘ��Ғ�������X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44D96B960157
     */
    public WEB3AdminBondOrderCancelUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * �P�j�g�����ڃZ�b�g<BR>
     * �@@�p�����[�^.�������P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �@@�P�|�P�j�����P��Params.get�s�ꂩ��m�F�ς݂̐���IsNull() == true �̏ꍇ�A<BR>
     * �@@�@@���ڐݒ���e�� �u����������i�����_�O�����j_�������P�ʃe�[�u��DB�X�V�d�l.xls�v�Q�ƁB<BR>
     * <BR>
     * �@@�P�|�Q�j�@@��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@���ڐݒ���e�� �u����������i����_�O�����j_�������P�ʃe�[�u��DB�X�V�d�l.xls�v�Q�ƁB <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44D96B960167
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        //�P�j�g�����ڃZ�b�g
        if (l_params.getConfirmedQuantityIsNull())
        {
            //�P�|�P�j�����P��Params.get�s�ꂩ��m�F�ς݂̐���IsNull() == true �̏ꍇ
            //���ڐݒ���e�� �u����������i�����_�O�����j_�������P�ʃe�[�u��DB�X�V�d�l.xls�v�Q�ƁB
            //2�F�����
            l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.CANCELED);

            //  �������P��.��������������ԊǗ�.get�������̏ꍇ�A
            //�@@  0�F�����M
            //  ����ȊO�̏ꍇ�A
            //�@@�@@2:���M�s�v
            try
            {
                if (WEB3DateUtility.compareToDay(
                    WEB3DateUtility.getDate(l_params.getBizDate(), "yyyyMMdd"),
                    WEB3GentradeTradingTimeManagement.getOrderBizDate()) < 0)
                {
                    l_params.setHostSendDiv(WEB3HostSendDivDef.UNSEND);
                }
                else
                {
                    l_params.setHostSendDiv(WEB3HostSendDivDef.NOT_SEND);
                }
            }
            catch (WEB3SystemLayerException l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //�Ǘ���.get�Ǘ��҃R�[�h()
            l_params.setAdministratorCode(this.getAdministrator().getAdministratorCode());
        }
        //�P�|�Q�j�@@��L�ȊO�̏ꍇ�A
        //���ڐݒ���e�� �u����������i����_�O�����j_�������P�ʃe�[�u��DB�X�V�d�l.xls�v�Q�ƁB

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
