head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailDistributionCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���ē����[���z�M�w������m�F����(WEB3AdminAccInfoMailDistributionCancelConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 �d�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҂��q�l���ē����[���z�M�w������m�F����)<BR>
 *  �Ǘ��҂��q�l���ē����[���z�M�w������m�F����<BR>
 */
public class WEB3AdminAccInfoMailDistributionCancelConfirmResponse extends WEB3GenResponse
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailDistributionCancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412131129L;

    /**
     * @@roseuid 418F38570138
     */
    public WEB3AdminAccInfoMailDistributionCancelConfirmResponse ()
    {

    }

    /**
     * (�Ǘ��҂��q�l���ē����[���z�M�w������m�F����)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelConfirmResponse
     * @@roseuid 4136B90D03C8
     */
    public WEB3AdminAccInfoMailDistributionCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}


@
