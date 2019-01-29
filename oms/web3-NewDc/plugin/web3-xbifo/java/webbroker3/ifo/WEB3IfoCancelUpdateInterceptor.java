head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP����X�V�C���^�Z�v�^�N���X(WEB3IfoCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/15 䈋� (���u) �V�K�쐬
Revesion History : 2007/01/29 ��іQ (���u) �c�a�X�V�d�lNo.141,No.154
*/
package webbroker3.ifo;


import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP����X�V�C���^�Z�v�^)<BR>
 * �敨OP����X�V�C���^�Z�v�^�N���X<BR>
 * @@author  䈋�
 * @@version 1.0
 */
public class WEB3IfoCancelUpdateInterceptor extends WEB3IfoOrderUpdateInterceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoCancelUpdateInterceptor.class);
    
    /**
     * @@roseuid 40C07C0C00EA
     */
    
    /**
     * (�敨OP����X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@return webbroker3.ifo.WEB3IfoCancelUpdateInterceptor
     * @@roseuid 407E828F0177
     */
    public WEB3IfoCancelUpdateInterceptor() 
    {

    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A<BR>�E
     * �p����B<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �����P��.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ <BR>
     * �uOP���_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR> 
     * �����P��.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ <BR> 
     * �u�敨���_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR> 
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * 
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_ifoOrderUnitParams - (�����P��Params)<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 407E7D25038A
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderUnitParams l_ifoOrderUnitParams) 
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType, " +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "IfoOrderUnitParams l_ifoOrderUnitParams)";

        log.entering(STR_METHOD_NAME);
        if(l_ifoOrderUnitParams == null)
        {        
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        // �����ID
        // �C���^�Z�v�^.�����ID
        if (traderId == 0)
        {   
            l_ifoOrderUnitParams.setTraderId(null);
        }
        else
        {
            l_ifoOrderUnitParams.setTraderId(traderId);
        }
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);

        // ���������E����敪
        if(l_ifoOrderUnitParams.getConfirmedOrderPriceIsNull())
        {
            l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELED);
        }
        else
        {
            l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELING);
        }

        // �����o�H�敪
        String l_orderRootDiv ="";
        String l_orderRootDivString = WEB3SessionAttributeDef.ORDER_ROOT_DIV;
        l_orderRootDiv = l_opLoginSec.getSessionProperty(l_orderRootDivString);
        l_ifoOrderUnitParams.setOrderRootDiv(l_orderRootDiv);
        
        // �����G���[���R�R�[�h
		l_ifoOrderUnitParams.setErrorReasonCode(
			WEB3ErrorReasonCodeDef.NORMAL);

		//�����o�H�敪
        //�敨OP�����T�[�r�X.get��������������o�H�敪()�̖߂�l
        WEB3IfoFrontOrderService l_service =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        OrderUnit l_orderUnit  = l_finApp.getTradingModule(
            ProductTypeEnum.IFO).getOrderManager().toOrderUnit(l_ifoOrderUnitParams);

        String l_strSubmitOrderRouteDiv = null;

        try
        {
            l_strSubmitOrderRouteDiv = l_service.getChangeSubmitOrderRouteDiv(
                (IfoOrderUnit)l_orderUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnitParams;
    }
    
    /**
     * �����ID
     */
    private long traderId;
    
    /**
     * (set�����ID)<BR>
     */
    public void setTraderId (long l_lngTraderID)
    {
        traderId = l_lngTraderID;
    }
}
@
