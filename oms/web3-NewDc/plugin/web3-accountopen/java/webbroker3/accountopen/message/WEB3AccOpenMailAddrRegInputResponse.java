head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.05.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMailAddrRegInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݃��[���A�h���X�o�^���̓��X�|���X(WEB3AccOpenMailAddrRegInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 �����F(���u) �V�K�쐬 ���f�� 162
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����J�݃��[���A�h���X�o�^���̓��X�|���X)<BR>
 * �����J�݃��[���A�h���X�o�^���̓��X�|���X<BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AccOpenMailAddrRegInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "acc_open_mail_addr_reg_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200908101113L;

    /**
     *
     */
    public WEB3AccOpenMailAddrRegInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AccOpenMailAddrRegInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
