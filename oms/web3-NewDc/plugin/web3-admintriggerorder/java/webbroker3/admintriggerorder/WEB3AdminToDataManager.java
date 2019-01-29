head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��҃f�[�^�}�l�[�W��(WEB3AdminToDataManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 杊��](���u) �V�K�쐬
                 : 2006/08/23�@@�юu��(���u) �d�l�ύX���f��No.068,070,071,081
                 : 2006/10/18  ������(���u) �d�l�ύX���f��No.091,094
                 : 2006/11/28�@@����(���u) �d�l�ύX���f��No.117
                 : 2006/12/19�@@�����(���u) �d�l�ύX���f��No.122
                 : 2006/12/20�@@�����(���u) �d�l�ύX���f��No.125
*/

package webbroker3.admintriggerorder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.admintriggerorder.define.WEB3AdminToDifferentTimeDef;
import webbroker3.admintriggerorder.define.WEB3AdminToTickMatchDivDef;
import webbroker3.admintriggerorder.define.WEB3AdminToTradeStopKeyItemDef;
import webbroker3.admintriggerorder.message.WEB3AdminToOrderRefRefCommonRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToOrderStopStateUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInfoUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopSortKey;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3DetectTypeDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3TargetTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.define.WEB3TriggerOrderStatusDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyRow;
import webbroker3.rlsgateway.data.RlsOrderMissParams;
import webbroker3.rlsgateway.data.RlsOrderMissRow;
import webbroker3.rlsgateway.define.WEB3RlsRequestTypeDef;
import webbroker3.triggerorder.base.data.TriggerOrderStopParams;
import webbroker3.triggerorder.base.data.TriggerOrderStopRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���K�[�����Ǘ��҃f�[�^�}�l�[�W��)<BR>
 * �g���K�[�����Ǘ��҂�DB I/O�A�f�[�^�ϊ��Ȃǂ��Ǘ�����N���X�B<BR>
 *   
 * @@author 杊��]
 * @@version 1.0
 */
public class WEB3AdminToDataManager 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToDataManager.class);
    
    /**
     * @@roseuid 43F1BDF601F4
     */
    public WEB3AdminToDataManager() 
    {
     
    }
    
    /**
     * (is���i���{)<BR>
     * ���X�R�[�h�ꗗ�ɊY�����镔�X�ɂ���<BR>
     * �w�肵�����i�����{���Ă��邩�𔻒肷��B<BR>
     * ���{���Ă���ꍇtrue���A���{���Ă��Ȃ��ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �������̕��X�R�[�h�ꗗ�̂����A<BR>
     * �@@�ꕔ�X�ł����{���Ă���΁A���{�ƂȂ�B<BR>
     * <BR>
     * �P�j�p�����[�^.���X�R�[�h�ꗗ�̗v�f������Loop�����B<BR>
     * <BR>
     * �@@�P�|�P�j�@@�g���A�J�E���g�}�l�[�W��.get���X()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[get���X()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F�@@�����Ώۂ̕��X�R�[�h<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�p�����[�^.���i�敪��"�I�v�V����"�̏ꍇ�A<BR>
     * �@@�@@�����Ώۂ̕��X.�I�v�V�������{�敪��"���{"�̏ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �@@�P�|�R�j�@@�p�����[�^.���i�敪��"�敨"�̏ꍇ�A<BR>
     * �@@�@@�����Ώۂ̕��X.�敨���{�敪��"���{"�̏ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �Q�j�S���X�����i�����{�̏ꍇ�i�P��Loop������break���Ȃ������ꍇ�j�A<BR>
     * �@@�@@false��ԋp����B <BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��<BR>
     * @@param l_strProductType - (���i�敪)<BR>
     * ���i�敪<BR> 
     * <BR>
     * 1�F�@@��������<BR> 
     * 2�F�@@�M�p���<BR> 
     * 3�F�@@�敨<BR> 
     * 4�F�@@�I�v�V����<BR> 
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 43E7228C03DF
     */
    public static boolean isProductExec(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strProductType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isProductExec(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_strBranchCodes == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //�P�j�p�����[�^.���X�R�[�h�ꗗ�̗v�f������Loop�����B
        for (int i = 0; i < l_strBranchCodes.length; i++)
        {
            //�P�|�P�j�@@�g���A�J�E���g�}�l�[�W��.get���X()���\�b�h���R�[������B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();  
            WEB3GentradeBranch l_branch = null;
            
            try
            {
                l_branch = 
                    (WEB3GentradeBranch) l_accountManager.getWeb3GenBranch(l_strInstitutionCode, l_strBranchCodes[i]);
            }
            catch (NotFoundException l_nfe)
            {
                continue;
            }    

            BranchRow l_branchRow = (BranchRow) l_branch.getDataSourceObject();
      
            //�P�|�Q�j�@@�p�����[�^.���i�敪��"�I�v�V����"�̏ꍇ�A
            //�����Ώۂ̕��X.�I�v�V�������{�敪��"���{"�̏ꍇ�Atrue��ԋp����B
            if (WEB3CommodityDivDef.OPTION.equals(l_strProductType))
            {
                if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getOptionDiv()))
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
            
            //�P�|�R�j�@@�p�����[�^.���i�敪��"�敨"�̏ꍇ�A
            //�����Ώۂ̕��X.�敨���{�敪��"���{"�̏ꍇ�Atrue��ԋp����B
            if (WEB3CommodityDivDef.FUTURE.equals(l_strProductType))
            {
                if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getFutureDiv()))
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
        }

        //�Q�j�S���X�����i�����{�̏ꍇ�i�P��Loop������break���Ȃ������ꍇ�j�A
        //false��ԋp����B <BR>
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (get�ڋq�ꗗ)<BR>
     * �����̏����ɊY������ڋq�̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�j ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j DB����<BR>
     * �@@�p�����[�^.���X�R�[�h[]�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j �g���A�J�E���g�}�l�[�W��.get�ڋq()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[get�ڋq()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F�@@�����Ώۂ̕��X�R�[�h<BR>
     * �@@�@@�@@�����R�[�h�F�@@�p�����[�^.�ڋq�R�[�h<BR>
     * <BR>
     * �@@�Q�|�Q�j �@@�Q�|�P�j�̌��ʂ��擾�ł����ꍇ�́A<BR>
     * �@@�@@�@@��������ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * �@@��toArray()�̖߂�l.length��0�̏ꍇ�A<BR>
     * �@@�@@�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01037<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@return WEB3GentradeMainAccount[]
     * @@throws WEB3BaseException
     * @@roseuid 43E83318024E
     */
    public static WEB3GentradeMainAccount[] getAccountList(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccountList(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_strBranchCodes == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //�P�j ArrayList�𐶐�����B
        List l_lisAccounts = new ArrayList();
        
        //�Q�j DB����
        //�p�����[�^.���X�R�[�h[]�̗v�f�����ȉ��̏������J��Ԃ��B
        for (int i = 0; i < l_strBranchCodes.length; i++)
        {
            //�Q�|�P�j �g���A�J�E���g�}�l�[�W��.get�ڋq()���\�b�h���R�[������B
            //      [get�ڋq()�ɃZ�b�g����p�����[�^]
            //      �،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h
            //      ���X�R�[�h�F�@@�����Ώۂ̕��X�R�[�h
            //      �����R�[�h�F�@@�p�����[�^.�ڋq�R�[�h
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountMananger = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
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
            
            //�Q�|�Q�j �@@�Q�|�P�j�̌��ʂ��擾�ł����ꍇ�́A��������ArrayList�ɒǉ�����B
            if (null != l_mainAccount)
            {
                l_lisAccounts.add(l_mainAccount);
            }
        }
        
        //�R�j�@@ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3GentradeMainAccount[] l_mainAccounts = new WEB3GentradeMainAccount[l_lisAccounts.size()];
        l_lisAccounts.toArray(l_mainAccounts);
        
        //��toArray()�̖߂�l.length��0�̏ꍇ�A�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B
        if (0 == l_mainAccounts.length)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_mainAccounts;
    }
    
    /**
     * (create���ʌ�������������)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j�@@��������������C���X�^���X(�FString)�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���X��������������������ɒǉ�����B<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B<BR>
     * <BR>
     * �@@�������������� += "branch_id in (?, ?,,,) "<BR>
     * <BR>
     * �R�j�@@�p�����[�^.���N�G�X�g�f�[�^.����ID��null�̏ꍇ�A<BR>
     * �@@����ID����������������ɒǉ�����B <BR>
     * <BR>
     * �@@�������������� += "and order_id = ? "<BR>
     * <BR>
     * �S�j�@@���������� <BR>
     * �@@�S�|�P�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������null�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̔�������������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and biz_date = ? "<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������null�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̔�������������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and biz_date >= ? "<BR>
     * <BR>
     * �T�j�@@�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h��null�̏ꍇ�A<BR>
     * �@@this.get�ڋq�ꗗ()�̖߂�l�̗v�f����"?"��ǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and account_id in (?, ?,,,) "<BR>
     * <BR>
     * �@@[get�ڋq�ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�@@�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h<BR>
     * �@@�@@�ڋq�R�[�h�F�@@�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h<BR>
     * <BR>
     * �U�j�p�����[�^.�s��R�[�h != null�̏ꍇ�A<BR>
     * �@@�s��ID����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and market_id = ? "<BR>
     * <BR>
     * �V�j�@@�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��null�̏ꍇ�A<BR>
     * �@@this.create���ʌ�������������i�����󋵋敪�j()�̖߂�l��<BR>
     * �@@��������������ɒǉ�����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���N�G�X�g�f�[�^�F�@@�p�����[�^.���N�G�X�g�f�[�^<BR>
     * <BR>
     * �W�j�@@����������ʂ���������������ɒǉ�����B<BR>
     * �@@�@@�������������� += "and order_id in "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(select order_id from oms_con_order_request "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "where request_type = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_type = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and product_type = ? )"<BR>
     * <BR>
     * �X�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������M����From��null�̏ꍇ�A<BR>
     * �@@��������M����From��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and order_id in "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "where hit_tick_timestamp <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@>= to_date(?,'yyyyMMddHH24:MI:SS')) "<BR>
     * <BR>
     * �P�O�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������M����To��null�̏ꍇ�A<BR>
     * �@@��������M����To��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and order_id in "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "where hit_tick_timestamp <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@<= to_date(?,'yyyyMMddHH24:MI:SS')) "<BR>
     * <BR>
     * �P�P�j�@@�p�����[�^.���N�G�X�g�f�[�^.�g���K�[�N������From��null�̏ꍇ�A<BR>
     * �@@�g���K�[�N������From��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and order_id in "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "where rls_hit_timestamp <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@>= to_date(?,'yyyyMMddHH24:MI:SS')) "<BR>
     * <BR>
     * �P�Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.�g���K�[�N������To��null�̏ꍇ�A<BR>
     * �@@�g���K�[�N������To��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and order_id in "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "where rls_hit_timestamp <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@<= to_date(?,'yyyyMMddHH24:MI:SS')) "<BR>
     * <BR>
     * �P�R�j�@@�p�����[�^.���N�G�X�g�f�[�^.������������From��null�̏ꍇ�A<BR>
     * �@@������������From��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and order_id in "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "where order_submit_timestamp <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@>= to_date(?,'yyyyMMddHH24:MI:SS')) "<BR>
     * <BR>
     * �P�S�j�@@�p�����[�^.���N�G�X�g�f�[�^.������������To��null�̏ꍇ�A<BR>
     * �@@������������To��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and order_id in "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "where order_submit_timestamp  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@<= to_date(?,'yyyyMMddHH24:MI:SS')) "<BR>
     * <BR>
     * �P�T�j�@@�p�����[�^.���N�G�X�g�f�[�^.�������ԁ�null�̏ꍇ�A<BR>
     * �p�����[�^.���N�G�X�g�f�[�^�������Ԃ̗v�f�������L�������s���B<BR>
     * <BR>
     * �@@[��������From��"������M����"�A���A<BR>
     * �@@��������To��"�g���K�[�N������"�̏ꍇ]<BR>
     * <BR>
     * �@@�@@�������������� += "and order_id in "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "where round((rls_hit_timestamp <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@- hit_tick_timestamp) * 86400) >= ? ) "<BR>
     * <BR>
     * �@@[��������From��"������M����"�A���A<BR>
     * �@@��������To��"������������"�̏ꍇ]<BR>
     * <BR>
     * �@@�@@�������������� += "and order_id in "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "where round((order_submit_timestamp <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@- hit_tick_timestamp) * 86400) >= ? ) "<BR>
     * <BR>
     * �@@[��������From��"�g���K�[�N������"�A���A<BR>
     * �@@��������To��"������������"�̏ꍇ]<BR>
     * <BR>
     * �@@�@@�������������� += "and order_id in "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "where round((order_submit_timestamp <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@- rls_hit_timestamp) * 86400) >= ? ) "<BR>
     * <BR>
     * �P�U�j�@@�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ�����From��null or<BR>
     * �@@�@@�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ�����To��null or<BR>
     * �@@�@@�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ��敪��null�̏ꍇ�A<BR>
     * �@@���ݒl�ƍ���������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� += "and order_id in "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(select order_id from rls_order_miss "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "where detect_type = ? "<BR>
     * <BR>
     * �@@�@@[�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ�����From��null�̏ꍇ]<BR>
     * �@@�@@�@@���ݒl�ƍ�����From��������������������ɒǉ�����B<BR>
     * �@@�@@�@@�������������� += "and created_timestamp >= to_date(?,'yyyyMMddHH24:MI:SS') "<BR>
     * <BR>
     * �@@�@@[�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ�����To��null�̏ꍇ]<BR>
     * �@@�@@�@@���ݒl�ƍ�����To��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += "and created_timestamp <= to_date(?,'yyyyMMddHH24:MI:SS') "<BR>
     * <BR>
     * �@@�@@[�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ��敪��null ���A<BR>
     * �@@�@@�@@���ݒl�ƍ��敪��"�S�ẴG���["�̏ꍇ]<BR>
     * �@@�@@�@@���ݒl�ƍ��敪����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�������������� += "and miss_type = ? "<BR>
     * <BR>
     * �@@�@@����L���������ǉ���A��������������̖�����")"��ǉ����邱�ƁB<BR>
     * <BR>
     * �P�V�j�@@�쐬�������������������ԋp����B  <BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�����Ɖ�ʃ��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43E826BD0329
     */
    public static String createCommonQueryString(
        String l_strInstitutionCode,
        WEB3AdminToOrderRefRefCommonRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createCommonQueryString(String, WEB3AdminToOrderRefRefCommonRequest)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@��������������C���X�^���X(�FString)�𐶐�����B
        String l_strWhere = new String();

        //�Q�j�@@���X��������������������ɒǉ�����B
        //�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B
        int l_intLength = l_request.branchCode.length;
        for (int i = 0; i < l_intLength; i++)
        {
            if (i == 0)
            {
                l_strWhere += "branch_id in (? ";
            }
            else
            {
                l_strWhere += ", ? ";
            }
        }
        l_strWhere += ") ";

        //�R�j�@@�p�����[�^.���N�G�X�g�f�[�^.����ID��null�̏ꍇ�A����ID����������������ɒǉ�����B
        if (null != l_request.orderId)
        {
            l_strWhere += "and order_id = ? ";
        }

        //�S�j�@@����������
        //�S�|�P�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������null�̏ꍇ�A�ȉ��̔�������������������������ɒǉ�����B
        if (null != l_request.orderBizDate)
        {
            l_strWhere += "and biz_date = ? ";
        }
        //�S�|�Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������null�̏ꍇ�A�ȉ��̔�������������������������ɒǉ�����B
        else
        {
            l_strWhere += "and biz_date >= ? ";
        }

        //�T�j�@@�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h��null�̏ꍇ�A
        //this.get�ڋq�ꗗ()�̖߂�l�̗v�f����"?"��ǉ�����B
        if (null != l_request.accountCode)
        {
            int l_intCodeLength =
                getAccountList(l_strInstitutionCode, l_request.branchCode, l_request.accountCode).length;
            for (int i = 0; i < l_intCodeLength; i++)
            {
                if (i == 0)
                {
                    l_strWhere += "and account_id in (? ";
                }
                else
                {
                    l_strWhere += ", ?";
                }
            }
            l_strWhere += ") ";
        }

        //�U�j�@@�p�����[�^.���N�G�X�g�f�[�^.�s��R�[�h��null�̏ꍇ�A�s��ID����������������ɒǉ�����B
        if (null != l_request.marketCode)
        {
            l_strWhere += "and market_id = ? ";
        }

        //�V�j�@@�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��null�̏ꍇ�A
        //this.create���ʌ�������������i�����󋵋敪�j()�̖߂�l��
        //��������������ɒǉ�����B
        //[����]
        //�@@���N�G�X�g�f�[�^�F�@@�p�����[�^.���N�G�X�g�f�[�^
        if (null != l_request.triggerOrderState)
        {
            l_strWhere += createCommonQueryStringForTriggerOrderState(l_request);
        }

        //�W�j�@@����������ʂ���������������ɒǉ�����B
        l_strWhere += "and order_id in "
                    + "(select order_id from oms_con_order_request "
                    + "where request_type = ? "
                    + "and order_type = ? "
                    + "and product_type = ? ) ";

        //�X�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������M����From��null�̏ꍇ�A
        //��������M����From��������������������ɒǉ�����B
        if (null != l_request.currentPriceInfoAcceptTimeFrom)
        {
            l_strWhere += "and order_id in "
                        + "(select order_id from rls_con_order_hit_notify "
                        + "where hit_tick_timestamp >= to_date(?,'yyyyMMddHH24:MI:SS')) ";
        }

        //�P�O�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������M����To��null�̏ꍇ�A
        //��������M����To��������������������ɒǉ�����B
        if (null != l_request.currentPriceInfoAcceptTimeTo)
        {
            l_strWhere += "and order_id in "
                        + "(select order_id from rls_con_order_hit_notify "
                        + "where hit_tick_timestamp <= to_date(?,'yyyyMMddHH24:MI:SS')) ";
        }

        //�P�P�j�@@�p�����[�^.���N�G�X�g�f�[�^.�g���K�[�N������From��null�̏ꍇ�A
        //�g���K�[�N������From��������������������ɒǉ�����B
        if (null != l_request.triggerStartTimeFrom)
        {
            l_strWhere += "and order_id in "
                        + "(select order_id from rls_con_order_hit_notify "
                        + "where rls_hit_timestamp >= to_date(?,'yyyyMMddHH24:MI:SS')) ";
        }

        //�P�Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.�g���K�[�N������To��null�̏ꍇ�A
        //�g���K�[�N������To��������������������ɒǉ�����B
        if (null != l_request.triggerStartTimeTo)
        {
            l_strWhere += "and order_id in "
                        + "(select order_id from rls_con_order_hit_notify "
                        + "where rls_hit_timestamp <= to_date(?,'yyyyMMddHH24:MI:SS')) ";
        }

        //�P�R�j�@@�p�����[�^.���N�G�X�g�f�[�^.������������From��null�̏ꍇ�A
        //������������From��������������������ɒǉ�����B
        if (null != l_request.orderCompleteTimeFrom)
        {
            l_strWhere += "and order_id in "
                        + "(select order_id from rls_con_order_hit_notify "
                        + "where order_submit_timestamp >= to_date(?,'yyyyMMddHH24:MI:SS')) ";
        }

        //�P�S�j�@@�p�����[�^.���N�G�X�g�f�[�^.������������To��null�̏ꍇ�A
        //������������To��������������������ɒǉ�����B
        if (null != l_request.orderCompleteTimeTo)
        {
            l_strWhere += "and order_id in "
                        + "(select order_id from rls_con_order_hit_notify "
                        + "where order_submit_timestamp <= to_date(?,'yyyyMMddHH24:MI:SS')) ";
        }

        //�P�T�j�@@�p�����[�^.���N�G�X�g�f�[�^.�������ԁ�null�̏ꍇ�A
        //�p�����[�^.���N�G�X�g�f�[�^�������Ԃ̗v�f�������L�������s���B
        if (null != l_request.differentTimeList)
        {
            for (int i = 0; i < l_request.differentTimeList.length; i++)
            {
                //[��������From��"������M����"�A���A
                //��������To��"�g���K�[�N������"�̏ꍇ]
                if (WEB3AdminToDifferentTimeDef.CURRENTPRICE_ACCEPT_TIME.equals(
                    l_request.differentTimeList[i].differentTimeFrom)
                    && WEB3AdminToDifferentTimeDef.TRIGGER_START_TIME.equals(
                    l_request.differentTimeList[i].differentTimeTo))
                {
                    l_strWhere += "and order_id in "
                                + "(select order_id from rls_con_order_hit_notify "
                                + "where round((rls_hit_timestamp - hit_tick_timestamp) * 86400) >= ? ) ";
                }

                //[��������From��"������M����"�A���A��������To��"������������"�̏ꍇ]
                if (WEB3AdminToDifferentTimeDef.CURRENTPRICE_ACCEPT_TIME.equals(
                    l_request.differentTimeList[i].differentTimeFrom)
                    && WEB3AdminToDifferentTimeDef.ORDER_COMPLETE_TIME.equals(
                    l_request.differentTimeList[i].differentTimeTo))
                {
                    l_strWhere += "and order_id in "
                                + "(select order_id from rls_con_order_hit_notify "
                                + "where round((order_submit_timestamp - hit_tick_timestamp) * 86400) >= ? ) ";
                }

                //[��������From��"�g���K�[�N������"�A���A��������To��"������������"�̏ꍇ]
                if (WEB3AdminToDifferentTimeDef.TRIGGER_START_TIME.equals(
                    l_request.differentTimeList[i].differentTimeFrom)
                    && WEB3AdminToDifferentTimeDef.ORDER_COMPLETE_TIME.equals(
                    l_request.differentTimeList[i].differentTimeTo))
                {
                    l_strWhere += "and order_id in "
                                + "(select order_id from rls_con_order_hit_notify "
                                + "where round((order_submit_timestamp - rls_hit_timestamp) * 86400) >= ? ) ";
                }
            }
        }

        //�P�U�j�@@�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ�����From��null�̏ꍇ�A
        //�@@���ݒl�ƍ�����From��������������������ɒǉ�����B
        if (l_request.tickMatchTimeFrom != null
            || l_request.tickMatchTimeTo != null
            || l_request.tickMatchDiv != null)
        {
            l_strWhere += "and order_id in "
                + "(select order_id from rls_order_miss "
                + "where detect_type = ? ";
            
	        //���N�G�X�g�f�[�^.���ݒl�ƍ�����From��null�̏ꍇ�A
            if (l_request.tickMatchTimeFrom != null)
            {
                l_strWhere += "and created_timestamp >= to_date(?,'yyyyMMddHH24:MI:SS') ";
            }
            
	        //���N�G�X�g�f�[�^.���ݒl�ƍ�����To��null�̏ꍇ�A
	        if (l_request.tickMatchTimeTo != null)
	        {
	            l_strWhere += "and created_timestamp <= to_date(?,'yyyyMMddHH24:MI:SS') ";
	        }
	        
            //���N�G�X�g�f�[�^.���ݒl�ƍ�����To��null ����
            // ���ݒl�ƍ��敪��"�S�ẴG���["�̏ꍇ�A
            String l_strTickMatchDiv = l_request.tickMatchDiv;
	        if (l_strTickMatchDiv != null
                && !WEB3AdminToTickMatchDivDef.ALL_ERROR.equals(l_strTickMatchDiv))
            {
                l_strWhere += "and miss_type = ? ";
            }
            
            l_strWhere += ")";
        }

        //�P�X�j�@@�쐬�������������������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strWhere;
    }

    /**
     * (create���ʌ��������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���X�����𐶐�����ArrayList�ɒǉ�����B<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h�̊e�v�f��<BR>
     * �@@�@@�Y�����镔�XID��S��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�����X�R�[�h�ɊY�����镔�XID���擾�ł��Ȃ��ꍇ�� <BR>
     * �@@�@@�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException <BR>
     *   tag: BUSINESS_ERROR_01037 <BR>
     * �R�j�@@�p�����[�^.���N�G�X�g�f�[�^.����ID��null�̏ꍇ�A<BR>
     * �@@����ID�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�j�@@����������<BR>
     * �@@�S�|�P�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������null�̏ꍇ�A<BR>
     * �@@�@@�p�����[�^.���N�G�X�g�f�[�^.�������𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������null�̏ꍇ�A<BR>
     * �@@�@@�Ɩ����t(*1)�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �T�j�@@�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h��null�̏ꍇ�A<BR>
     * �@@����ID�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@������ID�́Athis.get�ڋq�ꗗ()�ɂĎ擾�����S�Ă̌ڋq.����ID���Z�b�g<BR>
     * <BR>
     * �@@[get�ڋq�ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�@@�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h<BR>
     * �@@�@@�ڋq�R�[�h�F�@@�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h<BR>
     * <BR>
     * �U�j�p�����[�^.���N�G�X�g�f�[�^.�s��R�[�h != null�̏ꍇ�A<BR>
     * �@@�s��ID�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@���s��ID�́A�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɂ�<BR>
     * �@@�@@�擾�����s��.�s��ID���Z�b�g�B  <BR>
     * <BR>
     * �@@�@@[get�s��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�p�����[�^.���N�G�X�g�f�[�^.�s��R�[�h<BR>
     * <BR>
     * �V�j�@@�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��null�̏ꍇ�A<BR>
     * �@@this.create���ʌ��������f�[�^�R���e�i�i�����󋵋敪�j()�̖߂�l��<BR>
     * �@@String�z��̊e�v�f�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���N�G�X�g�f�[�^�F�@@�p�����[�^.���N�G�X�g�f�[�^<BR>
     * <BR>
     * �W�j�@@����������ʂ���������������ɒǉ�����B<BR>
     * �@@����𔻕ʂ�������𐶐�����ArrayList�ɒǉ�����B<BR>
     * �@@�@@�E2�i�������o�^�j<BR>
     * �@@�@@�E�p�����[�^.���N�G�X�g�f�[�^.�����������<BR>
     * �@@�@@�E�p�����[�^.�����^�C�v<BR>
     * <BR>
     * �X�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������M����From��null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.��������M����From�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �P�O�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������M����To��null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.��������M����To�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �P�P�j�@@�p�����[�^.���N�G�X�g�f�[�^.�g���K�[�N������From��null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.�g���K�[�N������From�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �P�Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.�g���K�[�N������To��null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.�g���K�[�N������To�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �P�R�j�@@�p�����[�^.���N�G�X�g�f�[�^.������������From��null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.������������From�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �P�S�j�@@�p�����[�^.���N�G�X�g�f�[�^.������������To��null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���N�G�X�g�f�[�^.������������To�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �P�T�j�@@�p�����[�^.���N�G�X�g�f�[�^.�������ԁ�null�̏ꍇ�A<BR>
     * �p�����[�^.���N�G�X�g�f�[�^�������Ԃ̊e�v�f�̘������Ԃ�<BR>
     * ��������ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �P�U�j�@@�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ�����From��null or<BR>
     * �@@�@@�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ�����To��null or<BR>
     * �@@�@@�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ��敪��null�̏ꍇ�A<BR>
     * �@@�@@�ȉ��𐶐�����ArrayList�ɒǉ�����B<BR>
     * �@@�@@�E"�I�����C��"�i���m�敪�j<BR>
     * <BR>
     * �@@�@@[�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ�����From��null�̏ꍇ]<BR>
     * �@@�@@�@@�E�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ�����From<BR>
     * <BR>
     * �@@�@@[�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ�����To��null�̏ꍇ]<BR>
     * �@@�@@�@@�E�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ�����To<BR>
     * <BR>
     * �@@�@@[�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ��敪��null ���A<BR>
     * �@@�@@�@@���ݒl�ƍ��敪��"�S�ẴG���["�̏ꍇ]<BR>
     * �@@�@@�@@�E�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ��敪<BR>
     * <BR>
     * �P�V�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�����Ɖ�ʃ��N�G�X�g�I�u�W�F�N�g<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 43E884A5018B
     */
    public static String[] createCommonQueryDataContainer(
        String l_strInstitutionCode,
        WEB3AdminToOrderRefRefCommonRequest l_request,
        ProductTypeEnum l_productType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createCommonQueryDataContainer("
            + "String, WEB3AdminToOrderRefRefCommonRequest, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@ArrayList�𐶐�����B
        List l_lisQueryContainers = new ArrayList();

        //�Q�j�@@���X�����𐶐�����ArrayList�ɒǉ�����B�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h�̊e�v�f��
        //�Y�����镔�XID��S��ArrayList�ɒǉ�����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeBranch l_branch = null;
        int l_intCodeLength = l_request.branchCode.length;
        for (int i = 0; i < l_intCodeLength; i++)
        {
            try
            {
                l_branch =
                    (WEB3GentradeBranch)l_accountManager.getBranch(
                        l_accountManager.getInstitution(l_strInstitutionCode), l_request.branchCode[i]);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }

            l_lisQueryContainers.add(String.valueOf(l_branch.getBranchId()));
        }

        //�R�j�@@�p�����[�^.���N�G�X�g�f�[�^.����ID��null�̏ꍇ�A
        //����ID�𐶐�����ArrayList�ɒǉ�����B
        if (null != l_request.orderId)
        {
            l_lisQueryContainers.add(l_request.orderId);
        }

        //�S�j�@@����������
        // �@@�S�|�P�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������null�̏ꍇ�A
        //   �p�����[�^.���N�G�X�g�f�[�^.�������𐶐�����ArrayList�ɒǉ�����B
        if (null != l_request.orderBizDate)
        {
            l_lisQueryContainers.add(WEB3DateUtility.formatDate(l_request.orderBizDate, "yyyyMMdd"));
        }

        //   �S�|�Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������null�̏ꍇ�A
        // �@@�@@�Ɩ����t(*1)�𐶐�����ArrayList�ɒǉ�����B
        //     (*1)GtlUtils.getTradingSystem().getBizDate()
        if (null == l_request.orderBizDate)
        {
            l_lisQueryContainers.add(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd"));
        }

        //�T�j�@@�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h��null�̏ꍇ�A
        // �@@����ID�𐶐�����ArrayList�ɒǉ�����B
        if (null != l_request.accountCode)
        {
            WEB3GentradeMainAccount[] l_mainAccount =
                getAccountList(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
            int l_intLength = l_mainAccount.length;
            for (int i = 0; i < l_intLength; i++)
            {
                l_lisQueryContainers.add(Long.toString(l_mainAccount[i].getAccountId()));
            }
        }

        //�U�j�@@�p�����[�^.���N�G�X�g�f�[�^.�s��R�[�h��null�̏ꍇ�A
        // �@@�s��ID�𐶐�����ArrayList�ɒǉ�����B
        if (null != l_request.marketCode)
        {
            WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            Market l_market = null;

            try
            {
                //throw NotFoundException
                l_market = l_gentradeFinObjectManager.getMarket(l_strInstitutionCode, l_request.marketCode);
            }
            catch (NotFoundException l_nfex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_nfex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                l_nfex.getMessage(),
                l_nfex);
            }

            l_lisQueryContainers.add(Long.toString(l_market.getMarketId()));
        }

        //�V�j�@@�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��null�̏ꍇ�A
        //this.create���ʌ��������f�[�^�R���e�i�i�����󋵋敪�j()�̖߂�l��
        //String�z��̊e�v�f�𐶐�����ArrayList�ɒǉ�����B
        //[����]
        //�@@���N�G�X�g�f�[�^�F�@@�p�����[�^.���N�G�X�g�f�[�^
        if (null != l_request.triggerOrderState)
        {
            String[] l_strOrderStates =
                createCommonQueryDataContainerForTriggerOrderState(l_request);
            int l_intLength = l_strOrderStates.length;
            for (int i = 0; i < l_intLength; i++)
            {
                l_lisQueryContainers.add(l_strOrderStates[i]);
            }
        }

        // �W�j�@@����������ʂ���������������ɒǉ�����B
        // �@@����𔻕ʂ�������𐶐�����ArrayList�ɒǉ�����B
        // �@@�@@�E2�i�������o�^�j
        // �@@�@@�E�p�����[�^.���N�G�X�g�f�[�^.�����������
        // �@@�@@�E�p�����[�^.�����^�C�v
        l_lisQueryContainers.add(String.valueOf(WEB3RlsRequestTypeDef.NEW));
        l_lisQueryContainers.add(l_request.triggerOrderType);
        l_lisQueryContainers.add(String.valueOf(l_productType.intValue()));

        // �X�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������M����From��null�̏ꍇ�A
        // �@@�p�����[�^.���N�G�X�g�f�[�^.��������M����From�𐶐�����ArrayList�ɒǉ�����B
        if (null != l_request.currentPriceInfoAcceptTimeFrom)
        {
            l_lisQueryContainers.add(l_request.currentPriceInfoAcceptTimeFrom);
        }

        // �P�O�j�@@�p�����[�^.���N�G�X�g�f�[�^.��������M����To��null�̏ꍇ�A
        // �@@�p�����[�^.���N�G�X�g�f�[�^.��������M����To�𐶐�����ArrayList�ɒǉ�����B
        if (null != l_request.currentPriceInfoAcceptTimeTo)
        {
            l_lisQueryContainers.add(l_request.currentPriceInfoAcceptTimeTo);
        }

        // �P�P�j�@@�p�����[�^.���N�G�X�g�f�[�^.�g���K�[�N������From��null�̏ꍇ�A
        // �@@�p�����[�^.���N�G�X�g�f�[�^.�g���K�[�N������From�𐶐�����ArrayList�ɒǉ�����B
        if (null != l_request.triggerStartTimeFrom)
        {
            l_lisQueryContainers.add(l_request.triggerStartTimeFrom);
        }

        // �P�Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.�g���K�[�N������To��null�̏ꍇ�A
        // �@@�p�����[�^.���N�G�X�g�f�[�^.�g���K�[�N������To�𐶐�����ArrayList�ɒǉ�����B
        if (null != l_request.triggerStartTimeTo)
        {
            l_lisQueryContainers.add(l_request.triggerStartTimeTo);
        }

        // �P�R�j�@@�p�����[�^.���N�G�X�g�f�[�^.������������From��null�̏ꍇ�A
        // �@@�p�����[�^.���N�G�X�g�f�[�^.������������From�𐶐�����ArrayList�ɒǉ�����B
        if (null != l_request.orderCompleteTimeFrom)
        {
            l_lisQueryContainers.add(l_request.orderCompleteTimeFrom);
        }

        // �P�S�j�@@�p�����[�^.���N�G�X�g�f�[�^.������������To��null�̏ꍇ�A
        // �@@�p�����[�^.���N�G�X�g�f�[�^.������������To�𐶐�����ArrayList�ɒǉ�����B
        if (null != l_request.orderCompleteTimeTo)
        {
            l_lisQueryContainers.add(l_request.orderCompleteTimeTo);
        }

        // �P�T�j�@@�p�����[�^.���N�G�X�g�f�[�^.�������ԁ�null�̏ꍇ�A
        // �p�����[�^.���N�G�X�g�f�[�^�������Ԃ̊e�v�f�̘������Ԃ�
        // ��������ArrayList�ɒǉ�����B
        if (null != l_request.differentTimeList)
        {
            for (int i = 0; i < l_request.differentTimeList.length; i++)
            {
                l_lisQueryContainers.add(l_request.differentTimeList[i].differentTime);
            }
        }

        //�P�U�j�@@�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ�����From��null or
        //    �p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ�����To��null or
        //    �p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ��敪��null�̏ꍇ�A
        //    �ȉ��𐶐�����ArrayList�ɒǉ�����B
        String l_strTickMatchDiv = l_request.tickMatchDiv;
        if (l_request.tickMatchTimeFrom != null
            || l_request.tickMatchTimeTo != null
            || l_strTickMatchDiv != null)
        {
            //�@@�@@�E"�I�����C��"�i���m�敪�j
            l_lisQueryContainers.add(WEB3DetectTypeDef.ON_LINE);
            
            //���N�G�X�g�f�[�^.���ݒl�ƍ�����From��null�̏ꍇ�A
            if (l_request.tickMatchTimeFrom != null)
            {
	            l_lisQueryContainers.add(l_request.tickMatchTimeFrom);
            }
            
            //���N�G�X�g�f�[�^.���ݒl�ƍ�����To��null ����
            // ���ݒl�ƍ��敪��"�S�ẴG���["�̏ꍇ�A
            if (l_request.tickMatchTimeTo != null)
            {
	            l_lisQueryContainers.add(l_request.tickMatchTimeTo);
            }
            
            if (l_strTickMatchDiv != null
                && !WEB3AdminToTickMatchDivDef.ALL_ERROR.equals(l_strTickMatchDiv))
            {
                l_lisQueryContainers.add(l_strTickMatchDiv);
            }
        }

        //�P�X�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B
        String[] l_strQueryContainers = new String[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_strQueryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
    }

    /**
     * (get���[���G���W������̒ʒm)<BR>
     * �����̏����ɊY�����郋�[���G���W������̒ʒmParams��ԋp����B<BR> 
     * <BR>
     * �P�j�@@���[���G���W������̒ʒm�e�[�u��(rls_con_order_hit_notify)��<BR>
     * �@@�@@�@@�����̏����Ō����������ʂ�ԋp����B<BR>
     * <BR>
     * �@@[��������]<BR> 
     * �@@�@@����ID�F�@@�p�����[�^.�����P��.����ID<BR>
     * �@@�@@�⏕����ID�F�@@�p�����[�^.�����P��.�⏕����ID<BR>
     * �@@�@@�����t�������^�C�v�F�@@�p�����[�^.�����������<BR>
     * �@@�@@����ID�F�@@�p�����[�^.�����P��.����ID<BR>
     * �@@�@@���������^�C�v�F�@@�p�����[�^.�����^�C�v<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * �@@���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR> 
     * �@@���������ʂ̃��R�[�h���������̏ꍇ�́A�f�[�^�s�����̗�O���X���[����B<BR>
     * class: WEB3SystemLayerException<BR>
     * tag  : SYSTEM_ERROR_80006<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_strTriggerOrderType - (�����������)<BR>
     * �����������<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * @@return RlsConOrderHitNotifyParams
     * @@throws WEB3BaseException
     * @@roseuid 43E9B694028B
     */
    public static RlsConOrderHitNotifyParams getRlsConOrderHitNotify(
        OrderUnit l_orderUnit,
        String l_strTriggerOrderType,
        ProductTypeEnum l_productType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getRlsConOrderHitNotify(OrderUnit, String, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���[���G���W������̒ʒm�e�[�u��(rls_con_order_hit_notify)�������̏����Ō����������ʂ�ԋp����B
        // �@@[��������]
        // �@@�@@����ID�F�@@�p�����[�^.�����P��.����ID
        // �@@�@@�⏕����ID�F�@@�p�����[�^.�����P��.�⏕����ID
        // �@@�@@�����t�������^�C�v�F�@@�p�����[�^.�����������
        //   �@@����ID�F�@@�p�����[�^.�����P��.����ID
        // �@@�@@���������^�C�v�F�@@�p�����[�^.�����^�C�v
        String l_strWhere = " account_id = ? ";
        l_strWhere += " and sub_account_id = ? ";
        l_strWhere += " and order_type = ? ";
        l_strWhere += " and order_id = ? ";
        l_strWhere += " and product_type = ? ";
        
        Object[] l_objValue = 
            new Object[]{
                String.valueOf(l_orderUnit.getAccountId()),
                String.valueOf(l_orderUnit.getSubAccountId()),
                l_strTriggerOrderType,
                String.valueOf(l_orderUnit.getOrderId()),
                String.valueOf(l_productType.intValue())}; 
        
        List l_lisRecords = new ArrayList();
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                RlsConOrderHitNotifyRow.TYPE,
                l_strWhere,
                null,
                l_objValue);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�Q�j�@@�������ʂ�ԋp����B
        //���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if (null == l_lisRecords || 0 == l_lisRecords.size())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //���������ʂ̃��R�[�h���������̏ꍇ�́A�f�[�^�s�����̗�O���X���[����B
        if (l_lisRecords.size() > 1)
        {
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }
        
        RlsConOrderHitNotifyParams[] l_params = new RlsConOrderHitNotifyParams[l_lisRecords.size()];
        l_lisRecords.toArray(l_params);
        
        log.exiting(STR_METHOD_NAME);
        return l_params[0];
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
     * �@@�@@�@@class: WEB3SystemLayerException<BR>
     * �@@�@@�@@tag  : SYSTEM_ERROR_80005<BR>
     * <BR>
     * [�p�����[�^.�����^�C�v == "����"�̏ꍇ]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h = �p�����[�^.���XID�ɊY�����镔�X�R�[�h<BR>
     * �@@�@@�s��R�[�h = �p�����[�^.�s��ID�ɊY������s��R�[�h<BR>
     * �@@�@@�����R�[�h = �p�����[�^.����ID�ɊY�����銔������.�����R�[�h<BR>
     * �@@�@@��t���ԋ敪 = �p�����[�^.��t���ԋ敪<BR>
     * <BR>
     * �@@[�p�����[�^.�����^�C�v == "�敨�I�v�V����"�̏ꍇ<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h = �p�����[�^.���XID�ɊY�����镔�X�R�[�h<BR>
     * �@@�@@�s��R�[�h = "DEFAULT"<BR>
     * �@@�@@�����R�[�h = �p�����[�^.����ID�ɊY������敨OP����.�����Y�����R�[�h<BR>
     * �@@�@@��t���ԋ敪 = �p�����[�^.��t���ԋ敪<BR>
     * <BR>
     * �R�j�@@����J�����_�R���e�L�X�g���ăZ�b�g����B<BR>
     * �@@ThreadLocalSystemAttributesRegistry.setAttribute()���R�[������B<BR>
     * <BR>
     * �@@[setAttribute()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * �@@�@@arg1�F�@@�v���p�e�B�Z�b�g��������J�����_�R���e�L�X�g<BR>
     * <BR>
     * �S�j�@@��t�����A���t���[���̃��Z�b�g<BR>
     * �@@������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * @@param l_branchID - (���XID)<BR>
     * ���XID<BR>
     * @@param l_marketID - (�s��ID)<BR>
     * �s��ID<BR>
     * @@param l_productID - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strTradetimeType - (��t���ԋ敪)<BR>
     * ��t���ԋ敪<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43E9B694028B
     */
    public static void resetTradingCalContext(
        String l_strInstitutionCode,
        ProductTypeEnum l_productType,
        Long l_branchID,
        Long l_marketID,
        Long l_productID,
        String l_strTradetimeType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " resetTradingCalContext(String, ProductTypeEnum, Long, Long, Long, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@����J�����_�R���e�L�X�g���擾����B
        //ThreadLocalSystemAttributesRegistry.getAttribute()���R�[������B
        //���擾�ł��Ȃ������ꍇ�́A����J�����_�R���e�L�X�g�𐶐����A
        //�@@�ȍ~�̏����Ŏg�p���邱�ƁB
        Object l_object = ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        WEB3GentradeTradingClendarContext l_context = null;
        if (l_object != null)
        {
            l_context = (WEB3GentradeTradingClendarContext) l_object;
        }
        else
        {
            l_context = new WEB3GentradeTradingClendarContext();
        }

        //���Ή�����p�����[�^��null�̏ꍇ�́A�ăZ�b�g���s��Ȃ��B
        String l_strInstitutionCodeForSet = l_context.getInstitutionCode();
        String l_strBranchCodeForSet = l_context.getBranchCode();
        String l_strMarketCodeForSet = l_context.getMarketCode();
        String l_strProductCodeForSet = l_context.getProductCode();
        String l_strTradetimeDivForSet = l_context.getTradingTimeType();

        if (l_strInstitutionCode != null)
        {
            l_strInstitutionCodeForSet = l_strInstitutionCode;
        }

        if (l_strTradetimeType != null)
        {
            l_strTradetimeDivForSet = l_strTradetimeType;
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        if (l_branchID != null)
        {
            try
            {
                Branch l_branch = l_accountManager.getBranch(l_branchID.longValue());
                l_strBranchCodeForSet = l_branch.getBranchCode();
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminToDataManager." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
        }

        if (l_marketID != null)
        {
            try
            {
                WEB3GentradeFinObjectManager l_finObjManager =
                    (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket) l_finObjManager.getMarket(l_marketID.longValue());
                l_strMarketCodeForSet = l_market.getMarketCode();
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminToDataManager." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
        }

        if (l_productID != null)
        {
            if (ProductTypeEnum.IFO.equals(l_productType))
            {
                IfoProductRow l_ifoProductRow = null;
                try
                {
                    l_ifoProductRow = IfoProductDao.findRowByPk(l_productID.longValue());
                }
                catch (DataFindException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        "WEB3AdminToDataManager." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3AdminToDataManager." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3AdminToDataManager." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                l_strProductCodeForSet = l_ifoProductRow.getUnderlyingProductCode();
            }
        }

        //�Q�j�@@�擾��������J�����_�R���e�L�X�g�Ɉȉ��̃v���p�e�B���Z�b�g����B
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
            l_context.setInstitutionCode(l_strInstitutionCodeForSet);
            //���X�R�[�h = �p�����[�^.���XID�ɊY�����镔�X�R�[�h
            l_context.setBranchCode(l_strBranchCodeForSet);
            //�s��R�[�h = �p�����[�^.�s��ID�ɊY������s��R�[�h
            l_context.setMarketCode(l_strMarketCodeForSet);
            //�����R�[�h = "DEFAULT"
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //��t���ԋ敪 = �p�����[�^.��t���ԋ敪
            l_context.setTradingTimeType(l_strTradetimeDivForSet);
        }
        else if (ProductTypeEnum.IFO.equals(l_productType))
        {
            //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
            l_context.setInstitutionCode(l_strInstitutionCodeForSet);
            //���X�R�[�h = �p�����[�^.���XID�ɊY�����镔�X�R�[�h
            l_context.setBranchCode(l_strBranchCodeForSet);
            //�s��R�[�h = "DEFAULT"
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //�����R�[�h = �p�����[�^.����ID�ɊY������敨OP����.�����Y�����R�[�h
            l_context.setProductCode(l_strProductCodeForSet);
            //��t���ԋ敪 = �p�����[�^.��t���ԋ敪
            l_context.setTradingTimeType(l_strTradetimeDivForSet);
        }

        //�R�j�@@����J�����_�R���e�L�X�g���ăZ�b�g����B
        //ThreadLocalSystemAttributesRegistry.setAttribute()���R�[������B
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        //�S�j�@@��t�����A���t���[���̃��Z�b�g
        //������ԊǗ�.setTimestamp()���R�[������B
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
     * �Q�j���ʏ������쐬����B<BR>
     * �@@�Q�|�P�j�@@ID���ڎw�莞�i�p�����[�^.����ID != null�j�̏ꍇ�A<BR>
     * �@@�@@����ID���������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�������������� = "order_id = ?"<BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.����ID��ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@��������肷����������������ɒǉ�����B<BR>
     * �@@�@@�Q�|�Q�|�P�j�@@���X���������������ɒǉ�����B<BR>
     * �@@�@@�@@�p�����[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B <BR>
     * <BR>
     * �@@�@@�@@�������������� = "branch_id in (?, ?,,,) "<BR>
     * �@@�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���X�R�[�h�ɊY�����镔�X.���XID��<BR>
     * �@@�@@�@@�v�f�����Aadd����B<BR>
     * <BR>
     * �@@�@@�@@�����X���擾����ۂɁA�p�����[�^.�،���Ђ��g�p����B<BR>
     * �@@�@@�@@���擾���ɗ�O�ƂȂ����ꍇ�A�u�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
     * �@@�@@�@@�@@�̋Ɩ��G���[���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag  : BUSINESS_ERROR_01037<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j�@@�������������������ɒǉ�����B<BR>
     * �@@�@@�@@�������������� += " and order_open_status = ?"<BR>
     * �@@�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     * �@@�@@�@@�@@�E"�I�[�v��"<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�R�j�@@����������ʏ��������������ɒǉ�����B<BR>
     * �@@�@@�@@�Q�|�Q�|�R�|�P�j�@@�ȉ��A�p�����[�^.����������ʈꗗ�̗v�f�����A<BR>
     * �@@�@@�@@�@@Loop���A����������ʏ������쐬����B <BR>
     * �@@�@@�@@�@@�@@�@@�Q�|�Q�|�R�|�P�|�P�j�@@�����Ώۂ̗v�f��"�t�w�l"�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@[����������ʏ��� == ""�̏ꍇ] <BR>
     * �@@�@@�@@�@@�@@�@@�@@����������ʏ��� = " (order_condition_type = ?)"<BR>
     * <BR>
     *  �@@�@@�@@�@@�@@�@@[��L�ȊO�̏ꍇ]<BR>
     *  �@@�@@�@@�@@�@@�@@����������ʏ��� += " or (order_condition_type = ?)"<BR>
     * <BR>
     *   �@@�@@�@@�@@�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     *   �@@�@@�@@�@@�@@�@@�E"�t�w�l" <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�Q�|�Q�|�R�|�P�|�Q�j�@@�����Ώۂ̗v�f��"W�w�l"�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@[����������ʏ��� == ""�̏ꍇ]<BR>
     *  �@@�@@�@@�@@�@@�@@ ����������ʏ��� = " (order_condition_type = ?" <BR>
     *  �@@�@@�@@�@@�@@�@@�@@+ " and request_type <> ?)"<BR>
     * <BR>
     *  �@@�@@�@@�@@�@@�@@[��L�ȊO�̏ꍇ]<BR>
     *   �@@�@@�@@�@@�@@�@@����������ʏ��� += " or (order_condition_type = ?"<BR>
     *    �@@�@@�@@�@@�@@�@@+ " and request_type <> ?)"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     *  �@@�@@�@@�@@�@@�@@�@@�E"W�w�l"<BR>
     *   �@@�@@�@@�@@�@@�@@ �E"�����T�[�o" <BR>
     * <BR>
     * �@@�@@�@@�Q�|�Q�|�R�|�Q�j�@@�쐬��������������ʏ�����<BR>
     * �@@�@@�@@�@@��������������ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�@@�@@�������������� += " and (" + ����������ʏ��� + ")" <BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�S�j�@@�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A<BR>
     * �@@�@@�@@�ڋq���������������ɒǉ�����B<BR>
     * �@@�@@�@@�Q�|�Q�|�S�|�P�j�@@�g���K�[�����Ǘ��҃f�[�^�}�l�[�W��.get�ڋq�ꗗ()��<BR>
     * �@@�@@�@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[get�ڋq�ꗗ()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@���X�R�[�h�F�@@�p�����[�^.���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�ڋq�R�[�h�F�@@�p�����[�^.�ڋq�R�[�h<BR>
     * <BR>
     * �@@�@@�@@�Q�|�Q�|�S�|�Q�j�@@�Q�|�Q�|�S�|�P�j�̖߂�l�̗v�f�����A����������"?"���A<BR>
     * �@@�@@�@@�@@�f�[�^�R���e�i�ɁA�e�v�f�̌���ID��ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�������������� += " and account_id in (?, ?,,,) "<BR>
     * �@@�@@�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     * �@@�@@�@@�@@�@@�E�Q�|�Q�|�S�|�P�j�̖߂�l�̊e�v�f.����ID<BR>
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
     * �R�j�@@�߂�l���i�[����ArrayList�𐶐�����B<BR>
     * <BR>
     * �S�j�@@�p�����[�^.���i�敪�ꗗ��"��������" or "�M�p���"���܂܂��ꍇ�A<BR>
     * �@@�@@�ȉ��̏������s���B<BR>
     * �@@�S�|�P�j�@@ID���ڎw�莞�łȂ��i�p�����[�^.����ID == null�j�̏ꍇ�A<BR>
     * �@@�@@Eqtype���L�̌����������쐬����B<BR>
     * <BR>
     * �@@�@@�S�|�P�|�P�j�@@�p�����[�^.����ID == null�̏ꍇ�A<BR>
     * �@@�@@[�p�����[�^.���i�敪�ꗗ��"��������"���܂܂��ꍇ]<BR>
     * �@@�@@�@@Eqtype�������������� = " (order_type in (?, ?)"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ " and sonar_traded_code <> ?)"<BR>
     * �@@�@@�@@Eqtype�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     * �@@�@@�@@�@@�E"����������"<BR>
     * �@@�@@�@@�@@�E"����������"<BR>
     * �@@�@@�@@�@@�E"����O����"<BR>
     * <BR>
     * �@@�@@[�p�����[�^.���i�敪�ꗗ��"�M�p���"���܂܂��ꍇ]<BR>
     * �@@�@@�@@[Eqtype�������������񂪋󕶎��̏ꍇ]<BR>
     * �@@�@@�@@�@@Eqtype�������������� = " (order_categ in (?, ?))"<BR>
     * �@@�@@�@@[��L�ȊO]<BR>
     * �@@�@@�@@�@@Eqtype�������������� += " or order_categ in (?, ?)"<BR>
     * <BR>
     * �@@�@@�@@Eqtype�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     * �@@�@@�@@�@@�E"�M�p�V�K������"<BR>
     * �@@�@@�@@�@@�E"�M�p�ԍϒ���"<BR>
     * <BR>
     * �@@�@@�S�|�P�|�Q�j�@@Eqtype��������������ɉ��Z�q��ǉ�����B<BR>
     * �@@�@@�@@Eqtype�������������� = " and (" + Eqtype�������������� + ")"<BR>
     * <BR>
     * �@@�@@�S�|�P�|�R�j�@@�s�������Eqtype��������������ɒǉ�����B<BR>
     * �@@�@@�@@�p�����[�^.�s��R�[�h�ꗗ�̗v�f����"?"��ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@Eqtype�������������� = " and market_id in (?, ?,,,) "<BR>
     * �@@�@@�@@Eqtype�f�[�^�R���e�i�Ƀp�����[�^.�s��R�[�h�ꗗ�ɊY������s��.�s��ID��<BR>
     * �@@�@@�@@�v�f�����Aadd����B<BR>
     * <BR>
     * �@@�@@�@@���擾���ɗ�O�ƂȂ����ꍇ�A�u�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
     * �@@�@@�@@�̋Ɩ��G���[���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag  : BUSINESS_ERROR_01037<BR>
     * <BR>
     * �@@�@@�S�|�P�|�S�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@�@@�@@����������Eqtype��������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@Eqtype�������������� = " and product_id = ?"<BR>
     * �@@�@@�@@Eqtype�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     * �@@�@@�@@�@@�E�p�����[�^.�����R�[�h�ɊY�����銔������.����ID<BR>
     * <BR>
     * �@@�@@�@@���擾���ɗ�O�ƂȂ����ꍇ�A�u�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
     * �@@�@@�@@�̋Ɩ��G���[���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag  : BUSINESS_ERROR_01037<BR>
     * <BR>
     * �@@�S�|�Q�j�@@DB����������B<BR>
     * �@@�@@�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@arg0�F�@@���������P��Row.TYPE <BR>
     * �@@�@@�@@�@@arg1�F�@@�쐬������������������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@+ �쐬����Eqtype��������������<BR>
     * �@@�@@�@@�@@arg2�F�@@�쐬�����f�[�^�R���e�i.toArray()��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�쐬����Eqtype�f�[�^�R���e�i.toArray()��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�߂�l��v�f�Ƃ���z��<BR>
     * �@@�@@�@@�@@��Eqtype���������^�f�[�^�R���e�i���쐬���Ă��Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���������ɒǉ����Ȃ��B<BR>
     * <BR>
     * �@@�S�|�R�j�@@�S�|�Q�j�̖߂�l�̊e�v�f���犔�������P�ʂ��쐬���A<BR>
     * �@@�@@�߂�l���i�[����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�����������P�ʂ́A�g�����������}�l�[�W��.toOrderUnit()�ɂ�<BR>
     * �@@�@@�@@�쐬����B<BR>
     * <BR>
     * �T�j�@@�p�����[�^.���i�敪�ꗗ��"�敨" or "�I�v�V����"���܂܂��ꍇ�A<BR>
     * �@@�ȉ��̏������s���B<BR>
     * �@@�T�|�P�j�@@ID���ڎw�莞�łȂ��i�p�����[�^.����ID == null�j�̏ꍇ�A<BR>
     * �@@�@@Ifo���L�̌����������쐬����B<BR>
     * <BR>
     * �@@�@@[�p�����[�^.���i�敪�ꗗ��"�敨"���܂܂��ꍇ]<BR>
     * �@@�@@�@@Ifo�������������� = " future_option_div = ?"<BR>
     * �@@�@@�@@Ifo�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     * �@@�@@�@@�@@�E"�敨"<BR>
     * <BR>
     * �@@�@@[�p�����[�^.���i�敪�ꗗ��"�I�v�V����"���܂܂��ꍇ]<BR>
     * �@@�@@�@@[Ifo�������������񂪋󕶎��̏ꍇ]<BR>
     * �@@�@@�@@�@@Ifo�������������� = " future_option_div = ?"<BR>
     * �@@�@@�@@[��L�ȊO]<BR>
     * �@@�@@�@@�@@Ifo�������������� += " or future_option_div = ?"<BR>
     * <BR>
     * �@@�@@�@@Ifo�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j<BR>
     * �@@�@@�@@�@@�E"�I�v�V����"<BR>
     * <BR>
     * �@@�@@�T�|�P�|�P�j�@@Ifo��������������ɉ��Z�q��ǉ�����B<BR>
     * �@@�@@�@@Ifo�������������� = " and (" + Ifo�������������� + ")"<BR>
     * <BR>
     * �@@�T�|�Q�j�@@DB����������B<BR>
     * �@@�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@�敨OP�����P��Row.TYPE <BR>
     * �@@�@@�@@arg1�F�@@�쐬������������������<BR>
     * �@@�@@�@@�@@�@@�@@�@@+ �쐬����Ifo��������������<BR>
     * �@@�@@�@@arg2�F�@@�쐬�����f�[�^�R���e�i.toArray()��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�쐬����Ifo�f�[�^�R���e�i.toArray()��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�߂�l��v�f�Ƃ���z��<BR>
     * �@@�@@�@@��Ifo���������^�f�[�^�R���e�i���쐬���Ă��Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@���������ɒǉ����Ȃ��B<BR>
     * <BR>
     * �@@�T�|�R�j�@@�T�|�Q�j�̖߂�l�̊e�v�f����敨OP�����P�ʂ��쐬���A<BR>
     * �@@�@@�߂�l���i�[����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@���敨OP�����P�ʂ́AOP�����}�l�[�W��.toOrderUnit()�ɂ�<BR>
     * �@@�@@�@@�쐬����B<BR>
     * <BR>
     * �U�j�@@�߂�l���i�[����ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * �@@�@@���߂�l���i�[����ArrayList.size() == 0�̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * @@param l_orderID - (����ID)<BR>
     * ��ID���ڎw�莞�̂݃Z�b�g<BR>
     * @@param l_institution - (�،����)<BR>
     * �،����<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��<BR>
     * @@param l_strTriggerOrderTypes - (����������ʈꗗ)<BR>
     * ����������ʈꗗ<BR>
     * @@param l_strProductTypes - (���i�敪�ꗗ)<BR>
     * ���i�敪�ꗗ<BR>
     * @@param l_strMarketCodes - (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h�̔z��<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_accountIDFrom - (����IDFrom)<BR>
     * ����IDFrom<BR>
     * @@param l_accountIDTo - (����IDTo)<BR>
     * ����IDTo<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43E9B694028B
     * @@return OrderUnit[]
     */
    public static OrderUnit[] getManualExpireOrderUnits(
        Long l_orderID,
        Institution l_institution,
        String[] l_strBranchCodes,
        String[] l_strTriggerOrderTypes,
        String[] l_strCommodityDivs,
        String[] l_strMarketCodes,
        String l_strProductCode,
        String l_strAccountCode,
        Long l_accountIDFrom,
        Long l_accountIDTo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getManualExpireOrderUnits("
            + "Long, Institution, String[], "
            + "String[], String[], String[], "
            + "String, String, Long, Long)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminToDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@��������������i�FString�j�A
        //���������f�[�^�R���e�i�i�FArrayList�j�𐶐�����B
        ArrayList l_lisQueryStrings = new ArrayList();
        ArrayList l_lisQueryContainers = new ArrayList();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        //�Q�j���ʏ������쐬����B
        //�Q�|�P�j�@@ID���ڎw�莞�i�p�����[�^.����ID != null�j�̏ꍇ�A
        //����ID���������������ɒǉ�����B
        if (l_orderID != null)
        {
            //�@@�������������� = "order_id = ?"
            l_lisQueryStrings.add(" order_id = ? ");
            //�@@�f�[�^�R���e�i�Ƀp�����[�^.����ID��ǉ�����B
            l_lisQueryContainers.add(l_orderID);
        }
        //�Q�|�Q�j�@@��L�ȊO�̏ꍇ�A��������肷����������������ɒǉ�����B
        else
        {
            //�Q�|�Q�|�P�j�@@���X���������������ɒǉ�����B
            //�p�����[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B
            //�������������� = "branch_id in (?, ?,,,) "
            if (l_strBranchCodes != null && l_strBranchCodes.length != 0)
            {
                StringBuffer l_sbBranchIdForQuery = new StringBuffer(" branch_id in (");
                int l_intLength = l_strBranchCodes.length;
                try
                {
                    for (int i = 0; i < l_intLength; i++)
                    {
                        l_sbBranchIdForQuery.append("?,");
                        //�f�[�^�R���e�i�Ƀp�����[�^.���X�R�[�h��
                        //�Y�����镔�X.���XID��v�f�����Aadd����B
                        Branch l_branch = l_accountManager.getBranch(l_institution, l_strBranchCodes[i]);
                        l_lisQueryContainers.add(String.valueOf(l_branch.getBranchId()));
                    }
                }
                catch (NotFoundException l_nfe)
                {
                    log.error("�����ɊY������f�[�^�����݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                        "WEB3AdminToDataManager." + STR_METHOD_NAME,
                        "�����ɊY������f�[�^�����݂��Ȃ��B");
                }
                l_lisQueryStrings.add(l_sbBranchIdForQuery.substring(0, l_sbBranchIdForQuery.length() - 1) + ") ");
                //�Q�|�Q�|�Q�j�@@�������������������ɒǉ�����B
                //�������������� += " and order_open_status = ?"
                l_lisQueryStrings.add(" and order_open_status = ? ");
            }
            else
            {
                //�Q�|�Q�|�Q�j�@@�������������������ɒǉ�����B
                //�������������� += " order_open_status = ?"
                l_lisQueryStrings.add(" order_open_status = ? ");
            }

            //�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j
            //�E"�I�[�v��"
            l_lisQueryContainers.add(String.valueOf(OrderOpenStatusEnum.OPEN.intValue()));

            //�Q�|�Q�|�R�j�@@����������ʏ��������������ɒǉ�����B
            //�Q�|�Q�|�R�|�P�j�@@�ȉ��A�p�����[�^.����������ʈꗗ�̗v�f�����ALoop���A����������ʏ������쐬����B
            String l_strTriggerOrderTypeCondtion = "";
            if (l_strTriggerOrderTypes != null && l_strTriggerOrderTypes.length != 0)
            {
                int l_intLength = l_strTriggerOrderTypes.length;
                for (int i = 0; i < l_intLength; i++)
                {
                    //�Q�|�Q�|�R�|�P�|�P�j�@@�����Ώۂ̗v�f��"�t�w�l"�̏ꍇ
                    if (WEB3TriggerOrderTypeDef.STOP.equals(l_strTriggerOrderTypes[i]))
                    {
                        //[����������ʏ��� == ""�̏ꍇ]
                        //����������ʏ��� = " (order_condition_type = ?)"
                        if ("".equals(l_strTriggerOrderTypeCondtion))
                        {
                            l_strTriggerOrderTypeCondtion = " (order_condition_type = ?)";
                        }
                        //[��L�ȊO�̏ꍇ]
                        //����������ʏ��� += " or (order_condition_type = ?)"
                        else
                        {
                            l_strTriggerOrderTypeCondtion += " or (order_condition_type = ?)";
                        }

                        //�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j
                        //�E"�t�w�l"
                        l_lisQueryContainers.add(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
                    }

                    //�Q�|�Q�|�R�|�P�|�Q�j�@@�����Ώۂ̗v�f��"W�w�l"�̏ꍇ
                    else if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_strTriggerOrderTypes[i]))
                    {
                        //[����������ʏ��� == ""�̏ꍇ]
                        //����������ʏ��� = " (order_condition_type = ?"
                        //           + " and request_type <> ?)"
                        if ("".equals(l_strTriggerOrderTypeCondtion))
                        {
                            l_strTriggerOrderTypeCondtion = " (order_condition_type = ?"
                                + " and request_type <> ?)";
                        }

                        //[��L�ȊO�̏ꍇ]
                        //����������ʏ��� += " or (order_condition_type = ?"
                        //�@@�@@�@@�@@+ " and request_type <> ?)"
                        else
                        {
                            l_strTriggerOrderTypeCondtion +=
                                " or (order_condition_type = ?" + " and request_type <> ?)";
                        }

                        // �f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j
                        //�@@�@@�@@�E"W�w�l"
                        //�@@�@@�@@�E"�����T�[�o"
                        l_lisQueryContainers.add(WEB3OrderingConditionDef.W_LIMIT_PRICE);
                        l_lisQueryContainers.add(WEB3RequestTypeDef.QUOTE_SERVER);
                    }
                }

                //�Q�|�Q�|�R�|�Q�j�@@�쐬��������������ʏ�������������������ɒǉ�����B
                l_lisQueryStrings.add("and (" + l_strTriggerOrderTypeCondtion + ")");
            }

            //�Q�|�Q�|�S�j�@@�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A
            //�ڋq���������������ɒǉ�����B
            //�Q�|�Q�|�S�|�P�j�@@�g���K�[�����Ǘ��҃f�[�^�}�l�[�W��.get�ڋq�ꗗ()��
            //�R�[������B
            //[get�ڋq�ꗗ()�Ɏw�肷�����]
            //�،���ЃR�[�h�F�@@�p�����[�^.�،����.�،���ЃR�[�h
            //���X�R�[�h�F�@@�p�����[�^.���X�R�[�h
            //�ڋq�R�[�h�F�@@�p�����[�^.�ڋq�R�[�h
            if (WEB3StringTypeUtility.isNotEmpty(l_strAccountCode))
            {
                WEB3GentradeMainAccount[] l_mainAccounts =
                    getAccountList(l_institution.getInstitutionCode(), l_strBranchCodes, l_strAccountCode);
                //�Q�|�Q�|�S�|�Q�j�@@�Q�|�Q�|�S�|�P�j�̖߂�l�̗v�f�����A����������"?"���A
                //�f�[�^�R���e�i�ɁA�e�v�f�̌���ID��ǉ�����B
                //�������������� += " and account_id in (?, ?,,,) "
                StringBuffer l_sbMainAccountIdForQuery = new StringBuffer(" and account_id in (");
                int l_intLength = l_strBranchCodes.length;
                for (int i = 0; i < l_intLength; i++)
                {
                    l_sbMainAccountIdForQuery.append("?,");
                    //�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j
                    //�E�Q�|�Q�|�S�|�P�j�̖߂�l�̊e�v�f.����ID
                    l_lisQueryContainers.add(String.valueOf(l_mainAccounts[i].getAccountId()));
                }
                l_lisQueryStrings.add(
                    l_sbMainAccountIdForQuery.substring(0, l_sbMainAccountIdForQuery.length() - 1) + ") ");
            }
        }
        //�Q�|�R�j�@@�p�����[�^.����IDFrom != null�@@����
        //�p�����[�^.����IDTo != null�̏ꍇ�A����ID�����W������
        //���������ɒǉ�����B
        //���񓯊��������s���ꍇ�ɐݒ肳���B
        if (l_accountIDFrom != null && l_accountIDTo != null)
        {
            //�������������� += " and account_id >= ?"
            //�@@�@@�@@�@@�@@�@@+ " and account_id <= ?"
            l_lisQueryStrings.add(" and account_id >= ? ");
            l_lisQueryStrings.add(" and account_id <= ? ");
            //�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j
            //�E�p�����[�^.����IDFrom
            //�E�p�����[�^.����IDTo
            l_lisQueryContainers.add(l_accountIDFrom);
            l_lisQueryContainers.add(l_accountIDTo);
        }

        //�R�j�@@�߂�l���i�[����ArrayList�𐶐�����B
        ArrayList l_lisOrderUnits = new ArrayList();

        boolean l_blnIsEquity = false;
        boolean l_blnIsMargin = false;
        boolean l_blnIsFuture = false;
        boolean l_blnIsOption = false;
        int l_intLength = l_strCommodityDivs.length;
        for (int i = 0; i < l_intLength; i++)
        {
            if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDivs[i]))
            {
                l_blnIsEquity = true;
            }
            else if (WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDivs[i]))
            {
                l_blnIsMargin = true;
            }
            else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDivs[i]))
            {
                l_blnIsFuture = true;
            }
            else if (WEB3CommodityDivDef.OPTION.equals(l_strCommodityDivs[i]))
            {
                l_blnIsOption = true;
            }
        }

        int l_intCommonQueryString = l_lisQueryStrings.size();
        int l_intCommonContainer = l_lisQueryContainers.size();

        //�S�j�@@�p�����[�^.���i�敪�ꗗ��"��������" or "�M�p���"���܂܂��ꍇ�A
        //�ȉ��̏������s���B
        if (l_blnIsEquity || l_blnIsMargin)
        {
            //Eqtype��������������
            ArrayList l_lisEqtypeQueryStrings = new ArrayList();
            //�S�|�P�j�@@ID���ڎw�莞�łȂ��i�p�����[�^.����ID == null�j�̏ꍇ�A
            //Eqtype���L�̌����������쐬����B
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            if (l_orderID == null)
            {
                //�S�|�P�|�P�j�@@�p�����[�^.����ID == null�̏ꍇ�A
                //[�p�����[�^.���i�敪�ꗗ��"��������"���܂܂��ꍇ]
                if (l_blnIsEquity)
                {
                    //Eqtype�������������� = " (order_type in (?, ?)"
                    //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ " and sonar_traded_code <> ?)"
                    l_lisEqtypeQueryStrings.add(" (order_type in (?, ?) ");
                    l_lisEqtypeQueryStrings.add(" and sonar_traded_code <> ?) ");
                    //Eqtype�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j
                    //�E"����������"
                    //�E"����������"
                    //�E"����O����"
                    l_lisQueryContainers.add(String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue()));
                    l_lisQueryContainers.add(String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue()));
                    l_lisQueryContainers.add(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
                }
                //[�p�����[�^.���i�敪�ꗗ��"�M�p���"���܂܂��ꍇ]
                if (l_blnIsMargin)
                {
                    //[Eqtype�������������񂪋󕶎��̏ꍇ]
                    if (!l_blnIsEquity)
                    {
                        //  Eqtype�������������� = " (order_categ in (?, ?))"
                        l_lisEqtypeQueryStrings.add(" (order_categ in (?, ?)) ");
                    }
                    //[��L�ȊO]
                    else
                    {
                        //�@@Eqtype�������������� += " or order_categ in (?, ?)"
                        l_lisEqtypeQueryStrings.add(" or order_categ in (?, ?) ");
                    }
                    //Eqtype�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j
                    //�E"�M�p�V�K������"
                    //�E"�M�p�ԍϒ���"
                    l_lisQueryContainers.add(String.valueOf(OrderCategEnum.OPEN_MARGIN.intValue()));
                    l_lisQueryContainers.add(String.valueOf(OrderCategEnum.CLOSE_MARGIN.intValue()));
                }
                //�S�|�P�|�Q�j�@@Eqtype��������������ɉ��Z�q��ǉ�����B
                //Eqtype�������������� = " and (" + Eqtype�������������� + ")"
                StringBuffer l_sbEqtypeQueryString = new StringBuffer("");
                int l_intEqtypeQueryString = l_lisEqtypeQueryStrings.size();
                for (int j = 0; j < l_intEqtypeQueryString; j++)
                {
                    l_sbEqtypeQueryString.append(l_lisEqtypeQueryStrings.get(j));
                }
                l_lisQueryStrings.add(" and (" + l_sbEqtypeQueryString.toString() + ") ");

                //�S�|�P�|�R�j�@@�s�������Eqtype��������������ɒǉ�����B
                //�p�����[�^.�s��R�[�h�ꗗ�̗v�f����"?"��ǉ�����B
                if (l_strMarketCodes != null && l_strMarketCodes.length != 0)
                {
                    //Eqtype�������������� = " and market_id in (?, ?,,,) "
                    StringBuffer l_sbMarketIdForQuery = new StringBuffer(" and market_id in (");
                    l_intLength = l_strMarketCodes.length;
                    try
                    {
                        WEB3GentradeFinObjectManager l_finObjManager =
                            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                        for (int i = 0; i < l_intLength; i++)
                        {
                            l_sbMarketIdForQuery.append("?,");
                            WEB3GentradeMarket l_market =
                                (WEB3GentradeMarket)l_finObjManager.getMarket(l_institution, l_strMarketCodes[i]);

                            //Eqtype�f�[�^�R���e�i�Ƀp�����[�^.�s��R�[�h�ꗗ��
                            //�Y������s��.�s��ID��v�f�����Aadd����B
                            l_lisQueryContainers.add(String.valueOf(l_market.getMarketId()));
                        }
                    }
                    //���擾���ɗ�O�ƂȂ����ꍇ�A
                    //�u�����ɊY������f�[�^�����݂��Ȃ��B�v
                    //�̋Ɩ��G���[���X���[����B
                    catch (NotFoundException l_nfe)
                    {
                        log.error("�����ɊY������f�[�^�����݂��Ȃ��B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                            "WEB3AdminToDataManager." + STR_METHOD_NAME,
                            "�����ɊY������f�[�^�����݂��Ȃ��B");
                    }
                    l_lisQueryStrings.add(l_sbMarketIdForQuery.substring(0, l_sbMarketIdForQuery.length() - 1) + ") ");
                }

                //�S�|�P�|�S�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A
                //����������Eqtype��������������ɒǉ�����B
                if (WEB3StringTypeUtility.isNotEmpty(l_strProductCode))
                {
                    WEB3EquityProductManager l_productManager =
                        (WEB3EquityProductManager) l_tradingModule.getProductManager();
                    try
                    {
                        //Eqtype�������������� = " and product_id = ?"
                        l_lisQueryStrings.add(" and product_id = ? ");
                        EqTypeProduct l_product = l_productManager.getProduct(l_institution, l_strProductCode);
                        //Eqtype�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j
                        //�E�p�����[�^.�����R�[�h�ɊY�����銔������.����ID
                        l_lisQueryContainers.add(String.valueOf(l_product.getProductId()));
                    }
                    //���擾���ɗ�O�ƂȂ����ꍇ�A
                    //�u�����ɊY������f�[�^�����݂��Ȃ��B�v
                    //�̋Ɩ��G���[���X���[����B
                    catch (NotFoundException l_nfe)
                    {
                        log.error("�����ɊY������f�[�^�����݂��Ȃ��B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                            "WEB3AdminToDataManager." + STR_METHOD_NAME,
                            "�����ɊY������f�[�^�����݂��Ȃ��B");
                    }
                }
            }

            StringBuffer l_sbQueryString = new StringBuffer("");
            int l_intQueryString = l_lisQueryStrings.size();
            for (int i = 0; i < l_intQueryString; i++)
            {
                l_sbQueryString.append(l_lisQueryStrings.get(i));
            }

            int l_intQueryContainer = l_lisQueryContainers.size();
            Object[] l_objContainers = new Object[l_intQueryContainer];
            l_lisQueryContainers.toArray(l_objContainers);

            //�S�|�Q�j�@@DB����������B
            //QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor = null;
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                //[doFindAllQuery()�ɃZ�b�g����p�����[�^]
                //arg0�F�@@���������P��Row.TYPE
                //arg1�F�@@�쐬������������������
                //�@@�@@�@@+ �쐬����Eqtype��������������
                //arg2�F�@@�쐬�����f�[�^�R���e�i.toArray()��
                //�@@�@@�@@�@@�쐬����Eqtype�f�[�^�R���e�i.toArray()��
                //�@@�@@�@@�@@�߂�l��v�f�Ƃ���z��
                //�@@�@@�@@  ��Eqtype���������^�f�[�^�R���e�i���쐬���Ă��Ȃ��ꍇ�́A
                //�@@�@@�@@�@@���������ɒǉ����Ȃ��B
                List l_lisRows = l_queryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbQueryString.toString(),
                    l_objContainers);

                if (!l_lisRows.isEmpty())
                {
                    WEB3EquityOrderManager l_orderMgr =
                        (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

                    int l_intResultLength = l_lisRows.size();
                    for (int i = 0; i < l_intResultLength; i++)
                    {
                        l_lisOrderUnits.add(l_orderMgr.toOrderUnit((EqtypeOrderUnitRow)l_lisRows.get(i)));
                    }
                }
            }
            catch (DataQueryException l_dqex)
            {
                log.error("DB�A�N�Z�X�G���[", l_dqex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminToDataManager." + STR_METHOD_NAME,
                    l_dqex.getMessage(),
                    l_dqex);
            }
            catch (DataNetworkException l_dnex)
            {
                log.error("DB�A�N�Z�X�G���[", l_dnex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminToDataManager." + STR_METHOD_NAME,
                    l_dnex.getMessage(),
                    l_dnex);
            }
        }

        int l_intEqtypeQueryString = l_lisQueryStrings.size();
        int l_intEqtypeContainer = l_lisQueryContainers.size();

        for (int i = 0; i < l_intEqtypeContainer - l_intCommonContainer; i++)
        {
            l_lisQueryContainers.remove(l_intCommonContainer);
        }

        for (int i = 0; i < l_intEqtypeQueryString - l_intCommonQueryString; i++)
        {
            l_lisQueryStrings.remove(l_intCommonQueryString);
        }

        //�T�j�@@�p�����[�^.���i�敪�ꗗ��"�敨" or "�I�v�V����"���܂܂��ꍇ�A
        //�ȉ��̏������s���B
        if (l_blnIsFuture || l_blnIsOption)
        {
            //Ifo���L�̌�������
            ArrayList l_lisIfoQueryStrings = new ArrayList();
            //�T�|�P�j�@@ID���ڎw�莞�łȂ��i�p�����[�^.����ID == null�j�̏ꍇ
            if (l_orderID == null)
            {
                //[�p�����[�^.���i�敪�ꗗ��"�敨"���܂܂��ꍇ]
                if (l_blnIsFuture)
                {
                    //Ifo�������������� = " future_option_div = ?"
                    l_lisIfoQueryStrings.add(" future_option_div = ? ");
                    //Ifo�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j
                    //�E"�敨"
                    l_lisQueryContainers.add(WEB3FuturesOptionDivDef.FUTURES);
                }
                //[�p�����[�^.���i�敪�ꗗ��"�I�v�V����"���܂܂��ꍇ]
                if (l_blnIsOption)
                {
                    //[Ifo�������������񂪋󕶎��̏ꍇ]
                    if (!l_blnIsFuture)
                    {
                        //Ifo�������������� = " future_option_div = ?"
                        l_lisIfoQueryStrings.add(" future_option_div = ? ");
                    }
                    //[��L�ȊO]
                    else
                    {
                        //Ifo�������������� += " or future_option_div = ?"
                        l_lisIfoQueryStrings.add(" or future_option_div = ? ");
                    }
                    //Ifo�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j
                    //�E"�I�v�V����"
                    l_lisQueryContainers.add(WEB3FuturesOptionDivDef.OPTION);
                }

                //�T�|�P�|�P�j�@@Ifo��������������ɉ��Z�q��ǉ�����B
                StringBuffer l_sbIfoQueryString = new StringBuffer("");
                int l_intIfoQueryString = l_lisIfoQueryStrings.size();
                for (int j = 0; j < l_intIfoQueryString; j++)
                {
                    l_sbIfoQueryString.append(l_lisIfoQueryStrings.get(j));
                }
                //Ifo�������������� = " and (" + Ifo�������������� + ")"
                l_lisQueryStrings.add(" and (" + l_sbIfoQueryString.toString() + ") ");
            }

            StringBuffer l_sbQueryString = new StringBuffer("");
            int l_intQueryString = l_lisQueryStrings.size();
            for (int i = 0; i < l_intQueryString; i++)
            {
                l_sbQueryString.append(l_lisQueryStrings.get(i));
            }
            int l_intQueryContainer = l_lisQueryContainers.size();
            Object[] l_objContainer = new Object[l_intQueryContainer];
            l_lisQueryContainers.toArray(l_objContainer);

            //�@@�T�|�Q�j�@@DB����������B
            //QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor = null;
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                //[doFindAllQuery()�ɃZ�b�g����p�����[�^]
                //�@@arg0�F�@@�敨OP�����P��Row.TYPE
                //�@@arg1�F�@@�쐬������������������
                //�@@�@@�@@�@@�@@+ �쐬����Ifo��������������
                //�@@arg2�F�@@�쐬�����f�[�^�R���e�i.toArray()��
                //�@@�@@�@@�@@�@@�쐬����Ifo�f�[�^�R���e�i.toArray()��
                //�@@�@@�@@�@@�@@�߂�l��v�f�Ƃ���z��
                //�@@��Ifo���������^�f�[�^�R���e�i���쐬���Ă��Ȃ��ꍇ�́A
                //�@@�@@���������ɒǉ����Ȃ��B
                List l_lisRows = l_queryProcessor.doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    l_sbQueryString.toString(),
                    l_objContainer);

                if (!l_lisRows.isEmpty())
                {
                    WEB3OptionOrderManagerImpl l_orderMgr =
                        (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
                    int l_intResultLength = l_lisRows.size();
                    for (int i = 0; i < l_intResultLength; i++)
                    {
                        l_lisOrderUnits.add(l_orderMgr.toOrderUnit((IfoOrderUnitRow)l_lisRows.get(i)));
                    }
                }
            }
            catch (DataQueryException l_dqex)
            {
                log.error("DB�A�N�Z�X�G���[", l_dqex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminToDataManager." + STR_METHOD_NAME,
                    l_dqex.getMessage(),
                    l_dqex);
            }
            catch (DataNetworkException l_dnex)
            {
                log.error("DB�A�N�Z�X�G���[", l_dnex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminToDataManager." + STR_METHOD_NAME,
                    l_dnex.getMessage(),
                    l_dnex);
            }
        }

        //�Q�j�@@�������ʂ�ԋp����B
        if (l_lisOrderUnits.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        OrderUnit[] l_orderUnits = new OrderUnit[l_lisOrderUnits.size()];
        l_lisOrderUnits.toArray(l_orderUnits);
        log.exiting(STR_METHOD_NAME);
        return l_orderUnits;
    }

    /**
     * (get���i�敪�ꗗ)<BR>
     * �����̕��X�R�[�h�ꗗ�ɊY�����镔�X��<BR>
     * ���i���{�󋵂��`�F�b�N���A���{���Ă��鏤�i�̈ꗗ��ԋp����B<BR>
     * �������̕��X�R�[�h�ꗗ�̂����A<BR>
     * �@@�ꕔ�X�ł����{���Ă���΁A���{�ƂȂ�B<BR>
     * <BR>
     * �P�j���X�ꗗ�̎擾<BR>
     * �@@�P�|�P�jArrayList�𐶐�����B<BR>
     * �@@�P�|�Q�j�p�����[�^.���X�R�[�h�ꗗ�̗v�f�����A<BR>
     * �@@�@@�g���A�J�E���g�}�l�[�W��.get���X()���\�b�h���R�[�����A<BR>
     * �@@�@@���ʂ�ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@[get���X()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F�@@�����Ώۂ̕��X�R�[�h<BR>
     * �@@�P�|�R�jArrayList.toArray()���\�b�h���R�[�����A<BR>
     * �@@�@@���X�ꗗ(���X�I�u�W�F�N�g�̔z��)���擾����B<BR>
     * <BR>
     * �Q�j�@@�߂�l���i�[����TreeMap�𐶐�����B<BR>
     * <BR>
     * �R�j�@@����������ǉ�����B<BR>
     * �@@TreeMap.put("��������", "��������")���\�b�h���R�[������B<BR>
     * <BR>
     * �S�j�P�j�ɂĎ擾�������X�ꗗ�̗v�f�����A<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�S�|�P�j�����Ώۂ̕��X�̊e���{�敪�̒l�ɂ��A<BR>
     * �@@�@@���i�敪��TreeMap�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@[�����Ώۂ̕��X.���x�M�p���{�敪 == "���{"�̏ꍇ or<BR>
     * �@@�@@�@@�����Ώۂ̕��X.��ʐM�p���{�敪 == "���{"�̏ꍇ]<BR>
     * �@@�@@�@@TreeMap.put("�M�p���", "�M�p���")���\�b�h���R�[������B<BR>
     * �@@�@@[�����Ώۂ̕��X.�敨���{�敪 == "���{"�̏ꍇ]<BR>
     * �@@�@@�@@TreeMap.put("�敨", "�敨")���\�b�h���R�[������B<BR>
     * �@@�@@[�����Ώۂ̕��X.�I�v�V�������{�敪 == "���{"�̏ꍇ]<BR>
     * �@@�@@�@@TreeMap.put("�I�v�V����", "�I�v�V����")���\�b�h���R�[������B<BR>
     * <BR>
     * �T�jTreeMap.values().toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCodeList - (���X�R�[�h�ꗗ)<BR>
     * ���X�R�[�h�̔z��<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 44064F80019F
     */
    public static String[] getCommodityDivList(String l_strInstitutionCode, String[] l_strBranchCodeList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCommodityDivList(String, String[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBranchCodeList == null || l_strBranchCodeList.length == 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminToDataManager." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        
        // �P�j���X�ꗗ�̎擾
        // �P�|�P�jArrayList�𐶐�����B
        ArrayList l_lisBranchs = new ArrayList();
        int l_intLength = l_strBranchCodeList.length;
        
        // �P�|�Q�j�p�����[�^.���X�R�[�h�ꗗ�̗v�f�����A
        //�@@�@@�g���A�J�E���g�}�l�[�W��.get���X()���\�b�h���R�[�����A
        //�@@�@@���ʂ�ArrayList�ɒǉ�����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();  
            
        try
        {
            for(int i = 0; i < l_intLength; i++)
            {
                Branch l_branch = l_accountManager.getWeb3GenBranch(l_strInstitutionCode, l_strBranchCodeList[i]);
                l_lisBranchs.add(l_branch);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                "WEB3AdminToDataManager." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        // �@@�P�|�R�jArrayList.toArray()���\�b�h���R�[�����A
        // �@@�@@���X�ꗗ(���X�I�u�W�F�N�g�̔z��)���擾����B
        Branch[] l_branchs = new Branch[l_lisBranchs.size()];
        l_lisBranchs.toArray(l_branchs);
        
        // �Q�j�@@�߂�l���i�[����TreeMap�𐶐�����B
        TreeMap l_map= new TreeMap();
        
        // �R�j�@@����������ǉ�����B
        // TreeMap.put("��������", "��������")���\�b�h���R�[������B
        l_map.put(WEB3CommodityDivDef.EQUITY, WEB3CommodityDivDef.EQUITY);
        
        // �S�j�P�j�ɂĎ擾�������X�ꗗ�̗v�f�����A
        // �@@�ȉ��̏������J��Ԃ��B
        // �@@�S�|�P�j�����Ώۂ̕��X�̊e���{�敪�̒l�ɂ��A
        // �@@�@@���i�敪��TreeMap�ɒǉ�����B
        l_intLength = l_branchs.length;
        for(int i = 0; i < l_intLength; i++)
        {
            BranchRow l_branchRow =(BranchRow) l_branchs[i].getDataSourceObject();
            //�@@[�����Ώۂ̕��X.���x�M�p���{�敪 == "���{"�̏ꍇ or
            //�@@  �����Ώۂ̕��X.��ʐM�p���{�敪 == "���{"�̏ꍇ]
            // �@@ TreeMap.put("�M�p���", "�M�p���")���\�b�h���R�[������B
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginSysDiv())
                || WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginGenDiv()))
            {
                l_map.put(WEB3CommodityDivDef.MARGIN, WEB3CommodityDivDef.MARGIN);
            }
            
            //�@@[�����Ώۂ̕��X.�敨���{�敪 == "���{"�̏ꍇ]
            //  �@@TreeMap.put("�敨", "�敨")���\�b�h���R�[������B
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getFutureDiv()))
            {
                l_map.put(WEB3CommodityDivDef.FUTURE, WEB3CommodityDivDef.FUTURE);
            }
            
            // �@@ [�����Ώۂ̕��X.�I�v�V�������{�敪 == "���{"�̏ꍇ]
            //  TreeMap.put("�I�v�V����", "�I�v�V����")���\�b�h���R�[������B
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getOptionDiv()))
            {
                l_map.put(WEB3CommodityDivDef.OPTION, WEB3CommodityDivDef.OPTION);
            }
        }
        
        // �T�jTreeMap.values().toArray()�̖߂�l��ԋp����B
        String[] l_strProductList = new String[l_map.size()];
        l_map.values().toArray(l_strProductList);
        
        log.exiting(STR_METHOD_NAME);
        return l_strProductList;
    }
    
    /**
    * (get���ꎷ�s�����戵��~�ꗗ)<BR>
    * �����ɊY��������ꎷ�s�����戵��~�e�[�u��<BR>
    * �̃��R�[�h��ԋp����B<BR>
    * <BR>
    * �P�j�@@���������̌���<BR>
    * �@@�p�����[�^.�����敪�ɂ��A�������@@�𕪊򂷂�B<BR>
    * �@@�P�|�P�j�@@�p�����[�^.�����敪 == "���i"�̏ꍇ<BR>
    * �@@�@@���������͈ȉ��̒ʂ�Ƃ���B<BR>
    * �@@�@@�y���������z<BR>
    * �@@�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
    * �@@�@@�@@And ���X�R�[�h IN (�p�����[�^.���X�R�[�h�̑S�v�f)<BR>
    * �@@�@@�@@And �ݒ�Ώێ�� = "���i"<BR>
    * �@@�@@�@@And �L�[��� IN (�،���Ђ����{���Ă��鏤�i�̏��i�敪�ꗗ(*1))<BR>
    * �@@�@@�@@And �폜�t���O = "DEFAULT"<BR>
    * <BR>
    * �@@�@@�y�\�[�g�����z<BR>
    * �@@�@@�@@�L�[��� ����, ���X�R�[�h ����<BR>
    * <BR>
    * �@@�P�|�Q�j�@@�p�����[�^.�����敪 == "�s��"�̏ꍇ<BR>
    * �@@�@@���������͈ȉ��̒ʂ�Ƃ���B<BR>
    * �@@�@@�y���������z<BR>
    * �@@�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
    * �@@�@@�@@And �ݒ�Ώێ�� = "�s��"<BR>
    * �@@�@@�@@And �L�[��� IN (�،���Ђ��戵�\�Ȏs��R�[�h�ꗗ(*2))<BR>
    * �@@�@@�@@And �폜�t���O = "DEFAULT"<BR>
    * <BR>
    * �@@�@@�y�\�[�g�����z<BR>
    * �@@�@@�@@to_number(�L�[���) ����<BR>
    * <BR>
    * �@@�P�|�R�j�@@�p�����[�^.�����敪 == "����"�̏ꍇ<BR>
    * �@@�@@���������͈ȉ��̒ʂ�Ƃ���B<BR>
    * �@@�@@�y���������z<BR>
    * �@@�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
    * �@@�@@�@@And �ݒ�Ώێ�� = "����"<BR>
    * �@@�@@�@@And �L�[��� = �p�����[�^.�����R�[�h�@@��null�̏ꍇ�͏����Ɋ܂߂Ȃ��B<BR>
    * �@@�@@�@@And �L������To >= ���ݎ�����YYYYMMDD<BR>
    * �@@�@@�@@And �폜�t���O = "DEFAULT"<BR>
    * <BR>
    * �@@�@@�y�\�[�g�����z<BR>
    * �@@�@@�@@[�p�����[�^.�\�[�g�L�[ == null�̏ꍇ]<BR>
    * �@@�@@�@@�@@�\�[�g�����Ȃ��B<BR>
    * �@@�@@�@@[��L�ȊO]<BR>
    * �@@�@@�@@�@@�ȉ��̕�����A�p�����[�^.�\�[�g�L�[�̗v�f����Loop���A<BR>
    * �@@�@@�@@�@@�\�[�g�������쐬����B<BR>
    * �@@�@@�@@�@@<�\�[�g����><BR>
    * �@@�@@�@@�@@�@@�����Ώۂ̗v�f.�L�[���ڂ��A<BR>
    * �@@�@@�@@�@@�@@["�����R�[�h"�̏ꍇ]<BR>
    * �@@�@@�@@�@@�@@�@@���ꎷ�s�����戵��~�e�[�u��.�L�[���<BR>
    * �@@�@@�@@�@@�@@["�L������From"�̏ꍇ]<BR>
    * �@@�@@�@@�@@�@@�@@���ꎷ�s�����戵��~�e�[�u��.�L������From<BR>
    * <BR>
    * �@@�@@�@@�@@<�����^�~��><BR>
    * �@@�@@�@@�@@�@@�����Ώۂ̗v�f.�����^�~���ɑΉ�����\�[�g������iASC or DESC�j<BR>
    * <BR>
    * �Q�j�@@DB����<BR>
    * �@@�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
    * <BR>
    * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
    * �@@�@@�@@arg0�F�@@���ꎷ�s�����戵��~Row.TYPE<BR>
    * �@@�@@�@@arg1�F�@@���肵������������SQL�������l<BR>
    * �@@�@@�@@arg2�F�@@���肵�����������̌����l�̔z��i�FString[]�j<BR>
    * �@@�@@�@@arg3�F�@@null <BR>
    * �@@�@@�@@arg4�F�@@���肵���\�[�g������SQL�������l<BR>
    * <BR>
    * �R�j�@@�������ʂ�ԋp����B<BR>
    * <BR>
    * (*1)�g���K�[�����Ǘ��҃f�[�^�}�l�[�W��.get���i�敪�ꗗ()�ɂ�<BR>
    * �@@�擾����B<BR>
    * <BR>
    * �@@[get���i�敪�ꗗ()�ɃZ�b�g����p�����[�^]<BR>
    * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
    * �@@�@@���X�R�[�h�ꗗ�F�@@�p�����[�^.���X�R�[�h<BR>
    * <BR>
    * (*2)�i���X�s��ʁj�戵���.get�戵�\�s��()�ɂĎ擾����B<BR>
    * <BR>
    * �@@[get�戵�\�s��()�ɃZ�b�g����p�����[�^]<BR>
    * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
    * �@@�@@�����^�C�v�F�@@"����"<BR>
    * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
    * �،���ЃR�[�h<BR>
    * @@param l_strBranchCodes - (���X�R�[�h)<BR>
    * ���X�R�[�h�̔z��<BR>
    * @@param l_strTradeStopDiv - (�����敪)<BR>
    * �����敪<BR>
    * @@param l_strProductCode - (�����R�[�h)<BR>
    * �����R�[�h<BR>
    * @@param l_tradeStopSortKeys - (�\�[�g�L�[)<BR>
    * �\�[�g�L�[<BR>
    * @@return List
    * @@throws WEB3BaseException
    */
    public static List getTriggerOrderStopList(
        String l_strInstitutionCode, 
        String[] l_strBranchCodes, 
        String l_strTradeStopDiv, 
        String l_strProductCode,
        WEB3AdminToTradeStopSortKey[] l_tradeStopSortKeys)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getTriggerOrderStopList(String, String[], String, String, " +
            "WEB3AdminToTradeStopSortKey[])";
        log.entering(STR_METHOD_NAME);

        ArrayList l_lisQueryStrings = new ArrayList();
        ArrayList l_lisQueryContainers = new ArrayList();
        String l_strSortCondition = null;
        
        //�P�j�@@���������̌���
        //�p�����[�^.�����敪�ɂ��A�������@@�𕪊򂷂�B
        //�P�|�P�j�@@�p�����[�^.�����敪 == "���i"�̏ꍇ
        //���������͈ȉ��̒ʂ�Ƃ���B
        if (WEB3TargetTypeDef.COMMODITY.equals(l_strTradeStopDiv))
        {
            //�y���������z
            //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
            l_lisQueryStrings.add(" institution_code = ? ");
            l_lisQueryContainers.add(l_strInstitutionCode);
            
            //And ���X�R�[�h IN (�p�����[�^.���X�R�[�h�̑S�v�f)
            l_lisQueryStrings.add(" and branch_code in ( ");
            int l_intLen = l_strBranchCodes.length;
            int i = 0;
            for (; i < l_intLen - 1; i++)
            {
                l_lisQueryStrings.add(" ?, ");
                l_lisQueryContainers.add(l_strBranchCodes[i]);
            }
            l_lisQueryStrings.add(" ?) ");
            l_lisQueryContainers.add(l_strBranchCodes[i]);
            
            //And �ݒ�Ώێ�� = "���i"
            l_lisQueryStrings.add(" and target_type = ? ");
            l_lisQueryContainers.add(WEB3TargetTypeDef.COMMODITY);
            
            //And �L�[��� IN (�،���Ђ����{���Ă��鏤�i�̏��i�敪�ꗗ(*1))
            l_lisQueryStrings.add(" and key in ( ");
            String[] l_strKeys = getCommodityDivList(l_strInstitutionCode, l_strBranchCodes);
            l_intLen = l_strKeys.length;
            for (i = 0; i < l_intLen - 1; i++)
            {
                l_lisQueryStrings.add(" ?, ");
                l_lisQueryContainers.add(l_strKeys[i]);
            }
            l_lisQueryStrings.add(" ?) ");
            l_lisQueryContainers.add(l_strKeys[i]);
            
            //And �폜�t���O = "DEFAULT"
            l_lisQueryStrings.add(" and delete_flag = ? ");
            l_lisQueryContainers.add(BooleanEnum.FALSE);
            
            //�y�\�[�g�����z
            //�L�[��� ����, ���X�R�[�h ����
            l_strSortCondition = " key asc, branch_code asc ";
        }
        //�P�|�Q�j�@@�p�����[�^.�����敪 == "�s��"�̏ꍇ
        //���������͈ȉ��̒ʂ�Ƃ���B
        else if (WEB3TargetTypeDef.MARKET.equals(l_strTradeStopDiv))
        {
            //�y���������z
            //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
            l_lisQueryStrings.add(" institution_code = ? ");
            l_lisQueryContainers.add(l_strInstitutionCode);
            
            //And �ݒ�Ώێ�� = "�s��"
            l_lisQueryStrings.add(" and target_type = ? ");
            l_lisQueryContainers.add(WEB3TargetTypeDef.MARKET);
            
            //And �L�[��� IN (�،���Ђ��戵�\�Ȏs��R�[�h�ꗗ(*2))
            l_lisQueryStrings.add(" and key in ( ");
            String[] l_strKeys = WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
                l_strInstitutionCode, 
                ProductTypeEnum.EQUITY);
            int l_intLen = l_strKeys.length;
            int i = 0;
            for (; i < l_intLen - 1; i++)
            {
                l_lisQueryStrings.add(" ?, ");
                l_lisQueryContainers.add(l_strKeys[i]);
            }
            l_lisQueryStrings.add(" ?) ");
            l_lisQueryContainers.add(l_strKeys[i]);
            
            //And �폜�t���O = "DEFAULT"
            l_lisQueryStrings.add(" and delete_flag = ? ");
            l_lisQueryContainers.add(BooleanEnum.FALSE);
            
            //�y�\�[�g�����z
            //to_number(�L�[���) ����
            l_strSortCondition = " to_number(key) asc ";
        }
        //�P�|�R�j�@@�p�����[�^.�����敪 == "����"�̏ꍇ
        //���������͈ȉ��̒ʂ�Ƃ���B
        else if (WEB3TargetTypeDef.PRODUCT.equals(l_strTradeStopDiv))
        {
            //�y���������z
            //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
            l_lisQueryStrings.add(" institution_code = ? ");
            l_lisQueryContainers.add(l_strInstitutionCode);
            
            //And �ݒ�Ώێ�� = "����"
            l_lisQueryStrings.add(" and target_type = ? ");
            l_lisQueryContainers.add(WEB3TargetTypeDef.PRODUCT);
            
            //And �L�[��� = �p�����[�^.�����R�[�h�@@��null�̏ꍇ�͏����Ɋ܂߂Ȃ��B
            if (WEB3StringTypeUtility.isNotEmpty(l_strProductCode))
            {
                l_lisQueryStrings.add(" and key = ? ");
                l_lisQueryContainers.add(l_strProductCode);
            }
            
            //And �L������To >= ���ݎ�����YYYYMMDD
            l_lisQueryStrings.add(" and valid_term_to >= ? ");
            l_lisQueryContainers.add(
                WEB3DateUtility.formatDate(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()), "yyyyMMdd"));
            
            //And �폜�t���O = "DEFAULT"
            l_lisQueryStrings.add(" and delete_flag = ? ");
            l_lisQueryContainers.add(BooleanEnum.FALSE);
            
            //�y�\�[�g�����z
            //[�p�����[�^.�\�[�g�L�[ == null�̏ꍇ]
            //�\�[�g�����Ȃ��B
            if (l_tradeStopSortKeys != null && l_tradeStopSortKeys.length != 0)
            {
                //�ȉ��̕�����A�p�����[�^.�\�[�g�L�[�̗v�f����Loop���A
                //�\�[�g�������쐬����B
                //<�\�[�g����>
                //�����Ώۂ̗v�f.�L�[���ڂ��A
                //["�����R�[�h"�̏ꍇ]
                //���ꎷ�s�����戵��~�e�[�u��.�L�[���
                //["�L������From"�̏ꍇ]
                //���ꎷ�s�����戵��~�e�[�u��.�L������From
                int l_intLen = l_tradeStopSortKeys.length;
                for (int i = 0; i < l_intLen; i++)
                {
                    if (WEB3AdminToTradeStopKeyItemDef.PRODUCT_CODE.equals(l_tradeStopSortKeys[i].keyItem))
                    {
                        if (WEB3StringTypeUtility.isNotEmpty(l_strSortCondition))
                        {
                            l_strSortCondition += " , key ";
                        }
                        else
                        {
                            l_strSortCondition = " key ";
                        }
                        
                        if (WEB3AscDescDef.ASC.equals(l_tradeStopSortKeys[i].ascDesc))
                        {
                            l_strSortCondition += " asc ";
                        }
                        else if (WEB3AscDescDef.DESC.equals(l_tradeStopSortKeys[i].ascDesc))
                        {
                            l_strSortCondition += " desc ";
                        }
                    }
                    else if (WEB3AdminToTradeStopKeyItemDef.EXPIRATION_START_DATE.equals(
                        l_tradeStopSortKeys[i].keyItem))
                    {
                        if (WEB3StringTypeUtility.isNotEmpty(l_strSortCondition))
                        {
                            l_strSortCondition += " , valid_term_from ";
                        }
                        else
                        {
                            l_strSortCondition = " valid_term_from ";
                        }
                        
                        if (WEB3AscDescDef.ASC.equals(l_tradeStopSortKeys[i].ascDesc))
                        {
                            l_strSortCondition += " asc ";
                        }
                        else if (WEB3AscDescDef.DESC.equals(l_tradeStopSortKeys[i].ascDesc))
                        {
                            l_strSortCondition += " desc ";
                        }
                    }
                }
            }
        }
        
        //�Q�j�@@DB����
        StringBuffer l_sbQueryString = new StringBuffer("");
        int l_intQueryString = l_lisQueryStrings.size();
        for (int i = 0; i < l_intQueryString; i++)
        {
            l_sbQueryString.append(l_lisQueryStrings.get(i));
        }
        
        Object[] l_objContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_objContainers);
        List l_lisRows = null;
        try
        {
            //QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            //[doFindAllQuery()�ɃZ�b�g����p�����[�^]
            //�@@arg0�F�@@���ꎷ�s�����戵��~Row.TYPE
            //�@@arg1�F�@@���肵������������SQL�������l
            //�@@arg2�F�@@���肵���\�[�g������SQL�������l
            //�@@arg3�F�@@null
            //�@@arg4�F�@@���肵�����������̌����l�̔z��i�FString[]�j
            l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                TriggerOrderStopRow.TYPE, 
                l_sbQueryString.toString(), 
                l_strSortCondition,
                null,
                l_objContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminToDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminToDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        
        log.exiting(STR_METHOD_NAME);
        return l_lisRows;
    }
    
    /**
     * (create�戵��~���ꗗ)<BR>
     * �������戵��~���̈ꗗ���쐬����B<BR>
     * <BR>
     * �P�j�@@�߂�l���i�[����ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.���ꎷ�s�����戵��~�ꗗ��<BR>
     * �@@�v�f�����A�ȉ��̏�����Loop����B<BR>
     * �@@�Q�|�P�j�@@�戵��~���𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@���������戵��~����<BR>
     * �@@�@@�ȉ��̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@ID = �����Ώۂ̗v�f.���ꎷ�s�����戵��~ID<BR>
     * �@@�@@���X�R�[�h = �����Ώۂ̗v�f.���X�R�[�h<BR>
     * �@@�@@���i�敪 =<BR>
     * �@@�@@�@@�����Ώۂ̗v�f.�ݒ�Ώێ�� == "���i"�̏ꍇ�A<BR>
     * �@@�@@�@@�����Ώۂ̗v�f.�L�[���<BR>
     * �@@�@@�s��R�[�h =<BR>
     * �@@�@@�@@�����Ώۂ̗v�f.�ݒ�Ώێ�� == "�s��"�̏ꍇ�A<BR>
     * �@@�@@�@@�����Ώۂ̗v�f.�L�[���<BR>
     * �@@�@@�X�����J�敪 =<BR>
     * �@@�@@�@@�����Ώۂ̗v�f.�ݒ�Ώێ�� == "�s��"�̏ꍇ ���� <BR>
     * �@@�@@�@@�����Ώۂ̗v�f.�L�[��� == "JASDAQ"�̏ꍇ�A<BR>
     * �@@�@@�@@�����Ώۂ̗v�f.�ǉ����<BR>
     * �@@�@@�����R�[�h =<BR>
     * �@@�@@�@@�����Ώۂ̗v�f.�ݒ�Ώێ�� == "����"�̏ꍇ�A<BR>
     * �@@�@@�@@�����Ώۂ̗v�f.�L�[���<BR>
     * �@@�@@������ =<BR>
     * �@@�@@�@@�����Ώۂ̗v�f.�ݒ�Ώێ�� == "����"�̏ꍇ�A<BR>
     * �@@�@@�@@��������(*1).getDataSourceObject().������<BR>
     * �@@�@@��~���R = �����Ώۂ̗v�f.��~���R<BR>
     * �@@�@@�L������From = �����Ώۂ̗v�f.�L������From<BR>
     * �@@�@@�L������To = �����Ώۂ̗v�f.�L������To<BR>
     * <BR>
     * �@@�Q�|�R�j�@@������~�󋵂��i�[����ArrayList�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�����Ώۂ̏����������(*2)�̗v�f�����A<BR>
     * �@@�@@�ȉ��̏�����Loop����B<BR>
     * �@@�@@�Q�|�S�|�P�j�@@������~�󋵂𐶐�����B<BR>
     * <BR>
     * �@@�@@�Q�|�S�|�Q�j�@@������~�󋵂Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�����Ώۂ̗v�f���A<BR>
     * �@@�@@�@@["�A������"�̏ꍇ]<BR>
     * �@@�@@�@@�@@����������� = �����Ώۂ̗v�f<BR>
     * �@@�@@�@@�@@��~�t���O =<BR>
     * �@@�@@�@@�@@�@@�����Ώۂ̓��ꎷ�s�����戵��~Row.�A��������~�t���O<BR>
     * �@@�@@�@@["OCO����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@����������� = �����Ώۂ̗v�f<BR>
     * �@@�@@�@@�@@��~�t���O =<BR>
     * �@@�@@�@@�@@�@@�����Ώۂ̓��ꎷ�s�����戵��~Row.OCO������~�t���O<BR>
     * �@@�@@�@@["IFD����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@����������� = �����Ώۂ̗v�f<BR>
     * �@@�@@�@@�@@��~�t���O =<BR>
     * �@@�@@�@@�@@�@@�����Ώۂ̓��ꎷ�s�����戵��~Row.IFD������~�t���O<BR>
     * �@@�@@�@@["�t�w�l����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@����������� = �����Ώۂ̗v�f<BR>
     * �@@�@@�@@�@@��~�t���O =<BR>
     * �@@�@@�@@�@@�@@�����Ώۂ̓��ꎷ�s�����戵��~Row.�t�w�l������~�t���O<BR>
     * �@@�@@�@@["W�w�l����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@����������� = �����Ώۂ̗v�f<BR>
     * �@@�@@�@@�@@��~�t���O =<BR>
     * �@@�@@�@@�@@�@@�����Ώۂ̓��ꎷ�s�����戵��~Row.W�w�l������~�t���O<BR>
     * <BR>
     * �@@�@@�Q�|�S�|�R�j�@@�v���p�e�B�Z�b�g����������~�󋵂�<BR>
     * �@@�@@�@@ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�Q�|�T�j�@@�戵��~���ɒ�����~�󋵂��Z�b�g����B<BR>
     * �@@�@@�戵��~���.������~�󋵈ꗗ =<BR>
     * �@@�@@�@@������~�󋵂��i�[����ArrayList.toArray()<BR>
     * <BR>
     * �@@�Q�|�U�j�@@�߂�l���i�[����ArrayList�Ƀv���p�e�B�Z�b�g����<BR>
     * �@@�@@�戵��~����ǉ�����B<BR>
     * <BR>
     * �R�j�@@�߂�l���i�[����ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * (*1)��������<BR>
     * �@@�g���v���_�N�g�}�l�[�W��.getProduct(�،����,�����Ώۂ̗v�f.�L�[���)<BR>
     * <BR>
     * (*2)�����Ώۂ̏����������<BR>
     * �@@�ȉ��̏���������ʂ������ΏۂƂ���B<BR>
     * �@@�@@�@@"�A������"<BR>
     * �@@�@@�A"OCO����"<BR>
     * �@@�@@�B"IFD����"<BR>
     * �@@�@@�C"�t�w�l����"<BR>
     * �@@�@@�D"W�w�l"<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@param l_triggerOrderStopList - (���ꎷ�s�����戵��~�ꗗ)<BR>
     * ���ꎷ�s�����戵��~Row�̔z��<BR>
     * @@return WEB3AdminToTradeStopInfoUnit[]
     * @@throws WEB3BaseException
     */
    public static WEB3AdminToTradeStopInfoUnit[] createTradeStopInfoList(
        Institution l_institution, 
        TriggerOrderStopRow[] l_triggerOrderStopList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createTradeStopInfoList(Institution, TriggerOrderStopRow[])";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�߂�l���i�[����ArrayList�𐶐�����B
        ArrayList l_lisReturns = new ArrayList();
        
        //�Q�j�@@�p�����[�^.���ꎷ�s�����戵��~�ꗗ��
        //�v�f�����A�ȉ��̏�����Loop����B
        int l_intLen = l_triggerOrderStopList.length;
        for (int i = 0; i < l_intLen; i++)
        {
            //�Q�|�P�j�@@�戵��~���𐶐�����B
            WEB3AdminToTradeStopInfoUnit l_tradeStopInfoUnit = new WEB3AdminToTradeStopInfoUnit();
            
            //�Q�|�Q�j�@@���������戵��~����
            //�ȉ��̃v���p�e�B���Z�b�g����B
            //ID = �����Ώۂ̗v�f.���ꎷ�s�����戵��~ID
            l_tradeStopInfoUnit.id = String.valueOf(l_triggerOrderStopList[i].getTriggerOrderStopId());
            
            //���X�R�[�h = �����Ώۂ̗v�f.���X�R�[�h
            l_tradeStopInfoUnit.branchCode = l_triggerOrderStopList[i].getBranchCode();
            
            //���i�敪 =
            //�@@�����Ώۂ̗v�f.�ݒ�Ώێ�� == "���i"�̏ꍇ�A
            //�@@�����Ώۂ̗v�f.�L�[���
            String l_strTargetType = l_triggerOrderStopList[i].getTargetType();
            String l_strKey = l_triggerOrderStopList[i].getKey();
            if (WEB3TargetTypeDef.COMMODITY.equals(l_strTargetType))
            {
                l_tradeStopInfoUnit.productDiv = l_strKey;
            }
            //�s��R�[�h =
            //�@@�����Ώۂ̗v�f.�ݒ�Ώێ�� == "�s��"�̏ꍇ�A
            //�@@�����Ώۂ̗v�f.�L�[���
            else if (WEB3TargetTypeDef.MARKET.equals(l_strTargetType))
            {
                l_tradeStopInfoUnit.marketCode = l_strKey;
                
                //�X�����J�敪 =
                //�@@�����Ώۂ̗v�f.�L�[��� == "JASDAQ"�̏ꍇ�A
                //�@@�����Ώۂ̗v�f.�ǉ����
                if (WEB3MarketCodeDef.JASDAQ.equals(l_strKey))
                {
                    l_tradeStopInfoUnit.otcOpenDiv = l_triggerOrderStopList[i].getAddition();
                }
            }
            //�����R�[�h =
            //�@@�����Ώۂ̗v�f.�ݒ�Ώێ�� == "����"�̏ꍇ�A
            //�@@�����Ώۂ̗v�f.�L�[���
            //������ =
            //�@@�����Ώۂ̗v�f.�ݒ�Ώێ�� == "����"�̏ꍇ�A
            //�@@��������(*1).getDataSourceObject().������
            else if (WEB3TargetTypeDef.PRODUCT.equals(l_strTargetType))
            {
                l_tradeStopInfoUnit.productCode = l_strKey;
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                WEB3EquityProductManager l_productManager = (WEB3EquityProductManager) 
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
                try
                {
                    Product l_product = 
                        l_productManager.getProduct(l_institution, l_strKey);
                    EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow) l_product.getDataSourceObject();
                    l_tradeStopInfoUnit.productName = l_eqtypeProductRow.getStandardName();
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�����ɊY������f�[�^�����݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                        "WEB3AdminToDataManager." + STR_METHOD_NAME,
                        "�����ɊY������f�[�^�����݂��Ȃ��B");
                }
            }
            
            //��~���R = �����Ώۂ̗v�f.��~���R
            l_tradeStopInfoUnit.stopReason = l_triggerOrderStopList[i].getStopReason();
            
            //�L������From = �����Ώۂ̗v�f.�L������From
            l_tradeStopInfoUnit.expirationStartDate = 
                WEB3DateUtility.formatDate(l_triggerOrderStopList[i].getValidTermFrom(), "yyyyMMdd");
            
            //�L������To = �����Ώۂ̗v�f.�L������To
            l_tradeStopInfoUnit.expirationEndDate = 
                WEB3DateUtility.formatDate(l_triggerOrderStopList[i].getValidTermTo(), "yyyyMMdd");
            
            //�Q�|�R�j�@@������~�󋵂��i�[����ArrayList�𐶐�����B
            ArrayList l_lisUnits = new ArrayList();
            
            //�Q�|�S�j�@@�����Ώۂ̏����������(*2)�̗v�f�����A
            //�ȉ��̏�����Loop����B
            //�Q�|�S�|�P�j�@@������~�󋵂𐶐�����B
            WEB3AdminToOrderStopStateUnit l_succUnit = new WEB3AdminToOrderStopStateUnit();
            
            //�Q�|�S�|�Q�j�@@������~�󋵂Ɉȉ��̃v���p�e�B���Z�b�g����B
            //�����Ώۂ̗v�f���A
            //["�A������"�̏ꍇ]
            //�@@����������� = �����Ώۂ̗v�f
            //�@@��~�t���O =
            //�@@�@@�����Ώۂ̓��ꎷ�s�����戵��~Row.�A��������~�t���O
            TriggerOrderStopParams l_params = new TriggerOrderStopParams(l_triggerOrderStopList[i]);
            l_succUnit.triggerOrderType = WEB3TriggerOrderTypeDef.SUCC;
            l_succUnit.stopFlag = BooleanEnum.FALSE.intValue() != l_params.getSuccOrderStopFlag();

            //ArrayList�ɒǉ�����B
            l_lisUnits.add(l_succUnit);
            
            //������~�󋵂𐶐�����B
            WEB3AdminToOrderStopStateUnit l_ocoUnit = new WEB3AdminToOrderStopStateUnit();
            
            //["OCO����"�̏ꍇ]
            //�@@����������� = �����Ώۂ̗v�f
            //�@@��~�t���O =
            //�@@�@@�����Ώۂ̓��ꎷ�s�����戵��~Row.OCO������~�t���O
            l_ocoUnit.triggerOrderType = WEB3TriggerOrderTypeDef.OCO;
            l_ocoUnit.stopFlag = BooleanEnum.FALSE.intValue() != l_params.getOcoOrderStopFlag();
            
            //ArrayList�ɒǉ�����B
            l_lisUnits.add(l_ocoUnit);
            
            //������~�󋵂𐶐�����B
            WEB3AdminToOrderStopStateUnit l_ifdUnit = new WEB3AdminToOrderStopStateUnit();
            
            //["IFD����"�̏ꍇ]
            //�@@����������� = �����Ώۂ̗v�f
            //�@@��~�t���O =
            //�@@�@@�����Ώۂ̓��ꎷ�s�����戵��~Row.IFD������~�t���O
            l_ifdUnit.triggerOrderType = WEB3TriggerOrderTypeDef.IFD;
            l_ifdUnit.stopFlag = BooleanEnum.FALSE.intValue() != l_params.getIfdOrderStopFlag();
            
            //ArrayList�ɒǉ�����B
            l_lisUnits.add(l_ifdUnit);
            
            //������~�󋵂𐶐�����B
            WEB3AdminToOrderStopStateUnit l_stopUnit = new WEB3AdminToOrderStopStateUnit();
            
            //["�t�w�l����"�̏ꍇ]
            //�@@����������� = �����Ώۂ̗v�f
            //�@@��~�t���O =
            //�@@�@@�����Ώۂ̓��ꎷ�s�����戵��~Row.�t�w�l������~�t���O
            l_stopUnit.triggerOrderType = WEB3TriggerOrderTypeDef.STOP;
            l_stopUnit.stopFlag = BooleanEnum.FALSE.intValue() != l_params.getStopOrderStopFlag();
            
            //ArrayList�ɒǉ�����B
            l_lisUnits.add(l_stopUnit);
            
            //������~�󋵂𐶐�����B
            WEB3AdminToOrderStopStateUnit l_wlimitUnit = new WEB3AdminToOrderStopStateUnit();
            
            //["W�w�l����"�̏ꍇ]
            //�@@����������� = �����Ώۂ̗v�f
            //�@@��~�t���O =
            //�@@�@@�����Ώۂ̓��ꎷ�s�����戵��~Row.W�w�l������~�t���O
            l_wlimitUnit.triggerOrderType = WEB3TriggerOrderTypeDef.W_LlIMIT;
            l_wlimitUnit.stopFlag = BooleanEnum.FALSE.intValue() != l_params.getWlimitOrderStopFlag();

            //ArrayList�ɒǉ�����B
            l_lisUnits.add(l_wlimitUnit);
            
            //�Q�|�T�j�@@�戵��~���ɒ�����~�󋵂��Z�b�g����B
            //�@@�戵��~���.������~�󋵈ꗗ =
            //�@@�@@������~�󋵂��i�[����ArrayList.toArray()
            l_tradeStopInfoUnit.orderStopStateList = new WEB3AdminToOrderStopStateUnit[l_lisUnits.size()];
            l_lisUnits.toArray(l_tradeStopInfoUnit.orderStopStateList);
            
            //�Q�|�U�j�@@�߂�l���i�[����ArrayList�Ƀv���p�e�B�Z�b�g����
            //�@@�戵��~����ǉ�����B
            l_lisReturns.add(l_tradeStopInfoUnit);
        }
        
        //�R�j�@@�߂�l���i�[����ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3AdminToTradeStopInfoUnit[] l_trderStopStateUnits = new WEB3AdminToTradeStopInfoUnit[l_lisReturns.size()];
        l_lisReturns.toArray(l_trderStopStateUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_trderStopStateUnits;
    }

    /**
     * (create���ʌ�������������i�����󋵋敪�j)<BR>
     * �����󋵋敪�̌���������������쐬����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��null�̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@��������������C���X�^���X(�FString)�𐶐�����B <BR>
     * <BR>
     * �R�j�@@�p�����[�^.���N�G�X�g�f�[�^.����������ʁ�"�t�w�l"�̏ꍇ<BR>
     * �@@ <BR>
     * �@@�R�|�P�j�@@�ȉ��̏�������������������ɒǉ�����B<BR>
     *  �@@�@@ [�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�����x���G���["�̏ꍇ]<BR>
     * �@@�@@�@@�������������� += "and submit_order_delay_flag = ? " <BR>
     * <BR>
     * �@@�@@�@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��("������", "��������")�̏ꍇ]<BR>
     * <BR>
     * �@@�@@�@@�������������� += "and order_condition_type = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and request_type = ? "<BR>
     *   �@@�@@[��L�ȊO]<BR>
     * �@@�@@�@@�������������� += "and request_type = ? "  <BR>
     * <BR>
     * �S�j�@@�p�����[�^.���N�G�X�g�f�[�^.����������ʁ�"W�w�l"�̏ꍇ<BR>
     * <BR>
     * �@@�S�|�P�j�@@�ȉ��̏�������������������ɒǉ�����B<BR>
     * �@@�@@�@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�����x���G���["�̏ꍇ] <BR>
     * �@@�@@�@@�������������� += "and submit_order_delay_flag = ? " <BR>
     * �@@�@@�@@[��L�ȊO]<BR>
     * �@@�@@�@@�������������� += "and request_type = ? "<BR>
     * <BR>
     * �T�j�@@�쐬�������������������ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�����Ɖ�ʃ��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String createCommonQueryStringForTriggerOrderState(
        WEB3AdminToOrderRefRefCommonRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createCommonQueryStringForTriggerOrderState(WEB3AdminToOrderRefRefCommonRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminToDataManager" + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��null�̏ꍇ�Anull��ԋp����B
        String l_strTriggerOrderState = l_request.triggerOrderState;
        if (l_strTriggerOrderState == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@��������������C���X�^���X(�FString)�𐶐�����B
        String l_strWhere = new String();

        //�R�j�@@�p�����[�^.���N�G�X�g�f�[�^.����������ʁ�"�t�w�l"�̏ꍇ
        if (WEB3TriggerOrderTypeDef.STOP.equals(l_request.triggerOrderType))
        {
            //�@@�R�|�P�j�@@�ȉ��̏�������������������ɒǉ�����B
            //�@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�����x���G���["�̏ꍇ]
            //�@@�@@�������������� += "and submit_order_delay_flag = ? "
            if (WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR.equals(l_strTriggerOrderState))
            {
                l_strWhere += "and submit_order_delay_flag = ? ";
            }
            //�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��("������", "��������")�̏ꍇ�̂݁A
            //�������������� += "and order_condition_type = ? " + "and request_type = ? "
            else if (WEB3TriggerOrderStatusDef.ORDERING.equals(l_strTriggerOrderState)
                || WEB3TriggerOrderStatusDef.ORDER_COMPLETE.equals(l_strTriggerOrderState))
            {
                l_strWhere += "and order_condition_type = ? "
                    + "and request_type = ? ";
            }
            //�@@[��L�ȊO]
            //�@@�@@�������������� += "and request_type = ? "
            else
            {
                l_strWhere += "and request_type = ? ";
            }
        }

        //�S�j�@@�p�����[�^.���N�G�X�g�f�[�^.����������ʁ�"W�w�l"�̏ꍇ
        else if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_request.triggerOrderType))
        {
            //�@@�S�|�P�j�@@�ȉ��̏�������������������ɒǉ�����B
            //[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�����x���G���["�̏ꍇ]
            //�������������� += "and submit_order_delay_flag = ? "
            //[��L�ȊO]
            //�������������� += "and request_type = ? "
            if (WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR.equals(l_strTriggerOrderState))
            {
                l_strWhere += "and submit_order_delay_flag = ? ";
            }
            else
            {
                l_strWhere += "and request_type = ? ";
            }
        }

        //�T�j�@@�쐬�������������������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strWhere;
    }

    /**
     * (create���ʌ��������f�[�^�R���e�i�i�����󋵋敪�j)<BR>
     * �����󋵋敪�̌��������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��null�̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@ArrayList�𐶐�����B  <BR>
     * <BR>
     * �R�j�@@�p�����[�^.���N�G�X�g�f�[�^.����������ʁ�"�t�w�l"�̏ꍇ�A<BR>
     * �@@�ȉ��̏����𐶐�����ArrayList�ɒǉ�����B  <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�ҋ@@��"�̏ꍇ] <BR>
     * �@@�@@�E"DEFAULT"�i���N�G�X�g�^�C�v�j <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪���i"������"�j�̏ꍇ]<BR>
     * �@@�@@�E"�t�w�l"�i���������j <BR>
     * �@@�@@�E"�����T�[�o"�i���N�G�X�g�^�C�v�j<BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪���i"��������"�j�̏ꍇ]<BR>
     * �@@�@@�E"DEFAULT"�i���������j<BR>
     * �@@�@@�E"�����T�[�o"�i���N�G�X�g�^�C�v�j<BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�����R���G���["�̏ꍇ] <BR>
     * �@@�@@�E"�������s"�i���N�G�X�g�^�C�v�j <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�����x���G���["�̏ꍇ]<BR>
     *   �@@�E"�x������"�i�����x���t���O�j
     * <BR>
     * �S�j�@@�p�����[�^.���N�G�X�g�f�[�^.����������ʁ�"W�w�l"�̏ꍇ�A<BR>
     * �@@�ȉ��̏����𐶐�����ArrayList�ɒǉ�����B  <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�ҋ@@��"�̏ꍇ] <BR>
     * �@@�@@�E"DEFAULT"�i���N�G�X�g�^�C�v�j <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"������"�̏ꍇ]<BR>
     * �@@�@@�E"�����T�[�o"�i���N�G�X�g�^�C�v�j <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"��������"�̏ꍇ] <BR>
     * �@@�@@�E"�ؑ֊���"�i���N�G�X�g�^�C�v�j <BR>
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�����x���G���["�̏ꍇ]<BR>
     * �@@�@@�E"�x������"�i�����x���t���O�j
     * <BR>
     * �@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�X�g�b�v��������"�̏ꍇ]<BR>
     * �@@�@@�E"����"�i���N�G�X�g�^�C�v�j <BR>
     * <BR>
     * �T�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�����Ɖ�ʃ��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    public static String[] createCommonQueryDataContainerForTriggerOrderState(
        WEB3AdminToOrderRefRefCommonRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createCommonQueryDataContainerForTriggerOrderState(WEB3AdminToOrderRefRefCommonRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminToDataManager" + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��null�̏ꍇ�Anull��ԋp����B
        String l_strTriggerOrderState = l_request.triggerOrderState;
        if (l_strTriggerOrderState == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@ArrayList�𐶐�����B
        List l_lisQueryContainers = new ArrayList();

        //�R�j�@@�p�����[�^.���N�G�X�g�f�[�^.����������ʁ�"�t�w�l"�̏ꍇ�A
        if (WEB3TriggerOrderTypeDef.STOP.equals(l_request.triggerOrderType))
        {
            //[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�ҋ@@��"�̏ꍇ]
            //�@@�E"DEFAULT"�i���N�G�X�g�^�C�v�j
            if (WEB3TriggerOrderStatusDef.ORDER_WAITING.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3RequestTypeDef.DEFAULT);
            }

            //�@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪���i"������"�j�̏ꍇ]
            //�@@�@@�E"�t�w�l"�i���������j
            //�@@�@@�E"�����T�[�o"�i���N�G�X�g�^�C�v�j
            else if (WEB3TriggerOrderStatusDef.ORDERING.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
                l_lisQueryContainers.add(WEB3RequestTypeDef.QUOTE_SERVER);
            }

            //[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪���i"��������"�j�̏ꍇ]
            //�@@�@@�E"DEFAULT"�i���������j
            //�@@�@@�E"�����T�[�o"�i���N�G�X�g�^�C�v�j
            else if (WEB3TriggerOrderStatusDef.ORDER_COMPLETE.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3OrderingConditionDef.DEFAULT);
                l_lisQueryContainers.add(WEB3RequestTypeDef.QUOTE_SERVER);
            }

            //�@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�����R���G���["�̏ꍇ]
            //�@@�@@�E"�������s"�i���N�G�X�g�^�C�v�j
            else if (WEB3TriggerOrderStatusDef.ORDER_VALIDATE_ERROR.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3RequestTypeDef.ORDER_FAILURE);
            }

            //�@@�@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�����x���G���["�̏ꍇ]
            // �@@�E"�x������"�i�����x���t���O�j
            else if (WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(BooleanEnum.TRUE.intValue() + "");
            }
        }

        //�S�j�@@�p�����[�^.���N�G�X�g�f�[�^.����������ʁ�"W�w�l"�̏ꍇ�A
        //�@@�ȉ��̏����𐶐�����ArrayList�ɒǉ�����B
        else if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_request.triggerOrderType))
        {
            //�@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�ҋ@@��"�̏ꍇ]
            //�@@�@@�E"DEFAULT"�i���N�G�X�g�^�C�v�j
            if (WEB3TriggerOrderStatusDef.ORDER_WAITING.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3RequestTypeDef.DEFAULT);
            }

            //�@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"������"�̏ꍇ]
            //�@@�@@�E"�����T�[�o"�i���N�G�X�g�^�C�v�j
            else if (WEB3TriggerOrderStatusDef.ORDERING.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3RequestTypeDef.QUOTE_SERVER);
            }

            //�@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"��������"�̏ꍇ]
            //�@@�@@�E"�ؑ֊���"�i���N�G�X�g�^�C�v�j
            else if (WEB3TriggerOrderStatusDef.ORDER_COMPLETE.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3RequestTypeDef.TRANSFERED);
            }

            //�@@�@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�����x���G���["�̏ꍇ]
            //    �E"�x������"�i�����x���t���O�j
            else if (WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(BooleanEnum.TRUE.intValue() + "");
            }

            //�@@[�p�����[�^.���N�G�X�g�f�[�^.�����󋵋敪��"�X�g�b�v��������"�̏ꍇ]
            //�@@�@@�E"����"�i���N�G�X�g�^�C�v�j
            else if (WEB3TriggerOrderStatusDef.STOP_ORDER_INVALIDATION.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3RequestTypeDef.INVALIDATE);
            }
        }

        //�T�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B
        String[] l_strQueryContainers = new String[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_strQueryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;

    }

    /**
     * (get�������s����)<BR>
     * �����̏����ɊY�����锭�����s����Params��ԋp����B<BR>
     * <BR>
     * �P�j�@@�������s�����e�[�u��(rls_order_miss)��<BR>
     * �@@�@@�@@�����̏����Ō����������ʂ�ԋp����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@���s�敪�F�@@�p�����[�^.���s�敪(*1)<BR>
     * �@@�@@����ID�F�@@�p�����[�^.�����P��.����ID <BR>
     * �@@�@@�⏕����ID�F�@@�p�����[�^.�����P��.�⏕����ID <BR>
     * �@@�@@�����t�������^�C�v�F�@@�p�����[�^.����������� <BR>
     * �@@�@@����ID�F�@@�p�����[�^.�����P��.����ID <BR>
     * �@@�@@�����^�C�v�F�@@�p�����[�^.�����^�C�v <BR>
     * �@@�@@���m�敪�F�@@"�I�����C��" <BR>
     * <BR>
     * �@@(*1)�Ή�����p�����[�^��null�̏ꍇ�́A���������ɉ����Ȃ��B<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B  <BR>
     * �@@���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * �@@���������ʂ̃��R�[�h���������̏ꍇ�́A�쐬���t���ŐV�̃��R�[�h��ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_strOrderType - (�����������)<BR>
     * �����������<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * @@param l_strMissType - (���s�敪)<BR>
     * ���s�敪<BR>
     * @@return RlsOrderMissParams
     * @@throws WEB3BaseException
     */
    public static RlsOrderMissParams getRlsOrderMiss(
        OrderUnit l_orderUnit,
        String l_strOrderType,
        ProductTypeEnum l_productType,
        String l_strMissType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getRlsOrderMiss(OrderUnit, String, ProductTypeEnum, String)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisQueryContainers = new ArrayList();

        //���s�敪
        if (l_strMissType != null)
        {
            l_sbWhere.append("miss_type = ? and");
            l_lisQueryContainers.add(l_strMissType);
        }

        //����ID
        l_sbWhere.append(" account_id = ?");
        l_lisQueryContainers.add(l_orderUnit.getAccountId() + "");

        //�⏕����ID
        l_sbWhere.append(" and sub_account_id = ?");
        l_lisQueryContainers.add(l_orderUnit.getSubAccountId() + "");

        //�����t�������^�C�v
        l_sbWhere.append(" and oms_cond_order_type = ?");
        l_lisQueryContainers.add(l_strOrderType);

        //����ID
        l_sbWhere.append(" and order_id = ?");
        l_lisQueryContainers.add(l_orderUnit.getOrderId() + "");

        //�����^�C�v
        l_sbWhere.append(" and product_type = ?");
        l_lisQueryContainers.add(l_productType);

        //���m�敪
        l_sbWhere.append(" and detect_type = ?");
        l_lisQueryContainers.add(WEB3DetectTypeDef.ON_LINE);

        Object[] l_objQueryContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_objQueryContainers);

        List l_lisRows = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisRows = l_processor.doFindAllQuery(
                RlsOrderMissRow.TYPE,
                l_sbWhere.toString(),
                l_objQueryContainers);

        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminToDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminToDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@�������ʂ�ԋp����B
        //    ���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        RlsOrderMissRow l_rlsOrderMissRow = null;
        if (l_lisRows == null || l_lisRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //���������ʂ̃��R�[�h���������̏ꍇ
        else if (l_lisRows.size() > 1)
        {
            Timestamp l_tsCreated = null;
            RlsOrderMissRow l_row = null;
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                l_row = (RlsOrderMissRow)l_lisRows.get(i);
                if (l_tsCreated == null)
                {
                    l_tsCreated = l_row.getCreatedTimestamp();
                    l_rlsOrderMissRow = l_row;
                }
                else if (l_row.getCreatedTimestamp().compareTo(l_tsCreated) > 0)
                {
                    l_tsCreated = l_row.getCreatedTimestamp();
                    l_rlsOrderMissRow = l_row;
                }
            }
        }
        else
        {
            l_rlsOrderMissRow = (RlsOrderMissRow)l_lisRows.get(0);
        }

        RlsOrderMissParams l_rlsOrderMissParams =
            new RlsOrderMissParams(l_rlsOrderMissRow);
        log.exiting(STR_METHOD_NAME);
        return l_rlsOrderMissParams;
    }

}
@
