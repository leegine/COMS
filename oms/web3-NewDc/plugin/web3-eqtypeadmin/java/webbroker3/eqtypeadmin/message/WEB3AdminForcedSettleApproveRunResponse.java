head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleApproveRunResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔F�����N�����X�|���X(WEB3AdminForcedSettleApproveLapseRunResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 ��іQ (���u) �V�K�쐬
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔F�����N�����X�|���X)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔F�����N�����X�|���X�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminForcedSettleApproveRunResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_approve_run";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;
    
    /**
     * (���ݎ���)<BR>
     * ���ݎ���<BR>
     */
    public Date currentTime;
    
    /**
     * @@roseuid 462CA4270370
     */
    public WEB3AdminForcedSettleApproveRunResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminForcedSettleApproveRunResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
