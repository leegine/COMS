head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX���̓��X�|���X(WEB3AdminAccInfoMobileOfficeRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX���̓��X�|���X)<BR>
 * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX���̓��X�|���X<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegistInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082109L;

    /**
     * (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * <BR>
     * 0�F���̑�<BR>
     * 1�F�l�A�J�E���g<BR>
     * 2�F���p�A�J�E���g<BR>
     * 3�F�@@�l�A�J�E���g<BR>
     */
    public String accountType;
    
    /**
     * (�\���󋵋敪)<BR>
     * �\���󋵋敪<BR>
     * <BR>
     * 0�F�@@����҂�<BR>
     * 1�F�@@����҂��i�m�F���j<BR>
     * 2�F�@@����ς�<BR>
     */
    public String applyStateDiv;

    /**
     * (�ύX����)<BR>
     * �ύX����<BR>
     */    
    public WEB3AccInfoMobileOfficeInfo changedMobileOfficeInfo;
    
    /**
     * (�ύX�O���)<BR>
     * �ύX�O���<BR>
     */
    public WEB3AccInfoMobileOfficeInfo preMobileOfficeInfo;
    
    /**
     * @@roseuid 418F385902DE
     */
    public WEB3AdminAccInfoMobileOfficeRegistInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccInfoMobileOfficeRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
