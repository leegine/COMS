head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoCommissionCampaignUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔�������L�����y�[�����(WEB3PvInfoCommissionCampaignUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/26 �֔�(���u) �V�K�쐬
Revision History : 2007/03/07 �֔�(���u) �d�l�ύX���f��074
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�萔�������L�����y�[�����)<BR>
 * �萔�������L�����y�[�����N���X<BR>
 * @@author �֔�
 * @@version 1.0
 */
public class WEB3PvInfoCommissionCampaignUnit extends Message
{

    /**
     * (�L�����y�[������)<BR>
     * �萔�������L�����y�[������<BR>
     */
    public String campaignName;

    /**
     * (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * <BR>
     * 10�F��ꊔ��<BR>
     * 11�FJASDAQ<BR>
     * 12�F�~�j����<BR>
     * 30�F��<BR>
     * 31�F���i�X���j<BR>
     * 40�F�O������<BR>
     * 50�F�敨<BR>
     * 51�F�����w��OP<BR>
     */
    public String[] commodityCodeList;
        
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String discountRate;

    /**
     * (�K�p����From)<BR>
     * �K�p����from�i�x�x�x�x�l�l�c�c�j<BR>
     */
    public Date applyStartDate;
        
    /**
     * (�K�p���Ԃs��)<BR>
     * �K�p����To�iYYYYMMDD�j<BR>
     */
    public Date applyEndDate;
    
    /**
     * (�萔�������L�����y�[�����)<BR>
     * �R���X�g���N�^�B<BR>
     */
    public WEB3PvInfoCommissionCampaignUnit()
    {
        
    }
    
}
@
