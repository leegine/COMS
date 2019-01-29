head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.42.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOROrderExecutionRefCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������Ɖ�ʃ��N�G�X�g (WEB3AdminOROrderExecutionRefCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/20 ���@@�_�O(���u) ���f���ύX�_092
Revesion History : 2007/02/14 ���@@�@@��(���u) ���f���ύX�_091	
Revesion History : 2007/06/05 �đo�g(���u) ���f���ύX�_099
Revesion History : 2007/07/01 �����F(���u) ���f���ύX�_101
Revesion History : 2008/10/02 ���V(SRA) �y�Ǘ��Ғ������Ɖ�z�d�l�ύX�Ǘ��䒠�i���f���jNo.130
*/
package webbroker3.adminorderexecinquiry.message;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.adminorderexecinquiry.define.WEB3AdminExecTypeDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminProductDivDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminTradingTypeDef;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.equity.define.WEB3EquityExecStatusTypeDef;

/**
 * (�Ǘ��ҁE�������Ɖ�ʃ��N�G�X�g)<BR>
 * <BR>
 * �Ǘ��ҁE�������Ɖ�ʃ��N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminOROrderExecutionRefCommonRequest<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public abstract class WEB3AdminOROrderExecutionRefCommonRequest extends WEB3GenRequest
{
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (����ID)<BR>
     * <BR>
     * ����ID<BR>
     * <BR>
     * orderId<BR>
     * <BR>
     */
    public String orderId = null;

    /**
     * (���X�R�[�h)<BR>
     * <BR>
     * ���X�R�[�h�̔z��<BR>
     * <BR>
     * �����X�R�[�h�����͎��́APR�w�ŕێ����Ă���<BR>
     * �@@�戵�\���X�R�[�h�ꗗ���Z�b�g�����B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * branchCode<BR>
     * <BR>
     * An array of branchCode<BR>
     * <BR>
     * ��The handling possible branchCodeList held by PR layer is set when branchCode
     * is not input.<BR>
     * <BR>
     */
    public String[] branchCode;

    /**
     * (������)<BR>
     * <BR>
     * ������<BR>
     * <BR>
     * orderBizDate<BR>
     * <BR>
     */
    public Date orderBizDate = null;

    /**
     * (�ڋq�R�[�h)<BR>
     * <BR>
     * �ڋq�R�[�h<BR>
     * <BR>
     * accountCode<BR>
     * <BR>
     */
    public String accountCode = null;
    
    /**
     * (���҃R�[�h(SONAR))<BR>
     * <BR>
     * �ڋq�̈��҃R�[�h(SONAR)<BR>
     * <BR>
     */
    public String sonarTraderCode = null;

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
     * 7�F�@@�O������<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * productDiv<BR>
     * <BR>
     * 0�F�@@Def.EQUITY<BR>
     * 1�F�@@Def.MARGIN<BR>
     * 2�F�@@Def.MINI_STOCK<BR>
     * 3�F�@@Def.OPTION<BR>
     * 4�F�@@Def.FUTURE<BR>
     * 5�F�@@Def.MF<BR>
     * 6�F�@@Def.RUITO<BR>
     * 7�F�@@Def.FEQ<BR>
     * <BR>
     */
    public String productDiv = null;

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
     * tradingType<BR>
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
     * 701�FDef.FEQ_BUY<BR>
     * 702�FDef.FEQ_SELL<BR>
     * <BR>
     */
    public String tradingType = null;

    /**
     * (���s����)<BR>
     * <BR>
     * ���s����<BR>
     * <BR>
     * 1�F�@@������<BR>
     * 3�F�@@��t<BR>
     * 4�F�@@����<BR>
     * 7�F�@@�s�o���������s<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * execCondType<BR>
     * <BR>
     * 1: Def.NO_CONDITION<BR>
     * 3: Def.AT_MARKET_OPEN<BR>
     * 4: Def.AT_MARKET_CLOSE<BR>
     * 7: Def.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * <BR>
     */
    public String execCondType = null;

    /**
     * (���������敪)<BR>
     * <BR>
     * ���������敪<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�o����܂Œ���<BR>
     * 3�F�@@�[��܂Œ���<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * expirationDateType<BR>
     * <BR>
     * 1: Def.DAY_LIMIT<BR>
     * 2: Def.CARRIED_ORDER<BR>
     * <BR>
     */
    public String expirationDateType = null;

    /**
     * (���������敪)<BR>
     * <BR>
     * ���������敪<BR>
     * <BR>
     * 0�F�@@�w��Ȃ�<BR>
     * 1�F�@@�t�w�l<BR>
     * 2�F�@@W�w�l<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderCondType<BR>
     * <BR>
     * 0: Def.DEFAULT<BR>
     * 1: Def.STOP_LIMIT_PRICE <BR>
     * 2: Def.W_LIMIT_PRICE<BR>
     * <BR>
     */
    public String orderCondType = null;

    /**
     * (�����o�H�敪)<BR>
     * <BR>
     * �����o�H�敪<BR>
     * <BR>
     * 1�F�@@�R�[���Z���^�[<BR>
     * 2�F�@@�o�b<BR>
     * 3�F�@@�X�����O�V���b�g<BR>
     * 4�F�@@i-mode<BR>
     * 5�F�@@Vodafone<BR>
     * 6�F�@@AU<BR>
     * 9�F�@@HOST<BR>
     * A�F�@@�Ǘ���<BR>
     * C�F�@@���b�`�N���C�A���g<BR>
     * F�F�@@IVR
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderRootDiv<BR>
     * <BR>
     * 1: Def.CALLCENTER<BR>
     * 2: Def.PC <BR>
     * 3: Def.SLINGSHOT<BR>
     * 4: Def.I_MODE<BR>
     * 5: Def.VODAFONE<BR>
     * 6: Def.AU<BR>
     * 9: Def.HOST<BR>
     * A: Def.ADMIN<BR>
     * <BR>
     */
    public String orderRootDiv = null;

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
    public String orderState = null;

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
    public String execType = null;

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
     * <BR
     */
    public String changeCancelDiv = null;

    /**
     * (��������From)<BR>
     * <BR>
     * ��������From<BR>
     * (YYYYMMDDhhmm)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderStartDate<BR>
     * (YYYYMMDDhhmm)<BR>
     * <BR>
     */
    public String orderStartDate = null;

    /**
     * (��������To)<BR>
     * <BR>
     * ��������To<BR>
     * (YYYYMMDDhhmm)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderEndDate<BR>
     * (YYYYMMDDhhmm)<BR>
     * <BR>
     */
    public String orderEndDate = null;

    /**
     * (��n��)<BR>
     * <BR>
     * ��n��<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * deliveryDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     */
    public String deliveryDate = null;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * <BR>
     * �v���y�[�W�ԍ�<BR>
     * <BR>
     * �\�����������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * pageIndex<BR>
     * <BR>
     * Specify a page position to be displayed . Let the first page index 1<BR>
     * <BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * <BR>
     * �y�[�W���\���s��<BR>
     * <BR>
     * �P�y�[�W���ɕ\�����������s�����w��<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * pageSize<BR>
     * <BR>
     * Specify the line number to be displayed in one page<BR>
     * <BR>
     */
    public String pageSize;

    /**
     * �i�\�[�g�L�[�j<BR>
     * <BR>
     */
    public WEB3AdminOROrderExecutionSortKeyUnit[] sortKeys;

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOROrderExecutionRefCommonRequest.class);

    /**
     * @@roseuid 4212FB230269
     */
    public WEB3AdminOROrderExecutionRefCommonRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j����ID�`�F�b�N<BR>
     * �@@this.����ID != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�|�P�jthis.����ID�������ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u����ID�������ȊO�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01476<BR>
     * <BR>
     * �Q�j���X�R�[�h�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.���X�R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.���X�R�[�h.length == 0�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h�̗v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01757<BR>
     * <BR>
     * �@@�Q�|�R�jthis.���X�R�[�h�̗v�f�����ȉ��̏������s���B<BR >
     * �@@�@@�Q�|�R�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h != ���� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h.length != 3 <BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * �R�j�ڋq�R�[�h�`�F�b�N<BR>
     * �@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�jthis.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�ڋq�R�[�h != ����<BR>
     * �@@�@@�@@�@@�E�ڋq�R�[�h.length != 6<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00780<BR>
     * <BR>
     * �S�j���҃R�[�h�iSONAR�j�`�F�b�N<BR>
     * �@@this.���҃R�[�h�iSONAR�j != null ���� this.���҃R�[�h�iSONAR�j.length > 5�̏ꍇ�A<BR>
     * �@@�u���҃R�[�h�iSONAR�j�G���[�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01912<BR>
     * <BR>
     * �T�j���i�敪�`�F�b�N<BR>
     * �@@this.���i�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�T�|�P�jthis.���i�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u���i�敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"��������"<BR>
     * �@@�@@�@@�@@�E"�M�p���"<BR>
     * �@@�@@�@@�@@�E"�����~�j����"<BR>
     * �@@�@@�@@�@@�E"�I�v�V����"<BR>
     * �@@�@@�@@�@@�E"�敨"<BR>
     * �@@�@@�@@�@@�E"���M"<BR>
     * �@@�@@�@@�@@�E"�ݓ�"<BR>
     * �@@�@@�@@�@@�E"�O������"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01068<BR>
     * <BR>
     * �U�j����敪�`�F�b�N<BR>
     * �@@this.����敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�U�|�P�jthis.����敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u����敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"�������t����"<BR>
     * �@@�@@�@@�@@�E"�������t����"<BR>
     * �@@�@@�@@�@@�E"�V�K��������"<BR>
     * �@@�@@�@@�@@�E"�V�K��������"<BR>
     * �@@�@@�@@�@@�E"�����ԍϒ���"<BR>
     * �@@�@@�@@�@@�E"�����ԍϒ���"<BR>
     * �@@�@@�@@�@@�E"��������"<BR>
     * �@@�@@�@@�@@�E"���n����"<BR>
     * �@@�@@�@@�@@�E"����O����"<BR>
     * �@@�@@�@@�@@�E"�~�j�����t����"<BR>
     * �@@�@@�@@�@@�E"�~�j�����t����"<BR>
     * �@@�@@�@@�@@�E"�����M�����t����"<BR>
     * �@@�@@�@@�@@�E"�����M�����t����"<BR>
     * �@@�@@�@@�@@�E"�����M����W����"<BR>
     * �@@�@@�@@�@@�E"�����M���抷����"<BR>    
     * �@@�@@�@@�@@�E"�ݓ����t����"<BR>
     * �@@�@@�@@�@@�E"�ݓ����t����"<BR>
     * �@@�@@�@@�@@�E"�w���敨�V�K��������"<BR>
     * �@@�@@�@@�@@�E"�w���敨�V�K��������"<BR>
     * �@@�@@�@@�@@�E"�w���敨�����ԍϒ���"<BR>
     * �@@�@@�@@�@@�E"�w���敨�����ԍϒ���"<BR>
     * �@@�@@�@@�@@�E"�w���I�v�V�����V�K��������"<BR>
     * �@@�@@�@@�@@�E"�w���I�v�V�����V�K��������"<BR>
     * �@@�@@�@@�@@�E"�w���I�v�V���������ԍϒ���"<BR>
     * �@@�@@�@@�@@�E"�w���I�v�V���������ԍϒ���"<BR>
     * �@@�@@�@@�@@�E"�O���������t"<BR>
     * �@@�@@�@@�@@�E"�O���������t"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00602<BR>
     * <BR>
     * �V�j���s�����`�F�b�N<BR>
     * �@@this.���s���� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�V�|�P�jthis.���s�����ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u���s����������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"������"<BR>
     * �@@�@@�@@�@@�E"��t"<BR>
     * �@@�@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�@@�E"�s�o���������s"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00127<BR>
     * <BR>
     * �W�j���������敪�`�F�b�N<BR>
     * �@@this.���������敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s��<BR>
     * �@@�W�|�P�jthis.���������敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u���������敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"��������"<BR>
     * �@@�@@�@@�@@�E"�o����܂Œ���"<BR>
     * �@@�@@�@@�@@�E"�[��܂Œ���"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00209<BR>
     * <BR>
     * �X�j���������敪�`�F�b�N<BR>
     * �@@this.���������敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s��<BR>
     * �@@�X�|�P�jthis.���������敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u���������敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"�w��Ȃ�"<BR>
     * �@@�@@�@@�@@�E"�t�w�l"<BR>
     * �@@�@@�@@�@@�E"W�w�l"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00212<BR>
     * <BR>
     * �P�O�j�����o�H�敪�`�F�b�N<BR>
     * �@@this.�����o�H�敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s��<BR>
     * �@@�P�O�|�P�jthis.�����o�H�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u�����o�H�敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"�R�[���Z���^�["<BR>
     * �@@�@@�@@�@@�E"�o�b"<BR>
     * �@@�@@�@@�@@�E"�X�����O�V���b�g"<BR>
     * �@@�@@�@@�@@�E"i-mode"<BR>
     * �@@�@@�@@�@@�E"Vodafone"<BR>
     * �@@�@@�@@�@@�E"AU"<BR>
     * �@@�@@�@@�@@�E"HOST"<BR>
     * �@@�@@�@@�@@�E"�Ǘ���"<BR>
     * �@@�@@�@@�@@�E"���b�`�N���C�A���g"<BR>
     * �@@�@@�@@�@@�E"IVR"<BR>
     * �@@�@@�@@�@@�E"��������"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01472<BR>
     * <BR>
     * �P�P�j������ԋ敪�`�F�b�N<BR>
     * �@@this.������ԋ敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s��<BR>
     * �@@�P�P�|�P�jthis.������ԋ敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u������ԋ敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"���̑�"<BR>
     * �@@�@@�@@�@@�E"��t�ρi�V�K�����j" <BR>
     * �@@�@@�@@�@@�E"�������i�V�K�����j" <BR>
     * �@@�@@�@@�@@�E"�����ρi�V�K�����j" <BR>
     * �@@�@@�@@�@@�E"�������s�i�V�K�����j" <BR>
     * �@@�@@�@@�@@�E"��t�ρi�ύX�����j"<BR>
     * �@@�@@�@@�@@�E"�������i�ύX�����j"<BR>
     * �@@�@@�@@�@@�E"�����ρi�ύX�����j"<BR>
     * �@@�@@�@@�@@�E"�������s�i�ύX�����j"<BR>
     * �@@�@@�@@�@@�E"��t�ρi��������j"<BR>
     * �@@�@@�@@�@@�E"�������i��������j"<BR>
     * �@@�@@�@@�@@�E"�����ρi��������j"<BR>
     * �@@�@@�@@�@@�E"�������s�i��������j"<BR>
     * �@@�@@�@@�@@�E"�ꕔ����"<BR>
     * �@@�@@�@@�@@�E"�S������"<BR>
     * �@@�@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�@@�E"�蓮����"<BR>
     * �@@�@@�@@�@@�E"�J�z��"<BR>
     * �@@�@@�@@�@@�E"�J�z���s"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01477<BR>
     * <BR>
     * �P�Q�j����ԋ敪�`�F�b�N<BR>
     * �@@this.����ԋ敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s��<BR>
     * �@@�P�Q�|�P�jthis.����ԋ敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u����ԋ敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"�����"<BR>
     * �@@�@@�@@�@@�E"�ꕔ����"<BR>
     * �@@�@@�@@�@@�E"�S������"<BR>
     * �@@�@@�@@�@@�E"��菈����(�ꕔ����)"<BR> 
     * �@@�@@�@@�@@�E"��菈����(�S������)"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00626<BR>
     * <BR>
     * �P�R�j��������敪�`�F�b�N<BR>
     * �@@this.��������敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s��<BR>
     * �@@�P�R�|�P�jthis.��������敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u��������敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"�����l"<BR>
     * �@@�@@�@@�@@�E"�����"<BR>
     * �@@�@@�@@�@@�E"�ꕔ�������"<BR>
     * �@@�@@�@@�@@�E"�S���������"<BR>
     * �@@�@@�@@�@@�E"������s"<BR>
     * �@@�@@�@@�@@�E"������"<BR>
     * �@@�@@�@@�@@�E"�ꕔ��������"<BR>
     * �@@�@@�@@�@@�E"�S����������"<BR>
     * �@@�@@�@@�@@�E"�������s"<BR>
     * �@@�@@�@@�@@�E"�G���["<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01478<BR>
     * <BR>
     * �P�S�j��������From�`�F�b�N<BR>
     * �@@this.��������From != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�S�|�P�jthis.��������From��Date�^�ɕϊ��ł��Ȃ������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͎��ԃG���[(��������From)�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01479<BR>
     * <BR>
     * �P�T�j��������To�`�F�b�N<BR>
     * �@@this.��������To != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�T�|�P�jthis.��������To��Date�^�ɕϊ��ł��Ȃ������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͎��ԃG���[(��������To)�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01480<BR>
     * <BR>
     * �P�U�j��������From/To�������`�F�b�N<BR>
     * �@@this.��������From != null ����
     * this.��������To�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�U�|�P�jthis.��������From > this.��������To�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͎��Ԑ������G���[)�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01481<BR>
     * <BR>
     * �P�V�j��n���`�F�b�N<BR>
     * �@@this.��n�� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�V�|�P�jthis.��n����Date�^�ɕϊ��ł��Ȃ������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͎��ԃG���[(��n��)�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01482<BR>
     * <BR>
     * �P�W�j�@@�\�[�g�L�[�`�F�b�N <BR>
     * �@@�P�W�|�P�jthis.�\�[�g�L�[ == null�ł������ꍇ <BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�P�W�|�Q�jthis.�\�[�g�L�[.length == 0�������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�P�W�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂��� <BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���s���B<BR>
     * �@@�@@�P�W�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �P�X�j�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�P�X�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�P�X�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�P�X�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00616<BR>
     * <BR>
     * �Q�O�j�y�[�W���\���s���`�F�b�N<BR>
     * �@@�Q�O�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00091<BR>
     * <BR>
     * �@@�Q�O�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B<BR>
     * �@@<BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@�Q�O�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00617<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * 1)orderId check<BR>
     * �@@Check the following if this.orderId != null<BR>
     * �@@1-1)If this.orderId != numerical numeber<BR>
     * �@@�@@�@@�@@�@@Throw the exception "orderId is not a numerical value"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01476<BR>
     * <BR>
     * 2�jbranchCode check<BR>
     * �@@2-1)If this.branchCode== null<BR>
     * �@@�@@�@@�@@�@@Throw the exception "branchCode is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * �@@2-2)If this.branchCode.length == 0,<BR>
     * �@@�@@�@@�@@�@@Throw the exception "The number of the elements of branchCode is
     * 0"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01757<BR>
     * <BR>
     * �@@2-3)Loop for as many times as the number of the elements of
     * this.branchCode<BR>
     * �@@�@@2-3-1)If this.branchCode meets with the following conditions<BR>
     * �@@�@@�@@�@@�@@�@@Throw the exception "branchCode error"<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.branchCode != numerical number<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.branchCode.length != 3<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * 3)accountCode check<BR>
     * �@@Check the followings if this.accountCode != null<BR>
     * �@@3-1)If this.accountCode meets with the following conditions<BR>
     * �@@�@@�@@�@@�@@�@@Throw the exception "accountCode error"<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.accountCode != numerical value<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.accountCode.length != 6<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00780<BR>
     * <BR>
     * 4)productDiv check<BR>
     * �@@Check the followings if this.productDiv != null<BR>
     * �@@4-1)If this.productDiv contains values other than the followings<BR>
     * �@@�@@�@@�@@�@@Throw the exception "productDiv has an undefined value"<BR>
     * �@@�@@�@@�@@�E Def.EQUITY<BR>
     * �@@�@@�@@�@@�E Def.MARGIN<BR>
     *         �E Def.MINI_STOCK<BR>
     *         �E Def.OPTION<BR>
     *         �E Def.FUTURE<BR>
     *         �E Def.MF<BR>
     *         �E Def.RUITO<BR>
     *         �E Def.FEQ<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01068<BR>
     * <BR>
     * 5)tradingType check<BR>
     * �@@Check the followings if this.tradingType!= null<BR>
     * �@@5-1)If this.tradingType contains values other than the followings<BR>
     * �@@�@@�@@�@@�@@Throw the exception "tradingType has an undefined value"<BR>
     * �@@�@@�@@�@@�EDef.EQUITY_BUY<BR>
     * �@@�@@�@@�@@�EDef.EQUITY_SELL<BR>
     * �@@�@@�@@�@@�EDef.MARGIN_LONG<BR>
     * �@@�@@�@@�@@�EDef.MARGIN_SHORT<BR>
     * �@@�@@�@@�@@�EDef.CLOSE_MARGIN_LONG<BR>
     * �@@�@@�@@�@@�EDef.CLOSE_MARGIN_SHORT<BR>
     * �@@�@@�@@�@@�EDef.SWAP_MARGIN_LONG<BR>
     * �@@�@@�@@�@@�EDef.SWAP_MARGIN_SHORT<BR>
     * �@@�@@�@@�@@�EDef.SALES_OUTSIDE_MARKET<BR>
     * �@@�@@�@@�@@�EDef.MINI_STOCK_BUY<BR>
     * �@@�@@�@@�@@�EDef.MINI_STOCK_SELL<BR>
     * �@@�@@�@@�@@�EDef.MF_BUY<BR>
     * �@@�@@�@@�@@�EDef.MF_SELL<BR>
     * �@@�@@�@@�@@�EDef.RUITO_BUY<BR>
     * �@@�@@�@@�@@�EDef.RUITO_SELL<BR>
     * �@@�@@�@@�@@�EDef.IDX_FUTURES_BUY_TO_OPEN<BR>
     * �@@�@@�@@�@@�EDef.IDX_FUTURES_SELL_TO_OPEN<BR>
     * �@@�@@�@@�@@�EDef.IDX_FUTURES_BUY_TO_CLOSE<BR>
     * �@@�@@�@@�@@�EDef.IDX_FUTURES_SELL_TO_CLOSE<BR>
     * �@@�@@�@@�@@�EDef.IDX_OPTIONS_BUY_TO_OPEN<BR>
     * �@@�@@�@@�@@�EDef.IDX_OPTIONS_SELL_TO_OPEN<BR>
     * �@@�@@�@@�@@�EDef.IDX_OPTIONS_BUY_TO_CLOSE<BR>
     * �@@�@@�@@�@@�EDef.IDX_OPTIONS_SELL_TO_CLOSE<BR>
     * �@@�@@�@@�@@�EDef.FEQ_BUY<BR>
     * �@@�@@�@@�@@�EDef.FEQ_SELL<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00602<BR>
     * <BR>
     * 6)execCondType check<BR>
     * �@@Check the followings if this.execCondType!= null<BR>
     * �@@6-1)If this.execCondType contains values other than the followings<BR>
     * �@@�@@�@@�@@�@@Throw the exception "execCondType has an undefined value"<BR>
     * �@@�@@�@@  �E Def.NO_CONDITION<BR>
     * �@@      �E Def.AT_MARKET_OPEN<BR>
     * �@@      �E Def.AT_MARKET_CLOSE<BR>
     * �@@      �E Def.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00127<BR>
     * <BR>
     * 7)expirationDateType check<BR>
     * �@@Check the followings if this.expirationDateType!= null<BR>
     * �@@7-1)If this.expirationDate contains values other than the followings<BR>
     * �@@�@@�@@�@@Throw the excetion "expirationDateType has an undefined value"<BR>
     * �@@�@@�@@ �EDef.DAY_LIMIT<BR>
     *        �EDef.CARRIED_ORDER<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00209<BR>
     * <BR>
     * 8)orderCondType check<BR>
     * �@@�@@Check the followings if this.orderCondType!= null<BR>
     * �@@8-1)If this.orderCondType contains values other than the followings,<BR>
     * �@@�@@�@@�@@Throw the excetion "orderCondType has an undefined value"<BR>
     * �@@�@@�@@ �EDef.DEFAULT<BR>
     *        �EDef.STOP_LIMIT_PRICE <BR>
     *        �EDef.W_LIMIT_PRICE<BR>>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00212<BR>
     * <BR>
     * 9)orderRootDiv check<BR>
     * �@@Check the followings if this.orderRootDiv!= null<BR>
     * �@@9-1)If this.orderRootDiv contains values other than the followings<BR>
     * �@@�@@�@@�@@Throw the exception "orderRootDiv has an undefined value"<BR>
     *        �E Def.CALLCENTER<BR>
     *        �E Def.PC <BR>
     *        �E Def.SLINGSHOT<BR>
     *        �E Def.I_MODE<BR>
     *        �E Def.VODAFONE<BR>
     *        �E Def.AU<BR>
     *        �E Def.HOST<BR>
     *        �E Def.ADMIN<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01472<BR>
     * <BR>
     * 10)orderState check<BR>
     * �@@Check the followings if this.orderState!= null<BR>
     * �@@10-1)If this.orderState contains values other than the followings<BR>
     * Throw the excetion "orderState has an undefined value"<BR>
     * �@@�@@�@@ �EDef.UNDEFINED<BR>
     *         �EDef.ACCEPTED<BR>
     *         �EDef.ORDERING<BR>
     *         �EDef.ORDERED<BR>
     *         �EDef.NOT_ORDERED<BR>
     *         �EDef.MODIFY_ACCEPTED<BR>
     *         �EDef.MODIFYING<BR>
     *         �EDef.MODIFIED<BR>
     *         �EDef.NOT_MODIFIED<BR>
     *         �EDef.CANCEL_ACCEPTED<BR>
     *         �EDef.CANCELLING<BR>
     *         �EDef.CANCELLED<BR>
     *         �EDef.NOT_CANCELLED<BR>
     *         �EDef.PART_INAFFECTED<BR>
     *         �EDef.FULL_INAFFECTED<BR>
     *         �EDef.CLOSED<BR>
     *         �EDef.TRANSFERED<BR>
     *         �EDef.NOT_TRANSFERED<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01477<BR>
     * <BR>
     * 11)execType check<BR>
     * �@@Check the followings if this.execType!= null<BR>
     * �@@11-1)If this.execType contains values other than the followings<BR>
     * Throw the exception "execType has an undefined value"<BR>
     * �@@�@@�@@�@@�EDef.EXEC_TYPE_NOT_PROMISE<BR>
     *         �EDef.EXEC_TYPE_ONE_COMPLETE<BR>
     *         �EDef.EXEC_TYPE_ALL_COMPLETE<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00626<BR>
     * <BR>
     * 12)changeCancelDiv check<BR>
     * �@@Check the followings if this.changeCancelDiv!= null<BR>
     * �@@12-1)If this.changeCancelDivis contains values other than the followings<BR>
     * Throw the exception "changeCancelDiv has an undefined value"<BR>
     * �@@�@@�@@�@@�EDef.INITIAL_VALUE<BR>
     *         �EDef.CANCELING<BR>
     *         �EDef.PART_CANCELED<BR>
     *         �EDef.CANCELED<BR>
     *         �EDef.CANCEL_ERROR <BR>
     *         �EDef.CHANGING<BR>
     *         �EDef.PARTIALLY_CHANGED<BR>
     *         �EDef.CHANGE_ERROR<BR>
     *         �EDef.ERROR<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01478<BR>
     * <BR>
     * �P3)orderStartDate check<BR>
     * �@@Cehck the followings if this.orderStartDate !=null<BR>
     * �@@13-1)If it is unable to convert this.orderStartDate to Date type<BR>
     * �@@�@@�@@�@@�@@Throw the excetion "invalid input date(orderStartDate)"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01479<BR>
     * <BR>
     * 14)orderEndDate check<BR>
     * �@@Check the followng if this.orderEndDate != null<BR>
     * �@@14-1)If it is unable to convert this.orderEndDate to Date type,<BR>
     * �@@�@@�@@�@@�@@Throw the exception "invalid input date(orderEndDate)"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01480<BR>
     * <BR>
     * 15)orderStartDate / orderEndDate integrity check<BR>
     * �@@Check the following if this.orderStartDate != null and this.orderEndDateTo !=
     * null,<BR>
     * �@@15-1)If this.orderStartDate > this.orderEndDateTo<BR>
     * �@@�@@�@@�@@�@@Throw the excetion "input date integrity error"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01481<BR>
     * <BR>
     * 16)deliveryDate check <BR>
     * �@@Check the following if this.deliveryDate != null<BR>
     * �@@16-1)If it is unable to convert this.deliveryDate to Date type<BR>
     * �@@�@@�@@�@@�@@Throw the exception "invalid input date(deliveryDate)"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01482<BR>
     * <BR>
     * 17)sortKey Check <BR>
     * �@@17-1)If this.sortKeys == null<BR>
     * �@@�@@�@@�@@Throw the exception "sortKeys is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@17-2)If this.sortKeys.length == null<BR>
     * �@@�@@�@@�@@Throw the exception "The number of the elements of sortKeys.length is
     * 0"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@17-3)Check the following for all the this.sortKeys elements<BR>
     * �@@�@@17-3-1)Call sortKeys.Validate()<BR>
     * <BR>
     * 18)pageIndex check <BR>
     * �@@18-1)If this.pageIndex == null<BR>
     * �@@�@@�@@�@@Throw the exception "pageIndex is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@18-2)If this.pageIndex is not a numerical value,<BR>
     * �@@�@@�@@�@@Throw the exception "pageIndex is not a numerical value"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@18-3)If this.pageIndex <= 0<BR>
     * �@@�@@�@@�@@Throw the exception "pageIndex is less than 0"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00616<BR>
     * <BR>
     * 19)pageSize check <BR>
     * �@@19-1)If this.pageSize == null<BR>
     * �@@�@@�@@�@@Throw the exception "pageSize is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00091<BR>
     * <BR>
     * �@@19-2)If this.pageSize is not a numerical value<BR>
     * �@@�@@�@@�@@Throw the exception "pageSize is not a numerical value"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@19-3)If this.pageSize <= 0<BR>
     * �@@�@@�@@�@@Throw the exception "pageSize is less than 0"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41A5B3CB0168
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        final int l_intSix = 6;
        final int l_intFive = 5;
        final int l_intThree = 3;
        int l_intSortKeyLength = 0;
        int l_intBranchCodeLength = 0;

        // 1-1 if orderId != null ,then if orderId in not number throw exception
        if (this.orderId != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.orderId))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01476,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-1 If branchCode== null throw exception
        if (this.branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 2-2 If branchCode.length = 0, throw exception
            l_intBranchCodeLength = branchCode.length;
            if (l_intBranchCodeLength == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                // 2-3 If branchCode is Not a Number & its length not equal to 3, throw Exception
                for (int i = 0; i < l_intBranchCodeLength; i++)
                {
                    if ((!WEB3StringTypeUtility.isNumber(this.branchCode[i]))
                        || (WEB3StringTypeUtility.getByteLength(this.branchCode[i]) != l_intThree))
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
                    }
                }
            }
        }

        // 3-1 If accountCode is Not a Number & its length not equal to 6, throw Exception
        if (this.accountCode != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(this.accountCode))
                || (WEB3StringTypeUtility.getByteLength(this.accountCode) != l_intSix))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 4-1 If sonarTraderCode's length is over than 5, throw Exception
        if (this.sonarTraderCode != null)
        {
            if ((WEB3StringTypeUtility.getByteLength(this.sonarTraderCode) > l_intFive))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01912,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 5-1 if productDiv is not of any Def values throw Exception
        if (this.productDiv != null)
        {
            if ((!WEB3AdminProductDivDef.EQUITY.equals(this.productDiv))
                && (!WEB3AdminProductDivDef.MARGIN.equals(this.productDiv))
                && (!WEB3AdminProductDivDef.MINI_STOCK.equals(this.productDiv))
                && (!WEB3AdminProductDivDef.OPTION.equals(this.productDiv))
                && (!WEB3AdminProductDivDef.FUTURE.equals(this.productDiv))
                && (!WEB3AdminProductDivDef.MF.equals(this.productDiv))
                && (!WEB3AdminProductDivDef.RUITO.equals(this.productDiv))
                && (!WEB3AdminProductDivDef.FEQ.equals(this.productDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 6-1 If tradingType is not of any of Def values, throw an Exception.
        if (this.tradingType != null)
        {
            if ((!String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.SWAP_MARGIN_LONG.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue()).equals(this.tradingType))
                && (!WEB3AdminTradingTypeDef.SALES_OUTSIDE_MARKET.equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MINI_STOCK_BUY.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MINI_STOCK_SELL.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MF_BUY.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MF_SELL.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MF_RECRUIT.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.MF_SWITCHING.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.RUITO_BUY.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.RUITO_SELL.intValue()).equals(this.tradingType))                
                && (!String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.FEQ_BUY.intValue()).equals(this.tradingType))
                && (!String.valueOf(OrderTypeEnum.FEQ_SELL.intValue()).equals(this.tradingType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00602,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 7-1 if execCondType is not any of Def values, throw Exception.
        if (this.execCondType != null)
        {
            if ((!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
                && (!WEB3ExecutionConditionDef.AT_MARKET_OPEN.equals(this.execCondType))
                && (!WEB3ExecutionConditionDef.AT_MARKET_CLOSE.equals(this.execCondType))
                && (!WEB3ExecutionConditionDef
                    .AT_MARKET_CLOSE_NOT_EXECUTED
                    .equals(this.execCondType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00127,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 8-1 if expirationDateType is not any of Def values throw Exception.
        if (this.expirationDateType != null)
        {
            if ((!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType))
                && (!WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType))
                && (!WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(this.expirationDateType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00209,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 9-1 if orderCondType is not any of Def values, throw Exception.
        if (this.orderCondType != null)
        {
            if ((!WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
                && (!WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType))
                && (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00212,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 10-1 if orderRootDiv is not any of Def values, throw Exception.
        if (this.orderRootDiv != null)
        {
            if ((!WEB3OrderRootDivDef.CALLCENTER.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.PC.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.SLINGSHOT.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.I_MODE.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.VODAFONE.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.AU.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.HOST.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.ADMIN.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.RICH_CLIENT.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.IVR.equals(this.orderRootDiv))
                && (!WEB3OrderRootDivDef.FORCED_SETTLE.equals(this.orderRootDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01472,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 11-1 if orderState is not any of Def values throw Exception.
        if (this.orderState != null)
        {
            if ((!WEB3OrderStatusDef.OTHER.equals(this.orderState))
                && (!WEB3OrderStatusDef.ACCEPTED_OPENORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFYING.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFYED.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFY_FAIL_OPENORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.ACCEPTED_CHANGEORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFYING_CHANGEORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFYED_CHANGEORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFY_FAIL_CHANGEORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.ACCEPTED_CANCELORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFYING_CANCELORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFYED_CANCELORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.MODIFY_FAIL_CANCELORDER.equals(this.orderState))
                && (!WEB3OrderStatusDef.PART_INAFFECTED.equals(this.orderState))
                && (!WEB3OrderStatusDef.FULL_INAFFECTED.equals(this.orderState))
                && (!WEB3OrderStatusDef.CLOSED.equals(this.orderState))
                && (!WEB3OrderStatusDef.MANUAL_EXPIRED.equals(this.orderState))
                && (!WEB3OrderStatusDef.TRANSFERED.equals(this.orderState))
                && (!WEB3OrderStatusDef.NOT_TRANSFERED.equals(this.orderState)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01477,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 12-1 if execType is not any of Def values throw Exception.
        if (this.execType != null)
        {
            if ((!WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE.equals(this.execType))
                && (!WEB3EquityExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(this.execType))
                && (!WEB3EquityExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(this.execType))
                && (!WEB3AdminExecTypeDef.EXEC_PROCESSING_ONE_COMPLETE.equals(this.execType))
                && (!WEB3AdminExecTypeDef.EXEC_PROCESSING_ALL_COMPLETE.equals(this.execType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00626,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 13-1 if  changeCancelDiv is not any of Def values throw Exception.
        if (this.changeCancelDiv != null)
        {
            if ((!WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.CANCELING.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.PART_CANCELED.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.CANCELED.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.CANCEL_ERROR.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.CHANGING.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.CHANGED.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.CHANGE_ERROR.equals(this.changeCancelDiv))
                && (!WEB3ModifyCancelTypeDef.ERROR.equals(this.changeCancelDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01478,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 14-1 If orderStartDate is unable to change to Date type throw exception
        if (this.orderStartDate != null)
        {
            if (!WEB3StringTypeUtility.isDateStr(this.orderStartDate, "yyyyMMddHHmm"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01479,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 15-1 If orderEndDate is unable to change  to Date type throw exception
        if (this.orderEndDate != null)
        {
            if (!WEB3StringTypeUtility.isDateStr(this.orderEndDate, "yyyyMMddHHmm"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01480,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 16-1 if applyStartDate > applyEndDate throw exception
        if ((orderStartDate != null) && (orderEndDate != null))
        {
            Date l_oredrStartDate = WEB3DateUtility.getDate(this.orderStartDate, "yyyyMMddHHmm");
            Date l_orderEndDate = WEB3DateUtility.getDate(this.orderEndDate, "yyyyMMddHHmm");

            int l_resultcompare = WEB3DateUtility.compare(l_oredrStartDate, l_orderEndDate);
            if (l_resultcompare == 1)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01481,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 17-1 If deliveryDate is unable to change  to Date type throw exception
        if (this.deliveryDate != null)
        {
            if (!WEB3StringTypeUtility.isDateStr(this.deliveryDate, "yyyyMMdd"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01482,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 18-1 if sortKeys is  null throw exception.
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            l_intSortKeyLength = sortKeys.length;
            if (l_intSortKeyLength == 0)
            {
                // 18-2 If this.sortKeys.length == null
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                // 18-3 Check the following for all the this.sortKeys elements
                for (int i = 0; i < l_intSortKeyLength; i++)
                {
                    sortKeys[i].validate();
                }
            }
        }

        // 19-1 if pageIndex  is not null throw exception.
        if (this.pageIndex == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 19-2 If this.pageIndex is not a numerical value, throw Exception.
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 19-3 If this.pageIndex <= 0, throw Exception.
        int l_intPageIndex = Integer.parseInt(this.pageIndex);
        if (l_intPageIndex <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 20-1 If this.pageSize = null, throw Exception.
        if (this.pageSize == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 20-2 If this.pageSize is not a numerical value, throw Exception.
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 20-3 If this.pageSize <= 0, throw Exception.
        int l_intPageSize = Integer.parseInt(pageSize);
        if (l_intPageSize <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public abstract WEB3GenResponse createResponse();
}
@
