head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundCancelConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M������m��C���^�Z�v�^�N���X(WEB3MutualFundCancelConfirmInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/06 ���E (���u) �V�K�쐬
                   2004/08/20 ���� (���u) ���r���[
*/
package webbroker3.mf;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M������m��C���^�Z�v�^�N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualFundCancelConfirmInterceptor
    extends WEB3DefaultMutualFundOrderConfirmInterceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundCancelConfirmInterceptor.class); 
                 
    /**
     * (���M����m��C���^�Z�v�^)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40AAF3F50241ss
     */
    public WEB3MutualFundCancelConfirmInterceptor()
    {
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����ŗ^����ꂽ���M��������Params�ɒl��ݒ肵�A���M��������Params��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�����G���[���R�R�[�h�̐ݒ���s���B<BR>
     * �@@�|���M��������Params.set�����G���[���R�R�[�h()���R�[�����A�����G���[���R�R�[�h�̐ݒ���s���B<BR>
     * �@@�@@�mset�����G���[���R�R�[�h�ɓn���p�����^�n<BR>
     * �@@�@@�@@�����G���[���R�R�[�h�F null<BR>
     * <BR>
     * �Q�j�@@�����ŗ^����ꂽ���M��������Params��Ԃ��B<BR>
     * @@param l_orderManagerPersistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_orderManagerPersistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������Ȃ�<BR>
     * @@param l_mutualFundOrderActionParams - (���M��������Params)<BR>
     * �i�����O�̓��M��������Params<BR>
     * @@return MutualFundOrderActionParams
     * @@roseuid 40AAF3F50231
     */
    public MutualFundOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderActionParams l_mutualFundOrderActionParams)
    {

        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext," +
            "MutualFundOrderActionParams l_mutualFundOrderActionParams)";
        log.entering(STR_METHOD_NAME);    
        if (l_mutualFundOrderActionParams == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y���p�����[�^��Null�ł���");
        }        
        
        //�P�j�@@�����G���[���R�R�[�h�̐ݒ���s��
        l_mutualFundOrderActionParams.setErrorReasonCode(null);
        
        log.exiting(STR_METHOD_NAME);
        //�Q�j�@@�����ŗ^����ꂽ���M��������Params��Ԃ�
        return l_mutualFundOrderActionParams;
    }
}
@
