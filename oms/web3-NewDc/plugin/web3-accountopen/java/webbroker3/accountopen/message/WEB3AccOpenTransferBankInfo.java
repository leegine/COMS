head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenTransferBankInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�����s���(WEB3AccOpenTransferBankInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 ���w�� �V�K�쐬
*/
package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�U�����s���)<BR>
 * �U�����s���<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AccOpenTransferBankInfo extends Message 
{
    
    /**
     * (��s�R�[�h)<BR>
     * ��s�R�[�h<BR>
     */
    public String financialInstitutionCode;
    
    /**
     * (��s��)<BR>
     * ��s��<BR>
     */
    public String financialInstitutionName;
    
    /**
     * (�x�X�R�[�h)<BR>
     * �x�X�R�[�h<BR>
     */
    public String financialBranchCode;
    
    /**
     * (�x�X��)<BR>
     * �x�X��<BR>
     */
    public String financialBranchName;
    
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
     * (�U�֎萔���敪)<BR>
     * �U�֎萔���敪<BR>
     * <BR>
     * �i�� ���ڒl�ɂ��Ă͊e�ЃJ�X�^�}�C�Y����j<BR>
     */
    public String transferCommissionDiv;
    
    /**
     * @@roseuid 41B45E790222
     */
    public WEB3AccOpenTransferBankInfo() 
    {
     
    }
}
@
