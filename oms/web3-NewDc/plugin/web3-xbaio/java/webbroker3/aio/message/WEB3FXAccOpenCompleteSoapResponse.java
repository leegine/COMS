head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenCompleteSoapResponse.java;


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
File Name           : FX�����J�݊������X�|���X�iSOAP�ڑ��j(WEB3FXAccOpenCompleteSoapResponse.java)
Revision History    : 2008/04/08 ���u��(���u) �V�K�쐬 ���f��No.837
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (FX�����J�݊������X�|���X�iSOAP�ڑ��j)<BR>
 * FX�����J�݊������X�|���X�iSOAP�ڑ��j�N���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3FXAccOpenCompleteSoapResponse extends WEB3FXAccOpenCompleteResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "fx_acc_open_complete_soap";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200804081448L;

    /**
     * (FX�����J�݊������X�|���X�iSOAP�ڑ��j)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3FXAccOpenCompleteSoapResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FXAccOpenCompleteSoapResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
