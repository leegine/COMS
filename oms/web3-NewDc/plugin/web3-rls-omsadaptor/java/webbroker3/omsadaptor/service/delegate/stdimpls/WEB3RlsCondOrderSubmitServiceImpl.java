head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3RlsCondOrderSubmitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3RlsCondOrderSubmitServiceImpl�N���X(WEB3RlsCondOrderSubmitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/14 ��(FLJ) �V�K�쐬
 */
package webbroker3.omsadaptor.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.common.*;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.gentrade.*;
import webbroker3.ifo.*;
import webbroker3.omsadaptor.service.delegate.*;
import webbroker3.rlsgateway.data.*;
import webbroker3.rlsgateway.define.*;
import webbroker3.triggerorder.service.delegate.*;
import webbroker3.util.*;

/**
 *
 *
 * @@author �� (FLJ)
 * @@version 1.0
 */
public class WEB3RlsCondOrderSubmitServiceImpl
    implements WEB3RlsCondOrderSubmitService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsCondOrderSubmitServiceImpl.class);

    /**
     * �����x�������ɂ��ւꂸ�A��������t���O�B<BR>
     */
    private final boolean DEFAULT_IS_SUBMIT_DELAY_ORDER = false;

    /** �����x�������ɂ��ւꂸ�A��������t���O�ݒ�L�[ */
    private final static String STR_RLS_IS_SUBMIT_DELAY_ORDER =
        "rls.is.submit.delay.order";

    /**
     * ���������Ƀq�b�g����tick�̃^�C���X�^���v�Ŕ����x���`�F�b�N�t���O�B<BR>
     */
    private final boolean DEFAULT_IS_CHECK_MARKET_TICK_TIMESTAMP = false;

    /** ���������Ƀq�b�g����tick�̃^�C���X�^���v�Ŕ����x���`�F�b�N�t���O�ݒ�L�[ */
    private final static String STR_RLS_IS_CHECK_MARKET_TICK_TIMESTAMP =
        "rls.is.check.market.tick.timestamp";

    /**
     * ���������x������ DEFAULT=5���B<BR>
     */
    private final long DEFAULT_EQUITY_SUBMIT_DELAY_SECONDS = 5 * 60;

    /** ���������x�����Ԑݒ�L�[ */
    private final static String STR_EQUITY_SUBMIT_DELAY_SECONDS =
        "equity.submit.delay.seconds";

    /**
     * �敨�I�v�V���������x������ DEFAULT=5���B<BR>
     */
    private final long DEFAULT_IFO_SUBMIT_DELAY_SECONDS = 5 * 60;

    /** �敨�I�v�V���������x�����ԁB�ݒ�L�[ */
    private final static String STR_IFO_SUBMIT_DELAY_SECONDS =
        "ifo.submit.delay.seconds";

    /**
     * �i���[���G���W������ꌏ�����ʒm�j<br />
     * <br />
     * ���[���G���W������ꌏ�����ʒm�B�@@<br />
     * <br />
     * @@param l_notifyParams - ���[���G���W������ꌏ�����ʒm<br />
     * <br />
     */
    public String submitRlsCondOrder(RlsConOrderHitNotifyParams l_notifyParams
                                     ) throws WEB3SystemLayerException, WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " submitRlsCondOrder(RlsConOrderHitNotifyParams)";
        log.entering(STR_METHOD_NAME);

        long l_lngAccountId = l_notifyParams.getAccountId();
        long l_lngSubAccountId = l_notifyParams.getSubAccountId();
        int l_intOrderType = l_notifyParams.getOrderType();
        long l_lngConOrderId = l_notifyParams.getOrderId();
        ProductTypeEnum l_productType = l_notifyParams.getProductType();

        String l_strSubmitResult = WEB3RlsOrderSubmitErrorCodeDef.SUCCEED;

        try
        {

            WEB3GentradeSubAccount l_subaccount = new WEB3GentradeSubAccount(
                l_lngAccountId,
                l_lngSubAccountId);
            String l_strInstCode = l_subaccount.getInstitution().
                getInstitutionCode();

            String l_isSubmitDelayOrder = GtlUtils.getTradingSystem().getPreference(
                l_strInstCode + "." + STR_RLS_IS_SUBMIT_DELAY_ORDER);
            boolean isSubmitDelayOrder = DEFAULT_IS_SUBMIT_DELAY_ORDER;
            if ("true".equals(l_isSubmitDelayOrder))
            {
                isSubmitDelayOrder = true;
            }

            String l_isCheckMarketTickTimeStamp = GtlUtils.getTradingSystem().
                getPreference(
                l_strInstCode + "." + STR_RLS_IS_CHECK_MARKET_TICK_TIMESTAMP);
            boolean isCheckMarketTickTimeStamp = DEFAULT_IS_CHECK_MARKET_TICK_TIMESTAMP;
            if ("true".equals(l_isCheckMarketTickTimeStamp))
            {
                isCheckMarketTickTimeStamp = true;
            }

            String l_lngEquitySubmitDelaySeconds = GtlUtils.getTradingSystem().
                getPreference(
                l_strInstCode + "." + STR_EQUITY_SUBMIT_DELAY_SECONDS);
            long equitySubmitDelaySeconds = DEFAULT_EQUITY_SUBMIT_DELAY_SECONDS;
            if (l_lngEquitySubmitDelaySeconds != null)
            {
                equitySubmitDelaySeconds = Long.parseLong(l_lngEquitySubmitDelaySeconds);
            }

            String l_lngIfoSubmitDelaySeconds = GtlUtils.getTradingSystem().getPreference(
                l_strInstCode + "." + STR_IFO_SUBMIT_DELAY_SECONDS);
            long ifoSubmitDelaySeconds = DEFAULT_IFO_SUBMIT_DELAY_SECONDS;
            if (l_lngIfoSubmitDelaySeconds != null)
            {
                ifoSubmitDelaySeconds = Long.parseLong(l_lngIfoSubmitDelaySeconds);
            }
            if (WEB3RlsNotifyOrderTypeDef.EXECUTE == l_intOrderType)
            {

                submitRlsCondOrder(l_subaccount, l_intOrderType,
                                   l_lngConOrderId,
                                   l_productType);
            }
            else if (WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT == l_intOrderType)
            {

                WEB3ToStopOrderService service = (WEB3ToStopOrderService) Services.
                    getService(
                    WEB3ToStopOrderService.class);

                long l_lngDelaySeconds = 0;
                if (isCheckMarketTickTimeStamp)
                {
                    if (l_notifyParams.getHitTickTimestamp() != null)
                    {
                        l_lngDelaySeconds =
                            (GtlUtils.getSystemTimestamp().getTime() -
                             l_notifyParams.getHitTickTimestamp().getTime());
                        log.debug("check market tick timestamp and delay msecs:" +
                                  l_lngDelaySeconds);
                    }

                }
                else
                {
                    if (l_notifyParams.getRlsHitTimestamp() != null)
                    {
                        l_lngDelaySeconds =
                            (GtlUtils.getSystemTimestamp().getTime() -
                             l_notifyParams.getRlsHitTimestamp().getTime());
                        log.debug("check rls hit timestamp and delay msecs:" +
                                  l_lngDelaySeconds);
                    }
                }

                if (ProductTypeEnum.EQUITY.equals(l_productType))
                {
                    if (l_lngDelaySeconds <= equitySubmitDelaySeconds * 1000 ||
                        ! (WEB3RlsNotifyTypeDef.RLS.equals(l_notifyParams.getNotifyType()) ||
                           WEB3RlsNotifyTypeDef.OBSERVER_ONLINE.equals(l_notifyParams.
                        getNotifyType())))
                    {
                        service.executeStopOrder(l_subaccount, l_lngConOrderId,
                                                 l_productType);

                    }
                    else
                    {
                        log.debug("order is delay !!! order:" + l_productType + ":" +
                                  l_lngConOrderId);
                        l_strSubmitResult = WEB3RlsOrderSubmitErrorCodeDef.DELAY;

                        if (isSubmitDelayOrder)
                        {
                            //�x���ɂ��ւꂸ��������
                            l_strSubmitResult = WEB3RlsOrderSubmitErrorCodeDef.
                                FORCE_DELAY;
                            service.executeStopOrder(l_subaccount, l_lngConOrderId,
                                l_productType);

                            //update�����̔����x���X�e�[�^�X
                            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                            TradingModule l_tradingMod = l_finApp.getTradingModule(
                                ProductTypeEnum.EQUITY);
                            WEB3EquityOrderManager l_orderManager =
                                (WEB3EquityOrderManager) l_tradingMod.getOrderManager();
                            l_orderManager.updateOrderDelay(l_lngConOrderId);

                        }
                        else
                        {
                            //update�����̔����x���X�e�[�^�X
                            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                            TradingModule l_tradingMod = l_finApp.getTradingModule(
                                ProductTypeEnum.EQUITY);
                            WEB3EquityOrderManager l_orderManager =
                                (WEB3EquityOrderManager) l_tradingMod.getOrderManager();
                            l_orderManager.updateOrderDelay(l_lngConOrderId);
                        }
                    }

                }
                else if (ProductTypeEnum.IFO.equals(l_productType))
                {

                    if (l_lngDelaySeconds <= ifoSubmitDelaySeconds * 1000 ||
                        ! (WEB3RlsNotifyTypeDef.RLS.equals(l_notifyParams.getNotifyType()) ||
                           WEB3RlsNotifyTypeDef.OBSERVER_ONLINE.equals(l_notifyParams.
                        getNotifyType())))
                    {
                        service.executeStopOrder(l_subaccount, l_lngConOrderId,
                                                 l_productType);

                    }
                    else
                    {
                        log.debug("order is delay !!! order:" + l_productType + ":" +
                                  l_lngConOrderId);
                        l_strSubmitResult = WEB3RlsOrderSubmitErrorCodeDef.DELAY;

                        if (isSubmitDelayOrder)
                        {
                            //�x���ɂ��ւꂸ��������
                            l_strSubmitResult = WEB3RlsOrderSubmitErrorCodeDef.
                                FORCE_DELAY;
                            service.executeStopOrder(l_subaccount, l_lngConOrderId,
                                l_productType);

                            //update�����̔����x���X�e�[�^�X
                            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                            TradingModule l_tradingMod = l_finApp.getTradingModule(
                                ProductTypeEnum.IFO);
                            WEB3OptionOrderManagerImpl l_orderManager =
                                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();
                            l_orderManager.updateOrderDelay(l_lngConOrderId);
                        }
                        else
                        {
                            //update�����̔����x���X�e�[�^�X
                            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                            TradingModule l_tradingMod = l_finApp.getTradingModule(
                                ProductTypeEnum.IFO);
                            WEB3OptionOrderManagerImpl l_orderManager =
                                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();
                            l_orderManager.updateOrderDelay(l_lngConOrderId);
                        }
                    }

                }
                else
                {
                    throw new RuntimeException("no implementation!!!");
                }

            }

            else if (WEB3RlsNotifyOrderTypeDef.W_LlIMIT == l_intOrderType)
            {

                WEB3ToWLimitSwitchService l_service = (WEB3ToWLimitSwitchService)
                    Services.
                    getService(
                    WEB3ToWLimitSwitchService.class);

                long l_lngwDelaySeconds = 0;
                if (isCheckMarketTickTimeStamp)
                {
                    if (l_notifyParams.getHitTickTimestamp() != null)
                    {
                        l_lngwDelaySeconds =
                            (GtlUtils.getSystemTimestamp().getTime() -
                             l_notifyParams.getHitTickTimestamp().getTime());
                        log.debug("check market tick timestamp and delay msecs:" +
                                  l_lngwDelaySeconds);
                    }

                }
                else
                {
                    if (l_notifyParams.getRlsHitTimestamp() != null)
                    {
                        l_lngwDelaySeconds =
                            (GtlUtils.getSystemTimestamp().getTime() -
                             l_notifyParams.getRlsHitTimestamp().getTime());
                        log.debug("check rls hit timestamp and delay msecs:" +
                                  l_lngwDelaySeconds);
                    }
                }

                if (ProductTypeEnum.EQUITY.equals(l_productType))
                {
                    if (l_lngwDelaySeconds <= equitySubmitDelaySeconds * 1000 ||
                        ! (WEB3RlsNotifyTypeDef.RLS.equals(l_notifyParams.getNotifyType()) ||
                           WEB3RlsNotifyTypeDef.OBSERVER_ONLINE.equals(l_notifyParams.
                        getNotifyType())))
                    {
                        l_service.executeWLimitSwitch(l_subaccount, l_lngConOrderId,
                            l_productType);

                    }
                    else
                    {
                        log.debug("order is delay !!! order:" + l_productType + ":" +
                                  l_lngConOrderId);
                        l_strSubmitResult = WEB3RlsOrderSubmitErrorCodeDef.DELAY;

                        if (isSubmitDelayOrder)
                        {
                            //�x���ɂ��ւꂸ��������
                            l_strSubmitResult = WEB3RlsOrderSubmitErrorCodeDef.
                                FORCE_DELAY;
                            l_service.executeWLimitSwitch(l_subaccount, l_lngConOrderId,
                                l_productType);

                            //update�����̔����x���X�e�[�^�X
                            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                            TradingModule l_tradingMod = l_finApp.getTradingModule(
                                ProductTypeEnum.EQUITY);
                            WEB3EquityOrderManager l_orderManager =
                                (WEB3EquityOrderManager) l_tradingMod.getOrderManager();
                            l_orderManager.updateSwitchDelay(l_lngConOrderId);

                        }
                        else
                        {
                            //update�����̔����x���X�e�[�^�X
                            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                            TradingModule l_tradingMod = l_finApp.getTradingModule(
                                ProductTypeEnum.EQUITY);
                            WEB3EquityOrderManager l_orderManager =
                                (WEB3EquityOrderManager) l_tradingMod.getOrderManager();
                            l_orderManager.updateSwitchDelay(l_lngConOrderId);
                        }
                    }

                }
                else if (ProductTypeEnum.IFO.equals(l_productType))
                {

                    if (l_lngwDelaySeconds <= ifoSubmitDelaySeconds * 1000 ||
                        ! (WEB3RlsNotifyTypeDef.RLS.equals(l_notifyParams.getNotifyType()) ||
                           WEB3RlsNotifyTypeDef.OBSERVER_ONLINE.equals(l_notifyParams.
                        getNotifyType())))
                    {
                        l_service.executeWLimitSwitch(l_subaccount, l_lngConOrderId,
                            l_productType);

                    }
                    else
                    {
                        log.debug("order is delay !!! order:" + l_productType + ":" +
                                  l_lngConOrderId);
                        l_strSubmitResult = WEB3RlsOrderSubmitErrorCodeDef.DELAY;

                        if (isSubmitDelayOrder)
                        {
                            //�x���ɂ��ւꂸ��������
                            l_strSubmitResult = WEB3RlsOrderSubmitErrorCodeDef.
                                FORCE_DELAY;
                            l_service.executeWLimitSwitch(l_subaccount, l_lngConOrderId,
                                l_productType);

                            //update�����̔����x���X�e�[�^�X
                            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                            TradingModule l_tradingMod = l_finApp.getTradingModule(
                                ProductTypeEnum.IFO);
                            WEB3OptionOrderManagerImpl l_orderManager =
                                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();
                            l_orderManager.updateSwitchDelay(l_lngConOrderId);
                        }
                        else
                        {
                            //update�����̔����x���X�e�[�^�X
                            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                            TradingModule l_tradingMod = l_finApp.getTradingModule(
                                ProductTypeEnum.IFO);
                            WEB3OptionOrderManagerImpl l_orderManager =
                                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();
                            l_orderManager.updateSwitchDelay(l_lngConOrderId);
                        }
                    }

                }
                else
                {
                    throw new RuntimeException("no implementation!!!");
                }

            }

            else
            {
                throw new RuntimeException("no implementation!!!");
            }

        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.debug("order_submit_error_code=" + l_strSubmitResult);
        log.exiting(STR_METHOD_NAME);
        return l_strSubmitResult;
    }

    /**
     * �i���[���G���W������ꌏ�����ʒm�j<br />
     * <br />
     * ���[���G���W������ꌏ�����ʒm�B�@@<br />
     * <br />
     * @@param l_subaccount - �⏕����<br />
     * @@param l_intOrderType - �����t�����^�C�v
     * @@param l_lngConOrderId - ����ID<br />
     * @@param l_productType - ���i�^�C�v<br />
     * <br />
     */
    public void submitRlsCondOrder(WEB3GentradeSubAccount l_subaccount,
                                   int l_intOrderType,
                                   long l_lngConOrderId,
                                   ProductTypeEnum l_productType
                                   ) throws WEB3SystemLayerException,
        WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " submitRlsCondOrder(long,long,int,long,ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        if (WEB3RlsNotifyOrderTypeDef.EXECUTE == l_intOrderType)
        {

            WEB3ToSuccOrderService service = (WEB3ToSuccOrderService) Services.
                getService(
                WEB3ToSuccOrderService.class);
            service.executeSuccOrder(l_subaccount, l_lngConOrderId,
                                     l_productType);
        }
        else if (WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT == l_intOrderType)
        {
            WEB3ToStopOrderService service = (WEB3ToStopOrderService) Services.
                getService(
                WEB3ToStopOrderService.class);
            service.executeStopOrder(l_subaccount, l_lngConOrderId,
                                     l_productType);
        }
        else
        {
            throw new RuntimeException("no implementation!!!");
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
