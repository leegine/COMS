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
filename	WEB3RuitoDefaultRuitoOrderDecisionInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �f�t�H���g�ݓ������m��C���^�Z�v�^(WEB3RuitoDefaultRuitoOrderDecisionInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderExecutionParams;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;

/**
 * �f�t�H���g�ݓ������m��C���^�Z�v�^<BR> 
 * <BR>
 * �V�K�����A����̗ݓ������m��C���^�Z�v�^�̕W��������K�肵���N���X�B<BR>
 */
public abstract class WEB3RuitoDefaultRuitoOrderDecisionInterceptor
    implements RuitoOrderManagerPersistenceEventInterceptor 
{

    /**
     * �imutate�̎����j<BR>
     * <BR>
     * �����E��������̒��ŁA�ݓ������P�ʃf�[�^�̍쐬�E�X�V���ɌĂ΂��B<BR>
     * <BR>
     * ����.�ݓ������P��Params��Ԃ��B<BR>
     * @@param l_persistenceType - �f�[�^�̍X�V�܂��͑}������<BR>
     * �ĂԂ��ǂ����w��<BR>
     * @@param l_persistenceContext - �Ăяo�����̃R���e�L�X�g���w��B<BR>
     * �Ⴆ�Ό����������ȂǁB<BR>
     * @@param l__unitParams - �i�����O�̗ݓ������P��Params<BR>
     * 
     * @@return RuitoOrderUnitParams
     * @@roseuid 4084C0A803B8
     */
    public RuitoOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderUnitParams l_unitParams)
    {
        return l_unitParams;
    }

    /**
     * �imutate�̎����j<BR>
     * <BR>
     * ��菈���̒��ŁA�ݓ������P�ʃf�[�^�̍쐬�E�X�V���ɌĂ΂��B<BR>
     * <BR>
     * ����.�ݓ����Params��Ԃ��B<BR>
     * @@param l_persistenceType - �f�[�^�̍X�V�܂��͑}������<BR>
     * �ĂԂ��ǂ����w��<BR>
     * @@param l_persistenceContext - �Ăяo�����̃R���e�L�X�g���w��B<BR>
     * �Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_executionParams - �i�����O�̗ݓ����Params<BR>
     * @@return RuitoOrderExecutionParams
     * @@roseuid 4084C0A803E7
     */
    public RuitoOrderExecutionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderExecutionParams l_executionParams)
    {
        return l_executionParams;
    }

    /**
     * �imutate�̎����j<BR>
     * <BR>
     * �����E��������̒��ŁA�ݓ����������f�[�^�̍쐬�E�X�V���ɌĂ΂��B<BR>
     * <BR>
     * ����.�ݓ���������Params��Ԃ��B<BR>
     * @@param l_persistenceType - �f�[�^�̍X�V�܂��͑}������<BR>
     * �ĂԂ��ǂ����w��<BR>
     * @@param l_persistenceContext - �Ăяo�����̃R���e�L�X�g���w��B<BR>
     * �Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_actionParams - �i�����O�̗ݓ���������Params<BR>
     * @@return RuitoOrderActionParams
     * @@roseuid 4084C0A9005D
     */
    public RuitoOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderActionParams l_actionParams)
    {
        return l_actionParams;
    }

    /**
     * �igetQueryToExecute�̎����j<BR>
     * <BR>
     * null��Ԃ��B<BR>
     * @@param l_persistenceType - �f�[�^�̍X�V�܂��͑}������<BR>
     * �ĂԂ��ǂ����w��<BR>
     * @@param l_tableRow - �ǂ̃e�[�u�����X�V���邩�w��<BR>
     * @@return com.fitechlabs.xtrade.kernel.data.BatchedQuery
     * @@roseuid 4084C0A9002E
     */
    public BatchedQuery getQueryToExecute(
        OrderManagerPersistenceType l_persistenceType,
        Class l_tableRow)
    {
        return null;
    }

//    /**
//    	* @@param arg0
//    	* @@param arg1
//    	* @@param arg2
//    	* @@return RuitoOrderUnitParams
//    	* @@roseuid 40C481D20177
//    	*/
//    public RuitoOrderUnitParams mutate(
//        OrderManagerPersistenceType arg0,
//        OrderManagerPersistenceContext arg1,
//        RuitoOrderUnitParams arg2)
//    {
//        //����.�ݓ����Params��Ԃ��B
//        return arg2;        
//    }
//
//    /**
//    	* @@param arg0
//    	* @@param arg1
//    	* @@param arg2
//    	* @@return RuitoOrderExecutionParams
//    	* @@roseuid 40C481D4006D
//    	*/
//    public RuitoOrderExecutionParams mutate(
//        OrderManagerPersistenceType arg0,
//        OrderManagerPersistenceContext arg1,
//        RuitoOrderExecutionParams arg2)
//    {       
//        //����.�ݓ����Params��Ԃ��B
//        return arg2;
//    }
//
//    /**
//    	* @@param arg0
//    	* @@param arg1
//    	* @@param arg2
//    	* @@return RuitoOrderActionParams
//    	* @@roseuid 40C481D5032C
//    	*/
//    public RuitoOrderActionParams mutate(
//        OrderManagerPersistenceType arg0,
//        OrderManagerPersistenceContext arg1,
//        RuitoOrderActionParams arg2)
//    {
//        //����.�ݓ����Params��Ԃ��B
//        return arg2;        
//    }
}
@
