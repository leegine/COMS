head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXCompleteSoapRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�ւ̐U�֊������N�G�X�g�iSOAP�ڑ��j(WEB3FXTransToFXCompleteSoapRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/09 �]�V�q(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX�ւ̐U�֊������N�G�X�g�iSOAP�ڑ��j)<BR>
 * FX�ւ̐U�֊������N�G�X�g�iSOAP�ڑ��j�N���X<BR>
 *
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3FXTransToFXCompleteSoapRequest extends WEB3FXTransToFXAskingRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_to_fx_complete_soap";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200602091650L;

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3FXTransToFXCompleteSoapRequest()
    {
        
    }
    
    /**
     * (createResponse�̎���)<BR>
     * <BR>
     * FX�ւ̐U�֊������X�|���X�iSOAP�ڑ��j�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXTransToFXCompleteSoapResponse(this);
    }
}
@
