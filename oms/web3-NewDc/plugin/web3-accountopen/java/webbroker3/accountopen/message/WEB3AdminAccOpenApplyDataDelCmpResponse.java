head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDataDelCmpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������X�|���X(WEB3AdminAccOpenApplyDataDelCmpResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 ���m�a(���u) �V�K�쐬 ���f��No.160
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������X�|���X)<BR>
 * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������X�|���X<BR>
 * <BR>
 * @@author ���m�a
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelCmpResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_data_del_cmp";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200812120952L;

    /**
     * @@roseuid 4940F22B0196
     */
    public WEB3AdminAccOpenApplyDataDelCmpResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     */
    public WEB3AdminAccOpenApplyDataDelCmpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
