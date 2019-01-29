head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsRealTxSenderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���ۃ��[���G���W���֑��M�g�����U�N�V�����̏����T�[�r�X(WEB3RlsRealTxSenderService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/14 ��(FLJ) �V�K�쐬
 */
package webbroker3.rlsgateway.service;

import com.fitechlabs.xtrade.kernel.boot.*;
import webbroker3.common.*;
import webbroker3.rlsgateway.*;

/**
 * ���ۃ��[���G���W���֑��M�g�����U�N�V�����̏����T�[�r�X
 * @@author �� (FLJ)
 * @@version 1.0
 */
public interface WEB3RlsRealTxSenderService
    extends Service
{

    /**
     * �i�A���������ʒm�j<br />
     * <br />
     * ���[���G���W���ɑ΂��āA�A���������ʒm���b�Z�[�W�𑗐M�B<br />
     * <br />
     * @@param l_context - ���M���e�R���e�N�X�g<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendConOrderExecuteMessage(WEB3RlsConOrderExecuteMessageContext
                                           l_context) throws
        WEB3SystemLayerException, WEB3BaseException;

    /**
     * �i�����t���������o�^�j<br /> <br />
     * ���[���G���W���ɑ΂��āA�����t�����o�^���b�Z�[�W�𑗐M�B<br />
     * <br />
     * @@param l_context - ���M���e�R���e�N�X�g<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendRegisterConOrdersMessage(WEB3RlsRegisterConOrdersMessageContext
                                             l_context) throws
        WEB3SystemLayerException, WEB3BaseException;

    /**
     * �i�����t�������������j<br /> <br />
     * ���[���G���W���ɑ΂��āA�����t�����������b�Z�[�W�𑗐M<br />
     * <br />
     * @@param l_context - ���M���e�R���e�N�X�g<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendModifyConOrdersMessage(WEB3RlsModifyConOrdersMessageContext
                                           l_context) throws
        WEB3SystemLayerException, WEB3BaseException;

    /**
     * �i�����t������������j<br />
     * <br />
     * ���[���G���W���ɑ΂��āA�����t����������b�Z�[�W�𑗐M�B<br />
     * <br />
     * @@param l_context - ���M���e�R���e�N�X�g<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendCancelConOrderMessage(WEB3RlsCancelConOrdersMessageContext l_context) throws
        WEB3SystemLayerException, WEB3BaseException;

    /**
     * �i�蓮�����t�������������j<br />
     * <br />
     * �蓮�����t�������������B<br />
     * <br />
     * @@param l_context - �����������e�R���e�N�X�g<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendManualSubmitConOrder(WEB3RlsManualSubmitConOrderMessageContext
                                         l_context) throws
        WEB3SystemLayerException, WEB3BaseException;

}
@
