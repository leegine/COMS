head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������ւ̐U�֓��̓��N�G�X�g(WEB3FEqConTransferInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O�������ւ̐U�֓��̓��N�G�X�g)<BR>
 * �O�������ւ̐U�֓��̓��N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3FEqConTransferInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "feq_con_transfer_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;

    /**
     * @@roseuid 42354D7700DA
     */
    public WEB3FEqConTransferInputRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �O�������ւ̐U�֓��̓��X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3FEqConTransferInputResponse(this);
    }
}
@