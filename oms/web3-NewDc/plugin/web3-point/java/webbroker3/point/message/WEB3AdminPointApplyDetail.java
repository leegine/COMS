head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.54.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointApplyDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�\������(WEB3AdminPointApplyDetail.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�|�C���g�\������)<BR>
 * �|�C���g�\�����׃N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointApplyDetail extends Message
{
    
    /**
     * (�\��ID)<BR>
     * �\��ID<BR>
     */
    public String applyId;
    
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
     * (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     */
    public String premiumNo;
    
    /**
     * (�i�i��)<BR>
     * �i�i��<BR>
     */
    public String premiumName;
    
    /**
     * (�\������)<BR>
     * �\������<BR>
     */
    public Date applyDate;
    
    /**
     * (��t�敪)<BR>
     * ��t�敪<BR>
     * <BR>
     * 0�F ��t����<BR>
     * 1�F ��t�ς�<BR>
     */
    public String acceptDiv;
    
    /**
     * (�X�V����)<BR>
     * �X�V����<BR>
     */
    public Date updateTimeStamp;
    
    /**
     * (��t���[�U)<BR>
     * ��t���[�UID<BR>
     */
    public String acceptUser;
    
    /**
     * (����敪)<BR>
     * ����敪<BR>
     * <BR>
     * 0�F �������<BR>
     * 1�F ����ς�<BR>
     */
    public String cancelDiv;
    
    /**
     * (�|�C���g�\������)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 418F497B0165
     */
    public WEB3AdminPointApplyDetail() 
    {
     
    }
}
@
