head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.01.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCampaignInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔�������L�����y�[���������(WEB3AccInfoCampaignInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  ꎉ�(���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�萔�������L�����y�[���������)<BR>
 * �萔�������L�����y�[���ʌڋq�w����<BR>
 * <BR>
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AccInfoCampaignInfo extends Message 
{
    
    /**
     * (�萔�������L�����y�[������ID)<BR>
     * �萔�������L�����y�[������ID<BR>
     */
    public String campaignId;
    
    /**
     * (�L�����y�[������)<BR>
     * �L�����y�[������<BR>
     */
    public String campaignName;
    
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
     * (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     */
    public String[] itemCode;
    
    /**
     * (�Ώۊ���From)<BR>
     * �Ώۊ���From<BR>
     */
    public Date targetPeriodFrom;
    
    /**
     * (�Ώۊ���To)<BR>
     * �Ώۊ���To<BR>
     */
    public Date targetPeriodTo;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String collectRate;
    
    /**
     * (�����J�݌o�ߊ��ԁi���j)<BR>
     * �����J�݌o�ߊ��ԁi���j<BR>
     */
    public String accopenPassPeriodMonth;
    
    /**
     * (�����J�݌o�ߊ��ԁi���j)<BR>
     * �����J�݌o�ߊ��ԁi���j<BR>
     */
    public String accopenPassPeriodDay;
    
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
     * (�����J�ݓ�From)<BR>
     * �����J�ݓ�From<BR>
     */
    public Date accountOpenDateFrom;
    
    /**
     * (�����J�ݓ�To)<BR>
     * �����J�ݓ�To<BR>
     */
    public Date accountOpenDateTo;
    
    /**
     * (�o�^�^�C�v)<BR>
     * �o�^�^�C�v<BR>
     * <BR>
     * 0�F �����J�ݏ����ݒ�<BR>
     * 1�F �ʌڋq�w��<BR>
     * 2�F �����ʌڋq�w��<BR>
     */
    public String registType;
    
    /**
     * (�폜�t���O)<BR>
     * �폜�t���O<BR>
     */
    public String deleteFlag;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     */
    public String transactionDiv;
    
    /**
     * (�o�^��)<BR>
     * �o�^��<BR>
     */
    public String registrant;
    
    /**
     * (�o�^��)<BR>
     * �o�^��<BR>
     */
    public Date registDate;
    
    /**
     * (�X�V��)<BR>
     * �X�V��<BR>
     */
    public Date updateDate;
    
    /**
     * @@roseuid 45C0875E0063
     */
    public WEB3AccInfoCampaignInfo() 
    {
     
    }
}
@
