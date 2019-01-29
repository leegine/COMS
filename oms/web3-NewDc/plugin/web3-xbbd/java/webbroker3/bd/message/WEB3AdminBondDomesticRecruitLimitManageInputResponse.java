head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.50.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticRecruitLimitManageInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ��������X�ʉ���g�Ǘ����̓��X�|���X(WEB3AdminBondDomesticRecruitLimitManageInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.215
*/
package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�Ǘ��ҍ��������X�ʉ���g�Ǘ����̓��X�|���X)<BR>
 * �Ǘ��ҍ��������X�ʉ���g�Ǘ����̓��X�|���X<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminBondDomesticRecruitLimitManageInputResponse
    extends WEB3AdminBondDomesticRecruitLimitManageCommonResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_recruit_limit_manage_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231848L;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;

    /**
     * (�񍆃R�[�h)<BR>
     * �񍆃R�[�h<BR>
     */
    public String productIssueCode;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;

    /**
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h<BR>
     */
    public String bondCategCode;

    /**
     * (���s�N����)<BR>
     * ���s�N����<BR>
     */
    public Date issueDate;

    /**
     * (���s���i)<BR>
     * ���s���i<BR>
     */
    public String issuePrice;

    /**
     * (�N����)<BR>
     * �N����<BR>
     */
    public String annualRate;

    /**
     * (���������P)<BR>
     * ���������P<BR>
     */
    public String couponPaymentDate1;

    /**
     * (���������Q)<BR>
     * ���������Q<BR>
     */
    public String couponPaymentDate2;

    /**
     * (���ҔN����)<BR>
     * ���ҔN����<BR>
     */
    public Date maturityDate;

    /**
     * (����J�n����)<BR>
     * ����J�n����<BR>
     */
    public Date recruitStartDate;

    /**
     * (����I����)<BR>
     * ����I����<BR>
     */
    public Date recruitEndDate;

    /**
     * (�Ǘ��ҍ��������X�ʉ���g�Ǘ����̓��X�|���X)<BR>
     * �R���X�g���N�^
     * @@roseuid 4684B9D7021E
     */
    public WEB3AdminBondDomesticRecruitLimitManageInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminBondDomesticRecruitLimitManageInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
