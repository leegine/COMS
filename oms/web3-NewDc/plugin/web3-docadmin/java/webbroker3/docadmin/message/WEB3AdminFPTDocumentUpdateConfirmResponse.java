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
filename	WEB3AdminFPTDocumentUpdateConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t���ʍX�V�m�F���X�|���X(WEB3AdminFPTDocumentUpdateConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 �g�C�� (���u) �V�K�쐬 ���f��No.037
*/
package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҋ����@@��t���ʍX�V�m�F���X�|���X)<BR>
 * �Ǘ��ҋ����@@��t���ʍX�V�m�F���X�|���X�N���X<BR>
 *
 * @@author �g�C��
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentUpdateConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_document_update_confirm";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200803031808L;

    /**
     * (�d�q�������R�[�h)<BR>
     * �d�q�������R�[�h<BR>
     */
    public String batoProductCode;

    /**
     * @@roseuid 47CBC5AE003E
     */
    public WEB3AdminFPTDocumentUpdateConfirmResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFPTDocumentUpdateConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
