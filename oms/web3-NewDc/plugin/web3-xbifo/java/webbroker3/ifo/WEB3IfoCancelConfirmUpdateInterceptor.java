head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoCancelConfirmUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP����m��X�V�C���^�Z�v�^�N���X(WEB3IfoCancelConfirmUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/16 䈋� (���u) �V�K�쐬
*/

package webbroker3.ifo;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;

/**
 * (�敨OP����m��X�V�C���^�Z�v�^)<BR>
 * �敨OP����m��X�V�C���^�Z�v�^�N���X<BR>
 * @@author  䈋�
 * @@version 1.0
 */
public class WEB3IfoCancelConfirmUpdateInterceptor
    extends WEB3IfoUpdateInterceptor
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoCancelConfirmUpdateInterceptor.class);

    /**
     * (�敨OP����m��X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@return webbroker3.ifo.WEB3IfoCancelConfirmUpdateInterceptor
     * @@roseuid 40A81EF1018B
     */
    public WEB3IfoCancelConfirmUpdateInterceptor()
    {

    }

    /**
     * (�X�V�l�ݒ�)<BR>
     *�imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A<BR>�E
     * �p����B<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �����P��.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ <BR>
     * �uOP����m��_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR> 
     * �����P��.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ <BR>
     * �u�敨����m��_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR> 
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * 
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_ifoOrderUnitParams - (�����P��Params)<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 40A81EF1017C
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderUnitParams l_ifoOrderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate()";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        try
        {
            //�����G���[���R�R�[�h = 0000�F����                                                                      
            l_ifoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

            long l_lngOrderId = l_ifoOrderUnitParams.getOrderId();
            IfoOrderImpl l_order = new IfoOrderImpl(l_lngOrderId);
            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];
            
            //���������E����敪���Z�b�g
            if (l_orderUnit.isUnexecuted())
            {
                l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELED);
            }
            else
            {
                l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.PART_CANCELED);
            }
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnitParams;
    }
}
@
