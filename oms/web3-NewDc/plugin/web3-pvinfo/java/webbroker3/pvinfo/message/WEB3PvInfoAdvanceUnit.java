head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.08.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoAdvanceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���֋����(WEB3PvInfoAdvanceUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���֋����)<BR>
 * ���֋����N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoAdvanceUnit extends Message 
{
    
    /**
     * (���֋�������)<BR>
     * ���֋�������<BR>
     */
    public Date advanceGenDate;
        
    /**
     * (�Ǐؗ]��)<BR>
     * �Ǐؗ]��<BR>
     */
    public String additionalTradingPower = null;
    
    /**
     * (�a����s���z)<BR>
     * �a����s���z<BR>
     */
    public String accountBalanceShortfall = null;
    
    /**
     * (���������z)<BR>
     * ���������z<BR>
     */
    public String payClaimAmount;
    
    /**
     * (���֋����)<BR>
     * �R���X�g���N�^�B<BR>
     */
    public WEB3PvInfoAdvanceUnit()
    {
        
    }
    
}
@
