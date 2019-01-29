head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderAndExecuteCommonInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��ҐV�K�����̓C���^�Z�v�^(WEB3AdminBondOrderAndExecuteCommonInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/15 �V���h�O (FTL) �V�K�쐬
*/

package webbroker3.bd;

import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;


/**
 * (���Ǘ��ҐV�K�����̓C���^�Z�v�^)<BR>
 * ���Ǘ��ҐV�K�����̓C���^�Z�v�^�N���X<BR>
 * 
 * @@author �V���h�O (FTL)
 * @@version 1.0 
 */
public class WEB3AdminBondOrderAndExecuteCommonInterceptor implements BondOrderManagerPersistenceEventInterceptor
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderAndExecuteCommonInterceptor.class);
   
    /**
     * (���Ǘ��ҐV�K�����X�V�C���^�Z�v�^)<BR>
     * ���Ǘ��ҐV�K�����X�V�C���^�Z�v�^<BR>
     */
    private WEB3AdminBondNewOrderUpdateInterceptor adminBondNewOrderUpdateInterceptor;
    
    /**
     * (���Ǘ��ҐV�K���X�V�C���^�Z�v�^)<BR>
     * ���Ǘ��ҐV�K���X�V�C���^�Z�v�^<BR>
     */
    private WEB3AdminBondNewExecuteUpdateInterceptor adminBondNewExecuteUpdateInterceptor;
    
    /**
     * (���Ǘ��ҐV�K�����̓C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * 
     */
    public WEB3AdminBondOrderAndExecuteCommonInterceptor() 
    {
     
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * �P�j���Ǘ��ҐV�K�����X�V�C���^�Z�v�^.(�����P��)�X�V�l�ݒ�()���R�[�����A<BR>
     * �@@�@@�������P��Params���擾�B<BR>
     * �@@[(�����P��)�X�V�l�ݒ�()�̈���]<BR>
     * �@@�@@�@@DB�X�V�^�C�v =�@@����.OrderManagerPersistenceType<BR>
     * �@@�@@�@@�R���e�L�X�g = ����.OrderManagerPersistenceContext<BR>
     * �@@�@@�@@�����P��Params = ����.BondOrderUnitParams<BR>
     * <BR>
     * �Q�j���Ǘ��ҐV�K���X�V�C���^�Z�v�^.(�����P��)�X�V�l�ݒ�()���R�[�����A<BR>
     * �@@�@@�������P��Params���擾�B<BR>
     * �@@[(�����P��)�X�V�l�ݒ�()�̈���]<BR>
     * �@@�@@�@@DB�X�V�^�C�v =�@@����.OrderManagerPersistenceType<BR>
     * �@@�@@�@@�R���e�L�X�g = ����.OrderManagerPersistenceContext<BR>
     * �@@�@@�@@�����P��Params = ����.BondOrderUnitParams<BR>
     * <BR>
     * �R�j�P�j�A�Q�j���s��̒����P��Params��Ԃ��B <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * 
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType, 
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���Ǘ��ҐV�K�����X�V�C���^�Z�v�^.(�����P��)�X�V�l�ݒ�()���R�[����
        l_params = 
                this.adminBondNewOrderUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        //�Q�j���Ǘ��ҐV�K���X�V�C���^�Z�v�^.(�����P��)�X�V�l�ݒ�()���R�[����
        l_params = 
            this.adminBondNewExecuteUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (�i���������j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * �P�j���Ǘ��ҐV�K�����X�V�C���^�Z�v�^.(��������)�X�V�l�ݒ�()���R�[�����A<BR>
     * �@@�@@����������Params���擾�B<BR>
     * �@@[(��������)�X�V�l�ݒ�()�̈���]<BR>
     * �@@�@@�@@DB�X�V�^�C�v =�@@����.OrderManagerPersistenceType<BR>
     * �@@�@@�@@�R���e�L�X�g = ����.OrderManagerPersistenceContext<BR>
     * �@@�@@�@@��������Params = ����.BondOrderActionParams<BR>
     * <BR>
     * �Q�j���Ǘ��ҐV�K���X�V�C���^�Z�v�^.(��������)�X�V�l�ݒ�()���R�[�����A<BR>
     * �@@�@@����������Params���擾�B<BR>
     * �@@[(��������)�X�V�l�ݒ�()�̈���]<BR>
     * �@@�@@�@@DB�X�V�^�C�v =�@@����.OrderManagerPersistenceType<BR>
     * �@@�@@�@@�R���e�L�X�g = ����.OrderManagerPersistenceContext<BR>
     * �@@�@@�@@��������Params = ����.BondOrderActionParams<BR>
     * <BR>
     * �R�j�P�j�A�Q�j���s��̒�������Params��Ԃ��B <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderActionParams
     * 
     */
    public BondOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType, 
        OrderManagerPersistenceContext l_context, 
        BondOrderActionParams l_params) 
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���Ǘ��ҐV�K�����X�V�C���^�Z�v�^.(��������)�X�V�l�ݒ�()���R�[����
        l_params = 
            this.adminBondNewOrderUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        //�Q�j���Ǘ��ҐV�K���X�V�C���^�Z�v�^.(��������)�X�V�l�ݒ�()���R�[����
        l_params = 
            this.adminBondNewExecuteUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (�i���j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * �P�j���Ǘ��ҐV�K�����X�V�C���^�Z�v�^.�i���j�X�V�l�ݒ�()���R�[�����A<BR>
     * �@@�@@�����Params���擾�B<BR>
     * �@@[(���)�X�V�l�ݒ�()�̈���]<BR>
     * �@@�@@�@@DB�X�V�^�C�v =�@@����.OrderManagerPersistenceType<BR>
     * �@@�@@�@@�R���e�L�X�g = ����.OrderManagerPersistenceContext<BR>
     * �@@�@@�@@���Params = ����.BondOrderExecutionParams<BR>
     * <BR>
     * �Q�j���Ǘ��ҐV�K���X�V�C���^�Z�v�^.�i���j�X�V�l�ݒ�()���R�[�����A<BR>
     * �@@�@@�����Params���擾�B<BR>
     * �@@[(���)�X�V�l�ݒ�()�̈���]<BR>
     * �@@�@@�@@DB�X�V�^�C�v =�@@����.OrderManagerPersistenceType<BR>
     * �@@�@@�@@�R���e�L�X�g = ����.OrderManagerPersistenceContext<BR>
     * �@@�@@�@@���Params = ����.BondOrderExecutionParams<BR>
     * <BR>
     * �R�j�P�j�A�Q�j���s��̍����Params��Ԃ��B <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderExecutionParams
     * 
     */
    public BondOrderExecutionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context, 
        BondOrderExecutionParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderExecutionParams)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���Ǘ��ҐV�K�����X�V�C���^�Z�v�^.(���)�X�V�l�ݒ�()���R�[����
        l_params = 
                this.adminBondNewOrderUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        //�Q�j���Ǘ��ҐV�K���X�V�C���^�Z�v�^.(���)�X�V�l�ݒ�()���R�[����
        l_params = 
            this.adminBondNewExecuteUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (get���Ǘ��ҐV�K�����X�V�C���^�Z�v�^)<BR>
     * ���Ǘ��ҐV�K�����X�V�C���^�Z�v�^��ԋp����<BR>
     * @@return WEB3AdminBondNewOrderUpdateInterceptor
     * 
     */
    public WEB3AdminBondNewOrderUpdateInterceptor getBondNewOrderUpdateInterceptor() 
    {
        return this.adminBondNewOrderUpdateInterceptor;
    }
    
    /**
     * (set���Ǘ��ҐV�K�����X�V�C���^�Z�v�^)<BR>
     * ���Ǘ��ҐV�K�����X�V�C���^�Z�v�^���Z�b�g����<BR>
     * @@param l_newOrderUpdateInterceptor - (���Ǘ��ҐV�K�����X�V�C���^�Z�v�^)<BR>
     * ���Ǘ��ҐV�K�����X�V�C���^�Z�v�^<BR>
     * 
     */
    public void setBondNewOrderUpdateInterceptor(
        WEB3AdminBondNewOrderUpdateInterceptor l_newOrderUpdateInterceptor) 
    {
        this.adminBondNewOrderUpdateInterceptor = l_newOrderUpdateInterceptor;
    }
    
    /**
     * (get���Ǘ��ҐV�K���X�V�C���^�Z�v�^)<BR>
     * ���Ǘ��ҐV�K���X�V�C���^�Z�v�^��ԋp����<BR>
     * @@return WEB3AdminBondNewExecuteUpdateInterceptor
     * 
     */
    public WEB3AdminBondNewExecuteUpdateInterceptor getBondNewExecuteUpdateInterceptor() 
    {
        return this.adminBondNewExecuteUpdateInterceptor;
    }
    
    /**
     * (set���Ǘ��ҐV�K���X�V�C���^�Z�v�^)<BR>
     * ���Ǘ��ҐV�K���X�V�C���^�Z�v�^���Z�b�g����<BR>
     * @@param l_newExecuteUpdateInterceptor - (�Ǘ��ҍ����X�V�C���^�Z�v�^)<BR>
     * �Ǘ��ҍ����X�V�C���^�Z�v�^<BR>
     * 
     */
    public void setBondNewExecuteUpdateInterceptor(WEB3AdminBondNewExecuteUpdateInterceptor l_newExecuteUpdateInterceptor) 
    {
        this.adminBondNewExecuteUpdateInterceptor = l_newExecuteUpdateInterceptor;
    }

    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType arg0, Class arg1)
    {
        return null;
    }

}
@
