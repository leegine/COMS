head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.37.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductUpdateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������X�V���(WEB3AdminBondProductUpdateInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
                     2006/10/08 ���� (���u) �d�l�ύX�E���f��106�A107
Revesion History : 2008/08/13 �g�C�� (���u) �d�l�ύX�@@���f��260
Revesion History : 2009/07/24 ���g (���u) �d�l�ύX�@@���f��261
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�������X�V���)<BR>
 * �������X�V���N���X
 *
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondProductUpdateInfo extends Message
{

    /**
     * (�戵�敪)<BR>
     * �戵�敪<BR>
     * <BR>
     * �@@0�F�s�@@1�F�Ǘ��ҁ@@2�F�Ǘ���/�ڋq
     */
    public String tradeHandleDiv;

    /**
     * (�戵�J�n����)<BR>
     * �戵�J�n����
     */
    public Date tradeStartDate;

    /**
     * (�戵�I������)<BR>
     * �戵�I������
     */
    public Date tradeEndDate;

    /**
     * (����J�n��)<BR>
     * ����J�n��
     */
    public Date recruitStartDate;

    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    public Date deliveryDate;

    /**
     * (����I����)<BR>
     * ����I����
     */
    public Date recruitEndDate;

    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 1:���t�@@2:���p�@@3:����@@4:���t�^���p
     */
    public String buySellDiv;

    /**
     * (������)<BR>
     * ������
     */
    public String productName;

    /**
     * (���t�P��)<BR>
     * ���t�P��
     */
    public String buyPrice;

    /**
     * (���p�P��)<BR>
     * ���p�P��
     */
    public String sellPrice;

    /**
     * (�\���P��)<BR>
     * �\���P��
     */
    public String tradeUnit;

    /**
     * (�Œ�z��)<BR>
     * �Œ�z��
     */
    public String minFaceAmount;

    /**
     * (�ō��z��)<BR>
     * �ō��z��
     */
    public String maxFaceAmount;

    /**
     * (�J�����_�[�A�g�s��R�[�h)<BR>
     * �J�����_�[�A�g�s��R�[�h
     */
    public String calendarLinkedDiv;

    /**
     * (���t��n���ړ�����)<BR>
     * ���t��n���ړ�����
     */
    public String buyDeliveryMove;

    /**
     * (���p��n���ړ�����)<BR>
     * ���p��n���ړ�����
     */
    public String sellDeliveryMove;

    /**
     * (�������敪)<BR>
     * �������敪
     * <BR>
     * 0�F�񎩓����@@1�F�������
     */
    public String autoExecDiv;

    /**
     * (�������g)<BR>
     * �������g
     */
    public String autoExecLimit;

    /**
     * (�J�X�g�f�B�A���R�[�h)<BR>
     * �J�X�g�f�B�A���R�[�h
     */
    public String custodianCode;

    /**
     * (����萔����)<BR>
     * ����萔����
     */
    public String mediatorCommissionRate;

    /**
     * (�d�����̈בփ��[�g)<BR>
     * �d�����̈בփ��[�g
     */
    public String fxRateAtStock;

    /**
     * (������ԃ`�F�b�N�敪)<BR>
     * ������ԃ`�F�b�N�敪
     */
    public String tradeTimeCheckDiv;

    /**
     * (���助�U�`��)<BR>
     * ���助�U�`��
     */
    public String recruitInvitationForm;

    /**
     * (������󂯋敪)<BR>
     * ������󂯋敪
     */
    public String recruitAcceptDiv;

    /**
     * @@roseuid 44E3363E02CE
     */
    public WEB3AdminBondProductUpdateInfo()
    {

    }
}
@
