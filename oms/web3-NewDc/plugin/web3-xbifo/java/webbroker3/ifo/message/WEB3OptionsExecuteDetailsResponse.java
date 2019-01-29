head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.20.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsExecuteDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������������ڍ׃��X�|���X�N���X(WEB3OptionsExecuteDetailsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ������ �V�K�쐬
              001: 2006/07/12 �����F�@@(���u)�@@�d�l�ύX�@@454,457,470,488,497,527
Revesion History : 2007/06/08 ���^�]  (���u)  �d�l�ύX���f��No.640
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;


/**
 * (�����w���I�v�V���������������ڍ׃��X�|���X)<BR>
 * �����w���I�v�V���������������ڍ׃��X�|���X�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionsExecuteDetailsResponse extends WEB3GenResponse 
{
    
    /**
    * PTYPE<BR>
    */
    public final static  String PTYPE = "options_executeDetails";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406101530L;
    /**
     * (������)
     */
    public String opProductName;
    
    /**
     * (�w�����)<BR>
     * <BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
     */
    public String targetProductCode;
    
    /**
     * (����)<BR>
     * YYYYMM�`��<BR>
     */
    public String delivaryMonth;
    
    /**
     * (�I�v�V�������i�敪)<BR>
     * <BR>
     * P�F�v�b�g�I�v�V���� C�F�R�[���I�v�V����<BR>
     */
    public String opProductType;
    
    /**
     * (�s�g���i)
     */
    public String strikePrice;
    
    /**
     * (����s��)<BR>
     * <BR>
     * 1�F�����@@2�F���<BR>
     */
    public String marketCode;
    
    /**
     * (����敪)<BR>
     * <BR>
     * 3�F�V�K���������@@4�F�V�K��������<BR>
     * 5�F�����ԍϒ����i���ԍρj�@@6�F�����ԍϒ����i���ԍρj<BR>
     */
    public String tradingType;
    
    /**
     * (����)<BR>
     * ���o�C���Ŏg�p<BR>
     */
    public java.util.Date openDate;
    
    /**
     * (���P��)<BR>
     * ���o�C���Ŏg�p<BR>
     */
    public String contractPrice;
    
    /**
     * (��������)
     */
    public String opOrderQuantity;
    
    /**
     * (�����P���敪)<BR>
     * <BR>
     * 0�F���s�@@1�F�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)
     */
    public String limitPrice;
    
    /**
     * (�T�Z��n���)
     */
    public String estimatedPrice;
    
    /**
     * (�����L������)
     */
    public java.util.Date expirationDate;
    
    /**
     * (���s����)<BR>
     * <BR>
     * 1�F������ 3�F��t�@@4�F�����@@7�F�s�o���������s<BR>
     */
    public String execCondType;
    
    /**
     * (���������敪)<BR>
     * <BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;
    
    /**
     * (�t�w�l�p�v���~�A��/�����Y���i)<BR>
     * <BR>
     * 0�F�����Y���i�@@1�F�v���~�A��<BR>
     * <BR>
     * ���������敪�u1�F�t�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String stopPremium_underlyingAssets;
    
    /**
     * (�t�w�l�p���������P��)<BR>
     * <BR>
     * ���������敪�u1�F�t�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * <BR>
     * 1�F�ȉ��@@2�F�ȏ�<BR>
     * <BR>
     * ���������敪�u1�F�t�w�l�v�̏ꍇ�ݒ�
     */
    public String stopOrderCondOperator;
    
    /**
     * (�v�w�l�p�v���~�A���^�����Y���i)<BR>
     * <BR>
     * 0�F�����Y���i�@@1�F�v���~�A��<BR>
     * <BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�
     */
    public String wlimitPremium_underlyingAssets;
    
    /**
     * (�v�w�l�p���������P��)<BR>
     * <BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (�v�w�l�p�����������Z�q)<BR>
     * <BR>
     * 1�F�ȉ��@@2�F�ȏ�<BR>
     * <BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (�v�w�l�p�����P���敪)<BR>
     * <BR>
     * 0�F���s�@@1�F�w�l<BR>
     * <BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (�v�w�l�p�����P��)<BR>
     * <BR>
     * �v�w�l�p�����P���敪�u1�F�w�l�v�̏ꍇ�ݒ�
     */
    public String wLimitPrice;
    
    /**
     * (W�w�l�p���s����)<BR>
     * <BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitExecCondType;     
    
    /**
     * (W�w�l�p�L����ԋ敪)<BR>
     * <BR>
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L�� <BR>
     * 2�F�X�g�b�v���������� <BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitEnableStatusDiv; 
    
    /**
     * (W�w�l�p�֑ؑO�����P��)<BR>
     * <BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A�ݒ肳���<BR>
     */
    public String wlimitBefChgLimitPrice; 
    
    /**
     * (W�w�l�p�֑ؑO���s����)<BR>
     * <BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A�ݒ肳���<BR>
     */
    public String wlimitBefChgExecCondType; 
    
    /**
     * (�����������敪)<BR>
     * <BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l <BR>
     * <BR>
     * �������������s��̏ꍇ�ɐݒ�<BR>
     */
    public String orgOrderCondType; 
    
    /**
     * (���v���~�A��/�����Y���i)<BR>
     * <BR>
     * 0�F�v���~�A���@@1�F�����Y���i <BR>
     * <BR>
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgPremium_underlyingAssets; 
    
    /**
     * (�����������P��)<BR>
     * <BR>
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgOrderCondPrice; 
    
    /**
     * (�������������Z�q)<BR>
     * <BR>
     * 1�F�ȏ�@@2�F�ȉ� <BR>
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgCondOperator; 
    
    /**
     * (��W�w�l�p�����P���敪)<BR>
     * <BR>
     * 0�F���s�@@1�F�w�l <BR>
     * �������������敪��2�F�v�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgWLimitOrderPriceDiv; 
    
    /**
     * (��W�w�l�p�����P��)<BR>
     * �����v�w�l�p�����P���敪���A1�F�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgWLimitPrice; 
    
    /**
     * (��W�w�l�p���s����)<BR>
     * <BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s <BR>
     * �������������敪��2�F�v�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgWlimitExecCondType;
        
    /**
     * (������t��)
     */
    public java.util.Date orderDate;
    
    /**
     * (������ԋ敪)<BR>
     * <BR>
     * ���R�[�h�l�̓��b�Z�[�W��`�t�H���_�ȉ��� <BR> 
     * �uү���ޒ�`��_�����w���I�v�V����(���Ɖ�).xls�v��<BR> 
     * �u�w��OP�����������ڍׁv�V�[�g�̒�����ԋ敪��`���Q��<BR>
     */
    public String orderState;
    
    /**
     * (�J�z�G���[�R�[�h)<BR>
     * <BR>
     * 0001�F�l���G���[ 0002�F�a����s���G���[ 0003�F�����c���s���G���[ 0004�F�ۏ؋��s���G���[ <BR>
     * 0005�F�����c���s���G���[ 0006�F������~�����G���[ 0007�F�s��ύX�����G���[ <BR>0008�F���t�]�̓G���[ 
     * 0009�F���t�\���ʃG���[ 0010�F��������G���[ <BR>0011�F�o����~�����G���[ 9001�F���̑��G���[ <BR>
     * <BR>
     * ������ԋ敪���u51�F�J�z���s�v�̏ꍇ�ݒ�<BR>
     * <BR>
     * �����J�z�ŃG���[�����������ꍇ�́A�G���[���R�̃R�[�h�B<BR>
     * ��L�ȊO�̏ꍇ��null���Z�b�g�B
     */
    public String transferErrCode;
        
    /**
     * (��n��)<BR>
     * <BR>
     * ��肠��̏ꍇ�ݒ�
     */
    public Date deliveryDate;
    
    /**
     * ������
     */
    public Date orderBizDate;
    
    /**
     * (��萔��)<BR>
     * <BR>
     * ��肠��̏ꍇ�ݒ�<BR>
     */
    public String execQuantity;
    
    /**
     * (���P��)<BR>
     * <BR>
     * ��肠��̏ꍇ�ݒ�<BR>
     */
    public String execPrice;
    
    /**
     * (����ԋ敪)<BR>
     * <BR>
     * 0�F�����@@1�F�ꕔ�����@@2�F�S������<BR>
     * <BR>
     * ��肠��̏ꍇ�ݒ�
     */
    public String execType;
    
    /**
     * (�����z)<BR>
     * <BR>
     * ��肠��̏ꍇ�ݒ�
     */
    public String execTotalPrice;
    
    /**
     * (��n���z)<BR>
     * <BR>
     * ��肠��̏ꍇ�ݒ�
     */
    public String deliveryPrice;
    
    /**
     * (�萔��)<BR>
     * <BR>
     * ��肠��̏ꍇ�ݒ�
     */
    public String commission;
    
    /**
     * (�����)<BR>
     * <BR>
     * ��肠��̏ꍇ�ݒ�
     */
    public String consumptionTax;
    
    /**
     * (�������ڍז��)<BR>
     * <BR>
     * ��肠��̏ꍇ�ݒ�
     */
    public WEB3OptionsExecuteUnit[] opExecuteUnits;

    /**
     * (���X�R�[�h)<BR>
     */ 
    public String branchCode;
    /**
     * (�ڋq�R�[�h)<BR>
     */ 
    public String accountCode;
    /**
     * (�ڋq�� )<BR>
     */ 
    public String accountName;
    
    /**
     * ��������敪<BR>
     * 0�F�����l 1�F����� 2�F�ꕔ������� <BR>
     * 3�F�S���������4�F������s<BR>
     * 5�F������ 6�F�ꕔ�������� <BR>
     * 7�F�S���������� 8�F�������s 9�F�G���[<BR>
     */
    public String changeCancelDiv;
            
    /**
     * �����o�H�敪<BR>
     * 1�F�R�[���Z���^�[�@@<BR>
     * 2�F�o�b   3�F�X�����O�V���b�g <BR>
     * 4�Fi-mode 5�FVodafone <BR>
     * 6�FAU 9�FHOST A�F�Ǘ���<BR>
     */
    public String orderRootDiv;

    /**
     * ������<BR>
     * ���R�[�h�l�̓��b�Z�[�W��`�t�H���_�ȉ��� <BR>
     * �uү���ޒ�`��_�����w���I�v�V����(����).xls�v�̏����󋵋敪��`���Q��<BR>
     */
    public String transactionStateType;
    
    /**
     * (�x���敪)<BR>
     * <BR>
     * 0�F����@@1�F�x�� <BR>
     * <BR>
     * ���������敪���A�u1�F�t�w�l�v�܂��́u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String delayDiv;
    
    /**
     * (�蓮�����\�t���O)<BR>
     * <BR>
     * true�F�蓮�����\�@@�@@false�F�蓮�����s�� <BR>
     * <BR>
     * ���������敪���A�u1�F�t�w�l�v�܂��́u2�FW�w�l�v�ŁA <BR>
     * �蓮�������\�ł���ꍇ�Atrue���ݒ肳���B<BR>
     */
    public boolean manualFlag;
    
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
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsExecuteDetailsResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionsExecuteDetailsResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
