head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.45.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccProductInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�������������(WEB3SuccProductInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 ������(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�A�������������)<BR>
 * �A�������������<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3SuccProductInfo extends Message 
{
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (�A�������������)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 431F848A0081
     */
    public WEB3SuccProductInfo() 
    {
     
    }
}
@
