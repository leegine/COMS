head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3DocumentDeliverInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ���ʌ�t�Ǘ����(WEB3DocumentDeliverInfoUnit.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/01/28 ���V�� (���u) �V�K�쐬 ���f��050
*/

package webbroker3.login.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���ʌ�t�Ǘ����)<BR>
 * ���ʌ�t�Ǘ����<BR>
 * <BR>
 * @@author ���V��
 * @@version 1.0
 */
public class WEB3DocumentDeliverInfoUnit extends Message
{

    /**
     * (���ʋ敪)<BR>
     * ��ʃR�[�h<BR>
     */
    public String documentDiv;

    /**
     * (���ʎ��)<BR>
     * ���ʎ��<BR>
     */
    public String documentCategory;

    /**
     * (�d�q�������R�[�h)<BR>
     * ���ʋ敪�������@@�̏ꍇ<BR>
     * �@@���ʎ��+���ʒʔ�<BR>
     */
    public String batoProductCode;

    /**
     * (��t�σt���O)<BR>
     * 0�F��t����<BR>
     * 1�F��t��<BR>
     * 2�F��t�s�v<BR>
     */
    public String deliverFlag;

    /**
     * @@roseuid 4021A07F0167
     */
    public WEB3DocumentDeliverInfoUnit()
    {

    }
}
@
