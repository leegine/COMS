head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCommissionInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����萔�����(WEB3MarginCommissionInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i�M�p����萔�����j�B<br>
 * <br>
 * �M�p����萔�����N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCommissionInfoUnit extends Message 
{
    
    /**
     * (�萔���R�[�X)
     */
    public String commissionCourse;
    
    /**
     * (�萔��)
     */
    public String commission;
    
    /**
     * (�萔�������)
     */
    public String commissionConsumptionTax;
    
    /**
     * (�M�p����萔�����)<BR>
     * �R���X�g���N�^�B<BR>
     * @@return WEB3MarginCommissionInfoUnit
     * @@roseuid 40C930670323
     */
    public WEB3MarginCommissionInfoUnit() 
    {
     
    }
}
@
