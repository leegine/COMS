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
filename	WEB3AdminForcedSettleReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ϒ����Ɖ�X�|���X(WEB3AdminForcedSettleReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 ��іQ (���u) �V�K�쐬
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�������ϒ����Ɖ�X�|���X)<BR>
 * �Ǘ��ҁE�������ϒ����Ɖ�X�|���X�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminForcedSettleReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages = "0";
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords = "0";
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex = "0";
    
    /**
     * (�������ϒ����ꗗ)<BR>
     * �������ϒ����ꗗ<BR>
     */
    public WEB3AdminForcedSettleTemporaryOrderUnit[] forcedSettleTemporaryOrderList;
    
    /**
     * @@roseuid 462CA42802A4
     */
    public WEB3AdminForcedSettleReferenceResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminForcedSettleReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
