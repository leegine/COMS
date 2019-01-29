head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToOrderRefCommonUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�����Ɖ��Unit(WEB3AdminToOrderRefCommonUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16�@@�]�V�q(���u) �V�K�쐬
                 : 2006/08/23�@@�юu��(���u) �d�l�ύXNo.66,71,080
                 : 2006/10/18  ������(���u) �d�l�ύX���f��No.094
                 : 2006/11/10  �đo�g(���u) �d�l�ύX���f��No.109
Revesion History : 2007/06/30  �Ј���(���u) �d�l�ύX���f��No.129
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�g���K�[�����Ǘ��ҁE�����Ɖ��Unit)<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToOrderRefCommonUnit extends Message
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
     * ������������ʂ�"�t�w�l����"�܂���"�v�w�l"�̏ꍇ�ɐݒ�<BR>
     */
    public String condOperator = null;
    
    /**
     * (���������P��)<BR>
     * ���������P��<BR>
     * <BR>
     * ������������ʂ�"�t�w�l����"�܂���"�v�w�l"�̏ꍇ�ɐݒ�<BR>
     */
    public String orderCondPrice = null;
    
    /**
     * (�v�w�l�p�����P���敪)<BR>
     * 0�F�@@���s <BR>
     * 1�F�@@�w�l <BR>
     * <BR>
     * ������������ʂ�"�v�w�l"�̏ꍇ�ɐݒ�<BR>
     */
    public String wLimitOrderPriceDiv = null;
    
    /**
     * (�v�w�l�p�����P��)<BR>
     * ���v�w�l�p�����P���敪��"�w�l"�̏ꍇ�ݒ�<BR>
     */
    public String wLimitPrice = null;
    
    /**
     * (�v�w�l�p���s����)<BR>
     * 1�F�@@������<BR> 
     * 3�F�@@��t<BR> 
     * 4�F�@@����<BR> 
     * 7�F�@@�s�o���������s <BR>
     * <BR>
     * ������������ʂ�"�v�w�l"�̏ꍇ�ɐݒ�<BR>
     */
    public String wlimitExecCondType = null;
    
    /**
     * (�v�w�l�p�L����ԋ敪)<BR>
     * 0�F�@@���~�b�g�����L�� <BR>
     * 1�F�@@�X�g�b�v�����L�� <BR>
     * 2�F�@@�X�g�b�v���������� <BR> 
     * <BR>
     * ������������ʂ�"�v�w�l"�̏ꍇ�ݒ�<BR>
     */
    public String wlimitEnableStatusDiv = null;
    
    /**
     * (�v�w�l�p�֑ؑO�����P��)<BR>
     * ������������ʂ�"�v�w�l"�̏ꍇ�A�ݒ�<BR>
     */
    public String wlimitBefChgLimitPrice = null;
    
    /**
     * (�v�w�l�p�֑ؑO���s����)<BR>
     * 1�F�@@�������@@<BR>
     * 3�F�@@��t<BR>
     * 4�F�@@����<BR> 
     * 7�F�@@�s�o���������s <BR>
     * <BR>
     * ������������ʂ�"�v�w�l"�̏ꍇ�A�ݒ�<BR>
     */
    public String wlimitBefChgExecCondType = null;
    
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
     * (������)<BR>
     * ������<BR>
     */
    public String productName = null;
    
    /**
     * (���i�敪)<BR>
     * ���i�敪 <BR>
     * <BR>
     * 1�F�@@�������� <BR>
     * 2�F�@@�M�p��� <BR>
     * 3�F�@@�敨 <BR>
     * 4�F�@@�I�v�V���� <BR>
     */
    public String productDiv;
    
    /**
     * (����敪)<BR>
     * ����敪<BR>
     * <BR>
     * 1�F�@@�������t���� <BR>
     * 2�F�@@�������t���� <BR>
     * 3�F�@@�V�K�������� <BR>
     * 4�F�@@�V�K�������� <BR>
     * 5�F�@@�����ԍϒ��� <BR>
     * 6�F�@@�����ԍϒ��� <BR>
     * 7�F�@@�������� <BR>
     * 8�F�@@���n���� <BR>
     * 601�F�@@�w���敨�V�K�������� <BR>
     * 602�F�@@�w���敨�V�K�������� <BR>
     * 603�F�@@�w���敨�����ԍϒ��� <BR>
     * 604�F�@@�w���敨�����ԍϒ��� <BR>
     * 605�F�@@�w���I�v�V�����V�K�������� <BR>
     * 606�F�@@�w���I�v�V�����V�K�������� <BR>
     * 607�F�@@�w���I�v�V���������ԍϒ��� <BR>
     * 608�F�@@�w���I�v�V���������ԍϒ��� <BR>
     */
    public String tradingType;
    
    /**
     * (���s����)<BR>
     * ���s����<BR>
     * <BR>
     * 1�F�@@�������@@<BR>
     * 3�F�@@��t�@@<BR>
     * 4�F�@@�����@@<BR>
     * 7�F�@@�s�o���������s<BR>
     */
    public String execCondType;
    
    /**
     * (�����L������)<BR>
     * �����L������ <BR>
     * <BR>
     * ���o����܂Œ����̏ꍇ�ݒ�<BR>
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
     * 0�F�@@���s�@@<BR>
     * 1�F�@@�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     * �����P��<BR>
     */
    public String limitPrice = null;
    
    /**
     * (������ԋ敪)<BR>
     * ������ԋ敪<BR>
     * <BR>
     * 0�F�@@���̑� <BR>
     * 1�F�@@��t�ρi�V�K�����j <BR>
     * 2�F�@@�������i�V�K�����j <BR>
     * 3�F�@@�����ρi�V�K�����j  <BR>
     * 6�F�@@�������s�i�V�K�����j <BR>
     * 7�F�@@��t�ρi�ύX�����j <BR>
     * 8�F�@@�������i�ύX�����j  <BR>
     * 10�F�@@�����ρi�ύX�����j <BR>
     * 11�F�@@�������s�i�ύX�����j <BR>
     * 12�F�@@��t�ρi��������j  <BR>
     * 13�F�@@�������i��������j <BR>
     * 14�F�@@�����ρi��������j <BR>
     * 15�F�@@�������s�i��������j <BR>
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
     * 0�F�@@�����@@<BR>
     * 1�F�@@�ꕔ�����@@<BR>
     * 2�F�@@�S������ <BR>
     */
    public String execType;
    
    /**
     * (��������敪)<BR>
     * ��������敪 <BR>
     * <BR>
     * 0�F�@@�����l <BR>
     * 1�F�@@����� <BR>
     * 2�F�@@�ꕔ������� <BR>
     * 3�F�@@�S��������� <BR>
     * 4�F�@@������s <BR>
     * 5�F�@@������ <BR>
     * 6�F�@@�ꕔ�������� <BR>
     * 7�F�@@�S���������� <BR>
     * 8�F�@@�������s <BR>
     * 9�F�@@�G���[ <BR>
     * A�F�@@W�w�l�����ؑ֒�<BR> 
     * B�F�@@W�w�l�����ꕔ�ؑ֊���<BR> 
     * C�F�@@W�w�l�����S���ؑ֊���<BR> 
     * D�F�@@W�w�l�����ؑ֎��s<BR>
     */
    public String changeCancelDiv;
    
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
     * (��������M����)<BR>
     * ��������M����<BR>
     * <BR>
     * ��������ʂ�"�\�񒍕�"�̏ꍇ�A�܂��́A<BR>
     * �@@����������M���Ă��Ȃ��ꍇ�́Anull���Z�b�g�B<BR>
     */
    public Date currentPriceInfoAcceptTime = null;
    
    /**
     * (�g���K�[�N������t)<BR>
     * �g���K�[�N������<BR>
     * <BR>
     * ���N���ʒm���s���Ă��Ȃ��ꍇ�Anull���Z�b�g�B<BR>
     */
    public Date triggerStartTime = null;
    
    /**
     * (������������)<BR>
     * ������������<BR>
     * <BR>
     * �������������������Ă��Ȃ��ꍇ�Anull���Z�b�g�B<BR>
     */
    public Date orderCompleteTime = null;

    /**
     * (���ݒl�ƍ��敪)<BR>
     * ���ݒl�ƍ��敪 <BR>
     * <BR>
     * 0�F�@@���� <BR>
     * 1�F�@@�������^��<BR>
     * 2�F�@@�s�������^�� <BR>
     * 3�F�@@�����x���^�� <BR>
     * <BR>
     * ���u9�F�@@�S�ẴG���[�v�͐ݒ肳��Ȃ�<BR>
     */
    public String tickMatchDiv = null;

    /**
     * (���ݒl�\�z����)<BR>
     * ���ݒl�\�z����<BR>
     */
    public Date tickExpectTime = null;
    
    /**
     * (�蓮�����\�t���O)<BR>
     * �蓮�����\�t���O<BR>
     */
    public boolean manualFlag;

    /**
     * (�[��O�J�z�Ώۃt���O)<BR>
     * �[��O�J�z�Ώۃt���O<BR>
     * false�F�[��O�J�z�Ȃ� <BR>
     * true�F�[��O�J�z���� <BR>
     */
    public boolean eveningSessionCarryoverFlag;

    /**
     * (����敪)<BR>
     * ����敪<BR>
     * 1�F�[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@NULL�F���̑� <BR>
     */
    public String sessionType = null;

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F1B3C9032C
     */
    public WEB3AdminToOrderRefCommonUnit() 
    {
     
    }
}
@
