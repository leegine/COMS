head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFaqListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ⍇���Ǘ����⍇���ꗗ���X�|���X(WEB3AdminFaqListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Җ⍇���Ǘ����⍇���ꗗ���X�|���X)<BR>
 * �Ǘ��Җ⍇���Ǘ����⍇���ꗗ���X�|���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminFaqListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_faq_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412171303L;

    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (�⍇�����)<BR>
     * �⍇�����<BR>
     */
    public WEB3FaqInfo[] faqInfo;

    /**
     * @@roseuid 41C25C080261
     */
    public WEB3AdminFaqListResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFaqListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
