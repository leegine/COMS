head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXCompleteSoapResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�ւ̐U�֊������X�|���X�iSOAP�ڑ��j(WEB3FXTransToFXCompleteSoapResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/09 �]�V�q(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (FX�ւ̐U�֊������X�|���X�iSOAP�ڑ��j)<BR>
 * FX�ւ̐U�֊������X�|���X�iSOAP�ڑ��j�N���X<BR>
 *
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3FXTransToFXCompleteSoapResponse extends WEB3FXTransToFXCompleteResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_to_fx_complete_soap";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200602091550L;
    
    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3FXTransToFXCompleteSoapResponse()
    {
        
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FXTransToFXCompleteSoapResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
