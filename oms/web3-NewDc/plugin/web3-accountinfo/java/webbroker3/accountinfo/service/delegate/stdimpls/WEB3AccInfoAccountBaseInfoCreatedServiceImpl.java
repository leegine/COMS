head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountBaseInfoCreatedServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���ڋq��{���쐬�T�[�r�XImpl(WEB3AccInfoAccountBaseInfoCreatedServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ����� (���u) �V�K�쐬
Revesion History : 2005/12/23 �A���� (���u) �d�l�ύXNo.071
Revesion History : 2006/02/08 ������ (���u) �c�a���C�A�E�gNo.012
Revesion History : 2006/02/23 ������ (���u) ���f��No.086
Revesion History : 2006/02/28 ������ (���u) ���f��No.089
Revesion History : 2006/05/16 ������ (���u)�y���o���z�d�l�ύX�E�c�a���C�A�E�gNo.096�̊֘A���C��
Revesion History : 2006/05/30 ��؍� (SCS)���f��No.108
Revesion History : 2006/06/30 ������ (���u) �d�l�ύX�Ǘ�No.116
Revesion History : 2006/09/07 �Ԑi�@@ (���u) �d�l�ύX�Ǘ�No.122
Revesion History : 2006/09/12 �Ԑi�@@ (���u) �d�l�ύX�Ǘ�No.126
Revesion History : 2006/10/9  ꎉ�   (���u) �d�l�ύX�Ǘ�No.124
Revesion History : 2006/10/30 ꎉ�   (���u) ���f��No.139
Revesion History : 2006/12/01 ���� (���u) �d�l�ύX�Ǘ�No.150
Revesion History : 2007/02/26 �g��i (���u) �d�l�ύX�Ǘ�No.200
Revesion History : 2007/02/26 �g��i (���u) �d�l�ύX�E���f��206
Revesion History : 2007/03/13 �g��i (���u) �d�l�ύX�Ǘ�No.211
Revesion History : 2007/03/16 �g��i (���u) �d�l�ύX�Ǘ�No.212
Revesion History : 2007/03/13 �g��i (���u) �d�l�ύX�Ǘ�No.208
Revesion History : 2007/04/03 �Ӑ� (���u) �d�l�ύX�Ǘ�No.213
Revesion History : 2007/04/19 �Ӑ� (���u) �d�l�ύX�Ǘ�No.214
Revesion History : 2007/06/21 ���� (SCS) �d�l�ύX�Ǘ�No.215
Revesion History : 2007/07/13 ���g (���u) �d�l�ύX�Ǘ�No.219
Revesion History : 2007/09/07 ���g (���u) �d�l�ύX�Ǘ�No.223
Revesion History : 2007/09/10 ���g (���u) �d�l�ύX�Ǘ�No.224
Revesion History : 2007/09/10 ���g (���u) �d�l�ύX�Ǘ�No.225
Revesion History : 2007/08/27 ���g (���u) �d�l�ύX�Ǘ�No.216
Revesion History : 2007/08/30 ���g (���u) �d�l�ύX�Ǘ�No.222
Revesion History : 2007/09/12 ���g (���u) �d�l�ύX�Ǘ�No.226
Revesion History : 2007/11/01 ���g (���u) �d�l�ύX�E���f��No.228
Revesion History : 2008/02/15 �g�C�� (���u) �d�l�ύX�E���f��No.229
Revesion History : 2008/05/20 �Ԑi�@@ (���u) �d�l�ύX�E���f��No.230 231 232 233
Revesion History : 2008/06/18 ���������@@ (SCS) �d�l�ύX�E���f��No.235 236 237
Revesion History : 2008/08/01 ������ (���u) �d�l�ύX�E���f��No.238
Revesion History : 2008/09/22 ���g (���u) �d�l�ύX�E���f��No.250,252
Revesion History : 2010/02/21 ���g (���u) �d�l�ύX�E���f��No.258,No.264
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultSortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FilterQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoAsset;

import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.WEB3AccInfoLockAccYAccRegisterRelease;
import webbroker3.accountinfo.WEB3AccInfoMaster;
import webbroker3.accountinfo.WEB3AccInfoMobileOfficeInfoRegist;
import webbroker3.accountinfo.data.CommCampaignAccHistoryRow;
import webbroker3.accountinfo.data.CommCampaignProductMstRow;
import webbroker3.accountinfo.define.WEB3AccInfoBankAccountRegiDef;
import webbroker3.accountinfo.define.WEB3AccInfoRegistTypeDef;
import webbroker3.accountinfo.define.WEB3AccInfoStateDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoStockLoanAccOpenDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoTaxTypeDef;
import webbroker3.accountinfo.define.WEB3CreateStateDivDef;
import webbroker3.accountinfo.define.WEB3IfoTradingRegistDef;
import webbroker3.accountinfo.define.WEB3MobileOfficeChangeStateDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo;
import webbroker3.accountinfo.message.WEB3AccInfoAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoBatoInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCfdAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCampaignInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCorporationInfo;
import webbroker3.accountinfo.message.WEB3AccInfoFxAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoInsiderInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressInfoUnit;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressTypeUnit;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMultiMailAddressInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMultiMailAddressList;
import webbroker3.accountinfo.message.WEB3AccInfoStockLoanAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoStockOptionInfo;
import webbroker3.accountinfo.message.WEB3AccInfoStopInfo;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoAccountBaseInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommissionCourseRegistInfoCreatedService;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FeqAccountRow;
import webbroker3.aio.data.FxAccountCodeDao;
import webbroker3.aio.data.FxAccountCodeRow;
import webbroker3.aio.data.FxAccountDao;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BankAccountRegiDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3CumulativeAccountDivDef;
import webbroker3.common.define.WEB3FinDelDivDef;
import webbroker3.common.define.WEB3ForeignSecAccOpenDiv;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3FxSystemDivDef;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3MngLockDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.define.WEB3TransferDivDef;
import webbroker3.common.define.WEB3ValidDivDef;
import webbroker3.common.define.WEB3YellowCustomerDef;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBizLogicProvider;
import webbroker3.gentrade.WEB3GentradeInsider;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.gentrade.data.AccountInfoMstParams;
import webbroker3.gentrade.data.AccountInfoMstRow;
import webbroker3.gentrade.data.AccountMailAddressRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.ExclusiveUseAccountDao;
import webbroker3.gentrade.data.ExclusiveUseAccountRow;
import webbroker3.gentrade.data.FTransFinInstitutionRow;
import webbroker3.gentrade.data.InsiderRow;
import webbroker3.gentrade.data.MailAssortmentRow;
import webbroker3.gentrade.data.StockOptionProductRow;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.data.TransferedFinInstitutionDao;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.point.data.OrixTradeBonusPlanDao;
import webbroker3.point.data.OrixTradeBonusPlanRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.xbruito.WEB3RuitoPositionManager;

/**
 * (���q�l���ڋq��{���쐬�T�[�r�XImpl)<BR>
 * ���q�l���ڋq��{���쐬�T�[�r�X�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoAccountBaseInfoCreatedServiceImpl implements WEB3AccInfoAccountBaseInfoCreatedService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoAccountBaseInfoCreatedServiceImpl.class);  
        
    /**
     * @@roseuid 418F39FD037A
     */
    public WEB3AccInfoAccountBaseInfoCreatedServiceImpl() 
    {
     
    }
    
    /**
     * (create�ڋq��{���)<BR>
     * �ڋq�I�u�W�F�N�g���A�ڋq��{��񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u���q�l���i�ڋq��{���jcreate�ڋq��{���v�Q�ƁB<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo
     * @@roseuid 415D15F402AD
     */
    public WEB3AccInfoAccountBaseInfo createAccountBaseInfo(WEB3GentradeMainAccount l_mainAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createAccountBaseInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }
        
        //�����ҏ����擾����B 
        WEB3AccInfoInsiderInfo[] l_insiderInfoes = createInsiderInfo(l_mainAccount);
        
        //�����ϑ��萔���R�[�X�ύX�\�������擾����B
        WEB3AccInfoCommissionCourseChangeInfo[] l_commissionCourseChangeInfoes = createEquityCommissionCourseRegistInfo(l_mainAccount);
        
        //��~�����擾����B
        WEB3AccInfoStopInfo l_stopInfo = createStopInfo(l_mainAccount);
        
        //�g�єԍ��Ζ�������擾����B 
        WEB3AccInfoMobileOfficeInfo l_mobileOfficeInfo = createMobileOfficeInfo(l_mainAccount);
        
        //��p�U������������擾����B 
        WEB3AccInfoAccountInfo l_exclusiveTransferAccountInfo = createExclusiveTransferAccountInfo(l_mainAccount);
        
        //�U����w����������擾����B 
        WEB3AccInfoAccountInfo l_transferAccountInfo = createTransferAccountInfo(l_mainAccount);

        //create�O�ݐU����w��������(�ڋq)
        WEB3AccInfoAccountInfo[] l_accInfoAccountInfos = this.createForeignTransferAccountInfo(l_mainAccount);

        //�d�q�������擾����B
        WEB3AccInfoBatoInfo l_batoInfo = createBatoInfo(l_mainAccount);
        
        //1.8create�ב֕ۏ؋��������(�ڋq)
        WEB3AccInfoFxAccountInfo[] l_fxAccInformationUnits = this.createFXAccInformationUnit(l_mainAccount);
        
        //1.9get�I���b�N�X��p�U�������(�ڋq)
        String l_strSubAccountCode = this.getOrixSubAccountCode(l_mainAccount);
        
        //1.10create�@@�l���(�ڋq)
        WEB3AccInfoCorporationInfo l_corporationInfo = this.createCorporationInfo(l_mainAccount);
        
        //1.11create�X�g�b�N�I�v�V�����������(�ڋq)
        WEB3AccInfoStockOptionInfo[] l_stockOptionInfos  = this.createStockOptionInfo(l_mainAccount);
        
        //1.12create�����L�����y�[�����(�ڋq)
        WEB3AccInfoCommissionCampaignInfo[] l_accInfoCommissionCampaignInfos  = this.createAccInfoCampaignInfo(l_mainAccount);

        //create�،��S�ۃ��[�������J�ݏ��(�ڋq)
        WEB3AccInfoStockLoanAccountInfo l_accInfoStockLoanAccountInfo =
            this.createStockLoanAccountInfo(l_mainAccount);

        //createCFD�������(�ڋq)
        WEB3AccInfoCfdAccountInfo[] l_accInfoCfdAccountInfos =
            this.createCFDAccountInfo(l_mainAccount);

        //create���[���A�h���X���(�ڋq : �ڋq)
        WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoUnits =
            this.createMailAddressInfo(l_mainAccount);

        //create���[����ʏ��(�ڋq : �ڋq)
        WEB3AccInfoMailAddressTypeUnit[] l_mailAddressTypeUnits =
            this.createMailAddressTypeInfo(l_mainAccount);

        //���O�C���h�c���擾����B
        long l_lngLoginId = l_mainAccount.getLoginId();
        log.debug("[���O�C���h�c] = " + l_lngLoginId);
        
        OpLoginAdminService l_opLoginAdminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
        
        Map l_loginAttr = l_opLoginAdminService.getLoginAttributes(l_lngLoginId);
        
        LoginInfo l_loginInfo = l_opLoginAdminService.getLoginInfo(l_lngLoginId);
        
        Map l_loginTypeAttr = l_opLoginAdminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
        
        //�p�X���[�h�X�V����
        Date l_loginPasswordUpdatedDate = null;
        String l_strDate = (String)l_loginAttr.get(WEB3LoginAttributeKeyDef.LAST_PWDCHANGE);
        if (l_strDate != null)
        {
            try
            {
                l_loginPasswordUpdatedDate = WEB3PasswordUtility.loginAttributeDateFormat.parse(l_strDate);
            }
            catch (ParseException l_ex)
            {
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        log.debug("[�p�X���[�h�X�V����] = " + l_loginPasswordUpdatedDate);
        
        //�v�Z�T�[�r�X���擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //�ϑ��萔���R�[�X�i���ݒl�j���擾����B    
        String l_strCommissionCourse = null;
        
        //1.13�iget���X�v���t�@@�����X�i�ڋq�j != 1�j�̏ꍇ�̂ݏ������{
        if (getBranchPreferences(l_mainAccount) == null 
            || (getBranchPreferences(l_mainAccount) != null 
            && getBranchPreferences(l_mainAccount).intValue() != 1))
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider = 
                (WEB3GentradeBizLogicProvider) l_finApp.getGlobalBizLogicProvider();
            
            //�L����
            TradingSystem l_tradingSystem = GtlUtils.getTradingSystem();
            Date l_datBizDate = l_tradingSystem.getBizDate();
            log.debug("[�L���� = ]" + l_datBizDate);
            
            l_strCommissionCourse = l_bizLogicProvider.getCommissionCourseDiv(
                l_mainAccount,                              //�ڋq�F�@@�ڋq
                WEB3CommisionProductCodeDef.LISTING_STOCK,  //�萔�����i�R�[�h�F�@@�萔�����i�R�[�h.��ꊔ��
                l_datBizDate                                //�L����
                );
        }
        log.debug("[�ϑ��萔���R�[�X�i���ݒl�j = ]" + l_strCommissionCourse);
        //1.15
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //create�����A�h���X���(Row)
        //[create�����A�h���X���()�Ɏw�肷�����]
        //�ڋq�s�F�@@�ڋq.getDataSourceObject()�̖߂�l
        WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo =
            this.createMultiMailAddressInfo(l_mainAccountRow);

        //�ݓ����W���[�����擾����B 
        TradingModule l_ruitoTradingModule = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        
        //�⏕�������擾����B 
        WEB3GentradeSubAccount l_subAccount;
        WEB3AccInfoAccountBaseInfo l_accountBaseInfo = new WEB3AccInfoAccountBaseInfo();
        if (WEB3CumulativeAccountDivDef.ESTABLISH.equals(l_mainAccountRow.getRuitoAccOpenDiv()))
        {
            try
            {
                l_subAccount = (WEB3GentradeSubAccount) l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

				Object[] l_bindVal = { ProductTypeEnum.RUITO };
				FilterQueryParamsSpec l_spec = new FilterQueryParamsSpec(" product_type = ? ", l_bindVal); 

                //�ݓ��c�����擾����B
                WEB3RuitoPositionManager l_ruitoPositionMgr = (WEB3RuitoPositionManager)l_ruitoTradingModule.getPositionManager();
                List l_lisRuitoAssets = l_ruitoPositionMgr.getAssets(
                	l_subAccount, 
					l_spec,
                	DefaultSortKeySpec.NULL_SORT_KEY_SPEC
                	);
            
                //�����ݓ��c���i�FAsset�j�̖������igetProduct().getStandardName()�j�̔z����擾����B
                List l_lisProductName = new ArrayList();
            
                if (l_lisRuitoAssets != null)
                {
                    int l_intSize = l_lisRuitoAssets.size();
                    for (int i = 0; i < l_intSize; i++)
                    {
                        //�@@�ȉ��̏����𖞂����ݓ��c���i�FAsset�j�̖������igetProduct().getStandardName()�j�̔z����擾����B
                        //�|�ݓ����������݂���igetProduct()�ɂăI�u�W�F�N�g���擾�ł���j
                    
                        RuitoAsset l_ruitoAsset = (RuitoAsset)l_lisRuitoAssets.get(i);
                        Product l_ruitoProduct = l_ruitoAsset.getProduct();
                    
                        if (l_ruitoProduct != null)
                        {
                            l_lisProductName.add(l_ruitoProduct.getStandardName());
                        }
                    }
                }
            
                String[] l_strRuitoProductNames = new String[l_lisProductName.size()];
                l_lisProductName.toArray(l_strRuitoProductNames);
                log.debug("[������ = ]" + l_strRuitoProductNames);
            
                //�����e�E�l�l�e�o�^�F�@@(*1) �Ő��������ݓ��������̔z��
                l_accountBaseInfo.ruitoRegist = l_strRuitoProductNames;
            

            }
            catch(NotFoundException l_ex)
            {
                l_accountBaseInfo.ruitoRegist = null;
            }

        }

        //�g�єԍ��E�Ζ�����ύX�\�������擾����B 
        WEB3AccInfoMobileOfficeInfoRegist l_mobileOfficeInfoRegist = 
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_mainAccount); 
            

        
        //�ڋq��{��񃁃b�Z�[�W�I�u�W�F�N�g�𐶐�����B 
        
        
        //======================================================
        //�ڋq��{��񃁃b�Z�[�W�I�u�W�F�N�g�v���p�e�B�ɁA�ȉ��̒ʂ�l���Z�b�g����B
        //======================================================
        l_accountBaseInfo.fxAccountInfoList = l_fxAccInformationUnits;
        // �O�����������ԍ��F�@@
        // ���i�ڋq�s.�O�������A�g�����J�݋敪 == �P�F�����J�݁j�̏ꍇ�̂݃Z�b�g
        //     �O�������ڋq�e�[�u�����ȉ��̏����œǂ݁A�擾�����s.�O�����������ԍ�
        //
        //�@@�@@�@@�@@           [����]
        //�@@�@@�@@�@@           �O�������ڋq�e�[�u��.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() And
        //�@@�@@�@@�@@           �O�������ڋq�e�[�u��.���X�R�[�h = �ڋq.getBranch().getBranchCode() And
        //�@@�@@�@@�@@           �O�������ڋq�e�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode()
        if (WEB3ForeignSecAccOpenDiv.OPEN.equals(l_mainAccountRow.getFeqConAccOpenDiv()))
        {
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException
        
                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" institution_code = ? ");     
                l_sbWhere.append(" and branch_code = ? ");      
                l_sbWhere.append(" and account_code = ? ");     
        
                Object[] l_objWhere = { 
                    l_mainAccount.getInstitution().getInstitutionCode(),   
                    l_mainAccount.getBranch().getBranchCode(),        //���X�R�[�h
                    l_mainAccount.getAccountCode()
                    };
                  
                List l_lstRecords = l_processor.doFindAllQuery(
                    FeqAccountRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    null,
                    l_objWhere
                    );
                
                if (l_lstRecords == null || l_lstRecords.isEmpty())
                {
                    throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName()+ STR_METHOD_NAME);                       
                }
                
                FeqAccountRow l_feqAccountRow = (FeqAccountRow)l_lstRecords.get(0);
                l_accountBaseInfo.fstkAccountCode = l_feqAccountRow.getFeqAccountCode();
            }
            catch (DataFindException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName()+ STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }  

        }
        //���X�R�[�h�F�@@�ڋq.getBranch().getBranchCode()
        l_accountBaseInfo.branchCode = l_mainAccount.getBranch().getBranchCode();
        log.debug("[���X�R�[�h = ]" + l_accountBaseInfo.branchCode);
        //�ڋq�R�[�h�F�@@�ڋq.get�\���ڋq�R�[�h()
        l_accountBaseInfo.accountCode = l_mainAccount.getDisplayAccountCode();
        log.debug("[�ڋq�R�[�h = ]" + l_accountBaseInfo.accountCode);
        //�ڋq���i�J�i�j�F�@@�ڋq�s.���O�i�c���P�j���ڋq���i�J�i�j�Ƃ��Ďg�p����B
        l_accountBaseInfo.accountNameKana = l_mainAccountRow.getFamilyNameAlt1();
        log.debug("[�ڋq���i�J�i�j = ]" + l_accountBaseInfo.accountNameKana);
        //�ڋq���i�����j�F�@@�ڋq�s.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p����B
        l_accountBaseInfo.accountName = l_mainAccountRow.getFamilyName();
        log.debug("[�ڋq���i�����j = ]" + l_accountBaseInfo.accountName);
        //���N�����N���F�@@�ڋq�s.���N�����N��
        l_accountBaseInfo.eraBorn = l_mainAccountRow.getEraBorn();
        log.debug("[���N�����N�� = ]" + l_accountBaseInfo.eraBorn);
        //���N�����F�@@�ڋq�s.���N����
        l_accountBaseInfo.bornDate = l_mainAccountRow.getBornDate();
        log.debug("[���N���� = ]" + l_accountBaseInfo.bornDate);
        //�X�֔ԍ��F�@@�ڋq�s.�X�֔ԍ�
        l_accountBaseInfo.zipCode = l_mainAccountRow.getZipCode();
        log.debug("[�X�֔ԍ� = ]" + l_accountBaseInfo.zipCode);
        //�Z���P�i�J�i�j�F�@@�ڋq�s.�Z���P�i�J�i�j
        l_accountBaseInfo.addressKana1 = l_mainAccountRow.getAddressLine1Kana();
        log.debug("[�Z���P�i�J�i�j = ]" + l_accountBaseInfo.addressKana1);
        //�Z���Q�i�J�i�j�F�@@�ڋq�s.�Z���Q�i�J�i�j
        l_accountBaseInfo.addressKana2 = l_mainAccountRow.getAddressLine2Kana();
        log.debug("[�Z���Q�i�J�i�j = ]" + l_accountBaseInfo.addressKana2);
        //�Z���R�i�J�i�j�F�@@�ڋq�s.�Z���R�i�J�i�j
        l_accountBaseInfo.addressKana3 = l_mainAccountRow.getAddressLine3Kana();
        log.debug("[�Z���R�i�J�i�j = ]" + l_accountBaseInfo.addressKana3);
        //�Z���P�i�����j�F�@@�ڋq�s�Z���P
        l_accountBaseInfo.address1 = l_mainAccountRow.getAddressLine1();
        log.debug("[�Z���P�i�����j = ]" + l_accountBaseInfo.address1);
        //�Z���Q�i�����j�F�@@�ڋq�s�Z���Q
        l_accountBaseInfo.address2 = l_mainAccountRow.getAddressLine2();
        log.debug("[�Z���Q�i�����j = ]" + l_accountBaseInfo.address2);
        //�Z���R�i�����j�F�@@�ڋq�s�Z���R
        l_accountBaseInfo.address3 = l_mainAccountRow.getAddressLine3();
        log.debug("[�Z���R�i�����j = ]" + l_accountBaseInfo.address3);
        //�d�b�ԍ��F�@@�ڋq�s.�d�b�ԍ�
        l_accountBaseInfo.telephone = l_mainAccountRow.getTelephone();
        log.debug("[�d�b�ԍ� = ]" + l_accountBaseInfo.telephone);
        //�g�єԍ��E�Ζ�����F�@@create�g�єԍ��E�Ζ�����()�̖߂�l
        l_accountBaseInfo.mobileOfficeInfo = l_mobileOfficeInfo;
        log.debug("[�g�єԍ��E�Ζ����� = ]" + l_accountBaseInfo.mobileOfficeInfo);
        //�g�єԍ��E�Ζ�����ύX��ԋ敪
        if (l_mobileOfficeInfoRegist == null)
        {
            //�|�i�g�єԍ��E�Ζ�����ύX�\��.get�g�єԍ��E�Ζ�����ύX�\��() == null�j�̏ꍇ�A0�F�\����
            l_accountBaseInfo.mobileOfficeChangeStateDiv = WEB3MobileOfficeChangeStateDivDef.REGIST_POSSIBLE;
        }
        else
        {
            if (!l_mobileOfficeInfoRegist.isDeciding())
            {
                //�|�i�g�єԍ��E�Ζ�����ύX�\��.get�g�єԍ��E�Ζ�����ύX�\��().is����m�F��() == false�j�̏ꍇ�A1�F�\����
                l_accountBaseInfo.mobileOfficeChangeStateDiv = WEB3MobileOfficeChangeStateDivDef.REGISTING;
            }
            else
            {
                //�|�i�g�єԍ��E�Ζ�����ύX�\��.get�g�єԍ��E�Ζ�����ύX�\��().is����m�F��() == true�j�̏ꍇ�A2�F�m�F��
                l_accountBaseInfo.mobileOfficeChangeStateDiv = WEB3MobileOfficeChangeStateDivDef.CONFIRMING;
            }
        }
        log.debug("[�g�єԍ��E�Ζ�����ύX��ԋ敪 = ]" + l_accountBaseInfo.mobileOfficeChangeStateDiv);
        //�����ҏ��F�@@create�����ҏ��()�̖߂�l
        l_accountBaseInfo.insiderList = l_insiderInfoes;
        
        //�����J�ݓ��F�@@�ڋq�s.�����o�^��
        l_accountBaseInfo.accountOpenDate = l_mainAccountRow.getAccountOpenDate();
        log.debug("[�����J�ݓ� = ]" + l_accountBaseInfo.accountOpenDate);
        //���҃R�[�h�F�@@�ڋq�s.���҃R�[�h�iSONAR�j
        l_accountBaseInfo.traderCode = l_mainAccountRow.getSonarTraderCode();
        log.debug("[���҃R�[�h = ]" + l_accountBaseInfo.traderCode);
        //�U����w������F�@@create�U����w��������()�̖߂�l
        l_accountBaseInfo.transferAccount = l_transferAccountInfo;
        //�O�ݐU����w������ꗗ�F�@@create�O�ݐU����w��������()�̖߂�l
        l_accountBaseInfo.foreignTransferAccountList = l_accInfoAccountInfos;
        
        //��p�U��������F�@@create��p�U����������()�̖߂�l
        l_accountBaseInfo.exclusiveTransferAccount = l_exclusiveTransferAccountInfo;
                
        //���[���A�h���X�F�@@�ڋq�s.email�A�h���X
        l_accountBaseInfo.mailAddress = l_mainAccountRow.getEmailAddress();
        log.debug("[���[���A�h���X = ]" + l_accountBaseInfo.mailAddress);
        //���[���A�h���X�X�V�����F�@@�ڋq�s.email�A�h���X�X�V����
        l_accountBaseInfo.mailAddressUpdateDate = l_mainAccountRow.getEmailLastUpdatedTimestamp();
        log.debug("[���[���A�h���X�X�V���� = ]" + l_accountBaseInfo.mailAddressUpdateDate);
        //������胁�[�����M�t���O�F�@@�i�ڋq�s.������胁�[�����M�t���O == BooleanEnum.TRUE�j�̏ꍇtrue�A�ȊOfalse�B
        l_accountBaseInfo.equityExecMailFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getEquityOrderExeMailFlag());
        log.debug("[������胁�[�����M�t���O = ]" + l_accountBaseInfo.equityExecMailFlag);
        //��������胁�[�����M�t���O�F�@@�i�ڋq�s.��������胁�[�����M�t���O == BooleanEnum.TRUE�j�̏ꍇtrue�A�ȊOfalse�B
        l_accountBaseInfo.equityUnExecMailFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getEquityOrderUnexecMailFlag());
        log.debug("[��������胁�[�����M�t���O = ]" + l_accountBaseInfo.equityUnExecMailFlag);      
        //�敨OP��胁�[�����M�t���O�F�@@�i�ڋq�s.�敨OP������胁�[�����M�t���O == BooleanEnum.TRUE�j�̏ꍇtrue�A�ȊOfalse�B
        l_accountBaseInfo.futOpExecMailFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getIfoOrderExecMailFlag());
        log.debug("[�敨OP��胁�[�����M�t���O = ]" + l_accountBaseInfo.futOpExecMailFlag);
        //�敨OP����胁�[�����M�t���O�F�@@�i�ڋq�s.�敨OP����胁�[�����M�t���O == BooleanEnum.TRUE�j�̏ꍇtrue�A�ȊOfalse�B
        l_accountBaseInfo.futOpUnExecMailFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getIfoOrderUnexecMailFlag());
        log.debug("[�敨OP����胁�[�����M�t���O = ]" + l_accountBaseInfo.futOpUnExecMailFlag);
        //�ē����[�����M�t���O�F�@@�i�ڋq�s.�ē����[�����M�t���O == BooleanEnum.TRUE�j�̏ꍇtrue�A�ȊOfalse�B
        l_accountBaseInfo.guidanceMailFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getInformationMailFlag());
        log.debug("[�ē����[�����M�t���O = ]" + l_accountBaseInfo.guidanceMailFlag);
        //���O�C���G���[�񐔁F�@@OpLoginAdminService.getLoginInfo().getFailureCount()
        l_accountBaseInfo.loginErrorCount = "" + l_loginInfo.getFailureCount();
        log.debug("[���O�C���G���[�� = ]" + l_accountBaseInfo.loginErrorCount);
        //�p�X���[�h�X�V�����F�@@OpLoginAdminService.getLoginAttrubutes().get(���O�C��������.�O��p�X���[�h�X�V���t�j
        l_accountBaseInfo.lPasswordUpdateDate = l_loginPasswordUpdatedDate;
        log.debug("[�p�X���[�h�X�V���� = ]" + l_accountBaseInfo.lPasswordUpdateDate);
        //�Ïؔԍ��X�V�����F�@@�ڋq�s.����p�X���[�h�X�V����
        l_accountBaseInfo.passwordUpdateDate = l_mainAccountRow.getTrPwdLastUpdateTimestamp();
        log.debug("[�Ïؔԍ��X�V���� = ]" + l_accountBaseInfo.passwordUpdateDate);
        //�Ïؔԍ��ύX�\�t���O
        String l_strTradingPwdEnv = (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);
        if (WEB3TradingPwdEnvDef.DEFAULT.equals(l_strTradingPwdEnv))
        {
            //�|�iOpLoginAdminService.getLoginTypeAttrubutes().get(������.����p�X���[�h�ݒ�j == 0�FDEFAULT�j�̏ꍇ�Afalse�B
            l_accountBaseInfo.passwordUpdateAbleFlag = false;
        } 
        else if (WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strTradingPwdEnv))
        {
            //�|�iOpLoginAdminService.getLoginTypeAttrubutes().get(������.����p�X���[�h�ݒ�j == 1�F����p�X���[�h�g�p�j�̏ꍇ�Atrue�B
            l_accountBaseInfo.passwordUpdateAbleFlag = true;
        }
        log.debug("[�Ïؔԍ��ύX�\�t���O = ]" + l_accountBaseInfo.passwordUpdateAbleFlag);
        //��~���F�@@create��~���()�̖߂�l
        l_accountBaseInfo.stopInfo = l_stopInfo;
        
        //�萔���R�[�X�F�@@�v�Z�T�[�r�X.get�ϑ��萔���R�[�X()�̖߂�l
        l_accountBaseInfo.commissionCourse = l_strCommissionCourse;
        log.debug("[�萔���R�[�X = ]" + l_accountBaseInfo.commissionCourse);
        //�萔���R�[�X�ύX�\�����ꗗ�F�@@create�����ϑ��萔���R�[�X�ύX�\�����()�̖߂�l
        l_accountBaseInfo.commissionCourseChangeInfoList = l_commissionCourseChangeInfoes;
        
        //�L�����t���O�F�@@�ڋq.is���A���q()�̖߂�l
        l_accountBaseInfo.chargedInfoFlag = l_mainAccount.isRealCustomer();
        log.debug("[�L�����t���O = ]" + l_accountBaseInfo.chargedInfoFlag);
        //�U����w����Z�@@�֓o�^�t���O�F�@@�ڋq.is�~�ݐU����i��s�����j�o�^�敪()�̖߂�l�B
        l_accountBaseInfo.transferFinancialInstitutionFlag = l_mainAccount.isJapaneseCurrencyBankAccountRegi();
        log.debug("[�U����w����Z�@@�֓o�^�t���O = ]" + l_accountBaseInfo.transferFinancialInstitutionFlag);  
        //�O�ݐU����w����Z�@@�֓o�^�t���O�F�@@�ڋq.is�O�ݐU����i��s�����j�o�^�敪() �̖߂�l�B
        l_accountBaseInfo.foreignTransferFinancialInstitutionFlag = l_mainAccount.isForeignCurrencyBankAccountRegi();
        log.debug("[�O�ݐU����w����Z�@@�֓o�^�t���O = ]" + l_accountBaseInfo.foreignTransferFinancialInstitutionFlag);
        //���x�M�p����o�^�t���O�F�@@�i�ڋq�s.���x�M�p��������J�݋敪 == 1�F�����J�݁j�̏ꍇtrue�A�ȊOfalse�B
        l_accountBaseInfo.marketMarginFlag = WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv());
        log.debug("[���x�M�p����o�^�t���O]" + l_accountBaseInfo.marketMarginFlag);
        //��ʐM�p����o�^�t���O�F�@@�i�ڋq�s.��ʐM�p��������J�݋敪 == 1�F�����J�݁j�̏ꍇtrue�A�ȊOfalse�B
        l_accountBaseInfo.institutionMarginFlag = WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv());
        log.debug("[��ʐM�p����o�^�t���O = ]" + l_accountBaseInfo.institutionMarginFlag);
        //�敨OP����o�^�F
        boolean l_blnFuturesOpen = l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES);
        log.debug("[l_blnFuturesOpen= ]" + l_blnFuturesOpen);
        boolean l_blnOptionOpen = l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION);
        log.debug("[l_blnOptionOpen = ]" + l_blnOptionOpen);
        
        if (!l_blnFuturesOpen && l_blnOptionOpen)
        {
            //�|�i�ڋqis�敨OP�����J�݁i�敨�^�I�v�V�����敪.�敨�j == false && 
            //   �ڋqis�敨OP�����J�݁i�敨�^�I�v�V�����敪.�I�v�V�����j == true�j�̏ꍇ�A1�F�o�^�ς݁iOP��������j
            l_accountBaseInfo.futOpTradeRegist = WEB3IfoTradingRegistDef.REGISTED_OPTION_BUY_TRADING;
        }
        else if (l_blnFuturesOpen && !l_blnOptionOpen)
        {
            //�|�i�ڋqis�敨OP�����J�݁i�敨�^�I�v�V�����敪.�敨�j == true && 
            //    �ڋqis�敨OP�����J�݁i�敨�^�I�v�V�����敪.�I�v�V�����j == false�j�̏ꍇ�A2�F�o�^�ς݁i�敨����j
            l_accountBaseInfo.futOpTradeRegist = WEB3IfoTradingRegistDef.REGISTED_FUTURES_TRADING;
        }
        else if (l_blnFuturesOpen && l_blnOptionOpen)
        {
            //�|�i�ڋqis�敨OP�����J�݁i�敨�^�I�v�V�����敪.�敨�j == true && 
            //   �ڋqis�敨OP�����J�݁i�敨�^�I�v�V�����敪.�I�v�V�����j == true�j�̏ꍇ�A3�F�o�^�ς݁i�敨�^OP����j
            l_accountBaseInfo.futOpTradeRegist = WEB3IfoTradingRegistDef.REGISTED_FUTURES_OPTION_TRADING;            
        }
        else
        {
            //�|�i�ڋqis�敨OP�����J�݁i�敨�^�I�v�V�����敪.�敨�j == false && 
            //   �ڋqis�敨OP�����J�݁i�敨�^�I�v�V�����敪.�I�v�V�����j == false�j�̏ꍇ�Anull
            l_accountBaseInfo.futOpTradeRegist = null;
        }
        log.debug("[�敨OP����o�^ = ]" + l_accountBaseInfo.futOpTradeRegist);


        //�������������敪�F
        l_accountBaseInfo.equityTaxType = getTaxType(l_mainAccountRow.getTaxType());
        log.debug("[�������������敪 = ]" + getTaxType(l_mainAccountRow.getTaxType()));        
        //�������������敪�i���N�j�F
        l_accountBaseInfo.equityTaxTypeNext = getTaxType(l_mainAccountRow.getTaxTypeNext());
        log.debug("[�������������敪�i���N�j= ]" + getTaxType(l_mainAccountRow.getTaxTypeNext()));
        //����������������J�ݓ��F�@@�ڋq�s.����������������J�ݓ�
        l_accountBaseInfo.equityCapitalGainTaxOpenDate = l_mainAccountRow.getEquitySpAccOpenDate();
        log.debug("[����������������J�ݓ� = ]" + l_mainAccountRow.getEquitySpAccOpenDate());
        //�M�p��������敪
        l_accountBaseInfo.marginTaxType = getTaxType(l_mainAccountRow.getMarginTaxType());
        log.debug("[�M�p��������敪 = ]" + getTaxType(l_mainAccountRow.getMarginTaxType()));
        //�M�p��������敪�i���N�j
        l_accountBaseInfo.marginTaxTypeNext = getTaxType(l_mainAccountRow.getMarginTaxTypeNext());
        log.debug("[�M�p��������敪�i���N�j = ]" + getTaxType(l_mainAccountRow.getMarginTaxTypeNext()));
        //�M�p�����������J�ݓ��F�@@�ڋq�s.�M�p�����������J�ݓ�
        l_accountBaseInfo.marginCapitalGainTaxOpenDate = l_mainAccountRow.getMarginSpAccOpenDate();
        log.debug("[�M�p�����������J�ݓ� = ]" + l_mainAccountRow.getMarginSpAccOpenDate());
        //�d�q�����F�@@create�d�q�����()�̖߂�l
        l_accountBaseInfo.batoStatus = l_batoInfo;
        log.debug("[�d�q����� = ]" + l_batoInfo);
        log.exiting(STR_METHOD_NAME);
        log.debug("[l_accountBaseInfo = ]" + l_accountBaseInfo);
        //�I���b�N�X��p�U��������F�@@get�I���b�N�X��p�U�������()�̖߂�l
        l_accountBaseInfo.orixSubAccCode = l_strSubAccountCode;
        //�ݓ������J�݋敪�F�@@�ڋq�s.�ݓ������J�݋敪
        l_accountBaseInfo.ruitoAccountOpenDiv = l_mainAccountRow.getRuitoAccOpenDiv();
        //�O���،������J�݋敪�F�@@�ڋq�s.�O���،������J�݋敪
        l_accountBaseInfo.foreignSecAccOpenDiv = l_mainAccountRow.getForeignSecAccOpenDiv();
        //����Ǘ������J�݋敪�F�@@�ڋq�s.����Ǘ������J�݋敪
        l_accountBaseInfo.capitalGainTaxAccOpenDiv = l_mainAccountRow.getSpMngAccOpenDiv();
        //MRF�����J�݋敪�F�@@�ڋq�s.MRF�����J�݋敪
        l_accountBaseInfo.mrfAccOpenDiv = l_mainAccountRow.getMrfAccOpenDiv();
        //�����^�C�v�F�@@�ڋq�s.�����^�C�v
        l_accountBaseInfo.accountType = "" + l_mainAccountRow.getAccountType().intValue();
        if (l_mobileOfficeInfoRegist != null)
        {
            //�ڋq���ύX�敪1�F�@@�g�єԍ��E�Ζ�����ύX�\��.get����m�F���t���O()
            l_accountBaseInfo.accountChangeDiv1 = l_mobileOfficeInfoRegist.getDecisionFlag();
            //�ڋq���ύX�敪2�F�@@�g�єԍ��E�Ζ�����ύX�\��.get���茋��()
            l_accountBaseInfo.accountChangeDiv2 = l_mobileOfficeInfoRegist.getDecision();
            //�ڋq���ύX�敪3�F�@@�g�єԍ��E�Ζ�����ύX�\��.get�폜�t���O()
            l_accountBaseInfo.accountChangeDiv3 = l_mobileOfficeInfoRegist.getDeleteFlag();
        }

        //�������}�X�^�I�u�W�F�N�g���擾����B 
        WEB3AccInfoMaster l_accInfoMaster = WEB3AccInfoMaster.getAccInfoMaster(l_mainAccount);
        if(l_accInfoMaster != null)
        {
            AccountInfoMstRow l_accountInfoMstRow = (AccountInfoMstRow)l_accInfoMaster.getDataSourceObject();
            //�E�ƁF�@@�������}�X�^.get�������}�X�^().getDataSourceObject.�E�Ƌ敪
            l_accountBaseInfo.occupationDiv = l_accountInfoMstRow.getOccupationDiv();
            //���ЁF�@@�������}�X�^.get�������}�X�^().getDataSourceObject.����
            l_accountBaseInfo.nationality = l_accountInfoMstRow.getNationality();
            //���Ђ��̑��F�@@�������}�X�^.get�������}�X�^().getDataSourceObject.���Ђ��̑�
            l_accountBaseInfo.nationalityOther = l_accountInfoMstRow.getNationalityOther();
            //�ڋq��������1�F�@@�������}�X�^.get�������}�X�^().getDataSourceObject.�ڋq��������1
            l_accountBaseInfo.accountRealName1 = l_accountInfoMstRow.getRealName1();
            //�ڋq��������2�F�@@�������}�X�^.get�������}�X�^().getDataSourceObject.�ڋq��������2
            l_accountBaseInfo.accountRealName2 = l_accountInfoMstRow.getRealName2();
        }
        
        //�@@�l���F�@@create�@@�l���()�̖߂�l
        l_accountBaseInfo.corporationInfo =  l_corporationInfo;
        
        //�X�g�b�N�I�v�V���������J�݋敪�F�@@�ڋq�s.�X�g�b�N�I�v�V���������J�݋敪
        l_accountBaseInfo.stockOptionAccOpenDiv = l_mainAccountRow.getStockOptionAccOpenDiv();
        
        //�X�g�b�N�I�v�V���������J�ݏ��ꗗ�F�@@create�X�g�b�N�I�v�V�����������()�̖߂�l
        l_accountBaseInfo.stockOptionAccOpenList = l_stockOptionInfos;
        
        //�،��S�ۃ��[���敪�F�@@�ڋq�s.�،��S�ۃ��[���敪
        l_accountBaseInfo.comStockLoanDiv = l_mainAccountRow.getSecuredLoanSecAccOpenDiv();

        //���ʁF�@@�ڋq�s.����
        l_accountBaseInfo.sex = l_mainAccountRow.getSex();

        //�萔�������L�����y�[����� = create�����L�����y�[�����i�j�̖߂�l
        l_accountBaseInfo.commissionCampaignInfoList = l_accInfoCommissionCampaignInfos;

        //���o�C����p�����J�݋敪�F�@@�ڋq�s.���o�C����p�����J�݋敪
        l_accountBaseInfo.mobileAccountDiv = l_mainAccountRow.getOnlyMobileOpenDiv();

        l_accountBaseInfo.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;

        //�،��S�ۃ��[�������J�ݏ��F�@@create�،��S�ۃ��[�������J�ݏ��()�̖߂�l
        l_accountBaseInfo.stockLoanAccountInfo = l_accInfoStockLoanAccountInfo;

        //�v���A�}�敪�F�@@�ڋq�s.�v���A�}�敪
        l_accountBaseInfo.proAmDiv = l_mainAccountRow.getProamDiv();

        //PTS�����J�݋敪�F�@@�ڋq�s.PTS�����J�݋敪
        l_accountBaseInfo.ptsAccOpenDiv = l_mainAccountRow.getPtsAccOpenDiv();

        //�����@@�F �ڋq�s.�����@@
        l_accountBaseInfo.broadcastLaw = l_mainAccountRow.getBroadcastLaw();

        //�q��@@�F �ڋq�s.�q��@@
        l_accountBaseInfo.aviationLaw = l_mainAccountRow.getAviationLaw();

        //�m�s�s�@@�F �ڋq�s.NTT�@@
        l_accountBaseInfo.nttLaw = l_mainAccountRow.getNttLaw();

        //�z�����U���w��敪�F �ڋq�s.�z�����U���w��敪
        l_accountBaseInfo.dividendTransDesignate = l_mainAccountRow.getDividendTransDesignate();

        //��C�㗝�l�F �ڋq�s.��C�㗝�l
        l_accountBaseInfo.standingProxy = l_mainAccountRow.getStandingProxy();

        //�@@��㗝�l�F �ڋq�s.�@@��㗝�l
        l_accountBaseInfo.statutoryAgent = l_mainAccountRow.getStatutoryAgent();

        //�����Ҍ����ԍ��F �ڋq�s.�����Ҍ����ԍ�
        l_accountBaseInfo.affiliateAccountCode = l_mainAccountRow.getAffiliateAccountCode();

        //�@@�\�ʒm�����敪�F �ڋq�s.�@@�\�ʒm�����敪
        l_accountBaseInfo.agencyNotifyCmpDiv = l_mainAccountRow.getAgencyNotifyCmpDiv();

        //CFD�������[]�F�@@createCFD�������()�̖߂�l
        l_accountBaseInfo.cfdAccountInfoList = l_accInfoCfdAccountInfos;

        WEB3AccInfoMultiMailAddressList l_multiMailAddressList =
            new WEB3AccInfoMultiMailAddressList();
        l_multiMailAddressList.mailAddressInfoList =l_mailAddressInfoUnits;
        l_multiMailAddressList.mailTypeInfoList = l_mailAddressTypeUnits;
        l_accountBaseInfo.multiMailAddressList = l_multiMailAddressList;
        return l_accountBaseInfo;

    }
    
    /**
     * (create�����ҏ��)<BR>
     * �ڋq�I�u�W�F�N�g���A�����ҏ�񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j�@@�����҃I�u�W�F�N�g���擾����B<BR>
     * �@@������.get������()�ɂāA�����҃I�u�W�F�N�g�̔z����擾����B<BR>
     * �@@�擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �@@[get������()�Ɏw�肷�����]<BR>
     * �@@�ڋq�F�@@�ڋq<BR>
     * <BR>
     * �Q�j�@@�����v���_�N�g�}�l�[�W���擾<BR>
     * �@@��FinApp.getTradingModule(ProductTypeEnum.����<BR>)
     * .getProductManager()�ɂĎ擾����B<BR>
     * <BR>
     * �R�j�@@�����ҏ��ҏW<BR>
     * �@@�P�j�̖߂�l�̗v�f���ɂR�|�P�j�`�R�|�T�j���J��Ԃ��B<BR>
     * <BR>
     * �@@�R�|�P�j�@@���������I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�����v���_�N�g�}�l�[�W��.getProduct()�ɂĊ��������I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@[getProduct()�Ɏw�肷�����]<BR>
     * �@@�@@�����h�c�F�@@������.�����h�c<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�����ҏ��I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�R�|�R�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g���ԋp����B<BR>
     * <BR>
     * �@@�@@�����ҏ��.�����R�[�h = ��������.getProductCode()<BR>
     * �@@�@@�����ҏ��.������ = ��������.getStandardName()<BR>
     * �@@�@@�����ҏ��.�֌W�R�[�h = ������.�֌W�R�[�h<BR>
     * �@@�@@�����ҏ��.������ = �����҃}�X�^�s.������<BR>
     * �@@�@@�����ҏ��.��E�� = �����҃}�X�^�s.��E��<BR>
     * �@@�@@�����ҏ��,�o�^�󋵋敪 = �����҃}�X�^�s.�o�^�󋵋敪<BR>
     * <BR>
     * �S�j�@@�������������ҏ���z��ŕԋp����B<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoInsiderInfo[]
     * @@roseuid 415D1B450145
     */
    protected WEB3AccInfoInsiderInfo[] createInsiderInfo(WEB3GentradeMainAccount l_mainAccount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createInsiderInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        //�����҃I�u�W�F�N�g���擾����B
        WEB3GentradeInsider[] l_insiders = WEB3GentradeInsider.getInsider(l_mainAccount);
        
        //�擾�ł��Ȃ������ꍇ�́Anull��ԋp����B 
        if (l_insiders == null || l_insiders.length == 0)
        {
            return null;
        }
        
        WEB3AccInfoInsiderInfo[] l_insiderInfoes = new WEB3AccInfoInsiderInfo[l_insiders.length];
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_equityTradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        //�����v���_�N�g�}�l�[�W���擾 
        WEB3EquityProductManager l_equityProductMgr = (WEB3EquityProductManager)l_equityTradingModule.getProductManager();
        
        //�����ҏ��ҏW
        for (int i = 0; i < l_insiders.length; i++)
        {
            WEB3GentradeInsider l_insider = l_insiders[i];
            InsiderRow l_insiderRow = (InsiderRow)l_insider.getDataSourceObject();
            
            //���������I�u�W�F�N�g���擾����B
            WEB3EquityProduct l_equityProduct;
            try
            {
                l_equityProduct = (WEB3EquityProduct) l_equityProductMgr.getProduct(l_insiderRow.getProductId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            EqtypeProductRow l_productRow = (EqtypeProductRow)l_equityProduct.getDataSourceObject();
            
            //�����ҏ��I�u�W�F�N�g�𐶐�����B 
            l_insiderInfoes[i] = new WEB3AccInfoInsiderInfo();
            
            //�����ҏ��.�����R�[�h = ��������.getProductCode()
            l_insiderInfoes[i].productCode = l_equityProduct.getProductCode();
            
            //�����ҏ��.������ = ��������.getStandardName()
            l_insiderInfoes[i].productName = l_productRow.getStandardName();
            
            //�����ҏ��.�֌W�R�[�h = ������.�֌W�R�[�h
            l_insiderInfoes[i].relationCode = l_insider.getRelationDiv();
            
            //�����ҏ��.������ = �����҃}�X�^�s.������
            l_insiderInfoes[i].executive =  l_insiderRow.getOfficerName();
            
            //�����ҏ��.��E�� = �����҃}�X�^�s.��E��
            l_insiderInfoes[i].position =  l_insiderRow.getPostName();
             
            //�����ҏ��,�o�^�󋵋敪 = �����҃}�X�^�s.�o�^�󋵋敪
            l_insiderInfoes[i].registStateDiv = l_insiderRow.getRegistDiv();
            
        }
                
        log.exiting(STR_METHOD_NAME);
        return l_insiderInfoes;
    }
    
    /**
     * (create�����ϑ��萔���R�[�X�ύX�\�����)<BR>
     * �ڋq�I�u�W�F�N�g���A�萔���R�[�X�ύX�\����񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j�@@�ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g���擾����B<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��.get�ϑ��萔���R�[�X�ύX�\��()�ɂāA<BR>
     * �ϑ��萔���ύX�\���I�u�W�F�N�g���擾����B<BR>
     * �@@�擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �@@[get�ϑ��萔���R�[�X�ύX�\��()�Ɏw�肷�����]<BR>
     * �@@�ڋq�F�@@�ڋq<BR>
     * �@@�萔�����i�R�[�h�F�@@�萔�����i�R�[�h.�h��ꊔ���h<BR>
     * <BR>
     * �Q�j�@@�萔���R�[�X�ύX�\�����쐬<BR>
     * �@@���q�l���ϑ��萔���R�[�X�ύX�\�����쐬�T�[�r�X���擾���A<BR>
     * �@@create�萔���R�[�X�ύX�\�����()���\�b�h���R�[�����A�߂�l��ԋp����B<BR>
     * <BR>�@@
     * �@@[create�萔���R�[�X�ύX�\�����()�Ɏw�肷�����]<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��[]�F�@@get�ϑ��萔���ύX�\��()�̖߂�l<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo[]
     * @@roseuid 415D1B810145
     */
    protected WEB3AccInfoCommissionCourseChangeInfo[] createEquityCommissionCourseRegistInfo(WEB3GentradeMainAccount l_mainAccount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createEquityCommissionCourseRegistInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        //�ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g���擾����B 
        WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegists = 
            WEB3AccInfoCommissionCourseRegist.getCommissionCourseRegist(l_mainAccount, WEB3CommisionProductCodeDef.LISTING_STOCK);
        
        //�擾�ł��Ȃ������ꍇ�́Anull��ԋp����B 
        if (l_commissionCourseRegists == null || l_commissionCourseRegists.length == 0)
        {
            return null;
        }
                
        //���q�l���ϑ��萔���R�[�X�ύX�\�����쐬�T�[�r�X���擾        
        WEB3AccInfoCommissionCourseRegistInfoCreatedService l_service = 
            (WEB3AccInfoCommissionCourseRegistInfoCreatedService)Services.getService(WEB3AccInfoCommissionCourseRegistInfoCreatedService.class);
        
        WEB3AccInfoCommissionCourseChangeInfo[] l_commissionCourseChangeInfoes = 
            l_service.createCommissionCourseRegistInfo(l_commissionCourseRegists);
        
        log.exiting(STR_METHOD_NAME);
        return l_commissionCourseChangeInfoes;        
    }
    
    /**
     * (create��~���)<BR>
     * �ڋq�I�u�W�F�N�g���A��~��񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j�@@��~���I�u�W�F�N�g�𐶐�����B<BR>
     * �Q�j�@@�ڋq.getDataSourceObject()�ɂČڋq�s�I�u�W�F�N�g���擾����B<BR>
     * �R�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �@@��~���.�x�q�敪 = �ڋq�s.�x�q�敪<BR>
     * �@@��~���.�Ǘ����b�N�敪 = �ڋq�s.�Ǘ����b�N�敪<BR>
     * �@@��~���.�Ǘ����b�N���R�t���O�i���֋��j = <BR>
     * �@@�@@�|�i�ڋq�s.�Ǘ����b�N���R�t���O�i���֋��j == <BR>
     * BooleanEnum.TRUE�j�̏ꍇ�Atrue�B<BR>
     * �@@�@@�|�i�ڋq�s.�Ǘ����b�N���R�t���O�i���֋��j == <BR>
     * BooleanEnum.FALSE�j�̏ꍇ�Afalse�B<BR>
     * �@@��~���.�Ǘ����b�N���R�t���O�i�ۏ؋������j = <BR>
     * �@@�@@�|�i�ڋq�s.�Ǘ����b�N���R�t���O�i�ۏ؋������j == <BR>
     * BooleanEnum.TRUE�j�̏ꍇ�Atrue�B<BR>
     * �@@�@@�|�i�ڋq�s.�Ǘ����b�N���R�t���O�i�ۏ؋������j == <BR>
     * BooleanEnum.FALSE�j�̏ꍇ�Afalse�B<BR>
     * �@@��~���.�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j = <BR>
     * �@@�@@�|�i�ڋq�s.�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j == <BR>
     * BooleanEnum.TRUE�j�̏ꍇ�Atrue�B<BR>
     * �@@�@@�|�i�ڋq�s.�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j == <BR>
     * BooleanEnum.FALSE�j�̏ꍇ�Afalse�B<BR>
     * �@@��~���.�Ǘ����b�N���R�t���O�i�a��ؒ��������ցj = <BR>
     * �@@�@@�|�i�ڋq�s.�Ǘ����b�N���R�t���O�i�a��ؒ��������ցj == <BR>
     * BooleanEnum.TRUE�j�̏ꍇ�Atrue�B<BR>
     * �@@�@@�|�i�ڋq�s.�Ǘ����b�N���R�t���O�i�a��ؒ��������ցj == <BR>
     * BooleanEnum.FALSE�j�̏ꍇ�Afalse�B<BR>
     * �@@��~���.�Ǘ����b�N�����J�n�� = �ڋq�s.�Ǘ����b�N�����J�n��<BR>
     * �@@��~���.�Ǘ����b�N�����I���� = �ڋq�s.�Ǘ����b�N�����I����<BR>
     * �@@��~���.�x�X���b�N�敪 = �ڋq�s.�x�X���b�N�敪<BR>
     * �@@��~���.�����F�敪 = �ڋq�s.�����F�敪<BR>
     * �@@��~���.�󋵋敪 = �����~�����̏ꍇ�A1�F�֎~���B<BR>
     * �ȊO�A0�FDEFAULT�i�֎~���łȂ��j�B<BR>
     * <BR>
     * �@@��~���.��~�󋵓o�^���R = �ڋq�s.���b�N�o�^���R�B <BR>
     * �@@��~���.�o�^���� =  <BR>
     * �@@�@@�|�i�ڋq�s.Y�q�敪==Y�q or �ڋq�s.�Ǘ����b�N==���b�N or �ڋq�s.�x�X���b�N==���b�N�j�̏ꍇ�A�ڋq�s.��~�󋵍X�V�����B<BR> 
     * �@@�@@�|�ȊO�Anull�B <BR>
     * �@@��~���.���b�N�q�o�^����SONAR��t�� = <BR>
     * �@@�@@�|�i���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*1) == null�j�̏ꍇ�Anull�B<BR> 
     * �@@�@@�|�ȊO�A���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*1).get�����敪()�B<BR> 
     * �@@��~���.�x�q�o�^����SONAR��t�� = <BR>
     * �@@�@@�|�i���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*2) == null�j�̏ꍇ�Anull�B <BR>
     * �@@�@@�|�ȊO�A���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*2).get�����敪()�B <BR>
     * �@@�@@[get���b�N�q�x�q�o�^����()�Ɏw�肷�����] <BR> 
     * �@@�@@�ڋq�F�@@�ڋq <BR>
     * �@@�@@�f�[�^�R�[�h�F�@@(*1) �hGI846�h:���b�N�q�o�^���� �^ (*2) �hGI847�h:Y�q�o�^���� <BR>
     * <BR>
     * �@@�@@���@@�����~���̔���<BR>
     * �@@�@@�P�j�@@FinApp.getCommonOrderValidator()�ɂāA<BR>
     * �����`�F�b�N�C���X�^���X���擾����B
     * �@@�@@�Q�j�@@�����`�F�b�N.validate����\�ڋq()�ɂāA<BR>
     * �G���[���ԋp���ꂽ�ꍇ�͎����~���Ɣ��f����B
     * <BR>
     * �@@�@@�@@�@@�@@[validate����\�ڋq()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�ڋq�F�@@�ڋq<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoStopInfo
     * @@roseuid 415D1BD00107
     */
    public WEB3AccInfoStopInfo createStopInfo(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createStopInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoStopInfo l_stopInfo = new WEB3AccInfoStopInfo();
        
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //��~���.�x�q�敪 = �ڋq�s.�x�q�敪
        l_stopInfo.yellowAccountDiv = l_mainAccountRow.getYellowCustomer();
        
        //��~���.�Ǘ����b�N�敪 = �ڋq�s.�Ǘ����b�N�敪
        l_stopInfo.mngLockDiv = l_mainAccountRow.getMngLockFlag();
     
        //��~���.�Ǘ����b�N���R�t���O�i���֋��j = 
        //  �|�i�ڋq�s.�Ǘ����b�N���R�t���O�i���֋��j == BooleanEnum.TRUE�j�̏ꍇ�Atrue�B 
        //  �|�i�ڋq�s.�Ǘ����b�N���R�t���O�i���֋��j == BooleanEnum.FALSE�j�̏ꍇ�Afalse�B
        l_stopInfo.mngExpenseLockReasonFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getMngLockFlagAdvance());

        //��~���.�Ǘ����b�N���R�t���O�i�ۏ؋������j = 
        //  �|�i�ڋq�s.�Ǘ����b�N���R�t���O�i�ۏ؋������j == BooleanEnum.TRUE�j�̏ꍇ�Atrue�B 
        //  �|�i�ڋq�s.�Ǘ����b�N���R�t���O�i�ۏ؋������j == BooleanEnum.FALSE�j�̏ꍇ�Afalse�B
        l_stopInfo.mngDepositLockReasonFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getMngLockFlagUnpayMargin());
        
        //��~���.�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j = 
        //  �|�i�ڋq�s.�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j == BooleanEnum.TRUE�j�̏ꍇ�Atrue�B 
        //  �|�i�ڋq�s.�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j == BooleanEnum.FALSE�j�̏ꍇ�Afalse�B
        l_stopInfo.mngCollateralLockReasonFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getMngLockFlagShortSecurity());
                         
        //��~���.�Ǘ����b�N���R�t���O�i�a��ؒ��������ցj = 
        //  �|�i�ڋq�s.�Ǘ����b�N���R�t���O�i�a��ؒ��������ցj == BooleanEnum.TRUE�j�̏ꍇ�Atrue�B 
        //  �|�i�ڋq�s.�Ǘ����b�N���R�t���O�i�a��ؒ��������ցj == BooleanEnum.FALSE�j�̏ꍇ�Afalse�B
        l_stopInfo.mngReceiptLockReasonFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getMngLockFlagUnsubstitDepo());
                 
        //��~���.�Ǘ����b�N�����J�n�� = �ڋq�s.�Ǘ����b�N�����J�n��
        l_stopInfo.mngLockCancelStartDate = l_mainAccountRow.getMngLockOffStartDate();
         
        //��~���.�Ǘ����b�N�����I���� = �ڋq�s.�Ǘ����b�N�����I����
        l_stopInfo.mngLockCancelEndDate = l_mainAccountRow.getMngLockOffEndDate();
        
        //��~���.�x�X���b�N�敪 = �ڋq�s.�x�X���b�N�敪
        l_stopInfo.branchLockDiv = l_mainAccountRow.getBranchLock();
        
        //��~���.�����F�敪 = �ڋq�s.�����F�敪
        l_stopInfo.orderPermitDiv = l_mainAccountRow.getOrderPermission();
        
        //��~���.��~�󋵓o�^���R = �ڋq�s.���b�N�o�^���R�B
        l_stopInfo.stopStateRegistReason = l_mainAccountRow.getLockRegistrationReason();
        
        /*
         * ��~���.�o�^���� =  <BR>
         * �@@�@@�|�i�ڋq�s.Y�q�敪==Y�q or �ڋq�s.�Ǘ����b�N==���b�N or
         *     �ڋq�s.�x�X���b�N==���b�N�j�̏ꍇ�A�ڋq�s.��~�󋵍X�V�����B<BR> 
         * �@@�@@�|�ȊO�Anull�B <BR>
         */
        if (WEB3YellowCustomerDef.YELLOW_CUSTOMER.equals(l_mainAccountRow.getYellowCustomer()) 
                || WEB3MngLockDef.LOCK.equals(l_mainAccountRow.getMngLockFlag())
                || WEB3MngLockDef.LOCK.equals(l_mainAccountRow.getBranchLock()))
        {
            l_stopInfo.registDate = l_mainAccountRow.getEnableOrderUpdatedTimestamp();
        }
        else
        {
            l_stopInfo.registDate = null;
        }
        
        /*
         * ��~���.���b�N�q�o�^����SONAR��t�� = <BR>
         * �@@�@@�|�i���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*1) == null�j�̏ꍇ�Anull�B<BR> 
         * �@@�@@�|�ȊO�A���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*1).get�����敪()�B
         */
        WEB3AccInfoLockAccYAccRegisterRelease l_release = 
            WEB3AccInfoLockAccYAccRegisterRelease.getWEB3AccLockAccYAccRecordRelease(l_mainAccount, WEB3HostRequestCodeDef.ACCINFO_LOCK_REGIST_CANCEL);
        
        if (l_release == null)
        {
            l_stopInfo.lockAccountSonarState = null;
        }
        else
        {
            l_stopInfo.lockAccountSonarState = l_release.getStatus();
        }
        
        /*
         * ��~���.�x�q�o�^����SONAR��t�� = <BR>
         * �@@�@@�|�i���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*2) == null�j�̏ꍇ�Anull�B <BR>
         * �@@�@@�|�ȊO�A���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*2).get�����敪()�B <BR>
         */
        l_release = 
            WEB3AccInfoLockAccYAccRegisterRelease.getWEB3AccLockAccYAccRecordRelease(l_mainAccount, WEB3HostRequestCodeDef.ACCINFO_YELLOW_REGIST_CANCEL);

        if (l_release == null)
        {
            l_stopInfo.yAccountSonarState = null;
        }
        else
        {
            l_stopInfo.yAccountSonarState = l_release.getStatus();
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_orderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //�����`�F�b�N.validate����\�ڋq()�ɂāA�G���[���ԋp���ꂽ�ꍇ�͎����~���Ɣ��f����B
        boolean l_blnTradingStop = false;
        OrderValidationResult l_validationResult = l_orderValidator.validateAccountForTrading(l_mainAccount);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            l_blnTradingStop = true;
        }
                 
        //��~���.�󋵋敪 = �����~�����̏ꍇ�A1�F�֎~���B�ȊO�A0�FDEFAULT�i�֎~���łȂ��j�B
        if (l_blnTradingStop)
        {
            l_stopInfo.stateDiv = WEB3AccInfoStateDivDef.PROHIBITING;
        }
        else
        {
            l_stopInfo.stateDiv = WEB3AccInfoStateDivDef.DEFAULT;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_stopInfo;
    }
    
    /**
     * (create�g�єԍ��E�Ζ�����)<BR>
     * �ڋq�I�u�W�F�N�g���A�g�єԍ��E�Ζ����񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j�@@�g�єԍ��E�Ζ�����I�u�W�F�N�g�𐶐�����B<BR>
     * �Q�j�@@�ڋq.getDataSourceObject()�ɂČڋq�s�I�u�W�F�N�g���擾����B<BR>
     * �R�j�@@�������}�X�^�I�u�W�F�N�g���擾����B<BR> 
     * �@@�@@�@@�������}�X�^.get�������}�X�^()�ɂāA�������}�X�^�I�u�W�F�N�g���擾����B<BR> 
     * <BR> 
     * �S�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g���ԋp����B<BR>
     * <BR>
     * �@@�g�єԍ��E�Ζ�����.�g�єԍ� = �ڋq�s.�A����d�b�ԍ��i�g�сj<BR>
     * �@@�g�єԍ��E�Ζ�����.�Ζ��於�� = �ڋq�s.�Ζ��於��<BR>
     * �@@�g�єԍ��E�Ζ�����.�Ζ���X�֔ԍ� = �ڋq�s.�Ζ���X�֔ԍ�<BR>
     * �@@�g�єԍ��E�Ζ�����.�Ζ���Z�� = �ڋq�s.�Ζ���Z��<BR>
     * �@@�g�єԍ��E�Ζ�����.�Ζ���d�b�ԍ� = �ڋq�s.�Ζ���d�b�ԍ�<BR>
     * �@@�g�єԍ��E�Ζ�����.��E�� = �ڋq�s.��E<BR>
     * �@@�g�єԍ��E�Ζ�����.�A����D�揇��1�� = �������}�X�^.�A����D�揇��1��<BR> 
     * �@@�g�єԍ��E�Ζ�����.�A����D�揇��2�� = �������}�X�^.�A����D�揇��2��<BR> 
     * �@@�g�єԍ��E�Ζ�����.�A����D�揇��3�� = �������}�X�^.�A����D�揇��3��<BR>
     * �@@�g�єԍ��E�Ζ�����.���� = �������}�X�^.����<BR>
     * �@@�g�єԍ��E�Ζ�����.�������̂P = �������}�X�^.�ڋq�������̂P <BR>
     * �@@�g�єԍ��E�Ζ�����.�������̂Q = �������}�X�^.�ڋq�������̂Q <BR>
     * �@@�g�єԍ��E�Ζ�����.�E�� = �������}�X�^.�E�Ƌ敪  <BR>
     * �@@�g�єԍ��E�Ζ�����.���� = �������}�X�^.���� <BR>
     * �@@�g�єԍ��E�Ζ�����.���Ђ��̑� = �������}�X�^.���Ђ��̑��@@<BR>
     * �@@�g�єԍ��E�Ζ�����.��\�Җ��i�����j�� = �������}�X�^.��\�Җ��i�����j��<BR>
     * �@@�g�єԍ��E�Ζ�����.��\�Җ��i�����j�� = �������}�X�^.��\�Җ��i�����j��<BR>
     * �@@�g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j�� = �������}�X�^.��\�Җ��i�J�i�j��<BR> 
     * �@@�g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j�� = �������}�X�^.��\�Җ��i�J�i�j��<BR>
     * �@@�g�єԍ��E�Ζ�����.��\�Ҍ� = �������}�X�^.��\�Ҍ�<BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j�� = �������}�X�^.�����ӔC�Җ��i�����j��<BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j�� = �������}�X�^.�����ӔC�Җ��i�����j��<BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j�� = �������}�X�^.�����ӔC�Җ��i�J�i�j��<BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j�� = �������}�X�^.�����ӔC�Җ��i�J�i�j��<BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҁ@@�������� = �������}�X�^.����ӔC�ҏ�������<BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҁ@@��E = �������}�X�^.����ӔC�Җ�E<BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�җX�֔ԍ� = �������}�X�^.�����ӔC�җX�֔ԍ�<BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҏZ���P = �������}�X�^.�����ӔC�ҏZ���P<BR>  
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҏZ���Q = �������}�X�^.�����ӔC�ҏZ���Q<BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҏZ���R = �������}�X�^.�����ӔC�ҏZ���R<BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Ґ��N�����@@�N�� = �������}�X�^.�����ӔC�Ґ��N�����N��<BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Ґ��N���� = �������}�X�^.�����ӔC�Ґ��N����<BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�҉�В��ʔԍ� = �������}�X�^.�����ӔC�҉�В��ʔԍ�<BR> 
     * �@@�g�єԍ��E�Ζ�����.���̑��A����i�g�сA����j = �������}�X�^.���̑��̘A����i�g�сA����j<BR>
	 * �@@�g�єԍ��E�Ζ�����.FAX�ԍ� = �������}�X�^.FAX�ԍ� <BR>
	 * �@@�g�єԍ��E�Ζ�����.�N�� = �������}�X�^.�N�� <BR>
	 * �@@�g�єԍ��E�Ζ�����.���Z���Y�z = �������}�X�^.���Z���Y�z <BR>
	 * �@@�g�єԍ��E�Ζ�����.�^�p�\��z = �������}�X�^.�^�p�\��z <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����ړI = �������}�X�^.�����ړI <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����\����� = �������}�X�^.�����\����� <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�P�j = �������}�X�^.�����o���̗L���i�P�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�Q�j = �������}�X�^.�����o���̗L���i�Q�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�R�j = �������}�X�^.�����o���̗L���i�R�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�S�j = �������}�X�^.�����o���̗L���i�S�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�T�j = �������}�X�^.�����o���̗L���i�T�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�U�j = �������}�X�^.�����o���̗L���i�U�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�V�j = �������}�X�^.�����o���̗L���i�V�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�W�j = �������}�X�^.�����o���̗L���i�W�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�X�j = �������}�X�^.�����o���̗L���i�X�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�P�O�j = �������}�X�^.�����o���̗L���i�P�O�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���i�P�j = �������}�X�^.�����o���i�P�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���i�Q�j = �������}�X�^.�����o���i�Q�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���i�R�j = �������}�X�^.�����o���i�R�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���i�S�j = �������}�X�^.�����o���i�S�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���i�T�j = �������}�X�^.�����o���i�T�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���i�U�j = �������}�X�^.�����o���i�U�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���i�V�j = �������}�X�^.�����o���i�V�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���i�W�j = �������}�X�^.�����o���i�W�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���i�X�j = �������}�X�^.�����o���i�X�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����o���i�P�O�j = �������}�X�^.�����o���i�P�O�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.����̎�ށi�P�j = �������}�X�^.����̎�ށi�P�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.����̎�ށi�Q�j = �������}�X�^.����̎�ށi�Q�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.����̎�ށi�R�j = �������}�X�^.����̎�ށi�R�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.����̎�ށi�S�j = �������}�X�^.����̎�ށi�S�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.����̎�ށi�T�j = �������}�X�^.����̎�ށi�T�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.����̎�ށi�U�j = �������}�X�^.����̎�ށi�U�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.����̎�ށi�V�j = �������}�X�^.����̎�ށi�V�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.����̎�ށi�W�j = �������}�X�^.����̎�ށi�W�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.����̎�ށi�X�j = �������}�X�^.����̎�ށi�X�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.����̎�ށi�P�O�j = �������}�X�^.����̎�ށi�P�O�j <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����J�݂̓��@@ = �������}�X�^.�����J�݂̓��@@ <BR>
	 * �@@�g�єԍ��E�Ζ�����.�����J�݂̓��@@�̏ڍ� = �������}�X�^.�����J�݂̓��@@�̏ڍ� <BR>
	 * �@@�g�єԍ��E�Ζ�����.���ݗ��p���Ă���،���� = �������}�X�^.���ݗ��p���Ă���،���� <BR>
	 * �@@�g�єԍ��E�Ζ�����.�C���^�[�l�b�g����敪 = �������}�X�^.�C���^�[�l�b�g����敪 <BR>
	 * �@@�g�єԍ��E�Ζ�����.�Љ�x�X = �������}�X�^.�Љ�x�X<BR>
     * <BR> �@@
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo
     * @@throws WEB3BaseException
     * @@roseuid 415D1BEA0107
     */
    protected WEB3AccInfoMobileOfficeInfo createMobileOfficeInfo(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMobileOfficeInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AccInfoMobileOfficeInfo l_mobileOfficeInfo = new WEB3AccInfoMobileOfficeInfo();
        
        //�������}�X�^�I�u�W�F�N�g���擾����B 
        //�@@�������}�X�^.get�������}�X�^()�ɂāA�������}�X�^�I�u�W�F�N�g���擾����B 
        WEB3AccInfoMaster l_master = WEB3AccInfoMaster.getAccInfoMaster(l_mainAccount);
        
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //�g�єԍ��E�Ζ�����.�g�єԍ� = �ڋq�s.�A����d�b�ԍ��i�g�сj 
        l_mobileOfficeInfo.mobileTelephone = l_mainAccountRow.getMobile();
        
        //�g�єԍ��E�Ζ�����.�Ζ��於�� = �ڋq�s.�Ζ��於��
        l_mobileOfficeInfo.officeName = l_mainAccountRow.getOffice();
        
        //�g�єԍ��E�Ζ�����.�Ζ���X�֔ԍ� = �ڋq�s.�Ζ���X�֔ԍ�
        l_mobileOfficeInfo.officeZipCode = l_mainAccountRow.getOfficeZipCode();
         
        //�g�єԍ��E�Ζ�����.�Ζ���Z�� = �ڋq�s.�Ζ���Z��
        l_mobileOfficeInfo.officeAdress = l_mainAccountRow.getOfficeAddress();
         
        //�g�єԍ��E�Ζ�����.�Ζ���d�b�ԍ� = �ڋq�s.�Ζ���d�b�ԍ�
        l_mobileOfficeInfo.officeTelephone = l_mainAccountRow.getOfficeTelephone();
        
        //�g�єԍ��E�Ζ�����.��E�� = �ڋq�s.��E
        l_mobileOfficeInfo.position = l_mainAccountRow.getPost();

        if (l_master != null)
        {
            AccountInfoMstParams l_params = (AccountInfoMstParams)l_master.getDataSourceObject();
            
            //�g�єԍ��E�Ζ�����.�A����D�揇��1�� = �������}�X�^.�A����D�揇��1��
            l_mobileOfficeInfo.contactPriority1 = l_params.getContactPriority1();  
            
            //�g�єԍ��E�Ζ�����.�A����D�揇��2�� = �������}�X�^.�A����D�揇��2��
            l_mobileOfficeInfo.contactPriority2 = l_params.getContactPriority2();         
            
            //�g�єԍ��E�Ζ�����.�A����D�揇��3�� = �������}�X�^.�A����D�揇��3��
            l_mobileOfficeInfo.contactPriority3 = l_params.getContactPriority3(); 
            
            //�g�єԍ��E�Ζ�����.���� = �������}�X�^.����
            l_mobileOfficeInfo.department = l_params.getDepartment();
            
            //�g�єԍ��E�Ζ�����.�������̂P = �������}�X�^.�ڋq�������̂P  
            l_mobileOfficeInfo.accountRealName1 = l_params.getRealName1();
            
            //�g�єԍ��E�Ζ�����.�������̂Q = �������}�X�^.�ڋq�������̂Q 
            l_mobileOfficeInfo.accountRealName2 = l_params.getRealName2();
            
            //�g�єԍ��E�Ζ�����.�E�� = �������}�X�^.�E�Ƌ敪
            l_mobileOfficeInfo.occupationDiv = l_params.getOccupationDiv();
            
            //�g�єԍ��E�Ζ�����.���� = �������}�X�^.����  
            l_mobileOfficeInfo.nationality = l_params.getNationality();
            
            //�g�єԍ��E�Ζ�����.���Ђ��̑� = �������}�X�^.���Ђ��̑� 
            l_mobileOfficeInfo.nationalityOther = l_params.getNationalityOther();
            
            //�g�єԍ��E�Ζ�����.��\�Җ��i�����j�� = �������}�X�^.��\�Җ��i�����j��
            l_mobileOfficeInfo.representFamilyName = l_params.getRepresentFamilyName();
            
            //�g�єԍ��E�Ζ�����.��\�Җ��i�����j�� = �������}�X�^.��\�Җ��i�����j��
            l_mobileOfficeInfo.representName = l_params.getRepresentGivenName();
            
            //�g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j�� = �������}�X�^.��\�Җ��i�J�i�j��
            l_mobileOfficeInfo.representFamilyNameKana = l_params.getRepresentFamilyNameAlt1();
            
            //�g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j�� = �������}�X�^.��\�Җ��i�J�i�j��  
            l_mobileOfficeInfo.representNameKana = l_params.getRepresentGivenNameAlt1();
            
            //�g�єԍ��E�Ζ�����.��\�Ҍ� = �������}�X�^.��\�Ҍ�
            l_mobileOfficeInfo.representPower = l_params.getRepresentPower();
            
            //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j�� = �������}�X�^.�����ӔC�Җ��i�����j��
            l_mobileOfficeInfo.directorFamilyName = l_params.getDirectorFamilyName();
            
            //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j�� = �������}�X�^.�����ӔC�Җ��i�����j��
            l_mobileOfficeInfo.directorName = l_params.getDirectorGivenName();
            
            //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j�� = �������}�X�^.�����ӔC�Җ��i�J�i�j��
            l_mobileOfficeInfo.directorFamilyNameKana = l_params.getDirectorFamilyNameAlt1();
            
            //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j�� = �������}�X�^.�����ӔC�Җ��i�J�i�j��
            l_mobileOfficeInfo.directorNameKana = l_params.getDirectorGivenNameAlt1();
            
            //�g�єԍ��E�Ζ�����.����ӔC�ҁ@@�������� = �������}�X�^.����ӔC�ҏ�������
            l_mobileOfficeInfo.directorDepartment = l_params.getDirectorDepartment();
            
            //�g�єԍ��E�Ζ�����.����ӔC�ҁ@@��E = �������}�X�^.����ӔC�Җ�E
            l_mobileOfficeInfo.directorPosition = l_params.getDirectorPost();
            
            //�g�єԍ��E�Ζ�����.����ӔC�җX�֔ԍ� = �������}�X�^.�����ӔC�җX�֔ԍ�
            l_mobileOfficeInfo.directorZipCode = l_params.getDirectorZipCode();
            
            //�g�єԍ��E�Ζ�����.����ӔC�ҏZ���P = �������}�X�^.�����ӔC�ҏZ���P
            l_mobileOfficeInfo.directorAddress1 = l_params.getDirectorAddress1();

            //�g�єԍ��E�Ζ�����.����ӔC�ҏZ���Q = �������}�X�^.�����ӔC�ҏZ���Q
            l_mobileOfficeInfo.directorAddress2 = l_params.getDirectorAddress2();
            
            //�g�єԍ��E�Ζ�����.����ӔC�ҏZ���R = �������}�X�^.�����ӔC�ҏZ���R
            l_mobileOfficeInfo.directorAddress3 = l_params.getDirectorAddress3();
            
            //�g�єԍ��E�Ζ�����.����ӔC�Ґ��N�����@@�N�� = �������}�X�^.�����ӔC�Ґ��N�����N��
            l_mobileOfficeInfo.directorEraBorn = l_params.getDirectorEraBorn();
            
            //�g�єԍ��E�Ζ�����.����ӔC�Ґ��N���� = �������}�X�^.�����ӔC�Ґ��N����
            l_mobileOfficeInfo.directorBornDate = l_params.getDirectorBornDate();
            
            //�g�єԍ��E�Ζ�����.����ӔC�҉�В��ʔԍ� = �������}�X�^.�����ӔC�҉�В��ʔԍ�
            l_mobileOfficeInfo.directorCorpDirect = l_params.getDirectorCorpTelephone();
            
            //�g�єԍ��E�Ζ�����.���̑��A����i�g�сA����j = �������}�X�^.���̑��̘A����i�g�сA����j  
            l_mobileOfficeInfo.directorOtherContact = l_params.getOtherContact();
            
            //�g�єԍ��E�Ζ�����.FAX�ԍ� = �������}�X�^.FAX�ԍ� 
            l_mobileOfficeInfo.faxTelephone = l_params.getFax();
            
            //�g�єԍ��E�Ζ�����.�N�� = �������}�X�^.�N�� 
            l_mobileOfficeInfo.annualIncomeDiv = l_params.getAnnualIncomeDiv();
            
            //�g�єԍ��E�Ζ�����.���Z���Y�z = �������}�X�^.���Z���Y�z  
            l_mobileOfficeInfo.assetValueDiv = l_params.getAssetValueDiv();
            
            //�g�єԍ��E�Ζ�����.�^�p�\��z = �������}�X�^.�^�p�\��z  
            l_mobileOfficeInfo.fundBundgetAmountDiv = l_params.getFundBudgetAmountDiv();
            
            //�g�єԍ��E�Ζ�����.�����ړI = �������}�X�^.�����ړI   
            l_mobileOfficeInfo.investPurposeDiv = l_params.getInvestPurposeDiv();
            
            //�g�єԍ��E�Ζ�����.�����\����� = �������}�X�^.�����\�����  
            l_mobileOfficeInfo.investPlanPeriodDiv = l_params.getInvestPlanPeriodDiv();
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�P�j = �������}�X�^.�����o���̗L���i�P�j  
            if (!l_params.getExperienceFlag1IsNull())
            {
                l_mobileOfficeInfo.experienceFlag1 = l_params.getExperienceFlag1() + "";
            }

            //�g�єԍ��E�Ζ�����.�����o���̗L���i�Q�j = �������}�X�^.�����o���̗L���i�Q�j 
            if (!l_params.getExperienceFlag2IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag2 = l_params.getExperienceFlag2() + "";
            }
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�R�j = �������}�X�^.�����o���̗L���i�R�j
            if (!l_params.getExperienceFlag3IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag3 = l_params.getExperienceFlag3() + "";
            }
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�S�j = �������}�X�^.�����o���̗L���i�S�j
            if (!l_params.getExperienceFlag4IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag4 = l_params.getExperienceFlag4() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�T�j = �������}�X�^.�����o���̗L���i�T�j   
            if (!l_params.getExperienceFlag5IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag5 = l_params.getExperienceFlag5() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�U�j = �������}�X�^.�����o���̗L���i�U�j   
            if (!l_params.getExperienceFlag6IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag6 = l_params.getExperienceFlag6() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�V�j = �������}�X�^.�����o���̗L���i�V�j 
            if (!l_params.getExperienceFlag7IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag7 = l_params.getExperienceFlag7() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�W�j = �������}�X�^.�����o���̗L���i�W�j 
            if (!l_params.getExperienceFlag8IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag8 = l_params.getExperienceFlag8() + "";
            }
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�X�j = �������}�X�^.�����o���̗L���i�X�j 
            if (!l_params.getExperienceFlag9IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag9 = l_params.getExperienceFlag9() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�P�O�j = �������}�X�^.�����o���̗L���i�P�O�j  
            if (!l_params.getExperienceFlag10IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag10 = l_params.getExperienceFlag10() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���i�P�j = �������}�X�^.�����o���i�P�j 
            l_mobileOfficeInfo.experienceDiv1 = l_params.getExperienceDiv1();
            
            //�g�єԍ��E�Ζ�����.�����o���i�Q�j = �������}�X�^.�����o���i�Q�j   
            l_mobileOfficeInfo.experienceDiv2 = l_params.getExperienceDiv2();
            
            //�g�єԍ��E�Ζ�����.�����o���i�R�j = �������}�X�^.�����o���i�R�j   
            l_mobileOfficeInfo.experienceDiv3 = l_params.getExperienceDiv3();
            
            //�g�єԍ��E�Ζ�����.�����o���i�S�j = �������}�X�^.�����o���i�S�j    
            l_mobileOfficeInfo.experienceDiv4 = l_params.getExperienceDiv4();
            
            //�g�єԍ��E�Ζ�����.�����o���i�T�j = �������}�X�^.�����o���i�T�j    
            l_mobileOfficeInfo.experienceDiv5 = l_params.getExperienceDiv5();
            
            //�g�єԍ��E�Ζ�����.�����o���i�U�j = �������}�X�^.�����o���i�U�j    
            l_mobileOfficeInfo.experienceDiv6 = l_params.getExperienceDiv6();
            
            //�g�єԍ��E�Ζ�����.�����o���i�V�j = �������}�X�^.�����o���i�V�j    
            l_mobileOfficeInfo.experienceDiv7 = l_params.getExperienceDiv7();
            
            //�g�єԍ��E�Ζ�����.�����o���i�W�j = �������}�X�^.�����o���i�W�j 
            l_mobileOfficeInfo.experienceDiv8 = l_params.getExperienceDiv8();
            
            //�g�єԍ��E�Ζ�����.�����o���i�X�j = �������}�X�^.�����o���i�X�j    
            l_mobileOfficeInfo.experienceDiv9 = l_params.getExperienceDiv9();
            
            //�g�єԍ��E�Ζ�����.�����o���i�P�O�j = �������}�X�^.�����o���i�P�O�j  
            l_mobileOfficeInfo.experienceDiv10 = l_params.getExperienceDiv10();
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�P�j = �������}�X�^.����̎�ށi�P�j 
            if (!l_params.getInterestFlag1IsNull())
            {
            	l_mobileOfficeInfo.interest1 = l_params.getInterestFlag1() + "";
            }

            //�g�єԍ��E�Ζ�����.����̎�ށi�Q�j = �������}�X�^.����̎�ށi�Q�j 
            if (!l_params.getInterestFlag2IsNull())
            {
            	l_mobileOfficeInfo.interest2 = l_params.getInterestFlag2() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�R�j = �������}�X�^.����̎�ށi�R�j 
            if (!l_params.getInterestFlag3IsNull())
            {
            	l_mobileOfficeInfo.interest3 = l_params.getInterestFlag3() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�S�j = �������}�X�^.����̎�ށi�S�j 
            if (!l_params.getInterestFlag4IsNull())
            {
            	l_mobileOfficeInfo.interest4 = l_params.getInterestFlag4() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�T�j = �������}�X�^.����̎�ށi�T�j
            if (!l_params.getInterestFlag5IsNull())
            {
            	l_mobileOfficeInfo.interest5 = l_params.getInterestFlag5() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�U�j = �������}�X�^.����̎�ށi�U�j 
            if (!l_params.getInterestFlag6IsNull())
            {
            	l_mobileOfficeInfo.interest6 = l_params.getInterestFlag6() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�V�j = �������}�X�^.����̎�ށi�V�j 
            if (!l_params.getInterestFlag7IsNull())
            {
            	l_mobileOfficeInfo.interest7 = l_params.getInterestFlag7() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�W�j = �������}�X�^.����̎�ށi�W�j  
            if (!l_params.getInterestFlag8IsNull())
            {
            	l_mobileOfficeInfo.interest8 = l_params.getInterestFlag8() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�X�j = �������}�X�^.����̎�ށi�X�j   
            if (!l_params.getInterestFlag9IsNull())
            {
            	l_mobileOfficeInfo.interest9 = l_params.getInterestFlag9() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�P�O�j = �������}�X�^.����̎�ށi�P�O�j  
            if (!l_params.getInterestFlag10IsNull())
            {
            	l_mobileOfficeInfo.interest10 = l_params.getInterestFlag10() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����J�݂̓��@@ = �������}�X�^.�����J�݂̓��@@           
            l_mobileOfficeInfo.appliMotivatDiv = l_params.getAppliMotivatDiv();
            
            //�g�єԍ��E�Ζ�����.�����J�݂̓��@@�̏ڍ� = �������}�X�^.�����J�݂̓��@@�̏ڍ�           
            l_mobileOfficeInfo.appliMotivatDetail = l_params.getAppliMotivatDivDetail();

            //�g�єԍ��E�Ζ�����.���ݗ��p���Ă���،���� = �������}�X�^.���ݗ��p���Ă���،����           
            l_mobileOfficeInfo.useInstitutionDiv = l_params.getUseInstitutionDiv();
            
            //�g�єԍ��E�Ζ�����.�C���^�[�l�b�g����敪 = �������}�X�^.�C���^�[�l�b�g����敪           
            l_mobileOfficeInfo.internetTradeDiv = l_params.getInternetTradeDiv();
            
            //�g�єԍ��E�Ζ�����.�Љ�x�X = �������}�X�^.�Љ�x�X            
            l_mobileOfficeInfo.introduceBranch = l_params.getIntroduceBranchCode();
        }

        log.exiting(STR_METHOD_NAME);
        return l_mobileOfficeInfo;     
    }
    
    /**
     * (create��p�U����������)<BR>
     * �ڋq�I�u�W�F�N�g���A��p�U��������Ɋւ��āA<BR>
     * ���q�l��������񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j�@@��p�U��������e�[�u�����ȉ��̏����œǂށB<BR>
     * �@@�s���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@��p�U��������e�[�u��.����ID = �ڋqgetAccountId()<BR>
     * �@@<BR>
     * �Q�j�@@���q�l���������I�u�W�F�N�g�𐶐�����B<BR>
     * �R�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g���ԋp����B<BR>
     * <BR>
     *   ���q�l���������.���Z�@@�փR�[�h = ��p�U�������.��s�R�[�h<BR>
     * �@@���q�l���������.���Z�@@�֖��� = ��p�U�������.��s��<BR>
     * �@@���q�l���������.�x�X�R�[�h = ��p�U�������.�x�X�R�[�h<BR>
     * �@@���q�l���������.�x�X�� = ��p�U�������.�x�X��<BR>
     * �@@���q�l���������.������ޖ� = ��p�U�������.������ޖ�<BR>
     * �@@���q�l���������.�����ԍ� = ��p�U�������.�����ԍ�<BR>
     * �@@���q�l���������.�������`�l = ��p�U�������.�������`�l<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountInfo
     * @@roseuid 415D1C12003C
     */
    protected WEB3AccInfoAccountInfo createExclusiveTransferAccountInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException  
    {
        
        final String STR_METHOD_NAME = " createExclusiveTransferAccountInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        //��p�U����������ȉ��̏����œǂށB
        ExclusiveUseAccountRow l_exclusiveUseAccountRow = null;
        try
        {
            l_exclusiveUseAccountRow = ExclusiveUseAccountDao.findRowByPk(l_mainAccount.getAccountId());
        }
        catch (DataFindException l_ex)
        {
            //�s���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_exclusiveUseAccountRow == null)
        {
            //�s���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3AccInfoAccountInfo l_accountInfo = new WEB3AccInfoAccountInfo();
        
        //���q�l���������.���Z�@@�փR�[�h = ��p�U�������.��s�R�[�h
        l_accountInfo.financialInstitutionCode = l_exclusiveUseAccountRow.getFinInstitutionCode();
        
        //���q�l���������.���Z�@@�֖��� = ��p�U�������.��s��
        l_accountInfo.financialInstitutionName = l_exclusiveUseAccountRow.getFinInstitutionName();
        
        //���q�l���������.�x�X�R�[�h = ��p�U�������.�x�X�R�[�h
        l_accountInfo.financialBranchCode = l_exclusiveUseAccountRow.getFinBranchCode();
         
        //���q�l���������.�x�X�� = ��p�U�������.�x�X��
        l_accountInfo.financialBranchName = l_exclusiveUseAccountRow.getFinBranchName();
         
        //���q�l���������.������ޖ� = ��p�U�������.������ޖ�
        l_accountInfo.financialAccountTypeName = l_exclusiveUseAccountRow.getFinAccountTypeName();
         
        //���q�l���������.�����ԍ� = ��p�U�������.�����ԍ�
        l_accountInfo.financialAccountCode = l_exclusiveUseAccountRow.getFinAccountNo();
         
        //���q�l���������.�������`�l = ��p�U�������.�������`�l 
        l_accountInfo.financialAccountName = l_exclusiveUseAccountRow.getFinAccountName();
        
        log.exiting(STR_METHOD_NAME);
        return l_accountInfo; 
    }
    
    /**
     * (create�U����w��������)<BR>
     * �ڋq�I�u�W�F�N�g���A�U����w������Ɋւ��āA<BR>
     * ���q�l��������񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j�@@�U������Z�@@�փe�[�u�����ȉ��̏����œǂށB<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�U������Z�@@�փe�[�u��.�،���ЃR�[�h = <BR>
     * �ڋq.getInstitution().getInstitutionCode()<BR>
     * �@@�U������Z�@@�փe�[�u��.���X�R�[�h = <BR>
     * �ڋq.getBranch().getBranchCode()<BR>
     * �@@�U������Z�@@�փe�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode()<BR>
     * �@@�U������Z�@@�փe�[�u��.�w��敪 = 'A' <BR>
     * <BR>
     * �Q�j�@@���q�l���������I�u�W�F�N�g�𐶐�����B<BR>
     * �R�j�ȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�R�|�P�j�ڋq.is�~�ݐU����i��s�����j�o�^�敪() == false �̏ꍇ<BR>
     * �@@�@@�@@�R�|�P�|�P�j���q�l���������.�U����o�^�敪 = 0<BR>
     * �@@�@@�R�|�Q�j�ڋq.is�~�ݐU����i��s�����j�o�^�敪() == true �̏ꍇ<BR>
     * �@@�@@�@@�R�|�Q�|�P�j���q�l���������.�U����o�^�敪 = 1<BR>
     * <BR>
     * �S�j�ڋq.is�~�ݐU����i��s�����j�o�^�敪() == true �̏ꍇ<BR>
     * �@@�S�|�P�j�U������Z�@@�փe�[�u���ŊY���̃f�[�^�����݂��Ȃ������ꍇ<BR>
     *   �@@�@@�@@�@@���͎擾�����U������Z�@@�փf�[�^��<BR>
     * �@@�@@�@@�@@�@@�i�U�֋敪==1�F��s�U���j���i��s��==null�j�̏ꍇ<BR> 
     * �@@�@@�S�|�P�|�P�j���q�l���������.�U����o�^�敪 = 9<BR>
     *<BR> 
     * �@@�S�|�Q�j��L�ȊO�̏ꍇ<BR>
     * �@@�@@�S�|�Q�|�P�j�ȉ��̒ʂ�A�v���p�e�B���Z�b�g���ԋp����B<BR> 
     * �@@�@@�@@���q�l���������.���Z�@@�֖��� = �U������Z�@@��.��s��<BR> 
     * �@@�@@�@@���q�l���������.�x�X�R�[�h = �U������Z�@@��.�x�X�R�[�h<BR> 
     * �@@�@@�@@���q�l���������.�x�X�� = �U������Z�@@��.�x�X��<BR> 
     * �@@�@@�@@���q�l���������.������ޖ� =<BR> 
     * �@@�@@�@@�@@�|�i�U������Z�@@��.�a���敪 == 1�F���ʗa���j�̏ꍇ�A�h���ʗa���h<BR> 
     * �@@�@@�@@�@@�|�i�U������Z�@@��.�a���敪 == 2�F�����a���j�̏ꍇ�A�h�����a���h<BR> 
     * �@@�@@�@@�@@�|�i�U������Z�@@��.�a���敪 == 3�F���̑��j�̏ꍇ�A�h���̑��h<BR> 
     * �@@�@@�@@�@@�|�i�U������Z�@@��.�a���敪 == 4�F���~�a���j�̏ꍇ�A�h���~�a���h <BR>
     * �@@�@@�@@���q�l���������.�����ԍ� = �U������Z�@@��.�����ԍ�<BR> 
     * �@@�@@�@@���q�l���������.�������`�l = �U������Z�@@��.�������`�l <BR>
     * �@@�@@�@@���q�l���������.�U�֋敪 = �U������Z�@@��.�U�֋敪<BR>
     * �@@�@@�@@���q�l���������.�U����敪 = �U������Z�@@��.�U����敪<BR>
     * �@@�@@�@@���q�l���������.�戵�敪 = �U������Z�@@��.�戵�敪<BR>
     *<BR> 
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountInfo
     * @@roseuid 415D1C550174
     */
    protected WEB3AccInfoAccountInfo createTransferAccountInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createTransferAccountInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //�U������Z�@@�փe�[�u�����ȉ��̏����œǂށB     
        TransferedFinInstitutionRow l_finInstitutionRow = null;

        try
        {
            l_finInstitutionRow =
                TransferedFinInstitutionDao.findRowByPk(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    l_mainAccount.getAccountCode(),
                    "A");
        } 
        catch (DataFindException e)
        {
            log.debug("�U������Z�@@�փe�[�u���Y���f�[�^�Ȃ��B");
        } 
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        WEB3AccInfoAccountInfo l_accountInfo = new WEB3AccInfoAccountInfo();

        //�ڋq.is�~�ݐU����i��s�����j�o�^�敪() == false �̏ꍇ
        if (!l_mainAccount.isJapaneseCurrencyBankAccountRegi())
        {
            l_accountInfo.bankAccountRegi = WEB3BankAccountRegiDef.NOT_REGISTER;
        }
        else
        {
            //�ڋq.is�~�ݐU����i��s�����j�o�^�敪() == true�̏ꍇ
            l_accountInfo.bankAccountRegi = WEB3BankAccountRegiDef.ALREADY_REGISTER;
            
            if (l_finInstitutionRow == null
                || (WEB3TransferDivDef.BANK_TRANSFER.equals(l_finInstitutionRow.getTransferDiv())
                    && (l_finInstitutionRow.getFinInstitutionName() == null)))
            {
                //  ���q�l���������.�U����o�^�敪 = 9(�Y���f�[�^�Ȃ�)
                l_accountInfo.bankAccountRegi = WEB3AccInfoBankAccountRegiDef.NO_DATA;
            } 
            else
            {
                //���q�l���������.���Z�@@�֖��� = �U������Z�@@��.��s��
                l_accountInfo.financialInstitutionName = l_finInstitutionRow.getFinInstitutionName();

                //���q�l���������.�x�X�R�[�h = �U������Z�@@��.�x�X�R�[�h
                l_accountInfo.financialBranchCode = l_finInstitutionRow.getFinBranchCode();

                //���q�l���������.�x�X�� = �U������Z�@@��.�x�X��
                l_accountInfo.financialBranchName = l_finInstitutionRow.getFinBranchName();

                //���q�l���������.������ޖ�
                l_accountInfo.setFinancialAccountTypeName(l_finInstitutionRow.getFinSaveDiv());

                //���q�l���������.�����ԍ� = ��p�U�������.�����ԍ�
                l_accountInfo.financialAccountCode = l_finInstitutionRow.getFinAccountNo();

                //���q�l���������.�������`�l = ��p�U�������.�������`�l 
                l_accountInfo.financialAccountName = l_finInstitutionRow.getFinAccountName();

                //���q�l���������.�U�֋敪 = �U������Z�@@��.�U�֋敪
                l_accountInfo.transferDiv = l_finInstitutionRow.getTransferDiv();
                
                //2007.06.21 ������ދ敪,���Z�@@�փR�[�h���Z�b�g����悤�ɏC���@@SCS����-------------start
                l_accountInfo.financialAccountType = l_finInstitutionRow.getFinSaveDiv();
                
                l_accountInfo.financialInstitutionCode = l_finInstitutionRow.getFinInstitutionCode();
                //----------------end

                //���q�l���������.�U����敪 = �U������Z�@@��.�U����敪
                l_accountInfo.transferAccountDiv = l_finInstitutionRow.getTransCommission();

                //���q�l���������.�戵�敪 = �U������Z�@@��.�戵�敪
                l_accountInfo.tradeHandleDiv = l_finInstitutionRow.getTransDealDiv();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_accountInfo;
    }
    
    /**
     * (create�d�q�����)<BR>
     * �ڋq�I�u�W�F�N�g���A�d�q����񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j�@@�d�q�����I�u�W�F�N�g�𐶐�����B<BR>
     * �Q�j�@@�ڋq.getDataSourceObject()�ɂČڋq�s�I�u�W�F�N�g���擾����B<BR>
     * �R�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �@@�d�q�����.����񍐏���t��ԋ敪 = <BR>
     * �ڋq�s.����񍐏���t�敪
     * �@@�d�q�����.����c���񍐏���t��ԋ敪 = <BR>
     * �ڋq�s.����c���񍐏���t�敪
     * �@@�d�q�����.����c���񍐏��쐬�����敪 = <BR>
     * �ڋq�s.����c���񍐏��쐬�����敪
     * �@@�d�q�����.����c���񍐏��a��؍쐬��ԋ敪 = <BR>
     * �@@�@@�|�i�ڋq�s.����c���񍐏��a��؍쐬�t���O == <BR>
     * BooleanEnum.TRUE�j�̏ꍇ�A1�F�쐬<BR>
     * �@@�@@�|�i�ڋq�s.����c���񍐏��a��؍쐬�t���O == <BR>
     * BooleanEnum.FALSE�j�̏ꍇ�A0�F�s�쐬<BR>
     * �@@�d�q�����.����c���񍐏��v�Z���쐬��ԋ敪 = <BR>
     * �@@�@@�|�i�ڋq�s.����c���񍐏��v�Z���쐬�t���O == <BR>
     * BooleanEnum.TRUE�j�̏ꍇ�A1�F�쐬<BR>
     * �@@�@@�|�i�ڋq�s.����c���񍐏��v�Z���쐬�t���O == <BR>
     * BooleanEnum.FALSE�j�̏ꍇ�A0�F�s�쐬<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoBatoInfo
     * @@roseuid 415D1C8700B9
     */
    protected WEB3AccInfoBatoInfo createBatoInfo(WEB3GentradeMainAccount l_mainAccount) 
    {
        final String STR_METHOD_NAME = " createBatoInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoBatoInfo l_batoInfo = new WEB3AccInfoBatoInfo();
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //�d�q�����.����񍐏���t��ԋ敪 = �ڋq�s.����񍐏���t�敪
        l_batoInfo.tradingReportStateDiv = l_mainAccountRow.getTradingReportDiv();
        
        //�d�q�����.����c���񍐏���t��ԋ敪 = �ڋq�s.����c���񍐏���t�敪
        l_batoInfo.positionReportStateDiv = l_mainAccountRow.getPositionReportDiv();
         
        //�d�q�����.����c���񍐏��쐬�����敪 = �ڋq�s.����c���񍐏��쐬�����敪
        l_batoInfo.positionReportCycleDiv = l_mainAccountRow.getPositionReportCycleDiv();
         
        //�d�q�����.����c���񍐏��a��؍쐬��ԋ敪 = 
        //  �|�i�ڋq�s.����c���񍐏��a��؍쐬�t���O == BooleanEnum.TRUE�j�̏ꍇ�A1�F�쐬 
        //  �|�i�ڋq�s.����c���񍐏��a��؍쐬�t���O == BooleanEnum.FALSE�j�̏ꍇ�A0�F�s�쐬
        if (BooleanEnum.TRUE.equals(l_mainAccountRow.getCertificateDepositFlag()))
        {
            l_batoInfo.certificateDepositStateDiv = WEB3CreateStateDivDef.CREATE;
        }
        else
        {
            l_batoInfo.certificateDepositStateDiv = WEB3CreateStateDivDef.NOT_CREATE;
        }
         
        //�d�q�����.����c���񍐏��v�Z���쐬��ԋ敪 = . 
        //  �|�i�ڋq�s.����c���񍐏��v�Z���쐬�t���O == BooleanEnum.TRUE�j�̏ꍇ�A1�F�쐬 
        //  �|�i�ڋq�s.����c���񍐏��v�Z���쐬�t���O == BooleanEnum.FALSE�j�̏ꍇ�A0�F�s�쐬 
        if (BooleanEnum.TRUE.equals(l_mainAccountRow.getAccountStatementFlag()))
        {
            l_batoInfo.accountStatementStateDiv = WEB3CreateStateDivDef.CREATE;
        }
        else
        {
            l_batoInfo.accountStatementStateDiv = WEB3CreateStateDivDef.NOT_CREATE;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_batoInfo; 
    }
    
    /**
     * �����敪���擾����B
     * �|�i�����̐ŋ敪 == TaxTypeEnum.DEFAULT�C�܂���null�j�̏ꍇ�Anull�B
�@@�@@ * �|�i�����̐ŋ敪 == TaxTypeEnum.��ʁj�̏ꍇ�A1�F���J�݁i��ʁj
�@@�@@ * �|�i�����̐ŋ敪 == TaxTypeEnum.����j�̏ꍇ�A2�F�J�ݍς݌��򒥎��Ȃ��i���肩���򒥎��Ȃ��j
�@@�@@ * �|�i�����̐ŋ敪 == TaxTypeEnum.������������򒥎��j�̏ꍇ�A3�F�J�ݍς݌��򒥎�����i���肩���򒥎��j
     * 
     * @@param l_taxType �ŋ敪
     * @@return String
     */
    private String getTaxType(TaxTypeEnum l_taxType)
    {
        String l_strTaxType = null;
        
        if (TaxTypeEnum.UNDEFINED.equals(l_taxType))
        {
            //�|�i�����̐ŋ敪 == TaxTypeEnum.DEFAULT�C�܂���null�j�̏ꍇ�Anull�B
            l_strTaxType = null;
        }
        else if (TaxTypeEnum.NORMAL.equals(l_taxType))
        {
            //�|�i�����̐ŋ敪 == TaxTypeEnum.��ʁj�̏ꍇ�A1�F���J�݁i��ʁj
            l_strTaxType = WEB3AccInfoTaxTypeDef.DEFAULT;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_taxType))
        {
            //�|�i�����̐ŋ敪 == TaxTypeEnum.����j�̏ꍇ�A2�F�J�ݍς݌��򒥎��Ȃ��i���肩���򒥎��Ȃ��j
            l_strTaxType = WEB3AccInfoTaxTypeDef.OPEN_WITHOUT_SPECIAL_WITHHOLD;
        }
        else if (TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
        {
            //�|�i�����̐ŋ敪 == TaxTypeEnum.������������򒥎��j�̏ꍇ�A3�F�J�ݍς݌��򒥎�����i���肩���򒥎��j
            l_strTaxType = WEB3AccInfoTaxTypeDef.OPEN_SPECIAL_WITHHOLD;
        }
        
        return l_strTaxType;
    } 
    
    /**
     * (create�ב֕ۏ؋��������)<BR>
     * �w��ڋq�̂e�w�ڋq�C�e�w�����ԍ��f�[�^���A<BR>
     * �@@�ב֕ۏ؋�������񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�I�u�W�F�N�g�̐����B<BR>
     * �@@�@@�@@�ב֕ۏ؋��������I�u�W�F�N�g���i�[����ׂ̃I�u�W�F�N�g�����B<BR>
     * <BR>
     * <BR>
     * �Q�j�@@FX�V�X�e���R�[�h�̎擾<BR>
     * �@@�@@�@@��Е�FX�V�X�e�������e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�@@���Y���s���Ȃ������ꍇ�́A
     *       null��ԋp            
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@��Е�FX�V�X�e�������e�[�u��.�،���ЃR�[�h =<BR>
     * �@@�@@�@@�@@�ڋq.getInstitution().getInstitutionCode() And<BR>
     * �@@�@@�@@��Е�FX�V�X�e�������e�[�u��.���X�R�[�h =<BR>
     * �@@�@@�@@�@@�ڋq.getBranch().getBranchCode() And<BR>
     * �@@�@@�@@FX�V�X�e���敪 = null<BR>
     * <BR>
     * �R�j�@@�Q�j�̏����Ŏ擾������Е�FX�V�X�e�������e�[�u���̗v�f���ɁA�ȉ��̏������s��<BR>
     * <BR>
     * �@@���Q�j�Ń��R�[�h���擾�ł����ꍇ��<BR>
     * �@@�@@�@@�@@�@@�R�|�P�j�e�w���O�C���h�c�擾<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�ڋq�e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���Y���s���Ȃ������ꍇ�́A
     *�@@�@@�@@�@@�@@�@@�@@       ���������Ɏ��̗v�f��                

     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�ڋq�e�[�u��.�،���ЃR�[�h =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ڋq.getInstitution().getInstitutionCode() And<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�ڋq�e�[�u��.���X�R�[�h =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ڋq.getBranch().getBranchCode() And<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�ڋq�e�[�u��.�e�w�V�X�e���R�[�h�@@=<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��Е�FX�V�X�e�������s.FX�V�X�e���R�[�h And<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�ڋq�e�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode()<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�R�|�Q�jFX�����ԍ��擾<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�����ԍ��e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�����ԍ��e�[�u��.�،���ЃR�[�h =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ڋq.getInstitution().getInstitutionCode() And<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�����ԍ��e�[�u��.���X�R�[�h =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ڋq.getBranch().getBranchCode() And<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�����ԍ��e�[�u��.�e�w�V�X�e���R�[�h�@@=<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��Е�FX�V�X�e�������s.FX�V�X�e���R�[�h And<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�����ԍ��e�[�u��.�e�w�R�[�X�敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@in�i�e�w�R�[�X�敪.�P���ʉ݃R�[�X�C<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�R�[�X�敪.�P�O���ʉ݃R�[�X�j And<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�����ԍ��e�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode()<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�R�|�R�j�@@�ב֕ۏ؋��������I�u�W�F�N�g�𐶐����ȉ��̒ʂ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ב֕ۏ؋��������.�e�w���O�C���h�c�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�ڋq�s.�e�w���O�C���h�c<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ב֕ۏ؋��������.�e�w�����ԍ��i�P���ʉ݃R�[�X�j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�����ԍ��s.�e�w�����ԍ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���i�e�w�R�[�X�敪 == �P���ʉ݃R�[�X�j�̍s<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ב֕ۏ؋��������.�e�w�����ԍ��i�P�O���ʉ݃R�[�X�j�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�����ԍ��s.�e�w�����ԍ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���i�e�w�R�[�X�敪 == �P�O���ʉ݃R�[�X�j�̍s<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ב֕ۏ؋��������.FX�V�X�e���R�[�h�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��Е�FX�V�X�e�������s.FX�V�X�e���R�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�R�|�S�j�@@�P�j�Ő�������List�I�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Ɉב֕ۏ؋��������I�u�W�F�N�g���i�[����B<BR>
     * <BR>
     * �S�jArrayList.toArray()�̖߂�l��ԋp<BR>
     * <BR>
     * @@param l_account - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return WEB3FXAccInformationUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoFxAccountInfo[] createFXAccInformationUnit(WEB3GentradeMainAccount l_account)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createFXAccInformationUnit(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ArrayList�I�u�W�F�N�g�̐����B
        //�ב֕ۏ؋��������I�u�W�F�N�g���i�[����ׂ̃I�u�W�F�N�g�����B
        ArrayList l_lisFxAccountInfos = new ArrayList();
        WEB3AccInfoFxAccountInfo[] l_fxAccountInfos = null;

        //�QFX�V�X�e���R�[�h�̎擾
        //��Е�FX�V�X�e�������e�[�u�����ȉ��̏����Ō�������B
        //�@@[����]
        //�@@��Е�FX�V�X�e�������e�[�u��.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() And
        //�@@��Е�FX�V�X�e�������e�[�u��.���X�R�[�h = �ڋq.getBranch().getBranchCode() And
        //�@@FX�V�X�e���敪 = null
        List l_lisCompFxComditions = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append(" institution_code = ? ");
            l_sbQueryString.append(" and branch_code = ? ");
            l_sbQueryString.append(" and fx_system_div is null ");

            Object[] l_queryDataContainers = new Object[]{
                l_account.getInstitution().getInstitutionCode(),
                l_account.getBranch().getBranchCode()};

            l_lisCompFxComditions = l_queryProcessor.doFindAllQuery(
        		CompFxConditionRow.TYPE,
                l_sbQueryString.toString(),
                l_queryDataContainers);

            //���Y���s���Ȃ������ꍇ�́A
            //null��ԋp            
            
            if (l_lisCompFxComditions.isEmpty())
            {
            	return null;
            }
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�̏����Ŏ擾������Е�FX�V�X�e�������e�[�u���̗v�f���ɁA�ȉ��̏������s��
        CompFxConditionRow l_compFxConditionRow = null;
        Iterator l_itCompFxCon = l_lisCompFxComditions.iterator();

        while (l_itCompFxCon.hasNext())
        {
        	l_compFxConditionRow = (CompFxConditionRow)l_itCompFxCon.next();

        	//�e�w���O�C���h�c�擾
            FxAccountRow l_row = null;
            String l_strFxSystemCode = l_compFxConditionRow.getFxSystemCode();
            try
            {
                l_row = FxAccountDao.findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCode(
                            l_account.getInstitution().getInstitutionCode(),
                            l_account.getBranch().getBranchCode(),
                            l_strFxSystemCode,
                            l_account.getAccountCode());
                if (l_row == null)
                {
                   //���Y���s���Ȃ������ꍇ�́A
                   //���������Ɏ��̗v�f��                
                    continue;
                }                    
            }
            catch (DataNetworkException l_ex)
            {
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�R�|�Q�jFX�����ԍ��擾
            //�e�w�����ԍ��e�[�u�����ȉ��̏����Ō�������B
            //[����]
            //�e�w�����ԍ��e�[�u��.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() And
            //�e�w�����ԍ��e�[�u��.���X�R�[�h = �ڋq.getBranch().getBranchCode() And
            //�e�w�����ԍ��e�[�u��.�e�w�V�X�e���R�[�h�@@= ��Е�FX�V�X�e�������s.FX�V�X�e���R�[�h And
            //�e�w�����ԍ��e�[�u��.�e�w�R�[�X�敪
            //  in �i�e�w�R�[�X�敪.�P���ʉ݃R�[�X�C�e�w�R�[�X�敪.�P�O���ʉ݃R�[�X�j And 
            //�e�w�����ԍ��e�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode()
            //�i�e�w�R�[�X�敪 == �P���ʉ݃R�[�X�j�̍s
            FxAccountCodeRow l_fxAccountCodeRowOneCose = null;
            //�i�e�w�R�[�X�敪 == �P�O���ʉ݃R�[�X�j�̍s
            FxAccountCodeRow l_fxAccountCodeRowTenCose = null;
            
            try
            {
                l_fxAccountCodeRowOneCose = (FxAccountCodeRow) FxAccountCodeDao.findRowByPk(
                        l_account.getInstitution().getInstitutionCode(),
                        l_account.getBranch().getBranchCode(),
                        l_strFxSystemCode,
                        l_account.getAccountCode(),
                        WEB3GftTransStatusCourseDivDef.ONE_COSE);

                log.debug("�i�e�w�R�[�X�敪 == �P���ʉ݃R�[�X�j�̍s = " + l_fxAccountCodeRowOneCose);

            }
            catch (DataNetworkException l_ex)
            {
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            catch (DataFindException l_ex)
            {
                log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B" + l_ex);

            } 
            catch (DataQueryException l_ex)
            {
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            try
            {
                l_fxAccountCodeRowTenCose = (FxAccountCodeRow) FxAccountCodeDao.findRowByPk(
                        l_account.getInstitution().getInstitutionCode(),
                        l_account.getBranch().getBranchCode(),
                        l_strFxSystemCode,
                        l_account.getAccountCode(),
                        WEB3GftTransStatusCourseDivDef.TEN_COSE);

                log.debug("�i�e�w�R�[�X�敪 == �P�O���ʉ݃R�[�X�j�̍s = " + l_fxAccountCodeRowTenCose);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            catch (DataFindException l_ex)
            {
                log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B" + l_ex);

            } 
            catch (DataQueryException l_ex)
            {
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�R�|�R�j�@@�ב֕ۏ؋��������I�u�W�F�N�g�𐶐����ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B 
            //�ב֕ۏ؋��������.�e�w���O�C���h�c�F �e�w�ڋq�s.�e�w���O�C���h�c
            //�ב֕ۏ؋��������.�e�w�����ԍ��i�P���ʉ݃R�[�X�j�F �e�w�����ԍ��s.�e�w�����ԍ��@@���i�e�w�R�[�X�敪 == �P���ʉ݃R�[�X�j�̍s
            //�ב֕ۏ؋��������.�e�w�����ԍ��i�P�O���ʉ݃R�[�X�j�F �e�w�����ԍ��s.�e�w�����ԍ��@@���i�e�w�R�[�X�敪 == �P�O���ʉ݃R�[�X�j�̍s 
            //�ב֕ۏ؋��������.FX�V�X�e���R�[�h�F��Е�FX�V�X�e�������s.FX�V�X�e���R�[�h
            WEB3AccInfoFxAccountInfo l_fxAccInformationUnit = new WEB3AccInfoFxAccountInfo();
            l_fxAccInformationUnit.fxLoginId = "" + l_row.getFxLoginId();

            if (l_fxAccountCodeRowOneCose != null)
            {
                l_fxAccInformationUnit.fxAccountCode1 = l_fxAccountCodeRowOneCose.getFxAccountCode();
            }

            if (l_fxAccountCodeRowTenCose != null)
            {
                l_fxAccInformationUnit.fxAccountCode2 = l_fxAccountCodeRowTenCose.getFxAccountCode();
            }
            l_fxAccInformationUnit.fxSystemCode = l_strFxSystemCode;
            //�R�|�S�j�@@�P�j�Ő�������List�I�u�W�F�N�g�Ɉב֕ۏ؋��������I�u�W�F�N�g���i�[����B
            l_lisFxAccountInfos.add(l_fxAccInformationUnit);
        }

        log.exiting(STR_METHOD_NAME);
        //�S�jArrayList.toArray()�̖߂�l��ԋp
        
        //�擾�����f�[�^��0���ꍇ�Anull��ԋp
        if(l_lisFxAccountInfos.size() == 0){
			l_fxAccountInfos = null;
			return l_fxAccountInfos;
		}
        
        //����ȊO�̏ꍇ�A�擾�������ʂ�ԋp
        else{
        
        	l_fxAccountInfos = new WEB3AccInfoFxAccountInfo[l_lisFxAccountInfos.size()];
        
        }
        
        l_lisFxAccountInfos.toArray(l_fxAccountInfos);
        return l_fxAccountInfos;
    }
    
    /**
     * get�I���b�N�X��p�U��������B<BR>
     * �I���b�N�X��p�U����������擾����B<BR>
     *<BR>
     * �w��ڋq�̃I���b�N�X��p�U����������擾����B<BR>
     * <BR>
     * �P�j�@@�g���[�h�{�[�i�X�v�����e�[�u������<BR>
     *�@@�g���[�h�{�[�i�X�v�����e�[�u�����ȉ��̏����Ō�������B<BR>
     *<BR>
     *�@@[����]<BR>
     *�@@�g���[�h�{�[�i�X�v�����e�[�u��.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() And<BR>
     *�@@�g���[�h�{�[�i�X�v�����e�[�u��.���X�R�[�h = �ڋq.getBranch().getBranchCode() And<BR>
     *�@@�g���[�h�{�[�i�X�v�����e�[�u��.�����R�[�h = �ڋq.getAccountCode()<BR>
     *<BR>
     *�@@�Y���s������΁A�擾�s.�T�u�A�J�E���g��ԋp����B<BR>
     *�@@�Y���s���Ȃ������ꍇ�́Anull��ԋp����B<BR>
     *<BR>
     * @@param l_mainAccount �ڋq<BR>
     * @@return String
     */
    public String getOrixSubAccountCode(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTaxType(WEB3GentradeMainAccount l_MainAccount)";
        log.entering(STR_METHOD_NAME);
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        String l_strAccountCode = l_mainAccount.getAccountCode();
        OrixTradeBonusPlanRow l_row = null;
        try
        {
            l_row = OrixTradeBonusPlanDao.findRowByInstitutionCodeBranchCodeAccountCode(l_strInstitutionCode,
                l_strBranchCode,l_strAccountCode);
        }
        catch (DataFindException l_e)
        {
            log.debug(STR_METHOD_NAME,l_e);
            return null;
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }


        log.exiting(STR_METHOD_NAME);
        if (l_row != null)
        {
            return l_row.getSubAccCode();
        }
        else
        {
            return null;
        }
        
    }
    
    /**
     * (get���X�v���t�@@�����X)<BR>
     * �ڋq�I�u�W�F�N�g���A���X�p�v���t�@@�����X�e�[�u������<BR>
     * �ϑ��萔���R�[�X�敪���擾����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * �@@�@@���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@���XID = �ڋq.getBranch() And<BR>
     * �@@�@@�v���t�@@�����X�� = �v���t�@@�����X��.�ϑ��萔���R�[�X�擾�敪 And<BR>
     * �@@�@@�v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �@@�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@��������.�v���t�@@�����X�̒l��ԋp����B<BR>
     * @@param l_mainAccount - �ڋq<BR>
     * @@return Integer
     * @@throws WEB3BaseException
     */
    protected Integer getBranchPreferences(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchPreferences(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        try 
        {
            //�P�jDB���� 
            //  ���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō�������B
            BranchPreferencesRow l_branchReferencesRow = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_mainAccount.getBranch().getBranchId(), 
                    WEB3BranchPreferencesNameDef.GENTRADE_BIZLOGICPROVIDER_DIV,
                    1);
            
            log.exiting(STR_METHOD_NAME);
            
            //�Q�j�@@��������.�v���t�@@�����X�̒l��ԋp����B
            if (l_branchReferencesRow == null)
            {
                return null;
            }
            else
            {
                return Integer.valueOf(l_branchReferencesRow.getValue());
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    
    /**
     * (create�@@�l���)<BR>
     * �ڋq�I�u�W�F�N�g���A�@@�l��񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j�@@�@@�l���I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�������}�X�^�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�������}�X�^.get�������}�X�^(�����F�ڋq�I�u�W�F�N�g)�ɂāA<BR>
     * �������}�X�^�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �R�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g���ԋp����B<BR>
     * <BR>
     *�@@�@@�l���.��\�Җ��i�J�i�j�� = �������}�X�^.��\�Җ��i�J�i�j��<BR>
     *�@@�@@�l���.��\�Җ��i�J�i�j�� = �������}�X�^.��\�Җ��i�J�i�j��<BR>
     *�@@�@@�l���.��\�Җ��i�����j�� = �������}�X�^.��\�Җ��i�����j��<BR>
     *�@@�@@�l���.��\�Җ��i�����j�� = �������}�X�^.��\�Җ��i�����j��<BR>
     *�@@�@@�l���.��\�Ҍ� = �������}�X�^.��\�Ҍ�<BR>
     *�@@�@@�l���.����ӔC�Җ��i�J�i�j�� = �������}�X�^.����ӔC�Җ��i�J�i�j��<BR>
     *�@@�@@�l���.����ӔC�Җ��i�J�i�j�� = �������}�X�^.����ӔC�Җ��i�J�i�j��<BR>
     *�@@�@@�l���.����ӔC�Җ��i�����j�� = �������}�X�^.����ӔC�Җ��i�����j��<BR>
     *�@@�@@�l���.����ӔC�Җ��i�����j�� = �������}�X�^.����ӔC�Җ��i�����j��<BR>
     *�@@�@@�l���.����ӔC�ҁE�������� = �������}�X�^.����ӔC�� ��������<BR>
     *�@@�@@�l���.����ӔC�ҁE��E = �������}�X�^.����ӔC�ҁ@@��E<BR>
     *�@@�@@�l���.����ӔC�ҁE���N�����N�� = �������}�X�^.����ӔC�Ґ��N�����N��<BR>
     *�@@�@@�l���.����ӔC�ҁE���N���� = �������}�X�^.����ӔC�Ґ��N����<BR>
     *�@@�@@�l���.����ӔC�ҁE�X�֔ԍ� = �������}�X�^.����ӔC�җX�֔ԍ�<BR>
     *�@@�@@�l���.����ӔC�ҁE�Z��1 = �������}�X�^.����ӔC�ҏZ��1<BR>
     *�@@�@@�l���.����ӔC�ҁE�Z��2 = �������}�X�^.����ӔC�ҏZ��2<BR>
     *�@@�@@�l���.����ӔC�ҁE�Z��3 = �������}�X�^.����ӔC�ҏZ��3<BR>
     *�@@�@@�l���.����ӔC�ҁE���̑��A���� = �������}�X�^.���̑��A����i�g�сA����j<BR> 
     *�@@�@@�l���.����ӔC�ҁE��В��ʔԍ� = �������}�X�^.����ӔC�҉�В��ʔԍ�<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g
     * @@return WEB3AccInfoCorporateInfo
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoCorporationInfo createCorporationInfo(WEB3GentradeMainAccount l_mainAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createCorporationInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AccInfoCorporationInfo l_accInfoCorporationInfo = new WEB3AccInfoCorporationInfo();
        
        //�������}�X�^�I�u�W�F�N�g���擾����B 
        //�������}�X�^.get�������}�X�^()(�����F�ڋq�I�u�W�F�N�g)�ɂāA
        //�������}�X�^�I�u�W�F�N�g���擾����B 
        WEB3AccInfoMaster l_accInfoMaster = WEB3AccInfoMaster.getAccInfoMaster(l_mainAccount);

        if (l_accInfoMaster != null)
        {
            AccountInfoMstRow l_accountInfoMstRow = (AccountInfoMstRow)l_accInfoMaster.getDataSourceObject();
            AccountInfoMstParams l_accountInfoMstParams = new AccountInfoMstParams(l_accountInfoMstRow);
            
            //�@@�l���.��\�Җ��i�J�i�j�� = �������}�X�^.��\�Җ��i�J�i�j��        
            l_accInfoCorporationInfo.representFamilyNameKana = l_accountInfoMstParams.getRepresentFamilyNameAlt1();
            
            //�@@�l���.��\�Җ��i�J�i�j�� = �������}�X�^.��\�Җ��i�J�i�j��
            l_accInfoCorporationInfo.representNameKana = l_accountInfoMstParams.getRepresentGivenNameAlt1();
            
            //�@@�l���.��\�Җ��i�����j�� = �������}�X�^.��\�Җ��i�����j��
            l_accInfoCorporationInfo.representFamilyName = l_accountInfoMstParams.getRepresentFamilyName();
            
            //�@@�l���.��\�Җ��i�����j�� = �������}�X�^.��\�Җ��i�����j��
            l_accInfoCorporationInfo.representName = l_accountInfoMstParams.getRepresentGivenName();
            
            //�@@�l���.��\�Ҍ� = �������}�X�^.��\�Ҍ�
            l_accInfoCorporationInfo.representPower = l_accountInfoMstParams.getRepresentPower();
            
            //�@@�l���.����ӔC�Җ��i�J�i�j�� = �������}�X�^.����ӔC�Җ��i�J�i�j��
            l_accInfoCorporationInfo.directorFamilyNameKana = l_accountInfoMstParams.getDirectorFamilyNameAlt1();
            
            //�@@�l���.����ӔC�Җ��i�J�i�j�� = �������}�X�^.����ӔC�Җ��i�J�i�j��
            l_accInfoCorporationInfo.directorNameKana = l_accountInfoMstParams.getDirectorGivenNameAlt1();
            
            //�@@�l���.����ӔC�Җ��i�����j�� = �������}�X�^.����ӔC�Җ��i�����j��
            l_accInfoCorporationInfo.directorFamilyName = l_accountInfoMstParams.getDirectorFamilyName();
            
            //�@@�l���.����ӔC�Җ��i�����j�� = �������}�X�^.����ӔC�Җ��i�����j��
            l_accInfoCorporationInfo.directorName = l_accountInfoMstParams.getDirectorGivenName();
            
            //�@@�l���.����ӔC�ҁE�������� = �������}�X�^.����ӔC�� ��������
            l_accInfoCorporationInfo.directorDepartment = l_accountInfoMstParams.getDirectorDepartment();
            
            //�@@�l���.����ӔC�ҁE��E = �������}�X�^.����ӔC�ҁ@@��E
            l_accInfoCorporationInfo.directorPosition = l_accountInfoMstParams.getDirectorPost();
            
            //�@@�l���.����ӔC�ҁE���N�����N�� = �������}�X�^.����ӔC�Ґ��N�����N��
            l_accInfoCorporationInfo.directorEraBorn = l_accountInfoMstParams.getDirectorEraBorn();
            
            //�@@�l���.����ӔC�ҁE���N���� = �������}�X�^.����ӔC�Ґ��N����
            l_accInfoCorporationInfo.directorBornDate = l_accountInfoMstParams.getDirectorBornDate();
            
            //�@@�l���.����ӔC�ҁE�X�֔ԍ� = �������}�X�^.����ӔC�җX�֔ԍ�
            l_accInfoCorporationInfo.directorZipCode = l_accountInfoMstParams.getDirectorZipCode();
            
            //�@@�l���.����ӔC�ҁE�Z��1 = �������}�X�^.����ӔC�ҏZ��1
            l_accInfoCorporationInfo.directorAddress1 = l_accountInfoMstParams.getDirectorAddress1();
            
            //�@@�l���.����ӔC�ҁE�Z��2 = �������}�X�^.����ӔC�ҏZ��2
            l_accInfoCorporationInfo.directorAddress2 = l_accountInfoMstParams.getDirectorAddress2();
            
            //�@@�l���.����ӔC�ҁE�Z��3 = �������}�X�^.����ӔC�ҏZ��3
            l_accInfoCorporationInfo.directorAddress3 = l_accountInfoMstParams.getDirectorAddress3();
            
            //�@@�l���.����ӔC�ҁE���̑��A���� = �������}�X�^.���̑��A����i�g�сA���)
            l_accInfoCorporationInfo.directorOtherContact = l_accountInfoMstParams.getOtherContact();
            
            //�@@�l���.����ӔC�ҁE��В��ʔԍ� = �������}�X�^.����ӔC�҉�В��ʔԍ�    
            l_accInfoCorporationInfo.directorCorpDirect = l_accountInfoMstParams.getDirectorCorpTelephone();
        }  
                  
        log.exiting(STR_METHOD_NAME);        
        return l_accInfoCorporationInfo;
    }
    
    /**
     * (create�X�g�b�N�I�v�V�����������)<BR>
     * �ڋq�I�u�W�F�N�g���A�X�g�b�N�I�v�V����������񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j�@@�X�g�b�NOP�����Ǘ��}�X�^�[�e�[�u�����A���L�����ɊY�����郌�R�[�h���擾����B <BR>
     * �@@���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()<BR>
     * �@@���X�h�c = �ڋq.getBranch().getBranchId()<BR>
     * �@@�����h�c = �ڋq.getAccountId()<BR>
     * <BR>
     * �Q�j�@@�����v���_�N�g�}�l�[�W���擾<BR>
     * �@@��FinApp.getTradingModule(ProductTypeEnum.����).getProductManager()�ɂĎ擾����B<BR>
     * <BR>
     * �R�j�@@ArrayList�I�u�W�F�N�g�̐���<BR>
     * <BR>
     * �S�j�@@�P�j�̖߂�l�̗v�f���ALoop�������s���B<BR>
     * <BR>
     * �@@�S�|�P�j�@@���������I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�����v���_�N�g�}�l�[�W��.getProduct()�ɂĊ��������I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@[getProduct()�Ɏw�肷�����]<BR>
     * �@@�@@�����h�c�F�@@�X�g�b�NOP�����Ǘ��}�X�^�[Row.get�����h�c<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�X�g�b�N�I�v�V�����������N���X�̃I�u�W�F�N�g���� <BR>
     * <BR>
     * �@@�S�|�R�j�@@�X�g�b�N�I�v�V�����������I�u�W�F�N�g.�����R�[�h = ��������.getProductCode()<BR>
     * <BR>
     * �@@�S�|�S�j�@@�X�g�b�N�I�v�V�����������I�u�W�F�N�g.������ = ��������.getStandardName()<BR>
     * <BR>
     * �@@�S�|�T�j�@@ArrayList�I�u�W�F�N�g.add(�X�g�b�N�I�v�V�����������I�u�W�F�N�g) <BR>
     * <BR>
     * �T�j�@@ArrayList�I�u�W�F�N�g���X�g�b�N�I�v�V�����������[]�ɕϊ�����B <BR>
     * <BR>
     * �U�j�@@�T�j�ŕϊ������X�g�b�N�I�v�V�����������[]��ԋp����B<BR>
     * @@param �ڋq - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return WEB3AccInfoStockOptionInfo[]
     * @@throws WEB3BaseException
     * @@roseuid 44EBACB203AD
     */
    protected WEB3AccInfoStockOptionInfo[] createStockOptionInfo(WEB3GentradeMainAccount l_mainAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createStockOptionInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        List l_lisRecords = null;
        int l_intSize = 0;
        //�ڋq�I�u�W�F�N�g���A�X�g�b�N�I�v�V����������񃁃b�Z�[�W�f�[�^���쐬����B 
        //�P�j�@@�X�g�b�NOP�����Ǘ��}�X�^�[�e�[�u�����A���L�����ɊY�����郌�R�[�h���擾����B
        //�@@���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B
        //[����] 
        //�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() 
        //���X�h�c = �ڋq.getBranch().getBranchId() 
        //�����h�c = �ڋq.getAccountId()
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and branch_id = ? ");
            l_sbWhere.append(" and account_id = ? ");

            Object[] l_objWhere = {
                l_mainAccount.getInstitution().getInstitutionCode(),   
                new Long(l_mainAccount.getBranch().getBranchId()), 
                new Long(l_mainAccount.getAccountId())
                };

            l_lisRecords = l_processor.doFindAllQuery(
                StockOptionProductRow.TYPE, 
                l_sbWhere.toString(), 
                null, 
                null, 
                l_objWhere
                );

        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�����v���_�N�g�}�l�[�W���擾
        //��FinApp.getTradingModule(ProductTypeEnum.����).getProductManager()�ɂĎ擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_equityTradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //�����v���_�N�g�}�l�[�W���擾
        WEB3EquityProductManager l_equityProductMgr = (WEB3EquityProductManager)l_equityTradingModule.getProductManager();
 
        //�R�j�@@ArrayList�I�u�W�F�N�g�̐���
        List l_lisTemp = new ArrayList();

        //�S�j�@@�P�j�̖߂�l�̗v�f���ALoop�������s���B
        if (l_lisRecords != null)
        {
            l_intSize = l_lisRecords.size();
        }

        for (int i = 0; i < l_intSize; i++)
        {
            //�S�|�P�j�@@���������I�u�W�F�N�g���擾����B
            //�����v���_�N�g�}�l�[�W��.getProduct()�ɂĊ��������I�u�W�F�N�g���擾����B 
            //[getProduct()�Ɏw�肷�����] 
            //�����h�c�F�@@�X�g�b�NOP�����Ǘ��}�X�^�[Row.get�����h�c
            //���������I�u�W�F�N�g���擾����B
            StockOptionProductRow l_stockOptionProductRow = (StockOptionProductRow)l_lisRecords.get(i);
            WEB3EquityProduct l_equityProduct = null;
            try
            {   
                l_equityProduct = (WEB3EquityProduct) l_equityProductMgr.getProduct(l_stockOptionProductRow.getProductId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�S�|�Q�j�@@�X�g�b�N�I�v�V�����������N���X�̃I�u�W�F�N�g����
            WEB3AccInfoStockOptionInfo l_stockOptionInfo = new WEB3AccInfoStockOptionInfo();

            //�S�|�R�j�@@�X�g�b�N�I�v�V�����������I�u�W�F�N�g.�����R�[�h = ��������.getProductCode()
            l_stockOptionInfo.productCode = l_equityProduct.getProductCode();

            //�S�|�S�j�@@�X�g�b�N�I�v�V�����������I�u�W�F�N�g.������ = ��������.getStandardName()
            l_stockOptionInfo.productName = l_equityProduct.getStandardName();

            //�S�|�T�j�@@ArrayList�I�u�W�F�N�g.add(�X�g�b�N�I�v�V�����������I�u�W�F�N�g)
            l_lisTemp.add(l_stockOptionInfo);

        }

        //�T�j�@@ArrayList�I�u�W�F�N�g���X�g�b�N�I�v�V�����������[]�ɕϊ�����B
        WEB3AccInfoStockOptionInfo[] l_stockOptionInfos = new WEB3AccInfoStockOptionInfo[l_lisTemp.size()];
        l_lisTemp.toArray(l_stockOptionInfos);

        //�U�j�@@�T�j�ŕϊ������X�g�b�N�I�v�V�����������[]��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_stockOptionInfos;
    }

    /**
     * (create�����L�����y�[�����)<BR>
     * �萔�������L�����y�[�������쐬����B<BR>
     * <BR>
     * �P�j�@@�萔�������L�����y�[���ڋq�����e�[�u�����ȉ��̏����Ō�������B<BR> 
     * �@@�E�Y���s���Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * �@@�E�擾����List���Ɂy�o�^�^�C�v="2 �F �����ʌڋq�w��"�z��<BR>
     * �@@�@@�f�[�^���܂܂��ꍇ��null��ԋp����B<BR>
     * �@@[��������] <BR>
     * �@@����ID = �ڋq.getAccountId() and <BR>
     * �@@�Ώۊ���From <= ���X�c�Ɠ� and<BR>
     * �@@�Ώۊ���To >= �����c�Ɠ� and<BR>
     * �@@�L���敪 = 1�F�L��<BR>
     * <BR>
     * �@@[�\�[�g����]<BR>
     * �@@�Ώۊ���From.asc<BR>
     * <BR>
     * �Q�j�@@�萔�������L�����y�[�����i�}�X�^�e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@[��������] <BR>
     * �@@�L�����y�[���萔������ID = �P�j�Ŏ擾����List.get(0).�L�����y�[���萔������ID<BR>
     * <BR>
     * �R�j�@@�擾�������R�[�h���ALoop�������s���B <BR>
     *�@@�R�|�P�j�@@�萔�������L�����y�[�����I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     *�@@�R�|�Q�j�@@�萔�������L�����y�[�����i�}�X�^�e�[�u�����ȉ��̏����Ō�������B <BR>
     *�@@[��������]  <BR>
     *�@@�L�����y�[���萔������ID = �P�j�Ŏ擾����List.get( i ).�L�����y�[���萔������ID <BR>
     * <BR>
     *�@@�R�|�R)�@@�R�|�Q�j�Ŏ擾����List����Loop�����ɂď��i�R�[�h��List�𐶐� <BR>
     * <BR>
     *�@@�R�|�S�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g���A�ԋp����B <BR>
     *�@@�萔�������L�����y�[�����.�L�����y�[������ = �P�j�Ŏ擾����List.get( i ).�萔�������L�����y�[������ <BR>
     *�@@�萔�������L�����y�[�����.���i�R�[�h = �R�|�R�j�Ő����������i�R�[�hList��String�^�̔z��ɕϊ������l <BR>
     *�@@�萔�������L�����y�[�����.������ = (100 �| �擾����List.get( i ).������)��String�^�ɕϊ� <BR>
     *�@@�萔�������L�����y�[�����.�K�p����from = �P�j�Ŏ擾����List.get( i ).�Ώۊ���From <BR>
     *�@@�萔�������L�����y�[�����.�K�p����To = �P�j�Ŏ擾����List.get( i ).�Ώۊ���To <BR>
     * <BR>
     *�@@�R�|�T�j�@@�萔�������L�����y�[�����I�u�W�F�N�g��List�ɒǉ� <BR>
     * <BR>
     * �S�j�@@ArrayList�I�u�W�F�N�g��z��ɕϊ����A�ԋp
     * <BR>
     * @@param �ڋq - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return WEB3AccInfoCommissionCampaignInfo[]
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoCommissionCampaignInfo[] createAccInfoCampaignInfo(WEB3GentradeMainAccount l_mainAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAccInfoCampaignInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //��������
        StringBuffer l_sbSearchCondition = new StringBuffer();
        l_sbSearchCondition.append(" account_id = ? ");
        l_sbSearchCondition.append(" and appli_start_date <= ? ");
        l_sbSearchCondition.append(" and appli_end_date >= ? ");
        l_sbSearchCondition.append(" and valid_div = ? ");
        String l_strSearchCondition = l_sbSearchCondition.toString();
        
        Object[] l_strSearchConditionContainers = null;
        List l_lisSearchContainers = new ArrayList();

        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //����ID
        Long l_strAccountId = new Long(l_mainAccount.getAccountId());
        l_lisSearchContainers.add(l_strAccountId);
        //�Ɩ����t
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        WEB3GentradeBizDate l_bizDateCalcUtil = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
        //���X�c�Ɠ�
        Timestamp l_tsBizDate = l_bizDateCalcUtil.roll(2);
        l_lisSearchContainers.add(l_tsBizDate);
        //�����c�Ɠ�
        l_lisSearchContainers.add(l_datBizDate);
        //�L���敪
        l_lisSearchContainers.add(WEB3ValidDivDef.EFFECTIVE);
        l_strSearchConditionContainers = l_lisSearchContainers.toArray();
        //�\�[�g����
        String l_sortCondition = " appli_start_date ASC ";

        List l_lisCommCampaignAccHistory = new ArrayList();
        //�P�j�@@�萔�������L�����y�[���ڋq�����e�[�u�����ȉ��̏����Ō�������B
        //[��������]
        //����ID = �ڋq.getAccountId() and 
        //�Ώۊ���From <= ���c�Ɠ� and
        //�Ώۊ���To >= �����c�Ɠ� and
        //�L���敪 = 1�F�L�� 
        //[�\�[�g����]
        //�Ώۊ���From.asc
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisCommCampaignAccHistory = l_queryProcessor.doFindAllQuery(
                    CommCampaignAccHistoryRow.TYPE,
                    l_strSearchCondition,
                    l_sortCondition,
                    null,
                    l_strSearchConditionContainers);
        }
        catch (DataFindException l_ex)
        {
            //�Y���s���Ȃ������ꍇ�́Anull��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if ((l_lisCommCampaignAccHistory == null) || (l_lisCommCampaignAccHistory.size() == 0))
        {
            //�Y���s���Ȃ������ꍇ�́Anull��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //�擾����List���Ɂy�o�^�^�C�v="2 �F �����ʌڋq�w��"�z��
        //�f�[�^���܂܂��ꍇ��null��ԋp����B
        for (int i = 0; i < l_lisCommCampaignAccHistory.size(); i++)
        {
            if (WEB3AccInfoRegistTypeDef.FORCE_INDIVIDUAL.equals((
                    (CommCampaignAccHistoryRow)l_lisCommCampaignAccHistory.get(i)).getRegistType()))
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }

        //�Q�j�@@ArrayList�I�u�W�F�N�g����
        List l_lisAccInfoCommissionCampaignInfos = new ArrayList();
        //�R�j�@@�擾�������R�[�h���ALoop�������s���B
        for (int i = 0; i < l_lisCommCampaignAccHistory.size(); i++)
        {
            //�R�|�P�j�@@�萔�������L�����y�[�����I�u�W�F�N�g�𐶐�����B
            WEB3AccInfoCommissionCampaignInfo l_accInfoCommissionCampaignInfo =
                new WEB3AccInfoCommissionCampaignInfo();
            //�R�|�Q�j�@@�萔�������L�����y�[�����i�}�X�^�e�[�u�����ȉ��̏����Ō�������B
            //[��������] 
            //�L�����y�[���萔������ID = �P�j�Ŏ擾����List.get( i ).�L�����y�[���萔������ID
            l_strSearchCondition = " campaign_id = ? ";
            CommCampaignAccHistoryRow l_commCampaignAccHistoryRow =
                (CommCampaignAccHistoryRow)l_lisCommCampaignAccHistory.get(i);
            Long l_lngCampaignId =
                new Long(l_commCampaignAccHistoryRow.getCampaignId());

            l_strSearchConditionContainers = new Object[1];;
            l_strSearchConditionContainers[0] = l_lngCampaignId;

            List l_lisCampaignConditionMasters = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisCampaignConditionMasters = l_queryProcessor.doFindAllQuery(
                        CommCampaignProductMstRow.TYPE,
                        l_strSearchCondition,
                        l_strSearchConditionContainers);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            if ((l_lisCampaignConditionMasters == null) || (l_lisCampaignConditionMasters.size() == 0))
            {
                log.debug("�萔�������L�����y�[�����i�}�X�^�̃f�[�^�擾�o���Ȃ��A��O���X���[����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����ɊY������f�[�^�����݂��Ȃ��B");
            }
            List l_lisCommProductCodes = new ArrayList();
            //�R�|�R)�@@�R�|�Q�j�Ŏ擾����List����Loop�����ɂď��i�R�[�h��List�𐶐�
            for (int j = 0; j < l_lisCampaignConditionMasters.size(); j++)
            {
                l_lisCommProductCodes.add(((CommCampaignProductMstRow)l_lisCampaignConditionMasters.get(j)).getCommProductCode());
            }
            //�R�|�S�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g���A�ԋp����B
            //�萔�������L�����y�[�����.�L�����y�[������ = �P�j�Ŏ擾����List.get( i ).�萔�������L�����y�[������
            //�萔�������L�����y�[�����.���i�R�[�h = �R�|�R�j�Ő����������i�R�[�hList��String�^�̔z��ɕϊ������l
            //�萔�������L�����y�[�����.������ = (100 �| �擾����List.get( i ).������)��String�^�ɕϊ�
            //�萔�������L�����y�[�����.�K�p����from = �P�j�Ŏ擾����List.get( i ).�Ώۊ���From
            //�萔�������L�����y�[�����.�K�p����To = �P�j�Ŏ擾����List.get( i ).�Ώۊ���To
            CommCampaignAccHistoryRow l_commCampaignAccHistory = (CommCampaignAccHistoryRow)l_lisCommCampaignAccHistory.get(i);
            l_accInfoCommissionCampaignInfo.campaignName = l_commCampaignAccHistory.getCommCampaignName();
            l_accInfoCommissionCampaignInfo.itemCode =
                (String[])l_lisCommProductCodes.toArray(new String[l_lisCommProductCodes.size()]);
            l_accInfoCommissionCampaignInfo.discountRate =
                WEB3StringTypeUtility.formatNumber(100 - l_commCampaignAccHistoryRow.getAccountChargeRatio());
            l_accInfoCommissionCampaignInfo.targetPeriodFrom = l_commCampaignAccHistoryRow.getAppliStartDate();
            l_accInfoCommissionCampaignInfo.targetPeriodTo = l_commCampaignAccHistoryRow.getAppliEndDate();
            //�R�|�T�j�@@�萔�������L�����y�[�����I�u�W�F�N�g��List�ɒǉ�
            l_lisAccInfoCommissionCampaignInfos.add(l_accInfoCommissionCampaignInfo);
        }

        WEB3AccInfoCommissionCampaignInfo[] l_accInfoCommissionCampaignInfos =
            new WEB3AccInfoCommissionCampaignInfo[l_lisAccInfoCommissionCampaignInfos.size()];
        l_lisAccInfoCommissionCampaignInfos.toArray(l_accInfoCommissionCampaignInfos);
        
        log.exiting(STR_METHOD_NAME);
        return l_accInfoCommissionCampaignInfos;
    }

    /**
     * (create�O�ݐU����w��������)<BR>
     * �ڋq�I�u�W�F�N�g���A�O�ݐU����w������Ɋւ��āA���q�l��������񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j�@@�O�ݐU������Z�@@�փe�[�u�����ȉ��̏����œǂށB <BR>
     * �@@�@@�@@���Y���f�[�^���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�O�ݐU������Z�@@�փe�[�u��.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() <BR>
     * �@@�O�ݐU������Z�@@�փe�[�u��.���X�R�[�h = �ڋq.getBranch().getBranchCode() <BR>
     * �@@�O�ݐU������Z�@@�փe�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode() <BR>
     * �@@�O�ݐU������Z�@@�փe�[�u��.�o�^�敪 = '0'<BR>
     * <BR>
     * �Q�j�@@ArrayList�I�u�W�F�N�g�̐���<BR>
     * <BR>
     * �R�j�@@�P�j�̖߂�l�̗v�f���ALoop�������s���B<BR>
     * <BR>
     * �@@�R�|�P�j�@@���q�l���������I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�R�|�Q�j�@@�ȉ��̒ʂ�v���p�e�B���Z�b�g�B<BR>
     * �@@�@@���q�l���������.�ʉ݃R�[�h = �O�ݐU������Z�@@��.�ʉ݃R�[�h <BR>
     * �@@�@@���q�l���������.���Z�@@�փR�[�h  = �O�ݐU������Z�@@��.��s�R�[�h<BR>
     * �@@�@@���q�l���������.���Z�@@�֖��� = �O�ݐU������Z�@@��.��s�� <BR>
     * �@@�@@���q�l���������.�x�X�R�[�h = �O�ݐU������Z�@@��.�x�X�R�[�h <BR>
     * �@@�@@���q�l���������.�x�X�� = �O�ݐU������Z�@@��.�x�X�� <BR>
     * �@@�@@���q�l���������.������ދ敪 = �O�ݐU������Z�@@��.�a���敪<BR>
     * �@@�@@���q�l���������.�����ԍ� = �O�ݐU������Z�@@��.�����ԍ� <BR>
     * �@@�@@���q�l���������.�������`�l = �O�ݐU������Z�@@��.�������`�l<BR>
     * �@@�R�|�R�j�@@ArrayList�I�u�W�F�N�g.add(���q�l���������I�u�W�F�N�g)<BR>
     * <BR>
     * �S�j�@@ArrayList�I�u�W�F�N�g�����q�l���������[]�ɕϊ�����B<BR>
     * <BR>
     * �T�j�@@�S�j�ŕϊ��������q�l���������[]��ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return WEB3AccInfoAccountInfo[]
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoAccountInfo[] createForeignTransferAccountInfo(WEB3GentradeMainAccount l_mainAccount) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createForeignTransferAccountInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //��������
        StringBuffer l_sbSearchCondition = new StringBuffer();
        l_sbSearchCondition.append(" institution_code = ? ");
        l_sbSearchCondition.append(" and branch_code = ? ");
        l_sbSearchCondition.append(" and account_code = ? ");
        l_sbSearchCondition.append(" and fin_del_div = ? ");
        String l_strSearchCondition = l_sbSearchCondition.toString();
        
        Object[] l_strSearchConditionContainers = null;
        List l_lisSearchContainers = new ArrayList();

        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�ڋq.getInstitution().getInstitutionCode()
        l_lisSearchContainers.add(l_mainAccount.getInstitution().getInstitutionCode());
        //�ڋq.getBranch().getBranchCode()
        l_lisSearchContainers.add(l_mainAccount.getBranch().getBranchCode());
        //�ڋq.getAccountCode()
        l_lisSearchContainers.add(l_mainAccount.getAccountCode());
        //�o�^�敪 = '0'
        l_lisSearchContainers.add(WEB3FinDelDivDef.EXCEPT_OBLITERATION);
        l_strSearchConditionContainers = l_lisSearchContainers.toArray();

        //�P�j�@@�O�ݐU������Z�@@�փe�[�u�����ȉ��̏����œǂށB
        //�@@�@@�@@���Y���f�[�^���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B
        //�@@[����]
        //�@@�O�ݐU������Z�@@�փe�[�u��.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()
        //�@@�O�ݐU������Z�@@�փe�[�u��.���X�R�[�h = �ڋq.getBranch().getBranchCode()
        //�@@�O�ݐU������Z�@@�փe�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode()
        //�@@�O�ݐU������Z�@@�փe�[�u��.�o�^�敪 = '0'

        //�Q�j�@@ArrayList�I�u�W�F�N�g�̐���
        List l_lisFTransFinInstitution = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisFTransFinInstitution = l_queryProcessor.doFindAllQuery(
                    FTransFinInstitutionRow.TYPE,
                    l_strSearchCondition,
                    l_strSearchConditionContainers);
        }
        catch (DataFindException l_ex)
        {
            //�Y���f�[�^���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�R�j�@@�P�j�̖߂�l�̗v�f���ALoop�������s���B
        if ((l_lisFTransFinInstitution == null) || (l_lisFTransFinInstitution.size() == 0))
        {
            //�Y���f�[�^���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //ArrayList�I�u�W�F�N�g
        List l_lisAccInfoAccountInfo = new ArrayList();
        for (int i = 0; i < l_lisFTransFinInstitution.size(); i++)
        {
            //�R�|�P�j�@@���q�l���������I�u�W�F�N�g�𐶐�����B
            WEB3AccInfoAccountInfo l_accountInfo = new WEB3AccInfoAccountInfo();
            //�O�ݐU������Z�@@�փN���X�̃I�u�W�F�N�g����
            FTransFinInstitutionRow l_fTransFinInstitutionRow = (FTransFinInstitutionRow)l_lisFTransFinInstitution.get(i);
            //�R�|�Q�j�@@�ȉ��̒ʂ�v���p�e�B���Z�b�g�B
            //�@@�@@���q�l���������.�ʉ݃R�[�h = �O�ݐU������Z�@@��.�ʉ݃R�[�h
            //�@@�@@���q�l���������.���Z�@@�փR�[�h  = �O�ݐU������Z�@@��.��s�R�[�h
            //�@@�@@���q�l���������.���Z�@@�֖��� = �O�ݐU������Z�@@��.��s��
            //�@@�@@���q�l���������.�x�X�R�[�h = �O�ݐU������Z�@@��.�x�X�R�[�h
            //�@@�@@���q�l���������.�x�X�� = �O�ݐU������Z�@@��.�x�X��
            //�@@�@@���q�l���������.������ދ敪 = �O�ݐU������Z�@@��.�a���敪
            //�@@�@@���q�l���������.�����ԍ� = �O�ݐU������Z�@@��.�����ԍ�
            //�@@�@@���q�l���������.�������`�l = �O�ݐU������Z�@@��.�������`�l
            l_accountInfo.currencyCode = l_fTransFinInstitutionRow.getCurrencyCode();
            l_accountInfo.financialInstitutionCode = l_fTransFinInstitutionRow.getFinInstitutionCode();
            l_accountInfo.financialInstitutionName = l_fTransFinInstitutionRow.getFinInstitutionName();
            l_accountInfo.financialBranchCode = l_fTransFinInstitutionRow.getFinBranchCode();
            l_accountInfo.financialBranchName = l_fTransFinInstitutionRow.getFinBranchName();
            l_accountInfo.financialAccountType = l_fTransFinInstitutionRow.getFinSaveDiv();
            l_accountInfo.financialAccountCode = l_fTransFinInstitutionRow.getFinAccountNo();
            l_accountInfo.financialAccountName = l_fTransFinInstitutionRow.getFinAccountName();
            //�R�|�R�j�@@ArrayList�I�u�W�F�N�g.add(���q�l���������I�u�W�F�N�g)
            l_lisAccInfoAccountInfo.add(l_accountInfo);
        }
        //�S�j�@@ArrayList�I�u�W�F�N�g�����q�l���������[]�ɕϊ�����B
        WEB3AccInfoAccountInfo[] l_accInfoAccountInfos = new WEB3AccInfoAccountInfo[l_lisAccInfoAccountInfo.size()];
        //�T�j�@@�S�j�ŕϊ��������q�l���������[]��ԋp����B
        l_lisAccInfoAccountInfo.toArray(l_accInfoAccountInfos);
        log.exiting(STR_METHOD_NAME);
        return l_accInfoAccountInfos;
        
    }

    /**
     * (create�����A�h���X���)<BR>
     * �ڋq�s�I�u�W�F�N�g���A�����A�h���X��񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * �ڋq�s��蕡���A�h���X��񂪎擾�o���Ȃ��ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����A�h���X���I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B<BR>
     * �@@�Q-�P�j �Y����l��null�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�����A�h���X���.���[���A�h���X�Q = �i�����j�ڋq�s.email�A�h���X1<BR>
     * �@@�@@�����A�h���X���.���[���A�h���X�R = �i�����j�ڋq�s.email�A�h���X2<BR>
     * �@@�@@�����A�h���X���.���ʒm���M�t���O = �i�����j�ڋq�s.���ʒm���M�t���O<BR>
     * �@@�@@�����A�h���X���.�����ʒm���M�t���O = �i�����j�ڋq�s.�����ʒm���M�t���O<BR>
     * �@@�@@�����A�h���X���.�d�v�A�����[�����M�t���O = �i�����j�ڋq�s.�d�v�A�����[�����M�t���O<BR>
     * �@@�@@�����A�h���X���.�ē����[���Q���M�t���O = �i�����j�ڋq�s.�ē����[���Q���M�t���O<BR>
     * �@@�@@�����A�h���X���.���[���A�h���X�Q�폜�t���O = false<BR>
     * �@@�@@�����A�h���X���.���[���A�h���X�R�폜�t���O = false<BR>
     * <BR>
     * �@@�Q-�Q�j �Y����l��null�̏ꍇ<BR>
     * �@@�@@�����A�h���X���.���[���A�h���X�Q = null<BR>
     * �@@�@@�����A�h���X���.���[���A�h���X�R = null<BR>
     * �@@�@@�����A�h���X���.���ʒm���M�t���O = null<BR>
     * �@@�@@�����A�h���X���.�����ʒm���M�t���O = null<BR>
     * �@@�@@�����A�h���X���.�d�v�A�����[�����M�t���O = null<BR>
     * �@@�@@�����A�h���X���.�ē����[���Q���M�t���O = null<BR>
     * �@@�@@�����A�h���X���.���[���A�h���X�Q�폜�t���O = false<BR>
     * �@@�@@�����A�h���X���.���[���A�h���X�R�폜�t���O = false<BR>
     * <BR>
     * <BR>
     * �R�j �����A�h���X���I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_mainAccountRow - (�ڋq�s)<BR>
     * �ڋq�s�I�u�W�F�N�g<BR>
     * @@return WEB3AccInfoMultiMailAddressInfo
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoMultiMailAddressInfo createMultiMailAddressInfo(
        MainAccountRow l_mainAccountRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMultiMailAddressInfo(MainAccountRow)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����A�h���X���I�u�W�F�N�g�𐶐�����B
        WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo =
            new WEB3AccInfoMultiMailAddressInfo();

        if (l_mainAccountRow == null)
        {
            //�Q-�Q�j �Y����l��null�̏ꍇ
            //�����A�h���X���.���[���A�h���X�Q = null
            l_accInfoMultiMailAddressInfo.mailAddress2 = null;

            //�����A�h���X���.���[���A�h���X�R = null
            l_accInfoMultiMailAddressInfo.mailAddress3 = null;

            //�����A�h���X���.���ʒm���M�t���O = null
            l_accInfoMultiMailAddressInfo.execMailFlag = null;

            //�����A�h���X���.�����ʒm���M�t���O = null
            l_accInfoMultiMailAddressInfo.unExecMailFlag = null;

            //�����A�h���X���.�d�v�A�����[�����M�t���O = null
            l_accInfoMultiMailAddressInfo.importantMailFlag = null;

            //�����A�h���X���.�ē����[���Q���M�t���O = null
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = null;

            //�����A�h���X���.���[���A�h���X�Q�폜�t���O = false
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = false;

            //�����A�h���X���.���[���A�h���X�R�폜�t���O = false
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag3 = false;
        }
        else
        {
            //�Q-�P�j �Y����l��null�ȊO�̏ꍇ
            //�����A�h���X���.���[���A�h���X�Q = �i�����j�ڋq�s.email�A�h���X1
            l_accInfoMultiMailAddressInfo.mailAddress2 = l_mainAccountRow.getEmailAddressAlt1();

            //�����A�h���X���.���[���A�h���X�R = �i�����j�ڋq�s.email�A�h���X2
            l_accInfoMultiMailAddressInfo.mailAddress3 = l_mainAccountRow.getEmailAddressAlt2();

            //�����A�h���X���.���ʒm���M�t���O = �i�����j�ڋq�s.���ʒm���M�t���O
            if (!l_mainAccountRow.getOrderExeFlagIsNull())
            {
                l_accInfoMultiMailAddressInfo.execMailFlag = "" + l_mainAccountRow.getOrderExeFlag();
            }

            //�����A�h���X���.�����ʒm���M�t���O = �i�����j�ڋq�s.�����ʒm���M�t���O
            if (!l_mainAccountRow.getOrderUnexeFlagIsNull())
            {
                l_accInfoMultiMailAddressInfo.unExecMailFlag = "" + l_mainAccountRow.getOrderUnexeFlag();
            }

            //�����A�h���X���.�d�v�A�����[�����M�t���O = �i�����j�ڋq�s.�d�v�A�����[�����M�t���O
            if (!l_mainAccountRow.getImportantConnectionMailFlagIsNull())
            {
                l_accInfoMultiMailAddressInfo.importantMailFlag =
                    "" + l_mainAccountRow.getImportantConnectionMailFlag();
            }

            //�����A�h���X���.�ē����[���Q���M�t���O = �i�����j�ڋq�s.�ē����[���Q���M�t���O
            if (!l_mainAccountRow.getInformationMail2FlagIsNull())
            {
                l_accInfoMultiMailAddressInfo.guidanceMailFlag2 =
                    "" + l_mainAccountRow.getInformationMail2Flag();
            }

            //�����A�h���X���.���[���A�h���X�Q�폜�t���O = false
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = false;

            //�����A�h���X���.���[���A�h���X�R�폜�t���O = false
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag3 = false;
        }

        log.exiting(STR_METHOD_NAME);
        return l_accInfoMultiMailAddressInfo;
    }

    /**
     * (create�،��S�ۃ��[�������J�ݏ��)<BR>
     * �،��S�ۃ��[�������J�ݏ����쐬����B<BR>
     * <BR>
     * �P�j�@@�،��S�ۃ��[�������J�ݏ��I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j����:�ڋq.is�،��S�ۃ��[�������J��()==true�̏ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�Q�|�P�j�@@�����S�ۃ��[�������e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@�@@[��������]<BR>
     * �@@�@@�@@�@@�����S�ۃ��[�������e�[�u��.����ID = �ڋq.getAccountId() and<BR>
     * �@@�@@�@@�@@�����S�ۃ��[�������e�[�u��.�J�ݓ� != null<BR>
     * <BR>
     * �@@�@@�@@[�\�[�g����]<BR>
     * �@@�@@�@@�@@�����S�ۃ��[�������e�[�u��.�J�ݓ�.asc<BR>
     * <BR>
     * �@@�@@�Q�|�Q�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�،��S�ۃ��[�������J�ݏ��I�u�W�F�N�g.�J�ݏ� = 1�F�J��<BR>
     * �@@�@@�@@�@@�@@�،��S�ۃ��[�������J�ݏ��I�u�W�F�N�g.�J�ݓ� =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�擾����List..get( 0 ).get�J�ݓ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@*�Y���s���Ȃ������ꍇ�́Anull<BR>
     * <BR>
     * <BR>
     * <BR>
     * �R�j�@@����:�ڋq.is�،��S�ۃ��[�������J��()==false�̏ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * <BR>
     * �@@�@@�R�|�P�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�،��S�ۃ��[�������J�ݏ��I�u�W�F�N�g.�J�ݏ� =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@0�F���J��<BR>
     * �@@�@@�،��S�ۃ��[�������J�ݏ��I�u�W�F�N�g.�J�ݓ� = null<BR>
     * <BR>
     * <BR>
     * �S�j�@@�،��S�ۃ��[�������J�ݏ��I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return WEB3AccInfoStockLoanAccountInfo
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoStockLoanAccountInfo createStockLoanAccountInfo(
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createStockLoanAccountInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@�،��S�ۃ��[�������J�ݏ��I�u�W�F�N�g�𐶐�����B
        WEB3AccInfoStockLoanAccountInfo l_accInfoStockLoanAccountInfo =
            new WEB3AccInfoStockLoanAccountInfo();

        //�Q�j����:�ڋq.is�،��S�ۃ��[�������J��()==true�̏ꍇ�A�ȉ��̏������s���B
        if (l_mainAccount.isSecuredLoanAccountOpen())
        {
            //�Q�|�P�j�@@�����S�ۃ��[�������e�[�u�����ȉ��̏����Ō�������B
            //[��������]
            //�����S�ۃ��[�������e�[�u��.����ID = �ڋq.getAccountId() and
            //�����S�ۃ��[�������e�[�u��.�J�ݓ� != null
            String l_strQuery = " account_id = ? and account_open_date is not null ";
            Long l_accountId = new Long(l_mainAccount.getAccountId());
            Object[] l_whereValues = {l_accountId};

            //[�\�[�g����]
            //�����S�ۃ��[�������e�[�u��.�J�ݓ�.asc
            List l_lisStockSecuredLoans = new ArrayList();
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisStockSecuredLoans = l_queryProcessor.doFindAllQuery(
                    StockSecuredLoanRow.TYPE,
                    l_strQuery,
                    " account_open_date asc ",
                    null,
                    l_whereValues);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�Q�|�Q�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
            //�،��S�ۃ��[�������J�ݏ��I�u�W�F�N�g.�J�ݏ� = 1�F�J��
            l_accInfoStockLoanAccountInfo.stockLoanAccOpenDiv =
                WEB3AccInfoStockLoanAccOpenDivDef.OPEN;

            //�،��S�ۃ��[�������J�ݏ��I�u�W�F�N�g.�J�ݓ� = �擾����List..get( 0 ).get�J�ݓ�
            //*�Y���s���Ȃ������ꍇ�́Anull
            if (l_lisStockSecuredLoans.isEmpty())
            {
                l_accInfoStockLoanAccountInfo.stockLoanAccOpenDate = null;
            }
            else
            {
                StockSecuredLoanRow l_stockSecuredLoanRow =
                    (StockSecuredLoanRow)l_lisStockSecuredLoans.get(0);
                l_accInfoStockLoanAccountInfo.stockLoanAccOpenDate =
                    l_stockSecuredLoanRow.getAccountOpenDate();
            }
        }
        else
        {
            //�R�j�@@����:�ڋq.is�،��S�ۃ��[�������J��()==false�̏ꍇ�A�ȉ��̏������s���B
            //�R�|�P�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
            //�،��S�ۃ��[�������J�ݏ��I�u�W�F�N�g.�J�ݏ� = 0�F���J��
            l_accInfoStockLoanAccountInfo.stockLoanAccOpenDiv =
                WEB3AccInfoStockLoanAccOpenDivDef.NOT_OPEN;

            //�،��S�ۃ��[�������J�ݏ��I�u�W�F�N�g.�J�ݓ� = null
            l_accInfoStockLoanAccountInfo.stockLoanAccOpenDate = null;
        }

        //�S�j�@@�،��S�ۃ��[�������J�ݏ��I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_accInfoStockLoanAccountInfo;
    }

    /**
     * (createCFD�������)<BR>
     * �w��ڋq�̂e�w�ڋq�C�e�w�����ԍ��f�[�^���A<BR>
     * CFD������񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�I�u�W�F�N�g�̐����B<BR>
     * �@@�@@�@@CFD�������I�u�W�F�N�g���i�[����ׂ̃I�u�W�F�N�g�����B<BR>
     * <BR>
     * <BR>
     * �Q�j�@@FX�V�X�e���R�[�h�̎擾<BR>
     * �@@�@@�@@��Е�FX�V�X�e�������e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�@@���Y���s���Ȃ������ꍇ�́Anull��ԋp<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@��Е�FX�V�X�e�������e�[�u��.�،���ЃR�[�h =<BR>
     * �@@�@@�@@�@@�@@�@@�ڋq.getInstitution().getInstitutionCode() And<BR>
     * �@@�@@�@@��Е�FX�V�X�e�������e�[�u��.���X�R�[�h =<BR>
     * �@@�@@�@@�@@�@@�@@�ڋq.getBranch().getBranchCode() And<BR>
     * �@@�@@�@@FX�V�X�e���敪 = 2<BR>
     * <BR>
     * �R�j�@@�Q�j�̏����Ŏ擾������Е�FX�V�X�e�������e�[�u���̗v�f���ɁA<BR>
     * �@@�@@�@@�@@�@@�@@�ȉ��̏������s��<BR>
     * <BR>
     * �@@���Q�j�Ń��R�[�h���擾�ł����ꍇ��<BR>
     * �@@�@@�@@�@@�@@�R�|�P�j�e�w���O�C���h�c�擾<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�ڋq�e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���Y���s���Ȃ������ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���������Ɏ��̗v�f��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�ڋq�e�[�u��.�،���ЃR�[�h =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ڋq.getInstitution().getInstitutionCode() And<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�ڋq�e�[�u��.���X�R�[�h =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ڋq.getBranch().getBranchCode() And<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�ڋq�e�[�u��.�e�w�V�X�e���R�[�h�@@=<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��Е�FX�V�X�e�������s.FX�V�X�e���R�[�h And<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�ڋq�e�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode()<BR>
     * �@@�@@�@@�@@�@@<BR>
     * �@@�@@�@@�@@�@@�R�|�Q�jFX�����ԍ��擾<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�����ԍ��e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�����ԍ��e�[�u��.�،���ЃR�[�h =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ڋq.getInstitution().getInstitutionCode() And<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�����ԍ��e�[�u��.���X�R�[�h =<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ڋq.getBranch().getBranchCode() And<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�����ԍ��e�[�u��.�e�w�V�X�e���R�[�h�@@=<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��Е�FX�V�X�e�������s.FX�V�X�e���R�[�h And<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�w�����ԍ��e�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode()<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�R�|�R�j�@@CFD�������I�u�W�F�N�g�𐶐����ȉ��̒ʂ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@CFD�������.CFD���O�C���h�c�F �e�w�ڋq�s.�e�w���O�C���h�c<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@CFD�������.CFD�����ԍ��F �e�w�����ԍ��s.�e�w�����ԍ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���R�|�Q�j�Ń��R�[�h���擾�ł����ꍇ�̂�CFD�������.CFD�����ԍ��ɒl���Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@CFD�������.FX�V�X�e���R�[�h�F<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��Е�FX�V�X�e�������s.FX�V�X�e���R�[�h<BR>
     * �@@�@@�@@�@@�@@<BR>
     * �@@�@@�@@�@@�@@�R�|�S�j�@@�P�j�Ő�������List�I�u�W�F�N�g��CFD�������I�u�W�F�N�g���i�[����B<BR>
     * <BR>
     * �S�jArrayList.toArray()�̖߂�l��ԋp<BR>
     * �@@�@@�@@�@@�@@�擾�����f�[�^��0���ꍇ�Anull��ԋp<BR>
     * �@@�@@�@@�@@�@@����ȊO�̏ꍇ�A�擾�������ʂ�ԋp<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@return WEB3AccInfoCfdAccountInfo[]
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoCfdAccountInfo[] createCFDAccountInfo(
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createCFDAccountInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@ArrayList�I�u�W�F�N�g�̐����B
        //CFD�������I�u�W�F�N�g���i
        List l_lisAccountInfos = new ArrayList();

        //�ڋq.getInstitution().getInstitutionCode()
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        //�ڋq.getBranch().getBranchCode()
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //�ڋq.getAccountCode()
        String l_strAccountCode = l_mainAccount.getAccountCode();

        //�Q�j�@@FX�V�X�e���R�[�h�̎擾
        //��Е�FX�V�X�e�������e�[�u�����ȉ��̏����Ō�������B
        //���Y���s���Ȃ������ꍇ�́Anull��ԋp
        //[����]
        //��Е�FX�V�X�e�������e�[�u��.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() And
        //��Е�FX�V�X�e�������e�[�u��.���X�R�[�h = �ڋq.getBranch().getBranchCode() And
        //FX�V�X�e���敪 = 2
        List l_lisCompFxConditions = null;
        try
        {
            String l_strCompFxConditionQuery =
                " institution_code = ? and branch_code = ? and fx_system_div = ? ";
            Object[] l_objCompFxConditionQuery =
                new Object[]{
                    l_strInstitutionCode,
                    l_strBranchCode,
                    WEB3FxSystemDivDef.CFD_SYSTEM};
            // �P�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisCompFxConditions = l_queryProcessor.doFindAllQuery(
                CompFxConditionRow.TYPE,
                l_strCompFxConditionQuery,
                l_objCompFxConditionQuery);

            if (l_lisCompFxConditions.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //�R�j�@@�Q�j�̏����Ŏ擾������Е�FX�V�X�e�������e�[�u���̗v�f���ɁA�ȉ��̏������s��
            for (int i = 0; i < l_lisCompFxConditions.size(); i++)
            {
                CompFxConditionRow l_compFxConditionRow =
                    (CompFxConditionRow)l_lisCompFxConditions.get(i);
                String l_strFxSystemCode = l_compFxConditionRow.getFxSystemCode();

                WEB3AccInfoCfdAccountInfo l_accInfoCfdAccountInfo =
                    new WEB3AccInfoCfdAccountInfo();
                //[����]
                //�e�w�ڋq�e�[�u��.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() And
                //�e�w�ڋq�e�[�u��.���X�R�[�h = �ڋq.getBranch().getBranchCode() And
                //�e�w�ڋq�e�[�u��.�e�w�V�X�e���R�[�h�@@= ��Е�FX�V�X�e�������s.FX�V�X�e���R�[�h And
                //�e�w�ڋq�e�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode()
                String l_strQuery =
                    " institution_code = ?"
                    + " and branch_code = ?"
                    + " and fx_system_code = ?"
                    + " and account_code = ? ";

                Object[] l_objQuery =
                    new Object[]{
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strFxSystemCode,
                        l_strAccountCode};
                //���Q�j�Ń��R�[�h���擾�ł����ꍇ��
                //�R�|�P�j�e�w���O�C���h�c�擾
                //�e�w�ڋq�e�[�u�����ȉ��̏����Ō�������B
                //���Y���s���Ȃ������ꍇ�́A
                //���������Ɏ��̗v�f��
                FxAccountRow l_fxAccountRow =
                    FxAccountDao.findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCode(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strFxSystemCode,
                        l_strAccountCode);

                if (l_fxAccountRow == null)
                {
                    continue;
                }

                //�R�|�Q�jFX�����ԍ��擾
                //�e�w�����ԍ��e�[�u�����ȉ��̏����Ō�������B
                List l_lisFxAccountCodeRows = l_queryProcessor.doFindAllQuery(
                    FxAccountCodeRow.TYPE,
                    l_strQuery,
                    l_objQuery);

                //�R�|�R�j�@@CFD�������I�u�W�F�N�g�𐶐����ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
                //CFD�������.CFD���O�C���h�c�F �e�w�ڋq�s.�e�w���O�C���h�c
                l_accInfoCfdAccountInfo.cfdLoginId =
                    l_fxAccountRow.getFxLoginId() + "";

                //CFD�������.CFD�����ԍ��F �e�w�����ԍ��s.�e�w�����ԍ�
                //���R�|�Q�j�Ń��R�[�h���擾�ł����ꍇ�̂�CFD�������.CFD�����ԍ��ɒl���Z�b�g
                if (l_lisFxAccountCodeRows.size() != 0)
                {
                    FxAccountCodeRow l_fxAccountCodeRow =
                        (FxAccountCodeRow)l_lisFxAccountCodeRows.get(0);
                    l_accInfoCfdAccountInfo.cfdAccountCode =
                        l_fxAccountCodeRow.getFxAccountCode();
                }

                //CFD�������.FX�V�X�e���R�[�h�F��Е�FX�V�X�e�������s.FX�V�X�e���R�[�h
                l_accInfoCfdAccountInfo.fxSystemCode = l_strFxSystemCode;

                //�R�|�S�j�@@�P�j�Ő�������List�I�u�W�F�N�g��CFD�������I�u�W�F�N�g���i�[����B
                l_lisAccountInfos.add(l_accInfoCfdAccountInfo);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�S�jArrayList.toArray()�̖߂�l��ԋp�擾�����f�[�^��0���ꍇ�Anull��ԋp
        if (l_lisAccountInfos.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3AccInfoCfdAccountInfo[] l_accInfoCfdAccountInfo =
            new WEB3AccInfoCfdAccountInfo[l_lisAccountInfos.size()];
        l_lisAccountInfos.toArray(l_accInfoCfdAccountInfo);
        log.exiting(STR_METHOD_NAME);
        return l_accInfoCfdAccountInfo;
    }

    /**
     * (create���[���A�h���X���)<BR>
     * ���[���A�h���X�����쐬����B<BR>
     * <BR>
     * �P�j�@@�ڋq���[���A�h���X�e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�@@�E�Y���s���Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�ڋq.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F�@@�ڋq.���X�R�[�h<BR>
     * �@@�@@�@@�����R�[�h�F�@@�ڋq.�����R�[�h<BR>
     * �@@�@@[�\�[�g����]<BR>
     * �@@�@@�@@���[���A�h���X�ԍ�<BR>
     * <BR>
     * �Q)�@@ArrayList�I�u�W�F�N�g����<BR>
     * <BR>
     * �R�j�@@�P�j�̖߂�l�̗v�f���ALoop�������s���B<BR>
     * �@@�R�|�P�j���[���A�h���X���I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�R�|�Q�j�@@�ȉ��̒ʂ�v���p�e�B���Z�b�g�B<BR>
     * �@@�@@���[���A�h���X���.���[���A�h���X�ԍ� = �ڋq���[���A�h���X.���[���A�h���X�ԍ�<BR>
     * �@@�@@���[���A�h���X���.���[���A�h���X�敪 = �ڋq���[���A�h���X.�A�h���X�敪<BR>
     * �@@�@@���[���A�h���X���.���[���A�h���X = �ڋq���[���A�h���X.���[���A�h���X<BR>
     * �@@�R�|�R�j�@@ArrayList�I�u�W�F�N�g.add(���[���A�h���X���I�u�W�F�N�g)<BR>
     * <BR>
     * �S�j�@@ArrayList�I�u�W�F�N�g�����[���A�h���X���[]�ɕϊ�����B<BR>
     * <BR>
     * �T�j�@@�S�j�ŕϊ��������[���A�h���X���[]��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@return WEB3AccInfoMailAddressInfoUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoMailAddressInfoUnit[] createMailAddressInfo(
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMailAddressInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@ArrayList�I�u�W�F�N�g�̐����B
        List l_lisMailAddressInfoUnits = new ArrayList();
        //�ڋq.getInstitution().getInstitutionCode()
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        //�ڋq.getBranch().getBranchCode()
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //�ڋq.getAccountCode()
        String l_strAccountCode = l_mainAccount.getAccountCode();
        //�P�j�@@�ڋq���[���A�h���X�e�[�u�����ȉ��̏����Ō�������B
        String l_strQuery =
            " institution_code = ?"
            + " and branch_code = ?"
            + " and account_code = ? ";

        Object[] l_objQuery =
            new Object[]{
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode};
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisAccountMailAddressRows = l_queryProcessor.doFindAllQuery(
                AccountMailAddressRow.TYPE,
                l_strQuery,
                " email_address_number asc ",
                null,
                l_objQuery);

            if (l_lisAccountMailAddressRows.isEmpty())
            {
                //�Y���s���Ȃ������ꍇ�́Anull��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            WEB3AccInfoMailAddressInfoUnit l_mailAddressInfoUnit = null;
            for (int i = 0; i < l_lisAccountMailAddressRows.size(); i++)
            {
                AccountMailAddressRow l_accountMailAddressRow =
                    (AccountMailAddressRow)l_lisAccountMailAddressRows.get(i);
                l_mailAddressInfoUnit = new WEB3AccInfoMailAddressInfoUnit();
                //���[���A�h���X���.���[���A�h���X = �ڋq���[���A�h���X.���[���A�h���X
                l_mailAddressInfoUnit.mailAddress = l_accountMailAddressRow.getEmailAddress();
                //���[���A�h���X���.���[���A�h���X�ԍ� = �ڋq���[���A�h���X.���[���A�h���X�ԍ�
                l_mailAddressInfoUnit.mailAddressNo = l_accountMailAddressRow.getEmailAddressNumber() + "";
                //���[���A�h���X���.���[���A�h���X�敪 = �ڋq���[���A�h���X.�A�h���X�敪
                l_mailAddressInfoUnit.mailAddressDiv = l_accountMailAddressRow.getAddressDiv();
                l_lisMailAddressInfoUnits.add(l_mailAddressInfoUnit);
            }
            WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoUnits =
                new WEB3AccInfoMailAddressInfoUnit[l_lisMailAddressInfoUnits.size()];
            l_lisMailAddressInfoUnits.toArray(l_mailAddressInfoUnits);

            log.exiting(STR_METHOD_NAME);
            return l_mailAddressInfoUnits;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (create���[����ʏ��)<BR>
     * ���[����ʏ����쐬����B<BR>
     * <BR>
     * �P�j�@@���[����ʃe�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�E�Y���s���Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�ڋq.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F�@@�ڋq.���X�R�[�h<BR>
     * �@@�@@�@@�����R�[�h�F�@@�ڋq.�����R�[�h<BR>
     * <BR>
     * �@@�@@[�\�[�g����]<BR>
     * �@@�@@�@@���[����ʋ敪<BR>
     * <BR>
     * �Q�j�@@ArrayList�I�u�W�F�N�g����<BR>
     * <BR>
     * �R�j�@@�P�j�̖߂�l�̗v�f���ALoop�������s���B<BR>
     * �@@�R�|�P�j���[����ʏ��I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�R�|�Q�j�ȉ��̒ʂ�v���p�e�B���Z�b�g�B<BR>
     * �@@�@@���[����ʏ��.���[���A�h���X�ԍ� = ���[�����.���[���A�h���X�ԍ�<BR>
     * �@@�@@���[����ʏ��.���[����ʔԍ� = ���[�����.���[����ʔԍ�<BR>
     * �@@�R�|�R�jArrayList�I�u�W�F�N�g.add(���q�l���������I�u�W�F�N�g)<BR>
     * <BR>
     * �S�j�@@ArrayList�I�u�W�F�N�g�����[����ʏ��[]�ɕϊ�����B<BR>
     * <BR>
     * �T�j�@@�S�j�ŕϊ��������[����ʏ��[]��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@return WEB3AccInfoMailAddressTypeUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoMailAddressTypeUnit[] createMailAddressTypeInfo(
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMailAddressInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@ArrayList�I�u�W�F�N�g�̐����B
        List l_lisMailAddressTypeUnits = new ArrayList();
        //�ڋq.getInstitution().getInstitutionCode()
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        //�ڋq.getBranch().getBranchCode()
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //�ڋq.getAccountCode()
        String l_strAccountCode = l_mainAccount.getAccountCode();
        //�P�j�@@�ڋq���[���A�h���X�e�[�u�����ȉ��̏����Ō�������B
        String l_strQuery =
            " institution_code = ?"
            + " and branch_code = ?"
            + " and account_code = ? ";

        Object[] l_objQuery =
            new Object[]{
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode};
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisMailAssortmentRows = l_queryProcessor.doFindAllQuery(
                MailAssortmentRow.TYPE,
                l_strQuery,
                " mail_assortment_div asc ",
                null,
                l_objQuery);

            if (l_lisMailAssortmentRows.isEmpty())
            {
                //�Y���s���Ȃ������ꍇ�́Anull��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            WEB3AccInfoMailAddressTypeUnit l_mailAddressTypeUnit = null;
            for (int i = 0; i < l_lisMailAssortmentRows.size(); i++)
            {
                MailAssortmentRow l_accountMailAddressRow =
                    (MailAssortmentRow)l_lisMailAssortmentRows.get(i);
                l_mailAddressTypeUnit = new WEB3AccInfoMailAddressTypeUnit();
                //���[����ʏ��.���[���A�h���X�ԍ� = ���[�����.���[���A�h���X�ԍ�
                l_mailAddressTypeUnit.mailAddressNo = l_accountMailAddressRow.getEmailAddressNumber() + "";
                //���[����ʏ��.���[����ʔԍ� = ���[�����.���[����ʔԍ�
                l_mailAddressTypeUnit.mailAddressTypeNo = l_accountMailAddressRow.getMailAssortmentDiv();
                l_lisMailAddressTypeUnits.add(l_mailAddressTypeUnit);
            }
            WEB3AccInfoMailAddressTypeUnit[] l_mailAddressTypeUnits =
                new WEB3AccInfoMailAddressTypeUnit[l_lisMailAddressTypeUnits.size()];
            l_lisMailAddressTypeUnits.toArray(l_mailAddressTypeUnits);

            log.exiting(STR_METHOD_NAME);
            return l_mailAddressTypeUnits;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
}
@
