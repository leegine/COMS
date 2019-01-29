head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.21.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashoutCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o������X�V�C���^�Z�v�^(WEB3AioCashoutCancelUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ���E (���u) �V�K�쐬      
                   2004/10/23 ������ (���u) ���r���[             
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�o������X�V�C���^�Z�v�^)<BR>
 * �o������X�V�C���^�Z�v�^�N���X
 *
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashoutCancelUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutCancelUpdateInterceptor.class); 
    
    /**
     * (�o������X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^
     * @@return WEB3AioCashoutCancelUpdateInterceptor
     * @@roseuid 40E2A4160252
     */
    public WEB3AioCashoutCancelUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB <BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params��<BR>
     * �g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�o�����_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE<BR>
     * <BR>
     * �iOrderManagerPersistenceType�ɂĒ萔��`�B�j
     * @@param l_process - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * �����P�ʂ̃p�����[�^�N���X
     * @@return AioOrderUnitParams
     * @@roseuid 40E2A4230204
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_updateType,
            OrderManagerPersistenceContext l_process, AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType l_updateType," +
            "OrderManagerPersistenceContext l_process, " +
            "AioOrderUnitParams l_orderUnitParams)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }           
        // 1)��������敪�̐ݒ���s���B
        l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.CANCELED);
        
        // QA:WEB3-XBAIO-REMAIN-A-FT-0028��WEB3-XBAIO-REMAIN-A-FT-0029�ɂ���āA
        // �ȉ��̓��e��ǉ�����F
        // Begin
        //������ԁ�CANCELLED�A�����L����ԁ�CLOSED
        l_orderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
        l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        //End
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
}@
