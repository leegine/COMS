head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.30.05.57.24;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d92c6286688;
filename	WEB3FPTDocumentInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (��)��a�����r�W�l�X�E�C�m�x�[�V����
 File Name           : ���ʏ��N���X(WEB3FPTDocumentInfoUnit.java)
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/17 �����C(�k�����u) �V�K�쐬 �d�l�ύX���f��No.354
 */
package webbroker3.gentrade.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���ʏ��)<BR>
 * ���ʏ��N���X<BR>
 * <BR>
 * @@author �����C(�k�����u)
 * @@version 1.0 
 */
public class WEB3FPTDocumentInfoUnit extends Message
{
    /**
     * (���ʋ敪)<BR>
     * ���ʋ敪<BR>
     */
    public String documentDiv;

    /**
     * (���ʎ��)<BR>
     * ���ʎ��<BR>
     */
    public String documentCategory;

    /**
     * (�d�q�������R�[�h)<BR>
     * �d�q�������R�[�h<BR>
     */
    public String batoProductCode;

    /**
     * (��t�σt���O)<BR>
     * ��t�σt���O<BR>
     */
    public String deliverFlag;

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3FPTDocumentInfoUnit()
    {

    }
}
@
