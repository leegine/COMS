head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.45.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOROrderExecutionRefCommon.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ғ������Ɖ��Unit (WEB3AdminOROrderExecutionRefCommon.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 ���@@�_�O(���u) ���f���ύX�_087�E088�E090�E091
                   2006/10/16 ���� (���u) �d�l�ύX�E���f��060�A069
                   2006/12/04 ������ (���u) ���f��No.086
                   2006/12/19 �����F (���u) �d�l�ύX�E���f��087
Revesion History : 2007/07/01 �����F(���u) ���f��No.101
Revesion History : 2007/08/14 �����q(���u) ���f��No.105
Revesion History : 2008/10/02 ���V(SRA) �y�Ǘ��Ғ������Ɖ�z�d�l�ύX�Ǘ��䒠�i���f���jNo.130
*/
package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�Ǘ��Ғ������Ɖ��Unit)<BR>
 * <BR>
 * �Ǘ��Ғ������Ɖ��Unit�N���X<BR>
 * <BR>
 * WEB3AdminOROrderExecutionRefCommon<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOROrderExecutionRefCommon extends Message
{
    /**
     * (ID)<BR>
     * <BR>
     * �����h�c<BR>
     * <BR>
     * id<BR>
     * <BR>
     */
    public String id;

    /**
     * (���X�R�[�h)<BR>
     * <BR>
     * ���X�R�[�h<BR>
     * <BR>
     * branchCode<BR>
     * <BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * <BR>
     * �ڋq�R�[�h<BR>
     * <BR>
     * accountCode<BR>
     * <BR>
     */
    public String accountCode;

    /**
     * (�ڋq��)<BR>
     */
    public String accountName;

    /**
     * (���҃R�[�h(SONAR))<BR>
     * <BR>
     * �ڋq�̈��҃R�[�h(SONAR)<BR>
     * <BR>
     */
    public String sonarTraderCode;

    /**
     * (���i�敪)<BR>
     * <BR>
     * ���i�敪<BR>
     * <BR>
     * 0�F�@@��������<BR>
     * 1�F�@@�M�p���<BR>
     * 2�F�@@�����~�j����<BR>
     * 3�F�@@�I�v�V����<BR>
     * 4�F�@@�敨<BR>
     * 5�F�@@���M<BR>
     * 6�F�@@�ݓ�<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * productDiv<BR>
     * 0: Def.EQUITY<BR>
     * 1: Def.MARGIN<BR>
     * 2: Def.MINI_STOCK<BR>
     * 3: Def.OPTION<BR>
     * 4: Def.FUTURE<BR>
     * 5: Def.MF<BR>
     * 6: Def.RUITO<BR>
     * <BR>
     */
    public String productDiv;

    /**
     * (����敪)<BR>
     * <BR>
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
     * 99�F�@@����O����<BR>
     * 101�F�@@�~�j�����t����<BR>
     * 102�F�@@�~�j�����t����<BR>
     * 201�F�@@�����M�����t����<BR>
     * 202�F�@@�����M�����t����<BR>
     * 203�F�@@�����M����W����<BR>
     * 204�F�@@�����M���抷����<BR>
     * 501�F�@@�ݓ����t����<BR>
     * 502�F�@@�ݓ����t����<BR>
     * 601�F�@@�w���敨�V�K��������<BR>
     * 602�F�@@�w���敨�V�K��������<BR>
     * 603�F�@@�w���敨�����ԍϒ���<BR>
     * 604�F�@@�w���敨�����ԍϒ���<BR>
     * 605�F�@@�w���I�v�V�����V�K��������<BR>
     * 606�F�@@�w���I�v�V�����V�K��������<BR>
     * 607�F�@@�w���I�v�V���������ԍϒ���<BR>
     * 608�F�@@�w���I�v�V���������ԍϒ���<BR>
     * 701�F�@@�O���������t<BR>
     * 702�F�@@�O���������t<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * tradingDiv<BR>
     * 1: Def.EQUITY_BUY<BR>
     * 2: Def.EQUITY_SELL<BR>
     * 3: Def.MARGIN_LONG<BR>
     * 4: Def.MARGIN_SHORT<BR>
     * 5: Def.CLOSE_MARGIN_LONG<BR>
     * 6: Def.CLOSE_MARGIN_SHORT<BR>
     * 7: Def.SWAP_MARGIN_LONG<BR>
     * 8: Def.SWAP_MARGIN_SHORT<BR>
     * 99: Def.SALES_OUTSIDE_MARKET <BR>
     * 101: Def.MINI_STOCK_BUY<BR>
     * 102: Def.MINI_STOCK_SELL<BR>
     * 201: Def.MF_BUY<BR>
     * 202: Def.MF_SELL<BR>
     * 203: Def.MF_RECRUIT<BR>
     * 204: Def.MF_SWITCHING<BR>
     * 501: Def.RUITO_BUY<BR>
     * 502: Def.RUITO_SELL<BR>
     * 601: Def.IDX_FUTURES_BUY_TO_OPEN<BR>
     * 602: Def.IDX_FUTURES_SELL_TO_OPEN<BR>
     * 603: Def.IDX_FUTURES_BUY_TO_CLOSE<BR>
     * 604: Def.IDX_FUTURES_SELL_TO_CLOSE<BR>
     * 605: Def.IDX_OPTIONS_BUY_TO_OPEN<BR>
     * 606: Def.IDX_OPTIONS_SELL_TO_OPEN<BR>
     * 607: Def.IDX_OPTIONS_BUY_TO_CLOSE<BR>
     * 608: Def.IDX_OPTIONS_SELL_TO_CLOSE<BR>
     * 701: Def.FEQ_BUY<BR>
     * 702: Def.FEQ_SELL<BR>
     * <BR>
     */
    public String tradingType;

    /**
     * (��������)<BR>
     * <BR>
     * ��������<BR>
     * <BR>
     * orderQuantity<BR>
     * <BR>
     */
    public String orderQuantity;

    /**
     * (�����P���敪)<BR>
     * <BR>
     * 0�F���s�@@1�F�w�l<BR>
     * <BR>
     * orderPriceDiv<BR>
     * 0: Def.UNLIMIT_PRICE�@@1: Def.LIMIT_PRICE<BR>
     * <BR>
     */
    public String orderPriceDiv;

    /**
     * (�����P��)<BR>
     * <BR>
     * �����P��<BR>
     * <BR>
     * orderPrice<BR>
     * <BR>
     */
    public String orderPrice;

    /**
     * (�T�Z��n���)<BR>
     * <BR>
     * �T�Z��n���<BR>
     * <BR>
     * estimatedPrice<BR>
     * <BR>
     */
    public String estimatedPrice;

    /**
     * (�����`���l��)<BR>
     * <BR>
     * �����`���l��<BR>
     * <BR>
     * 0�F�@@�c�ƓX<BR>
     * 1�F�@@�C���^�[�l�b�g<BR>
     * 2�F�@@�R�[���Z���^�[<BR>
     * 3�F�@@���o�C��<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderChannel<BR>
     * <BR>
     * 0: Def.BRANCH<BR>
     * 1: Def.INTERNET<BR>
     * 2: Def.CALL_CENTER<BR>
     * 3: Def.MOBILE<BR>
     * <BR>
     */
    public String orderChannel;

    /**
     * (������ԋ敪)<BR>
     * <BR>
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
     * 23�F�@@�蓮����<BR>
     * 50�F�@@�J�z��<BR>
     * 51�F�@@�J�z���s<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderState<BR>
     * <BR>
     * 0: Def.UNDEFINED<BR>
     * 1: Def.ACCEPTED<BR>
     * 2: Def.ORDERING<BR>
     * 3: Def.ORDERED<BR>
     * 6: Def.NOT_ORDERED<BR>
     * 7: Def.MODIFY_ACCEPTED�j<BR>
     * 8: Def.MODIFYING<BR>
     * 10: Def.MODIFIED<BR>
     * 11: Def.NOT_MODIFIED<BR>
     * 12: Def.CANCEL_ACCEPTED<BR>
     * 13: Def.CANCELLING<BR>
     * 14: Def.CANCELLED<BR>
     * 15: Def.NOT_CANCELLED<BR>
     * 20: Def.PART_INAFFECTED<BR>
     * 21: Def.FULL_INAFFECTED<BR>
     * 22: Def.CLOSED<BR>
     * 50: Def.TRANSFERED<BR>
     * 51: Def.NOT_TRANSFERED<BR>
     * <BR>
     */
    public String orderState;

    /**
     * (��������)<BR>
     * <BR>
     * ��������<BR>
     * <BR>
     * orderDate<BR>
     * <BR>
     */
    public Date orderDate;

    /**
     * (������)<BR>
     * <BR>
     * ������<BR>
     * <BR>
     * orderBizDate<BR>
     * <BR>
     */
    public Date orderBizDate;

    /**
     * (���s����)<BR>
     * <BR>
     * 1�F�������@@3�F��t�@@4�F�����@@7�F�s�o���������s<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * execCondType<BR>
     * 1: Def.NO_CONDITION<BR>
     * 3: Def.AT_MARKET_OPEN<BR>
     * 4: Def.AT_MARKET_CLOSE<BR>
     * 7: Def.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * <BR>
     */
    public String execCondType;

    /**
     * (�����L������)<BR>
     * <BR>
     * �����L������<BR>
     * <BR>
     * ���o����܂Œ����̏ꍇ�ݒ�<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * expirationDate<BR>
     * <BR>
     * If order only today, set null<BR>
     * <BR>
     */
    public Date expirationDate = null;

    /**
     * (���������敪)<BR>
     * <BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderCondType<BR>
     * 0: Def.DEFAULT<BR>
     * 1: Def.STOP_LIMIT_PRICE <BR>
     * 2: Def.W_LIMIT_PRICE<BR>
     * <BR
     */
    public String orderCondType;

    /**
     * (���������P��)<BR>
     * <BR>
     * ���������P��<BR>
     * <BR>
     * �����������敪���A"�t�w�l"�܂���"W�w�l"�̏ꍇ�A�Z�b�g����B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderCondPrice<BR>
     * Set if orderCondType = Def.STOP_LIMIT_PRICE or <BR>
     * Def.W_LIMIT_PRICE<BR>
     * <BR>
     */
    public String orderCondPrice = null;

    /**
     * (�����������Z�q)<BR>
     * <BR>
     * �����������Z�q<BR>
     * <BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * <BR>
     * �����������敪���A"�t�w�l"�܂���"W�w�l"�̏ꍇ�A�Z�b�g����B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * condOperator<BR>
     * <BR>
     * 1: Def.OVER<BR>
     * 2.: Def.UNDER<BR>
     * Set if orderCondType = Def.STOP_LIMIT_PRICE or <BR>
     * Def.W_LIMIT_PRICE<BR>
     * <BR>
     */
    public String condOperator = null;

    /**
     * (�v�w�l�p�����P���敪)<BR>
     * <BR>
     * 0�F���s�@@1�F�w�l<BR>
     * <BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * wLimitOrderPriceDiv<BR>
     * <BR>
     * 0: Def.MARKET_PRICE<BR>
     * 1: Def.LIMIT_PRICE<BR>
     * <BR>
     * Set if orderCondType is Def.W_LIMIT_PRICE<BR>
     * <BR>
     */
    public String wLimitOrderPriceDiv = null;

    /**
     * (�v�w�l�p�����P��)<BR>
     * <BR>
     * �v�w�l�p�����P���敪�u1�F�w�l�v�̏ꍇ�ݒ�<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * wlimitOrderCondPrice<BR>
     * <BR>
     * Set if wLimitOrderPriceDiv is 1: Def.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE<BR>
     * <BR>
     */
    public String wlimitOrderCondPrice = null;

    /**
     * (��萔��)<BR>
     * <BR>
     * ��萔��<BR>
     * <BR>
     * ����肪����ꍇ�̓Z�b�g�B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * execQuantity<BR>
     * <BR>
     * ��Set if execution<BR>
     * <BR>
     */
    public String execQuantity = null;

    /**
     * (���P��)<BR>
     * <BR>
     * ���P��<BR>
     * <BR>
     * ����肪����ꍇ�̓Z�b�g�B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * execPrice<BR>
     * <BR>
     * ��Set if execution<BR>
     * <BR>
     */
    public String execPrice = null;

    /**
     * (���҃R�[�h)<BR>
     * <BR>
     * ���҃R�[�h<BR>
     * <BR>
     * ���R�[���Z���^�[����̒����̏ꍇ�Z�b�g�B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * traderCode<BR>
     * <BR>
     * ��Set if an order from call center<BR>
     * <BR>
     */
    public String traderCode = null;

    /**
     * (����ԋ敪)<BR>
     * <BR>
     * ����ԋ敪<BR>
     * <BR>
     * 0�F�@@�����<BR>
     * 1�F�@@�ꕔ����<BR>
     * 2�F�@@�S������<BR>
     * 3�F�@@��菈����(�ꕔ����)<BR>
     * 4�F�@@��菈����(�S������)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * execType<BR>
     * 0: Def.EXEC_TYPE_NOT_PROMISE<BR>
     * 1: Def.EXEC_TYPE_ONE_COMPLETE<BR>
     * 2: Def.EXEC_TYPE_ALL_COMPLETE<BR>
     * 3: Def.EXEC_PROCESSING_ONE_COMPLETE<BR>
     * 4: Def.EXEC_PROCESSING_ALL_COMPLETE<BR>
     * <BR>
     */
    public String execType;

    /**
     * (��������敪)<BR>
     * <BR>
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
     * A�F�@@W�w�l�����ؑ֒�<BR>
     * B�F�@@W�w�l�����ꕔ�ؑ֊���<BR>
     * C�F�@@W�w�l�����S���ؑ֊���<BR>
     * D�F�@@W�w�l�����ؑ֎��s<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * changeCancelDiv<BR>
     * 0: Def.INITIAL_VALUE<BR>
     * 1: Def.CANCELING<BR>
     * 2: Def.PART_CANCELED<BR>
     * 3: Def.CANCELED<BR>
     * 4: Def.CANCEL_ERROR<BR>
     * 5: Def.CHANGING<BR>
     * 6: Def.PARTIALLY_CHANGED<BR>
     * 7: Def.CHANGED<BR>
     * 8: Def.CHANGE_ERROR<BR>
     * 9: Def.ERROR<BR>
     * <BR>
     */
    public String changeCancelDiv;

    /**
     * (�����o�H�敪)<BR>
     * <BR>
     * 1�F�R�[���Z���^�[�@@2�F�o�b�@@3:�X�����O�V���b�g�@@4�Fi-mode�@@5�FVodafone<BR>
     * 6�FAU�@@9�FHOST�@@ A�F�Ǘ��ҁ@@C�F���b�`�N���C�A���g�@@F�FIVR�i���������d�b�j<BR>
     * G�F��������<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderRootDiv<BR>
     * 1: Def.CALLCENTER<BR>
     * 2: Def.PC <BR>
     * 3: Def.SLINGSHOT<BR>
     * 4: Def.I_MODE<BR>
     * 5: Def.VODAFONE<BR>
     * 6: Def.AU<BR>
     * 9: Def.HOST<BR>
     * <BR>
     */
    public String orderRootDiv;

    /**
     * (��n��)<BR>
     * <BR>
     * ��n��<BR>
     * <BR>
     * deliveryDate<BR>
     * <BR>
     */
    public Date deliveryDate;

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
    public String sessionType = null;

    /**
     * @@roseuid 4212FBC50017
     */
    public WEB3AdminOROrderExecutionRefCommon()
    {

    }
}
@
