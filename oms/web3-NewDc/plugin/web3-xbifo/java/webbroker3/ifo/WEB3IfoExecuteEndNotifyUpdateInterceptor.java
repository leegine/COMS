head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecuteEndNotifyUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�o���I���ʒm�X�V�C���^�Z�v�^�N���X(WEB3IfoExecuteEndNotifyUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/2/27 ���c (SRA) �V�K�쐬
				 : 2006/7/19 ���@@�r (���u) �d�l�ύX�@@���f��525
                 : 2006/8/10 �юu�� (���u) �d�l�ύX  ���f��543
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�o���I���ʒm�X�V�C���^�Z�v�^)<BR>
 * �敨OP�o���I���ʒm�X�V�C���^�Z�v�^�N���X<BR>
 * @@author  ���c
 * @@version 1.0
 */
public class WEB3IfoExecuteEndNotifyUpdateInterceptor extends WEB3IfoUpdateInterceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoExecuteEndNotifyUpdateInterceptor.class);

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR> 
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j�@@super.mutate(IfoOrderActionParams)���R�[������B<BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g<BR>
     * �@@�������G���[���R�R�[�h<BR>
     * �@@�@@�p�����[�^.��������Params.�����G���[���R�R�[�h�Ɂh0000:����h���Z�b�g����B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * (OrderManagerPersistenceType�ɂĒ萔��`)<BR>
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * (OrderManagerPersistenceContext�ɂĒ萔��`)<BR>
     * @@param l_ifoOrderActionParams - (��������Params)<BR>
     * �����������ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return webbroker3.ifo.data.IfoOrderActionParams
     */
    public IfoOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderActionParams l_ifoOrderActionParams) 
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType, " +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "IfoOrderActionParams l_ifoOrderActionParams)";

        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderActionParams == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME));
            return l_ifoOrderActionParams;
        }

        // super.mutate(IfoOrderActionParams)���R�[������B 
        super.mutate(l_orderManagerPersistenceType,
            l_orderManagerPersistenceContext, l_ifoOrderActionParams);
        
        //�����G���[���R�R�[�h
        l_ifoOrderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderActionParams;
    }

    /**
     * (�敨OP�o���I���ʒm�X�V�C���^�Z�v�^�N���X)<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * @@return webbroker3.ifo.WEB3IfoExecuteEndNotifyUpdateInterceptor
     */
    public WEB3IfoExecuteEndNotifyUpdateInterceptor() 
    {

    }
}@
