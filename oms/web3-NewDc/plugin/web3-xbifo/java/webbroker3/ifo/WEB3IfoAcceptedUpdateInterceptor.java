head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoAcceptedUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP������t�X�V�C���^�Z�v�^(WEB3IfoAcceptedUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/10 Ḗ@@�� (���u) �V�K�쐬
Revesion History : 2004/08/14 ���Ō� (Sinocom) �Ή�����  �y�����w���I�v�V�����z�\�[�X�R�[�h�`�F�b�N�w�E����(JP)20040802.xls No.28
Revesion History : 2006/7/11 ���r (���u) �c�a�X�V�d�l 096
Revesion History : 2006/07/13 �s�p (���u) DB�X�V�d�lNo097
Revesion History : 2007/01/29 ��іQ (���u) �d�l�ύX ���f��No.606,�c�a�X�V�d�lNo.142
*/

package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP������t�X�V�C���^�Z�v�^)<BR>
 * �敨OP������t�X�V�C���^�Z�v�^�N���X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3IfoAcceptedUpdateInterceptor extends WEB3IfoOrderUpdateInterceptor 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    public static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoAcceptedUpdateInterceptor.class);
        
    /**
     * �G���[�R�[�h<BR>
     */
    private String errorCode;    

    /**
     * �����o�H�敪<BR>
     */
    private String submitOrderRouteDiv;

    /**
     * @@roseuid 40C07C0C00EA
     */
    public WEB3IfoAcceptedUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�敨OP������t�X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �����̃G���[�R�[�h�A�����o�H�敪�����g�̃v���p�e�B�ɃZ�b�g�A���g�̃C���X�^���X��ԋp����B<BR>
     * @@param l_strErrorCode - (�G���[�R�[�h)<BR>
     * �G���[�R�[�h<BR>
     * @@param l_strSubmitOrderRouteDiv - (�����o�H�敪)<BR>
     * �����o�H�敪<BR>
     * @@return webbroker3.ifo.WEB3IfoAcceptedUpdateInterceptor
     * @@roseuid 40836BDB03D4
     */
    public WEB3IfoAcceptedUpdateInterceptor(String l_strErrorCode, String l_strSubmitOrderRouteDiv) 
    {   
        this.errorCode = l_strErrorCode;

        this.submitOrderRouteDiv = l_strSubmitOrderRouteDiv;
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     *�imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     *   xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     *   xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     *�@@ �v���p�e�B�̓��e���A�p�����[�^.�����P��Params <BR>
     *   �̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     *  �y��Trade�z�⑫����.DB�X�V<BR>
     *     �X�V���e�͉��L���Q�ƁB<BR>
     *    �u��OP������t_�����P�ʃe�[�u��.xls�v<BR>
     *�@@   ������Ԃ��h�����ρi�V�K�����j�FORDERED�h�̏ꍇ�F<BR>
     *�@@  �u�i��OP������t[��t����]�j�����P�ʃe�[�u���v�V�[�g<BR>
     *�@@   ������Ԃ��h�������s�i�V�K�����j�FNOT_ORDERED�h�̏ꍇ�F <BR>
     *�@@  �u�i��OP������t[��t�G���[]�j�����P�ʃe�[�u���v�V�[�g<BR>
     *    �i������Ԃ��h�����ρi�V�K�����j�FORDERED�h<BR>
     *     �܂��́h�������s�i�V�K�����j�FNOT_ORDERED�h�̏ꍇ�̂�<BR>
     *   ���������{����j <BR>
     * @@param l_updateType- (����)<BR>
     *   OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     *   OrderManagerPersistenceContext�ɂĒ萔��`<BR>
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     *   ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 4083655203D4
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_dealing, 
        IfoOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "WEB3IfoAcceptedUpdateInterceptor:mutate()";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }        

        try
        {
            if (OrderStatusEnum.ORDERED.equals(l_orderUnitParams.getOrderStatus()))
            {
                //(*1)�X�V�O�̔���������"�t�w�l"�̏ꍇ�A�t�w�l
                IfoOrderUnitParams l_orderUnitParamsCopy = 
                    new IfoOrderUnitParams(l_orderUnitParams);
                boolean l_blnStopLimitPrice = false;
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                    l_orderUnitParamsCopy.getOrderConditionType()))
                {
                    l_blnStopLimitPrice = true;
                }                
                
                //�s�ꂩ��m�F�ς݂̒����P��
                if (l_orderUnitParams.getPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.getPrice());
                }

                //�s�ꂩ��m�F�ς݂̊T�Z��n�����ݒ肷��
                if (l_orderUnitParams.getEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
                }

                //�s�ꂩ��m�F�ς݂̎��s����
                l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.getExecutionConditionType()); 

                //�����G���[���R�R�[�h
                l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL); 

                //�����o�H�敪
                if(this.submitOrderRouteDiv != null)
                {
                    l_orderUnitParams.setSubmitOrderRouteDiv(this.submitOrderRouteDiv);
                }

                if (l_blnStopLimitPrice)
                {
                    //��������
                    //�t�w�l(*1�j�̏ꍇ�A0�FDEFAULT�i�����w��Ȃ��j
                    l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
                    
                    //�����������Z�q
                    //�t�w�l(*1)�̏ꍇ�Anull
                    l_orderUnitParams.setOrderCondOperator(null);
                    
                    //�t�w�l��l�^�C�v
                    //�t�w�l(*1)�̏ꍇ�Anull
                    l_orderUnitParams.setStopPriceType(null);
                    
                    //�t�w�l��l
                    //�t�w�l(*1)�̏ꍇ�Anull
                    l_orderUnitParams.setStopOrderPrice(null);
                    
                    //����������
                    //�t�w�l(*1�j�̏ꍇ�A�X�V�O�̔�������
                    l_orderUnitParams.setOrgOrderConditionType(
                        l_orderUnitParamsCopy.getOrderConditionType());

                    //�������������Z�q
                    //�t�w�l�i*1)�̏ꍇ�A�X�V�O�̔����������Z�q
                    l_orderUnitParams.setOrgOrderCondOperator(
                        l_orderUnitParamsCopy.getOrderCondOperator());

                    //���t�w�l��l�^�C�v
                    //�t�w�l�i*1)�̏ꍇ�A�X�V�O�̋t�w�l��l�^�C�v
                    l_orderUnitParams.setOrgStopPriceType(
                        l_orderUnitParamsCopy.getStopPriceType());
                    
                    //���t�w�l��l
                    //�t�w�l�i*1)�̏ꍇ�A�X�V�O�̋t�w�l��l
                    if (l_orderUnitParamsCopy.getStopOrderPriceIsNull())
                    {
                        l_orderUnitParams.setOrgStopOrderPrice(null);
                    }
                    else
                    {
                        l_orderUnitParams.setOrgStopOrderPrice(
                            l_orderUnitParamsCopy.getStopOrderPrice());
                    }
                }
            }
            else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderUnitParams.getOrderStatus()))
            {
                //�����G���[���R�R�[�h
                l_orderUnitParams.setErrorReasonCode(this.errorCode);

                //�����o�H�敪
                if(this.submitOrderRouteDiv != null)
                {
                    l_orderUnitParams.setSubmitOrderRouteDiv(this.submitOrderRouteDiv);
                }
            }                                    
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }
                
        log.exiting(STR_METHOD_NAME);       
        return l_orderUnitParams;
    }
}
@
