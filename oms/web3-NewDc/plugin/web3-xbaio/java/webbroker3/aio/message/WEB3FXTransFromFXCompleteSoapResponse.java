head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXCompleteSoapResponse.java;


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
File Name           : FX����U�֊������X�|���X�iSOAP�ڑ��j(WEB3FXTransFromFXCompleteSoapResponse.java)
Revision History    : 2008/04/08 ���u��(���u) �V�K�쐬 ���f��No.837
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (FX����U�֊������X�|���X�iSOAP�ڑ��j)<BR>
 * FX����U�֊������X�|���X�iSOAP�ڑ��j�N���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3FXTransFromFXCompleteSoapResponse extends WEB3FXTransFromFXCompleteResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "fx_trans_from_fx_complete_soap";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200804081428L;

    /**
     * (FX����U�֊������X�|���X�iSOAP�ڑ��j)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3FXTransFromFXCompleteSoapResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FXTransFromFXCompleteSoapResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
