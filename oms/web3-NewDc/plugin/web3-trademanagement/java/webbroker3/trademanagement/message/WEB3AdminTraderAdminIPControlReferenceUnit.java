head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���O�C������IP���(WEB3AdminTraderAdminIPControlReferenceUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �����F (���u) �V�K�쐬 ���f��004
*/

package webbroker3.trademanagement.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���O�C������IP���)<BR>
 * ���O�C������IP���N���X�B<BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlReferenceUnit extends Message
{
    /**
     * (���O�C������ID)<BR>
     * ���O�C������ID<BR>
     */
    public String denyLoginID;

    /**
     * (IP�A�h���X)<BR>
     * IP�A�h���X<BR>
     */
    public String ipAddress;

    /**
     * (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     * <BR>
     * 0�F �Ώ�<BR>
     * 1�F ����<BR>
     * 2�F �ΏۊO<BR>
     */
    public String status;

    /**
     * (�K�p�J�n����)<BR>
     * �K�p�J�n����<BR>
     */
    public Date startDate;

    /**
     * (�K�p�I������)<BR>
     * �K�p�I������<BR>
     */
    public Date endDate;

    /**
     * (�o�^�敪)<BR>
     * �o�^�敪<BR>
     * <BR>
     * 0�F �f�[����<BR>
     * 1�F �Ǘ���<BR>
     */
    public String registDiv;

    /**
     * (�X�V�敪)<BR>
     * �X�V�敪<BR>
     * <BR>
     * 0�F �f�[����<BR>
     * 1�F �Ǘ���<BR>
     */
    public String updateDiv;

    /**
     * (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     */
    public String updaterCode;

    /**
     * (���O�C������IP���)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 48BE613E030B
     */
    public WEB3AdminTraderAdminIPControlReferenceUnit()
    {

    }
}
@
