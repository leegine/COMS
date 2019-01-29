head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecuteDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������ڍ׃��X�|���X(WEB3EquityExecuteDetailsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 �����F (���u) �V�K�쐬
Revesion History : 2004/12/07 �����iSAR�j�c�Č��Ή� �m��.�P�U�W
Revesion History : 2004/12/14 �K�� (SRA) �c�Č��Ή� No.385
Revesion History : 2006/07/05 �юu�� (���u) �d�l�ύX941
Revesion History : 2006/08/02 �h�C (���u) �d�l�ύX ���f��No.959��Ή�
                   2006/08/29 �����F(���u) ���f�� 972
                   2006/11/01 �����F(���u) ���f�� 948,989,999
                   2006/11/20 �����F(���u) ���f�� 1056
Revesion History : 2007/07/24 �����q(���u) ���f�� 1184
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i���������������ڍ׃��X�|���X�j�B<BR>
 * <BR>
 * ���������������ڍ׉����@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityExecuteDetailsResponse extends WEB3GenResponse
{

    /**
     * (ID)<BR>
     * ����ID<BR>
     */
    public String id;

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
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;

    /**
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����@@5�F�X�g�b�N�I�v�V����<BR>
     */
    public String taxType;

    /**
     * (����敪)<BR>
     * 1�F�����������@@2�F�����������@@99�F����O����<BR>
     */
    public String tradingType;

    /**
     * (�l�i����)<BR>
     * 0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l�@@7:���s�c�����<BR>
     */
    public String priceCondType;

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
     * ���������敪���u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * ���������敪���u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (W�w�l�p���������P��)<BR>
     * ���������敪���u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (W�w�l�p�����������Z�q)<BR>
     * ���������敪���u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (W�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (W�w�l�p�����P��)<BR>
     * �v�w�l�p�����P���敪���A�u1�F�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wLimitPrice;

    /**
     * (�v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
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
     * (�v�w�l�p�֑ؑO���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
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
     * 1�F�ȏ�@@2�F�ȉ� <BR>
     */
    public String orgOrderCondOperator;

    /**
     * (���v�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * �������������敪��2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgWlimitOrderPriceDiv;

    /**
     * (���v�w�l�p�����P��)<BR>
     * �����v�w�l�p�����P���敪���A1�F�w�l�̏ꍇ�ݒ肳���B<BR>
     */
    public String orgWlimitPrice;

    /**
     * (���v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * �������������敪��2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgWlimitExecCondType;

    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public String orderQuantity;

    /**
     * (�����P���敪)<BR>
     * 0:���s�@@1:�w�l<BR>
     */
    public String orderPriceDiv;

    /**
     * (�����P��)<BR>
     * �����P��<BR>
     */
    public String limitPrice;

    /**
     * (�T�Z��n���)<BR>
     * �T�Z��n���<BR>
     */
    public String estimatedPrice;

    /**
     * (�����L������)<BR>
     * �����L������<BR>
     */
    public Date expirationDate;

    /**
     * (������t��)<BR>
     * �󒍓���<BR>
     */
    public Date orderDate;

    /**
     * (������ԋ敪)<BR>
     * 0:���̑�,1:��t�ρi�V�K�����j,2:�������i�V�K�����j,<BR>
     * 3:�����ρi�V�K�����j,6:�������s�i�V�K�����j,<BR>
     * 7:��t�ρi�ύX�����j,8:�������i�ύX�����j,<BR>
     * 10:�����ρi�ύX�����j,11:�������s�i�ύX�����j,<BR>
     * 12:��t�ρi��������j,13:�������i��������j,<BR>
     * 14:�����ρi��������j,15:�������s�i��������j,<BR>
     * 20�F�ꕔ����, 21�F�S������, 22�F����,<BR> 
     * 24:�ؑ֒��� 25:�ؑ֎�t 26:�ؑ֊��� 27:�ؑ֒���(���s)<BR>
     * 50:�J�z�ρ@@51:�J�z���s<BR>
     * <BR>
     * ���j�J�z�ρA�J�z���s�̃R�[�h�ɂ��ẮA����<BR>
     * PS�l���DIR�l�Ɋm�F��<BR>
     */
    public String orderState;

    /**
     * (�J�z�G���[�R�[�h)<BR>
     * 0001�F�l���G���[ 0002�F�a����s���G���[ <BR>
     * 0003�F�����c���s���G���[ 0004�F�ۏ؋��s���G���[ <BR>
     * 0005�F�����c���s���G���[ 0006�F������~�����G���[ <BR>
     * 0007�F�s��ύX�����G���[ 0008�F���t�]�̓G���[ <BR>
     * 0009�F���t�\���ʃG���[ 0010�F��������G���[ <BR>
     * 0011�F�����J�z�X�L�b�v�����G���[ 9001�F���̑��G���[ <BR>
     * ������ԋ敪���u51�F�J�z���s�v�̏ꍇ�Z�b�g <BR>
     * �����J�z�ŃG���[�����������ꍇ�́A�G���[���R�̃R�[�h�B <BR>
     * ��L�ȊO�̏ꍇ��null���Z�b�g�B<BR>
     * <BR>
     * �����J�z�ŃG���[�����������ꍇ�́A�G���[���R�̃R�[�h�B<BR>
     * ��L�ȊO�̏ꍇ��null���Z�b�g�B<BR>
     */
    public String transferErrCode;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date orderBizDate;


    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    public Date deliveryDate;

    /**
     * (��芔��)<BR>
     * ���v��芔��<BR>
     */
    public String execQuantity;

    /**
     * (���P��)<BR>
     * ���P��<BR>
     */
    public String execPrice;

    /**
     * (�����)<BR>
     * �����<BR>
     */
    public String execTotalPrice;

    /**
     * (����ԋ敪)<BR>
     * 1:�����@@2:�ꕔ���@@<BR>
     */
    public String execType;

    /**
     * (��n���)<BR>
     * ��n���<BR>
     */
    public String deliveryPrice;

    /**
     * (�T�Z���v)<BR>
     * �T�Z���v<BR>
     */
    public String estimatedProfitLoss;

    /**
     * (�萔�����)<BR>
     * �萔�����<BR>
     */
    public WEB3EquityCommissionInfoUnit commissionInfo;

    /**
     * (�������ꗗ)<BR>
     * �������ꗗ<BR>
     */
    public WEB3EquityExecuteUnit[] executeUnits;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�ڋq��)<BR>
     * �ڋq��<BR>
     */
    public String accountName;

    /**
     * (��������敪)<BR>
     * 0�F�����l  1�F�����  2�F�ꕔ��������@@3�F�S����������@@4�F������s<BR>
     * 5�F�������@@6�F�ꕔ���������@@7�F�S�����������@@8�F�������s�@@9�F�G���[<BR>
     */
    public String changeCancelDiv;

    /**
     * (�����o�H�敪)<BR>
     * 1�F�R�[���Z���^�[�@@2�F�o�b�@@3:�X�����O�V���b�g<BR>
     * 4�Fi-mode�@@5�FVodafone�@@6�FAU�@@7�F�X�����O�V���b�g�i�����j�@@9�FHOST<BR>
     * A�F�Ǘ��ҁ@@B�F�ۏ؋������U�փo�b�`�@@C�F���b�`�N���C�A���g<BR>
     * F�FIVR�i���������d�b�j�@@G�F��������<BR>
     */
    public String orderRootDiv;
    
    /**
     * (������)<BR>
     * �uү���ޒ�`��_��������(����).xls�v<BR> 
     * �y�����󋵋敪��`�zsheet�Q�ƁB <BR>
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
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_exec_details";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405111057L;

    /**
     * @@roseuid 40A288A40186
     */
    public WEB3EquityExecuteDetailsResponse(WEB3EquityExecuteDetailsRequest l_request)
    {
        super(l_request);
    }
    /**
     * @@roseuid 40A288A40186
     */
    public WEB3EquityExecuteDetailsResponse()
    {
    }    
}
@
