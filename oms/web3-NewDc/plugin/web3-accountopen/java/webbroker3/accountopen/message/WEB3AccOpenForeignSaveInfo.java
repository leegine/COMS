head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.07.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenForeignSaveInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�ݗa���������(WEB3AccOpenForeignSaveInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/11 �ęԍg(���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�O�ݗa���������)<BR>
 * �O�ݗa���������<BR>
 *   
 * @@author �ęԍg
 * @@version 1.0
 */
public class WEB3AccOpenForeignSaveInfo extends Message
{
    /**
     * (�����ԍ�)<BR>
     * �����ԍ�<BR>
     */
    public String financialAccountCode;
    
    /**
     * (�������`�l)<BR>
     * �������`�l<BR>
     */
    public String financialAccountName;
    
    /**
     * (�������`�l�p��)<BR>
     * �������`�l�p��<BR>
     */
    public String financialAccountNameAlpha;
    
    /**
     * (�a���敪)<BR>
     * �a���敪<BR>
     */
    public String financialAccountDiv;

    public WEB3AccOpenForeignSaveInfo()
    {
        
    }
}
@
