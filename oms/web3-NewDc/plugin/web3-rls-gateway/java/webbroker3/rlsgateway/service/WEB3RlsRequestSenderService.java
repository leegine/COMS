head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsRequestSenderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���[���G���W���֑��M�����T�[�r�X(WEB3RlsRequestSenderService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/14 ��(FLJ) �V�K�쐬
 */
package webbroker3.rlsgateway.service;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.common.*;

/**
 *
 * ���[���G���W���֑��M�����T�[�r�X
 * @@author �� (FLJ)
 * @@version 1.0
 */
public interface WEB3RlsRequestSenderService
    extends Service
{

    /**
     * �i�A���������ʒm�j<br />
     * <br />
     * ���[���G���W���ɑ΂��āA�A���������ʒm���b�Z�[�W�𑗐M�B<br />
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
        WEB3SystemLayerException, WEB3BaseException;

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
        WEB3SystemLayerException, WEB3BaseException;

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
        WEB3SystemLayerException, WEB3BaseException;

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
        WEB3SystemLayerException, WEB3BaseException;

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
        WEB3SystemLayerException, WEB3BaseException;

}
@
