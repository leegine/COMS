head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkCommissionInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����萔�����(WEB3MstkCommissionInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12  �d��(���u) �V�K�쐬
*/
package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * �i�����~�j�����萔�����j�B<BR>
 * <BR>
 * �����~�j�����萔�����N���X
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3MstkCommissionInfoUnit extends Message 
{
    
    /**
     * �i�萔���R�[�X�j�B
     */
    public String commissionCourse;
    
    /**
     * �i�萔���j�B
     */
    public String commission;
    
    /**
     * �i�萔������Łj�B
     */
    public String commissionConsumptionTax;
    
    /**
     * �i�����~�j�����萔�����j�B<BR>
     * <BR>
     * �R���X�g���N�^�B
     */
    public WEB3MstkCommissionInfoUnit() 
    {
     
    }
}
@
