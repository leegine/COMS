head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenStateUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݏ�(WEB3AccOpenStateUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �A�C��(���u) �V�K�쐬
                 : 2006/06/08 ����(���u) �d�l�ύX�E���f��050
                   2006/09/19 �ęԍg (���u) �d�l�ύX ���f��098
Revesion History : 2009/08/10 �����F (���u) �d�l�ύX ���f��163
Revesion History : 2010/02/10 ���g (���u) �d�l�ύX ���f��No.216
*/

package webbroker3.accountopen.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�����J�ݏ�)<BR>
 * �����J�ݏ�<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AccOpenStateUnit extends Message 
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
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     */
    public String requestNumber;
    
    /**
     * (���͋敪)<BR>
     * ���͋敪<BR>
     * <BR>
     * 0�F�@@�ڋq<BR>
     * 1�F�@@�b�b�I�y���[�^<BR>
     * 2�F�@@�Ǘ���<BR>
     */
    public String inputDiv;

    /**
     * (�쐬�҃R�[�h)<BR>
     * �쐬�҃R�[�h<BR>
     * <BR>
     * �����͋敪�F�ڋq�̏ꍇ��null<BR>
     */
    public String creatorCode;
    
    /**
     * (����������)<BR>
     * ����������<BR>
     */
    public Date infoClaimDate;
    
    /**
     * (�����J�ݓ�)<BR>
     * �����J�ݓ�<BR>
     */
    public Date accountOpenDate;
    
    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j<BR>
     */
    public String accountFamilyNameKana;
    
    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j<BR>
     */
    public String accountNameKana;
    
    /**
     * (�����J�ݏ󋵋敪)<BR>
     * �����J�ݏ󋵋敪<BR>
     * <BR>
     * 0�F�@@DEFAULT�i���J�݁j<BR> 
     * 1�F�@@�J�ݒ� <BR>
     * 2�F�@@�G���[���� <BR>
     * 3�F�@@�J�ݍ� <BR>
     * 4�F�@@�R���� <BR>
     * 5�F�@@�p�� <BR>
     */
    public String accountOpenStateDiv;
    
    /**
     * (����\�t���O)<BR>
     * ����\�t���O<BR>
     * <BR>
     * true�F�@@����\<BR>
     * false�F�@@����s��<BR>
     */
    public boolean cancelFlag;

    /**
     * (�폜�t���O)<BR>
     * �폜�t���O<BR>
     * <BR>
     *  1�FTRUE/�����i�폜�j<BR>
     *  0�FFALSE/�L���iDEFAULT�j<BR>
     */
    public String deleteFlag;

    /**
     * (�폜����)<BR>
     * �폜����<BR>
     */
    public Date deleteDate;

    /**
     * (����t���O)<BR>
     * ����t���O<BR>
     */
    public String printFlag;

    /**
     * (��̃t���O)<BR>
     * ��̃t���O<BR>
     * <BR>
     * 1�FTRUE/��̍� <BR>
     * 0�FFALSE/����́iDEFAULT�j<BR>
     */
    public String receiveFlag;

    /**
     * (��������敪)<BR>
     * ��������敪<BR>
     * <BR>
     * 0�F��ʌ���<BR>
     * 1�F�������<BR>
     */
    public String taxTypeDiv;

    /**
     * (�O���l�t���O)<BR>
     * �O���l�t���O <BR>
     * <BR>
     * 1�FTRUE/���{�ȊO <BR>
     * 0�FFALSE/���{�iDEFAULT�j<BR>
     */
    public String foreignerFlag;

    /**
     * (�`�[�쐬���)<BR>
     * �`�[�쐬���<BR>
     */
    public WEB3AccOpenVoucherInfo voucherInfo;
    
    /**
     * (��p�U��������ԍ�)<BR>
     * ��p�U��������ԍ�<BR>
     */
    public String exclusiveAccountCode;

    /**
     * (�����ғo�^�敪)<BR>
     * �����ғo�^�敪<BR>
     * <BR>
     * 0�F�o�^�Ȃ�<BR>
     * 1�F�o�^����<BR>
     */
    public String insiderDiv;

    /**
     * (�����J�ݏ�)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 419C62E70235
     */
    public WEB3AccOpenStateUnit() 
    {
     
    }
}
@
