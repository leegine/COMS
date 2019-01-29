head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�������Ɖ���P�ʃN���X(WEB3FuturesExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 ����(���u) �V�K�쐬
                   2006/07/28 �����F�@@�d�l�ύX�@@���f��454,457,470,488
Revesion History : 2007/06/08 ���^�] (���u) �d�l�ύX���f��No.640
*/
package webbroker3.ifo.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����w���敨�������Ɖ���P��)<BR>
 * �����w���敨�������Ɖ���P�ʃN���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3FuturesExecuteGroup extends Message
{
    /**
     * (ID)<BR>
     * �����h�c<BR>
     */
    public String id;
    
    /**
     * (������)<BR>
     */
    public String futProductName;
    
    /**
     * (����s��)<BR>
     * 1�F�����@@2�F���<BR>
     */
    public String marketCode;
    
    /**
     * (����)<BR>
     */
    public Date openDate;
    
    /**
     * (���P��)<BR>
     */
    public String contractPrice;
    
    /**
     * (����敪)<BR>
     * 3�F�V�K���������@@4�F�V�K��������<BR>
     * 5�F�����ԍϒ����i���ԍρj�@@6�F�����ԍϒ����i���ԍρj<BR>
     */
    public String tradingType;
   
    /**
     * (��������)<BR>
     */
    public Date orderDate;
   
    /**
     * (������)<BR>
     */
    public Date orderBizDate;
    
    /**
     * (��������)<BR>
     */
    public String futOrderQuantity;
    
    /**
     * (�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     * �����P���敪���u�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String limitPrice;
    
    /**
     * (���s����)<BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s<BR>
     */
    public String execCondType;
    
    /**
     * (���������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;

    /**
     * (�t�w�l�p���������P��)<BR>
     * <BR>
     * ���������敪�u�t�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * <BR>
     * 1�F�ȉ��@@2�F�ȏ�<BR>
     * ���������敪�u�t�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (W�w�l�p���������P��)<BR>
     * <BR>
     * ���������敪�uW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (W�w�l�p�����������Z�q)<BR>
     * <BR>
     * 1�F�ȉ��@@2�F�ȏ�<BR>
     * ���������敪�uW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (W�w�l�p�����P���敪)<BR>
     * <BR>
     * 0�F���s�@@1�F�w�l<BR>
     * ���������敪�uW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (W�w�l�p�����P��)<BR>
     * <BR>
     * ���������敪�uW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wLimitPrice;
    
    /**
     * (�v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s <BR>
     * <BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitExecCondType;
    
    /**
     * (�v�w�l�p�L����ԋ敪)<BR>
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L��<BR>
     * 2�F�X�g�b�v���������� <BR>
     * <BR>
     * ���������敪�܂��͌����������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitEnableStatusDiv;
    
    /**
     * (�v�w�l�p�֑ؑO�����P��)<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A<BR>
     * �ݒ肳���<BR>
     */
    public String wlimitBefChgLimitPrice;
    
    /**
     * (�v�w�l�p�֑ؑO���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s <BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A<BR>
     * �ݒ肳���<BR>
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
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgOrderCondPrice;
    
    /**
     * (�������������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgCondOperator;
    
    /**
     * (���v�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * �������������敪��2�F�v�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgWLimitOrderPriceDiv;
    
    /**
     * (���v�w�l�p�����P��)<BR>
     * �����v�w�l�p�����P���敪���A1�F�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgWLimitPrice;
    
    /**
     * (���v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * �������������敪��2�F�v�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgWlimitExecCondType;

    /**
     * (��萔��)<BR>
     */
    public String execQuantity;
    
    /**
     * (���P��)<BR>
     */
    public String execPrice;
    
    /**
     * (�T�Z�����(���ϑ��v))<BR>
     */
    public String estimatedContractPrice;

    /**
     * (�����(���ϑ��v))<BR>
     */
    public String contractExecPrice;

    /**
     * (������)<BR>
     * ���R�[�h�l�̓��b�Z�[�W��`�t�H���_�ȉ��� <BR>
     * �uү���ޒ�`��_�����w���敨(����).xls�v�̏����󋵋敪��`���Q��<BR>
     */
    public String transactionStateType;
    
    /**
     * (�x���敪)<BR>
     * 0�F����@@1�F�x��<BR>
     * <BR>
     * ���������敪���A�u1�F�t�w�l�v�܂��́u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String delayDiv;
    
    /**
     * (�蓮�����\�t���O)<BR>
     * true�F�蓮�����\�@@�@@false�F�蓮�����s��<BR>
     * <BR>
     * ���������敪���A�u1�F�t�w�l�v�܂��́u2�FW�w�l�v�ŁA<BR>
     * �蓮�������\�ł���ꍇ�Atrue���ݒ肳���B<BR>
     */
    public boolean manualFlag;
    
    /**
     * (�����L������)<BR>
     */
    public Date expirationDate;
    
    /**
     * (�����`���l��)<BR>
     * 0�F�c�ƓX�@@1�F�C���^�[�l�b�g�@@2�F�R�[���Z���^�@@3�F���o�C��<BR>
     * <BR>
     * ���O�C�����[�U���R�[���Z���^�[�̏ꍇ�ݒ�<BR>
     */
    public String orderChannel;
    
    /**
     * (�����o�H�敪)<BR>
     * 1�F�R�[���Z���^�[�@@2�F�o�b�@@3:�X�����O�V���b�g�@@4�Fi-mode�@@5�FVodafone�@@6�FAU�@@9�FHOST<BR>
     * <BR>
     * ���O�C�����[�U���R�[���Z���^�[�̏ꍇ�ݒ�<BR>
     */
    public String orderRootDiv;
    
    /**
     * (�I�y���[�^�R�[�h)<BR>
     * ���O�C�����[�U���R�[���Z���^�[�̏ꍇ�ݒ�<BR>
     */
    public String operatorCode;
    
    /**
     * (�������Ɖ���)<BR>
     */
    public WEB3FuturesExecuteUnit[] futExecuteUnits;
    
    /**
     * (�����\�t���O)<BR>
     * true�F�����\�@@�@@false�F�����s��<BR>
     */
    public boolean changeFlag;
    
    /**
     * (����\�t���O)<BR>
     * true�F����\�@@�@@false�F����s��<BR>
     */
    public boolean cancelFlag;
    
    /**
     * (�����R�[�h)
     */
    public String futProductCode;

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
     * (�[��O�J�z�Ώۃt���O)<BR>
     * false�F�[��O�J�z�Ȃ�<BR>
     * true�F�[��O�J�z����<BR>
     */
    public boolean eveningSessionCarryoverFlag;

    /**
     * (����敪)<BR>
     * 1�F�[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@NULL�F���̑�<BR>
     */
    public String sessionType;

    /**
     * @@roseuid 40F7AE10009C
     */
    public WEB3FuturesExecuteGroup() 
    {
     
    }
}
@
