head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenVoucherMakeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݓ`�[�쐬�������X�|���X(WEB3AdminAccOpenVoucherMakeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 �s�p (���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Ҍ����J�ݓ`�[�쐬�������X�|���X)<BR>
 * �Ǘ��Ҍ����J�ݓ`�[�쐬�������X�|���X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */

public class WEB3AdminAccOpenVoucherMakeCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_voucherMakeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081602L;

    /**
     * (�쐬�����`�[�R�[�h�ꗗ)<BR>
     * �쐬�����`�[�R�[�h�ꗗ<BR>
     */
    public String[] creationCompleteList;

    /**
     * @@roseuid 41B45E7802FD
     */
    public WEB3AdminAccOpenVoucherMakeCompleteResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccOpenVoucherMakeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
