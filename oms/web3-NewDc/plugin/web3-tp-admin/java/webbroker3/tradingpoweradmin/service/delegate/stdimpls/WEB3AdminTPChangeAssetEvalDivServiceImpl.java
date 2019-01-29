head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeAssetEvalDivServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͌v�Z���@@�ύX�T�[�r�XImpl�N���X(WEB3AdminTPChangeAssetEvalDivServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTransactionCategoryCodeDef;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivCompleteResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPChangeAssetEvalDivService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�]�͌v�Z���@@�ύX�T�[�r�XImpl�N���X)
 *
 * �]�͌v�Z���@@�ύX�i�a��،��]�����敪�ύX�j�̃C���^�[�t�F�[�X�̎����N���X�B
 * �ėp�N���C�A���g���N�G�X�g�T�[�r�X�N���X���g���B�i���ݖ��g�p�j
 */
public class WEB3AdminTPChangeAssetEvalDivServiceImpl extends WEB3ClientRequestService implements WEB3AdminTPChangeAssetEvalDivService
{
   /**
    *
    * ���O�B
    */
    private final WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPChangeAssetEvalDivServiceImpl.class);

   /**
    * @@roseuid 41DBC9CD0234
    */
   public WEB3AdminTPChangeAssetEvalDivServiceImpl()
   {

   }

   /**
    * �]�͌v�Z���@@�ύX�������s���B
    *
    * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B
    *
    * ���]�͌v�Z���@@�ύX���̓��N�G�X�g�̏ꍇ
    * �@@this.get�]�͌v�Z���@@�ύX���͉��()���\�b�h���R�[������B
    *
    * ���]�͌v�Z���@@�ύX�m�F���N�G�X�g�̏ꍇ
    * �@@this.validate�]�͌v�Z���@@�ύX()���\�b�h���R�[������B
    *
    * ���]�͌v�Z���@@�ύX�������N�G�X�g�̏ꍇ
    * �@@this.submit�]�͌v�Z���@@�ύX()���\�b�h���R�[������B
    * @@param l_request - ���N�G�X�g
    *
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41B8072E0352
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request)
    throws WEB3BaseException
   {
       final String METHOD_NAME = "execute(WEB3GenRequest)";

       log.entering(METHOD_NAME);

       WEB3GenResponse l_response = null;

       if(l_request instanceof WEB3AdminTPChangeAssetEvalDivInputRequest)
       {
           l_response =  this.getChangeAssetEvalDivInputInfo((WEB3AdminTPChangeAssetEvalDivInputRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPChangeAssetEvalDivConfirmRequest)
       {
           l_response = this.validateChangeAssetEvalDiv((WEB3AdminTPChangeAssetEvalDivConfirmRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPChangeAssetEvalDivCompleteRequest)
       {
           l_response =  this.submitChangeAssetEvalDiv((WEB3AdminTPChangeAssetEvalDivCompleteRequest)l_request);
       }
       else
       {
           //�\�����ʃG���[
           throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, METHOD_NAME, new IllegalArgumentException());
       }
       log.exiting(METHOD_NAME);
       return l_response;


   }

   /**
    * (get�]�͌v�Z���@@�ύX���͉��)
    * �]�͌v�Z���@@�ύX���͉�ʍ쐬�������s���B
    *
    * �P�j���N�G�X�g���͍��ڃ`�F�b�N�B
    *
    * �Q�j�ȉ����`�F�b�N����B
    * �E�Ǘ��Ҍ���
    * �E�Ǘ��ҕ��X����
    * �E�M�p�ڋq�łȂ��i�����q�ł���j
    *
    * �R�jthis.get�ڋq�]�͏���Params(�ڋqID)���Ă�.
    *
    * �S�j�]�͌v�Z���@@�ύX���͉�ʃ��X�|���X���쐬��
    * �v���p�e�B�ɒl���Z�b�g����B
    *
    * �a��،��]���敪 = �ڋq�]�͏���Params.get�a��،��]���敪
    *
    * �T�j���X�|���X��ԋp����B
    * @@param l_request - �]�͌v�Z���@@�ύX���͉�ʃ��N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivInputResponse
    * @@roseuid 41B8FB970058
    */
   protected WEB3AdminTPChangeAssetEvalDivInputResponse getChangeAssetEvalDivInputInfo(WEB3AdminTPChangeAssetEvalDivInputRequest l_request)
   {
       final String METHOD_NAME = "getChangeAssetEvalDivInputInfo";
       WEB3AdminTPChangeAssetEvalDivInputResponse l_response = new WEB3AdminTPChangeAssetEvalDivInputResponse();

       try
       {
           //�P�j���N�G�X�g���͍��ڃ`�F�b�N�B
           l_request.validate();

           //�Q�j�ȉ����`�F�b�N����B
           //�E	�Ǘ��Ҍ���
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);
           String l_strInstCode = l_admin.getInstitutionCode();
           //�E�Ǘ��ҕ��X����
           l_admin.validateBranchPermission(l_request.branchCode);

           //�E�M�p�ڋq�̏ꍇ�G���[
           WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCode, l_request.accountCode);
           if(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
           {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01892, METHOD_NAME);
           }

           //�R�jthis.get�ڋq�]�͏���Params(�ڋqID)���Ă�.
           TradingpowerCalcConditionRow l_row = this.getTradingPowerCalcConditionParams(l_account.getAccountId());
           //�]�͌v�Z���@@�ύX���͉�ʃ��X�|���X���쐬��
           //�v���p�e�B�ɒl���Z�b�g����B
           l_response.accountName = l_account.getDisplayAccountName();
           l_response.assetEvalDiv = l_row.getAssetEvaluationDiv();
           //���X�|���X��ԋp����B

       }
       catch(Exception e)
       {
           //�J�X�^�}�C�Y��`�ς݃G���[�̏ꍇ
           if(e instanceof WEB3BaseException)
           {
               l_response.errorInfo = ((WEB3BaseException)e).getErrorInfo();
           }
           else
           {
               //�\�����ʃG���[
               l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           }

           log.error(l_response.errorInfo.error_message);
           //log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, e);

       }
       return l_response;

   }

   /**
    * (validate�]�͌v�Z���@@�ύX)
    * �]�͌v�Z���@@�ύX�m�F�������s���B
    *
    * �P�j���N�G�X�g���͍��ڃ`�F�b�N�B
    *
    * �Q�j�ȉ����`�F�b�N����B
    * �E�Ǘ��Ҍ���
    * �E�Ǘ��ҕ��X����
    * �E�M�p�ڋq�łȂ��i�����q�ł���j
    *
    * �R�j�]�͌v�Z���@@�ύX�m�F���X�|���X���쐬����B
    *
    * �S�j���X�|���X��ԋp����B
    * @@param l_request - �]�͌v�Z���@@�ύX�m�F���N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivConfirmRespons
    * e
    * @@roseuid 41C1009A01E1
    */
   protected WEB3AdminTPChangeAssetEvalDivConfirmResponse validateChangeAssetEvalDiv(WEB3AdminTPChangeAssetEvalDivConfirmRequest l_request)
   {
       final String METHOD_NAME = "validateChangeAssetEvalDiv";
       WEB3AdminTPChangeAssetEvalDivConfirmResponse l_response = new WEB3AdminTPChangeAssetEvalDivConfirmResponse();

       try
       {
           //�P�j���N�G�X�g���͍��ڃ`�F�b�N�B
           l_request.validate();

           //�Q�j�ȉ����`�F�b�N����B
           //�E	�Ǘ��Ҍ���
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, true);
           String l_strInstCode = l_admin.getInstitutionCode();
           //�E�Ǘ��ҕ��X����
           l_admin.validateBranchPermission(l_request.branchCode);

           //�E�M�p�ڋq�̏ꍇ�G���[
           WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCode, l_request.accountCode);
           if(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
           {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01892, METHOD_NAME);
           }
       }
       catch(Exception e)
       {
           //�J�X�^�}�C�Y��`�ς݃G���[�̏ꍇ
           if(e instanceof WEB3BaseException)
           {
               l_response.errorInfo = ((WEB3BaseException)e).getErrorInfo();
           }
           else
           {
               //�\�����ʃG���[
               l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           }

           log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, e);

       }
       return l_response;

   }

   /**
    * (submit�]�͌v�Z���@@�ύX)
    * �]�͌v�Z���@@�ύX�����������s���B
    *
    * �P�j���N�G�X�g���͍��ڃ`�F�b�N�B
    *
    * �Q�j�ȉ����`�F�b�N����B
    * �E�Ǘ��Ҍ���
    * �E�Ǘ��ҕ��X����
    * �E�M�p�ڋq�łȂ��i�����q�ł���j
    * �E�Ïؔԍ�
    *
    * �R�jthis.update�ڋq�]�͏���Params()���Ă�.
    *
    * �����Ɉȉ���^����B
    * �E�ڋqID = �Q�j�Ŏ擾�����ڋq
    * �E�a��،��]���敪 = ���N�G�X�g.�a��،��]���敪
    * �E�X�V����=ThreadLocalSystemRegistry.getAttribute(����Ǘ�.TIMESTAMP_TAG)�̖߂�?
    *
    *
    * �S�j�]�͌v�Z���@@�ύX�������X�|���X���쐬��
    * �v���p�e�B�ɒl���Z�b�g����B
    *
    * �X�V���� = �R�j��DB�ɃZ�b�g�����X�V����
    *
    * �T�j���X�|���X��ԋp����B
    * @@param l_request - �]�͌v�Z���@@�ύX�������N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivCompleteRespon
    * se
    * @@roseuid 41B8FBD103C3
    */
   protected WEB3AdminTPChangeAssetEvalDivCompleteResponse submitChangeAssetEvalDiv(WEB3AdminTPChangeAssetEvalDivCompleteRequest l_request)
   {
       final String METHOD_NAME = "submitChangeAssetEvalDiv(WEB3AdminTPChangeAssetEvalDivCompleteRequest l_request)";
       WEB3AdminTPChangeAssetEvalDivCompleteResponse l_response = new WEB3AdminTPChangeAssetEvalDivCompleteResponse();

       try
       {
           //�P�j���N�G�X�g���͍��ڃ`�F�b�N�B
           l_request.validate();

           //�Q�j�ȉ����`�F�b�N����B
           //�E	�Ǘ��Ҍ���
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, true);
           String l_strInstCode = l_admin.getInstitutionCode();
           //�E�Ǘ��ҕ��X����
           l_admin.validateBranchPermission(l_request.branchCode);

           //�E�M�p�ڋq�̏ꍇ�G���[
           WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCode, l_request.accountCode);
           if(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
           {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01892, METHOD_NAME);
           }

           //�E�Ïؔԍ�
           l_admin.validateTradingPassword(l_request.adminPassword);

           //�R�jthis.update�ڋq�]�͏���Params()���Ă�.
           Timestamp l_timestamp = this.updateTradingpowerCalcConditionParams(l_admin, l_account.getAccountId(), l_request.assetEvalDiv);

           //�ǉ�
           //�ڋq�]�͌v�Z�����e�[�u�����A�b�v�f�[�g������A
           //�]�͌v�Z�L���[�e�[�u���ɓ��Y�ڋq���R�[�h���P��
           //�C���T�[�g���鏈����ǉ�����B
           WEB3TPTradingPowerReCalcService l_service = (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);           
           WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)l_account.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);               
           l_service.reCalcTradingPower(l_subAccount);           
           
           //�S�j�]�͌v�Z���@@�ύX�������X�|���X���쐬��
           //�v���p�e�B�ɒl���Z�b�g����B
           l_response.lastUpdatedTime = l_timestamp;

       }
       catch(Exception e)
       {
           //�J�X�^�}�C�Y��`�ς݃G���[�̏ꍇ
           if(e instanceof WEB3BaseException)
           {
               l_response.errorInfo = ((WEB3BaseException)e).getErrorInfo();
           }
           else
           {
               //�\�����ʃG���[
               l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           }

           log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, e);

       }

       return l_response;

   }

   /**
    * (update�ڋq�]�͏���Params)
    * �ڋq�]�͏����e�[�u�����ȉ��̏����A�l�ōX�V����B
    *
    * �����F
    * �E�ڋqID = ����.�ڋqID
    *
    * �l�F
    * �E�a��،��]�����敪 = ����.�a��،��]�����敪
    * �E�X�V���� =
    * ThreadLocalSystemRegistry.getAttribute(������ԊǗ�.TIMESTAMP_TAG)�̖߂�l
    * @@param l_lngAccountId - (�ڋqID)
    * @@param l_strAssetEvalDiv - (�a��،��]�����敪)
    * @@return Timestamp
    * @@roseuid 41C7E6790395
    */
   protected Timestamp updateTradingpowerCalcConditionParams(WEB3Administrator l_admin, long l_lngAccountId, String l_strAssetEvalDiv)
    throws WEB3SystemLayerException
   {
       final String METHOD_NAME = "updateTradingpowerCalcConditionParams(long l_lngAccountId, String l_strAssetEvalDiv)";
       int l_intUdateCount = 0;
       Timestamp l_timestamp = null;

       try
       {
	       final String l_strWhere = new String("account_id = ?");
	       final Object[] l_bindVars = new Object[]{new Long(l_lngAccountId)};

	       l_timestamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
	               WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

	       if(l_timestamp == null)
	       {
	           log.debug("l_timestamp is null.");
	       }

	       Map l_updateMap = new HashMap();
	       l_updateMap.put("asset_evaluation_div", l_strAssetEvalDiv);
	       l_updateMap.put("last_updater", l_admin.getAdministratorCode());
	       l_updateMap.put("last_updated_timestamp", l_timestamp);

	       log.debug("asset_evaluation_div=" + l_updateMap.get("asset_evaluation_div"));
	       log.debug("last_updater=" + l_updateMap.get("last_updater"));
	       log.debug("last_updated_timestamp=" + l_updateMap.get("last_updated_timestamp"));

	       QueryProcessor l_qp = Processors.getDefaultProcessor();
	        l_intUdateCount = l_qp.doUpdateAllQuery(TradingpowerCalcConditionRow.TYPE, l_strWhere, l_bindVars, l_updateMap);


       }
       //DBAccess�G���[�̏ꍇ
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
       catch(Exception e)
       {
           log.error(e.getMessage(), e);
           //�\�����Ȃ��G���[
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, METHOD_NAME);
       }

       if(l_intUdateCount == 0)
       {
           //TODO ErrorInfo��`�m�F�@@SYSTEM_ERROR_80005�i�Y���f�[�^�Ȃ��j��OK?
           log.error(METHOD_NAME + " updating data not found accountId=" + l_lngAccountId);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
       }
       return l_timestamp;

    }

   /**
    * (get�ڋq�]�͏���Params)
    * �ڋq�]�͏����e�[�u�����A�ȉ��̏����Ń��R�[�h�����������ʂ�Ԃ��B
    *
    * �����F
    * �ڋqID = �ڋq.�ڋqID
    * @@param l_lngAccountId - (�ڋqID)
    * @@return TradingPowerCalcConditionRow
    * @@roseuid 41C940E50038
    */
   protected TradingpowerCalcConditionRow getTradingPowerCalcConditionParams(long l_lngAccountId)
    throws WEB3SystemLayerException
   {
       final String METHOD_NAME = "getTradingPowerCalcConditionParams";
       try
       {
           TradingpowerCalcConditionRow l_row = TradingpowerCalcConditionDao.findRowByAccountId(l_lngAccountId);
           if(l_row != null)
           {
               return l_row;

           }
           //TODO ErrorInfo��`�m�F�@@SYSTEM_ERROR_80005�i�Y���f�[�^�Ȃ��j��OK?
           log.error(METHOD_NAME + " updating data not found accountId=" + l_lngAccountId);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
       }
       catch(DataFindException dfe)
       {
           //TODO ErrorInfo��`�m�F�@@SYSTEM_ERROR_80005�i�Y���f�[�^�Ȃ��j��OK?
           log.error(METHOD_NAME + " updating data not found accountId=" + l_lngAccountId);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
       }
       //DBAccess�G���[�̏ꍇ
       catch(DataException de)
       {
           log.error(METHOD_NAME + de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
   }

}
@
