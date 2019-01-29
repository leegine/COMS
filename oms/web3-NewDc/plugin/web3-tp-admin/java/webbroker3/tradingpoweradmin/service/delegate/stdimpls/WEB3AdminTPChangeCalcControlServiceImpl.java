head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeCalcControlServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͐���@@�\�ύX�T�[�r�XImpl�N���X(WEB3AdminTPChangeCalcControlServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/06 �x�� �a��(FLJ) �V�K�쐬
Revision History : 2007/07/26 ��іQ(���u) ���f��No.004,005,006
Revision History : 2007/09/03 �И���(���u) ���f��No.013,014
Revision History : 2007/09/18 �И���(���u) ���f��No.015,017
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionPK;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPChangeCalcControlService;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPIfoAccountOpenDivDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTransactionCategoryCodeDef;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPCalcControlInfo;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindCalcControlRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindCalcControlResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlCompleteRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AdditionalDepositStopDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlInputRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * �]�͐���@@�\�ύX�T�[�r�XImpl�N���X
 *
 * �]�͐���@@�\�i�M�p�V�K����~�A�敨OP�V�K����~�A�o����~�A���̑����i���t��~�Ɋ�
 * ���j�ύX�̃C���^�[�t�F�[�X�̎����N���X�B
 * �ėp�N���C�A���g���N�G�X�g�T�[�r�X�N���X���g���B�i���ݖ��g�p�j
 */
public class WEB3AdminTPChangeCalcControlServiceImpl extends WEB3ClientRequestService implements WEB3AdminTPChangeCalcControlService
{
    /**
     * ���O�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPChangeCalcControlServiceImpl.class);

   /**
    * @@roseuid 41DBCA970224
    */
   public WEB3AdminTPChangeCalcControlServiceImpl()
   {

   }

   /**
    * |�]�͐���@@�\�ύX�������s���B
    * |
    * |���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B
    * |
    * |���]�͐���@@�\�������N�G�X�g�̏ꍇ
    * |�@@this.get�]�͐���@@�\��������()���\�b�h���R�[������B
    * |
    * |���]�͐���@@�\�ύX�m�F���N�G�X�g�̏ꍇ
    * |�@@this.validate�]�͐���@@�\�ύX()���\�b�h���R�[������B
    * |
    * |���]�͐���@@�\�ύX�������N�G�X�g�̏ꍇ
    * |�@@this.submit�]�͐���@@�\�ύX()���\�b�h���R�[������B
    * @@param l_request - ���N�G�X�g
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DB98740385
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request)
    throws WEB3BaseException
   {
       final String METHOD_NAME = "execute(WEB3GenRequest)";

       log.entering(METHOD_NAME);

       WEB3GenResponse l_response = null;

       if(l_request instanceof WEB3AdminTPFindCalcControlRequest)
       {
           l_response =  this.findTradingPowerCalcControls((WEB3AdminTPFindCalcControlRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPChangeCalcControlInputRequest)
       {
           l_response =  this.getChangeTradingPowerCalcControlInputInfo((WEB3AdminTPChangeCalcControlInputRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPChangeCalcControlConfirmRequest)
       {
           l_response = this.validateChangeTradingPowerCalcControl((WEB3AdminTPChangeCalcControlConfirmRequest)l_request);
       }
       else if(l_request instanceof WEB3AdminTPChangeCalcControlCompleteRequest)
       {
           l_response =  this.submitChangeTradingPowerCalcControl((WEB3AdminTPChangeCalcControlCompleteRequest)l_request);
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
    * (get�]�͐���@@�\��������)
    * �]�͐���@@�\�����������s���B
    *
    * �P�j���N�G�X�g���͍��ڃ`�F�b�N�B
    *
    * �Q�j�ȉ����`�F�b�N����B
    * �E�Ǘ��Ҍ���
    * �E�Ǘ��ҕ��X����
    * �E�ڋq�̑��݃`�F�b�N�i���N�G�X�g.�ڋq�R�[�h != null�̏ꍇ�j
    *
    * �R�jthis.get�ڋq�]�͏���Params�ꗗ()���Ă�.
    * �����Ɉȉ���n���B
    *
    * �E�������� = ���N�G�X�g.get��������()
    * �E�ڋqID =
    * �i���N�G�X�g.�������� = �h�ڋq�h�̏ꍇ�Ɏ擾�����ڋq�j
    *
    * �S�j�]�͋@@�\���䌟�����ʃ��X�|���X���쐬��
    * �v���p�e�B�ɒl���Z�b�g����B
    *
    *
    *
    * �T�j���X�|���X��ԋp����B
    * @@param l_request - �]�͐���@@�\�������N�G�X�g
    * @@return webbroker3.tradingpoweradmin.message.WEB3AdminTPFindCalcControlResponse
    * @@roseuid 41B9672D0048
    */
   protected WEB3AdminTPFindCalcControlResponse findTradingPowerCalcControls(WEB3AdminTPFindCalcControlRequest l_request)
   {
       final String METHOD_NAME = "findTradingPowerCalcControls(WEB3AdminTPFindCalcControlRequest)";

       WEB3AdminTPFindCalcControlResponse l_response = new WEB3AdminTPFindCalcControlResponse();

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

           // �R�jthis.get�ڋq�]�͏���Params�ꗗ()���Ă�.
           List l_rows = this.getTradingPowerCalcConditionParamsList(l_request, l_admin);
                      

           //�S�j�]�͋@@�\���䌟�����ʃ��X�|���X���쐬��
           //�v���p�e�B�ɒl���Z�b�g����B
           if((l_rows == null) || (l_rows.size() < 1))
           {
               l_response.calcControlInfos = new WEB3AdminTPCalcControlInfo[0];
           }
           else
           {
               WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
               
               List l_lisControlInfos = new ArrayList();               
               
               int l_intSize = l_rows.size();
               for(int i = 0; i < l_intSize; i++)
               {
                   TradingpowerCalcConditionRow l_row = (TradingpowerCalcConditionRow)l_rows.get(i);
                   WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());
                   
                   //�\���Ώیڋq���ǂ����`�F�b�N
                   if(this.isShowableAccount(l_request, l_account, l_row))
                   {
                       WEB3AdminTPCalcControlInfo l_calcControlInfo = this.createCalcControlInfo(l_account, l_row);
                       l_lisControlInfos.add(l_calcControlInfo);
                       log.debug("�ǉ������ڋq�̗]�͐�����=" + l_calcControlInfo);
                   }	                 
               }
               l_response.calcControlInfos = (WEB3AdminTPCalcControlInfo[])l_lisControlInfos.toArray(new WEB3AdminTPCalcControlInfo[l_lisControlInfos.size()]);           
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
    * (get�]�͐���@@�\�ύX���͉��)
    * �P�j�ȉ����`�F�b�N����B
    * �E�Ǘ��Ҍ���
    * �E�Ǘ��ҕ��X�����i���S�j�����Ōڋq���擾����ۂɃ`�F�b�N�j
    *
    * �Q�jthis.get�ڋq�]�͏���ParamsByPK()���Ă�.
    * �����Ɉȉ���n���B
    *
    * �E�ڋq�]�͏���ID = ���N�G�X�g.�ڋq�]�͏���ID
    *
    * �R�j�]�͋@@�\���䌟�����ʃ��X�|���X���쐬��
    * �v���p�e�B�ɒl���Z�b�g����B
    *
    * �S�j���X�|���X��ԋp����B
    * @@param l_request - �]�͐���@@�\�ύX���͉�ʃ��N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlInputResponse
    * @@roseuid 41CBA13F02F2
    */
   protected WEB3AdminTPChangeCalcControlInputResponse getChangeTradingPowerCalcControlInputInfo(WEB3AdminTPChangeCalcControlInputRequest l_request)
   {
       final String METHOD_NAME = "getChangeTradingPowerCalcControlInputInfo(WEB3AdminTPChangeCalcControlInputRequest l_request)";

       WEB3AdminTPChangeCalcControlInputResponse l_response = new WEB3AdminTPChangeCalcControlInputResponse();

       try
       {
           //�P�j���N�G�X�g���͍��ڃ`�F�b�N�B
           l_request.validate();

           //�Q�j�ȉ����`�F�b�N����B
           //�E	�Ǘ��Ҍ���
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);

           // �R�jthis.get�ڋq�]�͏���Params�ꗗ()���Ă�.
           long l_lngCalcConditionId = Long.parseLong(l_request.calcConditionId);
           TradingpowerCalcConditionRow l_row  = this.getTradingPowerCalcConditionParams(l_lngCalcConditionId);

           WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

           //�S�j�]�͋@@�\���䌟�����ʃ��X�|���X���쐬��
           //�v���p�e�B�ɒl���Z�b�g����B

           if(l_row == null)
           {
               //�Y���f�[�^�Ȃ�
               l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
           }
           else
           {
               l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
				WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());

				//�E�Ǘ��ҕ��X�����̃`�F�b�N
				l_admin.validateBranchPermission(l_account.getBranch().getBranchCode());
				l_response.calcControlInfo = this.createCalcControlInfo(l_account, l_row);
				
           }

       }
       catch(NumberFormatException nfe)
       {
           l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_01893;
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
    * (validate�]�͐���@@�\�ύX)
    * �]�͐���@@�\�ύX�m�F�������s���B
    *
    * �P�j���N�G�X�g���͍��ڃ`�F�b�N�B
    *
    * �Q�j�ȉ����`�F�b�N����B
    * �E�Ǘ��Ҍ���
    * �E�Ǘ��ҕ��X����
    * �E�ڋq�̑���
    *
    * �R�j�]�͐���@@�\�ύX�m�F���X�|���X���쐬����B
    *
    * �S�j���X�|���X��ԋp����B
    * @@param l_request - �]�͐���@@�\�ύX�m�F���N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlConfirmResponse
    * @@roseuid 41B9673C025B
    */
   protected WEB3AdminTPChangeCalcControlConfirmResponse validateChangeTradingPowerCalcControl(WEB3AdminTPChangeCalcControlConfirmRequest l_request)
   {
       final String METHOD_NAME = "validateChangeTradingPowerCalcControl(WEB3AdminTPChangeCalcControlConfirmRequest l_request)";

       WEB3AdminTPChangeCalcControlConfirmResponse l_response = new WEB3AdminTPChangeCalcControlConfirmResponse();

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


           //�ڋq�]�͏����A
           //�E�Ǘ��ҕ��X����
           //�ڋq�̑��݃`�F�b�N
           long l_lngCalcConditionId = Long.parseLong(l_request.calcConditionId);
           TradingpowerCalcConditionRow l_row  = this.getTradingPowerCalcConditionParams(l_lngCalcConditionId);
           if(l_row == null)
           {
               l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
               log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, new IllegalArgumentException());
           }
           else
           {
               WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());
               l_admin.validateBranchPermission(l_account.getBranch().getBranchCode());
           }

       }
       catch(NumberFormatException nfe)
       {
           l_response.errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_01893;
           log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, nfe);
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
    * (submit�]�͐���@@�\�ύX)
    * �]�͐���@@�\�ύX�����������s���B
    *
    * �P�j���N�G�X�g���͍��ڃ`�F�b�N�B
    *
    * �Q�j�ȉ����`�F�b�N����B
    * �E�Ǘ��Ҍ���
    * �E�Ǘ��ҕ��X����
    * �E�ڋq�̑���
    * �E�Ǘ��҃p�X���[�h
    *
    * �R�jthis.update�ڋq�]�͏���Params()���ĂԁB
    *
    * �S�j�]�͐���@@�\�ύX�������X�|���X���쐬����B
    *
    * �T�j���X�|���X��ԋp����B
    * @@param l_request - �]�͐���@@�\�ύX�������N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlCompleteRespons
    * e
    * @@roseuid 41C0E210025E
    */
   protected WEB3AdminTPChangeCalcControlCompleteResponse submitChangeTradingPowerCalcControl(WEB3AdminTPChangeCalcControlCompleteRequest l_request)
   {
       final String METHOD_NAME = "submitChangeTradingPowerCalcControl(WEB3AdminTPChangeCalcControlCompleteRequest l_request)";

       WEB3AdminTPChangeCalcControlCompleteResponse l_response = new WEB3AdminTPChangeCalcControlCompleteResponse();

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

           //�ڋq�]�͏����A
           //�Ǘ��ҕ��X����
           //�ڋq�̑��݃`�F�b�N
           long l_lngCalcConditionId = Long.parseLong(l_request.calcConditionId);
           TradingpowerCalcConditionRow l_row  = this.getTradingPowerCalcConditionParams(l_lngCalcConditionId);
           if(l_row == null)
           {
               //�Y���f�[�^�Ȃ�
               l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
               log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, new IllegalArgumentException());
               return l_response;
           }

           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());
           l_admin.validateBranchPermission(l_account.getBranch().getBranchCode());

           //�E�Ǘ��҃p�X���[�h
           l_admin.validateTradingPassword(l_request.adminPassword);

           //this.update�ڋq�]�͏���Params()���ĂԁB
           Timestamp l_timeStamp = this.updateTradingPowerCalcConditionParams(l_request, l_admin);

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
    * (get�ڋq�]�͏���Params�ꗗ)<BR>
    * �ڋq�]�͏����e�[�u�����猟���������ʂ�ԋp����B<BR>
    * <BR>
    * �P�j���N�G�X�g.�ڋq�R�[�h != null�̏ꍇ<BR>
    * �����F<BR>
    * �ڋqID = �ڋq.get�ڋqID()<BR>
    * <BR>
    * �Q�j��L�ȊO�̏ꍇ<BR>
    * <BR>
    * �����i�ȉ��S��OR�łȂ��j�F<BR>
    * [�]�͐���@@�\����.�����~�敪 != null�̏ꍇ]<BR>
    * �E�����~�敪 = �]�͐���@@�\����.�����~�敪<BR>
    * <BR>
    * [�]�͐���@@�\����.�M�p�V�K���]�͋敪 != null�̏ꍇ]<BR>
    * �E�M�p�V�K���]�͋敪 = �]�͐���@@�\����.�M�p�V�K���]�͋敪<BR>
    * <BR>
    * [�]�͐���@@�\����.�敨OP�V�K���]�͋敪 != null�̏ꍇ]<BR>
    * �E�敨OP�V�K���]�͋敪 = �]�͐���@@�\����.�敨OP�V�K���]�͋敪<BR>
    * <BR>
    * [�]�͐���@@�\����.�o���]�͋敪 != null�̏ꍇ]<BR>
    * �E�o���]�͋敪 = �]�͐���@@�\����.�o���]�͋敪<BR>
    * <BR>
    * [�]�͐���@@�\����.���̑����i���t�敪 != null�̏ꍇ]<BR>
    * �E���̑����i���t�敪 = �]�͐���@@�\����.���̑����i���t�]�͋敪<BR>
    * <BR>
    * [�]�͐���@@�\����.�Ǐؖ������敪 != null�̏ꍇ] <BR>
    * �E�Ǐؖ������敪 = �]�͐���@@�\����.�Ǐؖ������敪<BR>
    * <BR>
    * [�]�͐���@@�\����.���X�R�[�h  != null�̏ꍇ]<BR>
    * �E���XID = get���X(, �]�͐���@@�\����.���X�R�[�h()).get���XID()�̖߂�l<BR>
    * ���� :<BR>
    * �،���ЃR�[�h : �Ǘ���.get�،���ЃR�[�h()<BR>
    * ���X�R�[�h : �]�͐���@@�\����.���X�R�[�h<BR>
    * @@param l_request - �]�͐���@@�\�������N�G�X�g<BR>
    * @@param l_account - �ڋq<BR>
    * @@param l_admin - �Ǘ���<BR>
    * @@return List
    * @@throws WEB3BaseException
    * @@roseuid 41C0E1FE000D
    */
    protected List getTradingPowerCalcConditionParamsList(WEB3AdminTPFindCalcControlRequest l_request, WEB3Administrator l_admin)
    throws WEB3BaseException
    {
        final String METHOD_NAME = "getTradingPowerCalcConditionParamsList(WEB3AdminTPFindCalcControlRequest)";

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();

        WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
       
        //�ڋq�R�[�h != null�̂Ƃ�
        if(l_request.accountCode != null)
        {
            String l_strInstCode = l_admin.getInstitutionCode();
	
            //�P�ł��ڋq�����������ΐ���
            //�S��������Ȃ������ꍇ�G���[
            int l_intMissCount = 0;
            int l_intBranchLength = l_request.branchCodes.length;
            boolean l_blnIsFlag = true;

            for(int i = 0; i < l_intBranchLength; i++)
            {
                try
                {           		
                    WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCodes[i], l_request.accountCode);
                    if(l_blnIsFlag)
                    {
                        l_sbWhere.append("account_id in (?");
                        l_blnIsFlag = false;
                    }
                    else
                    {
                        l_sbWhere.append(", ?");		               
                    }
                    l_lisBindVars.add(new Long(l_account.getAccountId()));
                }
                //�ڋq�̑��݃`�F�b�N
                catch(WEB3BaseException e)
                {
                    log.debug("account not found. institution_code = " + l_strInstCode + " branch_code=" + l_request.branchCodes[i] + " account_code=" + l_request.accountCode);
                    l_intMissCount++;
                    log.debug("miss count=" + l_intMissCount + " branch length=" + l_intBranchLength);
                    if(l_intMissCount >= l_intBranchLength)
                    {
                        throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01987, METHOD_NAME);
                    }
                }
            }
            //�ڋq�����������ꍇ
            if(l_sbWhere.length() > 0)
            {
                l_sbWhere.append(")");           	
            }
        }
        
        //�ڋq�R�[�h�Ɨ]�͐����񂪎w�肳��Ă�����
        if ((l_request.accountCode != null)
            && ((l_request.tradingStop != null)
                || (l_request.marginOpenPositionStop != null)
                || (l_request.ifoOpenPositionStop != null)
                || (l_request.paymentStop != null)
                || (l_request.otherTradingStop != null)
                || (l_request.additionalDepositStop != null)))
        {
            l_sbWhere.append(" and ");
        }

        //�]�͐����񂪎w�肳��Ă�����
        if ((l_request.tradingStop != null)
            || (l_request.marginOpenPositionStop != null)
            || (l_request.ifoOpenPositionStop != null)
            || (l_request.paymentStop != null)
            || (l_request.otherTradingStop != null)
            || (l_request.additionalDepositStop != null))
        {
            l_sbWhere.append("(");
        }

       //[�]�͐���@@�\����.�����~�敪 != null�̏ꍇ]
       //�����~�敪 = �]�͐���@@�\����.�����~�敪
       if(l_request.tradingStop != null)
       {
           l_sbWhere.append("trading_stop = ?");
           l_lisBindVars.add(l_request.tradingStop);
       }
       //[�]�͐���@@�\����.�M�p�V�K���]�͋敪 != null�̏ꍇ]
       //�E�M�p�V�K���]�͋敪 = �]�͐���@@�\����.�M�p�V�K���]�͋敪
       if(l_request.marginOpenPositionStop != null)
       {
           if(l_request.tradingStop != null)
           {
               l_sbWhere.append(" or ");
           }
           l_sbWhere.append("margin_open_position_stop = ?");
           l_lisBindVars.add(l_request.marginOpenPositionStop);
       }
       //[�]�͐���@@�\����.�敨OP�V�K���]�͋敪 != null�̏ꍇ]
       //�E�敨OP�V�K���]�͋敪 = �]�͐���@@�\����.�敨OP�V�K���]�͋敪
       if(l_request.ifoOpenPositionStop != null)
       {
           if((l_request.tradingStop != null) ||
                   (l_request.marginOpenPositionStop != null))
           {
               l_sbWhere.append(" or ");
           }
           l_sbWhere.append("ifo_open_position_stop = ?");
           l_lisBindVars.add(l_request.ifoOpenPositionStop);
       }
       //�]�͐���@@�\����.�o���]�͋敪 != null�̏ꍇ]
       //�E�o���]�͋敪 = �]�͐���@@�\����.�o���]�͋敪
       if(l_request.paymentStop != null)
       {
           if((l_request.tradingStop != null) ||
                   (l_request.marginOpenPositionStop != null) ||
                   (l_request.ifoOpenPositionStop != null))
           {
               l_sbWhere.append(" or ");
           }
           l_sbWhere.append("payment_stop = ?");
           l_lisBindVars.add(l_request.paymentStop);
       }
       //[�]�͐���@@�\����.���̑����i���t�敪 != null�̏ꍇ]
       //�E���̑����i���t�敪 = �]�͐���@@�\����.���̑����i���t�]�͋敪
       if(l_request.otherTradingStop != null)
       {
           if((l_request.tradingStop != null) ||
                   (l_request.marginOpenPositionStop != null) ||
                   (l_request.ifoOpenPositionStop != null) ||
                   (l_request.paymentStop != null))
           {
               l_sbWhere.append(" or ");
           }
           l_sbWhere.append("other_trading_stop = ?");
           l_lisBindVars.add(l_request.otherTradingStop);
       }

       //[�]�͐���@@�\����.�Ǐؖ������敪 != null�̏ꍇ]
       //�E�Ǐؖ������敪 = �]�͐���@@�\����.�Ǐؖ������敪
       if (l_request.additionalDepositStop != null)
       {
           if ((l_request.tradingStop != null)
               || (l_request.marginOpenPositionStop != null)
               || (l_request.ifoOpenPositionStop != null)
               || (l_request.paymentStop != null)
               || (l_request.otherTradingStop != null))
           {
               l_sbWhere.append(" or ");
           }
           l_sbWhere.append("additional_deposit_stop = ?");
           l_lisBindVars.add(l_request.additionalDepositStop);
       }

       //�]�͐����񂪎w�肳��Ă�����
       if ((l_request.tradingStop != null)
           || (l_request.marginOpenPositionStop != null)
           || (l_request.ifoOpenPositionStop != null)
           || (l_request.paymentStop != null)
           || (l_request.otherTradingStop != null)
           || (l_request.additionalDepositStop != null))
       {
               l_sbWhere.append(")");
       }

       //[�]�͐���@@�\����.���X�R�[�h  != null�̏ꍇ]
       //�E���XID = get���X(, �]�͐���@@�\����.���X�R�[�h()).get���XID()�̖߂�l
       //���� :
       //�،���ЃR�[�h : �Ǘ���.get�،���ЃR�[�h()
       //���X�R�[�h : �]�͐���@@�\����.���X�R�[�h
       if(l_request.branchCodes != null)
       {
           if(l_sbWhere.length() > 0)
           {
               l_sbWhere.append(" and ");
           }
           l_sbWhere.append("branch_id in (");
           for(int i = 0; i < l_request.branchCodes.length; i++)
           {
               long l_lngBranchId;
               try
               {
                   l_lngBranchId = l_accMgr.getBranch(l_admin.getInstitution(), l_request.branchCodes[i]).getBranchId();
                   if(i == 0)
                       l_sbWhere.append("?");
                   else
                       l_sbWhere.append(",?");
                   l_lisBindVars.add(new Long(l_lngBranchId));
               }
               catch(NotFoundException nfe)
               {
                   //�Y�����X�Ȃ�
                   throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
               }

           }
           l_sbWhere.append(")");
       }

       final String l_strWhere = l_sbWhere.toString();
       final Object[] l_bindVars = l_lisBindVars.toArray();

       log.debug("l_strWhere=" + l_strWhere);
       log.debug("l_bindVars=" + l_bindVars);
       
       try
       {
           QueryProcessor l_qp = Processors.getDefaultProcessor();
           return l_qp.doFindAllQuery(TradingpowerCalcConditionRow.TYPE, l_strWhere, l_bindVars);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
       
    }

   /**
    * (get�ڋq�]�͏���Params)
    * �ڋq�]�͏����e�[�u�����APK�����������ʂ�ԋp����B
    * @@param l_lngTradingPowerCalcConditionId - �ڋq�]�͏���ID
    * @@return TradingPowerCalcConditionRow
    * @@roseuid 41CBAA9D03CC
    */
   protected TradingpowerCalcConditionRow getTradingPowerCalcConditionParams(long l_lngTradingPowerCalcConditionId)
   throws WEB3SystemLayerException
   {
       final String METHOD_NAME = "getTradingPowerCalcConditionParams(long l_lngTradingPowerCalcConditionId)";
       try
       {
           return TradingpowerCalcConditionDao.findRowByPk(l_lngTradingPowerCalcConditionId);
       }
       catch(DataFindException dfe)
       {
           //TODO ErrorInfo��`�m�F�@@SYSTEM_ERROR_80005�i�Y���f�[�^�Ȃ��j��OK?
           log.error(METHOD_NAME + " updating data not found l_lngTradingPowerCalcConditionId=" + l_lngTradingPowerCalcConditionId);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);

       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
   }

   /**
    * (update�ڋq�]�͏���Params)<BR>
    * �ڋq�]�͏����e�[�u���̍X�V���s���B<BR>
    * <BR>
    * �����F<BR>
    * PK = ���N�G�X�g.�ڋq�]�͏���ID<BR>
    * <BR>
    * �l�F<BR>
    * �E�M�p�V�K���]�͋敪 = ���N�G�X�g.�M�p�V�K���]�͋敪<BR>
    * �E�敨OP�V�K���]�͋敪 = ���N�G�X�g.�敨OP�V�K���]�͋敪<BR>
    * �E�o���]�͋敪 = ���N�G�X�g.�o���]�͋敪<BR>
    * �E���̑����i���t�]�͋敪 = ���N�G�X�g���̑����i���t�]�͋敪<BR>
    * �E�Ǐؖ������敪 = ���N�G�X�g.�ύX���e.�Ǐؖ������敪 <BR>
    * [���N�G�X�g.����]�͋敪 = �����~����(0)�̏ꍇ]<BR>
    * ���ʗ��֋����� = 0<BR>
    * <BR>
    * @@param l_request - �]�͐���@@�\�ύX�������N�G�X�g
    * @@param l_admin - �Ǘ���
    * @@return Timestamp
    * @@roseuid 41B967440336
    */
   protected Timestamp updateTradingPowerCalcConditionParams(WEB3AdminTPChangeCalcControlCompleteRequest l_request, WEB3Administrator l_admin)
   throws WEB3BaseException
   {
       final String METHOD_NAME = "updateTradingPowerCalcConditionParams(WEB3AdminTPChangeCalcControlCompleteRequest l_request, WEB3Administrator l_admin)";
       int l_intUdateCount = 0;
       Timestamp l_timestamp = null;

       try
       {
	       l_timestamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
	               WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

	       long l_lngCalcConditionId = Long.parseLong(l_request.calcConditionId);
	       Map l_updateMap = new HashMap();
	       l_updateMap.put("trading_stop", l_request.tradingStop);//�ǉ�

           //���N�G�X�g.�M�p�V�K���]�͋敪 != null�̏ꍇ
	       if(l_request.marginOpenPositionStop != null)
	       {
		       l_updateMap.put("margin_open_position_stop", l_request.marginOpenPositionStop);
	           
	       }
           //���N�G�X�g.�敨OP�V�K���]�͋敪 != null�̏ꍇ
	       if(l_request.ifoOpenPositionStop != null)
	       {
		       l_updateMap.put("ifo_open_position_stop", l_request.ifoOpenPositionStop);	           
	       }
	       l_updateMap.put("payment_stop", l_request.paymentStop);
	       l_updateMap.put("other_trading_stop", l_request.otherTradingStop);
	       l_updateMap.put("last_updater", l_admin.getAdministratorCode());
	       l_updateMap.put("last_updated_timestamp", l_timestamp);

           //���N�G�X�g.�Ǐؖ������敪 != null�̏ꍇ
           if (l_request.additionalDepositStop != null)
           {
               l_updateMap.put("additional_deposit_stop", l_request.additionalDepositStop);
           }

	       //[���N�G�X�g.����]�͋敪 = �����~����(0)�̏ꍇ]
	       //���ʗ��֋����� = 0
	       if(WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.ENABLE.equals(l_request.tradingStop))
	       {
	           l_updateMap.put("special_debit_amount", new Double(0));
	       }

	       QueryProcessor l_qp = Processors.getDefaultProcessor();
	       l_intUdateCount = l_qp.doUpdateQuery(new TradingpowerCalcConditionPK(l_lngCalcConditionId), l_updateMap);

       }
       //�ڋq�]�͏���ID���s���Ȓl�̂Ƃ�
       catch(NumberFormatException nfe)
       {
           throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01893, METHOD_NAME);
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
           log.error(METHOD_NAME + " updating data not found calcConditionId=" + l_request.calcConditionId);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
       }
       return l_timestamp;

    }
   
   /**
    * (create�ڋq�]�͐�����) <BR>
    * <BR>
    * �ڋq�]�͐�������쐬���A�ԋp����B<BR>
    * <BR>
    * 1)�]�͐�����N���X�𐶐�����B <BR>
    * <BR>
    * 2)�]�͐�����N���X�ɒl���Z�b�g����B <BR>
    * �@@2-1)�]�͐�����.���X�R�[�h = �ڋq.get���X�R�[�h <BR>
    * �@@2-2)�]�͐�����.�ڋq�R�[�h = �ڋq.get�ڋq�R�[�h <BR>
    * �@@2-3)�]�͐�����.�ڋq�� = �ڋq.get�ڋq�� <BR>
    * �@@2-4)�]�͐�����.�ڋq�]�͏���ID = �ڋq�]�͏���.get�ڋq�]�͏���ID <BR>
    * �@@2-5)�]�͐�����.�����~�敪 = �ڋq�]�͏���.�]�͒�~�敪 <BR>
    * �@@2-6)�]�͐�����.�Ǐؖ������敪 = �ڋq�]�͏���.�Ǐؖ������敪 <BR>
    * <BR>
    * 3)�ڋq.is�M�p�����J�� <BR>
    * �@@3-1) 0:�w��Ȃ��̏ꍇ <BR>
    * �@@�@@�]�͐�����.�M�p�V�K���敪 = �ڋq�]�͏���.get�M�p�V�K����~�敪 <BR>
    * �@@�@@�]�͐�����.�Ǐؖ������敪 = �ڋq�]�͏���.get�Ǐؖ������敪 <BR>
    * �@@3-2) ����ȊO�̏ꍇ <BR>
    * �@@�@@�]�͐�����.�M�p�V�K���敪 = null <BR>
    * �@@�@@�]�͐�����.�Ǐؖ������敪 = null <BR>
    * <BR>
    * 4)is��OP�����J�݌ڋq <BR>
    * �@@4-1) �����J�ݍς̏ꍇ <BR>
    * �@@�@@�@@�敨OP�V�K�]�͋敪 = �ڋq�]�͏���.get�敨OP�V�K����~�敪 <BR>
    * �@@4-2) �������J�݂̏ꍇ <BR>
    * �@@�@@�@@�敨OP�V�K�]�͋敪 = null <BR>
    * <BR>
    * 5)�]�͐�����N���X�ɒl���Z�b�g����B <BR>
    * �@@5-1) �]�͐�����.�o����~�敪 = �ڋq�]�͏���.�o����~�敪 <BR>
    * �@@5-2) �]�͐�����.���̑����i���t�敪 = �ڋq�]�͏���.���̑����i���t�敪 <BR>
    * <BR>
    * 6) �]�͐������ԋp����B <BR>
    * <BR>
    * @@param l_account - �ڋq<BR>
    * (�ڋq)
    * @@param l_row - �ڋq�]�͏���Params<BR>
    * (�ڋq�]�͏���)
    * @@return WEB3AdminTPCalcControlInfo
    */
   private WEB3AdminTPCalcControlInfo createCalcControlInfo(WEB3GentradeMainAccount l_account, TradingpowerCalcConditionRow l_row)
   {
		WEB3AdminTPCalcControlInfo l_calcControlInfo = new WEB3AdminTPCalcControlInfo();
		l_calcControlInfo.branchCode = l_account.getBranch().getBranchCode();
		l_calcControlInfo.accountCode = l_account.getDisplayAccountCode();
		l_calcControlInfo.accountName = l_account.getDisplayAccountName();
		l_calcControlInfo.calcConditionId = String.valueOf(l_row.getCalcConditionId());
		l_calcControlInfo.tradingStop = l_row.getTradingStop();

        //�Ǐؖ������敪 = �ڋq�]�͏���Params.get�Ǐؖ������敪()
        l_calcControlInfo.additionalDepositStop = l_row.getAdditionalDepositStop();

		if (l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
		{
			l_calcControlInfo.marginOpenPositionStop = l_row.getMarginOpenPositionStop();
            //�Ǐؖ������敪 = �ڋq�]�͏���Params.get�Ǐؖ������敪()
            l_calcControlInfo.additionalDepositStop = l_row.getAdditionalDepositStop();
		}
		else
		{
		    //�����Z�b�g���Ȃ��B(null)
			l_calcControlInfo.marginOpenPositionStop = null;
            l_calcControlInfo.additionalDepositStop = null;
		}
		
		if(isIfoAccountEstablished(l_account))
		{
			l_calcControlInfo.ifoOpenPositionStop = l_row.getIfoOpenPositionStop();		    
		}
		else
		{
		    //�����Z�b�g���Ȃ��B(null)
			l_calcControlInfo.ifoOpenPositionStop = null;		    		    
		}
		
		l_calcControlInfo.paymentStop = l_row.getPaymentStop();
		l_calcControlInfo.otherTradingStop = l_row.getOtherTradingStop();

		return l_calcControlInfo;
		
   }
   
   /**
    * �敨�I�v�V���������J�݌ڋq���ǂ������f����B
    * �J�݂��Ă���ꍇtrue, ����ȊO��false��Ԃ��B
    * 
    * @@param l_account
    * @@return boolean 
    */
   private boolean isIfoAccountEstablished(WEB3GentradeMainAccount l_account)
   {
		MainAccountRow l_row = (MainAccountRow)l_account.getDataSourceObject();
		
		//���A���É��A�����̂����ꂩ�Ō������J�݂��Ă����true
		if((!WEB3AdminTPIfoAccountOpenDivDef.DEFAULT.equals(l_row.getIfoAccOpenDivNagoya()))
		        	||
		        (!WEB3AdminTPIfoAccountOpenDivDef.DEFAULT.equals(l_row.getIfoAccOpenDivOsaka()))
		        	||
		        (!WEB3AdminTPIfoAccountOpenDivDef.DEFAULT.equals(l_row.getIfoAccOpenDivTokyo())))
		{
		            return true;
		    
		}
		return false;
   }
   
   /**
    * (is��ʕ\���ڋq)<BR>
    * <BR>
    * ��ʂɕ\�����邩�ǂ������`�F�b�N����<BR>
    * �M�p�V�K����~���I������Ă���ꍇ�͐M�p�ڋq�`�F�b�N<BR>
    * �I�v�V�����V�K����~���I������Ă���ꍇ�̓I�v�V�����ڋq�`�F�b�N<BR>
    * <BR>
    * 1)�]�͐���@@�\�������N�G�X�g.�M�p�V�K���]�͋敪�݂̂Ƀ`�F�b�N�����Ă���ꍇ<BR>
    * �@@�M�p�q�łȂ��ꍇ�A�M�p�V�K����~�敪��false��ݒ肷��B<BR>
    * <BR>
    * 2)�]�͐���@@�\�������N�G�X�g.�敨OP�V�K���]�͋敪�̂ݑI������Ă���ꍇ<BR>
    * �@@�I�v�V�����q�łȂ��ꍇ�A�I�v�V�����V�K����~�敪��false��ݒ肷��B<BR>
    * <BR>
    * 3)�]�͐���@@�\�������N�G�X�g.�Ǐؖ������敪�݂̂Ƀ`�F�b�N�����Ă���ꍇ<BR>
    * �@@�M�p�q�łȂ��ꍇ�A�Ǐؖ������敪��false��ݒ肷��B<BR>
    * <BR>
    * 4)�]�͐���@@�\�������N�G�X�g.�����~�敪���I������Ă���ꍇ<BR>
    * �@@�]�͒�~�敪��true��ݒ肷��B<BR>
    * <BR>
    * 5)�]�͐���@@�\�������N�G�X�g.�o���]�͋敪���I������Ă���ꍇ<BR>
    * �@@�o����~�敪��true��ݒ肷��B<BR>
    * <BR>
    * 6)�]�͐���@@�\�������N�G�X�g.���̑����i���t�敪���I������Ă���ꍇ<BR>
    * �@@���̑����i���t��~�敪��true��ݒ肷��B<BR>
    * <BR>
    * 7)�\���ڋq�敪�̐ݒ�<BR>
    * �@@7-1)���X�R�[�h�ƌڋq�R�[�h�����w�肳��Ă��Ȃ��ꍇ�A<BR>
    * �@@�@@�@@���́A���X�R�[�h���邢�͌ڋq�R�[�h�����w�肳��Ă��Ȃ��ꍇ�A<BR>
    * �@@�@@�@@�\���ڋq�敪��true��ݒ�<BR>
    * �@@7-2)1�`6�܂ł̂����ꂩ��true�ɂȂ��Ă���ꍇ�͕\���ڋq�敪��true��ݒ�,<BR>
    * �@@�@@�@@���ׂĂ�false�ƂȂ��Ă���ꍇ�͕\���ڋq��<BR>
    * �@@�@@�@@����false��ݒ肷��B<BR>
    * <BR>
    * 8)�\���ڋq�敪��ԋp����B<BR>
    * @@param l_request - �]�͐���@@�\�������N�G�X�g<BR>
    * (�]�͐���@@�\�������N�G�X�g)
    * @@param l_account - �ڋq<BR>
    * (�ڋq)
    * @@param l_row - �ڋq�]�͏���Params<BR>
    * (�ڋq�]�͏���Params)
    * @@return boolean
    */
   protected boolean isShowableAccount(WEB3AdminTPFindCalcControlRequest l_request, WEB3GentradeMainAccount l_account, TradingpowerCalcConditionRow l_row)
   {
       boolean isShowByMarginOpenPositionStop = false;
       boolean isShowByIfoOpenPositionStop = false;
       boolean l_blnIsShowByAdditionalDepositStop = false;
       boolean isShowByTradingStop = false;
       boolean isShowByPaymentStop = false;
       boolean isShowByOtherTradingStop = false;
       boolean isShow = false;       
       
       //��������
       //�]�͐���@@�\�������N�G�X�g.�M�p�V�K���]�͋敪�݂̂Ƀ`�F�b�N�����Ă���ꍇ
       //�M�p�q�łȂ��ꍇ�A�M�p�V�K����~�敪��false��ݒ肷��B
       if(l_request.marginOpenPositionStop != null)
       {
           if(WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.STOP.equals(l_row.getMarginOpenPositionStop()))
           {
               isShowByMarginOpenPositionStop = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);                          
           }
           else
           {
               isShowByMarginOpenPositionStop = false;
           }
       }

       //�]�͐���@@�\�������N�G�X�g.�敨OP�V�K���]�͋敪�̂ݑI������Ă���ꍇ
       //�I�v�V�����q�łȂ��ꍇ�A�I�v�V�����V�K����~�敪��false��ݒ肷��B
       if(l_request.ifoOpenPositionStop != null)
       {
           if(WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.STOP.equals(l_row.getIfoOpenPositionStop()))
           {
               isShowByIfoOpenPositionStop = this.isIfoAccountEstablished(l_account);               
           }
           else
           {
               isShowByIfoOpenPositionStop = false;
           }
       }       

       //�]�͐���@@�\�������N�G�X�g.�Ǐؖ������敪�݂̂Ƀ`�F�b�N�����Ă���ꍇ
       //�M�p�q�łȂ��ꍇ�A�Ǐؖ������敪��false��ݒ肷��B
       if (l_request.additionalDepositStop != null)
       {
           if (WEB3AdditionalDepositStopDef.ADDITIONAL_DEPOSIT_STOP.equals(l_row.getAdditionalDepositStop()))
           {
               l_blnIsShowByAdditionalDepositStop = l_account.isMarginAccountEstablished(
                   WEB3GentradeRepaymentDivDef.DEFAULT);
           }
           else
           {
               l_blnIsShowByAdditionalDepositStop = false;
           }
       }

       //�]�͐���@@�\�������N�G�X�g.�����~�敪���I������Ă���ꍇ
       //�]�͒�~�敪��true��ݒ肷��B
       if(l_request.tradingStop != null)
       {
           if(WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.STOP.equals(l_row.getTradingStop()))
           {
               isShowByTradingStop = true;
           }
           else
           {
               isShowByTradingStop = false;
           }           
       }

       //�]�͐���@@�\�������N�G�X�g.�o���]�͋敪���I������Ă���ꍇ
       //�o����~�敪��true��ݒ肷��B
       if(l_request.paymentStop != null)
       {
           if(WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.STOP.equals(l_row.getPaymentStop()))
           {
               isShowByPaymentStop = true;
           }
           else
           {
               isShowByPaymentStop = false;
           }           
       }

       //�]�͐���@@�\�������N�G�X�g.���̑����i���t�敪���I������Ă���ꍇ
       //���̑����i���t��~�敪��true��ݒ肷��B
       if(l_request.otherTradingStop != null)
       {
           if(WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.STOP.equals(l_row.getOtherTradingStop()))
           {
               isShowByOtherTradingStop = true;
           }
           else
           {
               isShowByOtherTradingStop = false;
           }           
       }
       
       //���X�R�[�h�ƌڋq�R�[�h�����w�肳��Ă��Ȃ��ꍇ�A
       //���́A���X�R�[�h���邢�͌ڋq�R�[�h�����w�肳��Ă��Ȃ��ꍇ�A�\���ڋq�敪��true��ݒ�
       if ((l_request.branchCodes != null || l_request.accountCode != null)
           && l_request.marginOpenPositionStop == null
           && l_request.ifoOpenPositionStop == null
           && l_request.additionalDepositStop == null
           && l_request.tradingStop == null
           && l_request.paymentStop == null
           && l_request.otherTradingStop == null
         )
       {
           isShow = true;
       }
       else
       {
           isShow = isShowByMarginOpenPositionStop || isShowByIfoOpenPositionStop
               || l_blnIsShowByAdditionalDepositStop || isShowByTradingStop || isShowByPaymentStop
               || isShowByOtherTradingStop;
       }
       

       log.debug("isShowByMarginOpenPositionStop=" + isShowByMarginOpenPositionStop);
	   log.debug("isShowByIfoOpenPositionStop=" + isShowByIfoOpenPositionStop);
	   log.debug("isShowByTradingStop=" + isShowByTradingStop);
	   log.debug("isShowByPaymentStop=" + isShowByPaymentStop);
	   log.debug("isShowByOtherTradingStop=" + isShowByOtherTradingStop);
	   log.debug("isShow=" + isShow);
                      
       if(isShow)
       {
           log.debug("�\������ڋq=" + l_account.getAccountCode());
       }
       
       return isShow;
       
   }
   


}
@
