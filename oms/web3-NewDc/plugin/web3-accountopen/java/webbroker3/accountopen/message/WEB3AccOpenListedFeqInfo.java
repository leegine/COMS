head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenListedFeqInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���O�����(WEB3AccOpenListedFeqInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/13 ����� �V�K�쐬
*/
package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���O�����)<BR>
 * ���O�����<BR>
 * 
 * @@author �����
 * @@version 1.0
 */
public class WEB3AccOpenListedFeqInfo extends Message 
{
    /**
     * (�쐬�敪)<BR>
     * �쐬�敪<BR>
     */          
    public String createDiv;
    
    /**
     * (���n)<BR>
     * ���n<BR>
     */
    public String taxationTran;
    
    /**
     * (�Z���i�J�i�j)<BR>
     * �Z���i�J�i�j<BR>
     */        
    public String addressKana;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public String transferDiv;
    
    /**
     * (��s�R�[�h)<BR>
     * ��s�R�[�h<BR>
     */
    public String financialInstitutionCode;
    
    /**
     * (�x�X�R�[�h)<BR>
     * �x�X�R�[�h<BR>
     */
    public String financialBranchCode;
    
    /**
     * (�a���敪)<BR>
     * �a���敪<BR>
     */
    public String financialAccountDiv;
    
    /**
     * (�����ԍ�)<BR>
     * �����ԍ�<BR>
     */           
    public String financialAccountCode;
    
    /**
     * @@roseuid 41B45E7A033C
     */
    public WEB3AccOpenListedFeqInfo() 
    {
     
    }

}
@
