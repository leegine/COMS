head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.48.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPwdLockCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ����߽ܰ��ۯ���������ڽ��ݽ(WEB3AdminMCAdminPwdLockCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҃��j���[����Ǘ����߽ܰ��ۯ���������ڽ��ݽ)<BR>
 * �Ǘ��҃��j���[����Ǘ����߽ܰ��ۯ���������ڽ��ݽ<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPwdLockCancelCompleteResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminPwdLockCancelComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * (�Ǘ��҃��j���[����Ǘ����߽ܰ��ۯ���������ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteResponse
     * @@roseuid 4175DF9F02A2
     */
    public WEB3AdminMCAdminPwdLockCancelCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
