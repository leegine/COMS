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
filename	WEB3AdminEquityForcedSettleOrderApproveMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔F���C�����X�|���X(WEB3AdminEquityForcedSettleOrderApproveMainResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 ��іQ (���u) �V�K�쐬
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔F���C�����X�|���X)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔F���C�����X�|���X�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleOrderApproveMainResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_forced_settle_order_approve_main";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;
    
    /**
     * @@roseuid 462CA42601AA
     */
    public WEB3AdminEquityForcedSettleOrderApproveMainResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminEquityForcedSettleOrderApproveMainResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
