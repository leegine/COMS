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
filename	WEB3FPTDocumentUpdateInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʍX�V���(WEB3FPTDocumentUpdateInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 �g�C�� (���u) �V�K�쐬 ���f��No.037
*/
package webbroker3.docadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���ʍX�V���)<BR>
 * ���ʍX�V���N���X<BR>
 *
 * @@author �g�C��
 * @@version 1.0
 */
public class WEB3FPTDocumentUpdateInfoUnit extends Message
{

    /**
     * (���ʋ敪)<BR>
     * ���ʋ敪<BR>
     */
    public String documentDiv;

    /**
     * (���ʎ�ރR�[�h)<BR>
     * ���ʎ�ރR�[�h<BR>
     */
    public String documentCategory;

    /**
     * (���ʒʔ�)<BR>
     * ���ʒʔ�<BR>
     */
    public String documentNumber;

    /**
     * (�L���敪)<BR>
     * �L���敪<BR>
     * <BR>
     * 0�Fvalid<BR>
     * 1�Finvalid<BR>
     */
    public String validFlag;

    /**
     * (�E�v)<BR>
     * �E�v<BR>
     */
    public String remarks;

    /**
     * (���ʎ�ޖ���)<BR>
     * ���ʎ�ޖ���<BR>
     */
    public String documentCategoryName;

    /**
     * (�d�q�������R�[�h)<BR>
     * �d�q�������R�[�h<BR>
     */
    public String batoProductCode;

    /**
     * @@roseuid 47CBC5AD030D
     */
    public WEB3FPTDocumentUpdateInfoUnit()
    {

    }
}
@
