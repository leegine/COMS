head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoIpoProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO���I�������(WEB3PvInfoIpoProductUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
*/
package webbroker3.pvinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (IPO���I�������)<BR>
 * IPO���I�������N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoIpoProductUnit extends Message 
{
    
    /**
     * (���I������)<BR>
     * ���I������<BR>
     */
    public String lotWinProductName;
    
    /**
     * (���I����)<BR>
     * ���I����<BR>
     */
    public String lotWinCount;
    
    /**
     * (IPO���I�������)<BR>
     * �R���X�g���N�^�B<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoIpoProductUnit
     * @@roseuid 4145615101A4
     */
    public WEB3PvInfoIpoProductUnit() 
    {
     
    }
}
@
