head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOnPaymentCooperationCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���A�g����X�V�C���^�Z�v�^(WEB3AioInputOutputHistoryServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 ���r (���u) �V�K�쐬
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o���A�g����X�V�C���^�Z�v�^)<BR>
 * �o���A�g����X�V�C���^�Z�v�^�N���X
 * @@author ���r
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationCancelUpdateInterceptor extends WEB3AioCashoutCancelUpdateInterceptor 
{
    
    /**
     * �U�֋L�q
     */
    private String description;

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioOnPaymentCooperationCancelUpdateInterceptor.class);  
    /**
     * @@roseuid 41EC855D000F
     */
    public WEB3AioOnPaymentCooperationCancelUpdateInterceptor() 
    {
     
    }
    
    /**
     * (set�U�֋L�q)<BR>
     * �U�֋L�q���Z�b�g����B<BR>
     * @@param l_strDescription - ���ʃR�[�h
     * @@roseuid 41E4FF010178
     */
    public void setDescription(String l_strDescription) 
    {
        description = l_strDescription;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     *  �imutate���\�b�h�̎����j<BR>  
     *  �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>  
     * <BR>
     *  (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>  
     *  (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>  
     * <BR>
     * �v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>  
     * <BR>
     *  �y��Trade�z�⑫����.DB�X�V <BR> 
     *  �u�o�����_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR>  
     *<BR> 
     *  �P�j�U�֋L�q�ȊO�̊g�����ڂ̒l��ݒ肷��B<BR> 
     *<BR> 
     *   super�N���X�̍X�V�l�ݒ�()���\�b�h���R�[������B<BR> 
     *<BR> 
     *  �Q�j�U�֋L�q�̒l���Z�b�g����B<BR> 
     *<BR> 
     *   this.�U�֋L�q�̒l���p�����[�^.�����P��Params�̓����ڂɃZ�b�g����B<BR> 
     * <BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE <BR>
     *  �iOrderManagerPersistenceType�ɂĒ萔��`�B�j<BR>
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     *  �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_aioOrderUnitParams - (�����P��Params)<BR>
     *   �����P�ʂ̃p�����[�^�N���X
     * @@return AioOrderUnitParams
     * @@roseuid 416CAF1A02D2
     */    
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType, 
        OrderManagerPersistenceContext l_orderManagerPersistenceContext, 
        AioOrderUnitParams l_aioOrderUnitParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(" +
            "OrderManagerPersistenceType l_orderManagerPersistenceType, " +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "AioOrderUnitParams l_aioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_aioOrderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        //�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B
        //�y��Trade�z�⑫����.DB�X�V �u�o�����_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB
        
        //super�N���X�̍X�V�l�ݒ�()���\�b�h���R�[������   
        super.mutate(l_orderManagerPersistenceType,l_orderManagerPersistenceContext,l_aioOrderUnitParams);
        
        // �Q�j�U�֋L�q�̒l���Z�b�g����B
        //�U�֋L�q�̒l���Z�b�g����
        //this.�U�֋L�q�̒l���p�����[�^.�����P��Params�̓����ڂɃZ�b�g����B
        l_aioOrderUnitParams.setDescription(this.description);
        
        log.exiting(STR_METHOD_NAME);   
        return l_aioOrderUnitParams;     
    }
}
@
