head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������̓��X�|���X(WEB3AdminAccInfoAccEstablishSearchInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09  �����q(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������̓��X�|���X)<BR>
 * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������̓��X�|���X<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0 
 */

public class WEB3AdminAccInfoAccEstablishSearchInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_info_acc_establish_search_input";
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610082152L;

    /**
     * @@roseuid 418F39F700AB
     */
    public WEB3AdminAccInfoAccEstablishSearchInputResponse()
    {

    }
    
    /**
     * (�����J�ݓ��i���j)<BR>
     * �����J�ݓ��i���j<BR> 
     */
    public Date accountOpenDateFrom;
    
    /**
     * (�����J�ݓ��i���j)<BR>
     * �����J�ݓ��i���j<BR> 
     */
    public Date accountOpenDateTo;
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccInfoAccEstablishSearchInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
