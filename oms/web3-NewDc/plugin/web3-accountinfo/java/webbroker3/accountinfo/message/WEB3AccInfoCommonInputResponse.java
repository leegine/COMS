head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommonInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񋤒ʓ��̓��X�|���X(WEB3AccInfoCommonInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���q�l��񋤒ʓ��̓��X�|���X)<BR>
 * ���q�l��񋤒ʓ��̓��X�|���X
 * <BR>
 * ���ȉ��̉�ʂŋ��ʂɎg�p����B<BR>
 * �@@�|���[���A�h���X�ύX���͏���<BR>
 * �@@�|�ē����[���z�M�ݒ���́^�m�F����<BR>
 * �@@�|���^����胁�[���z�M�ݒ�ύX���́^�m�F����<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoCommonInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_commonInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082163L;

    /**
     * @@roseuid 418F39EF036B
     */
    public WEB3AccInfoCommonInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AccInfoCommonInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
