head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.21.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSLRepayCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍώ���X�V�C���^�Z�v�^(WEB3AioSLRepayCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/12 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.759,DB�X�V�d�l 150
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
 * (�،��S�ۃ��[���ԍώ���X�V�C���^�Z�v�^)<BR>
 * �،��S�ۃ��[���ԍώ���X�V�C���^�Z�v�^�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AioSLRepayCancelUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayCancelUpdateInterceptor.class);

    /**
     * (�،��S�ۃ��[���ԍώ���X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * @@roseuid 46DE559F014C
     */
    public WEB3AioSLRepayCancelUpdateInterceptor()
    {

    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl<BR>
     * ���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �y��Trae�z�⑫����.DB�X�V<BR>
     * �u�،��S�ۃ��[���ԍώ��_�����P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE<BR>
     * <BR>
     * �iOrderManagerPersistenceType�ɂĒ萔��`�B�j<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_aioOrderUnitParams - (�����P��Params)<BR>
     * �����P�ʂ̃p�����[�^�N���X<BR>
     * <BR>
     * @@return AioOrderUnitParams
     * @@roseuid 46DE55A300CC
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        AioOrderUnitParams l_aioOrderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, AioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_aioOrderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �،��S�ۃ��[���ԍώ��_�����P�ʃe�[�u��.xls
        // �������
        // 14:�����ρi��������j
        l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
        // �����L�����
        // 2:�N���[�Y
        l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        // ��������敪
        // 3�F�S���������
        l_aioOrderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.CANCELED);

        log.exiting(STR_METHOD_NAME);
        return l_aioOrderUnitParams;
    }
}
@
