head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTDocumentListSearchInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t���ʏƉ�����̓��N�G�X�g(WEB3AdminFPTDocumentListSearchInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 �g�C�� (���u) �V�K�쐬 ���f��No.037
*/
package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҋ����@@��t���ʏƉ�����̓��N�G�X�g)<BR>
 * �Ǘ��ҋ����@@��t���ʏƉ�����̓��N�G�X�g�N���X<BR>
 *
 * @@author �g�C��
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentListSearchInputRequest extends WEB3GenRequest
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentListSearchInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_document_list_search_input";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200803031759L;

    /**
     * @@roseuid 47CBC5AE01B5
     */
    public WEB3AdminFPTDocumentListSearchInputRequest()
    {

    }

    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFPTDocumentListSearchInputResponse(this);
    }
}
@
