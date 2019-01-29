head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCalcCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͌v�Z�����N���X(WEB3TPCalcCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 nakazato(ACT) �V�K�쐬
                   2006/09/12 ���G�� (���u) ���f��No.018 �ANo.019
                   2006/09/25 �Ԑi�@@ (���u) ���f��No.050
                   2006/09/25 �Ԑi�@@ (���u) ���f��No.068
                   2006/11/13 ����� (���u) ���f��No.074-076
                   2007/01/30 �Ӑ�   (���u) �d�l�ύX �v�Z�����iNo.006 - 007�j
                   2007/03/19 �{�{ �ē� (SCS) ���f��No.099
Revision History : 2007/07/25 �Ј���(���u) ���f��No.133
Revision History : 2007/07/31 �Ј���(���u) ���f��No.143
Revision History : 2007/07/25 �Ј���(���u) ���f��No.170
Revision History : 2007/09/19 �����Q(���u) ���f��No.173
Revision History : 2007/09/28 �g�E�N�|�i���u�j���f��No.189
Revision History : 2007/09/28 ��іQ�i���u�j�����̖��No.003
Revision History : 2007/10/12 �����i���u�j���f��No.194�A���f��No.209
Revision History : 2007/10/16 �И���i���u�j���f��No.204�ANo.210
Revision History : 2007/10/30 ��іQ�i���u�j���ʂc�a���C�A�E�g562
Revision History : 2008/01/22 �И���(���u) ���f��No.237�A���f��No.239�A���f��No.249
Revision History : 2008/02/01 �����Q(���u) ���f��No.254-256�A���f��No.260
Revision History : 2008/04/01 �����Q(���u) ���f��No.271 272
Revision History : 2008/10/20 ������(���u) ���f��No.324
Revision History : 2009/09/10 �И���i���u�j���f��No391
Revesion History : 2010/02/22 ���g (���u) �d�l�ύX���f��No.456
*/
package webbroker3.tradingpower;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeQuoteData;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeQuoteDataSupplierService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AdditionalDepositStopDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3PaymentStopDivDef;
import webbroker3.common.define.WEB3SalesofficeTpcheckDivDef;
import webbroker3.common.define.WEB3SecuredLoanSecAccOpenDivDef;
import webbroker3.common.define.WEB3TheDayTransferDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.FeqLastClosingPriceRow;
import webbroker3.gentrade.data.LastClosingPriceDao;
import webbroker3.gentrade.data.LastClosingPriceRow;
import webbroker3.gentrade.data.ProcessManagementDao;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.data.QuoteClosingPriceDao;
import webbroker3.gentrade.data.QuoteClosingPriceRow;
import webbroker3.gentrade.data.SecurityCashoutRestraintDao;
import webbroker3.gentrade.data.SecurityCashoutRestraintRow;
import webbroker3.gentrade.data.SecurityShortageAccountDao;
import webbroker3.gentrade.data.SecurityShortageAccountRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.define.WEB3TPLegalMarginDepositRateDef;
import webbroker3.tradingpower.define.WEB3TPLegalMinMarginDepositDef;
import webbroker3.tradingpower.define.WEB3TPMarginOpenApplyDateDef;
import webbroker3.tradingpower.define.WEB3TPMutualFundBuyApplyDateDef;
import webbroker3.tradingpower.define.WEB3TPProcessManagementIdDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.define.WEB3TPTradingStopDivDef;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceCallback;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceDBQuoteCallback;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceQuoteCallback;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceStandardCallback;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�]�͌v�Z�����j
 * 
 * �]�͌v�Z�������i�[����f�[�^�Z�b�g�N���X�B
 */
public class WEB3TPCalcCondition
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPCalcCondition.class);

    /**
     * �u���o�\�����`�F�b�N���@@-�M�p�V�K�����v�̃v���t�@@�����X���B
     */
    public final static String MARGINOPEN_ACTPAY_CHECK = "marginopen.actpay.check";

    
    /**
     * �u�Ǐؗ]�̓`�F�b�N���@@-�����������v�̃v���t�@@�����X���B
     */
    public final static String EQUITYBUY_ADDDEPOSIT_CHECK = "equitybuy.adddeposit.check";

    /**
     * �u�Ǐؗ]�̓`�F�b�N���@@-�M�p�V�K�����v�̃v���t�@@�����X���B
     */
    public final static String MARGINOPEN_ADDDEPOSIT_CHECK = "marginopen.adddeposit.check";

    /**
     * �u�Ǐؗ]�̓`�F�b�N���@@-�M�p�������v�̃v���t�@@�����X���B
     */
    public final static String MARGINSWAPLONG_ADDDEPOSIT_CHECK = "marginswaplong.adddeposit.check";

    /**
     * �u�Ǐؗ]�̓`�F�b�N���@@-���̑����i���t���v�̃v���t�@@�����X���B
     */
    public final static String OTHERBUY_ADDDEPOSIT_CHECK = "otherbuy.adddeposit.check";

    
    /**
     * �u�M�p�V�K���\�z�K�p���v�̃v���t�@@�����X���B
     */
    public final static String MARGINOPEN_APPLY_DATE = "marginopen.apply.date";


    /**
     * �u�������ʑ���v��J�n���v�̃v���t�@@�����X���B
     */
    public final static String CONTRACTAMOUNT_APPLY_DATE = "contractamount.apply.date";

    
    /**
     * �u��K���`�F�b�N���@@-�����������v�̃v���t�@@�����X���B
     */
    public final static String EQUITYBUY_DOUBLEPOSITION_CHECK = "equitybuy.doubleposition.check";

    /**
     * �u��K���`�F�b�N���@@-�M�p�V�K�������v�̃v���t�@@�����X���B
     */
    public final static String MARGINOPENLONG_DOUBLEPOSITION_CHECK = "marginopenlong.doubleposition.check";

    /**
     * �u��K���`�F�b�N���@@-�M�p�������v�̃v���t�@@�����X���B
     */
    public final static String MARGINSWAPLONG_DOUBLEPOSITION_CHECK = "marginswaplong.doubleposition.check";

    /**
     * �u��K���`�F�b�N���@@-���̑����i���t���v�̃v���t�@@�����X���B
     */
    public final static String OTHERBUY_DOUBLEPOSITION_CHECK = "otherbuy.doubleposition.check";

    /**
     * �u��K���`�F�b�N���@@-(�ی�ˑ�p)�،��U�֎��v�̃v���t�@@�����X���B
     */
    public final static String MARGINSUBSECURITY_DOUBLEPOSITION_CHECK = "marginsubsecurity.doubleposition.check";
    
    /**
     * �u����]�̓`�F�b�N���@@-�����������t�����v�̃v���t�@@�����X���B
     */
    public final static String EQUITYBUY_TRADINGPOWER_CHECK = "equitybuy.tradingpower.check";

    /**
     * �u����]�̓`�F�b�N���@@-�M�p�����������v�̃v���t�@@�����X���B
     */
    public final static String MARGINSWAPLONG_TRADINGPOWER_CHECK = "marginswaplong.tradingpower.check";
    
    /**
     * �u�T�[�r�X���p�S���v�̃v���t�@@�����X���B
     */
    public final static String SERVICE_CHARGE_RESTRAINT = "service.charge.restraint";      

    /**
     * �u����蔄�t�����P���ݒ�敪�v�̃v���t�@@�����X���B
     */
    public final static String SELLORDER_PRICE_DIV = "sellorder.price.div";      
    
    /**
     * �u�����M���O��S���v�̃v���t�@@�����X���B
     */
    public final static String MUTUAL_FUND_ADVANCE_RESTRAINT = "mf.advance.restraint";      
    
    /**
     * �u�����M�����t�\�z�K�p���v�̃v���t�@@�����X���B
     */
    public final static String MFBUY_APPLY_DATE = "mfbuy.apply.date";  
    
    /**
     * �u��Е��X�ʗ]�͌v�Z�����v�̃v���t�@@�����X���B
     */
    public final static String ACTUALRECEIPT_MARGINCALLPOWER_CHECK = "actualreceipt.margincallpower.check";
    
    /**
     * �uIPO�w���\���]�̓`�F�b�N�v�̃v���t�@@�����X���B
     */
    public final static String IPO_OFFER_TRADINGPOWER_CHECK = "ipo.offer.tradingpower.check";
    
    /**
     * �u�������ϑ����O���t�����l���̍������σ`�F�b�N�v�̃v���t�@@�����X���B
     */
    public final static String EXCLUDE_EXCEPT_SETTLEMENT_BUY_AMOUNT_CHECK =
        "exclude.except.settlement.buy.amount.check";
    
    /**
     * �u�Ǐ؉������z���ڒǉ��敪�v�̃v���t�@@�����X���B
     */
    public final static String ADDDEPOSIT_DISSOLVE_DIV = "adddeposit.dissolve.div";
    
    /**
     * �u�ۏ؋����A���U�֎��{�敪�v�̃v���t�@@�����X���B
     */ 
    public final static String DEPOSIT_REAL_TRANSFER_ENFORCE_DIV = "deposit.real.transfer.enforce.div";
    
    /**
     * �u���ʏ��o��(�������n)�̖���n���ϑ��v��敪�v�̃v���t�@@�����X���B
     */
    public final static String EQTYPE_SWAP_MARGIN_COST_UNDELIVERED_CONTRACT_LOSS_DIV =
        "eqtype.swap.margin.cost.undelivered.contract.loss.div";
    
    /**
     * �u��ꐅ���Ǐ،o�ߓ����i�ݒ�2�j���@@��v�̃v���t�@@�����X���B
     */
    public final static String FIRST_MARGIN_PASS_DAY2 = "first.margin.pass.day2";
    
    /**
     * �u��ꐅ���Ǐ،o�ߓ����i�ݒ�1�j���Г��K��v�̃v���t�@@�����X���B
     */
    public final static String FIRST_MARGIN_PASS_DAY1 = "first.margin.pass.day1";

    /**
     * �i�V�X�e���^�C�v�X�^���v�j
     */
    private Timestamp systemTimeStamp;

    /**
     * �i�c�Ɠ�[T-2..+6]�j
     */
    private Date[] bizDate;
    
    /**
     * �i�����~�敪�j
     */
    private boolean tradingStop;

    /**
     * �i�M�p�V�K���]�͋敪�j
     */
    private boolean marginOpenPositionStop;

    /**
     * �i�o���]�͋敪�j
     */
    private boolean paymentStop;

    /**
     * �i���̑����i���t�]�͋敪�j
     */
    private boolean otherTradingStop;

    /**
     * (�Ǐؖ������敪)
     */
    private boolean additionalDepositDiv;

    /**
     * �i����������t�J�n�敪<��������>�j<BR>
     * <BR>
     * ����������t���ԑт̏ꍇ�Atrue�B�ȊO�̏ꍇ�Afalse�B<BR>
     * <BR>
     */
    private boolean equityNextDayOrderStartDiv;

    /**
     * �i�o���I���敪<��������>�j
     */
    private boolean equityExecutionDiv;

    /**
     * �i�o���I���敪<�I�v�V����>�j
     */
    private boolean optionExecutionDiv;

    /**
     * �i�a��،��]�����敪�j
     */
    private boolean assetEvalDiv;

    /**
     * �i�a��،��]����<���������]��>�j
     */
    private boolean equityEvalDiv;

    /**
     * �i�a��،��]����<���M�]��>�j
     */
    private boolean fundEvalDiv;

    /**
     * �i�a��،��]����<���]��>�j
     */
    private boolean bondEvalDiv;

    /**
     * �i�a��،��]����<GP�]��>�j
     */
    private boolean gpEvalDiv;

    /**
     * �i�a��،��]�����x�z�j
     */
    private double maxAssessment;

    /**
     * �i���ʗ��֋����сj
     */
    private double specialDebitAmount;

    /**
     * �i�����ʑ������l�j
     * 
     * �l�ڋq�ɑ΂���A��ЁE���X�Ŏ���ł��錚����̑����ʏ���l�B
     */
    private double maxContPrice;

    /**
     * �i�ۏ؋����j
     */
    private int marginDepositRate;

    /**
     * �i�����ۏ؋����j
     */
    private int cashMarginDepositRate;

    /**
     * �i�ۏ؋��ێ����j
     * ���ʂ��ێ�����(�Ǐ؂��������Ȃ�)�ׂɕK�v�ȕۏ؋��̊���
     */
    private int marginMaintenanceRate;

    /**
     * �i�Œ�K�v�ۏ؋��j
     */
    private double minMarginDeposit;

    /**
     * �i�@@��ۏ؋����j
     */
    private int legalMarginDepositRate;


    /**
     * �i�@@��Œ�K�v�ۏ؋��j
     */
    private double legalMinMarginDeposit;

    /**
     * �i�]�͌v�Z��p�|�ځj
     */
    private int substituteRate;

    /**
     * �i�]�͌v�Z���<�������t/�M�p����>�j
     * 
     * �������t�^�M�p�����̗]�͌v�Z���
     */
    private int equityBasePoint;

    /**
     * �i�]�͌v�Z���<�M�p�V�K��>�j
     * 
     * �M�p�V�K���̗]�͌v�Z���
     */
    private int marginBasePoint;

    /**
     * �i�]�͌v�Z���<���M>�j
     * 
     * ���M�̗]�͌v�Z���
     */
    private int fundBasePoint;

    /**
     * �i�]�͌v�Z���<�o��>�j
     * 
     * �o���̗]�͌v�Z���
     */
    private int paymentBasePoint;

    /**
     * �i�]�͌v�Z���<�I�v�V�����V�K��>�j
     * 
     * �I�v�V�����V�K�����̗]�͌v�Z���
     */
    private int optionBasePoint;

    /**
     * �i�]�͌v�Z���<���̑����t>�j
     * 
     * ���̑����i���t�̗]�͌v�Z���
     */
    private int otherBasePoint;

    /**
     * �i�]�͌v�Z���<�~�j��>�j
     * 
     * �~�j���̗]�͌v�Z���
     */
    private int mstkBasePoint;

    /**
     * �i�]�͌v�Z���<�ݓ�>�j
     * 
     * �ݓ��̗]�͌v�Z���
     */
    private int ruitoBasePoint;
    
    /**
     * �i�]�͌v�Z���<IPO>�j
     * 
     * IPO�̗]�͌v�Z���
     */
    private int ipoBasePoint;
    
    /**
     * �i�]�͌v�Z���<��������>�j
     * 
     * ���������̗]�͌v�Z���
     */
    private int feqBasePoint;

    /**
     * �i������<�����^�M�p>�j
     * 
     * �����^�M�p�̔�����
     */
    private int equityBizDate;

    /**
     * (����������)
     * 
     * �]�͌v�Z���ێ����ׂ�����������Map
     */
    private Map rightsOffDates;

    /**
     * (�����I�l)
     * 
     * �]�͌v�Z���ێ����ׂ������I�lMap
     */
    private Map closingPrices;

    /**
     * (����<����>)
     * 
     * �]�͌v�Z���ێ����ׂ�����<����>Map
     */
    private Map eqtypeQuotes;

    /**
     * (�]���P��Callback)
     * 
     * �]���P��Callback(�W���Ǝ����ŃI�u�W�F�N�g���قȂ�)
     */
    private WEB3TPUnitPriceCallback unitPriceCallback;

    /**
     * (��Е��X�ʗ]�͌v�Z����)
     * 
     * ��Е��X���̗]�͌v�Z�������i�[����Map
     */
    private Map instBranCalcCondition;
    
    /**
     * (���X�^�C�v)
     */
    private BranchTypeEnum branchType;
    
    /**
     * (�،��S�ۃ��[���敪) <BR>
     * <BR>
     * �،��S�ۃ��[�����{�ڋq�̏ꍇ�Atrue���Z�b�g�B�ȊO�̏ꍇ�Afalse���Z�b�g<BR>
     */
    private boolean securedLoanSecAccOpenDiv;
    
    /**
     * (�a����S�ۏo����~�敪)<BR> 
     *<BR>
     * �،��S�ۃ��[�����{�ڋq���a����S�ۑS�z�o����~�̏ꍇ�A<BR>
     * true���Z�b�g�B�ȊO�̏ꍇ�Afalse���Z�b�g<BR> 
     */
    private boolean cashDepositStopDiv;
    
    /**
     * (�I���b�N�X_�S�ۃ��[�������J�݋敪)<BR>
     * <BR>
     * true�F �S�ۃ��[�������J�ݍόڋq���S�ۃ��[���o���S�����e�[�u���Ƀ��R�[�h�����݂���B<BR>
     * false�F �S�ۃ��[���������J�݌ڋq�܂��͒S�ۃ��[���o���S�����e�[�u���Ƀ��R�[�h�����݂��Ȃ��B<BR>
     */
    private boolean orixSecuredLoanAccOpenDiv;

    /**
     * (�I���b�N�X_�S�ۃ��[���o���\�z)<BR>
     * <BR>
     */
    private String orixSecuredLoanPaymentTradingPower;

    /**
     * �I���b�N�X_�S�ۃ��[�����z���b�N�t���O<BR>
     * <BR>
     */
    private String orixSecuredLoanLockFlag;

    /**
     * (�O���I�l)<BR>
     * �O���I�l<BR>
     */
    private Map feqLastClosingPrice;

    /**
     * (PTS�o���I���敪)<BR>
     * <BR>
     * PTS�o���I���̏ꍇ�Atrue�B�ȊO�̏ꍇ�Afalse�B<BR>
     */
    private boolean ptsOrderExecutionEndType;

    /**
     * (�O�����v�����̗p)<BR>
     * �O�����v�����̗p<BR>
     */
    private boolean feqDayTradeAdoption;

    /**
     * �i�]�͌v�Z�����j
     * 
     * �R���X�g���N�^
     * @@roseuid 40BA93580399
     */
    public WEB3TPCalcCondition()
    {
        //�t�B�[���h������������B
        this.systemTimeStamp = null;
        this.bizDate = new Date[9];
        this.tradingStop = false;
        this.marginOpenPositionStop = false;
        this.paymentStop = false;
        this.otherTradingStop = false;
        this.equityExecutionDiv = false;
        this.optionExecutionDiv = false;
        this.assetEvalDiv = false;
        this.equityEvalDiv = false;
        this.fundEvalDiv = false;
        this.bondEvalDiv = false;
        this.gpEvalDiv = false;
        this.maxAssessment = 0.0;
        this.specialDebitAmount = 0.0;
        this.maxContPrice = 0.0;
        this.marginDepositRate = 0;
        this.cashMarginDepositRate = 0;
        this.marginMaintenanceRate = 0;
        this.minMarginDeposit = 0.0;
        this.legalMinMarginDeposit = 0.0;
        this.substituteRate = 0;
        this.equityBasePoint = 0;
        this.marginBasePoint = 0;
        this.fundBasePoint = 0;
        this.paymentBasePoint = 0;
        this.optionBasePoint = 0;
        this.otherBasePoint = 0;
        this.rightsOffDates = new HashMap();
        this.closingPrices = new HashMap();
        this.eqtypeQuotes = new HashMap();
        this.instBranCalcCondition = new HashMap();
        this.branchType = null;
        this.feqLastClosingPrice = new HashMap();
    }

    /**
     * �icreate�]�͌v�Z����<�W��>�j<BR>
     * (static���\�b�h)
     * 
     * �]�͌v�Z�����I�u�W�F�N�g�𐶐����A�]���P��<�W��>Callback���Z�b�g����B
     * �V�[�P���X�}�u(�]�͌v�Z����)create�]�͌v�Z����<�W��>�v�Q��     
     * @@param l_subAccount - (�⏕����)
     * @@return webbroker3.tradingpower.WEB3TPCalcCondition
     */
    public static WEB3TPCalcCondition createCalcConditionStandard(WEB3GentradeSubAccount l_subAccount)
    {
        WEB3TPCalcCondition l_calcCondition = createCalcCondition(l_subAccount);
        WEB3TPUnitPriceCallback l_unitPriceCallback = new WEB3TPUnitPriceStandardCallback(l_calcCondition);
        l_calcCondition.setUnitPriceCallback(l_unitPriceCallback);
        return l_calcCondition;
    }

    /**
     * �icreate�]�͌v�Z����<����>�j<BR>
     * (static���\�b�h)
     * 
     * �]�͌v�Z�����I�u�W�F�N�g�𐶐����A�]���P��<����>Callback���Z�b�g����B
     * �V�[�P���X�}�u(�]�͌v�Z����)create�]�͌v�Z����<����>�v�Q��     
     * @@param l_subAccount - (�⏕����)
     * @@return webbroker3.tradingpower.WEB3TPCalcCondition
     */
    public static WEB3TPCalcCondition createCalcConditionQuote(WEB3GentradeSubAccount l_subAccount)
    {
        WEB3TPCalcCondition l_calcCondition = createCalcCondition(l_subAccount);
        WEB3TPUnitPriceCallback l_unitPriceCallback = new WEB3TPUnitPriceQuoteCallback(l_calcCondition);
        l_calcCondition.setUnitPriceCallback(l_unitPriceCallback);
        return l_calcCondition;
    }

    /**
     * �icreate�]�͌v�Z�����j<BR>
     * (static���\�b�h)
     * 
     * �]�͌v�Z�����I�u�W�F�N�g�𐶐����A�v���p�e�B���Z�b�g����B
     * �V�[�P���X�}�u(�]�͌v�Z����)create�]�͌v�Z�����v�Q��     
     * @@param l_subAccount - (�⏕����)
     * @@return webbroker3.tradingpower.WEB3TPCalcCondition
     */
    private static WEB3TPCalcCondition createCalcCondition(WEB3GentradeSubAccount l_subAccount)
    {
        final String STR_METHOD_NAME = "WEB3TPCalcCondition.createCalcCondition()";

        //1.1.�]�͌v�Z�����̃C���X�^���X�𐶐�����
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();

        //1.2.�ڋq���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //1.3.�،���Ђ��擾
        WEB3GentradeInstitution l_institution =
            (WEB3GentradeInstitution)l_subAccount.getInstitution();
        //1.4.���X���擾
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();

        /*
         * ��Е��X�ʗ]�͌v�Z�������Z�b�g
         */
        try
        {
            //1.5.���XID
            long l_lngBranchId = l_branch.getBranchId();

            //1.6.���X�p�v���t�@@�����X�e�[�u��������
            List l_rowList = BranchPreferencesDao.findRowsByBranchId(l_lngBranchId);

            if(l_rowList != null)
            {
                for(Iterator iter = l_rowList.iterator(); iter.hasNext();)
                {
                    BranchPreferencesRow l_row = (BranchPreferencesRow) iter.next();

                    //1.6.1.�������ʍs��]�͌v�Z����Map�ɒǉ�
                    l_calcCondition.addInstBranCalcCondition(l_row.getName(), l_row.getValue());
                }
            }
        }
        catch(DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //1.7.�V�X�e���X�^���v�A�c�Ɠ����Z�b�g
        l_calcCondition.calcBizDate();
        
        //1.8.�]�͌v�Z������Z�b�g
        l_calcCondition.calcBasePoint(l_subAccount);

        //1.9.���X�^�C�v���Z�b�g
        //1.10.
        l_calcCondition.setBranchType(l_branch.getBranchType());
        

        /*
         * ����������t�J�n�敪<��������>���Z�b�g����
         */
        try
        {
            //�v���Z�X�Ǘ��e�[�u��������
            ProcessManagementRow l_processRow = ProcessManagementDao.findRowByProcessIdInstitutionCodeBranchCode(
                    WEB3TPProcessManagementIdDef.EQUITY_NEXTDAYORDER_STARTTIME,
                    l_institution.getInstitutionCode(),
                    l_branch.getBranchCode());

            if(l_processRow != null)
            {
                l_calcCondition.setEquityNextDayOrderStartDiv(true);
            }
            else
            {
                l_calcCondition.setEquityNextDayOrderStartDiv(false);
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        /*
         * �o���I���敪���Z�b�g
         */
        try
        {
            //1.11.�o���I���敪<��������>���Z�b�g       
            l_calcCondition.setEquityExecutionDiv(
                l_institution.isOrderExecEnd(ProductTypeEnum.EQUITY, WEB3FuturesOptionDivDef.DEFAULT));
            //1.12.�o���I���敪<�I�v�V����>���Z�b�g       
            l_calcCondition.setOptionExecutionDiv(
                l_institution.isOrderExecEnd(ProductTypeEnum.IFO, WEB3FuturesOptionDivDef.OPTION));
        }
        catch (WEB3SystemLayerException e)
        {
            throw new WEB3BaseRuntimeException(
                e.getErrorInfo(),
                e.getErrorMethod(),
                e.getErrorMessage(),
                e.getException());
        }

        /*
         * PTS�o���I���敪���Z�b�g����
         */
        try
        {
            //�v���Z�X�Ǘ��e�[�u��������
            ProcessManagementRow l_processRow = ProcessManagementDao.findRowByProcessIdInstitutionCodeBranchCode(
                    WEB3TPProcessManagementIdDef.PTS_ORDER_EXECUTION_END_TYPE,
                    l_institution.getInstitutionCode(),
                    l_branch.getBranchCode());

            if(l_processRow != null)
            {
                l_calcCondition.setPtsOrderExecutionEndType(true);
            }
            else
            {
                l_calcCondition.setPtsOrderExecutionEndType(false);
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        /*
         * 1.15.�]�͌v�Z����Row���擾����
         */
        TradingpowerCalcConditionRow l_calcConditionRow = null;
        try
        {
            l_calcConditionRow =
                (TradingpowerCalcConditionRow)TradingpowerCalcConditionDao.findRowByAccountId(
                    l_subAccount.getAccountId());
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        //1.16.���ʗ��֋����т��Z�b�g
        l_calcCondition.setSpecialDebitAmount(l_calcConditionRow.getSpecialDebitAmount());
        //1.17.�����~�敪���Z�b�g
        if (l_calcConditionRow.getTradingStopIsSet() == true
            && l_calcConditionRow.getTradingStop().equals(WEB3TPTradingStopDivDef.TRADING_STOP) == true
            || WEB3AdditionalDepositStopDef.ADDITIONAL_DEPOSIT_STOP.equals(
                l_calcConditionRow.getAdditionalDepositStop()))
        {
            l_calcCondition.setTradingStop(true);
        }
        else
        {
            l_calcCondition.setTradingStop(false);
        }

        //1.18.�M�p�V�K���]�͋敪���Z�b�g
        if (l_calcConditionRow.getMarginOpenPositionStopIsSet() == true
            && l_calcConditionRow.getMarginOpenPositionStop().equals(WEB3TPTradingStopDivDef.TRADING_OK) == true)
        {
            l_calcCondition.setMarginOpenPositionStop(false);
        }
        else
        {
            l_calcCondition.setMarginOpenPositionStop(true);
        }

        //1.19.�o���]�͋敪���Z�b�g
        if (l_calcConditionRow.getPaymentStopIsSet() == true
            && l_calcConditionRow.getPaymentStop().equals(WEB3TPTradingStopDivDef.TRADING_OK) == true)
        {
            l_calcCondition.setPaymentStop(false);
        }
        else
        {
            l_calcCondition.setPaymentStop(true);
        }

        //1.20.���̑����i���t�]�͋敪���Z�b�g
        if (l_calcConditionRow.getOtherTradingStopIsSet() == true
            && l_calcConditionRow.getOtherTradingStop().equals(WEB3TPTradingStopDivDef.TRADING_OK) == true)
        {
            l_calcCondition.setOtherTradingStop(false);
        }
        else
        {
            l_calcCondition.setOtherTradingStop(true);
        }

        //set�Ǐؖ������敪
        if (l_calcConditionRow.getAdditionalDepositStop() != null
            && WEB3AdditionalDepositStopDef.ADDITIONAL_DEPOSIT_STOP.equals(
            l_calcConditionRow.getAdditionalDepositStop()))
        {
            l_calcCondition.setAdditionalDepositDiv(true);
        }
        else
        {
            l_calcCondition.setAdditionalDepositDiv(false);
        }

        //1.21.�M�p�����敪���擾����B�ٍϋ敪�F�w��Ȃ�
        boolean l_accoutFlg = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //1.22.
        //�����ڋq�̎�
        if (l_accoutFlg == false)
        {
            //1.23.�a��،��]�����敪���擾
            boolean l_blnAssetEvaluation;
            try
            {
                l_blnAssetEvaluation = l_mainAccount.isAssetEvaluation();
            }
            catch (WEB3SystemLayerException e)
            {
                throw new WEB3BaseRuntimeException(
                    e.getErrorInfo(),
                    e.getErrorMethod(),
                    e.getErrorMessage(),
                    e.getException());
            }
            
            //�a��،��]�����敪���Z�b�g
            l_calcCondition.setAssetEvalDiv(l_blnAssetEvaluation);

            //1.22.4.�a��،��]�����敪== true�̏ꍇ
            if (l_calcCondition.isAssetEvalDiv() == true)
            {
                //1.22.4.1.�a��،��]����<���������]��>���Z�b�g
                l_calcCondition.setEquityEvalDiv(l_institution.isInstitutionStockEvaluation());
                //1.22.4.3.�a��،��]����<���M�]��>���Z�b�g
                l_calcCondition.setFundEvalDiv(l_institution.isInstitutionFundEvaluation());
                //1.22.4.5.�a��،��]����<���]��>���Z�b�g
                l_calcCondition.setBondEvalDiv(l_institution.isInstitutionBondEvaluation());
                //1.22.4.7.�a��،��]����<GP�]��>���Z�b�g
                l_calcCondition.setGpEvalDiv(l_institution.isInstitutionGpEvaluation());
            }
            //�a��،��]�����敪== false�̏ꍇ
            else
            {
                //�a��،��]����<���������]��>���Z�b�g
                l_calcCondition.setEquityEvalDiv(false);
                //�a��،��]����<���M�]��>���Z�b�g
                l_calcCondition.setFundEvalDiv(false);
                //�a��،��]����<���]��>���Z�b�g
                l_calcCondition.setBondEvalDiv(false);
                //�a��،��]����<GP�]��>���Z�b�g
                l_calcCondition.setGpEvalDiv(false);
            }

            //�،���Ђ̃f�[�^�\�[�X���擾����B
            InstitutionRow l_institutionRow = (InstitutionRow)l_institution.getDataSourceObject();
            //1.22.4.�a��،��]�����x�z���Z�b�g
            l_calcCondition.setMaxAssessment(l_institutionRow.getMaximumAssessment());
            
            //1.22.5.�ڋq�I�u�W�F�N�g���A�ڋq�}�X�^�̍s�I�u�W�F�N�g���擾����B
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            
            //1.22.6.set�،��S�ۃ��[���敪(boolean)
            //�،��S�ۃ��[���敪���Z�b�g����B 
            //[����] 
            //boolean = ".�،��S�ۃ��[���敪"(*) 
            //(*) 
            //�@@[a.�ڋqRow.�،��S�ۃ��[���敪 == 1�F�J��] 
            //�@@�@@".�،��S�ۃ��[���敪" = true�@@ 
            //�@@[a.�ȊO�̏ꍇ] 
            //�@@�@@".�،��S�ۃ��[���敪" = false�@@ 
            //�@@���ڋqRow = �ڋq.getDataSourceObject( )�̖߂�l 
            boolean l_blnSecuredLoanSecAccOpen = false;
            if (WEB3SecuredLoanSecAccOpenDivDef.OPEN.equals(
                l_mainAccountRow.getSecuredLoanSecAccOpenDiv()))
            {
                l_blnSecuredLoanSecAccOpen = true;
            }

            l_calcCondition.setSecuredLoanSecAccOpenDiv(l_blnSecuredLoanSecAccOpen);

            //1.22.7.is�،��S�ۃ��[���敪()
            boolean l_blnSecuredLoanSecAccOpenDiv = l_calcCondition.isSecuredLoanSecAccOpenDiv();
            
            //1.22.8.(*)����t���[
            //�،��S�ۃ��[�����{�ڋq�̏ꍇ
            //(is�،��S�ۃ��[���敪 == true)
            if (l_blnSecuredLoanSecAccOpenDiv)
            {
                //1.22.8.1.�S�ەs���ڋq�f�[�^�e�[�u�����A���Y�ڋq�̃��R�[�h���擾����B
                //[�Ώۃe�[�u��]
                // �S�ەs���ڋq�f�[�^�e�[�u��
                //[��������]
                // ����ID = ����.�⏕����.getAccountId()
                SecurityShortageAccountRow l_securityShortageAccountRow = null;
                try
                {
                    l_securityShortageAccountRow = 
                        (SecurityShortageAccountRow)SecurityShortageAccountDao.findRowByAccountId(
                            l_mainAccount.getAccountId());
                    
                }
                catch (DataException de)
                {
                    log.error(de.getMessage(), de);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        STR_METHOD_NAME,
                        de.getMessage(),
                        de);
                }
                
                //1.22.8.2.(*)����t���[
                //�S�ەs���ڋq�f�[�^�e�[�u���̍s�I�u�W�F�N�g���擾�ł����ꍇ
                if (l_securityShortageAccountRow != null)
                {
                    //1.22.8.2.1.set�a����S�ۏo����~�敪(boolean)
                    //�a����S�ۏo����~�敪���Z�b�g����B
                    //[����]
                    //boolean = "�o����~�敪"(*)
                    //(*)
                    // [a.�S�ەs���ڋqRow.�o����~�敪=1�F�S�z �̏ꍇ]
                    //�@@�@@"�o����~�敪" = true
                    // [a.�ȊO�̏ꍇ]
                    //�@@�@@"�o����~�敪" = false
                    boolean l_blnPaymentStop = false;
                    if (WEB3PaymentStopDivDef.FULL.equals(l_securityShortageAccountRow.getPaymentStopDiv()))
                    {
                        l_blnPaymentStop = true;
                    }
                    
                    l_calcCondition.setCashDepositStopDiv(l_blnPaymentStop);
                }
            }

            try
            {
                //is�،��S�ۃ��[�������J��
                boolean l_blnIsSecuredLoanAccountOpen = l_mainAccount.isSecuredLoanAccountOpen();

                if (l_blnIsSecuredLoanAccountOpen)
                {
                    //�I���b�N�X�،��S�ۃ��[�������J�ݍόڋq�̏ꍇ
                    //�S�ۃ��[���o���S�����e�[�u�����A���Y�ڋq�̃��R�[�h���擾����
                    SecurityCashoutRestraintRow l_securityCashoutRestraintRow = null;
                    try
                    {
                        l_securityCashoutRestraintRow =
                            (SecurityCashoutRestraintRow)SecurityCashoutRestraintDao.findRowByAccountId(
                                l_subAccount.getAccountId());
                    }
                    catch (DataException de)
                    {
                        log.error(de.getMessage(), de);
                        throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            STR_METHOD_NAME,
                            de.getMessage(),
                            de);
                    }

                    if(l_securityCashoutRestraintRow != null)
                    {
                        //set�I���b�N�X_�S�ۃ��[�������J�݋敪
                        l_calcCondition.setOrixSecuredLoanAccOpenDiv(true);

                        //set�I���b�N�X_�S�ۃ��[���o���\�z
                        l_calcCondition.setOrixSecuredLoanPaymentTradingPower(
                            l_securityCashoutRestraintRow.getCashoutEnablieAmount() + "");

                        //set�I���b�N�X_�S�ۃ��[�����z���b�N�t���O
                        l_calcCondition.setOrixSecuredLoanLockFlag(
                            l_securityCashoutRestraintRow.getAmountLockFlg());
                    }
                    else
                    {
                        //set�I���b�N�X_�S�ۃ��[�������J�݋敪
                        l_calcCondition.setOrixSecuredLoanAccOpenDiv(false);

                        //set�I���b�N�X_�S�ۃ��[���o���\�z
                        l_calcCondition.setOrixSecuredLoanPaymentTradingPower(null);

                        //set�I���b�N�X_�S�ۃ��[�����z���b�N�t���O
                        l_calcCondition.setOrixSecuredLoanLockFlag(null);
                    }
                }
                else
                {
                    //�I���b�N�X�،��S�ۃ��[���������J�݌ڋq�̏ꍇ
                    //set�I���b�N�X_�S�ۃ��[�������J�݋敪
                    l_calcCondition.setOrixSecuredLoanAccOpenDiv(false);

                    //set�I���b�N�X_�S�ۃ��[���o���\�z
                    l_calcCondition.setOrixSecuredLoanPaymentTradingPower(null);

                    //set�I���b�N�X_�S�ۃ��[�����z���b�N�t���O
                    l_calcCondition.setOrixSecuredLoanLockFlag(null);
                }
            }
            catch (WEB3SystemLayerException e)
            {
                throw new WEB3BaseRuntimeException(
                    e.getErrorInfo(),
                    e.getErrorMethod(),
                    e.getErrorMessage(),
                    e.getException());
            }
        }
        //�M�p�ڋq�̎�
        else
        {
            //�ڋq�̃f�[�^�\�[�X���擾����B
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            //���X�̃f�[�^�\�[�X���擾����B
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();

            //�����^�C�v���擾
            MainAccountTypeEnum l_accountType = l_mainAccountRow.getAccountType();

            //�@@�l�A�J�E���g�̎�
            if (l_accountType == MainAccountTypeEnum.CORPORATE_ACCOUNT)
            {
                //�����������l���Z�b�g
                l_calcCondition.setMaxContPrice(l_branchRow.getMaxContPriceAllCorp());
            }
            //�l�^���p�A�J�E���g�̎�
            else if (
                l_accountType == MainAccountTypeEnum.INDIVIDUAL_ACCOUNT
                    || l_accountType == MainAccountTypeEnum.JOINT_OWNERSHIP)
            {
                //�����������l���Z�b�g
                l_calcCondition.setMaxContPrice(l_branchRow.getMaxContPriceAllInd());
            }

            //�ۏ؋������Z�b�g
            l_calcCondition.setMarginDepositRate((int)l_branchRow.getMarginDepositRate());
            //�����ۏ؋������Z�b�g
            l_calcCondition.setCashMarginDepositRate((int)l_branchRow.getCashMarginDepositRate());
            //�ۏ؋��ێ��������Z�b�g
            l_calcCondition.setMarginMentenanceRate((int)l_branchRow.getMarginMaintenanceRate());
            //�Œ�K�v�ۏ؋����Z�b�g
            l_calcCondition.setMinMarginDeposit(l_branchRow.getMinMarginDeposit());
            //�@@��ۏ؋������Z�b�g
            l_calcCondition.setLegalMarginDepositRate(WEB3TPLegalMarginDepositRateDef.LEGAL_MARGIN_DEPOSIT_RATE);
            //�@@��Œ�K�v�ۏ؋����Z�b�g
            l_calcCondition.setLegalMinMarginDeposit(WEB3TPLegalMinMarginDepositDef.MIN_MARGIN_DEPOSIT);
            //�]�͌v�Z��p�|�ڂ��Z�b�g
            l_calcCondition.setSubstituteRate((int)l_branchRow.getCalcSubstituteRate());
        }

        //set�O�����v�����̗p
        boolean l_blnIsDayTradeAdoption = false;
        try
        {
            l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                WEB3TPCalcCondition.class.getName() + STR_METHOD_NAME, 
                l_ex.getErrorMessage(),
                l_ex);
        }
        l_calcCondition.setFeqDayTradeAdoption(l_blnIsDayTradeAdoption);
        //�C���X�^���X��ԋp����
        return l_calcCondition;
    }

    /**
     * �iget�V�X�e���^�C���X�^���v�j<BR>
     * 
     * this.�V�X�e���^�C���X�^���v��ԋp����B<BR>
     * @@return TimeStamp
     */
    public Timestamp getSystemTimeStamp()
    {
        return this.systemTimeStamp;
    }

    /**
     * �iset�V�X�e���^�C�v�X�^���v�j<BR>
     * 
     * �p�����[�^.�V�X�e���^�C���X�^���v��this.�V�X�e���^�C���X�^���v�ɃZ�b�g����B<BR>
     * @@param l_dblMaxContPrice
     */
    public void setSystemTimeStamp(Timestamp l_systemTimeStamp)
    {
        this.systemTimeStamp = l_systemTimeStamp;
    }

    /**
     * (get�c�Ɠ�)<BR> 
     * 
     * this.�c�Ɠ�[T-2..+6]��ԋp����B<BR>
     * @@return Date[]
     */
    protected Date[] getBizDate()
    {
        return this.bizDate;
    }

    /**
     * (get�c�Ɠ�)<BR>
     * 
     * ����.�w����ɑΉ�����c�Ɠ���ԋp����B<BR>
     * �p�����[�^�`�F�b�N(-2..+6)�̌�A<BR>
     * this.�c�Ɠ�[T-2..+6][�w���]��ԋp����<BR>
     * @@param l_intSpecifiedPoint
     * @@return Date
     * @@roseuid 40F4E04E0310
     */
    public Date getBizDate(int l_intSpecifiedPoint)
    {
        //�p�����[�^�`�F�b�N�����{����B
        //�p�����[�^�͈̔�(-2 <= �p�����[�^ <= 6)
        if (-2 <= l_intSpecifiedPoint && l_intSpecifiedPoint <= 6)
        {
            return this.bizDate[l_intSpecifiedPoint + 2];
        }
        else
        {
            return null;
        }
    }

    /**
     * (set�c�Ɠ�[T-2..+6]) <BR>
     * 
     * �p�����[�^.�c�Ɠ�[T-2..+6]��this.�c�Ɠ�[T-2..+6]�ɃZ�b�g����B<BR>
     * @@param l_datBizDate
     * @@roseuid 40F4C58B0003
     */
    public void setBizDate(Date[] l_datBizDate)
    {
        this.bizDate  = l_datBizDate;
     }

    /**
     * �iis�����~�敪�j<BR>
     * 
     * this.�����~�敪��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isTradingStop()
    {
        return this.tradingStop;
    }

    /**
     * �iset�����~�敪�j<BR>
     * 
     * �p�����[�^.�����~�敪��this.�����~�敪�ɃZ�b�g����B
     * <BR>
     * @@param l_blnTradingStop
     */
    public void setTradingStop(boolean l_blnTradingStop)
    {
        this.tradingStop = l_blnTradingStop;
    }

    /**
     * �iis�V�K���]�͋敪�j<BR>
     * 
     * this.�V�K���]�͋敪��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isMarginOpenPositionStop()
    {
        return this.marginOpenPositionStop;
    }

    /**
     * �iset�M�p�V�K���]�͋敪�j<BR>
     * 
     * �p�����[�^.�M�p�V�K���]�͋敪��this.�M�p�V�K���]�͋敪�ɃZ�b�g����B
     * <BR>
     * @@param l_blnMarginStop
     */
    public void setMarginOpenPositionStop(boolean l_blnMarginStop)
    {
        this.marginOpenPositionStop = l_blnMarginStop;
    }

    /**
     * �iis�o���]�͋敪�j<BR>
     * 
     * this.�o���]�͋敪��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isPaymentStop()
    {
        return this.paymentStop;
    }

    /**
     * �iset�o���]�͋敪�j<BR>
     * 
     * �p�����[�^.�o���]�͋敪��this.�o���]�͋敪�ɃZ�b�g����B
     * <BR>
     * @@param l_blnPayStop
     */
    public void setPaymentStop(boolean l_blnPayStop)
    {
        this.paymentStop = l_blnPayStop;
    }

    /**
     * �iis���̑����i���t�]�͋敪�j<BR>
     * 
     * this.���̑����i���t�]�͋敪��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isOtherTradingStop()
    {
        return this.otherTradingStop;
    }

    /**
     * �iset���̑����i���t�]�͋敪�j<BR>
     * 
     * �p�����[�^.���̑����i���t�]�͋敪��this.���̑����i���t�]�͋敪�ɃZ�b�g����B
     * <BR>
     * @@param l_blnOtherStop
     */
    public void setOtherTradingStop(boolean l_blnOtherStop)
    {
        this.otherTradingStop = l_blnOtherStop;
    }

    /**
     * (is�Ǐؖ������敪)  <BR>
     * <BR>
     * this.�Ǐؖ������敪��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isAdditionalDepositDiv()
    {
        return this.additionalDepositDiv;
    }

    /**
     * (set�Ǐؖ������敪) <BR>
     * <BR>
     * �p�����[�^.�Ǐؖ������敪��this.�Ǐؖ������敪�ɃZ�b�g����B<BR>
     * @@param l_blnAdditionalDepositDiv
     */
    public void setAdditionalDepositDiv(boolean l_blnAdditionalDepositDiv)
    {
        this.additionalDepositDiv = l_blnAdditionalDepositDiv;
    }

    /**
     * �iis����������t�J�n�敪<��������>�j<BR>
     * 
     * this.����������t�J�n�敪<��������>��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isEquityNextDayOrderStartDiv()
    {
        return this.equityNextDayOrderStartDiv;
    }

    /**
     * �iset����������t�J�n�敪<��������>�j<BR>
     * 
     * �p�����[�^.����������t�J�n�敪<��������>��this.����������t�J�n�敪<��������>�ɃZ�b�g����B<BR>
     * @@param l_equityNextDayOrderStartDiv
     */
    public void setEquityNextDayOrderStartDiv(boolean l_equityNextDayOrderStartDiv)
    {
        this.equityNextDayOrderStartDiv = l_equityNextDayOrderStartDiv;
    }
    
    /**
     * �iis�o���I���敪<��������>�j<BR>
     * 
     * this.�o���I���敪<��������>��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isEquityExecutionDiv()
    {
        return this.equityExecutionDiv;
    }

    /**
     * �iset�o���I���敪<��������>�j<BR>
     * 
     * �p�����[�^.�o���I���敪<��������>��this.�o���I���敪<��������>�ɃZ�b�g����B<BR>
     * @@param l_equityExecutionDiv
     * @@roseuid 40F4C5A501C8
     */
    public void setEquityExecutionDiv(boolean l_equityExecutionDiv)
    {
        this.equityExecutionDiv = l_equityExecutionDiv;
    }

    /**
     * �iis�o���I���敪<�I�v�V����>�j<BR>
     * 
     * this.�o���I���敪<�I�v�V����>��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isOptionExecutionDiv()
    {
        return this.optionExecutionDiv;
    }

    /**
     * �iset�o���I���敪<�I�v�V����>�j<BR>
     * 
     * �p�����[�^.�o���I���敪<�I�v�V����>��this.�o���I���敪<�I�v�V����>�ɃZ�b�g����B
     * <BR>
     * @@param l_optionExecutionDiv
     * @@roseuid 40FCE431026D
     */
    public void setOptionExecutionDiv(boolean l_optionExecutionDiv)
    {
        this.optionExecutionDiv = l_optionExecutionDiv;
    }

    /**
     * �iis�a��،��]�����敪�j<BR>
     * 
     *this.�a��،��]�����敪��ԋp����B<BR>
     *
     * @@return boolean
     * @@roseuid 40B6D0C70185
     */
    public boolean isAssetEvalDiv()
    {
        return this.assetEvalDiv;
    }

    /**
     * �iset�a��،��]�����敪�j<BR>
     * 
     * �p�����[�^.�a��،��]�����敪��this.�a��،��]�����敪�ɃZ�b�g����B<BR>
     * @@param l_assetEvalDiv
     * @@roseuid 40B6E6D40250
     */
    public void setAssetEvalDiv(boolean l_assetEvalDiv)
    {
        this.assetEvalDiv = l_assetEvalDiv;
    }

    /**
     * �iis�a��،��]����<���������]��>�j<BR>
     * 
     * this.�a��،��]����<���������]��>��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 40F4C5B40051
     */
    public boolean isEquityEvalDiv()
    {
        return this.equityEvalDiv;
    }

    /**
     * �iset�a��،��]�����敪�j<BR>
     * 
     * �p�����[�^.�a��،��]����<���������]��>��this.�a��،��]����<���������]��>�ɃZ�b
     * �g����B<BR>
     * @@param l_equityEvalDiv
     * @@roseuid 40F4C5CF0012
     */
    public void setEquityEvalDiv(boolean l_equityEvalDiv)
    {
        this.equityEvalDiv = l_equityEvalDiv;
    }

    /**
     * �iis�a��،��]����<���M�]��>�j<BR>
     * 
     * this.�a��،��]����<���M�]��>��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 40F4C5D50022
     */
    public boolean isFundEvalDiv()
    {
        return this.fundEvalDiv;
    }

    /**
     * �iset�a��،��]����<���M�]��>�j<BR>
     * 
     * �p�����[�^.�a��،��]����<���M�]��>��this.�a��،��]����<���M�]��>�ɃZ�b�g����B
     * <BR>
     * @@param l_fundEvalDiv
     * @@roseuid 40F4C5DC0003
     */
    public void setFundEvalDiv(boolean l_fundEvalDiv)
    {
        this.fundEvalDiv = l_fundEvalDiv;
    }

    /**
     * �iis�a��،��]����<���]��>�j<BR>
     * 
     * this.�a��،��]����<���]��>��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 40F4C5EA0012
     */
    public boolean isBondEvalDiv()
    {
        return this.bondEvalDiv;
    }

    /**
     * �iset�a��،��]����<���]��>�j<BR>
     * 
     * �p�����[�^.�a��،��]����<���]��>��this.�a��،��]����<���]��>�ɃZ�b�g����B
     * <BR>
     * @@param l_bondEvalDiv
     * @@roseuid 40F4C5F1012C
     */
    public void setBondEvalDiv(boolean l_bondEvalDiv)
    {
        this.bondEvalDiv = l_bondEvalDiv;
    }

    /**
     * �iis�a��،��]����<GP�]��>�j<BR>
     * 
     * this.�a��،��]����<GP�]��>��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 40F4C6070051
     */
    public boolean isGpEvalDiv()
    {
        return this.gpEvalDiv;
    }

    /**
     * �iset�a��،��]����<GP�]��>�j<BR>
     * 
     * �p�����[�^.�a��،��]����<GP�]��>��this.�a��،��]����<GP�]��>�ɃZ�b�g����B<BR>
     * @@param l_gpEvalDiv
     * @@roseuid 40F4C60E0003
     */
    public void setGpEvalDiv(boolean l_gpEvalDiv)
    {
        this.gpEvalDiv = l_gpEvalDiv;
    }

    /**
     * �iget�a��،��]�����x�z�j<BR>
     * 
     * this.�a��،��]�����x�z��ԋp����B<BR>
     * @@return double
     * @@roseuid 40F4C6150206
     */
    public double getMaxAssessment()
    {
        return this.maxAssessment;
    }

    /**
     * �iset�a��،��]�����x�z�j<BR>
     * 
     * �p�����[�^.�a��،��]�����x�z��this.�a��،��]�����x�z�ɃZ�b�g����B<BR>
     * @@param l_dblMaxAssessment
     * @@roseuid 40F4C626009F
     */
    public void setMaxAssessment(double l_dblMaxAssessment)
    {
        this.maxAssessment = l_dblMaxAssessment;
    }

    /**
     * �iget���ʗ��֋����сj<BR>
     * 
     * this.���ʗ��֋����т�ԋp����B<BR>
     * @@return double
     * @@roseuid 40FF4C5E0191
     */
    public double getSpecialDebitAmount()
    {
        return this.specialDebitAmount;
    }

    /**
     * �iset���ʗ��֋����сj<BR>
     * 
     * �p�����[�^.���ʗ��֋����т�this.���ʗ��֋����тɃZ�b�g����B<BR>
     * @@param l_dblSpecialDebitAmount
     * @@roseuid 40FF4C690366
     */
    public void setSpecialDebitAmount(double l_dblSpecialDebitAmount)
    {
        this.specialDebitAmount = l_dblSpecialDebitAmount;
    }

    /**
     * �iget�����������l�j<BR>
     * 
     * this.�����������l��ԋp����B<BR>
     * @@return double
     * @@roseuid 40BF12EA0264
     */
    public double getMaxContPrice()
    {
        return this.maxContPrice;
    }

    /**
     * �iset�����������l�j<BR>
     * 
     * �p�����[�^.�����������l��this.�����������l�ɃZ�b�g����B<BR>
     * @@param l_dblMaxContPrice
     * @@roseuid 40BF131100EC
     */
    public void setMaxContPrice(double l_dblMaxContPrice)
    {
        this.maxContPrice = l_dblMaxContPrice;
    }

    /**
     * �iget�ۏ؋����j<BR>
     * 
     * this.�ۏ؋�����ԋp����B<BR>
     * @@return int
     * @@roseuid 40BAD8ED032C
     */
    public int getMarginDepositRate()
    {
        return this.marginDepositRate;
    }

    /**
     * �iset�ۏ؋����j<BR>
     * 
     * �p�����[�^.�ۏ؋�����this.�ۏ؋����ɃZ�b�g����B<BR>
     * @@param l_intMarginDepositRate
     * @@roseuid 40BAD8F702DE
     */
    public void setMarginDepositRate(int l_intMarginDepositRate)
    {
        this.marginDepositRate = l_intMarginDepositRate;
    }

    /**
     * �iget�����ۏ؋����j<BR>
     * 
     * this.�����ۏ؋�����ԋp����B<BR>
     * @@return int
     * @@roseuid 40C528DC01C0
     */
    public int getCashMarginDepositRate()
    {
        return this.cashMarginDepositRate;
    }

    /**
     * �iset�����ۏ؋����j<BR>
     * 
     * �p�����[�^.�����ۏ؋�����this.�����ۏ؋����ɃZ�b�g����B<BR>
     * @@param l_intCashMarginDepositRate
     * @@roseuid 40C528E40385
     */
    public void setCashMarginDepositRate(int l_intCashMarginDepositRate)
    {
        this.cashMarginDepositRate = l_intCashMarginDepositRate;
    }

    /**
     * �iget�ۏ؋��ێ����j<BR>
     * 
     * this.�ۏ؋��ێ�����ԋp����B<BR>
     * @@return int
     * @@roseuid 40C515D50394
     */
    public int getMarginMentenanceRate()
    {
        return this.marginMaintenanceRate;
    }

    /**
     * �iset�ۏ؋��ێ����j<BR>
     * 
     * �p�����[�^.�ۏ؋��ێ�����this.�ۏ؋��ێ����ɃZ�b�g����B<BR>
     * @@param l_intMarginMentenanceRate
     * @@roseuid 40C515DE0162
     */
    public void setMarginMentenanceRate(int l_intMarginMentenanceRate)
    {
        this.marginMaintenanceRate = l_intMarginMentenanceRate;
    }

    /**
     * �iget�Œ�K�v�ۏ؋��j<BR>
     * 
     * this.�Œ�K�v�ۏ؋���ԋp����B<BR>
     * @@return double
     * @@roseuid 40BAD4C00399
     */
    public double getMinMarginDeposit()
    {
        return this.minMarginDeposit;
    }

    /**
     * �iset�Œ�K�v�ۏ؋��j<BR>
     * 
     * �p�����[�^.�Œ�K�v�ۏ؋���this.�Œ�K�v�ۏ؋��ɃZ�b�g����B<BR>
     * @@param l_dblMinMarginDeposit
     * @@roseuid 40BAD4CB034B
     */
    public void setMinMarginDeposit(double l_dblMinMarginDeposit)
    {
        this.minMarginDeposit = l_dblMinMarginDeposit;
    }

    /**
     * �iget�@@��ۏ؋����j<BR>
     * 
     * this.�@@��ۏ؋�����ԋp����B<BR>
     * @@return int
     */
    public int getLegalMarginDepositRate()
    {
        return this.legalMarginDepositRate;
    }

    /**
     * �iset�@@��ۏ؋����j<BR>
     * 
     * �p�����[�^.�@@��ۏ؋�����this.�@@��ۏ؋����ɃZ�b�g����B<BR>
     * @@param l_intLegalMarginDepositRate
     */
    public void setLegalMarginDepositRate(int l_intLegalMarginDepositRate)
    {
        this.legalMarginDepositRate = l_intLegalMarginDepositRate;
    }

    /**
     * �iget�@@��Œ�K�v�ۏ؋��j<BR>
     * 
     * this.�@@��Œ�K�v�ۏ؋���ԋp����B<BR>
     * @@return double
     * @@roseuid 40BAD97F02DE
     */
    public double getLegalMinMarginDeposit()
    {
        return this.legalMinMarginDeposit;
    }

    /**
     * �iset�@@��Œ�K�v�ۏ؋��j<BR>
     * 
     * �p�����[�^.�@@��K�v�ۏ؋���this.�@@��K�v�ۏ؋��ɃZ�b�g����B<BR>
     * @@param l_dblLegalMinMarginDeposit
     * @@roseuid 40C7FC640393
     */
    public void setLegalMinMarginDeposit(double l_dblLegalMinMarginDeposit)
    {
        this.legalMinMarginDeposit = l_dblLegalMinMarginDeposit;
    }

    /**
     * �iget�]�͌v�Z��p�|�ځj<BR>
     * 
     * this.�]�͌v�Z��p�|�ڂ�ԋp����B<BR>
     * @@return int
     * @@roseuid 40BAE01C0167
     */
    public int getSubstituteRate()
    {
        return this.substituteRate;
    }

    /**
     * �iset�]�͌v�Z��p�|�ځj<BR>
     * 
     * �p�����[�^.�]�͌v�Z��p�|�ڂ�this.�]�͌v�Z��p�|�ڂɃZ�b�g����B<BR>
     * @@param l_intSubstituteRate
     * @@roseuid 40BAE0160186
     */
    public void setSubstituteRate(int l_intSubstituteRate)
    {
        this.substituteRate = l_intSubstituteRate;
    }

    /**
     * �iget�]�͌v�Z���<�������t�^�M�p����>�j<BR>
     * 
     * this.�]�͌v�Z���<�������t/�M�p����>��ԋp����B<BR>
     * @@return int
     * @@roseuid 40C5B6EF0398
     */
    public int getEquityBasePoint()
    {
        return this.equityBasePoint;
    }

    /**
     * �iset�]�͌v�Z���<�������t�^�M�p����>�j<BR>
     * 
     * �p�����[�^.�]�͌v�Z���<�������t/�M�p����>��this.�]�͌v�Z���<�������t/�M�p?
     * ��>�ɃZ�b�g����B<BR>
     * @@param l_intEquityBasePoint
     * @@roseuid 40C5B6F60359
     */
    public void setEquityBasePoint(int l_intEquityBasePoint)
    {
        this.equityBasePoint = l_intEquityBasePoint;
    }

    /**
     * �iget�]�͌v�Z���<�M�p�V�K��>�j<BR>
     * 
     * this.�]�͌v�Z���<�M�p�V�K��>��ԋp����B<BR>
     * @@return int
     * @@roseuid 40DB82550312
     */
    public int getMarginBasePoint()
    {
        return this.marginBasePoint;
    }

    /**
     * �iset�]�͌v�Z���<�M�p�V�K��>�j<BR>
     * 
     * �p�����[�^.�]�͌v�Z���<�M�p�V�K��>��this.�]�͌v�Z���<�M�p�V�K��>�ɃZ�b�g��
     * ��B<BR>
     * @@param l_intMarginBasePoint
     * @@roseuid 40DB82550322
     */
    public void setMarginBasePoint(int l_intMarginBasePoint)
    {
        this.marginBasePoint = l_intMarginBasePoint;
    }

    /**
     * �iget�]�͌v�Z���<���M>�j<BR>
     * 
     * this.�]�͌v�Z���<���M>��ԋp����B<BR>
     * @@return int
     * @@roseuid 40DB826F0041
     */
    public int getFundBasePoint()
    {
        return this.fundBasePoint;
    }

    /**
     * �iset�]�͌v�Z���<���M>�j<BR>
     * 
     * �p�����[�^.�]�͌v�Z���<���M>��this.�]�͌v�Z���<���M>�ɃZ�b�g����B
     * <BR>
     * @@param l_intFundBasePoint
     * @@roseuid 40DB826F0051
     */
    public void setFundBasePoint(int l_intFundBasePoint)
    {
        this.fundBasePoint = l_intFundBasePoint;
    }

    /**
     * �iget�]�͌v�Z���<�o��>�j<BR>
     * 
     * this.�]�͌v�Z���<�o��>��ԋp����B<BR>
     * @@return int
     * @@roseuid 40DB8291033C
     */
    public int getPaymentBasePoint()
    {
        return this.paymentBasePoint;
    }

    /**
     * �iset�]�͌v�Z���<�o��>�j<BR>
     * 
     * �p�����[�^.�]�͌v�Z���<�o��>��this.�]�͌v�Z���<�o��>�ɃZ�b�g����B<BR>
     * @@param l_intPaymentBasePoint
     * @@roseuid 40DB8291034C
     */
    public void setPaymentBasePoint(int l_intPaymentBasePoint)
    {
        this.paymentBasePoint = l_intPaymentBasePoint;
    }

    /**
     * �iget�]�͌v�Z���<�I�v�V�����V�K����>�j<BR>
     * 
     * this.�]�͌v�Z���<�I�v�V�����V�K����>��ԋp����B<BR>
     * @@return int
     * @@roseuid 40DBAD930067
     */
    public int getOptionBasePoint()
    {
        return this.optionBasePoint;
    }

    /**
     * �iset�]�͌v�Z���<�I�v�V�����V�K����>�j<BR>
     * 
     * �p�����[�^.�]�͌v�Z���<�I�v�V�����V�K����>��this.�]�͌v�Z���<�I�v�V�����V?
     * ����>�ɃZ�b�g����B<BR>
     * @@param l_intOptionBasePoint
     * @@roseuid 40DBAD930077
     */
    public void setOptionBasePoint(int l_intOptionBasePoint)
    {
        this.optionBasePoint = l_intOptionBasePoint;
    }

    /**
     * �iget�]�͌v�Z���<���̑����t>�j<BR>
     * 
     * this.�]�͌v�Z���<���̑����t>��ԋp����B<BR>
     * @@return int
     * @@roseuid 40DB828001E6
     */
    public int getOtherBasePoint()
    {
        return this.otherBasePoint;
    }

    /**
     * �iset�]�͌v�Z���<���̑����t>�j<BR>
     * 
     * �p�����[�^.�]�͌v�Z���<���̑����t>��this.�]�͌v�Z���<���̑����t>�ɃZ�b�g��
     * ��B<BR>
     * @@param l_intOtherBasePoint
     * @@roseuid 40DB828001F5
     */
    public void setOtherBasePoint(int l_intOtherBasePoint)
    {
        this.otherBasePoint = l_intOtherBasePoint;
    }

    /**
     * �iget�]�͌v�Z���<�~�j��>�j<BR>
     * 
     * this.�]�͌v�Z���<�~�j��>��ԋp����B<BR>
     * @@return int
     * @@roseuid 40DB828001E6
     */
    public int getMstkBasePoint()
    {
        return this.mstkBasePoint;
    }

    /**
     * �iset�]�͌v�Z���<�~�j��>�j<BR>
     * 
     * �p�����[�^.�]�͌v�Z���<�~�j��>��this.�]�͌v�Z���<�~�j��>�ɃZ�b�g��
     * ��B<BR>
     * @@param l_intMstkBasePoint
     * @@roseuid 40DB828001F5
     */
    public void setMstkBasePoint(int l_intMstkBasePoint)
    {
        this.mstkBasePoint = l_intMstkBasePoint;
    }

    /**
     * �iget�]�͌v�Z���<�ݓ�>�j<BR>
     * 
     * this.�]�͌v�Z���<�ݓ�>��ԋp����B<BR>
     * @@return int
     * @@roseuid 40DB828001E6
     */
    public int getRuitoBasePoint()
    {
        return this.ruitoBasePoint;
    }

    /**
     * �iset�]�͌v�Z���<�ݓ�>�j<BR>
     * 
     * �p�����[�^.�]�͌v�Z���<�ݓ�>��this.�]�͌v�Z���<�ݓ�>�ɃZ�b�g��
     * ��B<BR>
     * @@param l_intRuitoBasePoint
     * @@roseuid 40DB828001F5
     */
    public void setRuitoBasePoint(int l_intRuitoBasePoint)
    {
        this.ruitoBasePoint = l_intRuitoBasePoint;
    }

    /**
     * �iget�]�͌v�Z���<IPO>�j<BR>
     * 
     * this.�]�͌v�Z���<IPO>��ԋp����B<BR>
     * @@return int
     * @@roseuid 40DB828001E6
     */
    public int getIpoBasePoint()
    {
        return this.ipoBasePoint;
    }

    /**
     * �iset�]�͌v�Z���<IPO>�j<BR>
     * 
     * �p�����[�^.�]�͌v�Z���<IPO>��this.�]�͌v�Z���<IPO>�ɃZ�b�g��
     * ��B<BR>
     * @@param l_intIpoBasePoint
     * @@roseuid 40DB828001F5
     */
    public void setIpoBasePoint(int l_intIpoBasePoint)
    {
        this.ipoBasePoint = l_intIpoBasePoint;
    }

    /**
     * �iget�]�͌v�Z���<��������>�j<BR>
     * 
     * this.�]�͌v�Z���<��������>��ԋp����B<BR>
     * @@return int
     * @@roseuid 40DB828001E6
     */
    public int getFeqBasePoint()
    {
        return this.feqBasePoint;
    }

    /**
     * �iset�]�͌v�Z���<��������>�j<BR>
     * 
     * �p�����[�^.�]�͌v�Z���<��������>��this.�]�͌v�Z���<��������>�ɃZ�b�g��
     * ��B<BR>
     * @@param l_intFeqBasePoint
     * @@roseuid 40DB828001F5
     */
    public void setFeqBasePoint(int l_intFeqBasePoint)
    {
        this.feqBasePoint = l_intFeqBasePoint;
    }

    /**
     * �iget������<�����^�M�p>�j<BR>
     * <BR>
     * this.������<�����^�M�p>��ԋp����B
     * <BR>
     * @@return int
     */
    public int getEquityBizDate()
    {
        return this.equityBizDate;
    }

    /**
     * �iset������<�����^�M�p>�j<BR>
     * <BR>
     * �p�����[�^.������<�����^�M�p>��this.������<�����^�M�p>�ɃZ�b�g����B
     * <BR>
     * @@param l_intBizDate
     */
    public void setEquityBizDate(int l_intBizDate)
    {
        this.equityBizDate = l_intBizDate;
    }

    /**
     * �icalc�w����j<BR>
     * 
     * �p�����[�^.�c�Ɠ��ɑΉ�����B�w�����ԋp����B<BR>
     * <BR>
     * ���p�����[�^.�c�Ɠ���this.�c�Ɠ�[T-2..+6]�͈̔͊O�ł���������O���X���[����B<BR>
     * ���c�Ɠ��Ǝw����̑Ή��E�E�E<BR>
     * �p�����[�^.�c�Ɠ� = this.�c�Ɠ�[T+0] �� 0 ��ԋp<BR>
     * �p�����[�^.�c�Ɠ� = this.�c�Ɠ�[T+1] �� 1 ��ԋp<BR>
     * �p�����[�^.�c�Ɠ� = this.�c�Ɠ�[T+2] �� 2 ��ԋp<BR>
     * �p�����[�^.�c�Ɠ� = this.�c�Ɠ�[T+3] �� 3 ��ԋp<BR>
     * �p�����[�^.�c�Ɠ� = this.�c�Ɠ�[T+4] �� 4 ��ԋp<BR>
     * �p�����[�^.�c�Ɠ� = this.�c�Ɠ�[T+5] �� 5 ��ԋp<BR>
     * �p�����[�^.�c�Ɠ� = this.�c�Ɠ�[T+6] �� 6 ��ԋp<BR>
     * �p�����[�^.�c�Ɠ� = this.�c�Ɠ�[T-1] �� -1 ��ԋp<BR>
     * �p�����[�^.�c�Ɠ� = this.�c�Ɠ�[T-2] �� -2 ��ԋp<BR>
     * @@param l_datBizDate
     * @@return int
     * @@roseuid 40FCC6440377
     */
    public int calcSpecifiedPoint(Date l_datBizDate)
    {
        //this.�c�Ɠ��̔z��̗v�f�����擾����B
        int l_intLength = this.bizDate.length;
        
        //���Ԃ��i0�`�v�f��-1�j�͈̔͂Ń��[�v
        for (int index = 0; index < l_intLength; index++)
        {
            //�c�Ɠ�[index]�ƃp�����[�^.�c�Ɠ�����������
            if (WEB3DateUtility.compareToDay(this.bizDate[index], l_datBizDate) == 0)
            {
                //���Ԃ�ԋp����B
                return index - 2;
            }
        }
        
        //�G���[���X���[
        log.error("illegal Argument");
        throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
            this.getClass().getName() + ".calcSpecifiedPoint(Date)");
    }

    /**
     * �iroll�c�Ɠ��j<BR>
     * 
     * �p�����[�^.�c�Ɠ����A�p�����[�^.���Z�^���Z���� ���X���C�h�������c�Ɠ���<BR>
     * this.�c�Ɠ�[T-2..+6]���擾���ԋp����B<BR>
     * <BR>
     * ���X���C�h�������c�Ɠ���this.�c�Ɠ�[T-2..+6]�͈̔͊O�ł��������A<BR>
     * ��O���X���[����B<BR>
     * @@param l_datBizDate
     * @@param l_intRollDays
     * @@return Date
     * @@roseuid 40F4C7CC014B
     */
    public Date rollBizDate(Date l_datBizDate, int l_intRollDays)
    {
        //�p�����[�^.�c�Ɠ��̍���
        int l_intIndex = 0;
        //�������ʃt���O
        boolean l_resultFlag = false;

        //�c�Ɠ��̗v�f�����擾����B
        int l_intLength = this.bizDate.length;

        //���Ԃ��i0�`�v�f��-1�j�͈̔͂Ń��[�v
        for (int index = 0; index < l_intLength; index++)
        {
            if (WEB3DateUtility.compareToDay(this.bizDate[index], l_datBizDate) == 0)
            {
                //�p�����[�^.�c�Ɠ��̍��Ԃ��擾
                l_intIndex = index - 2;
                //�������ʃt���O��TRUE�ɂ���
                l_resultFlag = true;

                //���[�v���甲����
                break;
            }
        }

        //�擾�������ԂɃ��[�������������������Z����B
        l_intIndex = l_intIndex + l_intRollDays;

        //�����������A�w�荀�Ԃ��c�Ɠ��͈͓̔��̎�
        if (l_resultFlag == true && -2 <= l_intIndex && l_intIndex <= 6)
        {
            return this.getBizDate( l_intIndex );
        }
        else
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".rollBizDate(Date, int)");
        }
    }
    
    
    /**
     * �iget�w����͈́j<BR>
     * �w����͈�(T+5)��ԋp����B<BR>
     * @@return int
     */
    public int getSpecifiedPointRange()
    {
        return WEB3TPSpecifiedPointDef.T_5;
    }

    /**
     * �icalc�]�͌v�Z����j<BR>
     * 
     * ������Ƃ̗]�͌v�Z������Z�o���A���g�̃v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�]�͌v�Z����)calc�]�͌v�Z����v�Q��<BR>
     * 
     * @@throws WEB3SystemLayerException
     * @@roseuid 40D7C9B801C1
     */
    protected void calcBasePoint(WEB3GentradeSubAccount l_subAccount)
    {
        /*
         * ������ԃR���e�L�X�g�����X���b�h���[�J������擾����B
         */
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
    
        //�s��R�[�h�A��t���ԋ敪�A�����R�[�h�R�[�h��ޔ�����B
        String l_marketCode = l_clendarContext.getMarketCode();
        String l_tradingTimeType = l_clendarContext.getTradingTimeType();
        String l_productCode = l_clendarContext.getProductCode();
    
        //�����R�[�h��0:DEFULT���Z�b�g
        WEB3GentradeTradingTimeManagement.resetProductCode("0");

        //�،���Ђ��擾
        WEB3GentradeInstitution l_institution =
            (WEB3GentradeInstitution)l_subAccount.getInstitution();
            
        //�،���Ђ̃f�[�^�\�[�X���擾����B 
        InstitutionRow l_institutionRow = 
            (InstitutionRow)l_institution.getDataSourceObject();
        
        //�����o���U�����{���擾
        String l_theDayTransfer =
            l_institutionRow.getTheDayTransfer();
             
        //�擾�����s��R�[�h==null�̏ꍇ
        if (l_marketCode == null)
        {
            //������ԃR���e�L�X�g�ɁA�s��R�[�h�i1�F�����j���Z�b�g
            WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.TOKYO);
        }
    
        //������
        Date l_orderDate = null;
        //�Ɩ����t���擾
        Date l_bizDate0 = this.getBizDate(0);
        //���c�Ɠ����擾
        Date l_bizDate1 = this.getBizDate(1);
        //���X�c�Ɠ����擾
        Date l_bizDate2 = this.getBizDate(2);
    
        /*
         * �������t�^�M�p�����^�M�p�V�K���̗]�͌v�Z����̌v�Z
         * �@@[get������()�̖߂�l��getBizDate()�̖߂�l�̏ꍇ]
         * �@@(�������@@���@@�Ɩ����t)
         * �@@�@@�]�͌v�Z���<�������t/�M�p����>��3
         * �@@[get������()�̖߂�l�������c�Ɠ��̏ꍇ]
         * �@@(�������@@���@@���c�Ɠ�)
         * �@@�@@�]�͌v�Z���<�������t/�M�p����>��4
         * �@@[�ȊO�̏ꍇ]
         * �@@�@@�]�͌v�Z���<�������t/�M�p����>��3
         * 
         * �@@��get������()�ɂ����āA�G���[���X���[���ꂽ�ꍇ
         * �@@�@@�]�͌v�Z���<�������t/�M�p����>��3         
         */
        //������ԃR���e�L�X�g�ɁA��t���ԋ敪�i01�F�����E�M�p�j���Z�b�g
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        //���������擾����B
        try
        {
            l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //���������Ɩ����t�̎�
            if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
            {
                this.setEquityBasePoint(3);
                this.setMarginBasePoint(1);
                this.setEquityBizDate(0);
            }
            //���������������t�̎�
            else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
            {
                this.setEquityBasePoint(4);
                this.setMarginBasePoint(2);
                this.setEquityBizDate(1);
            }
            //�ȊO�̏ꍇ
            else
            {
                this.setEquityBasePoint(3);
                this.setMarginBasePoint(1);
                this.setEquityBizDate(0);
            }
        }
        //�G���[���X���[���ꂽ�ꍇ
        catch (WEB3SystemLayerException e)
        {
            this.setEquityBasePoint(3);
            this.setMarginBasePoint(1);
            this.setEquityBizDate(0);
        }

        //��Е��X�ʗ]�͌v�Z����.�g�M�p�V�K���\�z�K�p���h���擾����
        String l_strMarginOpenApplyDate = this
            .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINOPEN_APPLY_DATE);

        //�g�M�p�V�K���\�z�K�p���h == FROM_BIZ_DATE_UNTIL_T5�̏ꍇ
        if(WEB3TPMarginOpenApplyDateDef.FROM_BIZ_DATE_UNTIL_T5
            .equals(l_strMarginOpenApplyDate))
        {
            /*
             * �]�͌v�Z���<�M�p�V�K��> = ������<�����^�M�p>
             */
            int l_intBizDate = this.getEquityBizDate();
            this.setMarginBasePoint(l_intBizDate);
        }
        //�g�M�p�V�K���\�z�K�p���h == FROM_T2_UNTIL_T5�̏ꍇ
        if (WEB3TPMarginOpenApplyDateDef.FROM_T2_UNTIL_T5.equals(l_strMarginOpenApplyDate))
        {
            try
            {
                //������
                l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

                //���������Ɩ����t�̎�
                if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
                {
                    this.setMarginBasePoint(2);
                }
                //���������������t�̎�
                else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
                {
                    this.setMarginBasePoint(3);
                }
                //�ȊO�̏ꍇ
                else
                {
                    this.setMarginBasePoint(2);
                }
            }
            //�G���[���X���[���ꂽ�ꍇ
            catch (WEB3SystemLayerException l_ex)
            {
                this.setMarginBasePoint(2);
            }
        }
        
        /*
         * �I�v�V�����̗]�͌v�Z����̌v�Z
         * �@@[get������()�̖߂�l��getBizDate()�̖߂�l�̏ꍇ]
         * �@@(�������@@���@@�Ɩ����t)
         * �@@�@@�]�͌v�Z���<�I�v�V�����V�K��>��1
         * �@@[get������()�̖߂�l�������c�Ɠ��̏ꍇ]
         * �@@(�������@@���@@�����c�Ɠ�)
         * �@@�@@�]�͌v�Z���<�I�v�V�����V�K��>��2
         * �@@[�ȊO�̏ꍇ]
         * �@@�@@�]�͌v�Z���<�I�v�V�����V�K��>��1
         * 
         * �@@��get������()�ɂ����āA�G���[���X���[���ꂽ�ꍇ
         * �@@�@@�]�͌v�Z���<�I�v�V�����V�K��>��1�@@�@@         
         */
        //������ԃR���e�L�X�g�ɁA��t���ԋ敪�i11�F�����w���I�v�V�����j���Z�b�g
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(
            WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
        //������ԃR���e�L�X�g�ɁA�s��R�[�h�i0�FDEFULT�j���Z�b�g
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //���������擾
        try
        {
            l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //���������Ɩ����t�̎�
            if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
            {
                //is�[�ꎞ�ԑ�()�̖߂�l��true�̏ꍇ
                boolean l_blnIsEvening = WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone();
                if (l_blnIsEvening)
                {
                    this.setOptionBasePoint(2);
                }
                else
                {
                    this.setOptionBasePoint(1);
                }
            }
            //���������������t�̎�
            else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
            {
                this.setOptionBasePoint(2);
            }
            //�ȊO�̏ꍇ
            else
            {
                this.setOptionBasePoint(1);
            }
        }
        //��O���X���[���ꂽ�ꍇ
        catch (WEB3SystemLayerException e)
        {
            this.setOptionBasePoint(1);
        }

        /*
         * ���M�̗]�͌v�Z����̌v�Z
         * �E�����M�����t�\�z�K�p�����������ȍ~�̏ꍇ
         * �@@�@@�]�͌v�Z���<���M>��������
         * �@@��get������()�ɂ����āA�G���[���X���[���ꂽ�ꍇ
         * �@@�@@�]�͌v�Z���<���M>��0         
         * 
         * �E�����M�����t�\�z�K�p���I���������ȍ~�̏ꍇ
         * �@@[get������()�̖߂�l��(getBizDate()�̖߂�l)�̏ꍇ]
         * �@@(�������@@���@@�Ɩ����t)
         * �@@�@@�]�͌v�Z���<���M>��3
         * �@@[get������()�̖߂�l�����c�Ɠ��̏ꍇ]
         * �@@(�������@@���@@���c�Ɠ�)
         * �@@�@@�]�͌v�Z���<���M>��4
          �@@[get������()�̖߂�l�����X�c�Ɠ��̏ꍇ]
         * �@@(�������@@���@@���X�c�Ɠ�)
         * �@@�@@�]�͌v�Z���<���M>��5
         * �@@[�ȊO�̏ꍇ]
         * �@@�@@�]�͌v�Z���<���M>��3
         * 
         * �@@��get������()�ɂ����āA�G���[���X���[���ꂽ�ꍇ
         * �@@�@@�]�͌v�Z���<���M���t>��3         
         */
        //������ԃR���e�L�X�g�ɁA��t���ԋ敪�i06�F�����M���j���Z�b�g
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
        //������ԃR���e�L�X�g�ɁA�s��R�[�h�i0�FDEFULT�j���Z�b�g
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //�����M�����t�\�z�K�p���̐ݒ�l���擾
        String l_strMFBuyApplyDate = this.getInstBranCalcCondition(WEB3TPCalcCondition.MFBUY_APPLY_DATE);

        //���������擾
        try
        {
            l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //�K�p�����������ȍ~�̏ꍇ
            if(WEB3TPMutualFundBuyApplyDateDef.FROM_BIZ_DATE_UNTIL_T5.equals(l_strMFBuyApplyDate))
            {
                //���������
                this.setFundBasePoint(WEB3TPSpecifiedPointDef.T_0);
            }
            else
            {
                //���������Ɩ����t�̎�
                if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
                {
                    this.setFundBasePoint(3);
                }
                //�����������c�Ɠ��̎�
                else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
                {
                    this.setFundBasePoint(4);
                }
                //�����������X�c�Ɠ��̎�
                else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate2) == 0)
                {
                    this.setFundBasePoint(5);
                }
                //����ȊO
                else
                {
                    this.setFundBasePoint(3);
                }
            }
        }
        //��O���X���[���ꂽ�ꍇ
        catch (WEB3SystemLayerException e)
        {
            //�K�p�����������ȍ~�̏ꍇ
            if(WEB3TPMutualFundBuyApplyDateDef.FROM_BIZ_DATE_UNTIL_T5.equals(l_strMFBuyApplyDate))
            {
                this.setFundBasePoint(0);
            }
            else
            {
                this.setFundBasePoint(3);
            }
        }

        /*
         * �o���̗]�͌v�Z����̌v�Z
         *  
         *  �،���Ѓe�[�u��.�����o���U���ݎ��{='1'�̎� 
         * 
         *  �@@[get������()�̖߂�l��getBizDate()�̖߂�l�̏ꍇ]
         *  �@@(�������@@���@@�Ɩ����t)
         *  �@@�@@�]�͌v�Z���<�o�����t>��0
         *  �@@[get������()�̖߂�l�����c�Ɠ��̏ꍇ]
         *  �@@(�������@@���@@���c�Ɠ�)
         *  �@@�@@�]�͌v�Z���<�o�����t>��1
         *  �@@[�ȊO�̏ꍇ]
         *  �@@�@@�]�͌v�Z���<�o�����t>��0
         * 
         *�@@����ȊO�̂Ƃ��i�،���Ѓe�[�u��.�����o���U���ݎ��{=null ���܂ށj 
         *   �@@[get������()�̖߂�l��getBizDate()�̖߂�l�̏ꍇ]
         * �@@    (�������@@���@@�Ɩ����t)
         * �@@    �@@�]�͌v�Z���<�o�����t>��1
         *  �@@[get������()�̖߂�l�����c�Ɠ��̏ꍇ]
         *  �@@(�������@@���@@���c�Ɠ�)
         *  �@@�@@�]�͌v�Z���<�o�����t>��2
         *  �@@[�ȊO�̏ꍇ]
         *  �@@�@@�]�͌v�Z���<�o�����t>��1
         *
         * 
         * �@@    ��get������()�ɂ����āA�G���[���X���[���ꂽ�ꍇ
         * �@@�@@�]�͌v�Z���<�o��>��1
         */
        //������ԃR���e�L�X�g�ɁA��t���ԋ敪�i16�F�o���j���Z�b�g
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.PAYMENT);
        //������ԃR���e�L�X�g�ɁA�s��R�[�h�i0�FDEFULT�j���Z�b�g
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //���������擾
        try
        {
            l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
 
            //�،���Ѓe�[�u��.�����o���U�����{��'1'�̎�
            if (WEB3TheDayTransferDef.ENFORCEMENT.equals(l_theDayTransfer) == true)
            {
                //���������Ɩ����t�̎�
                if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
                {
                    this.setPaymentBasePoint(0);
                }
                //�����������c�Ɠ��̎�
                else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
                {
                    this.setPaymentBasePoint(1);
                }
                //����ȊO
                else
                {
                    this.setPaymentBasePoint(0);
                }
            }
            
            //����ȊO
            else
            {
                //���������Ɩ����t�̎�
                if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
                {
                    this.setPaymentBasePoint(1);
                }
                //�����������c�Ɠ��̎�
                else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
                {
                    this.setPaymentBasePoint(2);
                }
                //����ȊO
                else
                {
                    this.setPaymentBasePoint(1);
                }
            }

        }
        //�G���[���X���[���ꂽ�ꍇ
        catch (WEB3SystemLayerException e)
        {
            //�،���Ѓe�[�u��.�����o���U�����{��'1'�̎�
            if (WEB3TheDayTransferDef.ENFORCEMENT.equals(l_theDayTransfer) == true)
            {
                this.setPaymentBasePoint(0);                
            }
            //����ȊO
            else
            {
                this.setPaymentBasePoint(1);
            }
        }

        /*
         * ���̏��i�̗]�͌v�Z����̌v�Z
         * 
         * �@@�@@�]�͌v�Z���<���̑����t>��0
         */

            this.setOtherBasePoint(0);

        /*
         * �~�j���̗]�͌v�Z����̌v�Z
         * 
         * �@@[get������()�̖߂�l��(getBizDate()�̖߂�l)�̏ꍇ]
         * �@@(�������@@���@@(�Ɩ����t))
         * �@@�@@�]�͌v�Z���<�~�j��>��3
         * �@@[get������()�̖߂�l�����c�Ɠ��̏ꍇ]
         * �@@(�������@@���@@���c�Ɠ�)
         * �@@�@@�]�͌v�Z���<�~�j��>��4
         * �@@[get������()�̖߂�l�����X�c�Ɠ��̏ꍇ]
         * �@@(�������@@���@@���X�c�Ɠ�)
         * �@@�@@�]�͌v�Z���<�~�j��>��5
         * �@@[�ȊO�̏ꍇ]
         * �@@�@@�]�͌v�Z���<�~�j��>��4
         * 
         * �@@��get������()�ɂ����āA�G���[���X���[���ꂽ�ꍇ
         * �@@�@@�]�͌v�Z���<�~�j��>��4
         */
        //������ԃR���e�L�X�g�ɁA��t���ԋ敪�i05�F�~�j���j���Z�b�g
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.MINI_STOCK);
        //������ԃR���e�L�X�g�ɁA�s��R�[�h�i0�FDEFULT�j���Z�b�g
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //���������擾
        try
        {
            l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            //���������Ɩ����t�̎�
            if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
            {
                this.setMstkBasePoint(3);
            }
            //�����������c�Ɠ��̎�
            else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
            {
                this.setMstkBasePoint(4);
            }
            //�����������X�c�Ɠ��̎�
            else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate2) == 0)
            {
                this.setMstkBasePoint(5);
            }
            //����ȊO
            else
            {
                this.setMstkBasePoint(4);
            }
        }
        //�G���[���X���[���ꂽ�ꍇ
        catch (WEB3SystemLayerException e)
        {
            this.setMstkBasePoint(4);
        }

        /*
         * �ݓ��̗]�͌v�Z����̌v�Z
         * 
         * �@@[get������()�̖߂�l��(getBizDate()�̖߂�l�@@�܂��́@@���c�Ɠ�)�̏ꍇ]
         * �@@(�������@@���@@(�Ɩ����t))
         * �@@�@@�]�͌v�Z���<�ݓ�>��1
         * �@@[get������()�̖߂�l�����c�Ɠ��̏ꍇ]
         * �@@(�������@@���@@���c�Ɠ�)
         * �@@�@@�]�͌v�Z���<�ݓ�>��2
         * �@@[�ȊO�̏ꍇ]
         * �@@�@@�]�͌v�Z���<�ݓ�>��1
         * 
         * �@@��get������()�ɂ����āA�G���[���X���[���ꂽ�ꍇ
         * �@@�@@�]�͌v�Z���<�ݓ�>��1
         */
        //������ԃR���e�L�X�g�ɁA��t���ԋ敪�i07�F����F�j���Z�b�g
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
        //������ԃR���e�L�X�g�ɁA�s��R�[�h�i0�FDEFULT�j���Z�b�g
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //���������擾
        try
        {
            l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            //���������Ɩ����t�̎�
            if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
            {
                this.setRuitoBasePoint(1);
            }
            //�����������c�Ɠ��̎�
            else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
            {
                this.setRuitoBasePoint(2);
            }
            //����ȊO
            else
            {
                this.setRuitoBasePoint(1);
            }
        }
        //�G���[���X���[���ꂽ�ꍇ
        catch (WEB3SystemLayerException e)
        {
            this.setRuitoBasePoint(1);
        }


        /*
         * IPO�̗]�͌v�Z����̌v�Z
         * 
         * �@@�@@�]�͌v�Z���<IPO>��0
         */
 
            this.setIpoBasePoint(0);
 
        /*
         * ���������̗]�͌v�Z����̌v�Z
         * 
         * �@@[get������()�̖߂�l��(getBizDate()�̖߂�l)�̏ꍇ]
         * �@@(�������@@���@@(�Ɩ����t))
         * �@@�@@�]�͌v�Z���<��������>��3
         * �@@[get������()�̖߂�l�����c�Ɠ��̏ꍇ]
         * �@@(�������@@���@@���c�Ɠ�)
         * �@@�@@�]�͌v�Z���<��������>��4
         * �@@[�ȊO�̏ꍇ]
         * �@@�@@�]�͌v�Z���<��������>��3
         * 
         * �@@��get������()�ɂ����āA�G���[���X���[���ꂽ�ꍇ
         * �@@�@@�]�͌v�Z���<��������>��3
         */
        //������ԃR���e�L�X�g�ɁA��t���ԋ敪�i10�F�O�������j���Z�b�g
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
        //������ԃR���e�L�X�g�ɁA�s��R�[�h�i0�FDEFULT�j���Z�b�g
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //���������擾
        try
        {
            l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            //���������Ɩ����t�̎�
            if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
            {
                this.setFeqBasePoint(3);
            }
            //�����������c�Ɠ��̎�
            else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
            {
                this.setFeqBasePoint(4);
            }
            //����ȊO
            else
            {
                this.setFeqBasePoint(3);
            }
        }
        //�G���[���X���[���ꂽ�ꍇ
        catch (WEB3SystemLayerException e)
        {
            this.setFeqBasePoint(3);
        }
        
        //����J�����_�R���e�L�X�g�̎s��R�[�h�A��t���ԋ敪�A�����R�[�h�������J�n��Ԃɖ߂�
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_marketCode);
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_tradingTimeType);
        WEB3GentradeTradingTimeManagement.resetProductCode(l_productCode);
    }

    /**
     * �icalc�c�Ɠ��j<BR>
     * 
     * �c�Ɠ�[T-2..+6]���Z�o���A���g�̃v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�]�͌v�Z����)calc�c�Ɠ��v�Q��<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40FCCDD5027D
     */
    protected void calcBizDate()
    {
        
        TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();
    
        //�V�X�e���^�C�v�X�^���v���擾����B
        this.systemTimeStamp = tradingSystem.getSystemTimestamp();
    
        //�Ɩ����t���擾����B
        Date l_bizDate0 = tradingSystem.getBizDate();
        
        //�Ɩ����t���v���p�e�B�ɃZ�b�g����B
        this.bizDate[1] = l_bizDate0;
    
        //TimeStamp�^�̋Ɩ����t�𐶐�����B
        Timestamp l_bizDateStamp = new Timestamp(l_bizDate0.getTime());
    
        //�c�Ɠ��v�Z�N���X�𐶐�����B
        WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_bizDateStamp);
        //�c�Ɠ��z��̗v�f�����擾����B
        int l_intLength = this.bizDate.length;
    
        //n = -2�`6�܂ŌJ��Ԃ�����
        for (int index = -2; index < l_intLength - 2; index++)
        {
            Timestamp l_timeStamp;
            try
            {
                l_timeStamp = l_genBizDate.roll(index);
            }
            catch (WEB3SystemLayerException e)
            {
                throw new WEB3BaseRuntimeException(
                     e.getErrorInfo(),
                     e.getErrorMethod(),
                     e.getErrorMessage(),
                     e.getException());
             }

            //�����F�c�Ɠ�[T-2..+6]�ɃZ�b�g����B
            this.bizDate[index + 2] = new Date(l_timeStamp.getTime());
        }
        
    }

    /**
     * (get����������)<BR>
     * 
     * �������������Z�o���A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�]�͌v�Z����)get�����������v�Q��<BR>
     * @@param l_lngProductId - (����ID)
     * @@return java.util.Date
     */
    public Date getRightsOffDate(long l_lngProductId)
    {
        final String STR_METHOD_NAME = "getRightOffDate(long)";

        Long l_productId = new Long(l_lngProductId);
        boolean l_isRegistered = rightsOffDates.containsKey(l_productId);
        Date l_datRightsOffDate = null;
        
        //���o�^�̏ꍇ
        if(! l_isRegistered)
        {
            EqtypeProductRow l_eqProductRow = null;
            try
            {
                l_eqProductRow = EqtypeProductDao.findRowByPk(l_lngProductId);
            }
            catch (DataException de)
            {
                log.error(de.getMessage(), de);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
            
            try
            {
                //���Z��������ɂ���
                WEB3GentradeBizDate l_bizDateCalcUtil =
                    new WEB3GentradeBizDate(l_eqProductRow.getYearlyBooksClosingDate());

                //�����������擾(���-2�c�Ɠ�)
                l_datRightsOffDate = l_bizDateCalcUtil.roll(-2);
                
                //����ID�ƌ������������֘A�t����
                this.addRightsOffDate(l_productId, l_datRightsOffDate);
                
                StringBuffer l_sbLog = new StringBuffer("product_id=");
                l_sbLog.append(l_lngProductId);
                l_sbLog.append(" rights_off_date=");
                l_sbLog.append(WEB3DateUtility.formatDate(l_datRightsOffDate, "yyyy/MM/dd"));
                log.debug(l_sbLog.toString());
            }
            catch(WEB3SystemLayerException l_ex)
            {
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    l_ex.getErrorMethod(),
                    l_ex.getErrorMessage(),
                    l_ex.getException());
            }
        }
        //�o�^�ς̏ꍇ
        else
        {
            l_datRightsOffDate = (Date)rightsOffDates.get(l_productId);
        }

        return l_datRightsOffDate;
    }

    /**
     * (get�I�l)<BR>
     * 
     * �I�l���Z�o���A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�]�͌v�Z����)get�I�l�v�Q��<BR>
     * @@param l_lngProductId - (����ID)
     * @@param l_datDate - (�w���)
     * @@param l_lngMarketId - (�s��ID)
     * @@param l_blnUsePrimaryMarket - (�D��s��K�p�t���O)
     * @@return double
     */
    public double getClosingPrice(long l_lngProductId, Date l_datDate, long l_lngMarketId, boolean l_blnUsePrimaryMarket)
    {
        final String STR_METHOD_NAME = "getClosingPrice(long, Date, long, boolean)";

        double l_dblClosingPrice = 0.0;
        LastClosingPriceRow l_lastClosingPriceRow = null;
        Timestamp l_tsDate = null;
        
        String l_strProductId_Date = String.valueOf(l_lngProductId)
                                    + WEB3DateUtility.formatDate(l_datDate, "yyyyMMdd");
        boolean l_isRegistered = closingPrices.containsKey(l_strProductId_Date);
        
        //���o�^�̏ꍇ
        if(! l_isRegistered)
        {
            try
            {
                //TimeStamp�^�̎w����𐶐�����B
                l_tsDate = new Timestamp(l_datDate.getTime());
                l_lastClosingPriceRow = LastClosingPriceDao.findRowByPk(l_lngProductId, l_tsDate);
            }
            catch (DataFindException l_dfe)
            {
                //no operation
            }
            catch (DataQueryException l_dqe)
            {
                log.error(l_dqe.getMessage(), l_dqe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqe.getMessage(),
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(l_dne.getMessage(), l_dne);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            this.addClosingPrice(l_strProductId_Date, l_lastClosingPriceRow);
            
            StringBuffer l_sbLog = new StringBuffer("product_id=");
            l_sbLog.append(l_lngProductId);
            l_sbLog.append(" base_date=");
            l_sbLog.append(l_tsDate);
            l_sbLog.append(" LastClosingPriceRow=");
            l_sbLog.append(l_lastClosingPriceRow);
            log.debug(l_sbLog.toString());
        }
        //�o�^�ς̏ꍇ
        else
        {
            l_lastClosingPriceRow = (LastClosingPriceRow)closingPrices.get(l_strProductId_Date);
        }
        
        if(l_lastClosingPriceRow != null)
        {
            //�D��s���K�p����ꍇ
            if(l_blnUsePrimaryMarket)
            {
                l_dblClosingPrice = l_lastClosingPriceRow.getPrimaryClosingPrice();
            }
            //�s����擾���Ĕ��肷��ꍇ
            else
            {
                MarketRow l_marketRow = null;
                try
                {
                    l_marketRow = MarketDao.findRowByPk(l_lngMarketId);
                }
                catch (DataException de)
                {
                    log.error(de.getMessage(), de);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        de.getMessage(),
                        de);
                }
                //�s��R�[�h�擾
                String l_strMarketCode = l_marketRow.getMarketCode();
                //����
                if(WEB3MarketCodeDef.TOKYO.equals(l_strMarketCode))
                {
                    l_dblClosingPrice = l_lastClosingPriceRow.getTokyoClosingPrice();
                }
                //���
                else if(WEB3MarketCodeDef.OSAKA.equals(l_strMarketCode))
                {
                    l_dblClosingPrice = l_lastClosingPriceRow.getOosakaClosingPrice();
                }
                //����
                else if(WEB3MarketCodeDef.NAGOYA.equals(l_strMarketCode))
                {
                    l_dblClosingPrice = l_lastClosingPriceRow.getNagoyaClosingPrice();
                }
                //���̑�
                else
                {
                    l_dblClosingPrice = l_lastClosingPriceRow.getOtherClosingPrice();
                }
            }
        }
        
        return l_dblClosingPrice;
    }

    /**
     * (get����<����>)<BR>
     * 
     * ����<����>���Z�o���A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�]�͌v�Z����)get����<����>�v�Q��<BR>
     * @@param l_lngProductId - (����ID)
     * @@param l_lngMarketId - (�s��ID)
     * @@return double
     */
    public double getEqtypeQuote(long l_lngProductId, long l_lngMarketId)
    {
        final String STR_METHOD_NAME = "getEqtypeQuote(long,long)";

        double l_dblEqtypeQuotePrice = 0.0;
        
        String l_strProductIdMarketId = String.valueOf(l_lngProductId)
                                        + String.valueOf(l_lngMarketId);
        boolean l_isRegistered = eqtypeQuotes.containsKey(l_strProductIdMarketId);
        
        //���o�^�̏ꍇ
        if(! l_isRegistered)
        {
            //�����T�[�r�X�ɓn�����������擾
            EqTypeProductManager l_eqPman = (EqTypeProductManager)GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            EqTypeTradedProduct l_eqTradedProduct = null;  
            
            try
            {
                l_eqTradedProduct = (EqTypeTradedProduct)l_eqPman.getTradedProduct(l_lngProductId, l_lngMarketId);
            }
            catch (NotFoundException l_nfe)
            {
                //������������݂��Ȃ�������A0��Ԃ��B
                StringBuffer l_sbLog = new StringBuffer("�f�[�^��������Ȃ��̂Ŏ���=0 ������������}�X�^�[�e�[�u���̌�������:product_id=");
                l_sbLog.append(l_lngProductId);
                l_sbLog.append(" market_id=");
                l_sbLog.append(l_lngMarketId);
                l_sbLog.append(" eqtype_quote_price=");
                l_sbLog.append(l_dblEqtypeQuotePrice);
                log.debug(l_sbLog.toString());

                Double l_eqtypeQuotePrice = new Double(l_dblEqtypeQuotePrice);
                this.addEqtypeQuote(l_strProductIdMarketId, l_eqtypeQuotePrice);
                return l_dblEqtypeQuotePrice;
            }
            
            try
            {
                //�����T�[�r�X���擾
                EqTypeQuoteDataSupplierService l_quoteDataSupplierService =
                    (EqTypeQuoteDataSupplierService)GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getQuoteDataSupplierService();
                
                //����<����>���擾
                EqTypeQuoteData l_eqQuoteData
                    = l_quoteDataSupplierService.getEqTypeQuote(l_eqTradedProduct);

                // �ȉ��̗D�揇�ʂŁA�擾�ł���i0�łȂ��j�P���������Ƃ���B
                //�P�D���ݒl(EqTypeQuoteData.getCurrentPrice( ))
                //�Q�D���C�z�l(EqTypeQuoteData.getBidPrice( ))
                //�R�D���C�z�l(EqTypeQuoteData.getAskPrice( ))

                l_dblEqtypeQuotePrice = l_eqQuoteData.getCurrentPrice();

                if (l_dblEqtypeQuotePrice == 0.0)
                {
                    l_dblEqtypeQuotePrice = l_eqQuoteData.getBidPrice();

                    if (l_dblEqtypeQuotePrice == 0.0)
                    {
                        l_dblEqtypeQuotePrice = l_eqQuoteData.getAskPrice();
                    }
                }
                Double l_eqtypeQuotePrice = new Double(l_dblEqtypeQuotePrice);
                this.addEqtypeQuote(l_strProductIdMarketId, l_eqtypeQuotePrice);
                
                StringBuffer l_sbLog = new StringBuffer("product_id=");
                l_sbLog.append(l_lngProductId);
                l_sbLog.append(" market_id=");
                l_sbLog.append(l_lngMarketId);
                l_sbLog.append(" eqtype_quote_price=");
                l_sbLog.append(l_dblEqtypeQuotePrice);
                log.debug(l_sbLog.toString());

            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(), l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
        }
        //�o�^�ς̏ꍇ
        else
        {
            Double l_eqtypeQuotePrice = (Double)eqtypeQuotes.get(l_strProductIdMarketId);
            l_dblEqtypeQuotePrice = l_eqtypeQuotePrice.doubleValue();
        }

        return l_dblEqtypeQuotePrice;
    }
    
    /**
     * (add����������)<BR>
     * ����ID�ƌ������������֘A�Â���B<BR>
     * <BR>
     * HashMap�ɓo�^����B<BR>
     * �|�L�[�F�����̖���ID��Long�I�u�W�F�N�g<BR>
     * �|�l�@@�F�����̌���������<BR>
     * @@param l_productId - (����ID)
     * @@param l_datRightsOffDate - (����������)
     */
    protected void addRightsOffDate(Long l_productId, Date l_datRightsOffDate) 
    {
        rightsOffDates.put(l_productId, l_datRightsOffDate);
    }
    
    /**
     * (add�I�l)<BR>
     * ����ID+�w����ƏI�lParams���֘A�Â���B<BR>
     * <BR>
     * HashMap�ɓo�^����B<BR>
     * �|�L�[�F�����̖���ID+�w�����String�I�u�W�F�N�g<BR>
     * �|�l�@@�F�����̏I�lParams<BR>
     * @@param l_strProductId_Date - (����ID+�w���)
     * @@param l_lastClosingPriceRow - (�I�lParams)
     */
    protected void addClosingPrice(String l_strProductId_Date, LastClosingPriceRow l_lastClosingPriceRow) 
    {
        closingPrices.put(l_strProductId_Date, l_lastClosingPriceRow);
    }
    
    /**
     * (add����<����>)<BR>
     * ����ID+�s��ID�Ǝ���<����>���֘A�Â���B<BR>
     * <BR>
     * HashMap�ɓo�^����B<BR>
     * �|�L�[�F�����̖���ID+�s��ID��String�I�u�W�F�N�g<BR>
     * �|�l�@@�F�����̎���<����><BR>
     * @@param l_strProductIdMarketId - (����ID+�s��ID)
     * @@param Double - (����<����>)
     */
    protected void addEqtypeQuote(String l_strProductIdMarketId, Double l_EqtypeQuote) 
    {
        eqtypeQuotes.put(l_strProductIdMarketId, l_EqtypeQuote);
    }

    /**
     * �iget�]���P��Callback�j<BR>
     * 
     * this.�]���P��Callback��ԋp����B<BR>
     * @@return WEB3TPUnitPriceCallback
     */
    public WEB3TPUnitPriceCallback getUnitPriceCallback()
    {
        return this.unitPriceCallback;
    }

    /**
     * �iset�]���P��Callback�j<BR>
     * 
     * �p�����[�^.�]���P��Callback��this.�]���P��Callback�ɃZ�b�g����B<BR>
     * @@param l_unitPriceCallback
     */
    public void setUnitPriceCallback(WEB3TPUnitPriceCallback l_unitPriceCallback)
    {
        this.unitPriceCallback = l_unitPriceCallback;
    }

    /**
     * (get��Е��X�ʗ]�͌v�Z����)<BR>
     * 
     * ����.�v���t�@@�����X���ɑΉ�����l���}�b�v��茟�����ԋp����B
     * �����R�[�h�����݂��Ȃ��ꍇ�Anull��ԋp����B
     * 
     * @@param l_strName - (�v���t�@@�����X��)
     * @@return String
     */
    public String getInstBranCalcCondition(String l_strName)
    {
        boolean l_isRegistered = instBranCalcCondition.containsKey(l_strName);

        //�l
        String l_strValue = null;
        
        //�o�^�ς̏ꍇ
        if(l_isRegistered == true)
        {
            l_strValue = (String)instBranCalcCondition.get(l_strName);
        }

        return l_strValue;
    }

    /**
     * (add��Е��X�ʗ]�͌v�Z����)<BR>
     * <BR>
     * �P�j����.�v���t�@@�����X�����L�[�Ƃ��Ĉ���.�l��<BR>
     * Map�ithis.��Е��X�ʗ]�͌v�Z�����j�ɃZ�b�g����B<BR>
     * <BR>
     * �@@-this.��Е��X�ʗ]�͌v�Z����.put()���R�[��<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@Object�F�@@����.�v���t�@@�����X��<BR>
     * �@@�@@Object�F�@@����.�l<BR>
     * <BR>
     * @@param l_strName - (�v���t�@@�����X��)
     * @@param l_strValue - (�l)
     */
    protected void addInstBranCalcCondition(String l_strName, String l_strValue) 
    {
        instBranCalcCondition.put(l_strName, l_strValue);
    }

    /**
     * (get���X�^�C�v)<BR>
     * <BR>
     * this.���X�^�C�v��ԋp����B<BR>
     * <BR>
     * @@return BranchTypeEnum
     */
    public BranchTypeEnum getBranchType()
    {
        return branchType;
    }

    /**
     * (set���X�^�C�v) <BR>
     * <BR>
     * �p�����[�^.���X�^�C�v��this.���X�^�C�v�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_branchType - (���X�^�C�v)
     */
    public void setBranchType(BranchTypeEnum l_branchType)
    {
        this.branchType = l_branchType;
    }
    
    /**
     * (is�،��S�ۃ��[���敪)<BR> 
     * <BR>
     * this.�،��S�ۃ��[���敪��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isSecuredLoanSecAccOpenDiv()
    {
        return this.securedLoanSecAccOpenDiv;
    }
    
    /**
     * (set�،��S�ۃ��[���敪)<BR>  
     * <BR> 
     * �p�����[�^.�،��S�ۃ��[���敪��this.�،��S�ۃ��[���敪�ɃZ�b�g����B<BR> 
     * @@param l_blnSecuredLoanSecAccOpenDiv - (�،��S�ۃ��[���敪)
     */
    public void setSecuredLoanSecAccOpenDiv(boolean l_blnSecuredLoanSecAccOpenDiv)
    {
        this.securedLoanSecAccOpenDiv = l_blnSecuredLoanSecAccOpenDiv;
    }
    
    /**
     * (is�c�ƓX����]�̓`�F�b�N���{�敪)<BR>
     * <BR>
     * ����.������ʂ̉c�ƓX����]�̓`�F�b�N���{�敪��ԋp����B<BR>
     * <BR>
     * [�ԋp�l = ture �̏ꍇ]<BR>
     * �@@�c�ƓX�p�̎���]�̓`�F�b�N�����{<BR>
     * <BR>
     * [�ԋp�l = false �̏ꍇ]<BR>
     * �@@�ʏ�̎���]�̓`�F�b�N�����{<BR>
     * <BR>
     * �P�j"��Е��X�ʗ]�͌v�Z����"���擾����B<BR>
     * <BR>
     * �@@[a.���̏ꍇ]<BR>
     * �@@�i����.������� IN (401�F����������, 402�F�����蒍��)�j<BR>
     * <BR>
     * �@@�@@-"��Е��X�ʗ]�͌v�Z����" = this.get��Е��X�ʗ]�͌v�Z����(:String = "bond.salesoffice.tpcheck.div")<BR>
     * <BR>
     * <BR>
     * �Q�j�c�ƓX����]�̓`�F�b�N���{�敪��ԋp����B<BR>
     * <BR>
     * �@@[a.�c�ƓX�p�̎���]�̓`�F�b�N�����{����ꍇ]<BR>
     * �@@�i"��Е��X�ʗ]�͌v�Z����" = 1�FEXECUTE�j<BR>
     * <BR>
     * �@@�@@�ԋp�l�Ftrue<BR>
     * <BR>
     * �@@[a.�ʏ�̎���]�̓`�F�b�N�����{����ꍇ]<BR>
     * �@@�i�ȊO�̏ꍇ�j<BR>
     * <BR>
     * �@@�@@�ԋp�l�Ffalse<BR>
     * <BR>
     * @@param l_orderTypeEnum - (�������)
     * 
     * @@return boolean
     * @@roseuid 4507B8E00052
     */
    public boolean isSalesOfficeTPCheckDiv(OrderTypeEnum l_orderTypeEnum) 
    {
        
        //�P�j"��Е��X�ʗ]�͌v�Z����"���擾����B 
        //�@@[a.���̏ꍇ] 
        //�i����.������� IN (401�F����������, 402�F�����蒍��)�j 
        //-"��Е��X�ʗ]�͌v�Z����" = this.get��Е��X�ʗ]�͌v�Z����(:String = "bond.salesoffice.tpcheck.div")
        String l_strInstBranCalcCondition = "";
        if (OrderTypeEnum.BOND_BUY.equals(l_orderTypeEnum) || OrderTypeEnum.BOND_SELL.equals(l_orderTypeEnum))
        {
            l_strInstBranCalcCondition = getInstBranCalcCondition(
                WEB3BranchPreferencesNameDef.BOND_SALESOFFICE_TPCHECK_DIV);
        }
        
        //�Q�j�c�ƓX����]�̓`�F�b�N���{�敪��ԋp����B 
        //[a.�c�ƓX�p�̎���]�̓`�F�b�N�����{����ꍇ]
        //�i"��Е��X�ʗ]�͌v�Z����" = 1�FEXECUTE�j
        //�ԋp�l�Ftrue 
        //[a.�ʏ�̎���]�̓`�F�b�N�����{����ꍇ] 
        //�i�ȊO�̏ꍇ�j 
        //�ԋp�l�Ffalse
        if (WEB3SalesofficeTpcheckDivDef.EXECUTE.equals(
            l_strInstBranCalcCondition))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (is�a����S�ۏo����~�敪)<BR>  
     * <BR>  
     * this.�a����S�ۏo����~�敪��ԋp����B<BR>  
     * @@return boolean
     */
    public boolean isCashDepositStopDiv()
    {
        return cashDepositStopDiv;
    }
    
    /**
     * (set�a����S�ۏo����~�敪) <BR>  
     * <BR>  
     * �p�����[�^.�a����S�ۏo����~�敪��this.�a����S�ۏo����~�敪�ɃZ�b�g����B<BR>  
     * @@param l_blnCashDepositStopDiv - (�a����S�ۏo����~�敪)
     */
    public void setCashDepositStopDiv(boolean l_blnCashDepositStopDiv)
    {
        this.cashDepositStopDiv = l_blnCashDepositStopDiv;
    }

    /**
     * (is�I���b�N�X_�S�ۃ��[�������J�݋敪)<BR>
     * <BR>
     * this.�I���b�N�X_�S�ۃ��[�������J�݋敪��ԋp����B<BR>
     * <BR>
     * @@return boolean
     */
    public boolean isOrixSecuredLoanAccOpenDiv()
    {
        return this.orixSecuredLoanAccOpenDiv;
    }

    /**
     * (set�I���b�N�X_�S�ۃ��[�������J�݋敪)<BR>
     * <BR>
     * �p�����[�^.�����J�݂�this.�I���b�N�X_�S�ۃ��[�������J�݋敪�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_blnOrixSecuredLoanAccOpenDiv - (�����J�݋敪)
     */
    public void setOrixSecuredLoanAccOpenDiv(boolean l_blnOrixSecuredLoanAccOpenDiv)
    {
        this.orixSecuredLoanAccOpenDiv = l_blnOrixSecuredLoanAccOpenDiv;
    }

    /**
     * (get�I���b�N�X_�S�ۃ��[���o���\�z)<BR>
     * <BR>
     * this.�I���b�N�X_�S�ۃ��[���o���\�z��ԋp����B<BR>
     * <BR>
     * @@return String
     */
    public String getOrixSecuredLoanPaymentTradingPower()
    {
        return this.orixSecuredLoanPaymentTradingPower;
    }

    /**
     * (set�I���b�N�X_�S�ۃ��[���o���\�z)<BR>
     * <BR>
     * �p�����[�^.�o���\�z��this.�I���b�N�X_�S�ۃ��[���o���\�z�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_strOrixSecuredLoanPaymentTradingPower - (�o���\�z)
     */
    public void setOrixSecuredLoanPaymentTradingPower(String l_strOrixSecuredLoanPaymentTradingPower)
    {
        this.orixSecuredLoanPaymentTradingPower = l_strOrixSecuredLoanPaymentTradingPower;
    }

    /**
     * (get�I���b�N�X_�S�ۃ��[�����z���b�N�t���O)<BR>
     * <BR>
     * this.�I���b�N�X_�S�ۃ��[�����z���b�N�t���O��ԋp����B<BR>
     * <BR>
     * @@return String
     */
    public String getOrixSecuredLoanLockFlag()
    {
        return this.orixSecuredLoanLockFlag;
    }

    /**
     * (set�I���b�N�X_�S�ۃ��[�����z���b�N�t���O)<BR>
     * <BR>
     * �p�����[�^.���z���b�N�t���O��this.�I���b�N�X_�S�ۃ��[�����z���b�N�t���O�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_strOrixSecuredLoanLockFlag - (���z���b�N�t���O)
     */
    public void setOrixSecuredLoanLockFlag(String l_strOrixSecuredLoanLockFlag)
    {
        this.orixSecuredLoanLockFlag = l_strOrixSecuredLoanLockFlag;
    }

    /**
     * (get�I�l&lt;DB����&gt;)<BR>
     * <BR>
     * �I�l���Z�o���A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�]�͌v�Z����)get�I�l&lt;DB����&gt;�v�Q��<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * @@param l_datDate - (�w���)<BR>
     * @@param l_lngMarketId - (�s��ID)<BR>
     * @@param l_blnUsePrimaryMarket - (�D��s��K�p�t���O)<BR>
     * @@return double
     */
    public double getClosingPriceDBQuote(
        long l_lngProductId,
        Date l_datDate,
        long l_lngMarketId,
        boolean l_blnUsePrimaryMarket)
    {
        final String STR_METHOD_NAME = "getClosingPriceDBQuote(long, Date, long, boolean)";
        log.entering(STR_METHOD_NAME);

        //�I�lTemp = 0�@@���Z�b�g
        //��Ɨp�́u�I�lTemp�v�ϐ����쐬���A����������B
        double l_dblClosingPriceTemp = 0;

        QuoteClosingPriceRow l_quoteClosingPriceRow = null;

        try
        {
            //[�����I�lRow�I�u�W�F�N�g]���擾����B
            String l_strDate = WEB3DateUtility.formatDate(l_datDate, "yyyyMMdd");
            l_quoteClosingPriceRow = QuoteClosingPriceDao.findRowByPk(l_lngProductId, l_strDate);
        }
        catch (DataFindException l_ex)
        {
            //no operation
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //����t���[
        //�����I�lRow == null
        if (l_quoteClosingPriceRow == null)
        {
            //�I�lTemp = 0�@@���Z�b�g
            l_dblClosingPriceTemp = 0;
        }
        //����t���[
        else
        {
            //����t���[
            if (l_blnUsePrimaryMarket)
            {
                //�I�l���Z�b�g
                l_dblClosingPriceTemp = l_quoteClosingPriceRow.getPrimaryClosingPrice();
            }
            //����t���[
            else
            {
                MarketRow l_marketRow = null;

                try
                {
                    //[�s��Row�I�u�W�F�N�g]���擾����B
                    l_marketRow = MarketDao.findRowByPk(l_lngMarketId);
                }
                catch (DataException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                //�s��R�[�h�擾
                String l_strMarketCode = l_marketRow.getMarketCode();
                
                //����
                if(WEB3MarketCodeDef.TOKYO.equals(l_strMarketCode))
                {
                    l_dblClosingPriceTemp = l_quoteClosingPriceRow.getTokyoClosingPrice();
                }
                //���
                else if(WEB3MarketCodeDef.OSAKA.equals(l_strMarketCode))
                {
                    l_dblClosingPriceTemp = l_quoteClosingPriceRow.getOosakaClosingPrice();
                }
                //����
                else if(WEB3MarketCodeDef.NAGOYA.equals(l_strMarketCode))
                {
                    l_dblClosingPriceTemp = l_quoteClosingPriceRow.getNagoyaClosingPrice();
                }
                //���̑�
                else
                {
                    l_dblClosingPriceTemp = l_quoteClosingPriceRow.getOtherClosingPrice();
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblClosingPriceTemp;
    }
    
    /**
     * (create�]�͌v�Z����&lt;DB����&gt;)<BR>
     * (static���\�b�h)(create�]�͌v�Z����&lt;DB����&gt;)<BR>
     * <BR>
     * �]�͌v�Z�����I�u�W�F�N�g�𐶐����A�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�]�͌v�Z����)create�]�͌v�Z����&lt;DB����&gt;�v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@return WEB3TPCalcCondition
     */
    public static WEB3TPCalcCondition createCalcConditionDBQuote(WEB3GentradeSubAccount l_subAccount)
    {
        final String STR_METHOD_NAME = "createCalcConditionDBQuote(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //create�]�͌v�Z����(�⏕����)
        //�]�͌v�Z�����I�u�W�F�N�g���擾����B
        //[����]
        //�⏕���� = ����.�⏕����
        WEB3TPCalcCondition l_calcCondition = WEB3TPCalcCondition.createCalcCondition(l_subAccount);

        //�]���P��<DB����>Callback(�]�͌v�Z����)
        //�]���P��<DB����>�I�u�W�F�N�g�𐶐�����B
        //[����]
        //�]�͌v�Z���� = create�]�͌v�Z����()�̖߂�l
        WEB3TPUnitPriceDBQuoteCallback l_unitPriceDBQuoteCallback = 
            new WEB3TPUnitPriceDBQuoteCallback(l_calcCondition);

        //set�]���P��Callback(�]���P��Callback�C���^�[�t�F�[�X)
        //�]�͌v�Z�����I�u�W�F�N�g�ɁA���������]���P��<DB����>Callback�I�u�W�F�N�g���Z�b�g����B
        //[����]
        //�]���P��Callback�C���^�[�t�F�[�X = ���������]���P��<DB����>Callback�I�u�W�F�N�g
        l_calcCondition.setUnitPriceCallback(l_unitPriceDBQuoteCallback);

        log.exiting(STR_METHOD_NAME);
        return l_calcCondition;
    }

    /**
     * (get�O���I�l)<BR>
     * <BR>
     * �O���I�l���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�]�͌v�Z����)get�O���I�l�v�Q��<BR>
     * <BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getFeqLastClosingPrice(long l_lngProductId)
    {
        final String STR_METHOD_NAME = "getFeqLastClosingPrice(long)";
        log.entering(STR_METHOD_NAME);

        // get�O���I�lRow
        FeqLastClosingPriceRow l_feqLastClosingPriceRow = this.getFeqLastClosingPriceRow(l_lngProductId);

        if (l_feqLastClosingPriceRow == null)
        {
            try
            {
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                String l_strWhere = " product_id = ? ";
                String l_strOrderBy = " base_date desc ";
                Object l_bindVars[] = {new Long(l_lngProductId)};
                //�O���I�lRow���擾����
                List l_lisFeqLastClosingPriceRows =
                    l_queryProcesser.doFindAllQuery(
                        FeqLastClosingPriceRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        null,
                        l_bindVars);

                if (l_lisFeqLastClosingPriceRows.size() == 0)
                {
                    l_feqLastClosingPriceRow = null;
                }
                else
                {
                    l_feqLastClosingPriceRow = (FeqLastClosingPriceRow)l_lisFeqLastClosingPriceRows.get(0);
                }

                //add�O���I�lRow
                this.addFeqLastClosingPriceRow(l_lngProductId, l_feqLastClosingPriceRow);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //�O���I�lRow���擾�ł��Ȃ��ꍇ
        if (l_feqLastClosingPriceRow == null)
        {
            //0��ԋp
            log.exiting(STR_METHOD_NAME);
            return 0.0;
        }
        log.exiting(STR_METHOD_NAME);
        return l_feqLastClosingPriceRow.getFeqClosingPrice();
    }

    /**
     * (get�O���I�lRow)<BR>
     * �O���I�l(:Map)����O���I�lRow���擾����B<BR>
     * <BR>
     * Map.get()<BR>
     * �@@�L�[ = ����.����ID<BR>
     * <BR>
     * �������̖���ID�͕�����ɕϊ�����B<BR>
     * ��Map.get()�̖߂�l���O���I�lRow�ɃL���X�g���ĕԋp����B<BR>
     * <BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID
     * @@return FeqLastClosingPriceRow
     */
    protected FeqLastClosingPriceRow getFeqLastClosingPriceRow(long l_lngProductId)
    {
        //Map.get()
        //�L�[ = ����.����ID
        return (FeqLastClosingPriceRow)feqLastClosingPrice.get(String.valueOf(l_lngProductId));
    }

    /**
     * (add�O���I�lRow)<BR>
     * <BR>
     * �O���I�lRow���O���I�l(:Map)�ɓo�^����B<BR>
     * <BR>
     * Map.put() <BR>
     * �@@�L�[ = ����.����ID <BR>
     * �@@�l = ����.�O���I�lRow<BR>
     * <BR>
     * �������̖���ID�͕�����ɕϊ�����B<BR>
     * <BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID
     * @@param l_feqLastClosingPriceRow - (�O���I�lRow)<BR>
     * �O���I�lRow
     */
    protected void addFeqLastClosingPriceRow(long l_lngProductId, FeqLastClosingPriceRow l_feqLastClosingPriceRow)
    {
        //Map.put()
        //�L�[ = ����.����ID
        //�l = ����.�O���I�lRow
        feqLastClosingPrice.put(String.valueOf(l_lngProductId), l_feqLastClosingPriceRow);
    }

    /**
     * (isPTS�o���I���敪)<BR>
     * <BR>
     * this.PTS�o���I����ԋp����B<BR>
     * @@return boolean
     */
    public boolean isPtsOrderExecutionEndType()
    {
        return this.ptsOrderExecutionEndType;
    }

    /**
     * (setPTS�o���I���敪)<BR>
     * <BR>
     * �p�����[�^.PTS�o���I���敪��this.PTS�o���I���敪�ɃZ�b�g����B<BR>
     * @@param l_blnOrderexecutionEndType - (PTS�o���I���敪)<BR>
     * PTS�o���I���敪
     */
    public void setPtsOrderExecutionEndType(boolean l_blnOrderexecutionEndType)
    {
        this.ptsOrderExecutionEndType = l_blnOrderexecutionEndType;
    }

    /**
     * is�O�����v�����̗p<BR>
     * <BR>
     * this.�O�����v�����̗p��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isFeqDayTradeAdoption()
    {
        return feqDayTradeAdoption;
    }

    /**
     * (set�O�����v�����̗p)<BR>
     * <BR>
     * �p�����[�^.�O�����v�����̗p��this.�O�����v�����̗p�ɃZ�b�g����B<BR>
     * @@param feqDayTradeAdoption
     */
    public void setFeqDayTradeAdoption(boolean feqDayTradeAdoption)
    {
        this.feqDayTradeAdoption = feqDayTradeAdoption;
    }

}
@
