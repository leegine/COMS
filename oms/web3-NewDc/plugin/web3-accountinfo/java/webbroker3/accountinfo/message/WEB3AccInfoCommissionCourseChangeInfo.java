head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommissionCourseChangeInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔���R�[�X�ύX�\�����(WEB3AccInfoCommissionCourseChangeInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �d�� (���u) �V�K�쐬
Revesion History : 2008/08/22 ������ (���u) �d�l�ύX�E���f��No.248
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�萔���R�[�X�ύX�\�����)<BR>
 * �萔���R�[�X�ύX�\����񃁃b�Z�[�W�N���X<BR>
 * 
 * @@author�@@�d��
 * @@version 1.0
 */
public class WEB3AccInfoCommissionCourseChangeInfo extends Message 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoCommissionCourseChangeInfo.class);
    
    /**
     * (�h�c)<BR>
     * �ϑ��萔���R�[�X�ύX�\���h�c<BR>
     */
    public String id;
    
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
     * ���i�R�[�h�i�萔�����i�R�[�h�j<BR>
     * <BR>
     * 10�F�@@��ꊔ��<BR>
     * 12�F�@@�~�j����<BR>
     * 40�F�@@�O������<BR>
     * 50�F�@@�敨<BR>
     * 51�F�@@�I�v�V����<BR>
     */
    public String instrumentsCode;
    
    /**
     * (�萔���R�[�X)<BR>
     * (�萔���R�[�X�i�萔���R�[�X�R�[�h�j)<BR>
     * <BR>
     * 02�F�@@�藦�萔���i�X�^���_�[�h�j <BR>
     * �i����1���������{�M�p1���������@@�����e���̂݁j<BR> 
     * 03�F�@@��������v<BR> 
     * �i����1�����v�{�M�p1�����v�@@�����e���̂݁j<BR> 
     * 04�F�@@���� <BR>
     * 05�F�@@�����z��<BR> 
     * 06�F�@@���z�{�b�N�X<BR> 
     * 07�F�@@����1�����v�{�M�p1��������<BR> 
     * 08�F�@@����1���������{�M�p1�����v<BR> 
     * 16�F�@@���z�{�b�N�X�i�L�����y�[���j<BR> 
     * 99�F�@@��L�ȊO�i���e���E���̂݁j<BR> 
     * <BR>
     * ���@@�e�R�[�h�̖��̂ɂ��ẮA�،���Ђɂ���ĈႤ�B<BR> 
     * �@@�@@Web�w�ɂāA���̂ɕϊ�����B<BR>
     */
    public String commissionCourse;
    
    /**
     * (�\����)<BR>
     * �\����<BR>
     */
    public Date applyDate;
    
    /**
     * (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     */
    public Date trialStartDate;
    
    /**
     * (�_�E�����[�h�σt���O)<BR>
     * �_�E�����[�h�σt���O<BR>
     * <BR>
     * true�F�_�E�����[�h�ρ@@false�F�_�E�����[�h����<BR>
     */
    public boolean downloadFlag;
    
    /**
     * (����\�t���O)<BR>
     * ����\�t���O<BR>
     * <BR>
     * true�F�@@����\�@@false�F�@@����s��<BR>
     */
    public boolean cancelFlag;
    
    /**
     * (�萔���R�[�X�ύX�\�����)<BR>
     * �R���X�g���N�^<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo
     * @@roseuid 413DB1440151
     */
    public WEB3AccInfoCommissionCourseChangeInfo() 
    {
     
    }
    
    /**
     * ���g�̃R�s�[���쐬���ԋp����B<BR>
     * <BR>
     * �萔���R�[�X�ύX�\�����𐶐�����B<BR>
     * ���������I�u�W�F�N�g�Ɉ����̏��i�R�[�h���Z�b�g����B<BR>
     * �ȊO�̃v���p�e�B�́A���g�Ɠ����l�𐶐������I�u�W�F�N�g�ɃZ�b�g���ԋp����B<BR>
     * @@param l_strProductCode - ���i�R�[�h
     * @@return Object
     * @@roseuid 413EE5E90109
     */
    public Object clone(String l_strProductCode) 
    {
        final String STR_METHOD_NAME = " clone(String l_strProductCode)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoCommissionCourseChangeInfo l_accInfo = new WEB3AccInfoCommissionCourseChangeInfo();
        l_accInfo.accountCode = this.accountCode;
        l_accInfo.applyDate = this.applyDate;
        l_accInfo.branchCode = this.branchCode;
        l_accInfo.cancelFlag = this.cancelFlag;
        l_accInfo.commissionCourse = this.commissionCourse;
        l_accInfo.downloadFlag = this.downloadFlag;
        l_accInfo.id = this.id;
        l_accInfo.trialStartDate = this.trialStartDate;
        
        if (l_strProductCode.equals(WEB3CommisionProductCodeDef.LISTING_STOCK) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.MINI_STOCK) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.FOREIGN_EQITY) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.INDEX_FUTURES) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.INDEX_OP))
            
        {
            l_accInfo.instrumentsCode = l_strProductCode;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_accInfo;

    }
}
@
