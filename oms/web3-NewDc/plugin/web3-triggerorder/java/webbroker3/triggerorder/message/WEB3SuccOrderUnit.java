head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A����������(WEB3SuccOrderUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 ������(���u) �V�K�쐬
Revesion History : 2006/08/24 ������(���u) ���f��No.163 ����
Revesion History : 2006/08/30 �ęԍg(���u) �d�l�ύX���f��165
Revesion History : 2006/11/23 ���G��(���u) �d�l�ύX���f��183
Revesion History : 2007/06/05 �đo�g(���u) �d�l�ύX���f��235
Revesion History : 2008/03/24 �k�v�u(���u) �d�l�ύX���f��289
*/
package webbroker3.triggerorder.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�A����������)<BR>
 * �A���������ʖ���<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3SuccOrderUnit extends Message 
{
    
    /**
     * (����ID)<BR>
     * ����ID<BR>
     * <BR>
     * �e�����̏ꍇ�A�e�����̒���ID�B<BR>
     * �q�����̏ꍇ�A�q�����̒���ID�B<BR>
     */
    public String orderId;
    
    /**
     * (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     */
    public String commodityType;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (�w�����)<BR>
     * �w�����<BR>
     * <BR>
     * 0005�F�@@TOPIX<BR>
     * 0018�F�@@���o225<BR>
     * 0016�F�@@���o300<BR>
     * 0019�F�@@�~�j���o225<BR>
     * <BR>
     * ���敨�E�I�v�V�����̏ꍇ�Z�b�g�B<BR>
     */
    public String targetProductCode = null;
    
    /**
     * (����)<BR>
     * ����<BR>
     * <BR>
     * ���敨�E�I�v�V�����̏ꍇ�Z�b�g�B<BR>
     */
    public String delivaryMonth = null;
    
    /**
     * (�I�v�V�������i�敪)<BR>
     * �I�v�V�������i�敪<BR>
     * <BR>
     * P�F�@@�v�b�g�I�v�V����<BR>
     * C�F�@@�R�[���I�v�V����<BR>
     * <BR>
     * ���I�v�V�����̏ꍇ�Z�b�g�B<BR>
     */
    public String opProductType = null;
    
    /**
     * (�s�g���i)<BR>
     * �s�g���i<BR>
     * <BR>
     * ���I�v�V�����̏ꍇ�Z�b�g�B<BR>
     */
    public String strikePrice = null;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�@@���<BR>
     * 1�F�@@����<BR>
     * 5�F�@@�X�g�b�N�I�v�V����<BR>
     * <BR>
     * �����E�M�p�̏ꍇ�Z�b�g�B<BR>
     */
    public String taxType = null;
    
    /**
     * (����敪)<BR>
     * ����敪<BR>
     * (�����P��.������ʂɑ���)<BR>
     * <BR>
     * 1�F�@@�������t����<BR>
     * 2�F�@@�������t����<BR>
     * 3�F�@@�V�K��������<BR>
     * 4�F�@@�V�K��������<BR>
     * 5�F�@@�����ԍϒ���<BR>
     * 6�F�@@�����ԍϒ���<BR>
     * 7�F�@@��������<BR>
     * 8�F�@@���n����<BR>
     * 601�F�@@�w���敨�V�K��������<BR>
     * 602�F�@@�w���敨�V�K��������<BR>
     * 603�F�@@�w���敨�����ԍϒ���<BR>
     * 604�F�@@�w���敨�����ԍϒ���<BR>
     * 605�F�@@�w���I�v�V�����V�K��������<BR>
     * 606�F�@@�w���I�v�V�����V�K��������<BR>
     * 607�F�@@�w���I�v�V���������ԍϒ���<BR>
     * 608�F�@@�w���I�v�V���������ԍϒ���<BR>
     */
    public String tradingType;
    
    /**
     * (�ٍϋ敪)<BR>
     * �ٍϋ敪<BR>
     * <BR>
     * 1�F�@@���x�M�p<BR>
     * 2�F�@@��ʐM�p<BR>
     * <BR>
     * ���M�p�����̏ꍇ�Z�b�g�B<BR>
     */
    public String repaymentDiv = null;
    
    /**
     * (�ٍϊ����l)<BR>
     * �ٍϊ����l<BR>
     * <BR>
     * ���w��B<BR>
     * �������̏ꍇ�h9999999�h<BR>
     * <BR>
     * ���M�p�����̏ꍇ�Z�b�g�B<BR>
     */
    public String repaymentTimeLimit = null;
    
    /**
     * (�l�i����)<BR>
     * 0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l<BR>
     * 7:���s�c�����<BR>
     * <BR>
     * �����E�M�p�̏ꍇ�Z�b�g�B<BR>
     */
    public String priceCondType = null;
    
    /**
     * (���s����)<BR>
     * ���s����<BR>
     * <BR>
     * 1�F�@@������<BR>
     * 3�F�@@��t<BR>
     * 4�F�@@����<BR>
     * 7�F�@@�s�o���������s<BR>
     */
    public String execCondType;
    
    /**
     * (���������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;
    
    /**
     * (�t�w�l�p�v���~�A��/�����Y���i)<BR>
     * 0�F�����Y���i�@@1�F�v���~�A��<BR>
     * <BR>
     * �����������敪���u�t�w�l�v���敨�EOP�̏ꍇ�ɃZ�b�g<BR>
     */
    public String stopPremium_underlyingAssets;
    
    /**
     * (�t�w�l�p���������P��)<BR>
     * 0�F�����Y���i�@@1�F�v���~�A��<BR>
     * <BR>
     * �����������敪���u�t�w�l�v�̏ꍇ�ɃZ�b�g<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * <BR>
     * �����������敪���u�t�w�l�v�̏ꍇ�ɃZ�b�g<BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * (�v�w�l�p�v���~�A��/�����Y���i)<BR>
     * 0�F�����Y���i�@@1�F�v���~�A��<BR>
     * <BR>
     * �����������敪���uW�w�l�v���敨�EOP�̏ꍇ�ɃZ�b�g<BR>
     */
    public String wlimitPremium_underlyingAssets;
    
    /**
     * (�v�w�l�p���������P��)<BR>
     * 0�F�����Y���i�@@1�F�v���~�A��<BR>
     * <BR>
     * �����������敪���uW�w�l�v�̏ꍇ�ɃZ�b�g<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (�v�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * <BR>
     * �����������敪���uW�w�l�v�̏ꍇ�ɃZ�b�g<BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (�v�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * <BR>
     * �����������敪���uW�w�l�v�̏ꍇ�ɃZ�b�g<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (�v�w�l�p�����P��)<BR>
     * <BR>
     * ��W�w�l�p�����P���敪���u�w�l�v�̏ꍇ�ɃZ�b�g<BR>
     */
    public String wLimitPrice;

    /**
     * (�v�w�l�p���s����)<BR>
     * 1�F�@@������<BR>
     * 3�F�@@��t<BR>
     * 4�F�@@����<BR>
     * 7�F�@@�s�o���������s<BR>
     * <BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitExecCondType;

    /**
     * (�v�w�l�p�L����ԋ敪)<BR>
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L��<BR>
     * 2�F�X�g�b�v����������<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitEnableStatusDiv;

    /**
     * (�v�w�l�p�֑ؑO�����P��)<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A�ݒ肳���<BR>
     */
    public String wlimitBefChgLimitPrice;

    /**
     * (W�w�l�p�֑ؑO���s����)<BR>
     * 1�F�@@������ <BR>
     * 3�F�@@��t <BR>
     * 4�F�@@���� <BR>
     * 7�F�@@�s�o���������s <BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A�ݒ肳���<BR>
     */
    public String wlimitBefChgExecCondType;

    /**
     * (�����������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orgOrderCondType;

    /**
     * (�����������P��)<BR>
     * �����������P��<BR>
     */
    public String orgOrderCondPrice;

    /**
     * (�������������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     */
    public String orgOrderCondOperator;

    /**
     * (���v�w�l�p�����P���敪)
     * 0�F���s�@@1�F�w�l 
     * �������������敪��2�F�v�w�l�̏ꍇ
     */
    public String orgWlimitOrderPriceDiv;

    /**
     * (���v�w�l�p�����P��)<BR>
     * �����v�w�l�p�����P���敪���A1�F�w�l�̏ꍇ�ݒ肳���B<BR>
     */
    public String orgWlimitPrice;

    /**
     * (���v�w�l�p���s����)<BR>
     * 1�F�@@������ <BR>
     * 3�F�@@��t <BR>
     * 4�F�@@���� <BR>
     * 7�F�@@�s�o���������s<BR>
     * <BR>
     * �������������敪��2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgWlimitExecCondType;

    /**
     * (���v���~�A���^�����Y���i)<BR>
     * 0�F�����Y���i�@@1�F�v���~�A��<BR>
     * <BR>
     * ���������敪�u0�FDEFAULT�v�ȊO�̏ꍇ�ݒ�<BR>
     * ���敨OP�̏ꍇ�̂�<BR>
     */
    public String orgPremium_underlyingAssets;

    /**
     * (�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public String orderQuantity;
    
    /**
     * (�����P��)<BR>
     * �����P��<BR>
     * <BR>
     * �����s�̏ꍇ�A0���Z�b�g�B<BR>
     */
    public String orderPrice;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public Date orderDate;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date orderBizDate;
    
    /**
     * (�����L������)<BR>
     * �����L������<BR>
     * <BR>
     * ���o����܂Œ����̏ꍇ�Z�b�g�B<BR>
     */
    public Date expirationDate = null;
    
    /**
     * (��n���)<BR>
     * ��n���<BR>
     * <BR>
     * ����肠��̏ꍇ�Z�b�g�B<BR>
     */
    public String deliveryPrice = null;
    
    /**
     * (������)<BR>
     * ������<BR>
     * <BR>
     * ���ݒ���e�͈ȉ��̃t�@@�C����<BR>
     * �@@�����󋵋敪��`�V�[�g���Q�ƁB<BR>
     * �@@�����F�uү���ޒ�`��_��������(����).xls�v<BR>
     * �@@�M�p�F�uү���ޒ�`��_�M�p���(����).xls�v<BR>
     * �@@�敨�F�uү���ޒ�`��_�����w���敨(����).xls�v<BR>
     * �@@OP�F�uү���ޒ�`��_�����w���I�v�V����(����).xls�v
     */
    public String transactionStateType;
    
    /**
     * (�A�������\�t���O)<BR>
     * �A�������\�t���O<BR>
     * <BR>
     * true�F�@@�A�������\<BR>
     * false�F�@@�A�������s��<BR>
     */
    public boolean succOrderFlag = true;
    
    /**
     * (�����\�t���O)<BR>
     * �����\�t���O<BR>
     * <BR>
     * true�F�@@�����\<BR>
     * false�F�@@�����s��<BR>
     */
    public boolean changeFlag = true;
    
    /**
     * (����\�t���O)<BR>
     * ����\�t���O<BR>
     * <BR>
     * true�F�@@����\<BR>
     * false�F�@@����s��<BR>
     * <BR>
     * �������n�ɂ��Ă͒����t���O�Ɣ񓯊��Őݒ肳���B<BR>
     */
    public boolean cancelFlag = true;
    
    /**
     * (�\�񒍕��ꗗ)<BR>
     * �\�񒍕��ꗗ
     */
    public WEB3SuccReservationOrderUnit[] reservationOrderList = null;

    /**
     * (�������ϗ��R)<BR>
     * �������ϗ��R<BR>
     * <BR>
     * 0�F�@@���ϊ�������<BR>
     * 1�F�@@�ۏ؋��ێ������i�I�����C���J�n�O�E�y�x�j<BR>
     * 2�F�@@�ۏ؋��ێ������i�I�����C���J�n�O�E�d�x�j<BR>
     * 3�F�@@�ۏ؋��ێ������i��ԁj<BR>
     * 9�F�@@�蓮��������<BR>
     * <BR>
     * ���������ϒ����łȂ��ꍇ��null���Z�b�g�����B<BR>
     */
    public String forcedSettleReason = null;

    /**
     * (���������敪)<BR>
     * <BR>
     * 0�F�@@�I�[�v��<BR>
     * 1�F�@@����������<BR>
     */
    public String forcedLapseDiv = null;

    /**
     * (�[��O�J�z�Ώۃt���O)<BR>
     * false�F�[��O�J�z�Ȃ�<BR>
     * true�F�[��O�J�z����<BR>
     */
    public boolean eveningSessionCarryoverFlag = false;

    /**
     * (����敪)<BR>
     * 1�F�[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@NULL�F���̑�<BR>
     */
    public String sessionType = null;

    /**
     * (�A����������)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 431F846F0265
     */
    public WEB3SuccOrderUnit() 
    {
     
    }
}
@
