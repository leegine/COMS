head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeChangeAccount.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g�єԍ��E�Ζ�����ύX�\���ڋq(WEB3AccInfoMobileOfficeChangeAccount.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
Revesion History : 2006/12/14 ���� (���u) ���f��No.153
Revesion History : 2007/01/17 �����q (���u) ���f��No.160
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�g�єԍ��E�Ζ�����ύX�\���ڋq)<BR>
 * �g�єԍ��E�Ζ�����ύX�\���ڋq���b�Z�[�W<BR>
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3AccInfoMobileOfficeChangeAccount extends Message 
{
    
    /**
     * (�\����)<BR>
     * �\����<BR>
     */
    public Date applyDate;
    
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
     * (�\���҃R�[�h)<BR>
     * �\���҃R�[�h<BR>
     */
    public String updaterCode;
    
    /**
     * (�����)<BR>
     * �����<BR>
     */
    public Date judgementDate;
    
    /**
     * (����҃R�[�h)<BR>
     * ����҃R�[�h<BR>
     */
    public String judgeCode;
    
    /**
     * (�\���󋵋敪)<BR>
     * �\���󋵋敪<BR>
     * <BR>
     * 0�F�@@����҂�<BR>
     * 1�F�@@����҂��i�m�F���j<BR>
     * 2�F�@@����ς�<BR>
     */
    public String applyStateDiv;
    
    /**
     * (���茋��)<BR>
     * ���茋��<BR>
     * <BR>
     * 1�F���F<BR>
     * 2�F�s��<BR>
     */
    public String judgmentResultDiv;

    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�l����<BR>
     * 1�F�@@�l����<BR>
     */
    public String accountType;

    /**
     * (�g�ѓd�b�E�Ζ�����)<BR>
     */
    public WEB3AccInfoMobileOfficeInfo mobileOfficeInfo;

    /**
     * (��t����)<BR>
     * ��t����<BR>
     * <BR>
     * 1�F��t����<BR>
     * 9�F�G���[<BR>
     */
    public String acceptedResult;

    /**
     * (�g�єԍ��E�Ζ�����ύX�\���ڋq)<BR>
     * �g�єԍ��E�Ζ�����ύX�\���ڋq<BR>
     * �R���X�g���N�^<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeChangeAccount
     * @@roseuid 414982D80319
     */
    public WEB3AccInfoMobileOfficeChangeAccount() 
    {
     
    }
}
@
