head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXCompleteSoapRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
Author Name         : Daiwa Institute of Research
File Name           : FX����U�֊������N�G�X�g�iSOAP�ڑ��j(WEB3FXTransFromFXCompleteSoapRequest.java)
Revision History    : 2008/04/08 ���u��(���u) �V�K�쐬 ���f��No.837
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (FX����U�֊������N�G�X�g�iSOAP�ڑ��j)<BR>
 * FX����U�֊������N�G�X�g�iSOAP�ڑ��j�N���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3FXTransFromFXCompleteSoapRequest extends WEB3FXTransFromFXAskingRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "fx_trans_from_fx_complete_soap";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200804081408L;

    /**
     * (FX����U�֊������N�G�X�g�iSOAP�ڑ��j)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3FXTransFromFXCompleteSoapRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXTransFromFXCompleteSoapResponse(this);
    }
}
@
