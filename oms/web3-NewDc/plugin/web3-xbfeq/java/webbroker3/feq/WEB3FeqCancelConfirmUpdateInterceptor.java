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
filename	WEB3FeqCancelConfirmUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O����������m��X�V�C���^�Z�v�^(WEB3FeqCancelConfirmUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �����F (���u) �V�K�쐬
                 : 2005/07/25 ���I(���u) ���r���[
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O����������m��X�V�C���^�Z�v�^)<BR>
 * �O����������m��X�V�C���^�Z�v�^�N���X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3FeqCancelConfirmUpdateInterceptor extends WEB3FeqUpdateInterceptor
{
    /**
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqCancelConfirmUpdateInterceptor.class);

    /**
     * (�O����������m��X�V�C�x���g�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 429D814202CE
     */
    public WEB3FeqCancelConfirmUpdateInterceptor()
    {

    }

    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     *  <BR>
     * �P�j�@@�����iͯ�ށj�e�[�u���X�V<BR>
     * �@@super.mutate(OrderManagerPersistenceType,<BR>
     * �@@OrderManagerPersistenceContext, FeqOrderUnitParams)<BR>
     * �@@���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����P�ʃe�[�u���X�V<BR>
     * �@@�����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * ���ڐݒ���e�́A<BR>
     * DB�X�V�d�l�u����m��_�O�������P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * �X�V�^�C�v<BR>
     * @@param l_context - (����)<BR>
     * ����<BR>
     * @@param l_feqOrderUnitParams - (�����P�ʍs)<BR>
     * �����P�ʍs�i�F�����P��Params�j<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams
     * @@roseuid 429D814202ED
     */
    public FeqOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderUnitParams l_feqOrderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if(l_feqOrderUnitParams == null)
        {
            log.debug(" �����P��Params�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        // �P�j�@@�����iͯ�ށj�e�[�u���X�V
        l_feqOrderUnitParams = super.mutate(l_updateType, l_context, l_feqOrderUnitParams);
        // �Q�j�@@�����P�ʃe�[�u���X�V
        // ���������E����敪
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        FeqOrderManager l_orderManager =
            (FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        FeqOrderUnit l_orderUnit =  (FeqOrderUnit)l_orderManager.toOrderUnit(l_feqOrderUnitParams);
        // this.isUnexecuted( ) == false�̏ꍇ�́A�h�ꕔ��������h
        // this.isUnexecuted( ) == true�̏ꍇ�́A�h�S����������h
        boolean l_blnIsUnExecuted = l_orderUnit.isUnexecuted();
        l_feqOrderUnitParams.setModifyCancelType(
            (!l_blnIsUnExecuted) ? WEB3ModifyCancelTypeDef.PART_CANCELED : WEB3ModifyCancelTypeDef.CANCELED);

        // �����G���[���R�R�[�h
        l_feqOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnitParams;
    }
}
@
