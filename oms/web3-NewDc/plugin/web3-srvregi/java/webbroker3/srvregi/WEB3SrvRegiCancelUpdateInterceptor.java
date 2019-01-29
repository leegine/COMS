head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p����X�V�C���^�Z�v�^(WEB3SrvRegiCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.aio.WEB3AioCashTransUpdateInterceptor;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p����X�V�C���^�Z�v�^)<BR>
 * �T�[�r�X���p����X�V�C���^�Z�v�^�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3SrvRegiCancelUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{

    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3SrvRegiCancelUpdateInterceptor.class);

    /**
     * (�T�[�r�X���p����X�V�C���^�Z�v�^)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 4100D63A0212
     */
    public WEB3SrvRegiCancelUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * �A�Z�b�g�U�֎�������P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB <BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�A�Z�b�g�U�֎�������P��Params��<BR>
     * �@@�g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�T�[�r�X���p�\�����_�����P�ʃe�[�u���d�l.xls�v<BR>
     * �u�T�[�r�X���p�Ǘ��ҁE�ڋq�A�b�v���[�h_�����P�ʃe�[�u��.xls�v<BR>
     * �u�T�[�r�X���p�Ǘ��ҁE�ڋq�ύX_�����P�ʃe�[�u��.xls�v�Q�ƁB <BR>
     * @@param l_persistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_persistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_aioOrderUnitParams - (�A�Z�b�g�U�֎�������P��Params)<BR>
     * �i�����O�̃A�Z�b�g�U�֎�������P��Params<BR>
     * 
     * @@return AioOrderUnitParams
     * @@roseuid 4100D7520389
     */
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType, 
        OrderManagerPersistenceContext l_persistenceContext, 
        AioOrderUnitParams l_aioOrderUnitParams) 
    {
        final String STR_METHOD_NAME = 
            " mutate(OrderManagerPersistenceType , OrderManagerPersistenceContext , AioOrderUnitParams )";
        log.entering(STR_METHOD_NAME);
        
        if (l_aioOrderUnitParams == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //��������敪       
        l_aioOrderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.CANCELED);
        
        log.exiting(STR_METHOD_NAME);
        return l_aioOrderUnitParams;
    }
}
@
