head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashTransAcceptUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o����t�X�V�C���^�Z�v�^(WEB3AioCashTransAcceptUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���E (���u) �V�K�쐬      
                   2004/10/23 ������ (���u) ���r���[             
*/
package webbroker3.aio;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;


/**
 * (���o����t�X�V�C���^�Z�v�^)<BR>
 * ���o����t�X�V�C���^�Z�v�^�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashTransAcceptUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{
    
    /**
     * (�G���[�R�[�h)
     */
    private String errorCode;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransAcceptUpdateInterceptor.class); 
    
    /**
     * (���o����t�X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �����̃G���[�R�[�h�����g�̃v���p�e�B�ɃZ�b�g�A���g�̃C���X�^���X��ԋp����B
     * @@param l_strErrorCode - (�G���[�R�[�h)
     * @@return webbroker3.aio.WEB3AioCashTransAcceptUpdateInterceptor
     * @@roseuid 40FC88F602AD
     */
    public WEB3AioCashTransAcceptUpdateInterceptor(String l_strErrorCode) 
    {
        final String STR_METHOD_NAME = "WEB3AioCashTransAcceptUpdateInterceptor" +
            "(String l_strErrorCode)";
        log.entering(STR_METHOD_NAME);

        this.errorCode = l_strErrorCode;
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * �����P��Params�Ɋg������(*)��ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB <BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �X�V���e�́A�u���o����t_�����P�ʃe�[�u��.xls�v�� <BR>
     * �u�i���o����t[��t����]�j�����P�ʃe�[�u���v�V�[�g�A <BR>
     * �u�i���o����t[��t�G���[]�j�����P�ʃe�[�u���v�V�[�g�Q�ƁB<BR>
     * @@param l_updateType - ((�X�V�^�C�v)<BR>)<BR>
     * INSERT�܂��́AUPDATE<BR>
     * <BR>
     * �iOrderManagerPersistenceType�ɂĒ萔��`�B�j
     * @@param l_process - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * �����P�ʂ̃p�����[�^�N���X
     * @@return AioOrderUnitParams
     * @@roseuid 40FC896602CC
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process, AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType l_updateType," +
            "OrderManagerPersistenceContext l_process, AioOrderUnitParams l_orderUnitParams) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        l_orderUnitParams.setErrorReasonCode(this.errorCode);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
}
@
