head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ����m�F���X�|���X(WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/12/14 ���� (���u) �V�K�쐬 ���f��No.153
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ����m�F���X�|���X)<BR>
 * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ����m�F���X�|���X
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegAccConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200612141400L;

    /**
     * (�ύX�O�\���ڋq�ꗗ)<BR>
     */
    public WEB3AccInfoMobileOfficeChangeAccount[] beforeChangeAccountList;

    /**
     * (�ύX��\���ڋq�ꗗ)<BR>
     */
    public WEB3AccInfoMobileOfficeChangeAccount[] afterChangeAccountList;

    /**
     * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ����m�F���X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     */
    public WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@