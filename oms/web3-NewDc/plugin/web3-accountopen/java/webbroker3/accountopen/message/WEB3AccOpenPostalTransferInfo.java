head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenPostalTransferInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �X�֐U�֏��(WEB3AccOpenPostalTransferInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 ���w�� �V�K�쐬
*/
package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�X�֐U�֏��)<BR>
 * �X�֐U�֏��<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AccOpenPostalTransferInfo extends Message 
{
    
    /**
     * (�ʒ��ԍ�)<BR>
     * �ʒ��ԍ�<BR>
     */
    public String passbookCode;
    
    /**
     * (�ʒ��L��)<BR>
     * �ʒ��L��<BR>
     */
    public String passbookSign;
    
    /**
     * @@roseuid 41B45E7902DE
     */
    public WEB3AccOpenPostalTransferInfo() 
    {
     
    }
}
@
