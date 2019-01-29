head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundNewOrderApplyConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�V�K�����m��C���^�Z�v�^�i��W�j(WEB3MutualFundNewOrderApplyConfirmInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 ��O�� (���u) �V�K�쐬
                   2006/09/12 ��іQ (���u) �d�l�ύX�E���f��488�A490
                   2006/10/11 �����F (���u) ���f��504
*/

package webbroker3.mf;

import java.sql.Timestamp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M�V�K�����m��C���^�Z�v�^�i��W�j<BR>
 *
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualFundNewOrderApplyConfirmInterceptor
    extends WEB3MutualFundNewOrderConfirmInterceptor
{

    /**
     *  ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundNewOrderApplyConfirmInterceptor.class);

    /**
     * �������<BR>
     */
    protected OrderTypeEnum orderType = OrderTypeEnum.MF_RECRUIT;

    /**
     * ������<BR>
     */
    protected Timestamp paymentDate;

    /**
     * (���M�V�K�����m��C���^�Z�v�^�i��W�j)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 40AD8E0E03B4
     */
    public WEB3MutualFundNewOrderApplyConfirmInterceptor()
    {
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate�̎����j <BR>
     * <BR>
     * ��W���������̒��ŁA���M�����P�ʃf�[�^�̍쐬�E�X�V���ɌĂ΂��B <BR>
     * �����ŗ^����ꂽ���M�����P��Params�ɒl��ݒ肵�A���M�����P��Params��Ԃ��B <BR>
     * <BR>
     * �P�j�@@super.mutate()���\�b�h���R�[������B <BR>
     * <BR>
     * �Q�j�@@���M�����P��Params�Ɉȉ��̐ݒ���s���B <BR>
     * <BR>
     * �@@�@@���M�����P��Params.set�������(this.�������)  <BR>
     *     ���M�����P��Params.set������(this.������) <BR>
     * <BR>
     * 3�j�@@�����ŗ^����ꂽ���M�����P��Params��Ԃ��B <BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_orderManagerPersistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_mutualFundOrderUnitParams - (���M�����P��Params)<BR>
     * �i�����O�̓��M�����P��Params<BR>
     * @@return MutualFundOrderActionParams
     * @@roseuid 40AD8E93025C
     */
    public MutualFundOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "MutualFundOrderUnitParams l_mutualFundOrderUnitParams))";
        log.entering(STR_METHOD_NAME);

        if  (l_mutualFundOrderUnitParams == null)
        {
            log.debug(" �p�����[�^Null�o���Ȃ��BWith " +
                "(�i�����O�̓��M�����P��Params)l_mutualFundOrderActionParams" +
                l_mutualFundOrderUnitParams);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^��Null�ł���");
        }
        //�P�j�@@super.mutate()���\�b�h���R�[������B
        super.mutate(
            l_orderManagerPersistenceType,
            l_orderManagerPersistenceContext,
            l_mutualFundOrderUnitParams);

        //�Q�j�@@���M�����P��Params�Ɉȉ��̐ݒ���s���B
        //���M�����P��Params.set�������(this.�������)
        l_mutualFundOrderUnitParams.setOrderType(this.orderType);

        //���M�����P��Params.set������(this.������)
        l_mutualFundOrderUnitParams.setPaymentDate(this.paymentDate);

        //3�j�@@�����ŗ^����ꂽ���M�����P��Params��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderUnitParams;
    }

    /**
     * (set������)<BR>
     * �������̐ݒ���s���B<BR>
     * @@param l_paymentDate - ������<BR>
     * @@roseuid 40AD92050133
     */
    public void setPaymentDate(Timestamp l_paymentDate)
    {
        this.paymentDate = l_paymentDate;
    }

    /**
     * (get������)<BR>
     * this.��������Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public Timestamp getPaymentDate()
    {
        return paymentDate;
    }

}
@
