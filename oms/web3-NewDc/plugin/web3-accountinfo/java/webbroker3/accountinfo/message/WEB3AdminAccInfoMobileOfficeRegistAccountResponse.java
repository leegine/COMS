head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistAccountResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋqڽ��ݽ(WEB3AdminAccInfoMobileOfficeRegistAccountResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
                   2006/12/18 ���� (���u) ���f��No.153
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋqڽ��ݽ)<BR>
 * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋqڽ��ݽ<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistAccountResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegistAccount";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082112L;

    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (�ύX�O�\���ڋq�ꗗ)<BR>
     */
    public WEB3AccInfoMobileOfficeChangeAccount[] beforeChangeAccountList;

    /**
     * (�ύX��\���ڋq�ꗗ)<BR>
     */
    public WEB3AccInfoMobileOfficeChangeAccount[] afterChangeAccountList;

    /**
     * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇��ڽ��ݽ)<BR>
     * �R���X�g���N�^<BR>
     * @@param l_request - (l_request)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountResponse
     * @@roseuid 414981B802DA
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
