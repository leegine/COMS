head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoAcceptedDecisionInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ���t�m��C���^�Z�v�^(WEB3RuitoAcceptedDecisionInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;

import webbroker3.util.WEB3LogUtility;

/**
 * �ݓ���t�m��C���^�Z�v�^<BR>
 */
public class WEB3RuitoAcceptedDecisionInterceptor
         extends WEB3RuitoDefaultRuitoOrderDecisionInterceptor
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoAcceptedDecisionInterceptor.class);

    /**
     * �����G���[���R�R�[�h<BR>
     */
    private String orderErrorReasonCode;

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40891614030C
     */
    public WEB3RuitoAcceptedDecisionInterceptor()
    {

    }

    /**
     * �imutate�̎����j<BR>
     * <BR>
     * �����E��������̒��ŁA�ݓ����������f�[�^�̍쐬�E�X�V���ɌĂ΂��B<BR>
     * <BR>
     * �P�j this.�����G���[���R�R�[�h���A<BR>
     * �����ݓ���������Params.�����G���[���R�R�[�h�ɐݒ肷��B<BR>
     * @@param l_persistenceType - �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_persistenceContext - 
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_actionParams - �i�����O�̗ݓ���������Params<BR>
     * @@return RuitoOrderActionParams
     * @@roseuid 408913A403E7
     */
    public RuitoOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderActionParams l_actionParams)
    {
        String STR_METHOD_NAME = "mutate(" +
            "OrderManagerPersistenceType l_persistenceType," +
            "OrderManagerPersistenceContext l_persistenceContext," +
            "RuitoOrderActionParams l_actionParams)";
        log.entering(STR_METHOD_NAME);
        //�P�j this.�����G���[���R�R�[�h���A�����ݓ������P��Params.
        //�����G���[���R�R�[�h�ɐݒ肷��B
        //this.orderErrorReasonCode = l_actionParams.getErrorReasonCode();        
        l_actionParams.setErrorReasonCode(this.getOrderErrorReasonCode());
        
        log.debug("l_actionParams.getErrorReasonCode()=" + l_actionParams.getErrorReasonCode());
        
        return l_actionParams;
    }

    /**
     * �imutate�̎����j<BR>
     * <BR>
     * �����E��������̒��ŁA�ݓ������P�ʃf�[�^�̍쐬�E�X�V���ɌĂ΂��B<BR>
     * <BR>
     * �P�j this.�����G���[���R�R�[�h���A<BR>
     *    �����ݓ������P��Params.�����G���[���R�R�[�h�ɐݒ肷��B<BR>
     * @@param l_persistenceType - �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_persistenceContext - 
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_unitParams - �i�����O�̗ݓ������P��Params<BR>
     * @@return RuitoOrderUnitParams
     * @@roseuid 408C5D080022
     */
    public RuitoOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderUnitParams l_unitParams)
    {
        String STR_METHOD_NAME = "mutate(" +
            "OrderManagerPersistenceType l_persistenceType," +
            "OrderManagerPersistenceContext l_persistenceContext," +
            "RuitoOrderUnitParams l_unitParams)";
            
        log.entering(STR_METHOD_NAME);
        log.debug("this.getOrderErrorReasonCode()=" + this.getOrderErrorReasonCode());
        //�P�j this.�����G���[���R�R�[�h���A
        //�����ݓ������P��Params.�����G���[���R�R�[�h�ɐݒ肷��B
        //this.orderErrorReasonCode = l_unitParams.getErrorReasonCode();        
        l_unitParams.setErrorReasonCode(this.getOrderErrorReasonCode());
        
        log.exiting(STR_METHOD_NAME);
        return l_unitParams;
    }

    /**
     * �����G���[���R�R�[�h�̐ݒ���s���B<BR>
     * @@param l_strErrorCode - �G���[�R�[�h<BR>
     * @@roseuid 408913F20109
     */
    public void setOrderErrorReasonCode(String l_strErrorCode)
    {
        log.debug("l_strErrorCode = " + l_strErrorCode);
        orderErrorReasonCode = l_strErrorCode;
    }

    /**
     * this.�����G���[���R�R�[�h��Ԃ��B<BR>
     * @@return String
     * @@roseuid 408913FF0251
     */
    public String getOrderErrorReasonCode()
    {
        return orderErrorReasonCode;
    }

//    /**
//     * @@param l_persistenceType
//     * @@param l_persistenceContext
//     * @@param l_executionParams
//     * @@return RuitoOrderExecutionParams
//     * @@roseuid 40C01A7D0127
//     */
//    public RuitoOrderExecutionParams setUpdatePrice(
//        OrderManagerPersistenceType l_persistenceType,
//        OrderManagerPersistenceContext l_persistenceContext,
//        RuitoOrderExecutionParams l_executionParams)
//    {
//        return l_executionParams;
//    }
//
//    /**
//     * @@param l_persistenceType
//     * @@param l_tableRow
//     * @@return com.fitechlabs.xtrade.kernel.data.BatchedQuery
//     * @@roseuid 40C01A7D0380
//     */
//    public BatchedQuery getQueryToExecute(
//        OrderManagerPersistenceType l_persistenceType,        
//        Class l_tableRow)
//    {
//        return null;
//    }
}
@
