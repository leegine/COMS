head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteChangeCommonInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Җ��ύX�C���^�Z�v�^(WEB3AdminBondExecuteChangeCommonInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����� (���u) �V�K�쐬
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
 * (���Ǘ��Җ��ύX�C���^�Z�v�^)<BR>
 * ���Ǘ��Җ��ύX�C���^�Z�v�^�N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0 
 */
public class WEB3AdminBondExecuteChangeCommonInterceptor implements BondOrderManagerPersistenceEventInterceptor
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteChangeCommonInterceptor.class);
   
    /**
     * (���Ǘ��Җ�����X�V�C���^�Z�v�^)<BR>
     * ���Ǘ��Җ�����X�V�C���^�Z�v�^<BR>
     */
    private WEB3AdminBondExecuteCancelUpdateInterceptor adminBondExecuteCancelUpdateInterceptor;
    
    /**
     * (���Ǘ��Җ��X�V�C���^�Z�v�^)<BR>
     * ���Ǘ��Җ��X�V�C���^�Z�v�^<BR>
     */
    private WEB3AdminBondExecuteUpdateInterceptor adminBondExecuteUpdateInterceptor;
    
    /**
     * (���Ǘ��Җ��ύX�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44D6A1D7010B
     */
    public WEB3AdminBondExecuteChangeCommonInterceptor() 
    {
     
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * �P�j���Ǘ��Җ�����X�V�C���^�Z�v�^ != null�̏ꍇ�A<BR>
     * �@@�@@���Ǘ��Җ�����X�V�C���^�Z�v�^.(�����P��)�X�V�l�ݒ�()���R�[�����A<BR>
     * �@@�@@�������P��Params���擾�B<BR>
     * �@@[(�����P��)�X�V�l�ݒ�()�̈���]<BR>
     * �@@�@@�@@DB�X�V�^�C�v =�@@����.OrderManagerPersistenceType<BR>
     * �@@�@@�@@�R���e�L�X�g = ����.OrderManagerPersistenceContext<BR>
     * �@@�@@�@@�����P��Params = ����.BondOrderUnitParams<BR>
     * <BR>
     * �Q�jthis.���Ǘ��Җ��X�V�C���^�Z�v�^.(�����P��)�X�V�l�ݒ�()���R�[�����A<BR>
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
     * @@roseuid 44D6A1D400CF
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType, 
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        BondOrderUnitParams l_orderUnitParams = new BondOrderUnitParams();
        //�P�j���Ǘ��Җ�����X�V�C���^�Z�v�^ != null�̏ꍇ�A
        if (this.adminBondExecuteCancelUpdateInterceptor != null)
        {
            l_orderUnitParams = 
                this.adminBondExecuteCancelUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        }
        
        //�Q�jthis.���Ǘ��Җ��X�V�C���^�Z�v�^.(�����P��)�X�V�l�ݒ�()���R�[����
        l_orderUnitParams = 
            this.adminBondExecuteUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (�i���������j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * �P�j���Ǘ��Җ�����X�V�C���^�Z�v�^ != null�̏ꍇ�A<BR>
     * �@@�@@���Ǘ��Җ�����X�V�C���^�Z�v�^.(��������)�X�V�l�ݒ�()���R�[�����A<BR>
     * �@@�@@����������Params���擾�B<BR>
     * �@@[(��������)�X�V�l�ݒ�()�̈���]<BR>
     * �@@�@@�@@DB�X�V�^�C�v =�@@����.OrderManagerPersistenceType<BR>
     * �@@�@@�@@�R���e�L�X�g = ����.OrderManagerPersistenceContext<BR>
     * �@@�@@�@@��������Params = ����.BondOrderActionParams<BR>
     * <BR>
     * �Q�jthis.���Ǘ��Җ��X�V�C���^�Z�v�^.(��������)�X�V�l�ݒ�()���R�[�����A<BR>
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
     * @@roseuid 44D7F7B8025B
     */
    public BondOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType, 
        OrderManagerPersistenceContext l_context, 
        BondOrderActionParams l_params) 
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        BondOrderActionParams l_orderActionParams = new BondOrderActionParams();
        //�P�j���Ǘ��Җ�����X�V�C���^�Z�v�^ != null�̏ꍇ�A
        if (this.adminBondExecuteCancelUpdateInterceptor != null)
        {
            l_orderActionParams = 
                this.adminBondExecuteCancelUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        }
        
        //�Q�jthis.���Ǘ��Җ��X�V�C���^�Z�v�^.(�����P��)�X�V�l�ݒ�()���R�[����
        l_orderActionParams = 
            this.adminBondExecuteUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        log.exiting(STR_METHOD_NAME);
        return l_orderActionParams;
    }
    
    /**
     * (�i���j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * �P�j���Ǘ��Җ�����X�V�C���^�Z�v�^ != null�̏ꍇ�A<BR>
     * �@@�@@���Ǘ��Җ�����X�V�C���^�Z�v�^.�i���j�X�V�l�ݒ�()���R�[�����A<BR>
     * �@@�@@�����Params���擾�B<BR>
     * �@@[(���)�X�V�l�ݒ�()�̈���]<BR>
     * �@@�@@�@@DB�X�V�^�C�v =�@@����.OrderManagerPersistenceType<BR>
     * �@@�@@�@@�R���e�L�X�g = ����.OrderManagerPersistenceContext<BR>
     * �@@�@@�@@���Params = ����.BondOrderExecutionParams<BR>
     * <BR>
     * �Q�jthis.���Ǘ��Җ��X�V�C���^�Z�v�^.�i���j�X�V�l�ݒ�()���R�[�����A<BR>
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
     * @@roseuid 44D7FA450222
     */
    public BondOrderExecutionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context, 
        BondOrderExecutionParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderExecutionParams)";
        log.entering(STR_METHOD_NAME);
        
        BondOrderExecutionParams l_orderExecutionParams = new BondOrderExecutionParams();
        //�P�j���Ǘ��Җ�����X�V�C���^�Z�v�^ != null�̏ꍇ�A
        if (this.adminBondExecuteCancelUpdateInterceptor != null)
        {
            l_orderExecutionParams = 
                this.adminBondExecuteCancelUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        }
        
        //�Q�jthis.���Ǘ��Җ��X�V�C���^�Z�v�^.(�����P��)�X�V�l�ݒ�()���R�[����
        l_orderExecutionParams = 
            this.adminBondExecuteUpdateInterceptor.mutate(l_persistenceType, l_context, l_params);            
        
        log.exiting(STR_METHOD_NAME);
        return l_orderExecutionParams;
    }
    
    /**
     * (get���Ǘ��Җ�����X�V�C���^�Z�v�^)<BR>
     * ���Ǘ��Җ�����X�V�C���^�Z�v�^��ԋp����<BR>
     * @@return WEB3AdminBondExecuteCancelUpdateInterceptor
     * @@roseuid 44D6D876006D
     */
    public WEB3AdminBondExecuteCancelUpdateInterceptor getBondExecuteCancelUpdateInterceptor() 
    {
        return this.adminBondExecuteCancelUpdateInterceptor;
    }
    
    /**
     * (set���Ǘ��Җ�����X�V�C���^�Z�v�^)<BR>
     * ���Ǘ��Җ�����X�V�C���^�Z�v�^���Z�b�g����<BR>
     * @@param l_executeCancelUpdateInterceptor - (���Ǘ��Җ�����X�V�C���^�Z�v�^)<BR>
     * ���Ǘ��Җ�����X�V�C���^�Z�v�^<BR>
     * @@roseuid 44D6D95A00A2
     */
    public void setBondExecuteCancelUpdateInterceptor(
        WEB3AdminBondExecuteCancelUpdateInterceptor l_executeCancelUpdateInterceptor) 
    {
        this.adminBondExecuteCancelUpdateInterceptor = l_executeCancelUpdateInterceptor;
    }
    
    /**
     * (get���Ǘ��Җ��X�V�C���^�Z�v�^)<BR>
     * ���Ǘ��Җ��X�V�C���^�Z�v�^��ԋp����<BR>
     * @@return WEB3AdminBondExecuteCancelUpdateInterceptor
     * @@roseuid 44D6D9BA0017
     */
    public WEB3AdminBondExecuteUpdateInterceptor getBondExecuteUpdateInterceptor() 
    {
        return this.adminBondExecuteUpdateInterceptor;
    }
    
    /**
     * (set���Ǘ��Җ��X�V�C���^�Z�v�^)<BR>
     * ���Ǘ��Җ��X�V�C���^�Z�v�^���Z�b�g����<BR>
     * @@param l_executeUpdateInterceptor - (�Ǘ��ҍ����X�V�C���^�Z�v�^)<BR>
     * �Ǘ��ҍ����X�V�C���^�Z�v�^�C���X�^���X<BR>
     * @@roseuid 44D6DA1F0389
     */
    public void setBondExecuteUpdateInterceptor(WEB3AdminBondExecuteUpdateInterceptor l_executeUpdateInterceptor) 
    {
        this.adminBondExecuteUpdateInterceptor = l_executeUpdateInterceptor;
    }

    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType arg0, Class arg1)
    {
        return null;
    }

}
@
