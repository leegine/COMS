head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDataDelCnfResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�m�F���X�|���X(WEB3AdminAccOpenApplyDataDelCnfResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 ���m�a(���u) �V�K�쐬 ���f��No.160
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�m�F���X�|���X)<BR>
 * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�m�F���X�|���X<BR>
 * <BR>
 * @@author ���m�a
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelCnfResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_data_del_cnf";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200812121037L;

    /**
     * (�ڋq���i�����j)<BR>
     * �ڋq���i�����j<BR>
     */
    public String accountFamilyName;

    /**
     * (�ڋq���i�����j)<BR>
     * �ڋq���i�����j<BR>
     */
    public String accountName;

    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j<BR>
     */
    public String accountFamilyNameKana;

    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j<BR>
     */
    public String accountNameKana;

    /**
     * (�Z���P)<BR>
     * �Z���P<BR>
     */
    public String address1;

    /**
     * (�Z���Q)<BR>
     * �Z���Q<BR>
     */
    public String address2;

    /**
     * (�Z���R)<BR>
     * �Z���R<BR>
     */
    public String address3;

    /**
     * (�Z���P�i�J�i�j)<BR>
     * �Z���P�i�J�i�j<BR>
     */
    public String addressKana1;

    /**
     * (�Z���Q�i�J�i�j)<BR>
     * �Z���Q�i�J�i�j<BR>
     */
    public String addressKana2;

    /**
     * (�Z���R�i�J�i�j)<BR>
     * �Z���R�i�J�i�j<BR>
     */
    public String addressKana3;

    /**
     * (�d�b�ԍ�)<BR>
     * �d�b�ԍ�<BR>
     */
    public String telephone;

    /**
     * @@roseuid 4940F22B01F4
     */
    public WEB3AdminAccOpenApplyDataDelCnfResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     */
    public WEB3AdminAccOpenApplyDataDelCnfResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
