head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : W�w�l���������ؑ֍X�V�C���^�Z�v�^(WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/20 ���G�� (���u) �V�K�쐬 �i���f���jNo.176 �iDB�X�V�d�l�jNo.025
*/
package webbroker3.triggerorder;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3MarginUpdateEventInterceptor;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * (W�w�l���������ؑ֍X�V�C���^�Z�v�^)
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor
    extends WEB3MarginUpdateEventInterceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor.class);

    /**
     * �T�Z����v�Z����
     */
    public WEB3EquityEstimatedPrice l_equityEstimatedPrice;

    /**
     * (W�w�l���������ؑ֍X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^  <BR>
     * <BR>
     * �C���X�^���X�𐶐����A���������g�̃v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_estimatedPrice - (�T�Z����v�Z����)
     */
    public WEB3ToWLimitEqTypeSwitchUpdateServiceInterceptor(
        WEB3EquityEstimatedPrice l_estimatedPrice)
    {
        this.l_equityEstimatedPrice = l_estimatedPrice;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����̒����P��Params�Ɋg������(*)��ݒ肵�ԋp����B<BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j �g�����ڃZ�b�g  <BR>
     * <BR>
     * �@@�@@�X�V���e�́ADB�X�V�d�l  <BR>
     * �@@�@@�@@�uW�w�l�����ؑ�(OK)_�����P�ʃe�[�u��.xls�v<BR>
     * �@@�@@�Q�ƁB <BR>
     * <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * @@param l_context - (����)<BR>
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * @@return EqtypeOrderUnitParams
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //���s����   this.�iW�w�l�j���s����
        l_orderUnitParams.setExecutionConditionType(
            l_orderUnitParams.getWLimitExecCondType());

        //�����P��   �C���^�Z�v�^.�T�Z����v�Z����.get�v�Z�P��()
        l_orderUnitParams.setPrice(
            this.l_equityEstimatedPrice.getCalcUnitPrice());

        //�T�Z��n���  �C���^�Z�v�^.�T�Z����v�Z����.get�T�Z��n���()
        l_orderUnitParams.setEstimatedPrice(
            this.l_equityEstimatedPrice.getEstimateDeliveryAmount());

        //���������E����敪  A�FW�w�l�����ؑ֒�
        l_orderUnitParams.setModifyCancelType(
            WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING);

        //�����o�H�敪   ���������T�[�r�X.get��������������o�H�敪()�̖߂�l

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        OrderUnit l_orderUnit = l_orderManager.toOrderUnit(l_orderUnitParams);
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        String l_strSubmitOrderRouteDiv = null;
        String l_strChangeOrderRev = null;
        try
        {
            l_strSubmitOrderRouteDiv =
                l_frontOrderService.getChangeSubmitOrderRouteDiv(
                    (EqTypeOrderUnit)l_orderUnit);
            l_strChangeOrderRev =
                l_frontOrderService.getChangeOrderRev((EqTypeOrderUnit)l_orderUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        l_orderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);

        // ���N�G�X�g�^�C�v  1�F�����T�[�o
        l_orderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);

        //����Rev.  ���������T�[�r�X.get����������Rev()�̖߂�l
        l_orderUnitParams.setOrderRev(l_strChangeOrderRev);
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;

    }
}
@
