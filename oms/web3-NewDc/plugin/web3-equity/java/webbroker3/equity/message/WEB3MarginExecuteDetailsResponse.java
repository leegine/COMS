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
filename	WEB3MarginExecuteDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p����������ڍ׃��X�|���X�N���X(WEB3MarginExecuteDetailsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 ������ (���u) �V�K�쐬
Revesion History : 2004/12/10 �K�� (SRA) �C��
Revesion History : 2006/07/05 �юu�� (���u) �d�l�ύX���f��941
Revesion History : 2006/11/02 �����F(���u) ���f�� 948,999
Revesion History : 2006/11/20 �����F(���u) ���f�� 1056
Revesion History : 2007/06/05 �����q (���u) �d�l�ύX�E���f��1164
Revesion History : 2007/07/24 �����q (���u) �d�l�ύX�E���f��1184
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p����������ڍ׃��X�|���X�j�B<br>
 * <br>
 * �M�p����������ڍ׃��X�|���X�N���X
 * @@version 1.0
 */
public class WEB3MarginExecuteDetailsResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_executeDetails";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101804L;        
    /**
     * (����No)<BR>
     */
    public String orderActionId;
    
    /**
     * (�����R�[�h)<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     */
    public String productName;
    
    /**
     * (�s��R�[�h)<BR>
     */
    public String marketCode;
    
    /**
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����<BR>
     */
    public String taxType;
    
    /**
     * (����敪)<BR>
     * 3�F�V�K���������@@4�F�V�K��������<BR>
     * 5�F�����ԍϒ����i���ԍρj�@@6�F�����ԍϒ����i���ԍρj<BR>
     * 7�F���������@@8�F���n����<BR>
     * <BR>
     * ���ό��ʈꗗ��ʂւ̃n�C�p�[�����N�̗L������ɂ��g�p<BR>
     */
    public String tradingType;
    
    /**
     * (�M�p����ٍ�)<BR>
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (�l�i����)<BR>
     * 0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l�@@ 7:���s�c����� <BR>
     */
    public String priceCondType;
    
    /**
     * (���s����)<BR>
     * 1�F�������@@3�F��t�@@4�F�����@@7�F�s�o���������s<BR>
     */
    public String execCondType;
    
    /**
     * (���������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;
    
    /**
     * (�t�w�l�p���������P��)<BR>
     * ���������敪�u1�F�t�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * 1�F�ȉ��@@2�F�ȏ�<BR>
     * <BR>
     * ���������敪�u1�F�t�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * (�v�w�l�p���������P��)<BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (�v�w�l�p�����������Z�q)<BR>
     * 1�F�ȉ��@@2�F�ȏ�<BR>
     * <BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (�v�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * <BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (�v�w�l�p�����P���敪�u1�F�w�l�v�̏ꍇ�ݒ�)<BR>
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
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳��� <BR>
     */
    public String wlimitEnableStatusDiv;
    
    /**
     * (�v�w�l�p�֑ؑO�����P��)<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A�ݒ肳���<BR>
     */
    public String wlimitBefChgLimitPrice;
    
    /**
     * (�v�w�l�p�֑ؑO���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s <BR>
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
     */
    public String orderQuantity;
    
    /**
     * (�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     */
    public String limitPrice;
    
    /**
     * (�T�Z��n���)<BR>
     * �V�K���@@�@@�F�@@�T�Z�����<BR>
     * �ԍρ@@�@@�@@�F�@@�T�Z���ϑ��v���<BR>
     * �������n�@@�F�@@�T�Z��n���<BR>
     */
    public String estimatedPrice;
    
    /**
     * (�����L������)
     */
    public Date expirationDate;
    
    /**
     * (������t��)<BR>
     */
    public Date orderDate;
    
    /**
     * (������ԋ敪)<BR>
     * 0�F���̑� 1�F��t�ρi�V�K�����j 2�F�������i�V�K�����j <BR>
     * 3�F�����ρi�V�K�����j 6�F�������s�i�V�K�����j <BR>
     * 7�F��t�ρi�ύX�����j 8�F�������i�ύX�����j <BR>
     * 10�F�����ρi�ύX�����j 11�F�������s�i�ύX�����j 12�F��t�ρi��������j <BR>
     * 13�F�������i��������j 14�F�����ρi��������j 15�F�������s�i��������j <BR>
     * 20�F�ꕔ���� 21�F�S������ 22�F���� <BR>
     * 24:�ؑ֒��� 25:�ؑ֎�t 26:�ؑ֊��� 27:�ؑ֒���(���s)<BR>
     * 50�F�J�z�� 51�F�J�z���s<BR>
     */
    public String orderState;
    
    /**
     * (�J�z�G���[�R�[�h)<BR>
     * 0001�F�l���G���[ 0002�F�a����s���G���[ 0003�F�����c���s���G���[<BR>
     * 0004�F�ۏ؋��s���G���[ 0005�F�����c���s���G���[ 0006�F������~�����G���[<BR>
     * 0007�F�s��ύX�����G���[ 0008�F���t�]�̓G���[ 0009�F���t�\��<BR>
     * �ʃG���[ 0010�F��������G���[ 0011�F�����J�z�X�L�b�v�����G���[�@@0012�F<BR>
     * ��K���`�F�b�N�G���[�@@0014�F�Ēl�`�F�b�N�G���[�@@0015�F�󔄂�`�F�b�N�G���[<BR>
     * 0016�F���ϊ��������σG���[�@@9001�F���̑��G���[<BR>
     * <BR>
     * ������ԋ敪���u51�F�J�z���s�v�̏ꍇ�ݒ�<BR>
     * <BR>
     * �����J�z�ŃG���[�����������ꍇ�́A�G���[���R�̃R�[�h�B<BR>
     * ��L�ȊO�̏ꍇ��null���Z�b�g�B<BR>
     */
    public String transferErrCode;
    
    /**
     * (������)<BR>
     * ������
     */
    public Date orderBizDate;
    
    /**
     * (��n��)<BR>
     * ��肠��̏ꍇ�ݒ�<BR>
     */
    public Date deliveryDate;
    
    /**
     * (��芔��)<BR>
     * ��肠��̏ꍇ�ݒ�<BR>
     */
    public String execQuantity;
    
    /**
     * (���P��)<BR>
     * ��肠��̏ꍇ�ݒ�<BR>
     */
    public String execPrice;
    
    /**
     * (�����)<BR>
     * <BR>
     * ��肠��̏ꍇ�ݒ�<BR>
     */
    public String execTotalPrice;
    
    /**
     * (����ԋ敪)<BR>
     * 0�F�����@@1�F�ꕔ�����@@2�F�S�������@@<BR>
     * <BR>
     * ��肠��̏ꍇ�ݒ�<BR>
     */
    public String execType;
    
    /**
     * (��n���)<BR>
     * �V�K���@@�@@ �F�@@�����<BR>
     * �ԍρ@@�@@ �@@ �F�@@���ϑ��v���<BR>
     * �������n�@@�F�@@��n���<BR>
     */
    public String deliveryPrice;
    
    /**
     * (�M�p����萔�����)<BR>
     */
    public WEB3MarginCommissionInfoUnit commissionInfo;
    
    /**
     * (�������ꗗ)<BR>
     * �M�p����������<BR>
     */
    public WEB3MarginExecuteUnit[] executeUnits;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (�ڋq��)<BR>
     * �ڋq��
     */
    public String accountName;
    
    /**
     * (��������敪)<BR>
     * ��������敪 <BR>
     *�i0:�����l 1:����� 2:�ꕔ������� 3:�S��������� 4:������s <BR>
�@@   *  5:������ 6:�ꕔ�������� 7:�S���������� 8:�������s 9:�G���[�j
     */
    public String changeCancelDiv;
    
    /**
     * (�����o�H�敪)<BR>
     * �����o�H�敪<BR>
     * �i1�F�R�[���Z���^�[�@@2�F�o�b�@@3:�X�����O�V���b�g�@@4�Fi-mode�@@5�FVodafone<BR>
�@@   * �@@6�FAU�@@�V�F�X�����O�V���b�g(����)�@@9�FHOST�@@A�F�Ǘ���<BR>
�@@   * �@@B�F�ۏ؋������U�փo�b�`�@@C�F���b�`�N���C�A���g�@@F�FIVR�i���������d�b�j<BR>
�@@   * �@@G�F�������ρj<BR>
     */
    public String orderRootDiv;
    
    /**
     * (������)<BR>
     * �uү���ޒ�`��_�M�p���(����).xls�v<BR> 
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
     * ���������敪<BR>
     * <BR>
     * 0�F�@@�I�[�v��<BR>
     * 1�F�@@����������<BR>
     */
    public String forcedLapseDiv;

    /**
     * @@roseuid 4140485A037D
     */
    public WEB3MarginExecuteDetailsResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginExecuteDetailsResponse(WEB3MarginExecuteDetailsRequest l_request)
    {
        super(l_request);
    }
}
@
