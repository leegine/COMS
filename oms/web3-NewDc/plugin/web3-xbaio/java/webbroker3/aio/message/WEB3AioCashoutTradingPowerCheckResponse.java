head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.11.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutTradingPowerCheckResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���]�̓`�F�b�N���X�|���X(WEB3AioCashoutTradingPowerCheckResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�o���]�̓`�F�b�N���X�|���X)<BR>
 * �o���]�̓`�F�b�N���X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashoutTradingPowerCheckResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_trading_power_check";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101425L;  
      
    /**
     * @@roseuid 4158E9B50349
     */
    public WEB3AioCashoutTradingPowerCheckResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioCashoutTradingPowerCheckResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }  
}
@
