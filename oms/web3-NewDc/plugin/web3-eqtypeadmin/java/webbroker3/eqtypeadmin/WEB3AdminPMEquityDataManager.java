head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMEquityDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���i�Ǘ��i�����j�f�[�^�}�l�[�W��(WEB3AdminPMEquityDataManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30�@@�юu��(���u) �d�l�ύX ���f��106
Revesion History : 2007/04/23�@@�đo�g(���u) �d�l�ύX ���f��130�A135�A137
Revesion History : 2007/04/28�@@���G��(���u) �d�l�ύX ���f��143
Revesion History : 2007/06/04�@@�đo�g(���u) �d�l�ύX ���f��150
Revesion History : 2007/07/24�@@�����q(���u) �d�l�ύX ���f��159
Revesion History : 2007/08/07�@@���n�m(���u) �d�l�ύX ���f��160
Revesion History : 2007/08/09�@@�����q(���u) �d�l�ύX ���f��161
Revesion History : 2007/08/27  �đo�g(���u) �d�l�ύX ���f��163
Revesion History : 2007/09/11  �đo�g(���u) �d�l�ύX ���f��164
Revesion History : 2007/10/10�@@�����q(���u) �d�l�ύX ���f��166
Revesion History : 2008/01/18�@@�����F(���u) �d�l�ύX ���f��181�A182�A185
Revesion History : 2008/01/25�@@�g�E�N�|(���u) �d�l�ύX ���f��184�A187�A189
Revesion History : 2008/02/13�@@�g�E�N�|(���u) �d�l�ύX ���f��196
Revesion History : 2008/02/27�@@��іQ(���u) �d�l�ύX ���f��200
*/
package webbroker3.eqtypeadmin;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ApproveStatusType;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ForcedSettleReasonType;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceRangeIdDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.eqtypeadmin.data.EqtypeProductConditionParams;
import webbroker3.eqtypeadmin.data.EqtypeProductConditionRow;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityDeleteFlgDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityForcedSettleReasonDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityLastUpdaterDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySmallItemDivDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySortKeyItemNameDef;
import webbroker3.eqtypeadmin.define.WEB3AdminPriceRangeValueDef;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSExecHistory;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSOrderDetailUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReasonUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleSortKeyUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleTemporaryOrderUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopInfoUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccTradeStopInfoUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminPMUpdateInfo;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations;
import webbroker3.equity.data.EquityLimitPriceRangeMstRow;
import webbroker3.equity.data.EquityTickValuesMstRow;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.data.HostEqtypeSwapRow;
import webbroker3.equity.data.HostEquityOrderExecNotifyRow;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AccountProductOrderStopParams;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.PtsOrderexecutionEndRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i���i�Ǘ��i�����j�f�[�^�}�l�[�W���j<BR>
 * <BR>
 * ���i�Ǘ��i�����j�f�[�^�}�l�[�W��<BR>
 * ���i�Ǘ�(����)�̃f�[�^�쐬�Ȃǂ��Ǘ�����N���X<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminPMEquityDataManager<BR>
 * WEB3AdminPMEquityDataManager class.<BR>
 * <BR>
 * @@author Saravanan Krishnamoorthy
 * @@version 1.0
 */
public class WEB3AdminPMEquityDataManager
{
    /** Log Variable.<BR> */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMEquityDataManager.class);

    /**
     * @@roseuid 41FDBE3C0109
     */
    public WEB3AdminPMEquityDataManager()
    {
    }

    /**
     * �iget��������o�^�l�j<BR>
     * �����̏����ڋ敪�ɑΉ������������̍��ڂ�ԋp����B<BR>
     * ���������������ݒ�ɂĎg�p�B<BR>
     * <BR>
     * �P�j�p�����[�^.�����ڋ敪�ɑΉ������������̍��ڂ�<BR>
     * �@@�ԋp����B�@@<BR>
     * �@@����������̊e���ڂ́A�S�ĕ�����ϊ����ĕԋp���邱�ƁB<BR>
     * <BR>
     * �@@�p�����[�^.�����ڋ敪���A<BR>
     * �@@// ����K��<BR>
     * �@@["��������~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.��������~��ԋp����B<BR>
     * <BR>
     * �@@["��������~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.��������~��ԋp����B<BR>
     * <BR>
     * �@@["�����x�M�p��~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����x�M�p��~��ԋp����B<BR>
     * <BR>
     * �@@["�����x�M�p��~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����x�M�p��~��ԋp����B<BR>
     * <BR>
     * �@@["�����ԍϐ��x�M�p��~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����ԍ�(���ԍ�)���x�M�p��~��ԋp����B<BR>
     * <BR>
     * �@@["�����ԍϐ��x�M�p��~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����ԍ�(���ԍ�)���x�M�p��~��ԋp����B<BR>
     * <BR>
     * �@@["�������x�M�p��~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�������x�M�p��~��ԋp����B<BR>
     * <BR>
     * �@@["���n���x�M�p��~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.���n���x�M�p��~��ԋp����B<BR>
     * <BR>
     * �@@["����ʐM�p��~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.����ʐM�p��~��ԋp����B<BR>
     * <BR>
     * �@@["����ʐM�p��~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.����ʐM�p��~��ԋp����B<BR>
     * <BR>
     * �@@["�����ԍψ�ʐM�p��~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����ԍ�(���ԍ�)��ʐM�p��~��ԋp����B<BR>
     * <BR>
     * �@@["�����ԍψ�ʐM�p��~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����ԍ�(���ԍ�)��ʐM�p��~��ԋp����B<BR>
     * <BR>
     * �@@["������ʐM�p��~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.������ʐM�p��~��ԋp����B<BR>
     * <BR>
     * �@@["���n��ʐM�p��~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.���n��ʐM�p��~��ԋp����B<BR>
     * <BR>
     * �@@["��������~(���s)"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.���������s�w���~��ԋp����B<BR>
     * <BR>
     * �@@["��������~(���s)"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.���������s�w���~��ԋp����B<BR>
     * <BR>
     * �@@["�����x�M�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����x�M�p���s�w���~��ԋp����B<BR>
     * <BR>
     * �@@["�����x�M�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����x�M�p���s�w���~��ԋp����B<BR>
     * <BR>
     * �@@["�����ԍϐ��x�M�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����ԍ�(���ԍ�)���x�M�p���s�w���~��ԋp����B<BR>
     * <BR>
     * �@@["�����ԍϐ��x�M�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����ԍ�(���ԍ�)���x�M�p���s�w���~��ԋp����B<BR>
     * <BR>
     * �@@["����ʐM�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.����ʐM�p���s�w���~��ԋp����B<BR>
     * <BR>
     * �@@["����ʐM�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.����ʐM�p���s�w���~��ԋp����B<BR>
     * <BR>
     * �@@["�����ԍψ�ʐM�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����ԍ�(���ԍ�)��ʐM�p���s�w���~��ԋp����B<BR>
     * <BR>
     * �@@["�����ԍψ�ʐM�p��~(���s)"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����ԍ�(���ԍ�)��ʐM�p���s�w���~��ԋp����B<BR>
     * <BR>
     * �@@["���~�j����~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.���~�j����~��ԋp����B<BR>
     * <BR>
     * �@@["���~�j����~"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.���~�j����~��ԋp����B<BR>
     * <BR>
     * �@@// ��{���<BR>
     * �@@["�����P��"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����P�ʂ�ԋp����B<BR>
     * <BR>
     * �@@["�������x�P��"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�������x�P�ʂ�ԋp����B<BR>
     * <BR>
     * �@@["�������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�X�����J�敪��ԋp����B<BR>
     * <BR>
     * �@@["���敪"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.���敪 + <BR>
     * �@@�@@�p�����[�^.�������.�V�s��敪��ԋp����B<BR>
     * <BR>
     * �@@["���������K��"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.���������K����ԋp����B<BR>
     * <BR>
     * �@@["�����~�j�����s��"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�s��R�[�h��ԋp����B<BR>
     * <BR>
     * �@@// �M�p�������<BR>
     * �@@["���x�M�p�����敪"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.���x�M�p�����敪��ԋp����B<BR>
     * <BR>
     * �@@["��ʐM�p�����敪"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.��ʐM�p�����敪��ԋp����B<BR>
     * <BR>
     * �@@// �ϑ��ۏ؋���<BR>
     * �@@["���ۏ؋���"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.���ۏ؋�����ԋp����B<BR>
     * <BR>
     * �@@["���ۏ؋���"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.���ۏ؋�����ԋp����B<BR>
     * <BR>
     * �@@["�������ۏ؋���"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�������ۏ؋�����ԋp����B<BR>
     * <BR>
     * �@@["�������ۏ؋���"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�������ۏ؋�����ԋp����B<BR>
     * <BR>
     * �@@// �l�i���<BR>
     * �@@["��l�i�I�l�j"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.��l�i�I�l�j��ԋp����B<BR>
     * 
     * �@@// �l�i���<BR>
     * �@@["��l"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.��l��ԋp����B<BR>
     * <BR>
     * �@@["�l���`�F�b�N"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�l���`�F�b�N�敪��ԋp����B<BR>
     * <BR>
     * �@@["�����l��(�l���敪)"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�l���敪��ԋp����B<BR>
     * <BR>
     * �@@["�����l��(����)"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����l��(�����l)��ԋp����B<BR>
     * <BR>
     * �@@["�����l��(���)"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�������.�����l��(����l)��ԋp����B<BR>
     * <BR>
     * �@@["�����l��"�̏ꍇ]<BR>
     * �@@�@@[�@@�p�����[�^.�������.�l���敪 == "�w��Ȃ�"�̏ꍇ]<BR>
     * �@@�@@�@@�����l�� == �p�����[�^.�������.�l���敪<BR>
     * <BR>
     * �@@�@@[�A�p�����[�^.�������.�l���敪 == "1/XX"�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.�������.�l���敪<BR>
     * �@@�@@�@@+ �p�����[�^.�������.�����l��(����)<BR>
     * �@@�@@�@@+ "�`"<BR>
     * �@@�@@�@@+ �p�����[�^.�������.�l���敪<BR>
     * �@@�@@�@@+ �p�����[�^.�������.�����l��(���)��ԋp����B<BR>
     * <BR>
     * �@@�@@[�@@�A�A�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.�������.�����l��(����)<BR>
     * �@@�@@�@@+ �p�����[�^.�������.�l���敪<BR>
     * �@@�@@�@@+ "�`"<BR>
     * �@@�@@�@@+ �p�����[�^.�������.�����l��(���)<BR>
     * �@@�@@�@@+ �p�����[�^.�������.�l���敪��ԋp����B<BR>
     * <BR>
     * �@@["�l��"�̏ꍇ]<BR>
     * �@@�@@�P�j�l��������擾����B<BR>
     * �@@�@@�@@�g�����������}�l�[�W��.get�l������l(�p�����[�^.�������)<BR>
     * <BR>
     * �@@�@@�Q�j�l���������擾����B<BR>
     * �@@�@@�@@�g�����������}�l�[�W��.get�l�������l(�p�����[�^.�������)<BR>
     * <BR>
     * �@@�@@�R�j�l�����쐬����B<BR>
     * <BR>
     * �@@�@@�@@�l�� = �l������<BR>
     * �@@�@@�@@�@@�@@�@@�@@+ "�~�`"<BR>
     * �@@�@@�@@�@@�@@�@@�@@+ �l�����<BR>
     * �@@�@@�@@�@@�@@�@@�@@+ "�~"<BR>
     * <BR>
     * �@@�@@�S�j�쐬�����l����ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getTradedProductRegistrationValue<BR>
     * Return the value of WEB3EquityTradedProduct according to the argument
     * l_strSmallItemDiv<BR>
     * ��Use for eqtypeProductCondition<BR>
     * <BR>
     * �P�jReturn the l_tradedProduct for each parameter.l_strSmallItemDiv. <BR>
     *  ��Each value of the l_tradedProduct is converted to string when it is
     * acquired.<BR>
     * <BR>
     * Compare Parameter.l_strSmallItemDiv<BR>
     *  // Trade Regulation<BR>
     *  [If "Def.BUY_CASH_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.buy_cash_stop.<BR>
     * <BR>
     *  [If "Def.SELL_CASH_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.sell_cash_stop.<BR>
     * <BR>
     *  [If "Def.LONG_MARGIN_SYS_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_margin_sys_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_MARGIN_SYS_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_margin_sys_stop.<BR>
     * <BR>
     *  [If "Def.LONG_CLOSE_MARGIN_SYS_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_close_margin_sys_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_CLOSE_MARGIN_SYS_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_close_margin_sys_stop.<BR>
     * <BR>
     *  [If "Def.LONG_SWAP_MARGIN_SYS_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_swap_margin_sys_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_SWAP_MARGIN_SYS_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_swap_margin_sys_stop.<BR>
     * <BR>
     *  [If "Def.LONG_MARGIN_GEN_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_margin_gen_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_MARGIN_GEN_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_margin_gen_stop.<BR>
     * <BR>
     *  [If "Def.LONG_CLOSE_MARGIN_GEN_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_close_margin_gen_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_CLOSE_MARGIN_GEN_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_close_margin_gen_stop.<BR>
     * <BR>
     *  [If "Def.LONG_SWAP_MARGIN_GEN_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_swap_margin_gen_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_SWAP_MARGIN_GEN_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_swap_margin_gen_stop.<BR>
     * <BR>
     *  [If "Def.BUY_SPOT_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.buy_spot_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.SELL_SPOT_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.sell_spot_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.LONG_MS_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_ms_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_MS_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_ms_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.LONG_CMS_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_cms_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_CMS_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_cms_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.LONG_MG_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_mg_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_MG_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_mg_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.LONG_CMG_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_cmg_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_CMG_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_cmg_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.BUY_MINI_STOCK_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.buy_mini_stock_stop.<BR>
     * <BR>
     *  [If "Def.SELL_MINI_STOCK_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.sell_mini_stock_stop.<BR>
     * <BR>
     *  // Basic Info<BR>
     *  [If "Def.LOT_SIZE"]<BR>
     *   Return Parameter.l_tradedProduct.lot_size.<BR>
     * <BR>
     *  [If "Def.COMPULSIVE_LIMITED_UNIT"]<BR>
     *   Return Parameter.l_tradedProduct.compulsive_limited_unit.<BR>
     * <BR>
     *  [If "Def.OPEN_OTC_DIV"]<BR>
     *   Return Parameter.l_tradedProduct.open_otc_div.<BR>
     * <BR>
     *  [If "Def.LIST_TYPE"]<BR>
     *   Return Parameter.l_tradedProduct.list_type + <BR>
     *   Return Parameter.l_tradedProduct.new_list_type.<BR>
     * <BR>
     *  [If "Def.TODAY_DEP_FUND_REG"]<BR>
     *   Return Parameter.l_tradedProduct.today_dep_fund_reg.<BR>
     * <BR>
     *  [If "Def.MINI_STOCK_MARKET"]<BR>
     *   Return Parameter.l_tradedProduct.marketCode.<BR>
     * <BR>
     *  // Margin product info<BR>
     *  [If "Def.MARGIN_SYS_PRODUCT_TYPE"]<BR>
     *   Return Parameter.l_tradedProduct.margin_sys_product_type.<BR>
     * <BR>
     *  [If "Def.MARGIN_GEN_PRODUCT_TYPE"]<BR>
     *   Return Parameter.l_tradedProduct.margin_gen_product_type.<BR>
     * <BR>
     *  // Deposit rate<BR>
     *  [If "Def.LONG_MARGIN_DEPOSIT_RATE"]<BR>
     *   Return Parameter.l_tradedProduct.long_margin_deposit_rate.<BR>
     * <BR>
     *  [If "Def.SHORT_MARGIN_DEPOSIT_RATE"]<BR>
     *   Return Parameter.l_tradedProduct.short_margin_deposit_rate.<BR>
     * <BR>
     *  [If "Def.LONG_CASH_MARGIN_DEPOSIT_RATE"]<BR>
     *   Return Parameter.l_tradedProduct.long_cash_margin_deposit_rate.<BR>
     * <BR>
     *  [If "Def.SHORT_CASH_MARGIN_DEPOSIT_RATE"]<BR>
     *   Return Parameter.l_tradedProduct.short_cash_margin_deposit_rate.<BR>
     * <BR>
     *  // Price Info<BR>
     *  [If "Def.LAST_CLOSING_PRICE"]<BR>
     *   Return Parameter.l_tradedProduct.last_closing_price.<BR>
     * <BR>
     *  [If "Def.PRICE_RANGE_TYPE"]<BR>
     *   Return Parameter.l_tradedProduct.price_range_type.<BR>
     * <BR>
     *  [If "Def.PRICE_RANGE_UNIT_TYPE"]<BR>
     *   Return Parameter.l_tradedProduct.price_range_unit_type.<BR>
     * <BR>
     *  [If "Def.LOW_COMPULSIVE_PRICE_RANGE"]<BR>
     *   Return Parameter.l_tradedProduct.low_compulsive_price_range.<BR>
     * <BR>
     *  [If "Def.HIGH_COMPULSIVE_PRICE_RANGE"]<BR>
     *   Return Parameter.l_tradedProduct.high_compulsive_price_range.<BR>
     * <BR>
     *  ["Def.COMPULSIVE_PRICE_RANGE"]<BR>
     *   [�@@ If Parameter.l_tradedProduct.price_range_unit_type == "Def.DEFAULT"]<BR>
     *    compulsivePriceRange == Parameter.l_tradedProduct.price_range_unit_type<BR>
     * <BR>
     *   [�A If Parameter.l_tradedProduct.price_range_unit_type == "Def.FRACTION"]<BR>
     *    Parameter.l_tradedProduct.price_range_unit_type<BR>
     *    + Parameter.l_tradedProduct.low_compulsive_price_range<BR>
     *    + "�`"<BR>
     *    + Parameter.l_tradedProduct.price_range_unit_type<BR>
     *    + Return Parameter.l_tradedProduct.high_compulsive_price_range.<BR>
     * <BR>
     *   [If not �@@ or �A]<BR>
     *    Parameter.l_tradedProduct.low_compulsive_price_range<BR>
     *    + Parameter.l_tradedProduct.price_range_unit_type<BR>
     *    + "�`"<BR>
     *    + Parameter.l_tradedProduct.high_compulsive_price_range<BR>
     *    + Return Parameter.l_tradedProduct.price_range_unit_type.<BR>
     * <BR>
     *  [If "Def.PRICE_RANGE"]<BR>
     *   1)Acquire stopHighPrice.<BR>
     *    WEB3EquityOrderManager.getStopHighPrice(Parameter.l_tradedProduct)<BR>
     * <BR>
     *   2)Acquire stopLowPrice.<BR>
     *    WEB3EquityOrderManager.getStopLowPrice(Parameter.l_tradedProduct)<BR>
     * <BR>
     *   3)Create priceRange.<BR>
     * <BR>
     *    priceRange = stopLowPrice<BR>
     *        + "Def.YEN�`"<BR>
     *        + stopHighPrice<BR>
     *        + "Def.YEN"<BR>
     * <BR>
     *   4)Return the created price range.<BR>
     * <BR>
     * @@param l_eqtypeTradedProductParams - �i��������I�u�W�F�N�g�j<BR>
     * <BR>
     * l_tradedProduct<BR>
     * <BR>
     * @@param l_strSmallItemDiv - �i�����ڋ敪�j<BR>
     * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
     * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
     * ----<English>--------------------<BR>
     * l_strSmallItemDiv<BR>
     * ��Refer to the DB layout for the definition value. <BR>
     * �@@"eqtype_product_condition_table.xls"<BR>
     * <BR>
     * @@return java.lang.String
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 4199B568019D
     */
    public String getTradedProductRegistrationValue(
        EqtypeTradedProductParams l_eqtypeTradedProductParams,
        String l_strSmallItemDiv)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "getTradedProductRegistrationValue(WEB3EquityTradedProduct, String)";
        log.entering(STR_METHOD_NAME);

        Object l_registrationValue = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradedProduct l_tradedProduct = null;

        StringBuffer l_strBuffer = new StringBuffer();

        if (WEB3AdminEquitySmallItemDivDef.BUY_CASH_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.buy_cash_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.SELL_CASH_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.sell_cash_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_margin_sys_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_margin_sys_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CLOSE_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_close_margin_sys_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_CLOSE_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_close_margin_sys_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_SWAP_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_swap_margin_sys_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_SWAP_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_swap_margin_sys_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_margin_gen_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_margin_gen_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CLOSE_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_close_margin_gen_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_CLOSE_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_close_margin_gen_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_SWAP_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_swap_margin_gen_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_SWAP_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_swap_margin_gen_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.BUY_SPOT_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.buy_spot_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SELL_SPOT_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.sell_spot_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_MS_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_ms_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_MS_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_ms_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CMS_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_cms_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_CMS_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_cms_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_MG_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_mg_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_MG_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_mg_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CMG_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_cmg_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_CMG_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_cmg_market_ord_des_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.BUY_MINI_STOCK_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.buy_mini_stock_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.SELL_MINI_STOCK_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.sell_mini_stock_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.LOT_SIZE.equals(l_strSmallItemDiv))
        {
            l_registrationValue = new Double (l_eqtypeTradedProductParams.lot_size);
        } else if (
            WEB3AdminEquitySmallItemDivDef.COMPULSIVE_LIMITED_UNIT.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.compulsive_limited_unit;
        } else if (WEB3AdminEquitySmallItemDivDef.OPEN_OTC_DIV.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.open_otc_div;
        } else if (WEB3AdminEquitySmallItemDivDef.LIST_TYPE.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.list_type + l_eqtypeTradedProductParams.new_list_type;
        } else if (WEB3AdminEquitySmallItemDivDef.TODAY_DEP_FUND_REG.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.today_dep_fund_reg;
        } else if (WEB3AdminEquitySmallItemDivDef.MINI_STOCK_MARKET.equals(l_strSmallItemDiv))
        {
            long l_lngMarketId = l_eqtypeTradedProductParams.getMarketId();
            Market l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);

            String l_strMarketCode = l_market.getMarketCode();

            l_registrationValue = l_strMarketCode;
        } else if (
            WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_PRODUCT_TYPE.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.margin_sys_product_type;
        } else if (
            WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_PRODUCT_TYPE.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.margin_gen_product_type;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_DEPOSIT_RATE.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_margin_deposit_rate;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_DEPOSIT_RATE.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_margin_deposit_rate;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CASH_MARGIN_DEPOSIT_RATE.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_cash_margin_deposit_rate;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_CASH_MARGIN_DEPOSIT_RATE.equals(
                l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_cash_margin_deposit_rate;
        } else if (WEB3AdminEquitySmallItemDivDef.LAST_CLOSING_PRICE.equals(l_strSmallItemDiv))
        {
            l_registrationValue = new Double(l_eqtypeTradedProductParams.last_closing_price);
        } else if (WEB3AdminEquitySmallItemDivDef.BASE_PRICE.equals(l_strSmallItemDiv))
        {
            l_registrationValue = new Double(l_eqtypeTradedProductParams.base_price);
        } else if (WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_TYPE.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.price_range_type;
        } else if (WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_UNIT_TYPE.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.price_range_unit_type;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LOW_COMPULSIVE_PRICE_RANGE.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.low_compulsive_price_range;
        } else if (
            WEB3AdminEquitySmallItemDivDef.HIGH_COMPULSIVE_PRICE_RANGE.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.high_compulsive_price_range;
        } else if (WEB3AdminEquitySmallItemDivDef.COMPULSIVE_PRICE_RANGE.equals(l_strSmallItemDiv))
        {
        	if (l_eqtypeTradedProductParams.price_range_unit_type == null)
        	{
				l_registrationValue = null;
        	}else
        	{
				String l_strPriceRangeValue = new String();
	        
				if (l_eqtypeTradedProductParams.price_range_unit_type.equals(WEB3PriceRangeIdDef.YEN))
				{
					l_strPriceRangeValue = WEB3AdminPriceRangeValueDef.YEN;
				} else if (l_eqtypeTradedProductParams.price_range_unit_type.equals(WEB3PriceRangeIdDef.PERCENT))
				{
					l_strPriceRangeValue = WEB3AdminPriceRangeValueDef.PERCENT;
				} else if (l_eqtypeTradedProductParams.price_range_unit_type.equals(WEB3PriceRangeIdDef.FRACTION))
				{
					l_strPriceRangeValue = WEB3AdminPriceRangeValueDef.FRACTION;
				}
	
	            if (WEB3PriceRangeIdDef.DEFAULT.equals(l_eqtypeTradedProductParams.price_range_unit_type))
	            {
	                l_registrationValue = l_eqtypeTradedProductParams.price_range_unit_type;
	            } else if (WEB3PriceRangeIdDef.FRACTION.equals(l_eqtypeTradedProductParams.price_range_unit_type))
	            {
	                l_strBuffer.append(l_strPriceRangeValue);
	                l_strBuffer.append(WEB3StringTypeUtility.formatNumber(l_eqtypeTradedProductParams.low_compulsive_price_range.doubleValue()));
	                l_strBuffer.append("�^");
	                l_strBuffer.append(l_strPriceRangeValue);
	                l_strBuffer.append(WEB3StringTypeUtility.formatNumber(l_eqtypeTradedProductParams.high_compulsive_price_range.doubleValue()));
	
	                l_registrationValue = l_strBuffer.toString();
	            } else
	            {
	                l_strBuffer.append(WEB3StringTypeUtility.formatNumber(l_eqtypeTradedProductParams.low_compulsive_price_range.doubleValue()));
	                l_strBuffer.append(l_strPriceRangeValue);
	                l_strBuffer.append("�^");
	                l_strBuffer.append(WEB3StringTypeUtility.formatNumber(l_eqtypeTradedProductParams.high_compulsive_price_range.doubleValue()));
	                l_strBuffer.append(l_strPriceRangeValue);
	
	                l_registrationValue = l_strBuffer.toString();
	            }
        	}
        } else if (WEB3AdminEquitySmallItemDivDef.PRICE_RANGE.equals(l_strSmallItemDiv))
        {
            double l_dblStopHighPrice = 0;
            double l_dblStopLowPrice = 0;
            long l_lngProductId = 0;
            long l_lngMarketId = 0;
            WEB3EquityOrderManager l_equityOrderManager = null;
            WEB3EquityProductManager l_productManager = null;
            TradingModule l_tradingModule = null;

            l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

            l_equityOrderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            l_productManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();

            l_lngProductId = l_eqtypeTradedProductParams.product_id;
            l_lngMarketId = l_eqtypeTradedProductParams.market_id;

            l_tradedProduct =
                (WEB3EquityTradedProduct) l_productManager.getTradedProduct(
                    l_lngProductId,
                    l_lngMarketId);

            l_dblStopHighPrice = l_equityOrderManager.getStopHighPrice(l_tradedProduct);
            l_dblStopLowPrice = l_equityOrderManager.getStopLowPrice(l_tradedProduct);

            l_strBuffer.append(WEB3StringTypeUtility.formatNumber(l_dblStopLowPrice));
            l_strBuffer.append(WEB3AdminPriceRangeValueDef.YEN);
            l_strBuffer.append("�`");
            l_strBuffer.append(WEB3StringTypeUtility.formatNumber(l_dblStopHighPrice));
            l_strBuffer.append(WEB3AdminPriceRangeValueDef.YEN);

            l_registrationValue = l_strBuffer.toString();
        }

        log.exiting(STR_METHOD_NAME);

        if (l_registrationValue == null)
        {
            return null;
        }
        else if (l_registrationValue instanceof Double)
        {
            return WEB3StringTypeUtility.formatNumber(((Double)l_registrationValue).doubleValue());
        }
        else if (l_registrationValue instanceof BooleanEnum)
        {
            return String.valueOf(((BooleanEnum)l_registrationValue).intValue());
        }
        else
        {
	        return l_registrationValue.toString();
        }
    }

    /**
     * �iset��������o�^�l�j<BR>
     * �����̎�������X�V���ɕύX��o�^�l���Z�b�g����B<BR>
     * ���������������ݒ�ɂĎg�p�B<BR>
     * <BR>
     * �P�j�p�����[�^.������� == null�̏ꍇ�A�������I������B<BR>
     * <BR>
     * �Q�j�s�ꂲ�Ƃ̍X�V�����擾����B<BR>
     * �@@�p�����[�^.��������X�V���.get()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[get()�ɃZ�b�g����p�����[�^](����)<BR>
     * �@@�@@�@@key�F�@@�p�����[�^.�s��R�[�h<BR>
     * <BR>
     * �R�j�Q�j�̖߂�l == null�̏ꍇ�A�X�V����V�K�쐬����B<BR>
     * <BR>
     * �@@�R�|�P�j�X�V���C���X�^���X�𐶐�����B<BR>
     * �@@�R�|�Q�j���������X�V���Ɉȉ��̃v���p�e�B�Z�b�g���s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�X�V���.ID = �p�����[�^.�������.�������ID<BR>
     * �@@�@@�@@�@@�@@�X�V���.�L���� = �p�����[�^.�������.�L����<BR>
     * <BR>
     * �@@�R�|�R�j�p�����[�^.��������X�V���.put()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@key�F�@@�p�����[�^.�s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@value�F�@@�v���p�e�B�Z�b�g�����X�V���C���X�^���X<BR>
     * <BR>
     * �@@�R�|�S�j�p�����[�^.��������X�V���.get()���\�b�h���R�[������B<BR>
     * �@@�@@�@@�@@�@@��Lget()���\�b�h�̖߂�l���s�ꂲ�Ƃ̍X�V���Ƃ���B<BR>
     * �@@�@@�@@�@@���X�V����V�K�쐬�����̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�ȍ~�̏����ł́u�Q�j�̖߂�l�v�Ƃ����L�q�́A<BR>
     * �@@�@@�@@�@@�@@��Lget()���\�b�h�̖߂�l���w�����ƂƂ���B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[get()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@key�F�@@�p�����[�^.�s��R�[�h<BR>
     * <BR>
     * �S�j�o�^�l���Z�b�g����B<BR>
     * �@@[�p�����[�^.�����ڋ敪 == "���敪"�̏ꍇ]<BR>
     * �@@�@@�@@���敪�̍X�V��/�l���Z�b�g<BR>
     * �@@�@@�@@�Q�j�̖߂�l.�X�V��and�l.put()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@key�F�@@�p�����[�^.�X�V��<BR>
     * �@@�@@�@@�@@value�F�@@�p�����[�^.�ύX��o�^�l��1byte�ڂ̕���<BR>
     * <BR>
     * �@@�@@�A�V�s��敪�̍X�V��/�l���Z�b�g�B<BR>
     * �@@�@@�@@�Q�j�̖߂�l.�X�V��and�l.put()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@key�F�@@"�V�s��敪"(new_list_type)<BR>
     * �@@�@@�@@�@@value�F�@@�p�����[�^.�ύX��o�^�l��2byte�ڂ̕���<BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�Q�j�̖߂�l.�X�V��and�l.put()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@key�F�@@�p�����[�^.�X�V��<BR>
     * �@@�@@�@@�@@�@@value�F�@@�p�����[�^.�ύX��o�^�l<BR>
     * <BR>
     * �T�j�X�V�҃R�[�h���Z�b�g����B<BR>
     * �@@�Q�j�̖߂�l.�X�V��and�l.put()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[put()�ɃZ�b�g����p�����[�^(�X�V�҃R�[�h)]<BR>
     * �@@�@@key�F�@@"�X�V�҃R�[�h"(last_updater)<BR>
     * �@@�@@value�F�@@�p�����[�^.�X�V�҃R�[�h<BR>
     * <BR>
     * �U�j�X�V���t���Z�b�g����B<BR>
     * �@@�Q�j�̖߂�l.�X�V��and�l.put()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[put()�ɃZ�b�g����p�����[�^(�X�V���t)]<BR>
     * �@@�@@key�F�@@"�X�V���t"(last_updated_timestamp)<BR>
     * �@@�@@value�F�@@���ݎ���(*1)<BR>
     * <BR>
     * (*1)���ݎ����E�E�E<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TIMESTAMP_TAG)<BR>
     * �ɂĎ擾�������ݎ���<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * setTradedProductRegistrationValue<BR>
     * Set  l_productUpdateInfo into the argument l_aftBizDateRegistData<BR>
     * ��Set with eqtypeProductCondition<BR>
     * <BR>
     * 1)If Parameter.l_tradedProduct == null, end process.<BR>
     * <BR>
     * 2)Acquire the UpdateInfo for each market.<BR>
     *  Call Parameter.l_productUpdateInfo.get() method.<BR>
     * <BR>
     *   [Parameter to set in get()](common)<BR>
     *    key: Parameter.marketCode<BR>
     * <BR>
     * 3) If return value == null�ACreate a new updateInfo<BR>
     * <BR>
     *  3-1)Generate UpdateInfo instance.<BR>
     *  3-2)Set the property of the generated UpdateInfo as follows.<BR>
     * <BR>
     *      UpdateInfo.id = Parameter.l_tradedProduct.traded_product_id<BR>
     *      UpdateInfo.expirationDate =
     * Parameter.l_tradedProduct.valid_until_biz_date<BR>
     * <BR>
     *  3-3)Call Parameter.tradeProductUpdateInfo.put() method.<BR>
     * <BR>
     *      [Parameter to set into put()]<BR>
     *       key: Parameter.marketCode<BR>
     *       value: The UpdateInfo instance with the property set.<BR>
     * <BR>
     *  3-4)Call Parameter.tradeProductUpdateInfo.get() method.<BR>
     *      Use the return value of the get() method above to the updateInfo of each
     * market<BR>
     *     ��If a new updateInfo has been created�A<BR>
     *      From here on the description [Return value of 2)] refers to the Return
     * value of the<BR>
     *      above method.<BR>
     * <BR>
     *        [Set the parameter of get()]<BR>
     *         key: Parameter.marketCode<BR>
     * <BR>
     * 4)Set registValue<BR>
     *  [If Parameter.l_strSmallItemDiv == "Def.LIST_TYPE"]<BR>
     *   �@@Set the updateRowAndValue of list_type.<BR>
     *    Call return value of2).updateRowAndValue.put().<BR>
     * <BR>
     *    [The parameter to set into put()]<BR>
     *     key: Parameter.updateRowName<BR>
     *     value: The 1st byte of Parameter.l_aftBizDateRegistData<BR>
     * <BR>
     *   �ASet the updateRowAndValue of  newMarketDiv<BR>
     *    Call Return value of 2).updateRowAndValue.put()<BR>
     * <BR>
     *    [Parameter to set into put()]<BR>
     *     key: "new_list_type"(new_list_type)<BR>
     *     value: The 2nd byte of Parameter.l_aftBizDateRegistData<BR>
     * <BR>
     *  [ELSE]<BR>
     *   Call Return value of 2)updateRowAndValue.put()<BR>
     * <BR>
     *     [Parameter to set into put()<BR>
     *      key: Parameter. updateRowName<BR>
     *      value: Parameter.aftBizDateRegistData<BR>
     * <BR>
     * �T�jSet the last_updater<BR>
     *  Call Return value of 2)updateRowAndValue.put() method.<BR>
     * <BR>
     *  [Parameter to set into put()]<BR>
     *   key: "last_updater"(last_updater)<BR>
     *   value: Parameter.lastUpdater<BR>
     * <BR>
     * 6)Set updateDate.<BR>
     *  Call Return value of 2)updateRowAndValue.put() method<BR>
     * <BR>
     *  [Parameter to set into put()]<BR>
     *   key: "last_updated_timestamp"(last_updated_timestamp)<BR>
     *   value: time stamp(*1)<BR>
     * <BR>
     * (*1)timeStamp<BR>
     *  The timestamp aquired at
     * ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManageme
     * nt.TIMESTAMP_TAG)<BR>
     * <BR>
     * @@param l_productUpdateInfo - �i��������X�V���j<BR>
     * <BR>
     * l_productUpdateInfo<BR>
     * @@param l_eqtypeTradedProductParams - �i��������j<BR>
     * <BR>
     * l_tradedProduct<BR>
     * <BR>
     * @@param l_strSmallItemDiv - �i�����ڋ敪�j<BR>
     * ����`�l�ɂ��Ă�DB���C�A�E�g<BR>
     * �@@�u�������������ݒ�e�[�u���d�l.xls�v�Q��<BR>
     * ----<English>--------------------<BR>
     * l_strSmallItemDiv<BR>
     * ��Refer to the DB layout for the definition value. <BR>
     * �@@"eqtype_product_condition_table.xls"<BR>
     * @@param l_strMarketCode - �i�s��R�[�h�j<BR>
     * <BR>
     * l_strMarketCode<BR>
     * <BR>
     * @@param l_updateColumnName - �i�X�V�񖼁j<BR>
     * <BR>
     * l_updateRowName<BR>
     * <BR>
     * @@param l_aftBizDateRegistData - �i�ύX��o�^�l�j<BR>
     * <BR>
     * l_aftBizDateRegistData<BR>
     * @@param l_lastUpdater - �i�X�V�҃R�[�h�j<BR>
     * <BR>
     * l_lastUpdater<BR>
     * @@roseuid 4199B89300D2
     */
    public void setTradedProductRegistrationValue(
        HashMap l_productUpdateInfo,
        EqtypeTradedProductParams l_eqtypeTradedProductParams,
        String l_strSmallItemDiv,
        String l_strMarketCode,
        String l_updateColumnName,
        String l_aftBizDateRegistData,
        String l_lastUpdater)
    {
        final String STR_METHOD_NAME =
            "setTradedProductRegistrationValue(HashMap, WEB3EquityTradedProduct, "
                + "String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        Timestamp l_lastUpdatedTimestamp = null;

        // 1 Checking the l_tradedProduct object is not equal to null
        if (l_eqtypeTradedProductParams != null)
        {
            WEB3AdminPMUpdateInfo l_updateInfo = null;

            // 2
            l_updateInfo = (WEB3AdminPMUpdateInfo) l_productUpdateInfo.get(l_strMarketCode);

            // 3 Checking return value of 2 is null
            if (l_updateInfo == null)
            {
                // 3.1
                l_updateInfo = new WEB3AdminPMUpdateInfo();

                // 3.2
                l_updateInfo.id = l_eqtypeTradedProductParams.getTradedProductId();

                l_updateInfo.expirationDate = l_eqtypeTradedProductParams.valid_until_biz_date;

                // 3.3
                l_productUpdateInfo.put(l_strMarketCode, l_updateInfo);

                // 3.4
                l_updateInfo = (WEB3AdminPMUpdateInfo) l_productUpdateInfo.get(l_strMarketCode);
            }

            // 4 Checking WEB3AdminEquitySmallItemDivDef.LIST_TYPE is equals with l_strSmallItemDiv
            if (WEB3AdminEquitySmallItemDivDef.LIST_TYPE.equals(l_strSmallItemDiv))
            {
                // 4.1
                l_updateInfo.updateRowAndValue.put(
                    l_updateColumnName,
                    l_aftBizDateRegistData.substring(0, 1));

                // 4.2
                l_updateInfo.updateRowAndValue.put(
                    "new_list_type",
                    l_aftBizDateRegistData.substring(1, 2));

            } else
            {
                l_updateInfo.updateRowAndValue.put(l_updateColumnName, l_aftBizDateRegistData);
            }

            // 5
            l_updateInfo.updateRowAndValue.put(
                WEB3AdminEquityLastUpdaterDef.LAST_UPDATER,
                l_lastUpdater);

            // 6
            l_lastUpdatedTimestamp =
                (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            l_updateInfo.updateRowAndValue.put(
                WEB3AdminEquityLastUpdaterDef.LAST_UPDATED_TIMESTAMP,
                l_lastUpdatedTimestamp);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iget�����~�敪�j<BR>
     * �����̒�����ʂɊY������ڋq�����ʎ����~Params��<BR>
     * ���ڂ�ԋp����B<BR>
     * ���ڋq�����ʎ����~�ɂĎg�p�B<BR>
     * <BR>
     * �@@�p�����[�^.������ʂ��A<BR>
     * �@@["��������������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.�����������~��ԋp����B<BR>
     * <BR>
     * �@@["��������������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.�����������~��ԋp����B<BR>
     * <BR>
     * �@@["�����M�p�V�K��������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.���V�K�������~��ԋp����B<BR>
     * <BR>
     * �@@["�����M�p�V�K��������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.���V�K�������~��ԋp����B<BR>
     * <BR>
     * �@@["�����M�p�V�K�����ԍϒ���"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.�����ԍ�(���ԍ�)�����~��ԋp����B<BR>
     * <BR>
     * �@@["�����M�p�V�K�����ԍϒ���"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.�����ԍ�(���ԍ�)�����~��ԋp����B<BR>
     * <BR>
     * �@@["�����M�p��������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.���������~��ԋp����B<BR>
     * <BR>
     * �@@["�����M�p�K���n����"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.���n�����~��ԋp����B<BR>
     * <BR>
     * �@@["�����~�j��������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.���~�j�������~��ԋp����B<BR>
     * <BR>
     * �@@["�����~�j��������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.���~�j�������~��ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getTradeStopDiv<BR>
     * Retun the value item of l_accountProductOrderStopParams<BR>
     * specific to the argument l_orderType.<BR>
     * <BR>
     * ��Use at the account product order stop<BR>
     * <BR>
     * Compare the following with Parameter.l_orderType<BR>
     *  [If "Def.EQUITY_BUY"]<BR>
     *   Acquire Parameter.l_accountProductOrderStopParams.stop_trade_div_buy_cash<BR>
     * <BR>
     *  [If "Def.EQUITY_SELL"]<BR>
     *   Acquire Parameter.l_accountProductOrderStopParams.stop_trade_div_sell_cash<BR>
     * <BR>
     *  [If "Def.MARGIN_LONG"]<BR>
     *   Acquire
     * Parameter.l_accountProductOrderStopParams.stop_trade_div_long_margin<BR>
     * <BR>
     *  [If "Def.MARGIN_SHORT"]<BR>
     *   Acquire
     * Parameter.l_accountProductOrderStopParams.stop_trade_div_short_margin<BR>
     * <BR>
     *  [If "Def.CLOSE_MARGIN_LONG"]<BR>
     *   Acquire
     * Parameter.l_accountProductOrderStopParams.stop_div_long_close_margin<BR>
     * <BR>
     *  [If "Def.CLOSE_MARGIN_SHORT"]<BR>
     *   Acquire
     * Parameter.l_accountProductOrderStopParams.stop_div_short_close_margin<BR>
     * <BR>
     *  [If "Def.SWAP_MARGIN_LONG"]<BR>
     *   Acquire
     * Parameter.l_accountProductOrderStopParams.stop_div_long_swap_margin<BR>
     * <BR>
     *  [If "Def.SWAP_MARGIN_SHORT"]<BR>
     *   Acquire
     * Parameter.l_accountProductOrderStopParams.stop_div_short_swap_margin<BR>
     * <BR>
     *  [If "Def.MINI_STOCK_BUY"]<BR>
     *   Acquire Parameter.l_accountProductOrderStopParams.stop_div_buy_mini_stock<BR>
     * <BR>
     *  [If "Def.MINI_STOCK_SELL"]<BR>
     *   Acquire Parameter.l_accountProductOrderStopParams.stop_div_sell_mini_stock<BR>
     * <BR>
     * @@param l_orderType - �i������ʁj<BR>
     * (OrderTypeEnum�ɂĒ�`)<BR>
     * <BR>
     * l_orderType<BR>
     * (Define it with OrderTypeEnum. )<BR>
     * @@param l_accountProductOrderStopParams - �iAccountProductOrderStopParams�j<BR>
     * <BR>
     * l_accountProductOrderStopParams<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41989CAF03BB
     */
    public String getTradeStopDiv(
        OrderTypeEnum l_orderType,
        AccountProductOrderStopParams l_accountProductOrderStopParams)
    {
        final String STR_METHOD_NAME =
            "getTradeStopDiv(OrderTypeEnum, AccountProductOrderStopParams)";
        log.entering(STR_METHOD_NAME);

        String l_strTradeStopDiv = null;

        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_trade_div_buy_cash;
        } else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_trade_div_sell_cash;
        } else if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_trade_div_long_margin;
        } else if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_trade_div_short_margin;
        } else if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_div_long_close_margin;
        } else if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_div_short_close_margin;
        } else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_div_long_swap_margin;
        } else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_div_short_swap_margin;
        } else if (OrderTypeEnum.MINI_STOCK_BUY.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_div_buy_mini_stock;
        } else if (OrderTypeEnum.MINI_STOCK_SELL.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_div_sell_mini_stock;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strTradeStopDiv;
    }

    /**
     * �iset�����~�敪�j<BR>
     * �����̒�����ʂɊY������ڋq�����ʎ����~Params��<BR>
     * ���ڂɈ����̎����~�敪���Z�b�g����B<BR>
     * ���ڋq�����ʎ����~�ɂĎg�p�B<BR>
     * <BR>
     * �p�����[�^.������ʂ��A<BR>
     * �@@["��������������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.�����������~ =<BR>
     * �@@�@@�@@�p�����[�^.�����~�敪<BR>
     * <BR>
     * �@@["��������������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.�����������~ =<BR>
     * �@@�@@�@@�p�����[�^.�����~�敪<BR>
     * <BR>
     * �@@["�����M�p�V�K��������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.���V�K�������~ =<BR>
     * �@@�@@�@@�p�����[�^.�����~�敪<BR>
     * <BR>
     * �@@["�����M�p�V�K��������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.���V�K�������~ =<BR>
     * �@@�@@�@@�p�����[�^.�����~�敪<BR>
     * <BR>
     * �@@["�����M�p�V�K�����ԍϒ���"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.�����ԍ�(���ԍ�)�����~ =<BR>
     * �@@�@@�@@�p�����[�^.�����~�敪<BR>
     * <BR>
     * �@@["�����M�p�V�K�����ԍϒ���"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.�����ԍ�(���ԍ�)�����~ =<BR>
     * �@@�@@�@@�p�����[�^.�����~�敪<BR>
     * <BR>
     * �@@["�����M�p��������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.���������~ =<BR>
     * �@@�@@�@@�p�����[�^.�����~�敪<BR>
     * <BR>
     * �@@["�����M�p�K���n����"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.���n�����~ =<BR>
     * �@@�@@�@@�p�����[�^.�����~�敪<BR>
     * <BR>
     * �@@["�����~�j��������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.���~�j�������~ =<BR>
     * �@@�@@�@@�p�����[�^.�����~�敪<BR>
     * <BR>
     * �@@["�����~�j��������"�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.�ڋq�����ʎ����~Params.���~�j�������~ =<BR>
     * �@@�@@�@@�p�����[�^.�����~�敪<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * setTradeStopDiv<BR>
     * Set the specific return value of l_accountProductOrderStopParams.stop_trade_dev
     * according to l_orderType<BR>
     * ��Use at account product order stop<BR>
     * <BR>
     * Compare the following with Parameter.l_orderType<BR>
     *  [If "Def.EQUITY_BUY"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_trade_div_buy_cash =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.EQUITY_SELL"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_trade_div_sell_cash =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.MARGIN_LONG"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_trade_div_long_margin =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.MARGIN_SHORT"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_trade_div_short_margin =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.CLOSE_MARGIN_LONG"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_div_long_close_margin =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.CLOSE_MARGIN_SHORT"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_div_short_close_margin =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.SWAP_MARGIN_LONG"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_div_long_swap_margin =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.SWAP_MARGIN_SHORT"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_div_short_swap_margin =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.MINI_STOCK_BUY"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_div_buy_mini_stock =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.MINI_STOCK_SELL"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_div_sell_mini_stock =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     * @@param l_orderType - �i������ʁj<BR>
     * (OrderTypeEnum�ɂĒ�`)<BR>
     * ----<English>--------------------<BR>
     * l_orderType<BR>
     * (Define it with OrderTypeEnum. ))<BR>
     * <BR>
     * @@param l_accountProductOrderStopParams - �i�ڋq�����ʎ����~Params�j<BR>
     * ----<English>--------------------<BR>
     * l_accountProductOrderStopParams<BR>
     * <BR>
     * @@param l_tradeStopDiv - �i�����~�敪�j<BR>
     * ----<English>--------------------<BR>
     * l_tradeStopDiv<BR>
     * <BR>
     * @@roseuid 4198A1CC026A
     */
    public void setTradeStopDiv(
        OrderTypeEnum l_orderType,
        AccountProductOrderStopParams l_accountProductOrderStopParams,
        String l_tradeStopDiv)
    {
        final String STR_METHOD_NAME =
            "setTradeStopDiv(OrderTypeEnum, AccountProductOrderStopParams, String)";
        log.entering(STR_METHOD_NAME);

        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_trade_div_buy_cash = l_tradeStopDiv;
        } else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_trade_div_sell_cash = l_tradeStopDiv;
        } else if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_trade_div_long_margin = l_tradeStopDiv;
        } else if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_trade_div_short_margin = l_tradeStopDiv;
        } else if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_div_long_close_margin = l_tradeStopDiv;
        } else if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_div_short_close_margin = l_tradeStopDiv;
        } else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_div_long_swap_margin = l_tradeStopDiv;
        } else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_div_short_swap_margin = l_tradeStopDiv;
        } else if (OrderTypeEnum.MINI_STOCK_BUY.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_div_buy_mini_stock = l_tradeStopDiv;
        } else if (OrderTypeEnum.MINI_STOCK_SELL.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_div_sell_mini_stock = l_tradeStopDiv;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �icreate�ڋq�����ʎ����~���j<BR>
     * �����̌ڋq�����ʎ����~Params���ڋq�����ʎ����~�����쐬����B<BR>
     * ���ڋq�����ʎ����~�ɂĎg�p�B<BR>
     * <BR>
     * �P�j�ڋq�����ʎ����~���C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�P�j�ɂĐ��������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@���X�R�[�h = ���X(*1).���X�R�[�h<BR>
     * �@@�ڋq�R�[�h = �ڋq(*2).�ڋq�R�[�h�̐擪6byte<BR>
     * �@@�ڋq�� = �ڋq(*2).�ڋq��<BR>
     * �@@�����R�[�h = ��������(*3).�����R�[�h<BR>
     * �@@������ = ��������(*3).������<BR>
     * �@@�L������From = �p�����[�^.�ڋq�����ʎ����~Params.�L������From<BR>
     * �@@�L������To = �p�����[�^.�ڋq�����ʎ����~Params.�L������To<BR>
     * �@@���R = �p�����[�^.�ڋq�����ʎ����~Params.�����~���R<BR>
     * <BR>
     * �R�j�ڋq�����~�����i�[����ArrayList�𐶐�����B<BR>
     * <BR>
     * �S�j�ڋq�����~���ꗗ���쐬����B<BR>
     * �@@�����Ώۂ̒������(*4)�ɂ��āA�ȉ��̏������J��Ԃ��B<BR>
     * �@@�S�|�P�j�ڋq�����~���C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�S�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@������� = �����Ώۂ̒������<BR>
     * �@@�@@�@@�����~�敪 = this.get�����~�敪()�̖߂�l<BR>
     * <BR>
     * �@@�@@�@@[get�����~�敪()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@������ʁF�@@�����Ώۂ̒������<BR>
     * �@@�@@�@@�@@�ڋq�����ʎ����~Params�F�@@�p�����[�^.�ڋq�����ʎ����~Params<BR>
     * <BR>
     * �@@�S�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����ڋq�����~����<BR>
     * �@@�@@�@@�@@�@@�ǉ�����B<BR>
     * <BR>
     * �T�j�ڋq�����~���ꗗ���ڋq�����ʎ����~���C���X�^���X��<BR>
     * �@@�Z�b�g����B<BR>
     * <BR>
     * �@@�ڋq�����~���ꗗ = ��������ArrayList.toArray()<BR>
     * <BR>
     * �U�j�v���p�e�B�Z�b�g�����ڋq�����ʎ����~����ԋp����B<BR>
     * <BR>
     * (*1)���X<BR>
     * �@@�p�����[�^.�ڋq�����ʎ����~Params.���XID�ɊY�����镔�X�I�u�W�F�N�g<BR>
     * (*2)�ڋq<BR>
     * �@@�p�����[�^.�ڋq�����ʎ����~Params.�ڋqID�ɊY������ڋq�I�u�W�F�N�g<BR>
     * (*3)��������<BR>
     * �@@�p�����[�^.���������ʎ����~Params.����ID�ɊY�����銔�������I�u�W�F�N�g<BR>
     * �@@����L����ID == 0�̏ꍇ�A�����R�[�h�A�������ɂ�null���Z�b�g�B<BR>
     * (*4)�����Ώۂ̒������<BR>
     * �@@���ȉ��̒l���ォ�珇�ɏ�������B<BR>
     * �@@�E"��������������"<BR>
     * �@@�E"��������������"<BR>
     * <BR>
     * �@@[���X.���x�M�p���{�敪 == "���{"�܂���<BR>
     * �@@�@@���X.��ʐM�p���{�敪 == "���{"�̏ꍇ]<BR>
     * �@@��L�ɑ����Ĉȉ��̒�����ʂ���������B<BR>
     * �@@�@@�E"�����M�p�V�K��������"<BR>
     * �@@�@@�E"�����M�p�V�K��������"<BR>
     * �@@�@@�E"�����M�p�V�K�����ԍϒ���"<BR>
     * �@@�@@�E"�����M�p�V�K�����ԍϒ���"<BR>
     * �@@�@@�E"�����M�p��������"<BR>
     * �@@�@@�E"�����M�p�K���n����"<BR>
     * <BR>
     * �@@[���X.�~�j�����{�敪 == "���{"�̏ꍇ]<BR>
     * �@@��L�ɑ����Ĉȉ��̒�����ʂ���������B<BR>
     * �@@�@@�E"�����~�j��������"<BR>
     * �@@�@@�E"�����~�j��������"<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * createAccProductTradeStopInfoUnit<BR>
     * Create WEB3AdminPMAccProductTradeStopInfoUnit from the argument
     * l_accountProductOrderStopParams<BR>
     * ��Use at AccountProductOrderStop.<BR>
     * <BR>
     * 1�jGenerate WEB3AdminPMAccProductTradeStopInfoUnit instance<BR>
     * <BR>
     * 2)Set the property of the instance that has been generated at 1)<BR>
     * �@@branchCode = branch(*1).branchCode<BR>
     * �@@accountCode = account(*2).the first 6 bytes of accountCode<BR>
     * �@@accountName = account(*2).accountName<BR>
     * �@@productCode = equityProduct(*3).productCode<BR>
     * �@@productName = equityProduct(*3).productName<BR>
     * �@@expirationStartDate =
     * Parameter.accountProductOrderStopParams.apply_start_date<BR>
     * �@@expirationEndDate = Parameter.accountProductOrderStopParams.apply_end_date<BR>
     * �@@reason = Parameter.accountProductOrderStopParams.stop_trade_reason<BR>
     * <BR>
     * 3)Generate the ArrayList to store tradeStopInfo.<BR>
     * <BR>
     * 4)Create WEB3AdminPMAccTradeStopInfoUnit list.<BR>
     * �@@Loop the following process for the specific orderType<BR>
     * �@@4-1)Generate WEB3AdminPMAccTradeStopInfoUnit instance.<BR>
     * <BR>
     * �@@4-2)Set the property of the generated instance<BR>
     * �@@�@@�@@orderType = the specific ordertype<BR>
     * �@@�@@�@@tradeStopDiv = the return value of this.getTradeStopDiv()<BR>
     * <BR>
     * �@@�@@�@@[Set the parameter of getTradeStopDiv()]<BR>
     * �@@�@@�@@�@@l_orderType: the specific orderType<BR>
     * �@@�@@�@@�@@l_accountProductOrderStopParams:
     * Parameter.accountProductOrderStopParams<BR>
     * <BR>
     * �@@4-3)Add the tradeStopInfoUnit to the generated ArrayList property<BR>
     * <BR>
     * 5)Set tradeStopInfoUnitList into the WEB3AdminPMAccProductTradeStopInfoUnit
     * instance<BR>
     * <BR>
     * �@@tradeStopInfoUnitList = Generated ArrayList.toArray()<BR>
     * <BR>
     * 6)Return the WEB3AdminPMAccProductTradeStopInfoUnit with the propertys set.<BR>
     * <BR>
     * (*1)brach<BR>
     * �@@The object that corresponds with
     * Parameter.accountProductOrderStopParams.branchID<BR>
     * (*2)account<BR>
     * �@@The object that corresponds with
     * Parameter.accountProductOrderStopParams.accountID<BR>
     * (*3)equity_product<BR>
     * �@@The object that corresponds with
     * Parameter.accountProductOrderStopParams.productID<BR>
     * �@@��If the above product_id ==0<BR>
     *        Set the following<BR>
     *          �EproductCode = null<BR>
     *          �EproductName = null<BR>
     * (*4)The specific orderType<BR>
     * �@@��Process in the following order<BR>
     * �@@�E"Def.EQUITY_BUY"<BR>
     * �@@�E"Def.EQUITY_SELL"<BR>
     * <BR>
     * �@@[If branch.margin_sys_div == "Def.ENFORCEMENT" or<BR>
     * �@@�@@branch.margin_gen_div == "Def.ENFORCEMENT"]<BR>
     * �@@Perform the following process of orderType after the above process<BR>
     * �@@�@@�E"Def.MARGIN_LONG"<BR>
     * �@@�@@�E"Def.MARGIN_SHORT"<BR>
     * �@@�@@�E"Def.CLOSE_MARGIN_LONG"<BR>
     * �@@�@@�E"Def.CLOSE_MARGIN_SHORT"<BR>
     * �@@�@@�E"Def.SWAP_MARGIN_LONG"<BR>
     * �@@�@@�E"SWAP_MARGIN_SHORT"<BR>
     * <BR>
     * �@@[If branch.mstk_div == "Def.ENFORCEMENT"]<BR>
     * �@@Perform the following process of orderType after the above process<BR>
     * �@@�@@�E"Def.MF_BUY"<BR>
     * �@@�@@�E"Def.MF_SELL"<BR>
     * <BR>
     * @@param l_accountProductOrderStopParams - �i�ڋq�����ʎ����~Params�j<BR>
     * ----<English>--------------------<BR>
     * l_accountProductOrderStopParams<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopInfoUnit
     * @@throws DataQueryException Data Query Exception
     * @@throws DataNetworkException Data Network Exception
     * @@throws DataFindException Exception in aquiring the Data
     * @@throws NotFoundException Not Found Exception
     * @@roseuid 41989F7A00C8
     */
    public WEB3AdminPMAccProductTradeStopInfoUnit createAccProductTradeStopInfoUnit(
        AccountProductOrderStopParams l_accountProductOrderStopParams)
        throws DataFindException, DataNetworkException, DataQueryException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "createAccProductTradeStopInfoUnit(AccountProductOrderStopParams)";
        long l_lngAccountId = 0;

        log.entering(STR_METHOD_NAME);

        WEB3AdminPMAccProductTradeStopInfoUnit l_productTradeStopInfoUnit =
            new WEB3AdminPMAccProductTradeStopInfoUnit();

        /*
         * Set All the property in l_productTradeStopInfoUnit
         * 2.1. Set BranchCode
         */
        BranchRow l_branchRow =
            BranchDao.findRowByBranchId(l_accountProductOrderStopParams.branch_id);
        l_productTradeStopInfoUnit.branchCode = l_branchRow.getBranchCode();

        // 2.2
        l_lngAccountId = l_accountProductOrderStopParams.account_id;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount) l_accountManager.getMainAccount(l_lngAccountId);
        // First 6 bytes of accountCode
        l_productTradeStopInfoUnit.accountCode = l_mainAccount.getAccountCode().substring(0, 6);
        l_productTradeStopInfoUnit.accountName = l_mainAccount.getDisplayAccountName();

        // 2.3. Set ProductCode and ProductName
        if (l_accountProductOrderStopParams.product_id != 0)
        {
            EqtypeProductRow l_eqtypeProductRow =
                EqtypeProductDao.findRowByProductId(l_accountProductOrderStopParams.product_id);
            l_productTradeStopInfoUnit.productCode = l_eqtypeProductRow.getProductCode();
            l_productTradeStopInfoUnit.productName = l_eqtypeProductRow.getStandardName();
        }

        // 2.4 Set expirationStartDate
		SimpleDateFormat formatter = new SimpleDateFormat();
		formatter.applyPattern("yyyyMMdd");
				
        l_productTradeStopInfoUnit.expirationStartDate =
		    formatter.format(l_accountProductOrderStopParams.apply_start_date);

        // 2.5 Set expirationEndDate
        l_productTradeStopInfoUnit.expirationEndDate =
		    formatter.format(l_accountProductOrderStopParams.apply_end_date);

        // 2.6 Set reason
        l_productTradeStopInfoUnit.reason = l_accountProductOrderStopParams.stop_trade_reason;

        // 3) Generate the ArrayList to store tradeStopInfo.
        List l_lisTradeStopInfo = new ArrayList();

        // 4-1) Generate WEB3AdminPMAccTradeStopInfoUnit instance.
        WEB3AdminPMAccTradeStopInfoUnit l_tradeStopInfoUnit = null;

        List l_lstOrderTypeEnum = new ArrayList();

        l_lstOrderTypeEnum.add(OrderTypeEnum.EQUITY_BUY);

        l_lstOrderTypeEnum.add(OrderTypeEnum.EQUITY_SELL);

        /*
         * When branch.margin_sys_div == "Def.ENFORCEMENT"
         *      or branch.margin_gen_div == "Def.ENFORCEMENT"
         */
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginSysDiv())
            || WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginGenDiv()))
        {
            l_lstOrderTypeEnum.add(OrderTypeEnum.MARGIN_LONG);
            l_lstOrderTypeEnum.add(OrderTypeEnum.MARGIN_SHORT);
            l_lstOrderTypeEnum.add(OrderTypeEnum.CLOSE_MARGIN_LONG);
            l_lstOrderTypeEnum.add(OrderTypeEnum.CLOSE_MARGIN_SHORT);
            l_lstOrderTypeEnum.add(OrderTypeEnum.SWAP_MARGIN_LONG);
            l_lstOrderTypeEnum.add(OrderTypeEnum.SWAP_MARGIN_SHORT);
        }

        // When branch.mstk_div == "Def.ENFORCEMENT"
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMstkDiv()))
        {
            l_lstOrderTypeEnum.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_lstOrderTypeEnum.add(OrderTypeEnum.MINI_STOCK_SELL);
        }

        OrderTypeEnum[] l_enumerated =
            (OrderTypeEnum[])l_lstOrderTypeEnum.toArray(new OrderTypeEnum[0]);

        for (int i = 0; i < l_enumerated.length; i++)
        {
            l_tradeStopInfoUnit = new WEB3AdminPMAccTradeStopInfoUnit();
            l_tradeStopInfoUnit.orderType = String.valueOf(l_enumerated[i].intValue());
            l_tradeStopInfoUnit.tradeStopDiv =
                this.getTradeStopDiv(l_enumerated[i], l_accountProductOrderStopParams);
            l_lisTradeStopInfo.add(l_tradeStopInfoUnit);
        }

        // 5) Set tradeStopInfoUnitList into the WEB3AdminPMAccProductTradeStopInfoUnit
        WEB3AdminPMAccTradeStopInfoUnit[] l_arrAccTradeStopInfoUnit =
            new WEB3AdminPMAccTradeStopInfoUnit[l_lisTradeStopInfo.size()];
        l_lisTradeStopInfo.toArray(l_arrAccTradeStopInfoUnit);
        l_productTradeStopInfoUnit.accTradeStopInfoList = l_arrAccTradeStopInfoUnit;

        log.exiting(STR_METHOD_NAME);
        return l_productTradeStopInfoUnit;
    }

    /**
     * �iget�������������ݒ�Params�ꗗ�j<BR>
     * �����̏����ɊY�����銔�����������ݒ�Params�̈ꗗ��<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@"�������������ݒ�e�[�u��"(eqtype_product_condition)<BR>
     * �@@�@@arg1�F�@@�p�����[�^.��������������<BR>
     * �@@�@@arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@�@@arg3�F�@@null<BR>
     * �@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �R�j�Q�j�̌������ʂ�ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getEqtypeProductConditionParamsList<BR>
     * Return the list of eqtypeProductConditionParams() that matches the conditions of
     * the arguments<BR>
     * <BR>
     * 1) Call QueryProcessor.doFindAllQuery() method.<BR>
     * <BR>
     *  [Parameters to set into doFindAllQuery()]<BR>
     *   arg0: "eqtype_product_condition table"(eqtype_product_condition)<BR>
     *   arg1: Parameter.l_strQueryCond<BR>
     *   arg2: Parameter.l_strSortCond<BR>
     *   arg3: null<BR>
     *   arg4: Parameter.l_strQueryCondDataContainer<BR>
     * <BR>
     *  If the search result cannot be aquired return null.<BR>
     * <BR>
     * 2)Return the search result of 1).<BR>
     * <BR>
     * @@param l_strQueryCond - �i��������������j<BR>
     * ----<English>--------------------<BR>
     * l_strQueryCond<BR>
     * <BR>
     * @@param l_strQueryCondDataContainer - �i���������f�[�^�R���e�i�j<BR>
     * ----<English>--------------------<BR>
     * l_strQueryCondDataContainer<BR>
     * <BR>
     * @@param l_strSortCond - �i�\�[�g�����j<BR>
     * ----<English>--------------------<BR>
     * l_strSortCond<BR>
     * <BR>
     * @@return List
     * @@throws DataFindException DataFindException
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 41B6E7BB018F
     */
    public List getEqtypeProductConditionParamsList(
        String l_strQueryCond,
        Object[] l_strQueryCondDataContainer,
        String l_strSortCond)
        throws DataFindException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME =
            "getEqtypeProductConditionParamsList(String, Object[], String)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        List l_lisSearchResult = null;
        int l_intLength = 0;

        l_queryProcessor = Processors.getDefaultProcessor();

        // 1
        l_lisSearchResult =
            l_queryProcessor.doFindAllQuery(
                EqtypeProductConditionRow.TYPE,
                l_strQueryCond,
                l_strSortCond,
                null,
                l_strQueryCondDataContainer);

        l_intLength = l_lisSearchResult.size();
        if (l_intLength > 0)
        {
            log.exiting(STR_METHOD_NAME);
            // 2
            return l_lisSearchResult;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * �iget�������������ݒ�Params�ꗗ�j<BR>
     * (get�������������ݒ�Params�ꗗ�̃I�[�o�[���[�h)<BR>
     * �����̏����ɊY�����銔�����������ݒ�Params�̈ꗗ��<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�j�ȉ��̏�����\�����������������<BR>
     * �@@ArrayList(�p�����[�^�Z�b�g)���쐬����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h�@@����<BR>
     * �@@�@@�����R�[�h = �p�����[�^.�����R�[�h�@@����<BR>
     * �@@�@@�폜�t���O = "0�F���폜"<BR>
     * <BR>
     * �@@�P�|�P�j��L��������Ɍ���������������쐬����B<BR>
     * <BR>
     * �@@�@@�@@�@@�������������� = " institution_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and product_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and delete_flg = ?"<BR>
     * <BR>
     * �@@�P�|�Q�j����������"?"�ɃZ�b�g����l�̃p�����[�^�Z�b�g��<BR>
     * �@@�@@�@@�@@�@@�쐬����B<BR>
     * �@@�@@�@@�@@�@@ArrayList�𐶐����A�ȉ��̒l��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@�E�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�E�p�����[�^.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�E"0�F���폜"<BR>
     * <BR>
     * �Q�jthis.�������������ݒ�Params�ꗗ()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[�������������ݒ�Params�ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@��������������F�@@�쐬������������������<BR>
     * �@@�@@���������f�[�^�R���e�i�F�@@�쐬����ArrayList.toArray()<BR>
     * �@@�@@�\�[�g�����F�@@null<BR>
     * <BR>
     * �R�j�Q�j�̌������ʂ�ԋp����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getEqtypeProductConditionParamsList<BR>
     * The overload of getEqtypeProductConditionParamsList<BR>
     * Return the getEqtypeProductConditionParamsList that match the condition of the
     * arguments<BR>
     * <BR>
     * 1)The l_strQueryCond that match the following condition and<BR>
     *  ArrayList(set parameter).<BR>
     * <BR>
     *  [Condition]<BR>
     *   institutionCode = Parameter.institutionCode and<BR>
     *   productCode = Parameter.productCode and<BR>
     *   delete_flg = "0: Def.DELETE_NO"<BR>
     * <BR>
     *  1-1)Create a searchConditionString using the above condition.<BR>
     * <BR>
     *     l_strQueryCond = " institution_code = ? "<BR>
     *                + "and product_code = ? "<BR>
     *                + "and delete_flg = ?"<BR>
     * <BR>
     *  1-2)Create the the value of the parameter to set into the search condition
     * "?"<BR>
     *      Generate ArrayList and add the following values.<BR>
     *       �EParameter.institutionCode<BR>
     *       �EParameter.productCode<BR>
     *       �E"0: Def.DELETE_NO"<BR>
     * <BR>
     * �Q�jCall this.getEqtypeProductConditionParamsList() method.<BR>
     * <BR>
     *  [Set the parameter into eqtypeProductConditionParams()]<BR>
     *   l_strQueryCond: The l_strQueryCond that has been created.<BR>
     *   l_strQueryCondDataContainer: The ArrayList.toArray() that has been
     * created.<BR>
     *   l_strSortCond: null<BR>
     * <BR>
     * �R�jReturn the sarch result of 2).<BR>
     * <BR>
     * @@param l_strInstitutionCode - �i�،���ЃR�[�h�j<BR>
     * ----<English>--------------------<BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_strProductCode - �i�����R�[�h�j<BR>
     * ----<English>--------------------<BR>
     * l_strProductCode<BR>
     * <BR>
     * @@return List
     * @@throws DataQueryException Data Query Exception
     * @@throws DataNetworkException Data Network Exception
     * @@throws DataFindException Exception in aquiring the Data
     * @@roseuid 41B6E29D03E2
     */
    public List getEqtypeProductConditionParamsList(
        String l_strInstitutionCode,
        String l_strProductCode)
        throws DataFindException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = "getEqtypeProductConditionParamsList(String, String)";
        List l_lisSearchResult = null;

        log.entering(STR_METHOD_NAME);

        // Build Where clause.
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and delete_flg = ? ");
        String l_strWhere = l_sbWhere.toString();

        // Add parameters in Where clause.
        Object[] l_objWhere =
            {l_strInstitutionCode, l_strProductCode, WEB3AdminEquityDeleteFlgDef.NOT_DELETE};

        // Select Records based on the where clause.
        l_lisSearchResult = this.getEqtypeProductConditionParamsList(l_strWhere, l_objWhere, null);

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /**
     * �iupdate�������������ݒ�j<BR>
     * �������������ݒ�e�[�u�����X�V����B<BR>
     * <BR>
     * �P�jQueryProcessor.doUpdateQuery()���R�[������B<BR>
     * <BR>
     * �@@[doUpdateQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�p�����[�^.�������������ݒ�Params<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * updateEqtypeProductCondition<BR>
     * Update the eqtype_prodcut_conditon table.<BR>
     * <BR>
     * 1)Call QueryProcessor.doUpdateQuery(). <BR>
     * <BR>
     *   [The parameter to set into doUpdateQuery()]<BR>
     *     arg0:Parameter. eqtypeProductConditionParams<BR>
     * <BR>
     * @@param l_eqtypeProductConditionParams - �i�������������ݒ�Params�j<BR>
     * ������������Params<BR>
     * ----<English>--------------------<BR>
     * �il_eqtypeProductConditionParams�j<BR>
     * l_eqtypeProductConditionParams<BR>
     * <BR>
     * @@throws DataQueryException Data Query Exception
     * @@throws DataNetworkException Data Network Exception
     * @@roseuid 41B6E29D03E5
     */
    public void updateEqtypeProductCondition(
        EqtypeProductConditionParams l_eqtypeProductConditionParams)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "updateEqtypeProductCondition(EqtypeProductConditionParams)";
        QueryProcessor l_queryProcessor = null;
        log.entering(STR_METHOD_NAME);

        l_queryProcessor = Processors.getDefaultProcessor();

        // UpdateQuery the EqtypeProductConditionParams Object Into the DataBase.
        l_queryProcessor.doUpdateQuery(l_eqtypeProductConditionParams);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iinsert�������������ݒ�j<BR>
     * �������������ݒ�e�[�u���Ɉꌏ�f�[�^��o�^����B<BR>
     * <BR>
     * �P�jQueryProcessor.doInsertQuery()���R�[������B<BR>
     * <BR>
     * �@@[doInsertQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�p�����[�^.�������������ݒ�Params<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * insertEqtypeProductCondition<BR>
     * Insert one record into eqtype_product_condition table. <BR>
     * <BR>
     * 1)Call QueryProcessor.doInsertQuery(). <BR>
     * <BR>
     *   [The parameter to set into doInsertQuery()]<BR>
     * arg0:Parameter.eqtypeProductConditionParams<BR>
     * <BR>
     * @@param l_eqtypeProductConditionParams - �i�������������ݒ�Params�j<BR>
     * ������������Params<BR>
     * ----<English>--------------------<BR>
     * �il_eqtypeProductConditionParams�j<BR>
     * l_eqtypeProductConditionParams<BR>
     * @@throws DataQueryException Data Query Exception
     * @@throws DataNetworkException Data Network Exception
     * <BR>
     * @@roseuid 41B6E29D03E7
     */
    public void insertEqtypeProductCondition(
        EqtypeProductConditionParams l_eqtypeProductConditionParams)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "insertEqtypeProductCondition(EqtypeProductConditionParams)";
        QueryProcessor l_queryProcessor = null;
        log.entering(STR_METHOD_NAME);

        l_queryProcessor = Processors.getDefaultProcessor();

        // Insert the EqtypeProductConditionParams Object Into the DataBase.
        l_queryProcessor.doInsertQuery(l_eqtypeProductConditionParams);

        log.exiting(STR_METHOD_NAME);
    }

	/**
	 * �iget���XID�ꗗ�j<BR>
	 * �����̏،���ЃR�[�h�A���X�R�[�h�ɊY�����镔�XID�̈ꗗ��ԋp����B<BR>
	 * 
	 * @@param l_strInstitutionCode - �i�،���ЃR�[�h�j<BR>
	 * @@param l_strBranchCodes - �i���X�R�[�h�j<BR>
	 * @@throws DataQueryException Data Query Exception <BR>
	 * @@throws DataNetworkException Data Network Exception <BR>
	 * @@throws DataFindException Exception in aquiring the Data <BR>
	 * @@return String[]
	 * 
	 */
	public String[] getBranchId(
		String l_strInstitutionCode,
		String[] l_strBranchCodes)
		throws DataFindException, DataNetworkException, DataQueryException
	{
		final String STR_METHOD_NAME = "getBranchIdList(String, String[])";
		log.entering(STR_METHOD_NAME);

		List l_lisSearchResult = null;
		String[] l_strBranchId = null;

		// Build Where clause.
		StringBuffer l_sbWhere = new StringBuffer();
		StringBuffer l_strBranchCode = new StringBuffer();

		l_sbWhere.append(" institution_code = ? ");
		l_sbWhere.append(" and branch_code in (");

		// Add parameters in Where clause.
		Object[] l_objWhere = new Object[(l_strBranchCodes.length + 1)];
		l_objWhere[0] = l_strInstitutionCode;

		for (int i = 0; i < l_strBranchCodes.length; i++)
		{
			l_sbWhere.append("?,");
			l_objWhere[i + 1] = l_strBranchCodes[i];
		}

		String l_strWhere = l_sbWhere.substring(0,l_sbWhere.length() -1) + ")";
		l_lisSearchResult = Processors.getDefaultProcessor().doFindAllQuery(
			BranchRow.TYPE,
			l_strWhere,
			l_objWhere);
			int l_intCount = l_lisSearchResult.size();
			if (l_intCount > 0)
			{
				l_strBranchId = new String[l_intCount];
				for (int i = 0; i < l_lisSearchResult.size(); i++)
				{
					BranchRow l_BranchRow = (BranchRow)l_lisSearchResult.get(i);
					l_strBranchId[i] = Long.toString ((l_BranchRow.getBranchId())) ;
				}
			}
			return l_strBranchId;
	}
	
    /**
     * (get�ڋq�ꗗ)<BR>
     * �����̏����ɊY������ڋq�̈ꗗ��ԋp����B<BR> 
     * <BR>
     * �P�j ArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j DB���� <BR>
     * �@@�p�����[�^.���X�R�[�h[]�̗v�f�����ȉ��̏������J��Ԃ��B <BR>
     * �@@�Q�|�P�j �g���A�J�E���g�}�l�[�W��.get�ڋq()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@�@@[get�ڋq()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h <BR>
     * �@@�@@�@@���X�R�[�h�F�@@�����Ώۂ̕��X�R�[�h <BR>
     * �@@�@@�@@�����R�[�h�F�@@�p�����[�^.�ڋq�R�[�h <BR>
     * <BR>
     * �@@�Q�|�Q�j �@@�Q�|�P�j�̌��ʂ��擾�ł����ꍇ�́A <BR>
     * �@@�@@�@@��������ArrayList�ɒǉ�����B <BR>
     * �@@ <BR>
     * �R�j�@@ArrayList.toArray()�̖߂�l��ԋp����B <BR>
     * �@@��toArray()�̖߂�l.length��0�̏ꍇ�A <BR>
     * �@@�@@�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@tag : BUSINESS_ERROR_01037<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     * @@return webbroker3.gentrade.WEB3GentradeMainAccount[]
     * @@throws WEB3BaseException 
     * @@roseuid 4469593901E5
     */
    public static WEB3GentradeMainAccount[] getAccountList(
        String l_strInstitutionCode, 
        String[] l_strBranchCodes, 
        String l_strAccountCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getAccountList(String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBranchCodes == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //�P�j ArrayList�𐶐�����B  
        List l_lisAccounts = new ArrayList();
        
        //�Q�j DB����  
        //�@@�p�����[�^.���X�R�[�h[]�̗v�f�����ȉ��̏������J��Ԃ��B  
        //�@@�Q�|�P�j �g���A�J�E���g�}�l�[�W��.get�ڋq()���\�b�h���R�[������B  
        //�@@�@@[get�ڋq()�ɃZ�b�g����p�����[�^]  
        //�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h  
        //�@@�@@�@@���X�R�[�h�F�@@�����Ώۂ̕��X�R�[�h  
        //�@@�@@�@@�����R�[�h�F�@@�p�����[�^.�ڋq�R�[�h  
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountMananger = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        for (int i = 0; i < l_strBranchCodes.length; i++) 
        {
            WEB3GentradeMainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = 
                    l_accountMananger.getMainAccount(l_strInstitutionCode, l_strBranchCodes[i], l_strAccountCode);
            }
            catch (WEB3BaseException l_ex)
            {
                continue;
            }
            
            //�@@�Q�|�Q�j �@@�Q�|�P�j�̌��ʂ��擾�ł����ꍇ�́A��������ArrayList�ɒǉ�����B  
            if (l_mainAccount != null) 
            {
                l_lisAccounts.add(l_mainAccount);
            }
        }
        //�R�j�@@ArrayList.toArray()�̖߂�l��ԋp����B 
        WEB3GentradeMainAccount[] l_mainAccounts = new WEB3GentradeMainAccount[l_lisAccounts.size()];
        l_lisAccounts.toArray(l_mainAccounts);
        
        //�@@��toArray()�̖߂�l.length��0�̏ꍇ�A�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B
        if (l_mainAccounts.length == 0)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                "WEB3AdminPMEquityDataManager."+ STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_mainAccounts;
    }
    
    /**
     * (reset����J�����_�R���e�L�X�g)<BR>
     * ����J�����_�R���e�L�X�g�̒l�������̒l�ōăZ�b�g����B<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g���擾����B<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute()���R�[������B<BR>
     * <BR>
     * �@@[getAttribute()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �@@���擾�ł��Ȃ������ꍇ�́A����J�����_�R���e�L�X�g�𐶐����A<BR>
     * �@@�@@�@@�ȍ~�̏����Ŏg�p���邱�ƁB<BR>
     * <BR>
     * �Q�j�@@�擾��������J�����_�R���e�L�X�g�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@���Ή�����p�����[�^��null�̏ꍇ�́A�ăZ�b�g���s��Ȃ��B<BR>
     * �@@�@@ID�ɊY������I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A<BR>
     * �@@�@@�u�Y���f�[�^�Ȃ��v�̃V�X�e���G���[���X���[����B<BR>
     * �@@class�@@:�@@WEB3SystemLayerException<BR>
     * �@@tag�@@:�@@SYSTEM_ERROR_80005<BR>
     * <BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h = �p�����[�^.���XID�ɊY�����镔�X�R�[�h<BR>
     * �@@�@@�s��R�[�h = �p�����[�^.�s��ID�ɊY������s��R�[�h<BR>
     * �@@�@@�����R�[�h = "DEFAULT"<BR>
     * �@@�@@��t���ԋ敪 = �p�����[�^.��t���ԋ敪<BR>
     * <BR>
     * �R�j�@@����J�����_�R���e�L�X�g���ăZ�b�g����B<BR>
     * �@@ThreadLocalSystemAttributesRegistry.setAttribute()���R�[������B<BR>
     * <BR>
     * �@@[setAttribute()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * �@@�@@arg1�F�@@�v���p�e�B�Z�b�g��������J�����_�R���e�L�X�g<BR>
     * <BR>
     * �S�j�@@��t�����A���t���[���̃��Z�b�g���s���B<BR>
     * �@@������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_branchId - (���XID)<BR>
     * ���XID
     * @@param l_marketId - (�s��ID)<BR>
     * �s��ID
     * @@param l_strTradeTimeType - (��t���ԋ敪)<BR>
     * ��t���ԋ敪
     * @@throws WEB3BaseException 
     * @@roseuid 4469593901F6
     */
    public static void resetTradingCalContext(
        String l_strInstitutionCode, 
        Long l_branchId, 
        Long l_marketId, 
        String l_strTradeTimeType) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "resetTradingCalContext(String, Long, Long, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����J�����_�R���e�L�X�g���擾����B 
        //�@@ThreadLocalSystemAttributesRegistry.getAttribute()���R�[������B 
        Object l_object = 
            ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        //�@@���擾�ł��Ȃ������ꍇ�́A����J�����_�R���e�L�X�g�𐶐����A�ȍ~�̏����Ŏg�p���邱�ƁB 
        WEB3GentradeTradingClendarContext l_context = null;
        if (l_object != null)
        {
            l_context = (WEB3GentradeTradingClendarContext)l_object;
        }
        else
        {
            l_context = new WEB3GentradeTradingClendarContext();
        }
        
        //�Q�j�@@�擾��������J�����_�R���e�L�X�g�Ɉȉ��̃v���p�e�B���Z�b�g����B 
        //�@@���Ή�����p�����[�^��null�̏ꍇ�́A�ăZ�b�g���s��Ȃ��B
        //�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h 
        if (l_strInstitutionCode != null)
        {
            l_context.setInstitutionCode(l_strInstitutionCode);
        }
        
        //�@@�@@���X�R�[�h = �p�����[�^.���XID�ɊY�����镔�X�R�[�h 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        if (l_branchId != null)
        {
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            try
            {
                Branch l_branch = l_accountManager.getBranch(l_branchId.longValue());
                l_context.setBranchCode(l_branch.getBranchCode());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
        }
        
        //�@@�@@�s��R�[�h = �p�����[�^.�s��ID�ɊY������s��R�[�h 
        if (l_marketId != null)
        {
            try
            {
                WEB3GentradeFinObjectManager l_finObjManager =
                    (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket)l_finObjManager.getMarket(l_marketId.longValue());
                l_context.setMarketCode(l_market.getMarketCode());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
        }
        
        //�@@�@@�����R�[�h = "DEFAULT" 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
        //�@@�@@��t���ԋ敪 = �p�����[�^.��t���ԋ敪 
        if (l_strTradeTimeType != null) 
        {
            l_context.setTradingTimeType(l_strTradeTimeType);
        }

        //�R�j�@@����J�����_�R���e�L�X�g���ăZ�b�g����B 
        //�@@ThreadLocalSystemAttributesRegistry.setAttribute()���R�[������B 
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        //�S�j�@@��t�����A���t���[���̃��Z�b�g���s���B 
        WEB3GentradeTradingTimeManagement.setTimestamp();
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�蓮�����Ώے����P�ʈꗗ)<BR>
     * �蓮�����Ώۂ̒����P�ʂ̈ꗗ���擾����B<BR>
     * <BR>
     * �P�j�@@��������������i�FString�j�A<BR>
     * �@@���������f�[�^�R���e�i�i�FArrayList�j�𐶐�����B<BR>
     * <BR>
     * �Q�j�����������쐬����B<BR>
     * �@@�Q�|�P�j�@@�ȉ��̒������������������ɒǉ�����B<BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�@@�������� = "DEFAULT"<BR>
     * �@@�@@�@@And �����L����� = "�I�[�v��"<BR>
     * �@@�@@�@@And ������� NOT IN ("�����~�j��������", "�����~�j��������")<BR>
     * �@@�@@�@@And ����R�[�h�iSONAR�j != "����O����"<BR>
     * �@@�@@�@@And �s�ꂩ��m�F�ς݂̐��� = null<BR>
     * �@@�@@�@@And ���F��ԋ敪 != "�����F"<BR>
     * <BR>
     * �@@�@@�������������� = " and order_condition_type = ?"<BR>
     * �@@�@@�@@�@@�@@�@@�@@+ " and order_open_status = ?"<BR>
     * �@@�@@�@@�@@�@@�@@�@@+ " and order_type not in (?, ?)"<BR>
     * �@@�@@�@@�@@�@@�@@�@@+ " and sonar_traded_code <> ?"<BR>
     * �@@�@@�@@�@@�@@�@@�@@+ " and confirmed_quantity is null"<BR>
     * �@@�@@�@@�@@�@@�@@�@@+ " and ( approve_status_type <> ? or approve_status_type is null )"<BR>
     * <BR>
     * �@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     * �@@�@@�@@�E"DEFAULT�i�����w��Ȃ��j"<BR>
     * �@@�@@�@@�E"�I�[�v��"<BR>
     * �@@�@@�@@�E"�����~�j��������"<BR>
     * �@@�@@�@@�E"�����~�j��������"<BR>
     * �@@�@@�@@�E"����O����"<BR>
     * �@@�@@�@@�E"�����F"�i���F��ԋ敪�j<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@���X���������������ɒǉ�����B<BR>
     * �@@�@@�p�����[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and branch_id in (?, ?,,,) "<BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���X�R�[�h�ɊY�����镔�X.���XID��<BR>
     * �@@�@@�v�f�����Aadd����B<BR>
     * <BR>
     * �@@�@@�����X���擾����ۂɁA�p�����[�^.�،���Ђ��g�p����B<BR>
     * �@@�@@���擾���ɗ�O�ƂȂ����ꍇ�A�u�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
     * �@@�@@�@@�̋Ɩ��G���[���X���[����B<BR>
     * �@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_01037<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@�@@������������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and product_id = ?"<BR>
     * �@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     * �@@�@@�@@�E�p�����[�^.�����R�[�h�ɊY�����銔������.����ID<BR>
     * <BR>
     * �@@�@@���擾���ɗ�O�ƂȂ����ꍇ�A�u�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
     * �@@�@@�̋Ɩ��G���[���X���[����B<BR>
     * �@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_01037<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�s���������������������ɒǉ�����B<BR>
     * �@@�@@�@@�p�����[�^.�s��R�[�h�ꗗ�̗v�f����"?"��ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@����������������� += " and market_id in (?, ?,,,)"<BR>
     * �@@�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.�s��R�[�h�ꗗ�ɊY������s��.�s��ID��add����B<BR>
     * <BR>
     * �@@�Q�|�T�j�@@�����������������������ɒǉ�����B<BR>
     * �@@�@@�Q�|�T�|�P�j�@@�p�����[�^.����敪�ꗗ�ɁA���������̎�����܂܂��ꍇ�A<BR>
     * �@@�@@�@@���������������쐬����B<BR>
     * �@@�@@�@@�p�����[�^.����敪�ꗗ�̗v�f����Loop���A�����Ώۂ̗v�f��<BR>
     * �@@�@@�@@�ȉ��̎���̏ꍇ��"?"��ǉ�����B<BR>
     * �@@�@@�@@�@@�E"�������t����"<BR>
     * �@@�@@�@@�@@�E"�������t����"<BR>
     * <BR>
     * �@@�@@�@@�@@������������ = " order_type in (?,?,,,) "<BR>
     * �@@�@@�@@�@@�f�[�^�R���e�i�ɏ����Ώۂ̗v�f��ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@���f�[�^�R���e�i�͓��l�̂��̂��g�p����B<BR>
     * <BR>
     * �@@�@@�@@�Q�|�T�|�P�|�P�j�@@����������������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�������������� += " and ((" + ������������ + ")"<BR>
     * <BR>
     * �@@�@@�Q�|�T�|�Q�j�@@�p�����[�^.����敪�ꗗ�ɁA�M�p����̎�����܂܂��ꍇ�A<BR>
     * �@@�@@�@@�M�p����������쐬����B<BR>
     * �@@�@@�@@�p�����[�^.����敪�ꗗ�̗v�f����Loop���A�����Ώۂ̗v�f��<BR>
     * �@@�@@�@@�ȉ��̎���̏ꍇ��"?"��ǉ�����B<BR>
     * �@@�@@�@@�@@�E"�V�K��������"<BR>
     * �@@�@@�@@�@@�E"�V�K��������"<BR>
     * �@@�@@�@@�@@�E"�����ԍϒ���"<BR>
     * �@@�@@�@@�@@�E"�����ԍϒ���"<BR>
     * �@@�@@�@@�@@�E"��������"<BR>
     * �@@�@@�@@�@@�E"���n����"<BR>
     * <BR>
     * �@@�@@�@@�@@�M�p������� = " order_type in (?,?,,,) "<BR>
     * �@@�@@�@@�@@�f�[�^�R���e�i�ɏ����Ώۂ̗v�f��ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@���f�[�^�R���e�i�͓��l�̂��̂��g�p����B<BR>
     * <BR>
     * �@@�@@�@@�Q�|�T�|�Q�|�P�j�@@�ٍϏ�����M�p��������ɒǉ�����B<BR>
     * �@@�@@�@@�@@�p�����[�^.�ٍϋ敪�ꗗ�̗v�f����"?"��ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�M�p������� += " and repayment_type in (?, ?,,,) "<BR>
     * �@@�@@�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.�ٍϋ敪�ꗗ��v�f�����Aadd����B<BR>
     * <BR>
     * �@@�@@�@@�Q�|�T�|�Q�|�Q�j�@@�M�p�����������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@[���������������쐬���Ă���ꍇ]<BR>
     * �@@�@@�@@�@@�@@�������������� += " or (" + �M�p������� + ")"<BR>
     * <BR>
     * �@@�@@�@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�������������� += " and ((" + �M�p������� + ")"<BR>
     * <BR>
     * �@@�@@�Q�|�T�|�R�j�@@���������������")"�i���ʁj��ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += ")"<BR>
     * <BR>
     * �@@�Q�|�U�j�@@�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A<BR>
     * �@@�@@�ڋq���������������ɒǉ�����B<BR>
     * �@@�@@�Q�|�U�|�P�j�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.get�ڋq�ꗗ()��<BR>
     * �@@�@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@�@@[get�ڋq�ꗗ()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@���X�R�[�h�F�@@�p�����[�^.���X�R�[�h<BR>
     * �@@�@@�@@�@@�ڋq�R�[�h�F�@@�p�����[�^.�ڋq�R�[�h<BR>
     * <BR>
     * �@@�@@�Q�|�U�|�Q�j�@@�Q�|�U�|�P�j�̖߂�l�̗v�f�����A����������"?"���A<BR>
     * �@@�@@�@@�f�[�^�R���e�i�ɁA�e�v�f�̌���ID��ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += " and account_id in (?, ?,,,) "<BR>
     * �@@�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     * �@@�@@�@@�@@�E�Q�|�U�|�P�j�̖߂�l�̊e�v�f.����ID<BR>
     * <BR>
     * �@@�Q�|�V�j�@@�p�����[�^.����IDFrom != null�@@����<BR>
     * �@@�@@�p�����[�^.����IDTo != null�̏ꍇ�A����ID�����W������<BR>
     * �@@�@@���������ɒǉ�����B<BR>
     * �@@�@@���񓯊��������s���ꍇ�ɐݒ肳���B<BR>
     * <BR>
     * �@@�@@�������������� += " and account_id >= ?"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@+ " and account_id <= ?"<BR>
     * �@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     * �@@�@@�@@�E�p�����[�^.����IDFrom<BR>
     * �@@�@@�@@�E�p�����[�^.����IDTo<BR>
     * <BR>
     * �R�j�@@DB����������B<BR>
     * �@@�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@���������P��Row.TYPE <BR>
     * �@@�@@�@@arg1�F�@@�쐬������������������<BR>
     * �@@�@@�@@arg2�F�@@�쐬�����f�[�^�R���e�i.toArray()�̖߂�l<BR>
     * <BR>
     * �@@���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �S�j�@@�L���[�e�[�u������������������쐬����B<BR>
     * �@@�S�|�P�j�@@��{�̌�������������A�f�[�^�R���e�i���쐬����B<BR>
     * �@@�@@���f�[�^�R���e�i�́A�ȑO�̂��̂��g�p����ꍇ�A�K���v�f��S��clear���邱�ƁB<BR>
     * �@@�@@[��{����]<BR>
     * �@@�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@�����敪 = "������"<BR>
     * <BR>
     * �@@�@@�������������� = "institution_code = ?"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@+ " and status = ?"<BR>
     * �@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     * �@@�@@�@@�E�p�����[�^.�،����<BR>
     * �@@�@@�@@�E"������"<BR>
     * <BR>
     * �@@�S�|�Q�j�@@���X���������������ɒǉ�����B<BR>
     * �@@�@@�p�����[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and branch_code in (?, ?,,,)"<BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���X�R�[�h�̑S�v�f��ǉ�����B<BR>
     * <BR>
     * �@@�S�|�R�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@�@@������������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and product_code = ?"<BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.�����R�[�h��ǉ�����B<BR>
     * <BR>
     * �@@�S�|�S�j�@@�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A<BR>
     * �@@�@@�ڋq��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�S�|�S�|�P�j�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.get�ڋq�ꗗ()��<BR>
     * �@@�@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@�@@[get�ڋq�ꗗ()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@���X�R�[�h�F�@@�p�����[�^.���X�R�[�h<BR>
     * �@@�@@�@@�@@�ڋq�R�[�h�F�@@�p�����[�^.�ڋq�R�[�h<BR>
     * <BR>
     * �@@�@@�S�|�S�|�Q�j�@@�S�|�S�|�P�j�̖߂�l�̗v�f�����A����������"?"���A<BR>
     * �@@�@@�@@�f�[�^�R���e�i�ɁA�e�v�f�̌����R�[�h��ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += " and account_code in (?, ?,,,) "<BR>
     * �@@�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     * �@@�@@�@@�@@�E�S�|�S�|�P�j�̖߂�l�̊e�v�f.�����R�[�h<BR>
     * <BR>
     * �T�j�@@���ʃR�[�h���X�g�i�FArrayList�j�𐶐�����B<BR>
     * <BR>
     * �U�j�@@�p�����[�^.����敪�ꗗ�ɁA�ȉ��̎���̂����ꂩ���܂܂��ꍇ�A<BR>
     * �@@������������L���[�e�[�u������������B<BR>
     * �@@�@@�E"�������t����"<BR>
     * �@@�@@�E"�������t����"<BR>
     * �@@�@@�E"�V�K��������"<BR>
     * �@@�@@�E"�V�K��������"<BR>
     * �@@�@@�E"�����ԍϒ���"<BR>
     * �@@�@@�E"�����ԍϒ���"<BR>
     * <BR>
     * �@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@������������L���[Row.TYPE <BR>
     * �@@�@@�@@arg1�F�@@�쐬������������������<BR>
     * �@@�@@�@@arg2�F�@@�쐬�����f�[�^�R���e�i.toArray()�̖߂�l<BR>
     * <BR>
     * �@@�������ʂ��擾�ł����ꍇ�A�������ʂ̊e�v�f.���ʃR�[�h��<BR>
     * �@@���ʃR�[�h���X�g�ɒǉ�����B<BR>
     * <BR>
     * �V�j�@@�p�����[�^.����敪�ꗗ��"��������"�܂���"���n����"��<BR>
     * �@@�܂܂��ꍇ�A�������n�L���[�e�[�u������������B<BR>
     * �@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@�������n�L���[Row.TYPE <BR>
     * �@@�@@�@@arg1�F�@@�쐬������������������<BR>
     * �@@�@@�@@arg2�F�@@�쐬�����f�[�^�R���e�i.toArray()�̖߂�l<BR>
     * <BR>
     * �@@�������ʂ��擾�ł����ꍇ�A�������ʂ̊e�v�f.���ʃR�[�h��<BR>
     * �@@���ʃR�[�h���X�g�ɒǉ�����B<BR>
     * <BR>
     * �W�j�@@�����Ώے������X�g�i�FArrayList�j�𐶐�����B<BR>
     * <BR>
     * �X�j�@@�����Ώے������m�肷��B<BR>
     * �@@�R�j�̖߂�l�̗v�f�����ALoop�������s���A<BR>
     * �@@�����Ώۂ̗v�f.���ʃR�[�h�����ʃR�[�h���X�g�Ɋ܂܂��ꍇ�A<BR>
     * �@@�����Ώے������X�g�ɏ����Ώۂ̗v�f��ǉ�����B<BR>
     * <BR>
     * �@@�����ʃR�[�h���X�g.size() == 0�̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �P�O�j�@@�����Ώے������X�g.toArray()�̖߂�l��ԋp����B<BR>
     * �@@�������Ώے������X�g.size() == 0�̏ꍇ�Anull��ԋp����B<BR>
     * @@param l_institution - (�،����)<BR>
     * �،����
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h
     * @@param l_strMarketList - (�s��R�[�h�ꗗ)<BR>
     * ���X�R�[�h�̔z��
     * @@param l_strTradingTypeList - (����敪�ꗗ)<BR>
     * ����敪�ꗗ
     * @@param l_strRepaymentDivList - (�ٍϋ敪�ꗗ)<BR>
     * �ٍϋ敪�ꗗ
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     * @@param l_accountIdFrom - (����IDFrom)<BR>
     * ����IDFrom
     * @@param l_accountIdTo - (����IDTo)<BR>
     * ����IDTo
     * @@return EqtypeOrderUnitRow[]
     * @@throws WEB3BaseException 
     * @@roseuid 44695939020C
     */
    public static EqtypeOrderUnitRow[] getManualExpireOrderUnits(
        WEB3GentradeInstitution l_institution, 
        String[] l_strBranchCodes, 
        String l_strProductCode, 
        String[] l_strMarketList, 
        String[] l_strTradingTypeList, 
        String[] l_strRepaymentDivList, 
        String l_strAccountCode, 
        Long l_accountIdFrom, 
        Long l_accountIdTo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getManualExpireOrderUnits(" 
            + "WEB3GentradeInstitution, String[], String, String[], String[], String[], String, Long, Long)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //�P�j�@@��������������i�FString�j�A 
        //�@@���������f�[�^�R���e�i�i�FArrayList�j�𐶐�����B 
        List l_lisQueryContainers = new ArrayList();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        //�Q�j�����������쐬����B 
        //�@@�Q�|�P�j�@@�ȉ��̒������������������ɒǉ�����B 
        //�@@�@@[��������] 
        //�@@�@@�@@�������� = "DEFAULT" 
        //�@@�@@�@@And �����L����� = "�I�[�v��" 
        //�@@�@@�@@And ������� NOT IN ("�����~�j��������", "�����~�j��������") 
        //�@@�@@�@@And ����R�[�h�iSONAR�j != "����O����" 
        //�@@�@@�@@And �s�ꂩ��m�F�ς݂̐��� = null 
        //�@@�@@�������������� = " and order_condition_type = ?" 
        //�@@�@@�@@�@@�@@�@@�@@+ " and order_open_status = ?" 
        //�@@�@@�@@�@@�@@�@@�@@+ " and order_type not in (?, ?)" 
        //�@@�@@�@@�@@�@@�@@�@@+ " and sonar_traded_code <> ?" 
        //�@@�@@�@@�@@�@@�@@�@@+ " and confirmed_quantity is null" 
        //             + " and ( approve_status_type <> ? or approve_status_type is null )"
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append("order_condition_type = ?");
        l_sbQueryString.append(" and order_open_status = ?");
        l_sbQueryString.append(" and order_type not in (?, ?)");
        l_sbQueryString.append(" and sonar_traded_code <> ?");
        l_sbQueryString.append(" and confirmed_quantity is null");
        l_sbQueryString.append(" and ( approve_status_type <> ? or approve_status_type is null )");
        
        //�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B)
        //�@@�@@�@@�E"DEFAULT�i�����w��Ȃ��j" 
        l_lisQueryContainers.add(WEB3OrderingConditionDef.DEFAULT);
        
        //�@@�@@�@@�E"�I�[�v��" 
        l_lisQueryContainers.add(OrderOpenStatusEnum.OPEN);
        
        //�@@�@@�@@�E"�����~�j��������" 
        l_lisQueryContainers.add(OrderTypeEnum.MINI_STOCK_BUY);
        
        //�@@�@@�@@�E"�����~�j��������" 
        l_lisQueryContainers.add(OrderTypeEnum.MINI_STOCK_SELL);
        
        //�@@�@@�@@�E"����O����" 
        l_lisQueryContainers.add(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);

        //�@@�@@�@@�E"�����F"�i���F��ԋ敪�j
        l_lisQueryContainers.add(WEB3ApproveStatusType.UNAPPROVED);

        //�@@�Q�|�Q�j�@@���X���������������ɒǉ�����B 
        //�@@�@@�p�����[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B 
        if (l_strBranchCodes != null && l_strBranchCodes.length != 0)
        {
            // �������������� += " and branch_id in (?, ?,,,) " 
            StringBuffer l_sbBranchIdForQuery = new StringBuffer();
            l_sbBranchIdForQuery.append(" and branch_id in (");
            try 
            {
                for (int i = 0; i < l_strBranchCodes.length; i++) 
                {
                    l_sbBranchIdForQuery.append("?,");
                    
                    //�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���X�R�[�h�ɊY�����镔�X.���XID��v�f�����Aadd����B
                    Branch l_branch = l_accountManager.getBranch(l_institution, l_strBranchCodes[i]);
                    l_lisQueryContainers.add(new Long(l_branch.getBranchId()));
                }
            }
            catch (NotFoundException l_ex)
            {
                //�@@�@@�����X���擾����ۂɁA�p�����[�^.�،���Ђ��g�p����B 
                //�@@�@@���擾���ɗ�O�ƂȂ����ꍇ�A�u�����ɊY������f�[�^�����݂��Ȃ��B�v�̋Ɩ��G���[���X���[����B 
                log.error("�����ɊY������f�[�^�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "�����ɊY������f�[�^�����݂��Ȃ��B");
            }
            
            l_sbQueryString.append(l_sbBranchIdForQuery.substring(0, l_sbBranchIdForQuery.length() - 1));
            l_sbQueryString.append(")");
        }

        //�@@�Q�|�R�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A 
        //�@@�@@������������������������ɒǉ�����B 
        if (l_strProductCode != null) 
        {
            try 
            {
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityProductManager l_equityProductManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                EqTypeProduct l_eqTypeProduct  =
                    l_equityProductManager.getProduct(l_institution, l_strProductCode);
           
                // �������������� += " and product_id = ?"
                l_sbQueryString.append(" and product_id = ?");
                
                //�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j 
                //�@@�@@�@@�E�p�����[�^.�����R�[�h�ɊY�����銔������.����ID 
                l_lisQueryContainers.add(String.valueOf(l_eqTypeProduct.getProductId()));
            }
            catch (NotFoundException l_ex)
            {
                //�@@�@@���擾���ɗ�O�ƂȂ����ꍇ�A�u�����ɊY������f�[�^�����݂��Ȃ��B�v�̋Ɩ��G���[���X���[����B 
                log.error("�����ɊY������f�[�^�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "�����ɊY������f�[�^�����݂��Ȃ��B");
            }
        }

        //�@@�Q�|�S�j�@@�s���������������������ɒǉ�����B  
        if (l_strMarketList != null && l_strMarketList.length != 0)
        {
            StringBuffer l_sbMarketForQuery = new StringBuffer();
            l_sbMarketForQuery.append(" and market_id in (");
            
            try 
            {
                WEB3GentradeFinObjectManager l_finObjManager =
                    (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                
                for (int i = 0; i < l_strMarketList.length; i ++)
                {
                    //�@@�@@�@@����������������� += " and market_id in (?, ?,,,)" 
                    l_sbMarketForQuery.append("?,");
                    
                    WEB3GentradeMarket l_market =
                        (WEB3GentradeMarket)l_finObjManager.getMarket(l_institution, l_strMarketList[i]);
                    
                    //�@@�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.�s��R�[�h�ꗗ�ɊY������s��.�s��ID��add����B 
                    l_lisQueryContainers.add(String.valueOf(l_market.getMarketId()));
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("�����ɊY������f�[�^�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "�����ɊY������f�[�^�����݂��Ȃ��B");
            }
            
            l_sbQueryString.append(l_sbMarketForQuery.substring(0, l_sbMarketForQuery.length() - 1));
            l_sbQueryString.append(")");
        }
        
        //�@@�Q�|�T�j�@@�����������������������ɒǉ�����B 
        //�@@�@@�Q�|�T�|�P�j�@@�p�����[�^.����敪�ꗗ�ɁA���������̎�����܂܂��ꍇ�A 
        //�@@�@@�@@���������������쐬����B 
        //�@@�@@�@@�p�����[�^.����敪�ꗗ�̗v�f����Loop���A�����Ώۂ̗v�f�� 
        //�@@�@@�@@�ȉ��̎���̏ꍇ��"?"��ǉ�����B 
        //�@@�@@�@@�@@�E"�������t����" 
        //�@@�@@�@@�@@�E"�������t����" 
        //�@@�@@�@@�@@������������ = " order_type in (?,?,,,) " 
        //�@@�@@�@@�@@�f�[�^�R���e�i�ɏ����Ώۂ̗v�f��ǉ�����B  �@@�@@
        boolean l_blnEquityFlag = false;
        if (l_strTradingTypeList != null && l_strTradingTypeList.length != 0) 
        {
            StringBuffer l_sbEquityForQuery = new StringBuffer();
            l_sbEquityForQuery.append(" order_type in (");
            for (int i = 0; i < l_strTradingTypeList.length; i++) 
            {
                if (String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbEquityForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.EQUITY_BUY);
                    l_blnEquityFlag = true;
                }
                else if (String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbEquityForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.EQUITY_SELL);
                    l_blnEquityFlag = true;
                }
            }
            
            //�Q�|�T�|�P�|�P�j�@@����������������������������ɒǉ�����B 
            // �������������� += " and ((" + ������������ + ")" 
            if (l_blnEquityFlag)
            {
                l_sbQueryString.append(" and ((");
                l_sbQueryString.append(l_sbEquityForQuery.substring(0, l_sbEquityForQuery.length() -1) + ")");
                l_sbQueryString.append(")");    
            }
                
            //�@@�@@�Q�|�T�|�Q�j�@@�p�����[�^.����敪�ꗗ�ɁA�M�p����̎�����܂܂��ꍇ�A 
            //�@@�@@�@@�M�p����������쐬����B 
            //�@@�@@�@@�p�����[�^.����敪�ꗗ�̗v�f����Loop���A�����Ώۂ̗v�f�� 
            //�@@�@@�@@�ȉ��̎���̏ꍇ��"?"��ǉ�����B 
            //�@@�@@�@@�@@�E"�V�K��������" 
            //�@@�@@�@@�@@�E"�V�K��������" 
            //�@@�@@�@@�@@�E"�����ԍϒ���" 
            //�@@�@@�@@�@@�E"�����ԍϒ���" 
            //�@@�@@�@@�@@�E"��������" 
            //�@@�@@�@@�@@�E"���n����" 
            //�@@�@@�@@�@@�M�p������� = " order_type in (?,?,,,) " 
            //�@@�@@�@@�@@�f�[�^�R���e�i�ɏ����Ώۂ̗v�f��ǉ�����B  
            StringBuffer l_sbMarginForQuery = new StringBuffer();
            l_sbMarginForQuery.append(" order_type in (");
            boolean l_blnMarginFlag = false;
            for (int i = 0; i < l_strTradingTypeList.length; i++)
            {
                if (String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbMarginForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.MARGIN_LONG);
                    l_blnMarginFlag = true;
                }
                else if (String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbMarginForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.MARGIN_SHORT);
                    l_blnMarginFlag = true;
                }
                else if (String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbMarginForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.CLOSE_MARGIN_LONG);
                    l_blnMarginFlag = true;
                }
                else if (String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbMarginForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.CLOSE_MARGIN_SHORT);
                    l_blnMarginFlag = true;
                }
                else if (String.valueOf(OrderTypeEnum.SWAP_MARGIN_LONG.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbMarginForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.SWAP_MARGIN_LONG);
                    l_blnMarginFlag = true;
                }
                else if (String.valueOf(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbMarginForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.SWAP_MARGIN_SHORT);
                    l_blnMarginFlag = true;
                }
            }
            
            StringBuffer l_sbMarginRepayment = new StringBuffer();
            
            if (l_blnMarginFlag)
            {
                l_sbMarginRepayment.append(l_sbMarginForQuery.substring(0, l_sbMarginForQuery.length() -1));
                l_sbMarginRepayment.append(")");          
        
                //�@@�@@�@@�Q�|�T�|�Q�|�P�j�@@�ٍϏ�����M�p��������ɒǉ�����B 
                //�@@�@@�@@�@@�p�����[�^.�ٍϋ敪�ꗗ�̗v�f����"?"��ǉ�����B 
                //�@@�@@�@@�@@�M�p������� += " and repayment_type in (?, ?,,,) " 
                //�@@�@@�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.�ٍϋ敪�ꗗ��v�f�����Aadd����B 
                if (l_strRepaymentDivList != null && l_strRepaymentDivList.length != 0) 
                {
                    StringBuffer l_sbRepaymentForQuery = new StringBuffer();
                    l_sbRepaymentForQuery.append(" and repayment_type in (");
                    for (int i = 0; i < l_strRepaymentDivList.length; i++) 
                    {
                        l_sbRepaymentForQuery.append("?,");
                        l_lisQueryContainers.add(l_strRepaymentDivList[i]);
                    }
                    
                    l_sbMarginRepayment.append(l_sbRepaymentForQuery.substring(0, l_sbRepaymentForQuery.length() -1));
                    l_sbMarginRepayment.append(")");
                }
                
                //�@@�@@�@@�Q�|�T�|�Q�|�Q�j�@@�M�p�����������������������ɒǉ�����B 
                //�@@�@@�@@�@@[���������������쐬���Ă���ꍇ] 
                //�@@�@@�@@�@@�@@�������������� += " or (" + �M�p������� + ")" 
                if (l_blnEquityFlag)
                {
                    l_sbQueryString.append(" or (");
                    l_sbQueryString.append(l_sbMarginRepayment);
                    l_sbQueryString.append(")");
                }
                
                //�@@�@@�@@�@@[��L�ȊO�̏ꍇ] 
                //�@@�@@�@@�@@�@@�������������� += " and ((" + �M�p������� + ")" 
                else
                {
                    l_sbQueryString.append(" and ((");
                    l_sbQueryString.append(l_sbMarginRepayment);
                    l_sbQueryString.append(")"); 
                }
            }
            
            //�@@�@@�Q�|�T�|�R�j�@@���������������")"�i���ʁj��ǉ�����B 
            l_sbQueryString.append(")");
        }
        
        //�@@�Q�|�U�j�@@�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A 
        //�@@�@@�ڋq���������������ɒǉ�����B 
        if (l_strAccountCode != null)
        {
            //�@@�@@�Q�|�U�|�P�j�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.get�ڋq�ꗗ()�� 
            //�@@�@@�@@�R�[������B 
            //�@@�@@�@@[get�ڋq�ꗗ()�Ɏw�肷�����] 
            //�@@�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،����.�،���ЃR�[�h 
            //�@@�@@�@@�@@���X�R�[�h�F�@@�p�����[�^.���X�R�[�h 
            //�@@�@@�@@�@@�ڋq�R�[�h�F�@@�p�����[�^.�ڋq�R�[�h 
            WEB3GentradeMainAccount[] l_mainAccounts = 
                WEB3AdminPMEquityDataManager.getAccountList(
                    l_institution.getInstitutionCode(), 
                    l_strBranchCodes, 
                    l_strAccountCode);
            
            //�@@�@@�Q�|�U�|�Q�j�@@�Q�|�U�|�P�j�̖߂�l�̗v�f�����A����������"?"���A 
            //�@@�@@�@@�f�[�^�R���e�i�ɁA�e�v�f�̌���ID��ǉ�����B �@@�@@�@@
            if (l_mainAccounts != null && l_mainAccounts.length != 0) 
            {
                //�@@�������������� += " and account_id in (?, ?,,,) " 
                StringBuffer l_sbAccountIdForQuery = new StringBuffer();
                l_sbAccountIdForQuery.append(" and account_id in (");
                for (int i = 0; i < l_mainAccounts.length; i++) 
                {
                    l_sbAccountIdForQuery.append("?,");
                    
                    //�@@�E�Q�|�U�|�P�j�̖߂�l�̊e�v�f.����ID 
                    l_lisQueryContainers.add(String.valueOf(l_mainAccounts[i].getAccountId()));
                }
                
                l_sbQueryString.append(l_sbAccountIdForQuery.substring(0, l_sbAccountIdForQuery.length() - 1));
                l_sbQueryString.append(")");
            }
        }
        
        //�@@�Q�|�V�j�@@�p�����[�^.����IDFrom != null�@@���� 
        //�@@�@@�p�����[�^.����IDTo != null�̏ꍇ�A����ID�����W������ 
        //�@@�@@���������ɒǉ�����B 
        //�@@�@@���񓯊��������s���ꍇ�ɐݒ肳���B �@@�@@
        if (l_accountIdFrom != null && l_accountIdTo != null) 
        {
            //�@@�@@�������������� += " and account_id >= ?" + " and account_id <= ?" 
            l_sbQueryString.append(" and account_id >= ?");
            l_sbQueryString.append(" and account_id <= ?");
            
            //�E�p�����[�^.����IDFrom 
            l_lisQueryContainers.add(l_accountIdFrom);
            
            //�E�p�����[�^.����IDTo 
            l_lisQueryContainers.add(l_accountIdTo);
        }
        
        //�R�j�@@DB����������B 
        //�@@�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B 
        QueryProcessor l_queryProcessor = null;
        List l_lisRows = null;
        Object[] l_queryContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_queryContainers);
        try 
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            
            //�@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] 
            //�@@�@@�@@arg0�F�@@���������P��Row.TYPE  
            //�@@�@@�@@arg1�F�@@�쐬������������������ 
            //�@@�@@�@@arg2�F�@@�쐬�����f�[�^�R���e�i.toArray()�̖߂�l 
            l_lisRows = l_queryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbQueryString.toString(),
                    l_queryContainers);
            
            // ���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
            if (l_lisRows == null || l_lisRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }
        catch (DataQueryException l_dqex)
        {
            log.error("DB�A�N�Z�X�G���[", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataNetworkException l_dnex)
        {
            log.error("DB�A�N�Z�X�G���[", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
        }
         
        //�S�j�@@�L���[�e�[�u������������������쐬����B 
        //�@@�S�|�P�j�@@��{�̌�������������A�f�[�^�R���e�i���쐬����B 
        //�@@�@@���f�[�^�R���e�i�́A�ȑO�̂��̂��g�p����ꍇ�A�K���v�f��S��clear���邱�ƁB 
        //�@@�@@[��{����] 
        //�@@�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h 
        //�@@�@@�@@�����敪 = "������" 
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisEQTypeQueryContainers = new ArrayList();
        
        //�@@�@@�������������� = "institution_code = ?" + " and status = ?" 
        l_sbQuery.append("institution_code = ?");
        l_sbQuery.append(" and status = ?");
        
        //�E�p�����[�^.�،���� 
        l_lisEQTypeQueryContainers.add(l_institution.getInstitutionCode());

        //�E"������" 
        l_lisEQTypeQueryContainers.add(WEB3FrontOrderStatusDef.NOT_DEAL);
        
        //�@@�S�|�Q�j�@@���X���������������ɒǉ�����B 
        //�@@�@@�p�����[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B 
        if (l_strBranchCodes != null && l_strBranchCodes.length != 0) 
        {
            //�@@�@@�������������� += " and branch_code in (?, ?,,,)" 
            //�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���X�R�[�h�̑S�v�f��ǉ�����B
            StringBuffer l_sbBranchCodeForQuery = new StringBuffer();
            l_sbBranchCodeForQuery.append(" and branch_code in (");
            for (int i = 0; i < l_strBranchCodes.length; i++) 
            {
                l_sbBranchCodeForQuery.append("?,");
                l_lisEQTypeQueryContainers.add(l_strBranchCodes[i]);
            }
            
            l_sbQuery.append(l_sbBranchCodeForQuery.substring(0, l_sbBranchCodeForQuery.length() - 1));
            l_sbQuery.append(")");
        }
        
        //�@@�S�|�R�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A 
        //�@@�@@������������������������ɒǉ�����B   
        if (l_strProductCode != null)
        {
            // �������������� += " and product_code = ?" 
            l_sbQuery.append(" and product_code = ?");
            
            // �f�[�^�R���e�i�Ƀp�����[�^.�����R�[�h��ǉ�����B
            l_lisEQTypeQueryContainers.add(l_strProductCode);
        }
        
        //�@@�S�|�S�j�@@�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A 
        //�@@�@@�ڋq��������������������ɒǉ�����B 
        //�@@�@@�S�|�S�|�P�j�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.get�ڋq�ꗗ()�� 
        //�@@�@@�@@�R�[������B 
        if (l_strAccountCode != null) 
        {
            WEB3GentradeMainAccount[] l_mainAccounts = 
                WEB3AdminPMEquityDataManager.getAccountList(
                    l_institution.getInstitutionCode(), 
                    l_strBranchCodes, 
                    l_strAccountCode);
            
            //�@@�@@�S�|�S�|�Q�j�@@�S�|�S�|�P�j�̖߂�l�̗v�f�����A����������"?"���A 
            //�@@�@@�@@�f�[�^�R���e�i�ɁA�e�v�f�̌����R�[�h��ǉ�����B 
            if (l_mainAccounts != null && l_mainAccounts.length != 0) 
            {
                //�������������� += " and account_code in (?, ?,,,) "
                //�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j 
                //�@@�@@�@@�@@�E�S�|�S�|�P�j�̖߂�l�̊e�v�f.�����R�[�h 
                StringBuffer l_sbAccountsForQuery = new StringBuffer();
                l_sbAccountsForQuery.append(" and account_code in (");
                
                for (int i = 0; i < l_mainAccounts.length; i++) 
                {
                    l_sbAccountsForQuery.append("?,");
                    l_lisEQTypeQueryContainers.add(l_mainAccounts[i].getAccountCode());
                }
                
                l_sbQuery.append(l_sbAccountsForQuery.substring(0, l_sbAccountsForQuery.length() - 1));
                l_sbQuery.append(")");
            }
        }
        
        //�T�j�@@���ʃR�[�h���X�g�i�FArrayList�j�𐶐�����B 
        List l_lisOrderRequestNumbers = new ArrayList();
        
        //�U�j�@@�p�����[�^.����敪�ꗗ�ɁA�ȉ��̎���̂����ꂩ���܂܂��ꍇ�A 
        //�@@������������L���[�e�[�u������������B 
        //�@@�@@�E"�������t����" 
        //�@@�@@�E"�������t����" 
        //�@@�@@�E"�V�K��������" 
        //�@@�@@�E"�V�K��������" 
        //�@@�@@�E"�����ԍϒ���" 
        //�@@�@@�E"�����ԍϒ���" 
        if (l_strTradingTypeList != null && l_strTradingTypeList.length != 0)
        {
            boolean l_blnTradingTypeFlag = false;
            for (int i = 0; i < l_strTradingTypeList.length; i++) 
            {
                if (String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue()).equals(l_strTradingTypeList[i]))
                {
                    l_blnTradingTypeFlag = true;
                    break;
                }
            }
            if (l_blnTradingTypeFlag) 
            {
                //�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B 
                QueryProcessor l_eqtypeEqueryProcessor =null;
                List l_lisEqtypeRows = null;
                Object[] l_eqTypeQueryContainers = new Object[l_lisEQTypeQueryContainers.size()];
                l_lisEQTypeQueryContainers.toArray(l_eqTypeQueryContainers);
                try
                {
                    l_eqtypeEqueryProcessor = Processors.getDefaultProcessor();
                    l_lisEqtypeRows = l_eqtypeEqueryProcessor.doFindAllQuery(
                        HostEqtypeOrderAllRow.TYPE,
                        l_sbQuery.toString(),
                        l_eqTypeQueryContainers);
                }
                catch (DataQueryException l_dqex)
                {
                    log.error("DB�A�N�Z�X�G���[", l_dqex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                        l_dqex.getMessage(),
                        l_dqex);
                }
                catch (DataNetworkException l_dnex)
                {
                    log.error("DB�A�N�Z�X�G���[", l_dnex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                        l_dnex.getMessage(),
                        l_dnex);
                }
                if (!l_lisEqtypeRows.isEmpty())
                {
                    for (int i = 0; i < l_lisEqtypeRows.size(); i++) 
                    {
                        //�@@�������ʂ��擾�ł����ꍇ�A�������ʂ̊e�v�f.���ʃR�[�h�� 
                        //�@@���ʃR�[�h���X�g�ɒǉ�����B 
                        HostEqtypeOrderAllRow l_row = (HostEqtypeOrderAllRow)l_lisEqtypeRows.get(i);
                        l_lisOrderRequestNumbers.add(l_row.getOrderRequestNumber());
                    }
                    
                }
            }
        
            //�V�j�@@�p�����[�^.����敪�ꗗ��"��������"�܂���"���n����"�� 
            //�@@�܂܂��ꍇ�A�������n�L���[�e�[�u������������B 
            //�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B 
            boolean l_blnSwapFlag = false;
            for (int i = 0; i < l_strTradingTypeList.length; i++) 
            {
                if (String.valueOf(OrderTypeEnum.SWAP_MARGIN_LONG.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue()).equals(l_strTradingTypeList[i]))
                {
                    l_blnSwapFlag = true;
                    break;
                }
            }
            if (l_blnSwapFlag)
            {
                QueryProcessor l_swapQueryProcessor = null;
                List l_lisSwapRows = null;
                Object[] l_eqTypeQueryContainers = new Object[l_lisEQTypeQueryContainers.size()];
                l_lisEQTypeQueryContainers.toArray(l_eqTypeQueryContainers);
                try 
                {
                    l_swapQueryProcessor = Processors.getDefaultProcessor();
                    l_lisSwapRows = l_swapQueryProcessor.doFindAllQuery(
                        HostEqtypeSwapRow.TYPE,
                        l_sbQuery.toString(),
                        l_eqTypeQueryContainers);
                }
                catch (DataQueryException l_dqex)
                {
                    log.error("DB�A�N�Z�X�G���[", l_dqex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                        l_dqex.getMessage(),
                        l_dqex);
                }
                catch (DataNetworkException l_dnex)
                {
                    log.error("DB�A�N�Z�X�G���[", l_dnex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                        l_dnex.getMessage(),
                        l_dnex);
                }
                if (!l_lisSwapRows.isEmpty())
                {
                    for (int i = 0; i < l_lisSwapRows.size(); i++) 
                    {
                        //�@@�������ʂ��擾�ł����ꍇ�A�������ʂ̊e�v�f.���ʃR�[�h�� 
                        //�@@���ʃR�[�h���X�g�ɒǉ�����B 
                        HostEqtypeSwapRow l_row = (HostEqtypeSwapRow)l_lisSwapRows.get(i);
                        l_lisOrderRequestNumbers.add(l_row.getOrderRequestNumber());
                    }
                }
            }
        
        }
        
        //�W�j�@@�����Ώے������X�g�i�FArrayList�j�𐶐�����B 
        List l_lisLapseTargetOrder = new ArrayList();
        
        //�����ʃR�[�h���X�g.size() == 0�̏ꍇ�Anull��ԋp����B
        if (l_lisOrderRequestNumbers.size() == 0) 
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�X�j�@@�����Ώے������m�肷��B 
        //�@@�R�j�̖߂�l�̗v�f�����ALoop�������s���A 
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_lisRows.get(i);
            for (int j = 0; j < l_lisOrderRequestNumbers.size(); j++) 
            {
                //�@@�����Ώۂ̗v�f.���ʃR�[�h�����ʃR�[�h���X�g�Ɋ܂܂��ꍇ�A 
                //�@@�����Ώے������X�g�ɏ����Ώۂ̗v�f��ǉ�����B
                if (l_eqtypeOrderUnitRow.getOrderRequestNumber().equals(l_lisOrderRequestNumbers.get(j)))
                {
                    l_lisLapseTargetOrder.add(l_eqtypeOrderUnitRow);
                }
            }
        }
        
        //�������Ώے������X�g.size() == 0�̏ꍇ�Anull��ԋp����B
        if (l_lisLapseTargetOrder.size() == 0) 
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�P�O�j�@@�����Ώے������X�g.toArray()�̖߂�l��ԋp����B
        EqtypeOrderUnitRow[] l_rows = new EqtypeOrderUnitRow[l_lisLapseTargetOrder.size()];
        l_lisLapseTargetOrder.toArray(l_rows);
        log.exiting(STR_METHOD_NAME);
        return l_rows;

    }

    /**
     * (get�������ϒ����ꗗ)<BR>
     * �����̃��N�G�X�g�f�[�^�ɂĎw�肳�ꂽ�����ɊY������<BR>
     * �������ϒ����̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�j�@@��������������i�FString�j�A���������f�[�^�R���e�i�i�FArrayList�j��<BR>
     * �@@��������B<BR>
     * <BR>
     * �Q�j�������ϒ������擾���錟���������쐬����B<BR>
     * �@@�Q�|�P�j�@@�������ϒ����̂ݎ擾���錟��������ǉ�����B<BR>
     * �@@�@@�������������� = "forced_settle_reason_type is not null"<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@���X���������������ɒǉ�����B<BR>
     *�@@ �@@�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h�ꗗ�̗v�f����"?"��ǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and branch_id in (?, ?,,,) "<BR>
     * �@@�@@�f�[�^�R���e�i��this.get���XID�ꗗ()�̖߂�l��<BR>
     * �@@�@@�v�f�����A�ǉ�����B<BR>
     * <BR>
     * �@@�@@[get���XID�ꗗ()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،����.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�ꗗ�F�@@�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h�ꗗ<BR>
     * <BR>
     * �@@�@@�@@��get���XID�ꗗ()�̖߂�l == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�����ɊY������f�[�^�����݂��Ȃ��B�v�̋Ɩ��G���[���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01037<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�p�����[�^.���N�G�X�g�f�[�^.������ != null�̏ꍇ�A<BR>
     * �@@�@@���������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and biz_date = ?"<BR>
     * <BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.��������YYYYMMDD�`����<BR>
     * �@@�@@�ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h != null�̏ꍇ�A <BR>
     * �@@�@@�ڋq���������������ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�������������� += " and account_code = ?" <BR>
     * <BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h��ǉ�����B <BR>
     * <BR>
     * �@@�Q�|�T�j�@@�p�����[�^.���N�G�X�g�f�[�^.�����R�[�h != null�̏ꍇ�A <BR>
     * �@@�@@������������������������ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�������������� += " and product_code = ?" <BR>
     * <BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�����R�[�h��ǉ�����B <BR>
     * <BR>
     * �@@�Q�|�U�j�@@�p�����[�^.���N�G�X�g�f�[�^.���F��� != null�̏ꍇ�A<BR>
     * �@@�@@���F��ԏ��������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and approve_status_type = ?"<BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.���F��Ԃ�ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�V�j�@@�p�����[�^.���N�G�X�g�f�[�^.���F�҃R�[�h != null�̏ꍇ�A<BR>
     * �@@�@@���F�҃R�[�h���������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and approver_code = ?"<BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.���F�҃R�[�h��ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�W�j�@@�p�����[�^.���N�G�X�g�f�[�^.�쐬����From != null�̏ꍇ�A<BR>
     * �@@�@@�쐬����From�����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and created_timestamp >= ?"<BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�쐬����From��ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�X�j�@@�p�����[�^.���N�G�X�g�f�[�^.�쐬����To != null�̏ꍇ�A<BR>
     * �@@�@@�쐬����To�����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and created_timestamp <= ?"<BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�쐬����To��ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�P�O�j�@@�p�����[�^.���N�G�X�g�f�[�^.���F����From != null�̏ꍇ�A<BR>
     * �@@�@@���F����From�����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and approve_timestamp >= ?"<BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.���F����From��ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�P�P�j�@@�p�����[�^.���N�G�X�g�f�[�^.���F����To != null�̏ꍇ�A<BR>
     * �@@�@@���F����To�����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and approve_timestamp <= ?"<BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.���F����To��ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�P�Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.�������ϗ��R != null�̏ꍇ�A <BR>
     * �@@�@@�������ϗ��R���������������ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�Q�|�P�Q�|�P�j�@@�p�����[�^.���N�G�X�g�f�[�^.�������ϗ��R == <BR>
     * �@@�@@"�Ǐ�(���)��������"�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�������������� += " and forced_settle_reason_type = in(? ,?)" <BR>
     * �@@�@@�@@�f�[�^�R���e�i��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�y�x�j"�A <BR>
     * �@@�@@�@@�@@"�ۏ؋��ێ�������i�I�����C���J�n�O�E�d�x�j" ��ǉ�����B <BR>
     * <BR>
     * �@@�@@�Q�|�P�Q�|�Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.�������ϗ��R == <BR>
     * �@@�@@"�Ǐ�(���)��������"�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�������������� += " and forced_settle_reason_type = in(? ,?)" <BR>
     * �@@�@@�@@�f�[�^�R���e�i��"�ۏ؋��ێ�������i��ԁj"�A <BR>
     * �@@�@@�@@�@@"�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j" ��ǉ�����B <BR>
     * <BR>
     * �@@�@@�Q�|�P�Q�|�R�j�@@�ȊO�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�������������� += " and forced_settle_reason_type = ?" <BR>
     * �@@�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�������ϗ��R��ǉ�����B <BR>
     * <BR>
     * �@@�Q�|�P�R�j�@@�p�����[�^.���N�G�X�g�f�[�^.���ϊ��� != null�̏ꍇ�A<BR>
     * �@@�@@���ϊ��������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and close_date = ?"<BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.���ϊ�����ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�P�S�j�@@�p�����[�^.���N�G�X�g�f�[�^.�����G���[���R�R�[�h != null�̏ꍇ�A<BR>
     * �@@�@@�����G���[���R�R�[�h�����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and error_reason_code = ?"<BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�����G���[���R�R�[�h��ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�P�T�j�@@�p�����[�^.���N�G�X�g�f�[�^.���F�敪 != null�̏ꍇ�A<BR>
     * �@@�@@���F�敪���������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and order_open_status = ?"<BR>
     * �@@�@@�@@+ " and approve_status_type = ?"<BR>
     * �@@�@@�f�[�^�R���e�i�Ɉȉ��̒l��ǉ�����B<BR>
     * �@@�@@�@@�E"�I�[�v��"�i�����L����ԁj<BR>
     * �@@�@@�@@�E"�����F"�i���F��ԋ敪�j<BR>
     * <BR>
     * �@@�Q�|�P�U�j�@@�p�����[�^.���N�G�X�g�f�[�^.�����敪 != null�̏ꍇ�A<BR>
     * �@@�@@�����敪���������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += " and tax_type = ?"<BR>
     * �@@�@@�f�[�^�R���e�i�Ɋ����f�[�^�A�_�v�^.getAP�����敪()��<BR>
     * �@@�@@�߂�l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@[getAP�����敪()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�ŋ敪�F�@@�p�����[�^.���N�G�X�g�f�[�^.�����敪<BR>
     * <BR>
     * �@@�Q�|�P�V�j�@@�p�����[�^.���N�G�X�g�f�[�^.�s��R�[�h != null�̏ꍇ�A<BR>
     * �@@�@@�s����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@����������������� += " and market_id ?"<BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�s��R�[�h��<BR>
     * �@@�@@�Y������s��.�s��ID��ǉ�����B<BR>
     * <BR>
     * �R�j�@@�\�[�g�������쐬����B<BR>
     * �@@this.create�������ϒ����\�[�g����()���R�[������B<BR>
     * <BR>
     * �@@[create�������ϒ����\�[�g����()�Ɏw�肷�����]<BR>
     * �@@�@@�\�[�g�L�[�F�@@�p�����[�^.���N�G�X�g�f�[�^.�\�[�g�L�[<BR>
     * <BR>
     * �S�j�@@DB����������B<BR>
     * �@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�������ϒ���Row.TYPE <BR>
     * �@@�@@arg1�F�@@�쐬������������������<BR>
     * �@@�@@arg2�F�@@create�������ϒ����\�[�g����()�̖߂�l<BR>
     * �@@�@@arg3�F�@@null<BR>
     * �@@�@@arg4�F�@@�쐬�����f�[�^�R���e�i.toArray()�̖߂�l<BR>
     * <BR>
     * ���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@param l_referenceRequest -(���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ω������Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return AdminEqForcedSettleOrderRow[]
     * @@throws WEB3BaseException
     */
    public static AdminEqForcedSettleOrderRow[] getForcedSettleOrderList(
        Institution l_institution,
        WEB3AdminForcedSettleReferenceRequest l_referenceRequest)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForcedSettleOrderList(Institution, "
            + "WEB3AdminForcedSettleReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null || l_referenceRequest == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectMgr =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        //��������������i�FString�j�A���������f�[�^�R���e�i�i�FArrayList�j�𐶐�����B
        StringBuffer l_sbQuery = new StringBuffer();
        ArrayList l_lisQueryDataContainers = new ArrayList();

        //�������ϒ������擾���錟���������쐬����B
        //  �������ϒ����̂ݎ擾���錟��������ǉ�����B
        //    �������������� = "forced_settle_reason_type is not null"
        l_sbQuery.append("forced_settle_reason_type is not null");

        //  ���X���������������ɒǉ�����B
        //    �p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h�ꗗ�̗v�f����"?"��ǉ�����B
        //    �������������� += " and branch_id in (?, ?,,,) "
        //    �f�[�^�R���e�i��this.get���XID�ꗗ()�̖߂�l��v�f�����A�ǉ�����B
        //�@@�@@[get���XID�ꗗ()�Ɏw�肷�����]
        //�@@�،���ЃR�[�h�F�@@�p�����[�^.�،����.�،���ЃR�[�h
        //�@@�@@���X�R�[�h�ꗗ�F�@@�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h�ꗗ
        //�@@�@@��get���XID�ꗗ()�̖߂�l == null�̏ꍇ�A
        //�@@�@@�@@�@@�u�����ɊY������f�[�^�����݂��Ȃ��B�v�̋Ɩ��G���[���X���[����B
        WEB3AdminPMEquityDataManager l_datamanager = new WEB3AdminPMEquityDataManager();
        String[] l_strBranchIds = null;
        try
        {
            l_strBranchIds = l_datamanager.getBranchId(
                l_institution.getInstitutionCode(),
                l_referenceRequest.branchCodeList);
        }
        catch (DataQueryException l_dqex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_strBranchIds == null)
        {
            log.error("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        l_sbQuery.append(" and branch_id in ( ");
        for(int i = 0; i < l_strBranchIds.length; i++)
        {
            l_sbQuery.append(" ? ,");
            l_lisQueryDataContainers.add(l_strBranchIds[i]);
        }
        l_sbQuery.deleteCharAt(l_sbQuery.length() - 1);
        l_sbQuery.append(") ");

        //�p�����[�^.���N�G�X�g�f�[�^.������ != null�̏ꍇ�A���������������������ɒǉ�����B
        if (l_referenceRequest.orderBizDate != null)
        {
            //�������������� += " and biz_date = ?"
            //�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.��������YYYYMMDD�`���Œǉ�����B
            l_sbQuery.append(" and biz_date = ?");
            String l_strBizDate = WEB3DateUtility.formatDate(l_referenceRequest.orderBizDate, "yyyyMMdd");
            l_lisQueryDataContainers.add(l_strBizDate);
        }

        //�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h != null�̏ꍇ�A�ڋq���������������ɒǉ�����B
        if (l_referenceRequest.accountCode != null)
        {
            //�������������� += " and account_code = ?"
            //�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h��ǉ�����B
            l_sbQuery.append(" and account_code = ?");
            l_lisQueryDataContainers.add(l_referenceRequest.accountCode);
        }

        //�p�����[�^.���N�G�X�g�f�[�^.�����R�[�h != null�̏ꍇ�A������������������������ɒǉ�����B
        if (l_referenceRequest.productCode != null)
        {
            //�������������� += " and product_code = ?"
            //�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�����R�[�h��ǉ�����B
            l_sbQuery.append(" and product_code = ?");
            l_lisQueryDataContainers.add(l_referenceRequest.productCode);
        }

        //�p�����[�^.���N�G�X�g�f�[�^.���F��� != null�̏ꍇ�A���F��ԏ��������������ɒǉ�����B
        if (l_referenceRequest.approveState != null)
        {
            //�������������� += " and approve_status_type = ?"
            //�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.���F��Ԃ�ǉ�����B
            l_sbQuery.append(" and approve_status_type = ?");
            l_lisQueryDataContainers.add(l_referenceRequest.approveState);
        }

        //�p�����[�^.���N�G�X�g�f�[�^.���F�҃R�[�h != null�̏ꍇ�A���F�҃R�[�h���������������ɒǉ�����B
        if (l_referenceRequest.checker != null)
        {
            //�������������� += " and approver_code = ?"
            //�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.���F�҃R�[�h��ǉ�����B
            l_sbQuery.append(" and approver_code = ?");
            l_lisQueryDataContainers.add(l_referenceRequest.checker);
        }

        //�p�����[�^.���N�G�X�g�f�[�^.�쐬����From != null�̏ꍇ�A�쐬����From�����������ɒǉ�����B
        if (l_referenceRequest.createDateFrom != null)
        {
            //�������������� += " and created_timestamp >= ?"
            //�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�쐬����From��ǉ�����B
            l_sbQuery.append(" and created_timestamp >= ?");
            l_lisQueryDataContainers.add(
                WEB3DateUtility.getDate(l_referenceRequest.createDateFrom, "yyyyMMddHHmm"));
        }

        //�p�����[�^.���N�G�X�g�f�[�^.�쐬����To != null�̏ꍇ�A�쐬����To�����������ɒǉ�����B
        if (l_referenceRequest.createDateTo != null)
        {
            //�������������� += " and created_timestamp <= ?"
            //�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�쐬����To��ǉ�����B
            l_sbQuery.append(" and created_timestamp <= ?");
            l_lisQueryDataContainers.add(
                WEB3DateUtility.getDate(l_referenceRequest.createDateTo, "yyyyMMddHHmm"));
        }

        //�p�����[�^.���N�G�X�g�f�[�^.���F����From != null�̏ꍇ�A���F����From�����������ɒǉ�����B
        if (l_referenceRequest.approveDateFrom != null)
        {
            //�������������� += " and approve_timestamp >= ?"
            //�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.���F����From��ǉ�����
            l_sbQuery.append(" and approve_timestamp >= ?");
            l_lisQueryDataContainers.add(
                WEB3DateUtility.getDate(l_referenceRequest.approveDateFrom, "yyyyMMddHHmm"));
        }

        //�p�����[�^.���N�G�X�g�f�[�^.���F����To != null�̏ꍇ�A���F����To�����������ɒǉ�����B
        if (l_referenceRequest.approveDateTo != null)
        {
            //�������������� += " and approve_timestamp <= ?"
            //�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.���F����To��ǉ�����B
            l_sbQuery.append(" and approve_timestamp <= ?");
            l_lisQueryDataContainers.add(
                WEB3DateUtility.getDate(l_referenceRequest.approveDateTo, "yyyyMMddHHmm"));
        }

        //�p�����[�^.���N�G�X�g�f�[�^.�������ϗ��R != null�̏ꍇ�A
        //�������ϗ��R���������������ɒǉ�����B
        if (l_referenceRequest.forcedSettleReason != null)
        {
            //�p�����[�^.���N�G�X�g�f�[�^.�������ϗ��R == "�Ǐ�(���)��������"�̏ꍇ
            //�@@�������������� += " and forced_settle_reason_type = in(? ,?)"
            //�@@�f�[�^�R���e�i��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�y�x�j"�A
            //�@@�@@"�ۏ؋��ێ�������i�I�����C���J�n�O�E�d�x�j" ��ǉ�����B
            if (WEB3AdminEquityForcedSettleReasonDef.ADDDEPOSIT_FIRST_DATE_EXCESS.equals(
                l_referenceRequest.forcedSettleReason))
            {
                l_sbQuery.append(" and forced_settle_reason_type in (?, ?)");
                l_lisQueryDataContainers.add(
                    WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS);
                l_lisQueryDataContainers.add(
                    WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS);
            }
            //�p�����[�^.���N�G�X�g�f�[�^.�������ϗ��R == "�Ǐ�(���)��������"�̏ꍇ
            //�@@�������������� += " and forced_settle_reason_type = in(? ,?)"
            //�@@�f�[�^�R���e�i��"�ۏ؋��ێ�������i��ԁj"�A
            //�@@�@@"�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j" ��ǉ�����B
            else if (WEB3AdminEquityForcedSettleReasonDef.ADDDEPOSIT_SECOND_DATE_EXCESS.equals(
                l_referenceRequest.forcedSettleReason))
            {
                l_sbQuery.append(" and forced_settle_reason_type in (?, ?)");
                l_lisQueryDataContainers.add(
                    WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET);
                l_lisQueryDataContainers.add(
                    WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL);
            }
            //�ȊO�̏ꍇ
            //�@@�������������� += " and forced_settle_reason_type = ?"
            //�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�������ϗ��R��ǉ�����B
            else
            {
                l_sbQuery.append(" and forced_settle_reason_type = ?");
                l_lisQueryDataContainers.add(l_referenceRequest.forcedSettleReason);
            }
        }

        //�p�����[�^.���N�G�X�g�f�[�^.���ϊ��� != null�̏ꍇ�A���ϊ��������������ɒǉ�����B
        if (l_referenceRequest.closeDate != null)
        {
            //�������������� += " and close_date = ?"
            //�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.���ϊ�����ǉ�����B
            l_sbQuery.append(" and close_date = ?");
            l_lisQueryDataContainers.add(l_referenceRequest.closeDate);
        }

        //�p�����[�^.���N�G�X�g�f�[�^.�����G���[���R�R�[�h != null�̏ꍇ�A�����G���[���R�R�[�h�����������ɒǉ�����B
        if (l_referenceRequest.errorReason != null)
        {
            //�������������� += " and error_reason_code = ?"
            //�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�����G���[���R�R�[�h��ǉ�����B
            l_sbQuery.append(" and error_reason_code = ?");
            l_lisQueryDataContainers.add(l_referenceRequest.errorReason);
        }

        //�p�����[�^.���N�G�X�g�f�[�^.���F�敪 != null�̏ꍇ�A���F�敪���������������ɒǉ�����B
        if (l_referenceRequest.approveType != null)
        {
            //�������������� += " and order_open_status = ?"
            //�@@�@@�@@+ " and approve_status_type = ?"
            //�@@�@@�f�[�^�R���e�i�Ɉȉ��̒l��ǉ�����B
            //�@@�@@�@@�E"�I�[�v��"�i�����L����ԁj
            //�@@�@@�@@�E"�����F"�i���F��ԋ敪�j
            l_sbQuery.append(" and order_open_status = ?");
            l_sbQuery.append(" and approve_status_type = ?");
            l_lisQueryDataContainers.add(OrderOpenStatusEnum.OPEN.intValue() + "");
            l_lisQueryDataContainers.add(WEB3ApproveStatusType.UNAPPROVED);
        }

        //�p�����[�^.���N�G�X�g�f�[�^.�����敪 != null�̏ꍇ�A�����敪���������������ɒǉ�����B
        if (l_referenceRequest.taxType != null)
        {
            //�������������� += " and tax_type = ?"
            //�f�[�^�R���e�i�Ɋ����f�[�^�A�_�v�^.getAP�����敪()�̖߂�l���Z�b�g����B
            //getAP�����敪()�Ɏw�肷�����]�ŋ敪�F�@@�p�����[�^.���N�G�X�g�f�[�^.�����敪
            l_sbQuery.append(" and tax_type = ?");
            String l_strTaxType = WEB3EquityDataAdapter.getApTaxType(l_referenceRequest.taxType);
            l_lisQueryDataContainers.add(l_strTaxType);
        }

        //�p�����[�^.���N�G�X�g�f�[�^.�s��R�[�h != null�̏ꍇ�A�s����������������ɒǉ�����B
        if (l_referenceRequest.marketCode != null)
        {
            //����������������� += " and market_id = ?"
            //�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�s��R�[�h��
            //�Y������s��.�s��ID��ǉ�����B
            l_sbQuery.append(" and market_id = ?");
            Market l_market = null;
            try
            {
                l_market = l_finObjectMgr.getMarket(l_institution, l_referenceRequest.marketCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error(STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                    "�s��I�u�W�F�N�g���擾�ł��܂���B");
            }
            l_lisQueryDataContainers.add(l_market.getMarketId() + "");
        }

        //�\�[�g�������쐬����Bthis.create�������ϒ����\�[�g����()���R�[������B
        //�@@[create�������ϒ����\�[�g����()�Ɏw�肷�����]
        //�@@�@@�\�[�g�L�[�F�@@�p�����[�^.���N�G�X�g�f�[�^.�\�[�g�L�[
        String l_strSortConditon =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_referenceRequest.sortKeys);

        //DB����������
        //QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        //�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]
        //�@@�@@arg0�F�@@�������ϒ���Row.TYPE
        //�@@�@@arg1�F�@@�쐬������������������
        //�@@�@@arg2�F�@@create�������ϒ����\�[�g����()�̖߂�l
        //�@@�@@arg3�F�@@null
        //�@@�@@arg4�F�@@�쐬�����f�[�^�R���e�i.toArray()�̖߂�l
        Object[] l_objValues = new Object[l_lisQueryDataContainers.size()];
        l_lisQueryDataContainers.toArray(l_objValues);

        List l_lisResult = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisResult = l_processor.doFindAllQuery(
                AdminEqForcedSettleOrderRow.TYPE,
                l_sbQuery.toString(),
                l_strSortConditon,
                null,
                l_objValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        if (l_lisResult == null || l_lisResult.size() == 0)
        {
            //���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        AdminEqForcedSettleOrderRow[] l_orderRows =
            new AdminEqForcedSettleOrderRow[l_lisResult.size()];
        l_lisResult.toArray(l_orderRows);

        log.exiting(STR_METHOD_NAME);
        return l_orderRows;
    }

    /**
     * (get�������ϒ����ꗗ)<BR>
     * �����̏����ɊY�����鋭�����ϒ����̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�j�@@��������������i�FString�j�A���������f�[�^�R���e�i�i�FArrayList�j��<BR>
     * �@@��������B<BR>
     * <BR>
     * �Q�j�������ϒ������擾���錟���������쐬����B<BR>
     * �@@�Q�|�P�j�@@�����F�̗L���ȋ������ϒ����̂ݎ擾���錟��������ǉ�����B<BR>
     * �@@�@@�������������� = "order_open_status = ?"<BR>
     * �@@�@@�@@+ " and forced_settle_reason_type is not null"<BR>
     * �@@�@@�@@+ " and approve_status_type = ?"<BR>
     * <BR>
     * �@@�@@�f�[�^�R���e�i��"�I�[�v��"�i�����L����ԁj�A<BR>
     * �@@�@@"�����F"�i���F��ԋ敪�j��ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@����ID�����������ɒǉ�����B<BR>
     * �@@�@@�p�����[�^.����ID�ꗗ�̗v�f�����A����������"?"��ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += " and order_id in (?, ?,,,) "<BR>
     * �@@�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.����ID�ꗗ�̑S�v�f��ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�p�����[�^.����IDFrom != null�@@����<BR>
     * �@@�@@�p�����[�^.����IDTo != null�̏ꍇ�A����ID�����W������<BR>
     * �@@�@@���������ɒǉ�����B<BR>
     * �@@�@@���񓯊��������s���ꍇ�ɐݒ肳���B<BR>
     * <BR>
     * �@@�@@�������������� += " and account_id >= ?"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@+ " and account_id <= ?"<BR>
     * �@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     * �@@�@@�@@�E�p�����[�^.����IDFrom<BR>
     * �@@�@@�@@�E�p�����[�^.����IDTo<BR>
     * <BR>
     * �R�j�@@�\�[�g�������쐬����B<BR>
     * �@@this.create�������ϒ����\�[�g����()���R�[������B<BR>
     * <BR>
     * �@@[create�������ϒ����\�[�g����()�Ɏw�肷�����]<BR>
     * �@@�@@�\�[�g�L�[�F�@@�p�����[�^.�\�[�g�L�[<BR>
     * <BR>
     * �S�j�@@DB����������B<BR>
     * �@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�������ϒ���Row.TYPE <BR>
     * �@@�@@arg1�F�@@�쐬������������������<BR>
     * �@@�@@arg2�F�@@create�������ϒ����\�[�g����()�̖߂�l<BR>
     * �@@�@@arg3�F�@@null<BR>
     * �@@�@@arg4�F�@@�쐬�����f�[�^�R���e�i.toArray()�̖߂�l<BR>
     * <BR>
     * ���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * @@param l_strOrderIdList - (����ID�ꗗ)<BR>
     * ����ID�̈ꗗ<BR>
     * @@param l_sortsKeys - (�\�[�g�L�[)<BR>
     * �������σ\�[�g�L�[<BR>
     * @@param l_lngAccountIdFrom - (����IDFrom)<BR>
     * ����IDFrom<BR>
     * @@param l_lngAccountIdTo - (����IDTo)<BR>
     * ����IDTo<BR>
     * @@return AdminEqForcedSettleOrderRow[]
     * @@throws WEB3BaseException
     */
    public static AdminEqForcedSettleOrderRow[] getForcedSettleOrderList(
        String[] l_strOrderIdList,
        WEB3AdminForcedSettleSortKeyUnit[] l_sortKeys,
        Long l_lngAccountIdFrom,
        Long l_lngAccountIdTo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForcedSettleOrderList(String, "
            + "WEB3AdminForcedSettleSortKeyUnit[], Long, Long)";
        log.entering(STR_METHOD_NAME);

        if (l_strOrderIdList == null || l_strOrderIdList.length == 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //��������������i�FString�j�A���������f�[�^�R���e�i�i�FArrayList�j�𐶐�����B
        StringBuffer l_sbQuery = new StringBuffer();
        ArrayList l_arrayList = new ArrayList();

        //�������ϒ������擾���錟���������쐬����B
        //�����F�̗L���ȋ������ϒ����̂ݎ擾���錟��������ǉ�����B
        //�@@�@@�������������� = "order_open_status = ?"
        //�@@�@@�@@+ " and forced_settle_reason_type is not null"
        //�@@�@@�@@+ " and approve_status_type = ?"
        //�@@�@@�f�[�^�R���e�i��"�I�[�v��"�i�����L����ԁj�A
        //�@@�@@"�����F"�i���F��ԋ敪�j��ǉ�����B
        l_sbQuery.append("order_open_status = ?");
        l_sbQuery.append(" and forced_settle_reason_type is not null");
        l_sbQuery.append(" and approve_status_type = ?");
        l_arrayList.add(OrderOpenStatusEnum.OPEN.intValue() + "");
        l_arrayList.add(WEB3ApproveStatusType.UNAPPROVED);

        //����ID�����������ɒǉ�����B
        //�@@�@@�p�����[�^.����ID�ꗗ�̗v�f�����A����������"?"��ǉ�����B
        //�@@�@@�@@�������������� += " and order_id in (?, ?,,,) "
        //�@@�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.����ID�ꗗ�̑S�v�f��ǉ�����B
        l_sbQuery.append(" and order_id in ( ");
        for(int i = 0; i < l_strOrderIdList.length; i++)
        {
            l_sbQuery.append(" ? ,");
            l_arrayList.add(l_strOrderIdList[i]);
        }
        l_sbQuery.deleteCharAt(l_sbQuery.length() - 1);
        l_sbQuery.append(") ");

        //�p�����[�^.����IDFrom != null�@@����
        //�@@�@@�p�����[�^.����IDTo != null�̏ꍇ�A����ID�����W������
        //�@@�@@���������ɒǉ�����B
        //�@@�@@���񓯊��������s���ꍇ�ɐݒ肳���B
        //�@@�@@�������������� += " and account_id >= ?"
        //�@@�@@�@@�@@�@@�@@�@@�@@+ " and account_id <= ?"
        //�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j
        //�@@�@@�@@�E�p�����[�^.����IDFrom
        //�@@�@@�@@�E�p�����[�^.����IDTo
        if (l_lngAccountIdFrom != null && l_lngAccountIdTo != null)
        {
            l_sbQuery.append(" and account_id >= ?");
            l_sbQuery.append(" and account_id <= ?");
            l_arrayList.add(l_lngAccountIdFrom);
            l_arrayList.add(l_lngAccountIdTo);
        }

        //�\�[�g�������쐬����B
        //�@@this.create�������ϒ����\�[�g����()���R�[������B
        //�@@[create�������ϒ����\�[�g����()�Ɏw�肷�����]
        //�@@�@@�\�[�g�L�[�F�@@�p�����[�^.�\�[�g�L�[
        String l_strSort = WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_sortKeys);

        //DB����������B
        //�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        //�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]
        //�@@�@@arg0�F�@@�������ϒ���Row.TYPE
        //�@@�@@arg1�F�@@�쐬������������������
        //�@@�@@arg2�F�@@create�������ϒ����\�[�g����()�̖߂�l
        //�@@�@@arg3�F�@@null
        //�@@�@@arg4�F�@@�쐬�����f�[�^�R���e�i.toArray()�̖߂�l
        Object[] l_objValues = new Object[l_arrayList.size()];
        l_arrayList.toArray(l_objValues);
        List l_lisResult = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisResult = l_processor.doFindAllQuery(
                AdminEqForcedSettleOrderRow.TYPE,
                l_sbQuery.toString(),
                l_strSort,
                null,
                l_objValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if (l_lisResult == null || l_lisResult.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        AdminEqForcedSettleOrderRow[] l_orderRows =
            new AdminEqForcedSettleOrderRow[l_lisResult.size()];
        l_lisResult.toArray(l_orderRows);

        log.exiting(STR_METHOD_NAME);
        return l_orderRows;
    }

    /**
     * (create�������ϒ����\�[�g����)<BR>
     * �\�[�g�������쐬����B<BR>
     * <BR>
     * �P�j�p�����[�^.�\�[�g�L�[ == null�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �Q�j�\�[�g����������(�FString)���쐬����B<BR>
     * <BR>
     * �R�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�R�|�P�j�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>
     * �@@�@@�쐬�����\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�E�u���X�R�[�h�v�@@���@@�������ϒ���.���XID<BR>
     * �@@�@@�E�u�ڋq�R�[�h�v�@@���@@substr(�������ϒ���.����ID, 9, 6)<BR>
     * �@@�@@�E�u�������ϗ��R�v�@@���@@�������ϒ���.�������ϗ��R�敪<BR>
     * �@@�@@�E�u�s��R�[�h�v�@@���@@�������ϒ���.�s��ID<BR>
     * �@@�@@�E�u�����R�[�h�v�@@���@@�������ϒ���.����ID<BR>
     * �@@�@@�E�u�����敪�v�@@���@@�������ϒ���.�ŋ敪<BR>
     * �@@�@@�E�u���敪�v�@@���@@�������ϒ���.���敪<BR>
     * �@@�@@�E�u�ٍϋ敪�v�@@���@@�������ϒ���.�ٍϋ敪<BR>
     * �@@�@@�E�u�ٍϊ����l�v�@@���@@�������ϒ���.�ٍϊ����l<BR>
     * �@@�@@�E�u�����v�@@���@@�������ϒ���.����<BR>
     * �@@�@@�E�u���ϊ����v�@@���@@�������ϒ���.����<BR>
     * �@@�@@�E�u�쐬�����v�@@���@@�������ϒ���.�쐬����<BR>
     * �@@�@@�E�u�i��j���F�����v�@@���@@�������ϒ���.���F�^�񏳔F����<BR>
     * �@@�@@�E�u�������v�@@���@@�������ϒ���.��������<BR>
     * �@@�@@�E�u���P���v�@@���@@�������ϒ���.�����P��<BR>
     * �@@�@@�E�u������v�@@���@@�������ϒ���.�����<BR>
     * �@@�@@�E�u���������v�@@���@@�������ϒ���.��������<BR>
     * �@@�@@�E�u�ۏ؋��a�����v�@@���@@�������ϒ���.�ۏ؋��ێ���<BR>
     * �@@�@@�E�u���F��ԁv�@@���@@�������ϒ���.���F��ԋ敪<BR>
     * <BR>
     * �@@�R�|�Q�j�\�[�g�L�[.�����^�~���ɑΉ�����擾����<BR>
     * �@@�@@(asc or desc)���\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �S�j�\�[�g���������ɁA�������ϒ����e�[�u��.�X�V���t�������w��ŕt��<BR>
     * <BR>
     * �T�j�쐬�����\�[�g�����������ԋp����B<BR>
     * @@param l_sortsKeys - (�\�[�g�L�[)<BR>
     * �������σ\�[�g�L�[<BR>
     * @@return String
     */
    public static String createForcedSettleSortCondition(
        WEB3AdminForcedSettleSortKeyUnit[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createForcedSettleSortCondition("
            + "WEB3AdminForcedSettleSortKeyUnit[])";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�\�[�g�L�[ == null�̏ꍇ�Anull��ԋp����B
        if (l_sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�\�[�g����������(�FString)���쐬����B
        StringBuffer l_sbSort = new StringBuffer();

        //�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        for(int i = 0; i < l_sortKeys.length; i++)
        {
            WEB3AdminForcedSettleSortKeyUnit l_sortKeyUnit = l_sortKeys[i];
            //�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A�쐬�����\�[�g����������ɒǉ�����B
            //�E�u���X�R�[�h�v�@@���@@�������ϒ���.���XID
            if (WEB3AdminEquitySortKeyItemNameDef.BRANCH_CODE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" branch_id ");
            }
            //�E�u�ڋq�R�[�h�v�@@���@@substr(�������ϒ���.����ID, 9, 6) 
            else if (WEB3AdminEquitySortKeyItemNameDef.ACCOUNT_CODE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" substr(account_id, 9, 6) ");
            }
            //�E�u�������ϗ��R�v�@@���@@�������ϒ���.�������ϗ��R�敪
            else if (WEB3AdminEquitySortKeyItemNameDef.FORCED_SETTLE_REASON.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" forced_settle_reason_type ");
            }
            //�E�u�s��R�[�h�v�@@���@@�������ϒ���.�s��ID
            else if (WEB3AdminEquitySortKeyItemNameDef.MARKET_CODE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" market_id ");
            }
            //�E�u�����R�[�h�v�@@���@@�������ϒ���.����ID
            else if (WEB3AdminEquitySortKeyItemNameDef.PRODUCT_CODE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" product_id ");
            }
            //�E�u�����敪�v�@@���@@�������ϒ���.�ŋ敪
            else if (WEB3AdminEquitySortKeyItemNameDef.TAX_TYPE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" tax_type ");
            }
            //�E�u���敪�v�@@���@@�������ϒ���.���敪
            else if (WEB3AdminEquitySortKeyItemNameDef.CONTRACT_TYPE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" contract_type ");
            }
            //�E�u�ٍϋ敪�v�@@���@@�������ϒ���.�ٍϋ敪
            else if (WEB3AdminEquitySortKeyItemNameDef.REPAYMENT_DIV.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" repayment_type ");
            }
            //�E�u�ٍϊ����l�v�@@���@@�������ϒ���.�ٍϊ����l
            else if (WEB3AdminEquitySortKeyItemNameDef.REPAYMENTTIME_LIMIT.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" repayment_num ");
            }
            //�E�u�����v�@@���@@�������ϒ���.����
            else if (WEB3AdminEquitySortKeyItemNameDef.OPEN_DATE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" open_date ");
            }
            //�E�u���ϊ����v�@@���@@�������ϒ���.����
            else if (WEB3AdminEquitySortKeyItemNameDef.CLOSE_DATE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" close_date ");
            }
            //�E�u�쐬�����v�@@���@@�������ϒ���.�쐬����
            else if (WEB3AdminEquitySortKeyItemNameDef.CREATE_DATE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" created_timestamp ");
            }
            //�E�u�i��j���F�����v�@@���@@�������ϒ���.���F�^�񏳔F����
            else if (WEB3AdminEquitySortKeyItemNameDef.APPROVE_DATE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" approve_timestamp ");
            }
            //�E�u�������v�@@���@@�������ϒ���.��������
            else if (WEB3AdminEquitySortKeyItemNameDef.CONTRACT_QUANTITY.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" org_contract_quantity ");
            }
            //�E�u���P���v�@@���@@�������ϒ���.�����P��
            else if (WEB3AdminEquitySortKeyItemNameDef.CONTRACT_PRICE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" original_contract_price ");
            }
            //�E�u������v�@@���@@�������ϒ���.�����
            else if (WEB3AdminEquitySortKeyItemNameDef.CONTRACT_EXEC_PRICE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" contract_amount ");
            }
            //�E�u���������v�@@���@@�������ϒ���.��������
            else if (WEB3AdminEquitySortKeyItemNameDef.ORDER_QUANTITY.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" quantity ");
            }
            //�E�u�ۏ؋��a�����v�@@���@@�������ϒ���.�ۏ؋��ێ���
            else if (WEB3AdminEquitySortKeyItemNameDef.MARGIN_COLLATERAL_RATE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" margin_maintenance_rate ");
            }
            //�E�u���F��ԁv�@@���@@�������ϒ���.���F��ԋ敪
            else if (WEB3AdminEquitySortKeyItemNameDef.APPROVE_STATE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" approve_status_type ");
            }
            else
            {
                continue;
            }
            //�\�[�g�L�[.�����^�~���ɑΉ�����擾����(asc or desc)���\�[�g����������ɒǉ�����B
            if (WEB3AscDescDef.ASC.equals(l_sortKeyUnit.ascDesc))
            {
                l_sbSort.append("ASC");
            }
            else
            {
                l_sbSort.append("DESC");
            }
            if (i < l_sortKeys.length)
            {
                l_sbSort.append(" , ");
            }
        }

        //�\�[�g���������ɁA�������ϒ����e�[�u��.�X�V���t�������w��ŕt��
        l_sbSort.append("last_updated_timestamp ASC ");

        //�쐬�����\�[�g�����������ԋp����B
        String l_strSort = l_sbSort.toString();
        log.exiting(STR_METHOD_NAME);
        return l_strSort;
    }

    /**
     * (create�������ϒ����Ɖ���ꗗ)<BR>
     * �����̋������ϒ����ꗗ��苭�����ϒ����Ɖ����<BR>
     * �ꗗ���쐬����B<BR>
     * <BR>
     * �P�j�@@�߂�l���i�[����ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���X�v���t�@@�����X����]���z���擾����B<BR>
     * <BR>
     * �@@[���X�v���t�@@�����X�e�[�u����������]<BR>
     * �@@�@@���XID = �p�����[�^.�������ϒ����ꗗ��0�Ԗڂ̗v�f.���XID And<BR>
     * �@@�@@�v���t�@@�����X�� = �M�p����������ϊ�]���z�i�����\������p�j And<BR>
     * �@@�@@�v���t�@@�����X���̘A�� = 1�i�Œ�j<BR>
     * <BR>
     * �R�j�@@�p�����[�^.�������ϒ����ꗗ�̗v�f�����A�ȉ��̏�����<BR>
     * �@@�J��Ԃ��B<BR>
     * �@@�R�|�P�j�@@�������ϒ����Ɖ���𐶐�����B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@ID�F�@@�����Ώۂ̗v�f.����ID<BR>
     * �@@�@@���X�R�[�h�F�@@�����Ώۂ̗v�f.���XID�ɊY�����镔�X.���X�R�[�h<BR>
     * �@@�@@�ڋq�R�[�h�F�@@�����Ώۂ̗v�f.�����R�[�h <BR>
     * �@@�@@�ڋq���F�@@�����Ώۂ̗v�f.����ID�ɊY������ڋq.get�\���ڋq��()�@@(*4) <BR>
     * �@@�@@�����R�[�h�F�@@�����Ώۂ̗v�f.�����R�[�h <BR>
     * �@@�@@�������F�@@�����Ώۂ̗v�f.����ID�ɊY�����銔������.getDataSourceObject()<BR>
     * �@@�@@�̖߂�l.�������@@(*4)<BR>
     * �@@�@@�s��R�[�h�F�@@�����Ώۂ̗v�f.�s��ID�ɊY������s��.�s��R�[�h<BR>
     * �@@�@@�����敪�F�@@�����f�[�^�A�_�v�^.get�����敪(�����Ώۂ̗v�f.�ŋ敪)<BR>
     * �@@�@@���敪�F�@@�����Ώۂ̗v�f.���敪<BR>
     * �@@�@@�����F�@@�����Ώۂ̗v�f.����<BR>
     * �@@�@@���ϊ����F�@@�����Ώۂ̗v�f.����<BR>
     * �@@�@@�������F�@@�����Ώۂ̗v�f.��������<BR>
     * �@@�@@���P���F�@@�����Ώۂ̗v�f.�����P��<BR>
     * �@@�@@������F�@@�����Ώۂ̗v�f.�����<BR>
     * �@@�@@�ۏ؋��a�����F�@@�����Ώۂ̗v�f.�ۏ؋��ێ���<BR>
     * �@@�@@�Ǐؔ������F�@@�����Ώۂ̗v�f.�Ǐؔ�����<BR>
     * �@@�@@�Ǐ،o�ߓ����F�@@�����Ώۂ̗v�f.�Ǐ،o�ߓ���<BR>
     * �@@�@@�ٍϋ敪�F�@@�����Ώۂ̗v�f.�ٍϋ敪<BR>
     * �@@�@@�ٍϊ����l�F�@@�����Ώۂ̗v�f.�ٍϊ����l<BR>
     * �@@�@@���������F�@@�����Ώۂ̗v�f.��������<BR>
     * �@@�@@�����P���敪�F�@@�����Ώۂ̗v�f.�w�l == 0�̏ꍇ�A"���s"�A�ȊO��"�w�l"�B<BR>
     * �@@�@@�����P���F�@@�����Ώۂ̗v�f.�w�l<BR>
     * �@@�@@�������F�@@�����Ώۂ̗v�f.������<BR>
     * �@@�@@�쐬�����F�@@�����Ώۂ̗v�f.�쐬����<BR>
     * �@@�@@�i��j���F�����F�@@�����Ώۂ̗v�f.���F�^�񏳔F����<BR>
     * �@@�@@���F��ԁF�@@�����Ώۂ̗v�f.���F��ԋ敪<BR>
     * �@@�@@���F�҃R�[�h�F�@@�����Ώۂ̗v�f.���F�҃R�[�h<BR>
     * �@@�@@�����G���[���R�R�[�h�F�@@�����Ώۂ̗v�f.�����G���[���R�R�[�h<BR>
     * �@@�@@��]���z���߃t���O�F�@@(*1)<BR>
     * �@@�@@�T�Z�]���z�F�@@(*2)<BR>
     * �@@�@@�������ϗ��R�F�@@(*3)<BR>
     * <BR>
     * �@@�@@(*1) �u�Q�j�ɂĎ擾������]���z�v�ƁA�u�T�Z�]���z�i(*2)�Ŏ擾�j�v�Ƃ��r���A<BR>
     * �@@�@@�@@�@@�擾������]���z < �T�Z�]���z<BR>
     * �@@�@@�@@�ł���΁Atrue�A�ȊO��false���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@���Q�j�ŊY�����R�[�h���擾�ł��Ȃ������ꍇ�A<BR>
     * �@@�@@�@@�@@false�i�F���߂Ȃ��j���Œ�ŃZ�b�g����B<BR>
     * <BR>
     * �@@�@@(*2)�p�����[�^.���F�敪��null�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�ȉ��v�Z�l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[�v�Z��]<BR>
     * �@@�@@�@@�@@�@@�@@�����Ώۂ̗v�f.�������ʁ~��l�i���j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@(��)�����Ώۂ̗v�f.����ID�A�s��ID�ɊY������������.��l<BR>
     * <BR>
     * �@@�@@(*3)this.create�������ϗ��R���()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�������ϗ��R�敪�F�@@�����Ώۂ̗v�f.�������ϗ��R�敪<BR>
     * �@@�@@�@@�@@�@@�@@���X�F�@@�����Ώۂ̗v�f.���XID�ɊY�����镔�X<BR>
     * <BR>
     * �@@�@@�@@�@@���߂�l��null�̏ꍇ�A�u�f�[�^�s�����v�̗�O��throw����B<BR>
     * <BR>
     * �@@�@@(*4)�擾�ł��Ȃ������ꍇ�́Anull���Z�b�g����B<BR>
     * <BR>
     * �@@�R�|�R�j�@@�v���p�e�B�Z�b�g�����C���X�^���X��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_orderRowList - (�������ϒ����ꗗ)<BR>
     * �������ꗗ<BR>
     * @@param l_strApproveType - (���F�敪)<BR>
     * ���F�敪<BR>
     * @@return WEB3AdminForcedSettleTemporaryOrderUnit[]
     * @@throws WEB3BaseException
     */
    public static WEB3AdminForcedSettleTemporaryOrderUnit[] createForcedSettleOrderUnitList(
        AdminEqForcedSettleOrderRow[] l_orderRowList,
        String l_strApproveType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createForcedSettleOrderUnitList("
            + "AdminEqForcedSettleOrderRow[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderRowList == null || l_orderRowList.length == 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�߂�l���i�[����ArrayList�𐶐�����B
        ArrayList l_arrayList = new ArrayList();

        //���X�v���t�@@�����X����]���z���擾����
        //[���X�v���t�@@�����X�e�[�u����������]
        //�@@�@@���XID = �p�����[�^.�������ϒ����ꗗ��0�Ԗڂ̗v�f.���XID And
        //�@@�@@�v���t�@@�����X�� = �M�p����������ϊ�]���z�i�����\������p�j And
        //�@@�@@�v���t�@@�����X���̘A�� = 1�i�Œ�j
        BranchPreferencesRow l_preferencesRow = null;
        try
        {
            l_preferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_orderRowList[0].getBranchId(),
                    WEB3BranchPreferencesNameDef.MARGIN_FORCEDSETTLEORDER_BASEESTIMATEDASSET,
                    1);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�p�����[�^.�������ϒ����ꗗ�̗v�f�����A�ȉ��̏������J��Ԃ��B
        for(int i = 0; i < l_orderRowList.length; i++)
        {
            //�������ϒ����Ɖ���𐶐�����B
            WEB3AdminForcedSettleTemporaryOrderUnit l_orderUnitResult =
                new WEB3AdminForcedSettleTemporaryOrderUnit();

            //���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();

            //ID�F�@@�����Ώۂ̗v�f.����ID
            if (!l_orderRowList[i].getOrderIdIsNull())
            {
                l_orderUnitResult.id = l_orderRowList[i].getOrderId() + "";
            }

            //���X�R�[�h�F�@@�����Ώۂ̗v�f.���XID�ɊY�����镔�X.���X�R�[�h
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Branch l_branch = null;
            try
            {
                l_branch =
                    l_accountManager.getBranch(l_orderRowList[i].getBranchId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
            l_orderUnitResult.branchCode = l_branch.getBranchCode();

            //�ڋq�R�[�h�F�@@�����Ώۂ̗v�f.�����R�[�h
            l_orderUnitResult.accountCode = l_orderRowList[i].getAccountCode();

            //�ڋq���F�@@�����Ώۂ̗v�f.����ID�ɊY������ڋq.get�\���ڋq��()
            //(*4)�擾�ł��Ȃ������ꍇ�́Anull���Z�b�g����B
            WEB3GentradeMainAccount l_mainAccount = null;
            try
            {
                l_mainAccount =
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_orderRowList[i].getAccountId());
                l_orderUnitResult.accountName = l_mainAccount.getDisplayAccountName();
            }
            catch (NotFoundException l_nfe)
            {
                l_orderUnitResult.accountName = null;
            }

            //�����R�[�h�F�@@�����Ώۂ̗v�f.�����R�[�h
            l_orderUnitResult.productCode = l_orderRowList[i].getProductCode();

            //�������F�@@�����Ώۂ̗v�f.����ID�ɊY�����銔������.getDataSourceObject()�̖߂�l.������
            //(*4)�擾�ł��Ȃ������ꍇ�́Anull���Z�b�g����B
            EqTypeProduct l_eqtypeProduct = null;
            try
            {
                l_eqtypeProduct =
                    (EqTypeProduct)l_productManager.getProduct(l_orderRowList[i].getProductId());
                EqtypeProductRow l_productRow = (EqtypeProductRow)l_eqtypeProduct.getDataSourceObject();
                l_orderUnitResult.productName = l_productRow.getStandardName();
            }
            catch (NotFoundException l_nfe)
            {
                l_orderUnitResult.productName = null;
            }

            //�s��R�[�h�F�@@�����Ώۂ̗v�f.�s��ID�ɊY������s��.�s��R�[�h
            WEB3GentradeFinObjectManager l_finObjectMgr =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = null;
            try
            {
                l_market = l_finObjectMgr.getMarket(l_orderRowList[i].getMarketId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
            l_orderUnitResult.marketCode = l_market.getMarketCode();

            //�����敪�F�@@�����f�[�^�A�_�v�^.get�����敪(�����Ώۂ̗v�f.�ŋ敪)
            l_orderUnitResult.taxType = WEB3EquityDataAdapter.getTaxType(l_orderRowList[i].getTaxType());

            //���敪�F�@@�����Ώۂ̗v�f.���敪
            l_orderUnitResult.contractType = l_orderRowList[i].getContractType().intValue() + "";

            //�����F�@@�����Ώۂ̗v�f.����
            l_orderUnitResult.openDate = l_orderRowList[i].getOpenDate();

            //���ϊ����F�@@�����Ώۂ̗v�f.����
            l_orderUnitResult.closeDate = l_orderRowList[i].getCloseDate();

            //�������F�@@�����Ώۂ̗v�f.��������
            l_orderUnitResult.contractQuantity =
                WEB3StringTypeUtility.formatNumber(l_orderRowList[i].getOrgContractQuantity());

            //���P���F�@@�����Ώۂ̗v�f.�����P��
            l_orderUnitResult.contractPrice =
                WEB3StringTypeUtility.formatNumber(l_orderRowList[i].getOriginalContractPrice());

            //������F�@@�����Ώۂ̗v�f.�����
            double l_dblContractAmount = l_orderRowList[i].getContractAmount();
            l_orderUnitResult.contractExecPrice =
                WEB3StringTypeUtility.formatNumber(l_dblContractAmount);

            // �ۏ؋��a�����F�@@�����Ώۂ̗v�f.�ۏ؋��ێ���
            if (!l_orderRowList[i].getMarginMaintenanceRateIsNull())
            {
                double l_dbMarginMaintenanceRate = l_orderRowList[i].getMarginMaintenanceRate();
                l_orderUnitResult.marginCollateralRate =
                    WEB3StringTypeUtility.formatNumber(l_dbMarginMaintenanceRate);
            }

            //�Ǐؔ������F�@@�����Ώۂ̗v�f.�Ǐؔ�����
            l_orderUnitResult.additionalOccurredDate = l_orderRowList[i].getAdditionalMarginDate();

            // �Ǐ،o�ߓ����F�@@�����Ώۂ̗v�f.�Ǐ،o�ߓ���
            if (!l_orderRowList[i].getAdditionalMarginAccruedDaysIsNull())
            {
                l_orderUnitResult.additionalElapsedDays = WEB3StringTypeUtility.formatNumber(
                    l_orderRowList[i].getAdditionalMarginAccruedDays());
            }

            //�ٍϋ敪�F�@@�����Ώۂ̗v�f.�ٍϋ敪
            l_orderUnitResult.repaymentDiv = l_orderRowList[i].getRepaymentType();

            //�ٍϊ����l�F�@@�����Ώۂ̗v�f.�ٍϊ����l
            l_orderUnitResult.repaymentTimeLimit = l_orderRowList[i].getRepaymentNum() + "";

            //���������F�@@�����Ώۂ̗v�f.��������
            l_orderUnitResult.orderQuantity =
                WEB3StringTypeUtility.formatNumber(l_orderRowList[i].getQuantity());

            //�����P���敪�F�@@�����Ώۂ̗v�f.�w�l == 0�̏ꍇ�A"���s"�A�ȊO��"�w�l"�B
            if (l_orderRowList[i].getLimitPrice() == 0)
            {
                l_orderUnitResult.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_orderUnitResult.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }

            //�����P���F�@@�����Ώۂ̗v�f.�w�l
            l_orderUnitResult.orderPrice =
                WEB3StringTypeUtility.formatNumber(l_orderRowList[i].getLimitPrice());

            //�������F�@@�����Ώۂ̗v�f.������
            l_orderUnitResult.orderBizDate =
                WEB3DateUtility.getDate(l_orderRowList[i].getBizDate(), "yyyyMMdd");

            //�쐬�����F�@@�����Ώۂ̗v�f.�쐬����
            l_orderUnitResult.createDate = l_orderRowList[i].getCreatedTimestamp();

            //�i��j���F�����F�@@�����Ώۂ̗v�f.���F�^�񏳔F����
            l_orderUnitResult.approveDate = l_orderRowList[i].getApproveTimestamp();

            //���F��ԁF�@@�����Ώۂ̗v�f.���F��ԋ敪
            l_orderUnitResult.approveState = l_orderRowList[i].getApproveStatusType();

            //���F�҃R�[�h�F�@@�����Ώۂ̗v�f.���F�҃R�[�h
            l_orderUnitResult.checker = l_orderRowList[i].getApproverCode();

            //�����G���[���R�R�[�h�F�@@�����Ώۂ̗v�f.�����G���[���R�R�[�h
            l_orderUnitResult.errorReason = l_orderRowList[i].getErrorReasonCode();

            // (*2)�p�����[�^.���F�敪��null�̏ꍇ�̂݁A
            double l_dblEstimatedAsset = 0;
            if (l_strApproveType != null)
            {
                WEB3EquityTradedProduct l_tradedProduct = null;
                try
                {
                    // (��)�����Ώۂ̗v�f.����ID�A�s��ID�ɊY������������.��l
                    l_tradedProduct =
                        (WEB3EquityTradedProduct) l_productManager.getTradedProduct(
                            l_orderRowList[i].getProductId(),
                            l_orderRowList[i].getMarketId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                EqtypeTradedProductRow l_eqtypeTradeProductRow =
                    (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                double l_dbBasePrice = l_eqtypeTradeProductRow.getBasePrice();

                // �����Ώۂ̗v�f.�������ʁ~��l�i���j
                String l_strQuantity =
                    String.valueOf(l_orderRowList[i].getQuantity());
                String l_strBasePrice =
                    String.valueOf(l_dbBasePrice);
                BigDecimal l_bdQuantity = new BigDecimal(l_strQuantity);
                BigDecimal l_bdBasePrice = new BigDecimal(l_strBasePrice);
                BigDecimal l_bdResult = l_bdQuantity.multiply(l_bdBasePrice);

                l_dblEstimatedAsset = l_bdResult.doubleValue();
                l_orderUnitResult.estimatedAsset =
                    WEB3StringTypeUtility.formatNumber(l_dblEstimatedAsset);
            }

            //��]���z���߃t���O�F�@@(*1)
            //(*1) �u�Q�j�ɂĎ擾������]���z�v�ƁA�u�T�Z�]���z�i(*2)�Ŏ擾�j�v�Ƃ��r���A
            //�擾������]���z < �T�Z�]���z
            //�ł���΁Atrue�A�ȊO��false���Z�b�g����B
            //�@@�@@�@@�@@���Q�j�ŊY�����R�[�h���擾�ł��Ȃ������ꍇ�A
            //�@@�@@�@@�@@�@@false�i�F���߂Ȃ��j���Œ�ŃZ�b�g����B
            if (l_preferencesRow == null)
            {
                l_orderUnitResult.baseAssetOverFlag = false;
            }
            else
            {
                double l_dblBaseValue = Double.parseDouble(l_preferencesRow.getValue());
                if (l_dblBaseValue < l_dblEstimatedAsset)
                {
                    l_orderUnitResult.baseAssetOverFlag = true;
                }
                else
                {
                    l_orderUnitResult.baseAssetOverFlag = false;
                }
            }

            // (*3)this.create�������ϗ��R���()�̖߂�l 
            // �������ϗ��R�敪�F�@@�����Ώۂ̗v�f.�������ϗ��R�敪
            // ���X�F�@@�����Ώۂ̗v�f.���XID�ɊY�����镔�X
            WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
                creatForcedSettleReasonUnit(
                    l_orderRowList[i].getForcedSettleReasonType(),
                    l_branch);
            // ���߂�l��null�̏ꍇ�A�u�f�[�^�s�����v�̗�O��throw����B
            if (l_forcedSettleReasonUnit == null)
            {
                log.debug("�f�[�^�s����");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "�f�[�^�s�����G���[�B");
            }
            l_orderUnitResult.forcedSettleReason = l_forcedSettleReasonUnit;

            //�v���p�e�B�Z�b�g�����C���X�^���X��ArrayList�ɒǉ�����B
            l_arrayList.add(l_orderUnitResult);
        }

        //��������ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3AdminForcedSettleTemporaryOrderUnit[] l_orderUnit =
            new WEB3AdminForcedSettleTemporaryOrderUnit[l_arrayList.size()];
        l_arrayList.toArray(l_orderUnit);
        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }

    /**
     * (is���F�^�񏳔F�����Ώے���)<BR>
     * ���F�^�񏳔F�����Ώۂ̒������ǂ����`�F�b�N����B<BR>
     * ���{���\�b�h���R�[������O�ɁA����J�����_�R���e�L�X�g��<BR>
     * �@@�Z�b�g��K���s���Ă������ƁB<BR>
     * <BR>
     * �ȉ��̏����̂����ꂩ�ɊY������ꍇ��false��ԋp����B<BR>
     * �@@�E�p�����[�^.�����P��Row.���F��ԋ敪 != "�����F"<BR>
     * �@@�E�p�����[�^.�����P��Row.�����L����� == "�N���[�Y"<BR>
     * �@@�E������ԊǗ�.get������(�p�����[�^.�����P��Row.������)��<BR>
     * �@@�@@��O���X���[�B<BR>
     * <BR>
     * ��L�ȊO��true��ԋp����B<BR>
     * @@param l_orderUnitRow - (�����P��Row)<BR>
     * �����P��Row�I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public static boolean isApproveProcessTargetOrder(EqtypeOrderUnitRow l_orderUnitRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isApproveProcessTargetOrder(EqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitRow == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�ȉ��̏����̂����ꂩ�ɊY������ꍇ��false��ԋp����B
        //�@@�E�p�����[�^.�����P��Row.���F��ԋ敪 != "�����F"
        //  �E�p�����[�^.�����P��Row.�����L����� == "�N���[�Y"
        //�@@�E������ԊǗ�.get������(�p�����[�^.�����P��Row.������)����O���X���[�B
        if (!WEB3ApproveStatusType.UNAPPROVED.equals(l_orderUnitRow.getApproveStatusType())
            || OrderOpenStatusEnum.CLOSED.equals(l_orderUnitRow.getOrderOpenStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        try
        {
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd"));
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (get�������ϗp�l�����)<BR>
     * �l������l���v�Z���A�ԋp����B<BR>
     * ���������σT�[�r�X�ɂ����āA���������}�[�P�b�g���C�N�����̏ꍇ�A<BR>
     * �@@�s��F�h���؁h�A��l�F�h�O���I�l�h�Ōv�Z�����l������ŁA���ς��s���B<BR>
     * <BR>
     * �P�j�@@��l�擾<BR>
     * �@@����.�������.��l�i�I�l�j���擾����B<BR>
     * <BR>
     * �Q�j�@@�l���擾 <BR>
     * �@@this.calc�l����call����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�h���؁h�i�Œ�j<BR>
     * �@@�@@�@@��l�F�@@�P�j�Ŏ擾������l�i�I�l�j<BR>
     * <BR>
     * �R�j�@@���ݒl�擾 <BR>
     * �@@�R�|�P�j�@@���ݒl�擾�p��l���v�Z<BR>
     * �@@�@@�ȉ��v�Z���ŁA���ݒl�擾�p��l���Z�o����B<BR>
     * <BR>
     * �@@�@@�@@���v�Z����<BR>
     * �@@�@@�@@�@@���ݒl�擾�p��l���P�j�Ŏ擾������l�{�Q�j�Ŏ擾���������l��<BR>
     * <BR>
     * �@@�R�|�Q�j<BR>
     * �@@�@@�y�Ēl�e�[�u���z���A�ȉ��̏����Ō������A���ݒl���擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@������������<BR>
     *  �@@�@@�Ēl�e�[�u��.�s��R�[�h�@@�� �h���؁h<BR>
     * �@@�@@�@@and �i�i�Ēl�e�[�u��.�����l �� ���ݒl�擾�p��l<BR>
     * �@@�@@�@@and �i���ݒl�擾�p��l �� �Ēl�e�[�u��.����l�j�j<BR>
     * <BR>
     * �S�j�@@�l������l�擾 <BR>
     * �@@���������R���ʃ`�F�b�N.calc�l�����(��l,�l��,�w�l�P��)���R�[������B <BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@��l�F�@@�P�j�Ŏ擾������l�i�I�l�j<BR>
     * �@@�@@�@@�l���F�@@�Q�j�̖߂�l<BR>
     * �@@�@@�@@�w�l�P�ʁF�@@�R�|�Q�j�̖߂�l<BR>
     * <BR>
     * �T�j�@@�S�jcalc�l�����()�̖߂�l��ԋp����B <BR>
     * @@param l_tradedProduct - (�������)<BR>
     * �������
     * @@return double
     * @@throws WEB3BaseException
     */
    public static double getForcedSettleHighPriceRange(
        WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForcedSettleHighPriceRange("
            + "WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //��l�擾
        //�@@����.�������.��l�i�I�l�j���擾����B
        double l_dblLCPrice = l_tradedProduct.getLastClosingPrice();

        //�l���擾
        //�@@this.calc�l����call����B
        //�@@�@@[����]
        //�@@�@@�@@�s��R�[�h�F�@@�h���؁h�i�Œ�j
        //�@@�@@�@@��l�F�@@�P�j�Ŏ擾������l�i�I�l�j
        double l_dblRange = WEB3AdminPMEquityDataManager.calcPriceRange(
            WEB3MarketCodeDef.TOKYO,
            l_dblLCPrice);

        //���ݒl�擾
        //���ݒl�擾�p��l���v�Z
        //�@@���v�Z����
        //�@@�@@���ݒl�擾�p��l���P�j�Ŏ擾������l�{�Q�j�Ŏ擾���������l��
        double l_dblTickValue = l_dblLCPrice + l_dblRange;

        //�y�Ēl�e�[�u���z���A�ȉ��̏����Ō������A���ݒl���擾����B
        //�@@�@@�@@�@@������������
        //�@@�@@�Ēl�e�[�u��.�s��R�[�h�@@�� �h���؁h
        //�@@�@@�@@and �i�i�Ēl�e�[�u��.�����l �� ���ݒl�擾�p��l
        //�@@�@@�@@and �i���ݒl�擾�p��l �� �Ēl�e�[�u��.����l�j�j
        StringBuffer l_sbQuery = new StringBuffer();
        l_sbQuery.append(" market_code = ? ");
        l_sbQuery.append(" and ((low_price < ?) ");
        l_sbQuery.append(" and (? <= cap_price)) ");

        Object[] l_object = {
            WEB3MarketCodeDef.TOKYO,
            WEB3StringTypeUtility.formatNumber(l_dblTickValue),
            WEB3StringTypeUtility.formatNumber(l_dblTickValue)};

        List l_listResult = null;
        double l_dblValueUnit = 0;

        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_listResult = l_processor.doFindAllQuery(
                EquityTickValuesMstRow.TYPE,
                l_sbQuery.toString(),
                l_object);

            if (l_listResult.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME);
            }

            EquityTickValuesMstRow l_mstRow =
                (EquityTickValuesMstRow)l_listResult.get(0);
            l_dblValueUnit = l_mstRow.getTickValue();
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�l������l�擾
        //�@@���������R���ʃ`�F�b�N.calc�l�����(��l,�l��,�w�l�P��)���R�[������B
        //�@@[����]
        //�@@�@@�@@��l�F�@@�P�j�Ŏ擾������l�i�I�l�j
        //�@@�@@�@@�l���F�@@�Q�j�̖߂�l
        //�@@�@@�@@�w�l�P�ʁF�@@�R�|�Q�j�̖߂�l
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations)
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        double l_dblHighPriceRange =
            l_orderMgrResVal.calcStopHighPrice(l_dblLCPrice, l_dblRange, l_dblValueUnit);

        log.exiting(STR_METHOD_NAME);
        return l_dblHighPriceRange;
    }

    /**
     * (get�������ϗp�l������)<BR>
     * �l�������l���v�Z���A�ԋp����B<BR>
     * ���������σT�[�r�X�ɂ����āA���������}�[�P�b�g���C�N�����̏ꍇ�A<BR>
     * �@@�s��F�h���؁h�A��l�F�h�O���I�l�h�Ōv�Z�����l�������ŁA���ς��s���B<BR>
     * <BR>
     * �P�j�@@��l�擾<BR>
     * �@@����.�������.��l�i�I�l�j���擾����B<BR>
     * <BR>
     * �Q�j�@@�l���擾 <BR>
     * �@@this.calc�l����call����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�h���؁h�i�Œ�j<BR>
     * �@@�@@�@@��l�F�@@�P�j�Ŏ擾������l�i�I�l�j<BR>
     * <BR>
     * �R�j�@@�l�������l�擾 <BR>
     * �@@[�i��l�|�l���j��0�̏ꍇ<BR>
     * �@@�@@1��ԋp����B <BR>
     * <BR>
     * �@@[�ȊO�̏ꍇ]<BR>
     * �@@�@@�i��l�|�l���j��ԋp����B <BR>
     * @@param l_tradedProduct - (�������)<BR>
     * �������<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public static double getForcedSettleLowPriceRange(
        WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForcedSettleLowPriceRange("
            + "WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //��l�擾
        //�@@����.�������.��l�i�I�l�j���擾����B
        double l_dblLCPrice = l_tradedProduct.getLastClosingPrice();

        //�l���擾
        //�@@this.calc�l����call����B
        //�@@�@@[����]
        //�@@�@@�@@�s��R�[�h�F�@@�h���؁h�i�Œ�j
        //�@@�@@�@@��l�F�@@�P�j�Ŏ擾������l�i�I�l�j
        double l_dblRange = WEB3AdminPMEquityDataManager.calcPriceRange(
            WEB3MarketCodeDef.TOKYO,
            l_dblLCPrice);

        //�l�������l�擾
        //�@@[�i��l�|�l���j��0�̏ꍇ
        //�@@�@@1��ԋp����B
        BigDecimal l_bdLowPriceRange = new BigDecimal("0");
        BigDecimal l_bdLCPrice = new BigDecimal(l_dblLCPrice + "");
        BigDecimal l_bdRange = new BigDecimal(l_dblRange + "");
        l_bdLowPriceRange = l_bdLCPrice.subtract(l_bdRange);
        if(l_bdLowPriceRange.doubleValue() <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return 1;
        }

        //[�ȊO�̏ꍇ]
        //�@@�i��l�|�l���j��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_bdLowPriceRange.doubleValue();
    }

    /**
     * (calc�l��)<BR>
     * �����̎s��R�[�h�A��l�ɊY������l�����擾���āA�ԋp����B<BR>
     * ����������̒l���敪�͍l���Ȃ��B<BR>
     * <BR>
     * �P�j�@@�����l�����擾����<BR>
     * �@@�@@�y�l���e�[�u���z���A�ȉ��̏����Ō������A�Y�����R�[�h�́u�l���v���u�����l���v�Ƃ���B<BR>
     * <BR>
     * �@@�@@�@@�@@������������<BR>
     *  �@@�@@�l���e�[�u��.�s��R�[�h�@@�� ����.�s��R�[�h<BR>
     * �@@�@@�@@and �i�i�l���e�[�u��.�����l �� ����.��l�j<BR>
     * �@@�@@�@@and �i����.��l �� �l���e�[�u��.����l�j�j<BR>
     * <BR>
     * �Q�j�@@�����l����ԋp����<BR>
     * �@@�P�j�Ŏ擾�����l��ԋp����B<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@param l_dblBaseValue - (��l)<BR>
     * ��l<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public static double calcPriceRange(String l_strMarketCode, double l_dblBaseValue)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcPriceRange(String, double)";
        log.entering(STR_METHOD_NAME);

        //�����l�����擾����
        //�@@�y�l���e�[�u���z���A�ȉ��̏����Ō������A�Y�����R�[�h�́u�l���v���u�����l���v�Ƃ���B
        //�@@�@@�@@�@@������������
        //�@@�@@�l���e�[�u��.�s��R�[�h�@@�� ����.�s��R�[�h
        //�@@�@@�@@and �i�i�l���e�[�u��.�����l �� ����.��l�j
        // �@@�@@�@@and �i����.��l �� �l���e�[�u��.����l�j�j
        StringBuffer l_sbQuery = new StringBuffer();
        l_sbQuery.append(" market_code = ? ");
        l_sbQuery.append(" and ((low_price <= ? ) ");
        l_sbQuery.append(" and (? < cap_price)) ");
        Object[] l_object = {
            l_strMarketCode,
            WEB3StringTypeUtility.formatNumber(l_dblBaseValue),
            WEB3StringTypeUtility.formatNumber(l_dblBaseValue)};

        List l_listResult = null;
        double l_dblRange = 0;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_listResult = l_processor.doFindAllQuery(
                EquityLimitPriceRangeMstRow.TYPE,
                l_sbQuery.toString(),
                l_object);

            if (l_listResult.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME);
            }

            EquityLimitPriceRangeMstRow l_mstRow =
                (EquityLimitPriceRangeMstRow)l_listResult.get(0);
            l_dblRange = l_mstRow.getRange();
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�����l����ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_dblRange;
    }

    /**
     * (get���ϒ������ꗗ)<BR>
     * �������ϑΏی����ɑ΂��錈�ϒ�������������B<BR>
     * �����ΏۂƂȂ錈�ϒ����͈ȉ��B<BR>
     * �@@�E�ԍρA�����A���n<BR>
     * �@@�E�ۗL�����ɑ΂���\�񒍕��i�ԍρA�����A���n�j<BR>
     * <BR>
     * �@@���������ϒ������擾����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����.����Row�̌���ID�ɕR�t���A�����ԍώw������擾����B<BR>
     * �@@QueryProcessor.doFindAllQuery()�ɂ��A<BR>
     * �@@�ȉ����������ɂāA�����ԍώw����e�[�u���ieqtype_closing_contract_spec�j����������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�����ԍώw����.����ID �� ����.����Row.getContractId()<BR>
     * <BR>
     * �R�j�@@�Q�j�Ń��R�[�h���擾�ł����ꍇ�A�����P�ʂ��擾����B<BR>
     * �@@�R�|�P�j�@@����ID�̎擾<BR>
     * �@@�@@�Q�j�̖߂�l�̗v�f����Loop�������A�S�Ă̗v�f�̒���ID���擾����B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�����P�ʂ̌���<BR>
     * �@@�@@QueryProcessor.doFindAllQuery()�ɂ��A<BR>
     * �@@�@@�ȉ����������ɂāA�����P�ʃe�[�u���ieqtype_order_unit�j����������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�����P��.����ID ���� �i �R�|�P�j�Ŏ擾���������h�c�j���A<BR>
     * �@@�@@�@@�@@�����P��.�����L����� �� "�I�[�v��"<BR>
     * <BR>
     * �@@�R�|�R�j�������ʂ�߂�l�I�u�W�F�N�g�ɒǉ�����B<BR>
     * <BR>
     * �S�j�@@�A�����������{/�����{�ɂ�鏈������<BR>
     * �@@����.is�\�񒍕��l���v �� false�̏ꍇ�A<BR>
     * �@@�擾���������P�ʂ�Lis����ԋp����B�ireturn;�j<BR>
     * <BR>
     * �@@�ȊO�̏ꍇ�A�ȍ~�̏������s���B<BR>
     * �@@�@@���A���������{��Ђ̂݁A�\�񒍕��P�ʂ̌������s���B<BR>
     * <BR>
     * �T�j�@@����.����Row�̌���ID�ɕR�t���A�����\�񌚊��ԍώw������擾����B<BR>
     * �@@���������ɑ΂��錈�ς̗\�񒍕��̂݁A�����\�񌚊��ԍώw����ɓo�^����Ă���B<BR>
     * <BR>
     * �@@QueryProcessor.doFindAllQuery()�ɂ��A<BR>
     * �@@�ȉ����������ɂāA�����\�񌚊��ԍώw����ð��فirsv_eq_closing_contract_spec�j����������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�����\�񌚊��ԍώw����.����ID �� ����.����Row.getContractId()<BR>
     * <BR>
     * �U�j�@@�T�j�Ń��R�[�h���擾�ł����ꍇ�A�����P�ʂ��擾����B<BR>
     * �@@�U�|�P�j�@@����ID�̎擾<BR>
     * �@@�@@�T�j�̖߂�l�̗v�f����Loop�������A�S�Ă̗v�f�̒���ID���擾����B<BR>
     * <BR>
     * �@@�U�|�Q�j�@@�\�񒍕��P�ʂ̌���<BR>
     * �@@�@@QueryProcessor.doFindAllQuery()�ɂ��A<BR>
     * �@@�@@�ȉ����������ɂāA�����\�񒍕��P�ʃe�[�u���irsv_eq_order_unit�j����������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�����\�񒍕��P��.����ID ���� �i �U�|�P�j�Ŏ擾���������h�c�j���A<BR>
     * �@@�@@�@@�@@�����\�񒍕��P��.�����L����� �� "�I�[�v��"<BR>
     * <BR>
     * �@@�U�|�R�j�������ʂ�߂�l�I�u�W�F�N�g�ɒǉ�����B<BR>
     * <BR>
     * �V�j�@@�߂�l�ԋp<BR>
     * �@@�擾���������P�ʂ�List��ԋp����B<BR>
     * @@param l_contractRow - (����Row)<BR>
     * ����Row�I�u�W�F�N�g<BR>
     * @@param l_isReservationOrder - (is�\�񒍕��l���v)<BR>
     * �\�񒍕����������邩�ǂ����̃t���O<BR>
     * <BR>
     * true�F�@@�\�񒍕�����������B<BR>
     * false�F�@@�\�񒍕��̌������s��Ȃ��B<BR>
     * @@return ArrayList
     */
    public static ArrayList getCloseOrderList(
        EqtypeContractRow l_contractRow,
        boolean l_isReservationOrder)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCloseOrderList(EqtypeContractRow, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_contractRow == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        QueryProcessor l_processor = null;
        List l_lisResult = null;
        List l_lisRsvResult = null;

        try
        {
            l_processor = Processors.getDefaultProcessor();
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����B
        ArrayList l_arrayList = new ArrayList();

        //����.����Row�̌���ID�ɕR�t���A�����ԍώw������擾����B
        // QueryProcessor.doFindAllQuery()�ɂ��A
        //�ȉ����������ɂāA�����ԍώw����e�[�u���ieqtype_closing_contract_spec�j����������B
        //�@@�@@[����]
        //�@@�@@�@@�����ԍώw����.����ID �� ����.����Row.getContractId()
        String l_strQuery = " contract_id = ? ";
        Object[] l_object = {l_contractRow.getContractId() + ""};
        try
        {
            l_lisResult = l_processor.doFindAllQuery(
                EqtypeClosingContractSpecRow.TYPE,
                l_strQuery,
                l_object);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���R�[�h���擾�ł����ꍇ�A�����P�ʂ��擾����B
        //����ID�̎擾
        //�߂�l�̗v�f����Loop�������A�S�Ă̗v�f�̒���ID���擾����B
        int l_intSize = 0;
        if (l_lisResult != null && l_lisResult.size() != 0)
        {
            l_intSize = l_lisResult.size();
        }

        if (l_intSize != 0)
        {
            //�����P�ʂ̌���
            //�@@�@@QueryProcessor.doFindAllQuery()�ɂ��A
            //�@@�@@�ȉ����������ɂāA�����P�ʃe�[�u���ieqtype_order_unit�j����������B
            //�@@�@@�@@[����]
            //�@@�@@�@@�@@�����P��.����ID ���� �i �R�|�P�j�Ŏ擾���������h�c�j���A
            //�@@�@@�@@�@@�����P��.�����L����� �� "�I�[�v��"
            StringBuffer l_sbQuery = new StringBuffer();
            l_sbQuery.append(" order_id in ( ");
            ArrayList l_orderUnit = new ArrayList();
            for(int i = 0; i < l_intSize; i++)
            {
                EqtypeClosingContractSpecRow l_specRow =
                    (EqtypeClosingContractSpecRow)l_lisResult.get(i);
                String l_strOrderId = l_specRow.getOrderId() + "";

                l_sbQuery.append(" ? ,");
                l_orderUnit.add(l_strOrderId);
            }
            l_sbQuery.deleteCharAt(l_sbQuery.length() - 1);
            l_sbQuery.append(") ");
            l_sbQuery.append(" and order_open_status = ? ");
            l_orderUnit.add(OrderOpenStatusEnum.OPEN.intValue() + "");
            Object[] l_strValues = new Object[l_orderUnit.size()];
            l_orderUnit.toArray(l_strValues);

            try
            {
                l_lisResult = l_processor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbQuery.toString(),
                    l_strValues);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�������ʂ�߂�l�I�u�W�F�N�g�ɒǉ�����B
            if (l_lisResult != null && l_lisResult.size() != 0)
            {
                for(int i = 0; i < l_lisResult.size(); i++)
                {
                    l_arrayList.add(l_lisResult.get(i));
                }
            }
        }

        //�A�����������{/�����{�ɂ�鏈������
        //�@@����.is�\�񒍕��l���v �� false�̏ꍇ�A
        //�@@�擾���������P�ʂ�Lis����ԋp����B�ireturn;�j
        if(!l_isReservationOrder)
        {
            log.exiting(STR_METHOD_NAME);
            return l_arrayList;
        }

        //�ȊO�̏ꍇ�A�ȍ~�̏������s���B
        //���A���������{��Ђ̂݁A�\�񒍕��P�ʂ̌������s���B
        //����.����Row�̌���ID�ɕR�t���A�����\�񌚊��ԍώw������擾����B
        //�@@���������ɑ΂��錈�ς̗\�񒍕��̂݁A�����\�񌚊��ԍώw����ɓo�^����Ă���B
        //�@@QueryProcessor.doFindAllQuery()�ɂ��A
        //�@@�ȉ����������ɂāA�����\�񌚊��ԍώw����ð��فirsv_eq_closing_contract_spec�j����������B
        //�@@�@@[����]
        //�@@�@@�@@�����\�񌚊��ԍώw����.����ID �� ����.����Row.getContractId()
        try
        {
            l_lisRsvResult = l_processor.doFindAllQuery(
                RsvEqClosingContractSpecRow.TYPE,
                l_strQuery,
                l_object);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intRsvResultCnt = 0;
        if (!l_lisRsvResult.isEmpty())
        {
            l_intRsvResultCnt = l_lisRsvResult.size();
        }
        // �Ń��R�[�h���擾�ł����ꍇ�A�����P�ʂ��擾����B
        if (l_intRsvResultCnt != 0)
        {
            // ����ID�̎擾
            //    �T�j�̖߂�l�̗v�f����Loop�������A�S�Ă̗v�f�̒���ID���擾����B
            //    �@@�\�񒍕��P�ʂ̌���
            //     �@@�@@QueryProcessor.doFindAllQuery()�ɂ��A
            //     �@@�@@�ȉ����������ɂāA�����\�񒍕��P�ʃe�[�u���irsv_eq_order_unit�j����������B
            //     �@@�@@�@@[����]
            //     �@@�@@�@@�@@�����\�񒍕��P��.����ID�����i�U�|�P�j�Ŏ擾���������h�c�j���A
            //     �@@�@@�@@�@@�����\�񒍕��P��.�����L����ԁ�"�I�[�v��"
            StringBuffer l_sbQuery = new StringBuffer();
            l_sbQuery.append(" order_id in ( ");
            ArrayList l_orderUnit = new ArrayList();
            for(int i = 0; i < l_intRsvResultCnt; i++)
            {
                RsvEqClosingContractSpecRow l_rsvEqClosingContractSpecRow =
                    (RsvEqClosingContractSpecRow)l_lisRsvResult.get(i);
                String l_strOrderId = l_rsvEqClosingContractSpecRow.getOrderId() + "";

                l_sbQuery.append(" ? ,");
                l_orderUnit.add(l_strOrderId);
            }
            l_sbQuery.deleteCharAt(l_sbQuery.length() - 1);
            l_sbQuery.append(") ");
            l_sbQuery.append(" and order_open_status = ? ");
            l_orderUnit.add(OrderOpenStatusEnum.OPEN.intValue() + "");
            Object[] l_objValues = new Object[l_orderUnit.size()];
            l_orderUnit.toArray(l_objValues);

            try
            {
                l_lisRsvResult = l_processor.doFindAllQuery(
                    RsvEqOrderUnitRow.TYPE,
                    l_sbQuery.toString(),
                    l_objValues);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�������ʂ�߂�l�I�u�W�F�N�g�ɒǉ�����B
            if (l_lisRsvResult != null && l_lisRsvResult.size() != 0)
            {
                for(int i = 0; i < l_lisRsvResult.size(); i++)
                {
                    l_arrayList.add(l_lisRsvResult.get(i));
                }
            }
        }

        //�߂�l�ԋp
        //�擾���������P�ʂ�List��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_arrayList;
    }

    /**
     * (create�������ϗ��R���)<BR>
     * �����̋������ϗ��R�敪�ɊY������A<BR>
     * �������ϗ��R�����쐬����B<BR>
     * <BR>
     * �P�j�@@����.�������ϗ��R�敪��"��������"�̏ꍇ<BR>
     * �@@�P�|�P�j�@@�������ϗ��R���C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�������ϗ��R�F�@@"���ϊ�������"<BR>
     * �@@�@@�ۏ؋��ێ����F�@@null<BR>
     * �@@�@@�Ǐ،o�ߓ�������F�@@null<BR>
     * <BR>
     * <BR>
     * �Q�j�@@����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�y�x�j"�̏ꍇ<BR>
     * �@@�Q�|�P�j�@@�ۏ؋��ێ����i�y�x�j�擾<BR>
     * �@@�@@�ȉ��̏����ŕ��X�v���t�@@�����X�e�[�u������������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���XID = ����.���X.���XID And<BR>
     * �@@�@@�@@�v���t�@@�����X�� = "first.deposit.rate1"<BR>
     * <BR>
     * �@@�@@�����R�[�h���擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�Ǐ،o�ߓ����擾<BR>
     * �@@�@@�ȉ��̏����ŕ��X�v���t�@@�����X�e�[�u������������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���XID = ����.���X.���XID And<BR>
     * �@@�@@�@@�v���t�@@�����X�� = "first.margin.pass.day1"<BR>
     * <BR>
     * �@@�@@�����R�[�h���擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�������ϗ��R���C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�S�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�������ϗ��R�F�@@"�ۏ؋��ێ������i�I�����C���J�n�O�E�y�x�j"<BR>
     * �@@�@@�ۏ؋��ێ����F�@@�Q�|�P�j�ɂĎ擾�������R�[�h.�v���t�@@�����X�̒l<BR>
     * �@@�@@�Ǐ،o�ߓ�������F�@@�Q�|�Q�j�ɂĎ擾�������R�[�h.�v���t�@@�����X�̒l<BR>
     * <BR>
     * <BR>
     * �R�j�@@����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�d�x�j"�̏ꍇ<BR>
     * �@@�R�|�P�j�@@�ۏ؋��ێ����i�d�x�j�擾<BR>
     * �@@�@@�ȉ��̏����ŕ��X�v���t�@@�����X�e�[�u������������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���XID = ����.���X.���XID And<BR>
     * �@@�@@�@@�v���t�@@�����X�� = "first.deposit.rate2"<BR>
     * <BR>
     * �@@�@@�����R�[�h���擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�Ǐ،o�ߓ����擾<BR>
     * �@@�@@�ȉ��̏����ŕ��X�v���t�@@�����X�e�[�u������������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���XID = �p�����[�^.���X.���XID And<BR>
     * �@@�@@�@@�v���t�@@�����X�� = "first.margin.pass.day2"<BR>
     * <BR>
     * �@@�@@�����R�[�h���擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �@@�R�|�R�j�@@�������ϗ��R���C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�R�|�S�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�������ϗ��R�F�@@"�ۏ؋��ێ������i�I�����C���J�n�O�E�d�x�j"<BR>
     * �@@�@@�ۏ؋��ێ����F�@@�R�|�P�j�ɂĎ擾�������R�[�h.�v���t�@@�����X�̒l<BR>
     * �@@�@@�Ǐ،o�ߓ�������F�@@�R�|�Q�j�ɂĎ擾�������R�[�h.�v���t�@@�����X�̒l<BR>
     * <BR>
     * <BR>
     * �S�j�@@����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j"�̏ꍇ <BR>
     * �@@�S�|�P�j�@@�������ϗ��R���C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �@@�S�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B <BR>
     * �@@�@@�������ϗ��R�F�@@"�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j" <BR>
     * �@@�@@�ۏ؋��ێ����F�@@null <BR>
     * �@@�@@�Ǐ،o�ߓ�������F�@@null <BR>
     * <BR>
     * <BR>
     * �T�j�@@����.�������ϗ��R�敪��"�ۏ؋��ێ�������i��ԁj"�̏ꍇ <BR>
     * �@@�T�|�P�j�@@�������ϗ��R���C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �@@�T�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B <BR>
     * �@@�@@�������ϗ��R�F�@@"�ۏ؋��ێ������i��ԁj" <BR>
     * �@@�@@�ۏ؋��ێ����F�@@null <BR>
     * �@@�@@�Ǐ،o�ߓ�������F�@@null <BR>
     * <BR>
     * <BR>
     * �U�j�@@����.�������ϗ��R�敪��"�蓮��������"�̏ꍇ <BR>
     * �@@�U�|�P�j�@@�������ϗ��R���C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �@@�U�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B <BR>
     * �@@�@@�������ϗ��R�F�@@"�蓮��������" <BR>
     * �@@�@@�ۏ؋��ێ����F�@@null <BR>
     * �@@�@@�Ǐ،o�ߓ�������F�@@null <BR>
     * <BR>
     * <BR>
     * �V�j�@@���������C���X�^���X��ԋp����B <BR>
     * <BR>
     * �@@�@@������.�������ϗ��R�敪���S�Ăɓ��Ă͂܂�Ȃ��ꍇ�́A <BR>
     * �@@�@@�@@�u�p�����[�^�l�s���v�̗�O��throw����B <BR>
     * <BR>
     * @@param l_strForcedSettleReasonDiv - (�������ϗ��R�敪)<BR>
     * �������ϗ��R�敪
     * @@param l_branch - (���X)
     * ���X
     * @@return WEB3AdminForcedSettleReasonUnit
     * @@throws WEB3BaseException
     */
    public static WEB3AdminForcedSettleReasonUnit creatForcedSettleReasonUnit(
        String l_strForcedSettleReasonDiv,
        Branch l_branch) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " creatForcedSettleReasonUnit(String, Branch)";
        log.entering(STR_METHOD_NAME);

        if (l_branch == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �P�j�@@����.�������ϗ��R�敪��"��������"�̏ꍇ
        if (WEB3ForcedSettleReasonType.FIXED_DATE_COMING.equals(l_strForcedSettleReasonDiv))
        {
            // �P�|�P�j�@@�������ϗ��R���C���X�^���X�𐶐�����B
            WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
                new WEB3AdminForcedSettleReasonUnit();
            // �P�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
            // �������ϗ��R�F�@@"���ϊ�������"
            // �ۏ؋��ێ����F�@@null
            // �Ǐ،o�ߓ�������F�@@null
            l_forcedSettleReasonUnit.forcedSettleReason =
                WEB3ForcedSettleReasonType.FIXED_DATE_COMING;
            l_forcedSettleReasonUnit.marginMaintenanceRate = null;
            l_forcedSettleReasonUnit.additionalElapsedDaysUpperLimit = null;

            log.exiting(STR_METHOD_NAME);
            return l_forcedSettleReasonUnit;
        }

        // �Q�j�@@����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�y�x�j"�̏ꍇ
        else if (WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS.equals(
            l_strForcedSettleReasonDiv))
        {
           try
           {
               // �Q�|�P�j�@@�ۏ؋��ێ����i�y�x�j�擾
                // �ȉ��̏����ŕ��X�v���t�@@�����X�e�[�u������������B
                // ���XID = ����.���X.���XID And
                // �v���t�@@�����X�� = "first.deposit.rate1"
                StringBuffer l_sbQueryCond = new StringBuffer();
                l_sbQueryCond.append(" branch_id = ? ");
                l_sbQueryCond.append(" and name = ? ");

                Object[] l_objWhere = {new Long(l_branch.getBranchId()),
                    WEB3BranchPreferencesNameDef.FIRST_DEPOSIT_RATE1};

                QueryProcessor l_processor = Processors.getDefaultProcessor();
                List l_lisResultList = l_processor.doFindAllQuery(
                    BranchPreferencesRow.TYPE,
                    l_sbQueryCond.toString(),
                    l_objWhere);

                // �����R�[�h���擾�ł��Ȃ������ꍇ�Anull��ԋp����B
                if (l_lisResultList.isEmpty())
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                // �Q�|�Q�j�@@�Ǐ،o�ߓ����擾
                // �ȉ��̏����ŕ��X�v���t�@@�����X�e�[�u������������B
                // ���XID = ����.���X.���XID And
                // �v���t�@@�����X�� = "first.margin.pass.day1"
                StringBuffer l_sbQueryCondNum = new StringBuffer();
                l_sbQueryCondNum.append(" branch_id = ? ");
                l_sbQueryCondNum.append(" and name = ? ");

                Object[] l_objWhereNum = {new Long(l_branch.getBranchId()),
                    WEB3BranchPreferencesNameDef.FIRST_MARGIN_PASS_DAY1};

                List l_lisResultListNums = l_processor.doFindAllQuery(
                    BranchPreferencesRow.TYPE,
                    l_sbQueryCondNum.toString(),
                    l_objWhereNum);

                // �����R�[�h���擾�ł��Ȃ������ꍇ�Anull��ԋp����B 
                if (l_lisResultListNums.isEmpty())
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                // �Q�|�R�j�@@�������ϗ��R���C���X�^���X�𐶐�����B
                WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
                    new WEB3AdminForcedSettleReasonUnit();

                // �Q�|�S�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                // �������ϗ��R�F�@@"�ۏ؋��ێ������i�I�����C���J�n�O�E�y�x�j"
                l_forcedSettleReasonUnit.forcedSettleReason =
                    WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS;
                // �ۏ؋��ێ����F�@@�Q�|�P�j�ɂĎ擾�������R�[�h.�v���t�@@�����X�̒l
                BranchPreferencesRow l_branchPreferenceRow =
                    (BranchPreferencesRow)l_lisResultList.get(0);
                l_forcedSettleReasonUnit.marginMaintenanceRate =
                    l_branchPreferenceRow.getValue();
                // �Ǐ،o�ߓ�������F�@@�Q�|�Q�j�ɂĎ擾�������R�[�h.�v���t�@@�����X�̒l
                BranchPreferencesRow l_branchPreferenceRow1 =
                    (BranchPreferencesRow)l_lisResultListNums.get(0);
                l_forcedSettleReasonUnit.additionalElapsedDaysUpperLimit =
                    l_branchPreferenceRow1.getValue();

                log.exiting(STR_METHOD_NAME);
                return l_forcedSettleReasonUnit;
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        // �R�j�@@����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�d�x�j"�̏ꍇ
        else if (WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS.equals(
            l_strForcedSettleReasonDiv))
        {
            try
            {
                // �R�|�P�j�@@�ۏ؋��ێ����i�d�x�j�擾
                // �ȉ��̏����ŕ��X�v���t�@@�����X�e�[�u������������B
                // ���XID = ����.���X.���XID And
                // �v���t�@@�����X�� = "first.deposit.rate2"
                StringBuffer l_sbQueryCond = new StringBuffer();
                l_sbQueryCond.append(" branch_id = ? ");
                l_sbQueryCond.append(" and name = ? ");

                Object[] l_objWhere = {new Long(l_branch.getBranchId()),
                    WEB3BranchPreferencesNameDef.FIRST_DEPOSIT_RATE2};

                QueryProcessor l_processor = Processors.getDefaultProcessor();
                List l_lisResultList = l_processor.doFindAllQuery(
                    BranchPreferencesRow.TYPE,
                    l_sbQueryCond.toString(),
                    l_objWhere);

                // �����R�[�h���擾�ł��Ȃ������ꍇ�Anull��ԋp����B
                if (l_lisResultList.isEmpty())
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                // �R�|�Q�j�@@�Ǐ،o�ߓ����擾
                // �ȉ��̏����ŕ��X�v���t�@@�����X�e�[�u������������B
                // ���XID = �p�����[�^.���X.���XID And
                // �v���t�@@�����X�� = "first.margin.pass.day2"
                StringBuffer l_sbQueryCond2 = new StringBuffer();
                l_sbQueryCond2.append(" branch_id = ? ");
                l_sbQueryCond2.append(" and name = ? ");

                Object[] l_objWhere2 = {new Long(l_branch.getBranchId()),
                    WEB3BranchPreferencesNameDef.FIRST_MARGIN_PASS_DAY2};

                List l_lisResultList2 = l_processor.doFindAllQuery(
                    BranchPreferencesRow.TYPE,
                    l_sbQueryCond2.toString(),
                    l_objWhere2);

                // �����R�[�h���擾�ł��Ȃ������ꍇ�Anull��ԋp����B
                if (l_lisResultList2.isEmpty())
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                // �R�|�R�j�@@�������ϗ��R���C���X�^���X�𐶐�����B
                WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
                    new WEB3AdminForcedSettleReasonUnit();

                // �R�|�S�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                // �������ϗ��R�F�@@"�ۏ؋��ێ������i�I�����C���J�n�O�E�d�x�j"
                l_forcedSettleReasonUnit.forcedSettleReason =
                    WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS;
                // �ۏ؋��ێ����F�@@�R�|�P�j�ɂĎ擾�������R�[�h.�v���t�@@�����X�̒l
                BranchPreferencesRow l_branchPreferenceRow =
                    (BranchPreferencesRow)l_lisResultList.get(0);
                l_forcedSettleReasonUnit.marginMaintenanceRate = l_branchPreferenceRow.getValue();
                // �Ǐ،o�ߓ�������F�@@�R�|�Q�j�ɂĎ擾�������R�[�h.�v���t�@@�����X�̒l
                BranchPreferencesRow l_branchPreferenceRow2 =
                    (BranchPreferencesRow)l_lisResultList2.get(0);
                l_forcedSettleReasonUnit.additionalElapsedDaysUpperLimit =
                    l_branchPreferenceRow2.getValue();

                log.exiting(STR_METHOD_NAME);
                return l_forcedSettleReasonUnit;
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //�S�j�@@����.�������ϗ��R�敪��"�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j"�̏ꍇ
        else if (WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL.equals(
            l_strForcedSettleReasonDiv))
        {
            //�S�|�P�j�@@�������ϗ��R���C���X�^���X�𐶐�����B
            WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
                new WEB3AdminForcedSettleReasonUnit();
            //�S�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
            //�@@�@@�������ϗ��R�F�@@"�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j"
            //�@@�@@�ۏ؋��ێ����F�@@null
            //�@@�@@�Ǐ،o�ߓ�������F�@@null
            l_forcedSettleReasonUnit.forcedSettleReason =
                WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL;
            l_forcedSettleReasonUnit.marginMaintenanceRate = null;
            l_forcedSettleReasonUnit.additionalElapsedDaysUpperLimit = null;

            log.exiting(STR_METHOD_NAME);
            return l_forcedSettleReasonUnit;
        }
        //�T�j�@@����.�������ϗ��R�敪��"�ۏ؋��ێ�������i��ԁj"�̏ꍇ
        else if (WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET.equals(
            l_strForcedSettleReasonDiv))
        {
            //�@@�T�|�P�j�@@�������ϗ��R���C���X�^���X�𐶐�����B
            WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
                new WEB3AdminForcedSettleReasonUnit();
            //�@@�T�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
            //�@@�@@�������ϗ��R�F�@@"�ۏ؋��ێ������i��ԁj"
            //�@@�@@�ۏ؋��ێ����F�@@null
            //�@@�@@�Ǐ،o�ߓ�������F�@@null
            l_forcedSettleReasonUnit.forcedSettleReason =
                WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET;
            l_forcedSettleReasonUnit.marginMaintenanceRate = null;
            l_forcedSettleReasonUnit.additionalElapsedDaysUpperLimit = null;

            log.exiting(STR_METHOD_NAME);
            return l_forcedSettleReasonUnit;
        }

        // �U�j�@@����.�������ϗ��R�敪��"�蓮��������"�̏ꍇ
        else if (WEB3ForcedSettleReasonType.MANUAL_FORCED_SETTLE.equals(l_strForcedSettleReasonDiv))
        {
            // �U�|�P�j�@@�������ϗ��R���C���X�^���X�𐶐�����B
            WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
                new WEB3AdminForcedSettleReasonUnit();

            // �U�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
            // �������ϗ��R�F�@@"�蓮��������"
            l_forcedSettleReasonUnit.forcedSettleReason =
                WEB3ForcedSettleReasonType.MANUAL_FORCED_SETTLE;
            // �ۏ؋��ێ����F�@@null
            l_forcedSettleReasonUnit.marginMaintenanceRate = null;
            // �Ǐ،o�ߓ�������F�@@null
            l_forcedSettleReasonUnit.additionalElapsedDaysUpperLimit = null;

            log.exiting(STR_METHOD_NAME);
            return l_forcedSettleReasonUnit;
        }
        // ������.�������ϗ��R�敪���S�Ăɓ��Ă͂܂�Ȃ��ꍇ�́A
        // �u�p�����[�^�l�s���v�̗�O��throw����B
        else
        {
            log.debug("�p�����[�^�l�s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager" + STR_METHOD_NAME,
                "�p�����[�^�l�s��");
        }
    }

    /**
     * (get�����ڍ�)<BR>
     * �Ǘ��ҁE�����iPTS�j�����ڍ�Unit�Ƀv���p�e�B��ݒ肷��B<BR>
     * �i�iPTS�j�o�����́A�iPTS�j�o���������R�[�������j<BR>
     * <BR>
     * <BR>
     * �P�j�@@�����f�[�^�A�_�v�^.get���i�敪()���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�����P�ʁF�@@����.�����P��<BR>
     * <BR>
     * �Q�j�@@�����f�[�^�A�_�v�^.get����敪()���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@������ʁF�@@����.�����P��.�������<BR>
     * <BR>
     * �R�j�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.get������ԋ敪()���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�����P�ʁF�@@����.�����P��<BR>
     * <BR>
     * �S�j�@@�g�����������}�l�[�W��.get���s����(SONAR)()���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@���s�����F�@@����.�����P��.���s����<BR>
     * <BR>
     * �T�j�@@�����f�[�^�A�_�v�^.get����ԋ敪()���R�[������B<BR>
     * �@@�@@[����] <BR>
     * �@@�@@�����P�ʁF�@@����.�����P��<BR>
     * <BR>
     * �U�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@arg0�F�@@����.�����P��.�s��ID<BR>
     * <BR>
     * �V�j�@@�����f�[�^�A�_�v�^.get�����敪()���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�ŋ敪�F�@@����.�����P��.�ŋ敪 <BR>
     * <BR>
     * �W�j�@@�Ǘ��ҁE�����iPTS�j�����ڍ�Unit�𐶐����A<BR>
     * �@@�@@�@@�v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����ID�@@�@@�@@�F�@@����.�����P��.����ID<BR>
     * �@@���X�R�[�h�@@�F�@@����.�����P��.getBranch().getBranchCode()<BR>
     * �@@�ڋq�R�[�h�@@�F�@@����.�����P��.getMainAccount().getAccountCode()<BR>
     * �@@�����R�[�h�@@�F�@@��������Row.get�����R�[�h()<BR>
     * �@@�s��R�[�h�@@�F�@@get�s��()�̖߂�l.get�s��R�[�h()<BR>
     * �@@�����敪�@@�@@�F�@@get�����敪()�̖߂�l<BR>
     * �@@���i�敪�@@�@@�F�@@get���i�敪()�̖߂�l<BR>
     * �@@����敪�@@�@@�F�@@get����敪()�̖߂�l<BR>
     * �@@�ٍϋ敪�@@�@@�F�@@����.�����P��.�ٍϋ敪<BR>
     * �@@���s�����@@�@@�F�@@get���s����(SONAR)()�̖߂�l<BR>
     * �@@�����L�������F�@@����.�����P��.���񒍕��̒����P��ID != null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����.�����P��.�����������t���Z�b�g�B<BR>
     * �@@�l�i�����@@�@@�@@�F�@@����.�����P��.�l�i����<BR>
     * �@@���������敪�F�@@����.�����P��.��������<BR>
     * �@@�������ʁ@@�@@�@@�F�@@����.�����P��.��������<BR>
     * �@@�����P���敪�F�@@����.�����P��.isMarketOrder()�̖߂�l��true�̏ꍇ�A"���s"���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@false�̏ꍇ��"�w�l"���Z�b�g�B <BR>
     * �@@�����P���@@�@@�@@�F�@@�����P���敪��"�w�l"�̏ꍇ�A����.�����P��.�w�l���Z�b�g�B <BR>
     * �@@��萔�ʁ@@�@@�@@�F�@@(*1)<BR>
     * �@@���P���@@�@@�@@�F�@@(*1)<BR>
     * �@@������ԋ敪�F�@@get������ԋ敪()�̖߂�l<BR>
     * �@@����ԋ敪�F�@@get����ԋ敪()�̖߂�l<BR>
     * �@@��������敪�F�@@����.�����P��.���������E����敪<BR>
     * �@@�������ԁ@@�@@�@@�F�@@����.�����P��.�󒍓��� <BR>
     * �@@�������@@�@@�@@�@@�F�@@����.�����P��.������<BR>
     * �@@��n���@@�@@�@@�@@�F�@@����.�����P��.��n��<BR>
     * <BR>
     * (*1)<BR>
     * �@@��肪���Ă���ꍇ�i����.�����P��.isUnExecuted() == false�j�A�ȉ��l���Z�b�g�B<BR>
     * �@@��萔�ʁF�@@����.�����P��.��萔��<BR>
     * �@@���P���F�@@����.�����P��.���v�����z�^����.�����P��.��萔�ʁi�~�����͎l�̌ܓ��j<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return WEB3AdminEquityPTSOrderDetailUnit
     * @@throws WEB3BaseException
     */
    public static WEB3AdminEquityPTSOrderDetailUnit getOrderUnitDetail(
        EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderUnitDetail(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@�����f�[�^�A�_�v�^.get���i�敪()���R�[������B
        String l_strProductType = WEB3EquityDataAdapter.getProductType(l_orderUnit);

        //�Q�j�@@�����f�[�^�A�_�v�^.get����敪()���R�[������B
        String l_strTradingType =
            WEB3EquityDataAdapter.getTradingType(l_orderUnit.getOrderType());
        //�R�j�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.get������ԋ敪()���R�[������B
        String l_strOrderState = getOrderState(l_orderUnit);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        //�S�j�@@�g�����������}�l�[�W��.get���s����(SONAR)()���R�[������B
        String l_strExecutionConditionTypeSonar =
            l_orderManager.getExecutionConditionTypeSonar(
                l_orderUnitRow.getExecutionConditionType());

        //�T�j�@@�����f�[�^�A�_�v�^.get����ԋ敪()���R�[������B
        String l_strExecType = WEB3EquityDataAdapter.getExecType(l_orderUnit);

        //�U�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[������B
        //�@@�@@[����]
        //�@@�@@arg0�F�@@����.�����P��.�s��ID
        WEB3GentradeFinObjectManager l_finObjectMgr =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�V�j�@@�����f�[�^�A�_�v�^.get�����敪()���R�[������B
        //�@@�@@[����]
        //�@@�@@�ŋ敪�F�@@����.�����P��.�ŋ敪
        String l_strTaxType = WEB3EquityDataAdapter.getTaxType(l_orderUnitRow.getTaxType());

        //�W�j�@@�Ǘ��ҁE�����iPTS�j�����ڍ�Unit�𐶐����A
        //�@@�@@�@@�v���p�e�B���Z�b�g����B
        WEB3AdminEquityPTSOrderDetailUnit l_orderDetailUnit = new WEB3AdminEquityPTSOrderDetailUnit();

        //�@@����ID�@@�@@�@@�F�@@����.�����P��.����ID
        l_orderDetailUnit.orderId = String.valueOf(l_orderUnitRow.getOrderId());

        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(l_orderUnitRow.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�@@���X�R�[�h�@@�F�@@����.�����P��.getBranch().getBranchCode()
        l_orderDetailUnit.branchCode = l_branch.getBranchCode();

        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_orderUnit.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�@@�ڋq�R�[�h�@@�F�@@����.�����P��.getMainAccount().getAccountCode()
        l_orderDetailUnit.accountCode = l_mainAccount.getAccountCode();

        //�@@�����R�[�h�@@�F�@@��������Row.get�����R�[�h()
        EqTypeProduct l_eqtypeProduct = (EqTypeProduct)l_orderUnit.getProduct();
        l_orderDetailUnit.productCode = l_eqtypeProduct.getProductCode();

        //�@@�s��R�[�h�@@�F�@@get�s��()�̖߂�l.get�s��R�[�h()
        l_orderDetailUnit.marketCode = l_market.getMarketCode();

        //�@@�����敪�@@�@@�F�@@get�����敪()�̖߂�l
        l_orderDetailUnit.taxType = l_strTaxType;

        //�@@���i�敪�@@�@@�F�@@get���i�敪()�̖߂�l
        l_orderDetailUnit.productDiv = l_strProductType;

        //�@@����敪�@@�@@�F�@@get����敪()�̖߂�l
        l_orderDetailUnit.tradingType = l_strTradingType;

        //�@@�ٍϋ敪�@@�@@�F�@@����.�����P��.�ٍϋ敪
        l_orderDetailUnit.repaymentDiv = l_orderUnitRow.getRepaymentType();

        //�@@���s�����@@�@@�F�@@get���s����(SONAR)()�̖߂�l
        l_orderDetailUnit.execCondType = l_strExecutionConditionTypeSonar;

        //�@@�����L�������F�@@����.�����P��.���񒍕��̒����P��ID != null�̏ꍇ�A����.�����P��.�����������t���Z�b�g�B
        if (!l_orderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_orderDetailUnit.expirationDate = l_orderUnitRow.getExpirationDate();
        }

        //�@@�l�i�����@@�@@�@@�F�@@����.�����P��.�l�i����
        l_orderDetailUnit.priceCondType = l_orderUnitRow.getPriceConditionType();

        //�@@���������敪�F�@@����.�����P��.��������
        l_orderDetailUnit.orderCondType = l_orderUnitRow.getOrderConditionType();

        //�@@�������ʁ@@�@@�@@�F�@@����.�����P��.��������
        l_orderDetailUnit.orderQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getQuantity());

        //�@@�����P���敪�F�@@����.�����P��.isMarketOrder()�̖߂�l��true�̏ꍇ�A"���s"���Z�b�g�B
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@false�̏ꍇ��"�w�l"���Z�b�g�B
        if (l_orderUnit.isMarketOrder())
        {
            l_orderDetailUnit.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            l_orderDetailUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

            //�@@�����P���@@�@@�@@�F�@@�����P���敪��"�w�l"�̏ꍇ�A����.�����P��.�w�l���Z�b�g�B
            l_orderDetailUnit.limitPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        }

        //��肪���Ă���ꍇ�i����.�����P��.isUnExecuted() == false�j�A�ȉ��l���Z�b�g�B
        if (!l_orderUnit.isUnexecuted())
        {
            //�@@��萔�ʁF�@@����.�����P��.��萔��
            l_orderDetailUnit.execQuantity =
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedQuantity());

            //���v�����z
            BigDecimal l_bdExecutedAmount = new BigDecimal("" + l_orderUnit.getExecutedAmount());
            //��萔��
            BigDecimal l_bdExecutedQuantity = new BigDecimal("" + l_orderUnit.getExecutedQuantity());
            //���P���F�@@����.�����P��.���v�����z�^����.�����P��.��萔�ʁi�~�����͎l�̌ܓ��j
            BigDecimal l_dbExecPrice =
                l_bdExecutedAmount.divide(l_bdExecutedQuantity, 0, BigDecimal.ROUND_HALF_UP);

            l_orderDetailUnit.execPrice =
                WEB3StringTypeUtility.formatNumber(l_dbExecPrice.doubleValue());
        }

        //�@@������ԋ敪�F�@@get������ԋ敪()�̖߂�l
        l_orderDetailUnit.orderState = l_strOrderState;

        //�@@����ԋ敪�F�@@get����ԋ敪()�̖߂�l
        l_orderDetailUnit.execType = l_strExecType;

        //�@@��������敪�F�@@����.�����P��.���������E����敪
        l_orderDetailUnit.changeCancelDiv = l_orderUnitRow.getModifyCancelType();

        //�@@�������ԁ@@�@@�@@�F�@@����.�����P��.�󒍓���
        l_orderDetailUnit.orderDate = l_orderUnitRow.getReceivedDateTime();

        //�@@�������@@�@@�@@�@@�F�@@����.�����P��.������
        l_orderDetailUnit.orderBizDate =
            WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        //�@@��n���@@�@@�@@�@@�F�@@����.�����P��.��n��
        l_orderDetailUnit.deliveryDate = l_orderUnitRow.getDeliveryDate();

        log.exiting(STR_METHOD_NAME);
        return l_orderDetailUnit;
    }

    /**
     * (get��藚��)<BR>
     * �w�肳�ꂽ�����P�ʂɕR�Â����̗�����<BR>
     * �����o���ʒm�L���[�e�[�u�����擾����B<BR>
     * �i�iPTS�j�o�����́A�iPTS�j�o���������R�[�������j<BR>
     * <BR>
     * <BR>
     * �P�j�@@�����o���ʒm�L���[�e�[�u������ꗗ���擾����B<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�����o���ʒm�L���[�e�[�u��.���ʃR�[�h������.�����P��.���ʃR�[�h<BR>
     * <BR>
     * �@@�@@[�\�[�g��]<BR>
     * �@@�@@�����o���ʒm�L���[�e�[�u��.�쐬���t�̏���<BR>
     * <BR>
     * <BR>
     * �@@�@@�������Ɉ�v����f�[�^���擾�ł��Ȃ��ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * <BR>
     * �Q�j�@@�����o���ʒm�L���[����擾�����ꗗ�̗v�f�����A�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�Ǘ��ҁE�����iPTS�j��藚���C���X�^���X�𐶐�����B<BR>
     * �@@�Q�|�Q�j�@@�Ǘ��ҁE�����iPTS�j��藚���̃v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�E����\�t���O�F�@@false <BR>
     * �@@�@@�E�������F�@@�����o���ʒm�L���[.������<BR>
     * �@@�@@�E��芔���F�@@�����o���ʒm�L���[.��芔��<BR>
     * �@@�@@�E���P���F�@@�����o���ʒm�L���[.���P��<BR>
     * �@@�@@�E���E������敪�F�@@�����o���ʒm�L���[.�o���ʒm�敪<BR>
     * �@@�@@�E�X�V�҃R�[�h�F�@@�����o���ʒm�L���[.�X�V�҃R�[�h<BR>
     * �@@�@@�E�����敪�F�@@�����o���ʒm�L���[.�����敪<BR>
     * <BR>
     * <BR>
     * �R�j�@@�Ǘ��ҁE�����iPTS�j��藚���I�u�W�F�N�g�̔z���ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return WEB3AdminEquityPTSExecHistory[]
     * @@throws WEB3BaseException
     */
    public static WEB3AdminEquityPTSExecHistory[] getExecHistory(
        EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderUnit(String, Branch)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�����o���ʒm�L���[�e�[�u������ꗗ���擾����B
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" order_request_number = ? ");

        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        Object[] l_objWheres = {l_orderUnitRow.getOrderRequestNumber()};

        List l_lisRecords = new ArrayList();
        String l_strSortCondition = "created_timestamp asc";
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisRecords = l_processor.doFindAllQuery(
                HostEquityOrderExecNotifyRow.TYPE,
                l_sbWhere.toString(),
                l_strSortCondition,
                null,
                l_objWheres);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�������Ɉ�v����f�[�^���擾�ł��Ȃ��ꍇ�Anull��ԋp����B
        if (l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3AdminEquityPTSExecHistory[] l_equityPTSExecHistorys =
            new WEB3AdminEquityPTSExecHistory[l_lisRecords.size()];

        //�����o���ʒm�L���[����擾�����ꗗ�̗v�f�����A�ȉ��̏������J��Ԃ��B
        for (int i = 0; i < l_lisRecords.size(); i++)
        {
            HostEquityOrderExecNotifyRow l_execNotifyRow =
                (HostEquityOrderExecNotifyRow)l_lisRecords.get(i);

            //�Ǘ��ҁE�����iPTS�j��藚���C���X�^���X�𐶐�����B
            WEB3AdminEquityPTSExecHistory l_equityPTSExecHistory =
                new WEB3AdminEquityPTSExecHistory();

            //�Ǘ��ҁE�����iPTS�j��藚���̃v���p�e�B�ɃZ�b�g����B
            //�E����\�t���O�F�@@false
            l_equityPTSExecHistory.cancelFlag = false;

            //�E�������F�@@�����o���ʒm�L���[.������
            l_equityPTSExecHistory.executionTimeStamp = l_execNotifyRow.getExecTimestamp();

            //�E��芔���F�@@�����o���ʒm�L���[.��芔��
            l_equityPTSExecHistory.execQuantity =
                WEB3StringTypeUtility.formatNumber(l_execNotifyRow.getExecQuantity());

            //�E���P���F�@@�����o���ʒm�L���[.���P��
            l_equityPTSExecHistory.execPrice =
                WEB3StringTypeUtility.formatNumber(l_execNotifyRow.getExecPrice());

            //�E���E������敪�F�@@�����o���ʒm�L���[.�o���ʒm�敪
            l_equityPTSExecHistory.inputExecCancelExecDiv = l_execNotifyRow.getDealedType();

            //�E�X�V�҃R�[�h�F�@@�����o���ʒm�L���[.�X�V�҃R�[�h
            l_equityPTSExecHistory.updaterCode = l_execNotifyRow.getLastUpdater();

            //�E�����敪�F�@@�����o���ʒm�L���[.�����敪
            l_equityPTSExecHistory.inputExecCancelExecProcDiv = l_execNotifyRow.getStatus();

            l_equityPTSExecHistorys[i] = l_equityPTSExecHistory;
        }

        //�Ǘ��ҁE�����iPTS�j��藚���I�u�W�F�N�g�̔z���ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_equityPTSExecHistorys;
    }

    /**
     * (validate�o�����͏o������\���ԑ�)<BR>
     * �iPTS�j�o�����́A�iPTS�j�o������̏������\�Ȏ��ԑт��`�F�b�N����<BR>
     * <BR>
     * �P�jPTS�s�ꂪ�s��J�ǎ��ԑ�<BR>
     * �@@�@@�iPTS������ԊǗ�.is�s��J�ǎ��ԑ�()==true�j�̏ꍇ�A<BR>
     * �@@�@@�@@�����\(����I��)�Ƃ���B<BR>
     * <BR>
     * <BR>
     * �Q�jPTS�s�ꂪ�ǁiPTS������ԊǗ�.is�s��J�ǎ��ԑ�()==false�j�̏ꍇ<BR>
     * <BR>
     * �Q�|�P�j(PTS)�o���I���e�[�u������������B<BR>
     * <BR>
     * �@@�@@�@@�m���������n<BR>
     * �@@�@@�@@�،���ЃR�[�h������J�����_�R���e�L�X�g(*1)�̓����v���p�e�B<BR>
     * �@@�@@�@@�s��R�[�h�� ����J�����_�R���e�L�X�g(*1)�̓����v���p�e�B<BR>
     * <BR>
     * <BR>
     * �Q�|�Q�j�o���I�����R�[�h�����݂��Ȃ��@@���@@<BR>
     * �@@�@@�@@�@@���ݓ��t�@@>�@@�Ɩ����t�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�����\(����I��)�Ƃ���B<BR>
     * <BR>
     * �Q�|�R�j�@@�Q�|�Q�j�̏����ɊY�����Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�u�����s�̎��ԑсv�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:BUSINESS_ERROR_03015<BR>
     * <BR>
     * <BR>
     *  (*1)����J�����_�R���e�L�X�g�̎擾<BR>
     * ThreadLocalSystemAttributesRegistry���A����J�����_�R���e�L�X�g���擾����B<BR>
     * �ݒ�L�[�F PTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@throws WEB3BaseException
     */
    public static void validateInputCancelExecEnableTimeZone() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInputCancelExecEnableTimeZone()";
        log.entering(STR_METHOD_NAME);

        //PTS�s�ꂪ�ǁiPTS������ԊǗ�.is�s��J�ǎ��ԑ�()==false�j�̏ꍇ
        if (!WEB3EquityPTSTradingTimeManagement.isTradeOpenTimeZone())
        {
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            //�،���ЃR�[�h������J�����_�R���e�L�X�g(*1)�̓����v���p�e�B
            String l_strInstitutionCode = l_clendarContext.getInstitutionCode();
            //�s��R�[�h�� ����J�����_�R���e�L�X�g(*1)�̓����v���p�e�B
            String l_strMarketCode = l_clendarContext.getMarketCode();

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and market_code = ? ");

            Object[] l_objWheres = {l_strInstitutionCode, l_strMarketCode};

            List l_lisPtsOrderexecutionEnds = new ArrayList();
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_lisPtsOrderexecutionEnds = l_processor.doFindAllQuery(
                    PtsOrderexecutionEndRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWheres);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�u�o���I�����R�[�h�����݂��Ȃ��@@�����ݓ��t�@@>�@@�Ɩ����t�v�̏����ɊY�����Ȃ��ꍇ�A
            // �u�����s�̎��ԑсv�̗�O���X���[����B
            Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            if (!(l_lisPtsOrderexecutionEnds.isEmpty()
                && WEB3DateUtility.compareToDay(l_tsSystemTime, l_datBizDate) > 0))
            {
                log.debug("�����s�̎��ԑсB");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03015,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "�����s�̎��ԑсB");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get������ԋ敪)<BR>
     * ����.�����P�ʂ��A������ԋ敪��ԋp����B<BR>
     * <BR>
     * �P�j����.�����P�ʂ̕ێ�����f�[�^�ɂ�蕪�򂵁A<BR>
     * �@@�Ή����钍����ԋ敪��ԋp����B<BR>
     * <BR>
     * �@@�P�|�P�j�蓮�����̔���<BR>
     * �@@�@@�@@�����P��.�����L����� == CLOSED�i�N���[�Y�j ����<BR>
     * �@@�@@�@@�����P��.�����敪 == INVALIDATED_BY_MKT�i�}�[�P�b�g���ہj����<BR>
     * �@@�@@�@@�����P��.�����G���[���R�R�[�h ==<BR>
     * �@@�@@�@@�@@("W001:�����Ǘ��Ҏ蓮������")�̏ꍇ�A<BR>
     * �@@�@@�@@�@@"�蓮����"��ԋp����B<BR>
     * <BR>
     * �@@�P�|�Q�j��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�����f�[�^�A�_�v�^.get������ԋ敪(����.�����P��)���\�b�h��<BR>
     * �@@�@@�@@�R�[�����A�߂�l��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@throws WEB3BaseException
     * @@return String
     */
    public static String getOrderState(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderState(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        //�����P��.�����L����� == CLOSED�i�N���[�Y�j ����
        //�����P��.�����敪 == INVALIDATED_BY_MKT�i�}�[�P�b�g���ہj����
        //�����P��.�����G���[���R�R�[�h ==
        //("W001:�����Ǘ��Ҏ蓮������")�̏ꍇ�A
        if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnitRow.getOrderOpenStatus())
            && OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnitRow.getExpirationStatus())
            && WEB3ErrorReasonCodeDef.EQUITY_ADMIN_MANUAL_EXPIRED.equals(l_orderUnitRow.getErrorReasonCode()))
        {
            //"�蓮����"��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderStatusDef.MANUAL_EXPIRED;
        }

        //�P�|�Q�j��L�ȊO�̏ꍇ�A
        //�����f�[�^�A�_�v�^.get������ԋ敪(����.�����P��)���\�b�h���R�[�����A�߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return WEB3EquityDataAdapter.getOrderState(l_orderUnit);
    }
}
@
