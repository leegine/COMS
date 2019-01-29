head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.07.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoStopStateChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����~�󋵕ύX���̓��X�|���X(WEB3AdminAccInfoStopStateChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l����~�󋵕ύX���̓��X�|���X)<BR>
 * �Ǘ��҂��q�l����~�󋵕ύX���̓��X�|���X<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminAccInfoStopStateChangeInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_stopStateChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082101L;

    /**
     * (���ݓ���)<BR>
     * ���ݓ���<BR>
     */
    public Date currentDate;
    
    /**
     * (�ڋq���i�����j�j<BR>
     * �ڋq���i�����j<BR>
     */
    public String accountName;
    
    /**
     * (���҃R�[�h)<BR>
     * ���҃R�[�h
     */
    public String traderCode;
    
    /**
     * (��~���)<BR>
     * ��~���<BR>
     */
    public WEB3AccInfoStopInfo stopInfo;

    /**
     * @@roseuid 418F386401A5
     */
    public WEB3AdminAccInfoStopStateChangeInputResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l����~�󋵕ύX���̓��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeInputResponse
     * @@roseuid 4166529F0097
     */
    public WEB3AdminAccInfoStopStateChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
