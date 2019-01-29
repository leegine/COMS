head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutTradingPowerCheckRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���]�̓`�F�b�N���N�G�X�g(WEB3AioCashoutTradingPowerCheckRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (�o���]�̓`�F�b�N���N�G�X�g)<BR>
 * �o���]�̓`�F�b�N���N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashoutTradingPowerCheckRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_trading_power_check";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101355L;    
    
    /**
     * (�،���ЃR�[�h)
     */
    public String institutionCode;
    
    /**
     * (�����t���O)<BR>
     * �����t���O<BR>
     * <BR>
     * "0"�F �S���f�[�^����<BR>
     * "1"�F �����U�����f�[�^����<BR>
     * "2"�F �����U�����f�[�^����<BR>
     */
    public String processFlag;
    
    /**
     * @@roseuid 4158E9B60001
     */
    public WEB3AioCashoutTradingPowerCheckRequest() 
    {
     
    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �o���]�̓��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3BackResponse
     * @@roseuid 4158E9B60015
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioCashoutTradingPowerCheckResponse(this);
    }
}
@
