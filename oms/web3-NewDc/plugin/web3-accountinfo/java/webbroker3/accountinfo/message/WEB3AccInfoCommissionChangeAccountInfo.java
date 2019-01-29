head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.57.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommissionChangeAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔���ύX�ڋq���(WEB3AccInfoCommissionChangeAccountInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���C�g (���u) �V�K�쐬
Revesion History : 2008/08/22 ������ (���u) �d�l�ύX�E���f��No.248
Revesion History : 2008/08/26 ������ (���u) �d�l�ύX�E���f��No.249
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.define.WEB3CommisionProductCodeDef;


/**
 * (�萔���ύX�ڋq���)<BR>
 * �萔���ύX�ڋq���<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AccInfoCommissionChangeAccountInfo extends Message 
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
     * ���i�R�[�h�i�萔�����i�R�[�h�j<BR>
     * <BR>
     * 10�F�@@��ꊔ��<BR>
     * 11�F�@@�X������<BR>
     * 12�F�@@�~�j����<BR>
     * 40�F�@@�O������<BR>
     * 50�F�@@�敨<BR>
     * 51�F�@@�I�v�V����<BR>
     */
    public String instrumentsCode;
    
    /**
     * (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     */
    public Date trialStartDate;
    
    /**
     * (�萔���m���D)<BR>
     * �萔���m���D<BR>
     * <BR>
     * �O�O�`�X�X�F�ϑ��萔���ڋq�����o�^�}�X�^�[�̎萔��No.<BR>
     */
    public String commissionNo;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String collectRate;
    
    /**
     * (�K�p�I����)<BR>
     * �K�p�I����<BR>
     */
    public Date trialEndDate;
    
    /**
     * @@roseuid 418F3862002E
     */
    public WEB3AccInfoCommissionChangeAccountInfo() 
    {
     
    }
    
    /**
     * ���g�̃R�s�[���쐬���ԋp����B<BR>
     * <BR>
     * �萔���ύX�ڋq���𐶐�����B<BR>
     * ���������I�u�W�F�N�g�Ɉ����̏��i�R�[�h���Z�b�g����B<BR>
     * �ȊO�̃v���p�e�B�́A���g�Ɠ����l�𐶐������I�u�W�F�N�g�ɃZ�b�g���ԋp����B<BR>
     * @@param l_strProductCode - ���i�R�[�h
     * @@return Object
     * @@roseuid 4146B01003BB
     */
    public Object clone(String l_strProductCode) 
    {
        
        WEB3AccInfoCommissionChangeAccountInfo l_accInfo = new WEB3AccInfoCommissionChangeAccountInfo();
        l_accInfo.accountCode = this.accountCode;
        l_accInfo.branchCode = this.branchCode;
        l_accInfo.collectRate = this.collectRate;
        l_accInfo.commissionNo = this.commissionNo;
        l_accInfo.trialEndDate = this.trialEndDate;
        l_accInfo.trialStartDate = this.trialStartDate;
        
        if (l_strProductCode.equals(WEB3CommisionProductCodeDef.LISTING_STOCK) ||
            //l_strProductCode.equals(WEB3CommisionProductCodeDef.OTC_STOCK) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.MINI_STOCK) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.FOREIGN_EQITY) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.INDEX_FUTURES) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.INDEX_OP))
            
        {
            l_accInfo.instrumentsCode = l_strProductCode;
        }
         return l_accInfo;
    }
}
@
