head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleApproveStatusResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔F�����X�e�[�^�X�m�F���X�|���X(WEB3AdminForcedSettleApproveLapseStatusResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 ��іQ (���u) �V�K�쐬
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔F�����X�e�[�^�X�m�F���X�|���X)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔F�����X�e�[�^�X�m�F���X�|���X�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminForcedSettleApproveStatusResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_approve_status";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;
    
    /**
     * (�����X�e�[�^�X)<BR>
     * �����X�e�[�^�X<BR>
     * <BR>
     * 1�F�@@������<BR>
     * 5�F�@@������<BR>
     * 9�F�@@�G���[<BR>
     */
    public String lapseStatus;
    
    /**
     * (���ݎ���)<BR>
     * ���ݎ���<BR>
     */
    public Date currentTime;
    
    /**
     * @@roseuid 462CA42800FF
     */
    public WEB3AdminForcedSettleApproveStatusResponse()
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminForcedSettleApproveStatusResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
