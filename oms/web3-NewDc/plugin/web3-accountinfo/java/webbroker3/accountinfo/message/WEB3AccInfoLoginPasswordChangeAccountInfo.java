head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLoginPasswordChangeAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �p�X���[�h�ύX�ڋq���(WEB3AccInfoLoginPasswordChangeAccountInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�p�X���[�h�ύX�ڋq���)<BR>
 * �p�X���[�h�ύX�ڋq���<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0 
 */
public class WEB3AccInfoLoginPasswordChangeAccountInfo extends Message 
{
    
    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * (�ڋq��)<BR>
     * �ڋq��<BR>
     */
    public String accountName;
    
    /**
     * (�X�V��)<BR>
     * �X�V��<BR>
     */
    public Date updateDate;
    
    /**
     * (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     */
    public String updaterCode;
    
    /**
     * @@roseuid 418F385501E4
     */
    public WEB3AccInfoLoginPasswordChangeAccountInfo() 
    {
     
    }
}
@
