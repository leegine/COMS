head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCampaignSearchCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔�������L�����y�[����������(WEB3AccInfoCampaignSearchCondition.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  ꎉ�(���u) �V�K�쐬
Revision History : 2007/2/1  ���f��No.165
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�萔�������L�����y�[����������)<BR>
 * �L�����y�[����������<BR>
 * <BR>
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AccInfoCampaignSearchCondition extends Message 
{
    
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
     * (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     */
    public String itemCode;
    
    /**
     * (�L�����y�[������)<BR>
     * �L�����y�[������
     */
    public String campaignName;
    
    /**
     * (�Ώۓ�)<BR>
     * �Ώۓ�
     */
    public Date targetDate;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String collectRate;
    
    /**
     * (���҃R�[�h)<BR>
     * ���҃R�[�h<BR>
     */
    public String traderCode;
    
    /**
     * (�����J�݋敪)<BR>
     * �����J�݋敪<BR>
     */
    public String accountOpenDiv;
    
    /**
     * (�폜�t���O)<BR>
     * �폜�t���O
     */
    public String deleteFlag;
    
    /**
     * @@roseuid 45C0875E0312
     */
    public WEB3AccInfoCampaignSearchCondition() 
    {
     
    }
}
@
