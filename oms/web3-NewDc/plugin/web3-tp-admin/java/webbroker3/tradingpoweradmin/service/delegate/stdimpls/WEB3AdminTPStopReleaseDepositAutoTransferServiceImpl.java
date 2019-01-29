head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPStopReleaseDepositAutoTransferServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۏ؋������U�֒�~�o�^/�����T�[�r�XImpl�N���X(WEB3AdminTPStopReleaseDepositAutoTransferServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.ProcessManagementDao;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpoweradmin.data.DepositAutotransferStopDao;
import webbroker3.tradingpoweradmin.data.DepositAutotransferStopPK;
import webbroker3.tradingpoweradmin.data.DepositAutotransferStopParams;
import webbroker3.tradingpoweradmin.data.DepositAutotransferStopRow;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPDepositAutoTransferStopRegistDivDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPProcessManagementIdDivDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTransactionCategoryCodeDef;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPDepositAutoTransferStopInfo;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindDepositAutoTransferStopRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindDepositAutoTransferStopResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferInputResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPStopReleaseDepositAutoTransferService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �ۏ؋������U�֒�~�o�^/�����T�[�r�XImpl�N���X
 */
public class WEB3AdminTPStopReleaseDepositAutoTransferServiceImpl extends WEB3ClientRequestService implements WEB3AdminTPStopReleaseDepositAutoTransferService
{
    /**
     * ���O�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPChangeCalcControlServiceImpl.class);

   /**
    * @@roseuid 41DBCAAE005F
    */
   public WEB3AdminTPStopReleaseDepositAutoTransferServiceImpl()
   {

   }

   /**
    * �ۏ؋������U�֒�~�o�^/�����������s���B
    *
    * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B
    *
    * ���ۏ؋������U�֒�~�o�^���͉�ʃ��N�G�X�g�̏ꍇ
    * �@@this.get�ۏ؋������U�֒�~�o�^���͉��()���\�b�h���R�[������B
    *
    * ���ۏ؋������U�֒�~�o�^�m�F���N�G�X�g�̏ꍇ
    * �@@this.validate�ۏ؋������U�֒�~�o�^()���\�b�h���R�[������B
    *
    * ���ۏ؋������U�֒�~�o�^�������N�G�X�g�̏ꍇ
    * �@@this.submit�ۏ؋������U�֒�~�o�^()���\�b�h���R�[������B
    *
    * ���ۏ؋������U�֒�~�ڋq�������N�G�X�g�̏ꍇ
    * �@@this.get�ۏ؋������U�֒�~�ڋq��������()���\�b�h���R�[������B
    *
    * ���ۏ؋������U�֒�~�����m�F���N�G�X�g�̏ꍇ
    * �@@this.validate�ۏ؋������U�֒�~����()���\�b�h���R�[������B
    *
    * ���ۏ؋������U�֒�~�o�^�������N�G�X�g�̏ꍇ
    * �@@this.submit�ۏ؋������U�֒�~����()���\�b�h���R�[������B
    * @@param l_request - ���N�G�X�g
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41BE539902FB
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request)
   throws WEB3BaseException
   {
       final String METHOD_NAME = "execute(WEB3GenRequest)";

       log.entering(METHOD_NAME);

       WEB3GenResponse l_response = null;

       if(l_request instanceof WEB3AdminTPStopDepositAutoTransferInputRequest)
       {
           l_response =  this.getDepositAutoTransferInputInfo((WEB3AdminTPStopDepositAutoTransferInputRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPStopDepositAutoTransferConfirmRequest)
       {
           l_response = this.validateStopDepositAutoTransfer((WEB3AdminTPStopDepositAutoTransferConfirmRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPStopDepositAutoTransferCompleteRequest)
       {
           l_response =  this.submitStopDepositAutoTransfer((WEB3AdminTPStopDepositAutoTransferCompleteRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPFindDepositAutoTransferStopRequest)
       {
           l_response =  this.findDepositAutoTransferStops((WEB3AdminTPFindDepositAutoTransferStopRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPReleaseDepositAutoTransferConfirmRequest)
       {
           l_response = this.validateReleaseDepositAutoTransfer((WEB3AdminTPReleaseDepositAutoTransferConfirmRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPReleaseDepositAutoTransferCompleteRequest)
       {
           l_response =  this.submitReleaseDepositAutoTransfer((WEB3AdminTPReleaseDepositAutoTransferCompleteRequest)l_request);
       }
       else
       {
           log.error(l_request, "Unknown Request=."+ l_request.getClass().getName(), null, new IllegalArgumentException());
           //�\�����ʃG���[
           throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, METHOD_NAME, new IllegalArgumentException());
       }
       log.exiting(METHOD_NAME);
       return l_response;
   }

   /**
    * (get�ۏ؋������U�֒�~�o�^���͉��)
    * �ۏ؋������U�֒�~�o�^���͉�ʎ擾�������s���B
    *
    * �P�j���N�G�X�g���͍��ڃ`�F�b�N�B
    *
    * �Q�j�ȉ����`�F�b�N����B
    * �E�Ǘ��Ҍ���
    * �E�Ǘ��ҕ��X����
    * �E�ڋq�̑��݃`�F�b�N
    *�E�M�p�ڋq�`�F�b�N
    *
    * �R�j�ۏ؋������U�֒�~�o�^���͉�ʃ��X�|���X���쐬��
    * �v���p�e�B�ɒl���Z�b�g����B
    *
    * �E���X�R�[�h = �ڋq.get���X�R�[�h()
    * �E�ڋq�R�[�h = �ڋq.get�ڋq�R�[�h()
    * �E�ڋq�� = �ڋq.get�ڋq��()
    *
    * �T�j���X�|���X��ԋp����B
    * @@param l_request - �ۏ؋������U�֒�~�o�^���͉�ʃ��N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferInputResp
    * onse
    * @@roseuid 41BE51F202DC
    */
   protected WEB3AdminTPStopDepositAutoTransferInputResponse getDepositAutoTransferInputInfo(WEB3AdminTPStopDepositAutoTransferInputRequest l_request)
   {
       final String METHOD_NAME = "findTradingPowerCalcControls(WEB3AdminTPFindCalcControlRequest)";

       WEB3AdminTPStopDepositAutoTransferInputResponse l_response = new WEB3AdminTPStopDepositAutoTransferInputResponse();

       try
       {
           //�P�j���N�G�X�g���͍��ڃ`�F�b�N�B
           l_request.validate();

           //�Q�j�ȉ����`�F�b�N����B
           //�E	�Ǘ��Ҍ���
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);
           //�E�Ǘ��ҕ��X����
           l_admin.validateBranchPermission(l_request.branchCode);
           String l_strInstCode = l_admin.getInstitutionCode();
           //�E�ڋq�̑��݃`�F�b�N
           //�E�M�p�ڋq�`�F�b�N
           WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCode, l_request.accountCode);
           if(!(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)))
           {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00747, METHOD_NAME);
           }

           //�R�j�ۏ؋������U�֒�~�o�^���͉�ʃ��X�|���X���쐬��
           //�v���p�e�B�ɒl���Z�b�g����B
           l_response.accountName = l_account.getDisplayAccountName();

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
    * (validate�ۏ؋������U�֒�~�o�^)
    * �ۏ؋������U�֒�~�o�^�m�F�������s���B
    *
    * �P�j���N�G�X�g���͍��ڃ`�F�b�N�B
    *
    * �Q�j�ȉ����`�F�b�N����B
    * �E�Ǘ��Ҍ���
    *�E�Ǘ��ҕ��X����
    * �E�ڋq�̑��݁A�M�p�ڋq�`�F�b�N
    * �E�Ώیڋq�����ɕۏ؋������U�֒�~�ݒ肪�s���Ă��邩�ǂ����`�F�b�N�B
    * this.validate���o�^()���ĂԁB
    * �E��~���Ԃ̃`�F�b�N
    * this.validate��~����()���Ă�
    *
    * �R�j�ۏ؋������U�֒�~�o�^�m�F���X�|���X���쐬����B
    *
    * �S�j���X�|���X��ԋp����B
    * @@param l_request - �ۏ؋������U�֒�~�����������N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferConfirmRe
    * sponse
    * @@roseuid 41C90D5A0038
    */
   protected WEB3AdminTPStopDepositAutoTransferConfirmResponse validateStopDepositAutoTransfer(WEB3AdminTPStopDepositAutoTransferConfirmRequest l_request)
   {
       final String METHOD_NAME = "validateChangeTradingPowerCalcControl(WEB3AdminTPChangeCalcControlConfirmRequest l_request)";

       WEB3AdminTPStopDepositAutoTransferConfirmResponse l_response = new WEB3AdminTPStopDepositAutoTransferConfirmResponse();

       WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

       try
       {
           //�P�j���N�G�X�g���͍��ڃ`�F�b�N�B
           l_request.validate();

           //�Q�j�ȉ����`�F�b�N����B
           //�E	�Ǘ��Ҍ���
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, true);
           //�E�Ǘ��ҕ��X����
           l_admin.validateBranchPermission(l_request.branchCode);
           String l_strInstCode = l_admin.getInstitutionCode();
           //�E�ڋq�̑��݃`�F�b�N
           //�E�M�p�ڋq�`�F�b�N
           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCode, l_request.accountCode);
           if(!(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)))
           {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00747, METHOD_NAME);
           }

           //�Ώیڋq�����ɕۏ؋������U�֒�~�ݒ肪�s���Ă��邩�ǂ����`�F�b�N�B
           //this.validate���o�^()���ĂԁB
           this.validateAlreadyRegistered(l_account.getAccountId());

           //�E��~���Ԃ̃`�F�b�N
           //this.validate��~����()���Ă�
           this.validateStopTerm(
                    l_request.autotransferStopStart,
                    l_request.autotransferStopEnd,
                    l_strInstCode,
                    l_request.branchCode);

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
    * (submit�ۏ؋������U�֒�~�o�^)
    * �ۏ؋������U�֒�~�o�^�����������s���B
    *
    * �P�j���N�G�X�g���͍��ڃ`�F�b�N�B
    *
    * �Q�j�ȉ����`�F�b�N����B
    * �E�Ǘ��Ҍ���
    * �E�ڋq�̑��݁A�M�p�ڋq�`�F�b�N
    * �E�Ώیڋq�����ɕۏ؋������U�֒�~�ݒ肪�s���Ă��邩�ǂ����`�F�b�N�B
    * this.validate���o�^()���ĂԁB
    * �E��~���Ԃ̃`�F�b�N
    * this.validate��~����()���Ă�
    * �E�Ïؔԍ��̃`�F�b�N
    *
    * �R�jthis.insert�ۏ؋������U�֒�~Params()���ĂԁB
    *
    * �S�j�ۏ؋������U�֒�~�o�^�������X�|���X���쐬��
    * ���X�|���X�̃v���p�e�B���ȉ��̂悤�ɃZ�b�g����B
    *
    * �E�X�V���� = DB�X�V���ɃZ�b�g�����X�V����
    *
    * �T�j���X�|���X��ԋp����B
    * @@param l_request - �ۏ؋������U�֒�~�o�^�������N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferCompleteR
    * esponse
    * @@roseuid 41C127B70378
    */
   protected WEB3AdminTPStopDepositAutoTransferCompleteResponse submitStopDepositAutoTransfer(WEB3AdminTPStopDepositAutoTransferCompleteRequest l_request)
   {
       final String METHOD_NAME = "submitChangeTradingPowerCalcControl(WEB3AdminTPChangeCalcControlCompleteRequest l_request)";

       WEB3AdminTPStopDepositAutoTransferCompleteResponse l_response = new WEB3AdminTPStopDepositAutoTransferCompleteResponse();

       WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

       try
       {
           //�P�j���N�G�X�g���͍��ڃ`�F�b�N�B
           l_request.validate();

           //�Q�j�ȉ����`�F�b�N����B
           //�E	�Ǘ��Ҍ���
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, true);
           //�E�Ǘ��ҕ��X����
           l_admin.validateBranchPermission(l_request.branchCode);
           String l_strInstCode = l_admin.getInstitutionCode();
           //�E�ڋq�̑��݃`�F�b�N
           //�E�M�p�ڋq�`�F�b�N
           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCode, l_request.accountCode);
           if(!(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)))
           {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00747, METHOD_NAME);
           }

           //�Ώیڋq�����ɕۏ؋������U�֒�~�ݒ肪�s���Ă��邩�ǂ����`�F�b�N�B
           //this.validate���o�^()���ĂԁB
           this.validateAlreadyRegistered(l_account.getAccountId());

           //�E��~���Ԃ̃`�F�b�N
            //this.validate��~����()���Ă�
            this.validateStopTerm(
                    l_request.autotransferStopStart,
                    l_request.autotransferStopEnd,
                    l_strInstCode,
                    l_request.branchCode);

           //�E�Ǘ��҃p�X���[�h
           l_admin.validateTradingPassword(l_request.adminPassword);

           /////////////////////////////////////////////////////////////////////////

           //�R�jthis.insert�ۏ؋������U�֒�~Params()���ĂԁB
           Timestamp l_timeStamp =
               this.insertDepositAutoTransferStopParams(
                   l_account,
                   l_admin,
                   l_request.autotransferStopStart,
                   l_request.autotransferStopEnd,
                   WEB3AdminTPDepositAutoTransferStopRegistDivDef.REGIST);

           //�]�͐���@@�\�ύX�������X�|���X���쐬����B
           l_response.lastUpdatedTime = l_timeStamp;

       }
       catch(NumberFormatException nfe)
       {
           l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_01035;
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
    * (get�ۏ؋������U�֒�~�ڋq��������)
    * �ۏ؋������U�֒�~�ڋq�����������s���B
    *
    * �P�j���N�G�X�g���͍��ڃ`�F�b�N�B

    * �Q�j�ȉ����`�F�b�N����B
    * �E�Ǘ��Ҍ���
    * �E�Ǘ��ҕ��X����
    * �E�ڋq���݁A�M�p�ڋq�`�F�b�N
    * ���M�p�ڋq�łȂ��ꍇ�A�ꗗ�Ώۂ���O���B
    *
    * �R�jthis.get�ۏ؋������U�֒�~Params�ꗗ()���Ă�.
    *
    * �S�jget�ۏ؋������U�֒�~Params()�̖߂�l�̗v�f����LOOP����~�ڋq�ꗗ���쐬����
    *
    * �S�|�P�jget�ڋq()�Ōڋq���擾�B
    * �����F
    * �ۏ؋������U�֒�~Params.get�ڋqID()
    *
    * �S�|�Q�j�ڋq�ʕۏ؋������U�֒�~�o�^���𐶐����ȉ��̂悤�ɑ������Z�b�g���A�ԋp
    * ����B
    *
    * �E���X�R�[�h = �ڋq.get���X�R�[�h()
    * �E�ڋq�R�[�h = �ڋq.get�ڋq�R�[�h()
    * �E�ڋq�� = �ڋq.get�ڋq��()
    * �E��~�J�n�� = �ۏ؋������U�֒�~Params.get��~�J�n��()
    * �E��~�I���� = �ۏ؋������U�֒�~Params.get��~�I����()
    * �E�o�^�敪 = �ۏ؋������U�֒�~Params.get�o�^�敪()
    *
    * �S�j�ۏ؋������U�֒�~�������X�|���X���쐬���v���p�e�B�ɒl���Z�b�g����B
    *
    * �E��~�ڋq�ꗗ
    * =�@@LOOP�����ō쐬�����ڋq�ʕۏ؋�������~�o�^���I�u�W�F�N�g�̔z��
    *
    * �T�j���X�|���X��ԋp����B
    * @@param l_request - �ۏ؋������U�֒�~�o�^�ڋq�������N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPFindDepositAutoTransferStopRespo
    * nse
    * @@roseuid 41BE58770200
    */
   protected WEB3AdminTPFindDepositAutoTransferStopResponse findDepositAutoTransferStops(WEB3AdminTPFindDepositAutoTransferStopRequest l_request)
   {
       final String METHOD_NAME = "findTradingPowerCalcControls(WEB3AdminTPFindCalcControlRequest)";

       WEB3AdminTPFindDepositAutoTransferStopResponse l_response = new WEB3AdminTPFindDepositAutoTransferStopResponse();

       try
       {
           //�P�j���N�G�X�g���͍��ڃ`�F�b�N�B
           l_request.validate();

           //�Q�j�ȉ����`�F�b�N����B
           //�E	�Ǘ��Ҍ���
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);
           //�E�Ǘ��ҕ��X����
           for(int i = 0; i < l_request.branchCodes.length; i++)
           {
               l_admin.validateBranchPermission(l_request.branchCodes[i]);
           }

           // �R�jthis.get�ۏ؋������U�֒�~Params�ꗗ()���Ă�.
           List l_rows = this.getDepositAutoTransferStopParamsList(l_request, l_admin);

           //�S�jget�ۏ؋������U�֒�~Params()�̖߂�l�̗v�f����LOOP����~�ڋq�ꗗ���쐬����
           //�S�|�P�jget�ڋq()�Ōڋq���擾�B


           // �S�|�Q�j�ڋq�ʕۏ؋������U�֒�~�o�^���𐶐���������l���Z�b�g����B
           if((l_rows == null) || (l_rows.size() < 1))
           {
               l_response.autoTransferStopInfos = new WEB3AdminTPDepositAutoTransferStopInfo[0];
           }
           else
           {
               WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
               
               List l_stopList = new ArrayList();

               int l_intSize = l_rows.size();
	             for(int i = 0; i < l_intSize; i++)
	             {
	                 DepositAutotransferStopRow l_row = (DepositAutotransferStopRow)l_rows.get(i);
	                 log.debug("selected row[" + i + "]" + l_row.toString());

	                 WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());
	                 
	                 //�M�p�ڋq�ł������ꍇ
	                 //�ۏ؋������U�֌ڋq�ꗗ�Ɋ܂߂�B
	                 if(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
	                 {
		                 WEB3AdminTPDepositAutoTransferStopInfo l_depositAutoTransferStopInfo = new WEB3AdminTPDepositAutoTransferStopInfo();
		                 l_depositAutoTransferStopInfo.branchCode = l_account.getBranch().getBranchCode();
		                 l_depositAutoTransferStopInfo.accountCode = l_account.getDisplayAccountCode();
		                 l_depositAutoTransferStopInfo.accountName = l_account.getDisplayAccountName();
		                 l_depositAutoTransferStopInfo.autoTransferStopId = String.valueOf(l_row.getDepositAutotransferStopId());
	                     l_depositAutoTransferStopInfo.transferStopStart = l_row.getAutotransferStopStart();
	                     l_depositAutoTransferStopInfo.transferStopEnd = l_row.getAutotransferStopEnd();
		                 l_stopList.add(l_depositAutoTransferStopInfo);

	                 }
	                 //���ɐM�p�ڋq�łȂ��ꍇ
	                 //�ꗗ�Ɋ܂߂Ȃ��B
	                 else
	                 {
	                 	log.debug("�ۏ؋������U�֒�~�o�^���ꂽ�ڋq���M�p�ڋq�łȂ� account_id=" + l_account.getAccountId());
	                 }

	             }
	             
	             l_response.autoTransferStopInfos = (WEB3AdminTPDepositAutoTransferStopInfo[])l_stopList.toArray(new WEB3AdminTPDepositAutoTransferStopInfo[l_stopList.size()]);
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
    * (validate�ۏ؋������U�֒�~����)
    * �ۏ؋������U�֒�~�o�^�����m�F�������s���B
    *
    * �P�j���N�G�X�g���͍��ڃ`�F�b�N�B
    *
    * �Q�j�ȉ����`�F�b�N����B
    * �E�Ǘ��Ҍ���
    * �E�Ǘ��ҕ��X����
    * �E�ڋq�̑��݁A�M�p�ڋq�`�F�b�N
    * ���N�G�X�g.�ۏ؋������U�֒�~ID�̗v�f����LOOP����
    * �G���[�̏ꍇ�A�����𒆒f���G���[���X�|���X��ԋp����B
    *
    * �S�j�ۏ؋������U�֒�~�����m�F���X�|���X���쐬����B
    *
    * �T�j���X�|���X��ԋp����B
    * @@param l_request - �ۏ؋������U�֒�~�����m�F���N�G�X�g
    * @@return �ۏ؋������U�֒�~�����m�F���X�|���X
    * @@roseuid 41BE5206001D
    */
   protected WEB3AdminTPReleaseDepositAutoTransferConfirmResponse validateReleaseDepositAutoTransfer(WEB3AdminTPReleaseDepositAutoTransferConfirmRequest l_request)
   {
       final String METHOD_NAME = "validateReleaseDepositAutoTransfer(WEB3AdminTPReleaseDepositAutoTransferConfirmRequest l_request)";

       WEB3AdminTPReleaseDepositAutoTransferConfirmResponse l_response = new WEB3AdminTPReleaseDepositAutoTransferConfirmResponse();

       WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

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

           int i_intSize = l_request.autoTransferStopIds.length;
           for(int i = 0; i < i_intSize; i++)
           {
               DepositAutotransferStopRow l_row = this.getDepositAutoTransferStopParams(Long.parseLong(l_request.autoTransferStopIds[i]));

               //�E�ڋq�̑��݃`�F�b�N
               //�E�Ǘ��ҕ��X����
               //�E�M�p�ڋq�`�F�b�N
               WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());

               l_admin.validateBranchPermission(l_account.getBranch().getBranchCode());

               if(!(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)))
               {
                   throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00747, METHOD_NAME);
               }


           }
       }
       catch(NumberFormatException nfe)
       {
           l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_01903;
           return l_response;
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
    * (submit�ۏ؋������U�֒�~����)
    * �ۏ؋������U�֒�~�o�^���������������s���B
    *
    * �P�j���N�G�X�g���͍��ڃ`�F�b�N�B
    *
    * �Q�j�ȉ����`�F�b�N����B
    * �E�Ǘ��Ҍ���
    * �E�Ǘ��ҕ��X����
    * �E�ڋq�̑��݁A�M�p�ڋq�`�F�b�N
    * ���N�G�X�g.�ۏ؋������U�֒�~ID�̗v�f����LOOP����
    * �G���[�̏ꍇ�A�����𒆒f���G���[���X�|���X��ԋp����B
    * �E�Ïؔԍ��̃`�F�b�N
    *
    * �R�jthis.update�ۏ؋������U�֒�~Params����()���ĂԁB
    *  �����Ƃ��Ĉȉ���n���F
    * �E�Ǘ���
    * �E�ۏ؋������U�֒�~ID
    *
    *
    * �S�j�ۏ؋������U�֒�~�����������X�|���X���쐬����B
    * �v���p�e�B�Ɉȉ����Z�b�g����B
    *
    * �E�X�V���� = DB�X�V���ɃZ�b�g�����X�V����
    *
    * �T�j���X�|���X��ԋp����B
    * @@param l_request - �ۏ؋������U�֒�~�����������N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferComple
    * teResponse
    * @@roseuid 41C127C9005B
    */
   protected WEB3AdminTPReleaseDepositAutoTransferCompleteResponse submitReleaseDepositAutoTransfer(WEB3AdminTPReleaseDepositAutoTransferCompleteRequest l_request)
   {
       final String METHOD_NAME = "submitReleaseDepositAutoTransfer(WEB3AdminTPReleaseDepositAutoTransferCompleteRequest l_request)";

       WEB3AdminTPReleaseDepositAutoTransferCompleteResponse l_response = new WEB3AdminTPReleaseDepositAutoTransferCompleteResponse();

       WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

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

           int i_intSize = l_request.autoTransferStopIds.length;
           for(int i = 0; i < i_intSize; i++)
           {
               DepositAutotransferStopRow l_row = this.getDepositAutoTransferStopParams(Long.parseLong(l_request.autoTransferStopIds[i]));

               //�E�ڋq�̑��݃`�F�b�N
               //�E�Ǘ��ҕ��X����
               //�E�M�p�ڋq�`�F�b�N
               WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());

               l_admin.validateBranchPermission(l_account.getBranch().getBranchCode());

               if(!(l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)))
               {
                   throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00747, METHOD_NAME);
               }

           }

           //�E�Ǘ��҃p�X���[�h
           l_admin.validateTradingPassword(l_request.adminPassword);

           //�R�jthis.update�ۏ؋������U�֒�~Params����()���ĂԁB
	       Timestamp l_timestamp = null;

           for(int i = 0; i < i_intSize; i++)
           {
               long l_lngTransferStopId = Long.parseLong(l_request.autoTransferStopIds[i]);

               l_timestamp = this.updateDepositAutoTransferStopParams(l_admin, l_lngTransferStopId, null, null, WEB3AdminTPDepositAutoTransferStopRegistDivDef.RELEASE);
           }

           l_response.lastUpdatedTime = l_timestamp;

       }
       catch(NumberFormatException nfe)
       {
           l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_01903;
           return l_response;
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
    * (get�ۏ؋������U�֒�~Params�ꗗ)
    *
    * �ۏ؋������U�֒�~�e�[�u�����
    * ���݁A�܂��͖����̕ۏ؋������U�֒�~�̌ڋq
    * �������B
    *
    * �����F
    * �E���� =< ��~���ԁi���j
    * �E�o�^�敪 = �h�o�^�h
    * [���N�G�X�g.�ڋq�w��̏ꍇ]
    * �ڋq�R�[�h�w��̏ꍇ�A���X�R�[�h�Ƃ̑g�ݍ��킹��
    * �E�ڋq�̑��݃`�F�b�N
    * �E�M�p�ڋq�`�F�b�N
    * �Ƀp�X�����ڋqID
    *
    * [��L�ȊO(���X�̂ݎw��̏ꍇ)]
    * �E���XID = get���X().get���XID()�̖߂�l
    * ���� :
    * �،���ЃR�[�h : �Ǘ���.get�،���ЃR�[�h()
    * ���X�R�[�h : ���N�G�X�g.���X�R�[�h
    *
    * @@return List
    * @@roseuid 41C1284101B3
    */
   protected List getDepositAutoTransferStopParamsList(WEB3AdminTPFindDepositAutoTransferStopRequest l_request, WEB3Administrator l_admin)
   throws WEB3BaseException
   {
       final String METHOD_NAME = "getDepositAutoTransferStopParamsList(WEB3AdminTPFindDepositAutoTransferStopRequest l_request, WEB3Administrator l_admin)";

       StringBuffer l_sbWhere = new StringBuffer();
       List l_lisBindVars = new ArrayList();

       //�E�o�^�敪 = �h�o�^�h
       l_sbWhere.append("regist_div = ?");
       l_lisBindVars.add(WEB3AdminTPDepositAutoTransferStopRegistDivDef.REGIST);

       //�E���� =< ��~���ԁi���j
       //�Ɩ����t���擾����B
       Date l_bizDate = GtlUtils.getTradingSystem().getBizDate();
       l_sbWhere.append(" and autotransfer_stop_end >= ?");
       l_lisBindVars.add(l_bizDate);

       WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

       //�ڋq�R�[�h != null�̂Ƃ�
       if(l_request.accountCode != null)
       {
           String l_strInstCode = l_admin.getInstitutionCode();

           //�ڋq�����݁@@����
           //�M�p�ڋq�ł���ꍇ
           //���������ɉ�����B
           //��L�ɊY�����Ȃ��ꍇ��Skip����B�i�����ɉ����Ȃ��j
           int l_intHitCount = 0;
           int l_intBranchLength = l_request.branchCodes.length;
           for(int i = 0; i < l_intBranchLength; i++)
           {
               try
               {
                   WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCodes[i], l_request.accountCode);

                   //�������ꂽ�ڋq���M�p�ڋq�̏ꍇ
                   if((l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)))
                   {
                       //�ڋq�R�[�h�ƕ��X�R�[�h��1��1�Ŏw�肳��Ă����ꍇ
                       if(l_intHitCount == 0)
                       {
                           l_sbWhere.append(" and account_id in (?");
                       }
                       else
                       {
                           l_sbWhere.append(",?");
                       }
                       l_intHitCount++;
                       l_lisBindVars.add(new Long(l_account.getAccountId()));
                   }
                   //�M�p�ڋq�łȂ������ꍇ
                   else
                   {
                       log.error("this account was not margin customer. branchCode = " + l_request.branchCodes[i] + " accountCode = " + l_request.accountCode);
                   }

               }
               //�ڋq�̑��݃`�F�b�N,Skip�����ڋq�����O�B
               catch(WEB3BaseException e)
               {
	               log.error(e.getErrorInfo().getErrorMessage() + " branchCode = " + l_request.branchCodes[i] + " accountCode = " + l_request.accountCode);
               }
           }
           if(l_intHitCount > 0)
           {
               l_sbWhere.append(")");
           }
           //�ڋq��1����Hit���Ȃ������ꍇ�ADB����������̃��X�g��Ԃ��B
           else
           {
               return Collections.EMPTY_LIST;
           }
       }


       else
       {
           //[���X�R�[�h  != null�̏ꍇ]
           //�E���XID = get���X().get���XID()�̖߂�l
           //���� :
           //�،���ЃR�[�h : �Ǘ���.get�،���ЃR�[�h()
           //���X�R�[�h : ���N�G�X�g.���X�R�[�h
           if(l_request.branchCodes != null)
           {

               l_sbWhere.append(" and branch_id in (");
               for(int i = 0; i < l_request.branchCodes.length; i++)
               {
                   long l_lngBranchId;
                   try
                   {
                       l_lngBranchId = l_accMgr.getBranch(l_admin.getInstitution(), l_request.branchCodes[i]).getBranchId();
                       if(i > 0)
                       {
                           l_sbWhere.append(",");
                       }
                       l_sbWhere.append("?");
                       l_lisBindVars.add(new Long(l_lngBranchId));
                   }
                   catch(NotFoundException nfe)
                   {
                       //�Y�����X�Ȃ�
                       throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
                   }

               }
               l_sbWhere.append(") ");
           }

       }

       final String l_strWhere = l_sbWhere.toString();
       final Object[] l_bindVars = l_lisBindVars.toArray();
       log.debug("l_strWhere=" + l_strWhere);
       log.debug("l_bindVars=" + l_bindVars);

       try
       {
           QueryProcessor l_qp = Processors.getDefaultProcessor();
           return l_qp.doFindAllQuery(DepositAutotransferStopRow.TYPE, l_strWhere, l_bindVars);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }

   }

   /**
    * (get�ۏ؋������U�֒�~Params)
    *
    * �ۏ؋������U�֒�~�e�[�u�����APK(����.�ۏ؋������U�֒�~ID)����
    * �������ʂ�Ԃ��B
    * @@param l_lngDepoistAutoTransferId - �ۏ؋������U�֒�~ID
    * @@return DepositAutoTransferStopRow
    * @@roseuid 41D257C200AC
    */
   public DepositAutotransferStopRow getDepositAutoTransferStopParams(long l_lngDepoistAutoTransferId)
   throws WEB3SystemLayerException
   {
       final String METHOD_NAME = "getDepositAutoTransferStopParams(long l_lngDepoistAutoTransferId)";
       try
       {
           return DepositAutotransferStopDao.findRowByPk(l_lngDepoistAutoTransferId);
       }
       catch(DataFindException dfe)
       {
           //TODO ErrorInfo��`�m�F�@@SYSTEM_ERROR_80005�i�Y���f�[�^�Ȃ��j��OK?
           log.error(METHOD_NAME + " updating data not found l_lngDepoistAutoTransferId=" + l_lngDepoistAutoTransferId);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }

   }

   /**
    * (insert�ۏ؋������U�֒�~Params)
    *
    * �ۏ؋������U�֒�~Params���쐬�A�v���p�e�B���ȉ��̂悤�ɃZ�b�g���A
    * �ۏ؋������U�֒�~�e�[�u���Ɉ����œn���ꂽ�o�^���e�̃��R�[�h��ǉ�����B
    *
    * �E�ۏ؋������U�֒�~ID = xTrade�Ŏ�������
    * �E�ڋqID = �ڋq.get�ڋqID()
    * �E��~����(��) = ����.��~�J�n��()
    * �E��~����(��) = ����.��~�I����()
    * �E�o�^�敪 = ����.�o�^�敪
    * �E�X�V�҃R�[�h = �Ǘ���.get�Ǘ��҃R�[�h()
    * �E�X�V���� = ThreadLocalSystemRegistry.getAttribute(�������.TIMESTAMP_TAG)
    * @@param l_account - �ڋq
    * @@param l_admin - �Ǘ���
    * @@param l_autoTransferStopStart - ��~�J�n��
    * @@param l_autoTransferStopEnd - ��~�I����
    * @@param l_strRegistDiv - �o�^�敪
    * @@return Timestamp
    * @@roseuid 41BE51FD0378
    */
   protected Timestamp insertDepositAutoTransferStopParams(MainAccount l_account, WEB3Administrator l_admin, Date l_autoTransferStopStart, Date l_autoTransferStopEnd, String l_strRegistDiv)
   throws WEB3SystemLayerException
   {
       final String METHOD_NAME = "insertDepositAutoTransferStopParams(MainAccount l_account, WEB3Administrator l_admin, Date l_autoTransferStopStart, Date l_autoTransferStopEnd, String l_strRegistDiv)";

       Timestamp l_timestamp = null;

       try
       {
	       l_timestamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
	               WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

	       if(l_timestamp == null)
	       {
	           log.debug("l_timestamp is null.");
	           return null;
	       }

	       long l_lngPK = DepositAutotransferStopDao.newPkValue();
           DepositAutotransferStopParams l_depositAutotransferStopParams =
               new DepositAutotransferStopParams();

           l_depositAutotransferStopParams.setDepositAutotransferStopId(l_lngPK);
           l_depositAutotransferStopParams.setAccountId(l_account.getAccountId());
           l_depositAutotransferStopParams.setBranchId(l_account.getBranch().getBranchId());
           l_depositAutotransferStopParams.setRegistDiv(l_strRegistDiv);
           l_depositAutotransferStopParams.setAutotransferStopStart(l_autoTransferStopStart);
           l_depositAutotransferStopParams.setAutotransferStopEnd(l_autoTransferStopEnd);
           l_depositAutotransferStopParams.setLastUpdater(l_admin.getAdministratorCode());
           l_depositAutotransferStopParams.setLastUpdatedTimestamp(l_timestamp);
           l_depositAutotransferStopParams.setCreatedTimestamp(l_timestamp);

           QueryProcessor l_qp = Processors.getDefaultProcessor();
           l_qp.doInsertQuery(l_depositAutotransferStopParams);
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

       return l_timestamp;



   }

   /**
    * (update�ۏ؋������U�֒�~Params)
    * �ۏ؋������U�֒�~�e�[�u�����ȉ��̏����A�l�ōX�V����B
    *
    * �����F
    * �ۏ؋������U�֒�~ID = ����.�ۏ؋������U�֒�~ID
    *
    * �l�F�i�e�����̒l��null�łȂ��ꍇ�A�ȉ��̂悤�ɃZ�b�g����B�j
    * �E��~����(��) = ����.��~�J�n��
    * �E��~����(��) = ����.��~�I����
    * �E�o�^�敪 = ����.�o�^�敪
    * �E�X�V�҃R�[�h = �Ǘ���.get�Ǘ��҃R�[�h()
    * �E�X�V���� = ThreadLocalSystemRegistry.getAttribute(�������.TIMESTAMP_TAG)
    * @@param l_admin - �Ǘ���
    * @@param l_lngDepositAutoTransferStopId - �ۏ؋������U�֒�~ID
    * @@param l_datDepositAutoTransferStopStart - ��~�J�n��
    * @@param l_datDepositAutoTransferStopEnd - ��~�I����
    * @@param l_strRegistDiv - �o�^�敪
    * @@return Timestamp
    * @@roseuid 41BE521700D8
    */
   protected Timestamp updateDepositAutoTransferStopParams(WEB3Administrator l_admin, long l_lngDepositAutoTransferStopId, Date l_datDepositAutoTransferStopStart, Date l_datDepositAutoTransferStopEnd, String l_strRegistDiv)
   throws WEB3BaseException
   {
       final String METHOD_NAME = "updateDepositAutoTransferStopParams(WEB3Administrator l_admin, long l_lngDepositAutoTransferStopId, Date l_datDepositAutoTransferStopStart, Date l_datDepositAutoTransferStopEnd, String l_strRegistDiv)";
       int l_intUdateCount = 0;
       Timestamp l_timestamp = null;

       try
       {
           l_timestamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                   WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

           if(l_timestamp == null)
	       {
	           log.debug("l_timestamp is null.");
	           return null;
	       }

	       Map l_updateMap = new HashMap();
	       //��~�J�n���A��~�I�����ɂ��Ă�
	       //�w�肪�������ꍇ�ɒl���Z�b�g
	       if(l_datDepositAutoTransferStopStart != null)
	       {
		       l_updateMap.put("autotransfer_stop_start", l_datDepositAutoTransferStopStart);
	       }
	       if(l_datDepositAutoTransferStopEnd != null)
	       {
		       l_updateMap.put("autotransfer_stop_end", l_datDepositAutoTransferStopEnd);
	       }
	       l_updateMap.put("regist_div", l_strRegistDiv);
	       l_updateMap.put("last_updater", l_admin.getAdministratorCode());
	       l_updateMap.put("last_updated_timestamp", l_timestamp);

	       QueryProcessor l_qp = Processors.getDefaultProcessor();
	       l_intUdateCount = l_qp.doUpdateQuery(new DepositAutotransferStopPK(l_lngDepositAutoTransferStopId), l_updateMap);

       }
       //�ۏ؋������U�֒�~ID���s���Ȓl�̂Ƃ�
       catch(NumberFormatException nfe)
       {
           throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01903, METHOD_NAME);
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
           log.error(METHOD_NAME + " updating data not found l_lngDepositAutoTransferStopId=" + l_lngDepositAutoTransferStopId);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
       }
       return l_timestamp;
    }

   /**
     * (validate��~����) <BR>
     * ��~���Ԃ̃`�F�b�N�����{����B<BR> 
     * ��~���Ԃ��s���������ꍇ�A�Ɩ��G���[���X���[����B <BR>
     * <BR>
     * �P�j�����̕ۏ؋������U�֏����̎��{�󋵂��m�F����B <BR>
     * <BR>
     * �@@�P-�P�j�v���Z�X�Ǘ��e�[�u������������B <BR>
     * <BR>
     * �@@�@@�@@[��������] <BR>
     * �@@�@@�@@�@@�v���Z�XID�F0005�F�ۏ؋������U�֏I�� <BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F����.�،���ЃR�[�h <BR>
     * �@@�@@�@@�@@���X�R�[�h�F����.���X�R�[�h <BR>
     * <BR>
     * �Q�j��~���Ԃ��`�F�b�N����B <BR>
     * <BR>
     * �@@�Q-�P�j��~����(��)���A��~����(��)���ߋ����t�łȂ��B <BR>
     * �@@�@@[a. ����.��~����(��) > ����.��~����(��) �@@�̏ꍇ] <BR>
     * �@@�@@�@@�@@�Ɩ��G���[���X���[����B <BR>
     * <BR>
     * �@@�Q-�Q�j��~���ԁi���j���ߓ��łȂ��B <BR>
     * �@@�@@[a.�P-�P�j�̌������� == null || �P-�P�j�̌������ʂ̗v�f�� == 0�@@�̏ꍇ]<BR> 
     * <BR>
     * �@@�@@�@@[b. ����(*) > ����.��~����(��) �̏ꍇ] <BR>
     * �@@�@@�@@�@@�@@�Ɩ��G���[���X���[����B<BR>
     * <BR>
     * �@@�@@[a.�ȊO�̏ꍇ]<BR>
     * <BR>
     * �@@�@@�@@[b. ����(*) >= ����.��~����(��) �̏ꍇ] <BR>
     * �@@�@@�@@�@@�@@�Ɩ��G���[���X���[����B<BR>
     * <BR>
     * �@@�@@(*)���� = GtlUtils.getTradingSystem().getBizDate() <BR>
     * <BR>
     * @@param l_datStopStart - ��~����(��)
     * @@param l_datStopEnd - ��~����(��)
     * @@param l_strInstCode - �،���ЃR�[�h
     * @@param l_strBranCode - ���X�R�[�h
     */
    protected void validateStopTerm(
            Date l_datStopStart,
            Date l_datStopEnd,
            String l_strInstCode,
            String l_strBranCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateStopTerm(Date l_datStopStart, Date l_datStopEnd, String l_strInstCode, String l_strBranCode) ";

        /*
         * �v���Z�X�Ǘ��e�[�u������������B
         * 
         * [��������]
         * �@@�v���Z�XID�F0005�F�ۏ؋������U�֏I��
         * �@@�،���ЃR�[�h�F����.�،���ЃR�[�h 
         * �@@���X�R�[�h�F����.���X�R�[�h 
         */
        //�v���Z�X�Ǘ�Row
        ProcessManagementRow l_processManagementRow = null;
        try
        {
            //�v���Z�X�Ǘ��e�[�u��������
            l_processManagementRow = ProcessManagementDao.findRowByProcessIdInstitutionCodeBranchCode(
                    WEB3AdminTPProcessManagementIdDivDef.DEPOSIT_AUTOTRANSFER_END,
                    l_strInstCode,
                    l_strBranCode);
        }
        catch(DataFindException l_ex)
        {
            log.error("�f�[�^�s�����G���[�B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch(DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        /*
         * ��~���Ԃ��`�F�b�N����
         */
        //�Ɩ����t���擾����B
        Date l_bizDate = GtlUtils.getTradingSystem().getBizDate();

        log.debug("l_datStopStart="
                + WEB3DateUtility.formatDate(l_datStopStart, "yyyy/MM/dd")
                + " l_datStopEnd="
                + WEB3DateUtility.formatDate(l_datStopEnd, "yyyy/MM/dd")
                + " l_bizDate="
                + WEB3DateUtility.formatDate(l_bizDate, "yyyy/MM/dd"));

        /*
         * ��~����(��)���A��~����(��)���ߋ����t�łȂ��B
         */
        if(WEB3DateUtility.compareToDay(l_datStopStart, l_datStopEnd) > 0)
        {
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01902,
                    STR_METHOD_NAME);
        }

        /*
         * ��~���ԁi���j���ߓ��łȂ��B
         */
        //�v���Z�X�Ǘ�Row == null �̏ꍇ
        if(l_processManagementRow == null)
        {
            //����(*) > ����.��~����(��) �̏ꍇ
            if(WEB3DateUtility.compareToDay(l_bizDate, l_datStopStart) > 0)
            {
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01902,
                        STR_METHOD_NAME);
            }
        }
        //�ȊO�i�v���Z�X�Ǘ�Row != null�j�̏ꍇ
        else
        {
            //����(*) >= ����.��~����(��) �̏ꍇ
            if(WEB3DateUtility.compareToDay(l_bizDate, l_datStopStart) >= 0)
            {
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01902,
                        STR_METHOD_NAME);
            }
        }
    }

   /**
    * (validate���o�^)
    * �Ώیڋq�����ɕۏ؋������U�֒�~�ݒ肪�s���Ă��邩�ǂ����`�F�b�N
    *
    * �ۏ؋������U�֒�~�e�[�u�����ȉ��̏����Ō��������R�[�h�����݂���΃G���[�B
    * �E�ڋqID = �ڋq.get�ڋqID()
    * �E�o�^�敪 = �h�o�^�i��~�j�h
    * �E���� < ��~���ԁi���j
    * @@param l_lngAccountId - �ڋqID
    * @@roseuid 41D24C02039A
    */
   protected void validateAlreadyRegistered(long l_lngAccountId) throws WEB3BaseException
   {
       final String METHOD_NAME = "validateAlreadyRegistered(long l_lngAccountId)";
       final String l_strWhere = "account_id = ? and regist_div = ? and autotransfer_stop_end > ?";

       //�Ɩ����t���擾����B
       Date l_bizDate0 = GtlUtils.getTradingSystem().getBizDate();

       final Object[] l_bindVars =
       {
               new Long(l_lngAccountId),
               WEB3AdminTPDepositAutoTransferStopRegistDivDef.REGIST,
               l_bizDate0
       };

       try
       {
           QueryProcessor l_qp = Processors.getDefaultProcessor();
           List l_rows = l_qp.doFindAllQuery(DepositAutotransferStopRow.TYPE, l_strWhere, l_bindVars);
           if(l_rows.size() > 0)
           {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01904, METHOD_NAME);
           }
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
   }

}
@
