head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.20.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ԍϒ����m�F���X�|���X(WEB3FuturesCloseMarginConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 羉s (���u) �V�K�쐬
Revesion History : 2007/11/19 �И��� �d�l�ύX���f��No.812
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����w���敨�ԍϒ����m�F���X�|���X)<BR>
 * �����w���敨�ԍϒ����m�F���X�|���X�N���X
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_closeMarginConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191545L;

    /**
     * (���ʖ���)<BR>
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits;

    /**
     * (�T�Z���ϑ��v)<BR>
     */
    public String estimatedSettleIncome;

    /**
     * (�萔���R�[�X)<BR>
     * 02�F��z�萔��(�X�^���_�[�h)<BR>
     * 03�F��������v<BR>
     * 04�F����<BR>
     * 05�F�����z��<BR>
     * 12�F�藦�萔���i�n�C�p�[�{�b�N�X�j�������� = 0%<BR>
     */
    public String commissionCourse;

    /**
     * (�萔��)<BR>
     */
    public String commission;

    /**
     * (�萔�������)<BR>
     */
    public String commissionConsumptionTax;

    /**
     * (����I���x������)<BR>
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[
     */
    public String[] messageSuspension;

    /**
     * (�m�F���P��)<BR>
     * ��ʂł͔�\���B�������N�G�X�g�ő��M����l�B
     */
    public String checkPrice;

    /**
     * (�m�F��������)<BR>
     * ��ʂł͔�\���B�������N�G�X�g�ő��M����l�B
     */
    public Date checkDate;

    /**
     * (����ID)<BR>
     * ��ʂł͔�\���B�������N�G�X�g�ő��M����l�B<BR>
     */
    public String orderId;

    /**
     * (�����L������)<BR>
     * ����������<BR>
     */
    public Date expirationDate;

    /**
     * @@roseuid 40F7AE18009C
     */
    public WEB3FuturesCloseMarginConfirmResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FuturesCloseMarginConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
