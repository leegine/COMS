head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.10.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCampaignRegistAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔�������L�����y�[���o�^�ڋq���(WEB3AccInfoCampaignRegistAccountInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  ꎉ�(���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�萔�������L�����y�[���o�^�ڋq���)<BR>
 * �萔�������L�����y�[���o�^�ڋq���<BR>
 * <BR>
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AccInfoCampaignRegistAccountInfo extends Message 
{
    
    /**
     * (�萔���L�����y�[������ID)<BR>
     * �萔���L�����y�[������ID<BR>
     */
    public String campaignId;
    
    /**
     * (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * <BR>
     * 10 �F ��ꊔ��<BR>
     * 11 �F JASDAQ<BR>
     * 12 �F �~�j���� <BR>
     * 30 �F �� <BR>
     * 31 �F ���i�X���j <BR>
     */
    public String[] itemCode;
    
    /**
     * (�L�����y�[������)<BR>
     * �L�����y�[������<BR>
     */
    public String campaignName;
    
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
     * (���҃R�[�h)<BR>
     * ���҃R�[�h<BR>
     */
    public String traderCode;
    
    /**
     * (�����J�݋敪)<BR>
     * �����J�݋敪<BR>
     * <BR>
     * 1 �F ��������<BR>
     * 2 �F �M�p����<BR>
     * 3 �F �敨OP����<BR>
     * 4 �F FX����<BR>
     * 5 �F ����������<BR>
     */
    public String accountOpenDiv;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String collectRate;
    
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
     * (�o�^�^�C�v)<BR>
     * �o�^�^�C�v<BR>
     * <BR>
     * 0 �F �����J�ݏ����w��<BR>
     * 1 �F �ʌڋq�w��<BR>
     * 2 �F �����ʌڋq�w��<BR>
     */
    public String registType;
    
    /**
     * (�L���敪)<BR>
     * �L���敪<BR>
     * <BR>
     * 0 �F ����<BR>
     * 1 �F �L��<BR>
     */
    public String activeDiv;
    
    /**
     * @@roseuid 45C0875E01F9
     */
    public WEB3AccInfoCampaignRegistAccountInfo() 
    {
     
    }
}
@
