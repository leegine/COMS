head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ManualCommissionInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �蓮�����萔�����(WEB3ManualCommissionInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�蓮�����萔�����)<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3ManualCommissionInfoUnit extends Message 
{
    
    /**
     * (�萔���R�[�X)<BR>
     * �萔���R�[�X<BR>
     */
    public String commissionCourse = null;
    
    /**
     * (�萔��)<BR>
     * �萔��<BR>
     */
    public String commission = null;
    
    /**
     * (�萔�������)<BR>
     * �萔�������<BR>
     */
    public String commissionConsumptionTax = null;
    
    /**
     * (�蓮�����萔�����)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 43F48892038A
     */
    public WEB3ManualCommissionInfoUnit() 
    {
     
    }
}
@
