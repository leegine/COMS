head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.14.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[����񋤒ʃ��X�|���X(WEB3AdminMailInfoCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���[����񋤒ʃ��X�|���X)<BR>
 * ���[����񋤒ʃ��X�|���X�N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoCommonResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (���M���[���敪)<BR>
     */
    public String sendMailDiv;
    
    /**
     * (����ID)<BR>
     */
    public String discernId;

    /**
     * (���[������)<BR>
     */
    public String mailName;

    /**
     * (���o�l)<BR>
     */
    public String mailFrom;

    /**
     * (���M�惁�[���A�h���X)<BR>
     */
    public String sendAddress;

    /**
     * �i����0<BR>
     */
    public String mailSubject;

    /**
     * �i���[���w�b�_�[�j<BR>
     */
    public String mailHeader;

    /**
     * �i���[���{���j<BR>
     */
    public String mailBody;

    /**
     * �i���[���t�b�^�[�j<BR>
     */
    public String mailFooter;

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 413D54130139
     */
    public WEB3AdminMailInfoCommonResponse()
    {

    }

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminMailInfoCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
