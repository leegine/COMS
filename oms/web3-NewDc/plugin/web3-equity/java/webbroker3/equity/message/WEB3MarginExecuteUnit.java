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
filename	WEB3MarginExecuteUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p����������N���X(WEB3MarginExecuteUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 ������ (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i�M�p����������j�B<br>
 * <br>
 * �M�p����������N���X
 * @@version 1.0
 */
public class WEB3MarginExecuteUnit extends Message 
{
    
    /**
     * (������)
     */
    public Date executionTimestamp;
    
    /**
     * (��芔��)
     */
    public String execQuantity;
    
    /**
     * (���P��)
     */
    public String execPrice;
    
    /**
     * @@roseuid 414045390173
     */
    public WEB3MarginExecuteUnit() 
    {
     
    }
}
@
