head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.26.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsRequestSenderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���[���G���W���֑��M�����T�[�r�X�����N���X(WEB3RlsRequestSenderServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/14 ��(FLJ) �V�K�쐬
 */
package webbroker3.rlsgateway.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.common.*;
import webbroker3.rlsgateway.*;
import webbroker3.rlsgateway.define.*;
import webbroker3.rlsgateway.service.*;
import webbroker3.util.*;

/**
 *
 * ���[���G���W���֑��M�����T�[�r�X����
 * @@author �� (FLJ)
 * @@version 1.0
 */
public class WEB3RlsRequestSenderServiceImpl
    implements WEB3RlsRequestSenderService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsRequestSenderServiceImpl.class);

    /**
     * �i�����t�������ʒm�j<br />
     * <br />
     * ���[���G���W���ɑ΂��āA�����t�������ʒm���b�Z�[�W�𑗐M�B<br />
     * <br />
     * @@param l_subaccount - �⏕����<br />
     * @@param l_lngConOrderId - �e�����̒���ID<br />
     * @@param l_productType - ���i�^�C�v<br />
     * @@param l_strRequestNumber - ���ʃR�[�h<br />
     * <br />
     */
    public void sendConOrderExecuteMessage(SubAccount l_subaccount,
                                           Long l_lngConOrderId,
                                           ProductTypeEnum l_productType,
                                           String l_strRequestNumber) throws
        WEB3SystemLayerException, WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " sendExecuteMessage(SubAccount,Long ,ProductTypeEnum,String)";
        log.entering(STR_METHOD_NAME);

        WEB3RlsConOrderExecuteMessageContext l_context = new
            WEB3RlsConOrderExecuteMessageContext
            (l_subaccount, WEB3RlsNotifyOrderTypeDef.EXECUTE,
             l_productType,
             l_lngConOrderId,
             l_strRequestNumber
             );

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME,
            l_context);

        doRealTxSend();
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * �i�����t���������o�^�j<br /> <br />
     * ���[���G���W���ɑ΂��āA�����t�����o�^���b�Z�[�W�𑗐M�B<br /> <br />
     *
     * @@param l_subaccount - �⏕����
     * @@param l_intOrderType - �����t�����^�C�v
     * @@see WEB3RlsNotifyOrderTypeDef
     * @@param l_conOrderProductType - �e�������i�^�C�v
     * @@param l_lngConOrderId - �e�����̒���ID
     * @@param l_subOrderProductTypes - �q�������i�^�C�v���X�g
     * @@param l_lngSubOrderIds - �q�����̒���ID���X�g <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendRegisterConOrdersMessage(SubAccount l_subaccount,
                                             int l_intOrderType,
                                             ProductTypeEnum l_conOrderProductType,
                                             Long l_lngConOrderId,
                                             ProductTypeEnum[] l_subOrderProductTypes,
                                             Long[] l_lngSubOrderIds) throws
        WEB3SystemLayerException, WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " sendRegisterConOrdersMessage(SubAccount,int,ProductTypeEnum,Long ,ProductTypeEnum[],Long[])";
        log.entering(STR_METHOD_NAME);

        WEB3RlsRegisterConOrdersMessageContext l_context = new
            WEB3RlsRegisterConOrdersMessageContext
            (l_subaccount,
             l_intOrderType,
             l_conOrderProductType,
             l_lngConOrderId,
             l_subOrderProductTypes,
             l_lngSubOrderIds
             );

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME,
            l_context);

        doRealTxSend();
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * �i�����t�������������j<br /> <br />
     * ���[���G���W���ɑ΂��āA�����t�����������b�Z�[�W�𑗐M�B<br /> <br />
     *
     * @@param l_subaccount - �⏕����
     * @@param l_intOrderType - �����t�����^�C�v
     * @@see WEB3RlsNotifyOrderTypeDef
     * @@param l_conOrderProductType - �e�������i�^�C�v
     * @@param l_lngConOrderId - �e�����̒���ID
     * @@param l_subOrderProductTypes - �q�������i�^�C�v���X�g
     * @@param l_lngSubOrderIds - �q�����̒���ID���X�g <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendModifyConOrdersMessage(SubAccount l_subaccount,
                                           int l_intOrderType,
                                           ProductTypeEnum l_conOrderProductType,
                                           Long l_lngConOrderId,
                                           ProductTypeEnum[] l_subOrderProductTypes,
                                           Long[] l_lngSubOrderIds) throws
        WEB3SystemLayerException, WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " sendModifyConOrdersMessage(SubAccount,int,ProductTypeEnum,Long ,ProductTypeEnum[],Long[])";
        log.entering(STR_METHOD_NAME);

        WEB3RlsModifyConOrdersMessageContext l_context = new
            WEB3RlsModifyConOrdersMessageContext
            (l_subaccount,
             l_intOrderType,
             l_conOrderProductType,
             l_lngConOrderId,
             l_subOrderProductTypes,
             l_lngSubOrderIds
             );

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME,
            l_context);

        doRealTxSend();
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * �i�����t������������j<br />
     * <br />
     * ���[���G���W���ɑ΂��āA�����t����������b�Z�[�W�𑗐M�B<br />
     * <br />
     * @@param l_subaccount - �⏕����<br />
     * @@param l_intOrderType - �����t�����^�C�v
     * @@see WEB3RlsNotifyOrderTypeDef
     * @@param l_productType - ���i�^�C�v
     * @@param l_lngSubOrderIds - ����ID<br />
     * <br />
     */
    public void sendCancelConOrderMessage(SubAccount l_subaccount,
                                          int l_intOrderType,
                                          ProductTypeEnum l_productType,
                                          Long l_lngOrderId) throws
        WEB3SystemLayerException, WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " sendCancelConOrderMessage(SubAccount,int,ProductTypeEnum,Long)";

        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3RlsCancelConOrdersMessageContext l_context = new
                WEB3RlsCancelConOrdersMessageContext
                (l_subaccount,
                 l_intOrderType,
                 l_productType,
                 l_lngOrderId
                 );

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME,
                l_context);

            doRealTxSend();
        }
        catch (Throwable l_t)
        {
            //��������ł̃G���[�̓��O�ɏo�͂��ă��\�b�h����I��������
            log.error(l_t.getMessage(), l_t);
        }

        log.exiting(STR_METHOD_NAME);

    }

    private void doRealTxSend() throws
        WEB3SystemLayerException, WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " doRealTxSend()";
        log.entering(STR_METHOD_NAME);

        Object l_context = ThreadLocalSystemAttributesRegistry
            .getAttribute(WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME);

        // ���M���e���ݒ肳��Ă��Ȃ��ꍇ
        if (l_context == null)
        {
            log.debug("WEB3RlsMessageContext���ݒ肳��Ă��Ȃ����߃��[���G���W���֑��M���܂���ł����B");
            log.exiting(STR_METHOD_NAME);
            return;
        }
        log.debug("���[���G���W���֑��M���e�F" + l_context.toString());

        try
        {

            WEB3RlsRealTxSenderService l_service = (WEB3RlsRealTxSenderService) Services.
                getService(WEB3RlsRealTxSenderService.class);
            if (l_context instanceof WEB3RlsConOrderExecuteMessageContext)
            {
                l_service.sendConOrderExecuteMessage( (
                    WEB3RlsConOrderExecuteMessageContext)
                    l_context);
            }
            else if (l_context instanceof WEB3RlsRegisterConOrdersMessageContext)
            {
                l_service.sendRegisterConOrdersMessage( (
                    WEB3RlsRegisterConOrdersMessageContext)
                    l_context);

            }
            else if (l_context instanceof WEB3RlsModifyConOrdersMessageContext)
            {
                l_service.sendModifyConOrdersMessage( (
                    WEB3RlsModifyConOrdersMessageContext)
                    l_context);

            }
            else if (l_context instanceof WEB3RlsCancelConOrdersMessageContext)
            {
                l_service.sendCancelConOrderMessage( (
                    WEB3RlsCancelConOrdersMessageContext)
                    l_context);

            }
            else if (l_context instanceof WEB3RlsManualSubmitConOrderMessageContext)
            {
                l_service.sendManualSubmitConOrder( (
                    WEB3RlsManualSubmitConOrderMessageContext)
                    l_context);

            }
            else // ���M���e���ݒ肳��Ă��Ȃ��ꍇ
            {
                log.debug(
                    "WEB3RlsMessageContext���ݒ肳��Ă��Ȃ����߃��[���G���W���֑��M���܂���ł����B [WEB3RlsMessageContext="
                    + l_context.toString() + "]");

            }
        }
        catch (WEB3SystemLayerException l_sle)
        {
            log.error("���[���G���W���֑��M�Ɏ��s���܂���!!!  [WEB3RlsMessageContext="
                      + l_context.toString() + "]", l_sle);

            throw l_sle;

        }
        catch (WEB3BaseException l_be)
        {
            log.error("���[���G���W���֑��M�Ɏ��s���܂���!!!  [WEB3RlsMessageContext="
                      + l_context.toString() + "]", l_be);
            throw l_be;
        }
        catch (Throwable l_t)
        {
            log.error("���[���G���W���֑��M�Ɏ��s���܂���!!!  [WEB3RlsMessageContext="
                      + l_context.toString() + "]", l_t);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                                               l_t.getMessage());
        }

        // �R���e�L�X�g�����N���A
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME, null);

        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * �i�蓮�����t���������j<br /> <br />
     * �蓮�����t�����������s���B<br /> <br />
     *
     * @@param l_lngSubmitterLoginId - �����҃��O�C��ID
     * @@param l_strSubmitnotifyType - �ʒm�o�H
     * @@param l_subaccount - �⏕����
     * @@param l_intOrderType - �����t�����^�C�v
     * @@see WEB3RlsNotifyOrderTypeDef
     * @@param l_conOrderProductType - �������i�^�C�v
     * @@param l_lngConOrderId - �����̒���ID
     * @@param l_lngParentOrderId - �e�����̒���ID
     * @@param l_intSerialNoInParent - ��������
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendManualSubmitConOrder(
        Long l_lngSubmitterLoginId,
        String l_strSubmitnotifyType,
        SubAccount l_subaccount,
        int l_intOrderType,
        ProductTypeEnum l_conOrderProductType,
        Long l_lngConOrderId,
        ProductTypeEnum
        l_parentOrderProductType,
        Long l_lngParentOrderId,
        int l_intSerialNoInParent) throws
        WEB3SystemLayerException, WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " sendManualSubmitConOrder(Long,String,SubAccount,int,ProductTypeEnum,Long ,ProductTypeEnum[],Long[])";
        log.entering(STR_METHOD_NAME);

        WEB3RlsManualSubmitConOrderMessageContext l_context = new
            WEB3RlsManualSubmitConOrderMessageContext
            (l_lngSubmitterLoginId,
             l_strSubmitnotifyType,
             l_subaccount,
             l_intOrderType,
             l_conOrderProductType,
             l_lngConOrderId,
             l_parentOrderProductType,
             l_lngParentOrderId,
             l_intSerialNoInParent
             );

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3RlsGatewayPlugin.MESSAGE_CONTEXT_ATTRIBUTE_NAME,
            l_context);

        doRealTxSend();
        log.exiting(STR_METHOD_NAME);

    }

}
@
