head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherRegAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݓ`�[�o�^��t���X�|���X(WEB3AccOpenVoucherRegAcceptResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �A�C��(���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (�����J�ݓ`�[�o�^��t���X�|���X)<BR>
 * �����J�ݓ`�[�o�^��t���X�|���X<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AccOpenVoucherRegAcceptResponse extends WEB3BackResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accOpen_voucherRegAccept";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081614L;

    /**
     * @@roseuid 41B45E7D0271
     */
    public WEB3AccOpenVoucherRegAcceptResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AccOpenVoucherRegAcceptResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
