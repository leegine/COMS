head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.01.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�݊������[�����M�ꗗ���X�|���X(WEB3AdminAccOpenCompleteMailSendListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 ���w�� �V�K�쐬
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Ҍ����J�݊������[�����M�ꗗ���X�|���X)<BR>
 * �Ǘ��Ҍ����J�݊������[�����M�ꗗ���X�|���X<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminAccOpenCompleteMailSendListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_completeMailSendList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081609L;

    /**
     * (���X�R�[�h)<BR>
     * �l�k���M�Ώۂ̕��X�R�[�h�z��<BR>
     */
    public String[] branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �l�k���M�Ώۂ̌ڋq�R�[�h�z��<BR>
     */
    public String[] accountCode;

    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;

    /**
     * @@roseuid 41B45E75035B
     */
    public WEB3AdminAccOpenCompleteMailSendListResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccOpenCompleteMailSendListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
