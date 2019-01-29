head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3DefaultMutualFundOrderConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �f�t�H���g���M�����m��C���^�Z�v�^(WEB3DefaultMutualFundOrderConfirmInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/06 ���E (���u) �V�K�쐬
                   2004/08/20 ���� (���u) ���r���[    
*/

package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * �f�t�H���g���M�����m��C���^�Z�v�^<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3DefaultMutualFundOrderConfirmInterceptor
    implements MutualFundOrderManagerPersistenceEventInterceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3DefaultMutualFundOrderConfirmInterceptor.class);
                
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate�̎����j<BR>
     * <BR>
     * �����E��������̒��ŁA���M�����P�ʃf�[�^�̍쐬�E�X�V���ɌĂ΂��B<BR>
     * <BR>
     * ����.���M�����P��Params��Ԃ��B<BR>
     * @@param l_orderManagerPersistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_orderManagerPersistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_mutualFundOrderUnitParams - (���M�����P��Params)<BR>
     * �i�����O�̓��M�����P��Params<BR>
     * 
     * @@return MutualFundOrderUnitParams
     * @@roseuid 40AAF0FF00CA
     */
    public MutualFundOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext," +
            "MutualFundOrderUnitParams l_mutualFundOrderUnitParams)";
        log.entering(STR_METHOD_NAME);    
        if (l_mutualFundOrderUnitParams == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderUnitParams;
        
    }
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate�̎����j<BR>
     * <BR>
     * ��菈���̒��ŁA���M�����P�ʃf�[�^�̍쐬�E�X�V���ɌĂ΂��B<BR>
     * <BR>
     * ����.���M���Params��Ԃ��B<BR>
     * @@param l_orderManagerPersistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_orderManagerPersistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_mutualFundOrderExecutionParams - (���M���Params)<BR>
     * �i�����O�̓��M���Params<BR>
     * @@return MutualFundOrderExecutionParams
     * @@roseuid 40AAF0FF00E9
     */
    public MutualFundOrderExecutionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderExecutionParams l_mutualFundOrderExecutionParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext," +
            "MutualFundOrderExecutionParams l_mutualFundOrderExecutionParams)";
        log.entering(STR_METHOD_NAME);    
        if (l_mutualFundOrderExecutionParams == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        log.exiting(STR_METHOD_NAME);               
        return l_mutualFundOrderExecutionParams;
    }
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate�̎����j<BR>
     * <BR>
     * �����E��������̒��ŁA���M���������f�[�^�̍쐬�E�X�V���ɌĂ΂��B<BR>
     * <BR>
     * ����.���M��������Params��Ԃ��B<BR>
     * @@param l_orderManagerPersistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_orderManagerPersistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_mutualFundOrderActionParams - (���M��������Params)<BR>
     * �i�����O�̓��M��������Params<BR>
     * @@return MutualFundOrderActionParams
     * @@roseuid 40AAF0FF00F9
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
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderActionParams;
    }
    /**
     * (get���s�N�G��)<BR>
     * �igetQueryToExecute�̎����j<BR>
     * <BR>
     * null��Ԃ��B<BR>
     * @@param l_orderManagerPersistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_tableRowClass - (�e�[�u��Row�N���X)<BR>
     * �ǂ̃e�[�u�����X�V���邩�w��<BR>
     * @@return BatchedQuery
     * @@roseuid 40AAF0FF0118
     */
    public BatchedQuery getQueryToExecute(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        Class l_tableRowClass)
    {
        return null;
    }
}
@
