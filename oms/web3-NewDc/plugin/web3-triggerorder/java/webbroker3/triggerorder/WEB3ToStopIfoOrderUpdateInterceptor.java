head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopIfoOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �t�w�l�����敨OP�����X�V�C���^�Z�v�^�N���X(WEB3ToStopIfoOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/2/17 ���c (SRA) �V�K�쐬
*/
package webbroker3.triggerorder;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.ifo.WEB3IfoUpdateInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * (�t�w�l�����敨OP�����X�V�C���^�Z�v�^)<BR>
 * �t�w�l�����敨OP�����X�V�C���^�Z�v�^�N���X<BR>
 * @@author  ���c
 * @@version 1.0
 */
public class WEB3ToStopIfoOrderUpdateInterceptor extends WEB3IfoUpdateInterceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3ToStopIfoOrderUpdateInterceptor.class);

    /**
     * �����G���[���R�R�[�h
     */
    private String l_errorReasonCode;

    /**
     * (�t�w�l�����敨OP�����X�V�C���^�Z�v�^)<BR>
     * <BR>
     * �R���X�g���N�^<BR>
     * �C���X�^���X�𐶐����A���������g�̃v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_errorReasonCode - (�����G���[���R�R�[�h)<BR>
     * �����P�ʃe�[�u���d�l.xls<BR> 
     * �u�i�����P�ʃe�[�u���⑫�j�����G���[���R�R�[�h�v�V�[�g�Q�ƁB<BR> 
     * @@return webbroker3.triggerorder.WEB3ToStopIfoOrderUpdateInterceptor
     */
    public WEB3ToStopIfoOrderUpdateInterceptor(String l_errorReasonCode) 
    {
        this.l_errorReasonCode = l_errorReasonCode;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * <BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����̒����P��Params�Ɋg������(*)��ݒ肵�ԋp����B<BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * �P�j �g�����ڃZ�b�g<BR>
     * �X�V���e�́A�u�t�w�l��������(NG)_�敨OP�����P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * �iOrderManagerPersistenceType�ɂĒ萔��`�j<BR>
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_ifoOrderUnitParams - (�����P�ʍs�I�u�W�F�N�g)<BR>
     * �敨OP�����P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderUnitParams l_ifoOrderUnitParams) 
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType, " +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "IfoOrderUnitParams l_ifoOrderUnitParams)";

        log.entering(STR_METHOD_NAME);
        if(l_ifoOrderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        //�����P��.���N�G�X�g�^�C�v�ցi8:�������s�j��ݒ�
        l_ifoOrderUnitParams.setRequestType(WEB3RequestTypeDef.ORDER_FAILURE);

        // �����G���[���R�R�[�h��ݒ�
        l_ifoOrderUnitParams.setErrorReasonCode(this.getErrorReasonCode());

        log.exiting(STR_METHOD_NAME);

        return l_ifoOrderUnitParams;
    }

    /**
     * (get�����G���[���R�R�[�h)<BR>
     * <BR>
     * this.�����G���[���R�R�[�h��ԋp����B<BR>
     * @@return String
     */
    private String getErrorReasonCode()
    {
        return this.l_errorReasonCode;
    }
}
@
