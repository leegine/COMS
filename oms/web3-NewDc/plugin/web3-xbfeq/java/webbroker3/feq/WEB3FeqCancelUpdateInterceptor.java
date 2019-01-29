head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O����������X�V�C���^�Z�v�^(WEB3FeqCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �����F (���u) �V�K�쐬
                 : 2005/07/25 ���I(���u) ���r���[
*/

package webbroker3.feq;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O����������X�V�C���^�Z�v�^)<BR>
 * �O����������X�V�C���^�Z�v�^�N���X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3FeqCancelUpdateInterceptor extends WEB3FeqUpdateInterceptor
{
    /**
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqCancelUpdateInterceptor.class);

    /**
     * (�㗝���͎�)<BR>
     * �㗝���͎�<BR>
     */
    private Trader trader;

    /**
     * (�O����������X�V�C�x���g�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �p�����[�^�̍��ڂ��A�e�v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * �㗝���͎�<BR>
     * @@roseuid 42D0D1FF0242
     */
    public WEB3FeqCancelUpdateInterceptor(Trader l_trader)
    {
        this.trader = l_trader;
    }

    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �P�j�@@�����iͯ�ށj�e�[�u���X�V<BR>
     * �@@super.mutate(OrderManagerPersistenceType,<BR>
     * �@@OrderManagerPersistenceContext, FeqOrderUnitParams)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �Q�j�@@�����P�ʃe�[�u���X�V<BR>
     *   �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * ���ڐݒ���e�́A<BR>
     * DB�X�V�d�l�u���_�O�������P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * �X�V�^�C�v<BR>
     * @@param l_context - (����)<BR>
     * ����<BR>
     * @@param l_feqOrderUnitParams - (�����P�ʍs)<BR>
     * �����P�ʍs�i�F�����P��Params�j<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams
     * @@roseuid 429B4D520379
     */
    public FeqOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderUnitParams l_feqOrderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if(l_feqOrderUnitParams == null)
        {
            log.debug(" �����P��Params�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }
        // �P�j�@@�����iͯ�ށj�e�[�u���X�V
        l_feqOrderUnitParams = super.mutate(l_updateType, l_context, l_feqOrderUnitParams);

        // ���������E����敪
        if (l_feqOrderUnitParams.getConfirmedQuantityIsNull())
        {
            l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELED);
        }
        else
        {
            l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELING);
        }

        // �����G���[���R�R�[�h
        l_feqOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        // �X�V�҃R�[�h
        // �ڋq���͂̏ꍇ
        if (this.trader == null)
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            // �ڋq�R�[�h���擾
            String l_strAccountCode;
            try
            {
                l_strAccountCode = l_accountMgr.getMainAccount(l_feqOrderUnitParams.getAccountId()).getAccountCode();
            }
            catch (NotFoundException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���:", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            l_feqOrderUnitParams.setLastUpdater(l_strAccountCode);
        }
        // �㗝���͂̏ꍇ
        else
        {
            l_feqOrderUnitParams.setLastUpdater(this.trader.getTraderCode());
        }
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        l_feqOrderUnitParams.setOrderRootDiv(l_strOrderRootDiv);
        
        TradingModule l_tradingModule = 
            GtlUtils.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        WEB3FeqOrderUnit l_feqOrderUnit =
            (WEB3FeqOrderUnit)l_orderManager.toOrderUnit(l_feqOrderUnitParams);
        if (!l_feqOrderUnit.isUnexecuted())
        {
            WEB3FeqFinTransactionManager l_feqFinTransactionManager =
                (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
            double l_dblEstimatedPrice = 0.0D;
            double l_dblEstimatedPriceFc = 0.0D;
            try
            {
                l_dblEstimatedPrice = l_feqFinTransactionManager.getNetAmount(l_feqOrderUnit);
                l_dblEstimatedPriceFc = l_feqFinTransactionManager.getNetAmountFc(l_feqOrderUnit);
            }
            catch (WEB3BaseException l_wbe)
            {
                log.error(l_wbe.getMessage(), l_wbe);
                throw new WEB3BaseRuntimeException(
                    l_wbe.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_wbe.getMessage(),
                    l_wbe);
            }
            if (l_dblEstimatedPrice < 0.0D)
            {
                l_dblEstimatedPrice *= -1D;
            }
            if (l_dblEstimatedPriceFc < 0.0D)
            {
                l_dblEstimatedPriceFc =
                    new BigDecimal(String.valueOf(l_dblEstimatedPriceFc)).multiply(new BigDecimal("-1")).doubleValue();
            }
            l_feqOrderUnitParams.setEstimatedPrice(l_dblEstimatedPrice);
            l_feqOrderUnitParams.setFEstimatedPrice(l_dblEstimatedPriceFc);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnitParams;
    }

    /**
     * (get�㗝���͎�)<BR>
     * this.�㗝���͎҂�ԋp����B<BR>
     * @@return Trader
     * @@roseuid 429B4D47007C
     */
    public Trader getTrader()
    {
        return this.trader;
    }
}
@
