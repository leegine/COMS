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
filename	WEB3FPTDocumentCategoryDetailsInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʎ�ޏڍ׏��(WEB3FPTDocumentCategoryDetailsInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/25 ���n�m (���u) �V�K�쐬�E���f��No.022
*/
package webbroker3.docadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���ʎ�ޏڍ׏��)<BR>
 * ���ʎ�ޏڍ׏��<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3FPTDocumentCategoryDetailsInfoUnit extends Message
{

    /**
     * (���ʋ敪�Ǘ����)<BR>
     * ���ʋ敪�Ǘ����<BR>
     */
    public WEB3FPTDocumentDivAdminInfoUnit documentDivList;

    /**
     * (�d�q�������R�[�h�Ǘ����)<BR>
     * �d�q�������R�[�h�Ǘ����<BR>
     */
    public WEB3FPTBatoProductCodeAdminInfoUnit[] batoProductCodeAdminInfo;

    /**
     * (���ʎ�ޏڍ׏��)<BR>
     * �f�B�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3FPTDocumentCategoryDetailsInfoUnit()
    {

    }

}
@
