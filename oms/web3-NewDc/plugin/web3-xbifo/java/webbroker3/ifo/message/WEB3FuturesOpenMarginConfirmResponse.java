head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�V�K�������m�F���X�|���X�N���X(WEB3FuturesOpenMarginConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 ������ �V�K�쐬
Revesion History : 2007/11/19 �И��� �d�l�ύX���f��No.812
*/
package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����w���敨�V�K�������m�F���X�|���X)<BR>
 * �����w���敨�V�K�������m�F���X�|���X�N���X<BR>
 * 
 * @@author ������
 * @@version 1.0
 */
public class WEB3FuturesOpenMarginConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="futures_OpenMarginConfirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201555L;
    
    /**
     * (�����R�[�h)<BR>
     */
    public String futProductCode;
    
    /**
     * (������)<BR>
     */
    public String futProductName;
    
    /**
     * (�T�Z�����)<BR>
     */
    public String estimatedContractPrice;
    
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
     * @@roseuid 40F7AE0B034B
     */
    public WEB3FuturesOpenMarginConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
