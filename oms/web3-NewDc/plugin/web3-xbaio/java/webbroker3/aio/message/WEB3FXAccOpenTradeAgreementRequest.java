head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenTradeAgreementRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�����J�ݎ�����Ӄ��N�G�X�g(WEB3FXAccOpenTradeAgreementRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
Revesion History : 2008/05/19 �đo�g(���u) �d�l�ύX ���f��No.865
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX�����J�ݎ�����Ӄ��N�G�X�g) <BR>
 * FX�����J�ݎ�����Ӄ��N�G�X�g�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXAccOpenTradeAgreementRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_acc_open_trade_agreement";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E784690109
     */
    public WEB3FXAccOpenTradeAgreementRequest()
    {
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E784690148
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXAccOpenTradeAgreementResponse(this);
    }
}@
