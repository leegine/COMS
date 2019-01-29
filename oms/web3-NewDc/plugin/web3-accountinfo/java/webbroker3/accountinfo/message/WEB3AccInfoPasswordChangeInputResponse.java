head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoPasswordChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���Ïؔԍ��ύX���̓��X�|���X(WEB3AccInfoPasswordChangeInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���q�l���Ïؔԍ��ύX���̓��X�|���X)<BR>
 * ���q�l���Ïؔԍ��ύX���̓��X�|���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoPasswordChangeInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_passwordChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082151L;

    /**
     * (�p�X���[�h�ŏ���)<BR>
     * �p�X���[�h�ŏ���<BR>
     */
    public String passwordLower;

    /**
     * (�p�X���[�h�ő咷)<BR>
     * �p�X���[�h�ő咷<BR>
     */
    public String passwordUpper;

    /**
     * (�p�X���[�h�`�F�b�N����)<BR>
     * �p�X���[�h�`�F�b�N����<BR>
     * <BR>
     * 1�F�@@�����̂�<BR>
     * 2�F�@@�p����<BR>
     * 3�F�@@�p��������<BR>
     * <BR>
     * �� WEB3CodeCheckModeDef�ɂĒ�`��<BR>
     * <BR>
     */
    public String passwordCheckType;

    /**
     * @@roseuid 418F39F70213
     */
    public WEB3AccInfoPasswordChangeInputResponse()
    {

    }

    /**
     * (���q�l���Ïؔԍ��ύX���̓��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeInputResponse
     * @@roseuid 416CAA8E0153
     */
    public WEB3AccInfoPasswordChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
