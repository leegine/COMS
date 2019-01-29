head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.16.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍϊm�F���X�|���X�N���X(WEB3OptionsCloseMarginConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ������ �V�K�쐬
Revesion History : 2007/11/19 �И��� �d�l�ύX���f��No.812
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���I�v�V�����ԍϊm�F���X�|���X)<BR>
 * �����w���I�v�V�����ԍϊm�F���X�|���X�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginConfirmResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginConfirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406111042L;
        
    /**
     * (���ʖ���)<BR>
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits;
    
    /**
     * (�T�Z��n���)<BR>
     */
    public String estimatedPrice;

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
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[<BR>
     */
    public String[] messageSuspension;
    
    /**
     * (�m�F���P��)<BR>
     * ��ʂł͔�\���B�������N�G�X�g�ő��M����l�B<BR>
     */
    public String checkPrice;
    
    /**
     * (�m�F��������)<BR>
     * ��ʂł͔�\���B�������N�G�X�g�ő��M����l�B<BR>
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
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsCloseMarginConfirmResponse()
    {
        
    }
    
    /**
     * @@roseuid 40C0754500EA
     */
    public WEB3OptionsCloseMarginConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
