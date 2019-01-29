head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSpsecTransferForceUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������U�֋����X�V�C���^�Z�v�^ (WEB3AioSpsecTransferForceUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/18 ��O�� (���u) �V�K�쐬 
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * (��������U�֋����X�V�C���^�Z�v�^)<BR>
 * ��������U�֋����X�V�C���^�Z�v�^ �N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceUpdateInterceptor extends WEB3AioSecurityTransferForceUpdateInterceptor 
{
    /**
    * ���O���[�e�B���e�B
    */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioSpsecTransferForceUpdateInterceptor.class);
        
    /**
     * (�~�j���敪)<BR>
     * �~�j���敪
     */
    private String miniStockDiv;
    
    /**
     * @@roseuid 41B0458B01F4
     */
    public WEB3AioSpsecTransferForceUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�،��U�֋����X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�����̓��o���������e���v���p�e�B�ɃZ�b�g����B
     * @@param l_securityTransferForceUpdateInterceptor - ���o���������e�I�u�W�F�N�g
     * @@return webbroker3.aio.WEB3AioSecurityTransferForceUpdateInterceptor
     * @@roseuid 416CAF1A02D0
     */
    public WEB3AioSpsecTransferForceUpdateInterceptor(WEB3AioNewOrderSpec l_aioNewOrderSpec) 
    {      
        //�C���X�^���X�𐶐����A�����̓��o���������e���v���p�e�B�ɃZ�b�g����B
        this.cashTransOrderSpec = l_aioNewOrderSpec; 
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j 
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     *(*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     *(*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB <BR>
     * <BR>
     *�@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * <BR>
     *�P�j�~�j���敪�ȊO�̊g�����ڂ̒l��ݒ肷��B <BR>
     * <BR>
     *super�N���X�̍X�V�l�ݒ�()���\�b�h���R�[������B <BR>
     * <BR>
     *�Q�j�~�j���敪�̒l���Z�b�g����B <BR>
     * <BR>
     *this.�~�j���敪�̒l���p�����[�^.�����P��Params�̓����ڂɃZ�b�g����B<BR>
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
        String l_strMethodName = 
            "mutate(" +
            "OrderManagerPersistenceType l_orderManagerPersistenceType, " +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "AioOrderUnitParams l_aioOrderUnitParams)";
        log.entering(l_strMethodName);
        
        if (l_aioOrderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);            
        }
        
        //�P�j�~�j���敪�ȊO�̊g�����ڂ̒l��ݒ肷��B 
        //super�N���X�̍X�V�l�ݒ�()���\�b�h���R�[������B 
        super.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext, 
                l_aioOrderUnitParams);
        
        //�Q�j�~�j���敪�̒l���Z�b�g����B 
        //this.�~�j���敪�̒l���p�����[�^.�����P��Params�̓����ڂɃZ�b�g����B
        l_aioOrderUnitParams.setMiniStockDiv(this.miniStockDiv);
        
        log.exiting(l_strMethodName);
        
        return l_aioOrderUnitParams;
    }
    
    /**
     * (set�~�j���敪)<BR>
     * �~�j���敪���Z�b�g����B
     * @@param l_strMiniStockDiv - �~�j���敪
     * @@roseuid 416CAF1A02D6
     */
    public void setMiniStockDiv(String l_strMiniStockDiv) 
    {       
        this.miniStockDiv = l_strMiniStockDiv;     
    }   
}
@
