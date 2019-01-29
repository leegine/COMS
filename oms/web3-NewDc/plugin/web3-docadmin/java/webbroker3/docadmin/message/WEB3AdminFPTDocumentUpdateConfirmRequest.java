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
filename	WEB3AdminFPTDocumentUpdateConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t���ʍX�V�m�F���N�G�X�g(WEB3AdminFPTDocumentUpdateConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 �g�C�� (���u) �V�K�쐬 ���f��No.037
*/
package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҋ����@@��t���ʍX�V�m�F���N�G�X�g)<BR>
 * �Ǘ��ҋ����@@��t���ʍX�V�m�F���N�G�X�g�N���X<BR>
 *
 * @@author �g�C��
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentUpdateConfirmRequest extends WEB3AdminFPTDocumentCommonRequest
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentUpdateConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_document_update_confirm";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200803031806L;

    /**
     * @@roseuid 47CBC5AE008C
     */
    public WEB3AdminFPTDocumentUpdateConfirmRequest()
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
         return new WEB3AdminFPTDocumentUpdateConfirmResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B <BR>
     * <BR>
     * super.validate()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BD031903B0
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        super.validate();

        log.exiting(STR_METHOD_NAME);
    }
}
@