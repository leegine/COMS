head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommissionCampaignInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔�������L�����y�[�����(WEB3AccInfoCommissionCampaignInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/25  �g��i(���u) �V�K�쐬
Revision History : 2007/03/06  �d�l�ύX�E���f��206
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�萔�������L�����y�[�����)<BR>
 * �萔�������L�����y�[�����b�Z�[�W<BR>
 * <BR>
 * @@author �g��i
 * @@version 1.0
 */
public class WEB3AccInfoCommissionCampaignInfo extends Message
{
    
    /**
     * (�L�����y�[������)<BR>
     * �L�����y�[������<BR>
     */
    public String campaignName;
    
    /**
     * (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * 10�F��ꊔ��<BR>
     * 11�FJASDAQ<BR>
     * 12�F�~�j����<BR>
     * 30�F��<BR>
     * 31�F���i�X���j<BR>
     * 40�F�O������<BR>
     * 50�F�敨<BR>
     * 51�F�����w��OP<BR>
     */
    public String[] itemCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String discountRate;
    
    /**
     * (�K�p����From)<BR>
     * �K�p����from�iYYYYMMDD�j<BR>
     */
    public Date targetPeriodFrom;

    /**
     * (�K�p����To)<BR>
     * �K�p����to�iYYYYMMDD�j<BR>
     */
    public Date targetPeriodTo;
    
    public WEB3AccInfoCommissionCampaignInfo() 
    {
     
    }
}
@
