head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInspectInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐR�����(WEB3AccOpenInspectInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 ���H�n (���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����J�ݐR�����)<BR>
 * �����J�ݐR�����
 * 
 * @@author ���H�n
 * @@version 1.0
 */
public class WEB3AccOpenInspectInfo extends Message
{
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h
     */
    public String requestNumber;
    
    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j
     */
    public String accountNameKana;
    
    /**
     * (���N�����N��)<BR>
     * ���N�����N��<BR>
     * <BR>
     * 1�F�����@@2�F�吳�@@3�F���a�@@4�F�����@@9�F�s��
     */
    public String eraBorn;
    
    /**
     * (���N����)<BR>
     * ���N����<BR>
     * <BR>
     * �a��iYYMMDD�j�F6��
     */
    public String bornDate;
    
    /**
     * (�d�b�ԍ�)<BR>
     * �d�b�ԍ�<BR>
     * <BR>
     * ���@@"-"���܂ޕ�����
     */
    public String telephone;
    
    /**
     * (�g�єԍ�)<BR>
     * �g�єԍ�<BR>
     * <BR>
     * ���@@"-"���܂ޕ�����
     */
    public String mobileTelephone;
    
    /**
     * (���[���A�h���X)<BR>
     * ���[���A�h���X
     */
    public String mailAddress;
    
    /**
     * (�Z���P)<BR>
     * �Z���P
     */
    public String address1;
    
    /**
     * (�Z���Q)<BR>
     * �Z���Q
     */
    public String address2;
    
    /**
     * (�Z���R)<BR>
     * �Z���R
     */
    public String address3;
    
    /**
     * (��������)<BR>
     * ��������
     */
    public Date occurredDate;
    
    /**
     * (�R�����)<BR>
     * �R�����<BR>
     * <BR>
     * 1�F����ڋq�`�F�b�N�@@2�F����ڋq�i�����j�`�F�b�N�@@3�FY�q�`�F�b�N
     */
    public String reviewCode;
    
    /**
     * (�R���敪)<BR>
     * �R���敪<BR>
     * <BR>
     * 0�F�R���҂��@@1�F�F�@@2�F�۔F
     */
    public String checkDiv;
    
    /**
     * (�R������)<BR>
     * �R������
     */
    public Date checkDate;
    
    /**
     * (�R���S����)<BR>
     * �R���S����
     */
    public String checkerCode;
    
    /**
     * (�d���敪)<BR>
     * �d���敪
     */
    public String duplicateDiv;
    
    /**
     * (�d�����X�R�[�h)<BR>
     * �d�����X�R�[�h
     */
    public String dupliBranchCode;
    
    /**
     * (�d���ڋq�R�[�h)<BR>
     * �d���ڋq�R�[�h
     */
    public String dupliAccountCode;
    
    /**
     * (�d���ڋq���i�J�i�j)<BR>
     * �d���ڋq���i�J�i�j
     */
    public String dupliAccountNameKana;
    
    /**
     * (�d�����N�����N��)<BR>
     * �d�����N�����N��<BR>
     * <BR>
     * 1�F�����@@2�F�吳�@@3�F���a�@@4�F�����@@9�F�s��
     */
    public String dupliEraBorn;
    
    /**
     * (�d�����N����)<BR>
     * �d�����N����<BR>
     * <BR>
     * �a��iYYMMDD�j�F6��
     */
    public String dupliBornDate;
    
    /**
     * (�d���d�b�ԍ�)<BR>
     * �d���d�b�ԍ�<BR>
     * <BR>
     * ���@@"-"���܂ޕ�����
     */
    public String dupliTelephone;
    
    /**
     * (�d���g�єԍ�)<BR>
     * �d���g�єԍ�<BR>
     * <BR>
     * ���@@"-"���܂ޕ�����
     */
    public String dupliMobileTelephone;
    
    /**
     * (�d�����[���A�h���X)<BR>
     * �d�����[���A�h���X
     */
    public String dupliMailAddress;
    
    /**
     * (�d���Z���P)<BR>
     * �d���Z���P
     */
    public String dupliAddress1;
    
    /**
     * (�d���Z���Q)<BR>
     * �d���Z���Q
     */
    public String dupliAddress2;
    
    /**
     * (�d���Z���R)<BR>
     * �d���Z���R
     */
    public String dupliAddress3;
    
    /**
     * (Y�qID)<BR>
     * Y�qID
     */
    public String yellowAccountId;
    
    /**
     * (Y�q�Ǘ����X�R�[�h)<BR>
     * Y�q�Ǘ����X�R�[�h
     */
    public String yAccountBranchCode;
    
    /**
     * (Y�q�Ɩ��敪)<BR>
     * Y�q�Ɩ��敪
     */
    public String yAccountBusinessDiv;
    
    /**
     * (Y�q�Ǘ�No)<BR>
     * Y�q�Ǘ�No
     */
    public String yAccountMngNo;
    
    /**
     * @@roseuid 44912C0E03A9
     */
    public WEB3AccOpenInspectInfo() 
    {
    }
}
@
