head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.35.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderReceiveHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ғ�����t�����Ɖ�X�|���X(WEB3AdminBondOrderReceiveHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.216
*/
package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��Ғ�����t�����Ɖ�X�|���X)<BR>
 * �Ǘ��Ғ�����t�����Ɖ�X�|���X<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminBondOrderReceiveHistoryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_order_receive_history";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231733L;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;

    /**
     * (����J�n��)<BR>
     * ����J�n��<BR>
     */
    public Date recruitStartDate;

    /**
     * (����I����)<BR>
     * ����I����<BR>
     */
    public Date recruitEndDate;

    /**
     * (������t����)<BR>
     * ������t����<BR>
     */
    public WEB3BondOrderAcceptHistoryUnit[] orderAcceptHistory;

    /**
     * (���������X�ʉ���g���)<BR>
     * ���������X�ʉ���g���<BR>
     */
    public WEB3BondDomesticBranchRecruitLimitInfo bondDomesticBranchRecruitLimitInfo;

    /**
     * (�Ǘ��Ғ�����t�����Ɖ�X�|���X)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 4683527D0330
     */
    public WEB3AdminBondOrderReceiveHistoryResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminBondOrderReceiveHistoryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
