head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�ւ̐U�֓��̓��N�G�X�g(WEB3FXTransToFXInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
                 : 2006/04/26 ������ (���u) �d�l�ύX�E���f��533
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX�ւ̐U�֓��̓��N�G�X�g) <BR>
 * FX�ւ̐U�֓��̓��N�G�X�g�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransToFXInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_to_fx_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;
    
    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     */
    public String fxSystemCode;
    
    /**
     * @@roseuid 41E780B2008C
     */
    public WEB3FXTransToFXInputRequest()
    {
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E780B200CB
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXTransToFXInputResponse(this);
    }
}@
