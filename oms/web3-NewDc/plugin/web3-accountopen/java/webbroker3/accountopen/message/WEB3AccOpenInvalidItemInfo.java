head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.03.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInvalidItemInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �s�����ڏ��(WEB3AccOpenInvalidItemInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 ���w�� �V�K�쐬
*/
package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�s�����ڏ��)<BR>
 * �s�����ڏ��<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AccOpenInvalidItemInfo extends Message 
{
    
    /**
     * (�s�����ږ�)<BR>
     * �s�����ږ�<BR>
     */
    public String invalidItemName;
    
    /**
     * (�R�����g)<BR>
     * �R�����g<BR>
     */
    public String comment;
    
    /**
     * (�����t���O)<BR>
     * �����t���O<BR>
     * <BR>
     * true�F�@@�����`�F�b�N����<BR>
     * false�F�@@�����`�F�b�N�Ȃ�<BR>
     */
    public boolean compFlag;
    
    /**
     * @@roseuid 41B45E7A033C
     */
    public WEB3AccOpenInvalidItemInfo() 
    {
     
    }
}
@
