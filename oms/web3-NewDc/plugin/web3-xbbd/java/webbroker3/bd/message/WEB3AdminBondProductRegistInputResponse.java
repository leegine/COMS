head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ������o�^���̓��X�|���X(WEB3AdminBondProductRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
                     2006/10/08 ���� (���u) �d�l�ύX�E���f��107
Revesion History : 2008/08/13 �g�C�� (���u) �d�l�ύX�E���f��260
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҍ������o�^���̓��X�|���X)<BR>
 * �Ǘ��ҍ������o�^���̓��X�|���X�N���X
 *
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondProductRegistInputResponse extends WEB3GenResponse
{
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_regist_input";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;

    /**
     * (�������X�V���)<BR>
     * �������X�V���
     */
    public WEB3AdminBondProductUpdateInfo updateInfo;

    /**
     * (��������{���)<BR>
     * ��������{���
     */
    public WEB3AdminBondProductBasicInfo basicInfo;

    /**
     * (�J�X�g�f�B�A���ꗗ)<BR>
     * �J�X�g�f�B�A���ꗗ<BR>
     * �@@<BR>
     * �@@�J�X�g�f�B�A���̔z��
     */
    public WEB3AdminBondCustodianUnit[] custodianList;

    /**
     * (���������g�����ꗗ)<BR>
     * ���������g�����ꗗ<BR>
     * <BR>
     * �@@���������g�����̔z��
     */
    public WEB3AdminBondAutoExecLimitHistoryUnit[] autoExecLimitList;

    /**
     * (���ώc��)<BR>
     * ���ώc��
     */
    public String executedBalance;

    /**
     * (�����������ꗗ)<BR>
     * �����������ꗗ<BR>
     * <BR>
     * �@@�����������̔z��
     */
    public WEB3AdminBondProductCouponUnit[] productCouponList;

    /**
     * (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h
     */
    public String administratorCode;

    /**
     * (�Ǘ��ҍŏI�X�V����)<BR>
     * �Ǘ��ҍŏI�X�V����
     */
    public Date lastUpdateTime;

    /**
     * (�戵�敪���X�g)<BR>
     * �戵�敪�ꗗ��ێ�����String�z��B<BR>
     * <BR>
     * 0�F�s��  1�F�Ǘ��ҁ@@2�F�Ǘ���/�ڋq
     */
    public String[] tradeHandleDivList;

    /**
     * (�����敪���X�g)<BR>
     * �����敪�ꗗ��ێ�����String�z��B<BR>
     * <BR>
     * 1:���t 2:���p�@@3:���� 4�F���t/���p
     */
    public String[] buySellDivList;

    /**
     * (�J�����_�[�A�g�s��R�[�h���X�g)<BR>
     * �J�����_�[�A�g�s��R�[�h���X�g
     */
    public String[] calLinkedDivList;

    /**
     * (�������g�敪���X�g)<BR>
     * �������g�敪���X�g<BR>
     * <BR>
     * 0�F�񎩓����@@1�F�������
     */
    public String[] autoExecDivList;

    /**
     * (������ԃ`�F�b�N�敪���X�g)<BR>
     * ������ԃ`�F�b�N�敪���X�g <BR>
     * <BR>
     * 0�F������Ԃ��`�F�b�N����   1�F������Ԃ��`�F�b�N���Ȃ�
     */
    public String[] tradeTimeCheckDivList;

    /**
     * (���助�U�`�����X�g)<BR>
     * ���助�U�`�����X�g<BR>
     */
    public String[] recruitInvitationFormList;

    /**
     * (������󂯋敪���X�g)<BR>
     * ������󂯋敪���X�g<BR>
     */
    public String[] recruitAcceptDivList;

    /**
     * @@roseuid 44E3363D01A5
     */
    public WEB3AdminBondProductRegistInputResponse()
    {

    }

    /**
     *�@@�R���X�g���N�^�B<BR>
     *�@@�w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *�@@@@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminBondProductRegistInputResponse(WEB3GenRequest l_request)
    {

        super(l_request);

    }
}
@
