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
filename	WEB3RuitoCancelDescitionInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ�����m��C���^�Z�v�^(WEB3RuitoCancelDescitionInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionParams;

/**
 * �ݓ�����m��C���^�Z�v�^<BR>
 */
public class WEB3RuitoCancelDescitionInterceptor
    extends WEB3RuitoDefaultRuitoOrderDecisionInterceptor
{

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 4091A1D401CA
     */
    public WEB3RuitoCancelDescitionInterceptor()
    {

    }

    /**
     * �imutate�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����ŗ^����ꂽ�ݓ���������Params�ɒl��ݒ肵�A<BR>
     *    �ݓ���������Params��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�����G���[���R�R�[�h�̐ݒ���s���B<BR>
     * �@@�|�ݓ���������Params.set�����G���[���R�R�[�h()���R�[�����A<BR>
     *      �����G���[���R�R�[�h�̐ݒ���s���B<BR>
     * �@@�@@�mset�����G���[���R�R�[�h�ɓn���p�����^�n<BR>
     * �@@�@@�@@�����G���[���R�R�[�h�F null<BR>
     * <BR>
     * �Q�j�@@�����ŗ^����ꂽ�ݓ���������Params��Ԃ��B<BR>
     * @@param l_persistenceType - �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_persistenceContext - 
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������Ȃ�<BR>
     * @@param l_actionParams - �i�����O�̗ݓ���������Params<BR>
     * @@return RuitoOrderActionParams
     * @@roseuid 4091A2030005
     */
    public RuitoOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderActionParams l_actionParams)
    {
        //�P�j�@@�����G���[���R�R�[�h�̐ݒ���s���B
        l_actionParams.setErrorReasonCode(null);
        //�Q�j�@@�����ŗ^����ꂽ�ݓ���������Params��Ԃ��B       
        return l_actionParams;
    }

//    /**
//     * @@param l_persistenceType
//     * @@param l_persistenceContext
//     * @@param l_unitParams
//     * @@return RuitoOrderUnitParams
//     * @@roseuid 40C01AA20198
//     */
//    public RuitoOrderUnitParams setUpdatePrice(
//        OrderManagerPersistenceType l_persistenceType,
//        OrderManagerPersistenceContext l_persistenceContext,
//        RuitoOrderUnitParams l_unitParams)
//    {
//        l_unitParams.setErrorReasonCode(null);
//        return l_unitParams;
//    }
//
//    /**
//     * @@param l_persistenceType
//     * @@param l_persistenceContext
//     * @@param l_executionParams
//     * @@return RuitoOrderExecutionParams
//     * @@roseuid 40C01AA203D3
//     */
//    public RuitoOrderExecutionParams setUpdatePrice(
//        OrderManagerPersistenceType l_persistenceType,
//        OrderManagerPersistenceContext l_persistenceContext,
//        RuitoOrderExecutionParams l_executionParams)
//    {
//        return l_executionParams;
//    }

//    /**
//     * @@param l_persistenceType
//     * @@param l_tableRow
//     * @@return com.fitechlabs.xtrade.kernel.data.BatchedQuery
//     * @@roseuid 40C01AA3021C
//     */
//    public BatchedQuery getQueryToExecute(
//        OrderManagerPersistenceType l_persistenceType,
//        Class l_tableRow)
//    {
//        return null;
//    }
}
@
