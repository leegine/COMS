head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginProductCodeNameUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p��������R�[�h���̃N���X(WEB3MarginProductCodeNameUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 ������ (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i�M�p��������R�[�h���́j�B<br>
 * <br>
 * �M�p��������R�[�h���̃N���X
 * @@version 1.0
 */
public class WEB3MarginProductCodeNameUnit extends Message 
{
    
    /**
     * (�����R�[�h)<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     */
    public String productName;
    
    /**
     * (�M�p��������R�[�h����)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 40C930B3015E
     */
    public WEB3MarginProductCodeNameUnit() 
    {
     
    }
}
@
