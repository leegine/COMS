head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoChangeConfirmUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����m��X�V�C���^�Z�v�^(WEB3IfoChangeConfirmUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/10 Ḗ@@�� (���u) �V�K�쐬
*/

package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.define.WEB3IfoOrderModifyCancelTypeDef;
/**
 * (�����m��X�V�C���^�Z�v�^)<BR>
 * �敨OP�����m��X�V�C���^�Z�v�^�N���X<BR>
 * @@author  Ḗ@@��
 * @@version 1.0
 */
public class WEB3IfoChangeConfirmUpdateInterceptor extends WEB3IfoUpdateInterceptor 
{
    /**
      * ���O�o�̓��[�e�B���e�B�B
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoChangeConfirmUpdateInterceptor.class);
    
    /**
     * (�X�V�l�ݒ�)<BR>
     *�imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A<BR>�E
     * �p����B<BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �����P��.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ <BR> 
     * �uOP�����m��_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR> 
     * �����P��.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ <BR>
     * �u�敨�����m��_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR> 
     * @@param l_updateType - INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * @@param l_dealing - �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_orderUnitParams - <�����P��Params><BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 40A4785200C4
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_dealing, 
        IfoOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME =
             ".mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, IfoOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }        
        
        try
        {
            //�����P����ݒ肷��
            if (l_orderUnitParams.getPriceIsNull())
            {
                l_orderUnitParams.setConfirmedOrderPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.getPrice());
            }
            
            //�T�Z��n�����ݒ肷��
            if (l_orderUnitParams.getEstimatedPriceIsNull())
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
            }

            //���s������ݒ肷��
            l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.getExecutionConditionType());  

            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            //�擾�����P�ʃI�u�W�F�N�g           
            //long l_lngOrderUnitId = l_orderUnitParams.getOrderUnitId();            

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr = 
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();
            OrderUnit l_orderUnit = l_orderMgr.toOrderUnit(l_orderUnitParams);
            //���������E����敪���Z�b�g
            if (l_orderUnit.isUnexecuted() == false)
            {
                log.debug("Enter the if path that l_orderUnit.isUnexecuted() is false ");
                l_orderUnitParams.setModifyCancelType(WEB3IfoOrderModifyCancelTypeDef.ORDER_REVISED_PARTLY);
                log.debug("Exiting the if path that l_orderUnit.isUnexecuted() is false");
            }
            else
            {
                log.debug("Enter the else path.");
                l_orderUnitParams.setModifyCancelType(WEB3IfoOrderModifyCancelTypeDef.ORDER_REVISED_ALL);
                log.debug("Exiting the els path.");
            }
            log.debug("Exiting the try path ."); 
        }
        catch(Exception l_ex)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,this.getClass().getName()+ "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
        
    }
    
    /**
     * (�敨OP�����m��X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@return webbroker3.ifo.WEB3IfoChangeConfirmUpdateInterceptor
     * @@roseuid 40A47975028A
     */
    public WEB3IfoChangeConfirmUpdateInterceptor() 
    {

    }
}
@
