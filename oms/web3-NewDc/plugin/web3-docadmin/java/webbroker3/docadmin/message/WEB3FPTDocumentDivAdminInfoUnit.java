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
filename	WEB3FPTDocumentDivAdminInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʋ敪�Ǘ����(WEB3FPTDocumentDivAdminInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 ���g (���u) �V�K�쐬
Revision History : 2008/01/25 ���n�m (���u) �d�l�ύX�E���f��No.022
*/

package webbroker3.docadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���ʋ敪�Ǘ����)<BR>
 * ���ʋ敪�Ǘ����<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3FPTDocumentDivAdminInfoUnit extends Message
{

    /**
     * (���ʋ敪)<BR>
     * ���ʋ敪<BR>
     */
    public String documentDiv;

    /**
     * (���ʖ���)<BR>
     * ���ʖ���<BR>
     */
    public String documentNames;

    /**
     * (���ʃ`�F�b�N�敪)<BR>
     * ���ʃ`�F�b�N�敪<BR>
     * <BR>
     * 1�FIPO<BR>
     * 2�F���M<BR>
     * 3�F�����@@<BR>
     */
    public String docuCheckDiv;

    /**
     * (���ʎ�ރR�[�h)<BR>
     * ���ʎ�ރR�[�h<BR>
     */
    public String documentCategory;

    /**
     * (���ʎ�ޖ���)<BR>
     * ���ʎ�ޖ���<BR>
     */
    public String documentCategoryName;

    /**
     * @@roseuid 46FDDD3D02AF
     */
    public WEB3FPTDocumentDivAdminInfoUnit()
    {

    }
}
@
