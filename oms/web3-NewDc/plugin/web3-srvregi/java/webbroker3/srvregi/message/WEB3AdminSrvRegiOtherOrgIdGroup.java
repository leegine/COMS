head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
Author Name         : Daiwa Institute of Research
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s(WEB3AdminSrvRegiOtherOrgIdGroup.java)
Revision History    : 2008/03/10 ���u��(���u) �V�K�쐬 ���f��No.338
*/

package webbroker3.srvregi.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s�f�[�^�N���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdGroup extends Message
{

    /**
     * (�ʔ�)<BR>
     * �ʔ�<BR>
     */
    public String seqNumber;

    /**
     * (ID)<BR>
     * ID<BR>
     */
    public String id;

    /**
     * (�p�X���[�h)<BR>
     * �p�X���[�h<BR>
     */
    public String password;

    /**
     * (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     */
    public String status;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�K�p����From)<BR>
     * �K�p����From<BR>
     */
    public Date appliStartDate;

    /**
     * (�K�p����To)<BR>
     * �K�p����To<BR>
     */
    public Date appliEndDate;

    /**
     * (�ŏI�X�V��)<BR>
     * �ŏI�X�V��<BR>
     */
    public Date lastUpdateTime;

    /**
     * (�ŏI�X�V��)<BR>
     * �ŏI�X�V��<BR>
     */
    public String lastUpdater;

    /**
     * (�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 47B8D08D00B0
     */
    public WEB3AdminSrvRegiOtherOrgIdGroup()
    {

    }
}
@
