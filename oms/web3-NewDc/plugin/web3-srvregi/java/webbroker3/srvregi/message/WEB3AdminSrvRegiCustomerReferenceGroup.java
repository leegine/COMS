head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerReferenceGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�ڋq�ꗗ�ύX�Ɖ�׈ꗗ�s(WEB3AdminSrvRegiCustomerReferenceGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
Revesion History : 2007/07/24 �h�C(���u) �d�l�ύX���f��294
*/

package webbroker3.srvregi.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�T�[�r�X���p�ڋq�ꗗ�ύX�Ɖ�׈ꗗ�s)<BR>
 * �T�[�r�X���p�ڋq�ꗗ�ύX�Ɖ�׈ꗗ�s�f�[�^�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiCustomerReferenceGroup extends Message 
{
   
    /**
     * (�\���o�^ID)
     */
    public String applyRegId;
    
    /**
     * (���I�敪)<BR>
     * 0:���@@1:�L<BR>
     */
    public String lotteryDiv;
    
    /**
     * (���X�R�[�h)
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)
     */
    public String accountCode;
    
    /**
     * (�\�����I�敪)<BR>
     * 0:���p�@@1:�\���@@2:���I�^�{�\���@@3:���I�@@4:����@@5:�������I�@@6:�S��<BR>
     */
    public String applyLotteryDiv;
    
    /**
     * (�\����)
     */
    public Date applyDate;
    
    /**
     * (�K�p�J�n��)
     */
    public Date trialStartDate;
    
    /**
     * (�K�p�I����)
     */
    public Date trialEndDate;
    
    /**
     * (�����敪)<BR>
     * 0 or null:�������@@1:������<BR>
     */
    public String transactionDiv;
    
    /**
     * (�o�^�敪)<BR>
     * 0:�L���@@1:����<BR>
     */
    public String registDiv;
    
    /**
     * (���p����)
     */
    public String chargeAmt;
    
    /**
     * (�ŏI�X�V��)
     */
    public Date lastUpdateTime;
    
    /**
     * (�ŏI�X�V��)
     */
    public String lastUpdater;
    
    /**
     * (�T�[�r�X���p�ڋq�ꗗ�ύX�Ɖ�׈ꗗ�s)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE5AF2020A
     */
    public WEB3AdminSrvRegiCustomerReferenceGroup() 
    {
     
    }
}
@
