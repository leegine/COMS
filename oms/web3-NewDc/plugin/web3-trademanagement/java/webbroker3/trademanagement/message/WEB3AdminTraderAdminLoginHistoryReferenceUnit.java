head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminLoginHistoryReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���O�C���������(WEB3AdminTraderAdminLoginHistoryReferenceUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �И��� (���u) �V�K�쐬 ���f��No.005,007
*/

package webbroker3.trademanagement.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���O�C���������)<BR>
 * ���O�C���������N���X�B<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTraderAdminLoginHistoryReferenceUnit extends Message
{
    /**
     * (���O�C������)<BR>
     * ���O�C�������B<BR>
     */
    public Date loginDate;

    /**
     * (IP�A�h���X)<BR>
     * IP�A�h���X�B<BR>
     */
    public String ipAddress;

    /**
     * (�����o�H�敪)<BR>
     * �����o�H�敪�B<BR>
     * <BR>
     * �󂯎�����R�[�h�ɑΉ�����������<BR>
     * ���O�C���`�ԂƂ��ĉ�ʂŕ\������B<BR>
     * ------------------------------<BR>
     * 1�F�R�[���Z���^�[<BR>
     * 2�FPC<BR>
     * 3�F�X�����O�V���b�g<BR>
     * 4�Fi-mode<BR>
     * 5�FVodafone<BR>
     * 6�FAU<BR>
     * 7�F�X�����O�V���b�g(����)<BR>
     * 9�FHOST<BR>
     * A�F�Ǘ���<BR>
     * B�F�ۏ�������U�փo�b�`<BR>
     * C�F���b�`�N���C�A���g(RICH_CLIENT)<BR>
     * F�FIVR(���������d�b)<BR>
     * G�F��������<BR>
     * ------------------------------<BR>
     */
    public String orderRootDiv;

    /**
     * (���O�C������)<BR>
     * ���O�C�����ہB<BR>
     * <BR>
     * �󂯎�����R�[�h�ɑΉ�������������ʂŕ\������B<BR>
     * ---------------------<BR>
     * 0�F����<BR>
     * 1�F���s<BR>
     * ---------------------<BR>
     */
    public String loginResult;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�B<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�B<BR>
     */
    public String accountCode;

    /**
     * @@roseuid 48D75CD6031B
     */
    public WEB3AdminTraderAdminLoginHistoryReferenceUnit()
    {

    }
}
@
