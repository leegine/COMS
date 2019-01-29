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
filename	WEB3MutualFundAcceptConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M����t�m��C���^�Z�v�^�N���X(WEB3MutualFundAcceptConfirmInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ��O�� (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/

package webbroker3.mf;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

/**
 * �����M����t�m��C���^�Z�v�^�N���X<BR>
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualFundAcceptConfirmInterceptor 
        extends WEB3DefaultMutualFundOrderConfirmInterceptor 
{
    
    /**
     * �����G���[���R�R�[�h<BR>
     */
    private String orderErrorReasonCode;
    
    /**
     * (���M��t�m��C���^�Z�v�^)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40AAF523005D
     */
    public WEB3MutualFundAcceptConfirmInterceptor() 
    {
     
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate�̎����j<BR>
     * <BR>
     * �����E��������̒��ŁA���M���������f�[�^�̍쐬�E�X�V���ɌĂ΂��B<BR>
     * <BR>
     * �P�j this.�����G���[���R�R�[�h���A����.���M��������Params.�����G���[���R�R�[�h�ɐݒ肷��B<BR>
     * @@param l_orderManagerPersistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_orderManagerPersistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_mutualFundOrderActionParams - (���M��������Params)<BR>
     * �i�����O�̓��M��������Params<BR>
     * @@return MutualFundOrderActionParams
     * @@roseuid 40AAF52203E7
     */
    public MutualFundOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType, 
        OrderManagerPersistenceContext l_orderManagerPersistenceContext, 
        MutualFundOrderActionParams l_mutualFundOrderActionParams) 
    {
        l_mutualFundOrderActionParams.setErrorReasonCode(this.orderErrorReasonCode);
        return l_mutualFundOrderActionParams;
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate�̎����j<BR>
     * <BR>
     * �����E��������̒��ŁA���M�����P�ʃf�[�^�̍쐬�E�X�V���ɌĂ΂��B<BR>
     * <BR>
     * �P�j this.�����G���[���R�R�[�h���A����.���M�����P��Params.�����G���[���R�R�[�h�ɐݒ肷��B<BR>
     * @@param l_orderManagerPersistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_orderManagerPersistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_mutualFundOrderUnitParams - (���M�����P��Params)<BR>
     * �i�����O�̓��M�����P��Params<BR>
     * @@return MutualFundOrderUnitParams
     * @@roseuid 40AAF523001E
     */
    public MutualFundOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext, 
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams) 
    {
        l_mutualFundOrderUnitParams.setErrorReasonCode(this.orderErrorReasonCode);
        return l_mutualFundOrderUnitParams;
    }
    
    /**
     * (set�����G���[���R�R�[�h)<BR>
     * �����G���[���R�R�[�h�̐ݒ���s���B<BR>
     * @@param l_strErrorCode - �G���[�R�[�h
     * @@roseuid 40AAF523003D
     */
    public void setOrderErrorReasonCode(String l_strErrorCode) 
    {
        this.orderErrorReasonCode = l_strErrorCode;
    }
    
    /**
     * (get�����G���[���R�R�[�h)<BR>
     * this.�����G���[���R�R�[�h��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AAF523004D
     */
    public String getOrderErrorReasonCode() 
    {
        return this.orderErrorReasonCode;
    }
}
@
