head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���g�єԍ��E�Ζ�����ύX�\�����̓��X�|���X(WEB3AccInfoMobileOfficeRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���q�l���g�єԍ��E�Ζ�����ύX�\�����̓��X�|���X)<BR>
 * ���q�l���g�єԍ��E�Ζ�����ύX�\�����̓��X�|���X<BR>
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3AccInfoMobileOfficeRegistInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_mobileOfficeRegistInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082153L;

    /**
     * (�ύX����)<BR>
     * <BR>
     * ���@@�ύX�\���ς݂Ŗ����F�̏ꍇ�̂݃Z�b�g�B�ȊOnull�B<BR>
     */
    public WEB3AccInfoMobileOfficeInfo changedMobileOfficeInfo;

    /**
     * @@roseuid 418F39F60222
     */
    public WEB3AccInfoMobileOfficeRegistInputResponse()
    {

    }

    /**
     * (���q�l���g�єԍ��E�Ζ�����ύX�\�����̓��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistInputResponse
     * @@roseuid 41368E510073
     */
    public WEB3AccInfoMobileOfficeRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
