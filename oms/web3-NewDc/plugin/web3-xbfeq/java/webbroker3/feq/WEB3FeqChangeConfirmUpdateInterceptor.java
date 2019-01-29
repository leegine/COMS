head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeConfirmUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������m��X�V�C���^�Z�v�^(WEB3FeqChangeConfirmUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 ���U (���u) �V�K�쐬
                 : 2005/07/28 ������(���u) ���r���[
*/
package webbroker3.feq;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�����������m��X�V�C���^�Z�v�^)<BR>
 * �O�����������m��X�V�C���^�Z�v�^�N���X<BR>
 * 
 * @@author ���U(���u)
 * @@version 1.0 
 */
public class WEB3FeqChangeConfirmUpdateInterceptor 
        extends WEB3FeqUpdateInterceptor
{

    /**
     * (���O���[�e�B���e�B)<BR>
     */
    protected static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqChangeConfirmUpdateInterceptor.class);
    
    /**
     * (�O�����������m��X�V�C�x���g�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 429D71260251
     */
    public WEB3FeqChangeConfirmUpdateInterceptor()
    {

    }

    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �P�j�@@�����iͯ�ށj�e�[�u���X�V<BR>
     * �@@super.mutate(OrderManagerPersistenceType,<BR>
     * �@@OrderManagerPersistenceContext, FeqOrderUnitParams)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �Q�j�@@�����P�ʃe�[�u���X�V<BR>
     * �@@�����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * ���ڐݒ���e�́A<BR>
     * DB�X�V�d�l�u�����m��_�O�������P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * �X�V�^�C�v<BR>
     * @@param l_context - (����)<BR>
     * ����<BR>
     * @@param l_orderUnitParams - (�����P�ʍs)<BR>
     * �����P�ʍs�i�F�����P��Params�j<BR>
     * @@return FeqOrderUnitParams
     * @@roseuid 429D71260260
     */
    public FeqOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType,"
            + "OrderManagerPersistenceContext, " 
            + "FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitParams == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        

        //�P�j�@@�����iͯ�ށj�e�[�u���X�V
        super.mutate(
            l_updateType,
            l_context,
            l_orderUnitParams);

        //�Q�j�@@�����P�ʃe�[�u���X�V
        WEB3FeqOrderUnit l_orderUnit = new WEB3FeqOrderUnit(l_orderUnitParams);
        
        // ������ԁ��h�����ς݁i�ύX�����j�h��ݒ�
        l_orderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        
        // ���������E����敪
        // this.isUnexecuted( ) == true�̏ꍇ�́A�h�S�����������h
        if (l_orderUnit.isUnexecuted())
        {
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGED);
        }
        // this.isUnexecuted( ) == false�̏ꍇ�́A�h�ꕔ���������h
        else
        {
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
        }
        
        //�s�ꂩ��m�F�ς݂̒����P��
        l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.price);
        
        //�s�ꂩ��m�F�ς݂̊T�Z��n���
        l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.estimated_price);
        
        //�s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
        l_orderUnitParams.setConfirmedFEstimatedPrice(l_orderUnitParams.f_estimated_price);
        
        //�s�ꂩ��m�F�ς݂̎��s����
        l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.execution_condition_type);
        
        //�����G���[���R�R�[�h
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
}
@
