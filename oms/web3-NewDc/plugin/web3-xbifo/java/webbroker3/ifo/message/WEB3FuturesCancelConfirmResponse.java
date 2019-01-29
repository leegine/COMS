head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.16.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨����m�F���X�|���X�N���X(WEB3FuturesCancelConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/22 Ḗ@@�� (���u) �V�K�쐬
              001: 2004/08/05 ���Ō� (���u) Review�C��
              002: 2004/08/05 ���Ō� �Ή�QA WEB3-XBIFO-A-CD-0081
              003: 2006/07/28 �����F�@@�d�l�ύX�@@���f��454,457,470,488
Revesion History : 2007/06/11 �Ј��� (���u) �d�l�ύX���f��No.639
Revesion History : 2007/06/21 ���^�] (���u) �d�l�ύX���f��No.711
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����w���敨��������m�F���X�|���X)<BR>
 * �����w���敨����m�F���X�|���X�N���X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3FuturesCancelConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="futures_cancelConfirm";
            
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407220950L;
    /**
     * (����敪)<BR>
     * 3�F�V�K���������@@4�F�V�K��������<BR>
     */
    public String tradingType;
    
    /**
     * (����s��)<BR>
     * 1�F�����@@2�F���<BR>
     */
    public String marketCode;
    
    /**
     * (�w�����)<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
     */
    public String targetProductCode;
    
    /**
     * (����)<BR>
     * YYYYMM�`��<BR>
     */
    public String delivaryMonth;
    
    /**
     * (��������)<BR>
     */
    public String futOrderQuantity;
    
    /**
     * (����萔��)<BR>
     */
    public String partExecQuantity;
    
    /**
     * (�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     * �����P���敪���u�w�l�v�̏ꍇ�ɐݒ�<BR>
     */
    public String limitPrice;
    
    /**
     * (���s����)<BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s<BR>
     */
    public String execCondType;
    
    /**
     * (���������敪)<BR>
     * 1�F��������@@2�F�o����܂Œ����@@3�F�[��܂Œ���<BR>
     */
    public String expirationDateType;
    
    /**
     * (�����L������)<BR>
     * ���������敪���u�o����܂Œ����v�̏ꍇ�ɐݒ�<BR>
     */
    public Date expirationDate;
    
    /**
     * (���������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;
    
    /**
     * (�t�w�l�p���������P��)<BR>
     * ���������敪���A1�F�t�w�l�̏ꍇ�ݒ肳���<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * ���������敪���A1�F�t�w�l�̏ꍇ�ݒ肳���<BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * (�v�w�l�p���������P��)<BR>
     * ���������敪���A2�FW�w�l�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (�v�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * ���������敪���A2�FW�w�l�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (�v�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * ���������敪���A2�FW�w�l�̏ꍇ�ݒ肳���<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (�v�w�l�p�����P��)<BR>
     * �v�w�l�p�����P���敪���A1�F�w�l�̏ꍇ�ݒ肳���<BR>
     */
    public String wLimitPrice;
    
    /**
     * (�v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * <BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitExecCondType;
    
    /**
     * (�v�w�l�p�L����ԋ敪)<BR>
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L��<BR>
     * 2�F�X�g�b�v����������<BR>
     * <BR>
     * ���������敪�܂��͌����������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitEnableStatusDiv;
    
    /**
     * (�v�w�l�p�֑ؑO�����P��)<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A�ݒ肳���<BR>
     */
    public String wlimitBefChgLimitPrice;
    
    /**
     * (�v�w�l�p�֑ؑO���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A�ݒ肳���<BR>
     */
    public String wlimitBefChgExecCondType;
    
    /**
     * (�����������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     * <BR>
     * �������������s��̏ꍇ�ɐݒ�<BR>
     */
    public String orgOrderCondType;
    
    /**
     * (�����������P��)<BR>
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgOrderCondPrice;
    
    /**
     * (�������������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgCondOperator;
    
    /**
     * (���v�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * �������������敪��2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgWLimitOrderPriceDiv;
    
    /**
     * (���v�w�l�p�����P��)<BR>
     * �����v�w�l�p�����P���敪���A1�F�w�l�̏ꍇ�ݒ肳���<BR>
     */
    public String orgWLimitPrice;
    
    /**
     * (���v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * �������������敪��2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgWlimitExecCondType;
    
    /**
     * (�T�Z�����(���ϑ��v))<BR>
     */
    public String estimatedContractPrice;
    
    /**
     * (����I���x������)<BR>
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[<BR>
     */
    public String[] messageSuspension;
    
    /**
     * (�m�F��������)<BR>
     * ��ʂł͔�\���B�������N�G�X�g�ő��M����l�B<BR>
     */
    public Date checkDate;

    /**
     * (���Ϗ���)<BR>
     * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
     */
    public String closingOrder;
    
    /**
     * (���ʖ���)<BR>
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits;
    
    /**
     * (���ݒl)<BR>
     */
    public String currentPrice;
    
    /**
     * (�O����)
     */
    public String comparedPreviousDay;
    
    /**
     * (�������)<BR>
     */
    public Date currentPriceTime;

    /**
     * (����敪)<BR>
     * 1�F�[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@NULL�F���̑�<BR>
     */
    public String sessionType;

    /**
     * @@roseuid 40F7AE1B03D8
     */
    public WEB3FuturesCancelConfirmResponse() 
    {
     
    }
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FuturesCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
