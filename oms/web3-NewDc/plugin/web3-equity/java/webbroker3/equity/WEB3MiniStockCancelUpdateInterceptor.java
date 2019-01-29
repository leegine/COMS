head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MiniStockCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j��������X�V�C���^�Z�v�^(WEB3MiniStockCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12  �d��(���u) �V�K�쐬
                   2004/12/29  �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����~�j��������X�V�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �����~�j��������X�V�C���^�Z�v�^�N���X
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3MiniStockCancelUpdateInterceptor extends WEB3MarginUpdateEventInterceptor 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MiniStockCancelUpdateInterceptor.class);
    
    /**
     * (�����o�H�敪)<BR>
     */
    private String orderRootDiv = null;
    
    /**
     * (�㗝���͎�)<BR>
     */
    private WEB3GentradeTrader trader = null;
    
    /**
     * �i�����~�j��������X�V�C���^�Z�v�^�j�B<BR>
     * <BR>
     * �R���X�g���N�^�B
     * @@param    l_orderRootdiv - �����o�H�敪<BR>
     * @@param    l_trader       - ����<BR>
     */
    public WEB3MiniStockCancelUpdateInterceptor(
        String l_strOrderRootdiv, WEB3GentradeTrader l_trader) 
    {
        this.orderRootDiv = l_strOrderRootdiv;
        this.trader = l_trader;
    }
    
    /**
     * �i�X�V�l�ݒ�j�B<BR>
     * <BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �P�j �g�����ڃZ�b�g<BR>
     * �@@�v���p�e�B����A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A<BR>
     * �ԋp����B<BR>
     * <BR>
     * �X�V���e��DB�ݒ�_���u�~�j�����_���������P�ʃe�[�u��.xls�v�Q�ƁB
     * @@param l_mutate (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * EqTypeOrderManagerPersistenceType�ɂĒ萔��`�B
     * @@param l_context (����)<BR>
     * �iEqTypeOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_eqtypeOrderUnitParams (�����P��Params)<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B
     * @@return EqtypeOrderUnitParams
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_mutate, 
        OrderManagerPersistenceContext l_context, 
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(" +
            "OrderManagerPersistenceType l_mutate," +
            "OrderManagerPersistenceContext l_context," + 
            "EqtypeOrderUnitParams l_eqtypeOrderUnitParams) ";
        log.entering(STR_METHOD_NAME);

        //���� = �C���^�Z�v�^�̃v���p�e�B.�㗝���͎�.�����ID
        //  ���C���^�Z�v�^�̃v���p�e�B.�㗝���͎�==null�̏ꍇ�́Anull���Z�b�g�B
        if (this.trader == null)
        {
            l_eqtypeOrderUnitParams.setTraderId(null);
        }
        else
        {
            l_eqtypeOrderUnitParams.setTraderId(this.trader.getTraderId());
        }
        //�S���������
        l_eqtypeOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELED);

        //�����o�H�敪 = �C���^�Z�v�^�̃v���p�e�B.�����o�H�敪
        l_eqtypeOrderUnitParams.setOrderRootDiv(this.orderRootDiv);

        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
    }
     
}
@
