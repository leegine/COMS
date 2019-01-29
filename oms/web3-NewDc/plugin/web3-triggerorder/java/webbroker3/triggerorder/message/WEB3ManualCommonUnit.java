head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ManualCommonUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �蓮��������Unit(WEB3ManualCommonUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17�@@�]�V�q(���u) �V�K�쐬
                 : 2006/02/22�@@���(���u) �d�l�ύX�E���f��102
                 : 2006/08/24  ������(���u) ���f��No.156,162 ���� 
                 : 2006/10/24  ������(���u) ���f��No.179 ���� 
                 : 2006/11/10  ������(���u)�@@���f��No.187����
                   2006/12/14 �đo�g(���u) ���f��205
Revesion History : 2007/06/29 ���^�] (���u) �d�l�ύX���f��No.238 241
*/

package webbroker3.triggerorder.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�蓮��������Unit)<BR>
 * �蓮��������Unit�N���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3ManualCommonUnit extends Message 
{
    
    /**
     * (ID)<BR>
     * ����ID<BR>
     */
    public String id;
    
    /**
     * (�����������)<BR>
     * �����������<BR>
     * <BR>
     * 1�F�@@�A������<BR>
     * 2�F�@@OCO����<BR>
     * 3�F�@@IFD����<BR>
     * 4�F�@@�t�w�l����<BR>
     * 5�F�@@W�w�l����<BR>
     */
    public String triggerOrderType;
    
    /**
     * (�����������Z�q)<BR>
     * �����������Z�q<BR>
     * <BR>
     * 1�F�@@��l�ȏ�<BR>
     * 2�F�@@��l�ȉ�<BR>
     * <BR>
     * ������������ʂ�"�t�w�l����"�̏ꍇ�ݒ�<BR>
     */
    public String condOperator = null;
    
    /**
     * (���������P��)<BR>
     * ���������P��<BR>
     * <BR>
     * ������������ʂ�"�t�w�l����"�̏ꍇ�ݒ�<BR>
     */
    public String orderCondPrice = null;
    
    /**
     * (�v�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l <BR>
     * <BR>
     * ������������ʂ�"W�w�l����"�̏ꍇ�ݒ�<BR>
     */
    public String wLimitOrderPriceDiv = null;
    
    /**
     * (�v�w�l�p�����P��)<BR>
     * �v�w�l�p�����P���敪�u1�F�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wLimitPrice = null;
    
    /**
     * (�v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s <BR>
     * <BR>
     * ������������ʂ�"W�w�l����"�̏ꍇ�ݒ�<BR>
     */
    public String wlimitExecCondType = null;
    
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
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName = null;
    
    /**
     * (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     */
    public String productDiv;
    
    /**
     * (����敪)<BR>
     * ����敪<BR>
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
     * ���������敪<BR>
     * <BR>
     * 1�F��������<BR>
     * 2�F�o����܂Œ���<BR>
     * 3�F�[��܂Œ���<BR>
     */
    public String expirationDateType;
    
    /**
     * (�����L������)<BR>
     * �����L������<BR>
     * <BR>
     * �����������敪���u�o����܂Œ����v�̏ꍇ�ɐݒ�<BR>
     */
    public Date expirationDate = null;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public String orderQuantity;
    
    /**
     * (�����P���敪)<BR>
     * �����P���敪<BR>
     * <BR>
     * 0�F�@@���s<BR>
     * 1�F�@@�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     * �����P��<BR>
     * <BR>
     * �������P���敪���u�w�l�v�̏ꍇ�ɐݒ�<BR>
     */
    public String limitPrice = null;
    
    /**
     * (������ԋ敪)<BR>
     * ������ԋ敪<BR>
     * <BR>
     * 0�F�@@���̑�<BR>
     * 1�F�@@��t�ρi�V�K�����j<BR>
     * 2�F�@@�������i�V�K�����j<BR>
     * 3�F�@@�����ρi�V�K�����j<BR>
     * 6�F�@@�������s�i�V�K�����j<BR>
     * 7�F�@@��t�ρi�ύX�����j<BR>
     * 8�F�@@�������i�ύX�����j<BR>
     * 10�F�@@�����ρi�ύX�����j<BR>
     * 11�F�@@�������s�i�ύX�����j<BR>
     * 12�F�@@��t�ρi��������j<BR>
     * 13�F�@@�������i��������j<BR>
     * 14�F�@@�����ρi��������j<BR>
     * 15�F�@@�������s�i��������j<BR>
     * 20�F�@@�ꕔ����<BR>
     * 21�F�@@�S������<BR>
     * 22�F�@@����<BR>
     * 24�F�@@�ؑ֒���<BR>
     * 25�F�@@�ؑ֎�t<BR> 
     * 26�F�@@�ؑ֊���<BR>
     * 27�F�@@�ؑ֒���(���s)<BR> 
     * 50�F�@@�J�z��<BR>
     * 51�F�@@�J�z���s<BR>
     */
    public String orderState;
    
    /**
     * (����ԋ敪)<BR>
     * ����ԋ敪<BR>
     * <BR>
     * 0�F�@@�����<BR>
     * 1�F�@@�ꕔ����<BR>
     * 2�F�@@�S������<BR>
     */
    public String execType;
    
    /**
     * (��������敪 )<BR>
     * ��������敪<BR>
     * <BR>
     * 0�F�@@�����l<BR>
     * 1�F�@@�����<BR>
     * 2�F�@@�ꕔ�������<BR>
     * 3�F�@@�S���������<BR>
     * 4�F�@@������s<BR>
     * 5�F�@@������<BR>
     * 6�F�@@�ꕔ��������<BR>
     * 7�F�@@�S����������<BR>
     * 8�F�@@�������s<BR>
     * 9�F�@@�G���[<BR>
     */
    public String changeCancelDiv = null;
    
    /**
     * (�������� )<BR>
     * ��������<BR>
     */
    public Date orderDate;
    
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
     * (�����󋵋敪)<BR>
     * �����󋵋敪<BR>
     * <BR>
     * 1�F�@@�ҋ@@��<BR>
     * 2�F�@@������<BR>
     * 3�F�@@��������<BR>
     * 8�F�@@�����R���G���[<BR>
     * 9�F�@@�����x���G���[<BR>
     * 13�F�@@�X�g�b�v��������<BR>
     * 99�F�@@���̑�<BR>
     */
    public String triggerOrderState;
    
    /**
     * (�T�Z��n���)<BR>
     * �T�Z��n���<BR>
     */
    public String estimatedPrice;
    
    /**
     * (���Ϗ���)<BR>
     * ���Ϗ���<BR>
     * <BR>
     * 0�F�����_��<BR>
     * 1�F�P���v��<BR>
     * 2�F�P������<BR>
     * 3�F������<BR>
     */
    public String closingOrder = null;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 1:���ݒl<BR> 
     * 2:���C�z�l <BR>
     * 3:���C�z�l <BR>
     * 4:�O���I�l <BR>
     * <BR>
     * ��IFO�̏ꍇ�Anull���Z�b�g<BR>
     */
    public String currentPriceDiv = null;
    
    /**
     * (�����i���ݒl�j)<BR>
     * �����i���ݒl�j<BR>
     */
    public String currentPrice = null;
    
    /**
     * (�O����)<BR>
     * �O����<BR>
     */
    public String comparedPreviousDay = null;
    
    /**
     * (������ԁi�������\���ԁj)<BR>
     * ������ԁi�������\���ԁj<BR>
     */
    public Date currentPriceTime = null;

    /**
     * (���ݒl)
     */
    public String boardCurrentPrice = null;

    /**
     * (���ݒl����)
     */
    public Date boardCurrentPriceTime = null;

    /**
     * (���ݒl�敪)
     */
    public String boardCurrentPriceDiv = null;

    /**
     * (���ݒl�O����)
     */
    public String boardChange = null;

    /**
     * (�o����)
     */
    public String volume = null;

    /**
     * (�o��������)
     */
    public Date volumeTime = null;

    /**
     * (���C�z�l�^�C�g���敪)
     */
    public String askPriceTitle = null;

    /**
     * (���C�z�l)
     */
    public String askPrice = null;

    /**
     * (���C�z�l����)
     */
    public Date askPriceTime = null;

    /**
     * (���C�z�l�^�C�g���敪)
     */
    public String bidPriceTitle = null;

    /**
     * (���C�z�l)
     */
    public String bidPrice = null;

    /**
     * (���C�z�l����)
     */
    public Date bidPriceTime = null;

    /**
     * (��l�i)
     */
    public String basePrice = null;

    /**
     * (�蓮�����G���[�R�[�h)<BR>
     * �蓮�����G���[�R�[�h<BR>
     * <BR>
     * 00�F�@@����<BR>
     * 01�F�@@����σG���[<BR>
     * 02�F�@@�����σG���[<BR>
     * 03�F�@@�������s<BR>
     * 04�F�@@���σG���[ <BR>
     * 05�F�@@�����σG���[<BR>
     * 90�F�@@�Y�������Ȃ�<BR>
     * 99�F�@@���̑��G���[<BR>
     */
    public String manualOrderErrorCode;
    
    /**
     * (��������M����)
     * ��������M����
     */
    public Date currentPriceInfoAcceptTime = null;
    
    /**
     * (�g���K�[�N������)
     * �g���K�[�N������ 
     */
    public Date triggerStartTime = null;
    
    /**
     * (������������)
     * ������������
     */ 
    public Date orderCompleteTime = null;
    
    /**
     * (�����󋵋敪)
     * �����󋵋敪
     */
    public String transactionStateType = null;
    
    /**
     * (�P�������l)
     * �P�������l
     */
    public String priceAdjustmentValue = null;
    
    /**
     * (�萔�����)<BR>
     * �蓮�����萔�����<BR>
     */
    public WEB3ManualCommissionInfoUnit commissionInfo;

    /**
     * (����敪)<BR>
     * 1�F�[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@null �F ���̑�<BR>
     */
    public String sessionType;
    
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F48892038A
     */
    public WEB3ManualCommonUnit() 
    {
     
    }
}
@
