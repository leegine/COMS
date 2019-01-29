head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p����m�F���X�|���X(WEB3SrvRegiCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 ���o�� �V�K�쐬
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�T�[�r�X���p����m�F���X�|���X)<BR>
 * �T�[�r�X���p����m�F���X�|���X�N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiCancelConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_cancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151417L;
    
    /**
     * (���I�敪)<BR>
     * 0:���@@1:�L<BR>
     */
    public String lotteryDiv;
    
    /**
     * (�^�p�敪)<BR>
     * 0:���������I�@@1:�ʏ�^�p�i���I�L�j�@@2:�ʏ�^�p�i���I�L�I�[�N�V�����j<BR>
     */
    public String useDiv;
    
    /**
     * (�T�[�r�X����)
     */
    public String serviceName;
    
    /**
     * (�T�[�r�X���e)
     */
    public String serviceContent;
    
    /**
     * (��W�g)
     */
    public String applyMax;
    
    /**
     * (�\�����ԁi���j)
     */
    public Date applyStartDate;
    
    /**
     * (�\�����ԁi���j)
     */
    public Date applyEndDate;
    
    /**
     * (���I��)
     */
    public Date lotteryDate;
    
    /**
     * (�K�p�J�n��)
     */
    public Date trialStartDate;
    
    /**
     * (�K�p�I����)
     */
    public Date trialEndDate;
    
    /**
     * (���p����)<BR>
     * this.�^�p�敪��"���������I"�܂���"�ʏ�^�p�i���I�L�j"�̏ꍇ�A<BR>
     * "���p����"�Ƃ��ĎQ��<BR>
     * this.�^�p�敪��"�ʏ�^�p�i���I�L�I�[�N�V�����j"�̏ꍇ�A<BR>
     * "�Œ���D���i"�Ƃ��ĎQ�Ƃ���B<BR>
     */
    public String chargeAmt;
    
    /**
     * (���D�P��)
     */
    public String biddingPriceUnit;
    
    /**
     * (�\������)
     */
    public String applyAmt;
    
    /**
     * (�O��̍ō����D�z)<BR>
     * �i�I�[�N�V�������̏ꍇ��null�j<BR>
     */
    public String maxSuccBidding;
    
    /**
     * (�O��̍Œᗎ�D�z)<BR>
     * �i�I�[�N�V�������̏ꍇ��null�j<BR>
     */
    public String minSuccBidding;
    
    /**
     * (�O��̉��d���ϊz)<BR>
     * �i�I�[�N�V�������̏ꍇ��null�j<BR>
     */
    public String weightedAverage;
    
    /**
     * (�T�[�r�X���p����m�F���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F20C300270
     */
    public WEB3SrvRegiCancelConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3SrvRegiCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
